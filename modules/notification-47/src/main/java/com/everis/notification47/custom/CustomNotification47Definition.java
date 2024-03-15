package com.everis.notification47.custom;

import com.everis.notification47.constants.CustomNotification47Type;
import com.everis.notification47.constants.Notification47PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = { "javax.portlet.name="
		+ Notification47PortletKeys.NOTIFICATION47  }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification47Definition extends UserNotificationDefinition {
	public CustomNotification47Definition() {

		super(Notification47PortletKeys.NOTIFICATION47, 0,
				CustomNotification47Type.NOTIFICATION_TYPE_USER_47,
				"an activity is added to the activity log");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, true, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
