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

package rec.supporting.resources.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link rec.supporting.resources.service.http.supportRServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class supportRSoap implements Serializable {

	public static supportRSoap toSoapModel(supportR model) {
		supportRSoap soapModel = new supportRSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setSupportRId(model.getSupportRId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setSpecificParty(model.getSpecificParty());
		soapModel.setOrgSiteId(model.getOrgSiteId());
		soapModel.setType(model.getType());
		soapModel.setTitle(model.getTitle());
		soapModel.setDescription(model.getDescription());
		soapModel.setDueDate(model.getDueDate());
		soapModel.setStatus(model.getStatus());
		soapModel.setLink(model.getLink());
		soapModel.setDisplayLink(model.getDisplayLink());
		soapModel.setCalendarBookingId(model.getCalendarBookingId());

		return soapModel;
	}

	public static supportRSoap[] toSoapModels(supportR[] models) {
		supportRSoap[] soapModels = new supportRSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static supportRSoap[][] toSoapModels(supportR[][] models) {
		supportRSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new supportRSoap[models.length][models[0].length];
		}
		else {
			soapModels = new supportRSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static supportRSoap[] toSoapModels(List<supportR> models) {
		List<supportRSoap> soapModels = new ArrayList<supportRSoap>(
			models.size());

		for (supportR model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new supportRSoap[soapModels.size()]);
	}

	public supportRSoap() {
	}

	public long getPrimaryKey() {
		return _supportRId;
	}

	public void setPrimaryKey(long pk) {
		setSupportRId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getSupportRId() {
		return _supportRId;
	}

	public void setSupportRId(long supportRId) {
		_supportRId = supportRId;
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

	public String getSpecificParty() {
		return _specificParty;
	}

	public void setSpecificParty(String specificParty) {
		_specificParty = specificParty;
	}

	public long getOrgSiteId() {
		return _orgSiteId;
	}

	public void setOrgSiteId(long orgSiteId) {
		_orgSiteId = orgSiteId;
	}

	public String getType() {
		return _type;
	}

	public void setType(String type) {
		_type = type;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public Date getDueDate() {
		return _dueDate;
	}

	public void setDueDate(Date dueDate) {
		_dueDate = dueDate;
	}

	public String getStatus() {
		return _status;
	}

	public void setStatus(String status) {
		_status = status;
	}

	public String getLink() {
		return _link;
	}

	public void setLink(String link) {
		_link = link;
	}

	public String getDisplayLink() {
		return _displayLink;
	}

	public void setDisplayLink(String displayLink) {
		_displayLink = displayLink;
	}

	public long getCalendarBookingId() {
		return _calendarBookingId;
	}

	public void setCalendarBookingId(long calendarBookingId) {
		_calendarBookingId = calendarBookingId;
	}

	private String _uuid;
	private long _supportRId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _specificParty;
	private long _orgSiteId;
	private String _type;
	private String _title;
	private String _description;
	private Date _dueDate;
	private String _status;
	private String _link;
	private String _displayLink;
	private long _calendarBookingId;

}