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

package com.everis.rec.validation.log.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.everis.rec.validation.log.service.http.ValidationLogServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class ValidationLogSoap implements Serializable {

	public static ValidationLogSoap toSoapModel(ValidationLog model) {
		ValidationLogSoap soapModel = new ValidationLogSoap();

		soapModel.setValidationLogId(model.getValidationLogId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setFileName(model.getFileName());
		soapModel.setUploadedBy(model.getUploadedBy());
		soapModel.setUploadedFrom(model.getUploadedFrom());
		soapModel.setFolderId(model.getFolderId());
		soapModel.setLogReason(model.getLogReason());

		return soapModel;
	}

	public static ValidationLogSoap[] toSoapModels(ValidationLog[] models) {
		ValidationLogSoap[] soapModels = new ValidationLogSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ValidationLogSoap[][] toSoapModels(ValidationLog[][] models) {
		ValidationLogSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ValidationLogSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ValidationLogSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ValidationLogSoap[] toSoapModels(List<ValidationLog> models) {
		List<ValidationLogSoap> soapModels = new ArrayList<ValidationLogSoap>(
			models.size());

		for (ValidationLog model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ValidationLogSoap[soapModels.size()]);
	}

	public ValidationLogSoap() {
	}

	public long getPrimaryKey() {
		return _validationLogId;
	}

	public void setPrimaryKey(long pk) {
		setValidationLogId(pk);
	}

	public long getValidationLogId() {
		return _validationLogId;
	}

	public void setValidationLogId(long validationLogId) {
		_validationLogId = validationLogId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
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

	public String getFileName() {
		return _fileName;
	}

	public void setFileName(String fileName) {
		_fileName = fileName;
	}

	public String getUploadedBy() {
		return _uploadedBy;
	}

	public void setUploadedBy(String uploadedBy) {
		_uploadedBy = uploadedBy;
	}

	public String getUploadedFrom() {
		return _uploadedFrom;
	}

	public void setUploadedFrom(String uploadedFrom) {
		_uploadedFrom = uploadedFrom;
	}

	public long getFolderId() {
		return _folderId;
	}

	public void setFolderId(long folderId) {
		_folderId = folderId;
	}

	public String getLogReason() {
		return _logReason;
	}

	public void setLogReason(String logReason) {
		_logReason = logReason;
	}

	private long _validationLogId;
	private long _companyId;
	private long _groupId;
	private Date _createDate;
	private String _fileName;
	private String _uploadedBy;
	private String _uploadedFrom;
	private long _folderId;
	private String _logReason;

}