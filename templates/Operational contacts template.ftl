<!-- Operational contacts template -->

<#assign Imagefilter = themeDisplay.getPathThemeImages()+"/forms/filter.svg" />
<#assign userRoles = user.getRoles()/>

<#-- GETTING DYMANIC URL PARAMETERS -->
<#assign instanceId = themeDisplay.getPortletDisplay().getInstanceId() />
<#assign plid = themeDisplay.getPlid() />
<#assign groupId= themeDisplay.getLayout().getGroupId() /> 

<#assign recordSetservice = serviceLocator.findService("com.liferay.dynamic.data.lists.service.DDLRecordSetLocalService") />
<#assign lista = recordSetservice.getRecordSets(groupId) />

<#list lista as item>
    <#if item.getName("en_GB") == "Operational Contacts Register">
        <#assign recordId = item.getRecordSetId() />
    </#if>
</#list>

<#-- END -->

<div id="operational-contacts-register">
    <div id="header">
        <div class="left">
            <h1>Operational Contacts Register</h1>
        </div>
        <div class="right">
            <input class="hide" id="add_new" type="submit" value="Add New">

            <#assign url = "/group/guest/~/control_panel/manage?p_p_id=com_liferay_dynamic_data_lists_web_portlet_DDLPortlet&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&refererPlid="+ plid +"&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_mvcPath=%2Fedit_record.jsp&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_recordSetId="+ recordId +"&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_redirect=%2Fgroup%2Fguest%2Foperational-contacts-register&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_referringPortletResource=com_liferay_asset_publisher_web_portlet_AssetPublisherPortlet_INSTANCE_"+ instanceId +"&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_assetTagNames=&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_portletResource=com_liferay_asset_publisher_web_portlet_AssetPublisherPortlet&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_groupId="+ groupId +"&p_p_auth=0lspQUnn" />

            <a id="add_new" href="${url}">Add New</a>
        </div>
    </div>

   
    
    <div id="table-info">

        <@liferay_portlet["runtime"]
            instanceId="operational_contacts"
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
