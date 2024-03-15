package com.everis.rec.maintenanceforms.portlet;

import com.everis.rec.maintenanceforms.constants.MaintenanceFormsActionPortletKeys;
import com.liferay.calendar.constants.CalendarBookingConstants;
import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.service.CalendarBookingLocalServiceUtil;
import com.liferay.calendar.service.CalendarBookingServiceUtil;
import com.liferay.calendar.service.CalendarLocalServiceUtil;
import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppService;
import com.liferay.document.library.kernel.service.DLFolderLocalService;
import com.liferay.dynamic.data.mapping.model.DDMFormInstance;
import com.liferay.dynamic.data.mapping.model.DDMFormInstanceRecordVersion;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMStructureVersion;
import com.liferay.dynamic.data.mapping.service.DDMFormInstanceLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMFormInstanceRecordVersionLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMStructureVersionLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.workflow.kaleo.model.KaleoInstance;
import com.liferay.portal.workflow.kaleo.service.KaleoInstanceLocalServiceUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ProcessAction;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import rec.everis.forms.service.model.MaintenanceActivityForms;
import rec.everis.forms.service.service.MaintenanceActivityFormsLocalService;

/**
 * @author Manish Kumar Jaiswal
 */

@Component(immediate = true, property = { "com.liferay.portlet.display-category=Rec",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=MaintenanceFormsAction",
		"javax.portlet.init-param.config-template=/configuration.jsp", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + MaintenanceFormsActionPortletKeys.MAINTENANCEFORMSACTION,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class MaintenanceFormsActionPortlet extends MVCPortlet {

	/**
	 * @author Manish Kumar Jaiswal
	 */
	private static final Log _log = LogFactoryUtil.getLog(MaintenanceFormsActionPortlet.class.getName());

	private static String ROOT_FOLDER_NAME = "Form_Upload";
	private static String ROOT_FOLDER_DESCRIPTION = "This is RPA Form Folder";
	private static long PARENT_FOLDER_ID = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		createOrUpdateFormEntries(renderRequest, renderResponse);
		super.render(renderRequest, renderResponse);
	}

	/*
	 * @Override public void doView(RenderRequest renderRequest, RenderResponse
	 * renderResponse) throws IOException, PortletException {
	 * createOrUpdateFormEntries(renderRequest, renderResponse);
	 * super.doView(renderRequest, renderResponse); }
	 * 
	 */

	@ProcessAction(name = "uploadDocument")
	public void downloadDocument(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {

		try {

			Long maintenanceactivityformId = ParamUtil.getLong(actionRequest, "uidDownload");
			MaintenanceActivityForms maintenanceActivityForms = _maintenanceActivityFormsLocalService
					.fetchMaintenanceActivityForms(maintenanceactivityformId);

			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
			UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(actionRequest);
			ROOT_FOLDER_NAME = uploadPortletRequest.getParameter("uidDownload");
			ROOT_FOLDER_NAME = "FORM_FILES_" + ROOT_FOLDER_NAME;
			Map<String, String> urlMap = getAllFileLink(themeDisplay, maintenanceActivityForms);
			actionRequest.setAttribute("urlMap", urlMap);
			actionResponse.getRenderParameters().setValue("mvcPath", "/download.jsp");

			// downloadFiles( actionRequest, actionResponse,maintenanceActivityForms) ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@ProcessAction(name = "uploadDocument")
	public void uploadDocument(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(actionRequest);
		ROOT_FOLDER_NAME = uploadPortletRequest.getParameter("uid");
		Long maintenanceactivityformId = ParamUtil.getLong(uploadPortletRequest, "uid");
		MaintenanceActivityForms maintenanceActivityForms = _maintenanceActivityFormsLocalService
				.fetchMaintenanceActivityForms(maintenanceactivityformId);
		ROOT_FOLDER_NAME = "FORM_FILES_" + ROOT_FOLDER_NAME;

		_log.info("==============================Into uploadDocument");

		ROOT_FOLDER_NAME = uploadPortletRequest.getParameter("uid");
		ROOT_FOLDER_NAME = "FORM_FILES_" + ROOT_FOLDER_NAME;

		Folder folder = createFolder(actionRequest, themeDisplay, maintenanceActivityForms);
		fileUpload(actionRequest, uploadPortletRequest.getParameter("uid"), maintenanceActivityForms, folder);
		// actionResponse.getRenderParameters().setValue("mvcPath", "/view.jsp");
		/*
		 * actionResponse.setRenderParameter("orderByCol", "year");
		 * actionResponse.setRenderParameter("orderByType", "asc");
		 * actionResponse.setRenderParameter("sortingOrder", "asc");
		 * 
		 * actionResponse.getRenderParameters().setValue("orderByCol", "year");
		 * actionResponse.getRenderParameters().setValue("orderByType", "desc");
		 * actionResponse.getRenderParameters().setValue("sortingOrder", "asc");
		 */

		actionResponse.getRenderParameters().setValue("orderByCol",
				ParamUtil.getString(actionRequest, "orderByCol", "year"));
		actionResponse.getRenderParameters().setValue("orderByType",
				ParamUtil.getString(actionRequest, "orderByType", "desc"));
		actionResponse.getRenderParameters().setValue("filterStatus",
				ParamUtil.getString(actionRequest, "filterStatus", "none"));
		actionResponse.getRenderParameters().setValue("filterParty",
				ParamUtil.getString(actionRequest, "filterParty", "none"));
		actionResponse.getRenderParameters().setValue("filterYear",
				ParamUtil.getString(actionRequest, "filterYear", "none"));
		actionResponse.getRenderParameters().setValue("keywords", ParamUtil.getString(actionRequest, "keywords"));

		// actionResponse.getRenderParameters().setValue("mvcPath", "/view.jsp");
	}

	public Folder createFolder(ActionRequest actionRequest, ThemeDisplay themeDisplay,
			MaintenanceActivityForms maintenanceActivityForms) {
		boolean folderExist = isFolderExist(maintenanceActivityForms);
		Folder folder = null;
		if (!folderExist) {
			// long repositoryId = themeDisplay.getScopeGroupId();
			try {
				ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFolder.class.getName(),
						actionRequest);
				folder = _dlAppServiceUtil.addFolder(maintenanceActivityForms.getOrgSiteId(), PARENT_FOLDER_ID,
						ROOT_FOLDER_NAME, ROOT_FOLDER_DESCRIPTION, serviceContext);
				_log.info("==============================FOLDER CREATED");
			} catch (PortalException e1) {
				e1.printStackTrace();
			} catch (SystemException e1) {
				e1.printStackTrace();
			}
		} else {
			folder = getFolder(maintenanceActivityForms);
		}
		return folder;
	}

	public boolean isFolderExist(MaintenanceActivityForms maintenanceActivityForms) {
		boolean folderExist = false;
		try {
			_dlAppServiceUtil.getFolder(maintenanceActivityForms.getOrgSiteId(), PARENT_FOLDER_ID, ROOT_FOLDER_NAME);
			folderExist = true;
			_log.info("Folder already Exists....");
		} catch (Exception e) {
			_log.info(e.getMessage());
		}
		return folderExist;
	}

	public Folder getFolder(MaintenanceActivityForms maintenanceActivityForms) {
		Folder folder = null;
		try {
			folder = _dlAppServiceUtil.getFolder(maintenanceActivityForms.getOrgSiteId(), PARENT_FOLDER_ID,
					ROOT_FOLDER_NAME);
		} catch (Exception e) {
			_log.info(e.getMessage());
		}
		return folder;
	}

	public void fileUpload(ActionRequest actionRequest, String uid, MaintenanceActivityForms maintenanceActivityForms,
			Folder folder) {
		UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(actionRequest);

		File[] fileList = uploadPortletRequest.getFiles("uploadedFile" + uid);
		String[] fileNamesList = uploadPortletRequest.getFileNames("uploadedFile" + uid);

		// Folder folder = getFolder(rfiLog);
		InputStream is;
		String title, description, mimeType;
		long repositoryId;

		for (int i = 0; i < fileList.length; i++) {
			File file = fileList[i];
			title = fileNamesList[i];
			if (title != null && !title.trim().isEmpty()) {
				_log.info("==============================title " + title);
				description = title + " is added via programatically";
				repositoryId = maintenanceActivityForms.getOrgSiteId();
				try {
					mimeType = MimeTypesUtil.getContentType(file);// Files.probeContentType(file.toPath());
					is = new FileInputStream(file);
					ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(),
							actionRequest);
					_dlAppServiceUtil.addFileEntry(repositoryId, folder.getFolderId(), title, mimeType, title,
							description, "", is, file.length(), serviceContext);
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (PortalException e) {
					e.printStackTrace();
				}
			}
		}

		boolean hasFiles;
		Map<Long, Boolean> formsDocsMap = new HashMap<Long, Boolean>();

		int totalcount = _maintenanceActivityFormsLocalService.getMaintenanceActivityFormsesCount();

		List<MaintenanceActivityForms> maintenanceActivityFormsList = _maintenanceActivityFormsLocalService
				.getMaintenanceActivityFormses(0, totalcount);

		for (MaintenanceActivityForms maintenanceActivityFormsLocal : maintenanceActivityFormsList) {
			hasFiles = false;
			_log.info("====================Into render: rfiLogs.getOrgSiteId() "
					+ maintenanceActivityFormsLocal.getOrgSiteId());

			ROOT_FOLDER_NAME = "FORM_FILES_" + maintenanceActivityFormsLocal.getMaintenanceactivityformId();
			DLFolder folderTemp = _dlFolderLocalService.fetchFolder(maintenanceActivityFormsLocal.getOrgSiteId(), 0,
					ROOT_FOLDER_NAME);

			if (folderTemp != null) {
				int fileEntriesNumber = 0;
				try {
					fileEntriesNumber = _dlAppServiceUtil.getFileEntriesCount(
							maintenanceActivityFormsLocal.getOrgSiteId(), folderTemp.getFolderId());
					if (fileEntriesNumber > 0) {
						hasFiles = true;
					}
				} catch (PortalException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			formsDocsMap.put(maintenanceActivityFormsLocal.getMaintenanceactivityformId(), hasFiles);
		}
		actionRequest.setAttribute("formsDocsMap", formsDocsMap);

		/*
		 * ManagementToolbarDisplayContext
		 * rfiLogsAssignmentsManagementToolbarDisplayContext = new
		 * ManagementToolbarDisplayContext(_portal.getHttpServletRequest(actionRequest),
		 * liferayPortletRequest, liferayPortletResponse, searchContainer);
		 * 
		 * actionRequest.setAttribute(
		 * "rfiLogsAssignmentsManagementToolbarDisplayContext",
		 * rfiLogsAssignmentsManagementToolbarDisplayContext);
		 */
	}

	/*
	 * public void downloadFiles(ActionRequest actionRequest,ActionResponse
	 * actionResponse, MaintenanceActivityForms maintenanceActivityForms) throws
	 * IOException,PortletException, PortalException, SystemException { ThemeDisplay
	 * themeDisplay = (ThemeDisplay)
	 * actionRequest.getAttribute(WebKeys.THEME_DISPLAY); UploadPortletRequest
	 * uploadPortletRequest = PortalUtil.getUploadPortletRequest(actionRequest);
	 * ROOT_FOLDER_NAME = uploadPortletRequest.getParameter("uidDownload");
	 * ROOT_FOLDER_NAME = "FORM_FILES_" + ROOT_FOLDER_NAME ;
	 * 
	 * Map<String,String> urlMap = getAllFileLink(themeDisplay,
	 * maintenanceActivityForms); actionRequest.setAttribute("urlMap", urlMap);
	 * actionResponse.getRenderParameters().setValue("mvcPath", "/download.jsp"); //
	 * actionResponse.setRenderParameter("jspPage","/download.jsp"); }
	 */

	public Map<String, String> getAllFileLink(ThemeDisplay themeDisplay2,
			MaintenanceActivityForms maintenanceActivityForms) {
		Map<String, String> urlMap = new HashMap<String, String>();// key = file name ,value = url
		long repositoryId = maintenanceActivityForms.getOrgSiteId();
		try {
			Folder folder = getFolder(maintenanceActivityForms);
			List<FileEntry> fileEntries = _dlAppServiceUtil.getFileEntries(repositoryId, folder.getFolderId());
			for (FileEntry file : fileEntries) {
				// String url = themeDisplay.getPortalURL() + themeDisplay.getPathContext() +
				// "/documents/" + themeDisplay.getScopeGroupId() + "/" + file.getFolderId() +
				// "/" +file.getTitle() ;
				// urlMap.put(file.getTitle().split("\\.")[0], url);// remove jpg or pdf
//				DLFileEntry df1 = DLFileEntryLocalServiceUtil.getDLFileEntry(candidato.getFichero1());
				String path = themeDisplay2.getPortalURL() + "/c/document_library/get_file?uuid=" + file.getUuid()
						+ "&groupId=" + maintenanceActivityForms.getOrgSiteId();
				urlMap.put(file.getTitle().split("\\.")[0], path);
				// + target='_blank"><img width="35" height="35" alt=""
				// src="<%=request.getContextPath()%>/img/file-view.png"/></a>
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return urlMap;

	}

	@ProcessAction(name = "cancelDownload")
	public void cancelDownload(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
		_log.info(">>>>>>>>>>>>>>>>>>>>> Into cancelDownload to view.jsp");
		// actionResponse.getRenderParameters().setValue("mvcPath", "/view.jsp");
	}

	private void createOrUpdateFormEntries(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		String lURL = "http://localhost:8080/group/guest/~/control_panel/manage?p_p_id=com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet&"
				+ "p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_redirect"
				+ "=http%3A%2F%2Flocalhost%3A8080%2Fgroup%2Fguest%2F~%2Fcontrol_panel%2Fmanage%3Fp_p_id%3Dcom_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet"
				+ "%26p_p_lifecycle%3D0%26p_p_state%3Dmaximized%26p_p_mode%3Dview%26_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_redirect"
				+ "%3D%252Fgroup%252Fguest%252F%257E%252Fcontrol_panel%252Fmanage%253Fp_p_id%253Dcom_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet"
				+ "%2526p_p_lifecycle%253D0%2526p_p_state%253Dmaximized%2526p_v_l_s_g_id%253D37244%26_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_mvcPath%3D%252Fadmin"
				+ "%252Fview_form_instance_records.jsp%26_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_formInstanceId%3D";

		String lURL1 = "%26"
				+ "_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_displayStyle%3Dlist%26_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_orderByCol%3Dmodified-date"
				+ "%26_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_orderByType%3Dasc%26_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_cur%3D1"
				+ "%26_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_delta%3D20%26_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_navigation"
				+ "%3Dall&_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_mvcPath=%2Fadmin%2Fview_form_instance_record.jsp&_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_formInstanceId=";

		String lURL2 = "&_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_formInstanceRecordId=";

		String dURL = "https://webserver-recportal-dev.lfr.cloud/group/guest/~/control_panel/manage?p_p_id=com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_redirect=https%3A%2F%2Fwebserver-recportal-dev.lfr.cloud%3A443%2Fgroup%2Fguest%2F%7E%2Fcontrol_panel%2Fmanage%3Fp_p_id%3Dcom_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet%26p_p_lifecycle%3D0%26p_p_state%3Dmaximized%26p_p_mode%3Dview%26_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_redirect%3D%252Fgroup%252Fguest%252F%257E%252Fcontrol_panel%252Fmanage%253Fp_p_id%253Dcom_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet%2526p_p_lifecycle%253D0%2526p_p_state%253Dmaximized%2526p_v_l_s_g_id%253D37141%26_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_mvcPath%3D%252Fadmin%252Fview_form_instance_records.jsp%26_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_formInstanceId%3D";
		String dURL1 = "%26_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_displayStyle%3Dlist%26_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_orderByCol%3Dmodified-date%26_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_orderByType%3Dasc%26_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_cur%3D1%26_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_delta%3D20%26_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_navigation%3Dall&_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_mvcPath=%2Fadmin%2Fview_form_instance_record.jsp&_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_formInstanceId=";
		String dURL2 = "&_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_formInstanceRecordId=";

		String uURL = "https://webserver-recportal-uat.lfr.cloud/group/guest/~/control_panel/manage?p_p_id=com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_redirect=https%3A%2F%2Fwebserver-recportal-uat.lfr.cloud%3A443%2Fgroup%2Fguest%2F%7E%2Fcontrol_panel%2Fmanage%3Fp_p_id%3Dcom_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet%26p_p_lifecycle%3D0%26p_p_state%3Dmaximized%26p_p_mode%3Dview%26_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_redirect%3D%252Fgroup%252Fguest%252F%257E%252Fcontrol_panel%252Fmanage%253Fp_p_id%253Dcom_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet%2526p_p_lifecycle%253D0%2526p_p_state%253Dmaximized%2526p_v_l_s_g_id%253D37141%26_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_mvcPath%3D%252Fadmin%252Fview_form_instance_records.jsp%26_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_formInstanceId%3D";
		String uURL1 = "%26_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_displayStyle%3Dlist%26_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_orderByCol%3Dmodified-date%26_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_orderByType%3Dasc%26_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_cur%3D1%26_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_delta%3D20%26_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_navigation%3Dall&_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_mvcPath=%2Fadmin%2Fview_form_instance_record.jsp&_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_formInstanceId=";
		String uURL2 = "&_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_formInstanceRecordId=";

		String ppURL = "https://webserver-recportal-preprd.lfr.cloud/group/guest/~/control_panel/manage?p_p_id=com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_redirect=https%3A%2F%2Fwebserver-recportal-preprd.lfr.cloud%3A443%2Fgroup%2Fguest%2F%7E%2Fcontrol_panel%2Fmanage%3Fp_p_id%3Dcom_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet%26p_p_lifecycle%3D0%26p_p_state%3Dmaximized%26p_p_mode%3Dview%26_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_redirect%3D%252Fgroup%252Fguest%252F%257E%252Fcontrol_panel%252Fmanage%253Fp_p_id%253Dcom_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet%2526p_p_lifecycle%253D0%2526p_p_state%253Dmaximized%2526p_v_l_s_g_id%253D20121%26_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_mvcPath%3D%252Fadmin%252Fview_form_instance_records.jsp%26_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_formInstanceId%3D";
		String ppURL1 = "%26_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_displayStyle%3Dlist%26_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_orderByCol%3Dmodified-date%26_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_orderByType%3Ddesc%26_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_cur%3D1%26_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_delta%3D20%26_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_navigation%3Dall&_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_mvcPath=%2Fadmin%2Fview_form_instance_record.jsp&_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_formInstanceId=";
		String ppURL2 = "&_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_formInstanceRecordId=";

		String prodURL = "https://recportal.co.uk/group/guest/~/control_panel/manage?p_p_id=com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_redirect=https%3A%2F%2Frecportal.co.uk%3A443%2Fgroup%2Fguest%2F%7E%2Fcontrol_panel%2Fmanage%3Fp_p_id%3Dcom_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet%26p_p_lifecycle%3D0%26p_p_state%3Dmaximized%26p_p_mode%3Dview%26_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_redirect%3D%252Fgroup%252Fguest%252F%257E%252Fcontrol_panel%252Fmanage%253Fp_p_id%253Dcom_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet%2526p_p_lifecycle%253D0%2526p_p_state%253Dmaximized%2526p_v_l_s_g_id%253D20121%26_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_mvcPath%3D%252Fadmin%252Fview_form_instance_records.jsp%26_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_formInstanceId%3D";
		String prodURL1 = "%26_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_displayStyle%3Dlist%26_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_orderByCol%3Dmodified-date%26_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_orderByType%3Ddesc%26_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_cur%3D1%26_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_delta%3D20%26_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_navigation%3Dall&_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_mvcPath=%2Fadmin%2Fview_form_instance_record.jsp&_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_formInstanceId=";
		String prodURL2 = "&_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_formInstanceRecordId=";

		String configvalue = renderRequest.getPreferences().getValue("formenv", "local");
////_log.info("THIS IS CONFIGURATION VALUE------------------------------->"+configvalue);

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		List<DDMFormInstance> ddmFormInstanceListFinal = new ArrayList<DDMFormInstance>();
		Map<Long, String> formNameMap = new HashMap<>();
		Map<Long, String> formInstanceIdNameMap = new HashMap<>();
		Map<String, String> OptionsMap = new HashMap<>();

//		CODE FOR XML PARSING AND COMPARING TO GET FINAL LIST OF ddmFormInstanceListFinal STARTS

		getDdmFormInstanceList(ddmFormInstanceListFinal, formNameMap);

		for (DDMFormInstance ddmFormInstanceforoptions : ddmFormInstanceListFinal) {
			DDMStructure DDMStructureinstance = DDMStructureLocalServiceUtil
					.fetchDDMStructure(ddmFormInstanceforoptions.getStructureId());

			String optiondefinitionToParse = DDMStructureinstance.getDefinition();
			// _log.info("DDMStructureinstance.getDefinition() >" +
			// DDMStructureinstance.getDefinition());
			JSONObject jsonObjectforOption;
			try {
				jsonObjectforOption = JSONFactoryUtil.createJSONObject(optiondefinitionToParse);
				// _log.info("Json object here ->>>>>>>>>>>>>>" + jsonObjectforOption);
				JSONArray OptionfieldjsonArray = (JSONArray) jsonObjectforOption.get("fields");
				for (Object optionslevel0 : OptionfieldjsonArray) {

//					JSONArray OptionfieldjsonArray1 =  optionslevel0.get("options");
					JSONArray OptionfieldjsonArray1 = (JSONArray) ((JSONObject) optionslevel0).get("options");

					if (OptionfieldjsonArray1 != null)
						for (Object optionslevel1 : OptionfieldjsonArray1) {

							JSONObject labeljson = (JSONObject) ((JSONObject) optionslevel1).get("label");
							String optionValueString = labeljson.getString("en_GB");
							String optionReferenceString = ((JSONObject) optionslevel1).getString("reference");

							// _log.info("THIS IS optionValueString " + optionValueString);
							// _log.info("THIS IS optionReferenceString " + optionReferenceString);

							OptionsMap.put(optionReferenceString, optionValueString);
						}
				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

//			CODE FOR XML PARSING AND COMPARING TO GET FINAL LIST OF ddmFormInstanceListFinal ENDS

//		CODE TO GET KEY FOR YEAR  STARTS (FROM ddmFormInstanceListFinal)

		HashSet<String> nameKeyCodeSet = getKeyForYear(ddmFormInstanceListFinal);

//		CODE TO GET DATA FROM ddmforminstancerecordversion STARTS

		int kaleoInstancesCount = KaleoInstanceLocalServiceUtil.getKaleoInstancesCount();
		List<KaleoInstance> kaleoInstanceList = KaleoInstanceLocalServiceUtil.getKaleoInstances(0, kaleoInstancesCount);
		List<KaleoInstance> kaleoInstanceListFinal = new ArrayList<KaleoInstance>();

		int ddmFormInstanceRecordVersionsCount = DDMFormInstanceRecordVersionLocalServiceUtil
				.getDDMFormInstanceRecordVersionsCount();
		List<DDMFormInstanceRecordVersion> ddmFormInstanceRecordVersionslist = DDMFormInstanceRecordVersionLocalServiceUtil
				.getDDMFormInstanceRecordVersions(0, ddmFormInstanceRecordVersionsCount);
		List<DDMFormInstanceRecordVersion> ddmFormInstanceRecordVersionFinal = new ArrayList<DDMFormInstanceRecordVersion>();
		for (DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion : ddmFormInstanceRecordVersionslist) {
//			TAKING OUT ONLY RELEVENT FORM DETAILS FROM ddmFormInstanceRecordVersion based on forminstanceID corrosponding to each 4 forms

			if (formNameMap.containsKey(ddmFormInstanceRecordVersion.getFormInstanceId())) {

//		TAKING OUT ONLY RELEVENT SUBMITTED FORM INSTANCE  DETAILS FROM KaleoInstance
				for (KaleoInstance kaleoInstance : kaleoInstanceList) {
//		CODE FOR FILTERING THE RECORDS FOR CURRENT SITE
					if ((kaleoInstance.getClassPK() == ddmFormInstanceRecordVersion.getFormInstanceRecordVersionId())
							&& (themeDisplay.getScopeGroupId() == kaleoInstance.getGroupId())) {
						kaleoInstanceListFinal.add(kaleoInstance);
						formInstanceIdNameMap.put(kaleoInstance.getClassPK(),
								formNameMap.get(ddmFormInstanceRecordVersion.getFormInstanceId()));

						// _log.info("kaleoInstance.getWorkflowContext()............." +
						// kaleoInstance.getWorkflowContext());
					}

				}

			}
		}
	

		for (KaleoInstance kaleoInstance : kaleoInstanceListFinal) {

			MaintenanceActivityForms _maintenanceActivityForms = null;

			String stringToParse = kaleoInstance.getWorkflowContext();
			// _log.info("THIS FORM VERSION IS SUBMITTED AT THE DATE>" +
			// kaleoInstance.getCreateDate());

			if ((_maintenanceActivityFormsLocalService.findByformInstanceRecordVersionId(kaleoInstance.getClassPK()))
					.size() == 0) {
				_maintenanceActivityForms = _maintenanceActivityFormsLocalService.createMaintenanceActivityForms(
						_counterLocalService.increment(MaintenanceActivityForms.class.getName()));
			} else {
				_maintenanceActivityForms = _maintenanceActivityFormsLocalService
						.findByformInstanceRecordVersionId(kaleoInstance.getClassPK()).get(0);

			}

			// _log.info("EXPERIMENTAL SUBMITTED DATE===============>" +
			// DateUtil.getDate(kaleoInstance.getCreateDate(), "dd/MM/yyyy",
			// themeDisplay.getLocale()));

			_maintenanceActivityForms.setSubmitDate(kaleoInstance.getCreateDate());
			_maintenanceActivityForms.setSubmitDateFormatted(
					DateUtil.getDate(kaleoInstance.getCreateDate(), "dd/MM/yyyy", themeDisplay.getLocale()));
			_maintenanceActivityForms.setFormInstanceRecordVersionId(kaleoInstance.getClassPK());
			_maintenanceActivityForms.setGroupId(kaleoInstance.getGroupId());
			_maintenanceActivityForms.setOrgSiteId(kaleoInstance.getGroupId());
			_maintenanceActivityForms.setFormType(formInstanceIdNameMap.get(kaleoInstance.getClassPK()));

			DDMFormInstanceRecordVersion DDMFormInstanceRecordVersionforurl = DDMFormInstanceRecordVersionLocalServiceUtil
					.fetchDDMFormInstanceRecordVersion(kaleoInstance.getClassPK());

			if (configvalue != null && !configvalue.trim().isEmpty()) {
				if (configvalue.trim().equalsIgnoreCase("local")) {
					_maintenanceActivityForms.setViewUrl(lURL + DDMFormInstanceRecordVersionforurl.getFormInstanceId()
							+ lURL1 + DDMFormInstanceRecordVersionforurl.getFormInstanceId() + lURL2
							+ DDMFormInstanceRecordVersionforurl.getFormInstanceRecordId());
					// _log.info(" CONFIGURATION VALUE------------------------------->LOCAL");
				} else if (configvalue.trim().equalsIgnoreCase("dev")) {
					_maintenanceActivityForms.setViewUrl(dURL + DDMFormInstanceRecordVersionforurl.getFormInstanceId()
							+ dURL1 + DDMFormInstanceRecordVersionforurl.getFormInstanceId() + dURL2
							+ DDMFormInstanceRecordVersionforurl.getFormInstanceRecordId());
					// _log.info(" CONFIGURATION VALUE------------------------------->DEV");
				} else if (configvalue.trim().equalsIgnoreCase("uat")) {
					_maintenanceActivityForms.setViewUrl(uURL + DDMFormInstanceRecordVersionforurl.getFormInstanceId()
							+ uURL1 + DDMFormInstanceRecordVersionforurl.getFormInstanceId() + uURL2
							+ DDMFormInstanceRecordVersionforurl.getFormInstanceRecordId());
					// _log.info(" CONFIGURATION VALUE------------------------------->UAT");
				} else if (configvalue.trim().equalsIgnoreCase("preprod")) {
					_maintenanceActivityForms.setViewUrl(ppURL + DDMFormInstanceRecordVersionforurl.getFormInstanceId()
							+ ppURL1 + DDMFormInstanceRecordVersionforurl.getFormInstanceId() + ppURL2
							+ DDMFormInstanceRecordVersionforurl.getFormInstanceRecordId());
					// _log.info(" CONFIGURATION VALUE------------------------------->PREPROD");
				} else if (configvalue.trim().equalsIgnoreCase("prod")) {
					_maintenanceActivityForms
							.setViewUrl(prodURL + DDMFormInstanceRecordVersionforurl.getFormInstanceId() + prodURL1
									+ DDMFormInstanceRecordVersionforurl.getFormInstanceId() + prodURL2
									+ DDMFormInstanceRecordVersionforurl.getFormInstanceRecordId());
					// _log.info(" CONFIGURATION VALUE------------------------------->PROD");
				}
			} else {
				_maintenanceActivityForms.setViewUrl(lURL + DDMFormInstanceRecordVersionforurl.getFormInstanceId()
						+ lURL1 + DDMFormInstanceRecordVersionforurl.getFormInstanceId() + lURL2
						+ DDMFormInstanceRecordVersionforurl.getFormInstanceRecordId());
				// _log.info(" CONFIGURATION VALUE------------------------------->DEFAULT
				// LOCAL");
			}

			// TODO REMOVE AS THIS IS REDUDENT CODE
			if (kaleoInstance.getCompleted() == false) {
				// _log.info("THIS FORM VERSION IS SUBMITTED BUT NOT APPROVED>" +
				// kaleoInstance.getClassPK());
			} else {
				// _log.info("THIS FORM VERSION IS SUBMITTED " + kaleoInstance.getClassPK());
				// _log.info("THIS FORM VERSION IS APPROVED AT THE DATE>" +
				// kaleoInstance.getCompletionDate());
				_maintenanceActivityForms.setApprovalDate(kaleoInstance.getCompletionDate());
				_maintenanceActivityForms.setApprovalDateFormatted(
						DateUtil.getDate(kaleoInstance.getCompletionDate(), "dd/MM/yyyy", themeDisplay.getLocale()));

			}

			// _log.info("JSONSTRING =====> " + stringToParse);
			try {
				JSONObject jsonObject = JSONFactoryUtil.createJSONObject(stringToParse);

				JSONObject mapjsonObject = (JSONObject) jsonObject.get("map");
				String transitionName = mapjsonObject.getString("transitionName");

				JSONObject serviceContextjson = (JSONObject) mapjsonObject.get("serviceContext");
				JSONObject serializablejson = (JSONObject) serviceContextjson.get("serializable");
				JSONObject attributesJson = (JSONObject) serializablejson.get("attributes");
				JSONObject mapJsontwo = (JSONObject) attributesJson.get("map");

				Iterator<String> mapKeys = mapJsontwo.keys();

				while (mapKeys.hasNext()) {
					String mapKey = mapKeys.next();

					for (String codeset : nameKeyCodeSet) {

						if (mapKey.contains(codeset)) {
							// _log.info("FOUNDDDDDDDD MAP KEY=============" + mapKey);
							String mapKeyValueStringYEAR = mapJsontwo.getString(mapKey);
							// _log.info("FOUNDDDDDDDD YEAR VALUE=============" + mapKeyValueStringYEAR);

							if (!(mapKeyValueStringYEAR.equals(null) || mapKeyValueStringYEAR.equals(""))) {
								Date yearDate;
								try {
									String mapKeyOptionValueStringYEAR = OptionsMap.get(mapKeyValueStringYEAR);
									yearDate = new SimpleDateFormat("yyyy").parse(mapKeyOptionValueStringYEAR);
									// _log.info("yearDate================" + DateUtil.getYear(yearDate));
									_maintenanceActivityForms.setYear(DateUtil.getYear(yearDate));

								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							}

						}

					}
				}
//				"map"//"serviceContext"//"serializable"//"attributes"//map//"ddm$$Field90475170$1sHiKiuq$0$$en_US":"submitted time check" 	
				String formInstanceId = mapJsontwo.getString("formInstanceId");

				// _log.info("FORMINSTANCEID >" + formInstanceId);
				if (transitionName != null && !transitionName.isEmpty()) {
					// _log.info("THIS IS TRANSITION NAME============================ >" +
					// transitionName);
					if (transitionName.trim().equalsIgnoreCase("approve")) {
						transitionName = "Approved";
					} else if (transitionName.trim().equalsIgnoreCase("reject")) {
						transitionName = "Rejected";
					}
					// _log.info("TRANSITIONNAME >" + transitionName);
					_maintenanceActivityForms.setStatus(transitionName);
				} else {
					// _log.info("TRANSITIONNAME >SUBMITTED");
					_maintenanceActivityForms.setStatus("Submitted");
				}

//				//_log.info("transitionName//////////"+jsonObject.get("transitionName").toString());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if ((_maintenanceActivityFormsLocalService.findByformInstanceRecordVersionId(kaleoInstance.getClassPK()))
					.size() == 0) {
				_maintenanceActivityFormsLocalService.addMaintenanceActivityForms(_maintenanceActivityForms);
				

			} else {
				_maintenanceActivityFormsLocalService.updateMaintenanceActivityForms(_maintenanceActivityForms);
				

			}

			// _log.info(_maintenanceActivityForms.getViewUrl());

		}
		// CODE TO GET DATA FROM ddmforminstancerecordversion ENDS

		int listCount = DDMFormInstanceRecordVersionLocalServiceUtil.getDDMFormInstanceRecordVersionsCount();

		// _log.info("counttttttttttttttttttttttt" + listCount);
		// _log.info("Groupiddddddddddd" + themeDisplay.getScopeGroupId());

		// TODO Auto-generated method stub
		// super.render(renderRequest, renderResponse);
	}

	private HashSet<String> getKeyForYear(List<DDMFormInstance> ddmFormInstanceListFinal) {

		String nameKeyValue = "";
		String nameKeyValue1 = "";
		HashSet<String> nameKeyCodeSet = new HashSet<String>();

		int DDMStructureVersionsCount = DDMStructureVersionLocalServiceUtil.getDDMStructureVersionsCount();
		List<DDMStructureVersion> DDMStructureVersionList = DDMStructureVersionLocalServiceUtil
				.getDDMStructureVersions(0, DDMStructureVersionsCount);

		for (DDMStructureVersion ddmStructureVersion : DDMStructureVersionList) {
			for (DDMFormInstance ddmFormInstance : ddmFormInstanceListFinal) {
				if (ddmStructureVersion.getStructureId() == ddmFormInstance.getStructureId()) {

					try {

						DDMStructureVersion ddmStructurelatest = DDMStructureVersionLocalServiceUtil
								.getLatestStructureVersion(ddmStructureVersion.getStructureId());

						String definitionToParse = ddmStructurelatest.getDefinition();
						// _log.info("ddmStructureVersion.getDefinition() >" +
						// ddmStructurelatest.getDefinition());
						JSONObject jsonObject;
						jsonObject = JSONFactoryUtil.createJSONObject(definitionToParse);
						JSONArray fieldjsonArray = (JSONArray) jsonObject.get("fields");

						if (fieldjsonArray != null)
							for (Object level0 : fieldjsonArray) {

								JSONObject labeljson = (JSONObject) ((JSONObject) level0).get("label");
								String KeyString = labeljson.getString("en_GB");

								// _log.info("THIS IS THE LATEST KEY " + KeyString);
								if (KeyString.trim().equalsIgnoreCase(
										"Please indicate the calendar year for which you are completing this form")) {
									nameKeyValue = ((JSONObject) level0).getString("name");
									// _log.info("THIS IS THE KEY LATEST CODE " + nameKeyValue);
									nameKeyCodeSet.add(nameKeyValue);
								}

								JSONArray fieldjsonArrayleve1 = (JSONArray) ((JSONObject) level0).get("nestedFields");

								if (fieldjsonArrayleve1 != null)
									for (Object level1 : fieldjsonArrayleve1) {

										JSONObject labeljson1 = (JSONObject) ((JSONObject) level1).get("label");
										String KeyString1 = labeljson1.getString("en_GB");

										// _log.info("THIS IS THE KEY at level 1 " + KeyString1);
										if (KeyString1.trim().equalsIgnoreCase(
												"Please indicate the calendar year for which you are completing this form")) {
											nameKeyValue1 = ((JSONObject) level1).getString("name");
											// _log.info("THIS IS THE KEY CODE at level 1 " + nameKeyValue1);
											nameKeyCodeSet.add(nameKeyValue1);
										}
									}
							}

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		}
		return nameKeyCodeSet;
	}

	private void getDdmFormInstanceList(List<DDMFormInstance> ddmFormInstanceListFinal, Map<Long, String> formNameMap)
			throws IOException {
		try {

			int ddmFormInstancesCount = DDMFormInstanceLocalServiceUtil.getDDMFormInstancesCount();
			List<DDMFormInstance> ddmFormInstanceList = DDMFormInstanceLocalServiceUtil.getDDMFormInstances(0,
					ddmFormInstancesCount);
	
			for (DDMFormInstance ddmFormInstance : ddmFormInstanceList) {

				InputSource xmlFile = new InputSource(new StringReader(ddmFormInstance.getName()));
				Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlFile);

				String formname = doc.getElementsByTagName("Name").item(0).getTextContent();

				// _log.info("FORM NAME >" + formname);

				if (formname.equalsIgnoreCase(
						"Maintenance of Qualification: Annual Statement and Smart Meter Installation Schedule Self Declaration")
						|| formname
								.equalsIgnoreCase("Maintenance of Qualification: System or Process Change Disclosure")
						|| formname.equalsIgnoreCase("Maintenance of Qualification: External Assessment")
						|| formname.equalsIgnoreCase("Maintenance of Qualification: Compliance Statement")) {
					ddmFormInstanceListFinal.add(ddmFormInstance);
					formNameMap.put(ddmFormInstance.getFormInstanceId(), formname);
				}

			}

		} catch (SAXException | ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	@Reference
	protected MaintenanceActivityFormsLocalService _maintenanceActivityFormsLocalService;

	@Reference
	protected CounterLocalService _counterLocalService;

	@Reference
	protected DLFolderLocalService _dlFolderLocalService;

	@Reference
	protected DLAppService _dlAppServiceUtil;

}
