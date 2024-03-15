package com.everis.rec.listeners.journal.article;

import com.everis.messages.service.builder.service.MessagesLocalServiceUtil;
import com.everis.rec.listeners.constants.RecListenersPortletKeys;
import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.dynamic.data.lists.service.DDLRecordSetLocalServiceUtil;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowDefinition;
import com.liferay.portal.kernel.workflow.WorkflowDefinitionManagerUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.workflow.kaleo.model.KaleoDefinition;
import com.liferay.portal.workflow.kaleo.service.KaleoDefinitionLocalServiceUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class NominationUtil {

	private final Log logger = LogFactoryUtil.getLog(NominationUtil.class);

	private final static String NOMINATIONS = "Nomination";

	public void checkUpdatesAndApplyLogic(JournalArticle model) {
		Double version = 1.0;
		try {
			String status = getNodeText(model, "StatusNomination");
			if (model.getVersion() != version) {

				try {
					if (closingDateHasBeenUpdated(model)) {
						logger.info("Closing date updated");
						String dateJournalArticle = getNodeText(model, "ClosingDateNomination");

						Date date = new Date();

						String newParseDate = new SimpleDateFormat("yyyy-MM-dd").format(date);

						int dateValue = DateUtil.compareTo(stringToDate(dateJournalArticle),
								stringToDate(newParseDate));

						if (dateValue < 0) {
							MessagesLocalServiceUtil.sendNotification39(model.getUserId(), model.getResourcePrimKey(),
									model.getGroupId(), "REC Contract Managers");
							logger.debug(
									"Notification 39 sent when closing date nomination is updated and less than actual date.");
						}

					} else if (StatusHasBeenUpdated(model)) {
						logger.info("Status updated");

						logger.info("status" + status);
						if (status.equals("Election-in-Progress")) {
							logger.info("Enviando notificacion 39");
							MessagesLocalServiceUtil.sendNotification39(model.getUserId(), model.getResourcePrimKey(),
									model.getGroupId(), "REC Contract Managers");
							logger.debug("Notification 39 sent when status is updated.");

							MessagesLocalServiceUtil.sendNotification41(model.getUserId(), model.getResourcePrimKey(),
									model.getGroupId(), "REC Contract Managers");
							logger.debug("Notification 41 sent when a election is open.");
						} else if (status.equals("Completed")) {
							MessagesLocalServiceUtil.sendNotification42(model.getUserId(), model.getResourcePrimKey(),
									model.getGroupId(), "REC Contract Managers");

						}

					}

				} catch (ParseException e) {
					// TODO Auto-generated catch block
					logger.error("Error updating nomination closing date notification.");
				}

			} else {
				logger.debug("Creating related Nomination DDL...");
				createRelatedDDL(model);
				logger.debug("Related Nomination DDL created correctly");

				MessagesLocalServiceUtil.sendNotification43(model.getUserId(), model.getResourcePrimKey(),
						model.getGroupId(), "REC Contract Managers");
				logger.debug("Notification 43 sent when nomination is created.");

				// logger.info("DDLs already created for this Nomination: " +
				// model.getResourcePrimKey());

			}
		} catch (

		DocumentException e) {
			logger.error("Error getting reading Journal Article Content... " + e);
		}
	}

	private void createRelatedDDL(JournalArticle article) throws DocumentException {
		logger.debug("createRelatedDDL... ");
		createDDLStructureByName(article, NOMINATIONS);
	}

	private void createDDLStructureByName(JournalArticle model, String structureName) throws DocumentException {
		logger.info("Creating DDL " + structureName + " for Nomination: " + model.getResourcePrimKey());
		long structureId = getStructureIdByName(model, structureName);
		if (Validator.isNotNull(structureId)) {
			String prefix = StringPool.BLANK, key = StringPool.BLANK;
			switch (structureName) {
			case NOMINATIONS:
				prefix = "LIST-NOMINATION-";
				key = "LSN";
				break;
			}

			Map<Locale, String> titleMap = new HashMap<>();
			// Version 1,0
//			String resourcePrimKey = String.valueOf(model.getResourcePrimKey());
//			String title = prefix.concat(resourcePrimKey);
			// Version 2.0
			String resourcePrimKey = String.valueOf(model.getResourcePrimKey());
			String title = prefix.concat(resourcePrimKey).concat(": ").concat(model.getTitle());
			titleMap.put(Locale.ENGLISH, title);
			logger.debug("title: " + title);
			Map<Locale, String> descriptionMap = new HashMap<>();
			descriptionMap.put(Locale.ENGLISH, title);
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
				logger.error("Error adding new DDL Record for Nomination: " + model.getResourcePrimKey());
			}
			logger.info("ddlRecordSet: " + ddlRecordSet);
			// Associate the ddl to workflow
			try {
				List<KaleoDefinition> list = KaleoDefinitionLocalServiceUtil.getKaleoDefinitions(QueryUtil.ALL_POS,
						QueryUtil.ALL_POS);
				String name = StringPool.BLANK;

				for (KaleoDefinition k : list) {
					if (k.getTitle() != null && k.getTitle().contains("Nomination")) {
						name = k.getName();
						break;
					}
				}

				logger.info("Looking for workDefinitionLink: " + name);
				WorkflowDefinition workflowDef = WorkflowDefinitionManagerUtil
						.getLatestWorkflowDefinition(model.getCompanyId(), name);
				WorkflowDefinitionLinkLocalServiceUtil.addWorkflowDefinitionLink(model.getUserId(),
						model.getCompanyId(), model.getGroupId(), DDLRecordSet.class.getName(),
						ddlRecordSet.getRecordSetId(), 0, workflowDef.getName(), workflowDef.getVersion());

			} catch (PortalException e) {
				// TODO Auto-generated catch block
				logger.error("Error assigning workflow to DDL");
			}

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

	private boolean closingDateHasBeenUpdated(JournalArticle model) throws DocumentException, ParseException {

		boolean hasBeenUpdated = false;
		JournalArticle previousModel = JournalArticleLocalServiceUtil.getPreviousApprovedArticle(model);
		if (!isCustomUpdate(previousModel, model)) {
			logger.info("chooseQATemplateHasBeenUpdated... in isCustomUpdate");
			String previousClosingDate = getNodeText(previousModel, RecListenersPortletKeys.CLOSING_DATE_NOMINATION);

			String currentClosingDate = getNodeText(model, RecListenersPortletKeys.CLOSING_DATE_NOMINATION);
			logger.info(
					"previousClosingDate: " + previousClosingDate + " -- currentClosingDate: " + currentClosingDate);
			hasBeenUpdated = (!currentClosingDate.equalsIgnoreCase(previousClosingDate) ? true : false);
		}
		logger.info("closingDateHasBeenUpdated: " + hasBeenUpdated);
		return hasBeenUpdated;
	}

	private boolean StatusHasBeenUpdated(JournalArticle model) throws DocumentException, ParseException {
		logger.info("inProgressHasBeenUpdated... starting method");
		boolean hasBeenUpdated = false;
		JournalArticle previousModel = JournalArticleLocalServiceUtil.getPreviousApprovedArticle(model);
		if (!isCustomUpdate(previousModel, model)) {
			logger.info("inProgressHasBeenUpdated... in isCustomUpdate");
			String previousStatus = getNodeText(previousModel, RecListenersPortletKeys.STATUS_NOMINATION);

			String currentStatus = getNodeText(model, RecListenersPortletKeys.STATUS_NOMINATION);
			logger.info("previousStatus: " + previousStatus + " -- currentStatus: " + currentStatus);
			hasBeenUpdated = (!previousStatus.equalsIgnoreCase(currentStatus) ? true : false);
		}
		logger.info("inProgressHasBeenUpdated: " + hasBeenUpdated);
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

	private Date stringToDate(String inputDate) {
		try {
			inputDate = inputDate.replace(" UTC", "");
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(inputDate);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

	}

}
