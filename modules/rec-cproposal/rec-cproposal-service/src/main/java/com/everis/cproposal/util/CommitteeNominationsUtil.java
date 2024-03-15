/*Nominations Util*/
package com.everis.cproposal.util;

import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;

public class CommitteeNominationsUtil {

	/* logger */
	private static final Log logger = LogFactoryUtil.getLog(CommitteeNominationsUtil.class);

	/* Structure key */
	private static final String STRUCTURE_KEY = "NOMINATIONS-STR";
	/* Template key */
	private static final String TEMPLATE_KEY = "NOMINATION-DETAILS-TPT";

	/* content params */
	private static final String AL_Nominations_TitleNomination = "@AL_Nominations_TitleNomination";
	private static final String AL_Nominations_DescriptionNomination = "@AL_Nominations_DescriptionNomination";
	private static final String AL_Nominations_ClosingDateNomination = "@AL_Nominations_ClosingDateNomination";
	private static final String AL_Nominations_StatusNomination = "@AL_Nominations_StatusNomination";
	private static final String AL_Nominations_ReportVisibilityNomination = "@AL_Nominations_ReportVisibilityNomination";
	private static final String AL_Nominations_DecidingVoteNomination = "@AL_Nominations_DecidingVoteNomination";

	/* content template */
	private static String committeeNominationsContentTemplate = "<?xml version='1.0'?><root available-locales='en_GB' default-locale='en_GB'><dynamic-element name='TitleNomination' type='text' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@AL_Nominations_TitleNomination]]></dynamic-content></dynamic-element><dynamic-element name='DescriptionNomination' type='text' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@AL_Nominations_DescriptionNomination]]></dynamic-content></dynamic-element><dynamic-element name='ClosingDateNomination' type='ddm-date' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@AL_Nominations_ClosingDateNomination]]></dynamic-content></dynamic-element><dynamic-element name='StatusNomination' type='list' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@AL_Nominations_StatusNomination]]></dynamic-content></dynamic-element><dynamic-element name='ReportVisibilityNomination' type='list' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@AL_Nominations_ReportVisibilityNomination]]></dynamic-content></dynamic-element><dynamic-element name='DecidingVoteNomination' type='text' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@AL_Nominations_DecidingVoteNomination]]></dynamic-content></dynamic-element></root>"; 

	public static long createCommitteeNominations(Map<String, String> parameters, long groupId, long userId,
			String title) {
		logger.info("---START createCommitteeNominations---");
		long resourcePrimKey = 0;
		long folderId = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;

		/* create Article */
		if (logger.isDebugEnabled()) {
			logger.info("---parameters---");
			parameters.forEach((k, v) -> logger.info(String.format("%-20s= %s", k, v)));
		}

		JournalArticle applicationArticle = createNominationsJournal(parameters, folderId, groupId, userId, title);
		resourcePrimKey = applicationArticle.getResourcePrimKey();

		logger.info("---END createApplication---");

		return resourcePrimKey;
	}

	public static long updateCommitteeNominations(Map<String, String> parameters, long appResourcePK) {
		logger.info("---START updateApplication---");
		long resourcePrimKey = 0;

		/* update Article */
		if (logger.isDebugEnabled()) {
			logger.info("---parameters---");
			parameters.forEach((k, v) -> logger.info(String.format("%-20s= %s", k, v)));
		}

		JournalArticle applicationArticle = updateNominationsJournal(parameters, appResourcePK);
		if (Validator.isNotNull(applicationArticle)) {
			resourcePrimKey = applicationArticle.getResourcePrimKey();
		}

		logger.info("---END updateApplication---");

		return resourcePrimKey;
	}

	private static JournalArticle createNominationsJournal(Map<String, String> parameters, long folderId, long groupId,
			long userId, String title) {
		logger.info("---START createApplicationJournal---");
		logger.info("groupId: " + groupId);
		JournalArticle article = null;
		Map<Locale, String> titleMap = new HashMap<>();
		Locale locale = LocaleUtil.getDefault();

		DynamicQuery dqStructure = DDMStructureLocalServiceUtil.dynamicQuery();
		dqStructure.add(PropertyFactoryUtil.forName("name").like("%" + STRUCTURE_KEY + "%"));
		dqStructure.add(PropertyFactoryUtil.forName("groupId").eq(groupId));
		List<DDMStructure> ddmListStructure = DDMStructureLocalServiceUtil.dynamicQuery(dqStructure);
		logger.info("ddmListStructure... " + ddmListStructure);

		DynamicQuery dqTemplate = DDMTemplateLocalServiceUtil.dynamicQuery();
		dqTemplate.add(PropertyFactoryUtil.forName("name").like("%" + TEMPLATE_KEY + "%"));
		dqTemplate.add(PropertyFactoryUtil.forName("groupId").eq(groupId));
		List<DDMTemplate> ddmListTemplate = DDMTemplateLocalServiceUtil.dynamicQuery(dqTemplate);
		logger.info("ddmListTemplate... " + ddmListTemplate);

		String structureKey = ddmListStructure.get(0).getStructureKey();
		String templateKey = ddmListTemplate.get(0).getTemplateKey();
		logger.info("structureKey... " + structureKey + " - templateKey... " + templateKey);

		String contentName = validTitleDescription(title);
		titleMap.put(locale, contentName);

		String nominationsContent = replaceContentWithFields(parameters);
		logger.info("Content replaced according to form fields! " + nominationsContent);

		/* create service context */
		ServiceContext serviceContext = new ServiceContext();
		try {
			serviceContext.setScopeGroupId(groupId);
			serviceContext.setAddGroupPermissions(true);
			serviceContext.setAddGuestPermissions(false);

			article = JournalArticleLocalServiceUtil.addArticle(userId, groupId, folderId, titleMap, null,
					nominationsContent, structureKey, templateKey, serviceContext);

			logger.info("Nominations Journal Article created!");
		} catch (PortalException e1) {
			logger.error("Error creating journalArticle", e1);
		}

		logger.info("---END createApplicationJournal---");
		return article;
	}

	private static JournalArticle updateNominationsJournal(Map<String, String> parameters, long resourcePK) {
		logger.info("---START updateApplicationJournal---");

		JournalArticle article = null;
		JournalArticle updatedArticle = null;

		String nominationsLogContent = replaceContentWithFields(parameters);
		logger.info("Content replaced according to form fields! " + nominationsLogContent);

		article = JournalArticleLocalServiceUtil.fetchLatestArticle(resourcePK);
		if (Validator.isNotNull(article)) {
			double version = article.getVersion();
			article.setContent(nominationsLogContent);
			ServiceContext serviceContext = new ServiceContext();
			serviceContext.setScopeGroupId(article.getGroupId());
			serviceContext.setAddGroupPermissions(true);
			serviceContext.setAddGuestPermissions(false);
			try {
				JournalArticleLocalServiceUtil.updateArticle(article.getUserId(), article.getGroupId(),
						article.getFolderId(), article.getArticleId(), version, article.getContent(), serviceContext);
				logger.info("Nomination Journal Article edited!");

			} catch (PortalException e) {
				logger.error("Error editing Nomination Journal Article", e);
			}
		}

		logger.info("---END updateApplicationJournal---");
		return updatedArticle;
	}

	private static String replaceContentWithFields(Map<String, String> parameters) {
		logger.info("---START replaceContentWithFields---");
		logger.info("Getting values from parameters...");

		String AL_Nominations_TitleNomination_value = parameters.get("AL_Nominations_TitleNomination");
		String AL_Nominations_DescriptionNomination_value = parameters.get("AL_Nominations_DescriptionNomination");
		String AL_Nominations_ClosingDateNomination_value = parameters.get("AL_Nominations_ClosingDateNomination");
		String AL_Nominations_StatusNomination_value = parameters.get("AL_Nominations_StatusNomination");
		String AL_Nominations_ReportVisibilityNomination_value = parameters
				.get("AL_Nominations_ReportVisibilityNomination");
		String AL_Nominations_DecidingVoteNomination_value = parameters.get("AL_Nominations_DecidingVoteNomination");

		String finalContent = committeeNominationsContentTemplate;
		logger.info("applicationContentTemplate " + committeeNominationsContentTemplate);

		logger.info("Replacing values un content template...");
		finalContent = finalContent.replaceAll(AL_Nominations_TitleNomination, AL_Nominations_TitleNomination_value);
		finalContent = finalContent.replaceAll(AL_Nominations_DescriptionNomination,
				AL_Nominations_DescriptionNomination_value);
		finalContent = finalContent.replaceAll(AL_Nominations_ClosingDateNomination,
				AL_Nominations_ClosingDateNomination_value);
		finalContent = finalContent.replaceAll(AL_Nominations_StatusNomination, AL_Nominations_StatusNomination_value);
		finalContent = finalContent.replaceAll(AL_Nominations_ReportVisibilityNomination,
				AL_Nominations_ReportVisibilityNomination_value);
		finalContent = finalContent.replaceAll(AL_Nominations_DecidingVoteNomination,
				AL_Nominations_DecidingVoteNomination_value);

		logger.info("finalContent: " + finalContent);

		logger.info("---END replaceContentWithFields---");
		return finalContent;
	}

	private static String validTitleDescription(String text) {
		logger.info("---START validTitleDescription---");
		if (Validator.isNotNull(text)) {
			text = text.replaceAll("\\$", Matcher.quoteReplacement("\\$"));
			text = text.replaceAll("&#039;", "'");
			text = text.replaceAll("&#034;", "\"");
			text = text.replaceAll("&amp;", "&");
		}
		logger.info("---END validTitleDescription---");
		return text;
	}

}