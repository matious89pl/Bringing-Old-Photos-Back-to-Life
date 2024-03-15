package com.everis.messages.service.builder.service.notification29.utils;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {}, service = Notifications29Utils.class)

public class Notifications29Utils {

	private static final Log logger = LogFactoryUtil.getLog(Notifications29Utils.class);

	public void notifySubscribers(List<User> userList, long userId, ServiceContext serviceContext,
			long resourcePrimaryKey, long siteGroupId) throws PortalException {

		/**
		 * Set up the WEBSITE TYPE custom notification by name
		 */

		String title = StringPool.BLANK, body = StringPool.BLANK, urlLink = StringPool.BLANK;
		try {

			JournalArticle journalArticle = JournalArticleLocalServiceUtil.fetchLatestArticle(resourcePrimaryKey);

			String siteName = GroupLocalServiceUtil.getGroup(siteGroupId).getDescriptiveName();

			char ch = '-';

			String siteNameLink = siteName.replace(' ', ch);

			String siteNameLinkToLowerCase = siteNameLink.toLowerCase();

			urlLink = "/group/"+siteNameLinkToLowerCase+"/-/".concat(journalArticle.getUrlTitle());

			String link = " <a style='color:#70ada3;' onclick='@LINK1'> Go to Voting Page </a>";

			title = "Vote Open - Please provide your vote ";
			body = "A new vote has been opened and you have been requested to participate. Please provide your vote here "
					+ link;

		} catch (Exception e1) {
			logger.error("Error notitication 29... " + e1);
		}

		/**
		 * Set up the EMAIL TYPE custom notification
		 */
		String entryTitle = title;

		String fromName = PropsUtil.get(CustomNotifications29Constants.EMAIL_FROM_NAME);
		String fromAddress = GetterUtil.getString(PropsUtil.get(CustomNotifications29Constants.EMAIL_FROM_ADDRESS),
				PropsUtil.get(PropsKeys.ADMIN_EMAIL_FROM_ADDRESS));

		CustomSubscription29Sender subscriptionSender = new CustomSubscription29Sender();

		Map<Locale, String> subjectMap = new HashMap<Locale, String>();
		subjectMap.put(LocaleUtil.ENGLISH, title);
		subscriptionSender.setLocalizedSubjectMap(subjectMap);

		Map<Locale, String> bodyMap = new HashMap<Locale, String>();
		bodyMap.put(LocaleUtil.ENGLISH, body);
		subscriptionSender.setLocalizedBodyMap(bodyMap);

		subscriptionSender.setEntryURL(urlLink);

		subscriptionSender.setClassPK(0);
		subscriptionSender.setClassName(Notifications29Utils.class.getName());
		subscriptionSender.setCompanyId(CompanyThreadLocal.getCompanyId());

		subscriptionSender.setCurrentUserId(userId);
		subscriptionSender.setEntryTitle(entryTitle);
		subscriptionSender.setFrom(fromAddress, fromName);
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setSubject(title);
		subscriptionSender.setBody(body);

		subscriptionSender.setMailId("custom_notitication29 to Change Managmenet Team", 0);

		int notificationType = CustomNotification29Type.NOTIFICATION_TYPE_USER_29;

		subscriptionSender.setNotificationType(notificationType);

		String portletId = Notification29PortletKeys.NOTIFICATION29;

		subscriptionSender.setPortletId(portletId);

		subscriptionSender.setReplyToAddress(fromAddress);
		subscriptionSender.setServiceContext(serviceContext);

		subscriptionSender.addPersistedSubscribers(Notifications29Utils.class.getName(), 0);

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
	public List<User> getRecipients(long groupId) {

		List<User> recipients = new ArrayList<>();

		List<User> users = UserLocalServiceUtil.getGroupUsers(groupId);

		for (User user : users) {
			recipients.add(user);

		}

		return recipients;
	}

}
