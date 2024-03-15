<%@ page import="com.liferay.portal.kernel.util.Constants" %>

<%@ include file="/init.jsp" %>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />
<liferay-portlet:renderURL portletConfiguration="<%= true %>" var="configurationRenderURL" />

<div class="rec-dashboards-config">
    <aui:form action="<%= configurationActionURL %>" method="post" name="fm">
        <aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
        <aui:input name="redirect" type="hidden" value="<%= configurationRenderURL %>" />

        <aui:input name="role" label="Role" type="text" value="<%=dashboardRole%>" />
        <aui:input name="userId" label="UserId" type="text" value="<%=dashboardUserId%>" />
        <aui:input name="link" label="Link" type="text" value="<%=dashboardLink%>" />
        <aui:input name="dataset" label="Dataset" type="text" value="<%=dashboardDataset%>" />
        <aui:input name="height" label="Height (default 700)" type="text" value="<%=dashboardHeight%>" />
        <aui:input name="isPublic" label="Public" type="checkbox" checked="<%=GetterUtil.getBoolean(dashboardPublic)%>" />

        <aui:button-row>
            <aui:button type="submit" />
        </aui:button-row>
    </aui:form>
</div>
