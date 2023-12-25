package edu.hw5.task3;

import java.time.LocalDate;

public class HandlerOneDayAgoCheckDate extends Handler {
    public HandlerOneDayAgoCheckDate(Handler nextHandler) {
        super(nextHandler);
    }

    public HandlerOneDayAgoCheckDate() {
        super(null);
    }

    @Override
    public LocalDate handleRequest(String request) {
        final String KEYWORD = "1 day ago";
        if (KEYWORD.equals(request)) {
            return LocalDate.now().minusDays(1);
        } else if (nextHandler != null) {
            return nextHandler.handleRequest(request);
        }
        return null;
    }
}
