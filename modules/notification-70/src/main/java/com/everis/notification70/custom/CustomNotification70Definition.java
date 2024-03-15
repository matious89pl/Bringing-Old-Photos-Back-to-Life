package com.everis.notification70.custom;

import com.everis.notification70.constants.CustomNotification70Type;
import com.everis.notification70.constants.Notification70PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = { "javax.portlet.name="
		+ Notification70PortletKeys.NOTIFICATION70  }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification70Definition extends UserNotificationDefinition {
	public CustomNotification70Definition() {

		super(Notification70PortletKeys.NOTIFICATION70, 0,
				CustomNotification70Type.NOTIFICATION_TYPE_USER_70,
				"Notification triggered when a remediation tracker action changed to open status");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, true, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
