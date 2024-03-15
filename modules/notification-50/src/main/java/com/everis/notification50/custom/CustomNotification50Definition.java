package com.everis.notification50.custom;

import com.everis.notification50.constants.CustomNotification50Type;
import com.everis.notification50.constants.Notification50PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = { "javax.portlet.name="
		+ Notification50PortletKeys.NOTIFICATION50  }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification50Definition extends UserNotificationDefinition {
	public CustomNotification50Definition() {

		super(Notification50PortletKeys.NOTIFICATION50, 0,
				CustomNotification50Type.NOTIFICATION_TYPE_USER_50,
				"Notification triggered when an activity log is changed to complete status");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, true, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
