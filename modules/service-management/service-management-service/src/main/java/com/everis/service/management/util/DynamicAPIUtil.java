package com.everis.service.management.util;

import com.everis.service.management.auth.Oauth2;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;

public class DynamicAPIUtil {

	private static final Log logger = LogFactoryUtil.getLog(DynamicAPIUtil.class);

	public static JSONObject get_subservices_cases(String urlEndpoint, String bearerToken) {
		logger.info("Getting Sub Services List from : " + urlEndpoint);
		int responseCode = 0;
		JSONObject jsonResponse = JSONFactoryUtil.createJSONObject();
		JSONArray data = new JSONArray();
		try {
			URL url = new URL(urlEndpoint);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Authorization", "Bearer " + bearerToken);
			conn.setDoOutput(true);
			responseCode = conn.getResponseCode();
			logger.info("GET Response Code: " + responseCode);
			logger.info("GET response: " + conn.getResponseMessage());
			jsonResponse.put("code", responseCode);

			if (responseCode == 200) {
				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String output;
				StringBuffer response = new StringBuffer();

				while ((output = in.readLine()) != null) {
					response.append(output);
				}
				in.close();

				data = new JSONArray(response.toString());
				jsonResponse.put("data", data);
			}
		} catch (MalformedURLException e) {
			logger.error("ERROR creating URL object from:" + urlEndpoint);
		} catch (IOException e) {
			logger.error("ERROR opening a connection: " + e.getMessage());
		}
		return jsonResponse;
	}

	public static JSONObject get_cases_by_user(String environment, long userId, String filters) {
		int responseCode = 0;
		JSONArray data = new JSONArray();
		JSONObject jsonResponse = JSONFactoryUtil.createJSONObject();
		URL url = null;
		try {
			String bearerToken = Oauth2.getAccessToken();
			User user = UserLocalServiceUtil.getUser(userId);
			String emailAddress = user.getEmailAddress();
			logger.info("EMAIL: " + emailAddress);
			if (Validator.isNotNull(filters) && !filters.isEmpty()) {
				logger.info("Getting tickets by email address: " + emailAddress + " whit filters: " + filters);
				url = new URL(HelpdeskUtil.getCustomerCasesUrlByEnvironment(environment) + emailAddress + filters);
			} else {
				logger.info("Getting tickets by email address: " + emailAddress + " without filters");
				url = new URL(HelpdeskUtil.getCustomerCasesUrlByEnvironment(environment) + emailAddress);
			}
			logger.info("URL... " + url);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Authorization", "Bearer " + bearerToken);
			conn.setDoOutput(true);
			responseCode = conn.getResponseCode();
			logger.info("GET Response Code: " + responseCode);
			logger.info("GET response: " + conn.getResponseMessage());
			jsonResponse.put("code", responseCode);

			if (responseCode == 200) {
				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String output;
				StringBuffer response = new StringBuffer();

				while ((output = in.readLine()) != null) {
					response.append(output);
				}
				in.close();

				data = new JSONArray(response.toString());
				jsonResponse.put("data", data);
			}

		} catch (IOException e) {
			logger.error(e);
		} catch (PortalException e) {
			logger.error(e);
		}
		return jsonResponse;
	}

	public static JSONObject create_case(String environment, String title, String description, String firstName,
			String lastName, String contactEmail, String type, String classification, String subserviceName) {

		logger.info("createCase method...");
		logger.info("environment: " + environment + " - title: " + title + " - description: " + description
				+ " - firstName: " + firstName + " - lastName: " + lastName + " - contactEmail: " + contactEmail
				+ " - type: " + type + " - classification: " + classification + " - subserviceName: " + subserviceName);

		org.json.JSONObject helpdeskRequest = new org.json.JSONObject();
		JSONObject jsonResponse = JSONFactoryUtil.createJSONObject();

		// Removing aphostrophes from firstname and lastname
		firstName = removingSpecialFirstNameCharacter(firstName);
		lastName = removingSpecialLastNameCharacter(lastName);
		
		helpdeskRequest.put("Title", title);
		helpdeskRequest.put("Description", description);
		helpdeskRequest.put("ContactFirstName", firstName);
		helpdeskRequest.put("ContactLastName", lastName);
		helpdeskRequest.put("ContactEmail", contactEmail);
		helpdeskRequest.put("SubService", subserviceName);
		helpdeskRequest.put("Type", type);
		helpdeskRequest.put("Classification", classification);

		logger.info("helpdesk... " + helpdeskRequest.toString());

		try {
			String bearerToken = Oauth2.getAccessToken();
			logger.info("creatingRequest...");
			URL url = new URL(HelpdeskUtil.getAddCustomerCaseUrlEnvironment(environment));
			logger.info("url to create ticket: " + url);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setRequestProperty("Authorization", "Bearer " + bearerToken);

			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
			wr.writeBytes(helpdeskRequest.toString());
			wr.flush();
			wr.close();

			int responseCode = conn.getResponseCode();
			logger.info("responseCode... " + responseCode);
			jsonResponse.put("code", responseCode);

			if (responseCode == 200) {
				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String output;
				StringBuffer apiResponse = new StringBuffer();

				while ((output = in.readLine()) != null) {
					apiResponse.append(output);
				}
				in.close();

				logger.info("API Response: " + apiResponse.toString());

				jsonResponse.put("data", apiResponse.toString());
			}

		} catch (MalformedURLException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}
		return jsonResponse;
	}

	private static String removingSpecialFirstNameCharacter(String firstName) {
		if (firstName.contains(StringPool.APOSTROPHE)) {
			firstName = firstName.replace(StringPool.APOSTROPHE, StringPool.BLANK);
			logger.info("encodingSpecialFirstNameCharacter: " + firstName);
		}
		return firstName;
	}

	private static String removingSpecialLastNameCharacter(String lastName) {
		if (lastName.contains(StringPool.APOSTROPHE)) {
			lastName = lastName.replace(StringPool.APOSTROPHE, StringPool.BLANK);
			logger.info("encodingSpecialLastNameCharacter: " + lastName);
		}
		return lastName;
	}

}
