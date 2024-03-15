<#--
REMEMBER UPDATE THE ENVIRONMENT IN LINE 16 (allow values: "dev", "uat", "prd"
-->

<#assign chevronImage = themeDisplay.getPathThemeImages()+"/forms/chevrom.svg" />

<script>
    $(document).ready(function(){
    if (sessionStorage.getItem('openTickets')){
    var openTicket = JSON.parse(sessionStorage.getItem('openTickets'));
    getTicketsHTML(openTicket);
    } else {
         Liferay.Service(
    	  '/servicemanagement.helpdesk/get_tickets_by_userId',
    	  {
    	    environment: "dev",
    		userId: themeDisplay.getUserId(),
    		filters: '&status=Active'
    	  },
    	  function(obj) {
        	  if(obj.code == 200){
        		responseTickets = obj.data;
        		sessionStorage.setItem('openTickets', JSON.stringify(obj.data));
        		getTicketsHTML(responseTickets);
        	  }
        	  else if (obj.code == 401) {
        	      unauthorized();
        	  }
    	    }
    	);
    }
});
    
function getTicketsHTML(responseTickets){
    var html = "";
    html += "<table id='instanceReportTable' class='margin-center'>";
    html += "<thead> <tr> <th class='left-align-text'>Reference & Title</th> <th class='left-align-text'>Description</th> <th class='left-align-text'>Status</th> <th class='left-align-text'>Target Response Time</th> <th class='left-align-text'></th> </tr> </thead>";
    html += "<tbody>";
    
    for (var ticket of responseTickets){
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
<h1 id="register-title">Service Management</h1>
																																																																									 
    <h1 class="explore">Explore our help areas...</h1>
    
    <div class="container-fluid">
        <div class="row">
            <a class="help-area col-md-8 green-helper" href="https://gemserv.eloomi.com/">
                <div>
                    <h3 class="knowledge">Knowledge Repository</h3>
                    <p>Click here to explore REC user guides, frequently asked questions, knowledge articles and training courses</p>									  	   
                </div>
            </a>

            <a class="help-area col-md-3 orange-helper raise-ticket" href="/group/guest/raise-a-ticket">
                <div >
                    <p>Can't find what you're looking for?</p>
                    <h3 class="raise">Raise a ticket</h3>
                </div>
            </a>
        </div>
    </div>
    
    
    <h2 class="open-tickets">My Open Tickets<a id="all-tickets" href="/group/guest/my-tickets">View All My Tickets</a></h2>
     <div id="open-ticket-list-render">
        <p> Loading tickets... </p>
        <img src="https://www.appcoda.com/learnswiftui/images/animation/swiftui-animation-8.gif" />
    </div>

</div>