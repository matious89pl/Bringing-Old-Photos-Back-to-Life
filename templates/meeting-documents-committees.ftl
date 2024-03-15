<#assign userService = utilLocator.findUtil("com.liferay.portal.kernel.service.UserLocalService") />

<#assign userId = themeDisplay.getUserId() />
<#assign user = userService.getUser(userId) />
<#assign siteId =  themeDisplay.getLayout().getGroupId() />   

<#assign userSiteList = user.getGroups() />
<#assign isSiteMember = false />

<#if userSiteList?has_content>
    <#list userSiteList as userSite>
        <#if userSite.getGroupId() == siteId> 
            <#assign isSiteMember = true />
        </#if>
    </#list>
</#if>

<#if !isSiteMember>
    <style>
        button.btn.nav-btn.nav-btn-monospaced.btn-primary {
            display: none;
        }
    </style>
</#if>
     
<@liferay_portlet["runtime"]
   instanceId="documentsCommittee"
   portletName="com_liferay_document_library_web_portlet_DLPortlet"
/>    