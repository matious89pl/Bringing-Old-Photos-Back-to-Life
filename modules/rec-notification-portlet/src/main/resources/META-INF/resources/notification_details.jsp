<%@ include file="init.jsp"%>

<portlet:actionURL name="goBack" var="goBack" />
<portlet:actionURL name="resendNotification" var="resendNotification" />

<div id="rec-custom-notification_details">

	<%
		String notificationIdString = ParamUtil.getString(renderRequest, "notificationId","");
		Integer notificationId =  Integer.parseInt(notificationIdString);
		NotificationRpa notification = NotificationRpaLocalServiceUtil.getNotificationRpa(notificationId);
		String notificationTitle = notification.getNotificationTitle();
		String notificationBody = notification.getNotificationBody();
		String notificationDeliveryMethod = notification.getDeliveryMethod();
		String notificationTargetAudience = notification.getTargetName();
	%> 	

	<aui:form action="<%=resendNotification%>" method="post" name="resendNotification">
		<div id="notificationIdInput"><aui:input  label="<%=notificationIdString%>" cssClass="NotificationInput" name="notificationId"/></div>
			<h1> Notification Details Page </h1>
			<div id="notificationLogTitleValue"> 
				<h2>Notification Title</h2>
				<h3> <%=notificationTitle%> </h3>
			</div>
			
			<div id="notificationLogBodyValue">
				<h2>Notification Body</h2>
				<h3> <%=notificationBody%> </h3>
			</div>
		
			<div id="cutomNotificationDetails">
				<div id="notificationLogDeliveryMethodValue">
					<h2>Delivery Method</h2>
					<h3> <%=notificationDeliveryMethod%> </h3>
				</div>
				<div id="notificationLogTargetAudienceValue">
					<h2>Target Audience</h2>
					<h3> <%=notificationTargetAudience%> </h3>
				</div>
			</div>
		<div id="notificationDetailsButtons"> 
		<aui:button cssClass="buttonNotificationCustom" type="cancel" value="Cancel" onClick="<%=goBack%>" />
		<%
			List<SegmentsEntry> segmentList = (List<SegmentsEntry>) renderRequest.getAttribute("segmentsList");
			boolean targetExist = false;
			if (segmentList.size() > 0){
				for (SegmentsEntry segment:segmentList) {
					if(segment.getNameCurrentValue().equals(notificationTargetAudience)){  
						targetExist = true;
					}
				}
			} 
			if (!targetExist){
		%>
				<aui:button cssClass="buttonNotificationCustom" type="submit"  value="Resend Notification" disabled="true"/> 
				<p id="targetRemoved"> The notification can't be sent because the target audience has been removed </p>
		<%
			} else {
		%>		        
			<aui:button cssClass="buttonNotificationCustom" type="submit"  value="Resend Notification"/> 
		<%
			} 
		%>	
		</div>	
	</aui:form>	
</div>

