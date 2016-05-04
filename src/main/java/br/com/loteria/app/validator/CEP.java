package br.com.loteria.app.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = CEPValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CEP
{
	String message() default "CEPValidator.java.lang.String";
    
    Class<?>[] groups() default {};
      
    Class<? extends Payload>[] payload() default {};
}
