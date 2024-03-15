<#-- GETTING DYMANIC URL PARAMETERS -->
<#assign siteGroupId = themeDisplay.getSiteGroupId() />
<#assign layoutService = serviceLocator.findService ("com.liferay.portal.kernel.service.LayoutLocalService") />

<#assign ddmStructureService = serviceLocator.findService("com.liferay.dynamic.data.mapping.service.DDMStructureLocalService") />
<#assign listddmStructure =  ddmStructureService.getStructures(siteGroupId) />

<#assign instanceId = themeDisplay.getPortletDisplay().getInstanceId() />

<#assign siteName = themeDisplay.getSiteGroupName() />
<#assign siteName = siteName?replace(" ", "-") />
<#assign siteName = siteName?lower_case />

<#assign url = "/web/"+siteName+"/nominations-form" />

<#if entries?has_content>    
	<#list entries as curEntry>
	    <#assign renderer = curEntry.getAssetRenderer() />
	    <#assign className = renderer.getClassName() />
	</#list>	
    <#assign journalArticle = renderer.getArticle() />
    <#assign plid = themeDisplay.getPlid() />
</#if>

<#assign layout = layoutService.getLayout(themeDisplay.getPlid()).getUuid() />

<#list listddmStructure as ddm>
    <#if ddm.getNameCurrentValue() == "NOMINATIONS-STR" >
        <#assign structureKey = ddm.getStructureKey() />
        <#assign groupId =  ddm.getGroupId() />
    </#if>
</#list>

<#-- END -->


<!-- WIP Consultation -->
<#assign Imagerute = themeDisplay.getPathThemeImages()+"/forms/chevrom.svg" />
<#assign userGroupService = utilLocator.findUtil("com.liferay.portal.kernel.service.UserGroupRoleLocalService") />
<#assign userService = utilLocator.findUtil("com.liferay.portal.kernel.service.UserLocalService") />
<#assign rolService = utilLocator.findUtil("com.liferay.portal.kernel.service.RoleLocalService") />
<#assign userId = themeDisplay.getUserId() />
<#assign user = userService.getUser(userId) />
<#assign isAdmin = false />
<#assign userRoles = user.getRoles()/>
<#list userRoles as rol>
     <#if rol.getName() == "Administrator" || rol.getName() == "Committee_Secretariat_Admi" || rol.getName() == "Portal_Admin" >	
        <#assign isAdmin = true />
    </#if> 
</#list>

<#assign member = false />
<#assign users = userService.getGroupUserIds(siteGroupId)/>
<#list users as user>
    <#if user == userId >
        <#assign member = true />
    </#if>
</#list>
<#assign access = false />
<#assign orgRoleContractM = rolService.getRole(companyId, "REC Contract Managers") />
<#assign groupRoles = userGroupService.getUserGroupRoles(userId) />
<#if groupRoles?has_content>
    <#list groupRoles as rol>
    <#assign roleName = rol.getRole().getName() />
    
    <#if roleName == orgRoleContractM.getName()>
            <#assign access = true />
            <#break>
        </#if>
        
    </#list>
</#if>

<#if access || isAdmin>
<div id="nomination-register">
    <div id="header-consult">
        <div class="left">
            <h1 id="title-consult">Nominations & Elections</h1>
        </div>
        <#if isAdmin>
            <div class="right">
                
                <#assign urlWebcontentPanel = "/group/"+siteName+"/~/control_panel/manage?p_p_id=com_liferay_journal_web_portlet_JournalPortlet&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&refererPlid="+ plid +"&_com_liferay_journal_web_portlet_JournalPortlet_mvcPath=%2Fedit_article.jsp&_com_liferay_journal_web_portlet_JournalPortlet_ddmStructureKey="+ structureKey +"&_com_liferay_journal_web_portlet_JournalPortlet_redirect=%2Fgroup%2F"+siteName+"%2Fnomination-register&_com_liferay_journal_web_portlet_JournalPortlet_referringPortletResource=com_liferay_asset_publisher_web_portlet_AssetPublisherPortlet_INSTANCE_imviWFfpRmDo&_com_liferay_journal_web_portlet_JournalPortlet_assetTagNames=&_com_liferay_journal_web_portlet_JournalPortlet_portletResource=com_liferay_asset_publisher_web_portlet_AssetPublisherPortlet&_com_liferay_journal_web_portlet_JournalPortlet_groupId=" + siteGroupId + "&_com_liferay_journal_web_portlet_JournalPortlet_layoutUuid=" + layout + "&p_p_auth=s6b8pmQK" />

                <a id="addconsult" href="${urlWebcontentPanel}">Add</a>
            </div>
        </#if>
    </div>
   
    <div id="table-consult"> 
    <table id="instanceReportTable" class="margin-center">
    <thead>
        <tr>
            <th class="left-align-text">Name</th>
            <th class="left-align-text">Description</th>
            <th class="left-align-text">Nomination Close Date </th>
            <th class="left-align-text">Status</th>
            <th class="left-align-text"></th>
        </tr>
    </thead>

    <tbody>        
<#if entries?has_content>
    
	<#list entries as curEntry>
	    <#assign renderer = curEntry.getAssetRenderer() />
	    <#assign className = renderer.getClassName() />
	   
    <#if className == "com.liferay.journal.model.JournalArticle">
        <#assign journalArticle = renderer.getArticle() />
        <#assign urlTitle = journalArticle.getUrlTitle() />
        <#assign document = saxReaderUtil.read(journalArticle.getContent())/>
        <#assign assetURL = "/web/"+siteName+"/-/" + urlTitle />

        <#assign textTitleNomination = document.valueOf("//dynamic-element[@name='TitleNomination']/dynamic-content/text()") />
        <#assign status = document.valueOf("//dynamic-element[@name='StatusNomination']/dynamic-content/text()") />
	    <#assign nominationDate = document.valueOf("//dynamic-element[@name='ClosingDateNomination']/dynamic-content/text()") />
        <#assign description = document.valueOf("//dynamic-element[@name='DescriptionNomination']/dynamic-content/text()") />
        
        <tr>
            <td>
                <a href="${assetURL}"> ${textTitleNomination} </a>
            </td>
             <td>
                 ${description} 
            </td>
            <td>
               
                 <#assign closingDate = getterUtil.getString(nominationDate)>

                        <#if validator.isNotNull(closingDate)>
                        	<#assign closingDateobj = dateUtil.parseDate("yyyy-mm-dd", closingDate, locale)>
                        
                        	<p>${dateUtil.getDate(closingDateobj, "dd/mm/yyyy", locale)}</p>
                        </#if>
                     
            </td>
            <td>
                ${status?replace("-", " ")}
            </td>
            <td>
                 <a href="${assetURL}" class="open"><img src="${Imagerute}"/></a>
            </td>
        </tr>
		</#if>
	</#list>
	
</#if>
</tbody>

</table>
</div>
</div>
</#if>