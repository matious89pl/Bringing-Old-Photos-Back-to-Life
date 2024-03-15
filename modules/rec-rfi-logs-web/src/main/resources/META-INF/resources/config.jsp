<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ page import="com.liferay.portal.kernel.util.StringUtil"%>
<%@ page import="com.liferay.portal.kernel.util.Constants" %>

<portlet:defineObjects />

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />

<aui:form action="<%= configurationActionURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

	<liferay-portlet:renderURL portletConfiguration="<%= true %>" var="configurationRenderURL" />

	<aui:input name="redirect" type="hidden" value="<%= configurationRenderURL %>" />

	<aui:fieldset>
		<aui:select label="aggregateLogView" name="aggregateLogView" value='<%= (String)portletPreferences.getValue("aggregateLogView", "no") %>'>
 		<% if (StringUtil.equals("no", (String)portletPreferences.getValue("aggregateLogView", "no").toLowerCase())){ %>
	            <aui:option value="No" selected="<%=true %>">No</aui:option>
	            <aui:option value="Yes">Yes</aui:option>
	        <%} else{ %>        
	            <aui:option value="No">No</aui:option>
	            <aui:option value="Yes" selected="<%=true %>">Yes</aui:option>
            <%} %>     
		</aui:select>
	</aui:fieldset>
	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>
</aui:form>