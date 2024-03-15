package com.everis.notification62.custom;

import com.everis.notification62.constants.CustomNotification62Type;
import com.everis.notification62.constants.Notification62PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {
		"javax.portlet.name=" + Notification62PortletKeys.NOTIFICATION62 }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification62Definition extends UserNotificationDefinition {
	public CustomNotification62Definition() {

		super(Notification62PortletKeys.NOTIFICATION62, 0, CustomNotification62Type.NOTIFICATION_TYPE_USER_62,
				"Notification triggered when a remediation tracker action is changed to complete status");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, true, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
