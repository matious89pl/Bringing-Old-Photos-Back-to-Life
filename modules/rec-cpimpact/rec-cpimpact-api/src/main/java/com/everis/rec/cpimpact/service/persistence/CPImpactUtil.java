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

import com.everis.rec.cpimpact.model.CPImpact;

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
 * The persistence utility for the cp impact service. This utility wraps <code>com.everis.rec.cpimpact.service.persistence.impl.CPImpactPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CPImpactPersistence
 * @generated
 */
public class CPImpactUtil {

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
	public static void clearCache(CPImpact cpImpact) {
		getPersistence().clearCache(cpImpact);
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
	public static Map<Serializable, CPImpact> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<CPImpact> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CPImpact> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CPImpact> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CPImpact> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CPImpact update(CPImpact cpImpact) {
		return getPersistence().update(cpImpact);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CPImpact update(
		CPImpact cpImpact, ServiceContext serviceContext) {

		return getPersistence().update(cpImpact, serviceContext);
	}

	/**
	 * Returns all the cp impacts where impactCategory = &#63;.
	 *
	 * @param impactCategory the impact category
	 * @return the matching cp impacts
	 */
	public static List<CPImpact> findByImpactCategory(String impactCategory) {
		return getPersistence().findByImpactCategory(impactCategory);
	}

	/**
	 * Returns a range of all the cp impacts where impactCategory = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CPImpactModelImpl</code>.
	 * </p>
	 *
	 * @param impactCategory the impact category
	 * @param start the lower bound of the range of cp impacts
	 * @param end the upper bound of the range of cp impacts (not inclusive)
	 * @return the range of matching cp impacts
	 */
	public static List<CPImpact> findByImpactCategory(
		String impactCategory, int start, int end) {

		return getPersistence().findByImpactCategory(
			impactCategory, start, end);
	}

	/**
	 * Returns an ordered range of all the cp impacts where impactCategory = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CPImpactModelImpl</code>.
	 * </p>
	 *
	 * @param impactCategory the impact category
	 * @param start the lower bound of the range of cp impacts
	 * @param end the upper bound of the range of cp impacts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp impacts
	 */
	public static List<CPImpact> findByImpactCategory(
		String impactCategory, int start, int end,
		OrderByComparator<CPImpact> orderByComparator) {

		return getPersistence().findByImpactCategory(
			impactCategory, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the cp impacts where impactCategory = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CPImpactModelImpl</code>.
	 * </p>
	 *
	 * @param impactCategory the impact category
	 * @param start the lower bound of the range of cp impacts
	 * @param end the upper bound of the range of cp impacts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching cp impacts
	 */
	public static List<CPImpact> findByImpactCategory(
		String impactCategory, int start, int end,
		OrderByComparator<CPImpact> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByImpactCategory(
			impactCategory, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first cp impact in the ordered set where impactCategory = &#63;.
	 *
	 * @param impactCategory the impact category
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp impact
	 * @throws NoSuchCPImpactException if a matching cp impact could not be found
	 */
	public static CPImpact findByImpactCategory_First(
			String impactCategory,
			OrderByComparator<CPImpact> orderByComparator)
		throws com.everis.rec.cpimpact.exception.NoSuchCPImpactException {

		return getPersistence().findByImpactCategory_First(
			impactCategory, orderByComparator);
	}

	/**
	 * Returns the first cp impact in the ordered set where impactCategory = &#63;.
	 *
	 * @param impactCategory the impact category
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp impact, or <code>null</code> if a matching cp impact could not be found
	 */
	public static CPImpact fetchByImpactCategory_First(
		String impactCategory, OrderByComparator<CPImpact> orderByComparator) {

		return getPersistence().fetchByImpactCategory_First(
			impactCategory, orderByComparator);
	}

	/**
	 * Returns the last cp impact in the ordered set where impactCategory = &#63;.
	 *
	 * @param impactCategory the impact category
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp impact
	 * @throws NoSuchCPImpactException if a matching cp impact could not be found
	 */
	public static CPImpact findByImpactCategory_Last(
			String impactCategory,
			OrderByComparator<CPImpact> orderByComparator)
		throws com.everis.rec.cpimpact.exception.NoSuchCPImpactException {

		return getPersistence().findByImpactCategory_Last(
			impactCategory, orderByComparator);
	}

	/**
	 * Returns the last cp impact in the ordered set where impactCategory = &#63;.
	 *
	 * @param impactCategory the impact category
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp impact, or <code>null</code> if a matching cp impact could not be found
	 */
	public static CPImpact fetchByImpactCategory_Last(
		String impactCategory, OrderByComparator<CPImpact> orderByComparator) {

		return getPersistence().fetchByImpactCategory_Last(
			impactCategory, orderByComparator);
	}

	/**
	 * Returns the cp impacts before and after the current cp impact in the ordered set where impactCategory = &#63;.
	 *
	 * @param impactId the primary key of the current cp impact
	 * @param impactCategory the impact category
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp impact
	 * @throws NoSuchCPImpactException if a cp impact with the primary key could not be found
	 */
	public static CPImpact[] findByImpactCategory_PrevAndNext(
			long impactId, String impactCategory,
			OrderByComparator<CPImpact> orderByComparator)
		throws com.everis.rec.cpimpact.exception.NoSuchCPImpactException {

		return getPersistence().findByImpactCategory_PrevAndNext(
			impactId, impactCategory, orderByComparator);
	}

	/**
	 * Removes all the cp impacts where impactCategory = &#63; from the database.
	 *
	 * @param impactCategory the impact category
	 */
	public static void removeByImpactCategory(String impactCategory) {
		getPersistence().removeByImpactCategory(impactCategory);
	}

	/**
	 * Returns the number of cp impacts where impactCategory = &#63;.
	 *
	 * @param impactCategory the impact category
	 * @return the number of matching cp impacts
	 */
	public static int countByImpactCategory(String impactCategory) {
		return getPersistence().countByImpactCategory(impactCategory);
	}

	/**
	 * Caches the cp impact in the entity cache if it is enabled.
	 *
	 * @param cpImpact the cp impact
	 */
	public static void cacheResult(CPImpact cpImpact) {
		getPersistence().cacheResult(cpImpact);
	}

	/**
	 * Caches the cp impacts in the entity cache if it is enabled.
	 *
	 * @param cpImpacts the cp impacts
	 */
	public static void cacheResult(List<CPImpact> cpImpacts) {
		getPersistence().cacheResult(cpImpacts);
	}

	/**
	 * Creates a new cp impact with the primary key. Does not add the cp impact to the database.
	 *
	 * @param impactId the primary key for the new cp impact
	 * @return the new cp impact
	 */
	public static CPImpact create(long impactId) {
		return getPersistence().create(impactId);
	}

	/**
	 * Removes the cp impact with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param impactId the primary key of the cp impact
	 * @return the cp impact that was removed
	 * @throws NoSuchCPImpactException if a cp impact with the primary key could not be found
	 */
	public static CPImpact remove(long impactId)
		throws com.everis.rec.cpimpact.exception.NoSuchCPImpactException {

		return getPersistence().remove(impactId);
	}

	public static CPImpact updateImpl(CPImpact cpImpact) {
		return getPersistence().updateImpl(cpImpact);
	}

	/**
	 * Returns the cp impact with the primary key or throws a <code>NoSuchCPImpactException</code> if it could not be found.
	 *
	 * @param impactId the primary key of the cp impact
	 * @return the cp impact
	 * @throws NoSuchCPImpactException if a cp impact with the primary key could not be found
	 */
	public static CPImpact findByPrimaryKey(long impactId)
		throws com.everis.rec.cpimpact.exception.NoSuchCPImpactException {

		return getPersistence().findByPrimaryKey(impactId);
	}

	/**
	 * Returns the cp impact with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param impactId the primary key of the cp impact
	 * @return the cp impact, or <code>null</code> if a cp impact with the primary key could not be found
	 */
	public static CPImpact fetchByPrimaryKey(long impactId) {
		return getPersistence().fetchByPrimaryKey(impactId);
	}

	/**
	 * Returns all the cp impacts.
	 *
	 * @return the cp impacts
	 */
	public static List<CPImpact> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the cp impacts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CPImpactModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp impacts
	 * @param end the upper bound of the range of cp impacts (not inclusive)
	 * @return the range of cp impacts
	 */
	public static List<CPImpact> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the cp impacts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CPImpactModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp impacts
	 * @param end the upper bound of the range of cp impacts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cp impacts
	 */
	public static List<CPImpact> findAll(
		int start, int end, OrderByComparator<CPImpact> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the cp impacts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CPImpactModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp impacts
	 * @param end the upper bound of the range of cp impacts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of cp impacts
	 */
	public static List<CPImpact> findAll(
		int start, int end, OrderByComparator<CPImpact> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the cp impacts from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of cp impacts.
	 *
	 * @return the number of cp impacts
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static CPImpactPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CPImpactPersistence, CPImpactPersistence>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CPImpactPersistence.class);

		ServiceTracker<CPImpactPersistence, CPImpactPersistence>
			serviceTracker =
				new ServiceTracker<CPImpactPersistence, CPImpactPersistence>(
					bundle.getBundleContext(), CPImpactPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}