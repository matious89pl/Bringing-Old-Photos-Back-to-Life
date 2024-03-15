package com.everis.file.upload.validation.portlet;

import com.everis.file.upload.validation.constants.fileUploadValidationPortletKeys;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingMessage;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.model.DLVersionNumberIncrease;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLAppService;
import com.liferay.document.library.kernel.service.DLFolderLocalService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import rec.file.conf.service.model.File_Conf;
import rec.file.conf.service.service.File_ConfLocalService;

import javax.portlet.*;
import java.io.File;
import java.io.*;
import java.util.Date;
import java.util.List;

/**
 * @author asamuilo
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=fileUploadValidation", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/add_file.jsp",
		"javax.portlet.name=" + fileUploadValidationPortletKeys.FILEUPLOADVALIDATION,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)

public class FileUploadValidationPortlet extends MVCPortlet {

	private static final Log _log = LogFactoryUtil.getLog(FileUploadValidationPortlet.class.getName());
	private static final String ROOT_FOLDER_NAME = "File Upload Schemas";
	private static final long PARENT_FOLDER_ID = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;
	private static final long CONSTANTZERO = 0L;

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException, java.io.IOException {
		_log.debug("=============================Into doView");

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		List<File_Conf> fileSchemas;
		Folder folderToDownload;
		boolean folderExists;
		long folderId = 0;

		if (F_filFile_Conf.getFile_ConfsCount() > 0) {
			fileSchemas = F_filFile_Conf.getFile_Confs(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			folderExists = isFolderExist(themeDisplay);
			if (folderExists) {
				folderToDownload = getFolder(themeDisplay);
				folderId = folderToDownload.getFolderId();
				_log.debug(" folder ID " + folderId);

				renderRequest.setAttribute("folderId", folderId);
				renderRequest.setAttribute("fileSchemas", fileSchemas);
				renderRequest.setAttribute("fileSchemasCount", fileSchemas.size());
			} else {
				_log.warn("WARN - No file schema folder");
			}

		}
		
		super.doView(renderRequest, renderResponse);
	}

	@ProcessAction(name = "addFileSchema")
	public void addFileSchema(ActionRequest actionRequest, ActionResponse actionResponse) {
		_log.debug("=============================Into addFileSchema");

		InputStream _inputStream;
		InputStream _inputStreamSave;
		File_Conf fileSchemas;
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(actionRequest);

		if (Validator.isNotNull(uploadRequest.getFile("jsonSchemaFile"))) {
			File file = uploadRequest.getFile("jsonSchemaFile");
			String ext = FileUtil.getExtension(file.getName()).toLowerCase();

			if (ext.equals("json")) {
				_log.debug("Is JSON file");
				String mimeType = MimeTypesUtil.getContentType(file);
				long repositoryId = themeDisplay.getScopeGroupId();
				Folder folder = createFolder(actionRequest, themeDisplay);

				try {
					ServiceContext serviceContext = ServiceContextFactory.getInstance(File_Conf.class.getName(), actionRequest);

					ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
					_inputStream = new FileInputStream(file);

					IOUtils.copy(_inputStream, byteArrayOutputStream);
					byte[] data = byteArrayOutputStream.toByteArray();
					_inputStreamSave = new ByteArrayInputStream(data);

					String fileContent = FileUtils.readFileToString(file, "UTF-8");
					JSONObject jsonObj = JSONFactoryUtil.createJSONObject(fileContent);

					String docConfigJSON = jsonObj.toString();
					String fileName = jsonObj.getString("conf_name");
					fileName = fileName.concat(".json");

					fileSchemas = F_filFile_Conf.findBydocFileType(jsonObj.getString("file_type"));

					if (Validator.isNull(fileSchemas)) {
						_log.debug("Schema object not existing. Creating new schema object");
						Date date = new Date();
						long userId = themeDisplay.getUserId();

						File_Conf conf_file = F_filFile_Conf.createFile_Conf(_counterLocalService.increment(File_Conf.class.getName()));
						conf_file.setCompanyId(themeDisplay.getCompanyGroupId());
						conf_file.setCreateDate(date);
						conf_file.setDocConfigJSON(docConfigJSON);
						conf_file.setDocConfName(fileName);
						conf_file.setDocFileType(jsonObj.getString("file_type"));
						conf_file.setUserName(themeDisplay.getUser().getFullName());
						conf_file.setUserId(userId);
						conf_file.setModifiedDate(date);

						//----
						//String schemaPath = "/schema/file-config-schema.json";
						//validateJSON(docConfigJSON, schemaPath); //add if conditional
						//----

						F_filFile_Conf.addFile_Conf(conf_file);

						fileUpload(repositoryId, _inputStreamSave, mimeType, fileName, folder, file.length(), false, CONSTANTZERO, null, serviceContext);

						IOUtils.closeQuietly(_inputStream);
						IOUtils.closeQuietly(_inputStreamSave);
						IOUtils.closeQuietly(byteArrayOutputStream);

					} else {
						_log.error("Schema already exists");
						SessionErrors.add(actionRequest, "error-duplicate");
						SessionMessages.add(actionRequest, PortalUtil.getPortletId(actionRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
					}

				} catch (IOException | PortalException e) {
					SessionErrors.add(actionRequest, "error-default");
					SessionMessages.add(actionRequest, PortalUtil.getPortletId(actionRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
					_log.error("ERROR creating file_conf", e);
				}

			} else {
				_log.error("ERROR - File is not JSON");
				SessionErrors.add(actionRequest, "error-no-json");
				SessionMessages.add(actionRequest, PortalUtil.getPortletId(actionRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
			}

		} else {
			_log.error("ERROR - File object is empty");
			SessionErrors.add(actionRequest, "error-empty");
			SessionMessages.add(actionRequest, PortalUtil.getPortletId(actionRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
		}
	}

	@ProcessAction(name = "updateSchema")
	public void updateSchema(ActionRequest actionRequest, ActionResponse actionResponse) {
		_log.debug("=============================Into updateSchema");

		String updateSchema = "jsonSchemaFileUpdate";
		updatingFileSchema(actionRequest, updateSchema);
	}

	public void updatingFileSchema(ActionRequest actionRequest, String InputStream) {
		_log.debug("=============================Into updatingFileSchema");

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(actionRequest);
		InputStream _inputStream;
		InputStream _inputStreamSave;

		long docConfId = Long.parseLong(ParamUtil.get(actionRequest, "DocConfId", ""));
		_log.debug("ID of schema to update: " + docConfId);

		File_Conf fetchedSchemaObj =  F_filFile_Conf.fetchFile_Conf(docConfId);

		try {
			if (Validator.isNotNull(uploadRequest.getFile(InputStream))) {
				File newFile = uploadRequest.getFile(InputStream);
				String ext = FileUtil.getExtension(newFile.getName()).toLowerCase();

				if (ext.equals("json")) {
					_log.debug("Is JSON file");
					String mimeType = MimeTypesUtil.getContentType(newFile);
					long repositoryId = themeDisplay.getScopeGroupId();
					Folder folder = createFolder(actionRequest, themeDisplay);

					Date date = new Date();

					ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
					_inputStream = new FileInputStream(newFile);

					IOUtils.copy(_inputStream, byteArrayOutputStream);
					byte[] data = byteArrayOutputStream.toByteArray();
					_inputStreamSave = new ByteArrayInputStream(data);

					String fileContent = FileUtils.readFileToString(newFile, "UTF-8");
					JSONObject jsonObj = JSONFactoryUtil.createJSONObject(fileContent);

					String docConfigJSON = jsonObj.toString();
					String fileName = jsonObj.getString("conf_name");
					fileName = fileName.concat(".json");

					long oldFileId = isFileExist(repositoryId, folder, fetchedSchemaObj.getDocConfName());
					
					if (Validator.isNotNull(fetchedSchemaObj)) {
						_log.debug("schema object is existing. Updating schema object");
						ServiceContext serviceContext = ServiceContextFactory.getInstance(File_Conf.class.getName(), actionRequest);
						long userId = themeDisplay.getUserId();

						fetchedSchemaObj.setCompanyId(themeDisplay.getCompanyGroupId());
						fetchedSchemaObj.setCreateDate(date);
						fetchedSchemaObj.setDocConfigJSON(docConfigJSON);
						fetchedSchemaObj.setDocConfName(fileName);
						fetchedSchemaObj.setDocFileType(jsonObj.getString("file_type"));
						fetchedSchemaObj.setUserName(themeDisplay.getUser().getFullName());
						fetchedSchemaObj.setUserId(userId);
						fetchedSchemaObj.setModifiedDate(date);

						fileUpload(repositoryId, _inputStreamSave, mimeType, fileName, folder, newFile.length(), true, oldFileId, newFile, serviceContext);
						F_filFile_Conf.updateFile_Conf(fetchedSchemaObj);

					} else {
						_log.error("ERROR - No schema object found to update");
					}

				} else {
					_log.error("ERROR - File is not JSON");
					SessionErrors.add(actionRequest, "error-no-json");
					SessionMessages.add(actionRequest, PortalUtil.getPortletId(actionRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
				}

			} else {
				_log.debug("ERROR - File object is empty");
				SessionErrors.add(actionRequest, "error-empty");
				SessionMessages.add(actionRequest, PortalUtil.getPortletId(actionRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
			}
		} catch (IOException | PortalException e) {
			SessionErrors.add(actionRequest, "error-default");
			SessionMessages.add(actionRequest, PortalUtil.getPortletId(actionRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
			_log.error("ERROR - Getting file in uploadRequest", e);
		}
	}

	@ProcessAction(name = "schemaFileManagement")
	public void schemaFileManagement(ActionRequest actionRequest, ActionResponse actionResponse) {
		_log.debug("=============================Into schemaFileManagement");

		String docConfName = ParamUtil.get(actionRequest, "DocConfName", "");
		String action = ParamUtil.get(actionRequest, "Action", "");

		if (action.equalsIgnoreCase("delete")) {
			File_Conf delete = F_filFile_Conf.findFileConfByName(docConfName);
			if (delete != null) {
				F_filFile_Conf.deleteFile_Conf(delete);
				ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
				long ID = themeDisplay.getScopeGroupId();

				try {
					Folder folder = _dlAppServiceUtil.getFolder(ID, 0, ROOT_FOLDER_NAME);
					long folderID = folder.getFolderId();
					_dlAppServiceUtil.deleteFileEntryByTitle(ID, folderID, docConfName);
				} catch (PortalException e) {
					_log.error("==============================error", e);
				}
			} else {
				_log.error("Object not found to delete");

			}

		}

	}

	public void fileUpload(long groupId, InputStream _inInputStreamSave, String mimeType, String fileName, Folder folder, long fileSize, boolean update, long oldFileId, File newFile, ServiceContext serviceContext) {
		_log.debug("=============================Into fileUpload");

		String description;

		if (!update && oldFileId == CONSTANTZERO) {
			_log.debug("============================== Adding new Schema file to D&L");
			description = fileName + " added at " + new Date();

			try {
				_log.debug("============================== Adding file with fileName " + fileName);
				_dlAppServiceUtil.addFileEntry(groupId, folder.getFolderId(), fileName, mimeType, fileName,	description, "", _inInputStreamSave, fileSize, serviceContext);
			} catch (PortalException e) {
				_log.error("ERROR - Uploading Schema file to D&L", e);
			}

		} else if (update && newFile != null) {
			_log.debug("============================== Updating Schema file in D&L");
			description = fileName + " updated at " + new Date();

			try {
				_log.debug("==============================oldFileId " + oldFileId);
				_dlAppServiceUtil.updateFileEntry(oldFileId, fileName, mimeType, fileName, description, null, DLVersionNumberIncrease.MINOR, newFile, serviceContext);
			} catch (PortalException e) {
				_log.error("ERROR - Updating Schema file to D&L", e);
			}
		}
		
	}

	public Folder createFolder(ActionRequest actionRequest, ThemeDisplay themeDisplay) {
		_log.debug("=============================Into createFolder");

		String ROOT_FOLDER_DESCRIPTION = "This is File Upload Schemas folder";
		boolean folderExist = isFolderExist(themeDisplay);
		Folder folder = null;
		if (!folderExist) {
			try {
				ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFolder.class.getName(),
						actionRequest);
				folder = _dlAppServiceUtil.addFolder(themeDisplay.getScopeGroupId(), PARENT_FOLDER_ID, ROOT_FOLDER_NAME,
						ROOT_FOLDER_DESCRIPTION, serviceContext);
				_log.debug("==============================FOLDER CREATED");
			} catch (PortalException | SystemException e) {
				_log.error("==============================error", e);
			}

		} else {
			folder = getFolder(themeDisplay);
		}
		return folder;
	}

private JSONObject validateJSON(String json, String schemaPath) {
		
		//Initialize jsonSchema object
		JsonSchema schema;
		//Initialize response jsonObject, example-> {isValid: true, message: "JSON file is valid"}
		JSONObject responseJSON = JSONFactoryUtil.createJSONObject();
		//Initialize messages jsonArray
		JSONArray responseMessages = JSONFactoryUtil.createJSONArray();
		
		try {
			//Get the schema from the portlet resource path
			schema = JsonSchemaFactory.byDefault().getJsonSchema("resource:" + schemaPath);
			//Cast json string to jsonNode
			JsonNode jsonNode = JsonLoader.fromString(json);
			
			//Validate json against jsonSchema
			ProcessingReport report = schema.validate(jsonNode);
			
			//Check if the report is success
			if (report.isSuccess()) {
				_log.info("###### isSuccess: true ");
				responseJSON.put("isValid", true);
				//Initialize success message response jsonObject
				JSONObject successMessage = JSONFactoryUtil.createJSONObject();
				successMessage.put("message", "Valid JSON file");
				
				responseMessages.put(successMessage);
				responseJSON.put("messages", responseMessages);
			} else {
				_log.info("###### isSuccess: false ");
				responseJSON.put("isValid", false);
				
				//Get reason from the report
				for (ProcessingMessage message : report) {
					//Initialize success message response jsonObject
					JSONObject errorMessage = JSONFactoryUtil.createJSONObject();
					
					_log.info("###### message: " + message.getMessage());
					errorMessage.put("message", message.getMessage());

					responseMessages.put(errorMessage);
				}
				
				responseJSON.put("messages", responseMessages);
			}
			
			_log.info("###### responseJSON: " + responseJSON);
			return responseJSON;
			
		} catch (ProcessingException | IOException e) {
			_log.error("Error validating json against jsonSchema", e);
			return responseJSON;
		}   
		
	}

	
	public Folder getFolder(ThemeDisplay themeDisplay) {
		_log.debug("=============================Into getFolder");

		Folder folder;

		try {
			folder = _dlAppServiceUtil.getFolder(themeDisplay.getScopeGroupId(), PARENT_FOLDER_ID, ROOT_FOLDER_NAME);
		} catch (Exception e) {
			_log.error("ERROR - Getting file schema folder" + e);
			folder = null;
		}

		return folder;
	}
	
	public long isFileExist(long repositoryId, Folder folder,String fileName) {
		_log.debug("=============================Into isFileExist");

		long fileEntryId = CONSTANTZERO;
		FileEntry oldFile;
		_log.debug("Folder: " + folder.getFolderId() + ", repositoryId " + repositoryId);

		try {
			oldFile = DLAppLocalServiceUtil.getFileEntry(repositoryId, folder.getFolderId(), fileName);
			fileEntryId = oldFile.getFileEntryId();
		} catch (PortalException e) {
			_log.error("ERROR - File " + fileName + " does not exist in D&L");
		}

		return fileEntryId;
	}

	public boolean isFolderExist(ThemeDisplay themeDisplay) {
		_log.debug("=============================Into isFolderExist");

		boolean folderExist = false;

		try {
			_dlAppServiceUtil.getFolder(themeDisplay.getScopeGroupId(), PARENT_FOLDER_ID, ROOT_FOLDER_NAME);
			folderExist = true;
			_log.debug("Folder already exists....");
		} catch (Exception e) {
			folderExist = false;
			_log.debug("Folder does not exists....");
		}

		return folderExist;
	}

	@Reference
	File_ConfLocalService F_filFile_Conf;

	@Reference
	CounterLocalService _counterLocalService;

	@Reference
	protected DLFolderLocalService _dlFolderLocalService;

	@Reference
	protected DLAppService _dlAppServiceUtil;

	@Reference
	private Portal _portal;

}