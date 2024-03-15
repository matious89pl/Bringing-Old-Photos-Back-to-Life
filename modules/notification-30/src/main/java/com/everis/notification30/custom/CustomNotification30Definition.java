package com.everis.notification30.custom;

import com.everis.notification30.constants.CustomNotification30Type;
import com.everis.notification30.constants.Notification30PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {
		"javax.portlet.name=" + Notification30PortletKeys.NOTIFICATION30 }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification30Definition extends UserNotificationDefinition {
	public CustomNotification30Definition() {

		super(Notification30PortletKeys.NOTIFICATION30, 0, CustomNotification30Type.NOTIFICATION_TYPE_USER_30,
				"a vote you have been requested to participate in has now closed. ");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, false, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
