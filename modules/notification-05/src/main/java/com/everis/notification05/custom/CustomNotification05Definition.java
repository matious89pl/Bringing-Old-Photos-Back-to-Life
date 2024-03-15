package com.everis.notification05.custom;

import com.everis.notification05.constants.CustomNotification05Type;
import com.everis.notification05.constants.Notification05PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = { "javax.portlet.name="
		+ Notification05PortletKeys.NOTIFICATION05  }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification05Definition extends UserNotificationDefinition {
	public CustomNotification05Definition() {

		super(Notification05PortletKeys.NOTIFICATION05, 0,
				CustomNotification05Type.NOTIFICATION_TYPE_USER_05,
				"a submitted Change Proposal is accepted");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, false, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
