package com.everis.notification64.custom;

import com.everis.notification64.constants.CustomNotification64Type;
import com.everis.notification64.constants.Notification64PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = { "javax.portlet.name="
		+ Notification64PortletKeys.NOTIFICATION64  }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification64Definition extends UserNotificationDefinition {
	public CustomNotification64Definition() {

		super(Notification64PortletKeys.NOTIFICATION64, 0,
				CustomNotification64Type.NOTIFICATION_TYPE_USER_64,
				"Notification triggered when a remediation tracker action changed to open status");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, true, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
