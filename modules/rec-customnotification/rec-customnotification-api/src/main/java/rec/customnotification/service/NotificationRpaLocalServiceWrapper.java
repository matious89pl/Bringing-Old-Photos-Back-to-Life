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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link NotificationRpaLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see NotificationRpaLocalService
 * @generated
 */
public class NotificationRpaLocalServiceWrapper
	implements NotificationRpaLocalService,
			   ServiceWrapper<NotificationRpaLocalService> {

	public NotificationRpaLocalServiceWrapper(
		NotificationRpaLocalService notificationRpaLocalService) {

		_notificationRpaLocalService = notificationRpaLocalService;
	}

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
	@Override
	public rec.customnotification.model.NotificationRpa addNotificationRpa(
		rec.customnotification.model.NotificationRpa notificationRpa) {

		return _notificationRpaLocalService.addNotificationRpa(notificationRpa);
	}

	/**
	 * Creates a new notification rpa with the primary key. Does not add the notification rpa to the database.
	 *
	 * @param customNotificationId the primary key for the new notification rpa
	 * @return the new notification rpa
	 */
	@Override
	public rec.customnotification.model.NotificationRpa createNotificationRpa(
		long customNotificationId) {

		return _notificationRpaLocalService.createNotificationRpa(
			customNotificationId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _notificationRpaLocalService.createPersistedModel(primaryKeyObj);
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
	@Override
	public rec.customnotification.model.NotificationRpa deleteNotificationRpa(
			long customNotificationId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _notificationRpaLocalService.deleteNotificationRpa(
			customNotificationId);
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
	@Override
	public rec.customnotification.model.NotificationRpa deleteNotificationRpa(
		rec.customnotification.model.NotificationRpa notificationRpa) {

		return _notificationRpaLocalService.deleteNotificationRpa(
			notificationRpa);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _notificationRpaLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _notificationRpaLocalService.dynamicQuery();
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

		return _notificationRpaLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _notificationRpaLocalService.dynamicQuery(
			dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _notificationRpaLocalService.dynamicQuery(
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

		return _notificationRpaLocalService.dynamicQueryCount(dynamicQuery);
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

		return _notificationRpaLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public rec.customnotification.model.NotificationRpa fetchNotificationRpa(
		long customNotificationId) {

		return _notificationRpaLocalService.fetchNotificationRpa(
			customNotificationId);
	}

	/**
	 * Returns the notification rpa matching the UUID and group.
	 *
	 * @param uuid the notification rpa's UUID
	 * @param groupId the primary key of the group
	 * @return the matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	@Override
	public rec.customnotification.model.NotificationRpa
		fetchNotificationRpaByUuidAndGroupId(String uuid, long groupId) {

		return _notificationRpaLocalService.
			fetchNotificationRpaByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public java.util.List<rec.customnotification.model.NotificationRpa>
		findAll() {

		return _notificationRpaLocalService.findAll();
	}

	@Override
	public rec.customnotification.model.NotificationRpa
			findByCustomNotificationId(long customNotificationId)
		throws rec.customnotification.exception.NoSuchNotificationRpaException {

		return _notificationRpaLocalService.findByCustomNotificationId(
			customNotificationId);
	}

	@Override
	public java.util.List<rec.customnotification.model.NotificationRpa>
		findByDates(long groupId, long plidPage, java.util.Date createDate) {

		return _notificationRpaLocalService.findByDates(
			groupId, plidPage, createDate);
	}

	@Override
	public java.util.List<rec.customnotification.model.NotificationRpa>
		findByGroupId(long groupId) {

		return _notificationRpaLocalService.findByGroupId(groupId);
	}

	@Override
	public java.util.List<rec.customnotification.model.NotificationRpa>
		findByGroupIdandPlidPage(long groupId, long plidPage) {

		return _notificationRpaLocalService.findByGroupIdandPlidPage(
			groupId, plidPage);
	}

	@Override
	public java.util.List<rec.customnotification.model.NotificationRpa>
		findByGroupPlidSearchContainer(
			long groupId, long plidPage, int start, int end) {

		return _notificationRpaLocalService.findByGroupPlidSearchContainer(
			groupId, plidPage, start, end);
	}

	@Override
	public int findByGroupPlidSearchContainterTotal(
		long groupId, long plidPage) {

		return _notificationRpaLocalService.
			findByGroupPlidSearchContainterTotal(groupId, plidPage);
	}

	@Override
	public java.util.List<rec.customnotification.model.NotificationRpa>
		findByNotificationBody(
			long groupId, long plidPage, String notificationBody) {

		return _notificationRpaLocalService.findByNotificationBody(
			groupId, plidPage, notificationBody);
	}

	@Override
	public java.util.List<rec.customnotification.model.NotificationRpa>
		findByNotificationBodyIndexOf(
			long groupId, long plidPage, String notificationBody) {

		return _notificationRpaLocalService.findByNotificationBodyIndexOf(
			groupId, plidPage, notificationBody);
	}

	@Override
	public java.util.List<rec.customnotification.model.NotificationRpa>
		findByNotificationTitle(
			long groupId, long plidPage, String notificationTitle) {

		return _notificationRpaLocalService.findByNotificationTitle(
			groupId, plidPage, notificationTitle);
	}

	@Override
	public java.util.List<rec.customnotification.model.NotificationRpa>
		findByNotificationTitleIndexOf(
			long groupId, long plidPage, String notificationTitle) {

		return _notificationRpaLocalService.findByNotificationTitleIndexOf(
			groupId, plidPage, notificationTitle);
	}

	@Override
	public java.util.List<rec.customnotification.model.NotificationRpa>
		findByTargetName(long groupId, long plidPage, String targetName) {

		return _notificationRpaLocalService.findByTargetName(
			groupId, plidPage, targetName);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _notificationRpaLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _notificationRpaLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _notificationRpaLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the notification rpa with the primary key.
	 *
	 * @param customNotificationId the primary key of the notification rpa
	 * @return the notification rpa
	 * @throws PortalException if a notification rpa with the primary key could not be found
	 */
	@Override
	public rec.customnotification.model.NotificationRpa getNotificationRpa(
			long customNotificationId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _notificationRpaLocalService.getNotificationRpa(
			customNotificationId);
	}

	/**
	 * Returns the notification rpa matching the UUID and group.
	 *
	 * @param uuid the notification rpa's UUID
	 * @param groupId the primary key of the group
	 * @return the matching notification rpa
	 * @throws PortalException if a matching notification rpa could not be found
	 */
	@Override
	public rec.customnotification.model.NotificationRpa
			getNotificationRpaByUuidAndGroupId(String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _notificationRpaLocalService.getNotificationRpaByUuidAndGroupId(
			uuid, groupId);
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
	@Override
	public java.util.List<rec.customnotification.model.NotificationRpa>
		getNotificationRpas(int start, int end) {

		return _notificationRpaLocalService.getNotificationRpas(start, end);
	}

	/**
	 * Returns all the notification rpas matching the UUID and company.
	 *
	 * @param uuid the UUID of the notification rpas
	 * @param companyId the primary key of the company
	 * @return the matching notification rpas, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<rec.customnotification.model.NotificationRpa>
		getNotificationRpasByUuidAndCompanyId(String uuid, long companyId) {

		return _notificationRpaLocalService.
			getNotificationRpasByUuidAndCompanyId(uuid, companyId);
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
	@Override
	public java.util.List<rec.customnotification.model.NotificationRpa>
		getNotificationRpasByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<rec.customnotification.model.NotificationRpa>
					orderByComparator) {

		return _notificationRpaLocalService.
			getNotificationRpasByUuidAndCompanyId(
				uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of notification rpas.
	 *
	 * @return the number of notification rpas
	 */
	@Override
	public int getNotificationRpasCount() {
		return _notificationRpaLocalService.getNotificationRpasCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _notificationRpaLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _notificationRpaLocalService.getPersistedModel(primaryKeyObj);
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
	@Override
	public rec.customnotification.model.NotificationRpa updateNotificationRpa(
		rec.customnotification.model.NotificationRpa notificationRpa) {

		return _notificationRpaLocalService.updateNotificationRpa(
			notificationRpa);
	}

	@Override
	public NotificationRpaLocalService getWrappedService() {
		return _notificationRpaLocalService;
	}

	@Override
	public void setWrappedService(
		NotificationRpaLocalService notificationRpaLocalService) {

		_notificationRpaLocalService = notificationRpaLocalService;
	}

	private NotificationRpaLocalService _notificationRpaLocalService;

}