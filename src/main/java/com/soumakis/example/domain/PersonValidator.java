package com.soumakis.example.domain;

import com.soumakis.control.Validated;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

@Service
public class PersonValidator {

    private static final Set<String> ALLOWED_CITIES = Set.of("New York", "Los Angeles", "Chicago",
        "Houston", "Phoenix", "Athens");
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
        "^(?=.*[0-9])(?=.*[!@#$%^&*])[A-Za-z0-9!@#$%^&*]{8,}$");
    private static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-z ]+$");

    public Validated<String, String> validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return Validated.invalid("Name cannot be empty");
        } else if (!NAME_PATTERN.matcher(name).matches()) {
            return Validated.invalid("Name must not contain numbers or special characters");
        } else {
            return Validated.valid(name);
        }
    }

    public Validated<String, Integer> validateAge(Integer age) {
        if (age == null) {
            return Validated.invalid("Age is required");
        } else if (age < 18 || age > 100) {
            return Validated.invalid("Age must be between 18 and 100");
        } else {
            return Validated.valid(age);
        }
    }

    public Validated<String, String> validateCity(String city) {
        if (city == null || city.trim().isEmpty()) {
            return Validated.invalid("City cannot be empty");
        } else if (!ALLOWED_CITIES.contains(city)) {
            return Validated.invalid("City must be one of " + ALLOWED_CITIES);
        } else {
            return Validated.valid(city);
        }
    }

    public Validated<String, String> validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return Validated.invalid("Email cannot be empty");
        } else if (!EMAIL_PATTERN.matcher(email).matches()) {
            return Validated.invalid("Invalid email format");
        } else {
            return Validated.valid(email);
        }
    }

    public Validated<String, String> validatePassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            return Validated.invalid("Password cannot be empty");
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            return Validated.invalid(
                "Password must be at least 8 characters long and contain at least one number and one special character");
        } else {
            return Validated.valid(password);
        }
    }

    public Validated<List<String>, String> validatePerson(String name, Integer age, String city,
        String email, String password) {
        List<Validated<String, ?>> validations = List.of(
            validateName(name),
            validateAge(age),
            validateCity(city),
            validateEmail(email),
            validatePassword(password)
        );

        return Validated.mapN(
            values -> "Person validated successfully",
            validations
        );
    }
}
