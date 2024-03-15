package com.everis.notification16.custom;

import com.everis.notification16.constants.CustomNotification16Type;
import com.everis.notification16.constants.Notification16PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = { "javax.portlet.name="
		+ Notification16PortletKeys.NOTIFICATION16  }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification16Definition extends UserNotificationDefinition {
	public CustomNotification16Definition() {

		super(Notification16PortletKeys.NOTIFICATION16, 0,
				CustomNotification16Type.NOTIFICATION_TYPE_USER_16,
				"an IAR is published with a date for comments");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, false, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
