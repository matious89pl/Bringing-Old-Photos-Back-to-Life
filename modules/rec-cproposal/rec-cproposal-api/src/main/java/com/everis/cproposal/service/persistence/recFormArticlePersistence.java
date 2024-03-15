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

package com.everis.cproposal.service.persistence;

import com.everis.cproposal.exception.NoSuchrecFormArticleException;
import com.everis.cproposal.model.recFormArticle;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the rec form article service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see recFormArticleUtil
 * @generated
 */
@ProviderType
public interface recFormArticlePersistence
	extends BasePersistence<recFormArticle> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link recFormArticleUtil} to access the rec form article persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the rec form articles where articleStatus = &#63; and articleReleaseSchedule = &#63;.
	 *
	 * @param articleStatus the article status
	 * @param articleReleaseSchedule the article release schedule
	 * @return the matching rec form articles
	 */
	public java.util.List<recFormArticle>
		findByarticleReleaseScheduleAndarticleStatus(
			String articleStatus, String articleReleaseSchedule);

	/**
	 * Returns a range of all the rec form articles where articleStatus = &#63; and articleReleaseSchedule = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>recFormArticleModelImpl</code>.
	 * </p>
	 *
	 * @param articleStatus the article status
	 * @param articleReleaseSchedule the article release schedule
	 * @param start the lower bound of the range of rec form articles
	 * @param end the upper bound of the range of rec form articles (not inclusive)
	 * @return the range of matching rec form articles
	 */
	public java.util.List<recFormArticle>
		findByarticleReleaseScheduleAndarticleStatus(
			String articleStatus, String articleReleaseSchedule, int start,
			int end);

	/**
	 * Returns an ordered range of all the rec form articles where articleStatus = &#63; and articleReleaseSchedule = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>recFormArticleModelImpl</code>.
	 * </p>
	 *
	 * @param articleStatus the article status
	 * @param articleReleaseSchedule the article release schedule
	 * @param start the lower bound of the range of rec form articles
	 * @param end the upper bound of the range of rec form articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rec form articles
	 */
	public java.util.List<recFormArticle>
		findByarticleReleaseScheduleAndarticleStatus(
			String articleStatus, String articleReleaseSchedule, int start,
			int end,
			com.liferay.portal.kernel.util.OrderByComparator<recFormArticle>
				orderByComparator);

	/**
	 * Returns an ordered range of all the rec form articles where articleStatus = &#63; and articleReleaseSchedule = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>recFormArticleModelImpl</code>.
	 * </p>
	 *
	 * @param articleStatus the article status
	 * @param articleReleaseSchedule the article release schedule
	 * @param start the lower bound of the range of rec form articles
	 * @param end the upper bound of the range of rec form articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching rec form articles
	 */
	public java.util.List<recFormArticle>
		findByarticleReleaseScheduleAndarticleStatus(
			String articleStatus, String articleReleaseSchedule, int start,
			int end,
			com.liferay.portal.kernel.util.OrderByComparator<recFormArticle>
				orderByComparator,
			boolean useFinderCache);

	/**
	 * Returns the first rec form article in the ordered set where articleStatus = &#63; and articleReleaseSchedule = &#63;.
	 *
	 * @param articleStatus the article status
	 * @param articleReleaseSchedule the article release schedule
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching rec form article
	 * @throws NoSuchrecFormArticleException if a matching rec form article could not be found
	 */
	public recFormArticle findByarticleReleaseScheduleAndarticleStatus_First(
			String articleStatus, String articleReleaseSchedule,
			com.liferay.portal.kernel.util.OrderByComparator<recFormArticle>
				orderByComparator)
		throws NoSuchrecFormArticleException;

	/**
	 * Returns the first rec form article in the ordered set where articleStatus = &#63; and articleReleaseSchedule = &#63;.
	 *
	 * @param articleStatus the article status
	 * @param articleReleaseSchedule the article release schedule
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching rec form article, or <code>null</code> if a matching rec form article could not be found
	 */
	public recFormArticle fetchByarticleReleaseScheduleAndarticleStatus_First(
		String articleStatus, String articleReleaseSchedule,
		com.liferay.portal.kernel.util.OrderByComparator<recFormArticle>
			orderByComparator);

	/**
	 * Returns the last rec form article in the ordered set where articleStatus = &#63; and articleReleaseSchedule = &#63;.
	 *
	 * @param articleStatus the article status
	 * @param articleReleaseSchedule the article release schedule
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching rec form article
	 * @throws NoSuchrecFormArticleException if a matching rec form article could not be found
	 */
	public recFormArticle findByarticleReleaseScheduleAndarticleStatus_Last(
			String articleStatus, String articleReleaseSchedule,
			com.liferay.portal.kernel.util.OrderByComparator<recFormArticle>
				orderByComparator)
		throws NoSuchrecFormArticleException;

	/**
	 * Returns the last rec form article in the ordered set where articleStatus = &#63; and articleReleaseSchedule = &#63;.
	 *
	 * @param articleStatus the article status
	 * @param articleReleaseSchedule the article release schedule
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching rec form article, or <code>null</code> if a matching rec form article could not be found
	 */
	public recFormArticle fetchByarticleReleaseScheduleAndarticleStatus_Last(
		String articleStatus, String articleReleaseSchedule,
		com.liferay.portal.kernel.util.OrderByComparator<recFormArticle>
			orderByComparator);

	/**
	 * Returns the rec form articles before and after the current rec form article in the ordered set where articleStatus = &#63; and articleReleaseSchedule = &#63;.
	 *
	 * @param formId the primary key of the current rec form article
	 * @param articleStatus the article status
	 * @param articleReleaseSchedule the article release schedule
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next rec form article
	 * @throws NoSuchrecFormArticleException if a rec form article with the primary key could not be found
	 */
	public recFormArticle[]
			findByarticleReleaseScheduleAndarticleStatus_PrevAndNext(
				long formId, String articleStatus,
				String articleReleaseSchedule,
				com.liferay.portal.kernel.util.OrderByComparator<recFormArticle>
					orderByComparator)
		throws NoSuchrecFormArticleException;

	/**
	 * Removes all the rec form articles where articleStatus = &#63; and articleReleaseSchedule = &#63; from the database.
	 *
	 * @param articleStatus the article status
	 * @param articleReleaseSchedule the article release schedule
	 */
	public void removeByarticleReleaseScheduleAndarticleStatus(
		String articleStatus, String articleReleaseSchedule);

	/**
	 * Returns the number of rec form articles where articleStatus = &#63; and articleReleaseSchedule = &#63;.
	 *
	 * @param articleStatus the article status
	 * @param articleReleaseSchedule the article release schedule
	 * @return the number of matching rec form articles
	 */
	public int countByarticleReleaseScheduleAndarticleStatus(
		String articleStatus, String articleReleaseSchedule);

	/**
	 * Returns all the rec form articles where articleId = &#63; and alternativeFormIds = &#63;.
	 *
	 * @param articleId the article ID
	 * @param alternativeFormIds the alternative form IDs
	 * @return the matching rec form articles
	 */
	public java.util.List<recFormArticle>
		findByalternativeArticleIdAndArticleId(
			long articleId, String alternativeFormIds);

	/**
	 * Returns a range of all the rec form articles where articleId = &#63; and alternativeFormIds = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>recFormArticleModelImpl</code>.
	 * </p>
	 *
	 * @param articleId the article ID
	 * @param alternativeFormIds the alternative form IDs
	 * @param start the lower bound of the range of rec form articles
	 * @param end the upper bound of the range of rec form articles (not inclusive)
	 * @return the range of matching rec form articles
	 */
	public java.util.List<recFormArticle>
		findByalternativeArticleIdAndArticleId(
			long articleId, String alternativeFormIds, int start, int end);

	/**
	 * Returns an ordered range of all the rec form articles where articleId = &#63; and alternativeFormIds = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>recFormArticleModelImpl</code>.
	 * </p>
	 *
	 * @param articleId the article ID
	 * @param alternativeFormIds the alternative form IDs
	 * @param start the lower bound of the range of rec form articles
	 * @param end the upper bound of the range of rec form articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rec form articles
	 */
	public java.util.List<recFormArticle>
		findByalternativeArticleIdAndArticleId(
			long articleId, String alternativeFormIds, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator<recFormArticle>
				orderByComparator);

	/**
	 * Returns an ordered range of all the rec form articles where articleId = &#63; and alternativeFormIds = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>recFormArticleModelImpl</code>.
	 * </p>
	 *
	 * @param articleId the article ID
	 * @param alternativeFormIds the alternative form IDs
	 * @param start the lower bound of the range of rec form articles
	 * @param end the upper bound of the range of rec form articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching rec form articles
	 */
	public java.util.List<recFormArticle>
		findByalternativeArticleIdAndArticleId(
			long articleId, String alternativeFormIds, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator<recFormArticle>
				orderByComparator,
			boolean useFinderCache);

	/**
	 * Returns the first rec form article in the ordered set where articleId = &#63; and alternativeFormIds = &#63;.
	 *
	 * @param articleId the article ID
	 * @param alternativeFormIds the alternative form IDs
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching rec form article
	 * @throws NoSuchrecFormArticleException if a matching rec form article could not be found
	 */
	public recFormArticle findByalternativeArticleIdAndArticleId_First(
			long articleId, String alternativeFormIds,
			com.liferay.portal.kernel.util.OrderByComparator<recFormArticle>
				orderByComparator)
		throws NoSuchrecFormArticleException;

	/**
	 * Returns the first rec form article in the ordered set where articleId = &#63; and alternativeFormIds = &#63;.
	 *
	 * @param articleId the article ID
	 * @param alternativeFormIds the alternative form IDs
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching rec form article, or <code>null</code> if a matching rec form article could not be found
	 */
	public recFormArticle fetchByalternativeArticleIdAndArticleId_First(
		long articleId, String alternativeFormIds,
		com.liferay.portal.kernel.util.OrderByComparator<recFormArticle>
			orderByComparator);

	/**
	 * Returns the last rec form article in the ordered set where articleId = &#63; and alternativeFormIds = &#63;.
	 *
	 * @param articleId the article ID
	 * @param alternativeFormIds the alternative form IDs
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching rec form article
	 * @throws NoSuchrecFormArticleException if a matching rec form article could not be found
	 */
	public recFormArticle findByalternativeArticleIdAndArticleId_Last(
			long articleId, String alternativeFormIds,
			com.liferay.portal.kernel.util.OrderByComparator<recFormArticle>
				orderByComparator)
		throws NoSuchrecFormArticleException;

	/**
	 * Returns the last rec form article in the ordered set where articleId = &#63; and alternativeFormIds = &#63;.
	 *
	 * @param articleId the article ID
	 * @param alternativeFormIds the alternative form IDs
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching rec form article, or <code>null</code> if a matching rec form article could not be found
	 */
	public recFormArticle fetchByalternativeArticleIdAndArticleId_Last(
		long articleId, String alternativeFormIds,
		com.liferay.portal.kernel.util.OrderByComparator<recFormArticle>
			orderByComparator);

	/**
	 * Returns the rec form articles before and after the current rec form article in the ordered set where articleId = &#63; and alternativeFormIds = &#63;.
	 *
	 * @param formId the primary key of the current rec form article
	 * @param articleId the article ID
	 * @param alternativeFormIds the alternative form IDs
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next rec form article
	 * @throws NoSuchrecFormArticleException if a rec form article with the primary key could not be found
	 */
	public recFormArticle[] findByalternativeArticleIdAndArticleId_PrevAndNext(
			long formId, long articleId, String alternativeFormIds,
			com.liferay.portal.kernel.util.OrderByComparator<recFormArticle>
				orderByComparator)
		throws NoSuchrecFormArticleException;

	/**
	 * Removes all the rec form articles where articleId = &#63; and alternativeFormIds = &#63; from the database.
	 *
	 * @param articleId the article ID
	 * @param alternativeFormIds the alternative form IDs
	 */
	public void removeByalternativeArticleIdAndArticleId(
		long articleId, String alternativeFormIds);

	/**
	 * Returns the number of rec form articles where articleId = &#63; and alternativeFormIds = &#63;.
	 *
	 * @param articleId the article ID
	 * @param alternativeFormIds the alternative form IDs
	 * @return the number of matching rec form articles
	 */
	public int countByalternativeArticleIdAndArticleId(
		long articleId, String alternativeFormIds);

	/**
	 * Returns all the rec form articles where articleId = &#63;.
	 *
	 * @param articleId the article ID
	 * @return the matching rec form articles
	 */
	public java.util.List<recFormArticle> findByarticleId(long articleId);

	/**
	 * Returns a range of all the rec form articles where articleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>recFormArticleModelImpl</code>.
	 * </p>
	 *
	 * @param articleId the article ID
	 * @param start the lower bound of the range of rec form articles
	 * @param end the upper bound of the range of rec form articles (not inclusive)
	 * @return the range of matching rec form articles
	 */
	public java.util.List<recFormArticle> findByarticleId(
		long articleId, int start, int end);

	/**
	 * Returns an ordered range of all the rec form articles where articleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>recFormArticleModelImpl</code>.
	 * </p>
	 *
	 * @param articleId the article ID
	 * @param start the lower bound of the range of rec form articles
	 * @param end the upper bound of the range of rec form articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rec form articles
	 */
	public java.util.List<recFormArticle> findByarticleId(
		long articleId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<recFormArticle>
			orderByComparator);

	/**
	 * Returns an ordered range of all the rec form articles where articleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>recFormArticleModelImpl</code>.
	 * </p>
	 *
	 * @param articleId the article ID
	 * @param start the lower bound of the range of rec form articles
	 * @param end the upper bound of the range of rec form articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching rec form articles
	 */
	public java.util.List<recFormArticle> findByarticleId(
		long articleId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<recFormArticle>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first rec form article in the ordered set where articleId = &#63;.
	 *
	 * @param articleId the article ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching rec form article
	 * @throws NoSuchrecFormArticleException if a matching rec form article could not be found
	 */
	public recFormArticle findByarticleId_First(
			long articleId,
			com.liferay.portal.kernel.util.OrderByComparator<recFormArticle>
				orderByComparator)
		throws NoSuchrecFormArticleException;

	/**
	 * Returns the first rec form article in the ordered set where articleId = &#63;.
	 *
	 * @param articleId the article ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching rec form article, or <code>null</code> if a matching rec form article could not be found
	 */
	public recFormArticle fetchByarticleId_First(
		long articleId,
		com.liferay.portal.kernel.util.OrderByComparator<recFormArticle>
			orderByComparator);

	/**
	 * Returns the last rec form article in the ordered set where articleId = &#63;.
	 *
	 * @param articleId the article ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching rec form article
	 * @throws NoSuchrecFormArticleException if a matching rec form article could not be found
	 */
	public recFormArticle findByarticleId_Last(
			long articleId,
			com.liferay.portal.kernel.util.OrderByComparator<recFormArticle>
				orderByComparator)
		throws NoSuchrecFormArticleException;

	/**
	 * Returns the last rec form article in the ordered set where articleId = &#63;.
	 *
	 * @param articleId the article ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching rec form article, or <code>null</code> if a matching rec form article could not be found
	 */
	public recFormArticle fetchByarticleId_Last(
		long articleId,
		com.liferay.portal.kernel.util.OrderByComparator<recFormArticle>
			orderByComparator);

	/**
	 * Returns the rec form articles before and after the current rec form article in the ordered set where articleId = &#63;.
	 *
	 * @param formId the primary key of the current rec form article
	 * @param articleId the article ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next rec form article
	 * @throws NoSuchrecFormArticleException if a rec form article with the primary key could not be found
	 */
	public recFormArticle[] findByarticleId_PrevAndNext(
			long formId, long articleId,
			com.liferay.portal.kernel.util.OrderByComparator<recFormArticle>
				orderByComparator)
		throws NoSuchrecFormArticleException;

	/**
	 * Removes all the rec form articles where articleId = &#63; from the database.
	 *
	 * @param articleId the article ID
	 */
	public void removeByarticleId(long articleId);

	/**
	 * Returns the number of rec form articles where articleId = &#63;.
	 *
	 * @param articleId the article ID
	 * @return the number of matching rec form articles
	 */
	public int countByarticleId(long articleId);

	/**
	 * Caches the rec form article in the entity cache if it is enabled.
	 *
	 * @param recFormArticle the rec form article
	 */
	public void cacheResult(recFormArticle recFormArticle);

	/**
	 * Caches the rec form articles in the entity cache if it is enabled.
	 *
	 * @param recFormArticles the rec form articles
	 */
	public void cacheResult(java.util.List<recFormArticle> recFormArticles);

	/**
	 * Creates a new rec form article with the primary key. Does not add the rec form article to the database.
	 *
	 * @param formId the primary key for the new rec form article
	 * @return the new rec form article
	 */
	public recFormArticle create(long formId);

	/**
	 * Removes the rec form article with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param formId the primary key of the rec form article
	 * @return the rec form article that was removed
	 * @throws NoSuchrecFormArticleException if a rec form article with the primary key could not be found
	 */
	public recFormArticle remove(long formId)
		throws NoSuchrecFormArticleException;

	public recFormArticle updateImpl(recFormArticle recFormArticle);

	/**
	 * Returns the rec form article with the primary key or throws a <code>NoSuchrecFormArticleException</code> if it could not be found.
	 *
	 * @param formId the primary key of the rec form article
	 * @return the rec form article
	 * @throws NoSuchrecFormArticleException if a rec form article with the primary key could not be found
	 */
	public recFormArticle findByPrimaryKey(long formId)
		throws NoSuchrecFormArticleException;

	/**
	 * Returns the rec form article with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param formId the primary key of the rec form article
	 * @return the rec form article, or <code>null</code> if a rec form article with the primary key could not be found
	 */
	public recFormArticle fetchByPrimaryKey(long formId);

	/**
	 * Returns all the rec form articles.
	 *
	 * @return the rec form articles
	 */
	public java.util.List<recFormArticle> findAll();

	/**
	 * Returns a range of all the rec form articles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>recFormArticleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of rec form articles
	 * @param end the upper bound of the range of rec form articles (not inclusive)
	 * @return the range of rec form articles
	 */
	public java.util.List<recFormArticle> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the rec form articles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>recFormArticleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of rec form articles
	 * @param end the upper bound of the range of rec form articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of rec form articles
	 */
	public java.util.List<recFormArticle> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<recFormArticle>
			orderByComparator);

	/**
	 * Returns an ordered range of all the rec form articles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>recFormArticleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of rec form articles
	 * @param end the upper bound of the range of rec form articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of rec form articles
	 */
	public java.util.List<recFormArticle> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<recFormArticle>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the rec form articles from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of rec form articles.
	 *
	 * @return the number of rec form articles
	 */
	public int countAll();

}