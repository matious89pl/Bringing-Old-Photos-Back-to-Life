<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="rec.dashboards.config.RecDashboardsPreferences"%>
<%@ page import="rec.dashboards.constants.RecDashboardsPortletKeys"%>
<%@ page import="com.liferay.petra.string.StringPool"%>
<%@ page import="com.liferay.portal.kernel.util.Validator"%>
<%@ page import="com.liferay.portal.kernel.util.GetterUtil"%>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
	RecDashboardsPreferences recDashboardsPreferences = (RecDashboardsPreferences) renderRequest.getAttribute(RecDashboardsPreferences.class.getName());

	String dashboardRole = StringPool.BLANK;
	String dashboardUserId = StringPool.BLANK;
	String dashboardLink = StringPool.BLANK;
	String dashboardDataset = StringPool.BLANK;
	String dashboardHeight = "700";
	String dashboardPublic = "false";

	if (Validator.isNotNull(recDashboardsPreferences)) {
		dashboardRole = portletPreferences.getValue("role", StringPool.BLANK);
		dashboardUserId = portletPreferences.getValue("userId", StringPool.BLANK);
		dashboardLink = portletPreferences.getValue("link", StringPool.BLANK);
		dashboardDataset = portletPreferences.getValue("dataset", StringPool.BLANK);
		dashboardHeight = portletPreferences.getValue("height", "700");
		dashboardPublic = portletPreferences.getValue("isPublic", "false");
	}
%>
