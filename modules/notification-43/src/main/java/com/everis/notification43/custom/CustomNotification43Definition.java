package com.everis.notification43.custom;

import com.everis.notification43.constants.CustomNotification43Type;
import com.everis.notification43.constants.Notification43PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {
		"javax.portlet.name=" + Notification43PortletKeys.NOTIFICATION43 }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author scruzhid
 */
public class CustomNotification43Definition extends UserNotificationDefinition {
	public CustomNotification43Definition() {

		super(Notification43PortletKeys.NOTIFICATION43, 0, CustomNotification43Type.NOTIFICATION_TYPE_USER_43,
				"a Nominations Process has been opened");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, false, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
