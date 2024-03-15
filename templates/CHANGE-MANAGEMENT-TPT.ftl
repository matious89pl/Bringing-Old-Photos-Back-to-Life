<!-- Change management -->

<#assign Imagerute = themeDisplay.getPathThemeImages()+"/forms/chevrom-white-small.svg" />
<#assign arrow = themeDisplay.getPathThemeImages()+"/forms/arrow-down.svg" />
<#assign doc = themeDisplay.getPathThemeImages()+"/forms/document.svg" />
<#assign grid = themeDisplay.getPathThemeImages()+"/forms/grid-white.svg" />
<#assign horizon = themeDisplay.getPathThemeImages()+"/forms/expand.svg" />
<#assign document_add = themeDisplay.getPathThemeImages()+"/forms/add_document.svg" />
<#assign userFriendImg = themeDisplay.getPathThemeImages()+"/forms/user_friend.svg" />
<#assign roadmap = themeDisplay.getPathThemeImages()+"/forms/code_branch.svg" />
<#assign network = themeDisplay.getPathThemeImages()+"/forms/network.svg" />
<#assign calendar = themeDisplay.getPathThemeImages()+"/forms/calendar.svg" />
<#assign cog = themeDisplay.getPathThemeImages()+"/forms/cog-big.svg" />


<#assign images_folder = themeDisplay.getPathThemeImages() />

<#assign userService = utilLocator.findUtil("com.liferay.portal.kernel.service.UserLocalService") />

<#assign userId = themeDisplay.getUserId() />
<#assign user = userService.getUser(userId)>
<#assign userRoles = user.getRoles()/>
<#assign userOrganisations = user.getOrganizations()/>
<#assign issuesLogVisible = 0 />
<#assign forwardWorkPlanVisible = 0 />
<#assign category3 = 0 />
<#assign cmsActionLog = 0 />


<!--  OLD PERMISIONS REQUIRED : rol.getName() == "Change_Management_Team" || rol.getName() == "Administrator" || rol.getName() == "Operational_Account_Manager" || rol.getName
   () == "Committee_Secretariat_Admi" || rol.getName() == "Helpdesk_Manager" || rol.getName() == "Helpdesk_Operative" || rol.getName() == "Portal_User"> -->
<!--NEW CODE BY REQUIREMENTS -- RP-3670 The REC Stakeholder should not be able to see the Issues Log or CMSP Action Log. These should only be visible to the RPA, RTS, RPS and RECCo. Change Management Team, Code Manager Master Admin User, RECCo -->

<#list userRoles as rol>
    <#if  rol.getName() == "Administrator" || rol.getName() == "Change_Management_Team" || rol.getName() == "Code_Manage_Master_Admin_User" || rol.getName() == "RECCo">
        <#assign issuesLogVisible = 1 />
        <#break>
    </#if>
</#list>

<#assign userOrg = user.getOrganizations()/>
<#list userOrg as org>
    <!-- Removed this conditions for requirements in the task RP-3670, only RTS y RPA -- org.getName() == "Responsible Service Provider" || -->
    <#if org.getName() == "REC Technical Service" || org.getName() == "REC Performance Assurance" >
        <#assign issuesLogVisible = 1 />
        <#break>
    </#if>
</#list>

<#list userRoles as rol>
     <#if rol.getName() == "Change_Management_Team" || rol.getName() == "Administrator" || rol.getName() == "Code_Manage_Master_Admin_User" || rol.getName() == "RECCo" || rol.getName() == "Portal_Admin">
        <#assign forwardWorkPlanVisible = 1 />
        <#break>
    </#if>
</#list>

<#list userOrganisations as organisations>
     <#if organisations.name == "Responsible Service Provider" >
        <#assign category3 = 1 />
     </#if>
</#list>

<#list userRoles as rol>
    <#if rol.getName() == "Administrator" || rol.getName() == "Change_Management_Team" || rol.getName() == "REC_Contract_Managers" || rol.getName() == "Code_Manage_Master_Admin_User" || rol.getName() == "Portal_User" >
        <#assign category3 = 1 />
    </#if>
</#list>

<#assign userOrg = user.getOrganizations()/>
    <#list userOrg as org>
    <#if  org.getName() == "Responsible Service Provider" ||  org.getName() == "REC Technical Service" || org.getName() == "REC Performance Assurance" >
        <#assign cmsActionLog = 1 />
        <#break>
    </#if>
</#list>

<#list userRoles as rol>
    <#if  rol.getName() == "Administrator" || rol.getName() == "Change_Management_Team" || rol.getName() == "Code_Manage_Master_Admin_User" || rol.getName() == "RECCo">
        <#assign cmsActionLog = 1 />
        <#break>

       <#elseif rol.getName() == "REC_Stakeholder" >
         <#assign cmsActionLog = 0 />

    </#if>
</#list>


<script>

    $(document).ready(function(){
        Liferay.Service(
            '/cproposal.recformarticle/get_cp_by_release_schedule',
        function(obj) {
            console.log(obj);
            if (Object.entries(obj).length !== 0){
                releaseScheduleCPs = obj.data;
                createReleaseScheduleList(releaseScheduleCPs);
            } else {
                emptyReleaseSchedule();
            }
            }
        );
    });

    function createReleaseScheduleList(releaseScheduleCPs){
        var html = '';
        var i = 1;
        for (var eachReleaseSchedule of releaseScheduleCPs) {
            console.log(eachReleaseSchedule.releaseSchedule);
            console.log(i);
            html += '<div id="schedule' + i + '">';
            html += '<div class="card">';
            html += '<div class="card-header schedule-item" id="heading' + i + '">';
            html += '<h5 class="mb-0">';
            html += '<button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapse' + i + '" aria-expanded="false" aria-controls="collapse1"> ' + eachReleaseSchedule.releaseSchedule;
            html += ' <img src="${arrow}"/> </button>  </h5> </div>' ;
            html += '<div id="collapse' + i + '" class="collapse show" aria-labelledby="heading' + i + '" data-parent="#schedule' + i + '">';
            html += '<div class="card-body">';
            html += '<ul class="list-cp-sche">';
            for (var eachCP of eachReleaseSchedule.changeProposalList) {
                html += '<li><a href="/group/guest/-/' + eachCP.cpURLTitle + '" >' + eachCP.cpReference + ' - ' + eachCP.cpTitle + ' <img src="${Imagerute}"/></a></li>';
            }
            html += '</ul> </div> </div> </div> </div>';
            i++;
        }
        $('#schedule-release-render').html(html);
    }

    function emptyReleaseSchedule(){
        var html = '<p> There is no Change Proposal(s) for release schedule.</p>';
        $('#schedule-release-render').html(html);
    }

</script>

<h1 id="title-change-management">
<img src="${cog}" />
${title.getData()}</h1>
<div id="boxes-cm" class="container-fluid">
   <div class="row" id="row-content">
        <div class="col-md-5 icons-bigger">
            <a data-senna-off="true" href="${CHM_VIEW_CHANGE_REGISTER.CHM_LINK_TO_CHREGISTER.getFriendlyUrl()}">
                <#assign class = "party-view" />
                <#if CHM_VIEW_CHANGE_REGISTER.CHM_VIEW_CHANGE_REGISTER_BackgroundColor.getData()?? && CHM_VIEW_CHANGE_REGISTER.CHM_VIEW_CHANGE_REGISTER_BackgroundColor.getData() != "None" >
                    <#assign class = "${CHM_VIEW_CHANGE_REGISTER.CHM_VIEW_CHANGE_REGISTER_BackgroundColor.getData()}" />
                </#if>
                <div id="change-register" class="${class}">
                    <div class="row">
                        <div class="col-md-2 image-left">
                            <#if CHM_VIEW_CHANGE_REGISTER.CHM_VIEW_CHANGE_REGISTER_LogoDown.getData()?? && CHM_VIEW_CHANGE_REGISTER.CHM_VIEW_CHANGE_REGISTER_LogoDown.getData() != "None" >
                                <#assign image = "${themeDisplay.getPathThemeImages()}/forms/${CHM_VIEW_CHANGE_REGISTER.CHM_VIEW_CHANGE_REGISTER_LogoDown.getData()}.svg" />
                                <img src="${image}" />
                            </#if>
                        </div>
                        <div class="col-md-8">
                            <h2 class="h2CM"> ${CHM_VIEW_CHANGE_REGISTER.getData()}</h2>
                        </div>
                    </div>
                </div>
            </a>
            <style>
                .Calendar-Release{
                    display: flex;
                    flex-wrap: nowrap;
                    padding: 0px;
                }
                .col-md-6.c-c-calendar {
                    padding-left: 0;
                    padding-right: 8px;
                }
                .col-md-6.c-c-release {
                    padding-right: 0;
                    padding-left: 8px;
                }   
                #change-calendar .row .col-md-8 {
                    max-width: 100%;
                }             
            </style>
            <div class= "container Calendar-Release">
                <div class="col-md-6 c-c-calendar">
                    <a data-senna-off="true" href="${CHM_CHANGE_CALENDAR.CHM_LINK_TO_CHANGE_CALENDAR.getFriendlyUrl()}" id="c-calendar">
                        <#assign class = "party-view" />
                        <#if CHM_CHANGE_CALENDAR.CHM_CHANGE_CALENDAR_BackgroundColor.getData()?? && CHM_CHANGE_CALENDAR.CHM_CHANGE_CALENDAR_BackgroundColor.getData() != "None" >
                            <#assign class = "${CHM_CHANGE_CALENDAR.CHM_CHANGE_CALENDAR_BackgroundColor.getData()}" />
                        </#if>
                        <div id="change-calendar" class="${class}">
                            <div class="row">
                                <div class="col-md-2 image-left">
                                    <#if CHM_CHANGE_CALENDAR.CHM_CHANGE_CALENDAR_LogoDown.getData()?? && CHM_CHANGE_CALENDAR.CHM_CHANGE_CALENDAR_LogoDown.getData() != "None" >
                                        <#assign image = "${themeDisplay.getPathThemeImages()}/forms/${CHM_CHANGE_CALENDAR.CHM_CHANGE_CALENDAR_LogoDown.getData()}.svg" />
                                        <img src="${image}" />
                                    </#if>
                                </div>
                                <div class="col-md-8">
                                    <h2 class="h2CM">${CHM_CHANGE_CALENDAR.getData()}</h2>
                                </div>
                            </div>
                        </div>
                        
                    </a>
                </div>
                <div class="col-md-6 c-c-release">
                    <a data-senna-off="true" href="${CHM_RELEASE.CHM_LINK_TO_RELEASE.getData()}" id="c-release">
                    <#assign class = "party-view" />
                    <#if CHM_RELEASE.CHM_RELEASE_BackgroundColor.getData()?? && CHM_RELEASE.CHM_RELEASE_BackgroundColor.getData() != "None" >
                        <#assign class = "${CHM_RELEASE.CHM_RELEASE_BackgroundColor.getData()}" />
                    </#if>
                    <div id="change-calendar" class="${class}">
                        <div class="row">
                            <div class="col-md-2 image-left">
                                <#if CHM_RELEASE.CHM_RELEASE_LogoDown.getData()?? && CHM_RELEASE.CHM_RELEASE_LogoDown.getData() != "None" >
                                    <#assign image = "${themeDisplay.getPathThemeImages()}/forms/${CHM_RELEASE.CHM_RELEASE_LogoDown.getData()}.svg" />
                                    <img src="${image}" />
                                </#if>
                            </div>
                            <div class="col-md-8">
                                <h2 class="h2CM">${CHM_RELEASE.getData()}</h2>
                        </div>
                    </div>
                    </div>
                    </a>
                </div>
            </div>
        </div>
        <div class="col-md-7">
            <div class="row" id="row-ups">
                <div class="col-md-4">
                    <a data-senna-off="true" href="${CHM_Consultation.CHM_LINK_TO_CONSULTATION.getFriendlyUrl()}">
                        <#assign class = "party-view" />
                        <#if CHM_CHANGE_CALENDAR.CHM_CHANGE_CALENDAR_BackgroundColor.getData()?? && CHM_CHANGE_CALENDAR.CHM_CHANGE_CALENDAR_BackgroundColor.getData() != "None" >
                            <#assign class = "${CHM_CHANGE_CALENDAR.CHM_CHANGE_CALENDAR_BackgroundColor.getData()}" />
                        </#if>
                        <div id="consultation-home" class="${class} margened">
                            <div class="row">
                                <div class="col-md-4 image-left">
                                <#if CHM_Consultation.CHM_Consultation_LogoDown.getData()?? && CHM_Consultation.CHM_Consultation_LogoDown.getData() != "None" >
                                    <#assign image = "${themeDisplay.getPathThemeImages()}/forms/${CHM_Consultation.CHM_Consultation_LogoDown.getData()}.svg" />
                                    <img src="${image}" />
                                </#if>
                                </div>
                                <div class="col-md-8">
                                    <h2 class="h2CM">${CHM_Consultation.getData()}</h2>
                                </div>
                            </div>
                        </div>
                    </a>
                </div>
            <div class="col-md-4">
               <a href="${CHM_HORIZON_SCANNER.CHM_HORIZON_SCANNER_DOCUMENT.getData()}" target="_blank">
                    <#assign class = "party-view" />
                    <#if CHM_HORIZON_SCANNER.CHM_HORIZON_SCANNER_BackgroundColor.getData()?? && CHM_HORIZON_SCANNER.CHM_HORIZON_SCANNER_BackgroundColor.getData() != "None" >
                        <#assign class = "${CHM_HORIZON_SCANNER.CHM_HORIZON_SCANNER_BackgroundColor.getData()}" />
                    </#if>
                    <div id="Horizon-scanner" class="${class} margened">
                        <div class="row">
                            <div class="col-md-4 image-left">
                                <#if CHM_HORIZON_SCANNER.CHM_HORIZON_SCANNER_LogoDown.getData()?? && CHM_HORIZON_SCANNER.CHM_HORIZON_SCANNER_LogoDown.getData() != "None" >
                                    <#assign image = "${themeDisplay.getPathThemeImages()}/forms/${CHM_HORIZON_SCANNER.CHM_HORIZON_SCANNER_LogoDown.getData()}.svg" />
                                    <img src="${image}" />
                                </#if>
                            </div>
                            <div class="col-md-8">
                                <h2 class="h2CM">${CHM_HORIZON_SCANNER.getData()}</h2>
                            </div>
                        </div>
                    </div>
                </a>
            </div>
            <div class="col-md-4 resize-col">
               <a href="${CHM_CODE_ROADMAP.CHM_LINK_TO_ROADMAP.getData()}" target="_blank">
                  <#assign class = "party-view" />
                  <#if CHM_CODE_ROADMAP.CHM_CODE_ROADMAP_BackgroundColor.getData()?? && CHM_CODE_ROADMAP.CHM_CODE_ROADMAP_BackgroundColor.getData() != "None" >
                  <#assign class = "${CHM_CODE_ROADMAP.CHM_CODE_ROADMAP_BackgroundColor.getData()}" />
                  </#if>
                  <div id="code-roadmap" class="${class}">
                    <div class="row">
                        <div class="col-md-3 image-left">
                           <#if CHM_CODE_ROADMAP.CHM_CODE_ROADMAP_LogoDown.getData()?? && CHM_CODE_ROADMAP.CHM_CODE_ROADMAP_LogoDown.getData() != "None" >
                           <#assign image = "${themeDisplay.getPathThemeImages()}/forms/${CHM_CODE_ROADMAP.CHM_CODE_ROADMAP_LogoDown.getData()}.svg" />
                           <img src="${image}" />
                           </#if>
                        </div>
                        <div class="col-md-9 code-padd">
                           <h2 class="h2CM">${CHM_CODE_ROADMAP.getData()}</h2>
                        </div>
                     </div>
                  </div>
               </a>
            </div>
         </div>
         <div class="row" id="row-down">
            <div class="col-md-4 ">
               <a data-senna-off="true" href="${CHM_IMPACT_ASSESSMENT.CHM_LINT_TO_IMPACT_ASSESSMENT.getFriendlyUrl()}">
                  <#assign class = "party-view" />
                  <#if CHM_IMPACT_ASSESSMENT.CHM_IMPACT_ASSESSMENT_BackgroundColor.getData()?? && CHM_IMPACT_ASSESSMENT.CHM_IMPACT_ASSESSMENT_BackgroundColor.getData() != "None" >
                  <#assign class = "${CHM_IMPACT_ASSESSMENT.CHM_IMPACT_ASSESSMENT_BackgroundColor.getData()}" />
                  </#if>
                  <div id="impact-assessent" class="${class} margened">
                     <div class="row">
                        <div class="col-md-4 image-left">
                           <#if CHM_IMPACT_ASSESSMENT.CHM_IMPACT_ASSESSMENT_LogoDown.getData()?? && CHM_IMPACT_ASSESSMENT.CHM_IMPACT_ASSESSMENT_LogoDown.getData() != "None" >
                           <#assign image = "${themeDisplay.getPathThemeImages()}/forms/${CHM_IMPACT_ASSESSMENT.CHM_IMPACT_ASSESSMENT_LogoDown.getData()}.svg" />
                           <img src="${image}" />
                           </#if>
                        </div>
                        <div class="col-md-8">
                           <h2 class="h2CM">${CHM_IMPACT_ASSESSMENT.getData()}</h2>
                        </div>
                     </div>
                  </div>
               </a>
            </div>
            <div class="col-md-4 ">
               <a href="${CHM_PRIORITISATION_MATRIX.CHM_PRIORITISATION_MATRIX_DOCUMENT.getData()}" target="_blank">
                  <#assign class = "party-view" />
                  <#if CHM_PRIORITISATION_MATRIX.CHM_PRIORITISATION_MATRIX_BackgroundColor.getData()?? && CHM_PRIORITISATION_MATRIX.CHM_PRIORITISATION_MATRIX_BackgroundColor.getData() != "None" >
                  <#assign class = "${CHM_PRIORITISATION_MATRIX.CHM_PRIORITISATION_MATRIX_BackgroundColor.getData()}" />
                  </#if>
                  <div id="prioritisation-matrix" class=" ${class} margened">
                     <div class="row">
                        <div class="col-md-4 image-left">
                           <#if CHM_PRIORITISATION_MATRIX.CHM_PRIORITISATION_MATRIX_LogoDown.getData()?? && CHM_PRIORITISATION_MATRIX.CHM_PRIORITISATION_MATRIX_LogoDown.getData() != "None" >
                           <#assign image = "${themeDisplay.getPathThemeImages()}/forms/${CHM_PRIORITISATION_MATRIX.CHM_PRIORITISATION_MATRIX_LogoDown.getData()}.svg" />
                           <img src="${image}" />
                           </#if>
                        </div>
                        <div class="col-md-8">
                           <h2 class="h2CM">${CHM_PRIORITISATION_MATRIX.getData()}</h2>
                        </div>
                     </div>
                  </div>
               </a>
            </div>
            <div class="col-md-4 resize-col">
               <a data-senna-off="true" href="${CHM_NEW_CHANGE_PROPOSAL.CHM_LINK_TO_NEW_CHANGE_PROPOSAL.getFriendlyUrl()}">
                  <#assign class = "party-view" />
                  <#if CHM_NEW_CHANGE_PROPOSAL.CHM_NEW_CHANGE_PROPOSAL_BackgroundColor.getData()?? && CHM_NEW_CHANGE_PROPOSAL.CHM_NEW_CHANGE_PROPOSAL_BackgroundColor.getData() != "None" >
                  <#assign class = "${CHM_NEW_CHANGE_PROPOSAL.CHM_NEW_CHANGE_PROPOSAL_BackgroundColor.getData()}" />
                  </#if>
                  <div id="new-change-proposal" class="${class}">
                    <div class="row">
                        <div class="col-md-4 image-left">
                           <#if CHM_NEW_CHANGE_PROPOSAL.CHM_NEW_CHANGE_PROPOSAL_LogoDown.getData()?? && CHM_NEW_CHANGE_PROPOSAL.CHM_NEW_CHANGE_PROPOSAL_LogoDown.getData() != "None" >
                           <#assign image = "${themeDisplay.getPathThemeImages()}/forms/${CHM_NEW_CHANGE_PROPOSAL.CHM_NEW_CHANGE_PROPOSAL_LogoDown.getData()}.svg" />
                           <img src="${image}" />
                           </#if>
                        </div>
                        <div class="col-md-8">
                           <h2 class="h2CM">${CHM_NEW_CHANGE_PROPOSAL.getData()}</h2>
                       </div>
                     </div>
                  </div>
               </a>
            </div>
         </div>
      </div>
   </div>
																			   
   <div class="row lastRow ">
								  
      <#assign class = "party-view" />
      <#if IssuesLog.IssuesLog_BackgroundColor.getData()?? && IssuesLog.IssuesLog_BackgroundColor.getData() != "None" >
      <#assign class = "${IssuesLog.IssuesLog_BackgroundColor.getData()}" />
      </#if>
      <a href="/issues-log" id="last-row-box" class="col-md-3 left ${class}">
         <div class="rowFinal">
            <#if IssuesLog.IssuesLog_LogoDown.getData()?? && IssuesLog.IssuesLog_LogoDown.getData() != "None" >
            <#assign image = "${themeDisplay.getPathThemeImages()}/forms/${IssuesLog.IssuesLog_LogoDown.getData()}.svg" />
            <img src="${image}" />
            </#if>
            <h2 class="h2CM"> ${IssuesLog.getData()} </h2>
         </div>
      </a>
			
						   
      <#assign class = "party-view" />
      <#if Category3.Category3_BackgroundColor.getData()?? && Category3.Category3_BackgroundColor.getData() != "None" >
      <#assign class = "${Category3.Category3_BackgroundColor.getData()}" />
      </#if>
      <a href="/category-3" id="category3cl" class="col-md-3 left ${class}">
         <div class="rowFinal">
            <#if Category3.Category3_LogoDown.getData()?? && Category3.Category3_LogoDown.getData() != "None" >
            <#assign image = "${themeDisplay.getPathThemeImages()}/forms/${Category3.Category3_LogoDown.getData()}.svg" />
            <img src="${image}" />
            </#if>
            <h2 class="h2CM">${Category3.getData()}  </h2>
         </div>
      </a>
			
									   
      <#assign class = "party-view" />
      <#if ForwardWorkPlan.ForwardWorkPlan_BackgroundColor.getData()?? && ForwardWorkPlan.ForwardWorkPlan_BackgroundColor.getData() != "None" >
      <#assign class = "${ForwardWorkPlan.ForwardWorkPlan_BackgroundColor.getData()}" />
      </#if>
      <a href="${ForwardWorkPlan.getData()}" target="_blank" id="forwardPlan" class="col-md-3 left ${class}">
         <div class="rowFinal">
            <#if ForwardWorkPlan.ForwardWorkPlan_LogoDown.getData()?? && ForwardWorkPlan.ForwardWorkPlan_LogoDown.getData() != "None" >
            <#assign image = "${themeDisplay.getPathThemeImages()}/forms/${ForwardWorkPlan.ForwardWorkPlan_LogoDown.getData()}.svg" />
            <img src="${image}" />
            </#if>
            <#-- UPDATE HREF TO BE ABLE TO WORK IN ALL ENVIROMENTS-->
            <h2 class="h2CM">${ForwardWorkPlan.ForwardWorkPlan_title.getData()} </h2>
         </div>
      </a>
			
							 
      <#assign class = "party-view" />
      <#if CMSPActionLog.CMSPActionLog_BackgroundColor.getData()?? && CMSPActionLog.CMSPActionLog_BackgroundColor.getData() != "None" >
      <#assign class = "${CMSPActionLog.CMSPActionLog_BackgroundColor.getData()}" />
      </#if>
      <a  href="/cmsp-action-log" id="cmspActionLog" class="col-md-3 right ${class}">
         <div class="rowFinal">
            <#-- UPDATE HREF TO BE ABLE TO WORK IN ALL ENVIROMENTS-->
            <#if CMSPActionLog.CMSPActionLog_LogoDown.getData()?? && CMSPActionLog.CMSPActionLog_LogoDown.getData() != "None" >
            <#assign image = "${themeDisplay.getPathThemeImages()}/forms/${CMSPActionLog.CMSPActionLog_LogoDown.getData()}.svg" />
            <img src="${image}" />
            </#if>
            <h2 class="h2CM">${CMSPActionLog.getData()}</h2>
         </div>
      </a>
			
   </div>
		 
</div>
<div id="release-schedule">
<h1 id="release-title">Release Schedule  <a href="/group/guest/change-register">See all</a></h1>
<div id="schedule-release-render"/></div>