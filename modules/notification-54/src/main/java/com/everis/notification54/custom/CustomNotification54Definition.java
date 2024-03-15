package com.everis.notification54.custom;

import com.everis.notification54.constants.CustomNotification54Type;
import com.everis.notification54.constants.Notification54PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {
		"javax.portlet.name=" + Notification54PortletKeys.NOTIFICATION54 }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification54Definition extends UserNotificationDefinition {
	public CustomNotification54Definition() {

		super(Notification54PortletKeys.NOTIFICATION54, 0, CustomNotification54Type.NOTIFICATION_TYPE_USER_54,
				"Notification triggered for due date of Activity approaching 10wds before due date");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, true, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
