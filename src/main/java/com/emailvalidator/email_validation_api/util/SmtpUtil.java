package com.emailvalidator.email_validation_api.util;

import jakarta.mail.Session;
import jakarta.mail.Store;
import java.util.Properties;

public class SmtpUtil {

    public static boolean checkSmtp(String email) {
        String domain = email.substring(email.indexOf("@") + 1);

        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", domain);
            props.put("mail.smtp.port", "25");
            props.put("mail.smtp.connectiontimeout", "3000");
            props.put("mail.smtp.timeout", "3000");

            Session session = Session.getInstance(props);

            Store store = session.getStore("smtp");
            store.connect();

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}