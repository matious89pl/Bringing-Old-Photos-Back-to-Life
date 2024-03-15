package com.everis.notification55.custom;

import com.everis.notification55.constants.CustomNotification55Type;
import com.everis.notification55.constants.Notification55PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {
		"javax.portlet.name=" + Notification55PortletKeys.NOTIFICATION55 }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification55Definition extends UserNotificationDefinition {
	public CustomNotification55Definition() {

		super(Notification55PortletKeys.NOTIFICATION55, 0, CustomNotification55Type.NOTIFICATION_TYPE_USER_55,
				"Notification triggered when a RFI log is changed to complete status");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, true, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
