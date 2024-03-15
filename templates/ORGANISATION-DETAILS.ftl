<#assign groupService = utilLocator.findUtil("com.liferay.portal.kernel.service.GroupLocalService") />

<#assign id = themeDisplay.getSiteGroupId() />
<#assign org = groupService.getGroup(id) />

<div class="titleCommite">
    <h1>${org.getName()}</h1>
    <h3>${org.getDescription()}</h3>
</div>