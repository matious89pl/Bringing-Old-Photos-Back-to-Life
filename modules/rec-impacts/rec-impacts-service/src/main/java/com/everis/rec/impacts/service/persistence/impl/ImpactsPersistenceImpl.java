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

package com.everis.rec.impacts.service.persistence.impl;

import com.everis.rec.impacts.exception.NoSuchImpactsException;
import com.everis.rec.impacts.model.Impacts;
import com.everis.rec.impacts.model.impl.ImpactsImpl;
import com.everis.rec.impacts.model.impl.ImpactsModelImpl;
import com.everis.rec.impacts.service.persistence.ImpactsPersistence;
import com.everis.rec.impacts.service.persistence.impl.constants.rec_cpImpactPersistenceConstants;

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
 * The persistence implementation for the impacts service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = ImpactsPersistence.class)
public class ImpactsPersistenceImpl
	extends BasePersistenceImpl<Impacts> implements ImpactsPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ImpactsUtil</code> to access the impacts persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ImpactsImpl.class.getName();

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
	 * Returns all the impactses where impactCategory = &#63;.
	 *
	 * @param impactCategory the impact category
	 * @return the matching impactses
	 */
	@Override
	public List<Impacts> findByImpactCategory(String impactCategory) {
		return findByImpactCategory(
			impactCategory, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the impactses where impactCategory = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ImpactsModelImpl</code>.
	 * </p>
	 *
	 * @param impactCategory the impact category
	 * @param start the lower bound of the range of impactses
	 * @param end the upper bound of the range of impactses (not inclusive)
	 * @return the range of matching impactses
	 */
	@Override
	public List<Impacts> findByImpactCategory(
		String impactCategory, int start, int end) {

		return findByImpactCategory(impactCategory, start, end, null);
	}

	/**
	 * Returns an ordered range of all the impactses where impactCategory = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ImpactsModelImpl</code>.
	 * </p>
	 *
	 * @param impactCategory the impact category
	 * @param start the lower bound of the range of impactses
	 * @param end the upper bound of the range of impactses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching impactses
	 */
	@Override
	public List<Impacts> findByImpactCategory(
		String impactCategory, int start, int end,
		OrderByComparator<Impacts> orderByComparator) {

		return findByImpactCategory(
			impactCategory, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the impactses where impactCategory = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ImpactsModelImpl</code>.
	 * </p>
	 *
	 * @param impactCategory the impact category
	 * @param start the lower bound of the range of impactses
	 * @param end the upper bound of the range of impactses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching impactses
	 */
	@Override
	public List<Impacts> findByImpactCategory(
		String impactCategory, int start, int end,
		OrderByComparator<Impacts> orderByComparator, boolean useFinderCache) {

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

		List<Impacts> list = null;

		if (useFinderCache) {
			list = (List<Impacts>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Impacts impacts : list) {
					if (!impactCategory.equals(impacts.getImpactCategory())) {
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

			sb.append(_SQL_SELECT_IMPACTS_WHERE);

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
				sb.append(ImpactsModelImpl.ORDER_BY_JPQL);
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

				list = (List<Impacts>)QueryUtil.list(
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
	 * Returns the first impacts in the ordered set where impactCategory = &#63;.
	 *
	 * @param impactCategory the impact category
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching impacts
	 * @throws NoSuchImpactsException if a matching impacts could not be found
	 */
	@Override
	public Impacts findByImpactCategory_First(
			String impactCategory, OrderByComparator<Impacts> orderByComparator)
		throws NoSuchImpactsException {

		Impacts impacts = fetchByImpactCategory_First(
			impactCategory, orderByComparator);

		if (impacts != null) {
			return impacts;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("impactCategory=");
		sb.append(impactCategory);

		sb.append("}");

		throw new NoSuchImpactsException(sb.toString());
	}

	/**
	 * Returns the first impacts in the ordered set where impactCategory = &#63;.
	 *
	 * @param impactCategory the impact category
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching impacts, or <code>null</code> if a matching impacts could not be found
	 */
	@Override
	public Impacts fetchByImpactCategory_First(
		String impactCategory, OrderByComparator<Impacts> orderByComparator) {

		List<Impacts> list = findByImpactCategory(
			impactCategory, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last impacts in the ordered set where impactCategory = &#63;.
	 *
	 * @param impactCategory the impact category
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching impacts
	 * @throws NoSuchImpactsException if a matching impacts could not be found
	 */
	@Override
	public Impacts findByImpactCategory_Last(
			String impactCategory, OrderByComparator<Impacts> orderByComparator)
		throws NoSuchImpactsException {

		Impacts impacts = fetchByImpactCategory_Last(
			impactCategory, orderByComparator);

		if (impacts != null) {
			return impacts;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("impactCategory=");
		sb.append(impactCategory);

		sb.append("}");

		throw new NoSuchImpactsException(sb.toString());
	}

	/**
	 * Returns the last impacts in the ordered set where impactCategory = &#63;.
	 *
	 * @param impactCategory the impact category
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching impacts, or <code>null</code> if a matching impacts could not be found
	 */
	@Override
	public Impacts fetchByImpactCategory_Last(
		String impactCategory, OrderByComparator<Impacts> orderByComparator) {

		int count = countByImpactCategory(impactCategory);

		if (count == 0) {
			return null;
		}

		List<Impacts> list = findByImpactCategory(
			impactCategory, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the impactses before and after the current impacts in the ordered set where impactCategory = &#63;.
	 *
	 * @param impactId the primary key of the current impacts
	 * @param impactCategory the impact category
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next impacts
	 * @throws NoSuchImpactsException if a impacts with the primary key could not be found
	 */
	@Override
	public Impacts[] findByImpactCategory_PrevAndNext(
			long impactId, String impactCategory,
			OrderByComparator<Impacts> orderByComparator)
		throws NoSuchImpactsException {

		impactCategory = Objects.toString(impactCategory, "");

		Impacts impacts = findByPrimaryKey(impactId);

		Session session = null;

		try {
			session = openSession();

			Impacts[] array = new ImpactsImpl[3];

			array[0] = getByImpactCategory_PrevAndNext(
				session, impacts, impactCategory, orderByComparator, true);

			array[1] = impacts;

			array[2] = getByImpactCategory_PrevAndNext(
				session, impacts, impactCategory, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Impacts getByImpactCategory_PrevAndNext(
		Session session, Impacts impacts, String impactCategory,
		OrderByComparator<Impacts> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_IMPACTS_WHERE);

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
			sb.append(ImpactsModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(impacts)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Impacts> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the impactses where impactCategory = &#63; from the database.
	 *
	 * @param impactCategory the impact category
	 */
	@Override
	public void removeByImpactCategory(String impactCategory) {
		for (Impacts impacts :
				findByImpactCategory(
					impactCategory, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(impacts);
		}
	}

	/**
	 * Returns the number of impactses where impactCategory = &#63;.
	 *
	 * @param impactCategory the impact category
	 * @return the number of matching impactses
	 */
	@Override
	public int countByImpactCategory(String impactCategory) {
		impactCategory = Objects.toString(impactCategory, "");

		FinderPath finderPath = _finderPathCountByImpactCategory;

		Object[] finderArgs = new Object[] {impactCategory};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_IMPACTS_WHERE);

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
		"impacts.impactCategory = ?";

	private static final String _FINDER_COLUMN_IMPACTCATEGORY_IMPACTCATEGORY_3 =
		"(impacts.impactCategory IS NULL OR impacts.impactCategory = '')";

	public ImpactsPersistenceImpl() {
		setModelClass(Impacts.class);

		setModelImplClass(ImpactsImpl.class);
		setModelPKClass(long.class);
	}

	/**
	 * Caches the impacts in the entity cache if it is enabled.
	 *
	 * @param impacts the impacts
	 */
	@Override
	public void cacheResult(Impacts impacts) {
		entityCache.putResult(
			ImpactsImpl.class, impacts.getPrimaryKey(), impacts);
	}

	/**
	 * Caches the impactses in the entity cache if it is enabled.
	 *
	 * @param impactses the impactses
	 */
	@Override
	public void cacheResult(List<Impacts> impactses) {
		for (Impacts impacts : impactses) {
			if (entityCache.getResult(
					ImpactsImpl.class, impacts.getPrimaryKey()) == null) {

				cacheResult(impacts);
			}
		}
	}

	/**
	 * Clears the cache for all impactses.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ImpactsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the impacts.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Impacts impacts) {
		entityCache.removeResult(ImpactsImpl.class, impacts);
	}

	@Override
	public void clearCache(List<Impacts> impactses) {
		for (Impacts impacts : impactses) {
			entityCache.removeResult(ImpactsImpl.class, impacts);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(ImpactsImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new impacts with the primary key. Does not add the impacts to the database.
	 *
	 * @param impactId the primary key for the new impacts
	 * @return the new impacts
	 */
	@Override
	public Impacts create(long impactId) {
		Impacts impacts = new ImpactsImpl();

		impacts.setNew(true);
		impacts.setPrimaryKey(impactId);

		return impacts;
	}

	/**
	 * Removes the impacts with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param impactId the primary key of the impacts
	 * @return the impacts that was removed
	 * @throws NoSuchImpactsException if a impacts with the primary key could not be found
	 */
	@Override
	public Impacts remove(long impactId) throws NoSuchImpactsException {
		return remove((Serializable)impactId);
	}

	/**
	 * Removes the impacts with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the impacts
	 * @return the impacts that was removed
	 * @throws NoSuchImpactsException if a impacts with the primary key could not be found
	 */
	@Override
	public Impacts remove(Serializable primaryKey)
		throws NoSuchImpactsException {

		Session session = null;

		try {
			session = openSession();

			Impacts impacts = (Impacts)session.get(
				ImpactsImpl.class, primaryKey);

			if (impacts == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchImpactsException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(impacts);
		}
		catch (NoSuchImpactsException noSuchEntityException) {
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
	protected Impacts removeImpl(Impacts impacts) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(impacts)) {
				impacts = (Impacts)session.get(
					ImpactsImpl.class, impacts.getPrimaryKeyObj());
			}

			if (impacts != null) {
				session.delete(impacts);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (impacts != null) {
			clearCache(impacts);
		}

		return impacts;
	}

	@Override
	public Impacts updateImpl(Impacts impacts) {
		boolean isNew = impacts.isNew();

		if (!(impacts instanceof ImpactsModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(impacts.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(impacts);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in impacts proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Impacts implementation " +
					impacts.getClass());
		}

		ImpactsModelImpl impactsModelImpl = (ImpactsModelImpl)impacts;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(impacts);
			}
			else {
				impacts = (Impacts)session.merge(impacts);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(ImpactsImpl.class, impactsModelImpl, false, true);

		if (isNew) {
			impacts.setNew(false);
		}

		impacts.resetOriginalValues();

		return impacts;
	}

	/**
	 * Returns the impacts with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the impacts
	 * @return the impacts
	 * @throws NoSuchImpactsException if a impacts with the primary key could not be found
	 */
	@Override
	public Impacts findByPrimaryKey(Serializable primaryKey)
		throws NoSuchImpactsException {

		Impacts impacts = fetchByPrimaryKey(primaryKey);

		if (impacts == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchImpactsException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return impacts;
	}

	/**
	 * Returns the impacts with the primary key or throws a <code>NoSuchImpactsException</code> if it could not be found.
	 *
	 * @param impactId the primary key of the impacts
	 * @return the impacts
	 * @throws NoSuchImpactsException if a impacts with the primary key could not be found
	 */
	@Override
	public Impacts findByPrimaryKey(long impactId)
		throws NoSuchImpactsException {

		return findByPrimaryKey((Serializable)impactId);
	}

	/**
	 * Returns the impacts with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param impactId the primary key of the impacts
	 * @return the impacts, or <code>null</code> if a impacts with the primary key could not be found
	 */
	@Override
	public Impacts fetchByPrimaryKey(long impactId) {
		return fetchByPrimaryKey((Serializable)impactId);
	}

	/**
	 * Returns all the impactses.
	 *
	 * @return the impactses
	 */
	@Override
	public List<Impacts> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the impactses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ImpactsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of impactses
	 * @param end the upper bound of the range of impactses (not inclusive)
	 * @return the range of impactses
	 */
	@Override
	public List<Impacts> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the impactses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ImpactsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of impactses
	 * @param end the upper bound of the range of impactses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of impactses
	 */
	@Override
	public List<Impacts> findAll(
		int start, int end, OrderByComparator<Impacts> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the impactses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ImpactsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of impactses
	 * @param end the upper bound of the range of impactses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of impactses
	 */
	@Override
	public List<Impacts> findAll(
		int start, int end, OrderByComparator<Impacts> orderByComparator,
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

		List<Impacts> list = null;

		if (useFinderCache) {
			list = (List<Impacts>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_IMPACTS);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_IMPACTS;

				sql = sql.concat(ImpactsModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<Impacts>)QueryUtil.list(
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
	 * Removes all the impactses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Impacts impacts : findAll()) {
			remove(impacts);
		}
	}

	/**
	 * Returns the number of impactses.
	 *
	 * @return the number of impactses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_IMPACTS);

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
		return _SQL_SELECT_IMPACTS;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ImpactsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the impacts persistence.
	 */
	@Activate
	public void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		_argumentsResolverServiceRegistration = _bundleContext.registerService(
			ArgumentsResolver.class, new ImpactsModelArgumentsResolver(),
			MapUtil.singletonDictionary(
				"model.class.name", Impacts.class.getName()));

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
		entityCache.removeCache(ImpactsImpl.class.getName());

		_argumentsResolverServiceRegistration.unregister();

		for (ServiceRegistration<FinderPath> serviceRegistration :
				_serviceRegistrations) {

			serviceRegistration.unregister();
		}
	}

	@Override
	@Reference(
		target = rec_cpImpactPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = rec_cpImpactPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = rec_cpImpactPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
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

	private static final String _SQL_SELECT_IMPACTS =
		"SELECT impacts FROM Impacts impacts";

	private static final String _SQL_SELECT_IMPACTS_WHERE =
		"SELECT impacts FROM Impacts impacts WHERE ";

	private static final String _SQL_COUNT_IMPACTS =
		"SELECT COUNT(impacts) FROM Impacts impacts";

	private static final String _SQL_COUNT_IMPACTS_WHERE =
		"SELECT COUNT(impacts) FROM Impacts impacts WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "impacts.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Impacts exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No Impacts exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		ImpactsPersistenceImpl.class);

	static {
		try {
			Class.forName(rec_cpImpactPersistenceConstants.class.getName());
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

	private static class ImpactsModelArgumentsResolver
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

			ImpactsModelImpl impactsModelImpl = (ImpactsModelImpl)baseModel;

			long columnBitmask = impactsModelImpl.getColumnBitmask();

			if (!checkColumn || (columnBitmask == 0)) {
				return _getValue(impactsModelImpl, columnNames, original);
			}

			Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
				finderPath);

			if (finderPathColumnBitmask == null) {
				finderPathColumnBitmask = 0L;

				for (String columnName : columnNames) {
					finderPathColumnBitmask |=
						impactsModelImpl.getColumnBitmask(columnName);
				}

				_finderPathColumnBitmasksCache.put(
					finderPath, finderPathColumnBitmask);
			}

			if ((columnBitmask & finderPathColumnBitmask) != 0) {
				return _getValue(impactsModelImpl, columnNames, original);
			}

			return null;
		}

		private Object[] _getValue(
			ImpactsModelImpl impactsModelImpl, String[] columnNames,
			boolean original) {

			Object[] arguments = new Object[columnNames.length];

			for (int i = 0; i < arguments.length; i++) {
				String columnName = columnNames[i];

				if (original) {
					arguments[i] = impactsModelImpl.getColumnOriginalValue(
						columnName);
				}
				else {
					arguments[i] = impactsModelImpl.getColumnValue(columnName);
				}
			}

			return arguments;
		}

		private static Map<FinderPath, Long> _finderPathColumnBitmasksCache =
			new ConcurrentHashMap<>();

	}

}