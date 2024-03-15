<#assign images_folder = themeDisplay.getPathThemeImages() />

<div id="pa-organisation">
 <div id="pa-home" class="container-fluid">	
 <div class="row">
    <div id="maintenance-assessment" class="col-md-6">
    <div>
        <h1 class="title">Maintenance and Assessment</h1>
        <#--<div class="filterOpt" >
            <a onclick="openFilterPopUp()">
                <img id="filterImg" src="${images_folder}/forms/filter.svg"/> 
                        Filter
            </a>
        </div>-->
    </div>

        <@liferay_portlet["runtime"]
                    instanceId="maintenance-assesment"
                    queryString=preferencesActivity
                    portletName="com_liferay_dynamic_data_lists_web_portlet_DDLDisplayPortlet"
        />    
    </div>
    <div id="incentives-risk-monitoring"  class="col-md-6 title">
        <h1 class="title">Incentives and Risk Monitoring</h1>
        <@liferay_portlet["runtime"]
                    instanceId="incentives-risk-monitoring"
                    queryString=preferencesActivity
                    portletName="com_liferay_dynamic_data_lists_web_portlet_DDLDisplayPortlet"
        /> 
    </div>
</div>
<div class="row">
     <div id="remediation-tracker"  class="col-md-6 title">
        <h1 class="title">Remediation Tracker</h1>
        <@liferay_portlet["runtime"]
                    instanceId="remediation-tracker"
                    queryString=preferencesActivity
                    portletName="com_liferay_dynamic_data_lists_web_portlet_DDLDisplayPortlet"
        /> 
    </div>
     <div id="organisation-calendar"  class="col-md-6 title">
        
        <@liferay_portlet["runtime"]
                    instanceId="Organization-calendar"
                    queryString=preferencesActivity
                    portletName="com_liferay_calendar_web_portlet_CalendarPortlet"
        /> 
    </div>
</div>
 

<!--
        </div>
        <div id="quick-links" class="tabcontent container-fluid">	
            <h1> Quick Links </h1>
        </div>
        <div id="data-dashboard" class="tabcontent container-fluid">
            <h1> Data Dashboard </h1>
        </div>
        <div id="trends" class="tabcontent container-fluid">	
            <h1> Trends</h1>
        </div>
        <div id="guiadance" class="tabcontent container-fluid">	
            <h1> </h1>
        </div>
        <div id="training" class="tabcontent container-fluid">	
            <h1> Guiadance </h1>
        </div>
        <div id="rfis-queries" class="tabcontent container-fluid">	
            <h1> RFIs & Queries </h1>
        </div>
        <div id="forms" class="tabcontent container-fluid">	
            <h1> Forms </h1>
        </div>
        <div id="surveys" class="tabcontent container-fluid">
            <h1> Surveys </h1>
        </div>
        <div id="data-submission" class="tabcontent container-fluid">	
            <h1> Data Submission </h1>
        </div>-->
        
    </div> 
</div>

<style>
    ul.navbar-nav   {
        visibility: visible !important; 
    } 
    
     .lfr-entry-action-column{
         display:block !important;
     }
      .lfr-icon-menu{
        visibility: visible !important; 
    }
</style>