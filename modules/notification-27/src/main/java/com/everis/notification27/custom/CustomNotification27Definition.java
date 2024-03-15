package com.everis.notification27.custom;

import com.everis.notification27.constants.CustomNotification27Type;
import com.everis.notification27.constants.Notification27PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {
		"javax.portlet.name=" + Notification27PortletKeys.NOTIFICATION27 }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification27Definition extends UserNotificationDefinition {
	public CustomNotification27Definition() {

		super(Notification27PortletKeys.NOTIFICATION27, 0, CustomNotification27Type.NOTIFICATION_TYPE_USER_27,
				"a nomination has been received in relation to an open nominations process.");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, false, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
