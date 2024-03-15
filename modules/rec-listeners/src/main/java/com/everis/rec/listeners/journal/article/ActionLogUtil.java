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

public class ActionLogUtil {

	private final Log logger = LogFactoryUtil.getLog(ActionLogUtil.class);

	public void checkUpdatesAndApplyLogic(JournalArticle model) {
		logger.info("model: " + model);
		Double version = 1.0;
		try {
			if (model.getVersion() != version) {
				logger.debug(model.getVersion() + " - " + version);

				if (emailFieldHasBeenUpdated(model)) {

					MessagesLocalServiceUtil.sendNotification38(model.getUserId(), model.getResourcePrimKey(),
							model.getGroupId());
				}

			} else {
				MessagesLocalServiceUtil.sendNotification38(model.getUserId(), model.getResourcePrimKey(),
						model.getGroupId());
			}
		} catch (DocumentException e) {
			logger.error("Error getting reading Journal Article Content... " + e);
		}
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

	private boolean isCustomUpdate(JournalArticle previousModel, JournalArticle newModel) {
		Double oldVersion = previousModel.getVersion();
		Double newVersion = newModel.getVersion();
		logger.debug("oldVersion: " + oldVersion + " , newVersion: " + newVersion);
		return oldVersion.equals(newVersion) ? true : false;
	}

	private boolean emailFieldHasBeenUpdated(JournalArticle model) throws DocumentException {
		logger.debug("EmailFieldHasBeenUpdated... ");
		boolean hasBeenUpdated = false;
		JournalArticle previousModel = JournalArticleLocalServiceUtil.getPreviousApprovedArticle(model);
		if (!isCustomUpdate(previousModel, model)) {
			String previousEmailValue = getNodeText(previousModel, RecListenersPortletKeys.ASSIGNEES_ACTIONLOG);
			String currentEmailValue = getNodeText(model, RecListenersPortletKeys.ASSIGNEES_ACTIONLOG);
			logger.debug("previousEmailValue: " + previousEmailValue + " -- currentEmailValue: " + currentEmailValue);
			hasBeenUpdated = previousEmailValue.equalsIgnoreCase(currentEmailValue) ? false : true;
		}
		logger.debug("emailFieldHasBeenUpdated: " + hasBeenUpdated);
		return hasBeenUpdated;
	}

}
