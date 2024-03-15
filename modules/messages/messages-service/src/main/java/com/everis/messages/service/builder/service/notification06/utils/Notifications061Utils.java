package com.everis.messages.service.builder.service.notification06.utils;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {}, service = Notifications061Utils.class)

public class Notifications061Utils {

	private static final Log logger = LogFactoryUtil.getLog(Notifications061Utils.class);

	public void notifySubscribers(User user, String comments, long userId, long companyId, ServiceContext serviceContext,
			String knowledgeRepositoryURL, String raiseTicketURL, long classPK) throws PortalException {

		/**
		 * Set up the WEBSITE TYPE custom notification by name
		 */

		String knowledgeRepository = "<a class='notification061knowledgeRepositoryLink' style='color:#70ada3;' onclick='@LINK1'> Reasons for rejection";

		String raiseTicket = "<a style='color:#70ada3;' onclick='@LINK2'> Knowledge and Service Desk.";

		String title = "Change Proposal Page Rejected";
		
//		String body = "To better understand your options for a rejected change proposal, please read "
//				+ knowledgeRepository + " in the knowledge repository. "
//				+ "If you want to discuss the rejection with a member of the Change Management Team, please raise a service request with the helpdesk at "
//				+ raiseTicket + " .";
		
		String body = "Your Change Proposal submission has been rejected for the following reason(s) " + comments + "." + " To better understand your options for a rejected change proposal, please read " + knowledgeRepository + raiseTicket + " .";

		/**
		 * Set up the EMAIL TYPE custom notification
		 */
		String entryTitle = title;

		String fromName = PropsUtil.get(CustomNotifications06Constants.EMAIL_FROM_NAME);
		String fromAddress = GetterUtil.getString(PropsUtil.get(CustomNotifications06Constants.EMAIL_FROM_ADDRESS),
				PropsUtil.get(PropsKeys.ADMIN_EMAIL_FROM_ADDRESS));

		CustomSubscription06Sender subscriptionSender = new CustomSubscription06Sender();

		Map<Locale, String> subjectMap = new HashMap<Locale, String>();
		subjectMap.put(LocaleUtil.ENGLISH, title);
		subscriptionSender.setLocalizedSubjectMap(subjectMap);

		Map<Locale, String> bodyMap = new HashMap<Locale, String>();
		bodyMap.put(LocaleUtil.ENGLISH, body);
		subscriptionSender.setLocalizedBodyMap(bodyMap);

		String entryURLS = knowledgeRepositoryURL.concat(";spc;").concat(raiseTicketURL);
		subscriptionSender.setEntryURL(entryURLS);

		subscriptionSender.setClassPK(0);
		subscriptionSender.setClassName(Notifications06Utils.class.getName());
		subscriptionSender.setCompanyId(companyId);

		subscriptionSender.setCurrentUserId(userId);
		subscriptionSender.setEntryTitle(entryTitle);
		subscriptionSender.setFrom(fromAddress, fromName);
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setSubject(title);
		subscriptionSender.setBody(body);

		subscriptionSender.setMailId("custom_notitication06", 0);

		int notificationType = CustomNotification06Type.NOTIFICATION_TYPE_USER_06;

		subscriptionSender.setNotificationType(notificationType);

		String portletId = Notification06PortletKeys.NOTIFICATION06;

		subscriptionSender.setPortletId(portletId);

		subscriptionSender.setReplyToAddress(fromAddress);
		subscriptionSender.setServiceContext(serviceContext);

		subscriptionSender.addPersistedSubscribers(Notifications061Utils.class.getName(), 0);

		subscriptionSender.flushNotificationsAsync();

		try {

			subscriptionSender.sendNotification6(user);

		} catch (Exception e) {
			logger.error("Error sending the notification: " + e);
		}

	}

}
