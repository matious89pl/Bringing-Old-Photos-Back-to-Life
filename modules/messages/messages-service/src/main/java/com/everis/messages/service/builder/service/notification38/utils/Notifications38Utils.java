package com.everis.messages.service.builder.service.notification38.utils;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
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

@Component(immediate = true, property = {}, service = Notifications38Utils.class)

public class Notifications38Utils {

	private static final Log logger = LogFactoryUtil.getLog(Notifications38Utils.class);

	public void notifySubscribers(List<User> userList, long userId, long companyId, ServiceContext serviceContext,
			long resourcePrimaryKey, long groupId) throws PortalException {

		/**
		 * Set up the WEBSITE TYPE custom notification by name
		 */

		JournalArticle journalArticle = JournalArticleLocalServiceUtil.fetchLatestArticle(resourcePrimaryKey);
		Group group = GroupLocalServiceUtil.fetchGroup(groupId);
		Document document;
		String title = StringPool.BLANK, body = StringPool.BLANK, urlLink = StringPool.BLANK;
		try {
			document = SAXReaderUtil.read(journalArticle.getContent());
			String nameCpReference = "ID_ActionLog";
			Node nodeCpReference = document
					.selectSingleNode("//root//dynamic-element[@name='" + nameCpReference + "']//dynamic-content");
			String ActionLogTitle = journalArticle.getTitle();

			urlLink = "/group".concat(group.getFriendlyURL()).concat("/action-log");
			logger.info("urlLink: " + urlLink);
			String link = " <a style='color:#70ada3;' onclick='@LINK1'> Go to Action Log</a>";

			title = "A New Action Log is available: " + ActionLogTitle;
			body = "A New Action Log is now available to view " + link;

		} catch (DocumentException e1) {
			logger.error("Error reading journal article... " + e1);
		}

		/**
		 * Set up the EMAIL TYPE custom notification
		 */
		String entryTitle = title;

		String fromName = PropsUtil.get(CustomNotifications38Constants.EMAIL_FROM_NAME);
		String fromAddress = GetterUtil.getString(PropsUtil.get(CustomNotifications38Constants.EMAIL_FROM_ADDRESS),
				PropsUtil.get(PropsKeys.ADMIN_EMAIL_FROM_ADDRESS));

		CustomSubscription38Sender subscriptionSender = new CustomSubscription38Sender();

		Map<Locale, String> subjectMap = new HashMap<Locale, String>();
		subjectMap.put(LocaleUtil.ENGLISH, title);
		subscriptionSender.setLocalizedSubjectMap(subjectMap);

		Map<Locale, String> bodyMap = new HashMap<Locale, String>();
		bodyMap.put(LocaleUtil.ENGLISH, body);
		subscriptionSender.setLocalizedBodyMap(bodyMap);

		subscriptionSender.setEntryURL(urlLink);

		subscriptionSender.setClassPK(0);
		subscriptionSender.setClassName(Notifications38Utils.class.getName());
		subscriptionSender.setCompanyId(companyId);

		subscriptionSender.setCurrentUserId(userId);
		subscriptionSender.setEntryTitle(entryTitle);
		subscriptionSender.setFrom(fromAddress, fromName);
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setSubject(title);
		subscriptionSender.setBody(body);

		subscriptionSender.setMailId("custom_notitication38 to Responsible Service Provider(organisation)", 0);

		int notificationType = CustomNotification38Type.NOTIFICATION_TYPE_USER_38;

		subscriptionSender.setNotificationType(notificationType);

		String portletId = Notification38PortletKeys.NOTIFICATION38;

		subscriptionSender.setPortletId(portletId);

		subscriptionSender.setReplyToAddress(fromAddress);
		subscriptionSender.setServiceContext(serviceContext);

		subscriptionSender.addPersistedSubscribers(Notifications38Utils.class.getName(), 0);

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
	public List<User> getRecipients(long companyId, long groupId, long resourcePrimaryKey) {
		
		JournalArticle journalArticle = JournalArticleLocalServiceUtil.fetchLatestArticle(resourcePrimaryKey);
		Document document;
		
		List<User> recipients = new ArrayList<>();

		try {
			document = SAXReaderUtil.read(journalArticle.getContent());
			String emailAssigner = "ActionLog_AssigneeEmail";
			Node nodeAssigner = document
					.selectSingleNode("//root//dynamic-element[@name='" + emailAssigner + "']//dynamic-content");
			String userEmail = nodeAssigner.getText();
			User userAssigner = UserLocalServiceUtil.getUserByEmailAddress(CompanyThreadLocal.getCompanyId(), userEmail);
			recipients.add(userAssigner);

		} catch (Exception e) {
			logger.error("Error getting recipients", e);
		}
		logger.info("Number of recipients: " + recipients.size());
		return recipients;
	}
}
