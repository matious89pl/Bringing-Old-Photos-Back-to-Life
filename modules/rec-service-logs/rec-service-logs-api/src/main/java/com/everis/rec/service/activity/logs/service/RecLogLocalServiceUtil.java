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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for RecLog. This utility wraps
 * <code>com.everis.rec.service.activity.logs.service.impl.RecLogLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see RecLogLocalService
 * @generated
 */
public class RecLogLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.everis.rec.service.activity.logs.service.impl.RecLogLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

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
	public static com.everis.rec.service.activity.logs.model.RecLog addRecLog(
		com.everis.rec.service.activity.logs.model.RecLog recLog) {

		return getService().addRecLog(recLog);
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
	 * Creates a new rec log with the primary key. Does not add the rec log to the database.
	 *
	 * @param activityLogId the primary key for the new rec log
	 * @return the new rec log
	 */
	public static com.everis.rec.service.activity.logs.model.RecLog
		createRecLog(long activityLogId) {

		return getService().createRecLog(activityLogId);
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
	public static com.everis.rec.service.activity.logs.model.RecLog
			deleteRecLog(long activityLogId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteRecLog(activityLogId);
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
	public static com.everis.rec.service.activity.logs.model.RecLog
		deleteRecLog(com.everis.rec.service.activity.logs.model.RecLog recLog) {

		return getService().deleteRecLog(recLog);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.everis.rec.service.activity.logs.model.impl.RecLogModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.everis.rec.service.activity.logs.model.impl.RecLogModelImpl</code>.
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

	public static com.everis.rec.service.activity.logs.model.RecLog fetchRecLog(
		long activityLogId) {

		return getService().fetchRecLog(activityLogId);
	}

	/**
	 * Returns the rec log matching the UUID and group.
	 *
	 * @param uuid the rec log's UUID
	 * @param groupId the primary key of the group
	 * @return the matching rec log, or <code>null</code> if a matching rec log could not be found
	 */
	public static com.everis.rec.service.activity.logs.model.RecLog
		fetchRecLogByUuidAndGroupId(String uuid, long groupId) {

		return getService().fetchRecLogByUuidAndGroupId(uuid, groupId);
	}

	public static java.util.List
		<com.everis.rec.service.activity.logs.model.RecLog> findAll() {

		return getService().findAll();
	}

	public static java.util.List
		<com.everis.rec.service.activity.logs.model.RecLog>
			findByJournaArticleClassPK(long journalArticleClassPK) {

		return getService().findByJournaArticleClassPK(journalArticleClassPK);
	}

	public static java.util.List
		<com.everis.rec.service.activity.logs.model.RecLog>
			findByJournaArticleClassPK(
				long journalArticleClassPK, int start, int end) {

		return getService().findByJournaArticleClassPK(
			journalArticleClassPK, start, end);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
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
	 * Returns the rec log with the primary key.
	 *
	 * @param activityLogId the primary key of the rec log
	 * @return the rec log
	 * @throws PortalException if a rec log with the primary key could not be found
	 */
	public static com.everis.rec.service.activity.logs.model.RecLog getRecLog(
			long activityLogId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getRecLog(activityLogId);
	}

	/**
	 * Returns the rec log matching the UUID and group.
	 *
	 * @param uuid the rec log's UUID
	 * @param groupId the primary key of the group
	 * @return the matching rec log
	 * @throws PortalException if a matching rec log could not be found
	 */
	public static com.everis.rec.service.activity.logs.model.RecLog
			getRecLogByUuidAndGroupId(String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getRecLogByUuidAndGroupId(uuid, groupId);
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
	public static java.util.List
		<com.everis.rec.service.activity.logs.model.RecLog> getRecLogs(
			int start, int end) {

		return getService().getRecLogs(start, end);
	}

	/**
	 * Returns the number of rec logs.
	 *
	 * @return the number of rec logs
	 */
	public static int getRecLogsCount() {
		return getService().getRecLogsCount();
	}

	public static com.everis.rec.service.activity.logs.model.RecLog
		registerActivityLog(
			long groupId, long journalArticleResourcePK, String type,
			String textMessage) {

		return getService().registerActivityLog(
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
	public static com.everis.rec.service.activity.logs.model.RecLog
		updateRecLog(com.everis.rec.service.activity.logs.model.RecLog recLog) {

		return getService().updateRecLog(recLog);
	}

	public static RecLogLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<RecLogLocalService, RecLogLocalService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(RecLogLocalService.class);

		ServiceTracker<RecLogLocalService, RecLogLocalService> serviceTracker =
			new ServiceTracker<RecLogLocalService, RecLogLocalService>(
				bundle.getBundleContext(), RecLogLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}