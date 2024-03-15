<%@ include file="/init.jsp"%>

<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui"%>
<%@ taglib prefix="clay" uri="http://liferay.com/tld/clay"%>

<%@page import="com.liferay.portal.kernel.service.GroupLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.model.Group"%>
<%@page
	import="com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.model.UserGroupRole"%>
<%@page import="com.liferay.portal.kernel.service.RoleLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.dao.search.SearchContainer"%>
<%@page import="com.liferay.portal.kernel.util.StringUtil"%>
<%@page
	import="com.liferay.portal.kernel.service.OrganizationLocalServiceUtil"%>

<style>
#rpa_updateStatus_popup {
	display: none;
}

.subnav-tbar-primary {
	display: none;
	!
	important;
}

.importCSVdiv {
	display: none;
}
</style>

<script>
	$(document).ready(function() {
		$(".slideButton").click(function() {
			$(".importCSVdiv").toggle("slide");

		});
		$(".cancelCSVbtn").click(function() {
			$(".importCSVdiv").toggle("slide");
			$(".slideButton").show();
		});
	});

	function callDeleteAction() {
		submitForm(document.<portlet:namespace />deleteSupportResource);

		Liferay.Portlet.refresh("<portlet:namespace />");

	}
	function callUpdateStatusAction(idd) {
		var pop = document.getElementById("rpa_updateStatus_popup");
		pop.style.display = "block";
		pop.style.opacity = "1";
		pop.style.visibility = "visible";
		document.getElementById("<portlet:namespace/>updateStatusId").value = idd;
	}
	function updateStatusRow(url) {
		//var newStatus = $("#select_rpa_status").val();
		document.getElementById("<portlet:namespace/>updateStatusVal").value = "Pending";
		submitForm(document.<portlet:namespace />updateStatus);
	}
	function closeUpdateStatus() {
		var pop = document.getElementById("rpa_updateStatus_popup");
		pop.style.display = "none";
	}

	function openPopUpDeleteResource(idd) {
		$("#supporResourceContentDelete").html(
				"Are you sure you want to delete this record?")
		$("#popupDeleteSupportingResource").css("visibility", "visible");
		document.getElementById("<portlet:namespace/>mid").value = idd;
	}
	function closePopUpDeleteResource() {
		$("#popupDeleteSupportingResource").css("visibility", "hidden");
		$("#selector").text("");
	}

	function callEditAction(idd) {
		document.getElementById("<portlet:namespace/>midEdit").value = idd;
		submitForm(document.<portlet:namespace />editSupportResources);
	}
	function closeForm() {
		var pop = document.getElementById("rpa_updateStatus_popup");
		pop.style.display = "none";
	}
</script>

<portlet:renderURL var="addNewSupportResourcePage">
	<portlet:param name="jspPage" value="/add_newRecord.jsp" />
</portlet:renderURL>

<liferay-portlet:actionURL name="deleteSupportResource"
	var="deleteSupportResource">
	<liferay-portlet:param name="param" value="" />
</liferay-portlet:actionURL>


<portlet:actionURL name="editSupportResource"
	var="editSupportResourceURL" />
<portlet:actionURL name="updateStatus" var="updateStatusURL" />
<portlet:actionURL name="addNewSupportResource"
	var="addNewSupportResourceURL" />

<portlet:actionURL name="importSupportResource"
	var="importSupportResource">
	<portlet:param name="type" value="SupportResource" />
	<portlet:param name="userId"
		value="<%=String.valueOf(themeDisplay.getUserId())%>" />
</portlet:actionURL>

<div class="rec-support-resources-portlet">

	<!-- PERMISSION CHECKS -->
	<%
		String aggregateSupportingResourcesView = portletPreferences.getValue("aggregateSupportingResourcesView",
				"no");

		boolean hasAccessOrgRole = false;
		List<UserGroupRole> userGroupRoles = UserGroupRoleLocalServiceUtil
				.getUserGroupRoles(themeDisplay.getUserId()); //, themeDisplay.getScopeGroupId(), -1, -1
		for (UserGroupRole userGroupRole : userGroupRoles) {
			if (userGroupRole.getRole().getName().equals("REC Contract Managers")
					|| userGroupRole.getRole().getName().equals("Master Administrative User")
					|| userGroupRole.getRole().getName().equals("REC_Performance_Assurance")) {
				hasAccessOrgRole = true;
			}
		}

		boolean hasAccessRPA = false;
		if (RoleLocalServiceUtil.hasUserRole(themeDisplay.getUserId(), themeDisplay.getCompanyId(), "RPA", false)
				|| RoleLocalServiceUtil.hasUserRole(themeDisplay.getUserId(), themeDisplay.getCompanyId(),
						"Portal_Admin", false)) {
			hasAccessRPA = true;
		}

		if (hasAccessRPA) {
			if (StringUtil.equals(aggregateSupportingResourcesView.toLowerCase(), "yes")) {
	%>
	<div class="importMainDiv">
		<div class="importCSVdiv">
			<aui:form cssClass="importCSVform"
				action="<%=importSupportResource%>" method="post" name="fm"
				enctype="multipart/form-data">
				<input type="file" name='csvDataFile' class="csvDataFile"
					value="Select a file" label="Select the file to import" />
				<aui:button cssClass="importCSVbtn" type="submit"
					value="Import XLXS" />
				<aui:button cssClass="cancelCSVbtn" type="button" value="Cancel" />
			</aui:form>
		</div>
		<aui:button cssClass="slideButton" value="Load XLXS " />
	</div>
	<%
		} else {
	%>
	<div id="addManually">
		<a class="new_supportResource" href="<%=addNewSupportResourceURL%>">Add
			New Record</a>
	</div>
	<%
		}
		}
	%>

	<clay:management-toolbar disabled="<%=false %>"
		displayContext="${supportResourcesManagementToolbarDisplayContext}"
		itemsTotal="${supportResourceListCount}"
		searchContainerId="supportingResourceListEntries" selectable="false"
		namespace="<%= renderResponse.getNamespace() %>"
		showResultsBar="<%= true%>" searchInputName="keywords"
		searchValue="<%= ParamUtil.getString(request, "keywords") %>"
		clearResultsURL="${supportResourcesManagementToolbarDisplayContext.getClearResultsURL()}"
		componentId="assignmentsManagementToolbar" searchFormName="searchFm"
		sortingOrder="${supportResourcesManagementToolbarDisplayContext.getOrderByType()}"
		sortingURL="${supportResourcesManagementToolbarDisplayContext.getSortingURL()}" />
	<%
		ZoneId defaultZoneId = ZoneId.of("Europe/London");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		PortletURL portletURL = renderResponse.createRenderURL();
	%>

	<liferay-ui:search-container iteratorURL="<%=portletURL%>"
		id="supportingResourceListEntries"
		emptyResultsMessage="No-Supporting-Resource-Found"
		deltaConfigurable="false" delta="50"
		total="${supportResourceListCount}">
		<liferay-ui:search-container-results results="${supportResourceList}" />
		<liferay-ui:search-container-row
			className="rec.supporting.resources.model.supportR"
			modelVar="supportResource">

			<%
				if (StringUtil.equals(aggregateSupportingResourcesView.toLowerCase(), "yes")) {
							Group group = GroupLocalServiceUtil.fetchGroup(supportResource.getOrgSiteId());
							if (group != null) {
			%>
			<liferay-ui:search-container-column-text name="Party">
				<a href="/group<%=group.getFriendlyURL()%>"><%=supportResource.getSpecificParty()%></a>
			</liferay-ui:search-container-column-text>
			<%
				} else {
			%>
			<liferay-ui:search-container-column-text name="Party">NULL</liferay-ui:search-container-column-text>
			<%
				}
						}
			%>
			<liferay-ui:search-container-column-text name="Type"
				value="${supportResource.getType()}" />
			<liferay-ui:search-container-column-text name="Title"
				value="${supportResource.getTitle()}" />
			<liferay-ui:search-container-column-text name="Description"
				value="${supportResource.getDescription()}" />
			<%
				Date date = supportResource.getDueDate();
						Instant instant = supportResource.getDueDate().toInstant();
						ZonedDateTime zonedDateTime = instant.atZone(defaultZoneId);
						String dueDate = zonedDateTime.format(formatter);
			%>
			<liferay-ui:search-container-column-text name="Due Date"
				value="<%=dueDate%>" />
			<liferay-ui:search-container-column-text name="Status"
				value="${supportResource.getStatus()}" />
			<liferay-ui:search-container-column-text
				href="<%=supportResource.getLink()%>" target="_blank" name="Link"
				value="${supportResource.getDisplayLink()}" />
			<%
				if (hasAccessRPA || hasAccessOrgRole) {
			%>
			<%
				if (hasAccessRPA) {
			%>
			<liferay-ui:search-container-column-text name="Actions">
				<aui:button name="Delete" cssClass="actionButtons" value="Delete"
					onClick="openPopUpDeleteResource('${supportResource.getSupportRId()}')" />
				<aui:button name="Edit" cssClass="actionButtons" value="Edit"
					onClick="callEditAction('${supportResource.getSupportRId()}')" />
			</liferay-ui:search-container-column-text>
			<%
				} else if (supportResource.getStatus().equals("Open") && hasAccessOrgRole) {
			%>
			<liferay-ui:search-container-column-text name="Actions">
				<input type="button" value="Update Status"
					onclick="callUpdateStatusAction('${supportResource.getSupportRId()}')">
			</liferay-ui:search-container-column-text>
			<%
				}
						}
			%>
		</liferay-ui:search-container-row>
		<liferay-ui:search-iterator markupView="lexicon" />
	</liferay-ui:search-container>

	<aui:form action="<%=deleteSupportResource%>" method="post"
		name="deleteSupportResource">
		<aui:input id="mid" name="mid" type="hidden" value="" />
	</aui:form>

	<aui:form action="<%=editSupportResourceURL%>" method="post"
		name="editSupportResources">
		<aui:input id="midEdit" name="midEdit" type="hidden" value="" />
	</aui:form>

	<div id="rpa_updateStatus_popup" class="overlaySolutions">
		<div class="popupSolutions" id="div_rpa_popup">
			<aui:form action="<%=updateStatusURL%>" method="post"
				name="updateStatus">
				<aui:input id="updateStatusId" name="updateStatusId" type="hidden"
					value="" />
				<aui:input id="updateStatusVal" name="updateStatusVal" type="hidden"
					value="" />
				<h2>Update Status</h2>
				<a class="close" href="#" onclick="closeForm()">&times;</a>
				<p class="goToStatus">The status is going to be pending.</p>
				<p>This change cannot be undone. Do you wish to proceed?</p>
				<!-- <select id="select_rpa_status">
			<option>Pending</option>
		</select> -->
				<input type="button" id="rpa_updateStatusButton" value="Update"
					onclick="updateStatusRow('<%=updateStatusURL.toString()%>')">
			</aui:form>
		</div>
	</div>


	<div id="popupDeleteSupportingResource" class="popupOrganisation1"
		style="visibility: hidden;">
		<div class="popupp">
			<div class="popUpContentTitle">
				<h2>Delete Record</h2>
				<a class="close" href="#" onclick="closeUpdateStatus()"><i class="icon-remove"></i></a>
				<a class="close" onclick="closePopUpDeleteResource()"></a>
			</div>

			<div class="popupContent">
				<div>
					<span id="supporResourceContentDelete"></span>
				</div>
				<div>
					<br>
				</div>
				<div>
					<button class="importFile_rfi btn btn-secondary"
						onclick="closePopUpDeleteResource()" type="button">Cancel</button>
					<button class="btn deleteResource" onclick="callDeleteAction()"
						type="button">Delete Record</button>

				</div>
			</div>
		</div>
	</div>
</div>
<liferay-ui:error key="invalidStatus" message="The file contains an invalid status" />	
	<liferay-ui:success key="success" message="Your request completed successfully."
/>
<liferay-ui:success key="successFile" message="File upload successful."
/>
