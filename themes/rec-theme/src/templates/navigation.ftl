<#assign linkLocalService = serviceLocator.findService("rec.link.menu.service.LinkMenuLocalService")>

<nav class="menu" style="position: static;" tabindex="0">
	<div class="smartphone-menu-trigger"></div>
	<header id="avatar" class="avatar">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<a class="${logo_css_class} align-items-center d-md-inline-flex d-sm-none logo-md" href="${company_url}" title="<@liferay.language_format arguments="" key="go-to-x" />">
			<img alt="${logo_description}" class="mr-2 logo-img" src="${site_logo}" />
		</a>
	</header>
	<ul id="left-menu" class="leftnav">
		<#assign links = linkLocalService.getLinkMenus(-1, -1)/>
		<#if links?has_content>
			<#list links as link>
				<li tabindex="0" class="icons">
					<#assign image = "${images_folder}/forms/${link.getIconName()}.svg"/>
					<#assign linkValue = "${link.getLink()}"/>
					<#assign linkName = "${link.getLinkName()}"/>
					<img src="${image}"/>
					<a href="${linkValue}">${linkName}</a>
				</li>
			</#list>
		</#if>
		<a href="javascript:void(0);" class="icon" onclick="myFunction()">
			<i class="fa fa-bars"></i>
		</a>
	</ul>
</nav>



