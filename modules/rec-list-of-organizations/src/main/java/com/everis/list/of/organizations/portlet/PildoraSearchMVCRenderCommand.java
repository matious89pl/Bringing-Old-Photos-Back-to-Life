package com.everis.list.of.organizations.portlet;

import com.everis.list.of.organizations.constants.RecListOfOrganizationsPortletKeys;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Order;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;


import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
		immediate = true, 
		property = {
			"javax.portlet.name=" + RecListOfOrganizationsPortletKeys.RECLISTOFORGANIZATIONS, 
			"mvc.command.name=/",
			"mvc.command.name=" + RecListOfOrganizationsPortletKeys.VIEW_LANDING	
		}, 
		service = MVCRenderCommand.class
	)
public class PildoraSearchMVCRenderCommand implements MVCRenderCommand {
	
	@Reference
	private Portal _portal;
	private static Log log = LogFactoryUtil.getLog(PildoraSearchMVCRenderCommand.class);

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		log.debug("******** In Render Pildora **********");
		
		// Add assignment list related attributes.
		addAssignmentListAttributes(renderRequest);
		
		// Add Clay management toolbar related attributes.
		addManagementToolbarAttributes(renderRequest, renderResponse);
		
		return "/view.jsp";
	}
	
	private void addManagementToolbarAttributes(
			RenderRequest renderRequest, RenderResponse renderResponse) {
			LiferayPortletRequest liferayPortletRequest = _portal.getLiferayPortletRequest(renderRequest);
			LiferayPortletResponse liferayPortletResponse = _portal.getLiferayPortletResponse(renderResponse);
			ManagementToolbarDisplayContext assignmentsManagementToolbarDisplayContext = new ManagementToolbarDisplayContext(_portal.getHttpServletRequest(renderRequest), liferayPortletRequest, liferayPortletResponse);
			renderRequest.setAttribute("assignmentsManagementToolbarDisplayContext", assignmentsManagementToolbarDisplayContext);
	}
	
	private void addAssignmentListAttributes(RenderRequest renderRequest) {	
		log.debug("******** START - addAssignmentListAttributes **********");
		
		String keywords = ParamUtil.getString(renderRequest, "keywords");	
		log.debug("----> keywords: " + keywords);
		
		long companyId = _portal.getCompanyId(renderRequest);
		
		// Resolve start and end for the search.
		int currentPage = ParamUtil.getInteger(renderRequest, SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_CUR);
		log.debug("----> currentPage: " + currentPage);
		int delta = 50;//ParamUtil.getInteger(renderRequest, SearchContainer.DEFAULT_DELTA_PARAM, SearchContainer.DEFAULT_DELTA);
		log.debug("----> delta: " + delta);
		
		int start = ((currentPage > 0) ? (currentPage - 1) : 0) * delta;
		log.debug("----> start: " + start);
		int end = start + delta;
		log.debug("----> end: " + end);
		
		String filterByParentName = ParamUtil.getString(renderRequest, "filterByParentName");
		log.debug("----> filterByParentName: " + filterByParentName);
		String filterByName = ParamUtil.getString(renderRequest, "filterByName");
		log.debug("----> filterByName: " + filterByName);
		String orderByCol =	ParamUtil.getString(renderRequest, "orderByCol", "name");
		String orderByType = ParamUtil.getString(renderRequest, "orderByType", "asc");
		log.debug("----> orderByCol: " + orderByCol + "----> orderByType: " + orderByType);
		
		long organisationListCount = 0;
	
		List<Organization> organisationList = new ArrayList<Organization>();
		List<Organization> organisationListTotal = new ArrayList<Organization>();
		DynamicQuery dynamicQueryOrgs = _organizationLocalService.dynamicQuery();
		
		if(Validator.isNotNull(filterByName)&&!StringUtil.equals(filterByName, "none")) {
			dynamicQueryOrgs.add(PropertyFactoryUtil.forName("name").eq(filterByName)); 
		}
		
		if(Validator.isNotNull(filterByParentName)&&!StringUtil.equals(filterByParentName, "none")) {
			Organization parentOrg = OrganizationLocalServiceUtil.fetchOrganization(companyId, filterByParentName);
			if (Validator.isNotNull(parentOrg)) {
				dynamicQueryOrgs.add(PropertyFactoryUtil.forName("parentOrganizationId").eq(parentOrg.getOrganizationId()));
			}	 
		}
		
		if(Validator.isNotNull(keywords)&&keywords.length()>0) {
			dynamicQueryOrgs.add(RestrictionsFactoryUtil.like("name",new StringBuilder("%").append(keywords).append("%").toString()));
		}
		
		
		
		if(StringUtil.equalsIgnoreCase(orderByCol, "none")) {
			orderByCol="name";
		}
		
		if(StringUtil.equalsIgnoreCase(orderByType, "asc")) {
			Order order = OrderFactoryUtil.asc(orderByCol);		
			dynamicQueryOrgs.addOrder(order);
		}
		else {
			Order order = OrderFactoryUtil.desc(orderByCol);		
			dynamicQueryOrgs.addOrder(order);
		}		
		
		organisationListTotal = _organizationLocalService.dynamicQuery(dynamicQueryOrgs);
		organisationListCount = organisationListTotal.size();
		log.debug("----> organisationListCount: " + organisationListCount);
		
		organisationList = _organizationLocalService.dynamicQuery(dynamicQueryOrgs, start, end);
		log.debug("----> organisationList: " + organisationList);

		renderRequest.getPortletSession().setAttribute("organisationList", organisationList, PortletSession.APPLICATION_SCOPE);
		renderRequest.getPortletSession().setAttribute("organisationListCount", organisationListCount, PortletSession.APPLICATION_SCOPE);
		
		log.debug("******** END - addAssignmentListAttributes **********");
		
	}
	
	@Reference
	protected OrganizationLocalService _organizationLocalService;
}
