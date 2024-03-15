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

import com.everis.cproposal.model.recFormArticle;

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
 * The persistence utility for the rec form article service. This utility wraps <code>com.everis.cproposal.service.persistence.impl.recFormArticlePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see recFormArticlePersistence
 * @generated
 */
public class recFormArticleUtil {

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
	public static void clearCache(recFormArticle recFormArticle) {
		getPersistence().clearCache(recFormArticle);
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
	public static Map<Serializable, recFormArticle> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<recFormArticle> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<recFormArticle> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<recFormArticle> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<recFormArticle> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static recFormArticle update(recFormArticle recFormArticle) {
		return getPersistence().update(recFormArticle);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static recFormArticle update(
		recFormArticle recFormArticle, ServiceContext serviceContext) {

		return getPersistence().update(recFormArticle, serviceContext);
	}

	/**
	 * Returns all the rec form articles where articleStatus = &#63; and articleReleaseSchedule = &#63;.
	 *
	 * @param articleStatus the article status
	 * @param articleReleaseSchedule the article release schedule
	 * @return the matching rec form articles
	 */
	public static List<recFormArticle>
		findByarticleReleaseScheduleAndarticleStatus(
			String articleStatus, String articleReleaseSchedule) {

		return getPersistence().findByarticleReleaseScheduleAndarticleStatus(
			articleStatus, articleReleaseSchedule);
	}

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
	public static List<recFormArticle>
		findByarticleReleaseScheduleAndarticleStatus(
			String articleStatus, String articleReleaseSchedule, int start,
			int end) {

		return getPersistence().findByarticleReleaseScheduleAndarticleStatus(
			articleStatus, articleReleaseSchedule, start, end);
	}

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
	public static List<recFormArticle>
		findByarticleReleaseScheduleAndarticleStatus(
			String articleStatus, String articleReleaseSchedule, int start,
			int end, OrderByComparator<recFormArticle> orderByComparator) {

		return getPersistence().findByarticleReleaseScheduleAndarticleStatus(
			articleStatus, articleReleaseSchedule, start, end,
			orderByComparator);
	}

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
	public static List<recFormArticle>
		findByarticleReleaseScheduleAndarticleStatus(
			String articleStatus, String articleReleaseSchedule, int start,
			int end, OrderByComparator<recFormArticle> orderByComparator,
			boolean useFinderCache) {

		return getPersistence().findByarticleReleaseScheduleAndarticleStatus(
			articleStatus, articleReleaseSchedule, start, end,
			orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first rec form article in the ordered set where articleStatus = &#63; and articleReleaseSchedule = &#63;.
	 *
	 * @param articleStatus the article status
	 * @param articleReleaseSchedule the article release schedule
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching rec form article
	 * @throws NoSuchrecFormArticleException if a matching rec form article could not be found
	 */
	public static recFormArticle
			findByarticleReleaseScheduleAndarticleStatus_First(
				String articleStatus, String articleReleaseSchedule,
				OrderByComparator<recFormArticle> orderByComparator)
		throws com.everis.cproposal.exception.NoSuchrecFormArticleException {

		return getPersistence().
			findByarticleReleaseScheduleAndarticleStatus_First(
				articleStatus, articleReleaseSchedule, orderByComparator);
	}

	/**
	 * Returns the first rec form article in the ordered set where articleStatus = &#63; and articleReleaseSchedule = &#63;.
	 *
	 * @param articleStatus the article status
	 * @param articleReleaseSchedule the article release schedule
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching rec form article, or <code>null</code> if a matching rec form article could not be found
	 */
	public static recFormArticle
		fetchByarticleReleaseScheduleAndarticleStatus_First(
			String articleStatus, String articleReleaseSchedule,
			OrderByComparator<recFormArticle> orderByComparator) {

		return getPersistence().
			fetchByarticleReleaseScheduleAndarticleStatus_First(
				articleStatus, articleReleaseSchedule, orderByComparator);
	}

	/**
	 * Returns the last rec form article in the ordered set where articleStatus = &#63; and articleReleaseSchedule = &#63;.
	 *
	 * @param articleStatus the article status
	 * @param articleReleaseSchedule the article release schedule
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching rec form article
	 * @throws NoSuchrecFormArticleException if a matching rec form article could not be found
	 */
	public static recFormArticle
			findByarticleReleaseScheduleAndarticleStatus_Last(
				String articleStatus, String articleReleaseSchedule,
				OrderByComparator<recFormArticle> orderByComparator)
		throws com.everis.cproposal.exception.NoSuchrecFormArticleException {

		return getPersistence().
			findByarticleReleaseScheduleAndarticleStatus_Last(
				articleStatus, articleReleaseSchedule, orderByComparator);
	}

	/**
	 * Returns the last rec form article in the ordered set where articleStatus = &#63; and articleReleaseSchedule = &#63;.
	 *
	 * @param articleStatus the article status
	 * @param articleReleaseSchedule the article release schedule
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching rec form article, or <code>null</code> if a matching rec form article could not be found
	 */
	public static recFormArticle
		fetchByarticleReleaseScheduleAndarticleStatus_Last(
			String articleStatus, String articleReleaseSchedule,
			OrderByComparator<recFormArticle> orderByComparator) {

		return getPersistence().
			fetchByarticleReleaseScheduleAndarticleStatus_Last(
				articleStatus, articleReleaseSchedule, orderByComparator);
	}

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
	public static recFormArticle[]
			findByarticleReleaseScheduleAndarticleStatus_PrevAndNext(
				long formId, String articleStatus,
				String articleReleaseSchedule,
				OrderByComparator<recFormArticle> orderByComparator)
		throws com.everis.cproposal.exception.NoSuchrecFormArticleException {

		return getPersistence().
			findByarticleReleaseScheduleAndarticleStatus_PrevAndNext(
				formId, articleStatus, articleReleaseSchedule,
				orderByComparator);
	}

	/**
	 * Removes all the rec form articles where articleStatus = &#63; and articleReleaseSchedule = &#63; from the database.
	 *
	 * @param articleStatus the article status
	 * @param articleReleaseSchedule the article release schedule
	 */
	public static void removeByarticleReleaseScheduleAndarticleStatus(
		String articleStatus, String articleReleaseSchedule) {

		getPersistence().removeByarticleReleaseScheduleAndarticleStatus(
			articleStatus, articleReleaseSchedule);
	}

	/**
	 * Returns the number of rec form articles where articleStatus = &#63; and articleReleaseSchedule = &#63;.
	 *
	 * @param articleStatus the article status
	 * @param articleReleaseSchedule the article release schedule
	 * @return the number of matching rec form articles
	 */
	public static int countByarticleReleaseScheduleAndarticleStatus(
		String articleStatus, String articleReleaseSchedule) {

		return getPersistence().countByarticleReleaseScheduleAndarticleStatus(
			articleStatus, articleReleaseSchedule);
	}

	/**
	 * Returns all the rec form articles where articleId = &#63; and alternativeFormIds = &#63;.
	 *
	 * @param articleId the article ID
	 * @param alternativeFormIds the alternative form IDs
	 * @return the matching rec form articles
	 */
	public static List<recFormArticle> findByalternativeArticleIdAndArticleId(
		long articleId, String alternativeFormIds) {

		return getPersistence().findByalternativeArticleIdAndArticleId(
			articleId, alternativeFormIds);
	}

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
	public static List<recFormArticle> findByalternativeArticleIdAndArticleId(
		long articleId, String alternativeFormIds, int start, int end) {

		return getPersistence().findByalternativeArticleIdAndArticleId(
			articleId, alternativeFormIds, start, end);
	}

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
	public static List<recFormArticle> findByalternativeArticleIdAndArticleId(
		long articleId, String alternativeFormIds, int start, int end,
		OrderByComparator<recFormArticle> orderByComparator) {

		return getPersistence().findByalternativeArticleIdAndArticleId(
			articleId, alternativeFormIds, start, end, orderByComparator);
	}

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
	public static List<recFormArticle> findByalternativeArticleIdAndArticleId(
		long articleId, String alternativeFormIds, int start, int end,
		OrderByComparator<recFormArticle> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByalternativeArticleIdAndArticleId(
			articleId, alternativeFormIds, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first rec form article in the ordered set where articleId = &#63; and alternativeFormIds = &#63;.
	 *
	 * @param articleId the article ID
	 * @param alternativeFormIds the alternative form IDs
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching rec form article
	 * @throws NoSuchrecFormArticleException if a matching rec form article could not be found
	 */
	public static recFormArticle findByalternativeArticleIdAndArticleId_First(
			long articleId, String alternativeFormIds,
			OrderByComparator<recFormArticle> orderByComparator)
		throws com.everis.cproposal.exception.NoSuchrecFormArticleException {

		return getPersistence().findByalternativeArticleIdAndArticleId_First(
			articleId, alternativeFormIds, orderByComparator);
	}

	/**
	 * Returns the first rec form article in the ordered set where articleId = &#63; and alternativeFormIds = &#63;.
	 *
	 * @param articleId the article ID
	 * @param alternativeFormIds the alternative form IDs
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching rec form article, or <code>null</code> if a matching rec form article could not be found
	 */
	public static recFormArticle fetchByalternativeArticleIdAndArticleId_First(
		long articleId, String alternativeFormIds,
		OrderByComparator<recFormArticle> orderByComparator) {

		return getPersistence().fetchByalternativeArticleIdAndArticleId_First(
			articleId, alternativeFormIds, orderByComparator);
	}

	/**
	 * Returns the last rec form article in the ordered set where articleId = &#63; and alternativeFormIds = &#63;.
	 *
	 * @param articleId the article ID
	 * @param alternativeFormIds the alternative form IDs
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching rec form article
	 * @throws NoSuchrecFormArticleException if a matching rec form article could not be found
	 */
	public static recFormArticle findByalternativeArticleIdAndArticleId_Last(
			long articleId, String alternativeFormIds,
			OrderByComparator<recFormArticle> orderByComparator)
		throws com.everis.cproposal.exception.NoSuchrecFormArticleException {

		return getPersistence().findByalternativeArticleIdAndArticleId_Last(
			articleId, alternativeFormIds, orderByComparator);
	}

	/**
	 * Returns the last rec form article in the ordered set where articleId = &#63; and alternativeFormIds = &#63;.
	 *
	 * @param articleId the article ID
	 * @param alternativeFormIds the alternative form IDs
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching rec form article, or <code>null</code> if a matching rec form article could not be found
	 */
	public static recFormArticle fetchByalternativeArticleIdAndArticleId_Last(
		long articleId, String alternativeFormIds,
		OrderByComparator<recFormArticle> orderByComparator) {

		return getPersistence().fetchByalternativeArticleIdAndArticleId_Last(
			articleId, alternativeFormIds, orderByComparator);
	}

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
	public static recFormArticle[]
			findByalternativeArticleIdAndArticleId_PrevAndNext(
				long formId, long articleId, String alternativeFormIds,
				OrderByComparator<recFormArticle> orderByComparator)
		throws com.everis.cproposal.exception.NoSuchrecFormArticleException {

		return getPersistence().
			findByalternativeArticleIdAndArticleId_PrevAndNext(
				formId, articleId, alternativeFormIds, orderByComparator);
	}

	/**
	 * Removes all the rec form articles where articleId = &#63; and alternativeFormIds = &#63; from the database.
	 *
	 * @param articleId the article ID
	 * @param alternativeFormIds the alternative form IDs
	 */
	public static void removeByalternativeArticleIdAndArticleId(
		long articleId, String alternativeFormIds) {

		getPersistence().removeByalternativeArticleIdAndArticleId(
			articleId, alternativeFormIds);
	}

	/**
	 * Returns the number of rec form articles where articleId = &#63; and alternativeFormIds = &#63;.
	 *
	 * @param articleId the article ID
	 * @param alternativeFormIds the alternative form IDs
	 * @return the number of matching rec form articles
	 */
	public static int countByalternativeArticleIdAndArticleId(
		long articleId, String alternativeFormIds) {

		return getPersistence().countByalternativeArticleIdAndArticleId(
			articleId, alternativeFormIds);
	}

	/**
	 * Returns all the rec form articles where articleId = &#63;.
	 *
	 * @param articleId the article ID
	 * @return the matching rec form articles
	 */
	public static List<recFormArticle> findByarticleId(long articleId) {
		return getPersistence().findByarticleId(articleId);
	}

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
	public static List<recFormArticle> findByarticleId(
		long articleId, int start, int end) {

		return getPersistence().findByarticleId(articleId, start, end);
	}

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
	public static List<recFormArticle> findByarticleId(
		long articleId, int start, int end,
		OrderByComparator<recFormArticle> orderByComparator) {

		return getPersistence().findByarticleId(
			articleId, start, end, orderByComparator);
	}

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
	public static List<recFormArticle> findByarticleId(
		long articleId, int start, int end,
		OrderByComparator<recFormArticle> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByarticleId(
			articleId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first rec form article in the ordered set where articleId = &#63;.
	 *
	 * @param articleId the article ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching rec form article
	 * @throws NoSuchrecFormArticleException if a matching rec form article could not be found
	 */
	public static recFormArticle findByarticleId_First(
			long articleId, OrderByComparator<recFormArticle> orderByComparator)
		throws com.everis.cproposal.exception.NoSuchrecFormArticleException {

		return getPersistence().findByarticleId_First(
			articleId, orderByComparator);
	}

	/**
	 * Returns the first rec form article in the ordered set where articleId = &#63;.
	 *
	 * @param articleId the article ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching rec form article, or <code>null</code> if a matching rec form article could not be found
	 */
	public static recFormArticle fetchByarticleId_First(
		long articleId, OrderByComparator<recFormArticle> orderByComparator) {

		return getPersistence().fetchByarticleId_First(
			articleId, orderByComparator);
	}

	/**
	 * Returns the last rec form article in the ordered set where articleId = &#63;.
	 *
	 * @param articleId the article ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching rec form article
	 * @throws NoSuchrecFormArticleException if a matching rec form article could not be found
	 */
	public static recFormArticle findByarticleId_Last(
			long articleId, OrderByComparator<recFormArticle> orderByComparator)
		throws com.everis.cproposal.exception.NoSuchrecFormArticleException {

		return getPersistence().findByarticleId_Last(
			articleId, orderByComparator);
	}

	/**
	 * Returns the last rec form article in the ordered set where articleId = &#63;.
	 *
	 * @param articleId the article ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching rec form article, or <code>null</code> if a matching rec form article could not be found
	 */
	public static recFormArticle fetchByarticleId_Last(
		long articleId, OrderByComparator<recFormArticle> orderByComparator) {

		return getPersistence().fetchByarticleId_Last(
			articleId, orderByComparator);
	}

	/**
	 * Returns the rec form articles before and after the current rec form article in the ordered set where articleId = &#63;.
	 *
	 * @param formId the primary key of the current rec form article
	 * @param articleId the article ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next rec form article
	 * @throws NoSuchrecFormArticleException if a rec form article with the primary key could not be found
	 */
	public static recFormArticle[] findByarticleId_PrevAndNext(
			long formId, long articleId,
			OrderByComparator<recFormArticle> orderByComparator)
		throws com.everis.cproposal.exception.NoSuchrecFormArticleException {

		return getPersistence().findByarticleId_PrevAndNext(
			formId, articleId, orderByComparator);
	}

	/**
	 * Removes all the rec form articles where articleId = &#63; from the database.
	 *
	 * @param articleId the article ID
	 */
	public static void removeByarticleId(long articleId) {
		getPersistence().removeByarticleId(articleId);
	}

	/**
	 * Returns the number of rec form articles where articleId = &#63;.
	 *
	 * @param articleId the article ID
	 * @return the number of matching rec form articles
	 */
	public static int countByarticleId(long articleId) {
		return getPersistence().countByarticleId(articleId);
	}

	/**
	 * Caches the rec form article in the entity cache if it is enabled.
	 *
	 * @param recFormArticle the rec form article
	 */
	public static void cacheResult(recFormArticle recFormArticle) {
		getPersistence().cacheResult(recFormArticle);
	}

	/**
	 * Caches the rec form articles in the entity cache if it is enabled.
	 *
	 * @param recFormArticles the rec form articles
	 */
	public static void cacheResult(List<recFormArticle> recFormArticles) {
		getPersistence().cacheResult(recFormArticles);
	}

	/**
	 * Creates a new rec form article with the primary key. Does not add the rec form article to the database.
	 *
	 * @param formId the primary key for the new rec form article
	 * @return the new rec form article
	 */
	public static recFormArticle create(long formId) {
		return getPersistence().create(formId);
	}

	/**
	 * Removes the rec form article with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param formId the primary key of the rec form article
	 * @return the rec form article that was removed
	 * @throws NoSuchrecFormArticleException if a rec form article with the primary key could not be found
	 */
	public static recFormArticle remove(long formId)
		throws com.everis.cproposal.exception.NoSuchrecFormArticleException {

		return getPersistence().remove(formId);
	}

	public static recFormArticle updateImpl(recFormArticle recFormArticle) {
		return getPersistence().updateImpl(recFormArticle);
	}

	/**
	 * Returns the rec form article with the primary key or throws a <code>NoSuchrecFormArticleException</code> if it could not be found.
	 *
	 * @param formId the primary key of the rec form article
	 * @return the rec form article
	 * @throws NoSuchrecFormArticleException if a rec form article with the primary key could not be found
	 */
	public static recFormArticle findByPrimaryKey(long formId)
		throws com.everis.cproposal.exception.NoSuchrecFormArticleException {

		return getPersistence().findByPrimaryKey(formId);
	}

	/**
	 * Returns the rec form article with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param formId the primary key of the rec form article
	 * @return the rec form article, or <code>null</code> if a rec form article with the primary key could not be found
	 */
	public static recFormArticle fetchByPrimaryKey(long formId) {
		return getPersistence().fetchByPrimaryKey(formId);
	}

	/**
	 * Returns all the rec form articles.
	 *
	 * @return the rec form articles
	 */
	public static List<recFormArticle> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<recFormArticle> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<recFormArticle> findAll(
		int start, int end,
		OrderByComparator<recFormArticle> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<recFormArticle> findAll(
		int start, int end, OrderByComparator<recFormArticle> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the rec form articles from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of rec form articles.
	 *
	 * @return the number of rec form articles
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static recFormArticlePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<recFormArticlePersistence, recFormArticlePersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			recFormArticlePersistence.class);

		ServiceTracker<recFormArticlePersistence, recFormArticlePersistence>
			serviceTracker =
				new ServiceTracker
					<recFormArticlePersistence, recFormArticlePersistence>(
						bundle.getBundleContext(),
						recFormArticlePersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}