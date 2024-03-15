package com.everis.panel.notifications.application.list;

import com.everis.panel.notifications.constants.PanelNotificationsPanelCategoryKeys;
import com.everis.panel.notifications.constants.PanelNotificationsPortletKeys;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.portal.kernel.model.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author ccaravac
 */
@Component(
	immediate = true,
	property = {
		"panel.app.order:Integer=100",
		"panel.category.key=" + PanelNotificationsPanelCategoryKeys.CONTROL_PANEL_CATEGORY
	},
	service = PanelApp.class
)
public class PanelNotificationsPanelApp extends BasePanelApp {

	@Override
	public String getPortletId() {
		return PanelNotificationsPortletKeys.PANELNOTIFICATIONS;
	}

	@Override
	@Reference(
		target = "(javax.portlet.name=" + PanelNotificationsPortletKeys.PANELNOTIFICATIONS + ")",
		unbind = "-"
	)
	public void setPortlet(Portlet portlet) {
		super.setPortlet(portlet);
	}

}