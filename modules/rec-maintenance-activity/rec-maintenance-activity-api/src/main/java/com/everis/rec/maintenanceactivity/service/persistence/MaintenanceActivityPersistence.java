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

import com.everis.rec.maintenanceactivity.exception.NoSuchMaintenanceActivityException;
import com.everis.rec.maintenanceactivity.model.MaintenanceActivity;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the maintenance activity service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MaintenanceActivityUtil
 * @generated
 */
@ProviderType
public interface MaintenanceActivityPersistence
	extends BasePersistence<MaintenanceActivity> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MaintenanceActivityUtil} to access the maintenance activity persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the maintenance activities where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching maintenance activities
	 */
	public java.util.List<MaintenanceActivity> findByUuid(String uuid);

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
	public java.util.List<MaintenanceActivity> findByUuid(
		String uuid, int start, int end);

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
	public java.util.List<MaintenanceActivity> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MaintenanceActivity>
			orderByComparator);

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
	public java.util.List<MaintenanceActivity> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MaintenanceActivity>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first maintenance activity in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching maintenance activity
	 * @throws NoSuchMaintenanceActivityException if a matching maintenance activity could not be found
	 */
	public MaintenanceActivity findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator
				<MaintenanceActivity> orderByComparator)
		throws NoSuchMaintenanceActivityException;

	/**
	 * Returns the first maintenance activity in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching maintenance activity, or <code>null</code> if a matching maintenance activity could not be found
	 */
	public MaintenanceActivity fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<MaintenanceActivity>
			orderByComparator);

	/**
	 * Returns the last maintenance activity in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching maintenance activity
	 * @throws NoSuchMaintenanceActivityException if a matching maintenance activity could not be found
	 */
	public MaintenanceActivity findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator
				<MaintenanceActivity> orderByComparator)
		throws NoSuchMaintenanceActivityException;

	/**
	 * Returns the last maintenance activity in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching maintenance activity, or <code>null</code> if a matching maintenance activity could not be found
	 */
	public MaintenanceActivity fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<MaintenanceActivity>
			orderByComparator);

	/**
	 * Returns the maintenance activities before and after the current maintenance activity in the ordered set where uuid = &#63;.
	 *
	 * @param maintenanceactivityId the primary key of the current maintenance activity
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next maintenance activity
	 * @throws NoSuchMaintenanceActivityException if a maintenance activity with the primary key could not be found
	 */
	public MaintenanceActivity[] findByUuid_PrevAndNext(
			long maintenanceactivityId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator
				<MaintenanceActivity> orderByComparator)
		throws NoSuchMaintenanceActivityException;

	/**
	 * Removes all the maintenance activities where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of maintenance activities where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching maintenance activities
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns all the maintenance activities where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching maintenance activities
	 */
	public java.util.List<MaintenanceActivity> findByUuid_C(
		String uuid, long companyId);

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
	public java.util.List<MaintenanceActivity> findByUuid_C(
		String uuid, long companyId, int start, int end);

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
	public java.util.List<MaintenanceActivity> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MaintenanceActivity>
			orderByComparator);

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
	public java.util.List<MaintenanceActivity> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MaintenanceActivity>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first maintenance activity in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching maintenance activity
	 * @throws NoSuchMaintenanceActivityException if a matching maintenance activity could not be found
	 */
	public MaintenanceActivity findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator
				<MaintenanceActivity> orderByComparator)
		throws NoSuchMaintenanceActivityException;

	/**
	 * Returns the first maintenance activity in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching maintenance activity, or <code>null</code> if a matching maintenance activity could not be found
	 */
	public MaintenanceActivity fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<MaintenanceActivity>
			orderByComparator);

	/**
	 * Returns the last maintenance activity in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching maintenance activity
	 * @throws NoSuchMaintenanceActivityException if a matching maintenance activity could not be found
	 */
	public MaintenanceActivity findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator
				<MaintenanceActivity> orderByComparator)
		throws NoSuchMaintenanceActivityException;

	/**
	 * Returns the last maintenance activity in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching maintenance activity, or <code>null</code> if a matching maintenance activity could not be found
	 */
	public MaintenanceActivity fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<MaintenanceActivity>
			orderByComparator);

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
	public MaintenanceActivity[] findByUuid_C_PrevAndNext(
			long maintenanceactivityId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator
				<MaintenanceActivity> orderByComparator)
		throws NoSuchMaintenanceActivityException;

	/**
	 * Removes all the maintenance activities where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of maintenance activities where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching maintenance activities
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns all the maintenance activities where status = &#63;.
	 *
	 * @param status the status
	 * @return the matching maintenance activities
	 */
	public java.util.List<MaintenanceActivity> findByStatus(String status);

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
	public java.util.List<MaintenanceActivity> findByStatus(
		String status, int start, int end);

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
	public java.util.List<MaintenanceActivity> findByStatus(
		String status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MaintenanceActivity>
			orderByComparator);

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
	public java.util.List<MaintenanceActivity> findByStatus(
		String status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MaintenanceActivity>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first maintenance activity in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching maintenance activity
	 * @throws NoSuchMaintenanceActivityException if a matching maintenance activity could not be found
	 */
	public MaintenanceActivity findByStatus_First(
			String status,
			com.liferay.portal.kernel.util.OrderByComparator
				<MaintenanceActivity> orderByComparator)
		throws NoSuchMaintenanceActivityException;

	/**
	 * Returns the first maintenance activity in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching maintenance activity, or <code>null</code> if a matching maintenance activity could not be found
	 */
	public MaintenanceActivity fetchByStatus_First(
		String status,
		com.liferay.portal.kernel.util.OrderByComparator<MaintenanceActivity>
			orderByComparator);

	/**
	 * Returns the last maintenance activity in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching maintenance activity
	 * @throws NoSuchMaintenanceActivityException if a matching maintenance activity could not be found
	 */
	public MaintenanceActivity findByStatus_Last(
			String status,
			com.liferay.portal.kernel.util.OrderByComparator
				<MaintenanceActivity> orderByComparator)
		throws NoSuchMaintenanceActivityException;

	/**
	 * Returns the last maintenance activity in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching maintenance activity, or <code>null</code> if a matching maintenance activity could not be found
	 */
	public MaintenanceActivity fetchByStatus_Last(
		String status,
		com.liferay.portal.kernel.util.OrderByComparator<MaintenanceActivity>
			orderByComparator);

	/**
	 * Returns the maintenance activities before and after the current maintenance activity in the ordered set where status = &#63;.
	 *
	 * @param maintenanceactivityId the primary key of the current maintenance activity
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next maintenance activity
	 * @throws NoSuchMaintenanceActivityException if a maintenance activity with the primary key could not be found
	 */
	public MaintenanceActivity[] findByStatus_PrevAndNext(
			long maintenanceactivityId, String status,
			com.liferay.portal.kernel.util.OrderByComparator
				<MaintenanceActivity> orderByComparator)
		throws NoSuchMaintenanceActivityException;

	/**
	 * Removes all the maintenance activities where status = &#63; from the database.
	 *
	 * @param status the status
	 */
	public void removeByStatus(String status);

	/**
	 * Returns the number of maintenance activities where status = &#63;.
	 *
	 * @param status the status
	 * @return the number of matching maintenance activities
	 */
	public int countByStatus(String status);

	/**
	 * Caches the maintenance activity in the entity cache if it is enabled.
	 *
	 * @param maintenanceActivity the maintenance activity
	 */
	public void cacheResult(MaintenanceActivity maintenanceActivity);

	/**
	 * Caches the maintenance activities in the entity cache if it is enabled.
	 *
	 * @param maintenanceActivities the maintenance activities
	 */
	public void cacheResult(
		java.util.List<MaintenanceActivity> maintenanceActivities);

	/**
	 * Creates a new maintenance activity with the primary key. Does not add the maintenance activity to the database.
	 *
	 * @param maintenanceactivityId the primary key for the new maintenance activity
	 * @return the new maintenance activity
	 */
	public MaintenanceActivity create(long maintenanceactivityId);

	/**
	 * Removes the maintenance activity with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param maintenanceactivityId the primary key of the maintenance activity
	 * @return the maintenance activity that was removed
	 * @throws NoSuchMaintenanceActivityException if a maintenance activity with the primary key could not be found
	 */
	public MaintenanceActivity remove(long maintenanceactivityId)
		throws NoSuchMaintenanceActivityException;

	public MaintenanceActivity updateImpl(
		MaintenanceActivity maintenanceActivity);

	/**
	 * Returns the maintenance activity with the primary key or throws a <code>NoSuchMaintenanceActivityException</code> if it could not be found.
	 *
	 * @param maintenanceactivityId the primary key of the maintenance activity
	 * @return the maintenance activity
	 * @throws NoSuchMaintenanceActivityException if a maintenance activity with the primary key could not be found
	 */
	public MaintenanceActivity findByPrimaryKey(long maintenanceactivityId)
		throws NoSuchMaintenanceActivityException;

	/**
	 * Returns the maintenance activity with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param maintenanceactivityId the primary key of the maintenance activity
	 * @return the maintenance activity, or <code>null</code> if a maintenance activity with the primary key could not be found
	 */
	public MaintenanceActivity fetchByPrimaryKey(long maintenanceactivityId);

	/**
	 * Returns all the maintenance activities.
	 *
	 * @return the maintenance activities
	 */
	public java.util.List<MaintenanceActivity> findAll();

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
	public java.util.List<MaintenanceActivity> findAll(int start, int end);

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
	public java.util.List<MaintenanceActivity> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MaintenanceActivity>
			orderByComparator);

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
	public java.util.List<MaintenanceActivity> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MaintenanceActivity>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the maintenance activities from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of maintenance activities.
	 *
	 * @return the number of maintenance activities
	 */
	public int countAll();

}