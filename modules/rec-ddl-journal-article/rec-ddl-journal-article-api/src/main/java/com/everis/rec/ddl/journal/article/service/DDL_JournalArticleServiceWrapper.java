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

package com.everis.rec.ddl.journal.article.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DDL_JournalArticleService}.
 *
 * @author Brian Wing Shun Chan
 * @see DDL_JournalArticleService
 * @generated
 */
public class DDL_JournalArticleServiceWrapper
	implements DDL_JournalArticleService,
			   ServiceWrapper<DDL_JournalArticleService> {

	public DDL_JournalArticleServiceWrapper(
		DDL_JournalArticleService ddl_JournalArticleService) {

		_ddl_JournalArticleService = ddl_JournalArticleService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _ddl_JournalArticleService.getOSGiServiceIdentifier();
	}

	@Override
	public void removePermissionsGuest(long userId) {
		_ddl_JournalArticleService.removePermissionsGuest(userId);
	}

	@Override
	public void removePermissionsOwner(long userId) {
		_ddl_JournalArticleService.removePermissionsOwner(userId);
	}

	@Override
	public void removePermissionsSiteMembers(long userId) {
		_ddl_JournalArticleService.removePermissionsSiteMembers(userId);
	}

	@Override
	public DDL_JournalArticleService getWrappedService() {
		return _ddl_JournalArticleService;
	}

	@Override
	public void setWrappedService(
		DDL_JournalArticleService ddl_JournalArticleService) {

		_ddl_JournalArticleService = ddl_JournalArticleService;
	}

	private DDL_JournalArticleService _ddl_JournalArticleService;

}