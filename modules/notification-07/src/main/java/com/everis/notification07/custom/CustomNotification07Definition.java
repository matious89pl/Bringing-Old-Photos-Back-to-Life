package com.everis.notification07.custom;

import com.everis.notification07.constants.CustomNotification07Type;
import com.everis.notification07.constants.Notification07PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = { "javax.portlet.name="
		+ Notification07PortletKeys.NOTIFICATION07  }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification07Definition extends UserNotificationDefinition {
	public CustomNotification07Definition() {

		super(Notification07PortletKeys.NOTIFICATION07, 0,
				CustomNotification07Type.NOTIFICATION_TYPE_USER_07,
				"a PCR is published with a due date for comments.");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, false, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
