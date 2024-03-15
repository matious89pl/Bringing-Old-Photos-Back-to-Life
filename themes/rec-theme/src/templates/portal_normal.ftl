<!DOCTYPE html>
<#include init />
<html class="${root_css_class}" dir="<@liferay.language key=" lang.dir" />" lang="${w3c_language_id}">

<head>
	<title>
		${html_title}
	</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<#if show_chat>
		<#-- PRO Script -->
			<script id="Microsoft_Omnichannel_LCWidget" src="https://oc-cdn-public-gbr.azureedge.net/livechatwidget/scripts/LiveChatBootstrapper.js" data-app-id="4de22280-0707-4f5f-befc-7d6c4fbbcde6" data-lcw-version="prod" data-org-id="26763d6a-d8e4-4f3d-b82d-d7ec27740002" data-org-url="https://unq26763d6ad8e44f3db82dd7ec27740-crm11.omnichannelengagementhub.com" data-color-override="#70ada3"></script>
			<#-- UAT Script -- <script id="Microsoft_Omnichannel_LCWidget" src="https://oc-cdn-public-gbr.azureedge.net/livechatwidget/scripts/LiveChatBootstrapper.js" data-app-id="b204cf0a-f444-4f2d-93fa-b021f87af7f9" data-lcw-version="prod" data-org-id="51758c7f-516c-434b-b9af-f29111fda99f" data-org-url="https://unq51758c7f516c434bb9aff29111fda-crm11.omnichannelengagementhub.com" data-color-override="#70ada3">
				</script> -->
				<#-- DEV Script -- <script id="Microsoft_Omnichannel_LCWidget" src="https://oc-cdn-public-gbr.azureedge.net/livechatwidget/scripts/LiveChatBootstrapper.js" data-app-id="e0744ce0-f216-4e60-8200-0be6ac53e7d3" data-lcw-version="prod" data-org-id="f97a7d1f-d904-4e43-8e60-8eda7c2e059c" data-org-url="https://1c4b99e758124b849f98313944c44f-crm11.omnichannelengagementhub.com" data-color-override="#70ada3">
					</script> -->
	</#if>
	<meta content="initial-scale=1.0, width=device-width" name="viewport" />
	<@liferay_util["include"]
		page=top_head_include />
</head>

<body id="rec-theme" class="${css_class}">
	<@liferay_ui["quick-access"]
		contentId="#main-content" />
	<@liferay_util["include"]
		page=body_top_include />
	<div class="d-flex flex-column min-vh-100">
		<#if isPortalUser=='false'>
			<@liferay.control_menu />
		</#if>
		<div class="d-flex flex-column flex-fill" id="wrapper">
			<div id="main-contents" class="container-fluid">
				<div class="row">
					<div class="col-md-2 rec-left-column">
						<#include "${full_templates_path}/navigation.ftl" />
					</div>
					<div class="col-md-10 rec-right-column">
						<#if show_header>
							<header id="banner">
								<div class="navbar navbar-classic navbar-top py-3">
									<div class="container user-personal-bar">
										<div class="align-items-center autofit-row">
											<#assign preferences=freeMarkerPortletPreferences.getPreferences({"portletSetupPortletDecoratorId": "barebone", "destination": "/search"}) />
											<div class="autofit-col autofit-col-expand">
												<#if show_header_search>
													<div id="search-bar-hidden" class="mr-4 navbar-form" role="search">
														<@liferay.search_bar default_preferences="${preferences}" />
													</div>
												</#if>
											</div>
											<div class="autofit-col">
												<#if is_signed_in>
													<div class="notifications-item">
														<img id="bell-notif" src="${images_folder}/forms/solid_bell.svg" width="24" height="24" />
													</div>
													<@liferay.user_personal_bar />
													<div class="personal-user-name">
														${user_name}
														<span class="arrow-down">▼</span>
													</div>
													<#else>
														<@liferay.user_personal_bar />
												</#if>
											</div>
										</div>
									</div>
								</div>
								<!--<#assign cont = 0 />-->
								<div id="popup-notifications" class="overlay">
									<div class="popup">
										<div class="content">
											<ul>
												<#list notification_list as notif, time>
													<li>
														${(notif)[1..]}
														<br><small>
															${time}
														</small>
													</li>
												</#list>
											</ul>
										</div>
									</div>
									<a href="/group/guest/manage?p_p_id=com_liferay_notifications_web_portlet_NotificationsPortlet">See all notifications</a>
								</div>
							</header>
						</#if>
						<section class="${portal_content_css_class} flex-fill" id="content">
							<h2 class="sr-only" role="heading" aria-level="1">
								${the_title}
							</h2>
							<#if selectable>
								<@liferay_util["include"]
									page=content_include />
								<#else>
									${portletDisplay.recycle()}
									${portletDisplay.setTitle(the_title)}
									<@liferay_theme["wrap-portlet"]
										page="portlet.ftl">
										<@liferay_util["include"]
											page=content_include />
										</@>
							</#if>
						</section>
						<#if show_chat>
							<#include "${full_templates_path}/chat.ftl" />
						</#if>
						<#-- <#if show_footer>
							<footer id="footer" role="contentinfo">
								<div class="container">
									<div class="row">
										<div class="col-md-12 text-center text-md-left">
											<@liferay.language key="powered-by" />
											<a class="text-white" href="http://www.liferay.com" rel="external">Liferay</a>
										</div>
									</div>
								</div>
							</footer>
							</#if>*/-->
					</div>
				</div>
			</div>
		</div>
		<@liferay_util["include"]
			page=body_bottom_include />
		<@liferay_util["include"]
			page=bottom_include />
</body>

</html>