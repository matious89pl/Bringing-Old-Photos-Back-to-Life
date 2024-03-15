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

package rec.file.conf.service.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for File_Conf. This utility wraps
 * <code>rec.file.conf.service.service.impl.File_ConfLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see File_ConfLocalService
 * @generated
 */
public class File_ConfLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>rec.file.conf.service.service.impl.File_ConfLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the file_ conf to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect File_ConfLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param file_Conf the file_ conf
	 * @return the file_ conf that was added
	 */
	public static rec.file.conf.service.model.File_Conf addFile_Conf(
		rec.file.conf.service.model.File_Conf file_Conf) {

		return getService().addFile_Conf(file_Conf);
	}

	/**
	 * Creates a new file_ conf with the primary key. Does not add the file_ conf to the database.
	 *
	 * @param docConfigId the primary key for the new file_ conf
	 * @return the new file_ conf
	 */
	public static rec.file.conf.service.model.File_Conf createFile_Conf(
		long docConfigId) {

		return getService().createFile_Conf(docConfigId);
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
	 * Deletes the file_ conf from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect File_ConfLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param file_Conf the file_ conf
	 * @return the file_ conf that was removed
	 */
	public static rec.file.conf.service.model.File_Conf deleteFile_Conf(
		rec.file.conf.service.model.File_Conf file_Conf) {

		return getService().deleteFile_Conf(file_Conf);
	}

	/**
	 * Deletes the file_ conf with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect File_ConfLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param docConfigId the primary key of the file_ conf
	 * @return the file_ conf that was removed
	 * @throws PortalException if a file_ conf with the primary key could not be found
	 */
	public static rec.file.conf.service.model.File_Conf deleteFile_Conf(
			long docConfigId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteFile_Conf(docConfigId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>rec.file.conf.service.model.impl.File_ConfModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>rec.file.conf.service.model.impl.File_ConfModelImpl</code>.
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

	public static rec.file.conf.service.model.File_Conf fetchFile_Conf(
		long docConfigId) {

		return getService().fetchFile_Conf(docConfigId);
	}

	/**
	 * Returns the file_ conf matching the UUID and group.
	 *
	 * @param uuid the file_ conf's UUID
	 * @param groupId the primary key of the group
	 * @return the matching file_ conf, or <code>null</code> if a matching file_ conf could not be found
	 */
	public static rec.file.conf.service.model.File_Conf
		fetchFile_ConfByUuidAndGroupId(String uuid, long groupId) {

		return getService().fetchFile_ConfByUuidAndGroupId(uuid, groupId);
	}

	public static rec.file.conf.service.model.File_Conf findBydocFileType(
		String docFileType) {

		return getService().findBydocFileType(docFileType);
	}

	public static rec.file.conf.service.model.File_Conf findFileConfByName(
		String docConfName) {

		return getService().findFileConfByName(docConfName);
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

	/**
	 * Returns the file_ conf with the primary key.
	 *
	 * @param docConfigId the primary key of the file_ conf
	 * @return the file_ conf
	 * @throws PortalException if a file_ conf with the primary key could not be found
	 */
	public static rec.file.conf.service.model.File_Conf getFile_Conf(
			long docConfigId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getFile_Conf(docConfigId);
	}

	/**
	 * Returns the file_ conf matching the UUID and group.
	 *
	 * @param uuid the file_ conf's UUID
	 * @param groupId the primary key of the group
	 * @return the matching file_ conf
	 * @throws PortalException if a matching file_ conf could not be found
	 */
	public static rec.file.conf.service.model.File_Conf
			getFile_ConfByUuidAndGroupId(String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getFile_ConfByUuidAndGroupId(uuid, groupId);
	}

	/**
	 * Returns a range of all the file_ confs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>rec.file.conf.service.model.impl.File_ConfModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of file_ confs
	 * @param end the upper bound of the range of file_ confs (not inclusive)
	 * @return the range of file_ confs
	 */
	public static java.util.List<rec.file.conf.service.model.File_Conf>
		getFile_Confs(int start, int end) {

		return getService().getFile_Confs(start, end);
	}

	/**
	 * Returns all the file_ confs matching the UUID and company.
	 *
	 * @param uuid the UUID of the file_ confs
	 * @param companyId the primary key of the company
	 * @return the matching file_ confs, or an empty list if no matches were found
	 */
	public static java.util.List<rec.file.conf.service.model.File_Conf>
		getFile_ConfsByUuidAndCompanyId(String uuid, long companyId) {

		return getService().getFile_ConfsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	 * Returns a range of file_ confs matching the UUID and company.
	 *
	 * @param uuid the UUID of the file_ confs
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of file_ confs
	 * @param end the upper bound of the range of file_ confs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching file_ confs, or an empty list if no matches were found
	 */
	public static java.util.List<rec.file.conf.service.model.File_Conf>
		getFile_ConfsByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<rec.file.conf.service.model.File_Conf> orderByComparator) {

		return getService().getFile_ConfsByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of file_ confs.
	 *
	 * @return the number of file_ confs
	 */
	public static int getFile_ConfsCount() {
		return getService().getFile_ConfsCount();
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
	 * Updates the file_ conf in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect File_ConfLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param file_Conf the file_ conf
	 * @return the file_ conf that was updated
	 */
	public static rec.file.conf.service.model.File_Conf updateFile_Conf(
		rec.file.conf.service.model.File_Conf file_Conf) {

		return getService().updateFile_Conf(file_Conf);
	}

	public static File_ConfLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<File_ConfLocalService, File_ConfLocalService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(File_ConfLocalService.class);

		ServiceTracker<File_ConfLocalService, File_ConfLocalService>
			serviceTracker =
				new ServiceTracker
					<File_ConfLocalService, File_ConfLocalService>(
						bundle.getBundleContext(), File_ConfLocalService.class,
						null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}