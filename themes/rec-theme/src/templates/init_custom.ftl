<#assign
	show_footer = getterUtil.getBoolean(themeDisplay.getThemeSetting("show-footer"))
	show_header = getterUtil.getBoolean(themeDisplay.getThemeSetting("show-header"))
	show_header_search = getterUtil.getBoolean(themeDisplay.getThemeSetting("show-header-search"))
	wrap_widget_page_content = getterUtil.getBoolean(themeDisplay.getThemeSetting("wrap-widget-page-content"))
	service_notifications_list = serviceLocator.findService("com.everis.cproposal.service.recFormArticleLocalService")
	notification_list = service_notifications_list.getUserNotificationsLimit(user_id, 4)
/>
<#assign show_chat = getterUtil.getBoolean(theme_settings["show-chat"])>

<#if wrap_widget_page_content && ((layout.isTypeContent() && themeDisplay.isStateMaximized()) || (layout.getType() == "portlet"))>
	<#assign portal_content_css_class = "container" />
<#else>
	<#assign portal_content_css_class = "" />
</#if>

<#-------------------------------- User Regular Roles -------------------->
 
<#assign isPortalUser = "false" />
<#assign roleList = theme_display.getUser().getRoles() />
 
<#foreach role in roleList >
<#assign roleUserRec = role.getName() />
    <#if roleUserRec == "Portal_User">
        <#assign isPortalUser = "true" />
        <#assign show_footer = "false" />
        <#break/>
    </#if>
</#foreach> 
 
<#------------------------------ User Organization Roles ----------------->
