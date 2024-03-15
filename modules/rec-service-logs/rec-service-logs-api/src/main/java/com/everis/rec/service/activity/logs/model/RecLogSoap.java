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

package com.everis.rec.service.activity.logs.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.everis.rec.service.activity.logs.service.http.RecLogServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class RecLogSoap implements Serializable {

	public static RecLogSoap toSoapModel(RecLog model) {
		RecLogSoap soapModel = new RecLogSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setActivityLogId(model.getActivityLogId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setLogMessage(model.getLogMessage());
		soapModel.setClassName(model.getClassName());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setComments(model.getComments());
		soapModel.setType(model.getType());

		return soapModel;
	}

	public static RecLogSoap[] toSoapModels(RecLog[] models) {
		RecLogSoap[] soapModels = new RecLogSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static RecLogSoap[][] toSoapModels(RecLog[][] models) {
		RecLogSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new RecLogSoap[models.length][models[0].length];
		}
		else {
			soapModels = new RecLogSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static RecLogSoap[] toSoapModels(List<RecLog> models) {
		List<RecLogSoap> soapModels = new ArrayList<RecLogSoap>(models.size());

		for (RecLog model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new RecLogSoap[soapModels.size()]);
	}

	public RecLogSoap() {
	}

	public long getPrimaryKey() {
		return _activityLogId;
	}

	public void setPrimaryKey(long pk) {
		setActivityLogId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getActivityLogId() {
		return _activityLogId;
	}

	public void setActivityLogId(long activityLogId) {
		_activityLogId = activityLogId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public String getLogMessage() {
		return _logMessage;
	}

	public void setLogMessage(String logMessage) {
		_logMessage = logMessage;
	}

	public String getClassName() {
		return _className;
	}

	public void setClassName(String className) {
		_className = className;
	}

	public Long getClassPK() {
		return _classPK;
	}

	public void setClassPK(Long classPK) {
		_classPK = classPK;
	}

	public String getComments() {
		return _comments;
	}

	public void setComments(String comments) {
		_comments = comments;
	}

	public String getType() {
		return _type;
	}

	public void setType(String type) {
		_type = type;
	}

	private String _uuid;
	private long _activityLogId;
	private long _groupId;
	private Date _createDate;
	private String _logMessage;
	private String _className;
	private Long _classPK;
	private String _comments;
	private String _type;

}