package com.everis.messages.service.builder.service.impl;

import com.everis.messages.service.builder.exception.NoSuchMessagesException;
import com.everis.messages.service.builder.model.Messages;
import com.everis.messages.service.builder.service.base.MessagesLocalServiceBaseImpl;
import com.everis.messages.service.builder.service.notification04.utils.Notifications041Utils;
import com.everis.messages.service.builder.service.notification04.utils.Notifications04Utils;
import com.everis.messages.service.builder.service.notification05.utils.Notifications051Utils;
import com.everis.messages.service.builder.service.notification06.utils.Notifications061Utils;
import com.everis.messages.service.builder.service.notification07.utils.Notifications07Utils;
import com.everis.messages.service.builder.service.notification08.utils.Notifications08Utils;
import com.everis.messages.service.builder.service.notification09.utils.Notifications09Utils;
import com.everis.messages.service.builder.service.notification10.utils.Notifications10Utils;
import com.everis.messages.service.builder.service.notification11.utils.Notifications11Utils;
import com.everis.messages.service.builder.service.notification12.utils.Notifications12Utils;
import com.everis.messages.service.builder.service.notification13.utils.Notifications13Utils;
import com.everis.messages.service.builder.service.notification14.utils.Notifications14Utils;
import com.everis.messages.service.builder.service.notification15.utils.Notifications15Utils;
import com.everis.messages.service.builder.service.notification16.utils.Notifications16Utils;
import com.everis.messages.service.builder.service.notification17.utils.Notifications17Utils;
import com.everis.messages.service.builder.service.notification19.utils.Notifications19Utils;
import com.everis.messages.service.builder.service.notification20.utils.Notifications20Utils;
import com.everis.messages.service.builder.service.notification21.utils.Notifications21Utils;
import com.everis.messages.service.builder.service.notification22.utils.Notifications22Utils;
import com.everis.messages.service.builder.service.notification23.utils.Notifications23Utils;
import com.everis.messages.service.builder.service.notification24.utils.Notifications24Utils;
import com.everis.messages.service.builder.service.notification25.utils.Notifications25Utils;
import com.everis.messages.service.builder.service.notification26.utils.Notifications26Utils;
import com.everis.messages.service.builder.service.notification27.utils.Notifications27Utils;
import com.everis.messages.service.builder.service.notification28.utils.Notifications28Utils;
import com.everis.messages.service.builder.service.notification29.utils.Notifications29Utils;
import com.everis.messages.service.builder.service.notification30.utils.Notifications30Utils;
import com.everis.messages.service.builder.service.notification31.utils.Notifications31Utils;
import com.everis.messages.service.builder.service.notification32.utils.Notifications32Utils;
import com.everis.messages.service.builder.service.notification38.utils.Notifications38Utils;
import com.everis.messages.service.builder.service.notification39.utils.Notifications39Utils;
import com.everis.messages.service.builder.service.notification41.utils.Notifications41Utils;
import com.everis.messages.service.builder.service.notification42.utils.Notifications42Utils;
import com.everis.messages.service.builder.service.notification43.utils.Notifications43Utils;
import com.everis.messages.service.builder.service.notification44.utils.Notifications44Utils;
import com.everis.messages.service.builder.service.notification45.utils.Notifications45Utils;
import com.everis.messages.service.builder.service.notification46.utils.Notifications46Utils;
import com.everis.messages.service.builder.service.notification47.utils.Notifications47Utils;
import com.everis.messages.service.builder.service.notification48.utils.Notifications48Utils;
import com.everis.messages.service.builder.service.notification49.utils.Notifications49Utils;
import com.everis.messages.service.builder.service.notification50.utils.Notifications50Utils;
import com.everis.messages.service.builder.service.notification51.utils.Notifications51Utils;
import com.everis.messages.service.builder.service.notification52.utils.Notifications52Utils;
import com.everis.messages.service.builder.service.notification53.utils.Notifications53Utils;
import com.everis.messages.service.builder.service.notification54.utils.Notifications54Utils;
import com.everis.messages.service.builder.service.notification55.utils.Notifications55Utils;
import com.everis.messages.service.builder.service.notification56.utils.Notifications56Utils;
import com.everis.messages.service.builder.service.notification57.utils.Notifications57Utils;
import com.everis.messages.service.builder.service.notification58.utils.Notifications58Utils;
import com.everis.messages.service.builder.service.notification59.utils.Notifications59Utils;
import com.everis.messages.service.builder.service.notification60.utils.Notifications60Utils;
import com.everis.messages.service.builder.service.notification61.utils.Notifications61Utils;
import com.everis.messages.service.builder.service.notification62.utils.Notifications62Utils;
import com.everis.messages.service.builder.service.notification63.utils.Notifications63Utils;
import com.everis.messages.service.builder.service.notification64.utils.Notifications64Utils;
import com.everis.messages.service.builder.service.notification65.utils.Notifications65Utils;
import com.everis.messages.service.builder.service.notification66.utils.Notifications66Utils;
import com.everis.messages.service.builder.service.notification67.utils.Notifications67Utils;
import com.everis.messages.service.builder.service.notification68.utils.Notifications68Utils;
import com.everis.messages.service.builder.service.notification69.utils.Notifications69Utils;
import com.everis.messages.service.builder.service.notification70.utils.Notifications70Utils;
import com.everis.messages.service.builder.service.notification71.utils.Notifications71Utils;
import com.everis.messages.service.builder.service.notification72.utils.Notifications72Utils;
import com.everis.messages.service.builder.service.notification73.utils.Notifications73Utils;
import com.everis.messages.service.builder.service.notification74.utils.Notifications74Utils;
import com.everis.messages.service.builder.service.notification75.utils.Notifications75Utils;
import com.everis.messages.service.builder.service.notification76.utils.Notifications76Utils;
import com.everis.messages.service.builder.service.utils.NotificationUtils;
import com.everis.rec.ddl.journal.article.model.DDL_JournalArticle;
import com.everis.rec.ddl.journal.article.service.DDL_JournalArticleLocalServiceUtil;
import com.liferay.dynamic.data.lists.model.DDLRecordVersion;
import com.liferay.dynamic.data.lists.service.DDLRecordVersionLocalServiceUtil;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.util.GroupThreadLocal;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.xml.DocumentException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * The implementation of the messages local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * <code>com.everis.messages.service.builder.service.MessagesLocalService</code>
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagesLocalServiceBaseImpl
 */
@Component(property = "model.class.name=com.everis.messages.service.builder.model.Messages", service = AopService.class)
public class MessagesLocalServiceImpl extends MessagesLocalServiceBaseImpl {
	private static final Log logger = LogFactoryUtil.getLog(MessagesLocalServiceImpl.class);

	/*
	 * NOTE FOR DEVELOPERS: You must implement the finders methods here, and
	 * buildService to create them in xxxLocalServiceUtil.java
	 *
	 * Never reference this class directly. Use
	 * <code>com.everis.messages.service.builder.service.MessagesLocalService</code>
	 * via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use
	 * <code>com.everis.messages.service.builder.service.MessagesLocalServiceUtil</
	 * code>.
	 */

	public List<Messages> findByCompanyId(long companyId) {

		return messagesPersistence.findByCompanyId(companyId);

	}

	public int getCountByCompanyId(long companyId) {

		return messagesPersistence.countByCompanyId(companyId);

	}

	public List<Messages> findByCompanyId(long companyId, int start, int end) {

		return messagesPersistence.findByCompanyId(companyId, start, end);

	}

	public List<Messages> findByNameCompany(String name, long companyId) {

		return messagesPersistence.findByNameCompany(name, companyId);

	}

	public Messages findByNameCompany_First(String name, long companyId, OrderByComparator<Messages> orderByComparator)
			throws NoSuchMessagesException {

		return messagesPersistence.findByNameCompany_First(name, companyId, orderByComparator);

	}

	/*
	 * Custom notifications 01 was deleted because of conflicts
	 */

	/*
	 * Custom notifications 03 was deleted because of conflicts
	 */

	/*
	 * Custom notifications 04 to Stake holder when successfully
	 */

	public void sendNotification04ByUser(User user, long classPK) {

		logger.info("Send Custom Notification by user");

		List<User> userList = new ArrayList<User>();
		List<String> roleNames = new ArrayList<>();

		/**
		 * We have and Admin and we need to collect the event
		 */

		try {

			roleNames.add(RoleConstants.ADMINISTRATOR);

			userList = _notifications04Utils.getRecipients(user.getCompanyId(), user.getGroupId(), roleNames);

			_notifications04Utils.notifySubscribers(userList, user.getUserId(), user.getCompanyId(), null, classPK);

		} catch (PortalException e) {
			logger.error("Error: " + e);
		}

	}

	public void sendNotification04ByUserByRole(User user, String role, long classPK) {

		logger.info("Send Custom Notification by user and role");

		List<User> userList = new ArrayList<User>();
		List<String> roleNames = new ArrayList<>();

		try {

			roleNames.add(role);

			userList = _notifications041Utils.getRecipients(user.getCompanyId(), user.getGroupId(), roleNames);

			_notifications041Utils.notifySubscribers(userList, user.getUserId(), user.getCompanyId(), null, classPK);

		} catch (PortalException e) {
			logger.error("Error: " + e);
		}

	}

	// Custom notifications 05 by user and Link accepted

	public void sendNotification051ByUserByUrl(User user, long classPK) {

		logger.info("Send Custom Notification by user and link");

		try {
			_notifications051Utils.notifySubscribers(user, user.getUserId(), user.getCompanyId(), null, classPK);

		} catch (PortalException e) {
			logger.error("Error: " + e);
		}

	}

	// Custom notifications 06 by user and Link rejected
	public void sendNotification061ByUserByUrl(User user, String comments, String knowledgeRepositoryURL,
			String raiseTicketURL, long classPK) {

		logger.info("Sending Custom Notification 06 - CP Rejected");

		try {
			_notifications061Utils.notifySubscribers(user, comments, user.getUserId(), user.getCompanyId(), null,
					knowledgeRepositoryURL, raiseTicketURL, classPK);

		} catch (PortalException e) {
			logger.error("Error: " + e);
		}

	}

	public void sendNotification07(long resourcePK) {
		logger.info("Sending Custom Notification 07 - Preliminary Assessment Report Published");

		HashSet<User> userList = new HashSet<User>();

		try {
			userList = _notifications07Utils.getRecipients(resourcePK);

			logger.info("users returned to send notification " + userList.size());

			_notifications07Utils.notifySubscribers(userList, null, resourcePK);
		} catch (PortalException e) {
			logger.error("Error Custom Notification 07: " + e);
		}
	}

	public void sendNotification08(User user, String classPK) {
		logger.info("Sending Custom Notification 08");

		List<User> userList = new ArrayList<User>();
		List<String> roleNames = new ArrayList<>();

		try {

			roleNames.add("REC_Stakeholder");
			roleNames.add("Change_Management_Team");

			userList = _notifications08Utils.getRecipients(user.getCompanyId(), user.getGroupId(), roleNames);

			_notifications08Utils.notifySubscribers(userList, user.getUserId(), user.getCompanyId(), null, classPK);

		} catch (PortalException e) {
			logger.error("Error: " + e);
		}
	}

	public void sendNotification09ByUser(User user, long classPK) {

		logger.info("Send Custom Notification by user");

		List<User> userList = new ArrayList<User>();
		List<String> roleNames = new ArrayList<>();

		/**
		 * We have and Admin and we need to collect the event
		 */

		try {

			roleNames.add("Change_Management_Team");

			userList = _notifications09Utils.getRecipients(user.getCompanyId(), user.getGroupId(), roleNames);

			_notifications09Utils.notifySubscribers(userList, user.getUserId(), user.getCompanyId(), null, classPK);

		} catch (PortalException e) {
			logger.error("Error: " + e);
		}

	}

	public void sendNotification10(long articleId) {
		logger.info("Sending Custom Notification 10");

		HashSet<User> userList = new HashSet<User>();

		try {
			userList = _notifications10Utils.getRecipients(articleId);

			logger.info("users returned to send notification " + userList.size());

			_notifications10Utils.notifySubscribers(userList, null, articleId);
		} catch (PortalException e) {
			logger.error("Error Custom Notification 10: " + e);
		}

	}

	public void sendNotification11(long articleId) {
		logger.info("Sending Custom Notification 11");

		HashSet<User> userList = new HashSet<User>();

		try {
			userList = _notifications11Utils.getRecipients(articleId);

			logger.info("users returned to send notification " + userList.size());

			_notifications11Utils.notifySubscribers(userList, null, articleId);
		} catch (PortalException e) {
			logger.error("Error Custom Notification 11: " + e);
		}

	}

	public void sendNotification12(long resourcePK) {
		logger.info("Sending Custom Notification 12 - Final Assessment Report Published");

		HashSet<User> userList = new HashSet<User>();

		try {
			userList = _notifications12Utils.getRecipients(resourcePK);

			logger.info("users returned to send notification " + userList.size());

			_notifications12Utils.notifySubscribers(userList, null, resourcePK);
		} catch (PortalException e) {
			logger.error("Error Custom Notification 07: " + e);
		}
	}

	public void sendNotification13(long articleId) {
		logger.info("Sending Custom Notification 13");

		HashSet<User> userList = new HashSet<User>();
		try {
			userList = _notifications13Utils.getRecipients(articleId);

			logger.info("users returned to send notification " + userList.size());

			_notifications13Utils.notifySubscribers(userList, null, articleId);

		} catch (PortalException e) {
			logger.error("Error: " + e);
		}
	}

	public void sendNotification14(long articleId) {
		logger.info("Sending Custom Notification 14");

		HashSet<User> userList = new HashSet<User>();

		try {

			userList = _notifications14Utils.getRecipients(articleId);

			logger.info("users returned to send notification " + userList.size());

			_notifications14Utils.notifySubscribers(userList, null, articleId);

		} catch (PortalException e) {
			logger.error("Error: " + e);
		}
	}

	public void sendNotification15(long companyId, long groupId, String cPReference, String commentsDeadline,
			long resourcePrimKey, String email) {
		logger.info("Sending Custom Notification 15");

		List<User> userList = new ArrayList<User>();
		List<String> roleNames = new ArrayList<>();

		try {

			roleNames.add("Change_Management_Team");

			userList = _notifications15Utils.getRecipients(companyId, groupId, roleNames);

			_notifications15Utils.notifySubscribers(userList, companyId, null, cPReference, commentsDeadline,
					resourcePrimKey, email);

		} catch (Exception e) {
			logger.error("Error: " + e);
		}
	}

	public void sendNotification16(long resourcePK) {
		logger.info("Sending Custom Notification 16 - Initial Assessment Report Published");

		HashSet<User> userList = new HashSet<User>();

		try {
			userList = _notifications16Utils.getRecipients(resourcePK);

			logger.info("users returned to send notification " + userList.size());

			_notifications16Utils.notifySubscribers(userList, null, resourcePK);
		} catch (PortalException e) {
			logger.error("Error Custom Notification 16: " + e);
		}
	}

	public void sendNotification17ByUserByRole(User user, String role, long classPK) {

		logger.info("Send Custom Notification by user and role - Approved Change Proposal Notification");

		List<User> userList = new ArrayList<User>();
		List<String> roleNames = new ArrayList<>();

		try {

			roleNames.add(role);

			userList = _notifications17Utils.getRecipients(user.getCompanyId(), user.getGroupId(), roleNames);

			_notifications17Utils.notifySubscribers(userList, user.getUserId(), user.getCompanyId(), null, classPK);

		} catch (PortalException e) {
			logger.error("Error: " + e);
		}

	}

	public void sendNotification19(User user, long classPK) {
		logger.info("Sending Custom Notification 19 - Voluntary Withdrawal Requested - To RTS and RPA");
		List<User> userList = new ArrayList<User>();

		try {
			userList = _notifications19Utils.getRecipients(user.getCompanyId(), user.getGroupId());

			_notifications19Utils.notifySubscribers(userList, user.getUserId(), user.getCompanyId(), null);

		} catch (PortalException e) {
			logger.error("Error Custom Notification 19: " + e);
		}
	}

	public void sendNotification20(User user, long classPK) {
		logger.info("Sending Custom Notification 20 - Voluntary Withdrawal Accepted - To RTS and RPA");
		List<User> userList = new ArrayList<User>();

		try {
			userList = _notifications20Utils.getRecipients(user.getCompanyId(), user.getGroupId());

			_notifications20Utils.notifySubscribers(userList, user.getUserId(), user.getCompanyId(), null);

		} catch (PortalException e) {
			logger.error("Error Custom Notification 20: " + e);
		}
	}

	public void sendNotification21(User user, long classPK) {
		logger.info("Sending Custom Notification 21 - Voluntary Withdrawal Rejected - To RTS and RPA");
		List<User> userList = new ArrayList<User>();

		try {
			userList = _notifications21Utils.getRecipients(user.getCompanyId(), user.getGroupId());

			_notifications21Utils.notifySubscribers(userList, user.getUserId(), user.getCompanyId(), null);

		} catch (PortalException e) {
			logger.error("Error Custom Notification 21: " + e);
		}
	}

	public void sendNotification22(User user, long journalArticleUserId, long resourcePrimaryKey) {
		logger.info("Sending Custom Notification 22 - Accept Category 3 Change Proposal");
		HashSet<User> userList = new HashSet<User>();

		try {

			userList = _notifications22Utils.getRecipients(user.getCompanyId(), user.getGroupId(),
					journalArticleUserId);

			_notifications22Utils.notifySubscribers(userList, user.getUserId(), user.getCompanyId(), null,
					resourcePrimaryKey);

		} catch (PortalException e) {
			logger.error("Error Custom Notification 22: " + e);
		}
	}

	public void sendNotification23(User user, String role, long resourcePrimaryKey, String commentsRejectionWorkflow) {
		logger.info("Sending Custom Notification 23 - Accept Category 3 Change Proposal");
		List<User> userList = new ArrayList<User>();
		List<String> roleNames = new ArrayList<>();

		try {

			roleNames.add(role);
			userList = _notifications23Utils.getRecipients(user.getCompanyId(), user.getGroupId(), roleNames);

			_notifications23Utils.notifySubscribers(userList, user.getUserId(), user.getCompanyId(), null,
					resourcePrimaryKey, commentsRejectionWorkflow);

		} catch (PortalException e) {
			logger.error("Error Custom Notification 23: " + e);
		}
	}

	public void sendNotification24(User user, long resourcePrimaryKey) {
		logger.info("Sending Custom Notification 24 - A New Category 3 Change is available");
		List<User> userList = new ArrayList<User>();

		try {

			userList = _notifications24Utils.getRecipients(user.getCompanyId(), user.getGroupId());

			_notifications24Utils.notifySubscribers(userList, user.getUserId(), user.getCompanyId(), null,
					resourcePrimaryKey);

		} catch (PortalException e) {
			logger.error("Error Custom Notification 24: " + e);
		}
	}

	public void sendNotification25(User user, long resourcePrimaryKey) {
		logger.info("Sending Custom Notification 25 - 	Category 3 Change Awaiting Implementation");
		List<User> userList = new ArrayList<User>();

		try {

			userList = _notifications25Utils.getRecipients(user.getCompanyId(), user.getGroupId());

			_notifications25Utils.notifySubscribers(userList, user.getUserId(), user.getCompanyId(), null,
					resourcePrimaryKey);

		} catch (PortalException e) {
			logger.error("Error Custom Notification 25: " + e);
		}
	}

	public void sendNotification26(long userId, long recordVersionId, long siteGroupId) throws DocumentException {
		logger.info("Sending Custom Notification 26 - Invalid nomination");

		try {

			String nomineeEmailAddress = NotificationUtils.getNomineeEmailAddressFromDDLRecord(recordVersionId);
			String nominatorEmailAddress = NotificationUtils.getNominatorEmailAddressFromDDLRecord(recordVersionId);

			HashSet<User> userList = _notifications26Utils.getRecipients(nomineeEmailAddress, nominatorEmailAddress);

			DDLRecordVersion ddl = DDLRecordVersionLocalServiceUtil.getRecordVersion(recordVersionId);

			DDL_JournalArticle article = DDL_JournalArticleLocalServiceUtil.getDDL_JournalArticle(ddl.getRecordSetId());

			_notifications26Utils.notifySubscribers(userList, userId, recordVersionId,
					article.getJournalResourcePrimKey(), siteGroupId, null);

		} catch (PortalException e) {
			logger.error("Error Sending Custom Notification 26: " + e);
		}
	}

	public void sendNotification27(long userId, long recordVersionId, long siteGroupId) {
		logger.info("Sending Custom Notification 27 - Valid nomination");

		try {
			HashSet<User> userList = new HashSet<User>();
			userList = _notifications27Utils.getRecipients();

			DDLRecordVersion ddl = DDLRecordVersionLocalServiceUtil.getRecordVersion(recordVersionId);

			DDL_JournalArticle article = DDL_JournalArticleLocalServiceUtil.getDDL_JournalArticle(ddl.getRecordSetId());

			_notifications27Utils.notifySubscribers(userList, userId, article.getJournalResourcePrimKey(), siteGroupId,
					null);

		} catch (PortalException e) {
			logger.error("Error Sending Custom Notification 27: " + e);
		}
	}

	public void sendNotification28(User user, long classPK) {
		logger.info("Sending Custom Notification 28 ");
		List<User> userList = new ArrayList<User>();
		String recipientFromDDL = NotificationUtils.getRecipientFromDDLRecord(classPK);

		try {

			userList = _notifications28Utils.getRecipients(user.getCompanyId(), recipientFromDDL);

			_notifications28Utils.notifySubscribers(userList, user.getUserId(), user.getCompanyId());

		} catch (PortalException e) {
			logger.error("Error Custom Notification 28: " + e);
		}
	}

	public void sendNotification29(long userId, long resourcePrimaryKey, long siteGroupId) {
		logger.debug("Sending Custom Notification 29");

		List<User> userList = new ArrayList<User>();

		try {

			userList = _notifications29Utils.getRecipients(siteGroupId);

			_notifications29Utils.notifySubscribers(userList, userId, null, resourcePrimaryKey, siteGroupId);

		} catch (PortalException e) {
			logger.error("Error: " + e);
		}

	}

	public void sendNotification30(long userId, long resourcePrimaryKey, long siteGroupId) {
		logger.debug("Sending Custom Notification 31");

		List<User> userList = new ArrayList<User>();

		try {

			userList = _notifications30Utils.getRecipients(siteGroupId);

			_notifications30Utils.notifySubscribers(userList, userId, null, resourcePrimaryKey, siteGroupId);

		} catch (PortalException e) {
			logger.error("Error: " + e);
		}

	}

	public void sendNotification31(long companyId, long groupId, long userId, String roleNames, String VotingLink,
			String titleOfNotification, String bodyOfNotification) {
		logger.debug("Sending Custom Notification 31");

		List<User> userList = new ArrayList<User>();

		try {

			userList = _notifications31Utils.getRecipients(groupId);

			_notifications31Utils.notifySubscribers(userList, userId, companyId, null, VotingLink, titleOfNotification,
					bodyOfNotification);

		} catch (PortalException e) {
			logger.error("Error: " + e);
		}

	}

	public void sendNotification32(User user, long resourcePrimaryKey) {
		logger.info("Sending Custom Notification 32 - A New Party Management is available");
		List<User> userList = new ArrayList<User>();

		try {

			userList = _notifications32Utils.getRecipients(user.getCompanyId(), user.getGroupId());

			_notifications32Utils.notifySubscribers(userList, user.getUserId(), user.getCompanyId(), null,
					resourcePrimaryKey);

		} catch (PortalException e) {
			logger.error("Error Custom Notification 32: " + e);
		}
	}

	public void sendNotification38(long userId, long resourcePrimaryKey, long groupId) {
		logger.info("Sending Custom Notification 38 - Action Log is available");
		List<User> userList = new ArrayList<User>();

		try {

			userList = _notifications38Utils.getRecipients(CompanyThreadLocal.getCompanyId(),
					GroupThreadLocal.getGroupId(), resourcePrimaryKey);

			_notifications38Utils.notifySubscribers(userList, userId, CompanyThreadLocal.getCompanyId(), null,
					resourcePrimaryKey, groupId);

		} catch (PortalException e) {
			logger.error("Error Custom Notification 38: " + e);
		}
	}

	public void sendNotification39(long userId, long resourcePrimaryKey, long siteGroupId, String role) {
		logger.info("Sending Custom Notification 39 - Nomination closed");

		HashSet<User> userList = new HashSet<User>();
		List<String> roleNames = new ArrayList<>();

		try {

			roleNames.add(role);

			userList = _notifications39Utils.getRecipients(roleNames);

			_notifications39Utils.notifySubscribers(userList, userId, resourcePrimaryKey, siteGroupId, null);

		} catch (PortalException e) {
			logger.error("Error: " + e);
		}
	}

	public void sendNotification41(long userId, long resourcePrimKey, long siteGroupId, String role) {
		logger.info("Sending Custom Notification 41 - Election Open");

		List<User> userList = new ArrayList<User>();
		List<String> roleNames = new ArrayList<>();

		try {

			roleNames.add(role);

			userList = _notifications41Utils.getRecipients(roleNames);

			_notifications41Utils.notifySubscribers(userList, userId, resourcePrimKey, siteGroupId, null);

		} catch (PortalException e) {
			logger.error("Error: " + e);
		}
	}

	public void sendNotification42(long userId, long resourcePrimaryKey, long siteGroupId, String role) {

		logger.info("Sending Custom Notification 42 - Election closed");

		HashSet<User> userList = new HashSet<User>();

		List<String> roleNames = new ArrayList<>();

		try {

			roleNames.add(role);

			userList = _notifications42Utils.getRecipients(roleNames);

			_notifications42Utils.notifySubscribers(userList, userId, resourcePrimaryKey, siteGroupId, null);

		} catch (PortalException e) {

			logger.error("Error: " + e);

		}

	}

	public void sendNotification43(long userId, long resourcePrimaryKey, long siteGroupId, String role) {

		logger.info("Sending Custom Notification 43 - Nomination open");

		HashSet<User> userList = new HashSet<User>();

		List<String> roleNames = new ArrayList<>();

		try {

			roleNames.add(role);

			userList = _notifications43Utils.getRecipients(roleNames);

			_notifications43Utils.notifySubscribers(userList, userId, resourcePrimaryKey, siteGroupId, null);

		} catch (PortalException e) {

			logger.error("Error: " + e);

		}

	}

	public void sendNotification44(long resourcePK) {
		logger.info("Sending Custom Notification 44 - Preliminary Assessment Report Published with new due date");

		HashSet<User> userList = new HashSet<User>();

		try {
			userList = _notifications44Utils.getRecipients(resourcePK);

			logger.info("users returned to send notification " + userList.size());

			_notifications44Utils.notifySubscribers(userList, null, resourcePK);
		} catch (PortalException e) {
			logger.error("Error Custom Notification 44: " + e);
		}
	}

	public void sendNotification45(long resourcePK) {
		logger.info("Sending Custom Notification 45 - Final Assessment Report Published with new due date");

		HashSet<User> userList = new HashSet<User>();

		try {
			userList = _notifications45Utils.getRecipients(resourcePK);

			logger.info("users returned to send notification " + userList.size());

			_notifications45Utils.notifySubscribers(userList, null, resourcePK);
		} catch (PortalException e) {
			logger.error("Error Custom Notification 45: " + e);
		}
	}

	public void sendNotification46(String notificationTitle, String notificationBody, String segment,
			long currentUserId, String webEnabled, String emailEnabled) {
		logger.info("Sending Custom Notification 46 - Segment Notification for RPA");

		HashSet<User> userList = new HashSet<User>();

		try {
			userList = _notifications46Utils.getRecipients(segment);

			logger.info("users returned to send notification " + userList.size());

			_notifications46Utils.notifySubscribers(userList, null, notificationTitle, notificationBody, currentUserId,
					webEnabled, emailEnabled);
		} catch (PortalException e) {
			logger.error("Error Custom Notification 46: " + e);
		}
	}

	public void sendNotification47(long currentUserId, long siteGroupId, String activityTitle, String urlEmail,
			long companyId) {
		logger.info("Sending Custom Notification 47");

		HashSet<User> userList = new HashSet<User>();

		try {
			Group group = GroupLocalServiceUtil.getGroup(siteGroupId);

			userList = _notifications47Utils.getRecipients(group.getClassPK(), siteGroupId, companyId);

			logger.info("users returned to send notification " + userList.size());

			_notifications47Utils.notifySubscribers(userList, null, currentUserId, siteGroupId, activityTitle,
					urlEmail);
		} catch (PortalException e) {
			logger.error("Error Custom Notification 47: " + e);
		}
	}

	public void sendNotification48(long currentUserId, long siteGroupId, String rfiTitle, String urlEmail,
			long companyId) {
		logger.info("Sending Custom Notification 48");

		HashSet<User> userList = new HashSet<User>();

		try {
			Group group = GroupLocalServiceUtil.getGroup(siteGroupId);

			userList = _notifications48Utils.getRecipients(group.getClassPK(), siteGroupId, companyId);

			logger.info("users returned to send notification " + userList.size());

			_notifications48Utils.notifySubscribers(userList, null, currentUserId, siteGroupId, rfiTitle, urlEmail);
		} catch (PortalException e) {
			logger.error("Error Custom Notification 48: " + e);
		}
	}

	public void sendNotification49(long currentUserId, long siteGroupId, String remedationTitle, String urlEmail,
			long companyId) {
		logger.info("Sending Custom Notification 49");

		HashSet<User> userList = new HashSet<User>();

		try {
			Group group = GroupLocalServiceUtil.getGroup(siteGroupId);

			userList = _notifications49Utils.getRecipients(group.getClassPK(), siteGroupId, companyId);

			logger.info("users returned to send notification " + userList.size());

			_notifications49Utils.notifySubscribers(userList, null, currentUserId, siteGroupId, remedationTitle,
					urlEmail);
		} catch (PortalException e) {
			logger.error("Error Custom Notification 49: " + e);
		}
	}

	public void sendNotification50(String portalURL, long currentUserId, long siteGroupId, String activityName,
			String urlEmail, long companyId) {
		logger.info("Sending Custom Notification 50");

		HashSet<User> userList = new HashSet<User>();

		try {
			Group group = GroupLocalServiceUtil.getGroup(siteGroupId);

			userList = _notifications50Utils.getRecipients(group.getClassPK(), siteGroupId, companyId);

			logger.info("users returned to send notification " + userList.size());

			_notifications50Utils.notifySubscribers(userList, null, currentUserId, siteGroupId, activityName, urlEmail);
		} catch (PortalException e) {
			logger.error("Error Custom Notification 50: " + e);
		}
	}

	public void sendNotification51(long currentUserId, long siteGroupId, String activityName, String urlEmail,
			long companyId) {
		logger.info("Sending Custom Notification 51");

		HashSet<User> userList = new HashSet<User>();

		try {
			Group group = GroupLocalServiceUtil.getGroup(siteGroupId);

			userList = _notifications51Utils.getRecipients(group.getClassPK(), siteGroupId, companyId);

			logger.info("users returned to send notification " + userList.size());

			_notifications51Utils.notifySubscribers(userList, null, currentUserId, siteGroupId, activityName, urlEmail);
		} catch (PortalException e) {
			logger.error("Error Custom Notification 51: " + e);
		}
	}

	public void sendNotification52(long currentUserId, long siteGroupId, String activityName, String urlEmail,
			long companyId) {
		logger.info("Sending Custom Notification 52");

		HashSet<User> userList = new HashSet<User>();
		logger.info("UserList " + userList);

		try {
			Group group = GroupLocalServiceUtil.getGroup(siteGroupId);

			userList = _notifications52Utils.getRecipients(group.getClassPK(), siteGroupId, companyId);

			logger.info("ClassPK " + group.getClassPK());
			logger.info("GroupId " + siteGroupId);
			logger.info("CompanyId " + companyId);
			logger.info("users returned to send notification " + userList.size());

			_notifications52Utils.notifySubscribers(userList, null, currentUserId, siteGroupId, activityName, urlEmail);
		} catch (PortalException e) {
			logger.error("Error Custom Notification 52: " + e);
		}
	}

	public void sendNotification53(long siteGroupId, String activityName, long companyId) {
		logger.info("Sending Custom Notification 53");

		HashSet<User> userList = new HashSet<User>();

		try {
			Group group = GroupLocalServiceUtil.getGroup(siteGroupId);

			userList = _notifications53Utils.getRecipients(group.getClassPK(), siteGroupId, companyId);

			logger.info("users returned to send notification " + userList.size());

			_notifications53Utils.notifySubscribers(userList, null, siteGroupId, activityName);
		} catch (PortalException e) {
			logger.error("Error Custom Notification 53: " + e);
		}
	}

	public void sendNotification54(long siteGroupId, String activityName, long companyId) {
		logger.info("Sending Custom Notification 54");

		HashSet<User> userList = new HashSet<User>();

		try {
			Group group = GroupLocalServiceUtil.getGroup(siteGroupId);

			userList = _notifications54Utils.getRecipients(group.getClassPK(), siteGroupId, companyId);

			logger.info("users returned to send notification " + userList.size());

			_notifications54Utils.notifySubscribers(userList, null, siteGroupId, activityName);
		} catch (PortalException e) {
			logger.error("Error Custom Notification 54: " + e);
		}
	}

	public void sendNotification55(long currentUserId, long siteGroupId, String rfiTitle, String urlEmail,
			long companyId) {
		logger.info("Sending Custom Notification 55");

		HashSet<User> userList = new HashSet<User>();

		try {
			Group group = GroupLocalServiceUtil.getGroup(siteGroupId);

			userList = _notifications55Utils.getRecipients(group.getClassPK(), siteGroupId, companyId);

			logger.info("users returned to send notification " + userList.size());

			_notifications55Utils.notifySubscribers(userList, null, currentUserId, siteGroupId, rfiTitle, urlEmail);
		} catch (PortalException e) {
			logger.error("Error Custom Notification 55: " + e);
		}
	}

	public void sendNotification56(long currentUserId, long siteGroupId, String rfiTitle, String urlEmail,
			long companyId) {
		logger.info("Sending Custom Notification 56");

		HashSet<User> userList = new HashSet<User>();

		try {
			Group group = GroupLocalServiceUtil.getGroup(siteGroupId);

			userList = _notifications56Utils.getRecipients(group.getClassPK(), siteGroupId, companyId);

			logger.info("users returned to send notification " + userList.size());

			_notifications56Utils.notifySubscribers(userList, null, currentUserId, siteGroupId, rfiTitle, urlEmail);
		} catch (PortalException e) {
			logger.error("Error Custom Notification 56: " + e);
		}
	}

	public void sendNotification57(long currentUserId, long siteGroupId, String rfiTitle, String urlEmail,
			long companyId) {
		logger.info("Sending Custom Notification 57");

		HashSet<User> userList = new HashSet<User>();

		try {
			Group group = GroupLocalServiceUtil.getGroup(siteGroupId);

			userList = _notifications57Utils.getRecipients(group.getClassPK(), siteGroupId, companyId);

			logger.info("users returned to send notification " + userList.size());

			_notifications57Utils.notifySubscribers(userList, null, currentUserId, siteGroupId, rfiTitle, urlEmail);
		} catch (PortalException e) {
			logger.error("Error Custom Notification 57: " + e);
		}
	}

	public void sendNotification58(long currentUserId, long siteGroupId, String rfiTitle, String urlEmail,
			long companyId) {
		logger.info("Sending Custom Notification 58");

		HashSet<User> userList = new HashSet<User>();

		try {
			Group group = GroupLocalServiceUtil.getGroup(siteGroupId);

			userList = _notifications58Utils.getRecipients(group.getClassPK(), siteGroupId, companyId);

			logger.info("ClassPK " + group.getClassPK());
			logger.info("GroupId " + siteGroupId);
			logger.info("CompanyId " + companyId);
			logger.info("users returned to send notification " + userList.size());

			_notifications58Utils.notifySubscribers(userList, null, currentUserId, siteGroupId, rfiTitle, urlEmail);
		} catch (PortalException e) {
			logger.error("Error Custom Notification 58: " + e);
		}
	}

	public void sendNotification59(long currentUserId, long siteGroupId, String rfiTitle, String urlEmail,
			long companyId) {
		logger.info("Sending Custom Notification 59");

		HashSet<User> userList = new HashSet<User>();

		try {
			Group group = GroupLocalServiceUtil.getGroup(siteGroupId);

			userList = _notifications59Utils.getRecipients(group.getClassPK(), siteGroupId, companyId);

			logger.info("users returned to send notification " + userList.size());

			_notifications59Utils.notifySubscribers(userList, null, currentUserId, siteGroupId, rfiTitle, urlEmail);
		} catch (PortalException e) {
			logger.error("Error Custom Notification 59: " + e);
		}
	}

	public void sendNotification60(long siteGroupId, String rfilogname, long companyId) {
		logger.info("Sending Custom Notification 60");

		HashSet<User> userList = new HashSet<User>();

		try {
			Group group = GroupLocalServiceUtil.getGroup(siteGroupId);

			userList = _notifications60Utils.getRecipients(group.getClassPK(), siteGroupId, companyId);

			logger.info("users returned to send notification " + userList.size());

			_notifications60Utils.notifySubscribers(userList, null, siteGroupId, rfilogname);
		} catch (PortalException e) {
			logger.error("Error Custom Notification 60: " + e);
		}
	}

	public void sendNotification61(long siteGroupId, String rfilogname, long companyId) {
		logger.info("Sending Custom Notification 61");

		HashSet<User> userList = new HashSet<User>();

		try {
			Group group = GroupLocalServiceUtil.getGroup(siteGroupId);

			userList = _notifications61Utils.getRecipients(group.getClassPK(), siteGroupId, companyId);

			logger.info("users returned to send notification " + userList.size());

			_notifications61Utils.notifySubscribers(userList, null, siteGroupId, rfilogname);
		} catch (PortalException e) {
			logger.error("Error Custom Notification 61: " + e);
		}
	}

	public void sendNotification62(long currentUserId, long siteGroupId, String remediationName, String urlEmail,
			long companyId) {
		logger.info("Sending Custom Notification 62");

		HashSet<User> userList = new HashSet<User>();

		try {
			Group group = GroupLocalServiceUtil.getGroup(siteGroupId);

			userList = _notifications62Utils.getRecipients(group.getClassPK(), siteGroupId, companyId);

			logger.info("users returned to send notification " + userList.size());

			_notifications62Utils.notifySubscribers(userList, null, currentUserId, siteGroupId, remediationName,
					urlEmail);
		} catch (PortalException e) {
			logger.error("Error Custom Notification 620: " + e);
		}
	}

	public void sendNotification63(long currentUserId, long siteGroupId, String remediationName, String urlEmail,
			long companyId) {
		logger.info("Sending Custom Notification 63");

		HashSet<User> userList = new HashSet<User>();

		try {
			Group group = GroupLocalServiceUtil.getGroup(siteGroupId);

			userList = _notifications63Utils.getRecipients(group.getClassPK(), siteGroupId, companyId);

			logger.info("users returned to send notification " + userList.size());

			_notifications63Utils.notifySubscribers(userList, null, currentUserId, siteGroupId, remediationName,
					urlEmail);
		} catch (PortalException e) {
			logger.error("Error Custom Notification 630: " + e);
		}
	}

	public void sendNotification64(long currentUserId, long siteGroupId, String remediationName, String urlEmail,
			long companyId) {
		logger.info("Sending Custom Notification 64");

		HashSet<User> userList = new HashSet<User>();

		try {
			Group group = GroupLocalServiceUtil.getGroup(siteGroupId);

			userList = _notifications64Utils.getRecipients(group.getClassPK(), siteGroupId, companyId);

			logger.info("users returned to send notification " + userList.size());

			_notifications64Utils.notifySubscribers(userList, null, currentUserId, siteGroupId, remediationName,
					urlEmail);
		} catch (PortalException e) {
			logger.error("Error Custom Notification 640: " + e);
		}
	}

	public void sendNotification65(long currentUserId, long siteGroupId, String rfiTitle, String urlEmail,
			long companyId) {
		logger.info("Sending Custom Notification 65");

		HashSet<User> userList = new HashSet<User>();

		try {
			Group group = GroupLocalServiceUtil.getGroup(siteGroupId);

			userList = _notifications65Utils.getRecipients(group.getClassPK(), siteGroupId, companyId);

			logger.info("users returned to send notification " + userList.size());

			_notifications65Utils.notifySubscribers(userList, null, currentUserId, siteGroupId, rfiTitle, urlEmail);
		} catch (PortalException e) {
			logger.error("Error Custom Notification 65: " + e);
		}
	}

	public void sendNotification66(long currentUserId, long siteGroupId, String rfiTitle, String urlEmail,
			long companyId) {
		logger.info("Sending Custom Notification 66");

		HashSet<User> userList = new HashSet<User>();

		try {
			Group group = GroupLocalServiceUtil.getGroup(siteGroupId);

			userList = _notifications66Utils.getRecipients(group.getClassPK(), siteGroupId, companyId);

			logger.info("users returned to send notification " + userList.size());

			_notifications66Utils.notifySubscribers(userList, null, currentUserId, siteGroupId, rfiTitle, urlEmail);
		} catch (PortalException e) {
			logger.error("Error Custom Notification 66: " + e);
		}
	}

	public void sendNotification67(long siteGroupId, String remediationName, long companyId) {
		logger.info("Sending Custom Notification 67");

		HashSet<User> userList = new HashSet<User>();

		try {
			Group group = GroupLocalServiceUtil.getGroup(siteGroupId);

			userList = _notifications67Utils.getRecipients(group.getClassPK(), siteGroupId, companyId);

			logger.info("users returned to send notification " + userList.size());

			_notifications67Utils.notifySubscribers(userList, null, siteGroupId, remediationName);
		} catch (PortalException e) {
			logger.error("Error Custom Notification 67: " + e);
		}
	}

	public void sendNotification68(long siteGroupId, String remediationName, long companyId) {
		logger.info("Sending Custom Notification 68");

		HashSet<User> userList = new HashSet<User>();

		try {
			Group group = GroupLocalServiceUtil.getGroup(siteGroupId);

			userList = _notifications68Utils.getRecipients(group.getClassPK(), siteGroupId, companyId);

			logger.info("users returned to send notification " + userList.size());

			_notifications68Utils.notifySubscribers(userList, null, siteGroupId, remediationName);
		} catch (PortalException e) {
			logger.error("Error Custom Notification 68: " + e);
		}
	}

	public void sendNotification69(long currentUserId, long siteGroupId, String activityTitle, String urlEmail,
			long companyId) {
		logger.info("Sending Custom Notification 69");

		HashSet<User> userList = new HashSet<User>();

		try {
			Group group = GroupLocalServiceUtil.getGroup(siteGroupId);

			userList = _notifications69Utils.getRecipients(group.getClassPK(), siteGroupId, companyId);

			logger.info("users returned to send notification " + userList.size());

			_notifications69Utils.notifySubscribers(userList, null, currentUserId, siteGroupId, activityTitle,
					urlEmail);
		} catch (PortalException e) {
			logger.error("Error Custom Notification 69: " + e);
		}
	}

	public void sendNotification70(long currentUserId, long siteGroupId, String activityName, String urlEmail,
			long companyId) {
		logger.info("Sending Custom Notification 70");

		HashSet<User> userList = new HashSet<User>();

		try {
			Group group = GroupLocalServiceUtil.getGroup(siteGroupId);

			userList = _notifications70Utils.getRecipients(group.getClassPK(), siteGroupId, companyId);

			logger.info("users returned to send notification " + userList.size());

			_notifications70Utils.notifySubscribers(userList, null, currentUserId, siteGroupId, activityName, urlEmail);
		} catch (PortalException e) {
			logger.error("Error Custom Notification 70: " + e);
		}
	}

	public void sendNotification71(long currentUserId, long siteGroupId, String activityName, String urlEmail,
			long companyId) {
		logger.info("Sending Custom Notification 71");

		HashSet<User> userList = new HashSet<User>();

		try {
			Group group = GroupLocalServiceUtil.getGroup(siteGroupId);

			userList = _notifications71Utils.getRecipients(group.getClassPK(), siteGroupId, companyId);

			logger.info("users returned to send notification " + userList.size());

			_notifications71Utils.notifySubscribers(userList, null, currentUserId, siteGroupId, activityName, urlEmail);
		} catch (PortalException e) {
			logger.error("Error Custom Notification 71: " + e);
		}
	}

	public void sendNotification72(long currentUserId, long siteGroupId, String activityName, String urlEmail,
			long companyId) {
		logger.info("Sending Custom Notification 72");

		HashSet<User> userList = new HashSet<User>();

		try {
			Group group = GroupLocalServiceUtil.getGroup(siteGroupId);

			userList = _notifications72Utils.getRecipients(group.getClassPK(), siteGroupId, companyId);

			logger.info("users returned to send notification " + userList.size());

			_notifications72Utils.notifySubscribers(userList, null, currentUserId, siteGroupId, activityName, urlEmail);
		} catch (PortalException e) {
			logger.error("Error Custom Notification 72: " + e);
		}
	}

	public void sendNotification73(long siteGroupId, String remediationName, long companyId) {
		logger.info("Sending Custom Notification 73");

		HashSet<User> userList = new HashSet<User>();

		try {
			Group group = GroupLocalServiceUtil.getGroup(siteGroupId);

			userList = _notifications73Utils.getRecipients(group.getClassPK(), siteGroupId, companyId);

			logger.info("users returned to send notification " + userList.size());

			_notifications73Utils.notifySubscribers(userList, null, siteGroupId, remediationName);
		} catch (PortalException e) {
			logger.error("Error Custom Notification 73: " + e);
		}
	}

	public void sendNotification74(long siteGroupId, String remediationName, long companyId) {
		logger.info("Sending Custom Notification 74");

		HashSet<User> userList = new HashSet<User>();

		try {
			Group group = GroupLocalServiceUtil.getGroup(siteGroupId);

			userList = _notifications74Utils.getRecipients(group.getClassPK(), siteGroupId, companyId);

			logger.info("users returned to send notification " + userList.size());

			_notifications74Utils.notifySubscribers(userList, null, siteGroupId, remediationName);
		} catch (PortalException e) {
			logger.error("Error Custom Notification 74: " + e);
		}
	}

	public void sendNotification75(long currentUserId, long siteGroupId, String remedationTitle, String urlEmail,
			long companyId) {
		logger.info("Sending Custom Notification 75");

		HashSet<User> userList = new HashSet<User>();

		try {
			Group group = GroupLocalServiceUtil.getGroup(siteGroupId);

			userList = _notifications75Utils.getRecipients(group.getClassPK(), siteGroupId, companyId);

			logger.info("users returned to send notification " + userList.size());

			_notifications75Utils.notifySubscribers(userList, null, currentUserId, siteGroupId, remedationTitle,
					urlEmail);
		} catch (PortalException e) {
			logger.error("Error Custom Notification 75: " + e);
		}
	}

	public void sendNotification76(long resourcePK) {
		logger.info("Sending Custom Notification 76 - Initial Assessment Report Published with new due date");

		HashSet<User> userList = new HashSet<User>();

		try {
			userList = _notifications76Utils.getRecipients(resourcePK);

			logger.info("users returned to send notification " + userList.size());

			_notifications76Utils.notifySubscribers(userList, null, resourcePK);
		} catch (PortalException e) {
			logger.error("Error Custom Notification 76: " + e);
		}
	}

	private Notifications04Utils _notifications04Utils;

	@Reference(unbind = "-")
	protected void setNotifications04(Notifications04Utils service) {
		_notifications04Utils = service;

	}

	private Notifications041Utils _notifications041Utils;

	@Reference(unbind = "-")
	protected void setNotifications041(Notifications041Utils service) {
		_notifications041Utils = service;
	}

	private Notifications051Utils _notifications051Utils;

	@Reference(unbind = "-")
	protected void setNotifications051(Notifications051Utils service) {
		_notifications051Utils = service;
	}

	private Notifications061Utils _notifications061Utils;

	@Reference(unbind = "-")
	protected void setNotifications061(Notifications061Utils service) {
		_notifications061Utils = service;
	}

	private Notifications07Utils _notifications07Utils;

	@Reference(unbind = "-")
	protected void setNotifications07(Notifications07Utils service) {
		_notifications07Utils = service;
	}

	private Notifications08Utils _notifications08Utils;

	@Reference(unbind = "-")
	protected void setNotifications08(Notifications08Utils service) {
		_notifications08Utils = service;
	}

	private Notifications09Utils _notifications09Utils;

	@Reference(unbind = "-")
	protected void setNotifications09(Notifications09Utils service) {
		_notifications09Utils = service;
	}

	private Notifications10Utils _notifications10Utils;

	@Reference(unbind = "-")
	protected void setNotifications10(Notifications10Utils service) {
		_notifications10Utils = service;
	}

	private Notifications11Utils _notifications11Utils;

	@Reference(unbind = "-")
	protected void setNotifications11(Notifications11Utils service) {
		_notifications11Utils = service;
	}

	private Notifications12Utils _notifications12Utils;

	@Reference(unbind = "-")
	protected void setNotifications12(Notifications12Utils service) {
		_notifications12Utils = service;
	}

	private Notifications13Utils _notifications13Utils;

	@Reference(unbind = "-")
	protected void setNotifications13(Notifications13Utils service) {
		_notifications13Utils = service;
	}

	private Notifications14Utils _notifications14Utils;

	@Reference(unbind = "-")
	protected void setNotifications14(Notifications14Utils service) {
		_notifications14Utils = service;
	}

	private Notifications15Utils _notifications15Utils;

	@Reference(unbind = "-")
	protected void setNotifications15(Notifications15Utils service) {
		_notifications15Utils = service;
	}

	private Notifications16Utils _notifications16Utils;

	@Reference(unbind = "-")
	protected void setNotifications16(Notifications16Utils service) {
		_notifications16Utils = service;
	}

	private Notifications17Utils _notifications17Utils;

	@Reference(unbind = "-")
	protected void setNotifications17(Notifications17Utils service) {
		_notifications17Utils = service;
	}

	private Notifications19Utils _notifications19Utils;

	@Reference(unbind = "-")
	protected void setNotifications19(Notifications19Utils service) {
		_notifications19Utils = service;
	}

	private Notifications20Utils _notifications20Utils;

	@Reference(unbind = "-")
	protected void setNotifications20(Notifications20Utils service) {
		_notifications20Utils = service;
	}

	private Notifications21Utils _notifications21Utils;

	@Reference(unbind = "-")
	protected void setNotifications21(Notifications21Utils service) {
		_notifications21Utils = service;
	}

	private Notifications22Utils _notifications22Utils;

	@Reference(unbind = "-")
	protected void setNotifications22(Notifications22Utils service) {
		_notifications22Utils = service;
	}

	private Notifications23Utils _notifications23Utils;

	@Reference(unbind = "-")
	protected void setNotifications23(Notifications23Utils service) {
		_notifications23Utils = service;
	}

	private Notifications24Utils _notifications24Utils;

	@Reference(unbind = "-")
	protected void setNotifications24(Notifications24Utils service) {
		_notifications24Utils = service;
	}

	private Notifications25Utils _notifications25Utils;

	@Reference(unbind = "-")
	protected void setNotifications25(Notifications25Utils service) {
		_notifications25Utils = service;
	}

	private Notifications26Utils _notifications26Utils;

	@Reference(unbind = "-")
	protected void setNotifications26(Notifications26Utils service) {
		_notifications26Utils = service;
	}

	private Notifications27Utils _notifications27Utils;

	@Reference(unbind = "-")
	protected void setNotifications27(Notifications27Utils service) {
		_notifications27Utils = service;
	}

	private Notifications28Utils _notifications28Utils;

	@Reference(unbind = "-")
	protected void setNotifications28(Notifications28Utils service) {
		_notifications28Utils = service;
	}

	private Notifications29Utils _notifications29Utils;

	@Reference(unbind = "-")
	protected void setNotifications29(Notifications29Utils service) {
		_notifications29Utils = service;
	}

	private Notifications30Utils _notifications30Utils;

	@Reference(unbind = "-")
	protected void setNotifications30(Notifications30Utils service) {
		_notifications30Utils = service;
	}

	private Notifications31Utils _notifications31Utils;

	@Reference(unbind = "-")
	protected void setNotifications31(Notifications31Utils service) {
		_notifications31Utils = service;
	}

	private Notifications32Utils _notifications32Utils;

	@Reference(unbind = "-")
	protected void setNotifications32(Notifications32Utils service) {
		_notifications32Utils = service;
	}

	private Notifications38Utils _notifications38Utils;

	@Reference(unbind = "-")
	protected void setNotifications38(Notifications38Utils service) {
		_notifications38Utils = service;
	}

	private Notifications39Utils _notifications39Utils;

	@Reference(unbind = "-")
	protected void setNotifications39(Notifications39Utils service) {
		_notifications39Utils = service;
	}

	private Notifications41Utils _notifications41Utils;

	@Reference(unbind = "-")
	protected void setNotifications(Notifications41Utils service) {
		_notifications41Utils = service;
	}

	private Notifications42Utils _notifications42Utils;

	@Reference(unbind = "-")
	protected void setNotifications42(Notifications42Utils service) {
		_notifications42Utils = service;
	}

	private Notifications43Utils _notifications43Utils;

	@Reference(unbind = "-")
	protected void setNotifications43(Notifications43Utils service) {
		_notifications43Utils = service;
	}

	private Notifications44Utils _notifications44Utils;

	@Reference(unbind = "-")
	protected void setNotifications44(Notifications44Utils service) {
		_notifications44Utils = service;
	}

	private Notifications45Utils _notifications45Utils;

	@Reference(unbind = "-")
	protected void setNotifications45(Notifications45Utils service) {
		_notifications45Utils = service;
	}

	private Notifications46Utils _notifications46Utils;

	@Reference(unbind = "-")
	protected void setNotifications46(Notifications46Utils service) {
		_notifications46Utils = service;
	}

	private Notifications47Utils _notifications47Utils;

	@Reference(unbind = "-")
	protected void setNotifications47(Notifications47Utils service) {
		_notifications47Utils = service;
	}

	private Notifications48Utils _notifications48Utils;

	@Reference(unbind = "-")
	protected void setNotifications48(Notifications48Utils service) {
		_notifications48Utils = service;
	}

	private Notifications49Utils _notifications49Utils;

	@Reference(unbind = "-")
	protected void setNotifications49(Notifications49Utils service) {
		_notifications49Utils = service;
	}

	private Notifications50Utils _notifications50Utils;

	@Reference(unbind = "-")
	protected void setNotifications50(Notifications50Utils service) {
		_notifications50Utils = service;
	}

	private Notifications51Utils _notifications51Utils;

	@Reference(unbind = "-")
	protected void setNotifications51(Notifications51Utils service) {
		_notifications51Utils = service;
	}

	private Notifications52Utils _notifications52Utils;

	@Reference(unbind = "-")
	protected void setNotifications52(Notifications52Utils service) {
		_notifications52Utils = service;
	}

	private Notifications53Utils _notifications53Utils;

	@Reference(unbind = "-")
	protected void setNotifications53(Notifications53Utils service) {
		_notifications53Utils = service;
	}

	private Notifications54Utils _notifications54Utils;

	@Reference(unbind = "-")
	protected void setNotifications54(Notifications54Utils service) {
		_notifications54Utils = service;
	}

	private Notifications55Utils _notifications55Utils;

	@Reference(unbind = "-")
	protected void setNotifications55(Notifications55Utils service) {
		_notifications55Utils = service;
	}

	private Notifications56Utils _notifications56Utils;

	@Reference(unbind = "-")
	protected void setNotifications56(Notifications56Utils service) {
		_notifications56Utils = service;
	}

	private Notifications57Utils _notifications57Utils;

	@Reference(unbind = "-")
	protected void setNotifications57(Notifications57Utils service) {
		_notifications57Utils = service;
	}

	private Notifications58Utils _notifications58Utils;

	@Reference(unbind = "-")
	protected void setNotifications58(Notifications58Utils service) {
		_notifications58Utils = service;
	}

	private Notifications59Utils _notifications59Utils;

	@Reference(unbind = "-")
	protected void setNotifications59(Notifications59Utils service) {
		_notifications59Utils = service;
	}

	private Notifications60Utils _notifications60Utils;

	@Reference(unbind = "-")
	protected void setNotifications60(Notifications60Utils service) {
		_notifications60Utils = service;
	}

	private Notifications61Utils _notifications61Utils;

	@Reference(unbind = "-")
	protected void setNotifications61(Notifications61Utils service) {
		_notifications61Utils = service;
	}

	private Notifications62Utils _notifications62Utils;

	@Reference(unbind = "-")
	protected void setNotifications62(Notifications62Utils service) {
		_notifications62Utils = service;
	}

	private Notifications63Utils _notifications63Utils;

	@Reference(unbind = "-")
	protected void setNotifications63(Notifications63Utils service) {
		_notifications63Utils = service;
	}

	private Notifications64Utils _notifications64Utils;

	@Reference(unbind = "-")
	protected void setNotifications64(Notifications64Utils service) {
		_notifications64Utils = service;
	}

	private Notifications65Utils _notifications65Utils;

	@Reference(unbind = "-")
	protected void setNotifications65(Notifications65Utils service) {
		_notifications65Utils = service;
	}

	private Notifications66Utils _notifications66Utils;

	@Reference(unbind = "-")
	protected void setNotifications66(Notifications66Utils service) {
		_notifications66Utils = service;
	}

	private Notifications67Utils _notifications67Utils;

	@Reference(unbind = "-")
	protected void setNotifications67(Notifications67Utils service) {
		_notifications67Utils = service;
	}

	private Notifications68Utils _notifications68Utils;

	@Reference(unbind = "-")
	protected void setNotifications68(Notifications68Utils service) {
		_notifications68Utils = service;
	}

	private Notifications69Utils _notifications69Utils;

	@Reference(unbind = "-")
	protected void setNotifications69(Notifications69Utils service) {
		_notifications69Utils = service;
	}

	private Notifications70Utils _notifications70Utils;

	@Reference(unbind = "-")
	protected void setNotifications70(Notifications70Utils service) {
		_notifications70Utils = service;
	}

	private Notifications71Utils _notifications71Utils;

	@Reference(unbind = "-")
	protected void setNotifications71(Notifications71Utils service) {
		_notifications71Utils = service;
	}

	private Notifications72Utils _notifications72Utils;

	@Reference(unbind = "-")
	protected void setNotifications72(Notifications72Utils service) {
		_notifications72Utils = service;
	}

	private Notifications73Utils _notifications73Utils;

	@Reference(unbind = "-")
	protected void setNotifications73(Notifications73Utils service) {
		_notifications73Utils = service;
	}

	private Notifications74Utils _notifications74Utils;

	@Reference(unbind = "-")
	protected void setNotifications74(Notifications74Utils service) {
		_notifications74Utils = service;
	}

	private Notifications75Utils _notifications75Utils;

	@Reference(unbind = "-")
	protected void setNotifications75(Notifications74Utils service) {
		_notifications74Utils = service;
	}

	private Notifications76Utils _notifications76Utils;

	@Reference(unbind = "-")
	protected void setNotifications76(Notifications76Utils service) {
		_notifications76Utils = service;
	}

}