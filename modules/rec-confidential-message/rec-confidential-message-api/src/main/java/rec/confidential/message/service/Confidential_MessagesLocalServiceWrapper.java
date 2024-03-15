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

package rec.confidential.message.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link Confidential_MessagesLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see Confidential_MessagesLocalService
 * @generated
 */
public class Confidential_MessagesLocalServiceWrapper
	implements Confidential_MessagesLocalService,
			   ServiceWrapper<Confidential_MessagesLocalService> {

	public Confidential_MessagesLocalServiceWrapper(
		Confidential_MessagesLocalService confidential_MessagesLocalService) {

		_confidential_MessagesLocalService = confidential_MessagesLocalService;
	}

	/**
	 * Adds the confidential_ messages to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect Confidential_MessagesLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param confidential_Messages the confidential_ messages
	 * @return the confidential_ messages that was added
	 */
	@Override
	public rec.confidential.message.model.Confidential_Messages
		addConfidential_Messages(
			rec.confidential.message.model.Confidential_Messages
				confidential_Messages) {

		return _confidential_MessagesLocalService.addConfidential_Messages(
			confidential_Messages);
	}

	/**
	 * Creates a new confidential_ messages with the primary key. Does not add the confidential_ messages to the database.
	 *
	 * @param messageId the primary key for the new confidential_ messages
	 * @return the new confidential_ messages
	 */
	@Override
	public rec.confidential.message.model.Confidential_Messages
		createConfidential_Messages(long messageId) {

		return _confidential_MessagesLocalService.createConfidential_Messages(
			messageId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _confidential_MessagesLocalService.createPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Deletes the confidential_ messages from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect Confidential_MessagesLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param confidential_Messages the confidential_ messages
	 * @return the confidential_ messages that was removed
	 */
	@Override
	public rec.confidential.message.model.Confidential_Messages
		deleteConfidential_Messages(
			rec.confidential.message.model.Confidential_Messages
				confidential_Messages) {

		return _confidential_MessagesLocalService.deleteConfidential_Messages(
			confidential_Messages);
	}

	/**
	 * Deletes the confidential_ messages with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect Confidential_MessagesLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param messageId the primary key of the confidential_ messages
	 * @return the confidential_ messages that was removed
	 * @throws PortalException if a confidential_ messages with the primary key could not be found
	 */
	@Override
	public rec.confidential.message.model.Confidential_Messages
			deleteConfidential_Messages(long messageId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _confidential_MessagesLocalService.deleteConfidential_Messages(
			messageId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _confidential_MessagesLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _confidential_MessagesLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _confidential_MessagesLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>rec.confidential.message.model.impl.Confidential_MessagesModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _confidential_MessagesLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>rec.confidential.message.model.impl.Confidential_MessagesModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _confidential_MessagesLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _confidential_MessagesLocalService.dynamicQueryCount(
			dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _confidential_MessagesLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public rec.confidential.message.model.Confidential_Messages
		fetchConfidential_Messages(long messageId) {

		return _confidential_MessagesLocalService.fetchConfidential_Messages(
			messageId);
	}

	/**
	 * Returns the confidential_ messages with the matching UUID and company.
	 *
	 * @param uuid the confidential_ messages's UUID
	 * @param companyId the primary key of the company
	 * @return the matching confidential_ messages, or <code>null</code> if a matching confidential_ messages could not be found
	 */
	@Override
	public rec.confidential.message.model.Confidential_Messages
		fetchConfidential_MessagesByUuidAndCompanyId(
			String uuid, long companyId) {

		return _confidential_MessagesLocalService.
			fetchConfidential_MessagesByUuidAndCompanyId(uuid, companyId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _confidential_MessagesLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the confidential_ messages with the primary key.
	 *
	 * @param messageId the primary key of the confidential_ messages
	 * @return the confidential_ messages
	 * @throws PortalException if a confidential_ messages with the primary key could not be found
	 */
	@Override
	public rec.confidential.message.model.Confidential_Messages
			getConfidential_Messages(long messageId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _confidential_MessagesLocalService.getConfidential_Messages(
			messageId);
	}

	/**
	 * Returns the confidential_ messages with the matching UUID and company.
	 *
	 * @param uuid the confidential_ messages's UUID
	 * @param companyId the primary key of the company
	 * @return the matching confidential_ messages
	 * @throws PortalException if a matching confidential_ messages could not be found
	 */
	@Override
	public rec.confidential.message.model.Confidential_Messages
			getConfidential_MessagesByUuidAndCompanyId(
				String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _confidential_MessagesLocalService.
			getConfidential_MessagesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	 * Returns a range of all the confidential_ messageses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>rec.confidential.message.model.impl.Confidential_MessagesModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of confidential_ messageses
	 * @param end the upper bound of the range of confidential_ messageses (not inclusive)
	 * @return the range of confidential_ messageses
	 */
	@Override
	public java.util.List<rec.confidential.message.model.Confidential_Messages>
		getConfidential_Messageses(int start, int end) {

		return _confidential_MessagesLocalService.getConfidential_Messageses(
			start, end);
	}

	/**
	 * Returns the number of confidential_ messageses.
	 *
	 * @return the number of confidential_ messageses
	 */
	@Override
	public int getConfidential_MessagesesCount() {
		return _confidential_MessagesLocalService.
			getConfidential_MessagesesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _confidential_MessagesLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _confidential_MessagesLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _confidential_MessagesLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Updates the confidential_ messages in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect Confidential_MessagesLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param confidential_Messages the confidential_ messages
	 * @return the confidential_ messages that was updated
	 */
	@Override
	public rec.confidential.message.model.Confidential_Messages
		updateConfidential_Messages(
			rec.confidential.message.model.Confidential_Messages
				confidential_Messages) {

		return _confidential_MessagesLocalService.updateConfidential_Messages(
			confidential_Messages);
	}

	@Override
	public Confidential_MessagesLocalService getWrappedService() {
		return _confidential_MessagesLocalService;
	}

	@Override
	public void setWrappedService(
		Confidential_MessagesLocalService confidential_MessagesLocalService) {

		_confidential_MessagesLocalService = confidential_MessagesLocalService;
	}

	private Confidential_MessagesLocalService
		_confidential_MessagesLocalService;

}