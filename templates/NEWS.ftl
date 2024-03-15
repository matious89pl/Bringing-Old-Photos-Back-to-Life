<!-- NEWS -->
<#-- GET JOURNAL ARTICLE  -->
<#assign journalArticleService = serviceLocator.findService("com.liferay.journal.service.JournalArticleLocalService") />

<#assign userId = themeDisplay.getUserId() />
<#assign journalArticleId = .vars['reserved-article-id'].data/>
<#assign companyId= themeDisplay.getCompanyId() />
<#assign groupId= themeDisplay.getLayout().getGroupId() />
<#assign currentEmail = user.getEmailAddress() />

<#assign article = journalArticleService.getArticle(groupId, "${journalArticleId}") />
<#assign articleUrl = article.getUrlTitle()  />
<#assign articleTitle = article.getUrlTitle() />
<#assign dateAvailable = 0 /> <#-- Used to expand news summary when date is not available.  -->

<div class="dashboard" id="newsFeedDashboard">
    <div id="image-horizontal">
        <div class="imageSimple">
      
            <a class="imageLinks" id="disableLink" href="/web/guest/-/${articleTitle}">
                <#if (ImageFeedsLandingPage.getData())?? && ImageFeedsLandingPage.getData() != "">
                	<img id="image" class="image" alt="${ImageFeedsLandingPage.getAttribute("alt")}" data-fileentryid="${ImageFeedsLandingPage.getAttribute("fileEntryId")}" src="${ImageFeedsLandingPage.getData()}" />
                </#if>
            
            <div id= "textUnderImageDetailView" >
                <#assign ImageFeedsLandingPage_DateFeedsLandingPage_Data = getterUtil.getString(ImageFeedsLandingPage.DateFeedsLandingPage.getData())>

                <#if validator.isNotNull(ImageFeedsLandingPage_DateFeedsLandingPage_Data)>
                	<#assign ImageFeedsLandingPage_DateFeedsLandingPage_DateObj = dateUtil.parseDate("yyyy-MM-dd", ImageFeedsLandingPage_DateFeedsLandingPage_Data, locale)>
                    <div  class="imageDate dateFootNews">
                	<span id = "hide"> ${dateUtil.getDate(ImageFeedsLandingPage_DateFeedsLandingPage_DateObj, "dd/MM/yyyy", locale)} </span>
                	</div>
                	
                	<#else>
                		<#assign dateAvailable = 1 />
                </#if>
				<div  class="imageFoot textFootNews ">
                    <#attempt>
                        <#if (ImageFeedsLandingPage.TextFeedsLandingPage.getData())?? && ImageFeedsLandingPage.TextFeedsLandingPage.getData() = "">
                          <span style="margin: 18px;"></span>
                        
                         <#else>
                          
                             <span id= "titleTextSizeDetailView">${ImageFeedsLandingPage.TextFeedsLandingPage.getData()}</span>
                              
                        </#if>
                    <#recover>
                    </#attempt>
                 </div>
				 
				 <#if dateAvailable = 1 >
    	            
    	                 <div id= "summary"  class="summaryFootWithoutDate">
    	                    ${ImageFeedsLandingPage.SummaryFeedsLandingPage.getData()}
                         </div>
                        
    	         <#else>
						<div id= "summary" class="summaryFoot">
                             ${ImageFeedsLandingPage.SummaryFeedsLandingPage.getData()}
						</div>
                 </#if>
				 
                    <div  class=" descriptionFoot descriptionFootNews">
                        ${ImageFeedsLandingPage.DescriptionLandingPage.getData()}
                    </div>
                </div>
            </a>
        </div>
    </div>
</div>


<script>

 // Short the descriptions
             $(".summaryFoot, .summaryFootWithoutDate").each(function(){
                
                var cad = $(this).text();
                if(cad.length > 100){
                     cad = cad.substring(0,100);
                    cad = cad.replace("...", "");
                    cad = cad + "...";
                }
               
                $(this).text(cad);
            });


 $( document ).ready(function() { 
 
      var images = document.getElementsByClassName("image");
      var image = $(".asset-full-content.clearfix.mb-5.show-asset-title");
      var widths = screen.width/4 + "px";
      var width = "100%";
      var height = 176 + "px";
      var heights = 176 + "px";

      for (i = 0; i < image.length; i++) {
          image[i].style.width = width;
          image[i].style.height = height;
          images[i].style.width = widths;
          images[i].style.height = heights;
      }
    
    
       /*
      Get the URL to show News Detail
      */
   
                
              var url = window.location.href;
              
              var friendlyUrl = url.includes("/web/guest/-/");
               
              if(friendlyUrl) {
              setTimeout(function(){
         

              //document.getElementById("disableLink").classList.add("inactiveLink");
                    $("div.autofit-row.mb-4.metadata-author").css("display","none");
                    $("#newsFeedDashboard div").removeClass( "image imageSimple imageFoot imageDate descriptionFoot asset-full-content.clearfix mb-5 show-asset-title" );
        
                    $(".image").removeAttr("style");
                    
                    //Classes added to style the detailed view
                    $("#image").addClass( "imageInDetailView" );
                    $("#textUnderImageDetailView").addClass( "textUnderImageDEtailViewCSS" );
                    $("#titleTextSizeDetailView").addClass( "titleTextSizeDetailViewCSS" );
                    $("#hide").addClass( "hideText" );
                    $("#disableLink").addClass( "deacticateLink" );
                    $("a.imageLinks").attr("href", "javascript:void(0)"); //remove the click event from <a> when clicked
                   
                    
                },5);
               
                }
                
        
  
  });
</script>



<style>

.descriptionFootNews{

   text-align: justify !important;

}
.lfr-icon-menu .visible-interaction {
    display: none !important ;
}

.hideText{
    
    display: none; 
}
.imageInDetailView{
    
   width: 95%;
   height: 80%;
   padding-top: 20px;
   
   
}
.titleTextSizeDetailViewCSS{
    
    font-size: 30px;
    font-weight: bold;

}

.deacticateLink {
   pointer-events: none;
   cursor: default;
}

.textUnderImageDEtailViewCSS{
    width: 94%;
    padding-left: 10px; 

}
.imageSimple{
      margin: 10px;
      border-radius: 4px;
      box-shadow: 0px 1px 4px 0px rgba(0, 0, 0, 0.3);
      height: 300px;
      
  }
    .summaryFoot{
      padding: 0px 16px 16px 16px;
      color: rgb(0, 0, 0);
      font-size: 12px;
      font-family: Roboto-Regular !important;
      font-weight: normal;
      letter-spacing: 0.25px;
      line-height: 18px;
      overflow: hidden;
      text-overflow: ellipsis;
      display: -webkit-box;
      max-height: 50px !important;
      -webkit-line-clamp: 6  ;
      -webkit-box-orient: vertical;
      
      
  }
   .summaryFootWithoutDate{
      padding: 0px 16px 16px 16px;
      color: rgb(0, 0, 0);
      font-size: 12px;
      font-family: Roboto-Regular !important;
      font-weight: normal;
      letter-spacing: 0.25px;
      line-height: 18px;
      overflow: hidden;
      text-overflow: ellipsis;
      display: -webkit-box;
      max-height: 50px !important;
      -webkit-line-clamp: 7 ;
      -webkit-box-orient: vertical;
      
      
  }
  #rec-theme #image-horizontal .summaryFoot {
    min-height: 18px !important;
}

 
 #layout-column_column-7 .portlet .portlet-content .portlet-content-container .portlet-body{
     display: inline-flex;
 }
 
  @media screen and (max-width: 600px) {
          #layout-column_column-7 .portlet .portlet-content .portlet-content-container .portlet-body{
            display: block;
          }
          
          #newsFeedDashboard .image {
              width: 100% !important;
          }
          
      
  }
 
 #layout-column_column-7 .h4{
     display: none;
 }
 
 #layout-column_column-7 .mb-4  {
     display: none;
 }
 
</style>
