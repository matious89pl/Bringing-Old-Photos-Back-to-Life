package com.everis.notification08.custom;

import com.everis.notification08.constants.CustomNotification08Type;
import com.everis.notification08.constants.Notification08PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = { "javax.portlet.name="
		+ Notification08PortletKeys.NOTIFICATION08  }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification08Definition extends UserNotificationDefinition {
	public CustomNotification08Definition() {

		super(Notification08PortletKeys.NOTIFICATION08, 0,
				CustomNotification08Type.NOTIFICATION_TYPE_USER_08,
				"a Change Proposer has withdrawn from a Change Proposal.");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, false, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
