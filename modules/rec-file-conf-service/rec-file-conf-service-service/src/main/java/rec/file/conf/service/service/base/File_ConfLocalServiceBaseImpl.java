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

package rec.file.conf.service.service.base;

import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Reference;

import rec.file.conf.service.model.File_Conf;
import rec.file.conf.service.service.File_ConfLocalService;
import rec.file.conf.service.service.persistence.File_ConfPersistence;

/**
 * Provides the base implementation for the file_ conf local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link rec.file.conf.service.service.impl.File_ConfLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see rec.file.conf.service.service.impl.File_ConfLocalServiceImpl
 * @generated
 */
public abstract class File_ConfLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, File_ConfLocalService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>File_ConfLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>rec.file.conf.service.service.File_ConfLocalServiceUtil</code>.
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
	@Override
	public File_Conf addFile_Conf(File_Conf file_Conf) {
		file_Conf.setNew(true);

		return file_ConfPersistence.update(file_Conf);
	}

	/**
	 * Creates a new file_ conf with the primary key. Does not add the file_ conf to the database.
	 *
	 * @param docConfigId the primary key for the new file_ conf
	 * @return the new file_ conf
	 */
	@Override
	@Transactional(enabled = false)
	public File_Conf createFile_Conf(long docConfigId) {
		return file_ConfPersistence.create(docConfigId);
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
	@Indexable(type = IndexableType.DELETE)
	@Override
	public File_Conf deleteFile_Conf(long docConfigId) throws PortalException {
		return file_ConfPersistence.remove(docConfigId);
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
	@Indexable(type = IndexableType.DELETE)
	@Override
	public File_Conf deleteFile_Conf(File_Conf file_Conf) {
		return file_ConfPersistence.remove(file_Conf);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			File_Conf.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return file_ConfPersistence.findWithDynamicQuery(dynamicQuery);
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
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return file_ConfPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
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
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return file_ConfPersistence.findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return file_ConfPersistence.countWithDynamicQuery(dynamicQuery);
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
		DynamicQuery dynamicQuery, Projection projection) {

		return file_ConfPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public File_Conf fetchFile_Conf(long docConfigId) {
		return file_ConfPersistence.fetchByPrimaryKey(docConfigId);
	}

	/**
	 * Returns the file_ conf matching the UUID and group.
	 *
	 * @param uuid the file_ conf's UUID
	 * @param groupId the primary key of the group
	 * @return the matching file_ conf, or <code>null</code> if a matching file_ conf could not be found
	 */
	@Override
	public File_Conf fetchFile_ConfByUuidAndGroupId(String uuid, long groupId) {
		return file_ConfPersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the file_ conf with the primary key.
	 *
	 * @param docConfigId the primary key of the file_ conf
	 * @return the file_ conf
	 * @throws PortalException if a file_ conf with the primary key could not be found
	 */
	@Override
	public File_Conf getFile_Conf(long docConfigId) throws PortalException {
		return file_ConfPersistence.findByPrimaryKey(docConfigId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(file_ConfLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(File_Conf.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("docConfigId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			file_ConfLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(File_Conf.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"docConfigId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(file_ConfLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(File_Conf.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("docConfigId");
	}

	@Override
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		final PortletDataContext portletDataContext) {

		final ExportActionableDynamicQuery exportActionableDynamicQuery =
			new ExportActionableDynamicQuery() {

				@Override
				public long performCount() throws PortalException {
					ManifestSummary manifestSummary =
						portletDataContext.getManifestSummary();

					StagedModelType stagedModelType = getStagedModelType();

					long modelAdditionCount = super.performCount();

					manifestSummary.addModelAdditionCount(
						stagedModelType, modelAdditionCount);

					long modelDeletionCount =
						ExportImportHelperUtil.getModelDeletionCount(
							portletDataContext, stagedModelType);

					manifestSummary.addModelDeletionCount(
						stagedModelType, modelDeletionCount);

					return modelAdditionCount;
				}

			};

		initActionableDynamicQuery(exportActionableDynamicQuery);

		exportActionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					portletDataContext.addDateRangeCriteria(
						dynamicQuery, "modifiedDate");
				}

			});

		exportActionableDynamicQuery.setCompanyId(
			portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<File_Conf>() {

				@Override
				public void performAction(File_Conf file_Conf)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, file_Conf);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(File_Conf.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return file_ConfPersistence.create(((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return file_ConfLocalService.deleteFile_Conf((File_Conf)persistedModel);
	}

	public BasePersistence<File_Conf> getBasePersistence() {
		return file_ConfPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return file_ConfPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns all the file_ confs matching the UUID and company.
	 *
	 * @param uuid the UUID of the file_ confs
	 * @param companyId the primary key of the company
	 * @return the matching file_ confs, or an empty list if no matches were found
	 */
	@Override
	public List<File_Conf> getFile_ConfsByUuidAndCompanyId(
		String uuid, long companyId) {

		return file_ConfPersistence.findByUuid_C(uuid, companyId);
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
	public List<File_Conf> getFile_ConfsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<File_Conf> orderByComparator) {

		return file_ConfPersistence.findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
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
	public File_Conf getFile_ConfByUuidAndGroupId(String uuid, long groupId)
		throws PortalException {

		return file_ConfPersistence.findByUUID_G(uuid, groupId);
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
	public List<File_Conf> getFile_Confs(int start, int end) {
		return file_ConfPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of file_ confs.
	 *
	 * @return the number of file_ confs
	 */
	@Override
	public int getFile_ConfsCount() {
		return file_ConfPersistence.countAll();
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
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public File_Conf updateFile_Conf(File_Conf file_Conf) {
		return file_ConfPersistence.update(file_Conf);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			File_ConfLocalService.class, IdentifiableOSGiService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		file_ConfLocalService = (File_ConfLocalService)aopProxy;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return File_ConfLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return File_Conf.class;
	}

	protected String getModelClassName() {
		return File_Conf.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = file_ConfPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	protected File_ConfLocalService file_ConfLocalService;

	@Reference
	protected File_ConfPersistence file_ConfPersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

}