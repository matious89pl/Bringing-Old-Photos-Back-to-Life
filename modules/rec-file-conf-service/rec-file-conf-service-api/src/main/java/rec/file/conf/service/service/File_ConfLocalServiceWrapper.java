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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link File_ConfLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see File_ConfLocalService
 * @generated
 */
public class File_ConfLocalServiceWrapper
	implements File_ConfLocalService, ServiceWrapper<File_ConfLocalService> {

	public File_ConfLocalServiceWrapper(
		File_ConfLocalService file_ConfLocalService) {

		_file_ConfLocalService = file_ConfLocalService;
	}

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
	@Override
	public rec.file.conf.service.model.File_Conf addFile_Conf(
		rec.file.conf.service.model.File_Conf file_Conf) {

		return _file_ConfLocalService.addFile_Conf(file_Conf);
	}

	/**
	 * Creates a new file_ conf with the primary key. Does not add the file_ conf to the database.
	 *
	 * @param docConfigId the primary key for the new file_ conf
	 * @return the new file_ conf
	 */
	@Override
	public rec.file.conf.service.model.File_Conf createFile_Conf(
		long docConfigId) {

		return _file_ConfLocalService.createFile_Conf(docConfigId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _file_ConfLocalService.createPersistedModel(primaryKeyObj);
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
	@Override
	public rec.file.conf.service.model.File_Conf deleteFile_Conf(
		rec.file.conf.service.model.File_Conf file_Conf) {

		return _file_ConfLocalService.deleteFile_Conf(file_Conf);
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
	@Override
	public rec.file.conf.service.model.File_Conf deleteFile_Conf(
			long docConfigId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _file_ConfLocalService.deleteFile_Conf(docConfigId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _file_ConfLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _file_ConfLocalService.dynamicQuery();
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

		return _file_ConfLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _file_ConfLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _file_ConfLocalService.dynamicQuery(
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

		return _file_ConfLocalService.dynamicQueryCount(dynamicQuery);
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

		return _file_ConfLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public rec.file.conf.service.model.File_Conf fetchFile_Conf(
		long docConfigId) {

		return _file_ConfLocalService.fetchFile_Conf(docConfigId);
	}

	/**
	 * Returns the file_ conf matching the UUID and group.
	 *
	 * @param uuid the file_ conf's UUID
	 * @param groupId the primary key of the group
	 * @return the matching file_ conf, or <code>null</code> if a matching file_ conf could not be found
	 */
	@Override
	public rec.file.conf.service.model.File_Conf fetchFile_ConfByUuidAndGroupId(
		String uuid, long groupId) {

		return _file_ConfLocalService.fetchFile_ConfByUuidAndGroupId(
			uuid, groupId);
	}

	@Override
	public rec.file.conf.service.model.File_Conf findBydocFileType(
		String docFileType) {

		return _file_ConfLocalService.findBydocFileType(docFileType);
	}

	@Override
	public rec.file.conf.service.model.File_Conf findFileConfByName(
		String docConfName) {

		return _file_ConfLocalService.findFileConfByName(docConfName);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _file_ConfLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _file_ConfLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	/**
	 * Returns the file_ conf with the primary key.
	 *
	 * @param docConfigId the primary key of the file_ conf
	 * @return the file_ conf
	 * @throws PortalException if a file_ conf with the primary key could not be found
	 */
	@Override
	public rec.file.conf.service.model.File_Conf getFile_Conf(long docConfigId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _file_ConfLocalService.getFile_Conf(docConfigId);
	}

	/**
	 * Returns the file_ conf matching the UUID and group.
	 *
	 * @param uuid the file_ conf's UUID
	 * @param groupId the primary key of the group
	 * @return the matching file_ conf
	 * @throws PortalException if a matching file_ conf could not be found
	 */
	@Override
	public rec.file.conf.service.model.File_Conf getFile_ConfByUuidAndGroupId(
			String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _file_ConfLocalService.getFile_ConfByUuidAndGroupId(
			uuid, groupId);
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
	@Override
	public java.util.List<rec.file.conf.service.model.File_Conf> getFile_Confs(
		int start, int end) {

		return _file_ConfLocalService.getFile_Confs(start, end);
	}

	/**
	 * Returns all the file_ confs matching the UUID and company.
	 *
	 * @param uuid the UUID of the file_ confs
	 * @param companyId the primary key of the company
	 * @return the matching file_ confs, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<rec.file.conf.service.model.File_Conf>
		getFile_ConfsByUuidAndCompanyId(String uuid, long companyId) {

		return _file_ConfLocalService.getFile_ConfsByUuidAndCompanyId(
			uuid, companyId);
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
	@Override
	public java.util.List<rec.file.conf.service.model.File_Conf>
		getFile_ConfsByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<rec.file.conf.service.model.File_Conf> orderByComparator) {

		return _file_ConfLocalService.getFile_ConfsByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of file_ confs.
	 *
	 * @return the number of file_ confs
	 */
	@Override
	public int getFile_ConfsCount() {
		return _file_ConfLocalService.getFile_ConfsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _file_ConfLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _file_ConfLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _file_ConfLocalService.getPersistedModel(primaryKeyObj);
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
	@Override
	public rec.file.conf.service.model.File_Conf updateFile_Conf(
		rec.file.conf.service.model.File_Conf file_Conf) {

		return _file_ConfLocalService.updateFile_Conf(file_Conf);
	}

	@Override
	public File_ConfLocalService getWrappedService() {
		return _file_ConfLocalService;
	}

	@Override
	public void setWrappedService(File_ConfLocalService file_ConfLocalService) {
		_file_ConfLocalService = file_ConfLocalService;
	}

	private File_ConfLocalService _file_ConfLocalService;

}