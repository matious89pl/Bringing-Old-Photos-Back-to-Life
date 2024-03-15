package com.everis.notification09.custom;

import com.everis.notification09.constants.CustomNotification09Type;
import com.everis.notification09.constants.Notification09PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = { "javax.portlet.name="
		+ Notification09PortletKeys.NOTIFICATION09  }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification09Definition extends UserNotificationDefinition {
	public CustomNotification09Definition() {

		super(Notification09PortletKeys.NOTIFICATION09, 0,
				CustomNotification09Type.NOTIFICATION_TYPE_USER_09,
				"solutions or problems have been edited");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, false, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
