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

package com.everis.messages.service.builder.model.impl;

import com.everis.messages.service.builder.model.Messages;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Messages in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class MessagesCacheModel
	implements CacheModel<Messages>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof MessagesCacheModel)) {
			return false;
		}

		MessagesCacheModel messagesCacheModel = (MessagesCacheModel)object;

		if (notificationEngineId == messagesCacheModel.notificationEngineId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, notificationEngineId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", notificationEngineId=");
		sb.append(notificationEngineId);
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
		sb.append(", name=");
		sb.append(name);
		sb.append(", subject=");
		sb.append(subject);
		sb.append(", body=");
		sb.append(body);
		sb.append(", status=");
		sb.append(status);
		sb.append(", tags=");
		sb.append(tags);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Messages toEntityModel() {
		MessagesImpl messagesImpl = new MessagesImpl();

		if (uuid == null) {
			messagesImpl.setUuid("");
		}
		else {
			messagesImpl.setUuid(uuid);
		}

		messagesImpl.setNotificationEngineId(notificationEngineId);
		messagesImpl.setGroupId(groupId);
		messagesImpl.setCompanyId(companyId);
		messagesImpl.setUserId(userId);

		if (userName == null) {
			messagesImpl.setUserName("");
		}
		else {
			messagesImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			messagesImpl.setCreateDate(null);
		}
		else {
			messagesImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			messagesImpl.setModifiedDate(null);
		}
		else {
			messagesImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			messagesImpl.setName("");
		}
		else {
			messagesImpl.setName(name);
		}

		if (subject == null) {
			messagesImpl.setSubject("");
		}
		else {
			messagesImpl.setSubject(subject);
		}

		if (body == null) {
			messagesImpl.setBody("");
		}
		else {
			messagesImpl.setBody(body);
		}

		messagesImpl.setStatus(status);

		if (tags == null) {
			messagesImpl.setTags("");
		}
		else {
			messagesImpl.setTags(tags);
		}

		messagesImpl.resetOriginalValues();

		return messagesImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		notificationEngineId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
		subject = objectInput.readUTF();
		body = objectInput.readUTF();

		status = objectInput.readBoolean();
		tags = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(notificationEngineId);

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

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (subject == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(subject);
		}

		if (body == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(body);
		}

		objectOutput.writeBoolean(status);

		if (tags == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(tags);
		}
	}

	public String uuid;
	public long notificationEngineId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String subject;
	public String body;
	public boolean status;
	public String tags;

}