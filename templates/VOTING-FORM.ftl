<#assign ImageruteThankyou = themeDisplay.getPathThemeImages()+"/forms/check.svg" />
<#assign counterService = utilLocator.findUtil("com.liferay.counter.kernel.service.CounterLocalService") />
<#assign journalArticleService = serviceLocator.findService("com.liferay.journal.service.JournalArticleLocalService") />
<#assign votingId = counterService.increment("com.liferay.journal.model.JournalArticle") />
<#assign editMode = false />
<#assign isEditPage = 0>
<#assign votingResourcePK = 0 />

<#assign siteName = themeDisplay.getSiteGroupName() />
<#assign siteName = siteName?replace(" ", "-") />
<#assign siteName = siteName?lower_case />

<#assign ja_title = "" />
<#assign ja_voting_description= "" />
<#assign ja_voting_clossingProcess = "" />
<#assign ja_voting_status = "" />
<#assign ja_voting_reportVisibility = "" />
<#assign ja_voting_votingVisibility = "" />
<#assign ja_voting_decidingVote = "" />
<#assign ja_voting_externalVote = "" />

 <#assign nodesSize = 0 />

<#if request.getParameter("mode")?? && request.getParameter("mode") == "EDIT" && request.getParameter("votingId")??>
    <#assign isEditPage = 1 />
	<#assign mode = request.getParameter("mode") />
	<#assign votingResourcePK = request.getParameter("votingId") />
	<#attempt>
        <#assign votingJournal = journalArticleService.fetchLatestArticle(votingResourcePK?number) />  
        <#-- <#assign ja_title = votingJournal.getTitle() /> -->
        <#assign document = saxReaderUtil.read(votingJournal.getContent())/>
        <#assign ja_title = document.valueOf("//dynamic-element[@name='TitleVoting']/dynamic-content/text()") />	
        <#assign ja_voting_description = document.valueOf("//dynamic-element[@name='DescriptionVoting']/dynamic-content/text()") />
        <#assign ja_voting_clossingProcess = document.valueOf("//dynamic-element[@name='DateVoting']/dynamic-content/text()") />
        <#assign ja_voting_status = document.valueOf("//dynamic-element[@name='StatusVoting']/dynamic-content/text()") />
        <#assign ja_voting_reportVisibility = document.valueOf("//dynamic-element[@name='ReportVisibilityVoting']/dynamic-content/text()") />
        <#assign ja_voting_votingVisibility = document.valueOf("//dynamic-element[@name='VotingVisibility']/dynamic-content/text()") />
        <#assign ja_voting_decidingVote = document.valueOf("//dynamic-element[@name='DecidingVoting']/dynamic-content/text()") />
        <#-- <#assign ja_voting_externalVote = document.valueOf("//dynamic-element[@name='ExternalVoting']/dynamic-content/text()") /> -->
        <#assign selectNodes = document.selectNodes("//root//dynamic-element[@name='ExternalVoting']//dynamic-content") />
        <#assign nodesSize = selectNodes?size />
        <#assign editMode = true />
	<#recover>
        FAIL 
	</#attempt>    
</#if>


<div id="raise-ticket" class="votingForm">
<#if isEditPage == 1>
    <h1 id="main-title">Edit a Voting Process</h1>
<#else>
    <h1 id="main-title">Make a Voting Process</h1>
</#if>
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
                                <label class="required">Description</label>
                                <input class="ja_voting_description" type="text" value="${ja_voting_description}" required oninvalid="this.setCustomValidity('This field is required')" oninput="this.setCustomValidity('')"/>                    
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <label>Status</label>
                                <select class="ja_voting_status" name="statusSelector">
                                    <option></option>
                                    <#if ja_voting_status == "In progress">
                                        <option selected>In progress</option>
                                    <#else>
                                        <option>In progress</option>
                                    </#if>
                                    <#if ja_voting_status == "Completed">
                                        <option selected>Completed</option>
                                    <#else>
                                        <option>Completed</option>
                                    </#if>
                                </select>                  
                            </div>
                            <div class="col-md-6">
                                <label class="required">Clossing Process Date</label>
                                <input class="ja_voting_clossingProcess" type="date" value="${ja_voting_clossingProcess}" required oninvalid="this.setCustomValidity('This field is required')" oninput="this.setCustomValidity('')"/>                   
                            </div>	
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <label>Report Visibility</label>
                                <select class="ja_voting_reportVisibility" name="reportVisibility">
                                    <option></option>
                                    <#if ja_voting_reportVisibility == "Yes">
                                        <option selected>Yes</option>
                                    <#else>
                                        <option>Yes</option>
                                    </#if>
                                    <#if ja_voting_reportVisibility == "No">
                                        <option selected>No</option>
                                    <#else>
                                        <option>No</option>
                                    </#if>
                                </select> 
                            </div>
                            <div class="col-md-6">
                                <label>Voting Visibility</label>
                                <select class="ja_voting_votingVisibility" name="votingVisibility">
                                    <option></option>
                                    <#if ja_voting_votingVisibility == "Yes">
                                        <option selected>Yes</option>
                                    <#else>
                                        <option>Yes</option>
                                    </#if>
                                    <#if ja_voting_votingVisibility == "No">
                                        <option selected>No</option>
                                    <#else>
                                        <option>No</option>
                                    </#if>
                                </select>
                            </div>
                        </div>
                        <#if isEditPage == 1>
                            <div class="row">
                                <div class="col-md-6">
                                    <label>Deciding Vote</label>
                                    <input class="ja_voting_decidingVote" type="text" value="${ja_voting_decidingVote}" />                  
                                </div>
                            </div>
                        </#if>
                    </div>
                </ul>
            </div>
            <#if isEditPage == 1>
                <div class="col-md-12">							
                    <ul>							
                        <div class="wrapper">
                            <h3>External Vote(s)</h3>
                            <div class="row">
                                <#if nodesSize != 0>
                                    <#list selectNodes as node> 
                                        <div class='col-md-6'>
                                            <label>External Vote </label> 
                                            <input class='ja_voting_externalVote' type='text' value='${node.getText()}'/>
                                        </div>
                                    </#list>
                                </#if>
                            </div>
                        <div id="moreExternalVotes"></div>
                        <a id="addExternalVote">Add external vote</a>
                        </div>
                    </ul>
                </div>
            </#if>      
        </div>

        <#if editMode>
            <input id="nextButton" class="lfr-ddm-form-pagination-next btn btn-primary" type="submit" value="Update">
        <#else>
            <input id="nextButton" class="lfr-ddm-form-pagination-next btn btn-primary" type="submit" value="Submit">
        </#if>
        <#assign urlBack = "/group/"+siteName+"/voting" />
        <a id="cancelButton" href="${urlBack}">Cancel</a>
    </form>
</div>


<script>

    $('#addExternalVote').click(function(e) {  							
        console.log("addExternalVote");
        html="<div class='row'><div class='col-md-6'><label>External Vote </label> <input class='ja_voting_externalVote' type='text' value='${ja_voting_externalVote}'/></div></div>";				
        $("#moreExternalVotes").append(html);
    });

	function showConfirmation(){
        var siteName = "${siteName}";
		var html="";
		html += "<div id='message_box'>";
		html += "<img src='${ImageruteThankyou}'/>";
		html += "<p id='raised'>Voting Process has been created properly.</p>";
        // TO CHANGE THE URL
		html += "<a id='back-service' href='/group/"+siteName+"/voting'>Back to Votings</a>";
		html += "</div>";
		$("#form-box").html(html);
	}
	function showReasoningForFailuire(){
        var siteName = "${siteName}";
		var html="";
		html += "<div id='message_box'>";
		html += "<p id='raised'>The voting process was not created, please try again or contact Administrator.</p>";
        // TO CHANGE THE URL
		html += "<a id='back-service' href='/group/"+siteName+"/voting'>Back to Votings</a>";
		html += "</div>";
		$("#form-box").html(html);
	}
	function showEditConfirmation(){
        var siteName = "${siteName}";
		var html="";
		html += "<div id='message_box'>";
		html += "<img src='${ImageruteThankyou}'/>";
		html += "<p id='raised'>Voting Process has been edited properly.</p>";
        // TO CHANGE THE URL
		html += "<a id='back-service' href='/group/"+siteName+"/voting'>Back to Votings</a>";
		html += "</div>";
		$("#form-box").html(html);
	}
	function showEditReasoningForFailuire(){
        var siteName = "${siteName}";
		var html="";
		html += "<div id='message_box'>";
		html += "<p id='raised'>The voting process was not edited, please try again or contact Administrator.</p>";
        // TO CHANGE THE URL
		html += "<a id='back-service' href='/group/"+siteName+"/voting'>Back to Votings</a>";
		html += "</div>";
		$("#form-box").html(html);
	}

	function validateForm() {
	
		var editMode = $("#checkEditMode").val();
	
        var v_ja_title = $(".ja_title").val();
        var v_ja_voting_description = $(".ja_voting_description").val();
        var v_ja_voting_clossingProcess = $(".ja_voting_clossingProcess").val();
        var v_ja_voting_status = $(".ja_voting_status").val();
        var v_ja_voting_reportVisibility = $(".ja_voting_reportVisibility").val();
        var v_ja_voting_votingVisibility = $(".ja_voting_votingVisibility").val();
        var v_ja_voting_decidingVote = $(".ja_voting_decidingVote").val();
        //var v_ja_voting_externalVote = $(".ja_voting_externalVote").val();
        //var v_ja_voting_externalVote_list = document.getElementsByClassName("ja_voting_externalVote");
        //console.log(v_ja_voting_externalVote_list[0].value, v_ja_voting_externalVote_list[1].value);
        var v_ja_voting_externalVote_list = $("ja_voting_externalVote");
		
		console.log(v_ja_title);

        var externalVotes = [];
		
		if(editMode == "true"){
            var v_ja_voting_externalVote_list = document.getElementsByClassName("ja_voting_externalVote");
            for(var i=0; i<v_ja_voting_externalVote_list.length; i++){
                var value = v_ja_voting_externalVote_list[i].value;
                externalVotes.push(value);
            }
            console.log("externalVotes: ", externalVotes);
			Liferay.Service(
				'/cproposal.recformarticle/update_committee_voting_process',
				{
					votingResourcePK: ${votingResourcePK?number},
					title: v_ja_title,
					Vote_Description: v_ja_voting_description,
					Vote_ClossingProcess: v_ja_voting_clossingProcess,
					Vote_Status: v_ja_voting_status,
					Vote_ReportVisibility: v_ja_voting_reportVisibility,
					Vote_VotingVisibility: v_ja_voting_votingVisibility,
					Vote_DecidingVote: v_ja_voting_decidingVote,
					Vote_ExternalVote: externalVotes
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
				'/cproposal.recformarticle/create_committee_voting_process',
				{
					title: v_ja_title,
					Vote_Description: v_ja_voting_description,
					Vote_ClossingProcess: v_ja_voting_clossingProcess,
					Vote_Status: v_ja_voting_status,
					Vote_ReportVisibility: v_ja_voting_reportVisibility,
					Vote_VotingVisibility: v_ja_voting_votingVisibility,
					Vote_DecidingVote: '',
					//Vote_ExternalVote: v_ja_voting_externalVote
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