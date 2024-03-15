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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link recFormArticleService}.
 *
 * @author Brian Wing Shun Chan
 * @see recFormArticleService
 * @generated
 */
public class recFormArticleServiceWrapper
	implements recFormArticleService, ServiceWrapper<recFormArticleService> {

	public recFormArticleServiceWrapper(
		recFormArticleService recFormArticleService) {

		_recFormArticleService = recFormArticleService;
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject
			createAlternativeChangeProposalJournalArticle(
				long userId, long groupId, String articleId)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.xml.DocumentException {

		return _recFormArticleService.
			createAlternativeChangeProposalJournalArticle(
				userId, groupId, articleId);
	}

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
	@Override
	public com.liferay.portal.kernel.json.JSONObject createApplication(
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
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		return _recFormArticleService.createApplication(
			title, PM_CompanyName, IA_CompanyRegistrationNumber,
			PM_RegisteredAddress, PM_ReasonForSubmission,
			PM_AuthorisingOfficerFullName, PM_AuthorisingOfficer1Role,
			PM_AuthorisingOfficer1EmailAddress,
			PM_AuthorisingOfficer1BusinessAddress,
			PM_AuthorisingOfficer1TelephoneNumber,
			PM_AnyadditionalAuthorisingOfficer, PM_KeyContact1Fullname,
			PM_KeyContact1Role, PM_KeyContact1EmailAddress,
			PM_KeyContact1BusinessAddress, PM_KeyContact1TelephoneNumber,
			PM_Anyadditionalkeycontacts, PM_Whatroleareyouapplyingfor,
			PM_Whatsystemsareyouapplyingtoaccess, PM_ProvideDetailsOrganisation,
			PM_Status, serviceContext);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject createCommitteeActionLog(
		String title, String AL_ID, String AL_Description, String AL_Assignee,
		String AL_DueDate, String AL_NextUpdateDue, String AL_Status,
		String AL_CompletionDate, long groupId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		return _recFormArticleService.createCommitteeActionLog(
			title, AL_ID, AL_Description, AL_Assignee, AL_DueDate,
			AL_NextUpdateDue, AL_Status, AL_CompletionDate, groupId,
			serviceContext);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject createCommitteeNominations(
		String TitleNomination, String DescriptionNomination,
		String ClosingDateNomination, String StatusNomination,
		String ReportVisibilityNomination, String DecidingVoteNomination,
		long groupId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		return _recFormArticleService.createCommitteeNominations(
			TitleNomination, DescriptionNomination, ClosingDateNomination,
			StatusNomination, ReportVisibilityNomination,
			DecidingVoteNomination, groupId, serviceContext);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject
		createCommitteeVotingProcess(
			String title, String Vote_Description, String Vote_ClossingProcess,
			String Vote_Status, String Vote_ReportVisibility,
			String Vote_VotingVisibility, String Vote_DecidingVote,
			long groupId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		return _recFormArticleService.createCommitteeVotingProcess(
			title, Vote_Description, Vote_ClossingProcess, Vote_Status,
			Vote_ReportVisibility, Vote_VotingVisibility, Vote_DecidingVote,
			groupId, serviceContext);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject createConsultation(
		String Title, String TextIDChangeProposal,
		String Consultation_TargetedAudience,
		String Consultation_ChooseQATemplate, String TextLinkChangeProposalID,
		String TextDocumentConsultation, String TextDate,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		return _recFormArticleService.createConsultation(
			Title, TextIDChangeProposal, Consultation_TargetedAudience,
			Consultation_ChooseQATemplate, TextLinkChangeProposalID,
			TextDocumentConsultation, TextDate, serviceContext);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject createIA(
		String Title, String IA_CPID, String IA_Type,
		String IA_TargetedAudience, String IA_ChooseQATemplate,
		String IA_LinkToCPPage, String IA_Documents,
		String IA_ResponsesDeadline,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		return _recFormArticleService.createIA(
			Title, IA_CPID, IA_Type, IA_TargetedAudience, IA_ChooseQATemplate,
			IA_LinkToCPPage, IA_Documents, IA_ResponsesDeadline,
			serviceContext);
	}

	@Override
	public java.util.List<com.liferay.calendar.model.CalendarBooking>
		get_rec_calendar_events(
			long companyId, long userId, long startTime, long endTime,
			boolean isAllCommittees) {

		return _recFormArticleService.get_rec_calendar_events(
			companyId, userId, startTime, endTime, isAllCommittees);
	}

	@Override
	public java.util.List<com.liferay.calendar.model.CalendarBooking>
		get_rec_calendar_events_by_committees(
			long companyId, long userId, long startTime, long endTime,
			long groupId) {

		return _recFormArticleService.get_rec_calendar_events_by_committees(
			companyId, userId, startTime, endTime, groupId);
	}

	@Override
	public long get_Upcoming_events(long companyId, long groupId, String name) {
		return _recFormArticleService.get_Upcoming_events(
			companyId, groupId, name);
	}

	@Override
	public java.util.List<com.liferay.calendar.model.CalendarBooking>
		getAllCommitteeEvents(long initialTime, long finalTime) {

		return _recFormArticleService.getAllCommitteeEvents(
			initialTime, finalTime);
	}

	@Override
	public java.util.List<com.liferay.calendar.model.CalendarBooking>
		getCalendarBookingsREC(long calendarId, long startTime, long endTime) {

		return _recFormArticleService.getCalendarBookingsREC(
			calendarId, startTime, endTime);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getCPByReleaseSchedule() {
		return _recFormArticleService.getCPByReleaseSchedule();
	}

	@Override
	public java.util.Map<String, String> getCPFormDataMap(
		long forminstancerecordversionId) {

		return _recFormArticleService.getCPFormDataMap(
			forminstancerecordversionId);
	}

	@Override
	public java.util.Map<String, com.liferay.portal.kernel.json.JSONObject>
		getFormFieldNamesAndLabelByFormId(long forminstancerecordversionId) {

		return _recFormArticleService.getFormFieldNamesAndLabelByFormId(
			forminstancerecordversionId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _recFormArticleService.getOSGiServiceIdentifier();
	}

	@Override
	public String getPortraitURL(
			String email, long companyId,
			com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _recFormArticleService.getPortraitURL(
			email, companyId, themeDisplay);
	}

	@Override
	public java.util.Map<String, String> getProposalManagementDetailsFromForm(
		long forminstancerecordversionId) {

		return _recFormArticleService.getProposalManagementDetailsFromForm(
			forminstancerecordversionId);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getRPADocument(
		long groupId, long folderId, String title,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		return _recFormArticleService.getRPADocument(
			groupId, folderId, title, serviceContext);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getRPADocumentList(
		long repositoryId, long folderId, String dataItemType,
		String performancePeriod, String modifiedSince,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		return _recFormArticleService.getRPADocumentList(
			repositoryId, folderId, dataItemType, performancePeriod,
			modifiedSince, serviceContext);
	}

	@Override
	public java.util.Map<String, String> newFieldsCPForm(
		long forminstancerecordversionId) {

		return _recFormArticleService.newFieldsCPForm(
			forminstancerecordversionId);
	}

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
	@Override
	public com.liferay.portal.kernel.json.JSONObject updateApplication(
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
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		return _recFormArticleService.updateApplication(
			appResourcePK, PM_CompanyName, IA_CompanyRegistrationNumber,
			PM_RegisteredAddress, PM_ReasonForSubmission,
			PM_AuthorisingOfficerFullName, PM_AuthorisingOfficer1Role,
			PM_AuthorisingOfficer1EmailAddress,
			PM_AuthorisingOfficer1BusinessAddress,
			PM_AuthorisingOfficer1TelephoneNumber,
			PM_AnyadditionalAuthorisingOfficer, PM_KeyContact1Fullname,
			PM_KeyContact1Role, PM_KeyContact1EmailAddress,
			PM_KeyContact1BusinessAddress, PM_KeyContact1TelephoneNumber,
			PM_Anyadditionalkeycontacts, PM_Whatroleareyouapplyingfor,
			PM_Whatsystemsareyouapplyingtoaccess, PM_ProvideDetailsOrganisation,
			PM_Status, serviceContext);
	}

	@Override
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
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.xml.DocumentException {

		_recFormArticleService.updateChangeProposalJournalArticle(
			fieldNameProblemStatement, fieldNameSolutionRequeriments,
			fieldNameSolutionRequeriments2, fieldNameSolutionRequeriments3,
			fieldNameSolutionRequeriments4, fieldNameSolutionRequeriments5,
			newTextProblemStatement, newTextSolutionRequeriments,
			newTextSolutionRequeriments2, newTextSolutionRequeriments3,
			newTextSolutionRequeriments4, newTextSolutionRequeriments5, groupId,
			articleId);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject updateCommitteeActionLog(
		long actionLogResourcePK, String title, String AL_ID,
		String AL_Description, String AL_Assignee, String AL_DueDate,
		String AL_NextUpdateDue, String AL_Status, String AL_CompletionDate) {

		return _recFormArticleService.updateCommitteeActionLog(
			actionLogResourcePK, title, AL_ID, AL_Description, AL_Assignee,
			AL_DueDate, AL_NextUpdateDue, AL_Status, AL_CompletionDate);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject updateCommitteeNominations(
		long nominationsResourcePK, String TitleNomination,
		String DescriptionNomination, String ClosingDateNomination,
		String StatusNomination, String ReportVisibilityNomination,
		String DecidingVoteNomination) {

		return _recFormArticleService.updateCommitteeNominations(
			nominationsResourcePK, TitleNomination, DescriptionNomination,
			ClosingDateNomination, StatusNomination, ReportVisibilityNomination,
			DecidingVoteNomination);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject
		updateCommitteeVotingProcess(
			long votingResourcePK, String title, String Vote_Description,
			String Vote_ClossingProcess, String Vote_Status,
			String Vote_ReportVisibility, String Vote_VotingVisibility,
			String Vote_DecidingVote, String[] Vote_ExternalVote) {

		return _recFormArticleService.updateCommitteeVotingProcess(
			votingResourcePK, title, Vote_Description, Vote_ClossingProcess,
			Vote_Status, Vote_ReportVisibility, Vote_VotingVisibility,
			Vote_DecidingVote, Vote_ExternalVote);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject UpdateConsultation(
		long appPK, String Title, String TextIDChangeProposal,
		String Consultation_TargetedAudience,
		String Consultation_ChooseQATemplate, String TextLinkChangeProposalID,
		String TextDocumentConsultation, String TextDate,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		return _recFormArticleService.UpdateConsultation(
			appPK, Title, TextIDChangeProposal, Consultation_TargetedAudience,
			Consultation_ChooseQATemplate, TextLinkChangeProposalID,
			TextDocumentConsultation, TextDate, serviceContext);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject updateCPChangeSummary(
		String newChangeSummary, long groupId, String articleId) {

		return _recFormArticleService.updateCPChangeSummary(
			newChangeSummary, groupId, articleId);
	}

	@Override
	public void updateCPImpacts(long resourcePK, String impactList) {
		_recFormArticleService.updateCPImpacts(resourcePK, impactList);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject updateCPLatestUpdate(
		String newLatestUpdate, long groupId, String articleId) {

		return _recFormArticleService.updateCPLatestUpdate(
			newLatestUpdate, groupId, articleId);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject updateIA(
		long appResourcePK, String Title, String IA_CPID, String IA_Type,
		String IA_TargetedAudience, String IA_ChooseQATemplate,
		String IA_LinkToCPPage, String IA_Documents,
		String IA_ResponsesDeadline,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		return _recFormArticleService.updateIA(
			appResourcePK, Title, IA_CPID, IA_Type, IA_TargetedAudience,
			IA_ChooseQATemplate, IA_LinkToCPPage, IA_Documents,
			IA_ResponsesDeadline, serviceContext);
	}

	@Override
	public void updateImpactTabJournalArticle(
			String fieldNameIMP, String newTextIMP, long groupId,
			long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.xml.DocumentException {

		_recFormArticleService.updateImpactTabJournalArticle(
			fieldNameIMP, newTextIMP, groupId, resourcePrimKey);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject uploadRPADocument(
		long repositoryId, long folderId, java.io.File file, String fileName,
		String processArea, String description, String fieldType, String date,
		String partyUploading, String dataItemType, String performancePeriod,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		return _recFormArticleService.uploadRPADocument(
			repositoryId, folderId, file, fileName, processArea, description,
			fieldType, date, partyUploading, dataItemType, performancePeriod,
			serviceContext);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject uploadRPADocumentByBytes(
		long repositoryId, long folderId, byte[] fileBytes, String mimeType,
		String fileName, String processArea, String description,
		String fieldType, String date, String partyUploading,
		String dataItemType, String performancePeriod,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		return _recFormArticleService.uploadRPADocumentByBytes(
			repositoryId, folderId, fileBytes, mimeType, fileName, processArea,
			description, fieldType, date, partyUploading, dataItemType,
			performancePeriod, serviceContext);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject uploadYourFiles(
		long userId, long repositoryId, long folderId, byte[] fileBytes,
		String mimeType, String title, String processArea, String description,
		String fieldType, String date, String partyUploading,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		return _recFormArticleService.uploadYourFiles(
			userId, repositoryId, folderId, fileBytes, mimeType, title,
			processArea, description, fieldType, date, partyUploading,
			serviceContext);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject
			uploadYourFilesFileEntryForConsultation(
				String title, long repositoryId, long folderId, long userId,
				byte[] fileBytes, String mimeType, String description,
				String date, String[] TargetedAudience,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _recFormArticleService.uploadYourFilesFileEntryForConsultation(
			title, repositoryId, folderId, userId, fileBytes, mimeType,
			description, date, TargetedAudience, serviceContext);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject
		uploadYourFilesFileEntryForIA(
			String title, long repositoryId, long folderId, long userId,
			byte[] fileBytes, String mimeType, String description, String date,
			String[] TargetedAudience,
			com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		return _recFormArticleService.uploadYourFilesFileEntryForIA(
			title, repositoryId, folderId, userId, fileBytes, mimeType,
			description, date, TargetedAudience, serviceContext);
	}

	@Override
	public java.util.HashMap<String, String> webContentFieldValues(
			long groupId, String articleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _recFormArticleService.webContentFieldValues(groupId, articleId);
	}

	@Override
	public recFormArticleService getWrappedService() {
		return _recFormArticleService;
	}

	@Override
	public void setWrappedService(recFormArticleService recFormArticleService) {
		_recFormArticleService = recFormArticleService;
	}

	private recFormArticleService _recFormArticleService;

}