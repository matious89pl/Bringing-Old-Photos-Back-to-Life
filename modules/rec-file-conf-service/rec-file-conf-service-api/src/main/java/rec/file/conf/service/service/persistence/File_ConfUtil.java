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

package rec.file.conf.service.service.persistence;

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

import rec.file.conf.service.model.File_Conf;

/**
 * The persistence utility for the file_ conf service. This utility wraps <code>rec.file.conf.service.service.persistence.impl.File_ConfPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see File_ConfPersistence
 * @generated
 */
public class File_ConfUtil {

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
	public static void clearCache(File_Conf file_Conf) {
		getPersistence().clearCache(file_Conf);
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
	public static Map<Serializable, File_Conf> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<File_Conf> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<File_Conf> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<File_Conf> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<File_Conf> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static File_Conf update(File_Conf file_Conf) {
		return getPersistence().update(file_Conf);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static File_Conf update(
		File_Conf file_Conf, ServiceContext serviceContext) {

		return getPersistence().update(file_Conf, serviceContext);
	}

	/**
	 * Returns all the file_ confs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching file_ confs
	 */
	public static List<File_Conf> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
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
	public static List<File_Conf> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
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
	public static List<File_Conf> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<File_Conf> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
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
	public static List<File_Conf> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<File_Conf> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first file_ conf in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching file_ conf
	 * @throws NoSuchFile_ConfException if a matching file_ conf could not be found
	 */
	public static File_Conf findByUuid_First(
			String uuid, OrderByComparator<File_Conf> orderByComparator)
		throws rec.file.conf.service.exception.NoSuchFile_ConfException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first file_ conf in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching file_ conf, or <code>null</code> if a matching file_ conf could not be found
	 */
	public static File_Conf fetchByUuid_First(
		String uuid, OrderByComparator<File_Conf> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last file_ conf in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching file_ conf
	 * @throws NoSuchFile_ConfException if a matching file_ conf could not be found
	 */
	public static File_Conf findByUuid_Last(
			String uuid, OrderByComparator<File_Conf> orderByComparator)
		throws rec.file.conf.service.exception.NoSuchFile_ConfException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last file_ conf in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching file_ conf, or <code>null</code> if a matching file_ conf could not be found
	 */
	public static File_Conf fetchByUuid_Last(
		String uuid, OrderByComparator<File_Conf> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
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
	public static File_Conf[] findByUuid_PrevAndNext(
			long docConfigId, String uuid,
			OrderByComparator<File_Conf> orderByComparator)
		throws rec.file.conf.service.exception.NoSuchFile_ConfException {

		return getPersistence().findByUuid_PrevAndNext(
			docConfigId, uuid, orderByComparator);
	}

	/**
	 * Removes all the file_ confs where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of file_ confs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching file_ confs
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the file_ conf where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchFile_ConfException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching file_ conf
	 * @throws NoSuchFile_ConfException if a matching file_ conf could not be found
	 */
	public static File_Conf findByUUID_G(String uuid, long groupId)
		throws rec.file.conf.service.exception.NoSuchFile_ConfException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the file_ conf where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching file_ conf, or <code>null</code> if a matching file_ conf could not be found
	 */
	public static File_Conf fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the file_ conf where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching file_ conf, or <code>null</code> if a matching file_ conf could not be found
	 */
	public static File_Conf fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, useFinderCache);
	}

	/**
	 * Removes the file_ conf where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the file_ conf that was removed
	 */
	public static File_Conf removeByUUID_G(String uuid, long groupId)
		throws rec.file.conf.service.exception.NoSuchFile_ConfException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of file_ confs where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching file_ confs
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the file_ confs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching file_ confs
	 */
	public static List<File_Conf> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
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
	public static List<File_Conf> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
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
	public static List<File_Conf> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<File_Conf> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
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
	public static List<File_Conf> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<File_Conf> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
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
	public static File_Conf findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<File_Conf> orderByComparator)
		throws rec.file.conf.service.exception.NoSuchFile_ConfException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first file_ conf in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching file_ conf, or <code>null</code> if a matching file_ conf could not be found
	 */
	public static File_Conf fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<File_Conf> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
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
	public static File_Conf findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<File_Conf> orderByComparator)
		throws rec.file.conf.service.exception.NoSuchFile_ConfException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last file_ conf in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching file_ conf, or <code>null</code> if a matching file_ conf could not be found
	 */
	public static File_Conf fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<File_Conf> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
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
	public static File_Conf[] findByUuid_C_PrevAndNext(
			long docConfigId, String uuid, long companyId,
			OrderByComparator<File_Conf> orderByComparator)
		throws rec.file.conf.service.exception.NoSuchFile_ConfException {

		return getPersistence().findByUuid_C_PrevAndNext(
			docConfigId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the file_ confs where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of file_ confs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching file_ confs
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the file_ conf where docConfName = &#63; or throws a <code>NoSuchFile_ConfException</code> if it could not be found.
	 *
	 * @param docConfName the doc conf name
	 * @return the matching file_ conf
	 * @throws NoSuchFile_ConfException if a matching file_ conf could not be found
	 */
	public static File_Conf findBydocConfName(String docConfName)
		throws rec.file.conf.service.exception.NoSuchFile_ConfException {

		return getPersistence().findBydocConfName(docConfName);
	}

	/**
	 * Returns the file_ conf where docConfName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param docConfName the doc conf name
	 * @return the matching file_ conf, or <code>null</code> if a matching file_ conf could not be found
	 */
	public static File_Conf fetchBydocConfName(String docConfName) {
		return getPersistence().fetchBydocConfName(docConfName);
	}

	/**
	 * Returns the file_ conf where docConfName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param docConfName the doc conf name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching file_ conf, or <code>null</code> if a matching file_ conf could not be found
	 */
	public static File_Conf fetchBydocConfName(
		String docConfName, boolean useFinderCache) {

		return getPersistence().fetchBydocConfName(docConfName, useFinderCache);
	}

	/**
	 * Removes the file_ conf where docConfName = &#63; from the database.
	 *
	 * @param docConfName the doc conf name
	 * @return the file_ conf that was removed
	 */
	public static File_Conf removeBydocConfName(String docConfName)
		throws rec.file.conf.service.exception.NoSuchFile_ConfException {

		return getPersistence().removeBydocConfName(docConfName);
	}

	/**
	 * Returns the number of file_ confs where docConfName = &#63;.
	 *
	 * @param docConfName the doc conf name
	 * @return the number of matching file_ confs
	 */
	public static int countBydocConfName(String docConfName) {
		return getPersistence().countBydocConfName(docConfName);
	}

	/**
	 * Returns the file_ conf where docFileType = &#63; or throws a <code>NoSuchFile_ConfException</code> if it could not be found.
	 *
	 * @param docFileType the doc file type
	 * @return the matching file_ conf
	 * @throws NoSuchFile_ConfException if a matching file_ conf could not be found
	 */
	public static File_Conf findBydocFileType(String docFileType)
		throws rec.file.conf.service.exception.NoSuchFile_ConfException {

		return getPersistence().findBydocFileType(docFileType);
	}

	/**
	 * Returns the file_ conf where docFileType = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param docFileType the doc file type
	 * @return the matching file_ conf, or <code>null</code> if a matching file_ conf could not be found
	 */
	public static File_Conf fetchBydocFileType(String docFileType) {
		return getPersistence().fetchBydocFileType(docFileType);
	}

	/**
	 * Returns the file_ conf where docFileType = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param docFileType the doc file type
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching file_ conf, or <code>null</code> if a matching file_ conf could not be found
	 */
	public static File_Conf fetchBydocFileType(
		String docFileType, boolean useFinderCache) {

		return getPersistence().fetchBydocFileType(docFileType, useFinderCache);
	}

	/**
	 * Removes the file_ conf where docFileType = &#63; from the database.
	 *
	 * @param docFileType the doc file type
	 * @return the file_ conf that was removed
	 */
	public static File_Conf removeBydocFileType(String docFileType)
		throws rec.file.conf.service.exception.NoSuchFile_ConfException {

		return getPersistence().removeBydocFileType(docFileType);
	}

	/**
	 * Returns the number of file_ confs where docFileType = &#63;.
	 *
	 * @param docFileType the doc file type
	 * @return the number of matching file_ confs
	 */
	public static int countBydocFileType(String docFileType) {
		return getPersistence().countBydocFileType(docFileType);
	}

	/**
	 * Caches the file_ conf in the entity cache if it is enabled.
	 *
	 * @param file_Conf the file_ conf
	 */
	public static void cacheResult(File_Conf file_Conf) {
		getPersistence().cacheResult(file_Conf);
	}

	/**
	 * Caches the file_ confs in the entity cache if it is enabled.
	 *
	 * @param file_Confs the file_ confs
	 */
	public static void cacheResult(List<File_Conf> file_Confs) {
		getPersistence().cacheResult(file_Confs);
	}

	/**
	 * Creates a new file_ conf with the primary key. Does not add the file_ conf to the database.
	 *
	 * @param docConfigId the primary key for the new file_ conf
	 * @return the new file_ conf
	 */
	public static File_Conf create(long docConfigId) {
		return getPersistence().create(docConfigId);
	}

	/**
	 * Removes the file_ conf with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param docConfigId the primary key of the file_ conf
	 * @return the file_ conf that was removed
	 * @throws NoSuchFile_ConfException if a file_ conf with the primary key could not be found
	 */
	public static File_Conf remove(long docConfigId)
		throws rec.file.conf.service.exception.NoSuchFile_ConfException {

		return getPersistence().remove(docConfigId);
	}

	public static File_Conf updateImpl(File_Conf file_Conf) {
		return getPersistence().updateImpl(file_Conf);
	}

	/**
	 * Returns the file_ conf with the primary key or throws a <code>NoSuchFile_ConfException</code> if it could not be found.
	 *
	 * @param docConfigId the primary key of the file_ conf
	 * @return the file_ conf
	 * @throws NoSuchFile_ConfException if a file_ conf with the primary key could not be found
	 */
	public static File_Conf findByPrimaryKey(long docConfigId)
		throws rec.file.conf.service.exception.NoSuchFile_ConfException {

		return getPersistence().findByPrimaryKey(docConfigId);
	}

	/**
	 * Returns the file_ conf with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param docConfigId the primary key of the file_ conf
	 * @return the file_ conf, or <code>null</code> if a file_ conf with the primary key could not be found
	 */
	public static File_Conf fetchByPrimaryKey(long docConfigId) {
		return getPersistence().fetchByPrimaryKey(docConfigId);
	}

	/**
	 * Returns all the file_ confs.
	 *
	 * @return the file_ confs
	 */
	public static List<File_Conf> findAll() {
		return getPersistence().findAll();
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
	public static List<File_Conf> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
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
	public static List<File_Conf> findAll(
		int start, int end, OrderByComparator<File_Conf> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
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
	public static List<File_Conf> findAll(
		int start, int end, OrderByComparator<File_Conf> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the file_ confs from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of file_ confs.
	 *
	 * @return the number of file_ confs
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static File_ConfPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<File_ConfPersistence, File_ConfPersistence>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(File_ConfPersistence.class);

		ServiceTracker<File_ConfPersistence, File_ConfPersistence>
			serviceTracker =
				new ServiceTracker<File_ConfPersistence, File_ConfPersistence>(
					bundle.getBundleContext(), File_ConfPersistence.class,
					null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}