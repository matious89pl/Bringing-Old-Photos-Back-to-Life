<!-- ========START ThemeDisplay assigns======== -->
    <#assign id = themeDisplay.getSiteGroupId() />
    <#assign companyId= themeDisplay.getCompanyId() />
    <#assign userId = themeDisplay.getUserId() />
    <#assign siteGroupId = themeDisplay.getLayout().getGroupId() />
    <#assign ID = themeDisplay.getURLCurrent() />
<!-- ========END ThemeDisplay assigns======== -->
<!-- ========START Services assigns======== -->
    <#assign groupService = utilLocator.findUtil("com.liferay.portal.kernel.service.GroupLocalService") />
    <#assign OrgService = utilLocator.findUtil("com.liferay.portal.kernel.service.OrganizationLocalService") />
    <#assign counterService = utilLocator.findUtil("com.liferay.counter.kernel.service.CounterLocalService") />
    <#assign journalArticleService = serviceLocator.findService("com.liferay.journal.service.JournalArticleLocalService") />
<!-- ========END Services assigns======== -->
<!-- ========START Custom assigns======== -->
    <#assign org = groupService.getGroup(id) />
    <#assign orgName = org.getName("en_GB") />
    <#assign edit = false />
    <#assign AID = 0 />
    <#assign appRPK = 0 />
    <#assign ED_IA_Title = "" />
    <#assign ED_IA_CPID = "" />
    <#assign ED_IA_Type = "" />
    <#assign ED_IA_TargetedAudience = "" />
    <#assign ED_IA_ChooseQATemplate = "" />
    <#assign ED_IA_LinkToCPPage = "" />
    <#assign ED_IA_Documents = "" />
    <#assign ED_IA_Document_Title = "" />
    <#assign ED_IA_ResponsesDeadline = "" />
<!-- ========END Custom assigns======== -->
<#if request.getParameter('IAID')?has_content>
    <#assign ArticleID = request.getParameter('IAID') />
    <#assign AID = ArticleID?number />
    <#assign edit = true />
<#else>
    <#assign edit = false />
    <#assign assetURL = "/group/guest/impact-assessment" />
</#if>
<#if AID != 0 >
    <#assign appJournal = journalArticleService.getArticle(siteGroupId, "${ArticleID?number}")  />
            <#assign articlleCustomTitle = appJournal.getExpandoBridge().getAttribute("customTitle") />
    <#assign urlTitle = appJournal.getUrlTitle()/>
    <#assign assetURL = "/group/guest/-/" + urlTitle />
    <#assign ja_title = appJournal.getTitle() />
    <#assign appRPK = appJournal.getResourcePrimKey()  />
    <#assign document = saxReaderUtil.read(appJournal.getContent())/>
    <#assign ED_IA_Title = document.valueOf("//dynamic-element[@name='IA_Title']/dynamic-content/text()") />
    <#assign ED_IA_CPID = document.valueOf("//dynamic-element[@name='IA_CPID']/dynamic-content/text()") />
    <#assign ED_IA_Type = document.valueOf("//dynamic-element[@name='IA_Type']/dynamic-content/text()") />
    <#assign ED_IA_TargetedAudience = document.valueOf("//dynamic-element[@name='IA_TargetedAudience']/dynamic-content") />
    <#assign ED_IA_ChooseQATemplate = document.valueOf("//dynamic-element[@name='IA_ChooseQATemplate']/dynamic-content/text()") />
    <#assign ED_IA_LinkToCPPage = document.valueOf("//dynamic-element[@name='IA_LinkToCPPage']/dynamic-content/text()") />
    <#assign ED_IA_Documents =  saxReaderUtil.createXPath("//root//dynamic-element[@name='IA_Documents']//dynamic-content//text()") />
    <#assign ED_IA_Document_Title = saxReaderUtil.createXPath("//dynamic-element[@name='IA_Document_Title']//dynamic-content//text()") />
    <#assign IANODE =  ED_IA_Documents.selectNodes(document) />
    <#assign IANODE2 = ED_IA_Document_Title.selectNodes(document) />
    <#assign cont = 0 />
    <#list IANODE as nodes>
        <#if nodes.getText()?contains("classPK")>
            <#assign IADoc = jsonFactoryUtil.createJSONObject(nodes.getText())>
            <#assign IADocPK = IADoc.getString("classPK") />
            <#assign IADoc = jsonFactoryUtil.createJSONObject(nodes.getText())>
            <#assign IADocTitle = IADoc.getString("title") />
            <#assign IADoc = jsonFactoryUtil.createJSONObject(nodes.getText())>
            <#assign IADocUuid = IADoc.getString("uuid") />
            <#assign IADocTitleField = IANODE2[cont].getText() />
            <input type="hidden" class="fileInfoListItem" data-pk="${IADocPK}" data-name="${IADocTitle}" data-id="${IADocUuid}" data-doctitle="${IADocTitleField}"/>
            <#assign cont++>
        </#if>
    </#list>
    <#assign ED_IA_ResponsesDeadline = document.valueOf("//dynamic-element[@name='IA_ResponsesDeadline']/dynamic-content/text()") />
    <#assign targetAudienceSelector = saxReaderUtil.createXPath("//root//dynamic-element[@name='IA_TargetedAudience']//dynamic-content//option") />
    <#assign targetNodes = targetAudienceSelector.selectNodes(document) />
    <#assign valueTargetNodes>
        <#list targetNodes as nodes>
            ${nodes.getText()}<#sep>,</#sep>
        </#list>
    </#assign>
    <input type="hidden" id="selectedValuesForEdit" value="${valueTargetNodes}">
     <input type="hidden" id="filesToDelete" value="">
    <#if ED_IA_Title = "">
        <#assign ED_IA_Title = ja_title />
    </#if>
    <#assign edit = true />
<#else>
    <#assign edit = false />
</#if>
<div id="upload-file-form-pa">
   <div id="titleAssuranceDiv">
      <script src="https://cdn.jsdelivr.net/gh/bbbootstrap/libraries@main/choices.min.js"></script>
   </div>
   <form id="fileuploadForm" enctype='multipart/form-data'>
      <div>
        <p>Title *<input type="text" id="iaTitle" name="iaTitle" value="${ED_IA_Title}" /></p>
      </div>
      <div>
         <p>CP ID *<input type="text" id="cpId" name="cpId" value="${ED_IA_CPID}" /></p>
      </div>
      <p>
         <label for="impactAssessmentType">Impact Assessment Type *</label>
         <select name="selectType" id="selectType" >
            <option>Preliminary</option>
            <option>Standard</option>
            <option>Detailed</option>
         </select>
      <p>
         <label for="targetedAudience">Targeted Audience *</label>
         <select name="select1" id="select1" required multiple="multiple" size ="5">
            <option value="emptyOption">Please select...</option>
            <option value="allStakeholders">All stakeholders</option>
            <option value="RecParty">REC Parties</option>
            <option value="NonPartyServiceUsers">Non-Party Service Users</option>
            <option value="NonRECParties">Non-REC Parties</option>
            <option value="ServiceProviders">Service Providers</option>
         </select>
      </p>
      <div >
         <p><label for="anualMaintenanceOptions">Targeted Organisations *</label></p>
         <div >
            <select name="select2" id="select2" required size ="5" multiple >
               <option id="emptySelection" value="emptyOption"> Please select... </option>
               <option value="All Portal users" name="allStakeholders" id="All Portal users" class="hide">All Portal users</option>
               <option value="All REC Parties" name="RecParty" id="All REC Parties" class="hide">All REC Parties</option>
               <option value="All Energy Suppliers" name="RecParty" id="All Energy Suppliers" class="hide">All Energy Suppliers</option>
               <option value="Electricity Suppliers" name="RecParty" id="Electricity Suppliers" class="hide">Electricity Suppliers</option>
               <option value="Gas Suppliers" name="RecParty" id="Gas Suppliers" class="hide">Gas Suppliers </option>
               <option value="Domestic Suppliers" name="RecParty" id="Domestic Suppliers" class="hide">Domestic Suppliers</option>
               <option value="Non-Domestic Suppliers" name="RecParty" id="Non-Domestic Suppliers" class="hide">Non-Domestic Suppliers</option>
               <option value="All Metering Equipment Managers" name="RecParty" id="All Metering Equipment Managers" class="hide">All Metering Equipment Managers</option>
               <option value="Electricity MEMs" name="RecParty" id="Electricity MEMs" class="hide">Electricity MEMs</option>
               <option value="Gas MEMs" name="RecParty" id="Gas MEMs" class="hide">Gas MEMs</option>
               <option value="Distribution Network Operators" name="RecParty" id="Distribution Network Operators" class="hide">Distribution Network Operators</option>
               <option value="Gas Transporters" name="RecParty" id="Gas Transporters" class="hide">Gas Transporters</option>
               <option value="Data Communications Company (DCC)" name="RecParty" id="Data Communications Company (DCC)" class="hide">Data Communications Company (DCC)</option>
               <option value="EES Users" name="NonPartyServiceUsers" id="EES Users" class="hide">EES Users</option>
               <option value="GES Users" name="NonPartyServiceUsers" id="GES Users" class="hide">GES Users</option>
               <option value="GDCC Users" name="NonPartyServiceUsers" id="GDCC Users" class="hide">GDCC Users</option>
               <option value="CSS Users" name="NonPartyServiceUsers" id="CSS Users" class="hide">CSS Users</option>
               <option value="All non-REC Parties" name="NonRECParties" id="All non-REC Parties" class="hide">All non-REC Parties</option>
               <option value="Shippers" name="NonRECParties" id="Shippers" class="hide">Shippers</option>
               <option value="Data Collectors" name="NonRECParties" id="Data Collectors" class="hide">Data Collectors</option>
               <option value="Data Aggregators" name="NonRECParties" id="Data Aggregators" class="hide">Data Aggregators</option>
               <option value="Green Deal Providers" name="NonRECParties" id="Green Deal Providers" class="hide">Green Deal Providers</option>
               <option value="Green Deal Finance Parties" name="NonRECParties" id="Green Deal Finance Parties" class="hide">Green Deal Finance Parties</option>
               <option value="Meter Asset Providers" name="NonRECParties" id="Meter Asset Providers" class="hide">Meter Asset Providers</option>
               <option value="AMR Service Providers" name="NonRECParties" id="AMR Service Providers" class="hide">AMR Service Providers</option>
               <option value="CSS Provider / Switching Operator" name="ServiceProviders" id="CSS Provider / Switching Operator" class="hide">CSS Provider / Switching Operator</option>
               <option value="ERDS Provider" name="ServiceProviders" id="ERDS Provider" class="hide">ERDS Provider</option>
               <option value="GRDS Provider" name="ServiceProviders" id="GRDS Provider" class="hide">GRDS Provider</option>
               <option value="EES Provider" name="ServiceProviders" id="EES Provider" class="hide">EES Provider</option>
               <option value="GES Provider" name="ServiceProviders" id="GES Provider" class="hide">GES Provider</option>
               <option value="Energy Theft Tip Off Service (ETTOS)" name="ServiceProviders" id="Energy Theft Tip Off Service (ETTOS)" class="hide">Energy Theft Tip Off Service (ETTOS)</option>
               <option value="SDES Provider" name="ServiceProviders" id="SDES Provider" class="hide">SDES Provider</option>
               <option value="Green Deal Database Service Provider" name="ServiceProviders" id="Green Deal Database Service Provider" class="hide">Green Deal Database Service Provider</option>
               <option value="Central Data Service Provider" name="ServiceProviders" id="Central Data Service Provider" class="hide">Central Data Service Provider</option>
            </select>
         </div>
      </div>
      <p>
         <label for="impactAssessmentType">Choose Question Template *</label>
         <select name="selectQtpt" id="selectQtpt">
            <option value="A" name="A" > A Document</option>
            <option value="B" name="B" > B Answer and Document</option>
         </select>
      <div>
         <p>Link to Change Proposal <input type="text" id="linkToCP" name="linkToCP" value="${ED_IA_LinkToCPPage}" /></p>
      </div>
      <div id="assurancePeriodDiv">
         <p id="assurancePeriod"> Response Deadline * <input id="dateFile" type="date" name="dateFile" value="${ED_IA_ResponsesDeadline}" required></p>
      </div>
      <div id="uploadEditFileButton">
        <span class="circle" onclick="addNewFileRow()">
            <img src="https://ssl.gstatic.com/bt/C3341AA7A1A076756462EE2E5CD71C11/2x/btw_ic_speeddial_white_24dp_2x.png" title="Add new file" alt="Add new file" />
        </span>
        <p>Add document</p>
      </div>
      <div id="fileName">
         <div id="documentFileHeader" class="documentFileHeader mb-1 row hide">
            <div name="documentTitleHeader" class="docTitle col-3 textOverflowEllipsis" display="inline-block">Document title</div>
            <div class="col-7"></div>
         </div>
      </div>
      <div id="submitFormDivButton">
         <button id="submitFormButton" class="greenButton" type="button" onclick="submitFile()">Submit</button>
         <a id="submitFormButton" class="grayButton" type="button" href="${assetURL}">Cancel</a>
      </div>
   </form>
   <br>
   <p> (*): Field required </p>
   <div id="waitingFileUpload"></div>
</div>
<style>
   .blue{
        background-color: #3297FD;
        color:white;
   }
    .white{
        background-color: white;
        color:black;
    }
    .circle {
        display: block;
        position: relative;
        padding: 0;
        z-index: 98;
        box-shadow: 0 6px 10px 0 rgba(0, 0, 0, 0.3);
        border-radius: 50%;
        height: 20px;
        width: 20px;
        background-color: #70ada3;
        text-align: center;
        float: left;
        margin-right: 5px;
    }
    .circle:active {
        transform: scale(0.9);
        box-shadow: 0 2px 15px 0 rgba(0, 0, 0, 0.3);
    }
    .circle:hover {
        cursor: pointer;
        background-color: #70ada3;
        box-shadow: 0 8px 15px 0 rgba(0, 0, 0, 0.3);
    }
    .circle img {
        position: absolute;
        top: 50%;
        left: 50%;
        margin-left: -9px;
        margin-top: -9.5px;
        width: 18px;
        height: 18px;
    }
    .newFileInput {
        margin-left: 12px;
    }
    #fileName {
        width: 90em;
        margin-top: 1em;
        margin-bottom: 70px;
        margin-left: 10px;
    }
    .docTitle {
        width: 10em;
        white-space: nowrap;
        overflow: hidden;
    }
    .docButton {
        height: 20px;
        font-size: 14px;
        background-color: #70ada3;
        color: white;
        padding-bottom: 20px;
        border-radius: 9px;
        border-width: 0px;
    }
    button[name="documentEditBtn"] {
        background: #a5a5a5;
    }
    .textOverflowEllipsis {
        text-overflow: ellipsis;
    }
    .documentFileHeader {
        font-weight: bold;
        text-decoration: underline;
        font-size: 1.15em;
    }
     #select1, #select2 {
    height: 100% !important;
    min-height: 9.5em;
    width: 21em;
    margin-bottom: 2em;
    overflow-y: auto;
    border-width: 3px;
    border-style: groove;
    border-radius: 14px;
   }
   select option[value='emptyOption'] {
    color: grey;
   }
   #linkToCP {
    width: 35em;
   }
    div#submitFormDivButton {
     margin-right: 31px;
    }
    div#submitFormDivButton a {
     padding-top: 7px;
    }
    .newFileInput {
        margin-right: 8px;
        margin-left: 1px;
    }
    button[name='documentCancel'] {
        margin-left: -5px !important;
        background: #a5a5a5;
    }
    @media only screen and (max-width: 1200px) {
        #select1, #select2 {
         width:21em;
        }
    }
    @media only screen and (max-width: 992px) {
        #select1, #select2 {
         width:21em;
        }
        .newFileInput, .documentFileName {
        margin-left: -6px;
        margin-top: 0.75em;
        margin-bottom: 1em;
        }
        button[name='documentCancel'] {
            margin-left: 0.25rem !important;
        }
    }
    @media only screen and (max-width: 768px) {
        #select1, #select2 {
         width: 21em;
        }
    }
     @media only screen and (max-width: 576px) {
        #select1, #select2 {
         width: 100%;
        }
        #linkToCP {
            width:100% !important;
            width: 22em;
        }
        div#submitFormDivButton {
            margin-bottom: 14px;
            margin-right: -30px;
        }
        #fileName {
            margin-left: 12px !important;
            width: 393%;
        }
    }
    .grayButton {
					background: #a5a5a5;
			        border-radius: 5px;
			        color: white;
			        font-size: 16px;
			        font-weight: normal;
			        letter-spacing: 0.25px;
			        height: 36px;
			        border: none;
			        padding: 6px 16px 9px 17px;
				}
</style>
<script>
    var backfileList;
    document.querySelector("#select1 option[value='emptyOption']").selected = true;
    document.querySelector("#select2 option[value='emptyOption']").selected = true;

    var fileArray = [];
    backfileList = document.querySelectorAll(".fileInfoListItem");

    for (var i = 0; i < backfileList.length; i++) {
        var document1 = new Object();
        if (backfileList[i].dataset.name != "") {
            document1.classPK = backfileList[i].dataset.pk;
            document1.name = backfileList[i].dataset.name;
            document1.uuid = backfileList[i].dataset.id;
            document1.doctitle = backfileList[i].dataset.doctitle;

            fileArray.push(document1);
        }else{
            console.log("fileInfoListItem is empty");
        }   
    }
    fileArray.forEach(displayFile);

    $('#select1 option, #select2 option').mousedown(function(e) {
        let evt = e || window.event;
        evt.preventDefault();
        let selectedAtr = evt.target;
        let selectParentElement = selectedAtr.parentNode;
        let selectParentElementId = selectParentElement.id;
        if (!selectedAtr.selected){
            highlight(selectedAtr);
            if($("#"+selectParentElementId).val().includes("emptyOption")) {
                highlightOff(document.querySelector("#"+selectParentElementId+" option[value='emptyOption']"));
            }
        } else {
            highlightOff(selectedAtr);
            if($("#"+selectParentElementId).val().length == 0 || $("#"+selectParentElementId).val()=="emptyOption" ) {
                document.querySelector("#"+selectParentElementId+" option[value='emptyOption']").selected = true;
            }  
        }
        let selectedId2 = selectedAtr.getAttribute('name');
        let selectedId = selectedAtr.getAttribute('value');
        let selectList = document.getElementsByName(selectedId);
        let selectList2 = document.getElementsByName(selectedId2);
        hideSelect(selectList);
    });
    $('#select1, #select2').focus(function(evt) {let oEvt = evt || window.event; oEvt.target.blur();});
    function fileDisplayedListIsEmpty() {
        let fileDisplayedList = document.querySelectorAll("#fileName .documentFile");
        if(fileDisplayedList.length == 0) {
            return true;
        } else {
            return false;
        }
    }
    function highlight(element) {
        element.selected = true;
        element.classList.add("blue"); 
    }
    function highlightOff(element) {
         element.selected = false;
         element.classList.remove("blue");
    }
    function hideSelect(selectList){
        for(selection of selectList) {
            if (selection.classList.contains("hide")) {
                selection.classList.remove('hide');
            } else {
                selection.classList.add("hide");
            }
        }
        document.getElementById("select2").size = document.querySelectorAll("#select2 option:not(.hide)").length;
        if(document.getElementById("select2").size<5) {
            document.getElementById("select2").size = 5;
        }
    }
    function selectElement(id, valueToSelect) {
        let element = document.getElementById(id);
        element.value = valueToSelect;
    }
    <#if AID != 0>
        selectElement('selectType', "${ED_IA_Type}");
        selectElement('selectQtpt', "${ED_IA_ChooseQATemplate}");
        var fileJsonObjects = [];
        var ARTTICLEID = ${AID};
        console.log("ArticleId ->" + ARTTICLEID);
        //var newURL = location.href.split("?")[0];
        //window.history.pushState('object', document.title, newURL);
    </#if>
    <#if ED_IA_TargetedAudience != "">
        selectPrevious("selectedValuesForEdit");
    </#if>
    function selectPrevious(elementId) {
        let elementValues = document.getElementById(elementId).value;
        elementValues = elementValues.replace(/(\r\n|\n|\r)/gm, "");
        let elementValuesArray = elementValues.split(',');
        var arrayLength = elementValuesArray.length;
        for (var i = 0; i < arrayLength; i++) {
            let currentElement = document.getElementById(elementValuesArray[i].trim());
            let currentElementName = currentElement.getAttribute("name");
            let select1Element = document.querySelector("#select1 option[value='"+currentElementName+"']");
            if(!select1Element.selected) {
                triggerMouseEvent(select1Element, "mousedown");
            }
            triggerMouseEvent(currentElement, "mousedown");
        }
    }
    function triggerMouseEvent(node, eventType) {
        var clickEvent = document.createEvent('MouseEvents');
        clickEvent.initEvent(eventType, true, true);
        node.dispatchEvent(clickEvent);
    }
    function addNewFileRow() {
        let filesDiv = window.document.getElementById("fileName");
        let docHtml = '<div class="documentFile mb-3 mb-lg-1 row" data-pk="" data-id="" data-title="" data-name="">';
        docHtml += '<input type="text" name="documentTitle" class="docTitle col-3 textOverflowEllipsis" display="inline-block" value="" onfocusout="titleInputFocusOut(event)" onkeyup="titleInputPressEnter(event)" />';
        docHtml += '<input type="file" name="file" class="newFileInput col-12 col-lg-2" data-updated="false" onChange="fileSelected(this)" />';
        docHtml += '<div class="hidden col-lg-1"></div><button type="button" class="docButton mx-1 col-1" name="documentDeleteBtn" onClick="deleteDisplayedFile()">Delete</button>';
        docHtml += '</div>';
        let htmlElement = new DOMParser().parseFromString(docHtml, "text/html");
        let fileElement = htmlElement.querySelector(".documentFile");
        filesDiv.append(fileElement);
        if(!fileDisplayedListIsEmpty()) {
            document.getElementById("documentFileHeader").classList.remove("hide");
        }
    }
    function fileSelected(element) {
        element.dataset.updated = "true";
        let parent = element.parentElement;
        parent.dataset.name = element.files[0].name;
    }
    function showEditFileUpload(element) {
        let thisButton = element;
        let thisInputFile = element.nextElementSibling;
        let thisFileName = element.previousSibling;
        let cancelButton = thisInputFile.nextElementSibling;
        let deleteButton = cancelButton.nextElementSibling;
        thisButton.classList.toggle("hide");
        thisInputFile.classList.toggle("hide");
        thisFileName.classList.toggle("hide");
        cancelButton.classList.toggle("hide");
        deleteButton.classList.toggle("hide");
    }
    function hideEditFileUpload(element) {
        let cancelButton = element;
        let deleteButton = cancelButton.nextElementSibling;
        let thisInputFile = cancelButton.previousSibling;
        let editButton = thisInputFile.previousSibling;
        let thisFileName = editButton.previousSibling;
        editButton.classList.toggle("hide");
        thisInputFile.classList.toggle("hide");
        thisInputFile.value = "";
        thisInputFile.dataset.updated = "false";
        thisFileName.classList.toggle("hide");
        cancelButton.classList.toggle("hide");
        deleteButton.classList.toggle("hide");
    }
    function submitFile(){
        if ($("#select1").val() == "emptyOption" || $("#select1").val() == "") {
            alert ("Targeted Audience is required field");
        }else if ($("#select2").val() == "emptyOption" || $("#select2").val() == "") {
            alert ("Target Organisation is required field");
        }else if($("#cpId").val() == ""){
            alert ("Change Proposal ID is required");
        }else if ($("#dateFile").val() == ""){
                alert ("Date is required");
        }else if ($("#selectQtpt").val() == "emptyOption" || $("#selectQtpt").val() == "") {
            alert ("Question Template is required field");
        }else{
            submitIA();
        }
    }
    async function submitIA() {
        var fileList = [];
        let alreadySelectedValidNames = [];
        let newFileInputList = window.document.querySelectorAll(".newFileInput[data-updated='true']");
        newFileInputList.forEach(function(fileInput){
            
                let nFiles = fileInput.files;
                const arr = Array.from(nFiles);
                fileList = fileList.concat(arr);
           
        });
        var count = 0;
        if (fileList.length > 0) {
                for (var i = 0; i < fileList.length; i++) {
                    let originalFileName = fileList[i].name;
                    let fileName = await getValidName(fileList[i].name, alreadySelectedValidNames);
                    alreadySelectedValidNames.push(fileName);
                    let fileHtmlElement = newFileInputList[i].parentElement;
                
                    
                    uploadingFile();
                    var mimeTypeFile = fileList[i].type;
                    let byteArray = await fileToByteArray(fileList[i]); 
                    Liferay.Service(
                        '/dlapp/add-file-entry',
                        {
                            repositoryId: ${siteGroupId},
                            folderId: 0,
                            sourceFileName: fileName,
                            mimeType: mimeTypeFile,
                            title: fileName,
                            description: '',
                            changeLog: '',
                            bytes: byteArray
                        },
                        function(obj) {
                            console.log("here is the response when file uploaded" + JSON.stringify(obj));
                            console.log("fileEntryId " + obj.fileEntryId);
                            console.log("uuid " + obj.uuid);
                            let chosenOne = fileHtmlElement;
                            chosenOne.dataset.pk = obj.fileEntryId;
                            chosenOne.dataset.id = obj.uuid;
                            chosenOne.dataset.name = obj.fileName;
                            count = count + 1;
                            if (count == fileList.length) {
                                uploadWebContent();
                            }
                        }
                    );
                }
        } else {
            uploadWebContent();
        }
    }
    function getFileEntry(checkName) {
        return new Promise((resolve, reject) => {
            Liferay.Service(
                '/dlapp/get-file-entry',
                {
                    groupId: ${siteGroupId},
                    folderId: 0,
                    title: checkName
                },
                function(obj) {
                    if (typeof obj.classPK == 'undefined') {
                        resolve(true);
                    } else{
                        resolve(false);
                    }
                }
            );
        })
    }

    async function getValidName(fileName, alreadySelectedValidNames) {
        let validFileName = await getFileEntry(fileName);
        let no = 1;
        let fileNameWithExtension = fileName;
        while(alreadySelectedValidNames.includes(fileNameWithExtension) || !validFileName) {
            let fileNameSplit = fileName.split(".");
            let ext = fileNameSplit.pop();
            let fileNameNoExtension = fileNameSplit.join(".");
            fileNameWithExtension = fileNameNoExtension +"("+no+")."+ext;
            no++;
            validFileName = await getFileEntry(fileNameWithExtension);
            console.log(fileNameWithExtension);
            console.log(validFileName);
        }
        console.log(fileNameWithExtension + " is a valid name");
        return fileNameWithExtension;
    }

    function fileToByteArray(file) {
        return new Promise((resolve, reject) => {
            try {
                let reader = new FileReader();
                let fileByteArray = [];
                reader.readAsArrayBuffer(file);
                reader.onloadend = (evt) => {
                    if (evt.target.readyState == FileReader.DONE) {
                        let arrayBuffer = evt.target.result,
                            array = new Uint8Array(arrayBuffer);
                        for (byte of array) {
                            fileByteArray.push(byte);
                        }
                    }
                    resolve(fileByteArray);
                }
            }
            catch (e) {
                reject(e);
            } 
        })
    }
    function displayFile(fileObj) {
        let filesDiv = window.document.getElementById("fileName");
        let fileId = fileObj.uuid;
        let fileTitle = fileObj.doctitle;
        let fileName = fileObj.name;
        let filePK = fileObj.classPK;
        let docHtml = '<div class="documentFile mb-3 mb-lg-1 row" data-pk="'+filePK+'" data-id="'+fileId+'" data-title="'+fileTitle+'" data-name="'+fileName+'">';
        docHtml += '<input type="text" name="documentTitle" class="docTitle col-3 textOverflowEllipsis" display="inline-block" value="'+fileTitle+'" onfocusout="titleInputFocusOut(event)" onkeyup="titleInputPressEnter(event)" />';
        docHtml += '<div name="documentFileName" class="docTitle col-12 col-lg-2 textOverflowEllipsis" display="inline-block">'+fileName+'</div>';
        docHtml += '<button type="button" class="docButton mx-1 col-1" name="documentEditBtn" onClick="showEditFileUpload(this)">Edit</button>';
        docHtml += '<input type="file" name="file" class="newFileInput hide col-12 col-lg-2" data-updated="false" onChange="fileSelected(this)" />';
        docHtml += '<button type="button" class="docButton mx-1 mx-lg-0 col-1 hide" name="documentCancel" onClick="hideEditFileUpload(this)">Cancel</button>';
        docHtml += '<button type="button" class="docButton mx-1 col-1" name="documentDeleteBtn" onClick="deleteDisplayedFile()">Delete</button>';
        docHtml += '</div>';
        filesDiv.innerHTML += docHtml;
        if(!fileDisplayedListIsEmpty()) {
            document.getElementById("documentFileHeader").classList.remove("hide");
        }
    }
    function uploadingFile() {
        var html = "<p> Uploading File... </p> <img src=https://www.appcoda.com/learnswiftui/images/animation/swiftui-animation-8.gif/>";
        $('#waitingFileUpload').html(html);
    }
    function uploadWebContent() {
        let docFiles = [];
        var IAType = window.document.getElementById("selectType").value;
        var title = window.document.getElementById("iaTitle").value;
        var questionTPT = window.document.getElementById("selectQtpt").value;
        var linkToCP = window.document.getElementById("linkToCP").value;
        var cpId = window.document.getElementById("cpId").value;
        var targetedAudience = window.document.getElementById('select2');
        var date = window.document.getElementById("dateFile").value;
        var selected = [];
        for (var option of targetedAudience.options) {
            if (option.classList.contains('blue')) {
                selected.push(option.value);
            }
        }
        var TargetAU = JSON.stringify(selected);
        let filesDiv = window.document.getElementById("fileName");
        let fileDisplayedList = filesDiv.querySelectorAll(".documentFile");
        if (fileDisplayedList.length != 0) {
            for (var i = 0; i < fileDisplayedList.length; i++) {
                var document1 = new Object();
                document1.classPK = fileDisplayedList[i].dataset.pk;
                document1.groupId = themeDisplay.getScopeGroupId();
                document1.title = fileDisplayedList[i].dataset.name;
                document1.type = "document";
                document1.uuid = fileDisplayedList[i].dataset.id;
                if (fileDisplayedList[i].dataset.title != '') {
                    document1.doctitle = fileDisplayedList[i].dataset.title;
                } else {
                    document1.doctitle = fileDisplayedList[i].dataset.name;
                }
                docFiles.push(document1);
            }
        }
        let docJSON = JSON.stringify(docFiles);
        console.log(docJSON);
        console.log("========================================================");
        <#if appRPK != 0 >
            Liferay.Service(
                '/cproposal.recformarticle/update_IA', {
                    appResourcePK: ${appRPK},
                    Title: title,
                    IA_CPID: cpId,
                    IA_Type: IAType,
                    IA_TargetedAudience: TargetAU,
                    IA_ChooseQATemplate: questionTPT,
                    IA_LinkToCPPage: linkToCP,
                    IA_Documents: docJSON,
                    IA_ResponsesDeadline: date
                },
                function(obj) {
                    deleteFiles();
                    showResponse(obj);
                }
            );
        <#else>
            Liferay.Service(
                '/cproposal.recformarticle/create_IA', {
                    Title: title,
                    IA_CPID: cpId,
                    IA_Type: IAType,
                    IA_TargetedAudience: TargetAU,
                    IA_ChooseQATemplate: questionTPT,
                    IA_LinkToCPPage: linkToCP,
                    IA_Documents: docJSON,
                    IA_ResponsesDeadline: date
                },
                function(obj) {
                    showResponse(obj);
                }
            );
        </#if>
    }
    function deleteFiles() {
        let filesToDelete = window.document.getElementById("filesToDelete");
        let filesToDeleteValue = filesToDelete.value;
        if (filesToDeleteValue != "") {
            filesToDeleteValue.split(",").forEach(function (item) {
                console.log("fileEntryId to delete: " + item);
                Liferay.Service(
                    '/dlapp/delete-file-entry',
                    {
                        fileEntryId: item
                    },
                    function(obj) {
                        console.log(obj);
                    }
                );
            });
        }
    }
    function showResponse(obj) {
        if (obj != undefined) {
            console.log(obj.code);
            console.log(obj.message);
            var goBack = "${assetURL}";
            if (obj.code == 500) {
                $('titleAssuranceDiv').addClass('hide');
                var html = "<p> Your file has not been uploaded </p>"
                html += "<p>Reason: " + obj.message + "</p> <div><p><a class="+"greenButton" +" href=" + goBack +">Back</a></p></div>";
            } else if (obj.code == 200) {
                var html = "<p>" + obj.message + "</p> <div><p><a class="+"greenButton" +" href=" + goBack +">Back</a></p></div>";
            } else {
                var html = "<p>" + obj.message + "</p> <div><p><a class="+"greenButton" +" href=" + goBack +">Back</a></p></div>";
            }
        } else {
            var html = "<p> There was an error with your file </p> <div><p><a class="+"greenButton" +" href=" + goBack +">Back</a></p></div>";
        }
        $('#upload-file-form-pa').html(html);
    }
    function titleInputFocusOut(oEvent) {
       let oE = oEvent || window.event;
       let target = oE.currentTarget;
       let fileElement = target.parentElement;
       let titleElement = fileElement.querySelector("[name='documentTitle']");
       let oldTitle = fileElement.dataset.title;
       let newTitle = titleElement.value;
        if(newTitle != "" && !fileIsDisplayed(newTitle)) {    
            fileElement.dataset.title = titleElement.value;
            titleElement.value = newTitle;
        } else {
            titleElement.value = oldTitle;
        }
        titleElement.remove();
        fileElement.prepend(titleElement);
   }
    function fileIsDisplayed(newfileName) {
        let filesDiv = window.document.getElementById("fileName");
        let fileDisplayedList = filesDiv.querySelectorAll(".documentFile");
        let result = false;
        fileDisplayedList.forEach(function(x){
            if(x.dataset.title.toLowerCase()==newfileName.toLowerCase() || x.dataset.name.toLowerCase()==newfileName.toLowerCase()) {
                result = true;
            }
        });
        return result;
    }
    function deleteDisplayedFile(oEvent) {
        let oE = oEvent || window.event;
        let filesDiv = window.document.getElementById("fileName");
        let target = oE.currentTarget;
        let fileElement = target.parentElement;
        let fileId = fileElement.dataset.id;
        let fileTitle = fileElement.dataset.title;
        let fileName = fileElement.dataset.name;
        let filePK = fileElement.dataset.pk;
        fileElement.remove();
        let filesToDelete = window.document.getElementById("filesToDelete");
        let filesToDeletevalue = filesToDelete.value;
        if (filesToDeletevalue != "") {
            filesToDelete.value = filesToDeletevalue + "," + filePK;
        } else {
            filesToDelete.value = filePK;
        }
        if(fileDisplayedListIsEmpty()) {
            document.getElementById("documentFileHeader").classList.add("hide");
        }
    }
    function titleInputPressEnter(oEvent) {  //Nested into editDisplayedFile
        let oE = oEvent || window.event;
        if (oE.key === "Enter") {
            oE.preventDefault();
            let target = oE.currentTarget;
            let fileElement = target.parentElement;
            let titleElement = fileElement.querySelector("[name='documentTitle']");
            titleElement.remove();
            fileElement.prepend(titleElement);
        }
    }
</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.5.3/jspdf.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf-autotable/3.5.6/jspdf.plugin.autotable.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf-autotable/3.5.6/jspdf.plugin.autotable.min.js"></script>