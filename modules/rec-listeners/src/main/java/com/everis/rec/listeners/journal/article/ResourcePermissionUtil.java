package com.everis.rec.listeners.journal.article;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author mvillalr
 * Class to manipulate Journal Article permissions in listeners
 */

public class ResourcePermissionUtil {
	
	private final static Log logger = LogFactoryUtil.getLog(ResourcePermissionUtil.class);
	
	
	/**
	 * This method updates or adds DLFileEntry(file) permissions for a specific role.
	 * 
	 * @param companyId
	 * @param primKey A String containing the primary key of the DLFileEntry
	 * @param roleName The name of the role whose permissions will be added/updated
	 * @param scope A number indicating the scope of the permission (individual, group, etc.)
	 * @param actionIds A bitwiseValue indicating which permissions are granted.
	 * @param ownerId The user who added/edited this permissions. 
	 */
	public static void updateJADocumentRolePermission(long companyId, long primKeyId, 
			String roleName, int scope, long actionIds, long ownerId) {
		
		boolean primKeyIdOk = primKeyId > 0;
		String primKey = ""+primKeyId;
		
		String dlFileEntryClassName = DLFileEntry.class.getName();
		Role role =  RoleLocalServiceUtil.fetchRole(companyId, roleName);
		
		boolean roleExists = Validator.isNotNull(role);
		
		
		if(primKeyIdOk && roleExists) {
			
			ResourcePermission resourcePermission = ResourcePermissionLocalServiceUtil.fetchResourcePermission(
			companyId, dlFileEntryClassName, scope,
			primKey, role.getRoleId());
			
			if(Validator.isNotNull(resourcePermission)) {  //UPDATE
				logger.info("Updating file permissions for role: "+roleName);
				resourcePermission.setViewActionId(true);
				resourcePermission.setActionIds(actionIds);
				ResourcePermissionLocalServiceUtil.updateResourcePermission(resourcePermission);
			} else {						//CREATE
				logger.info("Creating view permission for role: "+roleName);
				
				long newResourcePermissionId = CounterLocalServiceUtil.increment(ResourcePermission.class.getName());
				
				//create new permission
				resourcePermission = ResourcePermissionLocalServiceUtil.createResourcePermission(newResourcePermissionId);
				resourcePermission.setCompanyId(companyId);
				resourcePermission.setName(dlFileEntryClassName);
				resourcePermission.setScope(scope);
				resourcePermission.setPrimKey(primKey);
				resourcePermission.setPrimKeyId(primKeyId);
				resourcePermission.setRoleId(role.getRoleId());
				resourcePermission.setOwnerId(ownerId);
				resourcePermission.setViewActionId(true);
				resourcePermission.setActionIds(actionIds);
				
				ResourcePermission newRP = ResourcePermissionLocalServiceUtil.addResourcePermission(resourcePermission);
				logger.info("Created new "+roleName+" permission for primKeyId "+primKeyId+" with resourcePermissionid: "+newRP.getResourcePermissionId());
			}
			
		} else {
			if(!roleExists)
			logger.error("Doc permissions error: "+roleName+" role does not exist.");
			if(!primKeyIdOk)
			logger.error("Error: invalid primKey");
		}		
	}
	
	/**
	 * Review and Update All IA/Consultation Files Permissions depending on the targeted audience
	 * <p>
	 * Targeted audience and documents are retrieved from parameters attribute
	 * 
	 * @param parameters Map from which targeted audience and documents are extracted. 
	 * @param companyId 
	 * @param userId The user who edited this consultation will be the permission owner.
	 * @throws PortalException 
	 */
	public static void reviewJAFilesPermissions(long journalArticleId, String docDynamicElementName) throws DocumentException, PortalException {
		JournalArticle article = JournalArticleLocalServiceUtil.getArticle(journalArticleId);
		Document document = SAXReaderUtil.read(article.getContent());
		List<Node> docList = document.selectNodes("//root//dynamic-element[@name='"+docDynamicElementName+"']/dynamic-content");
		
		long companyId = article.getCompanyId();
		long userId = article.getUserId();
		int scopeIndividual = ResourceConstants.SCOPE_INDIVIDUAL;
		long viewPermissionActionIds = 1;
		List<String> roleList = new ArrayList<String>();
		
		//TODO iaTargetedAudience will be used when targeted audience logic is developed
				//temporary default roles until targeted audience logic is developed	
				roleList.add(RoleConstants.GUEST);
				roleList.add(RoleConstants.SITE_MEMBER);
				//****
		
		for(Node docNode : docList) {
			JSONObject jsonDoc = null;
			try {
				 jsonDoc = JSONFactoryUtil.createJSONObject(docNode.getText());
			} catch (JSONException e) {
				logger.error("Unable to create documents JSONArray");
			}
			if(Validator.isNotNull(jsonDoc)) {
				logger.info("file title: "+jsonDoc.get("title"));
				logger.info("file classPK:" + jsonDoc.getLong("classPK"));
				long primKeyId = jsonDoc.getLong("classPK");
				for(String roleName : roleList) {
					updateJADocumentRolePermission(companyId, primKeyId, roleName, scopeIndividual, viewPermissionActionIds, userId);
				}
			}
		}
		
	}
	
}
