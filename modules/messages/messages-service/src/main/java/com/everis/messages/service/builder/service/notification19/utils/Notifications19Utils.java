package com.everis.messages.service.builder.service.notification19.utils;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
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

@Component(immediate = true, property = {}, service = Notifications19Utils.class)

public class Notifications19Utils {

	private static final Log logger = LogFactoryUtil.getLog(Notifications19Utils.class);

	public void notifySubscribers(List<User> userList, long userId, long companyId, ServiceContext serviceContext) throws PortalException {

		/**
		 * Set up the WEBSITE TYPE custom notification by name
		 */

		String title = "Voluntary Withdrawal Requested";
		String body = "Voluntary Withdrawal has been requested";

		/**
		 * Set up the EMAIL TYPE custom notification
		 */
		String entryTitle = title;

		String fromName = PropsUtil.get(CustomNotifications19Constants.EMAIL_FROM_NAME);
		String fromAddress = GetterUtil.getString(PropsUtil.get(CustomNotifications19Constants.EMAIL_FROM_ADDRESS),
				PropsUtil.get(PropsKeys.ADMIN_EMAIL_FROM_ADDRESS));

		CustomSubscription19Sender subscriptionSender = new CustomSubscription19Sender();
		
		Map<Locale, String> subjectMap = new HashMap<Locale, String>();
		subjectMap.put(LocaleUtil.ENGLISH, title);
		subscriptionSender.setLocalizedSubjectMap(subjectMap);
				
		Map<Locale, String> bodyMap = new HashMap<Locale, String>();
		bodyMap.put(LocaleUtil.ENGLISH, body);
		subscriptionSender.setLocalizedBodyMap(bodyMap);

		subscriptionSender.setClassPK(0);
		subscriptionSender.setClassName(Notifications19Utils.class.getName());
		subscriptionSender.setCompanyId(companyId);

		subscriptionSender.setCurrentUserId(userId);
		subscriptionSender.setEntryTitle(entryTitle);
		subscriptionSender.setFrom(fromAddress, fromName);
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setSubject(title);
		subscriptionSender.setBody(body);

		subscriptionSender.setMailId("custom_notitication19 to Organization RTS and RPA", 0);

		int notificationType = CustomNotification19Type.NOTIFICATION_TYPE_USER_19;

		subscriptionSender.setNotificationType(notificationType);

		String portletId = Notification19PortletKeys.NOTIFICATION19;

		subscriptionSender.setPortletId(portletId);

		subscriptionSender.setReplyToAddress(fromAddress);
		subscriptionSender.setServiceContext(serviceContext);

		subscriptionSender.addPersistedSubscribers(Notifications19Utils.class.getName(), 0);

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
	public List<User> getRecipients(long companyId, long groupId) {

		List<User> recipients = new ArrayList<>();
		
		try {
			String[] orgNames = {"REC Technical Services", "REC Performance Assurance"};
			for (String orgName : orgNames) {
				Organization org = OrganizationLocalServiceUtil.getOrganization(companyId, orgName);
				List<User> users = UserLocalServiceUtil.getOrganizationUsers(org.getOrganizationId());
				for (User eachUser : users) {
					recipients.add(eachUser);
				}
			}
			
		} catch (PortalException e) {
			logger.error("Error getting recipients", e);
		}
		logger.info("Number of recipients: " + recipients.size());
		return recipients;
	}
}
