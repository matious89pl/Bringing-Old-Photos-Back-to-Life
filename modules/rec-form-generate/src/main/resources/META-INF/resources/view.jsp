<%@ include file="init.jsp"%>

<p>
	<b>TO BULK CREATE FORMS PLEASE ENTER FORMINSTANCEIDs OF RESPECTIVE FORMS</b>
</p>


<portlet:actionURL name="createForms" var="createForms" />

<aui:form action="<%=createForms%>" method="post" name="createForms">

	
	<aui:input name="adminGroupId" label="From GroupId" style="width:500px">
		<aui:validator name="required" />
	</aui:input>
	
	<aui:input name="inGroupId" label="In GroupId" style="width:500px">
	</aui:input>
	
	
	<aui:input name="formInstanceId1" label="Maintenance of Qualification: Annual Statement and Smart Meter Installation Schedule Self Declaration" style="width:500px">
		<aui:validator name="required" />
	</aui:input>
	
	<aui:input name="formInstanceId2" label="Maintenance of Qualification: System or Process Change Disclosure" style="width:500px">
		<aui:validator name="required" />
	</aui:input>
	
	<aui:input name="formInstanceId3" label="Maintenance of Qualification: External Assessment" style="width:500px">
		<aui:validator name="required" />
	</aui:input>
	
	<aui:input name="formInstanceId4" label="Maintenance of Qualification: Compliance Statement" style="width:500px">
		<aui:validator name="required" />
	</aui:input>



	<aui:button class="btn btn-success" name="Submit" type="submit" />
	

</aui:form>