package com.everis.notification38.custom;

import com.everis.notification38.constants.CustomNotification38Type;
import com.everis.notification38.constants.Notification38PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {
		"javax.portlet.name=" + Notification38PortletKeys.NOTIFICATION38 }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author scruzhid
 */
public class CustomNotification38Definition extends UserNotificationDefinition {
	public CustomNotification38Definition() {

		super(Notification38PortletKeys.NOTIFICATION38, 0, CustomNotification38Type.NOTIFICATION_TYPE_USER_38,
				"an action is assigned to you within the actions log.");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, false, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
