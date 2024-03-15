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

package com.everis.rec.service.activity.logs.model.impl;

import com.everis.rec.service.activity.logs.model.RecLog;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing RecLog in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class RecLogCacheModel implements CacheModel<RecLog>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof RecLogCacheModel)) {
			return false;
		}

		RecLogCacheModel recLogCacheModel = (RecLogCacheModel)object;

		if (activityLogId == recLogCacheModel.activityLogId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, activityLogId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", activityLogId=");
		sb.append(activityLogId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", logMessage=");
		sb.append(logMessage);
		sb.append(", className=");
		sb.append(className);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", comments=");
		sb.append(comments);
		sb.append(", type=");
		sb.append(type);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public RecLog toEntityModel() {
		RecLogImpl recLogImpl = new RecLogImpl();

		if (uuid == null) {
			recLogImpl.setUuid("");
		}
		else {
			recLogImpl.setUuid(uuid);
		}

		recLogImpl.setActivityLogId(activityLogId);
		recLogImpl.setGroupId(groupId);

		if (createDate == Long.MIN_VALUE) {
			recLogImpl.setCreateDate(null);
		}
		else {
			recLogImpl.setCreateDate(new Date(createDate));
		}

		if (logMessage == null) {
			recLogImpl.setLogMessage("");
		}
		else {
			recLogImpl.setLogMessage(logMessage);
		}

		if (className == null) {
			recLogImpl.setClassName("");
		}
		else {
			recLogImpl.setClassName(className);
		}

		recLogImpl.setClassPK(classPK);

		if (comments == null) {
			recLogImpl.setComments("");
		}
		else {
			recLogImpl.setComments(comments);
		}

		if (type == null) {
			recLogImpl.setType("");
		}
		else {
			recLogImpl.setType(type);
		}

		recLogImpl.resetOriginalValues();

		return recLogImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		activityLogId = objectInput.readLong();

		groupId = objectInput.readLong();
		createDate = objectInput.readLong();
		logMessage = objectInput.readUTF();
		className = objectInput.readUTF();

		classPK = objectInput.readLong();
		comments = objectInput.readUTF();
		type = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(activityLogId);

		objectOutput.writeLong(groupId);
		objectOutput.writeLong(createDate);

		if (logMessage == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(logMessage);
		}

		if (className == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(className);
		}

		objectOutput.writeLong(classPK);

		if (comments == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(comments);
		}

		if (type == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(type);
		}
	}

	public String uuid;
	public long activityLogId;
	public long groupId;
	public long createDate;
	public String logMessage;
	public String className;
	public long classPK;
	public String comments;
	public String type;

}