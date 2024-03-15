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

package com.everis.messages.service.builder.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Transactional;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the remote service interface for Messages. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see MessagesServiceUtil
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface MessagesService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.everis.messages.service.builder.service.impl.MessagesServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the messages remote service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link MessagesServiceUtil} if injection and service tracking are not available.
	 */

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	@JSONWebService(method = "POST", value = "mark_as_read_notification")
	public void markAsReadNotification(
		long userId, long userEventNotificationId);

	@JSONWebService(method = "POST", value = "send_notification_02")
	public void sendNotification02(
		long companyId, long groupId, long userId, String cpReference,
		ServiceContext serviceContext);

	@JSONWebService(method = "POST", value = "send_notification_09")
	public void sendNotification09(
		long userId, long groupId, long companyId, long classPK,
		ServiceContext serviceContext);

	@JSONWebService(method = "POST", value = "send_notification_15")
	public void sendNotification15(
		long companyId, long groupId, String cPReference,
		String commentsDeadline, long resourcePrimKey, String email);

	@JSONWebService(method = "POST", value = "send_notification_018")
	public void sendNotification18(
		long companyId, long groupId, long userId, long resourcePrimaryKey,
		ServiceContext serviceContext);

	@JSONWebService(method = "POST", value = "send_notification_029")
	public void sendNotification29(
		long userId, long resourcePrimaryKey, long siteGroupId);

	@JSONWebService(method = "POST", value = "send_notification_030")
	public void sendNotification30(
		long userId, long resourcePrimaryKey, long siteGroupId);

	@JSONWebService(method = "POST", value = "send_notification_031")
	public void sendNotification31(
		long companyId, long groupId, long userId, String roleNames,
		String VotingLink, String titleOfNotification,
		String bodyOfNotification);

	@JSONWebService(method = "POST", value = "send_notification_46")
	public void sendNotification46(
		String notificationTitle, String notificationBody, String segment,
		long currentUserId, String webEnabled, String emailEnabled);

	@JSONWebService(method = "POST", value = "send_notification_75")
	public void sendNotification75(
		long currentUserId, long siteGroupId, String remedationTitle,
		String urlEmail, long companyId);

}