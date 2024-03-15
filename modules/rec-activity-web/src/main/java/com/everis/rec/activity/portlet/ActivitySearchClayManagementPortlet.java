package com.everis.rec.activity.portlet;

import com.everis.messages.service.builder.service.MessagesLocalServiceUtil;
import com.everis.rec.activity.config.ActivityConfiguration;
import com.everis.rec.activity.constants.ActivitySearchClayManagementPortletKeys;
import com.everis.rec.maintenanceactivity.model.MaintenanceActivity;
import com.everis.rec.maintenanceactivity.service.MaintenanceActivityLocalServiceUtil;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

/**
 * @author Miquel Bada
 */
@Component(immediate = true, configurationPid = ActivitySearchClayManagementPortletKeys.ConfigurationId, property = {

		"com.liferay.portlet.display-category=category.sample", "com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true", "javax.portlet.display-name=ActivitySearchClayManagement",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/html/searchcontainer/view.jsp",
		"javax.portlet.init-param.config-template=/html/searchcontainer/configuration.jsp",
		"javax.portlet.name=" + ActivitySearchClayManagementPortletKeys.ACTIVITYEARCHCLAYMANAGEMENT,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.init-param.add-process-action-success-action=false",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class ActivitySearchClayManagementPortlet extends MVCPortlet {

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		renderRequest.setAttribute(ActivityConfiguration.class.getName(), _activityConfiguration);

		super.doView(renderRequest, renderResponse);
	}

	public String getString(Map labels) {
		return (String) labels.get(_activityConfiguration.aggregateView());
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

		actionResponse.getRenderParameters().setValue("mvcPath", "/html/searchcontainer/add_activity.jsp");

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

			_log.debug(">>>>>>>>>>>>>>>>>>>>>activitytitle " + activitytitle);
			_log.debug(">>>>>>>>>>>>>>>>>>>>>duedate " + duedate);
			_log.debug(">>>>>>>>>>>>>>>>>>>>>status " + status);

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate convertedldtfrmt = LocalDate.parse(duedate, formatter);
			duedate = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH).format(convertedldtfrmt);

			MaintenanceActivity _maintenanceActivity = MaintenanceActivityLocalServiceUtil
					.createMaintenanceActivity(CounterLocalServiceUtil.increment(MaintenanceActivity.class.getName()));

			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			long userId = themeDisplay.getUserId();

			_maintenanceActivity.setUserId(userId);
			_maintenanceActivity.setOrgSiteId(themeDisplay.getScopeGroupId());
			_maintenanceActivity.setSpecificParty(themeDisplay.getScopeGroupName());

			_log.debug("themeDisplay.getSiteGroupName() ==== " + themeDisplay.getSiteGroupName());

			_maintenanceActivity.setActivityTitle(activitytitle);
			_maintenanceActivity.setDueDate(duedate);

			// TODO add remaining parameters

			_maintenanceActivity.setStatus(status);

			MaintenanceActivityLocalServiceUtil.addMaintenanceActivity(_maintenanceActivity);

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

		_log.debug("mid parameter is ==>" + mid);
		_log.debug("submitType parameter is ==>" + submitType);

		try {
			if ((submitType != null && !submitType.trim().isEmpty()) && (mid != null && !mid.trim().isEmpty())) {
				if (submitType.equalsIgnoreCase("edit")) {
					MaintenanceActivity maintenanceActivityForEdit = MaintenanceActivityLocalServiceUtil
							.fetchMaintenanceActivity(Long.parseLong(mid));

					String duedateedit = maintenanceActivityForEdit.getDueDate();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					LocalDate convertedldtfrmt = LocalDate.parse(duedateedit, formatter);
					duedateedit = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(convertedldtfrmt);
					maintenanceActivityForEdit.setDueDate(duedateedit);

					actionRequest.setAttribute("maintenanceActivityForEdit", maintenanceActivityForEdit);

					actionResponse.getRenderParameters().setValue("mvcPath", "/html/searchcontainer/edit_activity.jsp");
				} else if (submitType.equalsIgnoreCase("delete")) {
					MaintenanceActivityLocalServiceUtil.deleteMaintenanceActivity(Long.parseLong(mid));
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

		try {
			if (mid != null && !mid.trim().isEmpty()) {

				MaintenanceActivity _maintenanceActivity = MaintenanceActivityLocalServiceUtil
						.fetchMaintenanceActivity(Long.parseLong(mid));

				String activitytitle = ParamUtil.getString(actionRequest, "activitytitle");
				String duedate = ParamUtil.getString(actionRequest, "duedate");
				String status = ParamUtil.getString(actionRequest, "status");

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate convertedldtfrmt = LocalDate.parse(duedate, formatter);
				duedate = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH).format(convertedldtfrmt);

				_maintenanceActivity.setActivityTitle(activitytitle);
				_maintenanceActivity.setDueDate(duedate);
				_maintenanceActivity.setStatus(status);

				MaintenanceActivityLocalServiceUtil.updateMaintenanceActivity(_maintenanceActivity);

			}
			_log.debug(">>>>>>>>>>>>>>>>>>>>>Into TRY editActivity");
		} catch (Exception e) {
			_log.error("Error editing activity", e);
		}
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
						String cellPartyType = StringPool.BLANK;
						String cellDueDate = StringPool.BLANK;
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
									cellPartyType = mycell.getStringCellValue();
									_log.debug("cellPartyType--> " + cellPartyType);
									break;
								case 2:
									cellDueDate = dateFormat.format(mycell.getDateCellValue());
									_log.debug("cellDueDate--> " + cellDueDate);
									break;
								case 3:
									cellActivity = mycell.getStringCellValue();
									_log.debug("cellActivity--> " + cellActivity);
									break;
								case 4:
									cellStatus = mycell.getStringCellValue();
									_log.debug("cellStatus--> " + cellStatus);
									break;
								}
							}
						}

						try {

							MaintenanceActivity newMaintenanaceAct = MaintenanceActivityLocalServiceUtil
									.createMaintenanceActivity(
											CounterLocalServiceUtil.increment(MaintenanceActivity.class.getName()));
							newMaintenanaceAct.setUserId(userId);
							newMaintenanaceAct.setUserName(UserLocalServiceUtil.getUser(userId).getFullName());
							newMaintenanaceAct.setCreateDate(new Date());
							newMaintenanaceAct.setModifiedDate(new Date());
							newMaintenanaceAct.setOrgSiteId(cellOrgId);
							newMaintenanaceAct.setSpecificParty(cellPartyType);
							newMaintenanaceAct.setDueDate(cellDueDate);
							newMaintenanaceAct.setActivityTitle(cellActivity);
							newMaintenanaceAct.setStatus(cellStatus);

							importList.add(newMaintenanaceAct);

							_log.debug("newMaintenanaceAct----> " + newMaintenanaceAct);
						} catch (Exception e) {
							_log.error("Error creating Maintenance Activity ", e);
							SessionErrors.add(request, "errorImporting");
							isValid = false;
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
					MaintenanceActivityLocalServiceUtil.addMaintenanceActivity(maintenanceAct);
				}
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

		_log.info("ActivityTitle WEB... ");
		_log.debug(">>>>>>>>>>>>>>>>>>>>> Into updatStatus CLAY");
		String mid = ParamUtil.getString(actionRequest, "updateStatusId");

		_log.debug("mid parameter is ==>" + mid);

		try {
			if (mid != null && !mid.trim().isEmpty()) {
				MaintenanceActivity _maintenanceActivity = MaintenanceActivityLocalServiceUtil
						.fetchMaintenanceActivity(Long.parseLong(mid));
				String status = ParamUtil.getString(actionRequest, "updateStatusVal");

				_maintenanceActivity.setStatus(status);
				MaintenanceActivityLocalServiceUtil.updateMaintenanceActivity(_maintenanceActivity);
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

	@Activate
	@Modified
	protected void activate(Map<Object, Object> properties) {
		_activityConfiguration = ConfigurableUtil.createConfigurable(ActivityConfiguration.class, properties);
	}

	private volatile ActivityConfiguration _activityConfiguration;

	// define log for this class
	private static final Log _log = LogFactoryUtil.getLog(ActivitySearchClayManagementPortlet.class.getName());

}
