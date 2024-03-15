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

package com.everis.service.management.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the helpdesk service. Represents a row in the &quot;SERVICEMANAGEMENT_helpdesk&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see helpdeskModel
 * @generated
 */
@ImplementationClassName(
	"com.everis.service.management.model.impl.helpdeskImpl"
)
@ProviderType
public interface helpdesk extends helpdeskModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.everis.service.management.model.impl.helpdeskImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<helpdesk, Long> HELPDESK_ID_ACCESSOR =
		new Accessor<helpdesk, Long>() {

			@Override
			public Long get(helpdesk helpdesk) {
				return helpdesk.getHelpdeskId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<helpdesk> getTypeClass() {
				return helpdesk.class;
			}

		};

}