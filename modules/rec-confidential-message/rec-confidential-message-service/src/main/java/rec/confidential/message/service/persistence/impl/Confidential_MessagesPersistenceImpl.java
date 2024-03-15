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

package rec.confidential.message.service.persistence.impl;

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

import rec.confidential.message.exception.NoSuchConfidential_MessagesException;
import rec.confidential.message.model.Confidential_Messages;
import rec.confidential.message.model.impl.Confidential_MessagesImpl;
import rec.confidential.message.model.impl.Confidential_MessagesModelImpl;
import rec.confidential.message.service.persistence.Confidential_MessagesPersistence;
import rec.confidential.message.service.persistence.impl.constants.CONFIDENTIAL_MESSAGESPersistenceConstants;

/**
 * The persistence implementation for the confidential_ messages service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = Confidential_MessagesPersistence.class)
public class Confidential_MessagesPersistenceImpl
	extends BasePersistenceImpl<Confidential_Messages>
	implements Confidential_MessagesPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>Confidential_MessagesUtil</code> to access the confidential_ messages persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		Confidential_MessagesImpl.class.getName();

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
	 * Returns all the confidential_ messageses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching confidential_ messageses
	 */
	@Override
	public List<Confidential_Messages> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the confidential_ messageses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>Confidential_MessagesModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of confidential_ messageses
	 * @param end the upper bound of the range of confidential_ messageses (not inclusive)
	 * @return the range of matching confidential_ messageses
	 */
	@Override
	public List<Confidential_Messages> findByUuid(
		String uuid, int start, int end) {

		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the confidential_ messageses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>Confidential_MessagesModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of confidential_ messageses
	 * @param end the upper bound of the range of confidential_ messageses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching confidential_ messageses
	 */
	@Override
	public List<Confidential_Messages> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Confidential_Messages> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the confidential_ messageses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>Confidential_MessagesModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of confidential_ messageses
	 * @param end the upper bound of the range of confidential_ messageses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching confidential_ messageses
	 */
	@Override
	public List<Confidential_Messages> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Confidential_Messages> orderByComparator,
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

		List<Confidential_Messages> list = null;

		if (useFinderCache) {
			list = (List<Confidential_Messages>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Confidential_Messages confidential_Messages : list) {
					if (!uuid.equals(confidential_Messages.getUuid())) {
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

			sb.append(_SQL_SELECT_CONFIDENTIAL_MESSAGES_WHERE);

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
				sb.append(Confidential_MessagesModelImpl.ORDER_BY_JPQL);
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

				list = (List<Confidential_Messages>)QueryUtil.list(
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
	 * Returns the first confidential_ messages in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching confidential_ messages
	 * @throws NoSuchConfidential_MessagesException if a matching confidential_ messages could not be found
	 */
	@Override
	public Confidential_Messages findByUuid_First(
			String uuid,
			OrderByComparator<Confidential_Messages> orderByComparator)
		throws NoSuchConfidential_MessagesException {

		Confidential_Messages confidential_Messages = fetchByUuid_First(
			uuid, orderByComparator);

		if (confidential_Messages != null) {
			return confidential_Messages;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchConfidential_MessagesException(sb.toString());
	}

	/**
	 * Returns the first confidential_ messages in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching confidential_ messages, or <code>null</code> if a matching confidential_ messages could not be found
	 */
	@Override
	public Confidential_Messages fetchByUuid_First(
		String uuid,
		OrderByComparator<Confidential_Messages> orderByComparator) {

		List<Confidential_Messages> list = findByUuid(
			uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last confidential_ messages in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching confidential_ messages
	 * @throws NoSuchConfidential_MessagesException if a matching confidential_ messages could not be found
	 */
	@Override
	public Confidential_Messages findByUuid_Last(
			String uuid,
			OrderByComparator<Confidential_Messages> orderByComparator)
		throws NoSuchConfidential_MessagesException {

		Confidential_Messages confidential_Messages = fetchByUuid_Last(
			uuid, orderByComparator);

		if (confidential_Messages != null) {
			return confidential_Messages;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchConfidential_MessagesException(sb.toString());
	}

	/**
	 * Returns the last confidential_ messages in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching confidential_ messages, or <code>null</code> if a matching confidential_ messages could not be found
	 */
	@Override
	public Confidential_Messages fetchByUuid_Last(
		String uuid,
		OrderByComparator<Confidential_Messages> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Confidential_Messages> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the confidential_ messageses before and after the current confidential_ messages in the ordered set where uuid = &#63;.
	 *
	 * @param messageId the primary key of the current confidential_ messages
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next confidential_ messages
	 * @throws NoSuchConfidential_MessagesException if a confidential_ messages with the primary key could not be found
	 */
	@Override
	public Confidential_Messages[] findByUuid_PrevAndNext(
			long messageId, String uuid,
			OrderByComparator<Confidential_Messages> orderByComparator)
		throws NoSuchConfidential_MessagesException {

		uuid = Objects.toString(uuid, "");

		Confidential_Messages confidential_Messages = findByPrimaryKey(
			messageId);

		Session session = null;

		try {
			session = openSession();

			Confidential_Messages[] array = new Confidential_MessagesImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, confidential_Messages, uuid, orderByComparator, true);

			array[1] = confidential_Messages;

			array[2] = getByUuid_PrevAndNext(
				session, confidential_Messages, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Confidential_Messages getByUuid_PrevAndNext(
		Session session, Confidential_Messages confidential_Messages,
		String uuid, OrderByComparator<Confidential_Messages> orderByComparator,
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

		sb.append(_SQL_SELECT_CONFIDENTIAL_MESSAGES_WHERE);

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
			sb.append(Confidential_MessagesModelImpl.ORDER_BY_JPQL);
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
						confidential_Messages)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Confidential_Messages> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the confidential_ messageses where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (Confidential_Messages confidential_Messages :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(confidential_Messages);
		}
	}

	/**
	 * Returns the number of confidential_ messageses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching confidential_ messageses
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_CONFIDENTIAL_MESSAGES_WHERE);

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
		"confidential_Messages.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(confidential_Messages.uuid IS NULL OR confidential_Messages.uuid = '')";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the confidential_ messageses where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching confidential_ messageses
	 */
	@Override
	public List<Confidential_Messages> findByUuid_C(
		String uuid, long companyId) {

		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the confidential_ messageses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>Confidential_MessagesModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of confidential_ messageses
	 * @param end the upper bound of the range of confidential_ messageses (not inclusive)
	 * @return the range of matching confidential_ messageses
	 */
	@Override
	public List<Confidential_Messages> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the confidential_ messageses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>Confidential_MessagesModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of confidential_ messageses
	 * @param end the upper bound of the range of confidential_ messageses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching confidential_ messageses
	 */
	@Override
	public List<Confidential_Messages> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Confidential_Messages> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the confidential_ messageses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>Confidential_MessagesModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of confidential_ messageses
	 * @param end the upper bound of the range of confidential_ messageses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching confidential_ messageses
	 */
	@Override
	public List<Confidential_Messages> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Confidential_Messages> orderByComparator,
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

		List<Confidential_Messages> list = null;

		if (useFinderCache) {
			list = (List<Confidential_Messages>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Confidential_Messages confidential_Messages : list) {
					if (!uuid.equals(confidential_Messages.getUuid()) ||
						(companyId != confidential_Messages.getCompanyId())) {

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

			sb.append(_SQL_SELECT_CONFIDENTIAL_MESSAGES_WHERE);

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
				sb.append(Confidential_MessagesModelImpl.ORDER_BY_JPQL);
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

				list = (List<Confidential_Messages>)QueryUtil.list(
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
	 * Returns the first confidential_ messages in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching confidential_ messages
	 * @throws NoSuchConfidential_MessagesException if a matching confidential_ messages could not be found
	 */
	@Override
	public Confidential_Messages findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<Confidential_Messages> orderByComparator)
		throws NoSuchConfidential_MessagesException {

		Confidential_Messages confidential_Messages = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (confidential_Messages != null) {
			return confidential_Messages;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchConfidential_MessagesException(sb.toString());
	}

	/**
	 * Returns the first confidential_ messages in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching confidential_ messages, or <code>null</code> if a matching confidential_ messages could not be found
	 */
	@Override
	public Confidential_Messages fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<Confidential_Messages> orderByComparator) {

		List<Confidential_Messages> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last confidential_ messages in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching confidential_ messages
	 * @throws NoSuchConfidential_MessagesException if a matching confidential_ messages could not be found
	 */
	@Override
	public Confidential_Messages findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<Confidential_Messages> orderByComparator)
		throws NoSuchConfidential_MessagesException {

		Confidential_Messages confidential_Messages = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (confidential_Messages != null) {
			return confidential_Messages;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchConfidential_MessagesException(sb.toString());
	}

	/**
	 * Returns the last confidential_ messages in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching confidential_ messages, or <code>null</code> if a matching confidential_ messages could not be found
	 */
	@Override
	public Confidential_Messages fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<Confidential_Messages> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<Confidential_Messages> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the confidential_ messageses before and after the current confidential_ messages in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param messageId the primary key of the current confidential_ messages
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next confidential_ messages
	 * @throws NoSuchConfidential_MessagesException if a confidential_ messages with the primary key could not be found
	 */
	@Override
	public Confidential_Messages[] findByUuid_C_PrevAndNext(
			long messageId, String uuid, long companyId,
			OrderByComparator<Confidential_Messages> orderByComparator)
		throws NoSuchConfidential_MessagesException {

		uuid = Objects.toString(uuid, "");

		Confidential_Messages confidential_Messages = findByPrimaryKey(
			messageId);

		Session session = null;

		try {
			session = openSession();

			Confidential_Messages[] array = new Confidential_MessagesImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, confidential_Messages, uuid, companyId,
				orderByComparator, true);

			array[1] = confidential_Messages;

			array[2] = getByUuid_C_PrevAndNext(
				session, confidential_Messages, uuid, companyId,
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

	protected Confidential_Messages getByUuid_C_PrevAndNext(
		Session session, Confidential_Messages confidential_Messages,
		String uuid, long companyId,
		OrderByComparator<Confidential_Messages> orderByComparator,
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

		sb.append(_SQL_SELECT_CONFIDENTIAL_MESSAGES_WHERE);

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
			sb.append(Confidential_MessagesModelImpl.ORDER_BY_JPQL);
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
						confidential_Messages)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Confidential_Messages> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the confidential_ messageses where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (Confidential_Messages confidential_Messages :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(confidential_Messages);
		}
	}

	/**
	 * Returns the number of confidential_ messageses where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching confidential_ messageses
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_CONFIDENTIAL_MESSAGES_WHERE);

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
		"confidential_Messages.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(confidential_Messages.uuid IS NULL OR confidential_Messages.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"confidential_Messages.companyId = ?";

	public Confidential_MessagesPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

		setDBColumnNames(dbColumnNames);

		setModelClass(Confidential_Messages.class);

		setModelImplClass(Confidential_MessagesImpl.class);
		setModelPKClass(long.class);
	}

	/**
	 * Caches the confidential_ messages in the entity cache if it is enabled.
	 *
	 * @param confidential_Messages the confidential_ messages
	 */
	@Override
	public void cacheResult(Confidential_Messages confidential_Messages) {
		entityCache.putResult(
			Confidential_MessagesImpl.class,
			confidential_Messages.getPrimaryKey(), confidential_Messages);
	}

	/**
	 * Caches the confidential_ messageses in the entity cache if it is enabled.
	 *
	 * @param confidential_Messageses the confidential_ messageses
	 */
	@Override
	public void cacheResult(
		List<Confidential_Messages> confidential_Messageses) {

		for (Confidential_Messages confidential_Messages :
				confidential_Messageses) {

			if (entityCache.getResult(
					Confidential_MessagesImpl.class,
					confidential_Messages.getPrimaryKey()) == null) {

				cacheResult(confidential_Messages);
			}
		}
	}

	/**
	 * Clears the cache for all confidential_ messageses.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(Confidential_MessagesImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the confidential_ messages.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Confidential_Messages confidential_Messages) {
		entityCache.removeResult(
			Confidential_MessagesImpl.class, confidential_Messages);
	}

	@Override
	public void clearCache(
		List<Confidential_Messages> confidential_Messageses) {

		for (Confidential_Messages confidential_Messages :
				confidential_Messageses) {

			entityCache.removeResult(
				Confidential_MessagesImpl.class, confidential_Messages);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				Confidential_MessagesImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new confidential_ messages with the primary key. Does not add the confidential_ messages to the database.
	 *
	 * @param messageId the primary key for the new confidential_ messages
	 * @return the new confidential_ messages
	 */
	@Override
	public Confidential_Messages create(long messageId) {
		Confidential_Messages confidential_Messages =
			new Confidential_MessagesImpl();

		confidential_Messages.setNew(true);
		confidential_Messages.setPrimaryKey(messageId);

		String uuid = PortalUUIDUtil.generate();

		confidential_Messages.setUuid(uuid);

		confidential_Messages.setCompanyId(CompanyThreadLocal.getCompanyId());

		return confidential_Messages;
	}

	/**
	 * Removes the confidential_ messages with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param messageId the primary key of the confidential_ messages
	 * @return the confidential_ messages that was removed
	 * @throws NoSuchConfidential_MessagesException if a confidential_ messages with the primary key could not be found
	 */
	@Override
	public Confidential_Messages remove(long messageId)
		throws NoSuchConfidential_MessagesException {

		return remove((Serializable)messageId);
	}

	/**
	 * Removes the confidential_ messages with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the confidential_ messages
	 * @return the confidential_ messages that was removed
	 * @throws NoSuchConfidential_MessagesException if a confidential_ messages with the primary key could not be found
	 */
	@Override
	public Confidential_Messages remove(Serializable primaryKey)
		throws NoSuchConfidential_MessagesException {

		Session session = null;

		try {
			session = openSession();

			Confidential_Messages confidential_Messages =
				(Confidential_Messages)session.get(
					Confidential_MessagesImpl.class, primaryKey);

			if (confidential_Messages == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchConfidential_MessagesException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(confidential_Messages);
		}
		catch (NoSuchConfidential_MessagesException noSuchEntityException) {
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
	protected Confidential_Messages removeImpl(
		Confidential_Messages confidential_Messages) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(confidential_Messages)) {
				confidential_Messages = (Confidential_Messages)session.get(
					Confidential_MessagesImpl.class,
					confidential_Messages.getPrimaryKeyObj());
			}

			if (confidential_Messages != null) {
				session.delete(confidential_Messages);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (confidential_Messages != null) {
			clearCache(confidential_Messages);
		}

		return confidential_Messages;
	}

	@Override
	public Confidential_Messages updateImpl(
		Confidential_Messages confidential_Messages) {

		boolean isNew = confidential_Messages.isNew();

		if (!(confidential_Messages instanceof
				Confidential_MessagesModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(confidential_Messages.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					confidential_Messages);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in confidential_Messages proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Confidential_Messages implementation " +
					confidential_Messages.getClass());
		}

		Confidential_MessagesModelImpl confidential_MessagesModelImpl =
			(Confidential_MessagesModelImpl)confidential_Messages;

		if (Validator.isNull(confidential_Messages.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			confidential_Messages.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(confidential_Messages);
			}
			else {
				confidential_Messages = (Confidential_Messages)session.merge(
					confidential_Messages);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			Confidential_MessagesImpl.class, confidential_MessagesModelImpl,
			false, true);

		if (isNew) {
			confidential_Messages.setNew(false);
		}

		confidential_Messages.resetOriginalValues();

		return confidential_Messages;
	}

	/**
	 * Returns the confidential_ messages with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the confidential_ messages
	 * @return the confidential_ messages
	 * @throws NoSuchConfidential_MessagesException if a confidential_ messages with the primary key could not be found
	 */
	@Override
	public Confidential_Messages findByPrimaryKey(Serializable primaryKey)
		throws NoSuchConfidential_MessagesException {

		Confidential_Messages confidential_Messages = fetchByPrimaryKey(
			primaryKey);

		if (confidential_Messages == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchConfidential_MessagesException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return confidential_Messages;
	}

	/**
	 * Returns the confidential_ messages with the primary key or throws a <code>NoSuchConfidential_MessagesException</code> if it could not be found.
	 *
	 * @param messageId the primary key of the confidential_ messages
	 * @return the confidential_ messages
	 * @throws NoSuchConfidential_MessagesException if a confidential_ messages with the primary key could not be found
	 */
	@Override
	public Confidential_Messages findByPrimaryKey(long messageId)
		throws NoSuchConfidential_MessagesException {

		return findByPrimaryKey((Serializable)messageId);
	}

	/**
	 * Returns the confidential_ messages with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param messageId the primary key of the confidential_ messages
	 * @return the confidential_ messages, or <code>null</code> if a confidential_ messages with the primary key could not be found
	 */
	@Override
	public Confidential_Messages fetchByPrimaryKey(long messageId) {
		return fetchByPrimaryKey((Serializable)messageId);
	}

	/**
	 * Returns all the confidential_ messageses.
	 *
	 * @return the confidential_ messageses
	 */
	@Override
	public List<Confidential_Messages> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the confidential_ messageses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>Confidential_MessagesModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of confidential_ messageses
	 * @param end the upper bound of the range of confidential_ messageses (not inclusive)
	 * @return the range of confidential_ messageses
	 */
	@Override
	public List<Confidential_Messages> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the confidential_ messageses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>Confidential_MessagesModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of confidential_ messageses
	 * @param end the upper bound of the range of confidential_ messageses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of confidential_ messageses
	 */
	@Override
	public List<Confidential_Messages> findAll(
		int start, int end,
		OrderByComparator<Confidential_Messages> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the confidential_ messageses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>Confidential_MessagesModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of confidential_ messageses
	 * @param end the upper bound of the range of confidential_ messageses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of confidential_ messageses
	 */
	@Override
	public List<Confidential_Messages> findAll(
		int start, int end,
		OrderByComparator<Confidential_Messages> orderByComparator,
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

		List<Confidential_Messages> list = null;

		if (useFinderCache) {
			list = (List<Confidential_Messages>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_CONFIDENTIAL_MESSAGES);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_CONFIDENTIAL_MESSAGES;

				sql = sql.concat(Confidential_MessagesModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<Confidential_Messages>)QueryUtil.list(
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
	 * Removes all the confidential_ messageses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Confidential_Messages confidential_Messages : findAll()) {
			remove(confidential_Messages);
		}
	}

	/**
	 * Returns the number of confidential_ messageses.
	 *
	 * @return the number of confidential_ messageses
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
					_SQL_COUNT_CONFIDENTIAL_MESSAGES);

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
		return "messageId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_CONFIDENTIAL_MESSAGES;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return Confidential_MessagesModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the confidential_ messages persistence.
	 */
	@Activate
	public void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		_argumentsResolverServiceRegistration = _bundleContext.registerService(
			ArgumentsResolver.class,
			new Confidential_MessagesModelArgumentsResolver(),
			MapUtil.singletonDictionary(
				"model.class.name", Confidential_Messages.class.getName()));

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
	}

	@Deactivate
	public void deactivate() {
		entityCache.removeCache(Confidential_MessagesImpl.class.getName());

		_argumentsResolverServiceRegistration.unregister();

		for (ServiceRegistration<FinderPath> serviceRegistration :
				_serviceRegistrations) {

			serviceRegistration.unregister();
		}
	}

	@Override
	@Reference(
		target = CONFIDENTIAL_MESSAGESPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = CONFIDENTIAL_MESSAGESPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = CONFIDENTIAL_MESSAGESPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
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

	private static final String _SQL_SELECT_CONFIDENTIAL_MESSAGES =
		"SELECT confidential_Messages FROM Confidential_Messages confidential_Messages";

	private static final String _SQL_SELECT_CONFIDENTIAL_MESSAGES_WHERE =
		"SELECT confidential_Messages FROM Confidential_Messages confidential_Messages WHERE ";

	private static final String _SQL_COUNT_CONFIDENTIAL_MESSAGES =
		"SELECT COUNT(confidential_Messages) FROM Confidential_Messages confidential_Messages";

	private static final String _SQL_COUNT_CONFIDENTIAL_MESSAGES_WHERE =
		"SELECT COUNT(confidential_Messages) FROM Confidential_Messages confidential_Messages WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"confidential_Messages.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Confidential_Messages exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No Confidential_Messages exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		Confidential_MessagesPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

	static {
		try {
			Class.forName(
				CONFIDENTIAL_MESSAGESPersistenceConstants.class.getName());
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

	private static class Confidential_MessagesModelArgumentsResolver
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

			Confidential_MessagesModelImpl confidential_MessagesModelImpl =
				(Confidential_MessagesModelImpl)baseModel;

			long columnBitmask =
				confidential_MessagesModelImpl.getColumnBitmask();

			if (!checkColumn || (columnBitmask == 0)) {
				return _getValue(
					confidential_MessagesModelImpl, columnNames, original);
			}

			Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
				finderPath);

			if (finderPathColumnBitmask == null) {
				finderPathColumnBitmask = 0L;

				for (String columnName : columnNames) {
					finderPathColumnBitmask |=
						confidential_MessagesModelImpl.getColumnBitmask(
							columnName);
				}

				_finderPathColumnBitmasksCache.put(
					finderPath, finderPathColumnBitmask);
			}

			if ((columnBitmask & finderPathColumnBitmask) != 0) {
				return _getValue(
					confidential_MessagesModelImpl, columnNames, original);
			}

			return null;
		}

		private Object[] _getValue(
			Confidential_MessagesModelImpl confidential_MessagesModelImpl,
			String[] columnNames, boolean original) {

			Object[] arguments = new Object[columnNames.length];

			for (int i = 0; i < arguments.length; i++) {
				String columnName = columnNames[i];

				if (original) {
					arguments[i] =
						confidential_MessagesModelImpl.getColumnOriginalValue(
							columnName);
				}
				else {
					arguments[i] =
						confidential_MessagesModelImpl.getColumnValue(
							columnName);
				}
			}

			return arguments;
		}

		private static Map<FinderPath, Long> _finderPathColumnBitmasksCache =
			new ConcurrentHashMap<>();

	}

}