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

package com.everis.messages.service.builder.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.everis.messages.service.builder.service.http.MessagesServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class MessagesSoap implements Serializable {

	public static MessagesSoap toSoapModel(Messages model) {
		MessagesSoap soapModel = new MessagesSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setNotificationEngineId(model.getNotificationEngineId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setName(model.getName());
		soapModel.setSubject(model.getSubject());
		soapModel.setBody(model.getBody());
		soapModel.setStatus(model.isStatus());
		soapModel.setTags(model.getTags());

		return soapModel;
	}

	public static MessagesSoap[] toSoapModels(Messages[] models) {
		MessagesSoap[] soapModels = new MessagesSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static MessagesSoap[][] toSoapModels(Messages[][] models) {
		MessagesSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new MessagesSoap[models.length][models[0].length];
		}
		else {
			soapModels = new MessagesSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static MessagesSoap[] toSoapModels(List<Messages> models) {
		List<MessagesSoap> soapModels = new ArrayList<MessagesSoap>(
			models.size());

		for (Messages model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new MessagesSoap[soapModels.size()]);
	}

	public MessagesSoap() {
	}

	public long getPrimaryKey() {
		return _notificationEngineId;
	}

	public void setPrimaryKey(long pk) {
		setNotificationEngineId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getNotificationEngineId() {
		return _notificationEngineId;
	}

	public void setNotificationEngineId(long notificationEngineId) {
		_notificationEngineId = notificationEngineId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getSubject() {
		return _subject;
	}

	public void setSubject(String subject) {
		_subject = subject;
	}

	public String getBody() {
		return _body;
	}

	public void setBody(String body) {
		_body = body;
	}

	public boolean getStatus() {
		return _status;
	}

	public boolean isStatus() {
		return _status;
	}

	public void setStatus(boolean status) {
		_status = status;
	}

	public String getTags() {
		return _tags;
	}

	public void setTags(String tags) {
		_tags = tags;
	}

	private String _uuid;
	private long _notificationEngineId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _subject;
	private String _body;
	private boolean _status;
	private String _tags;

}