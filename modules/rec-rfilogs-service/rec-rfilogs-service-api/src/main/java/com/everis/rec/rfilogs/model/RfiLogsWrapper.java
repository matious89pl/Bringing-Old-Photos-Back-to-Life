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

import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link RfiLogs}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RfiLogs
 * @generated
 */
public class RfiLogsWrapper
	extends BaseModelWrapper<RfiLogs>
	implements ModelWrapper<RfiLogs>, RfiLogs {

	public RfiLogsWrapper(RfiLogs rfiLogs) {
		super(rfiLogs);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("rfilogId", getRfilogId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("reqId", getReqId());
		attributes.put("specificParty", getSpecificParty());
		attributes.put("orgSiteId", getOrgSiteId());
		attributes.put("title", getTitle());
		attributes.put("dueDate", getDueDate());
		attributes.put("status", getStatus());
		attributes.put("reqDesc", getReqDesc());
		attributes.put("rationale", getRationale());
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

		Long rfilogId = (Long)attributes.get("rfilogId");

		if (rfilogId != null) {
			setRfilogId(rfilogId);
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

		Long reqId = (Long)attributes.get("reqId");

		if (reqId != null) {
			setReqId(reqId);
		}

		String specificParty = (String)attributes.get("specificParty");

		if (specificParty != null) {
			setSpecificParty(specificParty);
		}

		Long orgSiteId = (Long)attributes.get("orgSiteId");

		if (orgSiteId != null) {
			setOrgSiteId(orgSiteId);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String dueDate = (String)attributes.get("dueDate");

		if (dueDate != null) {
			setDueDate(dueDate);
		}

		String status = (String)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		String reqDesc = (String)attributes.get("reqDesc");

		if (reqDesc != null) {
			setReqDesc(reqDesc);
		}

		String rationale = (String)attributes.get("rationale");

		if (rationale != null) {
			setRationale(rationale);
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
	 * Returns the calendar booking ID of this rfi logs.
	 *
	 * @return the calendar booking ID of this rfi logs
	 */
	@Override
	public long getCalendarBookingId() {
		return model.getCalendarBookingId();
	}

	/**
	 * Returns the company ID of this rfi logs.
	 *
	 * @return the company ID of this rfi logs
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this rfi logs.
	 *
	 * @return the create date of this rfi logs
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the due date of this rfi logs.
	 *
	 * @return the due date of this rfi logs
	 */
	@Override
	public String getDueDate() {
		return model.getDueDate();
	}

	/**
	 * Returns the due date formated of this rfi logs.
	 *
	 * @return the due date formated of this rfi logs
	 */
	@Override
	public Date getDueDateFormated() {
		return model.getDueDateFormated();
	}

	/**
	 * Returns the modified date of this rfi logs.
	 *
	 * @return the modified date of this rfi logs
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the org site ID of this rfi logs.
	 *
	 * @return the org site ID of this rfi logs
	 */
	@Override
	public long getOrgSiteId() {
		return model.getOrgSiteId();
	}

	/**
	 * Returns the primary key of this rfi logs.
	 *
	 * @return the primary key of this rfi logs
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the rationale of this rfi logs.
	 *
	 * @return the rationale of this rfi logs
	 */
	@Override
	public String getRationale() {
		return model.getRationale();
	}

	/**
	 * Returns the req desc of this rfi logs.
	 *
	 * @return the req desc of this rfi logs
	 */
	@Override
	public String getReqDesc() {
		return model.getReqDesc();
	}

	/**
	 * Returns the req ID of this rfi logs.
	 *
	 * @return the req ID of this rfi logs
	 */
	@Override
	public long getReqId() {
		return model.getReqId();
	}

	/**
	 * Returns the rfilog ID of this rfi logs.
	 *
	 * @return the rfilog ID of this rfi logs
	 */
	@Override
	public long getRfilogId() {
		return model.getRfilogId();
	}

	/**
	 * Returns the specific party of this rfi logs.
	 *
	 * @return the specific party of this rfi logs
	 */
	@Override
	public String getSpecificParty() {
		return model.getSpecificParty();
	}

	/**
	 * Returns the status of this rfi logs.
	 *
	 * @return the status of this rfi logs
	 */
	@Override
	public String getStatus() {
		return model.getStatus();
	}

	/**
	 * Returns the title of this rfi logs.
	 *
	 * @return the title of this rfi logs
	 */
	@Override
	public String getTitle() {
		return model.getTitle();
	}

	/**
	 * Returns the user ID of this rfi logs.
	 *
	 * @return the user ID of this rfi logs
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this rfi logs.
	 *
	 * @return the user name of this rfi logs
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this rfi logs.
	 *
	 * @return the user uuid of this rfi logs
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this rfi logs.
	 *
	 * @return the uuid of this rfi logs
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
	 * Sets the calendar booking ID of this rfi logs.
	 *
	 * @param calendarBookingId the calendar booking ID of this rfi logs
	 */
	@Override
	public void setCalendarBookingId(long calendarBookingId) {
		model.setCalendarBookingId(calendarBookingId);
	}

	/**
	 * Sets the company ID of this rfi logs.
	 *
	 * @param companyId the company ID of this rfi logs
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this rfi logs.
	 *
	 * @param createDate the create date of this rfi logs
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the due date of this rfi logs.
	 *
	 * @param dueDate the due date of this rfi logs
	 */
	@Override
	public void setDueDate(String dueDate) {
		model.setDueDate(dueDate);
	}

	/**
	 * Sets the due date formated of this rfi logs.
	 *
	 * @param dueDateFormated the due date formated of this rfi logs
	 */
	@Override
	public void setDueDateFormated(Date dueDateFormated) {
		model.setDueDateFormated(dueDateFormated);
	}

	/**
	 * Sets the modified date of this rfi logs.
	 *
	 * @param modifiedDate the modified date of this rfi logs
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the org site ID of this rfi logs.
	 *
	 * @param orgSiteId the org site ID of this rfi logs
	 */
	@Override
	public void setOrgSiteId(long orgSiteId) {
		model.setOrgSiteId(orgSiteId);
	}

	/**
	 * Sets the primary key of this rfi logs.
	 *
	 * @param primaryKey the primary key of this rfi logs
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the rationale of this rfi logs.
	 *
	 * @param rationale the rationale of this rfi logs
	 */
	@Override
	public void setRationale(String rationale) {
		model.setRationale(rationale);
	}

	/**
	 * Sets the req desc of this rfi logs.
	 *
	 * @param reqDesc the req desc of this rfi logs
	 */
	@Override
	public void setReqDesc(String reqDesc) {
		model.setReqDesc(reqDesc);
	}

	/**
	 * Sets the req ID of this rfi logs.
	 *
	 * @param reqId the req ID of this rfi logs
	 */
	@Override
	public void setReqId(long reqId) {
		model.setReqId(reqId);
	}

	/**
	 * Sets the rfilog ID of this rfi logs.
	 *
	 * @param rfilogId the rfilog ID of this rfi logs
	 */
	@Override
	public void setRfilogId(long rfilogId) {
		model.setRfilogId(rfilogId);
	}

	/**
	 * Sets the specific party of this rfi logs.
	 *
	 * @param specificParty the specific party of this rfi logs
	 */
	@Override
	public void setSpecificParty(String specificParty) {
		model.setSpecificParty(specificParty);
	}

	/**
	 * Sets the status of this rfi logs.
	 *
	 * @param status the status of this rfi logs
	 */
	@Override
	public void setStatus(String status) {
		model.setStatus(status);
	}

	/**
	 * Sets the title of this rfi logs.
	 *
	 * @param title the title of this rfi logs
	 */
	@Override
	public void setTitle(String title) {
		model.setTitle(title);
	}

	/**
	 * Sets the user ID of this rfi logs.
	 *
	 * @param userId the user ID of this rfi logs
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this rfi logs.
	 *
	 * @param userName the user name of this rfi logs
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this rfi logs.
	 *
	 * @param userUuid the user uuid of this rfi logs
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this rfi logs.
	 *
	 * @param uuid the uuid of this rfi logs
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
	protected RfiLogsWrapper wrap(RfiLogs rfiLogs) {
		return new RfiLogsWrapper(rfiLogs);
	}

}