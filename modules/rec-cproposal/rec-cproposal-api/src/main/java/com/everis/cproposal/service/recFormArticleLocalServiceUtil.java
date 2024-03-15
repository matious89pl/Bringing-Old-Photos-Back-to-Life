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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for recFormArticle. This utility wraps
 * <code>com.everis.cproposal.service.impl.recFormArticleLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see recFormArticleLocalService
 * @generated
 */
public class recFormArticleLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.everis.cproposal.service.impl.recFormArticleLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
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
	public static com.everis.cproposal.model.recFormArticle addrecFormArticle(
		com.everis.cproposal.model.recFormArticle recFormArticle) {

		return getService().addrecFormArticle(recFormArticle);
	}

	public static long createCProposal(long classPK)
		throws com.liferay.portal.kernel.xml.DocumentException {

		return getService().createCProposal(classPK);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			createPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new rec form article with the primary key. Does not add the rec form article to the database.
	 *
	 * @param formId the primary key for the new rec form article
	 * @return the new rec form article
	 */
	public static com.everis.cproposal.model.recFormArticle
		createrecFormArticle(long formId) {

		return getService().createrecFormArticle(formId);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
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
	public static com.everis.cproposal.model.recFormArticle
			deleterecFormArticle(long formId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleterecFormArticle(formId);
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
	public static com.everis.cproposal.model.recFormArticle
		deleterecFormArticle(
			com.everis.cproposal.model.recFormArticle recFormArticle) {

		return getService().deleterecFormArticle(recFormArticle);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.everis.cproposal.model.recFormArticle fetchrecFormArticle(
		long formId) {

		return getService().fetchrecFormArticle(formId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static java.util.List<com.liferay.journal.model.JournalArticle>
			getActionLogFiltered(
				java.util.Date startDate, java.util.Date endDate, String status,
				java.util.Date startUpdate, java.util.Date endUpdate,
				long groupId, String ddmStructureKey)
		throws com.liferay.portal.kernel.xml.DocumentException,
			   java.text.ParseException {

		return getService().getActionLogFiltered(
			startDate, endDate, status, startUpdate, endUpdate, groupId,
			ddmStructureKey);
	}

	public static long getCalendarIDbyName(String CalendarName) {
		return getService().getCalendarIDbyName(CalendarName);
	}

	public static com.liferay.portal.kernel.json.JSONObject
			getCalendarJsonObject(
				com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay,
				com.liferay.calendar.model.Calendar calendar)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCalendarJsonObject(themeDisplay, calendar);
	}

	public static java.util.List<com.liferay.journal.model.JournalArticle>
		getChangeProposalFiltered(
			String status, java.util.Date startDate, java.util.Date endDate,
			String changePath, String responsibleCommittee,
			String impactedParties, String changeCategory) {

		return getService().getChangeProposalFiltered(
			status, startDate, endDate, changePath, responsibleCommittee,
			impactedParties, changeCategory);
	}

	public static java.util.List<com.liferay.journal.model.JournalArticle>
		getChangeProposalFilteredByIds(String changePorposalList) {

		return getService().getChangeProposalFilteredByIds(changePorposalList);
	}

	public static java.util.List<com.liferay.journal.model.JournalArticle>
		getChangeProposalFilteredByIdsByPage(
			String changePorposalList, Long page) {

		return getService().getChangeProposalFilteredByIdsByPage(
			changePorposalList, page);
	}

	public static java.util.List<com.liferay.journal.model.JournalArticle>
		getChangeProposalFilteredByPage(
			String status, java.util.Date startDate, java.util.Date endDate,
			String changePath, String responsibleCommittee,
			String impactedParties, String changeCategory, Long page) {

		return getService().getChangeProposalFilteredByPage(
			status, startDate, endDate, changePath, responsibleCommittee,
			impactedParties, changeCategory, page);
	}

	public static com.liferay.dynamic.data.mapping.model.DDMStructure
		getChangeProposalPageStructure(long companyId) {

		return getService().getChangeProposalPageStructure(companyId);
	}

	public static java.util.Map
		<String, com.liferay.portal.kernel.json.JSONObject> getCPDetails(
			String resourcePrimKey) {

		return getService().getCPDetails(resourcePrimKey);
	}

	public static long getFormIdByArticleId(long articleId) {
		return getService().getFormIdByArticleId(articleId);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static long getParentAlternativeFormId(long resourcePrimKey) {
		return getService().getParentAlternativeFormId(resourcePrimKey);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the rec form article with the primary key.
	 *
	 * @param formId the primary key of the rec form article
	 * @return the rec form article
	 * @throws PortalException if a rec form article with the primary key could not be found
	 */
	public static com.everis.cproposal.model.recFormArticle getrecFormArticle(
			long formId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getrecFormArticle(formId);
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
	public static java.util.List<com.everis.cproposal.model.recFormArticle>
		getrecFormArticles(int start, int end) {

		return getService().getrecFormArticles(start, end);
	}

	/**
	 * Returns the number of rec form articles.
	 *
	 * @return the number of rec form articles
	 */
	public static int getrecFormArticlesCount() {
		return getService().getrecFormArticlesCount();
	}

	public static java.util.List
		<com.liferay.dynamic.data.mapping.model.DDMStructure>
			getStructuresByName(long companyId, String name) {

		return getService().getStructuresByName(companyId, name);
	}

	public static java.util.List
		<com.liferay.dynamic.data.mapping.model.DDMStructure>
			getStructuresByNameAndByGroupId(
				long companyId, long groupId, String name) {

		return getService().getStructuresByNameAndByGroupId(
			companyId, groupId, name);
	}

	public static java.util.Map<String, String> getUserNotificationsLimit(
		long userId, int limit) {

		return getService().getUserNotificationsLimit(userId, limit);
	}

	public static boolean isAlternative(long resourcePrimKey) {
		return getService().isAlternative(resourcePrimKey);
	}

	public static boolean isCProposalUserValid(
		long currentUserId, String typeOfSegment) {

		return getService().isCProposalUserValid(currentUserId, typeOfSegment);
	}

	public static void removeJournalArticleUser(long resourcePrimKey) {
		getService().removeJournalArticleUser(resourcePrimKey);
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
	public static com.everis.cproposal.model.recFormArticle
		updaterecFormArticle(
			com.everis.cproposal.model.recFormArticle recFormArticle) {

		return getService().updaterecFormArticle(recFormArticle);
	}

	public static recFormArticleLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<recFormArticleLocalService, recFormArticleLocalService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			recFormArticleLocalService.class);

		ServiceTracker<recFormArticleLocalService, recFormArticleLocalService>
			serviceTracker =
				new ServiceTracker
					<recFormArticleLocalService, recFormArticleLocalService>(
						bundle.getBundleContext(),
						recFormArticleLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}