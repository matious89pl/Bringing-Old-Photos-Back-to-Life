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

<%@ include file="/init.jsp" %>
<%@ page import="com.liferay.portal.kernel.service.LayoutLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.model.Layout" %>
<%@ page import="com.liferay.portal.kernel.service.LayoutSetPrototypeLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.model.LayoutSetPrototype" %>
<%@ page import="com.liferay.portal.kernel.util.LocaleUtil" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "calendar");	 
		 
long id = themeDisplay.getPlid();
Layout mylay = LayoutLocalServiceUtil.getLayout(id);
long layoutSetPrototypeId = mylay.getLayoutSet().getLayoutSetPrototypeId();

boolean isHome = false;
boolean isPA = false;
boolean isCommittee = false;

if(mylay.getFriendlyURL().equals("/recportal")){
	isHome = true;
}

if(layoutSetPrototypeId > 0){
	LayoutSetPrototype layoutSetPrototype = LayoutSetPrototypeLocalServiceUtil.getLayoutSetPrototype(layoutSetPrototypeId);
	if(layoutSetPrototype.getName(LocaleUtil.getDefault()).equals("Organisation")) {
		if(mylay.getFriendlyURL().equals("/home")){
			isPA = true;
		}
	}else if(layoutSetPrototype.getName(LocaleUtil.getDefault()).equals("Committees")) {
		isCommittee = true;	
	}
}


%>

<div id="<portlet:namespace />alert"></div>

<c:if test="<%= themeDisplay.isSignedIn() && !displaySchedulerOnly %>">
<div id="tabs-calendar">
	<clay:navigation-bar
		navigationItems="<%= calendarDisplayContext.getNavigationItems() %>"
	/>
	</div>
</c:if>

<c:choose>
	<c:when test='<%= mylay.getFriendlyURL().equals("/calendar-management") %>'>
		<c:choose>
			<c:when test='<%= tabs1.equals("calendar") %>'>
				<liferay-util:include page="/view_calendar.jsp" servletContext="<%= application %>" />
			</c:when>
			<c:when test='<%= tabs1.equals("resources") %>'>
				<liferay-util:include page="/view_calendar_resources.jsp" servletContext="<%= application %>" />
			</c:when>
		</c:choose>
	</c:when>
	<c:when test='<%= mylay.getFriendlyURL().equals("/calendar-organisation") %>'>
		<c:choose>
			<c:when test='<%= tabs1.equals("calendar") %>'>
				<liferay-util:include page="/view_calendar_organisation.jsp" servletContext="<%= application %>" />
			</c:when>
			<c:when test='<%= tabs1.equals("resources") %>'>
				<liferay-util:include page="/view_calendar_resources.jsp" servletContext="<%= application %>" />
			</c:when>
		</c:choose>
	</c:when>
	<c:when test='<%= mylay.getFriendlyURL().equals("/rpa-calendar-management") %>'>
		<c:choose>
			<c:when test='<%= tabs1.equals("calendar") %>'>
				<liferay-util:include page="/rpa_view_calendar.jsp" servletContext="<%= application %>" />
			</c:when>
			<c:when test='<%= tabs1.equals("resources") %>'>
				<liferay-util:include page="/view_calendar_resources.jsp" servletContext="<%= application %>" />
			</c:when>
		</c:choose>
	</c:when>
	<c:when test='<%= isHome %>'>
		<c:choose>
			<c:when test='<%= tabs1.equals("calendar") %>'>
				<liferay-util:include page="/home_view_calendar.jsp" servletContext="<%= application %>" />
			</c:when>
			<c:when test='<%= tabs1.equals("resources") %>'>
				<liferay-util:include page="/view_calendar_resources.jsp" servletContext="<%= application %>" />
			</c:when>
		</c:choose>
	</c:when>
	<c:when test='<%= isPA %>'>
		<c:choose>
			<c:when test='<%= tabs1.equals("calendar") %>'>
				<liferay-util:include page="/PA_view_calendar.jsp" servletContext="<%= application %>" />
			</c:when>
			<c:when test='<%= tabs1.equals("resources") %>'>
				<liferay-util:include page="/view_calendar_resources.jsp" servletContext="<%= application %>" />
			</c:when>
		</c:choose>
	</c:when>
	<c:otherwise>
		<c:choose>
			<c:when test='<%= tabs1.equals("calendar") %>'>
				<liferay-util:include page="/rec_view_calendar.jsp" servletContext="<%= application %>" />
			</c:when>
			<c:when test='<%= tabs1.equals("resources") %>'>
				<liferay-util:include page="/view_calendar_resources.jsp" servletContext="<%= application %>" />
			</c:when>
		</c:choose>
	</c:otherwise>
</c:choose>
