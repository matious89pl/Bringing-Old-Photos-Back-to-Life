package com.everis.messages.service.builder.service.notification09.utils;

import com.everis.messages.service.builder.model.Messages;
import com.everis.messages.service.builder.service.MessagesLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {}, service = Notifications091Utils.class)

public class Notifications091Utils {

	private static final Log logger = LogFactoryUtil.getLog(Notifications091Utils.class);

	public void notifySubscribers(User user, long userId, long companyId, ServiceContext serviceContext,
			List<Messages> msgNotification, String urlLinkToPage) throws PortalException {

		/**
		 * Set up the WEBSITE TYPE custom notification by name
		 */

		String html = " <a id=\"changeProposal05\" href=\"" + urlLinkToPage + "\" target=\"_blank\"\r\n"
				+ "      >Liferay</a\r\n" + "    >";
		Document link = (Document) Jsoup.parse(html);

		msgNotification = MessagesLocalServiceUtil.findByNameCompany("notification-05", companyId);

		CustomNotification09Handler.setTitleKey(msgNotification.get(0).getSubject());
		CustomNotification09Handler.setBodyKey(msgNotification.get(0).getBody() + " " + link);

		/**
		 * Set up the EMAIL TYPE custom notification
		 */
		String entryTitle = "Change Proposal Approved Notification";

		String fromName = PropsUtil.get(CustomNotifications09Constants.EMAIL_FROM_NAME);
		String fromAddress = GetterUtil.getString(PropsUtil.get(CustomNotifications09Constants.EMAIL_FROM_ADDRESS),
				PropsUtil.get(PropsKeys.ADMIN_EMAIL_FROM_ADDRESS));

		CustomSubscription09Sender subscriptionSender = new CustomSubscription09Sender();

		subscriptionSender.setClassPK(0);
		subscriptionSender.setClassName(Notifications09Utils.class.getName());
		subscriptionSender.setCompanyId(companyId);

		subscriptionSender.setCurrentUserId(userId);
		subscriptionSender.setEntryTitle(entryTitle);
		subscriptionSender.setFrom(fromAddress, fromName);
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setSubject(msgNotification.get(0).getSubject());
		subscriptionSender.setBody(msgNotification.get(0).getBody() + " " + link);

		subscriptionSender.setMailId("custom_notitication05", 0);

		int notificationType = CustomNotification09Type.NOTIFICATION_TYPE_USER_09;

		subscriptionSender.setNotificationType(notificationType);

		String portletId = Notification09PortletKeys.NOTIFICATION09;

		subscriptionSender.setPortletId(portletId);

		subscriptionSender.setReplyToAddress(fromAddress);
		subscriptionSender.setServiceContext(serviceContext);

		subscriptionSender.addPersistedSubscribers(Notifications091Utils.class.getName(), 0);

		subscriptionSender.flushNotificationsAsync();

		try {

			subscriptionSender.sendNotification9(user);

		} catch (Exception e) {
			logger.error("Error sending the notification: " + e);
		}

	}

}
