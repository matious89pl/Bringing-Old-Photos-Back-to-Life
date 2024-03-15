package com.everis.notification48.custom;

import com.everis.notification48.constants.CustomNotification48Type;
import com.everis.notification48.constants.Notification48PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = { "javax.portlet.name="
		+ Notification48PortletKeys.NOTIFICATION48  }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification48Definition extends UserNotificationDefinition {
	public CustomNotification48Definition() {

		super(Notification48PortletKeys.NOTIFICATION48, 0,
				CustomNotification48Type.NOTIFICATION_TYPE_USER_48,
				"an RFI is added to the RFI log");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, true, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
