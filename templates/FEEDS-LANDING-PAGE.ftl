<!-- NEWS -->
<#-- GET JOURNAL ARTICLE  -->
<#assign journalArticleService = serviceLocator.findService("com.liferay.journal.service.JournalArticleLocalService") />

<#assign userId = themeDisplay.getUserId() />
<#assign journalArticleId = .vars['reserved-article-id'].data/>
<#assign companyId= themeDisplay.getCompanyId() />
<#assign groupId= themeDisplay.getLayout().getGroupId() />
<#assign currentEmail = user.getEmailAddress() />

<#assign article = journalArticleService.getArticle(groupId, "${journalArticleId}") />
<#assign articleUrl = article.getUrlTitle() />


<div class="dashboard" id="newsFeedDashboard">
    <div id="image-horizontal">
        <div class="imageSimple">

            <a class="imageLinks" href="/web/guest/-/${article.getUrlTitle()}">
                <#if (ImageFeedsLandingPage.getData())?? && ImageFeedsLandingPage.getData() != "">
                	<img class="image" alt="${ImageFeedsLandingPage.getAttribute("alt")}" data-fileentryid="${ImageFeedsLandingPage.getAttribute("fileEntryId")}" src="${ImageFeedsLandingPage.getData()}" />
                </#if>

                <#assign ImageFeedsLandingPage_DateFeedsLandingPage_Data = getterUtil.getString(ImageFeedsLandingPage.DateFeedsLandingPage.getData())>

                <#if validator.isNotNull(ImageFeedsLandingPage_DateFeedsLandingPage_Data)>
                	<#assign ImageFeedsLandingPage_DateFeedsLandingPage_DateObj = dateUtil.parseDate("yyyy-MM-dd", ImageFeedsLandingPage_DateFeedsLandingPage_Data, locale)>
                    <div class="imageDate dateFootNews">
                	${dateUtil.getDate(ImageFeedsLandingPage_DateFeedsLandingPage_DateObj, "dd/MM/yyyy", locale)}
                	</div>
                </#if>
                <div class="imageFoot textFootNews">
                    ${ImageFeedsLandingPage.TitleFeedsLandingPage.getData()}
                </div>

                <div class="summaryFoot">
                   ${ImageFeedsLandingPage.SummaryFeedsLandingPage.getData()}
                </div>

                <div class="descriptionFoot descriptionFootNews">
                    ${ImageFeedsLandingPage.DescriptionLandingPage.getData()}
                </div>
            </a>

        </div>
    </div>

</div>


<script>
  
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
      
      console.log(url);
      
      var friendlyUrl = url.includes("/web/guest/-/");
      
      if(friendlyUrl) {
         $( document ).ready(function() {
             $('div.autofit-row.mb-4.metadata-author').css('display','none');
            $( "div" ).removeClass( "image imageSimple imageFoot imageDate descriptionFoot asset-full-content.clearfix mb-5 show-asset-title" );
         });
         
         $(".image").removeAttr("style");
         
    }
  
</script>

<style>
 
 #layout-column_column-7 .portlet .portlet-content .portlet-content-container .portlet-body{
     display: inline-flex;
 }
 
 #layout-column_column-7 .h4{
     display: none;
 }
 
 #layout-column_column-7 .mb-4  {
     display: none;
 }
</style>