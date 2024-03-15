package rec.auto.email.form.field;

import com.liferay.dynamic.data.mapping.form.field.type.BaseDDMFormFieldRenderer;
import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldRenderer;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.render.DDMFormFieldRenderingContext;
import com.liferay.portal.kernel.template.Template;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.template.TemplateResource;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = "ddm.form.field.type.name=myFormField", service = DDMFormFieldRenderer.class)
public class RecAutoEmailDDMFormFieldRender extends BaseDDMFormFieldRenderer {

	@Override
	public String getTemplateLanguage() {
		return TemplateConstants.LANG_TYPE_SOY;
	}

	@Override
	public String getTemplateNamespace() {
		return "ddm.MyFormField";
	}

	@Override
	public TemplateResource getTemplateResource() {
		return _templateResource;
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_templateResource = getTemplateResource("/META-INF/resources/my-form-field.soy");
	}

	@Deactivate
	protected void deactivate() {
		_templateResource = null;
	}

	@Override
	protected void populateOptionalContext(Template template, DDMFormField ddmFormField,
			DDMFormFieldRenderingContext ddmFormFieldRenderingContext) {

		Map<String, Object> parameters = myFormFieldDDMFormFieldTemplateContextContributor.getParameters(ddmFormField,
				ddmFormFieldRenderingContext);

		template.putAll(parameters);
	}

	private TemplateResource _templateResource;
	
	@Reference
	protected RecAutoEmailDDMFormFieldTemplateContextContributor
	myFormFieldDDMFormFieldTemplateContextContributor;

}
