<!-- My application details -->
<script>
    $( window ).on( "load", function() {
        $(".table-cell-content a").each(function(){
         $(this).removeAttr("href")
        })   
    });
    
	$(document).ready(function(){
	     $(".autofit-row.mb-4.metadata-author").hide();
	     $(".form-group.form-inline.input-checkbox-wrapper").hide();
	    //All tabcontent >0 hide
	    tabcontent = document.getElementsByClassName("tabcontent");
	    subtabcontent = document.getElementsByClassName("subtabcontent");
        for (i = 1; i < tabcontent.length; i++) {
            tabcontent[i].style.display = "none";
        }
        for (i = 0; i < subtabcontent.length; i++) {
            subtabcontent[i].style.display = "none";
        }
	    
	 })
</script>

<script>
    function openTab(evt, tabName) {
      // Declare all variables
      var i, tabcontent, tablinks;

      // Get all elements with class="tabcontent" and hide them
      tabcontent = document.getElementsByClassName("tabcontent");
      for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
      }
      subtabcontent = document.getElementsByClassName("subtabcontent");
      
        for (i = 1; i < subtabcontent.length; i++) {
            subtabcontent[i].style.display = "none";
        }
   
    
      // Get all elements with class="tablinks" and remove the class "active"
      tablinks = document.getElementsByClassName("tablinks");
      for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
      }
      subtablinks = document.getElementsByClassName("subtablinks");
      for (i = 0; i < subtablinks.length; i++) {
        subtablinks[i].className = subtablinks[i].className.replace(" active", "");
      }
    
      // Show the current tab, and add an "active" class to the button that opened the tab
      document.getElementById(tabName).style.display = "block";
       if(tabName == "Party-ipt")
      {
          subtabcontent[0].style.display = "block";
          subtablinks[0].className += " active";
      } else {
          subtabcontent[0].style.display = "none";
      }
      evt.currentTarget.className += " active";

      
    }

    function opensubTab(evt, tabName) {
      // Declare all variables
      var i, tabcontent, tablinks;

      // Get all elements with class="tabcontent" and hide them
      tabcontent = document.getElementsByClassName("subtabcontent");
      for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
      }
    
      // Get all elements with class="tablinks" and remove the class "active"
      tablinks = document.getElementsByClassName("subtablinks");
      for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
      }
    
      // Show the current tab, and add an "active" class to the button that opened the tab
      document.getElementById(tabName).style.display = "block";
      evt.currentTarget.className += " active";
    }
    
</script>

<#-- Roles -->

<#assign userService = utilLocator.findUtil("com.liferay.portal.kernel.service.UserLocalService") />
<#assign rolService = utilLocator.findUtil("com.liferay.portal.kernel.service.RoleLocalService") />
<#assign organisationService = utilLocator.findUtil("com.liferay.portal.kernel.service.OrganizationLocalService") />

<#--FOLDER-->
<#assign ddlFileEntry = serviceLocator.findService("com.liferay.document.library.kernel.service.DLFileEntryLocalService") />
<#assign fileEntry = ddlFileEntry.getDLFileEntries(0, ddlFileEntry.getFileEntriesCount()) />

<#list fileEntry as item>

    <#if item.title == "BSA Form.docx" && item.folderId != 0>   
        <#assign folders = item.getFolder() />  
        
        <#if folders.name == "Party Management">
        
            <#assign folderIdBSA = folders.folderId />
        </#if>
       
    </#if>
    <#if item.title == "ISDP Form.docx" && item.folderId != 0>  
        <#assign folders = item.getFolder() /> 
          
        <#if folders.name == "Party Management">
            <#assign folderIdISDP = folders.folderId />
        </#if>
        
    </#if>     
</#list>

<#--END FOLDER-->

<#assign userId = themeDisplay.getUserId() />
<#assign user = userService.getUser(userId) />
<#assign groupId= themeDisplay.getLayout().getGroupId() />
<#assign companyId= themeDisplay.getCompanyId() />

<#assign userRoles = user.getRoles()/>
<#assign userOrganisations = user.getOrganizations()/>

<#assign showAddButton = 0 />
<#assign showAddDocumentButton = 0 />
<#assign showAddMilestone = 0 />


<#list userOrganisations as organisations>
     <#if organisations.name == "Responsible_Service_Provider" || organisations.name == "REC_Performance_Assurance"  >
        <#assign showAddButton = 1 />
		<#assign showAddDocumentButton = 1 />
     </#if>
</#list>

<br>

<#list userRoles as rol>
    <#if rol.getName() == "Administrator" || rol.getName() == "Operational_Account_Manager" || rol.getName() == "Senior_Operational_Account_Manager" || rol.getName() == "RPA_Entry_Analyst" || rol.getName() == "Code_Manage_Master_Admin_User" || rol.getName() == "Assurance_Analyst" || rol.getName() == "REC_Contract_Managers" || rol.getName() == "Application_Administrator">
        <#assign showAddButton = 1 />
    </#if>
</#list>

<#list userRoles as rol>
    <#if rol.getName() == "Administrator" || rol.getName() == "Operational_Account_Manager" || rol.getName() == "Senior_Operational_Account_Manager" || rol.getName() == "RPA_Entry_Analyst" || rol.getName() == "Code_Manage_Master_Admin_User" || rol.getName() == "Assurance_Analyst" || rol.getName() == "REC_Contract_Managers" || rol.getName() == "Application_Administrator" || rol.getName() == "Portal_User">
        <#assign showAddDocumentButton = 1 />
    </#if>
</#list>

<#list userRoles as rol>
    <#if rol.getName() == "Administrator" || rol.getName() == "Operational_Account_Manager" || rol.getName() == "Application_Administrator" >
        <#assign showAddMilestone = 1 />
    </#if>
</#list>

<#assign journalArticleId = .vars['reserved-article-id'].data/>

<#assign groupId= themeDisplay.getLayout().getGroupId() />

<#-- GET JOURNAL ARTICLE  -->
<#assign journalArticleService = serviceLocator.findService("com.liferay.journal.service.JournalArticleLocalService") />
<#assign ddlJournalArticleService = serviceLocator.findService("com.everis.rec.ddl.journal.article.service.DDL_JournalArticleLocalService") />

<#assign article = journalArticleService.getArticle(groupId, "${journalArticleId}") />

<#assign resourcePrimKey = article.getResourcePrimKey() />
<#assign lastArticleV = journalArticleService.fetchLatestArticle(resourcePrimKey) />
<#assign articleStatus = lastArticleV.status />

<#assign pmReference = "APP" + resourcePrimKey />

<#assign document = saxReaderUtil.read(lastArticleV.getContent())/>

<#assign pmStatus = document.valueOf("//dynamic-element[@name='PM_Status']/dynamic-content/text()") />
<#assign applicationType = document.valueOf("//dynamic-element[@name='PM_ProvideDetailsOrganisation']/dynamic-content/text()") />


<#assign PM_Whatroleareyouapplyingfor = document.valueOf("//dynamic-element[@name='PM_Whatroleareyouapplyingfor']/dynamic-content") />

<#assign PM_Whatsystemsareyouapplyingtoaccess = document.valueOf("//dynamic-element[@name='PM_Whatsystemsareyouapplyingtoaccess']/dynamic-content") />

<div class="applications-detail">

<#assign urlEntry = "/group/guest/make-an-application?mode=EDIT&appId=" + resourcePrimKey />

<#assign hasEditPermission = permissionChecker.hasPermission(article.getGroupId(), "com.liferay.journal.model.JournalArticle", resourcePrimKey?number, "UPDATE") />

<#if pmStatus?has_content >
    <div class="row">
    <#if pmStatus == "Complete" >
        <p class="applicationStatusComplete" style="margin-left: 15px; background: #b1c568;"> ${pmStatus} </p>
    <#elseif pmStatus == "In Progress">
        <p class="applicationStatusInprogress" style="margin-left: 15px; background: #f5b01e;"> ${pmStatus} </p>
    </#if>
    </div>
</#if>
<div class="row">
    <h1> ${pmReference} - ${applicationType}</h1>
</div>

<!-- Tab links -->
<div class="tab">

<button class="tablinks active" onclick="openTab(event, 'Party-att')">Application Management</button>
<button class="tablinks" onclick="openTab(event, 'Party-ipt')">Application Details</button>
<#-- <button class="tablinks" onclick="openTab(event, 'Party-hty')">History of Activities</button> -->
<button class="tablinks" onclick="openTab(event, 'Party-mln')">Application Milestones</button>
</div>

<#-- GETTING SPECIFIC DDL ID FOR THIS ARTICLE -->
<#if ddlJournalArticleService.getDDL_JournalArticleByJournalPKAndName(resourcePrimKey, "APP-Milestone-")?has_content>
    <#assign ddlJAMilestone = ddlJournalArticleService.getDDL_JournalArticleByJournalPKAndName(resourcePrimKey, "APP-Milestone-") >
</#if>
<#if ddlJAMilestone?has_content>
    <#assign ddlMilestoneId = ddlJAMilestone.getDdlRecordSetId()>
</#if>
<#if ddlJournalArticleService.getDDL_JournalArticleByJournalPKAndName(resourcePrimKey, "APP-Activity-Log-")?has_content>
    <#assign ddlJAActivity = ddlJournalArticleService.getDDL_JournalArticleByJournalPKAndName(resourcePrimKey, "APP-Activity-Log-") >
</#if>
<#if ddlJAActivity?has_content>
    <#assign ddlActivityId = ddlJAActivity.getDdlRecordSetId()>
</#if>


<#if ddlJournalArticleService.getDDL_JournalArticleByJournalPKAndName(resourcePrimKey, "APP-Docs-")?has_content>
    <#assign ddlDocs = ddlJournalArticleService.getDDL_JournalArticleByJournalPKAndName(resourcePrimKey, "APP-Docs-") >
</#if>
<#if ddlDocs?has_content>
    <#assign ddlDocsId = ddlDocs.getDdlRecordSetId()>
</#if>


<#if ddlJournalArticleService.getDDL_JournalArticleByJournalPKAndName(resourcePrimKey, "APP-Business-Solution-")?has_content>
    <#assign ddlJABS = ddlJournalArticleService.getDDL_JournalArticleByJournalPKAndName(resourcePrimKey, "APP-Business-Solution-") >
</#if>
<#if ddlJABS?has_content>
    <#assign ddlBSId = ddlJABS.getDdlRecordSetId()>
</#if>
<#if ddlJournalArticleService.getDDL_JournalArticleByJournalPKAndName(resourcePrimKey, "APP-ISDP-")?has_content>
    <#assign ddlJAISDP = ddlJournalArticleService.getDDL_JournalArticleByJournalPKAndName(resourcePrimKey, "APP-ISDP-") >
</#if>
<#if ddlJAISDP?has_content>
    <#assign ddlISDPId = ddlJAISDP.getDdlRecordSetId()>
</#if>

<!-- Tab content -->

<!-- Application page details activity log -->
<#if ddlActivityId?has_content>
    <#assign preferencesActivity = "recordSetId=" + ddlActivityId />
    <div id="Party-att" class="tabcontent">
		<div class="col-md-12 portlet-column portlet-column-last" id="column-3">
			
            <#assign filterImg = themeDisplay.getPathThemeImages()+"/forms/filter.svg" />
            
            <div id="activityLogs">
                <h1 class="title" class="col-md-9">Activity Log</h1>
                <#-- add this param on the below input:data="${ddlRecordSetId}" -->
                <#if showAddButton ==1 >
                    
                    <#assign appTitle = article.getUrlTitle() />
                    <#assign url = "/group/guest/~/control_panel/manage?p_p_id=com_liferay_dynamic_data_lists_web_portlet_DDLPortlet&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_mvcPath=%2Fedit_record.jsp&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_redirect=/group/guest/-/" + appTitle + "&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_recordSetId=" + ddlActivityId + "&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_formDDMTemplateId=0&p_p_auth=tYkZwwfY" />
                    <a id="AddEntry" class="right" href="${url}">Add Activity Log</a>	

                </#if>
                <div class="clear"></div>
                
                <@liferay_portlet["runtime"]
                    instanceId="partyLog"
                    queryString=preferencesActivity
                    portletName="com_liferay_dynamic_data_lists_web_portlet_DDLDisplayPortlet"
                />             
		    </div>

            <#if ddlDocsId?has_content>
                <#assign preferencesDocs= "recordSetId=" + ddlDocsId />
                <div id="docsLogs">
                    <h1 class="title" class="col-md-9">Documents</h1>
                    <#if showAddDocumentButton == 1 >
                        <#assign appTitle = article.getUrlTitle() />
                        <#assign url = "/group/guest/~/control_panel/manage?p_p_id=com_liferay_dynamic_data_lists_web_portlet_DDLPortlet&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_mvcPath=%2Fedit_record.jsp&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_redirect=/group/guest/-/" + appTitle + "&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_recordSetId=" + ddlDocsId + "&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_formDDMTemplateId=0&p_p_auth=tYkZwwfY" />

                        <a id="AddEntry" class="right" href="${url}">Add Documents</a>
                    </#if>
                    <div class="clear"></div>
            
                    <@liferay_portlet["runtime"]
                        instanceId="partyDocs"
                        queryString=preferencesDocs
                        portletName="com_liferay_dynamic_data_lists_web_portlet_DDLDisplayPortlet"
                    />       
                </div>
            </#if>
        </div>
    </div>
</#if>

<!-- Application page details Application Details--->
	<div id="Party-ipt" class="tabcontent">
            <button class="subtablinks active" onclick="opensubTab(event, 'Detail-f')">Accession Form</button>
            <button class="subtablinks" onclick="opensubTab(event, 'Detail-bs')">Business Solution</button>
            <button class="subtablinks" onclick="opensubTab(event, 'Detail-isdp')">Information Security and Data Protection</button>
    </div>
    
    <!-- SubTab content -->
    <!-- Application page Application Details--->
    	<div id="Detail-f" class="subtabcontent">
	
			<#if (hasEditPermission || userId == article.getUserId()) && articleStatus == 0>
				<a id="AddEntry" class="right" href="${urlEntry}">Edit</a>	
			</#if>
    	
    	    <#if PM_CompanyInformationSeparator.PM_CompanyName?has_content && PM_CompanyInformationSeparator.PM_CompanyName.getData() != "">
    	        <p>Company Name:</p> <p  class="bold"><span>${PM_CompanyInformationSeparator.PM_CompanyName.getData()}</span></p>
    	    </#if>
    	    <#if PM_CompanyInformationSeparator.IA_CompanyRegistrationNumber?has_content && PM_CompanyInformationSeparator.IA_CompanyRegistrationNumber.getData() != "">
    	    <p> Company Registration Number:</p><p  class="bold"> <span>${PM_CompanyInformationSeparator.IA_CompanyRegistrationNumber.getData()}</span></p>
    	    </#if>
    	    <#if PM_CompanyInformationSeparator.PM_RegisteredAddress?has_content && PM_CompanyInformationSeparator.PM_RegisteredAddress.getData() != "">
    	    <p>Registered Address:</p><p  class="bold">  <span>${PM_CompanyInformationSeparator.PM_RegisteredAddress.getData()}</span></p>
    	    </#if>
    	    <#if PM_CompanyInformationSeparator.PM_ReasonForSubmission?has_content && PM_CompanyInformationSeparator.PM_ReasonForSubmission.getData() != "">
    	    <p>Reason for submission:</p><p  class="bold">  <span>${PM_CompanyInformationSeparator.PM_ReasonForSubmission.getData()}</span></p>
    	    </#if>
    	    <#if PM_CompanyInformationSeparator.PM_AuthorisingOfficerFullName?has_content && PM_CompanyInformationSeparator.PM_AuthorisingOfficerFullName.getData() != "">
            <h3>Authorising officer</h3>
             <hr>
    	    <p>Authorising Officer 1 Full name:</p><p  class="bold">  <span>${PM_AuthorisingOfficerSeparator.PM_AuthorisingOfficerFullName.getData()}</span></p>
    	    </#if>
    	    <#if PM_CompanyInformationSeparator.PM_AuthorisingOfficer1Role?has_content && PM_CompanyInformationSeparator.PM_AuthorisingOfficer1Role.getData() != "">
    	    <p>Authorising Officer 1 Role:</p><p  class="bold">  <span>${PM_AuthorisingOfficerSeparator.PM_AuthorisingOfficer1Role.getData()}</span></p>
    	    </#if>
    	     <#if PM_CompanyInformationSeparator.PM_AuthorisingOfficer1EmailAddress?has_content && PM_CompanyInformationSeparator.PM_AuthorisingOfficer1EmailAddress.getData() != "">
    	    <p>Authorising Officer 1 Email address:</p><p  class="bold">  <span>${PM_AuthorisingOfficerSeparator.PM_AuthorisingOfficer1EmailAddress.getData()}</span></p>
    	    </#if>
    	     <#if PM_CompanyInformationSeparator.PM_AuthorisingOfficer1BusinessAddress?has_content && PM_CompanyInformationSeparator.PM_AuthorisingOfficer1BusinessAddress.getData() != "">
    	    <p>Authorising Officer 1 Business address:</p><p  class="bold">  <span>${PM_AuthorisingOfficerSeparator.PM_AuthorisingOfficer1BusinessAddress.getData()}</span></p>
    	    </#if>
    	    <#if PM_AuthorisingOfficerSeparator.PM_AuthorisingOfficer1TelephoneNumber?has_content && PM_AuthorisingOfficerSeparator.PM_AuthorisingOfficer1TelephoneNumber.getData() != "">
    	    <p>Authorising Officer 1 Telephone:</p><p  class="bold">  <span>${PM_AuthorisingOfficerSeparator.PM_AuthorisingOfficer1TelephoneNumber.getData()}</span></p>
    	    </#if>
    	    <#if PM_AuthorisingOfficerSeparator.PM_AnyadditionalAuthorisingOfficer?has_content && PM_AuthorisingOfficerSeparator.PM_AnyadditionalAuthorisingOfficer.getData() != "">
    	    <p>Additional Authorising Officer:</p><p  class="bold">  <span>${PM_AuthorisingOfficerSeparator.PM_AnyadditionalAuthorisingOfficer.getData()}</span></p>
    	    </#if>
    	    <#if PM_CompanyKeyInformationSeparator.PM_KeyContact1Fullname?has_content && PM_CompanyKeyInformationSeparator.PM_KeyContact1Fullname.getData() != "">
            <h3>Company Information</h3>
            <hr>
    	    <p>Key Contact 1 Full name:</p><p  class="bold">  <span>${PM_CompanyKeyInformationSeparator.PM_KeyContact1Fullname.getData()}</span></p>
    	    </#if>
    	     <#if PM_CompanyKeyInformationSeparator.PM_KeyContact1Role?has_content && PM_CompanyKeyInformationSeparator.PM_KeyContact1Role.getData() != "">
    	    <p>Key Contact 1 Role:</p><p  class="bold">  <span>${PM_CompanyKeyInformationSeparator.PM_KeyContact1Role.getData()}</span></p>
    	    </#if>
    	     <#if PM_CompanyKeyInformationSeparator.PM_KeyContact1EmailAddress?has_content && PM_CompanyKeyInformationSeparator.PM_KeyContact1EmailAddress.getData() != "">
    	    <p>Key Contact 1 Email address:</p><p  class="bold">  <span>${PM_CompanyKeyInformationSeparator.PM_KeyContact1EmailAddress.getData()}</span></p>
             </#if>
             <#if PM_CompanyKeyInformationSeparator.PM_KeyContact1BusinessAddress?has_content && PM_CompanyKeyInformationSeparator.PM_KeyContact1BusinessAddress.getData() != "">
    	    <p>Key Contact 1 Business address:</p><p  class="bold">  <span>${PM_CompanyKeyInformationSeparator.PM_KeyContact1BusinessAddress.getData()}</span></p>
             </#if>
             <#if PM_CompanyKeyInformationSeparator.PM_KeyContact1TelephoneNumber?has_content && PM_CompanyKeyInformationSeparator.PM_KeyContact1TelephoneNumber.getData() != "">
    	    <p>Key Contact 1 Telephone address:</p><p  class="bold">  <span>${PM_CompanyKeyInformationSeparator.PM_KeyContact1TelephoneNumber.getData()}</span></p>
             </#if>
              <#if PM_CompanyKeyInformationSeparator.PM_Anyadditionalkeycontacts?has_content && PM_CompanyKeyInformationSeparator.PM_Anyadditionalkeycontacts.getData() != "">
    	    <p>Additional key contacts:</p><p  class="bold">  <span>${PM_CompanyKeyInformationSeparator.PM_Anyadditionalkeycontacts.getData()}</span></p>
             </#if>
             <#--  <#if PM_Whatroleareyouapplyingfor?has_content && PM_Whatroleareyouapplyingfor.getData() != ""> -->
			<#-- <#if PM_Whatroleareyouapplyingfor.getData() != "">-->
			<#if PM_Whatroleareyouapplyingfor != "">
    	    <#-- <p>Role applying: <span>${PM_Whatroleareyouapplyingfor.getData()}</span> -->
			<p>Role applying:</p><p class="bold"> <span>${PM_Whatroleareyouapplyingfor}</span></p>
    	    <#-- <ul>
    	    
    	    
    	    <#list PM_Whatroleareyouapplyingfor.getSiblings() as rol>
             <#if rol?has_content && rol.getData() != "">
    	        <p><span>${rol.getData()}</span></p>
            </#if>
    	    </#list> 
    	    </ul>
    	    </p>-->
             </#if>
			 
             <#-- <#if PM_Whatsystemsareyouapplyingtoaccess?has_content && PM_Whatsystemsareyouapplyingtoaccess.getData() != ""> -->
			<#if PM_Whatsystemsareyouapplyingtoaccess != "">
				<p>Systems applying to access:</p><p class="bold"> <span>${PM_Whatsystemsareyouapplyingtoaccess}</span> </p>
				<#--<p>Systems applying to access: <span>${PM_Whatsystemsareyouapplyingtoaccess.getData()}</span> 
				<ul>
				<#list PM_Whatsystemsareyouapplyingtoaccess.getSiblings() as systems>
				 <#if systems?has_content && systems.getData() != "">
					<p><span>${systems.getData()}</span></p>
					</#if>
				</#list>
				</ul>
				</p>-->
            </#if>
             
             
            
            <#if PM_ProvideDetailsOrganisation?has_content && PM_ProvideDetailsOrganisation.getData() != "">
                <p>Provide details organisation:</p><p class="bold"> <span>${PM_ProvideDetailsOrganisation.getData()}</span></p>
            </#if>

            <#if PM_Status?has_content && PM_Status.getData() != "">
                <#if pmStatus == "Complete" >
    	            <p>Status: <span class="applicationStatusComplete" style="background: #b1c568;">${PM_Status.getData()}</span></p>
                <#elseif pmStatus == "In Progress">
                    <p>Status: <span class="applicationStatusInprogress" style="background: #f5b01e;">${PM_Status.getData()}</span></p>
                </#if>
    	    </#if>
    	
        </div>
    <#if ddlBSId?has_content>
        <#assign preferencesBSA = "recordSetId=" + ddlBSId />
        
        <div id="Detail-bs" class="subtabcontent">
       
            <div id="listBSA">
            <a class="actions right" href="/documents/${groupId}/${folderIdBSA}/BSA+Form.docx" target="_blank">Download the BSA Form</a>
            
            <#-- add this param on the below input:data="${ddlBSId}" -->
            <input id="AddEntry" class="right hide" type="button" value="Add BSA" onclick="addEntryDDL()" data="${ddlBSId}" name="addBSAEntry"/>

            <#assign appTitle = article.getUrlTitle() />
            <#assign url = "/group/guest/~/control_panel/manage?p_p_id=com_liferay_dynamic_data_lists_web_portlet_DDLPortlet&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_mvcPath=%2Fedit_record.jsp&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_redirect=/group/guest/-/" + appTitle + "&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_recordSetId=" + ddlBSId + "&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_formDDMTemplateId=0&p_p_auth=tYkZwwfY" />
            
	         <a id="AddEntry" class="right" href="${url}">Submit the BSA</a>	  
                <div class="clear"></div>

                    <@liferay_portlet["runtime"]
                        instanceId="BSA"
                        queryString=preferencesBSA
                        portletName="com_liferay_dynamic_data_lists_web_portlet_DDLDisplayPortlet"
                    /> 
                
		    </div>
        </div>
    </#if>
        
    <#if ddlISDPId?has_content>
        <#assign preferencesISDP = "recordSetId=" + ddlISDPId />
        <div id="Detail-isdp" class="subtabcontent">
        <div id="listBSA">
         <a class="actions right" href="/documents/${groupId}/${folderIdISDP}/ISDP+Form.docx" target="_blank">
	            Download the ISDP Form
            </a> 
        <#-- add this param on the below input:data="${ddlISDPId}" -->
                <input id="AddEntry" class="right" type="button" value="Submit the ISDP" onclick="addEntryDDL()" data="${ddlISDPId}" name="addISDPEntry"/>
           
        
            
                <div class="clear"></div>

                <div class="ddl-list">
                <@liferay_portlet["runtime"]
                    instanceId="ISDP"
                    queryString=preferencesISDP
                    portletName="com_liferay_dynamic_data_lists_web_portlet_DDLDisplayPortlet"
                />  
                </div>
		    </div>

        </div>
    </#if>
        
    
    <#-- Application page details History of Activitie-
    	<div id="Party-hty" class="tabcontent">
    
        </div>-->

<!-- Application page details milestone--->
<#if ddlMilestoneId?has_content>
    <#assign preferencesMiles = "recordSetId=" + ddlMilestoneId />
	<div id="Party-mln" class="tabcontent">
		<div class="col-md-12 portlet-column portlet-column-first" id="column-2">
                
            <div id="milestone">
                <h1 class="title" class="col-md-9">Milestone</h1>
                <input id="AddEntry" class="right hide" type="button" value="Add Milestone" onclick="addEntryDDL()" data="${ddlMilestoneId}"  name="AddMilestoneEntry"/>

                <#if showAddMilestone ==1 >
                    <#assign appTitle = article.getUrlTitle() />
                    <#assign url = "/group/guest/~/control_panel/manage?p_p_id=com_liferay_dynamic_data_lists_web_portlet_DDLPortlet&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_mvcPath=%2Fedit_record.jsp&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_redirect=/group/guest/-/" + appTitle + "&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_recordSetId=" + ddlMilestoneId + "&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_formDDMTemplateId=0&p_p_auth=tYkZwwfY" />
                    <a id="AddEntry" class="right" href="${url}">Add Milestone</a>	
                </#if>

                <div class="clear"></div>
                <@liferay_portlet["runtime"]
                    instanceId="partyMiles"
                    queryString=preferencesMiles
                    portletName="com_liferay_dynamic_data_lists_web_portlet_DDLDisplayPortlet"
                />                
            </div>
		</div>
    </div>    
</#if>

<!-- Comment for all page--->    
    <div class="portlet-layout row">
		<div class="col-md-12 portlet-column portlet-column-only" id="column-1">
		</div>
    </div>
  </div>  
<#-- scripts for ddl - add entries -->
<script>
    function addEntryDDL(){
        let nameOfFunction = this[event.target.name];
        let ddlRecordSetId = event.target.getAttribute('data');
		let pathname = window.location.pathname;
        let url = '/group/guest/~/control_panel/manage?p_p_id=com_liferay_dynamic_data_lists_web_portlet_DDLPortlet&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_mvcPath=%2Fedit_record.jsp&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_redirect=' + pathname + '&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_recordSetId=' + ddlRecordSetId + '&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_formDDMTemplateId=0&p_p_auth=tYkZwwfY';
        window.location.href = url;
    }
</script>

<script>

    $("a").ready(function() {
            Liferay.Service(
            '/rec_ddl_journal_article.ddl_journalarticle/remove-permissions-owner',
                {
                    userId: ${userId}
                },
                function(obj) {
                    console.log(obj);
                }
            );
        }
    );

</script>


<style>
    a#AddEntry:hover {
        text-decoration: none;
        color: white;
    }

    .container-fluid, .container-sm, .container-md, .container-lg, .container-xl {
        margin-left: 0px !important;
    }
    
    .portlet-asset-publisher .show-asset-title .visible-interaction a.icon-monospaced {
        visibility: hidden;
    }   
	
	<#if !permissionChecker.isOmniadmin()>
		.applications-detail .portlet-content-editable:hover {
			border-color: transparent !important;
		}
		
		.applications-detail .portlet-topper {
			visibility: hidden;
		}
	</#if>

	.table-cell-content a:hover {
	    text-decoration:none;
	    cursor: default;
	}

    .d-inline-flex{
	    display: none !important;
	}
</style>