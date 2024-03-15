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

package com.everis.rec.validation.log.service.persistence;

import com.everis.rec.validation.log.model.ValidationLog;

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
 * The persistence utility for the validation log service. This utility wraps <code>com.everis.rec.validation.log.service.persistence.impl.ValidationLogPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ValidationLogPersistence
 * @generated
 */
public class ValidationLogUtil {

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
	public static void clearCache(ValidationLog validationLog) {
		getPersistence().clearCache(validationLog);
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
	public static Map<Serializable, ValidationLog> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ValidationLog> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ValidationLog> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ValidationLog> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ValidationLog> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ValidationLog update(ValidationLog validationLog) {
		return getPersistence().update(validationLog);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ValidationLog update(
		ValidationLog validationLog, ServiceContext serviceContext) {

		return getPersistence().update(validationLog, serviceContext);
	}

	/**
	 * Caches the validation log in the entity cache if it is enabled.
	 *
	 * @param validationLog the validation log
	 */
	public static void cacheResult(ValidationLog validationLog) {
		getPersistence().cacheResult(validationLog);
	}

	/**
	 * Caches the validation logs in the entity cache if it is enabled.
	 *
	 * @param validationLogs the validation logs
	 */
	public static void cacheResult(List<ValidationLog> validationLogs) {
		getPersistence().cacheResult(validationLogs);
	}

	/**
	 * Creates a new validation log with the primary key. Does not add the validation log to the database.
	 *
	 * @param validationLogId the primary key for the new validation log
	 * @return the new validation log
	 */
	public static ValidationLog create(long validationLogId) {
		return getPersistence().create(validationLogId);
	}

	/**
	 * Removes the validation log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param validationLogId the primary key of the validation log
	 * @return the validation log that was removed
	 * @throws NoSuchValidationLogException if a validation log with the primary key could not be found
	 */
	public static ValidationLog remove(long validationLogId)
		throws com.everis.rec.validation.log.exception.
			NoSuchValidationLogException {

		return getPersistence().remove(validationLogId);
	}

	public static ValidationLog updateImpl(ValidationLog validationLog) {
		return getPersistence().updateImpl(validationLog);
	}

	/**
	 * Returns the validation log with the primary key or throws a <code>NoSuchValidationLogException</code> if it could not be found.
	 *
	 * @param validationLogId the primary key of the validation log
	 * @return the validation log
	 * @throws NoSuchValidationLogException if a validation log with the primary key could not be found
	 */
	public static ValidationLog findByPrimaryKey(long validationLogId)
		throws com.everis.rec.validation.log.exception.
			NoSuchValidationLogException {

		return getPersistence().findByPrimaryKey(validationLogId);
	}

	/**
	 * Returns the validation log with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param validationLogId the primary key of the validation log
	 * @return the validation log, or <code>null</code> if a validation log with the primary key could not be found
	 */
	public static ValidationLog fetchByPrimaryKey(long validationLogId) {
		return getPersistence().fetchByPrimaryKey(validationLogId);
	}

	/**
	 * Returns all the validation logs.
	 *
	 * @return the validation logs
	 */
	public static List<ValidationLog> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the validation logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ValidationLogModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of validation logs
	 * @param end the upper bound of the range of validation logs (not inclusive)
	 * @return the range of validation logs
	 */
	public static List<ValidationLog> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the validation logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ValidationLogModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of validation logs
	 * @param end the upper bound of the range of validation logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of validation logs
	 */
	public static List<ValidationLog> findAll(
		int start, int end,
		OrderByComparator<ValidationLog> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the validation logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ValidationLogModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of validation logs
	 * @param end the upper bound of the range of validation logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of validation logs
	 */
	public static List<ValidationLog> findAll(
		int start, int end, OrderByComparator<ValidationLog> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the validation logs from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of validation logs.
	 *
	 * @return the number of validation logs
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ValidationLogPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<ValidationLogPersistence, ValidationLogPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ValidationLogPersistence.class);

		ServiceTracker<ValidationLogPersistence, ValidationLogPersistence>
			serviceTracker =
				new ServiceTracker
					<ValidationLogPersistence, ValidationLogPersistence>(
						bundle.getBundleContext(),
						ValidationLogPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}