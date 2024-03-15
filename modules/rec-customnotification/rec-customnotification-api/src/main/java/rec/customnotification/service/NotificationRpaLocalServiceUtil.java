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

package rec.customnotification.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for NotificationRpa. This utility wraps
 * <code>rec.customnotification.service.impl.NotificationRpaLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see NotificationRpaLocalService
 * @generated
 */
public class NotificationRpaLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>rec.customnotification.service.impl.NotificationRpaLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the notification rpa to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NotificationRpaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param notificationRpa the notification rpa
	 * @return the notification rpa that was added
	 */
	public static rec.customnotification.model.NotificationRpa
		addNotificationRpa(
			rec.customnotification.model.NotificationRpa notificationRpa) {

		return getService().addNotificationRpa(notificationRpa);
	}

	/**
	 * Creates a new notification rpa with the primary key. Does not add the notification rpa to the database.
	 *
	 * @param customNotificationId the primary key for the new notification rpa
	 * @return the new notification rpa
	 */
	public static rec.customnotification.model.NotificationRpa
		createNotificationRpa(long customNotificationId) {

		return getService().createNotificationRpa(customNotificationId);
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
	 * Deletes the notification rpa with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NotificationRpaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param customNotificationId the primary key of the notification rpa
	 * @return the notification rpa that was removed
	 * @throws PortalException if a notification rpa with the primary key could not be found
	 */
	public static rec.customnotification.model.NotificationRpa
			deleteNotificationRpa(long customNotificationId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteNotificationRpa(customNotificationId);
	}

	/**
	 * Deletes the notification rpa from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NotificationRpaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param notificationRpa the notification rpa
	 * @return the notification rpa that was removed
	 */
	public static rec.customnotification.model.NotificationRpa
		deleteNotificationRpa(
			rec.customnotification.model.NotificationRpa notificationRpa) {

		return getService().deleteNotificationRpa(notificationRpa);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>rec.customnotification.model.impl.NotificationRpaModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>rec.customnotification.model.impl.NotificationRpaModelImpl</code>.
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

	public static rec.customnotification.model.NotificationRpa
		fetchNotificationRpa(long customNotificationId) {

		return getService().fetchNotificationRpa(customNotificationId);
	}

	/**
	 * Returns the notification rpa matching the UUID and group.
	 *
	 * @param uuid the notification rpa's UUID
	 * @param groupId the primary key of the group
	 * @return the matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	public static rec.customnotification.model.NotificationRpa
		fetchNotificationRpaByUuidAndGroupId(String uuid, long groupId) {

		return getService().fetchNotificationRpaByUuidAndGroupId(uuid, groupId);
	}

	public static java.util.List<rec.customnotification.model.NotificationRpa>
		findAll() {

		return getService().findAll();
	}

	public static rec.customnotification.model.NotificationRpa
			findByCustomNotificationId(long customNotificationId)
		throws rec.customnotification.exception.NoSuchNotificationRpaException {

		return getService().findByCustomNotificationId(customNotificationId);
	}

	public static java.util.List<rec.customnotification.model.NotificationRpa>
		findByDates(long groupId, long plidPage, java.util.Date createDate) {

		return getService().findByDates(groupId, plidPage, createDate);
	}

	public static java.util.List<rec.customnotification.model.NotificationRpa>
		findByGroupId(long groupId) {

		return getService().findByGroupId(groupId);
	}

	public static java.util.List<rec.customnotification.model.NotificationRpa>
		findByGroupIdandPlidPage(long groupId, long plidPage) {

		return getService().findByGroupIdandPlidPage(groupId, plidPage);
	}

	public static java.util.List<rec.customnotification.model.NotificationRpa>
		findByGroupPlidSearchContainer(
			long groupId, long plidPage, int start, int end) {

		return getService().findByGroupPlidSearchContainer(
			groupId, plidPage, start, end);
	}

	public static int findByGroupPlidSearchContainterTotal(
		long groupId, long plidPage) {

		return getService().findByGroupPlidSearchContainterTotal(
			groupId, plidPage);
	}

	public static java.util.List<rec.customnotification.model.NotificationRpa>
		findByNotificationBody(
			long groupId, long plidPage, String notificationBody) {

		return getService().findByNotificationBody(
			groupId, plidPage, notificationBody);
	}

	public static java.util.List<rec.customnotification.model.NotificationRpa>
		findByNotificationBodyIndexOf(
			long groupId, long plidPage, String notificationBody) {

		return getService().findByNotificationBodyIndexOf(
			groupId, plidPage, notificationBody);
	}

	public static java.util.List<rec.customnotification.model.NotificationRpa>
		findByNotificationTitle(
			long groupId, long plidPage, String notificationTitle) {

		return getService().findByNotificationTitle(
			groupId, plidPage, notificationTitle);
	}

	public static java.util.List<rec.customnotification.model.NotificationRpa>
		findByNotificationTitleIndexOf(
			long groupId, long plidPage, String notificationTitle) {

		return getService().findByNotificationTitleIndexOf(
			groupId, plidPage, notificationTitle);
	}

	public static java.util.List<rec.customnotification.model.NotificationRpa>
		findByTargetName(long groupId, long plidPage, String targetName) {

		return getService().findByTargetName(groupId, plidPage, targetName);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the notification rpa with the primary key.
	 *
	 * @param customNotificationId the primary key of the notification rpa
	 * @return the notification rpa
	 * @throws PortalException if a notification rpa with the primary key could not be found
	 */
	public static rec.customnotification.model.NotificationRpa
			getNotificationRpa(long customNotificationId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getNotificationRpa(customNotificationId);
	}

	/**
	 * Returns the notification rpa matching the UUID and group.
	 *
	 * @param uuid the notification rpa's UUID
	 * @param groupId the primary key of the group
	 * @return the matching notification rpa
	 * @throws PortalException if a matching notification rpa could not be found
	 */
	public static rec.customnotification.model.NotificationRpa
			getNotificationRpaByUuidAndGroupId(String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getNotificationRpaByUuidAndGroupId(uuid, groupId);
	}

	/**
	 * Returns a range of all the notification rpas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>rec.customnotification.model.impl.NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @return the range of notification rpas
	 */
	public static java.util.List<rec.customnotification.model.NotificationRpa>
		getNotificationRpas(int start, int end) {

		return getService().getNotificationRpas(start, end);
	}

	/**
	 * Returns all the notification rpas matching the UUID and company.
	 *
	 * @param uuid the UUID of the notification rpas
	 * @param companyId the primary key of the company
	 * @return the matching notification rpas, or an empty list if no matches were found
	 */
	public static java.util.List<rec.customnotification.model.NotificationRpa>
		getNotificationRpasByUuidAndCompanyId(String uuid, long companyId) {

		return getService().getNotificationRpasByUuidAndCompanyId(
			uuid, companyId);
	}

	/**
	 * Returns a range of notification rpas matching the UUID and company.
	 *
	 * @param uuid the UUID of the notification rpas
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching notification rpas, or an empty list if no matches were found
	 */
	public static java.util.List<rec.customnotification.model.NotificationRpa>
		getNotificationRpasByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<rec.customnotification.model.NotificationRpa>
					orderByComparator) {

		return getService().getNotificationRpasByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of notification rpas.
	 *
	 * @return the number of notification rpas
	 */
	public static int getNotificationRpasCount() {
		return getService().getNotificationRpasCount();
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
	 * Updates the notification rpa in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NotificationRpaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param notificationRpa the notification rpa
	 * @return the notification rpa that was updated
	 */
	public static rec.customnotification.model.NotificationRpa
		updateNotificationRpa(
			rec.customnotification.model.NotificationRpa notificationRpa) {

		return getService().updateNotificationRpa(notificationRpa);
	}

	public static NotificationRpaLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<NotificationRpaLocalService, NotificationRpaLocalService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			NotificationRpaLocalService.class);

		ServiceTracker<NotificationRpaLocalService, NotificationRpaLocalService>
			serviceTracker =
				new ServiceTracker
					<NotificationRpaLocalService, NotificationRpaLocalService>(
						bundle.getBundleContext(),
						NotificationRpaLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}