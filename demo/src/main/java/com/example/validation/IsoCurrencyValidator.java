package com.example.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Currency;

public class IsoCurrencyValidator implements ConstraintValidator<IsoCurrency, String> {

    @Override
    public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
        try {
            return Currency.getInstance(arg0) != null;
        } catch (Exception e) {
            return false;
        }
    }

}
