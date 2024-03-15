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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for MaintenanceActivityForms. This utility wraps
 * <code>rec.everis.forms.service.service.impl.MaintenanceActivityFormsLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see MaintenanceActivityFormsLocalService
 * @generated
 */
public class MaintenanceActivityFormsLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>rec.everis.forms.service.service.impl.MaintenanceActivityFormsLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

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
	public static rec.everis.forms.service.model.MaintenanceActivityForms
		addMaintenanceActivityForms(
			rec.everis.forms.service.model.MaintenanceActivityForms
				maintenanceActivityForms) {

		return getService().addMaintenanceActivityForms(
			maintenanceActivityForms);
	}

	/**
	 * Creates a new maintenance activity forms with the primary key. Does not add the maintenance activity forms to the database.
	 *
	 * @param maintenanceactivityformId the primary key for the new maintenance activity forms
	 * @return the new maintenance activity forms
	 */
	public static rec.everis.forms.service.model.MaintenanceActivityForms
		createMaintenanceActivityForms(long maintenanceactivityformId) {

		return getService().createMaintenanceActivityForms(
			maintenanceactivityformId);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			createPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().createPersistedModel(primaryKeyObj);
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
	public static rec.everis.forms.service.model.MaintenanceActivityForms
			deleteMaintenanceActivityForms(long maintenanceactivityformId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteMaintenanceActivityForms(
			maintenanceactivityformId);
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
	public static rec.everis.forms.service.model.MaintenanceActivityForms
		deleteMaintenanceActivityForms(
			rec.everis.forms.service.model.MaintenanceActivityForms
				maintenanceActivityForms) {

		return getService().deleteMaintenanceActivityForms(
			maintenanceActivityForms);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static rec.everis.forms.service.model.MaintenanceActivityForms
		fetchMaintenanceActivityForms(long maintenanceactivityformId) {

		return getService().fetchMaintenanceActivityForms(
			maintenanceactivityformId);
	}

	/**
	 * Returns the maintenance activity forms matching the UUID and group.
	 *
	 * @param uuid the maintenance activity forms's UUID
	 * @param groupId the primary key of the group
	 * @return the matching maintenance activity forms, or <code>null</code> if a matching maintenance activity forms could not be found
	 */
	public static rec.everis.forms.service.model.MaintenanceActivityForms
		fetchMaintenanceActivityFormsByUuidAndGroupId(
			String uuid, long groupId) {

		return getService().fetchMaintenanceActivityFormsByUuidAndGroupId(
			uuid, groupId);
	}

	public static java.util.List
		<rec.everis.forms.service.model.MaintenanceActivityForms>
			findByformInstanceRecordVersionId(
				long formInstanceRecordVersionId) {

		return getService().findByformInstanceRecordVersionId(
			formInstanceRecordVersionId);
	}

	public static java.util.List
		<rec.everis.forms.service.model.MaintenanceActivityForms> findByGroupId(
			long groupId) {

		return getService().findByGroupId(groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the maintenance activity forms with the primary key.
	 *
	 * @param maintenanceactivityformId the primary key of the maintenance activity forms
	 * @return the maintenance activity forms
	 * @throws PortalException if a maintenance activity forms with the primary key could not be found
	 */
	public static rec.everis.forms.service.model.MaintenanceActivityForms
			getMaintenanceActivityForms(long maintenanceactivityformId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getMaintenanceActivityForms(
			maintenanceactivityformId);
	}

	/**
	 * Returns the maintenance activity forms matching the UUID and group.
	 *
	 * @param uuid the maintenance activity forms's UUID
	 * @param groupId the primary key of the group
	 * @return the matching maintenance activity forms
	 * @throws PortalException if a matching maintenance activity forms could not be found
	 */
	public static rec.everis.forms.service.model.MaintenanceActivityForms
			getMaintenanceActivityFormsByUuidAndGroupId(
				String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getMaintenanceActivityFormsByUuidAndGroupId(
			uuid, groupId);
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
	public static java.util.List
		<rec.everis.forms.service.model.MaintenanceActivityForms>
			getMaintenanceActivityFormses(int start, int end) {

		return getService().getMaintenanceActivityFormses(start, end);
	}

	/**
	 * Returns all the maintenance activity formses matching the UUID and company.
	 *
	 * @param uuid the UUID of the maintenance activity formses
	 * @param companyId the primary key of the company
	 * @return the matching maintenance activity formses, or an empty list if no matches were found
	 */
	public static java.util.List
		<rec.everis.forms.service.model.MaintenanceActivityForms>
			getMaintenanceActivityFormsesByUuidAndCompanyId(
				String uuid, long companyId) {

		return getService().getMaintenanceActivityFormsesByUuidAndCompanyId(
			uuid, companyId);
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
	public static java.util.List
		<rec.everis.forms.service.model.MaintenanceActivityForms>
			getMaintenanceActivityFormsesByUuidAndCompanyId(
				String uuid, long companyId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<rec.everis.forms.service.model.MaintenanceActivityForms>
						orderByComparator) {

		return getService().getMaintenanceActivityFormsesByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of maintenance activity formses.
	 *
	 * @return the number of maintenance activity formses
	 */
	public static int getMaintenanceActivityFormsesCount() {
		return getService().getMaintenanceActivityFormsesCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
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
	public static rec.everis.forms.service.model.MaintenanceActivityForms
		updateMaintenanceActivityForms(
			rec.everis.forms.service.model.MaintenanceActivityForms
				maintenanceActivityForms) {

		return getService().updateMaintenanceActivityForms(
			maintenanceActivityForms);
	}

	public static MaintenanceActivityFormsLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<MaintenanceActivityFormsLocalService,
		 MaintenanceActivityFormsLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			MaintenanceActivityFormsLocalService.class);

		ServiceTracker
			<MaintenanceActivityFormsLocalService,
			 MaintenanceActivityFormsLocalService> serviceTracker =
				new ServiceTracker
					<MaintenanceActivityFormsLocalService,
					 MaintenanceActivityFormsLocalService>(
						 bundle.getBundleContext(),
						 MaintenanceActivityFormsLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}