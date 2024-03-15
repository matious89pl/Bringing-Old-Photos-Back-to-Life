package com.everis.notification17.custom;

import com.everis.notification17.constants.CustomNotification17Type;
import com.everis.notification17.constants.Notification17PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {
		"javax.portlet.name=" + Notification17PortletKeys.NOTIFICATION17 }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification17Definition extends UserNotificationDefinition {
	public CustomNotification17Definition() {

		super(Notification17PortletKeys.NOTIFICATION17, 0, CustomNotification17Type.NOTIFICATION_TYPE_USER_17,
				"a Change Proposal is approved");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, false, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
