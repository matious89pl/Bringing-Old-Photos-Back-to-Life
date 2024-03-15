<div id="raise-ticket">
<h1 id="main-title">Make an application</h1>

<div id="form-box">
<form action="" onsubmit="return validateForm()" method="post">	
  <div class="row">							
    <div class="col-md-12">							
      <h3>Please detail your company information</h3>    						
      <ul>							
        <div class="wrapper">
          <div class="row">
		        <div class="col-md-6">
              <label class="required">Company name</label>
              <input class="company_name_input" type="text" value="" required oninvalid="this.setCustomValidity('This field is required')" oninput="this.setCustomValidity('')"/>                  
            </div>
		        <div class="col-md-6">
              <label class="required">Company Registration Number</label>
              <input class="company_registration_input" type="text" value="" required oninvalid="this.setCustomValidity('This field is required')" oninput="this.setCustomValidity('')"/>                    
            </div>
          </div>
          <div class="row">
		        <div class="col-md-6">
              <label class="required">Registered Address</label>
              <input class="registered_address_input" type="text" value="" required oninvalid="this.setCustomValidity('This field is required')" oninput="this.setCustomValidity('')"/>                   
            </div>
		        <div class="col-md-6">
              <label>Reason for submission</label>
              <input class="submission_reason_input" type="text" value="" />                  
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
              <input class="auth_officer_fullname_input" type="text" value="" required oninvalid="this.setCustomValidity('This field is required')" oninput="this.setCustomValidity('')"/>                   
            </div>
		        <div class="col-md-6">
              <label>Authorising Officer 1 Role</label>
              <input class="auth_officer_role_input" type="text" value="" />                  
            </div>
          </div>
          <div class="row">
		        <div class="col-md-6">
              <label class="required">Authorising Officer 1 Email address</label>
              <input class="auth_officer_email_input" type="text" value="" required oninvalid="this.setCustomValidity('This field is required')" oninput="this.setCustomValidity('')"/>                   
            </div>
		        <div class="col-md-6">
              <label class="required">Authorising Officer 1 Business addressRequired</label>
              <input class="auth_officer_email_req_input" type="text" value="" required oninvalid="this.setCustomValidity('This field is required')" oninput="this.setCustomValidity('')"/>                  
            </div>
          </div>
          <div class="row">
            <div class="col-md-6">
              <label>Authorising Officer 1 Telephone Number</label>
              <input class="auth_officer_phone_input" type="text" value="" />                  
            </div>
		        <div class="col-md-6">
              <label>Any additional Authorising Officer?</label>
              <select class="additional_auth_input">
                <option>aaaaaaa</option>
                <option>bbbbbbb</option>
                <option>ccccccc</option>
              </select>                 
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
              <input class="key_contact_fullname_input" type="text" value="" required oninvalid="this.setCustomValidity('This field is required')" oninput="this.setCustomValidity('')"/>                   
            </div>
		        <div class="col-md-6">
              <label>Key Contact 1 Role</label>
              <input class="key_contact_role_input" type="text" value="" />                  
            </div>
          </div>
          <div class="row">
		        <div class="col-md-6">
              <label class="required">Key Contact 1 Email address</label>
              <input class="key_contact_email_input" type="text" value="" required oninvalid="this.setCustomValidity('This field is required')" oninput="this.setCustomValidity('')"/>                
            </div>
		        <div class="col-md-6">
              <label class="required">Key Contact 1 Business address</label>
              <input class="key_contact_buisiness_email_input" type="text" value="" required oninvalid="this.setCustomValidity('This field is required')" oninput="this.setCustomValidity('')"/>                  
            </div>
          </div>
          <div class="row">
            <div class="col-md-6">
              <label>Key Contact 1 Telephone Number</label>
              <input class="key_contact_phone_input" type="text" value="" />                 
            </div>
		        <div class="col-md-6">
              <label>Any additional key contacts?</label>
              <select class="additional_key_input">
                <option>aaaaaaa</option>
                <option>bbbbbbb</option>
                <option>ccccccc</option>
              </select>                 
            </div>
          </div>
          <div class="row">
            <div class="col-md-6">
              <label>What role are you applying for?</label>
              <select class="multiple role_applying_for_input" multiple>
                <option>aaaaaaa</option>
                <option>bbbbbbb</option>
                <option>ccccccc</option>
              </select>                 
            </div>
		        <div class="col-md-6">
              <label>What systems are you applying to access?</label>
              <select class="multiple system_applying_for_input" multiple>
                <option>aaaaaaa</option>
                <option>bbbbbbb</option>
                <option>ccccccc</option>
              </select>                
            </div>
          </div>
          <div class="row">
            <div class="col-md-12">
              <label>Please provide details around where you organisation is in terms of certifying against current recognised industry standards, agreements and obligations where applicable</label>
              <select class="provide_details_input">
                <option>aaaaaaa</option>
                <option>bbbbbbb</option>
                <option>ccccccc</option>
              </select>                 
            </div>
          </div>
          <div class="row">
		        <div class="col-md-12">
              <label>Status (to fill in by Change Management Team)</label>
              <select class="status_input">
                <option>aaaaaaa</option>
                <option>bbbbbbb</option>
                <option>ccccccc</option>
              </select>                 
            </div>
          </div>
        	</div>
        </div>														
      </ul>
      	
    </div>
    <input id="nextButton" class="lfr-ddm-form-pagination-next btn btn-primary" type="submit" value="Submit">
	<a id="cancelButton" href="/group/guest/my-applications">Cancel</a>
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
	function validateForm() {

        var v_title = "APP25445";
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
            }
        );
	}
</script>