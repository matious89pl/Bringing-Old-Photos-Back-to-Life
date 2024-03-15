package com.everis.rec.remediation.tracker.portlet;

import com.everis.rec.remediation.tracker.constants.RecRemediationTrackerWebPortletKeys;
import com.everis.rec.remediation.tracker.service.RemediationTrackerLocalServiceUtil;
import com.liferay.frontend.taglib.clay.servlet.taglib.display.context.BaseManagementToolbarDisplayContext;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.ViewTypeItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.ViewTypeItemList;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Order;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
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
import javax.servlet.http.HttpServletRequest;

import rec.filter.api.api.ManagementToolbarMultifilterUtil;

/**
 * Assigments management toolbar display context.
 *
 * This class passes contextual information to the user interface
 * for the Clay management toolbar.
 *
 * @author liferay
 */
public class RemediationTrackerManagementToolbarDisplayContext extends BaseManagementToolbarDisplayContext {
	
	public RemediationTrackerManagementToolbarDisplayContext(HttpServletRequest httpServletRequest,LiferayPortletRequest liferayPortletRequest, LiferayPortletResponse liferayPortletResponse) {
		
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

		searchURL.setProperty("mvcRenderCommandName", RecRemediationTrackerWebPortletKeys.VIEW_LANDING);		
		String navigation = ParamUtil.getString(httpServletRequest, "navigation", "entries");
		searchURL.setParameter("navigation", navigation);		

		searchURL.setParameter("orderByCol", getOrderByCol());
		searchURL.setParameter("orderByType", getOrderByType());
		searchURL.setParameter("filterStatus",ParamUtil.getString(httpServletRequest, "filterStatus"));
		searchURL.setParameter("filterParty", ParamUtil.getString(httpServletRequest, "filterParty"));
		searchURL.setParameter("filterDueDate", ParamUtil.getString(httpServletRequest, "filterDueDate"));
		searchURL.setParameter("keywords", "");
		//searchURL.setParameter("mvcPath", "/view.jsp");
		searchURL.setParameter(SearchContainer.DEFAULT_CUR_PARAM, ParamUtil.getString(	httpServletRequest, SearchContainer.DEFAULT_CUR_PARAM));

		ManagementToolbarMultifilterUtil.addMultipleFilterStringToPortletURL(httpServletRequest,searchURL,"strStatusMultipleFilter");
		ManagementToolbarMultifilterUtil.addMultipleFilterStringToPortletURL(httpServletRequest,searchURL,"strPartyMultipleFilter");
		ManagementToolbarMultifilterUtil.addMultipleFilterStringToPortletURL(httpServletRequest,searchURL,"strCategoryMultipleFilter");

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
			_portalPreferences.setValue(RecRemediationTrackerWebPortletKeys.RECREMEDIATIONTRACKERWEB, "assignments-display-style",displayStyle);
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
		return ParamUtil.getString(httpServletRequest, "orderByCol", "specificParty");
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

		sortingURL.setParameter("mvcRenderCommandName", RecRemediationTrackerWebPortletKeys.VIEW_LANDING);
		// Reset current page.
		sortingURL.setParameter(SearchContainer.DEFAULT_CUR_PARAM, "0");
		String keywords = ParamUtil.getString(httpServletRequest, "keywords");
		if (Validator.isNotNull(keywords)) {
			sortingURL.setParameter("keywords", keywords);
		}

		ManagementToolbarMultifilterUtil.addMultipleFilterStringToPortletURL(httpServletRequest,sortingURL,"strStatusMultipleFilter");
		ManagementToolbarMultifilterUtil.addMultipleFilterStringToPortletURL(httpServletRequest,sortingURL,"strPartyMultipleFilter");
		ManagementToolbarMultifilterUtil.addMultipleFilterStringToPortletURL(httpServletRequest,sortingURL,"strCategoryMultipleFilter");

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

		portletURL.setParameter("mvcRenderCommandName", RecRemediationTrackerWebPortletKeys.VIEW_LANDING);
		int delta =ParamUtil.getInteger(httpServletRequest, SearchContainer.DEFAULT_DELTA_PARAM);

		if (delta > 0) {
			portletURL.setParameter("delta", String.valueOf(delta));
		}

		String orderByCol =ParamUtil.getString(httpServletRequest, "orderByCol", "specificParty");
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
		
				
		PortletPreferences preferences = _themeDisplay.getPortletDisplay().getPortletSetup();
		String portletConfigAggregateView = preferences.getValue("aggregateRemediationTrackerView", "No");		
		if(StringUtil.equals(portletConfigAggregateView.toLowerCase(), "yes")){
			orderByList.add("Party"); 
		}
		
		orderByList.add("Title");
		orderByList.add("Category");
		orderByList.add("Description");
		orderByList.add("Due Date");
		orderByList.add("Status");
		
		List<String> filterByStatus = new ArrayList<String>();
		List<String> filterByCategory = new ArrayList<String>();
		List<String> filterByParty = new ArrayList<String>();
		
		try {
			DynamicQuery dynamicQueryStatus = RemediationTrackerLocalServiceUtil.dynamicQuery();
			List<String> maintenanceStatusList = new ArrayList<String>();
			dynamicQueryStatus.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property("status")));
			maintenanceStatusList = RemediationTrackerLocalServiceUtil.dynamicQuery(dynamicQueryStatus, -1, -1);
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
			DynamicQuery dynamicQueryCategory = RemediationTrackerLocalServiceUtil.dynamicQuery();
			List<String> categoryList = new ArrayList<String>();
			dynamicQueryCategory.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property("category")));
			Order order = OrderFactoryUtil.asc("category");		
			dynamicQueryCategory.addOrder(order);
			categoryList = RemediationTrackerLocalServiceUtil.dynamicQuery(dynamicQueryCategory, -1, -1);
			if(Validator.isNotNull(categoryList)) {
				for (int i= 0; i< categoryList.size();i++) {
					if (Validator.isNotNull(categoryList.get(i))) {
						filterByCategory.add(categoryList.get(i));
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Error at menu category:"+e.getMessage());
		}
		
		
		if(StringUtil.equals(portletConfigAggregateView.toLowerCase(), "yes")){
			try {
				DynamicQuery dynamicQueryCategory = RemediationTrackerLocalServiceUtil.dynamicQuery();
				List<String> partyList = new ArrayList<String>();
				dynamicQueryCategory.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property("specificParty")));
				Order order = OrderFactoryUtil.asc("specificParty");		
				dynamicQueryCategory.addOrder(order);
				partyList = RemediationTrackerLocalServiceUtil.dynamicQuery(dynamicQueryCategory, -1, -1);
				if(Validator.isNotNull(partyList)) {
					for (int i= 0; i< partyList.size();i++) {
						if (Validator.isNotNull(partyList.get(i))) {
							filterByParty.add(partyList.get(i));
						}
					}
				}
			} catch (Exception e) {
				System.out.println("Error at menu party:"+e.getMessage());
			}
		}		
		
		try {
			return getFilterDropdownItems(filterByStatus, filterByCategory, filterByParty, orderByList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	@SuppressWarnings("serial")
	public List<DropdownItem> getFilterDropdownItems(List<String> filterByStatus, List<String> filterByCategory, List<String> filterByParty, List<String> orderByList) {
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
		      
		      if(filterByCategory.size()>0) {
			    	addGroup(		        
			          dropdownGroupItem -> {
			            dropdownGroupItem.setDropdownItems(addDropdowFilterCategoryMenu(filterByCategory));
			            dropdownGroupItem.setLabel( "Filter by category");
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
		return ManagementToolbarMultifilterUtil.addDropdowFilterMenu(httpServletRequest, _getCurrentSortingURL(), currentURLObj.toString(), filterList, "filterStatus", "strStatusMultipleFilter");

	}
	
	public DropdownItemList addDropdowFilterCategoryMenu(List<String> filterList) throws PortletException {
		return ManagementToolbarMultifilterUtil.addDropdowFilterMenu(httpServletRequest, _getCurrentSortingURL(), currentURLObj.toString(), filterList, "filterCategory", "strCategoryMultipleFilter");

	}
	
	public DropdownItemList addDropdowFilterPartyMenu(List<String> filterList) throws PortletException {
		return ManagementToolbarMultifilterUtil.addDropdowFilterMenu(httpServletRequest, _getCurrentSortingURL(), currentURLObj.toString(), filterList, "filterParty", "strPartyMultipleFilter");

	}
	
	public DropdownItemList addDropdowOrderByMenu(List<String> activityOrderList) throws PortletException {
		return ManagementToolbarMultifilterUtil.addDropdownOrderByMenu(httpServletRequest, _getCurrentSortingURL(), currentURLObj.toString(), activityOrderList);

	}
		
	private final PortalPreferences _portalPreferences;	
	private final ThemeDisplay _themeDisplay;
	
}

