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

package rec.file.conf.service.service.impl;

import com.liferay.portal.aop.AopService;

import org.osgi.service.component.annotations.Component;

import rec.file.conf.service.model.File_Conf;
import rec.file.conf.service.service.base.File_ConfLocalServiceBaseImpl;

/**
 * The implementation of the file_ conf local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>rec.file.conf.service.service.File_ConfLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see File_ConfLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=rec.file.conf.service.model.File_Conf",
	service = AopService.class
)
public class File_ConfLocalServiceImpl extends File_ConfLocalServiceBaseImpl {

	public File_Conf findFileConfByName(String docConfName) {
		return file_ConfPersistence.fetchBydocConfName(docConfName); 
	}

	public File_Conf findBydocFileType(String docFileType) {
		return file_ConfPersistence.fetchBydocFileType(docFileType);
	}
	
}