<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.theme.ThemeDisplay"%>
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

<portlet:actionURL name="addFileSchema" var="addFileSchema" />
<portlet:actionURL name="updateSchema" var="updateSchema" />

<portlet:actionURL name="addFile" var="addFile" />

<style>
.subnav-tbar-primary {
	display: none !important;
}
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<!-- Session messages -->
<liferay-ui:success key="success-message" message="Your file has been uploaded successfully" />

<liferay-ui:error key="error-default" message="Incorrect JSON" />
<liferay-ui:error key="error-validation" message="ERROR: Validation message" />
<liferay-ui:error key="error-empty" message="Please select a file" />
<liferay-ui:error key="error-no-json" message="Please note that only json files are allowed" />
<liferay-ui:error key="error-duplicate" message="Configuration file already exists" />

<script type="text/javascript" >

	
	function callSchemaFileManagementUpdate(docConfname) {
		document.getElementById("<portlet:namespace/>DocConfName").value = docConfname;
		document.getElementById("<portlet:namespace/>Action").value = "Update";
	
		submitForm(document.<portlet:namespace />schemaFileManagement);
	}
	function callSchemaFileManagementDelete(docConfname) {
		document.getElementById("<portlet:namespace/>DocConfName").value = docConfname;
		document.getElementById("<portlet:namespace/>Action").value = "Delete";

		submitForm(document.<portlet:namespace />schemaFileManagement);
	}
	function callSchemaFileManagementDownload( uId, groupId) {
		window.location.href = "/c/document_library/get_file?uuid=" + uId
				+ "&groupId=" + groupId;
	}
</script>
<script type="text/javascript">

$(document).ready(function() {
	$(".slideButtonJSON").click(function() {
		$(".importJSONdiv").toggle("slide");
		$(this).hide();
	});

	$(".cancelJSONbtn").click(function() {
		$(".importJSONdiv").toggle("slide");
		$(".slideButtonJSON").show();
	});

	$(".hideUpdateDivJSON").click(function() {
		var elementId =  $(this).attr("data-id"); 
		var updateJsonD = ".updateJSONdiv" + elementId;
		var downloadSchema = ".downloadSchema" + elementId;
		var deleteSchema =  ".deleteSchema" + elementId;
		$(updateJsonD).removeClass("hide");
		$(downloadSchema).hide();
		$(deleteSchema).hide();
		$(this).hide();
	});

	$(".cancelJSONbtn2").click(function() {
		var elementId =  $(this).attr("data-id"); 
		var updateJsonD = ".updateJSONdiv" + elementId;
		var downloadSchema = ".downloadSchema" + elementId;
		var deleteSchema =  ".deleteSchema" + elementId;
		var hideUpdateDiv =  ".hideUpdateDivJSON" + elementId;
		$(hideUpdateDiv).show();
		$(updateJsonD).addClass("hide");
		$(downloadSchema).show();
		$(deleteSchema).show();
	});
});


</script>
<%
	Long folderIdFromSession = 0L;
	folderIdFromSession = (Long) renderRequest.getAttribute("folderId");
%>
<liferay-portlet:actionURL name="schemaFileManagement"
	var="schemaFileManagement">
	<liferay-portlet:param name="param" value="" />
	<liferay-portlet:param name="param" value="" />

</liferay-portlet:actionURL>

<div class="importMainDiv">
	<div class="importJSONdiv">
		<aui:form cssClass="importCSVform" action="<%=addFileSchema%>" method="post" name="addFileSchema" enctype="multipart/form-data">

			<input type="file" class="JSONDataFile" name='jsonSchemaFile' value="Select a file" label="Select the file to import" />
			<aui:button cssClass="importJSONbtn" type="submit" value="Import File" />
			<aui:button cssClass="cancelJSONbtn" type="button" value="Cancel" />
		</aui:form>
	</div>
	<aui:button cssClass="slideButtonJSON" value="Import Json File" />
</div>

<liferay-ui:search-container
	emptyResultsMessage="rec.activity.empty-results" total="${fileSchemasCount}"
	deltaConfigurable="false" delta="50">
	<liferay-ui:search-container-results results="${fileSchemas}" />
	<liferay-ui:search-container-row
		className="rec.file.conf.service.model.File_Conf"
		modelVar="fileSchemas">


		<liferay-ui:search-container-column-text name="Schema Name"
			value="${fileSchemas.getDocConfName()}" />

		<liferay-ui:search-container-column-text name="Schema Type"
			value="${fileSchemas.getDocFileType()}" />
		<liferay-ui:search-container-column-text name="Upload Date"
			value="${fileSchemas.getModifiedDate()}" />
		<liferay-ui:search-container-column-text>

			<%
				String uID = "";
							DLFileEntry file = DLFileEntryLocalServiceUtil.fetchFileEntry(themeDisplay.getScopeGroupId(),
									folderIdFromSession, fileSchemas.getDocConfName());
							if (Validator.isNotNull(file)) {
								uID = file.getUuid();
			%>
			<div class="buttonsDiv">
					
				<aui:form cssClass="updateSchema" action="<%=updateSchema%>" method="post" name="updateSchema${fileSchemas.getDocConfigId()}" enctype="multipart/form-data">
					<aui:input id="DocConfId" name="DocConfId" type="hidden" value="<%=fileSchemas.getDocConfigId()%>" />
		
					
					<aui:button cssClass="hideUpdateDivJSON"  data-id="${fileSchemas.getDocConfigId()}" value="Update" />
					
						<div class="updateJSONdiv${fileSchemas.getDocConfigId()} hide">
							<input type="file" class="JSONDataFile" name='jsonSchemaFileUpdate' value="Select a file" label="Select the file to import" />
							<aui:button cssClass="importJSONbtn2" type="submit" value="Update File" />
							<aui:button cssClass="cancelJSONbtn2" type="button" data-id="${fileSchemas.getDocConfigId()}" value="Cancel" />
						</div>
				</aui:form>
				
					
			<input type="button" class= "downloadSchema${fileSchemas.getDocConfigId()}" value="Download"
				onclick="callSchemaFileManagementDownload('<%=uID%>','<%=themeDisplay.getScopeGroupId()%>')">
				
				<input type="button" class= "deleteSchema${fileSchemas.getDocConfigId()}" value="Delete"
				onclick="callSchemaFileManagementDelete('<%=fileSchemas.getDocConfName()%>')">
				</div>
			<%
				} else {
			%>
			<input type="submit" value="Delete Info"
				onclick="callSchemaFileManagementDelete('<%=fileSchemas.getDocConfName()%>')">
			<p>File has been deleted</p>
		
			<%
				}
			%>

		</liferay-ui:search-container-column-text>

	</liferay-ui:search-container-row>
	<liferay-ui:search-iterator />
</liferay-ui:search-container>

<aui:form action="<%=schemaFileManagement%>" method="post"
	name="schemaFileManagement">
	<aui:input id="DocConfName" name="DocConfName" type="hidden" value="" />
	<aui:input id="Action" name="Action" type="hidden" value="" />

</aui:form>

