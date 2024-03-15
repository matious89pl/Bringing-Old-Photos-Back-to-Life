<#assign userService = utilLocator.findUtil("com.liferay.portal.kernel.service.UserLocalService") />
<#assign rolService = utilLocator.findUtil("com.liferay.portal.kernel.service.RoleLocalService") />
<#assign userGroupService = utilLocator.findUtil("com.liferay.portal.kernel.service.UserGroupRoleLocalService") />

<#assign member = false />
<#assign groupId = themeDisplay.getScopeGroupId()/>
<#assign userId = themeDisplay.getUserId() />
<#assign users = userService.getGroupUserIds(groupId)/>
<#assign user = userService.getUser(userId)/>
<#list users as user>
    <#if user == userId >
        <#assign member = true />
    </#if>
</#list>

<#assign orgRoleContractM = rolService.getRole(companyId, "REC Contract Managers") />
<#assign orgRolePerformanceA = rolService.getRole(companyId, "REC_Performance_Assurance") />
<#assign orgRoleMasterA = rolService.getRole(companyId, "Master Administrative User") />
<#assign orgRoleDirector= rolService.getRole(companyId, "Director") />
<#assign groupRoles = userGroupService.getUserGroupRoles(userId) />
<#assign userRoles = user.getRoles()/>
<#assign access = false />
<#assign isAdmin = false />

<#list userRoles as rol>	

    <#if rol.getName() == "Administrator" || rol.getName()=="RPA" >							
        <#assign isAdmin = true />				
        <#break>							
    </#if>							
</#list>

<#if groupRoles?has_content>
    <#list groupRoles as rol>
    <#assign roleName = rol.getRole().getName() />

    <#if roleName == orgRoleContractM.getName()  || roleName == orgRoleMasterA.getName() ||
    roleName == orgRolePerformanceA.getName() || roleName == orgRoleDirector.getName() >
            <#assign access = true />
            <#break>
        </#if>
        
        
    </#list>
</#if>

<div id="committee-menu">
<nav aria-label="<@liferay.language key="site-pages" />" class="navbar-blank navbar-nav navbar-site" id="navigation" role="navigation">
	<ul role="menubar" aria-label="Site Pages" class="navbar-blank navbar-nav navbar-site">
	    <#if entries?has_content>
		<#list entries as nav_item>
			<#assign
				nav_item_attr_has_popup = ""
				nav_item_css_class = ""
				nav_item_layout = nav_item.getLayout()
			/>

			<#if nav_item.isSelected()>
				<#assign
					nav_item_attr_has_popup = "aria-haspopup='true'"
					nav_item_css_class = "selected"
				/>
			</#if>
            	<#assign nameItem = nav_item.getName()?replace("&","")/>
				<#if (nameItem == "Annual Maintenance") >
					<#if access || isAdmin >
        						<li class="lfr-nav-item nav-item ${nav_item_css_class}" id="layout_${nav_item.getLayoutId()}" role="presentation">
        				<a class="nav-link text-truncate" href="${nav_item.getURL()}" ${nav_item.getTarget()} role="menuitem"><span class="text-truncate"><@liferay_theme["layout-icon"] layout=nav_item_layout /> ${nav_item.getName()}</span></a>	
        			</#if>
                <#else>
        				<li class="lfr-nav-item nav-item ${nav_item_css_class}" id="layout_${nav_item.getLayoutId()}" role="presentation">
        				<a class="nav-link text-truncate" href="${nav_item.getURL()}" ${nav_item.getTarget()} role="menuitem"><span class="text-truncate"><@liferay_theme["layout-icon"] layout=nav_item_layout /> ${nav_item.getName()}</span></a>	
				</#if>
							
		

				<#if nav_item.hasChildren()>
					<ul class="child-menu" role="menu">
						<#list nav_item.getChildren() as nav_child>
							<#assign
								nav_child_css_class = ""
							/>

							<#if nav_item.isSelected()>
								<#assign
									nav_child_css_class = "selected"
								/>
							</#if>
							
						
	                        <li class="${nav_child_css_class}" id="layout_${nav_child.getLayoutId()}" role="presentation">
    								<a href="${nav_child.getURL()}" ${nav_child.getTarget()} role="menuitem">${nav_child.getName()}</a>
    							</li>
							
						</#list>
					</ul>
				</#if>
			</li>
		</#list>
		</#if>
	</ul>
</nav>
</div>