package com.everis.notification20.custom;

import com.everis.notification20.constants.CustomNotification20Type;
import com.everis.notification20.constants.Notification20PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {
		"javax.portlet.name=" + Notification20PortletKeys.NOTIFICATION20 }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification20Definition extends UserNotificationDefinition {
	public CustomNotification20Definition() {

		super(Notification20PortletKeys.NOTIFICATION20, 0, CustomNotification20Type.NOTIFICATION_TYPE_USER_20,
				"a Voluntary Withdrawal has been accepted");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, false, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
