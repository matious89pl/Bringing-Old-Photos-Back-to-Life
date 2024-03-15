<#assign Imagerute = themeDisplay.getPathThemeImages()+"/forms/chevrom.svg" />

<#-- Roles -->

<#assign userService = utilLocator.findUtil("com.liferay.portal.kernel.service.UserLocalService") />
<#assign rolService = utilLocator.findUtil("com.liferay.portal.kernel.service.RoleLocalService") />
<#assign organisationService = utilLocator.findUtil("com.liferay.portal.kernel.service.OrganizationLocalService") />


<#-- FolderId -->
 <#assign dLAppLocalService = serviceLocator.findService("com.liferay.document.library.kernel.service.DLAppLocalService") />
 
 
<#assign userId = themeDisplay.getUserId() />
<#assign user = userService.getUser(userId) />
<#assign groupId= themeDisplay.getLayout().getGroupId() />

<#assign userRoles = user.getRoles()/>
<#assign userOrganisations = user.getOrganizations()/>

<#assign showAddButton = 0 />

<#list userOrganisations as organisations>
     <#if organisations.name == "Responsible Service Provider" >
        <#assign showAddButton = 1 />
     </#if>
</#list>

<#list userRoles as rol>
    <#if rol.getName() == "Administrator" || rol.getName() == "Change_Management_Team" || rol.getName() == "REC_Contract_Managers" || rol.getName() == "Code_Manage_Master_Admin_User" >
        <#assign showAddButton = 1 />
    </#if>
</#list>

<#attempt>    
<div id="consultation-register">
    <div id="header-consult">
        <div class="left">
            <h1 id="title-consult">Category 3 Change Log</h1>
        </div>
        <#if showAddButton == 1>
            <div class="right">
                <input id="addconsult" type="submit" class="hide" value="Add Category 3">
  <a id="addconsult" href="/group/guest/~/control_panel/manage?p_p_id=com_liferay_journal_web_portlet_JournalPortlet&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&_com_liferay_journal_web_portlet_JournalPortlet_mvcPath=%2Fedit_article.jsp&
_com_liferay_journal_web_portlet_JournalPortlet_redirect=%2Fgroup%2Fguest%2Fcategory-3&
_com_liferay_journal_web_portlet_JournalPortlet_groupId=37141&_com_liferay_journal_web_portlet_JournalPortlet_folderId=0&
_com_liferay_journal_web_portlet_JournalPortlet_ddmStructureKey=81939&p_p_auth=W5gQ9BdM">Add Category 3</a>






            </div>
        </#if>
    </div>
   
    <div id="table-consult"> 
    <table id="instanceReportTable" class="margin-center">
    <thead>
        <tr>
            <th class="left-align-text">Category 3 Change ID</th>
            <th class="left-align-text">Change Proposer</th>
            <th class="left-align-text">Category 3 Document</th>
            <th class="left-align-text">Description and Reason for Category 3 Change</th>
            <th class="left-align-text">Organisation</th>
            <th class="left-align-text">Status</th>
            <th class="left-align-text">Updated Document</th>
            <th class="left-align-text">Consultation responses</th>
            <th class="left-align-text">Implementation Date</th>
            <th class="left-align-text">Asociated Category 1 or 2 Changes</th>
            <th class="left-align-text"></th>
        </tr>
    </thead>

      <tbody>
        <#if entries?has_content>
            <#list entries as curEntry>

                <#assign renderer = curEntry.getAssetRenderer() />
                <#assign className = renderer.getClassName() />
                
            <#if className == "com.liferay.journal.model.JournalArticle">
                <#assign journalArticle = renderer.getArticle() />
                <#assign urlTitle = journalArticle.getUrlTitle() />
                <#assign document = saxReaderUtil.read(journalArticle.getContent())/>
				<#assign assetURL = "/web/guest/-/" + urlTitle />
                <#assign id = document.valueOf("//dynamic-element[@name='ChangeIdCatetory3']/dynamic-content/text()") />
                <#assign changeProposer = document.valueOf("//dynamic-element[@name='EmailAddressCategory3']/dynamic-content/text()") />
                <#assign category3DocumentCategory3 = document.valueOf("//dynamic-element[@name='Category3DocumentCategory3']/dynamic-content/text()") />
                <#assign descriptionCategory3 = document.valueOf("//dynamic-element[@name='DescriptionAndReasonForChangeCategory3']/dynamic-content/text()") />
                <#assign organisationCategory3 = document.valueOf("//dynamic-element[@name='OrganisationCategory3']/dynamic-content/text()") />
                <#assign statusCategory3 = document.valueOf("//dynamic-element[@name='StatusCategory3']/dynamic-content/text()") />
                <#assign attachSupportingDocumentCategory3 = document.valueOf("//dynamic-element[@name='AttachSupportingDocumentCategory3']/dynamic-content/text()") />
                <#assign consultationResponses = document.valueOf("//dynamic-element[@name='ConsultationResponsesCategory3']/dynamic-content/text()") />
                <#assign proposedImplementationDateCategory3 = document.valueOf("//dynamic-element[@name='ProposedImplementationDateCategory3']/dynamic-content/text()") />
                <#assign asociatedCategory1or2 = document.valueOf("//dynamic-element[@name='AssociatedCategory1or2ChangesCategory3']/dynamic-content/text()") />
                
                
                  
                 <#-- CREATE JSON OBJECT1 -->
    		    <#assign document_map_attachSupportingDocumentCategory3 = jsonFactoryUtil.createJSONObject(attachSupportingDocumentCategory3)>

    		    <#-- END -->
    		    
    		    <#-- GET FOLDER ID -->
    		    <#attempt>
    		    <#if attachSupportingDocumentCategory3?has_content && attachSupportingDocumentCategory3 != "" >
    		     <#assign uuid1 = document_map_attachSupportingDocumentCategory3.getString("uuid") />
    		     <#assign fileEntry1 = dLAppLocalService.getFileEntryByUuidAndGroupId(uuid1, groupId) />

                 <#assign folderId1 = fileEntry1.getFolderId() />
                 </#if>
				 <#recover>
				    <#assign folderId1 = -1 />
				 </#attempt>
                 
                <#-- END -->

                <#-- CREATE JSON OBJECT2 -->
    		    <#assign document_map_consultationResponsesDocumentCategory3 = jsonFactoryUtil.createJSONObject(consultationResponses)>

    		    <#-- END -->
    		    
    		    <#-- GET FOLDER ID -->
				<#attempt>
                 <#if consultationResponses?has_content && consultationResponses != "" >
    		     <#assign uuid2 = document_map_consultationResponsesDocumentCategory3.getString("uuid") />
    		     <#assign fileEntry2 = dLAppLocalService.getFileEntryByUuidAndGroupId(uuid2, groupId) />

                 <#assign folderId2 = fileEntry2.getFolderId() />
                 </#if>
				 <#recover>
				    <#assign folderId2 = -1 />
				 </#attempt>
                <#-- END -->

                <tr>
                    <td>
                        <#if assetURL != "">
                            <p class="ellipsis"><a href="${assetURL}">${id}</a></p>
                        <#else>
                            <p>else </p>
            	            <p class="ellipsis">${id}</p>
            	        </#if>
                        
                    </td>
                    <td>
                        <#if changeProposer?matches("^(.+)@(\\S+)$")>
                           <p class="ellipsisProposar">${changeProposer}</p>
                        <#else>
                          <span style="color:red;">Enter a valid Email</span> 
                        </#if>
                    </td>
                   
                    <td>
                        ${category3DocumentCategory3}
                    </td>
                    <td>
                        <p class="ellipsisDescription">${descriptionCategory3}</p>
                    </td>
                     <td>
                         ${organisationCategory3}
                    </td>
                    <td>
                        ${statusCategory3}
                    </td>
                    <td>
                    <#if attachSupportingDocumentCategory3?has_content && attachSupportingDocumentCategory3 != "" >
						<#if folderId1 != -1>
							<a href="/documents/${groupId}/${folderId1}/${document_map_attachSupportingDocumentCategory3.getString("title")}/${document_map_attachSupportingDocumentCategory3.getString("uuid")}">${document_map_attachSupportingDocumentCategory3.getString("title")}</a>
						<#else>
							<p> No document </p>
						</#if> 	
                    </#if>   
                    </td>
                    <td>
                    <#if consultationResponses?has_content && consultationResponses != "" >
						<#if folderId2 != -1>
							<a href="/documents/${groupId}/${folderId2}/${document_map_consultationResponsesDocumentCategory3.getString("title")}/${document_map_consultationResponsesDocumentCategory3.getString("uuid")}">${document_map_consultationResponsesDocumentCategory3.getString("title")}</a>
						<#else>
							<p> No document </p>
						</#if>
					</#if>   
                    </td>
                    <td>
                    <#assign IA_CommentsDeadline_Data = getterUtil.getString(proposedImplementationDateCategory3)>

                        <#if validator.isNotNull(IA_CommentsDeadline_Data)>
                        	<#assign IA_CommentsDeadline_DateObj = dateUtil.parseDate("yyyy-MM-dd", IA_CommentsDeadline_Data, locale)>
                        
                        	${dateUtil.getDate(IA_CommentsDeadline_DateObj, "dd MMM yyyy", locale)}
                        </#if>
                    </td>
                    <td>
                        <#if validator.isNotNull(asociatedCategory1or2)>
                            <a href="${asociatedCategory1or2}" target="_blank">link</a>
                       </#if>
                    </td>
                     <td>
                       <a href="${assetURL}" class="open"><img src="${Imagerute}"</a>
                    </td>
                    
                </tr>
            
            </#if>
            </#list>
        </#if>
    </tbody>
</table>
</div>
</div>

<#recover>
   <#list userRoles as roles>
    <#if roles.getName() == "Administrator" >
        <div class="alert alert-info">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            Ops! ... please contact with site administrator. 
        </div>
    </#if>
   </#list>
</#attempt>

<script>
$(document).ready(function(){
        $("#addconsult").click(function(){
            let url = "/group/guest/~/control_panel/manage?p_p_id=com_liferay_journal_web_portlet_JournalPortlet&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&_com_liferay_journal_web_portlet_JournalPortlet_mvcPath=%2Fedit_article.jsp&_com_liferay_journal_web_portlet_JournalPortlet_redirect=%2Fgroup%2Fguest%2Fcategory-3&_com_liferay_journal_web_portlet_JournalPortlet_groupId=37141&_com_liferay_journal_web_portlet_JournalPortlet_folderId=0&_com_liferay_journal_web_portlet_JournalPortlet_ddmStructureKey=81939&p_p_auth=W5gQ9BdM"
            window.location.href = url;
        });
    });
</script>

<script>
                $('.ellipsisProposer').mouseover(function(){
                    $('.ellipsisDescription').css({
                        "width": "100px"
                    });
                    $('.ellipsisProposer').css({
                        "width": "280px"
                    });
                });
                
                $('.ellipsisProposer').mouseout(function(){
                    $('.ellipsisDescription').css({
                        "width": "220px"
                    });
                    $('.ellipsisProposer').css({
                        "width": "110px"
                    });
                });
</script>