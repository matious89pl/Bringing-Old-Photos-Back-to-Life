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

package com.everis.rec.maintenanceactivity.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MaintenanceActivityService}.
 *
 * @author Brian Wing Shun Chan
 * @see MaintenanceActivityService
 * @generated
 */
public class MaintenanceActivityServiceWrapper
	implements MaintenanceActivityService,
			   ServiceWrapper<MaintenanceActivityService> {

	public MaintenanceActivityServiceWrapper(
		MaintenanceActivityService maintenanceActivityService) {

		_maintenanceActivityService = maintenanceActivityService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _maintenanceActivityService.getOSGiServiceIdentifier();
	}

	@Override
	public MaintenanceActivityService getWrappedService() {
		return _maintenanceActivityService;
	}

	@Override
	public void setWrappedService(
		MaintenanceActivityService maintenanceActivityService) {

		_maintenanceActivityService = maintenanceActivityService;
	}

	private MaintenanceActivityService _maintenanceActivityService;

}