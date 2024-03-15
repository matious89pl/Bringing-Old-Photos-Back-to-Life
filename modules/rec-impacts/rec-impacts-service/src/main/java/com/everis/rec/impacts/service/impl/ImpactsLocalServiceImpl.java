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

package com.everis.rec.impacts.service.impl;

import com.everis.rec.impacts.model.Impacts;
import com.everis.rec.impacts.service.base.ImpactsLocalServiceBaseImpl;
import com.everis.rec.impacts.service.persistence.ImpactsUtil;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * The implementation of the impacts local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.everis.rec.impacts.service.ImpactsLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ImpactsLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=com.everis.rec.impacts.model.Impacts",
	service = AopService.class
)
public class ImpactsLocalServiceImpl extends ImpactsLocalServiceBaseImpl {

	private final Log logger = LogFactoryUtil.getLog(ImpactsLocalServiceImpl.class);
	
	public List<Impacts> findImpactsByCategory(String category) {
		logger.debug("Getting impacts by category " + category);
		return ImpactsUtil.findByImpactCategory(category);
	}
	
}