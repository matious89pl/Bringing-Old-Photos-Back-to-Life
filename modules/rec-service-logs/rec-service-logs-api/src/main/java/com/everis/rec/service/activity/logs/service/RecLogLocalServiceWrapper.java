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

package com.everis.rec.service.activity.logs.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link RecLogLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see RecLogLocalService
 * @generated
 */
public class RecLogLocalServiceWrapper
	implements RecLogLocalService, ServiceWrapper<RecLogLocalService> {

	public RecLogLocalServiceWrapper(RecLogLocalService recLogLocalService) {
		_recLogLocalService = recLogLocalService;
	}

	/**
	 * Adds the rec log to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RecLogLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param recLog the rec log
	 * @return the rec log that was added
	 */
	@Override
	public com.everis.rec.service.activity.logs.model.RecLog addRecLog(
		com.everis.rec.service.activity.logs.model.RecLog recLog) {

		return _recLogLocalService.addRecLog(recLog);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _recLogLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new rec log with the primary key. Does not add the rec log to the database.
	 *
	 * @param activityLogId the primary key for the new rec log
	 * @return the new rec log
	 */
	@Override
	public com.everis.rec.service.activity.logs.model.RecLog createRecLog(
		long activityLogId) {

		return _recLogLocalService.createRecLog(activityLogId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _recLogLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the rec log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RecLogLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param activityLogId the primary key of the rec log
	 * @return the rec log that was removed
	 * @throws PortalException if a rec log with the primary key could not be found
	 */
	@Override
	public com.everis.rec.service.activity.logs.model.RecLog deleteRecLog(
			long activityLogId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _recLogLocalService.deleteRecLog(activityLogId);
	}

	/**
	 * Deletes the rec log from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RecLogLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param recLog the rec log
	 * @return the rec log that was removed
	 */
	@Override
	public com.everis.rec.service.activity.logs.model.RecLog deleteRecLog(
		com.everis.rec.service.activity.logs.model.RecLog recLog) {

		return _recLogLocalService.deleteRecLog(recLog);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _recLogLocalService.dynamicQuery();
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

		return _recLogLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.everis.rec.service.activity.logs.model.impl.RecLogModelImpl</code>.
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

		return _recLogLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.everis.rec.service.activity.logs.model.impl.RecLogModelImpl</code>.
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

		return _recLogLocalService.dynamicQuery(
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

		return _recLogLocalService.dynamicQueryCount(dynamicQuery);
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

		return _recLogLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.everis.rec.service.activity.logs.model.RecLog fetchRecLog(
		long activityLogId) {

		return _recLogLocalService.fetchRecLog(activityLogId);
	}

	/**
	 * Returns the rec log matching the UUID and group.
	 *
	 * @param uuid the rec log's UUID
	 * @param groupId the primary key of the group
	 * @return the matching rec log, or <code>null</code> if a matching rec log could not be found
	 */
	@Override
	public com.everis.rec.service.activity.logs.model.RecLog
		fetchRecLogByUuidAndGroupId(String uuid, long groupId) {

		return _recLogLocalService.fetchRecLogByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public java.util.List<com.everis.rec.service.activity.logs.model.RecLog>
		findAll() {

		return _recLogLocalService.findAll();
	}

	@Override
	public java.util.List<com.everis.rec.service.activity.logs.model.RecLog>
		findByJournaArticleClassPK(long journalArticleClassPK) {

		return _recLogLocalService.findByJournaArticleClassPK(
			journalArticleClassPK);
	}

	@Override
	public java.util.List<com.everis.rec.service.activity.logs.model.RecLog>
		findByJournaArticleClassPK(
			long journalArticleClassPK, int start, int end) {

		return _recLogLocalService.findByJournaArticleClassPK(
			journalArticleClassPK, start, end);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _recLogLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _recLogLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _recLogLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _recLogLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the rec log with the primary key.
	 *
	 * @param activityLogId the primary key of the rec log
	 * @return the rec log
	 * @throws PortalException if a rec log with the primary key could not be found
	 */
	@Override
	public com.everis.rec.service.activity.logs.model.RecLog getRecLog(
			long activityLogId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _recLogLocalService.getRecLog(activityLogId);
	}

	/**
	 * Returns the rec log matching the UUID and group.
	 *
	 * @param uuid the rec log's UUID
	 * @param groupId the primary key of the group
	 * @return the matching rec log
	 * @throws PortalException if a matching rec log could not be found
	 */
	@Override
	public com.everis.rec.service.activity.logs.model.RecLog
			getRecLogByUuidAndGroupId(String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _recLogLocalService.getRecLogByUuidAndGroupId(uuid, groupId);
	}

	/**
	 * Returns a range of all the rec logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.everis.rec.service.activity.logs.model.impl.RecLogModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of rec logs
	 * @param end the upper bound of the range of rec logs (not inclusive)
	 * @return the range of rec logs
	 */
	@Override
	public java.util.List<com.everis.rec.service.activity.logs.model.RecLog>
		getRecLogs(int start, int end) {

		return _recLogLocalService.getRecLogs(start, end);
	}

	/**
	 * Returns the number of rec logs.
	 *
	 * @return the number of rec logs
	 */
	@Override
	public int getRecLogsCount() {
		return _recLogLocalService.getRecLogsCount();
	}

	@Override
	public com.everis.rec.service.activity.logs.model.RecLog
		registerActivityLog(
			long groupId, long journalArticleResourcePK, String type,
			String textMessage) {

		return _recLogLocalService.registerActivityLog(
			groupId, journalArticleResourcePK, type, textMessage);
	}

	/**
	 * Updates the rec log in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RecLogLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param recLog the rec log
	 * @return the rec log that was updated
	 */
	@Override
	public com.everis.rec.service.activity.logs.model.RecLog updateRecLog(
		com.everis.rec.service.activity.logs.model.RecLog recLog) {

		return _recLogLocalService.updateRecLog(recLog);
	}

	@Override
	public RecLogLocalService getWrappedService() {
		return _recLogLocalService;
	}

	@Override
	public void setWrappedService(RecLogLocalService recLogLocalService) {
		_recLogLocalService = recLogLocalService;
	}

	private RecLogLocalService _recLogLocalService;

}