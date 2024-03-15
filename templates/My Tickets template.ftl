<#--
REMEMBER UPDATE THE ENVIRONMENT IN LINE 27 (allow values: "dev", "uat", "prd"
-->

<#assign Imagerute = themeDisplay.getPathThemeImages()+"/forms/chevrom.svg" />

<#assign url = themeDisplay.getURLHome()  />
<#assign env = "" />

<#if url?contains("dev")>
    <#assign env = "dev" />
<#elseif url?contains("uat") || url?contains("preprd")>
    <#assign env = "uat" />
<#elseif url?contains("recportal.co.uk")>
    <#assign env = "prd" />
</#if>

<script>
    $(document).ready(function(){
        Liferay.Service(
            '/servicemanagement.helpdesk/get_tickets_by_userId',
            {
            environment: "${env}",
            userId: themeDisplay.getUserId(),
            filters: '&limit=30'
            },
            function(obj) {
                if(obj.code == 200){
                responseTickets = obj.data;
                sessionStorage.setItem('allTickets', JSON.stringify(obj.data));
                createTicketsHTML(responseTickets);
                var queryString = window.location.search;
                var urlParams = new URLSearchParams(queryString);
                var idParam = urlParams.get('id');
                if (idParam != null){
                    $("#my-tickets").css("display","none");
                    ticketDetails(idParam, responseTickets);
                }
                }
                else if (obj.code == 401) {
                    unauthorized();
                } 
                else if (obj.code == 500) {
                    console.log("Internal Server Error");

                }
            }
        );
});

function createTicketsHTML(responseTickets){
    var html = "";
    html += "<div id='table-impact'>";
    html += "<table id='instanceReportTable' class='margin-center'>";
    html += "<thead> <tr> <th class='left-align-text'>Reference & Title</th> <th class='left-align-text'>Description</th> <th class='left-align-text'>Status</th> <th class='left-align-text'>Target Response Time</th> <th class='left-align-text'></th> </tr> </thead>";
    html += "<tbody>";
    
    for (var ticket of responseTickets){
											   
        html += "<tr>"; //class='new'
        html += "<td> <p><span class='title'> Ticket " + ticket.Title + "</p> </td>";
        html += "<td>" + ticket.Description + "</td>";
        if (ticket.Status == "Active"){
            html += "<td><p><span class='status service-pro'> Open </span></p></td>";
        }
        else {
            html += "<td><p><span class='status service-pro'>" + ticket.Status + "</span></p></td>";
        }
        if (ticket.TargetResponseTime != null){
            html += " <td>" + ticket.TargetResponseTime + "</td>";
        }
        else {
            html += " <td> - </td>";
        }
        
        var paramsString = "?id=" + ticket.CaseReference;
        var idParams = new URLSearchParams(paramsString);

        html += "<td><a href='/group/guest/my-tickets?" + idParams + " ' class='open'><img src='${Imagerute}'/></a></td>";
        html += "</tr>";
    }
    html += "</tbody> </table> </div>";
    $('#ticket-list-render').html(html);
}

function unauthorized(){
     var html = "<p> New token nedeed </p>";
     $('#ticket-list-render').html(html);
}

function ticketDetails(ticketReference, responseTickets){
    var html = "";
    html += "<div id='ticketdetails'>";
    for (var ticket of responseTickets){
        if (ticket.CaseReference == ticketReference){
            ticketFound = true;
            var title = ticket.Title;
            var description =  ticket.Description;
            var status =  ticket.Status;
            var id = ticket.CaseReference;
            var targetResponseTime = ticket.TargetResponseTime;
            var assignedUser = ticket.AssignedUser;
			 var assignedTeam = ticket.AssignedTeam;
            var helpDeskLine = ticket.HelpDeskLine;
            
           if (title == null) {  title = "    -    " }
           if (description == null) {  description = "    -    " }
           if (status == null) {  status = "    -    " }
           if (targetResponseTime == null) {  targetResponseTime = "    -    " }
           if (assignedUser == null) {  assignedUser = "Not Applicable" }
           if (assignedTeam == null) {  assignedTeam = "    -    " }
           if (helpDeskLine == null) {  helpDeskLine = "    -    " }

            html += "<div id='ticketdetailsStatus'>";
                html += "<h2>" + id + " </h2>";
                html += "<h2>" + status + " </h2>";
            html += "</div>";

            html += "<div id='ticketDetailsTitle'>";
                html += "<h2>" + title + " </h2>";
                html += "<h3>" + description + " </h3>";
            html += "</div>";

            html += "<div id='ticketdetailsInfo'>";
                html += "<div class='left'>";
                    html += "<h3> Target response time </h3>";
                    html += "<p> " + targetResponseTime + " </p>";
                    html += "<h3> HelpDesk Line </h3>";
                    html += "<p> " + helpDeskLine + " </p>";
                html += "</div>";
                
                html += "<div class='rigth'>";
                    html += "<h3> Assigned person </h3>";
                    html += "<p> " + assignedUser + " </p>";
                    html += "<h3> Assigned team </h3>";
                    html += "<p> " + assignedTeam + " </p>";
                html += "</div>";
            html += "</div>";
        break;
        }
    }
    html += "</div>";
    $("#my-tickets").css("display","block");
    $('#ticket-details-render').html(html);
}
</script>
 
<div id="my-tickets">
    <#if request.getParameter("id")??>
        <div id="ticket-details-render">
            <p> Loading ticket details... </p>
            <img src="https://www.appcoda.com/learnswiftui/images/animation/swiftui-animation-8.gif" />
        </div>
    <#else>
        <h1 id="main-title">My Tickets</h1>
        <div id="ticket-list-render">
            <p> Loading tickets... </p>
            <img src="https://www.appcoda.com/learnswiftui/images/animation/swiftui-animation-8.gif" />
        </div>
    </#if> 
    
</div>