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

import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link LinkMenu}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LinkMenu
 * @generated
 */
public class LinkMenuWrapper
	extends BaseModelWrapper<LinkMenu>
	implements LinkMenu, ModelWrapper<LinkMenu> {

	public LinkMenuWrapper(LinkMenu linkMenu) {
		super(linkMenu);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("linkId", getLinkId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("linkName", getLinkName());
		attributes.put("link", getLink());
		attributes.put("linkPosition", getLinkPosition());
		attributes.put("iconName", getIconName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long linkId = (Long)attributes.get("linkId");

		if (linkId != null) {
			setLinkId(linkId);
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

		String linkName = (String)attributes.get("linkName");

		if (linkName != null) {
			setLinkName(linkName);
		}

		String link = (String)attributes.get("link");

		if (link != null) {
			setLink(link);
		}

		Integer linkPosition = (Integer)attributes.get("linkPosition");

		if (linkPosition != null) {
			setLinkPosition(linkPosition);
		}

		String iconName = (String)attributes.get("iconName");

		if (iconName != null) {
			setIconName(iconName);
		}
	}

	/**
	 * Returns the company ID of this link menu.
	 *
	 * @return the company ID of this link menu
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this link menu.
	 *
	 * @return the create date of this link menu
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the group ID of this link menu.
	 *
	 * @return the group ID of this link menu
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the icon name of this link menu.
	 *
	 * @return the icon name of this link menu
	 */
	@Override
	public String getIconName() {
		return model.getIconName();
	}

	/**
	 * Returns the link of this link menu.
	 *
	 * @return the link of this link menu
	 */
	@Override
	public String getLink() {
		return model.getLink();
	}

	/**
	 * Returns the link ID of this link menu.
	 *
	 * @return the link ID of this link menu
	 */
	@Override
	public long getLinkId() {
		return model.getLinkId();
	}

	/**
	 * Returns the link name of this link menu.
	 *
	 * @return the link name of this link menu
	 */
	@Override
	public String getLinkName() {
		return model.getLinkName();
	}

	/**
	 * Returns the link position of this link menu.
	 *
	 * @return the link position of this link menu
	 */
	@Override
	public int getLinkPosition() {
		return model.getLinkPosition();
	}

	/**
	 * Returns the modified date of this link menu.
	 *
	 * @return the modified date of this link menu
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this link menu.
	 *
	 * @return the primary key of this link menu
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the user ID of this link menu.
	 *
	 * @return the user ID of this link menu
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this link menu.
	 *
	 * @return the user name of this link menu
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this link menu.
	 *
	 * @return the user uuid of this link menu
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this link menu.
	 *
	 * @return the uuid of this link menu
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
	 * Sets the company ID of this link menu.
	 *
	 * @param companyId the company ID of this link menu
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this link menu.
	 *
	 * @param createDate the create date of this link menu
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the group ID of this link menu.
	 *
	 * @param groupId the group ID of this link menu
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the icon name of this link menu.
	 *
	 * @param iconName the icon name of this link menu
	 */
	@Override
	public void setIconName(String iconName) {
		model.setIconName(iconName);
	}

	/**
	 * Sets the link of this link menu.
	 *
	 * @param link the link of this link menu
	 */
	@Override
	public void setLink(String link) {
		model.setLink(link);
	}

	/**
	 * Sets the link ID of this link menu.
	 *
	 * @param linkId the link ID of this link menu
	 */
	@Override
	public void setLinkId(long linkId) {
		model.setLinkId(linkId);
	}

	/**
	 * Sets the link name of this link menu.
	 *
	 * @param linkName the link name of this link menu
	 */
	@Override
	public void setLinkName(String linkName) {
		model.setLinkName(linkName);
	}

	/**
	 * Sets the link position of this link menu.
	 *
	 * @param linkPosition the link position of this link menu
	 */
	@Override
	public void setLinkPosition(int linkPosition) {
		model.setLinkPosition(linkPosition);
	}

	/**
	 * Sets the modified date of this link menu.
	 *
	 * @param modifiedDate the modified date of this link menu
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the primary key of this link menu.
	 *
	 * @param primaryKey the primary key of this link menu
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the user ID of this link menu.
	 *
	 * @param userId the user ID of this link menu
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this link menu.
	 *
	 * @param userName the user name of this link menu
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this link menu.
	 *
	 * @param userUuid the user uuid of this link menu
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this link menu.
	 *
	 * @param uuid the uuid of this link menu
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
	protected LinkMenuWrapper wrap(LinkMenu linkMenu) {
		return new LinkMenuWrapper(linkMenu);
	}

}