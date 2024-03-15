package com.everis.cproposal.util;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFileEntryMetadata;
import com.liferay.document.library.kernel.model.DLFileEntryType;
import com.liferay.document.library.kernel.model.DLFileVersion;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.model.DLVersionNumberIncrease;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryMetadataLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryTypeLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.dynamic.data.mapping.kernel.DDMForm;
import com.liferay.dynamic.data.mapping.kernel.DDMFormValues;
import com.liferay.dynamic.data.mapping.kernel.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMContent;
import com.liferay.dynamic.data.mapping.service.DDMContentLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.PwdGenerator;
import com.liferay.portal.kernel.util.Validator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import rec.file.validation.util.api.FileValidationUtil;

public class RPAUtil {
	private static final Log logger = LogFactoryUtil.getLog(RPAUtil.class);

	// DOCUMENT TYPE
	public static final String ANNUAL_MAINTENANCE = "Annual Maintenance";

	public static final String CODE_MANAGER_UPLOADS = "Code Manager Uploads";

	public static final String ENERGY_THEFT = "Energy Theft";

	public static final String GREEN_DEALS = "Green Deals";

	public static final String MARKET_ENTRY = "Market Entry";

	public static final String METERING = "Metering";

	public static final String PERFORMANCEASSURANCE_DATAUPLOAD = "Performance Assurance - Data Upload";

	public static final String SERVICE_PROVIDERS = "Service Providers";

	public static final String SMARTMETERING_INSTALLATIONINFORMATION = "Smart Metering Installation Information";

	public static final String OTHER = "Other";

	// EXTRA METADATA VALUES
	public static final String RPA_FORM_FIELDTYPE = "RPA_Form_FieldType";

	public static final String RPA_FORM_DATEUPLOADED = "RPA_Form_DateUploaded";

	public static final String RPA_FORM_PARTYUPLOADING = "RPA_Form_PartyUploading";

	// DATABASE VARIABLE NAMES

	public static final String FILEENTRYTYPEID = "fileEntryTypeId";

	public static final String ADMIN_EMAILADDRESS = "test@liferay.com";

	// FOLDER NAME
	private static final String RPA_FOLDER_NAME = "RPA Documents";

	/**
	 * Method to upload a document dependns on its type and adding extra metadata
	 * fields
	 *
	 * @param userId
	 * @param repositoryId
	 * @param folderId
	 * @param fileBytes
	 * @param mimeType
	 * @param title
	 * @param processArea
	 * @param description
	 * @param fieldType
	 * @param date
	 * @param partyUploading
	 * @return JSONResponse (code and message)
	 */
	public static JSONObject uploadYourFiles(long userId, long repositoryId, long folderId, byte[] fileBytes,
			String mimeType, String title, String processArea, String description, String fieldType, String date,
			String partyUploading, ServiceContext serviceContext) {
		JSONObject response = JSONFactoryUtil.createJSONObject();
		boolean validateFile = true;
		int code = 200;
		String message = "Your file " + title + " has been uploaded";
		String[] processAreasToExclude = { "CodeManagerUploads", "GreenDeals", "Metering" };
		boolean noValidation = Arrays.stream(processAreasToExclude).anyMatch(processArea::equals);

		folderId = getOrCreateFolder(serviceContext, userId, repositoryId, RPA_FOLDER_NAME);

		if (noValidation) {
			String processAreaMapped = mapProcessArea(processArea);
			long dlFileEntryTypeId = getDocumentType(processAreaMapped, repositoryId);
			if (dlFileEntryTypeId == 0) {
				logger.info("There is no document type for processArea: " + processAreaMapped);
				response.put("code", 500);
				response.put("message", "There is no document type for process area : " + processAreaMapped);
				return response;
			}

			ServiceContext sc = createServiceContextForFileEntry(dlFileEntryTypeId);
			try {
				uploadYourFilesFileEntry(title, repositoryId, folderId, dlFileEntryTypeId, userId, fileBytes, mimeType,
						description, fieldType, date, partyUploading, sc);
			} catch (PortalException | ParseException e) {
				logger.error("Error adding file 01: " + title);
				logger.error("Error " + e.getMessage());
				code = 500;
				if (e.getMessage().contains("exists")) {
					message = "A file entry already exists with the same name but with a different process area, filetype or period (date).";
				} else {
					message = e.getMessage();
				}
			}

		} else {
			try {
				File uploadedFile = FileUtil.createTempFile(fileBytes);
				response = FileValidationUtil.importFile(title, uploadedFile, fieldType, serviceContext,
						"UPLOAD-YOUR-FILES", folderId, userId);
				if (response.getInt("code") == 500) {
					return response;
				}
			} catch (IOException | JSONException e) {
				logger.error("Error validating file", e);
				response.put("code", 500);
				response.put("message", "File validation error in " + title + ", please contact support team");
				return response;
			}

			validateFile = validateFile(title, fileBytes);
			if (validateFile) {
				String processAreaMapped = mapProcessArea(processArea);
				long dlFileEntryTypeId = getDocumentType(processAreaMapped, repositoryId);
				if (dlFileEntryTypeId == 0) {
					logger.info("There is no document type for processArea: " + processAreaMapped);
					response.put("code", 500);
					response.put("message", "There is no document type for process area : " + processAreaMapped);
					return response;
				}

				ServiceContext sc = createServiceContextForFileEntry(dlFileEntryTypeId);

				try {
					if (FileUtil.getExtension(title).equals("zip")) {
						try {
							
							byte[] fileByteCopy = Arrays.copyOf(fileBytes, fileBytes.length);
							
							
							File tempFile = FileUtil.createTempFile(fileBytes);
							File tempFileCopy = FileUtil.createTempFile(fileByteCopy);
							
							ZipFile zipFile = new ZipFile(tempFile);
							ZipFile zipFileCopy = new ZipFile(tempFileCopy);

							Enumeration<? extends ZipEntry> entries = zipFile.entries();
							Enumeration<? extends ZipEntry> entriesCopy = zipFileCopy.entries();
							
							
							while (entriesCopy.hasMoreElements()) {
								ZipEntry entry = entriesCopy.nextElement();
								String entryName = entry.getName();
								logger.debug("File Name: " + entryName);
								
								DLFileEntry fileFetched = DLFileEntryLocalServiceUtil.fetchFileEntry(repositoryId, folderId, entryName);
								
								if(fileFetched != null) {
									throw new PortalException("File already exists, rejecting all files from the zip.");
								}
							}
							zipFileCopy.close();
							
							while (entries.hasMoreElements()) {
								ZipEntry entry = entries.nextElement();
								String entryName = entry.getName();
								InputStream entryInputStream = zipFile.getInputStream(entry);
								byte[] entryBytes = IOUtils.toByteArray(entryInputStream);

								uploadYourFilesFileEntry(entryName, repositoryId, folderId, dlFileEntryTypeId, userId,
										entryBytes, mimeType, description, fieldType, date, partyUploading, sc);
							}
							zipFile.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						uploadYourFilesFileEntry(title, repositoryId, folderId, dlFileEntryTypeId, userId, fileBytes,
								mimeType, description, fieldType, date, partyUploading, sc);
					}

				} catch (PortalException | ParseException e) {
					logger.error("Error adding file 02: " + title);
					logger.error("Error " + e.getMessage());
					code = 500;
					if (e.getMessage().contains("exists")) {
						message = "A file entry already exists with the same name but with a different process area, filetype or period (date).";
					} else {
						message = e.getMessage();
					}
				}
			} else {
				logger.info("Error adding file 03: " + title);
				logger.info("Error Format of the files is no valid with it is content");
				code = 500;
				message = "The format specified for the file does not match the file content. ";
			}
		}
		response.put("code", code);
		response.put("message", message);
		return response;
	}

	public static void uploadYourFilesFileEntry(String title, long repositoryId, long folderId, long dlFileEntryTypeId,
			long userId, byte[] fileBytes, String mimeType, String description, String fieldType, String date,
			String partyUploading, ServiceContext serviceContext) throws PortalException, ParseException {
		String sourceFileName = title.trim();
		FileEntry fileEntry;
		FileEntry fileEntrySearch = null;
		try {
			fileEntrySearch = DLAppLocalServiceUtil.getFileEntry(repositoryId, folderId, title);
		} catch (PortalException e) {
			logger.info("No file existing with name : " + title);
		}
		if (Validator.isNotNull(fileEntrySearch)) {
			DLFileEntry dLFileEntry = DLFileEntryLocalServiceUtil.getDLFileEntry(fileEntrySearch.getFileEntryId());
			long dlFileEntryTypeIdSearch = dLFileEntry.getFileEntryTypeId();
			if (dlFileEntryTypeIdSearch != dlFileEntryTypeId) {
				fileEntrySearch = null;
			} else {
				String dateFileSearch = StringPool.BLANK;
				String typeFileSearch = StringPool.BLANK;
				List<DLFileEntryMetadata> dlFileEntryMetadatas = DLFileEntryMetadataLocalServiceUtil
						.getFileVersionFileEntryMetadatas(fileEntrySearch.getLatestFileVersion().getFileVersionId());
				for (DLFileEntryMetadata dlFileEntryMetadata : dlFileEntryMetadatas) {
					DDMStructure ddmStructure = getStructureByFileEntryTypeId(dlFileEntryTypeId);
					if (dlFileEntryMetadata.getDDMStructureId() == ddmStructure.getStructureId()) {
						long ddmStorageId = dlFileEntryMetadata.getDDMStorageId();
						DDMContent ddmContent = DDMContentLocalServiceUtil.getContent(ddmStorageId);
						String data = ddmContent.getData();
						org.json.JSONObject json = new org.json.JSONObject(data);
						Object fieldValues = json.get("fieldValues");
						org.json.JSONArray array = new org.json.JSONArray(fieldValues.toString());
						for (int i = 0; i < array.length(); i++) {
							org.json.JSONObject object = array.getJSONObject(i);
							if (object.getString("name").equals(RPA_FORM_DATEUPLOADED)) {
								org.json.JSONObject value = object.getJSONObject("value");
								dateFileSearch = value.getString("en_GB");
							} else if (object.getString("name").equals(RPA_FORM_FIELDTYPE)) {
								org.json.JSONObject value = object.getJSONObject("value");
								typeFileSearch = value.getString("en_GB");
							}
						}
					}
				}
				if (!date.equals(dateFileSearch) || !fieldType.equals(typeFileSearch)) {
					fileEntrySearch = null;
				}
			}
		}

		if (Validator.isNotNull(fileEntrySearch)) {
			fileEntry = DLAppLocalServiceUtil.updateFileEntry(userId, fileEntrySearch.getFileEntryId(), sourceFileName,
					mimeType, title, description, StringPool.BLANK, DLVersionNumberIncrease.fromMajorVersion(true),
					fileBytes, serviceContext);
		} else {
			fileEntry = DLAppLocalServiceUtil.addFileEntry(userId, repositoryId, folderId, sourceFileName, mimeType,
					title, description, StringPool.BLANK, fileBytes, serviceContext);
		}
		if (Validator.isNotNull(fileEntry)) {
			logger.info("File added: " + fileEntry.getTitle());
			addExtraMetadataFromFormValues(fileEntry.getFileEntryId(), dlFileEntryTypeId, fieldType, date,
					partyUploading);
		}
		if (Validator.isNotNull(fieldType)) {
			fileEntry.getExpandoBridge().setAttribute("Data Item Type", fieldType);
			logger.info("fieldType : " + fieldType);
		}

		if (Validator.isNotNull(date)) {
			Date performancePeriodDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			fileEntry.getExpandoBridge().setAttribute("PerformancePeriod", performancePeriodDate.getTime());
			logger.info("PerformancePeriod : " + performancePeriodDate.getTime());
		}
	}

	/**
	 * Method to upload a document dependns on its type and adding extra metadata
	 * fields receiving a File object
	 *
	 * @param repositoryId
	 * @param folderId
	 * @param file
	 * @param mimeType
	 * @param title
	 * @param processArea
	 * @param description
	 * @param fieldType
	 * @param date
	 * @param partyUploading
	 * @return JSONResponse (code and message)
	 */
	public static JSONObject uploadRPADocument(long repositoryId, long folderId, File file, String mimeType,
			String title, String processArea, String description, String fieldType, String date, String partyUploading,
			String dataItemType, String performancePeriod, ServiceContext sc) {
		
		JSONObject response = JSONFactoryUtil.createJSONObject();
		int code = 200;
		String message = "Your file " + title + " has been uploaded";
		String[] processAreasToExclude = { "CodeManagerUploads", "GreenDeals", "Metering" };
		processArea = processArea.replace(" ", "");

		for (int i = 0; i < processAreasToExclude.length; i++) {
			if (processAreasToExclude[i].toLowerCase().equals(processArea.toLowerCase())) {
				processArea = processAreasToExclude[i];
			}
		}

		boolean noValidation = Arrays.stream(processAreasToExclude).anyMatch(processArea::equals);

		if (!noValidation) {
			try {
				response = FileValidationUtil.importFile(title, file, fieldType, sc, "API", folderId, sc.getUserId());
				if (response.getInt("code") == 500) {
					return response;
				}
			} catch (JSONException e) {
				logger.error("Error validating file", e);
				response.put("code", 500);
				response.put("message", "File validation error in " + title + ", please contact support team");
				return response;
			}
		}

		String processAreaMapped = mapProcessArea(processArea);
		long dlFileEntryTypeId = getDocumentType(processAreaMapped, repositoryId);
		if (dlFileEntryTypeId == 0) {
			logger.info("There is no document type for processArea: " + processAreaMapped);
			response.put("code", 500);
			response.put("message", "There is no document type for process area : " + processAreaMapped);
			return response;
		}

		ServiceContext serviceContext = createServiceContextForFileEntry(dlFileEntryTypeId);

		if (noValidation) {
			try {
				uploadRPADocumentFileEntry(title, repositoryId, folderId, dlFileEntryTypeId, file, dataItemType,
						performancePeriod, mimeType, description, fieldType, date, partyUploading, serviceContext);
			} catch (PortalException | ParseException e) {
				logger.error("Error adding file 04: " + title);
				logger.error("Error " + e.getMessage());
				code = 500;
				message = e.getMessage();
			}
		} else {
			try {
				if (FileUtil.getExtension(title).equals("zip")) {
					try {
						ZipFile zipFile = new ZipFile(file);
						Enumeration<? extends ZipEntry> entries = zipFile.entries();

						while (entries.hasMoreElements()) {
							ZipEntry entry = entries.nextElement();
							String entryName = entry.getName();
							String extension = FileUtil.getExtension(entryName);
							InputStream inputStream = zipFile.getInputStream(entry);
							File fileZip = File.createTempFile(entryName, "." + extension);
							// fileZip.deleteOnExit();
							try (FileOutputStream outputStream = new FileOutputStream(fileZip)) {
								IOUtils.copy(inputStream, outputStream);
							}
							uploadRPADocumentFileEntry(entryName, repositoryId, folderId, dlFileEntryTypeId, fileZip,
									dataItemType, performancePeriod, mimeType, description, fieldType, date,
									partyUploading, serviceContext);
						}
						zipFile.close();
					} catch (IOException e) {
						e.printStackTrace();
					}

				} else {
					uploadRPADocumentFileEntry(title, repositoryId, folderId, dlFileEntryTypeId, file, dataItemType,
							performancePeriod, mimeType, description, fieldType, date, partyUploading, serviceContext);
				}
			} catch (PortalException | ParseException e) {
				logger.error("Error adding file 05: " + title);
				logger.error("Error " + e.getMessage());
				code = 500;
				message = e.getMessage();
			}
		}
		response.put("code", code);
		response.put("message", message);
		return response;
	}

	public static void uploadRPADocumentFileEntry(String title, long repositoryId, long folderId,
			long dlFileEntryTypeId, File file, String dataItemType, String performancePeriod, String mimeType,
			String description, String fieldType, String date, String partyUploading, ServiceContext serviceContext)
			throws PortalException, ParseException {
		String sourceFileName = title.trim();
		FileEntry fileEntry = DLAppLocalServiceUtil.addFileEntry(serviceContext.getUserId(), repositoryId, folderId,
				sourceFileName, mimeType, title, description, StringPool.BLANK, file, serviceContext);

		if (Validator.isNotNull(fileEntry)) {
			logger.info("File added: " + fileEntry.getTitle());
			Date dateObj = new SimpleDateFormat("dd-MM-yyyy").parse(date);
			date = new SimpleDateFormat("yyyy-MM-dd").format(dateObj);
			addExtraMetadataFromFormValues(fileEntry.getFileEntryId(), dlFileEntryTypeId, fieldType, date,
					partyUploading);
		}

		if (Validator.isNotNull(dataItemType)) {
			fileEntry.getExpandoBridge().setAttribute("Data Item Type", dataItemType);
		}

		if (Validator.isNotNull(performancePeriod)) {
			Date performancePeriodDate = new SimpleDateFormat("dd-MM-yyyy").parse(performancePeriod);
			fileEntry.getExpandoBridge().setAttribute("PerformancePeriod", performancePeriodDate.getTime());
		}
	}

	/**
	 * Method to upload a document depends on its type and adding extra metadata
	 * fields receiving a File object
	 *
	 * @param repositoryId
	 * @param folderId
	 * @param mimeType
	 * @param title
	 * @param processArea
	 * @param description
	 * @param fieldType
	 * @param date
	 * @param partyUploading
	 * @return JSONResponse (code and message)
	 */
	public static JSONObject uploadRPADocumentByBytes(long repositoryId, long folderId, byte[] fileBytes,
			String mimeType, String title, String processArea, String description, String fieldType, String date,
			String partyUploading, String dataItemType, String performancePeriod) {
		JSONObject response = JSONFactoryUtil.createJSONObject();
		int code = 200;
		String message = "Your file has been uploaded";
		logger.debug("file: " + fileBytes);

		long dlFileEntryTypeId = getDocumentType(processArea, repositoryId);
		if (dlFileEntryTypeId == 0) {
			logger.info("There is no document type for processArea: " + processArea);
			response.put("code", 500);
			response.put("message", "There is no document type for process area : " + processArea);
			return response;
		}

		ServiceContext serviceContext = createServiceContextForFileEntry(dlFileEntryTypeId);
		String sourceFileName = title.trim();
		try {
			FileEntry fileEntry = DLAppLocalServiceUtil.addFileEntry(serviceContext.getUserId(), repositoryId, folderId,
					sourceFileName, mimeType, title, description, StringPool.BLANK, fileBytes, serviceContext);

			if (Validator.isNotNull(fileEntry)) {
				logger.info("File added: " + fileEntry.getTitle());
				Date dateObj = new SimpleDateFormat("dd-MM-yyyy").parse(date);
				date = new SimpleDateFormat("yyyy-MM-dd").format(dateObj);
				addExtraMetadataFromFormValues(fileEntry.getFileEntryId(), dlFileEntryTypeId, fieldType, date,
						partyUploading);
			}

			if (Validator.isNotNull(dataItemType)) {
				fileEntry.getExpandoBridge().setAttribute("Data Item Type", dataItemType);
			}

			if (Validator.isNotNull(performancePeriod)) {
				Date performancePeriodDate = new SimpleDateFormat("dd-MM-yyyy").parse(performancePeriod);
				fileEntry.getExpandoBridge().setAttribute("PerformancePeriod", performancePeriodDate.getTime());
			}

		} catch (PortalException | ParseException e) {
			logger.error("Error adding file 06: " + title);
			logger.error("Error " + e.getMessage());
			code = 500;
			message = e.getMessage();
		}

		response.put("code", code);
		response.put("message", message);
		return response;

	}

	private static boolean validateFile(String title, byte[] fileBytes) {
		boolean validateFile = true;
		String extension = StringPool.BLANK;
		int ext = title.lastIndexOf('.');
		if (ext > 0) {
			extension = title.substring(ext + 1);
		}
		File file = new File(title);
		try {
			FileUtils.writeByteArrayToFile(file, fileBytes);
		} catch (IOException e) {
			validateFile = false;
			logger.error("Error in validateFile() creating file from vileBytes", e);
		}
		switch (extension) {
		case "csv":
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				String line = "";
				while ((line = br.readLine()) != null) {
					String[] data = line.split(",");
					if (Validator.isNull(data) || data.length <= 1) {
						validateFile = false;
						br.close();
					} else {
						br.close();
						break;
					}
				}
			} catch (IOException e) {
				validateFile = false;
				logger.error("Error in validateFile() reading csv file", e);
			}
			break;
		case "xlsx":
			try {
				InputStream _inputStream = new FileInputStream(file);
				XSSFWorkbook _workbook = new XSSFWorkbook(_inputStream);
				XSSFSheet sheet = _workbook.getSheetAt(0);
				Iterator<Row> _iterator = sheet.rowIterator();
				if (!_iterator.hasNext()) {
					validateFile = false;
					_workbook.close();
					_inputStream.close();
				} else {
					_workbook.close();
					_inputStream.close();
					break;
				}
			} catch (IOException e) {
				validateFile = false;
				logger.error("Error in validateFile() reading xlsx file", e);
			}

			break;
		case "pip":
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				String line = "";
				while ((line = br.readLine()) != null) {
					String[] data = line.split("\\|");
					if (Validator.isNull(data) || data.length <= 1) {
						validateFile = false;
						br.close();
					} else {
						br.close();
						break;
					}
				}
			} catch (IOException e) {
				validateFile = false;
				logger.error("Error in validateFile() reading pip file", e);
			}
			break;
		case "tab":
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				String line = "";
				while ((line = br.readLine()) != null) {
					String[] data = line.split("\\t");
					if (Validator.isNull(data) || data.length <= 1) {
						validateFile = false;
						br.close();
					} else {
						br.close();
						break;
					}
				}
			} catch (IOException e) {
				validateFile = false;
				logger.error("Error in validateFile() reading tab file", e);
			}
			break;
		default:
			break;
		}
		file.delete();
		return validateFile;
	}

	private static String mapProcessArea(String processArea) {
		String processAreaMapped = StringPool.BLANK;
		logger.info("before switch");
		switch (processArea) {
		case "AnnualMaintenance":
			processAreaMapped = ANNUAL_MAINTENANCE;
			break;
		case "CodeManagerUploads":
			processAreaMapped = CODE_MANAGER_UPLOADS;
			break;
		case "EnergyTheft":
			processAreaMapped = ENERGY_THEFT;
			break;
		case "GreenDeals":
			processAreaMapped = GREEN_DEALS;
			break;
		case "MarketEntry":
			processAreaMapped = MARKET_ENTRY;
			break;
		case "Metering":
			processAreaMapped = METERING;
			break;
		case "PerformanceAssuranceDataUpload":
			processAreaMapped = PERFORMANCEASSURANCE_DATAUPLOAD;
			break;
		case "ServiceProviders":
			processAreaMapped = SERVICE_PROVIDERS;
			break;
		case "SmartMeteringInstallationInformation":
			processAreaMapped = SMARTMETERING_INSTALLATIONINFORMATION;
			break;
		case "Other":
			processAreaMapped = OTHER;
			break;
		}
		logger.info("processAreaMapped: " + processAreaMapped);
		return processAreaMapped;
	}

	private static long getDocumentType(String processArea, long groupId) {
		DynamicQuery dynamicQuery = DLFileEntryTypeLocalServiceUtil.dynamicQuery();
		dynamicQuery.add(PropertyFactoryUtil.forName("name").like("%" + processArea + "%"));
		dynamicQuery.add(PropertyFactoryUtil.forName("groupId").eq(groupId));
		List<DLFileEntryType> dlFileEntryTypes = DLFileEntryTypeLocalServiceUtil.dynamicQuery(dynamicQuery);
		return Validator.isNotNull(dlFileEntryTypes) && dlFileEntryTypes.size() > 0
				? dlFileEntryTypes.get(0).getFileEntryTypeId()
				: 0L;
	}

	private static ServiceContext createServiceContextForFileEntry(long dlFileEntryTypeId) {
		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setAttribute(FILEENTRYTYPEID, dlFileEntryTypeId);

		try {
			DDMStructure ddmStructure = getStructureByFileEntryTypeId(dlFileEntryTypeId);
			DDMForm ddmForm = ddmStructure.getDDMForm();
			DDMFormValues ddmFormValues = new DDMFormValues(ddmForm);

			ddmFormValues.setDefaultLocale(LocaleUtil.getDefault());
			serviceContext.setAttribute(
					DDMFormValues.class.getName() + StringPool.POUND + ddmStructure.getStructureId(), ddmFormValues);

			Company company = CompanyLocalServiceUtil
					.getCompanyByWebId(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
			User user = UserLocalServiceUtil.getUserByEmailAddress(company.getCompanyId(), ADMIN_EMAILADDRESS);
			serviceContext.setUserId(user.getUserId());

		} catch (PortalException e) {
			logger.error("Error getting ddmStructure by fileEntryTypeId: " + dlFileEntryTypeId);
		}

		return serviceContext;
	}

	private static DDMStructure getStructureByFileEntryTypeId(long dlFileEntryTypeId) {
		DDMStructure ddmStructure = null;
		try {
			DLFileEntryType dlFileEntryType = DLFileEntryTypeLocalServiceUtil.getDLFileEntryType(dlFileEntryTypeId);
			List<DDMStructure> ddmStructures = dlFileEntryType.getDDMStructures();
			ddmStructure = ddmStructures.get(0);

		} catch (PortalException e) {
			logger.error("Error getting dlFileEntryType by dlFileEntryType: " + dlFileEntryTypeId);
		}
		return Validator.isNotNull(ddmStructure) ? ddmStructure : null;
	}

	private static String addExtraMetadataFromFormValues(long fileEntryId, long fileEntryTypeId, String fieldType,
			String date, String partyUploading) throws PortalException {

		String data = StringPool.BLANK;
		DDMContent ddmContent = null;

		DDMStructure ddmStructure = getStructureByFileEntryTypeId(fileEntryTypeId);
		logger.info("Document Type - ddmStructure: " + ddmStructure.getStructureId());
		DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.getDLFileEntry(fileEntryId);
		DLFileVersion dlFileVersion = dlFileEntry.getFileVersion();
		long dlFileVersionId = dlFileVersion.getFileVersionId();
		logger.info("dlFileVersionId: " + dlFileVersionId);

		List<DLFileEntryMetadata> dlFileEntryMetadatas = DLFileEntryMetadataLocalServiceUtil
				.getFileVersionFileEntryMetadatas(dlFileVersionId);

		if (Validator.isNotNull(dlFileEntryMetadatas) && dlFileEntryMetadatas.size() > 0) {
			logger.info("dlFileEntryMetadatas.size(): " + dlFileEntryMetadatas.size());
			for (DLFileEntryMetadata dlFileEntryMetadata : dlFileEntryMetadatas) {
				logger.info("dlFileEntryMetadata.getDDMStructureId(): " + dlFileEntryMetadata.getDDMStructureId());
				if (dlFileEntryMetadata.getDDMStructureId() == ddmStructure.getStructureId()) {
					long ddmStorageId = dlFileEntryMetadata.getDDMStorageId();
					logger.info("ddmStorageId: " + ddmStorageId);
					ddmContent = DDMContentLocalServiceUtil.getContent(ddmStorageId);
					data = ddmContent.getData();
					logger.info("data: " + data);
					break;
				}
			}
		}

		if (Validator.isNotNull(data) && !data.isEmpty()) {
			data = addNewValuesOnDataField(data, fieldType, date, partyUploading);
			logger.info("ddmContent.getData before: " + ddmContent.getData());
			ddmContent.setData(data);
			logger.info("ddmContent.getData after: " + ddmContent.getData());
			DDMContentLocalServiceUtil.updateContent(ddmContent.getContentId(), ddmContent.getName(),
					ddmContent.getDescription(), data, new ServiceContext());
			logger.info("DATA UPDATED!");
		}

		return data;
	}

	private static String addNewValuesOnDataField(String data, String fieldType, String date, String partyUploading) {
		try {
			JSONObject dataObject = JSONFactoryUtil.createJSONObject(data);
			logger.info("dataObject: " + dataObject);
			// putting available Language Ids
			String[] availableLanguageIds = { "en_GB" };
			dataObject.put("availableLanguageIds", availableLanguageIds);
			logger.info("dataObject - after putting availableLanguages: " + dataObject);

			// putting fieldsValues
			JSONArray fieldValuesArray = JSONFactoryUtil.createJSONArray();

			// RPA_FORM_FIELDTYPE
			JSONObject fieldTypeJson = JSONFactoryUtil.createJSONObject();
			fieldTypeJson.put("instanceId", PwdGenerator.getPassword(8));
			fieldTypeJson.put("name", RPA_FORM_FIELDTYPE);
			JSONObject localizedValueFieldType = JSONFactoryUtil.createJSONObject();
			localizedValueFieldType.put(Locale.UK.toString(), fieldType);
			fieldTypeJson.put("value", localizedValueFieldType);
			fieldValuesArray.put(fieldTypeJson);

			// RPA_FORM_DATEUPLOADED
			JSONObject dateUploadedJson = JSONFactoryUtil.createJSONObject();
			dateUploadedJson.put("instanceId", PwdGenerator.getPassword(8));
			dateUploadedJson.put("name", RPA_FORM_DATEUPLOADED);
			JSONObject localizedValueDateUploaded = JSONFactoryUtil.createJSONObject();
			localizedValueDateUploaded.put(Locale.UK.toString(), date);
			dateUploadedJson.put("value", localizedValueDateUploaded);
			fieldValuesArray.put(dateUploadedJson);

			// RPA_FORM_PARTYUPLOADING
			JSONObject partyUploadingJson = JSONFactoryUtil.createJSONObject();
			partyUploadingJson.put("instanceId", PwdGenerator.getPassword(8));
			partyUploadingJson.put("name", RPA_FORM_PARTYUPLOADING);
			JSONObject localizedValuePartyUploading = JSONFactoryUtil.createJSONObject();
			localizedValuePartyUploading.put(Locale.UK.toString(), partyUploading);
			partyUploadingJson.put("value", localizedValuePartyUploading);
			fieldValuesArray.put(partyUploadingJson);

			dataObject.put("fieldValues", fieldValuesArray);
			logger.info("dataObject - after putting fieldValues: " + dataObject);
			data = dataObject.toJSONString();
		} catch (JSONException e) {
			logger.error("Error creating json object from data");
		}
		return data;
	}

	/**
	 * Obtains folderId or creates a new one
	 * 
	 * @param serviceContext ServiceContext
	 * @param userId         long
	 * @param folderName     String
	 * @return long
	 */
	private static long getOrCreateFolder(ServiceContext serviceContext, long userId, long repositoryId,
			String folderName) {
		logger.debug("getOrCreateFolder... ");

		final long parentFolderId = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;
		DLFolder prev = null;

		prev = DLFolderLocalServiceUtil.fetchFolder(repositoryId, parentFolderId, folderName);
		if (Validator.isNull(prev)) {
			logger.debug("Folder doesn't exist. Creating " + folderName);
			try {
				prev = DLFolderLocalServiceUtil.addFolder(userId, repositoryId, repositoryId, false, parentFolderId,
						folderName, StringPool.BLANK, false, serviceContext);
			} catch (PortalException e) {
				logger.error("Error creating RPA Documents folder", e);
			}
		}
		return prev.getFolderId();
	}

}
