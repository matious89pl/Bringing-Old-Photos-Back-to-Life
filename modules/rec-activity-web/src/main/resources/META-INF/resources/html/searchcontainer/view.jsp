<%@ include file="/html/searchcontainer/init.jsp"%>
<%@page	import="com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil"%>
<%@page	import="com.liferay.portal.kernel.model.UserGroupRole"%>
<%@page import="com.liferay.portal.kernel.service.RoleLocalServiceUtil"%>

<%@page import="java.util.List"%>
<%@page	import="com.everis.rec.maintenanceactivity.model.MaintenanceActivity"%>
<%@page import="com.liferay.portal.kernel.util.StringUtil"%>

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

	function callDeleteAction(url, idd) {
		document.getElementById("<portlet:namespace/>mid").value = idd;
		document.getElementById("<portlet:namespace/>submitType").value = "delete";
		submitForm(document.<portlet:namespace />deleteActivity);
	}

	function callEditAction(url, idd) {
		document.getElementById("<portlet:namespace/>mid").value = idd;
		document.getElementById("<portlet:namespace/>submitType").value = "edit";
		submitForm(document.<portlet:namespace />deleteActivity); 
	}
	
	function callUpdateStatusAction(idd){
		var pop = document.getElementById("rpa_updateStatus_popup");
		pop.style.display = "block"; 
		pop.style.opacity = "1";
		pop.style.visibility = "visible";
		document.getElementById("<portlet:namespace/>updateStatusId").value = idd;
	}
	
	function updateStatusActivity(url){
		var newStatus = $("#select_rpa_status").val();
		document.getElementById("<portlet:namespace/>updateStatusVal").value = newStatus;
		submitForm(document.<portlet:namespace />updateStatus);
	}
	
	function closeForm() { 
		var pop = document.getElementById("rpa_updateStatus_popup");
		pop.style.display = "none"; 
	} 
	
	
</script>

<portlet:actionURL name="importAnualMaintenance"
	var="importAnualMaintenanceURL">
	<portlet:param name="type" value="AnualMaintenance" />   
	<portlet:param name="userId" value="<%=String.valueOf(themeDisplay.getUserId())%>" />
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
//portletURL.setParameter("orderByCol", "urlTitle");
//portletURL.setParameter("mvcRenderCommandName", "aggregateView");

String classCard = GetterUtil.getString(portletPreferences.getValue("classCard", "col-md-12 col-sm-12 col-xs-12"), "");
String aggregateView = (String) renderRequest.getPortletSession().getAttribute("aggregateView", PortletSession.APPLICATION_SCOPE);
%>
<!-- PERMISSION CHECKS -->
<%
	boolean hasAccessOrgRole = false;
	
	List<UserGroupRole> userGroupRoles = UserGroupRoleLocalServiceUtil.getUserGroupRoles(themeDisplay.getUserId(), themeDisplay.getScopeGroupId());
	for (UserGroupRole userGroupRole : userGroupRoles) {
		if (userGroupRole.getRole().getName().equals("REC Contract Managers") || userGroupRole.getRole().getName().equals("Master Administrative User") || userGroupRole.getRole().getName().equals("REC_Performance_Assurance")){
			hasAccessOrgRole = true;
		}
	}

	boolean hasAccessRPA = false;
	if (RoleLocalServiceUtil.hasUserRole(themeDisplay.getUserId(), themeDisplay.getCompanyId(), "RPA", false) || RoleLocalServiceUtil.hasUserRole(themeDisplay.getUserId(), themeDisplay.getCompanyId(), "Portal_Admin", false)) {
		hasAccessRPA = true;
	}	
%>

<a class="rpa_newActivity" href="${addActivity}">Add New Record</a>

<%
	if (hasAccessRPA) {
%>
<div class="importMainDiv">
	<div class="importCSVdiv">
		<aui:form cssClass="importCSVform"
			action="<%=importAnualMaintenanceURL%>" method="post" name="fm"
			enctype="multipart/form-data">
			<input type="file" name='csvDataFile' class="csvDataFile"
					value="Select a file" label="Select the file to import" />
			<aui:button cssClass="importCSVbtn" type="submit" value="Import XLXS"/>
			<aui:button cssClass="cancelCSVbtn" type="button" value="Cancel" />
		</aui:form>
	</div>
	
	<aui:button cssClass="slideButton" value="Load XLXS " />
</div>

<%
	}
%>
<br><br>

<liferay-portlet:renderURL varImpl="iteratorURL">
	<portlet:param name="mvcPath" value="/html/searchcontainer/view.jsp" />
</liferay-portlet:renderURL>

<div class="container-fluid-1280">
    <%-- Clay management toolbar. --%>
    <clay:management-toolbar
        disabled="${activityListCount eq 0}"
        displayContext="${assignmentsManagementToolbarDisplayContext}"
        itemsTotal="${activityListCount}"
        searchContainerId="activityEntries"
        selectable="false"	     
    />
</div>
<%
List<MaintenanceActivity> maintenanceActivityList = (List<MaintenanceActivity>) renderRequest.getPortletSession().getAttribute("maintenanceActivityList", PortletSession.APPLICATION_SCOPE);
long maintenanceActivityListCount = (long)renderRequest.getPortletSession().getAttribute("maintenanceActivityListCount", PortletSession.APPLICATION_SCOPE);
%>
<c:if test='${assignmentsManagementToolbarDisplayContext.getDisplayStyle().equals("icon")}'>
   <div class="row align-items-lg-center align-items-sm-center align-items-center align-items-md-center flex-lg-row flex-sm-column-reverse flex-column-reverse flex-md-column-reverse">
</c:if>
    <%-- Search container. --%>
    <liferay-ui:search-container emptyResultsMessage="rec.activity.empty-results" id="activityEntries" iteratorURL="${portletURL}" total="${maintenanceActivityListCount}">
        <liferay-ui:search-container-results> 
        <%
         pageContext.setAttribute("results", maintenanceActivityList);
         pageContext.setAttribute("total", maintenanceActivityListCount);
      %>
        </liferay-ui:search-container-results>
        <liferay-ui:search-container-row className="com.everis.rec.maintenanceactivity.model.MaintenanceActivity" modelVar="activity"> 
        
        <%if(StringUtil.equals(aggregateView.toLowerCase(), "yes")){ %>
             <liferay-ui:search-container-column-text name="Party"> <a href="123" target="_blank"> ${activity.specificParty} </a></liferay-ui:search-container-column-text>
        <%} %>     
             
			<liferay-ui:search-container-column-text name="Due Date" value="${activity.dueDate}" />
			<liferay-ui:search-container-column-text name="Activity" value="${activity.activityTitle}" />
			<liferay-ui:search-container-column-text name="Status" value="${activity.status}" />
			<%			
				if (hasAccessRPA){
			%>
			<liferay-ui:search-container-column-text name="Action"> 
			<input type="button" value="Delete"	onclick="callDeleteAction('<%=deleteActivity.toString()%>','<%=activity.getMaintenanceactivityId()%>')">
			<input type="button" value="Edit" 	onclick="callEditAction('<%=deleteActivity.toString()%>','<%=activity.getMaintenanceactivityId()%>')"> </liferay-ui:search-container-column-text>
			<%
			} else { 
					if (activity.getStatus().equals("Open") && hasAccessOrgRole){						
			%>				
			<liferay-ui:search-container-column-text name="Action" ><input type="button" value="Update Status"
				onclick="callUpdateStatusAction('<%=activity.getMaintenanceactivityId()%>')"> </liferay-ui:search-container-column-text>
			<%
					}
				}
			%>
			
        </liferay-ui:search-container-row>
        <%-- Iterator / Paging --%>
        <liferay-ui:search-iterator displayStyle="${assignmentsManagementToolbarDisplayContext.getDisplayStyle()}" markupView="lexicon"  />
    </liferay-ui:search-container>
<c:if test='${assignmentsManagementToolbarDisplayContext.getDisplayStyle().equals("icon")}'>
   </div>
</c:if>

<aui:form action="<%=deleteActivity%>" method="post" name="deleteActivity">
	<aui:input id="submitType" name="submitType" type="hidden" value="" />
	<aui:input id="mid" name="mid" type="hidden" value="" />
</aui:form>


<div id="rpa_updateStatus_popup" class="overlaySolutions"> 
	<div class="popupSolutions" id="div_rpa_popup"> 
	<aui:form action="<%=updateStatusURL%>" method="post" name="updateStatus">
	<aui:input id="updateStatusId" name="updateStatusId" type="hidden" value="" />
	<aui:input id="updateStatusVal" name="updateStatusVal" type="hidden" value="" />
		<h2>Update Status</h2> 
		<a class="close" href="#" onclick="closeForm()">&times;</a>
		<select id="select_rpa_status">
			<option>Pending</option>
		</select>
		<input type="button" id="rpa_updateStatusButton" value="Update Status" onclick="updateStatusActivity('<%=updateStatusURL.toString()%>')">
	</aui:form>	
	</div> 
</div>