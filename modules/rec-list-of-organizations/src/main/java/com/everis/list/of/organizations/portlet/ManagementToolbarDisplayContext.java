package com.everis.list.of.organizations.portlet;

import com.everis.list.of.organizations.constants.RecListOfOrganizationsPortletKeys;
import com.liferay.frontend.taglib.clay.servlet.taglib.display.context.BaseManagementToolbarDisplayContext;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.servlet.http.HttpServletRequest;

public class ManagementToolbarDisplayContext extends BaseManagementToolbarDisplayContext {
	
	private final PortalPreferences _portalPreferences;	
	private final ThemeDisplay _themeDisplay;
		
	public ManagementToolbarDisplayContext(HttpServletRequest httpServletRequest,
			LiferayPortletRequest liferayPortletRequest, LiferayPortletResponse liferayPortletResponse) {
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
	@SuppressWarnings("deprecation")
	@Override
	public String getSearchActionURL() {
		PortletURL searchURL = liferayPortletResponse.createRenderURL();
		searchURL.setProperty("mvcRenderCommandName", RecListOfOrganizationsPortletKeys.VIEW_LANDING);		
		searchURL.setParameter("orderByCol", getOrderByCol());
		searchURL.setParameter("orderByType", getOrderByType());
		searchURL.setParameter("filterByName", "name");
		searchURL.setParameter("keywords", "");
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
			displayStyle = "list";
		}
		else {
			_portalPreferences.setValue(RecListOfOrganizationsPortletKeys.RECLISTOFORGANIZATIONS, "assignments-display-style",displayStyle);
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
		return ParamUtil.getString(httpServletRequest, "orderByCol", "name");
	}
	
	/**
	 * Returns the sort type (ascending / descending).
	 * 
	 * @return sort type
	 */
	public String getOrderByType() {
		return ParamUtil.getString(httpServletRequest, "orderByType", "asc");
	}
	
	private PortletURL _getCurrentSortingURL() throws PortletException {
		PortletURL sortingURL = PortletURLUtil.clone(currentURLObj, liferayPortletResponse);
		sortingURL.setParameter("mvcRenderCommandName", RecListOfOrganizationsPortletKeys.VIEW_LANDING);
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
		return "orgListEntries";
	}
		
	public List<DropdownItem> getFilterDropdownItems() {
		List<String> filterByName = new ArrayList<String>();
		List<String> filterByParentName = new ArrayList<String>();

		try {
			List<Organization> orgList = OrganizationLocalServiceUtil.getOrganizations(-1, -1);
			if(Validator.isNotNull(orgList)) {
			for (int i= 0; i< orgList.size();i++) {
					if (Validator.isNotNull(orgList.get(i))) {
						if (orgList.get(i).getSuborganizations().size() > 0) {
							String orgName = orgList.get(i).getName();
							filterByParentName.add(orgName);
						}
					}
				}
			}
			
			DynamicQuery dynamicQueryName = OrganizationLocalServiceUtil.dynamicQuery();
			List<String> orgFilterNameList = new ArrayList<String>();
			dynamicQueryName.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property("name")));
			orgFilterNameList = OrganizationLocalServiceUtil.dynamicQuery(dynamicQueryName, -1, -1);
			if(Validator.isNotNull(orgFilterNameList)) {
				for (int i= 0; i< orgFilterNameList.size();i++) {
					if (Validator.isNotNull(orgFilterNameList.get(i))) {
						filterByName.add(orgFilterNameList.get(i));
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Error at search menu status:"+e.getMessage());
		}						
				
		
		try {
			return getFilterDropdownItems(filterByName, filterByParentName/*, orderByList*/);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
		
		
	@SuppressWarnings("serial")
	public List<DropdownItem> getFilterDropdownItems(List<String> filterByName,List<String> filterByParentName/*, List<String> orderByList*/){
		return new DropdownItemList() {
		    {

		      if(filterByName.size()>0) {
			    	addGroup(		        
			          dropdownGroupItem -> {
			            dropdownGroupItem.setDropdownItems(addDropdowFilterNameMenu(filterByName));
			            dropdownGroupItem.setLabel( "filter by org name");
			            dropdownGroupItem.setActive(false);
			          });
			      
			  }
		      
		      if(filterByParentName.size()>0) {
			    	addGroup(		        
			          dropdownGroupItem -> {
			            dropdownGroupItem.setDropdownItems(addDropdowFilterParentNameMenu(filterByParentName));
			            dropdownGroupItem.setLabel( "filter by parent name");
			            dropdownGroupItem.setActive(false);
			          });
		      }
		    }
		};
	}
		
	public DropdownItemList addDropdowFilterNameMenu(List<String> filterList) throws PortletException {
		DropdownItemList  list= new DropdownItemList();	
		for(int i = 0; i< filterList.size(); i++) {
			String filter = filterList.get(i);			
			DropdownItem dropdownItem = new DropdownItem();
			dropdownItem.setLabel(filter);
			dropdownItem.setActive(false);					
			if(StringUtil.equalsIgnoreCase(filter,ParamUtil.getString(httpServletRequest, "filterByName"))) {
				dropdownItem.setHref(_getCurrentSortingURL(), "filterByName","none", "redirect", currentURLObj.toString());				
			}else {
				dropdownItem.setHref(_getCurrentSortingURL(), "filterByName",filter, "redirect", currentURLObj.toString());
			}
			dropdownItem.setActive(StringUtil.equalsIgnoreCase(filter,ParamUtil.getString(httpServletRequest, "filterByName")));
			dropdownItem.setSeparator(false);
			list.add(dropdownItem);
		}
		return list;
	}
	

	public DropdownItemList addDropdowFilterParentNameMenu(List<String> filterList) throws PortletException {
		DropdownItemList  list= new DropdownItemList();	
		for(int i = 0; i< filterList.size(); i++) {
			String filter = filterList.get(i);			
			DropdownItem dropdownItem = new DropdownItem();
			dropdownItem.setLabel(filter);
			dropdownItem.setActive(false);					
			if(StringUtil.equalsIgnoreCase(filter,ParamUtil.getString(httpServletRequest, "filterByParentName"))) {
				dropdownItem.setHref(_getCurrentSortingURL(), "filterByParentName","none", "redirect", currentURLObj.toString());				
			}else {
				dropdownItem.setHref(_getCurrentSortingURL(), "filterByParentName",filter, "redirect", currentURLObj.toString());
			}
			dropdownItem.setActive(StringUtil.equalsIgnoreCase(filter,ParamUtil.getString(httpServletRequest, "filterByParentName")));
			dropdownItem.setSeparator(false);
			list.add(dropdownItem);
		}
		return list;
	}

}
