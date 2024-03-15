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

package com.everis.rec.rfilogs.model.impl;

import com.everis.rec.rfilogs.model.RfiLogs;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing RfiLogs in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class RfiLogsCacheModel implements CacheModel<RfiLogs>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof RfiLogsCacheModel)) {
			return false;
		}

		RfiLogsCacheModel rfiLogsCacheModel = (RfiLogsCacheModel)object;

		if (rfilogId == rfiLogsCacheModel.rfilogId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, rfilogId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(35);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", rfilogId=");
		sb.append(rfilogId);
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
		sb.append(", reqId=");
		sb.append(reqId);
		sb.append(", specificParty=");
		sb.append(specificParty);
		sb.append(", orgSiteId=");
		sb.append(orgSiteId);
		sb.append(", title=");
		sb.append(title);
		sb.append(", dueDate=");
		sb.append(dueDate);
		sb.append(", status=");
		sb.append(status);
		sb.append(", reqDesc=");
		sb.append(reqDesc);
		sb.append(", rationale=");
		sb.append(rationale);
		sb.append(", dueDateFormated=");
		sb.append(dueDateFormated);
		sb.append(", calendarBookingId=");
		sb.append(calendarBookingId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public RfiLogs toEntityModel() {
		RfiLogsImpl rfiLogsImpl = new RfiLogsImpl();

		if (uuid == null) {
			rfiLogsImpl.setUuid("");
		}
		else {
			rfiLogsImpl.setUuid(uuid);
		}

		rfiLogsImpl.setRfilogId(rfilogId);
		rfiLogsImpl.setCompanyId(companyId);
		rfiLogsImpl.setUserId(userId);

		if (userName == null) {
			rfiLogsImpl.setUserName("");
		}
		else {
			rfiLogsImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			rfiLogsImpl.setCreateDate(null);
		}
		else {
			rfiLogsImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			rfiLogsImpl.setModifiedDate(null);
		}
		else {
			rfiLogsImpl.setModifiedDate(new Date(modifiedDate));
		}

		rfiLogsImpl.setReqId(reqId);

		if (specificParty == null) {
			rfiLogsImpl.setSpecificParty("");
		}
		else {
			rfiLogsImpl.setSpecificParty(specificParty);
		}

		rfiLogsImpl.setOrgSiteId(orgSiteId);

		if (title == null) {
			rfiLogsImpl.setTitle("");
		}
		else {
			rfiLogsImpl.setTitle(title);
		}

		if (dueDate == null) {
			rfiLogsImpl.setDueDate("");
		}
		else {
			rfiLogsImpl.setDueDate(dueDate);
		}

		if (status == null) {
			rfiLogsImpl.setStatus("");
		}
		else {
			rfiLogsImpl.setStatus(status);
		}

		if (reqDesc == null) {
			rfiLogsImpl.setReqDesc("");
		}
		else {
			rfiLogsImpl.setReqDesc(reqDesc);
		}

		if (rationale == null) {
			rfiLogsImpl.setRationale("");
		}
		else {
			rfiLogsImpl.setRationale(rationale);
		}

		if (dueDateFormated == Long.MIN_VALUE) {
			rfiLogsImpl.setDueDateFormated(null);
		}
		else {
			rfiLogsImpl.setDueDateFormated(new Date(dueDateFormated));
		}

		rfiLogsImpl.setCalendarBookingId(calendarBookingId);

		rfiLogsImpl.resetOriginalValues();

		return rfiLogsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		rfilogId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		reqId = objectInput.readLong();
		specificParty = objectInput.readUTF();

		orgSiteId = objectInput.readLong();
		title = objectInput.readUTF();
		dueDate = objectInput.readUTF();
		status = objectInput.readUTF();
		reqDesc = objectInput.readUTF();
		rationale = objectInput.readUTF();
		dueDateFormated = objectInput.readLong();

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

		objectOutput.writeLong(rfilogId);

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

		objectOutput.writeLong(reqId);

		if (specificParty == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(specificParty);
		}

		objectOutput.writeLong(orgSiteId);

		if (title == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (dueDate == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(dueDate);
		}

		if (status == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(status);
		}

		if (reqDesc == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(reqDesc);
		}

		if (rationale == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(rationale);
		}

		objectOutput.writeLong(dueDateFormated);

		objectOutput.writeLong(calendarBookingId);
	}

	public String uuid;
	public long rfilogId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long reqId;
	public String specificParty;
	public long orgSiteId;
	public String title;
	public String dueDate;
	public String status;
	public String reqDesc;
	public String rationale;
	public long dueDateFormated;
	public long calendarBookingId;

}