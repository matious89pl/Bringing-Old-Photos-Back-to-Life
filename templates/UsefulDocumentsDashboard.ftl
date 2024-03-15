<#assign themeImgsSource = themeDisplay.getPathThemeImages()/>

<#-- Useful documents -->

<#assign themeImgPath = themeDisplay.getPathThemeImages() />
 <#if TitleDoc?has_content && TitleDoc.getData() != "">
    <div class="grid_item box" id="usefulDocument">
        <h2> Useful Documents</h2>
        <div class="usefulDocuments">
            <div class="docuseful">
                <ul>
                   
                    	<#list TitleDoc.getSiblings() as cur_TitleDoc>
                    	    <li>
                    	    <img src="${themeImgPath}/forms/file-green.svg" class="fileImg">
                                <a onclick="return usefulDocumentDashboard('${cur_TitleDoc.getData()}');" href="${cur_TitleDoc.DocumentUseFul.getData()}" target="_blank">
                                    ${languageUtil.format(locale, "${cur_TitleDoc.getData()}", false)}
                                </a>
                            </li>
                    	</#list>
                   
                </ul>
            </div>
        </div>
               
    </div>
    </#if>
    
<script>
    function usefulDocumentDashboard(name) {
        console.log("In UsefulDocumentDashboard for " + name);
        Analytics.track("Useful Documents Dashboard", {name});
    }
</script>