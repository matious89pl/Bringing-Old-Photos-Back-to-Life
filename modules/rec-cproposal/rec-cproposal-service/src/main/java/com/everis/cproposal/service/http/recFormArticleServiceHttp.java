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
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * <code>recFormArticleServiceUtil</code> service
 * utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * <code>HttpPrincipal</code> parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see recFormArticleServiceSoap
 * @generated
 */
public class recFormArticleServiceHttp {

	public static void updateChangeProposalJournalArticle(
			HttpPrincipal httpPrincipal, String fieldNameProblemStatement,
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

		try {
			MethodKey methodKey = new MethodKey(
				recFormArticleServiceUtil.class,
				"updateChangeProposalJournalArticle",
				_updateChangeProposalJournalArticleParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, fieldNameProblemStatement,
				fieldNameSolutionRequeriments, fieldNameSolutionRequeriments2,
				fieldNameSolutionRequeriments3, fieldNameSolutionRequeriments4,
				fieldNameSolutionRequeriments5, newTextProblemStatement,
				newTextSolutionRequeriments, newTextSolutionRequeriments2,
				newTextSolutionRequeriments3, newTextSolutionRequeriments4,
				newTextSolutionRequeriments5, groupId, articleId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				if (exception instanceof
						com.liferay.portal.kernel.xml.DocumentException) {

					throw (com.liferay.portal.kernel.xml.DocumentException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject
		updateCPLatestUpdate(
			HttpPrincipal httpPrincipal, String newLatestUpdate, long groupId,
			String articleId) {

		try {
			MethodKey methodKey = new MethodKey(
				recFormArticleServiceUtil.class, "updateCPLatestUpdate",
				_updateCPLatestUpdateParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, newLatestUpdate, groupId, articleId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject
		updateCPChangeSummary(
			HttpPrincipal httpPrincipal, String newChangeSummary, long groupId,
			String articleId) {

		try {
			MethodKey methodKey = new MethodKey(
				recFormArticleServiceUtil.class, "updateCPChangeSummary",
				_updateCPChangeSummaryParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, newChangeSummary, groupId, articleId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void updateImpactTabJournalArticle(
			HttpPrincipal httpPrincipal, String fieldNameIMP, String newTextIMP,
			long groupId, long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.xml.DocumentException {

		try {
			MethodKey methodKey = new MethodKey(
				recFormArticleServiceUtil.class,
				"updateImpactTabJournalArticle",
				_updateImpactTabJournalArticleParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, fieldNameIMP, newTextIMP, groupId, resourcePrimKey);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				if (exception instanceof
						com.liferay.portal.kernel.xml.DocumentException) {

					throw (com.liferay.portal.kernel.xml.DocumentException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static String getPortraitURL(
			HttpPrincipal httpPrincipal, String email, long companyId,
			com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				recFormArticleServiceUtil.class, "getPortraitURL",
				_getPortraitURLParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, email, companyId, themeDisplay);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (String)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject
			createAlternativeChangeProposalJournalArticle(
				HttpPrincipal httpPrincipal, long userId, long groupId,
				String articleId)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.xml.DocumentException {

		try {
			MethodKey methodKey = new MethodKey(
				recFormArticleServiceUtil.class,
				"createAlternativeChangeProposalJournalArticle",
				_createAlternativeChangeProposalJournalArticleParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userId, groupId, articleId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				if (exception instanceof
						com.liferay.portal.kernel.xml.DocumentException) {

					throw (com.liferay.portal.kernel.xml.DocumentException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.HashMap<String, String> webContentFieldValues(
			HttpPrincipal httpPrincipal, long groupId, String articleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				recFormArticleServiceUtil.class, "webContentFieldValues",
				_webContentFieldValuesParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, articleId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.HashMap<String, String>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject
		getCPByReleaseSchedule(HttpPrincipal httpPrincipal) {

		try {
			MethodKey methodKey = new MethodKey(
				recFormArticleServiceUtil.class, "getCPByReleaseSchedule",
				_getCPByReleaseScheduleParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(methodKey);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.Map
		<String, com.liferay.portal.kernel.json.JSONObject>
			getFormFieldNamesAndLabelByFormId(
				HttpPrincipal httpPrincipal, long forminstancerecordversionId) {

		try {
			MethodKey methodKey = new MethodKey(
				recFormArticleServiceUtil.class,
				"getFormFieldNamesAndLabelByFormId",
				_getFormFieldNamesAndLabelByFormIdParameterTypes10);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, forminstancerecordversionId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.Map
				<String, com.liferay.portal.kernel.json.JSONObject>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.Map<String, String> getCPFormDataMap(
		HttpPrincipal httpPrincipal, long forminstancerecordversionId) {

		try {
			MethodKey methodKey = new MethodKey(
				recFormArticleServiceUtil.class, "getCPFormDataMap",
				_getCPFormDataMapParameterTypes11);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, forminstancerecordversionId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.Map<String, String>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.Map<String, String>
		getProposalManagementDetailsFromForm(
			HttpPrincipal httpPrincipal, long forminstancerecordversionId) {

		try {
			MethodKey methodKey = new MethodKey(
				recFormArticleServiceUtil.class,
				"getProposalManagementDetailsFromForm",
				_getProposalManagementDetailsFromFormParameterTypes13);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, forminstancerecordversionId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.Map<String, String>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List<com.liferay.calendar.model.CalendarBooking>
		getAllCommitteeEvents(
			HttpPrincipal httpPrincipal, long initialTime, long finalTime) {

		try {
			MethodKey methodKey = new MethodKey(
				recFormArticleServiceUtil.class, "getAllCommitteeEvents",
				_getAllCommitteeEventsParameterTypes14);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, initialTime, finalTime);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List<com.liferay.calendar.model.CalendarBooking>)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static long get_Upcoming_events(
		HttpPrincipal httpPrincipal, long companyId, long groupId,
		String name) {

		try {
			MethodKey methodKey = new MethodKey(
				recFormArticleServiceUtil.class, "get_Upcoming_events",
				_get_Upcoming_eventsParameterTypes15);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, groupId, name);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return ((Long)returnObj).longValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject getRPADocument(
		HttpPrincipal httpPrincipal, long groupId, long folderId, String title,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		try {
			MethodKey methodKey = new MethodKey(
				recFormArticleServiceUtil.class, "getRPADocument",
				_getRPADocumentParameterTypes16);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, folderId, title, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject getRPADocumentList(
		HttpPrincipal httpPrincipal, long repositoryId, long folderId,
		String dataItemType, String performancePeriod, String modifiedSince,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		try {
			MethodKey methodKey = new MethodKey(
				recFormArticleServiceUtil.class, "getRPADocumentList",
				_getRPADocumentListParameterTypes17);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, repositoryId, folderId, dataItemType,
				performancePeriod, modifiedSince, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void updateCPImpacts(
		HttpPrincipal httpPrincipal, long resourcePK, String impactList) {

		try {
			MethodKey methodKey = new MethodKey(
				recFormArticleServiceUtil.class, "updateCPImpacts",
				_updateCPImpactsParameterTypes18);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, resourcePK, impactList);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List<com.liferay.calendar.model.CalendarBooking>
		getCalendarBookingsREC(
			HttpPrincipal httpPrincipal, long calendarId, long startTime,
			long endTime) {

		try {
			MethodKey methodKey = new MethodKey(
				recFormArticleServiceUtil.class, "getCalendarBookingsREC",
				_getCalendarBookingsRECParameterTypes19);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, calendarId, startTime, endTime);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List<com.liferay.calendar.model.CalendarBooking>)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List<com.liferay.calendar.model.CalendarBooking>
		get_rec_calendar_events(
			HttpPrincipal httpPrincipal, long companyId, long userId,
			long startTime, long endTime, boolean isAllCommittees) {

		try {
			MethodKey methodKey = new MethodKey(
				recFormArticleServiceUtil.class, "get_rec_calendar_events",
				_get_rec_calendar_eventsParameterTypes20);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, userId, startTime, endTime,
				isAllCommittees);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List<com.liferay.calendar.model.CalendarBooking>)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List<com.liferay.calendar.model.CalendarBooking>
		get_rec_calendar_events_by_committees(
			HttpPrincipal httpPrincipal, long companyId, long userId,
			long startTime, long endTime, long groupId) {

		try {
			MethodKey methodKey = new MethodKey(
				recFormArticleServiceUtil.class,
				"get_rec_calendar_events_by_committees",
				_get_rec_calendar_events_by_committeesParameterTypes21);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, userId, startTime, endTime, groupId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List<com.liferay.calendar.model.CalendarBooking>)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.Map<String, String> newFieldsCPForm(
		HttpPrincipal httpPrincipal, long forminstancerecordversionId) {

		try {
			MethodKey methodKey = new MethodKey(
				recFormArticleServiceUtil.class, "newFieldsCPForm",
				_newFieldsCPFormParameterTypes22);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, forminstancerecordversionId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.Map<String, String>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject uploadYourFiles(
		HttpPrincipal httpPrincipal, long userId, long repositoryId,
		long folderId, byte[] fileBytes, String mimeType, String title,
		String processArea, String description, String fieldType, String date,
		String partyUploading,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		try {
			MethodKey methodKey = new MethodKey(
				recFormArticleServiceUtil.class, "uploadYourFiles",
				_uploadYourFilesParameterTypes23);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userId, repositoryId, folderId, fileBytes, mimeType,
				title, processArea, description, fieldType, date,
				partyUploading, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject uploadRPADocument(
		HttpPrincipal httpPrincipal, long repositoryId, long folderId,
		java.io.File file, String fileName, String processArea,
		String description, String fieldType, String date,
		String partyUploading, String dataItemType, String performancePeriod,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		try {
			MethodKey methodKey = new MethodKey(
				recFormArticleServiceUtil.class, "uploadRPADocument",
				_uploadRPADocumentParameterTypes24);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, repositoryId, folderId, file, fileName, processArea,
				description, fieldType, date, partyUploading, dataItemType,
				performancePeriod, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject
		uploadRPADocumentByBytes(
			HttpPrincipal httpPrincipal, long repositoryId, long folderId,
			byte[] fileBytes, String mimeType, String fileName,
			String processArea, String description, String fieldType,
			String date, String partyUploading, String dataItemType,
			String performancePeriod,
			com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		try {
			MethodKey methodKey = new MethodKey(
				recFormArticleServiceUtil.class, "uploadRPADocumentByBytes",
				_uploadRPADocumentByBytesParameterTypes25);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, repositoryId, folderId, fileBytes, mimeType,
				fileName, processArea, description, fieldType, date,
				partyUploading, dataItemType, performancePeriod,
				serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject
		uploadYourFilesFileEntryForIA(
			HttpPrincipal httpPrincipal, String title, long repositoryId,
			long folderId, long userId, byte[] fileBytes, String mimeType,
			String description, String date, String[] TargetedAudience,
			com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		try {
			MethodKey methodKey = new MethodKey(
				recFormArticleServiceUtil.class,
				"uploadYourFilesFileEntryForIA",
				_uploadYourFilesFileEntryForIAParameterTypes26);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, title, repositoryId, folderId, userId, fileBytes,
				mimeType, description, date, TargetedAudience, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject createIA(
		HttpPrincipal httpPrincipal, String Title, String IA_CPID,
		String IA_Type, String IA_TargetedAudience, String IA_ChooseQATemplate,
		String IA_LinkToCPPage, String IA_Documents,
		String IA_ResponsesDeadline,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		try {
			MethodKey methodKey = new MethodKey(
				recFormArticleServiceUtil.class, "createIA",
				_createIAParameterTypes27);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, Title, IA_CPID, IA_Type, IA_TargetedAudience,
				IA_ChooseQATemplate, IA_LinkToCPPage, IA_Documents,
				IA_ResponsesDeadline, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject updateIA(
		HttpPrincipal httpPrincipal, long appResourcePK, String Title,
		String IA_CPID, String IA_Type, String IA_TargetedAudience,
		String IA_ChooseQATemplate, String IA_LinkToCPPage, String IA_Documents,
		String IA_ResponsesDeadline,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		try {
			MethodKey methodKey = new MethodKey(
				recFormArticleServiceUtil.class, "updateIA",
				_updateIAParameterTypes28);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, appResourcePK, Title, IA_CPID, IA_Type,
				IA_TargetedAudience, IA_ChooseQATemplate, IA_LinkToCPPage,
				IA_Documents, IA_ResponsesDeadline, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject
			uploadYourFilesFileEntryForConsultation(
				HttpPrincipal httpPrincipal, String title, long repositoryId,
				long folderId, long userId, byte[] fileBytes, String mimeType,
				String description, String date, String[] TargetedAudience,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				recFormArticleServiceUtil.class,
				"uploadYourFilesFileEntryForConsultation",
				_uploadYourFilesFileEntryForConsultationParameterTypes29);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, title, repositoryId, folderId, userId, fileBytes,
				mimeType, description, date, TargetedAudience, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject createConsultation(
		HttpPrincipal httpPrincipal, String Title, String TextIDChangeProposal,
		String Consultation_TargetedAudience,
		String Consultation_ChooseQATemplate, String TextLinkChangeProposalID,
		String TextDocumentConsultation, String TextDate,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		try {
			MethodKey methodKey = new MethodKey(
				recFormArticleServiceUtil.class, "createConsultation",
				_createConsultationParameterTypes30);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, Title, TextIDChangeProposal,
				Consultation_TargetedAudience, Consultation_ChooseQATemplate,
				TextLinkChangeProposalID, TextDocumentConsultation, TextDate,
				serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject UpdateConsultation(
		HttpPrincipal httpPrincipal, long appPK, String Title,
		String TextIDChangeProposal, String Consultation_TargetedAudience,
		String Consultation_ChooseQATemplate, String TextLinkChangeProposalID,
		String TextDocumentConsultation, String TextDate,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		try {
			MethodKey methodKey = new MethodKey(
				recFormArticleServiceUtil.class, "UpdateConsultation",
				_UpdateConsultationParameterTypes31);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, appPK, Title, TextIDChangeProposal,
				Consultation_TargetedAudience, Consultation_ChooseQATemplate,
				TextLinkChangeProposalID, TextDocumentConsultation, TextDate,
				serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject createApplication(
		HttpPrincipal httpPrincipal, String title, String PM_CompanyName,
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

		try {
			MethodKey methodKey = new MethodKey(
				recFormArticleServiceUtil.class, "createApplication",
				_createApplicationParameterTypes32);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, title, PM_CompanyName, IA_CompanyRegistrationNumber,
				PM_RegisteredAddress, PM_ReasonForSubmission,
				PM_AuthorisingOfficerFullName, PM_AuthorisingOfficer1Role,
				PM_AuthorisingOfficer1EmailAddress,
				PM_AuthorisingOfficer1BusinessAddress,
				PM_AuthorisingOfficer1TelephoneNumber,
				PM_AnyadditionalAuthorisingOfficer, PM_KeyContact1Fullname,
				PM_KeyContact1Role, PM_KeyContact1EmailAddress,
				PM_KeyContact1BusinessAddress, PM_KeyContact1TelephoneNumber,
				PM_Anyadditionalkeycontacts, PM_Whatroleareyouapplyingfor,
				PM_Whatsystemsareyouapplyingtoaccess,
				PM_ProvideDetailsOrganisation, PM_Status, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject updateApplication(
		HttpPrincipal httpPrincipal, long appResourcePK, String PM_CompanyName,
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

		try {
			MethodKey methodKey = new MethodKey(
				recFormArticleServiceUtil.class, "updateApplication",
				_updateApplicationParameterTypes33);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, appResourcePK, PM_CompanyName,
				IA_CompanyRegistrationNumber, PM_RegisteredAddress,
				PM_ReasonForSubmission, PM_AuthorisingOfficerFullName,
				PM_AuthorisingOfficer1Role, PM_AuthorisingOfficer1EmailAddress,
				PM_AuthorisingOfficer1BusinessAddress,
				PM_AuthorisingOfficer1TelephoneNumber,
				PM_AnyadditionalAuthorisingOfficer, PM_KeyContact1Fullname,
				PM_KeyContact1Role, PM_KeyContact1EmailAddress,
				PM_KeyContact1BusinessAddress, PM_KeyContact1TelephoneNumber,
				PM_Anyadditionalkeycontacts, PM_Whatroleareyouapplyingfor,
				PM_Whatsystemsareyouapplyingtoaccess,
				PM_ProvideDetailsOrganisation, PM_Status, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject
		createCommitteeActionLog(
			HttpPrincipal httpPrincipal, String title, String AL_ID,
			String AL_Description, String AL_Assignee, String AL_DueDate,
			String AL_NextUpdateDue, String AL_Status, String AL_CompletionDate,
			long groupId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		try {
			MethodKey methodKey = new MethodKey(
				recFormArticleServiceUtil.class, "createCommitteeActionLog",
				_createCommitteeActionLogParameterTypes34);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, title, AL_ID, AL_Description, AL_Assignee,
				AL_DueDate, AL_NextUpdateDue, AL_Status, AL_CompletionDate,
				groupId, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject
		updateCommitteeActionLog(
			HttpPrincipal httpPrincipal, long actionLogResourcePK, String title,
			String AL_ID, String AL_Description, String AL_Assignee,
			String AL_DueDate, String AL_NextUpdateDue, String AL_Status,
			String AL_CompletionDate) {

		try {
			MethodKey methodKey = new MethodKey(
				recFormArticleServiceUtil.class, "updateCommitteeActionLog",
				_updateCommitteeActionLogParameterTypes35);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, actionLogResourcePK, title, AL_ID, AL_Description,
				AL_Assignee, AL_DueDate, AL_NextUpdateDue, AL_Status,
				AL_CompletionDate);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject
		createCommitteeVotingProcess(
			HttpPrincipal httpPrincipal, String title, String Vote_Description,
			String Vote_ClossingProcess, String Vote_Status,
			String Vote_ReportVisibility, String Vote_VotingVisibility,
			String Vote_DecidingVote, long groupId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		try {
			MethodKey methodKey = new MethodKey(
				recFormArticleServiceUtil.class, "createCommitteeVotingProcess",
				_createCommitteeVotingProcessParameterTypes36);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, title, Vote_Description, Vote_ClossingProcess,
				Vote_Status, Vote_ReportVisibility, Vote_VotingVisibility,
				Vote_DecidingVote, groupId, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject
		updateCommitteeVotingProcess(
			HttpPrincipal httpPrincipal, long votingResourcePK, String title,
			String Vote_Description, String Vote_ClossingProcess,
			String Vote_Status, String Vote_ReportVisibility,
			String Vote_VotingVisibility, String Vote_DecidingVote,
			String[] Vote_ExternalVote) {

		try {
			MethodKey methodKey = new MethodKey(
				recFormArticleServiceUtil.class, "updateCommitteeVotingProcess",
				_updateCommitteeVotingProcessParameterTypes37);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, votingResourcePK, title, Vote_Description,
				Vote_ClossingProcess, Vote_Status, Vote_ReportVisibility,
				Vote_VotingVisibility, Vote_DecidingVote, Vote_ExternalVote);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject
		createCommitteeNominations(
			HttpPrincipal httpPrincipal, String TitleNomination,
			String DescriptionNomination, String ClosingDateNomination,
			String StatusNomination, String ReportVisibilityNomination,
			String DecidingVoteNomination, long groupId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		try {
			MethodKey methodKey = new MethodKey(
				recFormArticleServiceUtil.class, "createCommitteeNominations",
				_createCommitteeNominationsParameterTypes38);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, TitleNomination, DescriptionNomination,
				ClosingDateNomination, StatusNomination,
				ReportVisibilityNomination, DecidingVoteNomination, groupId,
				serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject
		updateCommitteeNominations(
			HttpPrincipal httpPrincipal, long nominationsResourcePK,
			String TitleNomination, String DescriptionNomination,
			String ClosingDateNomination, String StatusNomination,
			String ReportVisibilityNomination, String DecidingVoteNomination) {

		try {
			MethodKey methodKey = new MethodKey(
				recFormArticleServiceUtil.class, "updateCommitteeNominations",
				_updateCommitteeNominationsParameterTypes39);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, nominationsResourcePK, TitleNomination,
				DescriptionNomination, ClosingDateNomination, StatusNomination,
				ReportVisibilityNomination, DecidingVoteNomination);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		recFormArticleServiceHttp.class);

	private static final Class<?>[]
		_updateChangeProposalJournalArticleParameterTypes0 = new Class[] {
			String.class, String.class, String.class, String.class,
			String.class, String.class, String.class, String.class,
			String.class, String.class, String.class, String.class, long.class,
			String.class
		};
	private static final Class<?>[] _updateCPLatestUpdateParameterTypes1 =
		new Class[] {String.class, long.class, String.class};
	private static final Class<?>[] _updateCPChangeSummaryParameterTypes2 =
		new Class[] {String.class, long.class, String.class};
	private static final Class<?>[]
		_updateImpactTabJournalArticleParameterTypes3 = new Class[] {
			String.class, String.class, long.class, long.class
		};
	private static final Class<?>[] _getPortraitURLParameterTypes4 =
		new Class[] {
			String.class, long.class,
			com.liferay.portal.kernel.theme.ThemeDisplay.class
		};
	private static final Class<?>[]
		_createAlternativeChangeProposalJournalArticleParameterTypes5 =
			new Class[] {long.class, long.class, String.class};
	private static final Class<?>[] _webContentFieldValuesParameterTypes6 =
		new Class[] {long.class, String.class};
	private static final Class<?>[] _getCPByReleaseScheduleParameterTypes7 =
		new Class[] {};
	private static final Class<?>[]
		_getFormFieldNamesAndLabelByFormIdParameterTypes10 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getCPFormDataMapParameterTypes11 =
		new Class[] {long.class};
	private static final Class<?>[]
		_getProposalManagementDetailsFromFormParameterTypes13 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getAllCommitteeEventsParameterTypes14 =
		new Class[] {long.class, long.class};
	private static final Class<?>[] _get_Upcoming_eventsParameterTypes15 =
		new Class[] {long.class, long.class, String.class};
	private static final Class<?>[] _getRPADocumentParameterTypes16 =
		new Class[] {
			long.class, long.class, String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _getRPADocumentListParameterTypes17 =
		new Class[] {
			long.class, long.class, String.class, String.class, String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _updateCPImpactsParameterTypes18 =
		new Class[] {long.class, String.class};
	private static final Class<?>[] _getCalendarBookingsRECParameterTypes19 =
		new Class[] {long.class, long.class, long.class};
	private static final Class<?>[] _get_rec_calendar_eventsParameterTypes20 =
		new Class[] {
			long.class, long.class, long.class, long.class, boolean.class
		};
	private static final Class<?>[]
		_get_rec_calendar_events_by_committeesParameterTypes21 = new Class[] {
			long.class, long.class, long.class, long.class, long.class
		};
	private static final Class<?>[] _newFieldsCPFormParameterTypes22 =
		new Class[] {long.class};
	private static final Class<?>[] _uploadYourFilesParameterTypes23 =
		new Class[] {
			long.class, long.class, long.class, byte[].class, String.class,
			String.class, String.class, String.class, String.class,
			String.class, String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _uploadRPADocumentParameterTypes24 =
		new Class[] {
			long.class, long.class, java.io.File.class, String.class,
			String.class, String.class, String.class, String.class,
			String.class, String.class, String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _uploadRPADocumentByBytesParameterTypes25 =
		new Class[] {
			long.class, long.class, byte[].class, String.class, String.class,
			String.class, String.class, String.class, String.class,
			String.class, String.class, String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[]
		_uploadYourFilesFileEntryForIAParameterTypes26 = new Class[] {
			String.class, long.class, long.class, long.class, byte[].class,
			String.class, String.class, String.class, String[].class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _createIAParameterTypes27 = new Class[] {
		String.class, String.class, String.class, String.class, String.class,
		String.class, String.class, String.class,
		com.liferay.portal.kernel.service.ServiceContext.class
	};
	private static final Class<?>[] _updateIAParameterTypes28 = new Class[] {
		long.class, String.class, String.class, String.class, String.class,
		String.class, String.class, String.class, String.class,
		com.liferay.portal.kernel.service.ServiceContext.class
	};
	private static final Class<?>[]
		_uploadYourFilesFileEntryForConsultationParameterTypes29 = new Class[] {
			String.class, long.class, long.class, long.class, byte[].class,
			String.class, String.class, String.class, String[].class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _createConsultationParameterTypes30 =
		new Class[] {
			String.class, String.class, String.class, String.class,
			String.class, String.class, String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _UpdateConsultationParameterTypes31 =
		new Class[] {
			long.class, String.class, String.class, String.class, String.class,
			String.class, String.class, String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _createApplicationParameterTypes32 =
		new Class[] {
			String.class, String.class, String.class, String.class,
			String.class, String.class, String.class, String.class,
			String.class, String.class, String.class, String.class,
			String.class, String.class, String.class, String.class,
			String.class, String.class, String.class, String.class,
			String.class, com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _updateApplicationParameterTypes33 =
		new Class[] {
			long.class, String.class, String.class, String.class, String.class,
			String.class, String.class, String.class, String.class,
			String.class, String.class, String.class, String.class,
			String.class, String.class, String.class, String.class,
			String.class, String.class, String.class, String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _createCommitteeActionLogParameterTypes34 =
		new Class[] {
			String.class, String.class, String.class, String.class,
			String.class, String.class, String.class, String.class, long.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _updateCommitteeActionLogParameterTypes35 =
		new Class[] {
			long.class, String.class, String.class, String.class, String.class,
			String.class, String.class, String.class, String.class
		};
	private static final Class<?>[]
		_createCommitteeVotingProcessParameterTypes36 = new Class[] {
			String.class, String.class, String.class, String.class,
			String.class, String.class, String.class, long.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[]
		_updateCommitteeVotingProcessParameterTypes37 = new Class[] {
			long.class, String.class, String.class, String.class, String.class,
			String.class, String.class, String.class, String[].class
		};
	private static final Class<?>[]
		_createCommitteeNominationsParameterTypes38 = new Class[] {
			String.class, String.class, String.class, String.class,
			String.class, String.class, long.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[]
		_updateCommitteeNominationsParameterTypes39 = new Class[] {
			long.class, String.class, String.class, String.class, String.class,
			String.class, String.class
		};

}