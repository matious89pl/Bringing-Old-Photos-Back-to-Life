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

package rec.supporting.resources.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

import rec.supporting.resources.model.supportR;

/**
 * The cache model class for representing supportR in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class supportRCacheModel
	implements CacheModel<supportR>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof supportRCacheModel)) {
			return false;
		}

		supportRCacheModel supportRCacheModel = (supportRCacheModel)object;

		if (supportRId == supportRCacheModel.supportRId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, supportRId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(37);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", supportRId=");
		sb.append(supportRId);
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
		sb.append(", specificParty=");
		sb.append(specificParty);
		sb.append(", orgSiteId=");
		sb.append(orgSiteId);
		sb.append(", type=");
		sb.append(type);
		sb.append(", title=");
		sb.append(title);
		sb.append(", description=");
		sb.append(description);
		sb.append(", dueDate=");
		sb.append(dueDate);
		sb.append(", status=");
		sb.append(status);
		sb.append(", link=");
		sb.append(link);
		sb.append(", displayLink=");
		sb.append(displayLink);
		sb.append(", calendarBookingId=");
		sb.append(calendarBookingId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public supportR toEntityModel() {
		supportRImpl supportRImpl = new supportRImpl();

		if (uuid == null) {
			supportRImpl.setUuid("");
		}
		else {
			supportRImpl.setUuid(uuid);
		}

		supportRImpl.setSupportRId(supportRId);
		supportRImpl.setGroupId(groupId);
		supportRImpl.setCompanyId(companyId);
		supportRImpl.setUserId(userId);

		if (userName == null) {
			supportRImpl.setUserName("");
		}
		else {
			supportRImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			supportRImpl.setCreateDate(null);
		}
		else {
			supportRImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			supportRImpl.setModifiedDate(null);
		}
		else {
			supportRImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (specificParty == null) {
			supportRImpl.setSpecificParty("");
		}
		else {
			supportRImpl.setSpecificParty(specificParty);
		}

		supportRImpl.setOrgSiteId(orgSiteId);

		if (type == null) {
			supportRImpl.setType("");
		}
		else {
			supportRImpl.setType(type);
		}

		if (title == null) {
			supportRImpl.setTitle("");
		}
		else {
			supportRImpl.setTitle(title);
		}

		if (description == null) {
			supportRImpl.setDescription("");
		}
		else {
			supportRImpl.setDescription(description);
		}

		if (dueDate == Long.MIN_VALUE) {
			supportRImpl.setDueDate(null);
		}
		else {
			supportRImpl.setDueDate(new Date(dueDate));
		}

		if (status == null) {
			supportRImpl.setStatus("");
		}
		else {
			supportRImpl.setStatus(status);
		}

		if (link == null) {
			supportRImpl.setLink("");
		}
		else {
			supportRImpl.setLink(link);
		}

		if (displayLink == null) {
			supportRImpl.setDisplayLink("");
		}
		else {
			supportRImpl.setDisplayLink(displayLink);
		}

		supportRImpl.setCalendarBookingId(calendarBookingId);

		supportRImpl.resetOriginalValues();

		return supportRImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		supportRId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		specificParty = objectInput.readUTF();

		orgSiteId = objectInput.readLong();
		type = objectInput.readUTF();
		title = objectInput.readUTF();
		description = objectInput.readUTF();
		dueDate = objectInput.readLong();
		status = objectInput.readUTF();
		link = objectInput.readUTF();
		displayLink = objectInput.readUTF();

		calendarBookingId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(supportRId);

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

		if (specificParty == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(specificParty);
		}

		objectOutput.writeLong(orgSiteId);

		if (type == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(type);
		}

		if (title == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeLong(dueDate);

		if (status == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(status);
		}

		if (link == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(link);
		}

		if (displayLink == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(displayLink);
		}

		objectOutput.writeLong(calendarBookingId);
	}

	public String uuid;
	public long supportRId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String specificParty;
	public long orgSiteId;
	public String type;
	public String title;
	public String description;
	public long dueDate;
	public String status;
	public String link;
	public String displayLink;
	public long calendarBookingId;

}