package com.everis.notification26.custom;

import com.everis.notification26.constants.CustomNotification26Type;
import com.everis.notification26.constants.Notification26PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {
		"javax.portlet.name=" + Notification26PortletKeys.NOTIFICATION26 }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification26Definition extends UserNotificationDefinition {
	public CustomNotification26Definition() {

		super(Notification26PortletKeys.NOTIFICATION26, 0, CustomNotification26Type.NOTIFICATION_TYPE_USER_26,
				"Nomination Result - Invalid");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, false, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
