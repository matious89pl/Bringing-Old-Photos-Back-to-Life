package com.everis.notification58.custom;

import com.everis.notification58.constants.CustomNotification58Type;
import com.everis.notification58.constants.Notification58PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = { "javax.portlet.name="
		+ Notification58PortletKeys.NOTIFICATION58  }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification58Definition extends UserNotificationDefinition {
	public CustomNotification58Definition() {

		super(Notification58PortletKeys.NOTIFICATION58, 0,
				CustomNotification58Type.NOTIFICATION_TYPE_USER_58,
				"Notification triggered when a document is added to the RFI log by RPA");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, true, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
