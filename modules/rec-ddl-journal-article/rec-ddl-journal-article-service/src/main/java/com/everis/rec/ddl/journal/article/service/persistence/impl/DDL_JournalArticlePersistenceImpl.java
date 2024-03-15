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

package com.everis.rec.ddl.journal.article.service.persistence.impl;

import com.everis.rec.ddl.journal.article.exception.NoSuchDDL_JournalArticleException;
import com.everis.rec.ddl.journal.article.model.DDL_JournalArticle;
import com.everis.rec.ddl.journal.article.model.impl.DDL_JournalArticleImpl;
import com.everis.rec.ddl.journal.article.model.impl.DDL_JournalArticleModelImpl;
import com.everis.rec.ddl.journal.article.service.persistence.DDL_JournalArticlePersistence;
import com.everis.rec.ddl.journal.article.service.persistence.impl.constants.REC_DDL_JOURNAL_ARTICLEPersistenceConstants;

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
 * The persistence implementation for the ddl_ journal article service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = DDL_JournalArticlePersistence.class)
public class DDL_JournalArticlePersistenceImpl
	extends BasePersistenceImpl<DDL_JournalArticle>
	implements DDL_JournalArticlePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>DDL_JournalArticleUtil</code> to access the ddl_ journal article persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		DDL_JournalArticleImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public DDL_JournalArticlePersistenceImpl() {
		setModelClass(DDL_JournalArticle.class);

		setModelImplClass(DDL_JournalArticleImpl.class);
		setModelPKClass(long.class);
	}

	/**
	 * Caches the ddl_ journal article in the entity cache if it is enabled.
	 *
	 * @param ddl_JournalArticle the ddl_ journal article
	 */
	@Override
	public void cacheResult(DDL_JournalArticle ddl_JournalArticle) {
		entityCache.putResult(
			DDL_JournalArticleImpl.class, ddl_JournalArticle.getPrimaryKey(),
			ddl_JournalArticle);
	}

	/**
	 * Caches the ddl_ journal articles in the entity cache if it is enabled.
	 *
	 * @param ddl_JournalArticles the ddl_ journal articles
	 */
	@Override
	public void cacheResult(List<DDL_JournalArticle> ddl_JournalArticles) {
		for (DDL_JournalArticle ddl_JournalArticle : ddl_JournalArticles) {
			if (entityCache.getResult(
					DDL_JournalArticleImpl.class,
					ddl_JournalArticle.getPrimaryKey()) == null) {

				cacheResult(ddl_JournalArticle);
			}
		}
	}

	/**
	 * Clears the cache for all ddl_ journal articles.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DDL_JournalArticleImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ddl_ journal article.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DDL_JournalArticle ddl_JournalArticle) {
		entityCache.removeResult(
			DDL_JournalArticleImpl.class, ddl_JournalArticle);
	}

	@Override
	public void clearCache(List<DDL_JournalArticle> ddl_JournalArticles) {
		for (DDL_JournalArticle ddl_JournalArticle : ddl_JournalArticles) {
			entityCache.removeResult(
				DDL_JournalArticleImpl.class, ddl_JournalArticle);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(DDL_JournalArticleImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new ddl_ journal article with the primary key. Does not add the ddl_ journal article to the database.
	 *
	 * @param ddlRecordSetId the primary key for the new ddl_ journal article
	 * @return the new ddl_ journal article
	 */
	@Override
	public DDL_JournalArticle create(long ddlRecordSetId) {
		DDL_JournalArticle ddl_JournalArticle = new DDL_JournalArticleImpl();

		ddl_JournalArticle.setNew(true);
		ddl_JournalArticle.setPrimaryKey(ddlRecordSetId);

		return ddl_JournalArticle;
	}

	/**
	 * Removes the ddl_ journal article with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ddlRecordSetId the primary key of the ddl_ journal article
	 * @return the ddl_ journal article that was removed
	 * @throws NoSuchDDL_JournalArticleException if a ddl_ journal article with the primary key could not be found
	 */
	@Override
	public DDL_JournalArticle remove(long ddlRecordSetId)
		throws NoSuchDDL_JournalArticleException {

		return remove((Serializable)ddlRecordSetId);
	}

	/**
	 * Removes the ddl_ journal article with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ddl_ journal article
	 * @return the ddl_ journal article that was removed
	 * @throws NoSuchDDL_JournalArticleException if a ddl_ journal article with the primary key could not be found
	 */
	@Override
	public DDL_JournalArticle remove(Serializable primaryKey)
		throws NoSuchDDL_JournalArticleException {

		Session session = null;

		try {
			session = openSession();

			DDL_JournalArticle ddl_JournalArticle =
				(DDL_JournalArticle)session.get(
					DDL_JournalArticleImpl.class, primaryKey);

			if (ddl_JournalArticle == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDDL_JournalArticleException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(ddl_JournalArticle);
		}
		catch (NoSuchDDL_JournalArticleException noSuchEntityException) {
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
	protected DDL_JournalArticle removeImpl(
		DDL_JournalArticle ddl_JournalArticle) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ddl_JournalArticle)) {
				ddl_JournalArticle = (DDL_JournalArticle)session.get(
					DDL_JournalArticleImpl.class,
					ddl_JournalArticle.getPrimaryKeyObj());
			}

			if (ddl_JournalArticle != null) {
				session.delete(ddl_JournalArticle);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (ddl_JournalArticle != null) {
			clearCache(ddl_JournalArticle);
		}

		return ddl_JournalArticle;
	}

	@Override
	public DDL_JournalArticle updateImpl(
		DDL_JournalArticle ddl_JournalArticle) {

		boolean isNew = ddl_JournalArticle.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(ddl_JournalArticle);
			}
			else {
				ddl_JournalArticle = (DDL_JournalArticle)session.merge(
					ddl_JournalArticle);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			DDL_JournalArticleImpl.class, ddl_JournalArticle, false, true);

		if (isNew) {
			ddl_JournalArticle.setNew(false);
		}

		ddl_JournalArticle.resetOriginalValues();

		return ddl_JournalArticle;
	}

	/**
	 * Returns the ddl_ journal article with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ddl_ journal article
	 * @return the ddl_ journal article
	 * @throws NoSuchDDL_JournalArticleException if a ddl_ journal article with the primary key could not be found
	 */
	@Override
	public DDL_JournalArticle findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDDL_JournalArticleException {

		DDL_JournalArticle ddl_JournalArticle = fetchByPrimaryKey(primaryKey);

		if (ddl_JournalArticle == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDDL_JournalArticleException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return ddl_JournalArticle;
	}

	/**
	 * Returns the ddl_ journal article with the primary key or throws a <code>NoSuchDDL_JournalArticleException</code> if it could not be found.
	 *
	 * @param ddlRecordSetId the primary key of the ddl_ journal article
	 * @return the ddl_ journal article
	 * @throws NoSuchDDL_JournalArticleException if a ddl_ journal article with the primary key could not be found
	 */
	@Override
	public DDL_JournalArticle findByPrimaryKey(long ddlRecordSetId)
		throws NoSuchDDL_JournalArticleException {

		return findByPrimaryKey((Serializable)ddlRecordSetId);
	}

	/**
	 * Returns the ddl_ journal article with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ddlRecordSetId the primary key of the ddl_ journal article
	 * @return the ddl_ journal article, or <code>null</code> if a ddl_ journal article with the primary key could not be found
	 */
	@Override
	public DDL_JournalArticle fetchByPrimaryKey(long ddlRecordSetId) {
		return fetchByPrimaryKey((Serializable)ddlRecordSetId);
	}

	/**
	 * Returns all the ddl_ journal articles.
	 *
	 * @return the ddl_ journal articles
	 */
	@Override
	public List<DDL_JournalArticle> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ddl_ journal articles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDL_JournalArticleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ddl_ journal articles
	 * @param end the upper bound of the range of ddl_ journal articles (not inclusive)
	 * @return the range of ddl_ journal articles
	 */
	@Override
	public List<DDL_JournalArticle> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ddl_ journal articles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDL_JournalArticleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ddl_ journal articles
	 * @param end the upper bound of the range of ddl_ journal articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ddl_ journal articles
	 */
	@Override
	public List<DDL_JournalArticle> findAll(
		int start, int end,
		OrderByComparator<DDL_JournalArticle> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ddl_ journal articles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDL_JournalArticleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ddl_ journal articles
	 * @param end the upper bound of the range of ddl_ journal articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of ddl_ journal articles
	 */
	@Override
	public List<DDL_JournalArticle> findAll(
		int start, int end,
		OrderByComparator<DDL_JournalArticle> orderByComparator,
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

		List<DDL_JournalArticle> list = null;

		if (useFinderCache) {
			list = (List<DDL_JournalArticle>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_DDL_JOURNALARTICLE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_DDL_JOURNALARTICLE;

				sql = sql.concat(DDL_JournalArticleModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<DDL_JournalArticle>)QueryUtil.list(
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
	 * Removes all the ddl_ journal articles from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DDL_JournalArticle ddl_JournalArticle : findAll()) {
			remove(ddl_JournalArticle);
		}
	}

	/**
	 * Returns the number of ddl_ journal articles.
	 *
	 * @return the number of ddl_ journal articles
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(
					_SQL_COUNT_DDL_JOURNALARTICLE);

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
		return "ddlRecordSetId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_DDL_JOURNALARTICLE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return DDL_JournalArticleModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ddl_ journal article persistence.
	 */
	@Activate
	public void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		_argumentsResolverServiceRegistration = _bundleContext.registerService(
			ArgumentsResolver.class,
			new DDL_JournalArticleModelArgumentsResolver(),
			MapUtil.singletonDictionary(
				"model.class.name", DDL_JournalArticle.class.getName()));

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
		entityCache.removeCache(DDL_JournalArticleImpl.class.getName());

		_argumentsResolverServiceRegistration.unregister();

		for (ServiceRegistration<FinderPath> serviceRegistration :
				_serviceRegistrations) {

			serviceRegistration.unregister();
		}
	}

	@Override
	@Reference(
		target = REC_DDL_JOURNAL_ARTICLEPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = REC_DDL_JOURNAL_ARTICLEPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = REC_DDL_JOURNAL_ARTICLEPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
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

	private static final String _SQL_SELECT_DDL_JOURNALARTICLE =
		"SELECT ddl_JournalArticle FROM DDL_JournalArticle ddl_JournalArticle";

	private static final String _SQL_COUNT_DDL_JOURNALARTICLE =
		"SELECT COUNT(ddl_JournalArticle) FROM DDL_JournalArticle ddl_JournalArticle";

	private static final String _ORDER_BY_ENTITY_ALIAS = "ddl_JournalArticle.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No DDL_JournalArticle exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		DDL_JournalArticlePersistenceImpl.class);

	static {
		try {
			Class.forName(
				REC_DDL_JOURNAL_ARTICLEPersistenceConstants.class.getName());
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

	private static class DDL_JournalArticleModelArgumentsResolver
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

			DDL_JournalArticleModelImpl ddl_JournalArticleModelImpl =
				(DDL_JournalArticleModelImpl)baseModel;

			long columnBitmask = ddl_JournalArticleModelImpl.getColumnBitmask();

			if (!checkColumn || (columnBitmask == 0)) {
				return _getValue(
					ddl_JournalArticleModelImpl, columnNames, original);
			}

			Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
				finderPath);

			if (finderPathColumnBitmask == null) {
				finderPathColumnBitmask = 0L;

				for (String columnName : columnNames) {
					finderPathColumnBitmask |=
						ddl_JournalArticleModelImpl.getColumnBitmask(
							columnName);
				}

				_finderPathColumnBitmasksCache.put(
					finderPath, finderPathColumnBitmask);
			}

			if ((columnBitmask & finderPathColumnBitmask) != 0) {
				return _getValue(
					ddl_JournalArticleModelImpl, columnNames, original);
			}

			return null;
		}

		private Object[] _getValue(
			DDL_JournalArticleModelImpl ddl_JournalArticleModelImpl,
			String[] columnNames, boolean original) {

			Object[] arguments = new Object[columnNames.length];

			for (int i = 0; i < arguments.length; i++) {
				String columnName = columnNames[i];

				if (original) {
					arguments[i] =
						ddl_JournalArticleModelImpl.getColumnOriginalValue(
							columnName);
				}
				else {
					arguments[i] = ddl_JournalArticleModelImpl.getColumnValue(
						columnName);
				}
			}

			return arguments;
		}

		private static Map<FinderPath, Long> _finderPathColumnBitmasksCache =
			new ConcurrentHashMap<>();

	}

}