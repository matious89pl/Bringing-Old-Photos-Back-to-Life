package com.everis.rec.rfi.logs.portlet;

import com.everis.cproposal.service.recFormArticleLocalServiceUtil;
import com.everis.messages.service.builder.service.MessagesLocalServiceUtil;
import com.everis.rec.rfi.logs.constants.RecRfiLogsWebPortletKeys;
import com.everis.rec.rfilogs.model.RfiLogs;
import com.everis.rec.rfilogs.service.RfiLogsLocalService;
import com.everis.rec.rfilogs.service.RfiLogsLocalServiceUtil;
import com.liferay.calendar.constants.CalendarBookingConstants;
import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.service.CalendarBookingServiceUtil;
import com.liferay.calendar.service.CalendarLocalServiceUtil;
import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLAppService;
import com.liferay.document.library.kernel.service.DLFolderLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.UserGroupRole;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.PortletURL;
import javax.portlet.ProcessAction;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Miquel
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=Rec",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=RecRfiLogsWeb", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp", "javax.portlet.init-param.config-template=/config.jsp",
		"javax.portlet.init-param.add-process-action-success-action=false",
		"javax.portlet.name=" + RecRfiLogsWebPortletKeys.RECRFILOGSWEB,
		"javax.portlet.resource-bundle=content.Language", "javax.portlet.security-role-ref=power-user,user",
		"mvc.command.name=" + RecRfiLogsWebPortletKeys.VIEW_LANDING }, service = Portlet.class)
public class RecRfiLogsWebPortlet extends MVCPortlet {

	// define log for this class
	private static final Log _log = LogFactoryUtil.getLog(RecRfiLogsWebPortlet.class.getName());

	private static String ROOT_FOLDER_NAME = "File_Upload";
	private static String ROOT_FOLDER_DESCRIPTION = "This is RPA Folder";
	private static long PARENT_FOLDER_ID = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;

	// static String link =
	// "rfis-queries?p_p_id=com_everis_rec_rfi_logs_RecRfiLogsWebPortlet_INSTANCE_iVnh0wJJhMYq&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&_com_everis_rec_rfi_logs_RecRfiLogsWebPortlet_INSTANCE_iVnh0wJJhMYq_mvcPath=%2Fdetail_view_rfi.jsp&rfiId=";

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		super.doView(renderRequest, renderResponse);
	}

	@ProcessAction(name = "uploadDocument")
	public void uploadDocument(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException, PortalException {

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(actionRequest);
		ROOT_FOLDER_NAME = uploadPortletRequest.getParameter("uid");
		Long rfilogId = ParamUtil.getLong(uploadPortletRequest, "uid");
		RfiLogs rfiLog = _rfiLogsLocalService.fetchRfiLogs(rfilogId);
		ROOT_FOLDER_NAME = "RFI_FILES_" + ROOT_FOLDER_NAME;
		String submitDocType = uploadPortletRequest.getParameter("submitDocType");

		if (submitDocType.equalsIgnoreCase("download")) {
			try {
				downloadFiles(actionRequest, actionResponse, rfiLog);
			} catch (SystemException e) {
				// TODO Aut-generated catch block
				e.printStackTrace();
			} catch (PortalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			_log.info("==============================Into uploadDocument");

			ROOT_FOLDER_NAME = uploadPortletRequest.getParameter("uid");
			ROOT_FOLDER_NAME = "RFI_FILES_" + ROOT_FOLDER_NAME;

			Folder folder = createFolder(actionRequest, themeDisplay, rfiLog);
			fileUpload(actionRequest, uploadPortletRequest.getParameter("uid"), rfiLog, folder);

			actionResponse.getRenderParameters().setValue("mvcPath", "/view.jsp");

			actionResponse.setRenderParameter("orderByCol", "specificParty");
			actionResponse.setRenderParameter("orderByType", "asc");
			actionResponse.setRenderParameter("sortingOrder", "asc");

			actionResponse.getRenderParameters().setValue("orderByCol", "specificParty");
			actionResponse.getRenderParameters().setValue("orderByType", "asc");
			actionResponse.getRenderParameters().setValue("sortingOrder", "asc");
			SessionMessages.add(actionRequest, "successFile");
		}
	}

	public Folder createFolder(ActionRequest actionRequest, ThemeDisplay themeDisplay, RfiLogs rfiLog) {
		boolean folderExist = isFolderExist(rfiLog);
		Folder folder = null;
		if (!folderExist) {
			// long repositoryId = themeDisplay.getScopeGroupId();
			try {
				ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFolder.class.getName(),
						actionRequest);
				folder = _dlAppServiceUtil.addFolder(rfiLog.getOrgSiteId(), PARENT_FOLDER_ID, ROOT_FOLDER_NAME,
						ROOT_FOLDER_DESCRIPTION, serviceContext);
				_log.info("==============================FOLDER CREATED");
			} catch (PortalException e1) {
				e1.printStackTrace();
			} catch (SystemException e1) {
				e1.printStackTrace();
			}
		} else {
			folder = getFolder(rfiLog);
		}
		return folder;
	}

	public boolean isFolderExist(RfiLogs rfiLog) {
		boolean folderExist = false;
		try {
			_dlAppServiceUtil.getFolder(rfiLog.getOrgSiteId(), PARENT_FOLDER_ID, ROOT_FOLDER_NAME);
			folderExist = true;
			_log.info("Folder already Exists....");
		} catch (Exception e) {
			_log.info(e.getMessage());
		}
		return folderExist;
	}

	public Folder getFolder(RfiLogs rfiLog) {
		Folder folder = null;
		try {
			folder = _dlAppServiceUtil.getFolder(rfiLog.getOrgSiteId(), PARENT_FOLDER_ID, ROOT_FOLDER_NAME);
		} catch (Exception e) {
			_log.info(e.getMessage());
		}
		return folder;
	}

	public void fileUpload(ActionRequest actionRequest, String uid, RfiLogs rfiLog, Folder folder)
			throws PortalException, IOException {
		_log.debug("==============================fileUpload RFI==============================");
		UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(actionRequest);

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long userId = themeDisplay.getUserId();
		boolean sendUsers = false;

		InputStream[] fileList = uploadPortletRequest.getFilesAsStream("uploadedFile" + uid);
		String[] fileNamesList = uploadPortletRequest.getFileNames("uploadedFile" + uid);

		String title, description, mimeType;
		long repositoryId;
		String urlPortal = themeDisplay.getURLPortal().concat(HtmlUtil.escape("/group/"));

		FileEntry fileEntrySearch = null;

		if (fileNamesList[0].length() > 0) {

			_log.info("************************ fileList: " + fileList.length);
			for (int i = 0; i < fileList.length; i++) {
				InputStream is = fileList[i];
				int length = is.available();
				title = fileNamesList[i];
				_log.info("************************ title: " + title);
				if (title != null && !title.trim().isEmpty()) {

					description = title + " is added via programatically";
					repositoryId = rfiLog.getOrgSiteId();
					try {
						mimeType = MimeTypesUtil.getContentType(is, title);
						ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(),
								actionRequest);
						/*
						 * ****************************************************************************
						 */

						long folderId = folder.getFolderId();
						try {
							fileEntrySearch = DLAppLocalServiceUtil.getFileEntry(repositoryId, folderId, title);
							if (Validator.isNotNull(fileEntrySearch)) {
								String[] parts = title.split("\\.");
								Date date = new Date();
								title = parts[0] + " " + date.hashCode() + "." + parts[1];
								fileEntrySearch = DLAppLocalServiceUtil.getFileEntry(repositoryId, folderId, title);
							}
						} catch (PortalException e) {
							_log.info("No file existing with name : " + title);
						}

						/*
						 * ****************************************************************************
						 */

						_dlAppServiceUtil.addFileEntry(repositoryId, folder.getFolderId(), title, mimeType, title,
								description, "", is, length, serviceContext);

					} catch (PortalException e) {
						e.printStackTrace();
					}

					_log.info("==============================Notification 58 ");
					_log.debug("Object RFI: " + rfiLog);
					String titleRFI = rfiLog.getTitle();

					_log.debug("Title1: " + titleRFI);

					List<Role> roleUser = UserLocalServiceUtil.getUser(userId).getRoles();

					for (Role role : roleUser) {
						if (role.getName().equals("RPA")) {
							_log.debug("Role to send: " + role.getName());
							try {
								_log.debug("Sending notification 58...");
								MessagesLocalServiceUtil.sendNotification58(themeDisplay.getUserId(), repositoryId,
										titleRFI, urlPortal, themeDisplay.getCompanyId());
							} catch (Exception e) {
								_log.error("Error sending notification 58 " + e);
							}

						}
					}

					_log.info("==============================Notification 59 ");

					List<UserGroupRole> userGroupRoles = UserGroupRoleLocalServiceUtil
							.getUserGroupRoles(themeDisplay.getUserId(), themeDisplay.getSiteGroupId());

					for (UserGroupRole userGroupRole : userGroupRoles) {

						if (userGroupRole.getRole().getName().equals("REC Contract Managers")
								|| userGroupRole.getRole().getName().equals("REC_Performance_Assurance")
								|| userGroupRole.getRole().getName().equals("Master Administrative User")) {

							sendUsers = true;

						}
					}

					_log.info("Boolean sendUsers: " + sendUsers);
					if (sendUsers) {
						try {
							_log.debug("Sending notification 59...");
							MessagesLocalServiceUtil.sendNotification59(themeDisplay.getUserId(),
									themeDisplay.getSiteGroupId(), titleRFI, urlPortal, themeDisplay.getCompanyId());
						} catch (Exception e) {
							_log.error("Error sending notification 59 " + e);
						}
					}
				}
			}

		} else {
			_log.info("noFile: ");
			SessionErrors.add(actionRequest, "noFile");
		}

		boolean hasFiles;
		Map<Long, Boolean> rfiLogsDocsMap = new HashMap<Long, Boolean>();

		List<RfiLogs> rfiLogsList = (List<RfiLogs>) actionRequest.getPortletSession().getAttribute("rfiLogsList",
				PortletSession.APPLICATION_SCOPE);

		for (RfiLogs rfiLogs : rfiLogsList) {
			hasFiles = false;
			_log.debug("====================Into render: rfiLogs.getOrgSiteId() " + rfiLogs.getOrgSiteId());

			ROOT_FOLDER_NAME = "RFI_FILES_" + rfiLogs.getRfilogId();
			DLFolder folderTemp = _dlFolderLocalService.fetchFolder(rfiLogs.getOrgSiteId(), 0, ROOT_FOLDER_NAME);

			if (folderTemp != null) {
				int fileEntriesNumber = 0;
				try {
					fileEntriesNumber = _dlAppServiceUtil.getFileEntriesCount(rfiLogs.getOrgSiteId(),
							folderTemp.getFolderId());
					if (fileEntriesNumber > 0) {
						hasFiles = true;
					}
				} catch (PortalException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			rfiLogsDocsMap.put(rfiLogs.getRfilogId(), hasFiles);
		}
		actionRequest.setAttribute("rfiLogsDocsMap", rfiLogsDocsMap);

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

	public void downloadFiles(ActionRequest actionRequest, ActionResponse actionResponse, RfiLogs rfiLog)
			throws IOException, PortletException, PortalException, SystemException {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(actionRequest);
		ROOT_FOLDER_NAME = uploadPortletRequest.getParameter("uid");
		ROOT_FOLDER_NAME = "RFI_FILES_" + ROOT_FOLDER_NAME;

		Map<String, String> urlMap = getAllFileLink(themeDisplay, rfiLog);
		actionRequest.setAttribute("urlMap", urlMap);
		actionResponse.getRenderParameters().setValue("mvcPath", "/download.jsp");
//			  actionResponse.setRenderParameter("jspPage","/download.jsp");
		SessionMessages.add(actionRequest, "success");
	}

	public Map<String, String> getAllFileLink(ThemeDisplay themeDisplay2, RfiLogs rfiLog) {
		Map<String, String> urlMap = new HashMap<String, String>();// key = file name ,value = url
		long repositoryId = rfiLog.getOrgSiteId();
		try {
			Folder folder = getFolder(rfiLog);
			List<FileEntry> fileEntries = _dlAppServiceUtil.getFileEntries(repositoryId, folder.getFolderId());
			for (FileEntry file : fileEntries) {
				// String url = themeDisplay.getPortalURL() + themeDisplay.getPathContext() +
				// "/documents/" + themeDisplay.getScopeGroupId() + "/" + file.getFolderId() +
				// "/" +file.getTitle() ;
				// urlMap.put(file.getTitle().split("\\.")[0], url);// remove jpg or pdf
//				DLFileEntry df1 = DLFileEntryLocalServiceUtil.getDLFileEntry(candidato.getFichero1());
				String path = themeDisplay2.getPortalURL() + "/c/document_library/get_file?uuid=" + file.getUuid()
						+ "&groupId=" + rfiLog.getOrgSiteId();
				urlMap.put(file.getTitle().split("\\.")[0], path);
				// + target='_blank"><img width="35" height="35" alt=""
				// src="<%=request.getContextPath()%>/img/file-view.png"/></a>
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return urlMap;

	}

	/**
	 * This method is used to Add the records for |REC Mantainance and Activity
	 * Records|
	 * 
	 * @param actionRequest  This is the ActionRequest of the Portlet .
	 * @param actionResponse This is the ActionResponse of the Portlet .
	 * @return void This returns nothing.
	 * 
	 * @author Manish Kumar Jaiswal
	 */
	@ProcessAction(name = "addRFI")
	public void addRFI(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
		_log.debug("==============================Into addRFI");
		actionResponse.getRenderParameters().setValue("mvcPath", "/add_rfi.jsp");
	}

	/**
	 * This method is used to Add the records for |REC Mantainance and Activity
	 * Records|
	 * 
	 * @param actionRequest  This is the ActionRequest of the Portlet .
	 * @param actionResponse This is the ActionResponse of the Portlet .
	 * @return void This returns nothing.
	 * 
	 * @author Manish Kumar Jaiswal
	 */
	@ProcessAction(name = "redirectToAggregate")
	public void redirectToAggregate(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {

		_log.debug("==============================Into addRFI for redirect");
		// TODO > Add configuration feature for this
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		String pageName = "/rfi-aggregate";
		String portletName = "";

		long plid = 0L;
		try {
			plid = LayoutLocalServiceUtil.getFriendlyURLLayout(themeDisplay.getScopeGroupId(), true, pageName)
					.getPlid();
		} catch (Exception e) {
			e.printStackTrace();
		}

		PortletURL redirectURL = PortletURLFactoryUtil.create(PortalUtil.getHttpServletRequest(actionRequest),
				portletName, plid, PortletRequest.RENDER_PHASE);
		// redirectURL.setParameter("data", "This Value Comes From Welcome Page"); //
		// set required parameter that you need in doView of another Portlet
		actionResponse.sendRedirect(redirectURL.toString());
	}

	/**
	 * This method is used to Add the records for |REC Mantainance and Activity
	 * Records|
	 * 
	 * @param actionRequest  This is the ActionRequest of the Portlet .
	 * @param actionResponse This is the ActionResponse of the Portlet .
	 * @return void This returns nothing.
	 * 
	 * @author Manish Kumar Jaiswal
	 */

	@ProcessAction(name = "addRFISubmit")
	public void addRFISubmit(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {

		_log.debug("=============================Into addRFISubmit");
		ParamUtil.print(actionRequest);

		try {

			String duedate = ParamUtil.getString(actionRequest, "duedate");
			String status = ParamUtil.getString(actionRequest, "status");

			String reqId = ParamUtil.getString(actionRequest, "reqId");
			String title = ParamUtil.getString(actionRequest, "title");
			String reqDesc = ParamUtil.getString(actionRequest, "reqDesc");
			String rationale = ParamUtil.getString(actionRequest, "rationale");

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate convertedldtfrmt = LocalDate.parse(duedate, formatter);
			duedate = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH).format(convertedldtfrmt);

			_log.debug(">>>>>>>>>>>>>>>>>>>>>duedate " + duedate);
			_log.debug(">>>>>>>>>>>>>>>>>>>>>status " + status);

			RfiLogs _rfiLog = _rfiLogsLocalService
					.createRfiLogs(_counterLocalService.increment(RfiLogs.class.getName()));

			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			long companyId = themeDisplay.getCompanyId();
			long userId = themeDisplay.getUserId();

			PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

			_rfiLog.setUserId(userId);
			_rfiLog.setCompanyId(themeDisplay.getCompanyId());
			_rfiLog.setUserName(UserLocalServiceUtil.getUser(userId).getFullName());
			_rfiLog.setCreateDate(new Date());
			_rfiLog.setModifiedDate(new Date());
			_rfiLog.setOrgSiteId(themeDisplay.getScopeGroupId());
			_rfiLog.setSpecificParty(themeDisplay.getScopeGroupName());
			_rfiLog.setReqId(Long.parseLong(reqId));
			_rfiLog.setTitle(title);
			_rfiLog.setDueDate(duedate);
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			_rfiLog.setDueDateFormated(format.parse(duedate));
			_rfiLog.setStatus(status);
			_rfiLog.setReqDesc(reqDesc);
			_rfiLog.setRationale(rationale);

			_log.info("themeDisplay.getSiteGroupName() ==== " + themeDisplay.getSiteGroupName());

			ServiceContext serviceContext = ServiceContextFactory.getInstance(RfiLogs.class.getName(), actionRequest);
			String dueDate = _rfiLog.getDueDate();
			String bookingLink = "rfis-queries";
			long bookingID = bookingAdd(Locale.UK, title, bookingLink, _rfiLog, actionRequest, dueDate);
			_rfiLog.setCalendarBookingId(bookingID);

			_rfiLogsLocalService.newRFI(userId, _rfiLog, serviceContext);
			SessionMessages.add(actionRequest, "success");
			try {
				String urlPortal = themeDisplay.getURLPortal().concat(HtmlUtil.escape("/group/"));

				MessagesLocalServiceUtil.sendNotification48(themeDisplay.getUserId(), themeDisplay.getSiteGroupId(),
						title, urlPortal, companyId);

			} catch (Exception e) {

				_log.error("Error sending Notification48", e);
			}

		} catch (Exception e) {
			_log.error("Error adding RFI", e);
		}
	}

	/**
	 * This method is used to Add the records for |REC Mantainance and Activity
	 * Records|
	 * 
	 * @param actionRequest  This is the ActionRequest of the Portlet .
	 * @param actionResponse This is the ActionResponse of the Portlet .
	 * @return void This returns nothing.
	 * 
	 * @author Manish Kumar Jaiswal
	 */
	@ProcessAction(name = "deleteRFI")
	public void deleteRFI(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {

		_log.debug(">>>>>>>>>>>>>>>>>>>>>Into deleteRFI");
		String mid = ParamUtil.get(actionRequest, "mid", "");
		String submitType = ParamUtil.get(actionRequest, "submitType", "");

		_log.debug("mid parameter is ==>" + mid);
		_log.debug("submitType parameter is ==>" + submitType);

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		RfiLogs rfiForEdit = _rfiLogsLocalService.fetchRfiLogs(Long.parseLong(mid));
		String originalRFITitle = rfiForEdit.getTitle();

		try {
			if ((submitType != null && !submitType.trim().isEmpty()) && (mid != null && !mid.trim().isEmpty())) {
				if (submitType.equalsIgnoreCase("edit")) {

					String duedateedit = rfiForEdit.getDueDate();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					LocalDate convertedldtfrmt = LocalDate.parse(duedateedit, formatter);
					duedateedit = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(convertedldtfrmt);
					rfiForEdit.setDueDate(duedateedit);
					actionRequest.setAttribute("rfiForEdit", rfiForEdit);
					actionResponse.getRenderParameters().setValue("mvcPath", "/edit_rfi.jsp");

				} else if (submitType.equalsIgnoreCase("delete")) {

					RfiLogs toDelete = RfiLogsLocalServiceUtil.fetchRfiLogs(Long.parseLong(mid));
					deleteBooking(themeDisplay, toDelete);
					_rfiLogsLocalService.deleteRfiLogs(Long.parseLong(mid));
					SessionMessages.add(actionRequest, "success");

				}
			}

			_log.debug(">>>>>>>>>>>>>>>>>>>>>Into TRY deleteRFI");

		} catch (Exception e) {
			_log.error("Error deleting RFI", e);
		}

	}

	@ProcessAction(name = "viewRFI")
	public void viewRFI(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {

		_log.debug(">>>>>>>>>>>>>>>>>>>>>Into viewRFI");
		Long midView = ParamUtil.getLong(actionRequest, "midView");

		_log.debug("mid parameter is ==>" + midView);
		try {
			RfiLogs rfiForView = _rfiLogsLocalService.fetchRfiLogs(midView);
			// actionRequest.getPortletSession().setAttribute("rfiForView", rfiForView,
			// PortletSession.PORTLET_SCOPE);
			actionRequest.setAttribute("rfiForView", rfiForView);
			actionResponse.getRenderParameters().setValue("mvcPath", "/detail_view_rfi.jsp");

			_log.debug(">>>>>>>>>>>>>>>>>>>>>Into TRY viewRFI");

		} catch (Exception e) {
			_log.error("Error deleting RFI", e);
		}

	}

	/**
	 * This method is used to Add the records for |REC Mantainance and Activity
	 * Records|
	 * 
	 * @param actionRequest  This is the ActionRequest of the Portlet .
	 * @param actionResponse This is the ActionResponse of the Portlet .
	 * @return void This returns nothing.
	 * 
	 * @author Manish Kumar Jaiswal
	 */
	@ProcessAction(name = "editActivity")
	public void editActivity(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
		System.out.println("==============================Notification RFI ");
		_log.debug(">>>>>>>>>>>>>>>>>>>>>Into editRFILog");
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		boolean sendUsers = false;
		long companyId = themeDisplay.getCompanyId();
		String mid = ParamUtil.get(actionRequest, "mid", "");

		_log.debug("mid parameter is ==>" + mid);
		String urlPortal = themeDisplay.getURLPortal().concat(HtmlUtil.escape("/group/"));

		try {

			if (mid != null && !mid.trim().isEmpty()) {

				RfiLogs _rfiLogs = _rfiLogsLocalService.fetchRfiLogs(Long.parseLong(mid));
				_log.info("Here the >>> booking 1st title" + _rfiLogs.getTitle());
				String originalRFITitle = _rfiLogs.getTitle();
				String duedate = ParamUtil.getString(actionRequest, "duedate");
				String status = ParamUtil.getString(actionRequest, "status");

				String reqId = ParamUtil.getString(actionRequest, "reqId");
				String title = ParamUtil.getString(actionRequest, "title");

				String reqDesc = ParamUtil.getString(actionRequest, "reqDesc");
				String rationale = ParamUtil.getString(actionRequest, "rationale");

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate convertedldtfrmt = LocalDate.parse(duedate, formatter);
				duedate = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH).format(convertedldtfrmt);

				PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();
				String portletId = portletDisplay.getId();

				long SiteId = _rfiLogs.getOrgSiteId();
				String oldstatus = _rfiLogs.getStatus();

				_rfiLogs.setReqId(Long.parseLong(reqId));
				_rfiLogs.setTitle(title);

				_rfiLogs.setDueDate(duedate);
				// mbada new field
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				_rfiLogs.setDueDateFormated(format.parse(duedate));
				// end new field
				_rfiLogs.setStatus(status);
				_rfiLogs.setReqDesc(reqDesc);
				_rfiLogs.setRationale(rationale);

				_rfiLogs.setUserId(themeDisplay.getUserId());
				_rfiLogs.setCompanyId(themeDisplay.getCompanyId());
				_rfiLogs.setUserName(themeDisplay.getUser().getFullName());
//				_rfiLogs.setCreateDate(new Date());
				_rfiLogs.setModifiedDate(new Date());

				_log.debug("***************Send Notification");
				_log.debug("Previous status is ==>" + oldstatus);
				_log.debug("New status is ==>" + _rfiLogs.getStatus());

				String bookingLink = "rfis-queries";
				long BookingID = updateBooking(actionRequest, Locale.UK, _rfiLogs, bookingLink);
				_rfiLogs.setCalendarBookingId(BookingID);
				_rfiLogsLocalService.updateRfiLogs(_rfiLogs);
				SessionMessages.add(actionRequest, "success");

				List<Role> rolesUser = RoleLocalServiceUtil.getUserRoles(themeDisplay.getUserId());
				_log.debug("List Role ==>" + rolesUser);
				for (Role roles : rolesUser) {
					_log.debug("User Role ==>" + roles.getName());
					if (roles.getName().equals("RPA")) {

						if (status.contains("Complete") && !oldstatus.contains("Complete")) {
							_log.debug("Entry to complete...");
							try {
								_log.debug("Sending notification 55...");
								MessagesLocalServiceUtil.sendNotification55(themeDisplay.getUserId(), SiteId, title,
										urlPortal, companyId);
							} catch (Exception e) {
								_log.error("Error sending notification 55 " + e);
							}

						} else if (status.contains("Open") && !oldstatus.contains("Open")) {
							System.out.println("==============================Entry to open... ");
							_log.debug("Entry to open...");
							try {
								_log.debug("Sending notification 57...");
								MessagesLocalServiceUtil.sendNotification57(themeDisplay.getUserId(), SiteId, title,
										urlPortal, companyId);
							} catch (Exception e) {
								_log.error("Error sending notification 57 " + e);
							}

						}
					}
				}
				_log.debug("Final send notification...");
			}
			_log.debug(">>>>>>>>>>>>>>>>>>>>>Into TRY editRFILog");
		} catch (Exception e) {
			_log.error("Error editing RFI", e);
		}
	}

	@ProcessAction(name = "importRFI")
	public void importRFI(ActionRequest request, ActionResponse response) throws IOException {

		boolean isValid = true;
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		long userId = Long.valueOf(ParamUtil.getString(request, "userId"));
		long companyId = Long.valueOf(ParamUtil.getString(request, "companyId"));
		List<RfiLogs> importList = new ArrayList<RfiLogs>();
		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(request);

		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();
		String portletId = portletDisplay.getId();

		if (Validator.isNotNull(uploadRequest.getFileAsStream("csvDataFile"))) {

			// Check if the extension is valid (xlsx)
			File file = uploadRequest.getFile("csvDataFile");
			String ext = FileUtil.getExtension(file.getName()).toLowerCase();
			if (ext.equals("xlsx")) {
				InputStream _inputStream = uploadRequest.getFileAsStream("csvDataFile");

				XSSFWorkbook _workbook = new XSSFWorkbook(_inputStream);
				XSSFSheet sheet = _workbook.getSheetAt(0);

				Iterator<Row> _iterator = sheet.rowIterator();

				if (!_iterator.hasNext()) {
					SessionErrors.add(request, "fileEmpty");
					isValid = false;
				} else {
					_iterator.next();
					while (_iterator.hasNext()) {
						_log.debug("----------------------------------------------");
						Row row = _iterator.next();

						long cellOrgId = 0;
						long cellReqId = 0;
						String cellPartyType = StringPool.BLANK;
						String cellDueDate = StringPool.BLANK;
						String cellTitle = StringPool.BLANK;
						String cellStatus = StringPool.BLANK;
						String cellReqDesc = StringPool.BLANK;
						String cellRationale = StringPool.BLANK;

						_log.debug("row--> " + row);

						for (Cell mycell : row) {

							int columnIndex = mycell.getColumnIndex();

							_log.debug("columnIndex--> " + columnIndex);

							if (mycell.getCellType() == CellType.BLANK) {
								SessionErrors.add(request, "incorrectFormat");
								isValid = false;
								break;
							} else {
								switch (columnIndex) {
								case 0:
									if (mycell.getCellType() == CellType.NUMERIC) {
										cellOrgId = Math.round(mycell.getNumericCellValue());
										_log.debug("cellOrgId--> " + cellOrgId);
										break;
									} else {
										_log.debug("CellType is not numeric for orgId");
										SessionErrors.add(request, "incorrectFormat");
										isValid = false;
										break;
									}
									/*
									 * case 1: _log.debug("cellReqId--> " + mycell.getCellType()); cellPartyType =
									 * mycell.getStringCellValue(); _log.debug("cellPartyType--> " + cellPartyType);
									 * break;
									 */
								case 1:
									cellReqId = Math.round(mycell.getNumericCellValue());
									_log.debug("cellReqId--> " + cellReqId);
									break;
								case 2:
									cellTitle = mycell.getStringCellValue();
									_log.debug("cellTitle--> " + cellTitle);
									break;
								case 3:
									cellDueDate = dateFormat.format(mycell.getDateCellValue());
									_log.debug("cellDueDate--> " + cellDueDate);
									break;
								case 4:
									if (mycell.getStringCellValue().trim().toLowerCase().equals("open")
											|| mycell.getStringCellValue().trim().toLowerCase().equals("pending")
											|| mycell.getStringCellValue().trim().toLowerCase().equals("complete")) {
										cellStatus = mycell.getStringCellValue();
										_log.debug("cellStatus--> " + cellStatus);
									} else {
										_log.info("Adding error message. cellstatus equals "+ cellStatus);
										SessionErrors.add(request, "invalidStatus");
										isValid = false;
									}
									break;
								case 5:
									cellReqDesc = mycell.getStringCellValue();
									_log.debug("cellReqDesc--> " + cellReqDesc);
									break;
								case 6:
									cellRationale = mycell.getStringCellValue();
									_log.debug("cellRationale--> " + cellRationale);
									break;
								}
							}
						}

					if(isValid) {
						try {
							RfiLogs newRFI = _rfiLogsLocalService
									.createRfiLogs(_counterLocalService.increment(RfiLogs.class.getName()));
							newRFI.setCompanyId(companyId);
							newRFI.setUserId(userId);
							newRFI.setUserName(UserLocalServiceUtil.getUser(userId).getFullName());
							newRFI.setCreateDate(new Date());
							newRFI.setModifiedDate(new Date());
							newRFI.setOrgSiteId(cellOrgId);
							newRFI.setSpecificParty(cellPartyType);
							newRFI.setReqId(cellReqId);
							newRFI.setDueDate(cellDueDate);
							SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
							newRFI.setDueDateFormated(format.parse(cellDueDate));
							newRFI.setTitle(cellTitle);
							newRFI.setStatus(cellStatus);
							newRFI.setReqDesc(cellReqDesc);
							newRFI.setRationale(cellRationale);

							importList.add(newRFI);

							_log.debug("newRFI----> " + newRFI);
						} catch (ParseException | PortalException e) {
							_log.error("Error creating RFI ", e);
							SessionErrors.add(request, "errorImporting");
							isValid = false;
							break;
						}
					} else {
						_log.info("Import failed. Invalid row.");
						break;
					}

						_log.debug("----------------------------------------------");
					}

				}

				_inputStream.close();
				_workbook.close();

			} else {
				_log.debug("RPA Maintenance Activity - Extension incorrect");
				isValid = false;
				SessionErrors.add(request, "incorrectExt");
			}

			if (isValid && importList.size() > 0) {
				_log.debug("Creating RFIs...");
				for (RfiLogs rfi : importList) {
					if (rfi.getDueDate() != null) {
						String title = rfi.getTitle();
						String dueDate = rfi.getDueDate();
						String bookingLink = "rfis-queries";
						long bookingID = bookingAdd(Locale.UK, title, bookingLink, rfi, request, dueDate);
						rfi.setCalendarBookingId(bookingID);
						_rfiLogsLocalService.addRfiLogs(rfi);

					}
				}
				SessionMessages.add(request, "success");
			}
			
		} else {
			SessionErrors.add(request, "noFile");
		}

	}

	/**
	 * This method is used to update the status of the record for |REC Mantainance
	 * and Activity Records|
	 * 
	 * @param actionRequest  This is the ActionRequest of the Portlet .
	 * @param actionResponse This is the ActionResponse of the Portlet .
	 * @return void This returns nothing.
	 * 
	 */
	@ProcessAction(name = "updateStatus")
	public void updateStatusActivity(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		boolean sendUsers = false;

		_log.debug(">>>>>>>>>>>>>>>>>>>>> Into updatStatus RFI");
		String mid = ParamUtil.getString(actionRequest, "updateStatusId");

		_log.debug("mid parameter is ==>" + mid);
		String urlPortal = themeDisplay.getURLPortal().concat(HtmlUtil.escape("/group/"));

		try {
			if (mid != null && !mid.trim().isEmpty()) {
				RfiLogs _rfiLogs = _rfiLogsLocalService.fetchRfiLogs(Long.parseLong(mid));
				String status = ParamUtil.getString(actionRequest, "updateStatusVal");
				String title = _rfiLogs.getTitle();

				_rfiLogs.setStatus(status);
				_rfiLogsLocalService.updateRfiLogs(_rfiLogs);

				_log.debug("==============================Notification 56 ");
				List<UserGroupRole> userGroupRoles = UserGroupRoleLocalServiceUtil
						.getUserGroupRoles(themeDisplay.getUserId(), themeDisplay.getSiteGroupId());

				for (UserGroupRole userGroupRole : userGroupRoles) {

					if (userGroupRole.getRole().getName().equals("REC Contract Managers")
							|| userGroupRole.getRole().getName().equals("REC_Performance_Assurance")
							|| userGroupRole.getRole().getName().equals("Master Administrative User")) {
						sendUsers = true;
					}
				}

				_log.info("Boolean sendUsers: " + sendUsers);
				if (sendUsers) {
					try {
						_log.debug("Sending notification 56...");
						MessagesLocalServiceUtil.sendNotification56(themeDisplay.getUserId(),
								themeDisplay.getSiteGroupId(), title, urlPortal, themeDisplay.getCompanyId());
					} catch (Exception e) {
						_log.error("Error sending notification 56 " + e);
					}
				}
			}

		} catch (Exception e) {
			_log.error("Error updating status of activity", e);
		}

	}

	/**
	 * This method is used to update the status of the record for |REC Mantainance
	 * and Activity Records|
	 * 
	 * @param actionRequest  This is the ActionRequest of the Portlet .
	 * @param actionResponse This is the ActionResponse of the Portlet .
	 * @return void This returns nothing.
	 * 
	 */
	@ProcessAction(name = "cancelDownload")
	public void cancelDownload(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
		_log.debug(">>>>>>>>>>>>>>>>>>>>> Into cancelDownload");

	}

	private long bookingAdd(Locale locale, String title, String desc, RfiLogs RfiLogs, ActionRequest actionRequest,
			String dueDate) {
		CalendarBooking book = null;
		try {

			DynamicQuery dqStructure = CalendarLocalServiceUtil.dynamicQuery();
			dqStructure.add(PropertyFactoryUtil.forName("groupId").like(RfiLogs.getOrgSiteId()));
			List<Calendar> ddmListStructure = CalendarLocalServiceUtil.dynamicQuery(dqStructure);
			long cID = ddmListStructure.get(0).getCalendarId();
			String dueDateForEnd = dueDate;
			long startHourBooking = 0L;
			long endHourBooking = 0L;
			String endDueDate = "";

			dueDate = dueDate + " 09:00:00";
			endDueDate = dueDateForEnd + " 10:00:00";

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

//	        	sdf.setTimeZone(TimeZone.getTimeZone("Europe/London"));
//	        	 log.debug("timezonee " + sdf.getTimeZone());
			Date date = sdf.parse(dueDate);
			startHourBooking = date.getTime();

			Date DuedateBooking = sdf.parse(endDueDate);
			endHourBooking = DuedateBooking.getTime();

			char ch = '-';
			String siteName = GroupLocalServiceUtil.getGroup(RfiLogs.getOrgSiteId()).getDescriptiveName();
			String siteNameLink = siteName.replace(' ', ch);

			String siteNameLinkToLowerCase = siteNameLink.toLowerCase();

			desc = "<a href=" + "/group/" + siteNameLinkToLowerCase + "/" + desc + " target=_blank >Click Here</a>"
					+ "Due date: " + dueDate;
			_log.info("siteNameLinkToLowerCase value: " + siteNameLinkToLowerCase);

			_log.info("desc value: " + desc);

			Map<Locale, String> titleMap = new HashMap<Locale, String>();
			Map<Locale, String> descriptionMap = new HashMap<Locale, String>();
			long[] childCalendarIds = new long[0];

			ServiceContext serviceContextC = ServiceContextFactory.getInstance(CalendarBooking.class.getName(),
					actionRequest);

			titleMap.put(locale, title);
			descriptionMap.put(locale, desc);
			book = CalendarBookingServiceUtil.addCalendarBooking(cID, childCalendarIds,
					CalendarBookingConstants.PARENT_CALENDAR_BOOKING_ID_DEFAULT, 0, titleMap, descriptionMap, "",
					startHourBooking, endHourBooking, false, "", 0, "email", 0, "email", serviceContextC);
			
			//Automatic invite to RPA using above booking Id as Parent Calendar Booking Id.
			Calendar RPAcalendar = CalendarLocalServiceUtil.fetchCalendar( recFormArticleLocalServiceUtil.getCalendarIDbyName("RPA Calendar"));
			CalendarBookingServiceUtil.addCalendarBooking(RPAcalendar.getCalendarId(), childCalendarIds,
					book.getCalendarBookingId(), 0, titleMap, descriptionMap, "",
					startHourBooking, endHourBooking, false, "", 0, "email", 0, "email", serviceContextC);

		} catch (Exception e) {
			_log.debug("->>>>>> here is the catch error of adding booking <<<<<<-");
			e.printStackTrace();
		}
		return book.getCalendarBookingId();

	}

	public long updateBooking(ActionRequest actionRequest, Locale locale, RfiLogs RfiLogs, String desc) {
		CalendarBooking booking = null;
		long bookingId = 0;
		try {

			DynamicQuery dqStructure = CalendarLocalServiceUtil.dynamicQuery();
			dqStructure.add(PropertyFactoryUtil.forName("groupId").like(RfiLogs.getOrgSiteId()));
			List<Calendar> ddmListStructure = CalendarLocalServiceUtil.dynamicQuery(dqStructure);
			long cID = ddmListStructure.get(0).getCalendarId();
			String dueDate = RfiLogs.getDueDate();
			String dueDateForEnd = RfiLogs.getDueDate();
			long startHourBooking, endHourBooking = 0L;

			String endDueDate = "";

			dueDate = dueDate + " 09:00:00";
			endDueDate = dueDateForEnd + " 10:00:00";

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

//		   	sdf.setTimeZone(TimeZone.getTimeZone("Europe/London"));
//		   	 log.debug("timezonee " + sdf.getTimeZone());
			Date date = sdf.parse(dueDate);
			startHourBooking = date.getTime();

			Date DuedateBooking = sdf.parse(endDueDate);
			endHourBooking = DuedateBooking.getTime();

			char ch = '-';
			String siteName = GroupLocalServiceUtil.getGroup(RfiLogs.getOrgSiteId()).getDescriptiveName();
			String siteNameLink = siteName.replace(' ', ch);

			String siteNameLinkToLowerCase = siteNameLink.toLowerCase();
			desc = "<a href=" + "/group/" + siteNameLinkToLowerCase + "/" + desc + " target=_blank >Click Here</a>"
					+ "Due date: " + dueDate;
			_log.info("siteNameLinkToLowerCase value: " + siteNameLinkToLowerCase);

			_log.info("desc value: " + desc);

			Map<Locale, String> titleMap = new HashMap<Locale, String>();
			Map<Locale, String> descriptionMap = new HashMap<Locale, String>();
			ServiceContext serviceContextC = ServiceContextFactory.getInstance(CalendarBooking.class.getName(),
					actionRequest);
			titleMap.put(locale, RfiLogs.getTitle());
			descriptionMap.put(locale, desc);

			if (RfiLogs.getCalendarBookingId() != 0) {
				booking = CalendarBookingServiceUtil.updateCalendarBooking(RfiLogs.getCalendarBookingId(), cID,
						titleMap, descriptionMap, "", startHourBooking, endHourBooking, false, "", 0, "email", 0,
						"email", serviceContextC);
				bookingId = booking.getCalendarBookingId();
			} else {
				_log.info("->>>>>> Calendarbooking not update as this item is an old one <<<<<<-");
			}

		} catch (PortalException | ParseException e) {
			_log.error("->>>>>> here is the catch error of updating booking <<<<<<-", e);
		}
		return bookingId;
	}

	public static void deleteBooking(ThemeDisplay themedisplay, RfiLogs RfiLogs) {

		try {

			long calendarBId = RfiLogs.getCalendarBookingId();

			if (calendarBId != 0) {
				CalendarBookingServiceUtil.deleteCalendarBooking(calendarBId);
			}
		} catch (Exception e) {
			_log.debug("->>>>>> here is the catch error of deleting booking <<<<<<-");
			e.printStackTrace();

		}

	}

	@Reference
	RfiLogsLocalService _rfiLogsLocalService;

	@Reference
	CounterLocalService _counterLocalService;

	@Reference
	protected DLFolderLocalService _dlFolderLocalService;

	@Reference
	protected DLAppService _dlAppServiceUtil;

	@Reference
	private Portal _portal;
}
