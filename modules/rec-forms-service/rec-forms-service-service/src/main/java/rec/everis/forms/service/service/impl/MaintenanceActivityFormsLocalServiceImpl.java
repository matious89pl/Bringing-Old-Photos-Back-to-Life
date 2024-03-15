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

package rec.everis.forms.service.service.impl;

import com.liferay.portal.aop.AopService;

import java.util.List;

import org.osgi.service.component.annotations.Component;

import rec.everis.forms.service.model.MaintenanceActivityForms;
import rec.everis.forms.service.service.base.MaintenanceActivityFormsLocalServiceBaseImpl;

/**
 * The implementation of the maintenance activity forms local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>rec.everis.forms.service.service.MaintenanceActivityFormsLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MaintenanceActivityFormsLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=rec.everis.forms.service.model.MaintenanceActivityForms",
	service = AopService.class
)
public class MaintenanceActivityFormsLocalServiceImpl
	extends MaintenanceActivityFormsLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>rec.everis.forms.service.service.MaintenanceActivityFormsLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>rec.everis.forms.service.service.MaintenanceActivityFormsLocalServiceUtil</code>.
	 */

public List<MaintenanceActivityForms> findByformInstanceRecordVersionId(long formInstanceRecordVersionId)
{
	return maintenanceActivityFormsPersistence.findByformInstanceRecordVersionId(formInstanceRecordVersionId);
}


public List<MaintenanceActivityForms> findByGroupId(long groupId)
{
	return maintenanceActivityFormsPersistence.findByGroupId(groupId);
}



}