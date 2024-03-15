package com.everis.messages.service.builder.service.notification02.utils;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroupRole;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {}, service = Notifications02Utils.class)

public class Notifications02Utils {

	private static final Log logger = LogFactoryUtil.getLog(Notifications02Utils.class);

	public void notifySubscribers(List<User> userList, long userId, long companyId, ServiceContext serviceContext,
			long adopterId, String cpReference) throws PortalException {

		logger.info("AdopterId - notifySubscribers... " + adopterId);
		User adopter = UserLocalServiceUtil.getUser(adopterId);
		String title = StringPool.BLANK, body = StringPool.BLANK;

		title = "Adopt Change Proposal Request";
		body = adopter.getEmailAddress() + " has requested to Adopt " + cpReference;

		/**
		 * Set up the EMAIL TYPE custom notification
		 */
		String entryTitle = title;

		String fromName = PropsUtil.get(CustomNotificationsConstants.EMAIL_FROM_NAME);
		String fromAddress = GetterUtil.getString(PropsUtil.get(CustomNotificationsConstants.EMAIL_FROM_ADDRESS),
				PropsUtil.get(PropsKeys.ADMIN_EMAIL_FROM_ADDRESS));

		CustomSubscriptionSender subscriptionSender = new CustomSubscriptionSender();

		Map<Locale, String> subjectMap = new HashMap<Locale, String>();
		subjectMap.put(LocaleUtil.ENGLISH, title);
		subscriptionSender.setLocalizedSubjectMap(subjectMap);

		Map<Locale, String> bodyMap = new HashMap<Locale, String>();
		bodyMap.put(LocaleUtil.ENGLISH, body);
		subscriptionSender.setLocalizedBodyMap(bodyMap);

		subscriptionSender.setClassPK(0);
		subscriptionSender.setClassName(Notifications02Utils.class.getName());
		subscriptionSender.setCompanyId(companyId);

		// subscriptionSender.setCurrentUserId(userId);
		subscriptionSender.setEntryTitle(entryTitle);
		subscriptionSender.setFrom(fromAddress, fromName);
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setSubject("Adopt Change Proposal Request");
		subscriptionSender.setBody(adopter.getEmailAddress() + " has requested to Adopt " + cpReference);

		subscriptionSender.setMailId("custom_notitication02", 0);

		int notificationType = CustomNotificationType.NOTIFICATION_TYPE_USER_02;

		subscriptionSender.setNotificationType(notificationType);

		String portletId = NotificationPortletKeys.NOTIFICATION02;

		subscriptionSender.setPortletId(portletId);

		subscriptionSender.setReplyToAddress(fromAddress);
		subscriptionSender.setServiceContext(serviceContext);

		subscriptionSender.addPersistedSubscribers(Notifications02Utils.class.getName(), 0);

		subscriptionSender.flushNotificationsAsync();

		try {

			for (User user : userList) {
				subscriptionSender.sendNotification(user);
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
				Role role = RoleLocalServiceUtil.getRole(companyId, roleName);
				logger.debug("4 role.... " + role);

				List<UserGroupRole> userGroupRoles = UserGroupRoleLocalServiceUtil.getUserGroupRolesByGroupAndRole(groupId,
						role.getRoleId());

				for (UserGroupRole userGroupRole : userGroupRoles) {
					recipients.add(userGroupRole.getUser());
					logger.debug("5 recipients.... " + userGroupRole.getUser().getEmailAddress());
				}
			}

			if (recipients.isEmpty()) {
				Role role = RoleLocalServiceUtil.getRole(companyId, "Change_Management_Team");

				recipients.addAll(UserLocalServiceUtil.getRoleUsers(role.getRoleId()));
			}
			logger.debug("6 recipients.... " + recipients);

		} catch (PortalException e) {
			logger.error("Error getting recipients", e);
		}

		return recipients;
	}

}
