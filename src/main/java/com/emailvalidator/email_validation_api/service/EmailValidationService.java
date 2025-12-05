package com.emailvalidator.email_validation_api.service;

import com.emailvalidator.email_validation_api.model.ValidationResponse;

public interface EmailValidationService {
    ValidationResponse validateEmail(String email);
}
