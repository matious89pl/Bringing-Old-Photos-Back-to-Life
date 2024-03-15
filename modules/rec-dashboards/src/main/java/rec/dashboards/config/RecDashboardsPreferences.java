package rec.dashboards.config;

import aQute.bnd.annotation.metatype.Meta;

@Meta.OCD(
    id = "rec.dashboards.config.RecDashboardsPreferences"
)

public interface RecDashboardsPreferences {

    @Meta.AD(required = false)
    public String dashboardRole();

    @Meta.AD(required = false)
    public String dashboardUserId();

    @Meta.AD(required = false)
    public String dashboardLink();
    
    @Meta.AD(required = false)
    public String dashboardDataset();

    @Meta.AD(required = false)
    public String dashboardHeight();

    @Meta.AD(required = false)
    public String dashboardPublic();

}
