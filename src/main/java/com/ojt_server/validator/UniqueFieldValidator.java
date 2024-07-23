package com.ojt_server.validator;



import com.ojt_server.modules.user.reqository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UniqueFieldValidator implements ConstraintValidator<UniqueField, String> {
    @Autowired
    private UserRepository userRepository;

    private String fieldName;

    @Override
    public void initialize(UniqueField constraintAnnotation) {
        fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return switch (fieldName) {
            case "userName" -> !userRepository.existsByUsername(value);
            case "email" -> !userRepository.existsByEmail(value);
            case "phone" -> !userRepository.existsByPhone(value);


            default -> throw new IllegalArgumentException("Invalid field name: " + fieldName);
        };
    }
}
