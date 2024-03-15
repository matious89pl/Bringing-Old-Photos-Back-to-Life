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

package rec.supporting.resources.model;

import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link supportR}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see supportR
 * @generated
 */
public class supportRWrapper
	extends BaseModelWrapper<supportR>
	implements ModelWrapper<supportR>, supportR {

	public supportRWrapper(supportR supportR) {
		super(supportR);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("supportRId", getSupportRId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("specificParty", getSpecificParty());
		attributes.put("orgSiteId", getOrgSiteId());
		attributes.put("type", getType());
		attributes.put("title", getTitle());
		attributes.put("description", getDescription());
		attributes.put("dueDate", getDueDate());
		attributes.put("status", getStatus());
		attributes.put("link", getLink());
		attributes.put("displayLink", getDisplayLink());
		attributes.put("calendarBookingId", getCalendarBookingId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long supportRId = (Long)attributes.get("supportRId");

		if (supportRId != null) {
			setSupportRId(supportRId);
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

		String specificParty = (String)attributes.get("specificParty");

		if (specificParty != null) {
			setSpecificParty(specificParty);
		}

		Long orgSiteId = (Long)attributes.get("orgSiteId");

		if (orgSiteId != null) {
			setOrgSiteId(orgSiteId);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Date dueDate = (Date)attributes.get("dueDate");

		if (dueDate != null) {
			setDueDate(dueDate);
		}

		String status = (String)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		String link = (String)attributes.get("link");

		if (link != null) {
			setLink(link);
		}

		String displayLink = (String)attributes.get("displayLink");

		if (displayLink != null) {
			setDisplayLink(displayLink);
		}

		Long calendarBookingId = (Long)attributes.get("calendarBookingId");

		if (calendarBookingId != null) {
			setCalendarBookingId(calendarBookingId);
		}
	}

	/**
	 * Returns the calendar booking ID of this support r.
	 *
	 * @return the calendar booking ID of this support r
	 */
	@Override
	public long getCalendarBookingId() {
		return model.getCalendarBookingId();
	}

	/**
	 * Returns the company ID of this support r.
	 *
	 * @return the company ID of this support r
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this support r.
	 *
	 * @return the create date of this support r
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the description of this support r.
	 *
	 * @return the description of this support r
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	/**
	 * Returns the display link of this support r.
	 *
	 * @return the display link of this support r
	 */
	@Override
	public String getDisplayLink() {
		return model.getDisplayLink();
	}

	/**
	 * Returns the due date of this support r.
	 *
	 * @return the due date of this support r
	 */
	@Override
	public Date getDueDate() {
		return model.getDueDate();
	}

	/**
	 * Returns the group ID of this support r.
	 *
	 * @return the group ID of this support r
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the link of this support r.
	 *
	 * @return the link of this support r
	 */
	@Override
	public String getLink() {
		return model.getLink();
	}

	/**
	 * Returns the modified date of this support r.
	 *
	 * @return the modified date of this support r
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the org site ID of this support r.
	 *
	 * @return the org site ID of this support r
	 */
	@Override
	public long getOrgSiteId() {
		return model.getOrgSiteId();
	}

	/**
	 * Returns the primary key of this support r.
	 *
	 * @return the primary key of this support r
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the specific party of this support r.
	 *
	 * @return the specific party of this support r
	 */
	@Override
	public String getSpecificParty() {
		return model.getSpecificParty();
	}

	/**
	 * Returns the status of this support r.
	 *
	 * @return the status of this support r
	 */
	@Override
	public String getStatus() {
		return model.getStatus();
	}

	/**
	 * Returns the support r ID of this support r.
	 *
	 * @return the support r ID of this support r
	 */
	@Override
	public long getSupportRId() {
		return model.getSupportRId();
	}

	/**
	 * Returns the title of this support r.
	 *
	 * @return the title of this support r
	 */
	@Override
	public String getTitle() {
		return model.getTitle();
	}

	/**
	 * Returns the type of this support r.
	 *
	 * @return the type of this support r
	 */
	@Override
	public String getType() {
		return model.getType();
	}

	/**
	 * Returns the user ID of this support r.
	 *
	 * @return the user ID of this support r
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this support r.
	 *
	 * @return the user name of this support r
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this support r.
	 *
	 * @return the user uuid of this support r
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this support r.
	 *
	 * @return the uuid of this support r
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
	 * Sets the calendar booking ID of this support r.
	 *
	 * @param calendarBookingId the calendar booking ID of this support r
	 */
	@Override
	public void setCalendarBookingId(long calendarBookingId) {
		model.setCalendarBookingId(calendarBookingId);
	}

	/**
	 * Sets the company ID of this support r.
	 *
	 * @param companyId the company ID of this support r
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this support r.
	 *
	 * @param createDate the create date of this support r
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the description of this support r.
	 *
	 * @param description the description of this support r
	 */
	@Override
	public void setDescription(String description) {
		model.setDescription(description);
	}

	/**
	 * Sets the display link of this support r.
	 *
	 * @param displayLink the display link of this support r
	 */
	@Override
	public void setDisplayLink(String displayLink) {
		model.setDisplayLink(displayLink);
	}

	/**
	 * Sets the due date of this support r.
	 *
	 * @param dueDate the due date of this support r
	 */
	@Override
	public void setDueDate(Date dueDate) {
		model.setDueDate(dueDate);
	}

	/**
	 * Sets the group ID of this support r.
	 *
	 * @param groupId the group ID of this support r
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the link of this support r.
	 *
	 * @param link the link of this support r
	 */
	@Override
	public void setLink(String link) {
		model.setLink(link);
	}

	/**
	 * Sets the modified date of this support r.
	 *
	 * @param modifiedDate the modified date of this support r
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the org site ID of this support r.
	 *
	 * @param orgSiteId the org site ID of this support r
	 */
	@Override
	public void setOrgSiteId(long orgSiteId) {
		model.setOrgSiteId(orgSiteId);
	}

	/**
	 * Sets the primary key of this support r.
	 *
	 * @param primaryKey the primary key of this support r
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the specific party of this support r.
	 *
	 * @param specificParty the specific party of this support r
	 */
	@Override
	public void setSpecificParty(String specificParty) {
		model.setSpecificParty(specificParty);
	}

	/**
	 * Sets the status of this support r.
	 *
	 * @param status the status of this support r
	 */
	@Override
	public void setStatus(String status) {
		model.setStatus(status);
	}

	/**
	 * Sets the support r ID of this support r.
	 *
	 * @param supportRId the support r ID of this support r
	 */
	@Override
	public void setSupportRId(long supportRId) {
		model.setSupportRId(supportRId);
	}

	/**
	 * Sets the title of this support r.
	 *
	 * @param title the title of this support r
	 */
	@Override
	public void setTitle(String title) {
		model.setTitle(title);
	}

	/**
	 * Sets the type of this support r.
	 *
	 * @param type the type of this support r
	 */
	@Override
	public void setType(String type) {
		model.setType(type);
	}

	/**
	 * Sets the user ID of this support r.
	 *
	 * @param userId the user ID of this support r
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this support r.
	 *
	 * @param userName the user name of this support r
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this support r.
	 *
	 * @param userUuid the user uuid of this support r
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this support r.
	 *
	 * @param uuid the uuid of this support r
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
	protected supportRWrapper wrap(supportR supportR) {
		return new supportRWrapper(supportR);
	}

}