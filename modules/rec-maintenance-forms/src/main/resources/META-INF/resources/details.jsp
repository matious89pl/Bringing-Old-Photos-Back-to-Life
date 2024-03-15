<!--  ********Author Details******** -->
<!--  Manish Kumar Jaiswal -->
<!--  ********Author Details******** -->

<%@page import="rec.everis.forms.service.model.MaintenanceActivityForms"%>
<%@ include file="init.jsp"%>
<%@page	import="rec.everis.forms.service.service.MaintenanceActivityFormsLocalServiceUtil"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@page import="javax.portlet.PortletURL"%>


<%@page import="com.liferay.portal.kernel.service.GroupLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.model.Group"%>
<%@page import="com.liferay.portal.kernel.util.StringUtil"%>
<%@page import="com.liferay.portal.kernel.service.OrganizationLocalServiceUtil"%>


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
		document.getElementById("<portlet:namespace/>submitDocType").value = "upload";
		submitForm(document.<portlet:namespace />uploadURL);
	}

	function callDownloadAction(uid) {
		document.getElementById("<portlet:namespace/>uid").value = uid;
		document.getElementById("<portlet:namespace/>submitDocType").value = "download";
		submitForm(document.<portlet:namespace />uploadURL);
	}
	
</script>


<% String aggregateFormView = portletPreferences.getValue("aggregateFormView", "local");%>

<h2>RPA - Annual Maintenance - Table</h2>

<portlet:actionURL name="uploadDocument" var="uploadURL"></portlet:actionURL>

<liferay-portlet:renderURL varImpl="iteratorURL">
	<portlet:param name="mvcPath" value="/details.jsp" />
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
	<aui:input id="submitDocType" name="submitDocType" type="hidden" value="" />
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
	        %>  <liferay-ui:search-container-column-text name="Annual Maintainance Form Type" value="${maintenanceActivityForms.formType}" />
	        	<liferay-ui:search-container-column-text name="Year" value="${maintenanceActivityForms.year}" />
	        	<liferay-ui:search-container-column-text name="Submitted Date" value="${maintenanceActivityForms.submitDateFormated}" />
	            <liferay-ui:search-container-column-text name="Approved/Rejected Date" value="${maintenanceActivityForms.approvalDateFormatted}" />
	            <liferay-ui:search-container-column-text name="Status" value="${maintenanceActivityForms.status}" />
			
				<liferay-ui:search-container-column-text name="Documents" >
					<div class="uploadDownloadRFI" >
						<aui:button cssClass="slideButtonUpload" value="Upload" />
						<div class="importCSVdivUpload">
							<div class="importFilesRFI"> 
								<aui:input class="importFile_rfi" type="file" name="uploadedFile${maintenanceActivityForms.remediationTrackerId}" multiple="<%=true %>" label=""></aui:input>
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
	        	 
       	</liferay-ui:search-container-row>
        <liferay-ui:search-iterator paginate="<%=true%>"  markupView="lexicon" />
    </liferay-ui:search-container>

</aui:form>


















<aui:form action="<%=uploadURL%>" method="post" enctype="multipart/form-data" name="uploadURL">
	<aui:input id="submitDocType" name="submitDocType" type="hidden" value="" />
	<aui:input id="uid" name="uid" type="hidden" value="" />


<liferay-ui:search-container emptyResultsMessage="there-are-no-records" headerNames="specificParty,activityTitle,dueDate,status" iteratorURL="<%=iteratorURL%>" delta="4" deltaConfigurable="true">
	<liferay-ui:search-container-results>
		<%
		List<MaintenanceActivityForms> maintenanceActivityFormsList = new ArrayList<MaintenanceActivityForms>();
		if(StringUtil.equals(aggregateFormView.toLowerCase(), "yes")){ 
 			maintenanceActivityFormsList = MaintenanceActivityFormsLocalServiceUtil.findByGroupId(themeDisplay.getScopeGroupId());
		}
		else{
 			maintenanceActivityFormsList = MaintenanceActivityFormsLocalServiceUtil.getMaintenanceActivityFormses(-1, -1);
		}
		results = ListUtil.subList(maintenanceActivityFormsList, searchContainer.getStart(), searchContainer.getEnd());
		searchContainer.setTotal(maintenanceActivityFormsList.size());
		searchContainer.setResults(results);
		%>
	</liferay-ui:search-container-results>



	<liferay-ui:search-container-row className="MaintenanceActivityForms"	keyProperty="maintenanceactivityformId"	modelVar="currentObjectMaintenanceActivityForms">

			<%if(StringUtil.equals(aggregateFormView.toLowerCase(), "yes")){ 
	        	Group group = GroupLocalServiceUtil.fetchGroup(currentObjectMaintenanceActivityForms.getOrgSiteId()); 
	        	if(group != null){ %>
	        		<liferay-ui:search-container-column-text name="Party"><a href="/group<%=group.getFriendlyURL() %>"><%=group.getName() %></a></liferay-ui:search-container-column-text>
	        <%	} else { %>
	        		<liferay-ui:search-container-column-text name="Party">NULL</liferay-ui:search-container-column-text>
	        <%		
	        	}
	          } 
	        %> 
		<liferay-ui:search-container-column-text href="<%= currentObjectMaintenanceActivityForms.getViewUrl() %>" 	name="Annual Maintainance Form Type" property="formType"  />
		<liferay-ui:search-container-column-text href="<%= currentObjectMaintenanceActivityForms.getViewUrl() %>" name="Year" property="year" />
		<liferay-ui:search-container-column-text  href="<%= currentObjectMaintenanceActivityForms.getViewUrl() %>" name="Submitted Date">${currentObjectMaintenanceActivityForms.submitDateFormatted}</liferay-ui:search-container-column-text>
		<liferay-ui:search-container-column-text href="<%= currentObjectMaintenanceActivityForms.getViewUrl() %>" name="Approved/Rejected Date">${currentObjectMaintenanceActivityForms.approvalDateFormatted}</liferay-ui:search-container-column-text>
		<liferay-ui:search-container-column-text href="<%= currentObjectMaintenanceActivityForms.getViewUrl() %>" name="Status" property="status" />
		<liferay-ui:search-container-column-text name="Documents" >
				<div class="uploadDownloadRFI" >
					<aui:button cssClass="slideButtonUpload" value="Upload" />
					<div class="importCSVdivUpload">
						<div class="importFilesRFI"> 
							<aui:input class="importFile_rfi" type="file" name="uploadedFile${currentObjectMaintenanceActivityForms.getMaintenanceactivityformId()}" multiple="<%=true %>" label=""></aui:input>
							<input class="importFileRFI_button" type="button" value="Import" onclick="callUploadAction('<%=currentObjectMaintenanceActivityForms.getMaintenanceactivityformId()%>', )">
						</div>						
					</div>
<%-- 					<%boolean hasDocs = formsDocsMap.get(rfiLog.getRfilogId());%> --%>
<%-- 					 <c:if test="<%=hasDocs%>"> --%>
<!-- 						<div class="downloadFilesRFI"> -->
<%-- 							<a onclick="callDownloadAction('<%=rfiLog.getRfilogId()%>')">Download</a> --%>
<!-- 						</div> -->
<%-- 					</c:if> --%>
				</div>							
        	</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>
	<liferay-ui:search-iterator searchContainer="<%=searchContainer%>" />
</liferay-ui:search-container>
</aui:form>
