package com.everis.rec.remediation.tracker.portlet;

import com.everis.cproposal.service.recFormArticleLocalServiceUtil;
import com.everis.messages.service.builder.service.MessagesLocalServiceUtil;
import com.everis.rec.remediation.tracker.constants.RecRemediationTrackerWebPortletKeys;
import com.everis.rec.remediation.tracker.model.RemediationTracker;
import com.everis.rec.remediation.tracker.service.RemediationTrackerLocalService;
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
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.UserGroupRole;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ParamUtil;
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
import javax.portlet.PortletSession;
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
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.header-portlet-javascript=/js/main.js",
		"javax.portlet.init-param.config-template=/configuration.jsp", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=RecRemediationTrackerWeb", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.init-param.add-process-action-success-action=false",
		"javax.portlet.name=" + RecRemediationTrackerWebPortletKeys.RECREMEDIATIONTRACKERWEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class RecRemediationTrackerWebPortlet extends MVCPortlet {

	private static String ROOT_FOLDER_NAME = "File_Upload";
	private static String ROOT_FOLDER_DESCRIPTION = "This is Remdiation Tracker Folder";
	private static long PARENT_FOLDER_ID = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;
	// private static String link
	// ="remediation-tracker?p_p_id=com_everis_rec_remediation_tracker_RecRemediationTrackerWebPortlet_INSTANCE_y6UBPzEh8otn&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&_com_everis_rec_remediation_tracker_RecRemediationTrackerWebPortlet_INSTANCE_y6UBPzEh8otn_mvcPath=%2Fedit_remediationtracker.jsp&RTID=";

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		super.doView(renderRequest, renderResponse);
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
	@ProcessAction(name = "addRemediationTracker")
	public void addRemediationTracker(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
		_log.debug("==============================Into addRemediationTracker");
		actionResponse.getRenderParameters().setValue("mvcPath", "/add_remediationtracker.jsp");
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
	@ProcessAction(name = "addRemediationTrackerSubmit")
	public void addRemediationTrackerSubmit(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {

		_log.debug("=============================Into addRemediationTrackerSubmit");
		ParamUtil.print(actionRequest);

		try {

			String title = ParamUtil.getString(actionRequest, "title");
			String duedate = ParamUtil.getString(actionRequest, "duedate");
			String status = ParamUtil.getString(actionRequest, "status");
			String category = ParamUtil.getString(actionRequest, "category");
			String description = ParamUtil.getString(actionRequest, "description");

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate convertedldtfrmt = LocalDate.parse(duedate, formatter);
			duedate = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH).format(convertedldtfrmt);

			RemediationTracker _remediationTracker = _remediationTrackerLocalService
					.createRemediationTracker(_counterLocalService.increment(RemediationTracker.class.getName()));

			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			_remediationTracker.setUserId(themeDisplay.getUserId());
			_remediationTracker.setUserName(themeDisplay.getUser().getFullName());
			_remediationTracker.setOrgSiteId(themeDisplay.getScopeGroupId());
			_remediationTracker.setSpecificParty(themeDisplay.getScopeGroupName());
			Group group = _groupLocalService.fetchGroup(_remediationTracker.getOrgSiteId());
			_remediationTracker.setSpecificParty(group.getName(themeDisplay.getLocale()));
			_remediationTracker.setTitle(title);
			_remediationTracker.setDueDate(duedate);
			_remediationTracker.setDescription(description);
			_remediationTracker.setCategory(category);
			_remediationTracker.setStatus(status);
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			_remediationTracker.setDueDateFormated(formato.parse(duedate));

			String bookingLink = "remediation-tracker";

			long BookingID = bookingAdd(Locale.UK, bookingLink, _remediationTracker, actionRequest);
			_remediationTracker.setCalendarBookingId(BookingID);
			_remediationTrackerLocalService.addRemediationTracker(_remediationTracker);
			SessionMessages.add(actionRequest, "success");
			try {
				String urlPortal = themeDisplay.getURLPortal().concat(HtmlUtil.escape("/group/"));
				long companyId = themeDisplay.getCompanyId();
				MessagesLocalServiceUtil.sendNotification49(themeDisplay.getUserId(), themeDisplay.getSiteGroupId(),
						title, urlPortal, companyId);
			} catch (Exception e) {

				_log.error("Error sending Notification49", e);
			}

		} catch (Exception e) {
			_log.error("Error adding new RemediationTracker", e);
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
	@ProcessAction(name = "deleteRemediationTracker")
	public void deleteRemediationTracker(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {

		_log.debug(">>>>>>>>>>>>>>>>>>>>>Into deleteRemediationTracker");
		String mid = ParamUtil.get(actionRequest, "mid", "");
		String submitType = ParamUtil.get(actionRequest, "submitType", "");
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		_log.debug("mid parameter is ==>" + mid);
		_log.debug("submitType parameter is ==>" + submitType);

		try {
			if ((submitType != null && !submitType.trim().isEmpty()) && (mid != null && !mid.trim().isEmpty())) {
				if (submitType.equalsIgnoreCase("edit")) {

					RemediationTracker remediationTrackerForEdit = _remediationTrackerLocalService
							.fetchRemediationTracker(Long.parseLong(mid));
					String duedateedit = remediationTrackerForEdit.getDueDate();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					LocalDate convertedldtfrmt = LocalDate.parse(duedateedit, formatter);
					duedateedit = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(convertedldtfrmt);
					remediationTrackerForEdit.setDueDate(duedateedit);
					actionRequest.setAttribute("remediationTrackerForEdit", remediationTrackerForEdit);
					actionResponse.getRenderParameters().setValue("mvcPath", "/edit_remediationtracker.jsp");

				} else if (submitType.equalsIgnoreCase("delete")) {
					RemediationTracker remediationTracker = _remediationTrackerLocalService
							.fetchRemediationTracker(Long.parseLong(mid));
					String remediationTrackerTitle = remediationTracker.getTitle();
					ROOT_FOLDER_NAME = "REMEDIATION_TRACKER_FILES_" + mid;
					try {
						if (isFolderExist(remediationTracker)) {
							Folder folder = _dlAppService.getFolder(remediationTracker.getOrgSiteId(), PARENT_FOLDER_ID,
									ROOT_FOLDER_NAME);
							_dlAppService.deleteFolder(folder.getFolderId());
						}
					} catch (Exception e) {
						_log.error("Error deleting remediationTracker folder", e);
					}
					deleteBooking(themeDisplay, remediationTracker);
					_remediationTrackerLocalService.deleteRemediationTracker(Long.parseLong(mid));
					SessionMessages.add(actionRequest, "success");
					List<RemediationTracker> remediationTrackerList = (List<RemediationTracker>) actionRequest
							.getPortletSession()
							.getAttribute("remediationTrackerList", PortletSession.APPLICATION_SCOPE);
					// remediationTrackerList.remove(remediationTrackerForDelete);
					actionRequest.setAttribute("remediationTrackerDocsMap", getDocsList(remediationTrackerList));
					actionResponse.getRenderParameters().setValue("mvcPath", "/redirect.jsp");
				}
			}
			_log.debug(">>>>>>>>>>>>>>>>>>>>>Into TRY deleteRemediationTracker");

		} catch (Exception e) {
			_log.error("Error deleting remediationTracker", e);
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
	@ProcessAction(name = "editRemediationTracker")
	public void editRemediationTracker(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {

		_log.debug(">>>>>>>>>>>>>>>>>>>>>Into editRemediationTracker");
		String mid = ParamUtil.get(actionRequest, "mid", "");
		_log.debug("mid parameter is ==>" + mid);
		boolean sendUsers = false;

		try {

			if (mid != null && !mid.trim().isEmpty()) {

				RemediationTracker _remediationTracker = _remediationTrackerLocalService
						.fetchRemediationTracker(Long.parseLong(mid));

				ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
				long companyId = themeDisplay.getCompanyId();

				String title = ParamUtil.getString(actionRequest, "title");
				String duedate = ParamUtil.getString(actionRequest, "duedate");
				String status = ParamUtil.getString(actionRequest, "status");

				String category = ParamUtil.getString(actionRequest, "category");
				String description = ParamUtil.getString(actionRequest, "description");

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate convertedldtfrmt = LocalDate.parse(duedate, formatter);
				duedate = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH).format(convertedldtfrmt);

				// DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyyMMdd");
				long SiteId = _remediationTracker.getOrgSiteId();
				String oldstatus = _remediationTracker.getStatus();
				_remediationTracker.setTitle(title);
				_remediationTracker.setDueDate(duedate);
				_remediationTracker.setStatus(status);
				_remediationTracker.setDescription(description);
				_remediationTracker.setCategory(category);

				_remediationTracker.setUserId(themeDisplay.getUserId());
				_remediationTracker.setUserName(themeDisplay.getUser().getFullName());
				_remediationTracker.setModifiedDate(new Date());

				Group group = _groupLocalService.fetchGroup(_remediationTracker.getOrgSiteId());
				_remediationTracker.setSpecificParty(group.getName(themeDisplay.getLocale()));

				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				_remediationTracker.setDueDateFormated(formato.parse(duedate));

				String bookingLink = "remediation-tracker";

				long BookingID = updateBooking(actionRequest, Locale.UK, _remediationTracker, bookingLink);
				_remediationTracker.setCalendarBookingId(BookingID);
				_remediationTrackerLocalService.updateRemediationTracker(_remediationTracker);
				SessionMessages.add(actionRequest, "success");

				List<Role> rolesUser = RoleLocalServiceUtil.getUserRoles(themeDisplay.getUserId());
				String urlPortal = themeDisplay.getURLPortal().concat(HtmlUtil.escape("/group/"));

				for (Role roles : rolesUser) {
					if (roles.getName().equals("RPA")) {
						if (status.contains("Complete") && !oldstatus.contains("Complete")) {
							_log.debug("Entry to complete...");
							try {
								_log.debug("Sending notification 62...");
								MessagesLocalServiceUtil.sendNotification62(themeDisplay.getUserId(), SiteId, title,
										urlPortal, companyId);
							} catch (Exception e) {
								_log.error("Error sending notification 62 " + e);
							}

						} else if (status.contains("Open") && !oldstatus.contains("Open")) {
							_log.debug("Entry to open...");
							try {
								_log.debug("Sending notification 64...");
								MessagesLocalServiceUtil.sendNotification64(themeDisplay.getUserId(), SiteId, title,
										urlPortal, companyId);
							} catch (Exception e) {
								_log.error("Error sending notification 64 " + e);
							}
						}
					}
				}
			}
			_log.debug(">>>>>>>>>>>>>>>>>>>>>Into TRY editRemediationTracker");
		} catch (Exception e) {
			_log.error("Error editing remedation tracker", e);
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
	public void updateStatusRemediationTracker(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		boolean sendUsers = false;

		_log.debug(">>>>>>>>>>>>>>>>>>>>> Into updatStatus REMEDIATION");
		String mid = ParamUtil.getString(actionRequest, "updateStatusId");

		_log.debug("mid parameter is ==>" + mid);
		String urlPortal = themeDisplay.getURLPortal().concat(HtmlUtil.escape("/group/"));

		try {
			if (mid != null && !mid.trim().isEmpty()) {
				RemediationTracker _remediationTracker = _remediationTrackerLocalService
						.fetchRemediationTracker(Long.parseLong(mid));
				String status = ParamUtil.getString(actionRequest, "updateStatusVal");
				String title = _remediationTracker.getTitle();

				_remediationTracker.setStatus(status);
				_remediationTrackerLocalService.updateRemediationTracker(_remediationTracker);

				SessionMessages.add(actionRequest, "success");
				_log.debug("==============================Notification 63 ");
				List<UserGroupRole> userGroupRoles = UserGroupRoleLocalServiceUtil
						.getUserGroupRoles(themeDisplay.getUserId(), themeDisplay.getSiteGroupId());

				for (UserGroupRole userGroupRole : userGroupRoles) {

					if (userGroupRole.getRole().getName().equals("REC Contract Managers")
							|| userGroupRole.getRole().getName().equals("REC_Performance_Assurance")
							|| userGroupRole.getRole().getName().equals("Master Administrative User")) {
						sendUsers = true;
					}
				}

				_log.debug("Boolean sendUsers: " + sendUsers);
				if (sendUsers) {
					try {
						_log.debug("Sending notification 63...");
						MessagesLocalServiceUtil.sendNotification63(themeDisplay.getUserId(),
								themeDisplay.getSiteGroupId(), title, urlPortal, themeDisplay.getCompanyId());
					} catch (Exception e) {
						_log.error("Error sending notification 63 " + e);
					}
				}
			}

		} catch (Exception e) {
			_log.error("Error updating status of activity", e);
		}

	}

	@ProcessAction(name = "uploadDocument")
	public void uploadDocument(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException, PortalException {

		UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(actionRequest);
		ROOT_FOLDER_NAME = "REMEDIATION_TRACKER_FILES_" + uploadPortletRequest.getParameter("uid");
		Long rfilogId = ParamUtil.getLong(uploadPortletRequest, "uid");
		RemediationTracker remediationTracker = _remediationTrackerLocalService.fetchRemediationTracker(rfilogId);
		String submitDocType = uploadPortletRequest.getParameter("submitDocType");
		if (submitDocType.equalsIgnoreCase("download")) {
			try {
				downloadFiles(actionRequest, actionResponse, remediationTracker);
			} catch (SystemException e) {
				e.printStackTrace();
			} catch (PortalException e) {
				e.printStackTrace();
			}
		} else {
			_log.debug("REMEDIATION_TRACKER_FILES==============================Into uploadDocument");
			ROOT_FOLDER_NAME = "REMEDIATION_TRACKER_FILES_" + uploadPortletRequest.getParameter("uid");

			Folder folder = createFolder(actionRequest, remediationTracker);
			fileUpload(actionRequest, uploadPortletRequest.getParameter("uid"), folder, remediationTracker);
			actionResponse.getRenderParameters().setValue("mvcPath", "/redirect.jsp");
			actionResponse.setRenderParameter("mvcRenderCommandName", RecRemediationTrackerWebPortletKeys.VIEW_LANDING);
			actionResponse.setRenderParameter("orderByCol", "specificParty");
			actionResponse.setRenderParameter("orderByType", "asc");
			SessionMessages.add(actionRequest, "successFile");
		}
	}

	public Folder createFolder(ActionRequest actionRequest, RemediationTracker remediationTracker) {
		boolean folderExist = isFolderExist(remediationTracker);
		Folder folder = null;
		if (!folderExist) {
			long repositoryId = remediationTracker.getOrgSiteId();
			try {
				ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFolder.class.getName(),
						actionRequest);
				folder = _dlAppService.addFolder(repositoryId, PARENT_FOLDER_ID, ROOT_FOLDER_NAME,
						ROOT_FOLDER_DESCRIPTION, serviceContext);
				_log.debug("REMEDIATION_TRACKER_FILES_========" + ROOT_FOLDER_NAME
						+ "==============================FOLDER CREATED");
			} catch (PortalException e1) {
				e1.printStackTrace();
			} catch (SystemException e1) {
				e1.printStackTrace();
			}
		} else {
			folder = getFolder(remediationTracker);
		}
		return folder;
	}

	public boolean isFolderExist(RemediationTracker remediationTracker) {
		boolean folderExist = false;
		try {
			_dlAppService.getFolder(remediationTracker.getOrgSiteId(), PARENT_FOLDER_ID, ROOT_FOLDER_NAME);
			folderExist = true;
			_log.debug(ROOT_FOLDER_NAME + " Folder already Exists....");
		} catch (Exception e) {
			_log.debug(e.getMessage());
		}
		return folderExist;
	}

	public Folder getFolder(RemediationTracker remediationTracker) {
		Folder folder = null;
		try {
			folder = _dlAppService.getFolder(remediationTracker.getOrgSiteId(), PARENT_FOLDER_ID, ROOT_FOLDER_NAME);
		} catch (Exception e) {
			_log.debug(e.getMessage());
		}
		return folder;
	}

	public void fileUpload(ActionRequest actionRequest, String uid, Folder folder,
			RemediationTracker remediationTracker) throws PortalException, IOException {
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

			for (int i = 0; i < fileList.length; i++) {
				InputStream is = fileList[i];
				int length = is.available();
				title = fileNamesList[i];

				if (title != null && !title.trim().isEmpty()) {
					_log.debug("==============================title " + title);
					description = title + " is added via programatically";
					repositoryId = remediationTracker.getOrgSiteId();

					try {
						mimeType = MimeTypesUtil.getContentType(is, title);
						ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(),
								actionRequest);

						/* ************************************************************************ */

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

						/* ************************************************************************ */

						_dlAppService.addFileEntry(repositoryId, folder.getFolderId(), title, mimeType, title,
								description, "", is, length, serviceContext);

					} catch (PortalException e) {
						e.printStackTrace();
					}

					_log.debug("==============================Notification 65 ");
					_log.debug("Object RFI: " + remediationTracker);
					String titleRFI = remediationTracker.getTitle();
					_log.debug("Title1: " + titleRFI);

					List<Role> roleUser = UserLocalServiceUtil.getUser(userId).getRoles();

					for (Role role : roleUser) {
						if (role.getName().equals("RPA")) {
							_log.debug("Role to send: " + role.getName());
							try {
								_log.debug("Sending notification 65...");
								MessagesLocalServiceUtil.sendNotification65(themeDisplay.getUserId(), repositoryId,
										titleRFI, urlPortal, themeDisplay.getCompanyId());
							} catch (Exception e) {
								_log.error("Error sending notification 65 " + e);
							}

						}
					}

					_log.debug("==============================Notification 66 ");

					List<UserGroupRole> userGroupRoles = UserGroupRoleLocalServiceUtil
							.getUserGroupRoles(themeDisplay.getUserId(), themeDisplay.getSiteGroupId());

					for (UserGroupRole userGroupRole : userGroupRoles) {

						if (userGroupRole.getRole().getName().equals("REC Contract Managers")
								|| userGroupRole.getRole().getName().equals("REC_Performance_Assurance")
								|| userGroupRole.getRole().getName().equals("Master Administrative User")) {

							sendUsers = true;

						}
					}

					_log.debug("Boolean sendUsers: " + sendUsers);
					if (sendUsers) {
						try {
							_log.debug("Sending notification 66...");
							MessagesLocalServiceUtil.sendNotification66(themeDisplay.getUserId(),
									themeDisplay.getSiteGroupId(), titleRFI, urlPortal, themeDisplay.getCompanyId());
						} catch (Exception e) {
							_log.error("Error sending notification 66 " + e);
						}
					}
				}
			}
		} else {
			_log.info("noFile: ");
			SessionErrors.add(actionRequest, "noFile");
		}

		List<RemediationTracker> remediationTrackerList = (List<RemediationTracker>) actionRequest.getPortletSession()
				.getAttribute("remediationTrackerList", PortletSession.APPLICATION_SCOPE);
		actionRequest.setAttribute("remediationTrackerDocsMap", getDocsList(remediationTrackerList));

	}

	private Map<Long, Boolean> getDocsList(List<RemediationTracker> remediationTrackerList) {
		boolean hasFiles;
		Map<Long, Boolean> remediationTrackerDocsMap = new HashMap<Long, Boolean>();
		for (RemediationTracker remediationTrackerTemp : remediationTrackerList) {
			hasFiles = false;
			_log.debug("====================Into render: remediationTracker.getOrgSiteId()"
					+ remediationTrackerTemp.getOrgSiteId());

			ROOT_FOLDER_NAME = "REMEDIATION_TRACKER_FILES_" + remediationTrackerTemp.getRemediationTrackerId();
			DLFolder folderTemp = _dlFolderLocalService.fetchFolder(remediationTrackerTemp.getOrgSiteId(), 0,
					ROOT_FOLDER_NAME);

			if (folderTemp != null) {
				int fileEntriesNumber = 0;
				try {
					fileEntriesNumber = _dlAppService.getFileEntriesCount(remediationTrackerTemp.getOrgSiteId(),
							folderTemp.getFolderId());
					if (fileEntriesNumber > 0) {
						hasFiles = true;
					}
				} catch (PortalException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			remediationTrackerDocsMap.put(remediationTrackerTemp.getRemediationTrackerId(), hasFiles);
		}
		return remediationTrackerDocsMap;

	}

	public void downloadFiles(ActionRequest actionRequest, ActionResponse actionResponse,
			RemediationTracker remediationTracker)
			throws IOException, PortletException, PortalException, SystemException {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(actionRequest);
		ROOT_FOLDER_NAME = "REMEDIATION_TRACKER_FILES_" + uploadPortletRequest.getParameter("uid");

		Map<String, String> urlMap = getAllFileLink(themeDisplay, remediationTracker);
		actionRequest.setAttribute("urlMap", urlMap);
		actionResponse.getRenderParameters().setValue("mvcPath", "/download.jsp");
	}

	public Map<String, String> getAllFileLink(ThemeDisplay themeDisplay, RemediationTracker remediationTracker) {
		Map<String, String> urlMap = new HashMap<String, String>();// key = file name ,value = url
		long repositoryId = remediationTracker.getOrgSiteId();
		try {
			Folder folder = getFolder(remediationTracker);
			List<FileEntry> fileEntries = _dlAppService.getFileEntries(repositoryId, folder.getFolderId());
			for (FileEntry file : fileEntries) {
				String path = themeDisplay.getPortalURL() + "/c/document_library/get_file?uuid=" + file.getUuid()
						+ "&groupId=" + remediationTracker.getOrgSiteId();
				urlMap.put(file.getTitle().split("\\.")[0], path);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return urlMap;

	}

	@ProcessAction(name = "importAnualMaintenance")
	public void importAnualRemediationTracker(ActionRequest request, ActionResponse response) throws IOException {

		boolean isValid = true;
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		long userId = Long.valueOf(ParamUtil.getString(request, "userId"));
		List<RemediationTracker> importRemediationTrackerList = new ArrayList<RemediationTracker>();
		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(request);

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
						// String cellPartyType = StringPool.BLANK;
						String cellDueDate = StringPool.BLANK;
						Date cellDueDateFormat = new Date();

						String cellTitle = StringPool.BLANK;
						String cellStatus = StringPool.BLANK;
						String cellDescription = StringPool.BLANK;
						String cellCategory = StringPool.BLANK;

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
//								case 1:
//									cellPartyType = mycell.getStringCellValue();
//									_log.debug("cellPartyType--> " + cellPartyType);
//									break;
								case 1:
									cellDueDate = dateFormat.format(mycell.getDateCellValue());
									cellDueDateFormat = mycell.getDateCellValue();
									_log.debug("cellDueDate--> " + cellDueDate);
									break;
								case 2:
									cellTitle = mycell.getStringCellValue();
									_log.debug("cellTitle--> " + cellTitle);
									break;
								case 3:
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
								case 4:
									cellDescription = mycell.getStringCellValue();
									_log.debug("cellDescription--> " + cellDescription);
									break;
								case 5:
									cellCategory = mycell.getStringCellValue();
									_log.debug("cellCategory--> " + cellCategory);
									break;
								}

							}
						}

						Group group = null;
						String orgName = null;
						try {
							group = GroupLocalServiceUtil.getGroup(cellOrgId);
							orgName = group.getDescriptiveName();
						} catch (PortalException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						_log.info("orgName --> " + orgName);
						
					if(isValid) {
					
						try {

							ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
							RemediationTracker newRemediationTracker = _remediationTrackerLocalService
									.createRemediationTracker(
											_counterLocalService.increment(RemediationTracker.class.getName()));
							newRemediationTracker.setUserId(userId);
							newRemediationTracker.setUserName(themeDisplay.getUser().getFullName());
							newRemediationTracker.setCreateDate(new Date());
							newRemediationTracker.setModifiedDate(new Date());
							newRemediationTracker.setOrgSiteId(cellOrgId);
							// Group group = _groupLocalService.fetchGroup(cellOrgId);
							newRemediationTracker.setSpecificParty(orgName);
							newRemediationTracker.setDueDate(cellDueDate);
							newRemediationTracker.setTitle(cellTitle);
							newRemediationTracker.setStatus(cellStatus);
							newRemediationTracker.setDueDateFormated(cellDueDateFormat);
							newRemediationTracker.setDescription(cellDescription);
							newRemediationTracker.setCategory(cellCategory);

							importRemediationTrackerList.add(newRemediationTracker);

							_log.debug("newMaintenanaceAct----> " + newRemediationTracker);
						} catch (Exception e) {
							_log.error("Error creating Maintenance Activity ", e);
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

			if (isValid && importRemediationTrackerList.size() > 0) {
				_log.debug("Creating RemediationTracker ...");
				for (RemediationTracker maintenanceAct : importRemediationTrackerList) {
					if (maintenanceAct.getDueDate() != null) {

						String bookingLink = "remediation-tracker";

						long BookingID = bookingAdd(Locale.UK, bookingLink, maintenanceAct, request);
						maintenanceAct.setCalendarBookingId(BookingID);
						_remediationTrackerLocalService.addRemediationTracker(maintenanceAct);

					}
				}
				SessionMessages.add(request, "success");
			}
		} else {
			SessionErrors.add(request, "noFile");
		}

	}

	@ProcessAction(name = "cancelDownload")
	public void cancelDownload(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
		_log.debug(">>>>>>>>>>>>>>>>>>>>> Into cancelDownload");

	}

	private long bookingAdd(Locale locale, String desc, RemediationTracker RemediationTracker,
			ActionRequest actionRequest) {
		CalendarBooking book = null;
		try {

			DynamicQuery dqStructure = CalendarLocalServiceUtil.dynamicQuery();
			dqStructure.add(PropertyFactoryUtil.forName("groupId").like(RemediationTracker.getOrgSiteId()));
			List<Calendar> ddmListStructure = CalendarLocalServiceUtil.dynamicQuery(dqStructure);
			long cID = ddmListStructure.get(0).getCalendarId();
			String dueDate = RemediationTracker.getDueDate();

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
			String siteName = GroupLocalServiceUtil.getGroup(RemediationTracker.getOrgSiteId()).getDescriptiveName();
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

			titleMap.put(locale, RemediationTracker.getTitle());
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

	public long updateBooking(ActionRequest actionRequest, Locale locale, RemediationTracker RemediationTracker,
			String desc) {
		CalendarBooking booking = null;
		long bookingId = 0;
		try {

			DynamicQuery dqStructure = CalendarLocalServiceUtil.dynamicQuery();
			dqStructure.add(PropertyFactoryUtil.forName("groupId").like(RemediationTracker.getOrgSiteId()));
			List<Calendar> ddmListStructure = CalendarLocalServiceUtil.dynamicQuery(dqStructure);
			long cID = ddmListStructure.get(0).getCalendarId();
			String dueDate = RemediationTracker.getDueDate();
			String dueDateForEnd = RemediationTracker.getDueDate();
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
			String siteName = GroupLocalServiceUtil.getGroup(RemediationTracker.getOrgSiteId()).getDescriptiveName();
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
			titleMap.put(locale, RemediationTracker.getTitle());
			descriptionMap.put(locale, desc);

			if (RemediationTracker.getCalendarBookingId() != 0) {
				booking = CalendarBookingServiceUtil.updateCalendarBooking(RemediationTracker.getCalendarBookingId(),
						cID, titleMap, descriptionMap, "", startHourBooking, endHourBooking, false, "", 0, "email", 0,
						"email", serviceContextC);
				bookingId = booking.getCalendarBookingId();
			} else {
				_log.info("->>>>>> Calendarbooking not update as this item is an old one <<<<<<-");
			}

		} catch (PortalException | ParseException e) {

			_log.error("->>>>>> here is the catch error of updating booking <<<<<<-");
		}

		return bookingId;
	}

	public static void deleteBooking(ThemeDisplay themedisplay, RemediationTracker RemediationTracker) {

		try {

			long calendarBId = RemediationTracker.getCalendarBookingId();

			if (calendarBId != 0) {
				CalendarBookingServiceUtil.deleteCalendarBooking(calendarBId);
			}
		} catch (Exception e) {
			_log.debug("->>>>>> here is the catch error of deleting booking <<<<<<-");
			e.printStackTrace();

		}

	}

	// define log for this class
	private static final Log _log = LogFactoryUtil.getLog(RecRemediationTrackerWebPortlet.class.getName());

	@Reference
	protected RemediationTrackerLocalService _remediationTrackerLocalService;

	@Reference
	protected CounterLocalService _counterLocalService;

	@Reference
	protected DLFolderLocalService _dlFolderLocalService;

	@Reference
	protected DLAppService _dlAppService;

	@Reference
	GroupLocalService _groupLocalService;
}
