package org.vsynytsyn.fidotestbackend.validation.annotation;

import org.vsynytsyn.fidotestbackend.validation.validator.ConsistentDateParametersAllValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = ConsistentDateParametersAllValidator.class)
@Target({ METHOD, CONSTRUCTOR })
@Retention(RUNTIME)
@Documented
public @interface ConsistentDateParametersAll {

    String message() default
            "End date must be after begin date";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
