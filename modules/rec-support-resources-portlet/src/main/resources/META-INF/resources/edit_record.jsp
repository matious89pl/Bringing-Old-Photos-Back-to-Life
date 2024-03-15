<%@page import="rec.supporting.resources.service.supportRLocalServiceUtil"%>
<%@ include file="/init.jsp" %>
<p><b>Edit Supporting Resource</b></p>
<portlet:actionURL name="editSupportResourceSubmit" var="editSupportResourceSubmitURL" />
<portlet:actionURL name="deleteSupportResource" var="deleteSupportResource" />
<portlet:actionURL name="goBack" var="goBack" />

<% supportR supportingResource = (supportR) renderRequest.getPortletSession().getAttribute("supportingResourceEdit", PortletSession.APPLICATION_SCOPE);

if(supportingResource != null){
	
%> 	
<aui:form action="<%=editSupportResourceSubmitURL%>" method="post" name="editSupportingResource">
	<aui:input id="resourceId" name="resourceId" type="hidden" value="<%=supportingResource.getSupportRId()%>"/>
	<!-- Type -->
	<aui:fieldset>
		<aui:select label="Supporting Resource Type" name="type" required="true" value="<%=supportingResource.getType()%>">
			<aui:option label="Survey" />
			<aui:option label="Training" />
			<aui:option label="Guidance" />
			<aui:option label="Form" />
		</aui:select>
	</aui:fieldset>

	<!-- Title -->
	<aui:input name="title" label="Supporting Resource Title" required="true" value="<%=supportingResource.getTitle()%>">
	   <aui:validator name="maxLength">250</aui:validator>
	</aui:input>
	
	<!-- Description -->
	<aui:input name="description" label="Supporting Resource Description" type="textarea" required="true" value="<%=supportingResource.getDescription()%>">
	   <aui:validator name="maxLength">500</aui:validator>
	</aui:input>
	<!-- Due Date -->
	<%
		ZoneId defaultZoneId = ZoneId.of("Europe/London");
		Date date = supportingResource.getDueDate();
		Instant instant = date.toInstant();
		ZonedDateTime zonedDateTime = instant.atZone(defaultZoneId);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String dueDate = zonedDateTime.format(formatter);
	%>
	<aui:input name="duedate"  type="date" label="Due Date" value="<%=dueDate%>">	
	    <aui:validator name="date"></aui:validator>
	</aui:input>
	
	<!-- Status -->
	<aui:fieldset>
		<aui:select label="Status" name="status" required="true" value="<%=supportingResource.getStatus()%>">
			<aui:option label="Open" selected="<%=StringUtil.equals(supportingResource.getStatus(), "Open")%>"/>
			<aui:option label="Pending" selected="<%=StringUtil.equals(supportingResource.getStatus(), "Pending")%>"/>
			<aui:option label="Complete" selected="<%=StringUtil.equals(supportingResource.getStatus(), "Complete")%>"/>			
		</aui:select>
	</aui:fieldset>
	
	<!-- Link -->
	<aui:input name="link" label="Link"  value="<%=supportingResource.getLink()%>" required="true">
	   <aui:validator name="maxLength">1000</aui:validator>
	   <aui:validator name="url"/>
	</aui:input>
	
	<!--Displyaed Link -->
	<aui:input name="displayedLink" label="Display link" value="<%=supportingResource.getDisplayLink()%>" required="true" >
		<aui:validator name="maxLength">75</aui:validator>	
	</aui:input>

	<aui:button cssClass="saveButton" name="Submit" type="submit" />
	<aui:button cssClass="cancelButton" type="cancel" value="Cancel" onClick="<%=goBack%>" />
</aui:form>

<% }%>