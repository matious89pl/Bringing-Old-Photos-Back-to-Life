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

package com.everis.cproposal.service;

import com.liferay.calendar.model.CalendarBooking;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.xml.DocumentException;

import java.io.File;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the remote service interface for recFormArticle. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see recFormArticleServiceUtil
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface recFormArticleService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.everis.cproposal.service.impl.recFormArticleServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the rec form article remote service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link recFormArticleServiceUtil} if injection and service tracking are not available.
	 */
	@JSONWebService(
		method = "POST", value = "Create Alternative Change Proposal"
	)
	public JSONObject createAlternativeChangeProposalJournalArticle(
			long userId, long groupId, String articleId)
		throws DocumentException, PortalException;

	/**
	 * @param title
	 * @param PM_CompanyName
	 * @param IA_CompanyRegistrationNumber
	 * @param PM_RegisteredAddress
	 * @param PM_ReasonForSubmission
	 * @param PM_AuthorisingOfficerFullName
	 * @param PM_AuthorisingOfficer1Role
	 * @param PM_AuthorisingOfficer1EmailAddress
	 * @param PM_AuthorisingOfficer1BusinessAddress
	 * @param PM_AuthorisingOfficer1TelephoneNumber
	 * @param PM_AnyadditionalAuthorisingOfficer
	 * @param PM_KeyContact1Fullname
	 * @param PM_KeyContact1Role
	 * @param PM_KeyContact1EmailAddress
	 * @param PM_KeyContact1BusinessAddress
	 * @param PM_KeyContact1TelephoneNumber
	 * @param PM_Anyadditionalkeycontacts
	 * @param PM_Whatroleareyouapplyingfor
	 * @param PM_Whatsystemsareyouapplyingtoaccess
	 * @param PM_ProvideDetailsOrganisation
	 * @param PM_Status
	 * @param serviceContext
	 */
	@JSONWebService(method = "POST", value = "create_application")
	public JSONObject createApplication(
		String title, String PM_CompanyName,
		String IA_CompanyRegistrationNumber, String PM_RegisteredAddress,
		String PM_ReasonForSubmission, String PM_AuthorisingOfficerFullName,
		String PM_AuthorisingOfficer1Role,
		String PM_AuthorisingOfficer1EmailAddress,
		String PM_AuthorisingOfficer1BusinessAddress,
		String PM_AuthorisingOfficer1TelephoneNumber,
		String PM_AnyadditionalAuthorisingOfficer,
		String PM_KeyContact1Fullname, String PM_KeyContact1Role,
		String PM_KeyContact1EmailAddress, String PM_KeyContact1BusinessAddress,
		String PM_KeyContact1TelephoneNumber,
		String PM_Anyadditionalkeycontacts, String PM_Whatroleareyouapplyingfor,
		String PM_Whatsystemsareyouapplyingtoaccess,
		String PM_ProvideDetailsOrganisation, String PM_Status,
		ServiceContext serviceContext);

	@JSONWebService(method = "POST", value = "create_committee_action_log")
	public JSONObject createCommitteeActionLog(
		String title, String AL_ID, String AL_Description, String AL_Assignee,
		String AL_DueDate, String AL_NextUpdateDue, String AL_Status,
		String AL_CompletionDate, long groupId, ServiceContext serviceContext);

	@JSONWebService(
		method = "POST", value = "create_committee_nominations_process"
	)
	public JSONObject createCommitteeNominations(
		String TitleNomination, String DescriptionNomination,
		String ClosingDateNomination, String StatusNomination,
		String ReportVisibilityNomination, String DecidingVoteNomination,
		long groupId, ServiceContext serviceContext);

	@JSONWebService(method = "POST", value = "create_committee_voting_process")
	public JSONObject createCommitteeVotingProcess(
		String title, String Vote_Description, String Vote_ClossingProcess,
		String Vote_Status, String Vote_ReportVisibility,
		String Vote_VotingVisibility, String Vote_DecidingVote, long groupId,
		ServiceContext serviceContext);

	@JSONWebService(method = "POST", value = "create_CONSULTATION")
	public JSONObject createConsultation(
		String Title, String TextIDChangeProposal,
		String Consultation_TargetedAudience,
		String Consultation_ChooseQATemplate, String TextLinkChangeProposalID,
		String TextDocumentConsultation, String TextDate,
		ServiceContext serviceContext);

	@JSONWebService(method = "POST", value = "create_IA")
	public JSONObject createIA(
		String Title, String IA_CPID, String IA_Type,
		String IA_TargetedAudience, String IA_ChooseQATemplate,
		String IA_LinkToCPPage, String IA_Documents,
		String IA_ResponsesDeadline, ServiceContext serviceContext);

	@AccessControlled(guestAccessEnabled = true)
	@JSONWebService(method = "GET", value = "get_rec_calendar_events")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CalendarBooking> get_rec_calendar_events(
		long companyId, long userId, long startTime, long endTime,
		boolean isAllCommittees);

	@AccessControlled(guestAccessEnabled = true)
	@JSONWebService(
		method = "GET", value = "get_rec_calendar_events_by_committee"
	)
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CalendarBooking> get_rec_calendar_events_by_committees(
		long companyId, long userId, long startTime, long endTime,
		long groupId);

	@JSONWebService(method = "GET", value = "get_calendar_id_UE")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long get_Upcoming_events(long companyId, long groupId, String name);

	@JSONWebService(method = "GET", value = "get_all_committee_events")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CalendarBooking> getAllCommitteeEvents(
		long initialTime, long finalTime);

	@AccessControlled(guestAccessEnabled = true)
	@JSONWebService(method = "GET", value = "getCalendarBookingsREC")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CalendarBooking> getCalendarBookingsREC(
		long calendarId, long startTime, long endTime);

	@JSONWebService(method = "POST", value = "get_cp_by_release_schedule")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getCPByReleaseSchedule();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Map<String, String> getCPFormDataMap(
		long forminstancerecordversionId);

	@JSONWebService(
		method = "GET", value = "get_Form_Field_Names_And_Label_By_Form_Id"
	)
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Map<String, JSONObject> getFormFieldNamesAndLabelByFormId(
		long forminstancerecordversionId);

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	@JSONWebService(method = "POST", value = "Get PortraitURL from user")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public String getPortraitURL(
			String email, long companyId, ThemeDisplay themeDisplay)
		throws PortalException;

	@JSONWebService(method = "GET", value = "get_proposal_management_details")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Map<String, String> getProposalManagementDetailsFromForm(
		long forminstancerecordversionId);

	@JSONWebService(method = "GET", value = "get_RPA_Document")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getRPADocument(
		long groupId, long folderId, String title,
		ServiceContext serviceContext);

	@JSONWebService(method = "GET", value = "get_RPA_Documents")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getRPADocumentList(
		long repositoryId, long folderId, String dataItemType,
		String performancePeriod, String modifiedSince,
		ServiceContext serviceContext);

	@JSONWebService(method = "POST", value = "new_fields_cp_form")
	public Map<String, String> newFieldsCPForm(
		long forminstancerecordversionId);

	/**
	 * @param appResourcePK
	 * @param PM_CompanyName
	 * @param IA_CompanyRegistrationNumber
	 * @param PM_RegisteredAddress
	 * @param PM_ReasonForSubmission
	 * @param PM_AuthorisingOfficerFullName
	 * @param PM_AuthorisingOfficer1Role
	 * @param PM_AuthorisingOfficer1EmailAddress
	 * @param PM_AuthorisingOfficer1BusinessAddress
	 * @param PM_AuthorisingOfficer1TelephoneNumber
	 * @param PM_AnyadditionalAuthorisingOfficer
	 * @param PM_KeyContact1Fullname
	 * @param PM_KeyContact1Role
	 * @param PM_KeyContact1EmailAddress
	 * @param PM_KeyContact1BusinessAddress
	 * @param PM_KeyContact1TelephoneNumber
	 * @param PM_Anyadditionalkeycontacts
	 * @param PM_Whatroleareyouapplyingfor
	 * @param PM_Whatsystemsareyouapplyingtoaccess
	 * @param PM_ProvideDetailsOrganisation
	 * @param PM_Status
	 * @param serviceContext
	 */
	@JSONWebService(method = "POST", value = "update_application")
	public JSONObject updateApplication(
		long appResourcePK, String PM_CompanyName,
		String IA_CompanyRegistrationNumber, String PM_RegisteredAddress,
		String PM_ReasonForSubmission, String PM_AuthorisingOfficerFullName,
		String PM_AuthorisingOfficer1Role,
		String PM_AuthorisingOfficer1EmailAddress,
		String PM_AuthorisingOfficer1BusinessAddress,
		String PM_AuthorisingOfficer1TelephoneNumber,
		String PM_AnyadditionalAuthorisingOfficer,
		String PM_KeyContact1Fullname, String PM_KeyContact1Role,
		String PM_KeyContact1EmailAddress, String PM_KeyContact1BusinessAddress,
		String PM_KeyContact1TelephoneNumber,
		String PM_Anyadditionalkeycontacts, String PM_Whatroleareyouapplyingfor,
		String PM_Whatsystemsareyouapplyingtoaccess,
		String PM_ProvideDetailsOrganisation, String PM_Status,
		ServiceContext serviceContext);

	public void updateChangeProposalJournalArticle(
			String fieldNameProblemStatement,
			String fieldNameSolutionRequeriments,
			String fieldNameSolutionRequeriments2,
			String fieldNameSolutionRequeriments3,
			String fieldNameSolutionRequeriments4,
			String fieldNameSolutionRequeriments5,
			String newTextProblemStatement, String newTextSolutionRequeriments,
			String newTextSolutionRequeriments2,
			String newTextSolutionRequeriments3,
			String newTextSolutionRequeriments4,
			String newTextSolutionRequeriments5, long groupId, String articleId)
		throws DocumentException, PortalException;

	@JSONWebService(method = "POST", value = "update_committee_action_log")
	public JSONObject updateCommitteeActionLog(
		long actionLogResourcePK, String title, String AL_ID,
		String AL_Description, String AL_Assignee, String AL_DueDate,
		String AL_NextUpdateDue, String AL_Status, String AL_CompletionDate);

	@JSONWebService(
		method = "POST", value = "update_committee_nominations_process"
	)
	public JSONObject updateCommitteeNominations(
		long nominationsResourcePK, String TitleNomination,
		String DescriptionNomination, String ClosingDateNomination,
		String StatusNomination, String ReportVisibilityNomination,
		String DecidingVoteNomination);

	@JSONWebService(method = "POST", value = "update_committee_voting_process")
	public JSONObject updateCommitteeVotingProcess(
		long votingResourcePK, String title, String Vote_Description,
		String Vote_ClossingProcess, String Vote_Status,
		String Vote_ReportVisibility, String Vote_VotingVisibility,
		String Vote_DecidingVote, String[] Vote_ExternalVote);

	@JSONWebService(method = "POST", value = "Update_CONSULTATION")
	public JSONObject UpdateConsultation(
		long appPK, String Title, String TextIDChangeProposal,
		String Consultation_TargetedAudience,
		String Consultation_ChooseQATemplate, String TextLinkChangeProposalID,
		String TextDocumentConsultation, String TextDate,
		ServiceContext serviceContext);

	@JSONWebService(method = "POST", value = "update_cp_change_summary")
	public JSONObject updateCPChangeSummary(
		String newChangeSummary, long groupId, String articleId);

	@JSONWebService(method = "POST", value = "update_CPImpacts")
	public void updateCPImpacts(long resourcePK, String impactList);

	@JSONWebService(method = "POST", value = "update_cp_latest_update")
	public JSONObject updateCPLatestUpdate(
		String newLatestUpdate, long groupId, String articleId);

	@JSONWebService(method = "POST", value = "update_IA")
	public JSONObject updateIA(
		long appResourcePK, String Title, String IA_CPID, String IA_Type,
		String IA_TargetedAudience, String IA_ChooseQATemplate,
		String IA_LinkToCPPage, String IA_Documents,
		String IA_ResponsesDeadline, ServiceContext serviceContext);

	public void updateImpactTabJournalArticle(
			String fieldNameIMP, String newTextIMP, long groupId,
			long resourcePrimKey)
		throws DocumentException, PortalException;

	@AccessControlled(guestAccessEnabled = true)
	@JSONWebService(method = "POST", value = "upload_RPA_Document")
	public JSONObject uploadRPADocument(
		long repositoryId, long folderId, File file, String fileName,
		String processArea, String description, String fieldType, String date,
		String partyUploading, String dataItemType, String performancePeriod,
		ServiceContext serviceContext);

	@AccessControlled(guestAccessEnabled = true)
	@JSONWebService(method = "POST", value = "upload_RPA_Document_by_bytes")
	public JSONObject uploadRPADocumentByBytes(
		long repositoryId, long folderId, byte[] fileBytes, String mimeType,
		String fileName, String processArea, String description,
		String fieldType, String date, String partyUploading,
		String dataItemType, String performancePeriod,
		ServiceContext serviceContext);

	@JSONWebService(method = "POST", value = "upload_your_files_RPA")
	public JSONObject uploadYourFiles(
		long userId, long repositoryId, long folderId, byte[] fileBytes,
		String mimeType, String title, String processArea, String description,
		String fieldType, String date, String partyUploading,
		ServiceContext serviceContext);

	@JSONWebService(method = "POST", value = "upload_FILE_CONSULTATION")
	public JSONObject uploadYourFilesFileEntryForConsultation(
			String title, long repositoryId, long folderId, long userId,
			byte[] fileBytes, String mimeType, String description, String date,
			String[] TargetedAudience, ServiceContext serviceContext)
		throws PortalException;

	@JSONWebService(method = "POST", value = "upload_FILE_IA")
	public JSONObject uploadYourFilesFileEntryForIA(
		String title, long repositoryId, long folderId, long userId,
		byte[] fileBytes, String mimeType, String description, String date,
		String[] TargetedAudience, ServiceContext serviceContext);

	public HashMap<String, String> webContentFieldValues(
			long groupId, String articleId)
		throws PortalException;

}