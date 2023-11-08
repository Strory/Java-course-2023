package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task1 {
    private Task1() {
    }

    public static String getAverageTimeSession(String[] sessions) {
        int sumOfSessions = 0;
        for (String sessionDuration : sessions) {
            sumOfSessions += getSessionTime(sessionDuration);
        }
        return timeToString(sumOfSessions / sessions.length);
    }

    public static int getSessionTime(String times) {
        String[] timesPair = times.split(" - ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");
        LocalDateTime start = LocalDateTime.parse(timesPair[0], formatter);
        LocalDateTime end = LocalDateTime.parse(timesPair[1], formatter);
        return (int) Duration.between(start, end).toMinutes();
    }

    public static String timeToString(int time) {
        final int SIXTY = 60;
        int hour = time / SIXTY;
        int minutes = time % SIXTY;
        return hour + "ч " + minutes + "м";
    }
}
