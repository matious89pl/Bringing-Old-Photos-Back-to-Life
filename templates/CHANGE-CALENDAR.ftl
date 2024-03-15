<#-- GETTING DYMANIC URL PARAMETERS -->
<#assign groupId= themeDisplay.getLayout().getGroupId() /> 
<#assign recordSetservice = serviceLocator.findService("com.liferay.dynamic.data.lists.service.DDLRecordSetLocalService") />
<#assign lista = recordSetservice.getRecordSets(groupId) />

<#assign instanceId = themeDisplay.getPortletDisplay().getInstanceId() />
<#assign plid = themeDisplay.getPlid() />

<#list lista as item>
    <#if item.getName("en_GB") == "Change Calendar">
        <#assign recordId = item.getRecordSetId() />
    </#if>
</#list>

<#-- END -->

<#assign Imagefilter = themeDisplay.getPathThemeImages()+"/forms/filter.svg" />

<#assign addNewButton = 0 />

<#assign userService = utilLocator.findUtil("com.liferay.portal.kernel.service.UserLocalService") />
<#assign userId = themeDisplay.getUserId() />
<#assign user = userService.getUser(userId)>
<#assign userRoles = user.getRoles()/>

<#list userRoles as rol>
    <#if rol.getName() == "Change_Management_Team" || rol.getName() == "Administrator" >
        <#assign addNewButton = 1 />
        <#break>
    </#if> 
</#list> 
       
<div id="change-calendar-page">
    <div id="header">
        <div class="left">
            <h1>Change Calendar</h1>
        </div>
        <div class="right">
        <#if addNewButton == 1 >
            <input id="add_new" class="hide" type="submit" value="Add New">

            <#assign url = "/group/guest/~/control_panel/manage?p_p_id=com_liferay_dynamic_data_lists_web_portlet_DDLPortlet&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&refererPlid="+ plid +"&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_mvcPath=%2Fedit_record.jsp&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_recordSetId="+ recordId +"&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_redirect=%2Fgroup%2Fguest%2Fchange-calendar&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_referringPortletResource=com_liferay_asset_publisher_web_portlet_AssetPublisherPortlet_INSTANCE_+ instanceId +&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_assetTagNames=&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_portletResource=com_liferay_asset_publisher_web_portlet_AssetPublisherPortlet&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_groupId=" + groupId + "&p_p_auth=0lspQUnn" />
            
            <a id="add_new" href="${url}">Add New</a>

        </#if>

        </div>
    </div>

   
    
<div id="table-info">

<@liferay_portlet["runtime"]
                   instanceId="changeCalendar"
                    portletName="com_liferay_dynamic_data_lists_web_portlet_DDLDisplayPortlet"
                />
                              
</div>
</div>

<style>
    a#add_new {
        text-decoration: none;
    }
</style>