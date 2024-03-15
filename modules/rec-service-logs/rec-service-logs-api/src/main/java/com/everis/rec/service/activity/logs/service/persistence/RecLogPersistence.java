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

import com.everis.rec.service.activity.logs.exception.NoSuchRecLogException;
import com.everis.rec.service.activity.logs.model.RecLog;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the rec log service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RecLogUtil
 * @generated
 */
@ProviderType
public interface RecLogPersistence extends BasePersistence<RecLog> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link RecLogUtil} to access the rec log persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the rec logs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching rec logs
	 */
	public java.util.List<RecLog> findByUuid(String uuid);

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
	public java.util.List<RecLog> findByUuid(String uuid, int start, int end);

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
	public java.util.List<RecLog> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RecLog>
			orderByComparator);

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
	public java.util.List<RecLog> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RecLog>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first rec log in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching rec log
	 * @throws NoSuchRecLogException if a matching rec log could not be found
	 */
	public RecLog findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<RecLog>
				orderByComparator)
		throws NoSuchRecLogException;

	/**
	 * Returns the first rec log in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching rec log, or <code>null</code> if a matching rec log could not be found
	 */
	public RecLog fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<RecLog>
			orderByComparator);

	/**
	 * Returns the last rec log in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching rec log
	 * @throws NoSuchRecLogException if a matching rec log could not be found
	 */
	public RecLog findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<RecLog>
				orderByComparator)
		throws NoSuchRecLogException;

	/**
	 * Returns the last rec log in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching rec log, or <code>null</code> if a matching rec log could not be found
	 */
	public RecLog fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<RecLog>
			orderByComparator);

	/**
	 * Returns the rec logs before and after the current rec log in the ordered set where uuid = &#63;.
	 *
	 * @param activityLogId the primary key of the current rec log
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next rec log
	 * @throws NoSuchRecLogException if a rec log with the primary key could not be found
	 */
	public RecLog[] findByUuid_PrevAndNext(
			long activityLogId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<RecLog>
				orderByComparator)
		throws NoSuchRecLogException;

	/**
	 * Removes all the rec logs where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of rec logs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching rec logs
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the rec log where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchRecLogException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching rec log
	 * @throws NoSuchRecLogException if a matching rec log could not be found
	 */
	public RecLog findByUUID_G(String uuid, long groupId)
		throws NoSuchRecLogException;

	/**
	 * Returns the rec log where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching rec log, or <code>null</code> if a matching rec log could not be found
	 */
	public RecLog fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the rec log where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching rec log, or <code>null</code> if a matching rec log could not be found
	 */
	public RecLog fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache);

	/**
	 * Removes the rec log where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the rec log that was removed
	 */
	public RecLog removeByUUID_G(String uuid, long groupId)
		throws NoSuchRecLogException;

	/**
	 * Returns the number of rec logs where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching rec logs
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the rec logs where classPK = &#63;.
	 *
	 * @param classPK the class pk
	 * @return the matching rec logs
	 */
	public java.util.List<RecLog> findByClassPK(Long classPK);

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
	public java.util.List<RecLog> findByClassPK(
		Long classPK, int start, int end);

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
	public java.util.List<RecLog> findByClassPK(
		Long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RecLog>
			orderByComparator);

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
	public java.util.List<RecLog> findByClassPK(
		Long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RecLog>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first rec log in the ordered set where classPK = &#63;.
	 *
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching rec log
	 * @throws NoSuchRecLogException if a matching rec log could not be found
	 */
	public RecLog findByClassPK_First(
			Long classPK,
			com.liferay.portal.kernel.util.OrderByComparator<RecLog>
				orderByComparator)
		throws NoSuchRecLogException;

	/**
	 * Returns the first rec log in the ordered set where classPK = &#63;.
	 *
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching rec log, or <code>null</code> if a matching rec log could not be found
	 */
	public RecLog fetchByClassPK_First(
		Long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<RecLog>
			orderByComparator);

	/**
	 * Returns the last rec log in the ordered set where classPK = &#63;.
	 *
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching rec log
	 * @throws NoSuchRecLogException if a matching rec log could not be found
	 */
	public RecLog findByClassPK_Last(
			Long classPK,
			com.liferay.portal.kernel.util.OrderByComparator<RecLog>
				orderByComparator)
		throws NoSuchRecLogException;

	/**
	 * Returns the last rec log in the ordered set where classPK = &#63;.
	 *
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching rec log, or <code>null</code> if a matching rec log could not be found
	 */
	public RecLog fetchByClassPK_Last(
		Long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<RecLog>
			orderByComparator);

	/**
	 * Returns the rec logs before and after the current rec log in the ordered set where classPK = &#63;.
	 *
	 * @param activityLogId the primary key of the current rec log
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next rec log
	 * @throws NoSuchRecLogException if a rec log with the primary key could not be found
	 */
	public RecLog[] findByClassPK_PrevAndNext(
			long activityLogId, Long classPK,
			com.liferay.portal.kernel.util.OrderByComparator<RecLog>
				orderByComparator)
		throws NoSuchRecLogException;

	/**
	 * Removes all the rec logs where classPK = &#63; from the database.
	 *
	 * @param classPK the class pk
	 */
	public void removeByClassPK(Long classPK);

	/**
	 * Returns the number of rec logs where classPK = &#63;.
	 *
	 * @param classPK the class pk
	 * @return the number of matching rec logs
	 */
	public int countByClassPK(Long classPK);

	/**
	 * Caches the rec log in the entity cache if it is enabled.
	 *
	 * @param recLog the rec log
	 */
	public void cacheResult(RecLog recLog);

	/**
	 * Caches the rec logs in the entity cache if it is enabled.
	 *
	 * @param recLogs the rec logs
	 */
	public void cacheResult(java.util.List<RecLog> recLogs);

	/**
	 * Creates a new rec log with the primary key. Does not add the rec log to the database.
	 *
	 * @param activityLogId the primary key for the new rec log
	 * @return the new rec log
	 */
	public RecLog create(long activityLogId);

	/**
	 * Removes the rec log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param activityLogId the primary key of the rec log
	 * @return the rec log that was removed
	 * @throws NoSuchRecLogException if a rec log with the primary key could not be found
	 */
	public RecLog remove(long activityLogId) throws NoSuchRecLogException;

	public RecLog updateImpl(RecLog recLog);

	/**
	 * Returns the rec log with the primary key or throws a <code>NoSuchRecLogException</code> if it could not be found.
	 *
	 * @param activityLogId the primary key of the rec log
	 * @return the rec log
	 * @throws NoSuchRecLogException if a rec log with the primary key could not be found
	 */
	public RecLog findByPrimaryKey(long activityLogId)
		throws NoSuchRecLogException;

	/**
	 * Returns the rec log with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param activityLogId the primary key of the rec log
	 * @return the rec log, or <code>null</code> if a rec log with the primary key could not be found
	 */
	public RecLog fetchByPrimaryKey(long activityLogId);

	/**
	 * Returns all the rec logs.
	 *
	 * @return the rec logs
	 */
	public java.util.List<RecLog> findAll();

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
	public java.util.List<RecLog> findAll(int start, int end);

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
	public java.util.List<RecLog> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RecLog>
			orderByComparator);

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
	public java.util.List<RecLog> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RecLog>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the rec logs from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of rec logs.
	 *
	 * @return the number of rec logs
	 */
	public int countAll();

}