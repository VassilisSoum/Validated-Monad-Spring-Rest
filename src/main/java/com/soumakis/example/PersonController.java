package com.soumakis.example;

import com.soumakis.control.Validated;
import com.soumakis.example.domain.PersonRequest;
import com.soumakis.example.domain.PersonValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    private final PersonValidator personValidator;

    public PersonController(PersonValidator personValidator) {
        this.personValidator = personValidator;
    }

    @PostMapping
    public ResponseEntity<?> addPerson(@RequestBody PersonRequest personRequest) {
        Validated<List<String>, String> validationResult = personValidator.validatePerson(
            personRequest.name(),
            personRequest.age(),
            personRequest.city(),
            personRequest.email(),
            personRequest.password()
        );

        if (validationResult.isValid()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.badRequest().body(validationResult.getInvalid());
        }
    }
}
