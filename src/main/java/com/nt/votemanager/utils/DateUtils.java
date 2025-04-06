package com.nt.votemanager.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

public class DateUtils {

    private static LocalDateTime dateToLocalDateTime(Date date) {
        if (Objects.isNull(date)) {
            return null;
        }
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public static LocalDateTime getEndDateFromFormatedString(String formattedString) {
        var timeValues = extractTimeFromFormattedString(formattedString);
        return LocalDateTime.now().plusDays(timeValues[0]).plusHours(timeValues[1]).plusMinutes(timeValues[2]).plusSeconds(timeValues[3]);
    }

    public static int[] extractTimeFromFormattedString(String formattedString) {
        if (Objects.isNull(formattedString) || formattedString.isBlank()) {
            return new int[]{0, 0, 1, 0};
        }

        String regex = "(\\d+d)?\\s*(\\d+h)?\\s*(\\d+m)?\\s*(\\d+s)?";
        if (!formattedString.matches(regex)) {
            throw new IllegalArgumentException("Invalid formatted string pattern");
        }

        int days = 0;
        int hours = 0;
        int minutes = 0;
        int seconds = 0;
        String[] parts = formattedString.split("\\s+");

        for (String part : parts) {
            if (part.endsWith("d")) {
                days = Integer.parseInt(part.replace("d", ""));
            } else if (part.endsWith("h")) {
                hours = Integer.parseInt(part.replace("h", ""));
            } else if (part.endsWith("m")) {
                minutes = Integer.parseInt(part.replace("m", ""));
            } else if (part.endsWith("s")) {
                seconds = Integer.parseInt(part.replace("s", ""));
            }
        }

        return new int[]{days, hours, minutes, seconds};
    }
}
