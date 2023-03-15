package ro.alexrmn.hospitalmanagerbackend.validators;


import jakarta.validation.*;
import org.springframework.stereotype.Component;
import ro.alexrmn.hospitalmanagerbackend.exception.ObjectNotValidException;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ObjectValidator<T> {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    public void validate(T objectToValidate){
        Set<ConstraintViolation<T>> violations = validator.validate(objectToValidate);
        if (!violations.isEmpty()) {
            var errorMessages = violations
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toSet());
            throw new ObjectNotValidException(errorMessages);
        }
    }
}
