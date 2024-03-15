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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for Messages. This utility wraps
 * <code>com.everis.messages.service.builder.service.impl.MessagesServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see MessagesService
 * @generated
 */
public class MessagesServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.everis.messages.service.builder.service.impl.MessagesServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static void markAsReadNotification(
		long userId, long userEventNotificationId) {

		getService().markAsReadNotification(userId, userEventNotificationId);
	}

	public static void sendNotification02(
		long companyId, long groupId, long userId, String cpReference,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		getService().sendNotification02(
			companyId, groupId, userId, cpReference, serviceContext);
	}

	public static void sendNotification09(
		long userId, long groupId, long companyId, long classPK,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		getService().sendNotification09(
			userId, groupId, companyId, classPK, serviceContext);
	}

	public static void sendNotification15(
		long companyId, long groupId, String cPReference,
		String commentsDeadline, long resourcePrimKey, String email) {

		getService().sendNotification15(
			companyId, groupId, cPReference, commentsDeadline, resourcePrimKey,
			email);
	}

	public static void sendNotification18(
		long companyId, long groupId, long userId, long resourcePrimaryKey,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		getService().sendNotification18(
			companyId, groupId, userId, resourcePrimaryKey, serviceContext);
	}

	public static void sendNotification29(
		long userId, long resourcePrimaryKey, long siteGroupId) {

		getService().sendNotification29(
			userId, resourcePrimaryKey, siteGroupId);
	}

	public static void sendNotification30(
		long userId, long resourcePrimaryKey, long siteGroupId) {

		getService().sendNotification30(
			userId, resourcePrimaryKey, siteGroupId);
	}

	public static void sendNotification31(
		long companyId, long groupId, long userId, String roleNames,
		String VotingLink, String titleOfNotification,
		String bodyOfNotification) {

		getService().sendNotification31(
			companyId, groupId, userId, roleNames, VotingLink,
			titleOfNotification, bodyOfNotification);
	}

	public static void sendNotification46(
		String notificationTitle, String notificationBody, String segment,
		long currentUserId, String webEnabled, String emailEnabled) {

		getService().sendNotification46(
			notificationTitle, notificationBody, segment, currentUserId,
			webEnabled, emailEnabled);
	}

	public static void sendNotification75(
		long currentUserId, long siteGroupId, String remedationTitle,
		String urlEmail, long companyId) {

		getService().sendNotification75(
			currentUserId, siteGroupId, remedationTitle, urlEmail, companyId);
	}

	public static MessagesService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<MessagesService, MessagesService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(MessagesService.class);

		ServiceTracker<MessagesService, MessagesService> serviceTracker =
			new ServiceTracker<MessagesService, MessagesService>(
				bundle.getBundleContext(), MessagesService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}