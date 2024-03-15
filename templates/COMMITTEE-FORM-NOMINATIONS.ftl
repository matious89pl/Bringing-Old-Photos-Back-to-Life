<#assign ImageruteThankyou = themeDisplay.getPathThemeImages()+"/forms/check.svg" />
<#assign counterService = utilLocator.findUtil("com.liferay.counter.kernel.service.CounterLocalService") />
<#assign journalArticleService = serviceLocator.findService("com.liferay.journal.service.JournalArticleLocalService") />
<#assign nominationsId = counterService.increment("com.liferay.journal.model.JournalArticle") />
<#assign editMode = false />
<#assign isEditPage = 0>
<#assign nominationsResourcePK = 0 />

<#assign siteName = themeDisplay.getSiteGroupName() />
<#assign siteName = siteName?replace(" ", "-") />
<#assign siteName = siteName?lower_case />

<#assign ja_Nominations_TitleNomination = "" />
<#assign ja_Nominations_DescriptionNomination = "" />
<#assign ja_Nominations_ClosingDateNomination = "" />
<#assign ja_Nominations_StatusNomination = "" />
<#assign ja_Nominations_ReportVisibilityNomination = "" />
<#assign ja_Nominations_DecidingVoteNomination = "" />

<#if request.getParameter("mode")?? && request.getParameter("mode") == "EDIT" && request.getParameter("nominationsId")??>
    <#assign isEditPage = 1 />
	<#assign mode = request.getParameter("mode") />
	<#assign nominationsResourcePK = request.getParameter("nominationsId") />
	<#attempt>
        <#assign nominationsJournal = journalArticleService.fetchLatestArticle(nominationsResourcePK?number) />  	
        <#assign document = saxReaderUtil.read(nominationsJournal.getContent())/>

        <#assign ja_Nominations_TitleNomination = document.valueOf("//dynamic-element[@name='TitleNomination']/dynamic-content/text()") />
        <#assign ja_Nominations_DescriptionNomination = document.valueOf("//dynamic-element[@name='DescriptionNomination']/dynamic-content/text()") />

        <#assign ja_Nominations_ClosingDateNomination = document.valueOf("//dynamic-element[@name='ClosingDateNomination']/dynamic-content/text()") />
        <#assign ja_Nominations_StatusNomination = document.valueOf("//dynamic-element[@name='StatusNomination']/dynamic-content/text()") />

        <#assign ja_Nominations_ReportVisibilityNomination = document.valueOf("//dynamic-element[@name='ReportVisibilityNomination']/dynamic-content/text()") />
        <#assign ja_Nominations_DecidingVoteNomination = document.valueOf("//dynamic-element[@name='DecidingVoteNomination']/dynamic-content/text()") />
        
        <#assign editMode = true />
	<#recover>
        
	</#attempt>    
</#if>

<div id="raise-ticket">
<#if isEditPage == 1>
    <h1 id="main-title">Edit a Nomination</h1>
<#else>
    <h1 id="main-title">Make a Nomination</h1>
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
                                <input class="ja_Nominations_TitleNomination" type="text" value="${ja_Nominations_TitleNomination}" required oninvalid="this.setCustomValidity('This field is required')" oninput="this.setCustomValidity('')"/>                  
                            </div>
                            <div class="col-md-6">
                                <label>Description</label>
                                <input class="ja_Nominations_DescriptionNomination" type="text" value="${ja_Nominations_DescriptionNomination}" />                   
                            </div>                           
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <label>Nomination Closing Date</label>
                                <input class="ja_Nominations_ClosingDateNomination" type="date" value="${ja_Nominations_ClosingDateNomination}" />                    
                            </div>
                            <div class="col-md-6">
                                <label>Status</label>
                                <select class="ja_Nominations_StatusNomination" name="status" required oninvalid="this.setCustomValidity('This field is required')" oninput="this.setCustomValidity('')">
                                    <option></option>
                                    <#if ja_Nominations_StatusNomination == "In progress">
                                        <option selected>In progress</option>
                                    <#else>
                                        <option>In progress</option>
                                    </#if>
                                    <#if ja_Nominations_StatusNomination == "Completed">
                                        <option selected>Completed</option>
                                    <#else>
                                        <option>Completed</option>
                                    </#if>
                                </select>                  
                            </div>	
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <label>Report Visibility</label>
                                <select class="ja_Nominations_ReportVisibilityNomination" name="ReportVisibilityNomination" required oninvalid="this.setCustomValidity('This field is required')" oninput="this.setCustomValidity('')">
                                    <option></option>
                                    <#if ja_Nominations_ReportVisibilityNomination == "Yes">
                                        <option selected>Yes</option>
                                    <#else>
                                        <option>Yes</option>
                                    </#if>
                                    <#if ja_Nominations_ReportVisibilityNomination == "No">
                                        <option selected>No</option>
                                    <#else>
                                        <option>No</option>
                                    </#if>
                                </select>                    
                            </div>
                            <div class="col-md-6">
                                <label>Deciding Vote</label>
                                <input class="ja_Nominations_DecidingVoteNomination" type="text" value="${ja_Nominations_DecidingVoteNomination}" />                    
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
        <#assign urlBack = "/group/"+siteName+"-1/pab" />
        <a id="cancelButton" href="${urlBack}">Cancel</a>
    </form>
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
        var siteName = "${siteName}";
		var html="";
		html += "<div id='message_box'>";
		html += "<img src='${ImageruteThankyou}'/>";
		html += "<p id='raised'>Nomination has been created properly.</p>";
        // TO CHANGE THE URL
		html += "<a id='back-service' href='/group/"+siteName+"/nominations-form'>Back to Nomination</a>";
		html += "</div>";
		$("#form-box").html(html);
	}
	function showReasoningForFailuire(){
        var siteName = "${siteName}";
		var html="";
		html += "<div id='message_box'>";
		html += "<p id='raised'>The Nomination was not created, please try again or contact Administrator.</p>";
        // TO CHANGE THE URL
		html += "<a id='back-service' href='/group/"+siteName+"/nominations-form'>Back to Nomination</a>";
		html += "</div>";
		$("#form-box").html(html);
	}
	function showEditConfirmation(){
        var siteName = "${siteName}";
		var html="";
		html += "<div id='message_box'>";
		html += "<img src='${ImageruteThankyou}'/>";
		html += "<p id='raised'>Nomination has been edited properly.</p>";
        // TO CHANGE THE URL
		html += "<a id='back-service' href='/group/"+siteName+"/nominations-form'>Back to Nomination</a>";
		html += "</div>";
		$("#form-box").html(html);
	}
	function showEditReasoningForFailuire(){
        var siteName = "${siteName}";
		var html="";
		html += "<div id='message_box'>";
		html += "<p id='raised'>The Nomination was not edited, please try again or contact Administrator.</p>";
        // TO CHANGE THE URL
		html += "<a id='back-service' href='/group/"+siteName+"/nominations-form'>Back to Nomination</a>";
		html += "</div>";
		$("#form-box").html(html);
	}

	function validateForm() {
	
		var editMode = $("#checkEditMode").val();
	
        var v_ja_Nominations_TitleNomination = $(".ja_Nominations_TitleNomination").val();
        var v_ja_Nominations_DescriptionNomination = $(".ja_Nominations_DescriptionNomination").val();
        var v_ja_Nominations_ClosingDateNomination = $(".ja_Nominations_ClosingDateNomination").val();
        var v_ja_Nominations_StatusNomination = $(".ja_Nominations_StatusNomination").val();
        var v_ja_Nominations_ReportVisibilityNomination = $(".ja_Nominations_ReportVisibilityNomination").val();
        var v_ja_Nominations_DecidingVoteNomination = $(".ja_Nominations_DecidingVoteNomination").val();
		
        console.log(editMode);

		if(editMode == "true"){
			Liferay.Service('/cproposal.recformarticle/update_committee_nominations_process',
				{
					nominationsResourcePK: ${nominationsResourcePK?number},
					TitleNomination: v_ja_Nominations_TitleNomination,
					DescriptionNomination: v_ja_Nominations_DescriptionNomination,
					ClosingDateNomination: v_ja_Nominations_ClosingDateNomination,
					StatusNomination: v_ja_Nominations_StatusNomination,
					ReportVisibilityNomination: v_ja_Nominations_ReportVisibilityNomination,
                    DecidingVoteNomination: v_ja_Nominations_DecidingVoteNomination,
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
		
                Liferay.Service('/cproposal.recformarticle/create_committee_nominations_process',
				{
					TitleNomination: v_ja_Nominations_TitleNomination,
					DescriptionNomination: v_ja_Nominations_DescriptionNomination,
					ClosingDateNomination: v_ja_Nominations_ClosingDateNomination,
					StatusNomination: v_ja_Nominations_StatusNomination,
					ReportVisibilityNomination: v_ja_Nominations_ReportVisibilityNomination,
                    DecidingVoteNomination: v_ja_Nominations_DecidingVoteNomination,
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