package com.everis.rec.activity.utils;

import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.LabelItemList;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

public class ToolUtils {
	
	private static final Log logger = LogFactoryUtil.getLog(ToolUtils.class);

	public static DropdownItemList addDropdowOrganizationMenu(List<Organization> userOrganizationList, RenderRequest renderRequest, RenderResponse renderResponse) {
		DropdownItemList  list= new DropdownItemList();	
		for(int i = 0; i< userOrganizationList.size(); i++) {
			Organization org = userOrganizationList.get(i);			
			logger.debug("START - addDropdowOrganizationMenu with organization: " + org.getName() + " and: " + org.getTreePath());
			DropdownItem dropdownItem = new DropdownItem();			
			//dropdownItem.setData(urlMenuItem.getData());
			//dropdownItem.setHref("?orgId="+org.getOrganizationId());
			//dropdownItem.setIcon(urlMenuItem.getIcon());
			dropdownItem.setLabel(org.getName() );
			dropdownItem.setSeparator(false);
			//TODO SEARCH IF PRESENT IN FILTERSESSION
			boolean presentInFilters=false;
			if(presentInFilters) {
				dropdownItem.setActive(false);					
				dropdownItem.setHref(renderResponse.createRenderURL(), "action","addFilter", "orgId",org.getOrganizationId());
				
			}else {
				dropdownItem.setActive(false);					
				dropdownItem.setHref(renderResponse.createRenderURL(), "action","removeFilter","orgId",org.getOrganizationId());
			}			
			//dropdownItem.setData(action);			
			//Parameters param;
			//dropdownItem.setH
			//dropdownItem.setHref(renderResponse.createRenderURL(), action);
			list.add(dropdownItem);
			logger.debug("END - addDropdowOrganizationMenu with organization: " + org.getName() + " and: " + org.getTreePath());
		}		
		
		return list;
	}
	
	public static DropdownItemList addDropdowStatusMenu(List<String> activityStatusList) {
		DropdownItemList  list= new DropdownItemList();	
		for(int i = 0; i< activityStatusList.size(); i++) {
			String status = activityStatusList.get(i);			
			logger.debug("START - addDropdowStatusMenu with status: " + status);
			DropdownItem dropdownItem = new DropdownItem();		
			Map<String, Object> action = new HashMap<>();
			boolean orderBy=false;
			if(orderBy) {
				dropdownItem.setActive(false);	
				action.put("action", new String("orderBy=asc"));
			}else {
				dropdownItem.setActive(false);	
				action.put("action", new String("orderBy=desc"));
			}			
			dropdownItem.setData(action);
			//dropdownItem.setData(urlMenuItem.getData());
			dropdownItem.setHref(status);
			//dropdownItem.setIcon(urlMenuItem.getIcon());
			dropdownItem.setLabel(status );
			dropdownItem.setSeparator(false);
			list.add(dropdownItem);
			logger.debug("END - addDropdowStatusMenu with status: " + status);
		}	
		return list;
	}
	
	@SuppressWarnings("serial")
	public static List<DropdownItem> getFilterDropdownItems(List<Organization> userOrganizationList, List<String> activityStatusList,RenderRequest renderRequest, RenderResponse renderResponse) {
		  return new DropdownItemList() {
		    {
		      if(userOrganizationList.size()>0) {
		    	addGroup(		        
		          dropdownGroupItem -> {
		            dropdownGroupItem.setDropdownItems(addDropdowOrganizationMenu(userOrganizationList, renderRequest,renderResponse));
		            dropdownGroupItem.setLabel( "filter-by-navigation");
		            dropdownGroupItem.setActive(false);
		          });
		      
		      }
		      if(activityStatusList.size()>0) {
		      addGroup(
		        dropdownGroupItem -> {
		          dropdownGroupItem.setDropdownItems(addDropdowStatusMenu(activityStatusList));
		          dropdownGroupItem.setLabel( "order-by");
		          dropdownGroupItem.setActive(false);
		        });
		      }
		    }
		  };
		}
	
	
	public static LabelItemList filterLabelMenu(RenderRequest renderRequest){
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
}
