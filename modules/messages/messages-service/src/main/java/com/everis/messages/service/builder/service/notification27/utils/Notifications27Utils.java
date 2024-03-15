package com.everis.messages.service.builder.service.notification27.utils;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
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

@Component(immediate = true, property = {}, service = Notifications27Utils.class)

public class Notifications27Utils {

	private static final Log logger = LogFactoryUtil.getLog(Notifications27Utils.class);

	public void notifySubscribers(HashSet<User> userList, long userId, long resourcePrimaryKey, long siteGroupId,
			ServiceContext serviceContext) throws PortalException {

		logger.info("Notify Subscribers - Notification27 - resourcePrimaryKey " + resourcePrimaryKey);
		JournalArticle journalArticle = JournalArticleLocalServiceUtil.fetchLatestArticle(resourcePrimaryKey);

		String title = StringPool.BLANK, body = StringPool.BLANK, urlLink = StringPool.BLANK;
		try {
			String siteName = GroupLocalServiceUtil.getGroup(siteGroupId).getDescriptiveName();
			logger.info("siteName... " + siteName);

			char ch = '-';
			logger.info("ch... " + ch);

			String siteNameLink = siteName.replace(' ', ch);
			logger.info("siteNameLink... " + siteNameLink);

			String siteNameLinkToLowerCase = siteNameLink.toLowerCase();
			logger.info("siteNameLinkToLowerCase... " + siteNameLinkToLowerCase);
			logger.info("UrlTitle... " + journalArticle.getUrlTitle());

			urlLink = "/web/" + siteNameLinkToLowerCase + "/-/".concat(journalArticle.getUrlTitle());
			logger.info("urlLink... " + urlLink);
			String titleNomination = getNodeText(journalArticle, "TitleNomination");
			logger.info("titleNomination... " + titleNomination);

			String link = " <a href='manage?p_p_id=com_liferay_portal_workflow_task_web_portlet_MyWorkflowTaskPortlet&p_p_lifecycle=0' style='color:#70ada3;' onclick='@LINK1'> "
					+ titleNomination + " </a>";
			logger.info("link... " + link);

			title = "Nomination Received";
			body = "Nomination received for " + link + " please validate nomination.";

		} catch (Exception e1) {
			logger.error("Error notitication 27... " + e1);
		}

		/**
		 * Set up the EMAIL TYPE custom notification
		 */
		String entryTitle = title;

		String fromName = PropsUtil.get(CustomNotifications27Constants.EMAIL_FROM_NAME);
		String fromAddress = GetterUtil.getString(PropsUtil.get(CustomNotifications27Constants.EMAIL_FROM_ADDRESS),
				PropsUtil.get(PropsKeys.ADMIN_EMAIL_FROM_ADDRESS));

		CustomSubscription27Sender subscriptionSender = new CustomSubscription27Sender();

		Map<Locale, String> subjectMap = new HashMap<Locale, String>();
		subjectMap.put(LocaleUtil.ENGLISH, title);
		subscriptionSender.setLocalizedSubjectMap(subjectMap);

		Map<Locale, String> bodyMap = new HashMap<Locale, String>();
		bodyMap.put(LocaleUtil.ENGLISH, body);
		subscriptionSender.setLocalizedBodyMap(bodyMap);

		subscriptionSender.setEntryURL(urlLink);

		subscriptionSender.setClassPK(0);
		subscriptionSender.setClassName(Notifications27Utils.class.getName());
		subscriptionSender.setCompanyId(CompanyThreadLocal.getCompanyId());

		subscriptionSender.setCurrentUserId(userId);
		subscriptionSender.setEntryTitle(entryTitle);
		subscriptionSender.setFrom(fromAddress, fromName);
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setSubject(title);
		subscriptionSender.setBody(body);

		subscriptionSender.setMailId("custom_notitication27 to Responsible Service Provider(organisation)", 0);

		int notificationType = CustomNotification27Type.NOTIFICATION_TYPE_USER_27;

		subscriptionSender.setNotificationType(notificationType);

		String portletId = Notification27PortletKeys.NOTIFICATION27;

		subscriptionSender.setPortletId(portletId);

		subscriptionSender.setReplyToAddress(fromAddress);
		subscriptionSender.setServiceContext(serviceContext);

		subscriptionSender.addPersistedSubscribers(Notifications27Utils.class.getName(), 0);

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
	public HashSet<User> getRecipients() {

		HashSet<User> recipients = new HashSet<>();

		try {

			if (recipients.isEmpty()) {
				Role role = RoleLocalServiceUtil.getRole(CompanyThreadLocal.getCompanyId(),
						"Committee_Secretariat_Admi");

				recipients.addAll(UserLocalServiceUtil.getRoleUsers(role.getRoleId()));
			}
			logger.debug("6 recipients.... " + recipients);

		} catch (PortalException e) {
			logger.error("Error getting recipients", e);
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
