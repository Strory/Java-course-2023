package edu.hw5.task3;

import java.time.LocalDate;

public class HandlerTomorrowCheckDate extends Handler {
    public HandlerTomorrowCheckDate(Handler nextHandler) {
        super(nextHandler);
    }

    public HandlerTomorrowCheckDate() {
        super(null);
    }

    @Override
    public LocalDate handleRequest(String request) {
        final String KEYWORD = "tomorrow";
        if (KEYWORD.equals(request)) {
            return LocalDate.now().plusDays(1);
        } else if (nextHandler != null) {
            return nextHandler.handleRequest(request);
        }
        return null;
    }
}
