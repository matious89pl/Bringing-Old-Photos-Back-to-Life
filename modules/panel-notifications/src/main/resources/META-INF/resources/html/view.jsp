<%@ include file="init.jsp" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<portlet:defineObjects />

<% long companyId = themeDisplay.getCompanyId(); %>

<% PortletURL iteratorURL = renderResponse.createRenderURL();  %>

<portlet:renderURL var="addMessage">
	<portlet:param name="mvcPath" value="/html/edit.jsp" />
</portlet:renderURL>

<div style="font-size: 24px; margin-left: 2rem; margin-top: 2rem;">
     <p class="caption">
         <liferay-ui:message key="NOTIFICATIONS" />
     </p>
</div>

<div class="panel-notifications">
<div class="header-buttons">
	<aui:button-row>	
		<aui:button type="submit" href="<%=addMessage.toString() %>" value="Add"></aui:button>			
	</aui:button-row>
</div>

<liferay-ui:search-container delta="5" total="<%= MessagesLocalServiceUtil.getCountByCompanyId(companyId) %>" iteratorURL="<%=iteratorURL%>" >
	<liferay-ui:search-container-results 
		results="<%= MessagesLocalServiceUtil.findByCompanyId(companyId, searchContainer.getStart(), searchContainer.getEnd()) %>"/>
		
		<liferay-ui:search-container-row className="com.everis.messages.service.builder.model.Messages" modelVar="message"  >
				
			<liferay-ui:search-container-column-text name="name" value='<%= message.getName() %>' />		
			<liferay-ui:search-container-column-text name="subject" value='<%= message.getSubject() %>' />
			<liferay-ui:search-container-column-text name="body" value='<%= message.getBody() %>' />
			<liferay-ui:search-container-column-text name="status" value='<%= String.valueOf(message.getStatus()) %>' />
			
			<liferay-ui:search-container-column-jsp name="" path="/html/action.jsp"/>
   					
		</liferay-ui:search-container-row>
		
	<liferay-ui:search-iterator />
</liferay-ui:search-container>
</div>
