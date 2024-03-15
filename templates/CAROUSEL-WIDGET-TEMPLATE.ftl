<#-- Roles -->

<#assign userService = utilLocator.findUtil("com.liferay.portal.kernel.service.UserLocalService") />
<#assign rolService = utilLocator.findUtil("com.liferay.portal.kernel.service.RoleLocalService") />

<#assign userId = themeDisplay.getUserId() />
<#assign user = userService.getUser(userId)>
<#assign userRoles = user.getRoles()/>

<#-- Journal Article -->
<#assign journalArticleService = serviceLocator.findService("com.liferay.journal.service.JournalArticleLocalService") />
 
<#assign groupId= themeDisplay.getLayout().getGroupId() />
 
<#-- FolderId -->
 <#assign dLFileEntryLocalService = serviceLocator.findService("com.liferay.document.library.kernel.service.DLFileEntryLocalService") />

 <#if entries?has_content>
	<#list entries as curEntry>
	      <#assign counter = curEntry?counter />
		  <#assign journalArticle =  journalArticleService.getLatestArticle(curEntry.classPK) />
		  <#assign document = saxReaderUtil.read(journalArticle.getContent())/>

		 <#if counter == 1>
              <#-- GET URL WEB CONTENT -->
              <#assign urlTitle1 = journalArticle.getUrlTitle() />
							<#assign assetURL = "/web/guest/-/" + urlTitle1 />
    		  
    		   <#-- GET URL IMAGE -->
    		  <#assign image1 = document.valueOf("//dynamic-element[@name='ImageFeedsLandingPage']/dynamic-content/text()") />
    		  
    		     <#-- CREATE JSON OBJECT -->
    		    <#assign image1_map = jsonFactoryUtil.createJSONObject(image1)>
    		    <#-- END -->
    		    
    		     <#-- GET FOLDER ID -->
    		   <#assign fileEntryId = image1_map.getString("fileEntryId") /> 
    		   
    		    <#assign folder1 = dLFileEntryLocalService.getDLFileEntry(fileEntryId?number) />
                <#-- END -->
    		   <#-- END URL IMAGE -->
    		    
    		  <#-- <#assign title1 = document.valueOf("//dynamic-element[@name='TitleFeedsLandingPage']/dynamic-content/text()") /> -->
			  <#assign title1 =  journalArticle.getTitle()/>
    		  
    		  <#assign summary1 = document.valueOf("//dynamic-element[@name='SummaryFeedsLandingPage']/dynamic-content/text()") />
    		  
    		  
    		  <#assign description1 = document.valueOf("//dynamic-element[@name='DescriptionLandingPage']/dynamic-content/text()") />



            <#elseif counter == 2>
                <#-- GET URL WEB CONTENT -->
                <#assign urlTitle2 = journalArticle.getUrlTitle() />
                                <#assign assetURL = "/web/guest/-/" + urlTitle2 />
                 <#-- GET URL IMAGE -->
                <#assign image2 = document.valueOf("//dynamic-element[@name='ImageFeedsLandingPage']/dynamic-content/text()") />
                
                    <#-- CREATE JSON OBJECT -->
    		    <#assign image2_map = jsonFactoryUtil.createJSONObject(image2)>
    		      <#-- END -->
    		      
    		      <#-- GET FOLDER ID -->
    		   
    		   <#assign fileEntryId = image2_map.getString("fileEntryId") />
    		   
    		    <#assign folder2 = dLFileEntryLocalService.getDLFileEntry(fileEntryId?number) />
                 <#-- END -->
    		   <#-- END URL IMAGE -->
    		   
                <#assign title2 = journalArticle.getTitle()/>
                <#-- document.valueOf("//dynamic-element[@name='TitleFeedsLandingPage']/dynamic-content/text()") /> -->
                
                <#assign summary2 = document.valueOf("//dynamic-element[@name='SummaryFeedsLandingPage']/dynamic-content/text()") />
                
                <#assign description2 = document.valueOf("//dynamic-element[@name='DescriptionLandingPage']/dynamic-content/text()") />




             <#elseif counter ==3>
                 <#-- GET URL WEB CONTENT -->
                <#assign urlTitle3 = journalArticle.getUrlTitle() />
                                <#assign assetURL = "/web/guest/-/" + urlTitle3 />
                 <#-- GET URL IMAGE -->
                <#assign image3 = document.valueOf("//dynamic-element[@name='ImageFeedsLandingPage']/dynamic-content/text()") />
                
                    <#-- CREATE JSON OBJECT -->
    		    <#assign image3_map = jsonFactoryUtil.createJSONObject(image3)>
    		    
    		      <#-- GET FOLDER ID -->
    		   
    		   <#assign fileEntryId = image3_map.getString("fileEntryId") />
    		   
    		    <#assign folder3 = dLFileEntryLocalService.getDLFileEntry(fileEntryId?number) />
 
                <#-- END -->
    		   <#-- END URL IMAGE -->
                
                <#assign title3 = journalArticle.getTitle() />
                <#--document.valueOf("//dynamic-element[@name='TitleFeedsLandingPage']/dynamic-content/text()") /> -->
                
                <#assign summary3 = document.valueOf("//dynamic-element[@name='SummaryFeedsLandingPage']/dynamic-content/text()") />
                
                <#assign description3 = document.valueOf("//dynamic-element[@name='DescriptionLandingPage']/dynamic-content/text()") />
        </#if>
	
	</#list>
</#if>


<#attempt>
 
<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
    <div class="carousel-inner">
        <div class="carousel-item active">
            <div class="inlineImageText col-md-12">
                <div class="carouselLeft col-md-6">
                    <div class="carousel-caption d-md-block carousel-caption text-left">
                        <h5 class="titleCarrousel">${title1}</h5>
                        <p class="descriptionCarrousel">${summary1}</p>
                        <a href="/web/guest/-/${urlTitle1}"><button type="button" class="float-right lfr-ddm-form-pagination-next btn btn-primary">Read More</button> </a>
                    </div>
                </div>
                <div class="carouselRight col-md-6">
                    <img class="d-block w-100 imageCarousel" alt="" data-fileentryid="" src="/documents/${groupId}/${folder1.getFolderId()}/${image1_map.getString("name")}/${image1_map.getString("uuid")}" />
                </div>
            </div>
        </div>

        <div class="carousel-item">
            <div class="inlineImageText col-md-12">
                <div class="carouselLeft col-md-6">
                    <div class="carousel-caption d-md-block carousel-caption text-left">
                        <h5 class="titleCarrousel">${title2}</h5>
                        <p class="descriptionCarrousel">${summary2}</p>
                        <a href="/web/guest/-/${urlTitle2}"><button type="button" class="float-right lfr-ddm-form-pagination-next btn btn-primary">Read More</button> </a>
                    </div>
                </div>
                <div class="carouselRight col-md-6">
                    <img class="d-block w-100 imageCarousel" alt="" data-fileentryid="" src="/documents/${groupId}/${folder2.getFolderId()}/${image2_map.getString("name")}/${image2_map.getString("uuid")}" />
                </div>
            </div>
        </div>

        <div class="carousel-item">
            <div class="inlineImageText col-md-12">
                <div class="carouselLeft col-md-6">
                    <div class="carousel-caption d-md-block carousel-caption text-left">
                        <h5 class="titleCarrousel">${title3}</h5>
                        <p class="descriptionCarrousel">${summary3}</p>
                        <a href="/web/guest/-/${urlTitle3}"><button type="button" class="float-right lfr-ddm-form-pagination-next btn btn-primary">Read More</button> </a>
                    </div>
                </div>
                <div class="carouselRight col-md-6">
                    <img class="d-block w-100 imageCarousel" alt="" data-fileentryid="" src="/documents/${groupId}/${folder3.getFolderId()}/${image3_map.getString("name")}/${image3_map.getString("uuid")}" />
                </div>
            </div>
        </div>

        <ol class="carousel-indicators">
            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
        </ol>
    </div>

    <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    </a>
    <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
    </a>
</div>
<#recover>
 <#list userRoles as rol>
    <#if rol.getName() == "Administrator" >
        <div class="alert alert-info">
    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
    Ops! ... There is no content for the Carousel (NEWS). Please set a <strong>title, image, summary and description</strong> for each Web Content and review that the Guest has view <strong>permissions</strong>.
</div>
    </#if>
</#list>
</#attempt>


<script>
 $('.carousel').carousel({
  interval: false
})
</script>
