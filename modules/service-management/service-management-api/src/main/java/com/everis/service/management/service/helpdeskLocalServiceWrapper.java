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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link helpdeskLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see helpdeskLocalService
 * @generated
 */
public class helpdeskLocalServiceWrapper
	implements helpdeskLocalService, ServiceWrapper<helpdeskLocalService> {

	public helpdeskLocalServiceWrapper(
		helpdeskLocalService helpdeskLocalService) {

		_helpdeskLocalService = helpdeskLocalService;
	}

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
	@Override
	public com.everis.service.management.model.helpdesk addhelpdesk(
		com.everis.service.management.model.helpdesk helpdesk) {

		return _helpdeskLocalService.addhelpdesk(helpdesk);
	}

	/**
	 * Creates a new helpdesk with the primary key. Does not add the helpdesk to the database.
	 *
	 * @param helpdeskId the primary key for the new helpdesk
	 * @return the new helpdesk
	 */
	@Override
	public com.everis.service.management.model.helpdesk createhelpdesk(
		long helpdeskId) {

		return _helpdeskLocalService.createhelpdesk(helpdeskId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _helpdeskLocalService.createPersistedModel(primaryKeyObj);
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
	@Override
	public com.everis.service.management.model.helpdesk deletehelpdesk(
		com.everis.service.management.model.helpdesk helpdesk) {

		return _helpdeskLocalService.deletehelpdesk(helpdesk);
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
	@Override
	public com.everis.service.management.model.helpdesk deletehelpdesk(
			long helpdeskId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _helpdeskLocalService.deletehelpdesk(helpdeskId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _helpdeskLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _helpdeskLocalService.dynamicQuery();
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

		return _helpdeskLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _helpdeskLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _helpdeskLocalService.dynamicQuery(
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

		return _helpdeskLocalService.dynamicQueryCount(dynamicQuery);
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

		return _helpdeskLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.everis.service.management.model.helpdesk fetchhelpdesk(
		long helpdeskId) {

		return _helpdeskLocalService.fetchhelpdesk(helpdeskId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _helpdeskLocalService.getActionableDynamicQuery();
	}

	@Override
	public void getAPIToken() {
		_helpdeskLocalService.getAPIToken();
	}

	/**
	 * Returns the helpdesk with the primary key.
	 *
	 * @param helpdeskId the primary key of the helpdesk
	 * @return the helpdesk
	 * @throws PortalException if a helpdesk with the primary key could not be found
	 */
	@Override
	public com.everis.service.management.model.helpdesk gethelpdesk(
			long helpdeskId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _helpdeskLocalService.gethelpdesk(helpdeskId);
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
	@Override
	public java.util.List<com.everis.service.management.model.helpdesk>
		gethelpdesks(int start, int end) {

		return _helpdeskLocalService.gethelpdesks(start, end);
	}

	/**
	 * Returns the number of helpdesks.
	 *
	 * @return the number of helpdesks
	 */
	@Override
	public int gethelpdesksCount() {
		return _helpdeskLocalService.gethelpdesksCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _helpdeskLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _helpdeskLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _helpdeskLocalService.getPersistedModel(primaryKeyObj);
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
	@Override
	public com.everis.service.management.model.helpdesk updatehelpdesk(
		com.everis.service.management.model.helpdesk helpdesk) {

		return _helpdeskLocalService.updatehelpdesk(helpdesk);
	}

	@Override
	public helpdeskLocalService getWrappedService() {
		return _helpdeskLocalService;
	}

	@Override
	public void setWrappedService(helpdeskLocalService helpdeskLocalService) {
		_helpdeskLocalService = helpdeskLocalService;
	}

	private helpdeskLocalService _helpdeskLocalService;

}