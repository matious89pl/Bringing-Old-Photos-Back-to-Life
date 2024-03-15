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

<%@page import="java.util.List"%>
<%@page
	import="com.everis.rec.remediation.tracker.model.RemediationTracker"%>
<%@page
	import="com.everis.rec.remediation.tracker.constants.RecRemediationTrackerWebPortletKeys"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>

<style>
.subnav-tbar-primary {
	display: none;
	!
	important;
}
</style>

<script>
	$(document).ready(function() {
		$(".importCSVdivUpload").hide();

		$(".slideButton").click(function() {
			$(".importCSVdiv").toggle("slide");
			$(this).hide();
		});

		$(".cancelCSVbtn").click(function() {
			$(".importCSVdiv").toggle("slide");
			$(".slideButton").show();
		});

		$(".btn.slideButtonUpload.btn-secondary").click(function(e) {
			if ($(this).hasClass("showed")) {
				$(this).next("div").hide();
				$(this).removeClass("showed");
			} else {
				$(this).next("div").show();
				$(this).addClass("showed");
			}
		});
	});

	function callDeleteAction() {
		document.getElementById("<portlet:namespace/>submitType").value = "delete";
		submitForm(document.<portlet:namespace />deleteRemediationTracker);

		Liferay.Portlet.refresh("<portlet:namespace />");
	}

	function callEditAction(idd) {
		document.getElementById("<portlet:namespace/>mid").value = idd;
		document.getElementById("<portlet:namespace/>submitType").value = "edit";
		submitForm(document.<portlet:namespace />deleteRemediationTracker);
	}

	function callUpdateStatusAction(idd) {
		var pop = document.getElementById("rpa_updateStatus_popup");
		pop.style.display = "block";
		pop.style.opacity = "1";
		pop.style.visibility = "visible";
		document.getElementById("<portlet:namespace/>updateStatusId").value = idd;
	}

	function callUploadAction(uid) {
		document.getElementById("<portlet:namespace/>uid").value = uid;
		document.getElementById("<portlet:namespace/>submitDocType").value = "upload";
		submitForm(document.<portlet:namespace />uploadURL);
	}

	function callDownloadAction(uid) {
		document.getElementById("<portlet:namespace/>uid").value = uid;
		document.getElementById("<portlet:namespace/>submitDocType").value = "download";
		submitForm(document.<portlet:namespace />uploadURL);
	}

	function updateStatusActivity(url) {
		//var newStatus = $("#select_rpa_status").val();
		document.getElementById("<portlet:namespace/>updateStatusVal").value = "Pending";
		submitForm(document.<portlet:namespace />updateStatus);
	}

	function closeForm() {
		var pop = document.getElementById("rpa_updateStatus_popup");
		pop.style.display = "none";
	}

	/**
	 * Remediation Tracker pop up funcionality
	 */
	function openPopUpRemediationTracker(idd, remediationTitle) {
		$("#remediationTrackerLabel").html(
				"Are you sure you want to delete this record?")
		$("#popupDeleteRemediationTracker").css("visibility", "visible");
		document.getElementById("<portlet:namespace/>mid").value = idd;
	}
	function closePopUpRemediationTracker() {
		$("#popupDeleteRemediationTracker").css("visibility", "hidden");
		$("#selector").text("");
	}
</script>
<%
	String aggregateRemediationTrackerView = portletPreferences.getValue("aggregateRemediationTrackerView",
			"no");
%>
<portlet:actionURL name="importAnualRemediationTracker"
	var="importAnualRemediationTrackerURL">
	<portlet:param name="type" value="AnualMaintenance" />
	<portlet:param name="userId"
		value="<%=String.valueOf(themeDisplay.getUserId())%>" />
</portlet:actionURL>
<portlet:actionURL name="updateStatus" var="updateStatusURL" />

<portlet:actionURL name="addRemediationTracker"
	var="addRemediationTracker" />

<portlet:actionURL name="deleteRemediationTracker"
	var="deleteRemediationTracker" />

<portlet:actionURL name="uploadDocument" var="uploadURL"></portlet:actionURL>

<%
	/*
	<liferay-portlet:actionURL name="deleteRemediationTracker" var="deleteRemediationTracker">	<liferay-portlet:param name="param" value="" /> </liferay-portlet:actionURL>
	*/
%>
<%
	PortletURL portletURL = renderResponse.createRenderURL();
	portletURL.setPortletMode(PortletMode.VIEW);
	portletURL.setWindowState(WindowState.NORMAL);
	portletURL.setParameter("mvcRenderCommandName", RecRemediationTrackerWebPortletKeys.VIEW_LANDING);
	portletURL.setParameter("cur", ParamUtil.getString(renderRequest, "cur"), "0");
	portletURL.setParameter("orderByCol", ParamUtil.getString(renderRequest, "orderByCol", "specificParty"));
	portletURL.setParameter("orderByType", ParamUtil.getString(renderRequest, "orderByType", "asc"));
	portletURL.setParameter("filterStatus", ParamUtil.getString(renderRequest, "filterStatus"));
	portletURL.setParameter("filterParty", ParamUtil.getString(renderRequest, "filterParty"));
	portletURL.setParameter("filterDueDate", ParamUtil.getString(renderRequest, "filterDueDate"));
	portletURL.setParameter("keywords", ParamUtil.getString(renderRequest, "keywords"));
%>
<!-- PERMISSION CHECKS -->
<%
	boolean hasAccessOrgRole = false;
	Map<Long, Boolean> remediationTrackerDocsMap = (Map<Long, Boolean>) renderRequest
			.getAttribute("remediationTrackerDocsMap");

	List<UserGroupRole> userGroupRoles = UserGroupRoleLocalServiceUtil
			.getUserGroupRoles(themeDisplay.getUserId(), themeDisplay.getScopeGroupId());
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
		if (StringUtil.equals(aggregateRemediationTrackerView.toLowerCase(), "yes")) {
%>
<div class="importMainDiv">
	<div class="importCSVdiv">
		<aui:form cssClass="importCSVform"
			action="<%=importAnualRemediationTrackerURL%>" method="post"
			name="fm" enctype="multipart/form-data">
			<input type="file" name='csvDataFile' class="csvDataFile"
				value="Select a file" label="Select the file to import" />
			<aui:button cssClass="importCSVbtn" type="submit" value="Import XLXS" />
			<aui:button cssClass="cancelCSVbtn" type="button" value="Cancel" />
		</aui:form>
	</div>
	<aui:button cssClass="slideButton" value="Load XLXS " />
</div>
<%
	} else {
%>
<div>
	<a class="rpa_newActivity" href="${addRemediationTracker}">Add New
		Record</a>
</div>
<%
	}
	}
%>

<clay:management-toolbar disabled="<%=false %>"
	displayContext="${remediationTrackerManagementToolbarDisplayContext}"
	itemsTotal="${activityListCount}"
	searchContainerId="remediationTrackerEntries" selectable="false"
	namespace="<%= renderResponse.getNamespace() %>"
	showResultsBar="<%= false%>" searchInputName="keywords"
	searchValue="<%= ParamUtil.getString(request, "keywords") %>"
	clearResultsURL="${remediationTrackerManagementToolbarDisplayContext.getClearResultsURL()}"
	componentId="remediationTrackerManagementToolbar"
	searchFormName="searchFm" />


<aui:form action="<%=uploadURL%>" method="post"
	enctype="multipart/form-data" name="uploadURL">
	<aui:input id="submitDocType" name="submitDocType" type="hidden"
		value="" />
	<aui:input id="uid" name="uid" type="hidden" value="" />

	<liferay-ui:search-container
		emptyResultsMessage="rec.RemediationTracker.empty-results"
		id="remediationTrackerEntries" iteratorURL="${portletURL}"
		total="${remediationTrackerListCount}" deltaConfigurable="false"
		delta="50">
		<liferay-ui:search-container-results
			results="${remediationTrackerList}" />
		<liferay-ui:search-container-row
			className="com.everis.rec.remediation.tracker.model.RemediationTracker"
			modelVar="remediationTracker">
			<%
				if (StringUtil.equals(aggregateRemediationTrackerView.toLowerCase(), "yes")) {
								Group group = GroupLocalServiceUtil.fetchGroup(remediationTracker.getOrgSiteId());
								if (group != null) {
			%>
			<liferay-ui:search-container-column-text name="Party">
				<a href="/group<%=group.getFriendlyURL()%>"><%=group.getName()%></a>
			</liferay-ui:search-container-column-text>
			<%
				} else {
			%>
			<liferay-ui:search-container-column-text name="Party">NULL</liferay-ui:search-container-column-text>
			<%
				}
							}
			%>
			<liferay-ui:search-container-column-text name="Title"
				value="${remediationTracker.title}" />
			<liferay-ui:search-container-column-text name="Category"
				value="${remediationTracker.category}" />
			<liferay-ui:search-container-column-text name="Description"
				value="${remediationTracker.description}" />
			<liferay-ui:search-container-column-text name="Due Date"
				value="${remediationTracker.dueDate}" />
			<liferay-ui:search-container-column-text name="Status"
				value="${remediationTracker.status}" />
			<%
				if (hasAccessOrgRole || hasAccessRPA) {
			%>
			<liferay-ui:search-container-column-text name="Documents">
				<div class="uploadDownloadRFI">
					<aui:button cssClass="slideButtonUpload" value="Upload" />
					<div class="importCSVdivUpload">
						<div class="importFilesRFI">
							<aui:input class="importFile_rfi" type="file"
								name="uploadedFile${remediationTracker.remediationTrackerId}"
								multiple="<%=true %>" label=""></aui:input>
							<input class="importFileRFI_button" type="button" value="Import"
								onclick="callUploadAction('<%=remediationTracker.getRemediationTrackerId()%>')">

						</div>
					</div>
					<%
						boolean hasDocs = remediationTrackerDocsMap
													.get(remediationTracker.getRemediationTrackerId());
					%>
					<c:if test="<%=hasDocs%>">
						<div class="downloadFilesRFI">
							<a
								onclick="callDownloadAction('<%=remediationTracker.getRemediationTrackerId()%>')">Download</a>
						</div>
					</c:if>
				</div>
			</liferay-ui:search-container-column-text>
			<liferay-ui:search-container-column-text name="Action">
				<%
					if (hasAccessRPA) {
				%>
				<div class="uploadDownloadRFI">
					<input type="button" value="Delete"
						onclick="openPopUpRemediationTracker('<%=remediationTracker.getRemediationTrackerId()%>','<%=remediationTracker.getTitle()%>')">
					<input type="button" value="Edit"
						onclick="callEditAction('<%=remediationTracker.getRemediationTrackerId()%>')">
				</div>
				<%
					} else {
											if (remediationTracker.getStatus().toLowerCase().equals("open")
													&& hasAccessOrgRole) {
				%>
				<input type="button" value="Update Status"
					onclick="callUpdateStatusAction('<%=remediationTracker.getRemediationTrackerId()%>')">
				<%
					}
										}
				%>

			</liferay-ui:search-container-column-text>
			<%
				}
			%>
		</liferay-ui:search-container-row>
		<liferay-ui:search-iterator paginate="<%=true%>" markupView="lexicon" />
	</liferay-ui:search-container>

</aui:form>

<aui:form action="<%=deleteRemediationTracker%>" method="post"
	name="deleteRemediationTracker">
	<aui:input id="submitType" name="submitType" type="hidden" value="" />
	<aui:input id="mid" name="mid" type="hidden" value="" />
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
				onclick="updateStatusActivity('<%=updateStatusURL.toString()%>')">
		</aui:form>
	</div>
</div>

<style>
h4 {
	display: inline;
}
</style>

<div id="popupDeleteRemediationTracker" class="popupOrganisation1"
	style="visibility: hidden;">
	<div class="popupp">
		<div class="popUpContentTitle">
			<h2>Delete Record</h2>
			<a class="close" onclick="closePopUpRemediationTracker()"><i class="icon-remove"></i></a>
		</div>



		<div class="popupContent">
			<div>
				<span id="remediationTrackerLabel"></span>
			</div>
			<div>
				<br>
			</div>
			<div>
				<button class="importFile_rfi btn btn-secondary"
					onclick="closePopUpRemediationTracker()" type="button">Cancel</button>
				<button class="btn deleteRemediationTracker"
					onclick="callDeleteAction()" type="button">Delete Record</button>
			</div>
		</div>
	</div>
</div>


<liferay-ui:error key="fileEmpty" message="The file is empty" />
<liferay-ui:error key="errorImporting"
	message="Error importing the XLXS file" />
<liferay-ui:error key="noFile" message="No file selected" />
<liferay-ui:error key="incorrectFormat"
	message="The file format is incorrect" />
	<liferay-ui:error key="invalidStatus" message="The file contains an invalid status" />	
	<liferay-ui:success key="success" message="Your request completed successfully."
/>
<liferay-ui:success key="successFile" message="File upload successful."
/>