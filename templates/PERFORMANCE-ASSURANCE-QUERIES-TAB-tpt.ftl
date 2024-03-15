<!-- Organisation site -- > RFis and Query Tab --> 

<#assign recordSetservice = serviceLocator.findService("com.liferay.dynamic.data.lists.service.DDLRecordSetLocalService") />
<#assign groupLocalService = serviceLocator.findService("com.liferay.portal.kernel.service.GroupLocalService") />

<#assign groupId= themeDisplay.getLayout().getGroupId() />  

<#assign list = recordSetservice.getRecordSets(groupId) />
<#assign group = groupLocalService.getGroup(groupId) />


<#assign id = list[3].getRecordSetId() />

<div id="pa-queries" class="container-fluid">	
 <div class="row">
  <#assign org = group.getDescriptiveName() />
 
    <div id="trainingLog" class="col-md-12">
        <div id="header-consult">
            <div class="left">
                <h1 class="title">Training Log</h1>
            </div>
                <div class="right">
                    <input id="AddEntry" class="hide" type="submit" value="Add Training Log">

                    <#assign url = "/group/"+org+"/~/control_panel/manage?p_p_id=com_liferay_dynamic_data_lists_web_portlet_DDLPortlet&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_mvcPath=%2Fedit_record.jsp&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_redirect=%2Fgroup%2F"+org+"&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet%26p_p_lifecycle%3D0%26p_p_state%3Dmaximized%26p_p_mode%3Dview%26_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_redirect%3Dhttps%253A%252F%252Fwebserver-recportal-dev.lfr.cloud%252Fgroup%252F"+org+"&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet%2526p_p_lifecycle%253D0%2526p_p_state%253Dmaximized%2526p_p_mode%253Dview%2526_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_mvcPath%253D%25252Fview.jsp%2526_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_orderByCol%253Dmodified-date%2526_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_orderByType%253Dasc%2526_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_displayStyle%253Dlist%2526_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_cur%253D1%2526_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_delta%253D20%2526_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_description%253D%2526_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_name%253D%2526p_p_auth%253DotsCdWBa%26_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_displayStyle%3Dlist%26_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_mvcPath%3D%252Fview_record_set.jsp%26_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_recordSetId%3D"+id+"%26p_p_auth%3DotsCdWBa&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_recordSetId="+id+"&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_formDDMTemplateId=0&p_p_auth=otsCdWBa">

                    <a id="AddEntry" href="${url}">Add Training Log</a>	  
 
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

var org = window.location.href.substr(-19,6);

        $("#AddEntry").click(function(){
            let url = "/group/"+org+"/~/control_panel/manage?p_p_id=com_liferay_dynamic_data_lists_web_portlet_DDLPortlet&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_mvcPath=%2Fedit_record.jsp&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_redirect=%2Fgroup%2F"+org+"&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet%26p_p_lifecycle%3D0%26p_p_state%3Dmaximized%26p_p_mode%3Dview%26_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_redirect%3Dhttps%253A%252F%252Fwebserver-recportal-dev.lfr.cloud%252Fgroup%252F"+org+"&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet%2526p_p_lifecycle%253D0%2526p_p_state%253Dmaximized%2526p_p_mode%253Dview%2526_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_mvcPath%253D%25252Fview.jsp%2526_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_orderByCol%253Dmodified-date%2526_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_orderByType%253Dasc%2526_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_displayStyle%253Dlist%2526_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_cur%253D1%2526_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_delta%253D20%2526_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_description%253D%2526_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_name%253D%2526p_p_auth%253DotsCdWBa%26_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_displayStyle%3Dlist%26_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_mvcPath%3D%252Fview_record_set.jsp%26_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_recordSetId%3D"+${id}+"%26p_p_auth%3DotsCdWBa&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_recordSetId="+${id}+"&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_formDDMTemplateId=0&p_p_auth=otsCdWBa"
            
            window.location.href = url;
        });
    });
</script>

<style>
#header-consult {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
        }

        #header-consult .left {
            grid-column: 1 / 3;
            grid-row: 1;
        }


        .right {
            position: absolute;
            right: 1%;
        }
#AddEntry {
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

#view_records_id .container-fluid {
    max-width: 100% !important;
}

#trainingLog .portlet-content * {
    padding: 0;
    margin: 0;
}

#trainingLog {
    box-shadow: 0 1px 4px 0 rgba(0, 0, 0, 0.3) !important;
   padding: 34px 40px 25px 40px;
    background: white;
    border-radius: 4px;
}

#trainingLog h1.title {
    color: rgb(0, 0, 0);
    font-size: 20px;
    font-family: "Roboto-Bold" !important;
    font-weight: bold;
    letter-spacing: 0.75px;
    line-height: 30px;
}

#trainingLog td, #trainingLog th {
    font-size: 12px;
    line-height: 18px;
    letter-spacing: 0.25px;
    padding: 12px 0px 12px 12px !important;
}

#view_records_id nav ul.navbar-nav{
    visibility: visible !important;
}

    
 #trainingLog  .portlet-content.portlet-content-editable {
     box-shadow: none !important;
     padding: 0 !important;
 } 
 
   table.table-table.table {
       width: 100%;
   }
    
    ul.navbar-nav   {
        visibility: visible !important; 
    } 
    
     .lfr-entry-action-column{
         display:block !important;
     }
      .lfr-icon-menu{
        visibility: visible !important; 
    }
    
    th {
        padding-left: 5px;    
    }
    

    tr{
        border: none !important;
    }
    
     td{
    	border-width: 0;
	}
    
    .table-sort-indicator{
        display: none;
    }

	.table-x-scroller, .table-y-scroller-container {
	    border: none;
	}
	
	.table-striped tbody tr:nth-of-type(odd) td, .table-striped tbody tr:nth-of-type(odd) th {
	    background-color: #ffffff;
	}
	
	.table thead td, .table thead th {
	    border-bottom: none; 
	}
	
	.table-col-PM_Status {
	            font-family: Roboto-Bold;
	font-size: 10px;
	font-weight: bold;
	height: 15px;
	letter-spacing: 0.25px;
	line-height: 15px;
	width: 44px;
	display: unset;
	     }
	
	.lfr-spreadsheet-add-rows-buttons{
	    display: none;
	}

.table-x-scroller {
        width: 100% !important;
}
.container-fluid {
max-width: 100%;
}
</style>