<#assign recordSetservice = serviceLocator.findService("com.liferay.dynamic.data.lists.service.DDLRecordSetLocalService") />
<#assign groupId= themeDisplay.getLayout().getGroupId() />  

<#assign list = recordSetservice.getRecordSets(groupId) />


<#assign id = list[3].getRecordSetId() />

<div id="pa-queries" class="container-fluid">	
 <div class="row">
 
    <div id="trainingLog" class="col-md-12">
        <div id="header-consult">
            <div class="left">
                <h1 class="title">Training Log</h1>
            </div>
                <div class="right">
                    <input id="AddEntry" type="submit" value="Add Training Log">
                </div>
        </div>
        <@liferay_portlet["runtime"]
                    instanceId="training_log"
                    portletName="com_liferay_dynamic_data_lists_web_portlet_DDLDisplayPortlet"
        />    
    </div>
</div>
<div class="row">
      <div class="col-md-12">
        <@liferay_portlet["runtime"]
                    instanceId="comments"
                    portletName="com_liferay_comment_page_comments_web_portlet_PageCommentsPortlet"
        />    
    </div>
</div>
<div class="row">
      <div id="documentsMedia" class="col-md-12">
        <@liferay_portlet["runtime"]
                    instanceId="documents"
                    portletName="com_liferay_document_library_web_portlet_DLPortlet"
        />    
    </div>
</div>

</div>

<script>
$(document).ready(function(){
// The name of the organisation has to be org001 for example in dev while in uat is organisation01 so the rule used would be this substr(46,14);
var org = window.location.href.substr(-19,6);

        $("#AddEntry").click(function(){
            let url = "/group/"+org+"/~/control_panel/manage?p_p_id=com_liferay_dynamic_data_lists_web_portlet_DDLPortlet&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_mvcPath=%2Fedit_record.jsp&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_redirect=%2Fgroup%2F"+org+"&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet%26p_p_lifecycle%3D0%26p_p_state%3Dmaximized%26p_p_mode%3Dview%26_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_redirect%3Dhttps%253A%252F%252Fwebserver-recportal-dev.lfr.cloud%252Fgroup%252F"+org+"&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet%2526p_p_lifecycle%253D0%2526p_p_state%253Dmaximized%2526p_p_mode%253Dview%2526_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_mvcPath%253D%25252Fview.jsp%2526_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_orderByCol%253Dmodified-date%2526_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_orderByType%253Dasc%2526_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_displayStyle%253Dlist%2526_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_cur%253D1%2526_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_delta%253D20%2526_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_description%253D%2526_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_name%253D%2526p_p_auth%253DotsCdWBa%26_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_displayStyle%3Dlist%26_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_mvcPath%3D%252Fview_record_set.jsp%26_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_recordSetId%3D"+${id}+"%26p_p_auth%3DotsCdWBa&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_recordSetId="+${id}+"&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_formDDMTemplateId=0&p_p_auth=otsCdWBa"
            
            window.location.href = url;
        });
    });
</script>
