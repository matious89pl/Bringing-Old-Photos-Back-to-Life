<#assign themeImgsSource = themeDisplay.getPathThemeImages()/>  
<#if LinkToServiceManagement.getSiblings()?has_content && LinkToServiceManagement.getData() !="">
    	<#list LinkToServiceManagement.getSiblings() as cur_LinkToServiceManagement>
    <a id="helpDesk" href="${cur_LinkToServiceManagement.getData()}" >
    <div>
    <ul>
    
    	    <li class="list">
    		<img src="${themeImgsSource}/forms/question-white.svg"> 
    		Help Desk
    		</li>
    
    </ul>
</div>
</a>
	</#list>
    </#if>