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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link helpdeskService}.
 *
 * @author Brian Wing Shun Chan
 * @see helpdeskService
 * @generated
 */
public class helpdeskServiceWrapper
	implements helpdeskService, ServiceWrapper<helpdeskService> {

	public helpdeskServiceWrapper(helpdeskService helpdeskService) {
		_helpdeskService = helpdeskService;
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject createCase(
		String environment, String title, String description, String firstName,
		String lastName, String contactEmail, String type,
		String classification, String subserviceName) {

		return _helpdeskService.createCase(
			environment, title, description, firstName, lastName, contactEmail,
			type, classification, subserviceName);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getCasesByUserEmail(
		String environment, long userId, String filters) {

		return _helpdeskService.getCasesByUserEmail(
			environment, userId, filters);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _helpdeskService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getSubServices(
		String environment) {

		return _helpdeskService.getSubServices(environment);
	}

	@Override
	public helpdeskService getWrappedService() {
		return _helpdeskService;
	}

	@Override
	public void setWrappedService(helpdeskService helpdeskService) {
		_helpdeskService = helpdeskService;
	}

	private helpdeskService _helpdeskService;

}