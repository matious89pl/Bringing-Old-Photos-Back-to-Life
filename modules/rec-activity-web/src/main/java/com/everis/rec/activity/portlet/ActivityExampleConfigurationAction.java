package com.everis.rec.activity.portlet;

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
import com.everis.rec.activity.portlet.ActivityExampleConfiguration;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.ParamUtil;

@Component(
    configurationPid = ActivitySearchClayManagementPortletKeys.CONFIGURATION_ID,
    configurationPolicy = ConfigurationPolicy.OPTIONAL,
    immediate = true,
    property = {
        "javax.portlet.name=PildoraSearchClayManagementPortlet"
    },
    service = ConfigurationAction.class
)
public class ActivityExampleConfigurationAction extends DefaultConfigurationAction {

    @Override
    public void processAction(PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse)
        throws Exception {

    	String color = ParamUtil.getString(actionRequest, "color");
		setPreference(actionRequest, "color", color);

    	String aggregateView = ParamUtil.getString(actionRequest, "aggregateView", "asdf");
		setPreference(actionRequest, "aggregateView", aggregateView);
		
        super.processAction(portletConfig, actionRequest, actionResponse);
    }

    @Override
    public void include(
        PortletConfig portletConfig, HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse) throws Exception {

        httpServletRequest.setAttribute(ActivityExampleConfiguration.class.getName(), _exampleConfiguration);

        super.include(portletConfig, httpServletRequest, httpServletResponse);
    }

    @Activate
    @Modified
    protected void activate(Map<Object, Object> properties) {
        _exampleConfiguration = ConfigurableUtil.createConfigurable(ActivityExampleConfiguration.class, properties);
    }

    private volatile ActivityExampleConfiguration _exampleConfiguration;

}