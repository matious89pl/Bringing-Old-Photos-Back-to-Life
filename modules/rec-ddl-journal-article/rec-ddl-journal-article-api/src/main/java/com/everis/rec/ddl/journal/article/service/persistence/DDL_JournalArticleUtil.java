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

package com.everis.rec.ddl.journal.article.service.persistence;

import com.everis.rec.ddl.journal.article.model.DDL_JournalArticle;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the ddl_ journal article service. This utility wraps <code>com.everis.rec.ddl.journal.article.service.persistence.impl.DDL_JournalArticlePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DDL_JournalArticlePersistence
 * @generated
 */
public class DDL_JournalArticleUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(DDL_JournalArticle ddl_JournalArticle) {
		getPersistence().clearCache(ddl_JournalArticle);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, DDL_JournalArticle> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<DDL_JournalArticle> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<DDL_JournalArticle> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<DDL_JournalArticle> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<DDL_JournalArticle> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static DDL_JournalArticle update(
		DDL_JournalArticle ddl_JournalArticle) {

		return getPersistence().update(ddl_JournalArticle);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static DDL_JournalArticle update(
		DDL_JournalArticle ddl_JournalArticle, ServiceContext serviceContext) {

		return getPersistence().update(ddl_JournalArticle, serviceContext);
	}

	/**
	 * Caches the ddl_ journal article in the entity cache if it is enabled.
	 *
	 * @param ddl_JournalArticle the ddl_ journal article
	 */
	public static void cacheResult(DDL_JournalArticle ddl_JournalArticle) {
		getPersistence().cacheResult(ddl_JournalArticle);
	}

	/**
	 * Caches the ddl_ journal articles in the entity cache if it is enabled.
	 *
	 * @param ddl_JournalArticles the ddl_ journal articles
	 */
	public static void cacheResult(
		List<DDL_JournalArticle> ddl_JournalArticles) {

		getPersistence().cacheResult(ddl_JournalArticles);
	}

	/**
	 * Creates a new ddl_ journal article with the primary key. Does not add the ddl_ journal article to the database.
	 *
	 * @param ddlRecordSetId the primary key for the new ddl_ journal article
	 * @return the new ddl_ journal article
	 */
	public static DDL_JournalArticle create(long ddlRecordSetId) {
		return getPersistence().create(ddlRecordSetId);
	}

	/**
	 * Removes the ddl_ journal article with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ddlRecordSetId the primary key of the ddl_ journal article
	 * @return the ddl_ journal article that was removed
	 * @throws NoSuchDDL_JournalArticleException if a ddl_ journal article with the primary key could not be found
	 */
	public static DDL_JournalArticle remove(long ddlRecordSetId)
		throws com.everis.rec.ddl.journal.article.exception.
			NoSuchDDL_JournalArticleException {

		return getPersistence().remove(ddlRecordSetId);
	}

	public static DDL_JournalArticle updateImpl(
		DDL_JournalArticle ddl_JournalArticle) {

		return getPersistence().updateImpl(ddl_JournalArticle);
	}

	/**
	 * Returns the ddl_ journal article with the primary key or throws a <code>NoSuchDDL_JournalArticleException</code> if it could not be found.
	 *
	 * @param ddlRecordSetId the primary key of the ddl_ journal article
	 * @return the ddl_ journal article
	 * @throws NoSuchDDL_JournalArticleException if a ddl_ journal article with the primary key could not be found
	 */
	public static DDL_JournalArticle findByPrimaryKey(long ddlRecordSetId)
		throws com.everis.rec.ddl.journal.article.exception.
			NoSuchDDL_JournalArticleException {

		return getPersistence().findByPrimaryKey(ddlRecordSetId);
	}

	/**
	 * Returns the ddl_ journal article with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ddlRecordSetId the primary key of the ddl_ journal article
	 * @return the ddl_ journal article, or <code>null</code> if a ddl_ journal article with the primary key could not be found
	 */
	public static DDL_JournalArticle fetchByPrimaryKey(long ddlRecordSetId) {
		return getPersistence().fetchByPrimaryKey(ddlRecordSetId);
	}

	/**
	 * Returns all the ddl_ journal articles.
	 *
	 * @return the ddl_ journal articles
	 */
	public static List<DDL_JournalArticle> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the ddl_ journal articles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDL_JournalArticleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ddl_ journal articles
	 * @param end the upper bound of the range of ddl_ journal articles (not inclusive)
	 * @return the range of ddl_ journal articles
	 */
	public static List<DDL_JournalArticle> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the ddl_ journal articles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDL_JournalArticleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ddl_ journal articles
	 * @param end the upper bound of the range of ddl_ journal articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ddl_ journal articles
	 */
	public static List<DDL_JournalArticle> findAll(
		int start, int end,
		OrderByComparator<DDL_JournalArticle> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the ddl_ journal articles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDL_JournalArticleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ddl_ journal articles
	 * @param end the upper bound of the range of ddl_ journal articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of ddl_ journal articles
	 */
	public static List<DDL_JournalArticle> findAll(
		int start, int end,
		OrderByComparator<DDL_JournalArticle> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the ddl_ journal articles from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of ddl_ journal articles.
	 *
	 * @return the number of ddl_ journal articles
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static DDL_JournalArticlePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<DDL_JournalArticlePersistence, DDL_JournalArticlePersistence>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			DDL_JournalArticlePersistence.class);

		ServiceTracker
			<DDL_JournalArticlePersistence, DDL_JournalArticlePersistence>
				serviceTracker =
					new ServiceTracker
						<DDL_JournalArticlePersistence,
						 DDL_JournalArticlePersistence>(
							 bundle.getBundleContext(),
							 DDL_JournalArticlePersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}