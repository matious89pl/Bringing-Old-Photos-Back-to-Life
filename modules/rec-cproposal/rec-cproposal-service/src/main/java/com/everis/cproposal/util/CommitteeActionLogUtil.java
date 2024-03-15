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

public class CommitteeActionLogUtil {

	/* logger */
	private static final Log logger = LogFactoryUtil.getLog(CommitteeActionLogUtil.class);

	/* Structure key */
	private static final String STRUCTURE_KEY = "COMMITTEE-ACTION-LOG";
	/* Template key */
	private static final String TEMPLATE_KEY = "COMMITTEE-FORM-ACTION-LOG";

	/* content params */
	private static final String AL_Title = "@AL_Title";
	private static final String AL_ID = "@AL_ID";
	private static final String AL_Description = "@AL_Description";
	private static final String AL_Assignee = "@AL_Assignee";
	private static final String AL_DueDate = "@AL_DueDate";
	private static final String AL_NextUpdateDue = "@AL_NextUpdateDue";
	private static final String AL_Status = "@AL_Status";
	private static final String AL_CompletionDate = "@AL_CompletionDate";

	/* content template */
	private static String committeeActionLogContentTemplate = "<?xml version='1.0' encoding='UTF-8'?><root available-locales='en_GB' default-locale='en_GB'><dynamic-element name='ActionLog_ID' type='text' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@AL_ID]]></dynamic-content></dynamic-element><dynamic-element name='ActionLog_Description' type='text' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@AL_Description]]></dynamic-content></dynamic-element><dynamic-element name='ActionLog_AssigneeEmail' type='text' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@AL_Assignee]]></dynamic-content></dynamic-element><dynamic-element name='ActionLog_DueDate' type='ddm-date' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@AL_DueDate]]></dynamic-content></dynamic-element><dynamic-element name='ActionLog_NextUpdateDue' type='ddm-date' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@AL_NextUpdateDue]]></dynamic-content></dynamic-element><dynamic-element name='ActionLog_Status' type='list' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@AL_Status]]></dynamic-content></dynamic-element><dynamic-element name='ActionLog_CompletionDate' type='ddm-date' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@AL_CompletionDate]]></dynamic-content></dynamic-element></root>";

	public static long createCommitteeActionLog(Map<String, String> parameters, long groupId, long userId,
			String title) {
		logger.info("---START createCommitteeActionLog---");
		long resourcePrimKey = 0;
		long folderId = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;

		/* create Article */
		if (logger.isDebugEnabled()) {
			logger.info("---parameters---");
			parameters.forEach((k, v) -> logger.info(String.format("%-20s= %s", k, v)));
		}

		JournalArticle applicationArticle = createActionLogJournal(parameters, folderId, groupId, userId, title);
		resourcePrimKey = applicationArticle.getResourcePrimKey();

		logger.info("---END createApplication---");

		return resourcePrimKey;
	}

	public static long updateCommitteeActionLog(Map<String, String> parameters, long appResourcePK) {
		logger.info("---START updateApplication---");
		long resourcePrimKey = 0;

		/* update Article */
		if (logger.isDebugEnabled()) {
			logger.info("---parameters---");
			parameters.forEach((k, v) -> logger.info(String.format("%-20s= %s", k, v)));
		}

		JournalArticle applicationArticle = updateActionLogJournal(parameters, appResourcePK);
		if (Validator.isNotNull(applicationArticle)) {
			resourcePrimKey = applicationArticle.getResourcePrimKey();
		}

		logger.info("---END updateApplication---");

		return resourcePrimKey;
	}

	private static JournalArticle createActionLogJournal(Map<String, String> parameters, long folderId, long groupId,
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

		String actionLogContent = replaceContentWithFields(parameters);
		logger.info("Content replaced according to form fields! " + actionLogContent);

		/* create service context */
		ServiceContext serviceContext = new ServiceContext();
		try {
			serviceContext.setScopeGroupId(groupId);
			serviceContext.setAddGroupPermissions(true);
			serviceContext.setAddGuestPermissions(false);
			// serviceContext.setWorkflowAction(1);

			article = JournalArticleLocalServiceUtil.addArticle(userId, groupId, folderId, titleMap, null,
					actionLogContent, structureKey, null, serviceContext);

			logger.info("Action Log Journal Article created!");
		} catch (PortalException e1) {
			logger.error("Error creating journalArticle", e1);
		}

		logger.info("---END createApplicationJournal---");
		return article;
	}

	private static JournalArticle updateActionLogJournal(Map<String, String> parameters, long resourcePK) {
		logger.info("---START updateApplicationJournal---");

		JournalArticle article = null;
		JournalArticle updatedArticle = null;

		String actionLogContent = replaceContentWithFields(parameters);
		logger.info("Content replaced according to form fields! " + actionLogContent);

		article = JournalArticleLocalServiceUtil.fetchLatestArticle(resourcePK);
		if (Validator.isNotNull(article)) {
			double version = article.getVersion();
			article.setContent(actionLogContent);
			ServiceContext serviceContext = new ServiceContext();
			serviceContext.setScopeGroupId(article.getGroupId());
			serviceContext.setAddGroupPermissions(true);
			serviceContext.setAddGuestPermissions(false);
			try {
				JournalArticleLocalServiceUtil.updateArticle(article.getUserId(), article.getGroupId(),
						article.getFolderId(), article.getArticleId(), version, article.getContent(), serviceContext);
				logger.info("Action Log Journal Article edited!");

			} catch (PortalException e) {
				logger.error("Error editing Action Log Journal Article", e);
			}
		}

		logger.info("---END updateApplicationJournal---");
		return updatedArticle;
	}

	private static String replaceContentWithFields(Map<String, String> parameters) {
		logger.info("---START replaceContentWithFields---");

		logger.info("Getting values from parameters...");
		String AL_ID_value = parameters.get("AL_ID");
		String AL_Description_value = parameters.get("AL_Description");
		String AL_Assignee_value = parameters.get("AL_Assignee");
		String AL_DueDate_value = parameters.get("AL_DueDate");
		String AL_NextUpdateDue_value = parameters.get("AL_NextUpdateDue");
		String AL_Status_value = parameters.get("AL_Status");
		String AL_CompletionDate_value = parameters.get("AL_CompletionDate");

		String finalContent = committeeActionLogContentTemplate;
		logger.info("applicationContentTemplate " + committeeActionLogContentTemplate);

		logger.info("Replacing values un content template...");
		finalContent = finalContent.replaceAll(AL_ID, AL_ID_value);
		finalContent = finalContent.replaceAll(AL_Description, AL_Description_value);
		finalContent = finalContent.replaceAll(AL_Assignee, AL_Assignee_value);
		finalContent = finalContent.replaceAll(AL_DueDate, AL_DueDate_value);
		finalContent = finalContent.replaceAll(AL_NextUpdateDue, AL_NextUpdateDue_value);
		finalContent = finalContent.replaceAll(AL_Status, AL_Status_value);
		finalContent = finalContent.replaceAll(AL_CompletionDate, AL_CompletionDate_value);

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
