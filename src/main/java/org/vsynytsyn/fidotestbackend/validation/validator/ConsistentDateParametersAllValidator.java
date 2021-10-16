package org.vsynytsyn.fidotestbackend.validation.validator;


import org.vsynytsyn.fidotestbackend.validation.annotation.ConsistentDateParametersAll;

import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;
import java.time.LocalDate;
import java.time.LocalTime;

@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class ConsistentDateParametersAllValidator
        extends ConsistentDataParametersValidator<ConsistentDateParametersAll> {
    @Override
    protected boolean validateDate(LocalDate date, LocalTime start, LocalTime end) {
        return start.isBefore(end);
    }
}