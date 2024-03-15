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

package com.everis.rec.ddl.journal.article.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DDL_JournalArticleLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see DDL_JournalArticleLocalService
 * @generated
 */
public class DDL_JournalArticleLocalServiceWrapper
	implements DDL_JournalArticleLocalService,
			   ServiceWrapper<DDL_JournalArticleLocalService> {

	public DDL_JournalArticleLocalServiceWrapper(
		DDL_JournalArticleLocalService ddl_JournalArticleLocalService) {

		_ddl_JournalArticleLocalService = ddl_JournalArticleLocalService;
	}

	/**
	 * Adds the ddl_ journal article to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DDL_JournalArticleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param ddl_JournalArticle the ddl_ journal article
	 * @return the ddl_ journal article that was added
	 */
	@Override
	public com.everis.rec.ddl.journal.article.model.DDL_JournalArticle
		addDDL_JournalArticle(
			com.everis.rec.ddl.journal.article.model.DDL_JournalArticle
				ddl_JournalArticle) {

		return _ddl_JournalArticleLocalService.addDDL_JournalArticle(
			ddl_JournalArticle);
	}

	/**
	 * Creates a new ddl_ journal article with the primary key. Does not add the ddl_ journal article to the database.
	 *
	 * @param ddlRecordSetId the primary key for the new ddl_ journal article
	 * @return the new ddl_ journal article
	 */
	@Override
	public com.everis.rec.ddl.journal.article.model.DDL_JournalArticle
		createDDL_JournalArticle(long ddlRecordSetId) {

		return _ddl_JournalArticleLocalService.createDDL_JournalArticle(
			ddlRecordSetId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddl_JournalArticleLocalService.createPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Deletes the ddl_ journal article from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DDL_JournalArticleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param ddl_JournalArticle the ddl_ journal article
	 * @return the ddl_ journal article that was removed
	 */
	@Override
	public com.everis.rec.ddl.journal.article.model.DDL_JournalArticle
		deleteDDL_JournalArticle(
			com.everis.rec.ddl.journal.article.model.DDL_JournalArticle
				ddl_JournalArticle) {

		return _ddl_JournalArticleLocalService.deleteDDL_JournalArticle(
			ddl_JournalArticle);
	}

	/**
	 * Deletes the ddl_ journal article with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DDL_JournalArticleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param ddlRecordSetId the primary key of the ddl_ journal article
	 * @return the ddl_ journal article that was removed
	 * @throws PortalException if a ddl_ journal article with the primary key could not be found
	 */
	@Override
	public com.everis.rec.ddl.journal.article.model.DDL_JournalArticle
			deleteDDL_JournalArticle(long ddlRecordSetId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddl_JournalArticleLocalService.deleteDDL_JournalArticle(
			ddlRecordSetId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddl_JournalArticleLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _ddl_JournalArticleLocalService.dynamicQuery();
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

		return _ddl_JournalArticleLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.everis.rec.ddl.journal.article.model.impl.DDL_JournalArticleModelImpl</code>.
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

		return _ddl_JournalArticleLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.everis.rec.ddl.journal.article.model.impl.DDL_JournalArticleModelImpl</code>.
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

		return _ddl_JournalArticleLocalService.dynamicQuery(
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

		return _ddl_JournalArticleLocalService.dynamicQueryCount(dynamicQuery);
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

		return _ddl_JournalArticleLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.everis.rec.ddl.journal.article.model.DDL_JournalArticle
		fetchDDL_JournalArticle(long ddlRecordSetId) {

		return _ddl_JournalArticleLocalService.fetchDDL_JournalArticle(
			ddlRecordSetId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _ddl_JournalArticleLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the ddl_ journal article with the primary key.
	 *
	 * @param ddlRecordSetId the primary key of the ddl_ journal article
	 * @return the ddl_ journal article
	 * @throws PortalException if a ddl_ journal article with the primary key could not be found
	 */
	@Override
	public com.everis.rec.ddl.journal.article.model.DDL_JournalArticle
			getDDL_JournalArticle(long ddlRecordSetId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddl_JournalArticleLocalService.getDDL_JournalArticle(
			ddlRecordSetId);
	}

	@Override
	public java.util.List
		<com.everis.rec.ddl.journal.article.model.DDL_JournalArticle>
			getDDL_JournalArticleByJournalPK(long journalResourcePrimKey) {

		return _ddl_JournalArticleLocalService.getDDL_JournalArticleByJournalPK(
			journalResourcePrimKey);
	}

	@Override
	public com.everis.rec.ddl.journal.article.model.DDL_JournalArticle
		getDDL_JournalArticleByJournalPKAndName(
			long journalResourcePrimKey, String name) {

		return _ddl_JournalArticleLocalService.
			getDDL_JournalArticleByJournalPKAndName(
				journalResourcePrimKey, name);
	}

	/**
	 * Returns a range of all the ddl_ journal articles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.everis.rec.ddl.journal.article.model.impl.DDL_JournalArticleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ddl_ journal articles
	 * @param end the upper bound of the range of ddl_ journal articles (not inclusive)
	 * @return the range of ddl_ journal articles
	 */
	@Override
	public java.util.List
		<com.everis.rec.ddl.journal.article.model.DDL_JournalArticle>
			getDDL_JournalArticles(int start, int end) {

		return _ddl_JournalArticleLocalService.getDDL_JournalArticles(
			start, end);
	}

	/**
	 * Returns the number of ddl_ journal articles.
	 *
	 * @return the number of ddl_ journal articles
	 */
	@Override
	public int getDDL_JournalArticlesCount() {
		return _ddl_JournalArticleLocalService.getDDL_JournalArticlesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _ddl_JournalArticleLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _ddl_JournalArticleLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddl_JournalArticleLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the ddl_ journal article in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DDL_JournalArticleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param ddl_JournalArticle the ddl_ journal article
	 * @return the ddl_ journal article that was updated
	 */
	@Override
	public com.everis.rec.ddl.journal.article.model.DDL_JournalArticle
		updateDDL_JournalArticle(
			com.everis.rec.ddl.journal.article.model.DDL_JournalArticle
				ddl_JournalArticle) {

		return _ddl_JournalArticleLocalService.updateDDL_JournalArticle(
			ddl_JournalArticle);
	}

	@Override
	public DDL_JournalArticleLocalService getWrappedService() {
		return _ddl_JournalArticleLocalService;
	}

	@Override
	public void setWrappedService(
		DDL_JournalArticleLocalService ddl_JournalArticleLocalService) {

		_ddl_JournalArticleLocalService = ddl_JournalArticleLocalService;
	}

	private DDL_JournalArticleLocalService _ddl_JournalArticleLocalService;

}