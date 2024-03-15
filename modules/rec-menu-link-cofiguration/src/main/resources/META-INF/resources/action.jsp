<%@ include file="init.jsp" %>

<%
    final ResultRow row = (ResultRow) request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
    final LinkMenu link = (LinkMenu) row.getObject();
    
%>
<div class="container-fluid-1280" >
<portlet:actionURL name="updateLink" var="updateLinkURL">
    <portlet:param name="CMD" value="edit"/>
    <portlet:param name="linkId" value="<%=String.valueOf(link.getLinkId())%>"/>
</portlet:actionURL>

<portlet:actionURL name="deleteLink" var="deleteLinkURL">
    <portlet:param name="delete" value="delete"/>
    <portlet:param name="deleteLink" value="<%=String.valueOf(link.getLinkId())%>"/>
</portlet:actionURL>

<div class="header-buttons">
	<liferay-ui:icon-menu>
    <liferay-ui:icon image="edit" message="Edit" url="<%=updateLinkURL%>"/>
    <liferay-ui:icon image="delete" message="Delete" url="<%=deleteLinkURL%>"/>
</liferay-ui:icon-menu>
</div>





