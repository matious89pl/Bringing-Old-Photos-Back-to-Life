package com.everis.notification57.custom;

import com.everis.notification57.constants.CustomNotification57Type;
import com.everis.notification57.constants.Notification57PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = { "javax.portlet.name="
		+ Notification57PortletKeys.NOTIFICATION57  }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification57Definition extends UserNotificationDefinition {
	public CustomNotification57Definition() {

		super(Notification57PortletKeys.NOTIFICATION57, 0,
				CustomNotification57Type.NOTIFICATION_TYPE_USER_57,
				"Notification triggered when a RFI log changed to open status");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, true, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
