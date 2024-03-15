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

package com.everis.rec.validation.log.service.persistence.impl;

import com.everis.rec.validation.log.exception.NoSuchValidationLogException;
import com.everis.rec.validation.log.model.ValidationLog;
import com.everis.rec.validation.log.model.impl.ValidationLogImpl;
import com.everis.rec.validation.log.model.impl.ValidationLogModelImpl;
import com.everis.rec.validation.log.service.persistence.ValidationLogPersistence;
import com.everis.rec.validation.log.service.persistence.impl.constants.VALIDATIONLOGPersistenceConstants;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.ArgumentsResolver;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the validation log service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = ValidationLogPersistence.class)
public class ValidationLogPersistenceImpl
	extends BasePersistenceImpl<ValidationLog>
	implements ValidationLogPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ValidationLogUtil</code> to access the validation log persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ValidationLogImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public ValidationLogPersistenceImpl() {
		setModelClass(ValidationLog.class);

		setModelImplClass(ValidationLogImpl.class);
		setModelPKClass(long.class);
	}

	/**
	 * Caches the validation log in the entity cache if it is enabled.
	 *
	 * @param validationLog the validation log
	 */
	@Override
	public void cacheResult(ValidationLog validationLog) {
		entityCache.putResult(
			ValidationLogImpl.class, validationLog.getPrimaryKey(),
			validationLog);
	}

	/**
	 * Caches the validation logs in the entity cache if it is enabled.
	 *
	 * @param validationLogs the validation logs
	 */
	@Override
	public void cacheResult(List<ValidationLog> validationLogs) {
		for (ValidationLog validationLog : validationLogs) {
			if (entityCache.getResult(
					ValidationLogImpl.class, validationLog.getPrimaryKey()) ==
						null) {

				cacheResult(validationLog);
			}
		}
	}

	/**
	 * Clears the cache for all validation logs.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ValidationLogImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the validation log.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ValidationLog validationLog) {
		entityCache.removeResult(ValidationLogImpl.class, validationLog);
	}

	@Override
	public void clearCache(List<ValidationLog> validationLogs) {
		for (ValidationLog validationLog : validationLogs) {
			entityCache.removeResult(ValidationLogImpl.class, validationLog);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(ValidationLogImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new validation log with the primary key. Does not add the validation log to the database.
	 *
	 * @param validationLogId the primary key for the new validation log
	 * @return the new validation log
	 */
	@Override
	public ValidationLog create(long validationLogId) {
		ValidationLog validationLog = new ValidationLogImpl();

		validationLog.setNew(true);
		validationLog.setPrimaryKey(validationLogId);

		validationLog.setCompanyId(CompanyThreadLocal.getCompanyId());

		return validationLog;
	}

	/**
	 * Removes the validation log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param validationLogId the primary key of the validation log
	 * @return the validation log that was removed
	 * @throws NoSuchValidationLogException if a validation log with the primary key could not be found
	 */
	@Override
	public ValidationLog remove(long validationLogId)
		throws NoSuchValidationLogException {

		return remove((Serializable)validationLogId);
	}

	/**
	 * Removes the validation log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the validation log
	 * @return the validation log that was removed
	 * @throws NoSuchValidationLogException if a validation log with the primary key could not be found
	 */
	@Override
	public ValidationLog remove(Serializable primaryKey)
		throws NoSuchValidationLogException {

		Session session = null;

		try {
			session = openSession();

			ValidationLog validationLog = (ValidationLog)session.get(
				ValidationLogImpl.class, primaryKey);

			if (validationLog == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchValidationLogException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(validationLog);
		}
		catch (NoSuchValidationLogException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected ValidationLog removeImpl(ValidationLog validationLog) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(validationLog)) {
				validationLog = (ValidationLog)session.get(
					ValidationLogImpl.class, validationLog.getPrimaryKeyObj());
			}

			if (validationLog != null) {
				session.delete(validationLog);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (validationLog != null) {
			clearCache(validationLog);
		}

		return validationLog;
	}

	@Override
	public ValidationLog updateImpl(ValidationLog validationLog) {
		boolean isNew = validationLog.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(validationLog);
			}
			else {
				validationLog = (ValidationLog)session.merge(validationLog);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			ValidationLogImpl.class, validationLog, false, true);

		if (isNew) {
			validationLog.setNew(false);
		}

		validationLog.resetOriginalValues();

		return validationLog;
	}

	/**
	 * Returns the validation log with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the validation log
	 * @return the validation log
	 * @throws NoSuchValidationLogException if a validation log with the primary key could not be found
	 */
	@Override
	public ValidationLog findByPrimaryKey(Serializable primaryKey)
		throws NoSuchValidationLogException {

		ValidationLog validationLog = fetchByPrimaryKey(primaryKey);

		if (validationLog == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchValidationLogException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return validationLog;
	}

	/**
	 * Returns the validation log with the primary key or throws a <code>NoSuchValidationLogException</code> if it could not be found.
	 *
	 * @param validationLogId the primary key of the validation log
	 * @return the validation log
	 * @throws NoSuchValidationLogException if a validation log with the primary key could not be found
	 */
	@Override
	public ValidationLog findByPrimaryKey(long validationLogId)
		throws NoSuchValidationLogException {

		return findByPrimaryKey((Serializable)validationLogId);
	}

	/**
	 * Returns the validation log with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param validationLogId the primary key of the validation log
	 * @return the validation log, or <code>null</code> if a validation log with the primary key could not be found
	 */
	@Override
	public ValidationLog fetchByPrimaryKey(long validationLogId) {
		return fetchByPrimaryKey((Serializable)validationLogId);
	}

	/**
	 * Returns all the validation logs.
	 *
	 * @return the validation logs
	 */
	@Override
	public List<ValidationLog> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the validation logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ValidationLogModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of validation logs
	 * @param end the upper bound of the range of validation logs (not inclusive)
	 * @return the range of validation logs
	 */
	@Override
	public List<ValidationLog> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the validation logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ValidationLogModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of validation logs
	 * @param end the upper bound of the range of validation logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of validation logs
	 */
	@Override
	public List<ValidationLog> findAll(
		int start, int end,
		OrderByComparator<ValidationLog> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the validation logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ValidationLogModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of validation logs
	 * @param end the upper bound of the range of validation logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of validation logs
	 */
	@Override
	public List<ValidationLog> findAll(
		int start, int end, OrderByComparator<ValidationLog> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<ValidationLog> list = null;

		if (useFinderCache) {
			list = (List<ValidationLog>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_VALIDATIONLOG);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_VALIDATIONLOG;

				sql = sql.concat(ValidationLogModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ValidationLog>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the validation logs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ValidationLog validationLog : findAll()) {
			remove(validationLog);
		}
	}

	/**
	 * Returns the number of validation logs.
	 *
	 * @return the number of validation logs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_VALIDATIONLOG);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "validationLogId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_VALIDATIONLOG;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ValidationLogModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the validation log persistence.
	 */
	@Activate
	public void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		_argumentsResolverServiceRegistration = _bundleContext.registerService(
			ArgumentsResolver.class, new ValidationLogModelArgumentsResolver(),
			MapUtil.singletonDictionary(
				"model.class.name", ValidationLog.class.getName()));

		_finderPathWithPaginationFindAll = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathWithoutPaginationFindAll = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathCountAll = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0], new String[0], false);
	}

	@Deactivate
	public void deactivate() {
		entityCache.removeCache(ValidationLogImpl.class.getName());

		_argumentsResolverServiceRegistration.unregister();

		for (ServiceRegistration<FinderPath> serviceRegistration :
				_serviceRegistrations) {

			serviceRegistration.unregister();
		}
	}

	@Override
	@Reference(
		target = VALIDATIONLOGPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = VALIDATIONLOGPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = VALIDATIONLOGPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	private BundleContext _bundleContext;

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_VALIDATIONLOG =
		"SELECT validationLog FROM ValidationLog validationLog";

	private static final String _SQL_COUNT_VALIDATIONLOG =
		"SELECT COUNT(validationLog) FROM ValidationLog validationLog";

	private static final String _ORDER_BY_ENTITY_ALIAS = "validationLog.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ValidationLog exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		ValidationLogPersistenceImpl.class);

	static {
		try {
			Class.forName(VALIDATIONLOGPersistenceConstants.class.getName());
		}
		catch (ClassNotFoundException classNotFoundException) {
			throw new ExceptionInInitializerError(classNotFoundException);
		}
	}

	private FinderPath _createFinderPath(
		String cacheName, String methodName, String[] params,
		String[] columnNames, boolean baseModelResult) {

		FinderPath finderPath = new FinderPath(
			cacheName, methodName, params, columnNames, baseModelResult);

		if (!cacheName.equals(FINDER_CLASS_NAME_LIST_WITH_PAGINATION)) {
			_serviceRegistrations.add(
				_bundleContext.registerService(
					FinderPath.class, finderPath,
					MapUtil.singletonDictionary("cache.name", cacheName)));
		}

		return finderPath;
	}

	private Set<ServiceRegistration<FinderPath>> _serviceRegistrations =
		new HashSet<>();
	private ServiceRegistration<ArgumentsResolver>
		_argumentsResolverServiceRegistration;

	private static class ValidationLogModelArgumentsResolver
		implements ArgumentsResolver {

		@Override
		public Object[] getArguments(
			FinderPath finderPath, BaseModel<?> baseModel, boolean checkColumn,
			boolean original) {

			String[] columnNames = finderPath.getColumnNames();

			if ((columnNames == null) || (columnNames.length == 0)) {
				if (baseModel.isNew()) {
					return FINDER_ARGS_EMPTY;
				}

				return null;
			}

			ValidationLogModelImpl validationLogModelImpl =
				(ValidationLogModelImpl)baseModel;

			long columnBitmask = validationLogModelImpl.getColumnBitmask();

			if (!checkColumn || (columnBitmask == 0)) {
				return _getValue(validationLogModelImpl, columnNames, original);
			}

			Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
				finderPath);

			if (finderPathColumnBitmask == null) {
				finderPathColumnBitmask = 0L;

				for (String columnName : columnNames) {
					finderPathColumnBitmask |=
						validationLogModelImpl.getColumnBitmask(columnName);
				}

				_finderPathColumnBitmasksCache.put(
					finderPath, finderPathColumnBitmask);
			}

			if ((columnBitmask & finderPathColumnBitmask) != 0) {
				return _getValue(validationLogModelImpl, columnNames, original);
			}

			return null;
		}

		private Object[] _getValue(
			ValidationLogModelImpl validationLogModelImpl, String[] columnNames,
			boolean original) {

			Object[] arguments = new Object[columnNames.length];

			for (int i = 0; i < arguments.length; i++) {
				String columnName = columnNames[i];

				if (original) {
					arguments[i] =
						validationLogModelImpl.getColumnOriginalValue(
							columnName);
				}
				else {
					arguments[i] = validationLogModelImpl.getColumnValue(
						columnName);
				}
			}

			return arguments;
		}

		private static Map<FinderPath, Long> _finderPathColumnBitmasksCache =
			new ConcurrentHashMap<>();

	}

}