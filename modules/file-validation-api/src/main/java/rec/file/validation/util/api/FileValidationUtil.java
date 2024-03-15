package rec.file.validation.util.api;

import com.liferay.document.library.kernel.store.DLStoreUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Validator;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.io.IOUtils;

import rec.file.conf.service.model.File_Conf;
import rec.file.conf.service.service.File_ConfLocalServiceUtil;
import rec.file.validation.util.constants.FileValidationConstants;
import rec.file.validation.util.exceptions.FileValidationException;

/**
 * @author mmaceasd
 */
public interface FileValidationUtil {
	Log _log = LogFactoryUtil.getLog(FileValidationUtil.class.getName());

	static JSONObject importFile(String filename, File file, String fileType, ServiceContext serviceContext,
								 String uploadedFrom, long folderId, long userId) throws JSONException {
		_log.debug("=============================Into importFile");

		JSONObject response = JSONFactoryUtil.createJSONObject();
		_log.debug("fileType" + fileType);
		File_Conf fileSchemas;
		fileSchemas = File_ConfLocalServiceUtil.findBydocFileType(fileType);

		try {
			DLStoreUtil.validate(filename, true);
		} catch (PortalException e) {
			_log.error("ERROR - Extension not enabled on the REC Portal", e);
			return FileValidationException.getError(serviceContext.getCompanyId(), serviceContext.getScopeGroupId(),
					filename, userId, uploadedFrom, folderId, "R001", 0, 0, false);
		}

		if (Validator.isNotNull(fileSchemas)) {
			JSONObject json = JSONFactoryUtil.createJSONObject(fileSchemas.getDocConfigJSON());

			// CHANGE
			try {
				response = checkExtension(filename, file, fileType, serviceContext, uploadedFrom, folderId, userId,
						json);
			} catch (IOException e) {
				response.put("code", 500);
				response.put("message", e.getMessage());
			} catch (FileValidationException e) {
				response.put("code", 500);
				response.put("message", e.getMessage());
			}
		} else {
			_log.info("FILE VALIDATION - No validation required");
			response.put("code", 200);
			response.put("message", "No validation required");
		}
		return response;
	}

	static JSONObject checkExtension(String filename, File file, String fileType, ServiceContext serviceContext,
									 String uploadedFrom, long folderId, long userId, JSONObject json)
			throws IOException, FileValidationException {
		boolean isZip = false;

		if (FileUtil.getExtension(filename).equals("zip")) {
			isZip = true;
			ZipFile zipFile = new ZipFile(file);
			Map<String, File> mapFilesFromZip = new HashMap<>();
			List<String> zipFilesExtension = new ArrayList<>();

			Enumeration<? extends ZipEntry> entries = zipFile.entries();

			int i = 0;
			while (entries.hasMoreElements()) {
				ZipEntry entry = entries.nextElement();
				String extension = FileUtil.getExtension(entry.getName());
				InputStream inputStream = zipFile.getInputStream(entry);
				File fileZip = new File(entry.getName());
				_log.info(fileZip.getName());
				// fileZip.deleteOnExit();
				try (FileOutputStream outputStream = new FileOutputStream(fileZip)) {
					IOUtils.copy(inputStream, outputStream);
				}
				zipFilesExtension.add(extension);
				mapFilesFromZip.put("File" + i, fileZip);
				i++;
			}
			zipFile.close();
			boolean allFilesSameExtension = zipFilesExtension.stream().distinct().limit(2).count() <= 1;

			if (!allFilesSameExtension) {
				_log.error("ERROR - Extensions from .zip are not the same");
				FileValidationException.getError(serviceContext.getCompanyId(), serviceContext.getScopeGroupId(),
						filename, userId, uploadedFrom, folderId, "R002", 0, 0, false);
				throw new FileValidationException("Extensions from .zip are not the same");
			}

			JSONObject zipJson = JSONFactoryUtil.createJSONObject();
			;
			for (File fileMap : mapFilesFromZip.values()) {
				zipJson = JSONFactoryUtil.createJSONObject();
				zipJson = validateFile(fileMap.getName(), fileMap, isZip, json, serviceContext, uploadedFrom, folderId,
						userId);
				if (zipJson.getInt("code") != 200) {
					break;
				}
			}

			return zipJson;
		} else {
			return validateFile(filename, file, isZip, json, serviceContext, uploadedFrom, folderId, userId);
		}
	}

	static JSONObject validateFile(String filename, File file, boolean isZip, JSONObject schemaJSON,
								   ServiceContext serviceContext, String uploadedFrom, long folderId, long userId)
			throws FileValidationException {
		_log.debug("=============================Into validateFile");

		JSONObject response = JSONFactoryUtil.createJSONObject();
		int code = 200;
		String message = "Your file: " + filename + " has been uploaded";

		boolean hasLength = true;
		CSVReader csvReader = null;
		//Used for BlockHeader
		CSVReader csvReader1 = null;
		CSVReader csvReader2 = null;

		Map<String, List<Integer>> positionMap = new HashMap<String, List<Integer>>();
		List<String> header = new ArrayList<>();
		List<String> headerBlock = new ArrayList<>();
		List<String> deduped = new ArrayList<>();
		Map<String, JSONArray> blockMap = new HashMap<>();
		List<String> blockList = new ArrayList<>();
		List<JSONObject> dataList = new ArrayList<>();
		List<Integer> positionList = new ArrayList<>();
		Integer[][] lengthList;
		Map<String, List<String>> valuesMap = null;
		Map<String, String> extensionMap = new HashMap<>();

		_log.info("###### JSON VALUES ######");

		// Getting has_headers parameter from json config
		boolean has_headers = schemaJSON.getBoolean("has_headers");
		_log.info("has_headers " + has_headers);

		// Getting has_blocks parameter from json config
		boolean has_blocks = schemaJSON.getBoolean("has_blocks");
		_log.info("has_blocks" + has_blocks);

		String mandatoryBlocks = schemaJSON.getString("mandatory_blocks", StringPool.BLANK);

		// Getting enabled_extensions array from json config
		JSONArray enabled_extensions = schemaJSON.getJSONArray("enabled_extensions");

		// Iterating array to store valid extensions in Map object
		for (int i = 0; i < enabled_extensions.length(); i++) {
			JSONObject ext = enabled_extensions.getJSONObject(i);
			String extension = ext.getString("extension");
			_log.info("extension " + extension);
			String delimiter = ext.getString("delimiter");
			_log.info("delimiter " + delimiter);
			extensionMap.put(extension, delimiter);
		}

		// Getting extension of the uploaded file
		String fileExtension = FileUtil.getExtension(filename);
		_log.info("##### FILE EXTENSION " + fileExtension.toUpperCase() + " #####");

		// Checking if the extension is .dat
		boolean isDat = fileExtension.equalsIgnoreCase("dat");
		if (!isDat) {
			try {
				csvReader = validateFileParse(extensionMap, file, filename, fileExtension, serviceContext, uploadedFrom,
						folderId, userId);
				if(has_blocks) {
					csvReader1 = validateFileParse(extensionMap, file, filename, fileExtension, serviceContext,
							uploadedFrom, folderId, userId);
					csvReader2 = validateFileParse(extensionMap, file, filename, fileExtension, serviceContext,
							uploadedFrom, folderId, userId);
				}
			} catch (FileValidationException e) {
				_log.error("ERROR - Parsing uploaded file", e);
				response.put("code", 500);
				response.put("message", e.getErrorMessage());
				return response;
			} catch (FileNotFoundException e) {
				_log.error("Error validating file", e);
				response.put("code", 500);
				response.put("message", "File validation error in " + filename + ", please contact support team");
				return response;
			}
		}

		// Getting columns array from json config
		JSONArray columns = schemaJSON.getJSONArray("columns");

		// Getting amount of columns
		int columnsLength = columns.length();

		// Creation array that stores minLength and maxLength
		lengthList = new Integer[columns.length()][2];
		positionList.add(0);

		_log.info("###### COLUMN VALUES ######");
		for (int i = 0; i < columns.length(); i++) {
			JSONObject column = columns.getJSONObject(i);

			if (has_blocks) {
				String blockName = column.getString("blockName");
				if (!blockMap.containsKey(blockName)) {
					blockMap.put(blockName, JSONFactoryUtil.createJSONArray());
				}
				int maxLength = column.getInt("maxLength");
				_log.info("maxLength " + maxLength);

				// Getting maxLength name parameter from data object
				int minLength = column.getInt("minLength");
				_log.info("minLength " + minLength);

				if ((maxLength == 0 || minLength == 0) && hasLength) {
					hasLength = false;
				}

				if (hasLength) {
					// Store data in lengthList
					lengthList[i][0] = minLength;
					lengthList[i][1] = maxLength;
				}

				if (isDat) {
					Integer startPosition = null;
					try {
						startPosition = Integer.parseInt(column.getString("startPosition"));
					} catch (Exception e) {
						_log.error("ERROR - startPosition null or Not Numeric", e);
						FileValidationException.getError(serviceContext.getCompanyId(),
								serviceContext.getScopeGroupId(), filename, userId, uploadedFrom, folderId, "R002", 0,
								0, false);
						throw new FileValidationException("Data size not valid");
					}
					_log.info(startPosition);
					positionList.add(startPosition);
				}
			} else {

				// Getting column name parameter from columns array
				String name = column.getString("name");

				if (has_headers) {
					_log.info("name " + name);
					// Store column name in header array
					header.add(name);
				}

				JSONObject data = column.getJSONObject("data");
				// Store data in data list
				dataList.add(data);

				// Getting maxLength name parameter from data object
				int maxLength = column.getInt("maxLength");
				_log.info("maxLength " + maxLength);

				// Getting maxLength name parameter from data object
				int minLength = column.getInt("minLength");
				_log.info("minLength " + minLength);

				if ((maxLength == 0 || minLength == 0) && hasLength) {
					hasLength = false;
				}

				if (hasLength) {
					// Store data in lengthList
					lengthList[i][0] = minLength;
					lengthList[i][1] = maxLength;
				}
				if (isDat) {
					Integer startPosition = null;
					try {
						startPosition = Integer.parseInt(column.getString("startPosition"));
					} catch (Exception e) {
						_log.error("ERROR - startPosition null or Not Numeric", e);
						FileValidationException.getError(serviceContext.getCompanyId(), serviceContext.getScopeGroupId(),
								filename, userId, uploadedFrom, folderId, "R002", 0, 0, false);
						throw new FileValidationException("Data size not valid");
					}
					_log.info(startPosition);
					positionList.add(startPosition);
				}
			}
		}

		if (has_blocks) {
			for (int i = 0; i < columns.length(); i++) {
				JSONObject column = columns.getJSONObject(i);
				String blockName = column.getString("blockName");
				blockMap.get(blockName).put(column);
				headerBlock.add(blockName);
			}

		}

		if (isDat) {
			if (has_blocks) {
				_log.info("##### DAT BLOCK HEADER VALIDATION #####");
				try {
					
					deduped = headerBlock.stream().distinct().collect(Collectors.toList());
					
					positionList.remove(0);
					int previousOccurrence = 0;
					int longestList = 0;
					for (int i = 0; i <= deduped.size() - 1; i++) {
						int occurrences = Collections.frequency(headerBlock, deduped.get(i));
						positionMap.put(deduped.get(i), new ArrayList<Integer>());
						
						if(i == 0) {
							positionMap.get(deduped.get(i)).addAll(positionList.subList(0, occurrences));
						} else {
							positionMap.get(deduped.get(i)).addAll(positionList.subList(previousOccurrence, occurrences + previousOccurrence));
						}
						previousOccurrence += occurrences;
					}
					
					blockList = getBlockHeaderDat(file, positionMap);
					
					if (!blockList.isEmpty()) {
						for (int i = 0; i < blockList.size(); i++) {
							if(!deduped.contains(blockList.get(i))) {
								_log.error("ERROR - validateBlockHeaderDat() - blockName does not exist in the file uploaded");
								response.put("code", 500);
								response.put("message", "File validation error in " + filename + ", block " + deduped.get(i) + " does not exist in the uploaded file.");
								return response;
							}
						}

						if (!mandatoryBlocks.isEmpty()) {
							String[] mandatoryItems = mandatoryBlocks.split(",");
							for (String mandatoryItem : mandatoryItems) {
								if (!blockList.contains(mandatoryItem)) {
									_log.error("ERROR - validateBlockHeader() - mandatory blockName does not exist in the file uploaded");
									response.put("code", 500);
									response.put("message", "File validation error in " + filename + ", block " + mandatoryItem + " does not exist in the uploaded file.");
									return response;
								} 
							}
						}
						for (Map.Entry<String,List<Integer>> entry : positionMap.entrySet()) {
							_log.info("################## POSITION KEY: "+ entry.getKey() + " POSITION VALUES " + entry.getValue());
							if(entry.getValue().size() > longestList){
								longestList = entry.getValue().size();
							}
						}
						
						valuesMap = validateBlockHeaderDat(file, positionMap, longestList, lengthList, filename, isZip, userId,
								uploadedFrom, folderId, serviceContext, hasLength, has_headers, deduped);
					} else {
						_log.error("ERROR - validateBlockHeaderDat()");
						response.put("code", 500);
						response.put("message", "File validation error in " + filename + ", please contact support team. There may be issues within the file delimiter.");
						return response;
					}
				} catch (IOException e) {
					_log.error("ERROR - validateBlockHeaderDat()", e);
					response.put("code", 500);
					response.put("message", "File validation error in " + filename + ", please contact support team");
					return response;
				} catch (FileValidationException e) {
					_log.error("ERROR - validateBlockHeaderDat()", e);
					response.put("code", 500);
					response.put("message", e.getErrorMessage());
					return response;
				}
			} else {
				_log.info("##### DAT VALIDATION #####");
				try {
					valuesMap = dataRecollectionDat(file, positionList, lengthList, filename, isZip, userId,
							uploadedFrom, folderId, serviceContext, hasLength, has_headers);
				} catch (IOException e) {
					_log.error("ERROR - dataRecollectionDat()", e);
					response.put("code", 500);
					response.put("message", "File validation error in " + filename + ", please contact support team");
					return response;
				} catch (FileValidationException e) {
					_log.error("ERROR - dataRecollectionDat() ", e);
					response.put("code", 500);
					response.put("message", e.getErrorMessage());
					return response;
				}
			}
		} else {
			if (has_blocks) {
				_log.info("##### BLOCK HEADER VALIDATION #####");
				try {
					deduped = headerBlock.stream().distinct().collect(Collectors.toList());
					
					blockList = getBlockHeader(csvReader1);
					if (!blockList.isEmpty()) {
						for (int i = 0; i < deduped.size(); i++) {
							if(!deduped.contains(blockList.get(i))) {
								_log.error("ERROR - validateBlockHeader() - blockName does not exist in the file uploaded");
								response.put("code", 500);
								response.put("message", "File validation error in " + filename + ", block " + deduped.get(i) + " does not exist in the uploaded file.");
								return response;
							}
						}

						if (!mandatoryBlocks.isEmpty()) {
							String[] mandatoryItems = mandatoryBlocks.split(",");
							for (String mandatoryItem : mandatoryItems) {
								if (!blockList.contains(mandatoryItem)) {
									_log.error("ERROR - validateBlockHeader() - mandatory blockName does not exist in the file uploaded");
									response.put("code", 500);
									response.put("message", "File validation error in " + filename + ", block " + mandatoryItem + " does not exist in the uploaded file.");
									return response;
								}
							}
						} 

						valuesMap = validateBlockHeader(csvReader, csvReader2, lengthList, columnsLength, filename, isZip, userId,
								uploadedFrom, folderId, serviceContext, hasLength, has_headers, blockMap, blockList);
					} else {
						_log.error("ERROR - validateBlockHeader()");
						response.put("code", 500);
						response.put("message", "File validation error in " + filename + ", please contact support team. There may be issues within the file delimiter.");
						return response;
					}
				} catch (IOException e) {
					_log.error("ERROR - validateBlockHeader()", e);
					response.put("code", 500);
					response.put("message", "File validation error in " + filename + ", please contact support team");
					return response;
				} catch (FileValidationException e) {
					_log.error("ERROR - validateBlockHeader() ", e);
					response.put("code", 500);
					response.put("message", e.getErrorMessage());
					return response;
				}
			} else {
				try {
					valuesMap = validateHeader(has_headers, csvReader, header, lengthList, columnsLength, filename,
							isZip, userId, uploadedFrom, folderId, serviceContext, hasLength, has_headers);
				} catch (IOException e) {
					_log.error("ERROR - validateHeader()", e);
					response.put("code", 500);
					response.put("message", "File validation error in " + filename + ", please contact support team");
					return response;
				} catch (FileValidationException e) {
					_log.error("ERROR - validateHeader() ", e);
					response.put("code", 500);
					response.put("message", e.getErrorMessage());
					return response;
				}
			}
		}

		_log.info("###### DATA VALUES ######");

		if (has_blocks) {
				int blockRow = 0;
				for (int i = 0; i < deduped.size(); i++) {
					int j = 0;
					String blockName = deduped.get(i);
					JSONArray jsonArray = blockMap.get(blockName);

					for (int k = 0; k < jsonArray.length(); k++) {
						JSONObject jsonObject = jsonArray.getJSONObject(k);
						JSONObject data = jsonObject.getJSONObject("data");
						List<String> valuesList = new ArrayList<>();

						String mandatory = data.getString("mandatory");
						_log.info("mandatory " + mandatory);

						_log.info("mandatory check " + mandatory);
						String type = data.getString("type");
						_log.info("type " + type);

						String format = data.getString("format");
						_log.info("format " + format);

						JSONArray values = null;
						if (!data.getString("values").isEmpty()) {
							values = data.getJSONArray("values");
							for (int l = 0; l < values.length(); l++) {
								valuesList.add(values.get(l).toString());
							}
						}
						_log.info("values " + values);
						_log.debug("##################  JSONOBJECT "+jsonObject+" ##################  ");
						try {
							validateBlockDataType(valuesList, blockName, blockList, isZip, header, valuesMap, mandatory, j,
									format, type, filename, userId, uploadedFrom, folderId, serviceContext, has_headers, blockRow, jsonArray, has_blocks, positionMap);
						} catch (FileValidationException e) {
							_log.error("ERROR - validateBlockDataType() ", e);
							response.put("code", 500);
							response.put("message", e.getErrorMessage());
							return response;
						}
						j++;
					}
					_log.info("BLOCKROW VALUE: " + blockRow);
				}

			
		} else {
			int j = 0;
			for (JSONObject data : dataList) {
				List<String> valuesList = new ArrayList<>();

				String mandatory = data.getString("mandatory");
				_log.info("mandatory " + mandatory);

				JSONObject conditional = null;

				if (validateJsonParam(data)) {
					conditional = data.getJSONObject("conditional");

					mandatory = "C";
				}

				_log.info("mandatory check " + mandatory);
				String type = data.getString("type");
				_log.info("type " + type);

				String format = data.getString("format");
				_log.info("format " + format);

				JSONArray values = null;
				if (!data.getString("values").isEmpty()) {
					values = data.getJSONArray("values");
					for (int i = 0; i < values.length(); i++) {
						valuesList.add(values.get(i).toString());
					}
				}
				_log.info("values " + values);

				try {
					validateDataType(valuesList, conditional, isZip, header, valuesMap, mandatory, j, format, type,
							filename, userId, uploadedFrom, folderId, serviceContext, has_headers, has_blocks, "",null,0);
				} catch (FileValidationException e) {
					_log.error("ERROR - validateDataType() ", e);
					response.put("code", 500);
					response.put("message", e.getErrorMessage());
					return response;
				}
				j++;
			}
		}
		response.put("code", code);
		response.put("message", message);
		return response;
	}

	static List<String> getBlockHeader(CSVReader csvReader)
			throws IOException, FileValidationException {
		List<String> blockHeader = new ArrayList<>();
		String[] nextRecord;

		while ((nextRecord = csvReader.readNext()) != null) {
			for (int i = 0; i <= nextRecord.length - 2; i++) {
				if (i == 0) {
					blockHeader.add(nextRecord[i]);
				}

			}
		}
		return blockHeader;
	}

	static List<String> getBlockHeaderDat(File file, Map<String, List<Integer>> positionMap) 
			throws IOException, FileValidationException {
		String[] lines = null;
		List<String> blockHeader = new ArrayList<>();
		lines = Files.readAllLines(file.toPath()).toArray(new String[0]);
		
		for (String line : lines) {
			
			for (Map.Entry<String,List<Integer>> entry : positionMap.entrySet()) {
	            int firstPosition = entry.getValue().get(0);
	            String stringOccurrence = line.substring(0, firstPosition).trim();
	            
	            if(stringOccurrence.equals(entry.getKey())) {
	            	blockHeader.add(stringOccurrence);
	            	break;
	            }
	            
	            
			}

		}
		return blockHeader;
		
	}

	static CSVReader validateFileParse(Map<String, String> extensionMap, File file, String filename,
									   String fileExtension, ServiceContext serviceContext, String uploadedFrom, long folderId, long userId)
			throws FileValidationException, FileNotFoundException {
		_log.debug("=============================Into validateFileParse");	

		if (extensionMap.containsKey(fileExtension)) {
			FileReader filereader = new FileReader(file);
			CSVParser parser;
			if (fileExtension.equalsIgnoreCase("tab")) {
				parser = new CSVParserBuilder().withSeparator('\t').build();
			} else {
				parser = new CSVParserBuilder().withSeparator(extensionMap.get(fileExtension).charAt(0)).build();
			}
			return new CSVReaderBuilder(filereader).withCSVParser(parser).build();
		} else {
			_log.debug("ERROR - Invalid extension");
			FileValidationException.getError(serviceContext.getCompanyId(), serviceContext.getScopeGroupId(), filename,
					userId, uploadedFrom, folderId, "R002", 0, 0, false);
			throw new FileValidationException("Invalid extension");
		}
	}

	static Map<String, List<String>> validateBlockHeaderDat(File file, Map<String, List<Integer>> positionMap, int longestList,
															Integer[][] lengthList, String filename, boolean isZip, long userId, String uploadedFrom, long folderId,
															ServiceContext serviceContext, boolean hasLength, boolean has_headers, List<String> deduped) throws IOException, FileValidationException {
		_log.debug("=============================Into validateBlockHeaderDat");

		String[] lines = null;
		Map<String, List<String>> fileMap = new HashMap<String, List<String>>();

		for (int i = 0; i <= longestList - 1; i++) {
			fileMap.put("List " + i, new ArrayList<String>());
		}

		lines = Files.readAllLines(file.toPath()).toArray(new String[0]);
		String string1 = "";
		boolean isValid = false;
		for (String line : lines) {
			for (Map.Entry<String,List<Integer>> entry : positionMap.entrySet()) {
	            List<Integer> positionList = entry.getValue();
	            for(int i = 0; i <= positionList.size(); i++) {
	            	
	            	if (i == (positionList.size())) {
						string1 = line.substring(positionList.get(i - 1)).trim();
					} else if (i == 0) { 
						string1 = line.substring(0, positionList.get(i)).trim();
						if(string1.equals(entry.getKey())) {
							isValid = true;
						} else {
							isValid = false;
							break;
						}
					} else {
						string1 = line.substring(positionList.get(i - 1), positionList.get(i)).trim();
					}
					
					if (isValid) {
						if (i != 0 && hasLength && i != 0 && !string1.isEmpty()
								&& (string1.length() < lengthList[i - 1][0].intValue()
								|| string1.length() > lengthList[i - 1][1].intValue())) {
							if (isZip) {
								_log.debug("ERROR - Data size not valid");
								FileValidationException.getError(serviceContext.getCompanyId(),
										serviceContext.getScopeGroupId(), filename, userId, uploadedFrom, folderId, "R004Z", 0,
										0, has_headers);
								throw new FileValidationException("Data size not valid");
							} else {
								_log.debug("ERROR - Data size not valid");
								FileValidationException.getError(serviceContext.getCompanyId(),
										serviceContext.getScopeGroupId(), filename, userId, uploadedFrom, folderId, "R004", 0,
										0, has_headers);
								throw new FileValidationException("Data size not valid");
							}
						}

						if (i != 0) {
							fileMap.get("List " + (i - 1)).add(string1);
						}
						
					}
	            }
	            
			}
		}
		
		return fileMap;

	}

	static Map<String, List<String>> dataRecollectionDat(File file, List<Integer> positionList, Integer[][] lengthList,
														 String filename, boolean isZip, long userId, String uploadedFrom, long folderId,
														 ServiceContext serviceContext, boolean hasLength, boolean has_headers) throws IOException, FileValidationException {
		_log.debug("=============================Into dataRecollectionDat");

		String[] lines = null;
		Map<String, List<String>> fileMap = new HashMap<String, List<String>>();

		for (int i = 0; i <= positionList.size() - 2; i++) {
			fileMap.put("List " + i, new ArrayList<String>());
		}
		lines = Files.readAllLines(file.toPath()).toArray(new String[0]);
		int j = 0;
		for (String line : lines) {
			
			for (int i = 0; i <= positionList.size() - 2; i++) {

				String string = line.substring(positionList.get(i), positionList.get(i + 1)).trim();
				if (hasLength && !string.isEmpty() && (string.length() < lengthList[i][0].intValue()
						|| string.length() > lengthList[i][1].intValue())) {
					if (isZip) {
						_log.debug("ERROR - Data size not valid");
						FileValidationException.getError(serviceContext.getCompanyId(),
								serviceContext.getScopeGroupId(), filename, userId, uploadedFrom, folderId, "R004Z", j,
								i, has_headers);
						throw new FileValidationException("Data size not valid");
					} else {
						_log.debug("ERROR - Data size not valid");
						FileValidationException.getError(serviceContext.getCompanyId(),
								serviceContext.getScopeGroupId(), filename, userId, uploadedFrom, folderId, "R004", j,
								i, has_headers);
						throw new FileValidationException("Data size not valid");
					}
				}
				fileMap.get("List " + i).add(string);
				
			}
			j++;
		}
		
		
		return fileMap;
	}

	static Map<String, List<String>> validateBlockHeader(CSVReader csvReader,CSVReader csvReader2, Integer[][] lengthList, int columnsLength,
														 String filename, boolean isZip, long userId, String uploadedFrom, long folderId,
														 ServiceContext serviceContext, boolean hasLength, boolean has_headers, Map<String,JSONArray> blockMap,
														 List<String> blockList) throws FileValidationException, IOException {
		_log.debug("=============================Into validateBlockHeader");

		Map<String, List<String>> fileMap = new HashMap<>();
		String[] nextRecord;
		String blockName;
		int maxLengthRow = csvReader2.readNext().length;

		while((nextRecord = csvReader2.readNext()) != null) {
			if(nextRecord.length > maxLengthRow) {
				maxLengthRow = nextRecord.length;
			}
		}

		int j = 0;
		while ((nextRecord = csvReader.readNext()) != null) {
			blockName = blockList.get(j);
			JSONArray jsonArray = blockMap.get(blockName);
			if (j == 0) {
				for (int i = 0; i <= maxLengthRow - 2; i++) {
					fileMap.put("List " + i, new ArrayList<>());
				}
			}

			for (int i = 0; i <= maxLengthRow - 1; i++) {
				
				if(i == 0 && blockName.equals(nextRecord[i]) && jsonArray.length() < nextRecord.length - 1) {
					if (isZip) {
						_log.debug("ERROR - File delimiter or the amount of columns present may not be correct");
						FileValidationException.getError(serviceContext.getCompanyId(), serviceContext.getScopeGroupId(),
								filename, userId, uploadedFrom, folderId, "R003Z", j + 1, nextRecord.length, has_headers);
						throw new FileValidationException("The file delimiter or the amount of columns present may not be correct");
					} else {
						_log.debug("ERROR - File delimiter may not be correct");
						FileValidationException.getError(serviceContext.getCompanyId(), serviceContext.getScopeGroupId(),
								filename, userId, uploadedFrom, folderId, "R003", j + 1, nextRecord.length, has_headers);
						throw new FileValidationException("The file delimiter or the amount of columns present may not be correct");
					}
				}

				if (i != 0 && hasLength && !nextRecord[i].isEmpty()
						&& (nextRecord[i].length() < lengthList[i][0].intValue()
						|| nextRecord[i].length() > lengthList[i][1].intValue())) {
					if (isZip) {
						_log.debug("ERROR - Data size not valid");
						FileValidationException.getError(serviceContext.getCompanyId(),
								serviceContext.getScopeGroupId(), filename, userId, uploadedFrom, folderId, "R004Z", j,
								i, has_headers);
						throw new FileValidationException("Data size not valid");
					} else {
						_log.debug("ERROR - Data size not valid");
						FileValidationException.getError(serviceContext.getCompanyId(),
								serviceContext.getScopeGroupId(), filename, userId, uploadedFrom, folderId, "R004", j,
								i, has_headers);
						throw new FileValidationException("Data size not valid");
					}
				}
				if (i != 0) {

					if(i >= nextRecord.length ) {
						fileMap.get("List " + (i - 1)).add(StringPool.BLANK);
					} else {
						fileMap.get("List " + (i - 1)).add(nextRecord[i]);
					}

				}
			}
			j++;
			
		}

		return fileMap;

	}

	static Map<String, List<String>> validateHeader(boolean has_header, CSVReader csvReader, List<String> header,
													Integer[][] lengthList, int columnsLength, String filename, boolean isZip, long userId, String uploadedFrom,
													long folderId, ServiceContext serviceContext, boolean hasLength, boolean has_headers)
			throws IOException, FileValidationException {
		_log.debug("=============================Into validateHeader");

		if (has_header) {
			_log.info("##### RECOLLECTION HEADER #####");
			String[] nextRecord = csvReader.readNext();
			List<String> fileHeader = Arrays.asList(nextRecord);

			//Check if headers has empty items
			for (int i = 0; i < fileHeader.size(); i++) {
				if (fileHeader.get(i).equals(StringPool.BLANK)){
					if (isZip) {
						_log.debug("ERROR - The file header may not be correct - empty items");
						FileValidationException.getError(serviceContext.getCompanyId(), serviceContext.getScopeGroupId(),
								filename, userId, uploadedFrom, folderId, "R003Z", 1, i + 1, has_headers);
						throw new FileValidationException("The file header may not be correct - empty items");
					} else {
						_log.debug("ERROR - The file header may not be correct - empty items");
						FileValidationException.getError(serviceContext.getCompanyId(), serviceContext.getScopeGroupId(),
								filename, userId, uploadedFrom, folderId, "R003", 1, i + 1, has_headers);
						throw new FileValidationException("The file header may not be correct - empty items");
					}
				}
			}

			if (nextRecord.length != columnsLength) {
				int columnError = nextRecord.length - 1;
				if (header.size() > fileHeader.size()) {
					for (int index = 0; index < fileHeader.size(); index++) {
						if (!fileHeader.get(index).equals(header.get(index))) {
							columnError = index;
							break;
						}
					}
				}

				if (isZip) {
					_log.debug("ERROR - File delimiter may not be correct");
					FileValidationException.getError(serviceContext.getCompanyId(), serviceContext.getScopeGroupId(),
							filename, userId, uploadedFrom, folderId, "R003Z", 1, columnError + 1, has_headers);
					throw new FileValidationException("The file delimiter may not be correct");
				} else {
					_log.debug("ERROR - File delimiter may not be correct");
					FileValidationException.getError(serviceContext.getCompanyId(), serviceContext.getScopeGroupId(),
							filename, userId, uploadedFrom, folderId, "R003", 1, columnError + 1, has_headers);
					throw new FileValidationException("The file delimiter may not be correct");
				}
			}

			if (fileHeader.equals(header)) {
				return dataRecollectionHeader(nextRecord.length, csvReader, lengthList, filename, isZip, userId,
						uploadedFrom, folderId, serviceContext, hasLength, has_headers);
			} else {

				int columnError = nextRecord.length - 1;
				if (header.size() > fileHeader.size()) {
					for (int i = 0; i < fileHeader.size(); i++) {
						if (!fileHeader.get(i).equals(header.get(i))) {
							columnError = i;
							break;
						}
					}
				}

				if (isZip) {
					_log.debug("ERROR - Invalid header");
					FileValidationException.getError(serviceContext.getCompanyId(), serviceContext.getScopeGroupId(),
							filename, userId, uploadedFrom, folderId, "R003Z", 1, columnError + 1, has_headers);
					throw new FileValidationException("Invalid header");
				} else {
					_log.debug("ERROR - Invalid header");
					FileValidationException.getError(serviceContext.getCompanyId(), serviceContext.getScopeGroupId(),
							filename, userId, uploadedFrom, folderId, "R003", 1, columnError + 1, has_headers);
					throw new FileValidationException("Invalid header");
				}
			}
		} else {
			_log.info("##### RECOLLECTION NO HEADER #####");
			return dataRecollection(csvReader, lengthList, columnsLength, filename, isZip, userId, uploadedFrom,
					folderId, serviceContext, hasLength, has_headers);
		}
	}

	static Map<String, List<String>> dataRecollectionHeader(int headerLength, CSVReader csvReader,
															Integer[][] lengthList, String filename, boolean isZip, long userId, String uploadedFrom, long folderId,
															ServiceContext serviceContext, boolean hasLength, boolean has_headers) throws FileValidationException, IOException {
		_log.debug("=============================Into dataRecollectionHeader");

		Map<String, List<String>> fileMap = new HashMap<>();
		String[] nextRecord;

		for (int i = 0; i <= headerLength - 1; i++) {
			fileMap.put("List " + i, new ArrayList<>());
		}

		int j = 0;
		while ((nextRecord = csvReader.readNext()) != null) {
			for (int i = 0; i <= headerLength - 1; i++) {
				if (hasLength && !nextRecord[i].isEmpty() && (nextRecord[i].length() < lengthList[i][0].intValue()
						|| nextRecord[i].length() > lengthList[i][1].intValue())) {
					if (isZip) {
						_log.debug("ERROR - Data size not valid");
						FileValidationException.getError(serviceContext.getCompanyId(),
								serviceContext.getScopeGroupId(), filename, userId, uploadedFrom, folderId, "R004Z", j,
								i, has_headers);
						throw new FileValidationException("Data size not valid");
					} else {
						_log.debug("ERROR - Data size not valid");
						FileValidationException.getError(serviceContext.getCompanyId(),
								serviceContext.getScopeGroupId(), filename, userId, uploadedFrom, folderId, "R004", j,
								i, has_headers);
						throw new FileValidationException("Data size not valid");
					}
				}
				fileMap.get("List " + i).add(nextRecord[i]);
			}
			j++;
		}

		return fileMap;
	}

	static Map<String, List<String>> dataRecollection(CSVReader csvReader, Integer[][] lengthList, int columnsLength,
													  String filename, boolean isZip, long userId, String uploadedFrom, long folderId,
													  ServiceContext serviceContext, boolean hasLength, boolean has_headers) throws IOException, FileValidationException {
		_log.debug("=============================Into dataRecollection");

		Map<String, List<String>> fileMap = new HashMap<>();
		String[] nextRecord;

		int j = 0;
		while ((nextRecord = csvReader.readNext()) != null) {
			if (nextRecord.length != columnsLength) {

				if (isZip) {
					_log.debug("ERROR - File delimiter may not be correct");
					FileValidationException.getError(serviceContext.getCompanyId(), serviceContext.getScopeGroupId(),
							filename, userId, uploadedFrom, folderId, "R003Z", j + 1, nextRecord.length, has_headers);
					throw new FileValidationException("The file delimiter may not be correct");
				} else {
					_log.debug("ERROR - File delimiter may not be correct");
					FileValidationException.getError(serviceContext.getCompanyId(), serviceContext.getScopeGroupId(),
							filename, userId, uploadedFrom, folderId, "R003", j + 1, nextRecord.length, has_headers);
					throw new FileValidationException("The file delimiter may not be correct");
				}
			}

			if (j == 0) {
				for (int i = 0; i <= nextRecord.length - 1; i++) {
					fileMap.put("List " + i, new ArrayList<>());
				}
			}

			for (int i = 0; i <= nextRecord.length - 1; i++) {
				if (hasLength && !nextRecord[i].isEmpty() && (nextRecord[i].length() < lengthList[i][0].intValue()
						|| nextRecord[i].length() > lengthList[i][1].intValue())) {
					if (isZip) {
						_log.debug("ERROR - Data size not valid");
						FileValidationException.getError(serviceContext.getCompanyId(),
								serviceContext.getScopeGroupId(), filename, userId, uploadedFrom, folderId, "R004Z", j,
								i, has_headers);
						throw new FileValidationException("Data size not valid");
					} else {
						_log.debug("ERROR - Data size not valid");
						FileValidationException.getError(serviceContext.getCompanyId(),
								serviceContext.getScopeGroupId(), filename, userId, uploadedFrom, folderId, "R004", j,
								i, has_headers);
						throw new FileValidationException("Data size not valid");
					}
				}
				fileMap.get("List " + i).add(nextRecord[i]);
			}
			j++;
		}
		return fileMap;
	}

	static void validateDataType(List<String> valuesList, JSONObject conditional, boolean isZip, List<String> header,
								 Map<String, List<String>> valuesMap, String mandatory, int j, String format, String type, String filename,
								 long userId, String uploadedFrom, long folderId, ServiceContext serviceContext, boolean has_headers, boolean has_blocks, String blockName, List<String> blockList, int blockRow)
			throws FileValidationException {
		_log.debug("=============================Into validateDataType");
		if (mandatory.equalsIgnoreCase("C")) {
			_log.info("#### CUSTOM CONDITIONAL #####");
			validateConditional(j, conditional, header, valuesMap, filename, isZip, userId, uploadedFrom, folderId,
					serviceContext, has_headers, valuesList, has_blocks, format, type, blockName, blockList, blockRow);
		} else {
			typeOfHeaderDataValueValidation(valuesMap.get("List " + j), blockName, blockList, valuesList, filename, isZip, j, blockRow, userId, uploadedFrom, folderId, serviceContext, has_headers, has_blocks, format, type, mandatory, null);
		}
	}

	static void validateConditional(int j, JSONObject conditional, List<String> header,
									Map<String, List<String>> valuesMap, String filename, boolean isZip, long userId, String uploadedFrom,
									long folderId, ServiceContext serviceContext, boolean has_headers, List<String> valuesList, boolean has_blocks, String formatDate, String type, String blockName, List<String> blockList, int blockRow) throws FileValidationException {
		_log.debug("=============================Into validateConditional");

		String needsMValidation = FileValidationConstants.CUSTOM_OPTIONAL;

		String operation = conditional.getString("operation");
		String columnName = conditional.getString("columnName");
		String[] operationArray = operation.split("\\s+");
		String value = conditional.getString("value");
		List<String> currentColumnList = valuesMap.get("List " + j);
		int index = header.indexOf(columnName);
		List<String> columnNameList = valuesMap.get("List " + index);
		String[] criteriaArray = criteriaToArray(operationArray[1]);
		List<String> criteriaList =ListUtil.fromArray(criteriaArray);
		int i = 0;

		boolean emptyList = true;
		List<String> currentColumnListCopy = new ArrayList<String>();
		currentColumnListCopy.addAll(currentColumnList);
		
		switch (operationArray[0]) {
			case "==":
				for (String stringIndex : columnNameList) {
						if (criteriaList.contains(stringIndex)) {
							needsMValidation = conditionalValidation(operationArray[3], currentColumnList, i, value, j, filename, isZip, userId,
									uploadedFrom, folderId, serviceContext, has_headers, i);
						}
						
						if(needsMValidation.equals(FileValidationConstants.OPTIONAL_VALIDATION)) {
							currentColumnListCopy.set(i, "");
						}
					i++;
				}
				break;
			case "!=":
				for (String stringIndex : columnNameList) {
					if (!criteriaList.contains(stringIndex)) {
						needsMValidation = conditionalValidation(operationArray[3], currentColumnList, i, value, j, filename, isZip, userId,
								uploadedFrom, folderId, serviceContext, has_headers, i);
					}
					
					if(needsMValidation.equals(FileValidationConstants.OPTIONAL_VALIDATION)) {
						currentColumnListCopy.set(i, "");
					}
				i++;
			}
				break;
			case ">":
				for (String stringIndex : columnNameList) {
					double valueOperationConditional = Double.parseDouble(stringIndex);
					for(String criteria: criteriaList) {
						double valueCriteriaConditional = Double.parseDouble(criteria);
						if (valueOperationConditional > valueCriteriaConditional) {
							needsMValidation = conditionalValidation(operationArray[3], currentColumnList, i, value, j, filename, isZip, userId,
									uploadedFrom, folderId, serviceContext, has_headers, i);
						}
						
						if(needsMValidation.equals(FileValidationConstants.OPTIONAL_VALIDATION)) {
							currentColumnListCopy.set(i, "");
							break;
						}
						
						if(needsMValidation.equals(FileValidationConstants.CUSTOM_OPTIONAL)) {
							break;
						}
					}
					i++;
				}
				break;
			case ">=":
				for (String stringIndex : columnNameList) {
					double valueOperationConditional = Double.parseDouble(stringIndex);
					for(String criteria: criteriaList) {
						double valueCriteriaConditional = Double.parseDouble(criteria);
						if (valueOperationConditional >= valueCriteriaConditional) {
							needsMValidation = conditionalValidation(operationArray[3], currentColumnList, i, value, j, filename, isZip, userId,
									uploadedFrom, folderId, serviceContext, has_headers, i);
						}
						
						if(needsMValidation.equals(FileValidationConstants.OPTIONAL_VALIDATION)) {
							currentColumnListCopy.set(i, "");
							break;
						}
						
						if(needsMValidation.equals(FileValidationConstants.CUSTOM_OPTIONAL)) {
							break;
						}
					}
					i++;
				}
				break;
			case "<":
				for (String stringIndex : columnNameList) {
					double valueOperationConditional = Double.parseDouble(stringIndex);
					for(String criteria: criteriaList) {
						double valueCriteriaConditional = Double.parseDouble(criteria);
						if (valueOperationConditional < valueCriteriaConditional) {
							needsMValidation = conditionalValidation(operationArray[3], currentColumnList, i, value, j, filename, isZip, userId,
									uploadedFrom, folderId, serviceContext, has_headers, i);
						}
						
						if(needsMValidation.equals(FileValidationConstants.OPTIONAL_VALIDATION)) {
							currentColumnListCopy.set(i, "");
							break;
						}
						
						if(needsMValidation.equals(FileValidationConstants.CUSTOM_OPTIONAL)) {
							break;
						}
					}
					i++;
				}
				break;
			case "<=":
				for (String stringIndex : columnNameList) {
					double valueOperationConditional = Double.parseDouble(stringIndex);
					for(String criteria: criteriaList) {
						double valueCriteriaConditional = Double.parseDouble(criteria);
						if (valueOperationConditional <= valueCriteriaConditional) {
							needsMValidation = conditionalValidation(operationArray[3], currentColumnList, i, value, j, filename, isZip, userId,
									uploadedFrom, folderId, serviceContext, has_headers, i);
						}
						
						if(needsMValidation.equals(FileValidationConstants.OPTIONAL_VALIDATION)) {
							currentColumnListCopy.set(i, "");
							break;
						}
						
						if(needsMValidation.equals(FileValidationConstants.CUSTOM_OPTIONAL)) {
							break;
						}
					}
					i++;
				}
				break;
			default:
				break;
		}
		for (String val : currentColumnListCopy) {
			if(!val.isEmpty()) {
				emptyList = false;
			}
		}
		
		if(needsMValidation.equals(FileValidationConstants.CUSTOM_OPTIONAL)) {
			needsMValidation = FileValidationConstants.OPTIONAL_VALIDATION;
		}
		
		if(!needsMValidation.equals(FileValidationConstants.INVALID_VALIDATION) && !emptyList) {
			typeOfHeaderDataValueValidation(currentColumnListCopy, blockName, blockList, valuesList, filename, isZip, j, blockRow, userId, uploadedFrom, folderId, serviceContext, has_headers, has_blocks, formatDate, type, needsMValidation, null);
		}
	}

	static String conditionalValidation(String operation, List<String> currentColumnList, int i, String valueConditional, int j,
									  String filename, boolean isZip, long userId, String uploadedFrom, long folderId,
									  ServiceContext serviceContext, boolean has_headers, int rowError) throws FileValidationException {
		_log.debug("=============================Into conditionalValidation");

		String needsMValidation = FileValidationConstants.CUSTOM_OPTIONAL;

		if (operation.equals("VALUE")) {
			_log.info("VALIDATION VALUE");
			String stringValue = currentColumnList.get(i);
				if (stringValue.equals(valueConditional)) {
					needsMValidation = FileValidationConstants.OPTIONAL_VALIDATION;
				}
				
		} else if (operation.equals("EMPTY")) {
			_log.info("VALIDATION EMPTY");
			String stringValue = currentColumnList.get(i);
				if (!stringValue.isEmpty()) {
					if (isZip) {
						_log.debug("ERROR - Failed in == validation OPERATION EMPTY");
						FileValidationException.getError(serviceContext.getCompanyId(),
								serviceContext.getScopeGroupId(), filename, userId, uploadedFrom, folderId, "R004Z", rowError,
								j, has_headers);
						throw new FileValidationException("Failed in == validation OPERATION EMPTY");
					} else {
						_log.debug("ERROR - Failed in == validation OPERATION EMPTY");
						FileValidationException.getError(serviceContext.getCompanyId(),
								serviceContext.getScopeGroupId(), filename, userId, uploadedFrom, folderId, "R004", rowError,
								j, has_headers);
						throw new FileValidationException("Failed in == validation OPERATION EMPTY");
					}
					
				}
				needsMValidation = FileValidationConstants.INVALID_VALIDATION;
		} else if (operation.equals("NOTEMPTY")) {
			_log.info("VALIDATION NOTEMPTY");
			String stringValue = currentColumnList.get(i);
			_log.info("NOT EMPTY CUSTOM" + stringValue + " ||| INDEX: " +i + " ||| ROWERROR" + rowError );
				if (stringValue.isEmpty()) {
					if (isZip) {
						_log.debug("ERROR - Failed in == validation OPERATION EMPTY");
						FileValidationException.getError(serviceContext.getCompanyId(),
								serviceContext.getScopeGroupId(), filename, userId, uploadedFrom, folderId, "R004Z", rowError,
								j, has_headers);
						throw new FileValidationException("Failed in == validation OPERATION NOTEMPTY");
					} else {
						_log.debug("ERROR - Failed in == validation OPERATION NOTEMPTY");
						FileValidationException.getError(serviceContext.getCompanyId(),
								serviceContext.getScopeGroupId(), filename, userId, uploadedFrom, folderId, "R004", rowError,
								j, has_headers);
						throw new FileValidationException("Failed in == validation OPERATION NOTEMPTY");
					}
				}
				
		}
		return needsMValidation;
	}

	static boolean validateText(String text) {
		_log.debug("=============================Into validateText");
		return true;
	}

	static boolean validateDate(String strDate, String customFormat) {
		_log.debug("=============================Into validateDate");

		SimpleDateFormat format;
		if (customFormat.isEmpty()) {
			format = new SimpleDateFormat("dd/MM/yyyy");
		} else {
			format = new SimpleDateFormat(customFormat);
		}

		format.setLenient(false);

		try {
			format.parse(strDate);
		} catch (ParseException e) {
			_log.error("ERROR - Parsing date", e);
			return false;
		}
		return true;
	}

	static boolean validateJsonParam(JSONObject data) {
		try {
			if (!data.getJSONObject("conditional").getString("operation").isEmpty()) {
				return true;
			}
			return false;
		} catch (NullPointerException e) {
			return false;
		}
	}

	static boolean validateNumeric(String text) {
		try {
			Double.parseDouble(text);
		} catch (NumberFormatException e) {
			_log.error("ERROR - Parsing double", e);
			return false;
		}
		return true;
	}

	static boolean validateInteger(String text) {
		try {
			Integer.parseInt(text);
		} catch (NumberFormatException e) {
			_log.error("ERROR - Parsing integer", e);
			return false;
		}
		return true;
	}

	static boolean validateChar(String text) {
		if (text.length() > 1) {
			return false;
		}

		if (validateText(text)) {
			return true;
		}

		return true;
	}
	
	static String[] criteriaToArray(String criteria) {
		if(criteria.contains("[") && criteria.contains("]")) {
			criteria = criteria.replaceAll("\\[", "").replaceAll("\\]","");
			String[] criteriasArray = criteria.split(",");
			
			return criteriasArray;
		} else {
			String[] criteriasArray = {criteria};
			
			return criteriasArray;
		}
		
	}

	// **********************************************************

	static void validateBlockDataType(List<String> valuesList, String blockName, List<String> blockList, boolean isZip,
			List<String> header, Map<String, List<String>> valuesMap, String mandatory, int j, String format,
			String type, String filename, long userId, String uploadedFrom, long folderId,
			ServiceContext serviceContext, boolean has_headers, int blockRow, JSONArray jsonArray, boolean has_blocks, Map<String, List<Integer>> positionMap)
			throws FileValidationException {
		_log.debug("=============================Into validateDataType");
		if (mandatory.equalsIgnoreCase("C")) {
			_log.info("#### CUSTOM CONDITIONAL #####");
			validateBlockConditional(blockName, blockList, valuesMap, j, filename, isZip, userId, uploadedFrom,
					folderId, serviceContext, has_headers, blockRow, jsonArray, valuesList, has_blocks, format, type, positionMap);
		} else {
			typeOfHeaderDataValueValidation(valuesMap.get("List " + j), blockName, blockList, valuesList, filename, isZip, j, blockRow, userId, uploadedFrom, folderId, serviceContext, has_headers, has_blocks, format, type, mandatory, positionMap);
		}
	}

	static void validateBlockConditional(String blockName, List<String> blockList, Map<String, List<String>> valuesMap,
			int column, String filename, boolean isZip, long userId, String uploadedFrom, long folderId,
			ServiceContext serviceContext, boolean has_headers, int row, JSONArray jsonArray, List<String> valuesList,
			boolean has_blocks, String formatDate, String type, Map<String, List<Integer>> positionMap)
			throws FileValidationException {
		_log.debug("=============================Into validateConditional");

		JSONObject object = jsonArray.getJSONObject(column);
		JSONObject data = object.getJSONObject("data");
		JSONObject conditional = data.getJSONObject("conditional");
		String operation = conditional.getString("operation");
		String columnName = conditional.getString("columnName");
		String[] operationArray = operation.split("\\s+");
		String value = conditional.getString("value");
		List<String> currentColumnList = valuesMap.get("List " + column);
		List<String> columnNameListCopy = new ArrayList<>();
		List<String> currentColumnListCopy = new ArrayList<>();
		int index = -1;

		for (int i = 0; i < jsonArray.length(); i++) {
			String blockNameItr = jsonArray.getJSONObject(i).getString("blockName");
			if (blockNameItr.equals(blockName)) {
				String nameItr = jsonArray.getJSONObject(i).getString("name");
				if (nameItr.equals(columnName)) {
					index = i;
				}
			}
		}

		List<String> columnNameList = valuesMap.get("List " + index);
		columnNameListCopy.addAll(columnNameList);
		currentColumnListCopy.addAll(currentColumnList);

		_log.debug("################## STRINGLIST CUSTOM : " + currentColumnList.toString());
		_log.debug("################## STRINGLIST CUSTOM TO COMPARE : " + columnNameList.toString());
		switchCustomValidation(blockName, blockList, operationArray, currentColumnListCopy, columnNameListCopy, index, value,
				filename, isZip, column, row, userId, uploadedFrom, folderId, serviceContext, has_headers, valuesList, has_blocks, formatDate, type, positionMap);

	}

	
	static void switchCustomValidation(String blockName, List<String> blockList, String[] operationArray,
			List<String> currentColumnList, List<String> columnNameList, int index, String value, String filename,
			boolean isZip, int column, int row, long userId, String uploadedFrom, long folderId,
			ServiceContext serviceContext, boolean has_headers, List<String> valuesList, boolean has_blocks, String formatDate, String type, Map<String,List<Integer>> positionMap) throws FileValidationException {

		List<String> currentColumnListCopy = new ArrayList<String>();
		currentColumnListCopy.addAll(currentColumnList);
		String needsMValidation = FileValidationConstants.CUSTOM_OPTIONAL;
		int i = 0;
		int rowError = 0;
		boolean emptyList = true;
		String[] criteriaArray = criteriaToArray(operationArray[1]);
		List<String> criteriaList =ListUtil.fromArray(criteriaArray);
		
		if(!(positionMap == null) && !positionMap.isEmpty()) {
			for(int j = 0; j < blockList.size(); j++) {
				if(positionMap.get(blockList.get(j)).size() <= column) {
					currentColumnList.add(j, "");
					currentColumnListCopy.add(j,"");
					columnNameList.add(j,"");
				}
			}
		}
		
		switch (operationArray[0]) {
		case "==":
			for(String blockNameFromList : blockList) {
				_log.debug("################## BLOCKNAME: " + blockNameFromList + " INDEX: " + i + " BlOCKNAME TO COMPARE: " + blockName);
				if(blockNameFromList.equalsIgnoreCase(blockName)) {
					if (criteriaList.contains(columnNameList.get(i))) {
						needsMValidation =  conditionalValidation(operationArray[3], currentColumnList, i, value, column, filename, isZip,
								userId, uploadedFrom, folderId, serviceContext, has_headers, i);
					}
					
					if(needsMValidation.equals(FileValidationConstants.OPTIONAL_VALIDATION)) {
						currentColumnListCopy.set(i, "");
					}
				}
				
				i++;
			}
			break;
		case "!=":
			for(String blockNameFromList : blockList) {
				_log.debug("################## BLOCKNAME: " + blockNameFromList + " INDEX: " + i + " BlOCKNAME TO COMPARE: " + blockName);
				if(blockNameFromList.equalsIgnoreCase(blockName)) {
					if (!criteriaList.contains(columnNameList.get(i))) {
						needsMValidation = conditionalValidation(operationArray[3], currentColumnList, i, value, column, filename, isZip,
								userId, uploadedFrom, folderId, serviceContext, has_headers, i);
						
					}
					
					if(needsMValidation.equals(FileValidationConstants.OPTIONAL_VALIDATION)) {
						currentColumnListCopy.set(i, "");
					}
				}
				
				i++;
			}
			break;
		case ">":
			for(String blockNameFromList : blockList) {
				_log.debug("################## BLOCKNAME: " + blockNameFromList + " INDEX: " + i + " BlOCKNAME TO COMPARE: " + blockName);
				if(blockNameFromList.equalsIgnoreCase(blockName)) {
						double valueOperationConditional = Double.parseDouble(columnNameList.get(i));
						
						for(String criteriaValue: criteriaList) {
							double valueCriteriaConditional = Double.parseDouble(criteriaValue);
							
							if (valueOperationConditional > valueCriteriaConditional) {
								needsMValidation = conditionalValidation(operationArray[3], currentColumnList, i, value, column, filename, isZip,
										userId, uploadedFrom, folderId, serviceContext, has_headers, rowError);
								
							}
							
							if(needsMValidation.equals(FileValidationConstants.OPTIONAL_VALIDATION)) {
								currentColumnListCopy.set(i, "");
								break;
							}
							
							if(needsMValidation.equals(FileValidationConstants.CUSTOM_OPTIONAL)) {
								break;
							}
						}
				}
				i++;
			}
			break;
		case ">=":
			for(String blockNameFromList : blockList) {
				_log.debug("################## BLOCKNAME: " + blockNameFromList + " INDEX: " + i + " BlOCKNAME TO COMPARE: " + blockName);
				if(blockNameFromList.equalsIgnoreCase(blockName)) {
						double valueOperationConditional = Double.parseDouble(columnNameList.get(i));
						
						for(String criteriaValue: criteriaList) {
							double valueCriteriaConditional = Double.parseDouble(criteriaValue);
							
							if (valueOperationConditional >= valueCriteriaConditional) {
								needsMValidation = conditionalValidation(operationArray[3], currentColumnList, i, value, column, filename, isZip,
										userId, uploadedFrom, folderId, serviceContext, has_headers, rowError);
								
							} 
							
							if(needsMValidation.equals(FileValidationConstants.OPTIONAL_VALIDATION)) {
								currentColumnListCopy.set(i, "");
								break;
							}
							
							if(needsMValidation.equals(FileValidationConstants.CUSTOM_OPTIONAL)) {
								break;
							}
						}
				}
				i++;
			}
			break;
		case "<":
			for(String blockNameFromList : blockList) {
				_log.debug("################## BLOCKNAME: " + blockNameFromList + " INDEX: " + i + " BlOCKNAME TO COMPARE: " + blockName);
				if(blockNameFromList.equalsIgnoreCase(blockName)) {
						double valueOperationConditional = Double.parseDouble(columnNameList.get(i));
						
						for(String criteriaValue: criteriaList) {
							double valueCriteriaConditional = Double.parseDouble(criteriaValue);
							
							if (valueOperationConditional < valueCriteriaConditional) {
								needsMValidation = conditionalValidation(operationArray[3], currentColumnList, i, value, column, filename, isZip,
										userId, uploadedFrom, folderId, serviceContext, has_headers, rowError);
								
							} 
							
							if(needsMValidation.equals(FileValidationConstants.OPTIONAL_VALIDATION)) {
								currentColumnListCopy.set(i, "");
								break;
							}
							
							if(needsMValidation.equals(FileValidationConstants.CUSTOM_OPTIONAL)) {
								break;
							}
						}
				}
				i++;
			}
			break;
		case "<=":
			for(String blockNameFromList : blockList) {
				_log.debug("################## BLOCKNAME: " + blockNameFromList + " INDEX: " + i + " BlOCKNAME TO COMPARE: " + blockName);
				if(blockNameFromList.equalsIgnoreCase(blockName)) {
						double valueOperationConditional = Double.parseDouble(columnNameList.get(i));
						
						for(String criteriaValue: criteriaList) {
							double valueCriteriaConditional = Double.parseDouble(criteriaValue);
							
							if (valueOperationConditional <= valueCriteriaConditional) {
								needsMValidation = conditionalValidation(operationArray[3], currentColumnList, i, value, column, filename, isZip,
										userId, uploadedFrom, folderId, serviceContext, has_headers, rowError);
								
							} 
							
							if(needsMValidation.equals(FileValidationConstants.OPTIONAL_VALIDATION)) {
								currentColumnListCopy.set(i, "");
								break;
							}
							
							if(needsMValidation.equals(FileValidationConstants.CUSTOM_OPTIONAL)) {
								break;
							}
						}
				}
				i++;
			}
			break;
		default:
			break;
		}
		
		for (String val : currentColumnListCopy) {
			if(!val.isEmpty()) {
				emptyList = false;
			}
		}
		
		
		if(needsMValidation.equals(FileValidationConstants.CUSTOM_OPTIONAL)) {
			needsMValidation = FileValidationConstants.OPTIONAL_VALIDATION;
		}
		
		if(!needsMValidation.equals(FileValidationConstants.INVALID_VALIDATION) && !emptyList) {
			typeOfHeaderDataValueValidation(currentColumnListCopy, blockName, blockList, valuesList, filename, isZip, column, row, userId, uploadedFrom, folderId, serviceContext, has_headers, has_blocks, formatDate, type, needsMValidation, positionMap);
		}
	}

	static void throwError(String filename, boolean isZip, int column, int row,
						   long userId, String uploadedFrom, long folderId, ServiceContext serviceContext, boolean has_headers, String errorLevel, String error) throws FileValidationException {
		String zipValue = "";

		if(isZip) {
			zipValue = "Z";
		}
		_log.debug("ERROR - " + error);
		FileValidationException.getError(serviceContext.getCompanyId(),
				serviceContext.getScopeGroupId(), filename, userId, uploadedFrom, folderId, "R00" + errorLevel + zipValue,
				row, column, has_headers);
		throw new FileValidationException(error);
	}

	static void typeOfHeaderDataValueValidation(List<String> stringList, String blockName,
												List<String> blockList, List<String> valuesString, String filename, boolean isZip, int column, int row,
												long userId, String uploadedFrom, long folderId, ServiceContext serviceContext, boolean has_headers,
												boolean has_blocks, String formatDate, String type, String mandatory, Map<String, List<Integer>> positionMap)  throws FileValidationException {
		boolean isFixedValues = false;
		if(!valuesString.isEmpty()) {
			isFixedValues = true;
		}
		String error = "Error Data Value Validation - typeOfHeaderDataValueValidation()";
		if(has_blocks && (positionMap == null || positionMap.isEmpty())) {
			for (int i = 0; i < stringList.size(); i++) {
				if (blockList.get(i).equalsIgnoreCase(blockName)) {
					_log.debug("################## BLOCKNAME "+blockName + " ||| STRINGLIST: "+stringList +" ##################");
					if(!typeOfValueValidation(valuesString, stringList.get(i), isFixedValues, formatDate, type, mandatory)) {
						throwError(filename, isZip, column, i, userId, uploadedFrom, folderId, serviceContext, has_headers, "4", error);
					}
				}
				row++;
			}
		} else if(has_blocks){
			List<String> currentColumnListCopy = new ArrayList<>();
			currentColumnListCopy.addAll(stringList);
			if(stringList.size() < blockList.size()) {
				for(int j = 0; j < blockList.size(); j++) {
					if(positionMap.get(blockList.get(j)).size() <= column) {
						currentColumnListCopy.add(j,"");
					}
				}
			}
			
			for (int i = 0; i < currentColumnListCopy.size(); i++) {
				if (blockList.get(i).equalsIgnoreCase(blockName)) {
					_log.debug("################## DAT BLOCKNAME "+blockName + " ||| STRINGLIST: "+stringList +" ##################");
					if(!typeOfValueValidation(valuesString, currentColumnListCopy.get(i), isFixedValues, formatDate, type, mandatory)) {
						throwError(filename, isZip, column, i, userId, uploadedFrom, folderId, serviceContext, has_headers, "4", error);
					}
				}
				row++;
			}

		} else {
			_log.debug("=============================Into valuesValidation");
			for (int i = 0; i < stringList.size(); i++) {
				if(!typeOfValueValidation(valuesString, stringList.get(i), isFixedValues, formatDate, type, mandatory)) {
					throwError(filename, isZip, column, row, userId, uploadedFrom, folderId, serviceContext, has_headers, "4", error);
				}
				row++;
			}
		}
	}
	
	static boolean typeOfValueValidation(List<String> valuesString,String valueToValidate, boolean isFixedValues, String formatDate, String type, String mandatoryValue) {
		String mandatory = MandatoryValidation(mandatoryValue,valueToValidate);
		_log.debug("################## "+ "TYPE: " +type+ " ||| MANDATORY: "+mandatory+" ||| VALUE: " +valueToValidate +"##################");
		switch(mandatory) {
			case FileValidationConstants.MANDATORY_VALIDATION:
				if(isFixedValues) {
					_log.info("=============================Into Mandatory Fixed Values Validation");
					if(!valuesString.contains(valueToValidate)) {
						return false;
					}
				} else {
					_log.info("=============================Into Mandatory Validation");
					if(!DataTypeValidation(type, valueToValidate, formatDate)) {
						return false;
					}
				}

				return true;
			case FileValidationConstants.OPTIONAL_VALIDATION:
				if(isFixedValues) {
					_log.info("=============================Into Optional Fixed Values Validation");
					if(!valueToValidate.isEmpty() && !valuesString.contains(valueToValidate)) {
						return false;
					}
				} else {
					_log.info("=============================Into Optional Validation");
					if(!valueToValidate.isEmpty() && !DataTypeValidation(type, valueToValidate, formatDate)) {
						return false;
					}
				}

				return true;
			default:
				return false;
		}		
	}
	
	static String MandatoryValidation(String mandatory, String valueToValidate) {
		switch(mandatory) {
			case "M":
				_log.info("#### MANDATORY CONDITIONAL #####");
				if (valueToValidate.isEmpty()) {
					return FileValidationConstants.INVALID_VALIDATION;
				}
				return FileValidationConstants.MANDATORY_VALIDATION;

			case "O":
				_log.info("#### OPTIONAL CONDITIONAL #####");
				return FileValidationConstants.OPTIONAL_VALIDATION;

			default:
				return FileValidationConstants.INVALID_VALIDATION;
		}
	}

	static boolean DataTypeValidation(String type, String valueToValidate, String formatDate) {
		_log.debug("=============================Into TypeValidation");
		switch(type) {
			case "text":
				_log.debug("=============================Into textValidation");
				return validateText(valueToValidate);
			case "date":
				_log.debug("=============================Into dateValidation");
				return validateDate(valueToValidate, formatDate);
			case "numeric":
				_log.debug("=============================Into numericValidation");
				return validateNumeric(valueToValidate);
			case "int":
				_log.debug("=============================Into intValidation");
				return validateInteger(valueToValidate);
			case "char":
				_log.debug("=============================Into charValidation");
				return validateChar(valueToValidate);
			default:
				return false;
		}

	}
}
