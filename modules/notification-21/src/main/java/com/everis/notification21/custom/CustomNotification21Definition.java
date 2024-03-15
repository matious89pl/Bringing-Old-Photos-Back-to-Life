package com.everis.notification21.custom;

import com.everis.notification21.constants.CustomNotification21Type;
import com.everis.notification21.constants.Notification21PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {
		"javax.portlet.name=" + Notification21PortletKeys.NOTIFICATION21 }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification21Definition extends UserNotificationDefinition {
	public CustomNotification21Definition() {

		super(Notification21PortletKeys.NOTIFICATION21, 0, CustomNotification21Type.NOTIFICATION_TYPE_USER_21,
				"a Voluntary Withdrawal has been rejected");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, false, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
