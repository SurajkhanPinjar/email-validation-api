package com.emailvalidator.email_validation_api.util;

import org.xbill.DNS.Lookup;
import org.xbill.DNS.MXRecord;
import org.xbill.DNS.Record;
import org.xbill.DNS.Type;

public class DnsUtil {

    public static boolean hasMxRecord(String email) {
        String domain = email.substring(email.indexOf("@") + 1);

        try {
            Lookup lookup = new Lookup(domain, Type.MX);
            lookup.run();

            if (lookup.getResult() == Lookup.SUCCESSFUL) {
                Record[] records = lookup.getAnswers();
                return records != null && records.length > 0;
            }
        } catch (Exception e) {
            return false;
        }

        return false;
    }
}