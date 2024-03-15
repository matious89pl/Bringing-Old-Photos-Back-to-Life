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

package com.everis.rec.sites.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for Recsites. This utility wraps
 * <code>com.everis.rec.sites.service.impl.RecsitesServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see RecsitesService
 * @generated
 */
public class RecsitesServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.everis.rec.sites.service.impl.RecsitesServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static void closeVoteByQuestionId(long questionId, long userId) {
		getService().closeVoteByQuestionId(questionId, userId);
	}

	public static void deletePollVote(long questionId, long userId) {
		getService().deletePollVote(questionId, userId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static RecsitesService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<RecsitesService, RecsitesService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(RecsitesService.class);

		ServiceTracker<RecsitesService, RecsitesService> serviceTracker =
			new ServiceTracker<RecsitesService, RecsitesService>(
				bundle.getBundleContext(), RecsitesService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}