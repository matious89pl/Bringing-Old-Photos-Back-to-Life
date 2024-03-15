<%@ include file="init.jsp"%>

<p>
	<b><liferay-ui:message key="rfilogsaction.caption.add" /></b>
</p>

<portlet:actionURL name="editActivity" var="editActivity" />
<portlet:actionURL name="addRFISubmit" var="addRFISubmit" />

<aui:form action="<%=addRFISubmit%>" method="post" name="addRFISubmit">

	<!-- ReqId -->
	<aui:input name="reqId" label="REQ ID" style="width:500px">
		<aui:validator name="required" />
		<aui:validator name="digits" />
	</aui:input>

	<!-- Title -->
	<aui:input name="title" label="Title" style="width:500px">
		<aui:validator name="required" />
		<aui:validator name="maxLength">500</aui:validator>
	</aui:input>

	<!-- Due Date -->

	<aui:input name="duedate" type="date" label="Due Date"
		style="width:500px">
		<aui:validator name="date"></aui:validator>
	</aui:input>

	<!-- Status -->
	<aui:fieldset>
		<aui:select label="Status" name="status" style="width:500px">
			<aui:option label="Open" />
			<aui:option label="Pending" />
			<aui:option label="Complete" />
		</aui:select>
	</aui:fieldset>

	<!-- Request Description  -->
	<aui:input name="reqDesc" label="Request Description "
		style="width:500px">
		<aui:validator name="required" />
		<aui:validator name="maxLength">500</aui:validator>
	</aui:input>

	<!-- Rationale  -->
	<aui:input name="rationale" label="Rationale" style="width:500px">
		<aui:validator name="required" />
		<aui:validator name="maxLength">500</aui:validator>
	</aui:input>

	<aui:button class="btn btn-success" name="Submit" type="submit" />
	<aui:button type="cancel" onClick="<%=editActivity%>" />

</aui:form>
