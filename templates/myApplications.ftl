<#assign filterImg = themeDisplay.getPathThemeImages()+"/forms/filter.svg" />
<#assign chevromImg = themeDisplay.getPathThemeImages()+"/forms/chevrom.svg" />

<#--  ROLES  -->
<#assign userService = utilLocator.findUtil("com.liferay.portal.kernel.service.UserLocalService") />
<#assign rolService = utilLocator.findUtil("com.liferay.portal.kernel.service.RoleLocalService") />
<#assign organisationService = utilLocator.findUtil("com.liferay.portal.kernel.service.OrganizationLocalService") />
<#assign groupService = utilLocator.findUtil("com.liferay.portal.kernel.service.UserGroupRoleLocalService") />

<#assign userId = themeDisplay.getUserId() />
<#assign user = userService.getUser(userId) />
<#assign groupId= themeDisplay.getLayout().getGroupId() />
<#assign companyId= themeDisplay.getCompanyId() />


<#assign userRoles = user.getRoles()/>
<#assign userSites = user.getSiteGroups() />
<#assign userGroups = groupService.getUserGroupRoles(userId, groupId) />
<#assign userOrganisations = user.getOrganizations()/>

<#--  SITES ROLES  -->
<#attempt>
    <#assign helpdeskManager = rolService.getRole(companyId, "Helpdesk_Manager").getRoleId() />
    <#assign helpdeskOperative = rolService.getRole(companyId, "Helpdesk_Operative").getRoleId() />
    <#assign committeeMember = rolService.getRole(companyId, "Committee_Member").getRoleId() />
    <#assign committeeSecretariatChair = rolService.getRole(companyId, "Committee_Secretariat-Chair").getRoleId() />
    <#assign crossCodeSteeringGroup = rolService.getRole(companyId, "Cross-Code_Steering_Group").getRoleId() />
<#recover>
    <#assign helpdeskManager = 0 />
    <#assign helpdeskOperative = 0 />
    <#assign committeeMember = 0 />
    <#assign committeeSecretariatChair = 0 />
    <#assign crossCodeSteeringGroup = 0 />
</#attempt>

<#assign showAddButton = 1 />
<#assign filteraApplicationAdministrator = 0 />

<#--  NO ACCESS MY APPLICATION ACTIVITIES DETAILS  -->
<#list userRoles as rol>
    <#if rol.getName() == "Change_Management_Team" || rol.getName() == "Code_Manage_Master_Admin_User" || rol.getName() == "REC_Stakeholder" || rol.getName() == "RECCo" || rol.getName() == "Change_Analyst" || rol.getName() == "Change_Proposer" || rol.getName() == "Committee_Secretariat_Admi" || rol.getName() == "Contributor" || rol.getName() == "Editor" || rol.getName() == "Knowledge_Content_Administrator" || rol.getName() == "Portal_Content_Manager" || rol.getName() == "RPS_Master_Admin_User" || rol.getName() == "Authority" || rol.getName() == "Authority_Ofgem" >
        <#assign showAddButton = 0 />
    </#if>
</#list>

<#--  NO ACCESS MY APPLICATION ACTIVITIES DETAILS ORGANIZATIONS  -->       
<#list userOrganisations as organisations>
     <#if organisations.name == "Responsible Service Provider"  >
        <#assign showAddButton = 0 />
     </#if>
</#list>

<#--  NO ACCESS MY APPLICATION ACTIVITIES DETAILS SITE ROLES  -->   
<#list userGroups as rol>
     <#if helpdeskManager == rol.roleId || helpdeskOperative == rol.roleId || committeeMember == rol.roleId || committeeSecretariatChair == rol.roleId || crossCodeSteeringGroup == rol.roleId >
        <#assign showAddButton = 0 />
     </#if> 
</#list>

<#-- GETTING DYMANIC URL PARAMETERS -->
<#assign groupId= themeDisplay.getLayout().getGroupId() /> 
<#assign instanceId = themeDisplay.getPortletDisplay().getInstanceId() />
<#assign plid = themeDisplay.getPlid() />

<#assign ddmStructureService = serviceLocator.findService("com.liferay.dynamic.data.mapping.service.DDMStructureLocalService") />
<#assign listddmStructure =  ddmStructureService.getDDMStructures(0, ddmStructureService.getDDMStructuresCount()) />

<#list listddmStructure as ddm>
    <#if ddm.getNameCurrentValue() == "PARTY-MANAGEMENT" >
        <#assign structureKey = ddm.getStructureKey() />
    </#if>
</#list>

<#-- END -->

<div id="myApplication">
    <h1 id="myApplicationTitle" class="col-md-9">Applications</h1>
    <div id="myApplicationButtons"> 
        <button id="newApplication" class="hide" > Make an application </button>
        <!-- <a id="newApplication" href="/group/guest/~/control_panel/manage?p_p_id=com_liferay_journal_web_portlet_JournalPortlet&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&refererPlid=361&_com_liferay_journal_web_portlet_JournalPortlet_mvcPath=%2Fedit_article.jsp&_com_liferay_journal_web_portlet_JournalPortlet_ddmStructureKey=70485&_com_liferay_journal_web_portlet_JournalPortlet_redirect=%2Fgroup%2Fguest%2Fmy-applications&_com_liferay_journal_web_portlet_JournalPortlet_referringPortletResource=com_liferay_asset_publisher_web_portlet_AssetPublisherPortlet_INSTANCE_dzqolguSuMfz&_com_liferay_journal_web_portlet_JournalPortlet_assetTagNames=&_com_liferay_journal_web_portlet_JournalPortlet_portletResource=com_liferay_asset_publisher_web_portlet_AssetPublisherPortlet&_com_liferay_journal_web_portlet_JournalPortlet_groupId=37244&p_p_auth=swoOCyP2"">Make an application</a> -->
        <a id="newApplication" href="/group/guest/make-an-application">Make an application</a>
        <!-- <button id="askHelp"> Ask for Help </button> -->
    </div>
  
    <div id="myApplicationContent">    
        
        <table id="myApplicationTableList">
            <thead>
                <tr>
                    <th class="left-align-text"></th>
                    <th class="left-align-text">Reference</th>
                    <th class="left-align-text">Application Type</th>
                    <th class="left-align-text">Application Status</th>
                    <th class="left-align-text">Date Created</th>
                    <th class="left-align-text">Asignee</th>
                    <th class="left-align-text"></th>
                </tr>
            </thead>
   
            <#if entries?has_content>
                <tbody>
                    <#list entries as curEntry>
                        <tr>
                            <#assign renderer = curEntry.getAssetRenderer() />
                            <#assign className = renderer.getClassName() />
                            <#if className == "com.liferay.journal.model.JournalArticle">
                                <#assign journalArticle = renderer.getArticle() />
                                <#assign document = saxReaderUtil.read(journalArticle.getContent())/>
                                <#assign pmReference = "APP" + journalArticle.getResourcePrimKey() />
                                <#assign applicationType = document.valueOf("//dynamic-element[@name='PM_ProvideDetailsOrganisation']/dynamic-content/text()") />
                                <#assign pmStatus = document.valueOf("//dynamic-element[@name='PM_Status']/dynamic-content/text()") />
                                <#assign creteDate = journalArticle.getCreateDate()?date />
                                <#assign pmURL = "/group/guest/-/" + journalArticle.getUrlTitle() />                        
                            </#if>

                            <#if filteraApplicationAdministrator == 0>
                                <#list userRoles as rol>
                                    <#if rol.getName() == "Application_Administrator" || rol.getName() == "Administrator" >
                                        <#assign filteraApplicationAdministrator = 1 />
                                    </#if>
                                </#list>
                            </#if>

                            <#if filteraApplicationAdministrator == 1>
                                <td class="left-align-text"></td>
                                <td class="left-align-text reference">${pmReference}</td>
                                <td class="left-align-text">${applicationType}</td>
                            
                                <#if pmStatus?has_content >
                                    <#if pmStatus == "Complete" >
                                        <td class="left-align-text applicationStatusComplete"> <p> ${pmStatus} </p> </td>
                                    <#elseif pmStatus == "In Progress">
                                        <td class="left-align-text applicationStatusInprogress"> <p> ${pmStatus} </p> </td>
                                    </#if>
                                <#else>
                                    <td>-</td>
                                </#if>
                    
                                <td class="left-align-text">${creteDate}</td>
                                <td class="left-align-text">Anton Moden</td>
                                <td> <a  href='${pmURL}'><img class="img" src="${chevromImg}"></a></td>

                            <#else>     

                                <#if journalArticle.userId == userId>
                                    <td class="left-align-text"></td>
                                    <td class="left-align-text reference">${pmReference}</td>
                                    <td class="left-align-text">${applicationType}</td>
                                
                                    <#if pmStatus?has_content >
                                        <#if pmStatus == "Complete" >
                                            <td class="left-align-text applicationStatusComplete"> <p> ${pmStatus} </p> </td>
                                        <#elseif pmStatus == "In Progress">
                                            <td class="left-align-text applicationStatusInprogress"> <p> ${pmStatus} </p> </td>
                                        </#if>
                                    <#else>
                                        <td>-</td>
                                    </#if>
                                
                                    <td class="left-align-text">${creteDate}</td>
                                    <td class="left-align-text">Anton Moden</td>
                                    <td> <a  href='${pmURL}'><img class="img" src="${chevromImg}"></a></td>
                                </#if>
                            </#if>        
                        </tr>
                    </#list>
                </tbody>            
            </#if> 
        </table>
    </div>
</div>

<style>

    #newApplication:hover {
        text-decoration: none;
    }

</style>
