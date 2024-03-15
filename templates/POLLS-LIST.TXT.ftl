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

<#-- GETTING DYMANIC URL PARAMETERS -->
<#assign groupId= themeDisplay.getLayout().getGroupId() /> 
<#assign instanceId = themeDisplay.getPortletDisplay().getInstanceId() />
<#assign plid = themeDisplay.getPlid() />


<div id="pollsVoteContent">    
        
<table id="pollsVoteTableList">
    <thead>
        <tr>
            
            <th class="left-align-text">Vote Process</th>
           
            <th class="left-align-text">Vote Close Date</th>
            
            <th class="left-align-text voteStatus">Vote Status</th>
            <th class="left-align-text"></th>
            
        </tr>
    </thead>
   
<#if entries?has_content>
<tbody>
	<#list entries as curEntry>
	 <tr>
	    <td class="left-align-text">Vote Process</td>
        <td class="left-align-text reference">Vote Close Date</td>
        <td class="left-align-text voteStatusComplete"><p>Vote Status</p></td>
        <td class="left-align-text"><a  href='/group/guest/new-cp-form'><img class="img" src="${chevromImg}"></a></td>

	</tr>
 </tbody>
  </#list>
</#if>

</table>
</div>
</div>


<style>

    #pollsVoteTableList, .tableList{
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
        

        .voteStatus{
            letter-spacing: .25px;
        }

        .voteStatusComplete p{
            background: #b1c568;
            padding: 3px;
            border-radius: 2px;
            color: white;
            width: fit-content;
            margin-bottom: 0;
            font-size: 12px;
        }

        .voteStatusInprogress p{
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

   

    #pollsVoteContent{
        background-color: white;
        border-radius: 4px;
        box-shadow: 0 1px 4px 0 rgba(0, 0, 0, 0.3);
        margin-top: 1%;
        padding-top: 1%;
        padding-bottom: 20px;
    }

    img.img {
        background: rgba(0,0,0,0.25);
        border-radius: 4px;
        padding: 10px;
    }

}