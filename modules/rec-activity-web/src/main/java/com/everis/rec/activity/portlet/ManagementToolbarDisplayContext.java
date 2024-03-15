package com.everis.rec.activity.portlet;

import com.everis.rec.activity.constants.ActivitySearchClayManagementPortletKeys;
import com.everis.rec.maintenanceactivity.service.MaintenanceActivityLocalService;
import com.everis.rec.maintenanceactivity.service.MaintenanceActivityLocalServiceUtil;
import com.liferay.frontend.taglib.clay.servlet.taglib.display.context.BaseManagementToolbarDisplayContext;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.LabelItemList;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.ViewTypeItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.ViewTypeItemList;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Reference;


/**
 * Assigments management toolbar display context.
 *
 * This class passes contextual information to the user interface
 * for the Clay management toolbar.
 *
 * @author liferay
 */
public class ManagementToolbarDisplayContext extends BaseManagementToolbarDisplayContext {
	
	public ManagementToolbarDisplayContext(HttpServletRequest httpServletRequest,LiferayPortletRequest liferayPortletRequest, LiferayPortletResponse liferayPortletResponse) {
		
		super(httpServletRequest, liferayPortletRequest, liferayPortletResponse);
		
		_portalPreferences = PortletPreferencesFactoryUtil.getPortalPreferences(liferayPortletRequest);

		_themeDisplay = (ThemeDisplay)httpServletRequest.getAttribute(WebKeys.THEME_DISPLAY);	
	}

	
	
	
	@Override
	public String getClearResultsURL() {
		return getSearchActionURL();
	}
	
	/**
	 * Returns the action URL for the search.
	 *
	 * @return search action URL
	 */
	@Override
	public String getSearchActionURL() {
		
		PortletURL searchURL = liferayPortletResponse.createRenderURL();
		searchURL.setProperty("mvcRenderCommandName", ActivitySearchClayManagementPortletKeys.AGGREGATE_VIEW);		
		String navigation = ParamUtil.getString(httpServletRequest, "navigation", "entries");
		searchURL.setParameter("navigation", navigation);

		searchURL.setParameter("orderByCol", getOrderByCol());
		searchURL.setParameter("orderByType", getOrderByType());

		return searchURL.toString();
	}



	/**
	 * Returns the assignment list display style. 
	 * 
	 * Current selection is stored in portal preferences.
	 * 
	 * @return current display style
	 */
	public String getDisplayStyle() {

		String displayStyle = ParamUtil.getString(httpServletRequest, "displayStyle");

		if (Validator.isNull(displayStyle)) {
			displayStyle = _portalPreferences.getValue(	ActivitySearchClayManagementPortletKeys.ACTIVITYEARCHCLAYMANAGEMENT, "assignments-display-style","table");
		}
		else {
			_portalPreferences.setValue(ActivitySearchClayManagementPortletKeys.ACTIVITYEARCHCLAYMANAGEMENT, "assignments-display-style",displayStyle);
			httpServletRequest.setAttribute(WebKeys.SINGLE_PAGE_APPLICATION_CLEAR_CACHE, Boolean.TRUE);
		}

		return displayStyle;
	}

	/**
	 * Returns the sort order column.
	 * 
	 * @return sort column
	 */
	public String getOrderByCol() {
		return ParamUtil.getString(httpServletRequest, "orderByCol", "activity");
	}

	/**
	 * Returns the sort type (ascending / descending).
	 * 
	 * @return sort type
	 */
	public String getOrderByType() {
		return ParamUtil.getString(httpServletRequest, "orderByType", "asc");
	}
	
	
	
	/**
	 * Returns the current sorting URL.
	 *
	 * @return current sorting portlet URL
	 *
	 * @throws PortletException
	 */
	public PortletURL _getCurrentSortingURL() throws PortletException {
		PortletURL sortingURL = PortletURLUtil.clone(currentURLObj, liferayPortletResponse);

		sortingURL.setParameter("mvcRenderCommandName", ActivitySearchClayManagementPortletKeys.AGGREGATE_VIEW);

		// Reset current page.
		sortingURL.setParameter(SearchContainer.DEFAULT_CUR_PARAM, "0");
		String keywords = ParamUtil.getString(httpServletRequest, "keywords");
		if (Validator.isNotNull(keywords)) {
			sortingURL.setParameter("keywords", keywords);
		}
		return sortingURL;
	}
	
	@Override
	public String getSearchContainerId() {
		return "activityEntries";
	}
	
	/**
	 * Return the option items for the sort column menu.
	 *
	 * @return options list for the sort column menu
	 */
	
	@Override
	protected List<DropdownItem> getOrderByDropdownItems() {
		return new DropdownItemList() {
			{
				add(
						dropdownItem -> {
							dropdownItem.setActive("activity".equals(getOrderByCol()));
							dropdownItem.setHref(_getCurrentSortingURL(), "orderByCol",	"activity");
							dropdownItem.setLabel("Activity");
						});	
				add(
					dropdownItem -> {
						dropdownItem.setActive("urlTitle".equals(getOrderByCol()));
						dropdownItem.setHref(_getCurrentSortingURL(), "orderByCol", "status");
						dropdownItem.setLabel("Status");
					});
				add(
					dropdownItem -> {
						dropdownItem.setActive("modifiedDate".equals(getOrderByCol()));
						dropdownItem.setHref(_getCurrentSortingURL(), "orderByCol",	"organization");
						dropdownItem.setLabel("Organization");
					});				
			}
		};
	}
	
		/*
	@Override
	public CreationMenu getCreationMenu() {
		return new CreationMenu() {
			{
				addDropdownItem(
					dropdownItem -> {
						dropdownItem.setHref(liferayPortletResponse.createRenderURL(),
						"mvcRenderCommandName", "edicion_add",
						"redirect", currentURLObj.toString());
  						dropdownItem.setLabel("Sample 1");
					});
				addDropdownItem(
						dropdownItem -> {
							dropdownItem.setHref("#");
	  						dropdownItem.setLabel("Sample 2");
						});

				
			}
		};
	}		
			*/
			
	@Override
	public Boolean isShowCreationMenu() {
		return false;
	}
	
	
	
	
	/**	
	 * Returns the view type options (card, list, table).
	 *
	 * @return list of view types
	 */
	@Override
	public List<ViewTypeItem> getViewTypeItems() {
		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		portletURL.setParameter("mvcRenderCommandName", ActivitySearchClayManagementPortletKeys.AGGREGATE_VIEW);

		int delta =	ParamUtil.getInteger(httpServletRequest, SearchContainer.DEFAULT_DELTA_PARAM);

		if (delta > 0) {
			portletURL.setParameter("delta", String.valueOf(delta));
		}

		String orderByCol =ParamUtil.getString(httpServletRequest, "orderByCol", "activity");
		String orderByType =ParamUtil.getString(httpServletRequest, "orderByType", "asc");

		portletURL.setParameter("orderByCol", orderByCol);
		portletURL.setParameter("orderByType", orderByType);

		int cur = ParamUtil.getInteger(request, SearchContainer.DEFAULT_CUR_PARAM);

		if (cur > 0) {
			portletURL.setParameter("cur", String.valueOf(cur));
		}

		return new ViewTypeItemList(portletURL, getDisplayStyle()) {
			{
				//addCardViewTypeItem();
				//addListViewTypeItem();
				addTableViewTypeItem();				
				/*add(
					viewTypeItem -> {
						viewTypeItem.setIcon("list");
						viewTypeItem.setHref(portletURL, "displayStyle", "descriptive");
						viewTypeItem.setLabel(LanguageUtil.get(LocaleUtil.getMostRelevantLocale(), "list"));
						viewTypeItem.setActive(Objects.equals(getDisplayStyle(), "descriptive"));
					});
				*/
			}
		};
	}
	
	
	
	
	public List<DropdownItem> getFilterDropdownItems() {		
		List<String> orderByList = new ArrayList<String>();
		orderByList.add("Status");
				
		PortletPreferences preferences = _themeDisplay.getPortletDisplay().getPortletSetup();
		String portletConfigAggregateView = preferences.getValue("aggregateView", "No");
		
		if(StringUtil.equals(portletConfigAggregateView.toLowerCase(), "yes")){
			orderByList.add("Party"); 
		}
		
		orderByList.add("Activity");
		//orderByList.add("dueDate");
		List<String> filterByStatus = new ArrayList<String>();
		List<String> filterByParty = new ArrayList<String>();
		try {
			DynamicQuery dynamicQueryStatus = MaintenanceActivityLocalServiceUtil.dynamicQuery();
			List<String> maintenanceStatusList = new ArrayList<String>();
			dynamicQueryStatus.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property("status")));
			maintenanceStatusList = MaintenanceActivityLocalServiceUtil.dynamicQuery(dynamicQueryStatus, -1, -1);
			if(Validator.isNotNull(maintenanceStatusList)) {
				for (int i= 0; i< maintenanceStatusList.size();i++) {
					if (Validator.isNotNull(maintenanceStatusList.get(i))) {
						filterByStatus.add(maintenanceStatusList.get(i));
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Error at search menu status:"+e.getMessage());
		}						
		
		if(StringUtil.equals(portletConfigAggregateView.toLowerCase(), "yes")){
			try {
				DynamicQuery dynamicQueryParty = MaintenanceActivityLocalServiceUtil.dynamicQuery();
				List<String> maintenancePartyList = new ArrayList<String>();
				dynamicQueryParty.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property("specificParty")));
				//we filter specifiv partis by scopeGroupdId
				//dynamicQueryParty.add(RestrictionsFactoryUtil.eq("orgSiteId", _themeDisplay.getScopeGroupId()));
				maintenancePartyList = MaintenanceActivityLocalServiceUtil.dynamicQuery(dynamicQueryParty, -1, -1);
				if(Validator.isNotNull(maintenancePartyList)) {
					for (int i= 0; i< maintenancePartyList.size();i++) {
						if (Validator.isNotNull(maintenancePartyList.get(i))) {
							filterByParty.add(maintenancePartyList.get(i));
						}
					}
				}
			} catch (Exception e) {
				System.out.println("Error at search menu party:"+e.getMessage());
			}
		}
		
		
		try {
			return getFilterDropdownItems(filterByStatus, filterByParty, orderByList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	@SuppressWarnings("serial")
	public List<DropdownItem> getFilterDropdownItems(List<String> filterByStatus, List<String> filterByParty, List<String> orderByList) {
		return new DropdownItemList() {
		    {
		      if(filterByStatus.size()>0) {
		    	addGroup(		        
		          dropdownGroupItem -> {
		            dropdownGroupItem.setDropdownItems(addDropdowFilterStatusMenu(filterByStatus));
		            dropdownGroupItem.setLabel( "filter-by-status");
		            dropdownGroupItem.setActive(false);
		          });
		      
		      }
		      
		      if(filterByParty.size()>0) {
			    	addGroup(		        
			          dropdownGroupItem -> {
			            dropdownGroupItem.setDropdownItems(addDropdowFilterPartyMenu(filterByParty));
			            dropdownGroupItem.setLabel( "filter-by-party");
			            dropdownGroupItem.setActive(false);
			          });
			      
			  }
		      
		      if(orderByList.size()>0) {
		      addGroup(
		        dropdownGroupItem -> {
		          dropdownGroupItem.setDropdownItems(addDropdowOrderByMenu(orderByList));
		          dropdownGroupItem.setLabel( "order-by");
		          dropdownGroupItem.setActive(false);
		        });
		      }
		    }
		};
	}
	
	
	public DropdownItemList addDropdowFilterStatusMenu(List<String> filterList) throws PortletException {
		DropdownItemList  list= new DropdownItemList();	
		for(int i = 0; i< filterList.size(); i++) {
			String filter = filterList.get(i);			
			DropdownItem dropdownItem = new DropdownItem();
			dropdownItem.setLabel(filter);
			dropdownItem.setActive(false);					
			if(StringUtil.equalsIgnoreCase(filter,ParamUtil.getString(httpServletRequest, "filterStatus"))) {
				dropdownItem.setHref(_getCurrentSortingURL(), "filterStatus","none", "redirect", currentURLObj.toString()	);				
			}else {
				dropdownItem.setHref(_getCurrentSortingURL(), "filterStatus",filter, "redirect", currentURLObj.toString());
			}
			dropdownItem.setActive(StringUtil.equalsIgnoreCase(filter,ParamUtil.getString(httpServletRequest, "filterStatus")));
			dropdownItem.setSeparator(false);
			list.add(dropdownItem);
		}
		return list;
	}
	
	
	public DropdownItemList addDropdowFilterPartyMenu(List<String> filterList) throws PortletException {
		DropdownItemList  list= new DropdownItemList();	
		for(int i = 0; i< filterList.size(); i++) {
			String filter = filterList.get(i);			
			DropdownItem dropdownItem = new DropdownItem();
			dropdownItem.setLabel(filter);
			dropdownItem.setActive(false);					
			if(StringUtil.equalsIgnoreCase(filter,ParamUtil.getString(httpServletRequest, "filterParty"))) {
				dropdownItem.setHref(_getCurrentSortingURL(), "filterParty","none", "redirect", currentURLObj.toString()	);				
			}else {
				dropdownItem.setHref(_getCurrentSortingURL(), "filterParty",filter, "redirect", currentURLObj.toString());
			}
			dropdownItem.setActive(StringUtil.equalsIgnoreCase(filter,ParamUtil.getString(httpServletRequest, "filterParty")));
			dropdownItem.setSeparator(false);
			list.add(dropdownItem);
		}
		return list;
	}
	
	
	
	public DropdownItemList addDropdowOrderByMenu(List<String> activityStatusList) throws PortletException {
		DropdownItemList  list= new DropdownItemList();	
		for(int i = 0; i< activityStatusList.size(); i++) {
			String status = activityStatusList.get(i);			
			DropdownItem dropdownItem = new DropdownItem();
			if(StringUtil.equalsIgnoreCase(status,ParamUtil.getString(httpServletRequest, "orderByCol"))) {
				dropdownItem.setHref(_getCurrentSortingURL(), "orderByCol","none", "redirect", currentURLObj.toString()	);				
			}else {
				dropdownItem.setHref(_getCurrentSortingURL(), "orderByCol",status, "redirect", currentURLObj.toString());
			}
			dropdownItem.setActive(StringUtil.equalsIgnoreCase(status,ParamUtil.getString(httpServletRequest, "orderByCol")));
			dropdownItem.setLabel(status );
			dropdownItem.setSeparator(false);
			list.add(dropdownItem);
		}	
		return list;
	}
	
	
	public static LabelItemList filterLabelMenu(RenderRequest renderRequest){
		//TODO SE TIENE Q GUARDAR LA INFO EN UNA VARIABLE PARA SABER POR QUE SE ESTA FILTRANDO
		return new LabelItemList() {
		    {
		      add(
		        labelItem -> {
		          labelItem.setLabel("Filter 1");
		        });
		
		      add(
		        labelItem -> {
		          labelItem.setLabel("Filter 2");
		        });
		    }
		  };
	}
	
	
	private final PortalPreferences _portalPreferences;	
	private final ThemeDisplay _themeDisplay;
	
	@Reference
	protected MaintenanceActivityLocalService _maintenanceActivityLocalService;
}

