package rec.notification.portlet.portlet;

import rec.customnotification.service.NotificationRpaLocalService;
import rec.customnotification.service.NotificationRpaLocalServiceUtil;
import rec.customnotification.model.NotificationRpa;
import rec.notification.portlet.constants.RecNotificationPortletKeys;

import com.everis.messages.service.builder.service.MessagesLocalService;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ProcessAction;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.segments.model.SegmentsEntry;
import com.liferay.segments.model.SegmentsEntryRel;
import com.liferay.segments.service.SegmentsEntryLocalService;
import com.liferay.segments.service.SegmentsEntryRelLocalServiceUtil;

/**
 * @author sarsolis
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=RecNotification", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + RecNotificationPortletKeys.RECNOTIFICATION,
		"javax.portlet.resource-bundle=content.Language", "javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.init-param.copy-request-parameters=false",
		"com.liferay.portlet.action-url-redirect=true" }, service = Portlet.class)
public class RecNotificationPortlet extends MVCPortlet {

	private static final Log _log = LogFactoryUtil.getLog(RecNotificationPortlet.class.getName());

	@Reference
	protected NotificationRpaLocalService notificationRpaService;

	@Reference
	protected SegmentsEntryLocalService segmentsLocalService;

	@Reference
	protected MessagesLocalService messagesLocalService;

	private ThemeDisplay themeDisplay;

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		_log.debug("------- In Render Notification RPA ------- ");
		themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		List<NotificationRpa> notificationslogList = notificationRpaService
				.findByGroupIdandPlidPage(themeDisplay.getScopeGroupId(), themeDisplay.getPlid());

		List<SegmentsEntry> segmentsList = segmentsLocalService.getSegmentsEntriesBySource("DEFAULT", -1, -1, null);

		renderRequest.setAttribute("notificationslogList", notificationslogList);
		renderRequest.setAttribute("segmentsList", segmentsList);
		super.render(renderRequest, renderResponse);
	}

	@ProcessAction(name = "newNotification")
	public void newNotification(ActionRequest actionRequest, ActionResponse actionResponse) {
		_log.debug("------- In new Notification ------- ");

		String notificationTitle = ParamUtil.getString(actionRequest, "notificationTitle");
		String notificationBody = ParamUtil.getString(actionRequest, "notificationBody");
		String url = ParamUtil.getString(actionRequest, "notificationURL");
		String targetName = ParamUtil.getString(actionRequest, "segments");
		String deliveryMethodPortal = ParamUtil.getString(actionRequest, "methodsenderPotal");
		String deliveryMethodEmail = ParamUtil.getString(actionRequest, "methodsenderEmail");
		
		sendNotification(notificationTitle, notificationBody, deliveryMethodPortal, deliveryMethodEmail, targetName, url);
		addNewNotificationLog(notificationTitle, notificationBody, deliveryMethodPortal, deliveryMethodEmail, targetName, url);
	}

	// Add new notification log
	public void addNewNotificationLog(String notificationTitle, String notificationBody, String deliveryMethodPortal, String deliveryMethodEmail,
			String targetAudience, String url) {
		try {
			NotificationRpa notificationRpa = notificationRpaService
					.createNotificationRpa(CounterLocalServiceUtil.increment(NotificationRpa.class.getName()));

			notificationRpa.setNotificationTitle(notificationTitle);
			notificationRpa.setNotificationBody(notificationBody);
			notificationRpa.setUrl(url);
			notificationRpa.setTargetName(targetAudience);
			notificationRpa.setUserId(themeDisplay.getUserId());
			notificationRpa.setUserName(themeDisplay.getUser().getFullName());
			notificationRpa.setPlidPage(themeDisplay.getPlid());
			notificationRpa.setGroupId(themeDisplay.getScopeGroupId());
			
			if (deliveryMethodPortal.equals("Portal Notification") && deliveryMethodEmail.equals("Email")) {
				notificationRpa.setDeliveryMethod("Portal Notification And Email");
			} else if (deliveryMethodPortal.equals("Portal Notification")){
				notificationRpa.setDeliveryMethod("Portal Notification");
			} else if (deliveryMethodEmail.equals("Email")) {
				notificationRpa.setDeliveryMethod("Email");
			}

			if (!deliveryMethodEmail.equals("") || !deliveryMethodPortal.equals("")) {
				notificationRpaService.addNotificationRpa(notificationRpa);
			}
			
		} catch (Exception e) {
			_log.error("Error adding new notification log", e);
		}
	}

	// Send notification
	public void sendNotification(String notificationTitle, String notificationBody, String deliveryMethodPortal, String deliveryMethodEmail,
			String targetAudience, String url) {
		try {
			String email = " ";
			String web = " ";
			if (deliveryMethodEmail.equals("Email")) {
				email = "Email";
			}
			if (deliveryMethodPortal.equals("Portal Notification")) {
				web = "Web";
			}

			String notificationBodyToSend = addFriendlyUrl(notificationBody, url);
			messagesLocalService.sendNotification46(notificationTitle, notificationBodyToSend, targetAudience,
					themeDisplay.getUserId(), web, email);
		} catch (Exception e) {
			_log.error("Error sending new notification", e);
		}
	}

	@ProcessAction(name = "goBack")
	public void goBack(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
		actionResponse.getRenderParameters().setValue("mvcPath", "/view.jsp");
	}

	@ProcessAction(name = "resendNotification")
	public void resendNotification(ActionRequest actionRequest, ActionResponse actionResponse) {
		_log.info("------- In Resend Notification ------- ");
		try {
			String notificationIdString = ParamUtil.getString(actionRequest, "notificationId", "");
			Integer notificationId = Integer.parseInt(notificationIdString);

			NotificationRpa notification = notificationRpaService.getNotificationRpa(notificationId);

			String notificationTitle = notification.getNotificationTitle();
			String notificationBody = notification.getNotificationBody();
			String deliveryMethod = notification.getDeliveryMethod();
			String targetAudience = notification.getTargetName();
			String url = notification.getUrl();
			String  deliveryMethodEmail = "";
			String  deliveryMethodPortal = "";
			
			if (deliveryMethod.contains("Portal Notification") && deliveryMethod.contains("Email")) {
				deliveryMethodEmail = "Email";
				deliveryMethodPortal= "Portal Notification";
			} else if (deliveryMethod.equals("Portal Notification")){
				deliveryMethodPortal= "Portal Notification";
			}else if (deliveryMethod.equals("Email")) {
				deliveryMethodEmail = "Email";
			}
			
			sendNotification(notificationTitle, notificationBody, deliveryMethodPortal, deliveryMethodEmail, targetAudience, url);
			addNewNotificationLog(notificationTitle, notificationBody, deliveryMethodPortal, deliveryMethodEmail, targetAudience, url);
		} catch (Exception e) {
			_log.error("Error resending a notification", e);
		}
	}

	private static String addFriendlyUrl(String body, String url) {
		Pattern httpLinkPattern = Pattern.compile("(http[s]?)://(www\\.)?([\\S&&[^.@]]+)(\\.[\\S&&[^@]]+)");
		Pattern wwwLinkPattern = Pattern.compile("(?<!http[s]?://)(www\\.+)([\\S&&[^.@]]+)(\\.[\\S&&[^@]]+)");
		// Pattern mailAddressPattern = Pattern.compile("[\\S&&[^@]]+@([\\S&&[^.@]]+)(\\.[\\S&&[^@]]+)");

		if (Objects.nonNull(body)) {
			Matcher httpLinksMatcher = httpLinkPattern.matcher(body);
			body = httpLinksMatcher.replaceAll("<a style='color:#70ada3' href='$0'>" + HtmlUtil.escape(url) + "</a>");

			final Matcher wwwLinksMatcher = wwwLinkPattern.matcher(body);
			body = wwwLinksMatcher
					.replaceAll("<a style='color:#70ada3' href='http://$0'>" + HtmlUtil.escape(url) + "</a>");

			// final Matcher mailLinksMatcher = mailAddressPattern.matcher(body);
			// body = mailLinksMatcher.replaceAll("<a href=\"mailto:$0\"
			// style=\"color:#70ada3\">" + url + "</a>");

			_log.info("FriendlyUrl: " + body);
		}
		return body;
	}
	
	@Override
    public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws IOException, PortletException {
		   _log.debug("This is serve resource method....");
		long segmentId = ParamUtil.getLong(resourceRequest, "segmentId");
		List<SegmentsEntryRel> relsList = SegmentsEntryRelLocalServiceUtil.getSegmentsEntryRels(segmentId);
		JSONArray jsonResponse = JSONFactoryUtil.createJSONArray();
		for (SegmentsEntryRel segmentsEntryRel : relsList) {
			if (segmentsEntryRel.getClassName().contentEquals("com.liferay.portal.kernel.model.User")) {
				User user;
				try {
					user = UserLocalServiceUtil.getUser(segmentsEntryRel.getClassPK());
					  JSONObject catJSON = JSONFactoryUtil.createJSONObject();
			          catJSON.put("id", user.getUserId());
			          catJSON.put("name", user.getFullName());
			          catJSON.put("emailAddress", user.getEmailAddress());
			          jsonResponse.put(catJSON); 
				} catch (PortalException e) {
					e.printStackTrace();
				}
			}
		}
		resourceResponse.getWriter().print(jsonResponse.toString()); 
        super.serveResource(resourceRequest, resourceResponse);
    }

}