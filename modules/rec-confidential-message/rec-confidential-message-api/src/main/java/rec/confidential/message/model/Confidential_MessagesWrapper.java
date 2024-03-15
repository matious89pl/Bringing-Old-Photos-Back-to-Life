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

package rec.confidential.message.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Confidential_Messages}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Confidential_Messages
 * @generated
 */
public class Confidential_MessagesWrapper
	extends BaseModelWrapper<Confidential_Messages>
	implements Confidential_Messages, ModelWrapper<Confidential_Messages> {

	public Confidential_MessagesWrapper(
		Confidential_Messages confidential_Messages) {

		super(confidential_Messages);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("messageId", getMessageId());
		attributes.put("companyId", getCompanyId());
		attributes.put("createDate", getCreateDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long messageId = (Long)attributes.get("messageId");

		if (messageId != null) {
			setMessageId(messageId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}
	}

	/**
	 * Returns the company ID of this confidential_ messages.
	 *
	 * @return the company ID of this confidential_ messages
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this confidential_ messages.
	 *
	 * @return the create date of this confidential_ messages
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the message ID of this confidential_ messages.
	 *
	 * @return the message ID of this confidential_ messages
	 */
	@Override
	public long getMessageId() {
		return model.getMessageId();
	}

	/**
	 * Returns the primary key of this confidential_ messages.
	 *
	 * @return the primary key of this confidential_ messages
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the uuid of this confidential_ messages.
	 *
	 * @return the uuid of this confidential_ messages
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
	 * Sets the company ID of this confidential_ messages.
	 *
	 * @param companyId the company ID of this confidential_ messages
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this confidential_ messages.
	 *
	 * @param createDate the create date of this confidential_ messages
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the message ID of this confidential_ messages.
	 *
	 * @param messageId the message ID of this confidential_ messages
	 */
	@Override
	public void setMessageId(long messageId) {
		model.setMessageId(messageId);
	}

	/**
	 * Sets the primary key of this confidential_ messages.
	 *
	 * @param primaryKey the primary key of this confidential_ messages
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the uuid of this confidential_ messages.
	 *
	 * @param uuid the uuid of this confidential_ messages
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	protected Confidential_MessagesWrapper wrap(
		Confidential_Messages confidential_Messages) {

		return new Confidential_MessagesWrapper(confidential_Messages);
	}

}