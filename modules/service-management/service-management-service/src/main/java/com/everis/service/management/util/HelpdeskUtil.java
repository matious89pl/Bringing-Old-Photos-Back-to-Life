package com.everis.service.management.util;

import com.everis.service.management.auth.Oauth2;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class HelpdeskUtil {

	private static final Log logger = LogFactoryUtil.getLog(HelpdeskUtil.class);

	public static JSONObject gettingSubServicesFromDynamicAPI(String environment) {
		String bearerToken = Oauth2.getAccessToken();
		String urlEndpoint = getSubserviceUrlByEnvironment(environment);
		JSONObject jsonResponse = DynamicAPIUtil.get_subservices_cases(urlEndpoint, bearerToken);
		return jsonResponse;
	}

	public static String getSubserviceUrlByEnvironment(String environment) {
		switch (environment) {
		case Constants.DEV:
			return Constants.SUB_SERVICES_DEV;
		case Constants.UAT:
			return Constants.SUB_SERVICES_UAT;
		case Constants.PRD:
			return Constants.SUB_SERVICES_PRD;
		}
		return StringPool.BLANK;
	}

	public static String getCustomerCasesUrlByEnvironment(String environment) {
		switch (environment) {
		case Constants.DEV:
			return Constants.GET_CASES_USER_DEV;
		case Constants.UAT:
			return Constants.GET_CASES_USER_UAT;
		case Constants.PRD:
			return Constants.GET_CASES_USER_PRD;
		}
		return StringPool.BLANK;
	}

	public static String getAddCustomerCaseUrlEnvironment(String environment) {
		switch (environment) {
		case Constants.DEV:
			return Constants.ADD_CASE_USER_DEV;
		case Constants.UAT:
			return Constants.ADD_CASE_USER_UAT;
		case Constants.PRD:
			return Constants.ADD_CASE_USER_PRD;
		}
		return StringPool.BLANK;
	}
}
