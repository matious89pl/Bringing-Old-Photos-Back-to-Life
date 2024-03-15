<!-- Application page details activity log -->
<#assign filterImg = themeDisplay.getPathThemeImages()+"/forms/filter.svg" />

<div id="activityLogs">
<h1 class="title" class="col-md-9">Activity Log</h1>
  
<div>    
    <div class="filterOpt" >
            <a onclick="openFilterPopUp()"> <img src="${filterImg}"/> Filter </a> <!-- TODO openFilterPopUp function -->
    </div>
        
<table class="tableList">
    <thead>
        <tr>
            <th class="left-align-text"></th>
            <th class="left-align-text">Activity Ref</th>
            <th class="left-align-text">Stage</th>
            <th class="left-align-text">Activity Type</th>
            <th class="left-align-text">Description</th>
            <th class="left-align-text">Due Date</th>
            <th class="left-align-text">Assigned Owner</th>
            <th class="left-align-text">Linked Document</th>
            <th class="left-align-text">Status</th>
            <th class="left-align-text"></th>
        </tr>
    </thead>

    <tbody>
        <tr>
            <td class="left-align-text"></td>
            <td class="left-align-text reference">Lorem</td>
            <td class="left-align-text">Lorem</td>
            <td class="left-align-text">Lorem ipsum</td>
            <td class="left-align-text">Lorem ipsum</td>
            <td class="left-align-text">01/01/2021</td>
            <td class="left-align-text">Anton Moden</td>
            <td class="left-align-text">Lorem ipsum document</td>
            <td class="left-align-text applicationStatusComplete"> <p> Complete </p> </td>

        </tr>
           <tr>
            <td class="left-align-text"></td>
            <td class="left-align-text reference">Lorem</td>
            <td class="left-align-text">Lorem</td>
            <td class="left-align-text">Lorem ipsum</td>
            <td class="left-align-text">Lorem ipsum</td>
            <td class="left-align-text">01/01/2021</td>
            <td class="left-align-text">Dave Daracott</td>
            <td class="left-align-text">Lorem ipsum document</td>
            <td class="left-align-text applicationStatusComplete"> <p>Complete </p> </td>

        </tr>
           <tr>
            <td class="left-align-text"></td>
            <td class="left-align-text reference">Lorem</td>
            <td class="left-align-text">Lorem</td>
            <td class="left-align-text">Lorem ipsum</td>
            <td class="left-align-text">Lorem ipsum</td>
            <td class="left-align-text">01/01/2021</td>
            <td class="left-align-text">Anton Moden</td>
            
            <td class="left-align-text">Lorem ipsum document</td>
            <td class="left-align-text applicationStatusInprogress"> <p> In progress </p> </td>
        </tr>
    </tbody>

</table>
</div>
</div>
