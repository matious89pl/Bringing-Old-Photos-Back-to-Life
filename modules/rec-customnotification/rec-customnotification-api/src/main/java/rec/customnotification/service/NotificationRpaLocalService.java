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

import java.util.Date;
import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

import rec.customnotification.exception.NoSuchNotificationRpaException;
import rec.customnotification.model.NotificationRpa;

/**
 * Provides the local service interface for NotificationRpa. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see NotificationRpaLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface NotificationRpaLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>rec.customnotification.service.impl.NotificationRpaLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the notification rpa local service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link NotificationRpaLocalServiceUtil} if injection and service tracking are not available.
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
	@Indexable(type = IndexableType.REINDEX)
	public NotificationRpa addNotificationRpa(NotificationRpa notificationRpa);

	/**
	 * Creates a new notification rpa with the primary key. Does not add the notification rpa to the database.
	 *
	 * @param customNotificationId the primary key for the new notification rpa
	 * @return the new notification rpa
	 */
	@Transactional(enabled = false)
	public NotificationRpa createNotificationRpa(long customNotificationId);

	/**
	 * @throws PortalException
	 */
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

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
	@Indexable(type = IndexableType.DELETE)
	public NotificationRpa deleteNotificationRpa(long customNotificationId)
		throws PortalException;

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
	@Indexable(type = IndexableType.DELETE)
	public NotificationRpa deleteNotificationRpa(
		NotificationRpa notificationRpa);

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>rec.customnotification.model.impl.NotificationRpaModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>rec.customnotification.model.impl.NotificationRpaModelImpl</code>.
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
	public NotificationRpa fetchNotificationRpa(long customNotificationId);

	/**
	 * Returns the notification rpa matching the UUID and group.
	 *
	 * @param uuid the notification rpa's UUID
	 * @param groupId the primary key of the group
	 * @return the matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public NotificationRpa fetchNotificationRpaByUuidAndGroupId(
		String uuid, long groupId);

	public List<NotificationRpa> findAll();

	public NotificationRpa findByCustomNotificationId(long customNotificationId)
		throws NoSuchNotificationRpaException;

	public List<NotificationRpa> findByDates(
		long groupId, long plidPage, Date createDate);

	public List<NotificationRpa> findByGroupId(long groupId);

	public List<NotificationRpa> findByGroupIdandPlidPage(
		long groupId, long plidPage);

	public List<NotificationRpa> findByGroupPlidSearchContainer(
		long groupId, long plidPage, int start, int end);

	public int findByGroupPlidSearchContainterTotal(
		long groupId, long plidPage);

	public List<NotificationRpa> findByNotificationBody(
		long groupId, long plidPage, String notificationBody);

	public List<NotificationRpa> findByNotificationBodyIndexOf(
		long groupId, long plidPage, String notificationBody);

	public List<NotificationRpa> findByNotificationTitle(
		long groupId, long plidPage, String notificationTitle);

	public List<NotificationRpa> findByNotificationTitleIndexOf(
		long groupId, long plidPage, String notificationTitle);

	public List<NotificationRpa> findByTargetName(
		long groupId, long plidPage, String targetName);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	 * Returns the notification rpa with the primary key.
	 *
	 * @param customNotificationId the primary key of the notification rpa
	 * @return the notification rpa
	 * @throws PortalException if a notification rpa with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public NotificationRpa getNotificationRpa(long customNotificationId)
		throws PortalException;

	/**
	 * Returns the notification rpa matching the UUID and group.
	 *
	 * @param uuid the notification rpa's UUID
	 * @param groupId the primary key of the group
	 * @return the matching notification rpa
	 * @throws PortalException if a matching notification rpa could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public NotificationRpa getNotificationRpaByUuidAndGroupId(
			String uuid, long groupId)
		throws PortalException;

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<NotificationRpa> getNotificationRpas(int start, int end);

	/**
	 * Returns all the notification rpas matching the UUID and company.
	 *
	 * @param uuid the UUID of the notification rpas
	 * @param companyId the primary key of the company
	 * @return the matching notification rpas, or an empty list if no matches were found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<NotificationRpa> getNotificationRpasByUuidAndCompanyId(
		String uuid, long companyId);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<NotificationRpa> getNotificationRpasByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<NotificationRpa> orderByComparator);

	/**
	 * Returns the number of notification rpas.
	 *
	 * @return the number of notification rpas
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getNotificationRpasCount();

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
	 * Updates the notification rpa in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect NotificationRpaLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param notificationRpa the notification rpa
	 * @return the notification rpa that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public NotificationRpa updateNotificationRpa(
		NotificationRpa notificationRpa);

}