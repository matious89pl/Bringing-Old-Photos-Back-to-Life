package com.everis.notification66.custom;

import com.everis.notification66.constants.CustomNotification66Type;
import com.everis.notification66.constants.Notification66PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = { "javax.portlet.name="
		+ Notification66PortletKeys.NOTIFICATION66  }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification66Definition extends UserNotificationDefinition {
	public CustomNotification66Definition() {

		super(Notification66PortletKeys.NOTIFICATION66, 0,
				CustomNotification66Type.NOTIFICATION_TYPE_USER_66,
				"Notification triggered when a document is added to the remediation tracker by REC Party User");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, true, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
