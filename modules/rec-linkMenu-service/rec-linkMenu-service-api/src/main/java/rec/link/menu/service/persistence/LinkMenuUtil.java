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

package rec.link.menu.service.persistence;

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

import rec.link.menu.model.LinkMenu;

/**
 * The persistence utility for the link menu service. This utility wraps <code>rec.link.menu.service.persistence.impl.LinkMenuPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LinkMenuPersistence
 * @generated
 */
public class LinkMenuUtil {

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
	public static void clearCache(LinkMenu linkMenu) {
		getPersistence().clearCache(linkMenu);
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
	public static Map<Serializable, LinkMenu> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<LinkMenu> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LinkMenu> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LinkMenu> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<LinkMenu> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static LinkMenu update(LinkMenu linkMenu) {
		return getPersistence().update(linkMenu);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static LinkMenu update(
		LinkMenu linkMenu, ServiceContext serviceContext) {

		return getPersistence().update(linkMenu, serviceContext);
	}

	/**
	 * Returns all the link menus where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching link menus
	 */
	public static List<LinkMenu> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the link menus where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LinkMenuModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of link menus
	 * @param end the upper bound of the range of link menus (not inclusive)
	 * @return the range of matching link menus
	 */
	public static List<LinkMenu> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the link menus where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LinkMenuModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of link menus
	 * @param end the upper bound of the range of link menus (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching link menus
	 */
	public static List<LinkMenu> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<LinkMenu> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the link menus where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LinkMenuModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of link menus
	 * @param end the upper bound of the range of link menus (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching link menus
	 */
	public static List<LinkMenu> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<LinkMenu> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first link menu in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching link menu
	 * @throws NoSuchLinkMenuException if a matching link menu could not be found
	 */
	public static LinkMenu findByUuid_First(
			String uuid, OrderByComparator<LinkMenu> orderByComparator)
		throws rec.link.menu.exception.NoSuchLinkMenuException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first link menu in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching link menu, or <code>null</code> if a matching link menu could not be found
	 */
	public static LinkMenu fetchByUuid_First(
		String uuid, OrderByComparator<LinkMenu> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last link menu in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching link menu
	 * @throws NoSuchLinkMenuException if a matching link menu could not be found
	 */
	public static LinkMenu findByUuid_Last(
			String uuid, OrderByComparator<LinkMenu> orderByComparator)
		throws rec.link.menu.exception.NoSuchLinkMenuException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last link menu in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching link menu, or <code>null</code> if a matching link menu could not be found
	 */
	public static LinkMenu fetchByUuid_Last(
		String uuid, OrderByComparator<LinkMenu> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the link menus before and after the current link menu in the ordered set where uuid = &#63;.
	 *
	 * @param linkId the primary key of the current link menu
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next link menu
	 * @throws NoSuchLinkMenuException if a link menu with the primary key could not be found
	 */
	public static LinkMenu[] findByUuid_PrevAndNext(
			long linkId, String uuid,
			OrderByComparator<LinkMenu> orderByComparator)
		throws rec.link.menu.exception.NoSuchLinkMenuException {

		return getPersistence().findByUuid_PrevAndNext(
			linkId, uuid, orderByComparator);
	}

	/**
	 * Removes all the link menus where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of link menus where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching link menus
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the link menu where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchLinkMenuException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching link menu
	 * @throws NoSuchLinkMenuException if a matching link menu could not be found
	 */
	public static LinkMenu findByUUID_G(String uuid, long groupId)
		throws rec.link.menu.exception.NoSuchLinkMenuException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the link menu where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching link menu, or <code>null</code> if a matching link menu could not be found
	 */
	public static LinkMenu fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the link menu where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching link menu, or <code>null</code> if a matching link menu could not be found
	 */
	public static LinkMenu fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, useFinderCache);
	}

	/**
	 * Removes the link menu where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the link menu that was removed
	 */
	public static LinkMenu removeByUUID_G(String uuid, long groupId)
		throws rec.link.menu.exception.NoSuchLinkMenuException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of link menus where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching link menus
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the link menus where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching link menus
	 */
	public static List<LinkMenu> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the link menus where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LinkMenuModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of link menus
	 * @param end the upper bound of the range of link menus (not inclusive)
	 * @return the range of matching link menus
	 */
	public static List<LinkMenu> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the link menus where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LinkMenuModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of link menus
	 * @param end the upper bound of the range of link menus (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching link menus
	 */
	public static List<LinkMenu> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<LinkMenu> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the link menus where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LinkMenuModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of link menus
	 * @param end the upper bound of the range of link menus (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching link menus
	 */
	public static List<LinkMenu> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<LinkMenu> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first link menu in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching link menu
	 * @throws NoSuchLinkMenuException if a matching link menu could not be found
	 */
	public static LinkMenu findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<LinkMenu> orderByComparator)
		throws rec.link.menu.exception.NoSuchLinkMenuException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first link menu in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching link menu, or <code>null</code> if a matching link menu could not be found
	 */
	public static LinkMenu fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<LinkMenu> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last link menu in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching link menu
	 * @throws NoSuchLinkMenuException if a matching link menu could not be found
	 */
	public static LinkMenu findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<LinkMenu> orderByComparator)
		throws rec.link.menu.exception.NoSuchLinkMenuException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last link menu in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching link menu, or <code>null</code> if a matching link menu could not be found
	 */
	public static LinkMenu fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<LinkMenu> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the link menus before and after the current link menu in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param linkId the primary key of the current link menu
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next link menu
	 * @throws NoSuchLinkMenuException if a link menu with the primary key could not be found
	 */
	public static LinkMenu[] findByUuid_C_PrevAndNext(
			long linkId, String uuid, long companyId,
			OrderByComparator<LinkMenu> orderByComparator)
		throws rec.link.menu.exception.NoSuchLinkMenuException {

		return getPersistence().findByUuid_C_PrevAndNext(
			linkId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the link menus where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of link menus where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching link menus
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Caches the link menu in the entity cache if it is enabled.
	 *
	 * @param linkMenu the link menu
	 */
	public static void cacheResult(LinkMenu linkMenu) {
		getPersistence().cacheResult(linkMenu);
	}

	/**
	 * Caches the link menus in the entity cache if it is enabled.
	 *
	 * @param linkMenus the link menus
	 */
	public static void cacheResult(List<LinkMenu> linkMenus) {
		getPersistence().cacheResult(linkMenus);
	}

	/**
	 * Creates a new link menu with the primary key. Does not add the link menu to the database.
	 *
	 * @param linkId the primary key for the new link menu
	 * @return the new link menu
	 */
	public static LinkMenu create(long linkId) {
		return getPersistence().create(linkId);
	}

	/**
	 * Removes the link menu with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param linkId the primary key of the link menu
	 * @return the link menu that was removed
	 * @throws NoSuchLinkMenuException if a link menu with the primary key could not be found
	 */
	public static LinkMenu remove(long linkId)
		throws rec.link.menu.exception.NoSuchLinkMenuException {

		return getPersistence().remove(linkId);
	}

	public static LinkMenu updateImpl(LinkMenu linkMenu) {
		return getPersistence().updateImpl(linkMenu);
	}

	/**
	 * Returns the link menu with the primary key or throws a <code>NoSuchLinkMenuException</code> if it could not be found.
	 *
	 * @param linkId the primary key of the link menu
	 * @return the link menu
	 * @throws NoSuchLinkMenuException if a link menu with the primary key could not be found
	 */
	public static LinkMenu findByPrimaryKey(long linkId)
		throws rec.link.menu.exception.NoSuchLinkMenuException {

		return getPersistence().findByPrimaryKey(linkId);
	}

	/**
	 * Returns the link menu with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param linkId the primary key of the link menu
	 * @return the link menu, or <code>null</code> if a link menu with the primary key could not be found
	 */
	public static LinkMenu fetchByPrimaryKey(long linkId) {
		return getPersistence().fetchByPrimaryKey(linkId);
	}

	/**
	 * Returns all the link menus.
	 *
	 * @return the link menus
	 */
	public static List<LinkMenu> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the link menus.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LinkMenuModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of link menus
	 * @param end the upper bound of the range of link menus (not inclusive)
	 * @return the range of link menus
	 */
	public static List<LinkMenu> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the link menus.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LinkMenuModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of link menus
	 * @param end the upper bound of the range of link menus (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of link menus
	 */
	public static List<LinkMenu> findAll(
		int start, int end, OrderByComparator<LinkMenu> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the link menus.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LinkMenuModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of link menus
	 * @param end the upper bound of the range of link menus (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of link menus
	 */
	public static List<LinkMenu> findAll(
		int start, int end, OrderByComparator<LinkMenu> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the link menus from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of link menus.
	 *
	 * @return the number of link menus
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static LinkMenuPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LinkMenuPersistence, LinkMenuPersistence>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(LinkMenuPersistence.class);

		ServiceTracker<LinkMenuPersistence, LinkMenuPersistence>
			serviceTracker =
				new ServiceTracker<LinkMenuPersistence, LinkMenuPersistence>(
					bundle.getBundleContext(), LinkMenuPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}