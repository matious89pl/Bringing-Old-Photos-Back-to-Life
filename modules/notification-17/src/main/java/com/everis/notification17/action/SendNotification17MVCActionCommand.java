package com.everis.notification17.action;

import com.everis.notification17.constants.CustomNotification17Type;
import com.everis.notification17.constants.CustomNotifications17Constants;
import com.everis.notification17.constants.Notification17PortletKeys;
import com.everis.notification17.custom.CustomNotification17Handler;
import com.everis.notification17.custom.CustomSubscription17Sender;

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
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserGroupRoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = { "javax.portlet.name=" + Notification17PortletKeys.NOTIFICATION17,
		"mvc.command.name=sendNotification" }, service = MVCActionCommand.class)

/**
 * class SendNotification01MVCActionCommand: the logic to send a custom
 * notifcation by email and website
 *
 * @author ccaravac
 */

public class SendNotification17MVCActionCommand implements MVCActionCommand {
	private static final Log logger = LogFactoryUtil.getLog(SendNotification17MVCActionCommand.class);

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

			roleNames.add(RoleConstants.ADMINISTRATOR);

			userList = getRecipients(user.getCompanyId(), serviceContext.getScopeGroupId(), roleNames);

			notifySubscribers(userList, user.getUserId(), user.getCompanyId(), serviceContext);

		} catch (PortalException e) {
			logger.error("Error: " + e);
		}

	}

	protected void notifySubscribers(List<User> userList, long userId, long companyId, ServiceContext serviceContext)
			throws PortalException {

		/**
		 * Set up the WEBSITE TYPE custom notification by name
		 */

		CustomNotification17Handler.setTitleKey("Tittle notification17");
		CustomNotification17Handler.setBodyKey("Body notification17");

		/**
		 * Set up the EMAIL TYPE custom notification
		 */
		String entryTitle = "Admin User Login";

		String fromName = PropsUtil.get(CustomNotifications17Constants.EMAIL_FROM_NAME);
		String fromAddress = GetterUtil.getString(PropsUtil.get(CustomNotifications17Constants.EMAIL_FROM_ADDRESS),
				PropsUtil.get(PropsKeys.ADMIN_EMAIL_FROM_ADDRESS));

		CustomSubscription17Sender subscriptionSender = new CustomSubscription17Sender();

		subscriptionSender.setClassPK(0);
		subscriptionSender.setClassName(SendNotification17MVCActionCommand.class.getName());
		subscriptionSender.setCompanyId(companyId);

		subscriptionSender.setCurrentUserId(userId);
		subscriptionSender.setEntryTitle(entryTitle);
		subscriptionSender.setFrom(fromAddress, fromName);
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setSubject("Tittle notification17");
		subscriptionSender.setBody("Body notification17");

		subscriptionSender.setMailId("custom_notitication17", 0);

		int notificationType = CustomNotification17Type.NOTIFICATION_TYPE_USER_17;

		subscriptionSender.setNotificationType(notificationType);

		String portletId = Notification17PortletKeys.NOTIFICATION17;

		subscriptionSender.setPortletId(portletId);

		subscriptionSender.setReplyToAddress(fromAddress);
		subscriptionSender.setServiceContext(serviceContext);

		subscriptionSender.addPersistedSubscribers(SendNotification17MVCActionCommand.class.getName(), 0);

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
