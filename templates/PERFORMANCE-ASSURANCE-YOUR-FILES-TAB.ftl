<!-- PA. Your files tab. -->
<#assign groupLocalService = serviceLocator.findService("com.liferay.portal.kernel.service.GroupLocalService") />
<#assign rolService = utilLocator.findUtil("com.liferay.portal.kernel.service.RoleLocalService") />
<#assign userService = utilLocator.findUtil("com.liferay.portal.kernel.service.UserLocalService") />
<#assign userGroupService = utilLocator.findUtil("com.liferay.portal.kernel.service.UserGroupRoleLocalService") />
<#assign dlFolderService = serviceLocator.findService("com.liferay.document.library.kernel.service.DLFolderLocalService") />
<#assign groupId= themeDisplay.getLayout().getGroupId() />
<#assign group = groupLocalService.getGroup(groupId) />
<#assign org = group.getFriendlyURL() />

<#assign userId = themeDisplay.getUserId() />
<#assign user = userService.getUser(userId) />
<#assign userRoles = user.getRoles()/>
<#assign companyId = user.getCompanyId() />

<#assign orgRole = rolService.getRole(companyId, "REC Contract Managers") />
<#assign groupRoles = userGroupService.getUserGroupRoles(userId) />

<#assign isCorrectOrganisationRole = false />
<#assign isCorrectRegularRole = false />
<#assign isAdmin = false />

<#attempt>
  <#assign dlFolderId = dlFolderService.getFolder(groupId, 0, "RPA Documents").getFolderId() />
<#recover>
  <#assign dlFolderId = 0 />
</#attempt>

<#assign myPreferences = freeMarkerPortletPreferences.getPreferences({
  "portletSetupPortletDecoratorId": "barebone",
  "rootFolderId": "${dlFolderId}"
}) />

<#if groupRoles?has_content>
    <#list groupRoles as rol>
    <#assign roleName = rol.getRole().getName() />
        <#if roleName == orgRole.getName()>
            <#assign isCorrectOrganisationRole = true />
            <#break>
        </#if>
    </#list>
</#if>

<#if userRoles?has_content>
    <#list userRoles as rol>
        <#if rol.getName() == "RPA" || rol.getName() == "Portal_User">
            <#assign isCorrectRegularRole = true />
            <#break>
        </#if>
        <#if rol.getName() == "Administrator">
            <#assign isAdmin = true />
            <#break>
        </#if>
    </#list>
</#if>

<div id="your-files-tab">
    <div id="uploadFileButtonSection">
        <#assign url = "/group"+ org + "/upload-your-files" />
        <a class="greenButton" href="${url}">Upload File</a>
    </div>
    <div>
        <@liferay_portlet["runtime"]
                         defaultPreferences="${myPreferences}"
                         instanceId="rec-dm-${groupId}"
                         portletName="com_liferay_document_library_web_portlet_DLPortlet"
        />
    </div>
</div>
<#if !isAdmin >
    <#if isCorrectOrganisationRole || isCorrectRegularRole>

        <style>
        header.portlet-topper {
            visibility: hidden;
        }

        #your-files-tab button.dropdown-toggle.btn.nav-btn.nav-btn-monospaced.btn-primary {
        visibility: hidden;
        }

        .portlet-content.portlet-content-editable:hover {
            border-color: transparent !important;
        }

        </style>
    </#if>
</#if>




<style>
    .greenButton{
        background: #b1c568;
        border-radius: 5px;
        color: white;
        font-size: 12px;
        font-weight: normal;
        letter-spacing: 0.25px;
        line-height: 18px;
        height: 36px;
        border: none;
        padding: 9px 16px 9px 17px;
    }

    a.greenButton:hover {
        color: white;
        text-decoration: none;
    }

    .breadcrumb-text-truncate{
        display: none;
    }
</style>

<script>
    $(document).ready(function() {
      $('.portlet-boundary_com_liferay_document_library_web_portlet_DLPortlet_').on('mousedown', function(e) {
          return null;
      });
    });
</script>

<script type='text/javascript'>
    $('body').on('dragstart drop', function(e){
        e.preventDefault();
        return false;
    });
</script>