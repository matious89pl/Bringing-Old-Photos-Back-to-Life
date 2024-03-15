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

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.liferay.portal.kernel.model.Group" %>
<%@ page import="com.liferay.portal.kernel.service.GroupLocalServiceUtil" %>

<%
	List<Group> filteredResults = new ArrayList<Group>();
	Group committeeSite = GroupLocalServiceUtil.fetchGroup(company.getCompanyId(), "Committees");
%>

<div id="committee-register">

<liferay-ui:success key="membershipRequestSent" message="your-request-was-sent-you-will-receive-a-reply-by-email" />

<liferay-ui:error embed="<%= false %>" key="membershipAlreadyRequested" message="membership-was-already-requested" />

<h1>My Committees</h1>

<clay:navigation-bar
	navigationItems="<%= siteMySitesDisplayContext.getNavigationItems() %>"
/>

<clay:management-toolbar
	displayContext="<%= new SiteMySitesManagementToolbarDisplayContext(request, liferayPortletRequest, liferayPortletResponse, siteMySitesDisplayContext) %>"
/>
<ul id="list_title">
	<li class="left-committees">Committee Name</li>
	<li class="right-committees">Committee Information</li>
</ul>

<c:choose>
<c:when test='<%= committeeSite != null %>'>

<%
	List<Group> results = siteMySitesDisplayContext.getGroupSearchContainer().getResults();
	for(Group group : results){
		if(group.getParentGroupId() == committeeSite.getGroupId()){
			filteredResults.add(group);
		}
	}
	siteMySitesDisplayContext.getGroupSearchContainer().setResults(filteredResults);
	siteMySitesDisplayContext.getGroupSearchContainer().setTotal(filteredResults.size());
%>
					
<aui:form action="<%= siteMySitesDisplayContext.getPortletURL() %>" cssClass="container-fluid-1280" method="get" name="fm">
	<liferay-ui:search-container
		searchContainer="<%= siteMySitesDisplayContext.getGroupSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.portal.kernel.model.Group"
			keyProperty="groupId"
			modelVar="group"
			rowIdProperty="friendlyURL"
		>

			<%
			String siteImageURL = group.getLogoURL(themeDisplay, false);

			String rowURL = StringPool.BLANK;

			if (group.getPublicLayoutsPageCount() > 0) {
				rowURL = group.getDisplayURL(themeDisplay, false);
			}
			else if (Objects.equals(siteMySitesDisplayContext.getTabs1(), "my-sites") && (group.getPrivateLayoutsPageCount() > 0)) {
				rowURL = group.getDisplayURL(themeDisplay, true);
			}

			List<DropdownItem> dropdownItems = siteMySitesDisplayContext.getGroupActionDropdownItems(group);
			%>
					
			<c:choose>
				<c:when test='<%= Objects.equals(siteMySitesDisplayContext.getDisplayStyle(), "descriptive") %>'>
					<c:choose>
						<c:when test="<%= Validator.isNotNull(siteImageURL) %>">
							<liferay-ui:search-container-column-image
								src="<%= siteImageURL %>"
							/>
						</c:when>
						<c:otherwise>
							<liferay-ui:search-container-column-icon
								icon="sites"
							/>
						</c:otherwise>
					</c:choose>
					
					<liferay-ui:search-container-column-text
						colspan="<%= 2 %>"
					>
						<h5>
							<c:choose>
								<c:when test="<%= Validator.isNotNull(rowURL) %>">
									
									<ul id="list_committee">
										<div class="left-committees">
										<li>
											<a href="<%= rowURL %>" target="_blank">
												<strong><%= HtmlUtil.escape(group.getDescriptiveName(locale)) %></strong>										
											</a>
										</li>
										</div>
										<div class="right-committees">
										<li><%= HtmlUtil.escape(group.getDescription(locale)) %></li>
										</div>
									</ul>
								</c:when>
								<c:otherwise>
									<ul id="list_committee">
										<div class="left-committees">
										<li>
											<a href="<%= rowURL %>" target="_blank">
												<strong><%= HtmlUtil.escape(group.getDescriptiveName(locale)) %></strong>										
											</a>
										</li>
										</div>
										<div class="right-committees">
										<li><%= HtmlUtil.escape(group.getDescription(locale)) %></li>
										</div>
									</ul>
								</c:otherwise>
							</c:choose>
						</h5>

						<c:if test='<%= !Objects.equals(siteMySitesDisplayContext.getTabs1(), "my-sites") && Validator.isNotNull(group.getDescription(locale)) %>'>
							<h6 class="text-default">
								<%= HtmlUtil.escape(group.getDescription(locale)) %>
							</h6>
						</c:if>

						<h6 class="text-default">
							<liferay-asset:asset-tags-summary
								className="<%= Group.class.getName() %>"
								classPK="<%= group.getGroupId() %>"
							/>
						</h6>

						<h6 class="text-default">
							<strong><liferay-ui:message key="members" /></strong>: <%= siteMySitesDisplayContext.getGroupUsersCounts(group.getGroupId()) %>
						</h6>
						
						<c:if test='<%= Objects.equals(siteMySitesDisplayContext.getTabs1(), "my-sites") && PropsValues.LIVE_USERS_ENABLED %>'>
							<h6 class="text-default">
								<strong><liferay-ui:message key="online-now" /></strong>: <%= String.valueOf(LiveUsers.getGroupUsersCount(company.getCompanyId(), group.getGroupId())) %>
							</h6>
						</c:if>
					</liferay-ui:search-container-column-text>

					<c:if test="<%= ListUtil.isNotEmpty(dropdownItems) %>">
						<liferay-ui:search-container-column-text>
							<clay:dropdown-actions
								defaultEventHandler="<%= MySitesWebKeys.SITES_DROPDOWN_DEFAULT_EVENT_HANDLER %>"
								dropdownItems="<%= dropdownItems %>"
							/>
						</liferay-ui:search-container-column-text>
					</c:if>
				</c:when>
				<c:when test='<%= Objects.equals(siteMySitesDisplayContext.getDisplayStyle(), "icon") %>'>

					<%
					row.setCssClass("entry-card lfr-asset-item");
					%>

					<liferay-ui:search-container-column-text>
						<clay:vertical-card
							verticalCard="<%= new SiteVerticalCard(group, renderRequest, renderResponse, siteMySitesDisplayContext.getTabs1(), siteMySitesDisplayContext.getGroupUsersCounts(group.getGroupId())) %>"
						/>
					</liferay-ui:search-container-column-text>
				</c:when>
				<c:when test='<%= Objects.equals(siteMySitesDisplayContext.getDisplayStyle(), "list") %>'>
					<liferay-ui:search-container-column-text
						name="name"
						orderable="<%= true %>"
						truncate="<%= true %>"
					>
						<c:choose>
							<c:when test="<%= Validator.isNotNull(rowURL) %>">
								<a href="<%= rowURL %>" target="_blank">
									<strong><%= HtmlUtil.escape(group.getDescriptiveName(locale)) %></strong>
								</a>
							</c:when>
							<c:otherwise>
								<strong><%= HtmlUtil.escape(group.getDescriptiveName(locale)) %></strong>
							</c:otherwise>
						</c:choose>

						<c:if test='<%= !Objects.equals(siteMySitesDisplayContext.getTabs1(), "my-sites") && Validator.isNotNull(group.getDescription(locale)) %>'>
							<br />

							<em><%= HtmlUtil.escape(group.getDescription(locale)) %></em>
						</c:if>
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text
						name="members"
						value="<%= String.valueOf(siteMySitesDisplayContext.getGroupUsersCounts(group.getGroupId())) %>"
					/>

					<c:if test='<%= Objects.equals(siteMySitesDisplayContext.getTabs1(), "my-sites") && PropsValues.LIVE_USERS_ENABLED %>'>
						<liferay-ui:search-container-column-text
							name="online-now"
							value="<%= String.valueOf(LiveUsers.getGroupUsersCount(company.getCompanyId(), group.getGroupId())) %>"
						/>
					</c:if>

					<liferay-ui:search-container-column-text
						name="tags"
					>
						<liferay-asset:asset-tags-summary
							className="<%= Group.class.getName() %>"
							classPK="<%= group.getGroupId() %>"
						/>
					</liferay-ui:search-container-column-text>

					<c:if test="<%= ListUtil.isNotEmpty(dropdownItems) %>">
						<liferay-ui:search-container-column-text>
							<clay:dropdown-actions
								defaultEventHandler="<%= MySitesWebKeys.SITES_DROPDOWN_DEFAULT_EVENT_HANDLER %>"
								dropdownItems="<%= dropdownItems %>"
							/>
						</liferay-ui:search-container-column-text>
					</c:if>
				</c:when>
			</c:choose>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			displayStyle="<%= siteMySitesDisplayContext.getDisplayStyle() %>"
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</aui:form>
</c:when>
</c:choose>

<liferay-frontend:component
	componentId="<%= MySitesWebKeys.SITES_DROPDOWN_DEFAULT_EVENT_HANDLER %>"
	module="js/SiteDropdownDefaultEventHandler.es"
/>

</div>



<script>
$(document).ready(function(){
		document.getElementById('committee-register').getElementsByClassName("nav-link")[0].innerHTML = "My Committees";
		});
 	/*window.onload = function() {
 		document.getElementById('committee-register').getElementsByClassName("nav-link")[0].innerHTML = "My Committees";
 		document.getElementById('committee-register').getElementsByClassName("nav-link")[1].innerHTML = "All Committees";
 		window.location.reload(false);

 	};*/
 		
</script>



<style>

	#committee-register h1 {
	    color: rgb(0, 0, 0);
	    font-size: 20px;
	    font-weight: bold;
		height: 30px;
		letter-spacing: 0.75px;
		line-height: 30px;
	    }
	
	#committee-register h5{
		height: 24px;
		color: rgb(0, 0, 0);
		font-size: 16px;
		font-weight: bold;
		letter-spacing: 0.5px;
		line-height: 24px;
		padding-left:0px;
	}
	    
	.nav-link.active:after{
		background: rgb(77, 137, 52) !important;
		border-radius: 0px;
		height: 2px !important;

	}
	
	.container-fluid-1280{
		padding: 0px;
	}
	
	.list-group-notification .list-group-item{
		box-shadow: none;
		align-items: center;
	}
	
	.navigation-bar-light{
		border-color: transparent;
	}
	
	#list_title{
		padding-left:0px;
		padding-top: 10px;
		margin-bottom: -10px;
	}
	
	#list_title li {
	    display: inline-block;
	    padding-left:0px;
	    padding-right:5px;	     
		height: 18px;
		color: rgb(0, 0, 0);
		font-size: 12px;
		font-weight: normal;
		letter-spacing: 0.25px;
		line-height: 18px;
	}
	
	#list_title .left-committees{
		width: 245px;
	}
	
	#list_committee{
		padding-left:0px;
		display:-webkit-inline-box;
	}
	
	#list_committee li {
	    display: inline-block;
	    padding-left:0px;
	    padding-right:5px;	     
	}
	
	#list_committee .left-committees{
		width: 250px;
	}
	
	#list_committee .right-committees{
		color: rgb(0, 0, 0);
	    font-size: 12px;
	    font-weight: normal;
	    letter-spacing: 0.25px;
	    line-height: 18px;
	    width: 325px;
	    padding-top: 4px;
	}	
	
	li.list-group-item.list-group-item-flex{
		padding: 0px;
	}
	
	nav-link link-unstyled{
		height: auto;
		color: rgb(0, 0, 0);
		font-size: 16px;
		font-weight: bold;
		letter-spacing: 0.5px;
		line-height: 24px;
		padding-bottom: 0px;
	}
	
	.autofit-col.autofit-col-expand{
		padding-left: 0px !important;
	}
	
	#committee-register{
		background: white;
	    box-shadow: 0 1px 4px 0 rgb(0 0 0 / 30%);
	    border-radius: 4px;
	    padding: 20px;
	}
	
</style>
