package com.example.spring_validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Set;

@Service
@Validated
public class UserService {

    public void validateUserViaAnnotation(@Valid User user) {
        System.out.println("Bean is valid");
    }

    @Autowired
    Validator validator;

    public void validateUserProgrammatically(User user) {
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        if (!violations.isEmpty()) {
            for (ConstraintViolation<User> violation : violations) {
                System.out.println("Validation error: " + violation.getMessage());
            }
        } else {
            System.out.println("Bean is valid.");
        }
    }
}
