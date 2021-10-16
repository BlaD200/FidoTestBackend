package org.vsynytsyn.fidotestbackend.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;
import java.lang.annotation.Annotation;
import java.time.LocalDate;
import java.time.LocalTime;

@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public abstract class ConsistentDataParametersValidator<T extends Annotation>
        implements ConstraintValidator<T, Object[]> {

    @Override
    public boolean isValid(
            Object[] value,
            ConstraintValidatorContext context) {

        if (value[0] == null || value[1] == null || value[2] == null) {
            return false;
        }

        if (!(value[0] instanceof LocalDate)
                || !(value[1] instanceof LocalTime)
                || !(value[2] instanceof LocalTime)
        ) {
            throw new IllegalArgumentException(
                    "Illegal method signature, expected parameter of type LocalDate and two parameters of type " +
                            "LocalTime.");
        }

        LocalDate date = (LocalDate) value[0];
        LocalTime startsAt = (LocalTime) value[1];
        LocalTime endsAt = (LocalTime) value[2];

        return validateDate(date, startsAt, endsAt);
    }

    protected abstract boolean validateDate(LocalDate date, LocalTime start, LocalTime end);
}
