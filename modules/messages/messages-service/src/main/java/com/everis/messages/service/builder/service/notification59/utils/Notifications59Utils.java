package com.everis.messages.service.builder.service.notification59.utils;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {}, service = Notifications59Utils.class)

public class Notifications59Utils {

	private static final Log logger = LogFactoryUtil.getLog(Notifications59Utils.class);

	public void notifySubscribers(HashSet<User> userList, ServiceContext serviceContext, long currentUserId,
			long siteGroupId, String rfiTitle, String urlEmail) throws PortalException {

		String title = StringPool.BLANK, body = StringPool.BLANK, bodyEmail = StringPool.BLANK;

		char ch = '-';

		String siteName = GroupLocalServiceUtil.getGroup(siteGroupId).getDescriptiveName();
		String siteNameLink = siteName.replace(' ', ch);

		String siteNameLinkToLowerCase = siteNameLink.toLowerCase();

		String urlLink = "/group/" + siteNameLinkToLowerCase;
		String link = " <a style='color:#70ada3;' onclick='@LINK1'> performance assurance area </a>";

		logger.debug("Subject + Body: " + title + " - " + body);

		title = "RFi Log - Document Added by REC Party User";
		body = "A document has been uploaded to " + rfiTitle + " within " + siteName + " " + link;

		String linkEmail = "<a style='color:#70ada3' href='" + HtmlUtil.escape(urlEmail.concat(siteNameLinkToLowerCase))
				+ "'>" + HtmlUtil.escape("performance assurance area") + "</a>";

		bodyEmail = "A document has been uploaded to " + rfiTitle + " within " + siteName + " " + linkEmail;

		/**
		 * Set up the EMAIL TYPE custom notification
		 */
		String entryTitle = title;

		String fromName = PropsUtil.get(CustomNotifications59Constants.EMAIL_FROM_NAME);
		String fromAddress = GetterUtil.getString(PropsUtil.get(CustomNotifications59Constants.EMAIL_FROM_ADDRESS),
				PropsUtil.get(PropsKeys.ADMIN_EMAIL_FROM_ADDRESS));

		CustomSubscription59Sender subscriptionSender = new CustomSubscription59Sender();

		Map<Locale, String> subjectMap = new HashMap<Locale, String>();
		subjectMap.put(LocaleUtil.ENGLISH, title);
		subscriptionSender.setLocalizedSubjectMap(subjectMap);

		Map<Locale, String> bodyMap = new HashMap<Locale, String>();
		bodyMap.put(LocaleUtil.ENGLISH, body);
		subscriptionSender.setLocalizedBodyMap(bodyMap);

		subscriptionSender.setEntryURL(urlLink);

		subscriptionSender.setClassPK(0);
		subscriptionSender.setClassName(Notifications59Utils.class.getName());
		subscriptionSender.setCompanyId(CompanyThreadLocal.getCompanyId());

		subscriptionSender.setCurrentUserId(currentUserId);
		subscriptionSender.setEntryTitle(entryTitle);
		subscriptionSender.setFrom(fromAddress, fromName);
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setSubject(title);
		subscriptionSender.setBody(bodyEmail);

		subscriptionSender.setMailId("user", UserLocalServiceUtil.getUser(currentUserId));

		subscriptionSender.addRuntimeSubscribers(UserLocalServiceUtil.getUser(currentUserId).getEmailAddress(),
				UserLocalServiceUtil.getUser(currentUserId).getFullName());

		int notificationType = CustomNotification59Type.NOTIFICATION_TYPE_USER_59;

		subscriptionSender.setNotificationType(notificationType);

		String portletId = Notification59PortletKeys.NOTIFICATION59;

		subscriptionSender.setPortletId(portletId);

		subscriptionSender.setReplyToAddress(fromAddress);
		subscriptionSender.setServiceContext(serviceContext);

		subscriptionSender.addPersistedSubscribers(Notifications59Utils.class.getName(), 0);

		/*
		 * subscriptionSender.flushNotificationsAsync();
		 */

		try {

			for (User user : userList) {

				subscriptionSender.sendNotification(user);

			}

		} catch (Exception e) {
			logger.error("Error sending the notification: " + e);
		}

	}

	/**
	 * Recipients who has an specific Segments
	 * 
	 * @param List Users than belong to a Segment
	 * @return the recipients that has an specific Segments
	 */
	public HashSet<User> getRecipients(long organisationId, long siteGroupId, long companyId) {

		HashSet<User> recipients = new HashSet<User>();

		try {

			Role role = RoleLocalServiceUtil.getRole(companyId, "RPA");
			recipients.addAll(UserLocalServiceUtil.getRoleUsers(role.getRoleId()));

		} catch (PortalException e) {
			logger.error("Error getting recipients", e);
		}

		return recipients;
	}
}
