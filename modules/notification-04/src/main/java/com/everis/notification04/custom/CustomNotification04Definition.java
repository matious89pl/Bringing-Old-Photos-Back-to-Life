package com.everis.notification04.custom;

import com.everis.notification04.constants.CustomNotification04Type;
import com.everis.notification04.constants.Notification04PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = { "javax.portlet.name="
		+ Notification04PortletKeys.NOTIFICATION04  }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification04Definition extends UserNotificationDefinition {
	public CustomNotification04Definition() {

		super(Notification04PortletKeys.NOTIFICATION04, 0,
				CustomNotification04Type.NOTIFICATION_TYPE_USER_04,
				"a Change Proposal has been successfully implemented.");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, false, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
