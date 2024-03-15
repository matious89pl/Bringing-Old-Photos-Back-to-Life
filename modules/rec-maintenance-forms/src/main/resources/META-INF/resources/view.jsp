<!--  ********Author Details******** -->
<!--  Manish Kumar Jaiswal -->
<!--  ********Author Details******** -->

<%@page import="rec.everis.forms.service.model.MaintenanceActivityForms"%>
<%@ include file="init.jsp"%>
<%@page	import="rec.everis.forms.service.service.MaintenanceActivityFormsLocalServiceUtil"%>
<%@page import="java.util.List"%>
<%@page import="javax.portlet.PortletURL"%>


<%@page import="com.liferay.portal.kernel.service.GroupLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.model.Group"%>
<%@page import="com.liferay.portal.kernel.util.StringUtil"%>
<%@page import="com.liferay.portal.kernel.service.OrganizationLocalServiceUtil"%>
<%@ page import="com.liferay.portal.kernel.util.Validator"%>
<%@ page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ page import="com.liferay.portal.kernel.util.Constants"%>
<%@ page import="com.liferay.portal.kernel.util.HtmlUtil"%>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil"%>

<%@page import="com.liferay.portal.kernel.model.UserGroupRole"%>
<%@page import="com.liferay.portal.kernel.model.Group"%>
<%@page import="com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.service.GroupLocalServiceUtil"%>	
<%@page import="com.liferay.portal.kernel.service.RoleLocalServiceUtil"%>



<%@page import="java.util.Map"%>


<style>
.lfr-pagination-config {
	display: none
}
</style>


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

	

	function callUploadAction(uid) {
		document.getElementById("<portlet:namespace/>uid").value = uid;
		submitForm(document.<portlet:namespace />uploadURL);
	}

	function callDownloadAction(uid) {
		document.getElementById("<portlet:namespace/>uidDownload").value = uid;
		submitForm(document.<portlet:namespace />downloadURL);
	}
	
</script>

<!-- PERMISSION CHECKS -->
<%
	boolean hasAccessRECParty = false;

	List<UserGroupRole> userGroupRoles = UserGroupRoleLocalServiceUtil.getUserGroupRoles(themeDisplay.getUserId(), themeDisplay.getScopeGroupId());
	for (UserGroupRole userGroupRole : userGroupRoles) {
		if (userGroupRole.getRole().getName().equals("REC Contract Managers") || userGroupRole.getRole().getName().equals("Master Administrative User") || userGroupRole.getRole().getName().equals("REC_Performance_Assurance")) {
			hasAccessRECParty = true;
		}
	}

	boolean hasAccessRPA = false;
	if (RoleLocalServiceUtil.hasUserRole(themeDisplay.getUserId(), themeDisplay.getCompanyId(), "RPA", false) || RoleLocalServiceUtil.hasUserRole(themeDisplay.getUserId(), themeDisplay.getCompanyId(), "Portal_Admin", false)) {
		hasAccessRPA = true;
	}
%>


<% String aggregateFormView = portletPreferences.getValue("aggregateFormView", "local");
Map<Long, Boolean> maintenanceActivityFormsDocsMap = (Map<Long, Boolean>) renderRequest.getAttribute("maintenanceActivityFormsDocsMap");
%>

<h2>RPA - Annual Maintenance - Table</h2>

<portlet:actionURL name="uploadDocument" var="uploadURL"></portlet:actionURL>

<portlet:actionURL name="downloadDocument" var="downloadURL"></portlet:actionURL>

<liferay-portlet:renderURL varImpl="iteratorURL">
	<portlet:param name="mvcPath" value="/view.jsp" />
</liferay-portlet:renderURL>


   <clay:management-toolbar
        disabled="<%=false %>"
        displayContext="${maintenanceActivityFormsManagementToolbarDisplayContext}"
        itemsTotal="${maintenanceActivityFormsListCount}"
        searchContainerId="maintenanceActivityFormsEntries"
        selectable="false"
        namespace="<%= renderResponse.getNamespace() %>"
        showResultsBar="<%= false%>"
	    searchInputName="keywords"
	    searchValue="<%= ParamUtil.getString(request, "keywords") %>"
	    clearResultsURL="${maintenanceActivityFormsManagementToolbarDisplayContext.getClearResultsURL()}"
        componentId="maintenanceActivityFormsManagementToolbar"
        searchFormName="searchFm"
    /> 
 
 
 <aui:form action="<%=uploadURL%>" method="post" enctype="multipart/form-data" name="uploadURL">
	<aui:input id="uid" name="uid" type="hidden" value="" />
    
	<liferay-ui:search-container emptyResultsMessage="rec.maintenanceActivityForms.empty-results" id="maintenanceActivityFormsEntries" iteratorURL="${portletURL}" total="${maintenanceActivityFormsListCount}" deltaConfigurable="false" delta="50">
     	<liferay-ui:search-container-results results="${maintenanceActivityFormsList}" />
       	<liferay-ui:search-container-row className="rec.everis.forms.service.model.MaintenanceActivityForms" modelVar="maintenanceActivityForms"> 
	        <%if(StringUtil.equals(aggregateFormView.toLowerCase(), "yes")){ 
	        	Group group = GroupLocalServiceUtil.fetchGroup(maintenanceActivityForms.getOrgSiteId()); 
	        	if(group != null){ %>
	        		<liferay-ui:search-container-column-text name="Party"><a href="/group<%=group.getFriendlyURL() %>"><%=group.getName()%></a></liferay-ui:search-container-column-text>
	        <%	} else { %>
	        		<liferay-ui:search-container-column-text name="Party">NULL</liferay-ui:search-container-column-text>
	        <%		
	        	}
	          } 
	        %>  <liferay-ui:search-container-column-text href="<%= maintenanceActivityForms.getViewUrl() %>" name="Annual Maintainance Form Type" value="${maintenanceActivityForms.formType}" />
	        	<liferay-ui:search-container-column-text href="<%= maintenanceActivityForms.getViewUrl() %>" name="Year" value="${maintenanceActivityForms.year}" />
	        	<liferay-ui:search-container-column-text href="<%= maintenanceActivityForms.getViewUrl() %>" name="Submitted Date" value="${maintenanceActivityForms.submitDateFormatted}" />
	            <liferay-ui:search-container-column-text href="<%= maintenanceActivityForms.getViewUrl() %>" name="Approved/Rejected Date" value="${maintenanceActivityForms.approvalDateFormatted}" />
	            <liferay-ui:search-container-column-text href="<%= maintenanceActivityForms.getViewUrl() %>" name="Status" value="${maintenanceActivityForms.status}" />
				<% if (hasAccessRECParty || hasAccessRPA) { %>
				<liferay-ui:search-container-column-text name="Documents" >
					<div class="uploadDownloadRFI" >
						<aui:button cssClass="slideButtonUpload" value="Upload" />
						<div class="importCSVdivUpload">
							<div class="importFilesRFI"> 
								<aui:input class="importFile_rfi" type="file" name="uploadedFile${maintenanceActivityForms.maintenanceactivityformId}" multiple="<%=true %>" label=""></aui:input>
								<input class="importFileRFI_button" type="button" value="Import" onclick="callUploadAction('<%=maintenanceActivityForms.getMaintenanceactivityformId()%>')">
	
							</div>						
						</div>
						<%boolean hasDocs = maintenanceActivityFormsDocsMap.get(maintenanceActivityForms.getMaintenanceactivityformId());%>
						 <c:if test="<%=hasDocs%>">
							<div class="downloadFilesRFI">
								<a onclick="callDownloadAction('<%=maintenanceActivityForms.getMaintenanceactivityformId()%>')">Download</a>
							</div>
						</c:if>
						
					</div>							
        		</liferay-ui:search-container-column-text>
        		 <% } %>	
	        	 
       	</liferay-ui:search-container-row>
        <liferay-ui:search-iterator paginate="<%=true%>"  markupView="lexicon" />
    </liferay-ui:search-container>

</aui:form>

<div class="hidden" > 
	<aui:form action="<%=downloadURL%>" method="post" name="downloadURL">
		<aui:input id="uidDownload" name="uidDownload" type="hidden" value="" />		
	</aui:form>	
</div>
