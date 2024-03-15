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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

import rec.everis.forms.service.model.MaintenanceActivityForms;

/**
 * The persistence utility for the maintenance activity forms service. This utility wraps <code>rec.everis.forms.service.service.persistence.impl.MaintenanceActivityFormsPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MaintenanceActivityFormsPersistence
 * @generated
 */
public class MaintenanceActivityFormsUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(
		MaintenanceActivityForms maintenanceActivityForms) {

		getPersistence().clearCache(maintenanceActivityForms);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, MaintenanceActivityForms>
		fetchByPrimaryKeys(Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<MaintenanceActivityForms> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<MaintenanceActivityForms> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<MaintenanceActivityForms> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<MaintenanceActivityForms> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static MaintenanceActivityForms update(
		MaintenanceActivityForms maintenanceActivityForms) {

		return getPersistence().update(maintenanceActivityForms);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static MaintenanceActivityForms update(
		MaintenanceActivityForms maintenanceActivityForms,
		ServiceContext serviceContext) {

		return getPersistence().update(
			maintenanceActivityForms, serviceContext);
	}

	/**
	 * Returns all the maintenance activity formses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching maintenance activity formses
	 */
	public static List<MaintenanceActivityForms> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
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
	public static List<MaintenanceActivityForms> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
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
	public static List<MaintenanceActivityForms> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<MaintenanceActivityForms> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
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
	public static List<MaintenanceActivityForms> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<MaintenanceActivityForms> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first maintenance activity forms in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching maintenance activity forms
	 * @throws NoSuchMaintenanceActivityFormsException if a matching maintenance activity forms could not be found
	 */
	public static MaintenanceActivityForms findByUuid_First(
			String uuid,
			OrderByComparator<MaintenanceActivityForms> orderByComparator)
		throws rec.everis.forms.service.exception.
			NoSuchMaintenanceActivityFormsException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first maintenance activity forms in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching maintenance activity forms, or <code>null</code> if a matching maintenance activity forms could not be found
	 */
	public static MaintenanceActivityForms fetchByUuid_First(
		String uuid,
		OrderByComparator<MaintenanceActivityForms> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last maintenance activity forms in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching maintenance activity forms
	 * @throws NoSuchMaintenanceActivityFormsException if a matching maintenance activity forms could not be found
	 */
	public static MaintenanceActivityForms findByUuid_Last(
			String uuid,
			OrderByComparator<MaintenanceActivityForms> orderByComparator)
		throws rec.everis.forms.service.exception.
			NoSuchMaintenanceActivityFormsException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last maintenance activity forms in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching maintenance activity forms, or <code>null</code> if a matching maintenance activity forms could not be found
	 */
	public static MaintenanceActivityForms fetchByUuid_Last(
		String uuid,
		OrderByComparator<MaintenanceActivityForms> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
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
	public static MaintenanceActivityForms[] findByUuid_PrevAndNext(
			long maintenanceactivityformId, String uuid,
			OrderByComparator<MaintenanceActivityForms> orderByComparator)
		throws rec.everis.forms.service.exception.
			NoSuchMaintenanceActivityFormsException {

		return getPersistence().findByUuid_PrevAndNext(
			maintenanceactivityformId, uuid, orderByComparator);
	}

	/**
	 * Removes all the maintenance activity formses where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of maintenance activity formses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching maintenance activity formses
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the maintenance activity forms where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchMaintenanceActivityFormsException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching maintenance activity forms
	 * @throws NoSuchMaintenanceActivityFormsException if a matching maintenance activity forms could not be found
	 */
	public static MaintenanceActivityForms findByUUID_G(
			String uuid, long groupId)
		throws rec.everis.forms.service.exception.
			NoSuchMaintenanceActivityFormsException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the maintenance activity forms where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching maintenance activity forms, or <code>null</code> if a matching maintenance activity forms could not be found
	 */
	public static MaintenanceActivityForms fetchByUUID_G(
		String uuid, long groupId) {

		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the maintenance activity forms where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching maintenance activity forms, or <code>null</code> if a matching maintenance activity forms could not be found
	 */
	public static MaintenanceActivityForms fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, useFinderCache);
	}

	/**
	 * Removes the maintenance activity forms where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the maintenance activity forms that was removed
	 */
	public static MaintenanceActivityForms removeByUUID_G(
			String uuid, long groupId)
		throws rec.everis.forms.service.exception.
			NoSuchMaintenanceActivityFormsException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of maintenance activity formses where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching maintenance activity formses
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the maintenance activity formses where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching maintenance activity formses
	 */
	public static List<MaintenanceActivityForms> findByUuid_C(
		String uuid, long companyId) {

		return getPersistence().findByUuid_C(uuid, companyId);
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
	public static List<MaintenanceActivityForms> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
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
	public static List<MaintenanceActivityForms> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<MaintenanceActivityForms> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
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
	public static List<MaintenanceActivityForms> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<MaintenanceActivityForms> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
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
	public static MaintenanceActivityForms findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<MaintenanceActivityForms> orderByComparator)
		throws rec.everis.forms.service.exception.
			NoSuchMaintenanceActivityFormsException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first maintenance activity forms in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching maintenance activity forms, or <code>null</code> if a matching maintenance activity forms could not be found
	 */
	public static MaintenanceActivityForms fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<MaintenanceActivityForms> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
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
	public static MaintenanceActivityForms findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<MaintenanceActivityForms> orderByComparator)
		throws rec.everis.forms.service.exception.
			NoSuchMaintenanceActivityFormsException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last maintenance activity forms in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching maintenance activity forms, or <code>null</code> if a matching maintenance activity forms could not be found
	 */
	public static MaintenanceActivityForms fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<MaintenanceActivityForms> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
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
	public static MaintenanceActivityForms[] findByUuid_C_PrevAndNext(
			long maintenanceactivityformId, String uuid, long companyId,
			OrderByComparator<MaintenanceActivityForms> orderByComparator)
		throws rec.everis.forms.service.exception.
			NoSuchMaintenanceActivityFormsException {

		return getPersistence().findByUuid_C_PrevAndNext(
			maintenanceactivityformId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the maintenance activity formses where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of maintenance activity formses where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching maintenance activity formses
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns all the maintenance activity formses where formInstanceRecordVersionId = &#63;.
	 *
	 * @param formInstanceRecordVersionId the form instance record version ID
	 * @return the matching maintenance activity formses
	 */
	public static List<MaintenanceActivityForms>
		findByformInstanceRecordVersionId(long formInstanceRecordVersionId) {

		return getPersistence().findByformInstanceRecordVersionId(
			formInstanceRecordVersionId);
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
	public static List<MaintenanceActivityForms>
		findByformInstanceRecordVersionId(
			long formInstanceRecordVersionId, int start, int end) {

		return getPersistence().findByformInstanceRecordVersionId(
			formInstanceRecordVersionId, start, end);
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
	public static List<MaintenanceActivityForms>
		findByformInstanceRecordVersionId(
			long formInstanceRecordVersionId, int start, int end,
			OrderByComparator<MaintenanceActivityForms> orderByComparator) {

		return getPersistence().findByformInstanceRecordVersionId(
			formInstanceRecordVersionId, start, end, orderByComparator);
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
	public static List<MaintenanceActivityForms>
		findByformInstanceRecordVersionId(
			long formInstanceRecordVersionId, int start, int end,
			OrderByComparator<MaintenanceActivityForms> orderByComparator,
			boolean useFinderCache) {

		return getPersistence().findByformInstanceRecordVersionId(
			formInstanceRecordVersionId, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first maintenance activity forms in the ordered set where formInstanceRecordVersionId = &#63;.
	 *
	 * @param formInstanceRecordVersionId the form instance record version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching maintenance activity forms
	 * @throws NoSuchMaintenanceActivityFormsException if a matching maintenance activity forms could not be found
	 */
	public static MaintenanceActivityForms
			findByformInstanceRecordVersionId_First(
				long formInstanceRecordVersionId,
				OrderByComparator<MaintenanceActivityForms> orderByComparator)
		throws rec.everis.forms.service.exception.
			NoSuchMaintenanceActivityFormsException {

		return getPersistence().findByformInstanceRecordVersionId_First(
			formInstanceRecordVersionId, orderByComparator);
	}

	/**
	 * Returns the first maintenance activity forms in the ordered set where formInstanceRecordVersionId = &#63;.
	 *
	 * @param formInstanceRecordVersionId the form instance record version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching maintenance activity forms, or <code>null</code> if a matching maintenance activity forms could not be found
	 */
	public static MaintenanceActivityForms
		fetchByformInstanceRecordVersionId_First(
			long formInstanceRecordVersionId,
			OrderByComparator<MaintenanceActivityForms> orderByComparator) {

		return getPersistence().fetchByformInstanceRecordVersionId_First(
			formInstanceRecordVersionId, orderByComparator);
	}

	/**
	 * Returns the last maintenance activity forms in the ordered set where formInstanceRecordVersionId = &#63;.
	 *
	 * @param formInstanceRecordVersionId the form instance record version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching maintenance activity forms
	 * @throws NoSuchMaintenanceActivityFormsException if a matching maintenance activity forms could not be found
	 */
	public static MaintenanceActivityForms
			findByformInstanceRecordVersionId_Last(
				long formInstanceRecordVersionId,
				OrderByComparator<MaintenanceActivityForms> orderByComparator)
		throws rec.everis.forms.service.exception.
			NoSuchMaintenanceActivityFormsException {

		return getPersistence().findByformInstanceRecordVersionId_Last(
			formInstanceRecordVersionId, orderByComparator);
	}

	/**
	 * Returns the last maintenance activity forms in the ordered set where formInstanceRecordVersionId = &#63;.
	 *
	 * @param formInstanceRecordVersionId the form instance record version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching maintenance activity forms, or <code>null</code> if a matching maintenance activity forms could not be found
	 */
	public static MaintenanceActivityForms
		fetchByformInstanceRecordVersionId_Last(
			long formInstanceRecordVersionId,
			OrderByComparator<MaintenanceActivityForms> orderByComparator) {

		return getPersistence().fetchByformInstanceRecordVersionId_Last(
			formInstanceRecordVersionId, orderByComparator);
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
	public static MaintenanceActivityForms[]
			findByformInstanceRecordVersionId_PrevAndNext(
				long maintenanceactivityformId,
				long formInstanceRecordVersionId,
				OrderByComparator<MaintenanceActivityForms> orderByComparator)
		throws rec.everis.forms.service.exception.
			NoSuchMaintenanceActivityFormsException {

		return getPersistence().findByformInstanceRecordVersionId_PrevAndNext(
			maintenanceactivityformId, formInstanceRecordVersionId,
			orderByComparator);
	}

	/**
	 * Removes all the maintenance activity formses where formInstanceRecordVersionId = &#63; from the database.
	 *
	 * @param formInstanceRecordVersionId the form instance record version ID
	 */
	public static void removeByformInstanceRecordVersionId(
		long formInstanceRecordVersionId) {

		getPersistence().removeByformInstanceRecordVersionId(
			formInstanceRecordVersionId);
	}

	/**
	 * Returns the number of maintenance activity formses where formInstanceRecordVersionId = &#63;.
	 *
	 * @param formInstanceRecordVersionId the form instance record version ID
	 * @return the number of matching maintenance activity formses
	 */
	public static int countByformInstanceRecordVersionId(
		long formInstanceRecordVersionId) {

		return getPersistence().countByformInstanceRecordVersionId(
			formInstanceRecordVersionId);
	}

	/**
	 * Returns all the maintenance activity formses where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching maintenance activity formses
	 */
	public static List<MaintenanceActivityForms> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
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
	public static List<MaintenanceActivityForms> findByGroupId(
		long groupId, int start, int end) {

		return getPersistence().findByGroupId(groupId, start, end);
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
	public static List<MaintenanceActivityForms> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<MaintenanceActivityForms> orderByComparator) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator);
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
	public static List<MaintenanceActivityForms> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<MaintenanceActivityForms> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first maintenance activity forms in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching maintenance activity forms
	 * @throws NoSuchMaintenanceActivityFormsException if a matching maintenance activity forms could not be found
	 */
	public static MaintenanceActivityForms findByGroupId_First(
			long groupId,
			OrderByComparator<MaintenanceActivityForms> orderByComparator)
		throws rec.everis.forms.service.exception.
			NoSuchMaintenanceActivityFormsException {

		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	 * Returns the first maintenance activity forms in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching maintenance activity forms, or <code>null</code> if a matching maintenance activity forms could not be found
	 */
	public static MaintenanceActivityForms fetchByGroupId_First(
		long groupId,
		OrderByComparator<MaintenanceActivityForms> orderByComparator) {

		return getPersistence().fetchByGroupId_First(
			groupId, orderByComparator);
	}

	/**
	 * Returns the last maintenance activity forms in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching maintenance activity forms
	 * @throws NoSuchMaintenanceActivityFormsException if a matching maintenance activity forms could not be found
	 */
	public static MaintenanceActivityForms findByGroupId_Last(
			long groupId,
			OrderByComparator<MaintenanceActivityForms> orderByComparator)
		throws rec.everis.forms.service.exception.
			NoSuchMaintenanceActivityFormsException {

		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the last maintenance activity forms in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching maintenance activity forms, or <code>null</code> if a matching maintenance activity forms could not be found
	 */
	public static MaintenanceActivityForms fetchByGroupId_Last(
		long groupId,
		OrderByComparator<MaintenanceActivityForms> orderByComparator) {

		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
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
	public static MaintenanceActivityForms[] findByGroupId_PrevAndNext(
			long maintenanceactivityformId, long groupId,
			OrderByComparator<MaintenanceActivityForms> orderByComparator)
		throws rec.everis.forms.service.exception.
			NoSuchMaintenanceActivityFormsException {

		return getPersistence().findByGroupId_PrevAndNext(
			maintenanceactivityformId, groupId, orderByComparator);
	}

	/**
	 * Removes all the maintenance activity formses where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	 * Returns the number of maintenance activity formses where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching maintenance activity formses
	 */
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	 * Caches the maintenance activity forms in the entity cache if it is enabled.
	 *
	 * @param maintenanceActivityForms the maintenance activity forms
	 */
	public static void cacheResult(
		MaintenanceActivityForms maintenanceActivityForms) {

		getPersistence().cacheResult(maintenanceActivityForms);
	}

	/**
	 * Caches the maintenance activity formses in the entity cache if it is enabled.
	 *
	 * @param maintenanceActivityFormses the maintenance activity formses
	 */
	public static void cacheResult(
		List<MaintenanceActivityForms> maintenanceActivityFormses) {

		getPersistence().cacheResult(maintenanceActivityFormses);
	}

	/**
	 * Creates a new maintenance activity forms with the primary key. Does not add the maintenance activity forms to the database.
	 *
	 * @param maintenanceactivityformId the primary key for the new maintenance activity forms
	 * @return the new maintenance activity forms
	 */
	public static MaintenanceActivityForms create(
		long maintenanceactivityformId) {

		return getPersistence().create(maintenanceactivityformId);
	}

	/**
	 * Removes the maintenance activity forms with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param maintenanceactivityformId the primary key of the maintenance activity forms
	 * @return the maintenance activity forms that was removed
	 * @throws NoSuchMaintenanceActivityFormsException if a maintenance activity forms with the primary key could not be found
	 */
	public static MaintenanceActivityForms remove(
			long maintenanceactivityformId)
		throws rec.everis.forms.service.exception.
			NoSuchMaintenanceActivityFormsException {

		return getPersistence().remove(maintenanceactivityformId);
	}

	public static MaintenanceActivityForms updateImpl(
		MaintenanceActivityForms maintenanceActivityForms) {

		return getPersistence().updateImpl(maintenanceActivityForms);
	}

	/**
	 * Returns the maintenance activity forms with the primary key or throws a <code>NoSuchMaintenanceActivityFormsException</code> if it could not be found.
	 *
	 * @param maintenanceactivityformId the primary key of the maintenance activity forms
	 * @return the maintenance activity forms
	 * @throws NoSuchMaintenanceActivityFormsException if a maintenance activity forms with the primary key could not be found
	 */
	public static MaintenanceActivityForms findByPrimaryKey(
			long maintenanceactivityformId)
		throws rec.everis.forms.service.exception.
			NoSuchMaintenanceActivityFormsException {

		return getPersistence().findByPrimaryKey(maintenanceactivityformId);
	}

	/**
	 * Returns the maintenance activity forms with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param maintenanceactivityformId the primary key of the maintenance activity forms
	 * @return the maintenance activity forms, or <code>null</code> if a maintenance activity forms with the primary key could not be found
	 */
	public static MaintenanceActivityForms fetchByPrimaryKey(
		long maintenanceactivityformId) {

		return getPersistence().fetchByPrimaryKey(maintenanceactivityformId);
	}

	/**
	 * Returns all the maintenance activity formses.
	 *
	 * @return the maintenance activity formses
	 */
	public static List<MaintenanceActivityForms> findAll() {
		return getPersistence().findAll();
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
	public static List<MaintenanceActivityForms> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
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
	public static List<MaintenanceActivityForms> findAll(
		int start, int end,
		OrderByComparator<MaintenanceActivityForms> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
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
	public static List<MaintenanceActivityForms> findAll(
		int start, int end,
		OrderByComparator<MaintenanceActivityForms> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the maintenance activity formses from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of maintenance activity formses.
	 *
	 * @return the number of maintenance activity formses
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static MaintenanceActivityFormsPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<MaintenanceActivityFormsPersistence,
		 MaintenanceActivityFormsPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			MaintenanceActivityFormsPersistence.class);

		ServiceTracker
			<MaintenanceActivityFormsPersistence,
			 MaintenanceActivityFormsPersistence> serviceTracker =
				new ServiceTracker
					<MaintenanceActivityFormsPersistence,
					 MaintenanceActivityFormsPersistence>(
						 bundle.getBundleContext(),
						 MaintenanceActivityFormsPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}