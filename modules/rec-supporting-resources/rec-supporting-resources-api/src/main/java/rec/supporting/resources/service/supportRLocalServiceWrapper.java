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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link supportRLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see supportRLocalService
 * @generated
 */
public class supportRLocalServiceWrapper
	implements ServiceWrapper<supportRLocalService>, supportRLocalService {

	public supportRLocalServiceWrapper(
		supportRLocalService supportRLocalService) {

		_supportRLocalService = supportRLocalService;
	}

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
	@Override
	public rec.supporting.resources.model.supportR addsupportR(
		rec.supporting.resources.model.supportR supportR) {

		return _supportRLocalService.addsupportR(supportR);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _supportRLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new support r with the primary key. Does not add the support r to the database.
	 *
	 * @param supportRId the primary key for the new support r
	 * @return the new support r
	 */
	@Override
	public rec.supporting.resources.model.supportR createsupportR(
		long supportRId) {

		return _supportRLocalService.createsupportR(supportRId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _supportRLocalService.deletePersistedModel(persistedModel);
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
	@Override
	public rec.supporting.resources.model.supportR deletesupportR(
			long supportRId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _supportRLocalService.deletesupportR(supportRId);
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
	@Override
	public rec.supporting.resources.model.supportR deletesupportR(
		rec.supporting.resources.model.supportR supportR) {

		return _supportRLocalService.deletesupportR(supportR);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _supportRLocalService.dynamicQuery();
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

		return _supportRLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _supportRLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _supportRLocalService.dynamicQuery(
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

		return _supportRLocalService.dynamicQueryCount(dynamicQuery);
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

		return _supportRLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public rec.supporting.resources.model.supportR fetchsupportR(
		long supportRId) {

		return _supportRLocalService.fetchsupportR(supportRId);
	}

	/**
	 * Returns the support r matching the UUID and group.
	 *
	 * @param uuid the support r's UUID
	 * @param groupId the primary key of the group
	 * @return the matching support r, or <code>null</code> if a matching support r could not be found
	 */
	@Override
	public rec.supporting.resources.model.supportR
		fetchsupportRByUuidAndGroupId(String uuid, long groupId) {

		return _supportRLocalService.fetchsupportRByUuidAndGroupId(
			uuid, groupId);
	}

	@Override
	public java.util.List<rec.supporting.resources.model.supportR>
		findByStatusOnly(String status) {

		return _supportRLocalService.findByStatusOnly(status);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _supportRLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _supportRLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _supportRLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _supportRLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _supportRLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the support r with the primary key.
	 *
	 * @param supportRId the primary key of the support r
	 * @return the support r
	 * @throws PortalException if a support r with the primary key could not be found
	 */
	@Override
	public rec.supporting.resources.model.supportR getsupportR(long supportRId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _supportRLocalService.getsupportR(supportRId);
	}

	/**
	 * Returns the support r matching the UUID and group.
	 *
	 * @param uuid the support r's UUID
	 * @param groupId the primary key of the group
	 * @return the matching support r
	 * @throws PortalException if a matching support r could not be found
	 */
	@Override
	public rec.supporting.resources.model.supportR getsupportRByUuidAndGroupId(
			String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _supportRLocalService.getsupportRByUuidAndGroupId(uuid, groupId);
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
	@Override
	public java.util.List<rec.supporting.resources.model.supportR> getsupportRs(
		int start, int end) {

		return _supportRLocalService.getsupportRs(start, end);
	}

	/**
	 * Returns all the support rs matching the UUID and company.
	 *
	 * @param uuid the UUID of the support rs
	 * @param companyId the primary key of the company
	 * @return the matching support rs, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<rec.supporting.resources.model.supportR>
		getsupportRsByUuidAndCompanyId(String uuid, long companyId) {

		return _supportRLocalService.getsupportRsByUuidAndCompanyId(
			uuid, companyId);
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
	@Override
	public java.util.List<rec.supporting.resources.model.supportR>
		getsupportRsByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<rec.supporting.resources.model.supportR> orderByComparator) {

		return _supportRLocalService.getsupportRsByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of support rs.
	 *
	 * @return the number of support rs
	 */
	@Override
	public int getsupportRsCount() {
		return _supportRLocalService.getsupportRsCount();
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
	@Override
	public rec.supporting.resources.model.supportR updatesupportR(
		rec.supporting.resources.model.supportR supportR) {

		return _supportRLocalService.updatesupportR(supportR);
	}

	@Override
	public supportRLocalService getWrappedService() {
		return _supportRLocalService;
	}

	@Override
	public void setWrappedService(supportRLocalService supportRLocalService) {
		_supportRLocalService = supportRLocalService;
	}

	private supportRLocalService _supportRLocalService;

}