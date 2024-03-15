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

import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link MaintenanceActivityForms}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MaintenanceActivityForms
 * @generated
 */
public class MaintenanceActivityFormsWrapper
	extends BaseModelWrapper<MaintenanceActivityForms>
	implements MaintenanceActivityForms,
			   ModelWrapper<MaintenanceActivityForms> {

	public MaintenanceActivityFormsWrapper(
		MaintenanceActivityForms maintenanceActivityForms) {

		super(maintenanceActivityForms);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put(
			"maintenanceactivityformId", getMaintenanceactivityformId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("formType", getFormType());
		attributes.put("year", getYear());
		attributes.put("submitDate", getSubmitDate());
		attributes.put("approvalDate", getApprovalDate());
		attributes.put("submitDateFormatted", getSubmitDateFormatted());
		attributes.put("approvalDateFormatted", getApprovalDateFormatted());
		attributes.put("status", getStatus());
		attributes.put(
			"formInstanceRecordVersionId", getFormInstanceRecordVersionId());
		attributes.put("orgSiteId", getOrgSiteId());
		attributes.put("viewUrl", getViewUrl());
		attributes.put("formInstanceId", getFormInstanceId());
		attributes.put("formInstanceRecordId", getFormInstanceRecordId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long maintenanceactivityformId = (Long)attributes.get(
			"maintenanceactivityformId");

		if (maintenanceactivityformId != null) {
			setMaintenanceactivityformId(maintenanceactivityformId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String formType = (String)attributes.get("formType");

		if (formType != null) {
			setFormType(formType);
		}

		Integer year = (Integer)attributes.get("year");

		if (year != null) {
			setYear(year);
		}

		Date submitDate = (Date)attributes.get("submitDate");

		if (submitDate != null) {
			setSubmitDate(submitDate);
		}

		Date approvalDate = (Date)attributes.get("approvalDate");

		if (approvalDate != null) {
			setApprovalDate(approvalDate);
		}

		String submitDateFormatted = (String)attributes.get(
			"submitDateFormatted");

		if (submitDateFormatted != null) {
			setSubmitDateFormatted(submitDateFormatted);
		}

		String approvalDateFormatted = (String)attributes.get(
			"approvalDateFormatted");

		if (approvalDateFormatted != null) {
			setApprovalDateFormatted(approvalDateFormatted);
		}

		String status = (String)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Long formInstanceRecordVersionId = (Long)attributes.get(
			"formInstanceRecordVersionId");

		if (formInstanceRecordVersionId != null) {
			setFormInstanceRecordVersionId(formInstanceRecordVersionId);
		}

		Long orgSiteId = (Long)attributes.get("orgSiteId");

		if (orgSiteId != null) {
			setOrgSiteId(orgSiteId);
		}

		String viewUrl = (String)attributes.get("viewUrl");

		if (viewUrl != null) {
			setViewUrl(viewUrl);
		}

		Long formInstanceId = (Long)attributes.get("formInstanceId");

		if (formInstanceId != null) {
			setFormInstanceId(formInstanceId);
		}

		Long formInstanceRecordId = (Long)attributes.get(
			"formInstanceRecordId");

		if (formInstanceRecordId != null) {
			setFormInstanceRecordId(formInstanceRecordId);
		}
	}

	/**
	 * Returns the approval date of this maintenance activity forms.
	 *
	 * @return the approval date of this maintenance activity forms
	 */
	@Override
	public Date getApprovalDate() {
		return model.getApprovalDate();
	}

	/**
	 * Returns the approval date formatted of this maintenance activity forms.
	 *
	 * @return the approval date formatted of this maintenance activity forms
	 */
	@Override
	public String getApprovalDateFormatted() {
		return model.getApprovalDateFormatted();
	}

	/**
	 * Returns the company ID of this maintenance activity forms.
	 *
	 * @return the company ID of this maintenance activity forms
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this maintenance activity forms.
	 *
	 * @return the create date of this maintenance activity forms
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the form instance ID of this maintenance activity forms.
	 *
	 * @return the form instance ID of this maintenance activity forms
	 */
	@Override
	public long getFormInstanceId() {
		return model.getFormInstanceId();
	}

	/**
	 * Returns the form instance record ID of this maintenance activity forms.
	 *
	 * @return the form instance record ID of this maintenance activity forms
	 */
	@Override
	public long getFormInstanceRecordId() {
		return model.getFormInstanceRecordId();
	}

	/**
	 * Returns the form instance record version ID of this maintenance activity forms.
	 *
	 * @return the form instance record version ID of this maintenance activity forms
	 */
	@Override
	public long getFormInstanceRecordVersionId() {
		return model.getFormInstanceRecordVersionId();
	}

	/**
	 * Returns the form type of this maintenance activity forms.
	 *
	 * @return the form type of this maintenance activity forms
	 */
	@Override
	public String getFormType() {
		return model.getFormType();
	}

	/**
	 * Returns the group ID of this maintenance activity forms.
	 *
	 * @return the group ID of this maintenance activity forms
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the maintenanceactivityform ID of this maintenance activity forms.
	 *
	 * @return the maintenanceactivityform ID of this maintenance activity forms
	 */
	@Override
	public long getMaintenanceactivityformId() {
		return model.getMaintenanceactivityformId();
	}

	/**
	 * Returns the modified date of this maintenance activity forms.
	 *
	 * @return the modified date of this maintenance activity forms
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the org site ID of this maintenance activity forms.
	 *
	 * @return the org site ID of this maintenance activity forms
	 */
	@Override
	public long getOrgSiteId() {
		return model.getOrgSiteId();
	}

	/**
	 * Returns the primary key of this maintenance activity forms.
	 *
	 * @return the primary key of this maintenance activity forms
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the status of this maintenance activity forms.
	 *
	 * @return the status of this maintenance activity forms
	 */
	@Override
	public String getStatus() {
		return model.getStatus();
	}

	/**
	 * Returns the submit date of this maintenance activity forms.
	 *
	 * @return the submit date of this maintenance activity forms
	 */
	@Override
	public Date getSubmitDate() {
		return model.getSubmitDate();
	}

	/**
	 * Returns the submit date formatted of this maintenance activity forms.
	 *
	 * @return the submit date formatted of this maintenance activity forms
	 */
	@Override
	public String getSubmitDateFormatted() {
		return model.getSubmitDateFormatted();
	}

	/**
	 * Returns the user ID of this maintenance activity forms.
	 *
	 * @return the user ID of this maintenance activity forms
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this maintenance activity forms.
	 *
	 * @return the user name of this maintenance activity forms
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this maintenance activity forms.
	 *
	 * @return the user uuid of this maintenance activity forms
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this maintenance activity forms.
	 *
	 * @return the uuid of this maintenance activity forms
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	/**
	 * Returns the view url of this maintenance activity forms.
	 *
	 * @return the view url of this maintenance activity forms
	 */
	@Override
	public String getViewUrl() {
		return model.getViewUrl();
	}

	/**
	 * Returns the year of this maintenance activity forms.
	 *
	 * @return the year of this maintenance activity forms
	 */
	@Override
	public int getYear() {
		return model.getYear();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the approval date of this maintenance activity forms.
	 *
	 * @param approvalDate the approval date of this maintenance activity forms
	 */
	@Override
	public void setApprovalDate(Date approvalDate) {
		model.setApprovalDate(approvalDate);
	}

	/**
	 * Sets the approval date formatted of this maintenance activity forms.
	 *
	 * @param approvalDateFormatted the approval date formatted of this maintenance activity forms
	 */
	@Override
	public void setApprovalDateFormatted(String approvalDateFormatted) {
		model.setApprovalDateFormatted(approvalDateFormatted);
	}

	/**
	 * Sets the company ID of this maintenance activity forms.
	 *
	 * @param companyId the company ID of this maintenance activity forms
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this maintenance activity forms.
	 *
	 * @param createDate the create date of this maintenance activity forms
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the form instance ID of this maintenance activity forms.
	 *
	 * @param formInstanceId the form instance ID of this maintenance activity forms
	 */
	@Override
	public void setFormInstanceId(long formInstanceId) {
		model.setFormInstanceId(formInstanceId);
	}

	/**
	 * Sets the form instance record ID of this maintenance activity forms.
	 *
	 * @param formInstanceRecordId the form instance record ID of this maintenance activity forms
	 */
	@Override
	public void setFormInstanceRecordId(long formInstanceRecordId) {
		model.setFormInstanceRecordId(formInstanceRecordId);
	}

	/**
	 * Sets the form instance record version ID of this maintenance activity forms.
	 *
	 * @param formInstanceRecordVersionId the form instance record version ID of this maintenance activity forms
	 */
	@Override
	public void setFormInstanceRecordVersionId(
		long formInstanceRecordVersionId) {

		model.setFormInstanceRecordVersionId(formInstanceRecordVersionId);
	}

	/**
	 * Sets the form type of this maintenance activity forms.
	 *
	 * @param formType the form type of this maintenance activity forms
	 */
	@Override
	public void setFormType(String formType) {
		model.setFormType(formType);
	}

	/**
	 * Sets the group ID of this maintenance activity forms.
	 *
	 * @param groupId the group ID of this maintenance activity forms
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the maintenanceactivityform ID of this maintenance activity forms.
	 *
	 * @param maintenanceactivityformId the maintenanceactivityform ID of this maintenance activity forms
	 */
	@Override
	public void setMaintenanceactivityformId(long maintenanceactivityformId) {
		model.setMaintenanceactivityformId(maintenanceactivityformId);
	}

	/**
	 * Sets the modified date of this maintenance activity forms.
	 *
	 * @param modifiedDate the modified date of this maintenance activity forms
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the org site ID of this maintenance activity forms.
	 *
	 * @param orgSiteId the org site ID of this maintenance activity forms
	 */
	@Override
	public void setOrgSiteId(long orgSiteId) {
		model.setOrgSiteId(orgSiteId);
	}

	/**
	 * Sets the primary key of this maintenance activity forms.
	 *
	 * @param primaryKey the primary key of this maintenance activity forms
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the status of this maintenance activity forms.
	 *
	 * @param status the status of this maintenance activity forms
	 */
	@Override
	public void setStatus(String status) {
		model.setStatus(status);
	}

	/**
	 * Sets the submit date of this maintenance activity forms.
	 *
	 * @param submitDate the submit date of this maintenance activity forms
	 */
	@Override
	public void setSubmitDate(Date submitDate) {
		model.setSubmitDate(submitDate);
	}

	/**
	 * Sets the submit date formatted of this maintenance activity forms.
	 *
	 * @param submitDateFormatted the submit date formatted of this maintenance activity forms
	 */
	@Override
	public void setSubmitDateFormatted(String submitDateFormatted) {
		model.setSubmitDateFormatted(submitDateFormatted);
	}

	/**
	 * Sets the user ID of this maintenance activity forms.
	 *
	 * @param userId the user ID of this maintenance activity forms
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this maintenance activity forms.
	 *
	 * @param userName the user name of this maintenance activity forms
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this maintenance activity forms.
	 *
	 * @param userUuid the user uuid of this maintenance activity forms
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this maintenance activity forms.
	 *
	 * @param uuid the uuid of this maintenance activity forms
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	/**
	 * Sets the view url of this maintenance activity forms.
	 *
	 * @param viewUrl the view url of this maintenance activity forms
	 */
	@Override
	public void setViewUrl(String viewUrl) {
		model.setViewUrl(viewUrl);
	}

	/**
	 * Sets the year of this maintenance activity forms.
	 *
	 * @param year the year of this maintenance activity forms
	 */
	@Override
	public void setYear(int year) {
		model.setYear(year);
	}

	@Override
	public StagedModelType getStagedModelType() {
		return model.getStagedModelType();
	}

	@Override
	protected MaintenanceActivityFormsWrapper wrap(
		MaintenanceActivityForms maintenanceActivityForms) {

		return new MaintenanceActivityFormsWrapper(maintenanceActivityForms);
	}

}