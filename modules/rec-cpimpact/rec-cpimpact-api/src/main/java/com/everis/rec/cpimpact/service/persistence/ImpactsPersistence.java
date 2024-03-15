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

import com.everis.rec.cpimpact.exception.NoSuchImpactsException;
import com.everis.rec.cpimpact.model.Impacts;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the impacts service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ImpactsUtil
 * @generated
 */
@ProviderType
public interface ImpactsPersistence extends BasePersistence<Impacts> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ImpactsUtil} to access the impacts persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the impactses where impactCategory = &#63;.
	 *
	 * @param impactCategory the impact category
	 * @return the matching impactses
	 */
	public java.util.List<Impacts> findByImpactCategory(String impactCategory);

	/**
	 * Returns a range of all the impactses where impactCategory = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ImpactsModelImpl</code>.
	 * </p>
	 *
	 * @param impactCategory the impact category
	 * @param start the lower bound of the range of impactses
	 * @param end the upper bound of the range of impactses (not inclusive)
	 * @return the range of matching impactses
	 */
	public java.util.List<Impacts> findByImpactCategory(
		String impactCategory, int start, int end);

	/**
	 * Returns an ordered range of all the impactses where impactCategory = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ImpactsModelImpl</code>.
	 * </p>
	 *
	 * @param impactCategory the impact category
	 * @param start the lower bound of the range of impactses
	 * @param end the upper bound of the range of impactses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching impactses
	 */
	public java.util.List<Impacts> findByImpactCategory(
		String impactCategory, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Impacts>
			orderByComparator);

	/**
	 * Returns an ordered range of all the impactses where impactCategory = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ImpactsModelImpl</code>.
	 * </p>
	 *
	 * @param impactCategory the impact category
	 * @param start the lower bound of the range of impactses
	 * @param end the upper bound of the range of impactses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching impactses
	 */
	public java.util.List<Impacts> findByImpactCategory(
		String impactCategory, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Impacts>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first impacts in the ordered set where impactCategory = &#63;.
	 *
	 * @param impactCategory the impact category
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching impacts
	 * @throws NoSuchImpactsException if a matching impacts could not be found
	 */
	public Impacts findByImpactCategory_First(
			String impactCategory,
			com.liferay.portal.kernel.util.OrderByComparator<Impacts>
				orderByComparator)
		throws NoSuchImpactsException;

	/**
	 * Returns the first impacts in the ordered set where impactCategory = &#63;.
	 *
	 * @param impactCategory the impact category
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching impacts, or <code>null</code> if a matching impacts could not be found
	 */
	public Impacts fetchByImpactCategory_First(
		String impactCategory,
		com.liferay.portal.kernel.util.OrderByComparator<Impacts>
			orderByComparator);

	/**
	 * Returns the last impacts in the ordered set where impactCategory = &#63;.
	 *
	 * @param impactCategory the impact category
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching impacts
	 * @throws NoSuchImpactsException if a matching impacts could not be found
	 */
	public Impacts findByImpactCategory_Last(
			String impactCategory,
			com.liferay.portal.kernel.util.OrderByComparator<Impacts>
				orderByComparator)
		throws NoSuchImpactsException;

	/**
	 * Returns the last impacts in the ordered set where impactCategory = &#63;.
	 *
	 * @param impactCategory the impact category
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching impacts, or <code>null</code> if a matching impacts could not be found
	 */
	public Impacts fetchByImpactCategory_Last(
		String impactCategory,
		com.liferay.portal.kernel.util.OrderByComparator<Impacts>
			orderByComparator);

	/**
	 * Returns the impactses before and after the current impacts in the ordered set where impactCategory = &#63;.
	 *
	 * @param impactId the primary key of the current impacts
	 * @param impactCategory the impact category
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next impacts
	 * @throws NoSuchImpactsException if a impacts with the primary key could not be found
	 */
	public Impacts[] findByImpactCategory_PrevAndNext(
			long impactId, String impactCategory,
			com.liferay.portal.kernel.util.OrderByComparator<Impacts>
				orderByComparator)
		throws NoSuchImpactsException;

	/**
	 * Removes all the impactses where impactCategory = &#63; from the database.
	 *
	 * @param impactCategory the impact category
	 */
	public void removeByImpactCategory(String impactCategory);

	/**
	 * Returns the number of impactses where impactCategory = &#63;.
	 *
	 * @param impactCategory the impact category
	 * @return the number of matching impactses
	 */
	public int countByImpactCategory(String impactCategory);

	/**
	 * Caches the impacts in the entity cache if it is enabled.
	 *
	 * @param impacts the impacts
	 */
	public void cacheResult(Impacts impacts);

	/**
	 * Caches the impactses in the entity cache if it is enabled.
	 *
	 * @param impactses the impactses
	 */
	public void cacheResult(java.util.List<Impacts> impactses);

	/**
	 * Creates a new impacts with the primary key. Does not add the impacts to the database.
	 *
	 * @param impactId the primary key for the new impacts
	 * @return the new impacts
	 */
	public Impacts create(long impactId);

	/**
	 * Removes the impacts with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param impactId the primary key of the impacts
	 * @return the impacts that was removed
	 * @throws NoSuchImpactsException if a impacts with the primary key could not be found
	 */
	public Impacts remove(long impactId) throws NoSuchImpactsException;

	public Impacts updateImpl(Impacts impacts);

	/**
	 * Returns the impacts with the primary key or throws a <code>NoSuchImpactsException</code> if it could not be found.
	 *
	 * @param impactId the primary key of the impacts
	 * @return the impacts
	 * @throws NoSuchImpactsException if a impacts with the primary key could not be found
	 */
	public Impacts findByPrimaryKey(long impactId)
		throws NoSuchImpactsException;

	/**
	 * Returns the impacts with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param impactId the primary key of the impacts
	 * @return the impacts, or <code>null</code> if a impacts with the primary key could not be found
	 */
	public Impacts fetchByPrimaryKey(long impactId);

	/**
	 * Returns all the impactses.
	 *
	 * @return the impactses
	 */
	public java.util.List<Impacts> findAll();

	/**
	 * Returns a range of all the impactses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ImpactsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of impactses
	 * @param end the upper bound of the range of impactses (not inclusive)
	 * @return the range of impactses
	 */
	public java.util.List<Impacts> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the impactses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ImpactsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of impactses
	 * @param end the upper bound of the range of impactses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of impactses
	 */
	public java.util.List<Impacts> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Impacts>
			orderByComparator);

	/**
	 * Returns an ordered range of all the impactses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ImpactsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of impactses
	 * @param end the upper bound of the range of impactses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of impactses
	 */
	public java.util.List<Impacts> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Impacts>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the impactses from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of impactses.
	 *
	 * @return the number of impactses
	 */
	public int countAll();

}