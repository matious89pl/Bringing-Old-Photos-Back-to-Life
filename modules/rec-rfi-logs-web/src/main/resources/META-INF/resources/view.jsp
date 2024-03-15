<%@ include file="init.jsp"%>

<%@page import="com.liferay.portal.kernel.model.RoleConstants"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>

<%@page import="javax.portlet.PortletURL"%>
<%@page import="javax.portlet.PortletMode"%>
<%@page import="javax.portlet.WindowState"%>

<%@page import="com.liferay.portal.kernel.util.StringUtil"%>
<%@page import="com.liferay.portal.kernel.model.UserGroupRole"%>
<%@page import="com.liferay.portal.kernel.model.Group"%>
<%@page
	import="com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.service.GroupLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.service.RoleLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.dao.search.SearchContainer"%>
<%@page import="com.liferay.portal.kernel.util.StringUtil"%>
<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>




<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui"%>
<%@ taglib prefix="clay" uri="http://liferay.com/tld/clay"%>

<%@page
	import="com.everis.rec.rfi.logs.constants.RecRfiLogsWebPortletKeys"%>


<script type="text/javascript">
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
		submitForm(document.<portlet:namespace />deleteRFI);
		Liferay.Portlet.refresh("<portlet:namespace />");

	}
	

	function callEditAction(idd) {
		document.getElementById("<portlet:namespace/>mid").value = idd;
		document.getElementById("<portlet:namespace/>submitType").value = "edit";
		submitForm(document.<portlet:namespace />deleteRFI);
	}
	function callViewAction(idd) {
		document.getElementById("<portlet:namespace/>midView").value = idd;
		submitForm(document.<portlet:namespace />viewRFI);
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

	function callUpdateStatusAction(idd) {
		var pop = document.getElementById("rpa_updateStatus_popup");
		pop.style.display = "block";
		pop.style.opacity = "1";
		pop.style.visibility = "visible";
		document.getElementById("<portlet:namespace/>updateStatusId").value = idd;
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
	function openPopUpDeleteResource(idd) {
		$("#RFIContentDelete").html(
				"Are you sure you want to delete this record?")
		$("#popupDeleteRFI").css("visibility", "visible");
		document.getElementById("<portlet:namespace/>mid").value = idd;
	}
	function closePopUpDeleteRFI() {
		$("#popupDeleteRFI").css("visibility", "hidden");
		$("#selector").text("");
	}
</script>
<portlet:actionURL name="updateStatus" var="updateStatusURL" />
<portlet:actionURL name="addRFI" var="addRFI" />
<portlet:actionURL name="redirectToAggregate" var="redirectToAggregate" />
<liferay-portlet:actionURL name="deleteRFI" var="deleteRFI">
	<portlet:param name="sortingOrder" value="asc" />
</liferay-portlet:actionURL>
<portlet:actionURL name="importRFI" var="importRFIURL">
	<portlet:param name="type" value="RFI" />
	<portlet:param name="userId"
		value="<%=String.valueOf(themeDisplay.getUserId())%>" />
	<portlet:param name="companyId"
		value="<%=String.valueOf(themeDisplay.getCompanyId())%>" />
</portlet:actionURL>
<portlet:actionURL name="uploadDocument" var="uploadURL"></portlet:actionURL>

<liferay-portlet:actionURL name="viewRFI" var="viewRFI" >

</liferay-portlet:actionURL>

<%
	String aggregateLogView = portletPreferences.getValue("aggregateLogView", "no");
	PortletURL portletURL = renderResponse.createRenderURL();
	portletURL.setPortletMode(PortletMode.VIEW);
	portletURL.setWindowState(WindowState.NORMAL);
	portletURL.setParameter("mvcRenderCommandName", RecRfiLogsWebPortletKeys.VIEW_LANDING);
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
	Map<Long, Boolean> rfiLogsDocsMap = (Map<Long, Boolean>) renderRequest.getAttribute("rfiLogsDocsMap");
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
%>
<%
	//  <a class="aggregateButton" href="${redirectToAggregate}">Aggregate RFI</a>
%>

<%
	if (StringUtil.equals(aggregateLogView.toLowerCase(), "no")) {
		if (hasAccessRPA) {
%>
<a class="rpa_newActivity" href="${addRFI}">Add New Record</a>
<%
	}
	}
%>

<%
	if (hasAccessRPA && StringUtil.equals(aggregateLogView.toLowerCase(), "yes")) {
%>
<div class="importMainDiv">
	<div class="importCSVdiv">
		<aui:form cssClass="importCSVform" action="<%=importRFIURL%>"
			method="post" name="fm" enctype="multipart/form-data">
			<aui:input type="file" name='csvDataFile' class="csvDataFile"
				value="Select a file" label="Select the file to import" />
			<aui:button cssClass="importCSVbtn" type="submit" value="Import XLXS" />
			<aui:button cssClass="cancelCSVbtn" type="button" value="Cancel" />
		</aui:form>
	</div>
	<aui:button cssClass="slideButton" value="Load XLXS " />
</div>
<%
	}
%>

<clay:management-toolbar disabled="<%=false %>"
	displayContext="${rfiLogsAssignmentsManagementToolbarDisplayContext}"
	itemsTotal="${rfiLogsListCount}" searchContainerId="rfiLogsEntries"
	selectable="false" namespace="<%= renderResponse.getNamespace() %>"
	showResultsBar="<%= false%>" searchInputName="keywords"
	searchValue="<%= ParamUtil.getString(request, "keywords") %>"
	clearResultsURL="${rfiLogsAssignmentsManagementToolbarDisplayContext.getClearResultsURL()}"
	componentId="assignmentsManagementToolbar" searchFormName="searchFm"
	sortingURL="${rfiLogsAssignmentsManagementToolbarDisplayContext.getSortingURL()}" />

<aui:form action="<%=uploadURL%>" method="post"
	enctype="multipart/form-data" name="uploadURL">
	<aui:input id="submitDocType" name="submitDocType" type="hidden"
		value="" />
	<aui:input id="uid" name="uid" type="hidden" value="" />


	<liferay-ui:search-container
		emptyResultsMessage="rec.rfi.empty-results" id="rfiLogsEntries"
		iteratorURL="${portletURL}" total="${rfiLogsListCount}"
		deltaConfigurable="false" delta="50">
		<liferay-ui:search-container-results results="${rfiLogsList}" />
		<liferay-ui:search-container-row
			className="com.everis.rec.rfilogs.model.RfiLogs" modelVar="rfiLog">
			<%
				if (StringUtil.equals(aggregateLogView.toLowerCase(), "yes")) {
								Group group = GroupLocalServiceUtil.fetchGroup(rfiLog.getOrgSiteId());
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
			<liferay-ui:search-container-column-text name="Due Date"
				value="${rfiLog.dueDate}" />
			<liferay-ui:search-container-column-text name="REQ ID"
				value="${rfiLog.reqId}" />
			<liferay-ui:search-container-column-text name="Title">
				<a onclick="callViewAction('<%=rfiLog.getRfilogId()%>')">${rfiLog.title}</a>
			</liferay-ui:search-container-column-text>
			<liferay-ui:search-container-column-text name="Status"
				value="${rfiLog.status}" />
			<liferay-ui:search-container-column-text name="Rationale"
				value="${rfiLog.rationale}" />
			<%
				if (hasAccessOrgRole || hasAccessRPA) {
			%>
			<liferay-ui:search-container-column-text name="Documents">
				<div class="uploadDownloadRFI">
					<aui:button cssClass="slideButtonUpload" value="Upload" />
					<div class="importCSVdivUpload">
						<div class="importFilesRFI">
							<aui:input class="importFile_rfi" type="file"
								name="uploadedFile${rfiLog.rfilogId}" multiple="<%=true %>"
								label=""></aui:input>
							<input class="importFileRFI_button" type="button" value="Import"
								onclick="callUploadAction('<%=rfiLog.getRfilogId()%>', )">

						</div>
					</div>
					<%
						boolean hasDocs = rfiLogsDocsMap.get(rfiLog.getRfilogId());
					%>
					<c:if test="<%=hasDocs%>">
						<div class="downloadFilesRFI">
							<a onclick="callDownloadAction('<%=rfiLog.getRfilogId()%>')">Download</a>
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
						onClick="openPopUpDeleteResource('<%=rfiLog.getRfilogId()%>')"> <input
						type="button" value="Edit"
						onclick="callEditAction('<%=rfiLog.getRfilogId()%>')">
				</div>
				<%
					} else {
											if (rfiLog.getStatus().equals("Open") && hasAccessOrgRole) {
				%>
				<input type="button" value="Update Status"
					onclick="callUpdateStatusAction('<%=rfiLog.getRfilogId()%>')">
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



<aui:form action="<%=deleteRFI%>" method="post" name="deleteRFI">
	<aui:input id="submitType" name="submitType" type="hidden" value="" />
	<aui:input id="mid" name="mid" type="hidden" value="" />
	<aui:input id="sortingOrder" name="sortingOrder" type="hidden"
		value="asc" />
	<aui:input id="rfilogId" name="rfilogId" type="hidden" value="" />


</aui:form>
<aui:form action="<%=viewRFI%>" method="post" name="viewRFI">
	<aui:input id="midView" name="midView" type="hidden" value="" />
</aui:form>

<div id="popupDeleteRFI" class="popupOrganisation1"
		style="visibility: hidden;">
		<div class="popupp">
			<div class="popUpContentTitle">
				<h2>Delete Record</h2>
				<a class="close" href="#" onclick="closeUpdateStatus()"><i class="icon-remove"></i></a>
				<a class="close" onclick="closePopUpDeleteRFI()"></a>
			</div>

			<div class="popupContent">
				<div>
					<span id="RFIContentDelete"></span>
				</div>
				<div>
					<br>
				</div>
				<div>
					<button class="importFile_rfi btn btn-secondary"
						onclick="closePopUpDeleteRFI()" type="button">Cancel</button>
					<button class="btn deleteRFI" onclick="callDeleteAction()"
						type="button">Delete Record</button>

				</div>
			</div>
		</div>
	</div>



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
