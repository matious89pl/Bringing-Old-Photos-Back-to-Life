<%@page
	import="com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.model.UserGroupRole"%>
<%@ include file="init.jsp"%>
<p>
	<b><liferay-ui:message key="rfilogsaction.caption.edit" /></b>
</p>
<portlet:actionURL name="editActivity" var="editActivity" />



<%
	boolean hasAccessComments = false;
	if (RoleLocalServiceUtil.hasUserRole(themeDisplay.getUserId(), themeDisplay.getCompanyId(), "Administrator",
			false)
			|| RoleLocalServiceUtil.hasUserRole(themeDisplay.getUserId(), themeDisplay.getCompanyId(),
					"Portal_Admin", false)) {
			hasAccessComments = true;
	}
	RfiLogs rfi = (RfiLogs) renderRequest.getAttribute("rfiForEdit");

	if (rfi == null) {
		rfi = (RfiLogs) renderRequest.getPortletSession().getAttribute("activityRFI", PortletSession.PORTLET_SCOPE);

	}

	if (rfi != null) {

		renderRequest.getPortletSession().setAttribute("activityRFI", rfi, PortletSession.PORTLET_SCOPE);
%>

<%
	if (!hasAccessComments) {
%>
<style>
.lfr-discussion.lfr-discussion-container .dropdown.lfr-icon-menu.actions-menu {
	display: none;
}
</style>


<%
	}
%>

<aui:form action="<%=editActivity%>" method="post" name="editActivity">
	<aui:input id="mid" name="mid" type="hidden"
		value="<%=rfi.getRfilogId()%>" />

	<!-- ReqId -->
	<aui:input name="reqId" label="REQ ID" style="width:500px"
		value="<%=rfi.getReqId()%>">
		<aui:validator name="required" />
		<aui:validator name="digits" />

	</aui:input>

	<!-- Title -->
	<aui:input name="title" label="Title" style="width:500px"
		value="<%=rfi.getTitle()%>">
		<aui:validator name="required" />
		<aui:validator name="maxLength">500</aui:validator>
	</aui:input>

	<!-- Due Date -->
	<aui:input name="duedate" type="date" label="Due Date"
		style="width:500px" value="<%=rfi.getDueDate()%>">
		<aui:validator name="date"></aui:validator>
	</aui:input>

	<!-- Status -->
	<aui:fieldset>
		<aui:select label="Status" name="status"
			value="<%=rfi.getStatus()%>" style="width:500px">
			<aui:option label="Open" />
			<aui:option label="Pending" />
			<aui:option label="Complete" />
		</aui:select>
	</aui:fieldset>

	<!-- Request Description  -->
	<aui:input name="reqDesc" label="Request Description "
		style="width:500px" value="<%=rfi.getReqDesc()%>">
		<aui:validator name="required" />
		<aui:validator name="maxLength">500</aui:validator>
	</aui:input>

	<!-- Rationale  -->
	<aui:input name="rationale" label="Rationale" style="width:500px"
		value="<%=rfi.getRationale()%>">
		<aui:validator name="required" />
		<aui:validator name="maxLength">500</aui:validator>
	</aui:input>
	<aui:button class="btn btn-success" name="Submit" type="submit" />
	<aui:button type="cancel" onClick="<%=editActivity%>" />
</aui:form>

 <%@include file="page_comments.jspf" %>

<%
	}
%>
