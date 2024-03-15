package com.everis.notification59.custom;

import com.everis.notification59.constants.CustomNotification59Type;
import com.everis.notification59.constants.Notification59PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = { "javax.portlet.name="
		+ Notification59PortletKeys.NOTIFICATION59  }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification59Definition extends UserNotificationDefinition {
	public CustomNotification59Definition() {

		super(Notification59PortletKeys.NOTIFICATION59, 0,
				CustomNotification59Type.NOTIFICATION_TYPE_USER_59,
				"Notification triggered when a document is added to the RFI log by REC Party User");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, true, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
