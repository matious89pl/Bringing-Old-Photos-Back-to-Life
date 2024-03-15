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

package com.everis.rec.impacts.model.impl;

import com.everis.rec.impacts.model.Impacts;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Impacts in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ImpactsCacheModel implements CacheModel<Impacts>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ImpactsCacheModel)) {
			return false;
		}

		ImpactsCacheModel impactsCacheModel = (ImpactsCacheModel)object;

		if (impactId == impactsCacheModel.impactId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, impactId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{impactId=");
		sb.append(impactId);
		sb.append(", impactName=");
		sb.append(impactName);
		sb.append(", impactCategory=");
		sb.append(impactCategory);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Impacts toEntityModel() {
		ImpactsImpl impactsImpl = new ImpactsImpl();

		impactsImpl.setImpactId(impactId);

		if (impactName == null) {
			impactsImpl.setImpactName("");
		}
		else {
			impactsImpl.setImpactName(impactName);
		}

		if (impactCategory == null) {
			impactsImpl.setImpactCategory("");
		}
		else {
			impactsImpl.setImpactCategory(impactCategory);
		}

		impactsImpl.resetOriginalValues();

		return impactsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		impactId = objectInput.readLong();
		impactName = objectInput.readUTF();
		impactCategory = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(impactId);

		if (impactName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(impactName);
		}

		if (impactCategory == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(impactCategory);
		}
	}

	public long impactId;
	public String impactName;
	public String impactCategory;

}