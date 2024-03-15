<#assign userService = utilLocator.findUtil("com.liferay.portal.kernel.service.UserLocalService") />
<#assign rolService = utilLocator.findUtil("com.liferay.portal.kernel.service.RoleLocalService") />

<#assign userId = themeDisplay.getUserId() />
<#assign user = userService.getUser(userId) />
<#assign userRoles = user.getRoles()/>
<div id="home-header">
<#if HeaderLandingPage?has_content && HeaderLandingPage.getData() != "">
    ${HeaderLandingPage.getData()}
</#if>


<#if user.firstName?has_content && user.firstName != "">
    <h1 class="title">Welcome ${user.firstName}</h1>
    <#else>
     <h1 class="title">Welcome</h1>
</#if>
</div>