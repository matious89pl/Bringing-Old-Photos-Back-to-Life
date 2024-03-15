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

package com.everis.rec.maintenanceactivity.service.persistence.impl;

import com.everis.rec.maintenanceactivity.exception.NoSuchMaintenanceActivityException;
import com.everis.rec.maintenanceactivity.model.MaintenanceActivity;
import com.everis.rec.maintenanceactivity.model.impl.MaintenanceActivityImpl;
import com.everis.rec.maintenanceactivity.model.impl.MaintenanceActivityModelImpl;
import com.everis.rec.maintenanceactivity.service.persistence.MaintenanceActivityPersistence;
import com.everis.rec.maintenanceactivity.service.persistence.impl.constants.AMAPersistenceConstants;

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
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Date;
import java.util.HashMap;
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
 * The persistence implementation for the maintenance activity service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = MaintenanceActivityPersistence.class)
public class MaintenanceActivityPersistenceImpl
	extends BasePersistenceImpl<MaintenanceActivity>
	implements MaintenanceActivityPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>MaintenanceActivityUtil</code> to access the maintenance activity persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		MaintenanceActivityImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByUuid;
	private FinderPath _finderPathWithoutPaginationFindByUuid;
	private FinderPath _finderPathCountByUuid;

	/**
	 * Returns all the maintenance activities where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching maintenance activities
	 */
	@Override
	public List<MaintenanceActivity> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the maintenance activities where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MaintenanceActivityModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of maintenance activities
	 * @param end the upper bound of the range of maintenance activities (not inclusive)
	 * @return the range of matching maintenance activities
	 */
	@Override
	public List<MaintenanceActivity> findByUuid(
		String uuid, int start, int end) {

		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the maintenance activities where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MaintenanceActivityModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of maintenance activities
	 * @param end the upper bound of the range of maintenance activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching maintenance activities
	 */
	@Override
	public List<MaintenanceActivity> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<MaintenanceActivity> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the maintenance activities where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MaintenanceActivityModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of maintenance activities
	 * @param end the upper bound of the range of maintenance activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching maintenance activities
	 */
	@Override
	public List<MaintenanceActivity> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<MaintenanceActivity> orderByComparator,
		boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid;
				finderArgs = new Object[] {uuid};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid;
			finderArgs = new Object[] {uuid, start, end, orderByComparator};
		}

		List<MaintenanceActivity> list = null;

		if (useFinderCache) {
			list = (List<MaintenanceActivity>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (MaintenanceActivity maintenanceActivity : list) {
					if (!uuid.equals(maintenanceActivity.getUuid())) {
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

			sb.append(_SQL_SELECT_MAINTENANCEACTIVITY_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(MaintenanceActivityModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				list = (List<MaintenanceActivity>)QueryUtil.list(
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
	 * Returns the first maintenance activity in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching maintenance activity
	 * @throws NoSuchMaintenanceActivityException if a matching maintenance activity could not be found
	 */
	@Override
	public MaintenanceActivity findByUuid_First(
			String uuid,
			OrderByComparator<MaintenanceActivity> orderByComparator)
		throws NoSuchMaintenanceActivityException {

		MaintenanceActivity maintenanceActivity = fetchByUuid_First(
			uuid, orderByComparator);

		if (maintenanceActivity != null) {
			return maintenanceActivity;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchMaintenanceActivityException(sb.toString());
	}

	/**
	 * Returns the first maintenance activity in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching maintenance activity, or <code>null</code> if a matching maintenance activity could not be found
	 */
	@Override
	public MaintenanceActivity fetchByUuid_First(
		String uuid, OrderByComparator<MaintenanceActivity> orderByComparator) {

		List<MaintenanceActivity> list = findByUuid(
			uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last maintenance activity in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching maintenance activity
	 * @throws NoSuchMaintenanceActivityException if a matching maintenance activity could not be found
	 */
	@Override
	public MaintenanceActivity findByUuid_Last(
			String uuid,
			OrderByComparator<MaintenanceActivity> orderByComparator)
		throws NoSuchMaintenanceActivityException {

		MaintenanceActivity maintenanceActivity = fetchByUuid_Last(
			uuid, orderByComparator);

		if (maintenanceActivity != null) {
			return maintenanceActivity;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchMaintenanceActivityException(sb.toString());
	}

	/**
	 * Returns the last maintenance activity in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching maintenance activity, or <code>null</code> if a matching maintenance activity could not be found
	 */
	@Override
	public MaintenanceActivity fetchByUuid_Last(
		String uuid, OrderByComparator<MaintenanceActivity> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<MaintenanceActivity> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the maintenance activities before and after the current maintenance activity in the ordered set where uuid = &#63;.
	 *
	 * @param maintenanceactivityId the primary key of the current maintenance activity
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next maintenance activity
	 * @throws NoSuchMaintenanceActivityException if a maintenance activity with the primary key could not be found
	 */
	@Override
	public MaintenanceActivity[] findByUuid_PrevAndNext(
			long maintenanceactivityId, String uuid,
			OrderByComparator<MaintenanceActivity> orderByComparator)
		throws NoSuchMaintenanceActivityException {

		uuid = Objects.toString(uuid, "");

		MaintenanceActivity maintenanceActivity = findByPrimaryKey(
			maintenanceactivityId);

		Session session = null;

		try {
			session = openSession();

			MaintenanceActivity[] array = new MaintenanceActivityImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, maintenanceActivity, uuid, orderByComparator, true);

			array[1] = maintenanceActivity;

			array[2] = getByUuid_PrevAndNext(
				session, maintenanceActivity, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected MaintenanceActivity getByUuid_PrevAndNext(
		Session session, MaintenanceActivity maintenanceActivity, String uuid,
		OrderByComparator<MaintenanceActivity> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_MAINTENANCEACTIVITY_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_UUID_2);
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
			sb.append(MaintenanceActivityModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						maintenanceActivity)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<MaintenanceActivity> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the maintenance activities where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (MaintenanceActivity maintenanceActivity :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(maintenanceActivity);
		}
	}

	/**
	 * Returns the number of maintenance activities where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching maintenance activities
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_MAINTENANCEACTIVITY_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
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

	private static final String _FINDER_COLUMN_UUID_UUID_2 =
		"maintenanceActivity.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(maintenanceActivity.uuid IS NULL OR maintenanceActivity.uuid = '')";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the maintenance activities where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching maintenance activities
	 */
	@Override
	public List<MaintenanceActivity> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the maintenance activities where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MaintenanceActivityModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of maintenance activities
	 * @param end the upper bound of the range of maintenance activities (not inclusive)
	 * @return the range of matching maintenance activities
	 */
	@Override
	public List<MaintenanceActivity> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the maintenance activities where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MaintenanceActivityModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of maintenance activities
	 * @param end the upper bound of the range of maintenance activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching maintenance activities
	 */
	@Override
	public List<MaintenanceActivity> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<MaintenanceActivity> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the maintenance activities where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MaintenanceActivityModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of maintenance activities
	 * @param end the upper bound of the range of maintenance activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching maintenance activities
	 */
	@Override
	public List<MaintenanceActivity> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<MaintenanceActivity> orderByComparator,
		boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid_C;
				finderArgs = new Object[] {uuid, companyId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid_C;
			finderArgs = new Object[] {
				uuid, companyId, start, end, orderByComparator
			};
		}

		List<MaintenanceActivity> list = null;

		if (useFinderCache) {
			list = (List<MaintenanceActivity>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (MaintenanceActivity maintenanceActivity : list) {
					if (!uuid.equals(maintenanceActivity.getUuid()) ||
						(companyId != maintenanceActivity.getCompanyId())) {

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

			sb.append(_SQL_SELECT_MAINTENANCEACTIVITY_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(MaintenanceActivityModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(companyId);

				list = (List<MaintenanceActivity>)QueryUtil.list(
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
	 * Returns the first maintenance activity in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching maintenance activity
	 * @throws NoSuchMaintenanceActivityException if a matching maintenance activity could not be found
	 */
	@Override
	public MaintenanceActivity findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<MaintenanceActivity> orderByComparator)
		throws NoSuchMaintenanceActivityException {

		MaintenanceActivity maintenanceActivity = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (maintenanceActivity != null) {
			return maintenanceActivity;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchMaintenanceActivityException(sb.toString());
	}

	/**
	 * Returns the first maintenance activity in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching maintenance activity, or <code>null</code> if a matching maintenance activity could not be found
	 */
	@Override
	public MaintenanceActivity fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<MaintenanceActivity> orderByComparator) {

		List<MaintenanceActivity> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last maintenance activity in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching maintenance activity
	 * @throws NoSuchMaintenanceActivityException if a matching maintenance activity could not be found
	 */
	@Override
	public MaintenanceActivity findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<MaintenanceActivity> orderByComparator)
		throws NoSuchMaintenanceActivityException {

		MaintenanceActivity maintenanceActivity = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (maintenanceActivity != null) {
			return maintenanceActivity;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchMaintenanceActivityException(sb.toString());
	}

	/**
	 * Returns the last maintenance activity in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching maintenance activity, or <code>null</code> if a matching maintenance activity could not be found
	 */
	@Override
	public MaintenanceActivity fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<MaintenanceActivity> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<MaintenanceActivity> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the maintenance activities before and after the current maintenance activity in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param maintenanceactivityId the primary key of the current maintenance activity
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next maintenance activity
	 * @throws NoSuchMaintenanceActivityException if a maintenance activity with the primary key could not be found
	 */
	@Override
	public MaintenanceActivity[] findByUuid_C_PrevAndNext(
			long maintenanceactivityId, String uuid, long companyId,
			OrderByComparator<MaintenanceActivity> orderByComparator)
		throws NoSuchMaintenanceActivityException {

		uuid = Objects.toString(uuid, "");

		MaintenanceActivity maintenanceActivity = findByPrimaryKey(
			maintenanceactivityId);

		Session session = null;

		try {
			session = openSession();

			MaintenanceActivity[] array = new MaintenanceActivityImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, maintenanceActivity, uuid, companyId,
				orderByComparator, true);

			array[1] = maintenanceActivity;

			array[2] = getByUuid_C_PrevAndNext(
				session, maintenanceActivity, uuid, companyId,
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

	protected MaintenanceActivity getByUuid_C_PrevAndNext(
		Session session, MaintenanceActivity maintenanceActivity, String uuid,
		long companyId,
		OrderByComparator<MaintenanceActivity> orderByComparator,
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

		sb.append(_SQL_SELECT_MAINTENANCEACTIVITY_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

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
			sb.append(MaintenanceActivityModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		queryPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						maintenanceActivity)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<MaintenanceActivity> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the maintenance activities where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (MaintenanceActivity maintenanceActivity :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(maintenanceActivity);
		}
	}

	/**
	 * Returns the number of maintenance activities where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching maintenance activities
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_MAINTENANCEACTIVITY_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(companyId);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_2 =
		"maintenanceActivity.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(maintenanceActivity.uuid IS NULL OR maintenanceActivity.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"maintenanceActivity.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByStatus;
	private FinderPath _finderPathWithoutPaginationFindByStatus;
	private FinderPath _finderPathCountByStatus;

	/**
	 * Returns all the maintenance activities where status = &#63;.
	 *
	 * @param status the status
	 * @return the matching maintenance activities
	 */
	@Override
	public List<MaintenanceActivity> findByStatus(String status) {
		return findByStatus(status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the maintenance activities where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MaintenanceActivityModelImpl</code>.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of maintenance activities
	 * @param end the upper bound of the range of maintenance activities (not inclusive)
	 * @return the range of matching maintenance activities
	 */
	@Override
	public List<MaintenanceActivity> findByStatus(
		String status, int start, int end) {

		return findByStatus(status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the maintenance activities where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MaintenanceActivityModelImpl</code>.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of maintenance activities
	 * @param end the upper bound of the range of maintenance activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching maintenance activities
	 */
	@Override
	public List<MaintenanceActivity> findByStatus(
		String status, int start, int end,
		OrderByComparator<MaintenanceActivity> orderByComparator) {

		return findByStatus(status, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the maintenance activities where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MaintenanceActivityModelImpl</code>.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of maintenance activities
	 * @param end the upper bound of the range of maintenance activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching maintenance activities
	 */
	@Override
	public List<MaintenanceActivity> findByStatus(
		String status, int start, int end,
		OrderByComparator<MaintenanceActivity> orderByComparator,
		boolean useFinderCache) {

		status = Objects.toString(status, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByStatus;
				finderArgs = new Object[] {status};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByStatus;
			finderArgs = new Object[] {status, start, end, orderByComparator};
		}

		List<MaintenanceActivity> list = null;

		if (useFinderCache) {
			list = (List<MaintenanceActivity>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (MaintenanceActivity maintenanceActivity : list) {
					if (!status.equals(maintenanceActivity.getStatus())) {
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

			sb.append(_SQL_SELECT_MAINTENANCEACTIVITY_WHERE);

			boolean bindStatus = false;

			if (status.isEmpty()) {
				sb.append(_FINDER_COLUMN_STATUS_STATUS_3);
			}
			else {
				bindStatus = true;

				sb.append(_FINDER_COLUMN_STATUS_STATUS_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(MaintenanceActivityModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindStatus) {
					queryPos.add(status);
				}

				list = (List<MaintenanceActivity>)QueryUtil.list(
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
	 * Returns the first maintenance activity in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching maintenance activity
	 * @throws NoSuchMaintenanceActivityException if a matching maintenance activity could not be found
	 */
	@Override
	public MaintenanceActivity findByStatus_First(
			String status,
			OrderByComparator<MaintenanceActivity> orderByComparator)
		throws NoSuchMaintenanceActivityException {

		MaintenanceActivity maintenanceActivity = fetchByStatus_First(
			status, orderByComparator);

		if (maintenanceActivity != null) {
			return maintenanceActivity;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("status=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchMaintenanceActivityException(sb.toString());
	}

	/**
	 * Returns the first maintenance activity in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching maintenance activity, or <code>null</code> if a matching maintenance activity could not be found
	 */
	@Override
	public MaintenanceActivity fetchByStatus_First(
		String status,
		OrderByComparator<MaintenanceActivity> orderByComparator) {

		List<MaintenanceActivity> list = findByStatus(
			status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last maintenance activity in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching maintenance activity
	 * @throws NoSuchMaintenanceActivityException if a matching maintenance activity could not be found
	 */
	@Override
	public MaintenanceActivity findByStatus_Last(
			String status,
			OrderByComparator<MaintenanceActivity> orderByComparator)
		throws NoSuchMaintenanceActivityException {

		MaintenanceActivity maintenanceActivity = fetchByStatus_Last(
			status, orderByComparator);

		if (maintenanceActivity != null) {
			return maintenanceActivity;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("status=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchMaintenanceActivityException(sb.toString());
	}

	/**
	 * Returns the last maintenance activity in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching maintenance activity, or <code>null</code> if a matching maintenance activity could not be found
	 */
	@Override
	public MaintenanceActivity fetchByStatus_Last(
		String status,
		OrderByComparator<MaintenanceActivity> orderByComparator) {

		int count = countByStatus(status);

		if (count == 0) {
			return null;
		}

		List<MaintenanceActivity> list = findByStatus(
			status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the maintenance activities before and after the current maintenance activity in the ordered set where status = &#63;.
	 *
	 * @param maintenanceactivityId the primary key of the current maintenance activity
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next maintenance activity
	 * @throws NoSuchMaintenanceActivityException if a maintenance activity with the primary key could not be found
	 */
	@Override
	public MaintenanceActivity[] findByStatus_PrevAndNext(
			long maintenanceactivityId, String status,
			OrderByComparator<MaintenanceActivity> orderByComparator)
		throws NoSuchMaintenanceActivityException {

		status = Objects.toString(status, "");

		MaintenanceActivity maintenanceActivity = findByPrimaryKey(
			maintenanceactivityId);

		Session session = null;

		try {
			session = openSession();

			MaintenanceActivity[] array = new MaintenanceActivityImpl[3];

			array[0] = getByStatus_PrevAndNext(
				session, maintenanceActivity, status, orderByComparator, true);

			array[1] = maintenanceActivity;

			array[2] = getByStatus_PrevAndNext(
				session, maintenanceActivity, status, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected MaintenanceActivity getByStatus_PrevAndNext(
		Session session, MaintenanceActivity maintenanceActivity, String status,
		OrderByComparator<MaintenanceActivity> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_MAINTENANCEACTIVITY_WHERE);

		boolean bindStatus = false;

		if (status.isEmpty()) {
			sb.append(_FINDER_COLUMN_STATUS_STATUS_3);
		}
		else {
			bindStatus = true;

			sb.append(_FINDER_COLUMN_STATUS_STATUS_2);
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
			sb.append(MaintenanceActivityModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindStatus) {
			queryPos.add(status);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						maintenanceActivity)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<MaintenanceActivity> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the maintenance activities where status = &#63; from the database.
	 *
	 * @param status the status
	 */
	@Override
	public void removeByStatus(String status) {
		for (MaintenanceActivity maintenanceActivity :
				findByStatus(
					status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(maintenanceActivity);
		}
	}

	/**
	 * Returns the number of maintenance activities where status = &#63;.
	 *
	 * @param status the status
	 * @return the number of matching maintenance activities
	 */
	@Override
	public int countByStatus(String status) {
		status = Objects.toString(status, "");

		FinderPath finderPath = _finderPathCountByStatus;

		Object[] finderArgs = new Object[] {status};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_MAINTENANCEACTIVITY_WHERE);

			boolean bindStatus = false;

			if (status.isEmpty()) {
				sb.append(_FINDER_COLUMN_STATUS_STATUS_3);
			}
			else {
				bindStatus = true;

				sb.append(_FINDER_COLUMN_STATUS_STATUS_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindStatus) {
					queryPos.add(status);
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

	private static final String _FINDER_COLUMN_STATUS_STATUS_2 =
		"maintenanceActivity.status = ?";

	private static final String _FINDER_COLUMN_STATUS_STATUS_3 =
		"(maintenanceActivity.status IS NULL OR maintenanceActivity.status = '')";

	public MaintenanceActivityPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

		setDBColumnNames(dbColumnNames);

		setModelClass(MaintenanceActivity.class);

		setModelImplClass(MaintenanceActivityImpl.class);
		setModelPKClass(long.class);
	}

	/**
	 * Caches the maintenance activity in the entity cache if it is enabled.
	 *
	 * @param maintenanceActivity the maintenance activity
	 */
	@Override
	public void cacheResult(MaintenanceActivity maintenanceActivity) {
		entityCache.putResult(
			MaintenanceActivityImpl.class, maintenanceActivity.getPrimaryKey(),
			maintenanceActivity);
	}

	/**
	 * Caches the maintenance activities in the entity cache if it is enabled.
	 *
	 * @param maintenanceActivities the maintenance activities
	 */
	@Override
	public void cacheResult(List<MaintenanceActivity> maintenanceActivities) {
		for (MaintenanceActivity maintenanceActivity : maintenanceActivities) {
			if (entityCache.getResult(
					MaintenanceActivityImpl.class,
					maintenanceActivity.getPrimaryKey()) == null) {

				cacheResult(maintenanceActivity);
			}
		}
	}

	/**
	 * Clears the cache for all maintenance activities.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(MaintenanceActivityImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the maintenance activity.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MaintenanceActivity maintenanceActivity) {
		entityCache.removeResult(
			MaintenanceActivityImpl.class, maintenanceActivity);
	}

	@Override
	public void clearCache(List<MaintenanceActivity> maintenanceActivities) {
		for (MaintenanceActivity maintenanceActivity : maintenanceActivities) {
			entityCache.removeResult(
				MaintenanceActivityImpl.class, maintenanceActivity);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(MaintenanceActivityImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new maintenance activity with the primary key. Does not add the maintenance activity to the database.
	 *
	 * @param maintenanceactivityId the primary key for the new maintenance activity
	 * @return the new maintenance activity
	 */
	@Override
	public MaintenanceActivity create(long maintenanceactivityId) {
		MaintenanceActivity maintenanceActivity = new MaintenanceActivityImpl();

		maintenanceActivity.setNew(true);
		maintenanceActivity.setPrimaryKey(maintenanceactivityId);

		String uuid = PortalUUIDUtil.generate();

		maintenanceActivity.setUuid(uuid);

		maintenanceActivity.setCompanyId(CompanyThreadLocal.getCompanyId());

		return maintenanceActivity;
	}

	/**
	 * Removes the maintenance activity with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param maintenanceactivityId the primary key of the maintenance activity
	 * @return the maintenance activity that was removed
	 * @throws NoSuchMaintenanceActivityException if a maintenance activity with the primary key could not be found
	 */
	@Override
	public MaintenanceActivity remove(long maintenanceactivityId)
		throws NoSuchMaintenanceActivityException {

		return remove((Serializable)maintenanceactivityId);
	}

	/**
	 * Removes the maintenance activity with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the maintenance activity
	 * @return the maintenance activity that was removed
	 * @throws NoSuchMaintenanceActivityException if a maintenance activity with the primary key could not be found
	 */
	@Override
	public MaintenanceActivity remove(Serializable primaryKey)
		throws NoSuchMaintenanceActivityException {

		Session session = null;

		try {
			session = openSession();

			MaintenanceActivity maintenanceActivity =
				(MaintenanceActivity)session.get(
					MaintenanceActivityImpl.class, primaryKey);

			if (maintenanceActivity == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMaintenanceActivityException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(maintenanceActivity);
		}
		catch (NoSuchMaintenanceActivityException noSuchEntityException) {
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
	protected MaintenanceActivity removeImpl(
		MaintenanceActivity maintenanceActivity) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(maintenanceActivity)) {
				maintenanceActivity = (MaintenanceActivity)session.get(
					MaintenanceActivityImpl.class,
					maintenanceActivity.getPrimaryKeyObj());
			}

			if (maintenanceActivity != null) {
				session.delete(maintenanceActivity);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (maintenanceActivity != null) {
			clearCache(maintenanceActivity);
		}

		return maintenanceActivity;
	}

	@Override
	public MaintenanceActivity updateImpl(
		MaintenanceActivity maintenanceActivity) {

		boolean isNew = maintenanceActivity.isNew();

		if (!(maintenanceActivity instanceof MaintenanceActivityModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(maintenanceActivity.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					maintenanceActivity);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in maintenanceActivity proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom MaintenanceActivity implementation " +
					maintenanceActivity.getClass());
		}

		MaintenanceActivityModelImpl maintenanceActivityModelImpl =
			(MaintenanceActivityModelImpl)maintenanceActivity;

		if (Validator.isNull(maintenanceActivity.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			maintenanceActivity.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (maintenanceActivity.getCreateDate() == null)) {
			if (serviceContext == null) {
				maintenanceActivity.setCreateDate(now);
			}
			else {
				maintenanceActivity.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!maintenanceActivityModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				maintenanceActivity.setModifiedDate(now);
			}
			else {
				maintenanceActivity.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(maintenanceActivity);
			}
			else {
				maintenanceActivity = (MaintenanceActivity)session.merge(
					maintenanceActivity);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			MaintenanceActivityImpl.class, maintenanceActivityModelImpl, false,
			true);

		if (isNew) {
			maintenanceActivity.setNew(false);
		}

		maintenanceActivity.resetOriginalValues();

		return maintenanceActivity;
	}

	/**
	 * Returns the maintenance activity with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the maintenance activity
	 * @return the maintenance activity
	 * @throws NoSuchMaintenanceActivityException if a maintenance activity with the primary key could not be found
	 */
	@Override
	public MaintenanceActivity findByPrimaryKey(Serializable primaryKey)
		throws NoSuchMaintenanceActivityException {

		MaintenanceActivity maintenanceActivity = fetchByPrimaryKey(primaryKey);

		if (maintenanceActivity == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchMaintenanceActivityException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return maintenanceActivity;
	}

	/**
	 * Returns the maintenance activity with the primary key or throws a <code>NoSuchMaintenanceActivityException</code> if it could not be found.
	 *
	 * @param maintenanceactivityId the primary key of the maintenance activity
	 * @return the maintenance activity
	 * @throws NoSuchMaintenanceActivityException if a maintenance activity with the primary key could not be found
	 */
	@Override
	public MaintenanceActivity findByPrimaryKey(long maintenanceactivityId)
		throws NoSuchMaintenanceActivityException {

		return findByPrimaryKey((Serializable)maintenanceactivityId);
	}

	/**
	 * Returns the maintenance activity with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param maintenanceactivityId the primary key of the maintenance activity
	 * @return the maintenance activity, or <code>null</code> if a maintenance activity with the primary key could not be found
	 */
	@Override
	public MaintenanceActivity fetchByPrimaryKey(long maintenanceactivityId) {
		return fetchByPrimaryKey((Serializable)maintenanceactivityId);
	}

	/**
	 * Returns all the maintenance activities.
	 *
	 * @return the maintenance activities
	 */
	@Override
	public List<MaintenanceActivity> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the maintenance activities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MaintenanceActivityModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of maintenance activities
	 * @param end the upper bound of the range of maintenance activities (not inclusive)
	 * @return the range of maintenance activities
	 */
	@Override
	public List<MaintenanceActivity> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the maintenance activities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MaintenanceActivityModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of maintenance activities
	 * @param end the upper bound of the range of maintenance activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of maintenance activities
	 */
	@Override
	public List<MaintenanceActivity> findAll(
		int start, int end,
		OrderByComparator<MaintenanceActivity> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the maintenance activities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MaintenanceActivityModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of maintenance activities
	 * @param end the upper bound of the range of maintenance activities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of maintenance activities
	 */
	@Override
	public List<MaintenanceActivity> findAll(
		int start, int end,
		OrderByComparator<MaintenanceActivity> orderByComparator,
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

		List<MaintenanceActivity> list = null;

		if (useFinderCache) {
			list = (List<MaintenanceActivity>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_MAINTENANCEACTIVITY);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_MAINTENANCEACTIVITY;

				sql = sql.concat(MaintenanceActivityModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<MaintenanceActivity>)QueryUtil.list(
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
	 * Removes all the maintenance activities from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (MaintenanceActivity maintenanceActivity : findAll()) {
			remove(maintenanceActivity);
		}
	}

	/**
	 * Returns the number of maintenance activities.
	 *
	 * @return the number of maintenance activities
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(
					_SQL_COUNT_MAINTENANCEACTIVITY);

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
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "maintenanceactivityId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_MAINTENANCEACTIVITY;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return MaintenanceActivityModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the maintenance activity persistence.
	 */
	@Activate
	public void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		_argumentsResolverServiceRegistration = _bundleContext.registerService(
			ArgumentsResolver.class,
			new MaintenanceActivityModelArgumentsResolver(),
			MapUtil.singletonDictionary(
				"model.class.name", MaintenanceActivity.class.getName()));

		_finderPathWithPaginationFindAll = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathWithoutPaginationFindAll = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathCountAll = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0], new String[0], false);

		_finderPathWithPaginationFindByUuid = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"uuid_"}, true);

		_finderPathWithoutPaginationFindByUuid = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()}, new String[] {"uuid_"},
			true);

		_finderPathCountByUuid = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()}, new String[] {"uuid_"},
			false);

		_finderPathWithPaginationFindByUuid_C = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"uuid_", "companyId"}, true);

		_finderPathWithoutPaginationFindByUuid_C = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "companyId"}, true);

		_finderPathCountByUuid_C = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "companyId"}, false);

		_finderPathWithPaginationFindByStatus = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByStatus",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"status"}, true);

		_finderPathWithoutPaginationFindByStatus = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByStatus",
			new String[] {String.class.getName()}, new String[] {"status"},
			true);

		_finderPathCountByStatus = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByStatus",
			new String[] {String.class.getName()}, new String[] {"status"},
			false);
	}

	@Deactivate
	public void deactivate() {
		entityCache.removeCache(MaintenanceActivityImpl.class.getName());

		_argumentsResolverServiceRegistration.unregister();

		for (ServiceRegistration<FinderPath> serviceRegistration :
				_serviceRegistrations) {

			serviceRegistration.unregister();
		}
	}

	@Override
	@Reference(
		target = AMAPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = AMAPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = AMAPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
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

	private static final String _SQL_SELECT_MAINTENANCEACTIVITY =
		"SELECT maintenanceActivity FROM MaintenanceActivity maintenanceActivity";

	private static final String _SQL_SELECT_MAINTENANCEACTIVITY_WHERE =
		"SELECT maintenanceActivity FROM MaintenanceActivity maintenanceActivity WHERE ";

	private static final String _SQL_COUNT_MAINTENANCEACTIVITY =
		"SELECT COUNT(maintenanceActivity) FROM MaintenanceActivity maintenanceActivity";

	private static final String _SQL_COUNT_MAINTENANCEACTIVITY_WHERE =
		"SELECT COUNT(maintenanceActivity) FROM MaintenanceActivity maintenanceActivity WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "maintenanceActivity.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No MaintenanceActivity exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No MaintenanceActivity exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		MaintenanceActivityPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

	static {
		try {
			Class.forName(AMAPersistenceConstants.class.getName());
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

	private static class MaintenanceActivityModelArgumentsResolver
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

			MaintenanceActivityModelImpl maintenanceActivityModelImpl =
				(MaintenanceActivityModelImpl)baseModel;

			long columnBitmask =
				maintenanceActivityModelImpl.getColumnBitmask();

			if (!checkColumn || (columnBitmask == 0)) {
				return _getValue(
					maintenanceActivityModelImpl, columnNames, original);
			}

			Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
				finderPath);

			if (finderPathColumnBitmask == null) {
				finderPathColumnBitmask = 0L;

				for (String columnName : columnNames) {
					finderPathColumnBitmask |=
						maintenanceActivityModelImpl.getColumnBitmask(
							columnName);
				}

				_finderPathColumnBitmasksCache.put(
					finderPath, finderPathColumnBitmask);
			}

			if ((columnBitmask & finderPathColumnBitmask) != 0) {
				return _getValue(
					maintenanceActivityModelImpl, columnNames, original);
			}

			return null;
		}

		private Object[] _getValue(
			MaintenanceActivityModelImpl maintenanceActivityModelImpl,
			String[] columnNames, boolean original) {

			Object[] arguments = new Object[columnNames.length];

			for (int i = 0; i < arguments.length; i++) {
				String columnName = columnNames[i];

				if (original) {
					arguments[i] =
						maintenanceActivityModelImpl.getColumnOriginalValue(
							columnName);
				}
				else {
					arguments[i] = maintenanceActivityModelImpl.getColumnValue(
						columnName);
				}
			}

			return arguments;
		}

		private static Map<FinderPath, Long> _finderPathColumnBitmasksCache =
			new ConcurrentHashMap<>();

	}

}