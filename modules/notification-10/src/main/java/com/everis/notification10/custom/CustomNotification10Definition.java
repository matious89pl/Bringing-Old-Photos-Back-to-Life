package com.everis.notification10.custom;

import com.everis.notification10.constants.CustomNotification10Type;
import com.everis.notification10.constants.Notification10PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {
		"javax.portlet.name=" + Notification10PortletKeys.NOTIFICATION10 }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification10Definition extends UserNotificationDefinition {
	public CustomNotification10Definition() {

		super(Notification10PortletKeys.NOTIFICATION10, 0, CustomNotification10Type.NOTIFICATION_TYPE_USER_10,
				"the response date is updated for a Consultation");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, false, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
