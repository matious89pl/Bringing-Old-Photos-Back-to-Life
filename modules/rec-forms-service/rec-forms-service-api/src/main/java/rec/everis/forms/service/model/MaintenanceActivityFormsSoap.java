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

package rec.everis.forms.service.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link rec.everis.forms.service.service.http.MaintenanceActivityFormsServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class MaintenanceActivityFormsSoap implements Serializable {

	public static MaintenanceActivityFormsSoap toSoapModel(
		MaintenanceActivityForms model) {

		MaintenanceActivityFormsSoap soapModel =
			new MaintenanceActivityFormsSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setMaintenanceactivityformId(
			model.getMaintenanceactivityformId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setFormType(model.getFormType());
		soapModel.setYear(model.getYear());
		soapModel.setSubmitDate(model.getSubmitDate());
		soapModel.setApprovalDate(model.getApprovalDate());
		soapModel.setSubmitDateFormatted(model.getSubmitDateFormatted());
		soapModel.setApprovalDateFormatted(model.getApprovalDateFormatted());
		soapModel.setStatus(model.getStatus());
		soapModel.setFormInstanceRecordVersionId(
			model.getFormInstanceRecordVersionId());
		soapModel.setOrgSiteId(model.getOrgSiteId());
		soapModel.setViewUrl(model.getViewUrl());
		soapModel.setFormInstanceId(model.getFormInstanceId());
		soapModel.setFormInstanceRecordId(model.getFormInstanceRecordId());

		return soapModel;
	}

	public static MaintenanceActivityFormsSoap[] toSoapModels(
		MaintenanceActivityForms[] models) {

		MaintenanceActivityFormsSoap[] soapModels =
			new MaintenanceActivityFormsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static MaintenanceActivityFormsSoap[][] toSoapModels(
		MaintenanceActivityForms[][] models) {

		MaintenanceActivityFormsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new MaintenanceActivityFormsSoap
					[models.length][models[0].length];
		}
		else {
			soapModels = new MaintenanceActivityFormsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static MaintenanceActivityFormsSoap[] toSoapModels(
		List<MaintenanceActivityForms> models) {

		List<MaintenanceActivityFormsSoap> soapModels =
			new ArrayList<MaintenanceActivityFormsSoap>(models.size());

		for (MaintenanceActivityForms model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new MaintenanceActivityFormsSoap[soapModels.size()]);
	}

	public MaintenanceActivityFormsSoap() {
	}

	public long getPrimaryKey() {
		return _maintenanceactivityformId;
	}

	public void setPrimaryKey(long pk) {
		setMaintenanceactivityformId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getMaintenanceactivityformId() {
		return _maintenanceactivityformId;
	}

	public void setMaintenanceactivityformId(long maintenanceactivityformId) {
		_maintenanceactivityformId = maintenanceactivityformId;
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

	public String getFormType() {
		return _formType;
	}

	public void setFormType(String formType) {
		_formType = formType;
	}

	public int getYear() {
		return _year;
	}

	public void setYear(int year) {
		_year = year;
	}

	public Date getSubmitDate() {
		return _submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		_submitDate = submitDate;
	}

	public Date getApprovalDate() {
		return _approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		_approvalDate = approvalDate;
	}

	public String getSubmitDateFormatted() {
		return _submitDateFormatted;
	}

	public void setSubmitDateFormatted(String submitDateFormatted) {
		_submitDateFormatted = submitDateFormatted;
	}

	public String getApprovalDateFormatted() {
		return _approvalDateFormatted;
	}

	public void setApprovalDateFormatted(String approvalDateFormatted) {
		_approvalDateFormatted = approvalDateFormatted;
	}

	public String getStatus() {
		return _status;
	}

	public void setStatus(String status) {
		_status = status;
	}

	public long getFormInstanceRecordVersionId() {
		return _formInstanceRecordVersionId;
	}

	public void setFormInstanceRecordVersionId(
		long formInstanceRecordVersionId) {

		_formInstanceRecordVersionId = formInstanceRecordVersionId;
	}

	public long getOrgSiteId() {
		return _orgSiteId;
	}

	public void setOrgSiteId(long orgSiteId) {
		_orgSiteId = orgSiteId;
	}

	public String getViewUrl() {
		return _viewUrl;
	}

	public void setViewUrl(String viewUrl) {
		_viewUrl = viewUrl;
	}

	public long getFormInstanceId() {
		return _formInstanceId;
	}

	public void setFormInstanceId(long formInstanceId) {
		_formInstanceId = formInstanceId;
	}

	public long getFormInstanceRecordId() {
		return _formInstanceRecordId;
	}

	public void setFormInstanceRecordId(long formInstanceRecordId) {
		_formInstanceRecordId = formInstanceRecordId;
	}

	private String _uuid;
	private long _maintenanceactivityformId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _formType;
	private int _year;
	private Date _submitDate;
	private Date _approvalDate;
	private String _submitDateFormatted;
	private String _approvalDateFormatted;
	private String _status;
	private long _formInstanceRecordVersionId;
	private long _orgSiteId;
	private String _viewUrl;
	private long _formInstanceId;
	private long _formInstanceRecordId;

}