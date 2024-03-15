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

package rec.customnotification.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

import rec.customnotification.exception.NoSuchNotificationRpaException;
import rec.customnotification.model.NotificationRpa;

/**
 * The persistence interface for the notification rpa service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see NotificationRpaUtil
 * @generated
 */
@ProviderType
public interface NotificationRpaPersistence
	extends BasePersistence<NotificationRpa> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link NotificationRpaUtil} to access the notification rpa persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the notification rpas where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching notification rpas
	 */
	public java.util.List<NotificationRpa> findByUuid(String uuid);

	/**
	 * Returns a range of all the notification rpas where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @return the range of matching notification rpas
	 */
	public java.util.List<NotificationRpa> findByUuid(
		String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the notification rpas where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching notification rpas
	 */
	public java.util.List<NotificationRpa> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
			orderByComparator);

	/**
	 * Returns an ordered range of all the notification rpas where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching notification rpas
	 */
	public java.util.List<NotificationRpa> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first notification rpa in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification rpa
	 * @throws NoSuchNotificationRpaException if a matching notification rpa could not be found
	 */
	public NotificationRpa findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
				orderByComparator)
		throws NoSuchNotificationRpaException;

	/**
	 * Returns the first notification rpa in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	public NotificationRpa fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
			orderByComparator);

	/**
	 * Returns the last notification rpa in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification rpa
	 * @throws NoSuchNotificationRpaException if a matching notification rpa could not be found
	 */
	public NotificationRpa findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
				orderByComparator)
		throws NoSuchNotificationRpaException;

	/**
	 * Returns the last notification rpa in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	public NotificationRpa fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
			orderByComparator);

	/**
	 * Returns the notification rpas before and after the current notification rpa in the ordered set where uuid = &#63;.
	 *
	 * @param customNotificationId the primary key of the current notification rpa
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next notification rpa
	 * @throws NoSuchNotificationRpaException if a notification rpa with the primary key could not be found
	 */
	public NotificationRpa[] findByUuid_PrevAndNext(
			long customNotificationId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
				orderByComparator)
		throws NoSuchNotificationRpaException;

	/**
	 * Removes all the notification rpas where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of notification rpas where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching notification rpas
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the notification rpa where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchNotificationRpaException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching notification rpa
	 * @throws NoSuchNotificationRpaException if a matching notification rpa could not be found
	 */
	public NotificationRpa findByUUID_G(String uuid, long groupId)
		throws NoSuchNotificationRpaException;

	/**
	 * Returns the notification rpa where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	public NotificationRpa fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the notification rpa where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	public NotificationRpa fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache);

	/**
	 * Removes the notification rpa where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the notification rpa that was removed
	 */
	public NotificationRpa removeByUUID_G(String uuid, long groupId)
		throws NoSuchNotificationRpaException;

	/**
	 * Returns the number of notification rpas where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching notification rpas
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the notification rpas where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching notification rpas
	 */
	public java.util.List<NotificationRpa> findByUuid_C(
		String uuid, long companyId);

	/**
	 * Returns a range of all the notification rpas where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @return the range of matching notification rpas
	 */
	public java.util.List<NotificationRpa> findByUuid_C(
		String uuid, long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the notification rpas where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching notification rpas
	 */
	public java.util.List<NotificationRpa> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
			orderByComparator);

	/**
	 * Returns an ordered range of all the notification rpas where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching notification rpas
	 */
	public java.util.List<NotificationRpa> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first notification rpa in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification rpa
	 * @throws NoSuchNotificationRpaException if a matching notification rpa could not be found
	 */
	public NotificationRpa findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
				orderByComparator)
		throws NoSuchNotificationRpaException;

	/**
	 * Returns the first notification rpa in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	public NotificationRpa fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
			orderByComparator);

	/**
	 * Returns the last notification rpa in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification rpa
	 * @throws NoSuchNotificationRpaException if a matching notification rpa could not be found
	 */
	public NotificationRpa findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
				orderByComparator)
		throws NoSuchNotificationRpaException;

	/**
	 * Returns the last notification rpa in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	public NotificationRpa fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
			orderByComparator);

	/**
	 * Returns the notification rpas before and after the current notification rpa in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param customNotificationId the primary key of the current notification rpa
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next notification rpa
	 * @throws NoSuchNotificationRpaException if a notification rpa with the primary key could not be found
	 */
	public NotificationRpa[] findByUuid_C_PrevAndNext(
			long customNotificationId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
				orderByComparator)
		throws NoSuchNotificationRpaException;

	/**
	 * Removes all the notification rpas where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of notification rpas where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching notification rpas
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns all the notification rpas where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching notification rpas
	 */
	public java.util.List<NotificationRpa> findByGroupId(long groupId);

	/**
	 * Returns a range of all the notification rpas where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @return the range of matching notification rpas
	 */
	public java.util.List<NotificationRpa> findByGroupId(
		long groupId, int start, int end);

	/**
	 * Returns an ordered range of all the notification rpas where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching notification rpas
	 */
	public java.util.List<NotificationRpa> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
			orderByComparator);

	/**
	 * Returns an ordered range of all the notification rpas where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching notification rpas
	 */
	public java.util.List<NotificationRpa> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first notification rpa in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification rpa
	 * @throws NoSuchNotificationRpaException if a matching notification rpa could not be found
	 */
	public NotificationRpa findByGroupId_First(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
				orderByComparator)
		throws NoSuchNotificationRpaException;

	/**
	 * Returns the first notification rpa in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	public NotificationRpa fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
			orderByComparator);

	/**
	 * Returns the last notification rpa in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification rpa
	 * @throws NoSuchNotificationRpaException if a matching notification rpa could not be found
	 */
	public NotificationRpa findByGroupId_Last(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
				orderByComparator)
		throws NoSuchNotificationRpaException;

	/**
	 * Returns the last notification rpa in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	public NotificationRpa fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
			orderByComparator);

	/**
	 * Returns the notification rpas before and after the current notification rpa in the ordered set where groupId = &#63;.
	 *
	 * @param customNotificationId the primary key of the current notification rpa
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next notification rpa
	 * @throws NoSuchNotificationRpaException if a notification rpa with the primary key could not be found
	 */
	public NotificationRpa[] findByGroupId_PrevAndNext(
			long customNotificationId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
				orderByComparator)
		throws NoSuchNotificationRpaException;

	/**
	 * Removes all the notification rpas where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public void removeByGroupId(long groupId);

	/**
	 * Returns the number of notification rpas where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching notification rpas
	 */
	public int countByGroupId(long groupId);

	/**
	 * Returns all the notification rpas where groupId = &#63; and plidPage = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @return the matching notification rpas
	 */
	public java.util.List<NotificationRpa> findByGroupIdPlidPage(
		long groupId, long plidPage);

	/**
	 * Returns a range of all the notification rpas where groupId = &#63; and plidPage = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @return the range of matching notification rpas
	 */
	public java.util.List<NotificationRpa> findByGroupIdPlidPage(
		long groupId, long plidPage, int start, int end);

	/**
	 * Returns an ordered range of all the notification rpas where groupId = &#63; and plidPage = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching notification rpas
	 */
	public java.util.List<NotificationRpa> findByGroupIdPlidPage(
		long groupId, long plidPage, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
			orderByComparator);

	/**
	 * Returns an ordered range of all the notification rpas where groupId = &#63; and plidPage = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching notification rpas
	 */
	public java.util.List<NotificationRpa> findByGroupIdPlidPage(
		long groupId, long plidPage, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first notification rpa in the ordered set where groupId = &#63; and plidPage = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification rpa
	 * @throws NoSuchNotificationRpaException if a matching notification rpa could not be found
	 */
	public NotificationRpa findByGroupIdPlidPage_First(
			long groupId, long plidPage,
			com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
				orderByComparator)
		throws NoSuchNotificationRpaException;

	/**
	 * Returns the first notification rpa in the ordered set where groupId = &#63; and plidPage = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	public NotificationRpa fetchByGroupIdPlidPage_First(
		long groupId, long plidPage,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
			orderByComparator);

	/**
	 * Returns the last notification rpa in the ordered set where groupId = &#63; and plidPage = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification rpa
	 * @throws NoSuchNotificationRpaException if a matching notification rpa could not be found
	 */
	public NotificationRpa findByGroupIdPlidPage_Last(
			long groupId, long plidPage,
			com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
				orderByComparator)
		throws NoSuchNotificationRpaException;

	/**
	 * Returns the last notification rpa in the ordered set where groupId = &#63; and plidPage = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	public NotificationRpa fetchByGroupIdPlidPage_Last(
		long groupId, long plidPage,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
			orderByComparator);

	/**
	 * Returns the notification rpas before and after the current notification rpa in the ordered set where groupId = &#63; and plidPage = &#63;.
	 *
	 * @param customNotificationId the primary key of the current notification rpa
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next notification rpa
	 * @throws NoSuchNotificationRpaException if a notification rpa with the primary key could not be found
	 */
	public NotificationRpa[] findByGroupIdPlidPage_PrevAndNext(
			long customNotificationId, long groupId, long plidPage,
			com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
				orderByComparator)
		throws NoSuchNotificationRpaException;

	/**
	 * Removes all the notification rpas where groupId = &#63; and plidPage = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 */
	public void removeByGroupIdPlidPage(long groupId, long plidPage);

	/**
	 * Returns the number of notification rpas where groupId = &#63; and plidPage = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @return the number of matching notification rpas
	 */
	public int countByGroupIdPlidPage(long groupId, long plidPage);

	/**
	 * Returns all the notification rpas where groupId = &#63; and plidPage = &#63; and notificationTitle = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationTitle the notification title
	 * @return the matching notification rpas
	 */
	public java.util.List<NotificationRpa> findByNotificationTitle(
		long groupId, long plidPage, String notificationTitle);

	/**
	 * Returns a range of all the notification rpas where groupId = &#63; and plidPage = &#63; and notificationTitle = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationTitle the notification title
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @return the range of matching notification rpas
	 */
	public java.util.List<NotificationRpa> findByNotificationTitle(
		long groupId, long plidPage, String notificationTitle, int start,
		int end);

	/**
	 * Returns an ordered range of all the notification rpas where groupId = &#63; and plidPage = &#63; and notificationTitle = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationTitle the notification title
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching notification rpas
	 */
	public java.util.List<NotificationRpa> findByNotificationTitle(
		long groupId, long plidPage, String notificationTitle, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
			orderByComparator);

	/**
	 * Returns an ordered range of all the notification rpas where groupId = &#63; and plidPage = &#63; and notificationTitle = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationTitle the notification title
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching notification rpas
	 */
	public java.util.List<NotificationRpa> findByNotificationTitle(
		long groupId, long plidPage, String notificationTitle, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first notification rpa in the ordered set where groupId = &#63; and plidPage = &#63; and notificationTitle = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationTitle the notification title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification rpa
	 * @throws NoSuchNotificationRpaException if a matching notification rpa could not be found
	 */
	public NotificationRpa findByNotificationTitle_First(
			long groupId, long plidPage, String notificationTitle,
			com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
				orderByComparator)
		throws NoSuchNotificationRpaException;

	/**
	 * Returns the first notification rpa in the ordered set where groupId = &#63; and plidPage = &#63; and notificationTitle = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationTitle the notification title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	public NotificationRpa fetchByNotificationTitle_First(
		long groupId, long plidPage, String notificationTitle,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
			orderByComparator);

	/**
	 * Returns the last notification rpa in the ordered set where groupId = &#63; and plidPage = &#63; and notificationTitle = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationTitle the notification title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification rpa
	 * @throws NoSuchNotificationRpaException if a matching notification rpa could not be found
	 */
	public NotificationRpa findByNotificationTitle_Last(
			long groupId, long plidPage, String notificationTitle,
			com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
				orderByComparator)
		throws NoSuchNotificationRpaException;

	/**
	 * Returns the last notification rpa in the ordered set where groupId = &#63; and plidPage = &#63; and notificationTitle = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationTitle the notification title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	public NotificationRpa fetchByNotificationTitle_Last(
		long groupId, long plidPage, String notificationTitle,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
			orderByComparator);

	/**
	 * Returns the notification rpas before and after the current notification rpa in the ordered set where groupId = &#63; and plidPage = &#63; and notificationTitle = &#63;.
	 *
	 * @param customNotificationId the primary key of the current notification rpa
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationTitle the notification title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next notification rpa
	 * @throws NoSuchNotificationRpaException if a notification rpa with the primary key could not be found
	 */
	public NotificationRpa[] findByNotificationTitle_PrevAndNext(
			long customNotificationId, long groupId, long plidPage,
			String notificationTitle,
			com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
				orderByComparator)
		throws NoSuchNotificationRpaException;

	/**
	 * Removes all the notification rpas where groupId = &#63; and plidPage = &#63; and notificationTitle = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationTitle the notification title
	 */
	public void removeByNotificationTitle(
		long groupId, long plidPage, String notificationTitle);

	/**
	 * Returns the number of notification rpas where groupId = &#63; and plidPage = &#63; and notificationTitle = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationTitle the notification title
	 * @return the number of matching notification rpas
	 */
	public int countByNotificationTitle(
		long groupId, long plidPage, String notificationTitle);

	/**
	 * Returns all the notification rpas where groupId = &#63; and plidPage = &#63; and notificationBody = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationBody the notification body
	 * @return the matching notification rpas
	 */
	public java.util.List<NotificationRpa> findByNotificationBody(
		long groupId, long plidPage, String notificationBody);

	/**
	 * Returns a range of all the notification rpas where groupId = &#63; and plidPage = &#63; and notificationBody = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationBody the notification body
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @return the range of matching notification rpas
	 */
	public java.util.List<NotificationRpa> findByNotificationBody(
		long groupId, long plidPage, String notificationBody, int start,
		int end);

	/**
	 * Returns an ordered range of all the notification rpas where groupId = &#63; and plidPage = &#63; and notificationBody = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationBody the notification body
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching notification rpas
	 */
	public java.util.List<NotificationRpa> findByNotificationBody(
		long groupId, long plidPage, String notificationBody, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
			orderByComparator);

	/**
	 * Returns an ordered range of all the notification rpas where groupId = &#63; and plidPage = &#63; and notificationBody = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationBody the notification body
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching notification rpas
	 */
	public java.util.List<NotificationRpa> findByNotificationBody(
		long groupId, long plidPage, String notificationBody, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first notification rpa in the ordered set where groupId = &#63; and plidPage = &#63; and notificationBody = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationBody the notification body
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification rpa
	 * @throws NoSuchNotificationRpaException if a matching notification rpa could not be found
	 */
	public NotificationRpa findByNotificationBody_First(
			long groupId, long plidPage, String notificationBody,
			com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
				orderByComparator)
		throws NoSuchNotificationRpaException;

	/**
	 * Returns the first notification rpa in the ordered set where groupId = &#63; and plidPage = &#63; and notificationBody = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationBody the notification body
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	public NotificationRpa fetchByNotificationBody_First(
		long groupId, long plidPage, String notificationBody,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
			orderByComparator);

	/**
	 * Returns the last notification rpa in the ordered set where groupId = &#63; and plidPage = &#63; and notificationBody = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationBody the notification body
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification rpa
	 * @throws NoSuchNotificationRpaException if a matching notification rpa could not be found
	 */
	public NotificationRpa findByNotificationBody_Last(
			long groupId, long plidPage, String notificationBody,
			com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
				orderByComparator)
		throws NoSuchNotificationRpaException;

	/**
	 * Returns the last notification rpa in the ordered set where groupId = &#63; and plidPage = &#63; and notificationBody = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationBody the notification body
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	public NotificationRpa fetchByNotificationBody_Last(
		long groupId, long plidPage, String notificationBody,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
			orderByComparator);

	/**
	 * Returns the notification rpas before and after the current notification rpa in the ordered set where groupId = &#63; and plidPage = &#63; and notificationBody = &#63;.
	 *
	 * @param customNotificationId the primary key of the current notification rpa
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationBody the notification body
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next notification rpa
	 * @throws NoSuchNotificationRpaException if a notification rpa with the primary key could not be found
	 */
	public NotificationRpa[] findByNotificationBody_PrevAndNext(
			long customNotificationId, long groupId, long plidPage,
			String notificationBody,
			com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
				orderByComparator)
		throws NoSuchNotificationRpaException;

	/**
	 * Removes all the notification rpas where groupId = &#63; and plidPage = &#63; and notificationBody = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationBody the notification body
	 */
	public void removeByNotificationBody(
		long groupId, long plidPage, String notificationBody);

	/**
	 * Returns the number of notification rpas where groupId = &#63; and plidPage = &#63; and notificationBody = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationBody the notification body
	 * @return the number of matching notification rpas
	 */
	public int countByNotificationBody(
		long groupId, long plidPage, String notificationBody);

	/**
	 * Returns all the notification rpas where groupId = &#63; and plidPage = &#63; and createDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param createDate the create date
	 * @return the matching notification rpas
	 */
	public java.util.List<NotificationRpa> findByCreateDate(
		long groupId, long plidPage, Date createDate);

	/**
	 * Returns a range of all the notification rpas where groupId = &#63; and plidPage = &#63; and createDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param createDate the create date
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @return the range of matching notification rpas
	 */
	public java.util.List<NotificationRpa> findByCreateDate(
		long groupId, long plidPage, Date createDate, int start, int end);

	/**
	 * Returns an ordered range of all the notification rpas where groupId = &#63; and plidPage = &#63; and createDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param createDate the create date
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching notification rpas
	 */
	public java.util.List<NotificationRpa> findByCreateDate(
		long groupId, long plidPage, Date createDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
			orderByComparator);

	/**
	 * Returns an ordered range of all the notification rpas where groupId = &#63; and plidPage = &#63; and createDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param createDate the create date
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching notification rpas
	 */
	public java.util.List<NotificationRpa> findByCreateDate(
		long groupId, long plidPage, Date createDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first notification rpa in the ordered set where groupId = &#63; and plidPage = &#63; and createDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification rpa
	 * @throws NoSuchNotificationRpaException if a matching notification rpa could not be found
	 */
	public NotificationRpa findByCreateDate_First(
			long groupId, long plidPage, Date createDate,
			com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
				orderByComparator)
		throws NoSuchNotificationRpaException;

	/**
	 * Returns the first notification rpa in the ordered set where groupId = &#63; and plidPage = &#63; and createDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	public NotificationRpa fetchByCreateDate_First(
		long groupId, long plidPage, Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
			orderByComparator);

	/**
	 * Returns the last notification rpa in the ordered set where groupId = &#63; and plidPage = &#63; and createDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification rpa
	 * @throws NoSuchNotificationRpaException if a matching notification rpa could not be found
	 */
	public NotificationRpa findByCreateDate_Last(
			long groupId, long plidPage, Date createDate,
			com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
				orderByComparator)
		throws NoSuchNotificationRpaException;

	/**
	 * Returns the last notification rpa in the ordered set where groupId = &#63; and plidPage = &#63; and createDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	public NotificationRpa fetchByCreateDate_Last(
		long groupId, long plidPage, Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
			orderByComparator);

	/**
	 * Returns the notification rpas before and after the current notification rpa in the ordered set where groupId = &#63; and plidPage = &#63; and createDate = &#63;.
	 *
	 * @param customNotificationId the primary key of the current notification rpa
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next notification rpa
	 * @throws NoSuchNotificationRpaException if a notification rpa with the primary key could not be found
	 */
	public NotificationRpa[] findByCreateDate_PrevAndNext(
			long customNotificationId, long groupId, long plidPage,
			Date createDate,
			com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
				orderByComparator)
		throws NoSuchNotificationRpaException;

	/**
	 * Removes all the notification rpas where groupId = &#63; and plidPage = &#63; and createDate = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param createDate the create date
	 */
	public void removeByCreateDate(
		long groupId, long plidPage, Date createDate);

	/**
	 * Returns the number of notification rpas where groupId = &#63; and plidPage = &#63; and createDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param createDate the create date
	 * @return the number of matching notification rpas
	 */
	public int countByCreateDate(long groupId, long plidPage, Date createDate);

	/**
	 * Returns all the notification rpas where groupId = &#63; and plidPage = &#63; and targetName = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param targetName the target name
	 * @return the matching notification rpas
	 */
	public java.util.List<NotificationRpa> findByTargetName(
		long groupId, long plidPage, String targetName);

	/**
	 * Returns a range of all the notification rpas where groupId = &#63; and plidPage = &#63; and targetName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param targetName the target name
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @return the range of matching notification rpas
	 */
	public java.util.List<NotificationRpa> findByTargetName(
		long groupId, long plidPage, String targetName, int start, int end);

	/**
	 * Returns an ordered range of all the notification rpas where groupId = &#63; and plidPage = &#63; and targetName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param targetName the target name
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching notification rpas
	 */
	public java.util.List<NotificationRpa> findByTargetName(
		long groupId, long plidPage, String targetName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
			orderByComparator);

	/**
	 * Returns an ordered range of all the notification rpas where groupId = &#63; and plidPage = &#63; and targetName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param targetName the target name
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching notification rpas
	 */
	public java.util.List<NotificationRpa> findByTargetName(
		long groupId, long plidPage, String targetName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first notification rpa in the ordered set where groupId = &#63; and plidPage = &#63; and targetName = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param targetName the target name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification rpa
	 * @throws NoSuchNotificationRpaException if a matching notification rpa could not be found
	 */
	public NotificationRpa findByTargetName_First(
			long groupId, long plidPage, String targetName,
			com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
				orderByComparator)
		throws NoSuchNotificationRpaException;

	/**
	 * Returns the first notification rpa in the ordered set where groupId = &#63; and plidPage = &#63; and targetName = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param targetName the target name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	public NotificationRpa fetchByTargetName_First(
		long groupId, long plidPage, String targetName,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
			orderByComparator);

	/**
	 * Returns the last notification rpa in the ordered set where groupId = &#63; and plidPage = &#63; and targetName = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param targetName the target name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification rpa
	 * @throws NoSuchNotificationRpaException if a matching notification rpa could not be found
	 */
	public NotificationRpa findByTargetName_Last(
			long groupId, long plidPage, String targetName,
			com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
				orderByComparator)
		throws NoSuchNotificationRpaException;

	/**
	 * Returns the last notification rpa in the ordered set where groupId = &#63; and plidPage = &#63; and targetName = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param targetName the target name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	public NotificationRpa fetchByTargetName_Last(
		long groupId, long plidPage, String targetName,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
			orderByComparator);

	/**
	 * Returns the notification rpas before and after the current notification rpa in the ordered set where groupId = &#63; and plidPage = &#63; and targetName = &#63;.
	 *
	 * @param customNotificationId the primary key of the current notification rpa
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param targetName the target name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next notification rpa
	 * @throws NoSuchNotificationRpaException if a notification rpa with the primary key could not be found
	 */
	public NotificationRpa[] findByTargetName_PrevAndNext(
			long customNotificationId, long groupId, long plidPage,
			String targetName,
			com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
				orderByComparator)
		throws NoSuchNotificationRpaException;

	/**
	 * Removes all the notification rpas where groupId = &#63; and plidPage = &#63; and targetName = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param targetName the target name
	 */
	public void removeByTargetName(
		long groupId, long plidPage, String targetName);

	/**
	 * Returns the number of notification rpas where groupId = &#63; and plidPage = &#63; and targetName = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param targetName the target name
	 * @return the number of matching notification rpas
	 */
	public int countByTargetName(
		long groupId, long plidPage, String targetName);

	/**
	 * Caches the notification rpa in the entity cache if it is enabled.
	 *
	 * @param notificationRpa the notification rpa
	 */
	public void cacheResult(NotificationRpa notificationRpa);

	/**
	 * Caches the notification rpas in the entity cache if it is enabled.
	 *
	 * @param notificationRpas the notification rpas
	 */
	public void cacheResult(java.util.List<NotificationRpa> notificationRpas);

	/**
	 * Creates a new notification rpa with the primary key. Does not add the notification rpa to the database.
	 *
	 * @param customNotificationId the primary key for the new notification rpa
	 * @return the new notification rpa
	 */
	public NotificationRpa create(long customNotificationId);

	/**
	 * Removes the notification rpa with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param customNotificationId the primary key of the notification rpa
	 * @return the notification rpa that was removed
	 * @throws NoSuchNotificationRpaException if a notification rpa with the primary key could not be found
	 */
	public NotificationRpa remove(long customNotificationId)
		throws NoSuchNotificationRpaException;

	public NotificationRpa updateImpl(NotificationRpa notificationRpa);

	/**
	 * Returns the notification rpa with the primary key or throws a <code>NoSuchNotificationRpaException</code> if it could not be found.
	 *
	 * @param customNotificationId the primary key of the notification rpa
	 * @return the notification rpa
	 * @throws NoSuchNotificationRpaException if a notification rpa with the primary key could not be found
	 */
	public NotificationRpa findByPrimaryKey(long customNotificationId)
		throws NoSuchNotificationRpaException;

	/**
	 * Returns the notification rpa with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param customNotificationId the primary key of the notification rpa
	 * @return the notification rpa, or <code>null</code> if a notification rpa with the primary key could not be found
	 */
	public NotificationRpa fetchByPrimaryKey(long customNotificationId);

	/**
	 * Returns all the notification rpas.
	 *
	 * @return the notification rpas
	 */
	public java.util.List<NotificationRpa> findAll();

	/**
	 * Returns a range of all the notification rpas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @return the range of notification rpas
	 */
	public java.util.List<NotificationRpa> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the notification rpas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of notification rpas
	 */
	public java.util.List<NotificationRpa> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
			orderByComparator);

	/**
	 * Returns an ordered range of all the notification rpas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of notification rpas
	 */
	public java.util.List<NotificationRpa> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NotificationRpa>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the notification rpas from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of notification rpas.
	 *
	 * @return the number of notification rpas
	 */
	public int countAll();

}