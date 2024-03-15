<div id="service-management">
<#if MainTitle.getData()?has_content  && MainTitle.getData() != "">
     <h1 id="register-title">
     <#if MainIcon.getData()?has_content && MainIcon.getData() != "none">
     <#assign image = "${themeDisplay.getPathThemeImages()}/forms/${MainIcon.getData()}.svg" />
	  <img src="${image}" />
     </#if>
     ${MainTitle.getData()}</h1>
</#if>		

<#if MainDescription.getData()?has_content && MainDescription.getData() != "">
     <h1 class="explore">${MainDescription.getData()}</h1>
</#if>																																																																				
    <div class="container-fluid">
        <div class="row">
        
        
        <#if Link3.getData()?has_content && Link3.getData() != "">
                   <a class="help-area col-md-3 green-helper" href="${Link3.getData()}">
            </#if>	
            
                <div>
                <#if Title3.getData()?has_content && Title3.getData() != "">
                    <h3 class="knowledge">
                    <#if Icon3.getData()?has_content && Icon3.getData() != "none">
                     <#assign image = "${themeDisplay.getPathThemeImages()}/forms/${Icon3.getData()}.svg" />
                	  <img src="${image}" />
                     </#if>
                    ${Title3.getData()}</h3>
                </#if>	
                <#if Description3.getData()?has_content&& Description3.getData() != "" >
                   <p>${Description3.getData()}</p>
                </#if>	   
                    									  	   
                </div>
            </a>
            
            
            
            <#if Link1.getData()?has_content && Link1.getData() != "">
                   <a class="help-area col-md-3 green-helper" href="${Link1.getData()}">
            </#if>	
            
                <div>
                <#if Title1.getData()?has_content && Title1.getData() != "">
                    <h3 class="knowledge">
                    <#if Icon2.getData()?has_content && Icon2.getData() != "none">
                     <#assign image = "${themeDisplay.getPathThemeImages()}/forms/${Icon2.getData()}.svg" />
                	  <img src="${image}" />
                     </#if>
                    ${Title1.getData()}</h3>
                </#if>	
                <#if Description1.getData()?has_content&& Description1.getData() != "" >
                   <p>${Description1.getData()}</p>
                </#if>	   
                    									  	   
                </div>
            </a>
            
            <#if Link2.getData()?has_content && Link2.getData() != "">
                    <a class="help-area col-md-3 orange-helper raise-ticket" href="${Link2.getData()}">
                </#if>	
           
                <div >
                <#if TextUpLink2.getData()?has_content && TextUpLink2.getData() != "">
                   <p>${TextUpLink2.getData()}</p>
                </#if>	
                 <#if MainTextLink2.getData()?has_content && MainTextLink2.getData() != "">
                  <h3 class="raise">${MainTextLink2.getData()}</h3>
                </#if>  
                    
                </div>
            </a>
        </div>
    </div>
</div>