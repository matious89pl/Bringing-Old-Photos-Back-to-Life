package com.everis.notification15.custom;

import com.everis.notification15.constants.CustomNotification15Type;
import com.everis.notification15.constants.Notification15PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {
		"javax.portlet.name=" + Notification15PortletKeys.NOTIFICATION15 }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification15Definition extends UserNotificationDefinition {
	public CustomNotification15Definition() {

		super(Notification15PortletKeys.NOTIFICATION15, 0, CustomNotification15Type.NOTIFICATION_TYPE_USER_15,
				"an Impact Assessment related to a Change Proposal has been received.");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, false, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
