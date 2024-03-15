package com.everis.notification71.custom;

import com.everis.notification71.constants.CustomNotification71Type;
import com.everis.notification71.constants.Notification71PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = { "javax.portlet.name="
		+ Notification71PortletKeys.NOTIFICATION71  }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification71Definition extends UserNotificationDefinition {
	public CustomNotification71Definition() {

		super(Notification71PortletKeys.NOTIFICATION71, 0,
				CustomNotification71Type.NOTIFICATION_TYPE_USER_71,
				"Notification triggered when a remediation tracker action changed to open status");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, true, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
