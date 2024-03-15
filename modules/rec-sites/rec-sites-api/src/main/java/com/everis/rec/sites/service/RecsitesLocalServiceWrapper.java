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

package com.everis.rec.sites.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link RecsitesLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see RecsitesLocalService
 * @generated
 */
public class RecsitesLocalServiceWrapper
	implements RecsitesLocalService, ServiceWrapper<RecsitesLocalService> {

	public RecsitesLocalServiceWrapper(
		RecsitesLocalService recsitesLocalService) {

		_recsitesLocalService = recsitesLocalService;
	}

	/**
	 * Adds the recsites to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RecsitesLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param recsites the recsites
	 * @return the recsites that was added
	 */
	@Override
	public com.everis.rec.sites.model.Recsites addRecsites(
		com.everis.rec.sites.model.Recsites recsites) {

		return _recsitesLocalService.addRecsites(recsites);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _recsitesLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new recsites with the primary key. Does not add the recsites to the database.
	 *
	 * @param fooId the primary key for the new recsites
	 * @return the new recsites
	 */
	@Override
	public com.everis.rec.sites.model.Recsites createRecsites(long fooId) {
		return _recsitesLocalService.createRecsites(fooId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _recsitesLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the recsites with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RecsitesLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param fooId the primary key of the recsites
	 * @return the recsites that was removed
	 * @throws PortalException if a recsites with the primary key could not be found
	 */
	@Override
	public com.everis.rec.sites.model.Recsites deleteRecsites(long fooId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _recsitesLocalService.deleteRecsites(fooId);
	}

	/**
	 * Deletes the recsites from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RecsitesLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param recsites the recsites
	 * @return the recsites that was removed
	 */
	@Override
	public com.everis.rec.sites.model.Recsites deleteRecsites(
		com.everis.rec.sites.model.Recsites recsites) {

		return _recsitesLocalService.deleteRecsites(recsites);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _recsitesLocalService.dynamicQuery();
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

		return _recsitesLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.everis.rec.sites.model.impl.RecsitesModelImpl</code>.
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

		return _recsitesLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.everis.rec.sites.model.impl.RecsitesModelImpl</code>.
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

		return _recsitesLocalService.dynamicQuery(
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

		return _recsitesLocalService.dynamicQueryCount(dynamicQuery);
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

		return _recsitesLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.everis.rec.sites.model.Recsites fetchRecsites(long fooId) {
		return _recsitesLocalService.fetchRecsites(fooId);
	}

	/**
	 * Returns the recsites matching the UUID and group.
	 *
	 * @param uuid the recsites's UUID
	 * @param groupId the primary key of the group
	 * @return the matching recsites, or <code>null</code> if a matching recsites could not be found
	 */
	@Override
	public com.everis.rec.sites.model.Recsites fetchRecsitesByUuidAndGroupId(
		String uuid, long groupId) {

		return _recsitesLocalService.fetchRecsitesByUuidAndGroupId(
			uuid, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _recsitesLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _recsitesLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _recsitesLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _recsitesLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _recsitesLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the recsites with the primary key.
	 *
	 * @param fooId the primary key of the recsites
	 * @return the recsites
	 * @throws PortalException if a recsites with the primary key could not be found
	 */
	@Override
	public com.everis.rec.sites.model.Recsites getRecsites(long fooId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _recsitesLocalService.getRecsites(fooId);
	}

	/**
	 * Returns the recsites matching the UUID and group.
	 *
	 * @param uuid the recsites's UUID
	 * @param groupId the primary key of the group
	 * @return the matching recsites
	 * @throws PortalException if a matching recsites could not be found
	 */
	@Override
	public com.everis.rec.sites.model.Recsites getRecsitesByUuidAndGroupId(
			String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _recsitesLocalService.getRecsitesByUuidAndGroupId(uuid, groupId);
	}

	/**
	 * Returns a range of all the recsiteses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.everis.rec.sites.model.impl.RecsitesModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of recsiteses
	 * @param end the upper bound of the range of recsiteses (not inclusive)
	 * @return the range of recsiteses
	 */
	@Override
	public java.util.List<com.everis.rec.sites.model.Recsites> getRecsiteses(
		int start, int end) {

		return _recsitesLocalService.getRecsiteses(start, end);
	}

	/**
	 * Returns all the recsiteses matching the UUID and company.
	 *
	 * @param uuid the UUID of the recsiteses
	 * @param companyId the primary key of the company
	 * @return the matching recsiteses, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<com.everis.rec.sites.model.Recsites>
		getRecsitesesByUuidAndCompanyId(String uuid, long companyId) {

		return _recsitesLocalService.getRecsitesesByUuidAndCompanyId(
			uuid, companyId);
	}

	/**
	 * Returns a range of recsiteses matching the UUID and company.
	 *
	 * @param uuid the UUID of the recsiteses
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of recsiteses
	 * @param end the upper bound of the range of recsiteses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching recsiteses, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<com.everis.rec.sites.model.Recsites>
		getRecsitesesByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.everis.rec.sites.model.Recsites> orderByComparator) {

		return _recsitesLocalService.getRecsitesesByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of recsiteses.
	 *
	 * @return the number of recsiteses
	 */
	@Override
	public int getRecsitesesCount() {
		return _recsitesLocalService.getRecsitesesCount();
	}

	/**
	 * Updates the recsites in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RecsitesLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param recsites the recsites
	 * @return the recsites that was updated
	 */
	@Override
	public com.everis.rec.sites.model.Recsites updateRecsites(
		com.everis.rec.sites.model.Recsites recsites) {

		return _recsitesLocalService.updateRecsites(recsites);
	}

	@Override
	public RecsitesLocalService getWrappedService() {
		return _recsitesLocalService;
	}

	@Override
	public void setWrappedService(RecsitesLocalService recsitesLocalService) {
		_recsitesLocalService = recsitesLocalService;
	}

	private RecsitesLocalService _recsitesLocalService;

}