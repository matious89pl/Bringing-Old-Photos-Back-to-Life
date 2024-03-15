package com.everis.rec.remediation.tracker.config;

import com.everis.rec.remediation.tracker.constants.RecRemediationTrackerWebPortletKeys;
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
		property = "javax.portlet.name="+RecRemediationTrackerWebPortletKeys.RECREMEDIATIONTRACKERWEB,
		service = ConfigurationAction.class
	)
 
public class AggregateViewPreferencesAction extends DefaultConfigurationAction {
 
	@Override
	public void processAction(PortletConfig portletConfig, ActionRequest actionRequest,ActionResponse actionResponse)
		throws Exception {

		setPreference(actionRequest, "aggregateRemediationTrackerView",ParamUtil.getString(actionRequest, "aggregateRemediationTrackerView"));

		super.processAction(portletConfig, actionRequest, actionResponse);
	}
 
}

