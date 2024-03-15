package com.everis.notification32.custom;

import com.everis.notification32.constants.CustomNotification32Type;
import com.everis.notification32.constants.Notification32PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {
		"javax.portlet.name=" + Notification32PortletKeys.NOTIFICATION32 }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification32Definition extends UserNotificationDefinition {
	public CustomNotification32Definition() {

		super(Notification32PortletKeys.NOTIFICATION32, 0, CustomNotification32Type.NOTIFICATION_TYPE_USER_32,
				"A Party Management notification to REC Performance Assurance when an application is approved.");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, false, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
