<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@page import="javax.portlet.PortletURL"%>
<%@ page import="javax.portlet.WindowState"%>
<%@ page import="javax.portlet.PortletMode"%>

<%@ page import="java.util.List"%>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil"%>

<%@ page import="java.time.ZoneId"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.time.Instant"%>
<%@ page import="java.time.ZonedDateTime"%>
<%@ page import="java.time.format.DateTimeFormatter"%>

<%@ page import="com.liferay.segments.model.SegmentsEntry"%>
<%@ page import="com.liferay.segments.service.SegmentsEntryLocalServiceUtil"%>
<%@page import="rec.customnotification.service.NotificationRpaLocalServiceUtil"%>
<%@page import="rec.customnotification.model.NotificationRpa"%>


<liferay-theme:defineObjects />

<portlet:defineObjects />