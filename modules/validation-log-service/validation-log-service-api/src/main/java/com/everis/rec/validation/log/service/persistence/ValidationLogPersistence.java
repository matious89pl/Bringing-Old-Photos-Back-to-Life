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

import com.everis.rec.validation.log.exception.NoSuchValidationLogException;
import com.everis.rec.validation.log.model.ValidationLog;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the validation log service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ValidationLogUtil
 * @generated
 */
@ProviderType
public interface ValidationLogPersistence
	extends BasePersistence<ValidationLog> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ValidationLogUtil} to access the validation log persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the validation log in the entity cache if it is enabled.
	 *
	 * @param validationLog the validation log
	 */
	public void cacheResult(ValidationLog validationLog);

	/**
	 * Caches the validation logs in the entity cache if it is enabled.
	 *
	 * @param validationLogs the validation logs
	 */
	public void cacheResult(java.util.List<ValidationLog> validationLogs);

	/**
	 * Creates a new validation log with the primary key. Does not add the validation log to the database.
	 *
	 * @param validationLogId the primary key for the new validation log
	 * @return the new validation log
	 */
	public ValidationLog create(long validationLogId);

	/**
	 * Removes the validation log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param validationLogId the primary key of the validation log
	 * @return the validation log that was removed
	 * @throws NoSuchValidationLogException if a validation log with the primary key could not be found
	 */
	public ValidationLog remove(long validationLogId)
		throws NoSuchValidationLogException;

	public ValidationLog updateImpl(ValidationLog validationLog);

	/**
	 * Returns the validation log with the primary key or throws a <code>NoSuchValidationLogException</code> if it could not be found.
	 *
	 * @param validationLogId the primary key of the validation log
	 * @return the validation log
	 * @throws NoSuchValidationLogException if a validation log with the primary key could not be found
	 */
	public ValidationLog findByPrimaryKey(long validationLogId)
		throws NoSuchValidationLogException;

	/**
	 * Returns the validation log with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param validationLogId the primary key of the validation log
	 * @return the validation log, or <code>null</code> if a validation log with the primary key could not be found
	 */
	public ValidationLog fetchByPrimaryKey(long validationLogId);

	/**
	 * Returns all the validation logs.
	 *
	 * @return the validation logs
	 */
	public java.util.List<ValidationLog> findAll();

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
	public java.util.List<ValidationLog> findAll(int start, int end);

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
	public java.util.List<ValidationLog> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ValidationLog>
			orderByComparator);

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
	public java.util.List<ValidationLog> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ValidationLog>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the validation logs from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of validation logs.
	 *
	 * @return the number of validation logs
	 */
	public int countAll();

}