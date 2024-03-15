package com.everis.rec.activity.aggretate.portlet;

import com.everis.cproposal.service.recFormArticleLocalServiceUtil;
import com.everis.messages.service.builder.service.MessagesLocalServiceUtil;
import com.everis.rec.activity.aggretate.constants.RecActivityAggregateWebPortletKeys;
import com.everis.rec.maintenanceactivity.model.MaintenanceActivity;
import com.everis.rec.maintenanceactivity.service.MaintenanceActivityLocalService;
import com.liferay.calendar.constants.CalendarBookingConstants;
import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.service.CalendarBookingServiceUtil;
import com.liferay.calendar.service.CalendarLocalServiceUtil;
import com.liferay.calendar.service.persistence.CalendarBookingUtil;
import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.UserGroupRole;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
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
import javax.portlet.PortletRequest;
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

@Component(property = { "com.liferay.portlet.display-category=Rec",
		"javax.portlet.display-name=Activity Aggregate View",
		"javax.portlet.init-param.config-template=/configuration.jsp",
		"javax.portlet.init-param.view-template=/view.jsp", "com.liferay.portlet.header-portlet-css=/css/main.css",
		"javax.portlet.name=" + RecActivityAggregateWebPortletKeys.RECACTIVITYAGGREGATEWEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.init-param.add-process-action-success-action=false",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class RecActivityAggregateWebPortlet extends MVCPortlet {

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
	@ProcessAction(name = "addActivity")
	public void addActivity(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {

		_log.debug("==============================Into addActivity");

		actionResponse.getRenderParameters().setValue("mvcPath", "/add_activity.jsp");

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
	@ProcessAction(name = "addActivitySubmit")
	public void addActivitySubmit(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {

		_log.debug("=============================Into addActivitySubmit");
		ParamUtil.print(actionRequest);

		try {

			String activitytitle = ParamUtil.getString(actionRequest, "activitytitle");
			String duedate = ParamUtil.getString(actionRequest, "duedate");
			String status = ParamUtil.getString(actionRequest, "status");
			String title = ParamUtil.getString(actionRequest, "title");

			_log.debug(">>>>>>>>>>>>>>>>>>>>>activitytitle " + activitytitle);
			_log.debug(">>>>>>>>>>>>>>>>>>>>>duedate " + duedate);
			_log.debug(">>>>>>>>>>>>>>>>>>>>>status " + status);

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate convertedldtfrmt = LocalDate.parse(duedate, formatter);
			duedate = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH).format(convertedldtfrmt);

			MaintenanceActivity _maintenanceActivity = _maintenanceActivityLocalService
					.createMaintenanceActivity(_counterLocalService.increment(MaintenanceActivity.class.getName()));

			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			long userId = themeDisplay.getUserId();

			_maintenanceActivity.setUserId(userId);
			_maintenanceActivity.setOrgSiteId(themeDisplay.getScopeGroupId());
			_maintenanceActivity.setSpecificParty(themeDisplay.getScopeGroupName());

			long mActivityOrgID = _maintenanceActivity.getOrgSiteId();

			_log.debug("themeDisplay.getSiteGroupName() ==== " + themeDisplay.getSiteGroupName());

			_maintenanceActivity.setActivityTitle(activitytitle);
			_maintenanceActivity.setDueDate(duedate);

			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			_maintenanceActivity.setDueDateFormated(formato.parse(duedate));

			// TODO add remaining parameters

			_maintenanceActivity.setStatus(status);
			String bookingLink = "home";

			try {

				String urlPortal = themeDisplay.getURLPortal().concat(HtmlUtil.escape("/group/"));
				long companyId = themeDisplay.getCompanyId();
				MessagesLocalServiceUtil.sendNotification47(themeDisplay.getUserId(), themeDisplay.getSiteGroupId(),
						activitytitle, urlPortal, companyId);

				String intanceID = themeDisplay.getPortletDisplay().getURLPortlet();

				_log.info(" Instance ID ==== " + intanceID);

				long BookingID = bookingAdd(Locale.UK, bookingLink, _maintenanceActivity, actionRequest);
				
				_maintenanceActivity.setCalendarBookingId(BookingID);
				_maintenanceActivityLocalService.addMaintenanceActivity(_maintenanceActivity);
				SessionMessages.add(actionRequest, "success");

			} catch (Exception e) {

				_log.error("Error sending Notification47", e);
			}

		} catch (Exception e) {
			_log.error("Error adding new activity", e);
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
	@ProcessAction(name = "deleteActivity")
	public void deleteActivity(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {

		_log.debug(">>>>>>>>>>>>>>>>>>>>>Into deleteActivity");
		String mid = ParamUtil.get(actionRequest, "mid", "");
		String submitType = ParamUtil.get(actionRequest, "submitType", "");

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		MaintenanceActivity maintenanceActivityForEditt = _maintenanceActivityLocalService
				.fetchMaintenanceActivity(Long.parseLong(mid));

		String originalActivityTitle = maintenanceActivityForEditt.getActivityTitle();

		_log.debug("mid parameter is ==>" + mid);
		_log.debug("submitType parameter is ==>" + submitType);

		try {
			if ((submitType != null && !submitType.trim().isEmpty()) && (mid != null && !mid.trim().isEmpty())) {
				if (submitType.equalsIgnoreCase("edit")) {
					MaintenanceActivity maintenanceActivityForEdit = _maintenanceActivityLocalService
							.fetchMaintenanceActivity(Long.parseLong(mid));

					String duedateedit = maintenanceActivityForEdit.getDueDate();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					LocalDate convertedldtfrmt = LocalDate.parse(duedateedit, formatter);
					duedateedit = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(convertedldtfrmt);
					maintenanceActivityForEdit.setDueDate(duedateedit);

					actionRequest.setAttribute("maintenanceActivityForEdit", maintenanceActivityForEdit);

					actionResponse.getRenderParameters().setValue("mvcPath", "/edit_activity.jsp");
				} else if (submitType.equalsIgnoreCase("delete")) {

					MaintenanceActivity maintenanceActivityForDELETE = _maintenanceActivityLocalService
							.fetchMaintenanceActivity(Long.parseLong(mid));

					deleteBooking(themeDisplay, maintenanceActivityForDELETE);
					_maintenanceActivityLocalService.deleteMaintenanceActivity(Long.parseLong(mid));
					SessionMessages.add(actionRequest, "success");

				}
			}
			_log.debug(">>>>>>>>>>>>>>>>>>>>>Into TRY deleteActivity");

		} catch (Exception e) {
			_log.error("Error deleting activity", e);
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

		_log.debug(">>>>>>>>>>>>>>>>>>>>>Into editActivity");
		String mid = ParamUtil.get(actionRequest, "mid", "");
		_log.debug("mid parameter is ==>" + mid);

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		boolean sendUsers = false;
		long companyId = themeDisplay.getCompanyId();
		String urlPortal = themeDisplay.getURLPortal().concat(HtmlUtil.escape("/group/"));

		try {

			if (mid != null && !mid.trim().isEmpty()) {
				MaintenanceActivity _maintenanceActivity = _maintenanceActivityLocalService
						.fetchMaintenanceActivity(Long.parseLong(mid));

				String activitytitle = ParamUtil.getString(actionRequest, "activitytitle");
				String duedate = ParamUtil.getString(actionRequest, "duedate");
				String status = ParamUtil.getString(actionRequest, "status");

				String oldActivityTitle = _maintenanceActivity.getActivityTitle();

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate convertedldtfrmt = LocalDate.parse(duedate, formatter);
				duedate = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH).format(convertedldtfrmt);

				// DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyyMMdd");
				long SiteId = _maintenanceActivity.getOrgSiteId();
				String oldstatus = _maintenanceActivity.getStatus();
				_maintenanceActivity.setActivityTitle(activitytitle);
				_maintenanceActivity.setDueDate(duedate);
				_maintenanceActivity.setStatus(status);

				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				_maintenanceActivity.setDueDateFormated(formato.parse(duedate));
				_log.debug("Previous status is ==>" + oldstatus);
				_log.debug("New status is ==>" + _maintenanceActivity.getStatus());

				List<Role> rolesUser = RoleLocalServiceUtil.getUserRoles(themeDisplay.getUserId());

				String bookingLink = "home";

				long BookingID = updateBooking(actionRequest, Locale.UK, _maintenanceActivity, bookingLink);
				_maintenanceActivity.setCalendarBookingId(BookingID);
				_maintenanceActivityLocalService.updateMaintenanceActivity(_maintenanceActivity);
				SessionMessages.add(actionRequest, "success");

				for (Role roles : rolesUser) {
					if (roles.getName().equals("RPA")) {
						if (status.contains("Complete") && !oldstatus.contains("Complete")) {

							try {
								_log.debug("Sending notification 50...");
								MessagesLocalServiceUtil.sendNotification50(themeDisplay.getPortalURL(),
										themeDisplay.getUserId(), SiteId, activitytitle, urlPortal, companyId);

							} catch (Exception e) {
								_log.error("Error sending notification 50 " + e);
							}

						} else if (status.contains("Open") && !oldstatus.contains("Open")) {
							try {
								_log.debug("Sending notification 52...");
								MessagesLocalServiceUtil.sendNotification52(themeDisplay.getUserId(), SiteId,
										activitytitle, urlPortal, companyId);
							} catch (Exception e) {
								_log.error("Error sending notification 52 " + e);
							}

						}
					}
				}
			}
			_log.debug(">>>>>>>>>>>>>>>>>>>>>Into TRY editActivity");
		} catch (Exception e) {
			_log.error("Error editing activity", e);
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
		String urlPortal = themeDisplay.getURLPortal().concat(HtmlUtil.escape("/group/"));

		_log.debug(">>>>>>>>>>>>>>>>>>>>> Into updatStatus ACTIVITY");
		String mid = ParamUtil.getString(actionRequest, "updateStatusId");

		_log.debug("mid parameter is ==>" + mid);

		try {
			if (mid != null && !mid.trim().isEmpty()) {
				MaintenanceActivity _maintenanceActivity = _maintenanceActivityLocalService
						.fetchMaintenanceActivity(Long.parseLong(mid));
				String status = ParamUtil.getString(actionRequest, "updateStatusVal");
				String activitytitle = _maintenanceActivity.getActivityTitle();

				_maintenanceActivity.setStatus(status);
				_maintenanceActivityLocalService.updateMaintenanceActivity(_maintenanceActivity);
//				SessionMessages.add(actionRequest, "success");

				_log.info("==============================Notification 51 ");
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
						_log.debug("Sending notification 51...");
						MessagesLocalServiceUtil.sendNotification51(themeDisplay.getUserId(),
								themeDisplay.getSiteGroupId(), activitytitle, urlPortal, themeDisplay.getCompanyId());
					} catch (Exception e) {
						_log.error("Error sending notification 51 " + e);
					}
				}
			}

		} catch (Exception e) {
			_log.error("Error updating status of activity", e);
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
	@ProcessAction(name = "redirectToAggregate")
	public void redirectToAggregate(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {

		_log.debug("==============================Into addRFI for redirect");
		// TODO > Add configuration feature for this
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		String pageName = "/activity-aggregate";
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

	@ProcessAction(name = "importAnualMaintenance")
	public void importAnualMaintenance(ActionRequest request, ActionResponse response) throws IOException {

		boolean isValid = true;
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		long userId = Long.valueOf(ParamUtil.getString(request, "userId"));
		List<MaintenanceActivity> importList = new ArrayList<MaintenanceActivity>();
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
						String cellActivity = StringPool.BLANK;
						String cellStatus = StringPool.BLANK;

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
								case 1:
									cellDueDate = dateFormat.format(mycell.getDateCellValue());
									cellDueDateFormat = mycell.getDateCellValue();
									_log.debug("cellDueDate--> " + cellDueDate);
									break;
								case 2:
									cellActivity = mycell.getStringCellValue();
									_log.debug("cellActivity--> " + cellActivity);
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
								}
							}
						}

						Group group = null;
						String orgName = null;
						try {
							group = GroupLocalServiceUtil.getGroup(cellOrgId);
							orgName = group.getDescriptiveName();
						} catch (PortalException e1) {
							_log.error("Error getting group: "+cellOrgId,e1);
						}

						_log.info("orgName --> " + orgName);
						
					if(isValid) {
						try {

							MaintenanceActivity newMaintenanaceAct = _maintenanceActivityLocalService
									.createMaintenanceActivity(
											_counterLocalService.increment(MaintenanceActivity.class.getName()));

							newMaintenanaceAct.setUserId(userId);
							newMaintenanaceAct.setUserName(UserLocalServiceUtil.getUser(userId).getFullName());
							newMaintenanaceAct.setCreateDate(new Date());
							newMaintenanaceAct.setModifiedDate(new Date());
							newMaintenanaceAct.setOrgSiteId(cellOrgId);
							newMaintenanaceAct.setSpecificParty(orgName);
							newMaintenanaceAct.setDueDate(cellDueDate);
							newMaintenanaceAct.setActivityTitle(cellActivity);
							newMaintenanaceAct.setStatus(cellStatus);
							newMaintenanaceAct.setDueDateFormated(cellDueDateFormat);

							importList.add(newMaintenanaceAct);

							_log.debug("newMaintenanaceAct----> " + newMaintenanaceAct);

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

			if (isValid && importList.size() > 0) {
				_log.debug("Creating Maintenance Activities...");
				for (MaintenanceActivity maintenanceAct : importList) {
					String bookingLink = "home";

					long BookingID = bookingAdd(Locale.UK, bookingLink, maintenanceAct, request);

					maintenanceAct.setCalendarBookingId(BookingID);
					_maintenanceActivityLocalService.addMaintenanceActivity(maintenanceAct);
				}
				SessionMessages.add(request, "success");
			}

		} else {
			SessionErrors.add(request, "noFile");
		}

	}

	private long bookingAdd(Locale locale, String desc, MaintenanceActivity MaintenanceActivity,
			ActionRequest actionRequest) {
		CalendarBooking book = null;
		try {

			DynamicQuery dqStructure = CalendarLocalServiceUtil.dynamicQuery();
			dqStructure.add(PropertyFactoryUtil.forName("groupId").like(MaintenanceActivity.getOrgSiteId()));
			List<Calendar> ddmListStructure = CalendarLocalServiceUtil.dynamicQuery(dqStructure);
			long cID = ddmListStructure.get(0).getCalendarId();
			String dueDate = MaintenanceActivity.getDueDate();

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
			String siteName = GroupLocalServiceUtil.getGroup(MaintenanceActivity.getOrgSiteId()).getDescriptiveName();
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

			titleMap.put(locale, MaintenanceActivity.getActivityTitle());
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

	public long updateBooking(ActionRequest actionRequest, Locale locale, MaintenanceActivity MaintenanceActivity,
			String desc) {
		CalendarBooking booking = null;
		long bookingId = 0;
		try {

			DynamicQuery dqStructure = CalendarLocalServiceUtil.dynamicQuery();
			dqStructure.add(PropertyFactoryUtil.forName("groupId").like(MaintenanceActivity.getOrgSiteId()));
			List<Calendar> ddmListStructure = CalendarLocalServiceUtil.dynamicQuery(dqStructure);
			long cID = ddmListStructure.get(0).getCalendarId();
			String dueDate = MaintenanceActivity.getDueDate();
			String dueDateForEnd = MaintenanceActivity.getDueDate();
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
			String siteName = GroupLocalServiceUtil.getGroup(MaintenanceActivity.getOrgSiteId()).getDescriptiveName();
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
			titleMap.put(locale, MaintenanceActivity.getActivityTitle());
			descriptionMap.put(locale, desc);

			if (MaintenanceActivity.getCalendarBookingId() != 0) {
				booking = CalendarBookingServiceUtil.updateCalendarBooking(MaintenanceActivity.getCalendarBookingId(),
						cID, titleMap, descriptionMap, "", startHourBooking, endHourBooking, false, "", 0, "email", 0,
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

	public static void deleteBooking(ThemeDisplay themedisplay, MaintenanceActivity MaintenanceActivity) {

		try {

			long calendarBId = MaintenanceActivity.getCalendarBookingId();
			_log.info("->>>>>> here is the catch error of deleting booking <<<<<<-" + calendarBId);
			if (calendarBId != 0) {
				CalendarBookingServiceUtil.deleteCalendarBooking(calendarBId);
			}
		} catch (Exception e) {
			_log.debug("->>>>>> here is the catch error of deleting booking <<<<<<-");
			e.printStackTrace();

		}

	}

	// define log for this class
	private static final Log _log = LogFactoryUtil.getLog(RecActivityAggregateWebPortlet.class.getName());

	@Reference
	protected MaintenanceActivityLocalService _maintenanceActivityLocalService;

	@Reference
	protected CounterLocalService _counterLocalService;

}
