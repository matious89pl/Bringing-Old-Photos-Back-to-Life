<%@ include file="init.jsp" %>

<%
String className = GetterUtil.getString(request.getAttribute("className"));
int classPK = GetterUtil.getInteger(request.getAttribute("classPK"));
String formName = "fm" + classPK;
%> 		

<liferay-comment:discussion
	className="<%=className%>"
	classPK="<%=classPK%>"
	formName="<%=formName%>"
	ratingsEnabled="false"
	redirect="<%= themeDisplay.getURLCurrent() %>"
	userId="<%= themeDisplay.getUserId() %>"
/>