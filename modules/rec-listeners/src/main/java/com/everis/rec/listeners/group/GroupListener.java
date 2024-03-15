package com.everis.rec.listeners.group;

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
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.kernel.service.persistence.OrganizationUtil;

import java.util.List;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = ModelListener.class)
public class GroupListener extends BaseModelListener<Group> {

	private static final Log logger = LogFactoryUtil.getLog(UserListener.class);

	private static final String MAU_ORGANISATION_ROLE = "Master Administrative User";

	@Override
	public void onAfterCreate(Group model) throws ModelListenerException {
		logger.info("onAfterCreate: group " + model);
		long classNameId = ClassNameLocalServiceUtil.getClassName("com.liferay.portal.kernel.model.Organization")
				.getClassNameId();
		if (classNameId == model.getClassNameId()) {
			logger.debug("New group for organization: " + model.getName());
			assingNewChildOrganizationToMAU(model);
		}
	}

	private void assingNewChildOrganizationToMAU(Group model) {
		Organization original = OrganizationLocalServiceUtil.fetchOrganization(model.getClassPK());
		Organization parent = original;
		logger.debug("original: " + original);
		if (original.getParentOrganizationId() != 0) {
			try {
				while (parent.getAncestors().size() > 1) {
					logger.debug("model.getAncestors().size(): " + parent.getAncestors().size());
					for (Organization ancestor : parent.getAncestors()) {
						logger.debug("ancestor: " + ancestor.getName());
						if (ancestor.getAncestors().size() == 1) {
							parent = ancestor;
							logger.debug("toCheck");
						}
					}
				}

				logger.debug("parent organization : " + model.getName());
				List<User> userList = OrganizationUtil.getUsers(parent.getOrganizationId());
				logger.debug("userList: " + userList.size());

				Role role = RoleLocalServiceUtil.getRole(original.getCompanyId(), MAU_ORGANISATION_ROLE);

				for (User user : userList) {
					logger.debug("user: " + user.getEmailAddress());
					List<UserGroupRole> userGroupRoleList = UserGroupRoleLocalServiceUtil
							.getUserGroupRoles(user.getUserId());
					for (UserGroupRole userGroupRole : userGroupRoleList) {
						if (userGroupRole.getRoleId() == role.getRoleId()) {
							logger.debug("is mau");
							OrganizationLocalServiceUtil.addUserOrganization(user.getUserId(),
									original.getOrganizationId());

							UserGroupRoleLocalServiceUtil.addUserGroupRole(user.getUserId(), model.getGroupId(),
									role.getRoleId());
							break;
						}
					}
				}

			} catch (PortalException e) {
				logger.error("ERROR: " + e.getMessage());
			}
		}
	}
}