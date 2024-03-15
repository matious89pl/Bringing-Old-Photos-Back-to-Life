package com.everis.messages.service.builder.service.notification15.utils;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroupRole;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil;
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

@Component(immediate = true, property = {}, service = Notifications15Utils.class)

public class Notifications15Utils {

	private static final Log logger = LogFactoryUtil.getLog(Notifications15Utils.class);

	public void notifySubscribers(List<User> userList, long companyId, ServiceContext serviceContext, String cpReference, String commentsDeadline, long resourcePrimKey,
			String email) {

		logger.debug("articleId: " + resourcePrimKey);
		JournalArticle journalArticle = JournalArticleLocalServiceUtil.fetchLatestArticle(resourcePrimKey);
		logger.debug("journalArticle: " + journalArticle);
		String title = StringPool.BLANK, body = StringPool.BLANK, urlLink = StringPool.BLANK;
		try {
			Document document = SAXReaderUtil.read(journalArticle.getContent());
			String cpIALink = "IA_LinkToCPPage";
			Node nodeLink = document
					.selectSingleNode("//root//dynamic-element[@name='" + cpIALink + "']//dynamic-content");
			
			urlLink = nodeLink.getText();
			String link = " <a style='color:#70ada3;' onclick='@LINK1'> Go to Change Proposal Page</a>";

			String ia_Type = "IA_Type";
			Node nodeIaType = document
					.selectSingleNode("//root//dynamic-element[@name='" + ia_Type + "']//dynamic-content");
			String getIaType = nodeIaType.getText();

			logger.debug("before handler: ");

			title = "A completed Impact Assessment has been received";
			body = "Impact Assessment related to " + cpReference + " has been received " + link;
			

		} catch (DocumentException e1) {
			logger.error("Error reading journal article... " + e1);
		}

		/**
		 * Set up the EMAIL TYPE custom notification
		 */
		String entryTitle = title;

		String fromName = PropsUtil.get(CustomNotifications15Constants.EMAIL_FROM_NAME);
		String fromAddress = GetterUtil.getString(PropsUtil.get(CustomNotifications15Constants.EMAIL_FROM_ADDRESS),
				PropsUtil.get(PropsKeys.ADMIN_EMAIL_FROM_ADDRESS));

		CustomSubscription15Sender subscriptionSender = new CustomSubscription15Sender();

		Map<Locale, String> subjectMap = new HashMap<Locale, String>();
		subjectMap.put(LocaleUtil.ENGLISH, title);
		subscriptionSender.setLocalizedSubjectMap(subjectMap);

		Map<Locale, String> bodyMap = new HashMap<Locale, String>();
		bodyMap.put(LocaleUtil.ENGLISH, body);
		subscriptionSender.setLocalizedBodyMap(bodyMap);
		
		subscriptionSender.setEntryURL(urlLink);

		subscriptionSender.setClassPK(0);
		subscriptionSender.setClassName(Notifications15Utils.class.getName());
		subscriptionSender.setCompanyId(companyId);

		// subscriptionSender.setCurrentUserId(userId);
		subscriptionSender.setEntryTitle(entryTitle);
		subscriptionSender.setFrom(fromAddress, fromName);
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setSubject(title);
		subscriptionSender.setBody(body);

		subscriptionSender.setMailId("custom_notitication15 to Change_Management_Team", 0);

		int notificationType = CustomNotification15Type.NOTIFICATION_TYPE_USER_15;

		subscriptionSender.setNotificationType(notificationType);

		String portletId = Notification15PortletKeys.NOTIFICATION15;

		subscriptionSender.setPortletId(portletId);

		subscriptionSender.setReplyToAddress(fromAddress);
		subscriptionSender.setServiceContext(serviceContext);

		subscriptionSender.addPersistedSubscribers(Notifications15Utils.class.getName(), 0);

		subscriptionSender.flushNotificationsAsync();

		try {

			for (User user : userList) {
				logger.info("User from userList..." + user.getScreenName());
				subscriptionSender.sendNotification15(user);
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
	public List<User> getRecipients(long companyId, long groupId, List<String> roleNames) {
		List<User> recipients = new ArrayList<>();

		try {
			for (String roleName : roleNames) {
				Role role = RoleLocalServiceUtil.getRole(companyId, roleName);

				List<UserGroupRole> userGroupRoles = UserGroupRoleLocalServiceUtil.getUserGroupRolesByGroupAndRole(groupId,
						role.getRoleId());

				for (UserGroupRole userGroupRole : userGroupRoles) {
					recipients.add(userGroupRole.getUser());
				}
			}

			if (recipients.isEmpty()) {
				Role roleStakeholder = RoleLocalServiceUtil.getRole(companyId, "Change_Management_Team");
				recipients.addAll(UserLocalServiceUtil.getRoleUsers(roleStakeholder.getRoleId()));
			}
		} catch (PortalException e) {
			logger.error("Error getting recipients", e);
		}

		return recipients;
	}

}
