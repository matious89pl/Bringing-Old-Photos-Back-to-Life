package com.everis.rec.listeners.journal.article;

import com.everis.cproposal.model.recFormArticle;
import com.everis.cproposal.service.recFormArticleLocalServiceUtil;
import com.everis.messages.service.builder.service.MessagesLocalServiceUtil;
import com.everis.rec.ddl.journal.article.service.DDL_JournalArticleLocalServiceUtil;
import com.everis.rec.listeners.constants.RecListenersPortletKeys;
import com.everis.rec.service.activity.logs.model.RecLog;
import com.everis.rec.service.activity.logs.service.RecLogLocalServiceUtil;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.dynamic.data.lists.service.DDLRecordSetLocalServiceUtil;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowTaskManagerUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.kernel.xml.XPath;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.service.KaleoTaskInstanceTokenLocalServiceUtil;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ChangeProposalUtil {

	private final Log logger = LogFactoryUtil.getLog(ChangeProposalUtil.class);

	private final static String CP_MILESTONE = "CP-MILESTONE";
	private final static String CP_INITIAL_COMMENTS = "CP-INITIAL-COMMENTS";
	private final static String CP_PRELIMINARY_COMMENTS = "CP-PRELIMINARY-COMMENTS";
	private final static String CP_FINAL_COMMENTS = "CP-FINAL-COMMENTS";

	public void checkUpdatesAndApplyLogic(JournalArticle model) {
		Double version = 1.0;
		try {
			if (model.getVersion() != version) {
				logger.debug(model.getVersion() + " - " + version);

				if (emailFieldHasBeenUpdated(model)) {

					String email = getNodeText(model, RecListenersPortletKeys.PROPOSER_EMAIL_FIELDNAME);
					logger.debug("email " + email);

					registerActivityLog(model, "email updated", "Email changed to " + email);

					if (isWithdrawOption(model)) {
						logger.debug("onAfterUpdate email field value is empty");

					} else if (isAdoptOption(model)) {
						adoptFunction(model);

					}
				}

				if (initialAssessmentReportCommentsDeadlineHasBeenUpdated(model)
						&& isInitialAssessmentReportUploaded(model)) {
					logger.info(
							"CommentsDeadlineHasBeenUpdated + there is a Initial AssessmentReport Uplodaded Listener - Sending Notification by Segments");

					String commentsDeadline = getNodeText(model,
							RecListenersPortletKeys.IAR_COMMENTS_DEADLINE_FIELDNAME);
					MessagesLocalServiceUtil.sendNotification16(model.getResourcePrimKey());
					logger.info(
							"CommentsDeadlineHasBeenUpdated + there is a Initial AssessmentReport Uplodaded Listener - Sending Notification by Segments");

					registerActivityLog(model, "Comments for Initial Assessment Report updated",
							"Comments deadline changed to " + commentsDeadline);

				} else if (initialAssessmentReportCommentsDeadlineHasBeenUpdated(model)) {

					logger.debug("CommentsDeadlineHasBeenUpdated - Sending Notification by Segments");

					MessagesLocalServiceUtil.sendNotification76(model.getResourcePrimKey());

					logger.debug("Notification76 sended OK");

				}

				if (preliminaryAssessmentReportCommentsDeadlineHasBeenUpdated(model)
						&& isPreliminaryChangeReportUploaded(model)) {
					logger.debug(
							"CommentsDeadlineHasBeenUpdated + there is a PreliminaryChangeReport Uplodaded - Sending Notification by Segments");

					String commentsDeadline = getNodeText(model,
							RecListenersPortletKeys.PCR_COMMENTS_DEADLINE_FIELDNAME);
					MessagesLocalServiceUtil.sendNotification07(model.getResourcePrimKey());

					registerActivityLog(model, "Comments preliminaryCP updated",
							"Comments deadline changed to " + commentsDeadline);

				} else if (preliminaryAssessmentReportCommentsDeadlineHasBeenUpdated(model)) {

					logger.debug("CommentsDeadlineHasBeenUpdated - Sending Notification by Segments");

					MessagesLocalServiceUtil.sendNotification44(model.getResourcePrimKey());

					logger.debug("Notification44 sended OK");

				}

				if (finalChangeReportCommentsDeadlineHasBeenUpdated(model) && isFinalChangeReportUploaded(model)) {
					logger.debug(
							"CommentsDeadlineHasBeenUpdated + there is a Final Change Report Uplodaded - Sending Notification by Segments");

					String commentsDeadline = getNodeText(model,
							RecListenersPortletKeys.FCR_COMMENTS_DEADLINE_FIELDNAME);
					MessagesLocalServiceUtil.sendNotification12(model.getResourcePrimKey());
					logger.info(
							"CommentsDeadlineHasBeenUpdated + there is a Final Change Assessment Uplodaded - Sending Notification by Segments");

					registerActivityLog(model, "Comments finalCP updated",
							"Comments deadline changed to " + commentsDeadline);

				} else if (finalChangeReportCommentsDeadlineHasBeenUpdated(model)) {
					logger.debug("CommentsDeadlineHasBeenUpdated - Sending Notification by Segments");

					MessagesLocalServiceUtil.sendNotification45(model.getResourcePrimKey());

					logger.debug("Notification45 sended OK");
				}

				if (statusHasBeenUpdated(model)) {
					// adding new status value on cproposal_recformarticle table
					long formId = recFormArticleLocalServiceUtil.getFormIdByArticleId(model.getResourcePrimKey());
					recFormArticle recFormArticle = recFormArticleLocalServiceUtil.getrecFormArticle(formId);
					String status = getNodeText(model, RecListenersPortletKeys.CHANGE_PROPOSAL_STATUS);
					logger.debug("status " + status);
					recFormArticle.setArticleStatus(status);
					recFormArticleLocalServiceUtil.updaterecFormArticle(recFormArticle);

					registerActivityLog(model, "Status updated", "Status changed to " + status);
				}
				if (releaseScheduleHasBeenUpdated(model)) {
					// adding new schedule value on cproposal_recformarticle table
					long formId = recFormArticleLocalServiceUtil.getFormIdByArticleId(model.getResourcePrimKey());
					recFormArticle recFormArticle = recFormArticleLocalServiceUtil.getrecFormArticle(formId);
					String releaseSchedule = getNodeText(model,
							RecListenersPortletKeys.CHANGE_PROPOSAL_RELEASE_SCHEDULE);
					logger.debug("releaseSchedule " + releaseSchedule);
					recFormArticle.setArticleReleaseSchedule(releaseSchedule);
					recFormArticleLocalServiceUtil.updaterecFormArticle(recFormArticle);

					registerActivityLog(model, "Release Schedule updated",
							"Release Schedule changed to " + releaseSchedule);
				}
				if (changeCategoryHasBeenUpdated(model)) {
					// adding change Category value on cproposal_recformarticle table
					long formId = recFormArticleLocalServiceUtil.getFormIdByArticleId(model.getResourcePrimKey());
					recFormArticle recFormArticle = recFormArticleLocalServiceUtil.getrecFormArticle(formId);
					String changeCategory = getNodeText(model, RecListenersPortletKeys.CHANGE_CATEGORY);
					logger.debug("changeCategory " + changeCategory);
					recFormArticle.setArticleChangeCategory(changeCategory);
					recFormArticleLocalServiceUtil.updaterecFormArticle(recFormArticle);

					registerActivityLog(model, "Change Category updated",
							"Change Category changed to " + changeCategory);
				}
				if (leadAnaylystHasBeenUpdated(model)) {

					String leadChangeAnaylyst = getNodeText(model, RecListenersPortletKeys.LEAD_CHANGE_ANALYST);

					registerActivityLog(model, "Lead Analyst updated",
							leadChangeAnaylyst + " has been allocated as Lead Analyst");
				}
				if (leadTechnicalHasBeenUpdated(model)) {

					String leadTechnicalAnaylyst = getNodeText(model, RecListenersPortletKeys.LEAD_TECHNICAL_ANAYLYST);

					registerActivityLog(model, "Lead Technical updated",
							leadTechnicalAnaylyst + " has been allocated as Lead Technical ");
				}
				if (leadAssuranceAnalystHasBeenUpdated(model)) {

					String leadAssuranceAnaylyst = getNodeText(model, RecListenersPortletKeys.LEAD_ASSURANCE_ANALYST);

					registerActivityLog(model, "Lead Assurance Analyst updated",
							leadAssuranceAnaylyst + " has been allocated as Lead Assurance");

				}
				if (changePathHasBeenUpdated(model)) {

					long formId = recFormArticleLocalServiceUtil.getFormIdByArticleId(model.getResourcePrimKey());
					recFormArticle recFormArticle = recFormArticleLocalServiceUtil.getrecFormArticle(formId);
					String changePath = getNodeText(model, RecListenersPortletKeys.CHANGE_PATH);
					logger.debug("changePath " + changePath);
					recFormArticle.setArticleChangePath(changePath);
					recFormArticleLocalServiceUtil.updaterecFormArticle(recFormArticle);

					registerActivityLog(model, "Change Path updated", "Change Path changed to " + changePath);
				}
				if (problemStatementHasBeenUpdated(model)) {
					String problemStatement = getNodeText(model, RecListenersPortletKeys.PROBLEM_STATEMENT);

					registerActivityLog(model, "Problem Statement updated",
							"Problem Statement changed to " + problemStatement);

				}
				if (solutionHasBeenUpdated(model)) {

					String solution = getNodeText(model, RecListenersPortletKeys.SOLUTION_REQUERIMENTS);

					registerActivityLog(model, "Solution updated", "Solution changed to " + solution);

				}

				if (solutionHasBeenUpdated2(model)) {

					String solution = getNodeText(model, RecListenersPortletKeys.SOLUTION_REQUERIMENTS2);

					registerActivityLog(model, "Solution2 updated", "Solution2 changed to " + solution);

				}

				if (solutionHasBeenUpdated3(model)) {

					String solution = getNodeText(model, RecListenersPortletKeys.SOLUTION_REQUERIMENTS3);

					registerActivityLog(model, "Solution3 updated", "Solution3 changed to " + solution);

				}

				if (solutionHasBeenUpdated4(model)) {

					String solution = getNodeText(model, RecListenersPortletKeys.SOLUTION_REQUERIMENTS4);

					registerActivityLog(model, "Solution4 updated", "Solution4 changed to " + solution);

				}

				if (solutionHasBeenUpdated5(model)) {

					String solution = getNodeText(model, RecListenersPortletKeys.SOLUTION_REQUERIMENTS5);

					registerActivityLog(model, "Solution5 updated", "Solution5 changed to " + solution);

				}

			} else {
				logger.debug("Creating related CP DDL...");

				if (DDL_JournalArticleLocalServiceUtil.getDDL_JournalArticleByJournalPK(model.getResourcePrimKey())
						.size() == 0) {
					createRelatedDDL(model);
					logger.debug("Related CP DDL created correctly");
				} else {
					logger.info("DDLs already created for this CP: " + model.getResourcePrimKey());
				}

			}
		} catch (

		DocumentException e) {
			logger.error("Error getting reading Journal Article Content... " + e);
		} catch (PortalException e) {
			logger.error("Error Portal Exception... " + e);
		} catch (ParseException e) {
			logger.error("Error parsing currentCommentsDateline to Date on commentsDeadlineHasBeenUpdated method " + e);
		}
	}

	private void createRelatedDDL(JournalArticle article) throws DocumentException {
		logger.debug("createRelatedDDL... ");
		createDDLStructureByName(article, CP_MILESTONE);
		createDDLStructureByName(article, CP_INITIAL_COMMENTS);
		createDDLStructureByName(article, CP_PRELIMINARY_COMMENTS);
		createDDLStructureByName(article, CP_FINAL_COMMENTS);
	}

	private void createDDLStructureByName(JournalArticle model, String structureName) throws DocumentException {
		logger.info("Creating DDL " + structureName + " for ChangeProposal(CP): " + model.getResourcePrimKey());
		long structureId = getStructureIdByName(model, structureName);
		if (Validator.isNotNull(structureId)) {
			String prefix = StringPool.BLANK, key = StringPool.BLANK;
			switch (structureName) {
			case CP_MILESTONE:
				prefix = "CP-Milestone-";
				key = "CPM";
				break;
			case CP_INITIAL_COMMENTS:
				prefix = "CP-Initial-Comments-";
				key = "CPIC";
				break;
			case CP_PRELIMINARY_COMMENTS:
				prefix = "CP-Preliminary-Comments-";
				key = "CPPC";
				break;
			case CP_FINAL_COMMENTS:
				prefix = "CP-Final-Comments-";
				key = "CPFC";
				break;
			}

			Map<Locale, String> titleMap = new HashMap<>();
			String resourcePrimKey = String.valueOf(model.getResourcePrimKey());
			String title = prefix.concat(resourcePrimKey);
			titleMap.put(Locale.ENGLISH, title);
			logger.debug("title: " + title);
			Map<Locale, String> descriptionMap = new HashMap<>();
			titleMap.put(Locale.ENGLISH, title);
			String recordSetKey = key.concat(String.valueOf(model.getResourcePrimKey()));

			DDLRecordSet ddlRecordSet = null;

			try {
				ddlRecordSet = DDLRecordSetLocalServiceUtil.addRecordSet(model.getUserId(), model.getGroupId(),
						structureId, recordSetKey, titleMap, descriptionMap, 3, 0, new ServiceContext());
				String defaultName = ddlRecordSet.getName();
				String newName = defaultName.replaceAll("Untitled Dynamic Data List", title);
				ddlRecordSet.setName(newName);
				logger.debug("newName: " + newName);
				ddlRecordSet = DDLRecordSetLocalServiceUtil.updateDDLRecordSet(ddlRecordSet);
			} catch (PortalException e) {
				logger.error("Error adding new DDL Record for Milestone: " + model.getResourcePrimKey());
			}
			logger.info("ddlRecordSet: " + ddlRecordSet);
			if (JournalArticleUtil.addDDLJournalRelation(ddlRecordSet, model))
				logger.info("DDL_Jounal added!");
		}
	}

	private long getStructureIdByName(JournalArticle model, String structureName) throws DocumentException {
		long structureId = 0;
		DynamicQuery dynamicQuery = DDMStructureLocalServiceUtil.dynamicQuery();
		dynamicQuery.add(PropertyFactoryUtil.forName("name").like("%" + structureName + "%"));
		List<DDMStructure> ddmStructureList = DDMStructureLocalServiceUtil.dynamicQuery(dynamicQuery);
		if (ddmStructureList.size() > 0) {
			structureId = ddmStructureList.get(0).getStructureId();
		}
		logger.debug("structureId: " + structureId);
		return structureId;
	}

	private void adoptFunction(JournalArticle model) throws DocumentException, PortalException {

		logger.debug("onAfterUpdate - AdoptFunction - Adding adopter user on EmailFieldValue... ");

		long articleId = model.getResourcePrimKey();
		logger.debug("articleId... " + articleId);

		try {
			long formId = recFormArticleLocalServiceUtil.getFormIdByArticleId(articleId);
			if (formId == 0) {
				logger.error("There is not any form related to this articleId: " + articleId);
			}
			logger.debug("formId... " + formId);
			// getting the new user email from the journal article field ChangeProposerEmail
			String adopterEmail = getNodeText(model, RecListenersPortletKeys.PROPOSER_EMAIL_FIELDNAME);

			// getting the user by its email
			User userAdopter = UserLocalServiceUtil.getUserByEmailAddress(model.getCompanyId(), adopterEmail);
			logger.debug("userAdopter: " + userAdopter.getUserId() + " - " + userAdopter.getScreenName());

			DynamicQuery dynamicQuery = KaleoTaskInstanceTokenLocalServiceUtil.dynamicQuery();
			dynamicQuery.add(PropertyFactoryUtil.forName("classPK").eq(formId));
			dynamicQuery.add(PropertyFactoryUtil.forName("completed").eq(false));
			dynamicQuery.add(PropertyFactoryUtil.forName("kaleoTaskName").eq("Withdraw"));

			List<KaleoTaskInstanceToken> kaleoTaskInstanceTokens = new ArrayList<KaleoTaskInstanceToken>();
			try {
				kaleoTaskInstanceTokens = KaleoTaskInstanceTokenLocalServiceUtil.dynamicQuery(dynamicQuery);
			} catch (Exception e) {
				logger.error("Error executing dynamic query on KaleoTaskInstanceToken" + e);
			}
			logger.debug("kaleoTaskInstanceTokens... " + kaleoTaskInstanceTokens);
			if (kaleoTaskInstanceTokens.size() == 1) {
				logger.debug("getKaleoTaskInstanceTokenId... "
						+ kaleoTaskInstanceTokens.get(0).getKaleoTaskInstanceTokenId());

				logger.debug("Before update user " + kaleoTaskInstanceTokens.get(0).getUserId() + " - "
						+ kaleoTaskInstanceTokens.get(0).getUserName());

				// asign task to new adopter user
				WorkflowTaskManagerUtil.assignWorkflowTaskToUser(model.getCompanyId(), model.getUserId(),
						kaleoTaskInstanceTokens.get(0).getKaleoTaskInstanceTokenId(), userAdopter.getUserId(),
						"New adopter", null, null);
				logger.debug("After update user " + kaleoTaskInstanceTokens.get(0).getUserId() + " - "
						+ kaleoTaskInstanceTokens.get(0).getUserName());
			}
		} catch (Exception e) {
			logger.error("Does not exist this articleId: " + articleId + " - " + e);
		}

	}

	private boolean isWithdrawOption(JournalArticle model) throws DocumentException {
		String fieldValue = getNodeText(model, RecListenersPortletKeys.PROPOSER_EMAIL_FIELDNAME);
		return fieldValue.isEmpty() ? true : false;
	}

	private boolean isAdoptOption(JournalArticle model) throws DocumentException {
		return !getNodeText(model, RecListenersPortletKeys.PROPOSER_EMAIL_FIELDNAME).isEmpty() ? true : false;
	}

	private String getNodeText(JournalArticle model, String fieldName) throws DocumentException {
		logger.trace("model: " + model.getArticleId());
		logger.trace("getContent: " + model.getContent());
		Document document = SAXReaderUtil.read(model.getContent());
		logger.trace("document: " + document.getText());
		Node node = document.selectSingleNode("//root//dynamic-element[@name='" + fieldName + "']//dynamic-content");
		if (node == null) {
			logger.debug("There is no field on the structure for the following fieldName");
			return "";
		} else {
			logger.debug("node.getText(): " + node.getText());
			return node.getText();
		}
	}

	private String getNodeDocument(JournalArticle model, String fieldName) throws DocumentException {
		logger.trace("model: " + model.getArticleId());
		logger.trace("getContent: " + model.getContent());
		Document document = SAXReaderUtil.read(model.getContent());
		logger.trace("document: " + document.getText());

		XPath xPathSelector = SAXReaderUtil
				.createXPath("//root//dynamic-element[@name='" + fieldName + "']//dynamic-content");
		List<Node> ImpactNodes = xPathSelector.selectNodes(document);

		return ImpactNodes.get(ImpactNodes.size()-1).getText();
	}

	private void registerActivityLog(JournalArticle model, String type, String textMessage) {

		RecLog recActivityLogs;
		long activityLogId = CounterLocalServiceUtil.increment(RecLog.class.getName());

		recActivityLogs = RecLogLocalServiceUtil.createRecLog(activityLogId);
		recActivityLogs.setGroupId(model.getGroupId());
		recActivityLogs.setCreateDate(new Date());
		recActivityLogs.setClassName("com.liferay.journal");
		recActivityLogs.setClassPK(model.getResourcePrimKey());
		recActivityLogs.setType(type);
		recActivityLogs.setLogMessage(textMessage);

		RecLogLocalServiceUtil.addRecLog(recActivityLogs);

	}

	private boolean isCustomUpdate(JournalArticle previousModel, JournalArticle newModel) {
		Double oldVersion = previousModel.getVersion();
		Double newVersion = newModel.getVersion();
		logger.debug("oldVersion: " + oldVersion + " , newVersion: " + newVersion);
		return oldVersion.equals(newVersion) ? true : false;
	}

	private boolean emailFieldHasBeenUpdated(JournalArticle model) throws DocumentException {
		logger.debug("EmailFieldHasBeenUpdated... ");
		boolean hasBeenUpdated = false;
		JournalArticle previousModel = JournalArticleLocalServiceUtil.getPreviousApprovedArticle(model);
		if (!isCustomUpdate(previousModel, model)) {
			String previousEmailValue = getNodeText(previousModel, RecListenersPortletKeys.PROPOSER_EMAIL_FIELDNAME);
			String currentEmailValue = getNodeText(model, RecListenersPortletKeys.PROPOSER_EMAIL_FIELDNAME);
			logger.debug("previousEmailValue: " + previousEmailValue + " -- currentEmailValue: " + currentEmailValue);
			hasBeenUpdated = previousEmailValue.equalsIgnoreCase(currentEmailValue) ? false : true;
		}
		logger.debug("emailFieldHasBeenUpdated: " + hasBeenUpdated);
		return hasBeenUpdated;
	}

	private boolean initialAssessmentReportCommentsDeadlineHasBeenUpdated(JournalArticle model)
			throws DocumentException, ParseException {
		logger.info("commentsDeadlineHasBeenUpdated...");
		boolean hasBeenUpdated = false;
		JournalArticle previousModel = JournalArticleLocalServiceUtil.getPreviousApprovedArticle(model);
		if (!isCustomUpdate(previousModel, model)) {
			logger.info("commentsDeadlineHasBeenUpdated... in isCustomUpdate");
			String previousCommentsDeadline = getNodeText(previousModel,
					RecListenersPortletKeys.IAR_COMMENTS_DEADLINE_FIELDNAME);
			String currentCommentsDeadline = getNodeText(model,
					RecListenersPortletKeys.IAR_COMMENTS_DEADLINE_FIELDNAME);
			logger.info("previousCommentsDeadline: " + previousCommentsDeadline + " -- currentCommentsDeadline: "
					+ currentCommentsDeadline);
			Date nowDate = new Date();
			Date currentCommentsDeadlineDate = new Date();
			if (!currentCommentsDeadline.isEmpty()) {
				currentCommentsDeadlineDate = DateUtil.parseDate("yyyy-MM-dd", currentCommentsDeadline,
						Locale.getDefault());
			}
			hasBeenUpdated = (!previousCommentsDeadline.equalsIgnoreCase(currentCommentsDeadline)
					&& currentCommentsDeadlineDate.after(nowDate)) ? true : false;
		}
		logger.info("commentsDeadlineHasBeenUpdated: " + hasBeenUpdated);
		return hasBeenUpdated;
	}

	private boolean isInitialAssessmentReportUploaded(JournalArticle model) throws DocumentException {
		logger.debug("isInitialAssessmentReportUploaded...");
		boolean hasBeenUpdated = false;
		JournalArticle previousModel = JournalArticleLocalServiceUtil.getPreviousApprovedArticle(model);
		if (!isCustomUpdate(previousModel, model)) {

			logger.debug("documentBeenUpdated... in isCustomUpdate");
			String previousDocumentUpload = getNodeDocument(previousModel,
					RecListenersPortletKeys.INITIAL_ASSESSMENT_REPORT_FIELDNAME);
			String currentDocumentUpload = getNodeDocument(model,
					RecListenersPortletKeys.INITIAL_ASSESSMENT_REPORT_FIELDNAME);
			logger.debug("previousDocumentUpload: " + previousDocumentUpload + " -- currentDocumentUpload: "
					+ currentDocumentUpload);

			hasBeenUpdated = !previousDocumentUpload.equals(currentDocumentUpload) ? true : false;

		}

		logger.debug("isInitialAssessmentReportUploaded...");

		return hasBeenUpdated;

	}

	private boolean preliminaryAssessmentReportCommentsDeadlineHasBeenUpdated(JournalArticle model)
			throws DocumentException, ParseException {
		logger.debug("commentsDeadlineHasBeenUpdated...");
		boolean hasBeenUpdated = false;
		JournalArticle previousModel = JournalArticleLocalServiceUtil.getPreviousApprovedArticle(model);
		if (!isCustomUpdate(previousModel, model)) {
			logger.debug("commentsDeadlineHasBeenUpdated... in isCustomUpdate");
			String previousCommentsDeadline = getNodeText(previousModel,
					RecListenersPortletKeys.PCR_COMMENTS_DEADLINE_FIELDNAME);
			String currentCommentsDeadline = getNodeText(model,
					RecListenersPortletKeys.PCR_COMMENTS_DEADLINE_FIELDNAME);
			logger.debug("previousCommentsDeadline: " + previousCommentsDeadline + " -- currentCommentsDeadline: "
					+ currentCommentsDeadline);
			Date nowDate = new Date();
			Date currentCommentsDeadlineDate = new Date();
			if (!currentCommentsDeadline.isEmpty()) {
				currentCommentsDeadlineDate = DateUtil.parseDate("yyyy-MM-dd", currentCommentsDeadline,
						Locale.getDefault());
			}
			hasBeenUpdated = (!previousCommentsDeadline.equalsIgnoreCase(currentCommentsDeadline)
					&& currentCommentsDeadlineDate.after(nowDate)) ? true : false;
		}
		logger.debug("commentsDeadlineHasBeenUpdated: " + hasBeenUpdated);
		return hasBeenUpdated;
	}

	private boolean isPreliminaryChangeReportUploaded(JournalArticle model) throws DocumentException {

		logger.debug("isPreliminaryChangeReportUploaded...");
		boolean hasBeenUpdated = false;
		JournalArticle previousModel = JournalArticleLocalServiceUtil.getPreviousApprovedArticle(model);
		if (!isCustomUpdate(previousModel, model)) {

			logger.debug("documentBeenUpdated... in isCustomUpdate");
			String previousDocumentUpload = getNodeDocument(previousModel,
					RecListenersPortletKeys.PRELIMINARY_CHANGE_REPORT_FIELDNAME);
			String currentDocumentUpload = getNodeDocument(model,
					RecListenersPortletKeys.PRELIMINARY_CHANGE_REPORT_FIELDNAME);
			logger.debug("previousDocumentUpload: " + previousDocumentUpload + " -- currentDocumentUpload: "
					+ currentDocumentUpload);

			hasBeenUpdated = !previousDocumentUpload.equals(currentDocumentUpload) ? true : false;

		}

		logger.debug("isPreliminaryChangeReportUploaded...");

		return hasBeenUpdated;

	}

	private boolean finalChangeReportCommentsDeadlineHasBeenUpdated(JournalArticle model)
			throws DocumentException, ParseException {
		logger.debug("finalChangeReportCommentsDeadlineHasBeenUpdated...");
		boolean hasBeenUpdated = false;
		JournalArticle previousModel = JournalArticleLocalServiceUtil.getPreviousApprovedArticle(model);
		if (!isCustomUpdate(previousModel, model)) {
			logger.debug("fcrCommentsDeadlineHasBeenUpdated... in isCustomUpdate");
			String previousCommentsDeadline = getNodeText(previousModel,
					RecListenersPortletKeys.FCR_COMMENTS_DEADLINE_FIELDNAME);
			String currentCommentsDeadline = getNodeText(model,
					RecListenersPortletKeys.FCR_COMMENTS_DEADLINE_FIELDNAME);
			logger.debug("previousfcrCommentsDeadline: " + previousCommentsDeadline + " -- currentfcrCommentsDeadline: "
					+ currentCommentsDeadline);
			Date nowDate = new Date();
			Date currentCommentsDeadlineDate = new Date();
			if (!currentCommentsDeadline.isEmpty()) {
				currentCommentsDeadlineDate = DateUtil.parseDate("yyyy-MM-dd", currentCommentsDeadline,
						Locale.getDefault());
			}
			hasBeenUpdated = (!previousCommentsDeadline.equalsIgnoreCase(currentCommentsDeadline)
					&& currentCommentsDeadlineDate.after(nowDate)) ? true : false;
		}
		logger.debug("fcrCommentsDeadlineHasBeenUpdated: " + hasBeenUpdated);
		return hasBeenUpdated;
	}

	private boolean isFinalChangeReportUploaded(JournalArticle model) throws DocumentException {
		logger.debug("isFinalChangeReportUploaded...");
		boolean hasBeenUpdated = false;
		JournalArticle previousModel = JournalArticleLocalServiceUtil.getPreviousApprovedArticle(model);
		if (!isCustomUpdate(previousModel, model)) {

			logger.debug("documentBeenUpdated... in isCustomUpdate");
			String previousDocumentUpload = getNodeDocument(previousModel,
					RecListenersPortletKeys.FINAL_CHANGE_REPORT_FIELDNAME);
			String currentDocumentUpload = getNodeDocument(model,
					RecListenersPortletKeys.FINAL_CHANGE_REPORT_FIELDNAME);
			logger.debug("previousDocumentUpload: " + previousDocumentUpload + " -- currentDocumentUpload: "
					+ currentDocumentUpload);

			hasBeenUpdated = !previousDocumentUpload.equals(currentDocumentUpload) ? true : false;

		}

		logger.debug("isFinalChangeReportUploaded...");

		return hasBeenUpdated;
	}

	private boolean statusHasBeenUpdated(JournalArticle model) throws DocumentException {
		logger.debug("statusHasBeenUpdated...");
		boolean hasBeenUpdated = false;
		JournalArticle previousModel = JournalArticleLocalServiceUtil.getPreviousApprovedArticle(model);
		String previousStatus = getNodeText(previousModel, RecListenersPortletKeys.CHANGE_PROPOSAL_STATUS);
		String currentStatus = getNodeText(model, RecListenersPortletKeys.CHANGE_PROPOSAL_STATUS);
		logger.debug("previousStatus: " + previousStatus + " -- currentStatus: " + currentStatus);
		hasBeenUpdated = previousStatus.equalsIgnoreCase(currentStatus) ? false : true;
		return hasBeenUpdated;
	}

	private boolean releaseScheduleHasBeenUpdated(JournalArticle model) throws DocumentException {
		logger.debug("releaseScheduleHasBeenUpdated...");
		boolean hasBeenUpdated = false;
		JournalArticle previousModel = JournalArticleLocalServiceUtil.getPreviousApprovedArticle(model);
		String previousReleaseSchedule = getNodeText(previousModel,
				RecListenersPortletKeys.CHANGE_PROPOSAL_RELEASE_SCHEDULE);
		String currentReleaseSchedule = getNodeText(model, RecListenersPortletKeys.CHANGE_PROPOSAL_RELEASE_SCHEDULE);
		logger.debug("previousReleaseSchedule: " + previousReleaseSchedule + " -- currentReleaseSchedule: "
				+ currentReleaseSchedule);
		hasBeenUpdated = previousReleaseSchedule.equalsIgnoreCase(currentReleaseSchedule) ? false : true;
		return hasBeenUpdated;
	}

	private boolean leadAnaylystHasBeenUpdated(JournalArticle model) throws DocumentException {
		logger.debug("leadAnaylystHasBeenUpdated...");
		boolean hasBeenUpdated = false;
		JournalArticle previousModel = JournalArticleLocalServiceUtil.getPreviousApprovedArticle(model);
		String previousLeadAnaylys = getNodeText(previousModel, RecListenersPortletKeys.LEAD_CHANGE_ANALYST);
		String currentLeadAnaylys = getNodeText(model, RecListenersPortletKeys.LEAD_CHANGE_ANALYST);
		logger.debug("previousLeadAnaylys: " + previousLeadAnaylys + " -- currentLeadAnaylys: " + currentLeadAnaylys);
		hasBeenUpdated = previousLeadAnaylys.equalsIgnoreCase(currentLeadAnaylys) ? false : true;
		return hasBeenUpdated;
	}

	private boolean leadTechnicalHasBeenUpdated(JournalArticle model) throws DocumentException {
		logger.debug("leadTechnicalHasBeenUpdated...");
		boolean hasBeenUpdated = false;
		JournalArticle previousModel = JournalArticleLocalServiceUtil.getPreviousApprovedArticle(model);
		String previousLeadTechnical = getNodeText(previousModel, RecListenersPortletKeys.LEAD_TECHNICAL_ANAYLYST);
		String currentLeadTechnical = getNodeText(model, RecListenersPortletKeys.LEAD_TECHNICAL_ANAYLYST);
		logger.debug("previousLeadTechnical: " + previousLeadTechnical + " -- currentLeadTechnical: "
				+ currentLeadTechnical);
		hasBeenUpdated = previousLeadTechnical.equalsIgnoreCase(currentLeadTechnical) ? false : true;
		return hasBeenUpdated;
	}

	private boolean leadAssuranceAnalystHasBeenUpdated(JournalArticle model) throws DocumentException {
		logger.debug("leadAssuranceAnalystHasBeenUpdated...");
		boolean hasBeenUpdated = false;
		JournalArticle previousModel = JournalArticleLocalServiceUtil.getPreviousApprovedArticle(model);
		String previousLeadAssuranceAnalyst = getNodeText(previousModel,
				RecListenersPortletKeys.LEAD_ASSURANCE_ANALYST);
		String currentLeadAssuranceAnalyst = getNodeText(model, RecListenersPortletKeys.LEAD_ASSURANCE_ANALYST);
		logger.debug("previousLeadAssuranceAnalyst: " + previousLeadAssuranceAnalyst
				+ " -- currentLeadAssuranceAnalyst: " + currentLeadAssuranceAnalyst);
		hasBeenUpdated = previousLeadAssuranceAnalyst.equalsIgnoreCase(currentLeadAssuranceAnalyst) ? false : true;
		return hasBeenUpdated;
	}

	private boolean changeCategoryHasBeenUpdated(JournalArticle model) throws DocumentException {
		logger.debug("changeCategoryHasBeenUpdated...");
		boolean hasBeenUpdated = false;
		JournalArticle previousModel = JournalArticleLocalServiceUtil.getPreviousApprovedArticle(model);
		String previousChageCategory = getNodeText(previousModel, RecListenersPortletKeys.CHANGE_CATEGORY);
		String currentChangeCategory = getNodeText(model, RecListenersPortletKeys.CHANGE_CATEGORY);
		logger.debug("previousChageCategory: " + previousChageCategory + " -- currentChangeCategory: "
				+ currentChangeCategory);
		hasBeenUpdated = previousChageCategory.equalsIgnoreCase(currentChangeCategory) ? false : true;
		return hasBeenUpdated;
	}

	private boolean changePathHasBeenUpdated(JournalArticle model) throws DocumentException {
		logger.debug("changePathHasBeenUpdated...");
		boolean hasBeenUpdated = false;
		JournalArticle previousModel = JournalArticleLocalServiceUtil.getPreviousApprovedArticle(model);
		String previousChagePath = getNodeText(previousModel, RecListenersPortletKeys.CHANGE_PATH);
		String currentChangePath = getNodeText(model, RecListenersPortletKeys.CHANGE_PATH);
		logger.debug("previousChagePath: " + previousChagePath + " -- currentChangePath: " + currentChangePath);
		hasBeenUpdated = previousChagePath.equalsIgnoreCase(currentChangePath) ? false : true;
		return hasBeenUpdated;
	}

	private boolean problemStatementHasBeenUpdated(JournalArticle model) throws DocumentException {
		logger.debug("problemStatementHasBeenUpdated...");
		boolean hasBeenUpdated = false;
		JournalArticle previousModel = JournalArticleLocalServiceUtil.getPreviousApprovedArticle(model);
		String previousProblemStatement = getNodeText(previousModel, RecListenersPortletKeys.PROBLEM_STATEMENT);
		String currentProblemStatement = getNodeText(model, RecListenersPortletKeys.PROBLEM_STATEMENT);
		logger.debug("previousProblemStatement: " + previousProblemStatement
				+ " -- currentProblemStatementHasBeenUpdated: " + currentProblemStatement);
		hasBeenUpdated = previousProblemStatement.equalsIgnoreCase(currentProblemStatement) ? false : true;
		return hasBeenUpdated;
	}

	private boolean solutionHasBeenUpdated(JournalArticle model) throws DocumentException {
		logger.debug("solutionHasBeenUpdated...");
		boolean hasBeenUpdated = false;
		JournalArticle previousModel = JournalArticleLocalServiceUtil.getPreviousApprovedArticle(model);
		String previousSolution = getNodeText(previousModel, RecListenersPortletKeys.SOLUTION_REQUERIMENTS);
		String currentSolution = getNodeText(model, RecListenersPortletKeys.SOLUTION_REQUERIMENTS);
		logger.debug("previousSolution: " + previousSolution + " -- currentProblemStatement: " + currentSolution);
		hasBeenUpdated = previousSolution.equalsIgnoreCase(currentSolution) ? false : true;
		return hasBeenUpdated;
	}

	private boolean solutionHasBeenUpdated2(JournalArticle model) throws DocumentException {
		logger.debug("solutionHasBeenUpdated2...");
		boolean hasBeenUpdated = false;
		JournalArticle previousModel = JournalArticleLocalServiceUtil.getPreviousApprovedArticle(model);
		String previousSolution = getNodeText(previousModel, RecListenersPortletKeys.SOLUTION_REQUERIMENTS2);
		String currentSolution = getNodeText(model, RecListenersPortletKeys.SOLUTION_REQUERIMENTS2);
		logger.debug("previousSolution: " + previousSolution + " -- currentProblemStatement: " + currentSolution);
		hasBeenUpdated = previousSolution.equalsIgnoreCase(currentSolution) ? false : true;
		return hasBeenUpdated;
	}

	private boolean solutionHasBeenUpdated3(JournalArticle model) throws DocumentException {
		logger.debug("solutionHasBeenUpdated3...");
		boolean hasBeenUpdated = false;
		JournalArticle previousModel = JournalArticleLocalServiceUtil.getPreviousApprovedArticle(model);
		String previousSolution = getNodeText(previousModel, RecListenersPortletKeys.SOLUTION_REQUERIMENTS3);
		String currentSolution = getNodeText(model, RecListenersPortletKeys.SOLUTION_REQUERIMENTS3);
		logger.debug("previousSolution: " + previousSolution + " -- currentProblemStatement: " + currentSolution);
		hasBeenUpdated = previousSolution.equalsIgnoreCase(currentSolution) ? false : true;
		return hasBeenUpdated;
	}

	private boolean solutionHasBeenUpdated4(JournalArticle model) throws DocumentException {
		logger.debug("solutionHasBeenUpdated4...");
		boolean hasBeenUpdated = false;
		JournalArticle previousModel = JournalArticleLocalServiceUtil.getPreviousApprovedArticle(model);
		String previousSolution = getNodeText(previousModel, RecListenersPortletKeys.SOLUTION_REQUERIMENTS4);
		String currentSolution = getNodeText(model, RecListenersPortletKeys.SOLUTION_REQUERIMENTS4);
		logger.debug("previousSolution: " + previousSolution + " -- currentProblemStatement: " + currentSolution);
		hasBeenUpdated = previousSolution.equalsIgnoreCase(currentSolution) ? false : true;
		return hasBeenUpdated;
	}

	private boolean solutionHasBeenUpdated5(JournalArticle model) throws DocumentException {
		logger.debug("solutionHasBeenUpdated5...");
		boolean hasBeenUpdated = false;
		JournalArticle previousModel = JournalArticleLocalServiceUtil.getPreviousApprovedArticle(model);
		String previousSolution = getNodeText(previousModel, RecListenersPortletKeys.SOLUTION_REQUERIMENTS5);
		String currentSolution = getNodeText(model, RecListenersPortletKeys.SOLUTION_REQUERIMENTS5);
		logger.debug("previousSolution: " + previousSolution + " -- currentProblemStatement: " + currentSolution);
		hasBeenUpdated = previousSolution.equalsIgnoreCase(currentSolution) ? false : true;
		return hasBeenUpdated;
	}

}
