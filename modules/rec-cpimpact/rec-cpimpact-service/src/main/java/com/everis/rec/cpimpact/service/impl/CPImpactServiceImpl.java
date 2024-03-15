package com.everis.rec.cpimpact.service.impl;

import com.everis.rec.cpimpact.service.base.CPImpactServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.osgi.service.component.annotations.Component;

@Component(property = { "json.web.service.context.name=cpimpact",
		"json.web.service.context.path=CPImpact" }, service = AopService.class)
public class CPImpactServiceImpl extends CPImpactServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use
	 * <code>com.everis.rec.cpimpact.service.CPImpactServiceUtil</code> to access
	 * the cp impact remote service.
	 */

	private final Log logger = LogFactoryUtil.getLog(CPImpactServiceImpl.class);

}