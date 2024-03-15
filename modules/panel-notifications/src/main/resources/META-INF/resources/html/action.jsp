<%@ include file="init.jsp" %>

<%
    final ResultRow row = (ResultRow) request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
    final Messages messages = (Messages) row.getObject();
    
    final String status = String.valueOf(messages.getStatus());
    
%>


<div class="container-fluid-1280" >


<portlet:actionURL name="editMessage" var="editMessageURL">
    <portlet:param name="CMD" value="edit"/>
    <portlet:param name="<%=PanelNotificationsPortletKeys.MESSAGEID %>" value="<%=String.valueOf(messages.getNotificationEngineId())%>"/>
</portlet:actionURL>

<portlet:actionURL name="checkStatus" var="checkStatusURL">
    <portlet:param name="status" value="<%= Validator.isNotNull(messages) ? String.valueOf(messages.getStatus()) : "true" %>"/>
    <portlet:param name="checkStatus" value="<%=String.valueOf(messages.getNotificationEngineId())%>"/>
</portlet:actionURL>

<portlet:actionURL name="deleteMessage" var="deleteMessageURL">
    <portlet:param name="delete" value="delete"/>
    <portlet:param name="deleteMessage" value="<%=String.valueOf(messages.getNotificationEngineId())%>"/>
</portlet:actionURL>

<div class="header-buttons">
	<liferay-ui:icon-menu>
    <liferay-ui:icon image="edit" message="Edit" url="<%=editMessageURL%>"/>
    <liferay-ui:icon image="check" message="<%= status == "false" ? "Enable" : "Disable" %>" url="<%=checkStatusURL%>"/>
    <liferay-ui:icon image="delete" message="Delete" url="<%=deleteMessageURL%>"/>
</liferay-ui:icon-menu>
</div>





