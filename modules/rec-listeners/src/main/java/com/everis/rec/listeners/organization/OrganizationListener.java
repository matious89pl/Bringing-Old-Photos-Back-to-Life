package com.everis.rec.listeners.organization;

import com.everis.rec.listeners.user.UserListener;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroupRole;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.kernel.service.persistence.OrganizationUtil;

import java.util.List;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = ModelListener.class)
public class OrganizationListener extends BaseModelListener<Organization> {

	private static final Log logger = LogFactoryUtil.getLog(UserListener.class);

	private static final String REC_CONTRACT_MANAGERS_ORGANISATION_ROLE = "REC Contract Managers";

	@Override
	public void onAfterCreate(Organization model) throws ModelListenerException {
		logger.info("onAfterCreate: Organization Listener");

		Organization original = model;
		if (model.getParentOrganizationId() != 0) {
			try {
				while (model.getAncestors().size() > 1) {
					logger.info("model.getAncestors().size(): " + model.getAncestors().size());
					for (Organization ancestor : model.getAncestors()) {
						logger.debug("ancestor: " + ancestor.getName());
						if (ancestor.getAncestors().size() == 1) {
							model = ancestor;
							logger.debug("toCheck");
						}
					}
				}
				logger.debug("parent organization : " + model.getName());
				List<User> userList = OrganizationUtil.getUsers(model.getOrganizationId());
				logger.debug("userList: " + userList.size());

				assingNewChildOrganizationToRECContractManager(userList, original);
			} catch (PortalException e) {
				logger.error("Error getting ancestors for organization: " + model.getName());
			}
		}
	}

	private void assingNewChildOrganizationToRECContractManager(List<User> userList, Organization original)
			throws PortalException {

		Role role = RoleLocalServiceUtil.getRole(original.getCompanyId(), REC_CONTRACT_MANAGERS_ORGANISATION_ROLE);

		for (User user : userList) {
			logger.debug("user: " + user.getEmailAddress());
			List<UserGroupRole> userGroupRoleList = UserGroupRoleLocalServiceUtil.getUserGroupRoles(user.getUserId());
			for (UserGroupRole userGroupRole : userGroupRoleList) {
				if (userGroupRole.getRoleId() == role.getRoleId()) {
					logger.debug("is contract manager");
					OrganizationLocalServiceUtil.addUserOrganization(user.getUserId(), original.getOrganizationId());
				}
			}
		}
	}

}
