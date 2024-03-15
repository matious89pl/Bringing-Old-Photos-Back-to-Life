package com.everis.rec.listeners.ddlrecord;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.dynamic.data.lists.model.DDLRecord;
import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.dynamic.data.mapping.model.DDMContent;
import com.liferay.dynamic.data.mapping.service.DDMContentLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = ModelListener.class)
public class DDLRecordListener extends BaseModelListener<DDLRecord> {

	/* logger */
	private static final Log logger = LogFactoryUtil.getLog(DDLRecordListener.class);

	/* constants */
	private static final String APP_DOCS = "APP-Docs-";
	private static final String APP_BS = "APP-Business-Solution-";
	private static final String APP_ISDP = "APP-ISDP-";
	private static final String CONSULTATION = "Consultation-";
	private static final String IMPACT_ASSESSMENT = "Impact-Assessment-";

	@Override
	public void onAfterUpdate(DDLRecord model) throws ModelListenerException {
		logger.debug("---START onAfterUpdate---");
		logger.debug("DDLRecord model... " + model);

		DDLRecordSet ddlRecordSet;
		String docFiledName = StringPool.BLANK;
		try {
			ddlRecordSet = model.getRecordSet();
			logger.debug("model ddlRecordSet... " + ddlRecordSet);
			logger.debug("ddlRecordSet name: "+ddlRecordSet.getName("en_GB"));

			if (ddlRecordSet.getName("en_GB").startsWith(APP_DOCS)) {
				logger.debug("Is APP-DOCS...");
				docFiledName = "DocumentsAndMedia2arq";
				removeDocPermissions(model, docFiledName);
			} else if(ddlRecordSet.getName("en_GB").startsWith(CONSULTATION)) {
				logger.debug("Is Consultation...");
				docFiledName = "ConsultationA_UploadDocument";
				removeDocPermissions(model, docFiledName);
			} else if(ddlRecordSet.getName("en_GB").startsWith(IMPACT_ASSESSMENT)) {
				logger.debug("Is Impact Assessment...");
				docFiledName = "IA_QA_B_Document";
				removeDocPermissions(model, docFiledName);
			} else if (ddlRecordSet.getName("en_GB").startsWith(APP_BS)) {
				logger.debug("Is APP-BS...");
				docFiledName = "PMBusiness_BSADocument";
				removeDocPermissions(model, docFiledName);
			} else if (ddlRecordSet.getName("en_GB").startsWith(APP_ISDP)) {
				logger.debug("Is APP-ISDP...");
				docFiledName = "PM_Information_ISDPDocument";
				removeDocPermissions(model, docFiledName);
			}

		} catch (PortalException e) {
			logger.error("Error getting DDLRecordSet", e);
		}

		logger.debug("---END onAfterUpdate---");
		super.onAfterUpdate(model);
	}

	private void removeDocPermissions(DDLRecord model, String docFiledName) throws PortalException {

		logger.debug("Getting DDMStorageId...");
		long storageId = model.getDDMStorageId();
		logger.debug("storageId..." + storageId);

		logger.debug("Getting DDMContent...");
		DDMContent docContent = DDMContentLocalServiceUtil.getContent(storageId);
		logger.debug("docContent..." + docContent);

		logger.debug("Getting data...");
		JSONObject docContentJSON = JSONFactoryUtil.createJSONObject(docContent.getData());
		logger.debug("docContentJSON..." + docContentJSON);
		JSONArray fieldValuesArray = (JSONArray) docContentJSON.get("fieldValues");

		for (int i = 0; i < fieldValuesArray.length(); i++) {
			JSONObject JSONField = fieldValuesArray.getJSONObject(i);
			logger.debug("Field name..." + JSONField.get("name"));
			if (JSONField.get("name").equals(docFiledName)) {
				logger.debug("Is Application document");
				JSONObject JSONFieldValue = (JSONObject) JSONField.get("value");
				JSONObject JSONDLFile = JSONFactoryUtil.createJSONObject(JSONFieldValue.get("en_GB").toString());

				if (Validator.isNotNull(JSONDLFile.get("classPK"))) {

					logger.debug("Document classPK..." + JSONDLFile.get("classPK"));

					logger.debug("Getting Guest role...");
					Role roleGuest = RoleLocalServiceUtil.getRole(model.getCompanyId(), RoleConstants.GUEST);
					ResourcePermission resourcePermission = ResourcePermissionLocalServiceUtil.getResourcePermission(
							model.getCompanyId(), DLFileEntry.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL,
							JSONDLFile.get("classPK").toString(), roleGuest.getRoleId());
					resourcePermission.setActionIds(0);
					resourcePermission.setViewActionId(false);
					logger.debug("Updating file permissions for Guest role...");
					ResourcePermissionLocalServiceUtil.updateResourcePermission(resourcePermission);

					logger.debug("Getting Site Member role...");
					Role roleSM = RoleLocalServiceUtil.getRole(model.getCompanyId(), RoleConstants.SITE_MEMBER);
					ResourcePermission resourcePermissionSM = ResourcePermissionLocalServiceUtil.getResourcePermission(
							model.getCompanyId(), DLFileEntry.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL,
							JSONDLFile.get("classPK").toString(), roleSM.getRoleId());
					resourcePermissionSM.setActionIds(0);
					resourcePermissionSM.setViewActionId(false);
					logger.debug("Updating file permissions for Site Member role...");
					ResourcePermissionLocalServiceUtil.updateResourcePermission(resourcePermissionSM);
					
				}

				break;
			}
		}

	}

}
