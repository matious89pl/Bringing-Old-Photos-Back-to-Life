package com.everis.notification06.custom;

import com.everis.notification06.constants.CustomNotification06Type;
import com.everis.notification06.constants.Notification06PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = { "javax.portlet.name="
		+ Notification06PortletKeys.NOTIFICATION06  }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification06Definition extends UserNotificationDefinition {
	public CustomNotification06Definition() {

		super(Notification06PortletKeys.NOTIFICATION06, 0,
				CustomNotification06Type.NOTIFICATION_TYPE_USER_06,
				"a submitted Change Proposal is rejected");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, false, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
