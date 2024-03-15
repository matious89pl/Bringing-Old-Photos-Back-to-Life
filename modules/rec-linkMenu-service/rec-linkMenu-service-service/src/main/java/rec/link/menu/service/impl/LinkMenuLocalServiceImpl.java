/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package rec.link.menu.service.impl;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;

import java.util.Collections;
import java.util.List;

import org.osgi.service.component.annotations.Component;

import rec.link.menu.model.LinkMenu;
import rec.link.menu.service.LinkMenuLocalServiceUtil;
import rec.link.menu.service.base.LinkMenuLocalServiceBaseImpl;

/**
 * The implementation of the link menu local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>rec.link.menu.service.LinkMenuLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LinkMenuLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=rec.link.menu.model.LinkMenu",
	service = AopService.class
)
public class LinkMenuLocalServiceImpl extends LinkMenuLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>rec.link.menu.service.LinkMenuLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>rec.link.menu.service.LinkMenuLocalServiceUtil</code>.
	 */

Log log = LogFactoryUtil.getLog(LinkMenuLocalServiceImpl.class);
	
	public LinkMenu addLink(long linkId, String link, String linkName, String iconName, int linkPosition, ServiceContext context)  {
		
		User user = null;
		try {
			user = UserLocalServiceUtil.getUser(context.getUserId());
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			log.error("Error gettinng the user (ServiceImpl)", e);
		}
		LinkMenu newLink = null;
		
		if(linkId > 0) {
			 try {
				newLink = LinkMenuLocalServiceUtil.getLinkMenu(linkId);
			} catch (PortalException e) {
				// TODO Auto-generated catch block
				log.error("Error getting the link (ServiceImpl)", e);
			}
		} else {
			long newLinkId = CounterLocalServiceUtil.increment(LinkMenu.class.getName());
			 newLink = LinkMenuLocalServiceUtil.createLinkMenu(newLinkId);
		}
		 newLink.setUserId(context.getUserId());
		 newLink.setUserName(user.getFullName());
		 newLink.setGroupId(context.getScopeGroupId());
		 newLink.setLinkName(linkName.trim());
		 newLink.setLink(link.trim());
		 newLink.setIconName(iconName.trim());
		 newLink.setLinkPosition(linkPosition);
		 
		 LinkMenuLocalServiceUtil.updateLinkMenu(newLink);
		 return  newLink;	
		}
	
	public List<LinkMenu> getSortedByLinksPosition(List<LinkMenu> links) {
	    OrderByComparator<LinkMenu> comparator = OrderByComparatorFactoryUtil.create(
	        "LinkMenu", "linkPosition", true);
	    Collections.sort(links, comparator);
	    return links;
	}
}