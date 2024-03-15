package com.everis.rec.activity.portlet;


import com.liferay.journal.service.JournalArticleService;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.everis.rec.activity.constants.ActivitySearchClayManagementPortletKeys;

/**
 * MVC command for showing the assignments list.
 * 
 * @author liferay
 */
@Component(
		immediate = true,
		property = {
			"javax.portlet.name=" + ActivitySearchClayManagementPortletKeys.ACTIVITYEARCHCLAYMANAGEMENT,
			"mvc.command.name=edicion_add"
		}, 
		service = MVCRenderCommand.class
	)

public class AddActivitySearchMVCRenderCommand implements MVCRenderCommand {

	
	@Reference
	private Portal _portal;
	
	@Reference
	protected JournalArticleService _journalArticleService;

	

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		ThemeDisplay themeDisplay =	(ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();
		portletDisplay.setShowBackIcon(true);
		String redirect = renderRequest.getParameter("redirect");
		portletDisplay.setURLBack(redirect);		
		return "/html/searchcontainer/edit_add.jsp"; 
	}
	

}