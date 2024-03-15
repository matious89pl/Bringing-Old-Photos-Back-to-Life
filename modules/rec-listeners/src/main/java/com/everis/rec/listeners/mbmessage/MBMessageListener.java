package com.everis.rec.listeners.mbmessage;

import com.liferay.message.boards.model.MBMessage;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.util.Validator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import rec.confidential.message.model.Confidential_Messages;
import rec.confidential.message.service.Confidential_MessagesLocalService;

@Component(immediate = true, service = ModelListener.class)
public class MBMessageListener extends BaseModelListener<MBMessage> {

	private static final Log logger = LogFactoryUtil.getLog(MBMessageListener.class);
	
	@Override
	public void onAfterRemove(MBMessage model) throws ModelListenerException {	
		logger.debug("START - onAfterRemove... " + model.getMessageId());	
		Confidential_Messages cnf_msg = _confidentialMessageLocalService.fetchConfidential_Messages(model.getMessageId());
		if (Validator.isNotNull(cnf_msg)) {
			logger.debug("Removing confidential message relation for message: " + model.getMessageId());
			_confidentialMessageLocalService.deleteConfidential_Messages(cnf_msg);
			logger.debug("Confidential message relation for message: " + model.getMessageId() + " removed properly");
		}else {
			logger.debug("This comment has not confidential message relation");
		}
		logger.debug("END - onAfterRemove... " + model.getMessageId());
		super.onAfterRemove(model);
	}
	
	private Confidential_MessagesLocalService _confidentialMessageLocalService;

	@Reference(unbind = "-")
	protected void setConfidentialMessageLocalService(Confidential_MessagesLocalService service) {
		_confidentialMessageLocalService = service;
	}
	
}
