<!-- Party and MEM -->
<#-- Role administrator -->
<#assign userService = utilLocator.findUtil("com.liferay.portal.kernel.service.UserLocalService") />
<#assign rolService = utilLocator.findUtil("com.liferay.portal.kernel.service.RoleLocalService") />
<#assign userId = themeDisplay.getUserId() />
<#assign user = userService.getUser(userId)>
<#assign userRoles = user.getRoles()/>

<#assign Imagefilter = themeDisplay.getPathThemeImages()+"/forms/filter.svg" />

<#-- GETTING DYMANIC URL PARAMETERS -->
<#assign instanceId = themeDisplay.getPortletDisplay().getInstanceId() />
<#assign plid = themeDisplay.getPlid() />
<#assign groupId= themeDisplay.getLayout().getGroupId() />

<#assign recordSetservice = serviceLocator.findService("com.liferay.dynamic.data.lists.service.DDLRecordSetLocalService") />
<#assign lista = recordSetservice.getRecordSets(groupId) />

<#list lista as item>
    <#if item.getName("en_GB") == "Party and MEM Register">
        <#assign recordId = item.getRecordSetId() />
    </#if>
</#list>

<#-- END -->

<div id="party-and-mem">
    <div id="header">
        <div class="left">
            <h1>Party and MEM Register</h1>
        </div>
           
        <#list userRoles as rol>
            <#if rol.getName() == "Administrator" || rol.getName() == "Operational_Account_Manager" || rol.getName() == "Senior_Operational_Account_Manager" ||  rol.getName() == "Portal_Admin">
                <div class="right">
                    <input class="hide" id="add_new" type="submit" value="Add New">

                    <#assign url = "/group/guest/~/control_panel/manage?p_p_id=com_liferay_dynamic_data_lists_web_portlet_DDLPortlet&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&refererPlid="+ plid +"&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_mvcPath=%2Fedit_record.jsp&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_recordSetId="+ recordId +"&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_redirect=%2Fgroup%2Fguest%2Fparty-and-mem-register&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_referringPortletResource=com_liferay_asset_publisher_web_portlet_AssetPublisherPortlet_INSTANCE_"+ instanceId +"&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_assetTagNames=&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_portletResource=com_liferay_asset_publisher_web_portlet_AssetPublisherPortlet&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_groupId="+ groupId +"&p_p_auth=0lspQUnn" />

                    <a id="add_new" href="${url}">Add New</a>
                </div>
            </#if>
        </#list>
    </div>

    <div id="table-info">
        <@liferay_portlet["runtime"]
            instanceId="party_mem"
            portletName="com_liferay_dynamic_data_lists_web_portlet_DDLDisplayPortlet"
        />          
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
