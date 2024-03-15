package com.everis.cproposal.util;

import com.everis.cproposal.service.impl.recFormArticleLocalServiceImpl;
import com.liferay.calendar.constants.CalendarActionKeys;
import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.service.CalendarBookingService;
import com.liferay.calendar.service.CalendarResourceLocalService;
import com.liferay.calendar.service.CalendarResourceLocalServiceUtil;
import com.liferay.calendar.service.CalendarService;
import com.liferay.calendar.service.CalendarServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalService;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import org.osgi.service.component.annotations.Reference;

public class CalendarUtil {
	private final static Log logger = LogFactoryUtil.getLog(recFormArticleLocalServiceImpl.class);

	public static JSONObject toCalendarJSONObject(
			ThemeDisplay themeDisplay, Calendar calendar)
		throws PortalException {
		CalendarResource calendarResource = null;
		try {
		 calendarResource =
				 CalendarResourceLocalServiceUtil.getCalendarResource(calendar.getCalendarResourceId());
		 logger.info(calendarResource);	
		}
		catch (Exception e) {
			logger.info(e.fillInStackTrace());

		}
		logger.info(calendar.getColor());	
		int[] color = {calendar.getColor()};
		return JSONUtil.put(
			"calendarId", calendar.getCalendarId()
		).put(
			"calendarResourceId", calendarResource.getCalendarResourceId()
		).put(
			"calendarResourceName",
			calendarResource.getName(themeDisplay.getLocale())
		).put(
			"classNameId", calendarResource.getClassNameId()
		).put(
			"classPK", calendarResource.getClassPK()
		).put(
				"color", "#65AD89"
		).put(
			"defaultCalendar", calendar.isDefaultCalendar()
		).put(
			"groupId", calendar.getGroupId()
		).put(
			"hasWorkflowDefinitionLink",
			
			WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(
				themeDisplay.getCompanyId(), calendarResource.getGroupId(),
				CalendarBooking.class.getName())
			
		).put(
			"manageable", 
			CalendarServiceUtil.isManageableFromGroup(
				calendar.getCalendarId(), themeDisplay.getScopeGroupId())
		).put(
			"name", calendar.getName(themeDisplay.getLocale())
		).put(
			"permissions",
			_getPermissionsJSONObject(
				themeDisplay.getPermissionChecker(), calendar)
		).put(
			"userId", calendar.getUserId()
		);
	}

	private static JSONObject _getPermissionsJSONObject(PermissionChecker permissionChecker, Calendar calendar)
			throws PortalException {
		
		Group gropObject  = GroupLocalServiceUtil.getGroup(calendar.getGroupId());
		
		return JSONUtil
				.put(ActionKeys.DELETE,
						permissionChecker.hasPermission(gropObject, Calendar.class.getName(), calendar.getPrimaryKey(), ActionKeys.DELETE)
)
				.put(ActionKeys.PERMISSIONS,
						permissionChecker.hasPermission(gropObject, Calendar.class.getName(), calendar.getPrimaryKey(), ActionKeys.PERMISSIONS)
)
				.put(ActionKeys.UPDATE,
						permissionChecker.hasPermission(gropObject, Calendar.class.getName(), calendar.getPrimaryKey(), ActionKeys.UPDATE)
)
				.put(ActionKeys.VIEW,
						permissionChecker.hasPermission(gropObject, Calendar.class.getName(), calendar.getPrimaryKey(), ActionKeys.VIEW)
)
				.put(CalendarActionKeys.MANAGE_BOOKINGS,
						permissionChecker.hasPermission(gropObject, Calendar.class.getName(), calendar.getPrimaryKey(), CalendarActionKeys.MANAGE_BOOKINGS))
				.put(CalendarActionKeys.VIEW_BOOKING_DETAILS,permissionChecker.hasPermission(gropObject, Calendar.class.getName(), calendar.getPrimaryKey(), CalendarActionKeys.VIEW_BOOKING_DETAILS)
);
	}
	@Reference(unbind = "-")
	protected void setCalendarBookingService(
		CalendarBookingService calendarBookingService) {

		_calendarBookingService = calendarBookingService;
	}

	@Reference(unbind = "-")
	protected void setCalendarResourceLocalService(
		CalendarResourceLocalService calendarResourceLocalService) {

		_calendarResourceLocalService = calendarResourceLocalService;
	}

	@Reference(unbind = "-")
	protected void setCalendarService(CalendarService calendarService) {
		_calendarService = calendarService;
	}

	@Reference(
		target = "(model.class.name=com.liferay.calendar.model.Calendar)",
		unbind = "-"
	)
	protected void setModelResourcePermission(
		ModelResourcePermission<Calendar> modelResourcePermission) {

		_calendarModelResourcePermission = modelResourcePermission;
	}

	@Reference(unbind = "-")
	protected void setWorkflowDefinitionLinkLocalService(
		WorkflowDefinitionLinkLocalService workflowDefinitionLinkLocalService) {

		_workflowDefinitionLinkLocalService =
			workflowDefinitionLinkLocalService;
	}

	@Reference(unbind = "-")
	protected void setWorkflowInstanceLinkLocalService(
		WorkflowInstanceLinkLocalService workflowInstanceLinkLocalService) {

		_workflowInstanceLinkLocalService = workflowInstanceLinkLocalService;
	}

	
	private static CalendarBookingService _calendarBookingService;
	private static ModelResourcePermission<Calendar> _calendarModelResourcePermission;
	private static CalendarResourceLocalService _calendarResourceLocalService;
	private static CalendarService _calendarService;
	private static WorkflowDefinitionLinkLocalService _workflowDefinitionLinkLocalService;
	private static WorkflowInstanceLinkLocalService _workflowInstanceLinkLocalService;
}
