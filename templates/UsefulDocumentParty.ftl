<#assign themeImgPath = themeDisplay.getPathThemeImages() />

<div id="party_management">
    <#if TitleDoc?has_content && TitleDoc.getData() != "">
        <div class="grid_item box" id="usefulDocument">
            <h2> Useful Documents</h2>
            <div class="usefulDocuments">
                <div class="docuseful">
                    <ul>                    
                        <#list TitleDoc.getSiblings() as cur_TitleDoc>
                            <li>
                                <img src="${themeImgPath}/forms/file-green.svg" class="fileImg">
                                <a href="${cur_TitleDoc.DocumentUseFul.getData()}" target="_blank">
                                    ${languageUtil.format(locale, "${cur_TitleDoc.getData()}", false)}
                                </a>
                            </li>
                        </#list>
                    </ul>
                </div>
            </div>
        </div>
    </#if>
 </div>