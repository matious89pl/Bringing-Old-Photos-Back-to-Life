package com.everis.rec.maintenanceforms.portlet;

import com.everis.rec.maintenanceforms.constants.MaintenanceFormsActionPortletKeys;
import com.liferay.frontend.taglib.clay.servlet.taglib.display.context.BaseManagementToolbarDisplayContext;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.ViewTypeItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.ViewTypeItemList;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Order;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
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
import javax.servlet.http.HttpServletRequest;

import rec.everis.forms.service.service.MaintenanceActivityFormsLocalServiceUtil;

/**
 * Assigments management toolbar display context.
 *
 * This class passes contextual information to the user interface
 * for the Clay management toolbar.
 *
 * @author liferay
 */
public class MaintenanceFormsManagementToolbarDisplayContext extends BaseManagementToolbarDisplayContext {
	
	public MaintenanceFormsManagementToolbarDisplayContext(HttpServletRequest httpServletRequest,LiferayPortletRequest liferayPortletRequest, LiferayPortletResponse liferayPortletResponse) {
		
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

		searchURL.setProperty("mvcRenderCommandName", MaintenanceFormsActionPortletKeys.VIEW_LANDING);		
		String navigation = ParamUtil.getString(httpServletRequest, "navigation", "entries");
		searchURL.setParameter("navigation", navigation);		

		searchURL.setParameter("orderByCol", getOrderByCol());
		searchURL.setParameter("orderByType", getOrderByType());
		searchURL.setParameter("filterStatus",ParamUtil.getString(httpServletRequest, "filterStatus"));
		searchURL.setParameter("filterParty", ParamUtil.getString(httpServletRequest, "filterParty"));
		searchURL.setParameter("filterSubmitDate", ParamUtil.getString(httpServletRequest, "filterSubmitDate"));
		searchURL.setParameter("keywords", "");
		//searchURL.setParameter("mvcPath", "/view.jsp");
		searchURL.setParameter(SearchContainer.DEFAULT_CUR_PARAM, ParamUtil.getString(	httpServletRequest, SearchContainer.DEFAULT_CUR_PARAM));

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
			displayStyle = "list";//_portalPreferences.getValue(	RecActivityAggregateWebPortletKeys.RECACTIVITYAGGREGATEWEB, "assignments-display-style","descriptive");
		}
		else {
			_portalPreferences.setValue(MaintenanceFormsActionPortletKeys.MAINTENANCEFORMSACTION, "assignments-display-style",displayStyle);
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
		return ParamUtil.getString(httpServletRequest, "orderByCol", "Party");
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
	private PortletURL _getCurrentSortingURL() throws PortletException {
		PortletURL sortingURL = PortletURLUtil.clone(currentURLObj, liferayPortletResponse);

		sortingURL.setParameter("mvcRenderCommandName", MaintenanceFormsActionPortletKeys.VIEW_LANDING);
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
	
		
	public Boolean isShowCreationMenu() {
		return false;
	}
	
	@Override
	public Boolean isShowAdvancedSearch() {
		return false;
	}

	@Override
	public Boolean isShowFiltersDoneButton() {
		return false;
	}

	@Override
	public Boolean isShowInfoButton() {
		return false;
	}

	@Override
	public Boolean isShowSearch() {
		return true;
	}

	
	
	
	/**	
	 * Returns the view type options (card, list, table).
	 *
	 * @return list of view types
	 */
	@Override
	public List<ViewTypeItem> getViewTypeItems() {
		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		portletURL.setParameter("mvcRenderCommandName", MaintenanceFormsActionPortletKeys.VIEW_LANDING);
		int delta =ParamUtil.getInteger(httpServletRequest, SearchContainer.DEFAULT_DELTA_PARAM);

		if (delta > 0) {
			portletURL.setParameter("delta", String.valueOf(delta));
		}

		String orderByCol =ParamUtil.getString(httpServletRequest, "orderByCol", "orgSiteId");
		String orderByType =ParamUtil.getString(httpServletRequest, "orderByType", "asc");

		portletURL.setParameter("orderByCol", orderByCol);
		portletURL.setParameter("orderByType", orderByType);

		int cur = ParamUtil.getInteger(request, SearchContainer.DEFAULT_CUR_PARAM);	
		if (cur > 0) {
			portletURL.setParameter("cur", String.valueOf(cur));
		}

		return new ViewTypeItemList(portletURL, getDisplayStyle()) {
		{
				addTableViewTypeItem();				
		}
		};
	}
	
	public List<DropdownItem> getFilterDropdownItems() {		
		List<String> orderByList = new ArrayList<String>();		
				
		PortletPreferences preferences = _themeDisplay.getPortletDisplay().getPortletSetup();
		String portletConfigAggregateView = preferences.getValue("aggregateFormView", "No");		
		if(StringUtil.equals(portletConfigAggregateView.toLowerCase(), "yes")){
			orderByList.add("Party"); 
		}		
		orderByList.add("Submitted Date");		
		
		List<String> filterByStatus = new ArrayList<String>();		
		List<Group> filterByParty = new ArrayList<Group>();
		List<Integer> filterByYear = new ArrayList<>();	
		
		
		try {
			DynamicQuery dynamicQueryStatus = MaintenanceActivityFormsLocalServiceUtil.dynamicQuery();
			List<String> maintenanceStatusList = new ArrayList<String>();
			dynamicQueryStatus.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property("status")));			
			if(StringUtil.equals(portletConfigAggregateView.toLowerCase(), "no")){
				dynamicQueryStatus.add(RestrictionsFactoryUtil.eq("orgSiteId", _themeDisplay.getSiteGroupId()));
			}
			maintenanceStatusList = MaintenanceActivityFormsLocalServiceUtil.dynamicQuery(dynamicQueryStatus, -1, -1);
			if(Validator.isNotNull(maintenanceStatusList)) {
				for (int i= 0; i< maintenanceStatusList.size();i++) {
					if (Validator.isNotNull(maintenanceStatusList.get(i))) {
						filterByStatus.add(maintenanceStatusList.get(i));
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Error at menu status:"+e.getMessage());
		}
		
		try {
			DynamicQuery dynamicQueryYear = MaintenanceActivityFormsLocalServiceUtil.dynamicQuery();
			List<Integer> maintenanceYearList = new ArrayList<Integer>();
			dynamicQueryYear.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property("year")));			
			if(StringUtil.equals(portletConfigAggregateView.toLowerCase(), "no")){
				dynamicQueryYear.add(RestrictionsFactoryUtil.eq("orgSiteId", _themeDisplay.getSiteGroupId()));
			}
			
			Order order = OrderFactoryUtil.desc("year");		
			dynamicQueryYear.addOrder(order);
			
			maintenanceYearList = MaintenanceActivityFormsLocalServiceUtil.dynamicQuery(dynamicQueryYear, -1, -1);
			if(Validator.isNotNull(maintenanceYearList)) {
				for (int i= 0; i< maintenanceYearList.size();i++) {
					if (Validator.isNotNull(maintenanceYearList.get(i))) {
						filterByYear.add(maintenanceYearList.get(i));
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Error at menu year:"+e.getMessage());
		}
		
		
		if(StringUtil.equals(portletConfigAggregateView.toLowerCase(), "yes")){
			try {
				DynamicQuery dynamicQueryCategory = MaintenanceActivityFormsLocalServiceUtil.dynamicQuery();
				List<Long> partyList = new ArrayList<Long>();
				dynamicQueryCategory.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property("orgSiteId")));
				partyList = MaintenanceActivityFormsLocalServiceUtil.dynamicQuery(dynamicQueryCategory, -1, -1);
				if(Validator.isNotNull(partyList)) {
					for (int i= 0; i< partyList.size();i++) {
						if (Validator.isNotNull(String.valueOf(partyList.get(i)))) {
							filterByParty.add(GroupLocalServiceUtil.getGroup(partyList.get(i)));
						}
					}
				}
			} catch (Exception e) {
				System.out.println("Error at menu party:"+e.getMessage());
			}
		}		
		
		try {
			return getFilterDropdownItems(filterByStatus, filterByParty, filterByYear, orderByList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	@SuppressWarnings("serial")
	public List<DropdownItem> getFilterDropdownItems(List<String> filterByStatus, List<Group> filterByParty, List <Integer> filterByYear, List<String> orderByList) {
		return new DropdownItemList() {
		    {
		      if(filterByStatus.size()>0) {
		    	addGroup(		        
		          dropdownGroupItem -> {
		            dropdownGroupItem.setDropdownItems(addDropdowFilterStatusMenu(filterByStatus));
		            dropdownGroupItem.setLabel( "Filter by status");
		            dropdownGroupItem.setActive(false);
		          });
		      
		      }
		      
		      if(filterByParty.size()>0) {
			    	addGroup(		        
			          dropdownGroupItem -> {
			            dropdownGroupItem.setDropdownItems(addDropdowFilterPartyMenu(filterByParty));
			            dropdownGroupItem.setLabel( "Filter by party");
			            dropdownGroupItem.setActive(false);
			          });
			      
			  } 	
		      
		      if(filterByYear.size()>0) {
			    	addGroup(		        
			          dropdownGroupItem -> {
			            dropdownGroupItem.setDropdownItems(addDropdowFilterYearMenu(filterByYear));
			            dropdownGroupItem.setLabel( "Filter by year");
			            dropdownGroupItem.setActive(false);
			          });
			      
			  } 	
		      
		      
		      if(orderByList.size()>0) {
		      addGroup(
		        dropdownGroupItem -> {
		          dropdownGroupItem.setDropdownItems(addDropdowOrderByMenu(orderByList));
		          dropdownGroupItem.setLabel( "Order by");
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
		
	public DropdownItemList addDropdowFilterPartyMenu(List<Group> partyList) throws PortletException {
		DropdownItemList  list= new DropdownItemList();	
		for(int i = 0; i< partyList.size(); i++) {
			Group party = partyList.get(i);			
			DropdownItem dropdownItem = new DropdownItem();
			dropdownItem.setLabel(party.getName(_themeDisplay.getLocale()));
			dropdownItem.setActive(false);					
			if(StringUtil.equalsIgnoreCase(String.valueOf(party.getGroupId()),ParamUtil.getString(httpServletRequest, "filterParty"))) {
				dropdownItem.setHref(_getCurrentSortingURL(), "filterParty","none", "redirect", currentURLObj.toString()	);				
			}else {
				dropdownItem.setHref(_getCurrentSortingURL(), "filterParty",party.getGroupId(), "redirect", currentURLObj.toString());
			}
			dropdownItem.setActive(StringUtil.equalsIgnoreCase(String.valueOf(party.getGroupId()),ParamUtil.getString(httpServletRequest, "filterParty")));
			dropdownItem.setSeparator(false);
			list.add(dropdownItem);
		}
		return list;
	}
	
	public DropdownItemList addDropdowFilterYearMenu(List<Integer> filterYear) throws PortletException {
		DropdownItemList  list= new DropdownItemList();	
		for(Integer year : filterYear) {
			String filterYearTemp = String.valueOf(year);			
			DropdownItem dropdownItem = new DropdownItem();
			dropdownItem.setLabel(filterYearTemp);
			dropdownItem.setActive(false);					
			if(StringUtil.equalsIgnoreCase(filterYearTemp,ParamUtil.getString(httpServletRequest, "filterYear"))) {
				dropdownItem.setHref(_getCurrentSortingURL(), "filterYear","none", "redirect", currentURLObj.toString()	);				
			}else {
				dropdownItem.setHref(_getCurrentSortingURL(), "filterYear",filterYearTemp, "redirect", currentURLObj.toString());
			}
			dropdownItem.setActive(StringUtil.equalsIgnoreCase(filterYearTemp,ParamUtil.getString(httpServletRequest, "filterYear")));
			dropdownItem.setSeparator(false);
			list.add(dropdownItem);
		}
		return list;
	}
	
	public DropdownItemList addDropdowOrderByMenu(List<String> activityOrderList) throws PortletException {
		DropdownItemList  list= new DropdownItemList();	
		for(int i = 0; i< activityOrderList.size(); i++) {
			String status = activityOrderList.get(i);			
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
		
	private final PortalPreferences _portalPreferences;	
	private final ThemeDisplay _themeDisplay;
	
}

