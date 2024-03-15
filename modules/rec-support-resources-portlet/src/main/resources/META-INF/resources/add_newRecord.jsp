<%@ include file="/init.jsp" %>

<portlet:actionURL name="addSupportResourceSubmit" var="addSupportResourceSubmit" />

<portlet:actionURL name="goBack" var="goBack" />

<aui:form action="<%=addSupportResourceSubmit%>" method="post" name="addSupportingResource">

	<!-- Type -->
	<aui:fieldset>
		<aui:select label="Supporting Resource Type" name="type" required="true" >
			<aui:option label="Survey" />
			<aui:option label="Training" />
			<aui:option label="Guidance" />
			<aui:option label="Form" />
		</aui:select>
	</aui:fieldset>

	<!-- Title -->
	<aui:input name="title" label="Supporting Resource Title" required="true">
	   <aui:validator name="maxLength">250</aui:validator>
	</aui:input>
	
	<!-- Description -->
	<aui:input name="description" label="Supporting Resource Description" type="textarea" required="true">
	   <aui:validator name="maxLength">500</aui:validator>
	</aui:input>
	
	<!-- Due Date -->
	<aui:input id="duedate" name="duedate" type="date" label="Due Date" required="true">
	    <aui:validator name="date"></aui:validator>
	</aui:input>
	
	<!-- Status -->
	<aui:fieldset>
		<aui:select label="Status" name="status" required="true">
			<aui:option label="Open" />
			<aui:option label="Pending" />
			<aui:option label="Complete" />
		</aui:select>
	</aui:fieldset>
	
	<!-- Link -->
	<aui:input name="link" label="Link" required="true" >
	   <aui:validator name="maxLength">1000</aui:validator>
	   <aui:validator name="url"/>
	</aui:input>
	
	<!--Displyaed Link -->
	<aui:input name="displayedLink" label="Display link" placeholder="This value is the one that you will see in the table" required="true">
	 	<aui:validator name="maxLength">75</aui:validator>	
	</aui:input>

	<aui:button cssClass="saveButton" name="Submit" type="submit" />
	<aui:button cssClass="cancelButton" type="cancel" value="Cancel" onClick="<%=goBack%>" />
</aui:form>
