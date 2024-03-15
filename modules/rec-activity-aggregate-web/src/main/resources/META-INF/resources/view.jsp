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
	import="com.everis.rec.maintenanceactivity.model.MaintenanceActivity"%>
<%@page
	import="com.everis.rec.activity.aggretate.constants.RecActivityAggregateWebPortletKeys"%>

<style>
.subnav-tbar-primary {
	display: none;
	!
	important;
}
</style>

<script>
	$(document).ready(function() {
		$(".slideButton").click(function() {
			$(".importCSVdiv").toggle("slide");
			$(this).hide();
		});

		$(".cancelCSVbtn").click(function() {
			$(".importCSVdiv").toggle("slide");
			$(".slideButton").show();
		});

	});

	
	function callDeleteAction() {
		submitForm(document.<portlet:namespace />deleteActivity);

		Liferay.Portlet.refresh("<portlet:namespace />");

	}
	function openPopUpDeleteActivity(url, idd) {
		$("#ActivityContentDelete").html(
				"Are you sure you want to delete this record?")
		$("#popupDeleteActivity").css("visibility", "visible");
		document.getElementById("<portlet:namespace/>mid").value = idd;
		document.getElementById("<portlet:namespace/>submitType").value = "delete";

	}
	function closePopUpDeleteActivity() {
		$("#popupDeleteActivity").css("visibility", "hidden");
		$("#selector").text("");
	}

	function callEditAction(url, idd) {
		document.getElementById("<portlet:namespace/>mid").value = idd;
		document.getElementById("<portlet:namespace/>submitType").value = "edit";
		submitForm(document.<portlet:namespace />deleteActivity);
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
</script>
<%
	String aggregateView = portletPreferences.getValue("aggregateView", "no");
%>
<portlet:actionURL name="importAnualMaintenance"
	var="importAnualMaintenanceURL">
	<portlet:param name="type" value="AnualMaintenance" />
	<portlet:param name="userId"
		value="<%=String.valueOf(themeDisplay.getUserId())%>" />
</portlet:actionURL>
<portlet:actionURL name="updateStatus" var="updateStatusURL" />
<portlet:actionURL name="addActivity" var="addActivity" />
<portlet:actionURL name="redirectToAggregate" var="redirectToAggregate" />

<liferay-portlet:actionURL name="deleteActivity" var="deleteActivity">
	<liferay-portlet:param name="param" value="" />
</liferay-portlet:actionURL>
<%
	PortletURL portletURL = renderResponse.createRenderURL();
	portletURL.setPortletMode(PortletMode.VIEW);
	portletURL.setWindowState(WindowState.NORMAL);
	portletURL.setParameter("mvcRenderCommandName", RecActivityAggregateWebPortletKeys.VIEW_LANDING);
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
		if (StringUtil.equals(aggregateView.toLowerCase(), "yes")) {
%>
<div class="importMainDiv">
	<div class="importCSVdiv">
		<aui:form cssClass="importCSVform"
			action="<%=importAnualMaintenanceURL%>" method="post" name="fm"
			enctype="multipart/form-data">
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
	<a class="rpa_newActivity" href="${addActivity}">Add New Record</a>
</div>
<%
	}
	}
%>

<clay:management-toolbar disabled="<%=false %>"
	displayContext="${assignmentsManagementToolbarDisplayContext}"
	itemsTotal="${activityListCount}" searchContainerId="activityEntries"
	selectable="false" namespace="<%= renderResponse.getNamespace() %>"
	showResultsBar="<%= false%>" searchInputName="keywords"
	searchValue="<%= ParamUtil.getString(request, "keywords") %>"
	clearResultsURL="${assignmentsManagementToolbarDisplayContext.getClearResultsURL()}"
	componentId="assignmentsManagementToolbar" searchFormName="searchFm"
	sortingOrder="${assignmentsManagementToolbarDisplayContext.getOrderByType()}"
	sortingURL="${assignmentsManagementToolbarDisplayContext.getSortingURL()}" />

<liferay-ui:search-container
	emptyResultsMessage="rec.activity.empty-results" id="activityEntries"
	iteratorURL="${portletURL}" total="${maintenanceActivityListCount}"
	deltaConfigurable="false" delta="50">
	<liferay-ui:search-container-results
		results="${maintenanceActivityList}" />
	<liferay-ui:search-container-row
		className="com.everis.rec.maintenanceactivity.model.MaintenanceActivity"
		modelVar="activity">
		<%
			if (StringUtil.equals(aggregateView.toLowerCase(), "yes")) {
						Group group = GroupLocalServiceUtil.fetchGroup(activity.getOrgSiteId());
						if (group != null) {
		%>
		<liferay-ui:search-container-column-text name="Party">
			<a href="/group<%=group.getFriendlyURL()%>"><%=activity.getSpecificParty()%></a>
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
			value="${activity.dueDate}" />
		<liferay-ui:search-container-column-text name="Activity"
			value="${activity.activityTitle}" />
		<liferay-ui:search-container-column-text name="Status"
			value="${activity.status}" />
		<%
			if (hasAccessRPA || hasAccessOrgRole) {
		%>
		<liferay-ui:search-container-column-text name="Action">
			<%
				if (hasAccessRPA) {
			%>
			<input type="button" value="Delete"
			onclick="openPopUpDeleteActivity('<%=deleteActivity.toString()%>','<%=activity.getMaintenanceactivityId()%>')" > 
			<input type="button" value="Edit"
				onclick="callEditAction('<%=deleteActivity.toString()%>','<%=activity.getMaintenanceactivityId()%>')">
			<%
				} else {
									if (activity.getStatus().equals("Open") && hasAccessOrgRole) {
			%>
			<input type="button" value="Update Status"
				onclick="callUpdateStatusAction('<%=activity.getMaintenanceactivityId()%>')">
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

<aui:form action="<%=deleteActivity%>" method="post"
	name="deleteActivity">
	<aui:input id="submitType" name="submitType" type="hidden" value="" />
	<aui:input id="mid" name="mid" type="hidden" value="" />
</aui:form>

<div id="popupDeleteActivity" class="popupOrganisation1"
		style="visibility: hidden;">
		<div class="popupp">
			<div class="popUpContentTitle">
				<h2>Delete Record</h2>
				<a class="close" href="#" onclick="closeUpdateStatus()"><i class="icon-remove"></i></a>
				<a class="close" onclick="closePopUpDeleteActivity()"></a>
			</div>

			<div class="popupContent">
				<div>
					<span id="ActivityContentDelete"></span>
				</div>
				<div>
					<br>
				</div>
				<div>
					<button class="importFile_rfi btn btn-secondary"
						onclick="closePopUpDeleteActivity()" type="button">Cancel</button>
					<button class="btn deleteActivity" onclick="callDeleteAction()"
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

<liferay-ui:error key="invalidStatus" message="The file contains an invalid status" />	
<liferay-ui:success key="success" message="Your request completed successfully."
/>
