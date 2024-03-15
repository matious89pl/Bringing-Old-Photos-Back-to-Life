package com.everis.notification55.custom;

import com.everis.notification55.constants.Notification55PortletKeys;
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
		"javax.portlet.name=" + Notification55PortletKeys.NOTIFICATION55 }, service = UserNotificationHandler.class)

/**
 * class CustomNotificationHandler: the template to show the custom notifcation
 *
 * @author ccaravac
 */

public class CustomNotification55Handler extends BaseUserNotificationHandler {

	private static String _TITLE_KEY55 = "Message";
	private static String _BODY_KEY55 = "{0} sent you a new message";

	private static final String _UKNOWN_USER_KEY = "Unknown";

	private static final String _BODY_TEMPLATE = "<div class=\"title\">[$TITLE$]</div><div class=\"body\">[$BODY$]</div>";
	private static final String[] _BODY_REPLACEMENTS = new String[] { "[$TITLE$]", "[$BODY$]" };

	private static final Log logger = LogFactoryUtil.getLog(CustomNotification55Handler.class);

	public CustomNotification55Handler() {

		setPortletId(Notification55PortletKeys.NOTIFICATION55);
	}

	@Override
	protected String getBody(UserNotificationEvent userNotificationEvent, ServiceContext serviceContext)
			throws Exception {

		String username = LanguageUtil.get(serviceContext.getLocale(), _UKNOWN_USER_KEY);

		String title = LanguageUtil.get(serviceContext.getLocale(), _TITLE_KEY55);

		String body = LanguageUtil.format(serviceContext.getLocale(), _BODY_KEY55, new Object[] { username });

		String html = StringUtil.replace(_BODY_TEMPLATE, _BODY_REPLACEMENTS, new String[] { title, body });

		return html;
	}

	public static void setTitleKey(String title) {
		logger.info("Title: " + title);
		_TITLE_KEY55 = title;
	}

	public static String getTitleKey() {
		return _TITLE_KEY55;
	}

	public static void setBodyKey(String body) {
		logger.info("Body: " + body);
		_BODY_KEY55 = body;
	}

	public static String getBodyKey() {
		return _BODY_KEY55;
	}

}