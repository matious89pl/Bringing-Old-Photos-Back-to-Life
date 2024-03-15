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

package rec.link.menu.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for LinkMenu. This utility wraps
 * <code>rec.link.menu.service.impl.LinkMenuLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see LinkMenuLocalService
 * @generated
 */
public class LinkMenuLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>rec.link.menu.service.impl.LinkMenuLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static rec.link.menu.model.LinkMenu addLink(
		long linkId, String link, String linkName, String iconName,
		int linkPosition,
		com.liferay.portal.kernel.service.ServiceContext context) {

		return getService().addLink(
			linkId, link, linkName, iconName, linkPosition, context);
	}

	/**
	 * Adds the link menu to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LinkMenuLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param linkMenu the link menu
	 * @return the link menu that was added
	 */
	public static rec.link.menu.model.LinkMenu addLinkMenu(
		rec.link.menu.model.LinkMenu linkMenu) {

		return getService().addLinkMenu(linkMenu);
	}

	/**
	 * Creates a new link menu with the primary key. Does not add the link menu to the database.
	 *
	 * @param linkId the primary key for the new link menu
	 * @return the new link menu
	 */
	public static rec.link.menu.model.LinkMenu createLinkMenu(long linkId) {
		return getService().createLinkMenu(linkId);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			createPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the link menu from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LinkMenuLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param linkMenu the link menu
	 * @return the link menu that was removed
	 */
	public static rec.link.menu.model.LinkMenu deleteLinkMenu(
		rec.link.menu.model.LinkMenu linkMenu) {

		return getService().deleteLinkMenu(linkMenu);
	}

	/**
	 * Deletes the link menu with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LinkMenuLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param linkId the primary key of the link menu
	 * @return the link menu that was removed
	 * @throws PortalException if a link menu with the primary key could not be found
	 */
	public static rec.link.menu.model.LinkMenu deleteLinkMenu(long linkId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteLinkMenu(linkId);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>rec.link.menu.model.impl.LinkMenuModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>rec.link.menu.model.impl.LinkMenuModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static rec.link.menu.model.LinkMenu fetchLinkMenu(long linkId) {
		return getService().fetchLinkMenu(linkId);
	}

	/**
	 * Returns the link menu matching the UUID and group.
	 *
	 * @param uuid the link menu's UUID
	 * @param groupId the primary key of the group
	 * @return the matching link menu, or <code>null</code> if a matching link menu could not be found
	 */
	public static rec.link.menu.model.LinkMenu fetchLinkMenuByUuidAndGroupId(
		String uuid, long groupId) {

		return getService().fetchLinkMenuByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the link menu with the primary key.
	 *
	 * @param linkId the primary key of the link menu
	 * @return the link menu
	 * @throws PortalException if a link menu with the primary key could not be found
	 */
	public static rec.link.menu.model.LinkMenu getLinkMenu(long linkId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getLinkMenu(linkId);
	}

	/**
	 * Returns the link menu matching the UUID and group.
	 *
	 * @param uuid the link menu's UUID
	 * @param groupId the primary key of the group
	 * @return the matching link menu
	 * @throws PortalException if a matching link menu could not be found
	 */
	public static rec.link.menu.model.LinkMenu getLinkMenuByUuidAndGroupId(
			String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getLinkMenuByUuidAndGroupId(uuid, groupId);
	}

	/**
	 * Returns a range of all the link menus.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>rec.link.menu.model.impl.LinkMenuModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of link menus
	 * @param end the upper bound of the range of link menus (not inclusive)
	 * @return the range of link menus
	 */
	public static java.util.List<rec.link.menu.model.LinkMenu> getLinkMenus(
		int start, int end) {

		return getService().getLinkMenus(start, end);
	}

	/**
	 * Returns all the link menus matching the UUID and company.
	 *
	 * @param uuid the UUID of the link menus
	 * @param companyId the primary key of the company
	 * @return the matching link menus, or an empty list if no matches were found
	 */
	public static java.util.List<rec.link.menu.model.LinkMenu>
		getLinkMenusByUuidAndCompanyId(String uuid, long companyId) {

		return getService().getLinkMenusByUuidAndCompanyId(uuid, companyId);
	}

	/**
	 * Returns a range of link menus matching the UUID and company.
	 *
	 * @param uuid the UUID of the link menus
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of link menus
	 * @param end the upper bound of the range of link menus (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching link menus, or an empty list if no matches were found
	 */
	public static java.util.List<rec.link.menu.model.LinkMenu>
		getLinkMenusByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<rec.link.menu.model.LinkMenu> orderByComparator) {

		return getService().getLinkMenusByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of link menus.
	 *
	 * @return the number of link menus
	 */
	public static int getLinkMenusCount() {
		return getService().getLinkMenusCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	public static java.util.List<rec.link.menu.model.LinkMenu>
		getSortedByLinksPosition(
			java.util.List<rec.link.menu.model.LinkMenu> links) {

		return getService().getSortedByLinksPosition(links);
	}

	/**
	 * Updates the link menu in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LinkMenuLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param linkMenu the link menu
	 * @return the link menu that was updated
	 */
	public static rec.link.menu.model.LinkMenu updateLinkMenu(
		rec.link.menu.model.LinkMenu linkMenu) {

		return getService().updateLinkMenu(linkMenu);
	}

	public static LinkMenuLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LinkMenuLocalService, LinkMenuLocalService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(LinkMenuLocalService.class);

		ServiceTracker<LinkMenuLocalService, LinkMenuLocalService>
			serviceTracker =
				new ServiceTracker<LinkMenuLocalService, LinkMenuLocalService>(
					bundle.getBundleContext(), LinkMenuLocalService.class,
					null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}