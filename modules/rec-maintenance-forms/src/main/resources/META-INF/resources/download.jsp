<%@ include file="init.jsp"%>

<portlet:actionURL name="cancelDownload" var="cancelDownload" />

<h3>Available Documents</h3>
<aui:form action="<%=cancelDownload%>" method="post" name="cancelDownload">
	<ul>
	 <c:forEach items="${urlMap}" var="links">
	 	<li>
	 		<img src="<%=themeDisplay.getPathThemeImages()%>/forms/download.svg"> 
	 		<a class="listDocumentsUploadedRFI" href="${links.value}" target="blank">${links.key}</a><br>
	 	</li>   	 
	</c:forEach>
 	</ul>
	<c:if test="${fn:length(urlMap) == 0 }">
		<p>There are no files to be downloaded.</p>
	</c:if>
</aui:form>
 
 <aui:button type="cancel" onClick="<%=cancelDownload%>" />
 
 
 