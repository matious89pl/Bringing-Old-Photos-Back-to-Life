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

package com.everis.service.management.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.everis.service.management.service.http.helpdeskServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class helpdeskSoap implements Serializable {

	public static helpdeskSoap toSoapModel(helpdesk model) {
		helpdeskSoap soapModel = new helpdeskSoap();

		soapModel.setHelpdeskId(model.getHelpdeskId());

		return soapModel;
	}

	public static helpdeskSoap[] toSoapModels(helpdesk[] models) {
		helpdeskSoap[] soapModels = new helpdeskSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static helpdeskSoap[][] toSoapModels(helpdesk[][] models) {
		helpdeskSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new helpdeskSoap[models.length][models[0].length];
		}
		else {
			soapModels = new helpdeskSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static helpdeskSoap[] toSoapModels(List<helpdesk> models) {
		List<helpdeskSoap> soapModels = new ArrayList<helpdeskSoap>(
			models.size());

		for (helpdesk model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new helpdeskSoap[soapModels.size()]);
	}

	public helpdeskSoap() {
	}

	public long getPrimaryKey() {
		return _helpdeskId;
	}

	public void setPrimaryKey(long pk) {
		setHelpdeskId(pk);
	}

	public long getHelpdeskId() {
		return _helpdeskId;
	}

	public void setHelpdeskId(long helpdeskId) {
		_helpdeskId = helpdeskId;
	}

	private long _helpdeskId;

}