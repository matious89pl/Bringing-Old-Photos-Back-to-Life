package com.everis.rec.listeners.wikipage;

import com.everis.rec.listeners.ddlrecord.DDLRecordListener;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.wiki.model.WikiPage;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = ModelListener.class)

public class WikiPageListener extends BaseModelListener<WikiPage> {

	/* logger */
	private static final Log logger = LogFactoryUtil.getLog(DDLRecordListener.class);

	@Override
	public void onAfterCreate(WikiPage model) {

		super.onAfterCreate(model);
	}

	@Override
	public void onBeforeCreate(WikiPage model) throws ModelListenerException {

		super.onBeforeCreate(model);
	}

	@Override
	public void onAfterUpdate(WikiPage model) throws ModelListenerException {
		logger.debug("---START onAfterUpdate---");
		logger.debug("Wiki Page... " + model);

		logger.debug("Checking mvccVersion..." + model.getMvccVersion());
		if (model.getMvccVersion() == 0) {
			logger.debug("Model is new (mvccversion 0), the process of removing update continue...");

			logger.debug("model nodeId... " + model.getNodeId());
			logger.debug("model getPageId... " + model.getPageId());
			try {
				logger.debug("Getting role...");
				Role roleSM = RoleLocalServiceUtil.getRole(model.getCompanyId(), RoleConstants.SITE_MEMBER);
				Role roleGuest = RoleLocalServiceUtil.getRole(model.getCompanyId(), RoleConstants.GUEST);
				logger.debug("Role..." + roleSM);

				logger.debug("Getting Resourcepermissions...");
				ResourcePermission resourcePermissionSM = ResourcePermissionLocalServiceUtil.getResourcePermission(
						model.getCompanyId(), WikiPage.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL,
						String.valueOf(model.getResourcePrimKey()), roleSM.getRoleId());
				ResourcePermission resourcePermissionGuest = ResourcePermissionLocalServiceUtil.getResourcePermission(
						model.getCompanyId(), WikiPage.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL,
						String.valueOf(model.getResourcePrimKey()), roleGuest.getRoleId());
				logger.debug("resourcePermissionSM..." + resourcePermissionSM);
				logger.debug("resourcePermissionGuest..." + resourcePermissionGuest);

				logger.debug("Removing 'update' and 'add discussion' permissions...");
				resourcePermissionSM.removeResourceAction(ActionKeys.UPDATE);
				resourcePermissionSM.removeResourceAction(ActionKeys.ADD_DISCUSSION);
				
				logger.debug("Adding view permission...");
				resourcePermissionSM.addResourceAction(ActionKeys.VIEW);
				
				logger.debug("Removing add discussion for guest...");
				resourcePermissionGuest.removeResourceAction(ActionKeys.ADD_DISCUSSION);
				
				logger.debug("Updating permissions...");
				ResourcePermissionLocalServiceUtil.updateResourcePermission(resourcePermissionSM);
				ResourcePermissionLocalServiceUtil.updateResourcePermission(resourcePermissionGuest);
				logger.debug("Permissions updated.");

			} catch (PortalException e) {
				logger.error("Error getting WikiPage", e);
			}

		}
		logger.debug("---END onAfterUpdate---");
		super.onAfterUpdate(model);
	}

}
