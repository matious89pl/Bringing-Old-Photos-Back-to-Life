package com.everis.cproposal.util;

import com.everis.cproposal.model.recFormArticle;
import com.everis.cproposal.service.recFormArticleLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.dynamic.data.mapping.model.DDMFormInstanceRecordVersion;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMFormInstanceRecordVersionLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalFolder;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.journal.service.JournalFolderLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;

public class CProposalUtil {

	private static final Log logger = LogFactoryUtil.getLog(CProposalUtil.class);

	public static final String TEMPLATE_XML = "/templates/change_proposal_template.xml";

	private static final String STRUCTURE_KEY = "CHANGE-PROPOSAL";
	private static final String TEMPLATE_KEY = "CHANGE-PROPOSAL";

	private static final String CPROPOSAL_FOLDER_NAME = "Change Proposal Folder";
	private static final String Empty = "Empty";
	private static final String CHANGE_PROPOSAL_REFERENCE = "@CHANGE_PROPOSAL_REFERENCE";
	private static final String CHANGE_PROPOSAL_TITLE = "@CHANGE_PROPOSAL_TITLE";
	private static final String CHANGE_PROPOSAL_STATUS = "@CHANGE_PROPOSAL_STATUS";
	private static final String CHANGE_PROPOSAL_EMAIL = "@CHANGE_PROPOSAL_EMAIL";
	private static final String CHANGE_PROPOSAL_LINK_TO_CP_PAGE = "@CHANGE_PROPOSAL_LINK_TO_CP_PAGE";
	private static final String CHANGE_PROPOSAL_LINK_TO_CONSULTATION_PAGE = "@CHANGE_PROPOSAL_LINK_TO_CONSULTATION_PAGE";
	private static final String CHANGE_PROPOSAL_LINK_TO_ALTERNATIVE_CP = "@CHANGE_PROPOSAL_LINK_TO_ALTERNATIVE_CP";
	private static final String CHANGE_PROPOSAL_IMPACTEDSEGMETNSIS = "@CHANGE_PROPOSAL_IMPACTEDSEGMETNSIS";
	private static final String CHANGE_PROPOSAL_PRIORITYSCORE = "@CHANGE_PROPOSAL_PRIORITYSCORE";
	private static final String CHANGE_PROPOSAL_FUEL = "@CHANGE_PROPOSAL_FUEL";
	private static final String CHANGE_PROPOSAL_CONSUMERTYPES = "@CHANGE_PROPOSAL_CONSUMERTYPES";
	private static final String CHANGE_PROPOSAL_LATEST_UPDATE = "@CHANGE_PROPOSAL_LATEST_UPDATE";
	private static final String CHANGE_PROPOSAL_CHANGE_SUMMARY = "@CHANGE_PROPOSAL_CHANGE_SUMMARY";
	private static final String CHANGE_PROPOSAL_RELATED_LINKTITLE = "@CHANGE_PROPOSAL_RELATED_LINKTITLE";
	private static final String CHANGE_PROPOSAL_RELATED_LINK = "@CHANGE_PROPOSAL_RELATED_LINK";

	private static final String CHANGE_PROPOSAL_INITIAL_ASSESSMENT_PB = "@CHANGE_PROPOSAL_INITIAL_ASSESSMENT_PB";
	private static final String CHANGE_PROPOSAL_SOLUTION_DEVELOPMENT_PB = "@CHANGE_PROPOSAL_SOLUTION_DEVELOPMENT_PB";
	private static final String CHANGE_PROPOSAL_SERVICE_PROVIDER_IMPACT_ASSESSMENT_PB = "@CHANGE_PROPOSAL_SERVICE_PROVIDER_IMPACT_ASSESSMENT_PB";
	private static final String CHANGE_PROPOSAL_PARTY_IMPACT_ASSESSMENT_PB = "@CHANGE_PROPOSAL_PARTY_IMPACT_ASSESSMENT_PB";
	private static final String CHANGE_PROPOSAL_PRELIMINARY_ASSESSMENT_PB = "@CHANGE_PROPOSAL_PRELIMINARY_ASSESSMENT_PB";
	private static final String CHANGE_PROPOSAL_CONSULTATION_PB = "@CHANGE_PROPOSAL_CONSULTATION_PB";
	private static final String CHANGE_PROPOSAL_FINAL_ASSESSMENT_PB = "@CHANGE_PROPOSAL_FINAL_ASSESSMENT_PB";
	private static final String CHANGE_PROPOSAL_APPROVED_WAITING_IMPLEMENTATION_PB = "@CHANGE_PROPOSAL_APPROVED_WAITING_IMPLEMENTATION_PB";
	private static final String CHANGE_PROPOSAL_REJECTED_PB = "@CHANGE_PROPOSAL_REJECTED_PB";
	private static final String CHANGE_PROPOSAL_AWAITING_AUTHORITY_DECISION_PB = "@CHANGE_PROPOSAL_AWAITING_AUTHORITY_DECISION_PB";
	private static final String CHANGE_PROPOSAL_WITHDRAWN_PB = "@CHANGE_PROPOSAL_WITHDRAWN_PB";
	private static final String CHANGE_PROPOSAL_APPEALINPROGRESS_PB = "@CHANGE_PROPOSAL_APPEALINPROGRESS_PB";
	private static final String CHANGE_PROPOSAL_IMPLEMENTED_PB = "@CHANGE_PROPOSAL_IMPLEMENTED_PB";
	private static final String CHANGE_PROPOSAL_NEW_PB = "@CHANGE_PROPOSAL_NEW_PB";





	private static final String CHANGE_PROPOSAL_LINK_TO_ORIGINAL_CP = "@CHANGE_PROPOSAL_LINK_TO_ORIGINAL_CP";
	private static final String CHANGE_PROPOSAL_PROBLEM_STATEMENT = "@CHANGE_PROPOSAL_PROBLEM_STATEMENT";
	private static final String CHANGE_PROPOSAL_SOLUTION_REQUIREMENTS = "@CHANGE_PROPOSAL_SOLUTION_REQUIREMENTS";
	private static final String CHANGE_PROPOSAL_CHANGE_CATEGORY = "@CHANGE_PROPOSAL_CHANGE_CATEGORY";
	private static final String CHANGE_PROPOSAL_CHANGE_PATH = "@CHANGE_PROPOSAL_CHANGE_PATH";
	private static final String CHANGE_PROPOSAL_RESPONSIBLE_COMMITTEE = "@CHANGE_PROPOSAL_RESPONSIBLE_COMMITTEE";
	private static final String CHANGE_PROPOSAL_PRIORITY_STATUS = "@CHANGE_PROPOSAL_PRIORITY_STATUS";
	private static final String CHANGE_PROPOSAL_URGENT_CHANGE = "@CHANGE_PROPOSAL_URGENT_CHANGE";
	private static final String CHANGE_PROPOSAL_DOCUMENT_TITLE = "@CHANGE_PROPOSAL_DOCUMENT_TITLE";
	private static final String CHANGE_PROPOSAL_DOCUMENT = "@CHANGE_PROPOSAL_DOCUMENT";
	private static final String CHANGE_PROPOSAL_LEAD_CHANGE_ANALYST = "@CHANGE_PROPOSAL_LEAD_CHANGE_ANALYST";
	private static final String CHANGE_PROPOSAL_LEAD_ASSURANCE_ANALYST = "@CHANGE_PROPOSAL_LEAD_ASSURANCE_ANALYST";
	private static final String CHANGE_PROPOSAL_LEAD_TECHNICAL_ANALYST = "@CHANGE_PROPOSAL_LEAD_TECHNICAL_ANALYST";
	private static final String CHANGE_PROPOSAL_PCR_DEADLINE = "@CHANGE_PROPOSAL_PCR_DEADLINE";
	private static final String CHANGE_PROPOSAL_PCR = "@CHANGE_PROPOSAL_PCR";
	private static final String CHANGE_PROPOSAL_FCR_DEADLINE = "@CHANGE_PROPOSAL_FCR_DEADLINE";
	private static final String CHANGE_PROPOSAL_FCR = "@CHANGE_PROPOSAL_FCR";
	private static final String CHANGE_PROPOSAL_SOLUTION_OPTION = "@CHANGE_PROPOSAL_SOLUTION_OPTION";
	private static final String CHANGE_PROPOSAL_RELEASE_SCHEDULE = "@CHANGE_PROPOSAL_RELEASE_SCHEDULE";
	private static final String CHANGE_PROPOSAL_IAR = "@CHANGE_PROPOSAL_IAR";
	private static final String CHANGE_PROPOSAL_IARDEADLINE = "@CHANGE_PROPOSAL_IARDEADLINE";

	private static final String DOCUMENTS_DYNAMIC_ELEMENT = "@DOCUMENTS_DYNAMIC_ELEMENT";

	private static String standardContent;

	private static String documentNodeTemplate = "<dynamic-element name='AttachDocuments' type='document_library' index-type='keyword'><dynamic-element name='DocumentTitle' type='text' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@CHANGE_PROPOSAL_DOCUMENT_TITLE]]></dynamic-content></dynamic-element><dynamic-content language-id='en_GB'><![CDATA[@CHANGE_PROPOSAL_DOCUMENT]]></dynamic-content></dynamic-element>";

	public static long createCProposal(long classPK) throws DocumentException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		long resourcePrimKey = 0;



		standardContent = "<?xml version='1.0'?> <root available-locales='en_GB' default-locale='en_GB'><dynamic-element name='ChangeProposalSeparator' type='selection_break' index-type='keyword'><dynamic-element name='ChangeProposalReference' type='text' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@CHANGE_PROPOSAL_REFERENCE]]></dynamic-content></dynamic-element><dynamic-element name='ChangeProposalTitle' type='text' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@CHANGE_PROPOSAL_TITLE]]></dynamic-content></dynamic-element><dynamic-element name='ChangeProposalStatus' type='list' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@CHANGE_PROPOSAL_STATUS]]></dynamic-content></dynamic-element><dynamic-element name='ChangeProposerEmail' type='text' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@CHANGE_PROPOSAL_EMAIL]]></dynamic-content></dynamic-element></dynamic-element><dynamic-element name='LinkToCPPage' type='text' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@CHANGE_PROPOSAL_LINK_TO_CP_PAGE]]></dynamic-content></dynamic-element><dynamic-element name='LinkToConsultationPage' type='text' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@CHANGE_PROPOSAL_LINK_TO_CONSULTATION_PAGE]]></dynamic-content></dynamic-element><dynamic-element name='relatedTitleLinkCP' type='text' index-type='keyword'><dynamic-element name='relatedUrlLinkCP'  type='text' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@CHANGE_PROPOSAL_RELATED_LINKTITLE]]></dynamic-content></dynamic-element><dynamic-element name='progressBarCP' type='selection_break' index-type='keyword' ><dynamic-element name='initialAssessmentPB'  type='boolean' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@CHANGE_PROPOSAL_INITIAL_ASSESSMENT_PB]]></dynamic-content></dynamic-element><dynamic-element name='solutionDevelopmentPB' type='boolean' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@CHANGE_PROPOSAL_SOLUTION_DEVELOPMENT_PB]]></dynamic-content></dynamic-element><dynamic-element name='ServiceProviderImpactAssessmentPB'  type='boolean' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@CHANGE_PROPOSAL_SERVICE_PROVIDER_IMPACT_ASSESSMENT_PB]]></dynamic-content></dynamic-element><dynamic-element name='PartyImpactAssessmentPB'  type='boolean' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@CHANGE_PROPOSAL_PARTY_IMPACT_ASSESSMENT_PB]]></dynamic-content></dynamic-element><dynamic-element name='PreliminaryAssessmentPB' type='boolean' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@CHANGE_PROPOSAL_PRELIMINARY_ASSESSMENT_PB]]></dynamic-content></dynamic-element><dynamic-element name='ConsultationPB'  type='boolean' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@CHANGE_PROPOSAL_CONSULTATION_PB]]></dynamic-content></dynamic-element><dynamic-element name='FinalAssessmentPB'  type='boolean' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@CHANGE_PROPOSAL_FINAL_ASSESSMENT_PB]]></dynamic-content></dynamic-element><dynamic-element name='ApprovedWaitingImplementationPB'  type='boolean' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@CHANGE_PROPOSAL_APPROVED_WAITING_IMPLEMENTATION_PB]]></dynamic-content></dynamic-element><dynamic-element name='rejectedPB'  type='boolean' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@CHANGE_PROPOSAL_REJECTED_PB]]></dynamic-content></dynamic-element><dynamic-element name='AwaitingAuthorityDecisionPB'  type='boolean' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@CHANGE_PROPOSAL_AWAITING_AUTHORITY_DECISION_PB]]></dynamic-content></dynamic-element><dynamic-element name='WithdrawnPB'  type='boolean' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@CHANGE_PROPOSAL_WITHDRAWN_PB]]></dynamic-content></dynamic-element><dynamic-element name='AppealInProgressPB'  type='boolean' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@CHANGE_PROPOSAL_APPEALINPROGRESS_PB]]></dynamic-content></dynamic-element><dynamic-element name='ImplementedPB'  type='boolean' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@CHANGE_PROPOSAL_IMPLEMENTED_PB]]></dynamic-content></dynamic-element><dynamic-element name='NewPB'  type='boolean' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@CHANGE_PROPOSAL_NEW_PB]]></dynamic-content></dynamic-element></dynamic-element><dynamic-content language-id='en_GB'><![CDATA[@CHANGE_PROPOSAL_RELATED_LINK]]></dynamic-content></dynamic-element><dynamic-element name='LinkToAlternativeCP' type='text' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@CHANGE_PROPOSAL_LINK_TO_ALTERNATIVE_CP]]></dynamic-content></dynamic-element><dynamic-element name='impactedSegmentsISCP' type='text' index-type='keyword' > <dynamic-content language-id='en_GB'><![CDATA[@CHANGE_PROPOSAL_IMPACTEDSEGMETNSIS]]></dynamic-content> </dynamic-element> <dynamic-element name='priorityScoreCP' type='text' index-type='keyword' > <dynamic-content language-id='en_GB'><![CDATA[@CHANGE_PROPOSAL_PRIORITYSCORE]]></dynamic-content> </dynamic-element> <dynamic-element name='fuelCP' type='text' index-type='keyword' > <dynamic-content language-id='en_GB'><![CDATA[@CHANGE_PROPOSAL_FUEL]]></dynamic-content> </dynamic-element> <dynamic-element name='consumerTypesCP' type='text' index-type='keyword' > <dynamic-content language-id='en_GB'><![CDATA[@CHANGE_PROPOSAL_CONSUMERTYPES]]></dynamic-content> </dynamic-element><dynamic-element name='LatestUpdate' type='text_box' index-type='text'><dynamic-content language-id='en_GB'><![CDATA[@CHANGE_PROPOSAL_LATEST_UPDATE]]></dynamic-content></dynamic-element><dynamic-element name='ChangeSummary' type='text_box' index-type='text'><dynamic-content language-id='en_GB'><![CDATA[@CHANGE_PROPOSAL_CHANGE_SUMMARY]]></dynamic-content></dynamic-element> <dynamic-element name='LinkToOriginalCP' type='text' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@CHANGE_PROPOSAL_LINK_TO_ORIGINAL_CP]]></dynamic-content></dynamic-element><dynamic-element name='InitialAssessment' type='selection_break' index-type='keyword'><dynamic-element name='ChangeRequirements' type='selection_break' index-type='keyword'><dynamic-element name='ProblemStatement' type='text_box' index-type='text'><dynamic-content language-id='en_GB'><![CDATA[@CHANGE_PROPOSAL_PROBLEM_STATEMENT]]></dynamic-content></dynamic-element><dynamic-element name='SolutionRequirements' type='text_box' index-type='text'><dynamic-content language-id='en_GB'><![CDATA[@CHANGE_PROPOSAL_SOLUTION_REQUIREMENTS]]></dynamic-content></dynamic-element><dynamic-element name='SolutionRequirements2' type='text_box' index-type='text'><dynamic-content language-id='en_GB'><![CDATA[@CHANGE_PROPOSAL_SOLUTION_REQUIREMENTS]]></dynamic-content></dynamic-element><dynamic-element name='SolutionRequirements3' type='text_box' index-type='text'><dynamic-content language-id='en_GB'><![CDATA[@CHANGE_PROPOSAL_SOLUTION_REQUIREMENTS]]></dynamic-content></dynamic-element><dynamic-element name='SolutionRequirements4' type='text_box' index-type='text'><dynamic-content language-id='en_GB'><![CDATA[@CHANGE_PROPOSAL_SOLUTION_REQUIREMENTS]]></dynamic-content></dynamic-element><dynamic-element name='SolutionRequirements5' type='text_box' index-type='text'><dynamic-content language-id='en_GB'><![CDATA[@CHANGE_PROPOSAL_SOLUTION_REQUIREMENTS]]></dynamic-content></dynamic-element></dynamic-element><dynamic-element name='ChangeProgresion' type='selection_break' index-type='keyword'><dynamic-element name='ChangeCategory' type='list' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@CHANGE_PROPOSAL_CHANGE_CATEGORY]]></dynamic-content></dynamic-element><dynamic-element name='ChangePath' type='list' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@CHANGE_PROPOSAL_CHANGE_PATH]]></dynamic-content></dynamic-element><dynamic-element name='ResponsibleCommittee' type='text' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@CHANGE_PROPOSAL_RESPONSIBLE_COMMITTEE]]></dynamic-content></dynamic-element><dynamic-element name='PriorityStatus' type='text' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@CHANGE_PROPOSAL_PRIORITY_STATUS]]></dynamic-content></dynamic-element><dynamic-element name='UrgentChange' type='list' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@CHANGE_PROPOSAL_URGENT_CHANGE]]></dynamic-content></dynamic-element></dynamic-element>@DOCUMENTS_DYNAMIC_ELEMENT<dynamic-element name='CP_InitialAssessmentReport' type='document_library' index-type='keyword'> <dynamic-element name='IARCommentsDeadline' type='ddm-date' index-type='keyword'> <dynamic-content language-id='en_GB'><![CDATA[]]></dynamic-content> </dynamic-element> <dynamic-content language-id='en_GB'><![CDATA[]]></dynamic-content></dynamic-element></dynamic-element><dynamic-element name='LeadChangeAnalyst' type='list' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@CHANGE_PROPOSAL_LEAD_CHANGE_ANALYST]]></dynamic-content></dynamic-element><dynamic-element name='LeadAssuranceAnalyst' type='list' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@CHANGE_PROPOSAL_LEAD_ASSURANCE_ANALYST]]></dynamic-content></dynamic-element><dynamic-element name='LeadTechnicalAnalyst' type='list' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@CHANGE_PROPOSAL_LEAD_TECHNICAL_ANALYST]]></dynamic-content></dynamic-element><dynamic-element name='PreliminaryChangeReport' type='document_library' index-type='keyword'><dynamic-element name='PCRCommentsDeadline' type='ddm-date' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@CHANGE_PROPOSAL_PCR_DEADLINE]]></dynamic-content></dynamic-element><dynamic-content language-id='en_GB'><![CDATA[@CHANGE_PROPOSAL_PCR]]></dynamic-content></dynamic-element><dynamic-element name='FinalChangeReport' type='document_library' index-type='keyword'><dynamic-element name='FCRCommentsDeadline' type='ddm-date' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@CHANGE_PROPOSAL_FCR_DEADLINE]]></dynamic-content></dynamic-element><dynamic-content language-id='en_GB'><![CDATA[@CHANGE_PROPOSAL_FCR]]></dynamic-content></dynamic-element><dynamic-element name='TitleSolutionOptions' type='text' index-type='keyword'><dynamic-element name='SolutionOptions' type='text_box' index-type='text'><dynamic-content language-id='en_GB'><![CDATA[@CHANGE_PROPOSAL_SOLUTION_OPTION]]></dynamic-content></dynamic-element><dynamic-element name='DocAndMedSoluction_option_doc_med' type='document_library' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[]]></dynamic-content></dynamic-element><dynamic-element name='Solution_option_link' type='text' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[]]></dynamic-content></dynamic-element><dynamic-content language-id='en_GB'><![CDATA[]]></dynamic-content></dynamic-element><dynamic-element name='CP_RELEASE_SCHEDULE' type='list' index-type='keyword'><dynamic-content language-id='en_GB'><![CDATA[@CHANGE_PROPOSAL_RELEASE_SCHEDULE]]></dynamic-content></dynamic-element></root>";

		try {

			DDMFormInstanceRecordVersion ddv = DDMFormInstanceRecordVersionLocalServiceUtil.getFormInstanceRecordVersion(classPK);

			/* create Article */
			ServiceContext serviceContext = new ServiceContext();

			Company company = CompanyLocalServiceUtil
					.getCompanyByWebId(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
			User user = UserLocalServiceUtil.getUserByEmailAddress(company.getCompanyId(), "test@liferay.com");
			long groupId = ddv.getGroupId();
			long folderId = getOrCreateFolder(serviceContext, user.getUserId(), groupId, CPROPOSAL_FOLDER_NAME);
			Map<String, String> formDataValuesMap = CProposalGetFormInfoUtil
					.getProposalManagementDetailsFromForm(classPK);

			logger.debug("formDataValuesMap: " + formDataValuesMap);

			parameters.put("user", user);
			parameters.put("groupId", groupId);
			parameters.put("title", formDataValuesMap.get("CPTitle"));
			parameters.put("folderId", folderId);

			parameters.put("CPRef", "CP" + classPK);
			parameters.put("CPTitle", formDataValuesMap.get("CPTitle"));
			parameters.put("CPStatus", StringPool.BLANK);
			parameters.put("CPProposerEmail", formDataValuesMap.get("CPProposerEmail"));
			parameters.put("CPLinkToCP", StringPool.BLANK);
			parameters.put("CPLinkToConsultation", StringPool.BLANK);
			parameters.put("CPLinkToAlternativeCP", StringPool.BLANK);
			parameters.put("CPImpactedSegmentsIS", Empty);
			parameters.put("CPPriorityScore", Empty);
			parameters.put("CPFuel", Empty);
			parameters.put("CPConsumerTypes", Empty);
			parameters.put("CPLatestUpdate", Empty);
			parameters.put("CPChangeSummary", Empty);
			parameters.put("CPRelatedLinkTitle", StringPool.BLANK);
			parameters.put("CPRelatedLink", StringPool.BLANK);

			parameters.put("CPInitialAssessmentPB", StringPool.TRUE);
			parameters.put("CPSolutionDevelopmentPB", StringPool.TRUE);
			parameters.put("CPServiceProviderImpactAssessmentPB", StringPool.TRUE);
			parameters.put("CPPartyImpactAssessmentPB", StringPool.TRUE);
			parameters.put("CPPreliminaryAssessmentPB", StringPool.TRUE);
			parameters.put("CPConsultationPB", StringPool.TRUE);
			parameters.put("CPFinalAssessmentPB", StringPool.TRUE);
			parameters.put("CPApprovedWaitingImplementationPB", StringPool.TRUE);
			parameters.put("CPRejectedPB", StringPool.TRUE);
			parameters.put("CPAwaitingAuthorityDecisionPB", StringPool.TRUE);
			parameters.put("CPWithdrawnPB", StringPool.TRUE);
			parameters.put("CPAppealInProgressPB", StringPool.TRUE);
			parameters.put("CPImplementedPB", StringPool.TRUE);
			parameters.put("CPNewPB", StringPool.TRUE);

			parameters.put("CPLinkToOriginalCP", StringPool.BLANK);
			parameters.put("CPProblemStatement", StringPool.BLANK);
			parameters.put("CPSolutionReq", StringPool.BLANK);
			parameters.put("CPChangeCategory", StringPool.BLANK);
			parameters.put("CPChangePath", StringPool.BLANK);
			parameters.put("CPResponsibleComittee", StringPool.BLANK);
			parameters.put("CPPriorityStatus", StringPool.BLANK);
			parameters.put("CPUrgentChange", StringPool.BLANK);
			if (Validator.isNotNull(formDataValuesMap.get("CPDocument"))) {
				parameters.put("CPDocument", formDataValuesMap.get("CPDocument"));
			} else {
				parameters.put("CPDocument", StringPool.BLANK);
			}
			parameters.put("CPLeadChangeAnalyst", StringPool.BLANK);
			parameters.put("CPLeadAssuranceAnalyst", StringPool.BLANK);
			parameters.put("CPLeaTechnicalAnalyst", StringPool.BLANK);
			parameters.put("CPPCRDeadline", StringPool.BLANK);
			parameters.put("CPPCR", StringPool.BLANK);
			parameters.put("CPFCRDeadline", StringPool.BLANK);
			parameters.put("CPFCR", StringPool.BLANK);
			parameters.put("CPSolutionOption", StringPool.BLANK);
			parameters.put("CPReleaseSchedule", StringPool.BLANK);
			parameters.put("CPIAR", StringPool.BLANK);
			parameters.put("CPIARDeadline", StringPool.BLANK);

			parameters.put("standardContent", standardContent);
			logger.info("3. parameters " + parameters);

			if (logger.isDebugEnabled()) {
				parameters.forEach((k, v) -> logger.debug(String.format("%-20s= %s", k, v)));
			}

			JournalArticle article = createArticle(parameters);

			createAlternativeRelationship(classPK, article);

			resourcePrimKey = article.getResourcePrimKey();

		} catch (PortalException e) {
			logger.error("Error creating journalArticle", e);
		}

		return resourcePrimKey;

	}

	private static JournalArticle createArticle(Map<String, Object> parameters) throws PortalException {
		logger.debug("createArticle method... ");
		User user = (User) parameters.get("user");
		long groupId = (long) parameters.get("groupId");
		String title = (String) parameters.get("title");
		long folderId = (long) parameters.get("folderId");

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
		JournalArticle article = null;
		Locale locale = LocaleUtil.getDefault();

		Map<Locale, String> titleMap = new HashMap<>();
		String nameContent = validTitleDescription(title);

		titleMap.put(locale, nameContent);
		logger.info("8. ddmListTemplate... " + ddmListTemplate);

		String content = replaceContentWithFields(parameters);

		logger.info("Content replaced according to form fields! " + content);

		/* create service context */
		ServiceContext serviceContext = new ServiceContext();
		try {
			serviceContext.setScopeGroupId(groupId);
			serviceContext.setAddGroupPermissions(true);
			serviceContext.setAddGuestPermissions(true);
			serviceContext.setWorkflowAction(1);

			article = JournalArticleLocalServiceUtil.addArticle(user.getUserId(), groupId, folderId, titleMap, null,
					content, structureKey, templateKey, serviceContext);

			logger.info("Journal Article created!");
		} catch (PortalException e1) {
			logger.error("Error creating journalArticle", e1);
		}
		return article;
	}

	private static void createAlternativeRelationship(long classPK, JournalArticle article) throws DocumentException {

		// ActionableDynamicQuery
		ActionableDynamicQuery adq = recFormArticleLocalServiceUtil.getActionableDynamicQuery();

		adq.setAddCriteriaMethod(new ActionableDynamicQuery.AddCriteriaMethod() {

			@Override
			public void addCriteria(DynamicQuery dynamicQuery) {
				dynamicQuery.add(PropertyFactoryUtil.forName("alternativeFormIds").like("%" + classPK + "%"));
			}

		});

		adq.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<recFormArticle>() {

			@Override
			public void performAction(recFormArticle originalRecFormArticle) throws PortalException {

				logger.debug("ActionableDynamicQuery: Executing ...");
				logger.debug("originalRecFormArticle: " + originalRecFormArticle);
				// if Alternative
				if (originalRecFormArticle != null) {
					try {
						recFormArticle original = originalRecFormArticle;
						long articleResourcePK = original.getArticleId();

						logger.debug("original: " + original);

						logger.debug("articleResourcePK: " + articleResourcePK);

						// Journal Original
						JournalArticle originalJA = JournalArticleLocalServiceUtil
								.fetchLatestArticle(articleResourcePK);
						String originalLink = "/group/guest/-/" + originalJA.getUrlTitle();

						logger.debug("originalJA: " + originalJA);
						logger.debug("originalLink: " + originalLink);

						// New journal article
						String alternativeLink = "/group/guest/-/" + article.getUrlTitle();
						logger.debug("alternativeLink: " + alternativeLink);

						Document documentAlternative = SAXReaderUtil.read(originalJA.getContent());
						String linkToAlternativeCP = "LinkToAlternativeCP";
						Node nodeLinkAlternative = documentAlternative.selectSingleNode(
								"//root//dynamic-element[@name='" + linkToAlternativeCP + "']//dynamic-content");

						logger.debug("nodeLinkAlternative: " + nodeLinkAlternative);
						nodeLinkAlternative.setText(alternativeLink);

						originalJA.setContent(documentAlternative.asXML());

						Document documentCP = SAXReaderUtil.read(article.getContent());
						String linkToCP = "LinkToOriginalCP";
						Node nodeLinkCP = documentCP
								.selectSingleNode("//root//dynamic-element[@name='" + linkToCP + "']//dynamic-content");

						logger.debug("nodeLinkCP: " + nodeLinkCP);
						nodeLinkCP.setText(originalLink);

						article.setContent(documentCP.asXML());

						JournalArticleLocalServiceUtil.updateJournalArticle(originalJA);
						JournalArticleLocalServiceUtil.updateJournalArticle(article);

						logger.debug("ActionableDynamicQuery: Alternative created OK");
					} catch (DocumentException e) {

						e.printStackTrace();
					}
				}

			}

		});

		try {
			adq.performActions();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("There was an error creating the Alternative");
		}

	}

	private static String validTitleDescription(String description) {
		if (Validator.isNotNull(description)) {
			description = description.replaceAll("\\$", Matcher.quoteReplacement("\\$"));
			description = description.replaceAll("&#039;", "'");
			description = description.replaceAll("&#034;", "\"");
			description = description.replaceAll("&amp;", "&");
		}
		return description;
	}

	private static String replaceContentWithFields(Map<String, Object> parameters) {

		logger.info("9. replaceContentWithFields... ");

		String CPRef = (String) parameters.get("CPRef");
		String CPTitle = (String) parameters.get("CPTitle");
		String CPStatus = (String) parameters.get("CPStatus");
		String CPProposerEmail = (String) parameters.get("CPProposerEmail");
		String CPLinkToCP = (String) parameters.get("CPLinkToCP");
		String CPLinkToConsultation = (String) parameters.get("CPLinkToConsultation");
		String CPLinkToAlternativeCP = (String) parameters.get("CPLinkToAlternativeCP");
		String CPImpactedSegmentsIS = (String) parameters.get("CPImpactedSegmentsIS");
		String CPPriorityScore = (String) parameters.get("CPPriorityScore");
		String CPFuel = (String) parameters.get("CPFuel");
		String CPConsumerTypes = (String) parameters.get("CPConsumerTypes");
		String CPLatestUpdate = (String) parameters.get("CPLatestUpdate");
		String CPChangeSummary = (String) parameters.get("CPChangeSummary");
		String CPRelatedLinkTitle = (String) parameters.get("CPRelatedLinkTitle");
		String CPRelatedLink = (String) parameters.get("CPRelatedLink");
		String CPInitialAssessmentPB = (String) parameters.get("CPInitialAssessmentPB");
		String CPSolutionDevelopmentPB = (String) parameters.get("CPSolutionDevelopmentPB");
		String CPServiceProviderImpactAssessmentPB = (String) parameters.get("CPServiceProviderImpactAssessmentPB");
		String CPPartyImpactAssessmentPB = (String) parameters.get("CPPartyImpactAssessmentPB");
		String CPPreliminaryAssessmentPB = (String) parameters.get("CPPreliminaryAssessmentPB");
		String CPConsultationPB = (String) parameters.get("CPConsultationPB");
		String CPFinalAssessmentPB = (String) parameters.get("CPFinalAssessmentPB");
		String CPApprovedWaitingImplementationPB = (String) parameters.get("CPApprovedWaitingImplementationPB");
		String CPRejectedPB = (String) parameters.get("CPRejectedPB");
		String CPAwaitingAuthorityDecisionPB = (String) parameters.get("CPAwaitingAuthorityDecisionPB");
		String CPWithdrawnPB = (String) parameters.get("CPWithdrawnPB");
		String CPAppealInProgressPB = (String) parameters.get("CPAppealInProgressPB");
		String CPImplementedPB = (String) parameters.get("CPImplementedPB");
		String CPNewPB = (String) parameters.get("CPNewPB");
		String CPLinkToOriginalCP = (String) parameters.get("CPLinkToOriginalCP");
		String CPProblemStatement = (String) parameters.get("CPProblemStatement");
		String CPSolutionReq = (String) parameters.get("CPSolutionReq");
		String CPChangeCategory = (String) parameters.get("CPChangeCategory");
		String CPChangePath = (String) parameters.get("CPChangePath");
		String CPResponsibleComittee = (String) parameters.get("CPResponsibleComittee");
		String CPPriorityStatus = (String) parameters.get("CPPriorityStatus");
		String CPUrgentChange = (String) parameters.get("CPUrgentChange");
		String CPDocument = (String) parameters.get("CPDocument");
		String CPLeadChangeAnalyst = (String) parameters.get("CPLeadChangeAnalyst");
		String CPLeadAssuranceAnalyst = (String) parameters.get("CPLeadAssuranceAnalyst");
		String CPLeaTechnicalAnalyst = (String) parameters.get("CPLeaTechnicalAnalyst");
		String CPPCRDeadline = (String) parameters.get("CPPCRDeadline");
		String CPPCR = (String) parameters.get("CPPCR");
		String CPFCRDeadline = (String) parameters.get("CPFCRDeadline");
		String CPFCR = (String) parameters.get("CPFCR");
		String CPSolutionOption = (String) parameters.get("CPSolutionOption");
		String CPReleaseSchedule = (String) parameters.get("CPReleaseSchedule");
		String CPIAR = (String) parameters.get("CPIAR");
		String CPIARDeadline = (String) parameters.get("CPIARDeadline");

		String finalContent = standardContent;

		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_REFERENCE, CPRef);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_TITLE, CPTitle);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_STATUS, CPStatus);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_EMAIL, CPProposerEmail);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_LINK_TO_CP_PAGE, CPLinkToCP);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_LINK_TO_CONSULTATION_PAGE, CPLinkToConsultation);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_LINK_TO_ALTERNATIVE_CP, CPLinkToAlternativeCP);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_IMPACTEDSEGMETNSIS, CPImpactedSegmentsIS);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_PRIORITYSCORE, CPPriorityScore);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_FUEL, CPFuel);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_CONSUMERTYPES, CPConsumerTypes);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_LATEST_UPDATE, CPLatestUpdate);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_CHANGE_SUMMARY, CPChangeSummary);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_RELATED_LINKTITLE, CPRelatedLinkTitle);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_RELATED_LINK, CPRelatedLink);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_INITIAL_ASSESSMENT_PB, CPInitialAssessmentPB);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_SOLUTION_DEVELOPMENT_PB, CPSolutionDevelopmentPB);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_SERVICE_PROVIDER_IMPACT_ASSESSMENT_PB, CPServiceProviderImpactAssessmentPB);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_PARTY_IMPACT_ASSESSMENT_PB, CPPartyImpactAssessmentPB);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_PRELIMINARY_ASSESSMENT_PB, CPPreliminaryAssessmentPB);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_CONSULTATION_PB, CPConsultationPB);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_FINAL_ASSESSMENT_PB, CPFinalAssessmentPB);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_APPROVED_WAITING_IMPLEMENTATION_PB, CPApprovedWaitingImplementationPB);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_REJECTED_PB, CPRejectedPB);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_AWAITING_AUTHORITY_DECISION_PB, CPAwaitingAuthorityDecisionPB);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_WITHDRAWN_PB, CPWithdrawnPB);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_APPEALINPROGRESS_PB, CPAppealInProgressPB);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_IMPLEMENTED_PB, CPImplementedPB);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_NEW_PB, CPNewPB);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_LINK_TO_ORIGINAL_CP, CPLinkToOriginalCP);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_PROBLEM_STATEMENT, CPProblemStatement);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_SOLUTION_REQUIREMENTS, CPSolutionReq);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_CHANGE_CATEGORY, CPChangeCategory);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_CHANGE_PATH, CPChangePath);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_RESPONSIBLE_COMMITTEE, CPResponsibleComittee);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_PRIORITY_STATUS, CPPriorityStatus);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_URGENT_CHANGE, CPUrgentChange);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_LEAD_CHANGE_ANALYST, CPLeadChangeAnalyst);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_LEAD_ASSURANCE_ANALYST, CPLeadAssuranceAnalyst);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_LEAD_TECHNICAL_ANALYST, CPLeaTechnicalAnalyst);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_PCR_DEADLINE, CPPCRDeadline);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_PCR, CPPCR);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_FCR_DEADLINE, CPFCRDeadline);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_FCR, CPFCR);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_SOLUTION_OPTION, CPSolutionOption);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_RELEASE_SCHEDULE, CPReleaseSchedule);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_IAR, CPIAR);
		finalContent = finalContent.replaceAll(CHANGE_PROPOSAL_IARDEADLINE, CPIARDeadline);

		finalContent = finalContent.replaceAll(DOCUMENTS_DYNAMIC_ELEMENT, createDocumentNodes(CPDocument));
		logger.debug("finalContent: " + finalContent);

		return finalContent;
	}

	/**
	 * Crate nodes for documents attached
	 * 
	 * @param CPDocument
	 * @return List of nodes (as String) to insert on the final xml
	 */
	private static String createDocumentNodes(String CPDocument) {
		String[] documentsAttached = CPDocument.split(";spt;");
		String documentNode = StringPool.BLANK;
		StringBuilder documentNodeListSB = new StringBuilder();

		for (String document : documentsAttached) {
			try {
				JSONObject documentJson = JSONFactoryUtil.createJSONObject(document);
				long fileEntryId = documentJson.getLong("fileEntryId");
				String title = documentJson.getString("title");
				JSONObject nodeValueJson = JSONFactoryUtil.createJSONObject();
				nodeValueJson.put("classPK", fileEntryId);
				nodeValueJson.put("groupId", documentJson.get("groupId"));
				nodeValueJson.put("title", title);
				nodeValueJson.put("type", documentJson.get("type"));
				nodeValueJson.put("uuid", documentJson.get("uuid"));

				documentNode = documentNodeTemplate.replaceAll(CHANGE_PROPOSAL_DOCUMENT_TITLE, title)
						.replaceAll(CHANGE_PROPOSAL_DOCUMENT, documentJson.toString());
				documentNodeListSB.append(documentNode);
			} catch (PortalException e) {
				e.printStackTrace();
			}
		}
		return documentNodeListSB.toString();
	}

	/**
	 * Obtains folderId or creates a new one
	 * 
	 * @param serviceContext ServiceContext
	 * @param userId         long
	 * @param folderName     String
	 * @return long
	 * @throws SystemException se
	 * @throws PortalException pe
	 */
	public static long getOrCreateFolder(ServiceContext serviceContext, long userId, long repositoryId,
			String folderName) throws PortalException, SystemException {
		logger.debug("getOrCreateFolder... ");

		final long parentFolderId = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;
		JournalFolder prev = null;

		prev = JournalFolderLocalServiceUtil.fetchFolder(repositoryId, parentFolderId, folderName);
		if (Validator.isNull(prev)) {
			logger.debug("Folder doesn't exist. Creating " + folderName);
			prev = JournalFolderLocalServiceUtil.addFolder(userId, repositoryId, parentFolderId, folderName, "",
					serviceContext);
		}
		return prev.getFolderId();
	}

}
