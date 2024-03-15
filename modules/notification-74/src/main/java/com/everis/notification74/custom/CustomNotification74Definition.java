package com.everis.notification74.custom;

import com.everis.notification74.constants.CustomNotification74Type;
import com.everis.notification74.constants.Notification74PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {
		"javax.portlet.name=" + Notification74PortletKeys.NOTIFICATION74 }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification74Definition extends UserNotificationDefinition {
	public CustomNotification74Definition() {

		super(Notification74PortletKeys.NOTIFICATION74, 0, CustomNotification74Type.NOTIFICATION_TYPE_USER_74,
				"Notification triggered for due date of Training, Guidance and Surveys log approaching 10wds before due date");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, true, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
