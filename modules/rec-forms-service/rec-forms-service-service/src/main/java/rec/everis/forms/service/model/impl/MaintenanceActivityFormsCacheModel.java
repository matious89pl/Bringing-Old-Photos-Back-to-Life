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

package rec.everis.forms.service.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

import rec.everis.forms.service.model.MaintenanceActivityForms;

/**
 * The cache model class for representing MaintenanceActivityForms in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class MaintenanceActivityFormsCacheModel
	implements CacheModel<MaintenanceActivityForms>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof MaintenanceActivityFormsCacheModel)) {
			return false;
		}

		MaintenanceActivityFormsCacheModel maintenanceActivityFormsCacheModel =
			(MaintenanceActivityFormsCacheModel)object;

		if (maintenanceactivityformId ==
				maintenanceActivityFormsCacheModel.maintenanceactivityformId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, maintenanceactivityformId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(41);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", maintenanceactivityformId=");
		sb.append(maintenanceactivityformId);
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
		sb.append(", formType=");
		sb.append(formType);
		sb.append(", year=");
		sb.append(year);
		sb.append(", submitDate=");
		sb.append(submitDate);
		sb.append(", approvalDate=");
		sb.append(approvalDate);
		sb.append(", submitDateFormatted=");
		sb.append(submitDateFormatted);
		sb.append(", approvalDateFormatted=");
		sb.append(approvalDateFormatted);
		sb.append(", status=");
		sb.append(status);
		sb.append(", formInstanceRecordVersionId=");
		sb.append(formInstanceRecordVersionId);
		sb.append(", orgSiteId=");
		sb.append(orgSiteId);
		sb.append(", viewUrl=");
		sb.append(viewUrl);
		sb.append(", formInstanceId=");
		sb.append(formInstanceId);
		sb.append(", formInstanceRecordId=");
		sb.append(formInstanceRecordId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public MaintenanceActivityForms toEntityModel() {
		MaintenanceActivityFormsImpl maintenanceActivityFormsImpl =
			new MaintenanceActivityFormsImpl();

		if (uuid == null) {
			maintenanceActivityFormsImpl.setUuid("");
		}
		else {
			maintenanceActivityFormsImpl.setUuid(uuid);
		}

		maintenanceActivityFormsImpl.setMaintenanceactivityformId(
			maintenanceactivityformId);
		maintenanceActivityFormsImpl.setGroupId(groupId);
		maintenanceActivityFormsImpl.setCompanyId(companyId);
		maintenanceActivityFormsImpl.setUserId(userId);

		if (userName == null) {
			maintenanceActivityFormsImpl.setUserName("");
		}
		else {
			maintenanceActivityFormsImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			maintenanceActivityFormsImpl.setCreateDate(null);
		}
		else {
			maintenanceActivityFormsImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			maintenanceActivityFormsImpl.setModifiedDate(null);
		}
		else {
			maintenanceActivityFormsImpl.setModifiedDate(
				new Date(modifiedDate));
		}

		if (formType == null) {
			maintenanceActivityFormsImpl.setFormType("");
		}
		else {
			maintenanceActivityFormsImpl.setFormType(formType);
		}

		maintenanceActivityFormsImpl.setYear(year);

		if (submitDate == Long.MIN_VALUE) {
			maintenanceActivityFormsImpl.setSubmitDate(null);
		}
		else {
			maintenanceActivityFormsImpl.setSubmitDate(new Date(submitDate));
		}

		if (approvalDate == Long.MIN_VALUE) {
			maintenanceActivityFormsImpl.setApprovalDate(null);
		}
		else {
			maintenanceActivityFormsImpl.setApprovalDate(
				new Date(approvalDate));
		}

		if (submitDateFormatted == null) {
			maintenanceActivityFormsImpl.setSubmitDateFormatted("");
		}
		else {
			maintenanceActivityFormsImpl.setSubmitDateFormatted(
				submitDateFormatted);
		}

		if (approvalDateFormatted == null) {
			maintenanceActivityFormsImpl.setApprovalDateFormatted("");
		}
		else {
			maintenanceActivityFormsImpl.setApprovalDateFormatted(
				approvalDateFormatted);
		}

		if (status == null) {
			maintenanceActivityFormsImpl.setStatus("");
		}
		else {
			maintenanceActivityFormsImpl.setStatus(status);
		}

		maintenanceActivityFormsImpl.setFormInstanceRecordVersionId(
			formInstanceRecordVersionId);
		maintenanceActivityFormsImpl.setOrgSiteId(orgSiteId);

		if (viewUrl == null) {
			maintenanceActivityFormsImpl.setViewUrl("");
		}
		else {
			maintenanceActivityFormsImpl.setViewUrl(viewUrl);
		}

		maintenanceActivityFormsImpl.setFormInstanceId(formInstanceId);
		maintenanceActivityFormsImpl.setFormInstanceRecordId(
			formInstanceRecordId);

		maintenanceActivityFormsImpl.resetOriginalValues();

		return maintenanceActivityFormsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		maintenanceactivityformId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		formType = objectInput.readUTF();

		year = objectInput.readInt();
		submitDate = objectInput.readLong();
		approvalDate = objectInput.readLong();
		submitDateFormatted = objectInput.readUTF();
		approvalDateFormatted = objectInput.readUTF();
		status = objectInput.readUTF();

		formInstanceRecordVersionId = objectInput.readLong();

		orgSiteId = objectInput.readLong();
		viewUrl = objectInput.readUTF();

		formInstanceId = objectInput.readLong();

		formInstanceRecordId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(maintenanceactivityformId);

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

		if (formType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(formType);
		}

		objectOutput.writeInt(year);
		objectOutput.writeLong(submitDate);
		objectOutput.writeLong(approvalDate);

		if (submitDateFormatted == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(submitDateFormatted);
		}

		if (approvalDateFormatted == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(approvalDateFormatted);
		}

		if (status == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(status);
		}

		objectOutput.writeLong(formInstanceRecordVersionId);

		objectOutput.writeLong(orgSiteId);

		if (viewUrl == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(viewUrl);
		}

		objectOutput.writeLong(formInstanceId);

		objectOutput.writeLong(formInstanceRecordId);
	}

	public String uuid;
	public long maintenanceactivityformId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String formType;
	public int year;
	public long submitDate;
	public long approvalDate;
	public String submitDateFormatted;
	public String approvalDateFormatted;
	public String status;
	public long formInstanceRecordVersionId;
	public long orgSiteId;
	public String viewUrl;
	public long formInstanceId;
	public long formInstanceRecordId;

}