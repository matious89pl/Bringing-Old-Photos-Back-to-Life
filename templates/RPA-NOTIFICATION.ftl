<#assign segmentsEntryService = serviceLocator.findService("com.liferay.segments.service.SegmentsEntryLocalService") />  

<#assign listSegments = segmentsEntryService.getSegmentsEntries(-1,-1) />

<#assign GroupLocalService = serviceLocator.findService("com.liferay.portal.kernel.service.GroupLocalService")>

<#assign siteName = themeDisplay.getSiteGroupName() />
<#assign siteName = siteName?replace(" ", "-") />
<#assign siteName = siteName?lower_case />

<#assign id = themeDisplay.getSiteGroupId() />
<#assign site = GroupLocalService.getGroup(id) />

<#assign rolService = utilLocator.findUtil("com.liferay.portal.kernel.service.RoleLocalService") />							
<#assign userId = themeDisplay.getUserId() />
<#assign userRoles = user.getRoles()/>

<#assign showButton = 0 />

<#list userRoles as rol>							
    <#if rol.getName() == "RPA" || rol.getName() == "Administrator" >							
        <#assign showButton = 1 />							
    </#if>							
</#list>

<#if showButton == 1 >

<h1>Notification</h1>

<div id="notificationRPA" class="RPANotification">

        <div class="RPA-content"> 
            <hr>
            <div class="row">
                <div class="col-md-12">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="content-label">Choose a target*</div>
                            <select class="content-placeholder RPASelect" name="segments" id="segmentsId">
                                        <option value="segments"></option>
                                <#if listSegments?has_content>
                                    <#list listSegments as segments>
                                        <#if segments.source == "DEFAULT">
                                        <option id="segmentName" value="segments" required>${segments.getNameCurrentValue()}</option>
                                        </#if>
                                    </#list>
                                </#if>
                            </select>
                        </div>
                        <div class="col-md-6">
                            <div class="content-label">Enter notification title*</div>
                            <div id="block_problem" class="content-placeholder">
                                <input class="NotificationInput" type="text" id="titleOfNotification" required>
                            </div>
                        </div>
                    </div>   
                </div>

                <div class="col-md-12">
                    <div class="content-label">Enter notification body*</div>
                    <div id="block_problem" class="content-placeholder">
                        <textArea class="NotificationInput" type="text" id="bodyOfNotification" required></textArea>
                    </div>
                </div>

                <div class="col-md-12">
                    <div class="content-label">URL</div>
                    <div id="block_problem" class="content-placeholder">
                        <input class="NotificationInput" id="titleOfLink"  type="text" placeholder="This is how your link will be displayed in the notification">
                    </div>
                </div>

                <div class="col-md-12">
                    <div class="content-label">Delivery Method*</div>
                    <div id="check-method" class="required">
                        <label for="webon">
                            <input type="checkbox" id="webon" name="methodsender" value="Web" checked>
                            Portal Notification</label>
                        <label for="mailon">
                            <input type="checkbox" id="mailon" name="methodsender" value="Email">
                            Email</label>
                    </div>
                </div>
                <div class="col-md-12">
                    <hr>
                    <button id="startNotification" class="BottonNotification lfr-ddm-form-pagination-next btn btn-primary">Send Notification</button>
                    
                    <#assign urlBack = "/group/"+siteName>
                    <a id="cancelButton" href="${urlBack}">Cancel</a>
                <div>
            <div>
        </div>
    </#if>

    <div id="notificationPopUp" class="popUpNotification">
        <div class="popUp-content">
            <a class="close" href="#">×</a>
            <h2>${site.name}</h2>
            <hr>
            <div class="content-label">Are you sure you want to send the notification?</div>
            <hr>
            <button id="sendNotification" class="BottonNotificationPoppup" onclick="sendRPANotification()">Yes</button>
            <button id="cancelNotification" class="BottonNotificationPoppup">No</button>
        </div>

    </div>

</div>

<script>

function sendRPANotification(){

    var ischecked = $('div#check-method.required :checkbox:checked').length;
    var notificationTitle = document.getElementById("titleOfNotification").value;
    var notificationBody  = document.getElementById("bodyOfNotification").value;
    var segmentName = $( "#segmentsId option:selected" ).text();

    var emailChecked = $("#mailon").is(':checked');
    var webChecked = $("#webon").is(':checked');

    if(emailChecked) {
        var email = "Email";
    } else {
        email = " ";
    }

    if(webChecked) {
        var web = "Web";
    } else {
        web = " ";
    }

    function replaceURLs(notificationBody) {
        if(!notificationBody) return;

        var urlRegex = /(((https?:\/\/)|(www\.))[^\s]+)/g;
        return notificationBody.replace(urlRegex, function (url) {
            var hyperlink = url;
            if (!hyperlink.match('^https?:\/\/')) {
                hyperlink = 'http://' + hyperlink;
            }

            if(document.getElementById("titleOfLink").value !="") {
                 url = document.getElementById("titleOfLink").value;
            } 

            var color = "color:#70ada3";
            return '<a style=' + color + ' href=' + hyperlink + ' rel="noopener noreferrer">' + url + '</a>'
        });
        }

    var getBody = document.getElementById("bodyOfNotification").value;

    notificationBody = replaceURLs(getBody);

    var modal = document.getElementById("notificationRPA");
        if (notificationTitle != "" & notificationBody != "" & segmentName !="" & ischecked > 0){
            
                Liferay.Service(
                    '/messages.messages/send_notification_46', 
                    {
                        notificationTitle: notificationTitle,
                        notificationBody: notificationBody,
                        segment: segmentName,
                        siteGroupId: ${groupId},
                        currentUserId: ${userId},
                        webEnabled: web,
                        emailEnabled: email
                    },

                    function(obj) {
                        console.log(obj);

                        let url = "/group/${siteName}";
                        window.location.href = url;
                    }
                );
        }
}

</script>

<style>

    #sendNotification{
        margin-right: 20px;
    }

    .NotificationInput{
        width: 100%;
        background: white;
        border: 0;
    }
    
    .RPASelect{
        border: 0;
        width: 100%;
        height: 37px;
    }

    a#cancelButton {
        margin-left: 15px !important;
        margin-top: 5px;
        display: inline-block;

        color: black;
        font-size: 20px;
        text-decoration: underline;
        letter-spacing: .75px;
        line-height: 30px;
        background: 0;
        border: 0;
        height: 48px;
        width: 98px;
        padding: 0;
    }
    
  	.popUpNotification {
      display: none; 
      position: fixed;
      z-index: 1; 
      padding-top: 300px; 
      left: 0;
      top: 0;
      width: 100%; 
      height: 100%; 
      overflow: auto;
      background-color: rgb(0,0,0); 
      background-color: rgba(0,0,0,0.4); 
    }

    #startNotification {
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
    
    #startNotification:hover {
        text-decoration: none;
    }

    span.btn.btn-link a {
        color: black;
    }

    .popUp-content {
      background-color: #fefefe;
      margin: auto;
      padding: 20px;
      border: 1px solid #888;
      width: 500px;
      height: 215px;
      border-radius: 4px;
    }

    .BottonNotificationPoppup{
        float: left !important;
        background-color: #70ada3 !important;
        border: 0;
        letter-spacing: .75px;
        line-height: 30px;
        font-weight: normal !important;
        font-size: 16px;
        height: 40px !important;
        padding: 5px 20px 5px 20px;
        border-radius: .25rem;
        color: white;
    }

    a.close{
        font-size: 25px;
        font-family: none !important;
    }

    #check-method {
        padding: 10px 10px 10px 0px;

    }

    label[for="webon"] {
        margin-right: 10px;
    }
    
    #check-method label {
        font-size: 18px;
    }

</style>

<script>

    var modal = document.getElementById("notificationPopUp");

    var btn = document.getElementById("startNotification");
    var btn2 = document.getElementById("cancelNotification");
    var span = document.getElementsByClassName("close")[0];

    btn.onclick = function() {
        var ischecked = $('div#check-method.required :checkbox:checked').length;
        var notificationTitle = document.getElementById("titleOfNotification").value;
        var notificationBody  = document.getElementById("bodyOfNotification").value;
        var segmentName = $( "#segmentsId option:selected" ).text();
            if (notificationTitle != "" & notificationBody != "" & segmentName !="" & ischecked > 0){
                modal.style.display = "block";
                }
        }

    span.onclick = function() {
        modal.style.display = "none"; 
    }
    
    btn2.onclick = function() {
        modal.style.display = "none"; 
    }

</script>
