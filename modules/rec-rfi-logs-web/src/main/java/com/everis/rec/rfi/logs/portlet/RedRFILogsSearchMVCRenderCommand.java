package com.everis.rec.rfi.logs.portlet;

import com.everis.rec.rfi.logs.constants.RecRfiLogsWebPortletKeys;
import com.everis.rec.rfilogs.model.RfiLogs;
import com.everis.rec.rfilogs.service.RfiLogsLocalService;
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
		"javax.portlet.name=" + RecRfiLogsWebPortletKeys.RECRFILOGSWEB, 
		"mvc.command.name=/",
		"mvc.command.name=" + RecRfiLogsWebPortletKeys.VIEW_LANDING	
	}, 
	service = MVCRenderCommand.class
)
public class RedRFILogsSearchMVCRenderCommand implements MVCRenderCommand {

	// define log for this class
	private static final Log _log = LogFactoryUtil.getLog(RedRFILogsSearchMVCRenderCommand.class.getName());

	private static String ROOT_FOLDER_NAME = "File_Upload";
	//private static String ROOT_FOLDER_DESCRIPTION = "This is RPA Folder";
	//private static long PARENT_FOLDER_ID = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;
	
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
		List<RfiLogs> rfiLogsEmptyDueDateList = new ArrayList<RfiLogs>();
		DynamicQuery dynamicQueryEmptyRfiLogs =_rfiLogsLocalService.dynamicQuery();
		dynamicQueryEmptyRfiLogs.add(RestrictionsFactoryUtil.isNull("dueDateFormated"));
		rfiLogsEmptyDueDateList= _rfiLogsLocalService.dynamicQuery(dynamicQueryEmptyRfiLogs, -1, -1);
		for(RfiLogs rfiLog:rfiLogsEmptyDueDateList) {
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
			try {
				rfiLog.setDueDateFormated(formato.parse(rfiLog.getDueDate()));
				_rfiLogsLocalService.updateRfiLogs(rfiLog);
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		//we update the status to Complete
		dynamicQueryEmptyRfiLogs =_rfiLogsLocalService.dynamicQuery();
		dynamicQueryEmptyRfiLogs.add(RestrictionsFactoryUtil.eq("status","Completed"));
		rfiLogsEmptyDueDateList= _rfiLogsLocalService.dynamicQuery(dynamicQueryEmptyRfiLogs, -1, -1);
		for (RfiLogs rfiLog : rfiLogsEmptyDueDateList) {
			try {
				rfiLog.setStatus("Complete");				
				_rfiLogsLocalService.updateRfiLogs(rfiLog);
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		//end of searching 
		
		// Resolve start and end for the search.
		int currentPage = ParamUtil.getInteger(	renderRequest, SearchContainer.DEFAULT_CUR_PARAM,	SearchContainer.DEFAULT_CUR);
		int delta = 50;//ParamUtil.getInteger(renderRequest, SearchContainer.DEFAULT_DELTA_PARAM,	SearchContainer.DEFAULT_DELTA);
		
		String portletConfigAggregateView = renderRequest.getPreferences().getValue("aggregateLogView", "No");
		renderRequest.getPortletSession().setAttribute("aggregateLogView", portletConfigAggregateView, PortletSession.APPLICATION_SCOPE);

		int start = ((currentPage > 0) ? (currentPage - 1) : 0) * delta;
		int end = start + delta;

		String orderByCol =	ParamUtil.getString(renderRequest, "orderByCol", "specificParty");
		String orderByType = ParamUtil.getString(renderRequest, "orderByType", "asc");
		String filterStatus = ParamUtil.getString(renderRequest, "filterStatus", "none");
		String filterParty = ParamUtil.getString(renderRequest, "filterParty");
		String filterDueDate = ParamUtil.getString(renderRequest, "filterDueDate");
		String keywords = ParamUtil.getString(renderRequest, "keywords");
		
		//Get Multiple Filter Lists adding or removing clicked filter
		List<String> filterStatusList = ManagementToolbarMultifilterUtil.getUpdatedMultipleFilter(renderRequest, "strStatusMultipleFilter", "filterStatus");
		List<String> filterPartyList = ManagementToolbarMultifilterUtil.getUpdatedMultipleFilter(renderRequest, "strPartyMultipleFilter", "filterParty");

		// Call the service to get the list of assignments.
		long rfiLogsListCount =0;
		List<RfiLogs> rfiLogsList = new ArrayList<RfiLogs>();
		List<RfiLogs> rfiLogsListTotal = new ArrayList<RfiLogs>();
		DynamicQuery dynamicQueryActivity = _rfiLogsLocalService.dynamicQuery();

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
			dynamicQueryActivity.add(RestrictionsFactoryUtil.like("title",new StringBuilder("%").append(keywords).append("%").toString()));
		}
		
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);	
		//we filter by aggregateView		
		if(StringUtil.equals(portletConfigAggregateView.toLowerCase(), "no")){
			dynamicQueryActivity.add(RestrictionsFactoryUtil.eq("orgSiteId", themeDisplay.getScopeGroupId()));
		}
		
		//order by column
		if(StringUtil.equalsIgnoreCase(orderByCol, "Due Date")) {
			orderByCol="dueDateFormated";
		}

		if(StringUtil.equalsIgnoreCase(orderByCol, "Status")) {
			orderByCol="status";
		}
		
		if(StringUtil.equalsIgnoreCase(orderByCol, "Title")) {
			orderByCol="title";
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
	
		rfiLogsListTotal = _rfiLogsLocalService.dynamicQuery(dynamicQueryActivity);
		rfiLogsListCount = rfiLogsListTotal.size();
		
		rfiLogsList = _rfiLogsLocalService.dynamicQuery(dynamicQueryActivity, start, end);
		renderRequest.getPortletSession().setAttribute("rfiLogsList", rfiLogsList, PortletSession.APPLICATION_SCOPE);
		renderRequest.getPortletSession().setAttribute("rfiLogsListCount", rfiLogsListCount, PortletSession.APPLICATION_SCOPE);
		
		//we search if the rfi has docs on its own site folder
		boolean hasFiles;
		Map<Long, Boolean> rfiLogsDocsMap = new HashMap<Long, Boolean>();		
		for (RfiLogs rfiLogs : rfiLogsList) {
			hasFiles = false;
			_log.debug("====================Into render: rfiLogs.getOrgSiteId() , themeDisplay.getScopeGroupId()  "	+ rfiLogs.getOrgSiteId() + " , " + themeDisplay.getScopeGroupId());
						
			ROOT_FOLDER_NAME = "RFI_FILES_" + rfiLogs.getRfilogId();
			DLFolder folder = _dlFolderLocalService.fetchFolder(rfiLogs.getOrgSiteId(), 0, ROOT_FOLDER_NAME);
			
			if(folder != null) {
				int fileEntriesNumber=0;
				try {
					fileEntriesNumber = _dlAppServiceUtil.getFileEntriesCount(rfiLogs.getOrgSiteId(), folder.getFolderId());
					if (fileEntriesNumber > 0) {
						hasFiles = true;
					}
				} catch (PortalException e) {
					_log.error("Error getting file entries count", e);
				}				
			}			
			rfiLogsDocsMap.put(rfiLogs.getRfilogId(), hasFiles);			
		}
		renderRequest.setAttribute("rfiLogsDocsMap", rfiLogsDocsMap);
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
		
		SearchContainer<RfiLogs>searchContainer = new SearchContainer<>(renderRequest, _getPortletURL(renderRequest, renderResponse), null,	"rec.rfiLogs.empty-results");
		
		searchContainer.setId("rfiLogsEntries");
		
		ManagementToolbarDisplayContext rfiLogsAssignmentsManagementToolbarDisplayContext = new ManagementToolbarDisplayContext(_portal.getHttpServletRequest(renderRequest), liferayPortletRequest, liferayPortletResponse, searchContainer);
				
		renderRequest.setAttribute( "rfiLogsAssignmentsManagementToolbarDisplayContext", rfiLogsAssignmentsManagementToolbarDisplayContext);
		renderRequest.setAttribute( "searchContainer",	searchContainer);
	}
	
	private PortletURL _getPortletURL(RenderRequest renderRequest, RenderResponse renderResponse)  {
		
		PortletURL portletURL = renderResponse.createRenderURL();
		try {
			portletURL.setPortletMode(PortletMode.VIEW);
		} catch (PortletModeException e) {
			_log.error("Error setting view portlet mode", e);
		}
		try {
			portletURL.setWindowState(WindowState.NORMAL);
		} catch (WindowStateException e) {
			_log.error("Error setting normal portlet mode", e);
		}
		//portletURL.setParameter("orderByCol", "urlTitle");
		//portletURL.setParameter("mvcRenderCommandName", ActivitySearchClayManagementPortletKeys.AGGREGATE_VIEW);
		portletURL.setParameter("mvcRenderCommandName", RecRfiLogsWebPortletKeys.VIEW_LANDING);
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
	@ProcessAction(name = "addRFI")
	public void addActivity(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
		//_log.debug("==============================Into addActivity");
		actionResponse.getRenderParameters().setValue("mvcPath", "/add_rfi.jsp");
	}

	@Reference
	private Portal _portal;
 
	@Reference
	protected RfiLogsLocalService _rfiLogsLocalService;
	
	@Reference
	protected DLFolderLocalService _dlFolderLocalService;
	
	@Reference
	protected DLAppService _dlAppServiceUtil;
	
	
}