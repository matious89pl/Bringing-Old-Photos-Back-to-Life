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

package com.everis.rec.remediation.tracker.service.persistence;

import com.everis.rec.remediation.tracker.model.RemediationTracker;

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
 * The persistence utility for the remediation tracker service. This utility wraps <code>com.everis.rec.remediation.tracker.service.persistence.impl.RemediationTrackerPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RemediationTrackerPersistence
 * @generated
 */
public class RemediationTrackerUtil {

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
	public static void clearCache(RemediationTracker remediationTracker) {
		getPersistence().clearCache(remediationTracker);
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
	public static Map<Serializable, RemediationTracker> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<RemediationTracker> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<RemediationTracker> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<RemediationTracker> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<RemediationTracker> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static RemediationTracker update(
		RemediationTracker remediationTracker) {

		return getPersistence().update(remediationTracker);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static RemediationTracker update(
		RemediationTracker remediationTracker, ServiceContext serviceContext) {

		return getPersistence().update(remediationTracker, serviceContext);
	}

	/**
	 * Returns all the remediation trackers where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching remediation trackers
	 */
	public static List<RemediationTracker> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the remediation trackers where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RemediationTrackerModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of remediation trackers
	 * @param end the upper bound of the range of remediation trackers (not inclusive)
	 * @return the range of matching remediation trackers
	 */
	public static List<RemediationTracker> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the remediation trackers where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RemediationTrackerModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of remediation trackers
	 * @param end the upper bound of the range of remediation trackers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching remediation trackers
	 */
	public static List<RemediationTracker> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<RemediationTracker> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the remediation trackers where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RemediationTrackerModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of remediation trackers
	 * @param end the upper bound of the range of remediation trackers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching remediation trackers
	 */
	public static List<RemediationTracker> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<RemediationTracker> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first remediation tracker in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching remediation tracker
	 * @throws NoSuchRemediationTrackerException if a matching remediation tracker could not be found
	 */
	public static RemediationTracker findByUuid_First(
			String uuid,
			OrderByComparator<RemediationTracker> orderByComparator)
		throws com.everis.rec.remediation.tracker.exception.
			NoSuchRemediationTrackerException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first remediation tracker in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching remediation tracker, or <code>null</code> if a matching remediation tracker could not be found
	 */
	public static RemediationTracker fetchByUuid_First(
		String uuid, OrderByComparator<RemediationTracker> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last remediation tracker in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching remediation tracker
	 * @throws NoSuchRemediationTrackerException if a matching remediation tracker could not be found
	 */
	public static RemediationTracker findByUuid_Last(
			String uuid,
			OrderByComparator<RemediationTracker> orderByComparator)
		throws com.everis.rec.remediation.tracker.exception.
			NoSuchRemediationTrackerException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last remediation tracker in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching remediation tracker, or <code>null</code> if a matching remediation tracker could not be found
	 */
	public static RemediationTracker fetchByUuid_Last(
		String uuid, OrderByComparator<RemediationTracker> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the remediation trackers before and after the current remediation tracker in the ordered set where uuid = &#63;.
	 *
	 * @param remediationTrackerId the primary key of the current remediation tracker
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next remediation tracker
	 * @throws NoSuchRemediationTrackerException if a remediation tracker with the primary key could not be found
	 */
	public static RemediationTracker[] findByUuid_PrevAndNext(
			long remediationTrackerId, String uuid,
			OrderByComparator<RemediationTracker> orderByComparator)
		throws com.everis.rec.remediation.tracker.exception.
			NoSuchRemediationTrackerException {

		return getPersistence().findByUuid_PrevAndNext(
			remediationTrackerId, uuid, orderByComparator);
	}

	/**
	 * Removes all the remediation trackers where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of remediation trackers where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching remediation trackers
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the remediation tracker where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchRemediationTrackerException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching remediation tracker
	 * @throws NoSuchRemediationTrackerException if a matching remediation tracker could not be found
	 */
	public static RemediationTracker findByUUID_G(String uuid, long groupId)
		throws com.everis.rec.remediation.tracker.exception.
			NoSuchRemediationTrackerException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the remediation tracker where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching remediation tracker, or <code>null</code> if a matching remediation tracker could not be found
	 */
	public static RemediationTracker fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the remediation tracker where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching remediation tracker, or <code>null</code> if a matching remediation tracker could not be found
	 */
	public static RemediationTracker fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, useFinderCache);
	}

	/**
	 * Removes the remediation tracker where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the remediation tracker that was removed
	 */
	public static RemediationTracker removeByUUID_G(String uuid, long groupId)
		throws com.everis.rec.remediation.tracker.exception.
			NoSuchRemediationTrackerException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of remediation trackers where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching remediation trackers
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the remediation trackers where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching remediation trackers
	 */
	public static List<RemediationTracker> findByUuid_C(
		String uuid, long companyId) {

		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the remediation trackers where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RemediationTrackerModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of remediation trackers
	 * @param end the upper bound of the range of remediation trackers (not inclusive)
	 * @return the range of matching remediation trackers
	 */
	public static List<RemediationTracker> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the remediation trackers where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RemediationTrackerModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of remediation trackers
	 * @param end the upper bound of the range of remediation trackers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching remediation trackers
	 */
	public static List<RemediationTracker> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<RemediationTracker> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the remediation trackers where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RemediationTrackerModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of remediation trackers
	 * @param end the upper bound of the range of remediation trackers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching remediation trackers
	 */
	public static List<RemediationTracker> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<RemediationTracker> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first remediation tracker in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching remediation tracker
	 * @throws NoSuchRemediationTrackerException if a matching remediation tracker could not be found
	 */
	public static RemediationTracker findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<RemediationTracker> orderByComparator)
		throws com.everis.rec.remediation.tracker.exception.
			NoSuchRemediationTrackerException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first remediation tracker in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching remediation tracker, or <code>null</code> if a matching remediation tracker could not be found
	 */
	public static RemediationTracker fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<RemediationTracker> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last remediation tracker in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching remediation tracker
	 * @throws NoSuchRemediationTrackerException if a matching remediation tracker could not be found
	 */
	public static RemediationTracker findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<RemediationTracker> orderByComparator)
		throws com.everis.rec.remediation.tracker.exception.
			NoSuchRemediationTrackerException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last remediation tracker in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching remediation tracker, or <code>null</code> if a matching remediation tracker could not be found
	 */
	public static RemediationTracker fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<RemediationTracker> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the remediation trackers before and after the current remediation tracker in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param remediationTrackerId the primary key of the current remediation tracker
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next remediation tracker
	 * @throws NoSuchRemediationTrackerException if a remediation tracker with the primary key could not be found
	 */
	public static RemediationTracker[] findByUuid_C_PrevAndNext(
			long remediationTrackerId, String uuid, long companyId,
			OrderByComparator<RemediationTracker> orderByComparator)
		throws com.everis.rec.remediation.tracker.exception.
			NoSuchRemediationTrackerException {

		return getPersistence().findByUuid_C_PrevAndNext(
			remediationTrackerId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the remediation trackers where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of remediation trackers where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching remediation trackers
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns all the remediation trackers where status = &#63;.
	 *
	 * @param status the status
	 * @return the matching remediation trackers
	 */
	public static List<RemediationTracker> findByStatus(String status) {
		return getPersistence().findByStatus(status);
	}

	/**
	 * Returns a range of all the remediation trackers where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RemediationTrackerModelImpl</code>.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of remediation trackers
	 * @param end the upper bound of the range of remediation trackers (not inclusive)
	 * @return the range of matching remediation trackers
	 */
	public static List<RemediationTracker> findByStatus(
		String status, int start, int end) {

		return getPersistence().findByStatus(status, start, end);
	}

	/**
	 * Returns an ordered range of all the remediation trackers where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RemediationTrackerModelImpl</code>.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of remediation trackers
	 * @param end the upper bound of the range of remediation trackers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching remediation trackers
	 */
	public static List<RemediationTracker> findByStatus(
		String status, int start, int end,
		OrderByComparator<RemediationTracker> orderByComparator) {

		return getPersistence().findByStatus(
			status, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the remediation trackers where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RemediationTrackerModelImpl</code>.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of remediation trackers
	 * @param end the upper bound of the range of remediation trackers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching remediation trackers
	 */
	public static List<RemediationTracker> findByStatus(
		String status, int start, int end,
		OrderByComparator<RemediationTracker> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByStatus(
			status, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first remediation tracker in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching remediation tracker
	 * @throws NoSuchRemediationTrackerException if a matching remediation tracker could not be found
	 */
	public static RemediationTracker findByStatus_First(
			String status,
			OrderByComparator<RemediationTracker> orderByComparator)
		throws com.everis.rec.remediation.tracker.exception.
			NoSuchRemediationTrackerException {

		return getPersistence().findByStatus_First(status, orderByComparator);
	}

	/**
	 * Returns the first remediation tracker in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching remediation tracker, or <code>null</code> if a matching remediation tracker could not be found
	 */
	public static RemediationTracker fetchByStatus_First(
		String status,
		OrderByComparator<RemediationTracker> orderByComparator) {

		return getPersistence().fetchByStatus_First(status, orderByComparator);
	}

	/**
	 * Returns the last remediation tracker in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching remediation tracker
	 * @throws NoSuchRemediationTrackerException if a matching remediation tracker could not be found
	 */
	public static RemediationTracker findByStatus_Last(
			String status,
			OrderByComparator<RemediationTracker> orderByComparator)
		throws com.everis.rec.remediation.tracker.exception.
			NoSuchRemediationTrackerException {

		return getPersistence().findByStatus_Last(status, orderByComparator);
	}

	/**
	 * Returns the last remediation tracker in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching remediation tracker, or <code>null</code> if a matching remediation tracker could not be found
	 */
	public static RemediationTracker fetchByStatus_Last(
		String status,
		OrderByComparator<RemediationTracker> orderByComparator) {

		return getPersistence().fetchByStatus_Last(status, orderByComparator);
	}

	/**
	 * Returns the remediation trackers before and after the current remediation tracker in the ordered set where status = &#63;.
	 *
	 * @param remediationTrackerId the primary key of the current remediation tracker
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next remediation tracker
	 * @throws NoSuchRemediationTrackerException if a remediation tracker with the primary key could not be found
	 */
	public static RemediationTracker[] findByStatus_PrevAndNext(
			long remediationTrackerId, String status,
			OrderByComparator<RemediationTracker> orderByComparator)
		throws com.everis.rec.remediation.tracker.exception.
			NoSuchRemediationTrackerException {

		return getPersistence().findByStatus_PrevAndNext(
			remediationTrackerId, status, orderByComparator);
	}

	/**
	 * Removes all the remediation trackers where status = &#63; from the database.
	 *
	 * @param status the status
	 */
	public static void removeByStatus(String status) {
		getPersistence().removeByStatus(status);
	}

	/**
	 * Returns the number of remediation trackers where status = &#63;.
	 *
	 * @param status the status
	 * @return the number of matching remediation trackers
	 */
	public static int countByStatus(String status) {
		return getPersistence().countByStatus(status);
	}

	/**
	 * Caches the remediation tracker in the entity cache if it is enabled.
	 *
	 * @param remediationTracker the remediation tracker
	 */
	public static void cacheResult(RemediationTracker remediationTracker) {
		getPersistence().cacheResult(remediationTracker);
	}

	/**
	 * Caches the remediation trackers in the entity cache if it is enabled.
	 *
	 * @param remediationTrackers the remediation trackers
	 */
	public static void cacheResult(
		List<RemediationTracker> remediationTrackers) {

		getPersistence().cacheResult(remediationTrackers);
	}

	/**
	 * Creates a new remediation tracker with the primary key. Does not add the remediation tracker to the database.
	 *
	 * @param remediationTrackerId the primary key for the new remediation tracker
	 * @return the new remediation tracker
	 */
	public static RemediationTracker create(long remediationTrackerId) {
		return getPersistence().create(remediationTrackerId);
	}

	/**
	 * Removes the remediation tracker with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param remediationTrackerId the primary key of the remediation tracker
	 * @return the remediation tracker that was removed
	 * @throws NoSuchRemediationTrackerException if a remediation tracker with the primary key could not be found
	 */
	public static RemediationTracker remove(long remediationTrackerId)
		throws com.everis.rec.remediation.tracker.exception.
			NoSuchRemediationTrackerException {

		return getPersistence().remove(remediationTrackerId);
	}

	public static RemediationTracker updateImpl(
		RemediationTracker remediationTracker) {

		return getPersistence().updateImpl(remediationTracker);
	}

	/**
	 * Returns the remediation tracker with the primary key or throws a <code>NoSuchRemediationTrackerException</code> if it could not be found.
	 *
	 * @param remediationTrackerId the primary key of the remediation tracker
	 * @return the remediation tracker
	 * @throws NoSuchRemediationTrackerException if a remediation tracker with the primary key could not be found
	 */
	public static RemediationTracker findByPrimaryKey(long remediationTrackerId)
		throws com.everis.rec.remediation.tracker.exception.
			NoSuchRemediationTrackerException {

		return getPersistence().findByPrimaryKey(remediationTrackerId);
	}

	/**
	 * Returns the remediation tracker with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param remediationTrackerId the primary key of the remediation tracker
	 * @return the remediation tracker, or <code>null</code> if a remediation tracker with the primary key could not be found
	 */
	public static RemediationTracker fetchByPrimaryKey(
		long remediationTrackerId) {

		return getPersistence().fetchByPrimaryKey(remediationTrackerId);
	}

	/**
	 * Returns all the remediation trackers.
	 *
	 * @return the remediation trackers
	 */
	public static List<RemediationTracker> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the remediation trackers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RemediationTrackerModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of remediation trackers
	 * @param end the upper bound of the range of remediation trackers (not inclusive)
	 * @return the range of remediation trackers
	 */
	public static List<RemediationTracker> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the remediation trackers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RemediationTrackerModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of remediation trackers
	 * @param end the upper bound of the range of remediation trackers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of remediation trackers
	 */
	public static List<RemediationTracker> findAll(
		int start, int end,
		OrderByComparator<RemediationTracker> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the remediation trackers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RemediationTrackerModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of remediation trackers
	 * @param end the upper bound of the range of remediation trackers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of remediation trackers
	 */
	public static List<RemediationTracker> findAll(
		int start, int end,
		OrderByComparator<RemediationTracker> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the remediation trackers from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of remediation trackers.
	 *
	 * @return the number of remediation trackers
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static RemediationTrackerPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<RemediationTrackerPersistence, RemediationTrackerPersistence>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			RemediationTrackerPersistence.class);

		ServiceTracker
			<RemediationTrackerPersistence, RemediationTrackerPersistence>
				serviceTracker =
					new ServiceTracker
						<RemediationTrackerPersistence,
						 RemediationTrackerPersistence>(
							 bundle.getBundleContext(),
							 RemediationTrackerPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}