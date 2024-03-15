<#-- USER  -->  
<#assign userService = utilLocator.findUtil("com.liferay.portal.kernel.service.UserLocalService") />
<#assign userId = themeDisplay.getUserId() />
<#assign user = userService.getUser(userId)>

<#-- COMPANY, GROUP, site GROUP -->
<#assign companyId = themeDisplay.getCompanyId() />							
<#assign groupId = themeDisplay.getLayout().getGroupId() />	
<#assign siteGroupId = themeDisplay.getScopeGroupId() />	

<#-- ROLES -->
<#assign rolService = utilLocator.findUtil("com.liferay.portal.kernel.service.RoleLocalService") />
<#assign userRoles = user.getRoles()/>

<#-- ORGANISATIONS -->
<#assign organisationService = utilLocator.findUtil("com.liferay.portal.kernel.service.OrganizationLocalService") />
<#assign userOrganisations = user.getOrganizations()/>

<#-- GET JOURNAL ARTICLE  -->                           
<#assign journalArticleId = .vars['reserved-article-id'].data />                            
<#assign companyId= themeDisplay.getCompanyId() />                          
<#assign groupId= themeDisplay.getLayout().getGroupId() />  
<#assign journalArticleService = serviceLocator.findService("com.liferay.journal.service.JournalArticleLocalService") />                            
                            
<#assign article = journalArticleService.getArticle(groupId, "${journalArticleId}") />                          
<#assign articleResourcePrimKey = article.getResourcePrimKey() />

<#-- GET SITE NAME  -->  
<#assign siteName = themeDisplay.getSiteGroupName() />
<#assign siteName = siteName?replace(" ", "-") />
<#assign siteName = siteName?lower_case />

<#-- CHECK COMMITTEE MEMBER 
<#assign groupService = utilLocator.findUtil("com.liferay.portal.kernel.service.UserGroupRoleLocalService") />
<#assign userGroups = groupService.getUserGroupRoles(userId, siteGroupId) />
<#assign committeeMember = rolService.getRole(companyId, "Committee_Member").getRoleId() />

  NO ACCESS MY APPLICATION ACTIVITIES DETAILS SITE ROLES  
<#assign ismember = false /> 
<#list userGroups as rol>
     <#if committeeMember == rol.roleId >
        <#assign ismember = true />
     </#if> 
</#list>-->

<#assign canEdit = 0 />

<#assign hasPoll = false />

<#list userRoles as rol>
    <#if rol.getName() == "Administrator" || rol.getName() == "Committee_Secretariat_Admi">
        <#assign canEdit = 1 />
    </#if>
</#list>

<#assign editUrl = "/group/"+siteName+"/voting-form?mode=EDIT&votingId="+articleResourcePrimKey />

<#-- CURRENT URL  -->   
<#assign currentUrl = themeDisplay.getURLCurrent() />

<#if !permissionChecker.isOmniadmin()>
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
        </style>
</#if>

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
        <#assign isExpired = pollsQuestions.getPollsQuestion(portletQuestionId).isExpired() />
        <#assign hasPoll = true />
		<#if pollsDescription != "">
			<#assign pollsDescription = "-" />
		</#if>


        <#-- POLLS VOTE  -->
        <#assign pollsVotes = serviceLocator.findService("com.liferay.polls.service.PollsVoteLocalService") />
        <#assign pollsVotesCount = pollsVotes.getPollsVotesCount() />
        <#assign getQuestionVotes = pollsVotes.getQuestionVotes(portletQuestionId, 0, pollsVotes.getPollsVotesCount()) />
        <#-- CHOICE POLLS  -->
        <#assign pollsChoices = serviceLocator.findService("com.liferay.polls.service.PollsChoiceLocalService") />
    
    <br>
<#recover>
    <#assign isExpired = false />
    <#assign hasPoll = false />
   <#--
   <#list userRoles as rol>
        <#if rol.getName() == "Administrator" >
            <div class="alert alert-info">
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                Ops! ... There is no voting. Please <strong>add a voting, and select it</strong>.
            </div>
        </#if>
    </#list>
    -->
</#attempt>
<#-- HTML  -->
<div id="voting-details">
<#if canEdit == 1>
    <a id="editVotingProcess" href=${editUrl}> Edit </a>
</#if>
<#--
<h1>${TitleVoting.getData()}</h1>
<br>-->
<h4>${DescriptionVoting.getData()}</h4>
<br>

<div id="vote-process" >
<div class="row">
<#if canEdit == 1>
    <div class="col-md-3">
        <a id="notifyUsers"  >Notify to vote</a>
    </div>
</#if>
</div>

<div id="notificationPopUp" class="popUpNotification">

  <!-- Modal content -->
  <div class="popUp-content">
      <a class="close" href="#">×</a>
      <h2>Notify for Vote</h2>
    
<hr>
    <div class="content-label">Please enter notification title</div>
    <div id="block_problem" class="content-placeholder">
        <input class="NotificationInput" type="text" id="titleOfNotification"></input>
    </div>
    <div class="content-label">Please enter notification body</div>
    <div id="block_problem" class="content-placeholder">
        <input class="NotificationInput" type="text" id="bodyOfNotification"></input>
    </div>
<hr>
    <button id="sendNotification" class="BottonNotification">Send Notification</button>
  </div>

</div>

<br>

<#-- GET INSTANCE PORTLET  -->
<@liferay_portlet["runtime"]
    instanceId= "${articleResourcePrimKey}"
    portletName="com_liferay_polls_web_portlet_PollsDisplayPortlet_INSTANCE_pollsVoting"
/>




<#-- HTML  -->
<#if hasPoll>
    <div>
        <#if isExpired>
            <p>Voting has now closed</p>
        <#else>
            <a id="deleteVote" href ="">Change Vote</a>
        </#if>
    <#if canEdit == 1 && !isExpired>
            <a id="closeVote" href ="" >Close Vote</a>
    </#if>
    </div>
</#if>


<#-- check if external vote and deciding vote have values -->
<#if ReportVisibilityVoting.getData() == "Yes">
    <#attempt>
        <#assign votingJournal = journalArticleService.fetchLatestArticle(articleResourcePrimKey?number) />
        <#assign document = saxReaderUtil.read(votingJournal.getContent())/>
        <#assign selectNodes = document.selectNodes("//root//dynamic-element[@name='ExternalVoting']//dynamic-content") />
        <#assign nodesSize = selectNodes?size />
    <#recover>
        <#assign nodesSize = 0 />
        <#assign externalVotes = "-" /> 
    </#attempt>
    <#if nodesSize != 0 >
        <br>
        <h2>External Votes</h2>
        <ul>
            <#list selectNodes as node> 
                <li>${node.getText()}</li>
            </#list>
        </ul>           
    </#if>
    <#attempt>
        <#assign votingJournal = journalArticleService.fetchLatestArticle(articleResourcePrimKey?number) />
        <#assign document = saxReaderUtil.read(votingJournal.getContent())/>
        <#assign selectDecidingNode = document.selectSingleNode("//root//dynamic-element[@name='DecidingVoting']//dynamic-content") />
        <#assign nodeDecidingSize = selectDecidingNode?size />
        <#assign decidingVote = DecidingVoting.getData() />
    <#recover>
        <#assign nodeDecidingSize = 0 />
        <#assign decidingVote = "-" /> 
    </#attempt>
    <#if nodeDecidingSize != 0 && canEdit == 1>
        <br>
        <h2>Deciding Vote</h2>
        <h4>${decidingVote}</h4>
        <br>
    </#if>

    <div id = "reportTable">  
    	<div class="hideReportTablecontent">
            <table id="table0">
                <tr>
                <#if pollsDescription?has_content>
			        <th > <h1>${pollsDescription} </h1></th>
		        </#if>
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
        <br>
        <br>
        <div class="hideReportTablecontent">
            <table id="table2">
                <tr>
                <th > <h1>Vote Outcome</h1></th>
                </tr>
            </table>
        </div>
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
<#--
<@liferay_portlet["runtime"]
    instanceId= "${articleResourcePrimKey}"
    portletName="com_liferay_comment_page_comments_web_portlet_PageCommentsPortlet"
/>-->
</div>

<#--JAVASCRIPT  -->
<script>
var instanceId= ${articleResourcePrimKey};
    $(document).ready(function(){
        var questionId =  document.getElementById("_com_liferay_polls_web_portlet_PollsDisplayPortlet_INSTANCE_"+instanceId+"_questionId").value;
        /*
        var url = new URLSearchParams(window.location.search);
        url.set('questionId',questionId);
        window.location.hash = url;
        */
        $("#deleteVote").click(function(){
                Liferay.Service(
                '/recsites.recsites/delete_polls_vote',
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

    $(document).ready(function(){
        var questionId =  document.getElementById("_com_liferay_polls_web_portlet_PollsDisplayPortlet_INSTANCE_"+instanceId+"_questionId").value;
        /*
        var url = new URLSearchParams(window.location.search);
        url.set('questionId',questionId);
        window.location.hash = url;
        */
        $("#closeVote").click(function(){
                Liferay.Service(
                '/recsites.recsites/closing_polls_question',
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


<#--- POPUP JAVASCRIPT AND SEND CUSTOM NOTIFICATION --->

<script>

$(document).ready(function(){
    $("#sendNotification").click(function(){

    var notificationTitle = document.getElementById("titleOfNotification").value;
    var notificationBody  = document.getElementById("bodyOfNotification").value;
    var notifyUsers = document.getElementById("sendNotification");

    var modal = document.getElementById("notificationPopUp");
        if ( notificationTitle != "" & notificationBody != "" ){
            Liferay.Service(
                  '/messages.messages/send_notification_031',
                  {
                    companyId: ${companyId} ,
                    groupId: ${siteGroupId} ,
                    userId: ${userId} ,
                    roleNames: 'Site Members',
                    VotingLink: '${currentUrl}',
                    titleOfNotification: notificationTitle ,
                    bodyOfNotification: notificationBody 
                  },
                  function(obj) {
                    console.log(obj);
                    location.reload();
                  }
            );
        }
    });
});

// Get the modal
var modal = document.getElementById("notificationPopUp");

// Get the button that opens the modal
var btn = document.getElementById("notifyUsers");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks the button, open the modal 
btn.onclick = function() {
  modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
  modal.style.display = "none";
  
}
</script>

<#-- RELOAD PAGE AFTER VOTING TO CHANGE THE VOTE  -->	

<script>

$(document).ready(function(){
    $( "button:contains('Vote')" ).click(function(){
     setTimeout(function(){ location.reload(); }, 250);   
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
  
   var nameFile = "${TitleVoting.getData()}";
   
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

        doc.text(20, 10, '${TitleVoting.getData()}');
        doc.text(20, 18, '${DescriptionVoting.getData()}');

        var externalVoteList=[<#if selectNodes?has_content><#list selectNodes as node>"${node.getText()}",</#list></#if>];
        console.log(externalVoteList);
       
        doc.text(20, 26, 'External Votes:');
        var docIterator = 34;
        var externalVoteSize = externalVoteList.length;
        for(var i=0; i < externalVoteSize; i++){
            console.log(externalVoteList[i], "-", docIterator);
            doc.text(20, docIterator, externalVoteList[i]);
            docIterator += 8;
        }        
        docIterator += 8;
        var decidingValue = "${DecidingVoting.getData()}";
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
