/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.everis.rec.rfi.logs.asset;

import com.everis.rec.rfi.logs.constants.RecRfiLogsWebPortletKeys;
import com.everis.rec.rfilogs.model.RfiLogs;
import com.everis.rec.rfilogs.service.RfiLogsLocalService;
import com.everis.rec.rfilogs.service.RfiLogsService;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.util.Portal;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;
import javax.portlet.WindowStateException;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jorge Ferrer
 * @author Juan Fernandez
 * @author Raymond Auge
 * @author Sergio Gonzalez
 */
@Component(immediate = true, property = "javax.portlet.name="
		+ RecRfiLogsWebPortletKeys.RECRFILOGSWEB, service = AssetRendererFactory.class)
public class RFILogsAssetRendererFactory extends BaseAssetRendererFactory<RfiLogs> {

	public static final String TYPE = "RFI Log";

	public RFILogsAssetRendererFactory() {
		setClassName(RfiLogs.class.getName());
		setLinkable(true);
		setPortletId(RecRfiLogsWebPortletKeys.RECRFILOGSWEB);
		setSearchable(true);
	}

	@Override
	public AssetRenderer<RfiLogs> getAssetRenderer(long classPK, int type) throws PortalException {

		RfiLogs entry = _rfilogsLocalService.getRfiLogs(classPK);

		RFILogsAssetRenderer rfiLogsAssetRenderer = new RFILogsAssetRenderer(entry);

		rfiLogsAssetRenderer.setAssetRendererType(type);
		rfiLogsAssetRenderer.setServletContext(_servletContext);

		return rfiLogsAssetRenderer;
	}

	@Override
	public String getClassName() {
		return RfiLogs.class.getName();
	}

	@Override
	public String getIconCssClass() {
		return "RFILog";
	}

	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public String getTypeName(Locale locale) {
		return "RFI LOG";
	}

	@Override
	public PortletURL getURLAdd(LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse, long classTypeId) {

		PortletURL portletURL = _portal.getControlPanelPortletURL(liferayPortletRequest,
				getGroup(liferayPortletRequest), RecRfiLogsWebPortletKeys.RECRFILOGSWEB, 0, 0,
				PortletRequest.RENDER_PHASE);

		portletURL.setParameter("mvcRenderCommandName", "/rfi-logs/edit_entry");

		return portletURL;
	}

	@Override
	public PortletURL getURLView(LiferayPortletResponse liferayPortletResponse, WindowState windowState) {

		LiferayPortletURL liferayPortletURL = liferayPortletResponse
				.createLiferayPortletURL(RecRfiLogsWebPortletKeys.RECRFILOGSWEB, PortletRequest.RENDER_PHASE);

		try {
			liferayPortletURL.setWindowState(windowState);
		} catch (WindowStateException wse) {
		}

		return liferayPortletURL;
	}

	@Reference(target = "(osgi.web.symbolicname=com.everis.rec.rfi.logs)", unbind = "-")
	public void setServletContext(ServletContext servletContext) {
		_servletContext = servletContext;
	}

	@Reference(unbind = "-")
	protected void setRfiLogLocalService(RfiLogsLocalService RfiLogsLocalService) {

		_rfilogsLocalService = RfiLogsLocalService;
	}

	@Reference(unbind = "-")
	protected void setRfiLogService(RfiLogsService rfilogService) {
		_rfilogsService = rfilogService;
	}

	private RfiLogsLocalService _rfilogsLocalService;
	private RfiLogsService _rfilogsService;

	@Reference
	private Portal _portal;

	private ServletContext _servletContext;

}