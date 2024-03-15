package com.everis.messages.service.builder.service.utils;

import com.liferay.dynamic.data.lists.model.DDLRecordVersion;
import com.liferay.dynamic.data.lists.service.DDLRecordVersionLocalServiceUtil;
import com.liferay.dynamic.data.mapping.model.DDMContent;
import com.liferay.dynamic.data.mapping.model.DDMFormInstance;
import com.liferay.dynamic.data.mapping.model.DDMFormInstanceRecordVersion;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMContentLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMFormInstanceLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMFormInstanceRecordVersionLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

public class NotificationUtils {

	private static final Log logger = LogFactoryUtil.getLog(NotificationUtils.class);

	/* field from NOMINATION form */
	private static final String NOMINEE_EMAIL_ADDRESS = "NomineeEmailAddress";

	private static final String NOMINATOR_EMAIL_ADDRESS = "NominatorEmailAddress";

	private static final String NOMINATOR_NAME = "NominatorFullName";

	private static final String NOMINEE_NAME = "NomineeFullName";

	/* field from CMSP ACTION LOG DDL */
	private static final String CMSP_ACTION_OWNER = "cmsp_action_owner";

	/**
	 * Get the email address from nomination form
	 * 
	 * @param classPK from workflow instance
	 */
	public static String getNomineeEmailAddressFromForm(long classPK) {
		String nomineeEmailAddress = StringPool.BLANK;
		try {
			DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion = DDMFormInstanceRecordVersionLocalServiceUtil
					.getFormInstanceRecordVersion(classPK);
			if (Validator.isNotNull(ddmFormInstanceRecordVersion)) {
				long formInstanceId = ddmFormInstanceRecordVersion.getFormInstanceId();
				long storageId = ddmFormInstanceRecordVersion.getStorageId();
				DDMFormInstance ddmFormInstance = DDMFormInstanceLocalServiceUtil.fetchDDMFormInstance(formInstanceId);
				if (Validator.isNotNull(ddmFormInstance)) {
					long structureId = ddmFormInstance.getStructureId();
					DDMStructure ddmStructure = DDMStructureLocalServiceUtil.fetchStructure(structureId);
					String structureDefinition = ddmStructure.getDefinition();
					String labelIdentificator = getLabelIdentificatorByLabelName(structureDefinition,
							NOMINEE_EMAIL_ADDRESS);
					if (Validator.isNotNull(labelIdentificator)) {
						nomineeEmailAddress = getValueByLabelIdentificator(storageId, labelIdentificator);
					} else {
						logger.info("There is no labelIdentificator with this label: " + NOMINEE_EMAIL_ADDRESS);
					}
				} else {
					logger.info("There is no ddmFormInstance with id: " + formInstanceId);
				}
			} else {
				logger.info("There is no ddmFormInstanceRecord with id: " + classPK);
			}
		} catch (PortalException e) {
			logger.error("Error getting workflow instance with id: " + classPK);
		}
		return nomineeEmailAddress;
	}

	/**
	 * Get Nominator Name from nomination form
	 * 
	 * @param classPK from workflow instance
	 */
	public static String getNominatorNameFromForm(long classPK) {
		String nominatorName = StringPool.BLANK;
		try {
			DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion = DDMFormInstanceRecordVersionLocalServiceUtil
					.getFormInstanceRecordVersion(classPK);
			if (Validator.isNotNull(ddmFormInstanceRecordVersion)) {
				long formInstanceId = ddmFormInstanceRecordVersion.getFormInstanceId();
				long storageId = ddmFormInstanceRecordVersion.getStorageId();
				DDMFormInstance ddmFormInstance = DDMFormInstanceLocalServiceUtil.fetchDDMFormInstance(formInstanceId);
				if (Validator.isNotNull(ddmFormInstance)) {
					long structureId = ddmFormInstance.getStructureId();
					DDMStructure ddmStructure = DDMStructureLocalServiceUtil.fetchStructure(structureId);
					String structureDefinition = ddmStructure.getDefinition();
					String labelIdentificator = getLabelIdentificatorByLabelName(structureDefinition, NOMINATOR_NAME);
					if (Validator.isNotNull(labelIdentificator)) {
						nominatorName = getValueByLabelIdentificator(storageId, labelIdentificator);
					} else {
						logger.info("There is no labelIdentificator with this label: " + NOMINATOR_NAME);
					}
				} else {
					logger.info("There is no ddmFormInstance with id: " + formInstanceId);
				}
			} else {
				logger.info("There is no ddmFormInstanceRecord with id: " + classPK);
			}
		} catch (PortalException e) {
			logger.error("Error getting workflow instance with id: " + classPK);
		}
		return nominatorName;
	}

	/**
	 * Get NomineeName from nomination form
	 * 
	 * @param classPK from workflow instance
	 */
	public static String getNomineeNameFromForm(long classPK) {
		String nomineeName = StringPool.BLANK;
		try {
			DDMFormInstanceRecordVersion ddmFormInstanceRecordVersion = DDMFormInstanceRecordVersionLocalServiceUtil
					.getFormInstanceRecordVersion(classPK);
			if (Validator.isNotNull(ddmFormInstanceRecordVersion)) {
				long formInstanceId = ddmFormInstanceRecordVersion.getFormInstanceId();
				long storageId = ddmFormInstanceRecordVersion.getStorageId();
				DDMFormInstance ddmFormInstance = DDMFormInstanceLocalServiceUtil.fetchDDMFormInstance(formInstanceId);
				if (Validator.isNotNull(ddmFormInstance)) {
					long structureId = ddmFormInstance.getStructureId();
					DDMStructure ddmStructure = DDMStructureLocalServiceUtil.fetchStructure(structureId);
					String structureDefinition = ddmStructure.getDefinition();
					String labelIdentificator = getLabelIdentificatorByLabelName(structureDefinition, NOMINEE_NAME);
					if (Validator.isNotNull(labelIdentificator)) {
						nomineeName = getValueByLabelIdentificator(storageId, labelIdentificator);
					} else {
						logger.info("There is no labelIdentificator with this label: " + NOMINEE_NAME);
					}
				} else {
					logger.info("There is no ddmFormInstance with id: " + formInstanceId);
				}
			} else {
				logger.info("There is no ddmFormInstanceRecord with id: " + classPK);
			}
		} catch (PortalException e) {
			logger.error("Error getting workflow instance with id: " + classPK);
		}
		return nomineeName;
	}

	private static String getLabelIdentificatorByLabelName(String structureDefinition, String labelName) {
		String labelIdentificator = StringPool.BLANK;
		try {
			JSONObject definitionJson = JSONFactoryUtil.createJSONObject(structureDefinition);
			JSONArray formFieldsArray = (JSONArray) definitionJson.get("fields");
			JSONObject eachFieldJsonObject = JSONFactoryUtil.createJSONObject();
			for (int i = 0; i < formFieldsArray.length(); i++) {
				eachFieldJsonObject = (JSONObject) formFieldsArray.get(i);
				JSONObject labelJsonObject = eachFieldJsonObject.getJSONObject("label");
				String fieldLabel = labelJsonObject.getString("en_GB");
				if (labelName.equalsIgnoreCase(fieldLabel)) {
					labelIdentificator = eachFieldJsonObject.getString("name");
					logger.info("labelIdentificator: " + labelIdentificator);
					break;
				}
			}

		} catch (JSONException e) {
			logger.error("Error parsing structure definition to json object: " + e);
		}
		logger.info("getLabelIdentificator - labelIdentificator: " + labelIdentificator);
		return labelIdentificator;
	}

	private static String getValueByLabelIdentificator(long storageId, String labelIdentificator) {
		String value = StringPool.BLANK;
		DDMContent ddmContent = DDMContentLocalServiceUtil.fetchDDMContent(storageId);
		if (Validator.isNotNull(ddmContent)) {
			String data = ddmContent.getData();
			try {
				JSONObject dataJsonObject = JSONFactoryUtil.createJSONObject(data);
				JSONArray dataFieldValuesArray = dataJsonObject.getJSONArray("fieldValues");
				for (int i = 0; i < dataFieldValuesArray.length(); i++) {
					JSONObject eachDataFieldJson = (JSONObject) dataFieldValuesArray.get(i);
					String name = eachDataFieldJson.getString("name");
					if (labelIdentificator.equalsIgnoreCase(name)) {
						JSONObject valueObject = (JSONObject) eachDataFieldJson.get("value");
						value = valueObject.getString("en_GB");
						break;
					}
				}
			} catch (JSONException e) {
				logger.error("Error parsing data to json object: " + e);
			}

		} else {
			logger.info("There is no ddmContent with id: " + storageId);

		}
		logger.info("getValueByLabelIdentificator - value: " + value);
		return value;
	}

	public static String getNomineeEmailAddressFromDDLRecord(long classPK) {
		String nomineeEmailAddress = StringPool.BLANK;
		try {
			DDLRecordVersion ddlRecordVersion = DDLRecordVersionLocalServiceUtil.getRecordVersion(classPK);
			nomineeEmailAddress = getValueByLabelIdentificator(ddlRecordVersion.getDDMStorageId(),
					NOMINEE_EMAIL_ADDRESS);
			logger.info("nomineeEmailAddress: " + nomineeEmailAddress);
		} catch (PortalException e) {
			logger.error("There is no ddlRecordVersion with id: " + classPK);
		}
		return nomineeEmailAddress;
	}

	public static String getNominatorEmailAddressFromDDLRecord(long classPK) {
		String nominatorEmailAddress = StringPool.BLANK;
		try {
			DDLRecordVersion ddlRecordVersion = DDLRecordVersionLocalServiceUtil.getRecordVersion(classPK);
			nominatorEmailAddress = getValueByLabelIdentificator(ddlRecordVersion.getDDMStorageId(),
					NOMINATOR_EMAIL_ADDRESS);
			logger.info("nominatorEmailAddress: " + nominatorEmailAddress);
		} catch (PortalException e) {
			logger.error("There is no ddlRecordVersion with id: " + classPK);
		}
		return nominatorEmailAddress;
	}

	public static String getNominatorNamefromDDL(long classPK) {
		String nominatorNamefromDDL = StringPool.BLANK;
		try {
			DDLRecordVersion ddlRecordVersion = DDLRecordVersionLocalServiceUtil.getRecordVersion(classPK);
			nominatorNamefromDDL = getValueByLabelIdentificator(ddlRecordVersion.getDDMStorageId(), NOMINATOR_NAME);
			logger.info("nominatorNamefromDDL: " + nominatorNamefromDDL);
		} catch (PortalException e) {
			logger.error("There is no ddlRecordVersion with id: " + classPK);
		}
		return nominatorNamefromDDL;
	}

	public static String getNomineeNamefromDDL(long classPK) {
		String nomineeNamefromDDL = StringPool.BLANK;
		try {
			DDLRecordVersion ddlRecordVersion = DDLRecordVersionLocalServiceUtil.getRecordVersion(classPK);
			nomineeNamefromDDL = getValueByLabelIdentificator(ddlRecordVersion.getDDMStorageId(), NOMINEE_NAME);
			logger.info("nomineeNamefromDDL: " + nomineeNamefromDDL);
		} catch (PortalException e) {
			logger.error("There is no ddlRecordVersion with id: " + classPK);
		}
		return nomineeNamefromDDL;
	}

	/**
	 * Get the value of the action owner field frm CMSP Action Log ddl
	 *
	 * @param classPK from workflow
	 * @return
	 */
	public static String getRecipientFromDDLRecord(long classPK) {
		String recipient = StringPool.BLANK;
		try {
			DDLRecordVersion ddlRecordVersion = DDLRecordVersionLocalServiceUtil.getRecordVersion(classPK);
			recipient = getValueByLabelIdentificatorForDropdowns(ddlRecordVersion.getDDMStorageId(), CMSP_ACTION_OWNER);
			logger.info("recipient: " + recipient);
		} catch (PortalException e) {
			logger.error("There is no ddlRecordVersion with id: " + classPK);
		}
		return recipient;
	}

	private static String getValueByLabelIdentificatorForDropdowns(long storageId, String labelIdentificator) {
		String value = StringPool.BLANK;
		DDMContent ddmContent = DDMContentLocalServiceUtil.fetchDDMContent(storageId);
		if (Validator.isNotNull(ddmContent)) {
			String data = ddmContent.getData();
			try {
				JSONObject dataJsonObject = JSONFactoryUtil.createJSONObject(data);
				JSONArray dataFieldValuesArray = dataJsonObject.getJSONArray("fieldValues");
				for (int i = 0; i < dataFieldValuesArray.length(); i++) {
					JSONObject eachDataFieldJson = (JSONObject) dataFieldValuesArray.get(i);
					String name = eachDataFieldJson.getString("name");
					if (labelIdentificator.equalsIgnoreCase(name)) {
						JSONObject valueObject = (JSONObject) eachDataFieldJson.get("value");
						value = valueObject.getString("en_GB");
						value = value.replace("[", "");
						value = value.replace("]", "");
						value = value.replace("\"", "");
						break;
					}
				}
			} catch (JSONException e) {
				logger.error("Error parsing data to json object: " + e);
			}
		} else {
			logger.info("There is no ddmContent with id: " + storageId);

		}
		logger.info("getValueByLabelIdentificator - value: " + value);
		return value;
	}

}
