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

package rec.file.conf.service.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
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

import rec.file.conf.service.model.File_Conf;
import rec.file.conf.service.model.File_ConfModel;
import rec.file.conf.service.model.File_ConfSoap;

/**
 * The base model implementation for the File_Conf service. Represents a row in the &quot;REC_File_Conf&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>File_ConfModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link File_ConfImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see File_ConfImpl
 * @generated
 */
@JSON(strict = true)
public class File_ConfModelImpl
	extends BaseModelImpl<File_Conf> implements File_ConfModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a file_ conf model instance should use the <code>File_Conf</code> interface instead.
	 */
	public static final String TABLE_NAME = "REC_File_Conf";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"docConfigId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"docConfName", Types.VARCHAR}, {"docFileType", Types.VARCHAR},
		{"docConfigJSON", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("docConfigId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("docConfName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("docFileType", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("docConfigJSON", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table REC_File_Conf (uuid_ VARCHAR(75) null,docConfigId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,docConfName VARCHAR(75) null,docFileType VARCHAR(75) null,docConfigJSON STRING null)";

	public static final String TABLE_SQL_DROP = "drop table REC_File_Conf";

	public static final String ORDER_BY_JPQL =
		" ORDER BY file_Conf.docConfName ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY REC_File_Conf.docConfName ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long DOCCONFNAME_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long DOCFILETYPE_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long GROUPID_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long UUID_COLUMN_BITMASK = 16L;

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
	public static File_Conf toModel(File_ConfSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		File_Conf model = new File_ConfImpl();

		model.setUuid(soapModel.getUuid());
		model.setDocConfigId(soapModel.getDocConfigId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setDocConfName(soapModel.getDocConfName());
		model.setDocFileType(soapModel.getDocFileType());
		model.setDocConfigJSON(soapModel.getDocConfigJSON());

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
	public static List<File_Conf> toModels(File_ConfSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<File_Conf> models = new ArrayList<File_Conf>(soapModels.length);

		for (File_ConfSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public File_ConfModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _docConfigId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setDocConfigId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _docConfigId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return File_Conf.class;
	}

	@Override
	public String getModelClassName() {
		return File_Conf.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<File_Conf, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<File_Conf, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<File_Conf, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((File_Conf)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<File_Conf, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<File_Conf, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(File_Conf)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<File_Conf, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<File_Conf, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, File_Conf>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			File_Conf.class.getClassLoader(), File_Conf.class,
			ModelWrapper.class);

		try {
			Constructor<File_Conf> constructor =
				(Constructor<File_Conf>)proxyClass.getConstructor(
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

	private static final Map<String, Function<File_Conf, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<File_Conf, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<File_Conf, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<File_Conf, Object>>();
		Map<String, BiConsumer<File_Conf, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<File_Conf, ?>>();

		attributeGetterFunctions.put("uuid", File_Conf::getUuid);
		attributeSetterBiConsumers.put(
			"uuid", (BiConsumer<File_Conf, String>)File_Conf::setUuid);
		attributeGetterFunctions.put("docConfigId", File_Conf::getDocConfigId);
		attributeSetterBiConsumers.put(
			"docConfigId",
			(BiConsumer<File_Conf, Long>)File_Conf::setDocConfigId);
		attributeGetterFunctions.put("groupId", File_Conf::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId", (BiConsumer<File_Conf, Long>)File_Conf::setGroupId);
		attributeGetterFunctions.put("companyId", File_Conf::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId", (BiConsumer<File_Conf, Long>)File_Conf::setCompanyId);
		attributeGetterFunctions.put("userId", File_Conf::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<File_Conf, Long>)File_Conf::setUserId);
		attributeGetterFunctions.put("userName", File_Conf::getUserName);
		attributeSetterBiConsumers.put(
			"userName", (BiConsumer<File_Conf, String>)File_Conf::setUserName);
		attributeGetterFunctions.put("createDate", File_Conf::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<File_Conf, Date>)File_Conf::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", File_Conf::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<File_Conf, Date>)File_Conf::setModifiedDate);
		attributeGetterFunctions.put("docConfName", File_Conf::getDocConfName);
		attributeSetterBiConsumers.put(
			"docConfName",
			(BiConsumer<File_Conf, String>)File_Conf::setDocConfName);
		attributeGetterFunctions.put("docFileType", File_Conf::getDocFileType);
		attributeSetterBiConsumers.put(
			"docFileType",
			(BiConsumer<File_Conf, String>)File_Conf::setDocFileType);
		attributeGetterFunctions.put(
			"docConfigJSON", File_Conf::getDocConfigJSON);
		attributeSetterBiConsumers.put(
			"docConfigJSON",
			(BiConsumer<File_Conf, String>)File_Conf::setDocConfigJSON);

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
	public long getDocConfigId() {
		return _docConfigId;
	}

	@Override
	public void setDocConfigId(long docConfigId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_docConfigId = docConfigId;
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
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_companyId = companyId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCompanyId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("companyId"));
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException portalException) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userName = userName;
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
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public String getDocConfName() {
		if (_docConfName == null) {
			return "";
		}
		else {
			return _docConfName;
		}
	}

	@Override
	public void setDocConfName(String docConfName) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_docConfName = docConfName;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalDocConfName() {
		return getColumnOriginalValue("docConfName");
	}

	@JSON
	@Override
	public String getDocFileType() {
		if (_docFileType == null) {
			return "";
		}
		else {
			return _docFileType;
		}
	}

	@Override
	public void setDocFileType(String docFileType) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_docFileType = docFileType;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalDocFileType() {
		return getColumnOriginalValue("docFileType");
	}

	@JSON
	@Override
	public String getDocConfigJSON() {
		if (_docConfigJSON == null) {
			return "";
		}
		else {
			return _docConfigJSON;
		}
	}

	@Override
	public void setDocConfigJSON(String docConfigJSON) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_docConfigJSON = docConfigJSON;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(File_Conf.class.getName()));
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
			getCompanyId(), File_Conf.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public File_Conf toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, File_Conf>
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
		File_ConfImpl file_ConfImpl = new File_ConfImpl();

		file_ConfImpl.setUuid(getUuid());
		file_ConfImpl.setDocConfigId(getDocConfigId());
		file_ConfImpl.setGroupId(getGroupId());
		file_ConfImpl.setCompanyId(getCompanyId());
		file_ConfImpl.setUserId(getUserId());
		file_ConfImpl.setUserName(getUserName());
		file_ConfImpl.setCreateDate(getCreateDate());
		file_ConfImpl.setModifiedDate(getModifiedDate());
		file_ConfImpl.setDocConfName(getDocConfName());
		file_ConfImpl.setDocFileType(getDocFileType());
		file_ConfImpl.setDocConfigJSON(getDocConfigJSON());

		file_ConfImpl.resetOriginalValues();

		return file_ConfImpl;
	}

	@Override
	public int compareTo(File_Conf file_Conf) {
		int value = 0;

		value = getDocConfName().compareTo(file_Conf.getDocConfName());

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

		if (!(object instanceof File_Conf)) {
			return false;
		}

		File_Conf file_Conf = (File_Conf)object;

		long primaryKey = file_Conf.getPrimaryKey();

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

		_setModifiedDate = false;

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<File_Conf> toCacheModel() {
		File_ConfCacheModel file_ConfCacheModel = new File_ConfCacheModel();

		file_ConfCacheModel.uuid = getUuid();

		String uuid = file_ConfCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			file_ConfCacheModel.uuid = null;
		}

		file_ConfCacheModel.docConfigId = getDocConfigId();

		file_ConfCacheModel.groupId = getGroupId();

		file_ConfCacheModel.companyId = getCompanyId();

		file_ConfCacheModel.userId = getUserId();

		file_ConfCacheModel.userName = getUserName();

		String userName = file_ConfCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			file_ConfCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			file_ConfCacheModel.createDate = createDate.getTime();
		}
		else {
			file_ConfCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			file_ConfCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			file_ConfCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		file_ConfCacheModel.docConfName = getDocConfName();

		String docConfName = file_ConfCacheModel.docConfName;

		if ((docConfName != null) && (docConfName.length() == 0)) {
			file_ConfCacheModel.docConfName = null;
		}

		file_ConfCacheModel.docFileType = getDocFileType();

		String docFileType = file_ConfCacheModel.docFileType;

		if ((docFileType != null) && (docFileType.length() == 0)) {
			file_ConfCacheModel.docFileType = null;
		}

		file_ConfCacheModel.docConfigJSON = getDocConfigJSON();

		String docConfigJSON = file_ConfCacheModel.docConfigJSON;

		if ((docConfigJSON != null) && (docConfigJSON.length() == 0)) {
			file_ConfCacheModel.docConfigJSON = null;
		}

		return file_ConfCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<File_Conf, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(4 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<File_Conf, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<File_Conf, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((File_Conf)this));
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
		Map<String, Function<File_Conf, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<File_Conf, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<File_Conf, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((File_Conf)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, File_Conf>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private String _uuid;
	private long _docConfigId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _docConfName;
	private String _docFileType;
	private String _docConfigJSON;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<File_Conf, Object> function = _attributeGetterFunctions.get(
			columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((File_Conf)this);
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
		_columnOriginalValues.put("docConfigId", _docConfigId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("docConfName", _docConfName);
		_columnOriginalValues.put("docFileType", _docFileType);
		_columnOriginalValues.put("docConfigJSON", _docConfigJSON);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("uuid_", "uuid");

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

		columnBitmasks.put("docConfigId", 2L);

		columnBitmasks.put("groupId", 4L);

		columnBitmasks.put("companyId", 8L);

		columnBitmasks.put("userId", 16L);

		columnBitmasks.put("userName", 32L);

		columnBitmasks.put("createDate", 64L);

		columnBitmasks.put("modifiedDate", 128L);

		columnBitmasks.put("docConfName", 256L);

		columnBitmasks.put("docFileType", 512L);

		columnBitmasks.put("docConfigJSON", 1024L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private File_Conf _escapedModel;

}