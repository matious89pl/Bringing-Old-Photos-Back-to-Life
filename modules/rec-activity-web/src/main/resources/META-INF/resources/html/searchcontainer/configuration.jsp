<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@ include file="/html/searchcontainer/init.jsp" %>
<%@ page import="com.everis.rec.activity.config.*" %>
<%@ page import="com.liferay.portal.kernel.util.StringUtil"%>
<%@ page import="javax.portlet.PortletSession"%>
 
<% String	aggregateView = (String) renderRequest.getPortletSession().getAttribute("aggregateView", PortletSession.APPLICATION_SCOPE); %> 
<liferay-portlet:actionURL portletConfiguration="<%=true%>" var="configurationActionURL" />
 
<liferay-portlet:renderURL portletConfiguration="<%=true%>" var="configurationRenderURL" />
 
<aui:form action="<%=configurationActionURL%>" method="post" name="fm">
    <aui:input name="<%=Constants.CMD%>" type="hidden" value="<%=Constants.UPDATE%>" />
    <aui:input name="redirect" type="hidden" value="<%=configurationRenderURL%>" />
 
    <aui:fieldset>
        <aui:select label="Aggregate View" name="aggregateView" value="<%=aggregateView%>">        
        <% if (StringUtil.equals("no", aggregateView.toLowerCase())){ %>
	            <aui:option value="No" selected="<%=true %>">No</aui:option>
	            <aui:option value="Yes">Yes</aui:option>
	        <%} else{ %>        
	            <aui:option value="No">No</aui:option>
	            <aui:option value="Yes" selected="<%=true %>">Yes</aui:option>
            <%} %>            
        </aui:select>
    </aui:fieldset>
   
    <aui:button-row>
        <aui:button type="submit"></aui:button>
    </aui:button-row>
</aui:form>
