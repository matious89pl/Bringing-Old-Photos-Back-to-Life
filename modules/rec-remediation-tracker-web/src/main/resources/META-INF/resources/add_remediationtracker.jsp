<%@ include file="init.jsp" %>
 
<p>
	<b>Add Remediation Tracker</b>
</p>
<portlet:actionURL name="editRemediationTracker" var="editRemediationTracker" />
<portlet:actionURL name="addRemediationTrackerSubmit" var="addRemediationTrackerSubmit" />

<aui:form action="<%=addRemediationTrackerSubmit%>" method="post" name="addRemediationTrackerSubmit">

<!-- Title -->
	<aui:input name="title" label="Title" required="true"  style="width:500px" >
	   <aui:validator name="required" />
	   <aui:validator name="maxLength">75</aui:validator>
	</aui:input>
	
	
	<aui:fieldset>
		<aui:select label="Category" required="true" name="category" style="width:500px" >
			<aui:option label="Prepayment" />
			<aui:option label="Metering" />
			<aui:option label="Data Management" />
			<aui:option label="SMIS" />
			<aui:option label="Meter Readings" />
			<aui:option label="Debt Assignment" /> 
			<aui:option label="Theft" />
			<aui:option label="Related Metering Point" />
			<aui:option label="Erroneous Switches" /> 
			<aui:option label="Green Deal Arrangements" />
			<aui:option label="Registrations" />
			<aui:option label="EES/SDEP" />
			<aui:option label="Other" />
		</aui:select>
	</aui:fieldset>
	
	<aui:input name="description" label="Description" required="true"  style="width:500px" >
	   <aui:validator name="required" />
	   <aui:validator name="maxLength">500</aui:validator>
	</aui:input>
	
	<aui:input name="duedate" required="true"  type="date" label="Due Date" style="width:500px"  >
	    <aui:validator name="date"></aui:validator>
	</aui:input>
	
	<!-- Status -->
	<aui:fieldset>
		<aui:select label="Status" name="status" required="true" style="width:500px" >
			<aui:option label="Open" />
			<aui:option label="Pending" />
			<aui:option label="Complete" />
		</aui:select>
	</aui:fieldset>

	<aui:button class="btn btn-success" name="Submit" type="submit" />
	<aui:button type="cancel" onClick="<%= editRemediationTracker %>" />

</aui:form>
