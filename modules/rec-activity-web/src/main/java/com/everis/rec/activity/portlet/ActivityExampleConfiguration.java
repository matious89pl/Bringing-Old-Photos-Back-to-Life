package com.everis.rec.activity.portlet;

import aQute.bnd.annotation.metatype.Meta;
import com.everis.rec.activity.constants.ActivitySearchClayManagementPortletKeys;

@Meta.OCD(id = ActivitySearchClayManagementPortletKeys.CONFIGURATION_ID)
public interface ActivityExampleConfiguration {

	@Meta.AD(deflt = "white", 
			name = "color", 
			optionLabels = { "%White", "%Red", "%Yellow" }, 
			optionValues = { "white", "red", "yellow" }, 
			required = false
		)

		public String color();
	
	@Meta.AD( 
			deflt = "false",
			name = "aggregateView", 
			required = false
		)

	public String aggregateView() ;

}