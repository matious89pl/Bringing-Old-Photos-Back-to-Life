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

package com.everis.rec.sites.service.persistence;

import com.everis.rec.sites.exception.NoSuchRecsitesException;
import com.everis.rec.sites.model.Recsites;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the recsites service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RecsitesUtil
 * @generated
 */
@ProviderType
public interface RecsitesPersistence extends BasePersistence<Recsites> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link RecsitesUtil} to access the recsites persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the recsiteses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching recsiteses
	 */
	public java.util.List<Recsites> findByUuid(String uuid);

	/**
	 * Returns a range of all the recsiteses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RecsitesModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of recsiteses
	 * @param end the upper bound of the range of recsiteses (not inclusive)
	 * @return the range of matching recsiteses
	 */
	public java.util.List<Recsites> findByUuid(String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the recsiteses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RecsitesModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of recsiteses
	 * @param end the upper bound of the range of recsiteses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching recsiteses
	 */
	public java.util.List<Recsites> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Recsites>
			orderByComparator);

	/**
	 * Returns an ordered range of all the recsiteses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RecsitesModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of recsiteses
	 * @param end the upper bound of the range of recsiteses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching recsiteses
	 */
	public java.util.List<Recsites> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Recsites>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first recsites in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching recsites
	 * @throws NoSuchRecsitesException if a matching recsites could not be found
	 */
	public Recsites findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Recsites>
				orderByComparator)
		throws NoSuchRecsitesException;

	/**
	 * Returns the first recsites in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching recsites, or <code>null</code> if a matching recsites could not be found
	 */
	public Recsites fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Recsites>
			orderByComparator);

	/**
	 * Returns the last recsites in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching recsites
	 * @throws NoSuchRecsitesException if a matching recsites could not be found
	 */
	public Recsites findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Recsites>
				orderByComparator)
		throws NoSuchRecsitesException;

	/**
	 * Returns the last recsites in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching recsites, or <code>null</code> if a matching recsites could not be found
	 */
	public Recsites fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Recsites>
			orderByComparator);

	/**
	 * Returns the recsiteses before and after the current recsites in the ordered set where uuid = &#63;.
	 *
	 * @param fooId the primary key of the current recsites
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next recsites
	 * @throws NoSuchRecsitesException if a recsites with the primary key could not be found
	 */
	public Recsites[] findByUuid_PrevAndNext(
			long fooId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Recsites>
				orderByComparator)
		throws NoSuchRecsitesException;

	/**
	 * Removes all the recsiteses where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of recsiteses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching recsiteses
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the recsites where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchRecsitesException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching recsites
	 * @throws NoSuchRecsitesException if a matching recsites could not be found
	 */
	public Recsites findByUUID_G(String uuid, long groupId)
		throws NoSuchRecsitesException;

	/**
	 * Returns the recsites where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching recsites, or <code>null</code> if a matching recsites could not be found
	 */
	public Recsites fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the recsites where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching recsites, or <code>null</code> if a matching recsites could not be found
	 */
	public Recsites fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache);

	/**
	 * Removes the recsites where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the recsites that was removed
	 */
	public Recsites removeByUUID_G(String uuid, long groupId)
		throws NoSuchRecsitesException;

	/**
	 * Returns the number of recsiteses where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching recsiteses
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the recsiteses where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching recsiteses
	 */
	public java.util.List<Recsites> findByUuid_C(String uuid, long companyId);

	/**
	 * Returns a range of all the recsiteses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RecsitesModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of recsiteses
	 * @param end the upper bound of the range of recsiteses (not inclusive)
	 * @return the range of matching recsiteses
	 */
	public java.util.List<Recsites> findByUuid_C(
		String uuid, long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the recsiteses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RecsitesModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of recsiteses
	 * @param end the upper bound of the range of recsiteses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching recsiteses
	 */
	public java.util.List<Recsites> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Recsites>
			orderByComparator);

	/**
	 * Returns an ordered range of all the recsiteses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RecsitesModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of recsiteses
	 * @param end the upper bound of the range of recsiteses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching recsiteses
	 */
	public java.util.List<Recsites> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Recsites>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first recsites in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching recsites
	 * @throws NoSuchRecsitesException if a matching recsites could not be found
	 */
	public Recsites findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Recsites>
				orderByComparator)
		throws NoSuchRecsitesException;

	/**
	 * Returns the first recsites in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching recsites, or <code>null</code> if a matching recsites could not be found
	 */
	public Recsites fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Recsites>
			orderByComparator);

	/**
	 * Returns the last recsites in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching recsites
	 * @throws NoSuchRecsitesException if a matching recsites could not be found
	 */
	public Recsites findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Recsites>
				orderByComparator)
		throws NoSuchRecsitesException;

	/**
	 * Returns the last recsites in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching recsites, or <code>null</code> if a matching recsites could not be found
	 */
	public Recsites fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Recsites>
			orderByComparator);

	/**
	 * Returns the recsiteses before and after the current recsites in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param fooId the primary key of the current recsites
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next recsites
	 * @throws NoSuchRecsitesException if a recsites with the primary key could not be found
	 */
	public Recsites[] findByUuid_C_PrevAndNext(
			long fooId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Recsites>
				orderByComparator)
		throws NoSuchRecsitesException;

	/**
	 * Removes all the recsiteses where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of recsiteses where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching recsiteses
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns all the recsiteses where field2 = &#63;.
	 *
	 * @param field2 the field2
	 * @return the matching recsiteses
	 */
	public java.util.List<Recsites> findByField2(boolean field2);

	/**
	 * Returns a range of all the recsiteses where field2 = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RecsitesModelImpl</code>.
	 * </p>
	 *
	 * @param field2 the field2
	 * @param start the lower bound of the range of recsiteses
	 * @param end the upper bound of the range of recsiteses (not inclusive)
	 * @return the range of matching recsiteses
	 */
	public java.util.List<Recsites> findByField2(
		boolean field2, int start, int end);

	/**
	 * Returns an ordered range of all the recsiteses where field2 = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RecsitesModelImpl</code>.
	 * </p>
	 *
	 * @param field2 the field2
	 * @param start the lower bound of the range of recsiteses
	 * @param end the upper bound of the range of recsiteses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching recsiteses
	 */
	public java.util.List<Recsites> findByField2(
		boolean field2, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Recsites>
			orderByComparator);

	/**
	 * Returns an ordered range of all the recsiteses where field2 = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RecsitesModelImpl</code>.
	 * </p>
	 *
	 * @param field2 the field2
	 * @param start the lower bound of the range of recsiteses
	 * @param end the upper bound of the range of recsiteses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching recsiteses
	 */
	public java.util.List<Recsites> findByField2(
		boolean field2, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Recsites>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first recsites in the ordered set where field2 = &#63;.
	 *
	 * @param field2 the field2
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching recsites
	 * @throws NoSuchRecsitesException if a matching recsites could not be found
	 */
	public Recsites findByField2_First(
			boolean field2,
			com.liferay.portal.kernel.util.OrderByComparator<Recsites>
				orderByComparator)
		throws NoSuchRecsitesException;

	/**
	 * Returns the first recsites in the ordered set where field2 = &#63;.
	 *
	 * @param field2 the field2
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching recsites, or <code>null</code> if a matching recsites could not be found
	 */
	public Recsites fetchByField2_First(
		boolean field2,
		com.liferay.portal.kernel.util.OrderByComparator<Recsites>
			orderByComparator);

	/**
	 * Returns the last recsites in the ordered set where field2 = &#63;.
	 *
	 * @param field2 the field2
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching recsites
	 * @throws NoSuchRecsitesException if a matching recsites could not be found
	 */
	public Recsites findByField2_Last(
			boolean field2,
			com.liferay.portal.kernel.util.OrderByComparator<Recsites>
				orderByComparator)
		throws NoSuchRecsitesException;

	/**
	 * Returns the last recsites in the ordered set where field2 = &#63;.
	 *
	 * @param field2 the field2
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching recsites, or <code>null</code> if a matching recsites could not be found
	 */
	public Recsites fetchByField2_Last(
		boolean field2,
		com.liferay.portal.kernel.util.OrderByComparator<Recsites>
			orderByComparator);

	/**
	 * Returns the recsiteses before and after the current recsites in the ordered set where field2 = &#63;.
	 *
	 * @param fooId the primary key of the current recsites
	 * @param field2 the field2
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next recsites
	 * @throws NoSuchRecsitesException if a recsites with the primary key could not be found
	 */
	public Recsites[] findByField2_PrevAndNext(
			long fooId, boolean field2,
			com.liferay.portal.kernel.util.OrderByComparator<Recsites>
				orderByComparator)
		throws NoSuchRecsitesException;

	/**
	 * Removes all the recsiteses where field2 = &#63; from the database.
	 *
	 * @param field2 the field2
	 */
	public void removeByField2(boolean field2);

	/**
	 * Returns the number of recsiteses where field2 = &#63;.
	 *
	 * @param field2 the field2
	 * @return the number of matching recsiteses
	 */
	public int countByField2(boolean field2);

	/**
	 * Caches the recsites in the entity cache if it is enabled.
	 *
	 * @param recsites the recsites
	 */
	public void cacheResult(Recsites recsites);

	/**
	 * Caches the recsiteses in the entity cache if it is enabled.
	 *
	 * @param recsiteses the recsiteses
	 */
	public void cacheResult(java.util.List<Recsites> recsiteses);

	/**
	 * Creates a new recsites with the primary key. Does not add the recsites to the database.
	 *
	 * @param fooId the primary key for the new recsites
	 * @return the new recsites
	 */
	public Recsites create(long fooId);

	/**
	 * Removes the recsites with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param fooId the primary key of the recsites
	 * @return the recsites that was removed
	 * @throws NoSuchRecsitesException if a recsites with the primary key could not be found
	 */
	public Recsites remove(long fooId) throws NoSuchRecsitesException;

	public Recsites updateImpl(Recsites recsites);

	/**
	 * Returns the recsites with the primary key or throws a <code>NoSuchRecsitesException</code> if it could not be found.
	 *
	 * @param fooId the primary key of the recsites
	 * @return the recsites
	 * @throws NoSuchRecsitesException if a recsites with the primary key could not be found
	 */
	public Recsites findByPrimaryKey(long fooId) throws NoSuchRecsitesException;

	/**
	 * Returns the recsites with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param fooId the primary key of the recsites
	 * @return the recsites, or <code>null</code> if a recsites with the primary key could not be found
	 */
	public Recsites fetchByPrimaryKey(long fooId);

	/**
	 * Returns all the recsiteses.
	 *
	 * @return the recsiteses
	 */
	public java.util.List<Recsites> findAll();

	/**
	 * Returns a range of all the recsiteses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RecsitesModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of recsiteses
	 * @param end the upper bound of the range of recsiteses (not inclusive)
	 * @return the range of recsiteses
	 */
	public java.util.List<Recsites> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the recsiteses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RecsitesModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of recsiteses
	 * @param end the upper bound of the range of recsiteses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of recsiteses
	 */
	public java.util.List<Recsites> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Recsites>
			orderByComparator);

	/**
	 * Returns an ordered range of all the recsiteses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RecsitesModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of recsiteses
	 * @param end the upper bound of the range of recsiteses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of recsiteses
	 */
	public java.util.List<Recsites> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Recsites>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the recsiteses from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of recsiteses.
	 *
	 * @return the number of recsiteses
	 */
	public int countAll();

}