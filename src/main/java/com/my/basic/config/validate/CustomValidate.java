package com.my.basic.config.validate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import javax.validation.Validation;
import java.util.Collection;

@Component
public class CustomValidate implements Validator {

    private SpringValidatorAdapter adapter;

    public CustomValidate() {
        this.adapter = new SpringValidatorAdapter(
                Validation.buildDefaultValidatorFactory().getValidator()
        );
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(target instanceof Collection){
            Collection collection = (Collection) target;

            for (Object object : collection) {
                adapter.validate(object, errors);
            }

        } else {
            adapter.validate(target, errors);
        }
    }
}
