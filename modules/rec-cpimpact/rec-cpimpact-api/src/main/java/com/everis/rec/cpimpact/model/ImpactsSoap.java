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
 * This class is used by SOAP remote services, specifically {@link com.everis.rec.cpimpact.service.http.ImpactsServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class ImpactsSoap implements Serializable {

	public static ImpactsSoap toSoapModel(Impacts model) {
		ImpactsSoap soapModel = new ImpactsSoap();

		soapModel.setImpactId(model.getImpactId());
		soapModel.setImpactName(model.getImpactName());
		soapModel.setImpactCategory(model.getImpactCategory());

		return soapModel;
	}

	public static ImpactsSoap[] toSoapModels(Impacts[] models) {
		ImpactsSoap[] soapModels = new ImpactsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ImpactsSoap[][] toSoapModels(Impacts[][] models) {
		ImpactsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ImpactsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ImpactsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ImpactsSoap[] toSoapModels(List<Impacts> models) {
		List<ImpactsSoap> soapModels = new ArrayList<ImpactsSoap>(
			models.size());

		for (Impacts model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ImpactsSoap[soapModels.size()]);
	}

	public ImpactsSoap() {
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