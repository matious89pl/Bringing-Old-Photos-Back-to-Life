package rec.dashboards.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import rec.dashboards.constants.RecDashboardsPortletKeys;

public class RecDashboardsUtil {

    static final Log logger = LogFactoryUtil.getLog(RecDashboardsUtil.class);



    /**
     * Embedding details controller
     * @return ResponseEntity<String> body contains the JSON object with embedUrl and embedToken
     * @throws JsonProcessingException
     * @throws JsonMappingException
     */
    public static String generateEmbedToken(String env) {

        // Get access token
        String accessToken;
        try {
            accessToken = getAccessTokenUsingServicePrincipal(env);
        } catch (UnirestException | JSONException e) {
            // Log error message
            logger.error("Error getting accessToken", e);
            return null;
        }

        return accessToken;
    }

    /**
     * Get Embed token for single report, multiple datasetIds, and optional target workspaces
     * @param {string} AccessToken
     * @throws UnirestException
     */
    private static String getEmbedToken(String accessToken) throws UnirestException, JSONException {
        JSONObject responseJSON;
        String embedToken;
        HttpResponse<String> response = Unirest.post("https://api.powerbi.com/v1.0/myorg/groups/48b6f7ed-e5f4-4f70-8075-56d540711aae/datasets/3c48aca4-f8c4-4c85-8e5b-ac78e0341f5b/GenerateToken")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer ".concat(accessToken))
                .body("{\r\n    \"accessLevel\": \"View\",\r\n    \"identities\": [\r\n        {\r\n            \"username\": \"awoodham@deloitte.co.uk\",\r\n            \"roles\": [\r\n                \"PAB\"\r\n            ],\r\n            \"datasets\": [\r\n                \"3c48aca4-f8c4-4c85-8e5b-ac78e0341f5b\"\r\n            ]\r\n        }\r\n    ]\r\n}")
                .asString();
        logger.info("response " + response.getBody());
        responseJSON = JSONFactoryUtil.createJSONObject(response.getBody());
        embedToken = String.valueOf(responseJSON.get("token"));
        logger.info("token -> " + embedToken);
        return embedToken;
    }

    /**
     * Acquires access token for the given clientId and app secret
     * @throws UnirestException
     * @throws JSONException
     * @return {string} AccessToken
     */
    private static String getAccessTokenUsingServicePrincipal(String env) throws UnirestException, JSONException {
        JSONObject responseJSON;
        String accessToken;
        HttpResponse<String> response = Unirest.post("https://login.microsoftonline.com/36da45f1-dd2c-4d1f-af13-5abe46b99921/oauth2/token")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .field("client_id", RecDashboardsPortletKeys.getClientId(env))
                .field("grant_type", "client_credentials")
                .field("client_secret", RecDashboardsPortletKeys.getAppSecret(env))
                .field("resource", RecDashboardsPortletKeys.scopeBase)
                .asString();
        logger.info("response " + response.getBody());
        responseJSON = JSONFactoryUtil.createJSONObject(response.getBody());
        accessToken = String.valueOf(responseJSON.get("access_token"));
        logger.info("access_token -> " + accessToken);

        return accessToken;
    }

    public static long getRPAFolder(long repositoryId) {

        DLFolder folder = null;
        long parentFolderId = 0;
        long folderId = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;

        folder = DLFolderLocalServiceUtil.fetchFolder(repositoryId, parentFolderId, RecDashboardsPortletKeys.RPA_FOLDER_NAME);
        if (Validator.isNotNull(folder)) {
            folderId = folder.getFolderId();
        }
        return folderId;
    }
}
