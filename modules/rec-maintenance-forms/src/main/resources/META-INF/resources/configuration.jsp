<%@ include file="init.jsp"%>
<%@ page import="com.liferay.portal.kernel.util.StringUtil"%>
<%@ page import="com.liferay.portal.kernel.util.Constants" %>

<!--  ********Author Details******** -->
<!--  Manish Kumar Jaiswal -->
<!--  ********Author Details******** -->

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />

<aui:form action="<%= configurationActionURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

	<liferay-portlet:renderURL portletConfiguration="<%= true %>" var="configurationRenderURL" />

	<aui:input name="redirect" type="hidden" value="<%= configurationRenderURL %>" />

	<aui:fieldset>
		<aui:select label="form environment" name="formenv" value='<%= (String)portletPreferences.getValue("formenv", "local") %>'>
	        <aui:option value="local" selected="<%=StringUtil.equals("local", (String)portletPreferences.getValue("formenv", "local").toLowerCase())%>">Local</aui:option>
            <aui:option value="dev"   selected="<%=StringUtil.equals("dev", (String)portletPreferences.getValue("formenv", "local").toLowerCase())%>">DEV</aui:option>
            <aui:option value="uat"   selected="<%=StringUtil.equals("uat", (String)portletPreferences.getValue("formenv", "local").toLowerCase())%>">UAT</aui:option>
            <aui:option value="preprod" selected="<%=StringUtil.equals("preprod", (String)portletPreferences.getValue("formenv", "local").toLowerCase())%>" >PRE PROD</aui:option>
            <aui:option value="prod"  selected="<%=StringUtil.equals("prod", (String)portletPreferences.getValue("formenv", "local").toLowerCase())%>">PROD</aui:option>
            <aui:option value="other"selected="<%=StringUtil.equals("other", (String)portletPreferences.getValue("formenv", "local").toLowerCase())%>" >Other</aui:option>
		</aui:select>

</aui:fieldset>	
	
	
	<aui:fieldset>
		<aui:select label="Aggregate Form View" name="aggregateFormView" value='<%= (String)portletPreferences.getValue("aggregateFormView", "no") %>'>
 		<% if (StringUtil.equals("no", (String)portletPreferences.getValue("aggregateFormView", "no").toLowerCase())){ %>
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
