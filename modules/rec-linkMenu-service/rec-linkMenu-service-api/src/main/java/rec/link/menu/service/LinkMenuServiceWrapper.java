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

package rec.link.menu.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link LinkMenuService}.
 *
 * @author Brian Wing Shun Chan
 * @see LinkMenuService
 * @generated
 */
public class LinkMenuServiceWrapper
	implements LinkMenuService, ServiceWrapper<LinkMenuService> {

	public LinkMenuServiceWrapper(LinkMenuService linkMenuService) {
		_linkMenuService = linkMenuService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _linkMenuService.getOSGiServiceIdentifier();
	}

	@Override
	public LinkMenuService getWrappedService() {
		return _linkMenuService;
	}

	@Override
	public void setWrappedService(LinkMenuService linkMenuService) {
		_linkMenuService = linkMenuService;
	}

	private LinkMenuService _linkMenuService;

}