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

import rec.file.conf.service.model.File_Conf;

/**
 * Provides the local service interface for File_Conf. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see File_ConfLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface File_ConfLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>rec.file.conf.service.service.impl.File_ConfLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the file_ conf local service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link File_ConfLocalServiceUtil} if injection and service tracking are not available.
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
	@Indexable(type = IndexableType.REINDEX)
	public File_Conf addFile_Conf(File_Conf file_Conf);

	/**
	 * Creates a new file_ conf with the primary key. Does not add the file_ conf to the database.
	 *
	 * @param docConfigId the primary key for the new file_ conf
	 * @return the new file_ conf
	 */
	@Transactional(enabled = false)
	public File_Conf createFile_Conf(long docConfigId);

	/**
	 * @throws PortalException
	 */
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

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
	@Indexable(type = IndexableType.DELETE)
	public File_Conf deleteFile_Conf(File_Conf file_Conf);

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
	@Indexable(type = IndexableType.DELETE)
	public File_Conf deleteFile_Conf(long docConfigId) throws PortalException;

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>rec.file.conf.service.model.impl.File_ConfModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>rec.file.conf.service.model.impl.File_ConfModelImpl</code>.
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
	public File_Conf fetchFile_Conf(long docConfigId);

	/**
	 * Returns the file_ conf matching the UUID and group.
	 *
	 * @param uuid the file_ conf's UUID
	 * @param groupId the primary key of the group
	 * @return the matching file_ conf, or <code>null</code> if a matching file_ conf could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public File_Conf fetchFile_ConfByUuidAndGroupId(String uuid, long groupId);

	public File_Conf findBydocFileType(String docFileType);

	public File_Conf findFileConfByName(String docConfName);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	/**
	 * Returns the file_ conf with the primary key.
	 *
	 * @param docConfigId the primary key of the file_ conf
	 * @return the file_ conf
	 * @throws PortalException if a file_ conf with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public File_Conf getFile_Conf(long docConfigId) throws PortalException;

	/**
	 * Returns the file_ conf matching the UUID and group.
	 *
	 * @param uuid the file_ conf's UUID
	 * @param groupId the primary key of the group
	 * @return the matching file_ conf
	 * @throws PortalException if a matching file_ conf could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public File_Conf getFile_ConfByUuidAndGroupId(String uuid, long groupId)
		throws PortalException;

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<File_Conf> getFile_Confs(int start, int end);

	/**
	 * Returns all the file_ confs matching the UUID and company.
	 *
	 * @param uuid the UUID of the file_ confs
	 * @param companyId the primary key of the company
	 * @return the matching file_ confs, or an empty list if no matches were found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<File_Conf> getFile_ConfsByUuidAndCompanyId(
		String uuid, long companyId);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<File_Conf> getFile_ConfsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<File_Conf> orderByComparator);

	/**
	 * Returns the number of file_ confs.
	 *
	 * @return the number of file_ confs
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getFile_ConfsCount();

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
	 * Updates the file_ conf in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect File_ConfLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param file_Conf the file_ conf
	 * @return the file_ conf that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public File_Conf updateFile_Conf(File_Conf file_Conf);

}