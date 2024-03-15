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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link rec.customnotification.service.http.NotificationRpaServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class NotificationRpaSoap implements Serializable {

	public static NotificationRpaSoap toSoapModel(NotificationRpa model) {
		NotificationRpaSoap soapModel = new NotificationRpaSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setCustomNotificationId(model.getCustomNotificationId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setPlidPage(model.getPlidPage());
		soapModel.setTargetName(model.getTargetName());
		soapModel.setNotificationTitle(model.getNotificationTitle());
		soapModel.setNotificationBody(model.getNotificationBody());
		soapModel.setUrl(model.getUrl());
		soapModel.setDeliveryMethod(model.getDeliveryMethod());

		return soapModel;
	}

	public static NotificationRpaSoap[] toSoapModels(NotificationRpa[] models) {
		NotificationRpaSoap[] soapModels =
			new NotificationRpaSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static NotificationRpaSoap[][] toSoapModels(
		NotificationRpa[][] models) {

		NotificationRpaSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new NotificationRpaSoap[models.length][models[0].length];
		}
		else {
			soapModels = new NotificationRpaSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static NotificationRpaSoap[] toSoapModels(
		List<NotificationRpa> models) {

		List<NotificationRpaSoap> soapModels =
			new ArrayList<NotificationRpaSoap>(models.size());

		for (NotificationRpa model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new NotificationRpaSoap[soapModels.size()]);
	}

	public NotificationRpaSoap() {
	}

	public long getPrimaryKey() {
		return _customNotificationId;
	}

	public void setPrimaryKey(long pk) {
		setCustomNotificationId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getCustomNotificationId() {
		return _customNotificationId;
	}

	public void setCustomNotificationId(long customNotificationId) {
		_customNotificationId = customNotificationId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getPlidPage() {
		return _plidPage;
	}

	public void setPlidPage(long plidPage) {
		_plidPage = plidPage;
	}

	public String getTargetName() {
		return _targetName;
	}

	public void setTargetName(String targetName) {
		_targetName = targetName;
	}

	public String getNotificationTitle() {
		return _notificationTitle;
	}

	public void setNotificationTitle(String notificationTitle) {
		_notificationTitle = notificationTitle;
	}

	public String getNotificationBody() {
		return _notificationBody;
	}

	public void setNotificationBody(String notificationBody) {
		_notificationBody = notificationBody;
	}

	public String getUrl() {
		return _url;
	}

	public void setUrl(String url) {
		_url = url;
	}

	public String getDeliveryMethod() {
		return _deliveryMethod;
	}

	public void setDeliveryMethod(String deliveryMethod) {
		_deliveryMethod = deliveryMethod;
	}

	private String _uuid;
	private long _customNotificationId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _plidPage;
	private String _targetName;
	private String _notificationTitle;
	private String _notificationBody;
	private String _url;
	private String _deliveryMethod;

}