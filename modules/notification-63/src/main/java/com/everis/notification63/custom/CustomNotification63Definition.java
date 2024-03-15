package com.everis.notification63.custom;

import com.everis.notification63.constants.CustomNotification63Type;
import com.everis.notification63.constants.Notification63PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = { "javax.portlet.name="
		+ Notification63PortletKeys.NOTIFICATION63  }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification63Definition extends UserNotificationDefinition {
	public CustomNotification63Definition() {

		super(Notification63PortletKeys.NOTIFICATION63, 0,
				CustomNotification63Type.NOTIFICATION_TYPE_USER_63,
				"Notification triggered when a remediation tracker action is changed to pending status");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, true, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
