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

package com.everis.rec.rfilogs.service.persistence;

import com.everis.rec.rfilogs.exception.NoSuchRfiLogsException;
import com.everis.rec.rfilogs.model.RfiLogs;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the rfi logs service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RfiLogsUtil
 * @generated
 */
@ProviderType
public interface RfiLogsPersistence extends BasePersistence<RfiLogs> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link RfiLogsUtil} to access the rfi logs persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the rfi logses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching rfi logses
	 */
	public java.util.List<RfiLogs> findByUuid(String uuid);

	/**
	 * Returns a range of all the rfi logses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RfiLogsModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of rfi logses
	 * @param end the upper bound of the range of rfi logses (not inclusive)
	 * @return the range of matching rfi logses
	 */
	public java.util.List<RfiLogs> findByUuid(String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the rfi logses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RfiLogsModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of rfi logses
	 * @param end the upper bound of the range of rfi logses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rfi logses
	 */
	public java.util.List<RfiLogs> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RfiLogs>
			orderByComparator);

	/**
	 * Returns an ordered range of all the rfi logses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RfiLogsModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of rfi logses
	 * @param end the upper bound of the range of rfi logses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching rfi logses
	 */
	public java.util.List<RfiLogs> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RfiLogs>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first rfi logs in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching rfi logs
	 * @throws NoSuchRfiLogsException if a matching rfi logs could not be found
	 */
	public RfiLogs findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<RfiLogs>
				orderByComparator)
		throws NoSuchRfiLogsException;

	/**
	 * Returns the first rfi logs in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching rfi logs, or <code>null</code> if a matching rfi logs could not be found
	 */
	public RfiLogs fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<RfiLogs>
			orderByComparator);

	/**
	 * Returns the last rfi logs in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching rfi logs
	 * @throws NoSuchRfiLogsException if a matching rfi logs could not be found
	 */
	public RfiLogs findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<RfiLogs>
				orderByComparator)
		throws NoSuchRfiLogsException;

	/**
	 * Returns the last rfi logs in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching rfi logs, or <code>null</code> if a matching rfi logs could not be found
	 */
	public RfiLogs fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<RfiLogs>
			orderByComparator);

	/**
	 * Returns the rfi logses before and after the current rfi logs in the ordered set where uuid = &#63;.
	 *
	 * @param rfilogId the primary key of the current rfi logs
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next rfi logs
	 * @throws NoSuchRfiLogsException if a rfi logs with the primary key could not be found
	 */
	public RfiLogs[] findByUuid_PrevAndNext(
			long rfilogId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<RfiLogs>
				orderByComparator)
		throws NoSuchRfiLogsException;

	/**
	 * Removes all the rfi logses where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of rfi logses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching rfi logses
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns all the rfi logses where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching rfi logses
	 */
	public java.util.List<RfiLogs> findByUuid_C(String uuid, long companyId);

	/**
	 * Returns a range of all the rfi logses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RfiLogsModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of rfi logses
	 * @param end the upper bound of the range of rfi logses (not inclusive)
	 * @return the range of matching rfi logses
	 */
	public java.util.List<RfiLogs> findByUuid_C(
		String uuid, long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the rfi logses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RfiLogsModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of rfi logses
	 * @param end the upper bound of the range of rfi logses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rfi logses
	 */
	public java.util.List<RfiLogs> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RfiLogs>
			orderByComparator);

	/**
	 * Returns an ordered range of all the rfi logses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RfiLogsModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of rfi logses
	 * @param end the upper bound of the range of rfi logses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching rfi logses
	 */
	public java.util.List<RfiLogs> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RfiLogs>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first rfi logs in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching rfi logs
	 * @throws NoSuchRfiLogsException if a matching rfi logs could not be found
	 */
	public RfiLogs findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<RfiLogs>
				orderByComparator)
		throws NoSuchRfiLogsException;

	/**
	 * Returns the first rfi logs in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching rfi logs, or <code>null</code> if a matching rfi logs could not be found
	 */
	public RfiLogs fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<RfiLogs>
			orderByComparator);

	/**
	 * Returns the last rfi logs in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching rfi logs
	 * @throws NoSuchRfiLogsException if a matching rfi logs could not be found
	 */
	public RfiLogs findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<RfiLogs>
				orderByComparator)
		throws NoSuchRfiLogsException;

	/**
	 * Returns the last rfi logs in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching rfi logs, or <code>null</code> if a matching rfi logs could not be found
	 */
	public RfiLogs fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<RfiLogs>
			orderByComparator);

	/**
	 * Returns the rfi logses before and after the current rfi logs in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param rfilogId the primary key of the current rfi logs
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next rfi logs
	 * @throws NoSuchRfiLogsException if a rfi logs with the primary key could not be found
	 */
	public RfiLogs[] findByUuid_C_PrevAndNext(
			long rfilogId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<RfiLogs>
				orderByComparator)
		throws NoSuchRfiLogsException;

	/**
	 * Removes all the rfi logses where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of rfi logses where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching rfi logses
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns all the rfi logses where status = &#63;.
	 *
	 * @param status the status
	 * @return the matching rfi logses
	 */
	public java.util.List<RfiLogs> findByStatus(String status);

	/**
	 * Returns a range of all the rfi logses where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RfiLogsModelImpl</code>.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of rfi logses
	 * @param end the upper bound of the range of rfi logses (not inclusive)
	 * @return the range of matching rfi logses
	 */
	public java.util.List<RfiLogs> findByStatus(
		String status, int start, int end);

	/**
	 * Returns an ordered range of all the rfi logses where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RfiLogsModelImpl</code>.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of rfi logses
	 * @param end the upper bound of the range of rfi logses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rfi logses
	 */
	public java.util.List<RfiLogs> findByStatus(
		String status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RfiLogs>
			orderByComparator);

	/**
	 * Returns an ordered range of all the rfi logses where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RfiLogsModelImpl</code>.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of rfi logses
	 * @param end the upper bound of the range of rfi logses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching rfi logses
	 */
	public java.util.List<RfiLogs> findByStatus(
		String status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RfiLogs>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first rfi logs in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching rfi logs
	 * @throws NoSuchRfiLogsException if a matching rfi logs could not be found
	 */
	public RfiLogs findByStatus_First(
			String status,
			com.liferay.portal.kernel.util.OrderByComparator<RfiLogs>
				orderByComparator)
		throws NoSuchRfiLogsException;

	/**
	 * Returns the first rfi logs in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching rfi logs, or <code>null</code> if a matching rfi logs could not be found
	 */
	public RfiLogs fetchByStatus_First(
		String status,
		com.liferay.portal.kernel.util.OrderByComparator<RfiLogs>
			orderByComparator);

	/**
	 * Returns the last rfi logs in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching rfi logs
	 * @throws NoSuchRfiLogsException if a matching rfi logs could not be found
	 */
	public RfiLogs findByStatus_Last(
			String status,
			com.liferay.portal.kernel.util.OrderByComparator<RfiLogs>
				orderByComparator)
		throws NoSuchRfiLogsException;

	/**
	 * Returns the last rfi logs in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching rfi logs, or <code>null</code> if a matching rfi logs could not be found
	 */
	public RfiLogs fetchByStatus_Last(
		String status,
		com.liferay.portal.kernel.util.OrderByComparator<RfiLogs>
			orderByComparator);

	/**
	 * Returns the rfi logses before and after the current rfi logs in the ordered set where status = &#63;.
	 *
	 * @param rfilogId the primary key of the current rfi logs
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next rfi logs
	 * @throws NoSuchRfiLogsException if a rfi logs with the primary key could not be found
	 */
	public RfiLogs[] findByStatus_PrevAndNext(
			long rfilogId, String status,
			com.liferay.portal.kernel.util.OrderByComparator<RfiLogs>
				orderByComparator)
		throws NoSuchRfiLogsException;

	/**
	 * Removes all the rfi logses where status = &#63; from the database.
	 *
	 * @param status the status
	 */
	public void removeByStatus(String status);

	/**
	 * Returns the number of rfi logses where status = &#63;.
	 *
	 * @param status the status
	 * @return the number of matching rfi logses
	 */
	public int countByStatus(String status);

	/**
	 * Caches the rfi logs in the entity cache if it is enabled.
	 *
	 * @param rfiLogs the rfi logs
	 */
	public void cacheResult(RfiLogs rfiLogs);

	/**
	 * Caches the rfi logses in the entity cache if it is enabled.
	 *
	 * @param rfiLogses the rfi logses
	 */
	public void cacheResult(java.util.List<RfiLogs> rfiLogses);

	/**
	 * Creates a new rfi logs with the primary key. Does not add the rfi logs to the database.
	 *
	 * @param rfilogId the primary key for the new rfi logs
	 * @return the new rfi logs
	 */
	public RfiLogs create(long rfilogId);

	/**
	 * Removes the rfi logs with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param rfilogId the primary key of the rfi logs
	 * @return the rfi logs that was removed
	 * @throws NoSuchRfiLogsException if a rfi logs with the primary key could not be found
	 */
	public RfiLogs remove(long rfilogId) throws NoSuchRfiLogsException;

	public RfiLogs updateImpl(RfiLogs rfiLogs);

	/**
	 * Returns the rfi logs with the primary key or throws a <code>NoSuchRfiLogsException</code> if it could not be found.
	 *
	 * @param rfilogId the primary key of the rfi logs
	 * @return the rfi logs
	 * @throws NoSuchRfiLogsException if a rfi logs with the primary key could not be found
	 */
	public RfiLogs findByPrimaryKey(long rfilogId)
		throws NoSuchRfiLogsException;

	/**
	 * Returns the rfi logs with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param rfilogId the primary key of the rfi logs
	 * @return the rfi logs, or <code>null</code> if a rfi logs with the primary key could not be found
	 */
	public RfiLogs fetchByPrimaryKey(long rfilogId);

	/**
	 * Returns all the rfi logses.
	 *
	 * @return the rfi logses
	 */
	public java.util.List<RfiLogs> findAll();

	/**
	 * Returns a range of all the rfi logses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RfiLogsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of rfi logses
	 * @param end the upper bound of the range of rfi logses (not inclusive)
	 * @return the range of rfi logses
	 */
	public java.util.List<RfiLogs> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the rfi logses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RfiLogsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of rfi logses
	 * @param end the upper bound of the range of rfi logses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of rfi logses
	 */
	public java.util.List<RfiLogs> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RfiLogs>
			orderByComparator);

	/**
	 * Returns an ordered range of all the rfi logses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RfiLogsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of rfi logses
	 * @param end the upper bound of the range of rfi logses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of rfi logses
	 */
	public java.util.List<RfiLogs> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RfiLogs>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the rfi logses from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of rfi logses.
	 *
	 * @return the number of rfi logses
	 */
	public int countAll();

}