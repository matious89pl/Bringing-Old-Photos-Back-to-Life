package com.everis.notification46.custom;

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
public class CustomSubscription46Sender extends SubscriptionSender {

	private static final Log logger = LogFactoryUtil.getLog(CustomSubscription46Sender.class);

	private static final long serialVersionUID = 6L;

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

	public void sendNotification(User user, String webEnabled, String emailEnabled) throws Exception {

		try {
			if (webEnabled.equals("Web")) {
				logger.info("Sending Web Notification: " + user);
				sendUserNotification(user, true);

			}

			if (emailEnabled.equals("Email")) {
				logger.info("Sending Email Notification: " + user);
				sendEmailNotification(user);

			}

		} catch (Exception e) {
			logger.error("Error CustomSubscriptionSender class : " + e);
		}

	}
}