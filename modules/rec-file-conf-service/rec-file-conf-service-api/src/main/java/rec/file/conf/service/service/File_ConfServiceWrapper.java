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

package rec.file.conf.service.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link File_ConfService}.
 *
 * @author Brian Wing Shun Chan
 * @see File_ConfService
 * @generated
 */
public class File_ConfServiceWrapper
	implements File_ConfService, ServiceWrapper<File_ConfService> {

	public File_ConfServiceWrapper(File_ConfService file_ConfService) {
		_file_ConfService = file_ConfService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _file_ConfService.getOSGiServiceIdentifier();
	}

	@Override
	public File_ConfService getWrappedService() {
		return _file_ConfService;
	}

	@Override
	public void setWrappedService(File_ConfService file_ConfService) {
		_file_ConfService = file_ConfService;
	}

	private File_ConfService _file_ConfService;

}