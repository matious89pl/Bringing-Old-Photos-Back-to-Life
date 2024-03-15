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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link CPImpact}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CPImpact
 * @generated
 */
public class CPImpactWrapper
	extends BaseModelWrapper<CPImpact>
	implements CPImpact, ModelWrapper<CPImpact> {

	public CPImpactWrapper(CPImpact cpImpact) {
		super(cpImpact);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("impactId", getImpactId());
		attributes.put("impactName", getImpactName());
		attributes.put("impactCategory", getImpactCategory());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long impactId = (Long)attributes.get("impactId");

		if (impactId != null) {
			setImpactId(impactId);
		}

		String impactName = (String)attributes.get("impactName");

		if (impactName != null) {
			setImpactName(impactName);
		}

		String impactCategory = (String)attributes.get("impactCategory");

		if (impactCategory != null) {
			setImpactCategory(impactCategory);
		}
	}

	/**
	 * Returns the impact category of this cp impact.
	 *
	 * @return the impact category of this cp impact
	 */
	@Override
	public String getImpactCategory() {
		return model.getImpactCategory();
	}

	/**
	 * Returns the impact ID of this cp impact.
	 *
	 * @return the impact ID of this cp impact
	 */
	@Override
	public long getImpactId() {
		return model.getImpactId();
	}

	/**
	 * Returns the impact name of this cp impact.
	 *
	 * @return the impact name of this cp impact
	 */
	@Override
	public String getImpactName() {
		return model.getImpactName();
	}

	/**
	 * Returns the primary key of this cp impact.
	 *
	 * @return the primary key of this cp impact
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the impact category of this cp impact.
	 *
	 * @param impactCategory the impact category of this cp impact
	 */
	@Override
	public void setImpactCategory(String impactCategory) {
		model.setImpactCategory(impactCategory);
	}

	/**
	 * Sets the impact ID of this cp impact.
	 *
	 * @param impactId the impact ID of this cp impact
	 */
	@Override
	public void setImpactId(long impactId) {
		model.setImpactId(impactId);
	}

	/**
	 * Sets the impact name of this cp impact.
	 *
	 * @param impactName the impact name of this cp impact
	 */
	@Override
	public void setImpactName(String impactName) {
		model.setImpactName(impactName);
	}

	/**
	 * Sets the primary key of this cp impact.
	 *
	 * @param primaryKey the primary key of this cp impact
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	@Override
	protected CPImpactWrapper wrap(CPImpact cpImpact) {
		return new CPImpactWrapper(cpImpact);
	}

}