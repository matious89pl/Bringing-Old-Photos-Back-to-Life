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

package rec.confidential.message.service.persistence;

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

import rec.confidential.message.model.Confidential_Messages;

/**
 * The persistence utility for the confidential_ messages service. This utility wraps <code>rec.confidential.message.service.persistence.impl.Confidential_MessagesPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Confidential_MessagesPersistence
 * @generated
 */
public class Confidential_MessagesUtil {

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
	public static void clearCache(Confidential_Messages confidential_Messages) {
		getPersistence().clearCache(confidential_Messages);
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
	public static Map<Serializable, Confidential_Messages> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Confidential_Messages> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Confidential_Messages> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Confidential_Messages> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Confidential_Messages> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Confidential_Messages update(
		Confidential_Messages confidential_Messages) {

		return getPersistence().update(confidential_Messages);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Confidential_Messages update(
		Confidential_Messages confidential_Messages,
		ServiceContext serviceContext) {

		return getPersistence().update(confidential_Messages, serviceContext);
	}

	/**
	 * Returns all the confidential_ messageses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching confidential_ messageses
	 */
	public static List<Confidential_Messages> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the confidential_ messageses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>Confidential_MessagesModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of confidential_ messageses
	 * @param end the upper bound of the range of confidential_ messageses (not inclusive)
	 * @return the range of matching confidential_ messageses
	 */
	public static List<Confidential_Messages> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the confidential_ messageses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>Confidential_MessagesModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of confidential_ messageses
	 * @param end the upper bound of the range of confidential_ messageses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching confidential_ messageses
	 */
	public static List<Confidential_Messages> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Confidential_Messages> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the confidential_ messageses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>Confidential_MessagesModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of confidential_ messageses
	 * @param end the upper bound of the range of confidential_ messageses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching confidential_ messageses
	 */
	public static List<Confidential_Messages> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Confidential_Messages> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first confidential_ messages in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching confidential_ messages
	 * @throws NoSuchConfidential_MessagesException if a matching confidential_ messages could not be found
	 */
	public static Confidential_Messages findByUuid_First(
			String uuid,
			OrderByComparator<Confidential_Messages> orderByComparator)
		throws rec.confidential.message.exception.
			NoSuchConfidential_MessagesException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first confidential_ messages in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching confidential_ messages, or <code>null</code> if a matching confidential_ messages could not be found
	 */
	public static Confidential_Messages fetchByUuid_First(
		String uuid,
		OrderByComparator<Confidential_Messages> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last confidential_ messages in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching confidential_ messages
	 * @throws NoSuchConfidential_MessagesException if a matching confidential_ messages could not be found
	 */
	public static Confidential_Messages findByUuid_Last(
			String uuid,
			OrderByComparator<Confidential_Messages> orderByComparator)
		throws rec.confidential.message.exception.
			NoSuchConfidential_MessagesException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last confidential_ messages in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching confidential_ messages, or <code>null</code> if a matching confidential_ messages could not be found
	 */
	public static Confidential_Messages fetchByUuid_Last(
		String uuid,
		OrderByComparator<Confidential_Messages> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the confidential_ messageses before and after the current confidential_ messages in the ordered set where uuid = &#63;.
	 *
	 * @param messageId the primary key of the current confidential_ messages
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next confidential_ messages
	 * @throws NoSuchConfidential_MessagesException if a confidential_ messages with the primary key could not be found
	 */
	public static Confidential_Messages[] findByUuid_PrevAndNext(
			long messageId, String uuid,
			OrderByComparator<Confidential_Messages> orderByComparator)
		throws rec.confidential.message.exception.
			NoSuchConfidential_MessagesException {

		return getPersistence().findByUuid_PrevAndNext(
			messageId, uuid, orderByComparator);
	}

	/**
	 * Removes all the confidential_ messageses where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of confidential_ messageses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching confidential_ messageses
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns all the confidential_ messageses where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching confidential_ messageses
	 */
	public static List<Confidential_Messages> findByUuid_C(
		String uuid, long companyId) {

		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the confidential_ messageses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>Confidential_MessagesModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of confidential_ messageses
	 * @param end the upper bound of the range of confidential_ messageses (not inclusive)
	 * @return the range of matching confidential_ messageses
	 */
	public static List<Confidential_Messages> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the confidential_ messageses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>Confidential_MessagesModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of confidential_ messageses
	 * @param end the upper bound of the range of confidential_ messageses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching confidential_ messageses
	 */
	public static List<Confidential_Messages> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Confidential_Messages> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the confidential_ messageses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>Confidential_MessagesModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of confidential_ messageses
	 * @param end the upper bound of the range of confidential_ messageses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching confidential_ messageses
	 */
	public static List<Confidential_Messages> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Confidential_Messages> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first confidential_ messages in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching confidential_ messages
	 * @throws NoSuchConfidential_MessagesException if a matching confidential_ messages could not be found
	 */
	public static Confidential_Messages findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<Confidential_Messages> orderByComparator)
		throws rec.confidential.message.exception.
			NoSuchConfidential_MessagesException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first confidential_ messages in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching confidential_ messages, or <code>null</code> if a matching confidential_ messages could not be found
	 */
	public static Confidential_Messages fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<Confidential_Messages> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last confidential_ messages in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching confidential_ messages
	 * @throws NoSuchConfidential_MessagesException if a matching confidential_ messages could not be found
	 */
	public static Confidential_Messages findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<Confidential_Messages> orderByComparator)
		throws rec.confidential.message.exception.
			NoSuchConfidential_MessagesException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last confidential_ messages in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching confidential_ messages, or <code>null</code> if a matching confidential_ messages could not be found
	 */
	public static Confidential_Messages fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<Confidential_Messages> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the confidential_ messageses before and after the current confidential_ messages in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param messageId the primary key of the current confidential_ messages
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next confidential_ messages
	 * @throws NoSuchConfidential_MessagesException if a confidential_ messages with the primary key could not be found
	 */
	public static Confidential_Messages[] findByUuid_C_PrevAndNext(
			long messageId, String uuid, long companyId,
			OrderByComparator<Confidential_Messages> orderByComparator)
		throws rec.confidential.message.exception.
			NoSuchConfidential_MessagesException {

		return getPersistence().findByUuid_C_PrevAndNext(
			messageId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the confidential_ messageses where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of confidential_ messageses where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching confidential_ messageses
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Caches the confidential_ messages in the entity cache if it is enabled.
	 *
	 * @param confidential_Messages the confidential_ messages
	 */
	public static void cacheResult(
		Confidential_Messages confidential_Messages) {

		getPersistence().cacheResult(confidential_Messages);
	}

	/**
	 * Caches the confidential_ messageses in the entity cache if it is enabled.
	 *
	 * @param confidential_Messageses the confidential_ messageses
	 */
	public static void cacheResult(
		List<Confidential_Messages> confidential_Messageses) {

		getPersistence().cacheResult(confidential_Messageses);
	}

	/**
	 * Creates a new confidential_ messages with the primary key. Does not add the confidential_ messages to the database.
	 *
	 * @param messageId the primary key for the new confidential_ messages
	 * @return the new confidential_ messages
	 */
	public static Confidential_Messages create(long messageId) {
		return getPersistence().create(messageId);
	}

	/**
	 * Removes the confidential_ messages with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param messageId the primary key of the confidential_ messages
	 * @return the confidential_ messages that was removed
	 * @throws NoSuchConfidential_MessagesException if a confidential_ messages with the primary key could not be found
	 */
	public static Confidential_Messages remove(long messageId)
		throws rec.confidential.message.exception.
			NoSuchConfidential_MessagesException {

		return getPersistence().remove(messageId);
	}

	public static Confidential_Messages updateImpl(
		Confidential_Messages confidential_Messages) {

		return getPersistence().updateImpl(confidential_Messages);
	}

	/**
	 * Returns the confidential_ messages with the primary key or throws a <code>NoSuchConfidential_MessagesException</code> if it could not be found.
	 *
	 * @param messageId the primary key of the confidential_ messages
	 * @return the confidential_ messages
	 * @throws NoSuchConfidential_MessagesException if a confidential_ messages with the primary key could not be found
	 */
	public static Confidential_Messages findByPrimaryKey(long messageId)
		throws rec.confidential.message.exception.
			NoSuchConfidential_MessagesException {

		return getPersistence().findByPrimaryKey(messageId);
	}

	/**
	 * Returns the confidential_ messages with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param messageId the primary key of the confidential_ messages
	 * @return the confidential_ messages, or <code>null</code> if a confidential_ messages with the primary key could not be found
	 */
	public static Confidential_Messages fetchByPrimaryKey(long messageId) {
		return getPersistence().fetchByPrimaryKey(messageId);
	}

	/**
	 * Returns all the confidential_ messageses.
	 *
	 * @return the confidential_ messageses
	 */
	public static List<Confidential_Messages> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the confidential_ messageses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>Confidential_MessagesModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of confidential_ messageses
	 * @param end the upper bound of the range of confidential_ messageses (not inclusive)
	 * @return the range of confidential_ messageses
	 */
	public static List<Confidential_Messages> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the confidential_ messageses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>Confidential_MessagesModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of confidential_ messageses
	 * @param end the upper bound of the range of confidential_ messageses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of confidential_ messageses
	 */
	public static List<Confidential_Messages> findAll(
		int start, int end,
		OrderByComparator<Confidential_Messages> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the confidential_ messageses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>Confidential_MessagesModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of confidential_ messageses
	 * @param end the upper bound of the range of confidential_ messageses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of confidential_ messageses
	 */
	public static List<Confidential_Messages> findAll(
		int start, int end,
		OrderByComparator<Confidential_Messages> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the confidential_ messageses from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of confidential_ messageses.
	 *
	 * @return the number of confidential_ messageses
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Confidential_MessagesPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<Confidential_MessagesPersistence, Confidential_MessagesPersistence>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			Confidential_MessagesPersistence.class);

		ServiceTracker
			<Confidential_MessagesPersistence, Confidential_MessagesPersistence>
				serviceTracker =
					new ServiceTracker
						<Confidential_MessagesPersistence,
						 Confidential_MessagesPersistence>(
							 bundle.getBundleContext(),
							 Confidential_MessagesPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}