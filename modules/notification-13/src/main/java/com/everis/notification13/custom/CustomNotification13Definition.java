package com.everis.notification13.custom;

import com.everis.notification13.constants.CustomNotification13Type;
import com.everis.notification13.constants.Notification13PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {
		"javax.portlet.name=" + Notification13PortletKeys.NOTIFICATION13 }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification13Definition extends UserNotificationDefinition {
	public CustomNotification13Definition() {

		super(Notification13PortletKeys.NOTIFICATION13, 0, CustomNotification13Type.NOTIFICATION_TYPE_USER_13,
				"an IA has been published.");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, false, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
