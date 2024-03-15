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

package rec.everis.forms.service.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

import rec.everis.forms.service.exception.NoSuchMaintenanceActivityFormsException;
import rec.everis.forms.service.model.MaintenanceActivityForms;

/**
 * The persistence interface for the maintenance activity forms service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MaintenanceActivityFormsUtil
 * @generated
 */
@ProviderType
public interface MaintenanceActivityFormsPersistence
	extends BasePersistence<MaintenanceActivityForms> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MaintenanceActivityFormsUtil} to access the maintenance activity forms persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the maintenance activity formses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching maintenance activity formses
	 */
	public java.util.List<MaintenanceActivityForms> findByUuid(String uuid);

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
	public java.util.List<MaintenanceActivityForms> findByUuid(
		String uuid, int start, int end);

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
	public java.util.List<MaintenanceActivityForms> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<MaintenanceActivityForms> orderByComparator);

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
	public java.util.List<MaintenanceActivityForms> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<MaintenanceActivityForms> orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first maintenance activity forms in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching maintenance activity forms
	 * @throws NoSuchMaintenanceActivityFormsException if a matching maintenance activity forms could not be found
	 */
	public MaintenanceActivityForms findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator
				<MaintenanceActivityForms> orderByComparator)
		throws NoSuchMaintenanceActivityFormsException;

	/**
	 * Returns the first maintenance activity forms in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching maintenance activity forms, or <code>null</code> if a matching maintenance activity forms could not be found
	 */
	public MaintenanceActivityForms fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator
			<MaintenanceActivityForms> orderByComparator);

	/**
	 * Returns the last maintenance activity forms in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching maintenance activity forms
	 * @throws NoSuchMaintenanceActivityFormsException if a matching maintenance activity forms could not be found
	 */
	public MaintenanceActivityForms findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator
				<MaintenanceActivityForms> orderByComparator)
		throws NoSuchMaintenanceActivityFormsException;

	/**
	 * Returns the last maintenance activity forms in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching maintenance activity forms, or <code>null</code> if a matching maintenance activity forms could not be found
	 */
	public MaintenanceActivityForms fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator
			<MaintenanceActivityForms> orderByComparator);

	/**
	 * Returns the maintenance activity formses before and after the current maintenance activity forms in the ordered set where uuid = &#63;.
	 *
	 * @param maintenanceactivityformId the primary key of the current maintenance activity forms
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next maintenance activity forms
	 * @throws NoSuchMaintenanceActivityFormsException if a maintenance activity forms with the primary key could not be found
	 */
	public MaintenanceActivityForms[] findByUuid_PrevAndNext(
			long maintenanceactivityformId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator
				<MaintenanceActivityForms> orderByComparator)
		throws NoSuchMaintenanceActivityFormsException;

	/**
	 * Removes all the maintenance activity formses where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of maintenance activity formses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching maintenance activity formses
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the maintenance activity forms where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchMaintenanceActivityFormsException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching maintenance activity forms
	 * @throws NoSuchMaintenanceActivityFormsException if a matching maintenance activity forms could not be found
	 */
	public MaintenanceActivityForms findByUUID_G(String uuid, long groupId)
		throws NoSuchMaintenanceActivityFormsException;

	/**
	 * Returns the maintenance activity forms where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching maintenance activity forms, or <code>null</code> if a matching maintenance activity forms could not be found
	 */
	public MaintenanceActivityForms fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the maintenance activity forms where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching maintenance activity forms, or <code>null</code> if a matching maintenance activity forms could not be found
	 */
	public MaintenanceActivityForms fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache);

	/**
	 * Removes the maintenance activity forms where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the maintenance activity forms that was removed
	 */
	public MaintenanceActivityForms removeByUUID_G(String uuid, long groupId)
		throws NoSuchMaintenanceActivityFormsException;

	/**
	 * Returns the number of maintenance activity formses where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching maintenance activity formses
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the maintenance activity formses where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching maintenance activity formses
	 */
	public java.util.List<MaintenanceActivityForms> findByUuid_C(
		String uuid, long companyId);

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
	public java.util.List<MaintenanceActivityForms> findByUuid_C(
		String uuid, long companyId, int start, int end);

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
	public java.util.List<MaintenanceActivityForms> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<MaintenanceActivityForms> orderByComparator);

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
	public java.util.List<MaintenanceActivityForms> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<MaintenanceActivityForms> orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first maintenance activity forms in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching maintenance activity forms
	 * @throws NoSuchMaintenanceActivityFormsException if a matching maintenance activity forms could not be found
	 */
	public MaintenanceActivityForms findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator
				<MaintenanceActivityForms> orderByComparator)
		throws NoSuchMaintenanceActivityFormsException;

	/**
	 * Returns the first maintenance activity forms in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching maintenance activity forms, or <code>null</code> if a matching maintenance activity forms could not be found
	 */
	public MaintenanceActivityForms fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator
			<MaintenanceActivityForms> orderByComparator);

	/**
	 * Returns the last maintenance activity forms in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching maintenance activity forms
	 * @throws NoSuchMaintenanceActivityFormsException if a matching maintenance activity forms could not be found
	 */
	public MaintenanceActivityForms findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator
				<MaintenanceActivityForms> orderByComparator)
		throws NoSuchMaintenanceActivityFormsException;

	/**
	 * Returns the last maintenance activity forms in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching maintenance activity forms, or <code>null</code> if a matching maintenance activity forms could not be found
	 */
	public MaintenanceActivityForms fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator
			<MaintenanceActivityForms> orderByComparator);

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
	public MaintenanceActivityForms[] findByUuid_C_PrevAndNext(
			long maintenanceactivityformId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator
				<MaintenanceActivityForms> orderByComparator)
		throws NoSuchMaintenanceActivityFormsException;

	/**
	 * Removes all the maintenance activity formses where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of maintenance activity formses where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching maintenance activity formses
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns all the maintenance activity formses where formInstanceRecordVersionId = &#63;.
	 *
	 * @param formInstanceRecordVersionId the form instance record version ID
	 * @return the matching maintenance activity formses
	 */
	public java.util.List<MaintenanceActivityForms>
		findByformInstanceRecordVersionId(long formInstanceRecordVersionId);

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
	public java.util.List<MaintenanceActivityForms>
		findByformInstanceRecordVersionId(
			long formInstanceRecordVersionId, int start, int end);

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
	public java.util.List<MaintenanceActivityForms>
		findByformInstanceRecordVersionId(
			long formInstanceRecordVersionId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<MaintenanceActivityForms> orderByComparator);

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
	public java.util.List<MaintenanceActivityForms>
		findByformInstanceRecordVersionId(
			long formInstanceRecordVersionId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<MaintenanceActivityForms> orderByComparator,
			boolean useFinderCache);

	/**
	 * Returns the first maintenance activity forms in the ordered set where formInstanceRecordVersionId = &#63;.
	 *
	 * @param formInstanceRecordVersionId the form instance record version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching maintenance activity forms
	 * @throws NoSuchMaintenanceActivityFormsException if a matching maintenance activity forms could not be found
	 */
	public MaintenanceActivityForms findByformInstanceRecordVersionId_First(
			long formInstanceRecordVersionId,
			com.liferay.portal.kernel.util.OrderByComparator
				<MaintenanceActivityForms> orderByComparator)
		throws NoSuchMaintenanceActivityFormsException;

	/**
	 * Returns the first maintenance activity forms in the ordered set where formInstanceRecordVersionId = &#63;.
	 *
	 * @param formInstanceRecordVersionId the form instance record version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching maintenance activity forms, or <code>null</code> if a matching maintenance activity forms could not be found
	 */
	public MaintenanceActivityForms fetchByformInstanceRecordVersionId_First(
		long formInstanceRecordVersionId,
		com.liferay.portal.kernel.util.OrderByComparator
			<MaintenanceActivityForms> orderByComparator);

	/**
	 * Returns the last maintenance activity forms in the ordered set where formInstanceRecordVersionId = &#63;.
	 *
	 * @param formInstanceRecordVersionId the form instance record version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching maintenance activity forms
	 * @throws NoSuchMaintenanceActivityFormsException if a matching maintenance activity forms could not be found
	 */
	public MaintenanceActivityForms findByformInstanceRecordVersionId_Last(
			long formInstanceRecordVersionId,
			com.liferay.portal.kernel.util.OrderByComparator
				<MaintenanceActivityForms> orderByComparator)
		throws NoSuchMaintenanceActivityFormsException;

	/**
	 * Returns the last maintenance activity forms in the ordered set where formInstanceRecordVersionId = &#63;.
	 *
	 * @param formInstanceRecordVersionId the form instance record version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching maintenance activity forms, or <code>null</code> if a matching maintenance activity forms could not be found
	 */
	public MaintenanceActivityForms fetchByformInstanceRecordVersionId_Last(
		long formInstanceRecordVersionId,
		com.liferay.portal.kernel.util.OrderByComparator
			<MaintenanceActivityForms> orderByComparator);

	/**
	 * Returns the maintenance activity formses before and after the current maintenance activity forms in the ordered set where formInstanceRecordVersionId = &#63;.
	 *
	 * @param maintenanceactivityformId the primary key of the current maintenance activity forms
	 * @param formInstanceRecordVersionId the form instance record version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next maintenance activity forms
	 * @throws NoSuchMaintenanceActivityFormsException if a maintenance activity forms with the primary key could not be found
	 */
	public MaintenanceActivityForms[]
			findByformInstanceRecordVersionId_PrevAndNext(
				long maintenanceactivityformId,
				long formInstanceRecordVersionId,
				com.liferay.portal.kernel.util.OrderByComparator
					<MaintenanceActivityForms> orderByComparator)
		throws NoSuchMaintenanceActivityFormsException;

	/**
	 * Removes all the maintenance activity formses where formInstanceRecordVersionId = &#63; from the database.
	 *
	 * @param formInstanceRecordVersionId the form instance record version ID
	 */
	public void removeByformInstanceRecordVersionId(
		long formInstanceRecordVersionId);

	/**
	 * Returns the number of maintenance activity formses where formInstanceRecordVersionId = &#63;.
	 *
	 * @param formInstanceRecordVersionId the form instance record version ID
	 * @return the number of matching maintenance activity formses
	 */
	public int countByformInstanceRecordVersionId(
		long formInstanceRecordVersionId);

	/**
	 * Returns all the maintenance activity formses where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching maintenance activity formses
	 */
	public java.util.List<MaintenanceActivityForms> findByGroupId(long groupId);

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
	public java.util.List<MaintenanceActivityForms> findByGroupId(
		long groupId, int start, int end);

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
	public java.util.List<MaintenanceActivityForms> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<MaintenanceActivityForms> orderByComparator);

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
	public java.util.List<MaintenanceActivityForms> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<MaintenanceActivityForms> orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first maintenance activity forms in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching maintenance activity forms
	 * @throws NoSuchMaintenanceActivityFormsException if a matching maintenance activity forms could not be found
	 */
	public MaintenanceActivityForms findByGroupId_First(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator
				<MaintenanceActivityForms> orderByComparator)
		throws NoSuchMaintenanceActivityFormsException;

	/**
	 * Returns the first maintenance activity forms in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching maintenance activity forms, or <code>null</code> if a matching maintenance activity forms could not be found
	 */
	public MaintenanceActivityForms fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator
			<MaintenanceActivityForms> orderByComparator);

	/**
	 * Returns the last maintenance activity forms in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching maintenance activity forms
	 * @throws NoSuchMaintenanceActivityFormsException if a matching maintenance activity forms could not be found
	 */
	public MaintenanceActivityForms findByGroupId_Last(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator
				<MaintenanceActivityForms> orderByComparator)
		throws NoSuchMaintenanceActivityFormsException;

	/**
	 * Returns the last maintenance activity forms in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching maintenance activity forms, or <code>null</code> if a matching maintenance activity forms could not be found
	 */
	public MaintenanceActivityForms fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator
			<MaintenanceActivityForms> orderByComparator);

	/**
	 * Returns the maintenance activity formses before and after the current maintenance activity forms in the ordered set where groupId = &#63;.
	 *
	 * @param maintenanceactivityformId the primary key of the current maintenance activity forms
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next maintenance activity forms
	 * @throws NoSuchMaintenanceActivityFormsException if a maintenance activity forms with the primary key could not be found
	 */
	public MaintenanceActivityForms[] findByGroupId_PrevAndNext(
			long maintenanceactivityformId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator
				<MaintenanceActivityForms> orderByComparator)
		throws NoSuchMaintenanceActivityFormsException;

	/**
	 * Removes all the maintenance activity formses where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public void removeByGroupId(long groupId);

	/**
	 * Returns the number of maintenance activity formses where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching maintenance activity formses
	 */
	public int countByGroupId(long groupId);

	/**
	 * Caches the maintenance activity forms in the entity cache if it is enabled.
	 *
	 * @param maintenanceActivityForms the maintenance activity forms
	 */
	public void cacheResult(MaintenanceActivityForms maintenanceActivityForms);

	/**
	 * Caches the maintenance activity formses in the entity cache if it is enabled.
	 *
	 * @param maintenanceActivityFormses the maintenance activity formses
	 */
	public void cacheResult(
		java.util.List<MaintenanceActivityForms> maintenanceActivityFormses);

	/**
	 * Creates a new maintenance activity forms with the primary key. Does not add the maintenance activity forms to the database.
	 *
	 * @param maintenanceactivityformId the primary key for the new maintenance activity forms
	 * @return the new maintenance activity forms
	 */
	public MaintenanceActivityForms create(long maintenanceactivityformId);

	/**
	 * Removes the maintenance activity forms with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param maintenanceactivityformId the primary key of the maintenance activity forms
	 * @return the maintenance activity forms that was removed
	 * @throws NoSuchMaintenanceActivityFormsException if a maintenance activity forms with the primary key could not be found
	 */
	public MaintenanceActivityForms remove(long maintenanceactivityformId)
		throws NoSuchMaintenanceActivityFormsException;

	public MaintenanceActivityForms updateImpl(
		MaintenanceActivityForms maintenanceActivityForms);

	/**
	 * Returns the maintenance activity forms with the primary key or throws a <code>NoSuchMaintenanceActivityFormsException</code> if it could not be found.
	 *
	 * @param maintenanceactivityformId the primary key of the maintenance activity forms
	 * @return the maintenance activity forms
	 * @throws NoSuchMaintenanceActivityFormsException if a maintenance activity forms with the primary key could not be found
	 */
	public MaintenanceActivityForms findByPrimaryKey(
			long maintenanceactivityformId)
		throws NoSuchMaintenanceActivityFormsException;

	/**
	 * Returns the maintenance activity forms with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param maintenanceactivityformId the primary key of the maintenance activity forms
	 * @return the maintenance activity forms, or <code>null</code> if a maintenance activity forms with the primary key could not be found
	 */
	public MaintenanceActivityForms fetchByPrimaryKey(
		long maintenanceactivityformId);

	/**
	 * Returns all the maintenance activity formses.
	 *
	 * @return the maintenance activity formses
	 */
	public java.util.List<MaintenanceActivityForms> findAll();

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
	public java.util.List<MaintenanceActivityForms> findAll(int start, int end);

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
	public java.util.List<MaintenanceActivityForms> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<MaintenanceActivityForms> orderByComparator);

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
	public java.util.List<MaintenanceActivityForms> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<MaintenanceActivityForms> orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the maintenance activity formses from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of maintenance activity formses.
	 *
	 * @return the number of maintenance activity formses
	 */
	public int countAll();

}