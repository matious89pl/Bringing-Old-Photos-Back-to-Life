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

<#--  SITES ROLES  -->
<#assign userRoles = user.getRoles()/>
<#assign userSites = user.getSiteGroups() />
<#assign userGroups = groupService.getUserGroupRoles(userId, groupId) />
<#assign userOrganisations = user.getOrganizations()/>


<#-- FRIENDLY URLS -->
<#assign siteName = themeDisplay.getSiteGroupName() />
<#assign siteName = siteName?replace(" ", "-") />
<#assign siteName = siteName?lower_case />

<#-- GETTING DYMANIC URL PARAMETERS -->
<#assign groupId= themeDisplay.getLayout().getGroupId() /> 
<#assign instanceId = themeDisplay.getPortletDisplay().getInstanceId() />
<#assign plid = themeDisplay.getPlid() />

<#assign ddmStructureService = serviceLocator.findService("com.liferay.dynamic.data.mapping.service.DDMStructureLocalService") />
<#assign listddmStructure =  ddmStructureService.getDDMStructures(0, ddmStructureService.getDDMStructuresCount()) />

<#list listddmStructure as ddm>
    <#if ddm.getNameCurrentValue() == "VOTING" >
        <#assign structureKey = ddm.getStructureKey() />
    </#if>
</#list>
<#-- layout --> 
<#assign layoutService = serviceLocator.findService ("com.liferay.portal.kernel.service.LayoutLocalService") />

<#if entries?has_content>    
	<#list entries as curEntry>
	    <#assign renderer = curEntry.getAssetRenderer() />
	    <#assign className = renderer.getClassName() />
	</#list>	
    <#assign journalArticle = renderer.getArticle() />
    <#assign plid = themeDisplay.getPlid() />
</#if>

<#assign layout = layoutService.getLayout(themeDisplay.getPlid()).getUuid() />

<#assign isAdmin = 0 />
<#assign userRoles = user.getRoles()/>
<#list userRoles as rol>
    <#if rol.getName() == "Administrator" || rol.getName() ==  "Committee_Secretariat_Admi">
        <#assign isAdmin = 1 />
    </#if> 
</#list>

<div id="votingContent">    
        <#if isAdmin == 1>
            <div class="right">
            
                <#assign urlWebcontentPanel = "/group/"+siteName+"/~/control_panel/manage?p_p_id=com_liferay_journal_web_portlet_JournalPortlet&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&refererPlid="+ plid +"&_com_liferay_journal_web_portlet_JournalPortlet_mvcPath=%2Fedit_article.jsp&_com_liferay_journal_web_portlet_JournalPortlet_ddmStructureKey=" + structureKey +"&_com_liferay_journal_web_portlet_JournalPortlet_redirect=%2Fgroup%2F"+siteName+"%2Fvoting&_com_liferay_journal_web_portlet_JournalPortlet_referringPortletResource=com_liferay_asset_publisher_web_portlet_AssetPublisherPortlet_INSTANCE_"+ instanceId +"&_com_liferay_journal_web_portlet_JournalPortlet_assetTagNames=&_com_liferay_journal_web_portlet_JournalPortlet_portletResource=com_liferay_asset_publisher_web_portlet_AssetPublisherPortlet&_com_liferay_journal_web_portlet_JournalPortlet_groupId=" + groupId + "&_com_liferay_journal_web_portlet_JournalPortlet_layoutUuid=" + layout + "&p_p_auth=AfvqIv1V" />
                 <a id="addconsult" href="${urlWebcontentPanel}">Add Voting</a>
                <#--<#assign urlCustomForm = "/group/"+siteName+"/voting-form" />
                <a id="addconsult" href="${urlCustomForm}">Add Voting</a>-->
            </div>
        </#if>
<table id="votingTableList">
    <thead>
        <tr>
            <th class="left-align-text"></th>
            <th class="left-align-text">Vote Process</th>
            <th class="left-align-text">Closing Date</th>
            <th class="left-align-text">Vote Status</th>
           
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
        
                        <#assign urlTitle = journalArticle.getUrlTitle() />
        
                        <#assign document = saxReaderUtil.read(journalArticle.getContent())/>
        
                        <#assign assetURL = "/group/"+siteName+"/-/" + urlTitle />
                        
                        
                        <#assign titleVoting = document.valueOf("//dynamic-element[@name='TitleVoting']/dynamic-content/text()") />
                        
                        <#assign votingDate = document.valueOf("//dynamic-element[@name='DateVoting']/dynamic-content/text()") />
                         <#assign statusVoting = document.valueOf("//dynamic-element[@name='StatusVoting']/dynamic-content/text()") />
           
                        
                        <td class="left-align-text"></td>
                        <td class="left-align-text reference"> ${titleVoting} </td>
                            <#if votingDate?has_content>
                            
                            <td class="left-align-text"> ${votingDate}</td>
                            <#else>
                            <td class="left-align-text"> - </td>
                            </#if>
                        
                       
                            <#if statusVoting == "Completed">
                                    <td class="left-align-text votingStatusComplete"> <p> ${statusVoting} </p> </td>
                                <#elseif statusVoting == "In progress">
                                    <td class="left-align-text votingStatusInProgress"> <p> ${statusVoting} </p> </td>
                                <#else>
                                     <td class="left-align-text "> <p> - </p> </td>
                             </#if>
                       
        
                        <td class="left-align-text"></td>
                        <td class="alight-right"> <a  href= "${assetURL}"><img class="img" src="${chevromImg}"></a></td>
     
	</tr>
 </tbody>
    </#if>
  </#list>
</#if>

</table>
</div>
</div>


<style>
    #votingTableList, .tableList{
        border-style: none;
        border-collapse: collapse;
        width: 98%;
        margin-left: 1%;
    }

        tr {
            border-bottom: solid 3px #ececec;
        }

        th {
            color: #9b9b9b;
            font-size: 12px;
            font-weight: normal;
            height: 18px;
            letter-spacing: 0.25px;
            line-height: 18px;    
        }

        .reference {
            font-weight: 700;
        }
        .alight-right{
            float: right;
            
        }

        .votingStatus{
            letter-spacing: .25px;
        }

        .votingStatusComplete p{
            background: #b1c568;
            padding: 3px;
            border-radius: 2px;
            color: white;
            width: fit-content;
            margin-bottom: 0;
            font-size: 12px;
        }

        .votingStatusInProgress p{
            background: #f5b01e;
            padding: 3px;
            border-radius: 2px;
            color: white;
            width: fit-content;
            margin-bottom: 0;
            font-size: 12px;
        }

        p.img {
            background: rgba(0, 0, 0, 0.25);
            border-radius: 4px; 
            width: 30px;
            height: 30px;
            font-size: 20px;
            font-weight: 500;
            text-align: center;
        }
    

   
 .filterOpt{
        float: right; 
        font-size: 17px;
        width: 9%;
    }

    .filterOpt img{
        width: 20px;
    }

    .filterOpt a{
    color:black;
    }
    td {
        padding: 5px;
    }

    #myApplicationTitle{
        float: left;
        color: black;
        font-size: 32px;
        font-weight: 500;
        letter-spacing: 1.5px;
        line-height: 48px;
        font-family: "Roboto-medium" !important;
		width: 84%;
    }

   
      
    #votingContent{
        background-color: white;
        border-radius: 4px;
        box-shadow: 0 1px 4px 0 rgba(0, 0, 0, 0.3);
        margin-top: 1%;
        padding-top: 1%;
    }

    img.img {
        background: rgba(0,0,0,0.25);
        border-radius: 4px;
        padding: 10px;
    }

}

#tabsDetails{
	padding-bottom: 10px;
}

</style>