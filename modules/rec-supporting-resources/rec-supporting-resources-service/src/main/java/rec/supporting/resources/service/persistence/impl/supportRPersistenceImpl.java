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

package rec.supporting.resources.service.persistence.impl;

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

import rec.supporting.resources.exception.NoSuchsupportRException;
import rec.supporting.resources.model.impl.supportRImpl;
import rec.supporting.resources.model.impl.supportRModelImpl;
import rec.supporting.resources.model.supportR;
import rec.supporting.resources.service.persistence.impl.constants.RECsrPersistenceConstants;
import rec.supporting.resources.service.persistence.supportRPersistence;

/**
 * The persistence implementation for the support r service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = supportRPersistence.class)
public class supportRPersistenceImpl
	extends BasePersistenceImpl<supportR> implements supportRPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>supportRUtil</code> to access the support r persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		supportRImpl.class.getName();

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
	 * Returns all the support rs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching support rs
	 */
	@Override
	public List<supportR> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the support rs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @return the range of matching support rs
	 */
	@Override
	public List<supportR> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the support rs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching support rs
	 */
	@Override
	public List<supportR> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<supportR> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the support rs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching support rs
	 */
	@Override
	public List<supportR> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<supportR> orderByComparator, boolean useFinderCache) {

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

		List<supportR> list = null;

		if (useFinderCache) {
			list = (List<supportR>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (supportR supportR : list) {
					if (!uuid.equals(supportR.getUuid())) {
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

			sb.append(_SQL_SELECT_SUPPORTR_WHERE);

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
				sb.append(supportRModelImpl.ORDER_BY_JPQL);
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

				list = (List<supportR>)QueryUtil.list(
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
	 * Returns the first support r in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	@Override
	public supportR findByUuid_First(
			String uuid, OrderByComparator<supportR> orderByComparator)
		throws NoSuchsupportRException {

		supportR supportR = fetchByUuid_First(uuid, orderByComparator);

		if (supportR != null) {
			return supportR;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchsupportRException(sb.toString());
	}

	/**
	 * Returns the first support r in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r, or <code>null</code> if a matching support r could not be found
	 */
	@Override
	public supportR fetchByUuid_First(
		String uuid, OrderByComparator<supportR> orderByComparator) {

		List<supportR> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last support r in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	@Override
	public supportR findByUuid_Last(
			String uuid, OrderByComparator<supportR> orderByComparator)
		throws NoSuchsupportRException {

		supportR supportR = fetchByUuid_Last(uuid, orderByComparator);

		if (supportR != null) {
			return supportR;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchsupportRException(sb.toString());
	}

	/**
	 * Returns the last support r in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r, or <code>null</code> if a matching support r could not be found
	 */
	@Override
	public supportR fetchByUuid_Last(
		String uuid, OrderByComparator<supportR> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<supportR> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the support rs before and after the current support r in the ordered set where uuid = &#63;.
	 *
	 * @param supportRId the primary key of the current support r
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next support r
	 * @throws NoSuchsupportRException if a support r with the primary key could not be found
	 */
	@Override
	public supportR[] findByUuid_PrevAndNext(
			long supportRId, String uuid,
			OrderByComparator<supportR> orderByComparator)
		throws NoSuchsupportRException {

		uuid = Objects.toString(uuid, "");

		supportR supportR = findByPrimaryKey(supportRId);

		Session session = null;

		try {
			session = openSession();

			supportR[] array = new supportRImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, supportR, uuid, orderByComparator, true);

			array[1] = supportR;

			array[2] = getByUuid_PrevAndNext(
				session, supportR, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected supportR getByUuid_PrevAndNext(
		Session session, supportR supportR, String uuid,
		OrderByComparator<supportR> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_SUPPORTR_WHERE);

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
			sb.append(supportRModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(supportR)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<supportR> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the support rs where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (supportR supportR :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(supportR);
		}
	}

	/**
	 * Returns the number of support rs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching support rs
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_SUPPORTR_WHERE);

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
		"supportR.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(supportR.uuid IS NULL OR supportR.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;
	private FinderPath _finderPathCountByUUID_G;

	/**
	 * Returns the support r where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchsupportRException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	@Override
	public supportR findByUUID_G(String uuid, long groupId)
		throws NoSuchsupportRException {

		supportR supportR = fetchByUUID_G(uuid, groupId);

		if (supportR == null) {
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

			throw new NoSuchsupportRException(sb.toString());
		}

		return supportR;
	}

	/**
	 * Returns the support r where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching support r, or <code>null</code> if a matching support r could not be found
	 */
	@Override
	public supportR fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the support r where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching support r, or <code>null</code> if a matching support r could not be found
	 */
	@Override
	public supportR fetchByUUID_G(
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

		if (result instanceof supportR) {
			supportR supportR = (supportR)result;

			if (!Objects.equals(uuid, supportR.getUuid()) ||
				(groupId != supportR.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_SUPPORTR_WHERE);

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

				List<supportR> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByUUID_G, finderArgs, list);
					}
				}
				else {
					supportR supportR = list.get(0);

					result = supportR;

					cacheResult(supportR);
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
			return (supportR)result;
		}
	}

	/**
	 * Removes the support r where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the support r that was removed
	 */
	@Override
	public supportR removeByUUID_G(String uuid, long groupId)
		throws NoSuchsupportRException {

		supportR supportR = findByUUID_G(uuid, groupId);

		return remove(supportR);
	}

	/**
	 * Returns the number of support rs where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching support rs
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUUID_G;

		Object[] finderArgs = new Object[] {uuid, groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_SUPPORTR_WHERE);

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
		"supportR.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(supportR.uuid IS NULL OR supportR.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"supportR.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the support rs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching support rs
	 */
	@Override
	public List<supportR> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the support rs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @return the range of matching support rs
	 */
	@Override
	public List<supportR> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the support rs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching support rs
	 */
	@Override
	public List<supportR> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<supportR> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the support rs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching support rs
	 */
	@Override
	public List<supportR> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<supportR> orderByComparator, boolean useFinderCache) {

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

		List<supportR> list = null;

		if (useFinderCache) {
			list = (List<supportR>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (supportR supportR : list) {
					if (!uuid.equals(supportR.getUuid()) ||
						(companyId != supportR.getCompanyId())) {

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

			sb.append(_SQL_SELECT_SUPPORTR_WHERE);

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
				sb.append(supportRModelImpl.ORDER_BY_JPQL);
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

				list = (List<supportR>)QueryUtil.list(
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
	 * Returns the first support r in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	@Override
	public supportR findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<supportR> orderByComparator)
		throws NoSuchsupportRException {

		supportR supportR = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (supportR != null) {
			return supportR;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchsupportRException(sb.toString());
	}

	/**
	 * Returns the first support r in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r, or <code>null</code> if a matching support r could not be found
	 */
	@Override
	public supportR fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<supportR> orderByComparator) {

		List<supportR> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last support r in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	@Override
	public supportR findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<supportR> orderByComparator)
		throws NoSuchsupportRException {

		supportR supportR = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (supportR != null) {
			return supportR;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchsupportRException(sb.toString());
	}

	/**
	 * Returns the last support r in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r, or <code>null</code> if a matching support r could not be found
	 */
	@Override
	public supportR fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<supportR> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<supportR> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the support rs before and after the current support r in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param supportRId the primary key of the current support r
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next support r
	 * @throws NoSuchsupportRException if a support r with the primary key could not be found
	 */
	@Override
	public supportR[] findByUuid_C_PrevAndNext(
			long supportRId, String uuid, long companyId,
			OrderByComparator<supportR> orderByComparator)
		throws NoSuchsupportRException {

		uuid = Objects.toString(uuid, "");

		supportR supportR = findByPrimaryKey(supportRId);

		Session session = null;

		try {
			session = openSession();

			supportR[] array = new supportRImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, supportR, uuid, companyId, orderByComparator, true);

			array[1] = supportR;

			array[2] = getByUuid_C_PrevAndNext(
				session, supportR, uuid, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected supportR getByUuid_C_PrevAndNext(
		Session session, supportR supportR, String uuid, long companyId,
		OrderByComparator<supportR> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_SUPPORTR_WHERE);

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
			sb.append(supportRModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(supportR)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<supportR> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the support rs where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (supportR supportR :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(supportR);
		}
	}

	/**
	 * Returns the number of support rs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching support rs
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_SUPPORTR_WHERE);

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
		"supportR.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(supportR.uuid IS NULL OR supportR.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"supportR.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByGroupId;
	private FinderPath _finderPathWithoutPaginationFindByGroupId;
	private FinderPath _finderPathCountByGroupId;

	/**
	 * Returns all the support rs where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching support rs
	 */
	@Override
	public List<supportR> findByGroupId(long groupId) {
		return findByGroupId(
			groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the support rs where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @return the range of matching support rs
	 */
	@Override
	public List<supportR> findByGroupId(long groupId, int start, int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the support rs where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching support rs
	 */
	@Override
	public List<supportR> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<supportR> orderByComparator) {

		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the support rs where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching support rs
	 */
	@Override
	public List<supportR> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<supportR> orderByComparator, boolean useFinderCache) {

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

		List<supportR> list = null;

		if (useFinderCache) {
			list = (List<supportR>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (supportR supportR : list) {
					if (groupId != supportR.getGroupId()) {
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

			sb.append(_SQL_SELECT_SUPPORTR_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(supportRModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				list = (List<supportR>)QueryUtil.list(
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
	 * Returns the first support r in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	@Override
	public supportR findByGroupId_First(
			long groupId, OrderByComparator<supportR> orderByComparator)
		throws NoSuchsupportRException {

		supportR supportR = fetchByGroupId_First(groupId, orderByComparator);

		if (supportR != null) {
			return supportR;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchsupportRException(sb.toString());
	}

	/**
	 * Returns the first support r in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r, or <code>null</code> if a matching support r could not be found
	 */
	@Override
	public supportR fetchByGroupId_First(
		long groupId, OrderByComparator<supportR> orderByComparator) {

		List<supportR> list = findByGroupId(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last support r in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	@Override
	public supportR findByGroupId_Last(
			long groupId, OrderByComparator<supportR> orderByComparator)
		throws NoSuchsupportRException {

		supportR supportR = fetchByGroupId_Last(groupId, orderByComparator);

		if (supportR != null) {
			return supportR;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchsupportRException(sb.toString());
	}

	/**
	 * Returns the last support r in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r, or <code>null</code> if a matching support r could not be found
	 */
	@Override
	public supportR fetchByGroupId_Last(
		long groupId, OrderByComparator<supportR> orderByComparator) {

		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<supportR> list = findByGroupId(
			groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the support rs before and after the current support r in the ordered set where groupId = &#63;.
	 *
	 * @param supportRId the primary key of the current support r
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next support r
	 * @throws NoSuchsupportRException if a support r with the primary key could not be found
	 */
	@Override
	public supportR[] findByGroupId_PrevAndNext(
			long supportRId, long groupId,
			OrderByComparator<supportR> orderByComparator)
		throws NoSuchsupportRException {

		supportR supportR = findByPrimaryKey(supportRId);

		Session session = null;

		try {
			session = openSession();

			supportR[] array = new supportRImpl[3];

			array[0] = getByGroupId_PrevAndNext(
				session, supportR, groupId, orderByComparator, true);

			array[1] = supportR;

			array[2] = getByGroupId_PrevAndNext(
				session, supportR, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected supportR getByGroupId_PrevAndNext(
		Session session, supportR supportR, long groupId,
		OrderByComparator<supportR> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_SUPPORTR_WHERE);

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
			sb.append(supportRModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(groupId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(supportR)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<supportR> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the support rs where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (supportR supportR :
				findByGroupId(
					groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(supportR);
		}
	}

	/**
	 * Returns the number of support rs where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching support rs
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = _finderPathCountByGroupId;

		Object[] finderArgs = new Object[] {groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_SUPPORTR_WHERE);

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
		"supportR.groupId = ?";

	private FinderPath _finderPathWithPaginationFindBySupportingResourceTitle;
	private FinderPath
		_finderPathWithoutPaginationFindBySupportingResourceTitle;
	private FinderPath _finderPathCountBySupportingResourceTitle;

	/**
	 * Returns all the support rs where groupId = &#63; and title = &#63;.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @return the matching support rs
	 */
	@Override
	public List<supportR> findBySupportingResourceTitle(
		long groupId, String title) {

		return findBySupportingResourceTitle(
			groupId, title, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the support rs where groupId = &#63; and title = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @return the range of matching support rs
	 */
	@Override
	public List<supportR> findBySupportingResourceTitle(
		long groupId, String title, int start, int end) {

		return findBySupportingResourceTitle(groupId, title, start, end, null);
	}

	/**
	 * Returns an ordered range of all the support rs where groupId = &#63; and title = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching support rs
	 */
	@Override
	public List<supportR> findBySupportingResourceTitle(
		long groupId, String title, int start, int end,
		OrderByComparator<supportR> orderByComparator) {

		return findBySupportingResourceTitle(
			groupId, title, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the support rs where groupId = &#63; and title = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching support rs
	 */
	@Override
	public List<supportR> findBySupportingResourceTitle(
		long groupId, String title, int start, int end,
		OrderByComparator<supportR> orderByComparator, boolean useFinderCache) {

		title = Objects.toString(title, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindBySupportingResourceTitle;
				finderArgs = new Object[] {groupId, title};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindBySupportingResourceTitle;
			finderArgs = new Object[] {
				groupId, title, start, end, orderByComparator
			};
		}

		List<supportR> list = null;

		if (useFinderCache) {
			list = (List<supportR>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (supportR supportR : list) {
					if ((groupId != supportR.getGroupId()) ||
						!title.equals(supportR.getTitle())) {

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

			sb.append(_SQL_SELECT_SUPPORTR_WHERE);

			sb.append(_FINDER_COLUMN_SUPPORTINGRESOURCETITLE_GROUPID_2);

			boolean bindTitle = false;

			if (title.isEmpty()) {
				sb.append(_FINDER_COLUMN_SUPPORTINGRESOURCETITLE_TITLE_3);
			}
			else {
				bindTitle = true;

				sb.append(_FINDER_COLUMN_SUPPORTINGRESOURCETITLE_TITLE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(supportRModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				if (bindTitle) {
					queryPos.add(title);
				}

				list = (List<supportR>)QueryUtil.list(
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
	 * Returns the first support r in the ordered set where groupId = &#63; and title = &#63;.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	@Override
	public supportR findBySupportingResourceTitle_First(
			long groupId, String title,
			OrderByComparator<supportR> orderByComparator)
		throws NoSuchsupportRException {

		supportR supportR = fetchBySupportingResourceTitle_First(
			groupId, title, orderByComparator);

		if (supportR != null) {
			return supportR;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", title=");
		sb.append(title);

		sb.append("}");

		throw new NoSuchsupportRException(sb.toString());
	}

	/**
	 * Returns the first support r in the ordered set where groupId = &#63; and title = &#63;.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r, or <code>null</code> if a matching support r could not be found
	 */
	@Override
	public supportR fetchBySupportingResourceTitle_First(
		long groupId, String title,
		OrderByComparator<supportR> orderByComparator) {

		List<supportR> list = findBySupportingResourceTitle(
			groupId, title, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last support r in the ordered set where groupId = &#63; and title = &#63;.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	@Override
	public supportR findBySupportingResourceTitle_Last(
			long groupId, String title,
			OrderByComparator<supportR> orderByComparator)
		throws NoSuchsupportRException {

		supportR supportR = fetchBySupportingResourceTitle_Last(
			groupId, title, orderByComparator);

		if (supportR != null) {
			return supportR;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", title=");
		sb.append(title);

		sb.append("}");

		throw new NoSuchsupportRException(sb.toString());
	}

	/**
	 * Returns the last support r in the ordered set where groupId = &#63; and title = &#63;.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r, or <code>null</code> if a matching support r could not be found
	 */
	@Override
	public supportR fetchBySupportingResourceTitle_Last(
		long groupId, String title,
		OrderByComparator<supportR> orderByComparator) {

		int count = countBySupportingResourceTitle(groupId, title);

		if (count == 0) {
			return null;
		}

		List<supportR> list = findBySupportingResourceTitle(
			groupId, title, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the support rs before and after the current support r in the ordered set where groupId = &#63; and title = &#63;.
	 *
	 * @param supportRId the primary key of the current support r
	 * @param groupId the group ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next support r
	 * @throws NoSuchsupportRException if a support r with the primary key could not be found
	 */
	@Override
	public supportR[] findBySupportingResourceTitle_PrevAndNext(
			long supportRId, long groupId, String title,
			OrderByComparator<supportR> orderByComparator)
		throws NoSuchsupportRException {

		title = Objects.toString(title, "");

		supportR supportR = findByPrimaryKey(supportRId);

		Session session = null;

		try {
			session = openSession();

			supportR[] array = new supportRImpl[3];

			array[0] = getBySupportingResourceTitle_PrevAndNext(
				session, supportR, groupId, title, orderByComparator, true);

			array[1] = supportR;

			array[2] = getBySupportingResourceTitle_PrevAndNext(
				session, supportR, groupId, title, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected supportR getBySupportingResourceTitle_PrevAndNext(
		Session session, supportR supportR, long groupId, String title,
		OrderByComparator<supportR> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_SUPPORTR_WHERE);

		sb.append(_FINDER_COLUMN_SUPPORTINGRESOURCETITLE_GROUPID_2);

		boolean bindTitle = false;

		if (title.isEmpty()) {
			sb.append(_FINDER_COLUMN_SUPPORTINGRESOURCETITLE_TITLE_3);
		}
		else {
			bindTitle = true;

			sb.append(_FINDER_COLUMN_SUPPORTINGRESOURCETITLE_TITLE_2);
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
			sb.append(supportRModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(groupId);

		if (bindTitle) {
			queryPos.add(title);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(supportR)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<supportR> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the support rs where groupId = &#63; and title = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 */
	@Override
	public void removeBySupportingResourceTitle(long groupId, String title) {
		for (supportR supportR :
				findBySupportingResourceTitle(
					groupId, title, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(supportR);
		}
	}

	/**
	 * Returns the number of support rs where groupId = &#63; and title = &#63;.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @return the number of matching support rs
	 */
	@Override
	public int countBySupportingResourceTitle(long groupId, String title) {
		title = Objects.toString(title, "");

		FinderPath finderPath = _finderPathCountBySupportingResourceTitle;

		Object[] finderArgs = new Object[] {groupId, title};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_SUPPORTR_WHERE);

			sb.append(_FINDER_COLUMN_SUPPORTINGRESOURCETITLE_GROUPID_2);

			boolean bindTitle = false;

			if (title.isEmpty()) {
				sb.append(_FINDER_COLUMN_SUPPORTINGRESOURCETITLE_TITLE_3);
			}
			else {
				bindTitle = true;

				sb.append(_FINDER_COLUMN_SUPPORTINGRESOURCETITLE_TITLE_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				if (bindTitle) {
					queryPos.add(title);
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
		_FINDER_COLUMN_SUPPORTINGRESOURCETITLE_GROUPID_2 =
			"supportR.groupId = ? AND ";

	private static final String _FINDER_COLUMN_SUPPORTINGRESOURCETITLE_TITLE_2 =
		"supportR.title = ?";

	private static final String _FINDER_COLUMN_SUPPORTINGRESOURCETITLE_TITLE_3 =
		"(supportR.title IS NULL OR supportR.title = '')";

	private FinderPath _finderPathWithPaginationFindBySupportingResourceType;
	private FinderPath _finderPathWithoutPaginationFindBySupportingResourceType;
	private FinderPath _finderPathCountBySupportingResourceType;

	/**
	 * Returns all the support rs where groupId = &#63; and type = &#63;.
	 *
	 * @param groupId the group ID
	 * @param type the type
	 * @return the matching support rs
	 */
	@Override
	public List<supportR> findBySupportingResourceType(
		long groupId, String type) {

		return findBySupportingResourceType(
			groupId, type, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the support rs where groupId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param type the type
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @return the range of matching support rs
	 */
	@Override
	public List<supportR> findBySupportingResourceType(
		long groupId, String type, int start, int end) {

		return findBySupportingResourceType(groupId, type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the support rs where groupId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param type the type
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching support rs
	 */
	@Override
	public List<supportR> findBySupportingResourceType(
		long groupId, String type, int start, int end,
		OrderByComparator<supportR> orderByComparator) {

		return findBySupportingResourceType(
			groupId, type, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the support rs where groupId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param type the type
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching support rs
	 */
	@Override
	public List<supportR> findBySupportingResourceType(
		long groupId, String type, int start, int end,
		OrderByComparator<supportR> orderByComparator, boolean useFinderCache) {

		type = Objects.toString(type, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindBySupportingResourceType;
				finderArgs = new Object[] {groupId, type};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindBySupportingResourceType;
			finderArgs = new Object[] {
				groupId, type, start, end, orderByComparator
			};
		}

		List<supportR> list = null;

		if (useFinderCache) {
			list = (List<supportR>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (supportR supportR : list) {
					if ((groupId != supportR.getGroupId()) ||
						!type.equals(supportR.getType())) {

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

			sb.append(_SQL_SELECT_SUPPORTR_WHERE);

			sb.append(_FINDER_COLUMN_SUPPORTINGRESOURCETYPE_GROUPID_2);

			boolean bindType = false;

			if (type.isEmpty()) {
				sb.append(_FINDER_COLUMN_SUPPORTINGRESOURCETYPE_TYPE_3);
			}
			else {
				bindType = true;

				sb.append(_FINDER_COLUMN_SUPPORTINGRESOURCETYPE_TYPE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(supportRModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				if (bindType) {
					queryPos.add(type);
				}

				list = (List<supportR>)QueryUtil.list(
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
	 * Returns the first support r in the ordered set where groupId = &#63; and type = &#63;.
	 *
	 * @param groupId the group ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	@Override
	public supportR findBySupportingResourceType_First(
			long groupId, String type,
			OrderByComparator<supportR> orderByComparator)
		throws NoSuchsupportRException {

		supportR supportR = fetchBySupportingResourceType_First(
			groupId, type, orderByComparator);

		if (supportR != null) {
			return supportR;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", type=");
		sb.append(type);

		sb.append("}");

		throw new NoSuchsupportRException(sb.toString());
	}

	/**
	 * Returns the first support r in the ordered set where groupId = &#63; and type = &#63;.
	 *
	 * @param groupId the group ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r, or <code>null</code> if a matching support r could not be found
	 */
	@Override
	public supportR fetchBySupportingResourceType_First(
		long groupId, String type,
		OrderByComparator<supportR> orderByComparator) {

		List<supportR> list = findBySupportingResourceType(
			groupId, type, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last support r in the ordered set where groupId = &#63; and type = &#63;.
	 *
	 * @param groupId the group ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	@Override
	public supportR findBySupportingResourceType_Last(
			long groupId, String type,
			OrderByComparator<supportR> orderByComparator)
		throws NoSuchsupportRException {

		supportR supportR = fetchBySupportingResourceType_Last(
			groupId, type, orderByComparator);

		if (supportR != null) {
			return supportR;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", type=");
		sb.append(type);

		sb.append("}");

		throw new NoSuchsupportRException(sb.toString());
	}

	/**
	 * Returns the last support r in the ordered set where groupId = &#63; and type = &#63;.
	 *
	 * @param groupId the group ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r, or <code>null</code> if a matching support r could not be found
	 */
	@Override
	public supportR fetchBySupportingResourceType_Last(
		long groupId, String type,
		OrderByComparator<supportR> orderByComparator) {

		int count = countBySupportingResourceType(groupId, type);

		if (count == 0) {
			return null;
		}

		List<supportR> list = findBySupportingResourceType(
			groupId, type, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the support rs before and after the current support r in the ordered set where groupId = &#63; and type = &#63;.
	 *
	 * @param supportRId the primary key of the current support r
	 * @param groupId the group ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next support r
	 * @throws NoSuchsupportRException if a support r with the primary key could not be found
	 */
	@Override
	public supportR[] findBySupportingResourceType_PrevAndNext(
			long supportRId, long groupId, String type,
			OrderByComparator<supportR> orderByComparator)
		throws NoSuchsupportRException {

		type = Objects.toString(type, "");

		supportR supportR = findByPrimaryKey(supportRId);

		Session session = null;

		try {
			session = openSession();

			supportR[] array = new supportRImpl[3];

			array[0] = getBySupportingResourceType_PrevAndNext(
				session, supportR, groupId, type, orderByComparator, true);

			array[1] = supportR;

			array[2] = getBySupportingResourceType_PrevAndNext(
				session, supportR, groupId, type, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected supportR getBySupportingResourceType_PrevAndNext(
		Session session, supportR supportR, long groupId, String type,
		OrderByComparator<supportR> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_SUPPORTR_WHERE);

		sb.append(_FINDER_COLUMN_SUPPORTINGRESOURCETYPE_GROUPID_2);

		boolean bindType = false;

		if (type.isEmpty()) {
			sb.append(_FINDER_COLUMN_SUPPORTINGRESOURCETYPE_TYPE_3);
		}
		else {
			bindType = true;

			sb.append(_FINDER_COLUMN_SUPPORTINGRESOURCETYPE_TYPE_2);
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
			sb.append(supportRModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(groupId);

		if (bindType) {
			queryPos.add(type);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(supportR)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<supportR> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the support rs where groupId = &#63; and type = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param type the type
	 */
	@Override
	public void removeBySupportingResourceType(long groupId, String type) {
		for (supportR supportR :
				findBySupportingResourceType(
					groupId, type, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(supportR);
		}
	}

	/**
	 * Returns the number of support rs where groupId = &#63; and type = &#63;.
	 *
	 * @param groupId the group ID
	 * @param type the type
	 * @return the number of matching support rs
	 */
	@Override
	public int countBySupportingResourceType(long groupId, String type) {
		type = Objects.toString(type, "");

		FinderPath finderPath = _finderPathCountBySupportingResourceType;

		Object[] finderArgs = new Object[] {groupId, type};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_SUPPORTR_WHERE);

			sb.append(_FINDER_COLUMN_SUPPORTINGRESOURCETYPE_GROUPID_2);

			boolean bindType = false;

			if (type.isEmpty()) {
				sb.append(_FINDER_COLUMN_SUPPORTINGRESOURCETYPE_TYPE_3);
			}
			else {
				bindType = true;

				sb.append(_FINDER_COLUMN_SUPPORTINGRESOURCETYPE_TYPE_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				if (bindType) {
					queryPos.add(type);
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
		_FINDER_COLUMN_SUPPORTINGRESOURCETYPE_GROUPID_2 =
			"supportR.groupId = ? AND ";

	private static final String _FINDER_COLUMN_SUPPORTINGRESOURCETYPE_TYPE_2 =
		"supportR.type = ?";

	private static final String _FINDER_COLUMN_SUPPORTINGRESOURCETYPE_TYPE_3 =
		"(supportR.type IS NULL OR supportR.type = '')";

	private FinderPath _finderPathWithPaginationFindByCreateDate;
	private FinderPath _finderPathWithoutPaginationFindByCreateDate;
	private FinderPath _finderPathCountByCreateDate;

	/**
	 * Returns all the support rs where groupId = &#63; and createDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param createDate the create date
	 * @return the matching support rs
	 */
	@Override
	public List<supportR> findByCreateDate(long groupId, Date createDate) {
		return findByCreateDate(
			groupId, createDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the support rs where groupId = &#63; and createDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param createDate the create date
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @return the range of matching support rs
	 */
	@Override
	public List<supportR> findByCreateDate(
		long groupId, Date createDate, int start, int end) {

		return findByCreateDate(groupId, createDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the support rs where groupId = &#63; and createDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param createDate the create date
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching support rs
	 */
	@Override
	public List<supportR> findByCreateDate(
		long groupId, Date createDate, int start, int end,
		OrderByComparator<supportR> orderByComparator) {

		return findByCreateDate(
			groupId, createDate, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the support rs where groupId = &#63; and createDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param createDate the create date
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching support rs
	 */
	@Override
	public List<supportR> findByCreateDate(
		long groupId, Date createDate, int start, int end,
		OrderByComparator<supportR> orderByComparator, boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByCreateDate;
				finderArgs = new Object[] {groupId, _getTime(createDate)};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByCreateDate;
			finderArgs = new Object[] {
				groupId, _getTime(createDate), start, end, orderByComparator
			};
		}

		List<supportR> list = null;

		if (useFinderCache) {
			list = (List<supportR>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (supportR supportR : list) {
					if ((groupId != supportR.getGroupId()) ||
						!Objects.equals(createDate, supportR.getCreateDate())) {

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

			sb.append(_SQL_SELECT_SUPPORTR_WHERE);

			sb.append(_FINDER_COLUMN_CREATEDATE_GROUPID_2);

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
				sb.append(supportRModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				if (bindCreateDate) {
					queryPos.add(new Timestamp(createDate.getTime()));
				}

				list = (List<supportR>)QueryUtil.list(
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
	 * Returns the first support r in the ordered set where groupId = &#63; and createDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	@Override
	public supportR findByCreateDate_First(
			long groupId, Date createDate,
			OrderByComparator<supportR> orderByComparator)
		throws NoSuchsupportRException {

		supportR supportR = fetchByCreateDate_First(
			groupId, createDate, orderByComparator);

		if (supportR != null) {
			return supportR;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", createDate=");
		sb.append(createDate);

		sb.append("}");

		throw new NoSuchsupportRException(sb.toString());
	}

	/**
	 * Returns the first support r in the ordered set where groupId = &#63; and createDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r, or <code>null</code> if a matching support r could not be found
	 */
	@Override
	public supportR fetchByCreateDate_First(
		long groupId, Date createDate,
		OrderByComparator<supportR> orderByComparator) {

		List<supportR> list = findByCreateDate(
			groupId, createDate, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last support r in the ordered set where groupId = &#63; and createDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	@Override
	public supportR findByCreateDate_Last(
			long groupId, Date createDate,
			OrderByComparator<supportR> orderByComparator)
		throws NoSuchsupportRException {

		supportR supportR = fetchByCreateDate_Last(
			groupId, createDate, orderByComparator);

		if (supportR != null) {
			return supportR;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", createDate=");
		sb.append(createDate);

		sb.append("}");

		throw new NoSuchsupportRException(sb.toString());
	}

	/**
	 * Returns the last support r in the ordered set where groupId = &#63; and createDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r, or <code>null</code> if a matching support r could not be found
	 */
	@Override
	public supportR fetchByCreateDate_Last(
		long groupId, Date createDate,
		OrderByComparator<supportR> orderByComparator) {

		int count = countByCreateDate(groupId, createDate);

		if (count == 0) {
			return null;
		}

		List<supportR> list = findByCreateDate(
			groupId, createDate, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the support rs before and after the current support r in the ordered set where groupId = &#63; and createDate = &#63;.
	 *
	 * @param supportRId the primary key of the current support r
	 * @param groupId the group ID
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next support r
	 * @throws NoSuchsupportRException if a support r with the primary key could not be found
	 */
	@Override
	public supportR[] findByCreateDate_PrevAndNext(
			long supportRId, long groupId, Date createDate,
			OrderByComparator<supportR> orderByComparator)
		throws NoSuchsupportRException {

		supportR supportR = findByPrimaryKey(supportRId);

		Session session = null;

		try {
			session = openSession();

			supportR[] array = new supportRImpl[3];

			array[0] = getByCreateDate_PrevAndNext(
				session, supportR, groupId, createDate, orderByComparator,
				true);

			array[1] = supportR;

			array[2] = getByCreateDate_PrevAndNext(
				session, supportR, groupId, createDate, orderByComparator,
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

	protected supportR getByCreateDate_PrevAndNext(
		Session session, supportR supportR, long groupId, Date createDate,
		OrderByComparator<supportR> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_SUPPORTR_WHERE);

		sb.append(_FINDER_COLUMN_CREATEDATE_GROUPID_2);

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
			sb.append(supportRModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(groupId);

		if (bindCreateDate) {
			queryPos.add(new Timestamp(createDate.getTime()));
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(supportR)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<supportR> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the support rs where groupId = &#63; and createDate = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param createDate the create date
	 */
	@Override
	public void removeByCreateDate(long groupId, Date createDate) {
		for (supportR supportR :
				findByCreateDate(
					groupId, createDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(supportR);
		}
	}

	/**
	 * Returns the number of support rs where groupId = &#63; and createDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param createDate the create date
	 * @return the number of matching support rs
	 */
	@Override
	public int countByCreateDate(long groupId, Date createDate) {
		FinderPath finderPath = _finderPathCountByCreateDate;

		Object[] finderArgs = new Object[] {groupId, _getTime(createDate)};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_SUPPORTR_WHERE);

			sb.append(_FINDER_COLUMN_CREATEDATE_GROUPID_2);

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
		"supportR.groupId = ? AND ";

	private static final String _FINDER_COLUMN_CREATEDATE_CREATEDATE_1 =
		"supportR.createDate IS NULL";

	private static final String _FINDER_COLUMN_CREATEDATE_CREATEDATE_2 =
		"supportR.createDate = ?";

	private FinderPath _finderPathWithPaginationFindByDueDate;
	private FinderPath _finderPathWithoutPaginationFindByDueDate;
	private FinderPath _finderPathCountByDueDate;

	/**
	 * Returns all the support rs where groupId = &#63; and dueDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @return the matching support rs
	 */
	@Override
	public List<supportR> findByDueDate(long groupId, Date dueDate) {
		return findByDueDate(
			groupId, dueDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the support rs where groupId = &#63; and dueDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @return the range of matching support rs
	 */
	@Override
	public List<supportR> findByDueDate(
		long groupId, Date dueDate, int start, int end) {

		return findByDueDate(groupId, dueDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the support rs where groupId = &#63; and dueDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching support rs
	 */
	@Override
	public List<supportR> findByDueDate(
		long groupId, Date dueDate, int start, int end,
		OrderByComparator<supportR> orderByComparator) {

		return findByDueDate(
			groupId, dueDate, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the support rs where groupId = &#63; and dueDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching support rs
	 */
	@Override
	public List<supportR> findByDueDate(
		long groupId, Date dueDate, int start, int end,
		OrderByComparator<supportR> orderByComparator, boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByDueDate;
				finderArgs = new Object[] {groupId, _getTime(dueDate)};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByDueDate;
			finderArgs = new Object[] {
				groupId, _getTime(dueDate), start, end, orderByComparator
			};
		}

		List<supportR> list = null;

		if (useFinderCache) {
			list = (List<supportR>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (supportR supportR : list) {
					if ((groupId != supportR.getGroupId()) ||
						!Objects.equals(dueDate, supportR.getDueDate())) {

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

			sb.append(_SQL_SELECT_SUPPORTR_WHERE);

			sb.append(_FINDER_COLUMN_DUEDATE_GROUPID_2);

			boolean bindDueDate = false;

			if (dueDate == null) {
				sb.append(_FINDER_COLUMN_DUEDATE_DUEDATE_1);
			}
			else {
				bindDueDate = true;

				sb.append(_FINDER_COLUMN_DUEDATE_DUEDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(supportRModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				if (bindDueDate) {
					queryPos.add(new Timestamp(dueDate.getTime()));
				}

				list = (List<supportR>)QueryUtil.list(
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
	 * Returns the first support r in the ordered set where groupId = &#63; and dueDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	@Override
	public supportR findByDueDate_First(
			long groupId, Date dueDate,
			OrderByComparator<supportR> orderByComparator)
		throws NoSuchsupportRException {

		supportR supportR = fetchByDueDate_First(
			groupId, dueDate, orderByComparator);

		if (supportR != null) {
			return supportR;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", dueDate=");
		sb.append(dueDate);

		sb.append("}");

		throw new NoSuchsupportRException(sb.toString());
	}

	/**
	 * Returns the first support r in the ordered set where groupId = &#63; and dueDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r, or <code>null</code> if a matching support r could not be found
	 */
	@Override
	public supportR fetchByDueDate_First(
		long groupId, Date dueDate,
		OrderByComparator<supportR> orderByComparator) {

		List<supportR> list = findByDueDate(
			groupId, dueDate, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last support r in the ordered set where groupId = &#63; and dueDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	@Override
	public supportR findByDueDate_Last(
			long groupId, Date dueDate,
			OrderByComparator<supportR> orderByComparator)
		throws NoSuchsupportRException {

		supportR supportR = fetchByDueDate_Last(
			groupId, dueDate, orderByComparator);

		if (supportR != null) {
			return supportR;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", dueDate=");
		sb.append(dueDate);

		sb.append("}");

		throw new NoSuchsupportRException(sb.toString());
	}

	/**
	 * Returns the last support r in the ordered set where groupId = &#63; and dueDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r, or <code>null</code> if a matching support r could not be found
	 */
	@Override
	public supportR fetchByDueDate_Last(
		long groupId, Date dueDate,
		OrderByComparator<supportR> orderByComparator) {

		int count = countByDueDate(groupId, dueDate);

		if (count == 0) {
			return null;
		}

		List<supportR> list = findByDueDate(
			groupId, dueDate, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the support rs before and after the current support r in the ordered set where groupId = &#63; and dueDate = &#63;.
	 *
	 * @param supportRId the primary key of the current support r
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next support r
	 * @throws NoSuchsupportRException if a support r with the primary key could not be found
	 */
	@Override
	public supportR[] findByDueDate_PrevAndNext(
			long supportRId, long groupId, Date dueDate,
			OrderByComparator<supportR> orderByComparator)
		throws NoSuchsupportRException {

		supportR supportR = findByPrimaryKey(supportRId);

		Session session = null;

		try {
			session = openSession();

			supportR[] array = new supportRImpl[3];

			array[0] = getByDueDate_PrevAndNext(
				session, supportR, groupId, dueDate, orderByComparator, true);

			array[1] = supportR;

			array[2] = getByDueDate_PrevAndNext(
				session, supportR, groupId, dueDate, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected supportR getByDueDate_PrevAndNext(
		Session session, supportR supportR, long groupId, Date dueDate,
		OrderByComparator<supportR> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_SUPPORTR_WHERE);

		sb.append(_FINDER_COLUMN_DUEDATE_GROUPID_2);

		boolean bindDueDate = false;

		if (dueDate == null) {
			sb.append(_FINDER_COLUMN_DUEDATE_DUEDATE_1);
		}
		else {
			bindDueDate = true;

			sb.append(_FINDER_COLUMN_DUEDATE_DUEDATE_2);
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
			sb.append(supportRModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(groupId);

		if (bindDueDate) {
			queryPos.add(new Timestamp(dueDate.getTime()));
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(supportR)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<supportR> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the support rs where groupId = &#63; and dueDate = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param dueDate the due date
	 */
	@Override
	public void removeByDueDate(long groupId, Date dueDate) {
		for (supportR supportR :
				findByDueDate(
					groupId, dueDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(supportR);
		}
	}

	/**
	 * Returns the number of support rs where groupId = &#63; and dueDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @return the number of matching support rs
	 */
	@Override
	public int countByDueDate(long groupId, Date dueDate) {
		FinderPath finderPath = _finderPathCountByDueDate;

		Object[] finderArgs = new Object[] {groupId, _getTime(dueDate)};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_SUPPORTR_WHERE);

			sb.append(_FINDER_COLUMN_DUEDATE_GROUPID_2);

			boolean bindDueDate = false;

			if (dueDate == null) {
				sb.append(_FINDER_COLUMN_DUEDATE_DUEDATE_1);
			}
			else {
				bindDueDate = true;

				sb.append(_FINDER_COLUMN_DUEDATE_DUEDATE_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				if (bindDueDate) {
					queryPos.add(new Timestamp(dueDate.getTime()));
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

	private static final String _FINDER_COLUMN_DUEDATE_GROUPID_2 =
		"supportR.groupId = ? AND ";

	private static final String _FINDER_COLUMN_DUEDATE_DUEDATE_1 =
		"supportR.dueDate IS NULL";

	private static final String _FINDER_COLUMN_DUEDATE_DUEDATE_2 =
		"supportR.dueDate = ?";

	private FinderPath _finderPathWithPaginationFindByCreateDateAndDueDate;
	private FinderPath _finderPathWithoutPaginationFindByCreateDateAndDueDate;
	private FinderPath _finderPathCountByCreateDateAndDueDate;

	/**
	 * Returns all the support rs where groupId = &#63; and dueDate = &#63; and createDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @param createDate the create date
	 * @return the matching support rs
	 */
	@Override
	public List<supportR> findByCreateDateAndDueDate(
		long groupId, Date dueDate, Date createDate) {

		return findByCreateDateAndDueDate(
			groupId, dueDate, createDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the support rs where groupId = &#63; and dueDate = &#63; and createDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @param createDate the create date
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @return the range of matching support rs
	 */
	@Override
	public List<supportR> findByCreateDateAndDueDate(
		long groupId, Date dueDate, Date createDate, int start, int end) {

		return findByCreateDateAndDueDate(
			groupId, dueDate, createDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the support rs where groupId = &#63; and dueDate = &#63; and createDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @param createDate the create date
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching support rs
	 */
	@Override
	public List<supportR> findByCreateDateAndDueDate(
		long groupId, Date dueDate, Date createDate, int start, int end,
		OrderByComparator<supportR> orderByComparator) {

		return findByCreateDateAndDueDate(
			groupId, dueDate, createDate, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the support rs where groupId = &#63; and dueDate = &#63; and createDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @param createDate the create date
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching support rs
	 */
	@Override
	public List<supportR> findByCreateDateAndDueDate(
		long groupId, Date dueDate, Date createDate, int start, int end,
		OrderByComparator<supportR> orderByComparator, boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByCreateDateAndDueDate;
				finderArgs = new Object[] {
					groupId, _getTime(dueDate), _getTime(createDate)
				};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByCreateDateAndDueDate;
			finderArgs = new Object[] {
				groupId, _getTime(dueDate), _getTime(createDate), start, end,
				orderByComparator
			};
		}

		List<supportR> list = null;

		if (useFinderCache) {
			list = (List<supportR>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (supportR supportR : list) {
					if ((groupId != supportR.getGroupId()) ||
						!Objects.equals(dueDate, supportR.getDueDate()) ||
						!Objects.equals(createDate, supportR.getCreateDate())) {

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

			sb.append(_SQL_SELECT_SUPPORTR_WHERE);

			sb.append(_FINDER_COLUMN_CREATEDATEANDDUEDATE_GROUPID_2);

			boolean bindDueDate = false;

			if (dueDate == null) {
				sb.append(_FINDER_COLUMN_CREATEDATEANDDUEDATE_DUEDATE_1);
			}
			else {
				bindDueDate = true;

				sb.append(_FINDER_COLUMN_CREATEDATEANDDUEDATE_DUEDATE_2);
			}

			boolean bindCreateDate = false;

			if (createDate == null) {
				sb.append(_FINDER_COLUMN_CREATEDATEANDDUEDATE_CREATEDATE_1);
			}
			else {
				bindCreateDate = true;

				sb.append(_FINDER_COLUMN_CREATEDATEANDDUEDATE_CREATEDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(supportRModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				if (bindDueDate) {
					queryPos.add(new Timestamp(dueDate.getTime()));
				}

				if (bindCreateDate) {
					queryPos.add(new Timestamp(createDate.getTime()));
				}

				list = (List<supportR>)QueryUtil.list(
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
	 * Returns the first support r in the ordered set where groupId = &#63; and dueDate = &#63; and createDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	@Override
	public supportR findByCreateDateAndDueDate_First(
			long groupId, Date dueDate, Date createDate,
			OrderByComparator<supportR> orderByComparator)
		throws NoSuchsupportRException {

		supportR supportR = fetchByCreateDateAndDueDate_First(
			groupId, dueDate, createDate, orderByComparator);

		if (supportR != null) {
			return supportR;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", dueDate=");
		sb.append(dueDate);

		sb.append(", createDate=");
		sb.append(createDate);

		sb.append("}");

		throw new NoSuchsupportRException(sb.toString());
	}

	/**
	 * Returns the first support r in the ordered set where groupId = &#63; and dueDate = &#63; and createDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r, or <code>null</code> if a matching support r could not be found
	 */
	@Override
	public supportR fetchByCreateDateAndDueDate_First(
		long groupId, Date dueDate, Date createDate,
		OrderByComparator<supportR> orderByComparator) {

		List<supportR> list = findByCreateDateAndDueDate(
			groupId, dueDate, createDate, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last support r in the ordered set where groupId = &#63; and dueDate = &#63; and createDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	@Override
	public supportR findByCreateDateAndDueDate_Last(
			long groupId, Date dueDate, Date createDate,
			OrderByComparator<supportR> orderByComparator)
		throws NoSuchsupportRException {

		supportR supportR = fetchByCreateDateAndDueDate_Last(
			groupId, dueDate, createDate, orderByComparator);

		if (supportR != null) {
			return supportR;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", dueDate=");
		sb.append(dueDate);

		sb.append(", createDate=");
		sb.append(createDate);

		sb.append("}");

		throw new NoSuchsupportRException(sb.toString());
	}

	/**
	 * Returns the last support r in the ordered set where groupId = &#63; and dueDate = &#63; and createDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r, or <code>null</code> if a matching support r could not be found
	 */
	@Override
	public supportR fetchByCreateDateAndDueDate_Last(
		long groupId, Date dueDate, Date createDate,
		OrderByComparator<supportR> orderByComparator) {

		int count = countByCreateDateAndDueDate(groupId, dueDate, createDate);

		if (count == 0) {
			return null;
		}

		List<supportR> list = findByCreateDateAndDueDate(
			groupId, dueDate, createDate, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the support rs before and after the current support r in the ordered set where groupId = &#63; and dueDate = &#63; and createDate = &#63;.
	 *
	 * @param supportRId the primary key of the current support r
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next support r
	 * @throws NoSuchsupportRException if a support r with the primary key could not be found
	 */
	@Override
	public supportR[] findByCreateDateAndDueDate_PrevAndNext(
			long supportRId, long groupId, Date dueDate, Date createDate,
			OrderByComparator<supportR> orderByComparator)
		throws NoSuchsupportRException {

		supportR supportR = findByPrimaryKey(supportRId);

		Session session = null;

		try {
			session = openSession();

			supportR[] array = new supportRImpl[3];

			array[0] = getByCreateDateAndDueDate_PrevAndNext(
				session, supportR, groupId, dueDate, createDate,
				orderByComparator, true);

			array[1] = supportR;

			array[2] = getByCreateDateAndDueDate_PrevAndNext(
				session, supportR, groupId, dueDate, createDate,
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

	protected supportR getByCreateDateAndDueDate_PrevAndNext(
		Session session, supportR supportR, long groupId, Date dueDate,
		Date createDate, OrderByComparator<supportR> orderByComparator,
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

		sb.append(_SQL_SELECT_SUPPORTR_WHERE);

		sb.append(_FINDER_COLUMN_CREATEDATEANDDUEDATE_GROUPID_2);

		boolean bindDueDate = false;

		if (dueDate == null) {
			sb.append(_FINDER_COLUMN_CREATEDATEANDDUEDATE_DUEDATE_1);
		}
		else {
			bindDueDate = true;

			sb.append(_FINDER_COLUMN_CREATEDATEANDDUEDATE_DUEDATE_2);
		}

		boolean bindCreateDate = false;

		if (createDate == null) {
			sb.append(_FINDER_COLUMN_CREATEDATEANDDUEDATE_CREATEDATE_1);
		}
		else {
			bindCreateDate = true;

			sb.append(_FINDER_COLUMN_CREATEDATEANDDUEDATE_CREATEDATE_2);
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
			sb.append(supportRModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(groupId);

		if (bindDueDate) {
			queryPos.add(new Timestamp(dueDate.getTime()));
		}

		if (bindCreateDate) {
			queryPos.add(new Timestamp(createDate.getTime()));
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(supportR)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<supportR> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the support rs where groupId = &#63; and dueDate = &#63; and createDate = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @param createDate the create date
	 */
	@Override
	public void removeByCreateDateAndDueDate(
		long groupId, Date dueDate, Date createDate) {

		for (supportR supportR :
				findByCreateDateAndDueDate(
					groupId, dueDate, createDate, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(supportR);
		}
	}

	/**
	 * Returns the number of support rs where groupId = &#63; and dueDate = &#63; and createDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @param createDate the create date
	 * @return the number of matching support rs
	 */
	@Override
	public int countByCreateDateAndDueDate(
		long groupId, Date dueDate, Date createDate) {

		FinderPath finderPath = _finderPathCountByCreateDateAndDueDate;

		Object[] finderArgs = new Object[] {
			groupId, _getTime(dueDate), _getTime(createDate)
		};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_COUNT_SUPPORTR_WHERE);

			sb.append(_FINDER_COLUMN_CREATEDATEANDDUEDATE_GROUPID_2);

			boolean bindDueDate = false;

			if (dueDate == null) {
				sb.append(_FINDER_COLUMN_CREATEDATEANDDUEDATE_DUEDATE_1);
			}
			else {
				bindDueDate = true;

				sb.append(_FINDER_COLUMN_CREATEDATEANDDUEDATE_DUEDATE_2);
			}

			boolean bindCreateDate = false;

			if (createDate == null) {
				sb.append(_FINDER_COLUMN_CREATEDATEANDDUEDATE_CREATEDATE_1);
			}
			else {
				bindCreateDate = true;

				sb.append(_FINDER_COLUMN_CREATEDATEANDDUEDATE_CREATEDATE_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				if (bindDueDate) {
					queryPos.add(new Timestamp(dueDate.getTime()));
				}

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

	private static final String _FINDER_COLUMN_CREATEDATEANDDUEDATE_GROUPID_2 =
		"supportR.groupId = ? AND ";

	private static final String _FINDER_COLUMN_CREATEDATEANDDUEDATE_DUEDATE_1 =
		"supportR.dueDate IS NULL AND ";

	private static final String _FINDER_COLUMN_CREATEDATEANDDUEDATE_DUEDATE_2 =
		"supportR.dueDate = ? AND ";

	private static final String
		_FINDER_COLUMN_CREATEDATEANDDUEDATE_CREATEDATE_1 =
			"supportR.createDate IS NULL";

	private static final String
		_FINDER_COLUMN_CREATEDATEANDDUEDATE_CREATEDATE_2 =
			"supportR.createDate = ?";

	private FinderPath _finderPathWithPaginationFindByStatus;
	private FinderPath _finderPathWithoutPaginationFindByStatus;
	private FinderPath _finderPathCountByStatus;

	/**
	 * Returns all the support rs where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the matching support rs
	 */
	@Override
	public List<supportR> findByStatus(long groupId, String status) {
		return findByStatus(
			groupId, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the support rs where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @return the range of matching support rs
	 */
	@Override
	public List<supportR> findByStatus(
		long groupId, String status, int start, int end) {

		return findByStatus(groupId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the support rs where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching support rs
	 */
	@Override
	public List<supportR> findByStatus(
		long groupId, String status, int start, int end,
		OrderByComparator<supportR> orderByComparator) {

		return findByStatus(
			groupId, status, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the support rs where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching support rs
	 */
	@Override
	public List<supportR> findByStatus(
		long groupId, String status, int start, int end,
		OrderByComparator<supportR> orderByComparator, boolean useFinderCache) {

		status = Objects.toString(status, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByStatus;
				finderArgs = new Object[] {groupId, status};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByStatus;
			finderArgs = new Object[] {
				groupId, status, start, end, orderByComparator
			};
		}

		List<supportR> list = null;

		if (useFinderCache) {
			list = (List<supportR>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (supportR supportR : list) {
					if ((groupId != supportR.getGroupId()) ||
						!status.equals(supportR.getStatus())) {

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

			sb.append(_SQL_SELECT_SUPPORTR_WHERE);

			sb.append(_FINDER_COLUMN_STATUS_GROUPID_2);

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
				sb.append(supportRModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				if (bindStatus) {
					queryPos.add(status);
				}

				list = (List<supportR>)QueryUtil.list(
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
	 * Returns the first support r in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	@Override
	public supportR findByStatus_First(
			long groupId, String status,
			OrderByComparator<supportR> orderByComparator)
		throws NoSuchsupportRException {

		supportR supportR = fetchByStatus_First(
			groupId, status, orderByComparator);

		if (supportR != null) {
			return supportR;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", status=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchsupportRException(sb.toString());
	}

	/**
	 * Returns the first support r in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r, or <code>null</code> if a matching support r could not be found
	 */
	@Override
	public supportR fetchByStatus_First(
		long groupId, String status,
		OrderByComparator<supportR> orderByComparator) {

		List<supportR> list = findByStatus(
			groupId, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last support r in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	@Override
	public supportR findByStatus_Last(
			long groupId, String status,
			OrderByComparator<supportR> orderByComparator)
		throws NoSuchsupportRException {

		supportR supportR = fetchByStatus_Last(
			groupId, status, orderByComparator);

		if (supportR != null) {
			return supportR;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", status=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchsupportRException(sb.toString());
	}

	/**
	 * Returns the last support r in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r, or <code>null</code> if a matching support r could not be found
	 */
	@Override
	public supportR fetchByStatus_Last(
		long groupId, String status,
		OrderByComparator<supportR> orderByComparator) {

		int count = countByStatus(groupId, status);

		if (count == 0) {
			return null;
		}

		List<supportR> list = findByStatus(
			groupId, status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the support rs before and after the current support r in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param supportRId the primary key of the current support r
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next support r
	 * @throws NoSuchsupportRException if a support r with the primary key could not be found
	 */
	@Override
	public supportR[] findByStatus_PrevAndNext(
			long supportRId, long groupId, String status,
			OrderByComparator<supportR> orderByComparator)
		throws NoSuchsupportRException {

		status = Objects.toString(status, "");

		supportR supportR = findByPrimaryKey(supportRId);

		Session session = null;

		try {
			session = openSession();

			supportR[] array = new supportRImpl[3];

			array[0] = getByStatus_PrevAndNext(
				session, supportR, groupId, status, orderByComparator, true);

			array[1] = supportR;

			array[2] = getByStatus_PrevAndNext(
				session, supportR, groupId, status, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected supportR getByStatus_PrevAndNext(
		Session session, supportR supportR, long groupId, String status,
		OrderByComparator<supportR> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_SUPPORTR_WHERE);

		sb.append(_FINDER_COLUMN_STATUS_GROUPID_2);

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
			sb.append(supportRModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(groupId);

		if (bindStatus) {
			queryPos.add(status);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(supportR)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<supportR> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the support rs where groupId = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 */
	@Override
	public void removeByStatus(long groupId, String status) {
		for (supportR supportR :
				findByStatus(
					groupId, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(supportR);
		}
	}

	/**
	 * Returns the number of support rs where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the number of matching support rs
	 */
	@Override
	public int countByStatus(long groupId, String status) {
		status = Objects.toString(status, "");

		FinderPath finderPath = _finderPathCountByStatus;

		Object[] finderArgs = new Object[] {groupId, status};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_SUPPORTR_WHERE);

			sb.append(_FINDER_COLUMN_STATUS_GROUPID_2);

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

				queryPos.add(groupId);

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

	private static final String _FINDER_COLUMN_STATUS_GROUPID_2 =
		"supportR.groupId = ? AND ";

	private static final String _FINDER_COLUMN_STATUS_STATUS_2 =
		"supportR.status = ?";

	private static final String _FINDER_COLUMN_STATUS_STATUS_3 =
		"(supportR.status IS NULL OR supportR.status = '')";

	private FinderPath _finderPathWithPaginationFindByStatusOnly;
	private FinderPath _finderPathWithoutPaginationFindByStatusOnly;
	private FinderPath _finderPathCountByStatusOnly;

	/**
	 * Returns all the support rs where status = &#63;.
	 *
	 * @param status the status
	 * @return the matching support rs
	 */
	@Override
	public List<supportR> findByStatusOnly(String status) {
		return findByStatusOnly(
			status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the support rs where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @return the range of matching support rs
	 */
	@Override
	public List<supportR> findByStatusOnly(String status, int start, int end) {
		return findByStatusOnly(status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the support rs where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching support rs
	 */
	@Override
	public List<supportR> findByStatusOnly(
		String status, int start, int end,
		OrderByComparator<supportR> orderByComparator) {

		return findByStatusOnly(status, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the support rs where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching support rs
	 */
	@Override
	public List<supportR> findByStatusOnly(
		String status, int start, int end,
		OrderByComparator<supportR> orderByComparator, boolean useFinderCache) {

		status = Objects.toString(status, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByStatusOnly;
				finderArgs = new Object[] {status};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByStatusOnly;
			finderArgs = new Object[] {status, start, end, orderByComparator};
		}

		List<supportR> list = null;

		if (useFinderCache) {
			list = (List<supportR>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (supportR supportR : list) {
					if (!status.equals(supportR.getStatus())) {
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

			sb.append(_SQL_SELECT_SUPPORTR_WHERE);

			boolean bindStatus = false;

			if (status.isEmpty()) {
				sb.append(_FINDER_COLUMN_STATUSONLY_STATUS_3);
			}
			else {
				bindStatus = true;

				sb.append(_FINDER_COLUMN_STATUSONLY_STATUS_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(supportRModelImpl.ORDER_BY_JPQL);
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

				list = (List<supportR>)QueryUtil.list(
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
	 * Returns the first support r in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	@Override
	public supportR findByStatusOnly_First(
			String status, OrderByComparator<supportR> orderByComparator)
		throws NoSuchsupportRException {

		supportR supportR = fetchByStatusOnly_First(status, orderByComparator);

		if (supportR != null) {
			return supportR;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("status=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchsupportRException(sb.toString());
	}

	/**
	 * Returns the first support r in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r, or <code>null</code> if a matching support r could not be found
	 */
	@Override
	public supportR fetchByStatusOnly_First(
		String status, OrderByComparator<supportR> orderByComparator) {

		List<supportR> list = findByStatusOnly(status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last support r in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	@Override
	public supportR findByStatusOnly_Last(
			String status, OrderByComparator<supportR> orderByComparator)
		throws NoSuchsupportRException {

		supportR supportR = fetchByStatusOnly_Last(status, orderByComparator);

		if (supportR != null) {
			return supportR;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("status=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchsupportRException(sb.toString());
	}

	/**
	 * Returns the last support r in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r, or <code>null</code> if a matching support r could not be found
	 */
	@Override
	public supportR fetchByStatusOnly_Last(
		String status, OrderByComparator<supportR> orderByComparator) {

		int count = countByStatusOnly(status);

		if (count == 0) {
			return null;
		}

		List<supportR> list = findByStatusOnly(
			status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the support rs before and after the current support r in the ordered set where status = &#63;.
	 *
	 * @param supportRId the primary key of the current support r
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next support r
	 * @throws NoSuchsupportRException if a support r with the primary key could not be found
	 */
	@Override
	public supportR[] findByStatusOnly_PrevAndNext(
			long supportRId, String status,
			OrderByComparator<supportR> orderByComparator)
		throws NoSuchsupportRException {

		status = Objects.toString(status, "");

		supportR supportR = findByPrimaryKey(supportRId);

		Session session = null;

		try {
			session = openSession();

			supportR[] array = new supportRImpl[3];

			array[0] = getByStatusOnly_PrevAndNext(
				session, supportR, status, orderByComparator, true);

			array[1] = supportR;

			array[2] = getByStatusOnly_PrevAndNext(
				session, supportR, status, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected supportR getByStatusOnly_PrevAndNext(
		Session session, supportR supportR, String status,
		OrderByComparator<supportR> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_SUPPORTR_WHERE);

		boolean bindStatus = false;

		if (status.isEmpty()) {
			sb.append(_FINDER_COLUMN_STATUSONLY_STATUS_3);
		}
		else {
			bindStatus = true;

			sb.append(_FINDER_COLUMN_STATUSONLY_STATUS_2);
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
			sb.append(supportRModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(supportR)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<supportR> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the support rs where status = &#63; from the database.
	 *
	 * @param status the status
	 */
	@Override
	public void removeByStatusOnly(String status) {
		for (supportR supportR :
				findByStatusOnly(
					status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(supportR);
		}
	}

	/**
	 * Returns the number of support rs where status = &#63;.
	 *
	 * @param status the status
	 * @return the number of matching support rs
	 */
	@Override
	public int countByStatusOnly(String status) {
		status = Objects.toString(status, "");

		FinderPath finderPath = _finderPathCountByStatusOnly;

		Object[] finderArgs = new Object[] {status};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_SUPPORTR_WHERE);

			boolean bindStatus = false;

			if (status.isEmpty()) {
				sb.append(_FINDER_COLUMN_STATUSONLY_STATUS_3);
			}
			else {
				bindStatus = true;

				sb.append(_FINDER_COLUMN_STATUSONLY_STATUS_2);
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

	private static final String _FINDER_COLUMN_STATUSONLY_STATUS_2 =
		"supportR.status = ?";

	private static final String _FINDER_COLUMN_STATUSONLY_STATUS_3 =
		"(supportR.status IS NULL OR supportR.status = '')";

	private FinderPath _finderPathWithPaginationFindByLink;
	private FinderPath _finderPathWithoutPaginationFindByLink;
	private FinderPath _finderPathCountByLink;

	/**
	 * Returns all the support rs where groupId = &#63; and link = &#63;.
	 *
	 * @param groupId the group ID
	 * @param link the link
	 * @return the matching support rs
	 */
	@Override
	public List<supportR> findByLink(long groupId, String link) {
		return findByLink(
			groupId, link, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the support rs where groupId = &#63; and link = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param link the link
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @return the range of matching support rs
	 */
	@Override
	public List<supportR> findByLink(
		long groupId, String link, int start, int end) {

		return findByLink(groupId, link, start, end, null);
	}

	/**
	 * Returns an ordered range of all the support rs where groupId = &#63; and link = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param link the link
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching support rs
	 */
	@Override
	public List<supportR> findByLink(
		long groupId, String link, int start, int end,
		OrderByComparator<supportR> orderByComparator) {

		return findByLink(groupId, link, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the support rs where groupId = &#63; and link = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param link the link
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching support rs
	 */
	@Override
	public List<supportR> findByLink(
		long groupId, String link, int start, int end,
		OrderByComparator<supportR> orderByComparator, boolean useFinderCache) {

		link = Objects.toString(link, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByLink;
				finderArgs = new Object[] {groupId, link};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByLink;
			finderArgs = new Object[] {
				groupId, link, start, end, orderByComparator
			};
		}

		List<supportR> list = null;

		if (useFinderCache) {
			list = (List<supportR>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (supportR supportR : list) {
					if ((groupId != supportR.getGroupId()) ||
						!link.equals(supportR.getLink())) {

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

			sb.append(_SQL_SELECT_SUPPORTR_WHERE);

			sb.append(_FINDER_COLUMN_LINK_GROUPID_2);

			boolean bindLink = false;

			if (link.isEmpty()) {
				sb.append(_FINDER_COLUMN_LINK_LINK_3);
			}
			else {
				bindLink = true;

				sb.append(_FINDER_COLUMN_LINK_LINK_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(supportRModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				if (bindLink) {
					queryPos.add(link);
				}

				list = (List<supportR>)QueryUtil.list(
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
	 * Returns the first support r in the ordered set where groupId = &#63; and link = &#63;.
	 *
	 * @param groupId the group ID
	 * @param link the link
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	@Override
	public supportR findByLink_First(
			long groupId, String link,
			OrderByComparator<supportR> orderByComparator)
		throws NoSuchsupportRException {

		supportR supportR = fetchByLink_First(groupId, link, orderByComparator);

		if (supportR != null) {
			return supportR;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", link=");
		sb.append(link);

		sb.append("}");

		throw new NoSuchsupportRException(sb.toString());
	}

	/**
	 * Returns the first support r in the ordered set where groupId = &#63; and link = &#63;.
	 *
	 * @param groupId the group ID
	 * @param link the link
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r, or <code>null</code> if a matching support r could not be found
	 */
	@Override
	public supportR fetchByLink_First(
		long groupId, String link,
		OrderByComparator<supportR> orderByComparator) {

		List<supportR> list = findByLink(
			groupId, link, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last support r in the ordered set where groupId = &#63; and link = &#63;.
	 *
	 * @param groupId the group ID
	 * @param link the link
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	@Override
	public supportR findByLink_Last(
			long groupId, String link,
			OrderByComparator<supportR> orderByComparator)
		throws NoSuchsupportRException {

		supportR supportR = fetchByLink_Last(groupId, link, orderByComparator);

		if (supportR != null) {
			return supportR;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", link=");
		sb.append(link);

		sb.append("}");

		throw new NoSuchsupportRException(sb.toString());
	}

	/**
	 * Returns the last support r in the ordered set where groupId = &#63; and link = &#63;.
	 *
	 * @param groupId the group ID
	 * @param link the link
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r, or <code>null</code> if a matching support r could not be found
	 */
	@Override
	public supportR fetchByLink_Last(
		long groupId, String link,
		OrderByComparator<supportR> orderByComparator) {

		int count = countByLink(groupId, link);

		if (count == 0) {
			return null;
		}

		List<supportR> list = findByLink(
			groupId, link, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the support rs before and after the current support r in the ordered set where groupId = &#63; and link = &#63;.
	 *
	 * @param supportRId the primary key of the current support r
	 * @param groupId the group ID
	 * @param link the link
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next support r
	 * @throws NoSuchsupportRException if a support r with the primary key could not be found
	 */
	@Override
	public supportR[] findByLink_PrevAndNext(
			long supportRId, long groupId, String link,
			OrderByComparator<supportR> orderByComparator)
		throws NoSuchsupportRException {

		link = Objects.toString(link, "");

		supportR supportR = findByPrimaryKey(supportRId);

		Session session = null;

		try {
			session = openSession();

			supportR[] array = new supportRImpl[3];

			array[0] = getByLink_PrevAndNext(
				session, supportR, groupId, link, orderByComparator, true);

			array[1] = supportR;

			array[2] = getByLink_PrevAndNext(
				session, supportR, groupId, link, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected supportR getByLink_PrevAndNext(
		Session session, supportR supportR, long groupId, String link,
		OrderByComparator<supportR> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_SUPPORTR_WHERE);

		sb.append(_FINDER_COLUMN_LINK_GROUPID_2);

		boolean bindLink = false;

		if (link.isEmpty()) {
			sb.append(_FINDER_COLUMN_LINK_LINK_3);
		}
		else {
			bindLink = true;

			sb.append(_FINDER_COLUMN_LINK_LINK_2);
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
			sb.append(supportRModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(groupId);

		if (bindLink) {
			queryPos.add(link);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(supportR)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<supportR> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the support rs where groupId = &#63; and link = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param link the link
	 */
	@Override
	public void removeByLink(long groupId, String link) {
		for (supportR supportR :
				findByLink(
					groupId, link, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(supportR);
		}
	}

	/**
	 * Returns the number of support rs where groupId = &#63; and link = &#63;.
	 *
	 * @param groupId the group ID
	 * @param link the link
	 * @return the number of matching support rs
	 */
	@Override
	public int countByLink(long groupId, String link) {
		link = Objects.toString(link, "");

		FinderPath finderPath = _finderPathCountByLink;

		Object[] finderArgs = new Object[] {groupId, link};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_SUPPORTR_WHERE);

			sb.append(_FINDER_COLUMN_LINK_GROUPID_2);

			boolean bindLink = false;

			if (link.isEmpty()) {
				sb.append(_FINDER_COLUMN_LINK_LINK_3);
			}
			else {
				bindLink = true;

				sb.append(_FINDER_COLUMN_LINK_LINK_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				if (bindLink) {
					queryPos.add(link);
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

	private static final String _FINDER_COLUMN_LINK_GROUPID_2 =
		"supportR.groupId = ? AND ";

	private static final String _FINDER_COLUMN_LINK_LINK_2 =
		"supportR.link = ?";

	private static final String _FINDER_COLUMN_LINK_LINK_3 =
		"(supportR.link IS NULL OR supportR.link = '')";

	public supportRPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");
		dbColumnNames.put("type", "type_");

		setDBColumnNames(dbColumnNames);

		setModelClass(supportR.class);

		setModelImplClass(supportRImpl.class);
		setModelPKClass(long.class);
	}

	/**
	 * Caches the support r in the entity cache if it is enabled.
	 *
	 * @param supportR the support r
	 */
	@Override
	public void cacheResult(supportR supportR) {
		entityCache.putResult(
			supportRImpl.class, supportR.getPrimaryKey(), supportR);

		finderCache.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {supportR.getUuid(), supportR.getGroupId()}, supportR);
	}

	/**
	 * Caches the support rs in the entity cache if it is enabled.
	 *
	 * @param supportRs the support rs
	 */
	@Override
	public void cacheResult(List<supportR> supportRs) {
		for (supportR supportR : supportRs) {
			if (entityCache.getResult(
					supportRImpl.class, supportR.getPrimaryKey()) == null) {

				cacheResult(supportR);
			}
		}
	}

	/**
	 * Clears the cache for all support rs.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(supportRImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the support r.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(supportR supportR) {
		entityCache.removeResult(supportRImpl.class, supportR);
	}

	@Override
	public void clearCache(List<supportR> supportRs) {
		for (supportR supportR : supportRs) {
			entityCache.removeResult(supportRImpl.class, supportR);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(supportRImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		supportRModelImpl supportRModelImpl) {

		Object[] args = new Object[] {
			supportRModelImpl.getUuid(), supportRModelImpl.getGroupId()
		};

		finderCache.putResult(
			_finderPathCountByUUID_G, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByUUID_G, args, supportRModelImpl, false);
	}

	/**
	 * Creates a new support r with the primary key. Does not add the support r to the database.
	 *
	 * @param supportRId the primary key for the new support r
	 * @return the new support r
	 */
	@Override
	public supportR create(long supportRId) {
		supportR supportR = new supportRImpl();

		supportR.setNew(true);
		supportR.setPrimaryKey(supportRId);

		String uuid = PortalUUIDUtil.generate();

		supportR.setUuid(uuid);

		supportR.setCompanyId(CompanyThreadLocal.getCompanyId());

		return supportR;
	}

	/**
	 * Removes the support r with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param supportRId the primary key of the support r
	 * @return the support r that was removed
	 * @throws NoSuchsupportRException if a support r with the primary key could not be found
	 */
	@Override
	public supportR remove(long supportRId) throws NoSuchsupportRException {
		return remove((Serializable)supportRId);
	}

	/**
	 * Removes the support r with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the support r
	 * @return the support r that was removed
	 * @throws NoSuchsupportRException if a support r with the primary key could not be found
	 */
	@Override
	public supportR remove(Serializable primaryKey)
		throws NoSuchsupportRException {

		Session session = null;

		try {
			session = openSession();

			supportR supportR = (supportR)session.get(
				supportRImpl.class, primaryKey);

			if (supportR == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchsupportRException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(supportR);
		}
		catch (NoSuchsupportRException noSuchEntityException) {
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
	protected supportR removeImpl(supportR supportR) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(supportR)) {
				supportR = (supportR)session.get(
					supportRImpl.class, supportR.getPrimaryKeyObj());
			}

			if (supportR != null) {
				session.delete(supportR);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (supportR != null) {
			clearCache(supportR);
		}

		return supportR;
	}

	@Override
	public supportR updateImpl(supportR supportR) {
		boolean isNew = supportR.isNew();

		if (!(supportR instanceof supportRModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(supportR.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(supportR);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in supportR proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom supportR implementation " +
					supportR.getClass());
		}

		supportRModelImpl supportRModelImpl = (supportRModelImpl)supportR;

		if (Validator.isNull(supportR.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			supportR.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (supportR.getCreateDate() == null)) {
			if (serviceContext == null) {
				supportR.setCreateDate(now);
			}
			else {
				supportR.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!supportRModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				supportR.setModifiedDate(now);
			}
			else {
				supportR.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(supportR);
			}
			else {
				supportR = (supportR)session.merge(supportR);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			supportRImpl.class, supportRModelImpl, false, true);

		cacheUniqueFindersCache(supportRModelImpl);

		if (isNew) {
			supportR.setNew(false);
		}

		supportR.resetOriginalValues();

		return supportR;
	}

	/**
	 * Returns the support r with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the support r
	 * @return the support r
	 * @throws NoSuchsupportRException if a support r with the primary key could not be found
	 */
	@Override
	public supportR findByPrimaryKey(Serializable primaryKey)
		throws NoSuchsupportRException {

		supportR supportR = fetchByPrimaryKey(primaryKey);

		if (supportR == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchsupportRException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return supportR;
	}

	/**
	 * Returns the support r with the primary key or throws a <code>NoSuchsupportRException</code> if it could not be found.
	 *
	 * @param supportRId the primary key of the support r
	 * @return the support r
	 * @throws NoSuchsupportRException if a support r with the primary key could not be found
	 */
	@Override
	public supportR findByPrimaryKey(long supportRId)
		throws NoSuchsupportRException {

		return findByPrimaryKey((Serializable)supportRId);
	}

	/**
	 * Returns the support r with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param supportRId the primary key of the support r
	 * @return the support r, or <code>null</code> if a support r with the primary key could not be found
	 */
	@Override
	public supportR fetchByPrimaryKey(long supportRId) {
		return fetchByPrimaryKey((Serializable)supportRId);
	}

	/**
	 * Returns all the support rs.
	 *
	 * @return the support rs
	 */
	@Override
	public List<supportR> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the support rs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @return the range of support rs
	 */
	@Override
	public List<supportR> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the support rs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of support rs
	 */
	@Override
	public List<supportR> findAll(
		int start, int end, OrderByComparator<supportR> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the support rs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>supportRModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of support rs
	 * @param end the upper bound of the range of support rs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of support rs
	 */
	@Override
	public List<supportR> findAll(
		int start, int end, OrderByComparator<supportR> orderByComparator,
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

		List<supportR> list = null;

		if (useFinderCache) {
			list = (List<supportR>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_SUPPORTR);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_SUPPORTR;

				sql = sql.concat(supportRModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<supportR>)QueryUtil.list(
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
	 * Removes all the support rs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (supportR supportR : findAll()) {
			remove(supportR);
		}
	}

	/**
	 * Returns the number of support rs.
	 *
	 * @return the number of support rs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_SUPPORTR);

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
		return "supportRId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_SUPPORTR;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return supportRModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the support r persistence.
	 */
	@Activate
	public void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		_argumentsResolverServiceRegistration = _bundleContext.registerService(
			ArgumentsResolver.class, new supportRModelArgumentsResolver(),
			MapUtil.singletonDictionary(
				"model.class.name", supportR.class.getName()));

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

		_finderPathWithPaginationFindBySupportingResourceTitle =
			_createFinderPath(
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
				"findBySupportingResourceTitle",
				new String[] {
					Long.class.getName(), String.class.getName(),
					Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				},
				new String[] {"groupId", "title"}, true);

		_finderPathWithoutPaginationFindBySupportingResourceTitle =
			_createFinderPath(
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"findBySupportingResourceTitle",
				new String[] {Long.class.getName(), String.class.getName()},
				new String[] {"groupId", "title"}, true);

		_finderPathCountBySupportingResourceTitle = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBySupportingResourceTitle",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"groupId", "title"}, false);

		_finderPathWithPaginationFindBySupportingResourceType =
			_createFinderPath(
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
				"findBySupportingResourceType",
				new String[] {
					Long.class.getName(), String.class.getName(),
					Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				},
				new String[] {"groupId", "type_"}, true);

		_finderPathWithoutPaginationFindBySupportingResourceType =
			_createFinderPath(
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"findBySupportingResourceType",
				new String[] {Long.class.getName(), String.class.getName()},
				new String[] {"groupId", "type_"}, true);

		_finderPathCountBySupportingResourceType = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBySupportingResourceType",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"groupId", "type_"}, false);

		_finderPathWithPaginationFindByCreateDate = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCreateDate",
			new String[] {
				Long.class.getName(), Date.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"groupId", "createDate"}, true);

		_finderPathWithoutPaginationFindByCreateDate = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCreateDate",
			new String[] {Long.class.getName(), Date.class.getName()},
			new String[] {"groupId", "createDate"}, true);

		_finderPathCountByCreateDate = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCreateDate",
			new String[] {Long.class.getName(), Date.class.getName()},
			new String[] {"groupId", "createDate"}, false);

		_finderPathWithPaginationFindByDueDate = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByDueDate",
			new String[] {
				Long.class.getName(), Date.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"groupId", "dueDate"}, true);

		_finderPathWithoutPaginationFindByDueDate = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByDueDate",
			new String[] {Long.class.getName(), Date.class.getName()},
			new String[] {"groupId", "dueDate"}, true);

		_finderPathCountByDueDate = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDueDate",
			new String[] {Long.class.getName(), Date.class.getName()},
			new String[] {"groupId", "dueDate"}, false);

		_finderPathWithPaginationFindByCreateDateAndDueDate = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCreateDateAndDueDate",
			new String[] {
				Long.class.getName(), Date.class.getName(),
				Date.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"groupId", "dueDate", "createDate"}, true);

		_finderPathWithoutPaginationFindByCreateDateAndDueDate =
			_createFinderPath(
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"findByCreateDateAndDueDate",
				new String[] {
					Long.class.getName(), Date.class.getName(),
					Date.class.getName()
				},
				new String[] {"groupId", "dueDate", "createDate"}, true);

		_finderPathCountByCreateDateAndDueDate = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCreateDateAndDueDate",
			new String[] {
				Long.class.getName(), Date.class.getName(), Date.class.getName()
			},
			new String[] {"groupId", "dueDate", "createDate"}, false);

		_finderPathWithPaginationFindByStatus = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByStatus",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"groupId", "status"}, true);

		_finderPathWithoutPaginationFindByStatus = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByStatus",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"groupId", "status"}, true);

		_finderPathCountByStatus = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByStatus",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"groupId", "status"}, false);

		_finderPathWithPaginationFindByStatusOnly = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByStatusOnly",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"status"}, true);

		_finderPathWithoutPaginationFindByStatusOnly = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByStatusOnly",
			new String[] {String.class.getName()}, new String[] {"status"},
			true);

		_finderPathCountByStatusOnly = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByStatusOnly",
			new String[] {String.class.getName()}, new String[] {"status"},
			false);

		_finderPathWithPaginationFindByLink = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByLink",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"groupId", "link"}, true);

		_finderPathWithoutPaginationFindByLink = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByLink",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"groupId", "link"}, true);

		_finderPathCountByLink = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByLink",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"groupId", "link"}, false);
	}

	@Deactivate
	public void deactivate() {
		entityCache.removeCache(supportRImpl.class.getName());

		_argumentsResolverServiceRegistration.unregister();

		for (ServiceRegistration<FinderPath> serviceRegistration :
				_serviceRegistrations) {

			serviceRegistration.unregister();
		}
	}

	@Override
	@Reference(
		target = RECsrPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = RECsrPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = RECsrPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
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

	private static final String _SQL_SELECT_SUPPORTR =
		"SELECT supportR FROM supportR supportR";

	private static final String _SQL_SELECT_SUPPORTR_WHERE =
		"SELECT supportR FROM supportR supportR WHERE ";

	private static final String _SQL_COUNT_SUPPORTR =
		"SELECT COUNT(supportR) FROM supportR supportR";

	private static final String _SQL_COUNT_SUPPORTR_WHERE =
		"SELECT COUNT(supportR) FROM supportR supportR WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "supportR.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No supportR exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No supportR exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		supportRPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid", "type"});

	static {
		try {
			Class.forName(RECsrPersistenceConstants.class.getName());
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

	private static class supportRModelArgumentsResolver
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

			supportRModelImpl supportRModelImpl = (supportRModelImpl)baseModel;

			long columnBitmask = supportRModelImpl.getColumnBitmask();

			if (!checkColumn || (columnBitmask == 0)) {
				return _getValue(supportRModelImpl, columnNames, original);
			}

			Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
				finderPath);

			if (finderPathColumnBitmask == null) {
				finderPathColumnBitmask = 0L;

				for (String columnName : columnNames) {
					finderPathColumnBitmask |=
						supportRModelImpl.getColumnBitmask(columnName);
				}

				_finderPathColumnBitmasksCache.put(
					finderPath, finderPathColumnBitmask);
			}

			if ((columnBitmask & finderPathColumnBitmask) != 0) {
				return _getValue(supportRModelImpl, columnNames, original);
			}

			return null;
		}

		private Object[] _getValue(
			supportRModelImpl supportRModelImpl, String[] columnNames,
			boolean original) {

			Object[] arguments = new Object[columnNames.length];

			for (int i = 0; i < arguments.length; i++) {
				String columnName = columnNames[i];

				if (original) {
					arguments[i] = supportRModelImpl.getColumnOriginalValue(
						columnName);
				}
				else {
					arguments[i] = supportRModelImpl.getColumnValue(columnName);
				}
			}

			return arguments;
		}

		private static Map<FinderPath, Long> _finderPathColumnBitmasksCache =
			new ConcurrentHashMap<>();

	}

}