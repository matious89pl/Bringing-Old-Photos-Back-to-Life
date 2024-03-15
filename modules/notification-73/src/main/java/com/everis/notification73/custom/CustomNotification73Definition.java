package com.everis.notification73.custom;

import com.everis.notification73.constants.CustomNotification73Type;
import com.everis.notification73.constants.Notification73PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {
		"javax.portlet.name=" + Notification73PortletKeys.NOTIFICATION73 }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification73Definition extends UserNotificationDefinition {
	public CustomNotification73Definition() {

		super(Notification73PortletKeys.NOTIFICATION73, 0, CustomNotification73Type.NOTIFICATION_TYPE_USER_73,
				"Notification triggered when the due date of Training, Guidance and Surveys log passed 1wd");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, true, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
