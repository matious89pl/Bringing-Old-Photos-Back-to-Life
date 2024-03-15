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

package com.everis.rec.cpimpact.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.everis.rec.cpimpact.service.http.CPImpactServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class CPImpactSoap implements Serializable {

	public static CPImpactSoap toSoapModel(CPImpact model) {
		CPImpactSoap soapModel = new CPImpactSoap();

		soapModel.setImpactId(model.getImpactId());
		soapModel.setImpactName(model.getImpactName());
		soapModel.setImpactCategory(model.getImpactCategory());

		return soapModel;
	}

	public static CPImpactSoap[] toSoapModels(CPImpact[] models) {
		CPImpactSoap[] soapModels = new CPImpactSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CPImpactSoap[][] toSoapModels(CPImpact[][] models) {
		CPImpactSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CPImpactSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CPImpactSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CPImpactSoap[] toSoapModels(List<CPImpact> models) {
		List<CPImpactSoap> soapModels = new ArrayList<CPImpactSoap>(
			models.size());

		for (CPImpact model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CPImpactSoap[soapModels.size()]);
	}

	public CPImpactSoap() {
	}

	public long getPrimaryKey() {
		return _impactId;
	}

	public void setPrimaryKey(long pk) {
		setImpactId(pk);
	}

	public long getImpactId() {
		return _impactId;
	}

	public void setImpactId(long impactId) {
		_impactId = impactId;
	}

	public String getImpactName() {
		return _impactName;
	}

	public void setImpactName(String impactName) {
		_impactName = impactName;
	}

	public String getImpactCategory() {
		return _impactCategory;
	}

	public void setImpactCategory(String impactCategory) {
		_impactCategory = impactCategory;
	}

	private long _impactId;
	private String _impactName;
	private String _impactCategory;

}