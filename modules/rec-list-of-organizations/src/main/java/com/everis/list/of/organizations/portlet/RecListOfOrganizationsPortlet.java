package com.everis.list.of.organizations.portlet;

import com.everis.list.of.organizations.constants.RecListOfOrganizationsPortletKeys;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author asamuilo
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=RecListOfOrganizations", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + RecListOfOrganizationsPortletKeys.RECLISTOFORGANIZATIONS,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class RecListOfOrganizationsPortlet extends MVCPortlet {
	
	private static Log log = LogFactoryUtil.getLog(RecListOfOrganizationsPortlet.class);

	public static String[] columnNames = { "Organisation Name", "Organisation ID", "Parent Organisation ID", "Parent Name", "Repository ID", "Folder ID" };
	public static final String SEPARATOR = ",";
	public static final String FILENAME = "List of Organisations.csv";

	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) {
		log.debug(" **** In Serve Resource Method *****");
		exportToCSV(resourceRequest,resourceResponse);
	
	}

	private void exportToCSV(ResourceRequest resourceRequest, ResourceResponse resourceResponse) {
		try {
				StringBundler sb = new StringBundler();
				for (String columnName : columnNames) {
					sb.append(getCSVFormattedValue(String.valueOf(columnName)));
					sb.append(SEPARATOR);
				}
				sb.setIndex(sb.index() - 1);
				sb.append(CharPool.NEW_LINE);
				List<Organization> organizationList = new ArrayList<Organization>();
				organizationList = (List<Organization>) _OrganizationLocalService.getOrganizations(-1, -1); // all
				log.debug(" Organisation list size: " + organizationList.size()); // organizations

				organizationList.forEach(org -> {
					
					sb.append(getCSVFormattedValue(String.valueOf(org.getName())));
					sb.append(SEPARATOR);

					sb.append(getCSVFormattedValue(String.valueOf(org.getOrganizationId())));
					sb.append(SEPARATOR);

					if (org.getParentOrganizationId() != 0) {
						sb.append(getCSVFormattedValue(String.valueOf(org.getParentOrganizationId())));
						sb.append(SEPARATOR);
					} else {
						sb.append("No Parent Organisation");
						sb.append(SEPARATOR);
					}
					
					try {
						if (org.getParentOrganization() != null) {

							sb.append(getCSVFormattedValue(String.valueOf(org.getParentOrganization().getName())));
							sb.append(SEPARATOR);

						} else {
							sb.append("No Parent Organisation");
							sb.append(SEPARATOR);
						}
					} catch (PortalException e1) {
						e1.printStackTrace();
						sb.append("No Parent Organisation");
						sb.append(SEPARATOR);
					}

					Group grp = org.getGroup();
					if (grp.getSite()) {
						sb.append(getCSVFormattedValue(String.valueOf(grp.getGroupId())));
						sb.append(SEPARATOR);

						try {
							Folder rp_folder = DLAppLocalServiceUtil.getFolder(grp.getGroupId(), 0, "RPA Documents");
							sb.append(getCSVFormattedValue(String.valueOf(rp_folder.getFolderId())));
							sb.append(SEPARATOR);

						} catch (Exception e) {

							sb.append("No Folder ID");
							sb.append(SEPARATOR);
						}
					} else {
						sb.append("No Site");
						sb.append(SEPARATOR);
						try {
							Folder rp_folder = DLAppLocalServiceUtil.getFolder(grp.getGroupId(), 0, "RPA Documents");
							sb.append(getCSVFormattedValue(String.valueOf(rp_folder.getFolderId())));
							sb.append(SEPARATOR);
						} catch (Exception e) {
							sb.append("No Folder ID");
							sb.append(SEPARATOR);
						}
					}

					sb.setIndex(sb.index() - 1);
					sb.append(CharPool.NEW_LINE);
				});

				byte[] bytes = sb.toString().getBytes();
				String contentType = ContentTypes.APPLICATION_TEXT;
				PortletResponseUtil.sendFile(resourceRequest, resourceResponse, FILENAME, bytes, contentType);
		} catch (

		Exception e) {
			log.error("The file was not created : ", e);
		}
		
	}
	
	private String getCSVFormattedValue(String value) {
		StringBundler sb = new StringBundler(3);
		sb.append(CharPool.QUOTE);
		sb.append(StringUtil.replace(value, CharPool.QUOTE, StringPool.DOUBLE_QUOTE));
		sb.append(CharPool.QUOTE);
		return sb.toString();
	}

	@Reference
	protected OrganizationLocalService _OrganizationLocalService;

}
