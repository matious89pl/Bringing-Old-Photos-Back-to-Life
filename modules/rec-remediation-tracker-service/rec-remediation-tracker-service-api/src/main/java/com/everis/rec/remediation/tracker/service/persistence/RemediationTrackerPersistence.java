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

import com.everis.rec.remediation.tracker.exception.NoSuchRemediationTrackerException;
import com.everis.rec.remediation.tracker.model.RemediationTracker;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the remediation tracker service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RemediationTrackerUtil
 * @generated
 */
@ProviderType
public interface RemediationTrackerPersistence
	extends BasePersistence<RemediationTracker> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link RemediationTrackerUtil} to access the remediation tracker persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the remediation trackers where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching remediation trackers
	 */
	public java.util.List<RemediationTracker> findByUuid(String uuid);

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
	public java.util.List<RemediationTracker> findByUuid(
		String uuid, int start, int end);

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
	public java.util.List<RemediationTracker> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RemediationTracker>
			orderByComparator);

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
	public java.util.List<RemediationTracker> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RemediationTracker>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first remediation tracker in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching remediation tracker
	 * @throws NoSuchRemediationTrackerException if a matching remediation tracker could not be found
	 */
	public RemediationTracker findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<RemediationTracker>
				orderByComparator)
		throws NoSuchRemediationTrackerException;

	/**
	 * Returns the first remediation tracker in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching remediation tracker, or <code>null</code> if a matching remediation tracker could not be found
	 */
	public RemediationTracker fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<RemediationTracker>
			orderByComparator);

	/**
	 * Returns the last remediation tracker in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching remediation tracker
	 * @throws NoSuchRemediationTrackerException if a matching remediation tracker could not be found
	 */
	public RemediationTracker findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<RemediationTracker>
				orderByComparator)
		throws NoSuchRemediationTrackerException;

	/**
	 * Returns the last remediation tracker in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching remediation tracker, or <code>null</code> if a matching remediation tracker could not be found
	 */
	public RemediationTracker fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<RemediationTracker>
			orderByComparator);

	/**
	 * Returns the remediation trackers before and after the current remediation tracker in the ordered set where uuid = &#63;.
	 *
	 * @param remediationTrackerId the primary key of the current remediation tracker
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next remediation tracker
	 * @throws NoSuchRemediationTrackerException if a remediation tracker with the primary key could not be found
	 */
	public RemediationTracker[] findByUuid_PrevAndNext(
			long remediationTrackerId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<RemediationTracker>
				orderByComparator)
		throws NoSuchRemediationTrackerException;

	/**
	 * Removes all the remediation trackers where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of remediation trackers where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching remediation trackers
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the remediation tracker where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchRemediationTrackerException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching remediation tracker
	 * @throws NoSuchRemediationTrackerException if a matching remediation tracker could not be found
	 */
	public RemediationTracker findByUUID_G(String uuid, long groupId)
		throws NoSuchRemediationTrackerException;

	/**
	 * Returns the remediation tracker where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching remediation tracker, or <code>null</code> if a matching remediation tracker could not be found
	 */
	public RemediationTracker fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the remediation tracker where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching remediation tracker, or <code>null</code> if a matching remediation tracker could not be found
	 */
	public RemediationTracker fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache);

	/**
	 * Removes the remediation tracker where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the remediation tracker that was removed
	 */
	public RemediationTracker removeByUUID_G(String uuid, long groupId)
		throws NoSuchRemediationTrackerException;

	/**
	 * Returns the number of remediation trackers where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching remediation trackers
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the remediation trackers where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching remediation trackers
	 */
	public java.util.List<RemediationTracker> findByUuid_C(
		String uuid, long companyId);

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
	public java.util.List<RemediationTracker> findByUuid_C(
		String uuid, long companyId, int start, int end);

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
	public java.util.List<RemediationTracker> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RemediationTracker>
			orderByComparator);

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
	public java.util.List<RemediationTracker> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RemediationTracker>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first remediation tracker in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching remediation tracker
	 * @throws NoSuchRemediationTrackerException if a matching remediation tracker could not be found
	 */
	public RemediationTracker findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<RemediationTracker>
				orderByComparator)
		throws NoSuchRemediationTrackerException;

	/**
	 * Returns the first remediation tracker in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching remediation tracker, or <code>null</code> if a matching remediation tracker could not be found
	 */
	public RemediationTracker fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<RemediationTracker>
			orderByComparator);

	/**
	 * Returns the last remediation tracker in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching remediation tracker
	 * @throws NoSuchRemediationTrackerException if a matching remediation tracker could not be found
	 */
	public RemediationTracker findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<RemediationTracker>
				orderByComparator)
		throws NoSuchRemediationTrackerException;

	/**
	 * Returns the last remediation tracker in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching remediation tracker, or <code>null</code> if a matching remediation tracker could not be found
	 */
	public RemediationTracker fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<RemediationTracker>
			orderByComparator);

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
	public RemediationTracker[] findByUuid_C_PrevAndNext(
			long remediationTrackerId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<RemediationTracker>
				orderByComparator)
		throws NoSuchRemediationTrackerException;

	/**
	 * Removes all the remediation trackers where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of remediation trackers where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching remediation trackers
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns all the remediation trackers where status = &#63;.
	 *
	 * @param status the status
	 * @return the matching remediation trackers
	 */
	public java.util.List<RemediationTracker> findByStatus(String status);

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
	public java.util.List<RemediationTracker> findByStatus(
		String status, int start, int end);

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
	public java.util.List<RemediationTracker> findByStatus(
		String status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RemediationTracker>
			orderByComparator);

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
	public java.util.List<RemediationTracker> findByStatus(
		String status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RemediationTracker>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first remediation tracker in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching remediation tracker
	 * @throws NoSuchRemediationTrackerException if a matching remediation tracker could not be found
	 */
	public RemediationTracker findByStatus_First(
			String status,
			com.liferay.portal.kernel.util.OrderByComparator<RemediationTracker>
				orderByComparator)
		throws NoSuchRemediationTrackerException;

	/**
	 * Returns the first remediation tracker in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching remediation tracker, or <code>null</code> if a matching remediation tracker could not be found
	 */
	public RemediationTracker fetchByStatus_First(
		String status,
		com.liferay.portal.kernel.util.OrderByComparator<RemediationTracker>
			orderByComparator);

	/**
	 * Returns the last remediation tracker in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching remediation tracker
	 * @throws NoSuchRemediationTrackerException if a matching remediation tracker could not be found
	 */
	public RemediationTracker findByStatus_Last(
			String status,
			com.liferay.portal.kernel.util.OrderByComparator<RemediationTracker>
				orderByComparator)
		throws NoSuchRemediationTrackerException;

	/**
	 * Returns the last remediation tracker in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching remediation tracker, or <code>null</code> if a matching remediation tracker could not be found
	 */
	public RemediationTracker fetchByStatus_Last(
		String status,
		com.liferay.portal.kernel.util.OrderByComparator<RemediationTracker>
			orderByComparator);

	/**
	 * Returns the remediation trackers before and after the current remediation tracker in the ordered set where status = &#63;.
	 *
	 * @param remediationTrackerId the primary key of the current remediation tracker
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next remediation tracker
	 * @throws NoSuchRemediationTrackerException if a remediation tracker with the primary key could not be found
	 */
	public RemediationTracker[] findByStatus_PrevAndNext(
			long remediationTrackerId, String status,
			com.liferay.portal.kernel.util.OrderByComparator<RemediationTracker>
				orderByComparator)
		throws NoSuchRemediationTrackerException;

	/**
	 * Removes all the remediation trackers where status = &#63; from the database.
	 *
	 * @param status the status
	 */
	public void removeByStatus(String status);

	/**
	 * Returns the number of remediation trackers where status = &#63;.
	 *
	 * @param status the status
	 * @return the number of matching remediation trackers
	 */
	public int countByStatus(String status);

	/**
	 * Caches the remediation tracker in the entity cache if it is enabled.
	 *
	 * @param remediationTracker the remediation tracker
	 */
	public void cacheResult(RemediationTracker remediationTracker);

	/**
	 * Caches the remediation trackers in the entity cache if it is enabled.
	 *
	 * @param remediationTrackers the remediation trackers
	 */
	public void cacheResult(
		java.util.List<RemediationTracker> remediationTrackers);

	/**
	 * Creates a new remediation tracker with the primary key. Does not add the remediation tracker to the database.
	 *
	 * @param remediationTrackerId the primary key for the new remediation tracker
	 * @return the new remediation tracker
	 */
	public RemediationTracker create(long remediationTrackerId);

	/**
	 * Removes the remediation tracker with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param remediationTrackerId the primary key of the remediation tracker
	 * @return the remediation tracker that was removed
	 * @throws NoSuchRemediationTrackerException if a remediation tracker with the primary key could not be found
	 */
	public RemediationTracker remove(long remediationTrackerId)
		throws NoSuchRemediationTrackerException;

	public RemediationTracker updateImpl(RemediationTracker remediationTracker);

	/**
	 * Returns the remediation tracker with the primary key or throws a <code>NoSuchRemediationTrackerException</code> if it could not be found.
	 *
	 * @param remediationTrackerId the primary key of the remediation tracker
	 * @return the remediation tracker
	 * @throws NoSuchRemediationTrackerException if a remediation tracker with the primary key could not be found
	 */
	public RemediationTracker findByPrimaryKey(long remediationTrackerId)
		throws NoSuchRemediationTrackerException;

	/**
	 * Returns the remediation tracker with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param remediationTrackerId the primary key of the remediation tracker
	 * @return the remediation tracker, or <code>null</code> if a remediation tracker with the primary key could not be found
	 */
	public RemediationTracker fetchByPrimaryKey(long remediationTrackerId);

	/**
	 * Returns all the remediation trackers.
	 *
	 * @return the remediation trackers
	 */
	public java.util.List<RemediationTracker> findAll();

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
	public java.util.List<RemediationTracker> findAll(int start, int end);

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
	public java.util.List<RemediationTracker> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RemediationTracker>
			orderByComparator);

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
	public java.util.List<RemediationTracker> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RemediationTracker>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the remediation trackers from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of remediation trackers.
	 *
	 * @return the number of remediation trackers
	 */
	public int countAll();

}