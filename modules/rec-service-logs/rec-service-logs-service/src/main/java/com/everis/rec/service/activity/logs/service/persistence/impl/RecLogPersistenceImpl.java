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

package com.everis.rec.service.activity.logs.service.persistence.impl;

import com.everis.rec.service.activity.logs.exception.NoSuchRecLogException;
import com.everis.rec.service.activity.logs.model.RecLog;
import com.everis.rec.service.activity.logs.model.impl.RecLogImpl;
import com.everis.rec.service.activity.logs.model.impl.RecLogModelImpl;
import com.everis.rec.service.activity.logs.service.persistence.RecLogPersistence;
import com.everis.rec.service.activity.logs.service.persistence.impl.constants.ACTIVITYPersistenceConstants;

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
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

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
 * The persistence implementation for the rec log service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = RecLogPersistence.class)
public class RecLogPersistenceImpl
	extends BasePersistenceImpl<RecLog> implements RecLogPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>RecLogUtil</code> to access the rec log persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		RecLogImpl.class.getName();

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
	 * Returns all the rec logs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching rec logs
	 */
	@Override
	public List<RecLog> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the rec logs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RecLogModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of rec logs
	 * @param end the upper bound of the range of rec logs (not inclusive)
	 * @return the range of matching rec logs
	 */
	@Override
	public List<RecLog> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the rec logs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RecLogModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of rec logs
	 * @param end the upper bound of the range of rec logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rec logs
	 */
	@Override
	public List<RecLog> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<RecLog> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the rec logs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RecLogModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of rec logs
	 * @param end the upper bound of the range of rec logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching rec logs
	 */
	@Override
	public List<RecLog> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<RecLog> orderByComparator, boolean useFinderCache) {

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

		List<RecLog> list = null;

		if (useFinderCache) {
			list = (List<RecLog>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (RecLog recLog : list) {
					if (!uuid.equals(recLog.getUuid())) {
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

			sb.append(_SQL_SELECT_RECLOG_WHERE);

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
				sb.append(RecLogModelImpl.ORDER_BY_JPQL);
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

				list = (List<RecLog>)QueryUtil.list(
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
	 * Returns the first rec log in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching rec log
	 * @throws NoSuchRecLogException if a matching rec log could not be found
	 */
	@Override
	public RecLog findByUuid_First(
			String uuid, OrderByComparator<RecLog> orderByComparator)
		throws NoSuchRecLogException {

		RecLog recLog = fetchByUuid_First(uuid, orderByComparator);

		if (recLog != null) {
			return recLog;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchRecLogException(sb.toString());
	}

	/**
	 * Returns the first rec log in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching rec log, or <code>null</code> if a matching rec log could not be found
	 */
	@Override
	public RecLog fetchByUuid_First(
		String uuid, OrderByComparator<RecLog> orderByComparator) {

		List<RecLog> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last rec log in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching rec log
	 * @throws NoSuchRecLogException if a matching rec log could not be found
	 */
	@Override
	public RecLog findByUuid_Last(
			String uuid, OrderByComparator<RecLog> orderByComparator)
		throws NoSuchRecLogException {

		RecLog recLog = fetchByUuid_Last(uuid, orderByComparator);

		if (recLog != null) {
			return recLog;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchRecLogException(sb.toString());
	}

	/**
	 * Returns the last rec log in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching rec log, or <code>null</code> if a matching rec log could not be found
	 */
	@Override
	public RecLog fetchByUuid_Last(
		String uuid, OrderByComparator<RecLog> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<RecLog> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the rec logs before and after the current rec log in the ordered set where uuid = &#63;.
	 *
	 * @param activityLogId the primary key of the current rec log
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next rec log
	 * @throws NoSuchRecLogException if a rec log with the primary key could not be found
	 */
	@Override
	public RecLog[] findByUuid_PrevAndNext(
			long activityLogId, String uuid,
			OrderByComparator<RecLog> orderByComparator)
		throws NoSuchRecLogException {

		uuid = Objects.toString(uuid, "");

		RecLog recLog = findByPrimaryKey(activityLogId);

		Session session = null;

		try {
			session = openSession();

			RecLog[] array = new RecLogImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, recLog, uuid, orderByComparator, true);

			array[1] = recLog;

			array[2] = getByUuid_PrevAndNext(
				session, recLog, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected RecLog getByUuid_PrevAndNext(
		Session session, RecLog recLog, String uuid,
		OrderByComparator<RecLog> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_RECLOG_WHERE);

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
			sb.append(RecLogModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(recLog)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<RecLog> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the rec logs where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (RecLog recLog :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(recLog);
		}
	}

	/**
	 * Returns the number of rec logs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching rec logs
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_RECLOG_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_2 = "recLog.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(recLog.uuid IS NULL OR recLog.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;
	private FinderPath _finderPathCountByUUID_G;

	/**
	 * Returns the rec log where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchRecLogException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching rec log
	 * @throws NoSuchRecLogException if a matching rec log could not be found
	 */
	@Override
	public RecLog findByUUID_G(String uuid, long groupId)
		throws NoSuchRecLogException {

		RecLog recLog = fetchByUUID_G(uuid, groupId);

		if (recLog == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("uuid=");
			sb.append(uuid);

			sb.append(", groupId=");
			sb.append(groupId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchRecLogException(sb.toString());
		}

		return recLog;
	}

	/**
	 * Returns the rec log where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching rec log, or <code>null</code> if a matching rec log could not be found
	 */
	@Override
	public RecLog fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the rec log where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching rec log, or <code>null</code> if a matching rec log could not be found
	 */
	@Override
	public RecLog fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {uuid, groupId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByUUID_G, finderArgs, this);
		}

		if (result instanceof RecLog) {
			RecLog recLog = (RecLog)result;

			if (!Objects.equals(uuid, recLog.getUuid()) ||
				(groupId != recLog.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_RECLOG_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(groupId);

				List<RecLog> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByUUID_G, finderArgs, list);
					}
				}
				else {
					RecLog recLog = list.get(0);

					result = recLog;

					cacheResult(recLog);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (RecLog)result;
		}
	}

	/**
	 * Removes the rec log where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the rec log that was removed
	 */
	@Override
	public RecLog removeByUUID_G(String uuid, long groupId)
		throws NoSuchRecLogException {

		RecLog recLog = findByUUID_G(uuid, groupId);

		return remove(recLog);
	}

	/**
	 * Returns the number of rec logs where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching rec logs
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUUID_G;

		Object[] finderArgs = new Object[] {uuid, groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_RECLOG_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(groupId);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_2 =
		"recLog.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(recLog.uuid IS NULL OR recLog.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"recLog.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByClassPK;
	private FinderPath _finderPathWithoutPaginationFindByClassPK;
	private FinderPath _finderPathCountByClassPK;

	/**
	 * Returns all the rec logs where classPK = &#63;.
	 *
	 * @param classPK the class pk
	 * @return the matching rec logs
	 */
	@Override
	public List<RecLog> findByClassPK(Long classPK) {
		return findByClassPK(
			classPK, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the rec logs where classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RecLogModelImpl</code>.
	 * </p>
	 *
	 * @param classPK the class pk
	 * @param start the lower bound of the range of rec logs
	 * @param end the upper bound of the range of rec logs (not inclusive)
	 * @return the range of matching rec logs
	 */
	@Override
	public List<RecLog> findByClassPK(Long classPK, int start, int end) {
		return findByClassPK(classPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the rec logs where classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RecLogModelImpl</code>.
	 * </p>
	 *
	 * @param classPK the class pk
	 * @param start the lower bound of the range of rec logs
	 * @param end the upper bound of the range of rec logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rec logs
	 */
	@Override
	public List<RecLog> findByClassPK(
		Long classPK, int start, int end,
		OrderByComparator<RecLog> orderByComparator) {

		return findByClassPK(classPK, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the rec logs where classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RecLogModelImpl</code>.
	 * </p>
	 *
	 * @param classPK the class pk
	 * @param start the lower bound of the range of rec logs
	 * @param end the upper bound of the range of rec logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching rec logs
	 */
	@Override
	public List<RecLog> findByClassPK(
		Long classPK, int start, int end,
		OrderByComparator<RecLog> orderByComparator, boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByClassPK;
				finderArgs = new Object[] {classPK};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByClassPK;
			finderArgs = new Object[] {classPK, start, end, orderByComparator};
		}

		List<RecLog> list = null;

		if (useFinderCache) {
			list = (List<RecLog>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (RecLog recLog : list) {
					if (!Objects.equals(classPK, recLog.getClassPK())) {
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

			sb.append(_SQL_SELECT_RECLOG_WHERE);

			sb.append(_FINDER_COLUMN_CLASSPK_CLASSPK_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(RecLogModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(classPK.longValue());

				list = (List<RecLog>)QueryUtil.list(
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
	 * Returns the first rec log in the ordered set where classPK = &#63;.
	 *
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching rec log
	 * @throws NoSuchRecLogException if a matching rec log could not be found
	 */
	@Override
	public RecLog findByClassPK_First(
			Long classPK, OrderByComparator<RecLog> orderByComparator)
		throws NoSuchRecLogException {

		RecLog recLog = fetchByClassPK_First(classPK, orderByComparator);

		if (recLog != null) {
			return recLog;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("classPK=");
		sb.append(classPK);

		sb.append("}");

		throw new NoSuchRecLogException(sb.toString());
	}

	/**
	 * Returns the first rec log in the ordered set where classPK = &#63;.
	 *
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching rec log, or <code>null</code> if a matching rec log could not be found
	 */
	@Override
	public RecLog fetchByClassPK_First(
		Long classPK, OrderByComparator<RecLog> orderByComparator) {

		List<RecLog> list = findByClassPK(classPK, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last rec log in the ordered set where classPK = &#63;.
	 *
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching rec log
	 * @throws NoSuchRecLogException if a matching rec log could not be found
	 */
	@Override
	public RecLog findByClassPK_Last(
			Long classPK, OrderByComparator<RecLog> orderByComparator)
		throws NoSuchRecLogException {

		RecLog recLog = fetchByClassPK_Last(classPK, orderByComparator);

		if (recLog != null) {
			return recLog;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("classPK=");
		sb.append(classPK);

		sb.append("}");

		throw new NoSuchRecLogException(sb.toString());
	}

	/**
	 * Returns the last rec log in the ordered set where classPK = &#63;.
	 *
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching rec log, or <code>null</code> if a matching rec log could not be found
	 */
	@Override
	public RecLog fetchByClassPK_Last(
		Long classPK, OrderByComparator<RecLog> orderByComparator) {

		int count = countByClassPK(classPK);

		if (count == 0) {
			return null;
		}

		List<RecLog> list = findByClassPK(
			classPK, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the rec logs before and after the current rec log in the ordered set where classPK = &#63;.
	 *
	 * @param activityLogId the primary key of the current rec log
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next rec log
	 * @throws NoSuchRecLogException if a rec log with the primary key could not be found
	 */
	@Override
	public RecLog[] findByClassPK_PrevAndNext(
			long activityLogId, Long classPK,
			OrderByComparator<RecLog> orderByComparator)
		throws NoSuchRecLogException {

		RecLog recLog = findByPrimaryKey(activityLogId);

		Session session = null;

		try {
			session = openSession();

			RecLog[] array = new RecLogImpl[3];

			array[0] = getByClassPK_PrevAndNext(
				session, recLog, classPK, orderByComparator, true);

			array[1] = recLog;

			array[2] = getByClassPK_PrevAndNext(
				session, recLog, classPK, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected RecLog getByClassPK_PrevAndNext(
		Session session, RecLog recLog, Long classPK,
		OrderByComparator<RecLog> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_RECLOG_WHERE);

		sb.append(_FINDER_COLUMN_CLASSPK_CLASSPK_2);

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
			sb.append(RecLogModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(classPK.longValue());

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(recLog)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<RecLog> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the rec logs where classPK = &#63; from the database.
	 *
	 * @param classPK the class pk
	 */
	@Override
	public void removeByClassPK(Long classPK) {
		for (RecLog recLog :
				findByClassPK(
					classPK, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(recLog);
		}
	}

	/**
	 * Returns the number of rec logs where classPK = &#63;.
	 *
	 * @param classPK the class pk
	 * @return the number of matching rec logs
	 */
	@Override
	public int countByClassPK(Long classPK) {
		FinderPath finderPath = _finderPathCountByClassPK;

		Object[] finderArgs = new Object[] {classPK};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_RECLOG_WHERE);

			sb.append(_FINDER_COLUMN_CLASSPK_CLASSPK_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(classPK.longValue());

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

	private static final String _FINDER_COLUMN_CLASSPK_CLASSPK_2 =
		"recLog.classPK = ?";

	public RecLogPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");
		dbColumnNames.put("type", "type_");

		setDBColumnNames(dbColumnNames);

		setModelClass(RecLog.class);

		setModelImplClass(RecLogImpl.class);
		setModelPKClass(long.class);
	}

	/**
	 * Caches the rec log in the entity cache if it is enabled.
	 *
	 * @param recLog the rec log
	 */
	@Override
	public void cacheResult(RecLog recLog) {
		entityCache.putResult(RecLogImpl.class, recLog.getPrimaryKey(), recLog);

		finderCache.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {recLog.getUuid(), recLog.getGroupId()}, recLog);
	}

	/**
	 * Caches the rec logs in the entity cache if it is enabled.
	 *
	 * @param recLogs the rec logs
	 */
	@Override
	public void cacheResult(List<RecLog> recLogs) {
		for (RecLog recLog : recLogs) {
			if (entityCache.getResult(
					RecLogImpl.class, recLog.getPrimaryKey()) == null) {

				cacheResult(recLog);
			}
		}
	}

	/**
	 * Clears the cache for all rec logs.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(RecLogImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the rec log.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(RecLog recLog) {
		entityCache.removeResult(RecLogImpl.class, recLog);
	}

	@Override
	public void clearCache(List<RecLog> recLogs) {
		for (RecLog recLog : recLogs) {
			entityCache.removeResult(RecLogImpl.class, recLog);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(RecLogImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(RecLogModelImpl recLogModelImpl) {
		Object[] args = new Object[] {
			recLogModelImpl.getUuid(), recLogModelImpl.getGroupId()
		};

		finderCache.putResult(
			_finderPathCountByUUID_G, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByUUID_G, args, recLogModelImpl, false);
	}

	/**
	 * Creates a new rec log with the primary key. Does not add the rec log to the database.
	 *
	 * @param activityLogId the primary key for the new rec log
	 * @return the new rec log
	 */
	@Override
	public RecLog create(long activityLogId) {
		RecLog recLog = new RecLogImpl();

		recLog.setNew(true);
		recLog.setPrimaryKey(activityLogId);

		String uuid = PortalUUIDUtil.generate();

		recLog.setUuid(uuid);

		return recLog;
	}

	/**
	 * Removes the rec log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param activityLogId the primary key of the rec log
	 * @return the rec log that was removed
	 * @throws NoSuchRecLogException if a rec log with the primary key could not be found
	 */
	@Override
	public RecLog remove(long activityLogId) throws NoSuchRecLogException {
		return remove((Serializable)activityLogId);
	}

	/**
	 * Removes the rec log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the rec log
	 * @return the rec log that was removed
	 * @throws NoSuchRecLogException if a rec log with the primary key could not be found
	 */
	@Override
	public RecLog remove(Serializable primaryKey) throws NoSuchRecLogException {
		Session session = null;

		try {
			session = openSession();

			RecLog recLog = (RecLog)session.get(RecLogImpl.class, primaryKey);

			if (recLog == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchRecLogException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(recLog);
		}
		catch (NoSuchRecLogException noSuchEntityException) {
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
	protected RecLog removeImpl(RecLog recLog) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(recLog)) {
				recLog = (RecLog)session.get(
					RecLogImpl.class, recLog.getPrimaryKeyObj());
			}

			if (recLog != null) {
				session.delete(recLog);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (recLog != null) {
			clearCache(recLog);
		}

		return recLog;
	}

	@Override
	public RecLog updateImpl(RecLog recLog) {
		boolean isNew = recLog.isNew();

		if (!(recLog instanceof RecLogModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(recLog.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(recLog);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in recLog proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom RecLog implementation " +
					recLog.getClass());
		}

		RecLogModelImpl recLogModelImpl = (RecLogModelImpl)recLog;

		if (Validator.isNull(recLog.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			recLog.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(recLog);
			}
			else {
				recLog = (RecLog)session.merge(recLog);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(RecLogImpl.class, recLogModelImpl, false, true);

		cacheUniqueFindersCache(recLogModelImpl);

		if (isNew) {
			recLog.setNew(false);
		}

		recLog.resetOriginalValues();

		return recLog;
	}

	/**
	 * Returns the rec log with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the rec log
	 * @return the rec log
	 * @throws NoSuchRecLogException if a rec log with the primary key could not be found
	 */
	@Override
	public RecLog findByPrimaryKey(Serializable primaryKey)
		throws NoSuchRecLogException {

		RecLog recLog = fetchByPrimaryKey(primaryKey);

		if (recLog == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchRecLogException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return recLog;
	}

	/**
	 * Returns the rec log with the primary key or throws a <code>NoSuchRecLogException</code> if it could not be found.
	 *
	 * @param activityLogId the primary key of the rec log
	 * @return the rec log
	 * @throws NoSuchRecLogException if a rec log with the primary key could not be found
	 */
	@Override
	public RecLog findByPrimaryKey(long activityLogId)
		throws NoSuchRecLogException {

		return findByPrimaryKey((Serializable)activityLogId);
	}

	/**
	 * Returns the rec log with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param activityLogId the primary key of the rec log
	 * @return the rec log, or <code>null</code> if a rec log with the primary key could not be found
	 */
	@Override
	public RecLog fetchByPrimaryKey(long activityLogId) {
		return fetchByPrimaryKey((Serializable)activityLogId);
	}

	/**
	 * Returns all the rec logs.
	 *
	 * @return the rec logs
	 */
	@Override
	public List<RecLog> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the rec logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RecLogModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of rec logs
	 * @param end the upper bound of the range of rec logs (not inclusive)
	 * @return the range of rec logs
	 */
	@Override
	public List<RecLog> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the rec logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RecLogModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of rec logs
	 * @param end the upper bound of the range of rec logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of rec logs
	 */
	@Override
	public List<RecLog> findAll(
		int start, int end, OrderByComparator<RecLog> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the rec logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RecLogModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of rec logs
	 * @param end the upper bound of the range of rec logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of rec logs
	 */
	@Override
	public List<RecLog> findAll(
		int start, int end, OrderByComparator<RecLog> orderByComparator,
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

		List<RecLog> list = null;

		if (useFinderCache) {
			list = (List<RecLog>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_RECLOG);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_RECLOG;

				sql = sql.concat(RecLogModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<RecLog>)QueryUtil.list(
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
	 * Removes all the rec logs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (RecLog recLog : findAll()) {
			remove(recLog);
		}
	}

	/**
	 * Returns the number of rec logs.
	 *
	 * @return the number of rec logs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_RECLOG);

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
		return "activityLogId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_RECLOG;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return RecLogModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the rec log persistence.
	 */
	@Activate
	public void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		_argumentsResolverServiceRegistration = _bundleContext.registerService(
			ArgumentsResolver.class, new RecLogModelArgumentsResolver(),
			MapUtil.singletonDictionary(
				"model.class.name", RecLog.class.getName()));

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

		_finderPathFetchByUUID_G = _createFinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "groupId"}, true);

		_finderPathCountByUUID_G = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "groupId"}, false);

		_finderPathWithPaginationFindByClassPK = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByClassPK",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"classPK"}, true);

		_finderPathWithoutPaginationFindByClassPK = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByClassPK",
			new String[] {Long.class.getName()}, new String[] {"classPK"},
			true);

		_finderPathCountByClassPK = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByClassPK",
			new String[] {Long.class.getName()}, new String[] {"classPK"},
			false);
	}

	@Deactivate
	public void deactivate() {
		entityCache.removeCache(RecLogImpl.class.getName());

		_argumentsResolverServiceRegistration.unregister();

		for (ServiceRegistration<FinderPath> serviceRegistration :
				_serviceRegistrations) {

			serviceRegistration.unregister();
		}
	}

	@Override
	@Reference(
		target = ACTIVITYPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = ACTIVITYPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = ACTIVITYPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
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

	private static final String _SQL_SELECT_RECLOG =
		"SELECT recLog FROM RecLog recLog";

	private static final String _SQL_SELECT_RECLOG_WHERE =
		"SELECT recLog FROM RecLog recLog WHERE ";

	private static final String _SQL_COUNT_RECLOG =
		"SELECT COUNT(recLog) FROM RecLog recLog";

	private static final String _SQL_COUNT_RECLOG_WHERE =
		"SELECT COUNT(recLog) FROM RecLog recLog WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "recLog.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No RecLog exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No RecLog exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		RecLogPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid", "type"});

	static {
		try {
			Class.forName(ACTIVITYPersistenceConstants.class.getName());
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

	private static class RecLogModelArgumentsResolver
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

			RecLogModelImpl recLogModelImpl = (RecLogModelImpl)baseModel;

			long columnBitmask = recLogModelImpl.getColumnBitmask();

			if (!checkColumn || (columnBitmask == 0)) {
				return _getValue(recLogModelImpl, columnNames, original);
			}

			Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
				finderPath);

			if (finderPathColumnBitmask == null) {
				finderPathColumnBitmask = 0L;

				for (String columnName : columnNames) {
					finderPathColumnBitmask |= recLogModelImpl.getColumnBitmask(
						columnName);
				}

				_finderPathColumnBitmasksCache.put(
					finderPath, finderPathColumnBitmask);
			}

			if ((columnBitmask & finderPathColumnBitmask) != 0) {
				return _getValue(recLogModelImpl, columnNames, original);
			}

			return null;
		}

		private Object[] _getValue(
			RecLogModelImpl recLogModelImpl, String[] columnNames,
			boolean original) {

			Object[] arguments = new Object[columnNames.length];

			for (int i = 0; i < arguments.length; i++) {
				String columnName = columnNames[i];

				if (original) {
					arguments[i] = recLogModelImpl.getColumnOriginalValue(
						columnName);
				}
				else {
					arguments[i] = recLogModelImpl.getColumnValue(columnName);
				}
			}

			return arguments;
		}

		private static Map<FinderPath, Long> _finderPathColumnBitmasksCache =
			new ConcurrentHashMap<>();

	}

}