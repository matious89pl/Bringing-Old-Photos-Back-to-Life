package com.everis.notification61.custom;

import com.everis.notification61.constants.CustomNotification61Type;
import com.everis.notification61.constants.Notification61PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {
		"javax.portlet.name=" + Notification61PortletKeys.NOTIFICATION61 }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification61Definition extends UserNotificationDefinition {
	public CustomNotification61Definition() {

		super(Notification61PortletKeys.NOTIFICATION61, 0, CustomNotification61Type.NOTIFICATION_TYPE_USER_61,
				"Notification triggered for due date of RFI approaching 10wds before due date");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, true, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
