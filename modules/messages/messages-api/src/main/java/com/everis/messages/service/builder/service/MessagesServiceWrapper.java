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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MessagesService}.
 *
 * @author Brian Wing Shun Chan
 * @see MessagesService
 * @generated
 */
public class MessagesServiceWrapper
	implements MessagesService, ServiceWrapper<MessagesService> {

	public MessagesServiceWrapper(MessagesService messagesService) {
		_messagesService = messagesService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _messagesService.getOSGiServiceIdentifier();
	}

	@Override
	public void markAsReadNotification(
		long userId, long userEventNotificationId) {

		_messagesService.markAsReadNotification(
			userId, userEventNotificationId);
	}

	@Override
	public void sendNotification02(
		long companyId, long groupId, long userId, String cpReference,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		_messagesService.sendNotification02(
			companyId, groupId, userId, cpReference, serviceContext);
	}

	@Override
	public void sendNotification09(
		long userId, long groupId, long companyId, long classPK,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		_messagesService.sendNotification09(
			userId, groupId, companyId, classPK, serviceContext);
	}

	@Override
	public void sendNotification15(
		long companyId, long groupId, String cPReference,
		String commentsDeadline, long resourcePrimKey, String email) {

		_messagesService.sendNotification15(
			companyId, groupId, cPReference, commentsDeadline, resourcePrimKey,
			email);
	}

	@Override
	public void sendNotification18(
		long companyId, long groupId, long userId, long resourcePrimaryKey,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		_messagesService.sendNotification18(
			companyId, groupId, userId, resourcePrimaryKey, serviceContext);
	}

	@Override
	public void sendNotification29(
		long userId, long resourcePrimaryKey, long siteGroupId) {

		_messagesService.sendNotification29(
			userId, resourcePrimaryKey, siteGroupId);
	}

	@Override
	public void sendNotification30(
		long userId, long resourcePrimaryKey, long siteGroupId) {

		_messagesService.sendNotification30(
			userId, resourcePrimaryKey, siteGroupId);
	}

	@Override
	public void sendNotification31(
		long companyId, long groupId, long userId, String roleNames,
		String VotingLink, String titleOfNotification,
		String bodyOfNotification) {

		_messagesService.sendNotification31(
			companyId, groupId, userId, roleNames, VotingLink,
			titleOfNotification, bodyOfNotification);
	}

	@Override
	public void sendNotification46(
		String notificationTitle, String notificationBody, String segment,
		long currentUserId, String webEnabled, String emailEnabled) {

		_messagesService.sendNotification46(
			notificationTitle, notificationBody, segment, currentUserId,
			webEnabled, emailEnabled);
	}

	@Override
	public void sendNotification75(
		long currentUserId, long siteGroupId, String remedationTitle,
		String urlEmail, long companyId) {

		_messagesService.sendNotification75(
			currentUserId, siteGroupId, remedationTitle, urlEmail, companyId);
	}

	@Override
	public MessagesService getWrappedService() {
		return _messagesService;
	}

	@Override
	public void setWrappedService(MessagesService messagesService) {
		_messagesService = messagesService;
	}

	private MessagesService _messagesService;

}