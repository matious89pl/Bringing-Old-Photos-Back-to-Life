package com.everis.notification41.custom;

import com.everis.notification41.constants.CustomNotification41Type;
import com.everis.notification41.constants.Notification41PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {
		"javax.portlet.name=" + Notification41PortletKeys.NOTIFICATION41 }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author scruzhid
 */
public class CustomNotification41Definition extends UserNotificationDefinition {
	public CustomNotification41Definition() {

		super(Notification41PortletKeys.NOTIFICATION41, 0, CustomNotification41Type.NOTIFICATION_TYPE_USER_41,
				"an Election vote has been opened");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, false, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
