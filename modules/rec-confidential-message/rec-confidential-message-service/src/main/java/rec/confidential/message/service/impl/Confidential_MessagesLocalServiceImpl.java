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

package rec.confidential.message.service.impl;

import com.liferay.portal.aop.AopService;

import org.osgi.service.component.annotations.Component;

import rec.confidential.message.service.base.Confidential_MessagesLocalServiceBaseImpl;

/**
 * The implementation of the confidential_ messages local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>rec.confidential.message.service.Confidential_MessagesLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Confidential_MessagesLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=rec.confidential.message.model.Confidential_Messages",
	service = AopService.class
)
public class Confidential_MessagesLocalServiceImpl
	extends Confidential_MessagesLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>rec.confidential.message.service.Confidential_MessagesLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>rec.confidential.message.service.Confidential_MessagesLocalServiceUtil</code>.
	 */
}