<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>
<%@ taglib prefix="clay" uri="http://liferay.com/tld/clay" %>
<%@ taglib prefix="liferay-item-selector" uri="http://liferay.com/tld/item-selector" %>
<%@ taglib prefix="liferay-frontend" uri="http://liferay.com/tld/frontend" %>
<%@ taglib prefix="liferay-portlet" uri="http://liferay.com/tld/portlet" %>
<%@ taglib prefix="liferay-theme" uri="http://liferay.com/tld/theme" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page import="javax.portlet.*" %>
<%@ page import="java.util.List"%>

<%@ page import="java.time.ZoneId"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.time.Instant"%>
<%@ page import="java.time.ZonedDateTime"%>
<%@ page import="java.time.format.DateTimeFormatter"%>

<%@page import="rec.supporting.resources.model.supportR"%>
<%@page import="rec.supporting.resources.service.supportRLocalServiceUtil"%>

<%@page import="com.liferay.portal.kernel.service.GroupLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.model.Group"%>
<%@page	import="com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil"%>
<%@page	import="com.liferay.portal.kernel.model.UserGroupRole"%>
<%@page import="com.liferay.portal.kernel.service.RoleLocalServiceUtil"%>

<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page	import="com.liferay.portal.kernel.util.StringUtil"%>

<%@page	import="javax.portlet.PortletSession"%>
<liferay-theme:defineObjects />

<portlet:defineObjects />