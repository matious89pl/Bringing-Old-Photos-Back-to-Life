<%@ include file="init.jsp"%>

<p>
	<b><liferay-ui:message key="testama.caption.add" /></b>
</p>
<portlet:actionURL name="addValidationLogSubmit"
	var="addValidationLogSubmit" />

<aui:form action="<%=addValidationLogSubmit%>" method="post"
	name="addValidationLogSubmit">



	<!-- File Name -->
	<aui:input name="fileName" label="File Name" style="width:500px">
		<aui:validator name="required" />
		<aui:validator name="maxLength">500</aui:validator>
	</aui:input>


	<!-- Status -->
	<aui:input name="uploadedBy" label="Uploaded By" style="width:500px">
		<aui:validator name="required" />
		<aui:validator name="maxLength">500</aui:validator>
	</aui:input>

	<aui:input name="uploadedFrom" label="Uploaded From"
		style="width:500px">
		<aui:validator name="required" />
		<aui:validator name="maxLength">500</aui:validator>
	</aui:input>

	<aui:input name="folderId" label="Folder Id" style="width:500px">
		<aui:validator name="required" />
		<aui:validator name="number" />
	</aui:input>

	<aui:input type="textarea" name="logReason" label="Log Reason"
		style="width:500px">
		<aui:validator name="required" />
		<aui:validator name="maxLength">500</aui:validator>
	</aui:input>

	<aui:button class="btn btn-success" name="Submit" type="submit" />

</aui:form>
