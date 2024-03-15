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

package com.everis.rec.maintenanceactivity.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the MaintenanceActivity service. Represents a row in the &quot;AMA_MaintenanceActivity&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see MaintenanceActivityModel
 * @generated
 */
@ImplementationClassName(
	"com.everis.rec.maintenanceactivity.model.impl.MaintenanceActivityImpl"
)
@ProviderType
public interface MaintenanceActivity
	extends MaintenanceActivityModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.everis.rec.maintenanceactivity.model.impl.MaintenanceActivityImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<MaintenanceActivity, Long>
		MAINTENANCEACTIVITY_ID_ACCESSOR =
			new Accessor<MaintenanceActivity, Long>() {

				@Override
				public Long get(MaintenanceActivity maintenanceActivity) {
					return maintenanceActivity.getMaintenanceactivityId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<MaintenanceActivity> getTypeClass() {
					return MaintenanceActivity.class;
				}

			};

}