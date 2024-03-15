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

<%@ include file="/document_library/init.jsp" %>
<%@page import="rec.document.and.media.DMUtils"%>
<%@page import="java.util.HashMap"%>

<%
FileEntry fileEntry = (FileEntry)request.getAttribute("info_panel.jsp-fileEntry");
FileVersion fileVersion = (FileVersion)request.getAttribute("info_panel.jsp-fileVersion");
boolean hideActions = GetterUtil.getBoolean(request.getAttribute("info_panel_file_entry.jsp-hideActions"));

DLPortletInstanceSettings dlPortletInstanceSettings = dlRequestHelper.getDLPortletInstanceSettings();
DLViewFileVersionDisplayContext dlViewFileVersionDisplayContext = dlDisplayContextProvider.getDLViewFileVersionDisplayContext(request, response, fileVersion);

long assetClassPK = DLAssetHelperUtil.getAssetClassPK(fileEntry, fileVersion);
%>

<div class="sidebar-header">
	<c:if test="<%= !hideActions %>">
		<ul class="sidebar-header-actions">
			<li>
				<liferay-util:include page="/document_library/file_entry_action.jsp" servletContext="<%= application %>" />
			</li>
		</ul>
	</c:if>

	<h1 class="sidebar-title">
		<%= HtmlUtil.escape(fileVersion.getTitle()) %>
	</h1>

	<c:if test="<%= dlViewFileVersionDisplayContext.isVersionInfoVisible() %>">
		<clay:label
			displayType="info"
			label='<%= LanguageUtil.get(request, "version") + StringPool.SPACE + fileVersion.getVersion() %>'
		/>
	</c:if>

	<aui:model-context bean="<%= fileVersion %>" model="<%= DLFileVersion.class %>" />

</div>

<div class="sidebar-body">

	<%
	String tabsNames = "details";

	if (dlViewFileVersionDisplayContext.isVersionInfoVisible()) {
		tabsNames += ",versions";
	}
	%>

	<liferay-ui:tabs
		cssClass="navbar-no-collapse"
		names="<%= tabsNames %>"
		param='<%= "tabs_" + fileEntry.getFileEntryId() %>'
		refresh="<%= false %>"
	>
		<liferay-ui:section>

			<%
			String thumbnailSrc = DLURLHelperUtil.getThumbnailSrc(fileEntry, fileVersion, themeDisplay);
			%>

			<c:if test="<%= Validator.isNotNull(thumbnailSrc) %>">
				<div class="aspect-ratio aspect-ratio-16-to-9 sidebar-panel thumbnail">
					<img alt="<liferay-ui:message escapeAttribute="<%= true %>" key="thumbnail" />" class="aspect-ratio-item-center-middle aspect-ratio-item-fluid" src="<%= DLURLHelperUtil.getThumbnailSrc(fileEntry, fileVersion, themeDisplay) %>" />
				</div>
			</c:if>
			
			<c:if test="<%= dlViewFileVersionDisplayContext.isDownloadLinkVisible() || dlViewFileVersionDisplayContext.isSharingLinkVisible() %>">
				<div class="sidebar-section">
					<div class="btn-group sidebar-panel">
						<c:if test="<%= dlViewFileVersionDisplayContext.isDownloadLinkVisible() %>">
							<c:choose>
								<c:when test="<%= PropsValues.DL_FILE_ENTRY_CONVERSIONS_ENABLED && DocumentConversionUtil.isEnabled() %>">

									<%
									String[] conversions = DocumentConversionUtil.getConversions(fileVersion.getExtension());
									%>

									<c:choose>
										<c:when test="<%= conversions.length > 0 %>">
											<div class="btn-group-item" data-analytics-file-entry-id="<%= String.valueOf(fileEntry.getFileEntryId()) %>">
												<clay:dropdown-menu
													dropdownItems='<%=
														new JSPDropdownItemList(pageContext) {
															{
																ThemeDisplay themeDisplay = (ThemeDisplay)httpServletRequest.getAttribute(WebKeys.THEME_DISPLAY);

																Map<String, Object> data = HashMapBuilder.<String, Object>put(
																	"analytics-file-entry-id", String.valueOf(fileEntry.getFileEntryId())
																).build();

																add(
																	dropdownItem -> {
																		dropdownItem.setData(data);
																		dropdownItem.setHref(DLURLHelperUtil.getDownloadURL(fileEntry, fileVersion, themeDisplay, StringPool.BLANK, false, true));
																		dropdownItem.setLabel(LanguageUtil.get(httpServletRequest, "this-version"));
																		dropdownItem.setSeparator(true);
																	});

																addGroup(
																	dropdownGroupItem -> {
																		dropdownGroupItem.setDropdownItems(
																			new DropdownItemList() {
																				{
																					for (String conversion : conversions) {
																						add(
																							dropdownItem -> {
																								dropdownItem.setData(data);
																								dropdownItem.setHref(DLURLHelperUtil.getDownloadURL(fileEntry, fileVersion, themeDisplay, "&targetExtension=" + conversion));
																								dropdownItem.setLabel(StringUtil.toUpperCase(conversion));
																							});
																					}
																				}
																			});
																		dropdownGroupItem.setLabel(LanguageUtil.get(httpServletRequest, "convert-to"));
																	});
															}
														}
													%>'
													label="download"
													small="<%= true %>"
												/>
											</div>
										</c:when>
										<c:otherwise>
											<div class="btn-group-item">
												<clay:link
													data-analytics-file-entry-id="<%= fileEntry.getFileEntryId() %>"
													displayType="primary"
													href="<%= DLURLHelperUtil.getDownloadURL(fileEntry, fileVersion, themeDisplay, StringPool.BLANK, false, true) %>"
													label="download"
													small="<%= true %>"
													title='<%= LanguageUtil.format(resourceBundle, "file-size-x", LanguageUtil.formatStorageSize(fileVersion.getSize(), locale), false) %>'
													type="button"
												/>
											</div>
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
									<div class="btn-group-item" data-analytics-file-entry-id="<%= String.valueOf(fileEntry.getFileEntryId()) %>">
										<clay:link
											displayType="primary"
											href="<%= DLURLHelperUtil.getDownloadURL(fileEntry, fileVersion, themeDisplay, StringPool.BLANK, false, true) %>"
											label="download"
											small="<%= true %>"
											title='<%= LanguageUtil.format(resourceBundle, "file-size-x", LanguageUtil.formatStorageSize(fileVersion.getSize(), locale), false) %>'
											type="button"
										/>
									</div>
								</c:otherwise>
							</c:choose>
						</c:if>
					</div>
				</div>
			</c:if>

			<dl class="sidebar-dl sidebar-section">
				<c:if test="<%= fileVersion.getModel() instanceof DLFileVersion %>">

					<%
					DLFileVersion dlFileVersion = (DLFileVersion)fileVersion.getModel();

					DLFileEntryType dlFileEntryType = dlFileVersion.getDLFileEntryType();
					%>

					<dt class="sidebar-dt">
						<liferay-ui:message key="Process Area" />
					</dt>
					<dd class="sidebar-dd">
						<%= HtmlUtil.escape(dlFileEntryType.getName(locale)) %>
					</dd>
				</c:if>

				<c:if test="<%= Validator.isNotNull(fileVersion.getExtension()) %>">
					<dt class="sidebar-dt">
						<liferay-ui:message key="extension" />
					</dt>
					<dd class="sidebar-dd">
						<%= HtmlUtil.escape(fileVersion.getExtension()) %>
					</dd>
				</c:if>

				<dt class="sidebar-dt">
					<liferay-ui:message key="size" />
				</dt>
				<dd class="sidebar-dd">
					<%= HtmlUtil.escape(LanguageUtil.formatStorageSize(fileVersion.getSize(), locale)) %>
				</dd>
				<dt class="sidebar-dt">
					<liferay-ui:message key="Uploaded date" />
				</dt>
				<dd class="sidebar-dd">
					<liferay-ui:message arguments="<%= new Object[] {dateFormatDateTime.format(fileVersion.getModifiedDate()), HtmlUtil.escape(fileVersion.getStatusByUserName())} %>" key="x-by-x" translateArguments="<%= false %>" />
				</dd>

				<c:if test="<%= Validator.isNotNull(fileEntry.getDescription()) %>">
					<dt class="sidebar-dt">
						<liferay-ui:message key="description" />
					</dt>
					<dd class="sidebar-dd">
						<%= HtmlUtil.replaceNewLine(HtmlUtil.escape(fileVersion.getDescription())) %>
					</dd>
				</c:if>
				
				<% 
				  	HashMap<String, String> valueMetaData = DMUtils.getMetadata(fileEntry);
					String dateFileSearch = valueMetaData.get("RPA_Form_DateUploaded");
				%>
				
				<c:if test="<%= Validator.isNotNull(dateFileSearch) %>">
					<dt class="sidebar-dt">
						<liferay-ui:message key="performance period" />
					</dt>
					<dd class="sidebar-dd">
						<%= HtmlUtil.replaceNewLine(HtmlUtil.escape(dateFileSearch)) %>
					</dd>
				</c:if>

				<liferay-asset:asset-categories-available
					className="<%= DLFileEntryConstants.getClassName() %>"
					classPK="<%= assetClassPK %>"
				>
					<dt class="sidebar-dt">
						<liferay-ui:message key="categories" />
					</dt>
					<dd class="sidebar-dd">
						<liferay-asset:asset-categories-summary
							className="<%= DLFileEntryConstants.getClassName() %>"
							classPK="<%= assetClassPK %>"
							displayStyle="simple-category"
						/>
					</dd>
				</liferay-asset:asset-categories-available>
			</dl>

			<%
			AssetEntry layoutAssetEntry = AssetEntryLocalServiceUtil.fetchEntry(DLFileEntryConstants.getClassName(), assetClassPK);
			%>

			<c:if test="<%= (layoutAssetEntry != null) && dlPortletInstanceSettings.isEnableRelatedAssets() && fileEntry.isSupportsSocial() %>">
				<liferay-asset:asset-links
					assetEntryId="<%= layoutAssetEntry.getEntryId() %>"
				/>
			</c:if>

		</liferay-ui:section>

		<c:if test="<%= dlViewFileVersionDisplayContext.isVersionInfoVisible() %>">
			<liferay-ui:section>

				<%
				request.setAttribute("info_panel.jsp-fileEntry", fileEntry);
				%>

				<liferay-util:include page="/document_library/file_entry_history.jsp" servletContext="<%= application %>" />
			</liferay-ui:section>
		</c:if>
	</liferay-ui:tabs>
</div>

<liferay-frontend:component
	module="document_library/js/InfoPanel.es"
/>
