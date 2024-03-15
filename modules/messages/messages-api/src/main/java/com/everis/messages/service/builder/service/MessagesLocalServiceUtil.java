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

package com.everis.messages.service.builder.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for Messages. This utility wraps
 * <code>com.everis.messages.service.builder.service.impl.MessagesLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see MessagesLocalService
 * @generated
 */
public class MessagesLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.everis.messages.service.builder.service.impl.MessagesLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the messages to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MessagesLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param messages the messages
	 * @return the messages that was added
	 */
	public static com.everis.messages.service.builder.model.Messages
		addMessages(
			com.everis.messages.service.builder.model.Messages messages) {

		return getService().addMessages(messages);
	}

	/**
	 * Creates a new messages with the primary key. Does not add the messages to the database.
	 *
	 * @param notificationEngineId the primary key for the new messages
	 * @return the new messages
	 */
	public static com.everis.messages.service.builder.model.Messages
		createMessages(long notificationEngineId) {

		return getService().createMessages(notificationEngineId);
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
	 * Deletes the messages with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MessagesLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param notificationEngineId the primary key of the messages
	 * @return the messages that was removed
	 * @throws PortalException if a messages with the primary key could not be found
	 */
	public static com.everis.messages.service.builder.model.Messages
			deleteMessages(long notificationEngineId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteMessages(notificationEngineId);
	}

	/**
	 * Deletes the messages from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MessagesLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param messages the messages
	 * @return the messages that was removed
	 */
	public static com.everis.messages.service.builder.model.Messages
		deleteMessages(
			com.everis.messages.service.builder.model.Messages messages) {

		return getService().deleteMessages(messages);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.everis.messages.service.builder.model.impl.MessagesModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.everis.messages.service.builder.model.impl.MessagesModelImpl</code>.
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

	public static com.everis.messages.service.builder.model.Messages
		fetchMessages(long notificationEngineId) {

		return getService().fetchMessages(notificationEngineId);
	}

	/**
	 * Returns the messages matching the UUID and group.
	 *
	 * @param uuid the messages's UUID
	 * @param groupId the primary key of the group
	 * @return the matching messages, or <code>null</code> if a matching messages could not be found
	 */
	public static com.everis.messages.service.builder.model.Messages
		fetchMessagesByUuidAndGroupId(String uuid, long groupId) {

		return getService().fetchMessagesByUuidAndGroupId(uuid, groupId);
	}

	public static java.util.List
		<com.everis.messages.service.builder.model.Messages> findByCompanyId(
			long companyId) {

		return getService().findByCompanyId(companyId);
	}

	public static java.util.List
		<com.everis.messages.service.builder.model.Messages> findByCompanyId(
			long companyId, int start, int end) {

		return getService().findByCompanyId(companyId, start, end);
	}

	public static java.util.List
		<com.everis.messages.service.builder.model.Messages> findByNameCompany(
			String name, long companyId) {

		return getService().findByNameCompany(name, companyId);
	}

	public static com.everis.messages.service.builder.model.Messages
			findByNameCompany_First(
				String name, long companyId,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.everis.messages.service.builder.model.Messages>
						orderByComparator)
		throws com.everis.messages.service.builder.exception.
			NoSuchMessagesException {

		return getService().findByNameCompany_First(
			name, companyId, orderByComparator);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static int getCountByCompanyId(long companyId) {
		return getService().getCountByCompanyId(companyId);
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the messages with the primary key.
	 *
	 * @param notificationEngineId the primary key of the messages
	 * @return the messages
	 * @throws PortalException if a messages with the primary key could not be found
	 */
	public static com.everis.messages.service.builder.model.Messages
			getMessages(long notificationEngineId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getMessages(notificationEngineId);
	}

	/**
	 * Returns the messages matching the UUID and group.
	 *
	 * @param uuid the messages's UUID
	 * @param groupId the primary key of the group
	 * @return the matching messages
	 * @throws PortalException if a matching messages could not be found
	 */
	public static com.everis.messages.service.builder.model.Messages
			getMessagesByUuidAndGroupId(String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getMessagesByUuidAndGroupId(uuid, groupId);
	}

	/**
	 * Returns a range of all the messageses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.everis.messages.service.builder.model.impl.MessagesModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of messageses
	 * @param end the upper bound of the range of messageses (not inclusive)
	 * @return the range of messageses
	 */
	public static java.util.List
		<com.everis.messages.service.builder.model.Messages> getMessageses(
			int start, int end) {

		return getService().getMessageses(start, end);
	}

	/**
	 * Returns all the messageses matching the UUID and company.
	 *
	 * @param uuid the UUID of the messageses
	 * @param companyId the primary key of the company
	 * @return the matching messageses, or an empty list if no matches were found
	 */
	public static java.util.List
		<com.everis.messages.service.builder.model.Messages>
			getMessagesesByUuidAndCompanyId(String uuid, long companyId) {

		return getService().getMessagesesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	 * Returns a range of messageses matching the UUID and company.
	 *
	 * @param uuid the UUID of the messageses
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of messageses
	 * @param end the upper bound of the range of messageses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching messageses, or an empty list if no matches were found
	 */
	public static java.util.List
		<com.everis.messages.service.builder.model.Messages>
			getMessagesesByUuidAndCompanyId(
				String uuid, long companyId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.everis.messages.service.builder.model.Messages>
						orderByComparator) {

		return getService().getMessagesesByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of messageses.
	 *
	 * @return the number of messageses
	 */
	public static int getMessagesesCount() {
		return getService().getMessagesesCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	public static void sendNotification04ByUser(
		com.liferay.portal.kernel.model.User user, long classPK) {

		getService().sendNotification04ByUser(user, classPK);
	}

	public static void sendNotification04ByUserByRole(
		com.liferay.portal.kernel.model.User user, String role, long classPK) {

		getService().sendNotification04ByUserByRole(user, role, classPK);
	}

	public static void sendNotification051ByUserByUrl(
		com.liferay.portal.kernel.model.User user, long classPK) {

		getService().sendNotification051ByUserByUrl(user, classPK);
	}

	public static void sendNotification061ByUserByUrl(
		com.liferay.portal.kernel.model.User user, String comments,
		String knowledgeRepositoryURL, String raiseTicketURL, long classPK) {

		getService().sendNotification061ByUserByUrl(
			user, comments, knowledgeRepositoryURL, raiseTicketURL, classPK);
	}

	public static void sendNotification07(long resourcePK) {
		getService().sendNotification07(resourcePK);
	}

	public static void sendNotification08(
		com.liferay.portal.kernel.model.User user, String classPK) {

		getService().sendNotification08(user, classPK);
	}

	public static void sendNotification09ByUser(
		com.liferay.portal.kernel.model.User user, long classPK) {

		getService().sendNotification09ByUser(user, classPK);
	}

	public static void sendNotification10(long articleId) {
		getService().sendNotification10(articleId);
	}

	public static void sendNotification11(long articleId) {
		getService().sendNotification11(articleId);
	}

	public static void sendNotification12(long resourcePK) {
		getService().sendNotification12(resourcePK);
	}

	public static void sendNotification13(long articleId) {
		getService().sendNotification13(articleId);
	}

	public static void sendNotification14(long articleId) {
		getService().sendNotification14(articleId);
	}

	public static void sendNotification15(
		long companyId, long groupId, String cPReference,
		String commentsDeadline, long resourcePrimKey, String email) {

		getService().sendNotification15(
			companyId, groupId, cPReference, commentsDeadline, resourcePrimKey,
			email);
	}

	public static void sendNotification16(long resourcePK) {
		getService().sendNotification16(resourcePK);
	}

	public static void sendNotification17ByUserByRole(
		com.liferay.portal.kernel.model.User user, String role, long classPK) {

		getService().sendNotification17ByUserByRole(user, role, classPK);
	}

	public static void sendNotification19(
		com.liferay.portal.kernel.model.User user, long classPK) {

		getService().sendNotification19(user, classPK);
	}

	public static void sendNotification20(
		com.liferay.portal.kernel.model.User user, long classPK) {

		getService().sendNotification20(user, classPK);
	}

	public static void sendNotification21(
		com.liferay.portal.kernel.model.User user, long classPK) {

		getService().sendNotification21(user, classPK);
	}

	public static void sendNotification22(
		com.liferay.portal.kernel.model.User user, long journalArticleUserId,
		long resourcePrimaryKey) {

		getService().sendNotification22(
			user, journalArticleUserId, resourcePrimaryKey);
	}

	public static void sendNotification23(
		com.liferay.portal.kernel.model.User user, String role,
		long resourcePrimaryKey, String commentsRejectionWorkflow) {

		getService().sendNotification23(
			user, role, resourcePrimaryKey, commentsRejectionWorkflow);
	}

	public static void sendNotification24(
		com.liferay.portal.kernel.model.User user, long resourcePrimaryKey) {

		getService().sendNotification24(user, resourcePrimaryKey);
	}

	public static void sendNotification25(
		com.liferay.portal.kernel.model.User user, long resourcePrimaryKey) {

		getService().sendNotification25(user, resourcePrimaryKey);
	}

	public static void sendNotification26(
			long userId, long recordVersionId, long siteGroupId)
		throws com.liferay.portal.kernel.xml.DocumentException {

		getService().sendNotification26(userId, recordVersionId, siteGroupId);
	}

	public static void sendNotification27(
		long userId, long recordVersionId, long siteGroupId) {

		getService().sendNotification27(userId, recordVersionId, siteGroupId);
	}

	public static void sendNotification28(
		com.liferay.portal.kernel.model.User user, long classPK) {

		getService().sendNotification28(user, classPK);
	}

	public static void sendNotification29(
		long userId, long resourcePrimaryKey, long siteGroupId) {

		getService().sendNotification29(
			userId, resourcePrimaryKey, siteGroupId);
	}

	public static void sendNotification30(
		long userId, long resourcePrimaryKey, long siteGroupId) {

		getService().sendNotification30(
			userId, resourcePrimaryKey, siteGroupId);
	}

	public static void sendNotification31(
		long companyId, long groupId, long userId, String roleNames,
		String VotingLink, String titleOfNotification,
		String bodyOfNotification) {

		getService().sendNotification31(
			companyId, groupId, userId, roleNames, VotingLink,
			titleOfNotification, bodyOfNotification);
	}

	public static void sendNotification32(
		com.liferay.portal.kernel.model.User user, long resourcePrimaryKey) {

		getService().sendNotification32(user, resourcePrimaryKey);
	}

	public static void sendNotification38(
		long userId, long resourcePrimaryKey, long groupId) {

		getService().sendNotification38(userId, resourcePrimaryKey, groupId);
	}

	public static void sendNotification39(
		long userId, long resourcePrimaryKey, long siteGroupId, String role) {

		getService().sendNotification39(
			userId, resourcePrimaryKey, siteGroupId, role);
	}

	public static void sendNotification41(
		long userId, long resourcePrimKey, long siteGroupId, String role) {

		getService().sendNotification41(
			userId, resourcePrimKey, siteGroupId, role);
	}

	public static void sendNotification42(
		long userId, long resourcePrimaryKey, long siteGroupId, String role) {

		getService().sendNotification42(
			userId, resourcePrimaryKey, siteGroupId, role);
	}

	public static void sendNotification43(
		long userId, long resourcePrimaryKey, long siteGroupId, String role) {

		getService().sendNotification43(
			userId, resourcePrimaryKey, siteGroupId, role);
	}

	public static void sendNotification44(long resourcePK) {
		getService().sendNotification44(resourcePK);
	}

	public static void sendNotification45(long resourcePK) {
		getService().sendNotification45(resourcePK);
	}

	public static void sendNotification46(
		String notificationTitle, String notificationBody, String segment,
		long currentUserId, String webEnabled, String emailEnabled) {

		getService().sendNotification46(
			notificationTitle, notificationBody, segment, currentUserId,
			webEnabled, emailEnabled);
	}

	public static void sendNotification47(
		long currentUserId, long siteGroupId, String activityTitle,
		String urlEmail, long companyId) {

		getService().sendNotification47(
			currentUserId, siteGroupId, activityTitle, urlEmail, companyId);
	}

	public static void sendNotification48(
		long currentUserId, long siteGroupId, String rfiTitle, String urlEmail,
		long companyId) {

		getService().sendNotification48(
			currentUserId, siteGroupId, rfiTitle, urlEmail, companyId);
	}

	public static void sendNotification49(
		long currentUserId, long siteGroupId, String remedationTitle,
		String urlEmail, long companyId) {

		getService().sendNotification49(
			currentUserId, siteGroupId, remedationTitle, urlEmail, companyId);
	}

	public static void sendNotification50(
		String portalURL, long currentUserId, long siteGroupId,
		String activityName, String urlEmail, long companyId) {

		getService().sendNotification50(
			portalURL, currentUserId, siteGroupId, activityName, urlEmail,
			companyId);
	}

	public static void sendNotification51(
		long currentUserId, long siteGroupId, String activityName,
		String urlEmail, long companyId) {

		getService().sendNotification51(
			currentUserId, siteGroupId, activityName, urlEmail, companyId);
	}

	public static void sendNotification52(
		long currentUserId, long siteGroupId, String activityName,
		String urlEmail, long companyId) {

		getService().sendNotification52(
			currentUserId, siteGroupId, activityName, urlEmail, companyId);
	}

	public static void sendNotification53(
		long siteGroupId, String activityName, long companyId) {

		getService().sendNotification53(siteGroupId, activityName, companyId);
	}

	public static void sendNotification54(
		long siteGroupId, String activityName, long companyId) {

		getService().sendNotification54(siteGroupId, activityName, companyId);
	}

	public static void sendNotification55(
		long currentUserId, long siteGroupId, String rfiTitle, String urlEmail,
		long companyId) {

		getService().sendNotification55(
			currentUserId, siteGroupId, rfiTitle, urlEmail, companyId);
	}

	public static void sendNotification56(
		long currentUserId, long siteGroupId, String rfiTitle, String urlEmail,
		long companyId) {

		getService().sendNotification56(
			currentUserId, siteGroupId, rfiTitle, urlEmail, companyId);
	}

	public static void sendNotification57(
		long currentUserId, long siteGroupId, String rfiTitle, String urlEmail,
		long companyId) {

		getService().sendNotification57(
			currentUserId, siteGroupId, rfiTitle, urlEmail, companyId);
	}

	public static void sendNotification58(
		long currentUserId, long siteGroupId, String rfiTitle, String urlEmail,
		long companyId) {

		getService().sendNotification58(
			currentUserId, siteGroupId, rfiTitle, urlEmail, companyId);
	}

	public static void sendNotification59(
		long currentUserId, long siteGroupId, String rfiTitle, String urlEmail,
		long companyId) {

		getService().sendNotification59(
			currentUserId, siteGroupId, rfiTitle, urlEmail, companyId);
	}

	public static void sendNotification60(
		long siteGroupId, String rfilogname, long companyId) {

		getService().sendNotification60(siteGroupId, rfilogname, companyId);
	}

	public static void sendNotification61(
		long siteGroupId, String rfilogname, long companyId) {

		getService().sendNotification61(siteGroupId, rfilogname, companyId);
	}

	public static void sendNotification62(
		long currentUserId, long siteGroupId, String remediationName,
		String urlEmail, long companyId) {

		getService().sendNotification62(
			currentUserId, siteGroupId, remediationName, urlEmail, companyId);
	}

	public static void sendNotification63(
		long currentUserId, long siteGroupId, String remediationName,
		String urlEmail, long companyId) {

		getService().sendNotification63(
			currentUserId, siteGroupId, remediationName, urlEmail, companyId);
	}

	public static void sendNotification64(
		long currentUserId, long siteGroupId, String remediationName,
		String urlEmail, long companyId) {

		getService().sendNotification64(
			currentUserId, siteGroupId, remediationName, urlEmail, companyId);
	}

	public static void sendNotification65(
		long currentUserId, long siteGroupId, String rfiTitle, String urlEmail,
		long companyId) {

		getService().sendNotification65(
			currentUserId, siteGroupId, rfiTitle, urlEmail, companyId);
	}

	public static void sendNotification66(
		long currentUserId, long siteGroupId, String rfiTitle, String urlEmail,
		long companyId) {

		getService().sendNotification66(
			currentUserId, siteGroupId, rfiTitle, urlEmail, companyId);
	}

	public static void sendNotification67(
		long siteGroupId, String remediationName, long companyId) {

		getService().sendNotification67(
			siteGroupId, remediationName, companyId);
	}

	public static void sendNotification68(
		long siteGroupId, String remediationName, long companyId) {

		getService().sendNotification68(
			siteGroupId, remediationName, companyId);
	}

	public static void sendNotification69(
		long currentUserId, long siteGroupId, String activityTitle,
		String urlEmail, long companyId) {

		getService().sendNotification69(
			currentUserId, siteGroupId, activityTitle, urlEmail, companyId);
	}

	public static void sendNotification70(
		long currentUserId, long siteGroupId, String activityName,
		String urlEmail, long companyId) {

		getService().sendNotification70(
			currentUserId, siteGroupId, activityName, urlEmail, companyId);
	}

	public static void sendNotification71(
		long currentUserId, long siteGroupId, String activityName,
		String urlEmail, long companyId) {

		getService().sendNotification71(
			currentUserId, siteGroupId, activityName, urlEmail, companyId);
	}

	public static void sendNotification72(
		long currentUserId, long siteGroupId, String activityName,
		String urlEmail, long companyId) {

		getService().sendNotification72(
			currentUserId, siteGroupId, activityName, urlEmail, companyId);
	}

	public static void sendNotification73(
		long siteGroupId, String remediationName, long companyId) {

		getService().sendNotification73(
			siteGroupId, remediationName, companyId);
	}

	public static void sendNotification74(
		long siteGroupId, String remediationName, long companyId) {

		getService().sendNotification74(
			siteGroupId, remediationName, companyId);
	}

	public static void sendNotification75(
		long currentUserId, long siteGroupId, String remedationTitle,
		String urlEmail, long companyId) {

		getService().sendNotification75(
			currentUserId, siteGroupId, remedationTitle, urlEmail, companyId);
	}

	public static void sendNotification76(long resourcePK) {
		getService().sendNotification76(resourcePK);
	}

	/**
	 * Updates the messages in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MessagesLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param messages the messages
	 * @return the messages that was updated
	 */
	public static com.everis.messages.service.builder.model.Messages
		updateMessages(
			com.everis.messages.service.builder.model.Messages messages) {

		return getService().updateMessages(messages);
	}

	public static MessagesLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<MessagesLocalService, MessagesLocalService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(MessagesLocalService.class);

		ServiceTracker<MessagesLocalService, MessagesLocalService>
			serviceTracker =
				new ServiceTracker<MessagesLocalService, MessagesLocalService>(
					bundle.getBundleContext(), MessagesLocalService.class,
					null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}