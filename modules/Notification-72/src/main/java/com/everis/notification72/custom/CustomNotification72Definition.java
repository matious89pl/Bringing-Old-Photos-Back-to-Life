package com.everis.notification72.custom;

import com.everis.notification72.constants.CustomNotification72Type;
import com.everis.notification72.constants.Notification72PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = { "javax.portlet.name="
		+ Notification72PortletKeys.NOTIFICATION72  }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification72Definition extends UserNotificationDefinition {
	public CustomNotification72Definition() {

		super(Notification72PortletKeys.NOTIFICATION72, 0,
				CustomNotification72Type.NOTIFICATION_TYPE_USER_72,
				"Notification triggered when a remediation tracker action changed to open status");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, true, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
