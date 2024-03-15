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

package rec.confidential.message.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link Confidential_MessagesService}.
 *
 * @author Brian Wing Shun Chan
 * @see Confidential_MessagesService
 * @generated
 */
public class Confidential_MessagesServiceWrapper
	implements Confidential_MessagesService,
			   ServiceWrapper<Confidential_MessagesService> {

	public Confidential_MessagesServiceWrapper(
		Confidential_MessagesService confidential_MessagesService) {

		_confidential_MessagesService = confidential_MessagesService;
	}

	@Override
	public void addConfidentialMessage(long messageId, long companyId) {
		_confidential_MessagesService.addConfidentialMessage(
			messageId, companyId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _confidential_MessagesService.getOSGiServiceIdentifier();
	}

	@Override
	public Confidential_MessagesService getWrappedService() {
		return _confidential_MessagesService;
	}

	@Override
	public void setWrappedService(
		Confidential_MessagesService confidential_MessagesService) {

		_confidential_MessagesService = confidential_MessagesService;
	}

	private Confidential_MessagesService _confidential_MessagesService;

}