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

package com.everis.service.management.model.impl;

import com.everis.service.management.model.helpdesk;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing helpdesk in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class helpdeskCacheModel
	implements CacheModel<helpdesk>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof helpdeskCacheModel)) {
			return false;
		}

		helpdeskCacheModel helpdeskCacheModel = (helpdeskCacheModel)object;

		if (helpdeskId == helpdeskCacheModel.helpdeskId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, helpdeskId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(3);

		sb.append("{helpdeskId=");
		sb.append(helpdeskId);

		return sb.toString();
	}

	@Override
	public helpdesk toEntityModel() {
		helpdeskImpl helpdeskImpl = new helpdeskImpl();

		helpdeskImpl.setHelpdeskId(helpdeskId);

		helpdeskImpl.resetOriginalValues();

		return helpdeskImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		helpdeskId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(helpdeskId);
	}

	public long helpdeskId;

}