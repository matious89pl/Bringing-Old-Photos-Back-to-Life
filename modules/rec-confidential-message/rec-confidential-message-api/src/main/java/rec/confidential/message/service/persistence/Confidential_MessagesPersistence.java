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

package rec.confidential.message.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

import rec.confidential.message.exception.NoSuchConfidential_MessagesException;
import rec.confidential.message.model.Confidential_Messages;

/**
 * The persistence interface for the confidential_ messages service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Confidential_MessagesUtil
 * @generated
 */
@ProviderType
public interface Confidential_MessagesPersistence
	extends BasePersistence<Confidential_Messages> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link Confidential_MessagesUtil} to access the confidential_ messages persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the confidential_ messageses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching confidential_ messageses
	 */
	public java.util.List<Confidential_Messages> findByUuid(String uuid);

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
	public java.util.List<Confidential_Messages> findByUuid(
		String uuid, int start, int end);

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
	public java.util.List<Confidential_Messages> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Confidential_Messages>
			orderByComparator);

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
	public java.util.List<Confidential_Messages> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Confidential_Messages>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first confidential_ messages in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching confidential_ messages
	 * @throws NoSuchConfidential_MessagesException if a matching confidential_ messages could not be found
	 */
	public Confidential_Messages findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator
				<Confidential_Messages> orderByComparator)
		throws NoSuchConfidential_MessagesException;

	/**
	 * Returns the first confidential_ messages in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching confidential_ messages, or <code>null</code> if a matching confidential_ messages could not be found
	 */
	public Confidential_Messages fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Confidential_Messages>
			orderByComparator);

	/**
	 * Returns the last confidential_ messages in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching confidential_ messages
	 * @throws NoSuchConfidential_MessagesException if a matching confidential_ messages could not be found
	 */
	public Confidential_Messages findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator
				<Confidential_Messages> orderByComparator)
		throws NoSuchConfidential_MessagesException;

	/**
	 * Returns the last confidential_ messages in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching confidential_ messages, or <code>null</code> if a matching confidential_ messages could not be found
	 */
	public Confidential_Messages fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Confidential_Messages>
			orderByComparator);

	/**
	 * Returns the confidential_ messageses before and after the current confidential_ messages in the ordered set where uuid = &#63;.
	 *
	 * @param messageId the primary key of the current confidential_ messages
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next confidential_ messages
	 * @throws NoSuchConfidential_MessagesException if a confidential_ messages with the primary key could not be found
	 */
	public Confidential_Messages[] findByUuid_PrevAndNext(
			long messageId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator
				<Confidential_Messages> orderByComparator)
		throws NoSuchConfidential_MessagesException;

	/**
	 * Removes all the confidential_ messageses where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of confidential_ messageses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching confidential_ messageses
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns all the confidential_ messageses where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching confidential_ messageses
	 */
	public java.util.List<Confidential_Messages> findByUuid_C(
		String uuid, long companyId);

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
	public java.util.List<Confidential_Messages> findByUuid_C(
		String uuid, long companyId, int start, int end);

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
	public java.util.List<Confidential_Messages> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Confidential_Messages>
			orderByComparator);

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
	public java.util.List<Confidential_Messages> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Confidential_Messages>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first confidential_ messages in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching confidential_ messages
	 * @throws NoSuchConfidential_MessagesException if a matching confidential_ messages could not be found
	 */
	public Confidential_Messages findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator
				<Confidential_Messages> orderByComparator)
		throws NoSuchConfidential_MessagesException;

	/**
	 * Returns the first confidential_ messages in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching confidential_ messages, or <code>null</code> if a matching confidential_ messages could not be found
	 */
	public Confidential_Messages fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Confidential_Messages>
			orderByComparator);

	/**
	 * Returns the last confidential_ messages in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching confidential_ messages
	 * @throws NoSuchConfidential_MessagesException if a matching confidential_ messages could not be found
	 */
	public Confidential_Messages findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator
				<Confidential_Messages> orderByComparator)
		throws NoSuchConfidential_MessagesException;

	/**
	 * Returns the last confidential_ messages in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching confidential_ messages, or <code>null</code> if a matching confidential_ messages could not be found
	 */
	public Confidential_Messages fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Confidential_Messages>
			orderByComparator);

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
	public Confidential_Messages[] findByUuid_C_PrevAndNext(
			long messageId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator
				<Confidential_Messages> orderByComparator)
		throws NoSuchConfidential_MessagesException;

	/**
	 * Removes all the confidential_ messageses where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of confidential_ messageses where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching confidential_ messageses
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Caches the confidential_ messages in the entity cache if it is enabled.
	 *
	 * @param confidential_Messages the confidential_ messages
	 */
	public void cacheResult(Confidential_Messages confidential_Messages);

	/**
	 * Caches the confidential_ messageses in the entity cache if it is enabled.
	 *
	 * @param confidential_Messageses the confidential_ messageses
	 */
	public void cacheResult(
		java.util.List<Confidential_Messages> confidential_Messageses);

	/**
	 * Creates a new confidential_ messages with the primary key. Does not add the confidential_ messages to the database.
	 *
	 * @param messageId the primary key for the new confidential_ messages
	 * @return the new confidential_ messages
	 */
	public Confidential_Messages create(long messageId);

	/**
	 * Removes the confidential_ messages with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param messageId the primary key of the confidential_ messages
	 * @return the confidential_ messages that was removed
	 * @throws NoSuchConfidential_MessagesException if a confidential_ messages with the primary key could not be found
	 */
	public Confidential_Messages remove(long messageId)
		throws NoSuchConfidential_MessagesException;

	public Confidential_Messages updateImpl(
		Confidential_Messages confidential_Messages);

	/**
	 * Returns the confidential_ messages with the primary key or throws a <code>NoSuchConfidential_MessagesException</code> if it could not be found.
	 *
	 * @param messageId the primary key of the confidential_ messages
	 * @return the confidential_ messages
	 * @throws NoSuchConfidential_MessagesException if a confidential_ messages with the primary key could not be found
	 */
	public Confidential_Messages findByPrimaryKey(long messageId)
		throws NoSuchConfidential_MessagesException;

	/**
	 * Returns the confidential_ messages with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param messageId the primary key of the confidential_ messages
	 * @return the confidential_ messages, or <code>null</code> if a confidential_ messages with the primary key could not be found
	 */
	public Confidential_Messages fetchByPrimaryKey(long messageId);

	/**
	 * Returns all the confidential_ messageses.
	 *
	 * @return the confidential_ messageses
	 */
	public java.util.List<Confidential_Messages> findAll();

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
	public java.util.List<Confidential_Messages> findAll(int start, int end);

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
	public java.util.List<Confidential_Messages> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Confidential_Messages>
			orderByComparator);

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
	public java.util.List<Confidential_Messages> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Confidential_Messages>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the confidential_ messageses from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of confidential_ messageses.
	 *
	 * @return the number of confidential_ messageses
	 */
	public int countAll();

}