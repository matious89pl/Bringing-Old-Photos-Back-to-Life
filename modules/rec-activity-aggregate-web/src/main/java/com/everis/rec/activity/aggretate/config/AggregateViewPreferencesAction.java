package com.everis.rec.activity.aggretate.config;

import com.everis.rec.activity.aggretate.constants.RecActivityAggregateWebPortletKeys;
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
		property = "javax.portlet.name="+RecActivityAggregateWebPortletKeys.RECACTIVITYAGGREGATEWEB,
		service = ConfigurationAction.class
	)
 
public class AggregateViewPreferencesAction extends DefaultConfigurationAction {
 
	@Override
	public void processAction(PortletConfig portletConfig, ActionRequest actionRequest,ActionResponse actionResponse)
		throws Exception {

		setPreference(actionRequest, "aggregateView",ParamUtil.getString(actionRequest, "aggregateView"));

		super.processAction(portletConfig, actionRequest, actionResponse);
	}
 
}

