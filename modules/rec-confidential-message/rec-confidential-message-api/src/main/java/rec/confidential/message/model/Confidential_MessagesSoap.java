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

package rec.confidential.message.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link rec.confidential.message.service.http.Confidential_MessagesServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class Confidential_MessagesSoap implements Serializable {

	public static Confidential_MessagesSoap toSoapModel(
		Confidential_Messages model) {

		Confidential_MessagesSoap soapModel = new Confidential_MessagesSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setMessageId(model.getMessageId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setCreateDate(model.getCreateDate());

		return soapModel;
	}

	public static Confidential_MessagesSoap[] toSoapModels(
		Confidential_Messages[] models) {

		Confidential_MessagesSoap[] soapModels =
			new Confidential_MessagesSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static Confidential_MessagesSoap[][] toSoapModels(
		Confidential_Messages[][] models) {

		Confidential_MessagesSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new Confidential_MessagesSoap[models.length][models[0].length];
		}
		else {
			soapModels = new Confidential_MessagesSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static Confidential_MessagesSoap[] toSoapModels(
		List<Confidential_Messages> models) {

		List<Confidential_MessagesSoap> soapModels =
			new ArrayList<Confidential_MessagesSoap>(models.size());

		for (Confidential_Messages model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new Confidential_MessagesSoap[soapModels.size()]);
	}

	public Confidential_MessagesSoap() {
	}

	public long getPrimaryKey() {
		return _messageId;
	}

	public void setPrimaryKey(long pk) {
		setMessageId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getMessageId() {
		return _messageId;
	}

	public void setMessageId(long messageId) {
		_messageId = messageId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	private String _uuid;
	private long _messageId;
	private long _companyId;
	private Date _createDate;

}