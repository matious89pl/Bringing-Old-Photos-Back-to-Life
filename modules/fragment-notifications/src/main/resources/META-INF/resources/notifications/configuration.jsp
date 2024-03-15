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

<clay:navigation-bar
	navigationItems='<%=
		new JSPNavigationItemList(pageContext) {
			{
				add(
					navigationItem -> {
						navigationItem.setActive(true);
						navigationItem.setLabel(LanguageUtil.get(httpServletRequest, "notifications"));
					});
			}
		}
	%>'
/>

<div class="manage-notifications-content portlet-configuration-setup">
	<div class="manage-notifications" id="<portlet:namespace />manageNotifications">
		<portlet:actionURL name="updateUserNotificationDelivery" var="updateUserNotificationDeliveryURL">
			<portlet:param name="redirect" value="<%= currentURL %>" />
		</portlet:actionURL>

		<liferay-frontend:edit-form
			action="<%= updateUserNotificationDeliveryURL %>"
			fluid="<%= true %>"
			name="fm"
		>

			<%
			Map<String, List<UserNotificationDefinition>> userNotificationDefinitionsMap = new TreeMap<String, List<UserNotificationDefinition>>(new PortletIdComparator(locale));

			userNotificationDefinitionsMap.putAll(UserNotificationManagerUtil.getActiveUserNotificationDefinitions());

			List<Long> userNotificationDeliveryIds = new ArrayList<Long>();
			%>

			<liferay-frontend:edit-form-body>
				<liferay-frontend:fieldset-group>

					<%
					boolean first = true;

					for (Map.Entry<String, List<UserNotificationDefinition>> entry : userNotificationDefinitionsMap.entrySet()) {
						Portlet portlet = PortletLocalServiceUtil.getPortletById(entry.getKey());
						String label = PortalUtil.getPortletTitle(portlet, application, locale);
						int count = 0;
					%>

						<liferay-frontend:fieldset
							collapsed="<%= !first %>"
							collapsible="<%= true %>"
							label="<%= label %>"
						>
							<table class="table table-autofit table-condensed table-responsive">
								<tbody>

									<%
									List<UserNotificationDefinition> userNotificationDefinitions = entry.getValue();

									for (int i = 0; i < userNotificationDefinitions.size(); i++) {
										UserNotificationDefinition userNotificationDefinition = userNotificationDefinitions.get(i);

										Map<Integer, UserNotificationDeliveryType> userNotificationDeliveryTypesMap = userNotificationDefinition.getUserNotificationDeliveryTypes();
									%>
									
										<c:if test="<%= i == 0 %>">
											<tr>
												<% if(label.equalsIgnoreCase("CUSTOM NOTIFICATION")) {%>
												<th class="table-cell-content">
													Receive a notification when someone:
												</th>
												<% } else { %>
												<th class="table-cell-content">
													Receive a notification when:
												</th>
												<% } %>		
												<%
												for (Map.Entry<Integer, UserNotificationDeliveryType> userNotificationDeliveryTypeEntry : userNotificationDeliveryTypesMap.entrySet()) {
													UserNotificationDeliveryType userNotificationDeliveryType = userNotificationDeliveryTypeEntry.getValue();

												%>
													
													<th class="lfr-<%= userNotificationDeliveryType.getName() %>-column">
														<%= LanguageUtil.get(request, userNotificationDeliveryType.getName()) %>
													</th>

												<%
												}
												%>

											</tr>
										</c:if>

										<tr>
											<td class="table-cell-content">
												<% if(label.equalsIgnoreCase("BLOGS")) {
													if(count == 0) {%>
														a new blog entry is added to a blog you are subscribed to.
													<% } else { %>
														a blog entry you are subscribed to is updated.	
													<% } %>

												<% } else if(label.equalsIgnoreCase("COMMENTS")) {
													if(count == 0) {%>
														a comment is added to comments you are subscribed to.
													<% } else { %>
														a comment you are subscribed to is updated.
													<% } %>

												<% } else if(label.equalsIgnoreCase("CONTACTS CENTER")) {%>
													you receive a social relationship request.

												<% } else if(label.equalsIgnoreCase("DOCUMENTS AND MEDIA")) {
													if(count == 0) {%>
														a new document is added to  a folder you are subscribed to.
													<% } else { %>
														a document you are subscribed to is updated.
													<% } %>

												<% } else if(label.equalsIgnoreCase("INVITE MEMBERS")) {%>
													you are sent a membership request.

												<% } else if(label.equalsIgnoreCase("MENTIONS")) {%>
													you are mentioned in a blog entry, comment, or message boards message.

												<% } else if(label.equalsIgnoreCase("MESSAGE BOARDS")) {
													if(count == 0) {%>
														a new post is added to  a thread or category you are subscribed to.
													<% } else { %>
														a post you are subscribed to is updated.
													<% } %>

												<% } else if(label.equalsIgnoreCase("MY WORKFLOW TASKS")) {%>
													an interaction within my workflow tasks has occurred.

												<% } else if(label.equalsIgnoreCase("REMEDIATION ADDED TO REMEDIATION LOG")) {%>
													an RFI is added to the RFI log.

												<% } else if(label.equalsIgnoreCase("SEGMENTS EXPERIMENT")) {%>
													the status of your A/B tests has changed

												<% } else if(label.equalsIgnoreCase("SHARING")) {
													if(count == 0) {%>
														Content is shared with you.
													<% } else { %>
														your permissions for content shared with you is modified.
													<% } %>

												<% } else if(label.equalsIgnoreCase("WEB CONTENT")) {
													if(count == 0) {%>
														new web content is added to  a folder you are subscribed to.
													<% } else if(count == 1) { %>
														web content you are subscribed to moves from Web Folder.
													<% } else if(count == 2) { %>
														web content you are subscribed is restored from trash Folder.
													<% } else if(count == 3) { %>
														web content you are subscribed to is moved into Web Folder.
													<% } else if(count == 4) { %>
														web content you are subscribed to moves to Trash.
													<% } else { %>
														web content you are subscribed to is updated.
													<% } %>
									
												<% } else if(label.equalsIgnoreCase("WIKI")) {
													if(count == 0) {%>
														a new wiki page is added to a wiki you are subscribed to.
													<% } else { %>
														a wiki page you are subscribed to is updated.
													<% } %>
													
												<% } else { %>
													<liferay-ui:message key="<%= userNotificationDefinition.getDescription(locale) %>" />
												<% }
												count++;%>
											</td>

											<%
											for (Map.Entry<Integer, UserNotificationDeliveryType> userNotificationDeliveryTypeEntry : userNotificationDeliveryTypesMap.entrySet()) {
												UserNotificationDeliveryType userNotificationDeliveryType = userNotificationDeliveryTypeEntry.getValue();

												UserNotificationDelivery userNotificationDelivery = UserNotificationDeliveryLocalServiceUtil.getUserNotificationDelivery(themeDisplay.getUserId(), entry.getKey(), userNotificationDefinition.getClassNameId(), userNotificationDefinition.getNotificationType(), userNotificationDeliveryType.getType(), userNotificationDeliveryType.isDefault());

												if (userNotificationDeliveryType.isModifiable()) {
													userNotificationDeliveryIds.add(userNotificationDelivery.getUserNotificationDeliveryId());
												}
											%>

												<td class="lfr-<%= userNotificationDeliveryType.getName() %>-column">
													<div class="checkbox-container">
														<aui:input cssClass="notification-delivery" data-userNotificationDeliveryId="<%= String.valueOf(userNotificationDelivery.getUserNotificationDeliveryId()) %>" disabled="<%= !userNotificationDeliveryType.isModifiable() %>" inlineLabel="<%= Boolean.TRUE.toString() %>" label="" name="<%= String.valueOf(userNotificationDelivery.getUserNotificationDeliveryId()) %>" type="toggle-switch" value="<%= userNotificationDelivery.isDeliver() %>" />
													</div>
												</td>

											<%
											}
											%>

										</tr>

									<%
									}
									%>

								</tbody>
							</table>
						</liferay-frontend:fieldset>

					<%
						first = false;
					}
					%>

				</liferay-frontend:fieldset-group>
			</liferay-frontend:edit-form-body>

			<aui:input name="userNotificationDeliveryIds" type="hidden" value="<%= StringUtil.merge(userNotificationDeliveryIds) %>" />

			<liferay-frontend:edit-form-footer>
				<aui:button type="submit" value="save" />

				<aui:button type="cancel" />
			</liferay-frontend:edit-form-footer>
		</liferay-frontend:edit-form>
	</div>
</div>