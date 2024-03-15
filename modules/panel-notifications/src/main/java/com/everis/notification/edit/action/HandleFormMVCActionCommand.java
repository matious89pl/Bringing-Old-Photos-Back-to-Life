package com.everis.notification.edit.action;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;

import java.io.IOException;

import com.everis.messages.service.builder.model.Messages;
import com.everis.messages.service.builder.service.MessagesLocalServiceUtil;
import com.everis.panel.notifications.constants.PanelNotificationsPortletKeys;


import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.ProcessAction;

import org.osgi.service.component.annotations.Component;

@Component(
    immediate = true,
    property = {
        "javax.portlet.name=" + PanelNotificationsPortletKeys.PANELNOTIFICATIONS,
        "mvc.command.name=editMessagePrueba"
    },
    service = MVCActionCommand.class
)
public class HandleFormMVCActionCommand implements MVCActionCommand {
    @Override
    public boolean processAction(
            ActionRequest actionRequest, ActionResponse actionResponse)
        throws PortletException {

        try {
			_handleActionCommand(actionRequest, actionResponse);
		} catch (PortalException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return true;
    }
    

    private void _handleActionCommand(ActionRequest actionRequest,  ActionResponse response) throws IOException, PortletException, PortalException {
    	
    	    String CMD = String.valueOf(actionRequest.getAttribute("CMD"));
    	    System.out.println(CMD);
    	    
    	    Messages message = null;
    	    
    	    	final long id = Long.valueOf(actionRequest.getParameter("messageId"));
    		    message = MessagesLocalServiceUtil.getMessages(id);
    	    
    		    actionRequest.setAttribute("messages", message);
    	    response.setRenderParameter("mvcPath", "/html/edit.jsp");
    	}
}
