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

package com.everis.rec.cpimpact.service.persistence.impl;

import com.everis.rec.cpimpact.exception.NoSuchCPImpactException;
import com.everis.rec.cpimpact.model.CPImpact;
import com.everis.rec.cpimpact.model.impl.CPImpactImpl;
import com.everis.rec.cpimpact.model.impl.CPImpactModelImpl;
import com.everis.rec.cpimpact.service.persistence.CPImpactPersistence;
import com.everis.rec.cpimpact.service.persistence.impl.constants.CPIMPACTPersistenceConstants;

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
 * The persistence implementation for the cp impact service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = CPImpactPersistence.class)
public class CPImpactPersistenceImpl
	extends BasePersistenceImpl<CPImpact> implements CPImpactPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CPImpactUtil</code> to access the cp impact persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CPImpactImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByImpactCategory;
	private FinderPath _finderPathWithoutPaginationFindByImpactCategory;
	private FinderPath _finderPathCountByImpactCategory;

	/**
	 * Returns all the cp impacts where impactCategory = &#63;.
	 *
	 * @param impactCategory the impact category
	 * @return the matching cp impacts
	 */
	@Override
	public List<CPImpact> findByImpactCategory(String impactCategory) {
		return findByImpactCategory(
			impactCategory, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp impacts where impactCategory = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CPImpactModelImpl</code>.
	 * </p>
	 *
	 * @param impactCategory the impact category
	 * @param start the lower bound of the range of cp impacts
	 * @param end the upper bound of the range of cp impacts (not inclusive)
	 * @return the range of matching cp impacts
	 */
	@Override
	public List<CPImpact> findByImpactCategory(
		String impactCategory, int start, int end) {

		return findByImpactCategory(impactCategory, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp impacts where impactCategory = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CPImpactModelImpl</code>.
	 * </p>
	 *
	 * @param impactCategory the impact category
	 * @param start the lower bound of the range of cp impacts
	 * @param end the upper bound of the range of cp impacts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp impacts
	 */
	@Override
	public List<CPImpact> findByImpactCategory(
		String impactCategory, int start, int end,
		OrderByComparator<CPImpact> orderByComparator) {

		return findByImpactCategory(
			impactCategory, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp impacts where impactCategory = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CPImpactModelImpl</code>.
	 * </p>
	 *
	 * @param impactCategory the impact category
	 * @param start the lower bound of the range of cp impacts
	 * @param end the upper bound of the range of cp impacts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching cp impacts
	 */
	@Override
	public List<CPImpact> findByImpactCategory(
		String impactCategory, int start, int end,
		OrderByComparator<CPImpact> orderByComparator, boolean useFinderCache) {

		impactCategory = Objects.toString(impactCategory, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByImpactCategory;
				finderArgs = new Object[] {impactCategory};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByImpactCategory;
			finderArgs = new Object[] {
				impactCategory, start, end, orderByComparator
			};
		}

		List<CPImpact> list = null;

		if (useFinderCache) {
			list = (List<CPImpact>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CPImpact cpImpact : list) {
					if (!impactCategory.equals(cpImpact.getImpactCategory())) {
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

			sb.append(_SQL_SELECT_CPIMPACT_WHERE);

			boolean bindImpactCategory = false;

			if (impactCategory.isEmpty()) {
				sb.append(_FINDER_COLUMN_IMPACTCATEGORY_IMPACTCATEGORY_3);
			}
			else {
				bindImpactCategory = true;

				sb.append(_FINDER_COLUMN_IMPACTCATEGORY_IMPACTCATEGORY_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(CPImpactModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindImpactCategory) {
					queryPos.add(impactCategory);
				}

				list = (List<CPImpact>)QueryUtil.list(
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
	 * Returns the first cp impact in the ordered set where impactCategory = &#63;.
	 *
	 * @param impactCategory the impact category
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp impact
	 * @throws NoSuchCPImpactException if a matching cp impact could not be found
	 */
	@Override
	public CPImpact findByImpactCategory_First(
			String impactCategory,
			OrderByComparator<CPImpact> orderByComparator)
		throws NoSuchCPImpactException {

		CPImpact cpImpact = fetchByImpactCategory_First(
			impactCategory, orderByComparator);

		if (cpImpact != null) {
			return cpImpact;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("impactCategory=");
		sb.append(impactCategory);

		sb.append("}");

		throw new NoSuchCPImpactException(sb.toString());
	}

	/**
	 * Returns the first cp impact in the ordered set where impactCategory = &#63;.
	 *
	 * @param impactCategory the impact category
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp impact, or <code>null</code> if a matching cp impact could not be found
	 */
	@Override
	public CPImpact fetchByImpactCategory_First(
		String impactCategory, OrderByComparator<CPImpact> orderByComparator) {

		List<CPImpact> list = findByImpactCategory(
			impactCategory, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cp impact in the ordered set where impactCategory = &#63;.
	 *
	 * @param impactCategory the impact category
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp impact
	 * @throws NoSuchCPImpactException if a matching cp impact could not be found
	 */
	@Override
	public CPImpact findByImpactCategory_Last(
			String impactCategory,
			OrderByComparator<CPImpact> orderByComparator)
		throws NoSuchCPImpactException {

		CPImpact cpImpact = fetchByImpactCategory_Last(
			impactCategory, orderByComparator);

		if (cpImpact != null) {
			return cpImpact;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("impactCategory=");
		sb.append(impactCategory);

		sb.append("}");

		throw new NoSuchCPImpactException(sb.toString());
	}

	/**
	 * Returns the last cp impact in the ordered set where impactCategory = &#63;.
	 *
	 * @param impactCategory the impact category
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp impact, or <code>null</code> if a matching cp impact could not be found
	 */
	@Override
	public CPImpact fetchByImpactCategory_Last(
		String impactCategory, OrderByComparator<CPImpact> orderByComparator) {

		int count = countByImpactCategory(impactCategory);

		if (count == 0) {
			return null;
		}

		List<CPImpact> list = findByImpactCategory(
			impactCategory, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cp impacts before and after the current cp impact in the ordered set where impactCategory = &#63;.
	 *
	 * @param impactId the primary key of the current cp impact
	 * @param impactCategory the impact category
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp impact
	 * @throws NoSuchCPImpactException if a cp impact with the primary key could not be found
	 */
	@Override
	public CPImpact[] findByImpactCategory_PrevAndNext(
			long impactId, String impactCategory,
			OrderByComparator<CPImpact> orderByComparator)
		throws NoSuchCPImpactException {

		impactCategory = Objects.toString(impactCategory, "");

		CPImpact cpImpact = findByPrimaryKey(impactId);

		Session session = null;

		try {
			session = openSession();

			CPImpact[] array = new CPImpactImpl[3];

			array[0] = getByImpactCategory_PrevAndNext(
				session, cpImpact, impactCategory, orderByComparator, true);

			array[1] = cpImpact;

			array[2] = getByImpactCategory_PrevAndNext(
				session, cpImpact, impactCategory, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected CPImpact getByImpactCategory_PrevAndNext(
		Session session, CPImpact cpImpact, String impactCategory,
		OrderByComparator<CPImpact> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_CPIMPACT_WHERE);

		boolean bindImpactCategory = false;

		if (impactCategory.isEmpty()) {
			sb.append(_FINDER_COLUMN_IMPACTCATEGORY_IMPACTCATEGORY_3);
		}
		else {
			bindImpactCategory = true;

			sb.append(_FINDER_COLUMN_IMPACTCATEGORY_IMPACTCATEGORY_2);
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
			sb.append(CPImpactModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindImpactCategory) {
			queryPos.add(impactCategory);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(cpImpact)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<CPImpact> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cp impacts where impactCategory = &#63; from the database.
	 *
	 * @param impactCategory the impact category
	 */
	@Override
	public void removeByImpactCategory(String impactCategory) {
		for (CPImpact cpImpact :
				findByImpactCategory(
					impactCategory, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(cpImpact);
		}
	}

	/**
	 * Returns the number of cp impacts where impactCategory = &#63;.
	 *
	 * @param impactCategory the impact category
	 * @return the number of matching cp impacts
	 */
	@Override
	public int countByImpactCategory(String impactCategory) {
		impactCategory = Objects.toString(impactCategory, "");

		FinderPath finderPath = _finderPathCountByImpactCategory;

		Object[] finderArgs = new Object[] {impactCategory};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_CPIMPACT_WHERE);

			boolean bindImpactCategory = false;

			if (impactCategory.isEmpty()) {
				sb.append(_FINDER_COLUMN_IMPACTCATEGORY_IMPACTCATEGORY_3);
			}
			else {
				bindImpactCategory = true;

				sb.append(_FINDER_COLUMN_IMPACTCATEGORY_IMPACTCATEGORY_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindImpactCategory) {
					queryPos.add(impactCategory);
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

	private static final String _FINDER_COLUMN_IMPACTCATEGORY_IMPACTCATEGORY_2 =
		"cpImpact.impactCategory = ?";

	private static final String _FINDER_COLUMN_IMPACTCATEGORY_IMPACTCATEGORY_3 =
		"(cpImpact.impactCategory IS NULL OR cpImpact.impactCategory = '')";

	public CPImpactPersistenceImpl() {
		setModelClass(CPImpact.class);

		setModelImplClass(CPImpactImpl.class);
		setModelPKClass(long.class);
	}

	/**
	 * Caches the cp impact in the entity cache if it is enabled.
	 *
	 * @param cpImpact the cp impact
	 */
	@Override
	public void cacheResult(CPImpact cpImpact) {
		entityCache.putResult(
			CPImpactImpl.class, cpImpact.getPrimaryKey(), cpImpact);
	}

	/**
	 * Caches the cp impacts in the entity cache if it is enabled.
	 *
	 * @param cpImpacts the cp impacts
	 */
	@Override
	public void cacheResult(List<CPImpact> cpImpacts) {
		for (CPImpact cpImpact : cpImpacts) {
			if (entityCache.getResult(
					CPImpactImpl.class, cpImpact.getPrimaryKey()) == null) {

				cacheResult(cpImpact);
			}
		}
	}

	/**
	 * Clears the cache for all cp impacts.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CPImpactImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the cp impact.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CPImpact cpImpact) {
		entityCache.removeResult(CPImpactImpl.class, cpImpact);
	}

	@Override
	public void clearCache(List<CPImpact> cpImpacts) {
		for (CPImpact cpImpact : cpImpacts) {
			entityCache.removeResult(CPImpactImpl.class, cpImpact);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(CPImpactImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new cp impact with the primary key. Does not add the cp impact to the database.
	 *
	 * @param impactId the primary key for the new cp impact
	 * @return the new cp impact
	 */
	@Override
	public CPImpact create(long impactId) {
		CPImpact cpImpact = new CPImpactImpl();

		cpImpact.setNew(true);
		cpImpact.setPrimaryKey(impactId);

		return cpImpact;
	}

	/**
	 * Removes the cp impact with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param impactId the primary key of the cp impact
	 * @return the cp impact that was removed
	 * @throws NoSuchCPImpactException if a cp impact with the primary key could not be found
	 */
	@Override
	public CPImpact remove(long impactId) throws NoSuchCPImpactException {
		return remove((Serializable)impactId);
	}

	/**
	 * Removes the cp impact with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the cp impact
	 * @return the cp impact that was removed
	 * @throws NoSuchCPImpactException if a cp impact with the primary key could not be found
	 */
	@Override
	public CPImpact remove(Serializable primaryKey)
		throws NoSuchCPImpactException {

		Session session = null;

		try {
			session = openSession();

			CPImpact cpImpact = (CPImpact)session.get(
				CPImpactImpl.class, primaryKey);

			if (cpImpact == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCPImpactException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(cpImpact);
		}
		catch (NoSuchCPImpactException noSuchEntityException) {
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
	protected CPImpact removeImpl(CPImpact cpImpact) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(cpImpact)) {
				cpImpact = (CPImpact)session.get(
					CPImpactImpl.class, cpImpact.getPrimaryKeyObj());
			}

			if (cpImpact != null) {
				session.delete(cpImpact);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (cpImpact != null) {
			clearCache(cpImpact);
		}

		return cpImpact;
	}

	@Override
	public CPImpact updateImpl(CPImpact cpImpact) {
		boolean isNew = cpImpact.isNew();

		if (!(cpImpact instanceof CPImpactModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(cpImpact.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(cpImpact);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in cpImpact proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CPImpact implementation " +
					cpImpact.getClass());
		}

		CPImpactModelImpl cpImpactModelImpl = (CPImpactModelImpl)cpImpact;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(cpImpact);
			}
			else {
				cpImpact = (CPImpact)session.merge(cpImpact);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			CPImpactImpl.class, cpImpactModelImpl, false, true);

		if (isNew) {
			cpImpact.setNew(false);
		}

		cpImpact.resetOriginalValues();

		return cpImpact;
	}

	/**
	 * Returns the cp impact with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the cp impact
	 * @return the cp impact
	 * @throws NoSuchCPImpactException if a cp impact with the primary key could not be found
	 */
	@Override
	public CPImpact findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCPImpactException {

		CPImpact cpImpact = fetchByPrimaryKey(primaryKey);

		if (cpImpact == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCPImpactException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return cpImpact;
	}

	/**
	 * Returns the cp impact with the primary key or throws a <code>NoSuchCPImpactException</code> if it could not be found.
	 *
	 * @param impactId the primary key of the cp impact
	 * @return the cp impact
	 * @throws NoSuchCPImpactException if a cp impact with the primary key could not be found
	 */
	@Override
	public CPImpact findByPrimaryKey(long impactId)
		throws NoSuchCPImpactException {

		return findByPrimaryKey((Serializable)impactId);
	}

	/**
	 * Returns the cp impact with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param impactId the primary key of the cp impact
	 * @return the cp impact, or <code>null</code> if a cp impact with the primary key could not be found
	 */
	@Override
	public CPImpact fetchByPrimaryKey(long impactId) {
		return fetchByPrimaryKey((Serializable)impactId);
	}

	/**
	 * Returns all the cp impacts.
	 *
	 * @return the cp impacts
	 */
	@Override
	public List<CPImpact> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cp impacts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CPImpactModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp impacts
	 * @param end the upper bound of the range of cp impacts (not inclusive)
	 * @return the range of cp impacts
	 */
	@Override
	public List<CPImpact> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the cp impacts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CPImpactModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp impacts
	 * @param end the upper bound of the range of cp impacts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cp impacts
	 */
	@Override
	public List<CPImpact> findAll(
		int start, int end, OrderByComparator<CPImpact> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cp impacts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CPImpactModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp impacts
	 * @param end the upper bound of the range of cp impacts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of cp impacts
	 */
	@Override
	public List<CPImpact> findAll(
		int start, int end, OrderByComparator<CPImpact> orderByComparator,
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

		List<CPImpact> list = null;

		if (useFinderCache) {
			list = (List<CPImpact>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_CPIMPACT);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_CPIMPACT;

				sql = sql.concat(CPImpactModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<CPImpact>)QueryUtil.list(
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
	 * Removes all the cp impacts from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CPImpact cpImpact : findAll()) {
			remove(cpImpact);
		}
	}

	/**
	 * Returns the number of cp impacts.
	 *
	 * @return the number of cp impacts
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_CPIMPACT);

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
		return "impactId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_CPIMPACT;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return CPImpactModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the cp impact persistence.
	 */
	@Activate
	public void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		_argumentsResolverServiceRegistration = _bundleContext.registerService(
			ArgumentsResolver.class, new CPImpactModelArgumentsResolver(),
			MapUtil.singletonDictionary(
				"model.class.name", CPImpact.class.getName()));

		_finderPathWithPaginationFindAll = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathWithoutPaginationFindAll = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathCountAll = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0], new String[0], false);

		_finderPathWithPaginationFindByImpactCategory = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByImpactCategory",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"impactCategory"}, true);

		_finderPathWithoutPaginationFindByImpactCategory = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByImpactCategory",
			new String[] {String.class.getName()},
			new String[] {"impactCategory"}, true);

		_finderPathCountByImpactCategory = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByImpactCategory",
			new String[] {String.class.getName()},
			new String[] {"impactCategory"}, false);
	}

	@Deactivate
	public void deactivate() {
		entityCache.removeCache(CPImpactImpl.class.getName());

		_argumentsResolverServiceRegistration.unregister();

		for (ServiceRegistration<FinderPath> serviceRegistration :
				_serviceRegistrations) {

			serviceRegistration.unregister();
		}
	}

	@Override
	@Reference(
		target = CPIMPACTPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = CPIMPACTPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = CPIMPACTPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
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

	private static final String _SQL_SELECT_CPIMPACT =
		"SELECT cpImpact FROM CPImpact cpImpact";

	private static final String _SQL_SELECT_CPIMPACT_WHERE =
		"SELECT cpImpact FROM CPImpact cpImpact WHERE ";

	private static final String _SQL_COUNT_CPIMPACT =
		"SELECT COUNT(cpImpact) FROM CPImpact cpImpact";

	private static final String _SQL_COUNT_CPIMPACT_WHERE =
		"SELECT COUNT(cpImpact) FROM CPImpact cpImpact WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "cpImpact.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CPImpact exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CPImpact exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CPImpactPersistenceImpl.class);

	static {
		try {
			Class.forName(CPIMPACTPersistenceConstants.class.getName());
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

	private static class CPImpactModelArgumentsResolver
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

			CPImpactModelImpl cpImpactModelImpl = (CPImpactModelImpl)baseModel;

			long columnBitmask = cpImpactModelImpl.getColumnBitmask();

			if (!checkColumn || (columnBitmask == 0)) {
				return _getValue(cpImpactModelImpl, columnNames, original);
			}

			Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
				finderPath);

			if (finderPathColumnBitmask == null) {
				finderPathColumnBitmask = 0L;

				for (String columnName : columnNames) {
					finderPathColumnBitmask |=
						cpImpactModelImpl.getColumnBitmask(columnName);
				}

				_finderPathColumnBitmasksCache.put(
					finderPath, finderPathColumnBitmask);
			}

			if ((columnBitmask & finderPathColumnBitmask) != 0) {
				return _getValue(cpImpactModelImpl, columnNames, original);
			}

			return null;
		}

		private Object[] _getValue(
			CPImpactModelImpl cpImpactModelImpl, String[] columnNames,
			boolean original) {

			Object[] arguments = new Object[columnNames.length];

			for (int i = 0; i < arguments.length; i++) {
				String columnName = columnNames[i];

				if (original) {
					arguments[i] = cpImpactModelImpl.getColumnOriginalValue(
						columnName);
				}
				else {
					arguments[i] = cpImpactModelImpl.getColumnValue(columnName);
				}
			}

			return arguments;
		}

		private static Map<FinderPath, Long> _finderPathColumnBitmasksCache =
			new ConcurrentHashMap<>();

	}

}