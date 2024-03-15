<#-- Annual Maintenance tile UAT -->
<#assign userBookImg = themeDisplay.getPathThemeImages()+"/forms/book_open.svg" />
<#assign userFriendImg = themeDisplay.getPathThemeImages()+"/forms/user_friend_party.svg" />
<#assign userPartyImg = themeDisplay.getPathThemeImages()+"/forms/user-white.svg" />
<#assign userCogImg = themeDisplay.getPathThemeImages()+"/forms/user-cog-white.svg" />
<#assign rolService = utilLocator.findUtil("com.liferay.portal.kernel.service.RoleLocalService") />
<#assign userService = utilLocator.findUtil("com.liferay.portal.kernel.service.UserLocalService") />
<#assign userGroupService = utilLocator.findUtil("com.liferay.portal.kernel.service.UserGroupRoleLocalService") />
<#assign groupService = utilLocator.findUtil("com.liferay.portal.kernel.service.GroupLocalService") />

<#assign userId = themeDisplay.getUserId() />
<#assign user = userService.getUser(userId) />
<#assign userRoles = user.getRoles()/>
<#assign companyId = user.getCompanyId() />
<#assign siteGroupId = themeDisplay.getScopeGroupId() />

<#assign orgRoleContractM = rolService.getRole(companyId, "REC Contract Managers") />
<#assign orgRolePerformanceA = rolService.getRole(companyId, "REC_Performance_Assurance") />
<#assign orgRoleMAU= rolService.getRole(companyId, "Master Administrative User") />
<#assign orgRoleDirectorId = rolService.getRole(companyId, "Director").getRoleId() />
<#assign userGroups = userGroupService.getUserGroupRoles(userId, groupId) />

<#assign groupRoles = userGroupService.getUserGroupRoles(userId) />
<#assign groupSiteRoles = userGroupService.getUserGroupRoles(userId,siteGroupId) />

<#assign isCorrectOrganisationRole = false />
<#assign isCorrectRegularRole = false />

<#if groupRoles?has_content>
    <#list groupRoles as rol>
        <#assign roleName = rol.getRole().getName() />
        <#if roleName == orgRoleContractM.getName() || roleName == orgRolePerformanceA.getName() || roleName == orgRoleMAU.getName()>
            <#assign isCorrectOrganisationRole = true />
            <#break>
        </#if>
    </#list>
</#if>

<#assign accessDirector = false />
<#list userGroups as rol>
     <#if orgRoleDirectorId == rol.roleId >
        <#assign accessDirector = true />
     </#if> 
</#list>

<#assign isAdmin = false />
<#assign accessOC = false>
<#assign accessRPA = false>

<#assign orgList = user.getOrganizations() />
<#list orgList as organization>
    <#assign expando = organization.getExpandoBridge() />
    <#assign expandoList = expando.getAttributeNames() />
    <#assign name = "" />
    <#list expandoList as e>
        <#assign name = e />
    </#list>
    <#if expando.getAttribute(name)?? >
        <#assign userTypes = expando.getAttribute(name) />
        <#list userTypes as typeString>
            <#if typeString == "REC Party" || typeString == "RECCo" || typeString == "REC Code Manager">
                <#assign accessOC = true>
            </#if>
        </#list>
    </#if>
</#list>

<#list userRoles as rol>
    <#if rol.getName()=="RPA"> 
        <#assign accessRPA = true />
    </#if>      
</#list> 

<div id="party_management">
    <#if MainTitle1.getData()?has_content>
        <h1 id="title-party-management">${MainTitle1.getData()}</h1>
    <#else>
        <h1 id="title-party-management">Annual Maintenance Forms</h1>
    </#if>

    <div id="boxes-cm" class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <#if accessRPA || accessDirector>
                    <#if NameUp.getSiblings()?has_content>
                        <div class="row" id="row-ups">
                            <#assign cont = 0 />
                            <#list NameUp.getSiblings() as cur_NameUp>                         
                                <div class="col-md-3">
                                    <a data-senna-off="true" href="${cur_NameUp.LinkUp.getData()}">
                                        <div class="party-view-golden">
                                            <div class="row">
                                                <div class="col-md-3 image-left">
                                                <#if cur_NameUp.LogoUp.getData()?? && cur_NameUp.LogoUp.getData() != "None" >
                                                    <#assign image = "${themeDisplay.getPathThemeImages()}/forms/${cur_NameUp.LogoUp.getData()}.svg" />
                                                <img src="${image}" />
                                                </#if>
                                                </div>
                                                <div class="col-md-9 code-padd">
                                                ${cur_NameUp.getData()}
                                                </div>
                                            </div>
                                        </div>
                                    </a>
                                </div>                    
                                <#assign cont = cont + 1 />
                            </#list>                
                        </div> 
                    </#if>
                </#if>       

                <#if NameDown.getSiblings()?has_content>
                    <div class="row" id="row-down">
                        <#assign cont = 0 />
                        <#list NameDown.getSiblings() as cur_NameDown>
                            <#if cont < 4 >                                                   
                                <#if accessRPA || accessDirector>
                                    <div class="col-md-3">
                                        <a href="${cur_NameDown.LinkDown.getData()}">
                                            <div class="party-view">
                                                <div class="row">
                                                    <div class="col-md-3 image-left">
                                                        <#if cur_NameDown.LogoDown.getData()?? && cur_NameDown.LogoDown.getData() != "None" >
                                                            <#assign image = "${themeDisplay.getPathThemeImages()}/forms/${cur_NameDown.LogoDown.getData()}.svg" />
                                                            <img src="${image}" />
                                                        </#if>
                                                    </div>
                                                    <div class="col-md-9 code-padd">
                                                        ${cur_NameDown.getData()}                   
                                                    </div>
                                                </div>
                                            </div>
                                        </a>
                                    </div>                                            
                                </#if>
                            </#if>                    
                            <#assign cont = cont + 1 />
                        </#list>
                    </div>
                </#if>          
            </div>
        </div>
    </div>
</div>