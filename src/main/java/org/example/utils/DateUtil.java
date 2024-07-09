package org.example.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {

    public static String parseDate(String value){
        DateTimeFormatter inputFormatter = determineDateFormat(value);
        LocalDate date = LocalDate.parse(value, inputFormatter);

        String desiredFormat = "yyyy-MM-dd";
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(desiredFormat);
        return date.format(outputFormatter);
    }

    private static DateTimeFormatter determineDateFormat(String dateString) {
        DateTimeFormatter[] dateFormatters = {
                DateTimeFormatter.ofPattern("yyyy-MM-dd"),
                DateTimeFormatter.ofPattern("yyyy/MM/dd"),
                DateTimeFormatter.ofPattern("yyyy.MM.dd"),
                DateTimeFormatter.ofPattern("dd-MM-yyyy"),
                DateTimeFormatter.ofPattern("dd/MM/yyyy"),
                DateTimeFormatter.ofPattern("MM/dd/yy"),
                DateTimeFormatter.ofPattern("dd-MMM-yyyy")
        };

        for (DateTimeFormatter formatter : dateFormatters) {
            try {
                formatter.parse(dateString);
                return formatter;
            } catch (DateTimeParseException e) {
                // Continue to next formatter
            }
        }

        throw new IllegalArgumentException("No valid date format found for: " + dateString);
    }

}
