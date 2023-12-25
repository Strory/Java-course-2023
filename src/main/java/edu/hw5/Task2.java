package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class Task2 {
    public static final int DAY_OF_MONTH = 13;

    private Task2() {
    }

    public static List<LocalDate> getFridays(int year) {
        final int FIRST_MONTH = 1;
        final int MAX_MONTH_PLUS_1 = 13;
        final DayOfWeek day = DayOfWeek.FRIDAY;
        List<LocalDate> dates = new ArrayList<>();

        for (int i = FIRST_MONTH; i < MAX_MONTH_PLUS_1; ++i) {
            LocalDate date = LocalDate.of(year, i, DAY_OF_MONTH);
            if (day == date.getDayOfWeek()) {
                dates.add(date);
            }
        }
        return dates;
    }

    @SuppressWarnings("ParameterAssignment")
    public static LocalDate getNextFridayThirteen(LocalDate date) {
        if (!(date.getDayOfMonth() < DAY_OF_MONTH)) {
            date = date.plusMonths(1);
        }
        while (true) {
            date = date.with(TemporalAdjusters.dayOfWeekInMonth(2, DayOfWeek.FRIDAY));
            if (date.getDayOfMonth() == DAY_OF_MONTH) {
                return date;
            }
            date = date.plusMonths(1);
        }
    }
}



