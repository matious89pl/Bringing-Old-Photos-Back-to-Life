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

package com.everis.cproposal.service;

import com.everis.cproposal.model.recFormArticle;

import com.liferay.calendar.model.Calendar;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.xml.DocumentException;

import java.io.Serializable;

import java.text.ParseException;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the local service interface for recFormArticle. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see recFormArticleLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface recFormArticleLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.everis.cproposal.service.impl.recFormArticleLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the rec form article local service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link recFormArticleLocalServiceUtil} if injection and service tracking are not available.
	 */

	/**
	 * Adds the rec form article to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect recFormArticleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param recFormArticle the rec form article
	 * @return the rec form article that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public recFormArticle addrecFormArticle(recFormArticle recFormArticle);

	public long createCProposal(long classPK) throws DocumentException;

	/**
	 * @throws PortalException
	 */
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	 * Creates a new rec form article with the primary key. Does not add the rec form article to the database.
	 *
	 * @param formId the primary key for the new rec form article
	 * @return the new rec form article
	 */
	@Transactional(enabled = false)
	public recFormArticle createrecFormArticle(long formId);

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	/**
	 * Deletes the rec form article with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect recFormArticleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param formId the primary key of the rec form article
	 * @return the rec form article that was removed
	 * @throws PortalException if a rec form article with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public recFormArticle deleterecFormArticle(long formId)
		throws PortalException;

	/**
	 * Deletes the rec form article from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect recFormArticleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param recFormArticle the rec form article
	 * @return the rec form article that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	public recFormArticle deleterecFormArticle(recFormArticle recFormArticle);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DynamicQuery dynamicQuery();

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.everis.cproposal.model.impl.recFormArticleModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end);

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.everis.cproposal.model.impl.recFormArticleModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public recFormArticle fetchrecFormArticle(long formId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<JournalArticle> getActionLogFiltered(
			Date startDate, Date endDate, String status, Date startUpdate,
			Date endUpdate, long groupId, String ddmStructureKey)
		throws DocumentException, ParseException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long getCalendarIDbyName(String CalendarName);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getCalendarJsonObject(
			ThemeDisplay themeDisplay, Calendar calendar)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<JournalArticle> getChangeProposalFiltered(
		String status, Date startDate, Date endDate, String changePath,
		String responsibleCommittee, String impactedParties,
		String changeCategory);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<JournalArticle> getChangeProposalFilteredByIds(
		String changePorposalList);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<JournalArticle> getChangeProposalFilteredByIdsByPage(
		String changePorposalList, Long page);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<JournalArticle> getChangeProposalFilteredByPage(
		String status, Date startDate, Date endDate, String changePath,
		String responsibleCommittee, String impactedParties,
		String changeCategory, Long page);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DDMStructure getChangeProposalPageStructure(long companyId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Map<String, JSONObject> getCPDetails(String resourcePrimKey);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long getFormIdByArticleId(long articleId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long getParentAlternativeFormId(long resourcePrimKey);

	/**
	 * @throws PortalException
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	 * Returns the rec form article with the primary key.
	 *
	 * @param formId the primary key of the rec form article
	 * @return the rec form article
	 * @throws PortalException if a rec form article with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public recFormArticle getrecFormArticle(long formId) throws PortalException;

	/**
	 * Returns a range of all the rec form articles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.everis.cproposal.model.impl.recFormArticleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of rec form articles
	 * @param end the upper bound of the range of rec form articles (not inclusive)
	 * @return the range of rec form articles
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<recFormArticle> getrecFormArticles(int start, int end);

	/**
	 * Returns the number of rec form articles.
	 *
	 * @return the number of rec form articles
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getrecFormArticlesCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DDMStructure> getStructuresByName(long companyId, String name);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DDMStructure> getStructuresByNameAndByGroupId(
		long companyId, long groupId, String name);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Map<String, String> getUserNotificationsLimit(
		long userId, int limit);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isAlternative(long resourcePrimKey);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isCProposalUserValid(
		long currentUserId, String typeOfSegment);

	public void removeJournalArticleUser(long resourcePrimKey);

	/**
	 * Updates the rec form article in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect recFormArticleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param recFormArticle the rec form article
	 * @return the rec form article that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public recFormArticle updaterecFormArticle(recFormArticle recFormArticle);

}