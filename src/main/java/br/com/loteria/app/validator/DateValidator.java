package br.com.loteria.app.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateValidator implements ConstraintValidator<Date, String>{
	private String DATE_REGEX;// = "\\d{2}-\\d{2}-\\d{4}( \\d{2}:\\d{2}(:\\d{2}(.\\d{3})?)?)?";
	
	@Override
	public void initialize(Date constraintAnnotation) {
		DATE_REGEX = constraintAnnotation.option().getExpression();
	}

	@Override
	public boolean isValid(String date, ConstraintValidatorContext context) {
		return date == null || date.matches(DATE_REGEX);
	}
}