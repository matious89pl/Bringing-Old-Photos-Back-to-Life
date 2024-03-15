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

package rec.confidential.message.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

import rec.confidential.message.model.Confidential_Messages;

/**
 * The cache model class for representing Confidential_Messages in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class Confidential_MessagesCacheModel
	implements CacheModel<Confidential_Messages>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Confidential_MessagesCacheModel)) {
			return false;
		}

		Confidential_MessagesCacheModel confidential_MessagesCacheModel =
			(Confidential_MessagesCacheModel)object;

		if (messageId == confidential_MessagesCacheModel.messageId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, messageId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", messageId=");
		sb.append(messageId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Confidential_Messages toEntityModel() {
		Confidential_MessagesImpl confidential_MessagesImpl =
			new Confidential_MessagesImpl();

		if (uuid == null) {
			confidential_MessagesImpl.setUuid("");
		}
		else {
			confidential_MessagesImpl.setUuid(uuid);
		}

		confidential_MessagesImpl.setMessageId(messageId);
		confidential_MessagesImpl.setCompanyId(companyId);

		if (createDate == Long.MIN_VALUE) {
			confidential_MessagesImpl.setCreateDate(null);
		}
		else {
			confidential_MessagesImpl.setCreateDate(new Date(createDate));
		}

		confidential_MessagesImpl.resetOriginalValues();

		return confidential_MessagesImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		messageId = objectInput.readLong();

		companyId = objectInput.readLong();
		createDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(messageId);

		objectOutput.writeLong(companyId);
		objectOutput.writeLong(createDate);
	}

	public String uuid;
	public long messageId;
	public long companyId;
	public long createDate;

}