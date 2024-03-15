<!-- Change Proposal -->
<#-- CHECK THE ROLES TO SHOW ADOPT BUTTON ONLY TO PORTAL_USER AND REC_STAKEHOLDERS -->
<#assign themeImgsSource = themeDisplay.getPathThemeImages()/>
							
<#assign userService = utilLocator.findUtil("com.liferay.portal.kernel.service.UserLocalService") />
<#assign rolService = utilLocator.findUtil("com.liferay.portal.kernel.service.RoleLocalService") />
<#assign recFormArticleService = serviceLocator.findService("com.everis.cproposal.service.recFormArticleLocalService") />
<#assign impactService = serviceLocator.findService("com.everis.rec.impacts.service.ImpactsLocalService") />

<#-- Getting impacts -->
<#assign consumerTypeList = impactService.findImpactsByCategory("COSTUMER-TYPE-IMPACTED") />
<#assign affectedConsumersList = impactService.findImpactsByCategory("IMPACTS-ON-AFFECTED-CONSUMERS") />
<#assign scheduleImpactList = impactService.findImpactsByCategory("REC-AND-REC-SCHEDULE-IMPACTS") />
<#assign specificationImpactList = impactService.findImpactsByCategory("DATA-SPECIFICATION-IMPACTS") />
<#assign serviceProviderImpactList = impactService.findImpactsByCategory("REC-SERVICE-PROVIDER-IMPACTS") />
<#assign marketParticipantList = impactService.findImpactsByCategory("REC-PARTY-AND-MARKET-PARTICIPANT-IMPACTS") />
<#assign industryCodesImpactedList = impactService.findImpactsByCategory("OTHER-INDUSTRY-CODES-IMPACTED") />

<#assign arrow = themeDisplay.getPathThemeImages()+"/forms/arrow-down.svg" />
<#assign Imagerute = themeDisplay.getPathThemeImages()+"/clay/icons.svg#angle-right" />

<#assign images_folder = themeDisplay.getPathThemeImages() />
<#assign theme_url = themeDisplay.getPathThemeRoot()/>
<#assign userId = themeDisplay.getUserId() />
<#assign user = userService.getUser(userId)>
<#assign userRoles = user.getRoles()/>
<#assign showAdoptButton = 0 />
<#assign showButton = 0 />
<#assign showEditCSButton = 0/>
<#assign showEditLUButton = 0/>
<#assign ImpactTabEditable = 0 />
<#assign addComments = 0 />

<#list userRoles as rol>
    <#if rol.getName() == "Change_Management_Team" || rol.getName() == "Administrator" >
        <#assign ImpactTabEditable = 1 />
    </#if>
</#list>

<#list userRoles as rol>
    <#if rol.getName() == "Portal_User" || rol.getName() == "REC_Stakeholder" || rol.getName() == "Administrator" >
        <#assign showAdoptButton = 1 />				
        <#break>
    </#if>
</#list>

<#list userRoles as rol>
    <#if rol.getName() == "REC_Stakeholder" || rol.getName() == "Administrator" || rol.getName() == "Change_Management_Team" >
        <#assign addComments = 1 />							
        <#break>
    </#if>
</#list>

<#list userRoles as rol>
    <#if rol.getName() == "Change_Management_Team" || rol.getName() == "Administrator" >
        <#assign showButton = 1 />
        <#break>
    </#if>
</#list>

							
<!-- Start script Edit Problem Statementent and Solution Requeriments-->
<#assign journalArticleId = .vars['reserved-article-id'].data />							
<#assign companyId= themeDisplay.getCompanyId() />							
<#assign groupId= themeDisplay.getLayout().getGroupId() />							
<#assign currentEmail = user.getEmailAddress() />	
							
<#-- GET JOURNAL ARTICLE  -->							
<#assign journalArticleService = serviceLocator.findService("com.liferay.journal.service.JournalArticleLocalService") />
<#assign article = journalArticleService.getArticle(groupId, "${journalArticleId}") />							
<#assign articleResourcePrimKey = article.getResourcePrimKey() />
<#assign hasRecFormArticle = false />

<#if recFormArticleService.getFormIdByArticleId(articleResourcePrimKey)??>
	<#assign formId = recFormArticleService.getFormIdByArticleId(articleResourcePrimKey) />
    <#if recFormArticleService.fetchrecFormArticle(formId)??>
        <#assign recFormArticle = recFormArticleService.fetchrecFormArticle(formId) />
        <#assign impacts = recFormArticle.getArticleImpacts() />
        <#assign hasRecFormArticle = true />
    </#if>
</#if>

<#-- Checking if CP is ALTERNATIVE -->
<#assign getAlternativeValue = recFormArticleService.isAlternative(articleResourcePrimKey) />
<#assign isAlternative = getAlternativeValue?string('true', 'false') />
<#assign recActivityLogs = serviceLocator.findService("com.everis.rec.service.activity.logs.service.RecLogLocalService") />
<#assign listRecActivityLogs = recActivityLogs.findByJournaArticleClassPK(articleResourcePrimKey) />
<#assign textProblem = InitialAssessment.ChangeRequirements.ProblemStatement.getData() />							
<#assign textSolution = InitialAssessment.ChangeRequirements.SolutionRequirements.getData() />							
							
<#-- GETTING SPECIFIC DDL ID FOR THIS ARTICLE -->
<#assign ddlJournalArticleService = serviceLocator.findService("com.everis.rec.ddl.journal.article.service.DDL_JournalArticleLocalService") />							
							
<#if ddlJournalArticleService.getDDL_JournalArticleByJournalPKAndName(articleResourcePrimKey, "CP-Milestone-")??>
	<#assign ddlJAMilestone = ddlJournalArticleService.getDDL_JournalArticleByJournalPKAndName(articleResourcePrimKey, "CP-Milestone-") >						
	<#assign ddlMilestoneId = ddlJAMilestone.getDdlRecordSetId()>						
<#else>
	<#assign ddlMilestoneId = 0>
</#if>
							
<#if ddlJournalArticleService.getDDL_JournalArticleByJournalPKAndName(articleResourcePrimKey, "CP-Initial-Comments-")??>
	<#assign ddlInitialComment = ddlJournalArticleService.getDDL_JournalArticleByJournalPKAndName(articleResourcePrimKey, "CP-Initial-Comments-") >						
	<#assign ddlInitialCommentId = ddlInitialComment.getDdlRecordSetId()>						
<#else>
	<#assign ddlInitialCommentId = 0>
</#if>
							
<#if ddlJournalArticleService.getDDL_JournalArticleByJournalPKAndName(articleResourcePrimKey, "CP-Preliminary-Comments-")??>
	<#assign ddlPreliminaryComment = ddlJournalArticleService.getDDL_JournalArticleByJournalPKAndName(articleResourcePrimKey, "CP-Preliminary-Comments-") >						
	<#assign ddlPreliminaryCommentId = ddlPreliminaryComment.getDdlRecordSetId()>						
<#else>
	<#assign ddlPreliminaryCommentId = 0>
</#if>
							
<#if ddlJournalArticleService.getDDL_JournalArticleByJournalPKAndName(articleResourcePrimKey, "CP-Final-Comments-")??>
	<#assign ddlFinalComment = ddlJournalArticleService.getDDL_JournalArticleByJournalPKAndName(articleResourcePrimKey, "CP-Final-Comments-") >						
	<#assign ddlFinalCommentId = ddlFinalComment.getDdlRecordSetId()>						
<#else>
	<#assign ddlFinalCommentId = 0>
</#if>

<#-- Getting CP values map to display -->
<#-- Constant names -->
<#assign cpDetails_proposerName = "Proposer Name" />
<#assign cpDetails_companyName = "Company" />
<#assign cpDetails_companyTypeName = "Company Type" />
<#assign cpDetails_issueSeekingName = "What is the issue you are seeking to solve?" />
<#assign cpDetails_impactHavingName = "What impact is this having?" />
<#assign cpDetails_outcomesName = "What outcomes do you consider are needed to achieve an effective solution?" />
<#assign cpDetails_justificationName = "If so, please provide justification?" />
<#assign cpDetails_backgroundInformation01Name = "Is there any background information or context to the Change Proposal that would be useful?" />
<#assign cpDetails_consumerName = "Which consumer types will be impacted by this Change Proposal?" />
<#assign cpDetails_impactConsumersName = "What impact will this Change Proposal have for these consumers?" />
<#assign cpDetails_recSchedulesName = "The REC or REC Schedules" />
<#assign cpDetails_dataSpecificationName = "The Data Specification" />
<#assign cpDetails_centralSystemsName = "Central Systems or REC Services" />
<#assign cpDetails_partiesAndMarketName = "REC Parties and/or Market Participants" />
<#assign cpDetails_industryCodesName = "Other industry codes" />
<#assign cpDetails_significantCodeReviewName = "Does this Change Proposal impact a Significant Code Review?" />
<#assign cpDetails_urgentCPName = "Do you believe this Change Proposal meets the criteria for 'Urgency' and should be progressed under an urgent timetable?" />
<#assign cpDetails_justificationName02 = "If so, please provide justification?" />
<#assign cpDetails_timescalesName = "Are there any required timescales for the development or implementation of this Change Proposal?" />
<#assign cpDetails_dependeciesName = "Are you aware of any dependencies associated with the Change Proposal which may impact its priority or progression timetable?" />
<#assign cpDetails_relatePreviousName = "Does this Change Proposal relate to an existing or previous Change Proposal?" />

<#assign articleResourcePrimKeyString = articleResourcePrimKey?string />

<#if recFormArticleService.getCPDetails(articleResourcePrimKeyString)??>
    <#assign CPMap = recFormArticleService.getCPDetails(articleResourcePrimKeyString) />
    <#assign defaultValue = {"value" : "", "hasAdditionalInformation" : false}/>
    <#assign cpDetails_proposerFullName = CPMap[cpDetails_proposerName]!defaultValue />
    <#assign cpDetails_company = CPMap[cpDetails_companyName]!defaultValue />
    <#--<#if validator.isNotNull(CPMap[cpDetails_proposerName])>-->
    <#assign cpDetails_companyType = CPMap[cpDetails_companyTypeName]!defaultValue />
    <#--<#else>-->
        <#--<#assign cpDetails_companyType = {"value" : "", "hasAdditionalInformation" : false} />-->
    <#--</#if>-->
    <#assign cpDetails_issueSeeking = CPMap[cpDetails_issueSeekingName]!defaultValue />
    <#assign cpDetails_impactHaving = CPMap[cpDetails_impactHavingName]!defaultValue />
    <#assign cpDetails_outcomes = CPMap[cpDetails_outcomesName]!defaultValue />
    <#assign cpDetails_justification = CPMap[cpDetails_justificationName]!defaultValue />
    <#assign cpDetails_backgroundInformation01 = CPMap[cpDetails_backgroundInformation01Name]!defaultValue />
    <#assign cpDetails_consumer = CPMap[cpDetails_consumerName]!defaultValue />
    <#assign cpDetails_impactConsumers = CPMap[cpDetails_impactConsumersName]!defaultValue />
    <#assign cpDetails_recSchedules = CPMap[cpDetails_recSchedulesName]!defaultValue />
    <#assign cpDetails_dataSpecification = CPMap[cpDetails_dataSpecificationName]!defaultValue />
    <#assign cpDetails_centralSystems = CPMap[cpDetails_centralSystemsName]!defaultValue />
    <#assign cpDetails_partiesAndMarket = CPMap[cpDetails_partiesAndMarketName]!defaultValue />
    <#assign cpDetails_industryCodes = CPMap[cpDetails_industryCodesName]!defaultValue />
    <#assign cpDetails_significantCodeReview = CPMap[cpDetails_significantCodeReviewName]!defaultValue />
	<#assign cpDetails_urgentCP = CPMap[cpDetails_urgentCPName]!defaultValue />
    <#assign cpDetails_justification02 = CPMap[cpDetails_justificationName02]!defaultValue />
    <#assign cpDetails_timescales = CPMap[cpDetails_timescalesName]!defaultValue />
    <#assign cpDetails_dependecies = CPMap[cpDetails_dependeciesName]!defaultValue />
    <#assign cpDetails_relatePrevious = CPMap[cpDetails_relatePreviousName]!defaultValue />
<#else>
    <#assign cpDetails_proposerFullName = defaultValue />
    <#assign cpDetails_company = defaultValue />
    <#assign cpDetails_companyType = defaultValue />
    <#assign cpDetails_issueSeeking = defaultValue />
    <#assign cpDetails_impactHaving = defaultValue />
    <#assign cpDetails_outcomes = defaultValue />
    <#assign cpDetails_justification = defaultValue />
    <#assign cpDetails_backgroundInformation01 = defaultValue />
    <#assign cpDetails_consumer = defaultValue />
    <#assign cpDetails_impactConsumers = defaultValue />
    <#assign cpDetails_recSchedules = defaultValue />
    <#assign cpDetails_dataSpecification = defaultValue />
    <#assign cpDetails_centralSystems = defaultValue />
    <#assign cpDetails_partiesAndMarket = defaultValue />
    <#assign cpDetails_industryCodes = defaultValue />
    <#assign cpDetails_significantCodeReview = defaultValue />
    <#assign cpDetails_urgentCP = defaultValue />
    <#assign cpDetails_justification02 = defaultValue />
    <#assign cpDetails_timescales = defaultValue />
    <#assign cpDetails_dependecies = defaultValue />
    <#assign cpDetails_relatePrevious = defaultValue />
</#if>
<#-- END - Getting CP values map to display -->							
							
<style>							
	.AddEntry {						
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
		float: right;					
    }
	.IARCustomComments .portlet-dynamic-data-lists-display, .PCRCustomComments .portlet-dynamic-data-lists-display, .FCRCustomComments .portlet-dynamic-data-lists-display, #Proposal-cpm .portlet-dynamic-data-lists-display {						
		padding-top: 3%;					
	}
    .F {
        padding-right: 0px;
    }
    .psScore {
        width: 3em;
    }
    .psAlert {
        padding: 3px;
        margin-left: 1em;
        transform: scale(1.2);
        position: absolute;
        left: 5em;
        top: 0em;
    }
    .left-item.change-prog hr {
        margin-left: -5.5%;
    }
    span.psAlert#h-prt {
        background: #a1353c;
        color: white;
        font-size: 10px;
        padding: 3px;
        border-radius: 2px;
        letter-spacing: .25px;
        line-height: 15px;
        font-family: "Roboto-bold" !important;
    }
    span.urgAlert#ugt {
        background: #551b20;
        color: white;
        font-size: 10px;
        padding: 3px;
        border-radius: 2px;
        letter-spacing: .25px;
        line-height: 15px;
        font-family: "Roboto-bold" !important;
    }
    #items-proposal {
        padding-bottom:5% !important;
    }
    #related-links {
        margin-top: 1em;
    }
    #related-links .card-header {
       background-color: rgba(112,173,163,0.5);
       font-weight: bold;
       font-size: 16px;
    }
    #related-links .card-body {
       background-color: rgba(112,173,163,0.3);
    }
    #related-links .card-body .relatedLink {
        margin-bottom: 0.4em;
    }
    #related-links .card-body .relatedLink a {
        text-decoration: underline;
        font-size: 15px;
        color:inherit;
    }
    #accordion-latest-update .card-body, #accordion-change-summary .card-body {
        text-align: justify;
        white-space: pre-line;
    }
    #header-chdetail .right {
        position: relative !important;
        top: 0% !important;
        padding-top: 35px;
        padding-right: 17px;
    }
    #withdrawnButton,#adoptButton,#alternativeCPButton {
        max-width: 16em;
        min-width: 11em;
    }
    #staffDiv {
        padding-left: 17%;
        padding-top: 5%;
    }
    #staffDiv .left-item {
        padding: 0%;
    }
    #staffDiv .left-item ul .userNameDetails {
        text-decoration: underline;
        font-weight: bold;
    }
    .staffMemberEmpty {
        color: black !important;
        text-decoration: none;
        font-weight: normal;
    }

    /*Progress bar css*/
    .cp-progress-bar {
        display: flex;
        width: 1200px;
    }
    .separator {
        height: 50px;
    }
    .polygon {
        position: absolute;
        height: 70px;
        width: 188px;
        left: 1px;
        top: 1px;
    }
    .polygonBorder {
        position: relative;
        height: 72px;
        width: 190px;
        background-color: green;
    }
    .inBetweenPoly {
        clip-path: polygon(75% 0%, 100% 50%, 75% 100%, 0% 100%, 25% 50%, 0% 0%);
    }
    .polygonBorder.inBetweenPoly {
        margin-left: -47px;
    }
    .firstPoly {
        clip-path: polygon(75% 0%, 100% 50%, 75% 100%, 0% 100%, 0 51%, 0% 0%);
    }
    .greenPoly {
        background-color: green;
        color: white;
    }
    .pinkPoly {
        background-color: pink;
    }
    .whitePoly {
        background-color: white;
    }
    .polygonText {
        width: 8em;
        padding: 0px 0px 0px 3px;
        font-size: 0.90em;
        position: relative;
        left: 21%;
        height: 2em;
        text-align: center;
    }
    .firstPoly .polygonText {
        left: 15%;
    }
    .one-line {
        top: 38%;
    }
    .two-line {
        top: 21%;
    }
    .three-line {
        top: 17%;
        left: 23%;
        font-size: 0.8em;
        width: 8em;
    }
    .pb-next-row {
        margin-left: 46px;
        margin-top: 5px;
    }
    #progress-bar-container {
        margin-bottom: 3em;
    }
    /* Small devices (landscape phones, 576px and up) */
    @media (min-width: 576px) {
        #progress-bar-container {
        transform: scale(1.0);
        }
    }
    /* Medium devices (tablets, 768px and up) */
    @media (min-width: 768px) {
        #progress-bar-container {
        transform: scale(1.0);
        }
    }
    /* Large devices (desktops, 992px and up) */
    @media (min-width: 992px) {
        #progress-bar-container {
        transform: scale(1.0);
        }
    }
    /* X-Large devices (large desktops, 1200px and up) */
    @media (min-width: 1200px) {
        #progress-bar-container {
        transform: scale(1.0);
        }
    }
    /* XX-Large devices (larger desktops, 1400px and up) */
    @media (min-width: 1400px) {
        #progress-bar-container {
        transform: scale(1.0);
        }
    }
</style>
							
<!-- Start Script alternative button-->							
<script>							
    $(document).ready(function(){							
        tabcontent = document.getElementsByClassName("tabcontent");

        for (i = 1; i < tabcontent.length; i++) {
            tabcontent[i].style.display = "none";							
        }	

        setTimeout(function(){
            $("#alternativeCPButton").click(function(){
                console.log("Alternative button clicked");
                Liferay.Service(
                    '/cproposal.recformarticle/Create Alternative Change Proposal',
                {
                    userId: ${userId},
                    groupId:  ${groupId},
                    articleId: ${journalArticleId}
                },
                function(obj) {
                    console.log(obj.formInstanceId);
                    console.log(obj.formInstanceRecordId);
                    let formInstanceId= obj.formInstanceId;
                    let formInstanceRecordId= obj.formInstanceRecordId;
                    let url = "/group/guest/~/control_panel/manage?p_p_id=com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_redirect=%2Fgroup%2Fguest%2Fchange-management&_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_mvcPath=%2Fadmin%2Fedit_form_instance_record.jsp&_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_formInstanceId="+formInstanceId+"&_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_formInstanceRecordId="+formInstanceRecordId+"";
                    window.location.href = url;
                }
                );
            });
        }, 500);
    });							
</script>
<!-- End Script alternative button-->

<#assign cpRef = ChangeProposalSeparator.ChangeProposalReference.getData() />							
							
<input type="hidden" id="cpReference" value="${cpRef}" />

<!-- Start Script adopt button-->
<script>
    $(document).ready(function(){							
        tabcontent = document.getElementsByClassName("tabcontent");							
        for (i = 1; i < tabcontent.length; i++) {							
            tabcontent[i].style.display = "none";							
        }							
    							
        $("#adoptButton").click(function(){
            var cpRefer = $("#cpReference").val();							
            Liferay.Service(							
                '/messages.messages/send_notification_02',							
                {							
                    companyId: ${companyId},							
                    groupId: ${groupId},							
                    userId: ${userId},							
                    cpReference: cpRefer							
                },
                function(obj) {	
                    console.log("Notification to adopt a CP sent");	
                    console.log(obj);											
                }	              						
            );							
        });							
    });							
</script>	
<!-- End Script adopt button-->

<script>							
    function openTab(evt, tabName) {							
        // Declare all variables
        var i, tabcontent, tablinks;

        // Get all elements with class="tabcontent" and hide them
        tabcontent = document.getElementsByClassName("tabcontent");
        for (i = 0; i < tabcontent.length; i++) {
            tabcontent[i].style.display = "none";
        }

        // Get all elements with class="tablinks" and remove the class "active"
        tablinks = document.getElementsByClassName("tablinks");
        for (i = 0; i < tablinks.length; i++) {
            tablinks[i].className = tablinks[i].className.replace(" active", "");
        }

        // Show the current tab, and add an "active" class to the button that opened the tab
        document.getElementById(tabName).style.display = "block";
        evt.currentTarget.className += " active";
    }							
</script>

<!-- Script pop up user details -->
<script>							
    $(document).ready(function () {
        var search = document.getElementById("search-bar-hidden");
        var popup_user = document.getElementById("change-proposal-pop-up1");

        $(".userNameDetails").click(function () {
            popup_user.style.visibility = "visible";
            search.style.display = "none";
        });

        $(document).click(function(event) {
            //if you click on anything except the modal itself or the "open modal" link, close the modal
            if (!$(event.target).closest("#change-proposal-pop-up1,.userNameDetails,.close").length) {
                popup_user.style.visibility = "hidden";
                search.style.display = "block";
            }
        });
    });
</script>
<!-- Script pop up user details  -->							
							
<script>							
    function getUserDetailPopUp(fullName, userEmail, userPhone, userDescription, userImg, userCompany, userCompanyType, proposer) {
		if (proposer == '1'){
            var phoneIcon = document.getElementById("phoneImgId");
            phoneIcon.className += " hide";
            phoneIcon.className += " iconsDisplayNonePopUp";
            var emailIcon = document.getElementById("emailImgId");
            emailIcon.className += " hide";
            emailIcon.className += " iconsDisplayNonePopUp";
        } 
        console.log("Funtion: getUserDetailPopUp");

        $("#change-proposal-pop-up1 .popupp .userInfo .userDetails .userFullName").html(fullName);					
        $("#change-proposal-pop-up1 .popupp .userInfo .userDetails  .userEmail").html(userEmail);						
        $("#change-proposal-pop-up1 .popupp .userInfo .userDetails  .userPhone").html(userPhone);						
        $("#change-proposal-pop-up1 .popupp .userInfo .userDetails .userDescription").html(userDescription);			
        $("#change-proposal-pop-up1 .popupp .userInfo .userImg").attr("src", userImg);
        $("#change-proposal-pop-up1 .popupp .userInfo .userDetails .userCompany").html(userCompany);
        $("#change-proposal-pop-up1 .popupp .userInfo .userDetails .userCompanyType").html(userCompanyType);
        $("#change-proposal-pop-up1").css("visibility","visible");							
        $("#search-bar-hidden").css("display","none");							
    }

    function closePopUp(){							
        $("#change-proposal-pop-up1").css("visibility","hidden");
        $("#search-bar-hidden").css("display","block");

        var phoneIcon = document.getElementById("phoneImgId");
        var emailIcon = document.getElementById("emailImgId");

        if (phoneIcon.classList.contains('hide')){
            phoneIcon.classList.remove('hide');
        }
        if (phoneIcon.classList.contains('iconsDisplayNonePopUp')){
            phoneIcon.classList.remove('iconsDisplayNonePopUp');
        }
        if (emailIcon.classList.contains('hide')){
            emailIcon.classList.remove('hide');
        }
        if (emailIcon.classList.contains('iconsDisplayNonePopUp')){
            emailIcon.classList.remove('iconsDisplayNonePopUp');
        }
    }
</script>							

<div id="progress-bar-container">
    <div class="cp-progress-bar">
    <#--use a counter to control when a second line shoud be added, use the counter to control the first element is "firstPoly"-->
    <#assign PBCounter = 0>
    <#assign PBSelectedFound = false>

    <#function getOrderClass auxCounter>
        <#local orderClass = "firstPoly">
        <#if auxCounter gt 1>
            <#local orderClass = "inBetweenPoly">
        </#if>
        <#return orderClass>
    </#function>

    <#if !progressBarCP?? || getterUtil.getBoolean(progressBarCP.initialAssessmentPB.getData())>
        <#assign PBCounter += 1>
        <div class="polygonBorder ${getOrderClass(PBCounter)}">
            <div class="polygon ${getOrderClass(PBCounter)} whitePoly"><div class="polygonText two-line" data-status="Initial Assessment">Initial Assessment</div></div>
        </div>
    </#if>

    <#if !progressBarCP?? || getterUtil.getBoolean(progressBarCP.solutionDevelopmentPB.getData())>
        <#assign PBCounter += 1>
        <div class="polygonBorder ${getOrderClass(PBCounter)}">
            <div class="polygon ${getOrderClass(PBCounter)} whitePoly"><div class="polygonText two-line" data-status="Solution Development">Solution Development</div></div>
        </div>
    </#if>

    <#if !progressBarCP?? || getterUtil.getBoolean(progressBarCP.ServiceProviderImpactAssessmentPB.getData())>
    <#assign PBCounter += 1>
        <div class="polygonBorder ${getOrderClass(PBCounter)}">
            <div class="polygon ${getOrderClass(PBCounter)} whitePoly"><div class="polygonText three-line" data-status="Service Provider Impact Assessment">Service Provider Impact Assessment</div></div>
        </div>
    </#if>

    <#if !progressBarCP?? || getterUtil.getBoolean(progressBarCP.PartyImpactAssessmentPB.getData())>
    <#assign PBCounter += 1>
        <div class="polygonBorder ${getOrderClass(PBCounter)}">
            <div class="polygon ${getOrderClass(PBCounter)} whitePoly"><div class="polygonText two-line" data-status="Party Impact Assessment">Impact Assessment</div></div>
        </div>
    </#if>

    <#if !progressBarCP?? || getterUtil.getBoolean(progressBarCP.PreliminaryAssessmentPB.getData())>
    <#assign PBCounter += 1>
        <div class="polygonBorder ${getOrderClass(PBCounter)}">
            <div class="polygon ${getOrderClass(PBCounter)} whitePoly"><div class="polygonText two-line" data-status="Preliminary Assessment">Preliminary Assessment</div></div>
        </div>
    </#if>

    <#if !progressBarCP?? || getterUtil.getBoolean(progressBarCP.ConsultationPB.getData())>
    <#assign PBCounter += 1>
        <div class="polygonBorder ${getOrderClass(PBCounter)}">
            <div class="polygon ${getOrderClass(PBCounter)} whitePoly"><div class="polygonText one-line" data-status="Consultation">Consultation</div></div>
        </div>
    </#if>

    <#if !progressBarCP?? || getterUtil.getBoolean(progressBarCP.FinalAssessmentPB.getData())>
    <#assign PBCounter += 1>
        <div class="polygonBorder ${getOrderClass(PBCounter)}">
            <div class="polygon ${getOrderClass(PBCounter)} whitePoly"><div class="polygonText two-line" data-status="Final Assessment">Final Assessment</div></div>
        </div>
    </#if>

    <#if !progressBarCP?? || getterUtil.getBoolean(progressBarCP.ApprovedWaitingImplementationPB.getData())>
    <#assign PBCounter += 1>
        <div class="polygonBorder ${getOrderClass(PBCounter)}">
            <div class="polygon ${getOrderClass(PBCounter)} whitePoly"><div class="polygonText two-line" data-status="Approved - awaiting implementation">Awaiting Implementation</div></div>
        </div>
        <#if PBCounter == 8>
            </div><div class="cp-progress-bar pb-next-row">
        </#if>
    </#if>

    <#if !progressBarCP?? || getterUtil.getBoolean(progressBarCP.rejectedPB.getData())>
    <#assign PBCounter += 1>
        <div class="polygonBorder ${getOrderClass(PBCounter)}">
            <div class="polygon ${getOrderClass(PBCounter)} whitePoly"><div class="polygonText one-line" data-status="Rejected">Rejected</div></div>
        </div>
        <#if PBCounter == 8>
            </div><div class="cp-progress-bar pb-next-row">
        </#if>
    </#if>

    <#if !progressBarCP?? || getterUtil.getBoolean(progressBarCP.AwaitingAuthorityDecisionPB.getData())>
    <#assign PBCounter += 1>
        <div class="polygonBorder ${getOrderClass(PBCounter)}">
            <div class="polygon ${getOrderClass(PBCounter)} whitePoly"><div class="polygonText three-line" data-status="Awaiting Authority Decision">Awaiting Authority Decision</div></div>
        </div>
        <#if PBCounter == 8>
            </div><div class="cp-progress-bar pb-next-row">
        </#if>
    </#if>

    <#if !progressBarCP?? || getterUtil.getBoolean(progressBarCP.WithdrawnPB.getData())>
    <#assign PBCounter += 1>
        <div class="polygonBorder ${getOrderClass(PBCounter)}">
            <div class="polygon ${getOrderClass(PBCounter)} whitePoly"><div class="polygonText one-line" data-status="Withdrawn">Withdrawn</div></div>
        </div>
        <#if PBCounter == 8>
            </div><div class="cp-progress-bar pb-next-row">
        </#if>
    </#if>

    <#if !progressBarCP?? || getterUtil.getBoolean(progressBarCP.AppealInProgressPB.getData())>
    <#assign PBCounter += 1>
        <div class="polygonBorder ${getOrderClass(PBCounter)}">
            <div class="polygon ${getOrderClass(PBCounter)} whitePoly"><div class="polygonText two-line" data-status="Appeal in progress">Appeal in progress</div></div>
        </div>
        <#if PBCounter == 8>
            </div><div class="cp-progress-bar pb-next-row">
        </#if>
    </#if>

    <#if !progressBarCP?? || getterUtil.getBoolean(progressBarCP.ImplementedPB.getData())>
    <#assign PBCounter += 1>
        <div class="polygonBorder ${getOrderClass(PBCounter)}">
            <div class="polygon ${getOrderClass(PBCounter)} whitePoly"><div class="polygonText one-line" data-status="Implemented">Implemented</div></div>
        </div>
        <#if PBCounter == 8>
            </div><div class="cp-progress-bar pb-next-row">
        </#if>
    </#if>

    <#if !progressBarCP?? || getterUtil.getBoolean(progressBarCP.NewPB.getData())>
    <#assign PBCounter += 1>
        <div class="polygonBorder ${getOrderClass(PBCounter)}">
            <div class="polygon ${getOrderClass(PBCounter)} whitePoly"><div class="polygonText one-line" data-status="New">New</div></div>
        </div>
        <#if PBCounter == 8>
            </div><div class="cp-progress-bar pb-next-row">
        </#if>
    </#if>
    </div>
</div>

<#if ChangeProposalSeparator.ChangeProposalStatus?has_content && ChangeProposalSeparator.ChangeProposalStatus.getData() != "">
    <script>
        //**This is script will colour the progress bar steps properly***
        var selectedStatus = "${ChangeProposalSeparator.ChangeProposalStatus.getData()}";
        selectedStatus = selectedStatus.trim();
        var allPolys = document.querySelectorAll("#progress-bar-container .polygon");
        var selectedFound = false;
        if(allPolys.length > 0) {
            //console.log("Selected status is "+selectedStatus);
            for(let i=allPolys.length-1;i>=0;i--) {
                if(!selectedFound) {
                    let text = allPolys[i].querySelector(".polygonText").dataset.status.trim();
                    //console.log("Poly text is "+text);
                    if(text == selectedStatus) {
                        selectedFound = true;
                        allPolys[i].classList.remove("whitePoly");
                        allPolys[i].classList.add("greenPoly");
                    }
                } else {
                    allPolys[i].classList.remove("whitePoly");
                    allPolys[i].classList.add("pinkPoly");
                }
            }
        }
    </script>
</#if>

<div id="header-chdetail">
    <div class="left">
        <ul id="header-chinfo">
            <li id="title">
                ${article.getTitle()}

                <#assign urgent = InitialAssessment.ChangeProgresion.UrgentChange.getData() />
                <#assign priority = InitialAssessment.ChangeProgresion.PriorityStatus.getData() />							

            </li>
            <li id="reference">
                ${ChangeProposalSeparator.ChangeProposalReference.getData()}
            </li>
        </ul>
    </div>
							
    <div class="right row">
        <div class="col"></div>
        <#if showAdoptButton == 1 >
			<#if isAlternative == "false">
				<input id="alternativeCPButton" type="submit" class="col-3" value="Raise Alternative CP">
            </#if>
        </#if>							

        <#if ChangeProposalSeparator.ChangeProposerEmail.getData() == "" && showAdoptButton == 1 >  							
            <input id="adoptButton" type="submit" class="col-3 ml-1" value="Adopt Proposal">
        </#if>						
        							
        <#if ChangeProposalSeparator.ChangeProposerEmail.getData() == "" && showAdoptButton == 1 >  							
            <input hidden="true" id="withdrawnButton" type="submit" class="col-3 ml-1" value="Withdrawn Proposal">
        </#if>													
    </div>							
</div>													
							
<!-- Tab links -->							
<div class="tab">							
    <button class="tablinks active" onclick="openTab(event, 'Proposal-mgt')">Change Overview</button>
    <button class="tablinks" onclick="openTab(event, 'Proposal-dtl')">Original CP Submission</button>
    <button class="tablinks" onclick="openTab(event, 'Proposal-chg')">Change Proposal Impact</button>
    <button class="tablinks" onclick="openTab(event, 'Proposal-hty')">History of Activities</button>
    <button class="tablinks" onclick="openTab(event, 'Proposal-cpm')">Change Plan Milestones</button>
</div>							
							
<!-- Tab content -->							
<div id="Proposal-mgt" class="tabcontent container-fluid">
	<div class="row page1">						
		<div id="content-left" class="col-md-4">					
            <div id="items-proposal">

            <#--ROW DIV SHOULD START HERE-->
            <div class="row" id="staffDiv">

                    <#--ITEM 1-->

                <#if ChangeProposalSeparator.ChangeProposerEmail?has_content && ChangeProposalSeparator.ChangeProposerEmail.getData() != "">
                <div class="left-item col-12 col-md-6">
                    <h3>Change Proposer</h3>
                    <ul>
                            <#assign proposerEmailAddress = ChangeProposalSeparator.ChangeProposerEmail.getData() />
                            <#if userService.getUserByEmailAddress(companyId, ChangeProposalSeparator.ChangeProposerEmail.getData())??>
                                <#assign changeProposer = userService.getUserByEmailAddress(companyId, ChangeProposalSeparator.ChangeProposerEmail.getData()) />
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
                                <li>
                                    <a class="userNameDetails" onclick="getUserDetailPopUp('${changeProposer.getFullName()}', '', '' , '${userDescriptionReplace}' , '${userImgSrc}', '${cpDetails_company.value}', '${cpDetails_companyType.value}', '1')">${changeProposer.getFullName()}</a>
                                </li>
                                <p>${cpDetails_company.value}</p>
                            <#else>
                                <#if cpDetails_proposerFullName.value?has_content>
                            <li class="userNameDetails">${cpDetails_proposerFullName.value}</li>
                                </#if>
                                <p>${cpDetails_company.value}</p>
                            </#if>


                        <#-- <#if ImpactTabEditable == 1>
                            <#if ChangeProposalSeparator.ChangeProposerEmail?has_content && ChangeProposalSeparator.ChangeProposerEmail.getData() != "">
                                    <li class="email-cp">
                                    ${ChangeProposalSeparator.ChangeProposerEmail.getData()}
                                </li>
                        </#if>
                        </#if>-->
                    </ul>
                </div>
                </#if>

                        <#--ITEM 2-->

                <#if LeadChangeAnalyst?has_content && LeadChangeAnalyst.getData() != "">
                            <div class="left-item col-12 col-md-6">
                        <#-- LEAD CHANGE ANALYST SELECTOR -->
                        <h3> Lead Change Analyst </h3>

                        <#if userService.fetchUserByScreenName(companyId, LeadChangeAnalyst.getData())??>
                            <#assign user = userService.fetchUserByScreenName(companyId, LeadChangeAnalyst.getData()) />
                            <#assign userPhones = user.getPhones()/>
                            <#if userPhones?has_content && userPhones??>
                                <#assign userPhone = userPhones[0]/>
                                <#assign userPhoneNumber = userPhone.number/>
                            <#else>
                                <#assign userPhoneNumber = 'No phone'/>
                            </#if>
                            <#assign userImgSrc = user.getPortraitURL(themeDisplay) />
                            <#assign userDescription = user.getComments()/>
                            <#if userDescription?has_content && userDescription??>
                                <#assign userDescriptionReplace = userDescription?replace('\n','<br>') />
                            <#else>
                                <#assign userDescriptionReplace = '' />
                            </#if>
                            <ul>
                                <li>
                                    <a class="userNameDetails" onclick="getUserDetailPopUp('${user.getFullName()}', '${user.getEmailAddress()}', '${userPhoneNumber}' , '${userDescriptionReplace}' , '${userImgSrc}', '', '')">${user.getFullName()}</a>
                                </li>
                            </ul>
                        <#else>
                            <#assign leadChangeAnalystName = LeadChangeAnalyst.getData() />
                                    <ul>
                                        <li id="" class="staffMemberEmpty">
                                        <div>Unregistered user</div> <div>(<b>${leadChangeAnalystName}</b>)</div>
                                        </li>
                                    </ul>
                        </#if>
                    </div>
                </#if>

                        <#--ITEM 3-->
                <#if LeadAssuranceAnalyst?has_content && LeadAssuranceAnalyst.getData() != "">
                            <div class="left-item col-12 col-md-6">
                        <#-- LED ASSURANCE ANALYST SELECTOR  onclick="getUserDetailPopUp() -->
                        <h3> Lead Assurance Analyst </h3>
                        <#if userService.fetchUserByScreenName(companyId, LeadAssuranceAnalyst.getData())??>
                            <#assign user = userService.fetchUserByScreenName(companyId, LeadAssuranceAnalyst.getData()) />
                            <#assign userPhones = user.getPhones()/>
                            <#if userPhones?has_content && userPhones??>
                                <#assign userPhone = userPhones[0]/>
                                <#assign userPhoneNumber = userPhone.number/>
                            <#else>
                                <#assign userPhoneNumber = 'No phone'/>
                            </#if>
                            <#assign userImgSrc = user.getPortraitURL(themeDisplay) />
                            <#assign userDescription = user.getComments()/>
                            <#if userDescription?has_content && userDescription??>
                                <#assign userDescriptionReplace = userDescription?replace('\n','<br>') />
                            <#else>
                                <#assign userDescriptionReplace = 'No phone' />
                            </#if>
                            <ul>
                                <li>
                                    <a class="userNameDetails" onclick="getUserDetailPopUp('${user.getFullName()}', '${user.getEmailAddress()}', '${userPhoneNumber}' , '${userDescriptionReplace}' , '${userImgSrc}', '', '')">${user.getFullName()}</a>
                                </li>
                            </ul>
                        <#else>
                            <#assign leadAssuranceAnalystName = LeadAssuranceAnalyst.getData() />
                                    <ul>
                                        <li id="" class="staffMemberEmpty">
                                        <div>Unregistered user</div> <div>(<b>${leadAssuranceAnalystName}</b>)</div>
                                        </li>
                                    </ul>
                        </#if>
                    </div>
                </#if>


                        <#--ITEM 4-->
                <#if LeadTechnicalAnalyst?has_content && LeadTechnicalAnalyst.getData() != "">
                            <div class="left-item col-12 col-md-6">
                        <#-- LEAD TECHNICAL ANALYST SELECTOR -->
                        <#if LeadTechnicalAnalyst.getSiblings()?has_content>
                            <h3> Lead Technical Analyst </h3>

                                <#list LeadTechnicalAnalyst.getSiblings() as cur_LeadTechnicalAnalyst>
                                    <#if userService.fetchUserByScreenName(companyId, cur_LeadTechnicalAnalyst.getData())??>
                                        <#assign user = userService.fetchUserByScreenName(companyId, cur_LeadTechnicalAnalyst.getData()) />
                                        <#assign userPhones = user.getPhones()/>
                                        <#if userPhones?has_content && userPhones??>
                                            <#assign userPhone = userPhones[0]/>
                                            <#assign userPhoneNumber = userPhone.number/>
                                        <#else>
                                            <#assign userPhoneNumber = 'No phone'/>
                                        </#if>
                                        <#assign userImgSrc = user.getPortraitURL(themeDisplay) />
                                        <#assign userDescription = user.getComments()/>
                                        <#if userDescription?has_content && userDescription??>
                                            <#assign userDescriptionReplace = userDescription?replace('\n','<br>') />
                                        <#else>
                                            <#assign userDescriptionReplace = '' />
                                        </#if>
                                        <ul>
                                        <li>
                                            <a class="userNameDetails" onclick="getUserDetailPopUp('${user.getFullName()}', '${user.getEmailAddress()}', '${userPhoneNumber}' , '${userDescriptionReplace}' , '${userImgSrc}', '', '')">${user.getFullName()}</a>
                                        </li>
                                        </ul>
                                    <#else>
                                        <#assign leadTechnicalAnalystName = cur_LeadTechnicalAnalyst.getData() />
                                                <ul>
                                                    <li id="leadTechnicalAnalystEmpty" class="staffMemberEmpty">
                                                    <div>Unregistered user</div> <div>(<b>${leadTechnicalAnalystName}</b>)</div>
                                                    </li>
                                                </ul>
                                    </#if>
                                </#list>

                        </#if>
                    </div>
                        </#if>

                </div><#--ROW DIV ENDING-->

                    <hr/>

                <div class="left-item change-prog">
                    <#if ChangeProposalSeparator.ChangeProposalStatus?has_content && ChangeProposalSeparator.ChangeProposalStatus.getData() != "">
                        <p> Status:
                            <#assign valueCPStatus = ChangeProposalSeparator.ChangeProposalStatus.getData()>
                            <#switch valueCPStatus>
                                <#case "Service Provider Impact Assessment">
                                    <span class="status service-pro">${valueCPStatus}</span>
                                    <#break>
                                <#case "Party Impact Assessment">
                                    <span class="status party-imp">${valueCPStatus}</span>
                                    <#break>
                                <#case "Rejected">
                                    <span class="status rejected">${valueCPStatus}</span>
                                    <#break>
                                <#case "Appeal in progress">
                                    <span class="status appeal-prs">${valueCPStatus}</span>
                                    <#break>
                                <#case "Solution Development">
                                    <span class="status solution">${valueCPStatus}</span>
                                    <#break>
                                <#case "Initial Assessment">
                                    <span class="status initial-asse">${valueCPStatus}</span>
                                    <#break>
                                <#case "Preliminary Assessment">
                                    <span class="status prel-asse">${valueCPStatus}</span>
                                    <#break>
                                <#case "Consultation">
                                    <span class="status consultation">${valueCPStatus}</span>
                                    <#break>
                                <#case "Final Assessment">
                                    <span class="status final-asse">${valueCPStatus}</span>
                                    <#break>
                                <#case "Approved - awaiting implementation">
                                    <span class="status approved-wait">${valueCPStatus}</span>
                                    <#break>
                                <#case "Awaiting Authority Decision">
                                    <span class="status waiting">${valueCPStatus}</span>
                                    <#break>
                                <#case "Withdrawn">
                                    <span class="status withdrawn">${valueCPStatus}</span>
                                    <#break>
                                <#case "Implemented">
                                    <span class="status implement">${valueCPStatus}</span>
                                    <#break>
                                <#default>
                                    <#assign res = valueCPStatus?matches("Approved.*")>
                                    <#if res>
                                        <span class="status approved-wait">${valueCPStatus}</span>
                                    <#else>
                                        <span class="status">${valueCPStatus}</span>
                                    </#if>
                            </#switch>
                        </p>
                    </#if>

                    <#if InitialAssessment.ChangeProgresion.ChangePath?has_content && InitialAssessment.ChangeProgresion.ChangePath.getData() != "">
                        <p> Change Path:
                            <b>${InitialAssessment.ChangeProgresion.ChangePath.getData()}</b>
                        </p>
                    </#if>

                    <#if InitialAssessment.ChangeProgresion.ResponsibleCommittee?has_content && InitialAssessment.ChangeProgresion.ResponsibleCommittee.getData() != "" >
                        <p>Responsible Committee:
                            <b>${InitialAssessment.ChangeProgresion.ResponsibleCommittee.getData()}</b>
                        </p>
                    </#if>

                    <#if CP_RELEASE_SCHEDULE?has_content && CP_RELEASE_SCHEDULE.getData() != "" >
                        <p>Implementation date:
                            <b>${CP_RELEASE_SCHEDULE.getData()}</b>
                        </p>
                    </#if>

                    <#--Priority Score-->
                    <#if (priorityScoreCP?? && priorityScoreCP.getData() != "Empty") || (priority??  && priority != "")>
                        <hr/>
                        <div class="row">
                            <div class="col-4">Priority Score: </div>
                                <div class="col-6 col-md-4 font-weight-bold">
                                    <div class="psScore">
                                    <#if priorityScoreCP?? && priorityScoreCP.getData() != "Empty">
                                        ${priorityScoreCP.getData()?trim}
                                    </#if>

                                    <#--Priority Tag-->
                                    <#if priority??  && priority != "">
                                        <#if  priority == "Critical">
                                            <#assign cpPriorityAlert = "CRITICAL">
                                        <#elseif  priority == "Medium">
                                            <#assign cpPriorityAlert = "MEDIUM">
                                        <#elseif  priority == "High">
                                            <#assign cpPriorityAlert = "HIGH">
                                        <#elseif priority == "Low">
                                            <#assign cpPriorityAlert = "LOW">
                                        </#if>

                                        <#if cpPriorityAlert?? && cpPriorityAlert?has_content>
                                        <span id="h-prt" class="psAlert">${cpPriorityAlert}</span>
                                    </#if>
                                    </#if>
                                    </div>
                                </div>

                            <div class="d-none d-md-block col-4"></div>  <#--Padding col element to align things properly-->
                         </div>
                    </#if>

                    <#--Urgency-->
                    <hr/>
                    <div class="row">
                        <div class="col-4">Urgency: </div>
                        <#if urgent?has_content && urgent == "Yes">
                            <div class="col-4 font-weight-bold"><span class="urgAlert" id="ugt">URGENT</span></div>
                        <#else>
                            <div class="col-4 font-weight-bold">Standard</div>
                        </#if>
                        <div class="col-4"></div>
                    </div>

                    <#--Consumers list-->
                    <#if consumerTypesCP??  && consumerTypesCP.getData() != "Empty">
                        <hr/>
                        <div class="row">
                            <div class="col-4">Consumers: </div>
                            <div class="col col-md-4 font-weight-bold">
                                <div>${consumerTypesCP.getData()}</div>
                            </div>
                            <div class="col-0 col-md-4"></div> <#--Padding col element to align things properly-->
                        </div>

                        <#--  IN CASE IT IS PREFERRED AS A LIST IN SEPARATE LINES
                        <#assign cpConsumersList = consumerTypesCP.getData()?split(",")>
                        <div class="row"><div class="col-4">Consumers: </div>
                            <div class="col-4 font-weight-bold">
                                <#list cpConsumersList as cpConsumerType>
                                    <div>${cpConsumerType?trim}</div>
                                </#list>
                            </div>
                        </div>
                        -->
                    </#if>

                    <#--Fuel-->
                    <#if fuelCP??  && fuelCP.getData() != "Empty">
                        <hr/>
                        <div class="row">
                            <div class="col-4">Fuel: </div>
                            <div class="col-4 font-weight-bold">${fuelCP.getData()}</div>
                            <div class="col-4"></div>
                        </div>
                    </#if>

                    <#--Impacted stakeholders list-->
                    <#if impactedSegmentsISCP??  && impactedSegmentsISCP.getData() != "Empty">
                        <hr/>
                        <div class="row">
                            <div class="col-4">Impacted Parties:</div>
                            <div class="col col-md-4 font-weight-bold">
                                <div>${impactedSegmentsISCP.getData()}</div>
                            </div>
                            <div class="col-0 col-md-4"></div>
                        </div>

                        <#-- IN CASE IT IS PREFERRED AS A LIST IN SEPARATE LINES
                        <#assign cpImpactedPartiesList = impactedSegmentsISCP.getData()?split(",")>
                        <div class="row"><div class="col-4">Impacted Parties: </div>
                            <div class="col-4 font-weight-bold">
                                <#list cpImpactedPartiesList as cpImpactedPartyName>
                                    <div>${cpImpactedPartyName?trim}</div>
                                </#list>
                            </div>
                            <div class="col-4"></div>
                        </div>
                        -->
                    </#if>
                </div>
            </div>

            <div id="documents-att">
                <#if recFormArticleService.isAlternative(articleResourcePrimKey)>
                    <#assign formIdAlternative = recFormArticleService.getFormIdByArticleId(articleResourcePrimKey) />
                    <#assign formIdOriginal = recFormArticleService.getParentAlternativeFormId(articleResourcePrimKey) />
                    <#if formIdOriginal != 0 >
                        <#if recFormArticleService.fetchrecFormArticle(formIdOriginal)??>
                            <#assign recFormArticleObject = recFormArticleService.fetchrecFormArticle(formIdOriginal)/>
                            <#assign alternativeFormIdsString = recFormArticleObject.getAlternativeFormIds() />
                            <h3 class="alternativeDelimiter hide">Links to Alternative</h3>
                            <#list alternativeFormIdsString?split(",") as eachAlternativeFormId>
                                <#if formIdAlternative == eachAlternativeFormId?number>
                                    <#continue>
                                </#if>
                                <#if recFormArticleService.fetchrecFormArticle(eachAlternativeFormId?number)??>
                                    <#assign alternativeRecFormArticleObject = recFormArticleService.fetchrecFormArticle(eachAlternativeFormId?number)/>
                                    <#assign alternativeArticleId = alternativeRecFormArticleObject.getArticleId() />
                                    <#if journalArticleService.fetchLatestArticle(alternativeArticleId)??>
                                        <#assign alternativeArticle = journalArticleService.fetchLatestArticle(alternativeArticleId) />
                                        <#assign alternativeURLTitle = alternativeArticle.getUrlTitle() />
                                        <#assign alternativeTitle = alternativeArticle.getTitle() />
                                        <img class="alternative-link" src="${themeImgsSource}/forms/expand-green.svg">
                                        <a href="/group/guest/-/${alternativeURLTitle}" rel="link">${alternativeTitle}</a>
                                        <br>
                                        <script>
                                            $(".alternativeDelimiter").removeClass("hide");
                                        </script>
                                    <#else>
                                        <script>
                                            console.log("alternativeFormId fails when getting journal article");
                                        </script>
                                    </#if>
                                <#else>
                                    <script>
                                        console.log("alternativeFormId fails when getting REC form article");
                                    </script>
                                </#if>
                            </#list>
                            <br class="alternativeDelimiter hide">
                            <h3>Link to Original</h3>
                            <#if LinkToOriginalCP.getData() == "">
                                <#assign parentArticleId = recFormArticleObject.getArticleId() />
                                <#if journalArticleService.fetchLatestArticle(parentArticleId)??>
                                    <#assign parentArticle = journalArticleService.fetchLatestArticle(parentArticleId) />
                                    <#assign parentURLTitle = "/group/guest/-/" + parentArticle.getUrlTitle() />
                                <#else>
                                    <script>
                                        console.log("Link to Original fails when getting journal article");
                                    </script>
                                    <#assign parentURLTitle = LinkToOriginalCP.getData() />
                                </#if>
                            <#else>
                                <#assign parentURLTitle = LinkToOriginalCP.getData() />
                            </#if>
                            <img class="alternative-link" src="${themeImgsSource}/forms/expand-green.svg">
                            <a href="${parentURLTitle}" rel="link">CP Original</a>
                        </#if>
                    </#if>
                <#else>
                    <#assign formIdOriginal = recFormArticleService.getFormIdByArticleId(articleResourcePrimKey) />
                    <#if formIdOriginal != 0 >
                        <#if recFormArticleService.fetchrecFormArticle(formIdOriginal)??>
                            <#assign recFormArticleObject = recFormArticleService.fetchrecFormArticle(formIdOriginal)/>
                            <#assign alternativeFormIdsString = recFormArticleObject.getAlternativeFormIds() />
                            <#if alternativeFormIdsString != "">
                                <h3 class="alternativeDelimiter2 hide">Links to Alternative</h3>
                                <#list alternativeFormIdsString?split(",") as eachAlternativeFormId>
                                    <#if recFormArticleService.fetchrecFormArticle(eachAlternativeFormId?number)??>
                                        <#assign alternativeRecFormArticleObject = recFormArticleService.fetchrecFormArticle(eachAlternativeFormId?number)/>
                                        <#assign alternativeArticleId = alternativeRecFormArticleObject.getArticleId() />
                                        <#if journalArticleService.fetchLatestArticle(alternativeArticleId)??>
                                            <#assign alternativeArticle = journalArticleService.fetchLatestArticle(alternativeArticleId) />
                                            <#assign alternativeURLTitle = alternativeArticle.getUrlTitle() />
                                            <#assign alternativeTitle = alternativeArticle.getTitle() />
                                            <img class="alternative-link" src="${themeImgsSource}/forms/expand-green.svg">
                                            <a href="/group/guest/-/${alternativeURLTitle}" rel="link">${alternativeTitle}</a>
                                            <br>
                                            <script>
                                                $(".alternativeDelimiter2").removeClass("hide");
                                            </script>
                                        </#if>
                                    <#else>
                                        <script>
                                            console.log("alternativeFormId fails");
                                        </script>
                                    </#if>
                                </#list>
                            </#if>
                        <#else>
                            <script>
                                console.log("alternativeFormId fails while getting recFormArticle");
                            </script>
                        </#if>
                    </#if>
                </#if>

                <#-- ACCOMPANYING DOCUMENTS -->
                <#assign documentsEmpty = 0 >
                <h3>Accompanying Documents</h3>
                <ul>
                    <#if InitialAssessment.AttachDocuments.getData()?has_content>
                        <#if InitialAssessment.AttachDocuments.getSiblings()?has_content>
                            <#list InitialAssessment.AttachDocuments.getSiblings() as cur_InitialAssessment_AttachDocuments>
                                <#assign title = cur_InitialAssessment_AttachDocuments.DocumentTitle.getData()/>
                                <#if title?has_content && title??>
                                    <li>
                                        <a href="${cur_InitialAssessment_AttachDocuments.getData()}">${languageUtil.format(locale, "download-x", cur_InitialAssessment_AttachDocuments.DocumentTitle.getData(), false)} </a>
                                    </li>
                                <#else>
                                    <li>
                                        <a href="${cur_InitialAssessment_AttachDocuments.getData()}">${languageUtil.format(locale, "download-x", "Attached Document", false)} </a>
                                    </li>
                                </#if>
                            </#list>
                            <#assign documentsEmpty = 1 >
                        </#if>
                    </#if>

                    <#if InitialAssessment.CP_InitialAssessmentReport.getData() != "">
                        <li>
                            <a href="${InitialAssessment.CP_InitialAssessmentReport.getData()}">
                                ${languageUtil.format(locale, "download-x", "Initial Assessment Report", false)}
                            </a>
                        </li>
                        <#assign documentsEmpty = 1 >
                    </#if>

                    <#if PreliminaryChangeReport.getData()?has_content>
                        <li>
                            <a href="${PreliminaryChangeReport.getData()}">
                                ${languageUtil.format(locale, "download-x", "Preliminary Change Report", false)}
                            </a>
                        </li>
                        <#assign documentsEmpty = 1 >
                    </#if>

                    <#if FinalChangeReport.getData()?has_content>
                        <li>
                            <a href="${FinalChangeReport.getData()}">
                                ${languageUtil.format(locale, "download-x", "Final Change Report", false)}
                            </a>
                        </li>
                        <#assign documentsEmpty = 1 >
                    </#if>

                    <#if documentsEmpty == 0>
                        <li> Attached Documents: none </li>
                    </#if>
                </ul>
                <#-- END ACCOMPANYING DOCUMENTS -->
            </div>

             <#--Related links section-->
            <#if relatedTitleLinkCP?? && relatedTitleLinkCP.getSiblings()?size != 0>
                <#assign atLeastOneLink = false>
                <#--**first we check whether a valid link(title/url) is present**-->
                <#list relatedTitleLinkCP.getSiblings() as relatedTitle>
                    <#if relatedTitle.relatedUrlLinkCP?has_content && relatedTitle.relatedUrlLinkCP.getData()?trim != "" && relatedTitle.getData()?trim != "">
                        <#assign atLeastOneLink = true>
                    </#if>
                </#list>

                <#if atLeastOneLink>
                    <div id="related-links" class="card mr-md-5">
                        <div class="card-header">Related Links/Documents</div>
                        <#--relatedUrlLinkCP & -->
                        <div class="card-body">
                                <#list relatedTitleLinkCP.getSiblings() as relatedTitle>
                                    <#if relatedTitle.relatedUrlLinkCP?has_content && relatedTitle.relatedUrlLinkCP.getData()?trim != "" && relatedTitle.getData()?trim != "">
                                        <#assign link =  relatedTitle.relatedUrlLinkCP.getData()?trim />
                                        <div class="relatedLink"><a href="${link}" target="_blank" rel="noopener noreferrer">${relatedTitle.getData()?trim}</a></div>
                                    </#if>
                                </#list>
                        </div>
                    </div>
                </#if>
            </#if>

        </div>

		<div id="content-right" class="col-md-8">
            <#assign SolutionC1 = InitialAssessment.ChangeRequirements.SolutionRequirements.getData() />
            <#assign SolutionC2 = InitialAssessment.ChangeRequirements.SolutionRequirements2.getData() />
            <#assign SolutionC3 = InitialAssessment.ChangeRequirements.SolutionRequirements3.getData() />

            <#assign LatestUpdate_value = "" />
            <#assign ChangeSummary_value = "" />

            <#if LatestUpdate??>
                <#assign LatestUpdate_value = LatestUpdate.getData() />
            </#if>

            <#if ChangeSummary??>
                <#assign ChangeSummary_value = ChangeSummary.getData() />
            </#if>

            <#if LatestUpdate_value?has_content && LatestUpdate_value != "Empty">
                <div id="accordion-latest-update">
                    <div class="card">
                        <div class="card-header" id="heading-latest-update">
                            <h5 class="mb-0">
                                <button class="btn btn-link edit" data-toggle="collapse" data-target="#collapse-latest-update" aria-expanded="true" aria-controls="collapse-latest-update">
                                    Latest Update
                                </button>
                                <#if showEditLUButton==1>
                                    <div class="edit-link">
                                        <a id="pop-lu" href="#view-latest-update-popup">
                                            <img src="${images_folder}/forms/pencil-black.svg">
                                            <span>Edit</span>
                                        </a>
                                    </div>
                                </#if>
                            </h5>
                        </div>

                        <div id="collapse-latest-update" class="collapse show" aria-labelledby="heading-latest-update" data-parent="#accordion-latest-update">
                            <div class="card-body">
                                ${LatestUpdate_value}
                            </div>
                        </div>
                    </div>
                </div>
            </#if>

            <#if ChangeSummary_value?has_content  && ChangeSummary_value != "Empty">
                <div id="accordion-change-summary">
                    <div class="card">
                        <div class="card-header" id="heading-change-summary">
                            <h5 class="mb-0">
                                <button class="btn btn-link edit" data-toggle="collapse" data-target="#collapse-change-summary" aria-expanded="true" aria-controls="collapse-change-summary">
                                    Change Summary
                                </button>
                                <#if showEditCSButton==1>
                                    <div class="edit-link">
                                        <a id="pop-cs" href="#view-change-summary-popup">
                                            <img src="${images_folder}/forms/pencil-black.svg">
                                            <span>Edit</span>
                                        </a>
                                    </div>
                                </#if>
                            </h5>
                        </div>

                        <div id="collapse-change-summary" class="collapse show" aria-labelledby="heading-change-summary" data-parent="#accordion-change-summary">
                            <div class="card-body">
                                ${ChangeSummary_value}
                            </div>
                        </div>
                    </div>
                </div>
            </#if>

            <#if InitialAssessment.ChangeRequirements.ProblemStatement?has_content >
                <div id="accordion">							
                    <div class="card">
                        <div class="card-header" id="headingOne">
                            <h5 class="mb-0">
                                <button class="btn btn-link edit" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                    Problem Statement
                                </button>
                                <#if showButton==1>
                                    <div class="edit-link">
                                        <a  id="pop" href="#viewSolutions">
                                            <img src="${images_folder}/forms/pencil-black.svg">
                                            <span >Edit</span>
                                        </a>
                                    </div>
                                </#if>
                            </h5>
                        </div>

                        <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordion">
                            <div class="card-body">
                                ${InitialAssessment.ChangeRequirements.ProblemStatement.getData()}
                            </div>
                        </div>
                    </div>
                </div>
            </#if>
                            							
            <div id="accordion2">
                <div class="card">
                    <div class="card-header" id="headingTwo">
                        <h5 class="mb-0">
                            <button class="btn btn-link edit collapsed" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                    Solution Requirement
                            </button>
                        </h5>
                    </div>

                    <div id="collapseTwo" class="collapse show" aria-labelledby="headingTwo" data-parent="#accordion2">
                        <div class="card-body">
                            <div id="accordion3">
                                <#if SolutionC1 !="" || SolutionC2 !="" || SolutionC3 !="">
                                    <div id="c1" class="card">
                                        <div class="card-header" id="headingThree">
                                            <h5 class="mb-0">
                                                <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                                    <span class="identity-number">1</span>Solution Requirement One
                                                    <img src="${arrow}"/>
                                                </button>
                                            </h5>
                                        </div>
                                        <div id="collapseThree" class="collapse show" aria-labelledby="headingThree" data-parent="#accordion3">
                                            <div id="c1-cb" class="card-body">
                                                ${SolutionC1}
                                            </div>
                                        </div>
                                    </div>
                                </#if>
                                <#if SolutionC2 !="" || SolutionC3 !="">
                                    <div id="c2" class="card">
                                        <div class="card-header" id="headingFour">
                                            <h5 class="mb-0">
                                                <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                                                    <span class="identity-number">2</span>Solution Requirement Two
                                                    <img src="${arrow}"/>
                                                </button>
                                            </h5>
                                        </div>
                                        <div id="collapseFour" class="collapse" aria-labelledby="headingFour" data-parent="#accordion3">
                                            <div id="c2-cb" class="card-body">
                                                ${SolutionC2}
                                            </div>
                                        </div>
                                    </div>
                                </#if>
                                <#if SolutionC3 !="">
                                    <div id="c3" class="card">
                                        <div class="card-header" id="headingFive">
                                            <h5 class="mb-0">
                                                <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
                                                    <span class="identity-number">3</span>Solution Requirement Three
                                                    <img src="${arrow}"/>
                                                </button>
                                            </h5>
                                        </div>
                                        <div id="collapseFive" class="collapse" aria-labelledby="headingFive" data-parent="#accordion3">
                                            <div id="c3-cb" class="card-body">
                                                ${SolutionC3}
                                            </div>
                                        </div>
                                    </div>
                                </#if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Final accordion2-->

            <div id="accordion4">
                <div class="card">
                    <!-- Inicio encabezado accordion4-->
                    <div class="card-header" id="headingEight">
                        <h5 class="mb-0">
                            <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseEight" aria-expanded="false" aria-controls="collapseEight">
                                Proposed Solutions
                            </button>
                        </h5>
                    </div>

                    <div id="collapseEight" class="collapse show" aria-labelledby="headingEight" data-parent="#accordion4">
                        <div class="card-body">
                            <div id="accordion5">
                                <!-- Inicio repetible accordion4-->
                                <#assign countProSol = 1>
                                <#if TitleSolutionOptions?has_content && TitleSolutionOptions.getData() != "">
                                    <#list TitleSolutionOptions.getSiblings() as cur_TitleSolutionOptions>
                                        <div class="card">
                                            <div class="card-header" id="heading${countProSol}">
                                                <h5 class="mb-0">
                                                    <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapse${countProSol}" aria-expanded="false" aria-controls="collapse${countProSol}">
                                                        <span class="identity-number">${countProSol}</span>${cur_TitleSolutionOptions.getData()}
                                                        <img src="${arrow}"/>
                                                    </button>
                                                </h5>
                                            </div>
                                            <div id="collapse${countProSol}" class="collapse show" aria-labelledby="heading${countProSol}" data-parent="#accordion5">
                                                <div class="TitleSolution">
                                                    <#if cur_TitleSolutionOptions.SolutionOptions?has_content && cur_TitleSolutionOptions.SolutionOptions.getData() != "">
                                                        <#list cur_TitleSolutionOptions.SolutionOptions.getSiblings() as cur_TitleSolutionOptions_SolutionOptions>
                                                            ${cur_TitleSolutionOptions_SolutionOptions.getData()}
                                                        </#list>
                                                    </#if>
                                                </div>
                                                <div class="card-body container-fluid">
                                                    <div class="row">
                                                        <div class="col-md-6">
                                                            <h3>Downloads</h3>
                                                            <ul class="downloads">
                                                                <#if cur_TitleSolutionOptions.DocAndMedSoluction_option_doc_med?has_content && cur_TitleSolutionOptions.DocAndMedSoluction_option_doc_med.getData() !="">
                                                                    <#list cur_TitleSolutionOptions.DocAndMedSoluction_option_doc_med.getSiblings() as cur_TitleSolutionOptions_DocAndMedSoluction_option_doc_med>
                                                                        <li>
                                                                            <#if cur_TitleSolutionOptions_DocAndMedSoluction_option_doc_med.CP_SolutionOption_DocumentTitle.getData()??>
                                                                                <#assign proposedSolDocumentTitle = cur_TitleSolutionOptions_DocAndMedSoluction_option_doc_med.CP_SolutionOption_DocumentTitle.getData() />
                                                                                <a href="${cur_TitleSolutionOptions_DocAndMedSoluction_option_doc_med.getData()}">
                                                                                    ${languageUtil.format(locale, "download-x", "${proposedSolDocumentTitle}", false)}
                                                                                </a>
                                                                            <#else>
                                                                                <a href="${cur_TitleSolutionOptions_DocAndMedSoluction_option_doc_med.getData()}">
                                                                                    ${languageUtil.format(locale, "download-x", "Document", false)}
                                                                                </a>
                                                                            </#if>
                                                                        </li>
                                                                    </#list>
                                                                </#if>
                                                            </ul>
                                                            </div>
                                                        <div class="col-md-6">
                                                            <h3>Links</h3>
                                                            <ul class="links">
                                                                <#if cur_TitleSolutionOptions.Solution_option_link?has_content && cur_TitleSolutionOptions.Solution_option_link.getData() !="">
                                                                    <#list cur_TitleSolutionOptions.Solution_option_link.getSiblings() as cur_TitleSolutionOptions_Solution_option_link>
                                                                        <#assign linkSolution = cur_TitleSolutionOptions_Solution_option_link.getData() />
                                                                        <li>
                                                                            <#if cur_TitleSolutionOptions_Solution_option_link.CP_SolutionOption_LinkTitle.getData() != "">
                                                                                <#assign proposedSolLinkTitle = cur_TitleSolutionOptions_Solution_option_link.CP_SolutionOption_LinkTitle.getData() />
                                                                                <a href="${linkSolution}" target="_blank">${proposedSolLinkTitle}</a>
                                                                            <#else>
                                                                                <a href="${linkSolution}" target="_blank">${linkSolution}</a>
                                                                            </#if>
                                                                        </li>
                                                                    </#list>
                                                                </#if>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <#assign countProSol = countProSol + 1>
                                    </#list>
                                </#if>
                                <!-- Final repetible accordion4-->
                            </div>
                            <!-- Final encabezado accordion4-->
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Final accordion4-->
    </div>
							
    <#-- COMMENTS SECTION -->
    <#-- INITIAL ASSESSMENT REPORT -->
    <#if InitialAssessment.CP_InitialAssessmentReport.getData() != "">
        <#assign InitialAssessment_CP_InitialAssessmentReport_IARCommentsDeadline_Data = getterUtil.getString(InitialAssessment.CP_InitialAssessmentReport.IARCommentsDeadline.getData())>

        <#if validator.isNotNull(InitialAssessment_CP_InitialAssessmentReport_IARCommentsDeadline_Data)>
            <#assign iarCommentsDeadline = dateUtil.parseDate("yyyy-MM-dd", InitialAssessment_CP_InitialAssessmentReport_IARCommentsDeadline_Data, locale)?date>

            <#assign currentDateString = dateUtil.getCurrentDate("yyyy-MM-dd", locale)>
            <#assign currentDate = dateUtil.parseDate("yyyy-MM-dd", currentDateString, locale)?date>
            <#assign compare = dateUtil.compareTo(iarCommentsDeadline, currentDate) />
            <p> Deadline to provide comments for Initial Assessment Report: <b>${iarCommentsDeadline}</b> </p>

            <#if compare == -1 || compare == 0 || compare == 1 >
                <div class="IARCustomComments">
                    <#if recFormArticleService.isCProposalUserValid(userId,"Initial Change Proposal")>
                        <input class="AddEntry hide" type="button" value="Add Comment" onclick="addIACommentEntry()" data="${ddlInitialCommentId}" name="AddIACommentEntry"/>
                        <#assign cpTitle = article.getUrlTitle() />
                        <#assign url = "/group/guest/~/control_panel/manage?p_p_id=com_liferay_dynamic_data_lists_web_portlet_DDLPortlet&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_mvcPath=%2Fedit_record.jsp&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_redirect=/group/guest/-/" + cpTitle + "&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_recordSetId=" + ddlInitialCommentId + "&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_formDDMTemplateId=0"/>
                        <#if compare == 0 || compare == 1>
                            <a class="AddEntry" href="${url}">Add Comment</a>
                        </#if>
                    </#if>
                    <#assign preferencesIAR = "recordSetId=" + ddlInitialCommentId + "&customSearch=customSearch" />
                    <@liferay_portlet["runtime"]
                        instanceId="initial${groupId}"
                        portletName="com_liferay_dynamic_data_lists_web_portlet_DDLDisplayPortlet"
                        queryString=preferencesIAR
                    />
                </div>
            <#elseif ImpactTabEditable == 1>
                <div class="IARCustomComments">
                    <#assign preferencesIAR = "recordSetId=" + ddlInitialCommentId />
                    <@liferay_portlet["runtime"]
                        portletName="com_liferay_dynamic_data_lists_web_portlet_DDLDisplayPortlet"
                        queryString=preferencesIAR
                    />
                </div>
            </#if>
        </#if>
    </#if>

    <#-- PRELIMINARY CHANGE REPORT -->
    <#if PreliminaryChangeReport.getData() != "">        							
        <#assign PreliminaryChangeReport_PCRCommentsDeadline_Data = getterUtil.getString(PreliminaryChangeReport.PCRCommentsDeadline.getData())>							
        							
        <#if validator.isNotNull(PreliminaryChangeReport_PCRCommentsDeadline_Data)>							
        	<#assign pcrCommentsDeadline = dateUtil.parseDate("yyyy-MM-dd", PreliminaryChangeReport_PCRCommentsDeadline_Data, locale)?date>
        	<#assign currentDateString = dateUtil.getCurrentDate("yyyy-MM-dd", locale)>						
        	<#assign currentDate = dateUtil.parseDate("yyyy-MM-dd", currentDateString, locale)?date>						
        	<#assign compare = dateUtil.compareTo(pcrCommentsDeadline, currentDate) />
			<p> Deadline to provide comments for Preliminary Change Report: <b>${pcrCommentsDeadline}</b> </p>

            <#if compare == -1 || compare == 0 || compare == 1 >
                <div class="PCRCustomComments">
                    <#if recFormArticleService.isCProposalUserValid(userId,"Preliminary Change Proposal")>
                        <input class="AddEntry hide" type="button" value="Add Comment" onclick="addPRCommentEntry()" data="${ddlPreliminaryCommentId}" name="AddPRCommentEntry"/>
                        <#assign cpTitle = article.getUrlTitle() />
                        <#assign url = "/group/guest/~/control_panel/manage?p_p_id=com_liferay_dynamic_data_lists_web_portlet_DDLPortlet&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_mvcPath=%2Fedit_record.jsp&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_redirect=/group/guest/-/" + cpTitle + "&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_recordSetId=" + ddlPreliminaryCommentId + "&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_formDDMTemplateId=0"/>
                        <#if compare == 0 || compare == 1>
                            <a class="AddEntry" href="${url}">Add Comment</a>	
                        </#if>					
                    </#if>
                    <#assign preferencesPCR = "recordSetId=" + ddlPreliminaryCommentId + "&customSearch=customSearch" />
                    <@liferay_portlet["runtime"]
                        portletName="com_liferay_dynamic_data_lists_web_portlet_DDLDisplayPortlet"
                        instanceId="preliminary${groupId}"
                        queryString=preferencesPCR
                    />
                </div>
            <#elseif ImpactTabEditable == 1>
                <div class="PCRCustomComments">
                    <#assign preferencesPCR = "recordSetId=" + ddlPreliminaryCommentId />
                    <@liferay_portlet["runtime"]
                        portletName="com_liferay_dynamic_data_lists_web_portlet_DDLDisplayPortlet"
                        queryString=preferencesPCR
                    />
                </div>
            <#elseif addComments == 1>
                <div class="PCRCustomComments">
                    <#assign preferencesPCR = "recordSetId=" + ddlPreliminaryCommentId + "&customSearch=customSearch" />
                    <@liferay_portlet["runtime"]
                        portletName="com_liferay_dynamic_data_lists_web_portlet_DDLDisplayPortlet"
                        queryString=preferencesPCR
                    />
                </div>
            </#if>
        </#if>							
    </#if>

    <#-- FINAL CHANGE REPORT  -->
    <#if FinalChangeReport.getData() != "">
        <#assign FinalChangeReport_FCRCommentsDeadline_Data = getterUtil.getString(FinalChangeReport.FCRCommentsDeadline.getData())>							

        <#if validator.isNotNull(FinalChangeReport_FCRCommentsDeadline_Data)>							
        	<#assign fcrCommentsDeadline = dateUtil.parseDate("yyyy-MM-dd", FinalChangeReport_FCRCommentsDeadline_Data, locale)?date>
        	<#assign currentDateString = dateUtil.getCurrentDate("yyyy-MM-dd", locale)>						
        	<#assign currentDate = dateUtil.parseDate("yyyy-MM-dd", currentDateString, locale)?date>
			<#assign compareF = dateUtil.compareTo(fcrCommentsDeadline, currentDate) />
			<p> Deadline to provide comments for Final Change Report: <b>${fcrCommentsDeadline}</b> </p>
            <#if compareF == -1 || compareF == 0 || compareF == 1 >
				<div class="FCRCustomComments">			
					<#if recFormArticleService.isCProposalUserValid(userId,"Final Change Proposal")>
                        <input class="AddEntry hide" type="button" value="Add Comment" onclick="addFRCommentEntry()" data="${ddlFinalCommentId}" name="AddFRCommentEntry"/>
                        <#assign cpTitle = article.getUrlTitle() />
                        <#assign url = "/group/guest/~/control_panel/manage?p_p_id=com_liferay_dynamic_data_lists_web_portlet_DDLPortlet&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_mvcPath=%2Fedit_record.jsp&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_redirect=/group/guest/-/" + cpTitle + "&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_recordSetId=" + ddlFinalCommentId + "&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_formDDMTemplateId=0"/>
                        <#if compareF == 0 || compareF == 1>
                            <a class="AddEntry" href="${url}">Add Comment</a>
                        </#if>
                    </#if>
					<#assign preferencesFCR = "recordSetId=" + ddlFinalCommentId + "&customSearch=customSearch" />		
					<@liferay_portlet["runtime"]
                        instanceId="final${groupId}"		
						portletName="com_liferay_dynamic_data_lists_web_portlet_DDLDisplayPortlet"	
						queryString=preferencesFCR	
					/>		
				</div>	
			<#elseif ImpactTabEditable == 1>				
				<div class="FCRCustomComments">			
					<#assign preferencesFCR = "recordSetId=" + ddlFinalCommentId />		
					<@liferay_portlet["runtime"]		
						portletName="com_liferay_dynamic_data_lists_web_portlet_DDLDisplayPortlet"	
						queryString=preferencesFCR	
					/>		
				</div>
			<#elseif addComments == 1>
			    <div class="FCRCustomComments">			
					<#assign preferencesFCR = "recordSetId=" + ddlFinalCommentId + "&customSearch=customSearch" />		
					<@liferay_portlet["runtime"]		
						portletName="com_liferay_dynamic_data_lists_web_portlet_DDLDisplayPortlet"	
						queryString=preferencesFCR	
					/>		
				</div>
            </#if>
        </#if>							
    </#if>
</div>

<div id="change-proposal-pop-up1" class="popup1">
    <div class="popupp">
        <a class="close" onclick=closePopUp()>&times;</a>
        <div class= "userInfo">
            <img class= "userImg" src= userImg /> <#-- "${theme_url}" + -->
            <div class="userDetails">
                <h1 class="userFullName"> userName </h1>
                <h3 class="userCompany"> userCompany </h3>
                <h4 class="userCompanyType"> userCompanyType </h4>
                <div class="phone" id="phoneImgId">
                    <img class=phoneImg id="phoneImgId" src="${images_folder}/forms/phone.svg"/>
                    <div class= "userPhone">  userPhone</div>
                </div>
                <div class="email" id="emailImgId">
                    <img id="emailImgId" src="${images_folder}/forms/envelope.svg"/>
                    <div class= "userEmail"> userEmail </div>
                </div>
                <div class="userDescription"> userDescription </div>
            </div>
        </div>
    </div>
</div>
      							
<div id="viewSolutions" class="overlaySolutions">							
	<div class="popupSolutions">						
		<h2>Edit Problem Statement/Solution Requirements</h2>					
        <a class="close" href="#">&times;</a>
        <hr>
		<div class="content-label">Problem Statement</div>					
        <div class="block_problem">
            <div id="block_problem" class="content-placeholder">
                <input
                id="problemId"
                type="text"
                placeholder="Enter problem statement"
                name="statement"
                required
                value="${textProblem}"
                />
            </div>
        </div>
        <hr><!-- Popup ViewSolutions-->
							
        <div class="block_requirement sr1">
            <div class="content-label">Solution Requirement One</div>
            <div class="content-placeholder">
                <textarea
                id="solutionId"
                type="text"
                name="solution"
                required
                >${SolutionC1}</textarea>
            </div>
        </div>
							
        <div class="block_requirement sr2">
            <div class="content-label">Solution Requirement Two</div>
            <div class="content-placeholder">
                <textarea
                id="solutionId2"
                type="text"
                name="solution"
                required
                >${SolutionC2}</textarea>
            </div>
        </div>
							
        <div id="sr3" class="block_requirement sr3">
            <div class="content-label">Solution Requirement Three</div>
            <div class="content-placeholder">
                <textarea
                id="solutionId3"
                type="text"
                name="solution"
                required
                >${SolutionC3}</textarea>
            </div>
        </div>
        <hr>

        <button id="updateButton" href="#" class="float-right lfr-ddm-form-pagination-next btn btn-primary" onclick="updateButton()">Update
        </button>
        <button id="moreButton" class="more-less float-right lfr-ddm-form-pagination-next btn btn-primary">+
        </button>
        <button id="lessButton" class="more-less float-right lfr-ddm-form-pagination-next btn btn-primary">-
        </button>
	</div>						
</div>

<div id="view-change-summary-popup" class="overlaySolutions">
	<div class="popupChangeSummary">
		<h2>Edit Change Summary</h2>
        <a class="close" href="#">&times;</a>
        <hr>
        <div class="content-label">
            Change Summary
        </div>
        <div class="block_change_summary">
            <div id="block_change_summary" class="content-placeholder">
                <textarea
                id="changeSummaryId"
                type="text"
                name="changeSummaryId"
                required
                >${ChangeSummary_value}</textarea>
            </div>
            <p id="change-summary-error" style="display: none; color: red;">ERROR - Change Summary must be filled out</p>
        </div>
        <hr>
        <button id="updateCSButton" href="#" class="float-right lfr-ddm-form-pagination-next btn btn-primary">Update</button>
	</div>
</div>

<div id="view-latest-update-popup" class="overlaySolutions">
	<div class="popupLatestUpdate">
		<h2>Edit Latest Update</h2>
        <a class="close" href="#">&times;</a>
        <hr>
        <div class="content-label">
            Latest Update
        </div>
        <div class="block_latest_update">
            <div id="block_latest_update" class="content-placeholder">
                <textarea
                id="latestUpdateId"
                type="text"
                name="latestUpdateId"
                required
                >${LatestUpdate_value}</textarea>
            </div>
            <p id="latest-update-error" style="display: none; color: red;">ERROR - Latest Update must be filled out</p>
        </div>
        <hr>
        <button id="updateLUButton" href="#" class="float-right lfr-ddm-form-pagination-next btn btn-primary">Update</button>
	</div>
</div>

<!-- Function Popup ViewSolutions-->							
<script>
    $(document).ready(function(){
        $('#pop').click(function(e) {
            var SolutionC1 = $("#solutionId").val();
            var SolutionC2 = $("#solutionId2").val();
            var SolutionC3 = $("#solutionId3").val();

            $('.sr1').css('display', 'none'); 							
            $('.sr2').css('display', 'none');							
            $('.sr3').css('display', 'none');							

            if (SolutionC3 != "") {
                $('.sr3').css('display', 'block');
                $('.sr2').css('display', 'block');
                $('.sr1').css('display', 'block');
            } else if (SolutionC2 != "") {
                $('.sr2').css('display', 'block');
                $('.sr1').css('display', 'block');
            } else if (SolutionC1 != "") {
                $('.sr1').css('display', 'block');
            }
        });

        <#if hasRecFormArticle>
            <#if impacts?contains(",")>
                <#list impacts?split(",") as ipctId>
                    $(".impact-checkbox-${ipctId}").prop('checked', true);
                </#list>
            <#else>
                $(".impact-checkbox-${impacts}").prop('checked', true);
            </#if>
        </#if>
    });
</script>							
							
<script>							
    $('#moreButton').click(function(e) {  							
        if ($('.sr1').is(":hidden")) {
            $('.sr1').css('display', 'block'); 							
        } else if ($('.sr2').is(":hidden")) {
            $('.sr2').css('display', 'block'); 							
        } else if ($('.sr3').is(":hidden")) {
            $('.sr3').css('display', 'block'); 							
        }							
    });							
							
    $('#lessButton').click(function(e) {
        if ($('.sr3').is(":visible")) {
            $('.sr3').css('display', 'none');							
            $("#solutionId3").html("");							
        } else if ($('.sr2').is(":visible")) {
            $('.sr2').css('display', 'none');							
            $("#solutionId2").html("");							
        } else if ($('.sr1').is(":visible")) {
            $('.sr1').css('display', 'none');							
            $("#solutionId").html(""); 							
        }							
    });							
</script>							
							
<#assign journalArticleId = .vars['reserved-article-id'].data/>							
<#assign companyId= themeDisplay.getCompanyId() />							
<#assign groupId= themeDisplay.getLayout().getGroupId() />							
<#assign currentEmail = user.getEmailAddress() />
<#assign textProblem = InitialAssessment.ChangeRequirements.ProblemStatement.getData() />							
<#assign textSolution = InitialAssessment.ChangeRequirements.SolutionRequirements.getData() />							
							
<script>
    function addMilestoneEntry() {
        let nameOfFunction = this[event.target.name];
        let ddlRecordSetId = event.target.getAttribute('data');
        let pathname = window.location.pathname;
        let url = '/group/guest/~/control_panel/manage?p_p_id=com_liferay_dynamic_data_lists_web_portlet_DDLPortlet&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_mvcPath=%2Fedit_record.jsp&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_redirect=' + pathname + '&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_recordSetId=' + ddlRecordSetId + '&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_formDDMTemplateId=0';
        window.location.href = url;
    }

    function addIACommentEntry() {
        let nameOfFunction = this[event.target.name];
        let ddlRecordSetId = event.target.getAttribute('data');
        let pathname = window.location.pathname;
        let url = '/group/guest/~/control_panel/manage?p_p_id=com_liferay_dynamic_data_lists_web_portlet_DDLPortlet&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_mvcPath=%2Fedit_record.jsp&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_redirect=' + pathname + '&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_recordSetId=' + ddlRecordSetId + '&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_formDDMTemplateId=0';
        window.location.href = url;
    }

    function addPRCommentEntry() {
        let nameOfFunction = this[event.target.name];
        let ddlRecordSetId = event.target.getAttribute('data');
        let pathname = window.location.pathname;
        let url = '/group/guest/~/control_panel/manage?p_p_id=com_liferay_dynamic_data_lists_web_portlet_DDLPortlet&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_mvcPath=%2Fedit_record.jsp&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_redirect=' + pathname + '&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_recordSetId=' + ddlRecordSetId + '&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_formDDMTemplateId=0';
        window.location.href = url;
    }

    function addFRCommentEntry() {
        let nameOfFunction = this[event.target.name];
        let ddlRecordSetId = event.target.getAttribute('data');
        let pathname = window.location.pathname;
        let url = '/group/guest/~/control_panel/manage?p_p_id=com_liferay_dynamic_data_lists_web_portlet_DDLPortlet&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_mvcPath=%2Fedit_record.jsp&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_redirect=' + pathname + '&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_recordSetId=' + ddlRecordSetId + '&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_formDDMTemplateId=0';
        window.location.href = url;
    }
</script>
							
<script>
    $(document).ready(function() {
        $("#updateLUButton").click(function(){
            console.log("Updating Latest Update...");
            var newLatestUpdate = $("#latestUpdateId").val();
            console.log("Updated Latest Update..." + newLatestUpdate);

            if (newLatestUpdate) {
                Liferay.Service(
                    '/cproposal.recformarticle/update_cp_latest_update',
                    {
                        newLatestUpdate: newLatestUpdate,
                        groupId:  ${groupId},
                        articleId: ${journalArticleId}
                    },
                    function(obj) {
                        console.log(obj);
                    }
                );
                document.getElementById("view-latest-update-popup").style.display = "contents";
                setTimeout(function(){ window.location = window.location.pathname; }, 500);
            } else {
                $("#latest-update-error").show();
            }

        });

        $("#updateCSButton").click(function(){
            console.log("Updating Change Summary...");
            var newChangeSummary = $("#changeSummaryId").val();
            console.log("Updated Change Summary..." + newChangeSummary);

            if (newChangeSummary) {
                Liferay.Service(
                    '/cproposal.recformarticle/update_cp_change_summary',
                    {
                        newChangeSummary: newChangeSummary,
                        groupId:  ${groupId},
                        articleId: ${journalArticleId}
                    },
                    function(obj) {
                        console.log(obj);
                    }
                );
                document.getElementById("view-change-summary-popup").style.display = "contents";
                setTimeout(function(){ window.location = window.location.pathname; }, 500);
            } else {
                $("#change-summary-error").show();
            }

        });

        $("#updateButton").click(function(){
            var newTextProblem = $("#problemId").val();
            var newTextSolution = $("#solutionId").val();
            var newTextSolution2 = $("#solutionId2").val();
            var newTextSolution3 = $("#solutionId3").val();
            var newTextSolution4 = "";
            var newTextSolution5 = "";

            Liferay.Service(
                '/cproposal.recformarticle/update-change-proposal-journal-article',							
                {							
                    fieldNameProblemStatement: 'ProblemStatement',							
                    fieldNameSolutionRequeriments: 'SolutionRequirements',							
                    fieldNameSolutionRequeriments2: 'SolutionRequirements2',							
                    fieldNameSolutionRequeriments3: 'SolutionRequirements3',							
                    fieldNameSolutionRequeriments4: 'SolutionRequirements4',							
                    fieldNameSolutionRequeriments5: 'SolutionRequirements5',							

                    newTextProblemStatement:  newTextProblem,							
                    newTextSolutionRequeriments:  newTextSolution,							
                    newTextSolutionRequeriments2:  newTextSolution2,							
                    newTextSolutionRequeriments3:  newTextSolution3,							
                    newTextSolutionRequeriments4:  newTextSolution4,							
                    newTextSolutionRequeriments5:  newTextSolution5,							

                    groupId:  ${groupId},							
                    articleId: ${journalArticleId}							
                },							
                function(obj) {							
                    console.log("starting second call");
                    Liferay.Service(
                        '/messages.messages/send_notification_018',
                        {
                            companyId: ${companyId},
                            groupId: ${groupId},
                            userId: ${userId},
                            resourcePrimaryKey: ${articleResourcePrimKey}
                        },
                        function(obj) {
                            console.log(obj);
                        }
                    );
                }							
            );
            document.getElementById("viewSolutions").style.display = "contents";
            setTimeout(function(){ location.reload(true); }, 500);
        });
    });
</script>
							
<div id="Proposal-dtl" class="tabcontent">							
    <div class="container-fluid proposal-details">							
        <div class="row">							
            <div class="col-md-12 detail-section">							
                <p class="main-section-title">Original CP Submission</p>
                <p>Change Proposal Title</p>	
                <input disabled class="form-control ddm-field-text" type="text" value="${article.getTitle()}"/>						
                <p>${cpDetails_issueSeekingName}</p>
                <textarea disabled class="form-control ddm-field-text" type="textarea">${cpDetails_issueSeeking.value}</textarea>							
                <p>${cpDetails_impactHavingName}</p>
                <textarea disabled class="form-control ddm-field-text" type="textarea">${cpDetails_impactHaving.value}</textarea>						
                <p>${cpDetails_outcomesName}</p>	
                <textarea disabled class="form-control ddm-field-text" type="textarea">${cpDetails_outcomes.value}</textarea>
                <p>${cpDetails_justificationName}</p>	
                <textarea disabled class="form-control ddm-field-text" type="textarea">${cpDetails_justification.value}</textarea>
                <p>${cpDetails_backgroundInformation01Name}</p>	
                <textarea disabled class="form-control ddm-field-text" type="textarea">${cpDetails_backgroundInformation01.value}</textarea>
            </div>							
        </div>
        <div class="row">							
            <div class="col-md-12 detail-section">							
                <p class="main-section-title">Consumer Impacts</p>							
                <p>${cpDetails_consumerName}</p>	
                <textarea disabled class="form-control ddm-field-text" type="textarea">${cpDetails_consumer.value}</textarea>													
                <p>${cpDetails_impactConsumersName}</p>
                <textarea disabled class="form-control ddm-field-text" type="textarea">${cpDetails_impactConsumers.value}</textarea>					
                <#if cpDetails_impactConsumers.hasAdditionalInformation == true >				
                    <p>Additional Information</p>		
                    <textarea disabled class="form-control ddm-field-text" type="textarea">${cpDetails_impactConsumers.additionalInfo}</textarea>						
                </#if>
            </div>							
        </div>							
        <div class="row">							
            <div class="col-md-12 detail-section">
                <p class="main-section-title">Code Impacts</p>							
                <div class="row">		
                    <div class="col-md-6">						
                        <p class="section-title">${cpDetails_recSchedulesName}</p>							
                        <input disabled class="form-control ddm-field-text" type="text" value="${cpDetails_recSchedules.value}"/>							
                    </div>
                    <div class="col-md-6">																
                        <#if cpDetails_recSchedules.hasAdditionalInformation == true >
                            <p class="section-title">Additional Information</p>
                             <textarea disabled class="form-control ddm-field-text" type="textarea">${cpDetails_recSchedules.additionalInfo}</textarea>													
                        </#if>
                    </div>							
                </div>

                <div class="row">		
                    <div class="col-md-6">						
                        <p class="section-title">${cpDetails_dataSpecificationName}</p>
                         <input disabled class="form-control ddm-field-text" type="text" value="${cpDetails_dataSpecification.value}"/>				
                    </div>
                    <div class="col-md-6">																
                        <#if cpDetails_dataSpecification.hasAdditionalInformation == true >
                            <p class="section-title">Additional Information</p>	
                            <textarea disabled class="form-control ddm-field-text" type="textarea">${cpDetails_dataSpecification.additionalInfo}</textarea>							
                        </#if>
                    </div>							
                </div>																	

                <div class="row">		
                    <div class="col-md-6">						
                        <p class="section-title">${cpDetails_centralSystemsName}</p>
                         <input disabled class="form-control ddm-field-text" type="text" value="${cpDetails_centralSystems.value}"/>					
                    </div>
                    <div class="col-md-6">																
                        <#if cpDetails_centralSystems.hasAdditionalInformation == true >
                            <p class="section-title">Additional Information</p>
                            <textarea disabled class="form-control ddm-field-text" type="textarea">${cpDetails_centralSystems.additionalInfo}</textarea>							
                        </#if>
                    </div>							
                </div>

                <div class="row">		
                    <div class="col-md-6">						
                        <p class="section-title">${cpDetails_partiesAndMarketName}</p>
                         <input disabled class="form-control ddm-field-text" type="text" value="${cpDetails_partiesAndMarket.value}"/>		
                    </div>
                    <div class="col-md-6">																
                        <#if cpDetails_partiesAndMarket.hasAdditionalInformation == true >
                            <p class="section-title">Additional Information</p>
                            <textarea disabled class="form-control ddm-field-text" type="textarea">${cpDetails_partiesAndMarket.additionalInfo}</textarea>
                        </#if>
                    </div>							
                </div>

                <div class="row">		
                    <div class="col-md-6">						
                        <p class="section-title">${cpDetails_industryCodesName}</p>	
                         <input disabled class="form-control ddm-field-text" type="text" value="${cpDetails_industryCodes.value}"/>				
                    </div>
                    <div class="col-md-6">																
                        <#if cpDetails_industryCodes.hasAdditionalInformation == true >
                            <p class="section-title">Additional Information</p>
                            <textarea disabled class="form-control ddm-field-text" type="textarea">${cpDetails_industryCodes.additionalInfo}</textarea>
                        </#if>
                    </div>							
                </div>

                <div class="row">		
                    <div class="col-md-6">						
                        <p class="section-title">${cpDetails_significantCodeReviewName}</p>
                         <input disabled class="form-control ddm-field-text" type="text" value="${cpDetails_significantCodeReview.value}"/>						
                    </div>
                    <div class="col-md-6">																
                        <#if cpDetails_significantCodeReview.hasAdditionalInformation == true >
                            <p class="section-title">Additional Information</p>
                            <textarea disabled class="form-control ddm-field-text" type="textarea">${cpDetails_significantCodeReview.additionalInfo}</textarea>							
                        </#if>
                    </div>							
                </div>
            </div>							
        </div>							
        <div class="row">							
            <div class="col-md-12 detail-section">							
                <p class="main-section-title">Change Progression<p>							
                <div class="row">							
                    <div class="col-md-12">							
                        <p class="bold">${cpDetails_urgentCPName}</p>
                        <textarea disabled class="form-control ddm-field-text" type="textarea">${cpDetails_urgentCP.value}</textarea>							
                    </div>							
                </div>
                <div class="row">							
                    <div class="col-md-12">
						<p class="bold">${cpDetails_justificationName02}</p>
                        <textarea disabled class="form-control ddm-field-text" type="textarea">${cpDetails_justification02.value}</textarea>							
                    </div>							
                </div>							
                <div class="row">							
                    <div class="col-md-12">					  
                        <p class="bold">${cpDetails_timescalesName}</p>	
                        <textarea disabled class="form-control ddm-field-text" type="textarea">${cpDetails_timescales.value}</textarea>					
                    </div>							
                </div>
                <div class="row">							
                    <div class="col-md-12">							
                        <p class="bold">${cpDetails_dependeciesName}</p>
                        <textarea disabled class="form-control ddm-field-text" type="textarea">${cpDetails_dependecies.value}</textarea>
                    </div>							
                </div>
                <div class="row">							
                    <div class="col-md-12">							
                        <p class="bold">${cpDetails_relatePreviousName}</p>
                        <input disabled class="form-control ddm-field-text" type="text" value="${cpDetails_relatePrevious.value}"/>															
                    </div>							
                </div>														
            </div>							
        </div>													
    </div>							
</div>							
						
<div id="Proposal-chg" class="tabcontent">							
    <div id="form-box" class="container-fluid">
        <div id="ConsumerTypesimpacted" class="row">
            <div class="col-md-12 rec-impacts">
                <h3>Consumer Types impacted</h3>
                <ul>
                    <div class="wrapper">
                        <#list consumerTypeList as consumerType>
                            <li class="cp-ck-impact">
                                <label><input class="impact-checkbox-${consumerType.getImpactId()}" type="checkbox" data-value="${consumerType.getImpactId()}">
                                <span class="checkBoxCustom"></span>
                                <label for="ck-cty-dp">${consumerType.getImpactName()}</label></label>
                            </li>
                        </#list>
                    </div>
                </ul>
            </div>
        </div>
							
        <div id="ImpactsAffectedConsumers" class="row">
            <div class="col-md-12 rec-impacts">
                <h3>Impacts on affected Consumers</h3>
                <ul>
                    <div class="wrapper">
                        <#list affectedConsumersList as affectedConsumer>
                            <li class="cp-ck-impact">
                                <label><input class="impact-checkbox-${affectedConsumer.getImpactId()}" type="checkbox" data-value="${affectedConsumer.getImpactId()}">
                                <span class="checkBoxCustom"></span>
                                <label for="ck-cty-dp">${affectedConsumer.getImpactName()}</label></label>
                            </li>
                        </#list>
                    </div>
                </ul>
            </div>
        </div>
							
        <div id="RECScheduleImpacts" class="row">
            <div class="col-md-12 rec-impacts">
                <h3>REC and REC Schedule impacts</h3>
                <ul>
                    <div class="wrapper">
                        <#list scheduleImpactList as scheduleImpact>
                            <li class="cp-ck-impact">
                                <label><input class="impact-checkbox-${scheduleImpact.getImpactId()}" type="checkbox" data-value="${scheduleImpact.getImpactId()}">
                                <span class="checkBoxCustom"></span>
                                <label for="ck-cty-dp">${scheduleImpact.getImpactName()}</label></label>
                            </li>
                        </#list>
                    </div>
                </ul>
            </div>
        </div>
							
        <div id="DataSpecificationImpacts" class="row">
            <div class="col-md-12 rec-impacts">
                <h3>Data Specification impacts</h3>
                <ul>
                    <div class="wrapper">
                        <#list specificationImpactList as specificationImpact>
                            <li class="cp-ck-impact">
                                <label><input class="impact-checkbox-${specificationImpact.getImpactId()}" type="checkbox" data-value="${specificationImpact.getImpactId()}">
                                <span class="checkBoxCustom"></span>
                                <label for="ck-cty-dp">${specificationImpact.getImpactName()}</label></label>
                            </li>
                        </#list>
                    </div>
                </ul>
            </div>
        </div>
							
        <div id="ServiceProviderImpacts" class="row">
            <div class="col-md-12 rec-impacts">
                <h3>REC Service Provider impacts</h3>
                <ul>
                    <div class="wrapper">
                        <#list serviceProviderImpactList as serviceProviderImpact>
                            <li class="cp-ck-impact">
                                <label><input class="impact-checkbox-${serviceProviderImpact.getImpactId()}" type="checkbox" data-value="${serviceProviderImpact.getImpactId()}">
                                <span class="checkBoxCustom"></span>
                                <label for="ck-cty-dp">${serviceProviderImpact.getImpactName()}</label></label>
                            </li>
                        </#list>
                    </div>
                </ul>
            </div>
        </div>
							
        <div id="PartyMarketParticipantImpacts" class="row">
            <div class="col-md-12 rec-impacts">
                <h3>REC Party and Market Participant impacts</h3>
                <ul>
                    <div class="wrapper">
                        <#list marketParticipantList as marketParticipant>
                            <li class="cp-ck-impact">
                                <label><input class="impact-checkbox-${marketParticipant.getImpactId()}" type="checkbox" data-value="${marketParticipant.getImpactId()}">
                                <span class="checkBoxCustom"></span>
                                <label for="ck-cty-dp">${marketParticipant.getImpactName()}</label></label>
                            </li>
                        </#list>
                    </div>
                </ul>
            </div>
        </div>
							
        <div id="OtherIndustryCodesImpacted" class="row">
            <div class="col-md-12 rec-impacts">
                <h3>Other Industry Codes impacted</h3>
                <ul>
                    <div class="wrapper">
                        <#list industryCodesImpactedList as industryCodesImpacted>
                            <li class="cp-ck-impact">
                                <label><input class="impact-checkbox-${industryCodesImpacted.getImpactId()}" type="checkbox" data-value="${industryCodesImpacted.getImpactId()}">
                                <span class="checkBoxCustom"></span>
                                <label for="ck-cty-dp">${industryCodesImpacted.getImpactName()}</label></label>
                            </li>
                        </#list>
                    </div>
                </ul>
            </div>
        </div>
							
        <div id="AdditionalInformation" class="row">
            <div class="col-md-12 rec-impacts">
                <h3>Additional Information:</h3>
                <ul>
                    <#if AdditionalInformation_Impact?has_content && AdditionalInformation_Impact.getData() != "">
                        <textarea name="ta-ai">${AdditionalInformation_Impact.getData()} </textarea>
                    <#else>
                        <textarea name="ta-ai"> </textarea>
                    </#if>
                </ul>
            </div>
        </div>
        <br>

        <button id="submitImpact" type="button">Submit
            <svg class="lexicon-icon lexicon-icon-angle-right" role="presentation">
                <use xlink:href="${Imagerute}"></use>
            </svg>
        </button>

        <script>
            $(function() {
                var requiredCheckboxes = $('.browsers :checkbox[required]');
                requiredCheckboxes.change(function(){
                    if(requiredCheckboxes.is(':checked')) {
                        requiredCheckboxes.removeAttr('required');
                    } else {
                        requiredCheckboxes.attr('required', 'required');
                    }
                });
            });
        </script>

    </div>							
</div>

<#if ImpactTabEditable == 0>
    <script>
        $(document).ready(function() {
            $("#form-box input[type='checkbox']").each(function(){
                if(!$(this).is(':checked')){
                    $(this).parent().parent().hide();
                }
            });

            $("span.checkBoxCustom").hide();
            $("#AdditionalInformation textarea").prop("disabled", true);
            $("#submitImpact").hide();
        });
    </script>
</#if>
							
<script>
    $(document).ready(function() {
        $('#popIMP').click(function(e) {
            var values = $("#IMPselect").val();
        });

        $('#popREC').click(function(e) {
            var values = $("#RECselect").val();
        });
    });
</script>
							
<div id="Proposal-hty" class="tabcontent">		
    <table id="historyActiviesTable" class="margin-center">							
        <thead>							
            <tr>							
                <th class="left-align-text">Date</th>							
                <th class="left-align-text">Activity</th>							
            </tr>							
        </thead>
        <tbody>							
            <#list listRecActivityLogs as log>
                <tr>
                    <td>
                        ${log.getCreateDate()?string('dd MMMM yyyy')}
                    </td>
                    <td>
                        ${log.getLogMessage()}
                    </td>
                </tr>
            </#list>							
        </tbody>							
    </table>							
</div>						
							
<div id="Proposal-cpm" class="tabcontent">		
    <#if showButton==1>
	    <#assign cpTitle = article.getUrlTitle() />
        <#assign url = "/group/guest/~/control_panel/manage?p_p_id=com_liferay_dynamic_data_lists_web_portlet_DDLPortlet&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_mvcPath=%2Fedit_record.jsp&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_redirect=/group/guest/-/" + cpTitle + "&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_recordSetId=" + ddlMilestoneId + "&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_formDDMTemplateId=0"/>
        <a class="AddEntry" href="${url}">Add Milestone</a>	
    </#if>

	<#assign preferencesMilestone = "recordSetId=" + ddlMilestoneId />
    <@liferay_portlet["runtime"]							
        portletName="com_liferay_dynamic_data_lists_web_portlet_DDLDisplayPortlet"							
        queryString=preferencesMilestone							
    />							
</div>

<script>
    function catch_values() {
        var list = "";
        var impactedList = "";
        $("#ConsumerTypesimpacted").find("input[type=checkbox]").each(function(){
            if ($(this).prop('checked')==true){
                var impactId = $(this).attr("data-value");
                console.log("Id: " + impactId);
                impactedList = impactedList + "," + impactId;

            }
        });
            
        if (impactedList != "") {
            impactedList = impactedList.substring(1);
            console.log("Impact list " + impactedList);
            list = list + impactedList;
        }

        var consumersList = "";
        $("#ImpactsAffectedConsumers").find("input[type=checkbox]").each(function(){
            if ($(this).prop('checked')==true){
                var consumerId = $(this).attr("data-value");
                console.log("Id: " + consumerId);
                consumersList = consumersList + "," + consumerId;
            }
        });
            
        if (consumersList != "") {
            consumersList = consumersList.substring(1);
            console.log(consumersList);
            list = list + "," + consumersList;
        }

        var scheduleList = "";
        $("#RECScheduleImpacts").find("input[type=checkbox]").each(function(){
            if ($(this).prop('checked')==true){
                var scheduleId = $(this).attr("data-value");
                console.log("Id: " + scheduleId);
                scheduleList = scheduleList + "," + scheduleId;
            }
        });
            
        if (scheduleList != "") {
            scheduleList = scheduleList.substring(1);
            console.log(scheduleList);
            list = list + "," + scheduleList;
        }

        var dataList = "";
        $("#DataSpecificationImpacts").find("input[type=checkbox]").each(function(){
            if ($(this).prop('checked')==true){
                var dataId = $(this).attr("data-value");
                console.log("Id: " + dataId);
                dataList = dataList + "," + dataId;
            }
        });
            
        if (dataList != "") {
            dataList = dataList.substring(1);
            console.log(dataList);
            list = list + "," + dataList;
        }

        var serviceList = "";
        $("#ServiceProviderImpacts").find("input[type=checkbox]").each(function(){
            if ($(this).prop('checked')==true){
                var serviceId = $(this).attr("data-value");
                console.log("Id: " + serviceId);
                serviceList = serviceList + "," + serviceId;
            }
        });
            
        if (serviceList != "") {
            serviceList = serviceList.substring(1);
            console.log(serviceList);
            list = list + "," + serviceList;
        }

        var participantList = "";
        $("#PartyMarketParticipantImpacts").find("input[type=checkbox]").each(function(){
            if ($(this).prop('checked')==true){
                var participantId = $(this).attr("data-value");
                console.log("Id: " + participantId);
                participantList = participantList + "," + participantId;
            }
        });
            
        if (participantList != "") {
            participantList = participantList.substring(1);
            console.log(participantList);
            list = list + "," + participantList;
        }

        var otherCodesList = "";
        $("#OtherIndustryCodesImpacted").find("input[type=checkbox]").each(function(){
            if ($(this).prop('checked')==true){
                var codeId = $(this).attr("data-value");
                console.log("Id: " + codeId);
                otherCodesList = otherCodesList + "," + codeId;
            }
        });
            
        if (otherCodesList != "") {
            otherCodesList = otherCodesList.substring(1);
            console.log(otherCodesList);
            list = list + "," + otherCodesList;
        }

        var textareaval = $("#AdditionalInformation textarea").val();
        console.log($("#AdditionalInformation textarea").val());
        
        Liferay.Service(
            '/cproposal.recformarticle/update-impact-tab-journal-article',
            {
                fieldNameIMP: 'AdditionalInformation_Impact',
                newTextIMP: textareaval,
                groupId:  ${groupId},
                articleId: ${journalArticleId}
            },
            function(obj) {
                console.log(obj);
            }
        );
        
        var bool = new RegExp("^,.*").test(list);
        if (bool) {
            list = list.substring(1);
        }
        return list;
    }
    
    $("#submitImpact").click(function(){
        var list = catch_values();
        console.log(list);
        Liferay.Service(
            '/cproposal.recformarticle/update_CPImpacts',
            {
                resourcePK: ${articleResourcePrimKey},
                impactList: list
            },
            function(obj) {
                console.log(obj);
                location.reload();
            }
        );
    })
</script>

<style>
    #change-proposal-pop-up1 {
       visibility: hidden;
    }
    .iconsDisplayNonePopUp {
        display: none;
    }
    a.AddEntry:hover {
      text-decoration: none;
      color: white;
    }
    .popup1{	
        position: fixed; 	
        top: 0;	
        bottom: 0;	
        left: 0;	
        right: 0;	
        background: rgba(0,0,0,0.7);	
        transition: opacity 500ms;	
    }	
    #NewAdoptProposal {	
        background: #b1c568;	
        width: 119px;	
        text-align: center;	
        margin-top: 10px;	
        margin-right: 20px;	
    }	
    .btnAdopt {	
        background-color: transparent;	
        border: 1px solid transparent;	
        width: 100%;	
        outline: 0;	
    }	
    .block_adopt{	
        height: 36px;	
    }	
    .popupAdopt {	
        width: 700px;	
        background: rgb(255, 255, 255);	
        box-shadow: 0px 1px 4px 0px rgba(0, 0, 0, 0.3);	
        border-radius: 4px;	
        margin: 70px auto;	
        padding: 20px 20px 70px 20px;	
        position: relative;	
    }	
    .popupAdopt h2 {	
        color: rgb(0, 0, 0);	
        letter-spacing: 0.75px;	
        line-height: 24px;	
    }	
    .popupAdopt .close {	
        position: absolute;	
        top: 0px;	
        right: 0px;	
        font-size: 25px;	
        text-decoration: none;	
        font-family: none !important;	
    }	
    .popupAdopt .content {	
        max-height: 30%;	
        overflow: auto;	
    }
    #heading-latest-update, #heading-change-summary {
        background: #F3C194;
        border-radius: 4px;
    }
    #collapse-latest-update, #collapse-change-summary {
        background: #F9E0CA !important;
    }
    #heading-latest-update .btn:focus, #heading-change-summary .btn:focus {
        outline: none;
        box-shadow: none;
    }
    .popupChangeSummary, .popupLatestUpdate {
        width: 944px;
        background: white;
        box-shadow: 0px 1px 4px 0px rgb(0 0 0 / 30%);
        border-radius: 4px;
        margin: 70px auto;
        padding: 20px 20px 70px 20px;
        position: relative;
    }
    .popupChangeSummary h2, .popupLatestUpdate h2 {
        color: black;
        font-size: 20px;
        font-weight: normal;
        letter-spacing: 0.75px;
        line-height: 30px;
    }
    .popupChangeSummary .close, .popupLatestUpdate .close {
        position: absolute;
        top: 0px;
        right: 0px;
        font-size: 25px;
        text-decoration: none;
        font-family: none !important;
    }
    .popupChangeSummary hr, .popupLatestUpdate hr {
        margin-bottom: 0px;
    }
    .popupChangeSummary #changeSummaryId, .popupLatestUpdate #latestUpdateId {
        width: 895px;
        resize: none;
        border: none;
        display: inline-table;
        outline: none;
    }
    .popupChangeSummary #updateCSButton, .popupLatestUpdate #updateLUButton {
        margin-top: 10px;
    }
</style>