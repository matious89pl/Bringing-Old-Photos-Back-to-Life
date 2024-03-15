package com.everis.notification24.custom;

import com.everis.notification24.constants.CustomNotification24Type;
import com.everis.notification24.constants.Notification24PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {
		"javax.portlet.name=" + Notification24PortletKeys.NOTIFICATION24 }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification24Definition extends UserNotificationDefinition {
	public CustomNotification24Definition() {

		super(Notification24PortletKeys.NOTIFICATION24, 0, CustomNotification24Type.NOTIFICATION_TYPE_USER_24,
				"a new Category 3 Change has been added to the Log. ");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, false, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
