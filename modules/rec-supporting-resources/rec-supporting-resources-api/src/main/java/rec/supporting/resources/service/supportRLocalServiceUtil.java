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

package rec.supporting.resources.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for supportR. This utility wraps
 * <code>rec.supporting.resources.service.impl.supportRLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see supportRLocalService
 * @generated
 */
public class supportRLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>rec.supporting.resources.service.impl.supportRLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the support r to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect supportRLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param supportR the support r
	 * @return the support r that was added
	 */
	public static rec.supporting.resources.model.supportR addsupportR(
		rec.supporting.resources.model.supportR supportR) {

		return getService().addsupportR(supportR);
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
	 * Creates a new support r with the primary key. Does not add the support r to the database.
	 *
	 * @param supportRId the primary key for the new support r
	 * @return the new support r
	 */
	public static rec.supporting.resources.model.supportR createsupportR(
		long supportRId) {

		return getService().createsupportR(supportRId);
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

	/**
	 * Deletes the support r with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect supportRLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param supportRId the primary key of the support r
	 * @return the support r that was removed
	 * @throws PortalException if a support r with the primary key could not be found
	 */
	public static rec.supporting.resources.model.supportR deletesupportR(
			long supportRId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletesupportR(supportRId);
	}

	/**
	 * Deletes the support r from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect supportRLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param supportR the support r
	 * @return the support r that was removed
	 */
	public static rec.supporting.resources.model.supportR deletesupportR(
		rec.supporting.resources.model.supportR supportR) {

		return getService().deletesupportR(supportR);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>rec.supporting.resources.model.impl.supportRModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>rec.supporting.resources.model.impl.supportRModelImpl</code>.
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

	public static rec.supporting.resources.model.supportR fetchsupportR(
		long supportRId) {

		return getService().fetchsupportR(supportRId);
	}

	/**
	 * Returns the support r matching the UUID and group.
	 *
	 * @param uuid the support r's UUID
	 * @param groupId the primary key of the group
	 * @return the matching support r, or <code>null</code> if a matching support r could not be found
	 */
	public static rec.supporting.resources.model.supportR
		fetchsupportRByUuidAndGroupId(String uuid, long groupId) {

		return getService().fetchsupportRByUuidAndGroupId(uuid, groupId);
	}

	public static java.util.List<rec.supporting.resources.model.supportR>
		findByStatusOnly(String status) {

		return getService().findByStatusOnly(status);
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
	 * Returns the support r with the primary key.
	 *
	 * @param supportRId the primary key of the support r
	 * @return the support r
	 * @throws PortalException if a support r with the primary key could not be found
	 */
	public static rec.supporting.resources.model.supportR getsupportR(
			long supportRId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getsupportR(supportRId);
	}

	/**
	 * Returns the support r matching the UUID and group.
	 *
	 * @param uuid the support r's UUID
	 * @param groupId the primary key of the group
	 * @return the matching support r
	 * @throws PortalException if a matching support r could not be found
	 */
	public static rec.supporting.resources.model.supportR
			getsupportRByUuidAndGroupId(String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getsupportRByUuidAndGroupId(uuid, groupId);
	}

	/**
	 * Returns a range of all the support rs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>rec.supporting.resources.model.impl.supportRModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @return the range of support rs
	 */
	public static java.util.List<rec.supporting.resources.model.supportR>
		getsupportRs(int start, int end) {

		return getService().getsupportRs(start, end);
	}

	/**
	 * Returns all the support rs matching the UUID and company.
	 *
	 * @param uuid the UUID of the support rs
	 * @param companyId the primary key of the company
	 * @return the matching support rs, or an empty list if no matches were found
	 */
	public static java.util.List<rec.supporting.resources.model.supportR>
		getsupportRsByUuidAndCompanyId(String uuid, long companyId) {

		return getService().getsupportRsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	 * Returns a range of support rs matching the UUID and company.
	 *
	 * @param uuid the UUID of the support rs
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching support rs, or an empty list if no matches were found
	 */
	public static java.util.List<rec.supporting.resources.model.supportR>
		getsupportRsByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<rec.supporting.resources.model.supportR> orderByComparator) {

		return getService().getsupportRsByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of support rs.
	 *
	 * @return the number of support rs
	 */
	public static int getsupportRsCount() {
		return getService().getsupportRsCount();
	}

	/**
	 * Updates the support r in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect supportRLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param supportR the support r
	 * @return the support r that was updated
	 */
	public static rec.supporting.resources.model.supportR updatesupportR(
		rec.supporting.resources.model.supportR supportR) {

		return getService().updatesupportR(supportR);
	}

	public static supportRLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<supportRLocalService, supportRLocalService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(supportRLocalService.class);

		ServiceTracker<supportRLocalService, supportRLocalService>
			serviceTracker =
				new ServiceTracker<supportRLocalService, supportRLocalService>(
					bundle.getBundleContext(), supportRLocalService.class,
					null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}