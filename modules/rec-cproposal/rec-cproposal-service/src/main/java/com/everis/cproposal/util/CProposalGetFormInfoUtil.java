package com.everis.cproposal.util;

import com.everis.cproposal.service.recFormArticleLocalServiceUtil;
import com.liferay.dynamic.data.mapping.model.DDMContent;
import com.liferay.dynamic.data.mapping.model.DDMFormInstanceRecordVersion;
import com.liferay.dynamic.data.mapping.model.DDMStorageLink;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMContentLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMFormInstanceRecordVersionLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMStorageLinkLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CProposalGetFormInfoUtil {

	private static final String Email_address = "Email Address";

	private static final String Change_Proposal_Title = "Change Proposal Title";

	private static final String Files_to_upload = "Attachments";

	private static final Log logger = LogFactoryUtil.getLog(CProposalGetFormInfoUtil.class);

	public static Map<String, String> getProposalManagementDetailsFromForm(long forminstancerecordversionId) {
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

					case Files_to_upload:
						String cpDocumentAttached = dataFromFormMap.get(nameField);
						proposalMgmDataMap.put("CPDocument", cpDocumentAttached);
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

	private static Map<String, JSONObject> getFormFieldNamesAndLabelByFormId(long forminstancerecordversionId) {
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
			logger.debug("formFieldsArray: " + formFieldsArray.toString());
			JSONObject eachFieldJsonObject = JSONFactoryUtil.createJSONObject();
			for (int i = 0; i < formFieldsArray.length(); i++) {
				boolean hasAdditionalInformation = false;
				JSONObject fieldsMapJson = JSONFactoryUtil.createJSONObject();
				eachFieldJsonObject = (JSONObject) formFieldsArray.get(i);
				logger.debug("eachFieldJsonObject: " + eachFieldJsonObject.toString());
				JSONObject labelJsonObject = eachFieldJsonObject.getJSONObject("label");
				logger.debug("labelJsonObject: " + labelJsonObject);
				String fieldLabel = labelJsonObject.getString("en_GB");
				String nameField = eachFieldJsonObject.getString("name");
				fieldsMapJson.put("name", fieldLabel);
				if (eachFieldJsonObject.get("options") != null) {
					JSONArray optionsJSONArray = JSONFactoryUtil.createJSONArray();
					optionsJSONArray = (JSONArray) eachFieldJsonObject.get("options");
					if (optionsJSONArray.length() > 0) {
						hasOptions = true;
						fieldsMapJson.put("hasOptions", hasOptions);
						logger.debug("optionsJSONArray: " + optionsJSONArray);
						JSONArray optionsMapArray = JSONFactoryUtil.createJSONArray();
						for (int j = 0; j < optionsJSONArray.length(); j++) {
							JSONObject eachOptionJsonObject = (JSONObject) optionsJSONArray.get(j);
							JSONObject optionMapObject = JSONFactoryUtil.createJSONObject();
							String optionNameValue = eachOptionJsonObject.getString("value");
							JSONObject labelOptionJsonObject = eachOptionJsonObject.getJSONObject("label");
							String optionLabel = labelOptionJsonObject.getString("en_GB");
							optionMapObject.put(optionNameValue, optionLabel);
							optionsMapArray.put(optionMapObject);
						}
						fieldsMapJson.put("options", optionsMapArray);
					}
				}
				if (CProposalGetFormInfoUtil.hasAdditionalInfo(fieldLabel)) {
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
			logger.error("Could not get ddmFormInstanceRecordVersion for this forminstancerecordversionId: "
					+ forminstancerecordversionId);
			logger.debug(e.getStackTrace());
		}
		return fieldNameLabelmap;
	}

	public static Map<String, JSONObject> getCPDetails(String resourcePrimKey) {

		Map<String, JSONObject> cpDetailsMap = new HashMap<String, JSONObject>();
		boolean hasAdditionalInformation = false;
		long formId = recFormArticleLocalServiceUtil.getFormIdByArticleId(Long.parseLong(resourcePrimKey));
		Map<String, JSONObject> formFieldNames = getFormFieldNamesAndLabelByFormId(formId);
		Map<String, String> formData = getCPFormDataMap(formId);
		Iterator it = formFieldNames.entrySet().iterator();

		while (it.hasNext()) {
			Map.Entry eachFormFieldName = (Map.Entry) it.next();
			String formFieldKey = (String) eachFormFieldName.getKey();
			JSONObject formFieldJSONObject = (JSONObject) eachFormFieldName.getValue();
			String fieldLabel = formFieldJSONObject.getString("name");
			String value = formData.get(formFieldKey);// phone
			logger.debug("value " + value);
			JSONObject eachJsonDataToSave = JSONFactoryUtil.createJSONObject();

			if (Validator.isNotNull(formFieldJSONObject.getString("hasOptions"))
					&& "true".equalsIgnoreCase(formFieldJSONObject.getString("hasOptions"))) {
				String[] optionArrayValues = value.split(",");
				logger.debug("optionArrayValues.length: " + optionArrayValues.length);

				//String optionValueList = "";
				StringBuilder optionValueList = new StringBuilder();

				for (String eachOptionValue : optionArrayValues) {

					eachOptionValue = eachOptionValue.replaceAll("[\\[\\]\"]", "");
					JSONArray optionsArray = (JSONArray) formFieldJSONObject.get("options");

					for (int i = 0; i < optionsArray.length(); i++) {
						JSONObject eachOption = optionsArray.getJSONObject(i);
						logger.debug("eachOption..." + eachOption);
						if (Validator.isNotNull(eachOption.get(eachOptionValue))) {
							logger.debug("optionValueList String builder length: "+optionValueList.length());
							if (optionValueList.length() > 0) {
								optionValueList.append(", ");
							}
							optionValueList.append((String) eachOption.get(eachOptionValue));
							logger.debug("optionValueList: " + optionValueList.toString());
							break;
						}
					}

					value = optionValueList.toString();
				}
			}

			if (Validator.isNotNull(formFieldJSONObject.getString("hasAdditionalInformation"))
					&& "true".equalsIgnoreCase(formFieldJSONObject.getString("hasAdditionalInformation"))) {
				String nameAdditionalInfo = (String) formFieldJSONObject.get("nameAdditionalInfo");
				String infoValue = formData.get(nameAdditionalInfo);
				logger.debug("infoValue " + infoValue);
				eachJsonDataToSave.put("additionalInfo", infoValue);
				hasAdditionalInformation = true;
			}
			eachJsonDataToSave.put("value", value);
			eachJsonDataToSave.put("hasAdditionalInformation", hasAdditionalInformation);
			cpDetailsMap.put(fieldLabel, eachJsonDataToSave);
			hasAdditionalInformation = false;
			it.remove();
		}

		logger.debug("cpDetailsMap: " + cpDetailsMap);
		return cpDetailsMap;
	}

	public static Map<String, String> getCPFormDataMap(long forminstancerecordversionId) {
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
			String name = StringPool.BLANK, value = StringPool.BLANK;
			for (int i = 0; i < dataFieldValuesArray.length(); i++) {
				JSONObject eachDataFieldJson = (JSONObject) dataFieldValuesArray.get(i);
				if (Validator.isNotNull(eachDataFieldJson.getString("nestedFieldValues"))) {
					JSONArray dataFieldValuesNestedArray = eachDataFieldJson.getJSONArray("nestedFieldValues");
					for (int j = 0; j < dataFieldValuesNestedArray.length(); j++) {
						JSONObject eachDataFieldNestedJson = (JSONObject) dataFieldValuesNestedArray.get(j);
						name = eachDataFieldNestedJson.getString("name");
						JSONObject valueObject = (JSONObject) eachDataFieldNestedJson.get("value");
						value = valueObject.getString("en_GB");
						if (Validator.isNotNull(cpDataFormMap.get(name))) {
							String previousValue = cpDataFormMap.get(name);
							value = previousValue.concat(";spt;").concat(value);
						}
						cpDataFormMap.put(name, value);
					}
				} else {
					name = eachDataFieldJson.getString("name");
					try {
						JSONObject valueObject = (JSONObject) eachDataFieldJson.get("value");
						value = valueObject.getString("en_GB");
					} catch (Exception e) {
						logger.info("Getting Proposer Name, Email Address or Telephone Number field value");
						value = (String) eachDataFieldJson.get("value");
					}
					if (Validator.isNotNull(cpDataFormMap.get(name))) {
						String previousValue = cpDataFormMap.get(name);
						value = previousValue.concat(";spt;").concat(value);
					}
					cpDataFormMap.put(name, value);
				}

			}

		} catch (PortalException e) {
			logger.error("There is no ddmFormInstanceRecordVersion with id: " + forminstancerecordversionId);
		}
		return cpDataFormMap;
	}

	public static boolean hasAdditionalInfo(String label) {
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
}
