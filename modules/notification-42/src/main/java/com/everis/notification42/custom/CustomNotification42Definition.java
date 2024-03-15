package com.everis.notification42.custom;

import com.everis.notification42.constants.CustomNotification42Type;
import com.everis.notification42.constants.Notification42PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {
		"javax.portlet.name=" + Notification42PortletKeys.NOTIFICATION42 }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author scruzhid
 */
public class CustomNotification42Definition extends UserNotificationDefinition {
	public CustomNotification42Definition() {

		super(Notification42PortletKeys.NOTIFICATION42, 0, CustomNotification42Type.NOTIFICATION_TYPE_USER_42,
				"an Election vote has closed");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, false, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
