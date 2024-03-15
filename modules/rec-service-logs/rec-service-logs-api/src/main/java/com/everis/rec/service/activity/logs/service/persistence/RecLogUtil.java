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

package com.everis.rec.service.activity.logs.service.persistence;

import com.everis.rec.service.activity.logs.model.RecLog;

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
 * The persistence utility for the rec log service. This utility wraps <code>com.everis.rec.service.activity.logs.service.persistence.impl.RecLogPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RecLogPersistence
 * @generated
 */
public class RecLogUtil {

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
	public static void clearCache(RecLog recLog) {
		getPersistence().clearCache(recLog);
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
	public static Map<Serializable, RecLog> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<RecLog> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<RecLog> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<RecLog> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<RecLog> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static RecLog update(RecLog recLog) {
		return getPersistence().update(recLog);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static RecLog update(RecLog recLog, ServiceContext serviceContext) {
		return getPersistence().update(recLog, serviceContext);
	}

	/**
	 * Returns all the rec logs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching rec logs
	 */
	public static List<RecLog> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the rec logs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RecLogModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of rec logs
	 * @param end the upper bound of the range of rec logs (not inclusive)
	 * @return the range of matching rec logs
	 */
	public static List<RecLog> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the rec logs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RecLogModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of rec logs
	 * @param end the upper bound of the range of rec logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rec logs
	 */
	public static List<RecLog> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<RecLog> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the rec logs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RecLogModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of rec logs
	 * @param end the upper bound of the range of rec logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching rec logs
	 */
	public static List<RecLog> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<RecLog> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first rec log in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching rec log
	 * @throws NoSuchRecLogException if a matching rec log could not be found
	 */
	public static RecLog findByUuid_First(
			String uuid, OrderByComparator<RecLog> orderByComparator)
		throws com.everis.rec.service.activity.logs.exception.
			NoSuchRecLogException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first rec log in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching rec log, or <code>null</code> if a matching rec log could not be found
	 */
	public static RecLog fetchByUuid_First(
		String uuid, OrderByComparator<RecLog> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last rec log in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching rec log
	 * @throws NoSuchRecLogException if a matching rec log could not be found
	 */
	public static RecLog findByUuid_Last(
			String uuid, OrderByComparator<RecLog> orderByComparator)
		throws com.everis.rec.service.activity.logs.exception.
			NoSuchRecLogException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last rec log in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching rec log, or <code>null</code> if a matching rec log could not be found
	 */
	public static RecLog fetchByUuid_Last(
		String uuid, OrderByComparator<RecLog> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the rec logs before and after the current rec log in the ordered set where uuid = &#63;.
	 *
	 * @param activityLogId the primary key of the current rec log
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next rec log
	 * @throws NoSuchRecLogException if a rec log with the primary key could not be found
	 */
	public static RecLog[] findByUuid_PrevAndNext(
			long activityLogId, String uuid,
			OrderByComparator<RecLog> orderByComparator)
		throws com.everis.rec.service.activity.logs.exception.
			NoSuchRecLogException {

		return getPersistence().findByUuid_PrevAndNext(
			activityLogId, uuid, orderByComparator);
	}

	/**
	 * Removes all the rec logs where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of rec logs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching rec logs
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the rec log where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchRecLogException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching rec log
	 * @throws NoSuchRecLogException if a matching rec log could not be found
	 */
	public static RecLog findByUUID_G(String uuid, long groupId)
		throws com.everis.rec.service.activity.logs.exception.
			NoSuchRecLogException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the rec log where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching rec log, or <code>null</code> if a matching rec log could not be found
	 */
	public static RecLog fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the rec log where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching rec log, or <code>null</code> if a matching rec log could not be found
	 */
	public static RecLog fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, useFinderCache);
	}

	/**
	 * Removes the rec log where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the rec log that was removed
	 */
	public static RecLog removeByUUID_G(String uuid, long groupId)
		throws com.everis.rec.service.activity.logs.exception.
			NoSuchRecLogException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of rec logs where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching rec logs
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the rec logs where classPK = &#63;.
	 *
	 * @param classPK the class pk
	 * @return the matching rec logs
	 */
	public static List<RecLog> findByClassPK(Long classPK) {
		return getPersistence().findByClassPK(classPK);
	}

	/**
	 * Returns a range of all the rec logs where classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RecLogModelImpl</code>.
	 * </p>
	 *
	 * @param classPK the class pk
	 * @param start the lower bound of the range of rec logs
	 * @param end the upper bound of the range of rec logs (not inclusive)
	 * @return the range of matching rec logs
	 */
	public static List<RecLog> findByClassPK(Long classPK, int start, int end) {
		return getPersistence().findByClassPK(classPK, start, end);
	}

	/**
	 * Returns an ordered range of all the rec logs where classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RecLogModelImpl</code>.
	 * </p>
	 *
	 * @param classPK the class pk
	 * @param start the lower bound of the range of rec logs
	 * @param end the upper bound of the range of rec logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rec logs
	 */
	public static List<RecLog> findByClassPK(
		Long classPK, int start, int end,
		OrderByComparator<RecLog> orderByComparator) {

		return getPersistence().findByClassPK(
			classPK, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the rec logs where classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RecLogModelImpl</code>.
	 * </p>
	 *
	 * @param classPK the class pk
	 * @param start the lower bound of the range of rec logs
	 * @param end the upper bound of the range of rec logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching rec logs
	 */
	public static List<RecLog> findByClassPK(
		Long classPK, int start, int end,
		OrderByComparator<RecLog> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByClassPK(
			classPK, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first rec log in the ordered set where classPK = &#63;.
	 *
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching rec log
	 * @throws NoSuchRecLogException if a matching rec log could not be found
	 */
	public static RecLog findByClassPK_First(
			Long classPK, OrderByComparator<RecLog> orderByComparator)
		throws com.everis.rec.service.activity.logs.exception.
			NoSuchRecLogException {

		return getPersistence().findByClassPK_First(classPK, orderByComparator);
	}

	/**
	 * Returns the first rec log in the ordered set where classPK = &#63;.
	 *
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching rec log, or <code>null</code> if a matching rec log could not be found
	 */
	public static RecLog fetchByClassPK_First(
		Long classPK, OrderByComparator<RecLog> orderByComparator) {

		return getPersistence().fetchByClassPK_First(
			classPK, orderByComparator);
	}

	/**
	 * Returns the last rec log in the ordered set where classPK = &#63;.
	 *
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching rec log
	 * @throws NoSuchRecLogException if a matching rec log could not be found
	 */
	public static RecLog findByClassPK_Last(
			Long classPK, OrderByComparator<RecLog> orderByComparator)
		throws com.everis.rec.service.activity.logs.exception.
			NoSuchRecLogException {

		return getPersistence().findByClassPK_Last(classPK, orderByComparator);
	}

	/**
	 * Returns the last rec log in the ordered set where classPK = &#63;.
	 *
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching rec log, or <code>null</code> if a matching rec log could not be found
	 */
	public static RecLog fetchByClassPK_Last(
		Long classPK, OrderByComparator<RecLog> orderByComparator) {

		return getPersistence().fetchByClassPK_Last(classPK, orderByComparator);
	}

	/**
	 * Returns the rec logs before and after the current rec log in the ordered set where classPK = &#63;.
	 *
	 * @param activityLogId the primary key of the current rec log
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next rec log
	 * @throws NoSuchRecLogException if a rec log with the primary key could not be found
	 */
	public static RecLog[] findByClassPK_PrevAndNext(
			long activityLogId, Long classPK,
			OrderByComparator<RecLog> orderByComparator)
		throws com.everis.rec.service.activity.logs.exception.
			NoSuchRecLogException {

		return getPersistence().findByClassPK_PrevAndNext(
			activityLogId, classPK, orderByComparator);
	}

	/**
	 * Removes all the rec logs where classPK = &#63; from the database.
	 *
	 * @param classPK the class pk
	 */
	public static void removeByClassPK(Long classPK) {
		getPersistence().removeByClassPK(classPK);
	}

	/**
	 * Returns the number of rec logs where classPK = &#63;.
	 *
	 * @param classPK the class pk
	 * @return the number of matching rec logs
	 */
	public static int countByClassPK(Long classPK) {
		return getPersistence().countByClassPK(classPK);
	}

	/**
	 * Caches the rec log in the entity cache if it is enabled.
	 *
	 * @param recLog the rec log
	 */
	public static void cacheResult(RecLog recLog) {
		getPersistence().cacheResult(recLog);
	}

	/**
	 * Caches the rec logs in the entity cache if it is enabled.
	 *
	 * @param recLogs the rec logs
	 */
	public static void cacheResult(List<RecLog> recLogs) {
		getPersistence().cacheResult(recLogs);
	}

	/**
	 * Creates a new rec log with the primary key. Does not add the rec log to the database.
	 *
	 * @param activityLogId the primary key for the new rec log
	 * @return the new rec log
	 */
	public static RecLog create(long activityLogId) {
		return getPersistence().create(activityLogId);
	}

	/**
	 * Removes the rec log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param activityLogId the primary key of the rec log
	 * @return the rec log that was removed
	 * @throws NoSuchRecLogException if a rec log with the primary key could not be found
	 */
	public static RecLog remove(long activityLogId)
		throws com.everis.rec.service.activity.logs.exception.
			NoSuchRecLogException {

		return getPersistence().remove(activityLogId);
	}

	public static RecLog updateImpl(RecLog recLog) {
		return getPersistence().updateImpl(recLog);
	}

	/**
	 * Returns the rec log with the primary key or throws a <code>NoSuchRecLogException</code> if it could not be found.
	 *
	 * @param activityLogId the primary key of the rec log
	 * @return the rec log
	 * @throws NoSuchRecLogException if a rec log with the primary key could not be found
	 */
	public static RecLog findByPrimaryKey(long activityLogId)
		throws com.everis.rec.service.activity.logs.exception.
			NoSuchRecLogException {

		return getPersistence().findByPrimaryKey(activityLogId);
	}

	/**
	 * Returns the rec log with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param activityLogId the primary key of the rec log
	 * @return the rec log, or <code>null</code> if a rec log with the primary key could not be found
	 */
	public static RecLog fetchByPrimaryKey(long activityLogId) {
		return getPersistence().fetchByPrimaryKey(activityLogId);
	}

	/**
	 * Returns all the rec logs.
	 *
	 * @return the rec logs
	 */
	public static List<RecLog> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the rec logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RecLogModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of rec logs
	 * @param end the upper bound of the range of rec logs (not inclusive)
	 * @return the range of rec logs
	 */
	public static List<RecLog> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the rec logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RecLogModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of rec logs
	 * @param end the upper bound of the range of rec logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of rec logs
	 */
	public static List<RecLog> findAll(
		int start, int end, OrderByComparator<RecLog> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the rec logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RecLogModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of rec logs
	 * @param end the upper bound of the range of rec logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of rec logs
	 */
	public static List<RecLog> findAll(
		int start, int end, OrderByComparator<RecLog> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the rec logs from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of rec logs.
	 *
	 * @return the number of rec logs
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static RecLogPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<RecLogPersistence, RecLogPersistence>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(RecLogPersistence.class);

		ServiceTracker<RecLogPersistence, RecLogPersistence> serviceTracker =
			new ServiceTracker<RecLogPersistence, RecLogPersistence>(
				bundle.getBundleContext(), RecLogPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}