<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>



<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui"%><%@
taglib
	uri="http://liferay.com/tld/portlet" prefix="liferay-portlet"%><%@
taglib
	uri="http://liferay.com/tld/theme" prefix="liferay-theme"%><%@
taglib
	uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<%@ taglib uri="http://liferay.com/tld/comment" prefix="liferay-comment"%>

<%@page import="java.time.LocalDate"%>
<%@page import="java.util.Locale"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="com.liferay.portal.kernel.service.RoleLocalServiceUtil"%>
<%@page import="java.util.List"%>

<%@ page import="javax.portlet.*"%>

<%@ page import="com.liferay.portal.kernel.util.Validator"%>
<%@ page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@ page import="com.liferay.portal.kernel.util.Constants"%>
<%@ page import="com.liferay.portal.kernel.util.HtmlUtil"%>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@ page import="javax.portlet.PortletPreferences"%>

<%@ page
	import="com.liferay.document.library.kernel.service.DLAppLocalServiceUtil"%>

<%@ page
	import="com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil"%>
<%@ page import="com.liferay.document.library.kernel.model.DLFileEntry"%>

<%@ page import="com.liferay.frontend.taglib.clay.servlet.taglib.util.*"%>

<liferay-theme:defineObjects />

<portlet:defineObjects />


