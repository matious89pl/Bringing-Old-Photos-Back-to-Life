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

package com.everis.rec.sites.service.impl;

import com.everis.rec.sites.service.base.RecsitesServiceBaseImpl;
import com.liferay.polls.model.PollsQuestion;
import com.liferay.polls.model.PollsVote;
import com.liferay.polls.service.PollsQuestionLocalServiceUtil;
import com.liferay.polls.service.PollsVoteLocalServiceUtil;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.osgi.service.component.annotations.Component;

/**
 * The implementation of the recsites remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * <code>com.everis.rec.sites.service.RecsitesService</code> interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have
 * security checks based on the propagated JAAS credentials because this service
 * can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RecsitesServiceBaseImpl
 */
@Component(property = { "json.web.service.context.name=recsites",
		"json.web.service.context.path=Recsites" }, service = AopService.class)
public class RecsitesServiceImpl extends RecsitesServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use
	 * <code>com.everis.rec.sites.service.RecsitesServiceUtil</code> to access the
	 * recsites remote service.
	 */

	private static final Log logger = LogFactoryUtil.getLog(RecsitesServiceImpl.class);

	@JSONWebService(value = "delete_polls_vote", method = "POST")
	public void deletePollVote(long questionId, long userId) {
		logger.debug("Deleting vote of questionId " + questionId);

		try {
			PollsVote pollsvote = PollsVoteLocalServiceUtil.fetchQuestionUserVote(questionId, userId);

			PollsVoteLocalServiceUtil.deletePollsVote(pollsvote);

			CacheRegistryUtil.clear();

			logger.info("Deleted vote of questionId " + questionId);

		} catch (Exception e) {

			logger.error("ERROR: Deleting vote of questionId " + questionId);
		}

	}

	@JSONWebService(value = "closing_polls_question", method = "POST")
	public void closeVoteByQuestionId(long questionId, long userId) {
		logger.debug("Closing vote by questionId " + questionId);

		try {
			PollsQuestion pollQuestion = PollsQuestionLocalServiceUtil.getPollsQuestion(questionId);

			pollQuestion.setExpirationDate(pollQuestion.getCreateDate());

			PollsQuestionLocalServiceUtil.updatePollsQuestion(pollQuestion);

			CacheRegistryUtil.clear();

			logger.info("Vote " + pollQuestion.getTitle() + " with questionId: " + questionId + " was disabled/closed");

		} catch (Exception e) {

			logger.error("ERROR: Closing vote of questionId " + questionId);
		}

	}

}