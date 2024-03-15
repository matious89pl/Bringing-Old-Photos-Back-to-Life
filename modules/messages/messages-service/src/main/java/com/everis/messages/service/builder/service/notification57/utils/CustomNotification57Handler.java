package com.everis.messages.service.builder.service.notification57.utils;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.UserNotificationEvent;
import com.liferay.portal.kernel.notifications.BaseUserNotificationHandler;
import com.liferay.portal.kernel.notifications.UserNotificationHandler;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.StringUtil;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {
		"javax.portlet.name=" + Notification57PortletKeys.NOTIFICATION57 }, service = UserNotificationHandler.class)

/**
 * class CustomNotificationHandler: the template to show the custom notifcation
 *
 * @author lperezve
 */

public class CustomNotification57Handler extends BaseUserNotificationHandler {

	private static String _TITLE_KEY57 = "Message";
	private static String _BODY_KEY57 = "{0} sent you a new message";

	private static final String _UKNOWN_USER_KEY = "Unknown";

	private static final String _BODY_TEMPLATE = "<div class=\"title\">[$TITLE$]</div><div class=\"body\">[$BODY$]</div>";
	private static final String[] _BODY_REPLACEMENTS = new String[] { "[$TITLE$]", "[$BODY$]" };

	private static final Log logger = LogFactoryUtil.getLog(CustomNotification57Handler.class);

	public CustomNotification57Handler() {

		setPortletId(Notification57PortletKeys.NOTIFICATION57);
	}

	@Override
	protected String getBody(UserNotificationEvent userNotificationEvent, ServiceContext serviceContext)
			throws Exception {

		String username = LanguageUtil.get(serviceContext.getLocale(), _UKNOWN_USER_KEY);

		String title = LanguageUtil.get(serviceContext.getLocale(), _TITLE_KEY57);

		String body = LanguageUtil.format(serviceContext.getLocale(), _BODY_KEY57, new Object[] { username });

		String html = StringUtil.replace(_BODY_TEMPLATE, _BODY_REPLACEMENTS, new String[] { title, body });

		return html;
	}

	public static void setTitleKey(String title) {
		logger.info("Title: " + title);
		_TITLE_KEY57 = title;
	}

	public static String getTitleKey() {
		return _TITLE_KEY57;
	}

	public static void setBodyKey(String body) {
		logger.info("Body: " + body);
		_BODY_KEY57 = body;
	}

	public static String getBodyKey() {
		return _BODY_KEY57;
	}

}