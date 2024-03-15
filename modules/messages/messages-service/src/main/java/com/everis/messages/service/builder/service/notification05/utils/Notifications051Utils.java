package com.everis.messages.service.builder.service.notification05.utils;

import com.everis.cproposal.model.recFormArticle;
import com.everis.cproposal.service.recFormArticleLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
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

@Component(immediate = true, property = {}, service = Notifications051Utils.class)

public class Notifications051Utils {

	private static final Log logger = LogFactoryUtil.getLog(Notifications051Utils.class);

	public void notifySubscribers(User user, long userId, long companyId, ServiceContext serviceContext, long classPK)
			throws PortalException {

		/**
		 * Set up the WEBSITE TYPE custom notification by name
		 */
		logger.info("classPK from notifySubscriber: " + classPK);
		recFormArticle article = recFormArticleLocalServiceUtil.getrecFormArticle(classPK);
		logger.info("article " + article);

		JournalArticle journalArticle = JournalArticleLocalServiceUtil.fetchLatestArticle((article.getArticleId()));
		logger.info("journalArticle " + journalArticle);

		Document document;
		String title = StringPool.BLANK, body = StringPool.BLANK, link = StringPool.BLANK;
		try {
			document = SAXReaderUtil.read(journalArticle.getContent());
			String nameCpReference = "ChangeProposalReference";
			Node nodeCpReference = document
					.selectSingleNode("//root//dynamic-element[@name='" + nameCpReference + "']//dynamic-content");
			String cpReference = nodeCpReference.getText();
			
			link = "/group/guest/-/".concat(journalArticle.getUrlTitle());
			String cpLink = "<a style='color:#70ada3;' onclick='@LINK1'> Go to Change Proposal Page</a>";

			title = "Your Change Proposal Form has been Accepted";
			body = cpReference + " has been accepted " + cpLink;
		
		} catch (DocumentException e1) {
			logger.error("Error reading journal article... " + e1);
		}

		/**
		 * Set up the EMAIL TYPE custom notification
		 */
		
		String entryTitle = title;

		String fromName = PropsUtil.get(CustomNotifications05Constants.EMAIL_FROM_NAME);
		String fromAddress = GetterUtil.getString(PropsUtil.get(CustomNotifications05Constants.EMAIL_FROM_ADDRESS),
				PropsUtil.get(PropsKeys.ADMIN_EMAIL_FROM_ADDRESS));

		CustomSubscription05Sender subscriptionSender = new CustomSubscription05Sender();
		
		Map<Locale, String> subjectMap = new HashMap<Locale, String>();
		subjectMap.put(LocaleUtil.ENGLISH, title);
		subscriptionSender.setLocalizedSubjectMap(subjectMap);
				
		Map<Locale, String> bodyMap = new HashMap<Locale, String>();
		bodyMap.put(LocaleUtil.ENGLISH, body);
		subscriptionSender.setLocalizedBodyMap(bodyMap);
		
		subscriptionSender.setEntryURL(link);

		subscriptionSender.setClassPK(0);
		subscriptionSender.setClassName(Notifications05Utils.class.getName());
		subscriptionSender.setCompanyId(companyId);

		subscriptionSender.setCurrentUserId(userId);
		subscriptionSender.setEntryTitle(entryTitle);
		subscriptionSender.setFrom(fromAddress, fromName);
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setSubject(title);
		subscriptionSender.setBody(body);

		subscriptionSender.setMailId("custom_notitication05", 0);

		int notificationType = CustomNotification05Type.NOTIFICATION_TYPE_USER_05;

		subscriptionSender.setNotificationType(notificationType);

		String portletId = Notification05PortletKeys.NOTIFICATION05;

		subscriptionSender.setPortletId(portletId);

		subscriptionSender.setReplyToAddress(fromAddress);
		subscriptionSender.setServiceContext(serviceContext);

		subscriptionSender.addPersistedSubscribers(Notifications051Utils.class.getName(), 0);

		subscriptionSender.flushNotificationsAsync();

		try {

			subscriptionSender.sendNotification5(user);

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
	public List<User> getRecipients(long companyId, long groupId, List<String> roleNames) {

		List<User> recipients = new ArrayList<>();

		try {
			Role role = RoleLocalServiceUtil.getRole(companyId, "REC_Stakeholder");
			recipients.addAll(UserLocalServiceUtil.getRoleUsers(role.getRoleId()));

		} catch (PortalException e) {
			logger.error("Error getting recipients", e);
		}

		return recipients;
	}

}
