package com.everis.notification75.custom;

import com.everis.notification75.constants.CustomNotification75Type;
import com.everis.notification75.constants.Notification75PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = { "javax.portlet.name="
		+ Notification75PortletKeys.NOTIFICATION75  }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification75Definition extends UserNotificationDefinition {
	public CustomNotification75Definition() {

		super(Notification75PortletKeys.NOTIFICATION75, 0,
				CustomNotification75Type.NOTIFICATION_TYPE_USER_75,
				"Notification triggered when an activity is added to the activity log");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, true, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
