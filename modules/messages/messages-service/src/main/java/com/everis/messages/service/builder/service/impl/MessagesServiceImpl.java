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

package com.everis.messages.service.builder.service.impl;

import com.everis.messages.service.builder.service.base.MessagesServiceBaseImpl;
import com.everis.messages.service.builder.service.notification02.utils.Notifications02Utils;
import com.everis.messages.service.builder.service.notification09.utils.Notifications09Utils;
import com.everis.messages.service.builder.service.notification15.utils.Notifications15Utils;
import com.everis.messages.service.builder.service.notification18.utils.Notifications18Utils;
import com.everis.messages.service.builder.service.notification29.utils.Notifications29Utils;
import com.everis.messages.service.builder.service.notification30.utils.Notifications30Utils;
import com.everis.messages.service.builder.service.notification31.utils.Notifications31Utils;
import com.everis.messages.service.builder.service.notification46.utils.Notifications46Utils;
import com.everis.messages.service.builder.service.notification75.utils.Notifications75Utils;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserNotificationEvent;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserNotificationEventLocalServiceUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * The implementation of the messages remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * <code>com.everis.messages.service.builder.service.MessagesService</code>
 * interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have
 * security checks based on the propagated JAAS credentials because this service
 * can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagesServiceBaseImpl
 */
@Component(property = { "json.web.service.context.name=messages",
		"json.web.service.context.path=Messages" }, service = AopService.class)
public class MessagesServiceImpl extends MessagesServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use
	 * <code>com.everis.messages.service.builder.service.MessagesServiceUtil</code>
	 * to access the messages remote service.
	 */
	private static final Log logger = LogFactoryUtil.getLog(MessagesServiceImpl.class);

	@JSONWebService(value = "send_notification_02", method = "POST")
	public void sendNotification02(long companyId, long groupId, long userId, String cpReference,
			ServiceContext serviceContext) {
		logger.debug("Sending Custom Notification 02");

		List<User> userList = new ArrayList<User>();
		List<String> roleNames = new ArrayList<>();

		try {

			roleNames.add("Change_Management_Team");

			userList = _notifications02Utils.getRecipients(companyId, groupId, roleNames);

			_notifications02Utils.notifySubscribers(userList, 0, companyId, null, userId, cpReference);

		} catch (PortalException e) {
			logger.error("Error: " + e);
		}

	}

	@JSONWebService(value = "send_notification_09", method = "POST")
	public void sendNotification09(long userId, long groupId, long companyId, long classPK,
			ServiceContext serviceContext) {
		logger.debug("Sending Custom Notification 09");

		List<User> userList = new ArrayList<User>();
		List<String> roleNames = new ArrayList<>();

		try {

			roleNames.add("Change_Management_Team");

			userList = _notifications09Utils.getRecipients(companyId, groupId, roleNames);

			_notifications09Utils.notifySubscribers(userList, userId, companyId, null, classPK);

		} catch (PortalException e) {
			logger.error("Error: " + e);
		}

	}

	@JSONWebService(value = "send_notification_15", method = "POST")
	public void sendNotification15(long companyId, long groupId, String cPReference, String commentsDeadline,
			long resourcePrimKey, String email) {
		logger.info("Sending Custom Notification 15");

		List<User> userList = new ArrayList<User>();
		List<String> roleNames = new ArrayList<>();

		try {

			roleNames.add("Change_Management_Team");

			userList = _notifications15Utils.getRecipients(companyId, groupId, roleNames);

			_notifications15Utils.notifySubscribers(userList, companyId, null, cPReference, commentsDeadline,
					resourcePrimKey, email);

		} catch (Exception e) {
			logger.error("Error: " + e);
		}
	}

	@JSONWebService(value = "send_notification_018", method = "POST")
	public void sendNotification18(long companyId, long groupId, long userId, long resourcePrimaryKey,
			ServiceContext serviceContext) {
		logger.debug("Sending Custom Notification 18");

		List<User> userList = new ArrayList<User>();
		List<String> roleNames = new ArrayList<>();

		try {

			roleNames.add("Change_Management_Team");

			userList = _notifications18Utils.getRecipients(companyId, groupId, roleNames);

			_notifications18Utils.notifySubscribers(userList, userId, companyId, null, resourcePrimaryKey);

		} catch (PortalException e) {
			logger.error("Error: " + e);
		}

	}

	@JSONWebService(value = "send_notification_029", method = "POST")
	public void sendNotification29(long userId, long resourcePrimaryKey, long siteGroupId) {
		logger.debug("Sending Custom Notification 29");

		List<User> userList = new ArrayList<User>();

		try {

			userList = _notifications29Utils.getRecipients(siteGroupId);

			_notifications29Utils.notifySubscribers(userList, userId, null, resourcePrimaryKey, siteGroupId);

		} catch (PortalException e) {
			logger.error("Error: " + e);
		}

	}

	@JSONWebService(value = "send_notification_030", method = "POST")
	public void sendNotification30(long userId, long resourcePrimaryKey, long siteGroupId) {
		logger.debug("Sending Custom Notification 30");

		List<User> userList = new ArrayList<User>();

		try {

			userList = _notifications30Utils.getRecipients(siteGroupId);

			_notifications30Utils.notifySubscribers(userList, userId, null, resourcePrimaryKey, siteGroupId);

		} catch (PortalException e) {
			logger.error("Error: " + e);
		}

	}

	@JSONWebService(value = "send_notification_031", method = "POST")
	public void sendNotification31(long companyId, long groupId, long userId, String roleNames, String VotingLink,
			String titleOfNotification, String bodyOfNotification) {
		logger.debug("Sending Custom Notification 31");

		List<User> userList = new ArrayList<User>();
		try {

			userList = _notifications31Utils.getRecipients(groupId);

			_notifications31Utils.notifySubscribers(userList, userId, companyId, null, VotingLink, titleOfNotification,
					bodyOfNotification);

		} catch (PortalException e) {
			logger.error("Error: " + e);
		}

	}

	@JSONWebService(value = "send_notification_46", method = "POST")
	public void sendNotification46(String notificationTitle, String notificationBody, String segment,
			long currentUserId, String webEnabled, String emailEnabled) {
		logger.info("Sending Custom Notification 46 - Segment Notification for RPA");

		HashSet<User> userList = new HashSet<User>();

		try {
			userList = _notifications46Utils.getRecipients(segment);

			logger.info("users returned to send notification " + userList.size());

			_notifications46Utils.notifySubscribers(userList, null, notificationTitle, notificationBody, currentUserId,
					webEnabled, emailEnabled);
		} catch (PortalException e) {
			logger.error("Error Custom Notification 46: " + e);
		}
	}

	@JSONWebService(value = "send_notification_75", method = "POST")
	public void sendNotification75(long currentUserId, long siteGroupId, String remedationTitle, String urlEmail,
			long companyId) {
		logger.info("Sending Custom Notification 75");

		HashSet<User> userList = new HashSet<User>();

		try {
			Group group = GroupLocalServiceUtil.getGroup(siteGroupId);

			userList = _notifications75Utils.getRecipients(group.getClassPK(), siteGroupId, companyId);

			logger.info("users returned to send notification " + userList.size());

			_notifications75Utils.notifySubscribers(userList, null, currentUserId, siteGroupId, remedationTitle,
					urlEmail);
		} catch (PortalException e) {
			logger.error("Error Custom Notification 75: " + e);
		}
	}

	@JSONWebService(value = "mark_as_read_notification", method = "POST")
	public void markAsReadNotification(long userId, long userEventNotificationId) {
		logger.debug("START - markAsReadNotification with userId: " + userId + " and userEventNotificationId: "
				+ userEventNotificationId);
		try {
			logger.debug("Getting userNotificationEvent");
			UserNotificationEvent notification = UserNotificationEventLocalServiceUtil
					.getUserNotificationEvent(userEventNotificationId);
			logger.debug("userNotificationEvent: " + notification);
			if (notification.getUserId() != userId) {
				logger.info("The user with userId: " + userId + " is different to the notificationEvent userId: "
						+ notification.getUserId());
				return;
			}
			notification.setArchived(true);
			UserNotificationEventLocalServiceUtil.updateUserNotificationEvent(notification);
			logger.debug("userNotificationEvent updated properly");
		} catch (PortalException e) {
			logger.error("ERROR - Getting userNotificationEvent with id: " + userEventNotificationId, e);
		}
		logger.debug("END - markAsReadNotification with userId: " + userId + " and userEventNotificationId: "
				+ userEventNotificationId);
	}

	private Notifications02Utils _notifications02Utils;

	@Reference(unbind = "-")
	protected void setNotifications02(Notifications02Utils service) {
		_notifications02Utils = service;
	}

	private Notifications09Utils _notifications09Utils;

	@Reference(unbind = "-")
	protected void setNotifications09(Notifications09Utils service) {
		_notifications09Utils = service;
	}

	private Notifications15Utils _notifications15Utils;

	@Reference(unbind = "-")
	protected void setNotifications15(Notifications15Utils service) {
		_notifications15Utils = service;
	}

	private Notifications18Utils _notifications18Utils;

	@Reference(unbind = "-")
	protected void setNotifications18(Notifications18Utils service) {
		_notifications18Utils = service;
	}

	private Notifications29Utils _notifications29Utils;

	@Reference(unbind = "-")
	protected void setNotifications29(Notifications29Utils service) {
		_notifications29Utils = service;
	}

	private Notifications30Utils _notifications30Utils;

	@Reference(unbind = "-")
	protected void setNotifications30(Notifications30Utils service) {
		_notifications30Utils = service;
	}

	private Notifications31Utils _notifications31Utils;

	@Reference(unbind = "-")
	protected void setNotifications31(Notifications31Utils service) {
		_notifications31Utils = service;
	}

	private Notifications46Utils _notifications46Utils;

	@Reference(unbind = "-")
	protected void setNotifications46(Notifications46Utils service) {
		_notifications46Utils = service;
	}

	private Notifications75Utils _notifications75Utils;

	@Reference(unbind = "-")
	protected void setNotifications75(Notifications75Utils service) {
		_notifications75Utils = service;
	}

}