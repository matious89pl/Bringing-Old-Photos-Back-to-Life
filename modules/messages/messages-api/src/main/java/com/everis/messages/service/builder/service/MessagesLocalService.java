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

import com.everis.messages.service.builder.exception.NoSuchMessagesException;
import com.everis.messages.service.builder.model.Messages;

import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.xml.DocumentException;

import java.io.Serializable;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the local service interface for Messages. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see MessagesLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface MessagesLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.everis.messages.service.builder.service.impl.MessagesLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the messages local service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link MessagesLocalServiceUtil} if injection and service tracking are not available.
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
	@Indexable(type = IndexableType.REINDEX)
	public Messages addMessages(Messages messages);

	/**
	 * Creates a new messages with the primary key. Does not add the messages to the database.
	 *
	 * @param notificationEngineId the primary key for the new messages
	 * @return the new messages
	 */
	@Transactional(enabled = false)
	public Messages createMessages(long notificationEngineId);

	/**
	 * @throws PortalException
	 */
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

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
	@Indexable(type = IndexableType.DELETE)
	public Messages deleteMessages(long notificationEngineId)
		throws PortalException;

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
	@Indexable(type = IndexableType.DELETE)
	public Messages deleteMessages(Messages messages);

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.everis.messages.service.builder.model.impl.MessagesModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.everis.messages.service.builder.model.impl.MessagesModelImpl</code>.
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
	public Messages fetchMessages(long notificationEngineId);

	/**
	 * Returns the messages matching the UUID and group.
	 *
	 * @param uuid the messages's UUID
	 * @param groupId the primary key of the group
	 * @return the matching messages, or <code>null</code> if a matching messages could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Messages fetchMessagesByUuidAndGroupId(String uuid, long groupId);

	public List<Messages> findByCompanyId(long companyId);

	public List<Messages> findByCompanyId(long companyId, int start, int end);

	public List<Messages> findByNameCompany(String name, long companyId);

	public Messages findByNameCompany_First(
			String name, long companyId,
			OrderByComparator<Messages> orderByComparator)
		throws NoSuchMessagesException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCountByCompanyId(long companyId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	 * Returns the messages with the primary key.
	 *
	 * @param notificationEngineId the primary key of the messages
	 * @return the messages
	 * @throws PortalException if a messages with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Messages getMessages(long notificationEngineId)
		throws PortalException;

	/**
	 * Returns the messages matching the UUID and group.
	 *
	 * @param uuid the messages's UUID
	 * @param groupId the primary key of the group
	 * @return the matching messages
	 * @throws PortalException if a matching messages could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Messages getMessagesByUuidAndGroupId(String uuid, long groupId)
		throws PortalException;

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Messages> getMessageses(int start, int end);

	/**
	 * Returns all the messageses matching the UUID and company.
	 *
	 * @param uuid the UUID of the messageses
	 * @param companyId the primary key of the company
	 * @return the matching messageses, or an empty list if no matches were found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Messages> getMessagesesByUuidAndCompanyId(
		String uuid, long companyId);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Messages> getMessagesesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Messages> orderByComparator);

	/**
	 * Returns the number of messageses.
	 *
	 * @return the number of messageses
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getMessagesesCount();

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	/**
	 * @throws PortalException
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	public void sendNotification04ByUser(User user, long classPK);

	public void sendNotification04ByUserByRole(
		User user, String role, long classPK);

	public void sendNotification051ByUserByUrl(User user, long classPK);

	public void sendNotification061ByUserByUrl(
		User user, String comments, String knowledgeRepositoryURL,
		String raiseTicketURL, long classPK);

	public void sendNotification07(long resourcePK);

	public void sendNotification08(User user, String classPK);

	public void sendNotification09ByUser(User user, long classPK);

	public void sendNotification10(long articleId);

	public void sendNotification11(long articleId);

	public void sendNotification12(long resourcePK);

	public void sendNotification13(long articleId);

	public void sendNotification14(long articleId);

	public void sendNotification15(
		long companyId, long groupId, String cPReference,
		String commentsDeadline, long resourcePrimKey, String email);

	public void sendNotification16(long resourcePK);

	public void sendNotification17ByUserByRole(
		User user, String role, long classPK);

	public void sendNotification19(User user, long classPK);

	public void sendNotification20(User user, long classPK);

	public void sendNotification21(User user, long classPK);

	public void sendNotification22(
		User user, long journalArticleUserId, long resourcePrimaryKey);

	public void sendNotification23(
		User user, String role, long resourcePrimaryKey,
		String commentsRejectionWorkflow);

	public void sendNotification24(User user, long resourcePrimaryKey);

	public void sendNotification25(User user, long resourcePrimaryKey);

	public void sendNotification26(
			long userId, long recordVersionId, long siteGroupId)
		throws DocumentException;

	public void sendNotification27(
		long userId, long recordVersionId, long siteGroupId);

	public void sendNotification28(User user, long classPK);

	public void sendNotification29(
		long userId, long resourcePrimaryKey, long siteGroupId);

	public void sendNotification30(
		long userId, long resourcePrimaryKey, long siteGroupId);

	public void sendNotification31(
		long companyId, long groupId, long userId, String roleNames,
		String VotingLink, String titleOfNotification,
		String bodyOfNotification);

	public void sendNotification32(User user, long resourcePrimaryKey);

	public void sendNotification38(
		long userId, long resourcePrimaryKey, long groupId);

	public void sendNotification39(
		long userId, long resourcePrimaryKey, long siteGroupId, String role);

	public void sendNotification41(
		long userId, long resourcePrimKey, long siteGroupId, String role);

	public void sendNotification42(
		long userId, long resourcePrimaryKey, long siteGroupId, String role);

	public void sendNotification43(
		long userId, long resourcePrimaryKey, long siteGroupId, String role);

	public void sendNotification44(long resourcePK);

	public void sendNotification45(long resourcePK);

	public void sendNotification46(
		String notificationTitle, String notificationBody, String segment,
		long currentUserId, String webEnabled, String emailEnabled);

	public void sendNotification47(
		long currentUserId, long siteGroupId, String activityTitle,
		String urlEmail, long companyId);

	public void sendNotification48(
		long currentUserId, long siteGroupId, String rfiTitle, String urlEmail,
		long companyId);

	public void sendNotification49(
		long currentUserId, long siteGroupId, String remedationTitle,
		String urlEmail, long companyId);

	public void sendNotification50(
		String portalURL, long currentUserId, long siteGroupId,
		String activityName, String urlEmail, long companyId);

	public void sendNotification51(
		long currentUserId, long siteGroupId, String activityName,
		String urlEmail, long companyId);

	public void sendNotification52(
		long currentUserId, long siteGroupId, String activityName,
		String urlEmail, long companyId);

	public void sendNotification53(
		long siteGroupId, String activityName, long companyId);

	public void sendNotification54(
		long siteGroupId, String activityName, long companyId);

	public void sendNotification55(
		long currentUserId, long siteGroupId, String rfiTitle, String urlEmail,
		long companyId);

	public void sendNotification56(
		long currentUserId, long siteGroupId, String rfiTitle, String urlEmail,
		long companyId);

	public void sendNotification57(
		long currentUserId, long siteGroupId, String rfiTitle, String urlEmail,
		long companyId);

	public void sendNotification58(
		long currentUserId, long siteGroupId, String rfiTitle, String urlEmail,
		long companyId);

	public void sendNotification59(
		long currentUserId, long siteGroupId, String rfiTitle, String urlEmail,
		long companyId);

	public void sendNotification60(
		long siteGroupId, String rfilogname, long companyId);

	public void sendNotification61(
		long siteGroupId, String rfilogname, long companyId);

	public void sendNotification62(
		long currentUserId, long siteGroupId, String remediationName,
		String urlEmail, long companyId);

	public void sendNotification63(
		long currentUserId, long siteGroupId, String remediationName,
		String urlEmail, long companyId);

	public void sendNotification64(
		long currentUserId, long siteGroupId, String remediationName,
		String urlEmail, long companyId);

	public void sendNotification65(
		long currentUserId, long siteGroupId, String rfiTitle, String urlEmail,
		long companyId);

	public void sendNotification66(
		long currentUserId, long siteGroupId, String rfiTitle, String urlEmail,
		long companyId);

	public void sendNotification67(
		long siteGroupId, String remediationName, long companyId);

	public void sendNotification68(
		long siteGroupId, String remediationName, long companyId);

	public void sendNotification69(
		long currentUserId, long siteGroupId, String activityTitle,
		String urlEmail, long companyId);

	public void sendNotification70(
		long currentUserId, long siteGroupId, String activityName,
		String urlEmail, long companyId);

	public void sendNotification71(
		long currentUserId, long siteGroupId, String activityName,
		String urlEmail, long companyId);

	public void sendNotification72(
		long currentUserId, long siteGroupId, String activityName,
		String urlEmail, long companyId);

	public void sendNotification73(
		long siteGroupId, String remediationName, long companyId);

	public void sendNotification74(
		long siteGroupId, String remediationName, long companyId);

	public void sendNotification75(
		long currentUserId, long siteGroupId, String remedationTitle,
		String urlEmail, long companyId);

	public void sendNotification76(long resourcePK);

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
	@Indexable(type = IndexableType.REINDEX)
	public Messages updateMessages(Messages messages);

}