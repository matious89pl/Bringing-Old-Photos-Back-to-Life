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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

import rec.file.conf.service.exception.NoSuchFile_ConfException;
import rec.file.conf.service.model.File_Conf;

/**
 * The persistence interface for the file_ conf service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see File_ConfUtil
 * @generated
 */
@ProviderType
public interface File_ConfPersistence extends BasePersistence<File_Conf> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link File_ConfUtil} to access the file_ conf persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the file_ confs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching file_ confs
	 */
	public java.util.List<File_Conf> findByUuid(String uuid);

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
	public java.util.List<File_Conf> findByUuid(
		String uuid, int start, int end);

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
	public java.util.List<File_Conf> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<File_Conf>
			orderByComparator);

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
	public java.util.List<File_Conf> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<File_Conf>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first file_ conf in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching file_ conf
	 * @throws NoSuchFile_ConfException if a matching file_ conf could not be found
	 */
	public File_Conf findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<File_Conf>
				orderByComparator)
		throws NoSuchFile_ConfException;

	/**
	 * Returns the first file_ conf in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching file_ conf, or <code>null</code> if a matching file_ conf could not be found
	 */
	public File_Conf fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<File_Conf>
			orderByComparator);

	/**
	 * Returns the last file_ conf in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching file_ conf
	 * @throws NoSuchFile_ConfException if a matching file_ conf could not be found
	 */
	public File_Conf findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<File_Conf>
				orderByComparator)
		throws NoSuchFile_ConfException;

	/**
	 * Returns the last file_ conf in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching file_ conf, or <code>null</code> if a matching file_ conf could not be found
	 */
	public File_Conf fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<File_Conf>
			orderByComparator);

	/**
	 * Returns the file_ confs before and after the current file_ conf in the ordered set where uuid = &#63;.
	 *
	 * @param docConfigId the primary key of the current file_ conf
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next file_ conf
	 * @throws NoSuchFile_ConfException if a file_ conf with the primary key could not be found
	 */
	public File_Conf[] findByUuid_PrevAndNext(
			long docConfigId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<File_Conf>
				orderByComparator)
		throws NoSuchFile_ConfException;

	/**
	 * Removes all the file_ confs where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of file_ confs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching file_ confs
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the file_ conf where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchFile_ConfException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching file_ conf
	 * @throws NoSuchFile_ConfException if a matching file_ conf could not be found
	 */
	public File_Conf findByUUID_G(String uuid, long groupId)
		throws NoSuchFile_ConfException;

	/**
	 * Returns the file_ conf where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching file_ conf, or <code>null</code> if a matching file_ conf could not be found
	 */
	public File_Conf fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the file_ conf where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching file_ conf, or <code>null</code> if a matching file_ conf could not be found
	 */
	public File_Conf fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache);

	/**
	 * Removes the file_ conf where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the file_ conf that was removed
	 */
	public File_Conf removeByUUID_G(String uuid, long groupId)
		throws NoSuchFile_ConfException;

	/**
	 * Returns the number of file_ confs where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching file_ confs
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the file_ confs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching file_ confs
	 */
	public java.util.List<File_Conf> findByUuid_C(String uuid, long companyId);

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
	public java.util.List<File_Conf> findByUuid_C(
		String uuid, long companyId, int start, int end);

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
	public java.util.List<File_Conf> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<File_Conf>
			orderByComparator);

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
	public java.util.List<File_Conf> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<File_Conf>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first file_ conf in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching file_ conf
	 * @throws NoSuchFile_ConfException if a matching file_ conf could not be found
	 */
	public File_Conf findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<File_Conf>
				orderByComparator)
		throws NoSuchFile_ConfException;

	/**
	 * Returns the first file_ conf in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching file_ conf, or <code>null</code> if a matching file_ conf could not be found
	 */
	public File_Conf fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<File_Conf>
			orderByComparator);

	/**
	 * Returns the last file_ conf in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching file_ conf
	 * @throws NoSuchFile_ConfException if a matching file_ conf could not be found
	 */
	public File_Conf findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<File_Conf>
				orderByComparator)
		throws NoSuchFile_ConfException;

	/**
	 * Returns the last file_ conf in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching file_ conf, or <code>null</code> if a matching file_ conf could not be found
	 */
	public File_Conf fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<File_Conf>
			orderByComparator);

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
	public File_Conf[] findByUuid_C_PrevAndNext(
			long docConfigId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<File_Conf>
				orderByComparator)
		throws NoSuchFile_ConfException;

	/**
	 * Removes all the file_ confs where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of file_ confs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching file_ confs
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns the file_ conf where docConfName = &#63; or throws a <code>NoSuchFile_ConfException</code> if it could not be found.
	 *
	 * @param docConfName the doc conf name
	 * @return the matching file_ conf
	 * @throws NoSuchFile_ConfException if a matching file_ conf could not be found
	 */
	public File_Conf findBydocConfName(String docConfName)
		throws NoSuchFile_ConfException;

	/**
	 * Returns the file_ conf where docConfName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param docConfName the doc conf name
	 * @return the matching file_ conf, or <code>null</code> if a matching file_ conf could not be found
	 */
	public File_Conf fetchBydocConfName(String docConfName);

	/**
	 * Returns the file_ conf where docConfName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param docConfName the doc conf name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching file_ conf, or <code>null</code> if a matching file_ conf could not be found
	 */
	public File_Conf fetchBydocConfName(
		String docConfName, boolean useFinderCache);

	/**
	 * Removes the file_ conf where docConfName = &#63; from the database.
	 *
	 * @param docConfName the doc conf name
	 * @return the file_ conf that was removed
	 */
	public File_Conf removeBydocConfName(String docConfName)
		throws NoSuchFile_ConfException;

	/**
	 * Returns the number of file_ confs where docConfName = &#63;.
	 *
	 * @param docConfName the doc conf name
	 * @return the number of matching file_ confs
	 */
	public int countBydocConfName(String docConfName);

	/**
	 * Returns the file_ conf where docFileType = &#63; or throws a <code>NoSuchFile_ConfException</code> if it could not be found.
	 *
	 * @param docFileType the doc file type
	 * @return the matching file_ conf
	 * @throws NoSuchFile_ConfException if a matching file_ conf could not be found
	 */
	public File_Conf findBydocFileType(String docFileType)
		throws NoSuchFile_ConfException;

	/**
	 * Returns the file_ conf where docFileType = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param docFileType the doc file type
	 * @return the matching file_ conf, or <code>null</code> if a matching file_ conf could not be found
	 */
	public File_Conf fetchBydocFileType(String docFileType);

	/**
	 * Returns the file_ conf where docFileType = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param docFileType the doc file type
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching file_ conf, or <code>null</code> if a matching file_ conf could not be found
	 */
	public File_Conf fetchBydocFileType(
		String docFileType, boolean useFinderCache);

	/**
	 * Removes the file_ conf where docFileType = &#63; from the database.
	 *
	 * @param docFileType the doc file type
	 * @return the file_ conf that was removed
	 */
	public File_Conf removeBydocFileType(String docFileType)
		throws NoSuchFile_ConfException;

	/**
	 * Returns the number of file_ confs where docFileType = &#63;.
	 *
	 * @param docFileType the doc file type
	 * @return the number of matching file_ confs
	 */
	public int countBydocFileType(String docFileType);

	/**
	 * Caches the file_ conf in the entity cache if it is enabled.
	 *
	 * @param file_Conf the file_ conf
	 */
	public void cacheResult(File_Conf file_Conf);

	/**
	 * Caches the file_ confs in the entity cache if it is enabled.
	 *
	 * @param file_Confs the file_ confs
	 */
	public void cacheResult(java.util.List<File_Conf> file_Confs);

	/**
	 * Creates a new file_ conf with the primary key. Does not add the file_ conf to the database.
	 *
	 * @param docConfigId the primary key for the new file_ conf
	 * @return the new file_ conf
	 */
	public File_Conf create(long docConfigId);

	/**
	 * Removes the file_ conf with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param docConfigId the primary key of the file_ conf
	 * @return the file_ conf that was removed
	 * @throws NoSuchFile_ConfException if a file_ conf with the primary key could not be found
	 */
	public File_Conf remove(long docConfigId) throws NoSuchFile_ConfException;

	public File_Conf updateImpl(File_Conf file_Conf);

	/**
	 * Returns the file_ conf with the primary key or throws a <code>NoSuchFile_ConfException</code> if it could not be found.
	 *
	 * @param docConfigId the primary key of the file_ conf
	 * @return the file_ conf
	 * @throws NoSuchFile_ConfException if a file_ conf with the primary key could not be found
	 */
	public File_Conf findByPrimaryKey(long docConfigId)
		throws NoSuchFile_ConfException;

	/**
	 * Returns the file_ conf with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param docConfigId the primary key of the file_ conf
	 * @return the file_ conf, or <code>null</code> if a file_ conf with the primary key could not be found
	 */
	public File_Conf fetchByPrimaryKey(long docConfigId);

	/**
	 * Returns all the file_ confs.
	 *
	 * @return the file_ confs
	 */
	public java.util.List<File_Conf> findAll();

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
	public java.util.List<File_Conf> findAll(int start, int end);

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
	public java.util.List<File_Conf> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<File_Conf>
			orderByComparator);

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
	public java.util.List<File_Conf> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<File_Conf>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the file_ confs from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of file_ confs.
	 *
	 * @return the number of file_ confs
	 */
	public int countAll();

}