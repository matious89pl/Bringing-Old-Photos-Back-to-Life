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

package com.everis.rec.ddl.journal.article.model.impl;

import com.everis.rec.ddl.journal.article.model.DDL_JournalArticle;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing DDL_JournalArticle in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class DDL_JournalArticleCacheModel
	implements CacheModel<DDL_JournalArticle>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof DDL_JournalArticleCacheModel)) {
			return false;
		}

		DDL_JournalArticleCacheModel ddl_JournalArticleCacheModel =
			(DDL_JournalArticleCacheModel)object;

		if (ddlRecordSetId == ddl_JournalArticleCacheModel.ddlRecordSetId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, ddlRecordSetId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{ddlRecordSetId=");
		sb.append(ddlRecordSetId);
		sb.append(", journalResourcePrimKey=");
		sb.append(journalResourcePrimKey);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DDL_JournalArticle toEntityModel() {
		DDL_JournalArticleImpl ddl_JournalArticleImpl =
			new DDL_JournalArticleImpl();

		ddl_JournalArticleImpl.setDdlRecordSetId(ddlRecordSetId);
		ddl_JournalArticleImpl.setJournalResourcePrimKey(
			journalResourcePrimKey);

		ddl_JournalArticleImpl.resetOriginalValues();

		return ddl_JournalArticleImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		ddlRecordSetId = objectInput.readLong();

		journalResourcePrimKey = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(ddlRecordSetId);

		objectOutput.writeLong(journalResourcePrimKey);
	}

	public long ddlRecordSetId;
	public long journalResourcePrimKey;

}