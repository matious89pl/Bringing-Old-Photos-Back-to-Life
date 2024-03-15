package com.everis.notification46.custom;

import com.everis.notification46.constants.CustomNotification46Type;
import com.everis.notification46.constants.Notification46PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = { "javax.portlet.name="
		+ Notification46PortletKeys.NOTIFICATION46  }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification46Definition extends UserNotificationDefinition {
	public CustomNotification46Definition() {

		super(Notification46PortletKeys.NOTIFICATION46, 0,
				CustomNotification46Type.NOTIFICATION_TYPE_USER_46,
				"Notification triggered manually.");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, true, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
