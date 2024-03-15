package com.everis.messages.service.builder.service.notification32.utils;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.petra.string.StringPool;
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

@Component(immediate = true, property = {}, service = Notifications32Utils.class)

public class Notifications32Utils {

	private static final Log logger = LogFactoryUtil.getLog(Notifications32Utils.class);

	public void notifySubscribers(List<User> userList, long userId, long companyId, ServiceContext serviceContext,
			long resourcePrimaryKey) throws PortalException {

		/**
		 * Set up the WEBSITE TYPE custom notification by name
		 */

		JournalArticle journalArticle = JournalArticleLocalServiceUtil.fetchLatestArticle(resourcePrimaryKey);
		Document document;
		String title = StringPool.BLANK, body = StringPool.BLANK, urlLink = StringPool.BLANK;
		try {
			document = SAXReaderUtil.read(journalArticle.getContent());
			String nameCpReference = "IA_CompanyRegistrationNumber";
			Node nodeCpReference = document
					.selectSingleNode("//root//dynamic-element[@name='" + nameCpReference + "']//dynamic-content");
			String partyReference = nodeCpReference.getText();

			urlLink = "/group/guest/-/".concat(journalArticle.getUrlTitle());
			String link = " <a style='color:#70ada3;' onclick='@LINK1'> Go to Party Management</a>";

			title = "A New Party Management is available";
			body = "A New Party Management is now available to view " + link;

		} catch (DocumentException e1) {
			logger.error("Error reading journal article... " + e1);
		}

		/**
		 * Set up the EMAIL TYPE custom notification
		 */
		String entryTitle = title;

		String fromName = PropsUtil.get(CustomNotifications32Constants.EMAIL_FROM_NAME);
		String fromAddress = GetterUtil.getString(PropsUtil.get(CustomNotifications32Constants.EMAIL_FROM_ADDRESS),
				PropsUtil.get(PropsKeys.ADMIN_EMAIL_FROM_ADDRESS));

		CustomSubscription32Sender subscriptionSender = new CustomSubscription32Sender();

		Map<Locale, String> subjectMap = new HashMap<Locale, String>();
		subjectMap.put(LocaleUtil.ENGLISH, title);
		subscriptionSender.setLocalizedSubjectMap(subjectMap);

		Map<Locale, String> bodyMap = new HashMap<Locale, String>();
		bodyMap.put(LocaleUtil.ENGLISH, body);
		subscriptionSender.setLocalizedBodyMap(bodyMap);

		subscriptionSender.setEntryURL(urlLink);

		subscriptionSender.setClassPK(0);
		subscriptionSender.setClassName(Notifications32Utils.class.getName());
		subscriptionSender.setCompanyId(companyId);

		subscriptionSender.setCurrentUserId(userId);
		subscriptionSender.setEntryTitle(entryTitle);
		subscriptionSender.setFrom(fromAddress, fromName);
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setSubject(title);
		subscriptionSender.setBody(body);

		subscriptionSender.setMailId("custom_notitication32 to Responsible Service Provider(organisation)", 0);

		int notificationType = CustomNotification32Type.NOTIFICATION_TYPE_USER_32;

		subscriptionSender.setNotificationType(notificationType);

		String portletId = Notification32PortletKeys.NOTIFICATION32;

		subscriptionSender.setPortletId(portletId);

		subscriptionSender.setReplyToAddress(fromAddress);
		subscriptionSender.setServiceContext(serviceContext);

		subscriptionSender.addPersistedSubscribers(Notifications32Utils.class.getName(), 0);

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
			String[] orgNames = { "REC Performance Assurance" };
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
