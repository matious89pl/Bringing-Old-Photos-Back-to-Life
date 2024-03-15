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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.GroupedModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedAuditedModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the File_Conf service. Represents a row in the &quot;REC_File_Conf&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>rec.file.conf.service.model.impl.File_ConfModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>rec.file.conf.service.model.impl.File_ConfImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see File_Conf
 * @generated
 */
@ProviderType
public interface File_ConfModel
	extends BaseModel<File_Conf>, GroupedModel, ShardedModel,
			StagedAuditedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a file_ conf model instance should use the {@link File_Conf} interface instead.
	 */

	/**
	 * Returns the primary key of this file_ conf.
	 *
	 * @return the primary key of this file_ conf
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this file_ conf.
	 *
	 * @param primaryKey the primary key of this file_ conf
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this file_ conf.
	 *
	 * @return the uuid of this file_ conf
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this file_ conf.
	 *
	 * @param uuid the uuid of this file_ conf
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the doc config ID of this file_ conf.
	 *
	 * @return the doc config ID of this file_ conf
	 */
	public long getDocConfigId();

	/**
	 * Sets the doc config ID of this file_ conf.
	 *
	 * @param docConfigId the doc config ID of this file_ conf
	 */
	public void setDocConfigId(long docConfigId);

	/**
	 * Returns the group ID of this file_ conf.
	 *
	 * @return the group ID of this file_ conf
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this file_ conf.
	 *
	 * @param groupId the group ID of this file_ conf
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this file_ conf.
	 *
	 * @return the company ID of this file_ conf
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this file_ conf.
	 *
	 * @param companyId the company ID of this file_ conf
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this file_ conf.
	 *
	 * @return the user ID of this file_ conf
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this file_ conf.
	 *
	 * @param userId the user ID of this file_ conf
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this file_ conf.
	 *
	 * @return the user uuid of this file_ conf
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this file_ conf.
	 *
	 * @param userUuid the user uuid of this file_ conf
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this file_ conf.
	 *
	 * @return the user name of this file_ conf
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this file_ conf.
	 *
	 * @param userName the user name of this file_ conf
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this file_ conf.
	 *
	 * @return the create date of this file_ conf
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this file_ conf.
	 *
	 * @param createDate the create date of this file_ conf
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this file_ conf.
	 *
	 * @return the modified date of this file_ conf
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this file_ conf.
	 *
	 * @param modifiedDate the modified date of this file_ conf
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the doc conf name of this file_ conf.
	 *
	 * @return the doc conf name of this file_ conf
	 */
	@AutoEscape
	public String getDocConfName();

	/**
	 * Sets the doc conf name of this file_ conf.
	 *
	 * @param docConfName the doc conf name of this file_ conf
	 */
	public void setDocConfName(String docConfName);

	/**
	 * Returns the doc file type of this file_ conf.
	 *
	 * @return the doc file type of this file_ conf
	 */
	@AutoEscape
	public String getDocFileType();

	/**
	 * Sets the doc file type of this file_ conf.
	 *
	 * @param docFileType the doc file type of this file_ conf
	 */
	public void setDocFileType(String docFileType);

	/**
	 * Returns the doc config json of this file_ conf.
	 *
	 * @return the doc config json of this file_ conf
	 */
	@AutoEscape
	public String getDocConfigJSON();

	/**
	 * Sets the doc config json of this file_ conf.
	 *
	 * @param docConfigJSON the doc config json of this file_ conf
	 */
	public void setDocConfigJSON(String docConfigJSON);

}