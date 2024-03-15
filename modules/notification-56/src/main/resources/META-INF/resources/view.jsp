<%@ include file="/init.jsp" %>

<portlet:actionURL name="sendNotification" var="actionSendMessage">
</portlet:actionURL>

<div class="send-notification">
<div class="header-buttons">
	<aui:button-row>	
		<aui:button type="submit" href="<%=actionSendMessage.toString() %>" value="Send Notification 56"></aui:button>			
	</aui:button-row>
</div></p>