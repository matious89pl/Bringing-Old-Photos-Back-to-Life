<%@ include file="init.jsp"%>
<portlet:actionURL name="cancelDownload" var="cancelDownload" />
<aui:form action="<%=cancelDownload%>" method="post" name="cancelDownload">

</aui:form>
 
 <script>
 submitForm(document.<portlet:namespace />cancelDownload);
 </script>