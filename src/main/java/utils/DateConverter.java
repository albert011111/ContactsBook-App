package utils;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by Patryk on 2017-08-10.
 */
public class DateConverter {


    private static final String DATE_PATTERN = "yyyy-MM-dd";


    public static org.joda.time.LocalDate convertToDate(String input) {
        DateTimeFormatter dtf = DateTimeFormat.forPattern(DATE_PATTERN);
        return dtf.parseLocalDate(input);
    }
}
