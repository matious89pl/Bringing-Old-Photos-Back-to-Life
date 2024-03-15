<#-- ISSUES LOG CHANGE MANAGEMENT TEMPLATE -->
<script>
    $( window ).on( "load", function() {
        $(".table-cell-content a").each(function(){
         $(this).removeAttr("href")
        })   
    });
</script>
<#assign groupId= themeDisplay.getLayout().getGroupId() /> 
<#assign recordSetservice = serviceLocator.findService("com.liferay.dynamic.data.lists.service.DDLRecordSetLocalService") />
<#assign lista = recordSetservice.getRecordSets(groupId) />

<#list lista as item>
    <#if item.getName("en_GB") == "Issues Log">
        <#assign recordId = item.getRecordSetId() />
    </#if>
</#list>

<#assign userService = utilLocator.findUtil("com.liferay.portal.kernel.service.UserLocalService") />
<#assign rolService = utilLocator.findUtil("com.liferay.portal.kernel.service.RoleLocalService") />

<#-- GETTING USER -->
<#assign userId = themeDisplay.getUserId() />
<#assign user = userService.getUser(userId) />

<#-- GETTING USER ROLES TO SHOW BUTTON -->
<#assign showButton = 0 />
<#assign userRoles = user.getRoles()/>
<#list userRoles as rol>
    <#if rol.getName() == "Change_Management_Team" || rol.getName() == "Administrator" || rol.getName() == "Committee_Secretariat_Admi" >
        <#assign showButton = 1 />
    </#if>
</#list>
<#assign userOrg = user.getOrganizations()/>
<#list userOrg as org>
    <#if org.getName() == "Responsible Service Provider" || org.getName() == "REC Technical Services" || org.getName() == "REC Performance Assurance" >
        <#assign showButton = 1 />
        <#break>
    </#if> 
</#list>

<#if showButton == 1>
<input class="hide" id="addIssueLog" type="button" value="Add Issue Log" onclick="addIssueLog()" data="${recordId}" name="addIssueLog"/>

    <#assign url = "/group/guest/~/control_panel/manage?p_p_id=com_liferay_dynamic_data_lists_web_portlet_DDLPortlet&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_mvcPath=%2Fedit_record.jsp&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_redirect=%2Fgroup%2Fguest%2Fissues-log&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_recordSetId="+ recordId +"&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_formDDMTemplateId=0&p_p_auth=tYkZwwfY" />

    <a id="addIssueLog" href="${url}">Add Issue Log</a>
</#if>
<#assign ISSUESLog = "recordSetId=" + recordId />
<@liferay_portlet["runtime"]
    instanceId="ISSUESLog"
    queryString=ISSUESLog
    portletName="com_liferay_dynamic_data_lists_web_portlet_DDLDisplayPortlet"
/>

<style>
    #addIssueLog {
        background: #b1c568;
        border-radius: 5px;
        color: white;
        font-size: 12px;
        font-weight: normal;
        letter-spacing: 0.25px;
        line-height: 18px;
        height: 36px;
        border: none;
        padding: 9px 16px 9px 17px;
    }

    .table-cell-content a:hover {
	    text-decoration:none;
	    cursor: default;
	}
</style>
