package rec.dashboards.constants;

/**
 * @author scarnero
 */
public class RecDashboardsPortletKeys {

	public static final String RECDASHBOARDS = "rec_dashboards_RecDashboardsPortlet";

	// FOLDER NAME
	public static final String RPA_FOLDER_NAME = "RPA Documents";

	// Set this to true, to show debug statements in console
	public static final boolean DEBUG = true;

	// ========== DO NOT CHANGE ==========
	public static final String authenticationType = "ServicePrincipal";
	public static final String authorityUrl = "https://login.windows.net/common/oauth2/authorize/";
	public static final String scopeBase = "https://analysis.windows.net/powerbi/api";
	// ========== DO NOT CHANGE ==========

	public static String getWorkspaceId(String env) {
		String WorkspaceId = null;
		switch (env) {
		case "poc":
			WorkspaceId = "48b6f7ed-e5f4-4f70-8075-56d540711aae";
			break;
		case "uat":
			WorkspaceId = "b6f600fa-b5a0-48e8-890e-af029bebdefa";
			break;
		case "pre":
			WorkspaceId = "cb4f4cbd-d14a-49c1-9b21-08dd1bb04aac";
			break;
		case "prd":
			WorkspaceId = "847c5307-aa34-4bf0-98e9-feaf813ea1b1";
			break;
		}
		return WorkspaceId;
	}

	public static String getClientId(String env) {
		String ClientId = null;
		switch (env) {
		case "poc":
			ClientId = "82938958-40ac-4af4-aa1e-08ba9bf3137c";
			break;
		case "uat":
			ClientId = "0c0ceec2-dda5-4242-aaf2-b8790c7e0a75";
			break;
		case "pre":
			ClientId = "fb955e08-1ff6-41e3-8d1f-0d07054df4cd";
			break;
		case "prd":
			ClientId = "bb3fc140-9399-44e8-8ae8-5ea0f1316b6b";
			break;
		}
		return ClientId;
	}

	public static String getTenantId(String env) {
		String TenantId = null;
		switch (env) {
		case "poc":
			TenantId = "36da45f1-dd2c-4d1f-af13-5abe46b99921";
			break;
		case "uat":
			TenantId = "36da45f1-dd2c-4d1f-af13-5abe46b99921";
			break;
		case "pre":
			TenantId = "36da45f1-dd2c-4d1f-af13-5abe46b99921";
			break;
		case "prd":
			TenantId = "36da45f1-dd2c-4d1f-af13-5abe46b99921";
			break;
		}
		return TenantId;
	}

	public static String getAppSecret(String env) {
		String AppSecret = null;
		switch (env) {
		case "poc":
			AppSecret = "cmNkWDd1elpXa0w4Q002UHdNK3RrMkE5ODBaVFhtMVFjYXpERWZLdUFEST0=";
			break;
		case "uat":
			AppSecret = "MUJPcysyRjJVbDJpZGJKcldVbkljemsrSmFsZnRFNGJyTW5Gd2pNVHhUMD0=";
			break;
		case "pre":
			AppSecret = "Q3dmMEJUL0pBYTR0OHhwS20vRVdPclpPSVNrZnNLU2xHNnExR3RodkdFWT0=";
			break;
		case "prd":
			AppSecret = "bEV1dGVCTnNRYURxQWtla3N1dmVRNFNGbm1Gd0RraXJ6WkNaQzU3dTV2VT0=";
			break;
		}
		return AppSecret;
	}

}
