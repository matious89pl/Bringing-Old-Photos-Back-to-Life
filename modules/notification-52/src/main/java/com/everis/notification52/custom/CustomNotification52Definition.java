package com.everis.notification52.custom;

import com.everis.notification52.constants.CustomNotification52Type;
import com.everis.notification52.constants.Notification52PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = { "javax.portlet.name="
		+ Notification52PortletKeys.NOTIFICATION52  }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification52Definition extends UserNotificationDefinition {
	public CustomNotification52Definition() {

		super(Notification52PortletKeys.NOTIFICATION52, 0,
				CustomNotification52Type.NOTIFICATION_TYPE_USER_52,
				"Notification triggered when an activity log changed to open status");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, true, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
