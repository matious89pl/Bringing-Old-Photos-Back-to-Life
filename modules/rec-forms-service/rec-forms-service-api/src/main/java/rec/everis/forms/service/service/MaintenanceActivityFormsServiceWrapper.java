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

package rec.everis.forms.service.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MaintenanceActivityFormsService}.
 *
 * @author Brian Wing Shun Chan
 * @see MaintenanceActivityFormsService
 * @generated
 */
public class MaintenanceActivityFormsServiceWrapper
	implements MaintenanceActivityFormsService,
			   ServiceWrapper<MaintenanceActivityFormsService> {

	public MaintenanceActivityFormsServiceWrapper(
		MaintenanceActivityFormsService maintenanceActivityFormsService) {

		_maintenanceActivityFormsService = maintenanceActivityFormsService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _maintenanceActivityFormsService.getOSGiServiceIdentifier();
	}

	@Override
	public MaintenanceActivityFormsService getWrappedService() {
		return _maintenanceActivityFormsService;
	}

	@Override
	public void setWrappedService(
		MaintenanceActivityFormsService maintenanceActivityFormsService) {

		_maintenanceActivityFormsService = maintenanceActivityFormsService;
	}

	private MaintenanceActivityFormsService _maintenanceActivityFormsService;

}