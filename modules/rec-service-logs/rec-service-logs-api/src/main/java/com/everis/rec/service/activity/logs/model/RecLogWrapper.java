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

package com.everis.rec.service.activity.logs.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link RecLog}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RecLog
 * @generated
 */
public class RecLogWrapper
	extends BaseModelWrapper<RecLog> implements ModelWrapper<RecLog>, RecLog {

	public RecLogWrapper(RecLog recLog) {
		super(recLog);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("activityLogId", getActivityLogId());
		attributes.put("groupId", getGroupId());
		attributes.put("createDate", getCreateDate());
		attributes.put("logMessage", getLogMessage());
		attributes.put("className", getClassName());
		attributes.put("classPK", getClassPK());
		attributes.put("comments", getComments());
		attributes.put("type", getType());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long activityLogId = (Long)attributes.get("activityLogId");

		if (activityLogId != null) {
			setActivityLogId(activityLogId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		String logMessage = (String)attributes.get("logMessage");

		if (logMessage != null) {
			setLogMessage(logMessage);
		}

		String className = (String)attributes.get("className");

		if (className != null) {
			setClassName(className);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		String comments = (String)attributes.get("comments");

		if (comments != null) {
			setComments(comments);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}
	}

	/**
	 * Returns the activity log ID of this rec log.
	 *
	 * @return the activity log ID of this rec log
	 */
	@Override
	public long getActivityLogId() {
		return model.getActivityLogId();
	}

	/**
	 * Returns the class name of this rec log.
	 *
	 * @return the class name of this rec log
	 */
	@Override
	public String getClassName() {
		return model.getClassName();
	}

	/**
	 * Returns the class pk of this rec log.
	 *
	 * @return the class pk of this rec log
	 */
	@Override
	public Long getClassPK() {
		return model.getClassPK();
	}

	/**
	 * Returns the comments of this rec log.
	 *
	 * @return the comments of this rec log
	 */
	@Override
	public String getComments() {
		return model.getComments();
	}

	/**
	 * Returns the create date of this rec log.
	 *
	 * @return the create date of this rec log
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the group ID of this rec log.
	 *
	 * @return the group ID of this rec log
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the log message of this rec log.
	 *
	 * @return the log message of this rec log
	 */
	@Override
	public String getLogMessage() {
		return model.getLogMessage();
	}

	/**
	 * Returns the primary key of this rec log.
	 *
	 * @return the primary key of this rec log
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the type of this rec log.
	 *
	 * @return the type of this rec log
	 */
	@Override
	public String getType() {
		return model.getType();
	}

	/**
	 * Returns the uuid of this rec log.
	 *
	 * @return the uuid of this rec log
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
	 * Sets the activity log ID of this rec log.
	 *
	 * @param activityLogId the activity log ID of this rec log
	 */
	@Override
	public void setActivityLogId(long activityLogId) {
		model.setActivityLogId(activityLogId);
	}

	/**
	 * Sets the class name of this rec log.
	 *
	 * @param className the class name of this rec log
	 */
	@Override
	public void setClassName(String className) {
		model.setClassName(className);
	}

	/**
	 * Sets the class pk of this rec log.
	 *
	 * @param classPK the class pk of this rec log
	 */
	@Override
	public void setClassPK(Long classPK) {
		model.setClassPK(classPK);
	}

	/**
	 * Sets the comments of this rec log.
	 *
	 * @param comments the comments of this rec log
	 */
	@Override
	public void setComments(String comments) {
		model.setComments(comments);
	}

	/**
	 * Sets the create date of this rec log.
	 *
	 * @param createDate the create date of this rec log
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the group ID of this rec log.
	 *
	 * @param groupId the group ID of this rec log
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the log message of this rec log.
	 *
	 * @param logMessage the log message of this rec log
	 */
	@Override
	public void setLogMessage(String logMessage) {
		model.setLogMessage(logMessage);
	}

	/**
	 * Sets the primary key of this rec log.
	 *
	 * @param primaryKey the primary key of this rec log
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the type of this rec log.
	 *
	 * @param type the type of this rec log
	 */
	@Override
	public void setType(String type) {
		model.setType(type);
	}

	/**
	 * Sets the uuid of this rec log.
	 *
	 * @param uuid the uuid of this rec log
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	protected RecLogWrapper wrap(RecLog recLog) {
		return new RecLogWrapper(recLog);
	}

}