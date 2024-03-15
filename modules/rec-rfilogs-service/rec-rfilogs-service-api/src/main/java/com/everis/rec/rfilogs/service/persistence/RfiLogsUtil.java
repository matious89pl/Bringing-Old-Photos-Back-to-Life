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

import com.everis.rec.rfilogs.model.RfiLogs;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the rfi logs service. This utility wraps <code>com.everis.rec.rfilogs.service.persistence.impl.RfiLogsPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RfiLogsPersistence
 * @generated
 */
public class RfiLogsUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(RfiLogs rfiLogs) {
		getPersistence().clearCache(rfiLogs);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, RfiLogs> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<RfiLogs> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<RfiLogs> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<RfiLogs> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<RfiLogs> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static RfiLogs update(RfiLogs rfiLogs) {
		return getPersistence().update(rfiLogs);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static RfiLogs update(
		RfiLogs rfiLogs, ServiceContext serviceContext) {

		return getPersistence().update(rfiLogs, serviceContext);
	}

	/**
	 * Returns all the rfi logses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching rfi logses
	 */
	public static List<RfiLogs> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

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
	public static List<RfiLogs> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static List<RfiLogs> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<RfiLogs> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

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
	public static List<RfiLogs> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<RfiLogs> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first rfi logs in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching rfi logs
	 * @throws NoSuchRfiLogsException if a matching rfi logs could not be found
	 */
	public static RfiLogs findByUuid_First(
			String uuid, OrderByComparator<RfiLogs> orderByComparator)
		throws com.everis.rec.rfilogs.exception.NoSuchRfiLogsException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first rfi logs in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching rfi logs, or <code>null</code> if a matching rfi logs could not be found
	 */
	public static RfiLogs fetchByUuid_First(
		String uuid, OrderByComparator<RfiLogs> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last rfi logs in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching rfi logs
	 * @throws NoSuchRfiLogsException if a matching rfi logs could not be found
	 */
	public static RfiLogs findByUuid_Last(
			String uuid, OrderByComparator<RfiLogs> orderByComparator)
		throws com.everis.rec.rfilogs.exception.NoSuchRfiLogsException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last rfi logs in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching rfi logs, or <code>null</code> if a matching rfi logs could not be found
	 */
	public static RfiLogs fetchByUuid_Last(
		String uuid, OrderByComparator<RfiLogs> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the rfi logses before and after the current rfi logs in the ordered set where uuid = &#63;.
	 *
	 * @param rfilogId the primary key of the current rfi logs
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next rfi logs
	 * @throws NoSuchRfiLogsException if a rfi logs with the primary key could not be found
	 */
	public static RfiLogs[] findByUuid_PrevAndNext(
			long rfilogId, String uuid,
			OrderByComparator<RfiLogs> orderByComparator)
		throws com.everis.rec.rfilogs.exception.NoSuchRfiLogsException {

		return getPersistence().findByUuid_PrevAndNext(
			rfilogId, uuid, orderByComparator);
	}

	/**
	 * Removes all the rfi logses where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of rfi logses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching rfi logses
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns all the rfi logses where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching rfi logses
	 */
	public static List<RfiLogs> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

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
	public static List<RfiLogs> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

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
	public static List<RfiLogs> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<RfiLogs> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

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
	public static List<RfiLogs> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<RfiLogs> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first rfi logs in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching rfi logs
	 * @throws NoSuchRfiLogsException if a matching rfi logs could not be found
	 */
	public static RfiLogs findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<RfiLogs> orderByComparator)
		throws com.everis.rec.rfilogs.exception.NoSuchRfiLogsException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first rfi logs in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching rfi logs, or <code>null</code> if a matching rfi logs could not be found
	 */
	public static RfiLogs fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<RfiLogs> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last rfi logs in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching rfi logs
	 * @throws NoSuchRfiLogsException if a matching rfi logs could not be found
	 */
	public static RfiLogs findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<RfiLogs> orderByComparator)
		throws com.everis.rec.rfilogs.exception.NoSuchRfiLogsException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last rfi logs in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching rfi logs, or <code>null</code> if a matching rfi logs could not be found
	 */
	public static RfiLogs fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<RfiLogs> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

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
	public static RfiLogs[] findByUuid_C_PrevAndNext(
			long rfilogId, String uuid, long companyId,
			OrderByComparator<RfiLogs> orderByComparator)
		throws com.everis.rec.rfilogs.exception.NoSuchRfiLogsException {

		return getPersistence().findByUuid_C_PrevAndNext(
			rfilogId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the rfi logses where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of rfi logses where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching rfi logses
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns all the rfi logses where status = &#63;.
	 *
	 * @param status the status
	 * @return the matching rfi logses
	 */
	public static List<RfiLogs> findByStatus(String status) {
		return getPersistence().findByStatus(status);
	}

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
	public static List<RfiLogs> findByStatus(
		String status, int start, int end) {

		return getPersistence().findByStatus(status, start, end);
	}

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
	public static List<RfiLogs> findByStatus(
		String status, int start, int end,
		OrderByComparator<RfiLogs> orderByComparator) {

		return getPersistence().findByStatus(
			status, start, end, orderByComparator);
	}

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
	public static List<RfiLogs> findByStatus(
		String status, int start, int end,
		OrderByComparator<RfiLogs> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByStatus(
			status, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first rfi logs in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching rfi logs
	 * @throws NoSuchRfiLogsException if a matching rfi logs could not be found
	 */
	public static RfiLogs findByStatus_First(
			String status, OrderByComparator<RfiLogs> orderByComparator)
		throws com.everis.rec.rfilogs.exception.NoSuchRfiLogsException {

		return getPersistence().findByStatus_First(status, orderByComparator);
	}

	/**
	 * Returns the first rfi logs in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching rfi logs, or <code>null</code> if a matching rfi logs could not be found
	 */
	public static RfiLogs fetchByStatus_First(
		String status, OrderByComparator<RfiLogs> orderByComparator) {

		return getPersistence().fetchByStatus_First(status, orderByComparator);
	}

	/**
	 * Returns the last rfi logs in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching rfi logs
	 * @throws NoSuchRfiLogsException if a matching rfi logs could not be found
	 */
	public static RfiLogs findByStatus_Last(
			String status, OrderByComparator<RfiLogs> orderByComparator)
		throws com.everis.rec.rfilogs.exception.NoSuchRfiLogsException {

		return getPersistence().findByStatus_Last(status, orderByComparator);
	}

	/**
	 * Returns the last rfi logs in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching rfi logs, or <code>null</code> if a matching rfi logs could not be found
	 */
	public static RfiLogs fetchByStatus_Last(
		String status, OrderByComparator<RfiLogs> orderByComparator) {

		return getPersistence().fetchByStatus_Last(status, orderByComparator);
	}

	/**
	 * Returns the rfi logses before and after the current rfi logs in the ordered set where status = &#63;.
	 *
	 * @param rfilogId the primary key of the current rfi logs
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next rfi logs
	 * @throws NoSuchRfiLogsException if a rfi logs with the primary key could not be found
	 */
	public static RfiLogs[] findByStatus_PrevAndNext(
			long rfilogId, String status,
			OrderByComparator<RfiLogs> orderByComparator)
		throws com.everis.rec.rfilogs.exception.NoSuchRfiLogsException {

		return getPersistence().findByStatus_PrevAndNext(
			rfilogId, status, orderByComparator);
	}

	/**
	 * Removes all the rfi logses where status = &#63; from the database.
	 *
	 * @param status the status
	 */
	public static void removeByStatus(String status) {
		getPersistence().removeByStatus(status);
	}

	/**
	 * Returns the number of rfi logses where status = &#63;.
	 *
	 * @param status the status
	 * @return the number of matching rfi logses
	 */
	public static int countByStatus(String status) {
		return getPersistence().countByStatus(status);
	}

	/**
	 * Caches the rfi logs in the entity cache if it is enabled.
	 *
	 * @param rfiLogs the rfi logs
	 */
	public static void cacheResult(RfiLogs rfiLogs) {
		getPersistence().cacheResult(rfiLogs);
	}

	/**
	 * Caches the rfi logses in the entity cache if it is enabled.
	 *
	 * @param rfiLogses the rfi logses
	 */
	public static void cacheResult(List<RfiLogs> rfiLogses) {
		getPersistence().cacheResult(rfiLogses);
	}

	/**
	 * Creates a new rfi logs with the primary key. Does not add the rfi logs to the database.
	 *
	 * @param rfilogId the primary key for the new rfi logs
	 * @return the new rfi logs
	 */
	public static RfiLogs create(long rfilogId) {
		return getPersistence().create(rfilogId);
	}

	/**
	 * Removes the rfi logs with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param rfilogId the primary key of the rfi logs
	 * @return the rfi logs that was removed
	 * @throws NoSuchRfiLogsException if a rfi logs with the primary key could not be found
	 */
	public static RfiLogs remove(long rfilogId)
		throws com.everis.rec.rfilogs.exception.NoSuchRfiLogsException {

		return getPersistence().remove(rfilogId);
	}

	public static RfiLogs updateImpl(RfiLogs rfiLogs) {
		return getPersistence().updateImpl(rfiLogs);
	}

	/**
	 * Returns the rfi logs with the primary key or throws a <code>NoSuchRfiLogsException</code> if it could not be found.
	 *
	 * @param rfilogId the primary key of the rfi logs
	 * @return the rfi logs
	 * @throws NoSuchRfiLogsException if a rfi logs with the primary key could not be found
	 */
	public static RfiLogs findByPrimaryKey(long rfilogId)
		throws com.everis.rec.rfilogs.exception.NoSuchRfiLogsException {

		return getPersistence().findByPrimaryKey(rfilogId);
	}

	/**
	 * Returns the rfi logs with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param rfilogId the primary key of the rfi logs
	 * @return the rfi logs, or <code>null</code> if a rfi logs with the primary key could not be found
	 */
	public static RfiLogs fetchByPrimaryKey(long rfilogId) {
		return getPersistence().fetchByPrimaryKey(rfilogId);
	}

	/**
	 * Returns all the rfi logses.
	 *
	 * @return the rfi logses
	 */
	public static List<RfiLogs> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<RfiLogs> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<RfiLogs> findAll(
		int start, int end, OrderByComparator<RfiLogs> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<RfiLogs> findAll(
		int start, int end, OrderByComparator<RfiLogs> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the rfi logses from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of rfi logses.
	 *
	 * @return the number of rfi logses
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static RfiLogsPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<RfiLogsPersistence, RfiLogsPersistence>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(RfiLogsPersistence.class);

		ServiceTracker<RfiLogsPersistence, RfiLogsPersistence> serviceTracker =
			new ServiceTracker<RfiLogsPersistence, RfiLogsPersistence>(
				bundle.getBundleContext(), RfiLogsPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}