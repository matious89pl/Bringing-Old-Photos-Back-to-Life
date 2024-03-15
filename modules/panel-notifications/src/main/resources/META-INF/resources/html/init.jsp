<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@page import="com.everis.panel.notifications.constants.PanelNotificationsPortletKeys"%>

<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@page import="com.liferay.portal.kernel.theme.ThemeDisplay"%>
<%@page import="com.liferay.portal.kernel.util.PropsUtil"%>
<%@ page import="com.liferay.portal.kernel.util.WebKeys" %>

<%@ page import="com.liferay.taglib.search.ResultRow" %>

<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>

<%@page import="javax.portlet.PortletURL"%>

<%@page import="com.everis.messages.service.builder.service.MessagesLocalServiceUtil"%>
<%@page import="com.everis.messages.service.builder.model.Messages"%>


<liferay-theme:defineObjects />

<portlet:defineObjects />