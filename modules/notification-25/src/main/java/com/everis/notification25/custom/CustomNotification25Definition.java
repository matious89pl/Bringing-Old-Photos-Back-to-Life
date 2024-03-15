package com.everis.notification25.custom;

import com.everis.notification25.constants.CustomNotification25Type;
import com.everis.notification25.constants.Notification25PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {
		"javax.portlet.name=" + Notification25PortletKeys.NOTIFICATION25 }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification25Definition extends UserNotificationDefinition {
	public CustomNotification25Definition() {

		super(Notification25PortletKeys.NOTIFICATION25, 0, CustomNotification25Type.NOTIFICATION_TYPE_USER_25,
				"a Category 3 Change's status has been updated to Awaiting Implementation.");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, false, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
