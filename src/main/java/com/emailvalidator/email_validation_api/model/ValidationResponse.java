package com.emailvalidator.email_validation_api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValidationResponse {
    private String email;
    private boolean validSyntax;
    private String username;
    private String domain;
    private boolean validMx;
    private boolean smtpConnectivity;
    private boolean disposable;
    private String suggestion;  // gmial → gmail
    private String reason;
    private int score;
    private boolean freeProvider;
}