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

package rec.supporting.resources.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

import rec.supporting.resources.exception.NoSuchsupportRException;
import rec.supporting.resources.model.supportR;

/**
 * The persistence interface for the support r service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see supportRUtil
 * @generated
 */
@ProviderType
public interface supportRPersistence extends BasePersistence<supportR> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link supportRUtil} to access the support r persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the support rs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching support rs
	 */
	public java.util.List<supportR> findByUuid(String uuid);

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
	public java.util.List<supportR> findByUuid(String uuid, int start, int end);

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
	public java.util.List<supportR> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<supportR>
			orderByComparator);

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
	public java.util.List<supportR> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<supportR>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first support r in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	public supportR findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<supportR>
				orderByComparator)
		throws NoSuchsupportRException;

	/**
	 * Returns the first support r in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r, or <code>null</code> if a matching support r could not be found
	 */
	public supportR fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<supportR>
			orderByComparator);

	/**
	 * Returns the last support r in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	public supportR findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<supportR>
				orderByComparator)
		throws NoSuchsupportRException;

	/**
	 * Returns the last support r in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r, or <code>null</code> if a matching support r could not be found
	 */
	public supportR fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<supportR>
			orderByComparator);

	/**
	 * Returns the support rs before and after the current support r in the ordered set where uuid = &#63;.
	 *
	 * @param supportRId the primary key of the current support r
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next support r
	 * @throws NoSuchsupportRException if a support r with the primary key could not be found
	 */
	public supportR[] findByUuid_PrevAndNext(
			long supportRId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<supportR>
				orderByComparator)
		throws NoSuchsupportRException;

	/**
	 * Removes all the support rs where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of support rs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching support rs
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the support r where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchsupportRException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	public supportR findByUUID_G(String uuid, long groupId)
		throws NoSuchsupportRException;

	/**
	 * Returns the support r where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching support r, or <code>null</code> if a matching support r could not be found
	 */
	public supportR fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the support r where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching support r, or <code>null</code> if a matching support r could not be found
	 */
	public supportR fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache);

	/**
	 * Removes the support r where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the support r that was removed
	 */
	public supportR removeByUUID_G(String uuid, long groupId)
		throws NoSuchsupportRException;

	/**
	 * Returns the number of support rs where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching support rs
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the support rs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching support rs
	 */
	public java.util.List<supportR> findByUuid_C(String uuid, long companyId);

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
	public java.util.List<supportR> findByUuid_C(
		String uuid, long companyId, int start, int end);

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
	public java.util.List<supportR> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<supportR>
			orderByComparator);

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
	public java.util.List<supportR> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<supportR>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first support r in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	public supportR findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<supportR>
				orderByComparator)
		throws NoSuchsupportRException;

	/**
	 * Returns the first support r in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r, or <code>null</code> if a matching support r could not be found
	 */
	public supportR fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<supportR>
			orderByComparator);

	/**
	 * Returns the last support r in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	public supportR findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<supportR>
				orderByComparator)
		throws NoSuchsupportRException;

	/**
	 * Returns the last support r in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r, or <code>null</code> if a matching support r could not be found
	 */
	public supportR fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<supportR>
			orderByComparator);

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
	public supportR[] findByUuid_C_PrevAndNext(
			long supportRId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<supportR>
				orderByComparator)
		throws NoSuchsupportRException;

	/**
	 * Removes all the support rs where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of support rs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching support rs
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns all the support rs where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching support rs
	 */
	public java.util.List<supportR> findByGroupId(long groupId);

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
	public java.util.List<supportR> findByGroupId(
		long groupId, int start, int end);

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
	public java.util.List<supportR> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<supportR>
			orderByComparator);

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
	public java.util.List<supportR> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<supportR>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first support r in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	public supportR findByGroupId_First(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<supportR>
				orderByComparator)
		throws NoSuchsupportRException;

	/**
	 * Returns the first support r in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r, or <code>null</code> if a matching support r could not be found
	 */
	public supportR fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<supportR>
			orderByComparator);

	/**
	 * Returns the last support r in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	public supportR findByGroupId_Last(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<supportR>
				orderByComparator)
		throws NoSuchsupportRException;

	/**
	 * Returns the last support r in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r, or <code>null</code> if a matching support r could not be found
	 */
	public supportR fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<supportR>
			orderByComparator);

	/**
	 * Returns the support rs before and after the current support r in the ordered set where groupId = &#63;.
	 *
	 * @param supportRId the primary key of the current support r
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next support r
	 * @throws NoSuchsupportRException if a support r with the primary key could not be found
	 */
	public supportR[] findByGroupId_PrevAndNext(
			long supportRId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<supportR>
				orderByComparator)
		throws NoSuchsupportRException;

	/**
	 * Removes all the support rs where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public void removeByGroupId(long groupId);

	/**
	 * Returns the number of support rs where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching support rs
	 */
	public int countByGroupId(long groupId);

	/**
	 * Returns all the support rs where groupId = &#63; and title = &#63;.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @return the matching support rs
	 */
	public java.util.List<supportR> findBySupportingResourceTitle(
		long groupId, String title);

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
	public java.util.List<supportR> findBySupportingResourceTitle(
		long groupId, String title, int start, int end);

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
	public java.util.List<supportR> findBySupportingResourceTitle(
		long groupId, String title, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<supportR>
			orderByComparator);

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
	public java.util.List<supportR> findBySupportingResourceTitle(
		long groupId, String title, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<supportR>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first support r in the ordered set where groupId = &#63; and title = &#63;.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	public supportR findBySupportingResourceTitle_First(
			long groupId, String title,
			com.liferay.portal.kernel.util.OrderByComparator<supportR>
				orderByComparator)
		throws NoSuchsupportRException;

	/**
	 * Returns the first support r in the ordered set where groupId = &#63; and title = &#63;.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r, or <code>null</code> if a matching support r could not be found
	 */
	public supportR fetchBySupportingResourceTitle_First(
		long groupId, String title,
		com.liferay.portal.kernel.util.OrderByComparator<supportR>
			orderByComparator);

	/**
	 * Returns the last support r in the ordered set where groupId = &#63; and title = &#63;.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	public supportR findBySupportingResourceTitle_Last(
			long groupId, String title,
			com.liferay.portal.kernel.util.OrderByComparator<supportR>
				orderByComparator)
		throws NoSuchsupportRException;

	/**
	 * Returns the last support r in the ordered set where groupId = &#63; and title = &#63;.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r, or <code>null</code> if a matching support r could not be found
	 */
	public supportR fetchBySupportingResourceTitle_Last(
		long groupId, String title,
		com.liferay.portal.kernel.util.OrderByComparator<supportR>
			orderByComparator);

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
	public supportR[] findBySupportingResourceTitle_PrevAndNext(
			long supportRId, long groupId, String title,
			com.liferay.portal.kernel.util.OrderByComparator<supportR>
				orderByComparator)
		throws NoSuchsupportRException;

	/**
	 * Removes all the support rs where groupId = &#63; and title = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 */
	public void removeBySupportingResourceTitle(long groupId, String title);

	/**
	 * Returns the number of support rs where groupId = &#63; and title = &#63;.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @return the number of matching support rs
	 */
	public int countBySupportingResourceTitle(long groupId, String title);

	/**
	 * Returns all the support rs where groupId = &#63; and type = &#63;.
	 *
	 * @param groupId the group ID
	 * @param type the type
	 * @return the matching support rs
	 */
	public java.util.List<supportR> findBySupportingResourceType(
		long groupId, String type);

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
	public java.util.List<supportR> findBySupportingResourceType(
		long groupId, String type, int start, int end);

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
	public java.util.List<supportR> findBySupportingResourceType(
		long groupId, String type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<supportR>
			orderByComparator);

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
	public java.util.List<supportR> findBySupportingResourceType(
		long groupId, String type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<supportR>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first support r in the ordered set where groupId = &#63; and type = &#63;.
	 *
	 * @param groupId the group ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	public supportR findBySupportingResourceType_First(
			long groupId, String type,
			com.liferay.portal.kernel.util.OrderByComparator<supportR>
				orderByComparator)
		throws NoSuchsupportRException;

	/**
	 * Returns the first support r in the ordered set where groupId = &#63; and type = &#63;.
	 *
	 * @param groupId the group ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r, or <code>null</code> if a matching support r could not be found
	 */
	public supportR fetchBySupportingResourceType_First(
		long groupId, String type,
		com.liferay.portal.kernel.util.OrderByComparator<supportR>
			orderByComparator);

	/**
	 * Returns the last support r in the ordered set where groupId = &#63; and type = &#63;.
	 *
	 * @param groupId the group ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	public supportR findBySupportingResourceType_Last(
			long groupId, String type,
			com.liferay.portal.kernel.util.OrderByComparator<supportR>
				orderByComparator)
		throws NoSuchsupportRException;

	/**
	 * Returns the last support r in the ordered set where groupId = &#63; and type = &#63;.
	 *
	 * @param groupId the group ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r, or <code>null</code> if a matching support r could not be found
	 */
	public supportR fetchBySupportingResourceType_Last(
		long groupId, String type,
		com.liferay.portal.kernel.util.OrderByComparator<supportR>
			orderByComparator);

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
	public supportR[] findBySupportingResourceType_PrevAndNext(
			long supportRId, long groupId, String type,
			com.liferay.portal.kernel.util.OrderByComparator<supportR>
				orderByComparator)
		throws NoSuchsupportRException;

	/**
	 * Removes all the support rs where groupId = &#63; and type = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param type the type
	 */
	public void removeBySupportingResourceType(long groupId, String type);

	/**
	 * Returns the number of support rs where groupId = &#63; and type = &#63;.
	 *
	 * @param groupId the group ID
	 * @param type the type
	 * @return the number of matching support rs
	 */
	public int countBySupportingResourceType(long groupId, String type);

	/**
	 * Returns all the support rs where groupId = &#63; and createDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param createDate the create date
	 * @return the matching support rs
	 */
	public java.util.List<supportR> findByCreateDate(
		long groupId, Date createDate);

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
	public java.util.List<supportR> findByCreateDate(
		long groupId, Date createDate, int start, int end);

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
	public java.util.List<supportR> findByCreateDate(
		long groupId, Date createDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<supportR>
			orderByComparator);

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
	public java.util.List<supportR> findByCreateDate(
		long groupId, Date createDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<supportR>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first support r in the ordered set where groupId = &#63; and createDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	public supportR findByCreateDate_First(
			long groupId, Date createDate,
			com.liferay.portal.kernel.util.OrderByComparator<supportR>
				orderByComparator)
		throws NoSuchsupportRException;

	/**
	 * Returns the first support r in the ordered set where groupId = &#63; and createDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r, or <code>null</code> if a matching support r could not be found
	 */
	public supportR fetchByCreateDate_First(
		long groupId, Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator<supportR>
			orderByComparator);

	/**
	 * Returns the last support r in the ordered set where groupId = &#63; and createDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	public supportR findByCreateDate_Last(
			long groupId, Date createDate,
			com.liferay.portal.kernel.util.OrderByComparator<supportR>
				orderByComparator)
		throws NoSuchsupportRException;

	/**
	 * Returns the last support r in the ordered set where groupId = &#63; and createDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r, or <code>null</code> if a matching support r could not be found
	 */
	public supportR fetchByCreateDate_Last(
		long groupId, Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator<supportR>
			orderByComparator);

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
	public supportR[] findByCreateDate_PrevAndNext(
			long supportRId, long groupId, Date createDate,
			com.liferay.portal.kernel.util.OrderByComparator<supportR>
				orderByComparator)
		throws NoSuchsupportRException;

	/**
	 * Removes all the support rs where groupId = &#63; and createDate = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param createDate the create date
	 */
	public void removeByCreateDate(long groupId, Date createDate);

	/**
	 * Returns the number of support rs where groupId = &#63; and createDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param createDate the create date
	 * @return the number of matching support rs
	 */
	public int countByCreateDate(long groupId, Date createDate);

	/**
	 * Returns all the support rs where groupId = &#63; and dueDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @return the matching support rs
	 */
	public java.util.List<supportR> findByDueDate(long groupId, Date dueDate);

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
	public java.util.List<supportR> findByDueDate(
		long groupId, Date dueDate, int start, int end);

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
	public java.util.List<supportR> findByDueDate(
		long groupId, Date dueDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<supportR>
			orderByComparator);

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
	public java.util.List<supportR> findByDueDate(
		long groupId, Date dueDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<supportR>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first support r in the ordered set where groupId = &#63; and dueDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	public supportR findByDueDate_First(
			long groupId, Date dueDate,
			com.liferay.portal.kernel.util.OrderByComparator<supportR>
				orderByComparator)
		throws NoSuchsupportRException;

	/**
	 * Returns the first support r in the ordered set where groupId = &#63; and dueDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r, or <code>null</code> if a matching support r could not be found
	 */
	public supportR fetchByDueDate_First(
		long groupId, Date dueDate,
		com.liferay.portal.kernel.util.OrderByComparator<supportR>
			orderByComparator);

	/**
	 * Returns the last support r in the ordered set where groupId = &#63; and dueDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	public supportR findByDueDate_Last(
			long groupId, Date dueDate,
			com.liferay.portal.kernel.util.OrderByComparator<supportR>
				orderByComparator)
		throws NoSuchsupportRException;

	/**
	 * Returns the last support r in the ordered set where groupId = &#63; and dueDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r, or <code>null</code> if a matching support r could not be found
	 */
	public supportR fetchByDueDate_Last(
		long groupId, Date dueDate,
		com.liferay.portal.kernel.util.OrderByComparator<supportR>
			orderByComparator);

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
	public supportR[] findByDueDate_PrevAndNext(
			long supportRId, long groupId, Date dueDate,
			com.liferay.portal.kernel.util.OrderByComparator<supportR>
				orderByComparator)
		throws NoSuchsupportRException;

	/**
	 * Removes all the support rs where groupId = &#63; and dueDate = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param dueDate the due date
	 */
	public void removeByDueDate(long groupId, Date dueDate);

	/**
	 * Returns the number of support rs where groupId = &#63; and dueDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @return the number of matching support rs
	 */
	public int countByDueDate(long groupId, Date dueDate);

	/**
	 * Returns all the support rs where groupId = &#63; and dueDate = &#63; and createDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @param createDate the create date
	 * @return the matching support rs
	 */
	public java.util.List<supportR> findByCreateDateAndDueDate(
		long groupId, Date dueDate, Date createDate);

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
	public java.util.List<supportR> findByCreateDateAndDueDate(
		long groupId, Date dueDate, Date createDate, int start, int end);

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
	public java.util.List<supportR> findByCreateDateAndDueDate(
		long groupId, Date dueDate, Date createDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<supportR>
			orderByComparator);

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
	public java.util.List<supportR> findByCreateDateAndDueDate(
		long groupId, Date dueDate, Date createDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<supportR>
			orderByComparator,
		boolean useFinderCache);

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
	public supportR findByCreateDateAndDueDate_First(
			long groupId, Date dueDate, Date createDate,
			com.liferay.portal.kernel.util.OrderByComparator<supportR>
				orderByComparator)
		throws NoSuchsupportRException;

	/**
	 * Returns the first support r in the ordered set where groupId = &#63; and dueDate = &#63; and createDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r, or <code>null</code> if a matching support r could not be found
	 */
	public supportR fetchByCreateDateAndDueDate_First(
		long groupId, Date dueDate, Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator<supportR>
			orderByComparator);

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
	public supportR findByCreateDateAndDueDate_Last(
			long groupId, Date dueDate, Date createDate,
			com.liferay.portal.kernel.util.OrderByComparator<supportR>
				orderByComparator)
		throws NoSuchsupportRException;

	/**
	 * Returns the last support r in the ordered set where groupId = &#63; and dueDate = &#63; and createDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r, or <code>null</code> if a matching support r could not be found
	 */
	public supportR fetchByCreateDateAndDueDate_Last(
		long groupId, Date dueDate, Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator<supportR>
			orderByComparator);

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
	public supportR[] findByCreateDateAndDueDate_PrevAndNext(
			long supportRId, long groupId, Date dueDate, Date createDate,
			com.liferay.portal.kernel.util.OrderByComparator<supportR>
				orderByComparator)
		throws NoSuchsupportRException;

	/**
	 * Removes all the support rs where groupId = &#63; and dueDate = &#63; and createDate = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @param createDate the create date
	 */
	public void removeByCreateDateAndDueDate(
		long groupId, Date dueDate, Date createDate);

	/**
	 * Returns the number of support rs where groupId = &#63; and dueDate = &#63; and createDate = &#63;.
	 *
	 * @param groupId the group ID
	 * @param dueDate the due date
	 * @param createDate the create date
	 * @return the number of matching support rs
	 */
	public int countByCreateDateAndDueDate(
		long groupId, Date dueDate, Date createDate);

	/**
	 * Returns all the support rs where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the matching support rs
	 */
	public java.util.List<supportR> findByStatus(long groupId, String status);

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
	public java.util.List<supportR> findByStatus(
		long groupId, String status, int start, int end);

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
	public java.util.List<supportR> findByStatus(
		long groupId, String status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<supportR>
			orderByComparator);

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
	public java.util.List<supportR> findByStatus(
		long groupId, String status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<supportR>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first support r in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	public supportR findByStatus_First(
			long groupId, String status,
			com.liferay.portal.kernel.util.OrderByComparator<supportR>
				orderByComparator)
		throws NoSuchsupportRException;

	/**
	 * Returns the first support r in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r, or <code>null</code> if a matching support r could not be found
	 */
	public supportR fetchByStatus_First(
		long groupId, String status,
		com.liferay.portal.kernel.util.OrderByComparator<supportR>
			orderByComparator);

	/**
	 * Returns the last support r in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	public supportR findByStatus_Last(
			long groupId, String status,
			com.liferay.portal.kernel.util.OrderByComparator<supportR>
				orderByComparator)
		throws NoSuchsupportRException;

	/**
	 * Returns the last support r in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r, or <code>null</code> if a matching support r could not be found
	 */
	public supportR fetchByStatus_Last(
		long groupId, String status,
		com.liferay.portal.kernel.util.OrderByComparator<supportR>
			orderByComparator);

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
	public supportR[] findByStatus_PrevAndNext(
			long supportRId, long groupId, String status,
			com.liferay.portal.kernel.util.OrderByComparator<supportR>
				orderByComparator)
		throws NoSuchsupportRException;

	/**
	 * Removes all the support rs where groupId = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 */
	public void removeByStatus(long groupId, String status);

	/**
	 * Returns the number of support rs where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the number of matching support rs
	 */
	public int countByStatus(long groupId, String status);

	/**
	 * Returns all the support rs where status = &#63;.
	 *
	 * @param status the status
	 * @return the matching support rs
	 */
	public java.util.List<supportR> findByStatusOnly(String status);

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
	public java.util.List<supportR> findByStatusOnly(
		String status, int start, int end);

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
	public java.util.List<supportR> findByStatusOnly(
		String status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<supportR>
			orderByComparator);

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
	public java.util.List<supportR> findByStatusOnly(
		String status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<supportR>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first support r in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	public supportR findByStatusOnly_First(
			String status,
			com.liferay.portal.kernel.util.OrderByComparator<supportR>
				orderByComparator)
		throws NoSuchsupportRException;

	/**
	 * Returns the first support r in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r, or <code>null</code> if a matching support r could not be found
	 */
	public supportR fetchByStatusOnly_First(
		String status,
		com.liferay.portal.kernel.util.OrderByComparator<supportR>
			orderByComparator);

	/**
	 * Returns the last support r in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	public supportR findByStatusOnly_Last(
			String status,
			com.liferay.portal.kernel.util.OrderByComparator<supportR>
				orderByComparator)
		throws NoSuchsupportRException;

	/**
	 * Returns the last support r in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r, or <code>null</code> if a matching support r could not be found
	 */
	public supportR fetchByStatusOnly_Last(
		String status,
		com.liferay.portal.kernel.util.OrderByComparator<supportR>
			orderByComparator);

	/**
	 * Returns the support rs before and after the current support r in the ordered set where status = &#63;.
	 *
	 * @param supportRId the primary key of the current support r
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next support r
	 * @throws NoSuchsupportRException if a support r with the primary key could not be found
	 */
	public supportR[] findByStatusOnly_PrevAndNext(
			long supportRId, String status,
			com.liferay.portal.kernel.util.OrderByComparator<supportR>
				orderByComparator)
		throws NoSuchsupportRException;

	/**
	 * Removes all the support rs where status = &#63; from the database.
	 *
	 * @param status the status
	 */
	public void removeByStatusOnly(String status);

	/**
	 * Returns the number of support rs where status = &#63;.
	 *
	 * @param status the status
	 * @return the number of matching support rs
	 */
	public int countByStatusOnly(String status);

	/**
	 * Returns all the support rs where groupId = &#63; and link = &#63;.
	 *
	 * @param groupId the group ID
	 * @param link the link
	 * @return the matching support rs
	 */
	public java.util.List<supportR> findByLink(long groupId, String link);

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
	public java.util.List<supportR> findByLink(
		long groupId, String link, int start, int end);

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
	public java.util.List<supportR> findByLink(
		long groupId, String link, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<supportR>
			orderByComparator);

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
	public java.util.List<supportR> findByLink(
		long groupId, String link, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<supportR>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first support r in the ordered set where groupId = &#63; and link = &#63;.
	 *
	 * @param groupId the group ID
	 * @param link the link
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	public supportR findByLink_First(
			long groupId, String link,
			com.liferay.portal.kernel.util.OrderByComparator<supportR>
				orderByComparator)
		throws NoSuchsupportRException;

	/**
	 * Returns the first support r in the ordered set where groupId = &#63; and link = &#63;.
	 *
	 * @param groupId the group ID
	 * @param link the link
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching support r, or <code>null</code> if a matching support r could not be found
	 */
	public supportR fetchByLink_First(
		long groupId, String link,
		com.liferay.portal.kernel.util.OrderByComparator<supportR>
			orderByComparator);

	/**
	 * Returns the last support r in the ordered set where groupId = &#63; and link = &#63;.
	 *
	 * @param groupId the group ID
	 * @param link the link
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r
	 * @throws NoSuchsupportRException if a matching support r could not be found
	 */
	public supportR findByLink_Last(
			long groupId, String link,
			com.liferay.portal.kernel.util.OrderByComparator<supportR>
				orderByComparator)
		throws NoSuchsupportRException;

	/**
	 * Returns the last support r in the ordered set where groupId = &#63; and link = &#63;.
	 *
	 * @param groupId the group ID
	 * @param link the link
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching support r, or <code>null</code> if a matching support r could not be found
	 */
	public supportR fetchByLink_Last(
		long groupId, String link,
		com.liferay.portal.kernel.util.OrderByComparator<supportR>
			orderByComparator);

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
	public supportR[] findByLink_PrevAndNext(
			long supportRId, long groupId, String link,
			com.liferay.portal.kernel.util.OrderByComparator<supportR>
				orderByComparator)
		throws NoSuchsupportRException;

	/**
	 * Removes all the support rs where groupId = &#63; and link = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param link the link
	 */
	public void removeByLink(long groupId, String link);

	/**
	 * Returns the number of support rs where groupId = &#63; and link = &#63;.
	 *
	 * @param groupId the group ID
	 * @param link the link
	 * @return the number of matching support rs
	 */
	public int countByLink(long groupId, String link);

	/**
	 * Caches the support r in the entity cache if it is enabled.
	 *
	 * @param supportR the support r
	 */
	public void cacheResult(supportR supportR);

	/**
	 * Caches the support rs in the entity cache if it is enabled.
	 *
	 * @param supportRs the support rs
	 */
	public void cacheResult(java.util.List<supportR> supportRs);

	/**
	 * Creates a new support r with the primary key. Does not add the support r to the database.
	 *
	 * @param supportRId the primary key for the new support r
	 * @return the new support r
	 */
	public supportR create(long supportRId);

	/**
	 * Removes the support r with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param supportRId the primary key of the support r
	 * @return the support r that was removed
	 * @throws NoSuchsupportRException if a support r with the primary key could not be found
	 */
	public supportR remove(long supportRId) throws NoSuchsupportRException;

	public supportR updateImpl(supportR supportR);

	/**
	 * Returns the support r with the primary key or throws a <code>NoSuchsupportRException</code> if it could not be found.
	 *
	 * @param supportRId the primary key of the support r
	 * @return the support r
	 * @throws NoSuchsupportRException if a support r with the primary key could not be found
	 */
	public supportR findByPrimaryKey(long supportRId)
		throws NoSuchsupportRException;

	/**
	 * Returns the support r with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param supportRId the primary key of the support r
	 * @return the support r, or <code>null</code> if a support r with the primary key could not be found
	 */
	public supportR fetchByPrimaryKey(long supportRId);

	/**
	 * Returns all the support rs.
	 *
	 * @return the support rs
	 */
	public java.util.List<supportR> findAll();

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
	public java.util.List<supportR> findAll(int start, int end);

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
	public java.util.List<supportR> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<supportR>
			orderByComparator);

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
	public java.util.List<supportR> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<supportR>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the support rs from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of support rs.
	 *
	 * @return the number of support rs
	 */
	public int countAll();

}