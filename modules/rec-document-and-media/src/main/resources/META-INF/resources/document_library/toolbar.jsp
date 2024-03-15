<%--
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
--%>

<%@ include file="/document_library/init.jsp" %>

<%@page import="com.liferay.frontend.taglib.clay.servlet.taglib.util.CreationMenu"%>
<%@page import="rec.document.and.media.DMUtils"%>

<%
DLAdminManagementToolbarDisplayContext dlAdminManagementToolbarDisplayContext = dlAdminDisplayContextProvider.getDLAdminManagementToolbarDisplayContext(request, response);
%>

<%
	CreationMenu creationMenu = dlAdminManagementToolbarDisplayContext.getCreationMenu();
	if(creationMenu == null){
		creationMenu = DMUtils.getDMCreationMenu(themeDisplay, liferayPortletRequest);
	}
%>

<clay:management-toolbar
	actionDropdownItems="<%= dlAdminManagementToolbarDisplayContext.getActionDropdownItems() %>"
	clearResultsURL="<%= dlAdminManagementToolbarDisplayContext.getClearResultsURL() %>"
	componentId="<%= dlAdminManagementToolbarDisplayContext.getComponentId() %>"
	creationMenu="<%= creationMenu %>"
	defaultEventHandler='<%= liferayPortletResponse.getNamespace() + "DocumentLibrary" %>'
	disabled="<%= dlAdminManagementToolbarDisplayContext.isDisabled() %>"
	filterDropdownItems="<%= dlAdminManagementToolbarDisplayContext.getFilterDropdownItems() %>"
	filterLabelItems="<%= dlAdminManagementToolbarDisplayContext.getFilterLabelItems() %>"
	infoPanelId="infoPanelId"
	itemsTotal="<%= dlAdminManagementToolbarDisplayContext.getTotalItems() %>"
	searchActionURL="<%= String.valueOf(dlAdminManagementToolbarDisplayContext.getSearchURL()) %>"
	searchContainerId="entries"
	selectable="<%= dlAdminManagementToolbarDisplayContext.isSelectable() %>"
	showInfoButton="<%= true %>"
	showSearch="<%= dlAdminManagementToolbarDisplayContext.isShowSearch() %>"
	sortingOrder="<%= dlAdminManagementToolbarDisplayContext.getSortingOrder() %>"
	sortingURL="<%= String.valueOf(dlAdminManagementToolbarDisplayContext.getSortingURL()) %>"
	supportsBulkActions="<%= true %>"
	viewTypeItems="<%= dlAdminManagementToolbarDisplayContext.getViewTypes() %>"
/>