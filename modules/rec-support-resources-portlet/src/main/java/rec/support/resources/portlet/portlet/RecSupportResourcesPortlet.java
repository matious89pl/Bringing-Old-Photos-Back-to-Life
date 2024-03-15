package rec.support.resources.portlet.portlet;

import com.everis.cproposal.service.recFormArticleLocalServiceUtil;
import com.everis.messages.service.builder.service.MessagesLocalServiceUtil;
import com.liferay.calendar.constants.CalendarBookingConstants;
import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.service.CalendarBookingServiceUtil;
import com.liferay.calendar.service.CalendarLocalServiceUtil;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.UserGroupRole;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
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

import rec.support.resources.portlet.constants.RecSupportResourcesPortletKeys;
import rec.supporting.resources.model.supportR;
import rec.supporting.resources.service.supportRLocalService;

/**
 * @author sarsolis
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=Rec",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=RecSupportResources", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.config-template=/configuration.jsp",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.init-param.add-process-action-success-action=false",
		"javax.portlet.name=" + RecSupportResourcesPortletKeys.RECSUPPORTRESOURCES,
		"javax.portlet.resource-bundle=content.Language", "javax.portlet.security-role-ref=power-user,user",
		"com.liferay.portlet.action-url-redirect=true" }, service = Portlet.class)
public class RecSupportResourcesPortlet extends MVCPortlet {
	private static final Log log = LogFactoryUtil.getLog(RecSupportResourcesPortlet.class.getName());

	@Reference
	supportRLocalService _supportRLocalService;
	@Reference
	GroupLocalService _groupLocalService;

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		log.debug("------- In RecSupportResourcesPortlet doView ------- ");
		super.doView(renderRequest, renderResponse);
	}

	@ProcessAction(name = "goBack")
	public void goBack(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
	}

	/**
	 * This method is used to Add the records for |REC Mantainance and Activity
	 * Records|
	 * 
	 * @param actionRequest  This is the ActionRequest of the Portlet .
	 * @param actionResponse This is the ActionResponse of the Portlet .
	 * @return void This returns nothing.
	 * 
	 */
	@ProcessAction(name = "addNewSupportResource")
	public void addNewSupportResource(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
		log.debug("==============================Into Add Supporting Resource Action");
		actionResponse.getRenderParameters().setValue("mvcPath", "/add_newRecord.jsp");
	}

	@ProcessAction(name = "addSupportResourceSubmit")
	public void addSupportingResourceSubmit(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
		log.debug("----------------- Into Add Supporting Resource Action submit-----------------");
		supportR supportingResource = _supportRLocalService
				.createsupportR(CounterLocalServiceUtil.increment(supportR.class.getName()));

//        ThemeDisplay themeDisplay= (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
//        PortletDisplay portletDisplay= themeDisplay.getPortletDisplay();
//        String portletId= portletDisplay.getId();

//        log.info("This is the protlet ID ->>>>> " + portletId);

		try {
			String type = ParamUtil.getString(actionRequest, "type");
			String title = ParamUtil.getString(actionRequest, "title");
			String description = ParamUtil.getString(actionRequest, "description");
			String duedate = ParamUtil.getString(actionRequest, "duedate");
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate convertedldtfrmt = LocalDate.parse(duedate, formatter);
			duedate = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH).format(convertedldtfrmt);
			String status = ParamUtil.getString(actionRequest, "status");
			String link = ParamUtil.getString(actionRequest, "link");
			if (!link.contains("http")) {
				link = "http://" + link;
			}
			String displayLink = ParamUtil.getString(actionRequest, "displayedLink");
			supportingResource.setType(type);
			supportingResource.setTitle(title);
			supportingResource.setDescription(description);
			supportingResource.setDueDate(format.parse(duedate));
			supportingResource.setStatus(status);
			supportingResource.setLink(link);
			supportingResource.setDisplayLink(displayLink);
			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();
			String portletId = portletDisplay.getId();

			log.info("This is the protlet ID ->>>>> " + portletId);
			log.info("This is the protlet URLEdit ->>>>> " + portletDisplay.getURLEdit());

			Long userId = themeDisplay.getUserId();
			supportingResource.setCompanyId(themeDisplay.getCompanyId());
			supportingResource.setUserId(userId);
			String userName = UserLocalServiceUtil.fetchUser(userId).getFullName();
			supportingResource.setUserName(userName);
			supportingResource.setOrgSiteId(themeDisplay.getScopeGroupId());
			Group group = _groupLocalService.getGroup(supportingResource.getOrgSiteId());
			supportingResource.setSpecificParty(group.getName(themeDisplay.getLocale()));

			String bookingLink = "training-surveys-and-forms";
			long ID = bookingAdd(Locale.UK, title, bookingLink, supportingResource, actionRequest, duedate);
			log.info("Bookign ID..." + ID);
			supportingResource.setCalendarBookingId(ID);
			_supportRLocalService.addsupportR(supportingResource);
			SessionMessages.add(actionRequest, "success");

			try {
				String urlPortal = themeDisplay.getURLPortal().concat(HtmlUtil.escape("/group/"));
				long companyId = themeDisplay.getCompanyId();
				MessagesLocalServiceUtil.sendNotification69(themeDisplay.getUserId(), themeDisplay.getSiteGroupId(),
						title, urlPortal, companyId);

			} catch (Exception e) {
				log.error("Error sending Notification69", e);
			}
		} catch (Exception e) {
			log.error("Error adding new supporting resource", e);
		}
	}

	@ProcessAction(name = "editSupportResource")
	public void editSupportingResource(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
		log.debug("----------------- Into Edit Supporting Resource Action -----------------");
		String mid = ParamUtil.get(actionRequest, "midEdit", "");
		try {
			supportR supportingResource = _supportRLocalService.fetchsupportR(Long.parseLong(mid));
			actionRequest.getPortletSession().setAttribute("supportingResourceEdit", supportingResource,
					PortletSession.APPLICATION_SCOPE);
			actionRequest.setAttribute("supportingResource", supportingResource);
			actionResponse.getRenderParameters().setValue("mvcPath", "/edit_record.jsp");
		} catch (Exception e) {
			log.error("Error editing a supporting resource", e);
		}
	}

	@ProcessAction(name = "editSupportResourceSubmit")
	public void editSupportResourceSubmit(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
		log.debug("----------------- Into Edit Supporting Resource Action Submit-----------------");
		String mid = ParamUtil.get(actionRequest, "resourceId", "");
		boolean sendUsers = false;
		try {
			if (mid != null && !mid.trim().isEmpty()) {
				supportR supportingResource = _supportRLocalService.fetchsupportR(Long.parseLong(mid));
				ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
				long SiteId = supportingResource.getOrgSiteId();
				String oldstatus = supportingResource.getStatus();
				long companyId = themeDisplay.getCompanyId();
				String originalTitle = supportingResource.getTitle();
				String urlPortal = themeDisplay.getURLPortal().concat(HtmlUtil.escape("/group/"));
				String type = ParamUtil.getString(actionRequest, "type");
				String title = ParamUtil.getString(actionRequest, "title");
				String description = ParamUtil.getString(actionRequest, "description");
				String duedate = ParamUtil.getString(actionRequest, "duedate");
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate convertedldtfrmt = LocalDate.parse(duedate, formatter);
				duedate = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH).format(convertedldtfrmt);
				log.info("title >>>" + title + " originalTitle >>>> " + originalTitle);
				String status = ParamUtil.getString(actionRequest, "status");
				String link = ParamUtil.getString(actionRequest, "link");
				if (!link.contains("http")) {
					link = "http://" + link;
				}
				String displayLink = ParamUtil.getString(actionRequest, "displayedLink");
				supportingResource.setType(type);
				supportingResource.setTitle(title);
				supportingResource.setDescription(description);
				supportingResource.setDueDate(format.parse(duedate));
				supportingResource.setStatus(status);
				supportingResource.setLink(link);
				supportingResource.setDisplayLink(displayLink);

				String bookingLink = "training-surveys-and-forms";

				long BookingId = updateBooking(actionRequest, Locale.UK, supportingResource, bookingLink, duedate);
				supportingResource.setCalendarBookingId(BookingId);
				_supportRLocalService.updatesupportR(supportingResource);
				SessionMessages.add(actionRequest, "success");
				List<Role> rolesUser = RoleLocalServiceUtil.getUserRoles(themeDisplay.getUserId());
				for (Role roles : rolesUser) {
					if (roles.getName().equals("RPA")) {
						if (status.contains("Complete") && !oldstatus.contains("Complete")) {
							log.debug("Entry to complete...");
							try {
								log.debug("Sending notification 70...");
								MessagesLocalServiceUtil.sendNotification70(themeDisplay.getUserId(), SiteId, title,
										urlPortal, companyId);
								// log.info("title >>>" + title + " originalTitle >>>> " + originalTitle);
							} catch (Exception e) {
								log.error("Error sending notification 70 " + e);
							}
						} else if (status.contains("Open") && !oldstatus.contains("Open")) {
							log.debug("Entry to open...");
							try {
								log.debug("Sending notification 72...");
								MessagesLocalServiceUtil.sendNotification72(themeDisplay.getUserId(), SiteId, title,
										urlPortal, companyId);

							} catch (Exception e) {
								log.error("Error sending notification 72 " + e);
							}
						}
					}

				}

			}
		} catch (Exception e) {
			log.error("Error editing a supporting resource", e);
		}
	}

	@ProcessAction(name = "deleteSupportResource")
	public void deleteSupportResource(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
		log.debug("----------------- Into Delete Support Resource -----------------");
//      supportR supportingResource = _supportRLocalService
//              .createsupportR(CounterLocalServiceUtil.increment(supportR.class.getName()));
		// String originalTitle = supportingResource.getTitle();
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		String mid = ParamUtil.getString(actionRequest, "mid", "");
		supportR supportingResource = _supportRLocalService.fetchsupportR(Long.parseLong(mid));
		String originalTitle = supportingResource.getTitle();
		try {
			_supportRLocalService.deletesupportR(Long.parseLong(mid));
			log.info(" originalTitle >>>> " + originalTitle);
			deleteBooking(themeDisplay, supportingResource);
			SessionMessages.add(actionRequest, "success");
		} catch (PortalException e) {
			log.error("Error deleting a supporting resource", e);
		}
	}

	@ProcessAction(name = "updateStatus")
	public void updateStatus(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		boolean sendUsers = false;
		String urlPortal = themeDisplay.getURLPortal().concat(HtmlUtil.escape("/group/"));
		log.debug("----------------- Into Update Status Support Resource -----------------");
		try {
			String mid = ParamUtil.getString(actionRequest, "updateStatusId");
			if (mid != null && !mid.trim().isEmpty()) {
				supportR supportR = _supportRLocalService.fetchsupportR(Long.parseLong(mid));
				String status = ParamUtil.getString(actionRequest, "updateStatusVal");
				String title = supportR.getTitle();
				supportR.setStatus(status);
				_supportRLocalService.updatesupportR(supportR);
//                SessionMessages.add(actionRequest, "success");
				log.info("==============================Notification 71 ");
				List<UserGroupRole> userGroupRoles = UserGroupRoleLocalServiceUtil
						.getUserGroupRoles(themeDisplay.getUserId(), themeDisplay.getSiteGroupId());
				for (UserGroupRole userGroupRole : userGroupRoles) {
					if (userGroupRole.getRole().getName().equals("REC Contract Managers")
							|| userGroupRole.getRole().getName().equals("REC_Performance_Assurance")
							|| userGroupRole.getRole().getName().equals("Master Administrative User")) {
						sendUsers = true;
					}
				}
				log.info("Boolean sendUsers: " + sendUsers);
				if (sendUsers) {
					try {
						log.debug("Sending notification 71...");
						MessagesLocalServiceUtil.sendNotification71(themeDisplay.getUserId(),
								themeDisplay.getSiteGroupId(), title, urlPortal, themeDisplay.getCompanyId());
					} catch (Exception e) {
						log.error("Error sending notification 71 " + e);
					}
				}
			}
		} catch (Exception e) {
			log.error("Error updating status of activity", e);
		}
	}

	@ProcessAction(name = "importSupportResource")
	public void importSupportResource(ActionRequest request, ActionResponse response) throws IOException {
		log.debug("----------------- Into importSupportResource  -----------------");
		boolean isValid = true;
		List<supportR> supportImportList = new ArrayList<supportR>();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		long userId = Long.valueOf(ParamUtil.getString(request, "userId"));
		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(request);
		ThemeDisplay themeDisplay = (ThemeDisplay) uploadRequest.getAttribute(WebKeys.THEME_DISPLAY);
		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

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
						log.debug("----------------------------------------------");
						Row row = _iterator.next();
						long cellOrgId = 0;
						// String cellPartyType = StringPool.BLANK;
						String cellDueDate = StringPool.BLANK;
						Date cellDueDateFormat = new Date();
						String cellTitle = StringPool.BLANK;
						String cellStatus = StringPool.BLANK;
						String cellType = StringPool.BLANK;
						String cellDescription = StringPool.BLANK;
						String cellLink = StringPool.BLANK;
						String cellDisplayLink = StringPool.BLANK;
						for (Cell mycell : row) {
							int columnIndex = mycell.getColumnIndex();
							log.debug("columnIndex--> " + columnIndex);
							if (mycell.getCellType() == CellType.BLANK) {
								SessionErrors.add(request, "incorrectFormat");
								isValid = false;
								break;
							} else {
								switch (columnIndex) {
								case 0:
									if (mycell.getCellType() == CellType.NUMERIC) {
										cellOrgId = Math.round(mycell.getNumericCellValue());
										log.debug("cellOrgId--> " + cellOrgId);
										break;
									} else {
										log.debug("CellType is not numeric for orgId");
										SessionErrors.add(request, "incorrectFormat");
										isValid = false;
										break;
									}
//                                case 1:
//                                    cellPartyType = mycell.getStringCellValue();
//                                    log.debug("cellPartyType--> " + cellPartyType);
//                                    break;
								case 1:
									cellDueDate = dateFormat.format(mycell.getDateCellValue());
									cellDueDateFormat = mycell.getDateCellValue();
									log.debug("cellDueDate--> " + cellDueDateFormat);
									break;
								case 2:
									cellTitle = mycell.getStringCellValue();
									log.debug("cellTitle--> " + cellTitle);
									break;
								case 3:
									if (mycell.getStringCellValue().trim().toLowerCase().equals("open")
											|| mycell.getStringCellValue().trim().toLowerCase().equals("pending")
											|| mycell.getStringCellValue().trim().toLowerCase().equals("complete")) {
										cellStatus = mycell.getStringCellValue();
										log.debug("cellStatus--> " + cellStatus);
									} else {
										log.info("Adding error message. cellstatus equals "+ cellStatus);
										SessionErrors.add(request, "invalidStatus");
										isValid = false;
									}
									break;
								case 4:
									cellType = mycell.getStringCellValue();
									log.debug("cellType--> " + cellType);
									break;
								case 5:
									cellDescription = mycell.getStringCellValue();
									log.debug("cellDescription--> " + cellDescription);
									break;
								case 6:
									cellLink = mycell.getStringCellValue();
									log.debug("cellLink--> " + cellLink);
									break;
								case 7:
									cellDisplayLink = mycell.getStringCellValue();
									log.debug("cellDisplayLink--> " + cellDisplayLink);
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
						log.info("orgName --> " + orgName);
						
					if(isValid) {
						try {
							supportR supportResource = _supportRLocalService
									.createsupportR(CounterLocalServiceUtil.increment(supportR.class.getName()));
							supportResource.setUserId(userId);
							supportResource.setUserName(UserLocalServiceUtil.getUser(userId).getFullName());
							supportResource.setCreateDate(new Date());
							supportResource.setModifiedDate(new Date());
							supportResource.setOrgSiteId(cellOrgId);
							supportResource.setSpecificParty(orgName);
							supportResource.setDueDate(cellDueDateFormat);
							supportResource.setType(cellType);
							supportResource.setTitle(cellTitle);
							supportResource.setDescription(cellDescription);
							supportResource.setStatus(cellStatus);
							supportResource.setLink(cellLink);
							supportResource.setDisplayLink(cellDisplayLink);
							/*
							 * ThemeDisplay themeDisplay = (ThemeDisplay)
							 * actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
							 * supportResource.setCompanyId(themeDisplay.getCompanyId());
							 * supportResource.setUserId(themeDisplay.getUserId());
							 */
//                            Group group = _groupLocalService.getGroup(cellOrgId);
//                           
//                            supportResource.setSpecificParty(group.getName(themeDisplay.getLocale()));
							log.debug("supportResource to add ----> " + supportResource);
							supportImportList.add(supportResource);
						} catch (Exception e) {
							log.error("Error creating Supporting Resource", e);
							SessionErrors.add(request, "errorImporting");
							isValid = false;
							break;
						}
					} else {
						log.info("Import failed. Invalid row.");
						break;
					}
						log.debug("----------------------------------------------");
					}
				}
				_inputStream.close();
				_workbook.close();
			} else {
				log.debug("Supporting Resource - Extension incorrect");
				isValid = false;
				SessionErrors.add(request, "incorrectExt");
			}
			if (isValid && supportImportList.size() > 0) {
				log.debug("Creating Supporting Resource...");
				for (supportR supportR : supportImportList) {

					if (supportR.getDueDate() != null) {
						String title = supportR.getTitle();

						long supporOrgID = supportR.getOrgSiteId();
						Date dueDate = supportR.getDueDate();

						log.info("Due date date..." + dueDate);
						log.info("Due date string..." + dueDate.toString());

						/*
						String dateStr = dueDate.toString();
						DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy");
						dueDate1 = formatter1.format(formatter.parse(dateStr));
						*/
						DateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");

						String dueDate1 = "";
						try {
							dueDate1 = formatter1.format(dueDate);
							log.info("dueDate1..." + dueDate1);
						} catch (Exception e) {
							log.info("Exception dueDate1..." + dueDate1);
						}

						String bookingLink = "training-surveys-and-forms";
						long ID = bookingAdd(Locale.UK, title, bookingLink, supportR, request, dueDate1);
						log.info("Bookign ID..." + ID);
						supportR.setCalendarBookingId(ID);
						_supportRLocalService.addsupportR(supportR);
						
					}
				}
				SessionMessages.add(request, "success");
			}
		} else {
			SessionErrors.add(request, "noFile");
		}
	}

	private long bookingAdd(Locale locale, String title, String desc, supportR supportingR, ActionRequest actionRequest,
			String dueDate) {
		CalendarBooking book = null;
		try {

			DynamicQuery dqStructure = CalendarLocalServiceUtil.dynamicQuery();
			dqStructure.add(PropertyFactoryUtil.forName("groupId").like(supportingR.getOrgSiteId()));
			List<Calendar> ddmListStructure = CalendarLocalServiceUtil.dynamicQuery(dqStructure);
			long cID = ddmListStructure.get(0).getCalendarId();
			String dueDateForEnd = dueDate;
			long startHourBooking = 0L;
			long endHourBooking = 0L;
			String endDueDate = "";

			dueDate = dueDate + " 09:00:00";
			endDueDate = dueDateForEnd + " 10:00:00";

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

//            	sdf.setTimeZone(TimeZone.getTimeZone("Europe/London"));
//            	 log.debug("timezonee " + sdf.getTimeZone());
			Date date = sdf.parse(dueDate);
			startHourBooking = date.getTime();

			Date DuedateBooking = sdf.parse(endDueDate);
			endHourBooking = DuedateBooking.getTime();

			char ch = '-';
			String siteName = GroupLocalServiceUtil.getGroup(supportingR.getOrgSiteId()).getDescriptiveName();
			String siteNameLink = siteName.replace(' ', ch);

			String siteNameLinkToLowerCase = siteNameLink.toLowerCase();

			desc = "<a href=" + "/group/" + siteNameLinkToLowerCase + "/" + desc + " target=_blank >Click Here</a>"
					+ "Due date: " + dueDate;
			log.info("siteNameLinkToLowerCase value: " + siteNameLinkToLowerCase);

			log.info("desc value: " + desc);

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
			log.debug("->>>>>> here is the catch error of adding booking <<<<<<-");
			e.printStackTrace();
		}
		return book.getCalendarBookingId();

	}

	public long updateBooking(ActionRequest actionRequest, Locale locale, supportR supportingR, String desc,
			String dueDate) {
		CalendarBooking booking = null;
		long bookingId = 0;
		try {

			DynamicQuery dqStructure = CalendarLocalServiceUtil.dynamicQuery();
			dqStructure.add(PropertyFactoryUtil.forName("groupId").like(supportingR.getOrgSiteId()));
			List<Calendar> ddmListStructure = CalendarLocalServiceUtil.dynamicQuery(dqStructure);
			long cID = ddmListStructure.get(0).getCalendarId();

			String dueDateForEnd = dueDate;
			long startHourBooking, endHourBooking = 0L;

			String endDueDate = "";

			dueDate = dueDate + " 09:00:00";
			endDueDate = dueDateForEnd + " 10:00:00";

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

			// sdf.setTimeZone(TimeZone.getTimeZone("Europe/London"));
			// log.debug("timezonee " + sdf.getTimeZone());
			Date date = sdf.parse(dueDate);
			startHourBooking = date.getTime();

			Date DuedateBooking = sdf.parse(endDueDate);
			endHourBooking = DuedateBooking.getTime();

			char ch = '-';
			String siteName = GroupLocalServiceUtil.getGroup(supportingR.getOrgSiteId()).getDescriptiveName();
			String siteNameLink = siteName.replace(' ', ch);

			String siteNameLinkToLowerCase = siteNameLink.toLowerCase();
			desc = "<a href=" + "/group/" + siteNameLinkToLowerCase + "/" + desc + " target=_blank >Click Here</a>"
					+ "Due date: " + dueDate;
			log.info("siteNameLinkToLowerCase value: " + siteNameLinkToLowerCase);

			log.info("desc value: " + desc);

			Map<Locale, String> titleMap = new HashMap<Locale, String>();
			Map<Locale, String> descriptionMap = new HashMap<Locale, String>();
			ServiceContext serviceContextC = ServiceContextFactory.getInstance(CalendarBooking.class.getName(),
					actionRequest);
			titleMap.put(locale, supportingR.getTitle());
			descriptionMap.put(locale, desc);

			if (supportingR.getCalendarBookingId() != 0) {
				booking = CalendarBookingServiceUtil.updateCalendarBooking(supportingR.getCalendarBookingId(), cID,
						titleMap, descriptionMap, "", startHourBooking, endHourBooking, false, "", 0, "email", 0,
						"email", serviceContextC);
				bookingId = booking.getCalendarBookingId();
			} else {
				log.info("->>>>>> Calendarbooking not update as this item is an old one <<<<<<-");
			}

		} catch (PortalException | ParseException e) {

			log.error("->>>>>> here is the catch error of updating booking <<<<<<-" + e);

		}

		return bookingId;
	}

	public static void deleteBooking(ThemeDisplay themedisplay, supportR supportR) {

		try {

			long calendarBId = supportR.getCalendarBookingId();
			log.info("->>>>>> here is the catch error of deleting booking <<<<<<-" + calendarBId);

			if (calendarBId != 0) {
				CalendarBookingServiceUtil.deleteCalendarBooking(calendarBId);
			}
		} catch (Exception e) {
			log.debug("->>>>>> here is the catch error of deleting booking <<<<<<-");
			e.printStackTrace();

		}
	}

}
