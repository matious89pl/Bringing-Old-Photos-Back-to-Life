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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link RecsitesService}.
 *
 * @author Brian Wing Shun Chan
 * @see RecsitesService
 * @generated
 */
public class RecsitesServiceWrapper
	implements RecsitesService, ServiceWrapper<RecsitesService> {

	public RecsitesServiceWrapper(RecsitesService recsitesService) {
		_recsitesService = recsitesService;
	}

	@Override
	public void closeVoteByQuestionId(long questionId, long userId) {
		_recsitesService.closeVoteByQuestionId(questionId, userId);
	}

	@Override
	public void deletePollVote(long questionId, long userId) {
		_recsitesService.deletePollVote(questionId, userId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _recsitesService.getOSGiServiceIdentifier();
	}

	@Override
	public RecsitesService getWrappedService() {
		return _recsitesService;
	}

	@Override
	public void setWrappedService(RecsitesService recsitesService) {
		_recsitesService = recsitesService;
	}

	private RecsitesService _recsitesService;

}