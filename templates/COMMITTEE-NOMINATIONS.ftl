<#assign recordSetservice = serviceLocator.findService("com.liferay.dynamic.data.lists.service.DDLRecordSetLocalService") />
<#assign groupId= themeDisplay.getLayout().getGroupId() />  
<#assign userService = utilLocator.findUtil("com.liferay.portal.kernel.service.UserLocalService") />
<#assign companyId= themeDisplay.getCompanyId() />	

<#assign list = recordSetservice.getRecordSets(groupId) />

<#assign arrow = themeDisplay.getPathThemeImages()+"/forms/arrow-down.svg" />	
<#assign Imagerute = themeDisplay.getPathThemeImages()+"/clay/icons.svg#angle-right" />						
<#assign images_folder = themeDisplay.getPathThemeImages() />

<#assign id = list[0].getRecordSetId() />

<div id="nominations" >
 <div id="header-consult">
        <div class="left">
            <p>Closing Date For Nominations: <span>15/06/2021</span></p>
        </div>
            <div class="right">
                <input id="AddEntry" class="right" type="button" value="Nominate a Candidate" onclick="addEntryDDL()" data="${id}"  name="AddMilestoneEntry"/>
            </div>
    </div>

<@liferay_portlet["runtime"]
                    instanceId="nominations"
                    portletName="com_liferay_dynamic_data_lists_web_portlet_DDLDisplayPortlet"
        />        

	<#--
				<#attempt>			
				  <#assign changeProposer = userService.getUserByEmailAddress(companyId, 'test@liferay.com') />			
				  <#assign userPhones = changeProposer.getPhones()/>			
					<#if userPhones?has_content && userPhones??>		
						<#assign userPhone = userPhones[0]/>  	
						<#assign userPhoneNumber = userPhone.number/>  	
					<#else>		
						<#assign userPhoneNumber = 'No phone'/>	
					</#if>		
					<#assign userImgSrc = changeProposer.getPortraitURL(themeDisplay) />		
					<#assign userDescription = changeProposer.getComments()/>   		
					<#if userDescription?has_content && userDescription??>		
						<#assign userDescriptionReplace = userDescription?replace('\n','<br>') />	
					<#else>		
						<#assign userDescriptionReplace = '' />	
					</#if>   				
						<a class="userNameDetails" onclick="getUserDetailPopUp('${changeProposer.getFullName()}', '${changeProposer.getEmailAddress()}', '${userPhoneNumber}' , '${userDescriptionReplace}' , '${userImgSrc}')">${changeProposer.getFullName()}</a>			
				<#recover>			
				  			
				</#attempt>			-->




    <div id="pop-up1" class="popup1">							
        <div class="popupp">							
            <a class="close" onclick=closePopUp()>&times;</a>
            <div class="userInfo">
                <div class="userDetails">
                    <div class="userFullName"> userFullName </div>	
                </div>
            </div>	
            					
            <div class="userDescription"> userDescription </div>							
        </div> 							
    </div>

</div>


<script>
 function addEntryDDL(){
    var org = window.location.href.substr(46);
    org = org.replace("/nomination","");
        let nameOfFunction = this[event.target.name];
        let ddlRecordSetId = event.target.getAttribute('data');
        let pathname = window.location.pathname;
        let url = '/group/guest/~/control_panel/manage?p_p_id=com_liferay_dynamic_data_lists_web_portlet_DDLPortlet&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_mvcPath=%2Fedit_record.jsp&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_redirect=' + pathname + '&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_recordSetId=' + ddlRecordSetId + '&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_formDDMTemplateId=0&p_p_auth=tYkZwwfY';
        window.location.href = url;
    }
</script>
						
							
<!-- Script pop up user details  -->							
							
<script>							
  function getUserDetailPopUp(userDescription, fullname) {	
      
      if(fullname != "" && undefined != fullname)
      {					
        $("#pop-up1 .popupp .userFullName").html(fullname);		
      } else {	
          $("#pop-up1 .popupp .userFullName").html("The user dont have name on portal.");		
      }				
        //$("#pop-up1 .popupp .userInfo .userDetails  .userEmail").html(userEmail);							
        //$("#pop-up1 .popupp .userInfo .userDetails  .userPhone").html(userPhone);	
        if(userDescription != "" && undefined != userDescription)
        {
            $("#pop-up1 .popupp .userDescription").html(userDescription);	    
        }else {
            $("#pop-up1 .popupp .userDescription").html("The user don't have bio.");	
        }				
        //$("#pop-up1 .popupp  .userInfo .userImg").attr("src", userImg);							
		var popup_user = document.getElementById("pop-up1");					
        popup_user.style.setProperty("visibility", "visible","important");
        var search = document.getElementById("search-bar-hidden");				
        search.style.display = "none";										
  } 	
  </script>

<script>						
    function closePopUp(){						
        var popup_user = document.getElementById("pop-up1");		
        popup_user.style.setProperty("visibility", "hidden","important");				
        var search = document.getElementById("search-bar-hidden");				
        search.style.display = "block";					
    }							
</script>

<script>
    
    $(document).ready(function(){

        $("tbody tr td:nth-child(3) a").each(function(){
        var email = $(this).html();
        var bio, name, user = "";
        var item = $(this);
        $(this).addClass("userNameDetails");
        $(this).attr("href", "");
        Liferay.Service(
          '/user/get-user-by-email-address',
          {
            companyId: ${companyId},
            emailAddress: email
          },
          function(obj) {
            
            user = obj;
            if(user.comments != ""){
                bio = user.comments;
                item.attr("data-bio", bio);
            }
            console.log(user.firstName);
            console.log(user.lastName);
            if(user.firstName != "" && user.lastName != "" && typeof user.firstName !== 'undefined' && typeof user.lastName !== 'undefined'){
                name = user.firstName+" "+user.lastName;
                item.attr("data-name", name);
            }
          }
        );
    });

        blockedWords = ["Denied","Pending"];

    $("strong.label.status.text-uppercase.workflow-value").filter(function() {
    
      // Save current element's innerText so we can use it within the reduce function
      var str = $(this).text();
    
      // Return sum of reduce function
      return blockedWords.reduce(function(s, v) {
    
        // For each item in blockedWords array, check whether it exists in the string. Add to total number of matches.
        return s + !!~str.indexOf(v);
    
      }, 0); // 0 = intial value of reduce function (number of matches)
    
    }).parents("tr").hide(); // Hide elements which pass through the filter function 

        var popup_user = document.getElementById("pop-up1");		
        popup_user.style.setProperty("visibility", "hidden","important");				
        var search = document.getElementById("search-bar-hidden");				
        search.style.display = "block";						
							
      $(".userNameDetails").click(function (e) {							
         var bio = $(this).attr("data-bio");
         var name = $(this).attr("data-name");
         getUserDetailPopUp(bio, name);	
         e.preventDefault();					
      }); 							
  							
    
        
    });
    
</script>

<style>

.userFullName {
    margin-left: 20%;
}

.userDetails {
    float: none !important;
}

#pop-up1 {
    visibility: visible !important;
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

div.col-md-3:nth-child(2){
    margin-top: 10px;
}

p span {
    color: red;
}

p {
    border: 1px solid blue;
    padding: 10px;
}

#rec-theme #view_records_id .container-fluid {
    max-width: 100%;
}

.lfr-nominator-full-name-column{
    display: none;
}



#rec-theme #view_records_id .container-fluid {
    max-width: 100%;
}

</style>