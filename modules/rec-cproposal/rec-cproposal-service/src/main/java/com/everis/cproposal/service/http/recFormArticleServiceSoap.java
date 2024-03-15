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

package com.everis.cproposal.service.http;

import com.everis.cproposal.service.recFormArticleServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>recFormArticleServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>com.everis.cproposal.model.recFormArticleSoap</code>. If the method in the
 * service utility returns a
 * <code>com.everis.cproposal.model.recFormArticle</code>, that is translated to a
 * <code>com.everis.cproposal.model.recFormArticleSoap</code>. Methods that SOAP
 * cannot safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see recFormArticleServiceHttp
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class recFormArticleServiceSoap {

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
		throws RemoteException {

		try {
			recFormArticleServiceUtil.updateChangeProposalJournalArticle(
				fieldNameProblemStatement, fieldNameSolutionRequeriments,
				fieldNameSolutionRequeriments2, fieldNameSolutionRequeriments3,
				fieldNameSolutionRequeriments4, fieldNameSolutionRequeriments5,
				newTextProblemStatement, newTextSolutionRequeriments,
				newTextSolutionRequeriments2, newTextSolutionRequeriments3,
				newTextSolutionRequeriments4, newTextSolutionRequeriments5,
				groupId, articleId);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static String updateCPLatestUpdate(
			String newLatestUpdate, long groupId, String articleId)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				recFormArticleServiceUtil.updateCPLatestUpdate(
					newLatestUpdate, groupId, articleId);

			return returnValue.toString();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static String updateCPChangeSummary(
			String newChangeSummary, long groupId, String articleId)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				recFormArticleServiceUtil.updateCPChangeSummary(
					newChangeSummary, groupId, articleId);

			return returnValue.toString();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static void updateImpactTabJournalArticle(
			String fieldNameIMP, String newTextIMP, long groupId,
			long resourcePrimKey)
		throws RemoteException {

		try {
			recFormArticleServiceUtil.updateImpactTabJournalArticle(
				fieldNameIMP, newTextIMP, groupId, resourcePrimKey);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static String createAlternativeChangeProposalJournalArticle(
			long userId, long groupId, String articleId)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				recFormArticleServiceUtil.
					createAlternativeChangeProposalJournalArticle(
						userId, groupId, articleId);

			return returnValue.toString();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static java.util.HashMap<String, String> webContentFieldValues(
			long groupId, String articleId)
		throws RemoteException {

		try {
			java.util.HashMap<String, String> returnValue =
				recFormArticleServiceUtil.webContentFieldValues(
					groupId, articleId);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static String getCPByReleaseSchedule() throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				recFormArticleServiceUtil.getCPByReleaseSchedule();

			return returnValue.toString();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.calendar.model.CalendarBookingSoap[]
			getAllCommitteeEvents(long initialTime, long finalTime)
		throws RemoteException {

		try {
			java.util.List<com.liferay.calendar.model.CalendarBooking>
				returnValue = recFormArticleServiceUtil.getAllCommitteeEvents(
					initialTime, finalTime);

			return com.liferay.calendar.model.CalendarBookingSoap.toSoapModels(
				returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static long get_Upcoming_events(
			long companyId, long groupId, String name)
		throws RemoteException {

		try {
			long returnValue = recFormArticleServiceUtil.get_Upcoming_events(
				companyId, groupId, name);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static String getRPADocument(
			long groupId, long folderId, String title,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				recFormArticleServiceUtil.getRPADocument(
					groupId, folderId, title, serviceContext);

			return returnValue.toString();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static String getRPADocumentList(
			long repositoryId, long folderId, String dataItemType,
			String performancePeriod, String modifiedSince,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				recFormArticleServiceUtil.getRPADocumentList(
					repositoryId, folderId, dataItemType, performancePeriod,
					modifiedSince, serviceContext);

			return returnValue.toString();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static void updateCPImpacts(long resourcePK, String impactList)
		throws RemoteException {

		try {
			recFormArticleServiceUtil.updateCPImpacts(resourcePK, impactList);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.calendar.model.CalendarBookingSoap[]
			getCalendarBookingsREC(
				long calendarId, long startTime, long endTime)
		throws RemoteException {

		try {
			java.util.List<com.liferay.calendar.model.CalendarBooking>
				returnValue = recFormArticleServiceUtil.getCalendarBookingsREC(
					calendarId, startTime, endTime);

			return com.liferay.calendar.model.CalendarBookingSoap.toSoapModels(
				returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.calendar.model.CalendarBookingSoap[]
			get_rec_calendar_events(
				long companyId, long userId, long startTime, long endTime,
				boolean isAllCommittees)
		throws RemoteException {

		try {
			java.util.List<com.liferay.calendar.model.CalendarBooking>
				returnValue = recFormArticleServiceUtil.get_rec_calendar_events(
					companyId, userId, startTime, endTime, isAllCommittees);

			return com.liferay.calendar.model.CalendarBookingSoap.toSoapModels(
				returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.calendar.model.CalendarBookingSoap[]
			get_rec_calendar_events_by_committees(
				long companyId, long userId, long startTime, long endTime,
				long groupId)
		throws RemoteException {

		try {
			java.util.List<com.liferay.calendar.model.CalendarBooking>
				returnValue =
					recFormArticleServiceUtil.
						get_rec_calendar_events_by_committees(
							companyId, userId, startTime, endTime, groupId);

			return com.liferay.calendar.model.CalendarBookingSoap.toSoapModels(
				returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static String uploadYourFiles(
			long userId, long repositoryId, long folderId, byte[] fileBytes,
			String mimeType, String title, String processArea,
			String description, String fieldType, String date,
			String partyUploading,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				recFormArticleServiceUtil.uploadYourFiles(
					userId, repositoryId, folderId, fileBytes, mimeType, title,
					processArea, description, fieldType, date, partyUploading,
					serviceContext);

			return returnValue.toString();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static String uploadRPADocumentByBytes(
			long repositoryId, long folderId, byte[] fileBytes, String mimeType,
			String fileName, String processArea, String description,
			String fieldType, String date, String partyUploading,
			String dataItemType, String performancePeriod,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				recFormArticleServiceUtil.uploadRPADocumentByBytes(
					repositoryId, folderId, fileBytes, mimeType, fileName,
					processArea, description, fieldType, date, partyUploading,
					dataItemType, performancePeriod, serviceContext);

			return returnValue.toString();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static String uploadYourFilesFileEntryForIA(
			String title, long repositoryId, long folderId, long userId,
			byte[] fileBytes, String mimeType, String description, String date,
			String[] TargetedAudience,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				recFormArticleServiceUtil.uploadYourFilesFileEntryForIA(
					title, repositoryId, folderId, userId, fileBytes, mimeType,
					description, date, TargetedAudience, serviceContext);

			return returnValue.toString();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static String createIA(
			String Title, String IA_CPID, String IA_Type,
			String IA_TargetedAudience, String IA_ChooseQATemplate,
			String IA_LinkToCPPage, String IA_Documents,
			String IA_ResponsesDeadline,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				recFormArticleServiceUtil.createIA(
					Title, IA_CPID, IA_Type, IA_TargetedAudience,
					IA_ChooseQATemplate, IA_LinkToCPPage, IA_Documents,
					IA_ResponsesDeadline, serviceContext);

			return returnValue.toString();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static String updateIA(
			long appResourcePK, String Title, String IA_CPID, String IA_Type,
			String IA_TargetedAudience, String IA_ChooseQATemplate,
			String IA_LinkToCPPage, String IA_Documents,
			String IA_ResponsesDeadline,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				recFormArticleServiceUtil.updateIA(
					appResourcePK, Title, IA_CPID, IA_Type, IA_TargetedAudience,
					IA_ChooseQATemplate, IA_LinkToCPPage, IA_Documents,
					IA_ResponsesDeadline, serviceContext);

			return returnValue.toString();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static String uploadYourFilesFileEntryForConsultation(
			String title, long repositoryId, long folderId, long userId,
			byte[] fileBytes, String mimeType, String description, String date,
			String[] TargetedAudience,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				recFormArticleServiceUtil.
					uploadYourFilesFileEntryForConsultation(
						title, repositoryId, folderId, userId, fileBytes,
						mimeType, description, date, TargetedAudience,
						serviceContext);

			return returnValue.toString();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static String createConsultation(
			String Title, String TextIDChangeProposal,
			String Consultation_TargetedAudience,
			String Consultation_ChooseQATemplate,
			String TextLinkChangeProposalID, String TextDocumentConsultation,
			String TextDate,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				recFormArticleServiceUtil.createConsultation(
					Title, TextIDChangeProposal, Consultation_TargetedAudience,
					Consultation_ChooseQATemplate, TextLinkChangeProposalID,
					TextDocumentConsultation, TextDate, serviceContext);

			return returnValue.toString();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static String UpdateConsultation(
			long appPK, String Title, String TextIDChangeProposal,
			String Consultation_TargetedAudience,
			String Consultation_ChooseQATemplate,
			String TextLinkChangeProposalID, String TextDocumentConsultation,
			String TextDate,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				recFormArticleServiceUtil.UpdateConsultation(
					appPK, Title, TextIDChangeProposal,
					Consultation_TargetedAudience,
					Consultation_ChooseQATemplate, TextLinkChangeProposalID,
					TextDocumentConsultation, TextDate, serviceContext);

			return returnValue.toString();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
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
	public static String createApplication(
			String title, String PM_CompanyName,
			String IA_CompanyRegistrationNumber, String PM_RegisteredAddress,
			String PM_ReasonForSubmission, String PM_AuthorisingOfficerFullName,
			String PM_AuthorisingOfficer1Role,
			String PM_AuthorisingOfficer1EmailAddress,
			String PM_AuthorisingOfficer1BusinessAddress,
			String PM_AuthorisingOfficer1TelephoneNumber,
			String PM_AnyadditionalAuthorisingOfficer,
			String PM_KeyContact1Fullname, String PM_KeyContact1Role,
			String PM_KeyContact1EmailAddress,
			String PM_KeyContact1BusinessAddress,
			String PM_KeyContact1TelephoneNumber,
			String PM_Anyadditionalkeycontacts,
			String PM_Whatroleareyouapplyingfor,
			String PM_Whatsystemsareyouapplyingtoaccess,
			String PM_ProvideDetailsOrganisation, String PM_Status,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				recFormArticleServiceUtil.createApplication(
					title, PM_CompanyName, IA_CompanyRegistrationNumber,
					PM_RegisteredAddress, PM_ReasonForSubmission,
					PM_AuthorisingOfficerFullName, PM_AuthorisingOfficer1Role,
					PM_AuthorisingOfficer1EmailAddress,
					PM_AuthorisingOfficer1BusinessAddress,
					PM_AuthorisingOfficer1TelephoneNumber,
					PM_AnyadditionalAuthorisingOfficer, PM_KeyContact1Fullname,
					PM_KeyContact1Role, PM_KeyContact1EmailAddress,
					PM_KeyContact1BusinessAddress,
					PM_KeyContact1TelephoneNumber, PM_Anyadditionalkeycontacts,
					PM_Whatroleareyouapplyingfor,
					PM_Whatsystemsareyouapplyingtoaccess,
					PM_ProvideDetailsOrganisation, PM_Status, serviceContext);

			return returnValue.toString();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
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
	public static String updateApplication(
			long appResourcePK, String PM_CompanyName,
			String IA_CompanyRegistrationNumber, String PM_RegisteredAddress,
			String PM_ReasonForSubmission, String PM_AuthorisingOfficerFullName,
			String PM_AuthorisingOfficer1Role,
			String PM_AuthorisingOfficer1EmailAddress,
			String PM_AuthorisingOfficer1BusinessAddress,
			String PM_AuthorisingOfficer1TelephoneNumber,
			String PM_AnyadditionalAuthorisingOfficer,
			String PM_KeyContact1Fullname, String PM_KeyContact1Role,
			String PM_KeyContact1EmailAddress,
			String PM_KeyContact1BusinessAddress,
			String PM_KeyContact1TelephoneNumber,
			String PM_Anyadditionalkeycontacts,
			String PM_Whatroleareyouapplyingfor,
			String PM_Whatsystemsareyouapplyingtoaccess,
			String PM_ProvideDetailsOrganisation, String PM_Status,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				recFormArticleServiceUtil.updateApplication(
					appResourcePK, PM_CompanyName, IA_CompanyRegistrationNumber,
					PM_RegisteredAddress, PM_ReasonForSubmission,
					PM_AuthorisingOfficerFullName, PM_AuthorisingOfficer1Role,
					PM_AuthorisingOfficer1EmailAddress,
					PM_AuthorisingOfficer1BusinessAddress,
					PM_AuthorisingOfficer1TelephoneNumber,
					PM_AnyadditionalAuthorisingOfficer, PM_KeyContact1Fullname,
					PM_KeyContact1Role, PM_KeyContact1EmailAddress,
					PM_KeyContact1BusinessAddress,
					PM_KeyContact1TelephoneNumber, PM_Anyadditionalkeycontacts,
					PM_Whatroleareyouapplyingfor,
					PM_Whatsystemsareyouapplyingtoaccess,
					PM_ProvideDetailsOrganisation, PM_Status, serviceContext);

			return returnValue.toString();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static String createCommitteeActionLog(
			String title, String AL_ID, String AL_Description,
			String AL_Assignee, String AL_DueDate, String AL_NextUpdateDue,
			String AL_Status, String AL_CompletionDate, long groupId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				recFormArticleServiceUtil.createCommitteeActionLog(
					title, AL_ID, AL_Description, AL_Assignee, AL_DueDate,
					AL_NextUpdateDue, AL_Status, AL_CompletionDate, groupId,
					serviceContext);

			return returnValue.toString();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static String updateCommitteeActionLog(
			long actionLogResourcePK, String title, String AL_ID,
			String AL_Description, String AL_Assignee, String AL_DueDate,
			String AL_NextUpdateDue, String AL_Status, String AL_CompletionDate)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				recFormArticleServiceUtil.updateCommitteeActionLog(
					actionLogResourcePK, title, AL_ID, AL_Description,
					AL_Assignee, AL_DueDate, AL_NextUpdateDue, AL_Status,
					AL_CompletionDate);

			return returnValue.toString();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static String createCommitteeVotingProcess(
			String title, String Vote_Description, String Vote_ClossingProcess,
			String Vote_Status, String Vote_ReportVisibility,
			String Vote_VotingVisibility, String Vote_DecidingVote,
			long groupId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				recFormArticleServiceUtil.createCommitteeVotingProcess(
					title, Vote_Description, Vote_ClossingProcess, Vote_Status,
					Vote_ReportVisibility, Vote_VotingVisibility,
					Vote_DecidingVote, groupId, serviceContext);

			return returnValue.toString();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static String updateCommitteeVotingProcess(
			long votingResourcePK, String title, String Vote_Description,
			String Vote_ClossingProcess, String Vote_Status,
			String Vote_ReportVisibility, String Vote_VotingVisibility,
			String Vote_DecidingVote, String[] Vote_ExternalVote)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				recFormArticleServiceUtil.updateCommitteeVotingProcess(
					votingResourcePK, title, Vote_Description,
					Vote_ClossingProcess, Vote_Status, Vote_ReportVisibility,
					Vote_VotingVisibility, Vote_DecidingVote,
					Vote_ExternalVote);

			return returnValue.toString();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static String createCommitteeNominations(
			String TitleNomination, String DescriptionNomination,
			String ClosingDateNomination, String StatusNomination,
			String ReportVisibilityNomination, String DecidingVoteNomination,
			long groupId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				recFormArticleServiceUtil.createCommitteeNominations(
					TitleNomination, DescriptionNomination,
					ClosingDateNomination, StatusNomination,
					ReportVisibilityNomination, DecidingVoteNomination, groupId,
					serviceContext);

			return returnValue.toString();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static String updateCommitteeNominations(
			long nominationsResourcePK, String TitleNomination,
			String DescriptionNomination, String ClosingDateNomination,
			String StatusNomination, String ReportVisibilityNomination,
			String DecidingVoteNomination)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				recFormArticleServiceUtil.updateCommitteeNominations(
					nominationsResourcePK, TitleNomination,
					DescriptionNomination, ClosingDateNomination,
					StatusNomination, ReportVisibilityNomination,
					DecidingVoteNomination);

			return returnValue.toString();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		recFormArticleServiceSoap.class);

}