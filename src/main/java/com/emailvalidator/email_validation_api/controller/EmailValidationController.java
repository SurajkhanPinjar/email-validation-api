package com.emailvalidator.email_validation_api.controller;

import com.emailvalidator.email_validation_api.model.ValidationResponse;
import com.emailvalidator.email_validation_api.service.EmailValidationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmailValidationController {

    @Autowired
    private EmailValidationService service;

    @Operation(
            summary = "Validate Email",
            description = "Validates MX, SMTP, Syntax, Disposable",
            security = @SecurityRequirement(name = "apiKeyAuth")  // Swagger enforces key
    )
    @GetMapping("/validate")
    public ResponseEntity<ValidationResponse> validate(@RequestParam String email) {
        return ResponseEntity.ok(service.validateEmail(email));
    }
}