package com.everis.rec.listeners.usergrouprole;

import com.everis.rec.listeners.user.UserListener;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroupRole;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

import java.util.List;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = ModelListener.class)
public class UserGroupRoleListener extends BaseModelListener<UserGroupRole>{
	
	private static final Log logger = LogFactoryUtil.getLog(UserListener.class);
	
	private static final String REC_CONTRACT_MANAGERS_ORGANISATION_ROLE = "REC Contract Managers";

	private static final String MAU_ORGANISATION_ROLE = "Master Administrative User";

	
	@Override
	public void onAfterCreate(UserGroupRole model) throws ModelListenerException {
		logger.debug("onAfterCreate: UserGroupRole Listener: " + model);		
		setChildOrganizationToRECContractManagers(model);
		setChildOrganizationTOMAU(model);
	}

	private void setChildOrganizationToRECContractManagers(UserGroupRole model) {
		try {
			User user = UserLocalServiceUtil.getUser(model.getUserId());
			Role role = RoleLocalServiceUtil.getRole(user.getCompanyId(), REC_CONTRACT_MANAGERS_ORGANISATION_ROLE);
			List<UserGroupRole> userGroupRoleList = UserGroupRoleLocalServiceUtil.getUserGroupRoles(model.getUserId());
			for (UserGroupRole userGroupRole : userGroupRoleList) {				
				Group group = GroupLocalServiceUtil.getGroup(userGroupRole.getGroupId());
				if (userGroupRole.getRoleId() == role.getRoleId() && group.getGroupId() == model.getGroupId()) {
					logger.debug("userGroupRole: SAME ROLEID + SAME ORGID" + userGroupRole.getRoleId() + " - " + group.getClassPK());
					Organization organization = OrganizationLocalServiceUtil.getOrganization(group.getClassPK());
					if (organization.hasSuborganizations()) {
						List<Organization> subOrganizationList = organization.getDescendants();
						logger.debug("subOrganizationList: " + subOrganizationList.size());
						for (Organization subOrganization : subOrganizationList) {
							logger.debug("subOrganization: " + subOrganization.getName());
							OrganizationLocalServiceUtil.addUserOrganization(model.getUserId(),
									subOrganization.getOrganizationId());
						}
					}
				}
			}

		} catch (PortalException e) {
			logger.error("There is no user with id: " + model.getUserId());
		}
	}
	
	private void setChildOrganizationTOMAU(UserGroupRole model) {
		try {
			User user = UserLocalServiceUtil.getUser(model.getUserId());
			Role role = RoleLocalServiceUtil.getRole(user.getCompanyId(), MAU_ORGANISATION_ROLE);
			List<UserGroupRole> userGroupRoleList = UserGroupRoleLocalServiceUtil.getUserGroupRoles(model.getUserId());
			for (UserGroupRole userGroupRole : userGroupRoleList) {				
				Group group = GroupLocalServiceUtil.getGroup(userGroupRole.getGroupId());
				if (userGroupRole.getRoleId() == role.getRoleId() && group.getGroupId() == model.getGroupId()) {
					logger.debug("userGroupRole: SAME ROLEID + SAME ORGID " + userGroupRole.getRoleId() + " - " + group.getClassPK());
					Organization organization = OrganizationLocalServiceUtil.getOrganization(group.getClassPK());
					if (organization.hasSuborganizations()) {
						List<Organization> subOrganizationList = organization.getDescendants();
						logger.debug("subOrganizationList: " + subOrganizationList.size());
						for (Organization subOrganization : subOrganizationList) {
							OrganizationLocalServiceUtil.addUserOrganization(model.getUserId(),
									subOrganization.getOrganizationId());
							//add relation role for each suborg
							long classNameId = ClassNameLocalServiceUtil.getClassName("com.liferay.portal.kernel.model.Organization").getClassNameId();
							UserGroupRoleLocalServiceUtil.addUserGroupRole(model.getUserId(), GroupLocalServiceUtil.fetchGroup(model.getCompanyId(), classNameId, subOrganization.getOrganizationId()).getGroupId(), role.getRoleId());
						}
					}
				}
			}

		} catch (PortalException e) {
			logger.error("There is no user with id: " + model.getUserId());
		}
	}

}
