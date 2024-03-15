package com.everis.messages.service.builder.service.notification04.utils;

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
import com.liferay.portal.kernel.model.UserGroupRole;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserGroupRoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
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

import org.jsoup.Jsoup;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = {}, service = Notifications04Utils.class)

public class Notifications04Utils {

	private static final Log logger = LogFactoryUtil.getLog(Notifications04Utils.class);

	public void notifySubscribers(List<User> userList, long userId, long companyId, ServiceContext serviceContext,
			long classPK) throws PortalException {

		/**
		 * Set up the WEBSITE TYPE custom notification by name
		 */

		recFormArticle article = recFormArticleLocalServiceUtil.getrecFormArticle(classPK);
		JournalArticle journalArticle = JournalArticleLocalServiceUtil.fetchLatestArticle((article.getArticleId()));
		Document document;
		String title = StringPool.BLANK, body = StringPool.BLANK;
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
			
			title = "Successfully Implemented Change Release";
			body = cpReference + " has been Successfully Implemented " + link;
			CustomNotification04Handler.setTitleKey(title);
			CustomNotification04Handler.setBodyKey(body);
		} catch (DocumentException e1) {
			logger.error("Error reading journal article... " + e1);
		}

		/**
		 * Set up the EMAIL TYPE custom notification
		 */
		String entryTitle = title;

		String fromName = PropsUtil.get(CustomNotifications04Constants.EMAIL_FROM_NAME);
		String fromAddress = GetterUtil.getString(PropsUtil.get(CustomNotifications04Constants.EMAIL_FROM_ADDRESS),
				PropsUtil.get(PropsKeys.ADMIN_EMAIL_FROM_ADDRESS));

		CustomSubscription04Sender subscriptionSender = new CustomSubscription04Sender();
		
		Map<Locale, String> subjectMap = new HashMap<Locale, String>();
		subjectMap.put(LocaleUtil.ENGLISH, title);
		subscriptionSender.setLocalizedSubjectMap(subjectMap);
				
		Map<Locale, String> bodyMap = new HashMap<Locale, String>();
		bodyMap.put(LocaleUtil.ENGLISH, body);
		subscriptionSender.setLocalizedBodyMap(bodyMap);

		subscriptionSender.setClassPK(0);
		subscriptionSender.setClassName(Notifications04Utils.class.getName());
		subscriptionSender.setCompanyId(companyId);

		subscriptionSender.setCurrentUserId(userId);
		subscriptionSender.setEntryTitle(entryTitle);
		subscriptionSender.setFrom(fromAddress, fromName);
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setSubject(title);
		subscriptionSender.setBody(body);

		subscriptionSender.setMailId("custom_notitication04 to REC_Stakeholder", 0);

		int notificationType = CustomNotification04Type.NOTIFICATION_TYPE_USER_04;

		subscriptionSender.setNotificationType(notificationType);

		String portletId = Notification04PortletKeys.NOTIFICATION04;

		subscriptionSender.setPortletId(portletId);

		subscriptionSender.setReplyToAddress(fromAddress);
		subscriptionSender.setServiceContext(serviceContext);

		subscriptionSender.addPersistedSubscribers(Notifications04Utils.class.getName(), 0);

		subscriptionSender.flushNotificationsAsync();

		try {

			for (User user : userList) {
				subscriptionSender.sendNotification4(user);
				
				logger.error("Stakeholder: " + user);
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
				Role role = _roleLocalService.getRole(companyId, roleName);

				List<UserGroupRole> userGroupRoles = _userGroupRoleLocalService.getUserGroupRolesByGroupAndRole(groupId,
						role.getRoleId());

				for (UserGroupRole userGroupRole : userGroupRoles) {
					recipients.add(userGroupRole.getUser());
				}
			}

			if (recipients.isEmpty()) {
				Role role = _roleLocalService.getRole(companyId, "REC_Stakeholder");

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
