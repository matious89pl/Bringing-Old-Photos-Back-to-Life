package com.everis.notification69.custom;

import com.everis.notification69.constants.CustomNotification69Type;
import com.everis.notification69.constants.Notification69PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = { "javax.portlet.name="
		+ Notification69PortletKeys.NOTIFICATION69  }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification69Definition extends UserNotificationDefinition {
	public CustomNotification69Definition() {

		super(Notification69PortletKeys.NOTIFICATION69, 0,
				CustomNotification69Type.NOTIFICATION_TYPE_USER_69,
				"Notification triggered when a record is added to Training, Guidance and Surveys log");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, true, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
