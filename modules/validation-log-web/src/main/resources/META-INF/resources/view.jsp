<%@ include file="/init.jsp"%>

<portlet:resourceURL var="exportCSVURL">
	<portlet:param name="export" value="exportCSV" />
</portlet:resourceURL>
<!--<portlet:actionURL name="addValidationLog" var="addValidationLog" />-->

<%
	PortletURL portletURL = renderResponse.createRenderURL();
	portletURL.setPortletMode(PortletMode.VIEW);
	portletURL.setWindowState(WindowState.NORMAL);
	portletURL.setParameter("mvcRenderCommandName", ValidationLogWebPortletKeys.VIEW_LANDING);
	portletURL.setParameter("cur", ParamUtil.getString(renderRequest, "cur"), "0");
	portletURL.setParameter("orderByCol", ParamUtil.getString(renderRequest, "orderByCol", "specificParty"));
	portletURL.setParameter("orderByType", ParamUtil.getString(renderRequest, "orderByType", "asc"));
	portletURL.setParameter("filterFileName", ParamUtil.getString(renderRequest, "filterFileName"));
	portletURL.setParameter("keywords", ParamUtil.getString(renderRequest, "keywords"));
%>

<h2>Data Export as CSV in Liferay Portlet</h2>
<div>
	<a href="<%=exportCSVURL%>">Export</a>
</div>
<!--<h2>Add new Validation Log</h2>
<div>
	<a href="<%--addValidationLog--%>">ADD NEW</a>
</div>  -->
<br />

<clay:management-toolbar disabled="<%=false %>"
	displayContext="${displayContext}"
	itemsTotal="${validationsList.size()}"
	searchContainerId="validationLogs" selectable="false"
	namespace="<%= renderResponse.getNamespace() %>"
	showResultsBar="<%= false%>" searchInputName="keywords"
	searchValue="<%= ParamUtil.getString(request, "keywords") %>"
	clearResultsURL="${displayContext.getClearResultsURL()}"
	componentId="assignmentsManagementToolbar" searchFormName="searchFm"
	sortingOrder="${displayContext.getOrderByType()}"
	sortingURL="${displayContext.getSortingURL()}" />

<liferay-ui:search-container id="validationLogs"
	iteratorURL="${portletURL}"
	emptyResultsMessage="There aren't validation logs" delta="3"
	total="${validationsList.size()}">
	<liferay-ui:search-container-results results="${entries}" />
	<liferay-ui:search-container-row
		className="com.everis.rec.validation.log.model.ValidationLog"
		keyProperty="validationLogId" modelVar="validationlog"
		escapedModel="<%=true%>">
		<liferay-ui:search-container-column-text name="companyId"
			property="companyId" />
		<liferay-ui:search-container-column-text name="groupId"
			property="groupId" />
		<liferay-ui:search-container-column-text name="createDate">
			<fmt:formatDate pattern="yyyy-MM-dd"
				value="${validationlog.getCreateDate()}" />
		</liferay-ui:search-container-column-text>
		<liferay-ui:search-container-column-text name="fileName"
			property="fileName" />
		<liferay-ui:search-container-column-text name="uploadedBy"
			property="uploadedBy" />
		<liferay-ui:search-container-column-text name="uploadedFrom"
			property="uploadedFrom" />
		<liferay-ui:search-container-column-text name="folderId"
			property="folderId" />
		<liferay-ui:search-container-column-text name="logReason"
			property="logReason" />
	</liferay-ui:search-container-row>
	<liferay-ui:search-iterator />
</liferay-ui:search-container>

