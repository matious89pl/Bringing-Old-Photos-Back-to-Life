<#-- GETTING DYMANIC URL PARAMETERS -->
<#assign plid = themeDisplay.getPlid() />
<#assign instanceId = themeDisplay.getPortletDisplay().getInstanceId() />

<#assign ddmStructureService = serviceLocator.findService("com.liferay.dynamic.data.mapping.service.DDMStructureLocalService") />
<#assign listddmStructure =  ddmStructureService.getDDMStructures(0, ddmStructureService.getDDMStructuresCount()) />
<#list listddmStructure as ddm>
    <#if ddm.getNameCurrentValue() == "IMPACT-ASSESSMENT" >
        <#assign structureKey = ddm.getStructureKey() />
        <#assign groupId =  ddm.getGroupId() />
    </#if>
</#list>

<#-- END -->
<#assign link = "/group/guest/add-assessment" />
<#assign Imagerute = themeDisplay.getPathThemeImages()+"/forms/chevrom.svg" />

<#assign userService = utilLocator.findUtil("com.liferay.portal.kernel.service.UserLocalService") />
<#assign rolService = utilLocator.findUtil("com.liferay.portal.kernel.service.RoleLocalService") />
<#assign recFormArticleService = serviceLocator.findService("com.everis.cproposal.service.recFormArticleLocalService") />
<#assign userId = themeDisplay.getUserId() />

<#assign user = userService.getUser(userId) />
<#assign showAddButton = 0 />
<#assign userRoles = user.getRoles()/>
<#list userRoles as rol>
    <#if rol.getName() == "Change_Management_Team" || rol.getName() == "Administrator" >
        <#assign showAddButton = 1 />
    </#if>
</#list>

<div id="impact-assessment">
<div id="header-impact">
    <div class="left">
        <h1 id="register-title">Impact Assessments</h1>
    </div>
    <#if showAddButton == 1>
        <div class="right">
            <input id="addimpact" class="hide" type="submit" value="Add Impact Assessment">

            <#assign url = link />
            <a id="addimpact" href="${url}">Add Impact Assessment</a>
        </div>
    </#if>
</div>
    
<div id="table-impact"> 
<table id="instanceReportTable" class="margin-center">
    <thead>
        <tr>
            <th class="left-align-text">IA Title</th>
            <th class="left-align-text">Change Proposal ID</th>
            <th class="left-align-text">IA Type</th>
            <th class="left-align-text">Targeted Audience</th>
            <th class="left-align-text">Closing date</th>
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
                <#assign articlleCustomTitle = journalArticle.getExpandoBridge().getAttribute("customTitle") />
             <#assign urlTitleToshow = journalArticle.getTitle() />
                <#assign urlTitle = journalArticle.getUrlTitle() />
                <#assign document = saxReaderUtil.read(journalArticle.getContent())/>
				<#assign assetURL = "/group/guest/-/" + urlTitle />
                <#assign id = document.valueOf("//dynamic-element[@name='IA_CPID']/dynamic-content/text()") />
                <#assign type = document.valueOf("//dynamic-element[@name='IA_Type']/dynamic-content/text()") />
                <#assign target = document.valueOf("//dynamic-element[@name='IA_TargetedAudience']/dynamic-content/text()") />
                <#assign date = document.valueOf("//dynamic-element[@name='IA_ResponsesDeadline']/dynamic-content/text()") />

                <#assign xPathSelector = saxReaderUtil.createXPath("//root//dynamic-element[@name='IA_TargetedAudience']//dynamic-content//option") />
				<#assign impactNodes = xPathSelector.selectNodes(document) />
                <#assign isValid = false>
                <#list impactNodes as nodes>
                    <#if nodes.getText()?contains("Impact")>
                    <#assign segmentConsultation = nodes.getText() />
                    <#else>
                        <#assign segmentConsultation = "Impact-" + nodes.getText()/>
                    </#if>

                    <#if recFormArticleService.isCProposalUserValid(userId,segmentConsultation)>
                        <#assign isValid = true>
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
                            ${articlleCustomTitle}
                            <#else>
                            ${urlTitleToshow}
                            </#if>
                    </td>
                    <td>
                        <#if assetURL != "">
                            <a href="${assetURL}">${id}</a>
                        <#else>
                            <p>else </p>
            	            ${id}
            	        </#if>
                        
                    </td>
                    <td>
                        <#switch type>
                            <#case "Standard">
                                <span class="status IA">${type}</span>
                                <#break>
                            <#case "Preliminary">
                                <span class="status preliminary">${type}</span>
                                <#break>
                            <#case "Detailed">
                                <span class="status final">${type}</span>
                                <#break>
                        </#switch>
                    </td>
                    <td>
                    <#list impactNodes as nodes>
                        ${nodes.getText()}<#sep>, </#sep>
                    </#list>
                        ${target}
                    </td>
                    <td>
                        <#assign IA_CommentsDeadline_Data = getterUtil.getString(date)>

                        <#if validator.isNotNull(IA_CommentsDeadline_Data)>
                        	<#assign IA_CommentsDeadline_DateObj = dateUtil.parseDate("yyyy-mm-dd", IA_CommentsDeadline_Data, locale)>
                        
                        	${dateUtil.getDate(IA_CommentsDeadline_DateObj, "dd/mm/yyyy", locale)}
                        </#if>
                    </td>
                    <td>
                       <a href="${assetURL}" class="open"><img src="${Imagerute}"</a>
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

    #addimpact:hover {
        text-decoration: none;
    }

</style>