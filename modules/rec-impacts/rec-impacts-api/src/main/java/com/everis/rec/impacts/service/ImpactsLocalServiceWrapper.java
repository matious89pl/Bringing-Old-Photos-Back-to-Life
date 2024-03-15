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

package com.everis.rec.impacts.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ImpactsLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ImpactsLocalService
 * @generated
 */
public class ImpactsLocalServiceWrapper
	implements ImpactsLocalService, ServiceWrapper<ImpactsLocalService> {

	public ImpactsLocalServiceWrapper(ImpactsLocalService impactsLocalService) {
		_impactsLocalService = impactsLocalService;
	}

	/**
	 * Adds the impacts to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ImpactsLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param impacts the impacts
	 * @return the impacts that was added
	 */
	@Override
	public com.everis.rec.impacts.model.Impacts addImpacts(
		com.everis.rec.impacts.model.Impacts impacts) {

		return _impactsLocalService.addImpacts(impacts);
	}

	/**
	 * Creates a new impacts with the primary key. Does not add the impacts to the database.
	 *
	 * @param impactId the primary key for the new impacts
	 * @return the new impacts
	 */
	@Override
	public com.everis.rec.impacts.model.Impacts createImpacts(long impactId) {
		return _impactsLocalService.createImpacts(impactId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _impactsLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the impacts from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ImpactsLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param impacts the impacts
	 * @return the impacts that was removed
	 */
	@Override
	public com.everis.rec.impacts.model.Impacts deleteImpacts(
		com.everis.rec.impacts.model.Impacts impacts) {

		return _impactsLocalService.deleteImpacts(impacts);
	}

	/**
	 * Deletes the impacts with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ImpactsLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param impactId the primary key of the impacts
	 * @return the impacts that was removed
	 * @throws PortalException if a impacts with the primary key could not be found
	 */
	@Override
	public com.everis.rec.impacts.model.Impacts deleteImpacts(long impactId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _impactsLocalService.deleteImpacts(impactId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _impactsLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _impactsLocalService.dynamicQuery();
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

		return _impactsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.everis.rec.impacts.model.impl.ImpactsModelImpl</code>.
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

		return _impactsLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.everis.rec.impacts.model.impl.ImpactsModelImpl</code>.
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

		return _impactsLocalService.dynamicQuery(
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

		return _impactsLocalService.dynamicQueryCount(dynamicQuery);
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

		return _impactsLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.everis.rec.impacts.model.Impacts fetchImpacts(long impactId) {
		return _impactsLocalService.fetchImpacts(impactId);
	}

	@Override
	public java.util.List<com.everis.rec.impacts.model.Impacts>
		findImpactsByCategory(String category) {

		return _impactsLocalService.findImpactsByCategory(category);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _impactsLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the impacts with the primary key.
	 *
	 * @param impactId the primary key of the impacts
	 * @return the impacts
	 * @throws PortalException if a impacts with the primary key could not be found
	 */
	@Override
	public com.everis.rec.impacts.model.Impacts getImpacts(long impactId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _impactsLocalService.getImpacts(impactId);
	}

	/**
	 * Returns a range of all the impactses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.everis.rec.impacts.model.impl.ImpactsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of impactses
	 * @param end the upper bound of the range of impactses (not inclusive)
	 * @return the range of impactses
	 */
	@Override
	public java.util.List<com.everis.rec.impacts.model.Impacts> getImpactses(
		int start, int end) {

		return _impactsLocalService.getImpactses(start, end);
	}

	/**
	 * Returns the number of impactses.
	 *
	 * @return the number of impactses
	 */
	@Override
	public int getImpactsesCount() {
		return _impactsLocalService.getImpactsesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _impactsLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _impactsLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _impactsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the impacts in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ImpactsLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param impacts the impacts
	 * @return the impacts that was updated
	 */
	@Override
	public com.everis.rec.impacts.model.Impacts updateImpacts(
		com.everis.rec.impacts.model.Impacts impacts) {

		return _impactsLocalService.updateImpacts(impacts);
	}

	@Override
	public ImpactsLocalService getWrappedService() {
		return _impactsLocalService;
	}

	@Override
	public void setWrappedService(ImpactsLocalService impactsLocalService) {
		_impactsLocalService = impactsLocalService;
	}

	private ImpactsLocalService _impactsLocalService;

}