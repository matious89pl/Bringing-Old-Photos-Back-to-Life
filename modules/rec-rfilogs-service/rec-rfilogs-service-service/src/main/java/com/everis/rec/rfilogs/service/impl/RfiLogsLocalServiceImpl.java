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

package com.everis.rec.rfilogs.service.impl;

import com.everis.rec.rfilogs.model.RfiLogs;
import com.everis.rec.rfilogs.service.base.RfiLogsLocalServiceBaseImpl;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetLinkConstants;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.ContentTypes;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * The implementation of the rfi logs local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * <code>com.everis.rec.rfilogs.service.RfiLogsLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RfiLogsLocalServiceBaseImpl
 */
@Component(property = "model.class.name=com.everis.rec.rfilogs.model.RfiLogs", service = AopService.class)
public class RfiLogsLocalServiceImpl extends RfiLogsLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use
	 * <code>com.everis.rec.rfilogs.service.RfiLogsLocalService</code> via injection
	 * or a <code>org.osgi.util.tracker.ServiceTracker</code> or use
	 * <code>com.everis.rec.rfilogs.service.RfiLogsLocalServiceUtil</code>.
	 */

	private static final Log _log = LogFactoryUtil.getLog(RfiLogsLocalServiceImpl.class.getName());

	public void newRFI(long userId, RfiLogs newRFI, ServiceContext serviceContext) {

		long groupId = serviceContext.getScopeGroupId();
		User user;

		try {
			user = UserLocalServiceUtil.getUserById(userId);
			_log.debug("newRFI--> " + newRFI);
			_log.debug("groupId--> " + groupId);
			_log.debug("user--> " + user);

			rfiLogsPersistence.update(newRFI);
			_log.debug("RFI properly created!");

			AssetEntry assetEntry = assetEntryLocalService.updateEntry(userId, groupId, newRFI.getCreateDate(),
					newRFI.getModifiedDate(), RfiLogs.class.getName(), newRFI.getRfilogId(), newRFI.getUuid(), 0,
					serviceContext.getAssetCategoryIds(), serviceContext.getAssetTagNames(), true, true, null, null,
					null, null, ContentTypes.TEXT_HTML, newRFI.getTitle(), null, null, null, null, 0, 0, null);
			_log.debug("RFI  AssetEntry properly created!");

			assetLinkLocalService.updateLinks(userId, assetEntry.getEntryId(), serviceContext.getAssetLinkEntryIds(),
					AssetLinkConstants.TYPE_RELATED);
			_log.debug("RFI  AssetEntryLink properly created!");

		} catch (PortalException e) {
			_log.error("Error creating RFI", e);
		}

	}

	public List<RfiLogs> findRfiLogs(int searchContainerStart, int searchContainerEnd) {
		return rfiLogsPersistence.findAll(searchContainerStart, searchContainerEnd);
	}

	public List<RfiLogs> findByStatus(String status) {

		return rfiLogsPersistence.findByStatus(status);

	}

}