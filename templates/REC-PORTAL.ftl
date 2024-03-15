<#assign themeImgsSource = themeDisplay.getPathThemeImages()/>

<div id="dashboard">

<div id="portalRECdev">

<#if header_home?has_content && header_home.getData() != "">
    <h2>${header_home.getData()}</h2>
</#if>

<div id="hyperLinks">
    <ul>
    <#if title_link?has_content && title_link.getData() !="">
    	<#list title_link.getSiblings() as cur_title_link>
    	<li class="list">
    	    <img src="${themeImgsSource}/forms/expand-green.svg">
    		<a onclick="return hyperLinksDashboard('${cur_title_link.getData()}');" href="${cur_title_link.link.getData()}" target="_blank">
                ${cur_title_link.getData()}
            </a>
    	</li>	
    	</#list>
    </#if>
    </ul>
</div>

</div>

<div id="accessRECservices">

<h2>Access to REC Services</h2>

<ul>
<#if title_service?has_content && title_service.getData() !="">
	<#list title_service.getSiblings() as cur_title_service>
	<li class="list">
		<a href="${cur_title_service.link_service.getData()}" target="_blank">
            ${cur_title_service.getData()}
        </a>
	</li>	
	</#list>
</#if>
</ul>

</div>

<!----------------------------------------ADD THEME------------------------------------------------>
<script>
    function hyperLinksDashboard(name) {
        console.log("In hyperLinkDashboardevent for " + name);
        Analytics.track("HyperLinks Dashboard", {name});
    }
</script>
<style>

    <#-- Hiperlinks -->  
    #portalRECdev{
	        background: rgba(112, 173, 163,0.2);
	        border-radius: 4px;
	        height: auto;
	        padding-bottom: 1px;
            margin-bottom: 10px;
    }
    
    #portalRECdev h2{
        padding: 10px;
        font-size: 18px;
        font-weight: normal;
        background: rgb(112, 173, 163,0.3);
    }
    
    .list{
        display: block;
    }
	
    .list a{
        color:rgb(0, 0, 0);
    }
    

    <#-- Links Services -->  
    #accessRECservices{
	        background: rgba(112, 173, 163,0.2);
	        border-radius: 4px;
	        height: auto;
	        padding-bottom: 1px;
            margin-bottom: 10px;
    }
    
    #accessRECservices h2{
        padding: 10px;
        font-size: 18px;
        font-weight: normal;
        background: rgb(112, 173, 163,0.3);
    }
    
     #hyperLinks {
      column-count: 2;
      padding: 10px;
      line-height: 36px;
    } 
     #hyperLinks img{
      width: 18px;
      padding-bottom: 3px;
    } 
    

</style>
<!---------------------------------------------------------------------------------------->