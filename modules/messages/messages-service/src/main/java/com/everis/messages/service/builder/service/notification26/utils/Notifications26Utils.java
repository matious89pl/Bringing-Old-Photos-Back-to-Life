package com.everis.messages.service.builder.service.notification26.utils;

import com.everis.messages.service.builder.service.utils.NotificationUtils;
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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {}, service = Notifications26Utils.class)

public class Notifications26Utils {

	private static final Log logger = LogFactoryUtil.getLog(Notifications26Utils.class);

	public void notifySubscribers(HashSet<User> userList, long userId, long classPK, long resourcePrimKey,
			long siteGroupId, ServiceContext serviceContext) throws PortalException, DocumentException {

		String title = StringPool.BLANK, body = StringPool.BLANK, urlLink = StringPool.BLANK;

		try {
			String nominatorName = NotificationUtils.getNominatorNamefromDDL(classPK);
			String nomineeName = NotificationUtils.getNomineeNamefromDDL(classPK);

			JournalArticle journalArticle = JournalArticleLocalServiceUtil.fetchLatestArticle(resourcePrimKey);
			String TitleOfNomination = getNodeText(journalArticle, "TitleNomination");

			String siteName = GroupLocalServiceUtil.getGroup(siteGroupId).getDescriptiveName();
			logger.info("siteName... " + siteName);

			char ch = '-';

			String siteNameLink = siteName.replace(' ', ch);

			String siteNameLinkToLowerCase = siteNameLink.toLowerCase();

			urlLink = "/web/" + siteNameLinkToLowerCase + "/-/".concat(journalArticle.getUrlTitle());
			/**
			 * Set up the WEBSITE TYPE custom notification by name
			 */

			title = "Nomination Result - Invalid";
			body = "We have received the nomination made by " + nominatorName + " for " + nomineeName + " under the "
					+ TitleOfNomination + ". On this occasion your nomination is Invalid.";
		} catch (Exception e1) {
			logger.error("Error notitication 26... " + e1);
		}

		/**
		 * Set up the EMAIL TYPE custom notification
		 */
		String entryTitle = title;

		String fromName = PropsUtil.get(CustomNotifications26Constants.EMAIL_FROM_NAME);
		String fromAddress = GetterUtil.getString(PropsUtil.get(CustomNotifications26Constants.EMAIL_FROM_ADDRESS),
				PropsUtil.get(PropsKeys.ADMIN_EMAIL_FROM_ADDRESS));

		CustomSubscription26Sender subscriptionSender = new CustomSubscription26Sender();

		Map<Locale, String> subjectMap = new HashMap<Locale, String>();
		subjectMap.put(LocaleUtil.ENGLISH, title);
		subscriptionSender.setLocalizedSubjectMap(subjectMap);

		Map<Locale, String> bodyMap = new HashMap<Locale, String>();
		bodyMap.put(LocaleUtil.ENGLISH, body);
		subscriptionSender.setLocalizedBodyMap(bodyMap);

		subscriptionSender.setEntryURL(urlLink);

		subscriptionSender.setClassPK(0);
		subscriptionSender.setClassName(Notifications26Utils.class.getName());
		subscriptionSender.setCompanyId(CompanyThreadLocal.getCompanyId());

		subscriptionSender.setCurrentUserId(userId);
		subscriptionSender.setEntryTitle(entryTitle);
		subscriptionSender.setFrom(fromAddress, fromName);
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setSubject(title);
		subscriptionSender.setBody(body);

		subscriptionSender.setMailId("custom_notitication26 to Responsible Service Provider(organisation)", 0);

		int notificationType = CustomNotification26Type.NOTIFICATION_TYPE_USER_26;

		subscriptionSender.setNotificationType(notificationType);

		String portletId = Notification26PortletKeys.NOTIFICATION26;

		subscriptionSender.setPortletId(portletId);

		subscriptionSender.setReplyToAddress(fromAddress);
		subscriptionSender.setServiceContext(serviceContext);

		subscriptionSender.addPersistedSubscribers(Notifications26Utils.class.getName(), 0);

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
	 * @param nomineeEmailAddress
	 * @return user
	 */
	public HashSet<User> getRecipients(String nomineeEmailAddress, String nominatorEmailAddress) {

		HashSet<User> recipients = new HashSet<User>();

		try {
			User userNominee = UserLocalServiceUtil.getUserByEmailAddress(CompanyThreadLocal.getCompanyId(),
					nomineeEmailAddress);
			User userNaminator = UserLocalServiceUtil.getUserByEmailAddress(CompanyThreadLocal.getCompanyId(),
					nominatorEmailAddress);
			recipients.add(userNominee);
			recipients.add(userNaminator);

		} catch (PortalException e) {
			logger.error("Error getting user by email address");
		}
		return recipients;
	}

	private String getNodeText(JournalArticle model, String fieldName) throws DocumentException {
		logger.trace("model: " + model.getArticleId());
		logger.trace("getContent: " + model.getContent());
		Document document = SAXReaderUtil.read(model.getContent());
		logger.trace("document: " + document.getText());
		Node node = document.selectSingleNode("//root//dynamic-element[@name='" + fieldName + "']//dynamic-content");
		if (node == null) {
			logger.debug("There is no field on the structure for the following fieldName");
			return "";
		} else {
			logger.debug("node.getText(): " + node.getText());
			return node.getText();
		}
	}
}
