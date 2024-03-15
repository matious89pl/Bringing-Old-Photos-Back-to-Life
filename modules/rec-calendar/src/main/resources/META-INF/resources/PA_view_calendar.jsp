<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@page import="java.util.ArrayList"%>
<%@ include file="/init.jsp" %>
<%@
page import="com.liferay.portal.kernel.service.LayoutLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.model.Layout" %><%@
page import="com.liferay.portal.kernel.service.RoleLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.model.Role" %>
<%
String activeView = ParamUtil.getString(request, "activeView", sessionClicksDefaultView);
long date = ParamUtil.getLong(request, "date", System.currentTimeMillis());

JSONArray groupCalendarsJSONArray = CalendarUtil.toCalendarsJSONArray(themeDisplay, groupCalendars);
JSONArray userCalendarsJSONArray = CalendarUtil.toCalendarsJSONArray(themeDisplay, userCalendars);
JSONArray otherCalendarsJSONArray = CalendarUtil.toCalendarsJSONArray(themeDisplay, otherCalendars);

boolean columnOptionsVisible = GetterUtil.getBoolean(SessionClicks.get(request, "com.liferay.calendar.web_columnOptionsVisible", "true"));
// Check if calendar management page exist
long groupId = themeDisplay.getScopeGroupId();

Layout mylay = LayoutLocalServiceUtil.fetchLayoutByFriendlyURL(groupId, true, "/calendar-management");
boolean hasPage = false;
String completeUrl = null;

if (mylay != null){
    hasPage = true;
    completeUrl = PortalUtil.getLayoutFullURL(mylay, themeDisplay);
}

long userId = themeDisplay.getUserId();

%>
    

<aui:script use="liferay-calendar-container,liferay-calendar-remote-services,liferay-component">
	Liferay.component('<portlet:namespace />calendarContainer', function () {
		var calendarContainer = new Liferay.CalendarContainer({
			groupCalendarResourceId: <%= groupCalendarResource.getCalendarResourceId() %>,

			<c:if test="<%= userCalendarResource != null %>">
				userCalendarResourceId: <%= userCalendarResource.getCalendarResourceId() %>,
			</c:if>

			namespace: '<portlet:namespace />',
		});

		var destroyInstance = function (event) {
			if (event.portletId === '<%= portletDisplay.getId() %>') {
				calendarContainer.destroy();

				Liferay.component('<portlet:namespace />calendarContainer', null);

				Liferay.detach('destroyPortlet', destroyInstance);
			}
		};

		Liferay.on('destroyPortlet', destroyInstance);

		return calendarContainer;
	});

	Liferay.component('<portlet:namespace />remoteServices', function () {
		var remoteServices = new Liferay.CalendarRemoteServices({
			baseActionURL:
				'<%= PortletURLFactoryUtil.create(request, portletDisplay.getId(), PortletRequest.ACTION_PHASE) %>',
			baseResourceURL:
				'<%= PortletURLFactoryUtil.create(request, portletDisplay.getId(), PortletRequest.RESOURCE_PHASE) %>',
			invokerURL: themeDisplay.getPathContext() + '/api/jsonws/invoke',
			namespace: '<portlet:namespace />',
			userId: themeDisplay.getUserId(),
		});

		var destroyInstance = function (event) {
			if (event.portletId === '<%= portletDisplay.getId() %>') {
				remoteServices.destroy();

				Liferay.component('<portlet:namespace />remoteServices', null);

				Liferay.detach('destroyPortlet', destroyInstance);
			}
		};

		Liferay.on('destroyPortlet', destroyInstance);

		return remoteServices;
	});
</aui:script>
<div id="committee-calendar">
<clay:container-fluid
	cssClass="calendar-portlet-column-parent"
>
	<clay:row>
		<c:if test="<%= !displaySchedulerOnly %>">
			<clay:col
				cssClass='<%= "calendar-portlet-column-options " + (columnOptionsVisible ? StringPool.BLANK : " ") %>'
				id='<%= liferayPortletResponse.getNamespace() + "columnOptions" %>'
				md="12"
			>
			<h1>PAF Calendar</h1>
				<div class="calendar-portlet-mini-calendar" id="<portlet:namespace />miniCalendarContainer"></div>
				
				<c:if test="<%=hasPage%>">
                	<a id="calendar-admin" href="<%= completeUrl %>">Calendar management</a>
                </c:if>

				<div id="listed-events">
				</div>
<script>
Liferay.on(
	'allPortletsReady',

	/*
	This function gets loaded when everything, including the portlets, is on
	the page.
	*/

	function() {
		initCalendarCom();
		setTimeout(function(){ 
			console.log("click event");
			$("#committee-calendar td").click(function (e) {
				console.log("TD clicked 2");
				$("#committee-calendar td").removeClass("selected-day lfr-current-day");
				e.stopPropagation();
				$(this).addClass("selected-day");
				var dia = $(this).html();
				var mes = $(this).closest("table").attr("aria-label").split(" ")[0];
				var ao = $(this).closest("table").attr("aria-label").split(" ")[1];

				console.log(dia + "/" + mes + "/" + ao);
				var cadenainicio = mes + " " + dia + " " + ao + " 00:00";
				var cadenafinal = mes + " " + dia + " " + ao + " 23:59";
				var initialDate = new Date(cadenainicio).getTime();
				var finalDate = new Date(cadenafinal).getTime();
				var id = $(".calendar-id-hidden").val();
				console.log(initialDate + " " + finalDate+ "/"+id);

				Liferay.Service(
					'/calendar.calendarbooking/get-calendar-bookings', {
						calendarId: id,
						startTime: initialDate,
						endTime: finalDate
					},
					function (obj) {
						console.log(obj);
						if(obj.length != 0){         
							var html = "<table><thead><tr><th>Date</th><th>Activity</th></tr></thead><tbody>";
							for(var i=0;i<obj.length;i++){
								html += "<tr>";
								var dateInicio = new Date(parseInt(obj[i].startTime));
								var dateFinal = new Date(parseInt(obj[i].endTime));
								var minutes = 0;
								var url = "/c/portal/layout?p_l_id=" + themeDisplay.getPlid() + "&p_v_l_s_g_id=0&_com_liferay_calendar_web_portlet_CalendarPortlet_mvcPath=%2Fview_calendar_booking.jsp&p_p_id=com_liferay_calendar_web_portlet_CalendarPortlet&p_p_lifecycle=0&p_p_state=maximized&_com_liferay_calendar_web_portlet_CalendarPortlet_calendarBookingId=" + obj[i].calendarBookingId;
								if(dateFinal.getMinutes()<10){
									minutes = "0"+dateFinal.getMinutes();
								} else {
									minutes = dateFinal.getMinutes();
								}
								
								html += "<td><a href='"+url+"'>"+(dateInicio.toString()).split(":00 ")[0]+"-"+dateFinal.getHours()+":"+minutes+"</a></td><td>"+
								obj[i].titleCurrentValue + "</br>"+ obj[i].descriptionCurrentValue +"</td></tr>";       
							}         
							html += "</tbody></table>";
							$("#listed-events").html(html);
							$("#listed-events").css('display','block');
						} else {
							$("#listed-events").html("");
						}
					}
				);
			});
		}, 3000);
		hideEmptyEvents();
	}
	);
	
function hideEmptyEvents() {
	console.log("Inside hideEmptyEvents with id " + $(".calendar-id-hidden").val());
	$(".lfr-busy-day").each(function() {
	    var item = $(this);
	    var dia = $(this).html();
	    var mes = $(this).closest("table").attr("aria-label").split(" ")[0];
	    var ao = $(this).closest("table").attr("aria-label").split(" ")[1];

	    console.log(dia + "/" + mes + "/" + ao);
	    var cadenainicio = mes + " " + dia + " " + ao + " 00:00";
	    var cadenafinal = mes + " " + dia + " " + ao + " 23:59";
	    var initialDate = new Date(cadenainicio).getTime();
	    var finalDate = new Date(cadenafinal).getTime();
	    var id = $(".calendar-id-hidden").val();

	    Liferay.Service(
	        '/calendar.calendarbooking/get-calendar-bookings', {
	            calendarId: id,
	            startTime: initialDate,
	            endTime: finalDate
	        },
	        function (obj) {
	            if(obj.length == 0){
	                console.log("remove class");
	                item.removeClass("lfr-busy-day");
	            }
	        }
	    );
	});
}

function initCalendarCom(){
var id = $(".calendar-id-hidden").val();
var f = new Date();
var startCurrentDate = f.setHours(00,00,00);
var finalCurrentDate = f.setHours(23,59,59);
Liferay.Service(
		'/calendar.calendarbooking/get-calendar-bookings', {
          calendarId: id,
          startTime: startCurrentDate,
          endTime: finalCurrentDate
      },
      function (obj) {

          if(obj.length != 0){         
        	  var html = "<table><thead><tr><th>Date</th><th>Activity</th></tr></thead><tbody>";
        	  for(var i=0;i<obj.length;i++){
              	html += "<tr>";
              	var dateInicio = new Date(parseInt(obj[i].startTime));
              	var dateFinal = new Date(parseInt(obj[i].endTime));
              	var minutes = 0;
              	var url = "/c/portal/layout?p_l_id=" + themeDisplay.getPlid() + "&p_v_l_s_g_id=0&_com_liferay_calendar_web_portlet_CalendarPortlet_mvcPath=%2Fview_calendar_booking.jsp&p_p_id=com_liferay_calendar_web_portlet_CalendarPortlet&p_p_lifecycle=0&p_p_state=maximized&_com_liferay_calendar_web_portlet_CalendarPortlet_calendarBookingId=" + obj[i].calendarBookingId;
                  if(dateFinal.getMinutes()<10){
                     minutes = "0"+dateFinal.getMinutes();
                  } else {
                      minutes = dateFinal.getMinutes();
                  }
              	
                  html += "<td><a href='"+url+"'>"+(dateInicio.toString()).split(":00 ")[0]+"-"+dateFinal.getHours()+":"+minutes+"</a></td><td>"+
                  obj[i].titleCurrentValue + "</br>"+ obj[i].descriptionCurrentValue +"</td></tr>";       
              }         
              html += "</tbody></table>";
              $("#listed-events").html(html);
              $("#listed-events").css('display','block');
          }
      }
  );

var celdas = $(".yui3-calendar-day");

for(var i=0;i<celdas.length;i++){
    if(celdas[i].textContent == f.getDate()){
       celdas[i].classList.add("lfr-current-day");
    }
}
}

</script>
						
			
				
				
				<script>
			     function openAllComitteesActionLog(){
			            $(".allComitteesActionLog").css("visibility","visible");
			            $(".allComitteesTitleActionLog").css("font-weight","700");
			            $(".allComitteesTitleActionLog").css("border-bottom","solid");
			            $(".allComitteesTitleActionLog").css("border-bottom-color","#4d8934");

			            $(".myComittesActionLog").css("visibility","hidden");
			            $(".myComitteesTitleActionLog").css("font-weight","normal");
			            $(".myComitteesTitleActionLog").css("border-bottom","none");
			            $(".myComitteesTitleActionLog").css("border-bottom-color","transparent");
			        }

			        function openMyComitteesActionLog(){
			            $(".myComittesActionLog").css("visibility","visible");
			            $(".myComitteesTitleActionLog").css("font-weight","700");
			            $(".myComitteesTitleActionLog").css("border-bottom","solid");
			            $(".myComitteesTitleActionLog").css("border-bottom-color","#4d8934");

			            $(".allComitteesActionLog").css("visibility","hidden");
			            $(".allComitteesTitleActionLog").css("font-weight","normal");
			            $(".allComitteesTitleActionLog").css("border-bottom","none");
			            $(".allComitteesTitleActionLog").css("border-bottom-color","transparent");
			        }
				</script>
				<div id="<portlet:namespace />calendarListContainer">
					<div class="calendar-portlet-list">
						<c:if test="<%= themeDisplay.isSignedIn() && showUserEvents %>">
							<div class="calendar-portlet-list-header toggler-header-expanded">
								<span class="calendar-portlet-list-arrow"></span>

								<span class="calendar-portlet-list-text"><liferay-ui:message key="my-calendars" /></span>
							</div>

							<c:if test="<%= userCalendarResource != null %>">
								<span class="calendar-list-item-arrow calendar-resource-arrow" data-calendarResourceId="<%= userCalendarResource.getCalendarResourceId() %>" tabindex="0"><clay:icon symbol="caret-bottom" /></span>
							</c:if>
						</c:if>

						<div class="calendar-portlet-calendar-list" id="<portlet:namespace />myCalendarList"></div>
					</div>

					<div class="calendar-portlet-list">
						<c:if test="<%= showSiteCalendars %>">
							<div class="calendar-portlet-list-header toggler-header-expanded">
								<span class="calendar-portlet-list-arrow"></span>

								<span class="calendar-portlet-list-text"><liferay-ui:message arguments="<%= HtmlUtil.escape(groupCalendarResource.getName(locale)) %>" key="x-calendars" /></span>
							</div>

							<c:if test="<%= CalendarResourcePermission.contains(permissionChecker, groupCalendarResource, CalendarActionKeys.ADD_CALENDAR) %>">
								<span class="calendar-list-item-arrow calendar-resource-arrow" data-calendarResourceId="<%= groupCalendarResource.getCalendarResourceId() %>" tabindex="0"><clay:icon symbol="caret-bottom" /></span>
							</c:if>

							<div class="calendar-portlet-calendar-list" id="<portlet:namespace />siteCalendarList"></div>
						</c:if>
					</div>

					<div class="calendar-portlet-list">
						<c:if test="<%= themeDisplay.isSignedIn() %>">
							<div class="calendar-portlet-list-header toggler-header-expanded">
								<span class="calendar-portlet-list-arrow"></span>

								<span class="calendar-portlet-list-text"><liferay-ui:message key="other-calendars" /></span>
							</div>

							<div class="calendar-portlet-calendar-list" id="<portlet:namespace />otherCalendarList">
								<input class="calendar-portlet-add-calendars-input" id="<portlet:namespace />addOtherCalendar" placeholder="<liferay-ui:message key="add-other-calendars" />" type="text" />
							</div>
						</c:if>
					</div>
				</div>
			</clay:col>
		</c:if>

		<clay:col
			cssClass="calendar-portlet-column-grid"
			id="scheduler_calendar_right"
			md="<%= (columnOptionsVisible && !displaySchedulerOnly) ? String.valueOf(9) : String.valueOf(12) %>"
		>
			<c:if test="<%= !displaySchedulerOnly %>">
				<div class="calendar-portlet-column-toggler" id="<portlet:namespace />columnToggler">
					<clay:icon
						id='<%= liferayPortletResponse.getNamespace() + "columnTogglerIcon" %>'
						symbol='<%= columnOptionsVisible ? "caret-left" : "caret-right" %>'
					/>
				</div>
			</c:if>

			<input class="calendar-id-hidden" type="hidden" value="" />

			<liferay-util:include page="/scheduler.jsp" servletContext="<%= application %>">
				<liferay-util:param name="activeView" value="<%= activeView %>" />
				<liferay-util:param name="date" value="<%= String.valueOf(date) %>" />

				<portlet:renderURL var="editCalendarBookingURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
					<portlet:param name="mvcPath" value="/edit_calendar_booking.jsp" />
					<portlet:param name="activeView" value="{activeView}" />
					<portlet:param name="allDay" value="{allDay}" />
					<portlet:param name="calendarBookingId" value="{calendarBookingId}" />
					<portlet:param name="calendarId" value="{calendarId}" />
					<portlet:param name="date" value="{date}" />
					<portlet:param name="endTimeDay" value="{endTimeDay}" />
					<portlet:param name="endTimeHour" value="{endTimeHour}" />
					<portlet:param name="endTimeMinute" value="{endTimeMinute}" />
					<portlet:param name="endTimeMonth" value="{endTimeMonth}" />
					<portlet:param name="endTimeYear" value="{endTimeYear}" />
					<portlet:param name="instanceIndex" value="{instanceIndex}" />
					<portlet:param name="startTimeDay" value="{startTimeDay}" />
					<portlet:param name="startTimeHour" value="{startTimeHour}" />
					<portlet:param name="startTimeMinute" value="{startTimeMinute}" />
					<portlet:param name="startTimeMonth" value="{startTimeMonth}" />
					<portlet:param name="startTimeYear" value="{startTimeYear}" />
					<portlet:param name="titleCurrentValue" value="{titleCurrentValue}" />
				</portlet:renderURL>

				<liferay-util:param name="editCalendarBookingURL" value="<%= editCalendarBookingURL %>" />

				<liferay-util:param name="hideAgendaView" value="<%= String.valueOf(!showAgendaView) %>" />
				<liferay-util:param name="hideDayView" value="<%= String.valueOf(!showDayView) %>" />
				<liferay-util:param name="hideWeekView" value="<%= String.valueOf(!showWeekView) %>" />
				<liferay-util:param name="hideMonthView" value="<%= String.valueOf(!showMonthView) %>" />
				<liferay-util:param name="readOnly" value="<%= Boolean.FALSE.toString() %>" />

				<liferay-security:permissionsURL
					modelResource="<%= CalendarBooking.class.getName() %>"
					modelResourceDescription="{modelResourceDescription}"
					resourceGroupId="{resourceGroupId}"
					resourcePrimKey="{resourcePrimKey}"
					var="permissionsCalendarBookingURL"
					windowState="<%= LiferayWindowState.POP_UP.toString() %>"
				/>

				<liferay-util:param name="permissionsCalendarBookingURL" value="<%= permissionsCalendarBookingURL %>" />

				<liferay-util:param name="showAddEventBtn" value="<%= String.valueOf((defaultCalendar != null) && CalendarPermission.contains(permissionChecker, defaultCalendar, CalendarActionKeys.MANAGE_BOOKINGS)) %>" />
				<liferay-util:param name="showSchedulerHeader" value="<%= String.valueOf(displaySchedulerHeader) %>" />

				<portlet:renderURL var="viewCalendarBookingURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
					<portlet:param name="mvcPath" value="/view_calendar_booking.jsp" />
					<portlet:param name="calendarBookingId" value="{calendarBookingId}" />
					<portlet:param name="instanceIndex" value="{instanceIndex}" />
				</portlet:renderURL>

				<liferay-util:param name="viewCalendarBookingURL" value="<%= viewCalendarBookingURL %>" />
			</liferay-util:include>
		</clay:col>
	</clay:row>
	
</clay:container-fluid>
</div>
<div id="<portlet:namespace />message"></div>

<c:if test="<%= !displaySchedulerOnly %>">
	<%@ include file="/view_calendar_menus.jspf" %>
</c:if>



<aui:script use="liferay-calendar-list,liferay-calendar-util,liferay-scheduler">
	Liferay.CalendarUtil.USER_CLASS_NAME_ID = <%= PortalUtil.getClassNameId(User.class) %>;

	var calendarContainer = Liferay.component(
		'<portlet:namespace />calendarContainer'
	);

	var syncCalendarsMap = function () {
		var calendarLists = [];

		<c:if test="<%= themeDisplay.isSignedIn() || (groupCalendarResource != null) %>">
			calendarLists.push(window.<portlet:namespace />myCalendarList);
		</c:if>

		<c:if test="<%= themeDisplay.isSignedIn() %>">
			calendarLists.push(window.<portlet:namespace />otherCalendarList);
		</c:if>

		<c:if test="<%= showSiteCalendars %>">
			calendarLists.push(window.<portlet:namespace />siteCalendarList);
		</c:if>

		calendarContainer.syncCalendarsMap(calendarLists);
	};

	window.<portlet:namespace />syncCalendarsMap = syncCalendarsMap;

	window.<portlet:namespace />calendarLists = {};

	<c:if test="<%= themeDisplay.isSignedIn() || (groupCalendarResource != null) %>">
		window.<portlet:namespace />myCalendarList = new Liferay.CalendarList({
			after: {
				calendarsChange: syncCalendarsMap,
				'scheduler-calendar:visibleChange': function (event) {
					syncCalendarsMap();

					<portlet:namespace />refreshVisibleCalendarRenderingRules();
				},
			},
			boundingBox: '#<portlet:namespace />myCalendarList',

			<%
			updateCalendarsJSONArray(request, userCalendarsJSONArray);
			%>

			calendars: <%= userCalendarsJSONArray %>,
			scheduler: <portlet:namespace />scheduler,
			showCalendarResourceName: false,
			simpleMenu: window.<portlet:namespace />calendarsMenu,
			visible: <%= !displaySchedulerOnly && themeDisplay.isSignedIn() %>,
		}).render();

		<c:if test="<%= userCalendarResource != null %>">
			window.<portlet:namespace />calendarLists[
				'<%= userCalendarResource.getCalendarResourceId() %>'
			] = window.<portlet:namespace />myCalendarList;
		</c:if>
	</c:if>

	<c:if test="<%= themeDisplay.isSignedIn() %>">
		window.<portlet:namespace />otherCalendarList = new Liferay.CalendarList({
			after: {
				calendarsChange: function (event) {
					syncCalendarsMap();

					<portlet:namespace />scheduler.load();

					var calendarIds = A.Array.invoke(event.newVal, 'get', 'calendarId');

					Liferay.Util.Session.set(
						'com.liferay.calendar.web_otherCalendars',
						calendarIds.join()
					);
				},
				'scheduler-calendar:visibleChange': function (event) {
					syncCalendarsMap();

					<portlet:namespace />refreshVisibleCalendarRenderingRules();
				},
			},
			boundingBox: '#<portlet:namespace />otherCalendarList',

			<%
			updateCalendarsJSONArray(request, otherCalendarsJSONArray);
			%>

			calendars: <%= otherCalendarsJSONArray %>,
			scheduler: <portlet:namespace />scheduler,
			simpleMenu: window.<portlet:namespace />calendarsMenu,
			visible: <%= !displaySchedulerOnly %>,
		}).render();
	</c:if>

	<c:if test="<%= showSiteCalendars %>">
		window.<portlet:namespace />siteCalendarList = new Liferay.CalendarList({
			after: {
				calendarsChange: syncCalendarsMap,
				'scheduler-calendar:visibleChange': function (event) {
					syncCalendarsMap();

					<portlet:namespace />refreshVisibleCalendarRenderingRules();
				},
			},
			boundingBox: '#<portlet:namespace />siteCalendarList',

			<%
			updateCalendarsJSONArray(request, groupCalendarsJSONArray);
			%>

			calendars: <%= groupCalendarsJSONArray %>,
			scheduler: <portlet:namespace />scheduler,
			showCalendarResourceName: false,
			simpleMenu: window.<portlet:namespace />calendarsMenu,
			visible: <%= !displaySchedulerOnly %>,
		}).render();

		window.<portlet:namespace />calendarLists[
			'<%= groupCalendarResource.getCalendarResourceId() %>'
		] = window.<portlet:namespace />siteCalendarList;
	</c:if>

	syncCalendarsMap();

	A.each(calendarContainer.get('availableCalendars'), function (item, index) {
		item.on({
			visibleChange: function (event) {
				var instance = this;

				var calendar = event.currentTarget;

				Liferay.Util.Session.set(
					'com.liferay.calendar.web_calendar' +
						calendar.get('calendarId') +
						'Visible',
					event.newVal
				);
			},
		});
	});
</aui:script>

<aui:script use="aui-base,aui-datatype,liferay-calendar-session-listener">
	window.<portlet:namespace />refreshSchedulerEventTooltipTitle = function (
		schedulerEvent
	) {
		schedulerEvent
			.get('node')
			.attr(
				'title',
				Liferay.Util.unescapeHTML(schedulerEvent.get('content'))
			);
	};

	<portlet:namespace />scheduler.after(['scheduler-events:load'], function (
		event
	) {
		event.currentTarget.eachEvent(
			<portlet:namespace />refreshSchedulerEventTooltipTitle
		);
	});

	<portlet:namespace />scheduler.load();

	var calendarContainer = Liferay.component(
		'<portlet:namespace />calendarContainer'
	);

	new Liferay.CalendarSessionListener({
		calendars: calendarContainer.get('availableCalendars'),
		scheduler: <portlet:namespace />scheduler,
	});
</aui:script>

<aui:script>
	var destroyMenus = function (event) {
		if (window.<portlet:namespace />calendarListsMenu) {
			window.<portlet:namespace />calendarListsMenu.destroy();
		}

		if (window.<portlet:namespace />colorPicker) {
			window.<portlet:namespace />colorPicker.destroy();
		}

		var myCalendarList = window.<portlet:namespace />myCalendarList;
		var otherCalendarList = window.<portlet:namespace />otherCalendarList;
		var siteCalendarList = window.<portlet:namespace />siteCalendarList;

		if (myCalendarList && myCalendarList.simpleMenu) {
			myCalendarList.simpleMenu.destroy();
			myCalendarList.destroy();
		}

		if (otherCalendarList && otherCalendarList.simpleMenu) {
			otherCalendarList.simpleMenu.destroy();
			otherCalendarList.destroy();
		}

		if (siteCalendarList && siteCalendarList.simpleMenu) {
			siteCalendarList.simpleMenu.destroy();
			siteCalendarList.destroy();
		}

		Liferay.detach(
			'<%= portletDisplay.getId() %>:portletRefreshed',
			destroyMenus
		);
		Liferay.detach('destroyPortlet', destroyMenus);
	};
	Liferay.on('<%= portletDisplay.getId() %>:portletRefreshed', destroyMenus);
	Liferay.on('destroyPortlet', destroyMenus);
</aui:script>

<%!
protected void updateCalendarsJSONArray(HttpServletRequest request, JSONArray calendarsJSONArray) {
	for (int i = 0; i < calendarsJSONArray.length(); i++) {
		JSONObject jsonObject = calendarsJSONArray.getJSONObject(i);

		long calendarId = jsonObject.getLong("calendarId");

		jsonObject.put("color", GetterUtil.getString(SessionClicks.get(request, "com.liferay.calendar.web_calendar" + calendarId + "Color", jsonObject.getString("color"))));
		jsonObject.put("visible", GetterUtil.getBoolean(SessionClicks.get(request, "com.liferay.calendar.web_calendar" + calendarId + "Visible", "true")));
	}
}
%>
