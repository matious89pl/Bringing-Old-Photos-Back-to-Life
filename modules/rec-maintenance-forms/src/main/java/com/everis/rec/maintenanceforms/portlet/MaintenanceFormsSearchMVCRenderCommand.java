package com.everis.rec.maintenanceforms.portlet;


import com.everis.rec.maintenanceforms.constants.MaintenanceFormsActionPortletKeys;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import rec.everis.forms.service.model.MaintenanceActivityForms;
import rec.everis.forms.service.service.MaintenanceActivityFormsLocalService;

/**
 * MVC command for showing the assignments list.
 * 
 * @author liferay
 */
@Component(
	immediate = true, 
	property = {
		"javax.portlet.name=" + MaintenanceFormsActionPortletKeys.MAINTENANCEFORMSACTION, 
		"mvc.command.name=/",
		"mvc.command.name=" + MaintenanceFormsActionPortletKeys.VIEW_LANDING	
	}, 
	service = MVCRenderCommand.class
)
public class MaintenanceFormsSearchMVCRenderCommand implements MVCRenderCommand {

	
	// define log for this class
	private static final Log _log = LogFactoryUtil.getLog(MaintenanceFormsSearchMVCRenderCommand.class.getName());

	private static String ROOT_FOLDER_NAME = "File_Upload";
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {		
		// Add assignment list related attributes.
		addMaintenanceFormsAssignmentListAttributes(renderRequest);
		// Add Clay management toolbar related attributes.
		addMaintenanceFormsManagementToolbarAttributes(renderRequest, renderResponse);
		return "/view.jsp";
	}

	/**
	 * Adds assigment list related attributes to the request.
	 * 
	 * @param renderRequest
	 */
	private void addMaintenanceFormsAssignmentListAttributes(RenderRequest renderRequest) {

		// Resolve start and end for the search.
		int currentPage = ParamUtil.getInteger(	renderRequest, SearchContainer.DEFAULT_CUR_PARAM,	SearchContainer.DEFAULT_CUR);
		int delta = 50;//ParamUtil.getInteger(renderRequest, SearchContainer.DEFAULT_DELTA_PARAM,	SearchContainer.DEFAULT_DELTA);
		
		String portletConfigAggregateView = renderRequest.getPreferences().getValue("aggregateFormView", "No");
		renderRequest.getPortletSession().setAttribute("aggregateFormView", portletConfigAggregateView, PortletSession.APPLICATION_SCOPE);

		int start = ((currentPage > 0) ? (currentPage - 1) : 0) * delta;
		int end = start + delta;

		String orderByCol =	ParamUtil.getString(renderRequest, "orderByCol", "year");
		String orderByType = ParamUtil.getString(renderRequest, "orderByType", "desc");
		String filterStatus = ParamUtil.getString(renderRequest, "filterStatus", "none");
		String filterParty = ParamUtil.getString(renderRequest, "filterParty", "none");	
		String filterYear = ParamUtil.getString(renderRequest, "filterYear", "none");		
		String keywords = ParamUtil.getString(renderRequest, "keywords");
				 
		List<MaintenanceActivityForms> maintenanceActivityFormsListTotal = new ArrayList<MaintenanceActivityForms>();
		List<MaintenanceActivityForms> maintenanceActivityFormsList = new ArrayList<MaintenanceActivityForms>();
		
		DynamicQuery dynamicQuerymaintenanceActivityForms = _maintenanceActivityFormsLocalService.dynamicQuery();
				
		if(Validator.isNotNull(filterStatus)&&!StringUtil.equals(filterStatus, "none")) {
			dynamicQuerymaintenanceActivityForms.add(PropertyFactoryUtil.forName("status").eq(filterStatus));
		}
		
		if(Validator.isNotNull(filterParty)&&!StringUtil.equals(filterParty, "none")) {
			long siteId = ParamUtil.getLong(renderRequest, "filterParty");			
			dynamicQuerymaintenanceActivityForms.add(PropertyFactoryUtil.forName("orgSiteId").eq(siteId));
		}
		
		if(Validator.isNotNull(filterYear)&&!StringUtil.equals(filterYear, "none")) {
			Integer year = ParamUtil.getInteger(renderRequest, "filterYear");
			dynamicQuerymaintenanceActivityForms.add(PropertyFactoryUtil.forName("year").eq(year));
		}
				
		
		if(Validator.isNotNull(keywords)&&keywords.length()>0) {
			dynamicQuerymaintenanceActivityForms.add(RestrictionsFactoryUtil.like("formType",new StringBuilder("%").append(keywords).append("%").toString()));
		}
		
		//we filter specifiv partis by scopeGroupdId
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);	
		//tenemos q filtrar si no es aggregateView
		
		if(StringUtil.equals(portletConfigAggregateView.toLowerCase(), "no")){
			dynamicQuerymaintenanceActivityForms.add(RestrictionsFactoryUtil.eq("orgSiteId", themeDisplay.getScopeGroupId()));
		}
		
		if(StringUtil.equalsIgnoreCase(orderByCol, "Status")) {
			orderByCol="status";
		}
		
		if(StringUtil.equalsIgnoreCase(orderByCol, "Party")) {
			orderByCol="orgSiteId";
		}
		
		if(StringUtil.equalsIgnoreCase(orderByCol, "Submitted Date")) {
			orderByCol="submitDate";
		}
		
		if(StringUtil.equalsIgnoreCase(orderByCol, "none")) {
			orderByCol="year";
		}
				
		if(StringUtil.equalsIgnoreCase(orderByType, "asc")) {
			Order order = OrderFactoryUtil.asc(orderByCol);		
			dynamicQuerymaintenanceActivityForms.addOrder(order);
		}
		else {
			Order order = OrderFactoryUtil.desc(orderByCol);		
			dynamicQuerymaintenanceActivityForms.addOrder(order);
		}
		
		maintenanceActivityFormsListTotal = _maintenanceActivityFormsLocalService.dynamicQuery(dynamicQuerymaintenanceActivityForms);
		maintenanceActivityFormsList = _maintenanceActivityFormsLocalService.dynamicQuery(dynamicQuerymaintenanceActivityForms, start, end);
		
		renderRequest.getPortletSession().setAttribute("maintenanceActivityFormsList", maintenanceActivityFormsList, PortletSession.APPLICATION_SCOPE);
		renderRequest.getPortletSession().setAttribute("maintenanceActivityFormsListCount", maintenanceActivityFormsListTotal.size(), PortletSession.APPLICATION_SCOPE);

		//we search if the rfi has docs
		boolean hasFiles;
		Map<Long, Boolean> maintenanceActivityFormsDocsMap = new HashMap<Long, Boolean>();
		for (MaintenanceActivityForms maintenanceActivityForms : maintenanceActivityFormsList) {
			hasFiles = false;
			_log.debug("====================Into render: maintenanceActivityForms.getOrgSiteId() " + maintenanceActivityForms.getOrgSiteId() );
						
			ROOT_FOLDER_NAME = "FORM_FILES_" + maintenanceActivityForms.getMaintenanceactivityformId();
			DLFolder folder = _dlFolderLocalService.fetchFolder(maintenanceActivityForms.getOrgSiteId(), 0, ROOT_FOLDER_NAME);
			
			if(folder != null) {
				int fileEntriesNumber=0;
				try {
					fileEntriesNumber = _dlAppServiceUtil.getFileEntriesCount(maintenanceActivityForms.getOrgSiteId(), folder.getFolderId());
					if (fileEntriesNumber > 0) {
						hasFiles = true;
					}
				} catch (PortalException e) {
					_log.error("Error getting file entries count", e);
				}				
			}			
			maintenanceActivityFormsDocsMap.put(maintenanceActivityForms.getMaintenanceactivityformId(), hasFiles);			
		}
		renderRequest.setAttribute("maintenanceActivityFormsDocsMap", maintenanceActivityFormsDocsMap);
	}

	/**
	 * Adds Clay management toolbar context object to the request.
	 * 
	 * @param renderRequest
	 * @param renderResponse
	 */
	private void addMaintenanceFormsManagementToolbarAttributes( RenderRequest renderRequest, RenderResponse renderResponse) {

		LiferayPortletRequest liferayPortletRequest = _portal.getLiferayPortletRequest(renderRequest);
		LiferayPortletResponse liferayPortletResponse = _portal.getLiferayPortletResponse(renderResponse);
		
		MaintenanceFormsManagementToolbarDisplayContext maintenanceActivityFormsManagementToolbarDisplayContext = new MaintenanceFormsManagementToolbarDisplayContext(_portal.getHttpServletRequest(renderRequest), liferayPortletRequest, liferayPortletResponse);
				
		renderRequest.setAttribute( "maintenanceActivityFormsManagementToolbarDisplayContext", maintenanceActivityFormsManagementToolbarDisplayContext);
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
		portletURL.setParameter("mvcRenderCommandName", MaintenanceFormsActionPortletKeys.VIEW_LANDING);
		portletURL.setParameter("cur",ParamUtil.getString(renderRequest, "cur"));
		//portletURL.setParameter("orderByCol", RecActivityAggregateWebPortletKeys.VIEW_LANDING);
		portletURL.setParameter("orderByCol",ParamUtil.getString(renderRequest, "orderByCol", "year"));
		portletURL.setParameter("orderByType",ParamUtil.getString(renderRequest, "orderByType", "desc"));
		portletURL.setParameter("filterStatus",ParamUtil.getString(renderRequest, "filterStatus"));
		portletURL.setParameter("filterParty", ParamUtil.getString(renderRequest, "filterParty"));
		portletURL.setParameter("filterDueDate", ParamUtil.getString(renderRequest, "filterDueDate"));
		portletURL.setParameter("keywords", ParamUtil.getString(renderRequest, "keywords"));
		return portletURL;
	}
	

	@Reference
	private Portal _portal;
	
	@Reference
	protected MaintenanceActivityFormsLocalService _maintenanceActivityFormsLocalService;
	
	@Reference
	protected DLFolderLocalService _dlFolderLocalService;
	
	@Reference
	protected DLAppService _dlAppServiceUtil;
}