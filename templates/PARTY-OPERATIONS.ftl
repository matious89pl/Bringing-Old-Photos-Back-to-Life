<#-- Party Management-->
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
<#assign userGroups = userGroupService.getUserGroupRoles(userId, groupId) />
<#--  <#assign userOrgRoles = user.getOrganizations.ROLES/>  -->

<#attempt>
    <#assign orgRoleContractM = rolService.getRole(companyId, "REC Contract Managers") />
    <#assign orgRolePerformanceA = rolService.getRole(companyId, "REC_Performance_Assurance") />
    <#assign orgRoleMAU= rolService.getRole(companyId, "Master Administrative User") />
    <#assign orgRoleDirector= rolService.getRole(companyId, "Director") />
<#recover>
    <#assign orgRoleContractM = "" />
    <#assign orgRolePerformanceA = "" />
    <#assign orgRoleMAU = "" />
    <#assign orgRoleDirector = "" />
</#attempt>

<#--  SITES ROLES  -->
<#attempt>
    <#assign orgRoleDirectorId = rolService.getRole(companyId, "Director").getRoleId() />
<#recover>
    <#assign orgRoleDirectorId = 0 />
</#attempt>

<#assign accessRPA = false>
<#assign accessDirector = false>


<#assign groupRoles = userGroupService.getUserGroupRoles(userId) />
<#assign groupSiteRoles = userGroupService.getUserGroupRoles(userId,siteGroupId) />

<#assign isCorrectOrganisationRole = false />
<#assign isCorrectRegularRole = false />

<#assign isAdmin = false />
<#assign accessOC = false/>

<#if groupRoles?has_content>
    <#list groupRoles as rol>
    <#assign roleName = rol.getRole().getName() />
        <#if orgRoleContractM != "" || orgRolePerformanceA != "" || orgRoleMAU != "" >
            <#if roleName == orgRoleContractM.getName() || roleName == orgRolePerformanceA.getName() || roleName == orgRoleMAU.getName()>
            <#assign isCorrectOrganisationRole = true />
                <#break>
            <#elseif roleName == orgRoleContractM.getName()>
            </#if>
        </#if>
    </#list>
</#if>

<#list userGroups as rol>
     <#if orgRoleDirectorId == rol.roleId >
        <#assign accessDirector = true />
     </#if>
</#list>


 <#--  <#if expando.getAttribute(name)?? >
        <#assign userTypes = expando.getAttribute(name) />
        <#list userTypes as typeString>
            <#if typeString == "REC Party" || typeString == "RECCo" || typeString == "REC Code Manager">
                <#assign accessOC = true>
            </#if>
        </#list>
    </#if>
</#list>  -->

<#--  <#list userRoles as role>
    <#if role.name == "REC Party" || role.name == "RECCo" || role.name == "REC Code Manager" >
        <#assign accessOC = true>
    </#if>
</#list>  -->

<#assign userOrganisationsList = user.getOrganizations() />
    <#if userOrganisationsList?has_content>
     <#list userOrganisationsList as userOrg>
     <#if userOrg.getName() != "All Portal Users" >
    <#assign accessOC = true>
</#if>
</#list>
</#if>


<#--  <#if groupRoles?has_content>
    <#list groupRoles as rol>
    <#assign roleName = rol.getRole().getName() />
        <#if orgRoleContractM != "" >
            <#if roleName == orgRoleContractM.getName() >
                    <#assign accessOC = true>
                <#break>
            </#if>
        </#if>
    </#list>
</#if>  -->

<#list userRoles as rol>
    <#if rol.getName()=="RPA">
        <#assign accessRPA = true />
    </#if>
</#list>

<style>
    #party_management #boxes-cm .row .col-md-12 .row .col-md-3 {
        margin-bottom: 15px !important;
    }
</style>

<div id="party_management">

    <#if MainTitle1.getData()?has_content>
        <h1 id="title-party-management">${MainTitle1.getData()}</h1>
    <#else>
        <h1 id="title-party-management">Participant Management</h1>
    </#if>

    <div id="boxes-cm" class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <#if NameUp.getSiblings()?has_content>
                    <div class="row" id="row-ups">
                        <#assign cont = 0 />
                        <#list NameUp.getSiblings() as cur_NameUp>
                            <#if cur_NameUp.getData() == "Applications">
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
                                                <div class="col-md-9">
                                                    ${cur_NameUp.getData()}
                                                </div>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                                <#list userRoles as rol>
                                    <#if rol.getName()=="Administrator" || rol.getName()=="Compliance_Analyst" || rol.getName()=="Compliance_Manager" || rol.getName()=="RPA">
                                        <#assign isCorrectRegularRole = true />
                                    </#if>
                                </#list>

                                <#if isCorrectOrganisationRole || isCorrectRegularRole>
                                    <div class="col-md-3">
                                        <#assign userOrganisationsList = user.getOrganizations() />
                                        <#if userOrganisationsList?has_content>
                                            <#assign userOrgCont = 0>
                                            <#list userOrganisationsList as userOrg>
                                                <#assign orgName = userOrg.getName() />
                                                <#assign orgGroupId = userOrg.getGroupId() />
                                                <#assign group = groupService.getGroup(orgGroupId)/>
                                                <#assign groupName = group.getName() />
                                                <#assign hasSite = group.getSite() />
                                                <#if hasSite == true>
                                                    <#assign orgFriendlyURL = group.getFriendlyURL() />
                                                    <#assign userOrgCont = userOrgCont + 1 />
                                                </#if>
                                            </#list>
                                        </#if>

                                        <#if userOrgCont == 0>
                                            <a data-senna-off="true" onclick="noOrganisations()">
                                        </#if>

                                        <#if userOrgCont gt 0>
                                            <#if userOrgCont gt 1>
                                                <a data-senna-off="true" onclick="openPopUpOrganisation()">
                                            <#else>
                                                <a data-senna-off="true" href="/group${orgFriendlyURL}/home">
                                            </#if>
                                        </#if>

                                        <div id="performance-assurance" class="party-view-golden">
                                            <div class="row">
                                                <div class="col-md-3 image-left">
                                                    <img src="${userBookImg}"/>
                                                </div>
                                                <div class="col-md-9">
                                                    Performance Assurance
                                                </div>
                                            </div>
                                        </div>
                                    </a>
                                    </div>
                                </#if>
                            <#else>
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
                                                <div class="col-md-9">
                                                    ${cur_NameUp.getData()}
                                                </div>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                            </#if>
                            <#assign cont = cont + 1 />
                        </#list>
                        <#if accessRPA || isAdmin>
                            <div class="col-md-3">
                                <a data-senna-off="true" href="/group/guest/home">
                                    <div id="rpa-dashboard" class="party-view-golden">
                                        <div class="row">
                                            <div class="col-md-3 image-left" ><img src="${image}"/>
                                            </div>
                                            RPA Dashboard
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </#if>
                    </div>
                </#if>

                <#if NameDown.getSiblings()?has_content>
                    <div class="row" id="row-down">
                        <#assign cont = 0 />
                        <#list NameDown.getSiblings() as cur_NameDown>
                            <#if cur_NameDown.getData() == "Operational Contacts">
                                <#list userRoles as rol>
                                    <#if rol.getName()=="Administrator">
                                        <#assign isAdmin = true />
                                    </#if>
                                </#list>
                                <#if accessOC || isAdmin>
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
                            <#else>
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
                            <#assign cont = cont + 1 />
                        </#list>

                        <#if accessRPA || isAdmin>
                            <div class="col-md-3">
                                <a data-senna-off="true" href="/group/guest/rpa-calendar-management">
                                    <div class="party-view">
                                        <div class="row">
                                            <div class="col-md-3 image-left" ><img src="${userCogImg}"/>
                                            </div>
                                            RPA Calendar
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </#if>

                    </div>
                </#if>
            </div>
        </div>
    </div>
</div>

<div id="popupOrganisation" class="popupOrganisation1">
    <div class="popupp">
        <div class="popUpContentTitle">
            <h3> My Organisations </h3>
            <a class="close" onclick=closePopUpOrganisation()>&times;</a>
        </div>

        <div class="popupContent">
            <#if userOrganisationsList?has_content>
                <#list userOrganisationsList as userOrg>
                    <#assign orgName = userOrg.getName() />
                    <#assign orgGroupId = userOrg.getGroupId() />
                    <#assign group = groupService.getGroup(orgGroupId)/>
                    <#assign groupName = group.getName() />
                    <#assign hasSite = group.getSite() />
                    <#if hasSite == true>
                        <#assign orgFriendlyURL = group.getFriendlyURL() />
                        <ul>
                            <li> <a href="/group${orgFriendlyURL}/home" >${orgName}</a> </li>
                        </ul>
                    </#if>
                </#list>
            </#if>
        </div>
    </div>
</div>

<div id="popupNoOrganisation" class="popupOrganisation1">
    <div class="popupp">
        <div class="popUpContentTitle row">
            <h3> No Organisations found </h3>
            <a class="close" onclick=closePopUpOrganisation()>&times;</a>
        </div>
    </div>

</div>