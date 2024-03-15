<#-- REMEMBER UPDATE THE ENVIRONMENT IN LINE 29 AND 84 (allow values: "dev" , "uat" , "prd" -->

    <#assign Imagerute=themeDisplay.getPathThemeImages()+"/forms/chevrom-white.svg" />
    <#assign ImageruteThankyou=themeDisplay.getPathThemeImages()+"/forms/check.svg" />

    <#assign userService=utilLocator.findUtil("com.liferay.portal.kernel.service.UserLocalService") />
    <#assign userId=themeDisplay.getUserId() />
    <#assign user=userService.getUser(userId)>
        <#assign currentEmail=user.getEmailAddress() />
        <#assign currentFirstName=user.getFirstName() />
        <#assign currentLastName=user.getLastName() />
        <#assign userPhones=user.getPhones() />
        <#if userPhones?has_content && userPhones??>
            <#assign userPhone=userPhones[0] />
            <#assign userPhoneNumber=userPhone.number />
            <#else>
                <#assign userPhoneNumber='' />
        </#if>

        <#assign url=themeDisplay.getURLHome() />
        <#assign env="" />

        <#if url?contains("dev")>
            <#assign env="dev" />
            <#elseif url?contains("uat") || url?contains("preprd")>
                <#assign env="uat" />
                <#elseif url?contains("recportal.co.uk")>
                    <#assign env="prd" />
        </#if>

        <script>

            $(document).ready(function () {
                //call a service to retrieve all the subservices and build the selectors
                Liferay.Service(
                    '/servicemanagement.helpdesk/get_sub_services_by_environment',
                    {
                        environment: "${env}"
                    },
                    function (result) {
                        console.log(result);
                        if (result.code == 200) {
                            buildSelectors(result.data);
                        }
                        else {
                            formUnavailable();
                        }
                    }
                );

                //Code for enable submit button when all the fields are filled
                $("input[type='text'], textarea").on("keyup", function () {
                    if ($(this).val() != "" && $("textarea").val() != "" && $("select[name='type']").val() != "" && $("select[name='classification']").val() != "" && $("select[name='rec_service_one']").val() != "" && $("select[name='rec_service_two']").val() != "") {
                        $("#nextButton").prop("disabled", false);
                    } else {
                        $("#nextButton").prop("disabled", true);
                    }
                });

                $("select").on("change", function () {
                    if ($(this).val() != "" && $("textarea").val() != "" && $("select[name='type']").val() != "" && $("select[name='classification']").val() != "" && $("select[name='rec_service_one']").val() != "" && $("select[name='rec_service_two']").val() != "") {
                        $("#nextButton").prop("disabled", false);
                    } else {
                        $("#nextButton").prop("disabled", true);
                    }
                });

                // type and classification
                // commented until have dynamic classification values
                /* $('#helpdesk_classification').prop("disabled", true);   
                 $("#helpdesk_type").change(function() {
                     $('#helpdesk_classification').prop("disabled", false);
                   if ($(this).data('options') === undefined) {
                     $(this).data('options', $('#helpdesk_classification option').clone());
                   }
                   var id = $(this).val();
                   var options = $(this).data('options').filter('[name=' + id + ']');
                   $('#helpdesk_classification').html(options);
                 });*/


                $("#nextButton").click(function () {

                    $(this).prop("disabled", true);
                    loadingRequest();
                    var typeValueUnderscore = document.getElementById("helpdesk_type").value;
                    var typeValueSpaces = typeValueUnderscore.replace(/_/g, " ");


                    Liferay.Service(
                        '/servicemanagement.helpdesk/create_new_ticket',
                        {
                            environment: "prd",
                            title: document.getElementById("helpdesk_title").value,
                            description: document.getElementById("helpdesk_description").value,
                            firstName: document.getElementById("helpdesk_firstname").value,
                            lastName: document.getElementById("helpdesk_lastname").value,
                            contactEmail: document.getElementById("helpdesk_contactEmailAddress").value,
                            type: typeValueSpaces,
                            classification: document.getElementById("helpdesk_classification").value,
                            subserviceName: document.getElementById("select-service-right").value
                        },
                        function (obj) {
                            //alert("New case created");
                            console.log(obj.code);
                            if (obj.code == 200) {
                                showConfirmation();
                                if (sessionStorage.getItem('allTickets')) {
                                    sessionStorage.removeItem('allTickets');
                                }
                                if (sessionStorage.getItem('openTickets')) {
                                    sessionStorage.removeItem('openTickets');
                                }
                            } else if (obj.code == 500 || obj.code == undefined) {
                                showReasoningForFailuire();
                            }
                        }
                    );
                });
            });

            function selectorLogic() {
                $('#select-service-right').prop("disabled", true);
                $("#select-service-left").change(function () {
                    $('#select-service-right').prop("disabled", false);
                    if ($(this).data('options') === undefined) {
                        $(this).data('options', $('#select-service-right option').clone());
                    }
                    var id = $(this).val();
                    var options = $(this).data('options').filter('[name=' + id + ']');
                    $('#select-service-right').html(options);
                });

                var subService = $("#select-service-right option:selected").text();

                if (subService == "Please select...") {
                    subService = "";
                }
            }

            function buildSelectors(data) {
                let htmlServiceSelector = "<select required id='select-service-left' name='rec_service_one'>";
                htmlServiceSelector += "<option value=''>Please select...</option>";

                let htmlSubServiceSelector = "<select required id='select-service-right' name='rec_service_two'>";
                htmlSubServiceSelector += "<option value=''>Please select...</option>";

                let parent = "";
                let parentNoSpaces = "";
                let childNoSpaces = "";
                for (let i = 0; i < data.length; i++) {
                    if (data[i].ParentSubjectTitle != parent) {
                        parent = data[i].ParentSubjectTitle;
                        parentNoSpaces = parent.replace(' &', "");
                        parentNoSpaces = parentNoSpaces.replace(',', "");
                        parentNoSpaces = parentNoSpaces.split(' ').join('_');
                        htmlServiceSelector += "<option value='" + parentNoSpaces + "'>" + data[i].ParentSubjectTitle + "</option>";
                    }
                    htmlSubServiceSelector += "<option name='" + parentNoSpaces + "' value='" + data[i].SubjectId + "'>" + data[i].Title + "</option>";
                }
                htmlServiceSelector += "</select>";
                htmlSubServiceSelector += "</select>";

                $("#ServiceSelector").html(htmlServiceSelector);
                $("#SubServiceSelector").html(htmlSubServiceSelector);
                $("#form-box").removeClass("hide");
                $("#loading-form").addClass("hide");
                selectorLogic();
            }

            function formUnavailable() {
                let html = "<p> Apologies, Raise a Ticket form is unavailable. Please try again later </p>";
                html += "<a id='back-service' href='/web/guest/knowledge-and-service-desk'>Back to Service Management</a>";
                $("#loading-form").html(html);
            }


            function showConfirmation() {
                var html = "";
                html += "<div id='message_box'>";
                html += "<img src='${ImageruteThankyou}'/>";
                html += "<p id='raised'>Your Ticket has been raised.</p>";
                html += "<a id='back-service' href='/web/guest/knowledge-and-service-desk'>Back to Service Management</a>";
                html += "</div>";
                $("#form-box").html(html);
            }
            function showReasoningForFailuire() {
                var html = "";
                html += "<div id='message_box'>";

                html += "<p id='raised'>The ticket was not created, please try again or contact Administrator.</p>";
                html += "<a id='back-service' href='/web/guest/knowledge-and-service-desk'>Back to Service Management</a>";
                html += "</div>";
                $("#form-box").html(html);
            }

            function loadingRequest() {
                var html = "<p> Creating ticket... </p>"
                html += "<img src='https://www.appcoda.com/learnswiftui/images/animation/swiftui-animation-8.gif' />";
                $("#loading-request").html(html);
            }
        </script>


        <div id="raise-ticket">
            <h1 id="main-title">Raise a Ticket</h1>
            <p id="descript-title"></p>
            <div id="loading-form"> Loading form... </div>
            <div id="form-box" class="container-fluid hide">
                <form>
                    <div class="row">
                        <div class="col-md-6">
                            <label class="required">First Name</label>
                            <input id="helpdesk_firstname" name="first_name" type="text" value="${currentFirstName}" />
                        </div>
                        <div class="col-md-6">
                            <label class="required">Last Name</label>
                            <input id="helpdesk_lastname" name="last_name" type="text" value="${currentLastName}" />
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <label class="required">Email Address</label>
                            <input id="helpdesk_contactEmailAddress" name="email_address" type="text"
                                value="${currentEmail}" />
                        </div>
                        <div class="col-md-6">
                            <label class="required">Telephone number</label>
                            <input id="helpdesk_telephoneNumber" name="tlp_number" type="text"
                                value="${userPhoneNumber}" />
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <label class="required">Type</label>
                            <select required id="helpdesk_type" name="type">
                                <option value="">Please select...</option>
                                <option value="Service_Request">Service Request</option>
                                <option value="Fault">Fault</option>
                                <option value="Technical_Incident">Technical Incident</option>
                                <option value="Help_Assistance">Help Assistance</option>
                                <option value="Change_Request">Change Request</option>
                            </select>

                        </div>
                        <div class="col-md-6">
                            <label class="required">Classification</label>
                            <select required disabled id="helpdesk_classification" name="classification">
                                <option value="Default">Default</option>
                                <#-- <option value="">Please select...</option>
                                    <option name="Service_Request" value="User and Organisation Provision">User and
                                        Organisation Provision</option>
                                    <option name="Service_Request" value="Role Management">Role Management</option>
                                    <option name="Service_Request" value="Password Reset">Password Reset</option>
                                    <option name="Service_Request" value="Other">Other</option>
                                    <option name="Fault" value="System">System</option>
                                    <option name="Fault" value="Content">Content</option>
                                    <option name="Fault" value="Workflow">Workflow</option>
                                    <option name="Fault" value="Other">Other</option>
                                    <option name="Technical_Incident" value="Automatic Alerts">Automatic Alerts</option>
                                    <option name="Technical_Incident" value="Other">Other</option>
                                    <option name="Help_Assistance" value="Request for Information">Request for
                                        Information</option>
                                    <option name="Help_Assistance" value="Assistance Using Application">Assistance Using
                                        Application</option>
                                    <option name="Help_Assistance" value="Other">Other</option>
                                    <option name="Change_Request" value="As In Rec Service Catalogue">As In Rec Service
                                        Catalogue</option>
                                    <option name="Change_Request" value="Other">Other</option> -->
                            </select>

                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <label class="required">REC Service Catalogue</label>
                            <div id="ServiceSelector"></div>
                        </div>
                        <div class="col-md-6">
                            <label class="required"></label>
                            <div id="SubServiceSelector"></div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <label class="required">Title</label>
                            <input id="helpdesk_title" type="text" value="" required />
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <label class="required">Description</label>
                            <textarea id="helpdesk_description" rows="5" name="description" required></textarea>
                        </div>
                    </div>
                </form>
                <button id="nextButton" class="float-right lfr-ddm-form-pagination-next btn btn-primary" disabled>Submit
                    <img src="${Imagerute}" value="submit" />
                </button>


                <a id="cancelButton" class=" lfr-ddm-form-pagination-next "
                    href='/web/guest/service-desk-landing'>Cancel</a>
                <div id="loading-request">
                </div>
            </div>
        </div>


        <style type="text/css">
            select[disabled] {
                background-color: rgba(0, 0, 0, 0.2) !important;
            }

            .required:after {
                content: " *";
                color: red;
            }

            a#cancelButton {
                margin-left: 15px !important;
                margin-top: 5px;
                display: inline-block;
            }
        </style>