package com.everis.cproposal.service.impl;

import com.everis.cproposal.model.recFormArticle;
import com.everis.cproposal.service.recFormArticleLocalServiceUtil;
import com.liferay.calendar.service.CalendarLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

public class groovy {

	public void test() {
//		DynamicQuery dynamicQuery = recFormArticleLocalServiceUtil.dynamicQuery();
//		dynamicQuery.add(PropertyFactoryUtil.forName("articleStatus").like("%Approved - awaiting implementation%"));
////		dynamicQuery.add(PropertyFactoryUtil.forName("articleReleaseSchedule").like("%" + scheduleReleaseOption + "%"));
//		List<recFormArticle> recFormArticleList = recFormArticleLocalServiceUtil.dynamicQuery(dynamicQuery);
//		for (recFormArticle recFormArticle : recFormArticleList) {
//			recFormArticle.getArticleId();
//		}
	}
	
	public void test2() throws PortalException {
		List<recFormArticle> recFormArticleList = recFormArticleLocalServiceUtil.getrecFormArticles(-1, -1);
		for (int i =0; i < recFormArticleList.size(); i++) {
			long articleId = recFormArticleList.get(i).getArticleId();
			JournalArticle ja = JournalArticleLocalServiceUtil.fetchLatestArticle(articleId);
			if (Validator.isNull(ja)) {
				long formId = recFormArticleList.get(i).getFormId();
				recFormArticleLocalServiceUtil.deleterecFormArticle(formId);
			}
		}
	}
//	List<long> lista = obtengo identificadores de la tabla relacional;
//	for (list as item)
//	JournalArticle ja = journalarticlelocalserviceutil.fetchJournalArticle(item.id)
//	if (ja == null) //esto quiere decir que no existe la cp 
//	tablarelacionallocalserviceutil.remove(item)

}
