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

package com.everis.cproposal.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.everis.cproposal.service.http.recFormArticleServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class recFormArticleSoap implements Serializable {

	public static recFormArticleSoap toSoapModel(recFormArticle model) {
		recFormArticleSoap soapModel = new recFormArticleSoap();

		soapModel.setFormId(model.getFormId());
		soapModel.setArticleId(model.getArticleId());
		soapModel.setArticleStatus(model.getArticleStatus());
		soapModel.setArticleReleaseSchedule(model.getArticleReleaseSchedule());
		soapModel.setAlternativeFormIds(model.getAlternativeFormIds());
		soapModel.setArticleImplementationDate(
			model.getArticleImplementationDate());
		soapModel.setArticleChangePath(model.getArticleChangePath());
		soapModel.setArticleResponsibleCommittee(
			model.getArticleResponsibleCommittee());
		soapModel.setArticleImpactedParties(model.getArticleImpactedParties());
		soapModel.setArticleChangeCategory(model.getArticleChangeCategory());
		soapModel.setArticleImpacts(model.getArticleImpacts());

		return soapModel;
	}

	public static recFormArticleSoap[] toSoapModels(recFormArticle[] models) {
		recFormArticleSoap[] soapModels = new recFormArticleSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static recFormArticleSoap[][] toSoapModels(
		recFormArticle[][] models) {

		recFormArticleSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new recFormArticleSoap[models.length][models[0].length];
		}
		else {
			soapModels = new recFormArticleSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static recFormArticleSoap[] toSoapModels(
		List<recFormArticle> models) {

		List<recFormArticleSoap> soapModels = new ArrayList<recFormArticleSoap>(
			models.size());

		for (recFormArticle model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new recFormArticleSoap[soapModels.size()]);
	}

	public recFormArticleSoap() {
	}

	public long getPrimaryKey() {
		return _formId;
	}

	public void setPrimaryKey(long pk) {
		setFormId(pk);
	}

	public long getFormId() {
		return _formId;
	}

	public void setFormId(long formId) {
		_formId = formId;
	}

	public long getArticleId() {
		return _articleId;
	}

	public void setArticleId(long articleId) {
		_articleId = articleId;
	}

	public String getArticleStatus() {
		return _articleStatus;
	}

	public void setArticleStatus(String articleStatus) {
		_articleStatus = articleStatus;
	}

	public String getArticleReleaseSchedule() {
		return _articleReleaseSchedule;
	}

	public void setArticleReleaseSchedule(String articleReleaseSchedule) {
		_articleReleaseSchedule = articleReleaseSchedule;
	}

	public String getAlternativeFormIds() {
		return _alternativeFormIds;
	}

	public void setAlternativeFormIds(String alternativeFormIds) {
		_alternativeFormIds = alternativeFormIds;
	}

	public Date getArticleImplementationDate() {
		return _articleImplementationDate;
	}

	public void setArticleImplementationDate(Date articleImplementationDate) {
		_articleImplementationDate = articleImplementationDate;
	}

	public String getArticleChangePath() {
		return _articleChangePath;
	}

	public void setArticleChangePath(String articleChangePath) {
		_articleChangePath = articleChangePath;
	}

	public String getArticleResponsibleCommittee() {
		return _articleResponsibleCommittee;
	}

	public void setArticleResponsibleCommittee(
		String articleResponsibleCommittee) {

		_articleResponsibleCommittee = articleResponsibleCommittee;
	}

	public String getArticleImpactedParties() {
		return _articleImpactedParties;
	}

	public void setArticleImpactedParties(String articleImpactedParties) {
		_articleImpactedParties = articleImpactedParties;
	}

	public String getArticleChangeCategory() {
		return _articleChangeCategory;
	}

	public void setArticleChangeCategory(String articleChangeCategory) {
		_articleChangeCategory = articleChangeCategory;
	}

	public String getArticleImpacts() {
		return _articleImpacts;
	}

	public void setArticleImpacts(String articleImpacts) {
		_articleImpacts = articleImpacts;
	}

	private long _formId;
	private long _articleId;
	private String _articleStatus;
	private String _articleReleaseSchedule;
	private String _alternativeFormIds;
	private Date _articleImplementationDate;
	private String _articleChangePath;
	private String _articleResponsibleCommittee;
	private String _articleImpactedParties;
	private String _articleChangeCategory;
	private String _articleImpacts;

}