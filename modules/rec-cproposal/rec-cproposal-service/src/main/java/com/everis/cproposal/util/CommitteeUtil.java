package com.everis.cproposal.util;

import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.service.CalendarBookingLocalServiceUtil;
import com.liferay.calendar.service.CalendarLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.Conjunction;
import com.liferay.portal.kernel.dao.orm.Disjunction;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CommitteeUtil {

	private static final Log logger = LogFactoryUtil.getLog(CommitteeUtil.class);

	public static List<CalendarBooking> get_rec_calendar_events(long companyId, long userId, long startTime,
			long endTime, boolean isAllCommittees) {
		logger.info("Getting recFormArticle with companyId: " + companyId + ", userId: " + userId + ", startTime: "
				+ startTime + ", endTime: " + endTime + ", isAllCommittees: " + isAllCommittees);

		List<CalendarBooking> rec_events = new ArrayList<CalendarBooking>();
		boolean atLeastOneCommittee = false;

		try {

			Group committeeSite = GroupLocalServiceUtil.fetchGroup(companyId, "Committees");
			logger.debug("committeeSite: " + committeeSite.getGroupId());

			List<Group> allCommitteesSites = GroupLocalServiceUtil.getGroups(companyId, committeeSite.getGroupId(),
					true);
			logger.debug("allCommitteesSites.size: " + allCommitteesSites.size());

			User user = UserLocalServiceUtil.getUser(userId);
			List<Group> user_groups = user.getGroups();
			List<Long> userGroupIds = user_groups.stream().map(a -> a.getGroupId()).collect(Collectors.toList());
			logger.debug("userGroupIds: " + userGroupIds.toString());

			DynamicQuery calendarBookingQuery = CalendarBookingLocalServiceUtil.dynamicQuery();

			Conjunction conjunction = RestrictionsFactoryUtil.conjunction();
			logger.debug("Before starting");
			
			if (!isAllCommittees) {
				logger.debug("My committees IN");
				// Add all user groups to dynamic query in OR clause
				if (Validator.isNotNull(user_groups) && !user_groups.isEmpty()) {
					
					Disjunction disjunctionUserGroups = RestrictionsFactoryUtil.disjunction();
					
					for (Group group : user_groups) {
						logger.debug("group: " + group);
						if (group.getParentGroupId() == committeeSite.getGroupId()) {
							logger.debug("committeeSitegroup: " + group.getParentGroupId());
							disjunctionUserGroups.add(RestrictionsFactoryUtil.eq("groupId", group.getGroupId()));
							atLeastOneCommittee = true;
						}
					}
					// Add disjunction to conjunction
					conjunction.add(disjunctionUserGroups);
				}
			} else {
				logger.debug("All committees IN");
				// Add all committees groups to dynamic query in OR clause
				// Conjunction conjunctionAllCommittees = RestrictionsFactoryUtil.conjunction();

				if (Validator.isNotNull(allCommitteesSites) && !allCommitteesSites.isEmpty()) {
					Disjunction disjunctionUserGroups = RestrictionsFactoryUtil.disjunction();
					for (Group group : allCommitteesSites) {
						logger.debug("group: " + group);
						// get event of committes where I'm in
						disjunctionUserGroups.add(RestrictionsFactoryUtil.eq("groupId", group.getGroupId()));
						atLeastOneCommittee = true;
					}
					conjunction.add(disjunctionUserGroups);
				}
			}
			
			conjunction.add(RestrictionsFactoryUtil.ge("startTime", startTime));
			conjunction.add(RestrictionsFactoryUtil.le("startTime", endTime));
		
			calendarBookingQuery.add(conjunction);

			List<CalendarBooking> dynamicQueryEvents = CalendarBookingLocalServiceUtil
					.dynamicQuery(calendarBookingQuery);
			
			logger.debug("size dynamicQueryEvents: " + dynamicQueryEvents.size());
			
			//Show in AllCommittees tab all public events and events to which user has been invited 
	 
			if (isAllCommittees) {
				logger.debug("isallCommitteess");
				if (Validator.isNotNull(dynamicQueryEvents) && !dynamicQueryEvents.isEmpty()) {
					logger.info("isNotEmpty");
					for (CalendarBooking event : dynamicQueryEvents) {
						logger.debug("after for: " + event);
						if (!userGroupIds.contains(event.getGroupId())) {
							logger.debug("NO userGroupIds.contains");
							logger.debug(event.getExpandoBridge().getAttribute("IsPrivate"));
							boolean isPrivate = (boolean) event.getExpandoBridge().getAttribute("IsPrivate");
							logger.debug("isPrivate: " + isPrivate);
							if (!isPrivate) {
								logger.debug("isNotPrivate");
								rec_events.add(event);
								logger.debug("rec_events: " + rec_events);
							}
						} else {
							rec_events.add(event);
						}
					}
				}
				// getting private events from external committees where the user has been
				// invited
				List<CalendarBooking> invitedEvents = getEventsByUser(userId, startTime, endTime);
				logger.info("Invited events size: "+invitedEvents.size());
				for (CalendarBooking event : invitedEvents) {
					if (!rec_events.contains(event)) {
						rec_events.add(event);
					}
				}
			} else {
				rec_events.addAll(dynamicQueryEvents);
			}

		} catch (PortalException e) {
			logger.error("Error getting rec calendar events", e);
			e.printStackTrace();
		}
		
		if(!atLeastOneCommittee) {
			rec_events = new ArrayList<CalendarBooking>();
		}
		
		// removing duplicates
		rec_events = rec_events.stream().distinct().collect(Collectors.toList());
		// sorting by time asc
		rec_events = rec_events.stream().sorted(Comparator.comparingLong(CalendarBooking::getStartTime))
				.collect(Collectors.toList());

		return rec_events;
	}

	public static List<CalendarBooking> get_rec_calendar_events_by_committee(long companyId, long userId,
			long startTime, long endTime, long groupId) {
		logger.info("Getting recFormArticle with companyId: " + companyId + ", userId: " + userId + ", startTime: "
				+ startTime + ", endTime: " + endTime + ", committeeId: " + groupId);

		List<CalendarBooking> rec_events = new ArrayList<CalendarBooking>();

		// 1. chequear si el usuario pertenece a ese grupo o no

		try {
			User user = UserLocalServiceUtil.getUser(userId);
			List<User> usersInAGgroup = UserLocalServiceUtil.getGroupUsers(groupId);
			// long[] usersByGroup = UserLocalServiceUtil.getGroupUserIds(groupId);
			logger.info("usersInAGgroup: " + usersInAGgroup.size());
			if (Validator.isNotNull(usersInAGgroup) && usersInAGgroup.size() > 0) {
				if (usersInAGgroup.contains(user)) {
					logger.info("user belongs to this committee");
					rec_events = getEventsByCommittee(groupId, true, startTime, endTime);
				} else {
					logger.info("user does not belong to this committee");
					rec_events = getEventsByCommittee(groupId, false, startTime, endTime);
				}
			}
		} catch (PortalException e) {
			logger.info("Error getting user by userId: " + userId + ". Message: " + e.getMessage());
		}

		// removing duplicates
		rec_events = rec_events.stream().distinct().collect(Collectors.toList());
		// sorting by time asc
		rec_events = rec_events.stream().sorted(Comparator.comparingLong(CalendarBooking::getStartTime))
				.collect(Collectors.toList());
		return rec_events;
	}

	private static List<CalendarBooking> getEventsByCommittee(long groupId, boolean belongs, long startTime,
			long endTime) {
		List<CalendarBooking> rec_events = new ArrayList<CalendarBooking>();
		DynamicQuery dynamicQuery = CalendarBookingLocalServiceUtil.dynamicQuery();
		dynamicQuery.add(PropertyFactoryUtil.forName("groupId").eq(groupId));
		dynamicQuery.add(PropertyFactoryUtil.forName("startTime").ge(startTime));
		dynamicQuery.add(PropertyFactoryUtil.forName("endTime").le(endTime));
		List<CalendarBooking> dynamicQueryEvents = CalendarBookingLocalServiceUtil.dynamicQuery(dynamicQuery);
		logger.info("rec_events.size() after query - " + dynamicQueryEvents.size());
		if (!belongs && Validator.isNotNull(dynamicQueryEvents) && dynamicQueryEvents.size() > 0) {
			for (CalendarBooking event : dynamicQueryEvents) {
				if (!isPrivateEvent(event))
					rec_events.add(event);
			}
		} else {
			rec_events = dynamicQueryEvents;
		}
		logger.info("rec_events final size:" + rec_events.size());
		return rec_events;
	}

	private static boolean isPrivateEvent(CalendarBooking event) {
		boolean isPrivate = (boolean) event.getExpandoBridge().getAttribute("IsPrivate");
		logger.info("isPrivateEvent? " + isPrivate);
		return isPrivate;
	}

	private static List<CalendarBooking> getEventsByUser(long userId, long startTime, long endTime) {
		List<CalendarBooking> rec_events = new ArrayList<CalendarBooking>();

		DynamicQuery dynamicQuery = CalendarLocalServiceUtil.dynamicQuery();
		dynamicQuery.add(PropertyFactoryUtil.forName("userId").eq(userId));
		List<Calendar> calendarList = CalendarLocalServiceUtil.dynamicQuery(dynamicQuery);
		if (Validator.isNotNull(calendarList) && calendarList.size() > 0) {
			Calendar calendar = calendarList.get(0);
			long calendarId = calendar.getCalendarId();
			logger.info("calendarId: " + calendarId);
			DynamicQuery bookingDynamicQuery = CalendarBookingLocalServiceUtil.dynamicQuery();
			bookingDynamicQuery.add(PropertyFactoryUtil.forName("calendarId").eq(calendarId));
			bookingDynamicQuery.add(PropertyFactoryUtil.forName("startTime").ge(startTime));
			bookingDynamicQuery.add(PropertyFactoryUtil.forName("endTime").le(endTime));
			List<CalendarBooking> calendarBookings = CalendarBookingLocalServiceUtil.dynamicQuery(bookingDynamicQuery);
			if (Validator.isNotNull(calendarBookings) && calendarBookings.size() > 0) {
				rec_events = calendarBookings;
			} else {
				logger.info("There is no calendar bookins for user with id: " + userId);
			}
		} else {
			logger.info("There is no calendar for user with id: " + userId);
		}
		return rec_events;
	}

}
