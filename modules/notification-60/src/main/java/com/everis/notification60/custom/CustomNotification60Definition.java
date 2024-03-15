package com.everis.notification60.custom;

import com.everis.notification60.constants.CustomNotification60Type;
import com.everis.notification60.constants.Notification60PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {
		"javax.portlet.name=" + Notification60PortletKeys.NOTIFICATION60 }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification60Definition extends UserNotificationDefinition {
	public CustomNotification60Definition() {

		super(Notification60PortletKeys.NOTIFICATION60, 0, CustomNotification60Type.NOTIFICATION_TYPE_USER_60,
				"Notification triggered when the due date of RFI passed 1wd");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, true, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
