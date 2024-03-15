<%@ include file="/init.jsp" %>

<script src="https://cdnjs.cloudflare.com/ajax/libs/powerbi-client/2.22.0/powerbi.js"></script>

<%
    String env = (String) renderRequest.getAttribute("env");
    String aToken = (String) renderRequest.getAttribute("access_token");
    long folderId = (long) renderRequest.getAttribute("folderId");
%>

<c:choose>
    <c:when test="<%= Validator.isNotNull(aToken) %>">
        <%
            String bearer = "Bearer " + aToken;
        %>
        <c:choose>
            <c:when test="<%= Validator.isNotNull(dashboardLink) %>">
                <%
                    if (dashboardUserId == StringPool.BLANK) {
                        dashboardUserId = String.valueOf(folderId);
                    }
                %>

                <main class="row">
                  <div id="dashboardContainer" style="border:none;height:<%=dashboardHeight%>px; width:100%;"></div>
                  <div id="error-container" class="alert alert-info" style="display:none;"></div>
                </main>

                <script>
                    $(function () {

                        const url = new URL("<%= dashboardLink %>");
                        const params = url.searchParams;
                        let reportId = params.get("reportId");
                        console.log("reportId " + reportId);

                        var newToken;
                        var generateTokenUrl = "https://api.powerbi.com/v1.0/myorg/groups/<%= RecDashboardsPortletKeys.getWorkspaceId(env) %>/reports/" + reportId + "/GenerateToken"
                        var settings = {
                          "url": generateTokenUrl,
                          "method": "POST",
                          "timeout": 0,
                          "headers": {
                            "Content-Type": "application/json",
                            "Authorization": "<%= bearer %>"
                          },
                          "data": JSON.stringify({
                            "accessLevel": "View",
                            "identities": [
                              {
                                "username": "<%= dashboardUserId %>",
                                "roles": [
                                  "<%= dashboardRole %>"
                                ],
                                "datasets": [
                                  "<%= dashboardDataset %>"
                                ]
                              }
                            ]
                          }),
                        };

                        $.ajax(settings).done(function (response) {
                          newToken = response.token;

                          models = window['powerbi-client'].models;

                          reportContainer = $("#dashboardContainer").get(0);

                          // Initialize iframe for embedding report
                          powerbi.bootstrap(reportContainer, { type: "report" });

                          // We give All permissions to demonstrate switching between View and Edit mode and saving report.
                          var permissions = models.Permissions.View;

                          reportLoadConfig = {
                              type: "report",
                              tokenType: 1,
                              accessToken: newToken,
                              embedUrl: "<%= dashboardLink %>",
                              id: reportId,
                              permissions: permissions,
                              // Setting to remove gray shoulders from embedded report
                              settings: {
                                  background: models.BackgroundType.Transparent,
                                  filterPaneEnabled: false
                              }
                          };

                          // Embed Power BI report when Access token and Embed URL are available
                          report = powerbi.embed(reportContainer, reportLoadConfig);

                          // Use the token expiry to regenerate Embed token for seamless end user experience
                          // Refer https://aka.ms/RefreshEmbedToken
                          // tokenExpiry = embedData["tokenExpiry"]

                          // Triggers when a report schema is successfully loaded
                          report.on("loaded", function () {
                              console.log("Report load successful");
                          });

                          // Triggers when a report is successfully embedded in UI
                          report.on("rendered", function () {
                              console.log("Report render successful");
                          });

                          // Clear any other error handler event
                          report.off("error");
                          // Below patch of code is for handling errors that occur during embedding
                          report.on("error", function (event) {
                              errorMsg = event.detail;

                              // Use errorMsg variable to log error in any destination of choice
                              console.error(errorMsg);
                              return;
                          });
                        }).fail(function(xhr, textStatus, errorThrown) {
                            console.log(xhr.responseJSON.error.message);
                            $("#dashboardContainer").hide();
                            $("#error-container").html(xhr.responseJSON.error.message);
                            $("#error-container").show();
                        });
                    });
                </script>
                
                <style>
               		iframe{
               			border: none;
               		}
                </style>
                
            </c:when>
            <c:otherwise>
                <div class="alert alert-info">
                   Ops! ... There is no link set for this Dashboard.
                </div>
            </c:otherwise>
        </c:choose>
    </c:when>
    <c:otherwise>
        <div class="alert alert-info">
           Ops! ... Error generating access token, Please contact REC support team.
        </div>
    </c:otherwise>
</c:choose>
