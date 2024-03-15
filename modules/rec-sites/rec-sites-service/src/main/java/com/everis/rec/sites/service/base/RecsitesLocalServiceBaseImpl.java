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

package com.everis.rec.sites.service.base;

import com.everis.rec.sites.model.Recsites;
import com.everis.rec.sites.service.RecsitesLocalService;
import com.everis.rec.sites.service.persistence.RecsitesPersistence;

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

/**
 * Provides the base implementation for the recsites local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.everis.rec.sites.service.impl.RecsitesLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.everis.rec.sites.service.impl.RecsitesLocalServiceImpl
 * @generated
 */
public abstract class RecsitesLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, IdentifiableOSGiService, RecsitesLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>RecsitesLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.everis.rec.sites.service.RecsitesLocalServiceUtil</code>.
	 */

	/**
	 * Adds the recsites to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RecsitesLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param recsites the recsites
	 * @return the recsites that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Recsites addRecsites(Recsites recsites) {
		recsites.setNew(true);

		return recsitesPersistence.update(recsites);
	}

	/**
	 * Creates a new recsites with the primary key. Does not add the recsites to the database.
	 *
	 * @param fooId the primary key for the new recsites
	 * @return the new recsites
	 */
	@Override
	@Transactional(enabled = false)
	public Recsites createRecsites(long fooId) {
		return recsitesPersistence.create(fooId);
	}

	/**
	 * Deletes the recsites with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RecsitesLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param fooId the primary key of the recsites
	 * @return the recsites that was removed
	 * @throws PortalException if a recsites with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Recsites deleteRecsites(long fooId) throws PortalException {
		return recsitesPersistence.remove(fooId);
	}

	/**
	 * Deletes the recsites from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RecsitesLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param recsites the recsites
	 * @return the recsites that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Recsites deleteRecsites(Recsites recsites) {
		return recsitesPersistence.remove(recsites);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			Recsites.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return recsitesPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.everis.rec.sites.model.impl.RecsitesModelImpl</code>.
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

		return recsitesPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.everis.rec.sites.model.impl.RecsitesModelImpl</code>.
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

		return recsitesPersistence.findWithDynamicQuery(
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
		return recsitesPersistence.countWithDynamicQuery(dynamicQuery);
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

		return recsitesPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public Recsites fetchRecsites(long fooId) {
		return recsitesPersistence.fetchByPrimaryKey(fooId);
	}

	/**
	 * Returns the recsites matching the UUID and group.
	 *
	 * @param uuid the recsites's UUID
	 * @param groupId the primary key of the group
	 * @return the matching recsites, or <code>null</code> if a matching recsites could not be found
	 */
	@Override
	public Recsites fetchRecsitesByUuidAndGroupId(String uuid, long groupId) {
		return recsitesPersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the recsites with the primary key.
	 *
	 * @param fooId the primary key of the recsites
	 * @return the recsites
	 * @throws PortalException if a recsites with the primary key could not be found
	 */
	@Override
	public Recsites getRecsites(long fooId) throws PortalException {
		return recsitesPersistence.findByPrimaryKey(fooId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(recsitesLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Recsites.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("fooId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			recsitesLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(Recsites.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("fooId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(recsitesLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Recsites.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("fooId");
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
			new ActionableDynamicQuery.PerformActionMethod<Recsites>() {

				@Override
				public void performAction(Recsites recsites)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, recsites);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(Recsites.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return recsitesPersistence.create(((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return recsitesLocalService.deleteRecsites((Recsites)persistedModel);
	}

	public BasePersistence<Recsites> getBasePersistence() {
		return recsitesPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return recsitesPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns all the recsiteses matching the UUID and company.
	 *
	 * @param uuid the UUID of the recsiteses
	 * @param companyId the primary key of the company
	 * @return the matching recsiteses, or an empty list if no matches were found
	 */
	@Override
	public List<Recsites> getRecsitesesByUuidAndCompanyId(
		String uuid, long companyId) {

		return recsitesPersistence.findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of recsiteses matching the UUID and company.
	 *
	 * @param uuid the UUID of the recsiteses
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of recsiteses
	 * @param end the upper bound of the range of recsiteses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching recsiteses, or an empty list if no matches were found
	 */
	@Override
	public List<Recsites> getRecsitesesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Recsites> orderByComparator) {

		return recsitesPersistence.findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the recsites matching the UUID and group.
	 *
	 * @param uuid the recsites's UUID
	 * @param groupId the primary key of the group
	 * @return the matching recsites
	 * @throws PortalException if a matching recsites could not be found
	 */
	@Override
	public Recsites getRecsitesByUuidAndGroupId(String uuid, long groupId)
		throws PortalException {

		return recsitesPersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the recsiteses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.everis.rec.sites.model.impl.RecsitesModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of recsiteses
	 * @param end the upper bound of the range of recsiteses (not inclusive)
	 * @return the range of recsiteses
	 */
	@Override
	public List<Recsites> getRecsiteses(int start, int end) {
		return recsitesPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of recsiteses.
	 *
	 * @return the number of recsiteses
	 */
	@Override
	public int getRecsitesesCount() {
		return recsitesPersistence.countAll();
	}

	/**
	 * Updates the recsites in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RecsitesLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param recsites the recsites
	 * @return the recsites that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Recsites updateRecsites(Recsites recsites) {
		return recsitesPersistence.update(recsites);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			RecsitesLocalService.class, IdentifiableOSGiService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		recsitesLocalService = (RecsitesLocalService)aopProxy;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return RecsitesLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return Recsites.class;
	}

	protected String getModelClassName() {
		return Recsites.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = recsitesPersistence.getDataSource();

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

	protected RecsitesLocalService recsitesLocalService;

	@Reference
	protected RecsitesPersistence recsitesPersistence;

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

	@Reference
	protected com.liferay.asset.kernel.service.AssetEntryLocalService
		assetEntryLocalService;

	@Reference
	protected com.liferay.asset.kernel.service.AssetTagLocalService
		assetTagLocalService;

}