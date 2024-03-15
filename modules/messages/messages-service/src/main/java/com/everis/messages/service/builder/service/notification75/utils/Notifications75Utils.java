package com.everis.messages.service.builder.service.notification75.utils;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroupRole;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {}, service = Notifications75Utils.class)

public class Notifications75Utils {

	private static final Log logger = LogFactoryUtil.getLog(Notifications75Utils.class);

	public void notifySubscribers(HashSet<User> userList, ServiceContext serviceContext, long currentUserId,
			long siteGroupId, String remedationTitle, String urlEmail) throws PortalException {

		String title = StringPool.BLANK, body = StringPool.BLANK, bodyEmail = StringPool.BLANK;

		char ch = '-';

		Company company = CompanyLocalServiceUtil.getCompanyByWebId(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
		String companyIdName = company.getPortalURL(siteGroupId);

		String siteName = GroupLocalServiceUtil.getGroup(siteGroupId).getDescriptiveName();
		String siteNameLink = siteName.replace(' ', ch);

		String siteNameLinkToLowerCase = siteNameLink.toLowerCase();

		String urlLink = "/group/" + siteNameLinkToLowerCase;
		String urlEmailLink = companyIdName + "/group/";
		String link = " <a style='color:#75ada3;' onclick='@LINK1'> performance assurance area </a>";

		logger.debug("Subject + Body: " + title + " - " + body);

		String YFile = "'Your Files'";
		title = "File added to " + YFile + " by Code Manager";
		body = "A file " + remedationTitle
				+ " has been added to the Your Files tab. Please navigate to your organisation " + link + " for more details";

		String linkEmail = "<a style='color:#70ada3' href='"
				+ HtmlUtil.escape(urlEmailLink.concat(siteNameLinkToLowerCase)) + "'>"
				+ HtmlUtil.escape("performance assurance area") + "</a>";

		bodyEmail = "A file " + remedationTitle
				+ " has been added to the Your Files tab. Please navigate to your organisation " + linkEmail + " for more details";

		/**
		 * Set up the EMAIL TYPE custom notification
		 */

		String entryTitle = title;

		String fromName = PropsUtil.get(CustomNotifications75Constants.EMAIL_FROM_NAME);
		String fromAddress = GetterUtil.getString(PropsUtil.get(CustomNotifications75Constants.EMAIL_FROM_ADDRESS),
				PropsUtil.get(PropsKeys.ADMIN_EMAIL_FROM_ADDRESS));

		CustomSubscription75Sender subscriptionSender = new CustomSubscription75Sender();

		Map<Locale, String> subjectMap = new HashMap<Locale, String>();
		subjectMap.put(LocaleUtil.ENGLISH, title);
		subscriptionSender.setLocalizedSubjectMap(subjectMap);

		Map<Locale, String> bodyMap = new HashMap<Locale, String>();
		bodyMap.put(LocaleUtil.ENGLISH, body);
		subscriptionSender.setLocalizedBodyMap(bodyMap);

		subscriptionSender.setEntryURL(urlLink);

		subscriptionSender.setClassPK(0);
		subscriptionSender.setClassName(Notifications75Utils.class.getName());
		subscriptionSender.setCompanyId(CompanyThreadLocal.getCompanyId());

		subscriptionSender.setCurrentUserId(currentUserId);
		subscriptionSender.setEntryTitle(entryTitle);
		subscriptionSender.setFrom(fromAddress, fromName);
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setSubject(title);
		subscriptionSender.setBody(bodyEmail);

		subscriptionSender.setMailId("user", UserLocalServiceUtil.getUser(currentUserId));

		subscriptionSender.addRuntimeSubscribers(UserLocalServiceUtil.getUser(currentUserId).getEmailAddress(),
				UserLocalServiceUtil.getUser(currentUserId).getFullName());

		int notificationType = CustomNotification75Type.NOTIFICATION_TYPE_USER_75;

		subscriptionSender.setNotificationType(notificationType);

		String portletId = Notification75PortletKeys.NOTIFICATION75;

		subscriptionSender.setPortletId(portletId);

		subscriptionSender.setReplyToAddress(fromAddress);
		subscriptionSender.setServiceContext(serviceContext);

		subscriptionSender.addPersistedSubscribers(Notifications75Utils.class.getName(), 0);

		/*
		 * subscriptionSender.flushNotificationsAsync();
		 */

		try {

			for (User user : userList) {

				subscriptionSender.sendNotification(user);

			}

		} catch (Exception e) {
			logger.error("Error sending the notification: " + e);
		}

	}

	/**
	 * Recipients who has an specific Segments
	 * 
	 * @param List Users than belong to a Segment
	 * @return the recipients that has an specific Segments
	 */
	public HashSet<User> getRecipients(long organisationId, long siteGroupId, long companyId) {

		List<User> siteOrganisitationUsers = UserLocalServiceUtil.getOrganizationUsers(organisationId);

		HashSet<User> recipients = new HashSet<User>();

		try {

			for (User memberSites : siteOrganisitationUsers) {

				logger.info("Members roles: " + memberSites.getRoles());

				List<UserGroupRole> userGroupRoles = UserGroupRoleLocalServiceUtil
						.getUserGroupRoles(memberSites.getUserId(), siteGroupId);

				for (UserGroupRole userGroupRole : userGroupRoles) {
					logger.info("Organisation Roles :" + userGroupRole.getRole().getName());
					if (userGroupRole.getRole().getName().equals("REC Contract Managers")
							|| userGroupRole.getRole().getName().equals("REC_Performance_Assurance")
							|| userGroupRole.getRole().getName().equals("Master Administrative User")) {

						User users = UserLocalServiceUtil.getUser(userGroupRole.getUserId());

						recipients.add(users);
					}
				}

			}

		} catch (PortalException e) {
			logger.error("Error getting recipients", e);
		}

		return recipients;
	}
}