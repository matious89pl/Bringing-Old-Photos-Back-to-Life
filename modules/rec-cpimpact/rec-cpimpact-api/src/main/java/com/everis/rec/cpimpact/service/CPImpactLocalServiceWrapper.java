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

package com.everis.rec.cpimpact.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CPImpactLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see CPImpactLocalService
 * @generated
 */
public class CPImpactLocalServiceWrapper
	implements CPImpactLocalService, ServiceWrapper<CPImpactLocalService> {

	public CPImpactLocalServiceWrapper(
		CPImpactLocalService cpImpactLocalService) {

		_cpImpactLocalService = cpImpactLocalService;
	}

	/**
	 * Adds the cp impact to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CPImpactLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param cpImpact the cp impact
	 * @return the cp impact that was added
	 */
	@Override
	public com.everis.rec.cpimpact.model.CPImpact addCPImpact(
		com.everis.rec.cpimpact.model.CPImpact cpImpact) {

		return _cpImpactLocalService.addCPImpact(cpImpact);
	}

	/**
	 * Creates a new cp impact with the primary key. Does not add the cp impact to the database.
	 *
	 * @param impactId the primary key for the new cp impact
	 * @return the new cp impact
	 */
	@Override
	public com.everis.rec.cpimpact.model.CPImpact createCPImpact(
		long impactId) {

		return _cpImpactLocalService.createCPImpact(impactId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpImpactLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the cp impact from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CPImpactLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param cpImpact the cp impact
	 * @return the cp impact that was removed
	 */
	@Override
	public com.everis.rec.cpimpact.model.CPImpact deleteCPImpact(
		com.everis.rec.cpimpact.model.CPImpact cpImpact) {

		return _cpImpactLocalService.deleteCPImpact(cpImpact);
	}

	/**
	 * Deletes the cp impact with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CPImpactLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param impactId the primary key of the cp impact
	 * @return the cp impact that was removed
	 * @throws PortalException if a cp impact with the primary key could not be found
	 */
	@Override
	public com.everis.rec.cpimpact.model.CPImpact deleteCPImpact(long impactId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpImpactLocalService.deleteCPImpact(impactId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpImpactLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _cpImpactLocalService.dynamicQuery();
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

		return _cpImpactLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.everis.rec.cpimpact.model.impl.CPImpactModelImpl</code>.
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

		return _cpImpactLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.everis.rec.cpimpact.model.impl.CPImpactModelImpl</code>.
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

		return _cpImpactLocalService.dynamicQuery(
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

		return _cpImpactLocalService.dynamicQueryCount(dynamicQuery);
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

		return _cpImpactLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.everis.rec.cpimpact.model.CPImpact fetchCPImpact(long impactId) {
		return _cpImpactLocalService.fetchCPImpact(impactId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _cpImpactLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the cp impact with the primary key.
	 *
	 * @param impactId the primary key of the cp impact
	 * @return the cp impact
	 * @throws PortalException if a cp impact with the primary key could not be found
	 */
	@Override
	public com.everis.rec.cpimpact.model.CPImpact getCPImpact(long impactId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpImpactLocalService.getCPImpact(impactId);
	}

	/**
	 * Returns a range of all the cp impacts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.everis.rec.cpimpact.model.impl.CPImpactModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp impacts
	 * @param end the upper bound of the range of cp impacts (not inclusive)
	 * @return the range of cp impacts
	 */
	@Override
	public java.util.List<com.everis.rec.cpimpact.model.CPImpact> getCPImpacts(
		int start, int end) {

		return _cpImpactLocalService.getCPImpacts(start, end);
	}

	/**
	 * Returns the number of cp impacts.
	 *
	 * @return the number of cp impacts
	 */
	@Override
	public int getCPImpactsCount() {
		return _cpImpactLocalService.getCPImpactsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _cpImpactLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _cpImpactLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpImpactLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the cp impact in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CPImpactLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param cpImpact the cp impact
	 * @return the cp impact that was updated
	 */
	@Override
	public com.everis.rec.cpimpact.model.CPImpact updateCPImpact(
		com.everis.rec.cpimpact.model.CPImpact cpImpact) {

		return _cpImpactLocalService.updateCPImpact(cpImpact);
	}

	@Override
	public CPImpactLocalService getWrappedService() {
		return _cpImpactLocalService;
	}

	@Override
	public void setWrappedService(CPImpactLocalService cpImpactLocalService) {
		_cpImpactLocalService = cpImpactLocalService;
	}

	private CPImpactLocalService _cpImpactLocalService;

}