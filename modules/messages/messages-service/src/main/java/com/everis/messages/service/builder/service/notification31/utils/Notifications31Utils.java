package com.everis.messages.service.builder.service.notification31.utils;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
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

@Component(immediate = true, property = {}, service = Notifications31Utils.class)

public class Notifications31Utils {

	private static final Log logger = LogFactoryUtil.getLog(Notifications31Utils.class);

	public void notifySubscribers(List<User> userList, long userId, long companyId, ServiceContext serviceContext,
			String VotingLink,String titleOfNotification, String bodyOfNotification) throws PortalException {

		/**
		 * Set up the WEBSITE TYPE custom notification by name
		 */

		String title = StringPool.BLANK, body = StringPool.BLANK, urlLink = StringPool.BLANK;
		try {
			urlLink = VotingLink;
			String link = " <a style='color:#70ada3;' onclick='@LINK1'> Voting Page</a>";

			title = titleOfNotification;
			body = bodyOfNotification + ". Go to " + link;

		} catch (Exception e1) {
			logger.error("Error notitication 31... " + e1);
		}

		/**
		 * Set up the EMAIL TYPE custom notification
		 */
		String entryTitle = title;

		String fromName = PropsUtil.get(CustomNotifications31Constants.EMAIL_FROM_NAME);
		String fromAddress = GetterUtil.getString(PropsUtil.get(CustomNotifications31Constants.EMAIL_FROM_ADDRESS),
				PropsUtil.get(PropsKeys.ADMIN_EMAIL_FROM_ADDRESS));

		CustomSubscription31Sender subscriptionSender = new CustomSubscription31Sender();

		Map<Locale, String> subjectMap = new HashMap<Locale, String>();
		subjectMap.put(LocaleUtil.ENGLISH, title);
		subscriptionSender.setLocalizedSubjectMap(subjectMap);

		Map<Locale, String> bodyMap = new HashMap<Locale, String>();
		bodyMap.put(LocaleUtil.ENGLISH, body);
		subscriptionSender.setLocalizedBodyMap(bodyMap);

		subscriptionSender.setEntryURL(urlLink);

		subscriptionSender.setClassPK(0);
		subscriptionSender.setClassName(Notifications31Utils.class.getName());
		subscriptionSender.setCompanyId(companyId);

		subscriptionSender.setCurrentUserId(userId);
		subscriptionSender.setEntryTitle(entryTitle);
		subscriptionSender.setFrom(fromAddress, fromName);
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setSubject(title);
		subscriptionSender.setBody(body);

		subscriptionSender.setMailId("custom_notitication31 to Change Managmenet Team", 0);

		int notificationType = CustomNotification31Type.NOTIFICATION_TYPE_USER_31;

		subscriptionSender.setNotificationType(notificationType);

		String portletId = Notification31PortletKeys.NOTIFICATION31;

		subscriptionSender.setPortletId(portletId);

		subscriptionSender.setReplyToAddress(fromAddress);
		subscriptionSender.setServiceContext(serviceContext);

		subscriptionSender.addPersistedSubscribers(Notifications31Utils.class.getName(), 0);

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
	public List<User> getRecipients( long groupId) {

		List<User> recipients = new ArrayList<>();

		List<User> users = UserLocalServiceUtil.getGroupUsers(groupId);
		
		for (User user: users) {
			recipients.add(user);
			
		}

		return recipients;
	}

}
