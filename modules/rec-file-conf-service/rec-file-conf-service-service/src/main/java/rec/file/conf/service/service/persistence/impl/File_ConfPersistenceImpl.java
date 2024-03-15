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

package rec.file.conf.service.service.persistence.impl;

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
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
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

import rec.file.conf.service.exception.NoSuchFile_ConfException;
import rec.file.conf.service.model.File_Conf;
import rec.file.conf.service.model.impl.File_ConfImpl;
import rec.file.conf.service.model.impl.File_ConfModelImpl;
import rec.file.conf.service.service.persistence.File_ConfPersistence;
import rec.file.conf.service.service.persistence.impl.constants.RECPersistenceConstants;

/**
 * The persistence implementation for the file_ conf service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = File_ConfPersistence.class)
public class File_ConfPersistenceImpl
	extends BasePersistenceImpl<File_Conf> implements File_ConfPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>File_ConfUtil</code> to access the file_ conf persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		File_ConfImpl.class.getName();

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
	 * Returns all the file_ confs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching file_ confs
	 */
	@Override
	public List<File_Conf> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the file_ confs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>File_ConfModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of file_ confs
	 * @param end the upper bound of the range of file_ confs (not inclusive)
	 * @return the range of matching file_ confs
	 */
	@Override
	public List<File_Conf> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the file_ confs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>File_ConfModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of file_ confs
	 * @param end the upper bound of the range of file_ confs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching file_ confs
	 */
	@Override
	public List<File_Conf> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<File_Conf> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the file_ confs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>File_ConfModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of file_ confs
	 * @param end the upper bound of the range of file_ confs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching file_ confs
	 */
	@Override
	public List<File_Conf> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<File_Conf> orderByComparator,
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

		List<File_Conf> list = null;

		if (useFinderCache) {
			list = (List<File_Conf>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (File_Conf file_Conf : list) {
					if (!uuid.equals(file_Conf.getUuid())) {
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

			sb.append(_SQL_SELECT_FILE_CONF_WHERE);

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
				sb.append(File_ConfModelImpl.ORDER_BY_JPQL);
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

				list = (List<File_Conf>)QueryUtil.list(
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
	 * Returns the first file_ conf in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching file_ conf
	 * @throws NoSuchFile_ConfException if a matching file_ conf could not be found
	 */
	@Override
	public File_Conf findByUuid_First(
			String uuid, OrderByComparator<File_Conf> orderByComparator)
		throws NoSuchFile_ConfException {

		File_Conf file_Conf = fetchByUuid_First(uuid, orderByComparator);

		if (file_Conf != null) {
			return file_Conf;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchFile_ConfException(sb.toString());
	}

	/**
	 * Returns the first file_ conf in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching file_ conf, or <code>null</code> if a matching file_ conf could not be found
	 */
	@Override
	public File_Conf fetchByUuid_First(
		String uuid, OrderByComparator<File_Conf> orderByComparator) {

		List<File_Conf> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last file_ conf in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching file_ conf
	 * @throws NoSuchFile_ConfException if a matching file_ conf could not be found
	 */
	@Override
	public File_Conf findByUuid_Last(
			String uuid, OrderByComparator<File_Conf> orderByComparator)
		throws NoSuchFile_ConfException {

		File_Conf file_Conf = fetchByUuid_Last(uuid, orderByComparator);

		if (file_Conf != null) {
			return file_Conf;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchFile_ConfException(sb.toString());
	}

	/**
	 * Returns the last file_ conf in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching file_ conf, or <code>null</code> if a matching file_ conf could not be found
	 */
	@Override
	public File_Conf fetchByUuid_Last(
		String uuid, OrderByComparator<File_Conf> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<File_Conf> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the file_ confs before and after the current file_ conf in the ordered set where uuid = &#63;.
	 *
	 * @param docConfigId the primary key of the current file_ conf
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next file_ conf
	 * @throws NoSuchFile_ConfException if a file_ conf with the primary key could not be found
	 */
	@Override
	public File_Conf[] findByUuid_PrevAndNext(
			long docConfigId, String uuid,
			OrderByComparator<File_Conf> orderByComparator)
		throws NoSuchFile_ConfException {

		uuid = Objects.toString(uuid, "");

		File_Conf file_Conf = findByPrimaryKey(docConfigId);

		Session session = null;

		try {
			session = openSession();

			File_Conf[] array = new File_ConfImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, file_Conf, uuid, orderByComparator, true);

			array[1] = file_Conf;

			array[2] = getByUuid_PrevAndNext(
				session, file_Conf, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected File_Conf getByUuid_PrevAndNext(
		Session session, File_Conf file_Conf, String uuid,
		OrderByComparator<File_Conf> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_FILE_CONF_WHERE);

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
			sb.append(File_ConfModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(file_Conf)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<File_Conf> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the file_ confs where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (File_Conf file_Conf :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(file_Conf);
		}
	}

	/**
	 * Returns the number of file_ confs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching file_ confs
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_FILE_CONF_WHERE);

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
		"file_Conf.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(file_Conf.uuid IS NULL OR file_Conf.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;
	private FinderPath _finderPathCountByUUID_G;

	/**
	 * Returns the file_ conf where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchFile_ConfException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching file_ conf
	 * @throws NoSuchFile_ConfException if a matching file_ conf could not be found
	 */
	@Override
	public File_Conf findByUUID_G(String uuid, long groupId)
		throws NoSuchFile_ConfException {

		File_Conf file_Conf = fetchByUUID_G(uuid, groupId);

		if (file_Conf == null) {
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

			throw new NoSuchFile_ConfException(sb.toString());
		}

		return file_Conf;
	}

	/**
	 * Returns the file_ conf where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching file_ conf, or <code>null</code> if a matching file_ conf could not be found
	 */
	@Override
	public File_Conf fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the file_ conf where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching file_ conf, or <code>null</code> if a matching file_ conf could not be found
	 */
	@Override
	public File_Conf fetchByUUID_G(
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

		if (result instanceof File_Conf) {
			File_Conf file_Conf = (File_Conf)result;

			if (!Objects.equals(uuid, file_Conf.getUuid()) ||
				(groupId != file_Conf.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_FILE_CONF_WHERE);

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

				List<File_Conf> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByUUID_G, finderArgs, list);
					}
				}
				else {
					File_Conf file_Conf = list.get(0);

					result = file_Conf;

					cacheResult(file_Conf);
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
			return (File_Conf)result;
		}
	}

	/**
	 * Removes the file_ conf where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the file_ conf that was removed
	 */
	@Override
	public File_Conf removeByUUID_G(String uuid, long groupId)
		throws NoSuchFile_ConfException {

		File_Conf file_Conf = findByUUID_G(uuid, groupId);

		return remove(file_Conf);
	}

	/**
	 * Returns the number of file_ confs where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching file_ confs
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUUID_G;

		Object[] finderArgs = new Object[] {uuid, groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_FILE_CONF_WHERE);

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
		"file_Conf.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(file_Conf.uuid IS NULL OR file_Conf.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"file_Conf.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the file_ confs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching file_ confs
	 */
	@Override
	public List<File_Conf> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the file_ confs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>File_ConfModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of file_ confs
	 * @param end the upper bound of the range of file_ confs (not inclusive)
	 * @return the range of matching file_ confs
	 */
	@Override
	public List<File_Conf> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the file_ confs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>File_ConfModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of file_ confs
	 * @param end the upper bound of the range of file_ confs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching file_ confs
	 */
	@Override
	public List<File_Conf> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<File_Conf> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the file_ confs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>File_ConfModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of file_ confs
	 * @param end the upper bound of the range of file_ confs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching file_ confs
	 */
	@Override
	public List<File_Conf> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<File_Conf> orderByComparator,
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

		List<File_Conf> list = null;

		if (useFinderCache) {
			list = (List<File_Conf>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (File_Conf file_Conf : list) {
					if (!uuid.equals(file_Conf.getUuid()) ||
						(companyId != file_Conf.getCompanyId())) {

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

			sb.append(_SQL_SELECT_FILE_CONF_WHERE);

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
				sb.append(File_ConfModelImpl.ORDER_BY_JPQL);
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

				list = (List<File_Conf>)QueryUtil.list(
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
	 * Returns the first file_ conf in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching file_ conf
	 * @throws NoSuchFile_ConfException if a matching file_ conf could not be found
	 */
	@Override
	public File_Conf findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<File_Conf> orderByComparator)
		throws NoSuchFile_ConfException {

		File_Conf file_Conf = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (file_Conf != null) {
			return file_Conf;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchFile_ConfException(sb.toString());
	}

	/**
	 * Returns the first file_ conf in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching file_ conf, or <code>null</code> if a matching file_ conf could not be found
	 */
	@Override
	public File_Conf fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<File_Conf> orderByComparator) {

		List<File_Conf> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last file_ conf in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching file_ conf
	 * @throws NoSuchFile_ConfException if a matching file_ conf could not be found
	 */
	@Override
	public File_Conf findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<File_Conf> orderByComparator)
		throws NoSuchFile_ConfException {

		File_Conf file_Conf = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (file_Conf != null) {
			return file_Conf;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchFile_ConfException(sb.toString());
	}

	/**
	 * Returns the last file_ conf in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching file_ conf, or <code>null</code> if a matching file_ conf could not be found
	 */
	@Override
	public File_Conf fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<File_Conf> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<File_Conf> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the file_ confs before and after the current file_ conf in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param docConfigId the primary key of the current file_ conf
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next file_ conf
	 * @throws NoSuchFile_ConfException if a file_ conf with the primary key could not be found
	 */
	@Override
	public File_Conf[] findByUuid_C_PrevAndNext(
			long docConfigId, String uuid, long companyId,
			OrderByComparator<File_Conf> orderByComparator)
		throws NoSuchFile_ConfException {

		uuid = Objects.toString(uuid, "");

		File_Conf file_Conf = findByPrimaryKey(docConfigId);

		Session session = null;

		try {
			session = openSession();

			File_Conf[] array = new File_ConfImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, file_Conf, uuid, companyId, orderByComparator, true);

			array[1] = file_Conf;

			array[2] = getByUuid_C_PrevAndNext(
				session, file_Conf, uuid, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected File_Conf getByUuid_C_PrevAndNext(
		Session session, File_Conf file_Conf, String uuid, long companyId,
		OrderByComparator<File_Conf> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_FILE_CONF_WHERE);

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
			sb.append(File_ConfModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(file_Conf)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<File_Conf> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the file_ confs where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (File_Conf file_Conf :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(file_Conf);
		}
	}

	/**
	 * Returns the number of file_ confs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching file_ confs
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_FILE_CONF_WHERE);

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
		"file_Conf.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(file_Conf.uuid IS NULL OR file_Conf.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"file_Conf.companyId = ?";

	private FinderPath _finderPathFetchBydocConfName;
	private FinderPath _finderPathCountBydocConfName;

	/**
	 * Returns the file_ conf where docConfName = &#63; or throws a <code>NoSuchFile_ConfException</code> if it could not be found.
	 *
	 * @param docConfName the doc conf name
	 * @return the matching file_ conf
	 * @throws NoSuchFile_ConfException if a matching file_ conf could not be found
	 */
	@Override
	public File_Conf findBydocConfName(String docConfName)
		throws NoSuchFile_ConfException {

		File_Conf file_Conf = fetchBydocConfName(docConfName);

		if (file_Conf == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("docConfName=");
			sb.append(docConfName);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchFile_ConfException(sb.toString());
		}

		return file_Conf;
	}

	/**
	 * Returns the file_ conf where docConfName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param docConfName the doc conf name
	 * @return the matching file_ conf, or <code>null</code> if a matching file_ conf could not be found
	 */
	@Override
	public File_Conf fetchBydocConfName(String docConfName) {
		return fetchBydocConfName(docConfName, true);
	}

	/**
	 * Returns the file_ conf where docConfName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param docConfName the doc conf name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching file_ conf, or <code>null</code> if a matching file_ conf could not be found
	 */
	@Override
	public File_Conf fetchBydocConfName(
		String docConfName, boolean useFinderCache) {

		docConfName = Objects.toString(docConfName, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {docConfName};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchBydocConfName, finderArgs, this);
		}

		if (result instanceof File_Conf) {
			File_Conf file_Conf = (File_Conf)result;

			if (!Objects.equals(docConfName, file_Conf.getDocConfName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_FILE_CONF_WHERE);

			boolean bindDocConfName = false;

			if (docConfName.isEmpty()) {
				sb.append(_FINDER_COLUMN_DOCCONFNAME_DOCCONFNAME_3);
			}
			else {
				bindDocConfName = true;

				sb.append(_FINDER_COLUMN_DOCCONFNAME_DOCCONFNAME_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindDocConfName) {
					queryPos.add(docConfName);
				}

				List<File_Conf> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchBydocConfName, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {docConfName};
							}

							_log.warn(
								"File_ConfPersistenceImpl.fetchBydocConfName(String, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					File_Conf file_Conf = list.get(0);

					result = file_Conf;

					cacheResult(file_Conf);
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
			return (File_Conf)result;
		}
	}

	/**
	 * Removes the file_ conf where docConfName = &#63; from the database.
	 *
	 * @param docConfName the doc conf name
	 * @return the file_ conf that was removed
	 */
	@Override
	public File_Conf removeBydocConfName(String docConfName)
		throws NoSuchFile_ConfException {

		File_Conf file_Conf = findBydocConfName(docConfName);

		return remove(file_Conf);
	}

	/**
	 * Returns the number of file_ confs where docConfName = &#63;.
	 *
	 * @param docConfName the doc conf name
	 * @return the number of matching file_ confs
	 */
	@Override
	public int countBydocConfName(String docConfName) {
		docConfName = Objects.toString(docConfName, "");

		FinderPath finderPath = _finderPathCountBydocConfName;

		Object[] finderArgs = new Object[] {docConfName};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_FILE_CONF_WHERE);

			boolean bindDocConfName = false;

			if (docConfName.isEmpty()) {
				sb.append(_FINDER_COLUMN_DOCCONFNAME_DOCCONFNAME_3);
			}
			else {
				bindDocConfName = true;

				sb.append(_FINDER_COLUMN_DOCCONFNAME_DOCCONFNAME_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindDocConfName) {
					queryPos.add(docConfName);
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

	private static final String _FINDER_COLUMN_DOCCONFNAME_DOCCONFNAME_2 =
		"file_Conf.docConfName = ?";

	private static final String _FINDER_COLUMN_DOCCONFNAME_DOCCONFNAME_3 =
		"(file_Conf.docConfName IS NULL OR file_Conf.docConfName = '')";

	private FinderPath _finderPathFetchBydocFileType;
	private FinderPath _finderPathCountBydocFileType;

	/**
	 * Returns the file_ conf where docFileType = &#63; or throws a <code>NoSuchFile_ConfException</code> if it could not be found.
	 *
	 * @param docFileType the doc file type
	 * @return the matching file_ conf
	 * @throws NoSuchFile_ConfException if a matching file_ conf could not be found
	 */
	@Override
	public File_Conf findBydocFileType(String docFileType)
		throws NoSuchFile_ConfException {

		File_Conf file_Conf = fetchBydocFileType(docFileType);

		if (file_Conf == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("docFileType=");
			sb.append(docFileType);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchFile_ConfException(sb.toString());
		}

		return file_Conf;
	}

	/**
	 * Returns the file_ conf where docFileType = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param docFileType the doc file type
	 * @return the matching file_ conf, or <code>null</code> if a matching file_ conf could not be found
	 */
	@Override
	public File_Conf fetchBydocFileType(String docFileType) {
		return fetchBydocFileType(docFileType, true);
	}

	/**
	 * Returns the file_ conf where docFileType = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param docFileType the doc file type
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching file_ conf, or <code>null</code> if a matching file_ conf could not be found
	 */
	@Override
	public File_Conf fetchBydocFileType(
		String docFileType, boolean useFinderCache) {

		docFileType = Objects.toString(docFileType, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {docFileType};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchBydocFileType, finderArgs, this);
		}

		if (result instanceof File_Conf) {
			File_Conf file_Conf = (File_Conf)result;

			if (!Objects.equals(docFileType, file_Conf.getDocFileType())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_FILE_CONF_WHERE);

			boolean bindDocFileType = false;

			if (docFileType.isEmpty()) {
				sb.append(_FINDER_COLUMN_DOCFILETYPE_DOCFILETYPE_3);
			}
			else {
				bindDocFileType = true;

				sb.append(_FINDER_COLUMN_DOCFILETYPE_DOCFILETYPE_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindDocFileType) {
					queryPos.add(docFileType);
				}

				List<File_Conf> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchBydocFileType, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {docFileType};
							}

							_log.warn(
								"File_ConfPersistenceImpl.fetchBydocFileType(String, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					File_Conf file_Conf = list.get(0);

					result = file_Conf;

					cacheResult(file_Conf);
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
			return (File_Conf)result;
		}
	}

	/**
	 * Removes the file_ conf where docFileType = &#63; from the database.
	 *
	 * @param docFileType the doc file type
	 * @return the file_ conf that was removed
	 */
	@Override
	public File_Conf removeBydocFileType(String docFileType)
		throws NoSuchFile_ConfException {

		File_Conf file_Conf = findBydocFileType(docFileType);

		return remove(file_Conf);
	}

	/**
	 * Returns the number of file_ confs where docFileType = &#63;.
	 *
	 * @param docFileType the doc file type
	 * @return the number of matching file_ confs
	 */
	@Override
	public int countBydocFileType(String docFileType) {
		docFileType = Objects.toString(docFileType, "");

		FinderPath finderPath = _finderPathCountBydocFileType;

		Object[] finderArgs = new Object[] {docFileType};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_FILE_CONF_WHERE);

			boolean bindDocFileType = false;

			if (docFileType.isEmpty()) {
				sb.append(_FINDER_COLUMN_DOCFILETYPE_DOCFILETYPE_3);
			}
			else {
				bindDocFileType = true;

				sb.append(_FINDER_COLUMN_DOCFILETYPE_DOCFILETYPE_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindDocFileType) {
					queryPos.add(docFileType);
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

	private static final String _FINDER_COLUMN_DOCFILETYPE_DOCFILETYPE_2 =
		"file_Conf.docFileType = ?";

	private static final String _FINDER_COLUMN_DOCFILETYPE_DOCFILETYPE_3 =
		"(file_Conf.docFileType IS NULL OR file_Conf.docFileType = '')";

	public File_ConfPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

		setDBColumnNames(dbColumnNames);

		setModelClass(File_Conf.class);

		setModelImplClass(File_ConfImpl.class);
		setModelPKClass(long.class);
	}

	/**
	 * Caches the file_ conf in the entity cache if it is enabled.
	 *
	 * @param file_Conf the file_ conf
	 */
	@Override
	public void cacheResult(File_Conf file_Conf) {
		entityCache.putResult(
			File_ConfImpl.class, file_Conf.getPrimaryKey(), file_Conf);

		finderCache.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {file_Conf.getUuid(), file_Conf.getGroupId()},
			file_Conf);

		finderCache.putResult(
			_finderPathFetchBydocConfName,
			new Object[] {file_Conf.getDocConfName()}, file_Conf);

		finderCache.putResult(
			_finderPathFetchBydocFileType,
			new Object[] {file_Conf.getDocFileType()}, file_Conf);
	}

	/**
	 * Caches the file_ confs in the entity cache if it is enabled.
	 *
	 * @param file_Confs the file_ confs
	 */
	@Override
	public void cacheResult(List<File_Conf> file_Confs) {
		for (File_Conf file_Conf : file_Confs) {
			if (entityCache.getResult(
					File_ConfImpl.class, file_Conf.getPrimaryKey()) == null) {

				cacheResult(file_Conf);
			}
		}
	}

	/**
	 * Clears the cache for all file_ confs.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(File_ConfImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the file_ conf.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(File_Conf file_Conf) {
		entityCache.removeResult(File_ConfImpl.class, file_Conf);
	}

	@Override
	public void clearCache(List<File_Conf> file_Confs) {
		for (File_Conf file_Conf : file_Confs) {
			entityCache.removeResult(File_ConfImpl.class, file_Conf);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(File_ConfImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		File_ConfModelImpl file_ConfModelImpl) {

		Object[] args = new Object[] {
			file_ConfModelImpl.getUuid(), file_ConfModelImpl.getGroupId()
		};

		finderCache.putResult(
			_finderPathCountByUUID_G, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByUUID_G, args, file_ConfModelImpl, false);

		args = new Object[] {file_ConfModelImpl.getDocConfName()};

		finderCache.putResult(
			_finderPathCountBydocConfName, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchBydocConfName, args, file_ConfModelImpl, false);

		args = new Object[] {file_ConfModelImpl.getDocFileType()};

		finderCache.putResult(
			_finderPathCountBydocFileType, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchBydocFileType, args, file_ConfModelImpl, false);
	}

	/**
	 * Creates a new file_ conf with the primary key. Does not add the file_ conf to the database.
	 *
	 * @param docConfigId the primary key for the new file_ conf
	 * @return the new file_ conf
	 */
	@Override
	public File_Conf create(long docConfigId) {
		File_Conf file_Conf = new File_ConfImpl();

		file_Conf.setNew(true);
		file_Conf.setPrimaryKey(docConfigId);

		String uuid = PortalUUIDUtil.generate();

		file_Conf.setUuid(uuid);

		file_Conf.setCompanyId(CompanyThreadLocal.getCompanyId());

		return file_Conf;
	}

	/**
	 * Removes the file_ conf with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param docConfigId the primary key of the file_ conf
	 * @return the file_ conf that was removed
	 * @throws NoSuchFile_ConfException if a file_ conf with the primary key could not be found
	 */
	@Override
	public File_Conf remove(long docConfigId) throws NoSuchFile_ConfException {
		return remove((Serializable)docConfigId);
	}

	/**
	 * Removes the file_ conf with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the file_ conf
	 * @return the file_ conf that was removed
	 * @throws NoSuchFile_ConfException if a file_ conf with the primary key could not be found
	 */
	@Override
	public File_Conf remove(Serializable primaryKey)
		throws NoSuchFile_ConfException {

		Session session = null;

		try {
			session = openSession();

			File_Conf file_Conf = (File_Conf)session.get(
				File_ConfImpl.class, primaryKey);

			if (file_Conf == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchFile_ConfException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(file_Conf);
		}
		catch (NoSuchFile_ConfException noSuchEntityException) {
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
	protected File_Conf removeImpl(File_Conf file_Conf) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(file_Conf)) {
				file_Conf = (File_Conf)session.get(
					File_ConfImpl.class, file_Conf.getPrimaryKeyObj());
			}

			if (file_Conf != null) {
				session.delete(file_Conf);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (file_Conf != null) {
			clearCache(file_Conf);
		}

		return file_Conf;
	}

	@Override
	public File_Conf updateImpl(File_Conf file_Conf) {
		boolean isNew = file_Conf.isNew();

		if (!(file_Conf instanceof File_ConfModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(file_Conf.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(file_Conf);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in file_Conf proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom File_Conf implementation " +
					file_Conf.getClass());
		}

		File_ConfModelImpl file_ConfModelImpl = (File_ConfModelImpl)file_Conf;

		if (Validator.isNull(file_Conf.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			file_Conf.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (file_Conf.getCreateDate() == null)) {
			if (serviceContext == null) {
				file_Conf.setCreateDate(now);
			}
			else {
				file_Conf.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!file_ConfModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				file_Conf.setModifiedDate(now);
			}
			else {
				file_Conf.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(file_Conf);
			}
			else {
				file_Conf = (File_Conf)session.merge(file_Conf);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			File_ConfImpl.class, file_ConfModelImpl, false, true);

		cacheUniqueFindersCache(file_ConfModelImpl);

		if (isNew) {
			file_Conf.setNew(false);
		}

		file_Conf.resetOriginalValues();

		return file_Conf;
	}

	/**
	 * Returns the file_ conf with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the file_ conf
	 * @return the file_ conf
	 * @throws NoSuchFile_ConfException if a file_ conf with the primary key could not be found
	 */
	@Override
	public File_Conf findByPrimaryKey(Serializable primaryKey)
		throws NoSuchFile_ConfException {

		File_Conf file_Conf = fetchByPrimaryKey(primaryKey);

		if (file_Conf == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchFile_ConfException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return file_Conf;
	}

	/**
	 * Returns the file_ conf with the primary key or throws a <code>NoSuchFile_ConfException</code> if it could not be found.
	 *
	 * @param docConfigId the primary key of the file_ conf
	 * @return the file_ conf
	 * @throws NoSuchFile_ConfException if a file_ conf with the primary key could not be found
	 */
	@Override
	public File_Conf findByPrimaryKey(long docConfigId)
		throws NoSuchFile_ConfException {

		return findByPrimaryKey((Serializable)docConfigId);
	}

	/**
	 * Returns the file_ conf with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param docConfigId the primary key of the file_ conf
	 * @return the file_ conf, or <code>null</code> if a file_ conf with the primary key could not be found
	 */
	@Override
	public File_Conf fetchByPrimaryKey(long docConfigId) {
		return fetchByPrimaryKey((Serializable)docConfigId);
	}

	/**
	 * Returns all the file_ confs.
	 *
	 * @return the file_ confs
	 */
	@Override
	public List<File_Conf> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the file_ confs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>File_ConfModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of file_ confs
	 * @param end the upper bound of the range of file_ confs (not inclusive)
	 * @return the range of file_ confs
	 */
	@Override
	public List<File_Conf> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the file_ confs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>File_ConfModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of file_ confs
	 * @param end the upper bound of the range of file_ confs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of file_ confs
	 */
	@Override
	public List<File_Conf> findAll(
		int start, int end, OrderByComparator<File_Conf> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the file_ confs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>File_ConfModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of file_ confs
	 * @param end the upper bound of the range of file_ confs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of file_ confs
	 */
	@Override
	public List<File_Conf> findAll(
		int start, int end, OrderByComparator<File_Conf> orderByComparator,
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

		List<File_Conf> list = null;

		if (useFinderCache) {
			list = (List<File_Conf>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_FILE_CONF);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_FILE_CONF;

				sql = sql.concat(File_ConfModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<File_Conf>)QueryUtil.list(
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
	 * Removes all the file_ confs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (File_Conf file_Conf : findAll()) {
			remove(file_Conf);
		}
	}

	/**
	 * Returns the number of file_ confs.
	 *
	 * @return the number of file_ confs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_FILE_CONF);

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
		return "docConfigId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_FILE_CONF;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return File_ConfModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the file_ conf persistence.
	 */
	@Activate
	public void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		_argumentsResolverServiceRegistration = _bundleContext.registerService(
			ArgumentsResolver.class, new File_ConfModelArgumentsResolver(),
			MapUtil.singletonDictionary(
				"model.class.name", File_Conf.class.getName()));

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

		_finderPathFetchBydocConfName = _createFinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchBydocConfName",
			new String[] {String.class.getName()}, new String[] {"docConfName"},
			true);

		_finderPathCountBydocConfName = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBydocConfName",
			new String[] {String.class.getName()}, new String[] {"docConfName"},
			false);

		_finderPathFetchBydocFileType = _createFinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchBydocFileType",
			new String[] {String.class.getName()}, new String[] {"docFileType"},
			true);

		_finderPathCountBydocFileType = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBydocFileType",
			new String[] {String.class.getName()}, new String[] {"docFileType"},
			false);
	}

	@Deactivate
	public void deactivate() {
		entityCache.removeCache(File_ConfImpl.class.getName());

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

	private static final String _SQL_SELECT_FILE_CONF =
		"SELECT file_Conf FROM File_Conf file_Conf";

	private static final String _SQL_SELECT_FILE_CONF_WHERE =
		"SELECT file_Conf FROM File_Conf file_Conf WHERE ";

	private static final String _SQL_COUNT_FILE_CONF =
		"SELECT COUNT(file_Conf) FROM File_Conf file_Conf";

	private static final String _SQL_COUNT_FILE_CONF_WHERE =
		"SELECT COUNT(file_Conf) FROM File_Conf file_Conf WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "file_Conf.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No File_Conf exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No File_Conf exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		File_ConfPersistenceImpl.class);

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

	private static class File_ConfModelArgumentsResolver
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

			File_ConfModelImpl file_ConfModelImpl =
				(File_ConfModelImpl)baseModel;

			long columnBitmask = file_ConfModelImpl.getColumnBitmask();

			if (!checkColumn || (columnBitmask == 0)) {
				return _getValue(file_ConfModelImpl, columnNames, original);
			}

			Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
				finderPath);

			if (finderPathColumnBitmask == null) {
				finderPathColumnBitmask = 0L;

				for (String columnName : columnNames) {
					finderPathColumnBitmask |=
						file_ConfModelImpl.getColumnBitmask(columnName);
				}

				_finderPathColumnBitmasksCache.put(
					finderPath, finderPathColumnBitmask);
			}

			if ((columnBitmask & finderPathColumnBitmask) != 0) {
				return _getValue(file_ConfModelImpl, columnNames, original);
			}

			return null;
		}

		private Object[] _getValue(
			File_ConfModelImpl file_ConfModelImpl, String[] columnNames,
			boolean original) {

			Object[] arguments = new Object[columnNames.length];

			for (int i = 0; i < arguments.length; i++) {
				String columnName = columnNames[i];

				if (original) {
					arguments[i] = file_ConfModelImpl.getColumnOriginalValue(
						columnName);
				}
				else {
					arguments[i] = file_ConfModelImpl.getColumnValue(
						columnName);
				}
			}

			return arguments;
		}

		private static Map<FinderPath, Long> _finderPathColumnBitmasksCache =
			new ConcurrentHashMap<>();

	}

}