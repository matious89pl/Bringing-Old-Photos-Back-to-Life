package com.everis.notification28.custom;

import com.everis.notification28.constants.CustomNotification28Type;
import com.everis.notification28.constants.Notification28PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {
		"javax.portlet.name=" + Notification28PortletKeys.NOTIFICATION28 }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */

public class CustomNotification28Definition extends UserNotificationDefinition {
	public CustomNotification28Definition() {

		super(Notification28PortletKeys.NOTIFICATION28, 0, CustomNotification28Type.NOTIFICATION_TYPE_USER_28,
				"an owner has been assigned to an action");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, false, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
