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

import com.everis.rec.remediation.tracker.model.RemediationTracker;

import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the local service interface for RemediationTracker. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see RemediationTrackerLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface RemediationTrackerLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.everis.rec.remediation.tracker.service.impl.RemediationTrackerLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the remediation tracker local service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link RemediationTrackerLocalServiceUtil} if injection and service tracking are not available.
	 */

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
	@Indexable(type = IndexableType.REINDEX)
	public RemediationTracker addRemediationTracker(
		RemediationTracker remediationTracker);

	/**
	 * @throws PortalException
	 */
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	 * Creates a new remediation tracker with the primary key. Does not add the remediation tracker to the database.
	 *
	 * @param remediationTrackerId the primary key for the new remediation tracker
	 * @return the new remediation tracker
	 */
	@Transactional(enabled = false)
	public RemediationTracker createRemediationTracker(
		long remediationTrackerId);

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

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
	@Indexable(type = IndexableType.DELETE)
	public RemediationTracker deleteRemediationTracker(
			long remediationTrackerId)
		throws PortalException;

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
	@Indexable(type = IndexableType.DELETE)
	public RemediationTracker deleteRemediationTracker(
		RemediationTracker remediationTracker);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DynamicQuery dynamicQuery();

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public RemediationTracker fetchRemediationTracker(
		long remediationTrackerId);

	/**
	 * Returns the remediation tracker matching the UUID and group.
	 *
	 * @param uuid the remediation tracker's UUID
	 * @param groupId the primary key of the group
	 * @return the matching remediation tracker, or <code>null</code> if a matching remediation tracker could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public RemediationTracker fetchRemediationTrackerByUuidAndGroupId(
		String uuid, long groupId);

	public List<RemediationTracker> findByStatus(String status);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	/**
	 * @throws PortalException
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	 * Returns the remediation tracker with the primary key.
	 *
	 * @param remediationTrackerId the primary key of the remediation tracker
	 * @return the remediation tracker
	 * @throws PortalException if a remediation tracker with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public RemediationTracker getRemediationTracker(long remediationTrackerId)
		throws PortalException;

	/**
	 * Returns the remediation tracker matching the UUID and group.
	 *
	 * @param uuid the remediation tracker's UUID
	 * @param groupId the primary key of the group
	 * @return the matching remediation tracker
	 * @throws PortalException if a matching remediation tracker could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public RemediationTracker getRemediationTrackerByUuidAndGroupId(
			String uuid, long groupId)
		throws PortalException;

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<RemediationTracker> getRemediationTrackers(int start, int end);

	/**
	 * Returns all the remediation trackers matching the UUID and company.
	 *
	 * @param uuid the UUID of the remediation trackers
	 * @param companyId the primary key of the company
	 * @return the matching remediation trackers, or an empty list if no matches were found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<RemediationTracker> getRemediationTrackersByUuidAndCompanyId(
		String uuid, long companyId);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<RemediationTracker> getRemediationTrackersByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<RemediationTracker> orderByComparator);

	/**
	 * Returns the number of remediation trackers.
	 *
	 * @return the number of remediation trackers
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getRemediationTrackersCount();

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
	@Indexable(type = IndexableType.REINDEX)
	public RemediationTracker updateRemediationTracker(
		RemediationTracker remediationTracker);

}