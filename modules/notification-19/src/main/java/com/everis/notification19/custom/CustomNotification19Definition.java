package com.everis.notification19.custom;

import com.everis.notification19.constants.CustomNotification19Type;
import com.everis.notification19.constants.Notification19PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {
		"javax.portlet.name=" + Notification19PortletKeys.NOTIFICATION19 }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author lperezve
 */
public class CustomNotification19Definition extends UserNotificationDefinition {
	public CustomNotification19Definition() {

		super(Notification19PortletKeys.NOTIFICATION19, 0, CustomNotification19Type.NOTIFICATION_TYPE_USER_19,
				"there is a new Voluntary Withdrawal request");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, false, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
