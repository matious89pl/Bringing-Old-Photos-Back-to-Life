package rec.dashboards.portlet;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Modified;
import rec.dashboards.config.RecDashboardsPreferences;
import rec.dashboards.constants.RecDashboardsPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import rec.dashboards.util.RecDashboardsUtil;

import java.io.IOException;
import java.util.Map;

/**
 * @author scarnero
 */
@Component(
        configurationPid = "rec.dashboards.config.RecDashboardsPreferences",
        immediate = true,
        property = {
                "com.liferay.portlet.display-category=category.sample",
                "com.liferay.portlet.header-portlet-css=/css/main.css",
                "com.liferay.portlet.instanceable=true",
                "javax.portlet.display-name=RecDashboards",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.config-template=/config.jsp",
                "javax.portlet.init-param.view-template=/view.jsp",
                "javax.portlet.name=" + RecDashboardsPortletKeys.RECDASHBOARDS,
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
)
public class RecDashboardsPortlet extends MVCPortlet {

    @Override
    public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
        ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
        String accessToken;
        String env = "poc";
        long folderId = 0;
        if (_log.isInfoEnabled()) {
            _log.info("Rec Dashboard Portlet render");
        }

        renderRequest.setAttribute(RecDashboardsPreferences.class.getName(), _dashboardsPreferences);

        String urlHome = themeDisplay.getURLHome();
        if (urlHome.contains("dev")) {
            env = "poc";
        } else if (urlHome.contains("uat")) {
            env = "uat";
        } else if (urlHome.contains("preprd")) {
            env = "pre";
        } else if (urlHome.contains("recportal.co.uk")) {
            env = "prd";
        }
        renderRequest.setAttribute("env", env);

        accessToken = RecDashboardsUtil.generateEmbedToken(env);
        _log.info("Controller -> " + accessToken);
        renderRequest.setAttribute("access_token", accessToken);

        folderId = RecDashboardsUtil.getRPAFolder(themeDisplay.getScopeGroupId());
        _log.info("FolderId -> " + folderId);
        renderRequest.setAttribute("folderId", folderId);

        super.doView(renderRequest, renderResponse);
    }

    @Activate
    @Modified
    protected void activate(Map<Object, Object> properties) {
        _dashboardsPreferences = ConfigurableUtil.createConfigurable(
                RecDashboardsPreferences.class, properties);
    }

    private static final Log _log = LogFactoryUtil.getLog(
            RecDashboardsPortlet.class);

    private volatile RecDashboardsPreferences _dashboardsPreferences;

}