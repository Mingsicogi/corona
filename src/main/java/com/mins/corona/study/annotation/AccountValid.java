package com.mins.corona.study.annotation;

import com.mins.corona.study.annotation.validator.AccountValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * AccountDTO 검사
 *
 * @author minssogi
 */
@Target(value = ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AccountValidator.class)
public @interface AccountValid {
    String message() default "AccountDTO Validation error.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
