package com.everis.messages.service.builder.service.notification10.utils;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.petra.string.StringPool;
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
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {}, service = Notifications10Utils.class)

public class Notifications10Utils {

	private static final Log logger = LogFactoryUtil.getLog(Notifications10Utils.class);

	public void notifySubscribers(HashSet<User> userList, ServiceContext serviceContext, long resourcePrimaryKey)
			throws PortalException {

		logger.debug("notifySubscribers... Notifications10");

		JournalArticle journalArticle = JournalArticleLocalServiceUtil.fetchLatestArticle(resourcePrimaryKey);

		String urlLink = "/group/guest/-/".concat(journalArticle.getUrlTitle());
		String link = " <a style='color:#70ada3;' onclick='@LINK1'> Consultation Page</a>";

		logger.debug("resourcePrimaryKey: " + resourcePrimaryKey);
		String title = StringPool.BLANK, body = StringPool.BLANK;

		logger.debug("journalArticle: " + journalArticle);
		try {
			Document document = SAXReaderUtil.read(journalArticle.getContent());
			logger.debug("try....");
			String nameID = "TextIDChangeProposal";
			Node nodeId = document.selectSingleNode("//root//dynamic-element[@name='" + nameID + "']//dynamic-content");
			logger.debug("nodeId: " + nodeId);
			String cpReference = nodeId.getText();
			logger.debug("cpReference: " + cpReference);

			String consultationTitle = journalArticle.getTitle();
			logger.debug("consultationTitle: " + consultationTitle);
			String nameDate = "TextDate";
			Node nodeDate = document
					.selectSingleNode("//root//dynamic-element[@name='" + nameDate + "']//dynamic-content");
			logger.debug("nodeDate: " + nodeDate);
			String consultationDate = nodeDate.getText();
			logger.debug("consultationDate: " + consultationDate);

			String formattedDate = null;
			try {
				Date dateParse = new SimpleDateFormat("yyyy-MM-dd").parse(consultationDate);

				formattedDate = new SimpleDateFormat("dd MMMM yyyy").format(dateParse);
			} catch (ParseException e) {
				logger.error("Error parssing Date comments");

			}

			title = "Consultation response deadline has been updated";
			body = "The response deadline has been updated for the Consultation related to a " + consultationTitle
					+ ". The closing date for responses is now " + formattedDate + ". Go to the " + link
					+ " to view the Consultation.";

		} catch (DocumentException e1) {
			logger.error("Error reading journal article... " + e1);
		}

		String entryTitle = title;

		String fromName = PropsUtil.get(CustomNotifications10Constants.EMAIL_FROM_NAME);
		String fromAddress = GetterUtil.getString(PropsUtil.get(CustomNotifications10Constants.EMAIL_FROM_ADDRESS),
				PropsUtil.get(PropsKeys.ADMIN_EMAIL_FROM_ADDRESS));

		CustomSubscription10Sender subscriptionSender = new CustomSubscription10Sender();

		Map<Locale, String> subjectMap = new HashMap<Locale, String>();
		subjectMap.put(LocaleUtil.ENGLISH, title);
		subscriptionSender.setLocalizedSubjectMap(subjectMap);

		Map<Locale, String> bodyMap = new HashMap<Locale, String>();
		bodyMap.put(LocaleUtil.ENGLISH, body);
		subscriptionSender.setLocalizedBodyMap(bodyMap);

		subscriptionSender.setEntryURL(urlLink);

		subscriptionSender.setClassPK(0);
		subscriptionSender.setClassName(Notifications10Utils.class.getName());
		subscriptionSender.setCompanyId(CompanyThreadLocal.getCompanyId());

		// subscriptionSender.setCurrentUserId(userId);
		subscriptionSender.setEntryTitle(entryTitle);
		subscriptionSender.setFrom(fromAddress, fromName);
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setSubject(title);
		subscriptionSender.setBody(body);

		subscriptionSender.setMailId("custom_notitication10", 0);

		int notificationType = CustomNotification10Type.NOTIFICATION_TYPE_USER_10;

		subscriptionSender.setNotificationType(notificationType);

		String portletId = Notification10PortletKeys.NOTIFICATION10;

		subscriptionSender.setPortletId(portletId);

		subscriptionSender.setReplyToAddress(fromAddress);
		subscriptionSender.setServiceContext(serviceContext);

		subscriptionSender.addPersistedSubscribers(Notifications10Utils.class.getName(), 0);

		subscriptionSender.flushNotificationsAsync();

		try {
			for (User user : userList) {
				subscriptionSender.sendNotification10(user);
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
				logger.debug("Segment Name: " + se.getNameCurrentValue());
				logger.debug("SegmentsEntryId: " + se.getSegmentsEntryId());

				String segmentName = se.getNameCurrentValue();
				String segmentToCompare = segmentName.substring(segmentName.indexOf("-") + 1);
				logger.debug("Segment Name after removing impact: " + segmentToCompare);


				Document document = SAXReaderUtil.read(journalArticle.getContent());

				XPath xPathSelector = SAXReaderUtil
				.createXPath("//root//dynamic-element[@name='Consultation_TargetedAudience']//dynamic-content//option");
				List<Node> ImpactNodes = xPathSelector.selectNodes(document);

				logger.debug("Getting selected Targeted Audiences");
				for (Node node : ImpactNodes) {
					logger.debug(" Targeted Audience Impact " + node.getText());
					logger.debug(" Targeted Audience Segment " + segmentToCompare);


					 if (node.getText().equals(segmentToCompare) ) {

							List<SegmentsEntryRel> segmentsEntryRel = SegmentsEntryRelLocalServiceUtil
									.getSegmentsEntryRels(se.getSegmentsEntryId());

							for (SegmentsEntryRel segmentsEntryRelUsers : segmentsEntryRel) {
								logger.debug("================Users number" + segmentsEntryRelUsers);

								long UserIds = segmentsEntryRelUsers.getClassPK();

								User users = UserLocalServiceUtil.getUser(UserIds);
								logger.debug("Users " + users);
								recipients.add(users);

							}

							}


				}

			}
		} catch (PortalException e) {
			logger.error("Error getting recipients", e);
		} catch (DocumentException e) {

			logger.error("Error getting documnent journal article", e);
		}

		logger.debug(" Users to send notification 10 to" + recipients.size() );
		return recipients;
	}
}
