package com.everis.notification68.action;

import com.everis.notification68.constants.CustomNotification68Type;
import com.everis.notification68.constants.CustomNotifications68Constants;
import com.everis.notification68.constants.Notification68PortletKeys;
import com.everis.notification68.custom.CustomNotification68Handler;
import com.everis.notification68.custom.CustomSubscription68Sender;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = { "javax.portlet.name=" + Notification68PortletKeys.NOTIFICATION68,
		"mvc.command.name=sendNotification" }, service = MVCActionCommand.class)

/**
 * class SendNotification01MVCActionCommand: the logic to send a custom
 * notifcation by email and website
 *
 * @author ccaravac
 */

public class SendNotification68MVCActionCommand implements MVCActionCommand {
	private static final Log logger = LogFactoryUtil.getLog(SendNotification68MVCActionCommand.class);

	@Override
	public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {

		_handleActionCommand(actionRequest);

		return true;
	}

	private void _handleActionCommand(ActionRequest actionRequest) {

		logger.info("Sending Custom Notification 68");

		User user = null;
		HashSet<User> userList = new HashSet<User>();
		List<String> roleNames = new ArrayList<>();

		try {
			user = PortalUtil.getUser(actionRequest);
		} catch (PortalException e) {
			logger.error("Error: no user " + e);
		}

		if (user == null) {
			return;
		}

		/**
		 * Check if the User has permissions and is an Admin
		 */
		PermissionChecker permissionChecker = null;

		try {
			permissionChecker = PermissionCheckerFactoryUtil.create(user);
		} catch (Exception e) {
			logger.error("Error: no permisions " + e);
		}

		if (permissionChecker == null) {
			return;
		}

		if (!permissionChecker.isOmniadmin()) {
			return;
		}

		/**
		 * We have and Admin and we need to collect the event
		 */
		ServiceContext serviceContext = null;

		try {
			serviceContext = ServiceContextFactory.getInstance(actionRequest);

			logger.error("Service Context : " + serviceContext);

			roleNames.add(RoleConstants.ADMINISTRATOR);

			userList = getRecipients();

			notifySubscribers(userList, user.getUserId(), serviceContext);

		} catch (PortalException e) {
			logger.error("Error: " + e);
		}

	}

	protected void notifySubscribers(HashSet<User> userList, long userId, ServiceContext serviceContext)
			throws PortalException {

		String title = "Initial Assessment Report published for comments";
		String body = "An Initial Assessment Report for [CP Page ID] has been published for comments. Go to [link to CP Page] to view the report. Closing date for comments is [comments deadline]";

		CustomNotification68Handler.setTitleKey(title);
		CustomNotification68Handler.setBodyKey(body);

		CustomSubscription68Sender subscriptionSender = new CustomSubscription68Sender();
		/**
		 * Set up the EMAIL TYPE custom notification
		 */
		String entryTitle = "Admin User Login";

		String fromName = PropsUtil.get(CustomNotifications68Constants.EMAIL_FROM_NAME);
		String fromAddress = GetterUtil.getString(PropsUtil.get(CustomNotifications68Constants.EMAIL_FROM_ADDRESS),
				PropsUtil.get(PropsKeys.ADMIN_EMAIL_FROM_ADDRESS));

		subscriptionSender.setClassPK(0);
		subscriptionSender.setClassName(SendNotification68MVCActionCommand.class.getName());
		subscriptionSender.setCompanyId(CompanyThreadLocal.getCompanyId());

		subscriptionSender.setCurrentUserId(userId);
		subscriptionSender.setEntryTitle(entryTitle);
		subscriptionSender.setFrom(fromAddress, fromName);
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setSubject("Hello");
		subscriptionSender.setBody("Email Notification");

		subscriptionSender.setMailId("user", UserLocalServiceUtil.getUser(userId));

		subscriptionSender.addRuntimeSubscribers(UserLocalServiceUtil.getUser(userId).getEmailAddress(),
				UserLocalServiceUtil.getUser(userId).getFullName());

		int notificationType = CustomNotification68Type.NOTIFICATION_TYPE_USER_68;

		subscriptionSender.setNotificationType(notificationType);

		String portletId = Notification68PortletKeys.NOTIFICATION68;

		subscriptionSender.setPortletId(portletId);

		subscriptionSender.setReplyToAddress(fromAddress);
		subscriptionSender.setServiceContext(serviceContext);

		subscriptionSender.addPersistedSubscribers(SendNotification68MVCActionCommand.class.getName(), 0);

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
	 * Recipients who has an specific role
	 *
	 * @param companyId
	 * @param groupId
	 * @param List      rolenames
	 * @return the recipients that has an specific role
	 */
	public HashSet<User> getRecipients() {

		HashSet<User> recipients = new HashSet<>();

		try {

			if (recipients.isEmpty()) {
				Role role = RoleLocalServiceUtil.getRole(CompanyThreadLocal.getCompanyId(),
						RoleConstants.ADMINISTRATOR);

				recipients.addAll(UserLocalServiceUtil.getRoleUsers(role.getRoleId()));
			}
		} catch (PortalException e) {
			logger.error("Error getting recipients", e);
		}

		return recipients;
	}

}
