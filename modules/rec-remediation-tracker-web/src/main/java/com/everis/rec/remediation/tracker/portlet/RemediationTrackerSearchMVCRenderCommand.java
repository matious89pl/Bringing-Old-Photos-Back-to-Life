package com.everis.rec.remediation.tracker.portlet;


import com.everis.rec.remediation.tracker.constants.RecRemediationTrackerWebPortletKeys;
import com.everis.rec.remediation.tracker.model.RemediationTracker;
import com.everis.rec.remediation.tracker.service.RemediationTrackerLocalService;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLAppService;
import com.liferay.document.library.kernel.service.DLFolderLocalService;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Order;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		"javax.portlet.name=" + RecRemediationTrackerWebPortletKeys.RECREMEDIATIONTRACKERWEB, 
		"mvc.command.name=/",
		"mvc.command.name=" + RecRemediationTrackerWebPortletKeys.VIEW_LANDING	
	}, 
	service = MVCRenderCommand.class
)
public class RemediationTrackerSearchMVCRenderCommand implements MVCRenderCommand {

	
	// define log for this class
	private static final Log _log = LogFactoryUtil.getLog(RemediationTrackerSearchMVCRenderCommand.class.getName());

	private static String ROOT_FOLDER_NAME = "File_Upload";
	
	@Override
	public String render(
		RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {		
		// Add assignment list related attributes.
		addRemediationTrackerAssignmentListAttributes(renderRequest);
		// Add Clay management toolbar related attributes.
		addRemediationTrackerManagementToolbarAttributes(renderRequest, renderResponse);
		return "/view.jsp";
	}

	/**
	 * Adds assigment list related attributes to the request.
	 * 
	 * @param renderRequest
	 */
	private void addRemediationTrackerAssignmentListAttributes(RenderRequest renderRequest) {

		//we search all the activities with no populatd date and populate it
		List<RemediationTracker> remediationTrackerList = new ArrayList<RemediationTracker>();
		DynamicQuery dynamicQueryEmptyRemediationTracker =_remediationTrackerLocalService.dynamicQuery();
		dynamicQueryEmptyRemediationTracker.add(RestrictionsFactoryUtil.isNull("dueDateFormated"));
		remediationTrackerList= _remediationTrackerLocalService.dynamicQuery(dynamicQueryEmptyRemediationTracker, -1, -1);
		for(int i=0; i<remediationTrackerList.size();i++) {
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
			RemediationTracker remediationTracker= remediationTrackerList.get(i);
			try {
				remediationTracker.setDueDateFormated(formato.parse(remediationTracker.getDueDate()));
				_remediationTrackerLocalService.updateRemediationTracker(remediationTracker);
			} catch (ParseException e) {
				e.printStackTrace();
			}			
		}
		
		//end of searching 
		
		// Resolve start and end for the search.
		int currentPage = ParamUtil.getInteger(	renderRequest, SearchContainer.DEFAULT_CUR_PARAM,	SearchContainer.DEFAULT_CUR);
		int delta = 50;//ParamUtil.getInteger(renderRequest, SearchContainer.DEFAULT_DELTA_PARAM,	SearchContainer.DEFAULT_DELTA);
		
		String portletConfigAggregateView = renderRequest.getPreferences().getValue("aggregateRemediationTrackerView", "No");
		renderRequest.getPortletSession().setAttribute("aggregateRemediationTrackerView", portletConfigAggregateView, PortletSession.APPLICATION_SCOPE);

		int start = ((currentPage > 0) ? (currentPage - 1) : 0) * delta;
		int end = start + delta;

		String orderByCol =	ParamUtil.getString(renderRequest, "orderByCol", "category");
		String orderByType = ParamUtil.getString(renderRequest, "orderByType", "asc");
		String filterStatus = ParamUtil.getString(renderRequest, "filterStatus", "none");
		String filterParty = ParamUtil.getString(renderRequest, "filterParty", "none");
		String filterCategory = ParamUtil.getString(renderRequest, "filterCategory");
		String keywords = ParamUtil.getString(renderRequest, "keywords");

		List<String> filterStatusList = ManagementToolbarMultifilterUtil.getUpdatedMultipleFilter(renderRequest, "strStatusMultipleFilter", "filterStatus");
		List<String> filterPartyList = ManagementToolbarMultifilterUtil.getUpdatedMultipleFilter(renderRequest, "strPartyMultipleFilter", "filterParty");
		List<String> filterCategoryList = ManagementToolbarMultifilterUtil.getUpdatedMultipleFilter(renderRequest, "strCategoryMultipleFilter", "filterCategory");

		List<RemediationTracker> remediationTrackerListTotal = new ArrayList<RemediationTracker>();
		DynamicQuery dynamicQueryRemediationTracker = _remediationTrackerLocalService.dynamicQuery();
				
		if (!filterStatusList.isEmpty()) {
			dynamicQueryRemediationTracker.add(PropertyFactoryUtil.forName("status").in(filterStatusList));
		} else {
			dynamicQueryRemediationTracker.add(PropertyFactoryUtil.forName("status").ne("Complete"));
		}

		if(!filterPartyList.isEmpty()) {
			dynamicQueryRemediationTracker.add(PropertyFactoryUtil.forName("specificParty").in(filterPartyList));
		}
		
		if(!filterCategoryList.isEmpty()) {
			dynamicQueryRemediationTracker.add(PropertyFactoryUtil.forName("category").in(filterCategoryList));
		}		
		
		if(Validator.isNotNull(keywords)&&keywords.length()>0) {
			dynamicQueryRemediationTracker.add(RestrictionsFactoryUtil.like("description",new StringBuilder("%").append(keywords).append("%").toString()));
		}
		
		//we filter specifiv parties by scopeGroupdId
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		if(StringUtil.equals(portletConfigAggregateView.toLowerCase(), "no")){
			dynamicQueryRemediationTracker.add(RestrictionsFactoryUtil.eq("orgSiteId", themeDisplay.getScopeGroupId()));
		}
		if(StringUtil.equalsIgnoreCase(orderByCol, "Status")) {
			orderByCol="status";
		}
		
		if(StringUtil.equalsIgnoreCase(orderByCol, "Title")) {
			orderByCol="title";
		}
		
		if(StringUtil.equalsIgnoreCase(orderByCol, "none")||StringUtil.equalsIgnoreCase(orderByCol, "Category")) {
			orderByCol="category";
		}
		
		if(StringUtil.equalsIgnoreCase(orderByCol, "Party")) {
			orderByCol="specificParty";
		}
		
		if(StringUtil.equalsIgnoreCase(orderByCol, "Due Date")) {
			orderByCol="dueDateFormated";
		}
		
		if(StringUtil.equalsIgnoreCase(orderByCol, "Description")) {
			orderByCol="description";
		}
				
		if(StringUtil.equalsIgnoreCase(orderByType, "asc")) {
			Order order = OrderFactoryUtil.asc(orderByCol);		
			dynamicQueryRemediationTracker.addOrder(order);
		}
		else {
			Order order = OrderFactoryUtil.desc(orderByCol);		
			dynamicQueryRemediationTracker.addOrder(order);
		}		
	
		remediationTrackerListTotal = _remediationTrackerLocalService.dynamicQuery(dynamicQueryRemediationTracker);
		remediationTrackerList = _remediationTrackerLocalService.dynamicQuery(dynamicQueryRemediationTracker, start, end);
		
		renderRequest.getPortletSession().setAttribute("remediationTrackerList", remediationTrackerList, PortletSession.APPLICATION_SCOPE);
		renderRequest.getPortletSession().setAttribute("remediationTrackerListCount", remediationTrackerListTotal.size(), PortletSession.APPLICATION_SCOPE);

		//we search if the rfi has docs
		boolean hasFiles;
		Map<Long, Boolean> remdiationTrackerDocsMap = new HashMap<Long, Boolean>();
		for (RemediationTracker remediationTracker : remediationTrackerList) {
			hasFiles = false;
			_log.debug("====================Into render: remediationTracker.getOrgSiteId() " + remediationTracker.getOrgSiteId() );
						
			ROOT_FOLDER_NAME = "REMEDIATION_TRACKER_FILES_" + remediationTracker.getRemediationTrackerId();
			DLFolder folder = _dlFolderLocalService.fetchFolder(remediationTracker.getOrgSiteId(), 0, ROOT_FOLDER_NAME);
			
			if(folder != null) {
				int fileEntriesNumber=0;
				try {
					fileEntriesNumber = _dlAppServiceUtil.getFileEntriesCount(remediationTracker.getOrgSiteId(), folder.getFolderId());
					if (fileEntriesNumber > 0) {
						hasFiles = true;
					}
				} catch (PortalException e) {
					_log.error("Error getting file entries count", e);
				}				
			}			
			remdiationTrackerDocsMap.put(remediationTracker.getRemediationTrackerId(), hasFiles);			
		}
		renderRequest.setAttribute("remediationTrackerDocsMap", remdiationTrackerDocsMap);
	}

	/**
	 * Adds Clay management toolbar context object to the request.
	 * 
	 * @param renderRequest
	 * @param renderResponse
	 */
	private void addRemediationTrackerManagementToolbarAttributes( RenderRequest renderRequest, RenderResponse renderResponse) {

		LiferayPortletRequest liferayPortletRequest = _portal.getLiferayPortletRequest(renderRequest);
		LiferayPortletResponse liferayPortletResponse = _portal.getLiferayPortletResponse(renderResponse);
		
		RemediationTrackerManagementToolbarDisplayContext remediationTrackerManagementToolbarDisplayContext = new RemediationTrackerManagementToolbarDisplayContext(_portal.getHttpServletRequest(renderRequest), liferayPortletRequest, liferayPortletResponse);
				
		renderRequest.setAttribute( "remediationTrackerManagementToolbarDisplayContext", remediationTrackerManagementToolbarDisplayContext);
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
		portletURL.setParameter("mvcRenderCommandName", RecRemediationTrackerWebPortletKeys.VIEW_LANDING);
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
	 * This method is used to Add the records for |REC Remediation Tracker Records|
	 * 
	 * @param actionRequest  This is the ActionRequest of the Portlet .
	 * @param actionResponse This is the ActionResponse of the Portlet .
	 * @return void This returns nothing.
	 * 
	 * @author Miquel Bada
	 */
	@ProcessAction(name = "addRemediationTracker")
	public void addActivity(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {

		//_log.debug("==============================Into addActivity");

		actionResponse.getRenderParameters().setValue("mvcPath", "/add_remediationtracker.jsp");

	}

	@Reference
	private Portal _portal;
	
	@Reference
	protected RemediationTrackerLocalService _remediationTrackerLocalService;
	
	@Reference
	protected DLFolderLocalService _dlFolderLocalService;
	
	@Reference
	protected DLAppService _dlAppServiceUtil;
}