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

package com.everis.rec.rfilogs.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for RfiLogs. This utility wraps
 * <code>com.everis.rec.rfilogs.service.impl.RfiLogsLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see RfiLogsLocalService
 * @generated
 */
public class RfiLogsLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.everis.rec.rfilogs.service.impl.RfiLogsLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the rfi logs to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RfiLogsLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param rfiLogs the rfi logs
	 * @return the rfi logs that was added
	 */
	public static com.everis.rec.rfilogs.model.RfiLogs addRfiLogs(
		com.everis.rec.rfilogs.model.RfiLogs rfiLogs) {

		return getService().addRfiLogs(rfiLogs);
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
	 * Creates a new rfi logs with the primary key. Does not add the rfi logs to the database.
	 *
	 * @param rfilogId the primary key for the new rfi logs
	 * @return the new rfi logs
	 */
	public static com.everis.rec.rfilogs.model.RfiLogs createRfiLogs(
		long rfilogId) {

		return getService().createRfiLogs(rfilogId);
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
	 * Deletes the rfi logs with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RfiLogsLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param rfilogId the primary key of the rfi logs
	 * @return the rfi logs that was removed
	 * @throws PortalException if a rfi logs with the primary key could not be found
	 */
	public static com.everis.rec.rfilogs.model.RfiLogs deleteRfiLogs(
			long rfilogId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteRfiLogs(rfilogId);
	}

	/**
	 * Deletes the rfi logs from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RfiLogsLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param rfiLogs the rfi logs
	 * @return the rfi logs that was removed
	 */
	public static com.everis.rec.rfilogs.model.RfiLogs deleteRfiLogs(
		com.everis.rec.rfilogs.model.RfiLogs rfiLogs) {

		return getService().deleteRfiLogs(rfiLogs);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.everis.rec.rfilogs.model.impl.RfiLogsModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.everis.rec.rfilogs.model.impl.RfiLogsModelImpl</code>.
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

	public static com.everis.rec.rfilogs.model.RfiLogs fetchRfiLogs(
		long rfilogId) {

		return getService().fetchRfiLogs(rfilogId);
	}

	/**
	 * Returns the rfi logs with the matching UUID and company.
	 *
	 * @param uuid the rfi logs's UUID
	 * @param companyId the primary key of the company
	 * @return the matching rfi logs, or <code>null</code> if a matching rfi logs could not be found
	 */
	public static com.everis.rec.rfilogs.model.RfiLogs
		fetchRfiLogsByUuidAndCompanyId(String uuid, long companyId) {

		return getService().fetchRfiLogsByUuidAndCompanyId(uuid, companyId);
	}

	public static java.util.List<com.everis.rec.rfilogs.model.RfiLogs>
		findByStatus(String status) {

		return getService().findByStatus(status);
	}

	public static java.util.List<com.everis.rec.rfilogs.model.RfiLogs>
		findRfiLogs(int searchContainerStart, int searchContainerEnd) {

		return getService().findRfiLogs(
			searchContainerStart, searchContainerEnd);
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
	 * Returns the rfi logs with the primary key.
	 *
	 * @param rfilogId the primary key of the rfi logs
	 * @return the rfi logs
	 * @throws PortalException if a rfi logs with the primary key could not be found
	 */
	public static com.everis.rec.rfilogs.model.RfiLogs getRfiLogs(long rfilogId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getRfiLogs(rfilogId);
	}

	/**
	 * Returns the rfi logs with the matching UUID and company.
	 *
	 * @param uuid the rfi logs's UUID
	 * @param companyId the primary key of the company
	 * @return the matching rfi logs
	 * @throws PortalException if a matching rfi logs could not be found
	 */
	public static com.everis.rec.rfilogs.model.RfiLogs
			getRfiLogsByUuidAndCompanyId(String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getRfiLogsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	 * Returns a range of all the rfi logses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.everis.rec.rfilogs.model.impl.RfiLogsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of rfi logses
	 * @param end the upper bound of the range of rfi logses (not inclusive)
	 * @return the range of rfi logses
	 */
	public static java.util.List<com.everis.rec.rfilogs.model.RfiLogs>
		getRfiLogses(int start, int end) {

		return getService().getRfiLogses(start, end);
	}

	/**
	 * Returns the number of rfi logses.
	 *
	 * @return the number of rfi logses
	 */
	public static int getRfiLogsesCount() {
		return getService().getRfiLogsesCount();
	}

	public static void newRFI(
		long userId, com.everis.rec.rfilogs.model.RfiLogs newRFI,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		getService().newRFI(userId, newRFI, serviceContext);
	}

	/**
	 * Updates the rfi logs in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RfiLogsLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param rfiLogs the rfi logs
	 * @return the rfi logs that was updated
	 */
	public static com.everis.rec.rfilogs.model.RfiLogs updateRfiLogs(
		com.everis.rec.rfilogs.model.RfiLogs rfiLogs) {

		return getService().updateRfiLogs(rfiLogs);
	}

	public static RfiLogsLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<RfiLogsLocalService, RfiLogsLocalService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(RfiLogsLocalService.class);

		ServiceTracker<RfiLogsLocalService, RfiLogsLocalService>
			serviceTracker =
				new ServiceTracker<RfiLogsLocalService, RfiLogsLocalService>(
					bundle.getBundleContext(), RfiLogsLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}