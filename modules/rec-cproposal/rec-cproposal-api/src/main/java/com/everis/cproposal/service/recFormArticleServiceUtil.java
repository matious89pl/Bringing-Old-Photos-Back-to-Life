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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for recFormArticle. This utility wraps
 * <code>com.everis.cproposal.service.impl.recFormArticleServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see recFormArticleService
 * @generated
 */
public class recFormArticleServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.everis.cproposal.service.impl.recFormArticleServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.json.JSONObject
			createAlternativeChangeProposalJournalArticle(
				long userId, long groupId, String articleId)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.xml.DocumentException {

		return getService().createAlternativeChangeProposalJournalArticle(
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
	public static com.liferay.portal.kernel.json.JSONObject createApplication(
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

		return getService().createApplication(
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

	public static com.liferay.portal.kernel.json.JSONObject
		createCommitteeActionLog(
			String title, String AL_ID, String AL_Description,
			String AL_Assignee, String AL_DueDate, String AL_NextUpdateDue,
			String AL_Status, String AL_CompletionDate, long groupId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		return getService().createCommitteeActionLog(
			title, AL_ID, AL_Description, AL_Assignee, AL_DueDate,
			AL_NextUpdateDue, AL_Status, AL_CompletionDate, groupId,
			serviceContext);
	}

	public static com.liferay.portal.kernel.json.JSONObject
		createCommitteeNominations(
			String TitleNomination, String DescriptionNomination,
			String ClosingDateNomination, String StatusNomination,
			String ReportVisibilityNomination, String DecidingVoteNomination,
			long groupId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		return getService().createCommitteeNominations(
			TitleNomination, DescriptionNomination, ClosingDateNomination,
			StatusNomination, ReportVisibilityNomination,
			DecidingVoteNomination, groupId, serviceContext);
	}

	public static com.liferay.portal.kernel.json.JSONObject
		createCommitteeVotingProcess(
			String title, String Vote_Description, String Vote_ClossingProcess,
			String Vote_Status, String Vote_ReportVisibility,
			String Vote_VotingVisibility, String Vote_DecidingVote,
			long groupId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		return getService().createCommitteeVotingProcess(
			title, Vote_Description, Vote_ClossingProcess, Vote_Status,
			Vote_ReportVisibility, Vote_VotingVisibility, Vote_DecidingVote,
			groupId, serviceContext);
	}

	public static com.liferay.portal.kernel.json.JSONObject createConsultation(
		String Title, String TextIDChangeProposal,
		String Consultation_TargetedAudience,
		String Consultation_ChooseQATemplate, String TextLinkChangeProposalID,
		String TextDocumentConsultation, String TextDate,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		return getService().createConsultation(
			Title, TextIDChangeProposal, Consultation_TargetedAudience,
			Consultation_ChooseQATemplate, TextLinkChangeProposalID,
			TextDocumentConsultation, TextDate, serviceContext);
	}

	public static com.liferay.portal.kernel.json.JSONObject createIA(
		String Title, String IA_CPID, String IA_Type,
		String IA_TargetedAudience, String IA_ChooseQATemplate,
		String IA_LinkToCPPage, String IA_Documents,
		String IA_ResponsesDeadline,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		return getService().createIA(
			Title, IA_CPID, IA_Type, IA_TargetedAudience, IA_ChooseQATemplate,
			IA_LinkToCPPage, IA_Documents, IA_ResponsesDeadline,
			serviceContext);
	}

	public static java.util.List<com.liferay.calendar.model.CalendarBooking>
		get_rec_calendar_events(
			long companyId, long userId, long startTime, long endTime,
			boolean isAllCommittees) {

		return getService().get_rec_calendar_events(
			companyId, userId, startTime, endTime, isAllCommittees);
	}

	public static java.util.List<com.liferay.calendar.model.CalendarBooking>
		get_rec_calendar_events_by_committees(
			long companyId, long userId, long startTime, long endTime,
			long groupId) {

		return getService().get_rec_calendar_events_by_committees(
			companyId, userId, startTime, endTime, groupId);
	}

	public static long get_Upcoming_events(
		long companyId, long groupId, String name) {

		return getService().get_Upcoming_events(companyId, groupId, name);
	}

	public static java.util.List<com.liferay.calendar.model.CalendarBooking>
		getAllCommitteeEvents(long initialTime, long finalTime) {

		return getService().getAllCommitteeEvents(initialTime, finalTime);
	}

	public static java.util.List<com.liferay.calendar.model.CalendarBooking>
		getCalendarBookingsREC(long calendarId, long startTime, long endTime) {

		return getService().getCalendarBookingsREC(
			calendarId, startTime, endTime);
	}

	public static com.liferay.portal.kernel.json.JSONObject
		getCPByReleaseSchedule() {

		return getService().getCPByReleaseSchedule();
	}

	public static java.util.Map<String, String> getCPFormDataMap(
		long forminstancerecordversionId) {

		return getService().getCPFormDataMap(forminstancerecordversionId);
	}

	public static java.util.Map
		<String, com.liferay.portal.kernel.json.JSONObject>
			getFormFieldNamesAndLabelByFormId(
				long forminstancerecordversionId) {

		return getService().getFormFieldNamesAndLabelByFormId(
			forminstancerecordversionId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static String getPortraitURL(
			String email, long companyId,
			com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPortraitURL(email, companyId, themeDisplay);
	}

	public static java.util.Map<String, String>
		getProposalManagementDetailsFromForm(long forminstancerecordversionId) {

		return getService().getProposalManagementDetailsFromForm(
			forminstancerecordversionId);
	}

	public static com.liferay.portal.kernel.json.JSONObject getRPADocument(
		long groupId, long folderId, String title,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		return getService().getRPADocument(
			groupId, folderId, title, serviceContext);
	}

	public static com.liferay.portal.kernel.json.JSONObject getRPADocumentList(
		long repositoryId, long folderId, String dataItemType,
		String performancePeriod, String modifiedSince,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		return getService().getRPADocumentList(
			repositoryId, folderId, dataItemType, performancePeriod,
			modifiedSince, serviceContext);
	}

	public static java.util.Map<String, String> newFieldsCPForm(
		long forminstancerecordversionId) {

		return getService().newFieldsCPForm(forminstancerecordversionId);
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
	public static com.liferay.portal.kernel.json.JSONObject updateApplication(
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

		return getService().updateApplication(
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

	public static void updateChangeProposalJournalArticle(
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

		getService().updateChangeProposalJournalArticle(
			fieldNameProblemStatement, fieldNameSolutionRequeriments,
			fieldNameSolutionRequeriments2, fieldNameSolutionRequeriments3,
			fieldNameSolutionRequeriments4, fieldNameSolutionRequeriments5,
			newTextProblemStatement, newTextSolutionRequeriments,
			newTextSolutionRequeriments2, newTextSolutionRequeriments3,
			newTextSolutionRequeriments4, newTextSolutionRequeriments5, groupId,
			articleId);
	}

	public static com.liferay.portal.kernel.json.JSONObject
		updateCommitteeActionLog(
			long actionLogResourcePK, String title, String AL_ID,
			String AL_Description, String AL_Assignee, String AL_DueDate,
			String AL_NextUpdateDue, String AL_Status,
			String AL_CompletionDate) {

		return getService().updateCommitteeActionLog(
			actionLogResourcePK, title, AL_ID, AL_Description, AL_Assignee,
			AL_DueDate, AL_NextUpdateDue, AL_Status, AL_CompletionDate);
	}

	public static com.liferay.portal.kernel.json.JSONObject
		updateCommitteeNominations(
			long nominationsResourcePK, String TitleNomination,
			String DescriptionNomination, String ClosingDateNomination,
			String StatusNomination, String ReportVisibilityNomination,
			String DecidingVoteNomination) {

		return getService().updateCommitteeNominations(
			nominationsResourcePK, TitleNomination, DescriptionNomination,
			ClosingDateNomination, StatusNomination, ReportVisibilityNomination,
			DecidingVoteNomination);
	}

	public static com.liferay.portal.kernel.json.JSONObject
		updateCommitteeVotingProcess(
			long votingResourcePK, String title, String Vote_Description,
			String Vote_ClossingProcess, String Vote_Status,
			String Vote_ReportVisibility, String Vote_VotingVisibility,
			String Vote_DecidingVote, String[] Vote_ExternalVote) {

		return getService().updateCommitteeVotingProcess(
			votingResourcePK, title, Vote_Description, Vote_ClossingProcess,
			Vote_Status, Vote_ReportVisibility, Vote_VotingVisibility,
			Vote_DecidingVote, Vote_ExternalVote);
	}

	public static com.liferay.portal.kernel.json.JSONObject UpdateConsultation(
		long appPK, String Title, String TextIDChangeProposal,
		String Consultation_TargetedAudience,
		String Consultation_ChooseQATemplate, String TextLinkChangeProposalID,
		String TextDocumentConsultation, String TextDate,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		return getService().UpdateConsultation(
			appPK, Title, TextIDChangeProposal, Consultation_TargetedAudience,
			Consultation_ChooseQATemplate, TextLinkChangeProposalID,
			TextDocumentConsultation, TextDate, serviceContext);
	}

	public static com.liferay.portal.kernel.json.JSONObject
		updateCPChangeSummary(
			String newChangeSummary, long groupId, String articleId) {

		return getService().updateCPChangeSummary(
			newChangeSummary, groupId, articleId);
	}

	public static void updateCPImpacts(long resourcePK, String impactList) {
		getService().updateCPImpacts(resourcePK, impactList);
	}

	public static com.liferay.portal.kernel.json.JSONObject
		updateCPLatestUpdate(
			String newLatestUpdate, long groupId, String articleId) {

		return getService().updateCPLatestUpdate(
			newLatestUpdate, groupId, articleId);
	}

	public static com.liferay.portal.kernel.json.JSONObject updateIA(
		long appResourcePK, String Title, String IA_CPID, String IA_Type,
		String IA_TargetedAudience, String IA_ChooseQATemplate,
		String IA_LinkToCPPage, String IA_Documents,
		String IA_ResponsesDeadline,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		return getService().updateIA(
			appResourcePK, Title, IA_CPID, IA_Type, IA_TargetedAudience,
			IA_ChooseQATemplate, IA_LinkToCPPage, IA_Documents,
			IA_ResponsesDeadline, serviceContext);
	}

	public static void updateImpactTabJournalArticle(
			String fieldNameIMP, String newTextIMP, long groupId,
			long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.xml.DocumentException {

		getService().updateImpactTabJournalArticle(
			fieldNameIMP, newTextIMP, groupId, resourcePrimKey);
	}

	public static com.liferay.portal.kernel.json.JSONObject uploadRPADocument(
		long repositoryId, long folderId, java.io.File file, String fileName,
		String processArea, String description, String fieldType, String date,
		String partyUploading, String dataItemType, String performancePeriod,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		return getService().uploadRPADocument(
			repositoryId, folderId, file, fileName, processArea, description,
			fieldType, date, partyUploading, dataItemType, performancePeriod,
			serviceContext);
	}

	public static com.liferay.portal.kernel.json.JSONObject
		uploadRPADocumentByBytes(
			long repositoryId, long folderId, byte[] fileBytes, String mimeType,
			String fileName, String processArea, String description,
			String fieldType, String date, String partyUploading,
			String dataItemType, String performancePeriod,
			com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		return getService().uploadRPADocumentByBytes(
			repositoryId, folderId, fileBytes, mimeType, fileName, processArea,
			description, fieldType, date, partyUploading, dataItemType,
			performancePeriod, serviceContext);
	}

	public static com.liferay.portal.kernel.json.JSONObject uploadYourFiles(
		long userId, long repositoryId, long folderId, byte[] fileBytes,
		String mimeType, String title, String processArea, String description,
		String fieldType, String date, String partyUploading,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		return getService().uploadYourFiles(
			userId, repositoryId, folderId, fileBytes, mimeType, title,
			processArea, description, fieldType, date, partyUploading,
			serviceContext);
	}

	public static com.liferay.portal.kernel.json.JSONObject
			uploadYourFilesFileEntryForConsultation(
				String title, long repositoryId, long folderId, long userId,
				byte[] fileBytes, String mimeType, String description,
				String date, String[] TargetedAudience,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().uploadYourFilesFileEntryForConsultation(
			title, repositoryId, folderId, userId, fileBytes, mimeType,
			description, date, TargetedAudience, serviceContext);
	}

	public static com.liferay.portal.kernel.json.JSONObject
		uploadYourFilesFileEntryForIA(
			String title, long repositoryId, long folderId, long userId,
			byte[] fileBytes, String mimeType, String description, String date,
			String[] TargetedAudience,
			com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		return getService().uploadYourFilesFileEntryForIA(
			title, repositoryId, folderId, userId, fileBytes, mimeType,
			description, date, TargetedAudience, serviceContext);
	}

	public static java.util.HashMap<String, String> webContentFieldValues(
			long groupId, String articleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().webContentFieldValues(groupId, articleId);
	}

	public static recFormArticleService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<recFormArticleService, recFormArticleService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(recFormArticleService.class);

		ServiceTracker<recFormArticleService, recFormArticleService>
			serviceTracker =
				new ServiceTracker
					<recFormArticleService, recFormArticleService>(
						bundle.getBundleContext(), recFormArticleService.class,
						null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}