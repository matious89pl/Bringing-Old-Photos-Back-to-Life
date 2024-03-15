<#assign ImageruteThankyou = themeDisplay.getPathThemeImages()+"/forms/check.svg" />
<#assign counterService = utilLocator.findUtil("com.liferay.counter.kernel.service.CounterLocalService") />
<#assign journalArticleService = serviceLocator.findService("com.liferay.journal.service.JournalArticleLocalService") />
<#assign actionLogId = counterService.increment("com.liferay.journal.model.JournalArticle") />
<#assign editMode = false />
<#assign actionLogResourcePK = 0 />

<#assign siteName = themeDisplay.getSiteGroupName() />
<#assign siteName = siteName?replace(" ", "-") />
<#assign siteName = siteName?lower_case />

<#assign ja_title = "" />
<#assign ja_ActionLog_ID = "" />
<#assign ja_ActionLog_Description = "" />
<#assign ja_ActionLog_AssigneeEmailAddress = "" />
<#assign ja_ActionLog_DueDate = "" />
<#assign ja_ActionLog_NextUpdateDue = "" />
<#assign ja_ActionLog_Status = "" />
<#assign ja_ActionLog_CompletionDate = "" />

<#if request.getParameter("mode")?? && request.getParameter("mode") == "EDIT" && request.getParameter("actionLogId")??>
    <#assign mode = request.getParameter("mode") />
    <#assign actionLogResourcePK = request.getParameter("actionLogId") />
    <#attempt>
        <#assign actionJournal = journalArticleService.fetchLatestArticle(actionLogResourcePK?number) />  
        <#assign ja_title = actionJournal.getTitle() /> 
        <#assign document = saxReaderUtil.read(actionJournal.getContent())/>    
        <#assign ja_ActionLog_ID = document.valueOf("//dynamic-element[@name='ActionLog_ID']/dynamic-content/text()") />
        <#assign ja_ActionLog_Description = document.valueOf("//dynamic-element[@name='ActionLog_Description']/dynamic-content/text()") />
        <#assign ja_ActionLog_AssigneeEmailAddress = document.valueOf("//dynamic-element[@name='ActionLog_AssigneeEmail']/dynamic-content/text()") />
        <#assign ja_ActionLog_DueDate = document.valueOf("//dynamic-element[@name='ActionLog_DueDate']/dynamic-content/text()") />
        <#assign ja_ActionLog_NextUpdateDue = document.valueOf("//dynamic-element[@name='ActionLog_NextUpdateDue']/dynamic-content/text()") />
        <#assign ja_ActionLog_Status = document.valueOf("//dynamic-element[@name='ActionLog_Status']/dynamic-content/text()") />
        <#assign ja_ActionLog_CompletionDate = document.valueOf("//dynamic-element[@name='ActionLog_CompletionDate']/dynamic-content/text()") />
        <#assign editMode = true />
    <#recover>
        FAIL
    </#attempt>    
</#if>
<div id="raise-ticket" class="actionLogForm">
<h1 id="main-title">Make an Action Log</h1>
<div id="form-box">
    <input id="checkEditMode" type="hidden" value="${editMode?string}" />
    <form action="" onsubmit="validateForm();return false" method="post">   
        <div class="row">                           
            <div class="col-md-12">                         
                <ul>                            
                    <div class="wrapper">
                        <div class="row">
                            <div class="col-md-6">
                                <label class="required">Title</label>
                                <input class="ja_title" type="text" value="${ja_title}" required oninvalid="this.setCustomValidity('This field is required')" oninput="this.setCustomValidity('')"/>                  
                            </div>
                            <div class="col-md-6">
                                <label class="required">ID</label>
                                <input class="ja_ActionLog_ID" type="text" value="${ja_ActionLog_ID}" required oninvalid="this.setCustomValidity('This field is required')" oninput="this.setCustomValidity('')"/>                    
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <label class="required">Description</label>
                                <input class="ja_ActionLog_Description" type="text" value="${ja_ActionLog_Description}" required oninvalid="this.setCustomValidity('This field is required')" oninput="this.setCustomValidity('')"/>                   
                            </div>  
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <label class="required">Assignee Email Address</label>
                                <input class="ja_ActionLog_AssigneeEmailAddress" type="text" value="${ja_ActionLog_AssigneeEmailAddress}" required oninvalid="this.setCustomValidity('This field is required')" oninput="this.setCustomValidity('')"/>                   
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-4">
                                <label>Due Date</label>
                                <input class="ja_ActionLog_DueDate" type="date" value="${ja_ActionLog_DueDate}" />                  
                            </div>
                            <div class="col-md-4">
                                <label>Next Update Due</label>
                                <input class="ja_ActionLog_NextUpdateDue" type="date" value="${ja_ActionLog_NextUpdateDue}" />                
                            </div>
                            <div class="col-md-4">
                                <label>Completion Date</label>
                                <input class="ja_ActionLog_CompletionDate" type="date" value="${ja_ActionLog_CompletionDate}" />                
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <label>Status</label>
                                <select class="ja_ActionLog_Status" name="status" required oninvalid="this.setCustomValidity('This field is required')" oninput="this.setCustomValidity('')">
                                    <option>Open</option>
                                    <option>Completed</option>
                                    <option>Withdrawn</option>
                                </select>                  
                            </div>
                        </div>
                    </div>                                                      
                </ul>
            </div>   
        </div>
        <#if editMode>
            <input id="nextButton" class="lfr-ddm-form-pagination-next btn btn-primary" type="submit" value="Update">
        <#else>
            <input id="nextButton" class="lfr-ddm-form-pagination-next btn btn-primary" type="submit" value="Submit">
        </#if>
        <#assign urlBack = "/group/"+siteName+"/action-log" />
        <a id="cancelButton" href="${urlBack}">Cancel</a>
    </form>
</div>
<script>
    function showConfirmation(){
        var siteName = "${siteName}";
        var html="";
        html += "<div id='message_box'>";
        html += "<img src='${ImageruteThankyou}'/>";
        html += "<p id='raised'>Action Log has been created properly.</p>";
        // TO CHANGE THE URL
        html += "<a id='back-service' href='/group/"+siteName+"/action-log'>Back to Action Logs</a>";
        html += "</div>";
        $("#form-box").html(html);
    }
    function showReasoningForFailuire(){
        var siteName = "${siteName}";
        var html="";
        html += "<div id='message_box'>";
        html += "<p id='raised'>The action log was not created, please try again or contact Administrator.</p>";
        // TO CHANGE THE URL
        html += "<a id='back-service' href='/group/"+siteName+"/action-log'>Back to Action Logs</a>";
        html += "</div>";
        $("#form-box").html(html);
    }
    function showEditConfirmation(){
        var siteName = "${siteName}";
        var html="";
        html += "<div id='message_box'>";
        html += "<img src='${ImageruteThankyou}'/>";
        html += "<p id='raised'>Action Log has been edited properly.</p>";
        // TO CHANGE THE URL
        html += "<a id='back-service' href='/group/"+siteName+"/action-log'>Back to Action Logs</a>";
        html += "</div>";
        $("#form-box").html(html);
    }
    function showEditReasoningForFailuire(){
        var siteName = "${siteName}";
        var html="";
        html += "<div id='message_box'>";
        html += "<p id='raised'>The action log was not edited, please try again or contact Administrator.</p>";
        // TO CHANGE THE URL
        html += "<a id='back-service' href='/group/"+siteName+"/action-log'>Back to Action Logs</a>";
        html += "</div>";
        $("#form-box").html(html);
    }
    function validateForm() {
	
		var editMode = $("#checkEditMode").val();
	
        //var v_ja_ActionLog_ID = "Action" + ${actionLogId};
        var v_ja_ActionLog_ID = $(".ja_ActionLog_ID").val();
        var v_ja_title = $(".ja_title").val();
        var v_ja_ActionLog_Description = $(".ja_ActionLog_Description").val();
        var v_ja_ActionLog_AssignerEmailAddress = $(".ja_ActionLog_AssignerEmailAddress").val();
        var v_ja_ActionLog_AssigneeEmailAddress = $(".ja_ActionLog_AssigneeEmailAddress").val();
        var v_ja_ActionLog_DueDate = $(".ja_ActionLog_DueDate").val();
        var v_ja_ActionLog_NextUpdateDue = $(".ja_ActionLog_NextUpdateDue").val();
        var v_ja_ActionLog_Status = $(".ja_ActionLog_Status").val();
        var v_ja_ActionLog_CompletionDate = $(".ja_ActionLog_CompletionDate").val();
		
		console.log(v_ja_title);
		
		if(editMode == "true"){
			Liferay.Service(
				'/cproposal.recformarticle/update_committee_action_log',
				{
					actionLogResourcePK: ${actionLogResourcePK?number},
					AL_ID: v_ja_ActionLog_ID,
					title: v_ja_title,
					AL_Description: v_ja_ActionLog_Description,
					AL_Assigner: v_ja_ActionLog_AssignerEmailAddress,
					AL_Assignee: v_ja_ActionLog_AssigneeEmailAddress,
					AL_DueDate: v_ja_ActionLog_DueDate,
					AL_NextUpdateDue: v_ja_ActionLog_NextUpdateDue,
					AL_Status: v_ja_ActionLog_Status,
					AL_CompletionDate: v_ja_ActionLog_CompletionDate
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
				'/cproposal.recformarticle/create_committee_action_log',
				{
					AL_ID: v_ja_ActionLog_ID,
					title: v_ja_title,
					AL_Description: v_ja_ActionLog_Description,
					AL_Assigner: v_ja_ActionLog_AssignerEmailAddress,
					AL_Assignee: v_ja_ActionLog_AssigneeEmailAddress,
					AL_DueDate: v_ja_ActionLog_DueDate,
					AL_NextUpdateDue: v_ja_ActionLog_NextUpdateDue,
					AL_Status: v_ja_ActionLog_Status,
					AL_CompletionDate: v_ja_ActionLog_CompletionDate,
                    groupId: ${groupId}
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