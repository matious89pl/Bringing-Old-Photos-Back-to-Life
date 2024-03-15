package com.everis.notification39.custom;

import com.everis.notification39.constants.CustomNotification39Type;
import com.everis.notification39.constants.Notification39PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {
		"javax.portlet.name=" + Notification39PortletKeys.NOTIFICATION39 }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author scruzhid
 */
public class CustomNotification39Definition extends UserNotificationDefinition {
	public CustomNotification39Definition() {

		super(Notification39PortletKeys.NOTIFICATION39, 0, CustomNotification39Type.NOTIFICATION_TYPE_USER_39,
				"a Nominations Process has closed");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, false, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
