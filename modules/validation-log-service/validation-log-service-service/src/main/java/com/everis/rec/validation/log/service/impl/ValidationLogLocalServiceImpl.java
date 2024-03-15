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

package com.everis.rec.validation.log.service.impl;

import com.everis.rec.validation.log.model.ValidationLog;
import com.everis.rec.validation.log.service.base.ValidationLogLocalServiceBaseImpl;
import com.everis.rec.validation.log.service.persistence.ValidationLogPersistence;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * The implementation of the validation log local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * <code>com.everis.rec.validation.log.service.ValidationLogLocalService</code>
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ValidationLogLocalServiceBaseImpl
 */
@Component(property = "model.class.name=com.everis.rec.validation.log.model.ValidationLog", service = AopService.class)
public class ValidationLogLocalServiceImpl extends ValidationLogLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use
	 * <code>com.everis.rec.maintenanceactivity.service.
	 * MaintenanceActivityLocalService</code> via injection or a
	 * <code>org.osgi.util.tracker.ServiceTracker</code> or use
	 * <code>com.everis.rec.maintenanceactivity.service.
	 * MaintenanceActivityLocalServiceUtil</code>.
	 */

	private static final Log _log = LogFactoryUtil.getLog(ValidationLogLocalServiceImpl.class.getName());

	public ValidationLog addNewValidationLog(String fileName, String uploadedBy, String uploadedFrom, long folderId, String logReason, long groupId, long companyId) {

		long validationLogId = counterLocalService.increment(ValidationLog.class.getName());
		ValidationLog entry = validationLogPersistence.create(validationLogId);

		entry.setCompanyId(companyId);
		entry.setGroupId(groupId);
		entry.setCreateDate(new Date());
		entry.setFileName(fileName);
		entry.setUploadedBy(uploadedBy);
		entry.setUploadedFrom(uploadedFrom);
		entry.setFolderId(folderId);
		entry.setLogReason(logReason);

		_log.info("ValidationLog: " + entry);

		validationLogPersistence.update(entry);

		return entry;
	}

	public List<ValidationLog> findValidationLogs(int searchContainerStart, int searchContainerEnd) {
		return validationLogPersistence.findAll(searchContainerStart, searchContainerEnd);
	}
}