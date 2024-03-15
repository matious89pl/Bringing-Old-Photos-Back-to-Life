/** 
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 * <p>
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * <p>
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.everis.cproposal.service.impl;

import com.everis.cproposal.model.recFormArticle;
import com.everis.cproposal.service.recFormArticleLocalServiceUtil;
import com.everis.cproposal.service.base.recFormArticleServiceBaseImpl;
import com.everis.cproposal.util.ApplicationUtil;
import com.everis.cproposal.util.CProposalGetFormInfoUtil;
import com.everis.cproposal.util.CommitteeActionLogUtil;
import com.everis.cproposal.util.CommitteeNominationsUtil;
import com.everis.cproposal.util.CommitteeUtil;
import com.everis.cproposal.util.CommitteeVotingProcessUtil;
import com.everis.cproposal.util.ConsultationUtil;
import com.everis.cproposal.util.ImpactAssesmentUtil;
import com.everis.cproposal.util.RPAUtil;
import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.service.CalendarBookingLocalServiceUtil;
import com.liferay.calendar.service.CalendarLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryTypeLocalServiceUtil;
import com.liferay.dynamic.data.mapping.model.DDMContent;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.DDMFormInstanceRecord;
import com.liferay.dynamic.data.mapping.model.DDMFormInstanceRecordVersion;
import com.liferay.dynamic.data.mapping.model.DDMStorageLink;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.Value;
import com.liferay.dynamic.data.mapping.service.DDMContentLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMFormInstanceRecordLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMFormInstanceRecordVersionLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMStorageLinkLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMStructureServiceUtil;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Junction;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Phone;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroupRole;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.activation.MimetypesFileTypeMap;

import org.osgi.service.component.annotations.Component;

@Component(property = { "json.web.service.context.name=cproposal",
		"json.web.service.context.path=recFormArticle" }, service = AopService.class)
public class recFormArticleServiceImpl extends recFormArticleServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use
	 * <code>com.everis.cproposal.service.recFormArticleServiceUtil</code> to access
	 * the rec form article remote service.
	 */

	private final Log logger = LogFactoryUtil.getLog(recFormArticleServiceImpl.class);

	private static final String Email_address = "Email address";

	private static final String Change_Proposal_Title = "Change Proposal Title";

	private static final String Attach_a_document = "Attach a document";

	public void updateChangeProposalJournalArticle(String fieldNameProblemStatement,
			String fieldNameSolutionRequeriments, String fieldNameSolutionRequeriments2,
			String fieldNameSolutionRequeriments3, String fieldNameSolutionRequeriments4,
			String fieldNameSolutionRequeriments5, String newTextProblemStatement, String newTextSolutionRequeriments,
			String newTextSolutionRequeriments2, String newTextSolutionRequeriments3,
			String newTextSolutionRequeriments4, String newTextSolutionRequeriments5, long groupId, String articleId)
			throws PortalException, DocumentException {

		JournalArticle model = JournalArticleLocalServiceUtil.getArticle(groupId, articleId);
		Document document = SAXReaderUtil.read(model.getContent());
		Node nodeProblemStatement = document.selectSingleNode(
				"//root//dynamic-element[@name='" + fieldNameProblemStatement + "']//dynamic-content");
		nodeProblemStatement.setText(newTextProblemStatement);

		Node nodeSolutionRequeriments = document.selectSingleNode(
				"//root//dynamic-element[@name='" + fieldNameSolutionRequeriments + "']//dynamic-content");
		nodeSolutionRequeriments.setText(newTextSolutionRequeriments);

		Node nodeSolutionRequeriments2 = document.selectSingleNode(
				"//root//dynamic-element[@name='" + fieldNameSolutionRequeriments2 + "']//dynamic-content");
		nodeSolutionRequeriments2.setText(newTextSolutionRequeriments2);

		Node nodeSolutionRequeriments3 = document.selectSingleNode(
				"//root//dynamic-element[@name='" + fieldNameSolutionRequeriments3 + "']//dynamic-content");
		nodeSolutionRequeriments3.setText(newTextSolutionRequeriments3);

		Node nodeSolutionRequeriments4 = document.selectSingleNode(
				"//root//dynamic-element[@name='" + fieldNameSolutionRequeriments4 + "']//dynamic-content");
		nodeSolutionRequeriments4.setText(newTextSolutionRequeriments4);

		Node nodeSolutionRequeriments5 = document.selectSingleNode(
				"//root//dynamic-element[@name='" + fieldNameSolutionRequeriments5 + "']//dynamic-content");
		nodeSolutionRequeriments5.setText(newTextSolutionRequeriments5);

		model.setContent(document.asXML());
		JournalArticleLocalServiceUtil.updateJournalArticle(model);

	}

    @JSONWebService(value = "update_cp_latest_update", method = "POST")
    public JSONObject updateCPLatestUpdate(String newLatestUpdate, long groupId, String articleId) {

        JSONObject response = JSONFactoryUtil.createJSONObject();
        JournalArticle model = null;

        String fieldNameLatestUpdate = "LatestUpdate";

        try {
            model = JournalArticleLocalServiceUtil.getArticle(groupId, articleId);
            Document document = SAXReaderUtil.read(model.getContent());

            Node nodeLatestUpdate = document.selectSingleNode("//root//dynamic-element[@name='" + fieldNameLatestUpdate + "']//dynamic-content");
            nodeLatestUpdate.setText(newLatestUpdate);

            model.setContent(document.asXML());
            JournalArticleLocalServiceUtil.updateJournalArticle(model);

            response.put("code", "200");
            response.put("message", "Latest Update field updated properly");
        } catch (PortalException | DocumentException e) {
            logger.error("Error updating Latest Update field", e);
            response.put("code", "500");
            response.put("message", "Error updating Latest Update field");
        }

        return response;
    }

    @JSONWebService(value = "update_cp_change_summary", method = "POST")
    public JSONObject updateCPChangeSummary(String newChangeSummary, long groupId, String articleId) {

        JSONObject response = JSONFactoryUtil.createJSONObject();
        JournalArticle model = null;

        String fieldNameChangeSummary = "ChangeSummary";

        try {
            model = JournalArticleLocalServiceUtil.getArticle(groupId, articleId);
            Document document = SAXReaderUtil.read(model.getContent());

            Node nodeLatestUpdate = document.selectSingleNode("//root//dynamic-element[@name='" + fieldNameChangeSummary + "']//dynamic-content");
            nodeLatestUpdate.setText(newChangeSummary);

            model.setContent(document.asXML());
            JournalArticleLocalServiceUtil.updateJournalArticle(model);

            response.put("code", "200");
            response.put("message", "Change Summary field updated properly");
        } catch (PortalException | DocumentException e) {
            logger.error("Error updating Change Summary field", e);
            response.put("code", "500");
            response.put("message", "Error updating Change Summary field");
        }

        return response;
    }

	public void updateImpactTabJournalArticle(String fieldNameIMP, String newTextIMP, long groupId,
			long resourcePrimKey) throws PortalException, DocumentException {

		JournalArticle article = JournalArticleLocalServiceUtil.fetchLatestArticle(resourcePrimKey);

		if (Validator.isNotNull(article)) {
			double version = article.getVersion();
			Document document = SAXReaderUtil.read(article.getContent());

			Node nodeIMP = document
					.selectSingleNode("//root//dynamic-element[@name='" + fieldNameIMP + "']//dynamic-content");
			nodeIMP.setText(newTextIMP);

			article.setContent(document.asXML());
			ServiceContext serviceContext = new ServiceContext();
			serviceContext.setScopeGroupId(article.getGroupId());
			serviceContext.setAddGroupPermissions(true);
			serviceContext.setAddGuestPermissions(false);
			try {
				JournalArticleLocalServiceUtil.updateArticle(article.getUserId(), article.getGroupId(),
						article.getFolderId(), article.getArticleId(), version, article.getContent(), serviceContext);
				logger.info("Nomination Journal Article edited!");

			} catch (PortalException e) {
				logger.error("Error editing Nomination Journal Article", e);
			}
		}

	}

	@JSONWebService(value = "Get PortraitURL from user", method = "POST")
	public String getPortraitURL(String email, long companyId, ThemeDisplay themeDisplay) throws PortalException {

		String userPortraitURL = UserLocalServiceUtil.getUserByEmailAddress(companyId, email)
				.getPortraitURL(themeDisplay);
		return userPortraitURL;
	}

	@JSONWebService(value = "Create Alternative Change Proposal", method = "POST")
	public JSONObject createAlternativeChangeProposalJournalArticle(long userId, long groupId, String articleId)
			throws PortalException, DocumentException {

		User user = UserLocalServiceUtil.getUser(userId);
		JournalArticle model = JournalArticleLocalServiceUtil.getArticle(groupId, articleId);

		long getResourcePrimaryKey = model.getResourcePrimKey();

		logger.debug("getResourcePrimaryKey: " + getResourcePrimaryKey);

		long formId = recFormArticleLocalService.getFormIdByArticleId(getResourcePrimaryKey);

		logger.debug("FormId: " + formId);
		DDMFormInstanceRecordVersion ddmFormInstanceVersion = DDMFormInstanceRecordVersionLocalServiceUtil
				.getDDMFormInstanceRecordVersion(formId);

		logger.info("ddmFormInstanceVersion: " + ddmFormInstanceVersion);

		Locale locale = ddmFormInstanceVersion.getDDMForm().getDefaultLocale();

		DDMFormValues ddmFormValues = ddmFormInstanceVersion.getDDMFormValues();

		List<DDMFormFieldValue> alternativeDdmFormFieldValues = ddmFormValues.getDDMFormFieldValues();

		for (DDMFormFieldValue ddmFormFieldValue : alternativeDdmFormFieldValues) {

			Value value = ddmFormFieldValue.getValue();
			DDMFormField ddmf = ddmFormFieldValue.getDDMFormField();
			String fieldLabel = ddmf.getLabel().getString(locale);
			logger.info("Label: " + fieldLabel);
			logger.info("Original Value: " + value.getString(locale));

			switch (fieldLabel) {
			case "Proposer Name":
				logger.info("Setting " + user.getFirstName() + " " + user.getLastName() + "...");
				value.addString(locale, user.getFirstName() + " " + user.getLastName());
				break;

			case "Company Type":
				logger.info("Setting []...");
				value.addString(locale, value.getString(locale));
				break;

			case "Email Address":
				logger.info("Setting " + user.getEmailAddress() + "...");
				value.addString(locale, user.getEmailAddress());
				break;

			case "Telephone Number":
				logger.info("Setting " + "00000000000 " + "...");
				value.addString(locale, "00000000000");
				try {
					List<Phone> phones = user.getPhones();

					for (int i = 0; i < phones.size(); i++) {

						logger.info("Phones " + phones.get(i) + "...");

						Pattern pattern = Pattern.compile("^[0-9]{11}$");
						Matcher matcher = pattern.matcher(phones.get(i).getNumber());
						boolean matches = matcher.matches();

						logger.info("Matches " + matches + "...");

						if (phones.get(i).getPrimary() && matches) {

							logger.info("Primary " + phones.get(i).getNumber() + "...");
							value.addString(locale, phones.get(i).getNumber());

						}

					}
				} catch (Exception e) {
					logger.error("Not founded Telephone" + "...");
					value.addString(locale, "00000000000");
				}
				break;

			case "Change Proposal Title":
				value.addString(locale, "---Add info here---");
				break;

			case "What is the issue you are seeking to solve?":
				logger.info("Setting ---Add info here---");
				value.addString(locale, "---Add info here---");
				break;

			case "What impact is this having?":
				logger.info("Setting ---Add info here---");
				value.addString(locale, "---Add info here---");
				break;

			case "What outcomes do you consider are needed to achieve an effective solution?":
				logger.info("Setting ---Add info here---");
				value.addString(locale, "---Add info here---");
				break;

			case "Which consumer types will be impacted by this Change Proposal?":
				logger.info("Setting [Option]...");
				value.addString(locale, value.getString(locale));
				break;

			case "What impact will this Change Proposal have for these consumers?":
				logger.info("Setting []...");
				value.addString(locale, value.getString(locale));
				break;

			case "The REC or REC Schedules":
				logger.info("Setting []...");
				value.addString(locale, "[]");
				break;

			case "The Data Specification":
				logger.info("Setting []...");
				value.addString(locale, "[]");
				break;

			case "Central Systems or REC Services":
				logger.info("Setting []...");
				value.addString(locale, "[]");
				break;

			case "REC Parties and/or Market Participants":
				logger.info("Setting []...");
				value.addString(locale, "[]");
				break;

			case "Other industry codes":
				logger.info("Setting []...");
				value.addString(locale, "[]");
				break;

			case "Does this Change Proposal impact a Significant Code Review?":
				logger.info("Setting []...");
				value.addString(locale, "[]");
				break;

			case "Do you believe this Change Proposal meets the criteria for 'Urgency' and should be progressed under an urgent timetable?":
				logger.info("Setting []...");
				value.addString(locale, "[]");
				break;

			case "Attachments":
				logger.info("Setting {}...");
				value.addString(locale, "{}");
				break;

			default:
				logger.info("Setting blank...");
				value.addString(locale, StringPool.BLANK);
				break;
			}
			ddmFormFieldValue.setValue(value);
		}

		ddmFormValues.setDDMFormFieldValues(alternativeDdmFormFieldValues);

		logger.debug("ddmFormValues: " + ddmFormValues.toString());
		logger.debug("ddmFormInstanceVersion.getUserId(): " + ddmFormInstanceVersion.getUserId());

		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setUserId(userId);

		DDMFormInstanceRecord alternariveFormInstanceRecord = DDMFormInstanceRecordLocalServiceUtil
				.addFormInstanceRecord(userId, groupId, ddmFormInstanceVersion.getFormInstanceId(), ddmFormValues,
						serviceContext);
		DDMFormInstanceRecordVersion alternariveFormInstanceRecordVersion = alternariveFormInstanceRecord
				.getFormInstanceRecordVersion();

		// Get AlternativeFormId
		long alternariveFormInstanceRecordVersionId = alternariveFormInstanceRecordVersion
				.getFormInstanceRecordVersionId();

		logger.debug("alternariveFormInstanceRecordVersionId: " + alternariveFormInstanceRecordVersionId);

		// Get original article
		recFormArticle originalRecFormArticle = recFormArticleLocalService.getrecFormArticle(formId);

		// Get list alternative
		String alternativeFormIds = originalRecFormArticle.getAlternativeFormIds();

		logger.debug("alternativeFormIds: " + alternativeFormIds);

		// Insert alternativeFormId into DB rec_cproposal
		if (!alternativeFormIds.isEmpty() || Validator.isNotNull(alternativeFormIds)) {
			String alternativeFormsIdsContatenated = alternativeFormIds
					.concat("," + alternariveFormInstanceRecordVersionId);
			originalRecFormArticle.setAlternativeFormIds(alternativeFormsIdsContatenated);

			logger.debug("alternativeFormsIdsContatenated: " + alternativeFormsIdsContatenated);

		} else {
			originalRecFormArticle.setAlternativeFormIds(String.valueOf(alternariveFormInstanceRecordVersionId));
			logger.debug(
					"originalRecFormArticle.setAlternativeFormIds(String.valueOf(alternariveFormInstanceRecordVersionId)) "
							+ originalRecFormArticle.getAlternativeFormIds());
		}

		recFormArticleLocalServiceUtil.updaterecFormArticle(originalRecFormArticle);

		long formInstanceId = alternariveFormInstanceRecord.getFormInstanceId();
		long formInstanceRecordId = alternariveFormInstanceRecord.getFormInstanceRecordId();

		logger.debug("formInstanceId: " + formInstanceId);
		logger.debug("formInstanceRecordId: " + formInstanceRecordId);

		JSONObject jsonResponse = JSONFactoryUtil.createJSONObject();

		jsonResponse.put("formInstanceId", formInstanceId);
		jsonResponse.put("formInstanceRecordId", formInstanceRecordId);

		return jsonResponse;
	}

	public HashMap<String, String> webContentFieldValues(long groupId, String articleId) throws PortalException {

		HashMap<String, String> fieldsStructureWebContentChangeProposal = new HashMap<String, String>();

		JournalArticle model = JournalArticleLocalServiceUtil.getArticle(groupId, articleId);

		try {
			Document document = SAXReaderUtil.read(model.getContent());
			String CPPageTitle = "ChangeProposalTitle";
			Node nodeTitle = document
					.selectSingleNode("//root//dynamic-element[@name='" + CPPageTitle + "']//dynamic-content");
			String getTitle = nodeTitle.getText();

			String CPPageEmail = "ChangeProposerEmail";
			Node nodeEmail = document
					.selectSingleNode("//root//dynamic-element[@name='" + CPPageEmail + "']//dynamic-content");
			String getEmail = nodeEmail.getText();

			fieldsStructureWebContentChangeProposal.put("ChangeProposalTitle", getTitle);
			fieldsStructureWebContentChangeProposal.put("ChangeProposerEmail", getEmail);

		} catch (DocumentException e1) {
			logger.error("Error reading journal article... " + e1);
		}

		return fieldsStructureWebContentChangeProposal;
	}

	@JSONWebService(value = "get_cp_by_release_schedule", method = "POST")
	public JSONObject getCPByReleaseSchedule() {
		JSONObject jsonResponse = JSONFactoryUtil.createJSONObject();
		org.json.JSONArray jsonResultArray = new org.json.JSONArray();

		List<String> optionLists = getReleaseScheduleOptions();
		List<recFormArticle> recFormArticleList = new ArrayList<recFormArticle>();
		if (!optionLists.isEmpty()) {
			for (String scheduleReleaseOption : optionLists) {
				org.json.JSONObject eachWholeReleaseScheduleData = new org.json.JSONObject();
				org.json.JSONArray eachReleaseSchedule = new org.json.JSONArray();
				logger.debug("scheduleReleaseOption " + scheduleReleaseOption);
				DynamicQuery dynamicQuery = recFormArticleLocalServiceUtil.dynamicQuery();

				Criterion criterionApproved = PropertyFactoryUtil.forName("articleStatus")
						.like("%Approved - awaiting implementation%");
				Criterion criterionImplemented = PropertyFactoryUtil.forName("articleStatus").like("%Implemented%");

				Junction disjunction = RestrictionsFactoryUtil.disjunction();
				disjunction.add(criterionApproved);
				disjunction.add(criterionImplemented);
				dynamicQuery.add(disjunction);

				dynamicQuery.add(
						PropertyFactoryUtil.forName("articleReleaseSchedule").like("%" + scheduleReleaseOption + "%"));
				recFormArticleList = recFormArticleLocalServiceUtil.dynamicQuery(dynamicQuery);

				logger.debug(
						"recFormArticleList.size() - " + recFormArticleList.size() + recFormArticleList.toString());

				if (recFormArticleList.size() > 0) {
					for (recFormArticle recFormArticle : recFormArticleList) {
						org.json.JSONObject eachCPdata = new org.json.JSONObject();
						logger.debug("3. recFormArticle.getArticleId() : " + recFormArticle.getArticleId());
						JournalArticle article = JournalArticleLocalServiceUtil
								.fetchLatestArticle(recFormArticle.getArticleId());
						String cpURLTitle = article.getUrlTitle();
						String cpReference = getNodeText(article, "ChangeProposalReference");
						String cpTitle = article.getTitle();
						eachCPdata.put("cpURLTitle", cpURLTitle);
						eachCPdata.put("cpReference", cpReference);
						eachCPdata.put("cpTitle", cpTitle);
						eachReleaseSchedule.put(eachCPdata);
					}
					logger.debug("eachReleaseSchedule - " + eachReleaseSchedule.toString());
				}

				eachWholeReleaseScheduleData.put("releaseSchedule", scheduleReleaseOption);
				eachWholeReleaseScheduleData.put("changeProposalList", eachReleaseSchedule);
				jsonResultArray.put(eachWholeReleaseScheduleData);
			}
			jsonResponse.put("data", jsonResultArray);
			logger.debug("jsonResponse" + jsonResponse);

		} else {
			logger.debug("There is no schedule release options");
		}

		return jsonResponse;
	}

	private List<String> getReleaseScheduleOptions() {
		List<String> optionList = new ArrayList<String>();
		try {
			// get the dynamic structureId with a dynamic query
			DynamicQuery dynamicQuery = DDMStructureLocalServiceUtil.dynamicQuery();
			dynamicQuery.add(PropertyFactoryUtil.forName("name").like("%CHANGE-PROPOSAL%"));
			List<DDMStructure> ddmStructureList = DDMStructureLocalServiceUtil.dynamicQuery(dynamicQuery);
			if (ddmStructureList.size() == 1) {
				long structureId = ddmStructureList.get(0).getStructureId();
				DDMStructure ddmStructure = DDMStructureServiceUtil.getStructure(structureId);

				String definition = ddmStructure.getDefinition();
				org.json.JSONObject jsonDefinition = new org.json.JSONObject(definition);
				org.json.JSONArray arrayFields = jsonDefinition.getJSONArray("fields");
				logger.debug("jsonDefinition" + jsonDefinition);
				logger.debug("arrayFields" + arrayFields);
				org.json.JSONObject eachJsonField = new org.json.JSONObject();
				for (int i = 0; i < arrayFields.length(); i++) {
					eachJsonField = arrayFields.getJSONObject(i);
					String fieldReference = (String) eachJsonField.get("name");
					if (fieldReference.equals("CP_RELEASE_SCHEDULE")) {
						logger.debug("fieldReference" + fieldReference);
						break;
					}
				}

				org.json.JSONArray releaseScheduleOptions = eachJsonField.getJSONArray("options");

				for (int i = 0; i < releaseScheduleOptions.length(); i++) {
					org.json.JSONObject optionJsonObject = (org.json.JSONObject) releaseScheduleOptions.get(i);
					String optionToSave = (String) optionJsonObject.get("value");
					if (!optionToSave.isEmpty())
						optionList.add(optionToSave);

				}
				logger.debug("list " + optionList.toString());
			} else {
				logger.error("There is more than one DDMStructure with CHANGE-PROPOSAL name");
			}

		} catch (PortalException e) {
			e.printStackTrace();
		}
		return optionList;
	}

	private String getNodeText(JournalArticle model, String fieldName) {
		logger.trace("model: " + model.getArticleId());
		logger.trace("getContent: " + model.getContent());
		Document document;
		try {
			document = SAXReaderUtil.read(model.getContent());
			logger.trace("document: " + document.getText());
			Node node = document
					.selectSingleNode("//root//dynamic-element[@name='" + fieldName + "']//dynamic-content");
			if (node == null) {
				logger.debug("There is no field on the structure for the following fieldName");
			} else {
				logger.debug("node.getText(): " + node.getText());
				return node.getText();
			}
		} catch (DocumentException e) {
			logger.error("Error reading Journal Article content... " + e);
		}
		return "";
	}

	@JSONWebService(value = "get_Form_Field_Names_And_Label_By_Form_Id", method = "GET")
	public Map<String, JSONObject> getFormFieldNamesAndLabelByFormId(long forminstancerecordversionId) {
		Map<String, JSONObject> fieldNameLabelmap = new HashMap<String, JSONObject>();
		boolean hasOptions = false;

		try {
			DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion = DDMFormInstanceRecordVersionLocalServiceUtil
					.getDDMFormInstanceRecordVersion(forminstancerecordversionId);
			Long storageId = ddmFormInstanceRecordVersion.getStorageId();
			DDMStorageLink ddmStorageLink = DDMStorageLinkLocalServiceUtil.getClassStorageLink(storageId);
			Long structureId = ddmStorageLink.getStructureId();
			DDMStructure ddmStructure = DDMStructureLocalServiceUtil.getDDMStructure(structureId);
			String formDefinition = ddmStructure.getDefinition();
			JSONObject formDefinitionJson = JSONFactoryUtil.createJSONObject(formDefinition);
			JSONArray formFieldsArray = (JSONArray) formDefinitionJson.get("fields");
			// logger.info("formFieldsArray: " + formFieldsArray.toString());
			JSONObject eachFieldJsonObject = JSONFactoryUtil.createJSONObject();
			for (int i = 0; i < formFieldsArray.length(); i++) {
				boolean hasAdditionalInformation = false;
				JSONObject fieldsMapJson = JSONFactoryUtil.createJSONObject();
				eachFieldJsonObject = (JSONObject) formFieldsArray.get(i);
				// logger.info("eachFieldJsonObject: " + eachFieldJsonObject.toString());
				JSONObject labelJsonObject = eachFieldJsonObject.getJSONObject("label");
				// logger.info("labelJsonObject: " + labelJsonObject);
				String fieldLabel = labelJsonObject.getString("en_GB");
				String nameField = eachFieldJsonObject.getString("name");
				fieldsMapJson.put("name", fieldLabel);
				if (eachFieldJsonObject.get("options") != null) {
					JSONArray optionsJSONArray = JSONFactoryUtil.createJSONArray();
					optionsJSONArray = (JSONArray) eachFieldJsonObject.get("options");
					if (optionsJSONArray.length() > 0) {
						hasOptions = true;
						fieldsMapJson.put("hasOptions", hasOptions);
						// logger.info("optionsJSONArray: " + optionsJSONArray);
						JSONArray optionsMapArray = JSONFactoryUtil.createJSONArray();
						for (int j = 0; j < optionsJSONArray.length(); j++) {
							JSONObject eachOptionJsonObject = (JSONObject) optionsJSONArray.get(j);
							JSONObject optionMapObject = JSONFactoryUtil.createJSONObject();
							// value
							String optionNameValue = eachOptionJsonObject.getString("value");
							JSONObject labelOptionJsonObject = eachOptionJsonObject.getJSONObject("label");
							String optionLabel = labelOptionJsonObject.getString("en_GB");
							optionMapObject.put(optionLabel, optionNameValue);
							optionsMapArray.put(optionMapObject);
						}
						fieldsMapJson.put("options", optionsMapArray);
					}
				}
				if (hasAdditionalInfo(fieldLabel)) {
					hasAdditionalInformation = true;
					JSONObject additionalInformationJson = JSONFactoryUtil.createJSONObject();
					additionalInformationJson = (JSONObject) formFieldsArray.get(i + 1);
					String nameAdditionalInfo = additionalInformationJson.getString("name");
					fieldsMapJson.put("nameAdditionalInfo", nameAdditionalInfo);
					i++;
				}
				fieldsMapJson.put("hasAdditionalInformation", hasAdditionalInformation);
				fieldNameLabelmap.put(nameField, fieldsMapJson);
			}

		} catch (PortalException e) {
			logger.error("There is no ddmFormInstanceRecordVersion for this forminstancerecordversionId: "
					+ forminstancerecordversionId);
			e.printStackTrace();
		}
		return fieldNameLabelmap;
	}

	public Map<String, String> getCPFormDataMap(long forminstancerecordversionId) {
		Map<String, String> cpDataFormMap = new HashMap<String, String>();

		DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion;
		try {
			ddmFormInstanceRecordVersion = DDMFormInstanceRecordVersionLocalServiceUtil
					.getDDMFormInstanceRecordVersion(forminstancerecordversionId);
			Long storageId = ddmFormInstanceRecordVersion.getStorageId();
			DDMContent ddmContent = DDMContentLocalServiceUtil.getContent(storageId);
			String data = ddmContent.getData();
			JSONObject dataJsonObject = JSONFactoryUtil.createJSONObject(data);
			JSONArray dataFieldValuesArray = dataJsonObject.getJSONArray("fieldValues");
			for (int i = 0; i < dataFieldValuesArray.length(); i++) {
				JSONObject eachDataFieldJson = (JSONObject) dataFieldValuesArray.get(i);
				String name = eachDataFieldJson.getString("name");
				JSONObject valueObject = (JSONObject) eachDataFieldJson.get("value");
				String value = valueObject.getString("en_GB");
				cpDataFormMap.put(name, value);
			}

		} catch (PortalException e) {
			logger.error("There is no ddmFormInstanceRecordVersion with id: " + forminstancerecordversionId);
		}
		return cpDataFormMap;
	}

	private static boolean hasAdditionalInfo(String label) {
		switch (label) {
		case "What impact will this Change Proposal have for these consumers?":
			return true;
		case "The REC or REC Schedules":
			return true;
		case "The Data Specification":
			return true;
		case "Central Systems or REC Services":
			return true;
		case "REC Parties and/or Market Participants":
			return true;
		case "Other industry codes":
			return true;
		case "Does this Change Proposal impact a Significant Code Review?":
			return true;
		default:
			return false;
		}
	}

	@JSONWebService(value = "get_proposal_management_details", method = "GET")
	public Map<String, String> getProposalManagementDetailsFromForm(long forminstancerecordversionId) {
		Map<String, String> proposalMgmDataMap = new HashMap<String, String>();
		Map<String, String> dataFromFormMap = getCPFormDataMap(forminstancerecordversionId);
		if (!dataFromFormMap.isEmpty()) {
			try {
				DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion = DDMFormInstanceRecordVersionLocalServiceUtil
						.getDDMFormInstanceRecordVersion(forminstancerecordversionId);
				Long storageId = ddmFormInstanceRecordVersion.getStorageId();
				DDMStorageLink ddmStorageLink = DDMStorageLinkLocalServiceUtil.getClassStorageLink(storageId);
				Long structureId = ddmStorageLink.getStructureId();
				DDMStructure ddmStructure = DDMStructureLocalServiceUtil.getDDMStructure(structureId);
				String formDefinition = ddmStructure.getDefinition();
				JSONObject formDefinitionJson = JSONFactoryUtil.createJSONObject(formDefinition);
				JSONArray formFieldsArray = (JSONArray) formDefinitionJson.get("fields");
				JSONObject eachFieldJsonObject = JSONFactoryUtil.createJSONObject();
				int fields = 0;
				for (int i = 0; i < formFieldsArray.length(); i++) {
					if (fields == 3)
						break;
					eachFieldJsonObject = (JSONObject) formFieldsArray.get(i);
					JSONObject labelJsonObject = eachFieldJsonObject.getJSONObject("label");
					String fieldLabel = labelJsonObject.getString("en_GB");
//					logger.info("1. fieldLabel: " + fieldLabel);
					String nameField = eachFieldJsonObject.getString("name");
					switch (fieldLabel) {
					case Email_address:
						String emailAddressValue = dataFromFormMap.get(nameField);
						proposalMgmDataMap.put("CPProposerEmail", emailAddressValue);
						fields++;
						break;
					case Change_Proposal_Title:
						String cpTitleValue = dataFromFormMap.get(nameField);
						proposalMgmDataMap.put("CPTitle", cpTitleValue);
						fields++;
						break;
					case Attach_a_document:
						String cpDocumentAttached = dataFromFormMap.get(nameField);
						JSONObject docuJson = JSONFactoryUtil.createJSONObject(cpDocumentAttached);
						String title = docuJson.getString("title");
						proposalMgmDataMap.put("title", title);
						fields++;
						break;
					}
				}

			} catch (PortalException e) {
				logger.error("There is no ddmFormInstanceRecordVersion for this forminstancerecordversionId: "
						+ forminstancerecordversionId + " - " + e);
			}
		} else {
			logger.error(
					"There is no data register for this forminstancerecordversionId: " + forminstancerecordversionId);
		}

		return proposalMgmDataMap;
	}

	@JSONWebService(value = "get_all_committee_events", method = "GET")
	public List<CalendarBooking> getAllCommitteeEvents(long initialTime, long finalTime) {
		logger.debug("START - getAllCommitteeEvents with startTime: " + initialTime + " and endTime: " + finalTime);
		List<CalendarBooking> resultCalendarBookings = new ArrayList<CalendarBooking>();
		List<Calendar> calendars = new ArrayList<Calendar>();

		try {
			logger.debug("Getting calendars");
			calendars = CalendarLocalServiceUtil.getCalendars(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			logger.debug("Calendar list: " + calendars);
		} catch (Exception e) {
			logger.error("ERROR - Getting calendar list", e);
		}
		if (Validator.isNotNull(calendars)) {
			for (Calendar calendar : calendars) {
				List<CalendarBooking> calendarBookings = new ArrayList<CalendarBooking>();
				try {
					logger.debug("Getting calendar bookings for calendarId: " + calendar.getCalendarId());
					calendarBookings = CalendarBookingLocalServiceUtil.getCalendarBookings(calendar.getCalendarId(),
							initialTime, finalTime);
					logger.debug(
							"Calendar bookings for calendarId " + calendar.getCalendarId() + " : " + calendarBookings);
					resultCalendarBookings.addAll(calendarBookings);
				} catch (Exception e) {
					logger.error("ERROR - Getting calendar bookings", e);
				}

			}
		}
		logger.debug("resultCalendarBookings: " + resultCalendarBookings);

		logger.debug("END - getAllCommitteeEvents with startTime: " + initialTime + " and endTime: " + finalTime);
		return resultCalendarBookings;
	}

	@JSONWebService(value = "get_calendar_id_UE", method = "GET")
	public long get_Upcoming_events(long companyId, long groupId, String name) {
		long id = 0;
		logger.info(companyId + "/" + groupId + "/" + name);
		try {

			Calendar cal = CalendarLocalServiceUtil.fetchGroupCalendar(companyId, groupId, name);
			if (cal != null) {
				id = cal.getCalendarId();
			}
			logger.debug("CalendarId de Upcoming Events: " + id);
		} catch (Exception e) {
			logger.error("ERROR - Getting calendar id", e);
		}

		return id;
	}

	@JSONWebService(value = "get_RPA_Document", method = "GET")
	public JSONObject getRPADocument(long groupId, long folderId, String title, ServiceContext serviceContext) {

		JSONObject RPADocument = JSONFactoryUtil.createJSONObject();
		FileEntry fileEntry = null;
		String statusCode = "200";

		logger.info("User: " + serviceContext.getUserId());
		long serviceUserId = serviceContext.getUserId();
		try {
			User defaultUser = UserLocalServiceUtil.getUserByEmailAddress(serviceContext.getCompanyId(),
					"default@liferay.com");
			if (serviceUserId != defaultUser.getUserId() && serviceUserId != 0) {
				logger.info("User is able to see documents");

				try {
					fileEntry = DLAppServiceUtil.getFileEntry(groupId, folderId, title);
					logger.info("fileEntry " + fileEntry);
					DLFileEntry DLfileEntry = DLFileEntryLocalServiceUtil.getDLFileEntry(fileEntry.getFileEntryId());
					logger.info("DLfileEntry " + DLfileEntry);
					String processArea = DLFileEntryTypeLocalServiceUtil
							.getFileEntryType(DLfileEntry.getFileEntryTypeId()).getName(LocaleUtil.getDefault());
					logger.info("processArea " + processArea);
					// String dataItemTypeCF =
					// String.valueOf(fileEntry.getExpandoBridge().getAttribute("Data Item Type"));
					String[] dataItemTypeCFObject = (String[]) fileEntry.getExpandoBridge()
							.getAttribute("Data Item Type");
					String dataItemTypeCF = StringPool.BLANK;
					if (dataItemTypeCFObject.length > 0) {
						dataItemTypeCF = dataItemTypeCFObject[0];
					}
					String performacePeriodCF = String
							.valueOf(fileEntry.getExpandoBridge().getAttribute("PerformancePeriod"));
					logger.info("dataItemTypeCF " + dataItemTypeCF);
					logger.info("performacePeriodCF " + performacePeriodCF);

					InputStream inputStream = DLFileEntryLocalServiceUtil.getFileAsStream(fileEntry.getFileEntryId(),
							fileEntry.getVersion());
					byte[] data = FileUtil.getBytes(inputStream);

					RPADocument.put("createDate", fileEntry.getCreateDate());
					RPADocument.put("description", fileEntry.getDescription());
					RPADocument.put("extension", fileEntry.getExtension());
					RPADocument.put("fileEntryId", fileEntry.getFileEntryId());
					RPADocument.put("fileName", fileEntry.getFileName());
					RPADocument.put("folderId", fileEntry.getFolderId());
					RPADocument.put("groupId", fileEntry.getGroupId());
					RPADocument.put("mimeType", fileEntry.getMimeType());
					RPADocument.put("modifiedDate", fileEntry.getModifiedDate());
					RPADocument.put("repositoryId", fileEntry.getRepositoryId());
					RPADocument.put("title", fileEntry.getTitle());
					RPADocument.put("userName", fileEntry.getUserName());
					RPADocument.put("version", fileEntry.getVersion());
					RPADocument.put("Data Item Type", dataItemTypeCF);
					RPADocument.put("PerformancePeriod", performacePeriodCF);
					RPADocument.put("data", data);
					RPADocument.put("Process Area", processArea);
				} catch (PortalException | IOException e) {
					logger.error("Error getting RPA Document", e);
					statusCode = "400";
				}

			} else {
				logger.info("User is not able to see documents");
				statusCode = "401";
				RPADocument.put("message", "Unauthorised. Missing or invalid authentication key");
			}
		} catch (PortalException e1) {
			logger.error("Error getting default user", e1);
		}

		RPADocument.put("statusCode", statusCode);
		logger.info("RPADocument OK");
		return RPADocument;

	}

	@JSONWebService(value = "get_RPA_Documents", method = "GET")
	public JSONObject getRPADocumentList(long repositoryId, long folderId, String dataItemType,
			String performancePeriod, String modifiedSince, ServiceContext serviceContext) {

		JSONObject jsonResponse = JSONFactoryUtil.createJSONObject();
		JSONArray jsonResultArray = JSONFactoryUtil.createJSONArray();
		String statusCode = "200";

		List<FileEntry> documentList = new ArrayList<FileEntry>();
		List<FileEntry> documentListFiltered = new ArrayList<FileEntry>();

		logger.info("User: " + serviceContext.getUserId());
		long serviceUserId = serviceContext.getUserId();

		try {
			User defaultUser = UserLocalServiceUtil.getUserByEmailAddress(serviceContext.getCompanyId(),
					"default@liferay.com");
			if (serviceUserId != defaultUser.getUserId() && serviceUserId != 0) {
				logger.info("User is able to see documents");
				try {
					documentList = DLAppServiceUtil.getFileEntries(repositoryId, folderId);
					if (Validator.isNotNull(dataItemType)) {
						logger.info("Data Item Type Parameter: " + dataItemType);
						for (FileEntry file : documentList) {
							String[] dataItemTypeCFObject = (String[]) file.getExpandoBridge()
									.getAttribute("Data Item Type");
							String dataItemTypeCF = dataItemTypeCFObject[0];
							// String dataItemTypeCF =
							// String.valueOf(file.getExpandoBridge().getAttribute("Data Item Type"));
							logger.info("Data Item Type " + dataItemTypeCF);
							if (Validator.isNotNull(dataItemTypeCF) && dataItemTypeCF.equals(dataItemType)) {
								documentListFiltered.add(file);
								logger.info("File added " + file);
							}
						}
						documentList = new ArrayList<>(documentListFiltered);
						logger.info("documentList before filter by Data Item Type " + documentList);
						documentListFiltered.clear();
						logger.info("documentList before clear: " + documentList);
					}
					if (Validator.isNotNull(performancePeriod)) {
						logger.info("PerformancePeriod Parameter: " + performancePeriod);
						for (FileEntry file : documentList) {
							String performacePeriodCF = String
									.valueOf(file.getExpandoBridge().getAttribute("PerformancePeriod"));
							logger.info("PerformancePeriod " + performacePeriodCF);
							if (Validator.isNotNull(performacePeriodCF)
									&& performacePeriodCF.equals(performancePeriod)) {
								documentListFiltered.add(file);
								logger.info("File added " + file);
							}
						}
						documentList = new ArrayList<>(documentListFiltered);
						logger.info("documentList before filter by PerformancePeriod " + documentList);
						documentListFiltered.clear();
						logger.info("documentList before clear: " + documentList);
					}

					logger.info("documentList " + documentList);

					LocalDateTime queryDateTime = null;
					if (Validator.isNotNull(modifiedSince)) {
						queryDateTime = LocalDateTime.parse(modifiedSince,
								DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
						logger.info("Using modifiedSince Query Parameter: " + queryDateTime);
					}

					for (FileEntry fileEntry : documentList) {
						if (queryDateTime == null || queryDateTime.compareTo(LocalDateTime
								.ofInstant(fileEntry.getModifiedDate().toInstant(), ZoneId.systemDefault())) < 0) {
							JSONObject RPADocument = JSONFactoryUtil.createJSONObject();
							logger.info("fileEntry " + fileEntry);
							DLFileEntry DLfileEntry = DLFileEntryLocalServiceUtil
									.getDLFileEntry(fileEntry.getFileEntryId());
							logger.debug("DLfileEntry " + DLfileEntry);
							String processArea = DLFileEntryTypeLocalServiceUtil
									.getFileEntryType(DLfileEntry.getFileEntryTypeId())
									.getName(LocaleUtil.getDefault());

							String[] dataItemTypeCFObject = (String[]) fileEntry.getExpandoBridge()
									.getAttribute("Data Item Type");
							String dataItemTypeCF = StringPool.BLANK;
							if (dataItemTypeCFObject.length > 0) {
								dataItemTypeCF = dataItemTypeCFObject[0];
							}
							String performacePeriodCF = String
									.valueOf(fileEntry.getExpandoBridge().getAttribute("PerformancePeriod"));

							logger.info("dataItemTypeCF " + dataItemTypeCF);
							logger.info("performacePeriodCF " + performacePeriodCF);

							RPADocument.put("createDate", fileEntry.getCreateDate());
							RPADocument.put("description", fileEntry.getDescription());
							RPADocument.put("extension", fileEntry.getExtension());
							RPADocument.put("fileEntryId", fileEntry.getFileEntryId());
							RPADocument.put("fileName", fileEntry.getFileName());
							RPADocument.put("folderId", fileEntry.getFolderId());
							RPADocument.put("groupId", fileEntry.getGroupId());
							RPADocument.put("mimeType", fileEntry.getMimeType());
							RPADocument.put("modifiedDate", fileEntry.getModifiedDate());
							RPADocument.put("repositoryId", fileEntry.getRepositoryId());
							RPADocument.put("title", fileEntry.getTitle());
							RPADocument.put("version", fileEntry.getVersion());
							RPADocument.put("userName", fileEntry.getUserName());
							RPADocument.put("Data Item Type", dataItemTypeCF);
							RPADocument.put("PerformancePeriod", performacePeriodCF);
							RPADocument.put("Process Area", processArea);
							jsonResultArray.put(RPADocument);
						}
					}

					jsonResponse.put("documentList", jsonResultArray);

				} catch (PortalException e) {
					logger.error("Error getting RPA Document list", e);
					statusCode = "400";
				}

			} else {
				logger.info("User is not able to see documents");
				statusCode = "401";
				jsonResponse.put("message", "Unauthorised. Missing or invalid authentication key");
			}
		} catch (PortalException e1) {
			logger.error("Error getting default user", e1);
		}

		jsonResponse.put("statusCode", statusCode);
		return jsonResponse;
	}

	@JSONWebService(value = "update_CPImpacts", method = "POST")
	public void updateCPImpacts(long resourcePK, String impactList) {
		logger.info("Getting recFormArticle with resourcePrimKey " + resourcePK);
		try {
			long formId = recFormArticleLocalServiceUtil.getFormIdByArticleId(resourcePK);
			recFormArticle cp = recFormArticleLocalServiceUtil.getrecFormArticle(formId);
			logger.info("recFormArticle getted " + cp);
			cp.setArticleImpacts(impactList);
			recFormArticleLocalServiceUtil.updaterecFormArticle(cp);
			logger.info("recFormArticle updated properly with impacts " + impactList);
		} catch (PortalException e) {
			logger.error("ERROR - Updating recFormArticle", e);
		}
	}

	@AccessControlled(guestAccessEnabled = true)
	@JSONWebService(value = "getCalendarBookingsREC", method = "GET")
	public List<CalendarBooking> getCalendarBookingsREC(long calendarId, long startTime, long endTime) {
		return CalendarBookingLocalServiceUtil.getCalendarBookings(calendarId, startTime, endTime, QueryUtil.ALL_POS);
	}

	@AccessControlled(guestAccessEnabled = true)
	@JSONWebService(value = "get_rec_calendar_events", method = "GET")
	public List<CalendarBooking> get_rec_calendar_events(long companyId, long userId, long startTime, long endTime,
			boolean isAllCommittees) {
		return CommitteeUtil.get_rec_calendar_events(companyId, userId, startTime, endTime, isAllCommittees);
	}

	@AccessControlled(guestAccessEnabled = true)
	@JSONWebService(value = "get_rec_calendar_events_by_committee", method = "GET")
	public List<CalendarBooking> get_rec_calendar_events_by_committees(long companyId, long userId, long startTime,
			long endTime, long groupId) {
		return CommitteeUtil.get_rec_calendar_events_by_committee(companyId, userId, startTime, endTime, groupId);
	}

	@JSONWebService(value = "new_fields_cp_form", method = "POST")
	public Map<String, String> newFieldsCPForm(long forminstancerecordversionId) {
		return CProposalGetFormInfoUtil.getProposalManagementDetailsFromForm(forminstancerecordversionId);
	}

	@JSONWebService(value = "upload_your_files_RPA", method = "POST")
	public JSONObject uploadYourFiles(long userId, long repositoryId, long folderId, byte[] fileBytes, String mimeType,
			String title, String processArea, String description, String fieldType, String date, String partyUploading,
			ServiceContext serviceContext) {

		JSONObject response = JSONFactoryUtil.createJSONObject();
		response.put("code", 401);
		response.put("message", "Unauthorised. Missing or invalid authentication key");

		if (!serviceContext.isSignedIn()) {
			logger.info("User is not able to upoload documents upload_your_files_RPA");
			return response;
		}

		boolean hasAccessOrgRole = false;
		boolean hasAccessRPA = false;

		try {

			List<UserGroupRole> userGroupRoles = UserGroupRoleLocalServiceUtil.getUserGroupRoles(userId, repositoryId);
			for (UserGroupRole userGroupRole : userGroupRoles) {
				if (userGroupRole.getRole().getName().equals("REC Contract Managers")
						|| userGroupRole.getRole().getName().equals("Master Administrative User")
						|| userGroupRole.getRole().getName().equals("REC_Performance_Assurance")) {
					hasAccessOrgRole = true;
				}
			}

			if (RoleLocalServiceUtil.hasUserRole(userId, CompanyThreadLocal.getCompanyId(), "Administrator", false)
					|| RoleLocalServiceUtil.hasUserRole(userId, CompanyThreadLocal.getCompanyId(), "RPA", false)
					|| RoleLocalServiceUtil.hasUserRole(userId, CompanyThreadLocal.getCompanyId(), "Portal_Admin",
							false)) {
				hasAccessRPA = true;
			}

		} catch (PortalException e) {
			logger.debug("Error check role upload_your_files_RPA");
		}

		if (!hasAccessOrgRole && !hasAccessRPA) {
			logger.info("User has not permissions upload_your_files_RPA");
			return response;
		}

		return RPAUtil.uploadYourFiles(userId, repositoryId, folderId, fileBytes, mimeType, title, processArea,
				description, fieldType, date, partyUploading, serviceContext);
	}

	@AccessControlled(guestAccessEnabled = true)
	@JSONWebService(value = "upload_RPA_Document", method = "POST")
	public JSONObject uploadRPADocument(long repositoryId, long folderId, File file, String fileName,
			String processArea, String description, String fieldType, String date, String partyUploading,
			String dataItemType, String performancePeriod, ServiceContext serviceContext) {

		JSONObject response = JSONFactoryUtil.createJSONObject();
		// Get user from serviceContext
		logger.info("User: " + serviceContext.getUserId());
		long serviceUserId = serviceContext.getUserId();
		try {
			User defaultUser = UserLocalServiceUtil.getUserByEmailAddress(serviceContext.getCompanyId(),
					"default@liferay.com");
			if (serviceUserId != defaultUser.getUserId() && serviceUserId != 0) {
				logger.info("User is able to see documents");
				response = RPAUtil.uploadRPADocument(repositoryId, folderId, file,
						new MimetypesFileTypeMap().getContentType(file), fileName, processArea, description, fieldType,
						date, partyUploading, dataItemType, performancePeriod, serviceContext);
			} else {
				logger.info("User is not able to see documents");
				response.put("code", 401);
				response.put("message", "Unauthorised. Missing or invalid authentication key");
			}
		} catch (PortalException e1) {
			logger.error("Error getting default user", e1);
		}

		return response;

	}

	@AccessControlled(guestAccessEnabled = true)
	@JSONWebService(value = "upload_RPA_Document_by_bytes", method = "POST")
	public JSONObject uploadRPADocumentByBytes(long repositoryId, long folderId, byte[] fileBytes, String mimeType,
			String fileName, String processArea, String description, String fieldType, String date,
			String partyUploading, String dataItemType, String performancePeriod, ServiceContext serviceContext) {

		JSONObject response = JSONFactoryUtil.createJSONObject();
		// Get user from serviceContext
		logger.info("User: " + serviceContext.getUserId());
		long serviceUserId = serviceContext.getUserId();
		try {
			User defaultUser = UserLocalServiceUtil.getUserByEmailAddress(serviceContext.getCompanyId(),
					"default@liferay.com");
			if (serviceUserId != defaultUser.getUserId() && serviceUserId != 0) {
				logger.info("User is able to see documents");
				response = RPAUtil.uploadRPADocumentByBytes(repositoryId, folderId, fileBytes, mimeType, fileName,
						processArea, description, fieldType, date, partyUploading, dataItemType, performancePeriod);
			} else {
				logger.info("User is not able to see documents");
				response.put("code", 401);
				response.put("message", "Unauthorised. Missing or invalid authentication key");
			}
		} catch (PortalException e1) {
			logger.error("Error getting default user", e1);
		}

		return response;

	}

	@JSONWebService(value = "upload_FILE_IA", method = "POST")
    public JSONObject uploadYourFilesFileEntryForIA(String title, long repositoryId, long folderId, long userId, byte[] fileBytes,
			 String mimeType, String description, String date,
                                                    String[] TargetedAudience, ServiceContext serviceContext) {

		JSONObject response = JSONFactoryUtil.createJSONObject();
		try {
			response = ImpactAssesmentUtil.uploadYourFilesFileEntryForIA(title, repositoryId, folderId, userId, fileBytes, mimeType, description, date,
					TargetedAudience, serviceContext);
			response.put("code", 200);
            response.put("message", "File has been uploaded");
		} catch (PortalException e1) {
			logger.error("Error getting default user", e1);
			response.put("code", 500);
            response.put("message", "File has not been uploaded");
		}
		return response;
	}

	@JSONWebService(value = "create_IA", method = "POST")
    public JSONObject createIA(String Title, String IA_CPID,
                               String IA_Type,
                               String IA_TargetedAudience,
                               String IA_ChooseQATemplate,
                               String IA_LinkToCPPage,
			 String IA_Documents,
			 String IA_ResponsesDeadline, ServiceContext serviceContext) {

		Map<String, String> parameters = new HashMap<String, String>();
		JSONObject response = JSONFactoryUtil.createJSONObject();
		parameters.put("IA_Title", Title);

		parameters.put("IA_CPID", IA_CPID);
		parameters.put("IA_Type", IA_Type);
		parameters.put("IA_TargetedAudience", IA_TargetedAudience);
		parameters.put("IA_ChooseQATemplate", IA_ChooseQATemplate);
		parameters.put("IA_LinkToCPPage", IA_LinkToCPPage);
		parameters.put("IA_Documents", IA_Documents);
		parameters.put("IA_ResponsesDeadline", IA_ResponsesDeadline);

		try {
			ImpactAssesmentUtil.createIA(parameters, serviceContext.getScopeGroupId(), serviceContext.getUserId(), Title);
			response.put("code", 200);
			response.put("message", "Impact Assessment created ");
		} catch (Exception e) {
			logger.error("---ERROR in Impact Assessment creation---", e);
			response.put("code", 500);
			response.put("message", "ERROR in Impact Assessment creation");
		}
        logger.info("JSON FILE" + response);

		return response;
    }

	@JSONWebService(value = "update_IA", method = "POST")
    public JSONObject updateIA(long appResourcePK, String Title, String IA_CPID, String IA_Type, String IA_TargetedAudience,
                               String IA_ChooseQATemplate,
                               String IA_LinkToCPPage,
			 String IA_Documents,
			 String IA_ResponsesDeadline, ServiceContext serviceContext) {

		Map<String, String> parameters = new HashMap<String, String>();
		JSONObject response = JSONFactoryUtil.createJSONObject();
		parameters.put("IA_Title", Title);

		parameters.put("IA_CPID", IA_CPID);
		parameters.put("IA_Type", IA_Type);
		parameters.put("IA_TargetedAudience", IA_TargetedAudience);
		parameters.put("IA_ChooseQATemplate", IA_ChooseQATemplate);
		parameters.put("IA_LinkToCPPage", IA_LinkToCPPage);
		parameters.put("IA_Documents", IA_Documents);
		parameters.put("IA_ResponsesDeadline", IA_ResponsesDeadline);

		try {
			ImpactAssesmentUtil.updateIA(parameters, appResourcePK);
			response.put("code", 200);
			response.put("message", "Impact Assessment updated ");
		} catch (Exception e) {
			logger.error("---ERROR in Impact Assessment creation---", e);
			response.put("code", 500);
			response.put("message", "ERROR in application creation");
		}
        logger.info("JSON FILE" + response);

		return response;
    }

	@JSONWebService(value = "upload_FILE_CONSULTATION", method = "POST")
    public JSONObject uploadYourFilesFileEntryForConsultation(String title, long repositoryId, long folderId, long userId, byte[] fileBytes,
			 String mimeType, String description, String date,
                                                              String[] TargetedAudience, ServiceContext serviceContext) throws PortalException {
		JSONObject response = JSONFactoryUtil.createJSONObject();
		// Get user from serviceContext

		try {
			response = ConsultationUtil.uploadYourFilesFileEntryForConsultation(title, repositoryId, folderId, userId, fileBytes, mimeType, description, date,
					TargetedAudience, serviceContext);
			response.put("code", 200);
            response.put("message", "file has been uploaded");
		} catch (PortalException e1) {
			logger.error("Error getting default user", e1);
			response.put("code", 500);
            response.put("message", "file has not been uploaded");
		}

		return response;

	}

	@JSONWebService(value = "create_CONSULTATION", method = "POST")
    public JSONObject createConsultation(String Title, String TextIDChangeProposal,
                                         String Consultation_TargetedAudience,
                                         String Consultation_ChooseQATemplate,
                                         String TextLinkChangeProposalID,
                                         String TextDocumentConsultation,
			 String TextDate, ServiceContext serviceContext) {

		Map<String, String> parameters = new HashMap<String, String>();
		JSONObject response = JSONFactoryUtil.createJSONObject();
		parameters.put("ConsultationTitle", Title);

		parameters.put("TextIDChangeProposal", TextIDChangeProposal);
		parameters.put("Consultation_TargetedAudience", Consultation_TargetedAudience);
		parameters.put("Consultation_ChooseQATemplate", Consultation_ChooseQATemplate);
		parameters.put("TextLinkChangeProposalID", TextLinkChangeProposalID);
		parameters.put("TextDocumentConsultation", TextDocumentConsultation);
		parameters.put("TextDate", TextDate);


		try {
			ConsultationUtil.createConsultation(parameters, serviceContext.getScopeGroupId(), serviceContext.getUserId(), Title);

			response.put("code", 200);
			response.put("message", "Consultation created ");
		} catch (Exception e) {
			logger.error("---ERROR in Consultation creation---", e);
			response.put("code", 500);
			response.put("message", "ERROR in Consultation creation");
		}
        logger.info("JSON FILE" + response);

		return response;

	}

	@JSONWebService(value = "Update_CONSULTATION", method = "POST")
    public JSONObject UpdateConsultation(long appPK, String Title, String TextIDChangeProposal,
                                         String Consultation_TargetedAudience,
                                         String Consultation_ChooseQATemplate,
                                         String TextLinkChangeProposalID,
                                         String TextDocumentConsultation,
			 String TextDate, ServiceContext serviceContext) {

		Map<String, String> parameters = new HashMap<String, String>();
		JSONObject response = JSONFactoryUtil.createJSONObject();
		parameters.put("ConsultationTitle", Title);

		parameters.put("TextIDChangeProposal", TextIDChangeProposal);
		parameters.put("Consultation_TargetedAudience", Consultation_TargetedAudience);
		parameters.put("Consultation_ChooseQATemplate", Consultation_ChooseQATemplate);
		parameters.put("TextLinkChangeProposalID", TextLinkChangeProposalID);
		parameters.put("TextDocumentConsultation", TextDocumentConsultation);
		parameters.put("TextDate", TextDate);


		try {
			ConsultationUtil.UpdateConsultation(parameters, appPK);
			response.put("code", 200);
			response.put("message", "Consultation updated ");
		} catch (Exception e) {
			logger.error("---ERROR in Consultation update---", e);
			response.put("code", 500);
			response.put("message", "ERROR in Consultation update");
		}
        logger.info("JSON FILE" + response);

		return response;

	}

	/**
	 * @param title
	 * @param PM_CompanyName
	 * @param IA_CompanyRegistrationNumber
	 * @param PM_RegisteredAddress
	 * @param PM_ReasonForSubmission
	 * @param PM_AuthorisingOfficerFullName
	 * @param PM_AuthorisingOfficer1Role
	 * @param PM_AuthorisingOfficer1EmailAddress
	 * @param PM_AuthorisingOfficer1BusinessAddress
	 * @param PM_AuthorisingOfficer1TelephoneNumber
	 * @param PM_AnyadditionalAuthorisingOfficer
	 * @param PM_KeyContact1Fullname
	 * @param PM_KeyContact1Role
	 * @param PM_KeyContact1EmailAddress
	 * @param PM_KeyContact1BusinessAddress
	 * @param PM_KeyContact1TelephoneNumber
	 * @param PM_Anyadditionalkeycontacts
	 * @param PM_Whatroleareyouapplyingfor
	 * @param PM_Whatsystemsareyouapplyingtoaccess
	 * @param PM_ProvideDetailsOrganisation
	 * @param PM_Status
	 * @param serviceContext
	 */
	@JSONWebService(value = "create_application", method = "POST")
	public JSONObject createApplication(String title, String PM_CompanyName, String IA_CompanyRegistrationNumber,
			String PM_RegisteredAddress, String PM_ReasonForSubmission, String PM_AuthorisingOfficerFullName,
			String PM_AuthorisingOfficer1Role, String PM_AuthorisingOfficer1EmailAddress,
			String PM_AuthorisingOfficer1BusinessAddress, String PM_AuthorisingOfficer1TelephoneNumber,
			String PM_AnyadditionalAuthorisingOfficer, String PM_KeyContact1Fullname, String PM_KeyContact1Role,
			String PM_KeyContact1EmailAddress, String PM_KeyContact1BusinessAddress,
			String PM_KeyContact1TelephoneNumber, String PM_Anyadditionalkeycontacts,
			String PM_Whatroleareyouapplyingfor, String PM_Whatsystemsareyouapplyingtoaccess,
			String PM_ProvideDetailsOrganisation, String PM_Status, ServiceContext serviceContext) {

		Map<String, String> parameters = new HashMap<String, String>();
		JSONObject response = JSONFactoryUtil.createJSONObject();

		parameters.put("PM_CompanyName", PM_CompanyName);
		parameters.put("IA_CompanyRegistrationNumber", IA_CompanyRegistrationNumber);
		parameters.put("PM_RegisteredAddress", PM_RegisteredAddress);
		parameters.put("PM_ReasonForSubmission", PM_ReasonForSubmission);
		parameters.put("PM_AuthorisingOfficerFullName", PM_AuthorisingOfficerFullName);
		parameters.put("PM_AuthorisingOfficer1Role", PM_AuthorisingOfficer1Role);
		parameters.put("PM_AuthorisingOfficer1EmailAddress", PM_AuthorisingOfficer1EmailAddress);
		parameters.put("PM_AuthorisingOfficer1BusinessAddress", PM_AuthorisingOfficer1BusinessAddress);
		parameters.put("PM_AuthorisingOfficer1TelephoneNumber", PM_AuthorisingOfficer1TelephoneNumber);
		parameters.put("PM_AnyadditionalAuthorisingOfficer", PM_AnyadditionalAuthorisingOfficer);
		parameters.put("PM_KeyContact1Fullname", PM_KeyContact1Fullname);
		parameters.put("PM_KeyContact1Role", PM_KeyContact1Role);
		parameters.put("PM_KeyContact1EmailAddress", PM_KeyContact1EmailAddress);
		parameters.put("PM_KeyContact1BusinessAddress", PM_KeyContact1BusinessAddress);
		parameters.put("PM_KeyContact1TelephoneNumber", PM_KeyContact1TelephoneNumber);
		parameters.put("PM_Anyadditionalkeycontacts", PM_Anyadditionalkeycontacts);
		parameters.put("PM_Whatroleareyouapplyingfor", PM_Whatroleareyouapplyingfor);
		parameters.put("PM_Whatsystemsareyouapplyingtoaccess", PM_Whatsystemsareyouapplyingtoaccess);
		parameters.put("PM_ProvideDetailsOrganisation", PM_ProvideDetailsOrganisation);
		parameters.put("PM_Status", PM_Status);

		try {
			ApplicationUtil.createApplication(parameters, serviceContext.getScopeGroupId(), serviceContext.getUserId(),
					title);
			response.put("code", 200);
			response.put("message", "Application created properly");
		} catch (Exception e) {
			logger.error("---ERROR in application creation---", e);
			response.put("code", 500);
			response.put("message", "ERROR in application creation");
		}

		return response;

	}

	/**
	 * @param appResourcePK
	 * @param PM_CompanyName
	 * @param IA_CompanyRegistrationNumber
	 * @param PM_RegisteredAddress
	 * @param PM_ReasonForSubmission
	 * @param PM_AuthorisingOfficerFullName
	 * @param PM_AuthorisingOfficer1Role
	 * @param PM_AuthorisingOfficer1EmailAddress
	 * @param PM_AuthorisingOfficer1BusinessAddress
	 * @param PM_AuthorisingOfficer1TelephoneNumber
	 * @param PM_AnyadditionalAuthorisingOfficer
	 * @param PM_KeyContact1Fullname
	 * @param PM_KeyContact1Role
	 * @param PM_KeyContact1EmailAddress
	 * @param PM_KeyContact1BusinessAddress
	 * @param PM_KeyContact1TelephoneNumber
	 * @param PM_Anyadditionalkeycontacts
	 * @param PM_Whatroleareyouapplyingfor
	 * @param PM_Whatsystemsareyouapplyingtoaccess
	 * @param PM_ProvideDetailsOrganisation
	 * @param PM_Status
	 * @param serviceContext
	 */
	@JSONWebService(value = "update_application", method = "POST")
	public JSONObject updateApplication(long appResourcePK, String PM_CompanyName, String IA_CompanyRegistrationNumber,
			String PM_RegisteredAddress, String PM_ReasonForSubmission, String PM_AuthorisingOfficerFullName,
			String PM_AuthorisingOfficer1Role, String PM_AuthorisingOfficer1EmailAddress,
			String PM_AuthorisingOfficer1BusinessAddress, String PM_AuthorisingOfficer1TelephoneNumber,
			String PM_AnyadditionalAuthorisingOfficer, String PM_KeyContact1Fullname, String PM_KeyContact1Role,
			String PM_KeyContact1EmailAddress, String PM_KeyContact1BusinessAddress,
			String PM_KeyContact1TelephoneNumber, String PM_Anyadditionalkeycontacts,
			String PM_Whatroleareyouapplyingfor, String PM_Whatsystemsareyouapplyingtoaccess,
			String PM_ProvideDetailsOrganisation, String PM_Status, ServiceContext serviceContext) {

		Map<String, String> parameters = new HashMap<String, String>();
		JSONObject response = JSONFactoryUtil.createJSONObject();

		parameters.put("PM_CompanyName", PM_CompanyName);
		parameters.put("IA_CompanyRegistrationNumber", IA_CompanyRegistrationNumber);
		parameters.put("PM_RegisteredAddress", PM_RegisteredAddress);
		parameters.put("PM_ReasonForSubmission", PM_ReasonForSubmission);
		parameters.put("PM_AuthorisingOfficerFullName", PM_AuthorisingOfficerFullName);
		parameters.put("PM_AuthorisingOfficer1Role", PM_AuthorisingOfficer1Role);
		parameters.put("PM_AuthorisingOfficer1EmailAddress", PM_AuthorisingOfficer1EmailAddress);
		parameters.put("PM_AuthorisingOfficer1BusinessAddress", PM_AuthorisingOfficer1BusinessAddress);
		parameters.put("PM_AuthorisingOfficer1TelephoneNumber", PM_AuthorisingOfficer1TelephoneNumber);
		parameters.put("PM_AnyadditionalAuthorisingOfficer", PM_AnyadditionalAuthorisingOfficer);
		parameters.put("PM_KeyContact1Fullname", PM_KeyContact1Fullname);
		parameters.put("PM_KeyContact1Role", PM_KeyContact1Role);
		parameters.put("PM_KeyContact1EmailAddress", PM_KeyContact1EmailAddress);
		parameters.put("PM_KeyContact1BusinessAddress", PM_KeyContact1BusinessAddress);
		parameters.put("PM_KeyContact1TelephoneNumber", PM_KeyContact1TelephoneNumber);
		parameters.put("PM_Anyadditionalkeycontacts", PM_Anyadditionalkeycontacts);
		parameters.put("PM_Whatroleareyouapplyingfor", PM_Whatroleareyouapplyingfor);
		parameters.put("PM_Whatsystemsareyouapplyingtoaccess", PM_Whatsystemsareyouapplyingtoaccess);
		parameters.put("PM_ProvideDetailsOrganisation", PM_ProvideDetailsOrganisation);
		parameters.put("PM_Status", PM_Status);

		try {
			ApplicationUtil.updateApplication(parameters, appResourcePK);
			response.put("code", 200);
			response.put("message", "Application created properly");
		} catch (Exception e) {
			logger.error("---ERROR in application creation---", e);
			response.put("code", 500);
			response.put("message", "ERROR in application creation");
		}

		return response;

	}

	@JSONWebService(value = "create_committee_action_log", method = "POST")
	public JSONObject createCommitteeActionLog(String title, String AL_ID, String AL_Description, String AL_Assignee,
			String AL_DueDate, String AL_NextUpdateDue, String AL_Status, String AL_CompletionDate, long groupId,
			ServiceContext serviceContext) {

		Map<String, String> parameters = new HashMap<String, String>();
		JSONObject response = JSONFactoryUtil.createJSONObject();

		parameters.put("AL_ID", AL_ID);
		parameters.put("AL_Description", AL_Description);
		parameters.put("AL_Assignee", AL_Assignee);
		parameters.put("AL_DueDate", AL_DueDate);
		parameters.put("AL_NextUpdateDue", AL_NextUpdateDue);
		parameters.put("AL_Status", AL_Status);
		parameters.put("AL_CompletionDate", AL_CompletionDate);

		try {
			CommitteeActionLogUtil.createCommitteeActionLog(parameters, groupId, serviceContext.getUserId(), title);
			response.put("code", 200);
			response.put("message", "Application created properly");
		} catch (Exception e) {
			logger.error("---ERROR in application creation---", e);
			response.put("code", 500);
			response.put("message", "ERROR in application creation");
		}

		return response;

	}

	@JSONWebService(value = "update_committee_action_log", method = "POST")
	public JSONObject updateCommitteeActionLog(long actionLogResourcePK, String title, String AL_ID,
			String AL_Description, String AL_Assignee, String AL_DueDate, String AL_NextUpdateDue, String AL_Status,
			String AL_CompletionDate) {

		Map<String, String> parameters = new HashMap<String, String>();
		JSONObject response = JSONFactoryUtil.createJSONObject();

		parameters.put("AL_ID", AL_ID);
		parameters.put("AL_Description", AL_Description);
		parameters.put("AL_Assignee", AL_Assignee);
		parameters.put("AL_DueDate", AL_DueDate);
		parameters.put("AL_NextUpdateDue", AL_NextUpdateDue);
		parameters.put("AL_Status", AL_Status);
		parameters.put("AL_CompletionDate", AL_CompletionDate);

		try {
			CommitteeActionLogUtil.updateCommitteeActionLog(parameters, actionLogResourcePK);
			response.put("code", 200);
			response.put("message", "Application updated properly");
		} catch (Exception e) {
			logger.error("---ERROR in application update ---", e);
			response.put("code", 500);
			response.put("message", "ERROR in application update");
		}

		return response;

	}

	@JSONWebService(value = "create_committee_voting_process", method = "POST")
	public JSONObject createCommitteeVotingProcess(String title, String Vote_Description, String Vote_ClossingProcess,
			String Vote_Status, String Vote_ReportVisibility, String Vote_VotingVisibility, String Vote_DecidingVote,
			long groupId, ServiceContext serviceContext) {

		Map<String, String> parameters = new HashMap<String, String>();
		JSONObject response = JSONFactoryUtil.createJSONObject();

		parameters.put("Vote_TitleVoting", title);
		parameters.put("Vote_Description", Vote_Description);
		parameters.put("Vote_ClossingProcess", Vote_ClossingProcess);
		parameters.put("Vote_Status", Vote_Status);
		parameters.put("Vote_ReportVisibility", Vote_ReportVisibility);
		parameters.put("Vote_VotingVisibility", Vote_VotingVisibility);
		parameters.put("Vote_DecidingVote", Vote_DecidingVote);

		try {
			CommitteeVotingProcessUtil.createCommitteeVotingProcess(parameters, groupId, serviceContext.getUserId(),
					title);
			response.put("code", 200);
			response.put("message", "Voting Process created properly");
		} catch (Exception e) {
			logger.error("---ERROR in voting creation---", e);
			response.put("code", 500);
			response.put("message", "ERROR in voting creation");
		}

		return response;

	}

	@JSONWebService(value = "update_committee_voting_process", method = "POST")
	public JSONObject updateCommitteeVotingProcess(long votingResourcePK, String title, String Vote_Description,
			String Vote_ClossingProcess, String Vote_Status, String Vote_ReportVisibility, String Vote_VotingVisibility,
			String Vote_DecidingVote, String[] Vote_ExternalVote) {

		Map<String, String> parameters = new HashMap<String, String>();
		JSONObject response = JSONFactoryUtil.createJSONObject();

		parameters.put("Vote_TitleVoting", title);
		parameters.put("Vote_Description", Vote_Description);
		parameters.put("Vote_ClossingProcess", Vote_ClossingProcess);
		parameters.put("Vote_Status", Vote_Status);
		parameters.put("Vote_ReportVisibility", Vote_ReportVisibility);
		parameters.put("Vote_VotingVisibility", Vote_VotingVisibility);
		parameters.put("Vote_DecidingVote", Vote_DecidingVote);

		try {
			CommitteeVotingProcessUtil.updateCommitteeVotingProcess(parameters, votingResourcePK, Vote_ExternalVote);
			response.put("code", 200);
			response.put("message", "Voting process updated properly");
		} catch (Exception e) {
			logger.error("---ERROR in voting update ---", e);
			response.put("code", 500);
			response.put("message", "ERROR in voting update");
		}

		return response;

	}

	@JSONWebService(value = "create_committee_nominations_process", method = "POST")
	public JSONObject createCommitteeNominations(String TitleNomination, String DescriptionNomination,
			String ClosingDateNomination, String StatusNomination, String ReportVisibilityNomination,
			String DecidingVoteNomination, long groupId, ServiceContext serviceContext) {

		Map<String, String> parameters = new HashMap<String, String>();
		JSONObject response = JSONFactoryUtil.createJSONObject();

		parameters.put("AL_Nominations_TitleNomination", TitleNomination);
		parameters.put("AL_Nominations_DescriptionNomination", DescriptionNomination);
		parameters.put("AL_Nominations_ClosingDateNomination", ClosingDateNomination);
		parameters.put("AL_Nominations_StatusNomination", StatusNomination);
		parameters.put("AL_Nominations_ReportVisibilityNomination", ReportVisibilityNomination);
		parameters.put("AL_Nominations_DecidingVoteNomination", DecidingVoteNomination);

		try {
			CommitteeNominationsUtil.createCommitteeNominations(parameters, groupId, serviceContext.getUserId(),
					TitleNomination);
			response.put("code", 200);
			response.put("message", "Nomination Process created properly");
		} catch (Exception e) {
			logger.error("---ERROR in nomination creation---", e);
			response.put("code", 500);
			response.put("message", "ERROR in nomination creation");
		}

		return response;

	}

	@JSONWebService(value = "update_committee_nominations_process", method = "POST")
	public JSONObject updateCommitteeNominations(long nominationsResourcePK, String TitleNomination,
			String DescriptionNomination, String ClosingDateNomination, String StatusNomination,
			String ReportVisibilityNomination, String DecidingVoteNomination) {

		Map<String, String> parameters = new HashMap<String, String>();
		JSONObject response = JSONFactoryUtil.createJSONObject();

		parameters.put("AL_Nominations_TitleNomination", TitleNomination);
		parameters.put("AL_Nominations_DescriptionNomination", DescriptionNomination);
		parameters.put("AL_Nominations_ClosingDateNomination", ClosingDateNomination);
		parameters.put("AL_Nominations_StatusNomination", StatusNomination);
		parameters.put("AL_Nominations_ReportVisibilityNomination", ReportVisibilityNomination);
		parameters.put("AL_Nominations_DecidingVoteNomination", DecidingVoteNomination);

		try {
			CommitteeNominationsUtil.updateCommitteeNominations(parameters, nominationsResourcePK);
			response.put("code", 200);
			response.put("message", "Nomination process updated properly");
		} catch (Exception e) {
			logger.error("---ERROR in nomination update ---", e);
			response.put("code", 500);
			response.put("message", "ERROR in nomination update");
		}

		return response;

	}


}
