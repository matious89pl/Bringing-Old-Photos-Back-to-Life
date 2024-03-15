package com.everis.notification67.custom;

import com.everis.notification67.constants.CustomNotification67Type;
import com.everis.notification67.constants.Notification67PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {
		"javax.portlet.name=" + Notification67PortletKeys.NOTIFICATION67 }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification67Definition extends UserNotificationDefinition {
	public CustomNotification67Definition() {

		super(Notification67PortletKeys.NOTIFICATION67, 0, CustomNotification67Type.NOTIFICATION_TYPE_USER_67,
				"Notification triggered when the due date of remediation tracker action passed 1wd");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, true, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
