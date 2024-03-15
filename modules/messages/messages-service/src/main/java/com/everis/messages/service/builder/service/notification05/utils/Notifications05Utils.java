package com.everis.messages.service.builder.service.notification05.utils;

import com.everis.messages.service.builder.model.Messages;
import com.everis.messages.service.builder.service.MessagesLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroupRole;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserGroupRoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = {}, service = Notifications05Utils.class)

public class Notifications05Utils {

	private static final Log logger = LogFactoryUtil.getLog(Notifications05Utils.class);

	public void notifySubscribers(List<User> userList, long userId, long companyId, ServiceContext serviceContext,
			List<Messages> msgNotification) throws PortalException {

		/**
		 * Set up the WEBSITE TYPE custom notification by name
		 */

		msgNotification = MessagesLocalServiceUtil.findByNameCompany("notification-05", companyId);

		CustomNotification05Handler.setTitleKey(msgNotification.get(0).getSubject());
		CustomNotification05Handler.setBodyKey(msgNotification.get(0).getBody());

		/**
		 * Set up the EMAIL TYPE custom notification
		 */
		String entryTitle = "Change Proposal Approved Notification";

		String fromName = PropsUtil.get(CustomNotifications05Constants.EMAIL_FROM_NAME);
		String fromAddress = GetterUtil.getString(PropsUtil.get(CustomNotifications05Constants.EMAIL_FROM_ADDRESS),
				PropsUtil.get(PropsKeys.ADMIN_EMAIL_FROM_ADDRESS));

		CustomSubscription05Sender subscriptionSender = new CustomSubscription05Sender();

		subscriptionSender.setClassPK(0);
		subscriptionSender.setClassName(Notifications05Utils.class.getName());
		subscriptionSender.setCompanyId(companyId);

		subscriptionSender.setCurrentUserId(userId);
		subscriptionSender.setEntryTitle(entryTitle);
		subscriptionSender.setFrom(fromAddress, fromName);
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setSubject(msgNotification.get(0).getSubject());
		subscriptionSender.setBody(msgNotification.get(0).getBody());

		subscriptionSender.setMailId("custom_notitication05", 0);

		int notificationType = CustomNotification05Type.NOTIFICATION_TYPE_USER_05;

		subscriptionSender.setNotificationType(notificationType);

		String portletId = Notification05PortletKeys.NOTIFICATION05;

		subscriptionSender.setPortletId(portletId);

		subscriptionSender.setReplyToAddress(fromAddress);
		subscriptionSender.setServiceContext(serviceContext);

		subscriptionSender.addPersistedSubscribers(Notifications05Utils.class.getName(), 0);

		subscriptionSender.flushNotificationsAsync();

		try {

			for (User user : userList) {
				subscriptionSender.sendNotification5(user);
				
				logger.error("Stakeholder: " + user);
			}
					
			

		} catch (Exception e) {
			logger.error("Error sending the notification: " + e);
		}

	}

	/**
	 * Recipients who has an specific role
	 *
	 * @param companyId
	 * @param groupId
	 * @param List      rolenames
	 * @return the recipients that has an specific role
	 */
	public List<User> getRecipients(long companyId, long groupId, List<String> roleNames) {

		List<User> recipients = new ArrayList<>();

		try {
			for (String roleName : roleNames) {
				Role role = _roleLocalService.getRole(companyId, roleName);

				List<UserGroupRole> userGroupRoles = _userGroupRoleLocalService.getUserGroupRolesByGroupAndRole(groupId,
						role.getRoleId());

				for (UserGroupRole userGroupRole : userGroupRoles) {
					recipients.add(userGroupRole.getUser());
				}
			}

			if (recipients.isEmpty()) {
				Role role = _roleLocalService.getRole(companyId, RoleConstants.ADMINISTRATOR);

				recipients.addAll(_userLocalService.getRoleUsers(role.getRoleId()));
			}
		} catch (PortalException e) {
			logger.error("Error getting recipients", e);
		}

		return recipients;
	}

	/**** REFERENCES ****/

	private RoleLocalService _roleLocalService;

	@Reference(unbind = "-")
	protected void setRoleLocalService(RoleLocalService serv) {
		_roleLocalService = serv;
	}

	private UserLocalService _userLocalService;

	@Reference(unbind = "-")
	protected void setUserLocalService(UserLocalService serv) {
		_userLocalService = serv;
	}

	private UserGroupRoleLocalService _userGroupRoleLocalService;

	@Reference(unbind = "-")
	protected void setUserGroupRoleLocalService(UserGroupRoleLocalService serv) {
		_userGroupRoleLocalService = serv;
	}

}
