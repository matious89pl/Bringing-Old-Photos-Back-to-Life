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

public class ApplicationUtil {

	/* logger */
	private static final Log logger = LogFactoryUtil.getLog(ApplicationUtil.class);
	
	/* Structure key */
	private static final String STRUCTURE_KEY = "PARTY-MANAGEMENT";
	/* Template key */
	private static final String TEMPLATE_KEY = "MY-APPLICATION-DETAILS";
	
	/* content params */
	private static final String PM_CompanyName = "@PM_CompanyName";
	private static final String IA_CompanyRegistrationNumber = "@IA_CompanyRegistrationNumber";
	private static final String PM_RegisteredAddress = "@PM_RegisteredAddress";
	private static final String PM_ReasonForSubmission = "@PM_ReasonForSubmission";
	private static final String PM_AuthorisingOfficerFullName = "@PM_AuthorisingOfficerFullName";
	private static final String PM_AuthorisingOfficer1Role = "@PM_AuthorisingOfficer1Role";
	private static final String PM_AuthorisingOfficer1EmailAddress = "@PM_AuthorisingOfficer1EmailAddress";
	private static final String PM_AuthorisingOfficer1BusinessAddress = "@PM_AuthorisingOfficer1BusinessAddress";
	private static final String PM_AuthorisingOfficer1TelephoneNumber = "@PM_AuthorisingOfficer1TelephoneNumber";
	private static final String PM_AnyadditionalAuthorisingOfficer = "@PM_AnyadditionalAuthorisingOfficer";
	private static final String PM_KeyContact1Fullname = "@PM_KeyContact1Fullname";
	private static final String PM_KeyContact1Role = "@PM_KeyContact1Role";
	private static final String PM_KeyContact1EmailAddress = "@PM_KeyContact1EmailAddress";
	private static final String PM_KeyContact1BusinessAddress = "@PM_KeyContact1BusinessAddress";
	private static final String PM_KeyContact1TelephoneNumber = "@PM_KeyContact1TelephoneNumber";
	private static final String PM_Anyadditionalkeycontacts = "@PM_Anyadditionalkeycontacts";
	private static final String PM_Whatroleareyouapplyingfor = "@PM_Whatroleareyouapplyingfor";
	private static final String PM_Whatsystemsareyouapplyingtoaccess = "@PM_Whatsystemsareyouapplyingtoaccess";
	private static final String PM_ProvideDetailsOrganisation = "@PM_ProvideDetailsOrganisation";
	private static final String PM_Status = "@PM_Status";
	
	private static final String option_template = "<option><![CDATA[@VALUE]]></option>";
	
	/* content template */
	private static String applicationContentTemplate = "<?xml version='1.0' encoding='UTF-8'?><root available-locales='en_GB' default-locale='en_GB'><dynamic-element name='PM_CompanyInformationSeparator' type='selection_break' index-type='keyword'><dynamic-element name='PM_CompanyName' type='text' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@PM_CompanyName]]></dynamic-content></dynamic-element><dynamic-element name='IA_CompanyRegistrationNumber' type='text' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@IA_CompanyRegistrationNumber]]></dynamic-content></dynamic-element><dynamic-element name='PM_RegisteredAddress' type='text' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@PM_RegisteredAddress]]></dynamic-content></dynamic-element><dynamic-element name='PM_ReasonForSubmission' type='text' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@PM_ReasonForSubmission]]></dynamic-content></dynamic-element></dynamic-element><dynamic-element name='PM_AuthorisingOfficerSeparator' type='selection_break' index-type='keyword'><dynamic-element name='PM_AuthorisingOfficerFullName' type='text' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@PM_AuthorisingOfficerFullName]]></dynamic-content></dynamic-element><dynamic-element name='PM_AuthorisingOfficer1Role' type='text' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@PM_AuthorisingOfficer1Role]]></dynamic-content></dynamic-element><dynamic-element name='PM_AuthorisingOfficer1EmailAddress' type='text' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@PM_AuthorisingOfficer1EmailAddress]]></dynamic-content></dynamic-element><dynamic-element name='PM_AuthorisingOfficer1BusinessAddress' type='text' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@PM_AuthorisingOfficer1BusinessAddress]]></dynamic-content></dynamic-element><dynamic-element name='PM_AuthorisingOfficer1TelephoneNumber' type='text' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@PM_AuthorisingOfficer1TelephoneNumber]]></dynamic-content></dynamic-element><dynamic-element name='PM_AnyadditionalAuthorisingOfficer' type='list' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@PM_AnyadditionalAuthorisingOfficer]]></dynamic-content></dynamic-element></dynamic-element><dynamic-element name='PM_CompanyKeyInformationSeparator' type='selection_break' index-type='keyword'><dynamic-element name='PM_KeyContact1Fullname' type='text' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@PM_KeyContact1Fullname]]></dynamic-content></dynamic-element><dynamic-element name='PM_KeyContact1Role' type='text' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@PM_KeyContact1Role]]></dynamic-content></dynamic-element><dynamic-element name='PM_KeyContact1EmailAddress' type='text' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@PM_KeyContact1EmailAddress]]></dynamic-content></dynamic-element><dynamic-element name='PM_KeyContact1BusinessAddress' type='text' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@PM_KeyContact1BusinessAddress]]></dynamic-content></dynamic-element><dynamic-element name='PM_KeyContact1TelephoneNumber' type='text' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@PM_KeyContact1TelephoneNumber]]></dynamic-content></dynamic-element><dynamic-element name='PM_Anyadditionalkeycontacts' type='list' index-type='keyword'><dynamic-content language-id='en_GB'>@PM_Anyadditionalkeycontacts</dynamic-content></dynamic-element></dynamic-element><dynamic-element name='PM_Whatroleareyouapplyingfor' type='list' index-type='keyword'><dynamic-content language-id='en_GB'>@PM_Whatroleareyouapplyingfor</dynamic-content></dynamic-element><dynamic-element name='PM_Whatsystemsareyouapplyingtoaccess' type='list' index-type='keyword'><dynamic-content language-id='en_GB'>@PM_Whatsystemsareyouapplyingtoaccess</dynamic-content></dynamic-element><dynamic-element name='PM_ProvideDetailsOrganisation' type='list' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@PM_ProvideDetailsOrganisation]]></dynamic-content></dynamic-element><dynamic-element name='PM_Status' type='list' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@PM_Status]]></dynamic-content></dynamic-element></root>";

	public static long createApplication(Map<String, String> parameters, long groupId, long userId, String title) {
		logger.debug("---START createApplication---");
		long resourcePrimKey = 0;
		long folderId = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;
		
		/* create Article */
		if (logger.isDebugEnabled()) {
			logger.debug("---parameters---");
			parameters.forEach((k, v) -> logger.debug(String.format("%-20s= %s", k, v)));
		}
		
		JournalArticle applicationArticle = createApplicationJournal(parameters, folderId, groupId, userId, title);
		resourcePrimKey = applicationArticle.getResourcePrimKey();
		
		logger.debug("---END createApplication---");
		
		return resourcePrimKey;
	}

	public static long updateApplication(Map<String, String> parameters, long appResourcePK) {
		logger.debug("---START updateApplication---");
		long resourcePrimKey = 0;
		
		/* update Article */
		if (logger.isDebugEnabled()) {
			logger.debug("---parameters---");
			parameters.forEach((k, v) -> logger.debug(String.format("%-20s= %s", k, v)));
		}
		
		JournalArticle applicationArticle = updateApplicationJournal(parameters, appResourcePK);
		if(Validator.isNotNull(applicationArticle)) {
			resourcePrimKey = applicationArticle.getResourcePrimKey();
		}
		
		logger.debug("---END updateApplication---");
		
		return resourcePrimKey;
	}

	private static JournalArticle createApplicationJournal(Map<String, String> parameters, long folderId, long groupId, long userId, String title) {
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
		
		String applicationContent = replaceContentWithFields(parameters);
		logger.debug("Content replaced according to form fields! " + applicationContent);
		
		/* create service context */
		ServiceContext serviceContext = new ServiceContext();
		try {
			serviceContext.setScopeGroupId(groupId);
			serviceContext.setAddGroupPermissions(false);
			serviceContext.setAddGuestPermissions(false);
			serviceContext.setWorkflowAction(1);
		
			article = JournalArticleLocalServiceUtil.addArticle(userId, groupId, folderId, titleMap, null,
					applicationContent, structureKey, templateKey, serviceContext);

			logger.info("Application Journal Article created!");
		} catch (PortalException e1) {
			logger.error("Error creating journalArticle", e1);
		}
		
		logger.debug("---END createApplicationJournal---");
		return article;
	}
	
	private static JournalArticle updateApplicationJournal(Map<String, String> parameters, long resourcePK) {
		logger.debug("---START updateApplicationJournal---");
		
		JournalArticle article = null;
		JournalArticle updatedArticle = null;
		
		String applicationContent = replaceContentWithFields(parameters);
		logger.debug("Content replaced according to form fields! " + applicationContent);
		
		article = JournalArticleLocalServiceUtil.fetchLatestArticle(resourcePK);
		if (Validator.isNotNull(article)) {
			double version = article.getVersion();
			article.setVersion(version + 0.1);
			article.setContent(applicationContent);
			updatedArticle = JournalArticleLocalServiceUtil.updateJournalArticle(article);
			logger.info("Application Journal Article edited!");
		}

		logger.debug("---END updateApplicationJournal---");
		return updatedArticle;
	}
	
	private static String replaceContentWithFields(Map<String, String> parameters) {
		logger.debug("---START replaceContentWithFields---");
		
		logger.debug("Getting values from parameters...");
		String PM_CompanyName_value = parameters.get("PM_CompanyName");
		String IA_CompanyRegistrationNumber_value = parameters.get("IA_CompanyRegistrationNumber");
		String PM_RegisteredAddress_value = parameters.get("PM_RegisteredAddress");
		String PM_ReasonForSubmission_value = parameters.get("PM_ReasonForSubmission");
		String PM_AuthorisingOfficerFullName_value = parameters.get("PM_AuthorisingOfficerFullName");
		String PM_AuthorisingOfficer1Role_value = parameters.get("PM_AuthorisingOfficer1Role");
		String PM_AuthorisingOfficer1EmailAddress_value = parameters.get("PM_AuthorisingOfficer1EmailAddress");
		String PM_AuthorisingOfficer1BusinessAddress_value = parameters.get("PM_AuthorisingOfficer1BusinessAddress");
		String PM_AuthorisingOfficer1TelephoneNumber_value = parameters.get("PM_AuthorisingOfficer1TelephoneNumber");
		String PM_AnyadditionalAuthorisingOfficer_value = parameters.get("PM_AnyadditionalAuthorisingOfficer");
		String PM_KeyContact1Fullname_value = parameters.get("PM_KeyContact1Fullname");
		String PM_KeyContact1Role_value = parameters.get("PM_KeyContact1Role");
		String PM_KeyContact1EmailAddress_value = parameters.get("PM_KeyContact1EmailAddress");
		String PM_KeyContact1BusinessAddress_value = parameters.get("PM_KeyContact1BusinessAddress");
		String PM_KeyContact1TelephoneNumber_value = parameters.get("PM_KeyContact1TelephoneNumber");
		String PM_Anyadditionalkeycontacts_value = parameters.get("PM_Anyadditionalkeycontacts");
		String PM_Whatroleareyouapplyingfor_value = parameters.get("PM_Whatroleareyouapplyingfor");
		if (Validator.isNotNull(PM_Whatroleareyouapplyingfor_value)) {
			if(PM_Whatroleareyouapplyingfor_value.contains(";spt;")) {
				String multiple_options = StringPool.BLANK;
				for (String option_element : PM_Whatroleareyouapplyingfor_value.split(";spt;")) {
					multiple_options.concat(option_template.replaceAll("@VALUE", option_element)) ;
				}
				PM_Whatroleareyouapplyingfor_value = multiple_options;
			}else {
				PM_Whatroleareyouapplyingfor_value = option_template.replaceAll("@VALUE", PM_Whatroleareyouapplyingfor_value);
			}
		}
		String PM_Whatsystemsareyouapplyingtoaccess_value = parameters.get("PM_Whatsystemsareyouapplyingtoaccess");
		if (Validator.isNotNull(PM_Whatsystemsareyouapplyingtoaccess_value)) {
			if(PM_Whatsystemsareyouapplyingtoaccess_value.contains(";spt;")) {
				String multiple_options_2 = StringPool.BLANK;
				for (String option_element_2 : PM_Whatsystemsareyouapplyingtoaccess_value.split(";spt;")) {
					multiple_options_2.concat(option_template.replaceAll("@VALUE", option_element_2)) ;
				}
				PM_Whatsystemsareyouapplyingtoaccess_value = multiple_options_2;
			}else {
				PM_Whatsystemsareyouapplyingtoaccess_value = option_template.replaceAll("@VALUE", PM_Whatsystemsareyouapplyingtoaccess_value);
			}
		}
		String PM_ProvideDetailsOrganisation_value = parameters.get("PM_ProvideDetailsOrganisation");
		String PM_Status_value = parameters.get("PM_Status");

		String finalContent = applicationContentTemplate;
		logger.debug("applicationContentTemplate " + applicationContentTemplate);

		logger.debug("Replacing values un content template...");
		finalContent = finalContent.replaceAll(PM_CompanyName, PM_CompanyName_value);
		finalContent = finalContent.replaceAll(IA_CompanyRegistrationNumber, IA_CompanyRegistrationNumber_value);
		finalContent = finalContent.replaceAll(PM_RegisteredAddress, PM_RegisteredAddress_value);
		finalContent = finalContent.replaceAll(PM_ReasonForSubmission, PM_ReasonForSubmission_value);
		finalContent = finalContent.replaceAll(PM_AuthorisingOfficerFullName, PM_AuthorisingOfficerFullName_value);
		finalContent = finalContent.replaceAll(PM_AuthorisingOfficer1Role, PM_AuthorisingOfficer1Role_value);
		finalContent = finalContent.replaceAll(PM_AuthorisingOfficer1EmailAddress, PM_AuthorisingOfficer1EmailAddress_value);
		finalContent = finalContent.replaceAll(PM_AuthorisingOfficer1BusinessAddress, PM_AuthorisingOfficer1BusinessAddress_value);
		finalContent = finalContent.replaceAll(PM_AuthorisingOfficer1TelephoneNumber, PM_AuthorisingOfficer1TelephoneNumber_value);
		finalContent = finalContent.replaceAll(PM_AnyadditionalAuthorisingOfficer, PM_AnyadditionalAuthorisingOfficer_value);
		finalContent = finalContent.replaceAll(PM_KeyContact1Fullname, PM_KeyContact1Fullname_value);
		finalContent = finalContent.replaceAll(PM_KeyContact1Role, PM_KeyContact1Role_value);
		finalContent = finalContent.replaceAll(PM_KeyContact1EmailAddress, PM_KeyContact1EmailAddress_value);
		finalContent = finalContent.replaceAll(PM_KeyContact1BusinessAddress, PM_KeyContact1BusinessAddress_value);
		finalContent = finalContent.replaceAll(PM_KeyContact1TelephoneNumber, PM_KeyContact1TelephoneNumber_value);
		finalContent = finalContent.replaceAll(PM_Anyadditionalkeycontacts, PM_Anyadditionalkeycontacts_value);
		finalContent = finalContent.replaceAll(PM_Whatroleareyouapplyingfor, PM_Whatroleareyouapplyingfor_value);
		finalContent = finalContent.replaceAll(PM_Whatsystemsareyouapplyingtoaccess, PM_Whatsystemsareyouapplyingtoaccess_value);
		finalContent = finalContent.replaceAll(PM_ProvideDetailsOrganisation, PM_ProvideDetailsOrganisation_value);
		finalContent = finalContent.replaceAll(PM_Status, PM_Status_value);

		logger.debug("finalContent: " + finalContent);
		
		logger.debug("---END replaceContentWithFields---");
		return finalContent;
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

}
