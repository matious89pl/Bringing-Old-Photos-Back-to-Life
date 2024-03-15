<!-- CONSULTATION DETAILS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.8.1/html2pdf.bundle.min.js" integrity="sha512-vDKWohFHe2vkVWXHp3tKvIxxXg0pJxeid5eo+UjdjME3DBFBn2F8yWOE0XmiFcFbXxrEOR1JriWEno5Ckpn15A==" crossorigin="anonymous"></script>

<#-- COMMENTS SECTION 
<#assign fileTitle = TextTitleConsultation.getData() + " Comments.pdf" />-->
<#assign images_folder = themeDisplay.getPathThemeImages() />			
<#assign allowResponseByDate = 0>
<#if TextDate.getData()??>
    <#assign nowDateFormat = .now?date />
    <#assign TextComments_TextDate_Data = getterUtil.getString(TextDate.getData())>
<#if validator.isNotNull(TextComments_TextDate_Data)>
 <#assign Comments_Deadline_DateObj = dateUtil.parseDate("yyyy-MM-dd", TextComments_TextDate_Data, locale)>
    <#assign Comments_Deadline_DateFormat = Comments_Deadline_DateObj?date />
		<#if (Comments_Deadline_DateFormat >= nowDateFormat) >
		  <#assign allowResponseByDate = 1>
		</#if>
    </#if>
</#if>


<#-- SERVICES  -->
<#assign userService = utilLocator.findUtil("com.liferay.portal.kernel.service.UserLocalService") />
<#assign rolService = utilLocator.findUtil("com.liferay.portal.kernel.service.RoleLocalService") />
<#assign journalArticleService = serviceLocator.findService("com.liferay.journal.service.JournalArticleLocalService") />
<#assign ddlJournalArticleService = serviceLocator.findService("com.everis.rec.ddl.journal.article.service.DDL_JournalArticleLocalService") />

<#-- GET JOURNAL ARTICLE  -->
<#assign journalArticleId = .vars['reserved-article-id'].data />
<#assign groupId = themeDisplay.getLayout().getGroupId() />
<#assign article = journalArticleService.getArticle(groupId, "${journalArticleId}") />

<#-- GETTING USER ORGANIZATIONS TO SHOW "ADD RESPONSES" BUTTON -->
<#assign userId = themeDisplay.getUserId() />
<#assign user = userService.getUser(userId) />

<#assign organizationList = user.getOrganizations() />
<#assign addResponses = 0 />
<#list organizationList as org>
    <#list Consultation_TargetedAudience.getOptions() as targetedAudience>
        <#if org.getName() == targetedAudience>
            <#assign addResponses = 1 />
        </#if>
    </#list>
</#list>

<#-- GETTING USER ROLES TO SHOW RESPONSES TO CHANGE MANAGEMENT TEAM -->
<#assign showResponses = 0 />
<#assign userRoles = user.getRoles()/>
<#list userRoles as rol>
    <#if rol.getName() == "Change_Management_Team" || rol.getName() == "Administrator">
        <#assign showResponses = 1 />
    </#if>
</#list>

<#list userRoles as rol>
    <#if rol.getName() == "Administrator" >
        <#assign addResponses = 1 />
    </#if>
</#list>

<#-- GETTING SPECIFIC DDL ID FOR THIS IMPACT ASSESSMENT -->
<#assign articlePrimKey = article.getResourcePrimKey() />
        <#assign articlleCustomTitle = article.getExpandoBridge().getAttribute("customTitle") />

<#assign ddlJournalArticleList = ddlJournalArticleService.getDDL_JournalArticleByJournalPK(articlePrimKey) />
<#list ddlJournalArticleList as ddlArticle>
    <#assign ddlArticle = ddlArticle />
</#list>
<#assign ddlRecordSetId = ddlArticle.ddlRecordSetId />
<#assign link = "/group/guest/add-consultation?IAID=${journalArticleId}">

<#-- GETTING CONSULTATION MEMBERS BY SEGMENT -->
<#assign segmentService = serviceLocator.findService("com.liferay.segments.service.SegmentsEntryLocalService") />
<#assign listSegmentsEntry =  segmentService.getSegmentsEntries(0, segmentService.getSegmentsEntriesCount()) />
<#assign segmentRelService = serviceLocator.findService("com.liferay.segments.service.SegmentsEntryRelLocalService") />

    <#list listSegmentsEntry as segmentEntry >
        <#if segmentEntry.name?contains("Consultation") >
            <#assign listSegmentsRelEntry =  segmentRelService.getSegmentsEntryRels(segmentEntry.segmentsEntryId) />
            <#list listSegmentsRelEntry as SegmentsRelEntry >
                <#if SegmentsRelEntry.classPK == userId >
                    <#assign addResponses = 1 />
                </#if>
            </#list>
        </#if>        
    </#list>
<#-- // END LOGIC -->

<#-- SHOWING DATA -->

<div id="consultation-details">
    <#if TextLinkChangeProposalID.getData() != ""> 
    <div id="cpchangeProposalLink">
         <p> 
             <a href="${TextLinkChangeProposalID.getData()}"> Change Proposal Page </a>
         </p>
    <#else>
        <div id="nochangeProposalLink">
    </#if>
    </div>
<#if showResponses = 1 >
    <a href= "${link}" class="editIA">Edit</a>
    </#if>
<br>
<br>


    <div class="row">
        <div id="cpid" class="left">
            <p class="bold">Change Proposal ID</p>
            <p> ${TextIDChangeProposal.getData()} </p>
        </div>
        <#if articlleCustomTitle != "">
         <div id="cpTitle" class="right">
            <p class="bold"> Consultation title</p> 
            <p> ${articlleCustomTitle} </p>
        </div> 
        <#else>
        <div id="cpTitle" class="right">
            <p class="bold"> Consultation title</p> 
            <p> ${article.getTitle()} </p>
        </div> 
        </#if>
    </div> 
    <div class="row">      
        <div id="cptargetAudience" class="left">
	        <p class="bold">Targeted Audience</p><p> 
	            <#list Consultation_TargetedAudience.getOptions() as targetedAudience>
                    ${targetedAudience}<#sep>, </#sep>
                </#list>
	        </p>
        </div>
   
        <div  id="cpresponsesDeadline" class="right">
            <#assign TextComments_TextDate_Data = getterUtil.getString(TextDate.getData())>
            <#if validator.isNotNull(TextComments_TextDate_Data)>
        	   <#assign TextComments_TextDate_DateObj = dateUtil.parseDate("yyyy-MM-dd", TextComments_TextDate_Data, locale)>
        	   <#assign Comments_DeadLineParse = TextComments_TextDate_DateObj?date />

                <p class="bold"> Responses Deadline</p><p> ${dateUtil.getDate(Comments_DeadLineParse, "dd MMM yyyy", locale)} </p>
            </#if>
        </div>
    </div> 

    <h3 id="cpaccompanyingDocs" class="bold"> Accompanying documents </h3>
    
    <#if TextDocumentConsultation??>
        <#if TextDocumentConsultation.getSiblings()?has_content>
        	<#list TextDocumentConsultation.getSiblings() as cur_TextDocumentConsultation>
             <#if cur_TextDocumentConsultation.getData()??>
        	    <#assign title = "Supporting Document" />
        	    <#if cur_TextDocumentConsultation.Consultation_Document_Title?? && cur_TextDocumentConsultation.Consultation_Document_Title.getData() != "">
            	    <#assign title = cur_TextDocumentConsultation.Consultation_Document_Title.getData()/>
    			</#if>
    			<p> <img src="${images_folder}/forms/file-green.svg" > <a href="${cur_TextDocumentConsultation.getData()}">
    				${languageUtil.format(locale, "download-x", title, false)}
    			</a> </p>
        	 <#else>
                <p> No documents added </p>
            </#if>
    		</#list>
    	</#if>
        <#else>
            <p> No documents added </p>
            
        
    </#if>
</div>

<!-- ADD ANOTHER BUTTON TO ENABLE EDIT RESPONSE -->
<#if addResponses == 1 && allowResponseByDate == 1 >
    <input id="addResponse" type="button" value="Add Response" onclick="addNewResponse('${Consultation_ChooseQATemplate.getData()}')" data="${ddlRecordSetId}" name="AddResponses"/>
</#if>

<#-- <input type="button" value="Download comments" onclick="downloadComments()"> -->

 <#if showResponses == 0 > 
    <#assign preferences = "recordSetId=" + ddlRecordSetId + "&customSearch=customSearch" />
<#else>
    <#assign preferences = "recordSetId=" + ddlRecordSetId />
</#if>

<div class="consultation-responses">
	<@liferay_portlet["runtime"]
		queryString=preferences
		portletName="com_liferay_dynamic_data_lists_web_portlet_DDLDisplayPortlet"
	/>
</div>


<script>

    function addNewResponse(responseTemplate){	
		var addURL = $("#view_records_id nav.management-bar div.container-fluid ul.navbar-nav li.nav-item a.btn-primary").attr("href");
		var addURLQueryString = addURL.split("?")[1];
		var currentURL = window.location.pathname;
		var finalURL = currentURL + "?" + addURLQueryString;
        window.location.href = finalURL;
    }

    function downloadComments(){
        $(".taglib-discussion > form > div").attr("id", "element-to-print");
        
        // Get the element to print
        var element = $('#element-to-print').html();
        
        // Define optional configuration
        var options = {
          filename: 'my-comments.pdf'
        };
        
        // Create instance of html2pdf class
        var exporter = new html2pdf(element, options);
        
        // Download the PDF or...
        exporter.getPdf(true).then((pdf) => {
          console.log('pdf file downloaded');
        });
        
        // Get the jsPDF object to work with it
        exporter.getPdf(false).then((pdf) => {
          console.log('doing something before downloading pdf file');
          pdf.save();
        });
        
        // You can also use static methods for one time use...
        options.source = element;
        options.download = true;
        html2pdf.getPdf(options);
    }
</script>

<style>

div#cpchangeProposalLink {
    margin-top: -55px !important;
}

.autofit-row.mb-4.metadata-author {
    width: 70%;
}

#rec-theme #consultation-details #cpchangeProposalLink a:hover {
    text-decoration: none;
}
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
	.editIA{
    background: #b1c568;
    padding: 11px;
    border-radius: 6px;
    color: white !important;
    font-size: 12px;
    border: 0;
    margin-right: 12px;
    float: left;
    text-align: center
    }
.has-control-menu .lfr-asset-anchor {
    margin-top: 0px;
}

</style>