package com.everis.rec.cpimpact.service.impl;

import com.everis.rec.cpimpact.service.base.CPImpactLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.osgi.service.component.annotations.Component;

@Component(property = "model.class.name=com.everis.rec.cpimpact.model.CPImpact", service = AopService.class)
public class CPImpactLocalServiceImpl extends CPImpactLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use
	 * <code>com.everis.rec.cpimpact.service.CPImpactLocalService</code> via
	 * injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use
	 * <code>com.everis.rec.cpimpact.service.CPImpactLocalServiceUtil</code>.
	 */

	private final Log logger = LogFactoryUtil.getLog(CPImpactLocalServiceImpl.class);

	
}