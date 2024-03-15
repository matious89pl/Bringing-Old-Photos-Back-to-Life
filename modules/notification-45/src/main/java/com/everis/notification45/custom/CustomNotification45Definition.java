package com.everis.notification45.custom;

import com.everis.notification45.constants.CustomNotification45Type;
import com.everis.notification45.constants.Notification45PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {
		"javax.portlet.name=" + Notification45PortletKeys.NOTIFICATION45 }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author e2517
 */
public class CustomNotification45Definition extends UserNotificationDefinition {
	public CustomNotification45Definition() {

		super(Notification45PortletKeys.NOTIFICATION45, 0, CustomNotification45Type.NOTIFICATION_TYPE_USER_45,
				"an FCR is published with an updated due date for comments");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, false, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
