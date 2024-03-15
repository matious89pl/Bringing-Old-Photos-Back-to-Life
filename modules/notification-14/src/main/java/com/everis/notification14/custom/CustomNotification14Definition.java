package com.everis.notification14.custom;

import com.everis.notification14.constants.CustomNotification14Type;
import com.everis.notification14.constants.Notification14PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {
		"javax.portlet.name=" + Notification14PortletKeys.NOTIFICATION14 }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification14Definition extends UserNotificationDefinition {
	public CustomNotification14Definition() {

		super(Notification14PortletKeys.NOTIFICATION14, 0, CustomNotification14Type.NOTIFICATION_TYPE_USER_14,
				"the IA response date is updated");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, false, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
