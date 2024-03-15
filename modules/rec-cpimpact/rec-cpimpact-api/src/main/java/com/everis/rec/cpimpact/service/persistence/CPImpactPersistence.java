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

package com.everis.rec.cpimpact.service.persistence;

import com.everis.rec.cpimpact.exception.NoSuchCPImpactException;
import com.everis.rec.cpimpact.model.CPImpact;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the cp impact service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CPImpactUtil
 * @generated
 */
@ProviderType
public interface CPImpactPersistence extends BasePersistence<CPImpact> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CPImpactUtil} to access the cp impact persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the cp impacts where impactCategory = &#63;.
	 *
	 * @param impactCategory the impact category
	 * @return the matching cp impacts
	 */
	public java.util.List<CPImpact> findByImpactCategory(String impactCategory);

	/**
	 * Returns a range of all the cp impacts where impactCategory = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CPImpactModelImpl</code>.
	 * </p>
	 *
	 * @param impactCategory the impact category
	 * @param start the lower bound of the range of cp impacts
	 * @param end the upper bound of the range of cp impacts (not inclusive)
	 * @return the range of matching cp impacts
	 */
	public java.util.List<CPImpact> findByImpactCategory(
		String impactCategory, int start, int end);

	/**
	 * Returns an ordered range of all the cp impacts where impactCategory = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CPImpactModelImpl</code>.
	 * </p>
	 *
	 * @param impactCategory the impact category
	 * @param start the lower bound of the range of cp impacts
	 * @param end the upper bound of the range of cp impacts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cp impacts
	 */
	public java.util.List<CPImpact> findByImpactCategory(
		String impactCategory, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPImpact>
			orderByComparator);

	/**
	 * Returns an ordered range of all the cp impacts where impactCategory = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CPImpactModelImpl</code>.
	 * </p>
	 *
	 * @param impactCategory the impact category
	 * @param start the lower bound of the range of cp impacts
	 * @param end the upper bound of the range of cp impacts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching cp impacts
	 */
	public java.util.List<CPImpact> findByImpactCategory(
		String impactCategory, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPImpact>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first cp impact in the ordered set where impactCategory = &#63;.
	 *
	 * @param impactCategory the impact category
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp impact
	 * @throws NoSuchCPImpactException if a matching cp impact could not be found
	 */
	public CPImpact findByImpactCategory_First(
			String impactCategory,
			com.liferay.portal.kernel.util.OrderByComparator<CPImpact>
				orderByComparator)
		throws NoSuchCPImpactException;

	/**
	 * Returns the first cp impact in the ordered set where impactCategory = &#63;.
	 *
	 * @param impactCategory the impact category
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cp impact, or <code>null</code> if a matching cp impact could not be found
	 */
	public CPImpact fetchByImpactCategory_First(
		String impactCategory,
		com.liferay.portal.kernel.util.OrderByComparator<CPImpact>
			orderByComparator);

	/**
	 * Returns the last cp impact in the ordered set where impactCategory = &#63;.
	 *
	 * @param impactCategory the impact category
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp impact
	 * @throws NoSuchCPImpactException if a matching cp impact could not be found
	 */
	public CPImpact findByImpactCategory_Last(
			String impactCategory,
			com.liferay.portal.kernel.util.OrderByComparator<CPImpact>
				orderByComparator)
		throws NoSuchCPImpactException;

	/**
	 * Returns the last cp impact in the ordered set where impactCategory = &#63;.
	 *
	 * @param impactCategory the impact category
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cp impact, or <code>null</code> if a matching cp impact could not be found
	 */
	public CPImpact fetchByImpactCategory_Last(
		String impactCategory,
		com.liferay.portal.kernel.util.OrderByComparator<CPImpact>
			orderByComparator);

	/**
	 * Returns the cp impacts before and after the current cp impact in the ordered set where impactCategory = &#63;.
	 *
	 * @param impactId the primary key of the current cp impact
	 * @param impactCategory the impact category
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cp impact
	 * @throws NoSuchCPImpactException if a cp impact with the primary key could not be found
	 */
	public CPImpact[] findByImpactCategory_PrevAndNext(
			long impactId, String impactCategory,
			com.liferay.portal.kernel.util.OrderByComparator<CPImpact>
				orderByComparator)
		throws NoSuchCPImpactException;

	/**
	 * Removes all the cp impacts where impactCategory = &#63; from the database.
	 *
	 * @param impactCategory the impact category
	 */
	public void removeByImpactCategory(String impactCategory);

	/**
	 * Returns the number of cp impacts where impactCategory = &#63;.
	 *
	 * @param impactCategory the impact category
	 * @return the number of matching cp impacts
	 */
	public int countByImpactCategory(String impactCategory);

	/**
	 * Caches the cp impact in the entity cache if it is enabled.
	 *
	 * @param cpImpact the cp impact
	 */
	public void cacheResult(CPImpact cpImpact);

	/**
	 * Caches the cp impacts in the entity cache if it is enabled.
	 *
	 * @param cpImpacts the cp impacts
	 */
	public void cacheResult(java.util.List<CPImpact> cpImpacts);

	/**
	 * Creates a new cp impact with the primary key. Does not add the cp impact to the database.
	 *
	 * @param impactId the primary key for the new cp impact
	 * @return the new cp impact
	 */
	public CPImpact create(long impactId);

	/**
	 * Removes the cp impact with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param impactId the primary key of the cp impact
	 * @return the cp impact that was removed
	 * @throws NoSuchCPImpactException if a cp impact with the primary key could not be found
	 */
	public CPImpact remove(long impactId) throws NoSuchCPImpactException;

	public CPImpact updateImpl(CPImpact cpImpact);

	/**
	 * Returns the cp impact with the primary key or throws a <code>NoSuchCPImpactException</code> if it could not be found.
	 *
	 * @param impactId the primary key of the cp impact
	 * @return the cp impact
	 * @throws NoSuchCPImpactException if a cp impact with the primary key could not be found
	 */
	public CPImpact findByPrimaryKey(long impactId)
		throws NoSuchCPImpactException;

	/**
	 * Returns the cp impact with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param impactId the primary key of the cp impact
	 * @return the cp impact, or <code>null</code> if a cp impact with the primary key could not be found
	 */
	public CPImpact fetchByPrimaryKey(long impactId);

	/**
	 * Returns all the cp impacts.
	 *
	 * @return the cp impacts
	 */
	public java.util.List<CPImpact> findAll();

	/**
	 * Returns a range of all the cp impacts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CPImpactModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp impacts
	 * @param end the upper bound of the range of cp impacts (not inclusive)
	 * @return the range of cp impacts
	 */
	public java.util.List<CPImpact> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the cp impacts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CPImpactModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp impacts
	 * @param end the upper bound of the range of cp impacts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cp impacts
	 */
	public java.util.List<CPImpact> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPImpact>
			orderByComparator);

	/**
	 * Returns an ordered range of all the cp impacts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CPImpactModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp impacts
	 * @param end the upper bound of the range of cp impacts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of cp impacts
	 */
	public java.util.List<CPImpact> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CPImpact>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the cp impacts from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of cp impacts.
	 *
	 * @return the number of cp impacts
	 */
	public int countAll();

}