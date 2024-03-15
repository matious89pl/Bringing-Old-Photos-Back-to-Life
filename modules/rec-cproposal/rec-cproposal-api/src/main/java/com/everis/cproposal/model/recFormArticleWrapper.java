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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link recFormArticle}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see recFormArticle
 * @generated
 */
public class recFormArticleWrapper
	extends BaseModelWrapper<recFormArticle>
	implements ModelWrapper<recFormArticle>, recFormArticle {

	public recFormArticleWrapper(recFormArticle recFormArticle) {
		super(recFormArticle);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("formId", getFormId());
		attributes.put("articleId", getArticleId());
		attributes.put("articleStatus", getArticleStatus());
		attributes.put("articleReleaseSchedule", getArticleReleaseSchedule());
		attributes.put("alternativeFormIds", getAlternativeFormIds());
		attributes.put(
			"articleImplementationDate", getArticleImplementationDate());
		attributes.put("articleChangePath", getArticleChangePath());
		attributes.put(
			"articleResponsibleCommittee", getArticleResponsibleCommittee());
		attributes.put("articleImpactedParties", getArticleImpactedParties());
		attributes.put("articleChangeCategory", getArticleChangeCategory());
		attributes.put("articleImpacts", getArticleImpacts());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long formId = (Long)attributes.get("formId");

		if (formId != null) {
			setFormId(formId);
		}

		Long articleId = (Long)attributes.get("articleId");

		if (articleId != null) {
			setArticleId(articleId);
		}

		String articleStatus = (String)attributes.get("articleStatus");

		if (articleStatus != null) {
			setArticleStatus(articleStatus);
		}

		String articleReleaseSchedule = (String)attributes.get(
			"articleReleaseSchedule");

		if (articleReleaseSchedule != null) {
			setArticleReleaseSchedule(articleReleaseSchedule);
		}

		String alternativeFormIds = (String)attributes.get(
			"alternativeFormIds");

		if (alternativeFormIds != null) {
			setAlternativeFormIds(alternativeFormIds);
		}

		Date articleImplementationDate = (Date)attributes.get(
			"articleImplementationDate");

		if (articleImplementationDate != null) {
			setArticleImplementationDate(articleImplementationDate);
		}

		String articleChangePath = (String)attributes.get("articleChangePath");

		if (articleChangePath != null) {
			setArticleChangePath(articleChangePath);
		}

		String articleResponsibleCommittee = (String)attributes.get(
			"articleResponsibleCommittee");

		if (articleResponsibleCommittee != null) {
			setArticleResponsibleCommittee(articleResponsibleCommittee);
		}

		String articleImpactedParties = (String)attributes.get(
			"articleImpactedParties");

		if (articleImpactedParties != null) {
			setArticleImpactedParties(articleImpactedParties);
		}

		String articleChangeCategory = (String)attributes.get(
			"articleChangeCategory");

		if (articleChangeCategory != null) {
			setArticleChangeCategory(articleChangeCategory);
		}

		String articleImpacts = (String)attributes.get("articleImpacts");

		if (articleImpacts != null) {
			setArticleImpacts(articleImpacts);
		}
	}

	/**
	 * Returns the alternative form IDs of this rec form article.
	 *
	 * @return the alternative form IDs of this rec form article
	 */
	@Override
	public String getAlternativeFormIds() {
		return model.getAlternativeFormIds();
	}

	/**
	 * Returns the article change category of this rec form article.
	 *
	 * @return the article change category of this rec form article
	 */
	@Override
	public String getArticleChangeCategory() {
		return model.getArticleChangeCategory();
	}

	/**
	 * Returns the article change path of this rec form article.
	 *
	 * @return the article change path of this rec form article
	 */
	@Override
	public String getArticleChangePath() {
		return model.getArticleChangePath();
	}

	/**
	 * Returns the article ID of this rec form article.
	 *
	 * @return the article ID of this rec form article
	 */
	@Override
	public long getArticleId() {
		return model.getArticleId();
	}

	/**
	 * Returns the article impacted parties of this rec form article.
	 *
	 * @return the article impacted parties of this rec form article
	 */
	@Override
	public String getArticleImpactedParties() {
		return model.getArticleImpactedParties();
	}

	/**
	 * Returns the article impacts of this rec form article.
	 *
	 * @return the article impacts of this rec form article
	 */
	@Override
	public String getArticleImpacts() {
		return model.getArticleImpacts();
	}

	/**
	 * Returns the article implementation date of this rec form article.
	 *
	 * @return the article implementation date of this rec form article
	 */
	@Override
	public Date getArticleImplementationDate() {
		return model.getArticleImplementationDate();
	}

	/**
	 * Returns the article release schedule of this rec form article.
	 *
	 * @return the article release schedule of this rec form article
	 */
	@Override
	public String getArticleReleaseSchedule() {
		return model.getArticleReleaseSchedule();
	}

	/**
	 * Returns the article responsible committee of this rec form article.
	 *
	 * @return the article responsible committee of this rec form article
	 */
	@Override
	public String getArticleResponsibleCommittee() {
		return model.getArticleResponsibleCommittee();
	}

	/**
	 * Returns the article status of this rec form article.
	 *
	 * @return the article status of this rec form article
	 */
	@Override
	public String getArticleStatus() {
		return model.getArticleStatus();
	}

	/**
	 * Returns the form ID of this rec form article.
	 *
	 * @return the form ID of this rec form article
	 */
	@Override
	public long getFormId() {
		return model.getFormId();
	}

	/**
	 * Returns the primary key of this rec form article.
	 *
	 * @return the primary key of this rec form article
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
	 * Sets the alternative form IDs of this rec form article.
	 *
	 * @param alternativeFormIds the alternative form IDs of this rec form article
	 */
	@Override
	public void setAlternativeFormIds(String alternativeFormIds) {
		model.setAlternativeFormIds(alternativeFormIds);
	}

	/**
	 * Sets the article change category of this rec form article.
	 *
	 * @param articleChangeCategory the article change category of this rec form article
	 */
	@Override
	public void setArticleChangeCategory(String articleChangeCategory) {
		model.setArticleChangeCategory(articleChangeCategory);
	}

	/**
	 * Sets the article change path of this rec form article.
	 *
	 * @param articleChangePath the article change path of this rec form article
	 */
	@Override
	public void setArticleChangePath(String articleChangePath) {
		model.setArticleChangePath(articleChangePath);
	}

	/**
	 * Sets the article ID of this rec form article.
	 *
	 * @param articleId the article ID of this rec form article
	 */
	@Override
	public void setArticleId(long articleId) {
		model.setArticleId(articleId);
	}

	/**
	 * Sets the article impacted parties of this rec form article.
	 *
	 * @param articleImpactedParties the article impacted parties of this rec form article
	 */
	@Override
	public void setArticleImpactedParties(String articleImpactedParties) {
		model.setArticleImpactedParties(articleImpactedParties);
	}

	/**
	 * Sets the article impacts of this rec form article.
	 *
	 * @param articleImpacts the article impacts of this rec form article
	 */
	@Override
	public void setArticleImpacts(String articleImpacts) {
		model.setArticleImpacts(articleImpacts);
	}

	/**
	 * Sets the article implementation date of this rec form article.
	 *
	 * @param articleImplementationDate the article implementation date of this rec form article
	 */
	@Override
	public void setArticleImplementationDate(Date articleImplementationDate) {
		model.setArticleImplementationDate(articleImplementationDate);
	}

	/**
	 * Sets the article release schedule of this rec form article.
	 *
	 * @param articleReleaseSchedule the article release schedule of this rec form article
	 */
	@Override
	public void setArticleReleaseSchedule(String articleReleaseSchedule) {
		model.setArticleReleaseSchedule(articleReleaseSchedule);
	}

	/**
	 * Sets the article responsible committee of this rec form article.
	 *
	 * @param articleResponsibleCommittee the article responsible committee of this rec form article
	 */
	@Override
	public void setArticleResponsibleCommittee(
		String articleResponsibleCommittee) {

		model.setArticleResponsibleCommittee(articleResponsibleCommittee);
	}

	/**
	 * Sets the article status of this rec form article.
	 *
	 * @param articleStatus the article status of this rec form article
	 */
	@Override
	public void setArticleStatus(String articleStatus) {
		model.setArticleStatus(articleStatus);
	}

	/**
	 * Sets the form ID of this rec form article.
	 *
	 * @param formId the form ID of this rec form article
	 */
	@Override
	public void setFormId(long formId) {
		model.setFormId(formId);
	}

	/**
	 * Sets the primary key of this rec form article.
	 *
	 * @param primaryKey the primary key of this rec form article
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	@Override
	protected recFormArticleWrapper wrap(recFormArticle recFormArticle) {
		return new recFormArticleWrapper(recFormArticle);
	}

}