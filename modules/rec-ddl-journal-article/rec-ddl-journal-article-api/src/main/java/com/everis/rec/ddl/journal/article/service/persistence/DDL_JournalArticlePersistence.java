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

import com.everis.rec.ddl.journal.article.exception.NoSuchDDL_JournalArticleException;
import com.everis.rec.ddl.journal.article.model.DDL_JournalArticle;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the ddl_ journal article service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DDL_JournalArticleUtil
 * @generated
 */
@ProviderType
public interface DDL_JournalArticlePersistence
	extends BasePersistence<DDL_JournalArticle> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DDL_JournalArticleUtil} to access the ddl_ journal article persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the ddl_ journal article in the entity cache if it is enabled.
	 *
	 * @param ddl_JournalArticle the ddl_ journal article
	 */
	public void cacheResult(DDL_JournalArticle ddl_JournalArticle);

	/**
	 * Caches the ddl_ journal articles in the entity cache if it is enabled.
	 *
	 * @param ddl_JournalArticles the ddl_ journal articles
	 */
	public void cacheResult(
		java.util.List<DDL_JournalArticle> ddl_JournalArticles);

	/**
	 * Creates a new ddl_ journal article with the primary key. Does not add the ddl_ journal article to the database.
	 *
	 * @param ddlRecordSetId the primary key for the new ddl_ journal article
	 * @return the new ddl_ journal article
	 */
	public DDL_JournalArticle create(long ddlRecordSetId);

	/**
	 * Removes the ddl_ journal article with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ddlRecordSetId the primary key of the ddl_ journal article
	 * @return the ddl_ journal article that was removed
	 * @throws NoSuchDDL_JournalArticleException if a ddl_ journal article with the primary key could not be found
	 */
	public DDL_JournalArticle remove(long ddlRecordSetId)
		throws NoSuchDDL_JournalArticleException;

	public DDL_JournalArticle updateImpl(DDL_JournalArticle ddl_JournalArticle);

	/**
	 * Returns the ddl_ journal article with the primary key or throws a <code>NoSuchDDL_JournalArticleException</code> if it could not be found.
	 *
	 * @param ddlRecordSetId the primary key of the ddl_ journal article
	 * @return the ddl_ journal article
	 * @throws NoSuchDDL_JournalArticleException if a ddl_ journal article with the primary key could not be found
	 */
	public DDL_JournalArticle findByPrimaryKey(long ddlRecordSetId)
		throws NoSuchDDL_JournalArticleException;

	/**
	 * Returns the ddl_ journal article with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ddlRecordSetId the primary key of the ddl_ journal article
	 * @return the ddl_ journal article, or <code>null</code> if a ddl_ journal article with the primary key could not be found
	 */
	public DDL_JournalArticle fetchByPrimaryKey(long ddlRecordSetId);

	/**
	 * Returns all the ddl_ journal articles.
	 *
	 * @return the ddl_ journal articles
	 */
	public java.util.List<DDL_JournalArticle> findAll();

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
	public java.util.List<DDL_JournalArticle> findAll(int start, int end);

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
	public java.util.List<DDL_JournalArticle> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDL_JournalArticle>
			orderByComparator);

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
	public java.util.List<DDL_JournalArticle> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DDL_JournalArticle>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the ddl_ journal articles from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of ddl_ journal articles.
	 *
	 * @return the number of ddl_ journal articles
	 */
	public int countAll();

}