package com.everis.rec.maintenanceforms.config;

import com.everis.rec.maintenanceforms.constants.MaintenanceFormsActionPortletKeys;
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
		property = "javax.portlet.name=" + MaintenanceFormsActionPortletKeys.MAINTENANCEFORMSACTION,
		service = ConfigurationAction.class
	)
public class MaintenanceFormsConfigAction extends DefaultConfigurationAction  {
	
	@Override
	public void processAction(PortletConfig portletConfig, ActionRequest actionRequest,ActionResponse actionResponse)
		throws Exception {

			

			

		setPreference(actionRequest, "formenv",ParamUtil.getString(actionRequest, "formenv"));
		setPreference(actionRequest, "aggregateFormView",ParamUtil.getString(actionRequest, "aggregateFormView"));		

		super.processAction(portletConfig, actionRequest, actionResponse);
	}


}
