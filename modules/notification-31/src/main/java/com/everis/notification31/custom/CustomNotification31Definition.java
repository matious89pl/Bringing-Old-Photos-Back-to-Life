package com.everis.notification31.custom;

import com.everis.notification31.constants.CustomNotification31Type;
import com.everis.notification31.constants.Notification31PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {
		"javax.portlet.name=" + Notification31PortletKeys.NOTIFICATION31 }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification31Definition extends UserNotificationDefinition {
	public CustomNotification31Definition() {

		super(Notification31PortletKeys.NOTIFICATION31, 0, CustomNotification31Type.NOTIFICATION_TYPE_USER_31,
				"Committee Administrator issues a custom notification.");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, false, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
