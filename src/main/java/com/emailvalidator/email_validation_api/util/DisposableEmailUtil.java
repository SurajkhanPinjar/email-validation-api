package com.emailvalidator.email_validation_api.util;

import java.util.HashSet;
import java.util.Set;

public class DisposableEmailUtil {

    private static final Set<String> DISPOSABLE_DOMAINS = new HashSet<>();

    static {
        DISPOSABLE_DOMAINS.add("mailinator.com");
        DISPOSABLE_DOMAINS.add("10minutemail.com");
        DISPOSABLE_DOMAINS.add("tempmail.com");
        DISPOSABLE_DOMAINS.add("guerrillamail.com");
        // Add more later
    }

    public static boolean isDisposable(String email) {
        String domain = email.substring(email.indexOf("@") + 1).toLowerCase();
        return DISPOSABLE_DOMAINS.contains(domain);
    }
}