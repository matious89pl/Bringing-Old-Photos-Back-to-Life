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

package rec.everis.forms.service.service.persistence.impl;

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

import rec.everis.forms.service.exception.NoSuchMaintenanceActivityFormsException;
import rec.everis.forms.service.model.MaintenanceActivityForms;
import rec.everis.forms.service.model.impl.MaintenanceActivityFormsImpl;
import rec.everis.forms.service.model.impl.MaintenanceActivityFormsModelImpl;
import rec.everis.forms.service.service.persistence.MaintenanceActivityFormsPersistence;
import rec.everis.forms.service.service.persistence.impl.constants.AMAFPersistenceConstants;

/**
 * The persistence implementation for the maintenance activity forms service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = MaintenanceActivityFormsPersistence.class)
public class MaintenanceActivityFormsPersistenceImpl
	extends BasePersistenceImpl<MaintenanceActivityForms>
	implements MaintenanceActivityFormsPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>MaintenanceActivityFormsUtil</code> to access the maintenance activity forms persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		MaintenanceActivityFormsImpl.class.getName();

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
	 * Returns all the maintenance activity formses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching maintenance activity formses
	 */
	@Override
	public List<MaintenanceActivityForms> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the maintenance activity formses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MaintenanceActivityFormsModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of maintenance activity formses
	 * @param end the upper bound of the range of maintenance activity formses (not inclusive)
	 * @return the range of matching maintenance activity formses
	 */
	@Override
	public List<MaintenanceActivityForms> findByUuid(
		String uuid, int start, int end) {

		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the maintenance activity formses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MaintenanceActivityFormsModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of maintenance activity formses
	 * @param end the upper bound of the range of maintenance activity formses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching maintenance activity formses
	 */
	@Override
	public List<MaintenanceActivityForms> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<MaintenanceActivityForms> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the maintenance activity formses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MaintenanceActivityFormsModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of maintenance activity formses
	 * @param end the upper bound of the range of maintenance activity formses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching maintenance activity formses
	 */
	@Override
	public List<MaintenanceActivityForms> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<MaintenanceActivityForms> orderByComparator,
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

		List<MaintenanceActivityForms> list = null;

		if (useFinderCache) {
			list = (List<MaintenanceActivityForms>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (MaintenanceActivityForms maintenanceActivityForms : list) {
					if (!uuid.equals(maintenanceActivityForms.getUuid())) {
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

			sb.append(_SQL_SELECT_MAINTENANCEACTIVITYFORMS_WHERE);

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
				sb.append(MaintenanceActivityFormsModelImpl.ORDER_BY_JPQL);
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

				list = (List<MaintenanceActivityForms>)QueryUtil.list(
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
	 * Returns the first maintenance activity forms in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching maintenance activity forms
	 * @throws NoSuchMaintenanceActivityFormsException if a matching maintenance activity forms could not be found
	 */
	@Override
	public MaintenanceActivityForms findByUuid_First(
			String uuid,
			OrderByComparator<MaintenanceActivityForms> orderByComparator)
		throws NoSuchMaintenanceActivityFormsException {

		MaintenanceActivityForms maintenanceActivityForms = fetchByUuid_First(
			uuid, orderByComparator);

		if (maintenanceActivityForms != null) {
			return maintenanceActivityForms;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchMaintenanceActivityFormsException(sb.toString());
	}

	/**
	 * Returns the first maintenance activity forms in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching maintenance activity forms, or <code>null</code> if a matching maintenance activity forms could not be found
	 */
	@Override
	public MaintenanceActivityForms fetchByUuid_First(
		String uuid,
		OrderByComparator<MaintenanceActivityForms> orderByComparator) {

		List<MaintenanceActivityForms> list = findByUuid(
			uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last maintenance activity forms in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching maintenance activity forms
	 * @throws NoSuchMaintenanceActivityFormsException if a matching maintenance activity forms could not be found
	 */
	@Override
	public MaintenanceActivityForms findByUuid_Last(
			String uuid,
			OrderByComparator<MaintenanceActivityForms> orderByComparator)
		throws NoSuchMaintenanceActivityFormsException {

		MaintenanceActivityForms maintenanceActivityForms = fetchByUuid_Last(
			uuid, orderByComparator);

		if (maintenanceActivityForms != null) {
			return maintenanceActivityForms;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchMaintenanceActivityFormsException(sb.toString());
	}

	/**
	 * Returns the last maintenance activity forms in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching maintenance activity forms, or <code>null</code> if a matching maintenance activity forms could not be found
	 */
	@Override
	public MaintenanceActivityForms fetchByUuid_Last(
		String uuid,
		OrderByComparator<MaintenanceActivityForms> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<MaintenanceActivityForms> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the maintenance activity formses before and after the current maintenance activity forms in the ordered set where uuid = &#63;.
	 *
	 * @param maintenanceactivityformId the primary key of the current maintenance activity forms
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next maintenance activity forms
	 * @throws NoSuchMaintenanceActivityFormsException if a maintenance activity forms with the primary key could not be found
	 */
	@Override
	public MaintenanceActivityForms[] findByUuid_PrevAndNext(
			long maintenanceactivityformId, String uuid,
			OrderByComparator<MaintenanceActivityForms> orderByComparator)
		throws NoSuchMaintenanceActivityFormsException {

		uuid = Objects.toString(uuid, "");

		MaintenanceActivityForms maintenanceActivityForms = findByPrimaryKey(
			maintenanceactivityformId);

		Session session = null;

		try {
			session = openSession();

			MaintenanceActivityForms[] array =
				new MaintenanceActivityFormsImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, maintenanceActivityForms, uuid, orderByComparator,
				true);

			array[1] = maintenanceActivityForms;

			array[2] = getByUuid_PrevAndNext(
				session, maintenanceActivityForms, uuid, orderByComparator,
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

	protected MaintenanceActivityForms getByUuid_PrevAndNext(
		Session session, MaintenanceActivityForms maintenanceActivityForms,
		String uuid,
		OrderByComparator<MaintenanceActivityForms> orderByComparator,
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

		sb.append(_SQL_SELECT_MAINTENANCEACTIVITYFORMS_WHERE);

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
			sb.append(MaintenanceActivityFormsModelImpl.ORDER_BY_JPQL);
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
						maintenanceActivityForms)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<MaintenanceActivityForms> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the maintenance activity formses where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (MaintenanceActivityForms maintenanceActivityForms :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(maintenanceActivityForms);
		}
	}

	/**
	 * Returns the number of maintenance activity formses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching maintenance activity formses
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_MAINTENANCEACTIVITYFORMS_WHERE);

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
		"maintenanceActivityForms.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(maintenanceActivityForms.uuid IS NULL OR maintenanceActivityForms.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;
	private FinderPath _finderPathCountByUUID_G;

	/**
	 * Returns the maintenance activity forms where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchMaintenanceActivityFormsException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching maintenance activity forms
	 * @throws NoSuchMaintenanceActivityFormsException if a matching maintenance activity forms could not be found
	 */
	@Override
	public MaintenanceActivityForms findByUUID_G(String uuid, long groupId)
		throws NoSuchMaintenanceActivityFormsException {

		MaintenanceActivityForms maintenanceActivityForms = fetchByUUID_G(
			uuid, groupId);

		if (maintenanceActivityForms == null) {
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

			throw new NoSuchMaintenanceActivityFormsException(sb.toString());
		}

		return maintenanceActivityForms;
	}

	/**
	 * Returns the maintenance activity forms where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching maintenance activity forms, or <code>null</code> if a matching maintenance activity forms could not be found
	 */
	@Override
	public MaintenanceActivityForms fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the maintenance activity forms where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching maintenance activity forms, or <code>null</code> if a matching maintenance activity forms could not be found
	 */
	@Override
	public MaintenanceActivityForms fetchByUUID_G(
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

		if (result instanceof MaintenanceActivityForms) {
			MaintenanceActivityForms maintenanceActivityForms =
				(MaintenanceActivityForms)result;

			if (!Objects.equals(uuid, maintenanceActivityForms.getUuid()) ||
				(groupId != maintenanceActivityForms.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_MAINTENANCEACTIVITYFORMS_WHERE);

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

				List<MaintenanceActivityForms> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByUUID_G, finderArgs, list);
					}
				}
				else {
					MaintenanceActivityForms maintenanceActivityForms =
						list.get(0);

					result = maintenanceActivityForms;

					cacheResult(maintenanceActivityForms);
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
			return (MaintenanceActivityForms)result;
		}
	}

	/**
	 * Removes the maintenance activity forms where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the maintenance activity forms that was removed
	 */
	@Override
	public MaintenanceActivityForms removeByUUID_G(String uuid, long groupId)
		throws NoSuchMaintenanceActivityFormsException {

		MaintenanceActivityForms maintenanceActivityForms = findByUUID_G(
			uuid, groupId);

		return remove(maintenanceActivityForms);
	}

	/**
	 * Returns the number of maintenance activity formses where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching maintenance activity formses
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUUID_G;

		Object[] finderArgs = new Object[] {uuid, groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_MAINTENANCEACTIVITYFORMS_WHERE);

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
		"maintenanceActivityForms.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(maintenanceActivityForms.uuid IS NULL OR maintenanceActivityForms.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"maintenanceActivityForms.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the maintenance activity formses where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching maintenance activity formses
	 */
	@Override
	public List<MaintenanceActivityForms> findByUuid_C(
		String uuid, long companyId) {

		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the maintenance activity formses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MaintenanceActivityFormsModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of maintenance activity formses
	 * @param end the upper bound of the range of maintenance activity formses (not inclusive)
	 * @return the range of matching maintenance activity formses
	 */
	@Override
	public List<MaintenanceActivityForms> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the maintenance activity formses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MaintenanceActivityFormsModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of maintenance activity formses
	 * @param end the upper bound of the range of maintenance activity formses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching maintenance activity formses
	 */
	@Override
	public List<MaintenanceActivityForms> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<MaintenanceActivityForms> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the maintenance activity formses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MaintenanceActivityFormsModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of maintenance activity formses
	 * @param end the upper bound of the range of maintenance activity formses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching maintenance activity formses
	 */
	@Override
	public List<MaintenanceActivityForms> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<MaintenanceActivityForms> orderByComparator,
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

		List<MaintenanceActivityForms> list = null;

		if (useFinderCache) {
			list = (List<MaintenanceActivityForms>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (MaintenanceActivityForms maintenanceActivityForms : list) {
					if (!uuid.equals(maintenanceActivityForms.getUuid()) ||
						(companyId !=
							maintenanceActivityForms.getCompanyId())) {

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

			sb.append(_SQL_SELECT_MAINTENANCEACTIVITYFORMS_WHERE);

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
				sb.append(MaintenanceActivityFormsModelImpl.ORDER_BY_JPQL);
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

				list = (List<MaintenanceActivityForms>)QueryUtil.list(
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
	 * Returns the first maintenance activity forms in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching maintenance activity forms
	 * @throws NoSuchMaintenanceActivityFormsException if a matching maintenance activity forms could not be found
	 */
	@Override
	public MaintenanceActivityForms findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<MaintenanceActivityForms> orderByComparator)
		throws NoSuchMaintenanceActivityFormsException {

		MaintenanceActivityForms maintenanceActivityForms = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (maintenanceActivityForms != null) {
			return maintenanceActivityForms;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchMaintenanceActivityFormsException(sb.toString());
	}

	/**
	 * Returns the first maintenance activity forms in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching maintenance activity forms, or <code>null</code> if a matching maintenance activity forms could not be found
	 */
	@Override
	public MaintenanceActivityForms fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<MaintenanceActivityForms> orderByComparator) {

		List<MaintenanceActivityForms> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last maintenance activity forms in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching maintenance activity forms
	 * @throws NoSuchMaintenanceActivityFormsException if a matching maintenance activity forms could not be found
	 */
	@Override
	public MaintenanceActivityForms findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<MaintenanceActivityForms> orderByComparator)
		throws NoSuchMaintenanceActivityFormsException {

		MaintenanceActivityForms maintenanceActivityForms = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (maintenanceActivityForms != null) {
			return maintenanceActivityForms;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchMaintenanceActivityFormsException(sb.toString());
	}

	/**
	 * Returns the last maintenance activity forms in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching maintenance activity forms, or <code>null</code> if a matching maintenance activity forms could not be found
	 */
	@Override
	public MaintenanceActivityForms fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<MaintenanceActivityForms> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<MaintenanceActivityForms> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the maintenance activity formses before and after the current maintenance activity forms in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param maintenanceactivityformId the primary key of the current maintenance activity forms
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next maintenance activity forms
	 * @throws NoSuchMaintenanceActivityFormsException if a maintenance activity forms with the primary key could not be found
	 */
	@Override
	public MaintenanceActivityForms[] findByUuid_C_PrevAndNext(
			long maintenanceactivityformId, String uuid, long companyId,
			OrderByComparator<MaintenanceActivityForms> orderByComparator)
		throws NoSuchMaintenanceActivityFormsException {

		uuid = Objects.toString(uuid, "");

		MaintenanceActivityForms maintenanceActivityForms = findByPrimaryKey(
			maintenanceactivityformId);

		Session session = null;

		try {
			session = openSession();

			MaintenanceActivityForms[] array =
				new MaintenanceActivityFormsImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, maintenanceActivityForms, uuid, companyId,
				orderByComparator, true);

			array[1] = maintenanceActivityForms;

			array[2] = getByUuid_C_PrevAndNext(
				session, maintenanceActivityForms, uuid, companyId,
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

	protected MaintenanceActivityForms getByUuid_C_PrevAndNext(
		Session session, MaintenanceActivityForms maintenanceActivityForms,
		String uuid, long companyId,
		OrderByComparator<MaintenanceActivityForms> orderByComparator,
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

		sb.append(_SQL_SELECT_MAINTENANCEACTIVITYFORMS_WHERE);

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
			sb.append(MaintenanceActivityFormsModelImpl.ORDER_BY_JPQL);
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
						maintenanceActivityForms)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<MaintenanceActivityForms> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the maintenance activity formses where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (MaintenanceActivityForms maintenanceActivityForms :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(maintenanceActivityForms);
		}
	}

	/**
	 * Returns the number of maintenance activity formses where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching maintenance activity formses
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_MAINTENANCEACTIVITYFORMS_WHERE);

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
		"maintenanceActivityForms.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(maintenanceActivityForms.uuid IS NULL OR maintenanceActivityForms.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"maintenanceActivityForms.companyId = ?";

	private FinderPath
		_finderPathWithPaginationFindByformInstanceRecordVersionId;
	private FinderPath
		_finderPathWithoutPaginationFindByformInstanceRecordVersionId;
	private FinderPath _finderPathCountByformInstanceRecordVersionId;

	/**
	 * Returns all the maintenance activity formses where formInstanceRecordVersionId = &#63;.
	 *
	 * @param formInstanceRecordVersionId the form instance record version ID
	 * @return the matching maintenance activity formses
	 */
	@Override
	public List<MaintenanceActivityForms> findByformInstanceRecordVersionId(
		long formInstanceRecordVersionId) {

		return findByformInstanceRecordVersionId(
			formInstanceRecordVersionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the maintenance activity formses where formInstanceRecordVersionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MaintenanceActivityFormsModelImpl</code>.
	 * </p>
	 *
	 * @param formInstanceRecordVersionId the form instance record version ID
	 * @param start the lower bound of the range of maintenance activity formses
	 * @param end the upper bound of the range of maintenance activity formses (not inclusive)
	 * @return the range of matching maintenance activity formses
	 */
	@Override
	public List<MaintenanceActivityForms> findByformInstanceRecordVersionId(
		long formInstanceRecordVersionId, int start, int end) {

		return findByformInstanceRecordVersionId(
			formInstanceRecordVersionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the maintenance activity formses where formInstanceRecordVersionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MaintenanceActivityFormsModelImpl</code>.
	 * </p>
	 *
	 * @param formInstanceRecordVersionId the form instance record version ID
	 * @param start the lower bound of the range of maintenance activity formses
	 * @param end the upper bound of the range of maintenance activity formses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching maintenance activity formses
	 */
	@Override
	public List<MaintenanceActivityForms> findByformInstanceRecordVersionId(
		long formInstanceRecordVersionId, int start, int end,
		OrderByComparator<MaintenanceActivityForms> orderByComparator) {

		return findByformInstanceRecordVersionId(
			formInstanceRecordVersionId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the maintenance activity formses where formInstanceRecordVersionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MaintenanceActivityFormsModelImpl</code>.
	 * </p>
	 *
	 * @param formInstanceRecordVersionId the form instance record version ID
	 * @param start the lower bound of the range of maintenance activity formses
	 * @param end the upper bound of the range of maintenance activity formses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching maintenance activity formses
	 */
	@Override
	public List<MaintenanceActivityForms> findByformInstanceRecordVersionId(
		long formInstanceRecordVersionId, int start, int end,
		OrderByComparator<MaintenanceActivityForms> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByformInstanceRecordVersionId;
				finderArgs = new Object[] {formInstanceRecordVersionId};
			}
		}
		else if (useFinderCache) {
			finderPath =
				_finderPathWithPaginationFindByformInstanceRecordVersionId;
			finderArgs = new Object[] {
				formInstanceRecordVersionId, start, end, orderByComparator
			};
		}

		List<MaintenanceActivityForms> list = null;

		if (useFinderCache) {
			list = (List<MaintenanceActivityForms>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (MaintenanceActivityForms maintenanceActivityForms : list) {
					if (formInstanceRecordVersionId !=
							maintenanceActivityForms.
								getFormInstanceRecordVersionId()) {

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

			sb.append(_SQL_SELECT_MAINTENANCEACTIVITYFORMS_WHERE);

			sb.append(
				_FINDER_COLUMN_FORMINSTANCERECORDVERSIONID_FORMINSTANCERECORDVERSIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(MaintenanceActivityFormsModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(formInstanceRecordVersionId);

				list = (List<MaintenanceActivityForms>)QueryUtil.list(
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
	 * Returns the first maintenance activity forms in the ordered set where formInstanceRecordVersionId = &#63;.
	 *
	 * @param formInstanceRecordVersionId the form instance record version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching maintenance activity forms
	 * @throws NoSuchMaintenanceActivityFormsException if a matching maintenance activity forms could not be found
	 */
	@Override
	public MaintenanceActivityForms findByformInstanceRecordVersionId_First(
			long formInstanceRecordVersionId,
			OrderByComparator<MaintenanceActivityForms> orderByComparator)
		throws NoSuchMaintenanceActivityFormsException {

		MaintenanceActivityForms maintenanceActivityForms =
			fetchByformInstanceRecordVersionId_First(
				formInstanceRecordVersionId, orderByComparator);

		if (maintenanceActivityForms != null) {
			return maintenanceActivityForms;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("formInstanceRecordVersionId=");
		sb.append(formInstanceRecordVersionId);

		sb.append("}");

		throw new NoSuchMaintenanceActivityFormsException(sb.toString());
	}

	/**
	 * Returns the first maintenance activity forms in the ordered set where formInstanceRecordVersionId = &#63;.
	 *
	 * @param formInstanceRecordVersionId the form instance record version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching maintenance activity forms, or <code>null</code> if a matching maintenance activity forms could not be found
	 */
	@Override
	public MaintenanceActivityForms fetchByformInstanceRecordVersionId_First(
		long formInstanceRecordVersionId,
		OrderByComparator<MaintenanceActivityForms> orderByComparator) {

		List<MaintenanceActivityForms> list = findByformInstanceRecordVersionId(
			formInstanceRecordVersionId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last maintenance activity forms in the ordered set where formInstanceRecordVersionId = &#63;.
	 *
	 * @param formInstanceRecordVersionId the form instance record version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching maintenance activity forms
	 * @throws NoSuchMaintenanceActivityFormsException if a matching maintenance activity forms could not be found
	 */
	@Override
	public MaintenanceActivityForms findByformInstanceRecordVersionId_Last(
			long formInstanceRecordVersionId,
			OrderByComparator<MaintenanceActivityForms> orderByComparator)
		throws NoSuchMaintenanceActivityFormsException {

		MaintenanceActivityForms maintenanceActivityForms =
			fetchByformInstanceRecordVersionId_Last(
				formInstanceRecordVersionId, orderByComparator);

		if (maintenanceActivityForms != null) {
			return maintenanceActivityForms;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("formInstanceRecordVersionId=");
		sb.append(formInstanceRecordVersionId);

		sb.append("}");

		throw new NoSuchMaintenanceActivityFormsException(sb.toString());
	}

	/**
	 * Returns the last maintenance activity forms in the ordered set where formInstanceRecordVersionId = &#63;.
	 *
	 * @param formInstanceRecordVersionId the form instance record version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching maintenance activity forms, or <code>null</code> if a matching maintenance activity forms could not be found
	 */
	@Override
	public MaintenanceActivityForms fetchByformInstanceRecordVersionId_Last(
		long formInstanceRecordVersionId,
		OrderByComparator<MaintenanceActivityForms> orderByComparator) {

		int count = countByformInstanceRecordVersionId(
			formInstanceRecordVersionId);

		if (count == 0) {
			return null;
		}

		List<MaintenanceActivityForms> list = findByformInstanceRecordVersionId(
			formInstanceRecordVersionId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the maintenance activity formses before and after the current maintenance activity forms in the ordered set where formInstanceRecordVersionId = &#63;.
	 *
	 * @param maintenanceactivityformId the primary key of the current maintenance activity forms
	 * @param formInstanceRecordVersionId the form instance record version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next maintenance activity forms
	 * @throws NoSuchMaintenanceActivityFormsException if a maintenance activity forms with the primary key could not be found
	 */
	@Override
	public MaintenanceActivityForms[]
			findByformInstanceRecordVersionId_PrevAndNext(
				long maintenanceactivityformId,
				long formInstanceRecordVersionId,
				OrderByComparator<MaintenanceActivityForms> orderByComparator)
		throws NoSuchMaintenanceActivityFormsException {

		MaintenanceActivityForms maintenanceActivityForms = findByPrimaryKey(
			maintenanceactivityformId);

		Session session = null;

		try {
			session = openSession();

			MaintenanceActivityForms[] array =
				new MaintenanceActivityFormsImpl[3];

			array[0] = getByformInstanceRecordVersionId_PrevAndNext(
				session, maintenanceActivityForms, formInstanceRecordVersionId,
				orderByComparator, true);

			array[1] = maintenanceActivityForms;

			array[2] = getByformInstanceRecordVersionId_PrevAndNext(
				session, maintenanceActivityForms, formInstanceRecordVersionId,
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

	protected MaintenanceActivityForms
		getByformInstanceRecordVersionId_PrevAndNext(
			Session session, MaintenanceActivityForms maintenanceActivityForms,
			long formInstanceRecordVersionId,
			OrderByComparator<MaintenanceActivityForms> orderByComparator,
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

		sb.append(_SQL_SELECT_MAINTENANCEACTIVITYFORMS_WHERE);

		sb.append(
			_FINDER_COLUMN_FORMINSTANCERECORDVERSIONID_FORMINSTANCERECORDVERSIONID_2);

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
			sb.append(MaintenanceActivityFormsModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(formInstanceRecordVersionId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						maintenanceActivityForms)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<MaintenanceActivityForms> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the maintenance activity formses where formInstanceRecordVersionId = &#63; from the database.
	 *
	 * @param formInstanceRecordVersionId the form instance record version ID
	 */
	@Override
	public void removeByformInstanceRecordVersionId(
		long formInstanceRecordVersionId) {

		for (MaintenanceActivityForms maintenanceActivityForms :
				findByformInstanceRecordVersionId(
					formInstanceRecordVersionId, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(maintenanceActivityForms);
		}
	}

	/**
	 * Returns the number of maintenance activity formses where formInstanceRecordVersionId = &#63;.
	 *
	 * @param formInstanceRecordVersionId the form instance record version ID
	 * @return the number of matching maintenance activity formses
	 */
	@Override
	public int countByformInstanceRecordVersionId(
		long formInstanceRecordVersionId) {

		FinderPath finderPath = _finderPathCountByformInstanceRecordVersionId;

		Object[] finderArgs = new Object[] {formInstanceRecordVersionId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_MAINTENANCEACTIVITYFORMS_WHERE);

			sb.append(
				_FINDER_COLUMN_FORMINSTANCERECORDVERSIONID_FORMINSTANCERECORDVERSIONID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(formInstanceRecordVersionId);

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
		_FINDER_COLUMN_FORMINSTANCERECORDVERSIONID_FORMINSTANCERECORDVERSIONID_2 =
			"maintenanceActivityForms.formInstanceRecordVersionId = ?";

	private FinderPath _finderPathWithPaginationFindByGroupId;
	private FinderPath _finderPathWithoutPaginationFindByGroupId;
	private FinderPath _finderPathCountByGroupId;

	/**
	 * Returns all the maintenance activity formses where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching maintenance activity formses
	 */
	@Override
	public List<MaintenanceActivityForms> findByGroupId(long groupId) {
		return findByGroupId(
			groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the maintenance activity formses where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MaintenanceActivityFormsModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of maintenance activity formses
	 * @param end the upper bound of the range of maintenance activity formses (not inclusive)
	 * @return the range of matching maintenance activity formses
	 */
	@Override
	public List<MaintenanceActivityForms> findByGroupId(
		long groupId, int start, int end) {

		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the maintenance activity formses where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MaintenanceActivityFormsModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of maintenance activity formses
	 * @param end the upper bound of the range of maintenance activity formses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching maintenance activity formses
	 */
	@Override
	public List<MaintenanceActivityForms> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<MaintenanceActivityForms> orderByComparator) {

		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the maintenance activity formses where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MaintenanceActivityFormsModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of maintenance activity formses
	 * @param end the upper bound of the range of maintenance activity formses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching maintenance activity formses
	 */
	@Override
	public List<MaintenanceActivityForms> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<MaintenanceActivityForms> orderByComparator,
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

		List<MaintenanceActivityForms> list = null;

		if (useFinderCache) {
			list = (List<MaintenanceActivityForms>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (MaintenanceActivityForms maintenanceActivityForms : list) {
					if (groupId != maintenanceActivityForms.getGroupId()) {
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

			sb.append(_SQL_SELECT_MAINTENANCEACTIVITYFORMS_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(MaintenanceActivityFormsModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				list = (List<MaintenanceActivityForms>)QueryUtil.list(
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
	 * Returns the first maintenance activity forms in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching maintenance activity forms
	 * @throws NoSuchMaintenanceActivityFormsException if a matching maintenance activity forms could not be found
	 */
	@Override
	public MaintenanceActivityForms findByGroupId_First(
			long groupId,
			OrderByComparator<MaintenanceActivityForms> orderByComparator)
		throws NoSuchMaintenanceActivityFormsException {

		MaintenanceActivityForms maintenanceActivityForms =
			fetchByGroupId_First(groupId, orderByComparator);

		if (maintenanceActivityForms != null) {
			return maintenanceActivityForms;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchMaintenanceActivityFormsException(sb.toString());
	}

	/**
	 * Returns the first maintenance activity forms in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching maintenance activity forms, or <code>null</code> if a matching maintenance activity forms could not be found
	 */
	@Override
	public MaintenanceActivityForms fetchByGroupId_First(
		long groupId,
		OrderByComparator<MaintenanceActivityForms> orderByComparator) {

		List<MaintenanceActivityForms> list = findByGroupId(
			groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last maintenance activity forms in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching maintenance activity forms
	 * @throws NoSuchMaintenanceActivityFormsException if a matching maintenance activity forms could not be found
	 */
	@Override
	public MaintenanceActivityForms findByGroupId_Last(
			long groupId,
			OrderByComparator<MaintenanceActivityForms> orderByComparator)
		throws NoSuchMaintenanceActivityFormsException {

		MaintenanceActivityForms maintenanceActivityForms = fetchByGroupId_Last(
			groupId, orderByComparator);

		if (maintenanceActivityForms != null) {
			return maintenanceActivityForms;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchMaintenanceActivityFormsException(sb.toString());
	}

	/**
	 * Returns the last maintenance activity forms in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching maintenance activity forms, or <code>null</code> if a matching maintenance activity forms could not be found
	 */
	@Override
	public MaintenanceActivityForms fetchByGroupId_Last(
		long groupId,
		OrderByComparator<MaintenanceActivityForms> orderByComparator) {

		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<MaintenanceActivityForms> list = findByGroupId(
			groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the maintenance activity formses before and after the current maintenance activity forms in the ordered set where groupId = &#63;.
	 *
	 * @param maintenanceactivityformId the primary key of the current maintenance activity forms
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next maintenance activity forms
	 * @throws NoSuchMaintenanceActivityFormsException if a maintenance activity forms with the primary key could not be found
	 */
	@Override
	public MaintenanceActivityForms[] findByGroupId_PrevAndNext(
			long maintenanceactivityformId, long groupId,
			OrderByComparator<MaintenanceActivityForms> orderByComparator)
		throws NoSuchMaintenanceActivityFormsException {

		MaintenanceActivityForms maintenanceActivityForms = findByPrimaryKey(
			maintenanceactivityformId);

		Session session = null;

		try {
			session = openSession();

			MaintenanceActivityForms[] array =
				new MaintenanceActivityFormsImpl[3];

			array[0] = getByGroupId_PrevAndNext(
				session, maintenanceActivityForms, groupId, orderByComparator,
				true);

			array[1] = maintenanceActivityForms;

			array[2] = getByGroupId_PrevAndNext(
				session, maintenanceActivityForms, groupId, orderByComparator,
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

	protected MaintenanceActivityForms getByGroupId_PrevAndNext(
		Session session, MaintenanceActivityForms maintenanceActivityForms,
		long groupId,
		OrderByComparator<MaintenanceActivityForms> orderByComparator,
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

		sb.append(_SQL_SELECT_MAINTENANCEACTIVITYFORMS_WHERE);

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
			sb.append(MaintenanceActivityFormsModelImpl.ORDER_BY_JPQL);
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
						maintenanceActivityForms)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<MaintenanceActivityForms> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the maintenance activity formses where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (MaintenanceActivityForms maintenanceActivityForms :
				findByGroupId(
					groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(maintenanceActivityForms);
		}
	}

	/**
	 * Returns the number of maintenance activity formses where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching maintenance activity formses
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = _finderPathCountByGroupId;

		Object[] finderArgs = new Object[] {groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_MAINTENANCEACTIVITYFORMS_WHERE);

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
		"maintenanceActivityForms.groupId = ?";

	public MaintenanceActivityFormsPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

		setDBColumnNames(dbColumnNames);

		setModelClass(MaintenanceActivityForms.class);

		setModelImplClass(MaintenanceActivityFormsImpl.class);
		setModelPKClass(long.class);
	}

	/**
	 * Caches the maintenance activity forms in the entity cache if it is enabled.
	 *
	 * @param maintenanceActivityForms the maintenance activity forms
	 */
	@Override
	public void cacheResult(MaintenanceActivityForms maintenanceActivityForms) {
		entityCache.putResult(
			MaintenanceActivityFormsImpl.class,
			maintenanceActivityForms.getPrimaryKey(), maintenanceActivityForms);

		finderCache.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {
				maintenanceActivityForms.getUuid(),
				maintenanceActivityForms.getGroupId()
			},
			maintenanceActivityForms);
	}

	/**
	 * Caches the maintenance activity formses in the entity cache if it is enabled.
	 *
	 * @param maintenanceActivityFormses the maintenance activity formses
	 */
	@Override
	public void cacheResult(
		List<MaintenanceActivityForms> maintenanceActivityFormses) {

		for (MaintenanceActivityForms maintenanceActivityForms :
				maintenanceActivityFormses) {

			if (entityCache.getResult(
					MaintenanceActivityFormsImpl.class,
					maintenanceActivityForms.getPrimaryKey()) == null) {

				cacheResult(maintenanceActivityForms);
			}
		}
	}

	/**
	 * Clears the cache for all maintenance activity formses.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(MaintenanceActivityFormsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the maintenance activity forms.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MaintenanceActivityForms maintenanceActivityForms) {
		entityCache.removeResult(
			MaintenanceActivityFormsImpl.class, maintenanceActivityForms);
	}

	@Override
	public void clearCache(
		List<MaintenanceActivityForms> maintenanceActivityFormses) {

		for (MaintenanceActivityForms maintenanceActivityForms :
				maintenanceActivityFormses) {

			entityCache.removeResult(
				MaintenanceActivityFormsImpl.class, maintenanceActivityForms);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				MaintenanceActivityFormsImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		MaintenanceActivityFormsModelImpl maintenanceActivityFormsModelImpl) {

		Object[] args = new Object[] {
			maintenanceActivityFormsModelImpl.getUuid(),
			maintenanceActivityFormsModelImpl.getGroupId()
		};

		finderCache.putResult(
			_finderPathCountByUUID_G, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByUUID_G, args, maintenanceActivityFormsModelImpl,
			false);
	}

	/**
	 * Creates a new maintenance activity forms with the primary key. Does not add the maintenance activity forms to the database.
	 *
	 * @param maintenanceactivityformId the primary key for the new maintenance activity forms
	 * @return the new maintenance activity forms
	 */
	@Override
	public MaintenanceActivityForms create(long maintenanceactivityformId) {
		MaintenanceActivityForms maintenanceActivityForms =
			new MaintenanceActivityFormsImpl();

		maintenanceActivityForms.setNew(true);
		maintenanceActivityForms.setPrimaryKey(maintenanceactivityformId);

		String uuid = PortalUUIDUtil.generate();

		maintenanceActivityForms.setUuid(uuid);

		maintenanceActivityForms.setCompanyId(
			CompanyThreadLocal.getCompanyId());

		return maintenanceActivityForms;
	}

	/**
	 * Removes the maintenance activity forms with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param maintenanceactivityformId the primary key of the maintenance activity forms
	 * @return the maintenance activity forms that was removed
	 * @throws NoSuchMaintenanceActivityFormsException if a maintenance activity forms with the primary key could not be found
	 */
	@Override
	public MaintenanceActivityForms remove(long maintenanceactivityformId)
		throws NoSuchMaintenanceActivityFormsException {

		return remove((Serializable)maintenanceactivityformId);
	}

	/**
	 * Removes the maintenance activity forms with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the maintenance activity forms
	 * @return the maintenance activity forms that was removed
	 * @throws NoSuchMaintenanceActivityFormsException if a maintenance activity forms with the primary key could not be found
	 */
	@Override
	public MaintenanceActivityForms remove(Serializable primaryKey)
		throws NoSuchMaintenanceActivityFormsException {

		Session session = null;

		try {
			session = openSession();

			MaintenanceActivityForms maintenanceActivityForms =
				(MaintenanceActivityForms)session.get(
					MaintenanceActivityFormsImpl.class, primaryKey);

			if (maintenanceActivityForms == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMaintenanceActivityFormsException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(maintenanceActivityForms);
		}
		catch (NoSuchMaintenanceActivityFormsException noSuchEntityException) {
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
	protected MaintenanceActivityForms removeImpl(
		MaintenanceActivityForms maintenanceActivityForms) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(maintenanceActivityForms)) {
				maintenanceActivityForms =
					(MaintenanceActivityForms)session.get(
						MaintenanceActivityFormsImpl.class,
						maintenanceActivityForms.getPrimaryKeyObj());
			}

			if (maintenanceActivityForms != null) {
				session.delete(maintenanceActivityForms);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (maintenanceActivityForms != null) {
			clearCache(maintenanceActivityForms);
		}

		return maintenanceActivityForms;
	}

	@Override
	public MaintenanceActivityForms updateImpl(
		MaintenanceActivityForms maintenanceActivityForms) {

		boolean isNew = maintenanceActivityForms.isNew();

		if (!(maintenanceActivityForms instanceof
				MaintenanceActivityFormsModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(maintenanceActivityForms.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					maintenanceActivityForms);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in maintenanceActivityForms proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom MaintenanceActivityForms implementation " +
					maintenanceActivityForms.getClass());
		}

		MaintenanceActivityFormsModelImpl maintenanceActivityFormsModelImpl =
			(MaintenanceActivityFormsModelImpl)maintenanceActivityForms;

		if (Validator.isNull(maintenanceActivityForms.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			maintenanceActivityForms.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (maintenanceActivityForms.getCreateDate() == null)) {
			if (serviceContext == null) {
				maintenanceActivityForms.setCreateDate(now);
			}
			else {
				maintenanceActivityForms.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!maintenanceActivityFormsModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				maintenanceActivityForms.setModifiedDate(now);
			}
			else {
				maintenanceActivityForms.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(maintenanceActivityForms);
			}
			else {
				maintenanceActivityForms =
					(MaintenanceActivityForms)session.merge(
						maintenanceActivityForms);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			MaintenanceActivityFormsImpl.class,
			maintenanceActivityFormsModelImpl, false, true);

		cacheUniqueFindersCache(maintenanceActivityFormsModelImpl);

		if (isNew) {
			maintenanceActivityForms.setNew(false);
		}

		maintenanceActivityForms.resetOriginalValues();

		return maintenanceActivityForms;
	}

	/**
	 * Returns the maintenance activity forms with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the maintenance activity forms
	 * @return the maintenance activity forms
	 * @throws NoSuchMaintenanceActivityFormsException if a maintenance activity forms with the primary key could not be found
	 */
	@Override
	public MaintenanceActivityForms findByPrimaryKey(Serializable primaryKey)
		throws NoSuchMaintenanceActivityFormsException {

		MaintenanceActivityForms maintenanceActivityForms = fetchByPrimaryKey(
			primaryKey);

		if (maintenanceActivityForms == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchMaintenanceActivityFormsException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return maintenanceActivityForms;
	}

	/**
	 * Returns the maintenance activity forms with the primary key or throws a <code>NoSuchMaintenanceActivityFormsException</code> if it could not be found.
	 *
	 * @param maintenanceactivityformId the primary key of the maintenance activity forms
	 * @return the maintenance activity forms
	 * @throws NoSuchMaintenanceActivityFormsException if a maintenance activity forms with the primary key could not be found
	 */
	@Override
	public MaintenanceActivityForms findByPrimaryKey(
			long maintenanceactivityformId)
		throws NoSuchMaintenanceActivityFormsException {

		return findByPrimaryKey((Serializable)maintenanceactivityformId);
	}

	/**
	 * Returns the maintenance activity forms with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param maintenanceactivityformId the primary key of the maintenance activity forms
	 * @return the maintenance activity forms, or <code>null</code> if a maintenance activity forms with the primary key could not be found
	 */
	@Override
	public MaintenanceActivityForms fetchByPrimaryKey(
		long maintenanceactivityformId) {

		return fetchByPrimaryKey((Serializable)maintenanceactivityformId);
	}

	/**
	 * Returns all the maintenance activity formses.
	 *
	 * @return the maintenance activity formses
	 */
	@Override
	public List<MaintenanceActivityForms> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the maintenance activity formses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MaintenanceActivityFormsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of maintenance activity formses
	 * @param end the upper bound of the range of maintenance activity formses (not inclusive)
	 * @return the range of maintenance activity formses
	 */
	@Override
	public List<MaintenanceActivityForms> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the maintenance activity formses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MaintenanceActivityFormsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of maintenance activity formses
	 * @param end the upper bound of the range of maintenance activity formses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of maintenance activity formses
	 */
	@Override
	public List<MaintenanceActivityForms> findAll(
		int start, int end,
		OrderByComparator<MaintenanceActivityForms> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the maintenance activity formses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MaintenanceActivityFormsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of maintenance activity formses
	 * @param end the upper bound of the range of maintenance activity formses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of maintenance activity formses
	 */
	@Override
	public List<MaintenanceActivityForms> findAll(
		int start, int end,
		OrderByComparator<MaintenanceActivityForms> orderByComparator,
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

		List<MaintenanceActivityForms> list = null;

		if (useFinderCache) {
			list = (List<MaintenanceActivityForms>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_MAINTENANCEACTIVITYFORMS);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_MAINTENANCEACTIVITYFORMS;

				sql = sql.concat(
					MaintenanceActivityFormsModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<MaintenanceActivityForms>)QueryUtil.list(
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
	 * Removes all the maintenance activity formses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (MaintenanceActivityForms maintenanceActivityForms : findAll()) {
			remove(maintenanceActivityForms);
		}
	}

	/**
	 * Returns the number of maintenance activity formses.
	 *
	 * @return the number of maintenance activity formses
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
					_SQL_COUNT_MAINTENANCEACTIVITYFORMS);

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
		return "maintenanceactivityformId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_MAINTENANCEACTIVITYFORMS;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return MaintenanceActivityFormsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the maintenance activity forms persistence.
	 */
	@Activate
	public void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		_argumentsResolverServiceRegistration = _bundleContext.registerService(
			ArgumentsResolver.class,
			new MaintenanceActivityFormsModelArgumentsResolver(),
			MapUtil.singletonDictionary(
				"model.class.name", MaintenanceActivityForms.class.getName()));

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

		_finderPathWithPaginationFindByformInstanceRecordVersionId =
			_createFinderPath(
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
				"findByformInstanceRecordVersionId",
				new String[] {
					Long.class.getName(), Integer.class.getName(),
					Integer.class.getName(), OrderByComparator.class.getName()
				},
				new String[] {"formInstanceRecordVersionId"}, true);

		_finderPathWithoutPaginationFindByformInstanceRecordVersionId =
			_createFinderPath(
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"findByformInstanceRecordVersionId",
				new String[] {Long.class.getName()},
				new String[] {"formInstanceRecordVersionId"}, true);

		_finderPathCountByformInstanceRecordVersionId = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByformInstanceRecordVersionId",
			new String[] {Long.class.getName()},
			new String[] {"formInstanceRecordVersionId"}, false);

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
	}

	@Deactivate
	public void deactivate() {
		entityCache.removeCache(MaintenanceActivityFormsImpl.class.getName());

		_argumentsResolverServiceRegistration.unregister();

		for (ServiceRegistration<FinderPath> serviceRegistration :
				_serviceRegistrations) {

			serviceRegistration.unregister();
		}
	}

	@Override
	@Reference(
		target = AMAFPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = AMAFPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = AMAFPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
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

	private static final String _SQL_SELECT_MAINTENANCEACTIVITYFORMS =
		"SELECT maintenanceActivityForms FROM MaintenanceActivityForms maintenanceActivityForms";

	private static final String _SQL_SELECT_MAINTENANCEACTIVITYFORMS_WHERE =
		"SELECT maintenanceActivityForms FROM MaintenanceActivityForms maintenanceActivityForms WHERE ";

	private static final String _SQL_COUNT_MAINTENANCEACTIVITYFORMS =
		"SELECT COUNT(maintenanceActivityForms) FROM MaintenanceActivityForms maintenanceActivityForms";

	private static final String _SQL_COUNT_MAINTENANCEACTIVITYFORMS_WHERE =
		"SELECT COUNT(maintenanceActivityForms) FROM MaintenanceActivityForms maintenanceActivityForms WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"maintenanceActivityForms.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No MaintenanceActivityForms exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No MaintenanceActivityForms exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		MaintenanceActivityFormsPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

	static {
		try {
			Class.forName(AMAFPersistenceConstants.class.getName());
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

	private static class MaintenanceActivityFormsModelArgumentsResolver
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

			MaintenanceActivityFormsModelImpl
				maintenanceActivityFormsModelImpl =
					(MaintenanceActivityFormsModelImpl)baseModel;

			long columnBitmask =
				maintenanceActivityFormsModelImpl.getColumnBitmask();

			if (!checkColumn || (columnBitmask == 0)) {
				return _getValue(
					maintenanceActivityFormsModelImpl, columnNames, original);
			}

			Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
				finderPath);

			if (finderPathColumnBitmask == null) {
				finderPathColumnBitmask = 0L;

				for (String columnName : columnNames) {
					finderPathColumnBitmask |=
						maintenanceActivityFormsModelImpl.getColumnBitmask(
							columnName);
				}

				_finderPathColumnBitmasksCache.put(
					finderPath, finderPathColumnBitmask);
			}

			if ((columnBitmask & finderPathColumnBitmask) != 0) {
				return _getValue(
					maintenanceActivityFormsModelImpl, columnNames, original);
			}

			return null;
		}

		private Object[] _getValue(
			MaintenanceActivityFormsModelImpl maintenanceActivityFormsModelImpl,
			String[] columnNames, boolean original) {

			Object[] arguments = new Object[columnNames.length];

			for (int i = 0; i < arguments.length; i++) {
				String columnName = columnNames[i];

				if (original) {
					arguments[i] =
						maintenanceActivityFormsModelImpl.
							getColumnOriginalValue(columnName);
				}
				else {
					arguments[i] =
						maintenanceActivityFormsModelImpl.getColumnValue(
							columnName);
				}
			}

			return arguments;
		}

		private static Map<FinderPath, Long> _finderPathColumnBitmasksCache =
			new ConcurrentHashMap<>();

	}

}