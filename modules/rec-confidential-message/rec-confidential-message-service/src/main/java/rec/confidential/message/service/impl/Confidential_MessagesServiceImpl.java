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
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Date;

import org.osgi.service.component.annotations.Component;

import rec.confidential.message.model.Confidential_Messages;
import rec.confidential.message.service.Confidential_MessagesLocalServiceUtil;
import rec.confidential.message.service.base.Confidential_MessagesServiceBaseImpl;

/**
 * The implementation of the confidential_ messages remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>rec.confidential.message.service.Confidential_MessagesService</code> interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Confidential_MessagesServiceBaseImpl
 */
@Component(
	property = {
		"json.web.service.context.name=confidential_messages",
		"json.web.service.context.path=Confidential_Messages"
	},
	service = AopService.class
)
public class Confidential_MessagesServiceImpl extends Confidential_MessagesServiceBaseImpl {
	
	private final Log logger = LogFactoryUtil.getLog(Confidential_MessagesServiceImpl.class);
	
	@JSONWebService(value = "add_confidential_message", method = "POST")
	public void addConfidentialMessage(long messageId, long companyId) {
		logger.debug("START - addConfidentialMessage with messageId: " + messageId + " and companyId: " + companyId);
		try {
			logger.debug("Creating Confidential_Messages object");
			Confidential_Messages cf_message =  Confidential_MessagesLocalServiceUtil.createConfidential_Messages(messageId);
			cf_message.setCompanyId(companyId);
			cf_message.setCreateDate(new Date());
			logger.debug("Confidential_Messages object created properly: " + cf_message);
			Confidential_MessagesLocalServiceUtil.addConfidential_Messages(cf_message);
			logger.debug("Confidential_Messages object added properly to database: " + cf_message);
		} catch (Exception e) {
			logger.error("ERROR - Creating Confidential_Messages object", e);
		}
		logger.debug("END - addConfidentialMessage with messageId: " + messageId + " and companyId: " + companyId);
	}
	
}