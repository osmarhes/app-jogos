package br.com.loteria.app.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CEPValidator implements ConstraintValidator<CEP, String>{

	private String CEP_REGEX = "\\d{5}-\\d{3}";
	
	@Override
	public void initialize(CEP constraintAnnotation) {
		
	}

	@Override
	public boolean isValid(String CEP, ConstraintValidatorContext context) {
		if( CEP != null && !CEP.matches(CEP_REGEX) )
		{
			return false;
		}
	
		return true;
	}

}
