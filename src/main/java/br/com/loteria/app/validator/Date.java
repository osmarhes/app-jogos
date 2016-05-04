package br.com.loteria.app.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = DateValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Date
{
	String message() default "DateValidator.java.lang.String";
    
    Class<?>[] groups() default {};
      
    Class<? extends Payload>[] payload() default {};
    
    DateValidationOption option() default DateValidationOption.DATE;
}
