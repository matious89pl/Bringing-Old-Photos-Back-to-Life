package com.everis.notification23.custom;

import com.everis.notification23.constants.CustomNotification23Type;
import com.everis.notification23.constants.Notification23PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {
		"javax.portlet.name=" + Notification23PortletKeys.NOTIFICATION23 }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification23Definition extends UserNotificationDefinition {
	public CustomNotification23Definition() {

		super(Notification23PortletKeys.NOTIFICATION23, 0, CustomNotification23Type.NOTIFICATION_TYPE_USER_23,
				"a Category 3 Change you have proposed has been Rejected by the Change Management Team");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, false, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
