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

package com.everis.messages.service.builder.service.persistence;

import com.everis.messages.service.builder.exception.NoSuchMessagesException;
import com.everis.messages.service.builder.model.Messages;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the messages service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagesUtil
 * @generated
 */
@ProviderType
public interface MessagesPersistence extends BasePersistence<Messages> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MessagesUtil} to access the messages persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the messageses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching messageses
	 */
	public java.util.List<Messages> findByUuid(String uuid);

	/**
	 * Returns a range of all the messageses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessagesModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of messageses
	 * @param end the upper bound of the range of messageses (not inclusive)
	 * @return the range of matching messageses
	 */
	public java.util.List<Messages> findByUuid(String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the messageses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessagesModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of messageses
	 * @param end the upper bound of the range of messageses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching messageses
	 */
	public java.util.List<Messages> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Messages>
			orderByComparator);

	/**
	 * Returns an ordered range of all the messageses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessagesModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of messageses
	 * @param end the upper bound of the range of messageses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching messageses
	 */
	public java.util.List<Messages> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Messages>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first messages in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching messages
	 * @throws NoSuchMessagesException if a matching messages could not be found
	 */
	public Messages findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Messages>
				orderByComparator)
		throws NoSuchMessagesException;

	/**
	 * Returns the first messages in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching messages, or <code>null</code> if a matching messages could not be found
	 */
	public Messages fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Messages>
			orderByComparator);

	/**
	 * Returns the last messages in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching messages
	 * @throws NoSuchMessagesException if a matching messages could not be found
	 */
	public Messages findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Messages>
				orderByComparator)
		throws NoSuchMessagesException;

	/**
	 * Returns the last messages in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching messages, or <code>null</code> if a matching messages could not be found
	 */
	public Messages fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Messages>
			orderByComparator);

	/**
	 * Returns the messageses before and after the current messages in the ordered set where uuid = &#63;.
	 *
	 * @param notificationEngineId the primary key of the current messages
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next messages
	 * @throws NoSuchMessagesException if a messages with the primary key could not be found
	 */
	public Messages[] findByUuid_PrevAndNext(
			long notificationEngineId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Messages>
				orderByComparator)
		throws NoSuchMessagesException;

	/**
	 * Removes all the messageses where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of messageses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching messageses
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the messages where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchMessagesException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching messages
	 * @throws NoSuchMessagesException if a matching messages could not be found
	 */
	public Messages findByUUID_G(String uuid, long groupId)
		throws NoSuchMessagesException;

	/**
	 * Returns the messages where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching messages, or <code>null</code> if a matching messages could not be found
	 */
	public Messages fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the messages where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching messages, or <code>null</code> if a matching messages could not be found
	 */
	public Messages fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache);

	/**
	 * Removes the messages where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the messages that was removed
	 */
	public Messages removeByUUID_G(String uuid, long groupId)
		throws NoSuchMessagesException;

	/**
	 * Returns the number of messageses where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching messageses
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the messageses where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching messageses
	 */
	public java.util.List<Messages> findByUuid_C(String uuid, long companyId);

	/**
	 * Returns a range of all the messageses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessagesModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of messageses
	 * @param end the upper bound of the range of messageses (not inclusive)
	 * @return the range of matching messageses
	 */
	public java.util.List<Messages> findByUuid_C(
		String uuid, long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the messageses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessagesModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of messageses
	 * @param end the upper bound of the range of messageses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching messageses
	 */
	public java.util.List<Messages> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Messages>
			orderByComparator);

	/**
	 * Returns an ordered range of all the messageses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessagesModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of messageses
	 * @param end the upper bound of the range of messageses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching messageses
	 */
	public java.util.List<Messages> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Messages>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first messages in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching messages
	 * @throws NoSuchMessagesException if a matching messages could not be found
	 */
	public Messages findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Messages>
				orderByComparator)
		throws NoSuchMessagesException;

	/**
	 * Returns the first messages in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching messages, or <code>null</code> if a matching messages could not be found
	 */
	public Messages fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Messages>
			orderByComparator);

	/**
	 * Returns the last messages in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching messages
	 * @throws NoSuchMessagesException if a matching messages could not be found
	 */
	public Messages findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Messages>
				orderByComparator)
		throws NoSuchMessagesException;

	/**
	 * Returns the last messages in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching messages, or <code>null</code> if a matching messages could not be found
	 */
	public Messages fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Messages>
			orderByComparator);

	/**
	 * Returns the messageses before and after the current messages in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param notificationEngineId the primary key of the current messages
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next messages
	 * @throws NoSuchMessagesException if a messages with the primary key could not be found
	 */
	public Messages[] findByUuid_C_PrevAndNext(
			long notificationEngineId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Messages>
				orderByComparator)
		throws NoSuchMessagesException;

	/**
	 * Removes all the messageses where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of messageses where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching messageses
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns all the messageses where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching messageses
	 */
	public java.util.List<Messages> findByCompanyId(long companyId);

	/**
	 * Returns a range of all the messageses where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessagesModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of messageses
	 * @param end the upper bound of the range of messageses (not inclusive)
	 * @return the range of matching messageses
	 */
	public java.util.List<Messages> findByCompanyId(
		long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the messageses where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessagesModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of messageses
	 * @param end the upper bound of the range of messageses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching messageses
	 */
	public java.util.List<Messages> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Messages>
			orderByComparator);

	/**
	 * Returns an ordered range of all the messageses where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessagesModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of messageses
	 * @param end the upper bound of the range of messageses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching messageses
	 */
	public java.util.List<Messages> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Messages>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first messages in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching messages
	 * @throws NoSuchMessagesException if a matching messages could not be found
	 */
	public Messages findByCompanyId_First(
			long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Messages>
				orderByComparator)
		throws NoSuchMessagesException;

	/**
	 * Returns the first messages in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching messages, or <code>null</code> if a matching messages could not be found
	 */
	public Messages fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Messages>
			orderByComparator);

	/**
	 * Returns the last messages in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching messages
	 * @throws NoSuchMessagesException if a matching messages could not be found
	 */
	public Messages findByCompanyId_Last(
			long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Messages>
				orderByComparator)
		throws NoSuchMessagesException;

	/**
	 * Returns the last messages in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching messages, or <code>null</code> if a matching messages could not be found
	 */
	public Messages fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Messages>
			orderByComparator);

	/**
	 * Returns the messageses before and after the current messages in the ordered set where companyId = &#63;.
	 *
	 * @param notificationEngineId the primary key of the current messages
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next messages
	 * @throws NoSuchMessagesException if a messages with the primary key could not be found
	 */
	public Messages[] findByCompanyId_PrevAndNext(
			long notificationEngineId, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Messages>
				orderByComparator)
		throws NoSuchMessagesException;

	/**
	 * Removes all the messageses where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	public void removeByCompanyId(long companyId);

	/**
	 * Returns the number of messageses where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching messageses
	 */
	public int countByCompanyId(long companyId);

	/**
	 * Returns all the messageses where name = &#63; and companyId = &#63;.
	 *
	 * @param name the name
	 * @param companyId the company ID
	 * @return the matching messageses
	 */
	public java.util.List<Messages> findByNameCompany(
		String name, long companyId);

	/**
	 * Returns a range of all the messageses where name = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessagesModelImpl</code>.
	 * </p>
	 *
	 * @param name the name
	 * @param companyId the company ID
	 * @param start the lower bound of the range of messageses
	 * @param end the upper bound of the range of messageses (not inclusive)
	 * @return the range of matching messageses
	 */
	public java.util.List<Messages> findByNameCompany(
		String name, long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the messageses where name = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessagesModelImpl</code>.
	 * </p>
	 *
	 * @param name the name
	 * @param companyId the company ID
	 * @param start the lower bound of the range of messageses
	 * @param end the upper bound of the range of messageses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching messageses
	 */
	public java.util.List<Messages> findByNameCompany(
		String name, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Messages>
			orderByComparator);

	/**
	 * Returns an ordered range of all the messageses where name = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessagesModelImpl</code>.
	 * </p>
	 *
	 * @param name the name
	 * @param companyId the company ID
	 * @param start the lower bound of the range of messageses
	 * @param end the upper bound of the range of messageses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching messageses
	 */
	public java.util.List<Messages> findByNameCompany(
		String name, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Messages>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first messages in the ordered set where name = &#63; and companyId = &#63;.
	 *
	 * @param name the name
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching messages
	 * @throws NoSuchMessagesException if a matching messages could not be found
	 */
	public Messages findByNameCompany_First(
			String name, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Messages>
				orderByComparator)
		throws NoSuchMessagesException;

	/**
	 * Returns the first messages in the ordered set where name = &#63; and companyId = &#63;.
	 *
	 * @param name the name
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching messages, or <code>null</code> if a matching messages could not be found
	 */
	public Messages fetchByNameCompany_First(
		String name, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Messages>
			orderByComparator);

	/**
	 * Returns the last messages in the ordered set where name = &#63; and companyId = &#63;.
	 *
	 * @param name the name
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching messages
	 * @throws NoSuchMessagesException if a matching messages could not be found
	 */
	public Messages findByNameCompany_Last(
			String name, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Messages>
				orderByComparator)
		throws NoSuchMessagesException;

	/**
	 * Returns the last messages in the ordered set where name = &#63; and companyId = &#63;.
	 *
	 * @param name the name
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching messages, or <code>null</code> if a matching messages could not be found
	 */
	public Messages fetchByNameCompany_Last(
		String name, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Messages>
			orderByComparator);

	/**
	 * Returns the messageses before and after the current messages in the ordered set where name = &#63; and companyId = &#63;.
	 *
	 * @param notificationEngineId the primary key of the current messages
	 * @param name the name
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next messages
	 * @throws NoSuchMessagesException if a messages with the primary key could not be found
	 */
	public Messages[] findByNameCompany_PrevAndNext(
			long notificationEngineId, String name, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Messages>
				orderByComparator)
		throws NoSuchMessagesException;

	/**
	 * Removes all the messageses where name = &#63; and companyId = &#63; from the database.
	 *
	 * @param name the name
	 * @param companyId the company ID
	 */
	public void removeByNameCompany(String name, long companyId);

	/**
	 * Returns the number of messageses where name = &#63; and companyId = &#63;.
	 *
	 * @param name the name
	 * @param companyId the company ID
	 * @return the number of matching messageses
	 */
	public int countByNameCompany(String name, long companyId);

	/**
	 * Caches the messages in the entity cache if it is enabled.
	 *
	 * @param messages the messages
	 */
	public void cacheResult(Messages messages);

	/**
	 * Caches the messageses in the entity cache if it is enabled.
	 *
	 * @param messageses the messageses
	 */
	public void cacheResult(java.util.List<Messages> messageses);

	/**
	 * Creates a new messages with the primary key. Does not add the messages to the database.
	 *
	 * @param notificationEngineId the primary key for the new messages
	 * @return the new messages
	 */
	public Messages create(long notificationEngineId);

	/**
	 * Removes the messages with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param notificationEngineId the primary key of the messages
	 * @return the messages that was removed
	 * @throws NoSuchMessagesException if a messages with the primary key could not be found
	 */
	public Messages remove(long notificationEngineId)
		throws NoSuchMessagesException;

	public Messages updateImpl(Messages messages);

	/**
	 * Returns the messages with the primary key or throws a <code>NoSuchMessagesException</code> if it could not be found.
	 *
	 * @param notificationEngineId the primary key of the messages
	 * @return the messages
	 * @throws NoSuchMessagesException if a messages with the primary key could not be found
	 */
	public Messages findByPrimaryKey(long notificationEngineId)
		throws NoSuchMessagesException;

	/**
	 * Returns the messages with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param notificationEngineId the primary key of the messages
	 * @return the messages, or <code>null</code> if a messages with the primary key could not be found
	 */
	public Messages fetchByPrimaryKey(long notificationEngineId);

	/**
	 * Returns all the messageses.
	 *
	 * @return the messageses
	 */
	public java.util.List<Messages> findAll();

	/**
	 * Returns a range of all the messageses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessagesModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of messageses
	 * @param end the upper bound of the range of messageses (not inclusive)
	 * @return the range of messageses
	 */
	public java.util.List<Messages> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the messageses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessagesModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of messageses
	 * @param end the upper bound of the range of messageses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of messageses
	 */
	public java.util.List<Messages> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Messages>
			orderByComparator);

	/**
	 * Returns an ordered range of all the messageses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MessagesModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of messageses
	 * @param end the upper bound of the range of messageses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of messageses
	 */
	public java.util.List<Messages> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Messages>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the messageses from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of messageses.
	 *
	 * @return the number of messageses
	 */
	public int countAll();

}