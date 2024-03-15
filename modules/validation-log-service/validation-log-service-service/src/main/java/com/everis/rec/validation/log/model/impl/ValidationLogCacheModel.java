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

package com.everis.rec.validation.log.model.impl;

import com.everis.rec.validation.log.model.ValidationLog;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ValidationLog in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ValidationLogCacheModel
	implements CacheModel<ValidationLog>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ValidationLogCacheModel)) {
			return false;
		}

		ValidationLogCacheModel validationLogCacheModel =
			(ValidationLogCacheModel)object;

		if (validationLogId == validationLogCacheModel.validationLogId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, validationLogId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{validationLogId=");
		sb.append(validationLogId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", fileName=");
		sb.append(fileName);
		sb.append(", uploadedBy=");
		sb.append(uploadedBy);
		sb.append(", uploadedFrom=");
		sb.append(uploadedFrom);
		sb.append(", folderId=");
		sb.append(folderId);
		sb.append(", logReason=");
		sb.append(logReason);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ValidationLog toEntityModel() {
		ValidationLogImpl validationLogImpl = new ValidationLogImpl();

		validationLogImpl.setValidationLogId(validationLogId);
		validationLogImpl.setCompanyId(companyId);
		validationLogImpl.setGroupId(groupId);

		if (createDate == Long.MIN_VALUE) {
			validationLogImpl.setCreateDate(null);
		}
		else {
			validationLogImpl.setCreateDate(new Date(createDate));
		}

		if (fileName == null) {
			validationLogImpl.setFileName("");
		}
		else {
			validationLogImpl.setFileName(fileName);
		}

		if (uploadedBy == null) {
			validationLogImpl.setUploadedBy("");
		}
		else {
			validationLogImpl.setUploadedBy(uploadedBy);
		}

		if (uploadedFrom == null) {
			validationLogImpl.setUploadedFrom("");
		}
		else {
			validationLogImpl.setUploadedFrom(uploadedFrom);
		}

		validationLogImpl.setFolderId(folderId);

		if (logReason == null) {
			validationLogImpl.setLogReason("");
		}
		else {
			validationLogImpl.setLogReason(logReason);
		}

		validationLogImpl.resetOriginalValues();

		return validationLogImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		validationLogId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();
		createDate = objectInput.readLong();
		fileName = objectInput.readUTF();
		uploadedBy = objectInput.readUTF();
		uploadedFrom = objectInput.readUTF();

		folderId = objectInput.readLong();
		logReason = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(validationLogId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(groupId);
		objectOutput.writeLong(createDate);

		if (fileName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(fileName);
		}

		if (uploadedBy == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uploadedBy);
		}

		if (uploadedFrom == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uploadedFrom);
		}

		objectOutput.writeLong(folderId);

		if (logReason == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(logReason);
		}
	}

	public long validationLogId;
	public long companyId;
	public long groupId;
	public long createDate;
	public String fileName;
	public String uploadedBy;
	public String uploadedFrom;
	public long folderId;
	public String logReason;

}