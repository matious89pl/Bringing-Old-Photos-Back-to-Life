/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.everis.rec.ddl.journal.article.service.impl;

import com.everis.rec.ddl.journal.article.service.base.DDL_JournalArticleServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.kernel.service.ResourcePermissionServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GroupThreadLocal;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * The implementation of the ddl_ journal article remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * <code>com.everis.rec.ddl.journal.article.service.DDL_JournalArticleService</code>
 * interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have
 * security checks based on the propagated JAAS credentials because this service
 * can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DDL_JournalArticleServiceBaseImpl
 */
@Component(property = { "json.web.service.context.name=rec_ddl_journal_article",
		"json.web.service.context.path=DDL_JournalArticle" }, service = AopService.class)
public class DDL_JournalArticleServiceImpl extends DDL_JournalArticleServiceBaseImpl {

	private final Log logger = LogFactoryUtil.getLog(DDL_JournalArticleServiceImpl.class);

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use
	 * <code>com.everis.rec.ddl.journal.article.service.
	 * DDL_JournalArticleServiceUtil</code> to access the ddl_ journal article
	 * remote service.
	 */

	public void removePermissionsOwner(long userId) {

		try {
			// Set view permissions to owner
			logger.info("GETTING OWNER ROLE");
			Role roleOwner = RoleLocalServiceUtil.getRole(CompanyThreadLocal.getCompanyId(), RoleConstants.OWNER);
			Role roleAdmin = RoleLocalServiceUtil.getRole(CompanyThreadLocal.getCompanyId(),
					RoleConstants.ADMINISTRATOR);

			logger.info("roleOwner: " + roleOwner);

			long ownerRoleId = roleOwner.getRoleId();
			logger.info("ownerRoleId: " + ownerRoleId);
			logger.info("roleAdmin: " + roleAdmin.getRoleId());
			logger.info("getGroupId(): " + GroupThreadLocal.getGroupId());
			logger.info("getCompanyId(): " + CompanyThreadLocal.getCompanyId());

			List<String> permissionList = ResourceActionsUtil
					.getModelResourceActions("com.liferay.dynamic.data.lists.model.DDLRecordSet");

			logger.info("PermissionList: " + permissionList);
			for (String permission : permissionList) {
				logger.info("Each Permission: " + permission);

				long[] roleIdList = UserLocalServiceUtil.getUser(userId).getRoleIds();

				List<Role> roleList = UserLocalServiceUtil.getUser(userId).getRoles();

				boolean containAdminRole = roleList.contains(roleAdmin);

				if (containAdminRole) {

					boolean hasPermissionsResource = ResourcePermissionLocalServiceUtil.hasResourcePermission(
							CompanyThreadLocal.getCompanyId(), "com.liferay.dynamic.data.lists.model.DDLRecordSet",
							ResourceConstants.SCOPE_INDIVIDUAL, "com.liferay.dynamic.data.lists.model.DDLRecordSet",
							roleAdmin.getRoleId(), permission);

					logger.info("This userId " + userId + " has permissions? " + hasPermissionsResource);

					ResourcePermissionServiceUtil.removeResourcePermissions(GroupThreadLocal.getGroupId(),
							CompanyThreadLocal.getCompanyId(), "com.liferay.dynamic.data.lists.model.DDLRecordSet",
							ResourceConstants.SCOPE_INDIVIDUAL, ownerRoleId, permission);

				} else {

					for (long roleId : roleIdList) {

						// Set admin role to user
						UserLocalServiceUtil.addRoleUser(roleAdmin.getRoleId(), userId);
						logger.info("Role Admin added: ");

						boolean hasPermissionsResource = ResourcePermissionLocalServiceUtil.hasResourcePermission(
								CompanyThreadLocal.getCompanyId(), "com.liferay.dynamic.data.lists.model.DDLRecordSet",
								ResourceConstants.SCOPE_INDIVIDUAL, "com.liferay.dynamic.data.lists.model.DDLRecordSet",
								roleId, permission);

						logger.info("This userId " + userId + " has permissions? " + hasPermissionsResource);

						ResourcePermissionServiceUtil.removeResourcePermissions(GroupThreadLocal.getGroupId(),
								CompanyThreadLocal.getCompanyId(), "com.liferay.dynamic.data.lists.model.DDLRecordSet",
								ResourceConstants.SCOPE_INDIVIDUAL, ownerRoleId, permission);

						// Remove admin role to user
						UserLocalServiceUtil.deleteRoleUser(roleAdmin.getRoleId(), userId);
						logger.info("Role Admin removed: ");

					}

				}

			}
			logger.info("All permissions removed for Owner User");

		} catch (PortalException e) {
			logger.error("Owner permissions ERROR");
		}

	}

	public void removePermissionsSiteMembers(long userId) {

		try {
			// Set view permissions to site member
			logger.info("GETTING SITE MEMBER ROLE");
			Role roleSiteMember = RoleLocalServiceUtil.getRole(CompanyThreadLocal.getCompanyId(),
					RoleConstants.SITE_MEMBER);
			Role roleAdmin = RoleLocalServiceUtil.getRole(CompanyThreadLocal.getCompanyId(),
					RoleConstants.ADMINISTRATOR);

			logger.info("roleSiteMember: " + roleSiteMember);

			long siteMemberRoleId = roleSiteMember.getRoleId();
			logger.info("siteMemberRoleId: " + siteMemberRoleId);
			logger.info("roleAdmin: " + roleAdmin.getRoleId());
			logger.info("getGroupId(): " + GroupThreadLocal.getGroupId());
			logger.info("getCompanyId(): " + CompanyThreadLocal.getCompanyId());

			List<String> permissionList = ResourceActionsUtil
					.getModelResourceActions("com.liferay.document.library.kernel.model.DLFileEntry");

			logger.info("PermissionList: " + permissionList);
			for (String permission : permissionList) {
				logger.info("Each Permission: " + permission);

				long[] roleIdList = UserLocalServiceUtil.getUser(userId).getRoleIds();

				List<Role> roleList = UserLocalServiceUtil.getUser(userId).getRoles();

				boolean containAdminRole = roleList.contains(roleAdmin);

				if (containAdminRole) {

					boolean hasPermissionsResource = ResourcePermissionLocalServiceUtil.hasResourcePermission(
							CompanyThreadLocal.getCompanyId(), "com.liferay.document.library.kernel.model.DLFileEntry",
							ResourceConstants.SCOPE_INDIVIDUAL, "com.liferay.document.library.kernel.model.DLFileEntry",
							roleAdmin.getRoleId(), permission);

					logger.info("This userId " + userId + " has permissions? " + hasPermissionsResource);

					ResourcePermissionServiceUtil.removeResourcePermissions(GroupThreadLocal.getGroupId(),
							CompanyThreadLocal.getCompanyId(), "com.liferay.document.library.kernel.model.DLFileEntry",
							ResourceConstants.SCOPE_INDIVIDUAL, siteMemberRoleId, permission);

				} else {

					for (long roleId : roleIdList) {

						// Set admin role to user
						UserLocalServiceUtil.addRoleUser(roleAdmin.getRoleId(), userId);
						logger.info("Role Admin added: ");

						boolean hasPermissionsResource = ResourcePermissionLocalServiceUtil.hasResourcePermission(
								CompanyThreadLocal.getCompanyId(), "com.liferay.document.library.kernel.model.DLFileEntry",
								ResourceConstants.SCOPE_INDIVIDUAL, "com.liferay.document.library.kernel.model.DLFileEntry",
								roleId, permission);

						logger.info("This userId " + userId + " has permissions? " + hasPermissionsResource);

						ResourcePermissionServiceUtil.removeResourcePermissions(GroupThreadLocal.getGroupId(),
								CompanyThreadLocal.getCompanyId(), "com.liferay.document.library.kernel.model.DLFileEntry",
								ResourceConstants.SCOPE_INDIVIDUAL, siteMemberRoleId, permission);

						// Remove admin role to user
						UserLocalServiceUtil.deleteRoleUser(roleAdmin.getRoleId(), userId);
						logger.info("Role Admin removed: ");

					}

				}

			}
			logger.info("All permissions removed for Site Members");

		} catch (PortalException e) {
			logger.error("Site Members permissions ERROR");
		}

	}
	
	public void removePermissionsGuest(long userId) {

		try {
			// Set view permissions to guess
			logger.info("GETTING GUESS ROLE");
			Role roleGuest = RoleLocalServiceUtil.getRole(CompanyThreadLocal.getCompanyId(),
					RoleConstants.GUEST);
			Role roleAdmin = RoleLocalServiceUtil.getRole(CompanyThreadLocal.getCompanyId(),
					RoleConstants.ADMINISTRATOR);

			logger.info("roleGuess: " + roleGuest);

			long guestRoleId = roleGuest.getRoleId();
			logger.info("guestRoleId: " + guestRoleId);
			logger.info("roleAdmin: " + roleAdmin.getRoleId());
			logger.info("getGroupId(): " + GroupThreadLocal.getGroupId());
			logger.info("getCompanyId(): " + CompanyThreadLocal.getCompanyId());

			List<String> permissionList = ResourceActionsUtil
					.getModelResourceActions("com.liferay.document.library.kernel.model.DLFileEntry");

			logger.info("PermissionList: " + permissionList);
			for (String permission : permissionList) {
				logger.info("Each Permission: " + permission);

				long[] roleIdList = UserLocalServiceUtil.getUser(userId).getRoleIds();

				List<Role> roleList = UserLocalServiceUtil.getUser(userId).getRoles();

				boolean containAdminRole = roleList.contains(roleAdmin);

				if (containAdminRole) {

					boolean hasPermissionsResource = ResourcePermissionLocalServiceUtil.hasResourcePermission(
							CompanyThreadLocal.getCompanyId(), "com.liferay.document.library.kernel.model.DLFileEntry",
							ResourceConstants.SCOPE_INDIVIDUAL, "com.liferay.document.library.kernel.model.DLFileEntry",
							roleAdmin.getRoleId(), permission);

					logger.info("This userId " + userId + " has permissions? " + hasPermissionsResource);

					ResourcePermissionServiceUtil.removeResourcePermissions(GroupThreadLocal.getGroupId(),
							CompanyThreadLocal.getCompanyId(), "com.liferay.document.library.kernel.model.DLFileEntry",
							ResourceConstants.SCOPE_INDIVIDUAL, guestRoleId, permission);

				} else {

					for (long roleId : roleIdList) {

						// Set admin role to user
						UserLocalServiceUtil.addRoleUser(roleAdmin.getRoleId(), userId);
						logger.info("Role Admin added: ");

						boolean hasPermissionsResource = ResourcePermissionLocalServiceUtil.hasResourcePermission(
								CompanyThreadLocal.getCompanyId(), "com.liferay.document.library.kernel.model.DLFileEntry",
								ResourceConstants.SCOPE_INDIVIDUAL, "com.liferay.document.library.kernel.model.DLFileEntry",
								roleId, permission);

						logger.info("This userId " + userId + " has permissions? " + hasPermissionsResource);

						ResourcePermissionServiceUtil.removeResourcePermissions(GroupThreadLocal.getGroupId(),
								CompanyThreadLocal.getCompanyId(), "com.liferay.document.library.kernel.model.DLFileEntry",
								ResourceConstants.SCOPE_INDIVIDUAL, guestRoleId, permission);

						// Remove admin role to user
						UserLocalServiceUtil.deleteRoleUser(roleAdmin.getRoleId(), userId);
						logger.info("Role Admin removed: ");

					}

				}

			}
			logger.info("All permissions removed for Guest");

		} catch (PortalException e) {
			logger.error("Guest permissions ERROR");
		}

	}
}