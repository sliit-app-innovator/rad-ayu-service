package com.sliit.ayu.ayuservice.utils;

import java.time.LocalDateTime;
import java.util.Base64;

public class Utils {
    private Utils(){}

    public static String generateReference(String prefix) {
        LocalDateTime localDateTime = LocalDateTime.now();
        String month = localDateTime.getMonth().ordinal() < 10 ? String.format("0%s", localDateTime.getMonth().ordinal()) : String.valueOf(localDateTime.getMonth().ordinal());
        String day = localDateTime.getDayOfMonth() < 10 ? String.format("0%s", localDateTime.getDayOfMonth()) : String.valueOf(localDateTime.getDayOfMonth());
        String hour = localDateTime.getHour() < 10 ? String.format("0%s", localDateTime.getHour()) : String.valueOf(localDateTime.getHour());
        String minute = localDateTime.getMinute() < 10 ? String.format("0%s", localDateTime.getMinute()) : String.valueOf(localDateTime.getMinute());
        String seconds = localDateTime.getSecond() < 10 ? String.format("0%s", localDateTime.getSecond()) : String.valueOf(localDateTime.getSecond());
        String suffix = String.format("%s%s%s%s%s%s", localDateTime.getYear(), month, day, hour, minute, seconds);
        return String.format("%s%s", prefix, suffix);
    }

    public static String base64Decode(String enc) {
        enc = enc.replace("-AAAAAAAAAA-", "");
        enc = enc.replace("-BBBBBBBBBB-", "");
        enc = enc.replace("-CCCCCCCCCC-", "");
        Base64.Decoder decoder = Base64.getDecoder();
        // Decode the Base64 encoded string
        byte[] decodedBytes = decoder.decode(enc);
        // Convert the decoded bytes to a String
        return new String(decodedBytes);
    }
}
