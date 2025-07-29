package com.example.spring_validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Set;

@SpringBootApplication
public class SpringValidationApplication implements CommandLineRunner {
	@Autowired
	UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(SpringValidationApplication.class, args);
	}

	public void run(String... args) throws Exception {
		try {
			this.userService.validateUserViaAnnotation(User.builder().name("Marcus Chiu").build());
		} catch (ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			if (!violations.isEmpty()) {
				for(ConstraintViolation violation : violations) {
					System.out.println("Validation error: " + violation.getMessage());
				}
			} else {
				System.out.println("Bean is valid.");
			}
		}

		this.userService.validateUserProgrammatically(User.builder().name("Marcus Chiu").build());
	}
}
