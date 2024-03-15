package com.everis.notification11.custom;

import com.everis.notification11.constants.CustomNotification11Type;
import com.everis.notification11.constants.Notification11PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {
		"javax.portlet.name=" + Notification11PortletKeys.NOTIFICATION11 }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification11Definition extends UserNotificationDefinition {
	public CustomNotification11Definition() {

		super(Notification11PortletKeys.NOTIFICATION11, 0, CustomNotification11Type.NOTIFICATION_TYPE_USER_11,
				"a consultation has been published.");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, false, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
