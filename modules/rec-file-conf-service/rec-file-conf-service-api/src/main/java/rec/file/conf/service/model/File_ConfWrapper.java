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

package rec.file.conf.service.model;

import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link File_Conf}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see File_Conf
 * @generated
 */
public class File_ConfWrapper
	extends BaseModelWrapper<File_Conf>
	implements File_Conf, ModelWrapper<File_Conf> {

	public File_ConfWrapper(File_Conf file_Conf) {
		super(file_Conf);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("docConfigId", getDocConfigId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("docConfName", getDocConfName());
		attributes.put("docFileType", getDocFileType());
		attributes.put("docConfigJSON", getDocConfigJSON());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long docConfigId = (Long)attributes.get("docConfigId");

		if (docConfigId != null) {
			setDocConfigId(docConfigId);
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

		String docConfName = (String)attributes.get("docConfName");

		if (docConfName != null) {
			setDocConfName(docConfName);
		}

		String docFileType = (String)attributes.get("docFileType");

		if (docFileType != null) {
			setDocFileType(docFileType);
		}

		String docConfigJSON = (String)attributes.get("docConfigJSON");

		if (docConfigJSON != null) {
			setDocConfigJSON(docConfigJSON);
		}
	}

	/**
	 * Returns the company ID of this file_ conf.
	 *
	 * @return the company ID of this file_ conf
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this file_ conf.
	 *
	 * @return the create date of this file_ conf
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the doc config ID of this file_ conf.
	 *
	 * @return the doc config ID of this file_ conf
	 */
	@Override
	public long getDocConfigId() {
		return model.getDocConfigId();
	}

	/**
	 * Returns the doc config json of this file_ conf.
	 *
	 * @return the doc config json of this file_ conf
	 */
	@Override
	public String getDocConfigJSON() {
		return model.getDocConfigJSON();
	}

	/**
	 * Returns the doc conf name of this file_ conf.
	 *
	 * @return the doc conf name of this file_ conf
	 */
	@Override
	public String getDocConfName() {
		return model.getDocConfName();
	}

	/**
	 * Returns the doc file type of this file_ conf.
	 *
	 * @return the doc file type of this file_ conf
	 */
	@Override
	public String getDocFileType() {
		return model.getDocFileType();
	}

	/**
	 * Returns the group ID of this file_ conf.
	 *
	 * @return the group ID of this file_ conf
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the modified date of this file_ conf.
	 *
	 * @return the modified date of this file_ conf
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this file_ conf.
	 *
	 * @return the primary key of this file_ conf
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the user ID of this file_ conf.
	 *
	 * @return the user ID of this file_ conf
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this file_ conf.
	 *
	 * @return the user name of this file_ conf
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this file_ conf.
	 *
	 * @return the user uuid of this file_ conf
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this file_ conf.
	 *
	 * @return the uuid of this file_ conf
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
	 * Sets the company ID of this file_ conf.
	 *
	 * @param companyId the company ID of this file_ conf
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this file_ conf.
	 *
	 * @param createDate the create date of this file_ conf
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the doc config ID of this file_ conf.
	 *
	 * @param docConfigId the doc config ID of this file_ conf
	 */
	@Override
	public void setDocConfigId(long docConfigId) {
		model.setDocConfigId(docConfigId);
	}

	/**
	 * Sets the doc config json of this file_ conf.
	 *
	 * @param docConfigJSON the doc config json of this file_ conf
	 */
	@Override
	public void setDocConfigJSON(String docConfigJSON) {
		model.setDocConfigJSON(docConfigJSON);
	}

	/**
	 * Sets the doc conf name of this file_ conf.
	 *
	 * @param docConfName the doc conf name of this file_ conf
	 */
	@Override
	public void setDocConfName(String docConfName) {
		model.setDocConfName(docConfName);
	}

	/**
	 * Sets the doc file type of this file_ conf.
	 *
	 * @param docFileType the doc file type of this file_ conf
	 */
	@Override
	public void setDocFileType(String docFileType) {
		model.setDocFileType(docFileType);
	}

	/**
	 * Sets the group ID of this file_ conf.
	 *
	 * @param groupId the group ID of this file_ conf
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the modified date of this file_ conf.
	 *
	 * @param modifiedDate the modified date of this file_ conf
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the primary key of this file_ conf.
	 *
	 * @param primaryKey the primary key of this file_ conf
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the user ID of this file_ conf.
	 *
	 * @param userId the user ID of this file_ conf
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this file_ conf.
	 *
	 * @param userName the user name of this file_ conf
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this file_ conf.
	 *
	 * @param userUuid the user uuid of this file_ conf
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this file_ conf.
	 *
	 * @param uuid the uuid of this file_ conf
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
	protected File_ConfWrapper wrap(File_Conf file_Conf) {
		return new File_ConfWrapper(file_Conf);
	}

}