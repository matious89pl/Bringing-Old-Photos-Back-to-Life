
package com.everis.rec.activity.config;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;
import com.everis.rec.activity.constants.ActivitySearchClayManagementPortletKeys;

//@formatter:off
@ExtendedObjectClassDefinition(
	category = "Activity", 
	scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE
)
@Meta.OCD(
	id = ActivitySearchClayManagementPortletKeys.ConfigurationId
)
/**
 * Definicion de los campos que apareceran en la configuracion del sistema
 * 
 * @author jbodegam
 */
public interface ActivityConfiguration {
	
	@Meta.AD( 
			deflt = "No",
			name = "aggregateView", 
			required = false
		)

	public String aggregateView() ;
	
}

