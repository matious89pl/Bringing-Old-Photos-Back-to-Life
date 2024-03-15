package com.everis.notification44.custom;

import com.everis.notification44.constants.CustomNotification44Type;
import com.everis.notification44.constants.Notification44PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {
		"javax.portlet.name=" + Notification44PortletKeys.NOTIFICATION44 }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author e2517
 */
public class CustomNotification44Definition extends UserNotificationDefinition {
	public CustomNotification44Definition() {

		super(Notification44PortletKeys.NOTIFICATION44, 0, CustomNotification44Type.NOTIFICATION_TYPE_USER_44,
				"a PCR is published with an updated due date and is open for comments.");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, false, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
