package com.everis.notification12.custom;

import com.everis.notification12.constants.CustomNotification12Type;
import com.everis.notification12.constants.Notification12PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = { "javax.portlet.name="
		+ Notification12PortletKeys.NOTIFICATION12  }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification12Definition extends UserNotificationDefinition {
	public CustomNotification12Definition() {

		super(Notification12PortletKeys.NOTIFICATION12, 0,
				CustomNotification12Type.NOTIFICATION_TYPE_USER_12,
				"an FCR is published with a due date for comments");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, false, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
