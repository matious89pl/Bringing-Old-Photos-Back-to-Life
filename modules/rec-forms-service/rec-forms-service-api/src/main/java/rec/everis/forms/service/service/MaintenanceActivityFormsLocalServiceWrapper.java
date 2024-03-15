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

package rec.everis.forms.service.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MaintenanceActivityFormsLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see MaintenanceActivityFormsLocalService
 * @generated
 */
public class MaintenanceActivityFormsLocalServiceWrapper
	implements MaintenanceActivityFormsLocalService,
			   ServiceWrapper<MaintenanceActivityFormsLocalService> {

	public MaintenanceActivityFormsLocalServiceWrapper(
		MaintenanceActivityFormsLocalService
			maintenanceActivityFormsLocalService) {

		_maintenanceActivityFormsLocalService =
			maintenanceActivityFormsLocalService;
	}

	/**
	 * Adds the maintenance activity forms to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MaintenanceActivityFormsLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param maintenanceActivityForms the maintenance activity forms
	 * @return the maintenance activity forms that was added
	 */
	@Override
	public rec.everis.forms.service.model.MaintenanceActivityForms
		addMaintenanceActivityForms(
			rec.everis.forms.service.model.MaintenanceActivityForms
				maintenanceActivityForms) {

		return _maintenanceActivityFormsLocalService.
			addMaintenanceActivityForms(maintenanceActivityForms);
	}

	/**
	 * Creates a new maintenance activity forms with the primary key. Does not add the maintenance activity forms to the database.
	 *
	 * @param maintenanceactivityformId the primary key for the new maintenance activity forms
	 * @return the new maintenance activity forms
	 */
	@Override
	public rec.everis.forms.service.model.MaintenanceActivityForms
		createMaintenanceActivityForms(long maintenanceactivityformId) {

		return _maintenanceActivityFormsLocalService.
			createMaintenanceActivityForms(maintenanceactivityformId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _maintenanceActivityFormsLocalService.createPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Deletes the maintenance activity forms with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MaintenanceActivityFormsLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param maintenanceactivityformId the primary key of the maintenance activity forms
	 * @return the maintenance activity forms that was removed
	 * @throws PortalException if a maintenance activity forms with the primary key could not be found
	 */
	@Override
	public rec.everis.forms.service.model.MaintenanceActivityForms
			deleteMaintenanceActivityForms(long maintenanceactivityformId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _maintenanceActivityFormsLocalService.
			deleteMaintenanceActivityForms(maintenanceactivityformId);
	}

	/**
	 * Deletes the maintenance activity forms from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MaintenanceActivityFormsLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param maintenanceActivityForms the maintenance activity forms
	 * @return the maintenance activity forms that was removed
	 */
	@Override
	public rec.everis.forms.service.model.MaintenanceActivityForms
		deleteMaintenanceActivityForms(
			rec.everis.forms.service.model.MaintenanceActivityForms
				maintenanceActivityForms) {

		return _maintenanceActivityFormsLocalService.
			deleteMaintenanceActivityForms(maintenanceActivityForms);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _maintenanceActivityFormsLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _maintenanceActivityFormsLocalService.dynamicQuery();
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

		return _maintenanceActivityFormsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>rec.everis.forms.service.model.impl.MaintenanceActivityFormsModelImpl</code>.
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

		return _maintenanceActivityFormsLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>rec.everis.forms.service.model.impl.MaintenanceActivityFormsModelImpl</code>.
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

		return _maintenanceActivityFormsLocalService.dynamicQuery(
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

		return _maintenanceActivityFormsLocalService.dynamicQueryCount(
			dynamicQuery);
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

		return _maintenanceActivityFormsLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public rec.everis.forms.service.model.MaintenanceActivityForms
		fetchMaintenanceActivityForms(long maintenanceactivityformId) {

		return _maintenanceActivityFormsLocalService.
			fetchMaintenanceActivityForms(maintenanceactivityformId);
	}

	/**
	 * Returns the maintenance activity forms matching the UUID and group.
	 *
	 * @param uuid the maintenance activity forms's UUID
	 * @param groupId the primary key of the group
	 * @return the matching maintenance activity forms, or <code>null</code> if a matching maintenance activity forms could not be found
	 */
	@Override
	public rec.everis.forms.service.model.MaintenanceActivityForms
		fetchMaintenanceActivityFormsByUuidAndGroupId(
			String uuid, long groupId) {

		return _maintenanceActivityFormsLocalService.
			fetchMaintenanceActivityFormsByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public java.util.List
		<rec.everis.forms.service.model.MaintenanceActivityForms>
			findByformInstanceRecordVersionId(
				long formInstanceRecordVersionId) {

		return _maintenanceActivityFormsLocalService.
			findByformInstanceRecordVersionId(formInstanceRecordVersionId);
	}

	@Override
	public java.util.List
		<rec.everis.forms.service.model.MaintenanceActivityForms> findByGroupId(
			long groupId) {

		return _maintenanceActivityFormsLocalService.findByGroupId(groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _maintenanceActivityFormsLocalService.
			getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _maintenanceActivityFormsLocalService.
			getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _maintenanceActivityFormsLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the maintenance activity forms with the primary key.
	 *
	 * @param maintenanceactivityformId the primary key of the maintenance activity forms
	 * @return the maintenance activity forms
	 * @throws PortalException if a maintenance activity forms with the primary key could not be found
	 */
	@Override
	public rec.everis.forms.service.model.MaintenanceActivityForms
			getMaintenanceActivityForms(long maintenanceactivityformId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _maintenanceActivityFormsLocalService.
			getMaintenanceActivityForms(maintenanceactivityformId);
	}

	/**
	 * Returns the maintenance activity forms matching the UUID and group.
	 *
	 * @param uuid the maintenance activity forms's UUID
	 * @param groupId the primary key of the group
	 * @return the matching maintenance activity forms
	 * @throws PortalException if a matching maintenance activity forms could not be found
	 */
	@Override
	public rec.everis.forms.service.model.MaintenanceActivityForms
			getMaintenanceActivityFormsByUuidAndGroupId(
				String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _maintenanceActivityFormsLocalService.
			getMaintenanceActivityFormsByUuidAndGroupId(uuid, groupId);
	}

	/**
	 * Returns a range of all the maintenance activity formses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>rec.everis.forms.service.model.impl.MaintenanceActivityFormsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of maintenance activity formses
	 * @param end the upper bound of the range of maintenance activity formses (not inclusive)
	 * @return the range of maintenance activity formses
	 */
	@Override
	public java.util.List
		<rec.everis.forms.service.model.MaintenanceActivityForms>
			getMaintenanceActivityFormses(int start, int end) {

		return _maintenanceActivityFormsLocalService.
			getMaintenanceActivityFormses(start, end);
	}

	/**
	 * Returns all the maintenance activity formses matching the UUID and company.
	 *
	 * @param uuid the UUID of the maintenance activity formses
	 * @param companyId the primary key of the company
	 * @return the matching maintenance activity formses, or an empty list if no matches were found
	 */
	@Override
	public java.util.List
		<rec.everis.forms.service.model.MaintenanceActivityForms>
			getMaintenanceActivityFormsesByUuidAndCompanyId(
				String uuid, long companyId) {

		return _maintenanceActivityFormsLocalService.
			getMaintenanceActivityFormsesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	 * Returns a range of maintenance activity formses matching the UUID and company.
	 *
	 * @param uuid the UUID of the maintenance activity formses
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of maintenance activity formses
	 * @param end the upper bound of the range of maintenance activity formses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching maintenance activity formses, or an empty list if no matches were found
	 */
	@Override
	public java.util.List
		<rec.everis.forms.service.model.MaintenanceActivityForms>
			getMaintenanceActivityFormsesByUuidAndCompanyId(
				String uuid, long companyId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<rec.everis.forms.service.model.MaintenanceActivityForms>
						orderByComparator) {

		return _maintenanceActivityFormsLocalService.
			getMaintenanceActivityFormsesByUuidAndCompanyId(
				uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of maintenance activity formses.
	 *
	 * @return the number of maintenance activity formses
	 */
	@Override
	public int getMaintenanceActivityFormsesCount() {
		return _maintenanceActivityFormsLocalService.
			getMaintenanceActivityFormsesCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _maintenanceActivityFormsLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _maintenanceActivityFormsLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Updates the maintenance activity forms in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MaintenanceActivityFormsLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param maintenanceActivityForms the maintenance activity forms
	 * @return the maintenance activity forms that was updated
	 */
	@Override
	public rec.everis.forms.service.model.MaintenanceActivityForms
		updateMaintenanceActivityForms(
			rec.everis.forms.service.model.MaintenanceActivityForms
				maintenanceActivityForms) {

		return _maintenanceActivityFormsLocalService.
			updateMaintenanceActivityForms(maintenanceActivityForms);
	}

	@Override
	public MaintenanceActivityFormsLocalService getWrappedService() {
		return _maintenanceActivityFormsLocalService;
	}

	@Override
	public void setWrappedService(
		MaintenanceActivityFormsLocalService
			maintenanceActivityFormsLocalService) {

		_maintenanceActivityFormsLocalService =
			maintenanceActivityFormsLocalService;
	}

	private MaintenanceActivityFormsLocalService
		_maintenanceActivityFormsLocalService;

}