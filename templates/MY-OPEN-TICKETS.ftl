<#assign chevronImage = themeDisplay.getPathThemeImages()+"/forms/chevrom.svg" />

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
        filters: '&status=Active'
        },
        function(obj) {
            console.log(obj);
            if(obj.code == 200){
            responseTickets = obj.data;
            sessionStorage.setItem('openTickets', JSON.stringify(obj.data));
            getTicketsHTML(responseTickets);
            }
            else if (obj.code == 401) {
                console.log(obj.code);
                unauthorized();
            }
        }
    );
     
    });
    
function getTicketsHTML(responseTickets){
    var html = "";
    html += "<table id='instanceReportTable' class='margin-center'>";
    html += "<thead> <tr> <th class='left-align-text'>Reference & Title</th> <th class='left-align-text'>Description</th> <th class='left-align-text'>Status</th> <th class='left-align-text'>Target Response Time</th> <th class='left-align-text'></th> </tr> </thead>";
    html += "<tbody>";
    
    for (var ticket of responseTickets){
        console.log(ticket);
        html += "<tr>"; //class='new'
        html += "<td> <p><span class='title'> Ticket " + ticket.Title + "</p> </td>";
        html += "<td>" + ticket.Description + "</td>";
        if (ticket.Status == "Active"){
            html += "<td><p><span class='status service-pro'>Open</span></p></td>";
        } else {
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

        html += "<td><a href='/group/guest/my-tickets?" + idParams + " ' class='open'><img src='${chevronImage}'/></a></td>";
        html += "</tr>";
    }
    html += "</tbody> </table> </div>";
    $('#open-ticket-list-render').html(html);
}
function unauthorized(){
     var html = "<p> New token nedeed </p>";
     $('#ticket-list-render').html(html);
}
</script>

<#assign Imagerute = themeDisplay.getPathThemeImages()+"/forms/chevrom.svg" />

<div id="service-management">

    <#if Title.getData()?has_content && Title.getData() != "" >
    <h2 class="open-tickets">${Title.getData()}<a id="all-tickets" href="/group/guest/my-tickets">${Link.getData()}</a></h2>
    </#if>
     <div id="open-ticket-list-render">
        <p> Loading tickets... </p>
        <img src="https://www.appcoda.com/learnswiftui/images/animation/swiftui-animation-8.gif" />
    </div>

</div>