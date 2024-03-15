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

package rec.supporting.resources.service.persistence;

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

import rec.supporting.resources.model.supportR;

/**
 * The persistence utility for the support r service. This utility wraps <code>rec.supporting.resources.service.persistence.impl.supportRPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see supportRPersistence
 * @generated
 */
public class supportRUtil {

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
	public static void clearCache(supportR supportR) {
		getPersistence().clearCache(supportR);
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
	public static Map<Serializable, supportR> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<supportR> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<supportR> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<supportR> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<supportR> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static supportR update(supportR supportR) {
		return getPersistence().update(supportR);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static supportR update(
		supportR supportR, ServiceContext serviceContext) {

		return getPersistence().update(supportR, serviceContext);
	}

	/**
	 * Returns all the support rs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching support rs
	 */
	public static List<supportR> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the support rs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @return the range of matching support rs
	 */
	public static List<supportR> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the support rs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching support rs
	 */
	public static List<supportR> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<supportR> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the support rs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching support rs
	 */
	public static List<supportR> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<supportR> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first support r in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	public static supportR findByUuid_First(
			String uuid, OrderByComparator<supportR> orderByComparator)
		throws rec.supporting.resources.exception.NoSuchsupportRException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first support r in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r, or <code>null</code> if a matching support r could not be found
	 */
	public static supportR fetchByUuid_First(
		String uuid, OrderByComparator<supportR> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last support r in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	public static supportR findByUuid_Last(
			String uuid, OrderByComparator<supportR> orderByComparator)
		throws rec.supporting.resources.exception.NoSuchsupportRException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last support r in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r, or <code>null</code> if a matching support r could not be found
	 */
	public static supportR fetchByUuid_Last(
		String uuid, OrderByComparator<supportR> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the support rs before and after the current support r in the ordered set where uuid = &#63;.
	 *
	 * @param supportRId the primary key of the current support r
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next support r
	 * @throws NoSuchsupportRException if a support r with the primary key could not be found
	 */
	public static supportR[] findByUuid_PrevAndNext(
			long supportRId, String uuid,
			OrderByComparator<supportR> orderByComparator)
		throws rec.supporting.resources.exception.NoSuchsupportRException {

		return getPersistence().findByUuid_PrevAndNext(
			supportRId, uuid, orderByComparator);
	}

	/**
	 * Removes all the support rs where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of support rs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching support rs
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the support r where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchsupportRException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	public static supportR findByUUID_G(String uuid, long groupId)
		throws rec.supporting.resources.exception.NoSuchsupportRException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the support r where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching support r, or <code>null</code> if a matching support r could not be found
	 */
	public static supportR fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the support r where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching support r, or <code>null</code> if a matching support r could not be found
	 */
	public static supportR fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, useFinderCache);
	}

	/**
	 * Removes the support r where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the support r that was removed
	 */
	public static supportR removeByUUID_G(String uuid, long groupId)
		throws rec.supporting.resources.exception.NoSuchsupportRException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of support rs where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching support rs
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the support rs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching support rs
	 */
	public static List<supportR> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the support rs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @return the range of matching support rs
	 */
	public static List<supportR> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the support rs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching support rs
	 */
	public static List<supportR> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<supportR> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the support rs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching support rs
	 */
	public static List<supportR> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<supportR> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first support r in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	public static supportR findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<supportR> orderByComparator)
		throws rec.supporting.resources.exception.NoSuchsupportRException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first support r in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r, or <code>null</code> if a matching support r could not be found
	 */
	public static supportR fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<supportR> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last support r in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	public static supportR findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<supportR> orderByComparator)
		throws rec.supporting.resources.exception.NoSuchsupportRException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last support r in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r, or <code>null</code> if a matching support r could not be found
	 */
	public static supportR fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<supportR> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the support rs before and after the current support r in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param supportRId the primary key of the current support r
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next support r
	 * @throws NoSuchsupportRException if a support r with the primary key could not be found
	 */
	public static supportR[] findByUuid_C_PrevAndNext(
			long supportRId, String uuid, long companyId,
			OrderByComparator<supportR> orderByComparator)
		throws rec.supporting.resources.exception.NoSuchsupportRException {

		return getPersistence().findByUuid_C_PrevAndNext(
			supportRId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the support rs where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of support rs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching support rs
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns all the support rs where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching support rs
	 */
	public static List<supportR> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	 * Returns a range of all the support rs where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @return the range of matching support rs
	 */
	public static List<supportR> findByGroupId(
		long groupId, int start, int end) {

		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	 * Returns an ordered range of all the support rs where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching support rs
	 */
	public static List<supportR> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<supportR> orderByComparator) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the support rs where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching support rs
	 */
	public static List<supportR> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<supportR> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first support r in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	public static supportR findByGroupId_First(
			long groupId, OrderByComparator<supportR> orderByComparator)
		throws rec.supporting.resources.exception.NoSuchsupportRException {

		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	 * Returns the first support r in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r, or <code>null</code> if a matching support r could not be found
	 */
	public static supportR fetchByGroupId_First(
		long groupId, OrderByComparator<supportR> orderByComparator) {

		return getPersistence().fetchByGroupId_First(
			groupId, orderByComparator);
	}

	/**
	 * Returns the last support r in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	public static supportR findByGroupId_Last(
			long groupId, OrderByComparator<supportR> orderByComparator)
		throws rec.supporting.resources.exception.NoSuchsupportRException {

		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the last support r in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r, or <code>null</code> if a matching support r could not be found
	 */
	public static supportR fetchByGroupId_Last(
		long groupId, OrderByComparator<supportR> orderByComparator) {

		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the support rs before and after the current support r in the ordered set where groupId = &#63;.
	 *
	 * @param supportRId the primary key of the current support r
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next support r
	 * @throws NoSuchsupportRException if a support r with the primary key could not be found
	 */
	public static supportR[] findByGroupId_PrevAndNext(
			long supportRId, long groupId,
			OrderByComparator<supportR> orderByComparator)
		throws rec.supporting.resources.exception.NoSuchsupportRException {

		return getPersistence().findByGroupId_PrevAndNext(
			supportRId, groupId, orderByComparator);
	}

	/**
	 * Removes all the support rs where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	 * Returns the number of support rs where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching support rs
	 */
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	 * Returns all the support rs where groupId = &#63; and title = &#63;.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @return the matching support rs
	 */
	public static List<supportR> findBySupportingResourceTitle(
		long groupId, String title) {

		return getPersistence().findBySupportingResourceTitle(groupId, title);
	}

	/**
	 * Returns a range of all the support rs where groupId = &#63; and title = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @return the range of matching support rs
	 */
	public static List<supportR> findBySupportingResourceTitle(
		long groupId, String title, int start, int end) {

		return getPersistence().findBySupportingResourceTitle(
			groupId, title, start, end);
	}

	/**
	 * Returns an ordered range of all the support rs where groupId = &#63; and title = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching support rs
	 */
	public static List<supportR> findBySupportingResourceTitle(
		long groupId, String title, int start, int end,
		OrderByComparator<supportR> orderByComparator) {

		return getPersistence().findBySupportingResourceTitle(
			groupId, title, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the support rs where groupId = &#63; and title = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching support rs
	 */
	public static List<supportR> findBySupportingResourceTitle(
		long groupId, String title, int start, int end,
		OrderByComparator<supportR> orderByComparator, boolean useFinderCache) {

		return getPersistence().findBySupportingResourceTitle(
			groupId, title, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first support r in the ordered set where groupId = &#63; and title = &#63;.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	public static supportR findBySupportingResourceTitle_First(
			long groupId, String title,
			OrderByComparator<supportR> orderByComparator)
		throws rec.supporting.resources.exception.NoSuchsupportRException {

		return getPersistence().findBySupportingResourceTitle_First(
			groupId, title, orderByComparator);
	}

	/**
	 * Returns the first support r in the ordered set where groupId = &#63; and title = &#63;.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r, or <code>null</code> if a matching support r could not be found
	 */
	public static supportR fetchBySupportingResourceTitle_First(
		long groupId, String title,
		OrderByComparator<supportR> orderByComparator) {

		return getPersistence().fetchBySupportingResourceTitle_First(
			groupId, title, orderByComparator);
	}

	/**
	 * Returns the last support r in the ordered set where groupId = &#63; and title = &#63;.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	public static supportR findBySupportingResourceTitle_Last(
			long groupId, String title,
			OrderByComparator<supportR> orderByComparator)
		throws rec.supporting.resources.exception.NoSuchsupportRException {

		return getPersistence().findBySupportingResourceTitle_Last(
			groupId, title, orderByComparator);
	}

	/**
	 * Returns the last support r in the ordered set where groupId = &#63; and title = &#63;.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r, or <code>null</code> if a matching support r could not be found
	 */
	public static supportR fetchBySupportingResourceTitle_Last(
		long groupId, String title,
		OrderByComparator<supportR> orderByComparator) {

		return getPersistence().fetchBySupportingResourceTitle_Last(
			groupId, title, orderByComparator);
	}

	/**
	 * Returns the support rs before and after the current support r in the ordered set where groupId = &#63; and title = &#63;.
	 *
	 * @param supportRId the primary key of the current support r
	 * @param groupId the group ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next support r
	 * @throws NoSuchsupportRException if a support r with the primary key could not be found
	 */
	public static supportR[] findBySupportingResourceTitle_PrevAndNext(
			long supportRId, long groupId, String title,
			OrderByComparator<supportR> orderByComparator)
		throws rec.supporting.resources.exception.NoSuchsupportRException {

		return getPersistence().findBySupportingResourceTitle_PrevAndNext(
			supportRId, groupId, title, orderByComparator);
	}

	/**
	 * Removes all the support rs where groupId = &#63; and title = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 */
	public static void removeBySupportingResourceTitle(
		long groupId, String title) {

		getPersistence().removeBySupportingResourceTitle(groupId, title);
	}

	/**
	 * Returns the number of support rs where groupId = &#63; and title = &#63;.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @return the number of matching support rs
	 */
	public static int countBySupportingResourceTitle(
		long groupId, String title) {

		return getPersistence().countBySupportingResourceTitle(groupId, title);
	}

	/**
	 * Returns all the support rs where groupId = &#63; and type = &#63;.
	 *
	 * @param groupId the group ID
	 * @param type the type
	 * @return the matching support rs
	 */
	public static List<supportR> findBySupportingResourceType(
		long groupId, String type) {

		return getPersistence().findBySupportingResourceType(groupId, type);
	}

	/**
	 * Returns a range of all the support rs where groupId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param type the type
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @return the range of matching support rs
	 */
	public static List<supportR> findBySupportingResourceType(
		long groupId, String type, int start, int end) {

		return getPersistence().findBySupportingResourceType(
			groupId, type, start, end);
	}

	/**
	 * Returns an ordered range of all the support rs where groupId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param type the type
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching support rs
	 */
	public static List<supportR> findBySupportingResourceType(
		long groupId, String type, int start, int end,
		OrderByComparator<supportR> orderByComparator) {

		return getPersistence().findBySupportingResourceType(
			groupId, type, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the support rs where groupId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param type the type
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching support rs
	 */
	public static List<supportR> findBySupportingResourceType(
		long groupId, String type, int start, int end,
		OrderByComparator<supportR> orderByComparator, boolean useFinderCache) {

		return getPersistence().findBySupportingResourceType(
			groupId, type, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first support r in the ordered set where groupId = &#63; and type = &#63;.
	 *
	 * @param groupId the group ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	public static supportR findBySupportingResourceType_First(
			long groupId, String type,
			OrderByComparator<supportR> orderByComparator)
		throws rec.supporting.resources.exception.NoSuchsupportRException {

		return getPersistence().findBySupportingResourceType_First(
			groupId, type, orderByComparator);
	}

	/**
	 * Returns the first support r in the ordered set where groupId = &#63; and type = &#63;.
	 *
	 * @param groupId the group ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r, or <code>null</code> if a matching support r could not be found
	 */
	public static supportR fetchBySupportingResourceType_First(
		long groupId, String type,
		OrderByComparator<supportR> orderByComparator) {

		return getPersistence().fetchBySupportingResourceType_First(
			groupId, type, orderByComparator);
	}

	/**
	 * Returns the last support r in the ordered set where groupId = &#63; and type = &#63;.
	 *
	 * @param groupId the group ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	public static supportR findBySupportingResourceType_Last(
			long groupId, String type,
			OrderByComparator<supportR> orderByComparator)
		throws rec.supporting.resources.exception.NoSuchsupportRException {

		return getPersistence().findBySupportingResourceType_Last(
			groupId, type, orderByComparator);
	}

	/**
	 * Returns the last support r in the ordered set where groupId = &#63; and type = &#63;.
	 *
	 * @param groupId the group ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r, or <code>null</code> if a matching support r could not be found
	 */
	public static supportR fetchBySupportingResourceType_Last(
		long groupId, String type,
		OrderByComparator<supportR> orderByComparator) {

		return getPersistence().fetchBySupportingResourceType_Last(
			groupId, type, orderByComparator);
	}

	/**
	 * Returns the support rs before and after the current support r in the ordered set where groupId = &#63; and type = &#63;.
	 *
	 * @param supportRId the primary key of the current support r
	 * @param groupId the group ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next support r
	 * @throws NoSuchsupportRException if a support r with the primary key could not be found
	 */
	public static supportR[] findBySupportingResourceType_PrevAndNext(
			long supportRId, long groupId, String type,
			OrderByComparator<supportR> orderByComparator)
		throws rec.supporting.resources.exception.NoSuchsupportRException {

		return getPersistence().findBySupportingResourceType_PrevAndNext(
			supportRId, groupId, type, orderByComparator);
	}

	/**
	 * Removes all the support rs where groupId = &#63; and type = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param type the type
	 */
	public static void removeBySupportingResourceType(
		long groupId, String type) {

		getPersistence().removeBySupportingResourceType(groupId, type);
	}

	/**
	 * Returns the number of support rs where groupId = &#63; and type = &#63;.
	 *
	 * @param groupId the group ID
	 * @param type the type
	 * @return the number of matching support rs
	 */
	public static int countBySupportingResourceType(long groupId, String type) {
		return getPersistence().countBySupportingResourceType(groupId, type);
	}

	/**
	 * Returns all the support rs where groupId = &#63; and createDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param createDate the create date
	 * @return the matching support rs
	 */
	public static List<supportR> findByCreateDate(
		long groupId, Date createDate) {

		return getPersistence().findByCreateDate(groupId, createDate);
	}

	/**
	 * Returns a range of all the support rs where groupId = &#63; and createDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param createDate the create date
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @return the range of matching support rs
	 */
	public static List<supportR> findByCreateDate(
		long groupId, Date createDate, int start, int end) {

		return getPersistence().findByCreateDate(
			groupId, createDate, start, end);
	}

	/**
	 * Returns an ordered range of all the support rs where groupId = &#63; and createDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param createDate the create date
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching support rs
	 */
	public static List<supportR> findByCreateDate(
		long groupId, Date createDate, int start, int end,
		OrderByComparator<supportR> orderByComparator) {

		return getPersistence().findByCreateDate(
			groupId, createDate, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the support rs where groupId = &#63; and createDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param createDate the create date
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching support rs
	 */
	public static List<supportR> findByCreateDate(
		long groupId, Date createDate, int start, int end,
		OrderByComparator<supportR> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByCreateDate(
			groupId, createDate, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first support r in the ordered set where groupId = &#63; and createDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	public static supportR findByCreateDate_First(
			long groupId, Date createDate,
			OrderByComparator<supportR> orderByComparator)
		throws rec.supporting.resources.exception.NoSuchsupportRException {

		return getPersistence().findByCreateDate_First(
			groupId, createDate, orderByComparator);
	}

	/**
	 * Returns the first support r in the ordered set where groupId = &#63; and createDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r, or <code>null</code> if a matching support r could not be found
	 */
	public static supportR fetchByCreateDate_First(
		long groupId, Date createDate,
		OrderByComparator<supportR> orderByComparator) {

		return getPersistence().fetchByCreateDate_First(
			groupId, createDate, orderByComparator);
	}

	/**
	 * Returns the last support r in the ordered set where groupId = &#63; and createDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	public static supportR findByCreateDate_Last(
			long groupId, Date createDate,
			OrderByComparator<supportR> orderByComparator)
		throws rec.supporting.resources.exception.NoSuchsupportRException {

		return getPersistence().findByCreateDate_Last(
			groupId, createDate, orderByComparator);
	}

	/**
	 * Returns the last support r in the ordered set where groupId = &#63; and createDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r, or <code>null</code> if a matching support r could not be found
	 */
	public static supportR fetchByCreateDate_Last(
		long groupId, Date createDate,
		OrderByComparator<supportR> orderByComparator) {

		return getPersistence().fetchByCreateDate_Last(
			groupId, createDate, orderByComparator);
	}

	/**
	 * Returns the support rs before and after the current support r in the ordered set where groupId = &#63; and createDate = &#63;.
	 *
	 * @param supportRId the primary key of the current support r
	 * @param groupId the group ID
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next support r
	 * @throws NoSuchsupportRException if a support r with the primary key could not be found
	 */
	public static supportR[] findByCreateDate_PrevAndNext(
			long supportRId, long groupId, Date createDate,
			OrderByComparator<supportR> orderByComparator)
		throws rec.supporting.resources.exception.NoSuchsupportRException {

		return getPersistence().findByCreateDate_PrevAndNext(
			supportRId, groupId, createDate, orderByComparator);
	}

	/**
	 * Removes all the support rs where groupId = &#63; and createDate = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param createDate the create date
	 */
	public static void removeByCreateDate(long groupId, Date createDate) {
		getPersistence().removeByCreateDate(groupId, createDate);
	}

	/**
	 * Returns the number of support rs where groupId = &#63; and createDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param createDate the create date
	 * @return the number of matching support rs
	 */
	public static int countByCreateDate(long groupId, Date createDate) {
		return getPersistence().countByCreateDate(groupId, createDate);
	}

	/**
	 * Returns all the support rs where groupId = &#63; and dueDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @return the matching support rs
	 */
	public static List<supportR> findByDueDate(long groupId, Date dueDate) {
		return getPersistence().findByDueDate(groupId, dueDate);
	}

	/**
	 * Returns a range of all the support rs where groupId = &#63; and dueDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @return the range of matching support rs
	 */
	public static List<supportR> findByDueDate(
		long groupId, Date dueDate, int start, int end) {

		return getPersistence().findByDueDate(groupId, dueDate, start, end);
	}

	/**
	 * Returns an ordered range of all the support rs where groupId = &#63; and dueDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching support rs
	 */
	public static List<supportR> findByDueDate(
		long groupId, Date dueDate, int start, int end,
		OrderByComparator<supportR> orderByComparator) {

		return getPersistence().findByDueDate(
			groupId, dueDate, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the support rs where groupId = &#63; and dueDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching support rs
	 */
	public static List<supportR> findByDueDate(
		long groupId, Date dueDate, int start, int end,
		OrderByComparator<supportR> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByDueDate(
			groupId, dueDate, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first support r in the ordered set where groupId = &#63; and dueDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	public static supportR findByDueDate_First(
			long groupId, Date dueDate,
			OrderByComparator<supportR> orderByComparator)
		throws rec.supporting.resources.exception.NoSuchsupportRException {

		return getPersistence().findByDueDate_First(
			groupId, dueDate, orderByComparator);
	}

	/**
	 * Returns the first support r in the ordered set where groupId = &#63; and dueDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r, or <code>null</code> if a matching support r could not be found
	 */
	public static supportR fetchByDueDate_First(
		long groupId, Date dueDate,
		OrderByComparator<supportR> orderByComparator) {

		return getPersistence().fetchByDueDate_First(
			groupId, dueDate, orderByComparator);
	}

	/**
	 * Returns the last support r in the ordered set where groupId = &#63; and dueDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	public static supportR findByDueDate_Last(
			long groupId, Date dueDate,
			OrderByComparator<supportR> orderByComparator)
		throws rec.supporting.resources.exception.NoSuchsupportRException {

		return getPersistence().findByDueDate_Last(
			groupId, dueDate, orderByComparator);
	}

	/**
	 * Returns the last support r in the ordered set where groupId = &#63; and dueDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r, or <code>null</code> if a matching support r could not be found
	 */
	public static supportR fetchByDueDate_Last(
		long groupId, Date dueDate,
		OrderByComparator<supportR> orderByComparator) {

		return getPersistence().fetchByDueDate_Last(
			groupId, dueDate, orderByComparator);
	}

	/**
	 * Returns the support rs before and after the current support r in the ordered set where groupId = &#63; and dueDate = &#63;.
	 *
	 * @param supportRId the primary key of the current support r
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next support r
	 * @throws NoSuchsupportRException if a support r with the primary key could not be found
	 */
	public static supportR[] findByDueDate_PrevAndNext(
			long supportRId, long groupId, Date dueDate,
			OrderByComparator<supportR> orderByComparator)
		throws rec.supporting.resources.exception.NoSuchsupportRException {

		return getPersistence().findByDueDate_PrevAndNext(
			supportRId, groupId, dueDate, orderByComparator);
	}

	/**
	 * Removes all the support rs where groupId = &#63; and dueDate = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param dueDate the due date
	 */
	public static void removeByDueDate(long groupId, Date dueDate) {
		getPersistence().removeByDueDate(groupId, dueDate);
	}

	/**
	 * Returns the number of support rs where groupId = &#63; and dueDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @return the number of matching support rs
	 */
	public static int countByDueDate(long groupId, Date dueDate) {
		return getPersistence().countByDueDate(groupId, dueDate);
	}

	/**
	 * Returns all the support rs where groupId = &#63; and dueDate = &#63; and createDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @param createDate the create date
	 * @return the matching support rs
	 */
	public static List<supportR> findByCreateDateAndDueDate(
		long groupId, Date dueDate, Date createDate) {

		return getPersistence().findByCreateDateAndDueDate(
			groupId, dueDate, createDate);
	}

	/**
	 * Returns a range of all the support rs where groupId = &#63; and dueDate = &#63; and createDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @param createDate the create date
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @return the range of matching support rs
	 */
	public static List<supportR> findByCreateDateAndDueDate(
		long groupId, Date dueDate, Date createDate, int start, int end) {

		return getPersistence().findByCreateDateAndDueDate(
			groupId, dueDate, createDate, start, end);
	}

	/**
	 * Returns an ordered range of all the support rs where groupId = &#63; and dueDate = &#63; and createDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @param createDate the create date
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching support rs
	 */
	public static List<supportR> findByCreateDateAndDueDate(
		long groupId, Date dueDate, Date createDate, int start, int end,
		OrderByComparator<supportR> orderByComparator) {

		return getPersistence().findByCreateDateAndDueDate(
			groupId, dueDate, createDate, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the support rs where groupId = &#63; and dueDate = &#63; and createDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @param createDate the create date
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching support rs
	 */
	public static List<supportR> findByCreateDateAndDueDate(
		long groupId, Date dueDate, Date createDate, int start, int end,
		OrderByComparator<supportR> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByCreateDateAndDueDate(
			groupId, dueDate, createDate, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first support r in the ordered set where groupId = &#63; and dueDate = &#63; and createDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	public static supportR findByCreateDateAndDueDate_First(
			long groupId, Date dueDate, Date createDate,
			OrderByComparator<supportR> orderByComparator)
		throws rec.supporting.resources.exception.NoSuchsupportRException {

		return getPersistence().findByCreateDateAndDueDate_First(
			groupId, dueDate, createDate, orderByComparator);
	}

	/**
	 * Returns the first support r in the ordered set where groupId = &#63; and dueDate = &#63; and createDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r, or <code>null</code> if a matching support r could not be found
	 */
	public static supportR fetchByCreateDateAndDueDate_First(
		long groupId, Date dueDate, Date createDate,
		OrderByComparator<supportR> orderByComparator) {

		return getPersistence().fetchByCreateDateAndDueDate_First(
			groupId, dueDate, createDate, orderByComparator);
	}

	/**
	 * Returns the last support r in the ordered set where groupId = &#63; and dueDate = &#63; and createDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	public static supportR findByCreateDateAndDueDate_Last(
			long groupId, Date dueDate, Date createDate,
			OrderByComparator<supportR> orderByComparator)
		throws rec.supporting.resources.exception.NoSuchsupportRException {

		return getPersistence().findByCreateDateAndDueDate_Last(
			groupId, dueDate, createDate, orderByComparator);
	}

	/**
	 * Returns the last support r in the ordered set where groupId = &#63; and dueDate = &#63; and createDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r, or <code>null</code> if a matching support r could not be found
	 */
	public static supportR fetchByCreateDateAndDueDate_Last(
		long groupId, Date dueDate, Date createDate,
		OrderByComparator<supportR> orderByComparator) {

		return getPersistence().fetchByCreateDateAndDueDate_Last(
			groupId, dueDate, createDate, orderByComparator);
	}

	/**
	 * Returns the support rs before and after the current support r in the ordered set where groupId = &#63; and dueDate = &#63; and createDate = &#63;.
	 *
	 * @param supportRId the primary key of the current support r
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next support r
	 * @throws NoSuchsupportRException if a support r with the primary key could not be found
	 */
	public static supportR[] findByCreateDateAndDueDate_PrevAndNext(
			long supportRId, long groupId, Date dueDate, Date createDate,
			OrderByComparator<supportR> orderByComparator)
		throws rec.supporting.resources.exception.NoSuchsupportRException {

		return getPersistence().findByCreateDateAndDueDate_PrevAndNext(
			supportRId, groupId, dueDate, createDate, orderByComparator);
	}

	/**
	 * Removes all the support rs where groupId = &#63; and dueDate = &#63; and createDate = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @param createDate the create date
	 */
	public static void removeByCreateDateAndDueDate(
		long groupId, Date dueDate, Date createDate) {

		getPersistence().removeByCreateDateAndDueDate(
			groupId, dueDate, createDate);
	}

	/**
	 * Returns the number of support rs where groupId = &#63; and dueDate = &#63; and createDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @param createDate the create date
	 * @return the number of matching support rs
	 */
	public static int countByCreateDateAndDueDate(
		long groupId, Date dueDate, Date createDate) {

		return getPersistence().countByCreateDateAndDueDate(
			groupId, dueDate, createDate);
	}

	/**
	 * Returns all the support rs where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the matching support rs
	 */
	public static List<supportR> findByStatus(long groupId, String status) {
		return getPersistence().findByStatus(groupId, status);
	}

	/**
	 * Returns a range of all the support rs where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @return the range of matching support rs
	 */
	public static List<supportR> findByStatus(
		long groupId, String status, int start, int end) {

		return getPersistence().findByStatus(groupId, status, start, end);
	}

	/**
	 * Returns an ordered range of all the support rs where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching support rs
	 */
	public static List<supportR> findByStatus(
		long groupId, String status, int start, int end,
		OrderByComparator<supportR> orderByComparator) {

		return getPersistence().findByStatus(
			groupId, status, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the support rs where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching support rs
	 */
	public static List<supportR> findByStatus(
		long groupId, String status, int start, int end,
		OrderByComparator<supportR> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByStatus(
			groupId, status, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first support r in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	public static supportR findByStatus_First(
			long groupId, String status,
			OrderByComparator<supportR> orderByComparator)
		throws rec.supporting.resources.exception.NoSuchsupportRException {

		return getPersistence().findByStatus_First(
			groupId, status, orderByComparator);
	}

	/**
	 * Returns the first support r in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r, or <code>null</code> if a matching support r could not be found
	 */
	public static supportR fetchByStatus_First(
		long groupId, String status,
		OrderByComparator<supportR> orderByComparator) {

		return getPersistence().fetchByStatus_First(
			groupId, status, orderByComparator);
	}

	/**
	 * Returns the last support r in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	public static supportR findByStatus_Last(
			long groupId, String status,
			OrderByComparator<supportR> orderByComparator)
		throws rec.supporting.resources.exception.NoSuchsupportRException {

		return getPersistence().findByStatus_Last(
			groupId, status, orderByComparator);
	}

	/**
	 * Returns the last support r in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r, or <code>null</code> if a matching support r could not be found
	 */
	public static supportR fetchByStatus_Last(
		long groupId, String status,
		OrderByComparator<supportR> orderByComparator) {

		return getPersistence().fetchByStatus_Last(
			groupId, status, orderByComparator);
	}

	/**
	 * Returns the support rs before and after the current support r in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param supportRId the primary key of the current support r
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next support r
	 * @throws NoSuchsupportRException if a support r with the primary key could not be found
	 */
	public static supportR[] findByStatus_PrevAndNext(
			long supportRId, long groupId, String status,
			OrderByComparator<supportR> orderByComparator)
		throws rec.supporting.resources.exception.NoSuchsupportRException {

		return getPersistence().findByStatus_PrevAndNext(
			supportRId, groupId, status, orderByComparator);
	}

	/**
	 * Removes all the support rs where groupId = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 */
	public static void removeByStatus(long groupId, String status) {
		getPersistence().removeByStatus(groupId, status);
	}

	/**
	 * Returns the number of support rs where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the number of matching support rs
	 */
	public static int countByStatus(long groupId, String status) {
		return getPersistence().countByStatus(groupId, status);
	}

	/**
	 * Returns all the support rs where status = &#63;.
	 *
	 * @param status the status
	 * @return the matching support rs
	 */
	public static List<supportR> findByStatusOnly(String status) {
		return getPersistence().findByStatusOnly(status);
	}

	/**
	 * Returns a range of all the support rs where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @return the range of matching support rs
	 */
	public static List<supportR> findByStatusOnly(
		String status, int start, int end) {

		return getPersistence().findByStatusOnly(status, start, end);
	}

	/**
	 * Returns an ordered range of all the support rs where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching support rs
	 */
	public static List<supportR> findByStatusOnly(
		String status, int start, int end,
		OrderByComparator<supportR> orderByComparator) {

		return getPersistence().findByStatusOnly(
			status, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the support rs where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching support rs
	 */
	public static List<supportR> findByStatusOnly(
		String status, int start, int end,
		OrderByComparator<supportR> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByStatusOnly(
			status, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first support r in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	public static supportR findByStatusOnly_First(
			String status, OrderByComparator<supportR> orderByComparator)
		throws rec.supporting.resources.exception.NoSuchsupportRException {

		return getPersistence().findByStatusOnly_First(
			status, orderByComparator);
	}

	/**
	 * Returns the first support r in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r, or <code>null</code> if a matching support r could not be found
	 */
	public static supportR fetchByStatusOnly_First(
		String status, OrderByComparator<supportR> orderByComparator) {

		return getPersistence().fetchByStatusOnly_First(
			status, orderByComparator);
	}

	/**
	 * Returns the last support r in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	public static supportR findByStatusOnly_Last(
			String status, OrderByComparator<supportR> orderByComparator)
		throws rec.supporting.resources.exception.NoSuchsupportRException {

		return getPersistence().findByStatusOnly_Last(
			status, orderByComparator);
	}

	/**
	 * Returns the last support r in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r, or <code>null</code> if a matching support r could not be found
	 */
	public static supportR fetchByStatusOnly_Last(
		String status, OrderByComparator<supportR> orderByComparator) {

		return getPersistence().fetchByStatusOnly_Last(
			status, orderByComparator);
	}

	/**
	 * Returns the support rs before and after the current support r in the ordered set where status = &#63;.
	 *
	 * @param supportRId the primary key of the current support r
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next support r
	 * @throws NoSuchsupportRException if a support r with the primary key could not be found
	 */
	public static supportR[] findByStatusOnly_PrevAndNext(
			long supportRId, String status,
			OrderByComparator<supportR> orderByComparator)
		throws rec.supporting.resources.exception.NoSuchsupportRException {

		return getPersistence().findByStatusOnly_PrevAndNext(
			supportRId, status, orderByComparator);
	}

	/**
	 * Removes all the support rs where status = &#63; from the database.
	 *
	 * @param status the status
	 */
	public static void removeByStatusOnly(String status) {
		getPersistence().removeByStatusOnly(status);
	}

	/**
	 * Returns the number of support rs where status = &#63;.
	 *
	 * @param status the status
	 * @return the number of matching support rs
	 */
	public static int countByStatusOnly(String status) {
		return getPersistence().countByStatusOnly(status);
	}

	/**
	 * Returns all the support rs where groupId = &#63; and link = &#63;.
	 *
	 * @param groupId the group ID
	 * @param link the link
	 * @return the matching support rs
	 */
	public static List<supportR> findByLink(long groupId, String link) {
		return getPersistence().findByLink(groupId, link);
	}

	/**
	 * Returns a range of all the support rs where groupId = &#63; and link = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param link the link
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @return the range of matching support rs
	 */
	public static List<supportR> findByLink(
		long groupId, String link, int start, int end) {

		return getPersistence().findByLink(groupId, link, start, end);
	}

	/**
	 * Returns an ordered range of all the support rs where groupId = &#63; and link = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param link the link
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching support rs
	 */
	public static List<supportR> findByLink(
		long groupId, String link, int start, int end,
		OrderByComparator<supportR> orderByComparator) {

		return getPersistence().findByLink(
			groupId, link, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the support rs where groupId = &#63; and link = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param link the link
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching support rs
	 */
	public static List<supportR> findByLink(
		long groupId, String link, int start, int end,
		OrderByComparator<supportR> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByLink(
			groupId, link, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first support r in the ordered set where groupId = &#63; and link = &#63;.
	 *
	 * @param groupId the group ID
	 * @param link the link
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	public static supportR findByLink_First(
			long groupId, String link,
			OrderByComparator<supportR> orderByComparator)
		throws rec.supporting.resources.exception.NoSuchsupportRException {

		return getPersistence().findByLink_First(
			groupId, link, orderByComparator);
	}

	/**
	 * Returns the first support r in the ordered set where groupId = &#63; and link = &#63;.
	 *
	 * @param groupId the group ID
	 * @param link the link
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r, or <code>null</code> if a matching support r could not be found
	 */
	public static supportR fetchByLink_First(
		long groupId, String link,
		OrderByComparator<supportR> orderByComparator) {

		return getPersistence().fetchByLink_First(
			groupId, link, orderByComparator);
	}

	/**
	 * Returns the last support r in the ordered set where groupId = &#63; and link = &#63;.
	 *
	 * @param groupId the group ID
	 * @param link the link
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	public static supportR findByLink_Last(
			long groupId, String link,
			OrderByComparator<supportR> orderByComparator)
		throws rec.supporting.resources.exception.NoSuchsupportRException {

		return getPersistence().findByLink_Last(
			groupId, link, orderByComparator);
	}

	/**
	 * Returns the last support r in the ordered set where groupId = &#63; and link = &#63;.
	 *
	 * @param groupId the group ID
	 * @param link the link
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r, or <code>null</code> if a matching support r could not be found
	 */
	public static supportR fetchByLink_Last(
		long groupId, String link,
		OrderByComparator<supportR> orderByComparator) {

		return getPersistence().fetchByLink_Last(
			groupId, link, orderByComparator);
	}

	/**
	 * Returns the support rs before and after the current support r in the ordered set where groupId = &#63; and link = &#63;.
	 *
	 * @param supportRId the primary key of the current support r
	 * @param groupId the group ID
	 * @param link the link
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next support r
	 * @throws NoSuchsupportRException if a support r with the primary key could not be found
	 */
	public static supportR[] findByLink_PrevAndNext(
			long supportRId, long groupId, String link,
			OrderByComparator<supportR> orderByComparator)
		throws rec.supporting.resources.exception.NoSuchsupportRException {

		return getPersistence().findByLink_PrevAndNext(
			supportRId, groupId, link, orderByComparator);
	}

	/**
	 * Removes all the support rs where groupId = &#63; and link = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param link the link
	 */
	public static void removeByLink(long groupId, String link) {
		getPersistence().removeByLink(groupId, link);
	}

	/**
	 * Returns the number of support rs where groupId = &#63; and link = &#63;.
	 *
	 * @param groupId the group ID
	 * @param link the link
	 * @return the number of matching support rs
	 */
	public static int countByLink(long groupId, String link) {
		return getPersistence().countByLink(groupId, link);
	}

	/**
	 * Caches the support r in the entity cache if it is enabled.
	 *
	 * @param supportR the support r
	 */
	public static void cacheResult(supportR supportR) {
		getPersistence().cacheResult(supportR);
	}

	/**
	 * Caches the support rs in the entity cache if it is enabled.
	 *
	 * @param supportRs the support rs
	 */
	public static void cacheResult(List<supportR> supportRs) {
		getPersistence().cacheResult(supportRs);
	}

	/**
	 * Creates a new support r with the primary key. Does not add the support r to the database.
	 *
	 * @param supportRId the primary key for the new support r
	 * @return the new support r
	 */
	public static supportR create(long supportRId) {
		return getPersistence().create(supportRId);
	}

	/**
	 * Removes the support r with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param supportRId the primary key of the support r
	 * @return the support r that was removed
	 * @throws NoSuchsupportRException if a support r with the primary key could not be found
	 */
	public static supportR remove(long supportRId)
		throws rec.supporting.resources.exception.NoSuchsupportRException {

		return getPersistence().remove(supportRId);
	}

	public static supportR updateImpl(supportR supportR) {
		return getPersistence().updateImpl(supportR);
	}

	/**
	 * Returns the support r with the primary key or throws a <code>NoSuchsupportRException</code> if it could not be found.
	 *
	 * @param supportRId the primary key of the support r
	 * @return the support r
	 * @throws NoSuchsupportRException if a support r with the primary key could not be found
	 */
	public static supportR findByPrimaryKey(long supportRId)
		throws rec.supporting.resources.exception.NoSuchsupportRException {

		return getPersistence().findByPrimaryKey(supportRId);
	}

	/**
	 * Returns the support r with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param supportRId the primary key of the support r
	 * @return the support r, or <code>null</code> if a support r with the primary key could not be found
	 */
	public static supportR fetchByPrimaryKey(long supportRId) {
		return getPersistence().fetchByPrimaryKey(supportRId);
	}

	/**
	 * Returns all the support rs.
	 *
	 * @return the support rs
	 */
	public static List<supportR> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the support rs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @return the range of support rs
	 */
	public static List<supportR> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the support rs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of support rs
	 */
	public static List<supportR> findAll(
		int start, int end, OrderByComparator<supportR> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the support rs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of support rs
	 */
	public static List<supportR> findAll(
		int start, int end, OrderByComparator<supportR> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the support rs from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of support rs.
	 *
	 * @return the number of support rs
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static supportRPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<supportRPersistence, supportRPersistence>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(supportRPersistence.class);

		ServiceTracker<supportRPersistence, supportRPersistence>
			serviceTracker =
				new ServiceTracker<supportRPersistence, supportRPersistence>(
					bundle.getBundleContext(), supportRPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}