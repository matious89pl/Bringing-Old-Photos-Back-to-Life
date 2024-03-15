<%@ include file="/init.jsp"%>

<portlet:actionURL name="newNotification" var="newNotification" />

<liferay-portlet:resourceURL var="viewMembers" >
    <liferay-portlet:param name="segmentId2" value="Value1"/>
</liferay-portlet:resourceURL>

<script>
$( document ).ready(function() {
	$('.RPASelect').change(function(){
		var segmentId= $(this).find(':selected').data('segmentid');		
		 AUI().use('aui-io-request', function(A){
		 		A.io.request('${viewMembers}', {
		               method: 'post',
		               dataType: 'json',
		               data: {
		                   <portlet:namespace />segmentId: segmentId,
		               },
		               on: {
		                       success: function() {
				                    console.log(this.get('responseData'));
		
				                    var data = this.get('responseData');
				                    $('#<portlet:namespace/>segmentsUsers').empty().append('<option value="">View Members</option>');
				                    
				                    $.each(data, function(i, item) {
				                        $("#<portlet:namespace/>segmentsUsers").append('<option value="'+item.id+'">' + item.name + "/ " + item.emailAddress + '</option>');
				                     });
		                   		}
		              }
		        });
		});
	});
});

</script>

<div class="rec-custom-notification">
<aui:form action="<%=newNotification%>" method="post" name="newNotification">
		<h1>Portal and Email Notifications</h1>
	<hr>
	<div class="col-md-12 row">
	<div class="col-md-6">
		<%
			List<SegmentsEntry> segmentList = (List<SegmentsEntry>) renderRequest.getAttribute("segmentsList");
			if (segmentList.size() > 0){
		%>
		<aui:fieldset>
			 <aui:select  label="Choose a target" cssClass="RPASelect content-placeholder" name="segments" required="true">
			  	<aui:option/>
			<%
				for (SegmentsEntry segment:segmentList) {
			%>
					<aui:option data-segmentId="<%=segment.getSegmentsEntryId()%>" value="<%=segment.getNameCurrentValue()%>" label="<%=segment.getNameCurrentValue()%>" /> 
			<%
				}
			%>
			</aui:select>
	  </aui:fieldset>
	</div>
	<div class="col-md-6">
		 <aui:fieldset>
		 	<aui:select  label="" cssClass="content-placeholder viewMembers" name="segmentsUsers">
		 		<option value="">View Members</option>
			</aui:select>
	    </aui:fieldset>
	</div>
		<%
			}
		%>
 	</div>
 	<div class="col-md-12">
		<aui:input label="Enter notification title" cssClass="NotificationInput content-placeholder" type="text" name="notificationTitle" required="true">
			<aui:validator name="maxLength">250</aui:validator>
		</aui:input>
	</div>
		
	<div class="col-md-12">
			<aui:input label="Enter notification body" cssClass="NotificationInput content-placeholder" name="notificationBody" type="textarea" required="true">
				<aui:validator name="maxLength">500</aui:validator>
			</aui:input>
	</div>
	
	 <div class="col-md-12">
			<aui:input label="URL" cssClass="NotificationInput content-placeholder" type="text" name="notificationURL" placeholder="This is how your link will be displayed in the notification">
				<aui:validator name="maxLength">75</aui:validator>
			</aui:input>
	</div>
	 
 	<div class="col-md-12">
	<div class="content-label">Delivery Method*</div>
		<aui:input label="Portal Notification" name="methodsenderPotal" type="checkbox" value="Portal Notification" checked="true"/>
		<aui:input label="Email" name="methodsenderEmail" type="checkbox" value="Email"/>
	</div>
	
	<aui:button cssClass="buttonNotificationCustom" type="submit"  value="Send Notification"/> 
</aui:form>
	<hr>

	<h1>Notifications Log</h1>

	<%
		List<NotificationRpa> customNotificationList = (List<NotificationRpa>) renderRequest.getAttribute("notificationslogList");
	%>
	
	<% PortletURL iteratorURL = renderResponse.createRenderURL(); %>
	
	<%-- Search container. --%>
	<div id="searchContainerCustomNotification">
	<liferay-ui:search-container id="customNotificationListEntries" emptyResultsMessage="no-notifications-were-found" delta="5" deltaConfigurable="true" total="<%= NotificationRpaLocalServiceUtil.findByGroupPlidSearchContainterTotal(themeDisplay.getSiteGroupId(), themeDisplay.getPlid()) %>" iteratorURL="<%= iteratorURL %>">
		<liferay-ui:search-container-results results="<%= NotificationRpaLocalServiceUtil.findByGroupPlidSearchContainer(themeDisplay.getSiteGroupId(), themeDisplay.getPlid(), searchContainer.getStart(), searchContainer.getEnd()) %>"/>
			<liferay-ui:search-container-row className="rec.customnotification.model.NotificationRpa" modelVar="customNot">
				<liferay-ui:search-container-column-text name="Notification Title" value="${customNot.getNotificationTitle()}" />
				<liferay-ui:search-container-column-text name="Target Audience" value="${customNot.getTargetName()}" />
				<liferay-ui:search-container-column-text name="Delivery Method" value="${customNot.getDeliveryMethod()}" />
				<%
					ZoneId defaultZoneId = ZoneId.of("Europe/London");
					Date date = customNot.getCreateDate();
					Instant instant = date.toInstant();
					ZonedDateTime zonedDateTime = instant.atZone(defaultZoneId);
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
					String createdDate = zonedDateTime.format(formatter);
				%>
				<liferay-ui:search-container-column-text name="Date/Time Issued" value="<%=createdDate%>" />

				<portlet:renderURL var="goDetails">
					<portlet:param name="jspPage" value="/notification_details.jsp" />
					<portlet:param name="notificationId" value="${customNot.getCustomNotificationId()}" />
				</portlet:renderURL>

				<liferay-ui:search-container-column-text href="<%=goDetails%>" name="" value="Details" />

			</liferay-ui:search-container-row>
			<liferay-ui:search-iterator/>
		</liferay-ui:search-container>
	</div>
</div>
