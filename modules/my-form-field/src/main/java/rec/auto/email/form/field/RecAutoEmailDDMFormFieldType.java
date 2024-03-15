package rec.auto.email.form.field;

import com.liferay.dynamic.data.mapping.form.field.type.BaseDDMFormFieldType;
import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldType;
import com.liferay.frontend.js.loader.modules.extender.npm.NPMResolver;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author mmaceasd
 */
@Component(
	immediate = true,
	property = {
		"ddm.form.field.type.description=my-form-field-description",
		"ddm.form.field.type.display.order:Integer=13",
		"ddm.form.field.type.group=customized",
		"ddm.form.field.type.icon=text",
		"ddm.form.field.type.label=my-form-field-label",
		"ddm.form.field.type.name=myFormField"
	},
	service = DDMFormFieldType.class
)
public class RecAutoEmailDDMFormFieldType extends BaseDDMFormFieldType {

	@Override
	public String getModuleName() {
		return _npmResolver.resolveModuleName(
			"dynamic-data-my-form-field-form-field/my-form-field.es");
	}

	@Override
	public String getName() {
		return "myFormField";
	}

	@Override
	public boolean isCustomDDMFormFieldType() {
		return true;
	}

	@Reference
	private NPMResolver _npmResolver;

}