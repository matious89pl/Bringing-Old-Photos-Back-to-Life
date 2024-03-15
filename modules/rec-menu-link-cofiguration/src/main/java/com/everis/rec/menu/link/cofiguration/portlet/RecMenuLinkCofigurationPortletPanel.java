package com.everis.rec.menu.link.cofiguration.portlet;

import com.everis.rec.menu.link.cofiguration.constants.RecMenuLinkCofigurationPortletKeys;
import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.application.list.constants.PanelCategoryKeys;
import com.liferay.portal.kernel.model.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	    immediate = true,
	    property = {
	        "panel.app.order:Integer=0",
	        "panel.category.key=" + PanelCategoryKeys.SITE_ADMINISTRATION_CONFIGURATION
	    },
	    service = PanelApp.class)
public class RecMenuLinkCofigurationPortletPanel extends BasePanelApp {
	
	@Override
	 public String getPortletId() {
	        return RecMenuLinkCofigurationPortletKeys.RECMENULINKCOFIGURATION;
	    }
		@Override
	  @Reference(target = "(javax.portlet.name=" + RecMenuLinkCofigurationPortletKeys.RECMENULINKCOFIGURATION + ")", unbind = "-")
	    public void setPortlet(Portlet portlet) {
	        super.setPortlet(portlet);
	    }
}
