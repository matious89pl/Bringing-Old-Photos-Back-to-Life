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

package com.everis.rec.maintenanceactivity.model.impl;

import com.everis.rec.maintenanceactivity.model.MaintenanceActivity;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing MaintenanceActivity in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class MaintenanceActivityCacheModel
	implements CacheModel<MaintenanceActivity>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof MaintenanceActivityCacheModel)) {
			return false;
		}

		MaintenanceActivityCacheModel maintenanceActivityCacheModel =
			(MaintenanceActivityCacheModel)object;

		if (maintenanceactivityId ==
				maintenanceActivityCacheModel.maintenanceactivityId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, maintenanceactivityId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", maintenanceactivityId=");
		sb.append(maintenanceactivityId);
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
		sb.append(", activityTitle=");
		sb.append(activityTitle);
		sb.append(", dueDate=");
		sb.append(dueDate);
		sb.append(", status=");
		sb.append(status);
		sb.append(", orgSiteId=");
		sb.append(orgSiteId);
		sb.append(", specificParty=");
		sb.append(specificParty);
		sb.append(", dueDateFormated=");
		sb.append(dueDateFormated);
		sb.append(", calendarBookingId=");
		sb.append(calendarBookingId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public MaintenanceActivity toEntityModel() {
		MaintenanceActivityImpl maintenanceActivityImpl =
			new MaintenanceActivityImpl();

		if (uuid == null) {
			maintenanceActivityImpl.setUuid("");
		}
		else {
			maintenanceActivityImpl.setUuid(uuid);
		}

		maintenanceActivityImpl.setMaintenanceactivityId(maintenanceactivityId);
		maintenanceActivityImpl.setCompanyId(companyId);
		maintenanceActivityImpl.setUserId(userId);

		if (userName == null) {
			maintenanceActivityImpl.setUserName("");
		}
		else {
			maintenanceActivityImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			maintenanceActivityImpl.setCreateDate(null);
		}
		else {
			maintenanceActivityImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			maintenanceActivityImpl.setModifiedDate(null);
		}
		else {
			maintenanceActivityImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (activityTitle == null) {
			maintenanceActivityImpl.setActivityTitle("");
		}
		else {
			maintenanceActivityImpl.setActivityTitle(activityTitle);
		}

		if (dueDate == null) {
			maintenanceActivityImpl.setDueDate("");
		}
		else {
			maintenanceActivityImpl.setDueDate(dueDate);
		}

		if (status == null) {
			maintenanceActivityImpl.setStatus("");
		}
		else {
			maintenanceActivityImpl.setStatus(status);
		}

		maintenanceActivityImpl.setOrgSiteId(orgSiteId);

		if (specificParty == null) {
			maintenanceActivityImpl.setSpecificParty("");
		}
		else {
			maintenanceActivityImpl.setSpecificParty(specificParty);
		}

		if (dueDateFormated == Long.MIN_VALUE) {
			maintenanceActivityImpl.setDueDateFormated(null);
		}
		else {
			maintenanceActivityImpl.setDueDateFormated(
				new Date(dueDateFormated));
		}

		maintenanceActivityImpl.setCalendarBookingId(calendarBookingId);

		maintenanceActivityImpl.resetOriginalValues();

		return maintenanceActivityImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		maintenanceactivityId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		activityTitle = objectInput.readUTF();
		dueDate = objectInput.readUTF();
		status = objectInput.readUTF();

		orgSiteId = objectInput.readLong();
		specificParty = objectInput.readUTF();
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

		objectOutput.writeLong(maintenanceactivityId);

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

		if (activityTitle == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(activityTitle);
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

		objectOutput.writeLong(orgSiteId);

		if (specificParty == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(specificParty);
		}

		objectOutput.writeLong(dueDateFormated);

		objectOutput.writeLong(calendarBookingId);
	}

	public String uuid;
	public long maintenanceactivityId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String activityTitle;
	public String dueDate;
	public String status;
	public long orgSiteId;
	public String specificParty;
	public long dueDateFormated;
	public long calendarBookingId;

}