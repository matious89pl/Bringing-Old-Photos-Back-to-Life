package com.everis.rec.listeners.journal.article;

import com.everis.messages.service.builder.service.MessagesLocalServiceUtil;
import com.everis.rec.ddl.journal.article.model.DDL_JournalArticle;
import com.everis.rec.ddl.journal.article.service.DDL_JournalArticleLocalServiceUtil;
import com.everis.rec.listeners.constants.RecListenersPortletKeys;
import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.dynamic.data.lists.service.DDLRecordSetLocalServiceUtil;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ImpactAssessmentUtil {
	private final Log logger = LogFactoryUtil.getLog(ChangeProposalUtil.class);

	private final static String IA_QA_A = "IA-QA-A";

	private final static String IA_QA_B = "IA-QA-B";

	public void checkUpdatesAndApplyLogic(JournalArticle model) {
		Double version = 1.0;

		logger.info("Check updates method  ");
		try {
			if (model.getVersion() != version) {
				logger.info(model.getVersion() + " - " + version);
				logger.debug("model.getLayoutUuid()... " + model.getLayoutUuid());
				if (commentsDeadlineHasBeenUpdated(model)) {
					logger.info(
							"ImpactAssessmentCommentsDeadlineHasBeenUpdated - Sending Notification 14 listener by Segments");
					logger.debug("model.getArticleId(): " + model.getArticleId());
					MessagesLocalServiceUtil.sendNotification14(model.getResourcePrimKey());

				}
				if (chooseQATemplateHasBeenUpdated(model)) {
					updateDDLByStructure(model);
				}
			} else {
				logger.info("New impact assesment is created - sending notification 13 listener by Segments");

				MessagesLocalServiceUtil.sendNotification13(model.getResourcePrimKey());
				if (model.getStatus() == 0) {
					logger.info("Impact Assessment approved");
					createQAStructure(model);
				}
			}
		} catch (DocumentException e) {
			logger.error("Error getting reading Journal Article Content... " + e);
		} catch (ParseException e) {
			logger.error("Error parsing currentCommentsDateline to Date on commentsDeadlineHasBeenUpdated method " + e);
		}

		String docFieldName = "IA_Documents";
		try {
			ResourcePermissionUtil.reviewJAFilesPermissions(model.getPrimaryKey(),docFieldName);
		} catch (DocumentException | PortalException e) {
			logger.error("Error while reviewing IA docs permissions");
			e.printStackTrace();
		}

	}

	private void updateDDLByStructure(JournalArticle model) throws DocumentException {
		logger.info("updateDDLByStructure method");
		List<DDL_JournalArticle> ddl_JournalArticleList = DDL_JournalArticleLocalServiceUtil
				.getDDL_JournalArticleByJournalPK(model.getResourcePrimKey());
		if (ddl_JournalArticleList.size() > 0) {
			DDL_JournalArticle ddl_JournalArticle = ddl_JournalArticleList.get(0);
			logger.info("ddl_JournalArticle: " + ddl_JournalArticle);
			// A
			long structureId = getStructureIdBytemplateOption(model);

			try {
				DDLRecordSet ddlRecordSet = DDLRecordSetLocalServiceUtil
						.getDDLRecordSet(ddl_JournalArticle.getDdlRecordSetId());
				ddlRecordSet.setDDMStructureId(structureId);
				DDLRecordSetLocalServiceUtil.updateDDLRecordSet(ddlRecordSet);
				logger.info("ddlRecordSet updated with a new DDMStructureId: " + structureId);
			} catch (PortalException e) {
				logger.error(
						"Error getting a DDLRecordSet by ddlRecordSetId: " + ddl_JournalArticle.getDdlRecordSetId());
			}
		}
	}

	private void createQAStructure(JournalArticle model) throws DocumentException {
		logger.info("Creating DDL Questions & Answers for Impact Assessment: " + model.getResourcePrimKey());
		long structureId = getStructureIdBytemplateOption(model);
		if (Validator.isNotNull(structureId)) {
			Map<Locale, String> titleMap = new HashMap<>();
			String resourcePrimKey = String.valueOf(model.getResourcePrimKey());
			String title = "Impact-Assessment-".concat(resourcePrimKey);
			titleMap.put(Locale.ENGLISH, title);
			logger.debug("title: " + title);
			Map<Locale, String> descriptionMap = new HashMap<>();
			titleMap.put(Locale.ENGLISH, title);
			String recordSetKey = "IA".concat(String.valueOf(model.getResourcePrimKey()));

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
				logger.error("Error adding new DDL Record for Impact Assessment: " + model.getResourcePrimKey());
			}
			logger.info("ddlRecordSet: " + ddlRecordSet);
			if (JournalArticleUtil.addDDLJournalRelation(ddlRecordSet, model))
				logger.info("DDL_Jounal added!");
		}
	}

	private long getStructureIdBytemplateOption(JournalArticle model) throws DocumentException {
		String templateOption = getNodeText(model, RecListenersPortletKeys.IA_CHOOSEQATEMPLATE);
		long structureId = 0;
		logger.debug("templateOption: " + templateOption);
		DynamicQuery dynamicQuery = DDMStructureLocalServiceUtil.dynamicQuery();
		switch (templateOption) {
		case "A":
			dynamicQuery.add(PropertyFactoryUtil.forName("name").like("%" + IA_QA_A + "%"));
			break;
		case "B":
			dynamicQuery.add(PropertyFactoryUtil.forName("name").like("%" + IA_QA_B + "%"));
			break;
		default:
			logger.info("There is no template option selected for a Impact Assessment");
		}
		List<DDMStructure> ddmStructureList = DDMStructureLocalServiceUtil.dynamicQuery(dynamicQuery);
		if (ddmStructureList.size() > 0) {
			structureId = ddmStructureList.get(0).getStructureId();
		}
		logger.debug("structureId: " + structureId);
		return structureId;
	}

	private boolean commentsDeadlineHasBeenUpdated(JournalArticle model) throws DocumentException, ParseException {
		logger.debug("commentsDeadlineHasBeenUpdated... starting method");
		boolean hasBeenUpdated = false;
		JournalArticle previousModel = JournalArticleLocalServiceUtil.getPreviousApprovedArticle(model);
		if (!isCustomUpdate(previousModel, model)) {
			logger.debug("commentsDeadlineHasBeenUpdated... in isCustomUpdate");
			String previousCommentsDeadline = getNodeText(previousModel, RecListenersPortletKeys.IA_RESPONSES_DEADLINE);
			String currentCommentsDeadline = getNodeText(model, RecListenersPortletKeys.IA_RESPONSES_DEADLINE);
			logger.debug("previousCommentsDeadline: " + previousCommentsDeadline + " -- currentCommentsDeadline: "
					+ currentCommentsDeadline);
			if (!currentCommentsDeadline.isEmpty()) {
				Date nowDate = new Date();
				Date currentCommentsDeadlineDate = DateUtil.parseDate("yyyy-MM-dd", currentCommentsDeadline,
						Locale.getDefault());
				hasBeenUpdated = (!previousCommentsDeadline.equalsIgnoreCase(currentCommentsDeadline)
						&& currentCommentsDeadlineDate.after(nowDate)) ? true : false;
			}
		}
		logger.debug("commentsDeadlineHasBeenUpdated: " + hasBeenUpdated);
		return hasBeenUpdated;
	}

	private boolean documentsHasBeenUpdated(JournalArticle model) throws DocumentException, ParseException {
		logger.debug("documentsHasBeenUpdated... starting method");
		boolean hasBeenUpdated = false;
		JournalArticle previousModel = JournalArticleLocalServiceUtil.getPreviousApprovedArticle(model);
		if (!isCustomUpdate(previousModel, model)) {
			logger.debug("documentsHasBeenUpdated... in isCustomUpdate");
			String previousDocuments = getNodeText(previousModel, RecListenersPortletKeys.IA_DOCUMENTS);
			String currentDocuments = getNodeText(model, RecListenersPortletKeys.IA_DOCUMENTS);
			logger.debug("previousDocuments: " + previousDocuments + " -- currentDocuments: " + currentDocuments);
			hasBeenUpdated = (!previousDocuments.equalsIgnoreCase(currentDocuments) ? true : false);
		}
		logger.debug("documentsHasBeenUpdated: " + hasBeenUpdated);
		return hasBeenUpdated;
	}

	private boolean chooseQATemplateHasBeenUpdated(JournalArticle model) throws DocumentException, ParseException {
		logger.info("chooseQATemplateHasBeenUpdated... starting method");
		boolean hasBeenUpdated = false;
		JournalArticle previousModel = JournalArticleLocalServiceUtil.getPreviousApprovedArticle(model);
		if (!isCustomUpdate(previousModel, model)) {
			logger.info("chooseQATemplateHasBeenUpdated... in isCustomUpdate");
			String previousChooseQATemplate = getNodeText(previousModel, RecListenersPortletKeys.IA_CHOOSEQATEMPLATE);
			String currentChooseQATemplate = getNodeText(model, RecListenersPortletKeys.IA_CHOOSEQATEMPLATE);
			logger.info("previousChooseQATemplate: " + previousChooseQATemplate + " -- currentChooseQATemplate: "
					+ currentChooseQATemplate);
			hasBeenUpdated = (!previousChooseQATemplate.equalsIgnoreCase(currentChooseQATemplate) ? true : false);
		}
		logger.info("chooseQATemplateHasBeenUpdated: " + hasBeenUpdated);
		return hasBeenUpdated;
	}

	private boolean isCustomUpdate(JournalArticle previousModel, JournalArticle newModel) {
		Double oldVersion = previousModel.getVersion();
		Double newVersion = newModel.getVersion();
		logger.debug("oldVersion: " + oldVersion + " , newVersion: " + newVersion);
		return oldVersion.equals(newVersion) ? true : false;
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

}
