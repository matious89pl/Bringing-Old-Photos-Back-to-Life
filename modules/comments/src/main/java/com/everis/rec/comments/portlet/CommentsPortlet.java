package com.everis.rec.comments.portlet;

import com.everis.rec.comments.constants.CommentsPortletKeys;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

/**
 * @author lperezve
 */

@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=Comments", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp", "javax.portlet.name=" + CommentsPortletKeys.COMMENTS,
		"javax.portlet.resource-bundle=content.Language", "javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.expiration-cache=0", "com.liferay.portlet.scopeable=true",
		"javax.portlet.supports.mime-type=text/html" }, service = Portlet.class)
public class CommentsPortlet extends MVCPortlet {

	static final Log logger = LogFactoryUtil.getLog(CommentsPortlet.class);

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		logger.info("doView... Comments");

		HttpServletRequest request = PortalUtil.getHttpServletRequest(renderRequest);
		logger.info("request... " + request.getParameter("className") + " - " + request.getParameter("classPK"));

		renderRequest.setAttribute("className", request.getParameter("className"));
		renderRequest.setAttribute("classPK", request.getParameter("classPK"));		
		super.doView(renderRequest, renderResponse);
	}

}