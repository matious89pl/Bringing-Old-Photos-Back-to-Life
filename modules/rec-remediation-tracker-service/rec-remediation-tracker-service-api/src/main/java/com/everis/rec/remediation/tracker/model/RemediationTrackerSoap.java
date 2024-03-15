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

package com.everis.rec.remediation.tracker.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class RemediationTrackerSoap implements Serializable {

	public static RemediationTrackerSoap toSoapModel(RemediationTracker model) {
		RemediationTrackerSoap soapModel = new RemediationTrackerSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setRemediationTrackerId(model.getRemediationTrackerId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setTitle(model.getTitle());
		soapModel.setOrgSiteId(model.getOrgSiteId());
		soapModel.setSpecificParty(model.getSpecificParty());
		soapModel.setCategory(model.getCategory());
		soapModel.setDescription(model.getDescription());
		soapModel.setDueDate(model.getDueDate());
		soapModel.setDueDateFormated(model.getDueDateFormated());
		soapModel.setStatus(model.getStatus());
		soapModel.setCalendarBookingId(model.getCalendarBookingId());

		return soapModel;
	}

	public static RemediationTrackerSoap[] toSoapModels(
		RemediationTracker[] models) {

		RemediationTrackerSoap[] soapModels =
			new RemediationTrackerSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static RemediationTrackerSoap[][] toSoapModels(
		RemediationTracker[][] models) {

		RemediationTrackerSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new RemediationTrackerSoap[models.length][models[0].length];
		}
		else {
			soapModels = new RemediationTrackerSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static RemediationTrackerSoap[] toSoapModels(
		List<RemediationTracker> models) {

		List<RemediationTrackerSoap> soapModels =
			new ArrayList<RemediationTrackerSoap>(models.size());

		for (RemediationTracker model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new RemediationTrackerSoap[soapModels.size()]);
	}

	public RemediationTrackerSoap() {
	}

	public long getPrimaryKey() {
		return _remediationTrackerId;
	}

	public void setPrimaryKey(long pk) {
		setRemediationTrackerId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getRemediationTrackerId() {
		return _remediationTrackerId;
	}

	public void setRemediationTrackerId(long remediationTrackerId) {
		_remediationTrackerId = remediationTrackerId;
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

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public long getOrgSiteId() {
		return _orgSiteId;
	}

	public void setOrgSiteId(long orgSiteId) {
		_orgSiteId = orgSiteId;
	}

	public String getSpecificParty() {
		return _specificParty;
	}

	public void setSpecificParty(String specificParty) {
		_specificParty = specificParty;
	}

	public String getCategory() {
		return _category;
	}

	public void setCategory(String category) {
		_category = category;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getDueDate() {
		return _dueDate;
	}

	public void setDueDate(String dueDate) {
		_dueDate = dueDate;
	}

	public Date getDueDateFormated() {
		return _dueDateFormated;
	}

	public void setDueDateFormated(Date dueDateFormated) {
		_dueDateFormated = dueDateFormated;
	}

	public String getStatus() {
		return _status;
	}

	public void setStatus(String status) {
		_status = status;
	}

	public long getCalendarBookingId() {
		return _calendarBookingId;
	}

	public void setCalendarBookingId(long calendarBookingId) {
		_calendarBookingId = calendarBookingId;
	}

	private String _uuid;
	private long _remediationTrackerId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _title;
	private long _orgSiteId;
	private String _specificParty;
	private String _category;
	private String _description;
	private String _dueDate;
	private Date _dueDateFormated;
	private String _status;
	private long _calendarBookingId;

}