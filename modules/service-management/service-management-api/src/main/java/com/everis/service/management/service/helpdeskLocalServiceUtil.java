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

package com.everis.service.management.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for helpdesk. This utility wraps
 * <code>com.everis.service.management.service.impl.helpdeskLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see helpdeskLocalService
 * @generated
 */
public class helpdeskLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.everis.service.management.service.impl.helpdeskLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the helpdesk to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect helpdeskLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param helpdesk the helpdesk
	 * @return the helpdesk that was added
	 */
	public static com.everis.service.management.model.helpdesk addhelpdesk(
		com.everis.service.management.model.helpdesk helpdesk) {

		return getService().addhelpdesk(helpdesk);
	}

	/**
	 * Creates a new helpdesk with the primary key. Does not add the helpdesk to the database.
	 *
	 * @param helpdeskId the primary key for the new helpdesk
	 * @return the new helpdesk
	 */
	public static com.everis.service.management.model.helpdesk createhelpdesk(
		long helpdeskId) {

		return getService().createhelpdesk(helpdeskId);
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
	 * Deletes the helpdesk from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect helpdeskLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param helpdesk the helpdesk
	 * @return the helpdesk that was removed
	 */
	public static com.everis.service.management.model.helpdesk deletehelpdesk(
		com.everis.service.management.model.helpdesk helpdesk) {

		return getService().deletehelpdesk(helpdesk);
	}

	/**
	 * Deletes the helpdesk with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect helpdeskLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param helpdeskId the primary key of the helpdesk
	 * @return the helpdesk that was removed
	 * @throws PortalException if a helpdesk with the primary key could not be found
	 */
	public static com.everis.service.management.model.helpdesk deletehelpdesk(
			long helpdeskId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletehelpdesk(helpdeskId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.everis.service.management.model.impl.helpdeskModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.everis.service.management.model.impl.helpdeskModelImpl</code>.
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

	public static com.everis.service.management.model.helpdesk fetchhelpdesk(
		long helpdeskId) {

		return getService().fetchhelpdesk(helpdeskId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static void getAPIToken() {
		getService().getAPIToken();
	}

	/**
	 * Returns the helpdesk with the primary key.
	 *
	 * @param helpdeskId the primary key of the helpdesk
	 * @return the helpdesk
	 * @throws PortalException if a helpdesk with the primary key could not be found
	 */
	public static com.everis.service.management.model.helpdesk gethelpdesk(
			long helpdeskId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().gethelpdesk(helpdeskId);
	}

	/**
	 * Returns a range of all the helpdesks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.everis.service.management.model.impl.helpdeskModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of helpdesks
	 * @param end the upper bound of the range of helpdesks (not inclusive)
	 * @return the range of helpdesks
	 */
	public static java.util.List<com.everis.service.management.model.helpdesk>
		gethelpdesks(int start, int end) {

		return getService().gethelpdesks(start, end);
	}

	/**
	 * Returns the number of helpdesks.
	 *
	 * @return the number of helpdesks
	 */
	public static int gethelpdesksCount() {
		return getService().gethelpdesksCount();
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
	 * Updates the helpdesk in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect helpdeskLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param helpdesk the helpdesk
	 * @return the helpdesk that was updated
	 */
	public static com.everis.service.management.model.helpdesk updatehelpdesk(
		com.everis.service.management.model.helpdesk helpdesk) {

		return getService().updatehelpdesk(helpdesk);
	}

	public static helpdeskLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<helpdeskLocalService, helpdeskLocalService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(helpdeskLocalService.class);

		ServiceTracker<helpdeskLocalService, helpdeskLocalService>
			serviceTracker =
				new ServiceTracker<helpdeskLocalService, helpdeskLocalService>(
					bundle.getBundleContext(), helpdeskLocalService.class,
					null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}