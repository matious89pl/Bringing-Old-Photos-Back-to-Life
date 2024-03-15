<#assign userService = utilLocator.findUtil("com.liferay.portal.kernel.service.UserLocalService") />							
<#assign rolService = utilLocator.findUtil("com.liferay.portal.kernel.service.RoleLocalService") />							
<#assign recFormArticleService = serviceLocator.findService("com.everis.cproposal.service.recFormArticleLocalService") />
<#assign RoleServiceUtil = utilLocator.findUtil("com.liferay.portal.kernel.service.UserGroupRoleLocalService") />


<#assign userId = themeDisplay.getUserId() />
<#assign groupId = themeDisplay.getScopeGroupId() />
<#assign user = userService.getUser(userId)>							
<#assign userRoles = user.getRoles() />
<#assign siteRoles = RoleServiceUtil.getUserGroupRoles(userId, groupId) />
<#assign userEmail = user.getEmailAddress() />

<#assign memberCommittee = 0 />
<#assign isAdmin = 0 />

<#list userRoles as rol>
    <#if rol.getName() == "Administrator" || rol.getName() == "Committee_Secretariat_Admi">		
        <#assign isAdmin = 1 />							
    </#if>							
</#list>


<#assign filterImg = themeDisplay.getPathThemeImages()+"/forms/filter.svg" />
<#assign chevromImg = themeDisplay.getPathThemeImages()+"/forms/chevrom.svg" />

<#assign siteName = themeDisplay.getSiteGroupName() />
<#assign siteName = siteName?replace(" ", "-") />
<#assign siteName = siteName?lower_case />


<#assign ddmStructureService = serviceLocator.findService("com.liferay.dynamic.data.mapping.service.DDMStructureLocalService") />

<#assign listddmStructure =  ddmStructureService.getDDMStructures(0, ddmStructureService.getDDMStructuresCount()) />

<#-- <#list listddmStructure as ddm>
    <#if ddm.getNameCurrentValue() == "COMMITTEE-ACTION LOG" && ddm.getGroupId() == groupId>
        <#assign structureKey = ddm.getStructureKey() />
    </#if>
</#list> -->

<#-- <#assign url = "/group/"+siteName+"/~/control_panel/manage?p_p_id=com_liferay_journal_web_portlet_JournalPortlet&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&_com_liferay_journal_web_portlet_JournalPortlet_mvcPath=%2Fedit_article.jsp&_com_liferay_journal_web_portlet_JournalPortlet_redirect=%2Fgroup%2F"+siteName+"/action-log&_com_liferay_journal_web_portlet_JournalPortlet_groupId="+groupId+"&_com_liferay_journal_web_portlet_JournalPortlet_folderId=0&_com_liferay_journal_web_portlet_JournalPortlet_ddmStructureKey="+structureKey+"&p_p_auth=QBEeaQ69" />
-->
<#assign url = "/group/"+siteName+"/action-log-form" />

<div id="committee-actionLog">

    <#-- ACTION LOG VISIBLE --> 
    <h1 id="committeeTitle" class="col-md-9">Live Action Log</h1>
    <div id="myApplicationButtons">
        <#if isAdmin == 1>  
            <a id="newAction" href="${url}">Make an action</a>
        </#if>
    </div>
  
    <div id="committeeContent">
            
        <table id="committeeTableList">
            <thead>
                <tr>
                    <th class="left-align-text"></th>
                    <th class="left-align-text">ID</th>
                    <th class="left-align-text">Description</th>
                    <th class="left-align-text">Assignee</th>
                    <th class="left-align-text">Due Date</th>
                    <th class="left-align-text">Next Update Due </th>
                    <th class="left-align-text">Status</th>
                    <th class="left-align-text">Completion Date</th>
                    <th class="left-align-text"></th>
                </tr>
            </thead>
    <#if entries?has_content>
    ENTRIES: ${entries?size}
        <tbody>
        	<#list entries as curEntry>
        	    <#assign renderer = curEntry.getAssetRenderer() />
                <#assign className = renderer.getClassName() />
                
                <#if className == "com.liferay.journal.model.JournalArticle">
                    <#assign journalArticle = renderer.getArticle() />
                    <#assign document = saxReaderUtil.read(journalArticle.getContent())/>
                    <#assign articleId = journalArticle.getArticleId() /> 
                    <#assign articleResourcePK = journalArticle.getResourcePrimKey() />
                    <#-- <#assign editUrl = "/group/"+siteName+"/~/control_panel/manage?p_p_id=com_liferay_journal_web_portlet_JournalPortlet&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&_com_liferay_journal_web_portlet_JournalPortlet_mvcPath=%2Fedit_article.jsp&_com_liferay_journal_web_portlet_JournalPortlet_redirect=%2Fgroup%2F"+siteName+"/action-log&_com_liferay_journal_web_portlet_JournalPortlet_groupId="+groupId+"&_com_liferay_journal_web_portlet_JournalPortlet_folderId=0&_com_liferay_journal_web_portlet_JournalPortlet_articleId="+articleId+"&_com_liferay_journal_web_portlet_JournalPortlet_version=1.0&p_p_auth=1a9RI7GP" /> -->
                    <#assign editUrl = "/group/"+siteName+"/action-log-form/?mode=EDIT&actionLogId="+articleResourcePK/>

                    <#assign id = document.valueOf("//dynamic-element[@name='ActionLog_ID']/dynamic-content/text()") />
                    <#assign descp = document.valueOf("//dynamic-element[@name='ActionLog_Description']/dynamic-content/text()") />
                    <#assign assine = document.valueOf("//dynamic-element[@name='ActionLog_AssigneeEmail']/dynamic-content/text()") />
                    <#assign due = document.valueOf("//dynamic-element[@name='ActionLog_DueDate']/dynamic-content/text()") />
                    <#assign nex = document.valueOf("//dynamic-element[@name='ActionLog_NextUpdateDue']/dynamic-content/text()") />
                    <#assign status = document.valueOf("//dynamic-element[@name='ActionLog_Status']/dynamic-content/text()") />
                    <#assign comple = document.valueOf("//dynamic-element[@name='ActionLog_CompletionDate']/dynamic-content/text()") />
                    
                    <#assign creteDate = journalArticle.getCreateDate()?date />
                    <#assign pmURL = "/group/guest/-/" + journalArticle.getUrlTitle() />
                    
                </#if>
       
        <#if (userEmail?string == assine || isAdmin == 1) && status == "In Progress">
             
            <tr>    
                <td class="left-align-text"></td>
                
                <#-- ID -->
                <td class="left-align-text">${id}</td>
                <#-- Description -->
        	    <td class="left-align-text">${descp}</td>
                <#-- Assignee(s) -->
                <td class="left-align-text">${assine}</td>
                <#-- Due Date -->
                <td class="left-align-text">${due}</td>
                <#-- NextUpdateDue -->
                <td class="left-align-text">${nex}</td>
                <#-- Status -->
                <td class="left-align-text">${status}</td>
                <#-- Completion Date -->
                <td class="left-align-text">${comple}</td>
                
                <td><a href="${editUrl}"><img class="img" src="${chevromImg}"></a></td>


        	</tr>
        </#if>
        	</#list>
         </tbody>
        </#if>
        </table>
        
        <#assign groupId= themeDisplay.getLayout().getGroupId() />
        <#assign userId = themeDisplay.getUserId() />

        
<div id="actionStatus" class="overlaySolutions">							
	<div class="popupSolutions">						
		<h2>Update Status</h2>					
        <a class="close" href="#" onclick="closeForm()">&times;</a>
		<select  id="selectStatus">
            <option>In progress</option>
            <option>Closed</option>
            <option>Completed</option>
        </select>

        <hr>							
        <button id="updateButton" href="#" class="float-right lfr-ddm-form-pagination-next btn btn-primary">Update</button>											
	</div>						
</div>				


    </div>

    <#-- ACTION LOG CLOSED -->
    <h1 id="committeeTitleClosed" class="col-md-9">Closed Action Log</h1>

    <div id="committeeContentClosed">            
        <table id="committeeTableListClosed">
            <thead>
                <tr>
                    <th class="left-align-text"></th>
                    <th class="left-align-text">ID</th>
                    <th class="left-align-text">Description</th>
                    <th class="left-align-text">Assignee</th>
                    <th class="left-align-text">Due Date</th>
                    <th class="left-align-text">Next Update Due </th>
                    <th class="left-align-text">Status</th>
                    <th class="left-align-text">Completion Date</th>
                </tr>
            </thead>
      
        <#if entries?has_content>
        <tbody>
        	<#list entries as curEntry>
        	    <#assign renderer = curEntry.getAssetRenderer() />
                <#assign className = renderer.getClassName() />
                
                <#if className == "com.liferay.journal.model.JournalArticle">
                    <#assign journalArticle = renderer.getArticle() />
                    <#assign document = saxReaderUtil.read(journalArticle.getContent())/>
                    <#assign articleId = journalArticle.getArticleId() />
                    <#assign articleResourcePK = journalArticle.getResourcePrimKey() />
                    
                    <#assign id = document.valueOf("//dynamic-element[@name='ActionLog_ID']/dynamic-content/text()") />
                    <#assign descp = document.valueOf("//dynamic-element[@name='ActionLog_Description']/dynamic-content/text()") />
                    <#assign assine = document.valueOf("//dynamic-element[@name='ActionLog_AssigneeEmail']/dynamic-content/text()") />
                    <#assign due = document.valueOf("//dynamic-element[@name='ActionLog_DueDate']/dynamic-content/text()") />
                    <#assign nex = document.valueOf("//dynamic-element[@name='ActionLog_NextUpdateDue']/dynamic-content/text()") />
                    <#assign status = document.valueOf("//dynamic-element[@name='ActionLog_Status']/dynamic-content/text()") />
                    <#assign comple = document.valueOf("//dynamic-element[@name='ActionLog_CompletionDate']/dynamic-content/text()") />
                    
                    <#assign creteDate = journalArticle.getCreateDate()?date />
                    <#assign pmURL = "/group/guest/-/" + journalArticle.getUrlTitle() />
                    
                    <#-- <#assign editUrl = "/group/"+siteName+"/~/control_panel/manage?p_p_id=com_liferay_journal_web_portlet_JournalPortlet&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&_com_liferay_journal_web_portlet_JournalPortlet_mvcPath=%2Fedit_article.jsp&_com_liferay_journal_web_portlet_JournalPortlet_redirect=%2Fgroup%2F"+siteName+"/action-log&_com_liferay_journal_web_portlet_JournalPortlet_groupId="+groupId+"&_com_liferay_journal_web_portlet_JournalPortlet_folderId=0&_com_liferay_journal_web_portlet_JournalPortlet_articleId="+articleId+"&_com_liferay_journal_web_portlet_JournalPortlet_version=1.0&p_p_auth=1a9RI7GP" /> -->
                    <#assign editUrl = "/group/"+siteName+"/action-log-form/?mode=EDIT&actionLogId="+articleResourcePK/>
                </#if>

                <#if (userEmail?string == assine || isAdmin == 1) && status != "In Progress">        
                    <tr>    
                        <td class="left-align-text"></td>
                        
                        <#-- ID -->
                        <td class="left-align-text">${id}</td>
                        <#-- Description -->
                        <td class="left-align-text">${descp}</td>
                        <#-- Assignee(s) -->
                        <td class="left-align-text">${assine}</td>
                        <#-- Due Date -->
                        <td class="left-align-text">${due}</td>
                        <#-- NextUpdateDue -->
                        <td class="left-align-text">${nex}</td>
                        <#-- Status -->
                        <td class="left-align-text">${status}</td>
                        <#-- Completion Date -->
                        <td class="left-align-text">${comple}</td>
                        
                        <td><a href="${editUrl}"><img class="img" src="${chevromImg}"></a></td>


                    </tr>
                </#if>

        	</#list>
         </tbody>
 
        </#if>    
        </table>
    </div>
</div>




<script>
  
        
        function openForm(articleId){
            var pop = document.getElementById("actionStatus");
            pop.style.display = "block";  
            pop.style.opacity = "1";
            pop.style.visibility = "visible";
            
            $("#updateButton").click(function(){
                var newStatus = $("#selectStatus").val();	
    
                Liferay.Service('/cproposal.recformarticle/update-impact-tab-journal-article', 
                {
                    fieldNameIMP: 'Status_ActionLog',
                    newTextIMP: newStatus,
                    groupId:  ${groupId},							
                    articleId: articleId
                },
                function(obj) {
    				console.log(obj);
    			});
    			setTimeout(function(){
                   window.location.reload(1);
                }, 1000);
            });
        }
        
         function closeForm() {     
             var pop = document.getElementById("actionStatus");
             pop.style.display = "none";                           
          }       
        
   
</script>