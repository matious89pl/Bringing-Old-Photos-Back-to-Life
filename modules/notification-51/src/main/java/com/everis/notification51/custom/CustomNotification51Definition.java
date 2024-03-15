package com.everis.notification51.custom;

import com.everis.notification51.constants.CustomNotification51Type;
import com.everis.notification51.constants.Notification51PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = { "javax.portlet.name="
		+ Notification51PortletKeys.NOTIFICATION51  }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification51Definition extends UserNotificationDefinition {
	public CustomNotification51Definition() {

		super(Notification51PortletKeys.NOTIFICATION51, 0,
				CustomNotification51Type.NOTIFICATION_TYPE_USER_51,
				"Notification triggered when an activity log is changed to pending status");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, true, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
