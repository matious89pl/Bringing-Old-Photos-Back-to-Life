package rec.filter.api.api;

import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.servlet.http.HttpServletRequest;

public class ManagementToolbarMultifilterUtil {
	public static final String SEPARATOR = ";spt;";

	
	public static List<String> getMultipleFilterList(HttpServletRequest httpServletRequest, String multipleFilterString) {
		String strMultipleFilter = String.valueOf(httpServletRequest.getAttribute(multipleFilterString));
		
		List<String> filterList = new ArrayList<String>();
		
		if(!strMultipleFilter.isEmpty() && !strMultipleFilter.equals("null")) {
			filterList.addAll(Arrays.asList(strMultipleFilter.split(SEPARATOR)));
		}
		return filterList;
	}
	
	public static List<String> getMultipleFilterList(RenderRequest renderRequest,String multipleFilterString) {
		String strMultipleFilter = ParamUtil.getString(renderRequest, multipleFilterString);
		List<String> filterList = new ArrayList<String>();
		
		if(!strMultipleFilter.isEmpty() && !strMultipleFilter.equals("null")) {
			filterList.addAll(Arrays.asList(strMultipleFilter.split(SEPARATOR)));
		}
		return filterList;
	}
	
	public static void setMultipleFilterAttributeFromList(RenderRequest renderRequest, String attributeName , List<String> multipleFilterList) {
		String strMultipleFilter = String.join(SEPARATOR, multipleFilterList);
		HttpServletRequest httpServletRequest = PortalUtil.getHttpServletRequest(renderRequest);
		httpServletRequest.setAttribute(attributeName, strMultipleFilter);
	}
	
	public static List<String> changeMultipleFilter(RenderRequest renderRequest, String multipleFilterString, String filter) {
	
	List<String> filterList = getMultipleFilterList(renderRequest, multipleFilterString);

	if(!filter.equals("null") && !filter.equals("none") ) { 	
		if (filterList.contains(filter)) {
			filterList.remove(filter);
		} else {
			filterList.add(filter);
		}
	}
	
	return filterList;	
	}
	
	public static List<String> getUpdatedMultipleFilter(RenderRequest renderRequest, String multipleFilterString, String filterName) {
	
		//Get last clicked filter
			String lastClicked = ParamUtil.getString(renderRequest, "lastClicked");
		//Get single filter value
			String filter = ParamUtil.getString(renderRequest, filterName);
		//Get lastClicked item unique value
			String lastClickedItemValue = filterName + "-" + filter;

		//Get Multiple Filter Lists adding or removing filter if it was the last one clicked
			List<String> multipleFilterList = new ArrayList<String>();			

			if(Validator.isNotNull(filter) && lastClickedItemValue.equals(lastClicked)) {
				multipleFilterList = changeMultipleFilter(renderRequest,multipleFilterString, filter);
			} else {
				multipleFilterList = getMultipleFilterList(renderRequest, multipleFilterString);
			}
		//Set updated multi filter attribute to be processed in further render phase. 
			setMultipleFilterAttributeFromList(renderRequest, multipleFilterString, multipleFilterList);
	
			return multipleFilterList;
	}
	
	public static void addMultipleFilterStringToPortletURL(HttpServletRequest httpServletRequest, PortletURL sURL, String multipleFilterName) {
		String strMultipleFilter = String.valueOf(httpServletRequest.getAttribute(multipleFilterName));
		if(!strMultipleFilter.equals("null")) {
			sURL.setParameter(multipleFilterName, strMultipleFilter);
		}
	}
	
	public static DropdownItemList addDropdowFilterMenu(HttpServletRequest httpServletRequest,PortletURL portletSortingURL,String currentURL, List<String> filterList, String filterName, String multipleFilterName) throws PortletException {
		DropdownItemList  list= new DropdownItemList();

		List<String> multipleFilterList = ManagementToolbarMultifilterUtil.getMultipleFilterList(httpServletRequest,multipleFilterName);

		for(int i = 0; i< filterList.size(); i++) {
			String filter = filterList.get(i);
			String lastClickedValue = filterName +"-"+filter;
			DropdownItem dropdownItem = new DropdownItem();
			dropdownItem.setLabel(filter);
			dropdownItem.setActive(false);					
			dropdownItem.setHref(portletSortingURL, filterName,filter, "lastClicked", lastClickedValue,"redirect", currentURL);
			dropdownItem.setActive(multipleFilterList.contains(filter));
			dropdownItem.setSeparator(false);
			list.add(dropdownItem);
		}

		return list;
	}
	
	public static DropdownItemList addDropdownOrderByMenu(HttpServletRequest httpServletRequest,PortletURL portletSortingURL, String currentURL, List<String> activityOrderList) throws PortletException {
		DropdownItemList  list= new DropdownItemList();	
		for(int i = 0; i< activityOrderList.size(); i++) {
			String status = activityOrderList.get(i);			
			DropdownItem dropdownItem = new DropdownItem();
			if(StringUtil.equalsIgnoreCase(status,ParamUtil.getString(httpServletRequest, "orderByCol"))) {
				dropdownItem.setHref(portletSortingURL, "orderByCol","none","lastClicked", status, "redirect", currentURL);				
			}else {
				dropdownItem.setHref(portletSortingURL, "orderByCol",status,"lastClicked", status, "redirect", currentURL);
			}
			dropdownItem.setActive(StringUtil.equalsIgnoreCase(status,ParamUtil.getString(httpServletRequest, "orderByCol")));
			dropdownItem.setLabel(status );
			dropdownItem.setSeparator(false);
			list.add(dropdownItem);
		}	
		return list;
	}
}


