<#-- Listing committees-->
<#assign groupService = utilLocator.findUtil("com.liferay.portal.kernel.service.GroupLocalService") />			

<#assign companyId = themeDisplay.getCompanyId() />
<#assign committeeSite = groupService.fetchGroup(companyId, "Committees") />
<#assign groupId = committeeSite.groupId />
<#assign sites = groupService.getGroups(companyId, groupId, true) />
<div id="all-committees-register">
<h1>All Committees</h1>
        <ul id="all_list_title">
            <li class="left-committees">Committee Name</li>
            <li class="right-committees">Committee Information</li>
        </ul>
    </thead>
<table>
    <tbody>
<#list sites as site >
<#assign rowURL = site.getDisplayURL(themeDisplay, false) />
<tr>
    <td class="left-committees"><a href="${rowURL}" target="_blank" >${site.name}</a></td>
    <td class="right-committees">${site.description}</td>
</tr>
</#list>
    </tbody>
</table>

</div>