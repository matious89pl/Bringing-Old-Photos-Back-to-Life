<%@ include file="/html/searchcontainer/init.jsp"%>

<%-- Generate add / edit action URL and set title. --%>
<portlet:actionURL var="assignmentActionURL" name="edicion_add">
	<portlet:param name="redirect" value="${param.redirect}" />
	<portlet:param name="cmd" value="add" />
</portlet:actionURL>
	
	<div class="container-fluid-1280 edit-assignment">

	<h1>Aniadir</h1>


	<aui:form action="${assignmentActionURL}" name="fm">
			
		<aui:fieldset-group markupView="lexicon">
		
			<aui:fieldset>
			
				<%-- Title field. --%>
				
					<aui:input name="name">
					<aui:validator name="required" />
				</aui:input>
				
				<%-- Description field. --%>

				<aui:input name="description">
					<aui:validator name="required" />
				</aui:input>

				
			</aui:fieldset>
		</aui:fieldset-group>
		
		<%--Buttons. --%>
		
		<aui:button-row>
			<aui:button cssClass="btn btn-primary" type="submit" />
			<aui:button cssClass="btn btn-secondary" onClick="${param.redirect}" type="cancel" />
		</aui:button-row>
	</aui:form>

</div>


