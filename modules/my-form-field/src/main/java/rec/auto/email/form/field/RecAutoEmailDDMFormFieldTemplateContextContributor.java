package rec.auto.email.form.field;

import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldTemplateContextContributor;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.render.DDMFormFieldRenderingContext;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Phone;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = "ddm.form.field.type.name=myFormField", service = {
		DDMFormFieldTemplateContextContributor.class, RecAutoEmailDDMFormFieldTemplateContextContributor.class })
public class RecAutoEmailDDMFormFieldTemplateContextContributor implements DDMFormFieldTemplateContextContributor {

	private final Log logger = LogFactoryUtil.getLog(RecAutoEmailDDMFormFieldTemplateContextContributor.class);
	
	@Override
	public Map<String, Object> getParameters(DDMFormField ddmFormField,
			DDMFormFieldRenderingContext ddmFormFieldRenderingContext) {
		
		Map<String, Object> parameters = new HashMap<>();
		String userEmail = "";
		String userFullname = "";
		String userPhone = "";
		User currentUser;
		
		long userId = PrincipalThreadLocal.getUserId();
		logger.debug("UserId: " + userId);
		
		try {
			currentUser = UserLocalServiceUtil.getUser(userId);
			
			//auto-mail
			userEmail = currentUser.getEmailAddress();	
			logger.debug("userEmail: " + userEmail);
			
			//auto-fullname
			userFullname = currentUser.getFullName();
			logger.debug("userFullname: " + userFullname);
			
			//auto-phone
			List<Phone> phoneList = currentUser.getPhones();
			
			for (Phone phone : phoneList) {
				
				Pattern pattern = Pattern.compile("^[0-9]{11}$");
				Matcher matcher = pattern.matcher(phone.getNumber());
				boolean matches = matcher.matches();

				if (phone.getPrimary() && matches) {
					userPhone = phone.getNumber();
					logger.debug("userPhone: " + userPhone);
				}
				
			}
			
		} catch (PortalException e) {
			logger.error("Error getting user information", e);
		}

		parameters.put("userEmail", userEmail);
		parameters.put("userFullname", userFullname);
		parameters.put("userPhone", userPhone);

		return parameters;
	}

}
