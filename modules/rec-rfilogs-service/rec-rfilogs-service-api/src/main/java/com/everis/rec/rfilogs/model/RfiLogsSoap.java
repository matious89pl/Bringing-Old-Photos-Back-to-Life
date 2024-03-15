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

package com.everis.rec.rfilogs.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.everis.rec.rfilogs.service.http.RfiLogsServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class RfiLogsSoap implements Serializable {

	public static RfiLogsSoap toSoapModel(RfiLogs model) {
		RfiLogsSoap soapModel = new RfiLogsSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setRfilogId(model.getRfilogId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setReqId(model.getReqId());
		soapModel.setSpecificParty(model.getSpecificParty());
		soapModel.setOrgSiteId(model.getOrgSiteId());
		soapModel.setTitle(model.getTitle());
		soapModel.setDueDate(model.getDueDate());
		soapModel.setStatus(model.getStatus());
		soapModel.setReqDesc(model.getReqDesc());
		soapModel.setRationale(model.getRationale());
		soapModel.setDueDateFormated(model.getDueDateFormated());
		soapModel.setCalendarBookingId(model.getCalendarBookingId());

		return soapModel;
	}

	public static RfiLogsSoap[] toSoapModels(RfiLogs[] models) {
		RfiLogsSoap[] soapModels = new RfiLogsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static RfiLogsSoap[][] toSoapModels(RfiLogs[][] models) {
		RfiLogsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new RfiLogsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new RfiLogsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static RfiLogsSoap[] toSoapModels(List<RfiLogs> models) {
		List<RfiLogsSoap> soapModels = new ArrayList<RfiLogsSoap>(
			models.size());

		for (RfiLogs model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new RfiLogsSoap[soapModels.size()]);
	}

	public RfiLogsSoap() {
	}

	public long getPrimaryKey() {
		return _rfilogId;
	}

	public void setPrimaryKey(long pk) {
		setRfilogId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getRfilogId() {
		return _rfilogId;
	}

	public void setRfilogId(long rfilogId) {
		_rfilogId = rfilogId;
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

	public long getReqId() {
		return _reqId;
	}

	public void setReqId(long reqId) {
		_reqId = reqId;
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

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public String getDueDate() {
		return _dueDate;
	}

	public void setDueDate(String dueDate) {
		_dueDate = dueDate;
	}

	public String getStatus() {
		return _status;
	}

	public void setStatus(String status) {
		_status = status;
	}

	public String getReqDesc() {
		return _reqDesc;
	}

	public void setReqDesc(String reqDesc) {
		_reqDesc = reqDesc;
	}

	public String getRationale() {
		return _rationale;
	}

	public void setRationale(String rationale) {
		_rationale = rationale;
	}

	public Date getDueDateFormated() {
		return _dueDateFormated;
	}

	public void setDueDateFormated(Date dueDateFormated) {
		_dueDateFormated = dueDateFormated;
	}

	public long getCalendarBookingId() {
		return _calendarBookingId;
	}

	public void setCalendarBookingId(long calendarBookingId) {
		_calendarBookingId = calendarBookingId;
	}

	private String _uuid;
	private long _rfilogId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _reqId;
	private String _specificParty;
	private long _orgSiteId;
	private String _title;
	private String _dueDate;
	private String _status;
	private String _reqDesc;
	private String _rationale;
	private Date _dueDateFormated;
	private long _calendarBookingId;

}