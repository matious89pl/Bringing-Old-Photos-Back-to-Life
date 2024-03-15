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

package com.everis.rec.sites.service.http;

import com.everis.rec.sites.service.RecsitesServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * <code>RecsitesServiceUtil</code> service
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
 * @see RecsitesServiceSoap
 * @generated
 */
public class RecsitesServiceHttp {

	public static void deletePollVote(
		HttpPrincipal httpPrincipal, long questionId, long userId) {

		try {
			MethodKey methodKey = new MethodKey(
				RecsitesServiceUtil.class, "deletePollVote",
				_deletePollVoteParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, questionId, userId);

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

	public static void closeVoteByQuestionId(
		HttpPrincipal httpPrincipal, long questionId, long userId) {

		try {
			MethodKey methodKey = new MethodKey(
				RecsitesServiceUtil.class, "closeVoteByQuestionId",
				_closeVoteByQuestionIdParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, questionId, userId);

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

	private static Log _log = LogFactoryUtil.getLog(RecsitesServiceHttp.class);

	private static final Class<?>[] _deletePollVoteParameterTypes0 =
		new Class[] {long.class, long.class};
	private static final Class<?>[] _closeVoteByQuestionIdParameterTypes1 =
		new Class[] {long.class, long.class};

}