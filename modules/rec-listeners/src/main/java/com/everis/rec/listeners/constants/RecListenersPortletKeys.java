package com.everis.rec.listeners.constants;

/**
 * @author lperezve
 */
public class RecListenersPortletKeys {

	public static final String RECLISTENERS = "com_everis_rec_listeners_RecListenersPortlet";

	/* STRUCTURES */
	public static final String CHANGE_PROPOSAL_PAGE = "CHANGE-PROPOSAL";

	public static final String CONSULTATION = "CONSULTATION";

	public static final String IMPACT_ASSESSMENT = "IMPACT-ASSESSMENT";

	public static final String PARTY_MANAGEMENT = "PARTY-MANAGEMENT";

	public static final String NEWS = "NEWS";

	public static final String CATEGORY_3 = "CATEGORY-3";

	public static final String NOMINATIONS = "NOMINATIONS-STR";

	public static final String COMMITTEE_ACTION_LOG = "COMMITTEE-ACTION-LOG";

	public static final String VOTING = "VOTING";

	public static final String[] STRUCTURE_LIST = { CHANGE_PROPOSAL_PAGE, CONSULTATION, IMPACT_ASSESSMENT,
			PARTY_MANAGEMENT, NEWS, CATEGORY_3, COMMITTEE_ACTION_LOG, NOMINATIONS, VOTING };

	/* FIELD NAMES - CONSULTATION PAGE STRUCTURE */
	public static final String CONSULTATION_RESPONSES_DEADLINE = "TextDate";

	public static final String CONSULTATION_ID_CP_FIELDNAME = "TextIDChangeProposal";

	public static final String CONSULTATION_TARGETEDAUDIENCE = "Consultation_TargetedAudience";

	public static final String CONSULTATION_CHOOSEQATEMPLATE = "Consultation_ChooseQATemplate";

	/* FIELD NAMES - CHANGE PROPOSAL PAGE STRUCTURE */

	public static final String CHANGE_PROPOSAL_REFERENCE_FIELDNAME = "ChangeProposalReference";

	public static final String CHANGE_PROPOSAL_TITLE = "ChangeProposalTitle";

	public static final String CHANGE_PROPOSAL_STATUS = "ChangeProposalStatus";

	public static final String PROPOSER_EMAIL_FIELDNAME = "ChangeProposerEmail";

	public static final String LINK_TO_CHANGE_PROPOSAL = "LinkToCPPage";

	public static final String LINK_TO_CONSULTATION_PAGE = "LinkToConsultationPage";

	public static final String LINK_TO_ALTERNATIVE_CHANGE_PROPOSAL = "LinkToAlternativeCP";

	public static final String LINK_TO_ORIGINAL_CHANGE_PROPOSAL = "LinkToOriginalCP";

	public static final String INITIAL_ASSESSMENT = "InitialAssessment";

	public static final String CHANGE_REQUERIMENTS = "ChangeRequirements";

	public static final String PROBLEM_STATEMENT = "ProblemStatement";

	public static final String SOLUTION_REQUERIMENTS = "SolutionRequirements";

	public static final String SOLUTION_REQUERIMENTS2 = "SolutionRequirements2";

	public static final String SOLUTION_REQUERIMENTS3 = "SolutionRequirements3";

	public static final String SOLUTION_REQUERIMENTS4 = "SolutionRequirements4";

	public static final String SOLUTION_REQUERIMENTS5 = "SolutionRequirements5";

	public static final String CHANGE_PROGRESSION = "ChangeProgresion";

	public static final String CHANGE_CATEGORY = "ChangeCategory";

	public static final String CHANGE_PATH = "ChangePath";

	public static final String RESPONSIBLE_COMITTEE = "ResponsibleCommittee";

	public static final String PRIORITY_STATUS = "PriorityStatus";

	public static final String URGENT_CHANGE = "UrgentChange";

	public static final String ATTACH_DOCUMENTS = "AttachDocuments";

	public static final String DOCUMENT_TITLE = "DocumentTitle";

	public static final String LEAD_CHANGE_ANALYST = "LeadChangeAnalyst";

	public static final String LEAD_ASSURANCE_ANALYST = "LeadAssuranceAnalyst";

	public static final String LEAD_TECHNICAL_ANAYLYST = "LeadTechnicalAnalyst";

	public static final String PRELIMINARY_CHANGE_REPORT_FIELDNAME = "PreliminaryChangeReport";

	public static final String FINAL_CHANGE_REPORT_FIELDNAME = "FinalChangeReport";

	public static final String PCR_COMMENTS_DEADLINE_FIELDNAME = "PCRCommentsDeadline";

	public static final String FCR_COMMENTS_DEADLINE_FIELDNAME = "FCRCommentsDeadline";

	public static final String TITLE_SOLUTION_OPTIONS = "TitleSolutionOptions";

	public static final String SOLUTION_OPTIONS = "SolutionOptions";

	public static final String DOC_AND_MED_SOLUTION_OPTION_DOC_MED = "DocAndMedSoluction_option_doc_med";

	public static final String CHANGE_PROPOSAL_RELEASE_SCHEDULE = "CP_RELEASE_SCHEDULE";

	public static final String INITIAL_ASSESSMENT_REPORT_FIELDNAME = "CP_InitialAssessmentReport";

	public static final String IAR_COMMENTS_DEADLINE_FIELDNAME = "IARCommentsDeadline";

	/* FIELD NAMES - IMPACT ASSESSMENT PAGE STRUCTURE */

	public static final String IMPACT_ASSESSMENT_ID = "IA_CPID";

	public static final String IA_TITLE = "IA_Title";

	public static final String IA_TYPE = "IA_Type";

	public static final String IA_TARGET_AUDIENCE = "IA_TargetedAudience";

	public static final String IA_RESPONSES_DEADLINE = "IA_ResponsesDeadline";

	public static final String IA_LINK_TO_CP_PAGE = "IA_LinkToCPPage";

	public static final String IA_PARTY_GENERAL_RESPONSE = "IA_PartyImpactAssessment_GeneralResponse";

	public static final String IA_COST_BENEFIT_ANALYSIS = "IA_CostBenefitAnalysis";

	public static final String IA_EFFORT_IMPACT_ANALYSIS = "IA_EffortImpactAnalysis";

	public static final String IA_DEPENDENCIES_RISK_ISSUES_ANY_OTHERS_IMPACTS = "IA_DependenciesRiskIssuesAnyOtherImpacts";

	public static final String IA_DOCUMENTS = "IA_Documents";

	public static final String IA_TARGETEDAUDIENCE = "IA_TargetedAudience";

	public static final String IA_CHOOSEQATEMPLATE = "IA_ChooseQATemplate";

	/* FIELD NAMES - COMMITTE ACTION LOG STRUCTURE */

	public static final String ID_ACTIONLOG = "ActionLog_ID";

	public static final String DESCRIPTION_ACTIONLOG = "ActionLog_Description";

	public static final String ASSIGNEES_ACTIONLOG = "ActionLog_AssigneeEmail";

	public static final String DUEDATE_ACTIONLOG = "ActionLog_DueDate";

	public static final String NEXTUPDATEDUE_ACTIONLOG = "ActionLog_NextUpdateDue";

	public static final String STATUS_ACTIONLOG = "ActionLog_Status";

	public static final String COMPLETIONDATE_ACTIONLOG = "ActionLog_CompletionDate";

	/* FIELD NAMES - VOTING STRUCTURE */

	public static final String TITLE_VOTING = "TitleVoting";

	public static final String DESCRIPTION_VOTING = "DescriptionVoting";

	public static final String DATE_VOTING = "DateVoting";

	public static final String STATUS_VOTING = "StatusVoting";

	public static final String REPORT_VISIBILITY = "ReportVisibilityVoting";

	public static final String DECIDING_VOTING = "DecidingVoting";

	public static final String EXTERNAL_VOTING = "ExternalVoting";

	/* FIELD NAMES - NOMINATION STRUCTURE */

	public static final String TITLE_NOMINATION = "TitleNomination";

	public static final String DESCRIPTION_NOMINATION = "DescriptionNomination";

	public static final String CLOSING_DATE_NOMINATION = "ClosingDateNomination";

	public static final String STATUS_NOMINATION = "StatusNomination";

	public static final String REPORT_NOMINATION = "ReportVisibilityNomination";

	public static final String DECIDING_NOMINATION = "DecidingVoteNomination";

}