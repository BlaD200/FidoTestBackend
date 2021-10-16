package org.vsynytsyn.fidotestbackend.validation.validator;

import org.vsynytsyn.fidotestbackend.validation.annotation.ConsistentDateParametersFuture;

import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;
import java.time.LocalDate;
import java.time.LocalTime;

@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class ConsistentDateParametersFutureValidator
        extends ConsistentDataParametersValidator<ConsistentDateParametersFuture> {
    @Override
    protected boolean validateDate(LocalDate date, LocalTime start, LocalTime end) {
        return ((date.isAfter(LocalDate.now()) || date.isEqual(LocalDate.now()))
                && start.isBefore(end)) && start.isAfter(LocalTime.now());
    }
}