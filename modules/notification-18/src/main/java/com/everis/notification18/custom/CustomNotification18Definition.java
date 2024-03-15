package com.everis.notification18.custom;

import com.everis.notification18.constants.CustomNotification18Type;
import com.everis.notification18.constants.Notification18PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {
		"javax.portlet.name=" + Notification18PortletKeys.NOTIFICATION18 }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 *
 * @author ccaravac
 */
public class CustomNotification18Definition extends UserNotificationDefinition {
	public CustomNotification18Definition() {

		super(Notification18PortletKeys.NOTIFICATION18, 0, CustomNotification18Type.NOTIFICATION_TYPE_USER_18,
				"a Change Proposal is approved");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, false, true));
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
