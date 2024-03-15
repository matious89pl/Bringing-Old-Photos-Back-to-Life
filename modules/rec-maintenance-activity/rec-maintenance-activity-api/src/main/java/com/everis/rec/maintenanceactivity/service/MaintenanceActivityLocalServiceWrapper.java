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

package com.everis.rec.maintenanceactivity.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MaintenanceActivityLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see MaintenanceActivityLocalService
 * @generated
 */
public class MaintenanceActivityLocalServiceWrapper
	implements MaintenanceActivityLocalService,
			   ServiceWrapper<MaintenanceActivityLocalService> {

	public MaintenanceActivityLocalServiceWrapper(
		MaintenanceActivityLocalService maintenanceActivityLocalService) {

		_maintenanceActivityLocalService = maintenanceActivityLocalService;
	}

	/**
	 * Adds the maintenance activity to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MaintenanceActivityLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param maintenanceActivity the maintenance activity
	 * @return the maintenance activity that was added
	 */
	@Override
	public com.everis.rec.maintenanceactivity.model.MaintenanceActivity
		addMaintenanceActivity(
			com.everis.rec.maintenanceactivity.model.MaintenanceActivity
				maintenanceActivity) {

		return _maintenanceActivityLocalService.addMaintenanceActivity(
			maintenanceActivity);
	}

	/**
	 * Creates a new maintenance activity with the primary key. Does not add the maintenance activity to the database.
	 *
	 * @param maintenanceactivityId the primary key for the new maintenance activity
	 * @return the new maintenance activity
	 */
	@Override
	public com.everis.rec.maintenanceactivity.model.MaintenanceActivity
		createMaintenanceActivity(long maintenanceactivityId) {

		return _maintenanceActivityLocalService.createMaintenanceActivity(
			maintenanceactivityId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _maintenanceActivityLocalService.createPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Deletes the maintenance activity with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MaintenanceActivityLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param maintenanceactivityId the primary key of the maintenance activity
	 * @return the maintenance activity that was removed
	 * @throws PortalException if a maintenance activity with the primary key could not be found
	 */
	@Override
	public com.everis.rec.maintenanceactivity.model.MaintenanceActivity
			deleteMaintenanceActivity(long maintenanceactivityId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _maintenanceActivityLocalService.deleteMaintenanceActivity(
			maintenanceactivityId);
	}

	/**
	 * Deletes the maintenance activity from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MaintenanceActivityLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param maintenanceActivity the maintenance activity
	 * @return the maintenance activity that was removed
	 */
	@Override
	public com.everis.rec.maintenanceactivity.model.MaintenanceActivity
		deleteMaintenanceActivity(
			com.everis.rec.maintenanceactivity.model.MaintenanceActivity
				maintenanceActivity) {

		return _maintenanceActivityLocalService.deleteMaintenanceActivity(
			maintenanceActivity);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _maintenanceActivityLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _maintenanceActivityLocalService.dynamicQuery();
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

		return _maintenanceActivityLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.everis.rec.maintenanceactivity.model.impl.MaintenanceActivityModelImpl</code>.
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

		return _maintenanceActivityLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.everis.rec.maintenanceactivity.model.impl.MaintenanceActivityModelImpl</code>.
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

		return _maintenanceActivityLocalService.dynamicQuery(
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

		return _maintenanceActivityLocalService.dynamicQueryCount(dynamicQuery);
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

		return _maintenanceActivityLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.everis.rec.maintenanceactivity.model.MaintenanceActivity
		fetchMaintenanceActivity(long maintenanceactivityId) {

		return _maintenanceActivityLocalService.fetchMaintenanceActivity(
			maintenanceactivityId);
	}

	/**
	 * Returns the maintenance activity with the matching UUID and company.
	 *
	 * @param uuid the maintenance activity's UUID
	 * @param companyId the primary key of the company
	 * @return the matching maintenance activity, or <code>null</code> if a matching maintenance activity could not be found
	 */
	@Override
	public com.everis.rec.maintenanceactivity.model.MaintenanceActivity
		fetchMaintenanceActivityByUuidAndCompanyId(
			String uuid, long companyId) {

		return _maintenanceActivityLocalService.
			fetchMaintenanceActivityByUuidAndCompanyId(uuid, companyId);
	}

	@Override
	public java.util.List
		<com.everis.rec.maintenanceactivity.model.MaintenanceActivity>
			findByStatus(String status) {

		return _maintenanceActivityLocalService.findByStatus(status);
	}

	@Override
	public java.util.List
		<com.everis.rec.maintenanceactivity.model.MaintenanceActivity>
			findMaintenanceActivityLogs(
				int searchContainerStart, int searchContainerEnd) {

		return _maintenanceActivityLocalService.findMaintenanceActivityLogs(
			searchContainerStart, searchContainerEnd);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _maintenanceActivityLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _maintenanceActivityLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _maintenanceActivityLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns a range of all the maintenance activities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.everis.rec.maintenanceactivity.model.impl.MaintenanceActivityModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of maintenance activities
	 * @param end the upper bound of the range of maintenance activities (not inclusive)
	 * @return the range of maintenance activities
	 */
	@Override
	public java.util.List
		<com.everis.rec.maintenanceactivity.model.MaintenanceActivity>
			getMaintenanceActivities(int start, int end) {

		return _maintenanceActivityLocalService.getMaintenanceActivities(
			start, end);
	}

	/**
	 * Returns the number of maintenance activities.
	 *
	 * @return the number of maintenance activities
	 */
	@Override
	public int getMaintenanceActivitiesCount() {
		return _maintenanceActivityLocalService.getMaintenanceActivitiesCount();
	}

	/**
	 * Returns the maintenance activity with the primary key.
	 *
	 * @param maintenanceactivityId the primary key of the maintenance activity
	 * @return the maintenance activity
	 * @throws PortalException if a maintenance activity with the primary key could not be found
	 */
	@Override
	public com.everis.rec.maintenanceactivity.model.MaintenanceActivity
			getMaintenanceActivity(long maintenanceactivityId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _maintenanceActivityLocalService.getMaintenanceActivity(
			maintenanceactivityId);
	}

	/**
	 * Returns the maintenance activity with the matching UUID and company.
	 *
	 * @param uuid the maintenance activity's UUID
	 * @param companyId the primary key of the company
	 * @return the matching maintenance activity
	 * @throws PortalException if a matching maintenance activity could not be found
	 */
	@Override
	public com.everis.rec.maintenanceactivity.model.MaintenanceActivity
			getMaintenanceActivityByUuidAndCompanyId(
				String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _maintenanceActivityLocalService.
			getMaintenanceActivityByUuidAndCompanyId(uuid, companyId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _maintenanceActivityLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _maintenanceActivityLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Updates the maintenance activity in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MaintenanceActivityLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param maintenanceActivity the maintenance activity
	 * @return the maintenance activity that was updated
	 */
	@Override
	public com.everis.rec.maintenanceactivity.model.MaintenanceActivity
		updateMaintenanceActivity(
			com.everis.rec.maintenanceactivity.model.MaintenanceActivity
				maintenanceActivity) {

		return _maintenanceActivityLocalService.updateMaintenanceActivity(
			maintenanceActivity);
	}

	@Override
	public MaintenanceActivityLocalService getWrappedService() {
		return _maintenanceActivityLocalService;
	}

	@Override
	public void setWrappedService(
		MaintenanceActivityLocalService maintenanceActivityLocalService) {

		_maintenanceActivityLocalService = maintenanceActivityLocalService;
	}

	private MaintenanceActivityLocalService _maintenanceActivityLocalService;

}