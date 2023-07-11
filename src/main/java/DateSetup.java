package ru.netology;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateSetup {
    public static String setupDate() {
        return (LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
    }

}
