<#-- Change Register Template -->
<#assign cpImpactService = serviceLocator.findService("com.everis.rec.cpimpact.service.CPImpactLocalService") />

<#assign journalArticleService = serviceLocator.findService("com.liferay.journal.service.JournalArticleLocalService") />
<#assign recFormArticleService = serviceLocator.findService("com.everis.cproposal.service.recFormArticleLocalService") />
<#assign impactService = serviceLocator.findService("com.everis.rec.impacts.service.ImpactsLocalService") />

<#assign marketParticipantList = impactService.findImpactsByCategory("REC-PARTY-AND-MARKET-PARTICIPANT-IMPACTS") />

<#assign Imagerute = themeDisplay.getPathThemeImages()+"/forms/chevrom.svg" />
<#assign images_folder = themeDisplay.getPathThemeImages() />
<#assign filterList = "" />
<#assign filterByCPId = false />
<#assign hasCustomFilter = false />

<#assign filterCPId = "" />
<#assign filterStatus = "" />
<#assign filterStartDate = "" />
<#assign filterEndDate = "" />
<#assign filterImpactedP = "" />
<#assign filterResponsibleC = "" />
<#assign filterChangeC = "" />
<#assign filterChangeP = "" />
 
<#if entries?has_content>
    <#assign entriesFilter = entries />
</#if>

<#if request.getParameter("filterCPId")??>
	<#assign filterByCPId = true />
	<#assign filterCPId = request.getParameter("filterCPId") />
    <#assign filterList = filterList + ",filterCPId:" + request.getParameter("filterCPId") />
</#if>

<#if request.getParameter("filterStatus")??>
	<#assign hasCustomFilter = true />
	<#assign filterStatus = request.getParameter("filterStatus") />
	<#assign filterList = filterList + ",filterStatus:" + request.getParameter("filterStatus") />
</#if>

<#if request.getParameter("filterStartDate")??>
	<#assign hasCustomFilter = true />
	<#assign filterStartDate = request.getParameter("filterStartDate") + " 00:00:00" />
    <#assign filterList = filterList + ",filterStartDate:" + request.getParameter("filterStartDate") />
</#if>

<#if request.getParameter("filterEndDate")??>
	<#assign hasCustomFilter = true />
    <#assign filterEndDate = request.getParameter("filterEndDate")  + " 23:59:59" />
    <#assign filterList = filterList + ",filterEndDate:" + request.getParameter("filterEndDate") />
</#if>

<#if request.getParameter("filterImpactedP")??>
	<#assign hasCustomFilter = true />
	<#assign filterImpactedP = request.getParameter("filterImpactedP") />
	<#assign filterList = filterList + ",filterImpactedP:" + request.getParameter("filterImpactedP") />
</#if>

<#if request.getParameter("filterResponsibleC")??>
	<#assign hasCustomFilter = true />
	<#assign filterResponsibleC = request.getParameter("filterResponsibleC") />
	<#assign filterList = filterList + ",filterResponsibleC:" + request.getParameter("filterResponsibleC") />
</#if>

<#if request.getParameter("filterChangeC")??>
	<#assign hasCustomFilter = true />
	<#assign filterChangeC = request.getParameter("filterChangeC") />
	<#assign filterList = filterList + ",filterChangeC:" + request.getParameter("filterChangeC") />
</#if>

<#if request.getParameter("filterChangeP")??>
	<#assign hasCustomFilter = true />
	<#assign filterChangeP = request.getParameter("filterChangeP") />
	<#assign filterList = filterList + ",filterChangeP:" + request.getParameter("filterChangeP") />
</#if>

<#assign filterList = filterList?remove_beginning(",") />

<style>
    /* filters  style */
    .filters{
        color: rgb(0, 0, 0);
        font-family: Roboto-Regular;
        font-size: 16px;
        font-weight: normal;
        margin-bottom: 30px;
        letter-spacing: 0.5px;
        line-height: 30px;
        margin-top: -15px; /*Update padding-top: 2%; in #table-impact, then delete it style*/ 
    }

    .filterOpt{
        float: right;
        padding-right: 3%;  
    }

    img#filterImg{
        width: 20px;
        height: 18px;
    }
    
    .filterList{
        padding-left: 3%;
        font-weight: bold;
    }

    .filterList a{
        color:black;
    }

    .filterList a:hover {
        color: black;
        text-decoration: underline;
    }

    .filterList .close{
        color:white;
        width: 15px;
        height: 15px;
		float: none;
    }
	
	.filterList .close:hover{
        color:white;
    }

    span.status.filterAdded {
        margin-right: 2px;
    }

    .filterAdded{
        background:black;
        color:white;
        border-radius: 3px;
    }

    /* pop up filter style */
    .popup1 {
        position: fixed;
        top: 0;
        bottom: 0;
        left: 0;
        right: 0;
        background: rgba(0, 0, 0, 0.7);
        transition: opacity 500ms;
        overflow: auto;
        z-index: 999;
    }
    .popupFilter div {
        padding: 3px;
    }

    .popupFilter {
        background: rgb(255, 255, 255);
        border-radius: 4px;
        box-shadow: 0px 1px 4px 0px rgba(0, 0, 0, 0.3);
        height: auto;
        width: 750px;
        margin: 70px auto;
        position: relative;
        padding: 1.5%;
    }

    .popupFilter .close {
        position: absolute;
        top: 0px;
        right: 0px;
        border-radius: 0px;
        height: 53.13px;
        width: 53.13px;
    }

    .popupFilter #dateFrom, .popupFilter #dateTo {
        text-align: center;
    }

    .popupFilter h1 {
        color: rgb(0, 0, 0);
        font-family: Roboto-Regular;
        font-size: 20px;
        font-weight: normal;
        height: 30px;
        letter-spacing: 0.75px;
        line-height: 30px;
    }

    .popupFilter h3 {
        color: #9b9b9b;
        font-family: Roboto-Regular;
        font-size: 12px;
        font-weight: normal;
        height: 18px;
        letter-spacing: 0.25px;
        line-height: 18px;
        padding-top: 5px;
    }

    .rigthSide {
        width: 45%;
        float: right;
    }


    .leftSide {
        width: 45%;
        float: left;
    }

    .full-row {
        width: 100%;
    }

    .full-row .btnDropDown {
        width: 100%;
    }

    .full-row .filterImpacted {
        width: 99%;
        max-height: 205px !important;
    }

    .filterCategory {
    max-height: 100px !important;
    }

    .filterTitle .cp-list {
        max-height: 250px;
        overflow: auto;
    }

    .applyFilters{
        background: rgb(112, 173, 163);
        border-radius: 0px;
        height: 40px;
        width: 164.86px;
        color: white;
        margin-top: 2%;
        font-weight: normal;
    }

    .clearFilters{
        color: rgb(0, 0, 0);
        font-family: Roboto-Regular;
        font-size: 16px;
        font-weight: normal;
        height: 40px;
        letter-spacing: 0.5px;
        line-height: 24px;
        text-decoration: underline;
        width: 115px;
        margin-top: 2%;
    }

    .filterOption{
        border-bottom: solid;
        border-color: #d6d6d6;
    }

    .shareRow {
        width: 100%;
        height: 100px;
    }

    .filtersDropdown{
        background-color: #fff;
        border-color: rgba(0, 0, 0, 0.125);
        border-style: solid;
        border-width: 0;
        border-radius: 0.25rem;
        box-shadow: 0 1px 3px -1px rgb(0 0 0 / 60%);
        display: block;
        margin-bottom: 0.5rem;
        min-width: 0;
        position: relative;
        word-wrap: break-word;
    }

    .filtersDropdown img{
        float: right;
        width: 20px;
        height: 18px;
        margin-left: 100px;
    }

    .filtersDropdown h2{
        float: left;
        font-size:18px;
        font-weight: normal;
    }

    div#referenceTitleHeading img {
    margin-left: 480px;
    }

    div#implementationDate img {
        margin-left: 190px; 
    }


    .filterDropdownHeader {
        border-radius: 0.25rem 0.25rem 0 0;
        border-bottom: 0 solid rgba(0, 0, 0, 0.125);
        margin-bottom: 0;
    }

    .filtersDropdown a{
        color:black;
        font-size: 12px;
    }

    .btnDropDown{
        color:black;
    }

    div#implementationDate button {
        color: #9b9b9b;
        width: -webkit-fill-available;
    }

    div#content, div#content1,  div#content2, div#content3, div#content4{
        position: absolute;
        background: white !important;
        border: 1px solid transparent;
        border-radius: 0.25rem;
        z-index: 3;    
        min-width: 288px;
        overflow: auto;
        max-height: 250px;
        box-shadow: 1px 5px 5px black;
    }

    .grid-container {
        display: grid;
        grid-template-columns: auto auto auto;
        padding: 10px;
    }

    .grid-item {
        font-size: 20px;
    }

    input[type="date"] {
        color: #9b9b9b;
        width: 217px;;
    }


    /* Customize the label (the container) */
    .containerStatus {
        position: relative;
        padding-left: 28px;
        cursor: pointer;
        font-weight: lighter;
    }

    /* Hide the browser's default checkbox and customized one */
    .containerStatus input {
        position: absolute;
        opacity: 0;
        cursor: pointer;
        height: 0;
        width: 0;
    }

    .checkBoxCustom{
        position: absolute;
        top: 0;
        left: 0;
        height: 23px;
        width: 23px;
        background-color: white;
        margin-right: 10px;
        border-radius: 2px;
        box-shadow: 0px 0px 4px 0px rgb(0 0 0 / 30%);
    }
 

    /* When the checkbox is checked, add a blue background */
    .containerStatus input:checked ~ .checkBoxCustom {
        background-image: url(https://webserver-recportal-dev.lfr.cloud//o/rec-theme/images/forms/check-gray.svg);
        background-size: contain;
    }

    /* Create the checkmark/indicator (hidden when not checked) */
    .checkBoxCustom:after {
        content: "";
        position: absolute;
        display: none;
    }


    .filterDropdownHeader.h2{
        font-size: 15px;
        float: left;
        text-align: left;
    }

    div#referenceTitle li, div#impactedParties li, div#responsibleComittes li, div#changeCategory li, div#changePath li {
        display: -webkit-box;
    }

    @media screen and (max-width: 700px){
        .box{
            width: 70%;
        }
        .popupFilter{
            width: 70%;
        }
    }

    /***************************************/

</style>

<style>

    #filtersButton{
        visibility:hidden;
    }

    #previousPage {
        visibility: hidden;
        background-color: transparent;
        border: 1px solid transparent;
        border-radius: 15px;
        display: inline-block;
        padding: 5px 14px;
        float:right;
        font-family: "Roboto-regular" !important;
    }

    #nextPage{
        background-color: transparent;
        border: 1px solid transparent;
        border-radius: 15px;
        display: inline-block;
        padding: 5px 14px;
        float:right;
        font-family: "Roboto-regular" !important;
    }

    #nextPage:hover{
        color:#0b5fff;
    }

    #previousPage:hover{
         color:#0b5fff;
    }

    div#paginationInfo {
        display: -webkit-box;
        margin-top: 20px;
    }

    #paginationInfo h3{
        float: left;
        line-height: 46px;
        margin-left: 10px;
        font-size: 17px;
        font-weight: normal;
    }

    input.btn.removeFilters {
        font-style: italic;
        font-weight: normal;
        margin-top: -3px;
        width: fit-content;
        font-size: 15px;
    }

    .statusOptions .status {
        border-radius: 3px;
        padding: 3px;
    }
</style>

<script>
    function openFilterPopUp() {
        $("#change-register-pop-up1").css("visibility","visible");
        $("#search-bar-hidden").css("display","none");
    } 

    function closePopUp(){
        $("#change-register-pop-up1").css("visibility","hidden");
        $("#search-bar-hidden").css("display","block");
    }
  
    function removeFilter(filter, value){
        var currentURL = new URL(window.location.href);
        if(filter === "filterStatus" || filter === "filterCPId" || filter === "filterImpactedP" || filter === "filterResponsibleC" || filter === "filterChangeC" || filter === "filterChangeP" ){
            var filters = currentURL.searchParams.get(filter);
            if(filters.includes(";spt;")){
                if(filters.indexOf(value) == 0){
                    filters = filters.replace(value + ";spt;", "");
                } else {
                    filters = filters.replace(";spt;" + value, "");
                }
                currentURL.searchParams.set(filter, filters);
                currentURL.searchParams.delete('page');
                currentURL.searchParams.append('page', 0);
            } else {
                currentURL.searchParams.delete(filter);
                currentURL.searchParams.delete('page');
            }
        } else {
            console.log(filter);
            if (filter.includes("filterStartDate") || filter.includes("filterEndDate")) {
                currentURL.searchParams.delete("filterStartDate");
                currentURL.searchParams.delete("filterEndDate");
                currentURL.searchParams.delete('page');
                currentURL.searchParams.append('page', 0);
            }
            else {
                currentURL.searchParams.delete(filter);
                currentURL.searchParams.delete('page');
            }
        }    
	    window.location.href = currentURL;
    }  
</script>

<script>
    $(function(){
        var requiredCheckboxes = $('.browsers :checkbox[required]');
        requiredCheckboxes.change(function(){
            if(requiredCheckboxes.is(':checked')) {
                requiredCheckboxes.removeAttr('required');
            } else {
                requiredCheckboxes.attr('required', 'required');
            }
        });
    });
	
	function applyFilters() {
    	var currentURL = new URL(location.protocol + '//' + location.host + location.pathname);
		var emptyChecks = true;

		//START filterTitle
		var cpRPK = "";
		$(".filterTitle").find("input[type=checkbox]").each(function(){
			if ($(this).prop('checked')==true){ 
				cpRPK = cpRPK + ";spt;" + $(this).attr("data-resourcepk");
			}
		});
		
		if(cpRPK != ""){
			cpRPK = cpRPK.substring(5);
			currentURL.searchParams.append('filterCPId', cpRPK);
			emptyChecks = false;
		}
		//END filterTitle
		
		//START filterStatus
		var status = "";
		$(".filterStatus").find("input[type=checkbox]").each(function(){
			if ($(this).prop('checked')==true){ 
				status = status + ";spt;" + $(this).attr("data-status");
			}
		});
		
		if(status != ""){
			status = status.substring(5);
			currentURL.searchParams.append('filterStatus', status);
			emptyChecks = false;
		}
		//END filterStatus
		
		//START filterImplementation
		var startDate = $(".filterImplementation .leftSide #dateFrom input[type=date]").val();
		console.log("startDate " + startDate);
		var endDate = $(".filterImplementation .rigthSide #dateTo input[type=date]").val();
		console.log("endDate " + endDate);
		
		if (startDate && endDate) {
			currentURL.searchParams.append('filterStartDate', startDate);
			currentURL.searchParams.append('filterEndDate', endDate);
			emptyChecks = false;
		}
		//END filterImplementation
		
		//START filterImpacted
		var impactIds = "";
		$(".filterImpacted").find("input[type=checkbox]").each(function(){
			if ($(this).prop('checked')==true){ 	
				impactIds = impactIds + ";spt;" + $(this).attr("data-impacted");
			}
		});
		
		if(impactIds != ""){
			impactIds = impactIds.substring(5);
			currentURL.searchParams.append('filterImpactedP', impactIds);
			emptyChecks = false;
		}
		//END filterImpacted
		
        var categoryIds = "";
		$(".filterCategory").find("input[type=checkbox]").each(function(){
			if ($(this).prop('checked')==true){ 	
				categoryIds = categoryIds + ";spt;" + $(this).attr("data-category");
			}
		});
		
		if(categoryIds != ""){
			categoryIds = categoryIds.substring(5);
			currentURL.searchParams.append('filterChangeC', categoryIds);
			emptyChecks = false;
		}
		//END filterCategory

		//START changePath
        var changePIds = "";
        console.log("changePIds");
		$(".filterChangeP").find("input[type=checkbox]").each(function(){
			if ($(this).prop('checked')==true){ 	
				changePIds = changePIds + ";spt;" + $(this).attr("data-change");
			}
		});
		if(changePIds != ""){
			changePIds = changePIds.substring(5);
			currentURL.searchParams.append('filterChangeP', changePIds);
			emptyChecks = false;
		}
		//END changePath

        //START data-resourcepk (CP)
        var dataresourcepk = "";
        console.log("dataresourcepk");
		$("filterCPId").find("input[type=checkbox]").each(function(){
			if ($(this).prop('checked')==true){ 	
				dataresourcepk = dataresourcepk + ";spt;" + $(this).attr("data-resourcepk");
			}
		});
		if(dataresourcepk != ""){
			dataresourcepk = dataresourcepk.substring(5);
			currentURL.searchParams.append('filterCPId', dataresourcepk);
			emptyChecks = false;
		}
		//END data-resourcepk (CP)        
		
		//START filterChange
		$(".filterChange").find("input[type=checkbox]").each(function(){
			if ($(this).prop('checked')==true){ 
				var change = $(this).attr("data-change");
				console.log("change: " + change);
				currentURL.searchParams.append('filterChangeP', change);
				emptyChecks = false;
			}
		});
		//END filterChange
		
		console.log("currentURL " + currentURL);
		if(emptyChecks){
			currentURL = window.location.pathname;
		}
        currentURL.searchParams.append('page', 0);
		window.location.replace(currentURL);
	}

    function removeFilters(){
        var currentURL = new URL(window.location.href);

        if(currentURL.searchParams.get('filterCPId') != null){
            currentURL.searchParams.delete('filterCPId');
        }  
        if(currentURL.searchParams.get('filterImpactedP') != null){
            currentURL.searchParams.delete('filterImpactedP');
        } 
        if(currentURL.searchParams.get('filterStatus') != null){
            currentURL.searchParams.delete('filterStatus');
        }  
        if(currentURL.searchParams.get('filterResponsibleC') != null){
            currentURL.searchParams.delete('filterResponsibleC');
        } 
        if(currentURL.searchParams.get('filterChangeC') != null){
            currentURL.searchParams.delete('filterChangeC');
        } 
        if(currentURL.searchParams.get('filterChangeP') != null){
            currentURL.searchParams.delete('filterChangeP');
        } 
        if(currentURL.searchParams.get('page') != null){
            currentURL.searchParams.delete('page');
        }   
        if(currentURL.searchParams.get('filterStartDate') != null){
            currentURL.searchParams.delete('filterStartDate');
        }  
        if(currentURL.searchParams.get('filterEndDate') != null){
            currentURL.searchParams.delete('filterEndDate');
        }  
        
        window.location.replace(currentURL);
    }
	
	function clearFilters() {
		$('input[type=checkbox]').prop('checked',false);
	}
</script>

<script src="https://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"></script>
<script>
    $(document).ready(function () {
        $('[data-toggle="tooltip"]').tooltip();
        
        $('.filterTitle .search-bar-simple .search-bar-keywords-input').on('keyup', function(){
            var searchTerm = $(this).val().toLowerCase();
            $('.filterTitle .cp-list li').each(function(){
                if ($(this).filter('[data-search-term *= "' + searchTerm + '"]').length > 0 || searchTerm.length < 1) {
                    $(this).show();
                } else {
                    $(this).hide();
                }
            });
        });        
    });    
</script>

<div id="change-register">
    <h1 id="register-title">Change Register</h1>
    <div id="table-impact">
        <div class="filters">
            <div class="filterList">
                <#if filterList?has_content>
                    <style> 
                        .clearfix.lfr-pagination{visibility:hidden;}
                    </style>

                    Filter Options: 
                    <#list filterList?split(",") as filter>
                        <#assign statusText = filter?split(":")[1] />
                        <#if statusText?contains(";spt;")>
                            <#list statusText?split(";spt;") as statusItem>
                                <#if filter?contains("filterImpactedP") >
                                    <#assign filterImpactElement = impactService.getImpacts(statusItem?number) />
                                    <#assign filterImpactName = filterImpactElement.getImpactName() />
                                    <span class="status filterAdded"> <a class="close" onclick="removeFilter('${filter?split(":")[0]}', '${statusItem}')">&times;</a> ${filterImpactName} </span>
                                <#elseif filter?contains("filterCPId")>
                                    <#assign filterCPElement = journalArticleService.fetchLatestArticle(statusItem?number) />
                                    <#assign filterCPName = filterCPElement.getTitle() />
                                    <span class="status filterAdded"> <a class="close" onclick="removeFilter('${filter?split(":")[0]}', '${statusItem}')">&times;</a> ${filterCPName} </span>
                                <#else>
                                    <span class="status filterAdded"> <a class="close" onclick="removeFilter('${filter?split(":")[0]}', '${statusItem}')">&times;</a> ${statusItem} </span>
                                </#if>
                            </#list>
                        <#else>
                            <#if filter?contains("filterImpactedP") >
                                <#assign filterImpactElement = impactService.getImpacts(filter?split(":")[1]?number) />
                                <#assign filterImpactName = filterImpactElement.getImpactName() />
                                <span class="status filterAdded"> <a class="close" onclick="removeFilter('${filter?split(":")[0]}', '${filter?split(":")[1]}')">&times;</a> ${filterImpactName} </span>
                            <#elseif filter?contains("filterCPId")>
                                <#assign filterCPElement = journalArticleService.fetchLatestArticle(filter?split(":")[1]?number) />
                                <#assign filterCPName = filterCPElement.getTitle() />
                                <span class="status filterAdded"> <a class="close" onclick="removeFilter('${filter?split(":")[0]}', '${filter?split(":")[1]}')">&times;</a> ${filterCPName} </span>
                            <#elseif filter?contains("filterStartDate") || filter?contains("filterEndDate")>
                                <span class="status filterAdded"> <a class="close" onclick="removeFilter('${filter?split(":")[0]}', '${filter?split(":")[1]}')">&times;</a> ${filter?split(":")[1]} </span>
                            <#else>
                                <span class="status filterAdded"> <a class="close" onclick="removeFilter('${filter?split(":")[0]}', '${filter?split(":")[1]}')">&times;</a> ${filter?split(":")[1]} </span>
                            </#if>
                        </#if>
                    </#list>
                    <input class="btn removeFilters clearFilters" type="submit" value='&times; Remove filters' onclick="removeFilters()">  
                </#if>
    
                <div class="filterOpt" >
                    <a onclick="openFilterPopUp()">
                        <img id="filterImg" src="${images_folder}/forms/filter.svg"/> 
                        Filter
                    </a>
                </div>
            </div>
        </div>

        <table id="instanceReportTable" class="margin-center">
            <thead>
                <tr>
                    <th class="left-align-text"></th>
                    <th class="left-align-text">Title, Reference & Status</th>
                    <th class="left-align-text">Implementation Date</th>
                    <th class="left-align-text">Impacted Parties</th>
                    <th class="left-align-text">Change Category</th>
                    <th class="left-align-text">Change Path</th>
                    <th class="left-align-text">Responsible Committee</th>
                    <th class="left-align-text"></th>
                </tr>
            </thead>

            <tbody>
		        <#if filterByCPId>
                    <#if request.getParameter("page")??>
                        <#assign parameterPage = request.getParameter("page") />
                        <#assign numpage = parameterPage?number />
                    </#if>

                    <#assign filteredJAbyId = recFormArticleService.getChangeProposalFilteredByIds(filterCPId) />

                    <#if request.getParameter("page")??>
                        <#assign pagination = recFormArticleService.getChangeProposalFilteredByIdsByPage(filterCPId, numpage) /> 
                    </#if>

                    <#if filteredJAbyId?has_content>
                        <#if (filteredJAbyId?size > 10)>
                            <style> #filtersButton{visibility:visible !important;}</style>
                            <#if request.getParameter("page")??>    
                                <#assign finalListbyId = pagination />
                            </#if>
                        <#else>
                            <#assign finalListbyId = filteredJAbyId />
                        </#if>
                        <input id="listSizePagination" type="hidden" value="${filteredJAbyId?size}"/>

                        <#list finalListbyId as journalArticle>
                            <#assign resourcePrimKey = journalArticle.getResourcePrimKey() />
                            <#assign formId = recFormArticleService.getFormIdByArticleId(resourcePrimKey)/>
                            <#assign impactedParties = "" />

                            <#if recFormArticleService.getrecFormArticle(formId)??>
                                <#assign article = recFormArticleService.getrecFormArticle(formId) />
                                <#assign impactList = article.getArticleImpacts() />
                                <#if impactList?contains(",")>
                                    <#list impactList?split(",") as eachImpactId>	
                                        <#assign impactElement = impactService.getImpacts(eachImpactId?number) />
                                        <#assign impactElementCategory = impactService.getImpacts(eachImpactId?number).getImpactCategory() />
                                        <#if impactElementCategory == "REC-PARTY-AND-MARKET-PARTICIPANT-IMPACTS" >
                                            <#assign impactName = impactElement.getImpactName() />
                                            <#assign impactedParties += ", "+impactName />
                                        </#if>
                                    </#list>
                                    <#if (impactedParties?length > 2)>
                                        <#assign impactedParties = impactedParties?substring(2) />
                                    </#if>
                                <#elseif impactList?has_content >
                                    <#assign impactElement = impactService.getImpacts(eachImpactId?number) />
                                        <#assign impactElement = impactService.getImpacts(eachImpactId?number) />
                                        <#assign impactElementCategory = impactService.getImpacts(eachImpactId?number).getImpactCategory() />
                                        <#if impactElementCategory == "REC-PARTY-AND-MARKET-PARTICIPANT-IMPACTS" >
                                            <#assign impactElement = impactService.getImpacts(impactList?number) />
                                            <#assign impactedParties = impactElement.getImpactName() />
                                        </#if>
                                </#if>
                            </#if>

                            <#assign document = saxReaderUtil.read(journalArticle.getContent())/>
                                
                            <#assign urlTitle = journalArticle.getUrlTitle() />
                            <#assign assetURL = "/group/guest/-/" + urlTitle />
                        
                            <#assign valueCPUrgent = document.valueOf("//dynamic-element[@name='UrgentChange']/dynamic-content/text()") />
                            <#assign valueCPTitle = journalArticle.getTitle() />
                            <#assign valueCPReference = document.valueOf("//dynamic-element[@name='ChangeProposalReference']/dynamic-content/text()") />
                            <#assign valueCPStatus = document.valueOf("//dynamic-element[@name='ChangeProposalStatus']/dynamic-content/text()") />
                            
                            <#assign valueCPChangeCategory = document.valueOf("//dynamic-element[@name='ChangeCategory']/dynamic-content/text()") />
                            <#assign valueCPChangePath = document.valueOf("//dynamic-element[@name='ChangePath']/dynamic-content/text()") />
                            <#assign valueCPResponsibleCommittee = document.valueOf("//dynamic-element[@name='ResponsibleCommittee']/dynamic-content/text()") />
                            <#assign valueCPImplementationDate = document.valueOf("//dynamic-element[@name='CP_RELEASE_SCHEDULE']/dynamic-content/text()") />
                            <tr>
                                <td class="tdUrgent">
                                    <#if (valueCPUrgent == "Yes") >
                                        <svg data-toggle="tooltip" data-placement="left" title="Urgent" width="5,63px" height="18px" viewBox="0 0 32 32" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
                                            <title>C76D8C6B-21E6-4245-8EB7-12987DC99780@3x</title>
                                            <defs>
                                                <path d="M18.0625,20 C18.479167,20 18.833333,19.864583 19.125,19.59375 C19.416667,19.322917 19.5625,18.979167 19.5625,18.5625 L19.5625,18.5625 L20.4375,1.5625 C20.4375,1.14583302 20.291667,0.78125 20,0.46875 C19.708333,0.15625 19.354167,0 18.9375,0 L18.9375,0 L13.0625,0 C12.645833,0 12.291667,0.15625 12,0.46875 C11.708333,0.78125 11.5625,1.14583302 11.5625,1.5625 L11.5625,1.5625 L12.4375,18.5625 C12.4375,18.979167 12.583333,19.322917 12.875,19.59375 C13.166667,19.864583 13.520833,20 13.9375,20 L13.9375,20 L18.0625,20 Z M16,32 C17.375,32 18.552083,31.510417 19.53125,30.53125 C20.510417,29.552083 21,28.375 21,27 C21,25.625 20.510417,24.447917 19.53125,23.46875 C18.552083,22.489583 17.375,22 16,22 C14.625,22 13.447917,22.489583 12.46875,23.46875 C11.489583,24.447917 11,25.625 11,27 C11,28.375 11.489583,29.552083 12.46875,30.53125 C13.447917,31.510417 14.625,32 16,32 Z M18.0625,20 C18.479167,20 18.833333,19.864583 19.125,19.59375 C19.416667,19.322917 19.5625,18.979167 19.5625,18.5625 L19.5625,18.5625 L20.4375,1.5625 C20.4375,1.14583302 20.291667,0.78125 20,0.46875 C19.708333,0.15625 19.354167,0 18.9375,0 L18.9375,0 L13.0625,0 C12.645833,0 12.291667,0.15625 12,0.46875 C11.708333,0.78125 11.5625,1.14583302 11.5625,1.5625 L11.5625,1.5625 L12.4375,18.5625 C12.4375,18.979167 12.583333,19.322917 12.875,19.59375 C13.166667,19.864583 13.520833,20 13.9375,20 L13.9375,20 L18.0625,20 Z M16,32 C17.375,32 18.552083,31.510417 19.53125,30.53125 C20.510417,29.552083 21,28.375 21,27 C21,25.625 20.510417,24.447917 19.53125,23.46875 C18.552083,22.489583 17.375,22 16,22 C14.625,22 13.447917,22.489583 12.46875,23.46875 C11.489583,24.447917 11,25.625 11,27 C11,28.375 11.489583,29.552083 12.46875,30.53125 C13.447917,31.510417 14.625,32 16,32 Z" id="path-1"></path>
                                            </defs>
                                            <g id="Symbols" stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                                                <g id="Atom/Icon/Solid/Exclamation">
                                                    <rect id="Frame" x="0" y="0" width="32" height="32"></rect>
                                                    <mask id="mask-2" fill="white">
                                                        <use xlink:href="#path-1"></use>
                                                    </mask>
                                                    <use id="Icon" fill="#a1353c" fill-rule="nonzero" xlink:href="#path-1"></use>
                                                    <g id="Group" mask="url(#mask-2)">
                                                        <g transform="translate(-34.000000, -33.840376)">
                                                            <rect id="Colour/General/Black" fill="#a1353c" x="0" y="0" width="100" height="100"></rect>
                                                        </g>
                                                    </g>
                                                </g>
                                            </g>
                                        </svg>
                                    </#if>
                                </td>

                                <td class="tdTitle">
                                    <#if assetURL?has_content>
                                        <a href="${assetURL}">
                                        ${valueCPTitle}</a>
                                    <#else>
                                        ${journalArticle.getTitle(locale)}
                                    </#if>
                                    <BR>${valueCPReference}</a></BR>

                                    <#switch valueCPStatus>
                                        <#case "Service Provider Impact Assessment">
                                            <span class="status service-pro">${valueCPStatus}</span>
                                            <#break>
                                        <#case "Party Impact Assessment">
                                            <span class="status party-imp">${valueCPStatus}</span>
                                            <#break>
                                        <#case "Rejected">
                                            <span class="status rejected">${valueCPStatus}</span>
                                            <#break>
                                        <#case "Appeal in progress">
                                            <span class="status appeal-prs">${valueCPStatus}</span>
                                            <#break>
                                        <#case "Solution Development">
                                            <span class="status solution">${valueCPStatus}</span>
                                            <#break>
                                        <#case "Initial Assessment">
                                            <span class="status initial-asse">${valueCPStatus}</span>
                                            <#break>    
                                        <#case "Preliminary Assessment">
                                            <span class="status prel-asse">${valueCPStatus}</span>
                                            <#break>    
                                        <#case "Consultation">
                                            <span class="status consultation">${valueCPStatus}</span>
                                            <#break>     
                                        <#case "Final Assessment">
                                            <span class="status final-asse">${valueCPStatus}</span>
                                            <#break>     
                                        <#case "Approved - awaiting implementation">
                                            <span class="status approved-wait">${valueCPStatus}</span>
                                            <#break> 
                                        <#case "Awaiting Authority Decision">
                                            <span class="status waiting">${valueCPStatus}</span>
                                            <#break> 
                                        <#case "Withdrawn">
                                            <span class="status withdrawn">${valueCPStatus}</span>
                                            <#break> 
                                        <#case "Implemented">
                                            <span class="status implement">${valueCPStatus}</span>
                                            <#break>
                                        <#default>
                                    
                                        <#assign res = valueCPStatus?matches("Approved.*")>
                                        <#if res>
                                            <span class="status approved-wait">${valueCPStatus}</span>
                                        <#else>
                                            <span class="status">${valueCPStatus}</span>
                                        </#if>
                                    </#switch>
                                </td>
                                
                                <#if valueCPImplementationDate?has_content >
                                    <td class="tdDate">${valueCPImplementationDate}</td>
                                <#else>
                                    <td class="tdDate">-</td>
                                </#if>
                        
                                <#if impactedParties?has_content >
                                    <td class="tdImpacted">${impactedParties}</td>
                                <#else>
                                    <td class="tdImpacted">-</td>
                                </#if>
                                <#if valueCPChangeCategory?has_content>
                                    <td class="tdChange">${valueCPChangeCategory}</td>
                                <#else>
                                    <td class="tdChange">-</td>
                                </#if>
                                
                                <#if valueCPChangePath?has_content>
                                    <td class="tdCP">${valueCPChangePath}</td>
                                <#else>
                                    <td class="tdCP">-</td>
                                </#if>
                                
                                <#if valueCPResponsibleCommittee?has_content>
                                    <td class="tdRespon">${valueCPResponsibleCommittee}</td>
                                <#else>
                                    <td class="tdRespon">-</td>
                                </#if>

                                <td class="tdOpen"><a href="${assetURL}" class="open"><img src="${Imagerute}"/></a></td>
                                
                            </tr>
                        </#list>
				    </#if>	
					
		        <#elseif hasCustomFilter>  
                    <#if request.getParameter("page")??>
                        <#assign parameterPage = request.getParameter("page") />
                        <#assign numpage = parameterPage?number />
                    </#if>

                    <#if !filterStartDate?has_content || !filterEndDate?has_content>
                        <#assign filteredJA = recFormArticleService.getChangeProposalFiltered(filterStatus, null, null, filterChangeP, "", filterImpactedP, filterChangeC) />  
                        <#if request.getParameter("page")??>
                            <#assign pagination = recFormArticleService.getChangeProposalFilteredByPage(filterStatus, null, null, filterChangeP, "", filterImpactedP, filterChangeC, numpage) />
                        </#if>
                    <#else>
                        <#assign filteredJA = recFormArticleService.getChangeProposalFiltered(filterStatus, filterStartDate?datetime("yyyy-MM-dd HH:mm:ss"), filterEndDate?datetime("yyyy-MM-dd HH:mm:ss"), filterChangeP, "", filterImpactedP, filterChangeC) />
                        <#if request.getParameter("page")??>    
                            <#assign pagination = recFormArticleService.getChangeProposalFilteredByPage(filterStatus, filterStartDate?datetime("yyyy-MM-dd HH:mm:ss"), filterEndDate?datetime("yyyy-MM-dd HH:mm:ss"), filterChangeP, "", filterImpactedP, filterChangeC, numpage) />
                        </#if>
                    </#if>

                    <#if filteredJA?has_content>
                        <#if (filteredJA?size > 10)>
                            <style> #filtersButton{visibility:visible !important;}</style>
                            <#if request.getParameter("page")??>    
                                <#assign finalList = pagination />
                            </#if>
                        <#else>
                            <#assign finalList = filteredJA />
                        </#if>

                        <input id="listSizePagination" type="hidden" value="${filteredJA?size}"/>

                        <#list finalList as ja>
                            <#assign resourcePrimKey = ja.getResourcePrimKey() />
                            <#assign formId = recFormArticleService.getFormIdByArticleId(resourcePrimKey)/>
                            <#assign impactedParties = "" />
                            <#if recFormArticleService.getrecFormArticle(formId)??>
                                    <#assign article = recFormArticleService.getrecFormArticle(formId) />
                                    <#assign impactList = article.getArticleImpacts() />
                                    <#if impactList?contains(",")>
                                        <#list impactList?split(",") as eachImpactId>
                                            <#assign impactElement = impactService.getImpacts(eachImpactId?number) />
                                            <#assign impactElementCategory = impactService.getImpacts(eachImpactId?number).getImpactCategory() />
                                            <#if impactElementCategory == "REC-PARTY-AND-MARKET-PARTICIPANT-IMPACTS" >
                                                <#assign impactName = impactElement.getImpactName() />
                                                <#assign impactedParties += ", " + impactName />
                                            </#if>
                                        </#list>
                                        <#if (impactedParties?length > 2)>
                                            <#assign impactedParties = impactedParties?substring(2) />
                                        </#if>
                                    <#elseif impactList?has_content >
                                        <#assign impactElement = impactService.getImpacts(eachImpactId?number) />
                                        <#assign impactElement = impactService.getImpacts(eachImpactId?number) />
                                        <#assign impactElementCategory = impactService.getImpacts(eachImpactId?number).getImpactCategory() />
                                        <#if impactElementCategory == "REC-PARTY-AND-MARKET-PARTICIPANT-IMPACTS" >
                                            <#assign impactElement = impactService.getImpacts(impactList?number) />
                                            <#assign impactedParties = impactElement.getImpactName() />
                                        </#if>
                                    </#if>
                            </#if>

                            <#assign document = saxReaderUtil.read(ja.getContent())/>
                            
                            <#assign urlTitle = ja.getUrlTitle() />
                            <#assign assetURL = "/group/guest/-/" + urlTitle />
                    
                            <#assign valueCPUrgent = document.valueOf("//dynamic-element[@name='UrgentChange']/dynamic-content/text()") />
                            <#assign valueCPTitle = ja.getTitle() />
                            <#assign valueCPReference = document.valueOf("//dynamic-element[@name='ChangeProposalReference']/dynamic-content/text()") />
                            <#assign valueCPStatus = document.valueOf("//dynamic-element[@name='ChangeProposalStatus']/dynamic-content/text()") />
                            
                            <#assign valueCPChangeCategory = document.valueOf("//dynamic-element[@name='ChangeCategory']/dynamic-content/text()") />
                            <#assign valueCPChangePath = document.valueOf("//dynamic-element[@name='ChangePath']/dynamic-content/text()") />
                            <#assign valueCPResponsibleCommittee = document.valueOf("//dynamic-element[@name='ResponsibleCommittee']/dynamic-content/text()") />
                            <#assign valueCPImplementationDate = document.valueOf("//dynamic-element[@name='CP_RELEASE_SCHEDULE']/dynamic-content/text()") />
            
                            <tr>
                                <td class="tdUrgent">
                                    <#if (valueCPUrgent == "Yes") >
                                        <svg data-toggle="tooltip" data-placement="left" title="Urgent" width="5,63px" height="18px" viewBox="0 0 32 32" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
                                            <title>C76D8C6B-21E6-4245-8EB7-12987DC99780@3x</title>
                                            <defs>
                                                <path d="M18.0625,20 C18.479167,20 18.833333,19.864583 19.125,19.59375 C19.416667,19.322917 19.5625,18.979167 19.5625,18.5625 L19.5625,18.5625 L20.4375,1.5625 C20.4375,1.14583302 20.291667,0.78125 20,0.46875 C19.708333,0.15625 19.354167,0 18.9375,0 L18.9375,0 L13.0625,0 C12.645833,0 12.291667,0.15625 12,0.46875 C11.708333,0.78125 11.5625,1.14583302 11.5625,1.5625 L11.5625,1.5625 L12.4375,18.5625 C12.4375,18.979167 12.583333,19.322917 12.875,19.59375 C13.166667,19.864583 13.520833,20 13.9375,20 L13.9375,20 L18.0625,20 Z M16,32 C17.375,32 18.552083,31.510417 19.53125,30.53125 C20.510417,29.552083 21,28.375 21,27 C21,25.625 20.510417,24.447917 19.53125,23.46875 C18.552083,22.489583 17.375,22 16,22 C14.625,22 13.447917,22.489583 12.46875,23.46875 C11.489583,24.447917 11,25.625 11,27 C11,28.375 11.489583,29.552083 12.46875,30.53125 C13.447917,31.510417 14.625,32 16,32 Z M18.0625,20 C18.479167,20 18.833333,19.864583 19.125,19.59375 C19.416667,19.322917 19.5625,18.979167 19.5625,18.5625 L19.5625,18.5625 L20.4375,1.5625 C20.4375,1.14583302 20.291667,0.78125 20,0.46875 C19.708333,0.15625 19.354167,0 18.9375,0 L18.9375,0 L13.0625,0 C12.645833,0 12.291667,0.15625 12,0.46875 C11.708333,0.78125 11.5625,1.14583302 11.5625,1.5625 L11.5625,1.5625 L12.4375,18.5625 C12.4375,18.979167 12.583333,19.322917 12.875,19.59375 C13.166667,19.864583 13.520833,20 13.9375,20 L13.9375,20 L18.0625,20 Z M16,32 C17.375,32 18.552083,31.510417 19.53125,30.53125 C20.510417,29.552083 21,28.375 21,27 C21,25.625 20.510417,24.447917 19.53125,23.46875 C18.552083,22.489583 17.375,22 16,22 C14.625,22 13.447917,22.489583 12.46875,23.46875 C11.489583,24.447917 11,25.625 11,27 C11,28.375 11.489583,29.552083 12.46875,30.53125 C13.447917,31.510417 14.625,32 16,32 Z" id="path-1"></path>
                                            </defs>
                                            <g id="Symbols" stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                                                <g id="Atom/Icon/Solid/Exclamation">
                                                    <rect id="Frame" x="0" y="0" width="32" height="32"></rect>
                                                    <mask id="mask-2" fill="white">
                                                        <use xlink:href="#path-1"></use>
                                                    </mask>
                                                    <use id="Icon" fill="#a1353c" fill-rule="nonzero" xlink:href="#path-1"></use>
                                                    <g id="Group" mask="url(#mask-2)">
                                                        <g transform="translate(-34.000000, -33.840376)">
                                                            <rect id="Colour/General/Black" fill="#a1353c" x="0" y="0" width="100" height="100"></rect>
                                                        </g>
                                                    </g>
                                                </g>
                                            </g>
                                        </svg>
                                    </#if>
                                </td>
            	                <td class="tdTitle">
                                    <#if assetURL?has_content>
                                        <a href="${assetURL}">
                                        ${valueCPTitle}</a>
                                    <#else>
                                        ${ja.getTitle(locale)}
                                    </#if>
                                    <BR>${valueCPReference}</a></BR>
                                    <#switch valueCPStatus>
                                        <#case "Service Provider Impact Assessment">
                                            <span class="status service-pro">${valueCPStatus}</span>
                                            <#break>
                                        <#case "Party Impact Assessment">
                                            <span class="status party-imp">${valueCPStatus}</span>
                                            <#break>
                                        <#case "Rejected">
                                            <span class="status rejected">${valueCPStatus}</span>
                                            <#break>
                                        <#case "Appeal in progress">
                                            <span class="status appeal-prs">${valueCPStatus}</span>
                                            <#break>
                                        <#case "Solution Development">
                                            <span class="status solution">${valueCPStatus}</span>
                                            <#break>
                                        <#case "Initial Assessment">
                                            <span class="status initial-asse">${valueCPStatus}</span>
                                            <#break>    
                                        <#case "Preliminary Assessment">
                                            <span class="status prel-asse">${valueCPStatus}</span>
                                            <#break>    
                                        <#case "Consultation">
                                            <span class="status consultation">${valueCPStatus}</span>
                                            <#break>     
                                        <#case "Final Assessment">
                                            <span class="status final-asse">${valueCPStatus}</span>
                                            <#break>     
                                        <#case "Approved - awaiting implementation">
                                            <span class="status approved-wait">${valueCPStatus}</span>
                                            <#break> 
                                        <#case "Awaiting Authority Decision">
                                            <span class="status waiting">${valueCPStatus}</span>
                                            <#break> 
                                        <#case "Withdrawn">
                                            <span class="status withdrawn">${valueCPStatus}</span>
                                            <#break> 
                                        <#case "Implemented">
                                            <span class="status implement">${valueCPStatus}</span>
                                            <#break>
                                        <#default>
                                            
                                        <#assign res = valueCPStatus?matches("Approved.*")>
                                            <#if res>
                                                <span class="status approved-wait">${valueCPStatus}</span>
                                            <#else>
                                                <span class="status">${valueCPStatus}</span>
                                            </#if>
                                    </#switch>
                                </td>
    	            
                                <#if valueCPImplementationDate?has_content >
                                    <td class="tdDate">${valueCPImplementationDate}</td>
                                <#else>
                                    <td class="tdDate">-</td>
                                </#if>
                                    
                                <#if impactedParties?has_content >
                                    <td class="tdImpacted">${impactedParties}</td>
                                <#else>
                                    <td class="tdImpacted">-</td>
                                </#if>
                                
                                <#if valueCPChangeCategory?has_content>
                                    <td class="tdChange">${valueCPChangeCategory}</td>
                                <#else>
                                    <td class="tdChange">-</td>
                                </#if>
                                
                                <#if valueCPChangePath?has_content>
                                    <td class="tdCP">${valueCPChangePath}</td>
                                <#else>
                                    <td class="tdCP">-</td>
                                </#if>
                                
                                <#if valueCPResponsibleCommittee?has_content>
                                    <td class="tdRespon">${valueCPResponsibleCommittee}</td>
                                <#else>
                                    <td class="tdRespond">-</td>
                                </#if>

                                <td class="tdOpen"><a href="${assetURL}" class="open"><img src="${Imagerute}"/></a></td>
                            </tr>
				        </#list>
			        </#if>			
		        <#else>
                    <#if entries?has_content>
                        <#list entries as curEntry>

                            <#if curEntry.getAssetRenderer()?has_content> 
                                <#assign renderer = curEntry.getAssetRenderer() />
                                <#assign className = renderer.getClassName() />                            
                            
                                <#if className == "com.liferay.journal.model.JournalArticle">
                                    <#assign journalArticle = renderer.getArticle() />

                                    <#assign resourcePrimKey = journalArticle.getResourcePrimKey() />
                                    
                                    <#assign formId = recFormArticleService.getFormIdByArticleId(resourcePrimKey)/>
                                    <#assign impactedParties = "" />

                                    <#if (formId > 0) && recFormArticleService.getrecFormArticle(formId)??>
                                        <#assign article = recFormArticleService.getrecFormArticle(formId) />
                                        <#assign impactList = article.getArticleImpacts() />
                                        <#if impactList?contains(",")>
                                            <#list impactList?split(",") as eachImpactId>	
                                                <#assign impactElement = impactService.getImpacts(eachImpactId?number) />
                                                <#assign impactElementCategory = impactService.getImpacts(eachImpactId?number).getImpactCategory() />
                                                <#if impactElementCategory == "REC-PARTY-AND-MARKET-PARTICIPANT-IMPACTS" >
                                                    <#assign impactName = impactElement.getImpactName() />
                                                    <#assign impactedParties += ", " + impactName />
                                                </#if>
                                            </#list>
                                            <#if (impactedParties?length > 2)>
                                                <#assign impactedParties = impactedParties?substring(2) />
                                            </#if>
                                        <#elseif impactList?has_content && eachImpactId??>
                                            <#assign impactElement = impactService.getImpacts(eachImpactId?number) />
                                            <#assign impactElement = impactService.getImpacts(eachImpactId?number) />
                                            <#assign impactElementCategory = impactService.getImpacts(eachImpactId?number).getImpactCategory() />
                                            <#if impactElementCategory == "REC-PARTY-AND-MARKET-PARTICIPANT-IMPACTS" >
                                                <#assign impactElement = impactService.getImpacts(impactList?number) />
                                                <#assign impactedParties = impactElement.getImpactName() />
                                            </#if>
                                        </#if>
                                    </#if>

                                    <#assign document = saxReaderUtil.read(journalArticle.getContent())/>
                                    <#assign urlTitle = journalArticle.getUrlTitle() />
                                    <#assign assetURL = "/group/guest/-/" + urlTitle />
                            
                                    <#assign valueCPUrgent = document.valueOf("//dynamic-element[@name='UrgentChange']/dynamic-content/text()") />
                                    <#assign valueCPTitle = journalArticle.getTitle() />
                                    <#assign valueCPReference = document.valueOf("//dynamic-element[@name='ChangeProposalReference']/dynamic-content/text()") />
                                    <#assign valueCPStatus = document.valueOf("//dynamic-element[@name='ChangeProposalStatus']/dynamic-content/text()") />
                                    
                                    <#assign valueCPChangeCategory = document.valueOf("//dynamic-element[@name='ChangeCategory']/dynamic-content/text()") />
                                    <#assign valueCPChangePath = document.valueOf("//dynamic-element[@name='ChangePath']/dynamic-content/text()") />
                                    <#assign valueCPResponsibleCommittee = document.valueOf("//dynamic-element[@name='ResponsibleCommittee']/dynamic-content/text()") />
                                    <#assign valueCPImplementationDate = document.valueOf("//dynamic-element[@name='CP_RELEASE_SCHEDULE']/dynamic-content/text()") />
            
                                    <tr>
                                        <td class="tdUrgent">
                                            <#if (valueCPUrgent == "Yes") >
                                                <svg data-toggle="tooltip" data-placement="left" title="Urgent" width="5,63px" height="18px" viewBox="0 0 32 32" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
                                                    <title>C76D8C6B-21E6-4245-8EB7-12987DC99780@3x</title>
                                                    <defs>
                                                        <path d="M18.0625,20 C18.479167,20 18.833333,19.864583 19.125,19.59375 C19.416667,19.322917 19.5625,18.979167 19.5625,18.5625 L19.5625,18.5625 L20.4375,1.5625 C20.4375,1.14583302 20.291667,0.78125 20,0.46875 C19.708333,0.15625 19.354167,0 18.9375,0 L18.9375,0 L13.0625,0 C12.645833,0 12.291667,0.15625 12,0.46875 C11.708333,0.78125 11.5625,1.14583302 11.5625,1.5625 L11.5625,1.5625 L12.4375,18.5625 C12.4375,18.979167 12.583333,19.322917 12.875,19.59375 C13.166667,19.864583 13.520833,20 13.9375,20 L13.9375,20 L18.0625,20 Z M16,32 C17.375,32 18.552083,31.510417 19.53125,30.53125 C20.510417,29.552083 21,28.375 21,27 C21,25.625 20.510417,24.447917 19.53125,23.46875 C18.552083,22.489583 17.375,22 16,22 C14.625,22 13.447917,22.489583 12.46875,23.46875 C11.489583,24.447917 11,25.625 11,27 C11,28.375 11.489583,29.552083 12.46875,30.53125 C13.447917,31.510417 14.625,32 16,32 Z M18.0625,20 C18.479167,20 18.833333,19.864583 19.125,19.59375 C19.416667,19.322917 19.5625,18.979167 19.5625,18.5625 L19.5625,18.5625 L20.4375,1.5625 C20.4375,1.14583302 20.291667,0.78125 20,0.46875 C19.708333,0.15625 19.354167,0 18.9375,0 L18.9375,0 L13.0625,0 C12.645833,0 12.291667,0.15625 12,0.46875 C11.708333,0.78125 11.5625,1.14583302 11.5625,1.5625 L11.5625,1.5625 L12.4375,18.5625 C12.4375,18.979167 12.583333,19.322917 12.875,19.59375 C13.166667,19.864583 13.520833,20 13.9375,20 L13.9375,20 L18.0625,20 Z M16,32 C17.375,32 18.552083,31.510417 19.53125,30.53125 C20.510417,29.552083 21,28.375 21,27 C21,25.625 20.510417,24.447917 19.53125,23.46875 C18.552083,22.489583 17.375,22 16,22 C14.625,22 13.447917,22.489583 12.46875,23.46875 C11.489583,24.447917 11,25.625 11,27 C11,28.375 11.489583,29.552083 12.46875,30.53125 C13.447917,31.510417 14.625,32 16,32 Z" id="path-1"></path>
                                                    </defs>
                                                    <g id="Symbols" stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                                                        <g id="Atom/Icon/Solid/Exclamation">
                                                            <rect id="Frame" x="0" y="0" width="32" height="32"></rect>
                                                            <mask id="mask-2" fill="white">
                                                                <use xlink:href="#path-1"></use>
                                                            </mask>
                                                            <use id="Icon" fill="#a1353c" fill-rule="nonzero" xlink:href="#path-1"></use>
                                                            <g id="Group" mask="url(#mask-2)">
                                                                <g transform="translate(-34.000000, -33.840376)">
                                                                    <rect id="Colour/General/Black" fill="#a1353c" x="0" y="0" width="100" height="100"></rect>
                                                                </g>
                                                            </g>
                                                        </g>
                                                    </g>
                                                </svg>
                                            </#if>
                                        </td>
            	                        <td class="tdTitle">
                                            <#if assetURL?has_content>
                                                <a href="${assetURL}">
                                            ${valueCPTitle}</a>
                                            <#else>
                                                ${curEntry.getTitle(locale)}
                                            </#if>
                                            <BR>${valueCPReference}</a></BR>
                                            <#switch valueCPStatus>
                                                <#case "Service Provider Impact Assessment">
                                                    <span class="status service-pro">${valueCPStatus}</span>
                                                    <#break>
                                                <#case "Party Impact Assessment">
                                                    <span class="status party-imp">${valueCPStatus}</span>
                                                    <#break>
                                                <#case "Rejected">
                                                    <span class="status rejected">${valueCPStatus}</span>
                                                    <#break>
                                                <#case "Appeal in progress">
                                                    <span class="status appeal-prs">${valueCPStatus}</span>
                                                    <#break>
                                                <#case "Solution Development">
                                                    <span class="status solution">${valueCPStatus}</span>
                                                    <#break>
                                                <#case "Initial Assessment">
                                                    <span class="status initial-asse">${valueCPStatus}</span>
                                                    <#break>    
                                                <#case "Preliminary Assessment">
                                                    <span class="status prel-asse">${valueCPStatus}</span>
                                                    <#break>    
                                                <#case "Consultation">
                                                    <span class="status consultation">${valueCPStatus}</span>
                                                    <#break>     
                                                <#case "Final Assessment">
                                                    <span class="status final-asse">${valueCPStatus}</span>
                                                    <#break>     
                                                <#case "Approved - awaiting implementation">
                                                    <span class="status approved-wait">${valueCPStatus}</span>
                                                    <#break> 
                                                <#case "Awaiting Authority Decision">
                                                    <span class="status waiting">${valueCPStatus}</span>
                                                    <#break> 
                                                <#case "Withdrawn">
                                                    <span class="status withdrawn">${valueCPStatus}</span>
                                                    <#break> 
                                                <#case "Implemented">
                                                    <span class="status implement">${valueCPStatus}</span>
                                                    <#break>
                                                <#default>
                                                    
                                                <#assign res = valueCPStatus?matches("Approved.*")>
                                                <#if res>
                                                    <span class="status approved-wait">${valueCPStatus}</span>
                                                <#else>
                                                    <span class="status">${valueCPStatus}</span>
                                                </#if>
                                            </#switch>
            	                        </td>
    	            
                                        <#if valueCPImplementationDate?has_content >
                                            <td class="tdDate">${valueCPImplementationDate}</td>
                                        <#else>
                                            <td class="tdDate">-</td>
                                        </#if>
                                        
                                        <#if impactedParties?has_content >
                                            <td class="tdImpacted">${impactedParties}</td>
                                        <#else>
                                            <td class="tdImpacted">-</td>
                                        </#if>
                                        <#if valueCPChangeCategory?has_content>
                                            <td class="tdChange">${valueCPChangeCategory}</td>
                                        <#else>
                                            <td class="tdChange">-</td>
                                        </#if>	
                                        
                                        <#if valueCPChangePath?has_content>
                                            <td class="tdCP">${valueCPChangePath}</td>
                                        <#else>
                                            <td class="tdCP">-</td>
                                        </#if>
                                        
                                        <#if valueCPResponsibleCommittee?has_content>
                                            <td class="tdRespon">${valueCPResponsibleCommittee}</td>
                                        <#else>
                                            <td class="tdRespon">-</td>
                                        </#if>
                                        <td class="tdOpen"><a href="${assetURL}" class="open"><img src="${Imagerute}"/></a></td>
            	                    </tr>
            	                </#if>
                            </#if>
	                    </#list>
                    </#if>
		        </#if>
            </tbody>
        </table>
    </div>
    <div id="filtersButton">
        <input id="nextPage" type="button" value="Next →" onclick="nextPage()">
        <input id="previousPage" type="button" value="← Previous" onclick="previousPage()">
        <div id="paginationInfo">
            <h3> 10 items per Page - Showing </h3>
            <h3 id="listPositionFiltered"> </h3>
            <h3> of </h3>
            <h3 id="paginationFilteredInfo"> </h3>
            <h3> results. </h3>
        </div>
    </div>
</div>

<#-- popUp to add filters -->
<div id="change-register-pop-up1" class="popup1">
    <div class="popupFilter">     
        <div class= filterOption>
            <h1> Filter Options </h1> 
            <a class="close" onclick=closePopUp()>&times;</a> 
        </div>

        <div class= filterOption >
            <h3>  Change Reference and Title </h3>
            <div id="referenceTitle">
                <div class="filtersDropdown">
                    <div class="filterDropdownHeader" id="referenceTitleHeading">
                        <button class="btn btnDropDown" data-toggle="collapse" data-target="#content5" aria-expanded="false" aria-controls="content5">
                            <div>
                                <h2 id="dropdownSelected"> Please Select... </h2>
                                <img src= "${images_folder}/forms/chevron-down.svg"/> 
                            </div>
                        </button>
                    </div>

                    <div id="content5" class="collapse filterTitle" aria-labelledby="referenceTitleHeading" data-parent="#referenceTitleHeading" style="">
                        <div>
                            <div class="input-group search-bar-simple">
                                <div class="input-group-item search-bar-keywords-input-wrapper">
                                    <input class="form-control input-group-inset input-group-inset-after search-bar-keywords-input" data-qa-id="searchInput" id="dkrk___q" name="q" placeholder="Search..." title="Search" type="text" value="">
                                    <input class="field form-control" id="_com_liferay_portal_search_web_search_bar_portlet_SearchBarPortlet_INSTANCE_templateSearch_scope" name="_com_liferay_portal_search_web_search_bar_portlet_SearchBarPortlet_INSTANCE_templateSearch_scope" type="hidden" value="">
                                    <div class="input-group-inset-item input-group-inset-item-after">
										<button class="btn btn-monospaced btn-unstyled" type="submit" aria-label="Submit"><span class="inline-item"><svg class="lexicon-icon lexicon-icon-search" role="presentation" viewBox="0 0 512 512"><use xlink:href="${images_folder}/clay/icons.svg#search"></use></svg></span></button>                                 
                                    </div>
                                </div>
                            </div>

                            <div class="cp-list">
                                <#assign option =''/>
								<#assign allRecFormEntries = recFormArticleService.getrecFormArticles(-1, -1) />
								<#if allRecFormEntries?has_content>
									<#list allRecFormEntries as recFormA>
                                        <#if journalArticleService.fetchLatestArticle(recFormA.getArticleId())??>
                                            <#assign journalArticleFilter = journalArticleService.fetchLatestArticle(recFormA.getArticleId()) />

											<#assign valueCPTitleFilter = journalArticleFilter.getTitle()/>
											<li data-search-term="${valueCPTitleFilter?lower_case}">
												<label class="containerStatus">
													<#assign option ='op1'/>
													<input type="checkbox" name="radio" id="radio4" data-resourcepk="${journalArticleFilter.getResourcePrimKey()}"> 
													<span  class="checkBoxCustom" for="radio4"></span> 
													<p> ${valueCPTitleFilter}</p>
												</label><br>
											</li>
										</#if>
									</#list>
								</#if>
                            </div>
                        </div>
                    </div>
                </div>
            </div> 
        </div>   

        <div class="filterOption statusOptions">
            <h3> Status </h3>
            <div class="grid-container filterStatus">
                <div class="grid-item">
                    <label class="containerStatus">
                        <input type="checkbox" name="row1op1" value="" class="status service-pro" data-status="Initial Assessment"> 
                        <span  class="checkBoxCustom" for="row1op1"></span>
                        <span class="status initial-asse"> Initial Assessment</span>
                    </label>   
                </div>

                <div class="grid-item">
                    <label class="containerStatus">
                        <input type="checkbox" name="row1op2" value="" data-status="Consultation">
                        <span  class="checkBoxCustom" for="row1op2"> </span> 
                        <span class="status consultation"> Consultation</span>
                    </label>   
                </div>

                <div class="grid-item">
                    <label class="containerStatus">
                        <input type="checkbox" name="row1op3" value="" data-status="Withdrawn">
                        <span  class="checkBoxCustom" for="row1op2"> </span> 
                        <span class="status withdrawn"> Withdrawn</span>
                    </label>   
                </div>

                 <div class="grid-item">
                    <label class="containerStatus">
                        <input type="checkbox" name="row2op1" value="" data-status="Solution Development">
                        <span  class="checkBoxCustom" for="row2op1"> </span> 
                        <span class="status solution"> Solution Development</span>
                    </label>   
                </div>

                 <div class="grid-item">
                    <label class="containerStatus">
                        <input type="checkbox" name="row2op2" value="" data-status="Final Assessment">
                        <span  class="checkBoxCustom" for="row2op2"> </span> 
                        <span class="status  final-asse">  Final Assessment</span>
                    </label>   
                </div>

                <div class="grid-item">
                    <label class="containerStatus">
                        <input type="checkbox" name="row2op3" value="" data-status="Appeal in progress">
                        <span  class="checkBoxCustom" for="row2op3"> </span> 
                        <span class="status appeal-prs"> Appeal in progress</span>
                    </label>   
                </div>

                <div class="grid-item">
                    <label class="containerStatus">
                        <input type="checkbox" name="row3op1" value="" data-status="Services provider Impact Assessment">
                        <span  class="checkBoxCustom" for="row3op1"> </span> 
                        <span class="status service-pro"> Services provider Impact Assessment</span>
                    </label>   
                </div>

                <div class="grid-item">
                    <label class="containerStatus">
                        <input type="checkbox" name="row3op2" value="" data-status="Approved - awaiting implementation">
                        <span  class="checkBoxCustom" for="row3op2"> </span> 
                        <span class="status approved-wait"> Approved - awaiting implementation </span>
                    </label>   
                </div>

                <div class="grid-item">
                    <label class="containerStatus">
                        <input type="checkbox" name="row3op3" value="" data-status="Implemented">
                        <span  class="checkBoxCustom" for="row3op3"> </span> 
                        <span class="status implement"> Implemented </span>
                    </label>   
                </div>

                <div class="grid-item">
                    <label class="containerStatus">
                        <input type="checkbox" name="row4op1" value="" data-status="Party Impact Assessment">
                        <span  class="checkBoxCustom" for="row4op1"> </span> 
                        <span class="status party-imp"> Party Impact Assessment </span>
                    </label>   
                </div>

                <div class="grid-item">
                    <label class="containerStatus">
                        <input type="checkbox" name="row4op2" value="" data-status="Rejected">
                        <span  class="checkBoxCustom" for="row4op2"> </span> 
                        <span class="status rejected"> Rejected </span>
                    </label>   
                </div>

                <div class="grid-item">
                    <label class="containerStatus">
                        <input type="checkbox" name="row5op1" value="" data-status="Preliminary Assessment">
                        <span  class="checkBoxCustom" for="row5op1"> </span> 
                        <span class="status prel-asse"> Preliminary Assessment </span>
                    </label>   
                </div>

                <div class="grid-item">
                    <label class="containerStatus">
                        <input type="checkbox" name="row5op2" value="" data-status="Awaiting Authority">
                        <span  class="checkBoxCustom" for="row5op2"> </span> 
                        <span class="status waiting"> Awaiting Authority </span>
                    </label>   
                </div>

            </div>
        </div>

        <div id="implementationDate" class="filterOption filterImplementation">
            <h3>  Implementation Date </h3>
            <div  class= shareRow>
                <div class="leftSide"> 
                    <div class="filtersDropdown"> 
                        <button class="btn btnDropDown" data-toggle="collapse" data-target="#dateFrom" aria-expanded="false" aria-controls="dateFrom"> From... 
                            <img src="${images_folder}/forms/calendar-black.svg"/>
                        </button>
                        <div id="dateFrom" class="collapse" aria-labelledby="dateFrom" data-parent="#dateFrom" style="">
                            <input type="date" id="start" name="trip-start" value="">
                        </div>
                    </div>
                </div>
                <div class="rigthSide">  
                    <div class="filtersDropdown">  
                        <button class="btn btnDropDown" data-toggle="collapse" data-target="#dateTo" aria-expanded="false" aria-controls="dateTo">   To...
                            <img id="dateToImg"  src="${images_folder}/forms/calendar-black.svg"/>
                        </button>
                        <div id="dateTo" class="collapse" aria-labelledby="dateTo" data-parent="#dateTo" style="">
                            <input type="date" id="end" name="trip-start" value=""> 
                        </div>   
                    </div>
                </div>
            </div>
        </div>

        <div class="filterOption">
            <div class="shareRow">
                <div class="leftSide full-row">
                    <h3>  Impacted Parties </h3>
                    <div id="impactedParties">
                        <div class="filtersDropdown">
                            <div class="filterDropdownHeader" id="impactedPartiesHeading">
                                <button class="btn btnDropDown" data-toggle="collapse" data-target="#content1" aria-expanded="false" aria-controls="content1"> 
                                    <h2> Please Select... </h2>
                                    <img src="${images_folder}/forms/chevron-down.svg"/>
                                </button>
                            </div>

                            <div id="content1" class="collapse filterImpacted" aria-labelledby="impactedPartiesHeading" data-parent="#impactedPartiesHeading" style="">
                                <div>							
									<p>REC Party and Market Participant impacts</p>									
									<#list marketParticipantList as marketParticipant>  
										<li>
											<label class="containerStatus">
												<input type="checkbox" name="radio" id="radio5" data-impacted="${marketParticipant.getImpactId()}"> 
												<span  class="checkBoxCustom" for="radio5"  ></span>
												<p> ${marketParticipant.getImpactName()} </p>
											</label>
											<br>
										</li>
									</#list> 
                                </div>
                            </div>
                        </div>
                    </div>   
                </div>

                <div class="rigthSide hide">
                    <h3> Responsible Committees </h3>
                    <div id=responsibleComittes>
                        <div class="filtersDropdown">
                            <div class="filterDropdownHeader" id="responsibleComiteesHeading">
                                <button class="btn btnDropDown" data-toggle="collapse" data-target="#content2" aria-expanded="false" aria-controls="content2"> 
                                    <h2> Please Select... </h2>
                                    <img src="${images_folder}/forms/chevron-down.svg"/>
                                </button>
                            </div>

                            <div id="content2" class="collapse filterResponsible" aria-labelledby="responsibleComiteesHeading" data-parent="#responsibleComiteesHeading" style="">
                                <div>
                                    <#assign option =''/>
                                    <li>
                                        <label class="containerStatus">
                                            <#assign option ='op1'/> <#--get the different values and send what needs it-->
                                            <input type="checkbox" name="radio" id="radio4"> 
                                            <span  class="checkBoxCustom" for="radio4"></span> 
                                            <p> CP XXX - Lorem ipsum dolor sit </p>
                                        </label>
                                        <br>
                                    </li>
                                        
                                    <li>
                                        <label class="containerStatus">
                                            <#assign option ='op2'/>
                                            <input type="checkbox" name="radio" id="radio5"> 
                                            <span  class="checkBoxCustom" for="radio5"  ></span>
                                            <p> CP XXX - Lorem ipsum dolor sit </p>
                                        </label>
                                        <br>
                                    </li>
                                        
                                    <li>
                                        <label class="containerStatus">
                                            <#assign option ='op3'/>
                                            <input type="checkbox" name="radio" id="radio6"> 
                                            <span  class="checkBoxCustom" for="radio6"  ></span> 
                                            <p> CP XXX - Lorem ipsum dolor sit </p>
                                        </label>
                                        <br>
                                    </li> 

                                </div>
                            </div>
                        </div>
                    </div>
                </div> 
            </div> 
        </div>

        <div class= filterOption>
            <div class=shareRow>
                <div class="leftSide">
                    <h3>  Change Category </h3>
                    <div id= changeCategory>
                        <div class="filtersDropdown">
                            <div class="filterDropdownHeader" id="changeCategoryheading">
                                <button class="btn btnDropDown" data-toggle="collapse" data-target="#content3" aria-expanded="false" aria-controls="content3">
                                    <h2> Please Select... </h2>
                                    <img src="${images_folder}/forms/chevron-down.svg"/>
                                </button>
                            </div>

                            <div id="content3" class="collapse filterCategory" aria-labelledby="changeCategoryheading" data-parent="#changeCategoryheading" style="">
                                <div>
                                    <#assign option =''/>
                                    <li>
                                        <label class="containerStatus">
                                            <#assign option ='op1'/> <#--get the different values and send what needs it-->
                                            <input type="checkbox" name="radio" id="radio4" data-category="Category 1"> 
                                            <span  class="checkBoxCustom" for="radio4"></span> 
                                            <p> Category 1 </p>
                                        </label>
                                        <br>
                                    </li>
                                        
                                    <li>
                                        <label class="containerStatus">
                                            <#assign option ='op2'/>
                                            <input type="checkbox" name="radio" id="radio5" data-category="Category 2"> 
                                            <span  class="checkBoxCustom" for="radio5"></span>
                                            <p> Category 2 </p>
                                        </label>
                                        <br>
                                    </li>
                                        
                                    <li>
                                        <label class="containerStatus">
                                            <#assign option ='op3'/>
                                            <input type="checkbox" name="radio" id="radio6" data-category="Category 3"> 
                                            <span  class="checkBoxCustom" for="radio6"></span> 
                                            <p> Category 3 </p>
                                        </label>
                                        <br>
                                    </li>   
                                    
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="rigthSide">
                    <h3>  Change Path </h3>
                    <div id=changePath>

                        <div class="filtersDropdown">
                            <div class="filterDropdownHeader" id="changePathHeading">
                                <button class="btn btnDropDown" data-toggle="collapse" data-target="#content4" aria-expanded="false" aria-controls="content4"> 
                                    <h2> Please Select... </h2>
                                    <img src="${images_folder}/forms/chevron-down.svg"/>
                                </button>
                            </div>

                            <div id="content4" class="collapse filterChangeP" aria-labelledby="changePathHeading" data-parent="#changePathHeading" style="">
                                <div>
                                    <#assign option =''/>
                                    <li>
                                        <label class="containerStatus">
                                            <#assign option ='op1'/> <#--get the different values and send what needs it-->
                                            <input type="checkbox" name="radio" id="radio4" data-change="Self-Governance Led"> 
                                            <span  class="checkBoxCustom" for="radio4"></span> 
                                            <p> Self-Governance Led </p>
                                        </label>
                                        <br>
                                    </li>
                                        
                                    <li>
                                        <label class="containerStatus">
                                            <#assign option ='op2'/>
                                            <input type="checkbox" name="radio" id="radio5" data-change="Authority Led"> 
                                            <span  class="checkBoxCustom" for="radio5"></span>
                                            <p> Authority Led </p>
                                        </label>
                                        <br>
                                    </li>  
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        
        <div>
			<input class="btn applyFilters" type="submit" value="Apply filters" onclick="applyFilters()">
            <input class="btn clearFilters" type="submit" value="Clear filters" onclick="clearFilters()">
        </div>
    </div>
</div>



<script>

    function previousPage(){
        var currentURL = new URL(window.location.href);
        var paramPage = currentURL.searchParams.get('page');
        var numpage = parseInt(paramPage);
        numpage = numpage - 1;
        currentURL.searchParams.delete('page');
        currentURL.searchParams.append('page', numpage);
        window.location.replace(currentURL);
    }

    function nextPage(){
        var currentURL = new URL(window.location.href);
        var paramPage = currentURL.searchParams.get('page');
        var numpage = parseInt(paramPage);
        numpage = numpage + 1;
        currentURL.searchParams.delete('page');
        currentURL.searchParams.append('page', numpage);
        window.location.replace(currentURL);
    }

    $( document ).ready(function() {
        var currentURL = new URL(window.location.href);
        var paramPage = currentURL.searchParams.get('page');
        var numpage = parseInt(paramPage);
        var sizeList = document.getElementById("listSizePagination").value;
        $("#paginationFilteredInfo").html(sizeList);
        var displayFirstPageNum =(numpage*10) + 1;
        var displayLastPageNum = (numpage+1)*10;       
        
        if (sizeList%10 == 0){
            var lastPage = sizeList/10;
        } else {
            var lastPage = Math.trunc(sizeList/10);
        }

        if (paramPage != null){
            if (numpage == lastPage){ 
                document.getElementById("nextPage").style.visibility = "hidden";
                $("#listPositionFiltered").html(displayFirstPageNum + "-" + sizeList);
            } else{
                $("#listPositionFiltered").html(displayFirstPageNum + "-" + displayLastPageNum);
            }
            if (numpage != 0){
                document.getElementById("previousPage").style.visibility = "visible";
            } 
        }
    });

</script>
