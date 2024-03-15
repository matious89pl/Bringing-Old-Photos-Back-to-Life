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

package rec.customnotification.service.persistence.impl;

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

import java.sql.Timestamp;

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

import rec.customnotification.exception.NoSuchNotificationRpaException;
import rec.customnotification.model.NotificationRpa;
import rec.customnotification.model.impl.NotificationRpaImpl;
import rec.customnotification.model.impl.NotificationRpaModelImpl;
import rec.customnotification.service.persistence.NotificationRpaPersistence;
import rec.customnotification.service.persistence.impl.constants.RECPersistenceConstants;

/**
 * The persistence implementation for the notification rpa service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = NotificationRpaPersistence.class)
public class NotificationRpaPersistenceImpl
	extends BasePersistenceImpl<NotificationRpa>
	implements NotificationRpaPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>NotificationRpaUtil</code> to access the notification rpa persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		NotificationRpaImpl.class.getName();

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
	 * Returns all the notification rpas where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching notification rpas
	 */
	@Override
	public List<NotificationRpa> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the notification rpas where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @return the range of matching notification rpas
	 */
	@Override
	public List<NotificationRpa> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the notification rpas where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching notification rpas
	 */
	@Override
	public List<NotificationRpa> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<NotificationRpa> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the notification rpas where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching notification rpas
	 */
	@Override
	public List<NotificationRpa> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<NotificationRpa> orderByComparator,
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

		List<NotificationRpa> list = null;

		if (useFinderCache) {
			list = (List<NotificationRpa>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (NotificationRpa notificationRpa : list) {
					if (!uuid.equals(notificationRpa.getUuid())) {
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

			sb.append(_SQL_SELECT_NOTIFICATIONRPA_WHERE);

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
				sb.append(NotificationRpaModelImpl.ORDER_BY_JPQL);
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

				list = (List<NotificationRpa>)QueryUtil.list(
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
	 * Returns the first notification rpa in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification rpa
	 * @throws NoSuchNotificationRpaException if a matching notification rpa could not be found
	 */
	@Override
	public NotificationRpa findByUuid_First(
			String uuid, OrderByComparator<NotificationRpa> orderByComparator)
		throws NoSuchNotificationRpaException {

		NotificationRpa notificationRpa = fetchByUuid_First(
			uuid, orderByComparator);

		if (notificationRpa != null) {
			return notificationRpa;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchNotificationRpaException(sb.toString());
	}

	/**
	 * Returns the first notification rpa in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	@Override
	public NotificationRpa fetchByUuid_First(
		String uuid, OrderByComparator<NotificationRpa> orderByComparator) {

		List<NotificationRpa> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last notification rpa in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification rpa
	 * @throws NoSuchNotificationRpaException if a matching notification rpa could not be found
	 */
	@Override
	public NotificationRpa findByUuid_Last(
			String uuid, OrderByComparator<NotificationRpa> orderByComparator)
		throws NoSuchNotificationRpaException {

		NotificationRpa notificationRpa = fetchByUuid_Last(
			uuid, orderByComparator);

		if (notificationRpa != null) {
			return notificationRpa;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchNotificationRpaException(sb.toString());
	}

	/**
	 * Returns the last notification rpa in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	@Override
	public NotificationRpa fetchByUuid_Last(
		String uuid, OrderByComparator<NotificationRpa> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<NotificationRpa> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the notification rpas before and after the current notification rpa in the ordered set where uuid = &#63;.
	 *
	 * @param customNotificationId the primary key of the current notification rpa
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next notification rpa
	 * @throws NoSuchNotificationRpaException if a notification rpa with the primary key could not be found
	 */
	@Override
	public NotificationRpa[] findByUuid_PrevAndNext(
			long customNotificationId, String uuid,
			OrderByComparator<NotificationRpa> orderByComparator)
		throws NoSuchNotificationRpaException {

		uuid = Objects.toString(uuid, "");

		NotificationRpa notificationRpa = findByPrimaryKey(
			customNotificationId);

		Session session = null;

		try {
			session = openSession();

			NotificationRpa[] array = new NotificationRpaImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, notificationRpa, uuid, orderByComparator, true);

			array[1] = notificationRpa;

			array[2] = getByUuid_PrevAndNext(
				session, notificationRpa, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected NotificationRpa getByUuid_PrevAndNext(
		Session session, NotificationRpa notificationRpa, String uuid,
		OrderByComparator<NotificationRpa> orderByComparator,
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

		sb.append(_SQL_SELECT_NOTIFICATIONRPA_WHERE);

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
			sb.append(NotificationRpaModelImpl.ORDER_BY_JPQL);
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
						notificationRpa)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<NotificationRpa> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the notification rpas where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (NotificationRpa notificationRpa :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(notificationRpa);
		}
	}

	/**
	 * Returns the number of notification rpas where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching notification rpas
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_NOTIFICATIONRPA_WHERE);

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
		"notificationRpa.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(notificationRpa.uuid IS NULL OR notificationRpa.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;
	private FinderPath _finderPathCountByUUID_G;

	/**
	 * Returns the notification rpa where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchNotificationRpaException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching notification rpa
	 * @throws NoSuchNotificationRpaException if a matching notification rpa could not be found
	 */
	@Override
	public NotificationRpa findByUUID_G(String uuid, long groupId)
		throws NoSuchNotificationRpaException {

		NotificationRpa notificationRpa = fetchByUUID_G(uuid, groupId);

		if (notificationRpa == null) {
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

			throw new NoSuchNotificationRpaException(sb.toString());
		}

		return notificationRpa;
	}

	/**
	 * Returns the notification rpa where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	@Override
	public NotificationRpa fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the notification rpa where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	@Override
	public NotificationRpa fetchByUUID_G(
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

		if (result instanceof NotificationRpa) {
			NotificationRpa notificationRpa = (NotificationRpa)result;

			if (!Objects.equals(uuid, notificationRpa.getUuid()) ||
				(groupId != notificationRpa.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_NOTIFICATIONRPA_WHERE);

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

				List<NotificationRpa> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByUUID_G, finderArgs, list);
					}
				}
				else {
					NotificationRpa notificationRpa = list.get(0);

					result = notificationRpa;

					cacheResult(notificationRpa);
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
			return (NotificationRpa)result;
		}
	}

	/**
	 * Removes the notification rpa where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the notification rpa that was removed
	 */
	@Override
	public NotificationRpa removeByUUID_G(String uuid, long groupId)
		throws NoSuchNotificationRpaException {

		NotificationRpa notificationRpa = findByUUID_G(uuid, groupId);

		return remove(notificationRpa);
	}

	/**
	 * Returns the number of notification rpas where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching notification rpas
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUUID_G;

		Object[] finderArgs = new Object[] {uuid, groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_NOTIFICATIONRPA_WHERE);

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
		"notificationRpa.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(notificationRpa.uuid IS NULL OR notificationRpa.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"notificationRpa.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the notification rpas where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching notification rpas
	 */
	@Override
	public List<NotificationRpa> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the notification rpas where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @return the range of matching notification rpas
	 */
	@Override
	public List<NotificationRpa> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the notification rpas where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching notification rpas
	 */
	@Override
	public List<NotificationRpa> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<NotificationRpa> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the notification rpas where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching notification rpas
	 */
	@Override
	public List<NotificationRpa> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<NotificationRpa> orderByComparator,
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

		List<NotificationRpa> list = null;

		if (useFinderCache) {
			list = (List<NotificationRpa>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (NotificationRpa notificationRpa : list) {
					if (!uuid.equals(notificationRpa.getUuid()) ||
						(companyId != notificationRpa.getCompanyId())) {

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

			sb.append(_SQL_SELECT_NOTIFICATIONRPA_WHERE);

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
				sb.append(NotificationRpaModelImpl.ORDER_BY_JPQL);
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

				list = (List<NotificationRpa>)QueryUtil.list(
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
	 * Returns the first notification rpa in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification rpa
	 * @throws NoSuchNotificationRpaException if a matching notification rpa could not be found
	 */
	@Override
	public NotificationRpa findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<NotificationRpa> orderByComparator)
		throws NoSuchNotificationRpaException {

		NotificationRpa notificationRpa = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (notificationRpa != null) {
			return notificationRpa;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchNotificationRpaException(sb.toString());
	}

	/**
	 * Returns the first notification rpa in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	@Override
	public NotificationRpa fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<NotificationRpa> orderByComparator) {

		List<NotificationRpa> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last notification rpa in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification rpa
	 * @throws NoSuchNotificationRpaException if a matching notification rpa could not be found
	 */
	@Override
	public NotificationRpa findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<NotificationRpa> orderByComparator)
		throws NoSuchNotificationRpaException {

		NotificationRpa notificationRpa = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (notificationRpa != null) {
			return notificationRpa;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchNotificationRpaException(sb.toString());
	}

	/**
	 * Returns the last notification rpa in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	@Override
	public NotificationRpa fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<NotificationRpa> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<NotificationRpa> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the notification rpas before and after the current notification rpa in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param customNotificationId the primary key of the current notification rpa
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next notification rpa
	 * @throws NoSuchNotificationRpaException if a notification rpa with the primary key could not be found
	 */
	@Override
	public NotificationRpa[] findByUuid_C_PrevAndNext(
			long customNotificationId, String uuid, long companyId,
			OrderByComparator<NotificationRpa> orderByComparator)
		throws NoSuchNotificationRpaException {

		uuid = Objects.toString(uuid, "");

		NotificationRpa notificationRpa = findByPrimaryKey(
			customNotificationId);

		Session session = null;

		try {
			session = openSession();

			NotificationRpa[] array = new NotificationRpaImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, notificationRpa, uuid, companyId, orderByComparator,
				true);

			array[1] = notificationRpa;

			array[2] = getByUuid_C_PrevAndNext(
				session, notificationRpa, uuid, companyId, orderByComparator,
				false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected NotificationRpa getByUuid_C_PrevAndNext(
		Session session, NotificationRpa notificationRpa, String uuid,
		long companyId, OrderByComparator<NotificationRpa> orderByComparator,
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

		sb.append(_SQL_SELECT_NOTIFICATIONRPA_WHERE);

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
			sb.append(NotificationRpaModelImpl.ORDER_BY_JPQL);
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
						notificationRpa)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<NotificationRpa> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the notification rpas where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (NotificationRpa notificationRpa :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(notificationRpa);
		}
	}

	/**
	 * Returns the number of notification rpas where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching notification rpas
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_NOTIFICATIONRPA_WHERE);

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
		"notificationRpa.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(notificationRpa.uuid IS NULL OR notificationRpa.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"notificationRpa.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByGroupId;
	private FinderPath _finderPathWithoutPaginationFindByGroupId;
	private FinderPath _finderPathCountByGroupId;

	/**
	 * Returns all the notification rpas where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching notification rpas
	 */
	@Override
	public List<NotificationRpa> findByGroupId(long groupId) {
		return findByGroupId(
			groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the notification rpas where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @return the range of matching notification rpas
	 */
	@Override
	public List<NotificationRpa> findByGroupId(
		long groupId, int start, int end) {

		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the notification rpas where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching notification rpas
	 */
	@Override
	public List<NotificationRpa> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<NotificationRpa> orderByComparator) {

		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the notification rpas where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching notification rpas
	 */
	@Override
	public List<NotificationRpa> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<NotificationRpa> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByGroupId;
				finderArgs = new Object[] {groupId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByGroupId;
			finderArgs = new Object[] {groupId, start, end, orderByComparator};
		}

		List<NotificationRpa> list = null;

		if (useFinderCache) {
			list = (List<NotificationRpa>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (NotificationRpa notificationRpa : list) {
					if (groupId != notificationRpa.getGroupId()) {
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

			sb.append(_SQL_SELECT_NOTIFICATIONRPA_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(NotificationRpaModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				list = (List<NotificationRpa>)QueryUtil.list(
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
	 * Returns the first notification rpa in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification rpa
	 * @throws NoSuchNotificationRpaException if a matching notification rpa could not be found
	 */
	@Override
	public NotificationRpa findByGroupId_First(
			long groupId, OrderByComparator<NotificationRpa> orderByComparator)
		throws NoSuchNotificationRpaException {

		NotificationRpa notificationRpa = fetchByGroupId_First(
			groupId, orderByComparator);

		if (notificationRpa != null) {
			return notificationRpa;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchNotificationRpaException(sb.toString());
	}

	/**
	 * Returns the first notification rpa in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	@Override
	public NotificationRpa fetchByGroupId_First(
		long groupId, OrderByComparator<NotificationRpa> orderByComparator) {

		List<NotificationRpa> list = findByGroupId(
			groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last notification rpa in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification rpa
	 * @throws NoSuchNotificationRpaException if a matching notification rpa could not be found
	 */
	@Override
	public NotificationRpa findByGroupId_Last(
			long groupId, OrderByComparator<NotificationRpa> orderByComparator)
		throws NoSuchNotificationRpaException {

		NotificationRpa notificationRpa = fetchByGroupId_Last(
			groupId, orderByComparator);

		if (notificationRpa != null) {
			return notificationRpa;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchNotificationRpaException(sb.toString());
	}

	/**
	 * Returns the last notification rpa in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	@Override
	public NotificationRpa fetchByGroupId_Last(
		long groupId, OrderByComparator<NotificationRpa> orderByComparator) {

		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<NotificationRpa> list = findByGroupId(
			groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the notification rpas before and after the current notification rpa in the ordered set where groupId = &#63;.
	 *
	 * @param customNotificationId the primary key of the current notification rpa
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next notification rpa
	 * @throws NoSuchNotificationRpaException if a notification rpa with the primary key could not be found
	 */
	@Override
	public NotificationRpa[] findByGroupId_PrevAndNext(
			long customNotificationId, long groupId,
			OrderByComparator<NotificationRpa> orderByComparator)
		throws NoSuchNotificationRpaException {

		NotificationRpa notificationRpa = findByPrimaryKey(
			customNotificationId);

		Session session = null;

		try {
			session = openSession();

			NotificationRpa[] array = new NotificationRpaImpl[3];

			array[0] = getByGroupId_PrevAndNext(
				session, notificationRpa, groupId, orderByComparator, true);

			array[1] = notificationRpa;

			array[2] = getByGroupId_PrevAndNext(
				session, notificationRpa, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected NotificationRpa getByGroupId_PrevAndNext(
		Session session, NotificationRpa notificationRpa, long groupId,
		OrderByComparator<NotificationRpa> orderByComparator,
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

		sb.append(_SQL_SELECT_NOTIFICATIONRPA_WHERE);

		sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

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
			sb.append(NotificationRpaModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(groupId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						notificationRpa)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<NotificationRpa> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the notification rpas where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (NotificationRpa notificationRpa :
				findByGroupId(
					groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(notificationRpa);
		}
	}

	/**
	 * Returns the number of notification rpas where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching notification rpas
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = _finderPathCountByGroupId;

		Object[] finderArgs = new Object[] {groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_NOTIFICATIONRPA_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 =
		"notificationRpa.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByGroupIdPlidPage;
	private FinderPath _finderPathWithoutPaginationFindByGroupIdPlidPage;
	private FinderPath _finderPathCountByGroupIdPlidPage;

	/**
	 * Returns all the notification rpas where groupId = &#63; and plidPage = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @return the matching notification rpas
	 */
	@Override
	public List<NotificationRpa> findByGroupIdPlidPage(
		long groupId, long plidPage) {

		return findByGroupIdPlidPage(
			groupId, plidPage, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the notification rpas where groupId = &#63; and plidPage = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @return the range of matching notification rpas
	 */
	@Override
	public List<NotificationRpa> findByGroupIdPlidPage(
		long groupId, long plidPage, int start, int end) {

		return findByGroupIdPlidPage(groupId, plidPage, start, end, null);
	}

	/**
	 * Returns an ordered range of all the notification rpas where groupId = &#63; and plidPage = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching notification rpas
	 */
	@Override
	public List<NotificationRpa> findByGroupIdPlidPage(
		long groupId, long plidPage, int start, int end,
		OrderByComparator<NotificationRpa> orderByComparator) {

		return findByGroupIdPlidPage(
			groupId, plidPage, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the notification rpas where groupId = &#63; and plidPage = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching notification rpas
	 */
	@Override
	public List<NotificationRpa> findByGroupIdPlidPage(
		long groupId, long plidPage, int start, int end,
		OrderByComparator<NotificationRpa> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByGroupIdPlidPage;
				finderArgs = new Object[] {groupId, plidPage};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByGroupIdPlidPage;
			finderArgs = new Object[] {
				groupId, plidPage, start, end, orderByComparator
			};
		}

		List<NotificationRpa> list = null;

		if (useFinderCache) {
			list = (List<NotificationRpa>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (NotificationRpa notificationRpa : list) {
					if ((groupId != notificationRpa.getGroupId()) ||
						(plidPage != notificationRpa.getPlidPage())) {

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

			sb.append(_SQL_SELECT_NOTIFICATIONRPA_WHERE);

			sb.append(_FINDER_COLUMN_GROUPIDPLIDPAGE_GROUPID_2);

			sb.append(_FINDER_COLUMN_GROUPIDPLIDPAGE_PLIDPAGE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(NotificationRpaModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				queryPos.add(plidPage);

				list = (List<NotificationRpa>)QueryUtil.list(
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
	 * Returns the first notification rpa in the ordered set where groupId = &#63; and plidPage = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification rpa
	 * @throws NoSuchNotificationRpaException if a matching notification rpa could not be found
	 */
	@Override
	public NotificationRpa findByGroupIdPlidPage_First(
			long groupId, long plidPage,
			OrderByComparator<NotificationRpa> orderByComparator)
		throws NoSuchNotificationRpaException {

		NotificationRpa notificationRpa = fetchByGroupIdPlidPage_First(
			groupId, plidPage, orderByComparator);

		if (notificationRpa != null) {
			return notificationRpa;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", plidPage=");
		sb.append(plidPage);

		sb.append("}");

		throw new NoSuchNotificationRpaException(sb.toString());
	}

	/**
	 * Returns the first notification rpa in the ordered set where groupId = &#63; and plidPage = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	@Override
	public NotificationRpa fetchByGroupIdPlidPage_First(
		long groupId, long plidPage,
		OrderByComparator<NotificationRpa> orderByComparator) {

		List<NotificationRpa> list = findByGroupIdPlidPage(
			groupId, plidPage, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last notification rpa in the ordered set where groupId = &#63; and plidPage = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification rpa
	 * @throws NoSuchNotificationRpaException if a matching notification rpa could not be found
	 */
	@Override
	public NotificationRpa findByGroupIdPlidPage_Last(
			long groupId, long plidPage,
			OrderByComparator<NotificationRpa> orderByComparator)
		throws NoSuchNotificationRpaException {

		NotificationRpa notificationRpa = fetchByGroupIdPlidPage_Last(
			groupId, plidPage, orderByComparator);

		if (notificationRpa != null) {
			return notificationRpa;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", plidPage=");
		sb.append(plidPage);

		sb.append("}");

		throw new NoSuchNotificationRpaException(sb.toString());
	}

	/**
	 * Returns the last notification rpa in the ordered set where groupId = &#63; and plidPage = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	@Override
	public NotificationRpa fetchByGroupIdPlidPage_Last(
		long groupId, long plidPage,
		OrderByComparator<NotificationRpa> orderByComparator) {

		int count = countByGroupIdPlidPage(groupId, plidPage);

		if (count == 0) {
			return null;
		}

		List<NotificationRpa> list = findByGroupIdPlidPage(
			groupId, plidPage, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the notification rpas before and after the current notification rpa in the ordered set where groupId = &#63; and plidPage = &#63;.
	 *
	 * @param customNotificationId the primary key of the current notification rpa
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next notification rpa
	 * @throws NoSuchNotificationRpaException if a notification rpa with the primary key could not be found
	 */
	@Override
	public NotificationRpa[] findByGroupIdPlidPage_PrevAndNext(
			long customNotificationId, long groupId, long plidPage,
			OrderByComparator<NotificationRpa> orderByComparator)
		throws NoSuchNotificationRpaException {

		NotificationRpa notificationRpa = findByPrimaryKey(
			customNotificationId);

		Session session = null;

		try {
			session = openSession();

			NotificationRpa[] array = new NotificationRpaImpl[3];

			array[0] = getByGroupIdPlidPage_PrevAndNext(
				session, notificationRpa, groupId, plidPage, orderByComparator,
				true);

			array[1] = notificationRpa;

			array[2] = getByGroupIdPlidPage_PrevAndNext(
				session, notificationRpa, groupId, plidPage, orderByComparator,
				false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected NotificationRpa getByGroupIdPlidPage_PrevAndNext(
		Session session, NotificationRpa notificationRpa, long groupId,
		long plidPage, OrderByComparator<NotificationRpa> orderByComparator,
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

		sb.append(_SQL_SELECT_NOTIFICATIONRPA_WHERE);

		sb.append(_FINDER_COLUMN_GROUPIDPLIDPAGE_GROUPID_2);

		sb.append(_FINDER_COLUMN_GROUPIDPLIDPAGE_PLIDPAGE_2);

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
			sb.append(NotificationRpaModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(groupId);

		queryPos.add(plidPage);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						notificationRpa)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<NotificationRpa> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the notification rpas where groupId = &#63; and plidPage = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 */
	@Override
	public void removeByGroupIdPlidPage(long groupId, long plidPage) {
		for (NotificationRpa notificationRpa :
				findByGroupIdPlidPage(
					groupId, plidPage, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(notificationRpa);
		}
	}

	/**
	 * Returns the number of notification rpas where groupId = &#63; and plidPage = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @return the number of matching notification rpas
	 */
	@Override
	public int countByGroupIdPlidPage(long groupId, long plidPage) {
		FinderPath finderPath = _finderPathCountByGroupIdPlidPage;

		Object[] finderArgs = new Object[] {groupId, plidPage};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_NOTIFICATIONRPA_WHERE);

			sb.append(_FINDER_COLUMN_GROUPIDPLIDPAGE_GROUPID_2);

			sb.append(_FINDER_COLUMN_GROUPIDPLIDPAGE_PLIDPAGE_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				queryPos.add(plidPage);

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

	private static final String _FINDER_COLUMN_GROUPIDPLIDPAGE_GROUPID_2 =
		"notificationRpa.groupId = ? AND ";

	private static final String _FINDER_COLUMN_GROUPIDPLIDPAGE_PLIDPAGE_2 =
		"notificationRpa.plidPage = ?";

	private FinderPath _finderPathWithPaginationFindByNotificationTitle;
	private FinderPath _finderPathWithoutPaginationFindByNotificationTitle;
	private FinderPath _finderPathCountByNotificationTitle;

	/**
	 * Returns all the notification rpas where groupId = &#63; and plidPage = &#63; and notificationTitle = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationTitle the notification title
	 * @return the matching notification rpas
	 */
	@Override
	public List<NotificationRpa> findByNotificationTitle(
		long groupId, long plidPage, String notificationTitle) {

		return findByNotificationTitle(
			groupId, plidPage, notificationTitle, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the notification rpas where groupId = &#63; and plidPage = &#63; and notificationTitle = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationTitle the notification title
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @return the range of matching notification rpas
	 */
	@Override
	public List<NotificationRpa> findByNotificationTitle(
		long groupId, long plidPage, String notificationTitle, int start,
		int end) {

		return findByNotificationTitle(
			groupId, plidPage, notificationTitle, start, end, null);
	}

	/**
	 * Returns an ordered range of all the notification rpas where groupId = &#63; and plidPage = &#63; and notificationTitle = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationTitle the notification title
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching notification rpas
	 */
	@Override
	public List<NotificationRpa> findByNotificationTitle(
		long groupId, long plidPage, String notificationTitle, int start,
		int end, OrderByComparator<NotificationRpa> orderByComparator) {

		return findByNotificationTitle(
			groupId, plidPage, notificationTitle, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the notification rpas where groupId = &#63; and plidPage = &#63; and notificationTitle = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationTitle the notification title
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching notification rpas
	 */
	@Override
	public List<NotificationRpa> findByNotificationTitle(
		long groupId, long plidPage, String notificationTitle, int start,
		int end, OrderByComparator<NotificationRpa> orderByComparator,
		boolean useFinderCache) {

		notificationTitle = Objects.toString(notificationTitle, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByNotificationTitle;
				finderArgs = new Object[] {
					groupId, plidPage, notificationTitle
				};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByNotificationTitle;
			finderArgs = new Object[] {
				groupId, plidPage, notificationTitle, start, end,
				orderByComparator
			};
		}

		List<NotificationRpa> list = null;

		if (useFinderCache) {
			list = (List<NotificationRpa>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (NotificationRpa notificationRpa : list) {
					if ((groupId != notificationRpa.getGroupId()) ||
						(plidPage != notificationRpa.getPlidPage()) ||
						!notificationTitle.equals(
							notificationRpa.getNotificationTitle())) {

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
					5 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(5);
			}

			sb.append(_SQL_SELECT_NOTIFICATIONRPA_WHERE);

			sb.append(_FINDER_COLUMN_NOTIFICATIONTITLE_GROUPID_2);

			sb.append(_FINDER_COLUMN_NOTIFICATIONTITLE_PLIDPAGE_2);

			boolean bindNotificationTitle = false;

			if (notificationTitle.isEmpty()) {
				sb.append(_FINDER_COLUMN_NOTIFICATIONTITLE_NOTIFICATIONTITLE_3);
			}
			else {
				bindNotificationTitle = true;

				sb.append(_FINDER_COLUMN_NOTIFICATIONTITLE_NOTIFICATIONTITLE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(NotificationRpaModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				queryPos.add(plidPage);

				if (bindNotificationTitle) {
					queryPos.add(notificationTitle);
				}

				list = (List<NotificationRpa>)QueryUtil.list(
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
	 * Returns the first notification rpa in the ordered set where groupId = &#63; and plidPage = &#63; and notificationTitle = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationTitle the notification title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification rpa
	 * @throws NoSuchNotificationRpaException if a matching notification rpa could not be found
	 */
	@Override
	public NotificationRpa findByNotificationTitle_First(
			long groupId, long plidPage, String notificationTitle,
			OrderByComparator<NotificationRpa> orderByComparator)
		throws NoSuchNotificationRpaException {

		NotificationRpa notificationRpa = fetchByNotificationTitle_First(
			groupId, plidPage, notificationTitle, orderByComparator);

		if (notificationRpa != null) {
			return notificationRpa;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", plidPage=");
		sb.append(plidPage);

		sb.append(", notificationTitle=");
		sb.append(notificationTitle);

		sb.append("}");

		throw new NoSuchNotificationRpaException(sb.toString());
	}

	/**
	 * Returns the first notification rpa in the ordered set where groupId = &#63; and plidPage = &#63; and notificationTitle = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationTitle the notification title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	@Override
	public NotificationRpa fetchByNotificationTitle_First(
		long groupId, long plidPage, String notificationTitle,
		OrderByComparator<NotificationRpa> orderByComparator) {

		List<NotificationRpa> list = findByNotificationTitle(
			groupId, plidPage, notificationTitle, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last notification rpa in the ordered set where groupId = &#63; and plidPage = &#63; and notificationTitle = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationTitle the notification title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification rpa
	 * @throws NoSuchNotificationRpaException if a matching notification rpa could not be found
	 */
	@Override
	public NotificationRpa findByNotificationTitle_Last(
			long groupId, long plidPage, String notificationTitle,
			OrderByComparator<NotificationRpa> orderByComparator)
		throws NoSuchNotificationRpaException {

		NotificationRpa notificationRpa = fetchByNotificationTitle_Last(
			groupId, plidPage, notificationTitle, orderByComparator);

		if (notificationRpa != null) {
			return notificationRpa;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", plidPage=");
		sb.append(plidPage);

		sb.append(", notificationTitle=");
		sb.append(notificationTitle);

		sb.append("}");

		throw new NoSuchNotificationRpaException(sb.toString());
	}

	/**
	 * Returns the last notification rpa in the ordered set where groupId = &#63; and plidPage = &#63; and notificationTitle = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationTitle the notification title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	@Override
	public NotificationRpa fetchByNotificationTitle_Last(
		long groupId, long plidPage, String notificationTitle,
		OrderByComparator<NotificationRpa> orderByComparator) {

		int count = countByNotificationTitle(
			groupId, plidPage, notificationTitle);

		if (count == 0) {
			return null;
		}

		List<NotificationRpa> list = findByNotificationTitle(
			groupId, plidPage, notificationTitle, count - 1, count,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the notification rpas before and after the current notification rpa in the ordered set where groupId = &#63; and plidPage = &#63; and notificationTitle = &#63;.
	 *
	 * @param customNotificationId the primary key of the current notification rpa
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationTitle the notification title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next notification rpa
	 * @throws NoSuchNotificationRpaException if a notification rpa with the primary key could not be found
	 */
	@Override
	public NotificationRpa[] findByNotificationTitle_PrevAndNext(
			long customNotificationId, long groupId, long plidPage,
			String notificationTitle,
			OrderByComparator<NotificationRpa> orderByComparator)
		throws NoSuchNotificationRpaException {

		notificationTitle = Objects.toString(notificationTitle, "");

		NotificationRpa notificationRpa = findByPrimaryKey(
			customNotificationId);

		Session session = null;

		try {
			session = openSession();

			NotificationRpa[] array = new NotificationRpaImpl[3];

			array[0] = getByNotificationTitle_PrevAndNext(
				session, notificationRpa, groupId, plidPage, notificationTitle,
				orderByComparator, true);

			array[1] = notificationRpa;

			array[2] = getByNotificationTitle_PrevAndNext(
				session, notificationRpa, groupId, plidPage, notificationTitle,
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

	protected NotificationRpa getByNotificationTitle_PrevAndNext(
		Session session, NotificationRpa notificationRpa, long groupId,
		long plidPage, String notificationTitle,
		OrderByComparator<NotificationRpa> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				6 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(5);
		}

		sb.append(_SQL_SELECT_NOTIFICATIONRPA_WHERE);

		sb.append(_FINDER_COLUMN_NOTIFICATIONTITLE_GROUPID_2);

		sb.append(_FINDER_COLUMN_NOTIFICATIONTITLE_PLIDPAGE_2);

		boolean bindNotificationTitle = false;

		if (notificationTitle.isEmpty()) {
			sb.append(_FINDER_COLUMN_NOTIFICATIONTITLE_NOTIFICATIONTITLE_3);
		}
		else {
			bindNotificationTitle = true;

			sb.append(_FINDER_COLUMN_NOTIFICATIONTITLE_NOTIFICATIONTITLE_2);
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
			sb.append(NotificationRpaModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(groupId);

		queryPos.add(plidPage);

		if (bindNotificationTitle) {
			queryPos.add(notificationTitle);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						notificationRpa)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<NotificationRpa> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the notification rpas where groupId = &#63; and plidPage = &#63; and notificationTitle = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationTitle the notification title
	 */
	@Override
	public void removeByNotificationTitle(
		long groupId, long plidPage, String notificationTitle) {

		for (NotificationRpa notificationRpa :
				findByNotificationTitle(
					groupId, plidPage, notificationTitle, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(notificationRpa);
		}
	}

	/**
	 * Returns the number of notification rpas where groupId = &#63; and plidPage = &#63; and notificationTitle = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationTitle the notification title
	 * @return the number of matching notification rpas
	 */
	@Override
	public int countByNotificationTitle(
		long groupId, long plidPage, String notificationTitle) {

		notificationTitle = Objects.toString(notificationTitle, "");

		FinderPath finderPath = _finderPathCountByNotificationTitle;

		Object[] finderArgs = new Object[] {
			groupId, plidPage, notificationTitle
		};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_COUNT_NOTIFICATIONRPA_WHERE);

			sb.append(_FINDER_COLUMN_NOTIFICATIONTITLE_GROUPID_2);

			sb.append(_FINDER_COLUMN_NOTIFICATIONTITLE_PLIDPAGE_2);

			boolean bindNotificationTitle = false;

			if (notificationTitle.isEmpty()) {
				sb.append(_FINDER_COLUMN_NOTIFICATIONTITLE_NOTIFICATIONTITLE_3);
			}
			else {
				bindNotificationTitle = true;

				sb.append(_FINDER_COLUMN_NOTIFICATIONTITLE_NOTIFICATIONTITLE_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				queryPos.add(plidPage);

				if (bindNotificationTitle) {
					queryPos.add(notificationTitle);
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

	private static final String _FINDER_COLUMN_NOTIFICATIONTITLE_GROUPID_2 =
		"notificationRpa.groupId = ? AND ";

	private static final String _FINDER_COLUMN_NOTIFICATIONTITLE_PLIDPAGE_2 =
		"notificationRpa.plidPage = ? AND ";

	private static final String
		_FINDER_COLUMN_NOTIFICATIONTITLE_NOTIFICATIONTITLE_2 =
			"notificationRpa.notificationTitle = ?";

	private static final String
		_FINDER_COLUMN_NOTIFICATIONTITLE_NOTIFICATIONTITLE_3 =
			"(notificationRpa.notificationTitle IS NULL OR notificationRpa.notificationTitle = '')";

	private FinderPath _finderPathWithPaginationFindByNotificationBody;
	private FinderPath _finderPathWithoutPaginationFindByNotificationBody;
	private FinderPath _finderPathCountByNotificationBody;

	/**
	 * Returns all the notification rpas where groupId = &#63; and plidPage = &#63; and notificationBody = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationBody the notification body
	 * @return the matching notification rpas
	 */
	@Override
	public List<NotificationRpa> findByNotificationBody(
		long groupId, long plidPage, String notificationBody) {

		return findByNotificationBody(
			groupId, plidPage, notificationBody, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the notification rpas where groupId = &#63; and plidPage = &#63; and notificationBody = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationBody the notification body
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @return the range of matching notification rpas
	 */
	@Override
	public List<NotificationRpa> findByNotificationBody(
		long groupId, long plidPage, String notificationBody, int start,
		int end) {

		return findByNotificationBody(
			groupId, plidPage, notificationBody, start, end, null);
	}

	/**
	 * Returns an ordered range of all the notification rpas where groupId = &#63; and plidPage = &#63; and notificationBody = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationBody the notification body
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching notification rpas
	 */
	@Override
	public List<NotificationRpa> findByNotificationBody(
		long groupId, long plidPage, String notificationBody, int start,
		int end, OrderByComparator<NotificationRpa> orderByComparator) {

		return findByNotificationBody(
			groupId, plidPage, notificationBody, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the notification rpas where groupId = &#63; and plidPage = &#63; and notificationBody = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationBody the notification body
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching notification rpas
	 */
	@Override
	public List<NotificationRpa> findByNotificationBody(
		long groupId, long plidPage, String notificationBody, int start,
		int end, OrderByComparator<NotificationRpa> orderByComparator,
		boolean useFinderCache) {

		notificationBody = Objects.toString(notificationBody, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByNotificationBody;
				finderArgs = new Object[] {groupId, plidPage, notificationBody};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByNotificationBody;
			finderArgs = new Object[] {
				groupId, plidPage, notificationBody, start, end,
				orderByComparator
			};
		}

		List<NotificationRpa> list = null;

		if (useFinderCache) {
			list = (List<NotificationRpa>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (NotificationRpa notificationRpa : list) {
					if ((groupId != notificationRpa.getGroupId()) ||
						(plidPage != notificationRpa.getPlidPage()) ||
						!notificationBody.equals(
							notificationRpa.getNotificationBody())) {

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
					5 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(5);
			}

			sb.append(_SQL_SELECT_NOTIFICATIONRPA_WHERE);

			sb.append(_FINDER_COLUMN_NOTIFICATIONBODY_GROUPID_2);

			sb.append(_FINDER_COLUMN_NOTIFICATIONBODY_PLIDPAGE_2);

			boolean bindNotificationBody = false;

			if (notificationBody.isEmpty()) {
				sb.append(_FINDER_COLUMN_NOTIFICATIONBODY_NOTIFICATIONBODY_3);
			}
			else {
				bindNotificationBody = true;

				sb.append(_FINDER_COLUMN_NOTIFICATIONBODY_NOTIFICATIONBODY_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(NotificationRpaModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				queryPos.add(plidPage);

				if (bindNotificationBody) {
					queryPos.add(notificationBody);
				}

				list = (List<NotificationRpa>)QueryUtil.list(
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
	 * Returns the first notification rpa in the ordered set where groupId = &#63; and plidPage = &#63; and notificationBody = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationBody the notification body
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification rpa
	 * @throws NoSuchNotificationRpaException if a matching notification rpa could not be found
	 */
	@Override
	public NotificationRpa findByNotificationBody_First(
			long groupId, long plidPage, String notificationBody,
			OrderByComparator<NotificationRpa> orderByComparator)
		throws NoSuchNotificationRpaException {

		NotificationRpa notificationRpa = fetchByNotificationBody_First(
			groupId, plidPage, notificationBody, orderByComparator);

		if (notificationRpa != null) {
			return notificationRpa;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", plidPage=");
		sb.append(plidPage);

		sb.append(", notificationBody=");
		sb.append(notificationBody);

		sb.append("}");

		throw new NoSuchNotificationRpaException(sb.toString());
	}

	/**
	 * Returns the first notification rpa in the ordered set where groupId = &#63; and plidPage = &#63; and notificationBody = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationBody the notification body
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	@Override
	public NotificationRpa fetchByNotificationBody_First(
		long groupId, long plidPage, String notificationBody,
		OrderByComparator<NotificationRpa> orderByComparator) {

		List<NotificationRpa> list = findByNotificationBody(
			groupId, plidPage, notificationBody, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last notification rpa in the ordered set where groupId = &#63; and plidPage = &#63; and notificationBody = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationBody the notification body
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification rpa
	 * @throws NoSuchNotificationRpaException if a matching notification rpa could not be found
	 */
	@Override
	public NotificationRpa findByNotificationBody_Last(
			long groupId, long plidPage, String notificationBody,
			OrderByComparator<NotificationRpa> orderByComparator)
		throws NoSuchNotificationRpaException {

		NotificationRpa notificationRpa = fetchByNotificationBody_Last(
			groupId, plidPage, notificationBody, orderByComparator);

		if (notificationRpa != null) {
			return notificationRpa;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", plidPage=");
		sb.append(plidPage);

		sb.append(", notificationBody=");
		sb.append(notificationBody);

		sb.append("}");

		throw new NoSuchNotificationRpaException(sb.toString());
	}

	/**
	 * Returns the last notification rpa in the ordered set where groupId = &#63; and plidPage = &#63; and notificationBody = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationBody the notification body
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	@Override
	public NotificationRpa fetchByNotificationBody_Last(
		long groupId, long plidPage, String notificationBody,
		OrderByComparator<NotificationRpa> orderByComparator) {

		int count = countByNotificationBody(
			groupId, plidPage, notificationBody);

		if (count == 0) {
			return null;
		}

		List<NotificationRpa> list = findByNotificationBody(
			groupId, plidPage, notificationBody, count - 1, count,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the notification rpas before and after the current notification rpa in the ordered set where groupId = &#63; and plidPage = &#63; and notificationBody = &#63;.
	 *
	 * @param customNotificationId the primary key of the current notification rpa
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationBody the notification body
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next notification rpa
	 * @throws NoSuchNotificationRpaException if a notification rpa with the primary key could not be found
	 */
	@Override
	public NotificationRpa[] findByNotificationBody_PrevAndNext(
			long customNotificationId, long groupId, long plidPage,
			String notificationBody,
			OrderByComparator<NotificationRpa> orderByComparator)
		throws NoSuchNotificationRpaException {

		notificationBody = Objects.toString(notificationBody, "");

		NotificationRpa notificationRpa = findByPrimaryKey(
			customNotificationId);

		Session session = null;

		try {
			session = openSession();

			NotificationRpa[] array = new NotificationRpaImpl[3];

			array[0] = getByNotificationBody_PrevAndNext(
				session, notificationRpa, groupId, plidPage, notificationBody,
				orderByComparator, true);

			array[1] = notificationRpa;

			array[2] = getByNotificationBody_PrevAndNext(
				session, notificationRpa, groupId, plidPage, notificationBody,
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

	protected NotificationRpa getByNotificationBody_PrevAndNext(
		Session session, NotificationRpa notificationRpa, long groupId,
		long plidPage, String notificationBody,
		OrderByComparator<NotificationRpa> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				6 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(5);
		}

		sb.append(_SQL_SELECT_NOTIFICATIONRPA_WHERE);

		sb.append(_FINDER_COLUMN_NOTIFICATIONBODY_GROUPID_2);

		sb.append(_FINDER_COLUMN_NOTIFICATIONBODY_PLIDPAGE_2);

		boolean bindNotificationBody = false;

		if (notificationBody.isEmpty()) {
			sb.append(_FINDER_COLUMN_NOTIFICATIONBODY_NOTIFICATIONBODY_3);
		}
		else {
			bindNotificationBody = true;

			sb.append(_FINDER_COLUMN_NOTIFICATIONBODY_NOTIFICATIONBODY_2);
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
			sb.append(NotificationRpaModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(groupId);

		queryPos.add(plidPage);

		if (bindNotificationBody) {
			queryPos.add(notificationBody);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						notificationRpa)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<NotificationRpa> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the notification rpas where groupId = &#63; and plidPage = &#63; and notificationBody = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationBody the notification body
	 */
	@Override
	public void removeByNotificationBody(
		long groupId, long plidPage, String notificationBody) {

		for (NotificationRpa notificationRpa :
				findByNotificationBody(
					groupId, plidPage, notificationBody, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(notificationRpa);
		}
	}

	/**
	 * Returns the number of notification rpas where groupId = &#63; and plidPage = &#63; and notificationBody = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param notificationBody the notification body
	 * @return the number of matching notification rpas
	 */
	@Override
	public int countByNotificationBody(
		long groupId, long plidPage, String notificationBody) {

		notificationBody = Objects.toString(notificationBody, "");

		FinderPath finderPath = _finderPathCountByNotificationBody;

		Object[] finderArgs = new Object[] {
			groupId, plidPage, notificationBody
		};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_COUNT_NOTIFICATIONRPA_WHERE);

			sb.append(_FINDER_COLUMN_NOTIFICATIONBODY_GROUPID_2);

			sb.append(_FINDER_COLUMN_NOTIFICATIONBODY_PLIDPAGE_2);

			boolean bindNotificationBody = false;

			if (notificationBody.isEmpty()) {
				sb.append(_FINDER_COLUMN_NOTIFICATIONBODY_NOTIFICATIONBODY_3);
			}
			else {
				bindNotificationBody = true;

				sb.append(_FINDER_COLUMN_NOTIFICATIONBODY_NOTIFICATIONBODY_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				queryPos.add(plidPage);

				if (bindNotificationBody) {
					queryPos.add(notificationBody);
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

	private static final String _FINDER_COLUMN_NOTIFICATIONBODY_GROUPID_2 =
		"notificationRpa.groupId = ? AND ";

	private static final String _FINDER_COLUMN_NOTIFICATIONBODY_PLIDPAGE_2 =
		"notificationRpa.plidPage = ? AND ";

	private static final String
		_FINDER_COLUMN_NOTIFICATIONBODY_NOTIFICATIONBODY_2 =
			"notificationRpa.notificationBody = ?";

	private static final String
		_FINDER_COLUMN_NOTIFICATIONBODY_NOTIFICATIONBODY_3 =
			"(notificationRpa.notificationBody IS NULL OR notificationRpa.notificationBody = '')";

	private FinderPath _finderPathWithPaginationFindByCreateDate;
	private FinderPath _finderPathWithoutPaginationFindByCreateDate;
	private FinderPath _finderPathCountByCreateDate;

	/**
	 * Returns all the notification rpas where groupId = &#63; and plidPage = &#63; and createDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param createDate the create date
	 * @return the matching notification rpas
	 */
	@Override
	public List<NotificationRpa> findByCreateDate(
		long groupId, long plidPage, Date createDate) {

		return findByCreateDate(
			groupId, plidPage, createDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the notification rpas where groupId = &#63; and plidPage = &#63; and createDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param createDate the create date
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @return the range of matching notification rpas
	 */
	@Override
	public List<NotificationRpa> findByCreateDate(
		long groupId, long plidPage, Date createDate, int start, int end) {

		return findByCreateDate(
			groupId, plidPage, createDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the notification rpas where groupId = &#63; and plidPage = &#63; and createDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param createDate the create date
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching notification rpas
	 */
	@Override
	public List<NotificationRpa> findByCreateDate(
		long groupId, long plidPage, Date createDate, int start, int end,
		OrderByComparator<NotificationRpa> orderByComparator) {

		return findByCreateDate(
			groupId, plidPage, createDate, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the notification rpas where groupId = &#63; and plidPage = &#63; and createDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param createDate the create date
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching notification rpas
	 */
	@Override
	public List<NotificationRpa> findByCreateDate(
		long groupId, long plidPage, Date createDate, int start, int end,
		OrderByComparator<NotificationRpa> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByCreateDate;
				finderArgs = new Object[] {
					groupId, plidPage, _getTime(createDate)
				};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByCreateDate;
			finderArgs = new Object[] {
				groupId, plidPage, _getTime(createDate), start, end,
				orderByComparator
			};
		}

		List<NotificationRpa> list = null;

		if (useFinderCache) {
			list = (List<NotificationRpa>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (NotificationRpa notificationRpa : list) {
					if ((groupId != notificationRpa.getGroupId()) ||
						(plidPage != notificationRpa.getPlidPage()) ||
						!Objects.equals(
							createDate, notificationRpa.getCreateDate())) {

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
					5 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(5);
			}

			sb.append(_SQL_SELECT_NOTIFICATIONRPA_WHERE);

			sb.append(_FINDER_COLUMN_CREATEDATE_GROUPID_2);

			sb.append(_FINDER_COLUMN_CREATEDATE_PLIDPAGE_2);

			boolean bindCreateDate = false;

			if (createDate == null) {
				sb.append(_FINDER_COLUMN_CREATEDATE_CREATEDATE_1);
			}
			else {
				bindCreateDate = true;

				sb.append(_FINDER_COLUMN_CREATEDATE_CREATEDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(NotificationRpaModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				queryPos.add(plidPage);

				if (bindCreateDate) {
					queryPos.add(new Timestamp(createDate.getTime()));
				}

				list = (List<NotificationRpa>)QueryUtil.list(
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
	 * Returns the first notification rpa in the ordered set where groupId = &#63; and plidPage = &#63; and createDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification rpa
	 * @throws NoSuchNotificationRpaException if a matching notification rpa could not be found
	 */
	@Override
	public NotificationRpa findByCreateDate_First(
			long groupId, long plidPage, Date createDate,
			OrderByComparator<NotificationRpa> orderByComparator)
		throws NoSuchNotificationRpaException {

		NotificationRpa notificationRpa = fetchByCreateDate_First(
			groupId, plidPage, createDate, orderByComparator);

		if (notificationRpa != null) {
			return notificationRpa;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", plidPage=");
		sb.append(plidPage);

		sb.append(", createDate=");
		sb.append(createDate);

		sb.append("}");

		throw new NoSuchNotificationRpaException(sb.toString());
	}

	/**
	 * Returns the first notification rpa in the ordered set where groupId = &#63; and plidPage = &#63; and createDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	@Override
	public NotificationRpa fetchByCreateDate_First(
		long groupId, long plidPage, Date createDate,
		OrderByComparator<NotificationRpa> orderByComparator) {

		List<NotificationRpa> list = findByCreateDate(
			groupId, plidPage, createDate, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last notification rpa in the ordered set where groupId = &#63; and plidPage = &#63; and createDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification rpa
	 * @throws NoSuchNotificationRpaException if a matching notification rpa could not be found
	 */
	@Override
	public NotificationRpa findByCreateDate_Last(
			long groupId, long plidPage, Date createDate,
			OrderByComparator<NotificationRpa> orderByComparator)
		throws NoSuchNotificationRpaException {

		NotificationRpa notificationRpa = fetchByCreateDate_Last(
			groupId, plidPage, createDate, orderByComparator);

		if (notificationRpa != null) {
			return notificationRpa;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", plidPage=");
		sb.append(plidPage);

		sb.append(", createDate=");
		sb.append(createDate);

		sb.append("}");

		throw new NoSuchNotificationRpaException(sb.toString());
	}

	/**
	 * Returns the last notification rpa in the ordered set where groupId = &#63; and plidPage = &#63; and createDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	@Override
	public NotificationRpa fetchByCreateDate_Last(
		long groupId, long plidPage, Date createDate,
		OrderByComparator<NotificationRpa> orderByComparator) {

		int count = countByCreateDate(groupId, plidPage, createDate);

		if (count == 0) {
			return null;
		}

		List<NotificationRpa> list = findByCreateDate(
			groupId, plidPage, createDate, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the notification rpas before and after the current notification rpa in the ordered set where groupId = &#63; and plidPage = &#63; and createDate = &#63;.
	 *
	 * @param customNotificationId the primary key of the current notification rpa
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next notification rpa
	 * @throws NoSuchNotificationRpaException if a notification rpa with the primary key could not be found
	 */
	@Override
	public NotificationRpa[] findByCreateDate_PrevAndNext(
			long customNotificationId, long groupId, long plidPage,
			Date createDate,
			OrderByComparator<NotificationRpa> orderByComparator)
		throws NoSuchNotificationRpaException {

		NotificationRpa notificationRpa = findByPrimaryKey(
			customNotificationId);

		Session session = null;

		try {
			session = openSession();

			NotificationRpa[] array = new NotificationRpaImpl[3];

			array[0] = getByCreateDate_PrevAndNext(
				session, notificationRpa, groupId, plidPage, createDate,
				orderByComparator, true);

			array[1] = notificationRpa;

			array[2] = getByCreateDate_PrevAndNext(
				session, notificationRpa, groupId, plidPage, createDate,
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

	protected NotificationRpa getByCreateDate_PrevAndNext(
		Session session, NotificationRpa notificationRpa, long groupId,
		long plidPage, Date createDate,
		OrderByComparator<NotificationRpa> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				6 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(5);
		}

		sb.append(_SQL_SELECT_NOTIFICATIONRPA_WHERE);

		sb.append(_FINDER_COLUMN_CREATEDATE_GROUPID_2);

		sb.append(_FINDER_COLUMN_CREATEDATE_PLIDPAGE_2);

		boolean bindCreateDate = false;

		if (createDate == null) {
			sb.append(_FINDER_COLUMN_CREATEDATE_CREATEDATE_1);
		}
		else {
			bindCreateDate = true;

			sb.append(_FINDER_COLUMN_CREATEDATE_CREATEDATE_2);
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
			sb.append(NotificationRpaModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(groupId);

		queryPos.add(plidPage);

		if (bindCreateDate) {
			queryPos.add(new Timestamp(createDate.getTime()));
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						notificationRpa)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<NotificationRpa> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the notification rpas where groupId = &#63; and plidPage = &#63; and createDate = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param createDate the create date
	 */
	@Override
	public void removeByCreateDate(
		long groupId, long plidPage, Date createDate) {

		for (NotificationRpa notificationRpa :
				findByCreateDate(
					groupId, plidPage, createDate, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(notificationRpa);
		}
	}

	/**
	 * Returns the number of notification rpas where groupId = &#63; and plidPage = &#63; and createDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param createDate the create date
	 * @return the number of matching notification rpas
	 */
	@Override
	public int countByCreateDate(long groupId, long plidPage, Date createDate) {
		FinderPath finderPath = _finderPathCountByCreateDate;

		Object[] finderArgs = new Object[] {
			groupId, plidPage, _getTime(createDate)
		};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_COUNT_NOTIFICATIONRPA_WHERE);

			sb.append(_FINDER_COLUMN_CREATEDATE_GROUPID_2);

			sb.append(_FINDER_COLUMN_CREATEDATE_PLIDPAGE_2);

			boolean bindCreateDate = false;

			if (createDate == null) {
				sb.append(_FINDER_COLUMN_CREATEDATE_CREATEDATE_1);
			}
			else {
				bindCreateDate = true;

				sb.append(_FINDER_COLUMN_CREATEDATE_CREATEDATE_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				queryPos.add(plidPage);

				if (bindCreateDate) {
					queryPos.add(new Timestamp(createDate.getTime()));
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

	private static final String _FINDER_COLUMN_CREATEDATE_GROUPID_2 =
		"notificationRpa.groupId = ? AND ";

	private static final String _FINDER_COLUMN_CREATEDATE_PLIDPAGE_2 =
		"notificationRpa.plidPage = ? AND ";

	private static final String _FINDER_COLUMN_CREATEDATE_CREATEDATE_1 =
		"notificationRpa.createDate IS NULL";

	private static final String _FINDER_COLUMN_CREATEDATE_CREATEDATE_2 =
		"notificationRpa.createDate = ?";

	private FinderPath _finderPathWithPaginationFindByTargetName;
	private FinderPath _finderPathWithoutPaginationFindByTargetName;
	private FinderPath _finderPathCountByTargetName;

	/**
	 * Returns all the notification rpas where groupId = &#63; and plidPage = &#63; and targetName = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param targetName the target name
	 * @return the matching notification rpas
	 */
	@Override
	public List<NotificationRpa> findByTargetName(
		long groupId, long plidPage, String targetName) {

		return findByTargetName(
			groupId, plidPage, targetName, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the notification rpas where groupId = &#63; and plidPage = &#63; and targetName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param targetName the target name
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @return the range of matching notification rpas
	 */
	@Override
	public List<NotificationRpa> findByTargetName(
		long groupId, long plidPage, String targetName, int start, int end) {

		return findByTargetName(
			groupId, plidPage, targetName, start, end, null);
	}

	/**
	 * Returns an ordered range of all the notification rpas where groupId = &#63; and plidPage = &#63; and targetName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param targetName the target name
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching notification rpas
	 */
	@Override
	public List<NotificationRpa> findByTargetName(
		long groupId, long plidPage, String targetName, int start, int end,
		OrderByComparator<NotificationRpa> orderByComparator) {

		return findByTargetName(
			groupId, plidPage, targetName, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the notification rpas where groupId = &#63; and plidPage = &#63; and targetName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param targetName the target name
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching notification rpas
	 */
	@Override
	public List<NotificationRpa> findByTargetName(
		long groupId, long plidPage, String targetName, int start, int end,
		OrderByComparator<NotificationRpa> orderByComparator,
		boolean useFinderCache) {

		targetName = Objects.toString(targetName, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByTargetName;
				finderArgs = new Object[] {groupId, plidPage, targetName};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByTargetName;
			finderArgs = new Object[] {
				groupId, plidPage, targetName, start, end, orderByComparator
			};
		}

		List<NotificationRpa> list = null;

		if (useFinderCache) {
			list = (List<NotificationRpa>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (NotificationRpa notificationRpa : list) {
					if ((groupId != notificationRpa.getGroupId()) ||
						(plidPage != notificationRpa.getPlidPage()) ||
						!targetName.equals(notificationRpa.getTargetName())) {

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
					5 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(5);
			}

			sb.append(_SQL_SELECT_NOTIFICATIONRPA_WHERE);

			sb.append(_FINDER_COLUMN_TARGETNAME_GROUPID_2);

			sb.append(_FINDER_COLUMN_TARGETNAME_PLIDPAGE_2);

			boolean bindTargetName = false;

			if (targetName.isEmpty()) {
				sb.append(_FINDER_COLUMN_TARGETNAME_TARGETNAME_3);
			}
			else {
				bindTargetName = true;

				sb.append(_FINDER_COLUMN_TARGETNAME_TARGETNAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(NotificationRpaModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				queryPos.add(plidPage);

				if (bindTargetName) {
					queryPos.add(targetName);
				}

				list = (List<NotificationRpa>)QueryUtil.list(
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
	 * Returns the first notification rpa in the ordered set where groupId = &#63; and plidPage = &#63; and targetName = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param targetName the target name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification rpa
	 * @throws NoSuchNotificationRpaException if a matching notification rpa could not be found
	 */
	@Override
	public NotificationRpa findByTargetName_First(
			long groupId, long plidPage, String targetName,
			OrderByComparator<NotificationRpa> orderByComparator)
		throws NoSuchNotificationRpaException {

		NotificationRpa notificationRpa = fetchByTargetName_First(
			groupId, plidPage, targetName, orderByComparator);

		if (notificationRpa != null) {
			return notificationRpa;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", plidPage=");
		sb.append(plidPage);

		sb.append(", targetName=");
		sb.append(targetName);

		sb.append("}");

		throw new NoSuchNotificationRpaException(sb.toString());
	}

	/**
	 * Returns the first notification rpa in the ordered set where groupId = &#63; and plidPage = &#63; and targetName = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param targetName the target name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	@Override
	public NotificationRpa fetchByTargetName_First(
		long groupId, long plidPage, String targetName,
		OrderByComparator<NotificationRpa> orderByComparator) {

		List<NotificationRpa> list = findByTargetName(
			groupId, plidPage, targetName, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last notification rpa in the ordered set where groupId = &#63; and plidPage = &#63; and targetName = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param targetName the target name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification rpa
	 * @throws NoSuchNotificationRpaException if a matching notification rpa could not be found
	 */
	@Override
	public NotificationRpa findByTargetName_Last(
			long groupId, long plidPage, String targetName,
			OrderByComparator<NotificationRpa> orderByComparator)
		throws NoSuchNotificationRpaException {

		NotificationRpa notificationRpa = fetchByTargetName_Last(
			groupId, plidPage, targetName, orderByComparator);

		if (notificationRpa != null) {
			return notificationRpa;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", plidPage=");
		sb.append(plidPage);

		sb.append(", targetName=");
		sb.append(targetName);

		sb.append("}");

		throw new NoSuchNotificationRpaException(sb.toString());
	}

	/**
	 * Returns the last notification rpa in the ordered set where groupId = &#63; and plidPage = &#63; and targetName = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param targetName the target name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification rpa, or <code>null</code> if a matching notification rpa could not be found
	 */
	@Override
	public NotificationRpa fetchByTargetName_Last(
		long groupId, long plidPage, String targetName,
		OrderByComparator<NotificationRpa> orderByComparator) {

		int count = countByTargetName(groupId, plidPage, targetName);

		if (count == 0) {
			return null;
		}

		List<NotificationRpa> list = findByTargetName(
			groupId, plidPage, targetName, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the notification rpas before and after the current notification rpa in the ordered set where groupId = &#63; and plidPage = &#63; and targetName = &#63;.
	 *
	 * @param customNotificationId the primary key of the current notification rpa
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param targetName the target name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next notification rpa
	 * @throws NoSuchNotificationRpaException if a notification rpa with the primary key could not be found
	 */
	@Override
	public NotificationRpa[] findByTargetName_PrevAndNext(
			long customNotificationId, long groupId, long plidPage,
			String targetName,
			OrderByComparator<NotificationRpa> orderByComparator)
		throws NoSuchNotificationRpaException {

		targetName = Objects.toString(targetName, "");

		NotificationRpa notificationRpa = findByPrimaryKey(
			customNotificationId);

		Session session = null;

		try {
			session = openSession();

			NotificationRpa[] array = new NotificationRpaImpl[3];

			array[0] = getByTargetName_PrevAndNext(
				session, notificationRpa, groupId, plidPage, targetName,
				orderByComparator, true);

			array[1] = notificationRpa;

			array[2] = getByTargetName_PrevAndNext(
				session, notificationRpa, groupId, plidPage, targetName,
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

	protected NotificationRpa getByTargetName_PrevAndNext(
		Session session, NotificationRpa notificationRpa, long groupId,
		long plidPage, String targetName,
		OrderByComparator<NotificationRpa> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				6 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(5);
		}

		sb.append(_SQL_SELECT_NOTIFICATIONRPA_WHERE);

		sb.append(_FINDER_COLUMN_TARGETNAME_GROUPID_2);

		sb.append(_FINDER_COLUMN_TARGETNAME_PLIDPAGE_2);

		boolean bindTargetName = false;

		if (targetName.isEmpty()) {
			sb.append(_FINDER_COLUMN_TARGETNAME_TARGETNAME_3);
		}
		else {
			bindTargetName = true;

			sb.append(_FINDER_COLUMN_TARGETNAME_TARGETNAME_2);
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
			sb.append(NotificationRpaModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(groupId);

		queryPos.add(plidPage);

		if (bindTargetName) {
			queryPos.add(targetName);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						notificationRpa)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<NotificationRpa> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the notification rpas where groupId = &#63; and plidPage = &#63; and targetName = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param targetName the target name
	 */
	@Override
	public void removeByTargetName(
		long groupId, long plidPage, String targetName) {

		for (NotificationRpa notificationRpa :
				findByTargetName(
					groupId, plidPage, targetName, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(notificationRpa);
		}
	}

	/**
	 * Returns the number of notification rpas where groupId = &#63; and plidPage = &#63; and targetName = &#63;.
	 *
	 * @param groupId the group ID
	 * @param plidPage the plid page
	 * @param targetName the target name
	 * @return the number of matching notification rpas
	 */
	@Override
	public int countByTargetName(
		long groupId, long plidPage, String targetName) {

		targetName = Objects.toString(targetName, "");

		FinderPath finderPath = _finderPathCountByTargetName;

		Object[] finderArgs = new Object[] {groupId, plidPage, targetName};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_COUNT_NOTIFICATIONRPA_WHERE);

			sb.append(_FINDER_COLUMN_TARGETNAME_GROUPID_2);

			sb.append(_FINDER_COLUMN_TARGETNAME_PLIDPAGE_2);

			boolean bindTargetName = false;

			if (targetName.isEmpty()) {
				sb.append(_FINDER_COLUMN_TARGETNAME_TARGETNAME_3);
			}
			else {
				bindTargetName = true;

				sb.append(_FINDER_COLUMN_TARGETNAME_TARGETNAME_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				queryPos.add(plidPage);

				if (bindTargetName) {
					queryPos.add(targetName);
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

	private static final String _FINDER_COLUMN_TARGETNAME_GROUPID_2 =
		"notificationRpa.groupId = ? AND ";

	private static final String _FINDER_COLUMN_TARGETNAME_PLIDPAGE_2 =
		"notificationRpa.plidPage = ? AND ";

	private static final String _FINDER_COLUMN_TARGETNAME_TARGETNAME_2 =
		"notificationRpa.targetName = ?";

	private static final String _FINDER_COLUMN_TARGETNAME_TARGETNAME_3 =
		"(notificationRpa.targetName IS NULL OR notificationRpa.targetName = '')";

	public NotificationRpaPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

		setDBColumnNames(dbColumnNames);

		setModelClass(NotificationRpa.class);

		setModelImplClass(NotificationRpaImpl.class);
		setModelPKClass(long.class);
	}

	/**
	 * Caches the notification rpa in the entity cache if it is enabled.
	 *
	 * @param notificationRpa the notification rpa
	 */
	@Override
	public void cacheResult(NotificationRpa notificationRpa) {
		entityCache.putResult(
			NotificationRpaImpl.class, notificationRpa.getPrimaryKey(),
			notificationRpa);

		finderCache.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {
				notificationRpa.getUuid(), notificationRpa.getGroupId()
			},
			notificationRpa);
	}

	/**
	 * Caches the notification rpas in the entity cache if it is enabled.
	 *
	 * @param notificationRpas the notification rpas
	 */
	@Override
	public void cacheResult(List<NotificationRpa> notificationRpas) {
		for (NotificationRpa notificationRpa : notificationRpas) {
			if (entityCache.getResult(
					NotificationRpaImpl.class,
					notificationRpa.getPrimaryKey()) == null) {

				cacheResult(notificationRpa);
			}
		}
	}

	/**
	 * Clears the cache for all notification rpas.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(NotificationRpaImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the notification rpa.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(NotificationRpa notificationRpa) {
		entityCache.removeResult(NotificationRpaImpl.class, notificationRpa);
	}

	@Override
	public void clearCache(List<NotificationRpa> notificationRpas) {
		for (NotificationRpa notificationRpa : notificationRpas) {
			entityCache.removeResult(
				NotificationRpaImpl.class, notificationRpa);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(NotificationRpaImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		NotificationRpaModelImpl notificationRpaModelImpl) {

		Object[] args = new Object[] {
			notificationRpaModelImpl.getUuid(),
			notificationRpaModelImpl.getGroupId()
		};

		finderCache.putResult(
			_finderPathCountByUUID_G, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByUUID_G, args, notificationRpaModelImpl, false);
	}

	/**
	 * Creates a new notification rpa with the primary key. Does not add the notification rpa to the database.
	 *
	 * @param customNotificationId the primary key for the new notification rpa
	 * @return the new notification rpa
	 */
	@Override
	public NotificationRpa create(long customNotificationId) {
		NotificationRpa notificationRpa = new NotificationRpaImpl();

		notificationRpa.setNew(true);
		notificationRpa.setPrimaryKey(customNotificationId);

		String uuid = PortalUUIDUtil.generate();

		notificationRpa.setUuid(uuid);

		notificationRpa.setCompanyId(CompanyThreadLocal.getCompanyId());

		return notificationRpa;
	}

	/**
	 * Removes the notification rpa with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param customNotificationId the primary key of the notification rpa
	 * @return the notification rpa that was removed
	 * @throws NoSuchNotificationRpaException if a notification rpa with the primary key could not be found
	 */
	@Override
	public NotificationRpa remove(long customNotificationId)
		throws NoSuchNotificationRpaException {

		return remove((Serializable)customNotificationId);
	}

	/**
	 * Removes the notification rpa with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the notification rpa
	 * @return the notification rpa that was removed
	 * @throws NoSuchNotificationRpaException if a notification rpa with the primary key could not be found
	 */
	@Override
	public NotificationRpa remove(Serializable primaryKey)
		throws NoSuchNotificationRpaException {

		Session session = null;

		try {
			session = openSession();

			NotificationRpa notificationRpa = (NotificationRpa)session.get(
				NotificationRpaImpl.class, primaryKey);

			if (notificationRpa == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchNotificationRpaException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(notificationRpa);
		}
		catch (NoSuchNotificationRpaException noSuchEntityException) {
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
	protected NotificationRpa removeImpl(NotificationRpa notificationRpa) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(notificationRpa)) {
				notificationRpa = (NotificationRpa)session.get(
					NotificationRpaImpl.class,
					notificationRpa.getPrimaryKeyObj());
			}

			if (notificationRpa != null) {
				session.delete(notificationRpa);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (notificationRpa != null) {
			clearCache(notificationRpa);
		}

		return notificationRpa;
	}

	@Override
	public NotificationRpa updateImpl(NotificationRpa notificationRpa) {
		boolean isNew = notificationRpa.isNew();

		if (!(notificationRpa instanceof NotificationRpaModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(notificationRpa.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					notificationRpa);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in notificationRpa proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom NotificationRpa implementation " +
					notificationRpa.getClass());
		}

		NotificationRpaModelImpl notificationRpaModelImpl =
			(NotificationRpaModelImpl)notificationRpa;

		if (Validator.isNull(notificationRpa.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			notificationRpa.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (notificationRpa.getCreateDate() == null)) {
			if (serviceContext == null) {
				notificationRpa.setCreateDate(now);
			}
			else {
				notificationRpa.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!notificationRpaModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				notificationRpa.setModifiedDate(now);
			}
			else {
				notificationRpa.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(notificationRpa);
			}
			else {
				notificationRpa = (NotificationRpa)session.merge(
					notificationRpa);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			NotificationRpaImpl.class, notificationRpaModelImpl, false, true);

		cacheUniqueFindersCache(notificationRpaModelImpl);

		if (isNew) {
			notificationRpa.setNew(false);
		}

		notificationRpa.resetOriginalValues();

		return notificationRpa;
	}

	/**
	 * Returns the notification rpa with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the notification rpa
	 * @return the notification rpa
	 * @throws NoSuchNotificationRpaException if a notification rpa with the primary key could not be found
	 */
	@Override
	public NotificationRpa findByPrimaryKey(Serializable primaryKey)
		throws NoSuchNotificationRpaException {

		NotificationRpa notificationRpa = fetchByPrimaryKey(primaryKey);

		if (notificationRpa == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchNotificationRpaException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return notificationRpa;
	}

	/**
	 * Returns the notification rpa with the primary key or throws a <code>NoSuchNotificationRpaException</code> if it could not be found.
	 *
	 * @param customNotificationId the primary key of the notification rpa
	 * @return the notification rpa
	 * @throws NoSuchNotificationRpaException if a notification rpa with the primary key could not be found
	 */
	@Override
	public NotificationRpa findByPrimaryKey(long customNotificationId)
		throws NoSuchNotificationRpaException {

		return findByPrimaryKey((Serializable)customNotificationId);
	}

	/**
	 * Returns the notification rpa with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param customNotificationId the primary key of the notification rpa
	 * @return the notification rpa, or <code>null</code> if a notification rpa with the primary key could not be found
	 */
	@Override
	public NotificationRpa fetchByPrimaryKey(long customNotificationId) {
		return fetchByPrimaryKey((Serializable)customNotificationId);
	}

	/**
	 * Returns all the notification rpas.
	 *
	 * @return the notification rpas
	 */
	@Override
	public List<NotificationRpa> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the notification rpas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @return the range of notification rpas
	 */
	@Override
	public List<NotificationRpa> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the notification rpas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of notification rpas
	 */
	@Override
	public List<NotificationRpa> findAll(
		int start, int end,
		OrderByComparator<NotificationRpa> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the notification rpas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationRpaModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of notification rpas
	 * @param end the upper bound of the range of notification rpas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of notification rpas
	 */
	@Override
	public List<NotificationRpa> findAll(
		int start, int end,
		OrderByComparator<NotificationRpa> orderByComparator,
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

		List<NotificationRpa> list = null;

		if (useFinderCache) {
			list = (List<NotificationRpa>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_NOTIFICATIONRPA);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_NOTIFICATIONRPA;

				sql = sql.concat(NotificationRpaModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<NotificationRpa>)QueryUtil.list(
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
	 * Removes all the notification rpas from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (NotificationRpa notificationRpa : findAll()) {
			remove(notificationRpa);
		}
	}

	/**
	 * Returns the number of notification rpas.
	 *
	 * @return the number of notification rpas
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_NOTIFICATIONRPA);

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
		return "customNotificationId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_NOTIFICATIONRPA;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return NotificationRpaModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the notification rpa persistence.
	 */
	@Activate
	public void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		_argumentsResolverServiceRegistration = _bundleContext.registerService(
			ArgumentsResolver.class,
			new NotificationRpaModelArgumentsResolver(),
			MapUtil.singletonDictionary(
				"model.class.name", NotificationRpa.class.getName()));

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

		_finderPathWithPaginationFindByGroupId = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"groupId"}, true);

		_finderPathWithoutPaginationFindByGroupId = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] {Long.class.getName()}, new String[] {"groupId"},
			true);

		_finderPathCountByGroupId = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] {Long.class.getName()}, new String[] {"groupId"},
			false);

		_finderPathWithPaginationFindByGroupIdPlidPage = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupIdPlidPage",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"groupId", "plidPage"}, true);

		_finderPathWithoutPaginationFindByGroupIdPlidPage = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupIdPlidPage",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"groupId", "plidPage"}, true);

		_finderPathCountByGroupIdPlidPage = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupIdPlidPage",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"groupId", "plidPage"}, false);

		_finderPathWithPaginationFindByNotificationTitle = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByNotificationTitle",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"groupId", "plidPage", "notificationTitle"}, true);

		_finderPathWithoutPaginationFindByNotificationTitle = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByNotificationTitle",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			new String[] {"groupId", "plidPage", "notificationTitle"}, true);

		_finderPathCountByNotificationTitle = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByNotificationTitle",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			new String[] {"groupId", "plidPage", "notificationTitle"}, false);

		_finderPathWithPaginationFindByNotificationBody = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByNotificationBody",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"groupId", "plidPage", "notificationBody"}, true);

		_finderPathWithoutPaginationFindByNotificationBody = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByNotificationBody",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			new String[] {"groupId", "plidPage", "notificationBody"}, true);

		_finderPathCountByNotificationBody = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByNotificationBody",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			new String[] {"groupId", "plidPage", "notificationBody"}, false);

		_finderPathWithPaginationFindByCreateDate = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCreateDate",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Date.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"groupId", "plidPage", "createDate"}, true);

		_finderPathWithoutPaginationFindByCreateDate = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCreateDate",
			new String[] {
				Long.class.getName(), Long.class.getName(), Date.class.getName()
			},
			new String[] {"groupId", "plidPage", "createDate"}, true);

		_finderPathCountByCreateDate = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCreateDate",
			new String[] {
				Long.class.getName(), Long.class.getName(), Date.class.getName()
			},
			new String[] {"groupId", "plidPage", "createDate"}, false);

		_finderPathWithPaginationFindByTargetName = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByTargetName",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"groupId", "plidPage", "targetName"}, true);

		_finderPathWithoutPaginationFindByTargetName = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTargetName",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			new String[] {"groupId", "plidPage", "targetName"}, true);

		_finderPathCountByTargetName = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTargetName",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			new String[] {"groupId", "plidPage", "targetName"}, false);
	}

	@Deactivate
	public void deactivate() {
		entityCache.removeCache(NotificationRpaImpl.class.getName());

		_argumentsResolverServiceRegistration.unregister();

		for (ServiceRegistration<FinderPath> serviceRegistration :
				_serviceRegistrations) {

			serviceRegistration.unregister();
		}
	}

	@Override
	@Reference(
		target = RECPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = RECPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = RECPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
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

	private static Long _getTime(Date date) {
		if (date == null) {
			return null;
		}

		return date.getTime();
	}

	private static final String _SQL_SELECT_NOTIFICATIONRPA =
		"SELECT notificationRpa FROM NotificationRpa notificationRpa";

	private static final String _SQL_SELECT_NOTIFICATIONRPA_WHERE =
		"SELECT notificationRpa FROM NotificationRpa notificationRpa WHERE ";

	private static final String _SQL_COUNT_NOTIFICATIONRPA =
		"SELECT COUNT(notificationRpa) FROM NotificationRpa notificationRpa";

	private static final String _SQL_COUNT_NOTIFICATIONRPA_WHERE =
		"SELECT COUNT(notificationRpa) FROM NotificationRpa notificationRpa WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "notificationRpa.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No NotificationRpa exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No NotificationRpa exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		NotificationRpaPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

	static {
		try {
			Class.forName(RECPersistenceConstants.class.getName());
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

	private static class NotificationRpaModelArgumentsResolver
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

			NotificationRpaModelImpl notificationRpaModelImpl =
				(NotificationRpaModelImpl)baseModel;

			long columnBitmask = notificationRpaModelImpl.getColumnBitmask();

			if (!checkColumn || (columnBitmask == 0)) {
				return _getValue(
					notificationRpaModelImpl, columnNames, original);
			}

			Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
				finderPath);

			if (finderPathColumnBitmask == null) {
				finderPathColumnBitmask = 0L;

				for (String columnName : columnNames) {
					finderPathColumnBitmask |=
						notificationRpaModelImpl.getColumnBitmask(columnName);
				}

				_finderPathColumnBitmasksCache.put(
					finderPath, finderPathColumnBitmask);
			}

			if ((columnBitmask & finderPathColumnBitmask) != 0) {
				return _getValue(
					notificationRpaModelImpl, columnNames, original);
			}

			return null;
		}

		private Object[] _getValue(
			NotificationRpaModelImpl notificationRpaModelImpl,
			String[] columnNames, boolean original) {

			Object[] arguments = new Object[columnNames.length];

			for (int i = 0; i < arguments.length; i++) {
				String columnName = columnNames[i];

				if (original) {
					arguments[i] =
						notificationRpaModelImpl.getColumnOriginalValue(
							columnName);
				}
				else {
					arguments[i] = notificationRpaModelImpl.getColumnValue(
						columnName);
				}
			}

			return arguments;
		}

		private static Map<FinderPath, Long> _finderPathColumnBitmasksCache =
			new ConcurrentHashMap<>();

	}

}