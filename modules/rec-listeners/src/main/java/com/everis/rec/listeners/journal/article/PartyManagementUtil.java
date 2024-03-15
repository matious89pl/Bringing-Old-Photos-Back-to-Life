package com.everis.rec.listeners.journal.article;

import com.everis.rec.ddl.journal.article.model.DDL_JournalArticle;
import com.everis.rec.ddl.journal.article.service.DDL_JournalArticleLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.dynamic.data.lists.model.DDLRecord;
import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.dynamic.data.lists.service.DDLRecordSetLocalServiceUtil;
import com.liferay.dynamic.data.lists.service.persistence.DDLRecordUtil;
import com.liferay.dynamic.data.mapping.model.DDMContent;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMContentLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.DocumentException;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class PartyManagementUtil {
	private final Log logger = LogFactoryUtil.getLog(PartyManagementUtil.class);

	private final static String PM_MILESTONE = "PM-MILESTONE";

	private final static String PM_ACTIVITYLOG = "PM-ACTIVITY-LOG";

	private final static String PM_BUSINESS_SOLUTION = "PM-BUSINESS-SOLUTION";

	private final static String PM_INFOSECURITY_DATAPROT = "PM-INFOSECURITY-DATAPROT";

	private final static String PM_DOCS = "PM-DOCS";

	public void checkUpdatesAndApplyLogic(JournalArticle model) {
		Double version = 1.0;
		try {

			if (model.getVersion() != version) {
				logger.info(model.getVersion() + " - " + version);
				String name = "APP-Docs-".concat(String.valueOf(model.getResourcePrimKey()));
				DDL_JournalArticle ddJA = DDL_JournalArticleLocalServiceUtil
						.getDDL_JournalArticleByJournalPKAndName(model.getResourcePrimKey(), name);
				long doscRecordSetId = ddJA.getDdlRecordSetId();
				List<DDLRecord> docDDRecordList = DDLRecordUtil.findByRecordSetId(doscRecordSetId, -1, -1);

				if (docDDRecordList.size() > 0) {
					for (DDLRecord ddlRecord : docDDRecordList) {
						long storageId = ddlRecord.getDDMStorageId();
						DDMContent docContent = DDMContentLocalServiceUtil.getContent(storageId);
						JSONObject docContentJSON = JSONFactoryUtil.createJSONObject(docContent.getData());
						JSONArray fieldValuesArray = (JSONArray) docContentJSON.get("fieldValues");
						for (int i = 0; i < fieldValuesArray.length(); i++) {
							JSONObject JSONField = fieldValuesArray.getJSONObject(i);
							if (JSONField.get("name").equals("DocumentsAndMedia2arq")) {
								JSONObject JSONFieldValue = (JSONObject) JSONField.get("value");
								JSONObject JSONDLFile = JSONFactoryUtil
										.createJSONObject(JSONFieldValue.get("en_GB").toString());

								Role roleGuest = RoleLocalServiceUtil.getRole(model.getCompanyId(),
										RoleConstants.GUEST);
								ResourcePermission resourcePermission = ResourcePermissionLocalServiceUtil
										.getResourcePermission(model.getCompanyId(), DLFileEntry.class.getName(),
												ResourceConstants.SCOPE_INDIVIDUAL,
												JSONDLFile.get("classPK").toString(), roleGuest.getRoleId());
								resourcePermission.setActionIds(0);
								resourcePermission.setViewActionId(false);
								ResourcePermissionLocalServiceUtil.updateResourcePermission(resourcePermission);

								Role roleSM = RoleLocalServiceUtil.getRole(model.getCompanyId(),
										RoleConstants.SITE_MEMBER);
								ResourcePermission resourcePermissionSM = ResourcePermissionLocalServiceUtil
										.getResourcePermission(model.getCompanyId(), DLFileEntry.class.getName(),
												ResourceConstants.SCOPE_INDIVIDUAL,
												JSONDLFile.get("classPK").toString(), roleSM.getRoleId());
								resourcePermissionSM.setActionIds(0);
								resourcePermissionSM.setViewActionId(false);
								ResourcePermissionLocalServiceUtil.updateResourcePermission(resourcePermissionSM);

								break;
							}
						}
					}
				}

				logger.debug("model.getLayoutUuid()... " + model.getLayoutUuid());
			}

			else {
				// status == 0 : accepted
				// status == 1 : pending
				if (model.getStatus() == 0) {
					createDDLStructureByName(model, PM_MILESTONE);
					createDDLStructureByName(model, PM_ACTIVITYLOG);
					createDDLStructureByName(model, PM_BUSINESS_SOLUTION);
					createDDLStructureByName(model, PM_INFOSECURITY_DATAPROT);
					createDDLStructureByName(model, PM_DOCS);
				}
			}
		} catch (DocumentException | PortalException e) {
			logger.error("Error getting reading Journal Article Content... " + e);
		}
	}

	private void createDDLStructureByName(JournalArticle model, String structureName) throws DocumentException {
		logger.info("Creating DDL " + structureName + " for Application(PM): " + model.getResourcePrimKey());
		long structureId = getStructureIdByName(model, structureName);
		if (Validator.isNotNull(structureId)) {
			String prefix = StringPool.BLANK, key = StringPool.BLANK;
			switch (structureName) {
			case PM_MILESTONE:
				prefix = "APP-Milestone-";
				key = "APPM";
				break;
			case PM_ACTIVITYLOG:
				prefix = "APP-Activity-Log-";
				key = "APPAL";
				break;
			case PM_BUSINESS_SOLUTION:
				prefix = "APP-Business-Solution-";
				key = "APPBS";
				break;
			case PM_INFOSECURITY_DATAPROT:
				prefix = "APP-ISDP-";
				key = "APPISDP";
				break;
			case PM_DOCS:
				prefix = "APP-Docs-";
				key = "APPDOCS";
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
			if (JournalArticleUtil.addDDLJournalRelation(ddlRecordSet, model)) {
				logger.info("DDL_Jounal added!");

			}
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

//	private String getNodeText(JournalArticle model, String fieldName) throws DocumentException {
//		logger.trace("model: " + model.getArticleId());
//		logger.trace("getContent: " + model.getContent());
//		Document document = SAXReaderUtil.read(model.getContent());
//		logger.trace("document: " + document.getText());
//		Node node = document.selectSingleNode("//root//dynamic-element[@name='" + fieldName + "']//dynamic-content");
//		if (node == null) {
//			logger.debug("There is no field on the structure for the following fieldName");
//			return "";
//		} else {
//			logger.debug("node.getText(): " + node.getText());
//			return node.getText();
//		}
//	}

}
