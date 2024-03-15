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

import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link MaintenanceActivity}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MaintenanceActivity
 * @generated
 */
public class MaintenanceActivityWrapper
	extends BaseModelWrapper<MaintenanceActivity>
	implements MaintenanceActivity, ModelWrapper<MaintenanceActivity> {

	public MaintenanceActivityWrapper(MaintenanceActivity maintenanceActivity) {
		super(maintenanceActivity);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("maintenanceactivityId", getMaintenanceactivityId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("activityTitle", getActivityTitle());
		attributes.put("dueDate", getDueDate());
		attributes.put("status", getStatus());
		attributes.put("orgSiteId", getOrgSiteId());
		attributes.put("specificParty", getSpecificParty());
		attributes.put("dueDateFormated", getDueDateFormated());
		attributes.put("calendarBookingId", getCalendarBookingId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long maintenanceactivityId = (Long)attributes.get(
			"maintenanceactivityId");

		if (maintenanceactivityId != null) {
			setMaintenanceactivityId(maintenanceactivityId);
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

		String activityTitle = (String)attributes.get("activityTitle");

		if (activityTitle != null) {
			setActivityTitle(activityTitle);
		}

		String dueDate = (String)attributes.get("dueDate");

		if (dueDate != null) {
			setDueDate(dueDate);
		}

		String status = (String)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Long orgSiteId = (Long)attributes.get("orgSiteId");

		if (orgSiteId != null) {
			setOrgSiteId(orgSiteId);
		}

		String specificParty = (String)attributes.get("specificParty");

		if (specificParty != null) {
			setSpecificParty(specificParty);
		}

		Date dueDateFormated = (Date)attributes.get("dueDateFormated");

		if (dueDateFormated != null) {
			setDueDateFormated(dueDateFormated);
		}

		Long calendarBookingId = (Long)attributes.get("calendarBookingId");

		if (calendarBookingId != null) {
			setCalendarBookingId(calendarBookingId);
		}
	}

	/**
	 * Returns the activity title of this maintenance activity.
	 *
	 * @return the activity title of this maintenance activity
	 */
	@Override
	public String getActivityTitle() {
		return model.getActivityTitle();
	}

	/**
	 * Returns the calendar booking ID of this maintenance activity.
	 *
	 * @return the calendar booking ID of this maintenance activity
	 */
	@Override
	public long getCalendarBookingId() {
		return model.getCalendarBookingId();
	}

	/**
	 * Returns the company ID of this maintenance activity.
	 *
	 * @return the company ID of this maintenance activity
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this maintenance activity.
	 *
	 * @return the create date of this maintenance activity
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the due date of this maintenance activity.
	 *
	 * @return the due date of this maintenance activity
	 */
	@Override
	public String getDueDate() {
		return model.getDueDate();
	}

	/**
	 * Returns the due date formated of this maintenance activity.
	 *
	 * @return the due date formated of this maintenance activity
	 */
	@Override
	public Date getDueDateFormated() {
		return model.getDueDateFormated();
	}

	/**
	 * Returns the maintenanceactivity ID of this maintenance activity.
	 *
	 * @return the maintenanceactivity ID of this maintenance activity
	 */
	@Override
	public long getMaintenanceactivityId() {
		return model.getMaintenanceactivityId();
	}

	/**
	 * Returns the modified date of this maintenance activity.
	 *
	 * @return the modified date of this maintenance activity
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the org site ID of this maintenance activity.
	 *
	 * @return the org site ID of this maintenance activity
	 */
	@Override
	public long getOrgSiteId() {
		return model.getOrgSiteId();
	}

	/**
	 * Returns the primary key of this maintenance activity.
	 *
	 * @return the primary key of this maintenance activity
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the specific party of this maintenance activity.
	 *
	 * @return the specific party of this maintenance activity
	 */
	@Override
	public String getSpecificParty() {
		return model.getSpecificParty();
	}

	/**
	 * Returns the status of this maintenance activity.
	 *
	 * @return the status of this maintenance activity
	 */
	@Override
	public String getStatus() {
		return model.getStatus();
	}

	/**
	 * Returns the user ID of this maintenance activity.
	 *
	 * @return the user ID of this maintenance activity
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this maintenance activity.
	 *
	 * @return the user name of this maintenance activity
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this maintenance activity.
	 *
	 * @return the user uuid of this maintenance activity
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this maintenance activity.
	 *
	 * @return the uuid of this maintenance activity
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
	 * Sets the activity title of this maintenance activity.
	 *
	 * @param activityTitle the activity title of this maintenance activity
	 */
	@Override
	public void setActivityTitle(String activityTitle) {
		model.setActivityTitle(activityTitle);
	}

	/**
	 * Sets the calendar booking ID of this maintenance activity.
	 *
	 * @param calendarBookingId the calendar booking ID of this maintenance activity
	 */
	@Override
	public void setCalendarBookingId(long calendarBookingId) {
		model.setCalendarBookingId(calendarBookingId);
	}

	/**
	 * Sets the company ID of this maintenance activity.
	 *
	 * @param companyId the company ID of this maintenance activity
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this maintenance activity.
	 *
	 * @param createDate the create date of this maintenance activity
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the due date of this maintenance activity.
	 *
	 * @param dueDate the due date of this maintenance activity
	 */
	@Override
	public void setDueDate(String dueDate) {
		model.setDueDate(dueDate);
	}

	/**
	 * Sets the due date formated of this maintenance activity.
	 *
	 * @param dueDateFormated the due date formated of this maintenance activity
	 */
	@Override
	public void setDueDateFormated(Date dueDateFormated) {
		model.setDueDateFormated(dueDateFormated);
	}

	/**
	 * Sets the maintenanceactivity ID of this maintenance activity.
	 *
	 * @param maintenanceactivityId the maintenanceactivity ID of this maintenance activity
	 */
	@Override
	public void setMaintenanceactivityId(long maintenanceactivityId) {
		model.setMaintenanceactivityId(maintenanceactivityId);
	}

	/**
	 * Sets the modified date of this maintenance activity.
	 *
	 * @param modifiedDate the modified date of this maintenance activity
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the org site ID of this maintenance activity.
	 *
	 * @param orgSiteId the org site ID of this maintenance activity
	 */
	@Override
	public void setOrgSiteId(long orgSiteId) {
		model.setOrgSiteId(orgSiteId);
	}

	/**
	 * Sets the primary key of this maintenance activity.
	 *
	 * @param primaryKey the primary key of this maintenance activity
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the specific party of this maintenance activity.
	 *
	 * @param specificParty the specific party of this maintenance activity
	 */
	@Override
	public void setSpecificParty(String specificParty) {
		model.setSpecificParty(specificParty);
	}

	/**
	 * Sets the status of this maintenance activity.
	 *
	 * @param status the status of this maintenance activity
	 */
	@Override
	public void setStatus(String status) {
		model.setStatus(status);
	}

	/**
	 * Sets the user ID of this maintenance activity.
	 *
	 * @param userId the user ID of this maintenance activity
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this maintenance activity.
	 *
	 * @param userName the user name of this maintenance activity
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this maintenance activity.
	 *
	 * @param userUuid the user uuid of this maintenance activity
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this maintenance activity.
	 *
	 * @param uuid the uuid of this maintenance activity
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
	protected MaintenanceActivityWrapper wrap(
		MaintenanceActivity maintenanceActivity) {

		return new MaintenanceActivityWrapper(maintenanceActivity);
	}

}