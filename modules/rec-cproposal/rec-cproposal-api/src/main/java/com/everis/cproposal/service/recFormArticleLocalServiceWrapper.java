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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link recFormArticleLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see recFormArticleLocalService
 * @generated
 */
public class recFormArticleLocalServiceWrapper
	implements recFormArticleLocalService,
			   ServiceWrapper<recFormArticleLocalService> {

	public recFormArticleLocalServiceWrapper(
		recFormArticleLocalService recFormArticleLocalService) {

		_recFormArticleLocalService = recFormArticleLocalService;
	}

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
	@Override
	public com.everis.cproposal.model.recFormArticle addrecFormArticle(
		com.everis.cproposal.model.recFormArticle recFormArticle) {

		return _recFormArticleLocalService.addrecFormArticle(recFormArticle);
	}

	@Override
	public long createCProposal(long classPK)
		throws com.liferay.portal.kernel.xml.DocumentException {

		return _recFormArticleLocalService.createCProposal(classPK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _recFormArticleLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new rec form article with the primary key. Does not add the rec form article to the database.
	 *
	 * @param formId the primary key for the new rec form article
	 * @return the new rec form article
	 */
	@Override
	public com.everis.cproposal.model.recFormArticle createrecFormArticle(
		long formId) {

		return _recFormArticleLocalService.createrecFormArticle(formId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _recFormArticleLocalService.deletePersistedModel(persistedModel);
	}

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
	@Override
	public com.everis.cproposal.model.recFormArticle deleterecFormArticle(
			long formId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _recFormArticleLocalService.deleterecFormArticle(formId);
	}

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
	@Override
	public com.everis.cproposal.model.recFormArticle deleterecFormArticle(
		com.everis.cproposal.model.recFormArticle recFormArticle) {

		return _recFormArticleLocalService.deleterecFormArticle(recFormArticle);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _recFormArticleLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _recFormArticleLocalService.dynamicQuery(dynamicQuery);
	}

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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _recFormArticleLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _recFormArticleLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _recFormArticleLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _recFormArticleLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.everis.cproposal.model.recFormArticle fetchrecFormArticle(
		long formId) {

		return _recFormArticleLocalService.fetchrecFormArticle(formId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _recFormArticleLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<com.liferay.journal.model.JournalArticle>
			getActionLogFiltered(
				java.util.Date startDate, java.util.Date endDate, String status,
				java.util.Date startUpdate, java.util.Date endUpdate,
				long groupId, String ddmStructureKey)
		throws com.liferay.portal.kernel.xml.DocumentException,
			   java.text.ParseException {

		return _recFormArticleLocalService.getActionLogFiltered(
			startDate, endDate, status, startUpdate, endUpdate, groupId,
			ddmStructureKey);
	}

	@Override
	public long getCalendarIDbyName(String CalendarName) {
		return _recFormArticleLocalService.getCalendarIDbyName(CalendarName);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getCalendarJsonObject(
			com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay,
			com.liferay.calendar.model.Calendar calendar)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _recFormArticleLocalService.getCalendarJsonObject(
			themeDisplay, calendar);
	}

	@Override
	public java.util.List<com.liferay.journal.model.JournalArticle>
		getChangeProposalFiltered(
			String status, java.util.Date startDate, java.util.Date endDate,
			String changePath, String responsibleCommittee,
			String impactedParties, String changeCategory) {

		return _recFormArticleLocalService.getChangeProposalFiltered(
			status, startDate, endDate, changePath, responsibleCommittee,
			impactedParties, changeCategory);
	}

	@Override
	public java.util.List<com.liferay.journal.model.JournalArticle>
		getChangeProposalFilteredByIds(String changePorposalList) {

		return _recFormArticleLocalService.getChangeProposalFilteredByIds(
			changePorposalList);
	}

	@Override
	public java.util.List<com.liferay.journal.model.JournalArticle>
		getChangeProposalFilteredByIdsByPage(
			String changePorposalList, Long page) {

		return _recFormArticleLocalService.getChangeProposalFilteredByIdsByPage(
			changePorposalList, page);
	}

	@Override
	public java.util.List<com.liferay.journal.model.JournalArticle>
		getChangeProposalFilteredByPage(
			String status, java.util.Date startDate, java.util.Date endDate,
			String changePath, String responsibleCommittee,
			String impactedParties, String changeCategory, Long page) {

		return _recFormArticleLocalService.getChangeProposalFilteredByPage(
			status, startDate, endDate, changePath, responsibleCommittee,
			impactedParties, changeCategory, page);
	}

	@Override
	public com.liferay.dynamic.data.mapping.model.DDMStructure
		getChangeProposalPageStructure(long companyId) {

		return _recFormArticleLocalService.getChangeProposalPageStructure(
			companyId);
	}

	@Override
	public java.util.Map<String, com.liferay.portal.kernel.json.JSONObject>
		getCPDetails(String resourcePrimKey) {

		return _recFormArticleLocalService.getCPDetails(resourcePrimKey);
	}

	@Override
	public long getFormIdByArticleId(long articleId) {
		return _recFormArticleLocalService.getFormIdByArticleId(articleId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _recFormArticleLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _recFormArticleLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public long getParentAlternativeFormId(long resourcePrimKey) {
		return _recFormArticleLocalService.getParentAlternativeFormId(
			resourcePrimKey);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _recFormArticleLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the rec form article with the primary key.
	 *
	 * @param formId the primary key of the rec form article
	 * @return the rec form article
	 * @throws PortalException if a rec form article with the primary key could not be found
	 */
	@Override
	public com.everis.cproposal.model.recFormArticle getrecFormArticle(
			long formId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _recFormArticleLocalService.getrecFormArticle(formId);
	}

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
	@Override
	public java.util.List<com.everis.cproposal.model.recFormArticle>
		getrecFormArticles(int start, int end) {

		return _recFormArticleLocalService.getrecFormArticles(start, end);
	}

	/**
	 * Returns the number of rec form articles.
	 *
	 * @return the number of rec form articles
	 */
	@Override
	public int getrecFormArticlesCount() {
		return _recFormArticleLocalService.getrecFormArticlesCount();
	}

	@Override
	public java.util.List<com.liferay.dynamic.data.mapping.model.DDMStructure>
		getStructuresByName(long companyId, String name) {

		return _recFormArticleLocalService.getStructuresByName(companyId, name);
	}

	@Override
	public java.util.List<com.liferay.dynamic.data.mapping.model.DDMStructure>
		getStructuresByNameAndByGroupId(
			long companyId, long groupId, String name) {

		return _recFormArticleLocalService.getStructuresByNameAndByGroupId(
			companyId, groupId, name);
	}

	@Override
	public java.util.Map<String, String> getUserNotificationsLimit(
		long userId, int limit) {

		return _recFormArticleLocalService.getUserNotificationsLimit(
			userId, limit);
	}

	@Override
	public boolean isAlternative(long resourcePrimKey) {
		return _recFormArticleLocalService.isAlternative(resourcePrimKey);
	}

	@Override
	public boolean isCProposalUserValid(
		long currentUserId, String typeOfSegment) {

		return _recFormArticleLocalService.isCProposalUserValid(
			currentUserId, typeOfSegment);
	}

	@Override
	public void removeJournalArticleUser(long resourcePrimKey) {
		_recFormArticleLocalService.removeJournalArticleUser(resourcePrimKey);
	}

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
	@Override
	public com.everis.cproposal.model.recFormArticle updaterecFormArticle(
		com.everis.cproposal.model.recFormArticle recFormArticle) {

		return _recFormArticleLocalService.updaterecFormArticle(recFormArticle);
	}

	@Override
	public recFormArticleLocalService getWrappedService() {
		return _recFormArticleLocalService;
	}

	@Override
	public void setWrappedService(
		recFormArticleLocalService recFormArticleLocalService) {

		_recFormArticleLocalService = recFormArticleLocalService;
	}

	private recFormArticleLocalService _recFormArticleLocalService;

}