<%@ include file="init.jsp" %>


<portlet:defineObjects />

<portlet:actionURL name="viewLinksURL" var="viewLinksURL" />


<aui:button-row cssClass="addLink-buttons">
    <portlet:renderURL var="addLink">
        <portlet:param name="mvcPath" value="/add_link.jsp" />
        
    </portlet:renderURL>
    <aui:button onClick="<%=addLink.toString()%>" value="Add Link"></aui:button>

</aui:button-row>
<liferay-ui:search-container total="<%= LinkMenuLocalServiceUtil.getLinkMenusCount()%>" var="searchContainer" delta="6" deltaConfigurable="true" 
  emptyResultsMessage="Oops. There are no links to display, please add links">
 <liferay-ui:search-container-results results="<%=LinkMenuLocalServiceUtil.getLinkMenus(searchContainer.getStart(),searchContainer.getEnd())%>" />
 	<liferay-ui:search-container-row  className="rec.link.menu.model.LinkMenu" modelVar="link1">
   <liferay-ui:search-container-column-text name="Link Name" property="linkName"/>
   <liferay-ui:search-container-column-text name="MenuLink" property="link"/>
   <liferay-ui:search-container-column-text name="Icon Name" property="iconName"/>
   <liferay-ui:search-container-column-text name="Link Position" property="linkPosition"/>
   <liferay-ui:search-container-column-jsp name="Actions" path="/action.jsp"/>
		</liferay-ui:search-container-row>
	<liferay-ui:search-iterator />
</liferay-ui:search-container>

<liferay-ui:success key="deletedLink" message="Link deleted successfully."/>
<liferay-ui:error key="invalid-url" message="The URL entered is invalid!"/>
<liferay-ui:success key="addedLink" message="Link created successfully."/>
<liferay-ui:success key="editedLink" message="Link edited successfully."/>