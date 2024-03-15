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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ValidationLog}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ValidationLog
 * @generated
 */
public class ValidationLogWrapper
	extends BaseModelWrapper<ValidationLog>
	implements ModelWrapper<ValidationLog>, ValidationLog {

	public ValidationLogWrapper(ValidationLog validationLog) {
		super(validationLog);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("validationLogId", getValidationLogId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("createDate", getCreateDate());
		attributes.put("fileName", getFileName());
		attributes.put("uploadedBy", getUploadedBy());
		attributes.put("uploadedFrom", getUploadedFrom());
		attributes.put("folderId", getFolderId());
		attributes.put("logReason", getLogReason());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long validationLogId = (Long)attributes.get("validationLogId");

		if (validationLogId != null) {
			setValidationLogId(validationLogId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		String fileName = (String)attributes.get("fileName");

		if (fileName != null) {
			setFileName(fileName);
		}

		String uploadedBy = (String)attributes.get("uploadedBy");

		if (uploadedBy != null) {
			setUploadedBy(uploadedBy);
		}

		String uploadedFrom = (String)attributes.get("uploadedFrom");

		if (uploadedFrom != null) {
			setUploadedFrom(uploadedFrom);
		}

		Long folderId = (Long)attributes.get("folderId");

		if (folderId != null) {
			setFolderId(folderId);
		}

		String logReason = (String)attributes.get("logReason");

		if (logReason != null) {
			setLogReason(logReason);
		}
	}

	/**
	 * Returns the company ID of this validation log.
	 *
	 * @return the company ID of this validation log
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this validation log.
	 *
	 * @return the create date of this validation log
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the file name of this validation log.
	 *
	 * @return the file name of this validation log
	 */
	@Override
	public String getFileName() {
		return model.getFileName();
	}

	/**
	 * Returns the folder ID of this validation log.
	 *
	 * @return the folder ID of this validation log
	 */
	@Override
	public long getFolderId() {
		return model.getFolderId();
	}

	/**
	 * Returns the group ID of this validation log.
	 *
	 * @return the group ID of this validation log
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the log reason of this validation log.
	 *
	 * @return the log reason of this validation log
	 */
	@Override
	public String getLogReason() {
		return model.getLogReason();
	}

	/**
	 * Returns the primary key of this validation log.
	 *
	 * @return the primary key of this validation log
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the uploaded by of this validation log.
	 *
	 * @return the uploaded by of this validation log
	 */
	@Override
	public String getUploadedBy() {
		return model.getUploadedBy();
	}

	/**
	 * Returns the uploaded from of this validation log.
	 *
	 * @return the uploaded from of this validation log
	 */
	@Override
	public String getUploadedFrom() {
		return model.getUploadedFrom();
	}

	/**
	 * Returns the validation log ID of this validation log.
	 *
	 * @return the validation log ID of this validation log
	 */
	@Override
	public long getValidationLogId() {
		return model.getValidationLogId();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the company ID of this validation log.
	 *
	 * @param companyId the company ID of this validation log
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this validation log.
	 *
	 * @param createDate the create date of this validation log
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the file name of this validation log.
	 *
	 * @param fileName the file name of this validation log
	 */
	@Override
	public void setFileName(String fileName) {
		model.setFileName(fileName);
	}

	/**
	 * Sets the folder ID of this validation log.
	 *
	 * @param folderId the folder ID of this validation log
	 */
	@Override
	public void setFolderId(long folderId) {
		model.setFolderId(folderId);
	}

	/**
	 * Sets the group ID of this validation log.
	 *
	 * @param groupId the group ID of this validation log
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the log reason of this validation log.
	 *
	 * @param logReason the log reason of this validation log
	 */
	@Override
	public void setLogReason(String logReason) {
		model.setLogReason(logReason);
	}

	/**
	 * Sets the primary key of this validation log.
	 *
	 * @param primaryKey the primary key of this validation log
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the uploaded by of this validation log.
	 *
	 * @param uploadedBy the uploaded by of this validation log
	 */
	@Override
	public void setUploadedBy(String uploadedBy) {
		model.setUploadedBy(uploadedBy);
	}

	/**
	 * Sets the uploaded from of this validation log.
	 *
	 * @param uploadedFrom the uploaded from of this validation log
	 */
	@Override
	public void setUploadedFrom(String uploadedFrom) {
		model.setUploadedFrom(uploadedFrom);
	}

	/**
	 * Sets the validation log ID of this validation log.
	 *
	 * @param validationLogId the validation log ID of this validation log
	 */
	@Override
	public void setValidationLogId(long validationLogId) {
		model.setValidationLogId(validationLogId);
	}

	@Override
	protected ValidationLogWrapper wrap(ValidationLog validationLog) {
		return new ValidationLogWrapper(validationLog);
	}

}