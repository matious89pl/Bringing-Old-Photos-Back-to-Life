package com.everis.messages.service.builder.service.notification41.utils;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionList;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {}, service = Notifications41Utils.class)

public class Notifications41Utils {

	private static final Log logger = LogFactoryUtil.getLog(Notifications41Utils.class);

	public void notifySubscribers(List<User> userList, long userId, long resourcePrimaryKey, long siteGroupId,
			ServiceContext serviceContext) throws PortalException {
		/**
		 * Set up the WEBSITE TYPE custom notification by name
		 */
		
		logger.info("Notify Subscribers - Notification41 - resourcePrimaryKey " + resourcePrimaryKey);

		JournalArticle journalArticle = JournalArticleLocalServiceUtil.fetchLatestArticle(resourcePrimaryKey);
		String title = StringPool.BLANK, body = StringPool.BLANK, urlLink = StringPool.BLANK;
		try {

			String siteName = GroupLocalServiceUtil.getGroup(siteGroupId).getDescriptiveName();

			char ch = '-';

			String siteNameLink = siteName.replace(' ', ch);

			String siteNameLinkToLowerCase = siteNameLink.toLowerCase();

			urlLink = "/web/" + siteNameLinkToLowerCase + "/-/".concat(journalArticle.getUrlTitle());

			String link = " <a style='color:#70ada3;' onclick='@LINK1'> Go to Election Page </a>";

			title = "Election Open - please vote";
			body = "A new Election has been opened and you have been requested to participate. Please provide your vote here "
					+ link;// [link to open Election in Committee Workspace];

		} catch (Exception e1) {
			logger.error("Error reading journal article... " + e1);
		}

		/**
		 * Set up the EMAIL TYPE custom notification
		 */
		String entryTitle = title;

		String fromName = PropsUtil.get(CustomNotifications41Constants.EMAIL_FROM_NAME);
		String fromAddress = GetterUtil.getString(PropsUtil.get(CustomNotifications41Constants.EMAIL_FROM_ADDRESS),
				PropsUtil.get(PropsKeys.ADMIN_EMAIL_FROM_ADDRESS));

		CustomSubscription41Sender subscriptionSender = new CustomSubscription41Sender();

		Map<Locale, String> subjectMap = new HashMap<Locale, String>();
		subjectMap.put(LocaleUtil.ENGLISH, title);
		subscriptionSender.setLocalizedSubjectMap(subjectMap);

		Map<Locale, String> bodyMap = new HashMap<Locale, String>();
		bodyMap.put(LocaleUtil.ENGLISH, body);
		subscriptionSender.setLocalizedBodyMap(bodyMap);

		subscriptionSender.setEntryURL(urlLink);

		subscriptionSender.setClassPK(0);
		subscriptionSender.setClassName(Notifications41Utils.class.getName());
		subscriptionSender.setCompanyId(CompanyThreadLocal.getCompanyId());

		subscriptionSender.setCurrentUserId(userId);
		subscriptionSender.setEntryTitle(entryTitle);
		subscriptionSender.setFrom(fromAddress, fromName);
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setSubject(title);
		subscriptionSender.setBody(body);

		subscriptionSender.setMailId("custom_notitication41 to Responsible Service Provider(organisation)", 0);

		int notificationType = CustomNotification41Type.NOTIFICATION_TYPE_USER_41;

		subscriptionSender.setNotificationType(notificationType);

		String portletId = Notification41PortletKeys.NOTIFICATION41;

		subscriptionSender.setPortletId(portletId);

		subscriptionSender.setReplyToAddress(fromAddress);
		subscriptionSender.setServiceContext(serviceContext);

		subscriptionSender.addPersistedSubscribers(Notifications41Utils.class.getName(), 0);

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
	public List<User> getRecipients(List<String> roleNames) {

		List<User> recipients = new ArrayList<>();

		try {
			for (String roleName : roleNames) {
				Role role = RoleLocalServiceUtil.getRole(CompanyThreadLocal.getCompanyId(), roleName);
				logger.debug("4 role.... " + role);

				DynamicQuery dynamicQuery = UserGroupRoleLocalServiceUtil.dynamicQuery();
				dynamicQuery.add(PropertyFactoryUtil.forName("roleId").eq(role.getRoleId()));
				logger.debug("dynamicQuery - before projection: ");
				ProjectionList projection = ProjectionFactoryUtil.projectionList();
				projection.add(ProjectionFactoryUtil.groupProperty("userId"));
				dynamicQuery.setProjection(projection);
				logger.debug("dynamicQuery - after projection: ");
				List<Long> userIdLong = UserGroupRoleLocalServiceUtil.dynamicQuery(dynamicQuery);
				logger.debug("userGroupRoleList.size(): " + userIdLong.size());

				if (userIdLong.size() > 0) {
					for (long userId : userIdLong) {
						logger.debug("starting for: ");
						logger.debug("userId: " + userId);
						User user = UserLocalServiceUtil.getUser(userId);
						recipients.add(user);
						logger.debug("5 recipients.... " + user.getEmailAddress());

					}
				}
			}

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

}
