package com.everis.messages.service.builder.service.notification16.utils;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroupRole;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.kernel.xml.XPath;
import com.liferay.segments.model.SegmentsEntry;
import com.liferay.segments.model.SegmentsEntryRel;
import com.liferay.segments.service.SegmentsEntryLocalServiceUtil;
import com.liferay.segments.service.SegmentsEntryRelLocalServiceUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {}, service = Notifications16Utils.class)

public class Notifications16Utils {

	private static final Log logger = LogFactoryUtil.getLog(Notifications16Utils.class);

	public void notifySubscribers(HashSet<User> userList, ServiceContext serviceContext, long resourcePK)
			throws PortalException {

		logger.debug("resourcePK: " + resourcePK);
		JournalArticle journalArticle = JournalArticleLocalServiceUtil.fetchLatestArticle(resourcePK);
		logger.debug("journalArticle: " + journalArticle);
		String title = StringPool.BLANK, body = StringPool.BLANK;

		String urlLink = "/group/guest/-/".concat(journalArticle.getUrlTitle());
		String initialAssessmentReportLink = "<a class='notification16IAReportLink' style='color:#70ada3;' onclick='@LINK1'> Change Proposal Page</a>";
		Document document = null;
		try {
			document = SAXReaderUtil.read(journalArticle.getContent());
		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String cpReference = "ChangeProposalReference";
		Node nodeCpReference = document
				.selectSingleNode("//root//dynamic-element[@name='" + cpReference + "']//dynamic-content");
		logger.info("nodeDate: " + nodeCpReference);
		String changeProposalReference = nodeCpReference.getText();
		logger.info("cpReference: " + cpReference);

		String nameDate = "IARCommentsDeadline";
		Node nodeDate = document.selectSingleNode("//root//dynamic-element[@name='" + nameDate + "']//dynamic-content");
		logger.info("nodeDate: " + nodeDate);
		String changeProposalDate = nodeDate.getText();
		logger.info("changeProposalDate: " + changeProposalDate);

		String formattedDate = null;
		try {
			Date dateParse = new SimpleDateFormat("yyyy-MM-dd").parse(changeProposalDate);

			formattedDate = new SimpleDateFormat("dd MMMM yyyy").format(dateParse);
		} catch (ParseException e) {
			logger.error("Error parssing Date comments");

		}

		title = "Initial Assessment Report published for comments";
		body = "An Initial Assessment Report for " + changeProposalReference
				+ " has been published for comments. Go to the " + initialAssessmentReportLink
				+ " to view the report. Closing date for comments is " + formattedDate;

		logger.debug("Subject + Body: " + title + " - " + body);

		/**
		 * Set up the EMAIL TYPE custom notification
		 */
		String entryTitle = title;

		String fromName = PropsUtil.get(CustomNotifications16Constants.EMAIL_FROM_NAME);
		String fromAddress = GetterUtil.getString(PropsUtil.get(CustomNotifications16Constants.EMAIL_FROM_ADDRESS),
				PropsUtil.get(PropsKeys.ADMIN_EMAIL_FROM_ADDRESS));

		CustomSubscription16Sender subscriptionSender = new CustomSubscription16Sender();

		Map<Locale, String> subjectMap = new HashMap<Locale, String>();
		subjectMap.put(LocaleUtil.ENGLISH, title);
		subscriptionSender.setLocalizedSubjectMap(subjectMap);

		Map<Locale, String> bodyMap = new HashMap<Locale, String>();
		bodyMap.put(LocaleUtil.ENGLISH, body);
		subscriptionSender.setLocalizedBodyMap(bodyMap);

		subscriptionSender.setEntryURL(urlLink);

		subscriptionSender.setClassPK(0);
		subscriptionSender.setClassName(Notifications16Utils.class.getName());
		subscriptionSender.setCompanyId(CompanyThreadLocal.getCompanyId());

		// subscriptionSender.setCurrentUserId(userId);
		subscriptionSender.setEntryTitle(entryTitle);
		subscriptionSender.setFrom(fromAddress, fromName);
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setSubject(title);
		subscriptionSender.setBody(body);

		subscriptionSender.setMailId("custom_notitication16 to stake holder", 0);

		int notificationType = CustomNotification16Type.NOTIFICATION_TYPE_USER_16;

		subscriptionSender.setNotificationType(notificationType);

		String portletId = Notification16PortletKeys.NOTIFICATION16;

		subscriptionSender.setPortletId(portletId);

		subscriptionSender.setReplyToAddress(fromAddress);
		subscriptionSender.setServiceContext(serviceContext);

		subscriptionSender.addPersistedSubscribers(Notifications16Utils.class.getName(), 0);

		subscriptionSender.flushNotificationsAsync();

		try {

			for (User user : userList) {
				logger.info("User from userList..." + user.getScreenName());
				subscriptionSender.sendNotification16(user);
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
	public HashSet<User> getRecipients(long articleId) {

		JournalArticle journalArticle = JournalArticleLocalServiceUtil.fetchLatestArticle(articleId);
		HashSet<User> recipients = new HashSet<User>();

		try {
			List<SegmentsEntry> segmentsEntry = SegmentsEntryLocalServiceUtil.getSegmentsEntries(0,
					SegmentsEntryLocalServiceUtil.getSegmentsEntriesCount());

			for (SegmentsEntry se : segmentsEntry) {

				logger.info("Segment Name: " + se.getNameCurrentValue());
				logger.info("SegmentsEntryId: " + se.getSegmentsEntryId());

					if (se.getNameCurrentValue().equals("Initial Change Proposal")) {

						List<SegmentsEntryRel> segmentsEntryRel = SegmentsEntryRelLocalServiceUtil
								.getSegmentsEntryRels(se.getSegmentsEntryId());

						for (SegmentsEntryRel segmentsEntryRelUsers : segmentsEntryRel) {

							long UserIds = segmentsEntryRelUsers.getClassPK();

							User users = UserLocalServiceUtil.getUser(UserIds);

							recipients.add(users);

						}
					}

				

			}
		} catch (PortalException e) {
			logger.error("Error getting recipients", e);
		}

		return recipients;
	}
}
