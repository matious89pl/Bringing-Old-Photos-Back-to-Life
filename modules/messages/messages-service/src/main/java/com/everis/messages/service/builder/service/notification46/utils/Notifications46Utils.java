package com.everis.messages.service.builder.service.notification46.utils;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.segments.model.SegmentsEntry;
import com.liferay.segments.model.SegmentsEntryRel;
import com.liferay.segments.service.SegmentsEntryLocalServiceUtil;
import com.liferay.segments.service.SegmentsEntryRelLocalServiceUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {}, service = Notifications46Utils.class)

public class Notifications46Utils {

	private static final Log logger = LogFactoryUtil.getLog(Notifications46Utils.class);

	public void notifySubscribers(HashSet<User> userList, ServiceContext serviceContext, String notificationTitle,
			String notificationBody, long currentUserId, String webEnabled, String emailEnabled)
			throws PortalException {

		String title = StringPool.BLANK, body = StringPool.BLANK;

		title = notificationTitle;
		body = notificationBody;

		logger.debug("Subject + Body: " + title + " - " + body);

		/**
		 * Set up the EMAIL TYPE custom notification
		 */
		String entryTitle = title;

		String fromName = PropsUtil.get(CustomNotifications46Constants.EMAIL_FROM_NAME);
		String fromAddress = GetterUtil.getString(PropsUtil.get(CustomNotifications46Constants.EMAIL_FROM_ADDRESS),
				PropsUtil.get(PropsKeys.ADMIN_EMAIL_FROM_ADDRESS));

		CustomSubscription46Sender subscriptionSender = new CustomSubscription46Sender();

		Map<Locale, String> subjectMap = new HashMap<Locale, String>();
		subjectMap.put(LocaleUtil.ENGLISH, title);
		subscriptionSender.setLocalizedSubjectMap(subjectMap);

		Map<Locale, String> bodyMap = new HashMap<Locale, String>();
		bodyMap.put(LocaleUtil.ENGLISH, body);
		subscriptionSender.setLocalizedBodyMap(bodyMap);

		// subscriptionSender.setEntryURL(urlLink);

		subscriptionSender.setClassPK(0);
		subscriptionSender.setClassName(Notifications46Utils.class.getName());
		subscriptionSender.setCompanyId(CompanyThreadLocal.getCompanyId());

		subscriptionSender.setCurrentUserId(currentUserId);
		subscriptionSender.setEntryTitle(entryTitle);
		subscriptionSender.setFrom(fromAddress, fromName);
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setSubject(title);
		subscriptionSender.setBody(body);

		subscriptionSender.setMailId("user", UserLocalServiceUtil.getUser(currentUserId));

		subscriptionSender.addRuntimeSubscribers(UserLocalServiceUtil.getUser(currentUserId).getEmailAddress(),
				UserLocalServiceUtil.getUser(currentUserId).getFullName());

		int notificationType = CustomNotification46Type.NOTIFICATION_TYPE_USER_46;

		subscriptionSender.setNotificationType(notificationType);

		String portletId = Notification46PortletKeys.NOTIFICATION46;

		subscriptionSender.setPortletId(portletId);

		subscriptionSender.setReplyToAddress(fromAddress);
		subscriptionSender.setServiceContext(serviceContext);

		subscriptionSender.addPersistedSubscribers(Notifications46Utils.class.getName(), 0);

		/*
		 * subscriptionSender.flushNotificationsAsync();
		 */

		try {

			for (User user : userList) {

				subscriptionSender.sendNotification46(user, webEnabled, emailEnabled);

			}

		} catch (Exception e) {
			logger.error("Error sending the notification: " + e);
		}

	}

	/**
	 * Recipients who has an specific Segments
	 * 
	 * @param List Users than belong to a Segment
	 * @return the recipients that has an specific Segments
	 */
	public HashSet<User> getRecipients(String segment) {

		HashSet<User> recipients = new HashSet<User>();
		logger.info("Segment name parameter: "+segment);
		DynamicQuery dynamicQuery = SegmentsEntryLocalServiceUtil.dynamicQuery();
		dynamicQuery.add(RestrictionsFactoryUtil.like("name","%<Name language-id=\"en_GB\">"+segment+"</Name>%"));
		List<SegmentsEntry> segmentList = SegmentsEntryLocalServiceUtil.dynamicQuery(dynamicQuery);
		logger.info("Number of segments matching the name parameter found: "+segmentList.size());

		if(segmentList.size()>0) {
			SegmentsEntry foundSegment = segmentList.get(0);
			logger.info("Found Segment!: "+foundSegment.getName("en_GB"));
			List<SegmentsEntryRel> segmentsEntryRel = SegmentsEntryRelLocalServiceUtil
					.getSegmentsEntryRels(foundSegment.getSegmentsEntryId());
			for (SegmentsEntryRel segmentsEntryRelUsers : segmentsEntryRel) {

				long userId = segmentsEntryRelUsers.getClassPK();

				User user = UserLocalServiceUtil.fetchUser(userId);
				if(Validator.isNotNull(user)) {
					recipients.add(user);
				} else {
					logger.info("No user exists with the id "+userId);
				}
			}
		} else {
			logger.info("No segment found with the name: "+segment);
		}

		return recipients;
	}
}
