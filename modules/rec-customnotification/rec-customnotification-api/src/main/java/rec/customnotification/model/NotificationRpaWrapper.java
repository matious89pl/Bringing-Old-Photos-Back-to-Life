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

package rec.customnotification.model;

import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link NotificationRpa}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see NotificationRpa
 * @generated
 */
public class NotificationRpaWrapper
	extends BaseModelWrapper<NotificationRpa>
	implements ModelWrapper<NotificationRpa>, NotificationRpa {

	public NotificationRpaWrapper(NotificationRpa notificationRpa) {
		super(notificationRpa);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("customNotificationId", getCustomNotificationId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("plidPage", getPlidPage());
		attributes.put("targetName", getTargetName());
		attributes.put("notificationTitle", getNotificationTitle());
		attributes.put("notificationBody", getNotificationBody());
		attributes.put("url", getUrl());
		attributes.put("deliveryMethod", getDeliveryMethod());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long customNotificationId = (Long)attributes.get(
			"customNotificationId");

		if (customNotificationId != null) {
			setCustomNotificationId(customNotificationId);
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

		Long plidPage = (Long)attributes.get("plidPage");

		if (plidPage != null) {
			setPlidPage(plidPage);
		}

		String targetName = (String)attributes.get("targetName");

		if (targetName != null) {
			setTargetName(targetName);
		}

		String notificationTitle = (String)attributes.get("notificationTitle");

		if (notificationTitle != null) {
			setNotificationTitle(notificationTitle);
		}

		String notificationBody = (String)attributes.get("notificationBody");

		if (notificationBody != null) {
			setNotificationBody(notificationBody);
		}

		String url = (String)attributes.get("url");

		if (url != null) {
			setUrl(url);
		}

		String deliveryMethod = (String)attributes.get("deliveryMethod");

		if (deliveryMethod != null) {
			setDeliveryMethod(deliveryMethod);
		}
	}

	/**
	 * Returns the company ID of this notification rpa.
	 *
	 * @return the company ID of this notification rpa
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this notification rpa.
	 *
	 * @return the create date of this notification rpa
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the custom notification ID of this notification rpa.
	 *
	 * @return the custom notification ID of this notification rpa
	 */
	@Override
	public long getCustomNotificationId() {
		return model.getCustomNotificationId();
	}

	/**
	 * Returns the delivery method of this notification rpa.
	 *
	 * @return the delivery method of this notification rpa
	 */
	@Override
	public String getDeliveryMethod() {
		return model.getDeliveryMethod();
	}

	/**
	 * Returns the group ID of this notification rpa.
	 *
	 * @return the group ID of this notification rpa
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the modified date of this notification rpa.
	 *
	 * @return the modified date of this notification rpa
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the notification body of this notification rpa.
	 *
	 * @return the notification body of this notification rpa
	 */
	@Override
	public String getNotificationBody() {
		return model.getNotificationBody();
	}

	/**
	 * Returns the notification title of this notification rpa.
	 *
	 * @return the notification title of this notification rpa
	 */
	@Override
	public String getNotificationTitle() {
		return model.getNotificationTitle();
	}

	/**
	 * Returns the plid page of this notification rpa.
	 *
	 * @return the plid page of this notification rpa
	 */
	@Override
	public long getPlidPage() {
		return model.getPlidPage();
	}

	/**
	 * Returns the primary key of this notification rpa.
	 *
	 * @return the primary key of this notification rpa
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the target name of this notification rpa.
	 *
	 * @return the target name of this notification rpa
	 */
	@Override
	public String getTargetName() {
		return model.getTargetName();
	}

	/**
	 * Returns the url of this notification rpa.
	 *
	 * @return the url of this notification rpa
	 */
	@Override
	public String getUrl() {
		return model.getUrl();
	}

	/**
	 * Returns the user ID of this notification rpa.
	 *
	 * @return the user ID of this notification rpa
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this notification rpa.
	 *
	 * @return the user name of this notification rpa
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this notification rpa.
	 *
	 * @return the user uuid of this notification rpa
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this notification rpa.
	 *
	 * @return the uuid of this notification rpa
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
	 * Sets the company ID of this notification rpa.
	 *
	 * @param companyId the company ID of this notification rpa
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this notification rpa.
	 *
	 * @param createDate the create date of this notification rpa
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the custom notification ID of this notification rpa.
	 *
	 * @param customNotificationId the custom notification ID of this notification rpa
	 */
	@Override
	public void setCustomNotificationId(long customNotificationId) {
		model.setCustomNotificationId(customNotificationId);
	}

	/**
	 * Sets the delivery method of this notification rpa.
	 *
	 * @param deliveryMethod the delivery method of this notification rpa
	 */
	@Override
	public void setDeliveryMethod(String deliveryMethod) {
		model.setDeliveryMethod(deliveryMethod);
	}

	/**
	 * Sets the group ID of this notification rpa.
	 *
	 * @param groupId the group ID of this notification rpa
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the modified date of this notification rpa.
	 *
	 * @param modifiedDate the modified date of this notification rpa
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the notification body of this notification rpa.
	 *
	 * @param notificationBody the notification body of this notification rpa
	 */
	@Override
	public void setNotificationBody(String notificationBody) {
		model.setNotificationBody(notificationBody);
	}

	/**
	 * Sets the notification title of this notification rpa.
	 *
	 * @param notificationTitle the notification title of this notification rpa
	 */
	@Override
	public void setNotificationTitle(String notificationTitle) {
		model.setNotificationTitle(notificationTitle);
	}

	/**
	 * Sets the plid page of this notification rpa.
	 *
	 * @param plidPage the plid page of this notification rpa
	 */
	@Override
	public void setPlidPage(long plidPage) {
		model.setPlidPage(plidPage);
	}

	/**
	 * Sets the primary key of this notification rpa.
	 *
	 * @param primaryKey the primary key of this notification rpa
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the target name of this notification rpa.
	 *
	 * @param targetName the target name of this notification rpa
	 */
	@Override
	public void setTargetName(String targetName) {
		model.setTargetName(targetName);
	}

	/**
	 * Sets the url of this notification rpa.
	 *
	 * @param url the url of this notification rpa
	 */
	@Override
	public void setUrl(String url) {
		model.setUrl(url);
	}

	/**
	 * Sets the user ID of this notification rpa.
	 *
	 * @param userId the user ID of this notification rpa
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this notification rpa.
	 *
	 * @param userName the user name of this notification rpa
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this notification rpa.
	 *
	 * @param userUuid the user uuid of this notification rpa
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this notification rpa.
	 *
	 * @param uuid the uuid of this notification rpa
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
	protected NotificationRpaWrapper wrap(NotificationRpa notificationRpa) {
		return new NotificationRpaWrapper(notificationRpa);
	}

}