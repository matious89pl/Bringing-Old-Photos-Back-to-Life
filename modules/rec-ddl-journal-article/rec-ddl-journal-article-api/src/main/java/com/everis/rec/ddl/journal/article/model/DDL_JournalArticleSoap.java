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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.everis.rec.ddl.journal.article.service.http.DDL_JournalArticleServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class DDL_JournalArticleSoap implements Serializable {

	public static DDL_JournalArticleSoap toSoapModel(DDL_JournalArticle model) {
		DDL_JournalArticleSoap soapModel = new DDL_JournalArticleSoap();

		soapModel.setDdlRecordSetId(model.getDdlRecordSetId());
		soapModel.setJournalResourcePrimKey(model.getJournalResourcePrimKey());

		return soapModel;
	}

	public static DDL_JournalArticleSoap[] toSoapModels(
		DDL_JournalArticle[] models) {

		DDL_JournalArticleSoap[] soapModels =
			new DDL_JournalArticleSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DDL_JournalArticleSoap[][] toSoapModels(
		DDL_JournalArticle[][] models) {

		DDL_JournalArticleSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new DDL_JournalArticleSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DDL_JournalArticleSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DDL_JournalArticleSoap[] toSoapModels(
		List<DDL_JournalArticle> models) {

		List<DDL_JournalArticleSoap> soapModels =
			new ArrayList<DDL_JournalArticleSoap>(models.size());

		for (DDL_JournalArticle model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new DDL_JournalArticleSoap[soapModels.size()]);
	}

	public DDL_JournalArticleSoap() {
	}

	public long getPrimaryKey() {
		return _ddlRecordSetId;
	}

	public void setPrimaryKey(long pk) {
		setDdlRecordSetId(pk);
	}

	public long getDdlRecordSetId() {
		return _ddlRecordSetId;
	}

	public void setDdlRecordSetId(long ddlRecordSetId) {
		_ddlRecordSetId = ddlRecordSetId;
	}

	public long getJournalResourcePrimKey() {
		return _journalResourcePrimKey;
	}

	public void setJournalResourcePrimKey(long journalResourcePrimKey) {
		_journalResourcePrimKey = journalResourcePrimKey;
	}

	private long _ddlRecordSetId;
	private long _journalResourcePrimKey;

}