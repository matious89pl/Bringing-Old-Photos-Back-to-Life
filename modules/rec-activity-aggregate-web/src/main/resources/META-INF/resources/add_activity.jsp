<%@ include file="init.jsp" %>
 
<p>
	<b><liferay-ui:message key="testama.caption.add"/></b>
</p>
<portlet:actionURL name="editActivity" var="editActivity" />
<portlet:actionURL name="addActivitySubmit" var="addActivitySubmit" />

<aui:form action="<%=addActivitySubmit%>" method="post" name="addActivitySubmit">

	

<!-- Title -->
	<aui:input name="activitytitle" label="Activity Title"  style="width:500px" >
	   <aui:validator name="required" />
	   <aui:validator name="maxLength">500</aui:validator>
	</aui:input>
	
<!-- Due Date -->
<!-- 	<aui:input name="duedate" label="Due Date" /> -->
	
	<aui:input name="duedate"  type="date" label="Due Date" style="width:500px"  >
	    <aui:validator name="date"></aui:validator>
	</aui:input>
	
	
	
	
	<!-- Status -->
	<aui:fieldset>
		<aui:select label="Status" name="status" style="width:500px" >
			<aui:option label="Open" />
			<aui:option label="Pending" />
			<aui:option label="Complete" />
		</aui:select>
	</aui:fieldset>



	<aui:button class="btn btn-success" name="Submit" type="submit" />
	<aui:button type="cancel" onClick="<%= editActivity %>" />

</aui:form>
