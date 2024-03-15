package com.everis.cproposal.util;

import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.petra.string.StringPool;
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

public class CommitteeVotingProcessUtil {

	/* logger */
	private static final Log logger = LogFactoryUtil.getLog(CommitteeVotingProcessUtil.class);

	/* Structure key */
	private static final String STRUCTURE_KEY = "VOTING";
	/* Template key */
	private static final String TEMPLATE_KEY = "VOTING";

	/* content params */
	private static final String Vote_Title = "@Vote_TitleVoting";
	private static final String Vote_Description = "@Vote_Description";
	private static final String Vote_ClossingProcess = "@Vote_ClossingProcess";
	private static final String Vote_Status = "@Vote_Status";
	private static final String Vote_ReportVisibility = "@Vote_ReportVisibility";
	private static final String Vote_VotingVisibility = "@Vote_VotingVisibility";
	private static final String Vote_DecidingVote = "@Vote_DecidingVote";
	private static final String ExternalVoteNodes = "@ExternalVoteNodes";
	private static final String Vote_ExternalVote = "@Vote_ExternalVote";

	/* content template */
	private static String committeeVotingProcessContentTemplate = "<?xml version='1.0'?><root available-locales='en_GB' default-locale='en_GB'><dynamic-element name='TitleVoting' type='text' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@Vote_TitleVoting]]></dynamic-content></dynamic-element><dynamic-element name='DescriptionVoting' type='text' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@Vote_Description]]></dynamic-content></dynamic-element><dynamic-element name='DateVoting' type='ddm-date' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@Vote_ClossingProcess]]></dynamic-content></dynamic-element><dynamic-element name='StatusVoting' type='list' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@Vote_Status]]></dynamic-content></dynamic-element><dynamic-element name='ReportVisibilityVoting' type='list' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@Vote_ReportVisibility]]></dynamic-content></dynamic-element><dynamic-element name='VotingVisibility' type='list' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@Vote_VotingVisibility]]></dynamic-content></dynamic-element><dynamic-element name='DecidingVoting' type='text' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@Vote_DecidingVote]]></dynamic-content></dynamic-element>@ExternalVoteNodes</root>";

	private static String externalVoteContentTemplate = "<dynamic-element name='ExternalVoting' type='text' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@Vote_ExternalVote]]></dynamic-content></dynamic-element>";

	public static long createCommitteeVotingProcess(Map<String, String> parameters, long groupId, long userId,
			String title) {
		logger.info("---START createCommitteeVotingProcess---");
		long resourcePrimKey = 0;
		long folderId = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;

		/* create Article */
		if (logger.isDebugEnabled()) {
			logger.info("---parameters---");
			parameters.forEach((k, v) -> logger.info(String.format("%-20s= %s", k, v)));
		}

		JournalArticle applicationArticle = createVotingProcessJournal(parameters, folderId, groupId, userId, title);
		resourcePrimKey = applicationArticle.getResourcePrimKey();

		logger.info("---END createApplication---");

		return resourcePrimKey;
	}

	public static long updateCommitteeVotingProcess(Map<String, String> parameters, long votingResourcePK,
			String[] Vote_ExternalVote) {
		logger.info("---START updateApplication---");
		long resourcePrimKey = 0;

		/* update Article */
		if (logger.isDebugEnabled()) {
			logger.info("---parameters---");
			parameters.forEach((k, v) -> logger.info(String.format("%-20s= %s", k, v)));
		}

		JournalArticle votingArticle = updateVotingProcessJournal(parameters, votingResourcePK, Vote_ExternalVote);
		if (Validator.isNotNull(votingArticle)) {
			resourcePrimKey = votingArticle.getResourcePrimKey();
		}

		logger.info("---END updateApplication---");

		return resourcePrimKey;
	}

	private static JournalArticle createVotingProcessJournal(Map<String, String> parameters, long folderId,
			long groupId, long userId, String title) {
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

		String VotingProcessContent = replaceContentWithFields(parameters, null);
		logger.info("Content replaced according to form fields! " + VotingProcessContent);

		/* create service context */
		ServiceContext serviceContext = new ServiceContext();
		try {
			serviceContext.setScopeGroupId(groupId);
			serviceContext.setAddGroupPermissions(true);
			serviceContext.setAddGuestPermissions(false);
			// serviceContext.setWorkflowAction(1);

			article = JournalArticleLocalServiceUtil.addArticle(userId, groupId, folderId, titleMap, null,
					VotingProcessContent, structureKey, templateKey, serviceContext);

			logger.info("Voting Process Journal Article created!");
		} catch (PortalException e1) {
			logger.error("Error creating journalArticle", e1);
		}

		logger.info("---END createApplicationJournal---");
		return article;
	}

	private static JournalArticle updateVotingProcessJournal(Map<String, String> parameters, long resourcePK,
			String[] Vote_ExternalVote) {
		logger.info("---START updateApplicationJournal---");

		JournalArticle article = null;
		JournalArticle updatedArticle = null;

		String VotingProcessContent = replaceContentWithFields(parameters, Vote_ExternalVote);
		logger.info("Content replaced according to form fields! " + VotingProcessContent);

		article = JournalArticleLocalServiceUtil.fetchLatestArticle(resourcePK);
		if (Validator.isNotNull(article)) {
			double version = article.getVersion();
			article.setContent(VotingProcessContent);
			ServiceContext serviceContext = new ServiceContext();
			serviceContext.setScopeGroupId(article.getGroupId());
			serviceContext.setAddGroupPermissions(true);
			serviceContext.setAddGuestPermissions(false);
			try {
				JournalArticleLocalServiceUtil.updateArticle(article.getUserId(), article.getGroupId(), article.getFolderId(), article.getArticleId(), version, article.getContent(), serviceContext);
				logger.info("Voting Journal Article edited!");

			} catch (PortalException e) {
				logger.error("Error editing Voting Journal Article", e);
			}
		}

		logger.info("---END updateApplicationJournal---");
		return updatedArticle;
	}

	private static String replaceContentWithFields(Map<String, String> parameters, String[] Vote_ExternalVote_value) {
		logger.info("---START replaceContentWithFields---");
		logger.info("Getting values from parameters...");

		String Vote_Title_value = parameters.get("Vote_TitleVoting");
		String Vote_Description_value = parameters.get("Vote_Description");
		String Vote_ClossingProcess_value = parameters.get("Vote_ClossingProcess");
		String Vote_Status_value = parameters.get("Vote_Status");
		String Vote_ReportVisibility_value = parameters.get("Vote_ReportVisibility");
		String Vote_VotingVisibility_value = parameters.get("Vote_VotingVisibility");
		String Vote_DecidingVote_value = parameters.get("Vote_DecidingVote");
		String ExternalVoteNodes_value = StringPool.BLANK;
		String Vote_ExternalVote_initialValue = StringPool.BLANK;
		if (Validator.isNotNull(Vote_ExternalVote_value) && Vote_ExternalVote_value.length > 0) {
			logger.info(Vote_ExternalVote_value.length);
			String finalExternalNodes = StringPool.BLANK;
			for (String externalVote : Vote_ExternalVote_value) {
				String externalNode = externalVoteContentTemplate;
				logger.info("externalVote: " + externalVote);
				externalNode = externalNode.replaceAll(Vote_ExternalVote, externalVote);
				finalExternalNodes = finalExternalNodes.concat(externalNode);
			}
			ExternalVoteNodes_value = finalExternalNodes;
		}

		String finalContent = committeeVotingProcessContentTemplate;
		logger.info("applicationContentTemplate " + committeeVotingProcessContentTemplate);

		logger.info("Replacing values un content template...");
		finalContent = finalContent.replaceAll(Vote_Title, Vote_Title_value);
		finalContent = finalContent.replaceAll(Vote_Description, Vote_Description_value);
		finalContent = finalContent.replaceAll(Vote_ClossingProcess, Vote_ClossingProcess_value);
		finalContent = finalContent.replaceAll(Vote_Status, Vote_Status_value);
		finalContent = finalContent.replaceAll(Vote_ReportVisibility, Vote_ReportVisibility_value);
		finalContent = finalContent.replaceAll(Vote_VotingVisibility, Vote_VotingVisibility_value);
		finalContent = finalContent.replaceAll(Vote_DecidingVote, Vote_DecidingVote_value);
		finalContent = finalContent.replaceAll(ExternalVoteNodes, ExternalVoteNodes_value);
		finalContent = finalContent.replaceAll(Vote_ExternalVote, Vote_ExternalVote_initialValue);

//		finalContent = finalContent.replaceAll(Vote_ExternalVote, Vote_ExternalVote_value);

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
