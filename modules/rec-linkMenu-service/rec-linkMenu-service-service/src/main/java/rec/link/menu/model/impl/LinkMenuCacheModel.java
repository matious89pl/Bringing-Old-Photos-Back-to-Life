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

package rec.link.menu.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

import rec.link.menu.model.LinkMenu;

/**
 * The cache model class for representing LinkMenu in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class LinkMenuCacheModel
	implements CacheModel<LinkMenu>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof LinkMenuCacheModel)) {
			return false;
		}

		LinkMenuCacheModel linkMenuCacheModel = (LinkMenuCacheModel)object;

		if (linkId == linkMenuCacheModel.linkId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, linkId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", linkId=");
		sb.append(linkId);
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
		sb.append(", linkName=");
		sb.append(linkName);
		sb.append(", link=");
		sb.append(link);
		sb.append(", linkPosition=");
		sb.append(linkPosition);
		sb.append(", iconName=");
		sb.append(iconName);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LinkMenu toEntityModel() {
		LinkMenuImpl linkMenuImpl = new LinkMenuImpl();

		if (uuid == null) {
			linkMenuImpl.setUuid("");
		}
		else {
			linkMenuImpl.setUuid(uuid);
		}

		linkMenuImpl.setLinkId(linkId);
		linkMenuImpl.setGroupId(groupId);
		linkMenuImpl.setCompanyId(companyId);
		linkMenuImpl.setUserId(userId);

		if (userName == null) {
			linkMenuImpl.setUserName("");
		}
		else {
			linkMenuImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			linkMenuImpl.setCreateDate(null);
		}
		else {
			linkMenuImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			linkMenuImpl.setModifiedDate(null);
		}
		else {
			linkMenuImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (linkName == null) {
			linkMenuImpl.setLinkName("");
		}
		else {
			linkMenuImpl.setLinkName(linkName);
		}

		if (link == null) {
			linkMenuImpl.setLink("");
		}
		else {
			linkMenuImpl.setLink(link);
		}

		linkMenuImpl.setLinkPosition(linkPosition);

		if (iconName == null) {
			linkMenuImpl.setIconName("");
		}
		else {
			linkMenuImpl.setIconName(iconName);
		}

		linkMenuImpl.resetOriginalValues();

		return linkMenuImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		linkId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		linkName = objectInput.readUTF();
		link = objectInput.readUTF();

		linkPosition = objectInput.readInt();
		iconName = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(linkId);

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

		if (linkName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(linkName);
		}

		if (link == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(link);
		}

		objectOutput.writeInt(linkPosition);

		if (iconName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(iconName);
		}
	}

	public String uuid;
	public long linkId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String linkName;
	public String link;
	public int linkPosition;
	public String iconName;

}