<#-- Party Management -->
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

<#assign orgRoleContractM = rolService.getRole(companyId, "REC Contract Managers") />
<#assign orgRolePerformanceA = rolService.getRole(companyId, "REC_Performance_Assurance") />
<#assign orgRoleMAU= rolService.getRole(companyId, "Master Administrative User") />

<#assign groupRoles = userGroupService.getUserGroupRoles(userId) />

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

<#assign operationalContactVisible = 0>
<#assign orgList = user.getOrganizations() />
<#list orgList as organization>
     <#assign expando = organization.getExpandoBridge() />
     <#assign expandoList = expando.getAttributeNames() />
     <#assign name = "Rec User Type" />
     <#if expando.getAttribute(name)?? >
        <#assign userTypes = expando.getAttribute(name) />
        <#list userTypes as typeString>
            <#if typeString == "REC Party" || typeString == "RECCo" || typeString == "REC Code Manager">
                <#assign operationalContactVisible = 1>
            </#if>
        </#list>
    </#if>
</#list>
            
<div id="party_management">

<h1 id="title-party-management">Participant Management</h1>

<div id="boxes-cm" class="container-fluid">
    <div class="row">
        <div class="col-md-12">
        
            <div class="row" id="row-ups">
            
                <div class="col-md-3">
                 <a data-senna-off="true" href="/group/guest/my-applications">
                    <div id="my-appliction" class="party-view-golden">
                    	<div class="row">
                    	    <div class="col-md-3 image-left">
                        	    <img src="${userBookImg}"/>
                            </div>
                            <div class="col-md-9 code-padd">
                               Applications
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
					<#assign userOrgCont = 0>
                        <#assign userOrganisationsList = user.getOrganizations() />
                        <#if userOrganisationsList?has_content>
                            
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
                                <a data-senna-off="true" href="/group${orgFriendlyURL}/your-files">
                            </#if> 
                        </#if>
                                     
                        <div id="performance-assurance" class="party-view-golden">
                                                <div class="row">
                                                    <div class="col-md-3 image-left">
                                                        <img src="${userBookImg}"/>
                                                    </div>
                                                    <div class="col-md-9 code-padd">
                                                    Performance Assurance	
                                                    </div>
                                                </div>
                                            </div>
                                            </a>
                                    </div>
                </#if>

                <div class="col-md-3">
                    <a data-senna-off="true" href="/group/guest/withdrawing-rec">
                        <div id="withdrawing-rec" class="party-view-golden">
                    	    <div class="row">
                                <div class="col-md-3 image-left">
                                    <img src="${userBookImg}"/>
                                </div>
                                <div class="col-md-9 code-padd">
                                    Withdrawing from the REC
                                </div>
                    	    </div>
                        </div>
                    </a>
                </div>
            </div>

            <div class="row" id="row-down">
                <div class="col-md-3">
                <a href="/group/guest/qualified-parties-register">
                    <div id="qualified-parties" class="party-view">
                        <div class="row">
                            <div class="col-md-3 image-left">
                                <img src="${userFriendImg}"/>
                            </div>
                            <div class="col-md-9 code-padd">
                                Qualified Parties Register
                            </div>
                        </div>
                    </div>
                    </a>
                </div>
                
                <div class="col-md-3">
                <a href="/group/guest/party-and-mem-register" >
                    <div id="party-register" class="party-view">
                        <div class="row">
                            <div class="col-md-3 image-left">
                                <img src="${userPartyImg}"/>
                            </div>
                            <div class="col-md-9 code-padd">
                                REC Party Register
                            </div>
                        </div>
                    </div>
                    </a>
                </div>
                
                <#if operationalContactVisible == 1>
                    <div class="col-md-3">
                        <a href="/group/guest/operational-contacts-register">
                            <div id="operational-contacs" class="party-view">
                                <div class="row">
                                    <div class="col-md-3 image-left">
                                        <img src="${userCogImg}"/>
                                    </div>
                                    <div class="col-md-9 code-padd">
                                    Operational Contacts
                                    </div>
                                </div>
                            </div>
                        </a>
                    </div>
                </#if>
            </div>
                   
        </div>
    </div>
</div>

</div>

<div id="popupOrganisation" class="popupOrganisation1">
    <div class="popupp">
        <div class="popUpContentTitle"> 
            <h3> My Organsations </h3>
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
                            <li> <a href="/group${orgFriendlyURL}/your-files" >${orgName}</a> </li>
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
            <h3> No Organsations found </h3>
            <a class="close" onclick=closePopUpOrganisation()>&times;</a>	
        </div>
    </div>
</div>