package com.everis.notification22.custom;

import com.everis.notification22.constants.CustomNotification22Type;
import com.everis.notification22.constants.Notification22PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {
		"javax.portlet.name=" + Notification22PortletKeys.NOTIFICATION22 }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification22Definition extends UserNotificationDefinition {
	public CustomNotification22Definition() {

		super(Notification22PortletKeys.NOTIFICATION22, 0, CustomNotification22Type.NOTIFICATION_TYPE_USER_22,
				"a Category 3 Change you have proposed has been accepted by the Change Management Team");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, false, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
