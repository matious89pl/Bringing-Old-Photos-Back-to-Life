package com.everis.service.management.auth;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Oauth2 {

	private static final Log logger = LogFactoryUtil.getLog(Oauth2.class);

	private static final String CLIENT_ID = "19e229ae-824d-484c-85f2-45ff065cf048";

	private static final String CLIENT_SECRET = "4c4uU2_uyCi2m4v-Rfk-rs6QboI103SBU~";

	private static final String ACCESS_TOKEN_URL = "https://login.microsoftonline.com/common/oauth2/token";

	private static final String CALLBACK_URL = "https://localhost";

	private static final String GRANT_TYPE = "password";

	private static final String USERNAME = "API.caseforms@recmanager.co.uk";

	private static final String PASSWORD = "Sefgy68!";

	private static final String RESOURCE = "19e229ae-824d-484c-85f2-45ff065cf048";

	public static JSONObject getToken() {

		JSONObject jsonResponse = JSONFactoryUtil.createJSONObject();

		StringBuilder urlParametersSB = new StringBuilder();
		urlParametersSB.append("grant_type=" + GRANT_TYPE);
		urlParametersSB.append("&username=" + USERNAME);
		urlParametersSB.append("&password=" + PASSWORD);
		urlParametersSB.append("&client_id=" + CLIENT_ID);
		urlParametersSB.append("&resource=" + RESOURCE);
		urlParametersSB.append("&client_secret=" + CLIENT_SECRET);
		urlParametersSB.append("&redirect_uri=" + CALLBACK_URL);
		
		String urlParameters = urlParametersSB.toString();
		byte[] postData = urlParameters.getBytes( StandardCharsets.UTF_8 );
		
		try {
			URL url = new URL(ACCESS_TOKEN_URL);
			logger.info("URL... " + url);
			logger.info("jsonRequest... " + urlParameters);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty( "charset", "utf-8");
			conn.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
			wr.write( postData );
			wr.flush();
			wr.close();

			int responseCode = conn.getResponseCode();
			logger.debug("responseCode... " + responseCode);

			if (responseCode == 200) {
				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String output;
				StringBuffer apiResponse = new StringBuffer();

				while ((output = in.readLine()) != null) {
					apiResponse.append(output);
				}
				in.close();

				logger.debug("API Response: " + apiResponse.toString());
				
				jsonResponse = JSONFactoryUtil.createJSONObject(apiResponse.toString());
				logger.debug("jsonResponse... " + jsonResponse);
				
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonResponse;
	}
	
	public static String getAccessToken() {
		JSONObject dataToken = getToken();
		logger.debug("access_token: " + (String) dataToken.get("access_token"));
		return (String) dataToken.get("access_token");
	}

}
