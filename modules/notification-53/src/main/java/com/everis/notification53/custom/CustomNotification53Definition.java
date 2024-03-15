package com.everis.notification53.custom;

import com.everis.notification53.constants.CustomNotification53Type;
import com.everis.notification53.constants.Notification53PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {
		"javax.portlet.name=" + Notification53PortletKeys.NOTIFICATION53 }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification53Definition extends UserNotificationDefinition {
	public CustomNotification53Definition() {

		super(Notification53PortletKeys.NOTIFICATION53, 0, CustomNotification53Type.NOTIFICATION_TYPE_USER_53,
				"Notification triggered when the due date passed 1wd");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, true, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
