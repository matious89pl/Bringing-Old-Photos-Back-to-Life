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

package com.everis.rec.maintenanceactivity.service.persistence;

import com.everis.rec.maintenanceactivity.model.MaintenanceActivity;

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
 * The persistence utility for the maintenance activity service. This utility wraps <code>com.everis.rec.maintenanceactivity.service.persistence.impl.MaintenanceActivityPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MaintenanceActivityPersistence
 * @generated
 */
public class MaintenanceActivityUtil {

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
	public static void clearCache(MaintenanceActivity maintenanceActivity) {
		getPersistence().clearCache(maintenanceActivity);
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
	public static Map<Serializable, MaintenanceActivity> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<MaintenanceActivity> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<MaintenanceActivity> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<MaintenanceActivity> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<MaintenanceActivity> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static MaintenanceActivity update(
		MaintenanceActivity maintenanceActivity) {

		return getPersistence().update(maintenanceActivity);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static MaintenanceActivity update(
		MaintenanceActivity maintenanceActivity,
		ServiceContext serviceContext) {

		return getPersistence().update(maintenanceActivity, serviceContext);
	}

	/**
	 * Returns all the maintenance activities where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching maintenance activities
	 */
	public static List<MaintenanceActivity> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the maintenance activities where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MaintenanceActivityModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of maintenance activities
	 * @param end the upper bound of the range of maintenance activities (not inclusive)
	 * @return the range of matching maintenance activities
	 */
	public static List<MaintenanceActivity> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the maintenance activities where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MaintenanceActivityModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of maintenance activities
	 * @param end the upper bound of the range of maintenance activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching maintenance activities
	 */
	public static List<MaintenanceActivity> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<MaintenanceActivity> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the maintenance activities where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MaintenanceActivityModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of maintenance activities
	 * @param end the upper bound of the range of maintenance activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching maintenance activities
	 */
	public static List<MaintenanceActivity> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<MaintenanceActivity> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first maintenance activity in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching maintenance activity
	 * @throws NoSuchMaintenanceActivityException if a matching maintenance activity could not be found
	 */
	public static MaintenanceActivity findByUuid_First(
			String uuid,
			OrderByComparator<MaintenanceActivity> orderByComparator)
		throws com.everis.rec.maintenanceactivity.exception.
			NoSuchMaintenanceActivityException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first maintenance activity in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching maintenance activity, or <code>null</code> if a matching maintenance activity could not be found
	 */
	public static MaintenanceActivity fetchByUuid_First(
		String uuid, OrderByComparator<MaintenanceActivity> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last maintenance activity in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching maintenance activity
	 * @throws NoSuchMaintenanceActivityException if a matching maintenance activity could not be found
	 */
	public static MaintenanceActivity findByUuid_Last(
			String uuid,
			OrderByComparator<MaintenanceActivity> orderByComparator)
		throws com.everis.rec.maintenanceactivity.exception.
			NoSuchMaintenanceActivityException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last maintenance activity in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching maintenance activity, or <code>null</code> if a matching maintenance activity could not be found
	 */
	public static MaintenanceActivity fetchByUuid_Last(
		String uuid, OrderByComparator<MaintenanceActivity> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the maintenance activities before and after the current maintenance activity in the ordered set where uuid = &#63;.
	 *
	 * @param maintenanceactivityId the primary key of the current maintenance activity
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next maintenance activity
	 * @throws NoSuchMaintenanceActivityException if a maintenance activity with the primary key could not be found
	 */
	public static MaintenanceActivity[] findByUuid_PrevAndNext(
			long maintenanceactivityId, String uuid,
			OrderByComparator<MaintenanceActivity> orderByComparator)
		throws com.everis.rec.maintenanceactivity.exception.
			NoSuchMaintenanceActivityException {

		return getPersistence().findByUuid_PrevAndNext(
			maintenanceactivityId, uuid, orderByComparator);
	}

	/**
	 * Removes all the maintenance activities where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of maintenance activities where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching maintenance activities
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns all the maintenance activities where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching maintenance activities
	 */
	public static List<MaintenanceActivity> findByUuid_C(
		String uuid, long companyId) {

		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the maintenance activities where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MaintenanceActivityModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of maintenance activities
	 * @param end the upper bound of the range of maintenance activities (not inclusive)
	 * @return the range of matching maintenance activities
	 */
	public static List<MaintenanceActivity> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the maintenance activities where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MaintenanceActivityModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of maintenance activities
	 * @param end the upper bound of the range of maintenance activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching maintenance activities
	 */
	public static List<MaintenanceActivity> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<MaintenanceActivity> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the maintenance activities where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MaintenanceActivityModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of maintenance activities
	 * @param end the upper bound of the range of maintenance activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching maintenance activities
	 */
	public static List<MaintenanceActivity> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<MaintenanceActivity> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first maintenance activity in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching maintenance activity
	 * @throws NoSuchMaintenanceActivityException if a matching maintenance activity could not be found
	 */
	public static MaintenanceActivity findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<MaintenanceActivity> orderByComparator)
		throws com.everis.rec.maintenanceactivity.exception.
			NoSuchMaintenanceActivityException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first maintenance activity in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching maintenance activity, or <code>null</code> if a matching maintenance activity could not be found
	 */
	public static MaintenanceActivity fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<MaintenanceActivity> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last maintenance activity in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching maintenance activity
	 * @throws NoSuchMaintenanceActivityException if a matching maintenance activity could not be found
	 */
	public static MaintenanceActivity findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<MaintenanceActivity> orderByComparator)
		throws com.everis.rec.maintenanceactivity.exception.
			NoSuchMaintenanceActivityException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last maintenance activity in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching maintenance activity, or <code>null</code> if a matching maintenance activity could not be found
	 */
	public static MaintenanceActivity fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<MaintenanceActivity> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the maintenance activities before and after the current maintenance activity in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param maintenanceactivityId the primary key of the current maintenance activity
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next maintenance activity
	 * @throws NoSuchMaintenanceActivityException if a maintenance activity with the primary key could not be found
	 */
	public static MaintenanceActivity[] findByUuid_C_PrevAndNext(
			long maintenanceactivityId, String uuid, long companyId,
			OrderByComparator<MaintenanceActivity> orderByComparator)
		throws com.everis.rec.maintenanceactivity.exception.
			NoSuchMaintenanceActivityException {

		return getPersistence().findByUuid_C_PrevAndNext(
			maintenanceactivityId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the maintenance activities where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of maintenance activities where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching maintenance activities
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns all the maintenance activities where status = &#63;.
	 *
	 * @param status the status
	 * @return the matching maintenance activities
	 */
	public static List<MaintenanceActivity> findByStatus(String status) {
		return getPersistence().findByStatus(status);
	}

	/**
	 * Returns a range of all the maintenance activities where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MaintenanceActivityModelImpl</code>.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of maintenance activities
	 * @param end the upper bound of the range of maintenance activities (not inclusive)
	 * @return the range of matching maintenance activities
	 */
	public static List<MaintenanceActivity> findByStatus(
		String status, int start, int end) {

		return getPersistence().findByStatus(status, start, end);
	}

	/**
	 * Returns an ordered range of all the maintenance activities where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MaintenanceActivityModelImpl</code>.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of maintenance activities
	 * @param end the upper bound of the range of maintenance activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching maintenance activities
	 */
	public static List<MaintenanceActivity> findByStatus(
		String status, int start, int end,
		OrderByComparator<MaintenanceActivity> orderByComparator) {

		return getPersistence().findByStatus(
			status, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the maintenance activities where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MaintenanceActivityModelImpl</code>.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of maintenance activities
	 * @param end the upper bound of the range of maintenance activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching maintenance activities
	 */
	public static List<MaintenanceActivity> findByStatus(
		String status, int start, int end,
		OrderByComparator<MaintenanceActivity> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByStatus(
			status, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first maintenance activity in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching maintenance activity
	 * @throws NoSuchMaintenanceActivityException if a matching maintenance activity could not be found
	 */
	public static MaintenanceActivity findByStatus_First(
			String status,
			OrderByComparator<MaintenanceActivity> orderByComparator)
		throws com.everis.rec.maintenanceactivity.exception.
			NoSuchMaintenanceActivityException {

		return getPersistence().findByStatus_First(status, orderByComparator);
	}

	/**
	 * Returns the first maintenance activity in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching maintenance activity, or <code>null</code> if a matching maintenance activity could not be found
	 */
	public static MaintenanceActivity fetchByStatus_First(
		String status,
		OrderByComparator<MaintenanceActivity> orderByComparator) {

		return getPersistence().fetchByStatus_First(status, orderByComparator);
	}

	/**
	 * Returns the last maintenance activity in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching maintenance activity
	 * @throws NoSuchMaintenanceActivityException if a matching maintenance activity could not be found
	 */
	public static MaintenanceActivity findByStatus_Last(
			String status,
			OrderByComparator<MaintenanceActivity> orderByComparator)
		throws com.everis.rec.maintenanceactivity.exception.
			NoSuchMaintenanceActivityException {

		return getPersistence().findByStatus_Last(status, orderByComparator);
	}

	/**
	 * Returns the last maintenance activity in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching maintenance activity, or <code>null</code> if a matching maintenance activity could not be found
	 */
	public static MaintenanceActivity fetchByStatus_Last(
		String status,
		OrderByComparator<MaintenanceActivity> orderByComparator) {

		return getPersistence().fetchByStatus_Last(status, orderByComparator);
	}

	/**
	 * Returns the maintenance activities before and after the current maintenance activity in the ordered set where status = &#63;.
	 *
	 * @param maintenanceactivityId the primary key of the current maintenance activity
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next maintenance activity
	 * @throws NoSuchMaintenanceActivityException if a maintenance activity with the primary key could not be found
	 */
	public static MaintenanceActivity[] findByStatus_PrevAndNext(
			long maintenanceactivityId, String status,
			OrderByComparator<MaintenanceActivity> orderByComparator)
		throws com.everis.rec.maintenanceactivity.exception.
			NoSuchMaintenanceActivityException {

		return getPersistence().findByStatus_PrevAndNext(
			maintenanceactivityId, status, orderByComparator);
	}

	/**
	 * Removes all the maintenance activities where status = &#63; from the database.
	 *
	 * @param status the status
	 */
	public static void removeByStatus(String status) {
		getPersistence().removeByStatus(status);
	}

	/**
	 * Returns the number of maintenance activities where status = &#63;.
	 *
	 * @param status the status
	 * @return the number of matching maintenance activities
	 */
	public static int countByStatus(String status) {
		return getPersistence().countByStatus(status);
	}

	/**
	 * Caches the maintenance activity in the entity cache if it is enabled.
	 *
	 * @param maintenanceActivity the maintenance activity
	 */
	public static void cacheResult(MaintenanceActivity maintenanceActivity) {
		getPersistence().cacheResult(maintenanceActivity);
	}

	/**
	 * Caches the maintenance activities in the entity cache if it is enabled.
	 *
	 * @param maintenanceActivities the maintenance activities
	 */
	public static void cacheResult(
		List<MaintenanceActivity> maintenanceActivities) {

		getPersistence().cacheResult(maintenanceActivities);
	}

	/**
	 * Creates a new maintenance activity with the primary key. Does not add the maintenance activity to the database.
	 *
	 * @param maintenanceactivityId the primary key for the new maintenance activity
	 * @return the new maintenance activity
	 */
	public static MaintenanceActivity create(long maintenanceactivityId) {
		return getPersistence().create(maintenanceactivityId);
	}

	/**
	 * Removes the maintenance activity with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param maintenanceactivityId the primary key of the maintenance activity
	 * @return the maintenance activity that was removed
	 * @throws NoSuchMaintenanceActivityException if a maintenance activity with the primary key could not be found
	 */
	public static MaintenanceActivity remove(long maintenanceactivityId)
		throws com.everis.rec.maintenanceactivity.exception.
			NoSuchMaintenanceActivityException {

		return getPersistence().remove(maintenanceactivityId);
	}

	public static MaintenanceActivity updateImpl(
		MaintenanceActivity maintenanceActivity) {

		return getPersistence().updateImpl(maintenanceActivity);
	}

	/**
	 * Returns the maintenance activity with the primary key or throws a <code>NoSuchMaintenanceActivityException</code> if it could not be found.
	 *
	 * @param maintenanceactivityId the primary key of the maintenance activity
	 * @return the maintenance activity
	 * @throws NoSuchMaintenanceActivityException if a maintenance activity with the primary key could not be found
	 */
	public static MaintenanceActivity findByPrimaryKey(
			long maintenanceactivityId)
		throws com.everis.rec.maintenanceactivity.exception.
			NoSuchMaintenanceActivityException {

		return getPersistence().findByPrimaryKey(maintenanceactivityId);
	}

	/**
	 * Returns the maintenance activity with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param maintenanceactivityId the primary key of the maintenance activity
	 * @return the maintenance activity, or <code>null</code> if a maintenance activity with the primary key could not be found
	 */
	public static MaintenanceActivity fetchByPrimaryKey(
		long maintenanceactivityId) {

		return getPersistence().fetchByPrimaryKey(maintenanceactivityId);
	}

	/**
	 * Returns all the maintenance activities.
	 *
	 * @return the maintenance activities
	 */
	public static List<MaintenanceActivity> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the maintenance activities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MaintenanceActivityModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of maintenance activities
	 * @param end the upper bound of the range of maintenance activities (not inclusive)
	 * @return the range of maintenance activities
	 */
	public static List<MaintenanceActivity> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the maintenance activities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MaintenanceActivityModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of maintenance activities
	 * @param end the upper bound of the range of maintenance activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of maintenance activities
	 */
	public static List<MaintenanceActivity> findAll(
		int start, int end,
		OrderByComparator<MaintenanceActivity> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the maintenance activities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MaintenanceActivityModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of maintenance activities
	 * @param end the upper bound of the range of maintenance activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of maintenance activities
	 */
	public static List<MaintenanceActivity> findAll(
		int start, int end,
		OrderByComparator<MaintenanceActivity> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the maintenance activities from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of maintenance activities.
	 *
	 * @return the number of maintenance activities
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static MaintenanceActivityPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<MaintenanceActivityPersistence, MaintenanceActivityPersistence>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			MaintenanceActivityPersistence.class);

		ServiceTracker
			<MaintenanceActivityPersistence, MaintenanceActivityPersistence>
				serviceTracker =
					new ServiceTracker
						<MaintenanceActivityPersistence,
						 MaintenanceActivityPersistence>(
							 bundle.getBundleContext(),
							 MaintenanceActivityPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}