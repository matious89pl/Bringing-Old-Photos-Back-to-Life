<#-- HIDE VIEW, EDIT REGISTER DDL-->
<script>
    $( window ).on( "load", function() {
        $(".direction-left-side.dropdown-toggle.icon-monospaced").click(function(){
            setTimeout(function(){
                $("li[role='presentation'] span").each(function( index ){
                    if($(this).html()=="View"){
                        $(this).parent().hide();
                    }
                });

                $("li[role='presentation'] span").each(function( index ){
                    if($(this).html()=="Edit"){
                        $(this).parent().hide();
                    }
                });
            }, 10);           

        });
    });
</script>

<#-- SHOW EDIT TITLE COMPONENT-->
<script>
    $( window ).on( "load", function() {
        $(".direction-left-side.dropdown-toggle.icon-monospaced.text-primary").click(function(){
            setTimeout(function(){
                $("li[role='presentation'] span").each(function( index ){
                    if($(this).html()=="Edit"){
                        $(this).parent().show();
                    }
                });
            }, 15);
        });
    });
</script>


<#assign siteGroupId = themeDisplay.getSiteGroupId() />
<#assign groupId= themeDisplay.getLayout().getGroupId() />  

<#assign userService = utilLocator.findUtil("com.liferay.portal.kernel.service.UserLocalService") />
<#assign rolService = utilLocator.findUtil("com.liferay.portal.kernel.service.RoleLocalService") />
<#assign userGroupService = utilLocator.findUtil("com.liferay.portal.kernel.service.UserGroupRoleLocalService") />
<#assign siteName= themeDisplay.getSiteGroupName() /> 
<#assign siteName= siteName?replace(" ","-") /> 

<#assign companyId= themeDisplay.getCompanyId() />	

<#assign arrow = themeDisplay.getPathThemeImages()+"/forms/arrow-down.svg" />	
<#assign Imagerute = themeDisplay.getPathThemeImages()+"/clay/icons.svg#angle-right" />						
<#assign images_folder = themeDisplay.getPathThemeImages() />

<#assign userId = themeDisplay.getUserId() />
<#assign user = userService.getUser(userId)>
<#assign groupRoles = userGroupService.getUserGroupRoles(userId) />
<#assign access = false />

<#attempt>
    <#assign orgRoleContractM = rolService.getRole(companyId, "REC Contract Managers") />
<#recover>
    <#assign orgRoleContractM = "" />
</#attempt>

<#if groupRoles?has_content>
    <#list groupRoles as rol>
        <#assign roleName = rol.getRole().getName() />
        <#if orgRoleContractM != "" >
            <#if roleName == orgRoleContractM.getName()>
                <#assign access = true />
                <#break>
            </#if>
        </#if>
    </#list>
</#if>

<#assign isAdmin = false />
<#assign userRoles = user.getRoles()/>
<#list userRoles as rol>
    <#if rol.getName() == "Administrator" || rol.getName() == "Committee_Secretariat_Admi" || rol.getName() == "Portal_Admin">
        <#assign isAdmin = true />
    </#if>
</#list>
<#assign canEdit = 0 />

<#list userRoles as rol>
    <#if rol.getName() == "Administrator" || rol.getName() == "Committee_Secretariat_Admi">
        <#assign canEdit = 1 />
    </#if>
</#list>

<#-- GET JOURNAL ARTICLE  -->
<#assign journalArticleId = .vars['reserved-article-id'].data />
<#assign journalArticleService = serviceLocator.findService("com.liferay.journal.service.JournalArticleLocalService") />
<#assign ddlJournalArticleService = serviceLocator.findService("com.everis.rec.ddl.journal.article.service.DDL_JournalArticleLocalService") />							
	
							
<#assign article = journalArticleService.getArticle(groupId, "${journalArticleId}") />						
<#assign articleResourcePrimKey = article.getResourcePrimKey() />
<#assign journalName = TitleNomination.getData() />
<#assign nominationStatus = StatusNomination.getData() />

<#assign editUrl = "/web/"+siteName+"/nominations-form?mode=EDIT&nominationsId="+articleResourcePrimKey />

<#if !isAdmin >
<style>
    
    header.portlet-topper {
        display: none !important;
    }

    .portlet-content.portlet-content-editable {
         border-color: white !important;
    }
            
    .autofit-row.autofit-row.mb-4.metadata-author {
         display: none !important;
    }

    .align-items-center.d-flex.mb-2 {
        display: none !important;
    }
    
    .yui3-widgett{
        display: none !important;
    }

</style>
</#if>
						
<#attempt>							
	<#assign ddlJANomination = ddlJournalArticleService.getDDL_JournalArticleByJournalPKAndName(articleResourcePrimKey, "LIST-NOMINATION-") >						
	<#assign ddlNominationId = ddlJANomination.getDdlRecordSetId()>						
<#recover>							
	<#assign ddlNominationId = 0>						
</#attempt>	

<#-- PORTLET PREFERENCES -->
<#assign portletPreferencesService = serviceLocator.findService("com.liferay.portal.kernel.service.PortletPreferencesLocalService") />
<#assign portletId = "com_liferay_polls_web_portlet_PollsDisplayPortlet_INSTANCE_" + articleResourcePrimKey />
<#assign listPreferences = portletPreferencesService.getPortletPreferences(0, portletId ) />

<#-- REPORT VISIBILITY -->
<#assign showButton = 0 />  
<#attempt>

    <#if listPreferences?has_content>
        <#list listPreferences as preferences >
            <#assign portletQuestionId = preferences.preferences?split("<value>")[1]?split("</value>")[0]?number />
        </#list> 

    </#if>
        <#-- POLLS QUESTIONS  -->
        <#assign pollsQuestions = serviceLocator.findService("com.liferay.polls.service.PollsQuestionLocalService") />
        <#assign pollsDescription = pollsQuestions.getPollsQuestion(portletQuestionId).getDescription() />

        <#-- POLLS VOTE  -->
        <#assign pollsVotes = serviceLocator.findService("com.liferay.polls.service.PollsVoteLocalService") />
        <#assign pollsVotesCount = pollsVotes.getPollsVotesCount() />
        <#assign getQuestionVotes = pollsVotes.getQuestionVotes(portletQuestionId, 0, pollsVotes.getPollsVotesCount()) />

        <#-- CHOICE POLLS  -->
        <#assign pollsChoices = serviceLocator.findService("com.liferay.polls.service.PollsChoiceLocalService") />

    <br>
<#recover>
    <#assign pollsDescription = "" />
</#attempt>

<#assign ClosingDate_Data = getterUtil.getString(ClosingDateNomination.getData())>
<#assign diff = 0>

    <#if validator.isNotNull(ClosingDate_Data)>
        <div id="nominations" >
            <div id="header-consult">
            
                <#assign ClosingDate_DateObj = dateUtil.parseDate("yyyy-MM-dd", ClosingDate_Data, locale)>
                <#assign today = dateUtil.getCurrentDate("yyyy-MM-dd", locale)/>
                <#assign current = dateUtil.parseDate("yyyy-MM-dd", today, locale)>

                <#assign diff = dateUtil.compareTo(ClosingDate_DateObj, current)>
        
                <#if diff gte 0 && nominationStatus == "Nomination-in-Progress">
                    <div class="left">
                        <p class="date">Closing Date For Nominations: <span>${dateUtil.getDate(ClosingDate_DateObj, "dd MMM yyyy", locale)}</span></p>
                    </div>
                    <#if access || isAdmin>
                        <div class="right">
                            <input id="AddEntry" class="right" type="button" value="Nominate a Candidate" onclick="addEntryDDL()"/>
                        </div>
                    </#if>
                <#else>
                    <p>Nominations finished.</p>
                </#if>

            </div>        

            <#assign preferencesNominations = "recordSetId=" + ddlNominationId />    
                
            <div id="list-nominations">
                <@liferay_portlet["runtime"]							
                portletName="com_liferay_dynamic_data_lists_web_portlet_DDLDisplayPortlet"							
                queryString=preferencesNominations							
                />
            </div>

            <#if isAdmin && nominationStatus == "Nomination-in-Progress">
                <a id="startElec" class="right">Start election</a>	
            </#if>  

            <#if diff lt 0 || nominationStatus == "Election-in-Progress" || nominationStatus == "Completed">

                <div id = "poll">
                    <@liferay_portlet["runtime"]
                        instanceId= "${articleResourcePrimKey}"
                        portletName="com_liferay_polls_web_portlet_PollsDisplayPortlet_INSTANCE_pollsVoting"
                    />    

                    <#if isAdmin>
                        <div>
                            <a id="closeVote" href ="" >Close Vote</a>
                        </div>
                    </#if>

                    <br><br>

                    <#if ReportVisibilityNomination.getData() == "Yes" && pollsDescription != "" >

                        <h2>Deciding Vote</h2>

                        <br>

                        <h4>${DecidingVoteNomination.getData()}</h4>

                        <br>

                        <div id = "reportTable">
                            <div class="hideReportTablecontent" >
                                <table id="table0">
                                    <tr>
                                        <th > <h1>${pollsDescription} </h1></th>
                                    </tr>
                                </table>
                            </div>

                            <table id = "table1">
                                <tr>
                                <th> % </th>
                                <th colspan="3"> Votes</th>
                                <#assign currentNumberOfVotes = 0 />
                                </tr>
                                <#if pollsChoices?has_content>
                                    <#assign getPollCount = pollsChoices.getChoices(portletQuestionId) />
                                </#if>
                            
                                
                                <#-- this is to get the total votes --->
                                <#if getPollCount?has_content>
                                    <#list getPollCount as count >
                                        <#assign numberOfVotes = pollsVotes.getChoiceVotesCount(count.choiceId)/>         
                                        <#assign currentNumberOfVotes = currentNumberOfVotes + numberOfVotes />
                                </#list>
                                </#if>  
                                <#if pollsChoices?has_content>
                                    <#assign pollChoices = pollsChoices.getChoices(portletQuestionId) />
                                </#if>
                                        <#if currentNumberOfVotes != 0>
                                            <#list pollChoices as choices >
                                                <#assign choiceVoteCount = pollsVotes.getChoiceVotesCount(choices.choiceId)/>
                                                
                                                <#assign percent =  100 / currentNumberOfVotes />
                                                
                                                <#assign votePercet = percent * choiceVoteCount  />
                                                
                                                <tr>
                                                    <td> ${(votePercet)?string(",##0;; roundingMode=halfEven")}%</td>
                                                    <td style="width: 800px"> ${choiceVoteCount}</td>
                                                    <td> ${choices.name}.</td>
                                                    <td>  ${choices.description}</td>
                                                </tr>
                                            </#list>
                                        <#else>
                                            <tr>
                                                <td>-</td>
                                                <td>-</td>
                                                <td>-</td>
                                                <td>-</td>   
                                            </tr>
                                        </#if>

                            </table>

                            <div class="hideReportTablecontent">
                                <table id="table2">
                                    <tr>
                                        <th ><h1>Vote Outcome</h1></th>
                                    </tr>
                                </table>
                            </div>
                            <br>
                            <table id = "table3">
                                <tr>
                                    <th>User</th>
                                    <th>Choice</th>
                                    <th>Vote Date</th>
                                </tr>

                                <#if   getQuestionVotes?has_content>        
                                    <#list getQuestionVotes as getvotes >
                                        <#if pollsChoices?has_content>
                                            <#assign voteChoice = pollsChoices.getChoice(getvotes.choiceId)/>   
                                        </#if>  
                                        <tr>                
                                            <td>${getvotes.userName}</td>                            
                                            <td> ${voteChoice.name}.  ${voteChoice.description}</td>                            
                                            <td>${getvotes.voteDate?string('dd MMMM yyyy HH:mm')}</td>                            
                                        </tr>

                                    </#list>
                                </#if>

                            </table>
                        </div>

                        <div id="editor"></div>
                            <br>
                            <#if canEdit == 1>
                                <button id ="generatePDFbtn" onclick="javascript:createPdf()" >generate PDF</button>
                            </#if>
                        </div>

                    </#if>
                </div>
            </#if>
        </div>
    </#if>

    

<div id="NominationsDetails-pop-up1">
    <div id="notificationPopUp" class="popUpNotification">
        <div class="popUp-content">
            <a class="close" href="#"></a>
            <h2>Confirm close nomination process</h2>
            <hr>
            <div class="content-label">Are you sure you want to close the nomination process?</div>
            <hr>
            <button id="sendNotification" class="BottonNotification" onclick="closeNomi()">Yes</button>
            <button id="cancelElection" class="BottonNotification">No</button>
        </div>
    </div>    
</div>

<script>
 function closeNomi(){

    Liferay.Service('/cproposal.recformarticle/update-impact-tab-journal-article', 
    {
        fieldNameIMP: 'StatusNomination',
        newTextIMP: 'Election-in-Progress',
        groupId:  ${siteGroupId},                           
        resourcePrimKey: ${articleResourcePrimKey}
    },
    
    function(obj) {
        setTimeout(function(){ 
            window.location.reload();
        }, 500);
    });
    
}
        
</script>

<script>
    function addEntryDDL(){
        let pathname = window.location.pathname;
        let url = '/group/${siteName}/~/control_panel/manage?p_p_id=com_liferay_dynamic_data_lists_web_portlet_DDLPortlet&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_mvcPath=%2Fedit_record.jsp&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_redirect=' + pathname + '&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_recordSetId=' + ${ddlNominationId} + '&_com_liferay_dynamic_data_lists_web_portlet_DDLPortlet_formDDMTemplateId=0&p_p_auth=tYkZwwfY';
        window.location.href = url;
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
                    console.log(obj);
                    user = obj;
                    if(user.comments != ""){
                        bio = user.comments;
                        item.attr("data-bio", bio);
                    }
                    if(user.firstName != "" && user.lastName != "" && typeof user.firstName !== 'undefined' && typeof user.lastName !== 'undefined'){
                        name = user.firstName+" "+user.lastName;
                        item.attr("data-name", name);
                    }
                }
            );
        });

        blockedWords = ["Denied","Pending"];

        $("strong.label.status.text-uppercase.workflow-value").filter(function() {    
            var str = $(this).text();            
            return blockedWords.reduce(function(s, v) {          
                return s + !!~str.indexOf(v);
            }, 0); 

        }).parents("tr").hide(); 
			
        var search = document.getElementById("search-bar-hidden");				
        search.style.display = "block";											

        var instanceId= ${articleResourcePrimKey};
        var questionId =  document.getElementById("_com_liferay_polls_web_portlet_PollsDisplayPortlet_INSTANCE_"+instanceId+"_questionId").value;
        
        var url = new URLSearchParams(window.location.search);
        url.set('questionId',questionId);
        window.location.hash = url;
        

        $("#closeVote").click(function(){

            Liferay.Service('/recsites.recsites/closing_polls_question',
                {
                questionId: questionId,
                userId: ${userId}
                },

                function(obj) {
                    console.log("QuestionId = " + questionId);
                    location.reload();
                }
            );
        });
   
    });
    
</script>

<#-- Create a PDF file-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.5.3/jspdf.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf-autotable/3.5.6/jspdf.plugin.autotable.min.js"></script>

<script>

    $("#table0 div").removeClass( "hideReportTablecontent" );
    $("#table1 div").removeClass( "hideReportTablecontent" );
    $("#table2 div").removeClass( "hideReportTablecontent" );
  
    var nameFile = "${TitleNomination.getData()}";

    function createPdf() {

        var doc = new jsPDF();       
        var header = function () {
            doc.setFontSize(14);
            doc.setTextColor(40);
            doc.setFontStyle('bold');     
        };

        specialElementHandlers = {
            "#editor": function(element, renderer) {
                return true;
            },
        };

        doc.text(20, 10, '${TitleNomination.getData()}');
        doc.text(20, 18, '${DescriptionNomination.getData()}');

        var docIterator = 34;
               
        docIterator += 8;
        var decidingValue = "${DecidingVoteNomination.getData()}";
        if (decidingValue != ""){
            doc.text(20, docIterator, 'Deciding Votes:');
            docIterator += 8;
            doc.text(20, docIterator, decidingValue);
        }
        
        docIterator +=10;
        doc.autoTable({html:"#table0", didDrawPage: header,startY: docIterator});
        docIterator +=10;
        doc.autoTable({html:"#table1", didDrawPage: header,startY: docIterator});
        doc.addPage();
        doc.autoTable({html:"#table2", didDrawPage: header, startY: 10});
        doc.autoTable({html:"#table3", didDrawPage: header, startY: 20});
        doc.save(nameFile);
    }

</script>

<script>
    var modal = document.getElementById("notificationPopUp");

    var btn = document.getElementById("startElec");
    var btn2 = document.getElementById("cancelElection");
    var span = document.getElementsByClassName("close")[0];

    btn.onclick = function() {
    modal.style.display = "block";
    }

    span.onclick = function() {
    modal.style.display = "none"; 
    }
    btn2.onclick = function() {
    modal.style.display = "none"; 
    }

</script>
