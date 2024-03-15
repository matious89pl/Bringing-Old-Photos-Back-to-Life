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

package com.everis.rec.service.activity.logs.service.impl;

import com.everis.rec.service.activity.logs.model.RecLog;
import com.everis.rec.service.activity.logs.service.RecLogLocalServiceUtil;
import com.everis.rec.service.activity.logs.service.base.RecLogLocalServiceBaseImpl;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.aop.AopService;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * The implementation of the rec log local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * <code>com.everis.rec.service.activity.logs.service.RecLogLocalService</code>
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RecLogLocalServiceBaseImpl
 */
@Component(property = "model.class.name=com.everis.rec.service.activity.logs.model.RecLog", service = AopService.class)
public class RecLogLocalServiceImpl extends RecLogLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use
	 * <code>com.everis.rec.service.activity.logs.service.RecLogLocalService</code>
	 * via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use
	 * <code>com.everis.rec.service.activity.logs.service.RecLogLocalServiceUtil</
	 * code>.
	 */

	public List<RecLog> findAll() {

		return recLogPersistence.findAll();

	}

	public List<RecLog> findByJournaArticleClassPK(long journalArticleClassPK) {
		return recLogPersistence.findByClassPK(journalArticleClassPK);

	}

	public List<RecLog> findByJournaArticleClassPK(long journalArticleClassPK, int start, int end) {

		return recLogPersistence.findByClassPK(journalArticleClassPK, start, end);

	}

	public RecLog registerActivityLog(long groupId, long journalArticleResourcePK, String type, String textMessage) {

		RecLog recActivityLogs;
		long activityLogId = CounterLocalServiceUtil.increment(RecLog.class.getName());

		recActivityLogs = RecLogLocalServiceUtil.createRecLog(activityLogId);
		recActivityLogs.setGroupId(groupId);
		recActivityLogs.setCreateDate(new Date());
		recActivityLogs.setClassName("com.liferay.workflow");
		recActivityLogs.setClassPK(journalArticleResourcePK);
		recActivityLogs.setType(type);
		recActivityLogs.setLogMessage(textMessage);

		RecLogLocalServiceUtil.addRecLog(recActivityLogs);

		return recActivityLogs;
	}

}