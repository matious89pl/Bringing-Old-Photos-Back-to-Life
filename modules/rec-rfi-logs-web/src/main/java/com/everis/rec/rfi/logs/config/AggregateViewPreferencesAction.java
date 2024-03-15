package com.everis.rec.rfi.logs.config;

import com.everis.rec.rfi.logs.constants.RecRfiLogsWebPortletKeys;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

@Component(
		configurationPolicy = ConfigurationPolicy.OPTIONAL,
		property = "javax.portlet.name="+RecRfiLogsWebPortletKeys.RECRFILOGSWEB,
		service = ConfigurationAction.class
	)
public class AggregateViewPreferencesAction extends DefaultConfigurationAction{

	@Override
	public void processAction(PortletConfig portletConfig, ActionRequest actionRequest,ActionResponse actionResponse)
		throws Exception {

		setPreference(actionRequest, "aggregateLogView",ParamUtil.getString(actionRequest, "aggregateLogView"));

		super.processAction(portletConfig, actionRequest, actionResponse);
	}
}
