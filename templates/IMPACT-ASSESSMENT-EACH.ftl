<#assign images_folder = themeDisplay.getPathThemeImages() />	
<#assign allowResponseByDate = 0>
<#assign showEdit = 0>


<#if IA_ResponsesDeadline.getData()??>
    <#assign IA_ResponsesDeadline_Data = getterUtil.getString(IA_ResponsesDeadline.getData())>
    <#assign nowDateFormat = .now?date />
    <#if validator.isNotNull(IA_ResponsesDeadline_Data)>
        <#assign IA_ResponsesDeadline_DateObj = dateUtil.parseDate("yyyy-MM-dd", IA_ResponsesDeadline_Data, locale)>
        <#assign Comments_Deadline_DateFormat = IA_ResponsesDeadline_DateObj?date />
        <#if (Comments_Deadline_DateFormat >= nowDateFormat) >
            <#assign allowResponseByDate = 1>
        </#if>

        <#assign deadline = dateUtil.getDate(IA_ResponsesDeadline_DateObj, "dd MMM yyyy", locale) />
    </#if>
</#if>

<#-- SERVICES  -->
<#assign userService = utilLocator.findUtil("com.liferay.portal.kernel.service.UserLocalService") />
<#assign rolService = utilLocator.findUtil("com.liferay.portal.kernel.service.RoleLocalService") />
<#assign journalArticleService = serviceLocator.findService("com.liferay.journal.service.JournalArticleLocalService") />
<#assign ddlJournalArticleService = serviceLocator.findService("com.everis.rec.ddl.journal.article.service.DDL_JournalArticleLocalService") />

<#-- GET JOURNAL ARTICLE  -->
<#assign userId = themeDisplay.getUserId() />
<#assign user = userService.getUser(userId) />
<#assign journalArticleId = .vars['reserved-article-id'].data/>
<#assign companyId= themeDisplay.getCompanyId() />
<#assign groupId = themeDisplay.getLayout().getGroupId() />
<#assign currentEmail = user.getEmailAddress() />

<#assign article = journalArticleService.getArticle(groupId, "${journalArticleId}") />
<#assign articlleCustomTitle = article.getExpandoBridge().getAttribute("customTitle") />
<#assign articlePrimKey = article.getResourcePrimKey() />
<#assign link = "/group/guest/add-assessment?IAID=${journalArticleId}">
<#assign idIA = IA_CPID.getData() />

<#assign document = saxReaderUtil.read(article.getContent())/>
<#assign targetedAudience = document.valueOf("//dynamic-element[@name='IA_TargetedAudience']/dynamic-content/text()") />

<#-- GETTING USER ORGANIZATIONS TO SHOW "ADD RESPONSES" BUTTON -->
<#assign organizationList = user.getOrganizations() />
<#assign addResponses = 0 />
<#list organizationList as org>
    <#if org.getName() == targetedAudience>
        <#assign addResponses = 1 />
    </#if>
</#list>

<#-- GETTING USER ROLES TO SHOW RESPONSES TO CHANGE MANAGEMENT TEAM -->
<#assign showResponses = 0 />
<#assign userRoles = user.getRoles()/>
<#list userRoles as rol>
    <#if rol.getName() == "Change_Management_Team" || rol.getName() == "Administrator" >
        <#assign showResponses = 1 />
    </#if>
</#list>
<#list userRoles as rol>
    <#if rol.getName() == "Change_Management_Team"  >
        <#assign showEdit = 1 />
    </#if>
</#list>

<#list userRoles as rol>
    <#if rol.getName() == "Administrator" >
        <#assign addResponses = 1 />
    </#if>
</#list>


<#-- GETTING SPECIFIC DDL ID FOR THIS IMPACT ASSESSMENT -->
<#assign ddlJournalArticleList = ddlJournalArticleService.getDDL_JournalArticleByJournalPK(articlePrimKey) />
<#list ddlJournalArticleList as ddlArticle>
    <#assign ddlArticle = ddlArticle />
</#list>
<#assign ddlRecordSetId = ddlArticle.ddlRecordSetId />

<#-- GETTING CONSULTATION MEMBERS BY SEGMENT -->
<#assign segmentService = serviceLocator.findService("com.liferay.segments.service.SegmentsEntryLocalService") />
<#assign listSegmentsEntry =  segmentService.getSegmentsEntries(0, segmentService.getSegmentsEntriesCount()) />
<#assign segmentRelService = serviceLocator.findService("com.liferay.segments.service.SegmentsEntryRelLocalService") />

    <#list listSegmentsEntry as segmentEntry >
        <#if segmentEntry.name?contains("Impact") >
            <#assign listSegmentsRelEntry =  segmentRelService.getSegmentsEntryRels(segmentEntry.segmentsEntryId) />
            <#list listSegmentsRelEntry as SegmentsRelEntry >
                <#if SegmentsRelEntry.classPK == userId >
                    <#assign addResponses = 1 />
                </#if>
            </#list>
        </#if>        
    </#list>
<#-- // END LOGIC -->


<style>
	
	#rec-theme .card-horizontal {
		background-color: transparent;
		border-color: transparent;
		box-shadow: none;
	}
	
	#rec-theme .card-horizontal .panel-body {
		padding: 0;
	}
	
	#rec-theme .card-horizontal .lfr-ddm-container {
		background-color: transparent;
	}
	
	#rec-theme .card-horizontal .lfr-ddm-container .input-localized-content {
		display: none;
	}
	
	#rec-theme .card-horizontal .lfr-ddm-container .form-builder-field {
		background: transparent;
	}
	
	#rec-theme .card-horizontal .lfr-ddm-container .form-group {
		margin-bottom: 0;
	}
	
	.impact-responses .card-horizontal .panel-body .btn-secondary, .impact-responses .btn-primary, .impact-responses .card-horizontal .panel-body .btn-secondary:hover, .impact-responses .btn-primary:hover {
		background: #b1c568;
		color: white;
		font-weight: normal;
		border-color: transparent;
	}
    .editIA{
    background: #b1c568;
    padding: 11px;
    border-radius: 6px;
    color: white;
    font-size: 12px;
    border: 0;
    margin-right: 12px;
    float: left;
    margin-left: 11px;
    text-align: center;
    }

	.asset-full-content .align-items-center.d-flex.mb-2 {
		display: none !important;
	}

</style>


<div id="impact-details">


<#-- SHOWING DATA -->
<#if IA_LinkToCPPage.getData() != ""> 
    <div id="cpchangeProposalLinkia">
     <p> 
         <a href="${IA_LinkToCPPage.getData()}"> Change Proposal Page </a>
     </p>
     </div>
<#else>
    <div id="nochangeProposalLinkia"> </div>
</#if>





<#if showResponses = 1 >
    <a href= "${link}" class="editIA">Edit</a>

   </#if>
   <br>
		<br>


<div class="row">
    <div id="cpidia" class="left">
        <p class="bold">Change Proposal ID</p>
        <p> ${IA_CPID.getData()} </p>
    </div>
    <#if articlleCustomTitle != "">
    <div id="cpTitleia" class="right">
        <p class="bold"> IA title</p>
        <p> ${articlleCustomTitle} </p>
    </div>
    <#else>
    <div id="cpTitleia" class="right">
        <p class="bold"> IA title</p> 
        <p> ${article.getTitle()} </p>

    </div>
     </#if>
</div>



<div class="row">      
    <div id="cptypeia" class="left">
        <p class="bold">IA Type</p>
        <p> ${IA_Type.getData()} </p>
    </div>

    <div id="cptargetAudienceia" class="left">
        <p class="bold"> IA Targeted Audience</p>
          <#list IA_TargetedAudience.getOptions() as targetedAudience>
                    ${targetedAudience}<#sep>, </#sep>
                </#list>
    </div>
</div>

<div id="cpresponsesDeadlineia">
    <p class="bold"> IA Responses deadline</p> 
    <p>${deadline}</p>
    <!--<p>${Comments_Deadline_DateFormat}</p>  -->
</div>
   

<!--<p> CP ID:</p> <p class="bold">${IA_CPID.getData()}</p>

<p> IA Title:</p> <p class="bold">${article.getTitle()}</p>  

<p> IA Type:</p><p class="bold">${IA_Type.getData()}</p>  

<p> IA Targeted Audience:</p><p class="bold"> ${IA_TargetedAudience.getData()}</p>   -->

<div id="cpaccompanyingDocsia">
    <h3 class="bold"> Accompanying documents </h3>
    <#if IA_Documents??>
    	<#if IA_Documents.getSiblings()?has_content>
    		<#list IA_Documents.getSiblings() as cur_IA_Documents>
            <#if cur_IA_Documents.getData()??>
                                <#assign title = "Supporting Document" />
                                <#if cur_IA_Documents.IA_Document_Title?? && cur_IA_Documents.IA_Document_Title.getData() != ""  >
                                    <#assign title = cur_IA_Documents.IA_Document_Title.getData()/>
                                </#if>

                                <p> <img src="${images_folder}/forms/file-green.svg" > <a href="${cur_IA_Documents.getData()}">
                                    ${languageUtil.format(locale, "download-x", title, false)}
                                </a> </p>
                                    </a>
            <#else>
                <p> No documents added </p>
            </#if>
    		</#list>
    	</#if>
        <#else>
            <p> No documents added </p>


    </#if>
</div>

</div>
<!-- ADD ANOTHER BUTTON TO ENABLE EDIT RESPONSE -->
<#if addResponses == 1 && allowResponseByDate == 1>
    <input id="AddResponse" type="button" value="Add Response" onclick="addNewResponse('${IA_ChooseQATemplate.getData()}')" data="${ddlRecordSetId}" name="AddResponses"/>
</#if>

<#if addResponses == 1 > 
    <#assign preferences = "recordSetId=" + ddlRecordSetId + "&customSearch=customSearch" />
<#else>
    <#assign preferences = "recordSetId=" + ddlRecordSetId />
</#if>

<div class="impact-responses">
<@liferay_portlet["runtime"]
	queryString=preferences
	portletName="com_liferay_dynamic_data_lists_web_portlet_DDLDisplayPortlet"
/>
</div>

<#-- // END SHOWING DATA -->

<script>
    function addNewResponse(responseTemplate){
		var addURL = $("#view_records_id nav.management-bar div.container-fluid ul.navbar-nav li.nav-item a.btn-primary").attr("href");
		var addURLQueryString = addURL.split("?")[1];
		var currentURL = window.location.pathname;
		var finalURL = currentURL + "?" + addURLQueryString;
        window.location.href = finalURL;
    }
    
    $( document ).ready(function() {
        $(".btn-comment").click(function() {
            
            console.log("sending new notification");
                Liferay.Service(
              '/messages.messages/send_notification_15',
              {
                companyId: ${companyId},
                groupId: ${groupId},
                cPReference: '${idIA}',
                commentsDeadline: '',
                resourcePrimKey: '${articlePrimKey}',
                email: '${currentEmail}'
              },
              function(obj) {
                console.log(obj);
              }
              
            );
         });
    });
</script>

<style>

#cpaccompanyingDocsia a{
    color: black !important;
}

#cpaccompanyingDocsia a:hover{
    color: black !important;
}

div#cpchangeProposalLinkia {
    margin-top: -55px !important;
}

.autofit-row.mb-4.metadata-author {
    width: 70%;
}

#rec-theme #impact-details #cpchangeProposalLinkia a:hover {
    text-decoration: none;
}
.editIA{
    background: #b1c568;
    padding: 11px;
    border-radius: 6px;
    color: white;
    font-size: 12px;
    border: 0;
    margin-right: 12px;
    float: left;
    margin-left: 11px;
    text-align: center;
    }
.has-control-menu .lfr-asset-anchor {
    margin-top: 0px;
}
</style>