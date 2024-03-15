package com.everis.notification68.custom;

import com.everis.notification68.constants.CustomNotification68Type;
import com.everis.notification68.constants.Notification68PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {
		"javax.portlet.name=" + Notification68PortletKeys.NOTIFICATION68 }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification68Definition extends UserNotificationDefinition {
	public CustomNotification68Definition() {

		super(Notification68PortletKeys.NOTIFICATION68, 0, CustomNotification68Type.NOTIFICATION_TYPE_USER_68,
				"Notification triggered for due date of remediation tracker action approaching 10wds before due date");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, true, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
