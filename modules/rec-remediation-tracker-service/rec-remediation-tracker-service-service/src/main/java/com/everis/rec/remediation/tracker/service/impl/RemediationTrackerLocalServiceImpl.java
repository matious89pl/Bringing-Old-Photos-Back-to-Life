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

package com.everis.rec.remediation.tracker.service.impl;

import com.everis.rec.remediation.tracker.model.RemediationTracker;
import com.everis.rec.remediation.tracker.service.base.RemediationTrackerLocalServiceBaseImpl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * The implementation of the remediation tracker local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.everis.rec.remediation.tracker.service.RemediationTrackerLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RemediationTrackerLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=com.everis.rec.remediation.tracker.model.RemediationTracker",
	service = AopService.class
)
public class RemediationTrackerLocalServiceImpl
	extends RemediationTrackerLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>com.everis.rec.remediation.tracker.service.RemediationTrackerLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.everis.rec.remediation.tracker.service.RemediationTrackerLocalServiceUtil</code>.
	 */
	
	
	
	@Override
	public List<RemediationTracker> findByStatus(String status) {
		return this.remediationTrackerPersistence.findByStatus(status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}
}