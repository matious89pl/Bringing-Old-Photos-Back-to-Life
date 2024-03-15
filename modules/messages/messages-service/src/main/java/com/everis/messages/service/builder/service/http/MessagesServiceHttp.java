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
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * <code>MessagesServiceUtil</code> service
 * utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * <code>HttpPrincipal</code> parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagesServiceSoap
 * @generated
 */
public class MessagesServiceHttp {

	public static void sendNotification02(
		HttpPrincipal httpPrincipal, long companyId, long groupId, long userId,
		String cpReference,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		try {
			MethodKey methodKey = new MethodKey(
				MessagesServiceUtil.class, "sendNotification02",
				_sendNotification02ParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, groupId, userId, cpReference,
				serviceContext);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void sendNotification09(
		HttpPrincipal httpPrincipal, long userId, long groupId, long companyId,
		long classPK,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		try {
			MethodKey methodKey = new MethodKey(
				MessagesServiceUtil.class, "sendNotification09",
				_sendNotification09ParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userId, groupId, companyId, classPK, serviceContext);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void sendNotification15(
		HttpPrincipal httpPrincipal, long companyId, long groupId,
		String cPReference, String commentsDeadline, long resourcePrimKey,
		String email) {

		try {
			MethodKey methodKey = new MethodKey(
				MessagesServiceUtil.class, "sendNotification15",
				_sendNotification15ParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, groupId, cPReference, commentsDeadline,
				resourcePrimKey, email);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void sendNotification18(
		HttpPrincipal httpPrincipal, long companyId, long groupId, long userId,
		long resourcePrimaryKey,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		try {
			MethodKey methodKey = new MethodKey(
				MessagesServiceUtil.class, "sendNotification18",
				_sendNotification18ParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, groupId, userId, resourcePrimaryKey,
				serviceContext);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void sendNotification29(
		HttpPrincipal httpPrincipal, long userId, long resourcePrimaryKey,
		long siteGroupId) {

		try {
			MethodKey methodKey = new MethodKey(
				MessagesServiceUtil.class, "sendNotification29",
				_sendNotification29ParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userId, resourcePrimaryKey, siteGroupId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void sendNotification30(
		HttpPrincipal httpPrincipal, long userId, long resourcePrimaryKey,
		long siteGroupId) {

		try {
			MethodKey methodKey = new MethodKey(
				MessagesServiceUtil.class, "sendNotification30",
				_sendNotification30ParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userId, resourcePrimaryKey, siteGroupId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void sendNotification31(
		HttpPrincipal httpPrincipal, long companyId, long groupId, long userId,
		String roleNames, String VotingLink, String titleOfNotification,
		String bodyOfNotification) {

		try {
			MethodKey methodKey = new MethodKey(
				MessagesServiceUtil.class, "sendNotification31",
				_sendNotification31ParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, groupId, userId, roleNames, VotingLink,
				titleOfNotification, bodyOfNotification);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void sendNotification46(
		HttpPrincipal httpPrincipal, String notificationTitle,
		String notificationBody, String segment, long currentUserId,
		String webEnabled, String emailEnabled) {

		try {
			MethodKey methodKey = new MethodKey(
				MessagesServiceUtil.class, "sendNotification46",
				_sendNotification46ParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, notificationTitle, notificationBody, segment,
				currentUserId, webEnabled, emailEnabled);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void sendNotification75(
		HttpPrincipal httpPrincipal, long currentUserId, long siteGroupId,
		String remedationTitle, String urlEmail, long companyId) {

		try {
			MethodKey methodKey = new MethodKey(
				MessagesServiceUtil.class, "sendNotification75",
				_sendNotification75ParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, currentUserId, siteGroupId, remedationTitle,
				urlEmail, companyId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void markAsReadNotification(
		HttpPrincipal httpPrincipal, long userId,
		long userEventNotificationId) {

		try {
			MethodKey methodKey = new MethodKey(
				MessagesServiceUtil.class, "markAsReadNotification",
				_markAsReadNotificationParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userId, userEventNotificationId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(MessagesServiceHttp.class);

	private static final Class<?>[] _sendNotification02ParameterTypes0 =
		new Class[] {
			long.class, long.class, long.class, String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _sendNotification09ParameterTypes1 =
		new Class[] {
			long.class, long.class, long.class, long.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _sendNotification15ParameterTypes2 =
		new Class[] {
			long.class, long.class, String.class, String.class, long.class,
			String.class
		};
	private static final Class<?>[] _sendNotification18ParameterTypes3 =
		new Class[] {
			long.class, long.class, long.class, long.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _sendNotification29ParameterTypes4 =
		new Class[] {long.class, long.class, long.class};
	private static final Class<?>[] _sendNotification30ParameterTypes5 =
		new Class[] {long.class, long.class, long.class};
	private static final Class<?>[] _sendNotification31ParameterTypes6 =
		new Class[] {
			long.class, long.class, long.class, String.class, String.class,
			String.class, String.class
		};
	private static final Class<?>[] _sendNotification46ParameterTypes7 =
		new Class[] {
			String.class, String.class, String.class, long.class, String.class,
			String.class
		};
	private static final Class<?>[] _sendNotification75ParameterTypes8 =
		new Class[] {
			long.class, long.class, String.class, String.class, long.class
		};
	private static final Class<?>[] _markAsReadNotificationParameterTypes9 =
		new Class[] {long.class, long.class};

}