<!--  ********Author Details******** -->
<!--  Manish Kumar Jaiswal -->
<!--  ********Author Details******** -->
 
<%@page import="com.everis.rec.maintenanceactivity.model.MaintenanceActivity"%>
<%@page import="com.everis.rec.maintenanceactivity.service.MaintenanceActivityLocalServiceUtil"%>
<%@ include file="/init.jsp"%>
<%@page import="java.util.List"%>

<%@page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@page import="javax.portlet.PortletURL"%>

<liferay-portlet:renderURL varImpl="iteratorURL">
	<portlet:param name="mvcPath" value="/html/searchcontainer/details.jsp" />
</liferay-portlet:renderURL>

<liferay-ui:search-container emptyResultsMessage="there-are-no-autodetails" headerNames="Year,Model,VIN,Manufacturer,userId" iteratorURL="<%=iteratorURL%>" delta="10" deltaConfigurable="true" >
	
	<liferay-ui:search-container-results>
		<%
		List<MaintenanceActivity> maintenanceActivityList = (List<MaintenanceActivity>) renderRequest.getAttribute("maintenanceActivityList");
		List<MaintenanceActivity> autoDetailsList = maintenanceActivityList;
		results = ListUtil.subList(autoDetailsList, searchContainer.getStart(), searchContainer.getEnd());
		searchContainer.setTotal(autoDetailsList.size());
		searchContainer.setResults(results);
		%>
	</liferay-ui:search-container-results>
	
	<liferay-ui:search-container-row className="MaintenanceActivity" keyProperty="maintenanceactivityId" modelVar="currentObjectmaintenanceactivity"  >
		
		<liferay-ui:search-container-column-text name="userId" 	property="userId" />	
		<liferay-ui:search-container-column-text name="activityTitle" property="activityTitle" />	
		<liferay-ui:search-container-column-text name="dueDate" property="dueDate" />	
		<liferay-ui:search-container-column-text name="status" property="status" />	
				
	</liferay-ui:search-container-row>
	
	<liferay-ui:search-iterator searchContainer="<%=searchContainer%>" />
</liferay-ui:search-container>
