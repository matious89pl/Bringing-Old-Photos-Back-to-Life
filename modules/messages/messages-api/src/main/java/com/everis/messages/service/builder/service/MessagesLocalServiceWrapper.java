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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MessagesLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see MessagesLocalService
 * @generated
 */
public class MessagesLocalServiceWrapper
	implements MessagesLocalService, ServiceWrapper<MessagesLocalService> {

	public MessagesLocalServiceWrapper(
		MessagesLocalService messagesLocalService) {

		_messagesLocalService = messagesLocalService;
	}

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
	@Override
	public com.everis.messages.service.builder.model.Messages addMessages(
		com.everis.messages.service.builder.model.Messages messages) {

		return _messagesLocalService.addMessages(messages);
	}

	/**
	 * Creates a new messages with the primary key. Does not add the messages to the database.
	 *
	 * @param notificationEngineId the primary key for the new messages
	 * @return the new messages
	 */
	@Override
	public com.everis.messages.service.builder.model.Messages createMessages(
		long notificationEngineId) {

		return _messagesLocalService.createMessages(notificationEngineId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _messagesLocalService.createPersistedModel(primaryKeyObj);
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
	@Override
	public com.everis.messages.service.builder.model.Messages deleteMessages(
			long notificationEngineId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _messagesLocalService.deleteMessages(notificationEngineId);
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
	@Override
	public com.everis.messages.service.builder.model.Messages deleteMessages(
		com.everis.messages.service.builder.model.Messages messages) {

		return _messagesLocalService.deleteMessages(messages);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _messagesLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _messagesLocalService.dynamicQuery();
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

		return _messagesLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _messagesLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _messagesLocalService.dynamicQuery(
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

		return _messagesLocalService.dynamicQueryCount(dynamicQuery);
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

		return _messagesLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.everis.messages.service.builder.model.Messages fetchMessages(
		long notificationEngineId) {

		return _messagesLocalService.fetchMessages(notificationEngineId);
	}

	/**
	 * Returns the messages matching the UUID and group.
	 *
	 * @param uuid the messages's UUID
	 * @param groupId the primary key of the group
	 * @return the matching messages, or <code>null</code> if a matching messages could not be found
	 */
	@Override
	public com.everis.messages.service.builder.model.Messages
		fetchMessagesByUuidAndGroupId(String uuid, long groupId) {

		return _messagesLocalService.fetchMessagesByUuidAndGroupId(
			uuid, groupId);
	}

	@Override
	public java.util.List<com.everis.messages.service.builder.model.Messages>
		findByCompanyId(long companyId) {

		return _messagesLocalService.findByCompanyId(companyId);
	}

	@Override
	public java.util.List<com.everis.messages.service.builder.model.Messages>
		findByCompanyId(long companyId, int start, int end) {

		return _messagesLocalService.findByCompanyId(companyId, start, end);
	}

	@Override
	public java.util.List<com.everis.messages.service.builder.model.Messages>
		findByNameCompany(String name, long companyId) {

		return _messagesLocalService.findByNameCompany(name, companyId);
	}

	@Override
	public com.everis.messages.service.builder.model.Messages
			findByNameCompany_First(
				String name, long companyId,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.everis.messages.service.builder.model.Messages>
						orderByComparator)
		throws com.everis.messages.service.builder.exception.
			NoSuchMessagesException {

		return _messagesLocalService.findByNameCompany_First(
			name, companyId, orderByComparator);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _messagesLocalService.getActionableDynamicQuery();
	}

	@Override
	public int getCountByCompanyId(long companyId) {
		return _messagesLocalService.getCountByCompanyId(companyId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _messagesLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _messagesLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the messages with the primary key.
	 *
	 * @param notificationEngineId the primary key of the messages
	 * @return the messages
	 * @throws PortalException if a messages with the primary key could not be found
	 */
	@Override
	public com.everis.messages.service.builder.model.Messages getMessages(
			long notificationEngineId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _messagesLocalService.getMessages(notificationEngineId);
	}

	/**
	 * Returns the messages matching the UUID and group.
	 *
	 * @param uuid the messages's UUID
	 * @param groupId the primary key of the group
	 * @return the matching messages
	 * @throws PortalException if a matching messages could not be found
	 */
	@Override
	public com.everis.messages.service.builder.model.Messages
			getMessagesByUuidAndGroupId(String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _messagesLocalService.getMessagesByUuidAndGroupId(uuid, groupId);
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
	@Override
	public java.util.List<com.everis.messages.service.builder.model.Messages>
		getMessageses(int start, int end) {

		return _messagesLocalService.getMessageses(start, end);
	}

	/**
	 * Returns all the messageses matching the UUID and company.
	 *
	 * @param uuid the UUID of the messageses
	 * @param companyId the primary key of the company
	 * @return the matching messageses, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<com.everis.messages.service.builder.model.Messages>
		getMessagesesByUuidAndCompanyId(String uuid, long companyId) {

		return _messagesLocalService.getMessagesesByUuidAndCompanyId(
			uuid, companyId);
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
	@Override
	public java.util.List<com.everis.messages.service.builder.model.Messages>
		getMessagesesByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.everis.messages.service.builder.model.Messages>
					orderByComparator) {

		return _messagesLocalService.getMessagesesByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of messageses.
	 *
	 * @return the number of messageses
	 */
	@Override
	public int getMessagesesCount() {
		return _messagesLocalService.getMessagesesCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _messagesLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _messagesLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public void sendNotification04ByUser(
		com.liferay.portal.kernel.model.User user, long classPK) {

		_messagesLocalService.sendNotification04ByUser(user, classPK);
	}

	@Override
	public void sendNotification04ByUserByRole(
		com.liferay.portal.kernel.model.User user, String role, long classPK) {

		_messagesLocalService.sendNotification04ByUserByRole(
			user, role, classPK);
	}

	@Override
	public void sendNotification051ByUserByUrl(
		com.liferay.portal.kernel.model.User user, long classPK) {

		_messagesLocalService.sendNotification051ByUserByUrl(user, classPK);
	}

	@Override
	public void sendNotification061ByUserByUrl(
		com.liferay.portal.kernel.model.User user, String comments,
		String knowledgeRepositoryURL, String raiseTicketURL, long classPK) {

		_messagesLocalService.sendNotification061ByUserByUrl(
			user, comments, knowledgeRepositoryURL, raiseTicketURL, classPK);
	}

	@Override
	public void sendNotification07(long resourcePK) {
		_messagesLocalService.sendNotification07(resourcePK);
	}

	@Override
	public void sendNotification08(
		com.liferay.portal.kernel.model.User user, String classPK) {

		_messagesLocalService.sendNotification08(user, classPK);
	}

	@Override
	public void sendNotification09ByUser(
		com.liferay.portal.kernel.model.User user, long classPK) {

		_messagesLocalService.sendNotification09ByUser(user, classPK);
	}

	@Override
	public void sendNotification10(long articleId) {
		_messagesLocalService.sendNotification10(articleId);
	}

	@Override
	public void sendNotification11(long articleId) {
		_messagesLocalService.sendNotification11(articleId);
	}

	@Override
	public void sendNotification12(long resourcePK) {
		_messagesLocalService.sendNotification12(resourcePK);
	}

	@Override
	public void sendNotification13(long articleId) {
		_messagesLocalService.sendNotification13(articleId);
	}

	@Override
	public void sendNotification14(long articleId) {
		_messagesLocalService.sendNotification14(articleId);
	}

	@Override
	public void sendNotification15(
		long companyId, long groupId, String cPReference,
		String commentsDeadline, long resourcePrimKey, String email) {

		_messagesLocalService.sendNotification15(
			companyId, groupId, cPReference, commentsDeadline, resourcePrimKey,
			email);
	}

	@Override
	public void sendNotification16(long resourcePK) {
		_messagesLocalService.sendNotification16(resourcePK);
	}

	@Override
	public void sendNotification17ByUserByRole(
		com.liferay.portal.kernel.model.User user, String role, long classPK) {

		_messagesLocalService.sendNotification17ByUserByRole(
			user, role, classPK);
	}

	@Override
	public void sendNotification19(
		com.liferay.portal.kernel.model.User user, long classPK) {

		_messagesLocalService.sendNotification19(user, classPK);
	}

	@Override
	public void sendNotification20(
		com.liferay.portal.kernel.model.User user, long classPK) {

		_messagesLocalService.sendNotification20(user, classPK);
	}

	@Override
	public void sendNotification21(
		com.liferay.portal.kernel.model.User user, long classPK) {

		_messagesLocalService.sendNotification21(user, classPK);
	}

	@Override
	public void sendNotification22(
		com.liferay.portal.kernel.model.User user, long journalArticleUserId,
		long resourcePrimaryKey) {

		_messagesLocalService.sendNotification22(
			user, journalArticleUserId, resourcePrimaryKey);
	}

	@Override
	public void sendNotification23(
		com.liferay.portal.kernel.model.User user, String role,
		long resourcePrimaryKey, String commentsRejectionWorkflow) {

		_messagesLocalService.sendNotification23(
			user, role, resourcePrimaryKey, commentsRejectionWorkflow);
	}

	@Override
	public void sendNotification24(
		com.liferay.portal.kernel.model.User user, long resourcePrimaryKey) {

		_messagesLocalService.sendNotification24(user, resourcePrimaryKey);
	}

	@Override
	public void sendNotification25(
		com.liferay.portal.kernel.model.User user, long resourcePrimaryKey) {

		_messagesLocalService.sendNotification25(user, resourcePrimaryKey);
	}

	@Override
	public void sendNotification26(
			long userId, long recordVersionId, long siteGroupId)
		throws com.liferay.portal.kernel.xml.DocumentException {

		_messagesLocalService.sendNotification26(
			userId, recordVersionId, siteGroupId);
	}

	@Override
	public void sendNotification27(
		long userId, long recordVersionId, long siteGroupId) {

		_messagesLocalService.sendNotification27(
			userId, recordVersionId, siteGroupId);
	}

	@Override
	public void sendNotification28(
		com.liferay.portal.kernel.model.User user, long classPK) {

		_messagesLocalService.sendNotification28(user, classPK);
	}

	@Override
	public void sendNotification29(
		long userId, long resourcePrimaryKey, long siteGroupId) {

		_messagesLocalService.sendNotification29(
			userId, resourcePrimaryKey, siteGroupId);
	}

	@Override
	public void sendNotification30(
		long userId, long resourcePrimaryKey, long siteGroupId) {

		_messagesLocalService.sendNotification30(
			userId, resourcePrimaryKey, siteGroupId);
	}

	@Override
	public void sendNotification31(
		long companyId, long groupId, long userId, String roleNames,
		String VotingLink, String titleOfNotification,
		String bodyOfNotification) {

		_messagesLocalService.sendNotification31(
			companyId, groupId, userId, roleNames, VotingLink,
			titleOfNotification, bodyOfNotification);
	}

	@Override
	public void sendNotification32(
		com.liferay.portal.kernel.model.User user, long resourcePrimaryKey) {

		_messagesLocalService.sendNotification32(user, resourcePrimaryKey);
	}

	@Override
	public void sendNotification38(
		long userId, long resourcePrimaryKey, long groupId) {

		_messagesLocalService.sendNotification38(
			userId, resourcePrimaryKey, groupId);
	}

	@Override
	public void sendNotification39(
		long userId, long resourcePrimaryKey, long siteGroupId, String role) {

		_messagesLocalService.sendNotification39(
			userId, resourcePrimaryKey, siteGroupId, role);
	}

	@Override
	public void sendNotification41(
		long userId, long resourcePrimKey, long siteGroupId, String role) {

		_messagesLocalService.sendNotification41(
			userId, resourcePrimKey, siteGroupId, role);
	}

	@Override
	public void sendNotification42(
		long userId, long resourcePrimaryKey, long siteGroupId, String role) {

		_messagesLocalService.sendNotification42(
			userId, resourcePrimaryKey, siteGroupId, role);
	}

	@Override
	public void sendNotification43(
		long userId, long resourcePrimaryKey, long siteGroupId, String role) {

		_messagesLocalService.sendNotification43(
			userId, resourcePrimaryKey, siteGroupId, role);
	}

	@Override
	public void sendNotification44(long resourcePK) {
		_messagesLocalService.sendNotification44(resourcePK);
	}

	@Override
	public void sendNotification45(long resourcePK) {
		_messagesLocalService.sendNotification45(resourcePK);
	}

	@Override
	public void sendNotification46(
		String notificationTitle, String notificationBody, String segment,
		long currentUserId, String webEnabled, String emailEnabled) {

		_messagesLocalService.sendNotification46(
			notificationTitle, notificationBody, segment, currentUserId,
			webEnabled, emailEnabled);
	}

	@Override
	public void sendNotification47(
		long currentUserId, long siteGroupId, String activityTitle,
		String urlEmail, long companyId) {

		_messagesLocalService.sendNotification47(
			currentUserId, siteGroupId, activityTitle, urlEmail, companyId);
	}

	@Override
	public void sendNotification48(
		long currentUserId, long siteGroupId, String rfiTitle, String urlEmail,
		long companyId) {

		_messagesLocalService.sendNotification48(
			currentUserId, siteGroupId, rfiTitle, urlEmail, companyId);
	}

	@Override
	public void sendNotification49(
		long currentUserId, long siteGroupId, String remedationTitle,
		String urlEmail, long companyId) {

		_messagesLocalService.sendNotification49(
			currentUserId, siteGroupId, remedationTitle, urlEmail, companyId);
	}

	@Override
	public void sendNotification50(
		String portalURL, long currentUserId, long siteGroupId,
		String activityName, String urlEmail, long companyId) {

		_messagesLocalService.sendNotification50(
			portalURL, currentUserId, siteGroupId, activityName, urlEmail,
			companyId);
	}

	@Override
	public void sendNotification51(
		long currentUserId, long siteGroupId, String activityName,
		String urlEmail, long companyId) {

		_messagesLocalService.sendNotification51(
			currentUserId, siteGroupId, activityName, urlEmail, companyId);
	}

	@Override
	public void sendNotification52(
		long currentUserId, long siteGroupId, String activityName,
		String urlEmail, long companyId) {

		_messagesLocalService.sendNotification52(
			currentUserId, siteGroupId, activityName, urlEmail, companyId);
	}

	@Override
	public void sendNotification53(
		long siteGroupId, String activityName, long companyId) {

		_messagesLocalService.sendNotification53(
			siteGroupId, activityName, companyId);
	}

	@Override
	public void sendNotification54(
		long siteGroupId, String activityName, long companyId) {

		_messagesLocalService.sendNotification54(
			siteGroupId, activityName, companyId);
	}

	@Override
	public void sendNotification55(
		long currentUserId, long siteGroupId, String rfiTitle, String urlEmail,
		long companyId) {

		_messagesLocalService.sendNotification55(
			currentUserId, siteGroupId, rfiTitle, urlEmail, companyId);
	}

	@Override
	public void sendNotification56(
		long currentUserId, long siteGroupId, String rfiTitle, String urlEmail,
		long companyId) {

		_messagesLocalService.sendNotification56(
			currentUserId, siteGroupId, rfiTitle, urlEmail, companyId);
	}

	@Override
	public void sendNotification57(
		long currentUserId, long siteGroupId, String rfiTitle, String urlEmail,
		long companyId) {

		_messagesLocalService.sendNotification57(
			currentUserId, siteGroupId, rfiTitle, urlEmail, companyId);
	}

	@Override
	public void sendNotification58(
		long currentUserId, long siteGroupId, String rfiTitle, String urlEmail,
		long companyId) {

		_messagesLocalService.sendNotification58(
			currentUserId, siteGroupId, rfiTitle, urlEmail, companyId);
	}

	@Override
	public void sendNotification59(
		long currentUserId, long siteGroupId, String rfiTitle, String urlEmail,
		long companyId) {

		_messagesLocalService.sendNotification59(
			currentUserId, siteGroupId, rfiTitle, urlEmail, companyId);
	}

	@Override
	public void sendNotification60(
		long siteGroupId, String rfilogname, long companyId) {

		_messagesLocalService.sendNotification60(
			siteGroupId, rfilogname, companyId);
	}

	@Override
	public void sendNotification61(
		long siteGroupId, String rfilogname, long companyId) {

		_messagesLocalService.sendNotification61(
			siteGroupId, rfilogname, companyId);
	}

	@Override
	public void sendNotification62(
		long currentUserId, long siteGroupId, String remediationName,
		String urlEmail, long companyId) {

		_messagesLocalService.sendNotification62(
			currentUserId, siteGroupId, remediationName, urlEmail, companyId);
	}

	@Override
	public void sendNotification63(
		long currentUserId, long siteGroupId, String remediationName,
		String urlEmail, long companyId) {

		_messagesLocalService.sendNotification63(
			currentUserId, siteGroupId, remediationName, urlEmail, companyId);
	}

	@Override
	public void sendNotification64(
		long currentUserId, long siteGroupId, String remediationName,
		String urlEmail, long companyId) {

		_messagesLocalService.sendNotification64(
			currentUserId, siteGroupId, remediationName, urlEmail, companyId);
	}

	@Override
	public void sendNotification65(
		long currentUserId, long siteGroupId, String rfiTitle, String urlEmail,
		long companyId) {

		_messagesLocalService.sendNotification65(
			currentUserId, siteGroupId, rfiTitle, urlEmail, companyId);
	}

	@Override
	public void sendNotification66(
		long currentUserId, long siteGroupId, String rfiTitle, String urlEmail,
		long companyId) {

		_messagesLocalService.sendNotification66(
			currentUserId, siteGroupId, rfiTitle, urlEmail, companyId);
	}

	@Override
	public void sendNotification67(
		long siteGroupId, String remediationName, long companyId) {

		_messagesLocalService.sendNotification67(
			siteGroupId, remediationName, companyId);
	}

	@Override
	public void sendNotification68(
		long siteGroupId, String remediationName, long companyId) {

		_messagesLocalService.sendNotification68(
			siteGroupId, remediationName, companyId);
	}

	@Override
	public void sendNotification69(
		long currentUserId, long siteGroupId, String activityTitle,
		String urlEmail, long companyId) {

		_messagesLocalService.sendNotification69(
			currentUserId, siteGroupId, activityTitle, urlEmail, companyId);
	}

	@Override
	public void sendNotification70(
		long currentUserId, long siteGroupId, String activityName,
		String urlEmail, long companyId) {

		_messagesLocalService.sendNotification70(
			currentUserId, siteGroupId, activityName, urlEmail, companyId);
	}

	@Override
	public void sendNotification71(
		long currentUserId, long siteGroupId, String activityName,
		String urlEmail, long companyId) {

		_messagesLocalService.sendNotification71(
			currentUserId, siteGroupId, activityName, urlEmail, companyId);
	}

	@Override
	public void sendNotification72(
		long currentUserId, long siteGroupId, String activityName,
		String urlEmail, long companyId) {

		_messagesLocalService.sendNotification72(
			currentUserId, siteGroupId, activityName, urlEmail, companyId);
	}

	@Override
	public void sendNotification73(
		long siteGroupId, String remediationName, long companyId) {

		_messagesLocalService.sendNotification73(
			siteGroupId, remediationName, companyId);
	}

	@Override
	public void sendNotification74(
		long siteGroupId, String remediationName, long companyId) {

		_messagesLocalService.sendNotification74(
			siteGroupId, remediationName, companyId);
	}

	@Override
	public void sendNotification75(
		long currentUserId, long siteGroupId, String remedationTitle,
		String urlEmail, long companyId) {

		_messagesLocalService.sendNotification75(
			currentUserId, siteGroupId, remedationTitle, urlEmail, companyId);
	}

	@Override
	public void sendNotification76(long resourcePK) {
		_messagesLocalService.sendNotification76(resourcePK);
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
	@Override
	public com.everis.messages.service.builder.model.Messages updateMessages(
		com.everis.messages.service.builder.model.Messages messages) {

		return _messagesLocalService.updateMessages(messages);
	}

	@Override
	public MessagesLocalService getWrappedService() {
		return _messagesLocalService;
	}

	@Override
	public void setWrappedService(MessagesLocalService messagesLocalService) {
		_messagesLocalService = messagesLocalService;
	}

	private MessagesLocalService _messagesLocalService;

}