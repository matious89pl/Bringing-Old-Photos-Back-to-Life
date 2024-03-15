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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

import rec.customnotification.model.NotificationRpa;

/**
 * The persistence utility for the notification rpa service. This utility wraps <code>rec.customnotification.service.persistence.impl.NotificationRpaPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see NotificationRpaPersistence
 * @generated
 */
public class NotificationRpaUtil {

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
	public static void clearCache(NotificationRpa notificationRpa) {
		getPersistence().clearCache(notificationRpa);
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
	public static Map<Serializable, NotificationRpa> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<NotificationRpa> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<NotificationRpa> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<NotificationRpa> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<NotificationRpa> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static NotificationRpa update(NotificationRpa notificationRpa) {
		return getPersistence().update(notificationRpa);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static NotificationRpa update(
		NotificationRpa notificationRpa, ServiceContext serviceContext) {

		return getPersistence().update(notificationRpa, serviceContext);
	}

	/**
	 * Returns all the notification rpas where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching notification rpas
	 */
	public static List<NotificationRpa> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

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
	public static List<NotificationRpa> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static List<NotificationRpa> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<NotificationRpa> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

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
	public static List<NotificationRpa> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<NotificationRpa> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first notification rpa in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification rpa
	 * @throws NoSuchNotificationRpaException if a matching notification rpa could not be found
	 */
	public static NotificationRpa findByUuid_First(
			String uuid, OrderByComparator<NotificationRpa> orderByComparator)
		throws rec.customnotification.exception.NoSuchNotificationRpaException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first notification rpa in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	public static NotificationRpa fetchByUuid_First(
		String uuid, OrderByComparator<NotificationRpa> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last notification rpa in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification rpa
	 * @throws NoSuchNotificationRpaException if a matching notification rpa could not be found
	 */
	public static NotificationRpa findByUuid_Last(
			String uuid, OrderByComparator<NotificationRpa> orderByComparator)
		throws rec.customnotification.exception.NoSuchNotificationRpaException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last notification rpa in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	public static NotificationRpa fetchByUuid_Last(
		String uuid, OrderByComparator<NotificationRpa> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the notification rpas before and after the current notification rpa in the ordered set where uuid = &#63;.
	 *
	 * @param customNotificationId the primary key of the current notification rpa
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next notification rpa
	 * @throws NoSuchNotificationRpaException if a notification rpa with the primary key could not be found
	 */
	public static NotificationRpa[] findByUuid_PrevAndNext(
			long customNotificationId, String uuid,
			OrderByComparator<NotificationRpa> orderByComparator)
		throws rec.customnotification.exception.NoSuchNotificationRpaException {

		return getPersistence().findByUuid_PrevAndNext(
			customNotificationId, uuid, orderByComparator);
	}

	/**
	 * Removes all the notification rpas where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of notification rpas where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching notification rpas
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the notification rpa where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchNotificationRpaException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching notification rpa
	 * @throws NoSuchNotificationRpaException if a matching notification rpa could not be found
	 */
	public static NotificationRpa findByUUID_G(String uuid, long groupId)
		throws rec.customnotification.exception.NoSuchNotificationRpaException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the notification rpa where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	public static NotificationRpa fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the notification rpa where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	public static NotificationRpa fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, useFinderCache);
	}

	/**
	 * Removes the notification rpa where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the notification rpa that was removed
	 */
	public static NotificationRpa removeByUUID_G(String uuid, long groupId)
		throws rec.customnotification.exception.NoSuchNotificationRpaException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of notification rpas where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching notification rpas
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the notification rpas where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching notification rpas
	 */
	public static List<NotificationRpa> findByUuid_C(
		String uuid, long companyId) {

		return getPersistence().findByUuid_C(uuid, companyId);
	}

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
	public static List<NotificationRpa> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

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
	public static List<NotificationRpa> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<NotificationRpa> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

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
	public static List<NotificationRpa> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<NotificationRpa> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first notification rpa in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification rpa
	 * @throws NoSuchNotificationRpaException if a matching notification rpa could not be found
	 */
	public static NotificationRpa findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<NotificationRpa> orderByComparator)
		throws rec.customnotification.exception.NoSuchNotificationRpaException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first notification rpa in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	public static NotificationRpa fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<NotificationRpa> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last notification rpa in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification rpa
	 * @throws NoSuchNotificationRpaException if a matching notification rpa could not be found
	 */
	public static NotificationRpa findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<NotificationRpa> orderByComparator)
		throws rec.customnotification.exception.NoSuchNotificationRpaException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last notification rpa in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	public static NotificationRpa fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<NotificationRpa> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

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
	public static NotificationRpa[] findByUuid_C_PrevAndNext(
			long customNotificationId, String uuid, long companyId,
			OrderByComparator<NotificationRpa> orderByComparator)
		throws rec.customnotification.exception.NoSuchNotificationRpaException {

		return getPersistence().findByUuid_C_PrevAndNext(
			customNotificationId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the notification rpas where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of notification rpas where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching notification rpas
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns all the notification rpas where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching notification rpas
	 */
	public static List<NotificationRpa> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

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
	public static List<NotificationRpa> findByGroupId(
		long groupId, int start, int end) {

		return getPersistence().findByGroupId(groupId, start, end);
	}

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
	public static List<NotificationRpa> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<NotificationRpa> orderByComparator) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator);
	}

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
	public static List<NotificationRpa> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<NotificationRpa> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first notification rpa in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification rpa
	 * @throws NoSuchNotificationRpaException if a matching notification rpa could not be found
	 */
	public static NotificationRpa findByGroupId_First(
			long groupId, OrderByComparator<NotificationRpa> orderByComparator)
		throws rec.customnotification.exception.NoSuchNotificationRpaException {

		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	 * Returns the first notification rpa in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	public static NotificationRpa fetchByGroupId_First(
		long groupId, OrderByComparator<NotificationRpa> orderByComparator) {

		return getPersistence().fetchByGroupId_First(
			groupId, orderByComparator);
	}

	/**
	 * Returns the last notification rpa in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification rpa
	 * @throws NoSuchNotificationRpaException if a matching notification rpa could not be found
	 */
	public static NotificationRpa findByGroupId_Last(
			long groupId, OrderByComparator<NotificationRpa> orderByComparator)
		throws rec.customnotification.exception.NoSuchNotificationRpaException {

		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the last notification rpa in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	public static NotificationRpa fetchByGroupId_Last(
		long groupId, OrderByComparator<NotificationRpa> orderByComparator) {

		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the notification rpas before and after the current notification rpa in the ordered set where groupId = &#63;.
	 *
	 * @param customNotificationId the primary key of the current notification rpa
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next notification rpa
	 * @throws NoSuchNotificationRpaException if a notification rpa with the primary key could not be found
	 */
	public static NotificationRpa[] findByGroupId_PrevAndNext(
			long customNotificationId, long groupId,
			OrderByComparator<NotificationRpa> orderByComparator)
		throws rec.customnotification.exception.NoSuchNotificationRpaException {

		return getPersistence().findByGroupId_PrevAndNext(
			customNotificationId, groupId, orderByComparator);
	}

	/**
	 * Removes all the notification rpas where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	 * Returns the number of notification rpas where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching notification rpas
	 */
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	 * Returns all the notification rpas where groupId = &#63; and plidPage = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @return the matching notification rpas
	 */
	public static List<NotificationRpa> findByGroupIdPlidPage(
		long groupId, long plidPage) {

		return getPersistence().findByGroupIdPlidPage(groupId, plidPage);
	}

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
	public static List<NotificationRpa> findByGroupIdPlidPage(
		long groupId, long plidPage, int start, int end) {

		return getPersistence().findByGroupIdPlidPage(
			groupId, plidPage, start, end);
	}

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
	public static List<NotificationRpa> findByGroupIdPlidPage(
		long groupId, long plidPage, int start, int end,
		OrderByComparator<NotificationRpa> orderByComparator) {

		return getPersistence().findByGroupIdPlidPage(
			groupId, plidPage, start, end, orderByComparator);
	}

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
	public static List<NotificationRpa> findByGroupIdPlidPage(
		long groupId, long plidPage, int start, int end,
		OrderByComparator<NotificationRpa> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByGroupIdPlidPage(
			groupId, plidPage, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first notification rpa in the ordered set where groupId = &#63; and plidPage = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification rpa
	 * @throws NoSuchNotificationRpaException if a matching notification rpa could not be found
	 */
	public static NotificationRpa findByGroupIdPlidPage_First(
			long groupId, long plidPage,
			OrderByComparator<NotificationRpa> orderByComparator)
		throws rec.customnotification.exception.NoSuchNotificationRpaException {

		return getPersistence().findByGroupIdPlidPage_First(
			groupId, plidPage, orderByComparator);
	}

	/**
	 * Returns the first notification rpa in the ordered set where groupId = &#63; and plidPage = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	public static NotificationRpa fetchByGroupIdPlidPage_First(
		long groupId, long plidPage,
		OrderByComparator<NotificationRpa> orderByComparator) {

		return getPersistence().fetchByGroupIdPlidPage_First(
			groupId, plidPage, orderByComparator);
	}

	/**
	 * Returns the last notification rpa in the ordered set where groupId = &#63; and plidPage = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification rpa
	 * @throws NoSuchNotificationRpaException if a matching notification rpa could not be found
	 */
	public static NotificationRpa findByGroupIdPlidPage_Last(
			long groupId, long plidPage,
			OrderByComparator<NotificationRpa> orderByComparator)
		throws rec.customnotification.exception.NoSuchNotificationRpaException {

		return getPersistence().findByGroupIdPlidPage_Last(
			groupId, plidPage, orderByComparator);
	}

	/**
	 * Returns the last notification rpa in the ordered set where groupId = &#63; and plidPage = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	public static NotificationRpa fetchByGroupIdPlidPage_Last(
		long groupId, long plidPage,
		OrderByComparator<NotificationRpa> orderByComparator) {

		return getPersistence().fetchByGroupIdPlidPage_Last(
			groupId, plidPage, orderByComparator);
	}

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
	public static NotificationRpa[] findByGroupIdPlidPage_PrevAndNext(
			long customNotificationId, long groupId, long plidPage,
			OrderByComparator<NotificationRpa> orderByComparator)
		throws rec.customnotification.exception.NoSuchNotificationRpaException {

		return getPersistence().findByGroupIdPlidPage_PrevAndNext(
			customNotificationId, groupId, plidPage, orderByComparator);
	}

	/**
	 * Removes all the notification rpas where groupId = &#63; and plidPage = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 */
	public static void removeByGroupIdPlidPage(long groupId, long plidPage) {
		getPersistence().removeByGroupIdPlidPage(groupId, plidPage);
	}

	/**
	 * Returns the number of notification rpas where groupId = &#63; and plidPage = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @return the number of matching notification rpas
	 */
	public static int countByGroupIdPlidPage(long groupId, long plidPage) {
		return getPersistence().countByGroupIdPlidPage(groupId, plidPage);
	}

	/**
	 * Returns all the notification rpas where groupId = &#63; and plidPage = &#63; and notificationTitle = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationTitle the notification title
	 * @return the matching notification rpas
	 */
	public static List<NotificationRpa> findByNotificationTitle(
		long groupId, long plidPage, String notificationTitle) {

		return getPersistence().findByNotificationTitle(
			groupId, plidPage, notificationTitle);
	}

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
	public static List<NotificationRpa> findByNotificationTitle(
		long groupId, long plidPage, String notificationTitle, int start,
		int end) {

		return getPersistence().findByNotificationTitle(
			groupId, plidPage, notificationTitle, start, end);
	}

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
	public static List<NotificationRpa> findByNotificationTitle(
		long groupId, long plidPage, String notificationTitle, int start,
		int end, OrderByComparator<NotificationRpa> orderByComparator) {

		return getPersistence().findByNotificationTitle(
			groupId, plidPage, notificationTitle, start, end,
			orderByComparator);
	}

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
	public static List<NotificationRpa> findByNotificationTitle(
		long groupId, long plidPage, String notificationTitle, int start,
		int end, OrderByComparator<NotificationRpa> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByNotificationTitle(
			groupId, plidPage, notificationTitle, start, end, orderByComparator,
			useFinderCache);
	}

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
	public static NotificationRpa findByNotificationTitle_First(
			long groupId, long plidPage, String notificationTitle,
			OrderByComparator<NotificationRpa> orderByComparator)
		throws rec.customnotification.exception.NoSuchNotificationRpaException {

		return getPersistence().findByNotificationTitle_First(
			groupId, plidPage, notificationTitle, orderByComparator);
	}

	/**
	 * Returns the first notification rpa in the ordered set where groupId = &#63; and plidPage = &#63; and notificationTitle = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationTitle the notification title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	public static NotificationRpa fetchByNotificationTitle_First(
		long groupId, long plidPage, String notificationTitle,
		OrderByComparator<NotificationRpa> orderByComparator) {

		return getPersistence().fetchByNotificationTitle_First(
			groupId, plidPage, notificationTitle, orderByComparator);
	}

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
	public static NotificationRpa findByNotificationTitle_Last(
			long groupId, long plidPage, String notificationTitle,
			OrderByComparator<NotificationRpa> orderByComparator)
		throws rec.customnotification.exception.NoSuchNotificationRpaException {

		return getPersistence().findByNotificationTitle_Last(
			groupId, plidPage, notificationTitle, orderByComparator);
	}

	/**
	 * Returns the last notification rpa in the ordered set where groupId = &#63; and plidPage = &#63; and notificationTitle = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationTitle the notification title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	public static NotificationRpa fetchByNotificationTitle_Last(
		long groupId, long plidPage, String notificationTitle,
		OrderByComparator<NotificationRpa> orderByComparator) {

		return getPersistence().fetchByNotificationTitle_Last(
			groupId, plidPage, notificationTitle, orderByComparator);
	}

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
	public static NotificationRpa[] findByNotificationTitle_PrevAndNext(
			long customNotificationId, long groupId, long plidPage,
			String notificationTitle,
			OrderByComparator<NotificationRpa> orderByComparator)
		throws rec.customnotification.exception.NoSuchNotificationRpaException {

		return getPersistence().findByNotificationTitle_PrevAndNext(
			customNotificationId, groupId, plidPage, notificationTitle,
			orderByComparator);
	}

	/**
	 * Removes all the notification rpas where groupId = &#63; and plidPage = &#63; and notificationTitle = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationTitle the notification title
	 */
	public static void removeByNotificationTitle(
		long groupId, long plidPage, String notificationTitle) {

		getPersistence().removeByNotificationTitle(
			groupId, plidPage, notificationTitle);
	}

	/**
	 * Returns the number of notification rpas where groupId = &#63; and plidPage = &#63; and notificationTitle = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationTitle the notification title
	 * @return the number of matching notification rpas
	 */
	public static int countByNotificationTitle(
		long groupId, long plidPage, String notificationTitle) {

		return getPersistence().countByNotificationTitle(
			groupId, plidPage, notificationTitle);
	}

	/**
	 * Returns all the notification rpas where groupId = &#63; and plidPage = &#63; and notificationBody = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationBody the notification body
	 * @return the matching notification rpas
	 */
	public static List<NotificationRpa> findByNotificationBody(
		long groupId, long plidPage, String notificationBody) {

		return getPersistence().findByNotificationBody(
			groupId, plidPage, notificationBody);
	}

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
	public static List<NotificationRpa> findByNotificationBody(
		long groupId, long plidPage, String notificationBody, int start,
		int end) {

		return getPersistence().findByNotificationBody(
			groupId, plidPage, notificationBody, start, end);
	}

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
	public static List<NotificationRpa> findByNotificationBody(
		long groupId, long plidPage, String notificationBody, int start,
		int end, OrderByComparator<NotificationRpa> orderByComparator) {

		return getPersistence().findByNotificationBody(
			groupId, plidPage, notificationBody, start, end, orderByComparator);
	}

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
	public static List<NotificationRpa> findByNotificationBody(
		long groupId, long plidPage, String notificationBody, int start,
		int end, OrderByComparator<NotificationRpa> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByNotificationBody(
			groupId, plidPage, notificationBody, start, end, orderByComparator,
			useFinderCache);
	}

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
	public static NotificationRpa findByNotificationBody_First(
			long groupId, long plidPage, String notificationBody,
			OrderByComparator<NotificationRpa> orderByComparator)
		throws rec.customnotification.exception.NoSuchNotificationRpaException {

		return getPersistence().findByNotificationBody_First(
			groupId, plidPage, notificationBody, orderByComparator);
	}

	/**
	 * Returns the first notification rpa in the ordered set where groupId = &#63; and plidPage = &#63; and notificationBody = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationBody the notification body
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	public static NotificationRpa fetchByNotificationBody_First(
		long groupId, long plidPage, String notificationBody,
		OrderByComparator<NotificationRpa> orderByComparator) {

		return getPersistence().fetchByNotificationBody_First(
			groupId, plidPage, notificationBody, orderByComparator);
	}

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
	public static NotificationRpa findByNotificationBody_Last(
			long groupId, long plidPage, String notificationBody,
			OrderByComparator<NotificationRpa> orderByComparator)
		throws rec.customnotification.exception.NoSuchNotificationRpaException {

		return getPersistence().findByNotificationBody_Last(
			groupId, plidPage, notificationBody, orderByComparator);
	}

	/**
	 * Returns the last notification rpa in the ordered set where groupId = &#63; and plidPage = &#63; and notificationBody = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationBody the notification body
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	public static NotificationRpa fetchByNotificationBody_Last(
		long groupId, long plidPage, String notificationBody,
		OrderByComparator<NotificationRpa> orderByComparator) {

		return getPersistence().fetchByNotificationBody_Last(
			groupId, plidPage, notificationBody, orderByComparator);
	}

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
	public static NotificationRpa[] findByNotificationBody_PrevAndNext(
			long customNotificationId, long groupId, long plidPage,
			String notificationBody,
			OrderByComparator<NotificationRpa> orderByComparator)
		throws rec.customnotification.exception.NoSuchNotificationRpaException {

		return getPersistence().findByNotificationBody_PrevAndNext(
			customNotificationId, groupId, plidPage, notificationBody,
			orderByComparator);
	}

	/**
	 * Removes all the notification rpas where groupId = &#63; and plidPage = &#63; and notificationBody = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationBody the notification body
	 */
	public static void removeByNotificationBody(
		long groupId, long plidPage, String notificationBody) {

		getPersistence().removeByNotificationBody(
			groupId, plidPage, notificationBody);
	}

	/**
	 * Returns the number of notification rpas where groupId = &#63; and plidPage = &#63; and notificationBody = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationBody the notification body
	 * @return the number of matching notification rpas
	 */
	public static int countByNotificationBody(
		long groupId, long plidPage, String notificationBody) {

		return getPersistence().countByNotificationBody(
			groupId, plidPage, notificationBody);
	}

	/**
	 * Returns all the notification rpas where groupId = &#63; and plidPage = &#63; and createDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param createDate the create date
	 * @return the matching notification rpas
	 */
	public static List<NotificationRpa> findByCreateDate(
		long groupId, long plidPage, Date createDate) {

		return getPersistence().findByCreateDate(groupId, plidPage, createDate);
	}

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
	public static List<NotificationRpa> findByCreateDate(
		long groupId, long plidPage, Date createDate, int start, int end) {

		return getPersistence().findByCreateDate(
			groupId, plidPage, createDate, start, end);
	}

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
	public static List<NotificationRpa> findByCreateDate(
		long groupId, long plidPage, Date createDate, int start, int end,
		OrderByComparator<NotificationRpa> orderByComparator) {

		return getPersistence().findByCreateDate(
			groupId, plidPage, createDate, start, end, orderByComparator);
	}

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
	public static List<NotificationRpa> findByCreateDate(
		long groupId, long plidPage, Date createDate, int start, int end,
		OrderByComparator<NotificationRpa> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByCreateDate(
			groupId, plidPage, createDate, start, end, orderByComparator,
			useFinderCache);
	}

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
	public static NotificationRpa findByCreateDate_First(
			long groupId, long plidPage, Date createDate,
			OrderByComparator<NotificationRpa> orderByComparator)
		throws rec.customnotification.exception.NoSuchNotificationRpaException {

		return getPersistence().findByCreateDate_First(
			groupId, plidPage, createDate, orderByComparator);
	}

	/**
	 * Returns the first notification rpa in the ordered set where groupId = &#63; and plidPage = &#63; and createDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	public static NotificationRpa fetchByCreateDate_First(
		long groupId, long plidPage, Date createDate,
		OrderByComparator<NotificationRpa> orderByComparator) {

		return getPersistence().fetchByCreateDate_First(
			groupId, plidPage, createDate, orderByComparator);
	}

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
	public static NotificationRpa findByCreateDate_Last(
			long groupId, long plidPage, Date createDate,
			OrderByComparator<NotificationRpa> orderByComparator)
		throws rec.customnotification.exception.NoSuchNotificationRpaException {

		return getPersistence().findByCreateDate_Last(
			groupId, plidPage, createDate, orderByComparator);
	}

	/**
	 * Returns the last notification rpa in the ordered set where groupId = &#63; and plidPage = &#63; and createDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	public static NotificationRpa fetchByCreateDate_Last(
		long groupId, long plidPage, Date createDate,
		OrderByComparator<NotificationRpa> orderByComparator) {

		return getPersistence().fetchByCreateDate_Last(
			groupId, plidPage, createDate, orderByComparator);
	}

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
	public static NotificationRpa[] findByCreateDate_PrevAndNext(
			long customNotificationId, long groupId, long plidPage,
			Date createDate,
			OrderByComparator<NotificationRpa> orderByComparator)
		throws rec.customnotification.exception.NoSuchNotificationRpaException {

		return getPersistence().findByCreateDate_PrevAndNext(
			customNotificationId, groupId, plidPage, createDate,
			orderByComparator);
	}

	/**
	 * Removes all the notification rpas where groupId = &#63; and plidPage = &#63; and createDate = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param createDate the create date
	 */
	public static void removeByCreateDate(
		long groupId, long plidPage, Date createDate) {

		getPersistence().removeByCreateDate(groupId, plidPage, createDate);
	}

	/**
	 * Returns the number of notification rpas where groupId = &#63; and plidPage = &#63; and createDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param createDate the create date
	 * @return the number of matching notification rpas
	 */
	public static int countByCreateDate(
		long groupId, long plidPage, Date createDate) {

		return getPersistence().countByCreateDate(
			groupId, plidPage, createDate);
	}

	/**
	 * Returns all the notification rpas where groupId = &#63; and plidPage = &#63; and targetName = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param targetName the target name
	 * @return the matching notification rpas
	 */
	public static List<NotificationRpa> findByTargetName(
		long groupId, long plidPage, String targetName) {

		return getPersistence().findByTargetName(groupId, plidPage, targetName);
	}

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
	public static List<NotificationRpa> findByTargetName(
		long groupId, long plidPage, String targetName, int start, int end) {

		return getPersistence().findByTargetName(
			groupId, plidPage, targetName, start, end);
	}

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
	public static List<NotificationRpa> findByTargetName(
		long groupId, long plidPage, String targetName, int start, int end,
		OrderByComparator<NotificationRpa> orderByComparator) {

		return getPersistence().findByTargetName(
			groupId, plidPage, targetName, start, end, orderByComparator);
	}

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
	public static List<NotificationRpa> findByTargetName(
		long groupId, long plidPage, String targetName, int start, int end,
		OrderByComparator<NotificationRpa> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByTargetName(
			groupId, plidPage, targetName, start, end, orderByComparator,
			useFinderCache);
	}

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
	public static NotificationRpa findByTargetName_First(
			long groupId, long plidPage, String targetName,
			OrderByComparator<NotificationRpa> orderByComparator)
		throws rec.customnotification.exception.NoSuchNotificationRpaException {

		return getPersistence().findByTargetName_First(
			groupId, plidPage, targetName, orderByComparator);
	}

	/**
	 * Returns the first notification rpa in the ordered set where groupId = &#63; and plidPage = &#63; and targetName = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param targetName the target name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	public static NotificationRpa fetchByTargetName_First(
		long groupId, long plidPage, String targetName,
		OrderByComparator<NotificationRpa> orderByComparator) {

		return getPersistence().fetchByTargetName_First(
			groupId, plidPage, targetName, orderByComparator);
	}

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
	public static NotificationRpa findByTargetName_Last(
			long groupId, long plidPage, String targetName,
			OrderByComparator<NotificationRpa> orderByComparator)
		throws rec.customnotification.exception.NoSuchNotificationRpaException {

		return getPersistence().findByTargetName_Last(
			groupId, plidPage, targetName, orderByComparator);
	}

	/**
	 * Returns the last notification rpa in the ordered set where groupId = &#63; and plidPage = &#63; and targetName = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param targetName the target name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	public static NotificationRpa fetchByTargetName_Last(
		long groupId, long plidPage, String targetName,
		OrderByComparator<NotificationRpa> orderByComparator) {

		return getPersistence().fetchByTargetName_Last(
			groupId, plidPage, targetName, orderByComparator);
	}

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
	public static NotificationRpa[] findByTargetName_PrevAndNext(
			long customNotificationId, long groupId, long plidPage,
			String targetName,
			OrderByComparator<NotificationRpa> orderByComparator)
		throws rec.customnotification.exception.NoSuchNotificationRpaException {

		return getPersistence().findByTargetName_PrevAndNext(
			customNotificationId, groupId, plidPage, targetName,
			orderByComparator);
	}

	/**
	 * Removes all the notification rpas where groupId = &#63; and plidPage = &#63; and targetName = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param targetName the target name
	 */
	public static void removeByTargetName(
		long groupId, long plidPage, String targetName) {

		getPersistence().removeByTargetName(groupId, plidPage, targetName);
	}

	/**
	 * Returns the number of notification rpas where groupId = &#63; and plidPage = &#63; and targetName = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param targetName the target name
	 * @return the number of matching notification rpas
	 */
	public static int countByTargetName(
		long groupId, long plidPage, String targetName) {

		return getPersistence().countByTargetName(
			groupId, plidPage, targetName);
	}

	/**
	 * Caches the notification rpa in the entity cache if it is enabled.
	 *
	 * @param notificationRpa the notification rpa
	 */
	public static void cacheResult(NotificationRpa notificationRpa) {
		getPersistence().cacheResult(notificationRpa);
	}

	/**
	 * Caches the notification rpas in the entity cache if it is enabled.
	 *
	 * @param notificationRpas the notification rpas
	 */
	public static void cacheResult(List<NotificationRpa> notificationRpas) {
		getPersistence().cacheResult(notificationRpas);
	}

	/**
	 * Creates a new notification rpa with the primary key. Does not add the notification rpa to the database.
	 *
	 * @param customNotificationId the primary key for the new notification rpa
	 * @return the new notification rpa
	 */
	public static NotificationRpa create(long customNotificationId) {
		return getPersistence().create(customNotificationId);
	}

	/**
	 * Removes the notification rpa with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param customNotificationId the primary key of the notification rpa
	 * @return the notification rpa that was removed
	 * @throws NoSuchNotificationRpaException if a notification rpa with the primary key could not be found
	 */
	public static NotificationRpa remove(long customNotificationId)
		throws rec.customnotification.exception.NoSuchNotificationRpaException {

		return getPersistence().remove(customNotificationId);
	}

	public static NotificationRpa updateImpl(NotificationRpa notificationRpa) {
		return getPersistence().updateImpl(notificationRpa);
	}

	/**
	 * Returns the notification rpa with the primary key or throws a <code>NoSuchNotificationRpaException</code> if it could not be found.
	 *
	 * @param customNotificationId the primary key of the notification rpa
	 * @return the notification rpa
	 * @throws NoSuchNotificationRpaException if a notification rpa with the primary key could not be found
	 */
	public static NotificationRpa findByPrimaryKey(long customNotificationId)
		throws rec.customnotification.exception.NoSuchNotificationRpaException {

		return getPersistence().findByPrimaryKey(customNotificationId);
	}

	/**
	 * Returns the notification rpa with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param customNotificationId the primary key of the notification rpa
	 * @return the notification rpa, or <code>null</code> if a notification rpa with the primary key could not be found
	 */
	public static NotificationRpa fetchByPrimaryKey(long customNotificationId) {
		return getPersistence().fetchByPrimaryKey(customNotificationId);
	}

	/**
	 * Returns all the notification rpas.
	 *
	 * @return the notification rpas
	 */
	public static List<NotificationRpa> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<NotificationRpa> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<NotificationRpa> findAll(
		int start, int end,
		OrderByComparator<NotificationRpa> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<NotificationRpa> findAll(
		int start, int end,
		OrderByComparator<NotificationRpa> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the notification rpas from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of notification rpas.
	 *
	 * @return the number of notification rpas
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static NotificationRpaPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<NotificationRpaPersistence, NotificationRpaPersistence>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			NotificationRpaPersistence.class);

		ServiceTracker<NotificationRpaPersistence, NotificationRpaPersistence>
			serviceTracker =
				new ServiceTracker
					<NotificationRpaPersistence, NotificationRpaPersistence>(
						bundle.getBundleContext(),
						NotificationRpaPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}