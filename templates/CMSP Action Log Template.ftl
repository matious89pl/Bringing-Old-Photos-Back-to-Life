<#-- CMSP ACTION LOG TEMPLATE -->
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
    <#if item.getName("en_GB") == "CMSP Action Log">
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

<#if showButton == 1>
    <input class="hide" id="addCMSPActionLog" type="button" value="Add CMSP Action Log" onclick="addCMSPActionLog()" data="${recordId}" name="addCMSPActionLog"/>

    <#assign url = "/group/guest/~/control_panel/manage?p_p_id=com_liferay_dynamic_data_lists_web_portlet_DDLPortlet&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_mvcPath=%2Fedit_record.jsp&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_redirect=%2Fgroup%2Fguest%2Fcmsp-action-log&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_recordSetId="+ recordId +"&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_formDDMTemplateId=0&p_p_auth=IRwVoIQR" />

    <a id="addCMSPActionLog" href="${url}">Add CMSP Action Log</a>
</#if>

<#assign CMSPAction = "recordSetId=" + recordId />
<@liferay_portlet["runtime"]
    instanceId="CMSPAction"
    queryString=CMSPAction
    portletName="com_liferay_dynamic_data_lists_web_portlet_DDLDisplayPortlet"
/>

<style>
    #addCMSPActionLog {
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
