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

package com.everis.rec.service.activity.logs.model.impl;

import com.everis.rec.service.activity.logs.model.RecLog;
import com.everis.rec.service.activity.logs.model.RecLogModel;
import com.everis.rec.service.activity.logs.model.RecLogSoap;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the RecLog service. Represents a row in the &quot;ACTIVITY_RecLog&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>RecLogModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link RecLogImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RecLogImpl
 * @generated
 */
@JSON(strict = true)
public class RecLogModelImpl
	extends BaseModelImpl<RecLog> implements RecLogModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a rec log model instance should use the <code>RecLog</code> interface instead.
	 */
	public static final String TABLE_NAME = "ACTIVITY_RecLog";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"activityLogId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"createDate", Types.TIMESTAMP},
		{"logMessage", Types.VARCHAR}, {"className", Types.VARCHAR},
		{"classPK", Types.BIGINT}, {"comments", Types.VARCHAR},
		{"type_", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("activityLogId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("logMessage", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("className", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("comments", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("type_", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table ACTIVITY_RecLog (uuid_ VARCHAR(75) null,activityLogId LONG not null primary key,groupId LONG,createDate DATE null,logMessage VARCHAR(75) null,className VARCHAR(75) null,classPK LONG,comments VARCHAR(75) null,type_ VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP = "drop table ACTIVITY_RecLog";

	public static final String ORDER_BY_JPQL =
		" ORDER BY recLog.createDate DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY ACTIVITY_RecLog.createDate DESC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long CLASSPK_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long GROUPID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long UUID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)
	 */
	@Deprecated
	public static final long CREATEDATE_COLUMN_BITMASK = 8L;

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
	public static RecLog toModel(RecLogSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		RecLog model = new RecLogImpl();

		model.setUuid(soapModel.getUuid());
		model.setActivityLogId(soapModel.getActivityLogId());
		model.setGroupId(soapModel.getGroupId());
		model.setCreateDate(soapModel.getCreateDate());
		model.setLogMessage(soapModel.getLogMessage());
		model.setClassName(soapModel.getClassName());
		model.setClassPK(soapModel.getClassPK());
		model.setComments(soapModel.getComments());
		model.setType(soapModel.getType());

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
	public static List<RecLog> toModels(RecLogSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<RecLog> models = new ArrayList<RecLog>(soapModels.length);

		for (RecLogSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public RecLogModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _activityLogId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setActivityLogId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _activityLogId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return RecLog.class;
	}

	@Override
	public String getModelClassName() {
		return RecLog.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<RecLog, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<RecLog, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<RecLog, Object> attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((RecLog)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<RecLog, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<RecLog, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(RecLog)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<RecLog, Object>> getAttributeGetterFunctions() {
		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<RecLog, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, RecLog>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			RecLog.class.getClassLoader(), RecLog.class, ModelWrapper.class);

		try {
			Constructor<RecLog> constructor =
				(Constructor<RecLog>)proxyClass.getConstructor(
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

	private static final Map<String, Function<RecLog, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<RecLog, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<RecLog, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<RecLog, Object>>();
		Map<String, BiConsumer<RecLog, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<RecLog, ?>>();

		attributeGetterFunctions.put("uuid", RecLog::getUuid);
		attributeSetterBiConsumers.put(
			"uuid", (BiConsumer<RecLog, String>)RecLog::setUuid);
		attributeGetterFunctions.put("activityLogId", RecLog::getActivityLogId);
		attributeSetterBiConsumers.put(
			"activityLogId",
			(BiConsumer<RecLog, Long>)RecLog::setActivityLogId);
		attributeGetterFunctions.put("groupId", RecLog::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId", (BiConsumer<RecLog, Long>)RecLog::setGroupId);
		attributeGetterFunctions.put("createDate", RecLog::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate", (BiConsumer<RecLog, Date>)RecLog::setCreateDate);
		attributeGetterFunctions.put("logMessage", RecLog::getLogMessage);
		attributeSetterBiConsumers.put(
			"logMessage", (BiConsumer<RecLog, String>)RecLog::setLogMessage);
		attributeGetterFunctions.put("className", RecLog::getClassName);
		attributeSetterBiConsumers.put(
			"className", (BiConsumer<RecLog, String>)RecLog::setClassName);
		attributeGetterFunctions.put("classPK", RecLog::getClassPK);
		attributeSetterBiConsumers.put(
			"classPK", (BiConsumer<RecLog, Long>)RecLog::setClassPK);
		attributeGetterFunctions.put("comments", RecLog::getComments);
		attributeSetterBiConsumers.put(
			"comments", (BiConsumer<RecLog, String>)RecLog::setComments);
		attributeGetterFunctions.put("type", RecLog::getType);
		attributeSetterBiConsumers.put(
			"type", (BiConsumer<RecLog, String>)RecLog::setType);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_uuid = uuid;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalUuid() {
		return getColumnOriginalValue("uuid_");
	}

	@JSON
	@Override
	public long getActivityLogId() {
		return _activityLogId;
	}

	@Override
	public void setActivityLogId(long activityLogId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_activityLogId = activityLogId;
	}

	@JSON
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_groupId = groupId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalGroupId() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("groupId"));
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_createDate = createDate;
	}

	@JSON
	@Override
	public String getLogMessage() {
		if (_logMessage == null) {
			return "";
		}
		else {
			return _logMessage;
		}
	}

	@Override
	public void setLogMessage(String logMessage) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_logMessage = logMessage;
	}

	@JSON
	@Override
	public String getClassName() {
		if (_className == null) {
			return "";
		}
		else {
			return _className;
		}
	}

	@Override
	public void setClassName(String className) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_className = className;
	}

	@JSON
	@Override
	public Long getClassPK() {
		return _classPK;
	}

	@Override
	public void setClassPK(Long classPK) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_classPK = classPK;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public Long getOriginalClassPK() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("classPK"));
	}

	@JSON
	@Override
	public String getComments() {
		if (_comments == null) {
			return "";
		}
		else {
			return _comments;
		}
	}

	@Override
	public void setComments(String comments) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_comments = comments;
	}

	@JSON
	@Override
	public String getType() {
		if (_type == null) {
			return "";
		}
		else {
			return _type;
		}
	}

	@Override
	public void setType(String type) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_type = type;
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
			0, RecLog.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public RecLog toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, RecLog>
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
		RecLogImpl recLogImpl = new RecLogImpl();

		recLogImpl.setUuid(getUuid());
		recLogImpl.setActivityLogId(getActivityLogId());
		recLogImpl.setGroupId(getGroupId());
		recLogImpl.setCreateDate(getCreateDate());
		recLogImpl.setLogMessage(getLogMessage());
		recLogImpl.setClassName(getClassName());
		recLogImpl.setClassPK(getClassPK());
		recLogImpl.setComments(getComments());
		recLogImpl.setType(getType());

		recLogImpl.resetOriginalValues();

		return recLogImpl;
	}

	@Override
	public int compareTo(RecLog recLog) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(), recLog.getCreateDate());

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof RecLog)) {
			return false;
		}

		RecLog recLog = (RecLog)object;

		long primaryKey = recLog.getPrimaryKey();

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
	public CacheModel<RecLog> toCacheModel() {
		RecLogCacheModel recLogCacheModel = new RecLogCacheModel();

		recLogCacheModel.uuid = getUuid();

		String uuid = recLogCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			recLogCacheModel.uuid = null;
		}

		recLogCacheModel.activityLogId = getActivityLogId();

		recLogCacheModel.groupId = getGroupId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			recLogCacheModel.createDate = createDate.getTime();
		}
		else {
			recLogCacheModel.createDate = Long.MIN_VALUE;
		}

		recLogCacheModel.logMessage = getLogMessage();

		String logMessage = recLogCacheModel.logMessage;

		if ((logMessage != null) && (logMessage.length() == 0)) {
			recLogCacheModel.logMessage = null;
		}

		recLogCacheModel.className = getClassName();

		String className = recLogCacheModel.className;

		if ((className != null) && (className.length() == 0)) {
			recLogCacheModel.className = null;
		}

		Long classPK = getClassPK();

		if (classPK != null) {
			recLogCacheModel.classPK = classPK;
		}

		recLogCacheModel.comments = getComments();

		String comments = recLogCacheModel.comments;

		if ((comments != null) && (comments.length() == 0)) {
			recLogCacheModel.comments = null;
		}

		recLogCacheModel.type = getType();

		String type = recLogCacheModel.type;

		if ((type != null) && (type.length() == 0)) {
			recLogCacheModel.type = null;
		}

		return recLogCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<RecLog, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(4 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<RecLog, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<RecLog, Object> attributeGetterFunction = entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((RecLog)this));
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
		Map<String, Function<RecLog, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<RecLog, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<RecLog, Object> attributeGetterFunction = entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((RecLog)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, RecLog>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private String _uuid;
	private long _activityLogId;
	private long _groupId;
	private Date _createDate;
	private String _logMessage;
	private String _className;
	private Long _classPK;
	private String _comments;
	private String _type;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<RecLog, Object> function = _attributeGetterFunctions.get(
			columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((RecLog)this);
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

		_columnOriginalValues.put("uuid_", _uuid);
		_columnOriginalValues.put("activityLogId", _activityLogId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("logMessage", _logMessage);
		_columnOriginalValues.put("className", _className);
		_columnOriginalValues.put("classPK", _classPK);
		_columnOriginalValues.put("comments", _comments);
		_columnOriginalValues.put("type_", _type);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("uuid_", "uuid");
		attributeNames.put("type_", "type");

		_attributeNames = Collections.unmodifiableMap(attributeNames);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("uuid_", 1L);

		columnBitmasks.put("activityLogId", 2L);

		columnBitmasks.put("groupId", 4L);

		columnBitmasks.put("createDate", 8L);

		columnBitmasks.put("logMessage", 16L);

		columnBitmasks.put("className", 32L);

		columnBitmasks.put("classPK", 64L);

		columnBitmasks.put("comments", 128L);

		columnBitmasks.put("type_", 256L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private RecLog _escapedModel;

}