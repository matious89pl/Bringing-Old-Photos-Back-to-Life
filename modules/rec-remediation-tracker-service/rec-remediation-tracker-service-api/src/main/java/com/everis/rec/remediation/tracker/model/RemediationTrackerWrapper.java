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

import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link RemediationTracker}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RemediationTracker
 * @generated
 */
public class RemediationTrackerWrapper
	extends BaseModelWrapper<RemediationTracker>
	implements ModelWrapper<RemediationTracker>, RemediationTracker {

	public RemediationTrackerWrapper(RemediationTracker remediationTracker) {
		super(remediationTracker);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("remediationTrackerId", getRemediationTrackerId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("title", getTitle());
		attributes.put("orgSiteId", getOrgSiteId());
		attributes.put("specificParty", getSpecificParty());
		attributes.put("category", getCategory());
		attributes.put("description", getDescription());
		attributes.put("dueDate", getDueDate());
		attributes.put("dueDateFormated", getDueDateFormated());
		attributes.put("status", getStatus());
		attributes.put("calendarBookingId", getCalendarBookingId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long remediationTrackerId = (Long)attributes.get(
			"remediationTrackerId");

		if (remediationTrackerId != null) {
			setRemediationTrackerId(remediationTrackerId);
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

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		Long orgSiteId = (Long)attributes.get("orgSiteId");

		if (orgSiteId != null) {
			setOrgSiteId(orgSiteId);
		}

		String specificParty = (String)attributes.get("specificParty");

		if (specificParty != null) {
			setSpecificParty(specificParty);
		}

		String category = (String)attributes.get("category");

		if (category != null) {
			setCategory(category);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String dueDate = (String)attributes.get("dueDate");

		if (dueDate != null) {
			setDueDate(dueDate);
		}

		Date dueDateFormated = (Date)attributes.get("dueDateFormated");

		if (dueDateFormated != null) {
			setDueDateFormated(dueDateFormated);
		}

		String status = (String)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Long calendarBookingId = (Long)attributes.get("calendarBookingId");

		if (calendarBookingId != null) {
			setCalendarBookingId(calendarBookingId);
		}
	}

	/**
	 * Returns the calendar booking ID of this remediation tracker.
	 *
	 * @return the calendar booking ID of this remediation tracker
	 */
	@Override
	public long getCalendarBookingId() {
		return model.getCalendarBookingId();
	}

	/**
	 * Returns the category of this remediation tracker.
	 *
	 * @return the category of this remediation tracker
	 */
	@Override
	public String getCategory() {
		return model.getCategory();
	}

	/**
	 * Returns the company ID of this remediation tracker.
	 *
	 * @return the company ID of this remediation tracker
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this remediation tracker.
	 *
	 * @return the create date of this remediation tracker
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the description of this remediation tracker.
	 *
	 * @return the description of this remediation tracker
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	/**
	 * Returns the due date of this remediation tracker.
	 *
	 * @return the due date of this remediation tracker
	 */
	@Override
	public String getDueDate() {
		return model.getDueDate();
	}

	/**
	 * Returns the due date formated of this remediation tracker.
	 *
	 * @return the due date formated of this remediation tracker
	 */
	@Override
	public Date getDueDateFormated() {
		return model.getDueDateFormated();
	}

	/**
	 * Returns the group ID of this remediation tracker.
	 *
	 * @return the group ID of this remediation tracker
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the modified date of this remediation tracker.
	 *
	 * @return the modified date of this remediation tracker
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the org site ID of this remediation tracker.
	 *
	 * @return the org site ID of this remediation tracker
	 */
	@Override
	public long getOrgSiteId() {
		return model.getOrgSiteId();
	}

	/**
	 * Returns the primary key of this remediation tracker.
	 *
	 * @return the primary key of this remediation tracker
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the remediation tracker ID of this remediation tracker.
	 *
	 * @return the remediation tracker ID of this remediation tracker
	 */
	@Override
	public long getRemediationTrackerId() {
		return model.getRemediationTrackerId();
	}

	/**
	 * Returns the specific party of this remediation tracker.
	 *
	 * @return the specific party of this remediation tracker
	 */
	@Override
	public String getSpecificParty() {
		return model.getSpecificParty();
	}

	/**
	 * Returns the status of this remediation tracker.
	 *
	 * @return the status of this remediation tracker
	 */
	@Override
	public String getStatus() {
		return model.getStatus();
	}

	/**
	 * Returns the title of this remediation tracker.
	 *
	 * @return the title of this remediation tracker
	 */
	@Override
	public String getTitle() {
		return model.getTitle();
	}

	/**
	 * Returns the user ID of this remediation tracker.
	 *
	 * @return the user ID of this remediation tracker
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this remediation tracker.
	 *
	 * @return the user name of this remediation tracker
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this remediation tracker.
	 *
	 * @return the user uuid of this remediation tracker
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this remediation tracker.
	 *
	 * @return the uuid of this remediation tracker
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the calendar booking ID of this remediation tracker.
	 *
	 * @param calendarBookingId the calendar booking ID of this remediation tracker
	 */
	@Override
	public void setCalendarBookingId(long calendarBookingId) {
		model.setCalendarBookingId(calendarBookingId);
	}

	/**
	 * Sets the category of this remediation tracker.
	 *
	 * @param category the category of this remediation tracker
	 */
	@Override
	public void setCategory(String category) {
		model.setCategory(category);
	}

	/**
	 * Sets the company ID of this remediation tracker.
	 *
	 * @param companyId the company ID of this remediation tracker
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this remediation tracker.
	 *
	 * @param createDate the create date of this remediation tracker
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the description of this remediation tracker.
	 *
	 * @param description the description of this remediation tracker
	 */
	@Override
	public void setDescription(String description) {
		model.setDescription(description);
	}

	/**
	 * Sets the due date of this remediation tracker.
	 *
	 * @param dueDate the due date of this remediation tracker
	 */
	@Override
	public void setDueDate(String dueDate) {
		model.setDueDate(dueDate);
	}

	/**
	 * Sets the due date formated of this remediation tracker.
	 *
	 * @param dueDateFormated the due date formated of this remediation tracker
	 */
	@Override
	public void setDueDateFormated(Date dueDateFormated) {
		model.setDueDateFormated(dueDateFormated);
	}

	/**
	 * Sets the group ID of this remediation tracker.
	 *
	 * @param groupId the group ID of this remediation tracker
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the modified date of this remediation tracker.
	 *
	 * @param modifiedDate the modified date of this remediation tracker
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the org site ID of this remediation tracker.
	 *
	 * @param orgSiteId the org site ID of this remediation tracker
	 */
	@Override
	public void setOrgSiteId(long orgSiteId) {
		model.setOrgSiteId(orgSiteId);
	}

	/**
	 * Sets the primary key of this remediation tracker.
	 *
	 * @param primaryKey the primary key of this remediation tracker
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the remediation tracker ID of this remediation tracker.
	 *
	 * @param remediationTrackerId the remediation tracker ID of this remediation tracker
	 */
	@Override
	public void setRemediationTrackerId(long remediationTrackerId) {
		model.setRemediationTrackerId(remediationTrackerId);
	}

	/**
	 * Sets the specific party of this remediation tracker.
	 *
	 * @param specificParty the specific party of this remediation tracker
	 */
	@Override
	public void setSpecificParty(String specificParty) {
		model.setSpecificParty(specificParty);
	}

	/**
	 * Sets the status of this remediation tracker.
	 *
	 * @param status the status of this remediation tracker
	 */
	@Override
	public void setStatus(String status) {
		model.setStatus(status);
	}

	/**
	 * Sets the title of this remediation tracker.
	 *
	 * @param title the title of this remediation tracker
	 */
	@Override
	public void setTitle(String title) {
		model.setTitle(title);
	}

	/**
	 * Sets the user ID of this remediation tracker.
	 *
	 * @param userId the user ID of this remediation tracker
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this remediation tracker.
	 *
	 * @param userName the user name of this remediation tracker
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this remediation tracker.
	 *
	 * @param userUuid the user uuid of this remediation tracker
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this remediation tracker.
	 *
	 * @param uuid the uuid of this remediation tracker
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	public StagedModelType getStagedModelType() {
		return model.getStagedModelType();
	}

	@Override
	protected RemediationTrackerWrapper wrap(
		RemediationTracker remediationTracker) {

		return new RemediationTrackerWrapper(remediationTracker);
	}

}