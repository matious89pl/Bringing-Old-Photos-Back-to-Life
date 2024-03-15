package com.everis.notification49.custom;

import com.everis.notification49.constants.CustomNotification49Type;
import com.everis.notification49.constants.Notification49PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = { "javax.portlet.name="
		+ Notification49PortletKeys.NOTIFICATION49  }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification49Definition extends UserNotificationDefinition {
	public CustomNotification49Definition() {

		super(Notification49PortletKeys.NOTIFICATION49, 0,
				CustomNotification49Type.NOTIFICATION_TYPE_USER_49,
				"Notification triggered when an remediation tracker action is added to the remediation tracker");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, true, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
