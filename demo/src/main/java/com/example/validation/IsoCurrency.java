package com.example.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

@Documented
@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {IsoCurrencyValidator.class})
public @interface IsoCurrency {
	
	String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
