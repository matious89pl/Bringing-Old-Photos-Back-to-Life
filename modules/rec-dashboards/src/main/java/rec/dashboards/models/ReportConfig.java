package rec.dashboards.models;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * Properties for embedding the report
 */
public class ReportConfig {
    static final Log logger = LogFactoryUtil.getLog(ReportConfig.class);

    public String reportId = "";

    public String embedUrl = "";

    public String reportName = "";

    public Boolean isEffectiveIdentityRolesRequired = false;

    public Boolean isEffectiveIdentityRequired = false;

    public Boolean enableRLS = false;

    public String username;

    public String roles;

    public JSONObject getJSONObject() {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();
        jsonObj.put("reportId", reportId);
        jsonObj.put("embedUrl", embedUrl);
        jsonObj.put("reportName", reportName);
        return jsonObj;
    }
}
