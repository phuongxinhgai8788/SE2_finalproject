package app.helpers;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class NTValidator {
    private static final Validator validator = Validation.buildDefaultValidatorFactory()
                                                         .getValidator();

    public static <T> Set<ConstraintViolation<T>> validate(T target) {
        return validator.validate(target);
    }
}
