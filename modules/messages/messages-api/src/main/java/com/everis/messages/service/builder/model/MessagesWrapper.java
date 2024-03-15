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

package com.everis.messages.service.builder.model;

import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Messages}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Messages
 * @generated
 */
public class MessagesWrapper
	extends BaseModelWrapper<Messages>
	implements Messages, ModelWrapper<Messages> {

	public MessagesWrapper(Messages messages) {
		super(messages);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("notificationEngineId", getNotificationEngineId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("subject", getSubject());
		attributes.put("body", getBody());
		attributes.put("status", isStatus());
		attributes.put("tags", getTags());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long notificationEngineId = (Long)attributes.get(
			"notificationEngineId");

		if (notificationEngineId != null) {
			setNotificationEngineId(notificationEngineId);
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

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String subject = (String)attributes.get("subject");

		if (subject != null) {
			setSubject(subject);
		}

		String body = (String)attributes.get("body");

		if (body != null) {
			setBody(body);
		}

		Boolean status = (Boolean)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		String tags = (String)attributes.get("tags");

		if (tags != null) {
			setTags(tags);
		}
	}

	/**
	 * Returns the body of this messages.
	 *
	 * @return the body of this messages
	 */
	@Override
	public String getBody() {
		return model.getBody();
	}

	/**
	 * Returns the company ID of this messages.
	 *
	 * @return the company ID of this messages
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this messages.
	 *
	 * @return the create date of this messages
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the group ID of this messages.
	 *
	 * @return the group ID of this messages
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the modified date of this messages.
	 *
	 * @return the modified date of this messages
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the name of this messages.
	 *
	 * @return the name of this messages
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the notification engine ID of this messages.
	 *
	 * @return the notification engine ID of this messages
	 */
	@Override
	public long getNotificationEngineId() {
		return model.getNotificationEngineId();
	}

	/**
	 * Returns the primary key of this messages.
	 *
	 * @return the primary key of this messages
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the status of this messages.
	 *
	 * @return the status of this messages
	 */
	@Override
	public boolean getStatus() {
		return model.getStatus();
	}

	/**
	 * Returns the subject of this messages.
	 *
	 * @return the subject of this messages
	 */
	@Override
	public String getSubject() {
		return model.getSubject();
	}

	/**
	 * Returns the tags of this messages.
	 *
	 * @return the tags of this messages
	 */
	@Override
	public String getTags() {
		return model.getTags();
	}

	/**
	 * Returns the user ID of this messages.
	 *
	 * @return the user ID of this messages
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this messages.
	 *
	 * @return the user name of this messages
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this messages.
	 *
	 * @return the user uuid of this messages
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this messages.
	 *
	 * @return the uuid of this messages
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	/**
	 * Returns <code>true</code> if this messages is status.
	 *
	 * @return <code>true</code> if this messages is status; <code>false</code> otherwise
	 */
	@Override
	public boolean isStatus() {
		return model.isStatus();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the body of this messages.
	 *
	 * @param body the body of this messages
	 */
	@Override
	public void setBody(String body) {
		model.setBody(body);
	}

	/**
	 * Sets the company ID of this messages.
	 *
	 * @param companyId the company ID of this messages
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this messages.
	 *
	 * @param createDate the create date of this messages
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the group ID of this messages.
	 *
	 * @param groupId the group ID of this messages
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the modified date of this messages.
	 *
	 * @param modifiedDate the modified date of this messages
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the name of this messages.
	 *
	 * @param name the name of this messages
	 */
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	 * Sets the notification engine ID of this messages.
	 *
	 * @param notificationEngineId the notification engine ID of this messages
	 */
	@Override
	public void setNotificationEngineId(long notificationEngineId) {
		model.setNotificationEngineId(notificationEngineId);
	}

	/**
	 * Sets the primary key of this messages.
	 *
	 * @param primaryKey the primary key of this messages
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets whether this messages is status.
	 *
	 * @param status the status of this messages
	 */
	@Override
	public void setStatus(boolean status) {
		model.setStatus(status);
	}

	/**
	 * Sets the subject of this messages.
	 *
	 * @param subject the subject of this messages
	 */
	@Override
	public void setSubject(String subject) {
		model.setSubject(subject);
	}

	/**
	 * Sets the tags of this messages.
	 *
	 * @param tags the tags of this messages
	 */
	@Override
	public void setTags(String tags) {
		model.setTags(tags);
	}

	/**
	 * Sets the user ID of this messages.
	 *
	 * @param userId the user ID of this messages
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this messages.
	 *
	 * @param userName the user name of this messages
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this messages.
	 *
	 * @param userUuid the user uuid of this messages
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this messages.
	 *
	 * @param uuid the uuid of this messages
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
	protected MessagesWrapper wrap(Messages messages) {
		return new MessagesWrapper(messages);
	}

}