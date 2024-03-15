
package com.everis.rec.activity.config;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.ParamUtil;

import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;

import com.everis.rec.activity.constants.ActivitySearchClayManagementPortletKeys;

//@formatter:off
@Component(
	immediate = true, 
	configurationPid = ActivitySearchClayManagementPortletKeys.ConfigurationId,	
	configurationPolicy = ConfigurationPolicy.OPTIONAL,
	property = {
		"javax.portlet.name=" + ActivitySearchClayManagementPortletKeys.ACTIVITYEARCHCLAYMANAGEMENT
	}, 
	service = ConfigurationAction.class)
//@formatter:on
public class ActivityConfigurationAction extends DefaultConfigurationAction {

	

	@Override
	public void processAction(PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String aggregateView = ParamUtil.getString(actionRequest, ActivitySearchClayManagementPortletKeys.AGGREGATE_VIEW);
	
		setPreference(actionRequest, ActivitySearchClayManagementPortletKeys.AGGREGATE_VIEW, aggregateView);
		
		System.out.println("Configuration action => aggregateView:"+aggregateView);
		
		
	
		super.processAction(portletConfig, actionRequest, actionResponse);
	}

	@Override
	public void include(PortletConfig portletConfig, HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		request.setAttribute(ActivityConfiguration.class.getName(), _activityConfiguration);
		super.include(portletConfig, request, response);
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {

		_activityConfiguration = ConfigurableUtil.createConfigurable(ActivityConfiguration.class, properties);
	}

	private volatile ActivityConfiguration _activityConfiguration;

}
