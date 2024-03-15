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

package com.everis.service.management.service.persistence;

import com.everis.service.management.exception.NoSuchhelpdeskException;
import com.everis.service.management.model.helpdesk;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the helpdesk service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see helpdeskUtil
 * @generated
 */
@ProviderType
public interface helpdeskPersistence extends BasePersistence<helpdesk> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link helpdeskUtil} to access the helpdesk persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the helpdesk in the entity cache if it is enabled.
	 *
	 * @param helpdesk the helpdesk
	 */
	public void cacheResult(helpdesk helpdesk);

	/**
	 * Caches the helpdesks in the entity cache if it is enabled.
	 *
	 * @param helpdesks the helpdesks
	 */
	public void cacheResult(java.util.List<helpdesk> helpdesks);

	/**
	 * Creates a new helpdesk with the primary key. Does not add the helpdesk to the database.
	 *
	 * @param helpdeskId the primary key for the new helpdesk
	 * @return the new helpdesk
	 */
	public helpdesk create(long helpdeskId);

	/**
	 * Removes the helpdesk with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param helpdeskId the primary key of the helpdesk
	 * @return the helpdesk that was removed
	 * @throws NoSuchhelpdeskException if a helpdesk with the primary key could not be found
	 */
	public helpdesk remove(long helpdeskId) throws NoSuchhelpdeskException;

	public helpdesk updateImpl(helpdesk helpdesk);

	/**
	 * Returns the helpdesk with the primary key or throws a <code>NoSuchhelpdeskException</code> if it could not be found.
	 *
	 * @param helpdeskId the primary key of the helpdesk
	 * @return the helpdesk
	 * @throws NoSuchhelpdeskException if a helpdesk with the primary key could not be found
	 */
	public helpdesk findByPrimaryKey(long helpdeskId)
		throws NoSuchhelpdeskException;

	/**
	 * Returns the helpdesk with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param helpdeskId the primary key of the helpdesk
	 * @return the helpdesk, or <code>null</code> if a helpdesk with the primary key could not be found
	 */
	public helpdesk fetchByPrimaryKey(long helpdeskId);

	/**
	 * Returns all the helpdesks.
	 *
	 * @return the helpdesks
	 */
	public java.util.List<helpdesk> findAll();

	/**
	 * Returns a range of all the helpdesks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>helpdeskModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of helpdesks
	 * @param end the upper bound of the range of helpdesks (not inclusive)
	 * @return the range of helpdesks
	 */
	public java.util.List<helpdesk> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the helpdesks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>helpdeskModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of helpdesks
	 * @param end the upper bound of the range of helpdesks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of helpdesks
	 */
	public java.util.List<helpdesk> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<helpdesk>
			orderByComparator);

	/**
	 * Returns an ordered range of all the helpdesks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>helpdeskModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of helpdesks
	 * @param end the upper bound of the range of helpdesks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of helpdesks
	 */
	public java.util.List<helpdesk> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<helpdesk>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the helpdesks from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of helpdesks.
	 *
	 * @return the number of helpdesks
	 */
	public int countAll();

}