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

package rec.confidential.message.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the Confidential_Messages service. Represents a row in the &quot;confidential_messages&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see Confidential_MessagesModel
 * @generated
 */
@ImplementationClassName(
	"rec.confidential.message.model.impl.Confidential_MessagesImpl"
)
@ProviderType
public interface Confidential_Messages
	extends Confidential_MessagesModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>rec.confidential.message.model.impl.Confidential_MessagesImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Confidential_Messages, Long>
		MESSAGE_ID_ACCESSOR = new Accessor<Confidential_Messages, Long>() {

			@Override
			public Long get(Confidential_Messages confidential_Messages) {
				return confidential_Messages.getMessageId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Confidential_Messages> getTypeClass() {
				return Confidential_Messages.class;
			}

		};

}