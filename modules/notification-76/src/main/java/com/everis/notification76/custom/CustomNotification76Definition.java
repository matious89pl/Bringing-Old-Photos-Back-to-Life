package com.everis.notification76.custom;

import com.everis.notification76.constants.CustomNotification76Type;
import com.everis.notification76.constants.Notification76PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {
		"javax.portlet.name=" + Notification76PortletKeys.NOTIFICATION76 }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author e2517
 */
public class CustomNotification76Definition extends UserNotificationDefinition {
	public CustomNotification76Definition() {

		super(Notification76PortletKeys.NOTIFICATION76, 0, CustomNotification76Type.NOTIFICATION_TYPE_USER_76,
				"an ICR is published with an updated due date for comments");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, false, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
