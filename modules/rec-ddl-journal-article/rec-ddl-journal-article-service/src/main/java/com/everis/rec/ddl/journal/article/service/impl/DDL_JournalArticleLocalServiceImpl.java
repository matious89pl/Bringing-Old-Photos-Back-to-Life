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

package com.everis.rec.ddl.journal.article.service.impl;

import com.everis.rec.ddl.journal.article.model.DDL_JournalArticle;
import com.everis.rec.ddl.journal.article.service.DDL_JournalArticleLocalServiceUtil;
import com.everis.rec.ddl.journal.article.service.base.DDL_JournalArticleLocalServiceBaseImpl;
import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.dynamic.data.lists.service.DDLRecordSetLocalServiceUtil;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * The implementation of the ddl_ journal article local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.everis.rec.ddl.journal.article.service.DDL_JournalArticleLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DDL_JournalArticleLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=com.everis.rec.ddl.journal.article.model.DDL_JournalArticle",
	service = AopService.class
)
public class DDL_JournalArticleLocalServiceImpl
	extends DDL_JournalArticleLocalServiceBaseImpl {
	
	private final Log logger = LogFactoryUtil.getLog(DDL_JournalArticleLocalServiceImpl.class);

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>com.everis.rec.ddl.journal.article.service.DDL_JournalArticleLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.everis.rec.ddl.journal.article.service.DDL_JournalArticleLocalServiceUtil</code>.
	 */
	
	public List<DDL_JournalArticle> getDDL_JournalArticleByJournalPK(long journalResourcePrimKey) {
		DynamicQuery dynamicQuery = DDL_JournalArticleLocalServiceUtil.dynamicQuery();
		dynamicQuery.add(PropertyFactoryUtil.forName("journalResourcePrimKey").eq(journalResourcePrimKey));
		List<DDL_JournalArticle> ddl_journalArticleList = DDL_JournalArticleLocalServiceUtil.dynamicQuery(dynamicQuery);
		return ddl_journalArticleList;
	}
	
	public DDL_JournalArticle getDDL_JournalArticleByJournalPKAndName(long journalResourcePrimKey, String name) {
		List<DDL_JournalArticle> ddl_journalArticleList = getDDL_JournalArticleByJournalPK(journalResourcePrimKey);
		DDLRecordSet ddlRecordSet = null;
		logger.info("ddl_journalArticleList: " + ddl_journalArticleList);
		logger.debug("ddl_journalArticleList size: "+ddl_journalArticleList.size());
		for (DDL_JournalArticle ddl_JournalArticle : ddl_journalArticleList) {
			try {
				ddlRecordSet = DDLRecordSetLocalServiceUtil.getDDLRecordSet(ddl_JournalArticle.getDdlRecordSetId());
				String ddlName = ddlRecordSet.getName();
				if (ddlName.contains(name)) {
					logger.debug("Returning ddl_JournalArticle for the ddlName: "+ddlName);
					return ddl_JournalArticle;
				}
			} catch (PortalException e) {
				logger.error("Error getting DDLRecordSet with ddlRecordSetId: " + ddl_JournalArticle.getDdlRecordSetId());
			}
		}
		return null;
	}
}