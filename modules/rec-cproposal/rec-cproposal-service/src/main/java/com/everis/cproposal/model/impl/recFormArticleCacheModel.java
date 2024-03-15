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

package com.everis.cproposal.model.impl;

import com.everis.cproposal.model.recFormArticle;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing recFormArticle in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class recFormArticleCacheModel
	implements CacheModel<recFormArticle>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof recFormArticleCacheModel)) {
			return false;
		}

		recFormArticleCacheModel recFormArticleCacheModel =
			(recFormArticleCacheModel)object;

		if (formId == recFormArticleCacheModel.formId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, formId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{formId=");
		sb.append(formId);
		sb.append(", articleId=");
		sb.append(articleId);
		sb.append(", articleStatus=");
		sb.append(articleStatus);
		sb.append(", articleReleaseSchedule=");
		sb.append(articleReleaseSchedule);
		sb.append(", alternativeFormIds=");
		sb.append(alternativeFormIds);
		sb.append(", articleImplementationDate=");
		sb.append(articleImplementationDate);
		sb.append(", articleChangePath=");
		sb.append(articleChangePath);
		sb.append(", articleResponsibleCommittee=");
		sb.append(articleResponsibleCommittee);
		sb.append(", articleImpactedParties=");
		sb.append(articleImpactedParties);
		sb.append(", articleChangeCategory=");
		sb.append(articleChangeCategory);
		sb.append(", articleImpacts=");
		sb.append(articleImpacts);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public recFormArticle toEntityModel() {
		recFormArticleImpl recFormArticleImpl = new recFormArticleImpl();

		recFormArticleImpl.setFormId(formId);
		recFormArticleImpl.setArticleId(articleId);

		if (articleStatus == null) {
			recFormArticleImpl.setArticleStatus("");
		}
		else {
			recFormArticleImpl.setArticleStatus(articleStatus);
		}

		if (articleReleaseSchedule == null) {
			recFormArticleImpl.setArticleReleaseSchedule("");
		}
		else {
			recFormArticleImpl.setArticleReleaseSchedule(
				articleReleaseSchedule);
		}

		if (alternativeFormIds == null) {
			recFormArticleImpl.setAlternativeFormIds("");
		}
		else {
			recFormArticleImpl.setAlternativeFormIds(alternativeFormIds);
		}

		if (articleImplementationDate == Long.MIN_VALUE) {
			recFormArticleImpl.setArticleImplementationDate(null);
		}
		else {
			recFormArticleImpl.setArticleImplementationDate(
				new Date(articleImplementationDate));
		}

		if (articleChangePath == null) {
			recFormArticleImpl.setArticleChangePath("");
		}
		else {
			recFormArticleImpl.setArticleChangePath(articleChangePath);
		}

		if (articleResponsibleCommittee == null) {
			recFormArticleImpl.setArticleResponsibleCommittee("");
		}
		else {
			recFormArticleImpl.setArticleResponsibleCommittee(
				articleResponsibleCommittee);
		}

		if (articleImpactedParties == null) {
			recFormArticleImpl.setArticleImpactedParties("");
		}
		else {
			recFormArticleImpl.setArticleImpactedParties(
				articleImpactedParties);
		}

		if (articleChangeCategory == null) {
			recFormArticleImpl.setArticleChangeCategory("");
		}
		else {
			recFormArticleImpl.setArticleChangeCategory(articleChangeCategory);
		}

		if (articleImpacts == null) {
			recFormArticleImpl.setArticleImpacts("");
		}
		else {
			recFormArticleImpl.setArticleImpacts(articleImpacts);
		}

		recFormArticleImpl.resetOriginalValues();

		return recFormArticleImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		formId = objectInput.readLong();

		articleId = objectInput.readLong();
		articleStatus = objectInput.readUTF();
		articleReleaseSchedule = objectInput.readUTF();
		alternativeFormIds = objectInput.readUTF();
		articleImplementationDate = objectInput.readLong();
		articleChangePath = objectInput.readUTF();
		articleResponsibleCommittee = objectInput.readUTF();
		articleImpactedParties = objectInput.readUTF();
		articleChangeCategory = objectInput.readUTF();
		articleImpacts = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(formId);

		objectOutput.writeLong(articleId);

		if (articleStatus == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(articleStatus);
		}

		if (articleReleaseSchedule == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(articleReleaseSchedule);
		}

		if (alternativeFormIds == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(alternativeFormIds);
		}

		objectOutput.writeLong(articleImplementationDate);

		if (articleChangePath == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(articleChangePath);
		}

		if (articleResponsibleCommittee == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(articleResponsibleCommittee);
		}

		if (articleImpactedParties == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(articleImpactedParties);
		}

		if (articleChangeCategory == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(articleChangeCategory);
		}

		if (articleImpacts == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(articleImpacts);
		}
	}

	public long formId;
	public long articleId;
	public String articleStatus;
	public String articleReleaseSchedule;
	public String alternativeFormIds;
	public long articleImplementationDate;
	public String articleChangePath;
	public String articleResponsibleCommittee;
	public String articleImpactedParties;
	public String articleChangeCategory;
	public String articleImpacts;

}