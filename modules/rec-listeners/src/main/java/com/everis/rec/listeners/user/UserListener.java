package com.everis.rec.listeners.user;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = ModelListener.class)
public class UserListener extends BaseModelListener<User> {

	private static final Log logger = LogFactoryUtil.getLog(UserListener.class);

	private static final String ALL_PORTAL_USERS_ORGANISATION = "All Portal Users";

	@Override
	public void onAfterUpdate(User model) throws ModelListenerException {
		logger.info("onAfterUpdate User Listener");
		try {
			Organization organisation = OrganizationLocalServiceUtil.getOrganization(model.getCompanyId(),
					ALL_PORTAL_USERS_ORGANISATION);
			if (Validator.isNotNull(organisation)) {
				List<Organization> userOrganizations = model.getOrganizations();
				if (!userOrganizations.contains(organisation)) {
					OrganizationLocalServiceUtil.addUserOrganization(model.getUserId(),
							organisation.getOrganizationId());
					logger.info("User " + model.getScreenName() + " has been added to " + ALL_PORTAL_USERS_ORGANISATION
							+ " organisation.");
				}
			}
		} catch (PortalException e) {
			logger.error("There is no organisation with name: " + ALL_PORTAL_USERS_ORGANISATION);
		} finally {
			super.onBeforeUpdate(model);
		}
	}

}
