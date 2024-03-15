<%@ include file="init.jsp" %>
 
<portlet:actionURL name="addLink" var="addLinkURL" />

  <portlet:renderURL var="viewLinksURL">
        <portlet:param name="mvcPath" value="/view.jsp" />
    </portlet:renderURL>
 <% request.setAttribute("viewLinksURL", viewLinksURL); %>
<%
 long linkId = ParamUtil.getLong(request,"linkId");
%>

<%if (linkId > 0){
	 LinkMenu link = LinkMenuLocalServiceUtil.getLinkMenu(linkId);%>
<p>
	<b><liferay-ui:message key="Update link"/></b>
</p>
<aui:form action="<%=addLinkURL%>" method="post" style=" margin-left: 2rem; margin-top: 2rem;">
<aui:fieldset>
<aui:input name="linkId" type="hidden" value="<%=Validator.isNotNull(link) ? link.getLinkId() : StringPool.BLANK %>"/>
<!-- Link name -->

	<aui:input name="linkName" label="Link Name" value="<%=Validator.isNotNull(link) ? link.getLinkName(): StringPool.BLANK %>"  style="width:500px" >
	   <aui:validator name="required" />
	   <aui:validator name="maxLength">500</aui:validator>
	</aui:input>
	
	<aui:input name="link" label="Link" value="<%=Validator.isNotNull(link) ? link.getLink(): StringPool.BLANK %>" style="width:500px" >
	   <aui:validator name="required" />
	   <aui:validator name="maxLength">500</aui:validator>
	</aui:input>
	
	<aui:input name="linkPosition" value="<%=Validator.isNotNull(link) ? link.getLinkPosition(): StringPool.BLANK %>" style="width:500px" >
	<aui:validator name="required" />
	<aui:validator name="maxLength">500</aui:validator>
	<label wrap="true">Link position: Select a digit between 1 and 10 to occupy the initial position, and subsequently choose another number from the following tenths. eg if the first position is 10 then the next should be 20, and the next 30 and so on.</label>
	</aui:input>
	
	<aui:select name="iconName" value="<%=Validator.isNotNull(link) ? link.getIconName() : StringPool.BLANK %>" label="Select Option"
    defaultValue="None"  style="width:500px">
	  	<%
    		IconConstants icons = new IconConstants();
    	        for (Map.Entry<String, String> currentIcon : icons.iconsValues.entrySet()) {
    	            String key = currentIcon.getKey();
    	            String value = currentIcon.getValue();
    	%>
            <aui:option value="<%=key%>" label="<%=value%>"/>
            <%
            	}
            %>

	</aui:select>
	<aui:button class="btn btn-success" name="Submit" type="submit"/>
	
   <aui:button type="cancel" onClick="<%=viewLinksURL.toString()%>" value="Cancel"></aui:button>
</aui:fieldset>
</aui:form>
<%
} else {
%>
<p>
	<b><liferay-ui:message key="Add a new link"/></b>
</p>
<aui:form action="<%=addLinkURL%>" method="post" style=" margin-left: 2rem; margin-top: 2rem;">
<aui:fieldset>
<aui:input name="linkId" type="hidden" value=""/>
<!-- Link name -->
	<aui:input name="linkName" label="Link Name" value="" style="width:500px" >
	   <aui:validator name="required" />
	   <aui:validator name="maxLength">500</aui:validator>
	</aui:input>
	<aui:input name="link" label="Link"  style="width:500px" >
	   <aui:validator name="required" />
	   <aui:validator name="maxLength">500</aui:validator>
	</aui:input>
	
	<aui:input name="linkPosition" label="Link position" value="" style="width:500px" >
	<aui:validator name="required" />
	<aui:validator name="maxLength">500</aui:validator>
	<label wrap="true">Select a digit between 1 and 10 to occupy the initial position, and subsequently choose another number from the following tenths. e.g if the first position is 10, the next on should be 20, next 30 and so on.</label>
	</aui:input>
	
	<aui:select name="iconName" value="" label="Select Option"
    defaultValue="None"  style="width:500px">
  	<%
    		IconConstants icons = new IconConstants();
    	        for (Map.Entry<String, String> currentIcon : icons.iconsValues.entrySet()) {
    	            String key = currentIcon.getKey();
    	            String value = currentIcon.getValue();
    	%>
            <aui:option value="<%=key%>" label="<%=value%>"/>
            <%
            	}
            %>

	</aui:select>
	<aui:button class="btn btn-success" name="Submit" type="submit"/>
   <aui:button type="cancel" onClick="<%=viewLinksURL.toString()%>" value="Cancel"></aui:button>
</aui:fieldset>
</aui:form>
<%
}
%>
