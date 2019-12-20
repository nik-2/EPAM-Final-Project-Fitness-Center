package by.epam.web.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateHolder {
    public static String findCurrentDate(){
        return LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
