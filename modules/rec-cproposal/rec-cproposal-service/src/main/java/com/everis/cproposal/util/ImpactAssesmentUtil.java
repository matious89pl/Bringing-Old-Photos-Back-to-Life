package com.everis.cproposal.util;

import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.model.DLVersionNumberIncrease;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
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
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;

public class ImpactAssesmentUtil {
	private final static Log logger = LogFactoryUtil.getLog(ImpactAssesmentUtil.class);

	/* Structure key */
	private static final String STRUCTURE_KEY = "IMPACT-ASSESSMENT";
	/* Template key */
	private static final String TEMPLATE_KEY = "IMPACT-ASSESSMENT";
	private static final String option_template = "<option><![CDATA[@VALUE]]></option>";

    private static String applicationContentTemplateForIA = "<?xml version='1.0'?> <root available-locales='en_GB' default-locale='en_GB'> <dynamic-element name='IA_CPID' type='text' index-type='keyword'> <dynamic-content language-id='en_GB'><![CDATA[@IA_CPID]]></dynamic-content> </dynamic-element> <dynamic-element name='IA_Title' type='text' index-type='keyword' > <dynamic-content language-id='en_GB'><![CDATA[@IA_Title]]></dynamic-content> </dynamic-element> <dynamic-element name='IA_Type' type='list' index-type='keyword' > <dynamic-content language-id='en_GB'><![CDATA[@IA_Type]]></dynamic-content> </dynamic-element> <dynamic-element name='IA_TargetedAudience' type='list' index-type='keyword' > <dynamic-content language-id='en_GB'>@IA_TargetedAudience</dynamic-content> </dynamic-element> <dynamic-element name='IA_ChooseQATemplate' type='list' index-type='keyword' > <dynamic-content language-id='en_GB'><![CDATA[@IA_ChooseQATemplate]]></dynamic-content> </dynamic-element> <dynamic-element name='IA_LinkToCPPage' type='text' index-type='keyword' > <dynamic-content language-id='en_GB'><![CDATA[@IA_LinkToCPPage]]></dynamic-content> </dynamic-element> @IA_Doc_to_replaceXML  <dynamic-element name='IA_ResponsesDeadline' type='ddm-date' index-type='keyword' > <dynamic-content language-id='en_GB'><![CDATA[@IA_ResponsesDeadline]]></dynamic-content> </dynamic-element> </root>";

    private static final String DOCUMENTS_TEMPLATE = "<dynamic-element name='IA_Documents' type='document_library' index-type='keyword'><dynamic-element name='IA_Document_Title'  type='text' index-type='keyword'>  <dynamic-content language-id='en_GB'><![CDATA[@IA_Doc_Title_to_replace]]></dynamic-content> </dynamic-element><dynamic-content language-id='en_GB'><![CDATA[@IA_Doc_to_replace]]></dynamic-content></dynamic-element>";
    private static final String IA_Title = "@IA_Title";

    private static final String IA_Doc_Title_to_replace = "@IA_Doc_Title_to_replace";
    private static final String IA_Doc_to_replace = "@IA_Doc_to_replace";

	private static final String IA_CPID = "@IA_CPID";
	private static final String IA_Type = "@IA_Type";
	private static final String IA_TargetedAudience = "@IA_TargetedAudience";
	private static final String IA_ChooseQATemplate = "@IA_ChooseQATemplate";
	private static final String IA_LinkToCPPage = "@IA_LinkToCPPage";
    private static final String IA_Documents = "@IA_Doc_to_replaceXML";
	private static final String IA_Document_Title = "@IA_Document_Title";
	private static final String IA_ResponsesDeadline = "@IA_ResponsesDeadline";
	
    private static String replaceContentWithFields(Map<String, String> parameters) throws JSONException {
		logger.debug("---START replaceContentWithFields---");
		
		logger.debug("Getting values from parameters...");
		String IA_Title_value = parameters.get("IA_Title");
		String IA_CPID_value = parameters.get("IA_CPID");
		String IA_Type_value = parameters.get("IA_Type");
		String IA_ChooseQATemplate_value = parameters.get("IA_ChooseQATemplate");
		String IA_LinkToCPPage_value = parameters.get("IA_LinkToCPPage");
		String IA_Documents_value = parameters.get("IA_Documents");

        logger.debug("=====================================IA_Documents_value" + IA_Documents_value);

        if (IA_Documents_value != "") {
            JSONArray docsArray = JSONFactoryUtil.createJSONArray(IA_Documents_value);
            String multiple_Doc = StringPool.BLANK;
            for (int i = 0; i < docsArray.length(); i++) {
                JSONObject docJSONObject = docsArray.getJSONObject(i);
                String docTitle = docJSONObject.getString("doctitle");
                long docClassPK = docJSONObject.getLong("classPK");
                docJSONObject.remove("doctitle");
                String toReplace = StringPool.QUOTE + "classPK" + StringPool.QUOTE + ":" + StringPool.QUOTE + docClassPK + StringPool.QUOTE;
                String replace = StringPool.QUOTE + "classPK" + StringPool.QUOTE + ":" + docClassPK;
                logger.debug("toReplace" + toReplace);
                logger.debug("replace" + replace);
                String docFile = docJSONObject.toString();

                docFile = docFile.replaceFirst(toReplace, replace);
                logger.debug("docFile" + docFile);

                multiple_Doc = multiple_Doc.concat(DOCUMENTS_TEMPLATE.replaceAll("@IA_Doc_to_replace", docFile).replaceAll("@IA_Doc_Title_to_replace", docTitle));
                logger.debug("=====================================multiple_Doc replace in xml" + multiple_Doc);
            }
            IA_Documents_value = multiple_Doc;
        }

        logger.debug("=====================================IA_Documents_value after loop to add to content" + IA_Documents_value);

		String IA_ResponsesDeadline_value = parameters.get("IA_ResponsesDeadline");
		
		String IA_TargetedAudience_value = parameters.get("IA_TargetedAudience");
        logger.debug("=====================================IA_ResponsesDeadline_value " + IA_ResponsesDeadline_value);

			IA_TargetedAudience_value = IA_TargetedAudience_value.replace("[", "").replace("]", "").replaceAll("\"", "");
			String[] TargetAU;
        TargetAU = IA_TargetedAudience_value.split(",");
			String multiple_options = StringPool.BLANK;
			
        for (int i = 0; i < TargetAU.length; i++) {
					multiple_options = multiple_options.concat(option_template.replaceAll("@VALUE", TargetAU[i]));
					IA_TargetedAudience_value = multiple_options;
				}
		String finalContent = applicationContentTemplateForIA;
		logger.debug("applicationContentTemplate " + applicationContentTemplateForIA);

		logger.debug("Replacing values un content template...");
		finalContent = finalContent.replaceAll(IA_Title, IA_Title_value);

		finalContent = finalContent.replaceAll(IA_CPID, IA_CPID_value);
		finalContent = finalContent.replaceAll(IA_Type, IA_Type_value);
		finalContent = finalContent.replaceAll(IA_TargetedAudience, IA_TargetedAudience_value);
		finalContent = finalContent.replaceAll(IA_ChooseQATemplate, IA_ChooseQATemplate_value);
		finalContent = finalContent.replaceAll(IA_LinkToCPPage, IA_LinkToCPPage_value);
		finalContent = finalContent.replaceAll(IA_Documents, IA_Documents_value);
		finalContent = finalContent.replaceAll(IA_ResponsesDeadline, IA_ResponsesDeadline_value);

		logger.debug("finalContent: " + finalContent);
		
		logger.debug("---END replaceContentWithFields---");
		return finalContent;
	}

    private static String removeQuotesFromStartAndEndOfString(String inputStr) {
        String result = inputStr;
        int firstQuote = inputStr.indexOf('\"');
        int lastQuote = result.lastIndexOf('\"');
        int strLength = inputStr.length();
        if (firstQuote == 0 && lastQuote == strLength - 1) {
            result = result.substring(1, strLength - 1);
        }
        return result;
    }

	public static long updateIA(Map<String, String> parameters, long appResourcePK) {
		logger.debug("---START updateApplication---");
		long resourcePrimKey = 0;
		
		/* update Article */
		if (logger.isDebugEnabled()) {
			logger.debug("---parameters---");
			parameters.forEach((k, v) -> logger.debug(String.format("%-20s= %s", k, v)));
		}
		
		JournalArticle applicationArticle = updateApplicationJournalIA(parameters, appResourcePK);
        if (Validator.isNotNull(applicationArticle)) {
			resourcePrimKey = applicationArticle.getResourcePrimKey();
		}
		
		logger.debug("---END updateApplication---");
		
		return resourcePrimKey;
	}

	private static JournalArticle updateApplicationJournalIA(Map<String, String> parameters, long resourcePK) {
		logger.debug("---START updateApplicationJournal---");
		
        JournalArticle article;
		JournalArticle updatedArticle = null;
		
        String applicationContent = null;
        try {
            applicationContent = replaceContentWithFields(parameters);
        } catch (JSONException e) {
            logger.error("---Error---", e);
        }
		logger.debug("Content replaced according to form fields! " + applicationContent);
		
		article = JournalArticleLocalServiceUtil.fetchLatestArticle(resourcePK);
        JournalArticle previous = JournalArticleLocalServiceUtil.getPreviousApprovedArticle(article);
        logger.debug("previous " + previous);

		if (Validator.isNotNull(article)) {
			double version = article.getVersion();
            logger.debug("version" + version);

			article.setContent(applicationContent);
            ServiceContext serviceContext = new ServiceContext();
            serviceContext.setScopeGroupId(article.getGroupId());
            serviceContext.setAddGroupPermissions(true);
            serviceContext.setAddGuestPermissions(false);

            try {
                String newTitle = parameters.get("IA_Title");
                logger.debug("newTitle " + newTitle);
                article.getExpandoBridge().setAttribute("customTitle", newTitle);

                updatedArticle = JournalArticleLocalServiceUtil.updateArticle(article.getUserId(), article.getGroupId(),
                        article.getFolderId(), article.getArticleId(), version, article.getTitleMap(), article.getDescriptionMap(), article.getContent(), article.getLayoutUuid(), serviceContext);
            } catch (PortalException e) {
                logger.error("---Error---", e);
            }
			logger.debug("Application Journal Article edited!");
		}

		logger.debug("---END updateApplicationJournal---");
		return updatedArticle;
	}
	
	public static long createIA(Map<String, String> parameters, long groupId, long userId, String title) {

		logger.debug("---START createApplication---");
		long resourcePrimKey = 0;
		long folderId = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;
		/* create Article */
		if (logger.isDebugEnabled()) {
			logger.debug("---parameters---");
			parameters.forEach((k, v) -> logger.debug(String.format("%-20s= %s", k, v)));
		}
		try {
            logger.debug("---parameters---" + parameters + folderId + groupId + userId + title);
		JournalArticle applicationArticle = createApplicationJournal_IA(parameters, folderId, groupId, userId, title);
		resourcePrimKey = applicationArticle.getResourcePrimKey();
        } catch (Exception e) {
			logger.error("---Error---", e);
		}

		logger.debug("---END createApplication---");
		
		return resourcePrimKey;
	}

	private static JournalArticle createApplicationJournal_IA(Map<String, String> parameters, long folderId, long groupId, long userId, String title) {
		logger.debug("---START createApplicationJournal---");
		
		JournalArticle article = null;
		Map<Locale, String> titleMap = new HashMap<>();
		Locale locale = LocaleUtil.getDefault();
		
		DynamicQuery dqStructure = DDMStructureLocalServiceUtil.dynamicQuery();
		dqStructure.add(PropertyFactoryUtil.forName("name").like("%" + STRUCTURE_KEY + "%"));
		List<DDMStructure> ddmListStructure = DDMStructureLocalServiceUtil.dynamicQuery(dqStructure);
		logger.debug("ddmListStructure... " + ddmListStructure);
		
		DynamicQuery dqTemplate = DDMTemplateLocalServiceUtil.dynamicQuery();
		dqTemplate.add(PropertyFactoryUtil.forName("name").like("%" + TEMPLATE_KEY + "%"));
		List<DDMTemplate> ddmListTemplate = DDMTemplateLocalServiceUtil.dynamicQuery(dqTemplate);
		logger.debug("ddmListTemplate... " + ddmListTemplate);

		String structureKey = ddmListStructure.get(0).getStructureKey();
		String templateKey = ddmListTemplate.get(0).getTemplateKey();
		logger.debug("structureKey... " + structureKey + " - templateKey... " + templateKey);
		
		String contentName = validTitleDescription(title);
		titleMap.put(locale, contentName);
		logger.debug("Params before " + parameters);

        String applicationContent = null;
        try {
            applicationContent = replaceContentWithFields(parameters);
        } catch (JSONException e) {
            logger.error("---Error---", e);
        }
		logger.debug("params after! " + applicationContent);
		
		/* create service context */
		ServiceContext serviceContext = new ServiceContext();

			try {
				serviceContext.setScopeGroupId(groupId);
				serviceContext.setAddGroupPermissions(true);
				serviceContext.setAddGuestPermissions(false);

				serviceContext.setWorkflowAction(1);

            article = JournalArticleLocalServiceUtil.addArticle(userId, groupId, folderId, titleMap, titleMap,
                    applicationContent, structureKey, templateKey, serviceContext);
            try {
                article.getExpandoBridge().setAttribute("customTitle", title);
            } catch (Exception e) {
                logger.debug("error with custom field " + e);
            }
			} catch (PortalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.debug("!" + e);
			}

		logger.debug("---END createApplicationJournal---");
		return article;
	}

	private static String validTitleDescription(String text) {
		logger.debug("---START validTitleDescription---");
		if (Validator.isNotNull(text)) {
			text = text.replaceAll("\\$", Matcher.quoteReplacement("\\$"));
			text = text.replaceAll("&#039;", "'");
			text = text.replaceAll("&#034;", "\"");
			text = text.replaceAll("&amp;", "&");
		}
		logger.debug("---END validTitleDescription---");
		return text;
	}

    public static JSONObject uploadYourFilesFileEntryForIA(String title, long repositoryId, long folderId, long userId,
                                                           byte[] fileBytes, String mimeType, String description, String date, String[] TargetedAudience,
                                                           ServiceContext serviceContext) throws PortalException {

		JSONObject response = JSONFactoryUtil.createJSONObject();
        FileEntry fileEntry;
        FileEntry fileEntrySearch = null;

		String sourceFileName = title.trim();

        logger.debug("fileBytes -> " + fileBytes);

		try {
			fileEntrySearch = DLAppLocalServiceUtil.getFileEntry(repositoryId, folderId, title);
        } catch (PortalException e) {
			logger.debug("No file existing with name : " + title);
		}

		if (Validator.isNotNull(fileBytes) && fileEntrySearch == null) {
            fileEntry = DLAppLocalServiceUtil.addFileEntry(userId, repositoryId, folderId, sourceFileName, mimeType,
                    title, description, StringPool.BLANK, fileBytes, serviceContext);
			logger.debug("No file existing with name : " + title + " Adding to D&M");
			response.put("fileEntryId", fileEntry.getFileEntryId());
			response.put("uuid", fileEntry.getUuid());
			response.put("type", fileEntry.getMimeType());
        } else if (fileEntrySearch != null) { //cambiar esto, puede editar cualquier fichero subido
			fileEntry = DLAppLocalServiceUtil.updateFileEntry(userId, fileEntrySearch.getFileEntryId(), sourceFileName,
                    mimeType, title, description, StringPool.BLANK, DLVersionNumberIncrease.fromMajorVersion(true),
                    fileBytes, serviceContext);
			logger.debug("File is existing with name : " + title + " Updating it to D&M");
			response.put("fileEntryId", fileEntry.getFileEntryId());
			response.put("uuid", fileEntry.getUuid());
			response.put("type", fileEntry.getMimeType());

		}

		return response;
	}

}
