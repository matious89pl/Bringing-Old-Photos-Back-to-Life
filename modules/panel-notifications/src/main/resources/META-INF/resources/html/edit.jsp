<%@ include file="init.jsp" %>

<% 
Messages message = (Messages) request.getAttribute("messages");
%>

<portlet:actionURL name="saveMessage" var="saveMessageURL" />

<portlet:renderURL var="backURL">
		<portlet:param name="mvcPath" value="/html/view.jsp"/>
</portlet:renderURL>

<div class="panel-notifications">

<aui:button-row>	
		<aui:button type="submit" href="<%=backURL %>" value="<- Back"></aui:button>			
</aui:button-row>
	
<aui:form action="<%=saveMessageURL %>" style=" margin-left: 2rem; margin-top: 2rem;" method="post">
	<aui:fieldset>
		<aui:input name="idMessage" type="hidden" value="<%= Validator.isNotNull(message) ? message.getNotificationEngineId() : StringPool.BLANK %>"/>
		
		<aui:input name="name" cssClass="inputemailnot" value="<%= Validator.isNotNull(message) ? message.getName() : StringPool.BLANK %>">
				<aui:validator name="required" errorMessage="Field required"></aui:validator>
		</aui:input>
		
		<aui:input name="subject" cssClass="inputemailnot" value="<%= Validator.isNotNull(message) ? message.getSubject() : StringPool.BLANK %>">
				<aui:validator name="required" errorMessage="Field required"></aui:validator>
		</aui:input>
		
		<aui:input name="body" type="textarea" style="width: 300px;height: 100px;" cssClass="inputemailnot" localized="<%= true %>" value="<%= Validator.isNotNull(message) ? message.getBody() : StringPool.BLANK %>">
				<aui:validator name="required" errorMessage="Field required"></aui:validator>
		</aui:input>
		<aui:button type="submit" name="submit" />
		<aui:button type="cancel" href='<%= backURL %>' />
	</aui:fieldset>
</aui:form>
</div>



