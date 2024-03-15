package com.everis.validation.log.web.portlet;

import com.everis.rec.validation.log.model.ValidationLog;
import com.everis.rec.validation.log.service.ValidationLogLocalService;
import com.everis.validation.log.web.constants.ValidationLogWebPortletKeys;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Order;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletModeException;
import javax.portlet.PortletSession;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;
import javax.portlet.WindowStateException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * MVC command for showing the assignments list.
 * 
 * @author liferay
 */
@Component(immediate = true, property = { "javax.portlet.name=" + ValidationLogWebPortletKeys.VALIDATIONLOGWEB,
		"mvc.command.name=/",
		"mvc.command.name=" + ValidationLogWebPortletKeys.VIEW_LANDING }, service = MVCRenderCommand.class)
public class SearchMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		// Add assignment list related attributes.
		addAssignmentListAttributes(renderRequest);
		// Add Clay management toolbar related attributes.
		addManagementToolbarAttributes(renderRequest, renderResponse);
		return "/view.jsp";
	}

	/**
	 * Adds assigment list related attributes to the request.
	 * 
	 * @param renderRequest
	 */
	private void addAssignmentListAttributes(RenderRequest renderRequest) {

		// Resolve start and end for the search.
		int currentPage = ParamUtil.getInteger(renderRequest, SearchContainer.DEFAULT_CUR_PARAM,
				SearchContainer.DEFAULT_CUR);
		int delta = 3;// ParamUtil.getInteger(renderRequest, SearchContainer.DEFAULT_DELTA_PARAM,
						// SearchContainer.DEFAULT_DELTA);

		String portletConfigAggregateView = renderRequest.getPreferences().getValue("aggregateView", "No");
		renderRequest.getPortletSession().setAttribute("aggregateView", portletConfigAggregateView,
				PortletSession.APPLICATION_SCOPE);

		int start = ((currentPage > 0) ? (currentPage - 1) : 0) * delta;
		int end = start + delta;

		String orderByCol = ParamUtil.getString(renderRequest, "orderByCol", "fileName");
		String orderByType = ParamUtil.getString(renderRequest, "orderByType", "asc");
		String filterName = ParamUtil.getString(renderRequest, "filterFileName", "none");
		String filterUploadedBy = ParamUtil.getString(renderRequest, "filterUploadedBy");
		String filterUploadedFrom = ParamUtil.getString(renderRequest, "filterUploadedFrom");
		String keywords = ParamUtil.getString(renderRequest, "keywords");

		// Call the service to get the list of assignments

		List<ValidationLog> validationLogList = new ArrayList<ValidationLog>();
		List<ValidationLog> validationLogsListTotal = new ArrayList<ValidationLog>();
		DynamicQuery dynamicQueryActivity = _validationLogLocalService.dynamicQuery();

		if (Validator.isNotNull(filterName) && !StringUtil.equals(filterName, "none")) {
			dynamicQueryActivity.add(PropertyFactoryUtil.forName("fileName").eq(filterName));
		}

		if (Validator.isNotNull(filterUploadedBy) && !StringUtil.equals(filterUploadedBy, "none")) {
			dynamicQueryActivity.add(PropertyFactoryUtil.forName("uploadedBy").eq(filterUploadedBy));
		}

		if (Validator.isNotNull(filterUploadedFrom) && !StringUtil.equals(filterUploadedFrom, "none")) {
			dynamicQueryActivity.add(PropertyFactoryUtil.forName("uploadedFrom").eq(filterUploadedFrom));
		}

		if (Validator.isNotNull(keywords) && keywords.length() > 0) {
			dynamicQueryActivity.add(RestrictionsFactoryUtil.like("fileName",
					new StringBuilder("%").append(keywords).append("%").toString()));
		}

		if (StringUtil.equalsIgnoreCase(orderByCol, "uploadedBy")) {
			orderByCol = "uploadedBy";
		}

		if (StringUtil.equalsIgnoreCase(orderByCol, "uploadedFrom")) {
			orderByCol = "uploadedFrom";
		}

		if (StringUtil.equalsIgnoreCase(orderByCol, "none")) {
			orderByCol = "fileName";
		}

		if (StringUtil.equalsIgnoreCase(orderByCol, "fileName")) {
			orderByCol = "fileName";
		}

		if (StringUtil.equalsIgnoreCase(orderByType, "asc")) {
			Order order = OrderFactoryUtil.asc(orderByCol);
			dynamicQueryActivity.addOrder(order);
		} else {
			Order order = OrderFactoryUtil.desc(orderByCol);
			dynamicQueryActivity.addOrder(order);
		}

		validationLogsListTotal = _validationLogLocalService.dynamicQuery(dynamicQueryActivity);

		validationLogList = _validationLogLocalService.dynamicQuery(dynamicQueryActivity, start, end);

		renderRequest.getPortletSession().setAttribute("entries", validationLogList, PortletSession.APPLICATION_SCOPE);
		renderRequest.getPortletSession().setAttribute("validationsList", validationLogsListTotal,
				PortletSession.APPLICATION_SCOPE);

	}

	/**
	 * Adds Clay management toolbar context object to the request.
	 * 
	 * @param renderRequest
	 * @param renderResponse
	 */
	private void addManagementToolbarAttributes(RenderRequest renderRequest, RenderResponse renderResponse) {

		LiferayPortletRequest liferayPortletRequest = _portal.getLiferayPortletRequest(renderRequest);
		LiferayPortletResponse liferayPortletResponse = _portal.getLiferayPortletResponse(renderResponse);
		// SearchContainer searchContainer = null;

		SearchContainer<ValidationLog> searchContainer = new SearchContainer<>(renderRequest,
				_getPortletURL(renderRequest, renderResponse), null, "There aren't validation logs");

		searchContainer.setId("validationLogs");

		MyManagementToolbarDisplayContext assignmentsManagementToolbarDisplayContext = new MyManagementToolbarDisplayContext(
				_portal.getHttpServletRequest(renderRequest), liferayPortletRequest, liferayPortletResponse,
				searchContainer);

		renderRequest.setAttribute("displayContext", assignmentsManagementToolbarDisplayContext);
		renderRequest.setAttribute("searchContainer", searchContainer);
	}

	private PortletURL _getPortletURL(RenderRequest renderRequest, RenderResponse renderResponse) {

		PortletURL portletURL = renderResponse.createRenderURL();
		try {
			portletURL.setPortletMode(PortletMode.VIEW);
		} catch (PortletModeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			portletURL.setWindowState(WindowState.NORMAL);
		} catch (WindowStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// portletURL.setParameter("orderByCol", "urlTitle");
		// portletURL.setParameter("mvcRenderCommandName",
		// ActivitySearchClayManagementPortletKeys.AGGREGATE_VIEW);
		portletURL.setParameter("mvcRenderCommandName", ValidationLogWebPortletKeys.VIEW_LANDING);
		portletURL.setParameter("cur", ParamUtil.getString(renderRequest, "cur"));
		// portletURL.setParameter("orderByCol",
		// RecActivityAggregateWebPortletKeys.VIEW_LANDING);
		portletURL.setParameter("orderByCol", ParamUtil.getString(renderRequest, "orderByCol", "specificParty"));
		portletURL.setParameter("orderByType", ParamUtil.getString(renderRequest, "orderByType", "asc"));
		portletURL.setParameter("filterFileName", ParamUtil.getString(renderRequest, "filterFileName"));
		portletURL.setParameter("filterUploadedBy", ParamUtil.getString(renderRequest, "filterUploadedBy"));
		portletURL.setParameter("filterUploadedFrom", ParamUtil.getString(renderRequest, "filterUploadedFrom"));
		portletURL.setParameter("keywords", ParamUtil.getString(renderRequest, "keywords"));
		return portletURL;
	}

	@Reference
	private Portal _portal;
	/*
	 * @Reference protected JournalArticleService _journalArticleService;
	 */
	@Reference
	protected ValidationLogLocalService _validationLogLocalService;

}
