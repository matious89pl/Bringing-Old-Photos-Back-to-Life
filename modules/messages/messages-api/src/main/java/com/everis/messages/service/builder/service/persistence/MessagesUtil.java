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

package com.everis.messages.service.builder.service.persistence;

import com.everis.messages.service.builder.model.Messages;

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
 * The persistence utility for the messages service. This utility wraps <code>com.everis.messages.service.builder.service.persistence.impl.MessagesPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagesPersistence
 * @generated
 */
public class MessagesUtil {

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
	public static void clearCache(Messages messages) {
		getPersistence().clearCache(messages);
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
	public static Map<Serializable, Messages> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Messages> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Messages> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Messages> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Messages> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Messages update(Messages messages) {
		return getPersistence().update(messages);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Messages update(
		Messages messages, ServiceContext serviceContext) {

		return getPersistence().update(messages, serviceContext);
	}

	/**
	 * Returns all the messageses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching messageses
	 */
	public static List<Messages> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the messageses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessagesModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of messageses
	 * @param end the upper bound of the range of messageses (not inclusive)
	 * @return the range of matching messageses
	 */
	public static List<Messages> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the messageses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessagesModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of messageses
	 * @param end the upper bound of the range of messageses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching messageses
	 */
	public static List<Messages> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Messages> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the messageses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessagesModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of messageses
	 * @param end the upper bound of the range of messageses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching messageses
	 */
	public static List<Messages> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Messages> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first messages in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching messages
	 * @throws NoSuchMessagesException if a matching messages could not be found
	 */
	public static Messages findByUuid_First(
			String uuid, OrderByComparator<Messages> orderByComparator)
		throws com.everis.messages.service.builder.exception.
			NoSuchMessagesException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first messages in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching messages, or <code>null</code> if a matching messages could not be found
	 */
	public static Messages fetchByUuid_First(
		String uuid, OrderByComparator<Messages> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last messages in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching messages
	 * @throws NoSuchMessagesException if a matching messages could not be found
	 */
	public static Messages findByUuid_Last(
			String uuid, OrderByComparator<Messages> orderByComparator)
		throws com.everis.messages.service.builder.exception.
			NoSuchMessagesException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last messages in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching messages, or <code>null</code> if a matching messages could not be found
	 */
	public static Messages fetchByUuid_Last(
		String uuid, OrderByComparator<Messages> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the messageses before and after the current messages in the ordered set where uuid = &#63;.
	 *
	 * @param notificationEngineId the primary key of the current messages
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next messages
	 * @throws NoSuchMessagesException if a messages with the primary key could not be found
	 */
	public static Messages[] findByUuid_PrevAndNext(
			long notificationEngineId, String uuid,
			OrderByComparator<Messages> orderByComparator)
		throws com.everis.messages.service.builder.exception.
			NoSuchMessagesException {

		return getPersistence().findByUuid_PrevAndNext(
			notificationEngineId, uuid, orderByComparator);
	}

	/**
	 * Removes all the messageses where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of messageses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching messageses
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the messages where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchMessagesException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching messages
	 * @throws NoSuchMessagesException if a matching messages could not be found
	 */
	public static Messages findByUUID_G(String uuid, long groupId)
		throws com.everis.messages.service.builder.exception.
			NoSuchMessagesException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the messages where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching messages, or <code>null</code> if a matching messages could not be found
	 */
	public static Messages fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the messages where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching messages, or <code>null</code> if a matching messages could not be found
	 */
	public static Messages fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, useFinderCache);
	}

	/**
	 * Removes the messages where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the messages that was removed
	 */
	public static Messages removeByUUID_G(String uuid, long groupId)
		throws com.everis.messages.service.builder.exception.
			NoSuchMessagesException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of messageses where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching messageses
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the messageses where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching messageses
	 */
	public static List<Messages> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the messageses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessagesModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of messageses
	 * @param end the upper bound of the range of messageses (not inclusive)
	 * @return the range of matching messageses
	 */
	public static List<Messages> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the messageses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessagesModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of messageses
	 * @param end the upper bound of the range of messageses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching messageses
	 */
	public static List<Messages> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Messages> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the messageses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessagesModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of messageses
	 * @param end the upper bound of the range of messageses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching messageses
	 */
	public static List<Messages> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Messages> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first messages in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching messages
	 * @throws NoSuchMessagesException if a matching messages could not be found
	 */
	public static Messages findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<Messages> orderByComparator)
		throws com.everis.messages.service.builder.exception.
			NoSuchMessagesException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first messages in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching messages, or <code>null</code> if a matching messages could not be found
	 */
	public static Messages fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<Messages> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last messages in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching messages
	 * @throws NoSuchMessagesException if a matching messages could not be found
	 */
	public static Messages findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<Messages> orderByComparator)
		throws com.everis.messages.service.builder.exception.
			NoSuchMessagesException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last messages in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching messages, or <code>null</code> if a matching messages could not be found
	 */
	public static Messages fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<Messages> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the messageses before and after the current messages in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param notificationEngineId the primary key of the current messages
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next messages
	 * @throws NoSuchMessagesException if a messages with the primary key could not be found
	 */
	public static Messages[] findByUuid_C_PrevAndNext(
			long notificationEngineId, String uuid, long companyId,
			OrderByComparator<Messages> orderByComparator)
		throws com.everis.messages.service.builder.exception.
			NoSuchMessagesException {

		return getPersistence().findByUuid_C_PrevAndNext(
			notificationEngineId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the messageses where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of messageses where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching messageses
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns all the messageses where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching messageses
	 */
	public static List<Messages> findByCompanyId(long companyId) {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	 * Returns a range of all the messageses where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessagesModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of messageses
	 * @param end the upper bound of the range of messageses (not inclusive)
	 * @return the range of matching messageses
	 */
	public static List<Messages> findByCompanyId(
		long companyId, int start, int end) {

		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the messageses where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessagesModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of messageses
	 * @param end the upper bound of the range of messageses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching messageses
	 */
	public static List<Messages> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<Messages> orderByComparator) {

		return getPersistence().findByCompanyId(
			companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the messageses where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessagesModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of messageses
	 * @param end the upper bound of the range of messageses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching messageses
	 */
	public static List<Messages> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<Messages> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByCompanyId(
			companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first messages in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching messages
	 * @throws NoSuchMessagesException if a matching messages could not be found
	 */
	public static Messages findByCompanyId_First(
			long companyId, OrderByComparator<Messages> orderByComparator)
		throws com.everis.messages.service.builder.exception.
			NoSuchMessagesException {

		return getPersistence().findByCompanyId_First(
			companyId, orderByComparator);
	}

	/**
	 * Returns the first messages in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching messages, or <code>null</code> if a matching messages could not be found
	 */
	public static Messages fetchByCompanyId_First(
		long companyId, OrderByComparator<Messages> orderByComparator) {

		return getPersistence().fetchByCompanyId_First(
			companyId, orderByComparator);
	}

	/**
	 * Returns the last messages in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching messages
	 * @throws NoSuchMessagesException if a matching messages could not be found
	 */
	public static Messages findByCompanyId_Last(
			long companyId, OrderByComparator<Messages> orderByComparator)
		throws com.everis.messages.service.builder.exception.
			NoSuchMessagesException {

		return getPersistence().findByCompanyId_Last(
			companyId, orderByComparator);
	}

	/**
	 * Returns the last messages in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching messages, or <code>null</code> if a matching messages could not be found
	 */
	public static Messages fetchByCompanyId_Last(
		long companyId, OrderByComparator<Messages> orderByComparator) {

		return getPersistence().fetchByCompanyId_Last(
			companyId, orderByComparator);
	}

	/**
	 * Returns the messageses before and after the current messages in the ordered set where companyId = &#63;.
	 *
	 * @param notificationEngineId the primary key of the current messages
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next messages
	 * @throws NoSuchMessagesException if a messages with the primary key could not be found
	 */
	public static Messages[] findByCompanyId_PrevAndNext(
			long notificationEngineId, long companyId,
			OrderByComparator<Messages> orderByComparator)
		throws com.everis.messages.service.builder.exception.
			NoSuchMessagesException {

		return getPersistence().findByCompanyId_PrevAndNext(
			notificationEngineId, companyId, orderByComparator);
	}

	/**
	 * Removes all the messageses where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	public static void removeByCompanyId(long companyId) {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	 * Returns the number of messageses where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching messageses
	 */
	public static int countByCompanyId(long companyId) {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	 * Returns all the messageses where name = &#63; and companyId = &#63;.
	 *
	 * @param name the name
	 * @param companyId the company ID
	 * @return the matching messageses
	 */
	public static List<Messages> findByNameCompany(
		String name, long companyId) {

		return getPersistence().findByNameCompany(name, companyId);
	}

	/**
	 * Returns a range of all the messageses where name = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessagesModelImpl</code>.
	 * </p>
	 *
	 * @param name the name
	 * @param companyId the company ID
	 * @param start the lower bound of the range of messageses
	 * @param end the upper bound of the range of messageses (not inclusive)
	 * @return the range of matching messageses
	 */
	public static List<Messages> findByNameCompany(
		String name, long companyId, int start, int end) {

		return getPersistence().findByNameCompany(name, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the messageses where name = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessagesModelImpl</code>.
	 * </p>
	 *
	 * @param name the name
	 * @param companyId the company ID
	 * @param start the lower bound of the range of messageses
	 * @param end the upper bound of the range of messageses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching messageses
	 */
	public static List<Messages> findByNameCompany(
		String name, long companyId, int start, int end,
		OrderByComparator<Messages> orderByComparator) {

		return getPersistence().findByNameCompany(
			name, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the messageses where name = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessagesModelImpl</code>.
	 * </p>
	 *
	 * @param name the name
	 * @param companyId the company ID
	 * @param start the lower bound of the range of messageses
	 * @param end the upper bound of the range of messageses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching messageses
	 */
	public static List<Messages> findByNameCompany(
		String name, long companyId, int start, int end,
		OrderByComparator<Messages> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByNameCompany(
			name, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first messages in the ordered set where name = &#63; and companyId = &#63;.
	 *
	 * @param name the name
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching messages
	 * @throws NoSuchMessagesException if a matching messages could not be found
	 */
	public static Messages findByNameCompany_First(
			String name, long companyId,
			OrderByComparator<Messages> orderByComparator)
		throws com.everis.messages.service.builder.exception.
			NoSuchMessagesException {

		return getPersistence().findByNameCompany_First(
			name, companyId, orderByComparator);
	}

	/**
	 * Returns the first messages in the ordered set where name = &#63; and companyId = &#63;.
	 *
	 * @param name the name
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching messages, or <code>null</code> if a matching messages could not be found
	 */
	public static Messages fetchByNameCompany_First(
		String name, long companyId,
		OrderByComparator<Messages> orderByComparator) {

		return getPersistence().fetchByNameCompany_First(
			name, companyId, orderByComparator);
	}

	/**
	 * Returns the last messages in the ordered set where name = &#63; and companyId = &#63;.
	 *
	 * @param name the name
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching messages
	 * @throws NoSuchMessagesException if a matching messages could not be found
	 */
	public static Messages findByNameCompany_Last(
			String name, long companyId,
			OrderByComparator<Messages> orderByComparator)
		throws com.everis.messages.service.builder.exception.
			NoSuchMessagesException {

		return getPersistence().findByNameCompany_Last(
			name, companyId, orderByComparator);
	}

	/**
	 * Returns the last messages in the ordered set where name = &#63; and companyId = &#63;.
	 *
	 * @param name the name
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching messages, or <code>null</code> if a matching messages could not be found
	 */
	public static Messages fetchByNameCompany_Last(
		String name, long companyId,
		OrderByComparator<Messages> orderByComparator) {

		return getPersistence().fetchByNameCompany_Last(
			name, companyId, orderByComparator);
	}

	/**
	 * Returns the messageses before and after the current messages in the ordered set where name = &#63; and companyId = &#63;.
	 *
	 * @param notificationEngineId the primary key of the current messages
	 * @param name the name
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next messages
	 * @throws NoSuchMessagesException if a messages with the primary key could not be found
	 */
	public static Messages[] findByNameCompany_PrevAndNext(
			long notificationEngineId, String name, long companyId,
			OrderByComparator<Messages> orderByComparator)
		throws com.everis.messages.service.builder.exception.
			NoSuchMessagesException {

		return getPersistence().findByNameCompany_PrevAndNext(
			notificationEngineId, name, companyId, orderByComparator);
	}

	/**
	 * Removes all the messageses where name = &#63; and companyId = &#63; from the database.
	 *
	 * @param name the name
	 * @param companyId the company ID
	 */
	public static void removeByNameCompany(String name, long companyId) {
		getPersistence().removeByNameCompany(name, companyId);
	}

	/**
	 * Returns the number of messageses where name = &#63; and companyId = &#63;.
	 *
	 * @param name the name
	 * @param companyId the company ID
	 * @return the number of matching messageses
	 */
	public static int countByNameCompany(String name, long companyId) {
		return getPersistence().countByNameCompany(name, companyId);
	}

	/**
	 * Caches the messages in the entity cache if it is enabled.
	 *
	 * @param messages the messages
	 */
	public static void cacheResult(Messages messages) {
		getPersistence().cacheResult(messages);
	}

	/**
	 * Caches the messageses in the entity cache if it is enabled.
	 *
	 * @param messageses the messageses
	 */
	public static void cacheResult(List<Messages> messageses) {
		getPersistence().cacheResult(messageses);
	}

	/**
	 * Creates a new messages with the primary key. Does not add the messages to the database.
	 *
	 * @param notificationEngineId the primary key for the new messages
	 * @return the new messages
	 */
	public static Messages create(long notificationEngineId) {
		return getPersistence().create(notificationEngineId);
	}

	/**
	 * Removes the messages with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param notificationEngineId the primary key of the messages
	 * @return the messages that was removed
	 * @throws NoSuchMessagesException if a messages with the primary key could not be found
	 */
	public static Messages remove(long notificationEngineId)
		throws com.everis.messages.service.builder.exception.
			NoSuchMessagesException {

		return getPersistence().remove(notificationEngineId);
	}

	public static Messages updateImpl(Messages messages) {
		return getPersistence().updateImpl(messages);
	}

	/**
	 * Returns the messages with the primary key or throws a <code>NoSuchMessagesException</code> if it could not be found.
	 *
	 * @param notificationEngineId the primary key of the messages
	 * @return the messages
	 * @throws NoSuchMessagesException if a messages with the primary key could not be found
	 */
	public static Messages findByPrimaryKey(long notificationEngineId)
		throws com.everis.messages.service.builder.exception.
			NoSuchMessagesException {

		return getPersistence().findByPrimaryKey(notificationEngineId);
	}

	/**
	 * Returns the messages with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param notificationEngineId the primary key of the messages
	 * @return the messages, or <code>null</code> if a messages with the primary key could not be found
	 */
	public static Messages fetchByPrimaryKey(long notificationEngineId) {
		return getPersistence().fetchByPrimaryKey(notificationEngineId);
	}

	/**
	 * Returns all the messageses.
	 *
	 * @return the messageses
	 */
	public static List<Messages> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the messageses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessagesModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of messageses
	 * @param end the upper bound of the range of messageses (not inclusive)
	 * @return the range of messageses
	 */
	public static List<Messages> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the messageses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessagesModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of messageses
	 * @param end the upper bound of the range of messageses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of messageses
	 */
	public static List<Messages> findAll(
		int start, int end, OrderByComparator<Messages> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the messageses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessagesModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of messageses
	 * @param end the upper bound of the range of messageses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of messageses
	 */
	public static List<Messages> findAll(
		int start, int end, OrderByComparator<Messages> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the messageses from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of messageses.
	 *
	 * @return the number of messageses
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static MessagesPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<MessagesPersistence, MessagesPersistence>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(MessagesPersistence.class);

		ServiceTracker<MessagesPersistence, MessagesPersistence>
			serviceTracker =
				new ServiceTracker<MessagesPersistence, MessagesPersistence>(
					bundle.getBundleContext(), MessagesPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}