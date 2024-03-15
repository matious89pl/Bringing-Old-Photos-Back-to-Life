<%@page import="com.everis.rec.maintenanceactivity.service.MaintenanceActivityLocalServiceUtil"%>
<%@ include file="/init.jsp"%>
<%@page	import="com.everis.rec.maintenanceactivity.model.MaintenanceActivity"%>
<%@page	import="com.liferay.portal.kernel.util.StringUtil"%>
<%@page import="com.liferay.portal.kernel.util.PortalUtil" %>
<p>
	<b><liferay-ui:message key="testama.caption.edit" /></b>
</p>
 
<portlet:actionURL name="editActivity" var="editActivity" />

<aui:form action="<%=editActivity%>" method="post" name="editActivity">

	<%
		MaintenanceActivity maintenanceActivityForEdit = (MaintenanceActivity) renderRequest.getAttribute("maintenanceActivityForEdit");
		
		
		
			if (maintenanceActivityForEdit != null) {
	%>
	<aui:input id="mid" name="mid" type="hidden"
		value="<%=maintenanceActivityForEdit.getMaintenanceactivityId()%>" />


	<!-- Title -->
	<aui:input name="activitytitle" label="Activity Title"
		value="<%=maintenanceActivityForEdit.getActivityTitle()%>"
		style="width:500px">
		<aui:validator name="required" />
		<aui:validator name="maxLength">500</aui:validator>
	</aui:input>

	<!-- Due Date -->


	<aui:input name="duedate" type="date" label="Due Date"
		style="width:500px"
		value="<%=maintenanceActivityForEdit.getDueDate()%>">
		<aui:validator name="date"></aui:validator>
	</aui:input>


	<!-- Status -->
	<aui:fieldset>
		<aui:select label="Status" name="status"
			value="<%=maintenanceActivityForEdit.getStatus()%>"
			style="width:500px">
			<aui:option label="Open" />
			<aui:option label="Pending" />
			<aui:option label="Complete" />
		</aui:select>
	</aui:fieldset>

	<%
		}
	%>

	<aui:button class="btn btn-success" name="Submit" type="submit" />
	<aui:button type="cancel" onClick="<%=editActivity%>" />

</aui:form>
