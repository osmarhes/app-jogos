package br.com.loteria.app.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.loteria.app.viewmodel.ResultadoBean;

public class ConcursosValidator  implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ResultadoBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "concurso", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataSorteio", "field.required");
		
		ResultadoBean bean = (ResultadoBean) target;
		if(bean.getDezenas().size() < 6){
			errors.rejectValue("numeroPaginas", "field.required");
		}
	}

}
