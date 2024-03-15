<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>
 <%@ taglib prefix="clay" uri="http://liferay.com/tld/clay" %>
 <%@ taglib prefix="liferay-item-selector" uri="http://liferay.com/tld/item-selector" %>
 <%@ taglib prefix="liferay-frontend" uri="http://liferay.com/tld/frontend" %>
 <%@ taglib prefix="liferay-portlet" uri="http://liferay.com/tld/portlet" %>
 <%@ taglib prefix="liferay-theme" uri="http://liferay.com/tld/theme" %>
 <%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>
 <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<%@ page import="com.liferay.portal.kernel.util.Validator"%>
<%@ page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ page import="com.liferay.portal.kernel.util.Constants"%>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil"%>

 <%@ page import="com.liferay.portal.kernel.util.HtmlUtil"%>
 <%@ page import="com.liferay.frontend.taglib.clay.servlet.taglib.util.*" %>
 <%@ page import="com.liferay.portal.kernel.util.ParamUtil"%>
 <%@ page import="com.everis.rec.activity.config.*" %>
 <%@ page import="javax.portlet.*" %>
 <%@ page import="com.everis.rec.activity.constants.*" %>
 <%@  page import="com.everis.rec.activity.portlet.ActivitySearchClayManagementPortlet"%>
 
 <liferay-frontend:defineObjects />

 <liferay-theme:defineObjects />

 <portlet:defineObjects />
 
