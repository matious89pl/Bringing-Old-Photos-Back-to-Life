package com.everis.rec.activity.portlet;


import com.everis.rec.activity.config.ActivityConfiguration;
import com.everis.rec.activity.constants.ActivitySearchClayManagementPortletKeys;
import com.everis.rec.maintenanceactivity.model.MaintenanceActivity;
import com.everis.rec.maintenanceactivity.service.MaintenanceActivityLocalService;
import com.everis.rec.maintenanceactivity.service.MaintenanceActivityLocalServiceUtil;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Order;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchContextFactory;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
//import com.liferay.portal.search.context.SearchContextFactory;
//import com.liferay.portal.vulcan.util.SearchUtil.SearchContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * MVC command for showing the assignments list.
 * 
 * @author liferay
 */
@Component(
	immediate = true, 
	property = {
		"javax.portlet.name=" + ActivitySearchClayManagementPortletKeys.ACTIVITYEARCHCLAYMANAGEMENT, 
		"mvc.command.name=/",
		"mvc.command.name=" + ActivitySearchClayManagementPortletKeys.AGGREGATE_VIEW	
	}, 
	service = MVCRenderCommand.class
)
public class ActivitySearchMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse)throws PortletException {
		// Add assignment list related attributes.
		addAssignmentListAttributes(renderRequest);
		// Add Clay management toolbar related attributes.
		addManagementToolbarAttributes(renderRequest, renderResponse);
		return "/html/searchcontainer/view.jsp";
	}

	/**
	 * Adds assigment list related attributes to the request.
	 * 
	 * @param renderRequest
	 */
	private void addAssignmentListAttributes(RenderRequest renderRequest) {

		// Resolve start and end for the search.
		int currentPage = ParamUtil.getInteger(	renderRequest, SearchContainer.DEFAULT_CUR_PARAM,	SearchContainer.DEFAULT_CUR);
		int delta = ParamUtil.getInteger(renderRequest, SearchContainer.DEFAULT_DELTA_PARAM,	SearchContainer.DEFAULT_DELTA);
		
		String portletConfigAggregateView = renderRequest.getPreferences().getValue("aggregateView", "No");
		renderRequest.getPortletSession().setAttribute("aggregateView", portletConfigAggregateView, PortletSession.APPLICATION_SCOPE);

		int start = ((currentPage > 0) ? (currentPage - 1) : 0) * delta;
		int end = start + delta;

		// Get sorting options.

		String orderByCol =	ParamUtil.getString(renderRequest, "orderByCol", "status").toLowerCase();
		String orderByType = ParamUtil.getString(renderRequest, "orderByType", "asc");
		String filterStatus = ParamUtil.getString(renderRequest, "filterStatus");
		String filterParty = ParamUtil.getString(renderRequest, "filterParty");
		String keywords = ParamUtil.getString(renderRequest, "keywords");
		
		// Call the service to get the list of assignments.
		long maintenanceActivityListCount =0;
		//System.out.println("filterStatus:" + filterStatus);
		 
		List<MaintenanceActivity> maintenanceActivityList = new ArrayList<MaintenanceActivity>();
		DynamicQuery dynamicQueryActivity = MaintenanceActivityLocalServiceUtil.dynamicQuery();
				
		if(Validator.isNotNull(filterStatus)&&!StringUtil.equals(filterStatus, "none")) {
			dynamicQueryActivity.add(PropertyFactoryUtil.forName("status").eq(filterStatus));
		}
		
		if(Validator.isNotNull(filterParty)&&!StringUtil.equals(filterParty, "none")) {
			dynamicQueryActivity.add(PropertyFactoryUtil.forName("specificParty").eq(filterParty));
		}
		
		if(Validator.isNotNull(keywords)&&keywords.length()>0) {
			dynamicQueryActivity.add(RestrictionsFactoryUtil.like("activityTitle",new StringBuilder("%").append(keywords).append("%").toString()));
		}
		
		if(Validator.isNotNull(keywords)&&keywords.length()>0) {
			dynamicQueryActivity.add(RestrictionsFactoryUtil.like("specificParty",new StringBuilder("%").append(keywords).append("%").toString()));
		}
		
		//we filter specifiv partis by scopeGroupdId
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);	
		//tenemos q filtrar si no es aggregateView
		
		if(StringUtil.equals(portletConfigAggregateView.toLowerCase(), "no")){
			dynamicQueryActivity.add(RestrictionsFactoryUtil.eq("orgSiteId", themeDisplay.getScopeGroupId()));
		}		
		
		String field="status";
		if (StringUtil.equalsIgnoreCase(orderByCol, "Party")) {
			field="specificParty";
		}
		
		Sort sort = null;
		if(Validator.isNotNull(ParamUtil.getString(renderRequest, "orderByCol"))&&!StringUtil.equals(orderByCol, "none")) {
			if(StringUtil.equalsIgnoreCase(orderByType, "asc")) {
				Order order = OrderFactoryUtil.asc(field);		
				dynamicQueryActivity.addOrder(order);
				sort = new Sort("orderByCol", Sort.STRING_TYPE, true);
			}
			else {
				Order order = OrderFactoryUtil.desc(field);		
				dynamicQueryActivity.addOrder(order);
				sort = new Sort("orderByCol", Sort.STRING_TYPE, false);
			}
		}
		
		maintenanceActivityList = _maintenanceActivityLocalService.dynamicQuery(dynamicQueryActivity, start, end);
		maintenanceActivityListCount = _maintenanceActivityLocalService.dynamicQueryCount(dynamicQueryActivity);
		
		renderRequest.getPortletSession().setAttribute("maintenanceActivityList", maintenanceActivityList, PortletSession.APPLICATION_SCOPE);
		renderRequest.getPortletSession().setAttribute("maintenanceActivityListCount", maintenanceActivityListCount, PortletSession.APPLICATION_SCOPE);
		
		//configuration section
		// renderRequest.setAttribute(ActivityConfiguration.class.getName(), _configuration);
		
		HttpServletRequest httpServletRequest = PortalUtil.getHttpServletRequest(renderRequest);
		SearchContext searchContext = SearchContextFactory.getInstance(httpServletRequest);
		searchContext.setCompanyId(themeDisplay.getCompanyId());
		searchContext.setStart(start);
		searchContext.setEnd(end);
		searchContext.setKeywords(keywords);
		//searchContext.setAttribute("ddmStructureKey", ddmStructureKey);
		searchContext.setLocale(themeDisplay.getLocale());
		searchContext.setSorts(sort);
		//searchContext.setAssetCategoryIds(assetCategoryIds);
				
		//System.out.println("maintenanceActivityList size:" + maintenanceActivityList.size());
		//System.out.println("maintenanceActivityListCount:" + maintenanceActivityListCount);
		
		/*
		if (Validator.isNotNull(assetCategoryIds) && (assetCategoryIds!=0) ) {
			journalArticles =_journalArticleService.search(themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(), java.util.Collections.EMPTY_LIST,
						JournalArticleConstants.CLASS_NAME_ID_DEFAULT, keywords, null, strJournal, null, null, null, WorkflowConstants.STATUS_APPROVED, null, start, end, comparator);
			count=_journalArticleService.searchCount(themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(), java.util.Collections.EMPTY_LIST, JournalArticleConstants.CLASS_NAME_ID_DEFAULT, keywords,
		null, strJournal, null, null, null, WorkflowConstants.STATUS_APPROVED, null);
			
		}else {
		
			//_journalArticleService.search(companyId, groupId, folderIds, classNameId, keywords, version, ddmStructureKey, ddmTemplateKey, displayDateGT, displayDateLT, status, reviewDate, start, end, orderByComparator)
			journalArticles =_journalArticleService.search(themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(), java.util.Collections.EMPTY_LIST,
				JournalArticleConstants.CLASS_NAME_ID_DEFAULT, keywords, null, "", null,	null, null, WorkflowConstants.STATUS_APPROVED, null, start, end, comparator);
			count=_journalArticleService.searchCount(themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(), java.util.Collections.EMPTY_LIST, JournalArticleConstants.CLASS_NAME_ID_DEFAULT, keywords,
				null, "", null, null, null, WorkflowConstants.STATUS_APPROVED, null);
		}
			*/
			
			/* Sort
			boolean orderByAsc = false;
			Sort sort = null;
			if (Objects.equals(orderByType, "asc")) {
				orderByAsc = true;
			}
			if (Objects.equals(orderByCol, "modifiedDate")) {
				sort = new Sort(Field.MODIFIED_DATE, Sort.LONG_TYPE, !orderByAsc);
			} else if (Objects.equals(orderByCol, "urlTitle")) {
				sort = new Sort("urlTitle", Sort.STRING_TYPE, !orderByAsc);
			} else if (Objects.equals(orderByCol, "userName")) {
				sort = new Sort(Field.USER_NAME, Sort.STRING_TYPE, !orderByAsc);
			}
			
			// Get keywords.
			// Notice that cleaning keywords is not implemented.
			
			HttpServletRequest httpServletRequest = PortalUtil.getHttpServletRequest(renderRequest);

			SearchContext searchContext = SearchContextFactory.getInstance(httpServletRequest);
			searchContext.setCompanyId(themeDisplay.getCompanyId());
			searchContext.setStart(start);
			searchContext.setEnd(end);
			searchContext.setKeywords(keywords);
			//searchContext.setAttribute("ddmStructureKey", ddmStructureKey);
			searchContext.setLocale(themeDisplay.getLocale());
			searchContext.setSorts(sort);
			searchContext.setAssetCategoryIds(assetCategoryIds);
			Indexer<JournalArticle> indexerJournal = IndexerRegistryUtil.getIndexer(JournalArticle.class);
			List<JournalArticle> journalArticles = new ArrayList<JournalArticle>();
			Hits hits;
			try {
				hits = indexerJournal.search(searchContext);
				for (int i = 0; i < hits.getDocs().length; i++) {
					Document doc = hits.doc(i);
					long articleId = GetterUtil.getLong(doc.get(Field.ENTRY_CLASS_PK));
					JournalArticle entry = JournalArticleLocalServiceUtil.fetchLatestArticle(articleId, WorkflowConstants.STATUS_APPROVED);
					if (Validator.isNotNull(entry)) {
						journalArticles.add(entry);
					}
				}
			} catch (SearchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			//List<LandingBean> listLanding = UtilJournal.parseJournalToBean(journalArticles, themeDisplay, _journalConverter, fieldStr, _favouriteLocalService);
			// Set request attributes.
			 * * /			 
		System.out.println(journalArticles.size()); 
		renderRequest.setAttribute("journalArticles", journalArticles);			
		System.out.println(count);
		renderRequest.setAttribute(	"journalArticlesCount", count);
		*/
			
	}

	/**
	 * Adds Clay management toolbar context object to the request.
	 * 
	 * @param renderRequest
	 * @param renderResponse
	 */
	private void addManagementToolbarAttributes(RenderRequest renderRequest, RenderResponse renderResponse) {

		LiferayPortletRequest liferayPortletRequest =_portal.getLiferayPortletRequest(renderRequest);
		LiferayPortletResponse liferayPortletResponse =	_portal.getLiferayPortletResponse(renderResponse);
		ManagementToolbarDisplayContext assignmentsManagementToolbarDisplayContext =new ManagementToolbarDisplayContext(_portal.getHttpServletRequest(renderRequest),liferayPortletRequest, liferayPortletResponse);
		renderRequest.setAttribute(	"assignmentsManagementToolbarDisplayContext",	assignmentsManagementToolbarDisplayContext);

	}
	
	

	@Reference
	private Portal _portal;	
	
	@Reference
	protected MaintenanceActivityLocalService _maintenanceActivityLocalService;
	
	@Activate
    @Modified
    protected void activate(Map<String, Object> properties) {
        _configuration = ConfigurableUtil.createConfigurable(ActivityConfiguration.class, properties);
    }

    private volatile ActivityConfiguration _configuration;

}