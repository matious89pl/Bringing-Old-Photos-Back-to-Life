/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.everis.service.management.service.impl;

import com.everis.service.management.service.base.helpdeskServiceBaseImpl;
import com.everis.service.management.util.DynamicAPIUtil;
import com.everis.service.management.util.HelpdeskUtil;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.osgi.service.component.annotations.Component;

@Component(property = { "json.web.service.context.name=servicemanagement",
		"json.web.service.context.path=helpdesk" }, service = AopService.class)
public class helpdeskServiceImpl extends helpdeskServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use
	 * <code>com.everis.service.management.service.helpdeskServiceUtil</code> to
	 * access the helpdesk remote service.
	 */

	private static final Log logger = LogFactoryUtil.getLog(helpdeskServiceImpl.class);
	
	@JSONWebService(value = "get_sub_services_by_environment", method = "POST")
	public 	JSONObject getSubServices(String environment) {
		return HelpdeskUtil.gettingSubServicesFromDynamicAPI(environment);
	}
	
	@JSONWebService(value = "get_tickets_by_userId", method = "GET")
	public JSONObject getCasesByUserEmail(String environment, long userId, String filters) {
		return DynamicAPIUtil.get_cases_by_user(environment, userId, filters);
	}
	
	@JSONWebService(value = "create_new_ticket", method = "POST")
	public JSONObject createCase(String environment, String title, String description, String firstName, String lastName,
			String contactEmail, String type, String classification, String subserviceName) {
		return DynamicAPIUtil.create_case(environment, title, description, firstName, lastName, contactEmail, type, classification, subserviceName);
	}

}