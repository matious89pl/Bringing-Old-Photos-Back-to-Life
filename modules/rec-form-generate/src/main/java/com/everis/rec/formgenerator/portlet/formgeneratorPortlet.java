package com.everis.rec.formgenerator.portlet;

import com.everis.rec.formgenerator.constants.formgeneratorPortletKeys;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.dynamic.data.mapping.form.values.factory.DDMFormValuesFactory;
import com.liferay.dynamic.data.mapping.model.DDMFormInstance;
import com.liferay.dynamic.data.mapping.model.DDMFormInstanceRecord;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMFormInstanceLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ProcessAction;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;


/**
 * @author Manish Kumar Jaiswal
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=formgenerator",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + formgeneratorPortletKeys.FORMGENERATOR,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class formgeneratorPortlet extends MVCPortlet {
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException 
	{
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		// TODO Auto-generated method stub
		super.render(renderRequest, renderResponse);
	}
	
	

	@ProcessAction(name = "createForms")
	public void createForms(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException, PortalException {
		System.out.println("IN CREATE FORMS---------------------------");
		 ServiceContext serviceContext = null;

			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
			
			
			long formInstanceId1 = ParamUtil.getLong(actionRequest, "formInstanceId1");
			long formInstanceId2 = ParamUtil.getLong(actionRequest, "formInstanceId2");
			long formInstanceId3 = ParamUtil.getLong(actionRequest, "formInstanceId3");
			long formInstanceId4 = ParamUtil.getLong(actionRequest, "formInstanceId4");
			
			
			
			long ddmStructureId1;
			long ddmStructureId2;
			long ddmStructureId3;
			long ddmStructureId4;
			
			
				ddmStructureId1 = DDMFormInstanceLocalServiceUtil.getDDMFormInstance(formInstanceId1).getStructureId();
				 ddmStructureId2 = DDMFormInstanceLocalServiceUtil.getDDMFormInstance(formInstanceId2).getStructureId();
				 ddmStructureId3 = DDMFormInstanceLocalServiceUtil.getDDMFormInstance(formInstanceId3).getStructureId();
				 ddmStructureId4 = DDMFormInstanceLocalServiceUtil.getDDMFormInstance(formInstanceId4).getStructureId();
				
				 System.out.println("ddmStructureId1  ddmStructureId2 ddmStructureId3 ddmStructureId4"+ddmStructureId1+" "+ddmStructureId2+" "+ddmStructureId3+" "+ddmStructureId4 );
			
			long adminGroupId = ParamUtil.getLong(actionRequest, "adminGroupId");
			long inGroupId  = ParamUtil.getLong(actionRequest, "inGroupId");
			System.out.println("INNNN GROUP ID IS--------------------------"+inGroupId);
		
			
			try {
	            serviceContext = ServiceContextFactory.getInstance(
	                DDMFormInstanceRecord.class.getName(), actionRequest);
	        } catch (PortalException e) {
	            e.printStackTrace();
	        }
			
			List<Group> grouplist = GroupLocalServiceUtil.getGroups(themeDisplay.getCompanyId(), GroupConstants.ANY_PARENT_GROUP_ID, true);
			
			if(inGroupId == 0)
			{
				for (Group group : grouplist) {
					System.out.println("GROUP ID IS--------------------------"+group.getGroupId());
					
					 if(group.getGroupId() != adminGroupId)
					 {
						 formCreation1(actionRequest, themeDisplay, serviceContext, group.getGroupId(), ddmStructureId1);
							formCreation2(actionRequest, themeDisplay, serviceContext, group.getGroupId(), ddmStructureId2);
							formCreation3(actionRequest, themeDisplay, serviceContext, group.getGroupId(), ddmStructureId3);
							formCreation4(actionRequest, themeDisplay, serviceContext, group.getGroupId(), ddmStructureId4);
					 }
					
				
				}
			}
			else
			{
				System.out.println("GROUP ID IS only one --------------------------"+inGroupId);
				formCreation1(actionRequest, themeDisplay, serviceContext, inGroupId, ddmStructureId1);
				formCreation2(actionRequest, themeDisplay, serviceContext, inGroupId, ddmStructureId2);
				formCreation3(actionRequest, themeDisplay, serviceContext, inGroupId, ddmStructureId3);
				formCreation4(actionRequest, themeDisplay, serviceContext, inGroupId, ddmStructureId4);
			}
			
			
			
		
	}




	private void formCreation1(ActionRequest actionRequest, ThemeDisplay themeDisplay, ServiceContext serviceContext,
			long inGroupId, long ddmStructureId) {
		// Initialize form instance
		long ddmInstanceId = CounterLocalServiceUtil.increment(DDMFormInstance.class.getName());
		DDMFormInstance ddmFormInstance = DDMFormInstanceLocalServiceUtil.createDDMFormInstance(ddmInstanceId);
		ddmFormInstance.setStructureId(ddmStructureId);

		// Retrieve the element set
		DDMStructure ddmStructure = null;
		try {
			ddmStructure = DDMStructureLocalServiceUtil.copyStructure(themeDisplay.getUserId(), ddmStructureId, serviceContext);
			
		} catch (PortalException e) {
			e.printStackTrace();
		}

		// Convert it into formValues needed for addFormInstance
		com.liferay.dynamic.data.mapping.model.DDMForm  ddmForm = ddmStructure.getDDMForm();
		DDMFormValues ddmFormValues = _ddmFormValuesFactory.create(actionRequest, ddmForm);
		
		
		// Set name and description for the new form
		Map<Locale, String> name = new HashMap<Locale, String>();
		name.put(LocaleUtil.UK, "Maintenance of Qualification: Annual Statement and Smart Meter Installation Schedule Self Declaration");
		Map<Locale, String> description = new HashMap<Locale, String>();
		description.put(LocaleUtil.UK, " ");
		
		
		// Create the form
		try {
			DDMFormInstance ddmFormInstanceone = DDMFormInstanceLocalServiceUtil.addFormInstance(themeDisplay.getUserId(), inGroupId, ddmStructure.getStructureId(), name, description, ddmFormValues, serviceContext);
//			PUBLISHING BY JSON IN ddmforminstance
		} catch (PortalException e) {
			e.printStackTrace();
		}
	}
	
	private void formCreation2(ActionRequest actionRequest, ThemeDisplay themeDisplay, ServiceContext serviceContext,
			long inGroupId, long ddmStructureId) {
		// Initialize form instance
		long ddmInstanceId = CounterLocalServiceUtil.increment(DDMFormInstance.class.getName());
		DDMFormInstance ddmFormInstance = DDMFormInstanceLocalServiceUtil.createDDMFormInstance(ddmInstanceId);
		ddmFormInstance.setStructureId(ddmStructureId);

		// Retrieve the element set
		DDMStructure ddmStructure = null;
		try {
			ddmStructure = DDMStructureLocalServiceUtil.copyStructure(themeDisplay.getUserId(), ddmStructureId, serviceContext);
			
		} catch (PortalException e) {
			e.printStackTrace();
		}

		// Convert it into formValues needed for addFormInstance
		com.liferay.dynamic.data.mapping.model.DDMForm  ddmForm = ddmStructure.getDDMForm();
		DDMFormValues ddmFormValues = _ddmFormValuesFactory.create(actionRequest, ddmForm);
		
		
		// Set name and description for the new form
		Map<Locale, String> name = new HashMap<Locale, String>();
		name.put(LocaleUtil.UK, "Maintenance of Qualification: System or Process Change Disclosure");
		Map<Locale, String> description = new HashMap<Locale, String>();
		description.put(LocaleUtil.UK, " ");
		
		
		// Create the form
		try {
			DDMFormInstance ddmFormInstanceone = DDMFormInstanceLocalServiceUtil.addFormInstance(themeDisplay.getUserId(), inGroupId, ddmStructure.getStructureId(), name, description, ddmFormValues, serviceContext);
		} catch (PortalException e) {
			e.printStackTrace();
		}
	}
	
	
	private void formCreation3(ActionRequest actionRequest, ThemeDisplay themeDisplay, ServiceContext serviceContext,
			long inGroupId, long ddmStructureId) {
		// Initialize form instance
		long ddmInstanceId = CounterLocalServiceUtil.increment(DDMFormInstance.class.getName());
		DDMFormInstance ddmFormInstance = DDMFormInstanceLocalServiceUtil.createDDMFormInstance(ddmInstanceId);
		ddmFormInstance.setStructureId(ddmStructureId);

		// Retrieve the element set
		DDMStructure ddmStructure = null;
		try {
			ddmStructure = DDMStructureLocalServiceUtil.copyStructure(themeDisplay.getUserId(), ddmStructureId, serviceContext);
			
		} catch (PortalException e) {
			e.printStackTrace();
		}

		// Convert it into formValues needed for addFormInstance
		com.liferay.dynamic.data.mapping.model.DDMForm  ddmForm = ddmStructure.getDDMForm();
		DDMFormValues ddmFormValues = _ddmFormValuesFactory.create(actionRequest, ddmForm);
		
		
		// Set name and description for the new form
		Map<Locale, String> name = new HashMap<Locale, String>();
		name.put(LocaleUtil.UK, "Maintenance of Qualification: External Assessment");
		Map<Locale, String> description = new HashMap<Locale, String>();
		description.put(LocaleUtil.UK, " ");
		
		
		// Create the form
		try {
			DDMFormInstance ddmFormInstanceone = DDMFormInstanceLocalServiceUtil.addFormInstance(themeDisplay.getUserId(), inGroupId, ddmStructure.getStructureId(), name, description, ddmFormValues, serviceContext);
		} catch (PortalException e) {
			e.printStackTrace();
		}
	}
	
	
	private void formCreation4(ActionRequest actionRequest, ThemeDisplay themeDisplay, ServiceContext serviceContext,
			long inGroupId, long ddmStructureId) {
		// Initialize form instance
		long ddmInstanceId = CounterLocalServiceUtil.increment(DDMFormInstance.class.getName());
		DDMFormInstance ddmFormInstance = DDMFormInstanceLocalServiceUtil.createDDMFormInstance(ddmInstanceId);
		ddmFormInstance.setStructureId(ddmStructureId);

		// Retrieve the element set
		DDMStructure ddmStructure = null;
		try {
			ddmStructure = DDMStructureLocalServiceUtil.copyStructure(themeDisplay.getUserId(), ddmStructureId, serviceContext);
			
		} catch (PortalException e) {
			e.printStackTrace();
		}

		// Convert it into formValues needed for addFormInstance
		com.liferay.dynamic.data.mapping.model.DDMForm  ddmForm = ddmStructure.getDDMForm();
		DDMFormValues ddmFormValues = _ddmFormValuesFactory.create(actionRequest, ddmForm);
		
		
		// Set name and description for the new form
		Map<Locale, String> name = new HashMap<Locale, String>();
		name.put(LocaleUtil.UK, "Maintenance of Qualification: Compliance Statement");
		Map<Locale, String> description = new HashMap<Locale, String>();
		description.put(LocaleUtil.UK, " ");
		
		
		// Create the form
		try {
			DDMFormInstance ddmFormInstanceone = DDMFormInstanceLocalServiceUtil.addFormInstance(themeDisplay.getUserId(), inGroupId, ddmStructure.getStructureId(), name, description, ddmFormValues, serviceContext);
		} catch (PortalException e) {
			e.printStackTrace();
		}
	}
	
	

	
	
	
	
	@Reference(unbind = "-")
	protected void setDDMFormValuesFactory(
		DDMFormValuesFactory ddmFormValuesFactory) {

		_ddmFormValuesFactory = ddmFormValuesFactory;
	}
	
	private DDMFormValuesFactory _ddmFormValuesFactory;

}