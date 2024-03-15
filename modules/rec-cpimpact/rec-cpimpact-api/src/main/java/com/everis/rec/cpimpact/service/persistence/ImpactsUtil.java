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

package com.everis.rec.cpimpact.service.persistence;

import com.everis.rec.cpimpact.model.Impacts;

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
 * The persistence utility for the impacts service. This utility wraps <code>com.everis.rec.cpimpact.service.persistence.impl.ImpactsPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ImpactsPersistence
 * @generated
 */
public class ImpactsUtil {

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
	public static void clearCache(Impacts impacts) {
		getPersistence().clearCache(impacts);
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
	public static Map<Serializable, Impacts> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Impacts> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Impacts> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Impacts> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Impacts> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Impacts update(Impacts impacts) {
		return getPersistence().update(impacts);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Impacts update(
		Impacts impacts, ServiceContext serviceContext) {

		return getPersistence().update(impacts, serviceContext);
	}

	/**
	 * Returns all the impactses where impactCategory = &#63;.
	 *
	 * @param impactCategory the impact category
	 * @return the matching impactses
	 */
	public static List<Impacts> findByImpactCategory(String impactCategory) {
		return getPersistence().findByImpactCategory(impactCategory);
	}

	/**
	 * Returns a range of all the impactses where impactCategory = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ImpactsModelImpl</code>.
	 * </p>
	 *
	 * @param impactCategory the impact category
	 * @param start the lower bound of the range of impactses
	 * @param end the upper bound of the range of impactses (not inclusive)
	 * @return the range of matching impactses
	 */
	public static List<Impacts> findByImpactCategory(
		String impactCategory, int start, int end) {

		return getPersistence().findByImpactCategory(
			impactCategory, start, end);
	}

	/**
	 * Returns an ordered range of all the impactses where impactCategory = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ImpactsModelImpl</code>.
	 * </p>
	 *
	 * @param impactCategory the impact category
	 * @param start the lower bound of the range of impactses
	 * @param end the upper bound of the range of impactses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching impactses
	 */
	public static List<Impacts> findByImpactCategory(
		String impactCategory, int start, int end,
		OrderByComparator<Impacts> orderByComparator) {

		return getPersistence().findByImpactCategory(
			impactCategory, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the impactses where impactCategory = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ImpactsModelImpl</code>.
	 * </p>
	 *
	 * @param impactCategory the impact category
	 * @param start the lower bound of the range of impactses
	 * @param end the upper bound of the range of impactses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching impactses
	 */
	public static List<Impacts> findByImpactCategory(
		String impactCategory, int start, int end,
		OrderByComparator<Impacts> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByImpactCategory(
			impactCategory, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first impacts in the ordered set where impactCategory = &#63;.
	 *
	 * @param impactCategory the impact category
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching impacts
	 * @throws NoSuchImpactsException if a matching impacts could not be found
	 */
	public static Impacts findByImpactCategory_First(
			String impactCategory, OrderByComparator<Impacts> orderByComparator)
		throws com.everis.rec.cpimpact.exception.NoSuchImpactsException {

		return getPersistence().findByImpactCategory_First(
			impactCategory, orderByComparator);
	}

	/**
	 * Returns the first impacts in the ordered set where impactCategory = &#63;.
	 *
	 * @param impactCategory the impact category
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching impacts, or <code>null</code> if a matching impacts could not be found
	 */
	public static Impacts fetchByImpactCategory_First(
		String impactCategory, OrderByComparator<Impacts> orderByComparator) {

		return getPersistence().fetchByImpactCategory_First(
			impactCategory, orderByComparator);
	}

	/**
	 * Returns the last impacts in the ordered set where impactCategory = &#63;.
	 *
	 * @param impactCategory the impact category
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching impacts
	 * @throws NoSuchImpactsException if a matching impacts could not be found
	 */
	public static Impacts findByImpactCategory_Last(
			String impactCategory, OrderByComparator<Impacts> orderByComparator)
		throws com.everis.rec.cpimpact.exception.NoSuchImpactsException {

		return getPersistence().findByImpactCategory_Last(
			impactCategory, orderByComparator);
	}

	/**
	 * Returns the last impacts in the ordered set where impactCategory = &#63;.
	 *
	 * @param impactCategory the impact category
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching impacts, or <code>null</code> if a matching impacts could not be found
	 */
	public static Impacts fetchByImpactCategory_Last(
		String impactCategory, OrderByComparator<Impacts> orderByComparator) {

		return getPersistence().fetchByImpactCategory_Last(
			impactCategory, orderByComparator);
	}

	/**
	 * Returns the impactses before and after the current impacts in the ordered set where impactCategory = &#63;.
	 *
	 * @param impactId the primary key of the current impacts
	 * @param impactCategory the impact category
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next impacts
	 * @throws NoSuchImpactsException if a impacts with the primary key could not be found
	 */
	public static Impacts[] findByImpactCategory_PrevAndNext(
			long impactId, String impactCategory,
			OrderByComparator<Impacts> orderByComparator)
		throws com.everis.rec.cpimpact.exception.NoSuchImpactsException {

		return getPersistence().findByImpactCategory_PrevAndNext(
			impactId, impactCategory, orderByComparator);
	}

	/**
	 * Removes all the impactses where impactCategory = &#63; from the database.
	 *
	 * @param impactCategory the impact category
	 */
	public static void removeByImpactCategory(String impactCategory) {
		getPersistence().removeByImpactCategory(impactCategory);
	}

	/**
	 * Returns the number of impactses where impactCategory = &#63;.
	 *
	 * @param impactCategory the impact category
	 * @return the number of matching impactses
	 */
	public static int countByImpactCategory(String impactCategory) {
		return getPersistence().countByImpactCategory(impactCategory);
	}

	/**
	 * Caches the impacts in the entity cache if it is enabled.
	 *
	 * @param impacts the impacts
	 */
	public static void cacheResult(Impacts impacts) {
		getPersistence().cacheResult(impacts);
	}

	/**
	 * Caches the impactses in the entity cache if it is enabled.
	 *
	 * @param impactses the impactses
	 */
	public static void cacheResult(List<Impacts> impactses) {
		getPersistence().cacheResult(impactses);
	}

	/**
	 * Creates a new impacts with the primary key. Does not add the impacts to the database.
	 *
	 * @param impactId the primary key for the new impacts
	 * @return the new impacts
	 */
	public static Impacts create(long impactId) {
		return getPersistence().create(impactId);
	}

	/**
	 * Removes the impacts with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param impactId the primary key of the impacts
	 * @return the impacts that was removed
	 * @throws NoSuchImpactsException if a impacts with the primary key could not be found
	 */
	public static Impacts remove(long impactId)
		throws com.everis.rec.cpimpact.exception.NoSuchImpactsException {

		return getPersistence().remove(impactId);
	}

	public static Impacts updateImpl(Impacts impacts) {
		return getPersistence().updateImpl(impacts);
	}

	/**
	 * Returns the impacts with the primary key or throws a <code>NoSuchImpactsException</code> if it could not be found.
	 *
	 * @param impactId the primary key of the impacts
	 * @return the impacts
	 * @throws NoSuchImpactsException if a impacts with the primary key could not be found
	 */
	public static Impacts findByPrimaryKey(long impactId)
		throws com.everis.rec.cpimpact.exception.NoSuchImpactsException {

		return getPersistence().findByPrimaryKey(impactId);
	}

	/**
	 * Returns the impacts with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param impactId the primary key of the impacts
	 * @return the impacts, or <code>null</code> if a impacts with the primary key could not be found
	 */
	public static Impacts fetchByPrimaryKey(long impactId) {
		return getPersistence().fetchByPrimaryKey(impactId);
	}

	/**
	 * Returns all the impactses.
	 *
	 * @return the impactses
	 */
	public static List<Impacts> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the impactses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ImpactsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of impactses
	 * @param end the upper bound of the range of impactses (not inclusive)
	 * @return the range of impactses
	 */
	public static List<Impacts> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the impactses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ImpactsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of impactses
	 * @param end the upper bound of the range of impactses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of impactses
	 */
	public static List<Impacts> findAll(
		int start, int end, OrderByComparator<Impacts> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the impactses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ImpactsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of impactses
	 * @param end the upper bound of the range of impactses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of impactses
	 */
	public static List<Impacts> findAll(
		int start, int end, OrderByComparator<Impacts> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the impactses from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of impactses.
	 *
	 * @return the number of impactses
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ImpactsPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ImpactsPersistence, ImpactsPersistence>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ImpactsPersistence.class);

		ServiceTracker<ImpactsPersistence, ImpactsPersistence> serviceTracker =
			new ServiceTracker<ImpactsPersistence, ImpactsPersistence>(
				bundle.getBundleContext(), ImpactsPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}