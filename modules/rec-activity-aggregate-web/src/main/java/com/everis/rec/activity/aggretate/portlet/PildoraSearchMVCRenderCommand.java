package com.everis.rec.activity.aggretate.portlet;

import com.everis.rec.activity.aggretate.constants.RecActivityAggregateWebPortletKeys;
import com.everis.rec.maintenanceactivity.model.MaintenanceActivity;
import com.everis.rec.maintenanceactivity.service.MaintenanceActivityLocalService;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Order;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletModeException;
import javax.portlet.PortletSession;
import javax.portlet.PortletURL;
import javax.portlet.ProcessAction;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;
import javax.portlet.WindowStateException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import rec.filter.api.api.ManagementToolbarMultifilterUtil;

/**
 * MVC command for showing the assignments list.
 * 
 * @author liferay
 */
@Component(
	immediate = true, 
	property = {
		"javax.portlet.name=" + RecActivityAggregateWebPortletKeys.RECACTIVITYAGGREGATEWEB, 
		"mvc.command.name=/",
		"mvc.command.name=" + RecActivityAggregateWebPortletKeys.VIEW_LANDING	
	}, 
	service = MVCRenderCommand.class
)
public class PildoraSearchMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
		RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {		
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

		//we search all the activities with no populatd date and populate it
		List<MaintenanceActivity> maintenanceEmptyActivityList = new ArrayList<MaintenanceActivity>();
		DynamicQuery dynamicQueryEmptyActivity =_maintenanceActivityLocalService.dynamicQuery();
		//DynamicQuery dynamicQueryEmptyActivity = MaintenanceActivityLocalServiceUtil.dynamicQuery();	
		dynamicQueryEmptyActivity.add(RestrictionsFactoryUtil.isNull("dueDateFormated"));
		maintenanceEmptyActivityList= _maintenanceActivityLocalService.dynamicQuery(dynamicQueryEmptyActivity, -1, -1);
		for(int i=0; i<maintenanceEmptyActivityList.size();i++) {
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
			MaintenanceActivity maintenanceActivity= maintenanceEmptyActivityList.get(i);
			try {
				maintenanceActivity.setDueDateFormated(formato.parse(maintenanceActivity.getDueDate()));
				_maintenanceActivityLocalService.updateMaintenanceActivity(maintenanceActivity);
			} catch (ParseException e) {
				e.printStackTrace();
			}			
		}
		
		
		//we update the status to Complete
		dynamicQueryEmptyActivity =_maintenanceActivityLocalService.dynamicQuery();
		dynamicQueryEmptyActivity.add(RestrictionsFactoryUtil.eq("status","Completed"));
		maintenanceEmptyActivityList= _maintenanceActivityLocalService.dynamicQuery(dynamicQueryEmptyActivity, -1, -1);
		for (MaintenanceActivity maintenanceActivity : maintenanceEmptyActivityList) {
			try {
				maintenanceActivity.setStatus("Complete");				
				_maintenanceActivityLocalService.updateMaintenanceActivity(maintenanceActivity);
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		
		
		//end of searching 
		
		// Resolve start and end for the search.
		int currentPage = ParamUtil.getInteger(	renderRequest, SearchContainer.DEFAULT_CUR_PARAM,	SearchContainer.DEFAULT_CUR);
		int delta = 50;//ParamUtil.getInteger(renderRequest, SearchContainer.DEFAULT_DELTA_PARAM,	SearchContainer.DEFAULT_DELTA);
		
		String portletConfigAggregateView = renderRequest.getPreferences().getValue("aggregateView", "No");
		renderRequest.getPortletSession().setAttribute("aggregateView", portletConfigAggregateView, PortletSession.APPLICATION_SCOPE);

		int start = ((currentPage > 0) ? (currentPage - 1) : 0) * delta;
		int end = start + delta;

		String orderByCol =	ParamUtil.getString(renderRequest, "orderByCol", "specificParty");
		String orderByType = ParamUtil.getString(renderRequest, "orderByType", "asc");
		String filterStatus = ParamUtil.getString(renderRequest, "filterStatus", "none");
		String filterParty = ParamUtil.getString(renderRequest, "filterParty");
		String filterDueDate = ParamUtil.getString(renderRequest, "filterDueDate");
		String keywords = ParamUtil.getString(renderRequest, "keywords");
		
		//Get Multiple Filter Lists adding or removing clicked filter
		List<String> filterStatusList = ManagementToolbarMultifilterUtil.getUpdatedMultipleFilter(renderRequest, "strStatusMultipleFilter",
				"filterStatus");
		List<String> filterPartyList = ManagementToolbarMultifilterUtil.getUpdatedMultipleFilter(renderRequest, "strPartyMultipleFilter", "filterParty");


		// Call the service to get the list of assignments.
		long maintenanceActivityListCount =0;
		 
		List<MaintenanceActivity> maintenanceActivityList = new ArrayList<MaintenanceActivity>();
		List<MaintenanceActivity> maintenanceActivityListTotal = new ArrayList<MaintenanceActivity>();
		DynamicQuery dynamicQueryActivity = _maintenanceActivityLocalService.dynamicQuery();
				
		if (!filterStatusList.isEmpty()) {
			dynamicQueryActivity.add(PropertyFactoryUtil.forName("status").in(filterStatusList));
		} else {
			dynamicQueryActivity.add(PropertyFactoryUtil.forName("status").ne("Complete"));
		}
		
		if(!filterPartyList.isEmpty()) {
			dynamicQueryActivity.add(PropertyFactoryUtil.forName("specificParty").in(filterPartyList));
		}
		
		if(Validator.isNotNull(filterDueDate)&&!StringUtil.equals(filterDueDate, "none")) {
			dynamicQueryActivity.add(PropertyFactoryUtil.forName("dueDate").eq(filterDueDate));
		}		
		
		if(Validator.isNotNull(keywords)&&keywords.length()>0) {
			dynamicQueryActivity.add(RestrictionsFactoryUtil.like("activityTitle",new StringBuilder("%").append(keywords).append("%").toString()));
		}
		
		//we filter specifiv partis by scopeGroupdId
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);	
		//tenemos q filtrar si no es aggregateView
		
		if(StringUtil.equals(portletConfigAggregateView.toLowerCase(), "no")){
			dynamicQueryActivity.add(RestrictionsFactoryUtil.eq("orgSiteId", themeDisplay.getScopeGroupId()));
		}

		if(StringUtil.equalsIgnoreCase(orderByCol, "Due Date")) {
			orderByCol="dueDateFormated";
		}

		if(StringUtil.equalsIgnoreCase(orderByCol, "Status")) {
			orderByCol="status";
		}
		
		if(StringUtil.equalsIgnoreCase(orderByCol, "Activity")) {
			orderByCol="activityTitle";
		}
		
		if(StringUtil.equalsIgnoreCase(orderByCol, "none")) {
			orderByCol="specificParty";
		}
		
		if(StringUtil.equalsIgnoreCase(orderByCol, "Party")) {
			orderByCol="specificParty";
		}
		
		if(StringUtil.equalsIgnoreCase(orderByType, "asc")) {
			Order order = OrderFactoryUtil.asc(orderByCol);		
			dynamicQueryActivity.addOrder(order);
		}
		else {
			Order order = OrderFactoryUtil.desc(orderByCol);		
			dynamicQueryActivity.addOrder(order);
		}		
	
		maintenanceActivityListTotal = _maintenanceActivityLocalService.dynamicQuery(dynamicQueryActivity);
		maintenanceActivityListCount = maintenanceActivityListTotal.size();
		
		maintenanceActivityList = _maintenanceActivityLocalService.dynamicQuery(dynamicQueryActivity, start, end);
		
		renderRequest.getPortletSession().setAttribute("maintenanceActivityList", maintenanceActivityList, PortletSession.APPLICATION_SCOPE);
		renderRequest.getPortletSession().setAttribute("maintenanceActivityListCount", maintenanceActivityListCount, PortletSession.APPLICATION_SCOPE);

	}

	/**
	 * Adds Clay management toolbar context object to the request.
	 * 
	 * @param renderRequest
	 * @param renderResponse
	 */
	private void addManagementToolbarAttributes( RenderRequest renderRequest, RenderResponse renderResponse) {

		LiferayPortletRequest liferayPortletRequest = _portal.getLiferayPortletRequest(renderRequest);
		LiferayPortletResponse liferayPortletResponse = _portal.getLiferayPortletResponse(renderResponse);
		//SearchContainer searchContainer = null;
		
		SearchContainer<MaintenanceActivity>searchContainer = new SearchContainer<>(renderRequest, _getPortletURL(renderRequest, renderResponse), null,	"rec.activity.empty-results");
		
		searchContainer.setId("activityEntries");
		
		ManagementToolbarDisplayContext assignmentsManagementToolbarDisplayContext = new ManagementToolbarDisplayContext(_portal.getHttpServletRequest(renderRequest), liferayPortletRequest, liferayPortletResponse, searchContainer);
				
		renderRequest.setAttribute( "assignmentsManagementToolbarDisplayContext",	assignmentsManagementToolbarDisplayContext);
		renderRequest.setAttribute( "searchContainer",	searchContainer);
	}
	
	private PortletURL _getPortletURL(RenderRequest renderRequest, RenderResponse renderResponse)  {
		
		
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
		//portletURL.setParameter("orderByCol", "urlTitle");
		//portletURL.setParameter("mvcRenderCommandName", ActivitySearchClayManagementPortletKeys.AGGREGATE_VIEW);
		portletURL.setParameter("mvcRenderCommandName", RecActivityAggregateWebPortletKeys.VIEW_LANDING);
		portletURL.setParameter("cur",ParamUtil.getString(renderRequest, "cur"));
		//portletURL.setParameter("orderByCol", RecActivityAggregateWebPortletKeys.VIEW_LANDING);
		portletURL.setParameter("orderByCol",ParamUtil.getString(renderRequest, "orderByCol", "specificParty"));
		portletURL.setParameter("orderByType",ParamUtil.getString(renderRequest, "orderByType", "asc"));
		portletURL.setParameter("filterStatus",ParamUtil.getString(renderRequest, "filterStatus"));
		portletURL.setParameter("filterParty", ParamUtil.getString(renderRequest, "filterParty"));
		portletURL.setParameter("filterDueDate", ParamUtil.getString(renderRequest, "filterDueDate"));
		portletURL.setParameter("keywords", ParamUtil.getString(renderRequest, "keywords"));
		return portletURL;
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

		//_log.debug("==============================Into addActivity");

		actionResponse.getRenderParameters().setValue("mvcPath", "/add_activity.jsp");

	}

	@Reference
	private Portal _portal;
	/*
	@Reference
	protected JournalArticleService _journalArticleService;
	*/
	@Reference
	protected MaintenanceActivityLocalService _maintenanceActivityLocalService;
	

}