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

package com.everis.rec.validation.log.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ValidationLogLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ValidationLogLocalService
 * @generated
 */
public class ValidationLogLocalServiceWrapper
	implements ServiceWrapper<ValidationLogLocalService>,
			   ValidationLogLocalService {

	public ValidationLogLocalServiceWrapper(
		ValidationLogLocalService validationLogLocalService) {

		_validationLogLocalService = validationLogLocalService;
	}

	@Override
	public com.everis.rec.validation.log.model.ValidationLog
		addNewValidationLog(
			String fileName, String uploadedBy, String uploadedFrom,
			long folderId, String logReason, long groupId, long companyId) {

		return _validationLogLocalService.addNewValidationLog(
			fileName, uploadedBy, uploadedFrom, folderId, logReason, groupId,
			companyId);
	}

	/**
	 * Adds the validation log to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ValidationLogLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param validationLog the validation log
	 * @return the validation log that was added
	 */
	@Override
	public com.everis.rec.validation.log.model.ValidationLog addValidationLog(
		com.everis.rec.validation.log.model.ValidationLog validationLog) {

		return _validationLogLocalService.addValidationLog(validationLog);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _validationLogLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new validation log with the primary key. Does not add the validation log to the database.
	 *
	 * @param validationLogId the primary key for the new validation log
	 * @return the new validation log
	 */
	@Override
	public com.everis.rec.validation.log.model.ValidationLog
		createValidationLog(long validationLogId) {

		return _validationLogLocalService.createValidationLog(validationLogId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _validationLogLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the validation log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ValidationLogLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param validationLogId the primary key of the validation log
	 * @return the validation log that was removed
	 * @throws PortalException if a validation log with the primary key could not be found
	 */
	@Override
	public com.everis.rec.validation.log.model.ValidationLog
			deleteValidationLog(long validationLogId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _validationLogLocalService.deleteValidationLog(validationLogId);
	}

	/**
	 * Deletes the validation log from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ValidationLogLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param validationLog the validation log
	 * @return the validation log that was removed
	 */
	@Override
	public com.everis.rec.validation.log.model.ValidationLog
		deleteValidationLog(
			com.everis.rec.validation.log.model.ValidationLog validationLog) {

		return _validationLogLocalService.deleteValidationLog(validationLog);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _validationLogLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _validationLogLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.everis.rec.validation.log.model.impl.ValidationLogModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _validationLogLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.everis.rec.validation.log.model.impl.ValidationLogModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _validationLogLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _validationLogLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _validationLogLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.everis.rec.validation.log.model.ValidationLog fetchValidationLog(
		long validationLogId) {

		return _validationLogLocalService.fetchValidationLog(validationLogId);
	}

	@Override
	public java.util.List<com.everis.rec.validation.log.model.ValidationLog>
		findValidationLogs(int searchContainerStart, int searchContainerEnd) {

		return _validationLogLocalService.findValidationLogs(
			searchContainerStart, searchContainerEnd);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _validationLogLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _validationLogLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _validationLogLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _validationLogLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the validation log with the primary key.
	 *
	 * @param validationLogId the primary key of the validation log
	 * @return the validation log
	 * @throws PortalException if a validation log with the primary key could not be found
	 */
	@Override
	public com.everis.rec.validation.log.model.ValidationLog getValidationLog(
			long validationLogId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _validationLogLocalService.getValidationLog(validationLogId);
	}

	/**
	 * Returns a range of all the validation logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.everis.rec.validation.log.model.impl.ValidationLogModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of validation logs
	 * @param end the upper bound of the range of validation logs (not inclusive)
	 * @return the range of validation logs
	 */
	@Override
	public java.util.List<com.everis.rec.validation.log.model.ValidationLog>
		getValidationLogs(int start, int end) {

		return _validationLogLocalService.getValidationLogs(start, end);
	}

	/**
	 * Returns the number of validation logs.
	 *
	 * @return the number of validation logs
	 */
	@Override
	public int getValidationLogsCount() {
		return _validationLogLocalService.getValidationLogsCount();
	}

	/**
	 * Updates the validation log in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ValidationLogLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param validationLog the validation log
	 * @return the validation log that was updated
	 */
	@Override
	public com.everis.rec.validation.log.model.ValidationLog
		updateValidationLog(
			com.everis.rec.validation.log.model.ValidationLog validationLog) {

		return _validationLogLocalService.updateValidationLog(validationLog);
	}

	@Override
	public ValidationLogLocalService getWrappedService() {
		return _validationLogLocalService;
	}

	@Override
	public void setWrappedService(
		ValidationLogLocalService validationLogLocalService) {

		_validationLogLocalService = validationLogLocalService;
	}

	private ValidationLogLocalService _validationLogLocalService;

}