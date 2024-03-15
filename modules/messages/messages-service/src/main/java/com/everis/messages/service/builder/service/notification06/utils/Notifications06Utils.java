package com.everis.messages.service.builder.service.notification06.utils;

import com.everis.cproposal.model.recFormArticle;
import com.everis.cproposal.service.recFormArticleLocalServiceUtil;
import com.everis.messages.service.builder.model.Messages;
import com.everis.messages.service.builder.service.MessagesLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroupRole;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserGroupRoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = {}, service = Notifications06Utils.class)

public class Notifications06Utils {

	private static final Log logger = LogFactoryUtil.getLog(Notifications06Utils.class);

	public void notifySubscribers(User user, long userId, long companyId, ServiceContext serviceContext,
			List<Messages> msgNotification, String urlLink, long classPK) throws PortalException {

		/**
		 * Set up the WEBSITE TYPE custom notification by name
		 */

		recFormArticle article = recFormArticleLocalServiceUtil.getrecFormArticle(classPK);
		JournalArticle journalArticle = JournalArticleLocalServiceUtil.fetchLatestArticle((article.getArticleId()));
		Document document;
		try {
			document = SAXReaderUtil.read(journalArticle.getContent());
			String nameCpReference = "ChangeProposalReference";
			Node nodeCpReference = document
					.selectSingleNode("//root//dynamic-element[@name='" + nameCpReference + "']//dynamic-content");
			String cpReference = nodeCpReference.getText();
			String nameLink = "LinkToCPPage";
			Node nodeLink = document
					.selectSingleNode("//root//dynamic-element[@name='" + nameLink + "']//dynamic-content");
			String urlLinkToPage = nodeLink.getText();

			String html = " <a id=\"changeProposal\" href=\"" + urlLinkToPage + "\" target=\"_blank\"\r\n"
					+ "      >CP Page Link</a\r\n" + "    >";
			org.jsoup.nodes.Document link = (org.jsoup.nodes.Document) Jsoup.parse(html);

			msgNotification = MessagesLocalServiceUtil.findByNameCompany("notification-06", companyId);
			logger.info("Subject + Body: " + msgNotification.get(0).getSubject() + " - "
					+ (msgNotification.get(0).getBody()));
			CustomNotification06Handler.setTitleKey("Change Proposal Page");
			CustomNotification06Handler.setBodyKey(
					" Your Change Proposal Form has been rejected " + link + " Please follow link for detailed reasons ");
		} catch (DocumentException e1) {
			logger.error("Error reading journal article... " + e1);
		}


		/**
		 * Set up the EMAIL TYPE custom notification
		 */
		String entryTitle = "Change Proposal Approved Notification";

		String fromName = PropsUtil.get(CustomNotifications06Constants.EMAIL_FROM_NAME);
		String fromAddress = GetterUtil.getString(PropsUtil.get(CustomNotifications06Constants.EMAIL_FROM_ADDRESS),
				PropsUtil.get(PropsKeys.ADMIN_EMAIL_FROM_ADDRESS));

		CustomSubscription06Sender subscriptionSender = new CustomSubscription06Sender();

		subscriptionSender.setClassPK(0);
		subscriptionSender.setClassName(Notifications06Utils.class.getName());
		subscriptionSender.setCompanyId(companyId);

		subscriptionSender.setCurrentUserId(userId);
		subscriptionSender.setEntryTitle(entryTitle);
		subscriptionSender.setFrom(fromAddress, fromName);
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setSubject(msgNotification.get(0).getSubject());
		subscriptionSender.setBody(msgNotification.get(0).getBody());

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
				Role role = _roleLocalService.getRole(companyId, roleName);

				List<UserGroupRole> userGroupRoles = _userGroupRoleLocalService.getUserGroupRolesByGroupAndRole(groupId,
						role.getRoleId());

				for (UserGroupRole userGroupRole : userGroupRoles) {
					recipients.add(userGroupRole.getUser());
				}
			}

			if (recipients.isEmpty()) {
				Role role = _roleLocalService.getRole(companyId, RoleConstants.ADMINISTRATOR);

				recipients.addAll(_userLocalService.getRoleUsers(role.getRoleId()));
			}
		} catch (PortalException e) {
			logger.error("Error getting recipients", e);
		}

		return recipients;
	}

	/**** REFERENCES ****/

	private RoleLocalService _roleLocalService;

	@Reference(unbind = "-")
	protected void setRoleLocalService(RoleLocalService serv) {
		_roleLocalService = serv;
	}

	private UserLocalService _userLocalService;

	@Reference(unbind = "-")
	protected void setUserLocalService(UserLocalService serv) {
		_userLocalService = serv;
	}

	private UserGroupRoleLocalService _userGroupRoleLocalService;

	@Reference(unbind = "-")
	protected void setUserGroupRoleLocalService(UserGroupRoleLocalService serv) {
		_userGroupRoleLocalService = serv;
	}

}
