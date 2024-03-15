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

package com.everis.rec.remediation.tracker.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link RemediationTrackerLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see RemediationTrackerLocalService
 * @generated
 */
public class RemediationTrackerLocalServiceWrapper
	implements RemediationTrackerLocalService,
			   ServiceWrapper<RemediationTrackerLocalService> {

	public RemediationTrackerLocalServiceWrapper(
		RemediationTrackerLocalService remediationTrackerLocalService) {

		_remediationTrackerLocalService = remediationTrackerLocalService;
	}

	/**
	 * Adds the remediation tracker to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RemediationTrackerLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param remediationTracker the remediation tracker
	 * @return the remediation tracker that was added
	 */
	@Override
	public com.everis.rec.remediation.tracker.model.RemediationTracker
		addRemediationTracker(
			com.everis.rec.remediation.tracker.model.RemediationTracker
				remediationTracker) {

		return _remediationTrackerLocalService.addRemediationTracker(
			remediationTracker);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _remediationTrackerLocalService.createPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Creates a new remediation tracker with the primary key. Does not add the remediation tracker to the database.
	 *
	 * @param remediationTrackerId the primary key for the new remediation tracker
	 * @return the new remediation tracker
	 */
	@Override
	public com.everis.rec.remediation.tracker.model.RemediationTracker
		createRemediationTracker(long remediationTrackerId) {

		return _remediationTrackerLocalService.createRemediationTracker(
			remediationTrackerId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _remediationTrackerLocalService.deletePersistedModel(
			persistedModel);
	}

	/**
	 * Deletes the remediation tracker with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RemediationTrackerLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param remediationTrackerId the primary key of the remediation tracker
	 * @return the remediation tracker that was removed
	 * @throws PortalException if a remediation tracker with the primary key could not be found
	 */
	@Override
	public com.everis.rec.remediation.tracker.model.RemediationTracker
			deleteRemediationTracker(long remediationTrackerId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _remediationTrackerLocalService.deleteRemediationTracker(
			remediationTrackerId);
	}

	/**
	 * Deletes the remediation tracker from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RemediationTrackerLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param remediationTracker the remediation tracker
	 * @return the remediation tracker that was removed
	 */
	@Override
	public com.everis.rec.remediation.tracker.model.RemediationTracker
		deleteRemediationTracker(
			com.everis.rec.remediation.tracker.model.RemediationTracker
				remediationTracker) {

		return _remediationTrackerLocalService.deleteRemediationTracker(
			remediationTracker);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _remediationTrackerLocalService.dynamicQuery();
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

		return _remediationTrackerLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.everis.rec.remediation.tracker.model.impl.RemediationTrackerModelImpl</code>.
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

		return _remediationTrackerLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.everis.rec.remediation.tracker.model.impl.RemediationTrackerModelImpl</code>.
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

		return _remediationTrackerLocalService.dynamicQuery(
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

		return _remediationTrackerLocalService.dynamicQueryCount(dynamicQuery);
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

		return _remediationTrackerLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.everis.rec.remediation.tracker.model.RemediationTracker
		fetchRemediationTracker(long remediationTrackerId) {

		return _remediationTrackerLocalService.fetchRemediationTracker(
			remediationTrackerId);
	}

	/**
	 * Returns the remediation tracker matching the UUID and group.
	 *
	 * @param uuid the remediation tracker's UUID
	 * @param groupId the primary key of the group
	 * @return the matching remediation tracker, or <code>null</code> if a matching remediation tracker could not be found
	 */
	@Override
	public com.everis.rec.remediation.tracker.model.RemediationTracker
		fetchRemediationTrackerByUuidAndGroupId(String uuid, long groupId) {

		return _remediationTrackerLocalService.
			fetchRemediationTrackerByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public java.util.List
		<com.everis.rec.remediation.tracker.model.RemediationTracker>
			findByStatus(String status) {

		return _remediationTrackerLocalService.findByStatus(status);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _remediationTrackerLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _remediationTrackerLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _remediationTrackerLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _remediationTrackerLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _remediationTrackerLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the remediation tracker with the primary key.
	 *
	 * @param remediationTrackerId the primary key of the remediation tracker
	 * @return the remediation tracker
	 * @throws PortalException if a remediation tracker with the primary key could not be found
	 */
	@Override
	public com.everis.rec.remediation.tracker.model.RemediationTracker
			getRemediationTracker(long remediationTrackerId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _remediationTrackerLocalService.getRemediationTracker(
			remediationTrackerId);
	}

	/**
	 * Returns the remediation tracker matching the UUID and group.
	 *
	 * @param uuid the remediation tracker's UUID
	 * @param groupId the primary key of the group
	 * @return the matching remediation tracker
	 * @throws PortalException if a matching remediation tracker could not be found
	 */
	@Override
	public com.everis.rec.remediation.tracker.model.RemediationTracker
			getRemediationTrackerByUuidAndGroupId(String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _remediationTrackerLocalService.
			getRemediationTrackerByUuidAndGroupId(uuid, groupId);
	}

	/**
	 * Returns a range of all the remediation trackers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.everis.rec.remediation.tracker.model.impl.RemediationTrackerModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of remediation trackers
	 * @param end the upper bound of the range of remediation trackers (not inclusive)
	 * @return the range of remediation trackers
	 */
	@Override
	public java.util.List
		<com.everis.rec.remediation.tracker.model.RemediationTracker>
			getRemediationTrackers(int start, int end) {

		return _remediationTrackerLocalService.getRemediationTrackers(
			start, end);
	}

	/**
	 * Returns all the remediation trackers matching the UUID and company.
	 *
	 * @param uuid the UUID of the remediation trackers
	 * @param companyId the primary key of the company
	 * @return the matching remediation trackers, or an empty list if no matches were found
	 */
	@Override
	public java.util.List
		<com.everis.rec.remediation.tracker.model.RemediationTracker>
			getRemediationTrackersByUuidAndCompanyId(
				String uuid, long companyId) {

		return _remediationTrackerLocalService.
			getRemediationTrackersByUuidAndCompanyId(uuid, companyId);
	}

	/**
	 * Returns a range of remediation trackers matching the UUID and company.
	 *
	 * @param uuid the UUID of the remediation trackers
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of remediation trackers
	 * @param end the upper bound of the range of remediation trackers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching remediation trackers, or an empty list if no matches were found
	 */
	@Override
	public java.util.List
		<com.everis.rec.remediation.tracker.model.RemediationTracker>
			getRemediationTrackersByUuidAndCompanyId(
				String uuid, long companyId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.everis.rec.remediation.tracker.model.
						RemediationTracker> orderByComparator) {

		return _remediationTrackerLocalService.
			getRemediationTrackersByUuidAndCompanyId(
				uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of remediation trackers.
	 *
	 * @return the number of remediation trackers
	 */
	@Override
	public int getRemediationTrackersCount() {
		return _remediationTrackerLocalService.getRemediationTrackersCount();
	}

	/**
	 * Updates the remediation tracker in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RemediationTrackerLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param remediationTracker the remediation tracker
	 * @return the remediation tracker that was updated
	 */
	@Override
	public com.everis.rec.remediation.tracker.model.RemediationTracker
		updateRemediationTracker(
			com.everis.rec.remediation.tracker.model.RemediationTracker
				remediationTracker) {

		return _remediationTrackerLocalService.updateRemediationTracker(
			remediationTracker);
	}

	@Override
	public RemediationTrackerLocalService getWrappedService() {
		return _remediationTrackerLocalService;
	}

	@Override
	public void setWrappedService(
		RemediationTrackerLocalService remediationTrackerLocalService) {

		_remediationTrackerLocalService = remediationTrackerLocalService;
	}

	private RemediationTrackerLocalService _remediationTrackerLocalService;

}