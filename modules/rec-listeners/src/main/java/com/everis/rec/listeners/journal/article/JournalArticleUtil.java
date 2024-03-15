package com.everis.rec.listeners.journal.article;

import com.everis.rec.ddl.journal.article.model.DDL_JournalArticle;
import com.everis.rec.ddl.journal.article.service.DDL_JournalArticleLocalServiceUtil;
import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

public class JournalArticleUtil {
	
	private final static Log logger = LogFactoryUtil.getLog(JournalArticleUtil.class);
	
	public static boolean addDDLJournalRelation(DDLRecordSet ddlRecordSet, JournalArticle model) {
		DDL_JournalArticle ddl_JournalArticle = DDL_JournalArticleLocalServiceUtil.createDDL_JournalArticle(ddlRecordSet.getRecordSetId());
		ddl_JournalArticle.setJournalResourcePrimKey(model.getResourcePrimKey());
		ddl_JournalArticle = DDL_JournalArticleLocalServiceUtil.updateDDL_JournalArticle(ddl_JournalArticle);
		logger.info("Adding new relation DDL_Journal: " + ddl_JournalArticle.toString());
		return Validator.isNotNull(ddl_JournalArticle) ? true : false;
	}

}
