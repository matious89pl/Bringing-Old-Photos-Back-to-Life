/*
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

package com.everis.cproposal.service.impl;

import com.everis.cproposal.model.recFormArticle;
import com.everis.cproposal.service.recFormArticleLocalServiceUtil;
import com.everis.cproposal.service.base.recFormArticleLocalServiceBaseImpl;
import com.everis.cproposal.service.persistence.recFormArticlePersistence;
import com.everis.cproposal.util.CProposalGetFormInfoUtil;
import com.everis.cproposal.util.CProposalUtil;
import com.everis.cproposal.util.CalendarUtil;
import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.service.CalendarLocalServiceUtil;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.journal.service.JournalArticleServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.UserNotificationEvent;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserNotificationEventLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.segments.model.SegmentsEntry;
import com.liferay.segments.model.SegmentsEntryRel;
import com.liferay.segments.service.SegmentsEntryLocalServiceUtil;
import com.liferay.segments.service.SegmentsEntryRelLocalServiceUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * The implementation of the rec form article local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * <code>com.everis.cproposal.service.recFormArticleLocalService</code>
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see recFormArticleLocalServiceBaseImpl
 */
@Component(property = "model.class.name=com.everis.cproposal.model.recFormArticle", service = AopService.class)
public class recFormArticleLocalServiceImpl extends recFormArticleLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use
	 * <code>com.everis.cproposal.service.recFormArticleLocalService</code> via
	 * injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use
	 * <code>com.everis.cproposal.service.recFormArticleLocalServiceUtil</code>.
	 */

	private final Log logger = LogFactoryUtil.getLog(recFormArticleLocalServiceImpl.class);

	/*
	 * Withdraw option
	 */
	public void removeJournalArticleUser(long resourcePrimKey) {

		logger.info("START - removeJournalArticleUser - articleId: " + resourcePrimKey);

		try {
			JournalArticle article = JournalArticleServiceUtil.getLatestArticle(resourcePrimKey);
			logger.info("article.... " + article);
			Document document = SAXReaderUtil.read(article.getContent());

			String fieldName = "ChangeProposerEmail";
			Node node = document
					.selectSingleNode("//root//dynamic-element[@name='" + fieldName + "']//dynamic-content");
			node.setText(StringPool.BLANK);

			logger.info("document.asXML.... " + document.asXML());
			article.setContent(document.asXML());

			String journalArticleResourceName = JournalArticle.class.getName();

			Role portalUser = RoleLocalServiceUtil.getRole(article.getCompanyId(), "Portal_User");
			String[] actionIds = new String[] { ActionKeys.VIEW, ActionKeys.UPDATE };

//			if (!ResourcePermissionLocalServiceUtil.hasResourcePermission(article.getCompanyId(),
//					journalArticleResourceName, ResourceConstants.SCOPE_INDIVIDUAL,
//					String.valueOf(article.getResourcePrimKey()), portalUser.getRoleId(), ActionKeys.UPDATE)) {
//				logger.info("The user does not have UPDATE permission... ");

			ResourcePermissionLocalServiceUtil.setResourcePermissions(article.getCompanyId(),
					journalArticleResourceName, ResourceConstants.SCOPE_INDIVIDUAL,
					String.valueOf(article.getResourcePrimKey()), portalUser.getRoleId(), actionIds);
			logger.info("UPDATE and VIEW permission added... ");

//			}

			JournalArticleServiceUtil.updateContent(article.getGroupId(), article.getArticleId(), article.getVersion(),
					article.getContent());

			logger.info("END - removeJournalArticleUser.");

		} catch (Exception e) {
			logger.error("END with Exception - removeJournalArticleUser... ", e);
		}
	}

	public long getFormIdByArticleId(long articleId) {
		List<recFormArticle> articles = recFormArticlePersistence.findByarticleId(articleId);
		logger.info("starting getFormIdByArticleId");

			if (articles.size() > 0) {
				recFormArticle recFormArticle = articles.get(0);
				logger.info("getFormIdByArticleId - article found: " + recFormArticle);
				return recFormArticle.getFormId();
			}

		return 0;
	}

	public long createCProposal(long classPK) throws DocumentException {
		return CProposalUtil.createCProposal(classPK);
	}

	public List<DDMStructure> getStructuresByName(long companyId, String name) {
		logger.info("getStructuresByName - name: " + name);
		DynamicQuery dynamicQuery = DDMStructureLocalServiceUtil.dynamicQuery();
		dynamicQuery.add(PropertyFactoryUtil.forName("companyId").eq(companyId));
		dynamicQuery.add(PropertyFactoryUtil.forName("name").like("%" + name + "%"));
		List<DDMStructure> ddmStructureList = DDMStructureLocalServiceUtil.dynamicQuery(dynamicQuery);
		return ddmStructureList;
	}

	public List<DDMStructure> getStructuresByNameAndByGroupId(long companyId, long groupId, String name) {
		logger.info("getStructuresByNameAndByGroupId - name: " + name);
		DynamicQuery dynamicQuery = DDMStructureLocalServiceUtil.dynamicQuery();
		dynamicQuery.add(PropertyFactoryUtil.forName("companyId").eq(companyId));
		dynamicQuery.add(PropertyFactoryUtil.forName("groupId").eq(groupId));
		dynamicQuery.add(PropertyFactoryUtil.forName("name").like("%" + name + "%"));
		List<DDMStructure> ddmStructureList = DDMStructureLocalServiceUtil.dynamicQuery(dynamicQuery);
		return ddmStructureList;
	}

	public DDMStructure getChangeProposalPageStructure(long companyId) {
		logger.debug("getChangeProposalPageStructure... starting method... ");
		DDMStructure ddmStructure = null;
		List<DDMStructure> ddmStructureList = getStructuresByName(companyId, "CHANGE-PROPOSAL");
		if (ddmStructureList.size() > 0) {
			ddmStructure = ddmStructureList.get(0);
		}
		logger.debug("Returned DDMStructure: " + ddmStructure);
		logger.debug("getChangeProposalPageStructure... ending method... ");
		return ddmStructure;
	}

	public List<JournalArticle> getChangeProposalFilteredByIds(String changePorposalList) {
		logger.debug("getChangeProposalFilteredByIds... starting method... ");

		List<Long> changePorposalRPK = new ArrayList<Long>();
		List<JournalArticle> result = new ArrayList<>();

		if (changePorposalList.contains(";spt;")) {
			for (String cpRPK : changePorposalList.split(";spt;")) {
				changePorposalRPK.add(Long.valueOf(cpRPK));
			}
		} else {
			changePorposalRPK.add(Long.valueOf(changePorposalList));
		}

		logger.info("changePorposalRPK: " + changePorposalRPK);
		logger.info("changePorposalRPK size: " + changePorposalRPK.size());
		for (long cpRPK : changePorposalRPK) {
			try {
				JournalArticle cp = JournalArticleLocalServiceUtil.getLatestArticle(cpRPK);
				result.add(cp);
				logger.info("CP added to filteredList: " + cp);
			} catch (Exception e) {
				logger.error("Error getting CP by respurcePrimKey", e);
			}
		}
		logger.info("Returned changeProposal_list: " + result);
		logger.debug("getChangeProposalFilteredByIds... ending method... ");
		return result;
	}

	public List<JournalArticle> getChangeProposalFilteredByIdsByPage(String changePorposalList, Long page) {
		logger.debug("getChangeProposalFilteredByIdsByPage... starting method... ");
		List<JournalArticle> listBypage = new ArrayList<>();
		List<JournalArticle> result = new ArrayList<>();
		result = getChangeProposalFilteredByIds(changePorposalList);
		if (result.size() > 10) {
			logger.info("Pagination needed");
			listBypage = result.stream().skip(10 * page).limit(10).collect(Collectors.toList());
			return listBypage;
		} else {
			logger.info("Not pagination needed");
			return result;
		}
	}

	public List<JournalArticle> getChangeProposalFilteredByPage(String status, Date startDate, Date endDate,
			String changePath, String responsibleCommittee, String impactedParties, String changeCategory, Long page) {
		logger.debug("getChangeProposalFilteredByPage... starting method... ");
		List<JournalArticle> listBypage = new ArrayList<>();
		List<JournalArticle> result = new ArrayList<>();
		result = getChangeProposalFiltered(status, startDate, endDate, changePath, responsibleCommittee,
				impactedParties, changeCategory);
		if (result.size() > 10) {
			logger.info("Pagination needed");
			listBypage = result.stream().skip(10 * page).limit(10).collect(Collectors.toList());
			return listBypage;
		} else {
			logger.info("Not pagination needed");
			return result;
		}
	}

	public List<JournalArticle> getChangeProposalFiltered(String status, Date startDate, Date endDate,
			String changePath, String responsibleCommittee, String impactedParties, String changeCategory) {
		logger.debug("getChangeProposalFiltered... starting method... ");
		List<JournalArticle> result = new ArrayList<>();
		List<recFormArticle> dqResult = new ArrayList<>();
		List<recFormArticle> finalResult = new ArrayList<>();
		List<String> statusList = new ArrayList<String>();
		List<String> impactedList = new ArrayList<String>();
		DateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");

		if (startDate != null) {
			startDate.setTime(startDate.getTime() - (long) 1 * 1000 * 60 * 60 * 24);
			logger.info("Start -1: " + startDate);
		}
		if (endDate != null) {
			endDate.setTime(endDate.getTime());
			logger.info("End +1: " + endDate);
		}

		if (status.contains(";spt;")) {
			for (String statusItem : status.split(";spt;")) {
				statusList.add(statusItem);
			}
		} else {
			statusList.add(status);
		}

		if (impactedParties.contains(";spt;")) {
			for (String impactedItem : impactedParties.split(";spt;")) {
				impactedList.add(impactedItem);
			}
		} else {
			impactedList.add(impactedParties);
		}

		boolean filteredByDate = false;
		boolean filteredByImpacts = false;

		DynamicQuery dqRecFormArticle = recFormArticleLocalServiceUtil.dynamicQuery();
		if (Validator.isNotNull(status)) {
			dqRecFormArticle.add(PropertyFactoryUtil.forName("articleStatus").in(statusList.toArray()));
		}
		if (Validator.isNotNull(startDate) || Validator.isNotNull(endDate)) {
			dqRecFormArticle.add(PropertyFactoryUtil.forName("articleReleaseSchedule").isNotNull());
			filteredByDate = true;
		}
		if (Validator.isNotNull(changePath)) {
			dqRecFormArticle.add(PropertyFactoryUtil.forName("articleChangePath").eq(changePath));
		}
		if (Validator.isNotNull(responsibleCommittee)) {
			dqRecFormArticle.add(PropertyFactoryUtil.forName("articleResponsibleCommittee").eq(responsibleCommittee));
		}
		if (Validator.isNotNull(impactedParties)) {
			dqRecFormArticle.add(PropertyFactoryUtil.forName("articleImpacts").isNotNull());
			filteredByImpacts = true;
		}
		if (Validator.isNotNull(changeCategory)) {
			dqRecFormArticle.add(PropertyFactoryUtil.forName("articleChangeCategory").eq(changeCategory));
		}

		logger.info("Query executed: " + dqRecFormArticle.toString());
		try {
			dqResult = recFormArticleLocalServiceUtil.dynamicQuery(dqRecFormArticle);
		} catch (Exception e) {
			logger.error("Executing dynamicQuery in getChangeProposalFiltered", e);
		}

		logger.info("dqResult: " + dqResult);
		logger.info("dqResult size: " + dqResult.size());
		if (dqResult.size() > 0) {
			logger.info("filteredByDate " + filteredByDate);
			if (filteredByDate) {
				for (int i = 0; i < dqResult.size(); i++) {
					logger.info("CPROPOSAL " + dqResult.get(i));
					String date_string = dqResult.get(i).getArticleReleaseSchedule();
					if (Validator.isNotNull(date_string)) {
						logger.info("date_string " + date_string);
						try {
							String replacedDate_String = date_string.replaceFirst("(?<=\\d)(?:st|nd|rd|th)", "");
							logger.info("replacedDate_String " + replacedDate_String);
							Date releaseScheduleDate = sdf.parse(replacedDate_String);
							if (releaseScheduleDate.after(startDate) && releaseScheduleDate.before(endDate)) {
								finalResult.add(dqResult.get(i));
								logger.info("ADDED");
							}
						} catch (ParseException e) {
							logger.error("Error casting String to date", e);
						}
					}
				}
				dqResult = finalResult;
				logger.info("dqResult after date filtering: " + dqResult);
			}

			logger.info("filteredByImpacts " + filteredByImpacts);
			if (filteredByImpacts) {
				for (int i = 0; i < dqResult.size(); i++) {
					logger.info("CPROPOSAL " + dqResult.get(i));
					String articleImpacts = dqResult.get(i).getArticleImpacts();
					if (Validator.isNotNull(articleImpacts)) {
						logger.info("articleImpacts " + articleImpacts);
						List<String> impactList = Arrays.asList(articleImpacts.split(","));
						for (String impact : impactedList) {
							if (impactList.contains(impact)) {
								finalResult.add(dqResult.get(i));
								logger.info("ADDED");
								break;
							}
						}
					}
				}
				dqResult = finalResult;
				logger.info("dqResult after impact filtering: " + dqResult);
			}

			for (int i = 0; i < dqResult.size(); i++) {
				JournalArticle ja = JournalArticleLocalServiceUtil.fetchLatestArticle(dqResult.get(i).getArticleId());
				if (Validator.isNotNull(ja)) {
					result.add(ja);
					logger.info("ADDED to results: " + ja);
				}
			}

		}

		logger.debug("getChangeProposalFiltered... ending method... ");
		return result;
	}

	public List<JournalArticle> getActionLogFiltered(Date startDate, Date endDate, String status, Date startUpdate,
			Date endUpdate, long groupId, String ddmStructureKey) throws DocumentException, ParseException {
		logger.info("getActionLogFiltered... starting method... ");
		List<JournalArticle> result = new ArrayList<>();
		List<JournalArticle> totalDate = new ArrayList<>();
		List<JournalArticle> totalStatus = new ArrayList<>();
		List<JournalArticle> totalUpdate = new ArrayList<>();
		List<String> statusList = new ArrayList<String>();

		logger.info("startDate: " + startDate);
		logger.info("endDate: " + endDate);
		logger.info("status: " + status);
		logger.info("startUpdate: " + startUpdate);
		logger.info("endUpdate: " + endUpdate);

		boolean filteredByDate = false;
		boolean filteredByStatus = false;
		boolean filteredByUpdate = false;

		if (Validator.isNotNull(startDate) || Validator.isNotNull(endDate)) {
			filteredByDate = true;
		}

		if (Validator.isNotNull(status)) {
			logger.info("Status: " + status);
			if (status.contains(";spt;")) {
				for (String statusItem : status.split(";spt;")) {
					statusList.add(statusItem);
					logger.info("Status1: " + statusList);
					filteredByStatus = true;
				}
			} else {
				statusList.add(status);
				filteredByStatus = true;
				logger.info("Status2: " + statusList);
			}
		}

		if (Validator.isNotNull(startUpdate) || Validator.isNotNull(endUpdate)) {
			filteredByUpdate = true;
		}

		result = JournalArticleLocalServiceUtil.getStructureArticles(groupId, ddmStructureKey);

		logger.info("filteredByDate: " + filteredByDate);
		if (filteredByDate) {
			for (JournalArticle actionLogJournal : result) {
				Document document = SAXReaderUtil.read(actionLogJournal.getContent());
				Node node = document
						.selectSingleNode("//root//dynamic-element[@name='ActionLog_DueDate']//dynamic-content");
				String actionLogStatus = node.getText();

				if (!actionLogStatus.isEmpty()) {
					Date dateParse = new SimpleDateFormat("yyyy-MM-dd").parse(actionLogStatus);
					logger.info("dateParse: " + dateParse);

					boolean isDateBetween = ((dateParse.after(startDate)) && (dateParse.before(endDate)));
					boolean isDateEqual = ((dateParse.equals(startDate)) || (dateParse.equals(endDate)));
					logger.info("isDateBetween: " + isDateBetween);
					logger.info("isDateEqual: " + isDateEqual);

					if (isDateBetween || isDateEqual) {
						totalDate.add(actionLogJournal);
						logger.info("dateParseAdd: " + dateParse);
					}
				}
			}
			result = totalDate;
		}

		logger.info("filteredByStatus: " + filteredByStatus);
		if (filteredByStatus) {
			logger.info("Enter status: ");
			for (JournalArticle actionLogJournal : result) {
				logger.info("Enter status1: ");
				Document document = SAXReaderUtil.read(actionLogJournal.getContent());
				Node node = document
						.selectSingleNode("//root//dynamic-element[@name='ActionLog_Status']//dynamic-content");
				String actionLogStatus = node.getText();

				if (!actionLogStatus.isEmpty()) {
					logger.info("Status3: " + actionLogStatus);
					for (String list : statusList) {

						if (actionLogStatus.equals(list)) {
							totalStatus.add(actionLogJournal);
							logger.info("Status4: " + actionLogStatus);
						}
					}
				}
			}
			result = totalStatus;
		}

		logger.info("filteredByUpdate: " + filteredByUpdate);
		if (filteredByUpdate) {
			for (JournalArticle actionLogJournal : result) {
				Document document = SAXReaderUtil.read(actionLogJournal.getContent());
				Node node = document
						.selectSingleNode("//root//dynamic-element[@name='ActionLog_NextUpdateDue']//dynamic-content");
				String actionLogStatus = node.getText();

				if (!actionLogStatus.isEmpty()) {
					Date dateParse = new SimpleDateFormat("yyyy-MM-dd").parse(actionLogStatus);
					logger.info("dateParse: " + dateParse);

					boolean isDateBetween = ((dateParse.after(startUpdate)) && (dateParse.before(endUpdate)));
					boolean isDateEqual = ((dateParse.equals(startUpdate)) || (dateParse.equals(endUpdate)));
					logger.info("isDateBetween: " + isDateBetween);
					logger.info("isDateEqual: " + isDateEqual);

					if (isDateBetween || isDateEqual) {
						totalUpdate.add(actionLogJournal);
						logger.info("dateParse: " + dateParse);
					}
				}
			}
			result = totalUpdate;
		}

		logger.debug("getActionLogFiltered... ending method... ");
		return result;
	}

	public Map<String, String> getUserNotificationsLimit(long userId, int limit) {

		// logger.info("User ID" + userId + ", Limit " + limit);

		List<UserNotificationEvent> event_list = UserNotificationEventLocalServiceUtil
				.getUserNotificationEvents(userId);
		Map<String, String> user_notifications_list = new HashMap<String, String>();
		int cont = 0;
		String cadena = StringPool.BLANK;
		try {
			for (UserNotificationEvent event : event_list) {

				JSONObject josn = JSONFactoryUtil.createJSONObject(event.getPayload());

				if (cont < limit) {
					if (event.getType().matches("com_everis_notification.*")) {
						cadena = String.valueOf(josn.get("entryTitle"));
					} else if (event.getType().matches("com_liferay_portal.*")) {
						cadena = String.valueOf(josn.get("notificationMessage"));
					} else if (event.getType().matches("com_liferay_exportimport.*")) {
						cadena = "Export/Import process finished";
					} else if (event.getType().matches("com_liferay_comment.*")) {
						cadena = "A user added a new comment";
					} else if (event.getType().matches("com_liferay_mentions.*")) {
						cadena = "A user mentioned you in a comment";
					} else if (event.getType().matches("com_liferay_message_boards.*")) {
						String sender = josn.getString("fullName");
						cadena = sender + " added a new MB message";
					} else {
						continue;
					}

					long difference_In_Time = System.currentTimeMillis() - event.getTimestamp();
					long difference_In_Days = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365;

					long difference_In_Seconds = (difference_In_Time / 1000) % 60;

					long difference_In_Minutes = (difference_In_Time / (1000 * 60)) % 60;

					long difference_In_Hours = (difference_In_Time / (1000 * 60 * 60)) % 24;

					if (difference_In_Days > 1) {
						user_notifications_list.put(cont + cadena, difference_In_Days + " days ago");
					} else if (difference_In_Days == 1) {
						user_notifications_list.put(cont + cadena, "1 day ago");
					} else if (difference_In_Hours > 1) {
						user_notifications_list.put(cont + cadena, difference_In_Hours + " hours ago");
					} else if (difference_In_Hours == 1) {
						user_notifications_list.put(cont + cadena, "1 hour ago");
					} else if (difference_In_Minutes > 1) {
						user_notifications_list.put(cont + cadena, difference_In_Minutes + " minutes ago");
					} else if (difference_In_Minutes == 1) {
						user_notifications_list.put(cont + cadena, "1 minute ago");
					} else if (difference_In_Seconds > 1) {
						user_notifications_list.put(cont + cadena, difference_In_Seconds + " seconds ago");
					} else if (difference_In_Seconds == 1) {
						user_notifications_list.put(cont + cadena, "1 second ago");
					}

					cont++;
				} else {
					break;
				}
			}
		} catch (JSONException e1) {
			logger.error("Error conviertiendo en JSONObject" + e1);
		}

		Map<String, String> treeMap = new TreeMap<String, String>(user_notifications_list);

		return treeMap;
	}
	public JSONObject  getCalendarJsonObject (ThemeDisplay themeDisplay, Calendar calendar) throws PortalException {
		
		
		return CalendarUtil.toCalendarJSONObject(themeDisplay, calendar);
		
	}
	public long getCalendarIDbyName(String CalendarName) {
		 long cID = 0L;
		try {
		DynamicQuery dqStructure = CalendarLocalServiceUtil.dynamicQuery();
        dqStructure.add(PropertyFactoryUtil.forName("name").like("%"+CalendarName+"%"));
		List<Calendar> ddmListStructure = CalendarLocalServiceUtil.dynamicQuery(dqStructure);
		  cID = ddmListStructure.get(0).getCalendarId();
		 logger.info("cID... " + ddmListStructure);
		}catch(Exception e) {
			 logger.info("cID... " + e);

		 }
		
		return cID;
		
	}

	public boolean isAlternative(long resourcePrimKey) {
		long formId = recFormArticleLocalServiceUtil.getFormIdByArticleId(resourcePrimKey);
		DynamicQuery dynamicQuery = recFormArticleLocalServiceUtil.dynamicQuery();
		dynamicQuery.add(PropertyFactoryUtil.forName("alternativeFormIds").like("%" + formId + "%"));
		List<recFormArticle> recFormArticles = recFormArticleLocalServiceUtil.dynamicQuery(dynamicQuery);
		return recFormArticles.size() > 0 ? true : false;
	}

	public long getParentAlternativeFormId(long resourcePrimKey) {
		long formId = recFormArticleLocalServiceUtil.getFormIdByArticleId(resourcePrimKey);
		DynamicQuery dynamicQuery = recFormArticleLocalServiceUtil.dynamicQuery();
		dynamicQuery.add(PropertyFactoryUtil.forName("alternativeFormIds").like("%" + formId + "%"));
		List<recFormArticle> recFormArticles = recFormArticleLocalServiceUtil.dynamicQuery(dynamicQuery);
		long formIdParent = recFormArticles.get(0).getFormId();
		return formIdParent;
	}

	public Map<String, JSONObject> getCPDetails(String resourcePrimKey) {
		return CProposalGetFormInfoUtil.getCPDetails(resourcePrimKey);
	}

	public boolean isCProposalUserValid(long currentUserId, String typeOfSegment) {
		boolean isValid = false;
		DynamicQuery dc = SegmentsEntryLocalServiceUtil.dynamicQuery();
		dc.add(PropertyFactoryUtil.forName("name").like("%" + typeOfSegment + "%"));
		List<SegmentsEntry> segmentsEntryList = SegmentsEntryLocalServiceUtil.dynamicQuery(dc);
        if(segmentsEntryList.isEmpty()) {
            logger.debug("Segment: "+ typeOfSegment+" does not exist");
            return false;
        }
		SegmentsEntry se = segmentsEntryList.get(0);
		List<SegmentsEntryRel> segmentsEntryRel = SegmentsEntryRelLocalServiceUtil
				.getSegmentsEntryRels(se.getSegmentsEntryId());
		for (SegmentsEntryRel segmentsEntryRelUsers : segmentsEntryRel) {
			long userId = segmentsEntryRelUsers.getClassPK();
			if(userId == currentUserId) {
				isValid = true;
				break;
			}
		}
		return isValid;
	}

	@Reference
	protected recFormArticlePersistence _recFormArticlePersistence;

}
