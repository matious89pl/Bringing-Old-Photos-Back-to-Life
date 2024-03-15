package com.everis.notification56.custom;

import com.everis.notification56.constants.CustomNotification56Type;
import com.everis.notification56.constants.Notification56PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = { "javax.portlet.name="
		+ Notification56PortletKeys.NOTIFICATION56  }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification56Definition extends UserNotificationDefinition {
	public CustomNotification56Definition() {

		super(Notification56PortletKeys.NOTIFICATION56, 0,
				CustomNotification56Type.NOTIFICATION_TYPE_USER_56,
				"Notification triggered when a RFI log is changed to pending status");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, true, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
