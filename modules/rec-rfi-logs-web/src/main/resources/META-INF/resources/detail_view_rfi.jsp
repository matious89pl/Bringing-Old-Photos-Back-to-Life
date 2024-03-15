<%@page import="com.everis.rec.rfilogs.service.RfiLogsLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.model.Layout"%>
<%@ include file="init.jsp"%>
<%@page
	import="com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.model.UserGroupRole"%>
<%@page import="com.liferay.portal.kernel.service.LayoutLocalServiceUtil" %>
<%@page import="com.liferay.portal.kernel.util.PortalUtil" %>


<%
	boolean hasAccessComments = false;

	if (RoleLocalServiceUtil.hasUserRole(themeDisplay.getUserId(), themeDisplay.getCompanyId(), "Administrator", false) || 
			RoleLocalServiceUtil.hasUserRole(themeDisplay.getUserId(), themeDisplay.getCompanyId(), "Portal_Admin", false)) {
		hasAccessComments = true;
	}
	RfiLogs rfi;
	 rfi = (RfiLogs) renderRequest.getAttribute("rfiForView");
	if (rfi == null) {
		rfi = (RfiLogs) renderRequest.getPortletSession().getAttribute("activityRFI", PortletSession.PORTLET_SCOPE);
	}
	//fetching rfi from calendar booking url
	//HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));
	//String RFIlogIDFromCalendar = httpReq.getParameter("rfiId");
	
	//if (RFIlogIDFromCalendar != null) {
		
		//rfi = (RfiLogs) RfiLogsLocalServiceUtil.fetchRfiLogs(Long.parseLong(RFIlogIDFromCalendar));
	//}
	
	
	if (rfi != null) {
		renderRequest.getPortletSession().setAttribute("activityRFI", rfi, PortletSession.PORTLET_SCOPE);

	if (hasAccessComments == false) {
%>

<style>
.lfr-discussion.lfr-discussion-container .dropdown.lfr-icon-menu.actions-menu
	{
	display: none;
}
</style>


<%
	}
%>

<p>
	<b>REQ ID</b>
<p><%=rfi.getReqId()%></p>
</p>

<p>
	<b>Title</b>
<p><%=rfi.getTitle()%></p>

</p>
<p>
	<b>Due Date</b>
<p><%=rfi.getDueDate()%></p>
</p>
<p>
	<b>Status</b>
<p><%=rfi.getStatus()%></p>
</p>
<p>
	<b>Request Description</b>
<p><%=rfi.getReqDesc()%></p>
</p>
<p>
	<b>Rationale</b>
<p><%=rfi.getRationale()%></p>
</p>

<%@include file="page_comments.jspf" %>
<%
	}
%>
