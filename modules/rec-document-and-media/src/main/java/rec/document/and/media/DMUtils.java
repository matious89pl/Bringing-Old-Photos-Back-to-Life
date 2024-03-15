package rec.document.and.media;

import com.liferay.document.library.constants.DLPortletKeys;
import com.liferay.document.library.kernel.exception.NoSuchFolderException;
import com.liferay.document.library.kernel.model.*;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryMetadataLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryTypeLocalServiceUtil;
import com.liferay.dynamic.data.mapping.kernel.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMContent;
import com.liferay.dynamic.data.mapping.service.DDMContentLocalServiceUtil;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.CreationMenu;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.servlet.taglib.ui.URLMenuItem;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portlet.documentlibrary.service.permission.DLFolderPermission;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.WindowStateException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class DMUtils {

	private static final Log logger = LogFactoryUtil.getLog(DMUtils.class);
	// EXTRA METADATA VALUES
	public static final String RPA_FORM_FIELDTYPE = "RPA_Form_FieldType";

	public static final String RPA_FORM_DATEUPLOADED = "RPA_Form_DateUploaded";


	public static CreationMenu getDMCreationMenu(ThemeDisplay themeDisplay, PortletRequest portletRequest) {
		logger.debug("START - getDMCreationMenu");
		CreationMenu creationMenu = new CreationMenu();
		creationMenu.setItemsIconAlignment("left");

		logger.debug("Getting folder");
		Folder folder = getFolder(themeDisplay, portletRequest);
		logger.debug("Folder: " + folder);

		URLMenuItem addFileMenuItem = getAddFileMenuItem(folder, themeDisplay, portletRequest);
		URLMenuItem addFolderMenuItem = getAddFolderMenuItem(folder, themeDisplay, portletRequest);
		URLMenuItem addMultipleFilesMenuItem = getAddMultipleFilesMenuItem(folder, themeDisplay, portletRequest);
		URLMenuItem addRepositoryMenuItem = getAddRepositoryMenuItem(folder, themeDisplay, portletRequest);
		URLMenuItem addShortcutMenuItem = getAddShortcutMenuItem(folder, themeDisplay, portletRequest);
		
		if(Validator.isNotNull(addFileMenuItem)) {
			creationMenu = addDropdowItem(creationMenu, addFileMenuItem);
		}
		
		if(Validator.isNotNull(addFolderMenuItem)) {
			creationMenu = addDropdowItem(creationMenu, addFolderMenuItem);		
		}
		
		if(Validator.isNotNull(addMultipleFilesMenuItem)) {
			creationMenu = addDropdowItem(creationMenu, addMultipleFilesMenuItem);
		}
		
		if(Validator.isNotNull(addRepositoryMenuItem)) {
			creationMenu = addDropdowItem(creationMenu, addRepositoryMenuItem);
		}
		
		if(Validator.isNotNull(addShortcutMenuItem)) {
			creationMenu = addDropdowItem(creationMenu, addShortcutMenuItem);
		}
		
		logger.debug("Returned creationMenu: " + creationMenu);

		logger.debug("END - getDMCreationMenu");
		return creationMenu;
	}

	private static CreationMenu addDropdowItem(CreationMenu creationMenu, URLMenuItem urlMenuItem) {
		logger.debug("START - addDropdowItem with creationMenu: " + creationMenu + " and: " + urlMenuItem);
		creationMenu.addDropdownItem(dropdownItem -> {
			dropdownItem.setData(urlMenuItem.getData());
			dropdownItem.setHref(urlMenuItem.getURL());
			dropdownItem.setIcon(urlMenuItem.getIcon());
			dropdownItem.setLabel(urlMenuItem.getLabel());
			dropdownItem.setSeparator(urlMenuItem.hasSeparator());
		});
		logger.debug("END - addDropdowItem with creationMenu: " + creationMenu + " and: " + urlMenuItem);
		return creationMenu;
	}

	private static URLMenuItem getAddFileMenuItem(Folder folder, ThemeDisplay themeDisplay, PortletRequest portletRequest) {
		logger.debug("START - getAddFileMenuItem with folder: " + folder);
		long folderId = getFolderId(folder);

		logger.debug("Checking permissions to upload documents");
		if (!hasPermission(themeDisplay.getPermissionChecker(), themeDisplay.getScopeGroupId(), folderId, ActionKeys.ADD_DOCUMENT)) {
			logger.debug("The user has not permissions to upload documents");
			return null;
		}

		URLMenuItem urlMenuItem = new URLMenuItem();

		urlMenuItem.setIcon("upload");
		urlMenuItem.setLabel(LanguageUtil.get(PortalUtil.getHttpServletRequest(portletRequest), "file-upload"));

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		PortletURL url = getPortletURL(themeDisplay, portletRequest);

		url.setParameter("mvcRenderCommandName", "/document_library/edit_file_entry");
		url.setParameter(Constants.CMD, Constants.ADD);
		url.setParameter("redirect", PortalUtil.getCurrentURL(portletRequest));
		url.setParameter("fileEntryTypeId", String.valueOf(getDefaultFileEntryTypeId(folderId)));
		url.setParameter("folderId", String.valueOf(folderId));
		url.setParameter("portletResource", portletDisplay.getId());
		url.setParameter("repositoryId", String.valueOf(getRepositoryId(folder, themeDisplay)));

		urlMenuItem.setURL(url.toString());
		
		logger.debug("PortletURL: " + url.toString());
		logger.debug("Returned urlMenuItem: " + urlMenuItem);
		
		logger.debug("END - getAddFileMenuItem with folder: " + folder);
		return urlMenuItem;
	}
	
	private static URLMenuItem getAddFolderMenuItem(Folder folder, ThemeDisplay themeDisplay, PortletRequest portletRequest) {
		logger.debug("START - getAddFolderMenuItem with folder: " + folder);
		long folderId = getFolderId(folder);

		logger.debug("Checking permissions to upload folders");
		if (!hasPermission(themeDisplay.getPermissionChecker(), themeDisplay.getScopeGroupId(), folderId, ActionKeys.ADD_FOLDER)) {
			logger.debug("The user has not permissions to upload folders");
			return null;
		}

		URLMenuItem urlMenuItem = new URLMenuItem();

		urlMenuItem.setIcon("folder");
		urlMenuItem.setLabel(LanguageUtil.get(PortalUtil.getHttpServletRequest(portletRequest), "folder"));

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		PortletURL url = getPortletURL(themeDisplay, portletRequest);

		url.setParameter("mvcRenderCommandName", "/document_library/edit_folder");
		url.setParameter("redirect", PortalUtil.getCurrentURL(portletRequest));
		url.setParameter("ignoreRootFolder", "true");
		url.setParameter("parentFolderId", String.valueOf(folderId));
		url.setParameter("portletResource", portletDisplay.getId());
		url.setParameter("repositoryId", String.valueOf(getRepositoryId(folder, themeDisplay)));

		urlMenuItem.setURL(url.toString());
		
		logger.debug("PortletURL: " + url.toString());
		logger.debug("Returned urlMenuItem: " + urlMenuItem);
		
		logger.debug("END - getAddFolderMenuItem with folder: " + folder);
		return urlMenuItem;
	}
	
	private static URLMenuItem getAddMultipleFilesMenuItem(Folder folder, ThemeDisplay themeDisplay, PortletRequest portletRequest) {
		logger.debug("START - getAddMultipleFilesMenuItem with folder: " + folder);
		
		logger.debug("Checking multiple upload support");
		if ((folder != null) && !folder.isSupportsMultipleUpload()) {
			logger.debug("The folder does not support multiple upload");
			return null;
		}
		
		long folderId = getFolderId(folder);

		logger.debug("Checking permissions to upload documents");
		if (!hasPermission(themeDisplay.getPermissionChecker(), themeDisplay.getScopeGroupId(), folderId, ActionKeys.ADD_DOCUMENT)) {
			logger.debug("The user has not permissions to upload documents");
			return null;
		}

		URLMenuItem urlMenuItem = new URLMenuItem();

		urlMenuItem.setIcon("upload-multiple");
		
		urlMenuItem.setLabel("Multiple Files Upload");

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		PortletURL url = getPortletURL(themeDisplay, portletRequest);

		url.setParameter("mvcRenderCommandName", "/document_library/upload_multiple_file_entries");
		url.setParameter("redirect", PortalUtil.getCurrentURL(portletRequest));
		url.setParameter("folderId", String.valueOf(folderId));
		url.setParameter("portletResource", portletDisplay.getId());
		url.setParameter("repositoryId", String.valueOf(getRepositoryId(folder, themeDisplay)));

		urlMenuItem.setURL(url.toString());

		logger.debug("PortletURL: " + url.toString());
		logger.debug("Returned urlMenuItem: " + urlMenuItem);
		
		logger.debug("END - getAddMultipleFilesMenuItem with folder: " + folder);
		return urlMenuItem;
	}
	
	private static URLMenuItem getAddRepositoryMenuItem(Folder folder, ThemeDisplay themeDisplay, PortletRequest portletRequest) {
		logger.debug("START - getAddRepositoryMenuItem with folder: " + folder);
		
		logger.debug("Checking if folder is not null");
		if (folder != null) {
			logger.debug("Folder is not null");
			return null;
		}

		long folderId = getFolderId(folder);

		logger.debug("Checking permissions to upload repositories");
		if (!hasPermission(themeDisplay.getPermissionChecker(),	themeDisplay.getScopeGroupId(),	DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,	ActionKeys.ADD_REPOSITORY)) {
			logger.debug("The user has not permissions to upload repositories");
			return null;
		}

		URLMenuItem urlMenuItem = new URLMenuItem();

		urlMenuItem.setIcon("repository");
		urlMenuItem.setLabel(LanguageUtil.get(PortalUtil.getHttpServletRequest(portletRequest),	"repository"));

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		PortletURL url = getPortletURL(themeDisplay, portletRequest);

		url.setParameter("mvcRenderCommandName", "/document_library/edit_repository");
		url.setParameter(Constants.CMD, Constants.ADD);
		url.setParameter("redirect", PortalUtil.getCurrentURL(portletRequest));

		urlMenuItem.setURL(url.toString());

		logger.debug("PortletURL: " + url.toString());
		logger.debug("Returned urlMenuItem: " + urlMenuItem);
		
		logger.debug("END - getAddRepositoryMenuItem with folder: " + folder);
		return urlMenuItem;
	}
	
	private static URLMenuItem getAddShortcutMenuItem(Folder folder, ThemeDisplay themeDisplay, PortletRequest portletRequest) {
		logger.debug("START - getAddShortcutMenuItem with folder: " + folder);
		
		logger.debug("Checking create shortcut support");
		if ((folder != null) && !folder.isSupportsShortcuts()) {
			logger.debug("The folder does not support create shortcuts");
			return null;
		}

		long folderId = getFolderId(folder);

		logger.debug("Checking permissions to create shortcuts");
		if (!hasPermission(themeDisplay.getPermissionChecker(),	themeDisplay.getScopeGroupId(), folderId, ActionKeys.ADD_SHORTCUT)) {
			logger.debug("The user has not permissions to create shortcuts");
			return null;
		}

		URLMenuItem urlMenuItem = new URLMenuItem();

		urlMenuItem.setIcon("shortcut");
		urlMenuItem.setLabel(LanguageUtil.get(PortalUtil.getHttpServletRequest(portletRequest), "shortcut"));

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		PortletURL url = getPortletURL(themeDisplay, portletRequest);

		url.setParameter("mvcRenderCommandName", "/document_library/edit_file_shortcut");
		url.setParameter("redirect", PortalUtil.getCurrentURL(portletRequest));
		url.setParameter("folderId", String.valueOf(folderId));
		url.setParameter("repositoryId", String.valueOf(getRepositoryId(folder, themeDisplay)));

		urlMenuItem.setURL(url.toString());

		logger.debug("PortletURL: " + url.toString());
		logger.debug("Returned urlMenuItem: " + urlMenuItem);
		
		logger.debug("END - getAddShortcutMenuItem with folder: " + folder);
		return urlMenuItem;
	}

	private static PortletURL getPortletURL(ThemeDisplay themeDisplay, PortletRequest portletRequest) {
		logger.debug("START - getPortletURL");
		PortletURL portletURL = PortalUtil.getControlPanelPortletURL(portletRequest, themeDisplay.getScopeGroup(),
				DLPortletKeys.DOCUMENT_LIBRARY_ADMIN, 0, 0, PortletRequest.RENDER_PHASE);

		try {
			portletURL.setWindowState(portletRequest.getWindowState());
		} catch (WindowStateException windowStateException) {
			logger.error(windowStateException, windowStateException);
		}
		logger.debug("END - getPortletURL");
		return portletURL;
	}

	private static long getFolderId(Folder folder) {
		logger.debug("START - getFolderId");
		if (folder == null) {
			return DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;
		}
		logger.debug("END - getFolderId");
		return folder.getFolderId();
	}

	private static boolean hasPermission(PermissionChecker permissionChecker, long groupId, long folderId, String actionId) {
		try {
			return DLFolderPermission.contains(permissionChecker, groupId, folderId, actionId);
		} catch (PortalException portalException) {
			logger.error(portalException, portalException);
			return false;
		}
	}

	private static long getDefaultFileEntryTypeId(long folderId) {
		try {
			return DLFileEntryTypeLocalServiceUtil.getDefaultFileEntryTypeId(folderId);
		} catch (PortalException portalException) {
			if (logger.isWarnEnabled()) {
				logger.warn("Unable to get default file entry type ID for folder " + folderId, portalException);
			}

			return DLFileEntryTypeConstants.COMPANY_ID_BASIC_DOCUMENT;
		}
	}

	private static long getRepositoryId(Folder folder, ThemeDisplay themeDisplay) {
		if (folder == null) {
			return themeDisplay.getScopeGroupId();
		}

		return folder.getRepositoryId();
	}

	private static Folder getFolder(ThemeDisplay themeDisplay, PortletRequest portletRequest) {

		Folder folder = (Folder) portletRequest.getAttribute(WebKeys.DOCUMENT_LIBRARY_FOLDER);

		if (folder != null) {
			return folder;
		}

		long rootFolderId = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;

		try {
			folder = DLAppLocalServiceUtil.getFolder(rootFolderId);
		} catch (NoSuchFolderException | PrincipalException exception) {

			// LPS-52675

			if (logger.isDebugEnabled()) {
				logger.debug(exception, exception);
			}

			folder = null;
		} catch (PortalException portalException) {
			logger.error(portalException, portalException);
		}

		return folder;
	}

	public static HashMap<String, String> getMetadata(FileEntry fileEntry) {
		HashMap<String, String> valueMetaData = new HashMap<>();
		try {
			DLFileEntry dLFileEntry = DLFileEntryLocalServiceUtil.getDLFileEntry(fileEntry.getFileEntryId());
			long dlFileEntryTypeId = dLFileEntry.getFileEntryTypeId();
			List<DLFileEntryMetadata> dlFileEntryMetadatas = DLFileEntryMetadataLocalServiceUtil
					.getFileVersionFileEntryMetadatas(fileEntry.getLatestFileVersion().getFileVersionId());
			for (DLFileEntryMetadata dlFileEntryMetadata : dlFileEntryMetadatas) {
				DDMStructure ddmStructure = getStructureByFileEntryTypeId(dlFileEntryTypeId);
				if (Validator.isNotNull(ddmStructure) && dlFileEntryMetadata.getDDMStructureId() == ddmStructure.getStructureId()) {
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
							Date date = new SimpleDateFormat("yyyy-MM-dd").parse(value.getString("en_GB"));
							valueMetaData.put(RPA_FORM_DATEUPLOADED,new SimpleDateFormat("dd/MM/yyyy").format(date));
						} else if (object.getString("name").equals(RPA_FORM_FIELDTYPE)) {
							org.json.JSONObject value = object.getJSONObject("value");
							valueMetaData.put(RPA_FORM_FIELDTYPE,value.getString("en_GB"));
						}
					}
				}
			}
		}catch (Exception e){
			logger.error(e, e);
		}

		return valueMetaData;
	}

	private static DDMStructure getStructureByFileEntryTypeId(long dlFileEntryTypeId) {
		DDMStructure ddmStructure = null;
		try {
			DLFileEntryType dlFileEntryType = DLFileEntryTypeLocalServiceUtil.getDLFileEntryType(dlFileEntryTypeId);
			List<DDMStructure> ddmStructures = dlFileEntryType.getDDMStructures();
			if(Validator.isNotNull(ddmStructures) && ddmStructures.size() >0) {
				ddmStructure = ddmStructures.get(0);
			}

		} catch(PortalException e) {
			logger.error("Error getting dlFileEntryType by dlFileEntryType: " + dlFileEntryTypeId);
		}
		return Validator.isNotNull(ddmStructure) ? ddmStructure : null;
	}

}
