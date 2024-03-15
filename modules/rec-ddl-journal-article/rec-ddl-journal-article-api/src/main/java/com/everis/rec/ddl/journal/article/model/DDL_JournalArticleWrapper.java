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

package com.everis.rec.ddl.journal.article.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link DDL_JournalArticle}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DDL_JournalArticle
 * @generated
 */
public class DDL_JournalArticleWrapper
	extends BaseModelWrapper<DDL_JournalArticle>
	implements DDL_JournalArticle, ModelWrapper<DDL_JournalArticle> {

	public DDL_JournalArticleWrapper(DDL_JournalArticle ddl_JournalArticle) {
		super(ddl_JournalArticle);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("ddlRecordSetId", getDdlRecordSetId());
		attributes.put("journalResourcePrimKey", getJournalResourcePrimKey());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long ddlRecordSetId = (Long)attributes.get("ddlRecordSetId");

		if (ddlRecordSetId != null) {
			setDdlRecordSetId(ddlRecordSetId);
		}

		Long journalResourcePrimKey = (Long)attributes.get(
			"journalResourcePrimKey");

		if (journalResourcePrimKey != null) {
			setJournalResourcePrimKey(journalResourcePrimKey);
		}
	}

	/**
	 * Returns the ddl record set ID of this ddl_ journal article.
	 *
	 * @return the ddl record set ID of this ddl_ journal article
	 */
	@Override
	public long getDdlRecordSetId() {
		return model.getDdlRecordSetId();
	}

	/**
	 * Returns the journal resource prim key of this ddl_ journal article.
	 *
	 * @return the journal resource prim key of this ddl_ journal article
	 */
	@Override
	public long getJournalResourcePrimKey() {
		return model.getJournalResourcePrimKey();
	}

	/**
	 * Returns the primary key of this ddl_ journal article.
	 *
	 * @return the primary key of this ddl_ journal article
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the ddl record set ID of this ddl_ journal article.
	 *
	 * @param ddlRecordSetId the ddl record set ID of this ddl_ journal article
	 */
	@Override
	public void setDdlRecordSetId(long ddlRecordSetId) {
		model.setDdlRecordSetId(ddlRecordSetId);
	}

	/**
	 * Sets the journal resource prim key of this ddl_ journal article.
	 *
	 * @param journalResourcePrimKey the journal resource prim key of this ddl_ journal article
	 */
	@Override
	public void setJournalResourcePrimKey(long journalResourcePrimKey) {
		model.setJournalResourcePrimKey(journalResourcePrimKey);
	}

	/**
	 * Sets the primary key of this ddl_ journal article.
	 *
	 * @param primaryKey the primary key of this ddl_ journal article
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	@Override
	protected DDL_JournalArticleWrapper wrap(
		DDL_JournalArticle ddl_JournalArticle) {

		return new DDL_JournalArticleWrapper(ddl_JournalArticle);
	}

}