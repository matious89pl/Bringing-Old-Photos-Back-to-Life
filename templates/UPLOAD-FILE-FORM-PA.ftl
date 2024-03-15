<!-- PA. Form upload file -->
<#assign siteGroupId = themeDisplay.getLayout().getGroupId() />
<#assign groupService = utilLocator.findUtil("com.liferay.portal.kernel.service.GroupLocalService") />
<#assign id = themeDisplay.getSiteGroupId() />
<#assign org = groupService.getGroup(id) />
<#assign orgName = org.getName("en_GB") />
<#assign companyId= themeDisplay.getCompanyId() />    
<#assign userId = themeDisplay.getUserId() />
<div id="upload-file-form-pa">
<div id="titleAssuranceDiv">
    <h2 id="titleAssurance">Upload Performance Assurance files</h2>
    <p id="titleAssuranceNote"> For guidance on Performance Assurance data upload please read the User Guide found <a target="_blank" href="${themeDisplay.getPortalURL()}/documents/37141/0/Performance+Assurance+Overview+of+Data+Processing.pdf/3a7cf560-0884-e987-856f-742d7cc2862c?t=1636534717403">here</a>.</p> 
</div>
    <form id="fileuploadForm">       
        <div>
            <p><label for="processArea">Process Area *</label>
            <select name="select1" id="select1" required>
                <option value="emptyOption">Please select...</option>
                <option value="CodeManagerUploads">Code Manager Uploads</option>
                <option value="EnergyTheft">Energy Theft</option>
                <option value="GreenDeals">Green Deals</option>
                <option value="MarketEntry">Market Entry</option>
                <option value="Metering">Metering</option>
                <option value="PerformanceAssuranceDataUpload">Performance Assurance - Data Upload</option>
                <option value="ServiceProviders">Service Providers</option>
                <option value="SmartMeteringInstallationInformation">Smart Metering Installation Information</option>
            </select></p>
        </div>
        <div> 
            <p><label for="anualMaintenanceOptions">File Type *</label>
            <select name="select2" id="select2" required>
                <option id="emptySelection" value="emptyOption"> Please select... </option>
                <#--Code Manager Uploads-->
                <option value="Code Manager GDAA Fails Report" name="CodeManagerUploads">Code Manager GDAA Fails Report</option>
                <option value="Code Manager GDAA Summary Report" name="CodeManagerUploads">Code Manager GDAA Summary Report</option>
                <option value="Code Manager SMIS Reporting" name="CodeManagerUploads">Code Manager SMIS Reporting</option>
                <option value="Code Manager Theft Summary Report" name="CodeManagerUploads">Code Manager Theft Summary Report</option>
                <option value="Data Cleanse Individual Supplier Reports" name="CodeManagerUploads">Data Cleanse Individual Supplier Reports</option>
                <option value="Data Cleanse Anon Movement Report" name="CodeManagerUploads">Data Cleanse Anon Movement Report</option>
                <option value="Other" name="CodeManagerUploads">Other</option>
                <#--Energy Theft-->
                <option value="1340 Residential Fixed Width" name="EnergyTheft">1340 Residential Fixed Width</option>
                <option value="1340 Residential Delimited" name="EnergyTheft">1340 Residential Delimited</option>
                <option value="1340 Commercial Delimited" name="EnergyTheft">1340 Commercial Delimited</option>
                <option value="1340 Commercial Fixed Width" name="EnergyTheft">1340 Commercial Fixed Width</option>
                <option value="Other" name="EnergyTheft">Other</option>
                <#--Green Deals-->
                <option value="1170 Penetration Testing Report" name="GreenDeals">1170 Penetration Testing Report</option>
                <option value="1180 Business Continuity Report" name="GreenDeals">1180 Business Continuity Report</option>
                <option value="Other" name="GreenDeals">Other</option>
                <#--Market Entry-->
                <option value="Distributor CME Report" name="MarketEntry">Distributor CME Report</option>
                <option value="Supplier HH CME Report" name="MarketEntry">Supplier HH CME Report</option>
                <option value="Supplier NHH CME Report" name="MarketEntry">Supplier NHH CME Report</option>
                <option value="Other" name="MarketEntry">Other</option>
                <#--Metering-->
                <option value="1460 ASPCOP Auditor Reports" name="Metering">1460 ASPCOP Auditor Reports</option>
                <option value="1450 MCoP Auditor Reports" name="Metering">1450 MCoP Auditor Reports</option>
                <option value="Metering Accreditation Application Form - MCoP" name="Metering">Metering Accreditation Application Form - MCoP</option>
                <option value="Metering Accreditation Application Form - MOCoP" name="Metering">Metering Accreditation Application Form - MOCoP</option>
                <option value="1480 MOCoP Auditor Reports" name="Metering">1480 MOCoP Auditor Reports</option>
                <option value="1490 Smart Metering Audit Reports" name="Metering">1490 Smart Metering Audit Reports</option>
                <option value="Metering Accreditation Application Form - ASPCoP" name="Metering">Metering Accreditation Application Form - ASPCoP</option>
                <option value="Other" name="Metering">Other</option>
                <#--Performance Assurance - Data Upload-->
                <option value="90 Network Operator Complaints Data" name="PerformanceAssuranceDataUpload">90 Network Operator Complaints Data</option>
                <option value="91 GDN Complaints Data" name="PerformanceAssuranceDataUpload">91 GDN Complaints Data</option>
                <option value="100 Supplier Complaints Data - Domestic" name="PerformanceAssuranceDataUpload">100 Supplier Complaints Data - Domestic</option>
                <option value="100 Supplier Complaints Data - Microbusiness" name="PerformanceAssuranceDataUpload">100 Supplier Complaints Data - Microbusiness</option>
                <option value="101 Monthly Supplier Aggregated Complaints Data - Domestic" name="PerformanceAssuranceDataUpload">101 Monthly Supplier Aggregated Complaints Data - Domestic</option>
                <option value="101 Monthly Supplier Aggregated Complaints Data - Microbusiness" name="PerformanceAssuranceDataUpload">101 Monthly Supplier Aggregated Complaints Data - Microbusiness</option>
                <option value="101 Quarterly Supplier Aggregated Complaints Data - Domestic" name="PerformanceAssuranceDataUpload">101 Quarterly Supplier Aggregated Complaints Data - Domestic</option>
                <option value="101 Quarterly Supplier Aggregated Complaints Data - Microbusiness" name="PerformanceAssuranceDataUpload">101 Quarterly Supplier Aggregated Complaints Data - Microbusiness</option>
                <option value="610 Content provided within PP01" name="PerformanceAssuranceDataUpload">610 Content provided within PP01</option>
                <option value="620 Content provided within PP05" name="PerformanceAssuranceDataUpload">620 Content provided within PP05</option>
                <option value="630 Content provided within PP08" name="PerformanceAssuranceDataUpload">630 Content provided within PP08</option>
                <option value="640 Content provided within PP09" name="PerformanceAssuranceDataUpload">640 Content provided within PP09</option>
                <option value="660 Content provided within RGMA flow - ONAGE" name="PerformanceAssuranceDataUpload">660 Content provided within RGMA flow - ONAGE</option>
                <option value="670 Content provided within RGMA flow - ONDET" name="PerformanceAssuranceDataUpload">670 Content provided within RGMA flow - ONDET</option>
                <option value="680 Content provided within RGMA flow - ONJOB" name="PerformanceAssuranceDataUpload">680 Content provided within RGMA flow - ONJOB</option>
                <option value="690 Content provided within RGMA flow - ONUPD" name="PerformanceAssuranceDataUpload">690 Content provided within RGMA flow - ONUPD</option>
                <option value="700 Content provided within RGMA flow - ORDET" name="PerformanceAssuranceDataUpload">700 Content provided within RGMA flow - ORDET</option>
                <option value="710 Content provided within RGMA flow - ORJOB" name="PerformanceAssuranceDataUpload">710 Content provided within RGMA flow - ORJOB</option>
                <option value="720 Content provided within RGMA flow - RNAGE" name="PerformanceAssuranceDataUpload">720 Content provided within RGMA flow - RNAGE</option>
                <option value="730 Content provided within RGMA flow - RNDET" name="PerformanceAssuranceDataUpload">730 Content provided within RGMA flow - RNDET</option>
                <option value="740 Content provided within RGMA flow - RNJOB" name="PerformanceAssuranceDataUpload">740 Content provided within RGMA flow - RNJOB</option>
                <option value="750 Content provided within RGMA flow - RNUPD" name="PerformanceAssuranceDataUpload">750 Content provided within RGMA flow - RNUPD</option>
                <option value="760 Content provided within RGMA flow - RRDET" name="PerformanceAssuranceDataUpload">760 Content provided within RGMA flow - RRDET</option>
                <option value="770 Content provided within RGMA flow - RRJOB" name="PerformanceAssuranceDataUpload">770 Content provided within RGMA flow - RRJOB</option>
                <option value="850 PPMIP List of Registered Meters" name="PerformanceAssuranceDataUpload">850 PPMIP List of Registered Meters</option>
                <option value="1240 PPMIP Assurance Report" name="PerformanceAssuranceDataUpload">1240 PPMIP Assurance Report</option>
                <option value="1260 PPMIP Multiple Supplier Report" name="PerformanceAssuranceDataUpload">1260 PPMIP Multiple Supplier Report</option>
                <option value="1270 PPMIP Redirected To Transaction Report" name="PerformanceAssuranceDataUpload">1270 PPMIP Redirected To Transaction Report</option>
                <option value="1280 PPMIP Redirected Away Transaction Report" name="PerformanceAssuranceDataUpload">1280 PPMIP Redirected Away Transaction Report</option>
                <option value="1290 PPMIP Routing Method Report" name="PerformanceAssuranceDataUpload">1290 PPMIP Routing Method Report</option>
                <option value="1300 PPMIP Unallocated Transaction Report - Closed Cash Report" name="PerformanceAssuranceDataUpload">1300 PPMIP Unallocated Transaction Report - Closed Cash Report</option>
                <option value="1310 PPMIP Unallocated Transaction Report - Gap Cash Report" name="PerformanceAssuranceDataUpload">1310 PPMIP Unallocated Transaction Report - Gap Cash Report</option>
                <option value="1315 PPMIP Unallocated Transaction Report - Electricity" name="PerformanceAssuranceDataUpload">1315 PPMIP Unallocated Transaction Report - Electricity</option>
                <option value="1360 Fixed balance UTRN Notification - SMETS1 Meter" name="PerformanceAssuranceDataUpload">1360 Fixed balance UTRN Notification - SMETS1 Meter</option>
                <option value="1380 SMETS1 Monthly Transaction Data" name="PerformanceAssuranceDataUpload">1380 SMETS1 Monthly Transaction Data</option>
                <option value="1430 Gaining Supplier Switching Data" name="PerformanceAssuranceDataUpload">1430 Gaining Supplier Switching Data</option>
                <option value="1431 Gaining Supplier Switching Data" name="PerformanceAssuranceDataUpload">1431 Gaining Supplier Switching Data</option>
                <option value="1440 Losing Supplier Switching Data" name="PerformanceAssuranceDataUpload">1440 Losing Supplier Switching Data</option>
                <option value="1441 Losing Supplier Switching Data" name="PerformanceAssuranceDataUpload">1441 Losing Supplier Switching Data</option>
                <option value="1810 Data Cleanse Exemption Report" name="PerformanceAssuranceDataUpload">1810 Data Cleanse Exemption Report</option>
                <option value="Other" name="PerformanceAssuranceDataUpload">Other</option>
                <#--Service Providers-->
                <option value="REC Portal Usage Data" name="ServiceProviders">20 REC Portal Usage Data<option>               
                <option value="30 Code Manager Qualitative SLA Reporting" name="ServiceProviders">30 Code Manager Qualitative SLA Reporting<option>
                <option value="31 Code Manager Quantitative SLA Reporting" name="ServiceProviders">31 Code Manager Quantitative SLA Reporting<option>
                <option value="32 EES SLA Reporting" name="ServiceProviders">32 EES SLA Reporting<option>
                <option value="33 GDCC SLA Reporting" name="ServiceProviders">33 GDCC SLA Reporting<option>
                <option value="34 SDES SLA Reporting" name="ServiceProviders">34 SDES SLA Reporting<option>
                <option value="35 REC GES SLA Reporting" name="ServiceProviders">35 REC GES SLA Reporting<option>
                <option value="36 REC ERDS SLA Reporting" name="ServiceProviders">36 REC ERDS SLA Reporting<option>
                <option value="37 REC GRDS SLA Reporting" name="ServiceProviders">37 REC GRDS SLA Reporting<option>
                <option value="38 REC CSS SLA Reporting" name="ServiceProviders">38 REC CSS SLA Reporting<option>
                <option value="60 Elexon TAA Report" name="ServiceProviders">60 Elexon TAA Report<option>
                <option value="840 Meter Asset Data Snapshot - ECOES" name="ServiceProviders">840 Meter Asset Data Snapshot - ECOES<option>
                <option value="870 ECOES Address Data Quality Report" name="ServiceProviders">870 ECOES Address Data Quality Report<option>
                <option value="880 EES Monthly Usage Report" name="ServiceProviders">880 EES Monthly Usage Report<option>
                <option value="890 EES Supplier Reports Relating to NDCs" name="ServiceProviders">890 EES Supplier Reports Relating to NDCs<option>
                <option value="900 EES User Reports - Daily by User Report" name="ServiceProviders">900 EES User Reports - Daily by User Report<option>
                <option value="910 EES User Reports - Daily by Supplier Report" name="ServiceProviders">910 EES User Reports - Daily by Supplier Report<option>
                <option value="920 EES User Reports - Total by User Report" name="ServiceProviders">920 EES User Reports - Total by User Report<option>
                <option value="930 EES User Reports - Total by Supplier Reports" name="ServiceProviders">930 EES User Reports - Total by Supplier Reports<option>
                <option value="940 EES User Reports - User Detail Report" name="ServiceProviders">940 EES User Reports - User Detail Report<option>
                <option value="950 EES User Reports - User Summary Report" name="ServiceProviders">950 EES User Reports - User Summary Report<option>
                <option value="960 EES User Reports - Deletion Report" name="ServiceProviders">960 EES User Reports - Deletion Report<option>
                <option value="1230 EES MPAN Limit Breach Report" name="ServiceProviders">1230 EES MPAN Limit Breach Report<option>
                <option value="Other" name="ServiceProviders">Other</option>
                <#--Smart Metering Installation Information-->
                <option value="1330 SMIS Survey Data - Domestic"  name="SmartMeteringInstallationInformation">1330 SMIS Survey Data - Domestic<option>
                <option value="1330 SMIS Survey Data - Microbusiness"  name="SmartMeteringInstallationInformation">1330 SMIS Survey Data - Microbusiness<option>
                <option value="Other" name="SmartMeteringInstallationInformation">Other</option>
            </select></p>
        </div>
        <div><p>Description <input type="text" name="" id="files_form_description"></p></div>
        <div id="assurancePeriodDiv">
            <p id="assurancePeriod"> Assurance Period * <input id="dateFile" type="date" name="" value="" required></p> 
            <p id="assuranceNote"> This date selected should be the last date of Assurance Period, i.e. if it's a monthly report for September the date should be 30/09, if it's a quarterly report (Q4) the date should be 31/12. </p>
        </div>
        <div class="hide"><p>Party Uploading * <input id="partyUploading" type="text" name="" value="${orgName}" required></p></div>
        <div id="uploadFileButton">
            <label for="uploadedFile"><p>Select a file<input type="file" id="uploadedFile" name="uploadedFile" required></p> </label>
            <div id="fileName"></div>
        </div>
        <div id="submitFormDivButton">
           <button id="submitFormButton" class="greenButton" type="button" onclick="submitFile()">Submit</button>
        </div>
    </form>
    <br>
    <p> (*): Field required </p>
    <div id="waitingFileUpload"></div>
</div>
<script>
    // Select dropdown options
    $('#select2').attr("disabled", "disabled");   
    $("#select1").change(function() {
        $('#select2').removeAttr("disabled");
      if ($(this).data('options') === undefined) {
        $(this).data('options', $('#select2 option').clone());
      }
      var id = $(this).val();
      var options = $(this).data('options').filter('[name=' + id + ']');
      $('#select2').html(options);
    });
    var subService = $("#select-service-right option:selected").text();
    if(subService == "Please select..."){
        subService = "";
    }
    //Show the file name
    $("#uploadedFile").change(function() {
        var html = document.getElementById('uploadedFile').files[0].name;
        $('#fileName').html(html);
    });
    function submitFile(){
        console.log("submit");
        if ($("#select1").val() == "emptyOption" || $("#select1").val() == "") {
            alert ("Process Area is required fields");
        }else if ($("#select2").val() == "emptyOption" || $("#select2").val() == "") {
            alert ("Type Document is required fields");
        }else if ($("#uploadedFile").val() == ""){
                alert ("Upload file is required");
        } else if ($("#dateFile").val() == ""){
                alert ("Date is required");
        }else if ($("#partyUploading").val() == ""){
                alert ("Party Uploading filed is required");
        } else {
            getByteArray();
            Analytics.track("Submit Files Orgs", {});
        }     
    }

   function checkFileExtention() {
        let myFile = document.getElementById('uploadedFile').files[0];
        let fileName = myFile.name;
        let ext = fileName.substr(fileName.lastIndexOf('.') + 1);
        //alert(ext)
        let mimeTypeFile = myFile.type;
        console.log(mimeTypeFile);
        //alert(mimeTypeFile);
        var csv = [
            "1340 Residential Delimited",
            "1340 Commercial Delimited",
            "Distributor CME Report",
            "Supplier HH CME Report",
            "Supplier NHH CME Report",
            "90 Network Operator Complaints Data",
            "91 GDN Complaints Data",
            "100 Supplier Complaints Data - Domestic",
            "100 Supplier Complaints Data - Microbusiness",
            "101 Monthly Supplier Aggregated Complaints Data - Domestic",
            "101 Monthly Supplier Aggregated Complaints Data - Microbusiness",
            "101 Quarterly Supplier Aggregated Complaints Data - Domestic",
            "101 Quarterly Supplier Aggregated Complaints Data - Microbusiness",
            "610 Content provided within PP01",
            "620 Content provided within PP05",
            "630 Content provided within PP08",
            "640 Content provided within PP09",
            "660 Content provided within RGMA flow - ONAGE",
            "670 Content provided within RGMA flow - ONDET",
            "680 Content provided within RGMA flow - ONJOB",
            "690 Content provided within RGMA flow - ONUPD",
            "700 Content provided within RGMA flow - ORDET",
            "710 Content provided within RGMA flow - ORJOB",
            "720 Content provided within RGMA flow - RNAGE",
            "730 Content provided within RGMA flow - RNDET",
            "740 Content provided within RGMA flow - RNJOB",
            "750 Content provided within RGMA flow - RNUPD",
            "760 Content provided within RGMA flow - RRDET",
            "770 Content provided within RGMA flow - RRJOB",
            "850 PPMIP List of Registered Meters",
            "1240 PPMIP Assurance Report",
            "1260 PPMIP Multiple Supplier Report",
            "1270 PPMIP Redirected To Transaction Report",
            "1280 PPMIP Redirected Away Transaction Report",
            "1290 PPMIP Routing Method Report",
            "1300 PPMIP Unallocated Transaction Report - Closed Cash Report",
            "1310 PPMIP Unallocated Transaction Report - Gap Cash Report",
            "1315 PPMIP Unallocated Transaction Report - Electricity",
            "1360 Fixed balance UTRN Notification - SMETS1 Meter",
            "1380 SMETS1 Monthly Transaction Data",
            "1430 Gaining Supplier Switching Data",
            "1431 Gaining Supplier Switching Data",
            "1440 Losing Supplier Switching Data",
            "1441 Losing Supplier Switching Data",
            "1810 Data Cleanse Exemption Report",
            "20 REC Portal Usage Data",
            "30 Code Manager Qualitative SLA Reporting",
            "31 Code Manager Quantitative SLA Reporting",
            "32 EES SLA Reporting",
            "33 GDCC SLA Reporting",
            "34 SDES SLA Reporting",
            "35 REC GES SLA Reporting",
            "36 REC ERDS SLA Reporting",
            "37 REC GRDS SLA Reporting",
            "38 REC CSS SLA Reporting",
            "60 Elexon TAA Report",
            "840 Meter Asset Data Snapshot - ECOES",
            "870 ECOES Address Data Quality Report",
            "880 EES Monthly Usage Report",
            "890 EES Supplier Reports Relating to NDCs",
            "900 EES User Reports - Daily by User Report",
            "910 EES User Reports - Daily by Supplier Report",
            "920 EES User Reports - Total by User Report",
            "930 EES User Reports - Total by Supplier Reports",
            "940 EES User Reports - User Detail Report",
            "950 EES User Reports - User Summary Report",
            "960 EES User Reports - Deletion Report",
            "1230 EES MPAN Limit Breach Report",
            "Other",
            "1170 Penetration Testing Report",
            "1180 Business Continuity Report",
            "Other",
            "Other",
            "1460 ASPCOP Auditor Reports",
            "1450 MCoP Auditor Reports",
            "Metering Accreditation Application Form - MCoP",
            "Metering Accreditation Application Form - MOCoP",
            "1480 MOCoP Auditor Reports",
            "1490 Smart Metering Audit Reports",
            "Metering Accreditation Application Form - ASPCoP",
            "Other",
            "Other",
            "Other",
            "Other",
            "Code Manager SMIS Reporting",
            "Code Manager GDAA Summary Report",
            "Code Manager GDAA Fails Report",
            "Code Manager Theft Summary Report",
            "Data Cleanse Individual Supplier Reports",
            "Data Cleanse Anon Movement Report",
            "Other"
            ];
        var xlsx = [
            "60 Elexon TAA Report",
            "101 Monthly Supplier Aggregated Complaints Data - Domestic",
            "101 Monthly Supplier Aggregated Complaints Data - Microbusiness",
            "101 Quarterly Supplier Aggregated Complaints Data - Domestic",
            "101 Quarterly Supplier Aggregated Complaints Data - Microbusiness",
            "870 ECOES Address Data Quality Report",
            "1330 SMIS Survey Data - Domestic",
            "1330 SMIS Survey Data - Microbusiness",
            "Other",
            "1170 Penetration Testing Report",
            "1180 Business Continuity Report",
            "Other",
            "Other",
            "1460 ASPCOP Auditor Reports",
            "1450 MCoP Auditor Reports",
            "Metering Accreditation Application Form - MCoP",
            "Metering Accreditation Application Form - MOCoP",
            "1480 MOCoP Auditor Reports",
            "1490 Smart Metering Audit Reports",
            "Metering Accreditation Application Form - ASPCoP",
            "Other",
            "Other",
            "Other",
            "Other",
            "Code Manager SMIS Reporting",
            "Code Manager GDAA Summary Report",
            "Code Manager GDAA Fails Report",
            "Code Manager Theft Summary Report",
            "Data Cleanse Individual Supplier Reports",
            "Data Cleanse Anon Movement Report",
            "Other"
           ];
        var pip = [
            "60 Elexon TAA Report",
            "1340 Residential Delimited",
            "1340 Commercial Delimited",
            "Other",
            "1170 Penetration Testing Report",
            "1180 Business Continuity Report",
            "Other",
            "Other",
            "1460 ASPCOP Auditor Reports",
            "1450 MCoP Auditor Reports",
            "Metering Accreditation Application Form - MCoP",
            "Metering Accreditation Application Form - MOCoP",
            "1480 MOCoP Auditor Reports",
            "1490 Smart Metering Audit Reports",
            "Metering Accreditation Application Form - ASPCoP",
            "Other",
            "Other",
            "Other",
            "Other",
            "Code Manager SMIS Reporting",
            "Code Manager GDAA Summary Report",
            "Code Manager GDAA Fails Report",
            "Code Manager Theft Summary Report",
            "Data Cleanse Individual Supplier Reports",
            "Data Cleanse Anon Movement Report",
            "Other"
            ];
        var til = [
            "60 Elexon TAA Report",
            "1340 Residential Delimited",
            "1340 Commercial Delimited",
            "Other",
            "1170 Penetration Testing Report",
            "1180 Business Continuity Report",
            "Other",
            "Other",
            "1460 ASPCOP Auditor Reports",
            "1450 MCoP Auditor Reports",
            "Metering Accreditation Application Form - MCoP",
            "Metering Accreditation Application Form - MOCoP",
            "1480 MOCoP Auditor Reports",
            "1490 Smart Metering Audit Reports",
            "Metering Accreditation Application Form - ASPCoP",
            "Other",
            "Other",
            "Other",
            "Other",
            "Code Manager SMIS Reporting",
            "Code Manager GDAA Summary Report",
            "Code Manager GDAA Fails Report",
            "Code Manager Theft Summary Report",
            "Data Cleanse Individual Supplier Reports",
            "Data Cleanse Anon Movement Report",
            "Other"
            ];
        var tab = [
            "60 Elexon TAA Report",
            "1340 Residential Delimited",
            "1340 Commercial Delimited",
            "Other",
            "1170 Penetration Testing Report",
            "1180 Business Continuity Report",
            "Other",
            "Other",
            "1460 ASPCOP Auditor Reports",
            "1450 MCoP Auditor Reports",
            "Metering Accreditation Application Form - MCoP",
            "Metering Accreditation Application Form - MOCoP",
            "1480 MOCoP Auditor Reports",
            "1490 Smart Metering Audit Reports",
            "Metering Accreditation Application Form - ASPCoP",
            "Other",
            "Other",
            "Other",
            "Other",
            "Code Manager SMIS Reporting",
            "Code Manager GDAA Summary Report",
            "Code Manager GDAA Fails Report",
            "Code Manager Theft Summary Report",
            "Data Cleanse Individual Supplier Reports",
            "Data Cleanse Anon Movement Report",
            "Other"
            ];
        var dat = [
            "60 Elexon TAA Report",
            "1340 Residential Fixed Width",
            "1340 Commercial Fixed Width",
            "Other",
            "1170 Penetration Testing Report",
            "1180 Business Continuity Report",
            "Other",
            "Other",
            "1460 ASPCOP Auditor Reports",
            "1450 MCoP Auditor Reports",
            "Metering Accreditation Application Form - MCoP",
            "Metering Accreditation Application Form - MOCoP",
            "1480 MOCoP Auditor Reports",
            "1490 Smart Metering Audit Reports",
            "Metering Accreditation Application Form - ASPCoP",
            "Other",
            "Other",
            "Other",
            "Other",
            "Code Manager SMIS Reporting",
            "Code Manager GDAA Summary Report",
            "Code Manager GDAA Fails Report",
            "Code Manager Theft Summary Report",
            "Data Cleanse Individual Supplier Reports",
            "Data Cleanse Anon Movement Report",
            "Other"
            ];
        var txt = [
            "60 Elexon TAA Report",
            "90 Network Operator Complaints Data",
            "91 GDN Complaints Data",
            "100 Supplier Complaints Data - Domestic",
            "100 Supplier Complaints Data - Microbusiness",
            "610 Content provided within PP01",
            "620 Content provided within PP05",
            "630 Content provided within PP08",
            "640 Content provided within PP09",
            "660 Content provided within RGMA flow - ONAGE",
            "670 Content provided within RGMA flow - ONDET",
            "680 Content provided within RGMA flow - ONJOB",
            "690 Content provided within RGMA flow - ONUPD",
            "700 Content provided within RGMA flow - ORDET",
            "710 Content provided within RGMA flow - ORJOB",
            "720 Content provided within RGMA flow - RNAGE",
            "730 Content provided within RGMA flow - RNDET",
            "740 Content provided within RGMA flow - RNJOB",
            "750 Content provided within RGMA flow - RNUPD",
            "760 Content provided within RGMA flow - RRDET",
            "770 Content provided within RGMA flow - RRJOB",
            "850 PPMIP List of Registered Meters",
            "1240 PPMIP Assurance Report",
            "1260 PPMIP Multiple Supplier Report",
            "1270 PPMIP Redirected To Transaction Report",
            "1280 PPMIP Redirected Away Transaction Report",
            "1290 PPMIP Routing Method Report",
            "1300 PPMIP Unallocated Transaction Report - Closed Cash Report",
            "1310 PPMIP Unallocated Transaction Report - Gap Cash Report",
            "1315 PPMIP Unallocated Transaction Report - Electricity",
            "1360 Fixed balance UTRN Notification - SMETS1 Meter",
            "1380 SMETS1 Monthly Transaction Data",
            "1430 Gaining Supplier Switching Data",
            "1431 Gaining Supplier Switching Data",
            "1440 Losing Supplier Switching Data",
            "1441 Losing Supplier Switching Data",
            "1810 Data Cleanse Exemption Report",
            "840 Meter Asset Data Snapshot - ECOES",
            "880 EES Monthly Usage Report",
            "890 EES Supplier Reports Relating to NDCs",
            "900 EES User Reports - Daily by User Report",
            "910 EES User Reports - Daily by Supplier Report",
            "920 EES User Reports - Total by User Report",
            "930 EES User Reports - Total by Supplier Reports",
            "940 EES User Reports - User Detail Report",
            "950 EES User Reports - User Summary Report",
            "960 EES User Reports - Deletion Report",
            "1230 EES MPAN Limit Breach Report",
            "Other",
            "1170 Penetration Testing Report",
            "1180 Business Continuity Report",
            "Other",
            "Other",
            "1460 ASPCOP Auditor Reports",
            "1450 MCoP Auditor Reports",
            "Metering Accreditation Application Form - MCoP",
            "Metering Accreditation Application Form - MOCoP",
            "1480 MOCoP Auditor Reports",
            "1490 Smart Metering Audit Reports",
            "Metering Accreditation Application Form - ASPCoP",
            "Other",
            "Other",
            "Other",
            "Other",
            "Code Manager SMIS Reporting",
            "Code Manager GDAA Summary Report",
            "Code Manager GDAA Fails Report",
            "Code Manager Theft Summary Report",
            "Data Cleanse Individual Supplier Reports",
            "Data Cleanse Anon Movement Report",
            "Other"
            ];
        var notApplicable = [
            "60 Elexon TAA Report",
            "1170 Penetration Testing Report",
            "1180 Business Continuity Report",
            "1460 ASPCOP Auditor Reports",
            "1450 MCoP Auditor Reports",
            "Metering Accreditation Application Form - MCoP",
            "Metering Accreditation Application Form - MOCoP",
            "1480 MOCoP Auditor Reports",
            "1490 Smart Metering Audit Reports",
            "Metering Accreditation Application Form - ASPCoP",
            "Code Manager SMIS Reporting",
            "Code Manager GDAA Summary Report",
            "Code Manager GDAA Fails Report",
            "Code Manager Theft Summary Report",
            "Data Cleanse Individual Supplier Reports",
            "Data Cleanse Anon Movement Report",
            "Other"
            ];
        var zip = [
            "100 Supplier Complaints Data - Domestic",
            "100 Supplier Complaints Data - Microbusiness",
            "101 Monthly Supplier Aggregated Complaints Data - Domestic",
            "101 Monthly Supplier Aggregated Complaints Data - Microbusiness",
            "101 Quarterly Supplier Aggregated Complaints Data - Domestic",
            "101 Quarterly Supplier Aggregated Complaints Data - Microbusiness",
            "1170 Penetration Testing Report",
            "1180 Business Continuity Report",
            "1230 EES MPAN Limit Breach Report",
            "1240 PPMIP Assurance Report",
            "1260 PPMIP Multiple Supplier Report",
            "1270 PPMIP Redirected To Transaction Report",
            "1280 PPMIP Redirected Away Transaction Report",
            "1290 PPMIP Routing Method Report",
            "1300 PPMIP Unallocated Transaction Report - Closed Cash Report",
            "1310 PPMIP Unallocated Transaction Report - Gap Cash Report",
            "1315 PPMIP Unallocated Transaction Report - Electricity",
            "1330 SMIS Survey Data - Domestic",
            "1330 SMIS Survey Data - Microbusiness",
            "1340 Commercial Delimited",
            "1340 Commercial Fixed Width",
            "1340 Residential Delimited",
            "1340 Residential Fixed Width",
            "1360 Fixed balance UTRN Notification - SMETS1 Meter",
            "1380 SMETS1 Monthly Transaction Data",
            "1430 Gaining Supplier Switching Data",
            "1431 Gaining Supplier Switching Data",
            "1440 Losing Supplier Switching Data",
            "1441 Losing Supplier Switching Data",
            "1450 MCoP Auditor Reports",
            "1460 ASPCOP Auditor Reports",
            "1480 MOCoP Auditor Reports",
            "1490 Smart Metering Audit Reports",
            "1810 Data Cleanse Exemption Report",
            "20 REC Portal Usage Data",
            "30 Code Manager Qualitative SLA Reporting",
            "31 Code Manager Quantitative SLA Reporting",
            "32 EES SLA Reporting",
            "33 GDCC SLA Reporting",
            "34 SDES SLA Reporting",
            "35 REC GES SLA Reporting",
            "36 REC ERDS SLA Reporting",
            "37 REC GRDS SLA Reporting",
            "38 REC CSS SLA Reporting",
            "60 Elexon TAA Report",
            "610 Content provided within PP01",
            "620 Content provided within PP05",
            "630 Content provided within PP08",
            "640 Content provided within PP09",
            "660 Content provided within RGMA flow - ONAGE",
            "670 Content provided within RGMA flow - ONDET",
            "680 Content provided within RGMA flow - ONJOB",
            "690 Content provided within RGMA flow - ONUPD",
            "700 Content provided within RGMA flow - ORDET",
            "710 Content provided within RGMA flow - ORJOB",
            "720 Content provided within RGMA flow - RNAGE",
            "730 Content provided within RGMA flow - RNDET",
            "740 Content provided within RGMA flow - RNJOB",
            "750 Content provided within RGMA flow - RNUPD",
            "760 Content provided within RGMA flow - RRDET",
            "770 Content provided within RGMA flow - RRJOB",
            "840 Meter Asset Data Snapshot - ECOES",
            "850 PPMIP List of Registered Meters",
            "870 ECOES Address Data Quality Report",
            "880 EES Monthly Usage Report",
            "890 EES Supplier Reports Relating to NDCs",
            "90 Network Operator Complaints Data",
            "900 EES User Reports - Daily by User Report",
            "91 GDN Complaints Data",
            "910 EES User Reports - Daily by Supplier Report",
            "920 EES User Reports - Total by User Report",
            "930 EES User Reports - Total by Supplier Reports",
            "940 EES User Reports - User Detail Report",
            "950 EES User Reports - User Summary Report",
            "960 EES User Reports - Deletion Report",
            "Code Manager GDAA Fails Report",
            "Code Manager GDAA Summary Report",
            "Code Manager SMIS Reporting",
            "Code Manager Theft Summary Report",
            "Data Cleanse Anon Movement Report",
            "Data Cleanse Individual Supplier Reports",
            "Distributor CME Report",
            "Metering Accreditation Application Form - ASPCoP",
            "Metering Accreditation Application Form - MCoP",
            "Metering Accreditation Application Form - MOCoP",
            "Other",
            "Supplier HH CME Report",
            "Supplier NHH CME Report"
            ];

              var selected = $("#select2").val();
              console.log("selected" + selected);
              console.log("ext" + ext);
                var result = 0;
                ext = ext.toLowerCase()
                switch(ext) {
                case "csv":
                    result = csv.filter(item => item.includes(selected));
                    break;
                case "xlsx":
                    result = xlsx.filter(item => item.includes(selected));
                    break;
                case "pip":
                    result = pip.filter(item => item.includes(selected));
                    break;
                case "til":
                    result = til.filter(item => item.includes(selected));
                    break;
                case "tab":
                    result = tab.filter(item => item.includes(selected));
                    break;
                case "dat":
                    result = dat.filter(item => item.includes(selected));
                    break;
                case "txt":
                   result = txt.filter(item => item.includes(selected));
                    break;
                case "zip":
                    result = zip.filter(item => item.includes(selected));
                    break;
                default:
                    console.log("in default switch");
                    console.log("selected" + selected);
                    result = notApplicable.filter(item => item.includes(selected));
                    break;
                }
                
                if(result.length > 0) {
                return "Valid";
                } else {
                return "Not Valid";
                }
    }

    async function getByteArray() {
		var fileVerification = checkFileExtention();
        //var fileVerification = "Valid";
        var fileSize = document.getElementById('uploadedFile').files[0].size;
        if (fileSize < 104857600){
            if (fileVerification == "Valid") {
                //alert("Valid")
                uploadingFile();
                let myFile = document.getElementById('uploadedFile').files[0];
                let fileName = myFile.name;
                let ext = fileName.substr(fileName.lastIndexOf('.') + 1);
                //alert(ext)
                let mimeTypeFile = myFile.type;
                console.log(mimeTypeFile);
            //Wait for the file to be converted to a byteArray
                let byteArray = await fileToByteArray(myFile);
                //Do something with the byteArray
                console.log(byteArray);
                var processArea = document.getElementById("select1").value;
                console.log("processArea", processArea);
                Liferay.Service(
                '/cproposal.recformarticle/upload_your_files_RPA',
                    {
                        userId: themeDisplay.getUserId(),
                        repositoryId: '${siteGroupId}',
                        folderId: 0,
                        fileBytes: byteArray,
                        mimeType: mimeTypeFile,
                        title: fileName,
                        processArea: document.getElementById("select1").value,
                        description: document.getElementById("files_form_description").value,
                        fieldType: document.getElementById("select2").value,
                        date: document.getElementById("dateFile").value,
                        partyUploading: document.getElementById("partyUploading").value
                    },
                    function(obj) {
                        console.log(obj);
                        showResponse(obj)
                    }
                );
            }else if (fileVerification == "Not Valid"){
                alert("File submitted is Not Valid");
            }
        } else {
            alert ("Files larger than 100MB can't be uploaded");
        }
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
    function uploadingFile() {
        console.log("uploadingFile");
        var html = "<p> Uploading File... </p> <img src=https://www.appcoda.com/learnswiftui/images/animation/swiftui-animation-8.gif/>";
        $('#waitingFileUpload').html(html);
    }
    

    function showResponse(obj){
        if (obj != undefined){
            console.log(obj.code);
            console.log(obj.message);
            var goBack = window.location.pathname;
            if (obj.code == 500){                
                $('titleAssuranceDiv').addClass('hide');
                var html = "<p> Your file has not been uploaded </p>" 
                html += "<p>Reason: " + obj.message + "</p> <div><p><a class="+"greenButton" +" href=" + goBack +">Back</a></p></div>";
            }else if (obj.code == 200){
                sendNotification75();
                var html = "<p>" + obj.message + "</p> <div><p><a class="+"greenButton" +" href=" + goBack +">Back</a></p></div>";
            } else {
                var html = "<p>" + obj.message + "</p> <div><p><a class="+"greenButton" +" href=" + goBack +">Back</a></p></div>";
            }
        }
        else {
             var html = "<p> There was an error with your file </p> <div><p><a class="+"greenButton" +" href=" + goBack +">Back</a></p></div>";
        }
        $('#upload-file-form-pa').html(html);
    }

    function sendNotification75(){
        var fileUpload = document.getElementById("uploadedFile").value;
        let myFile = document.getElementById('uploadedFile').files[0];
        var notifyUsers = document.getElementById("submitFormButton");
        if ( fileUpload != "" ){
            Liferay.Service(
                  '/messages.messages/send_notification_75',
                  {
                    currentUserId:${userId},
                    siteGroupId:${siteGroupId},
                    remedationTitle:myFile.name,
                    urlEmail:"",
                    companyId:${companyId}
                  },
                  function(obj) {
                    console.log(obj);
                  }
            );
        }
    }

    </script>
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.5.3/jspdf.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf-autotable/3.5.6/jspdf.plugin.autotable.min.js"></script>
<!--
 <script>   
 $(document).ready(function(){
    $("#submitFormButton").click(function(){
    var fileUpload = document.getElementById("uploadedFile").value;
    let myFile = document.getElementById('uploadedFile').files[0];
    var notifyUsers = document.getElementById("submitFormButton");
        if ( fileUpload != "" ){
            Liferay.Service(
                  '/messages.messages/send_notification_75',
                  {
                    currentUserId:${userId},
                    siteGroupId:${siteGroupId},
                    remedationTitle:myFile.name,
                    urlEmail:"",
                    companyId:${companyId}
                  },
                  function(obj) {
                    console.log(obj);
                  }
            );
        }
     });
});

</script>
-->