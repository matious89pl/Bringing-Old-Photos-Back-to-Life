package com.everis.notification06.action;

import com.everis.messages.service.builder.model.Messages;
import com.everis.messages.service.builder.service.MessagesLocalServiceUtil;
import com.everis.notification06.constants.CustomNotification06Type;
import com.everis.notification06.constants.CustomNotifications06Constants;
import com.everis.notification06.constants.Notification06PortletKeys;
import com.everis.notification06.custom.CustomNotification06Handler;
import com.everis.notification06.custom.CustomSubscription06Sender;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroupRole;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.webcache.WebCachePoolUtil;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = { "javax.portlet.name=" + Notification06PortletKeys.NOTIFICATION06,
		"mvc.command.name=sendNotification" }, service = MVCActionCommand.class)

/**
 * class SendNotification01MVCActionCommand: the logic to send a custom
 * notifcation by email and website
 *
 * @author ccaravac
 */

public class SendNotification06MVCActionCommand implements MVCActionCommand {
	private static final Log logger = LogFactoryUtil.getLog(SendNotification06MVCActionCommand.class);

	@Override
	public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {

		_handleActionCommand(actionRequest);

		return true;
	}

	private void _handleActionCommand(ActionRequest actionRequest) {

		logger.info("Send Custom Notification");

		User user = null;
		List<User> userList = new ArrayList<User>();
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

			List<Messages> msgNotification = MessagesLocalServiceUtil.findByCompanyId(user.getCompanyId());

			roleNames.add(RoleConstants.ADMINISTRATOR);

			userList = getRecipients(user.getCompanyId(), serviceContext.getScopeGroupId(), roleNames);

			notifySubscribers(userList, user.getUserId(), user.getCompanyId(), serviceContext, msgNotification);

		} catch (PortalException e) {
			logger.error("Error: " + e);
		}

	}

	protected void notifySubscribers(List<User> userList, long userId, long companyId, ServiceContext serviceContext,
			List<Messages> msgNotification) throws PortalException {

		CustomNotification06Handler.setTitleKey("Change Proposal Page Rejected");
		CustomNotification06Handler.setBodyKey(
				"To better understand your options for a rejected change proposal, please read <<link>> in the knowledge repository."
						+ "If you want to discuss the rejection with a member of the Change Management Team, please raise a service request with the Service Desk at <<link>> .");

		/**
		 * Set up the EMAIL TYPE custom notification
		 */
		String entryTitle = "Change Proposal Rejected Notification";

		String fromName = PropsUtil.get(CustomNotifications06Constants.EMAIL_FROM_NAME);
		String fromAddress = GetterUtil.getString(PropsUtil.get(CustomNotifications06Constants.EMAIL_FROM_ADDRESS),
				PropsUtil.get(PropsKeys.ADMIN_EMAIL_FROM_ADDRESS));

		CustomSubscription06Sender subscriptionSender = new CustomSubscription06Sender();

		subscriptionSender.setClassPK(0);
		subscriptionSender.setClassName(SendNotification06MVCActionCommand.class.getName());
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

		subscriptionSender.addPersistedSubscribers(SendNotification06MVCActionCommand.class.getName(), 0);

		subscriptionSender.flushNotificationsAsync();

		try {

			for (User user : userList) {
				subscriptionSender.sendNotification(user);

				CacheRegistryUtil.clear();
				WebCachePoolUtil.clear();
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

				List<UserGroupRole> userGroupRoles = UserGroupRoleLocalServiceUtil
						.getUserGroupRolesByGroupAndRole(groupId, role.getRoleId());

				for (UserGroupRole userGroupRole : userGroupRoles) {
					recipients.add(userGroupRole.getUser());
				}
			}

			if (recipients.isEmpty()) {
				Role role = RoleLocalServiceUtil.getRole(companyId, RoleConstants.ADMINISTRATOR);

				recipients.addAll(UserLocalServiceUtil.getRoleUsers(role.getRoleId()));
			}
		} catch (PortalException e) {
			logger.error("Error getting recipients", e);
		}

		return recipients;
	}
}
