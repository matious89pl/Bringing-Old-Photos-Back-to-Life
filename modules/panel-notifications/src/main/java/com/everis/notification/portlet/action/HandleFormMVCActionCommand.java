package com.everis.notification.portlet.action;


import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.everis.messages.service.builder.model.Messages;
import com.everis.messages.service.builder.service.MessagesLocalServiceUtil;
import com.everis.panel.notifications.constants.PanelNotificationsPortletKeys;


import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;

@Component(
    immediate = true,
    property = {
        "javax.portlet.name=" + PanelNotificationsPortletKeys.PANELNOTIFICATIONS,
        "mvc.command.name=saveEmailNot"
    },
    service = MVCActionCommand.class
)
public class HandleFormMVCActionCommand implements MVCActionCommand {
    @Override
    public boolean processAction(
            ActionRequest actionRequest, ActionResponse actionResponse)
        throws PortletException {

        _handleActionCommand(actionRequest);

        return true;
    }
    

    private void _handleActionCommand(ActionRequest actionRequest) {
    	
    	//Insert subject and body in the database

        System.out.println("Form Executed");

        String subject = actionRequest.getParameter("subject");
              
        System.out.println("Subject:" + subject);
        
        String body = actionRequest.getParameter("body");
        
        System.out.println("Body: " + body);
        
        long emailId = CounterLocalServiceUtil.increment(Messages.class.getName());
        
        Messages notification =  MessagesLocalServiceUtil.createMessages(emailId);    
        
        notification.setSubject(subject);
        notification.setBody(body);
        
        System.out.println(notification);
        
        MessagesLocalServiceUtil.addMessages(notification);
          
    }
}
