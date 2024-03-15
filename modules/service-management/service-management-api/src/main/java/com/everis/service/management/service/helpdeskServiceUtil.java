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

package com.everis.service.management.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for helpdesk. This utility wraps
 * <code>com.everis.service.management.service.impl.helpdeskServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see helpdeskService
 * @generated
 */
public class helpdeskServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.everis.service.management.service.impl.helpdeskServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.json.JSONObject createCase(
		String environment, String title, String description, String firstName,
		String lastName, String contactEmail, String type,
		String classification, String subserviceName) {

		return getService().createCase(
			environment, title, description, firstName, lastName, contactEmail,
			type, classification, subserviceName);
	}

	public static com.liferay.portal.kernel.json.JSONObject getCasesByUserEmail(
		String environment, long userId, String filters) {

		return getService().getCasesByUserEmail(environment, userId, filters);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.json.JSONObject getSubServices(
		String environment) {

		return getService().getSubServices(environment);
	}

	public static helpdeskService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<helpdeskService, helpdeskService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(helpdeskService.class);

		ServiceTracker<helpdeskService, helpdeskService> serviceTracker =
			new ServiceTracker<helpdeskService, helpdeskService>(
				bundle.getBundleContext(), helpdeskService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}