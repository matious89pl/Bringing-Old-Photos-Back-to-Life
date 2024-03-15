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

package com.everis.rec.maintenanceactivity.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.everis.rec.maintenanceactivity.service.http.MaintenanceActivityServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class MaintenanceActivitySoap implements Serializable {

	public static MaintenanceActivitySoap toSoapModel(
		MaintenanceActivity model) {

		MaintenanceActivitySoap soapModel = new MaintenanceActivitySoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setMaintenanceactivityId(model.getMaintenanceactivityId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setActivityTitle(model.getActivityTitle());
		soapModel.setDueDate(model.getDueDate());
		soapModel.setStatus(model.getStatus());
		soapModel.setOrgSiteId(model.getOrgSiteId());
		soapModel.setSpecificParty(model.getSpecificParty());
		soapModel.setDueDateFormated(model.getDueDateFormated());
		soapModel.setCalendarBookingId(model.getCalendarBookingId());

		return soapModel;
	}

	public static MaintenanceActivitySoap[] toSoapModels(
		MaintenanceActivity[] models) {

		MaintenanceActivitySoap[] soapModels =
			new MaintenanceActivitySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static MaintenanceActivitySoap[][] toSoapModels(
		MaintenanceActivity[][] models) {

		MaintenanceActivitySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new MaintenanceActivitySoap[models.length][models[0].length];
		}
		else {
			soapModels = new MaintenanceActivitySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static MaintenanceActivitySoap[] toSoapModels(
		List<MaintenanceActivity> models) {

		List<MaintenanceActivitySoap> soapModels =
			new ArrayList<MaintenanceActivitySoap>(models.size());

		for (MaintenanceActivity model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new MaintenanceActivitySoap[soapModels.size()]);
	}

	public MaintenanceActivitySoap() {
	}

	public long getPrimaryKey() {
		return _maintenanceactivityId;
	}

	public void setPrimaryKey(long pk) {
		setMaintenanceactivityId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getMaintenanceactivityId() {
		return _maintenanceactivityId;
	}

	public void setMaintenanceactivityId(long maintenanceactivityId) {
		_maintenanceactivityId = maintenanceactivityId;
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

	public String getActivityTitle() {
		return _activityTitle;
	}

	public void setActivityTitle(String activityTitle) {
		_activityTitle = activityTitle;
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
	private long _maintenanceactivityId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _activityTitle;
	private String _dueDate;
	private String _status;
	private long _orgSiteId;
	private String _specificParty;
	private Date _dueDateFormated;
	private long _calendarBookingId;

}