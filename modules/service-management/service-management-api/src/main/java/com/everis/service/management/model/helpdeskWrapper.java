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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link helpdesk}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see helpdesk
 * @generated
 */
public class helpdeskWrapper
	extends BaseModelWrapper<helpdesk>
	implements helpdesk, ModelWrapper<helpdesk> {

	public helpdeskWrapper(helpdesk helpdesk) {
		super(helpdesk);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("helpdeskId", getHelpdeskId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long helpdeskId = (Long)attributes.get("helpdeskId");

		if (helpdeskId != null) {
			setHelpdeskId(helpdeskId);
		}
	}

	/**
	 * Returns the helpdesk ID of this helpdesk.
	 *
	 * @return the helpdesk ID of this helpdesk
	 */
	@Override
	public long getHelpdeskId() {
		return model.getHelpdeskId();
	}

	/**
	 * Returns the primary key of this helpdesk.
	 *
	 * @return the primary key of this helpdesk
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
	 * Sets the helpdesk ID of this helpdesk.
	 *
	 * @param helpdeskId the helpdesk ID of this helpdesk
	 */
	@Override
	public void setHelpdeskId(long helpdeskId) {
		model.setHelpdeskId(helpdeskId);
	}

	/**
	 * Sets the primary key of this helpdesk.
	 *
	 * @param primaryKey the primary key of this helpdesk
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	@Override
	protected helpdeskWrapper wrap(helpdesk helpdesk) {
		return new helpdeskWrapper(helpdesk);
	}

}