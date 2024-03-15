package com.everis.panel.notifications.portlet;

import com.everis.messages.service.builder.model.Messages;
import com.everis.messages.service.builder.service.MessagesLocalServiceUtil;
import com.everis.panel.notifications.constants.PanelNotificationsPortletKeys;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ProcessAction;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author ccaravac
 */
@Component(
        immediate = true,
        property = {
                "com.liferay.portlet.add-default-resource=true",
                "com.liferay.portlet.display-category=category.hidden",
                "com.liferay.portlet.header-portlet-css=/css/main.css",
                "com.liferay.portlet.layout-cacheable=true",
                "com.liferay.portlet.private-request-attributes=false",
                "com.liferay.portlet.private-session-attributes=false",
                "com.liferay.portlet.render-weight=50",
                "com.liferay.portlet.use-default-template=true",
                "javax.portlet.display-name=PanelNotification",
                "javax.portlet.expiration-cache=0",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/html/view.jsp",
                "javax.portlet.name=" + PanelNotificationsPortletKeys.PANELNOTIFICATIONS,
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user",

        },
        service = Portlet.class
)

public class PanelNotificationsPortlet extends MVCPortlet {

    private static final Log logger = LogFactoryUtil.getLog(PanelNotificationsPortlet.class);

    @Override
    public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException, java.io.IOException {

        ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

        long companyId = themeDisplay.getCompanyId();

        List<Messages> messages = MessagesLocalServiceUtil.findByCompanyId(companyId);
        
        logger.info("Messages " + messages);
        
        super.doView(renderRequest, renderResponse);
        
        
    }

    @ProcessAction(name = "editMessage")
    public void displayMessageEdit(ActionRequest request, ActionResponse response) throws IOException, PortletException, PortalException {
        String CMD = request.getParameter("CMD");
        Messages message = null;
        if (CMD.equals("edit")) {
            final long id = Long.valueOf(request.getParameter("messageId"));
            message = MessagesLocalServiceUtil.getMessages(id);

        }
        request.setAttribute("messages", message);
        response.setRenderParameter("mvcPath", "/html/edit.jsp");
    }

    @ProcessAction(name = "saveMessage")
    public void saveMessage(ActionRequest request, ActionResponse response) throws IOException, PortletException, PortalException {

        String messageId = request.getParameter("idMessage");

        if (messageId.isEmpty()) {

            logger.info("Insert ");
            long emailId = CounterLocalServiceUtil.increment(Messages.class.getName());
            Messages notification = MessagesLocalServiceUtil.createMessages(Long.valueOf(emailId));

            String name = request.getParameter("name");
            String subject = request.getParameter("subject");
            String body = request.getParameter("body");

            notification.setName(name);
            notification.setSubject(subject);
            notification.setBody(body);

            MessagesLocalServiceUtil.addMessages(notification);

        } else {

            logger.info("Update ");
            Messages notification = MessagesLocalServiceUtil.getMessages(Long.valueOf(messageId));

            String name = request.getParameter("name");
            String subject = request.getParameter("subject");
            String body = request.getParameter("body");

            notification.setName(name);
            notification.setSubject(subject);
            notification.setBody(body);

            MessagesLocalServiceUtil.updateMessages(notification);

        }
    }

    @ProcessAction(name = "checkStatus")
    public void checkStatus(ActionRequest request, ActionResponse response) throws IOException, PortletException, PortalException {

        logger.info("Check Status ");
        String messageId = request.getParameter("checkStatus");

        Messages notification = MessagesLocalServiceUtil.getMessages(Long.valueOf(messageId));

        String status = request.getParameter("status");

        if (status.equals("true")) {
            status = "false";
            notification.setStatus(Boolean.valueOf(status));
            MessagesLocalServiceUtil.updateMessages(notification);
        } else if (status.equals("false")) {
            status = "true";
            notification.setStatus(Boolean.valueOf(status));
            MessagesLocalServiceUtil.updateMessages(notification);
        }

    }

    @ProcessAction(name = "deleteMessage")
    public void deleteMessage(ActionRequest request, ActionResponse response) throws IOException, PortletException, PortalException {

        String messageId = request.getParameter("deleteMessage");

        logger.info("Delete ");
        Messages notification = MessagesLocalServiceUtil.getMessages(Long.valueOf(messageId));

        MessagesLocalServiceUtil.deleteMessages(notification);
        
    }

}