package com.everis.notification29.custom;

import com.everis.notification29.constants.CustomNotification29Type;
import com.everis.notification29.constants.Notification29PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {
		"javax.portlet.name=" + Notification29PortletKeys.NOTIFICATION29 }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification29Definition extends UserNotificationDefinition {
	public CustomNotification29Definition() {

		super(Notification29PortletKeys.NOTIFICATION29, 0, CustomNotification29Type.NOTIFICATION_TYPE_USER_29,
				"your participation for an open vote has been requested");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, false, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
