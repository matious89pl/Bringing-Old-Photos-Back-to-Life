<%@ include file="/init.jsp"%>
<%@page	import="com.everis.rec.remediation.tracker.model.RemediationTracker"%>
<%@page	import="com.liferay.portal.kernel.util.StringUtil"%>
<%@page import="com.everis.rec.remediation.tracker.service.RemediationTrackerLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.util.PortalUtil" %>
<p><b>Edit Remediation Tracker</b></p>
 
<portlet:actionURL name="editRemediationTracker" var="editRemediationTracker" />

<%
RemediationTracker remediationTrackerForEdit;
 remediationTrackerForEdit = (RemediationTracker) renderRequest.getAttribute("remediationTrackerForEdit");



if (remediationTrackerForEdit != null) {
%>

<aui:form action="<%=editRemediationTracker%>" method="post" name="editRemediationTracker">

	<aui:input id="mid" name="mid" type="hidden" value="<%=remediationTrackerForEdit.getRemediationTrackerId()%>" />

<!-- Title -->
	<aui:input name="title" label="Title" required="true" style="width:500px" value="<%=remediationTrackerForEdit.getTitle()%>" >
	   <aui:validator name="required" />
	   <aui:validator name="maxLength">75</aui:validator>
	</aui:input>
	
	<aui:fieldset>
		<aui:select label="Category" name="category" required="true" style="width:500px" >
			<aui:option label="Prepayment" selected="<%=StringUtil.equals(remediationTrackerForEdit.getCategory(), "Prepayment")%>" />
			<aui:option label="Metering" selected="<%=StringUtil.equals(remediationTrackerForEdit.getCategory(), "Metering")%>"/>
			<aui:option label="Data Management" selected="<%=StringUtil.equals(remediationTrackerForEdit.getCategory(), "Data Management")%>" />
			<aui:option label="SMIS" selected="<%=StringUtil.equals(remediationTrackerForEdit.getCategory(), "SMIS")%>" />
			<aui:option label="Meter Readings" selected="<%=StringUtil.equals(remediationTrackerForEdit.getCategory(), "Meter Readings")%>"/>
			<aui:option label="Debt Assignment" selected="<%=StringUtil.equals(remediationTrackerForEdit.getCategory(), "Debt Assignment")%>"/> 
			<aui:option label="Theft" selected="<%=StringUtil.equals(remediationTrackerForEdit.getCategory(), "Theft")%>"/>
			<aui:option label="Related Metering Point" selected="<%=StringUtil.equals(remediationTrackerForEdit.getCategory(), "Related Metering Point")%>"/>
			<aui:option label="Erroneous Switches" selected="<%=StringUtil.equals(remediationTrackerForEdit.getCategory(), "Erroneous Switches")%>"/> 
			<aui:option label="Green Deal Arrangements" selected="<%=StringUtil.equals(remediationTrackerForEdit.getCategory(), "Green Deal Arrangements")%>"/>
			<aui:option label="Registrations" selected="<%=StringUtil.equals(remediationTrackerForEdit.getCategory(), "Registrations")%>"/>
			<aui:option label="EES/SDEP" selected="<%=StringUtil.equals(remediationTrackerForEdit.getCategory(), "EES/SDEP")%>"/>
			<aui:option label="Other" selected="<%=StringUtil.equals(remediationTrackerForEdit.getCategory(), "Other")%>"/>
		</aui:select>
	</aui:fieldset>
	
	<aui:input name="description" label="Description" required="true" style="width:500px" value="<%=remediationTrackerForEdit.getDescription()%>" >
	   <aui:validator name="required" />
	   <aui:validator name="maxLength">500</aui:validator>
	</aui:input>
	
	<aui:input name="duedate"  type="date" label="Due Date" required="true" style="width:500px" value="<%=remediationTrackerForEdit.getDueDate()%>" >
	    <aui:validator name="date"></aui:validator>
	</aui:input>
	
	<!-- Status -->
	<aui:fieldset>
		<aui:select label="Status" name="status" required="true" style="width:500px" >
			<aui:option label="Open" selected="<%=StringUtil.equals(remediationTrackerForEdit.getStatus(), "Open")%>"/>
			<aui:option label="Pending" selected="<%=StringUtil.equals(remediationTrackerForEdit.getStatus(), "Pending")%>"/>
			<aui:option label="Complete" selected="<%=StringUtil.equals(remediationTrackerForEdit.getStatus(), "Complete")%>"/>
		</aui:select>
	</aui:fieldset>

	<aui:button class="btn btn-success" name="Submit" type="submit" />
	<aui:button type="cancel" onClick="<%=editRemediationTracker%>" />
</aui:form>

<% 	}	%>
