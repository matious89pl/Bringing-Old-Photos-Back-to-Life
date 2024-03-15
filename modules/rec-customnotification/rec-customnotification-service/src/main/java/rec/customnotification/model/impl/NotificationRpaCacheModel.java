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

package rec.customnotification.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

import rec.customnotification.model.NotificationRpa;

/**
 * The cache model class for representing NotificationRpa in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class NotificationRpaCacheModel
	implements CacheModel<NotificationRpa>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof NotificationRpaCacheModel)) {
			return false;
		}

		NotificationRpaCacheModel notificationRpaCacheModel =
			(NotificationRpaCacheModel)object;

		if (customNotificationId ==
				notificationRpaCacheModel.customNotificationId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, customNotificationId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", customNotificationId=");
		sb.append(customNotificationId);
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
		sb.append(", plidPage=");
		sb.append(plidPage);
		sb.append(", targetName=");
		sb.append(targetName);
		sb.append(", notificationTitle=");
		sb.append(notificationTitle);
		sb.append(", notificationBody=");
		sb.append(notificationBody);
		sb.append(", url=");
		sb.append(url);
		sb.append(", deliveryMethod=");
		sb.append(deliveryMethod);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public NotificationRpa toEntityModel() {
		NotificationRpaImpl notificationRpaImpl = new NotificationRpaImpl();

		if (uuid == null) {
			notificationRpaImpl.setUuid("");
		}
		else {
			notificationRpaImpl.setUuid(uuid);
		}

		notificationRpaImpl.setCustomNotificationId(customNotificationId);
		notificationRpaImpl.setGroupId(groupId);
		notificationRpaImpl.setCompanyId(companyId);
		notificationRpaImpl.setUserId(userId);

		if (userName == null) {
			notificationRpaImpl.setUserName("");
		}
		else {
			notificationRpaImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			notificationRpaImpl.setCreateDate(null);
		}
		else {
			notificationRpaImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			notificationRpaImpl.setModifiedDate(null);
		}
		else {
			notificationRpaImpl.setModifiedDate(new Date(modifiedDate));
		}

		notificationRpaImpl.setPlidPage(plidPage);

		if (targetName == null) {
			notificationRpaImpl.setTargetName("");
		}
		else {
			notificationRpaImpl.setTargetName(targetName);
		}

		if (notificationTitle == null) {
			notificationRpaImpl.setNotificationTitle("");
		}
		else {
			notificationRpaImpl.setNotificationTitle(notificationTitle);
		}

		if (notificationBody == null) {
			notificationRpaImpl.setNotificationBody("");
		}
		else {
			notificationRpaImpl.setNotificationBody(notificationBody);
		}

		if (url == null) {
			notificationRpaImpl.setUrl("");
		}
		else {
			notificationRpaImpl.setUrl(url);
		}

		if (deliveryMethod == null) {
			notificationRpaImpl.setDeliveryMethod("");
		}
		else {
			notificationRpaImpl.setDeliveryMethod(deliveryMethod);
		}

		notificationRpaImpl.resetOriginalValues();

		return notificationRpaImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		customNotificationId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		plidPage = objectInput.readLong();
		targetName = objectInput.readUTF();
		notificationTitle = objectInput.readUTF();
		notificationBody = objectInput.readUTF();
		url = objectInput.readUTF();
		deliveryMethod = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(customNotificationId);

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

		objectOutput.writeLong(plidPage);

		if (targetName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(targetName);
		}

		if (notificationTitle == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(notificationTitle);
		}

		if (notificationBody == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(notificationBody);
		}

		if (url == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(url);
		}

		if (deliveryMethod == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(deliveryMethod);
		}
	}

	public String uuid;
	public long customNotificationId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long plidPage;
	public String targetName;
	public String notificationTitle;
	public String notificationBody;
	public String url;
	public String deliveryMethod;

}