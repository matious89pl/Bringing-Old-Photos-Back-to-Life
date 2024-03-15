package com.everis.rec.activity.portlet;


import com.liferay.journal.service.JournalArticleService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

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
		service = MVCActionCommand.class
	)

public class AddActivitySearchMVCActionCommand extends BaseMVCActionCommand {

	
	@Reference
	private Portal _portal;
	
	@Reference
	protected JournalArticleService _journalArticleService;

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException {
		String name = ParamUtil.getString(actionRequest, "name");
		String descripcion = ParamUtil.getString(actionRequest, "description");
		ThemeDisplay themeDisplay =	(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
			// Call the service to update the assignment
		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setScopeGroupId(themeDisplay.getScopeGroupId());
		Map<Locale, String> title= new HashMap<Locale, String>();
		title.put(themeDisplay.getLocale(), name);
		Map<Locale, String> descripcionJournal= new HashMap<Locale, String>();
		descripcionJournal.put(themeDisplay.getLocale(), descripcion);
		try {
			String content="<?xml version=\"1.0\"?>\n" + 
					"\n" + 
					"<root available-locales=\"es_ES\" default-locale=\"es_ES\" version=\"1.0\">\n" + 
					"	<dynamic-element index-type=\"text\" instance-id=\"mCKe99GN\" name=\"content\" type=\"rich_text\">\n" + 
					"		<dynamic-content language-id=\"es_ES\"><![CDATA[]]></dynamic-content>\n" + 
					"	</dynamic-element>\n" + 
					"</root>";
			_journalArticleService.addArticle(themeDisplay.getScopeGroupId(), 0, title, descripcionJournal, content, "BASIC-WEB-CONTENT", "BASIC-WEB-CONTENT", serviceContext);
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}					
		sendRedirect(actionRequest, actionResponse);		
	}

	

	

}