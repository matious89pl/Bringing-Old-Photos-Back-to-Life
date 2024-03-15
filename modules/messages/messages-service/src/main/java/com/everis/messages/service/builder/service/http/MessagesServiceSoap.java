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

package com.everis.messages.service.builder.service.http;

import com.everis.messages.service.builder.service.MessagesServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>MessagesServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>com.everis.messages.service.builder.model.MessagesSoap</code>. If the method in the
 * service utility returns a
 * <code>com.everis.messages.service.builder.model.Messages</code>, that is translated to a
 * <code>com.everis.messages.service.builder.model.MessagesSoap</code>. Methods that SOAP
 * cannot safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagesServiceHttp
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class MessagesServiceSoap {

	public static void sendNotification02(
			long companyId, long groupId, long userId, String cpReference,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			MessagesServiceUtil.sendNotification02(
				companyId, groupId, userId, cpReference, serviceContext);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static void sendNotification09(
			long userId, long groupId, long companyId, long classPK,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			MessagesServiceUtil.sendNotification09(
				userId, groupId, companyId, classPK, serviceContext);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static void sendNotification15(
			long companyId, long groupId, String cPReference,
			String commentsDeadline, long resourcePrimKey, String email)
		throws RemoteException {

		try {
			MessagesServiceUtil.sendNotification15(
				companyId, groupId, cPReference, commentsDeadline,
				resourcePrimKey, email);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static void sendNotification18(
			long companyId, long groupId, long userId, long resourcePrimaryKey,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			MessagesServiceUtil.sendNotification18(
				companyId, groupId, userId, resourcePrimaryKey, serviceContext);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static void sendNotification29(
			long userId, long resourcePrimaryKey, long siteGroupId)
		throws RemoteException {

		try {
			MessagesServiceUtil.sendNotification29(
				userId, resourcePrimaryKey, siteGroupId);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static void sendNotification30(
			long userId, long resourcePrimaryKey, long siteGroupId)
		throws RemoteException {

		try {
			MessagesServiceUtil.sendNotification30(
				userId, resourcePrimaryKey, siteGroupId);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static void sendNotification31(
			long companyId, long groupId, long userId, String roleNames,
			String VotingLink, String titleOfNotification,
			String bodyOfNotification)
		throws RemoteException {

		try {
			MessagesServiceUtil.sendNotification31(
				companyId, groupId, userId, roleNames, VotingLink,
				titleOfNotification, bodyOfNotification);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static void sendNotification46(
			String notificationTitle, String notificationBody, String segment,
			long currentUserId, String webEnabled, String emailEnabled)
		throws RemoteException {

		try {
			MessagesServiceUtil.sendNotification46(
				notificationTitle, notificationBody, segment, currentUserId,
				webEnabled, emailEnabled);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static void sendNotification75(
			long currentUserId, long siteGroupId, String remedationTitle,
			String urlEmail, long companyId)
		throws RemoteException {

		try {
			MessagesServiceUtil.sendNotification75(
				currentUserId, siteGroupId, remedationTitle, urlEmail,
				companyId);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static void markAsReadNotification(
			long userId, long userEventNotificationId)
		throws RemoteException {

		try {
			MessagesServiceUtil.markAsReadNotification(
				userId, userEventNotificationId);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(MessagesServiceSoap.class);

}