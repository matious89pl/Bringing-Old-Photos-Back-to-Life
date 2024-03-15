<#assign ImageruteThankyou = themeDisplay.getPathThemeImages()+"/forms/check.svg" />
<#assign counterService = utilLocator.findUtil("com.liferay.counter.kernel.service.CounterLocalService") />
<#assign journalArticleService = serviceLocator.findService("com.liferay.journal.service.JournalArticleLocalService") />
<#assign appId = counterService.increment("com.liferay.journal.model.JournalArticle") />
<#assign editMode = false />
<#assign appResourcePK = 0 />

<#assign ja_title = "" />
<#assign ja_PM_CompanyName = "" />
<#assign ja_IA_CompanyRegistrationNumber = "" />
<#assign ja_PM_RegisteredAddress = "" />
<#assign ja_PM_ReasonForSubmission = "" />
<#assign ja_PM_AuthorisingOfficerFullName = "" />
<#assign ja_PM_AuthorisingOfficer1Role = "" />
<#assign ja_PM_AuthorisingOfficer1EmailAddress = "" />
<#assign ja_PM_AuthorisingOfficer1BusinessAddress = "" />
<#assign ja_PM_AuthorisingOfficer1TelephoneNumber = "" />
<#assign ja_PM_AnyadditionalAuthorisingOfficer = "" />
<#assign ja_PM_KeyContact1Fullname = "" />
<#assign ja_PM_KeyContact1Role = "" />
<#assign ja_PM_KeyContact1EmailAddress = "" />
<#assign ja_PM_KeyContact1BusinessAddress = "" />
<#assign ja_PM_KeyContact1TelephoneNumber = "" />
<#assign ja_PM_Anyadditionalkeycontacts = "" />
<#assign ja_PM_Whatroleareyouapplyingfor = "" />
<#assign ja_PM_Whatsystemsareyouapplyingtoaccess = "" />
<#assign ja_PM_ProvideDetailsOrganisation = "" />
<#assign ja_PM_Status = "" />

<#if request.getParameter("mode")?? && request.getParameter("mode") == "EDIT" && request.getParameter("appId")??>
	<#assign mode = request.getParameter("mode") />
	<#assign appResourcePK = request.getParameter("appId") />
	<#attempt>
	  <#assign appJournal = journalArticleService.fetchLatestArticle(appResourcePK?number) />
		<#assign ja_title = appJournal.getTitle() />	
		<#assign document = saxReaderUtil.read(appJournal.getContent())/>	
		<#assign ja_PM_CompanyName = document.valueOf("//dynamic-element[@name='PM_CompanyName']/dynamic-content/text()") />
		<#assign ja_IA_CompanyRegistrationNumber = document.valueOf("//dynamic-element[@name='IA_CompanyRegistrationNumber']/dynamic-content/text()") />
		<#assign ja_PM_RegisteredAddress = document.valueOf("//dynamic-element[@name='PM_RegisteredAddress']/dynamic-content/text()") />
		<#assign ja_PM_ReasonForSubmission = document.valueOf("//dynamic-element[@name='PM_ReasonForSubmission']/dynamic-content/text()") />
		<#assign ja_PM_AuthorisingOfficerFullName = document.valueOf("//dynamic-element[@name='PM_AuthorisingOfficerFullName']/dynamic-content/text()") />
		<#assign ja_PM_AuthorisingOfficer1Role = document.valueOf("//dynamic-element[@name='PM_AuthorisingOfficer1Role']/dynamic-content/text()") />
		<#assign ja_PM_AuthorisingOfficer1EmailAddress = document.valueOf("//dynamic-element[@name='PM_AuthorisingOfficer1EmailAddress']/dynamic-content/text()") />
		<#assign ja_PM_AuthorisingOfficer1BusinessAddress = document.valueOf("//dynamic-element[@name='PM_AuthorisingOfficer1BusinessAddress']/dynamic-content/text()") />
		<#assign ja_PM_AuthorisingOfficer1TelephoneNumber = document.valueOf("//dynamic-element[@name='PM_AuthorisingOfficer1TelephoneNumber']/dynamic-content/text()") />
		<#assign ja_PM_AnyadditionalAuthorisingOfficer = document.valueOf("//dynamic-element[@name='PM_AnyadditionalAuthorisingOfficer']/dynamic-content/text()") />
		<#assign ja_PM_KeyContact1Fullname = document.valueOf("//dynamic-element[@name='PM_KeyContact1Fullname']/dynamic-content/text()") />
		<#assign ja_PM_KeyContact1Role = document.valueOf("//dynamic-element[@name='PM_KeyContact1Role']/dynamic-content/text()") />
		<#assign ja_PM_KeyContact1EmailAddress = document.valueOf("//dynamic-element[@name='PM_KeyContact1EmailAddress']/dynamic-content/text()") />
		<#assign ja_PM_KeyContact1BusinessAddress = document.valueOf("//dynamic-element[@name='PM_KeyContact1BusinessAddress']/dynamic-content/text()") />
		<#assign ja_PM_KeyContact1TelephoneNumber = document.valueOf("//dynamic-element[@name='PM_KeyContact1TelephoneNumber']/dynamic-content/text()") />
		<#assign ja_PM_Anyadditionalkeycontacts = document.valueOf("//dynamic-element[@name='PM_Anyadditionalkeycontacts']/dynamic-content/text()") />
		
		<#assign ja_PM_Whatroleareyouapplyingfor = document.valueOf("//dynamic-element[@name='PM_Whatroleareyouapplyingfor']/dynamic-content") />
		
		<#assign ja_PM_Whatsystemsareyouapplyingtoaccess = document.valueOf("//dynamic-element[@name='PM_Whatsystemsareyouapplyingtoaccess']/dynamic-content") />
		
		<#assign ja_PM_ProvideDetailsOrganisation = document.valueOf("//dynamic-element[@name='PM_ProvideDetailsOrganisation']/dynamic-content/text()") />
		<#assign ja_PM_Status = document.valueOf("//dynamic-element[@name='PM_Status']/dynamic-content/text()") />
		
		<#assign hasEditPermission = permissionChecker.hasPermission(appJournal.getGroupId(), "com.liferay.journal.model.JournalArticle", appResourcePK?number, "UPDATE") />
		<#if hasEditPermission>
			<#assign editMode = true />
		</#if>
	<#recover>
	  
	</#attempt>    
</#if>

<p style="display: none">${ja_PM_Whatroleareyouapplyingfor}</p>

<div id="raise-ticket">
<h1 id="main-title">Make an application</h1>

<div id="form-box">
<input id="checkEditMode" type="hidden" value="${editMode?string}" />
<form action="" onsubmit="validateForm();return false" method="post">	
  <div class="row">							
    <div class="col-md-12">							
      <h3>Please detail your company information</h3>    						
      <ul>							
        <div class="wrapper">
          <div class="row">
		        <div class="col-md-6">
              <label class="required">Company name</label>
              <input class="company_name_input" type="text" value="${ja_PM_CompanyName}" required oninvalid="this.setCustomValidity('This field is required')" oninput="this.setCustomValidity('')"/>                  
            </div>
		        <div class="col-md-6">
              <label class="required">Company Registration Number</label>
              <input class="company_registration_input" type="text" value="${ja_IA_CompanyRegistrationNumber}" required oninvalid="this.setCustomValidity('This field is required')" oninput="this.setCustomValidity('')"/>                    
            </div>
          </div>
          <div class="row">
		        <div class="col-md-6">
              <label class="required">Registered Address</label>
              <input class="registered_address_input" type="text" value="${ja_PM_RegisteredAddress}" required oninvalid="this.setCustomValidity('This field is required')" oninput="this.setCustomValidity('')"/>                   
            </div>
		        <div class="col-md-6">
              <label>Reason for submission</label>
              <input class="submission_reason_input" type="text" value="${ja_PM_ReasonForSubmission}" />                  
            </div>
        	</div>
        </div>														
      </ul>
    </div>

    <div class="col-md-12">
      <h3>Please detail your authorising officer</h3>							
      <ul>							
        <div class="wrapper">
          <div class="row">
		        <div class="col-md-6">
              <label class="required">Authorising Officer 1 Full name</label>
              <input class="auth_officer_fullname_input" type="text" value="${ja_PM_AuthorisingOfficerFullName}" required oninvalid="this.setCustomValidity('This field is required')" oninput="this.setCustomValidity('')"/>                   
            </div>
		        <div class="col-md-6">
              <label>Authorising Officer 1 Role</label>
              <input class="auth_officer_role_input" type="text" value="${ja_PM_AuthorisingOfficer1Role}" />                  
            </div>
          </div>
          <div class="row">
		        <div class="col-md-6">
              <label class="required">Authorising Officer 1 Email address</label>
              <input class="auth_officer_email_input" type="text" value="${ja_PM_AuthorisingOfficer1EmailAddress}" required oninvalid="this.setCustomValidity('This field is required')" oninput="this.setCustomValidity('')"/>                   
            </div>
		        <div class="col-md-6">
              <label class="required">Authorising Officer 1 Business addressRequired</label>
              <input class="auth_officer_email_req_input" type="text" value="${ja_PM_AuthorisingOfficer1BusinessAddress}" required oninvalid="this.setCustomValidity('This field is required')" oninput="this.setCustomValidity('')"/>                  
            </div>
          </div>
          <div class="row">
            <div class="col-md-6">
              <label>Authorising Officer 1 Telephone Number</label>
              <input class="auth_officer_phone_input" type="text" value="${ja_PM_AuthorisingOfficer1TelephoneNumber}" />                  
            </div>
		        <div class="col-md-6">
              <label>Any additional Authorising Officer?</label>
			  <#if editMode>
				<select class="additional_auth_input">
					<option></option>
					<#list PM_AuthorisingOfficerSeparator.PM_AnyadditionalAuthorisingOfficer.getOptionsMap() as key, value> 
						<#if ja_PM_AnyadditionalAuthorisingOfficer?contains(value)>
							<option selected>${value}</option>
						<#else>
							<option>${value}</option>
						</#if>
					</#list>
				</select> 
			  <#else>
				<select class="additional_auth_input">
					<option></option>
					<#list PM_AuthorisingOfficerSeparator.PM_AnyadditionalAuthorisingOfficer.getOptionsMap() as key, value> 
						<option>${value}</option>
					</#list>
				</select> 
			  </#if>	  
            </div>
          </div>
        </div>														
      </ul>
    </div>

    <div class="col-md-12">
      <h3>Please detail your company information</h3>							
      <ul>							
        <div class="wrapper">
          <div class="row">
		        <div class="col-md-6">
              <label class="required">Key Contact 1 Full name</label>
              <input class="key_contact_fullname_input" type="text" value="${ja_PM_KeyContact1Fullname}" required oninvalid="this.setCustomValidity('This field is required')" oninput="this.setCustomValidity('')"/>                   
            </div>
		        <div class="col-md-6">
              <label>Key Contact 1 Role</label>
              <input class="key_contact_role_input" type="text" value="${ja_PM_KeyContact1Role}" />                  
            </div>
          </div>
          <div class="row">
		        <div class="col-md-6">
              <label class="required">Key Contact 1 Email address</label>
              <input class="key_contact_email_input" type="text" value="${ja_PM_KeyContact1EmailAddress}" required oninvalid="this.setCustomValidity('This field is required')" oninput="this.setCustomValidity('')"/>                
            </div>
		        <div class="col-md-6">
              <label class="required">Key Contact 1 Business address</label>
              <input class="key_contact_buisiness_email_input" type="text" value="${ja_PM_KeyContact1BusinessAddress}" required oninvalid="this.setCustomValidity('This field is required')" oninput="this.setCustomValidity('')"/>                  
            </div>
          </div>
          <div class="row">
            <div class="col-md-6">
              <label>Key Contact 1 Telephone Number</label>
              <input class="key_contact_phone_input" type="text" value="${ja_PM_KeyContact1TelephoneNumber}" />                 
            </div>
		        <div class="col-md-6">
              <label>Any additional key contacts?</label>
				<#if editMode>
				<select class="additional_key_input">
					<option></option>
					<#list PM_CompanyKeyInformationSeparator.PM_Anyadditionalkeycontacts.getOptionsMap() as key, value> 
						<#if ja_PM_Anyadditionalkeycontacts?contains(value)>
							<option selected>${value}</option>
						<#else>
							<option>${value}</option>
						</#if>
					</#list>
				</select> 
			  <#else>
				<select class="additional_key_input">
					<option></option>
					<#list PM_CompanyKeyInformationSeparator.PM_Anyadditionalkeycontacts.getOptionsMap() as key, value> 
						<option>${value}</option>
					</#list>
				</select> 
			  </#if>
			  
            </div>
          </div>
          <div class="row">
            <div class="col-md-6">
              <label>What role are you applying for?</label>
			<#if editMode>
				<select class="multiple role_applying_for_input" multiple>
					<option></option>
					<#list PM_Whatroleareyouapplyingfor.getOptionsMap() as key, value> 
						<#if ja_PM_Whatroleareyouapplyingfor?contains(value)>
							<option selected="selected">${value}</option>
						<#else>
							<option>${value}</option>
						</#if>
					</#list>
				</select> 
			  <#else>
				<select class="multiple role_applying_for_input" multiple>
					<option></option>
					<#list PM_Whatroleareyouapplyingfor.getOptionsMap() as key, value> 
						<option>${value}</option>
					</#list>
				</select> 
			  </#if>  
            </div>
		        <div class="col-md-6">
              <label>What systems are you applying to access?</label>
			<#if editMode>
				<select class="multiple system_applying_for_input" multiple>
					<option></option>
					<#list PM_Whatsystemsareyouapplyingtoaccess.getOptionsMap() as key, value> 
						<#if ja_PM_Whatsystemsareyouapplyingtoaccess?contains(value)>
							<option selected="selected">${value}</option>
						<#else>
							<option>${value}</option>
						</#if>
					</#list>
				</select> 
			  <#else>
				<select class="multiple system_applying_for_input" multiple>
					<option></option>
					<#list PM_Whatsystemsareyouapplyingtoaccess.getOptionsMap() as key, value> 
						<option>${value}</option>
					</#list>
				</select> 
			  </#if>
            </div>
          </div>
          <div class="row">
            <div class="col-md-12">
              <label>Please provide details around where you organisation is in terms of certifying against current recognised industry standards, agreements and obligations where applicable</label>
				<#if editMode>
				<select class="provide_details_input">
					<option></option>
					<#list PM_ProvideDetailsOrganisation.getOptionsMap() as key, value> 
						<#if ja_PM_ProvideDetailsOrganisation?contains(value)>
							<option selected>${value}</option>
						<#else>
							<option>${value}</option>
						</#if>
					</#list>
				</select> 
			  <#else>
				<select class="provide_details_input">
					<option></option>
					<#list PM_ProvideDetailsOrganisation.getOptionsMap() as key, value> 
						<option>${value}</option>
					</#list>
				</select> 
			  </#if>
            </div>
          </div>
          <div class="row">
		        <div class="col-md-12">
              <label>Status (to fill in by Change Management Team)</label>			  
			  <#if editMode>
				<select class="status_input">
					<option></option>
					<#list PM_Status.getOptionsMap() as key, value> 
						<#if ja_PM_Status?contains(value)>
							<option selected>${value}</option>
						<#else>
							<option>${value}</option>
						</#if>
					</#list>
				</select> 
			  <#else>
				<select class="status_input">
					<option></option>
					<#list PM_Status.getOptionsMap() as key, value> 
						<option>${value}</option>
					</#list>
				</select> 
			  </#if>                
            </div>
          </div>
        	</div>
        </div>														
      </ul>
      	
    </div>
	<#if editMode>
		<input id="nextButton" class="lfr-ddm-form-pagination-next btn btn-primary" type="submit" value="Update">
	<#else>
		<input id="nextButton" class="lfr-ddm-form-pagination-next btn btn-primary" type="submit" value="Submit">
	</#if>
	<a id="cancelButton" href="javascript:history.back()">Cancel</a>
    </form>
  </div>

</div>

<style type="text/css">
  select[disabled] { background-color: rgba(0,0,0,0.2) !important; }

  .required:after {
    content:" *";
    color: red;
  }

  ul{
    padding-left: 0px;
  }

  select.multiple{
    height: 70px!important;
  }

  a#cancelButton {
    margin-left: 15px !important;
    margin-top: 5px;
    display: inline-block;
  }
  
</style>

<script>

	function showConfirmation(){
		var html="";
		html += "<div id='message_box'>";
		html += "<img src='${ImageruteThankyou}'/>";
		html += "<p id='raised'>Your Application has been created properly.</p>";
		html += "<a id='back-service' href='/group/guest/my-applications'>Back to My Applications</a>";
		html += "</div>";
		$("#form-box").html(html);
	}
	function showReasoningForFailuire(){
		var html="";
		html += "<div id='message_box'>";
		html += "<p id='raised'>The application was not created, please try again or contact Administrator.</p>";
		html += "<a id='back-service' href='/group/guest/my-applications'>Back to My Applications</a>";
		html += "</div>";
		$("#form-box").html(html);
	}
	function showEditConfirmation(){
		var html="";
		html += "<div id='message_box'>";
		html += "<img src='${ImageruteThankyou}'/>";
		html += "<p id='raised'>Your Application has been edited properly.</p>";
		html += "<a id='back-service' href='/group/guest/my-applications'>Back to My Applications</a>";
		html += "</div>";
		$("#form-box").html(html);
	}
	function showEditReasoningForFailuire(){
		var html="";
		html += "<div id='message_box'>";
		html += "<p id='raised'>The application was not edited, please try again or contact Administrator.</p>";
		html += "<a id='back-service' href='/group/guest/my-applications'>Back to My Applications</a>";
		html += "</div>";
		$("#form-box").html(html);
	}

	function validateForm() {
	
		var editMode = $("#checkEditMode").val();
	
        var v_title = "APP" + ${appId};
        var v_PM_CompanyName = $(".company_name_input").val();
        var v_IA_CompanyRegistrationNumber = $(".company_registration_input").val();
        var v_PM_RegisteredAddress = $(".registered_address_input").val();
        var v_PM_ReasonForSubmission = $(".submission_reason_input").val();
        var v_PM_AuthorisingOfficerFullName = $(".auth_officer_fullname_input").val();
        var v_PM_AuthorisingOfficer1Role = $(".auth_officer_role_input").val();
        var v_PM_AuthorisingOfficer1EmailAddress = $(".auth_officer_email_input").val();
        var v_PM_AuthorisingOfficer1BusinessAddress = $(".auth_officer_email_req_input").val();
        var v_PM_AuthorisingOfficer1TelephoneNumber = $(".auth_officer_phone_input").val();
        var v_PM_AnyadditionalAuthorisingOfficer = $(".additional_auth_input").val();
        var v_PM_KeyContact1Fullname = $(".key_contact_fullname_input").val();
        var v_PM_KeyContact1Role = $(".key_contact_role_input").val();
        var v_PM_KeyContact1EmailAddress = $(".key_contact_email_input").val();
        var v_PM_KeyContact1BusinessAddress = $(".key_contact_buisiness_email_input").val();
        var v_PM_KeyContact1TelephoneNumber = $(".key_contact_phone_input").val();
        var v_PM_Anyadditionalkeycontacts = $(".additional_key_input").val();
        var v_PM_Whatroleareyouapplyingfor = $(".role_applying_for_input").val().join(";spt;");
        var v_PM_Whatsystemsareyouapplyingtoaccess = $(".system_applying_for_input").val().join(";spt;");
        var v_PM_ProvideDetailsOrganisation = $(".provide_details_input").val();
        var v_PM_Status = $(".status_input").val();
        
        console.log(v_PM_Whatroleareyouapplyingfor);
		
		if(editMode == "true"){
			Liferay.Service(
				'/cproposal.recformarticle/update_application',
				{
					appResourcePK: ${appResourcePK?number},
					PM_CompanyName: v_PM_CompanyName,
					IA_CompanyRegistrationNumber: v_IA_CompanyRegistrationNumber,
					PM_RegisteredAddress: v_PM_RegisteredAddress,
					PM_ReasonForSubmission: v_PM_ReasonForSubmission,
					PM_AuthorisingOfficerFullName: v_PM_AuthorisingOfficerFullName,
					PM_AuthorisingOfficer1Role: v_PM_AuthorisingOfficer1Role,
					PM_AuthorisingOfficer1EmailAddress: v_PM_AuthorisingOfficer1EmailAddress,
					PM_AuthorisingOfficer1BusinessAddress: v_PM_AuthorisingOfficer1BusinessAddress,
					PM_AuthorisingOfficer1TelephoneNumber: v_PM_AuthorisingOfficer1TelephoneNumber,
					PM_AnyadditionalAuthorisingOfficer: v_PM_AnyadditionalAuthorisingOfficer,
					PM_KeyContact1Fullname: v_PM_KeyContact1Fullname,
					PM_KeyContact1Role: v_PM_KeyContact1Role,
					PM_KeyContact1EmailAddress: v_PM_KeyContact1EmailAddress,
					PM_KeyContact1BusinessAddress: v_PM_KeyContact1BusinessAddress,
					PM_KeyContact1TelephoneNumber: v_PM_KeyContact1TelephoneNumber,
					PM_Anyadditionalkeycontacts: v_PM_Anyadditionalkeycontacts,
					PM_Whatroleareyouapplyingfor: v_PM_Whatroleareyouapplyingfor,
					PM_Whatsystemsareyouapplyingtoaccess: v_PM_Whatsystemsareyouapplyingtoaccess,
					PM_ProvideDetailsOrganisation: v_PM_ProvideDetailsOrganisation,
					PM_Status: v_PM_Status
				},
				function(obj) {
					console.log(obj);
					if (obj.code == 200) {
						showEditConfirmation();
					}else  if (obj.code == 500 || obj.code == undefined) {
						showEditReasoningForFailuire();
					}
				}
			);
		} else if(editMode == "false"){
			Liferay.Service(
				'/cproposal.recformarticle/create_application',
				{
					title: v_title,
					PM_CompanyName: v_PM_CompanyName,
					IA_CompanyRegistrationNumber: v_IA_CompanyRegistrationNumber,
					PM_RegisteredAddress: v_PM_RegisteredAddress,
					PM_ReasonForSubmission: v_PM_ReasonForSubmission,
					PM_AuthorisingOfficerFullName: v_PM_AuthorisingOfficerFullName,
					PM_AuthorisingOfficer1Role: v_PM_AuthorisingOfficer1Role,
					PM_AuthorisingOfficer1EmailAddress: v_PM_AuthorisingOfficer1EmailAddress,
					PM_AuthorisingOfficer1BusinessAddress: v_PM_AuthorisingOfficer1BusinessAddress,
					PM_AuthorisingOfficer1TelephoneNumber: v_PM_AuthorisingOfficer1TelephoneNumber,
					PM_AnyadditionalAuthorisingOfficer: v_PM_AnyadditionalAuthorisingOfficer,
					PM_KeyContact1Fullname: v_PM_KeyContact1Fullname,
					PM_KeyContact1Role: v_PM_KeyContact1Role,
					PM_KeyContact1EmailAddress: v_PM_KeyContact1EmailAddress,
					PM_KeyContact1BusinessAddress: v_PM_KeyContact1BusinessAddress,
					PM_KeyContact1TelephoneNumber: v_PM_KeyContact1TelephoneNumber,
					PM_Anyadditionalkeycontacts: v_PM_Anyadditionalkeycontacts,
					PM_Whatroleareyouapplyingfor: v_PM_Whatroleareyouapplyingfor,
					PM_Whatsystemsareyouapplyingtoaccess: v_PM_Whatsystemsareyouapplyingtoaccess,
					PM_ProvideDetailsOrganisation: v_PM_ProvideDetailsOrganisation,
					PM_Status: v_PM_Status
				},
				function(obj) {
					console.log(obj);
					if (obj.code == 200) {
						showConfirmation();
					}else  if (obj.code == 500 || obj.code == undefined) {
						showReasoningForFailuire();
					}
				}
			);
		}
    
	}
</script>
