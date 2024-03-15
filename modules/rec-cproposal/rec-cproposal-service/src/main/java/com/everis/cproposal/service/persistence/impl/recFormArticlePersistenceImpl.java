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

package com.everis.cproposal.service.persistence.impl;

import com.everis.cproposal.exception.NoSuchrecFormArticleException;
import com.everis.cproposal.model.impl.recFormArticleImpl;
import com.everis.cproposal.model.impl.recFormArticleModelImpl;
import com.everis.cproposal.model.recFormArticle;
import com.everis.cproposal.service.persistence.impl.constants.CPROPOSALPersistenceConstants;
import com.everis.cproposal.service.persistence.recFormArticlePersistence;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.ArgumentsResolver;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the rec form article service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = recFormArticlePersistence.class)
public class recFormArticlePersistenceImpl
	extends BasePersistenceImpl<recFormArticle>
	implements recFormArticlePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>recFormArticleUtil</code> to access the rec form article persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		recFormArticleImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath
		_finderPathWithPaginationFindByarticleReleaseScheduleAndarticleStatus;
	private FinderPath
		_finderPathWithoutPaginationFindByarticleReleaseScheduleAndarticleStatus;
	private FinderPath _finderPathCountByarticleReleaseScheduleAndarticleStatus;

	/**
	 * Returns all the rec form articles where articleStatus = &#63; and articleReleaseSchedule = &#63;.
	 *
	 * @param articleStatus the article status
	 * @param articleReleaseSchedule the article release schedule
	 * @return the matching rec form articles
	 */
	@Override
	public List<recFormArticle> findByarticleReleaseScheduleAndarticleStatus(
		String articleStatus, String articleReleaseSchedule) {

		return findByarticleReleaseScheduleAndarticleStatus(
			articleStatus, articleReleaseSchedule, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<recFormArticle> findByarticleReleaseScheduleAndarticleStatus(
		String articleStatus, String articleReleaseSchedule, int start,
		int end) {

		return findByarticleReleaseScheduleAndarticleStatus(
			articleStatus, articleReleaseSchedule, start, end, null);
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
	@Override
	public List<recFormArticle> findByarticleReleaseScheduleAndarticleStatus(
		String articleStatus, String articleReleaseSchedule, int start, int end,
		OrderByComparator<recFormArticle> orderByComparator) {

		return findByarticleReleaseScheduleAndarticleStatus(
			articleStatus, articleReleaseSchedule, start, end,
			orderByComparator, true);
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
	@Override
	public List<recFormArticle> findByarticleReleaseScheduleAndarticleStatus(
		String articleStatus, String articleReleaseSchedule, int start, int end,
		OrderByComparator<recFormArticle> orderByComparator,
		boolean useFinderCache) {

		articleStatus = Objects.toString(articleStatus, "");
		articleReleaseSchedule = Objects.toString(articleReleaseSchedule, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByarticleReleaseScheduleAndarticleStatus;
				finderArgs = new Object[] {
					articleStatus, articleReleaseSchedule
				};
			}
		}
		else if (useFinderCache) {
			finderPath =
				_finderPathWithPaginationFindByarticleReleaseScheduleAndarticleStatus;
			finderArgs = new Object[] {
				articleStatus, articleReleaseSchedule, start, end,
				orderByComparator
			};
		}

		List<recFormArticle> list = null;

		if (useFinderCache) {
			list = (List<recFormArticle>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (recFormArticle recFormArticle : list) {
					if (!articleStatus.equals(
							recFormArticle.getArticleStatus()) ||
						!articleReleaseSchedule.equals(
							recFormArticle.getArticleReleaseSchedule())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(4);
			}

			sb.append(_SQL_SELECT_RECFORMARTICLE_WHERE);

			boolean bindArticleStatus = false;

			if (articleStatus.isEmpty()) {
				sb.append(
					_FINDER_COLUMN_ARTICLERELEASESCHEDULEANDARTICLESTATUS_ARTICLESTATUS_3);
			}
			else {
				bindArticleStatus = true;

				sb.append(
					_FINDER_COLUMN_ARTICLERELEASESCHEDULEANDARTICLESTATUS_ARTICLESTATUS_2);
			}

			boolean bindArticleReleaseSchedule = false;

			if (articleReleaseSchedule.isEmpty()) {
				sb.append(
					_FINDER_COLUMN_ARTICLERELEASESCHEDULEANDARTICLESTATUS_ARTICLERELEASESCHEDULE_3);
			}
			else {
				bindArticleReleaseSchedule = true;

				sb.append(
					_FINDER_COLUMN_ARTICLERELEASESCHEDULEANDARTICLESTATUS_ARTICLERELEASESCHEDULE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(recFormArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindArticleStatus) {
					queryPos.add(articleStatus);
				}

				if (bindArticleReleaseSchedule) {
					queryPos.add(articleReleaseSchedule);
				}

				list = (List<recFormArticle>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public recFormArticle findByarticleReleaseScheduleAndarticleStatus_First(
			String articleStatus, String articleReleaseSchedule,
			OrderByComparator<recFormArticle> orderByComparator)
		throws NoSuchrecFormArticleException {

		recFormArticle recFormArticle =
			fetchByarticleReleaseScheduleAndarticleStatus_First(
				articleStatus, articleReleaseSchedule, orderByComparator);

		if (recFormArticle != null) {
			return recFormArticle;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("articleStatus=");
		sb.append(articleStatus);

		sb.append(", articleReleaseSchedule=");
		sb.append(articleReleaseSchedule);

		sb.append("}");

		throw new NoSuchrecFormArticleException(sb.toString());
	}

	/**
	 * Returns the first rec form article in the ordered set where articleStatus = &#63; and articleReleaseSchedule = &#63;.
	 *
	 * @param articleStatus the article status
	 * @param articleReleaseSchedule the article release schedule
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching rec form article, or <code>null</code> if a matching rec form article could not be found
	 */
	@Override
	public recFormArticle fetchByarticleReleaseScheduleAndarticleStatus_First(
		String articleStatus, String articleReleaseSchedule,
		OrderByComparator<recFormArticle> orderByComparator) {

		List<recFormArticle> list =
			findByarticleReleaseScheduleAndarticleStatus(
				articleStatus, articleReleaseSchedule, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public recFormArticle findByarticleReleaseScheduleAndarticleStatus_Last(
			String articleStatus, String articleReleaseSchedule,
			OrderByComparator<recFormArticle> orderByComparator)
		throws NoSuchrecFormArticleException {

		recFormArticle recFormArticle =
			fetchByarticleReleaseScheduleAndarticleStatus_Last(
				articleStatus, articleReleaseSchedule, orderByComparator);

		if (recFormArticle != null) {
			return recFormArticle;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("articleStatus=");
		sb.append(articleStatus);

		sb.append(", articleReleaseSchedule=");
		sb.append(articleReleaseSchedule);

		sb.append("}");

		throw new NoSuchrecFormArticleException(sb.toString());
	}

	/**
	 * Returns the last rec form article in the ordered set where articleStatus = &#63; and articleReleaseSchedule = &#63;.
	 *
	 * @param articleStatus the article status
	 * @param articleReleaseSchedule the article release schedule
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching rec form article, or <code>null</code> if a matching rec form article could not be found
	 */
	@Override
	public recFormArticle fetchByarticleReleaseScheduleAndarticleStatus_Last(
		String articleStatus, String articleReleaseSchedule,
		OrderByComparator<recFormArticle> orderByComparator) {

		int count = countByarticleReleaseScheduleAndarticleStatus(
			articleStatus, articleReleaseSchedule);

		if (count == 0) {
			return null;
		}

		List<recFormArticle> list =
			findByarticleReleaseScheduleAndarticleStatus(
				articleStatus, articleReleaseSchedule, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public recFormArticle[]
			findByarticleReleaseScheduleAndarticleStatus_PrevAndNext(
				long formId, String articleStatus,
				String articleReleaseSchedule,
				OrderByComparator<recFormArticle> orderByComparator)
		throws NoSuchrecFormArticleException {

		articleStatus = Objects.toString(articleStatus, "");
		articleReleaseSchedule = Objects.toString(articleReleaseSchedule, "");

		recFormArticle recFormArticle = findByPrimaryKey(formId);

		Session session = null;

		try {
			session = openSession();

			recFormArticle[] array = new recFormArticleImpl[3];

			array[0] = getByarticleReleaseScheduleAndarticleStatus_PrevAndNext(
				session, recFormArticle, articleStatus, articleReleaseSchedule,
				orderByComparator, true);

			array[1] = recFormArticle;

			array[2] = getByarticleReleaseScheduleAndarticleStatus_PrevAndNext(
				session, recFormArticle, articleStatus, articleReleaseSchedule,
				orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected recFormArticle
		getByarticleReleaseScheduleAndarticleStatus_PrevAndNext(
			Session session, recFormArticle recFormArticle,
			String articleStatus, String articleReleaseSchedule,
			OrderByComparator<recFormArticle> orderByComparator,
			boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_RECFORMARTICLE_WHERE);

		boolean bindArticleStatus = false;

		if (articleStatus.isEmpty()) {
			sb.append(
				_FINDER_COLUMN_ARTICLERELEASESCHEDULEANDARTICLESTATUS_ARTICLESTATUS_3);
		}
		else {
			bindArticleStatus = true;

			sb.append(
				_FINDER_COLUMN_ARTICLERELEASESCHEDULEANDARTICLESTATUS_ARTICLESTATUS_2);
		}

		boolean bindArticleReleaseSchedule = false;

		if (articleReleaseSchedule.isEmpty()) {
			sb.append(
				_FINDER_COLUMN_ARTICLERELEASESCHEDULEANDARTICLESTATUS_ARTICLERELEASESCHEDULE_3);
		}
		else {
			bindArticleReleaseSchedule = true;

			sb.append(
				_FINDER_COLUMN_ARTICLERELEASESCHEDULEANDARTICLESTATUS_ARTICLERELEASESCHEDULE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(recFormArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindArticleStatus) {
			queryPos.add(articleStatus);
		}

		if (bindArticleReleaseSchedule) {
			queryPos.add(articleReleaseSchedule);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						recFormArticle)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<recFormArticle> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the rec form articles where articleStatus = &#63; and articleReleaseSchedule = &#63; from the database.
	 *
	 * @param articleStatus the article status
	 * @param articleReleaseSchedule the article release schedule
	 */
	@Override
	public void removeByarticleReleaseScheduleAndarticleStatus(
		String articleStatus, String articleReleaseSchedule) {

		for (recFormArticle recFormArticle :
				findByarticleReleaseScheduleAndarticleStatus(
					articleStatus, articleReleaseSchedule, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(recFormArticle);
		}
	}

	/**
	 * Returns the number of rec form articles where articleStatus = &#63; and articleReleaseSchedule = &#63;.
	 *
	 * @param articleStatus the article status
	 * @param articleReleaseSchedule the article release schedule
	 * @return the number of matching rec form articles
	 */
	@Override
	public int countByarticleReleaseScheduleAndarticleStatus(
		String articleStatus, String articleReleaseSchedule) {

		articleStatus = Objects.toString(articleStatus, "");
		articleReleaseSchedule = Objects.toString(articleReleaseSchedule, "");

		FinderPath finderPath =
			_finderPathCountByarticleReleaseScheduleAndarticleStatus;

		Object[] finderArgs = new Object[] {
			articleStatus, articleReleaseSchedule
		};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_RECFORMARTICLE_WHERE);

			boolean bindArticleStatus = false;

			if (articleStatus.isEmpty()) {
				sb.append(
					_FINDER_COLUMN_ARTICLERELEASESCHEDULEANDARTICLESTATUS_ARTICLESTATUS_3);
			}
			else {
				bindArticleStatus = true;

				sb.append(
					_FINDER_COLUMN_ARTICLERELEASESCHEDULEANDARTICLESTATUS_ARTICLESTATUS_2);
			}

			boolean bindArticleReleaseSchedule = false;

			if (articleReleaseSchedule.isEmpty()) {
				sb.append(
					_FINDER_COLUMN_ARTICLERELEASESCHEDULEANDARTICLESTATUS_ARTICLERELEASESCHEDULE_3);
			}
			else {
				bindArticleReleaseSchedule = true;

				sb.append(
					_FINDER_COLUMN_ARTICLERELEASESCHEDULEANDARTICLESTATUS_ARTICLERELEASESCHEDULE_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindArticleStatus) {
					queryPos.add(articleStatus);
				}

				if (bindArticleReleaseSchedule) {
					queryPos.add(articleReleaseSchedule);
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String
		_FINDER_COLUMN_ARTICLERELEASESCHEDULEANDARTICLESTATUS_ARTICLESTATUS_2 =
			"recFormArticle.articleStatus = ? AND ";

	private static final String
		_FINDER_COLUMN_ARTICLERELEASESCHEDULEANDARTICLESTATUS_ARTICLESTATUS_3 =
			"(recFormArticle.articleStatus IS NULL OR recFormArticle.articleStatus = '') AND ";

	private static final String
		_FINDER_COLUMN_ARTICLERELEASESCHEDULEANDARTICLESTATUS_ARTICLERELEASESCHEDULE_2 =
			"recFormArticle.articleReleaseSchedule = ?";

	private static final String
		_FINDER_COLUMN_ARTICLERELEASESCHEDULEANDARTICLESTATUS_ARTICLERELEASESCHEDULE_3 =
			"(recFormArticle.articleReleaseSchedule IS NULL OR recFormArticle.articleReleaseSchedule = '')";

	private FinderPath
		_finderPathWithPaginationFindByalternativeArticleIdAndArticleId;
	private FinderPath
		_finderPathWithoutPaginationFindByalternativeArticleIdAndArticleId;
	private FinderPath _finderPathCountByalternativeArticleIdAndArticleId;

	/**
	 * Returns all the rec form articles where articleId = &#63; and alternativeFormIds = &#63;.
	 *
	 * @param articleId the article ID
	 * @param alternativeFormIds the alternative form IDs
	 * @return the matching rec form articles
	 */
	@Override
	public List<recFormArticle> findByalternativeArticleIdAndArticleId(
		long articleId, String alternativeFormIds) {

		return findByalternativeArticleIdAndArticleId(
			articleId, alternativeFormIds, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
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
	@Override
	public List<recFormArticle> findByalternativeArticleIdAndArticleId(
		long articleId, String alternativeFormIds, int start, int end) {

		return findByalternativeArticleIdAndArticleId(
			articleId, alternativeFormIds, start, end, null);
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
	@Override
	public List<recFormArticle> findByalternativeArticleIdAndArticleId(
		long articleId, String alternativeFormIds, int start, int end,
		OrderByComparator<recFormArticle> orderByComparator) {

		return findByalternativeArticleIdAndArticleId(
			articleId, alternativeFormIds, start, end, orderByComparator, true);
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
	@Override
	public List<recFormArticle> findByalternativeArticleIdAndArticleId(
		long articleId, String alternativeFormIds, int start, int end,
		OrderByComparator<recFormArticle> orderByComparator,
		boolean useFinderCache) {

		alternativeFormIds = Objects.toString(alternativeFormIds, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByalternativeArticleIdAndArticleId;
				finderArgs = new Object[] {articleId, alternativeFormIds};
			}
		}
		else if (useFinderCache) {
			finderPath =
				_finderPathWithPaginationFindByalternativeArticleIdAndArticleId;
			finderArgs = new Object[] {
				articleId, alternativeFormIds, start, end, orderByComparator
			};
		}

		List<recFormArticle> list = null;

		if (useFinderCache) {
			list = (List<recFormArticle>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (recFormArticle recFormArticle : list) {
					if ((articleId != recFormArticle.getArticleId()) ||
						!alternativeFormIds.equals(
							recFormArticle.getAlternativeFormIds())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(4);
			}

			sb.append(_SQL_SELECT_RECFORMARTICLE_WHERE);

			sb.append(
				_FINDER_COLUMN_ALTERNATIVEARTICLEIDANDARTICLEID_ARTICLEID_2);

			boolean bindAlternativeFormIds = false;

			if (alternativeFormIds.isEmpty()) {
				sb.append(
					_FINDER_COLUMN_ALTERNATIVEARTICLEIDANDARTICLEID_ALTERNATIVEFORMIDS_3);
			}
			else {
				bindAlternativeFormIds = true;

				sb.append(
					_FINDER_COLUMN_ALTERNATIVEARTICLEIDANDARTICLEID_ALTERNATIVEFORMIDS_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(recFormArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(articleId);

				if (bindAlternativeFormIds) {
					queryPos.add(alternativeFormIds);
				}

				list = (List<recFormArticle>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public recFormArticle findByalternativeArticleIdAndArticleId_First(
			long articleId, String alternativeFormIds,
			OrderByComparator<recFormArticle> orderByComparator)
		throws NoSuchrecFormArticleException {

		recFormArticle recFormArticle =
			fetchByalternativeArticleIdAndArticleId_First(
				articleId, alternativeFormIds, orderByComparator);

		if (recFormArticle != null) {
			return recFormArticle;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("articleId=");
		sb.append(articleId);

		sb.append(", alternativeFormIds=");
		sb.append(alternativeFormIds);

		sb.append("}");

		throw new NoSuchrecFormArticleException(sb.toString());
	}

	/**
	 * Returns the first rec form article in the ordered set where articleId = &#63; and alternativeFormIds = &#63;.
	 *
	 * @param articleId the article ID
	 * @param alternativeFormIds the alternative form IDs
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching rec form article, or <code>null</code> if a matching rec form article could not be found
	 */
	@Override
	public recFormArticle fetchByalternativeArticleIdAndArticleId_First(
		long articleId, String alternativeFormIds,
		OrderByComparator<recFormArticle> orderByComparator) {

		List<recFormArticle> list = findByalternativeArticleIdAndArticleId(
			articleId, alternativeFormIds, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public recFormArticle findByalternativeArticleIdAndArticleId_Last(
			long articleId, String alternativeFormIds,
			OrderByComparator<recFormArticle> orderByComparator)
		throws NoSuchrecFormArticleException {

		recFormArticle recFormArticle =
			fetchByalternativeArticleIdAndArticleId_Last(
				articleId, alternativeFormIds, orderByComparator);

		if (recFormArticle != null) {
			return recFormArticle;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("articleId=");
		sb.append(articleId);

		sb.append(", alternativeFormIds=");
		sb.append(alternativeFormIds);

		sb.append("}");

		throw new NoSuchrecFormArticleException(sb.toString());
	}

	/**
	 * Returns the last rec form article in the ordered set where articleId = &#63; and alternativeFormIds = &#63;.
	 *
	 * @param articleId the article ID
	 * @param alternativeFormIds the alternative form IDs
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching rec form article, or <code>null</code> if a matching rec form article could not be found
	 */
	@Override
	public recFormArticle fetchByalternativeArticleIdAndArticleId_Last(
		long articleId, String alternativeFormIds,
		OrderByComparator<recFormArticle> orderByComparator) {

		int count = countByalternativeArticleIdAndArticleId(
			articleId, alternativeFormIds);

		if (count == 0) {
			return null;
		}

		List<recFormArticle> list = findByalternativeArticleIdAndArticleId(
			articleId, alternativeFormIds, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public recFormArticle[] findByalternativeArticleIdAndArticleId_PrevAndNext(
			long formId, long articleId, String alternativeFormIds,
			OrderByComparator<recFormArticle> orderByComparator)
		throws NoSuchrecFormArticleException {

		alternativeFormIds = Objects.toString(alternativeFormIds, "");

		recFormArticle recFormArticle = findByPrimaryKey(formId);

		Session session = null;

		try {
			session = openSession();

			recFormArticle[] array = new recFormArticleImpl[3];

			array[0] = getByalternativeArticleIdAndArticleId_PrevAndNext(
				session, recFormArticle, articleId, alternativeFormIds,
				orderByComparator, true);

			array[1] = recFormArticle;

			array[2] = getByalternativeArticleIdAndArticleId_PrevAndNext(
				session, recFormArticle, articleId, alternativeFormIds,
				orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected recFormArticle getByalternativeArticleIdAndArticleId_PrevAndNext(
		Session session, recFormArticle recFormArticle, long articleId,
		String alternativeFormIds,
		OrderByComparator<recFormArticle> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_RECFORMARTICLE_WHERE);

		sb.append(_FINDER_COLUMN_ALTERNATIVEARTICLEIDANDARTICLEID_ARTICLEID_2);

		boolean bindAlternativeFormIds = false;

		if (alternativeFormIds.isEmpty()) {
			sb.append(
				_FINDER_COLUMN_ALTERNATIVEARTICLEIDANDARTICLEID_ALTERNATIVEFORMIDS_3);
		}
		else {
			bindAlternativeFormIds = true;

			sb.append(
				_FINDER_COLUMN_ALTERNATIVEARTICLEIDANDARTICLEID_ALTERNATIVEFORMIDS_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(recFormArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(articleId);

		if (bindAlternativeFormIds) {
			queryPos.add(alternativeFormIds);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						recFormArticle)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<recFormArticle> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the rec form articles where articleId = &#63; and alternativeFormIds = &#63; from the database.
	 *
	 * @param articleId the article ID
	 * @param alternativeFormIds the alternative form IDs
	 */
	@Override
	public void removeByalternativeArticleIdAndArticleId(
		long articleId, String alternativeFormIds) {

		for (recFormArticle recFormArticle :
				findByalternativeArticleIdAndArticleId(
					articleId, alternativeFormIds, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(recFormArticle);
		}
	}

	/**
	 * Returns the number of rec form articles where articleId = &#63; and alternativeFormIds = &#63;.
	 *
	 * @param articleId the article ID
	 * @param alternativeFormIds the alternative form IDs
	 * @return the number of matching rec form articles
	 */
	@Override
	public int countByalternativeArticleIdAndArticleId(
		long articleId, String alternativeFormIds) {

		alternativeFormIds = Objects.toString(alternativeFormIds, "");

		FinderPath finderPath =
			_finderPathCountByalternativeArticleIdAndArticleId;

		Object[] finderArgs = new Object[] {articleId, alternativeFormIds};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_RECFORMARTICLE_WHERE);

			sb.append(
				_FINDER_COLUMN_ALTERNATIVEARTICLEIDANDARTICLEID_ARTICLEID_2);

			boolean bindAlternativeFormIds = false;

			if (alternativeFormIds.isEmpty()) {
				sb.append(
					_FINDER_COLUMN_ALTERNATIVEARTICLEIDANDARTICLEID_ALTERNATIVEFORMIDS_3);
			}
			else {
				bindAlternativeFormIds = true;

				sb.append(
					_FINDER_COLUMN_ALTERNATIVEARTICLEIDANDARTICLEID_ALTERNATIVEFORMIDS_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(articleId);

				if (bindAlternativeFormIds) {
					queryPos.add(alternativeFormIds);
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String
		_FINDER_COLUMN_ALTERNATIVEARTICLEIDANDARTICLEID_ARTICLEID_2 =
			"recFormArticle.articleId = ? AND ";

	private static final String
		_FINDER_COLUMN_ALTERNATIVEARTICLEIDANDARTICLEID_ALTERNATIVEFORMIDS_2 =
			"recFormArticle.alternativeFormIds = ?";

	private static final String
		_FINDER_COLUMN_ALTERNATIVEARTICLEIDANDARTICLEID_ALTERNATIVEFORMIDS_3 =
			"(recFormArticle.alternativeFormIds IS NULL OR recFormArticle.alternativeFormIds = '')";

	private FinderPath _finderPathWithPaginationFindByarticleId;
	private FinderPath _finderPathWithoutPaginationFindByarticleId;
	private FinderPath _finderPathCountByarticleId;

	/**
	 * Returns all the rec form articles where articleId = &#63;.
	 *
	 * @param articleId the article ID
	 * @return the matching rec form articles
	 */
	@Override
	public List<recFormArticle> findByarticleId(long articleId) {
		return findByarticleId(
			articleId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<recFormArticle> findByarticleId(
		long articleId, int start, int end) {

		return findByarticleId(articleId, start, end, null);
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
	@Override
	public List<recFormArticle> findByarticleId(
		long articleId, int start, int end,
		OrderByComparator<recFormArticle> orderByComparator) {

		return findByarticleId(articleId, start, end, orderByComparator, true);
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
	@Override
	public List<recFormArticle> findByarticleId(
		long articleId, int start, int end,
		OrderByComparator<recFormArticle> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByarticleId;
				finderArgs = new Object[] {articleId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByarticleId;
			finderArgs = new Object[] {
				articleId, start, end, orderByComparator
			};
		}

		List<recFormArticle> list = null;

		if (useFinderCache) {
			list = (List<recFormArticle>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (recFormArticle recFormArticle : list) {
					if (articleId != recFormArticle.getArticleId()) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_RECFORMARTICLE_WHERE);

			sb.append(_FINDER_COLUMN_ARTICLEID_ARTICLEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(recFormArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(articleId);

				list = (List<recFormArticle>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first rec form article in the ordered set where articleId = &#63;.
	 *
	 * @param articleId the article ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching rec form article
	 * @throws NoSuchrecFormArticleException if a matching rec form article could not be found
	 */
	@Override
	public recFormArticle findByarticleId_First(
			long articleId, OrderByComparator<recFormArticle> orderByComparator)
		throws NoSuchrecFormArticleException {

		recFormArticle recFormArticle = fetchByarticleId_First(
			articleId, orderByComparator);

		if (recFormArticle != null) {
			return recFormArticle;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("articleId=");
		sb.append(articleId);

		sb.append("}");

		throw new NoSuchrecFormArticleException(sb.toString());
	}

	/**
	 * Returns the first rec form article in the ordered set where articleId = &#63;.
	 *
	 * @param articleId the article ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching rec form article, or <code>null</code> if a matching rec form article could not be found
	 */
	@Override
	public recFormArticle fetchByarticleId_First(
		long articleId, OrderByComparator<recFormArticle> orderByComparator) {

		List<recFormArticle> list = findByarticleId(
			articleId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last rec form article in the ordered set where articleId = &#63;.
	 *
	 * @param articleId the article ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching rec form article
	 * @throws NoSuchrecFormArticleException if a matching rec form article could not be found
	 */
	@Override
	public recFormArticle findByarticleId_Last(
			long articleId, OrderByComparator<recFormArticle> orderByComparator)
		throws NoSuchrecFormArticleException {

		recFormArticle recFormArticle = fetchByarticleId_Last(
			articleId, orderByComparator);

		if (recFormArticle != null) {
			return recFormArticle;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("articleId=");
		sb.append(articleId);

		sb.append("}");

		throw new NoSuchrecFormArticleException(sb.toString());
	}

	/**
	 * Returns the last rec form article in the ordered set where articleId = &#63;.
	 *
	 * @param articleId the article ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching rec form article, or <code>null</code> if a matching rec form article could not be found
	 */
	@Override
	public recFormArticle fetchByarticleId_Last(
		long articleId, OrderByComparator<recFormArticle> orderByComparator) {

		int count = countByarticleId(articleId);

		if (count == 0) {
			return null;
		}

		List<recFormArticle> list = findByarticleId(
			articleId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public recFormArticle[] findByarticleId_PrevAndNext(
			long formId, long articleId,
			OrderByComparator<recFormArticle> orderByComparator)
		throws NoSuchrecFormArticleException {

		recFormArticle recFormArticle = findByPrimaryKey(formId);

		Session session = null;

		try {
			session = openSession();

			recFormArticle[] array = new recFormArticleImpl[3];

			array[0] = getByarticleId_PrevAndNext(
				session, recFormArticle, articleId, orderByComparator, true);

			array[1] = recFormArticle;

			array[2] = getByarticleId_PrevAndNext(
				session, recFormArticle, articleId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected recFormArticle getByarticleId_PrevAndNext(
		Session session, recFormArticle recFormArticle, long articleId,
		OrderByComparator<recFormArticle> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_RECFORMARTICLE_WHERE);

		sb.append(_FINDER_COLUMN_ARTICLEID_ARTICLEID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(recFormArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(articleId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						recFormArticle)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<recFormArticle> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the rec form articles where articleId = &#63; from the database.
	 *
	 * @param articleId the article ID
	 */
	@Override
	public void removeByarticleId(long articleId) {
		for (recFormArticle recFormArticle :
				findByarticleId(
					articleId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(recFormArticle);
		}
	}

	/**
	 * Returns the number of rec form articles where articleId = &#63;.
	 *
	 * @param articleId the article ID
	 * @return the number of matching rec form articles
	 */
	@Override
	public int countByarticleId(long articleId) {
		FinderPath finderPath = _finderPathCountByarticleId;

		Object[] finderArgs = new Object[] {articleId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_RECFORMARTICLE_WHERE);

			sb.append(_FINDER_COLUMN_ARTICLEID_ARTICLEID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(articleId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_ARTICLEID_ARTICLEID_2 =
		"recFormArticle.articleId = ?";

	public recFormArticlePersistenceImpl() {
		setModelClass(recFormArticle.class);

		setModelImplClass(recFormArticleImpl.class);
		setModelPKClass(long.class);
	}

	/**
	 * Caches the rec form article in the entity cache if it is enabled.
	 *
	 * @param recFormArticle the rec form article
	 */
	@Override
	public void cacheResult(recFormArticle recFormArticle) {
		entityCache.putResult(
			recFormArticleImpl.class, recFormArticle.getPrimaryKey(),
			recFormArticle);
	}

	/**
	 * Caches the rec form articles in the entity cache if it is enabled.
	 *
	 * @param recFormArticles the rec form articles
	 */
	@Override
	public void cacheResult(List<recFormArticle> recFormArticles) {
		for (recFormArticle recFormArticle : recFormArticles) {
			if (entityCache.getResult(
					recFormArticleImpl.class, recFormArticle.getPrimaryKey()) ==
						null) {

				cacheResult(recFormArticle);
			}
		}
	}

	/**
	 * Clears the cache for all rec form articles.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(recFormArticleImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the rec form article.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(recFormArticle recFormArticle) {
		entityCache.removeResult(recFormArticleImpl.class, recFormArticle);
	}

	@Override
	public void clearCache(List<recFormArticle> recFormArticles) {
		for (recFormArticle recFormArticle : recFormArticles) {
			entityCache.removeResult(recFormArticleImpl.class, recFormArticle);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(recFormArticleImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new rec form article with the primary key. Does not add the rec form article to the database.
	 *
	 * @param formId the primary key for the new rec form article
	 * @return the new rec form article
	 */
	@Override
	public recFormArticle create(long formId) {
		recFormArticle recFormArticle = new recFormArticleImpl();

		recFormArticle.setNew(true);
		recFormArticle.setPrimaryKey(formId);

		return recFormArticle;
	}

	/**
	 * Removes the rec form article with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param formId the primary key of the rec form article
	 * @return the rec form article that was removed
	 * @throws NoSuchrecFormArticleException if a rec form article with the primary key could not be found
	 */
	@Override
	public recFormArticle remove(long formId)
		throws NoSuchrecFormArticleException {

		return remove((Serializable)formId);
	}

	/**
	 * Removes the rec form article with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the rec form article
	 * @return the rec form article that was removed
	 * @throws NoSuchrecFormArticleException if a rec form article with the primary key could not be found
	 */
	@Override
	public recFormArticle remove(Serializable primaryKey)
		throws NoSuchrecFormArticleException {

		Session session = null;

		try {
			session = openSession();

			recFormArticle recFormArticle = (recFormArticle)session.get(
				recFormArticleImpl.class, primaryKey);

			if (recFormArticle == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchrecFormArticleException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(recFormArticle);
		}
		catch (NoSuchrecFormArticleException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected recFormArticle removeImpl(recFormArticle recFormArticle) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(recFormArticle)) {
				recFormArticle = (recFormArticle)session.get(
					recFormArticleImpl.class,
					recFormArticle.getPrimaryKeyObj());
			}

			if (recFormArticle != null) {
				session.delete(recFormArticle);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (recFormArticle != null) {
			clearCache(recFormArticle);
		}

		return recFormArticle;
	}

	@Override
	public recFormArticle updateImpl(recFormArticle recFormArticle) {
		boolean isNew = recFormArticle.isNew();

		if (!(recFormArticle instanceof recFormArticleModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(recFormArticle.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					recFormArticle);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in recFormArticle proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom recFormArticle implementation " +
					recFormArticle.getClass());
		}

		recFormArticleModelImpl recFormArticleModelImpl =
			(recFormArticleModelImpl)recFormArticle;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(recFormArticle);
			}
			else {
				recFormArticle = (recFormArticle)session.merge(recFormArticle);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			recFormArticleImpl.class, recFormArticleModelImpl, false, true);

		if (isNew) {
			recFormArticle.setNew(false);
		}

		recFormArticle.resetOriginalValues();

		return recFormArticle;
	}

	/**
	 * Returns the rec form article with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the rec form article
	 * @return the rec form article
	 * @throws NoSuchrecFormArticleException if a rec form article with the primary key could not be found
	 */
	@Override
	public recFormArticle findByPrimaryKey(Serializable primaryKey)
		throws NoSuchrecFormArticleException {

		recFormArticle recFormArticle = fetchByPrimaryKey(primaryKey);

		if (recFormArticle == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchrecFormArticleException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return recFormArticle;
	}

	/**
	 * Returns the rec form article with the primary key or throws a <code>NoSuchrecFormArticleException</code> if it could not be found.
	 *
	 * @param formId the primary key of the rec form article
	 * @return the rec form article
	 * @throws NoSuchrecFormArticleException if a rec form article with the primary key could not be found
	 */
	@Override
	public recFormArticle findByPrimaryKey(long formId)
		throws NoSuchrecFormArticleException {

		return findByPrimaryKey((Serializable)formId);
	}

	/**
	 * Returns the rec form article with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param formId the primary key of the rec form article
	 * @return the rec form article, or <code>null</code> if a rec form article with the primary key could not be found
	 */
	@Override
	public recFormArticle fetchByPrimaryKey(long formId) {
		return fetchByPrimaryKey((Serializable)formId);
	}

	/**
	 * Returns all the rec form articles.
	 *
	 * @return the rec form articles
	 */
	@Override
	public List<recFormArticle> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<recFormArticle> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<recFormArticle> findAll(
		int start, int end,
		OrderByComparator<recFormArticle> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<recFormArticle> findAll(
		int start, int end, OrderByComparator<recFormArticle> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<recFormArticle> list = null;

		if (useFinderCache) {
			list = (List<recFormArticle>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_RECFORMARTICLE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_RECFORMARTICLE;

				sql = sql.concat(recFormArticleModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<recFormArticle>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the rec form articles from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (recFormArticle recFormArticle : findAll()) {
			remove(recFormArticle);
		}
	}

	/**
	 * Returns the number of rec form articles.
	 *
	 * @return the number of rec form articles
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_RECFORMARTICLE);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "formId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_RECFORMARTICLE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return recFormArticleModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the rec form article persistence.
	 */
	@Activate
	public void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		_argumentsResolverServiceRegistration = _bundleContext.registerService(
			ArgumentsResolver.class, new recFormArticleModelArgumentsResolver(),
			MapUtil.singletonDictionary(
				"model.class.name", recFormArticle.class.getName()));

		_finderPathWithPaginationFindAll = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathWithoutPaginationFindAll = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathCountAll = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0], new String[0], false);

		_finderPathWithPaginationFindByarticleReleaseScheduleAndarticleStatus =
			_createFinderPath(
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
				"findByarticleReleaseScheduleAndarticleStatus",
				new String[] {
					String.class.getName(), String.class.getName(),
					Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				},
				new String[] {"articleStatus", "articleReleaseSchedule"}, true);

		_finderPathWithoutPaginationFindByarticleReleaseScheduleAndarticleStatus =
			_createFinderPath(
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"findByarticleReleaseScheduleAndarticleStatus",
				new String[] {String.class.getName(), String.class.getName()},
				new String[] {"articleStatus", "articleReleaseSchedule"}, true);

		_finderPathCountByarticleReleaseScheduleAndarticleStatus =
			_createFinderPath(
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"countByarticleReleaseScheduleAndarticleStatus",
				new String[] {String.class.getName(), String.class.getName()},
				new String[] {"articleStatus", "articleReleaseSchedule"},
				false);

		_finderPathWithPaginationFindByalternativeArticleIdAndArticleId =
			_createFinderPath(
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
				"findByalternativeArticleIdAndArticleId",
				new String[] {
					Long.class.getName(), String.class.getName(),
					Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				},
				new String[] {"articleId", "alternativeFormIds"}, true);

		_finderPathWithoutPaginationFindByalternativeArticleIdAndArticleId =
			_createFinderPath(
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"findByalternativeArticleIdAndArticleId",
				new String[] {Long.class.getName(), String.class.getName()},
				new String[] {"articleId", "alternativeFormIds"}, true);

		_finderPathCountByalternativeArticleIdAndArticleId = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByalternativeArticleIdAndArticleId",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"articleId", "alternativeFormIds"}, false);

		_finderPathWithPaginationFindByarticleId = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByarticleId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"articleId"}, true);

		_finderPathWithoutPaginationFindByarticleId = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByarticleId",
			new String[] {Long.class.getName()}, new String[] {"articleId"},
			true);

		_finderPathCountByarticleId = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByarticleId",
			new String[] {Long.class.getName()}, new String[] {"articleId"},
			false);
	}

	@Deactivate
	public void deactivate() {
		entityCache.removeCache(recFormArticleImpl.class.getName());

		_argumentsResolverServiceRegistration.unregister();

		for (ServiceRegistration<FinderPath> serviceRegistration :
				_serviceRegistrations) {

			serviceRegistration.unregister();
		}
	}

	@Override
	@Reference(
		target = CPROPOSALPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = CPROPOSALPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = CPROPOSALPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	private BundleContext _bundleContext;

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_RECFORMARTICLE =
		"SELECT recFormArticle FROM recFormArticle recFormArticle";

	private static final String _SQL_SELECT_RECFORMARTICLE_WHERE =
		"SELECT recFormArticle FROM recFormArticle recFormArticle WHERE ";

	private static final String _SQL_COUNT_RECFORMARTICLE =
		"SELECT COUNT(recFormArticle) FROM recFormArticle recFormArticle";

	private static final String _SQL_COUNT_RECFORMARTICLE_WHERE =
		"SELECT COUNT(recFormArticle) FROM recFormArticle recFormArticle WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "recFormArticle.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No recFormArticle exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No recFormArticle exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		recFormArticlePersistenceImpl.class);

	static {
		try {
			Class.forName(CPROPOSALPersistenceConstants.class.getName());
		}
		catch (ClassNotFoundException classNotFoundException) {
			throw new ExceptionInInitializerError(classNotFoundException);
		}
	}

	private FinderPath _createFinderPath(
		String cacheName, String methodName, String[] params,
		String[] columnNames, boolean baseModelResult) {

		FinderPath finderPath = new FinderPath(
			cacheName, methodName, params, columnNames, baseModelResult);

		if (!cacheName.equals(FINDER_CLASS_NAME_LIST_WITH_PAGINATION)) {
			_serviceRegistrations.add(
				_bundleContext.registerService(
					FinderPath.class, finderPath,
					MapUtil.singletonDictionary("cache.name", cacheName)));
		}

		return finderPath;
	}

	private Set<ServiceRegistration<FinderPath>> _serviceRegistrations =
		new HashSet<>();
	private ServiceRegistration<ArgumentsResolver>
		_argumentsResolverServiceRegistration;

	private static class recFormArticleModelArgumentsResolver
		implements ArgumentsResolver {

		@Override
		public Object[] getArguments(
			FinderPath finderPath, BaseModel<?> baseModel, boolean checkColumn,
			boolean original) {

			String[] columnNames = finderPath.getColumnNames();

			if ((columnNames == null) || (columnNames.length == 0)) {
				if (baseModel.isNew()) {
					return FINDER_ARGS_EMPTY;
				}

				return null;
			}

			recFormArticleModelImpl recFormArticleModelImpl =
				(recFormArticleModelImpl)baseModel;

			long columnBitmask = recFormArticleModelImpl.getColumnBitmask();

			if (!checkColumn || (columnBitmask == 0)) {
				return _getValue(
					recFormArticleModelImpl, columnNames, original);
			}

			Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
				finderPath);

			if (finderPathColumnBitmask == null) {
				finderPathColumnBitmask = 0L;

				for (String columnName : columnNames) {
					finderPathColumnBitmask |=
						recFormArticleModelImpl.getColumnBitmask(columnName);
				}

				_finderPathColumnBitmasksCache.put(
					finderPath, finderPathColumnBitmask);
			}

			if ((columnBitmask & finderPathColumnBitmask) != 0) {
				return _getValue(
					recFormArticleModelImpl, columnNames, original);
			}

			return null;
		}

		private Object[] _getValue(
			recFormArticleModelImpl recFormArticleModelImpl,
			String[] columnNames, boolean original) {

			Object[] arguments = new Object[columnNames.length];

			for (int i = 0; i < arguments.length; i++) {
				String columnName = columnNames[i];

				if (original) {
					arguments[i] =
						recFormArticleModelImpl.getColumnOriginalValue(
							columnName);
				}
				else {
					arguments[i] = recFormArticleModelImpl.getColumnValue(
						columnName);
				}
			}

			return arguments;
		}

		private static Map<FinderPath, Long> _finderPathColumnBitmasksCache =
			new ConcurrentHashMap<>();

	}

}