package com.everis.validation.log.web.portlet;

import com.everis.rec.validation.log.model.ValidationLog;
import com.everis.rec.validation.log.service.ValidationLogLocalService;
import com.everis.rec.validation.log.service.ValidationLogLocalServiceUtil;
import com.everis.validation.log.web.constants.ValidationLogWebPortletKeys;
import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletModeException;
import javax.portlet.PortletURL;
import javax.portlet.ProcessAction;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.WindowState;
import javax.portlet.WindowStateException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author ialvarec
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Validation Log Web", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + ValidationLogWebPortletKeys.VALIDATIONLOGWEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class ValidationLogWebPortlet extends MVCPortlet {

	private static Log log = LogFactoryUtil.getLog(ValidationLogWebPortlet.class);

	public static String[] columnNames = { "CompanyId", "GroupId", "CreateDate", "FileName", "Uploaded By",
			"Uploaded From", "FolderId", "Log Reason" };
	public static final String COMMA = ",";
	public static final String FILENAME = "ValidationLog.csv";
	public static final String DD_MM_YYYY = "dd-MM-yyyy";

	@Reference
	protected ValidationLogLocalService _validationLogLocalService;

	@Reference
	protected CounterLocalService _counterLocalService;

	@Reference
	private Portal _portal;

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		super.doView(renderRequest, renderResponse);
	}
	/*
	 * @Override public void render(RenderRequest renderRequest, RenderResponse
	 * renderResponse) throws IOException, PortletException {
	 * log.info("******** In Render **********");
	 * 
	 * int delta = ParamUtil.getInteger(renderRequest, "delta"); int cur =
	 * ParamUtil.getInteger(renderRequest, "cur"); int from = delta * (cur - 1); int
	 * to = delta == 0 ? 3 : delta * cur; List<ValidationLog> validationsList =
	 * ValidationLogLocalServiceUtil.getValidationLogs(0,
	 * ValidationLogLocalServiceUtil.getValidationLogsCount());
	 * 
	 * LiferayPortletRequest liferayPortletRequest =
	 * _portal.getLiferayPortletRequest(renderRequest); LiferayPortletResponse
	 * liferayPortletResponse = _portal.getLiferayPortletResponse(renderResponse);
	 * 
	 * SearchContainer<ValidationLog> searchContainer = new
	 * SearchContainer<>(renderRequest, _getPortletURL(renderRequest,
	 * renderResponse), null, "There aren't validation logs");
	 * 
	 * searchContainer.setId("validationLogs");
	 * 
	 * MyManagementToolbarDisplayContext myDisplayContext = new
	 * MyManagementToolbarDisplayContext(
	 * _portal.getHttpServletRequest(renderRequest), liferayPortletRequest,
	 * liferayPortletResponse, searchContainer);
	 * 
	 * renderRequest.setAttribute("displayContext", myDisplayContext);
	 * renderRequest.setAttribute("searchContainer", searchContainer);
	 * 
	 * renderRequest.setAttribute("validationsList", validationsList);
	 * renderRequest.setAttribute("entries",
	 * ValidationLogLocalServiceUtil.getValidationLogs(from, to));
	 * 
	 * super.render(renderRequest, renderResponse); }
	 * 
	 * private PortletURL _getPortletURL(RenderRequest renderRequest, RenderResponse
	 * renderResponse) {
	 * 
	 * PortletURL portletURL = renderResponse.createRenderURL(); try {
	 * portletURL.setPortletMode(PortletMode.VIEW); } catch (PortletModeException e)
	 * { // TODO Auto-generated catch block e.printStackTrace(); } try {
	 * portletURL.setWindowState(WindowState.NORMAL); } catch (WindowStateException
	 * e) { e.printStackTrace(); }
	 * 
	 * portletURL.setParameter("mvcRenderCommandName",
	 * ValidationLogWebPortletKeys.VIEW_LANDING); portletURL.setParameter("cur",
	 * ParamUtil.getString(renderRequest, "cur"));
	 * 
	 * portletURL.setParameter("orderByCol", ParamUtil.getString(renderRequest,
	 * "orderByCol", "specificParty")); portletURL.setParameter("orderByType",
	 * ParamUtil.getString(renderRequest, "orderByType", "asc"));
	 * portletURL.setParameter("filterStatus", ParamUtil.getString(renderRequest,
	 * "filterStatus")); portletURL.setParameter("filterParty",
	 * ParamUtil.getString(renderRequest, "filterParty"));
	 * portletURL.setParameter("keywords", ParamUtil.getString(renderRequest,
	 * "keywords")); return portletURL; }
	 */

	@ProcessAction(name = "addValidationLog")
	public void addValidationLog(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
		log.debug("==============================Into addValidationLog");
		actionResponse.getRenderParameters().setValue("mvcPath", "/add_validation.jsp");
	}

	/*
	 * 
	 */
	@ProcessAction(name = "addValidationLogSubmit")
	public void addValidationLogSubmit(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
		log.debug("=============================Into addActivitySubmit");
		ParamUtil.print(actionRequest);

		try {

			String fileName = ParamUtil.getString(actionRequest, "fileName");
			String uploadedBy = ParamUtil.getString(actionRequest, "uploadedBy");
			String uploadedFrom = ParamUtil.getString(actionRequest, "uploadedFrom");
			long folderId = ParamUtil.getLong(actionRequest, "folderId");
			String logReason = ParamUtil.getString(actionRequest, "logReason");

			log.debug(">>>>>>>>>>>>>>>>>>>>>fileName " + fileName);
			log.debug(">>>>>>>>>>>>>>>>>>>>>uploadedBy " + uploadedBy);
			log.debug(">>>>>>>>>>>>>>>>>>>>>uploadedFrom " + uploadedFrom);
			log.debug(">>>>>>>>>>>>>>>>>>>>>folderId " + folderId);
			log.debug(">>>>>>>>>>>>>>>>>>>>>logReason " + logReason);

			ValidationLog _validationLog = _validationLogLocalService
					.createValidationLog(_counterLocalService.increment(ValidationLog.class.getName()));

			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			_validationLog.setCompanyId(themeDisplay.getCompanyId());
			_validationLog.setGroupId(themeDisplay.getScopeGroupId());
			_validationLog.setCreateDate(new Date());
			_validationLog.setFileName(fileName);
			_validationLog.setUploadedBy(uploadedBy);
			_validationLog.setUploadedFrom(uploadedFrom);

			_validationLog.setFolderId(folderId);
			_validationLog.setLogReason(logReason);

			_validationLogLocalService.addValidationLog(_validationLog);

		} catch (Exception e) {
			log.error("Error adding new activity", e);
		}

	}

	/**
	 * Method to export the content in a CSV file
	 */
	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) {

		log.info(" **** In exportCSV Method *****");
		// String url = ParamUtil.getString(resourceRequest, "export");

		try {

			StringBundler sb = new StringBundler();
			for (String columnName : columnNames) {
				sb.append(getCSVFormattedValue(String.valueOf(columnName)));
				sb.append(COMMA);
			}
			sb.setIndex(sb.index() - 1);
			sb.append(CharPool.NEW_LINE);
			List<ValidationLog> validationsList = ValidationLogLocalServiceUtil.getValidationLogs(0,
					ValidationLogLocalServiceUtil.getValidationLogsCount());
			validationsList.forEach(validationlog -> {
				String stringDate;
				try {
					stringDate = getDateToString(validationlog);
					/*
					 * sb.append(getCSVFormattedValue(String.valueOf(validationlog.
					 * getValidationLogId()))); sb.append(COMMA);
					 */
					sb.append(getCSVFormattedValue(String.valueOf(validationlog.getCompanyId())));
					sb.append(COMMA);
					sb.append(getCSVFormattedValue(String.valueOf(validationlog.getGroupId())));
					sb.append(COMMA);
					sb.append(getCSVFormattedValue(String.valueOf(stringDate)));
					sb.append(COMMA);
					sb.append(getCSVFormattedValue(String.valueOf(validationlog.getFileName())));
					sb.append(COMMA);
					sb.append(getCSVFormattedValue(String.valueOf(validationlog.getUploadedBy())));
					sb.append(COMMA);
					sb.append(getCSVFormattedValue(String.valueOf(validationlog.getUploadedFrom())));
					sb.append(COMMA);
					sb.append(getCSVFormattedValue(String.valueOf(validationlog.getFolderId())));
					sb.append(COMMA);
					sb.append(getCSVFormattedValue(String.valueOf(validationlog.getLogReason())));
					sb.append(COMMA);
					sb.setIndex(sb.index() - 1);
					sb.append(CharPool.NEW_LINE);
				} catch (PortalException e) {
					log.error("Error while export data : " + e);
				}
			});

			byte[] bytes = sb.toString().getBytes();
			String contentType = ContentTypes.APPLICATION_TEXT;

			PortletResponseUtil.sendFile(resourceRequest, resourceResponse, FILENAME, bytes, contentType);

		} catch (Exception e) {
			log.error("Exception While export CSV file : ", e);
		}

	}

	/**
	 * Transform the format of a date in a string
	 * 
	 * @param val
	 * @return
	 * @throws PortalException
	 */
	private String getDateToString(ValidationLog val) throws PortalException {
		Date createDate = val.getCreateDate();
		SimpleDateFormat formatter = new SimpleDateFormat(DD_MM_YYYY);
		String stringDate = formatter.format(createDate);
		return stringDate;
	}

	/**
	 * Give csv format of a string parameter
	 * 
	 * @param value
	 * @return
	 */
	private String getCSVFormattedValue(String value) {
		StringBundler sb = new StringBundler(3);
		sb.append(CharPool.QUOTE);
		sb.append(StringUtil.replace(value, CharPool.QUOTE, StringPool.DOUBLE_QUOTE));
		sb.append(CharPool.QUOTE);
		return sb.toString();
	}
}