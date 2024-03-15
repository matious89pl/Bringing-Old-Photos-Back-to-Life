<#assign groupService = utilLocator.findUtil("com.liferay.portal.kernel.service.GroupLocalService") />			

<#assign id = themeDisplay.getSiteGroupId() />
<#assign site = groupService.getGroup(id) />

<div class="titleCommite">
    <#if site.name?has_content && site.name != "">
    <div>
        <#--<h1>${TitleCommittee.getData()}</h1>-->
        <h1>${site.name}</h1>
        <ul>
            <li><img src="${themeDisplay.getPathThemeImages()}/forms/user.png"></li>
            <li><img src="${themeDisplay.getPathThemeImages()}/forms/user.png"></li>
            <li><img src="${themeDisplay.getPathThemeImages()}/forms/user.png"></li>
        </ul>
    
        <#--<h3>${TitleCommittee.DescriptionCommittee.getData()}</h3>-->
        <h3>${site.description}</h3>
    </div>
    </#if>
</div>