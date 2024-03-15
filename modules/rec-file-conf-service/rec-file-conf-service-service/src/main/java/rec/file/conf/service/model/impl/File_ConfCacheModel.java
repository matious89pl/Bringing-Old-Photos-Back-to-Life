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

package rec.file.conf.service.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

import rec.file.conf.service.model.File_Conf;

/**
 * The cache model class for representing File_Conf in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class File_ConfCacheModel
	implements CacheModel<File_Conf>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof File_ConfCacheModel)) {
			return false;
		}

		File_ConfCacheModel file_ConfCacheModel = (File_ConfCacheModel)object;

		if (docConfigId == file_ConfCacheModel.docConfigId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, docConfigId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", docConfigId=");
		sb.append(docConfigId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", docConfName=");
		sb.append(docConfName);
		sb.append(", docFileType=");
		sb.append(docFileType);
		sb.append(", docConfigJSON=");
		sb.append(docConfigJSON);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public File_Conf toEntityModel() {
		File_ConfImpl file_ConfImpl = new File_ConfImpl();

		if (uuid == null) {
			file_ConfImpl.setUuid("");
		}
		else {
			file_ConfImpl.setUuid(uuid);
		}

		file_ConfImpl.setDocConfigId(docConfigId);
		file_ConfImpl.setGroupId(groupId);
		file_ConfImpl.setCompanyId(companyId);
		file_ConfImpl.setUserId(userId);

		if (userName == null) {
			file_ConfImpl.setUserName("");
		}
		else {
			file_ConfImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			file_ConfImpl.setCreateDate(null);
		}
		else {
			file_ConfImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			file_ConfImpl.setModifiedDate(null);
		}
		else {
			file_ConfImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (docConfName == null) {
			file_ConfImpl.setDocConfName("");
		}
		else {
			file_ConfImpl.setDocConfName(docConfName);
		}

		if (docFileType == null) {
			file_ConfImpl.setDocFileType("");
		}
		else {
			file_ConfImpl.setDocFileType(docFileType);
		}

		if (docConfigJSON == null) {
			file_ConfImpl.setDocConfigJSON("");
		}
		else {
			file_ConfImpl.setDocConfigJSON(docConfigJSON);
		}

		file_ConfImpl.resetOriginalValues();

		return file_ConfImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		docConfigId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		docConfName = objectInput.readUTF();
		docFileType = objectInput.readUTF();
		docConfigJSON = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(docConfigId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (docConfName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(docConfName);
		}

		if (docFileType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(docFileType);
		}

		if (docConfigJSON == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(docConfigJSON);
		}
	}

	public String uuid;
	public long docConfigId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String docConfName;
	public String docFileType;
	public String docConfigJSON;

}