package rec.support.resources.portlet.config;

import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

import rec.support.resources.portlet.constants.RecSupportResourcesPortletKeys;

@Component(
		configurationPolicy = ConfigurationPolicy.OPTIONAL,
		property = "javax.portlet.name="+RecSupportResourcesPortletKeys.RECSUPPORTRESOURCES,
		service = ConfigurationAction.class
	)
 
public class AggregateSupportingResourcesViewPreferencesAction extends DefaultConfigurationAction {
 
	@Override
	public void processAction(PortletConfig portletConfig, ActionRequest actionRequest,ActionResponse actionResponse)
		throws Exception {

		setPreference(actionRequest, "aggregateSupportingResourcesView",ParamUtil.getString(actionRequest, "aggregateSupportingResourcesView"));

		super.processAction(portletConfig, actionRequest, actionResponse);
	}
 
}

