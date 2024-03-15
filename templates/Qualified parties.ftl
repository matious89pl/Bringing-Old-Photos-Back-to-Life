<#assign Imagefilter = themeDisplay.getPathThemeImages()+"/forms/filter.svg" />

<#-- Role administrator -->
<#assign userService = utilLocator.findUtil("com.liferay.portal.kernel.service.UserLocalService") />
<#assign rolService = utilLocator.findUtil("com.liferay.portal.kernel.service.RoleLocalService") />
<#assign userId = themeDisplay.getUserId() />
<#assign user = userService.getUser(userId)>
<#assign userRoles = user.getRoles()/>

<#assign groupId= themeDisplay.getLayout().getGroupId() /> 
<#assign recordSetservice = serviceLocator.findService("com.liferay.dynamic.data.lists.service.DDLRecordSetLocalService") />
<#assign lista = recordSetservice.getRecordSets(groupId) />
<#assign id = 0 />

<#list lista as item>
    <#if item.getName("en_GB") == "Qualified Parties Register">
        <#assign recordId = item.getRecordSetId() />
    </#if>
</#list>
        
<div id="qualified-parties">
    <div id="header">
        <div class="left">
            <h1>Qualified Parties Register</h1>
        </div>
<#if lista?has_content>

            <#list userRoles as rol>
                <#if rol.getName() == "Administrator" || rol.getName() == "Operational_Account_Manager" || rol.getName() == "Senior_Operational_Account_Manager" ||  rol.getName() == "Portal_Admin"  ||  rol.getName() == "Application_Administrator" ||  rol.getName() == "Assurance_Analyst" ||  rol.getName() == "Assurance_Analyst" ||  rol.getName() == "Change_Management_Team" >
                    <div class="right">
                        <input class="hide" id="add_new" type="submit" value="Add New" data="${recordId}" name="AddQuality"/>

                        <#assign url = "/group/guest/~/control_panel/manage?p_p_id=com_liferay_dynamic_data_lists_web_portlet_DDLPortlet&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_mvcPath=%2Fedit_record.jsp&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_redirect=/group/guest/qualified-parties-register&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_recordSetId="+ recordId +"&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_formDDMTemplateId=0&p_p_auth=qcyeCaTD" />

                        <a id="add_new" href="${url}">Add New</a>
                    </div>
                </#if>
            </#list>
    </div>

    <div id="table-info">

    <@liferay_portlet["runtime"]
                       instanceId="qualified_parties"
                        portletName="com_liferay_dynamic_data_lists_web_portlet_DDLDisplayPortlet"
                    />
                    
</#if>                    
    </div>
</div>

<script>
    $( document ).ready(function() {
        $( ".dropdown.lfr-icon-menu" ).hide();
    });
</script>

<#list userRoles as rol>        
    <#if rol.getName() == "Administrator" || rol.getName() == "Portal_Admin">
        <script>
            $( document ).ready(function() {
                $( ".dropdown.lfr-icon-menu" ).show();
            });
        </script>
    </#if>
</#list>