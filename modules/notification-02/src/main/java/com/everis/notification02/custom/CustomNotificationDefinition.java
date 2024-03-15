package com.everis.notification02.custom;

import com.everis.notification02.constants.Notification02PortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = { "javax.portlet.name="
		+ Notification02PortletKeys.NOTIFICATION02  }, service = UserNotificationDefinition.class)

/**
 * class CustomNotificationDefinition: definition of the custon notification
 */
public class CustomNotificationDefinition extends UserNotificationDefinition {
	public CustomNotificationDefinition() {

		super(Notification02PortletKeys.NOTIFICATION02, 0,
				CustomNotificationType.NOTIFICATION_TYPE_USER_02,
				"a REC Portal User wishes to adopt a Change Proposal. ");

		addUserNotificationDeliveryType(
				new UserNotificationDeliveryType("email", UserNotificationDeliveryConstants.TYPE_EMAIL, false, true));
		
		addUserNotificationDeliveryType(new UserNotificationDeliveryType("website",
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, true));
	}
}
