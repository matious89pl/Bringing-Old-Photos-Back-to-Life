<%@ include file="/init.jsp" %>

<p>
	<b><liferay-ui:message key="notification16.caption"/></b>
</p>

<portlet:actionURL name="sendNotification" var="actionSendMessage">
</portlet:actionURL>

<div class="send-notification">
<div class="header-buttons">
	<aui:button-row>	
		<aui:button type="submit" href="<%=actionSendMessage.toString() %>" value="Send Notification 16"></aui:button>			
	</aui:button-row>
</div>
</p>

