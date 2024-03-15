package com.everis.notification65.custom;

import com.everis.notification65.constants.CustomNotification65Type;
import com.everis.notification65.constants.Notification65PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = { "javax.portlet.name="
		+ Notification65PortletKeys.NOTIFICATION65  }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification65Definition extends UserNotificationDefinition {
	public CustomNotification65Definition() {

		super(Notification65PortletKeys.NOTIFICATION65, 0,
				CustomNotification65Type.NOTIFICATION_TYPE_USER_65,
				"Notification triggered when a document is added to the remediation tracker by RPA");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, true, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
