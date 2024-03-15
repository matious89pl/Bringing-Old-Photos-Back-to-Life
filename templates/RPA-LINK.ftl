<#assign rolService = utilLocator.findUtil("com.liferay.portal.kernel.service.RoleLocalService") />							
<#assign userId = themeDisplay.getUserId() />
<#assign userRoles = user.getRoles()/>

<#assign siteName = themeDisplay.getSiteGroupName() />
<#assign siteName = siteName?replace(" ", "-") />
<#assign siteName = siteName?lower_case />

<#assign showButton = 0 />

<#list userRoles as rol>							
    <#if rol.getName() == "RPA" || rol.getName() == "Administrator" >							
        <#assign showButton = 1 />							
    </#if>							
</#list>

<#if showButton == 1 >

<a id="sendLinkNotificationRPA" href="/group/${siteName}/rpa-notification">Send Notification</a>

</#if>

<style>

 #sendLinkNotificationRPA {
    background: #b1c568;
    padding: 11px;
    border-radius: 6px;
    color: white;
    width: fit-content;
    font-size: 12px;
    border: 0;
 
}

</style>