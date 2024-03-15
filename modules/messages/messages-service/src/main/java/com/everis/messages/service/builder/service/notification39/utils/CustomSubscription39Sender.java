package com.everis.messages.service.builder.service.notification39.utils;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Subscription;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.SubscriptionSender;

/**
 * class CustomSubscriptionSender: to send the notification
 *
 * @author ccaravac
 */
public class CustomSubscription39Sender extends SubscriptionSender {

	private static final Log logger = LogFactoryUtil.getLog(CustomSubscription39Sender.class);

	private static final long serialVersionUID = 4L;

	protected void populateNotificationEventJSONObject(JSONObject notificationEventJSONObject) {

		super.populateNotificationEventJSONObject(notificationEventJSONObject);

	}

	protected boolean hasPermission(Subscription subscription, String className, long classPK, User user)
			throws Exception {
		return true;
	}

	protected boolean hasPermission(Subscription subscription, User user) throws Exception {
		return true;
	}

	public void sendNotification(User user) throws Exception {

		
		try {
			sendUserNotification(user, true);
		} catch (Exception e) {
			logger.error("Error CustomSubscriptionSender class : " + e);
		}

	}
}