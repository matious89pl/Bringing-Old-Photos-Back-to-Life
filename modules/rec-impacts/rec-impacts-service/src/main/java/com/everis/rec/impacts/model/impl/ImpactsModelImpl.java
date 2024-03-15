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

package com.everis.rec.impacts.model.impl;

import com.everis.rec.impacts.model.Impacts;
import com.everis.rec.impacts.model.ImpactsModel;
import com.everis.rec.impacts.model.ImpactsSoap;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the Impacts service. Represents a row in the &quot;rec_cpImpact_Impacts&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>ImpactsModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ImpactsImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ImpactsImpl
 * @generated
 */
@JSON(strict = true)
public class ImpactsModelImpl
	extends BaseModelImpl<Impacts> implements ImpactsModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a impacts model instance should use the <code>Impacts</code> interface instead.
	 */
	public static final String TABLE_NAME = "rec_cpImpact_Impacts";

	public static final Object[][] TABLE_COLUMNS = {
		{"impactId", Types.BIGINT}, {"impactName", Types.VARCHAR},
		{"impactCategory", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("impactId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("impactName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("impactCategory", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table rec_cpImpact_Impacts (impactId LONG not null primary key,impactName VARCHAR(75) null,impactCategory VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP =
		"drop table rec_cpImpact_Impacts";

	public static final String ORDER_BY_JPQL = " ORDER BY impacts.impactId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY rec_cpImpact_Impacts.impactId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long IMPACTCATEGORY_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)
	 */
	@Deprecated
	public static final long IMPACTID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
	}

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static Impacts toModel(ImpactsSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Impacts model = new ImpactsImpl();

		model.setImpactId(soapModel.getImpactId());
		model.setImpactName(soapModel.getImpactName());
		model.setImpactCategory(soapModel.getImpactCategory());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static List<Impacts> toModels(ImpactsSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Impacts> models = new ArrayList<Impacts>(soapModels.length);

		for (ImpactsSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public ImpactsModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _impactId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setImpactId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _impactId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Impacts.class;
	}

	@Override
	public String getModelClassName() {
		return Impacts.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<Impacts, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<Impacts, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Impacts, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((Impacts)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<Impacts, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<Impacts, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(Impacts)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<Impacts, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<Impacts, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, Impacts>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			Impacts.class.getClassLoader(), Impacts.class, ModelWrapper.class);

		try {
			Constructor<Impacts> constructor =
				(Constructor<Impacts>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException
							reflectiveOperationException) {

					throw new InternalError(reflectiveOperationException);
				}
			};
		}
		catch (NoSuchMethodException noSuchMethodException) {
			throw new InternalError(noSuchMethodException);
		}
	}

	private static final Map<String, Function<Impacts, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<Impacts, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<Impacts, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<Impacts, Object>>();
		Map<String, BiConsumer<Impacts, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<Impacts, ?>>();

		attributeGetterFunctions.put("impactId", Impacts::getImpactId);
		attributeSetterBiConsumers.put(
			"impactId", (BiConsumer<Impacts, Long>)Impacts::setImpactId);
		attributeGetterFunctions.put("impactName", Impacts::getImpactName);
		attributeSetterBiConsumers.put(
			"impactName", (BiConsumer<Impacts, String>)Impacts::setImpactName);
		attributeGetterFunctions.put(
			"impactCategory", Impacts::getImpactCategory);
		attributeSetterBiConsumers.put(
			"impactCategory",
			(BiConsumer<Impacts, String>)Impacts::setImpactCategory);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getImpactId() {
		return _impactId;
	}

	@Override
	public void setImpactId(long impactId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_impactId = impactId;
	}

	@JSON
	@Override
	public String getImpactName() {
		if (_impactName == null) {
			return "";
		}
		else {
			return _impactName;
		}
	}

	@Override
	public void setImpactName(String impactName) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_impactName = impactName;
	}

	@JSON
	@Override
	public String getImpactCategory() {
		if (_impactCategory == null) {
			return "";
		}
		else {
			return _impactCategory;
		}
	}

	@Override
	public void setImpactCategory(String impactCategory) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_impactCategory = impactCategory;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalImpactCategory() {
		return getColumnOriginalValue("impactCategory");
	}

	public long getColumnBitmask() {
		if (_columnBitmask > 0) {
			return _columnBitmask;
		}

		if ((_columnOriginalValues == null) ||
			(_columnOriginalValues == Collections.EMPTY_MAP)) {

			return 0;
		}

		for (Map.Entry<String, Object> entry :
				_columnOriginalValues.entrySet()) {

			if (entry.getValue() != getColumnValue(entry.getKey())) {
				_columnBitmask |= _columnBitmasks.get(entry.getKey());
			}
		}

		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			0, Impacts.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Impacts toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, Impacts>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		ImpactsImpl impactsImpl = new ImpactsImpl();

		impactsImpl.setImpactId(getImpactId());
		impactsImpl.setImpactName(getImpactName());
		impactsImpl.setImpactCategory(getImpactCategory());

		impactsImpl.resetOriginalValues();

		return impactsImpl;
	}

	@Override
	public int compareTo(Impacts impacts) {
		long primaryKey = impacts.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Impacts)) {
			return false;
		}

		Impacts impacts = (Impacts)object;

		long primaryKey = impacts.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isEntityCacheEnabled() {
		return true;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isFinderCacheEnabled() {
		return true;
	}

	@Override
	public void resetOriginalValues() {
		_columnOriginalValues = Collections.emptyMap();

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<Impacts> toCacheModel() {
		ImpactsCacheModel impactsCacheModel = new ImpactsCacheModel();

		impactsCacheModel.impactId = getImpactId();

		impactsCacheModel.impactName = getImpactName();

		String impactName = impactsCacheModel.impactName;

		if ((impactName != null) && (impactName.length() == 0)) {
			impactsCacheModel.impactName = null;
		}

		impactsCacheModel.impactCategory = getImpactCategory();

		String impactCategory = impactsCacheModel.impactCategory;

		if ((impactCategory != null) && (impactCategory.length() == 0)) {
			impactsCacheModel.impactCategory = null;
		}

		return impactsCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<Impacts, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(4 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<Impacts, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Impacts, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((Impacts)this));
			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<Impacts, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<Impacts, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Impacts, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((Impacts)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, Impacts>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _impactId;
	private String _impactName;
	private String _impactCategory;

	public <T> T getColumnValue(String columnName) {
		Function<Impacts, Object> function = _attributeGetterFunctions.get(
			columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((Impacts)this);
	}

	public <T> T getColumnOriginalValue(String columnName) {
		if (_columnOriginalValues == null) {
			return null;
		}

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		return (T)_columnOriginalValues.get(columnName);
	}

	private void _setColumnOriginalValues() {
		_columnOriginalValues = new HashMap<String, Object>();

		_columnOriginalValues.put("impactId", _impactId);
		_columnOriginalValues.put("impactName", _impactName);
		_columnOriginalValues.put("impactCategory", _impactCategory);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("impactId", 1L);

		columnBitmasks.put("impactName", 2L);

		columnBitmasks.put("impactCategory", 4L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private Impacts _escapedModel;

}