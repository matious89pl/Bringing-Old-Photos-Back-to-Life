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

package com.everis.rec.remediation.tracker.model.impl;

import com.everis.rec.remediation.tracker.model.RemediationTracker;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing RemediationTracker in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class RemediationTrackerCacheModel
	implements CacheModel<RemediationTracker>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof RemediationTrackerCacheModel)) {
			return false;
		}

		RemediationTrackerCacheModel remediationTrackerCacheModel =
			(RemediationTrackerCacheModel)object;

		if (remediationTrackerId ==
				remediationTrackerCacheModel.remediationTrackerId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, remediationTrackerId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(35);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", remediationTrackerId=");
		sb.append(remediationTrackerId);
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
		sb.append(", title=");
		sb.append(title);
		sb.append(", orgSiteId=");
		sb.append(orgSiteId);
		sb.append(", specificParty=");
		sb.append(specificParty);
		sb.append(", category=");
		sb.append(category);
		sb.append(", description=");
		sb.append(description);
		sb.append(", dueDate=");
		sb.append(dueDate);
		sb.append(", dueDateFormated=");
		sb.append(dueDateFormated);
		sb.append(", status=");
		sb.append(status);
		sb.append(", calendarBookingId=");
		sb.append(calendarBookingId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public RemediationTracker toEntityModel() {
		RemediationTrackerImpl remediationTrackerImpl =
			new RemediationTrackerImpl();

		if (uuid == null) {
			remediationTrackerImpl.setUuid("");
		}
		else {
			remediationTrackerImpl.setUuid(uuid);
		}

		remediationTrackerImpl.setRemediationTrackerId(remediationTrackerId);
		remediationTrackerImpl.setGroupId(groupId);
		remediationTrackerImpl.setCompanyId(companyId);
		remediationTrackerImpl.setUserId(userId);

		if (userName == null) {
			remediationTrackerImpl.setUserName("");
		}
		else {
			remediationTrackerImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			remediationTrackerImpl.setCreateDate(null);
		}
		else {
			remediationTrackerImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			remediationTrackerImpl.setModifiedDate(null);
		}
		else {
			remediationTrackerImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (title == null) {
			remediationTrackerImpl.setTitle("");
		}
		else {
			remediationTrackerImpl.setTitle(title);
		}

		remediationTrackerImpl.setOrgSiteId(orgSiteId);

		if (specificParty == null) {
			remediationTrackerImpl.setSpecificParty("");
		}
		else {
			remediationTrackerImpl.setSpecificParty(specificParty);
		}

		if (category == null) {
			remediationTrackerImpl.setCategory("");
		}
		else {
			remediationTrackerImpl.setCategory(category);
		}

		if (description == null) {
			remediationTrackerImpl.setDescription("");
		}
		else {
			remediationTrackerImpl.setDescription(description);
		}

		if (dueDate == null) {
			remediationTrackerImpl.setDueDate("");
		}
		else {
			remediationTrackerImpl.setDueDate(dueDate);
		}

		if (dueDateFormated == Long.MIN_VALUE) {
			remediationTrackerImpl.setDueDateFormated(null);
		}
		else {
			remediationTrackerImpl.setDueDateFormated(
				new Date(dueDateFormated));
		}

		if (status == null) {
			remediationTrackerImpl.setStatus("");
		}
		else {
			remediationTrackerImpl.setStatus(status);
		}

		remediationTrackerImpl.setCalendarBookingId(calendarBookingId);

		remediationTrackerImpl.resetOriginalValues();

		return remediationTrackerImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		remediationTrackerId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		title = objectInput.readUTF();

		orgSiteId = objectInput.readLong();
		specificParty = objectInput.readUTF();
		category = objectInput.readUTF();
		description = objectInput.readUTF();
		dueDate = objectInput.readUTF();
		dueDateFormated = objectInput.readLong();
		status = objectInput.readUTF();

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

		objectOutput.writeLong(remediationTrackerId);

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

		if (title == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(title);
		}

		objectOutput.writeLong(orgSiteId);

		if (specificParty == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(specificParty);
		}

		if (category == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(category);
		}

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (dueDate == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(dueDate);
		}

		objectOutput.writeLong(dueDateFormated);

		if (status == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(status);
		}

		objectOutput.writeLong(calendarBookingId);
	}

	public String uuid;
	public long remediationTrackerId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String title;
	public long orgSiteId;
	public String specificParty;
	public String category;
	public String description;
	public String dueDate;
	public long dueDateFormated;
	public String status;
	public long calendarBookingId;

}