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

package rec.link.menu.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link rec.link.menu.service.http.LinkMenuServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class LinkMenuSoap implements Serializable {

	public static LinkMenuSoap toSoapModel(LinkMenu model) {
		LinkMenuSoap soapModel = new LinkMenuSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setLinkId(model.getLinkId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setLinkName(model.getLinkName());
		soapModel.setLink(model.getLink());
		soapModel.setLinkPosition(model.getLinkPosition());
		soapModel.setIconName(model.getIconName());

		return soapModel;
	}

	public static LinkMenuSoap[] toSoapModels(LinkMenu[] models) {
		LinkMenuSoap[] soapModels = new LinkMenuSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LinkMenuSoap[][] toSoapModels(LinkMenu[][] models) {
		LinkMenuSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LinkMenuSoap[models.length][models[0].length];
		}
		else {
			soapModels = new LinkMenuSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LinkMenuSoap[] toSoapModels(List<LinkMenu> models) {
		List<LinkMenuSoap> soapModels = new ArrayList<LinkMenuSoap>(
			models.size());

		for (LinkMenu model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LinkMenuSoap[soapModels.size()]);
	}

	public LinkMenuSoap() {
	}

	public long getPrimaryKey() {
		return _linkId;
	}

	public void setPrimaryKey(long pk) {
		setLinkId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getLinkId() {
		return _linkId;
	}

	public void setLinkId(long linkId) {
		_linkId = linkId;
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

	public String getLinkName() {
		return _linkName;
	}

	public void setLinkName(String linkName) {
		_linkName = linkName;
	}

	public String getLink() {
		return _link;
	}

	public void setLink(String link) {
		_link = link;
	}

	public int getLinkPosition() {
		return _linkPosition;
	}

	public void setLinkPosition(int linkPosition) {
		_linkPosition = linkPosition;
	}

	public String getIconName() {
		return _iconName;
	}

	public void setIconName(String iconName) {
		_iconName = iconName;
	}

	private String _uuid;
	private long _linkId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _linkName;
	private String _link;
	private int _linkPosition;
	private String _iconName;

}