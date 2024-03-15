package com.everis.rec.menu.link.cofiguration.portlet;


import com.everis.rec.menu.link.cofiguration.constants.RecMenuLinkCofigurationPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ProcessAction;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import rec.link.menu.model.LinkMenu;
import rec.link.menu.service.LinkMenuLocalServiceUtil;
/**
 * @author Ricardo Rosa
 */
@Component(property = {
		"com.liferay.portlet.display-category=",
		"javax.portlet.display-name= Menu Configuration App",
		"javax.portlet.init-param.config-template=/configuration.jsp",
		"javax.portlet.init-param.view-template=/view.jsp", "com.liferay.portlet.header-portlet-css=/css/main.css",
		"javax.portlet.name=" + RecMenuLinkCofigurationPortletKeys.RECMENULINKCOFIGURATION,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.init-param.add-process-action-success-action=false",
		"javax.portlet.security-role-ref=power-user,user" }, 
		service = Portlet.class)

public class RecMenuLinkCofigurationPortlet extends MVCPortlet {
	
	// contants variables 
	private static String CMD = "CMD";
	private static final String EDIT = "edit";
	private static final String ADDED_LINK = "addedLink";
	private static final String EDITED_LINK = "editedLink";
	private static final String DELETED_LINK = "deletedLink";
	private static final String INVALID_URL = "invalid-url";
	private static final String HTTPS = "https://";
	private static final String WWW = "www";
	private static final String MVC_PATH = "mvcPath";
	private static final String ADD_LINK_JSP = "/add_link.jsp";
	private static final String VIEW_LINKS_URL = "viewLinksURL";
	private static final String LINK_ID = "linkId";
	private static final String LINK_NAME = "linkName";
	private static final String LINK_POSITION = "linkPosition";
	private static final String LINK = "link";
	private static final String ICON_NAME = "iconName";
	private static final String ABSOLUTE_URL_REGEX = "^(https?://)?(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,4}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)$";
	private static final String RELATIVE_URL_REGEX = "^\\/[a-z]+\\/[a-z]+\\/[a-z-]+$";
	private static final String RELATIVE_URL_REGEX_2 = "^\\/[a-zA-Z-]+$";
	Log log = LogFactoryUtil.getLog(RecMenuLinkCofigurationPortlet.class);
	private static ServiceContext context = null;
	private static User user = null;
	
	@Override
	public void doView(RenderRequest request, RenderResponse renderResponse)
			throws IOException, PortletException {
       	
		super.doView(request, renderResponse);
	
	}
	// method to update link
	@ProcessAction(name = "updateLink")	
    public void updateLink(ActionRequest request, ActionResponse response) {
        String cmd = request.getParameter(CMD);
        if (cmd.equals(EDIT)) 
        response.getRenderParameters().setValue(MVC_PATH, ADD_LINK_JSP);
    }

	// method to add a link
	@ProcessAction(name = "addLink")
	public void addLink(ActionRequest request, ActionResponse response) {
	    ServiceContext context;
	    try {
	        context = ServiceContextFactory.getInstance(RecMenuLinkCofigurationPortlet.class.getName(), request);
	    } catch (PortalException e) {
	        log.error("Error setting the service context or getting the user", e);
	        return;
	    }
	    String viewLinksURL = (String) request.getAttribute(VIEW_LINKS_URL);
	    String linkId = request.getParameter(LINK_ID);
	    String linkName = request.getParameter(LINK_NAME);
	    String linkValue = request.getParameter(LINK);
	    String iconName = request.getParameter(ICON_NAME);
	    String position = request.getParameter(LINK_POSITION );
	    if (linkName.isEmpty() || !isValidLink(linkValue.trim())) {
	        SessionErrors.add(request, INVALID_URL);
	        try {
	        	if (viewLinksURL != null) {
	        		response.sendRedirect(viewLinksURL);
	        	}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				log.error("Error with the redirect", e);
			}
	        return;
	    }
	    if (linkValue.startsWith(WWW)) {
	        linkValue = HTTPS + linkValue;
	    }
	    
	    try {
	        if (linkId.isEmpty()) {
	        	LinkMenuLocalServiceUtil.addLink(0, linkValue, linkName, iconName, Integer.valueOf(position), context);
	            
	            SessionMessages.add(request, ADDED_LINK);
	            CacheRegistryUtil.clear();
	        } else {
	        	LinkMenuLocalServiceUtil.addLink(Long.valueOf(linkId), linkValue, linkName, iconName, Integer.valueOf(position), context);
	            SessionMessages.add(request, EDITED_LINK);
	            CacheRegistryUtil.clear();
	        }
	    } catch (Exception e) {
	        log.error("Error creating/getting the link", e);
	        return;
	    }
	    try {
	    	if (viewLinksURL != null) {
        	response.sendRedirect(viewLinksURL);
        	}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("Error with the redirect", e);
		}
	}


	// Method to validate the urls
	private static boolean isValidLink(String url) {
	    return isValidLinkAbsolute(url) || isValidLinkRelative(url);
	}

	// Method to validate the absolute urls 
	public static boolean isValidLinkAbsolute(String url) { 
	    return Pattern.matches(ABSOLUTE_URL_REGEX, url);
	}
    
    // Method to validate relative urls
	public static boolean isValidLinkRelative(String url) {
	    return Pattern.matches(RELATIVE_URL_REGEX, url) || Pattern.matches(RELATIVE_URL_REGEX_2, url);
	}

    // method to remove a link from the database
	@ProcessAction(name = "deleteLink")
	public void deleteLink(ActionRequest request, ActionResponse response) {
	String linkId = request.getParameter("deleteLink");
	LinkMenu link;
	try {
		link = LinkMenuLocalServiceUtil.getLinkMenu(Long.valueOf(linkId));
		LinkMenuLocalServiceUtil.deleteLinkMenu(link);
		CacheRegistryUtil.clear();
	} catch (NumberFormatException | PortalException e) {
		// TODO Auto-generated catch block
		log.error("Error in deleting a link", e);
	}
	SessionMessages.add(request, DELETED_LINK);
	}
}