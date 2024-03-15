<#-- GETTING DYMANIC URL PARAMETERS -->
<#assign layoutService = serviceLocator.findService ("com.liferay.portal.kernel.service.LayoutLocalService") />
<#assign ddmStructureService = serviceLocator.findService("com.liferay.dynamic.data.mapping.service.DDMStructureLocalService") />
<#assign recFormArticleService = serviceLocator.findService("com.everis.cproposal.service.recFormArticleLocalService") />

<#assign listddmStructure =  ddmStructureService.getDDMStructures(0, ddmStructureService.getDDMStructuresCount()) />

<#assign instanceId = themeDisplay.getPortletDisplay().getInstanceId() />

<#if entries?has_content>    
	<#list entries as curEntry>
	    <#assign renderer = curEntry.getAssetRenderer() />
	    <#assign className = renderer.getClassName() />
	</#list>	
    <#assign journalArticle = renderer.getArticle() />
    <#assign plid = journalArticle.getLayout().getPlid() />
</#if>


<#list listddmStructure as ddm>
    <#if ddm.getNameCurrentValue() == "CONSULTATION" >
        <#assign structureKey = ddm.getStructureKey() />
        <#assign groupId =  ddm.getGroupId() />
    </#if>
</#list>

<#-- END -->
<#assign link = "/group/guest/add-consultation">


<!-- WIP Consultation -->
<#assign Imagerute = themeDisplay.getPathThemeImages()+"/forms/chevrom.svg" />

<#assign userService = utilLocator.findUtil("com.liferay.portal.kernel.service.UserLocalService") />
<#assign rolService = utilLocator.findUtil("com.liferay.portal.kernel.service.RoleLocalService") />
<#assign userId = themeDisplay.getUserId() />
<#assign user = userService.getUser(userId) />
<#assign showAddButton = 0 />
<#assign userRoles = user.getRoles()/>
<#list userRoles as rol>
    <#if rol.getName() == "Change_Management_Team" || rol.getName() == "Administrator" >
        <#assign showAddButton = 1 />
    </#if>
</#list>
        
<div id="consultation-register">
    <div id="header-consult">
        <div class="left">
            <h1 id="title-consult">Consultations</h1>
        </div>
        <#if showAddButton == 1>
            <div class="right">
                <input id="addconsult" class="hide" type="submit" value="Add Consultation"/>
                <#-- DEV -->
                <#assign url = link />
                <a id="addconsult" href="${url}">Add Consultation</a>
            </div>
        </#if>
    </div>
   
    <div id="table-consult"> 
    <table id="instanceReportTable" class="margin-center">
    <thead>
        <tr>
            <th class="left-align-text">Title of Consultation</th>
            <th class="left-align-text">Closing date</th>
            <th class="left-align-text">Linked Change Proposal ID</th>
            <th class="left-align-text"></th>
        </tr>
    </thead>

    <tbody>   

    <!-- entries size ${entries?size}--> 

<#if entries?has_content>
    
	<#list entries as curEntry>
	    <#assign renderer = curEntry.getAssetRenderer() />
	    <#assign className = renderer.getClassName() />
	   
    <#if className == "com.liferay.journal.model.JournalArticle">
        <#assign journalArticle = renderer.getArticle() />
        <#assign articlleCustomTitle = journalArticle.getExpandoBridge().getAttribute("customTitle") />

        <#assign urlTitle = journalArticle.getUrlTitle() />
        <#assign document = saxReaderUtil.read(journalArticle.getContent())/>
        <#assign assetURL = "/group/guest/-/" + urlTitle />
	      
	    <#assign textIDChangeProposal = document.valueOf("//dynamic-element[@name='TextIDChangeProposal']/dynamic-content/text()") />
	    <#assign textTitleConsultation = journalArticle.getTitle() />
	    <#assign textDate = document.valueOf("//dynamic-element[@name='TextDate']/dynamic-content") />
        
        <#assign textLinkChangeProposalID = document.valueOf("//dynamic-element[@name='ConsultationLink']/dynamic-content/text()") />
        <#assign xPathSelector = saxReaderUtil.createXPath("//root//dynamic-element[@name='Consultation_TargetedAudience']//dynamic-content//option") />
        <#assign impactNodes = xPathSelector.selectNodes(document) />
                <#assign isValid = false>
                <#list impactNodes as nodes>
                    <#if nodes.getText()?contains("Consultation")>
                    <#assign segmentConsultation = nodes.getText() />
                    <#else>
                        <#assign segmentConsultation = "Consultation-" + nodes.getText()/>
                    </#if>

                    <#if recFormArticleService.isCProposalUserValid(userId,segmentConsultation)>
                        <#assign isValid = true/>
                        <#break>
                    </#if>
                    <#list userRoles as rol>
                        <#if rol.getName() == "Change_Management_Team" || rol.getName() == "Administrator" >
                            <#assign isValid = true/>
                            <#break>
                        </#if>
                    </#list>
                </#list>
                    <#if isValid>
	
                    <tr>
                        <td>
                        <#if articlleCustomTitle != "" >
                            <a href="${assetURL}"> ${articlleCustomTitle} </a>
                            <#else>
                            <a href="${assetURL}"> ${journalArticle.getTitle()} </a>
                            </#if>
                        </td>
                        <td>
                       
                            <#assign IA_CommentsDeadline_Data = getterUtil.getString(textDate)>
 
                                    <#if validator.isNotNull(IA_CommentsDeadline_Data)>
                                        <#assign IA_CommentsDeadline_DateObj = dateUtil.parseDate("yyyy-mm-dd", IA_CommentsDeadline_Data, locale)>
                                    
                                        ${dateUtil.getDate(IA_CommentsDeadline_DateObj, "dd/mm/yyyy", locale)}
                                        <#else> 
                                        <p>No date</p>
                                    </#if>
                        </td>
                        <td>
                            ${textIDChangeProposal}
                        </td>
                        <td>
                            <a href="${assetURL}" class="open"><img src="${Imagerute}"/></a>
                        </td>
                    </tr>   
                </#if>
		</#if>
	</#list>
	
</#if>
</tbody>

</table>
</div>
</div>
<style>

    #addconsult:hover {
        text-decoration: none;
    }

</style>