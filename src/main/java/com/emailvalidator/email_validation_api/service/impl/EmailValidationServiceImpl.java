package com.emailvalidator.email_validation_api.service.impl;

import com.emailvalidator.email_validation_api.model.ValidationResponse;
import com.emailvalidator.email_validation_api.service.EmailValidationService;
import com.emailvalidator.email_validation_api.util.DisposableEmailUtil;
import com.emailvalidator.email_validation_api.util.DnsUtil;
import com.emailvalidator.email_validation_api.util.SmtpUtil;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;

@Service
public class EmailValidationServiceImpl implements EmailValidationService {

    @Override
    public ValidationResponse validateEmail(String email) {

        boolean syntax = EmailValidator.getInstance().isValid(email);

        boolean mx = DnsUtil.hasMxRecord(email);

        boolean disposable = DisposableEmailUtil.isDisposable(email);

        boolean smtp = false;
        if (mx) {
            smtp = SmtpUtil.checkSmtp(email);
        }

        int score = calculateScore(syntax, mx, smtp, disposable);

        return ValidationResponse.builder()
                .email(email)
                .validSyntax(syntax)
                .validMx(mx)
                .smtpConnectivity(smtp)
                .disposable(disposable)
                .score(score)
                .suggestion(getSuggestion(email))
                .reason(getReason(syntax, mx, smtp, disposable))
                .build();
    }

    // TODO: Add AI or simple typo correction later
    private String getSuggestion(String email) {
        return null;
    }

    private String getReason(boolean syntax, boolean mx, boolean smtp, boolean disposable) {
        if(!syntax) return "Invalid syntax";
        if(!mx) return "No MX record";
        if(disposable) return "Disposable email detected";
        if(!smtp) return "SMTP not reachable";
        return "Valid email";
    }

    private int calculateScore(boolean syntax, boolean mx, boolean smtp, boolean disposable) {
        int score = 100;

        if (!syntax) score -= 50;
        if (!mx) score -= 30;
        if (!smtp) score -= 20;
        if (disposable) score -= 40;

        if (score < 0) score = 0;
        return score;
    }
}
