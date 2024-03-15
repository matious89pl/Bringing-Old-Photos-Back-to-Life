package rec.dashboards.config;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.ParamUtil;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;
import rec.dashboards.constants.RecDashboardsPortletKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component(configurationPid = "rec.dashboards.config.RecDashboardsPreferences", configurationPolicy = ConfigurationPolicy.OPTIONAL, immediate = true, property = "javax.portlet.name=" + RecDashboardsPortletKeys.RECDASHBOARDS, service = ConfigurationAction.class)

public class RecDashboardsPreferencesAction extends DefaultConfigurationAction {

    @Override
    public void include(PortletConfig portletConfig, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        if (_log.isInfoEnabled()) {
            _log.info("Rec Dashboard Portlet configuration include");
        }

        httpServletRequest.setAttribute(RecDashboardsPreferences.class.getName(), _recDashboardsPreferences);
        super.include(portletConfig, httpServletRequest, httpServletResponse);
    }

    @Override
    public void processAction(PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
        setPreference(actionRequest, "role", ParamUtil.getString(actionRequest, "role"));
        setPreference(actionRequest, "userId", ParamUtil.getString(actionRequest, "userId"));
        setPreference(actionRequest, "link", ParamUtil.getString(actionRequest, "link"));
        setPreference(actionRequest, "dataset", ParamUtil.getString(actionRequest, "dataset"));
        setPreference(actionRequest, "isPublic", ParamUtil.getString(actionRequest, "isPublic"));
        if (ParamUtil.getString(actionRequest, "height").isEmpty()) {
            setPreference(actionRequest, "height", "700");
        } else {
            setPreference(actionRequest, "height", ParamUtil.getString(actionRequest, "height"));
        }
        super.processAction(portletConfig, actionRequest, actionResponse);
    }

    @Activate
    @Modified
    protected void activate(Map<Object, Object> properties) {
        _recDashboardsPreferences = ConfigurableUtil.createConfigurable(RecDashboardsPreferences.class, properties);
    }

    private static final Log _log = LogFactoryUtil.getLog(RecDashboardsPreferencesAction.class);

    private volatile RecDashboardsPreferences _recDashboardsPreferences;
}
