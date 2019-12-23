package by.epam.web.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The type Date holder.
 */
public class DateHolder {
    /**
     * Find current date string.
     *
     * @return the string
     */
    public static String findCurrentDate(){
        return LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
