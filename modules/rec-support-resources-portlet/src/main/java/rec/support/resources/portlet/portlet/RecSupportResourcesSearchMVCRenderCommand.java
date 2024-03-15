package rec.support.resources.portlet.portlet;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Order;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

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

import rec.filter.api.api.ManagementToolbarMultifilterUtil;
import rec.support.resources.portlet.constants.RecSupportResourcesPortletKeys;
import rec.supporting.resources.model.supportR;
import rec.supporting.resources.service.supportRLocalService;

/**
 * MVC command for showing the assignments list.
 * 
 * @author liferay
 */
@Component(immediate = true, property = { "javax.portlet.name=" + RecSupportResourcesPortletKeys.RECSUPPORTRESOURCES,
		"mvc.command.name=/",
		"mvc.command.name=" + RecSupportResourcesPortletKeys.VIEW_LANDING }, service = MVCRenderCommand.class)
public class RecSupportResourcesSearchMVCRenderCommand implements MVCRenderCommand {

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

		List<supportR> _supportRList = new ArrayList<supportR>();
		DynamicQuery dynamicQueryEmptySupportR = _supportRLocalService.dynamicQuery();
		dynamicQueryEmptySupportR.add(RestrictionsFactoryUtil.isNull("specificParty"));
		_supportRList = _supportRLocalService.dynamicQuery(dynamicQueryEmptySupportR, -1, -1);
		for (int i = 0; i < _supportRList.size(); i++) {
			supportR _supportR = _supportRList.get(i);
			try {
				ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
				Group group = _groupLocalService.getGroup(_supportR.getOrgSiteId());
				_supportR.setSpecificParty(group.getName(themeDisplay.getLocale()));
				_supportRLocalService.updatesupportR(_supportR);
			} catch (PortalException e) {
				e.printStackTrace();
			}
		}

		// Resolve start and end for the search.
		int currentPage = ParamUtil.getInteger(renderRequest, SearchContainer.DEFAULT_CUR_PARAM,
				SearchContainer.DEFAULT_CUR);
		int delta = 50;// ParamUtil.getInteger(renderRequest, SearchContainer.DEFAULT_DELTA_PARAM,
						// SearchContainer.DEFAULT_DELTA);

		String portletConfigAggregateView = renderRequest.getPreferences().getValue("aggregateSupportingResourcesView",
				"No");
		renderRequest.getPortletSession().setAttribute("aggregateSupportingResourcesView", portletConfigAggregateView,
				PortletSession.APPLICATION_SCOPE);

		int start = ((currentPage > 0) ? (currentPage - 1) : 0) * delta;
		int end = start + delta;

		String orderByCol = ParamUtil.getString(renderRequest, "orderByCol", "specificParty");
		String orderByType = ParamUtil.getString(renderRequest, "orderByType", "asc");
		String filterStatus = ParamUtil.getString(renderRequest, "filterStatus", "none");
		String filterParty = ParamUtil.getString(renderRequest, "filterParty");
		String keywords = ParamUtil.getString(renderRequest, "keywords");

		List<String> filterStatusList = ManagementToolbarMultifilterUtil.getUpdatedMultipleFilter(renderRequest, "strStatusMultipleFilter", "filterStatus");
		List<String> filterPartyList = ManagementToolbarMultifilterUtil.getUpdatedMultipleFilter(renderRequest, "strPartyMultipleFilter", "filterParty");

		List<supportR> supportResourceList = new ArrayList<supportR>();
		List<supportR> supportRListTotal = new ArrayList<supportR>();
		DynamicQuery dynamicQuerySupportR = _supportRLocalService.dynamicQuery();

		if (!filterStatusList.isEmpty()) {
			dynamicQuerySupportR.add(PropertyFactoryUtil.forName("status").in(filterStatusList));
		} else {
			dynamicQuerySupportR.add(PropertyFactoryUtil.forName("status").ne("Complete"));
		}

		if(!filterPartyList.isEmpty()) {
			dynamicQuerySupportR.add(PropertyFactoryUtil.forName("specificParty").in(filterPartyList));
		}

		if (Validator.isNotNull(keywords) && keywords.length() > 0) {
			dynamicQuerySupportR.add(RestrictionsFactoryUtil.like("description",
					new StringBuilder("%").append(keywords).append("%").toString()));
		}

		// we filter specifiv partis by scopeGroupdId
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		// tenemos q filtrar si no es aggregateView

		if (StringUtil.equals(portletConfigAggregateView.toLowerCase(), "no")) {
			dynamicQuerySupportR.add(RestrictionsFactoryUtil.eq("orgSiteId", themeDisplay.getScopeGroupId()));
		}
		if (StringUtil.equalsIgnoreCase(orderByCol, "Status")) {
			orderByCol = "status";
		}

		if (StringUtil.equalsIgnoreCase(orderByCol, "none")) {
			orderByCol = "specificParty";
		}

		if (StringUtil.equalsIgnoreCase(orderByCol, "Party")) {
			orderByCol = "specificParty";
		}

		if (StringUtil.equalsIgnoreCase(orderByCol, "Due Date")) {
			orderByCol = "dueDate";
		}

		if (StringUtil.equalsIgnoreCase(orderByType, "asc")) {
			Order order = OrderFactoryUtil.asc(orderByCol);
			dynamicQuerySupportR.addOrder(order);
		} else {
			Order order = OrderFactoryUtil.desc(orderByCol);
			dynamicQuerySupportR.addOrder(order);
		}

		supportRListTotal = _supportRLocalService.dynamicQuery(dynamicQuerySupportR);
		supportResourceList = _supportRLocalService.dynamicQuery(dynamicQuerySupportR, start, end);

		renderRequest.getPortletSession().setAttribute("supportResourceList", supportResourceList,
				PortletSession.APPLICATION_SCOPE);
		renderRequest.getPortletSession().setAttribute("supportResourceListCount", supportRListTotal.size(),
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

		SearchContainer<supportR> searchContainer = new SearchContainer<>(renderRequest,
				_getPortletURL(renderRequest, renderResponse), null, "rec.activity.empty-results");

		searchContainer.setId("activityEntries");

		RecSupportResourcesManagementToolbarDisplayContext supportResourcesManagementToolbarDisplayContext = new RecSupportResourcesManagementToolbarDisplayContext(
				_portal.getHttpServletRequest(renderRequest), liferayPortletRequest, liferayPortletResponse,
				searchContainer);

		renderRequest.setAttribute("supportResourcesManagementToolbarDisplayContext",
				supportResourcesManagementToolbarDisplayContext);
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
		portletURL.setParameter("mvcRenderCommandName", RecSupportResourcesPortletKeys.VIEW_LANDING);
		// portletURL.setParameter("cur",ParamUtil.getString(renderRequest, "cur"));
		// portletURL.setParameter("orderByCol",
		// RecActivityAggregateWebPortletKeys.VIEW_LANDING);
		portletURL.setParameter("orderByCol", ParamUtil.getString(renderRequest, "orderByCol", "specificParty"));
		portletURL.setParameter("orderByType", ParamUtil.getString(renderRequest, "orderByType", "asc"));
		portletURL.setParameter("filterStatus", ParamUtil.getString(renderRequest, "filterStatus"));
		portletURL.setParameter("filterParty", ParamUtil.getString(renderRequest, "filterParty"));
		portletURL.setParameter("filterDueDate", ParamUtil.getString(renderRequest, "filterDueDate"));
		portletURL.setParameter("keywords", ParamUtil.getString(renderRequest, "keywords"));
		return portletURL;
	}

	@Reference
	private Portal _portal;
	@Reference
	protected supportRLocalService _supportRLocalService;
	@Reference
	protected GroupLocalService _groupLocalService;

}