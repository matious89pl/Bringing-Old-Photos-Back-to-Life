/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

$(document).ready(function () {
    //renderMenu();
	mutations();
    popupNotifications();
    dynamicDataListColors();
    resizePersonalMenu();
    refreshLists();
});

window.onload = function() {
    resizePersonalMenu();
};


function refreshLists() {
    if($("#table-consult #instanceReportTable").length || $("#myApplication #myApplicationTableList").length || $("#table-impact #instanceReportTable").length)
    {
        (function()
        {
        if( window.localStorage )
        {
            if( !localStorage.firstLoad )
            {
            localStorage.firstLoad = true;
            window.location.reload();
            }
            else {
            localStorage.removeItem('firstLoad');
            }
        }
        })();
    }

}

function popupNotifications() {
    var modal = document.getElementById("popup-notifications");

    $(".notifications-item").click(function () {
        modal.style.visibility = "visible";
    });


    $(document).click(function (event) {
        //if you click on anything except the modal itself or the "open modal" link, close the modal
        if (!$(event.target).closest("#popup-notifications,.notifications-item").length) {
            modal.style.visibility = "hidden";
        }
    });

}

function renderMenu() {
    /*$("#left-menu li.icons").each(function () {
        $(this).removeClass("active-menu");
    });
    console.log("active-menu class removed");*/

    var pathName = window.location.pathname;
    console.log("pathName: " + pathName);
    if (highlightMenu(pathName) == ""){
        //http://localhost:8080/group/guest/party-management
        var previousPathName = document.referrer;
        var pathSplited = previousPathName.split("/group/guest");
        var lastPathName = "/group/guest".concat(pathSplited[pathSplited.length - 1]);
        console.log("previousPathName: " + lastPathName);
        highlightMenu(lastPathName);
    }
}

    function highlightMenu(pathName){
        var leftOptionMenu = "";
        resetIcons();
        switch (pathName) {
            case '/':
            case '/web/guest/recportal':
            case '/web/guest':
                //leftOptionMenu = "#left-menu li.icon-dashboardMenu";
                $("#left-menu li.icon-dashboardMenu").addClass("active-menu");
                changeImgMenu("icon-dashboardMenu","home.svg");
                break;
            case '/group/guest/party-management':
            case '/group/guest/party-operations':
            case (pathName.match("\/group\/guest\/party-operations") || {}).input:
            case '/group/guest/my-applications':
            case '/group/guest/make-an-application':
            case '/group/guest/operational-contacts-register': 
            case '/group/guest/qualified-parties-register':  
            case '/group/guest/party-and-mem-register':
                //leftOptionMenu = "#left-menu li.icon-party";
                $("#left-menu li.icon-party").addClass("active-menu");
                changeImgMenu("icon-party","user_cog.svg");
                break;
            case '/group/guest/change-management':
            case '/group/guest/new-change-proposal-form':
            case '/group/guest/change-register':
            case (pathName.match("\/group\/guest\/change-proposal") || {}).input:
            case '/group/guest/impact-assessment':
            case '/group/guest/consultation-register':
            case '/group/guest/change-calendar':
                //leftOptionMenu = "#left-menu li.icon-change";
                $("#left-menu li.icon-change").addClass("active-menu");
                changeImgMenu("icon-change","cog.svg");
                break;
            case '/group/guest/committee-management':
            case '/group/guest/committees':
            case (pathName.match("\/committee-management") || {}).input:    
            case (pathName.match("\/committees") || {}).input:    
            case (pathName.match("\/group.*\/calendar-management") || {}).input:
                //leftOptionMenu = "#left-menu li.icon-committee";
                $("#left-menu li.icon-committee").addClass("active-menu");
                changeImgMenu("icon-committee","user.svg");
                break;
            case '/knowledge-base-test':
            case '/group/guest/raise-a-ticket':
            case '/group/guest/my-tickets':
                leftOptionMenu = "#left-menu li.icon-service";
                $("#left-menu li.icon-service").addClass("active-menu");
                changeImgMenu("icon-service","tools.svg");
                break;
            default:
                console.log("TBD next steps - No item in the lateral menu for this page");
        return leftOptionMenu;
    }

}

    function mutations(){

    // Function to add the "active-menu" class and change the icon based on the current URL path
   	function addActiveMenuClass() {
    	    let leftMenuLines = $("#left-menu li.icons");

    	    leftMenuLines.each(function() {
    	        let menuLineLinkPath = $(this).find("a").attr("href");
    	        let iconSrc = $(this).find("img").attr("src");
    	        let greenIcon = iconSrc.replace("-black", "");
    	        if (iconSrc.endsWith("-black") || iconSrc.endsWith("-white")){
    	        	iconSrc.replace("-black", "").replace("-white", "");
    	        }
    	        if (window.location.pathname === menuLineLinkPath) {
    	            $(this).find("img").attr("src", greenIcon);
    	            $(this).addClass("active-menu");
    	        } else {
    	            $(this).removeClass("active-menu");
    	        }
    	    });
    	}
      addActiveMenuClass();

    	// Function to run when mutations occur
    	function handleMutations(mutationsList, observer) {
    	  for (let mutation of mutationsList) {
    	    if (mutation.type === 'childList' || mutation.type === 'subtree') {
    	      addActiveMenuClass();
    	    }
    	  }
    	}

    	// Observer and attach it to the body element
    	let observer = new MutationObserver(handleMutations);
    	observer.observe(document.body, {
    	  childList: true,
    	  subtree: true
    	});

    	// Calling function to initially  add the "active-menu" class
    	addActiveMenuClass();
    }

function changeImgMenu(icon, newImg) {
    var newSrc = $("#left-menu li."+icon+" img")[0].getAttribute("src").replace($("#left-menu li."+icon+" img")[0].getAttribute("src").split("/").slice(-1), newImg);
                $("#left-menu li."+icon+" img")[0].setAttribute("src", newSrc);
}

function resetIcons() {
    changeImgMenu("icon-dashboardMenu","home-black.svg");
    changeImgMenu("icon-party","user-cog-black.svg");
    changeImgMenu("icon-change","cog-black.svg");
    changeImgMenu("icon-committee","user-black.svg");
    changeImgMenu("icon-service","tools-black.svg");
}

/*
 * This function gets loaded when all the HTML, not including the portlets, is
 * loaded.
 */
AUI().ready(function () {});

/*
 * This function gets loaded after each and every portlet on the page.
 *
 * portletId: the current portlet's id
 * node: the Alloy Node object of the current portlet
 */
Liferay.Portlet.ready(function (_portletId, _node) {});

/*
 * This function gets loaded when everything, including the portlets, is on
 * the page.
 */
Liferay.on('allPortletsReady', function () {});

/*
 * This function change the colours of the status in dynamic data list component (for the Change Plain Milestones tab).
 *
 */
function dynamicDataListColors(){
    
    $(".table-cell-content.lfr-progress-column a, .table-col-PM_Status").each(function(){
        var progressStatus = this.innerHTML;
    
        if (progressStatus === "Completed"){
            this.classList.add('progressCompleted');
        }
        else if (progressStatus === "In progress"){
            this.classList.add('progressInProgress');
        }
        else if (progressStatus === "Not Started"){
            this.classList.add('progressNotStarted');
        } 
        else if (progressStatus === "Rejected"){
            this.classList.add('progressRejected');
        }
        else if (progressStatus === "") {
            this.classList.add('progressEmpty');
        }
        else {
            this.classList.add('progressOtherStatus'); 
        }
  });
}

/*
 * This function is responsible to manage the personal menu size base on the user name length.
 *
 */
function resizePersonalMenu(){
    var totalWidth=$(".personal-user-name").width()+60;
    
    $("#rec-theme .personal-menu-dropdown button.dropdown-toggle").css("width", "");
    $("#rec-theme .personal-menu-dropdown button.dropdown-toggle").css("width", totalWidth+"px");
}

/**
 * Organisations pop up funcionality
 */
function openPopUpOrganisation(){
    $("#popupOrganisation").css("visibility","visible");
    $("#search-bar-hidden").css("visibility","hidden");
}
function noOrganisations(){
    $("#popupNoOrganisation").css("visibility","visible");
    $("#search-bar-hidden").css("visibility","hidden");
}
function closePopUpOrganisation(){							
    $("#popupOrganisation").css("visibility","hidden");
    $("#popupNoOrganisation").css("visibility","hidden");	
    $("#search-bar-hidden").css("display","block");			
}							

/**
 * Analytics function to track click on the REC and EMAR option on the left menu
 */
function digitalRECandEMAR() {
     console.log("In digitalRECandEMAR");
     Analytics.track("Digital REC and EMAR", {});
 }
