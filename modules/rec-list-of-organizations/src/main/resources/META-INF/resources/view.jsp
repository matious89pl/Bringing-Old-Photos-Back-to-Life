<%@ include file="init.jsp"%>

<%@ page import="java.util.List"%>
<%@ page import="javax.portlet.WindowState"%>
<%@ page import="javax.portlet.PortletMode"%>
<%@ page import="javax.portlet.PortletURL"%>
<%@ page import="javax.portlet.PortletSession"%>

<%@ page import="com.liferay.portal.kernel.service.OrganizationLocalServiceUtil"%>
<%@ page import="com.liferay.document.library.kernel.service.DLAppLocalServiceUtil"%>
<%@ page import="com.liferay.portal.kernel.model.Organization"%>
<%@ page import="com.liferay.portal.kernel.model.Group"%>
<%@ page import="com.liferay.portal.kernel.repository.model.Folder"%>

<%@page	import="com.everis.list.of.organizations.constants.RecListOfOrganizationsPortletKeys"%>

<%@ taglib prefix="clay" uri="http://liferay.com/tld/clay"%>

<portlet:resourceURL var="exportCSVURL"></portlet:resourceURL>

<script>
	window.addEventListener('load', function() {
	    document.getElementsByClassName("navbar-breakpoint-down-d-none")[0].innerHTML = "Filters Options";
	})
</script>

<%
	PortletURL portletURL = renderResponse.createRenderURL();
	portletURL.setPortletMode(PortletMode.VIEW);
	portletURL.setWindowState(WindowState.NORMAL);
	
	portletURL.setParameter("mvcRenderCommandName", RecListOfOrganizationsPortletKeys.VIEW_LANDING);
	portletURL.setParameter("cur",ParamUtil.getString(renderRequest, "cur"),"0");
	portletURL.setParameter("orderByCol",ParamUtil.getString(renderRequest, "orderByCol", "name"));
	portletURL.setParameter("orderByType",ParamUtil.getString(renderRequest, "orderByType", "asc"));
	portletURL.setParameter("keywords", ParamUtil.getString(renderRequest, "keywords"));
%>

<div class="rec-list-of-organizations">
	<div id="exportFile">
		<aui:button cssClass="exportButton" type="submit" name="export"
			value="Export CSV" href="${exportCSVURL}" />
	</div>

	<div>
		<%-- Clay management toolbar. "${assignmentsManagementToolbarDisplayContext.getOrderByType()}"--%>
		<clay:management-toolbar disabled="<%=false %>"
			displayContext="${assignmentsManagementToolbarDisplayContext}"
			itemsTotal="${organisationListCount}"
			searchContainerId="orgListEntries" 
			selectable="false"
			namespace="<%= renderResponse.getNamespace() %>"
        	showResultsBar="<%= false%>"
        	searchInputName="keywords"
        	searchValue="<%= ParamUtil.getString(request, "keywords") %>" 
        	componentId="assignmentsManagementToolbar"   
	        searchFormName="searchFm" 
			sortingOrder="desc"
	        sortingURL="${assignmentsManagementToolbarDisplayContext.getSortingURL()}"
		/>
	</div>

	<%-- Search container. --%>
	<div id="searchContainerOrgs">
		<liferay-ui:search-container 
			emptyResultsMessage="No organisations"
			id="orgListEntries" 
			iteratorURL="${portletURL}"
			total="${organisationListCount}"
			deltaConfigurable="false"
			delta="50">

			<liferay-ui:search-container-results results="${organisationList}" />

			<liferay-ui:search-container-row
				className="com.liferay.portal.kernel.model.Organization"
				modelVar="org">
				<%
					Group grp = org.getGroup();
					if (grp.getSite()) {
				%>
				<liferay-ui:search-container-column-text
					href="/group${org.getGroup().getFriendlyURL()}"
					name="Organisation Name" value="${org.getName()}" />
				<%
					} else {
				%>
				<liferay-ui:search-container-column-text name="Organisation Name"
					value="${org.getName()}" />
				<%
					}
				%>
				<liferay-ui:search-container-column-text name="Organisation ID"
					value="${org.getOrganizationId()}" />
				<%
					if (org.getParentOrganizationId() != 0) {
				%>
				<liferay-ui:search-container-column-text
					name="Parent Organisation ID"
					value="${org.getParentOrganizationId()}" />
				<liferay-ui:search-container-column-text
					name="Parent Name"
					value="${org.getParentOrganization().getName()}" />
				<%
					} else {
				%>
				<liferay-ui:search-container-column-text
					name="Parent Organisation ID" value="No Parent Organisation"
					cssClass="noContent" />
				<liferay-ui:search-container-column-text
					name="Parent Name" value="No Parent Organisation"
					cssClass="noContent" />
				<%
					}
					
					if (grp.getSite()) {
				%>
				<liferay-ui:search-container-column-text name="Repository ID"
					value="${org.getGroup().getGroupId()}" />
				<%
					} else {
				%>
				<liferay-ui:search-container-column-text name="Repository ID"
					value="No Repository" cssClass="noContent" />
				<%
					}
					
					try {
						Folder rp_folder = DLAppLocalServiceUtil.getFolder(grp.getGroupId(), 0, "RPA Documents");
						String folderId = String.valueOf(rp_folder.getFolderId());
				%>
				<liferay-ui:search-container-column-text name="Folder ID"
					value="<%=folderId%>" />
				<%
					} catch (Exception e) {
				%>
				<liferay-ui:search-container-column-text name="Folder ID"
					value="No Folder ID" cssClass="noContent" />
				<%
					}
				%>

			</liferay-ui:search-container-row>
			<liferay-ui:search-iterator paginate="<%=true%>"  markupView="lexicon" />
		</liferay-ui:search-container>
	</div>
</div>
