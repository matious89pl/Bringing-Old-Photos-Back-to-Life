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

package com.everis.messages.service.builder.model.impl;

import com.everis.messages.service.builder.model.Messages;
import com.everis.messages.service.builder.service.MessagesLocalServiceUtil;

/**
 * The extended model base implementation for the Messages service. Represents a row in the &quot;rec_messages&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link MessagesImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagesImpl
 * @see Messages
 * @generated
 */
public abstract class MessagesBaseImpl
	extends MessagesModelImpl implements Messages {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a messages model instance should use the <code>Messages</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			MessagesLocalServiceUtil.addMessages(this);
		}
		else {
			MessagesLocalServiceUtil.updateMessages(this);
		}
	}

}