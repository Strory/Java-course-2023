package edu.hw5.task3;

import java.time.LocalDate;

public class HandlerTodayCheckDate extends Handler {
    public HandlerTodayCheckDate(Handler nextHandler) {
        super(nextHandler);
    }

    public HandlerTodayCheckDate() {
        super(null);
    }

    @Override
    public LocalDate handleRequest(String request) {
        final String KEYWORD = "today";
        if (KEYWORD.equals(request)) {
            return LocalDate.now();
        } else if (nextHandler != null) {
            return nextHandler.handleRequest(request);
        }
        return null;
    }
}
