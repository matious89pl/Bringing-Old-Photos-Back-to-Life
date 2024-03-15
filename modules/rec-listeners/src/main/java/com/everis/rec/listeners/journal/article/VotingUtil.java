package com.everis.rec.listeners.journal.article;

import com.everis.messages.service.builder.service.MessagesLocalServiceUtil;
import com.everis.rec.listeners.constants.RecListenersPortletKeys;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

import java.text.ParseException;

public class VotingUtil {

	private final Log logger = LogFactoryUtil.getLog(VotingUtil.class);

	public void checkUpdatesAndApplyLogic(JournalArticle model) {
		Double version = 1.0;
		try {

			if (model.getVersion() != version) {
				logger.info(model.getVersion() + " - " + version);
				logger.debug("model.getLayoutUuid()... " + model.getLayoutUuid());

				if (statusHasBeenUpdated(model)) {
					logger.info("Status Voting has been updated");
					logger.debug("model.getArticleId(): " + model.getArticleId());

					String status = getNodeText(model, "StatusVoting");

					if (status.equals("In progress")) {
						MessagesLocalServiceUtil.sendNotification29(model.getUserId(), model.getResourcePrimKey(),
								model.getGroupId());
					} else if (status.equals("Completed")) {

						MessagesLocalServiceUtil.sendNotification30(model.getUserId(), model.getResourcePrimKey(),
								model.getGroupId());

					}
				}
			}

			else {

			}
		} catch (Exception e) {
			logger.error("Error getting reading Journal Article Content... " + e);
		}
	}

	private boolean statusHasBeenUpdated(JournalArticle model) throws DocumentException, ParseException {
		logger.debug("statusHasBeenUpdated... starting method");
		boolean hasBeenUpdated = false;
		JournalArticle previousModel = JournalArticleLocalServiceUtil.getPreviousApprovedArticle(model);
		if (!isCustomUpdate(previousModel, model)) {
			logger.debug("statusHasBeenUpdated... in isCustomUpdate");
			String previousDocuments = getNodeText(previousModel, RecListenersPortletKeys.STATUS_VOTING);
			String currentDocuments = getNodeText(model, RecListenersPortletKeys.STATUS_VOTING);
			logger.debug("previousDocuments: " + previousDocuments + " -- currentDocuments: " + currentDocuments);
			hasBeenUpdated = (!previousDocuments.equalsIgnoreCase(currentDocuments) ? true : false);
		}
		logger.debug("statusHasBeenUpdated: " + hasBeenUpdated);
		return hasBeenUpdated;
	}

	private boolean isCustomUpdate(JournalArticle previousModel, JournalArticle newModel) {
		Double oldVersion = previousModel.getVersion();
		Double newVersion = newModel.getVersion();
		logger.debug("oldVersion: " + oldVersion + " , newVersion: " + newVersion);
		return oldVersion.equals(newVersion) ? true : false;
	}

	private String getNodeText(JournalArticle model, String fieldName) throws DocumentException {
		logger.trace("model: " + model.getArticleId());
		logger.trace("getContent: " + model.getContent());
		Document document = SAXReaderUtil.read(model.getContent());
		logger.trace("document: " + document.getText());
		Node node = document.selectSingleNode("//root//dynamic-element[@name='" + fieldName + "']//dynamic-content");
		if (node == null) {
			logger.debug("There is no field on the structure for the following fieldName");
			return "";
		} else {
			logger.debug("node.getText(): " + node.getText());
			return node.getText();
		}
	}

}
