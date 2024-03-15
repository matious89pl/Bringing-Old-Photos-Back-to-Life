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

package rec.customnotification.service.impl;

import com.liferay.portal.aop.AopService;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;

import rec.customnotification.exception.NoSuchNotificationRpaException;
import rec.customnotification.model.NotificationRpa;
import rec.customnotification.service.NotificationRpaLocalServiceUtil;
import rec.customnotification.service.base.NotificationRpaLocalServiceBaseImpl;

/**
 * The implementation of the notification rpa local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * <code>rec.customnotification.service.NotificationRpaLocalService</code>
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see NotificationRpaLocalServiceBaseImpl
 */
@Component(property = "model.class.name=rec.customnotification.model.NotificationRpa", service = AopService.class)
public class NotificationRpaLocalServiceImpl extends NotificationRpaLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use
	 * <code>rec.customnotification.service.NotificationRpaLocalService</code> via
	 * injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use
	 * <code>rec.customnotification.service.NotificationRpaLocalServiceUtil</code>.
	 */
	public List<NotificationRpa> findAll() {

		return notificationRpaPersistence.findAll();

	}

	public NotificationRpa findByCustomNotificationId(long customNotificationId) throws NoSuchNotificationRpaException {
		return notificationRpaPersistence.findByPrimaryKey(customNotificationId);

	}

	public List<NotificationRpa> findByGroupId(long groupId) {
		return notificationRpaPersistence.findByGroupId(groupId);

	}

	public List<NotificationRpa> findByGroupIdandPlidPage(long groupId, long plidPage) {

		return notificationRpaPersistence.findByGroupIdPlidPage(groupId, plidPage);

	}

	public List<NotificationRpa> findByNotificationTitle(long groupId, long plidPage, String notificationTitle) {

		return notificationRpaPersistence.findByNotificationTitle(groupId, plidPage, notificationTitle);

	}

	public List<NotificationRpa> findByNotificationTitleIndexOf(long groupId, long plidPage, String notificationTitle) {

		List<NotificationRpa> notificationRPA = NotificationRpaLocalServiceUtil.getNotificationRpas(-1, -1);

		for (NotificationRpa notification : notificationRPA) {
			if (notification.getNotificationTitle().indexOf(notificationTitle) > -1)

				return notificationRpaPersistence.findByNotificationTitle(groupId, plidPage, notificationTitle);

		}
		return null;

	}

	public List<NotificationRpa> findByNotificationBody(long groupId, long plidPage, String notificationBody) {

		return notificationRpaPersistence.findByNotificationBody(groupId, plidPage, notificationBody);

	}

	public List<NotificationRpa> findByNotificationBodyIndexOf(long groupId, long plidPage, String notificationBody) {

		List<NotificationRpa> notificationRPA = NotificationRpaLocalServiceUtil.getNotificationRpas(-1, -1);

		for (NotificationRpa notification : notificationRPA) {
			if (notification.getNotificationBody().indexOf(notificationBody) > -1)

				return notificationRpaPersistence.findByNotificationTitle(groupId, plidPage, notificationBody);

		}
		return null;

	}

	public List<NotificationRpa> findByDates(long groupId, long plidPage, Date createDate) {

		return notificationRpaPersistence.findByCreateDate(groupId, plidPage, createDate);

	}

	public List<NotificationRpa> findByTargetName(long groupId, long plidPage, String targetName) {

		return notificationRpaPersistence.findByTargetName(groupId, plidPage, targetName);

	}

	public List<NotificationRpa> findByGroupPlidSearchContainer(long groupId, long plidPage, int start, int end) {

		return notificationRpaPersistence.findByGroupIdPlidPage(groupId, plidPage, start, end);
	}

	public int findByGroupPlidSearchContainterTotal(long groupId, long plidPage) {

		List<NotificationRpa> totalNotificationByGroupandPlid = notificationRpaPersistence
				.findByGroupIdPlidPage(groupId, plidPage);

		return totalNotificationByGroupandPlid.size();
	}

}